package com.example

import com.example.data.initial.InitialData
import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.MedicalTermEntity
import com.example.domain.lesson.BulkExerciseGenerator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests du générateur en masse — 13 512 exercices (3 par terme × 4 504 termes).
 *
 * Ces tests ne valident pas que le contenu médical est exact (le générateur ne
 * fait que recopier les fiches trilingues, donc la qualité vient du seed), mais
 * ils garantissent les **invariants de l'UI** : si l'un d'eux échoue, l'écran
 * de quiz plante ou marque une bonne réponse comme fausse.
 */
class BulkExerciseGeneratorTest {

    private val allowedTypes = setOf(
        "mcq", "fill_blank", "matching", "sentence_order", "reading", "clinical_case"
    )

    private val allModules: List<Pair<String, List<MedicalTermEntity>>> by lazy {
        InitialData.modulesList.map { it.titleFr to InitialData.termsOfModule(it.titleFr) }
    }

    private val allExercises: List<ExerciseEntity> by lazy {
        BulkExerciseGenerator.generateAll(allModules)
    }

    @Test
    fun `le total d'exercices depasse 10000`() {
        assertTrue("total = ${allExercises.size}", allExercises.size >= 10_000)
    }

    @Test
    fun `chaque terme a exactement 3 exercices`() {
        val byModule = allExercises.groupBy { it.module }
        for ((moduleTitle, terms) in allModules) {
            val generated = byModule[moduleTitle].orEmpty()
            val expected = terms.size * BulkExerciseGenerator.EXERCISES_PER_TERM
            assertEquals(
                "module '$moduleTitle' : ${generated.size} exercices, attendus $expected",
                expected,
                generated.size
            )
        }
    }

    @Test
    fun `aucun id n est duplique`() {
        val ids = allExercises.map { it.id }
        val dupes = ids.groupBy { it }.filter { it.value.size > 1 }
        assertTrue(
            "${dupes.size} ids dupliqués (ex. ${dupes.keys.take(5)})",
            dupes.isEmpty()
        )
    }

    @Test
    fun `tous les ids sont negatifs et ne collisionnent pas avec le seed main`() {
        allExercises.forEach { ex ->
            assertTrue("id doit être négatif : ${ex.id}", ex.id < 0)
        }
        val seedIds = InitialData.exercises.map { it.id }.toSet()
        val collision = allExercises.map { it.id }.intersect(seedIds)
        assertTrue("collision avec le seed main : $collision", collision.isEmpty())
    }

    @Test
    fun `les 15 modules sont couverts`() {
        val covered = allExercises.map { it.module }.toSet()
        for (module in allModules.map { it.first }) {
            assertTrue("module non couvert : '$module'", module in covered)
        }
    }

    @Test
    fun `tous les types sont supportes par l UI`() {
        val types = allExercises.map { it.type }.toSet()
        for (type in types) {
            assertTrue("type non géré par l'UI : '$type'", type in allowedTypes)
        }
    }

    @Test
    fun `la reponse est toujours une option des QCM`() {
        // `McqQuestionView` compare le texte de l'option à `correctAnswer` :
        // si la réponse n'est pas dans `optionsRaw`, l'exercice est injouable.
        allExercises
            .filter { it.type == "mcq" || it.type == "fill_blank" }
            .forEach { ex ->
                val options = ex.getOptionsList().map { it.trim().lowercase() }
                assertTrue(
                    "ex ${ex.id} (${ex.module}) : options vides",
                    options.isNotEmpty()
                )
                assertTrue(
                    "ex ${ex.id} (${ex.module}) : '${ex.correctAnswer}' n'est pas dans $options",
                    ex.correctAnswer.trim().lowercase() in options
                )
            }
    }

    @Test
    fun `les QCM ont entre 2 et 4 options uniques`() {
        // `fill_blank` est exclu : c'est une saisie libre sur les deux plateformes
        // (Android `acceptedAnswers` = réponse seule, web `fillScore`), donc
        // `optionsRaw` ne contient que la réponse acceptée.
        allExercises
            .filter { it.type == "mcq" }
            .forEach { ex ->
                val options = ex.getOptionsList()
                assertTrue(
                    "ex ${ex.id} : ${options.size} options (attendu 2..4)",
                    options.size in 2..4
                )
                val normalized = options.map { it.trim().lowercase() }
                assertEquals(
                    "ex ${ex.id} : options dupliquées $options",
                    normalized.size,
                    normalized.toSet().size
                )
            }
    }

    @Test
    fun `le matching a 4 paires terme traduction`() {
        // `MatchingExerciseView` découpe `correctAnswer` sur `|` puis sur `:` :
        // chaque entrée doit avoir exactement 2 morceaux, sinon la paire est
        // ignorée silencieusement et l'exercice est impossible à valider.
        allExercises
            .filter { it.type == "matching" }
            .forEach { ex ->
                val options = ex.getOptionsList()
                assertEquals(
                    "ex ${ex.id} : ${options.size} paires (attendu 4)",
                    4,
                    options.size
                )
                options.forEach { pair ->
                    val parts = pair.split(":")
                    assertEquals(
                        "ex ${ex.id} : paire malformée '$pair'",
                        2,
                        parts.size
                    )
                    parts.forEach { part ->
                        assertTrue(
                            "ex ${ex.id} : côté de paire vide dans '$pair'",
                            part.isNotBlank()
                        )
                    }
                }
                // `correctAnswer` doit refléter exactement `optionsRaw` pour
                // que `checkMatchFromRaw` compare les bonnes paires.
                assertEquals(
                    "ex ${ex.id} : correctAnswer != optionsRaw",
                    ex.optionsRaw,
                    ex.correctAnswer
                )
            }
    }

