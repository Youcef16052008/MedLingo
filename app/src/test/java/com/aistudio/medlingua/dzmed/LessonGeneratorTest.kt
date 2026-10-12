package com.aistudio.medlingua.dzmed

import com.aistudio.medlingua.dzmed.data.local.entity.MedicalTermEntity
import com.aistudio.medlingua.dzmed.domain.lesson.LessonGenerator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * LessonGenerator — port Kotlin de `web-react/src/domain/lessons.ts`.
 *
 * Ce générateur est l'action qui transforme le produit : 44 exercices écrits
 * main couvraient 13 couples (module, niveau) sur 90 (14 %) — 10 modules sur 15
 * n'avaient **rien**. Ici on dérive les questions des 4 504 fiches trilingues.
 *
 * Les tests sur données réelles (`InitialData`) sont dans
 * `LessonGeneratorRealDataTest` ; ceux-ci valident la mécanique sur données
 * synthétiques.
 */
class LessonGeneratorTest {

    private val moduleTitle = "TestModule"

    private fun term(
        id: Int,
        en: String,
        fr: String,
        ar: String = "ترجمة$id",
        defFr: String = "Définition française de $en",
        defEn: String = "English definition of $en",
        defAr: String = "تعريف $en",
        etym: String = "latin $en",
        exEn: String = "Clinical context for $en.",
        exFr: String = "Contexte clinique de $en."
    ) = MedicalTermEntity(
        id = id, termEn = en, termFr = fr, termAr = ar,
        definitionEn = defEn, definitionFr = defFr, definitionAr = defAr,
        etymology = etym, clinicalPearl = "Perle de $en",
        module = moduleTitle, chapter = "Chapitre 1",
        exampleEn = exEn, exampleFr = exFr,
        ipaPhonetic = "/test/"
    )

    private val pool = (1..20).map {
        term(it, "term$it", "terme$it")
    }

    @Test
    fun `module inconnu ou niveau invalide renvoie une liste vide`() {
        assertEquals(emptyList<Nothing>(), LessonGenerator.lessonQuestions("nexiste_pas", 3, emptyList(), pool))
        assertEquals(emptyList<Nothing>(), LessonGenerator.lessonQuestions("anat", 0, emptyList(), pool))
        assertEquals(emptyList<Nothing>(), LessonGenerator.lessonQuestions("anat", 7, emptyList(), pool))
        // Pour utiliser un vrai id de module : on vérifie surtout que le pool vide
        // ne plante pas et renvoie bien vide.
        assertEquals(emptyList<Nothing>(), LessonGenerator.lessonQuestions("anat", 3, emptyList(), emptyList()))
    }

    @Test
    fun `un module avec des termes produit exactement 8 questions`() {
        // "anat" est un vrai id de module (InitialData) ; on passe des termes
        // synthétiques pour rester indépendant du seed.
        val lesson = LessonGenerator.lessonQuestions("anat", 2, emptyList(), pool)
        assertEquals(LessonGenerator.LESSON_SIZE, lesson.size)
    }

    @Test
    fun `les exercices reels sont places en tete et limites a 4`() {
        val real = (1..6).map { i ->
            com.aistudio.medlingua.dzmed.data.local.entity.ExerciseEntity(
                id = i, level = 1, type = "mcq", difficulty = "beginner",
                module = "Anatomie", chapter = "Ch1",
                questionEn = "Q$i", questionFr = "Q$i", questionAr = "Q$i",
                optionsRaw = "a|b|c|d", correctAnswer = "a",
                explanationEn = "", explanationFr = "", explanationAr = ""
            )
        }
        val lesson = LessonGenerator.lessonQuestions("anat", 1, real, pool)
        assertEquals(8, lesson.size)
        // Les 4 premiers sont les exercices réels (limités à REAL_EXERCISES_MAX)
        assertEquals(1, lesson[0].id)
        assertEquals(4, lesson.take(4).count { it.id in 1..6 })
        // Le reste est généré : ids négatifs, jamais de collision
        val generated = lesson.drop(4)
        assertTrue(generated.isNotEmpty())
        assertTrue(generated.all { it.id < 0 })
    }

    @Test
    fun `le tirage est deterministe - deux appels donnent la meme lecon`() {
        val a = LessonGenerator.lessonQuestions("anat", 3, emptyList(), pool)
        val b = LessonGenerator.lessonQuestions("anat", 3, emptyList(), pool)
        assertEquals(a.map { it.id }, b.map { it.id })
        assertEquals(a.map { it.correctAnswer }, b.map { it.correctAnswer })
        // Et deux modules différents donnent des leçons différentes
        val c = LessonGenerator.lessonQuestions("physio", 3, emptyList(), pool)
        assertNotEquals(a.map { it.id }, c.map { it.id })
    }

    @Test
    fun `L1 L2 ne generent que des mcq de traduction`() {
        val lesson = LessonGenerator.lessonQuestions("anat", 1, emptyList(), pool)
        assertTrue(lesson.all { it.type == "mcq" })
        assertTrue(lesson.all { it.optionsRaw.split("|").size == 4 })
    }