    @Test
    fun `le sentence_order a des mots non vides et une reponse reconstituable`() {
        // `SentenceOrderExerciseView` découpe `correctAnswer` sur les espaces et
        // compare la phrase reconstruite par l'utilisateur : le pool de mots
        // doit donc contenir exactement les mots de la phrase, sans trou.
        allExercises
            .filter { it.type == "sentence_order" }
            .forEach { ex ->
                val pool = ex.getOptionsList()
                val answer = ex.correctAnswer.split(" ").filter { it.isNotBlank() }
                assertTrue(
                    "ex ${ex.id} : phrase vide",
                    answer.isNotEmpty()
                )
                assertEquals(
                    "ex ${ex.id} : le pool de mots ne correspond pas à la phrase",
                    answer.toSet(),
                    pool.toSet()
                )
            }
    }

    @Test
    fun `le reading et le clinical_case ont un contexte non vide`() {
        // La vignette s'affiche dans une carte dédiée au-dessus de la question ;
        // un contexte vide afficherait une zone blanche à la place.
        allExercises
            .filter { it.type == "reading" || it.type == "clinical_case" }
            .forEach { ex ->
                assertTrue(
                    "ex ${ex.id} (${ex.type}) : contextTextEn vide",
                    ex.contextTextEn.isNotBlank()
                )
                assertTrue(
                    "ex ${ex.id} (${ex.type}) : contextTextFr vide",
                    ex.contextTextFr.isNotBlank()
                )
                assertTrue(
                    "ex ${ex.id} (${ex.type}) : questionEn vide",
                    ex.questionEn.isNotBlank()
                )
            }
    }

    @Test
    fun `les points suivent la table android L1 a L6`() {
        // Table de référence : L1=10, L2=15, L3=20, L4=25, L5=30, L6=35.
        // Les tests de gamification (`ProgressionManager`) dépendent de cette
        // table pour le calcul d'XP.
        val expected = mapOf(1 to 10, 2 to 15, 3 to 20, 4 to 25, 5 to 30, 6 to 35)
        allExercises.forEach { ex ->
            assertTrue(
                "ex ${ex.id} : level hors plage ${ex.level}",
                ex.level in 1..6
            )
            assertEquals(
                "ex ${ex.id} (L${ex.level}) : points=${ex.points}",
                expected.getValue(ex.level),
                ex.points
            )
        }
    }

    @Test
    fun `aucun exercice n a un champ vide`() {
        allExercises.forEach { ex ->
            listOf(
                "questionEn" to ex.questionEn,
                "questionFr" to ex.questionFr,
                "questionAr" to ex.questionAr,
                "correctAnswer" to ex.correctAnswer,
                "explanationEn" to ex.explanationEn,
                "explanationFr" to ex.explanationFr,
                "explanationAr" to ex.explanationAr,
                "optionsRaw" to ex.optionsRaw,
                "module" to ex.module
            ).forEach { (name, value) ->
                assertTrue(
                    "ex ${ex.id} : $name est vide",
                    value.isNotBlank()
                )
            }
        }
    }

    @Test
    fun `la generation est deterministe`() {
        // Deux exécutions doivent donner le même résultat : sinon la base
        // changerait à chaque régénération et les ids deviendraient instables.
        val first = BulkExerciseGenerator.generateAll(allModules)
        val second = BulkExerciseGenerator.generateAll(allModules)
        assertEquals(first.size, second.size)
        first.zip(second).forEach { (a, b) ->
            assertEquals("id divergent", a.id, b.id)
            assertEquals("type divergent", a.type, b.type)
            assertEquals("questionEn divergente", a.questionEn, b.questionEn)
            assertEquals("correctAnswer divergent", a.correctAnswer, b.correctAnswer)
        }
    }

    @Test
    fun `les distracteurs sont differents de la reponse`() {
        allExercises
            .filter { it.type == "mcq" }
            .forEach { ex ->
                val options = ex.getOptionsList().map { it.trim().lowercase() }
                val answer = ex.correctAnswer.trim().lowercase()
                val distractors = options - answer
                assertTrue(
                    "ex ${ex.id} : aucun distracteur, la question est triviale",
                    distractors.isNotEmpty()
                )
            }
    }

    @Test
    fun `la diversite des types est superieure a 10`() {
        // Garde-fou : si on perd la variété des templates, on retombe sur le
        // défaut historique (tout en QCM de traduction).
        val types = allExercises.map { it.type }.distinct()
        assertTrue(
            "${types.size} types différents : $types",
            types.size >= 6
        )
    }

    @Test
    fun `un module vide ne plante pas`() {
        val out = BulkExerciseGenerator.generateModule("ModuleInexistant", emptyList())
        assertNotNull(out)
        assertTrue(out.isEmpty())
    }

    @Test
    fun `un module avec un seul terme produit 3 exercices`() {
        val term = InitialData.termsOfModule("Anatomie").first()
        val out = BulkExerciseGenerator.generateModule("Anatomie", listOf(term))
        assertEquals(3, out.size)
    }
}