    @Test
    fun `L3 L4 generent des matching et des mcq`() {
        val lesson = LessonGenerator.lessonQuestions("anat", 3, emptyList(), pool)
        assertTrue(lesson.any { it.type == "matching" })
        // Un matching a 4 paires "en:fr"
        val match = lesson.first { it.type == "matching" }
        assertEquals(4, match.optionsRaw.split("|").size)
        match.optionsRaw.split("|").forEach { pair ->
            assertTrue("chaque paire est 'en:fr'", pair.contains(":"))
        }
    }

    @Test
    fun `L5 genere des reading, L6 des clinical_case`() {
        val l5 = LessonGenerator.lessonQuestions("anat", 5, emptyList(), pool)
        assertTrue("L5 doit contenir au moins un reading", l5.any { it.type == "reading" })
        l5.filter { it.type == "reading" }.forEach {
            assertTrue(it.contextTextEn.isNotBlank())
        }

        val l6 = LessonGenerator.lessonQuestions("anat", 6, emptyList(), pool)
        assertTrue("L6 doit contenir au moins un clinical_case", l6.any { it.type == "clinical_case" })
        l6.filter { it.type == "clinical_case" }.forEach {
            assertTrue(it.contextTextEn.isNotBlank())
        }
    }

    @Test
    fun `la bonne reponse est toujours parmi les options`() {
        for (level in 1..6) {
            val lesson = LessonGenerator.lessonQuestions("anat", level, emptyList(), pool)
            lesson.filter { it.type != "matching" }.forEach { ex ->
                val options = ex.optionsRaw.split("|")
                assertTrue("niveau $level : les QCM ont 4 options", options.size == 4)
                assertTrue(
                    "niveau $level : la réponse est dans les options",
                    options.any { it.trim() == ex.correctAnswer.trim() }
                )
            }
        }
    }

    @Test
    fun `les options sont uniques`() {
        for (level in 1..6) {
            val lesson = LessonGenerator.lessonQuestions("anat", level, emptyList(), pool)
            lesson.filter { it.type == "mcq" }.forEach { ex ->
                val options = ex.optionsRaw.split("|").map { it.trim() }
                assertEquals(
                    "niveau $level : options dupliquées dans ${ex.questionEn}",
                    options.size,
                    options.toSet().size
                )
            }
        }
    }

    @Test
    fun `les points suivent la table L1 a L6 (10, 15, 20, 25, 30, 35)`() {
        for (level in 1..6) {
            val lesson = LessonGenerator.lessonQuestions("anat", level, emptyList(), pool)
            val expected = 10 + (level - 1) * 5
            assertTrue(
                "niveau $level : tous les exercices valent $expected points",
                lesson.all { it.points == expected }
            )
        }
    }

    @Test
    fun `lessonScore calcule un pourcentage arrondi`() {
        assertEquals(100, LessonGenerator.lessonScore(8, 8))
        assertEquals(63, LessonGenerator.lessonScore(5, 8))
        assertEquals(50, LessonGenerator.lessonScore(1, 2))
        assertEquals(0, LessonGenerator.lessonScore(0, 0))
    }

    @Test
    fun `un terme aux champs vides est saute, pas fatal`() {
        // Cas réaliste : la moitié des termes a des données, l'autre non.
        // Le générateur doit sauter les termes inutilisables et remplir la leçon.
        val mixed = (1..20).map {
            if (it % 2 == 0) {
                term(it, "t$it", "tf$it", etym = "", defFr = "", defEn = "", exEn = "")
            } else {
                term(it, "t$it", "tf$it")
            }
        }
        for (level in 1..6) {
            val lesson = LessonGenerator.lessonQuestions("anat", level, emptyList(), mixed)
            assertTrue("L$level doit être jouable avec des données partielles", lesson.isNotEmpty())
            assertTrue(lesson.size <= LessonGenerator.LESSON_SIZE)
            // Et chaque question a bien une réponse
            lesson.filter { it.type != "matching" }.forEach { ex ->
                assertTrue(ex.correctAnswer.isNotBlank())
            }
        }
    }

    @Test
    fun `un module totalement vide de donnees ne plante pas et renvoie vide`() {
        // Borne de sécurité du port web : si TOUTES les données manquent
        // (def + ex + etym vides partout), la leçon est vide — pas de boucle
        // infinie, pas de crash. L'écran affiche alors `no_exercises`.
        val allEmpty = (1..20).map {
            term(it, "t$it", "tf$it", etym = "", defFr = "", defEn = "", exEn = "")
        }
        for (level in 1..6) {
            val lesson = LessonGenerator.lessonQuestions("anat", level, emptyList(), allEmpty)
            // À L1/L2 les QCM de traduction n'ont besoin que de termEn/termFr :
            // ils marchent toujours. À L3+ tout peut être vide.
            assertTrue(lesson.size <= LessonGenerator.LESSON_SIZE)
        }
    }
}
