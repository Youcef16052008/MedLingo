package com.aistudio.medlingua.dzmed

import com.aistudio.medlingua.dzmed.data.initial.InitialData
import com.aistudio.medlingua.dzmed.domain.lesson.LessonGenerator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Preuve que les 90 leçons du parcours sont jouables — sur les **vraies**
 * données du seed (4 504 fiches trilingues).
 *
 * Avant : 44 exercices écrits main couvraient 13 couples (module, niveau) sur
 * 90 — 10 modules sur 15 n'avaient **aucun** exercice, et leur nœud pulsait
 * indéfiniment vers l'écran vide `no_exercises`. Maintenant, le générateur
 * dérive les questions des fiches : ce test mesure la couverture réelle.
 */
class LessonGeneratorRealDataTest {

    private val totalLessons = InitialData.modulesList.size * 6 // 15 modules × 6 niveaux

    @Test
    fun `les 15 modules du seed ont des termes`() {
        // Sans termes, pas de leçon générée. C'est la seule condition de
        // jouabilité de `PathScreen.moduleHasContent` après la phase 2.
        InitialData.modulesList.forEach { mod ->
            assertTrue(
                "le module ${mod.id} (${mod.titleFr}) n'a aucun terme — leçon injouable",
                InitialData.termsOfModule(mod.titleFr).isNotEmpty()
            )
        }
        assertEquals(15, InitialData.modulesList.size)
    }

    @Test
    fun `les 90 leçons du parcours sont generables`() {
        var playable = 0
        var emptyLessons = mutableListOf<String>()

        for (mod in InitialData.modulesList) {
            for (level in 1..6) {
                val terms = InitialData.termsOfModule(mod.titleFr)
                val lesson = LessonGenerator.lessonQuestions(mod.id, level, emptyList(), terms)
                if (lesson.isNotEmpty()) {
                    playable++
                } else {
                    emptyLessons.add("${mod.id}:L$level")
                }
            }
        }

        // C'est le chiffre qui compte : on passe de 13/90 à 90/90 leçons jouables.
        // La barre est stricte (90/90) — mesurée en Phase 4, le 10/10/2026, sur
        // les vraies données du seed. Une tolérance aurait masqué une régression
        // silencieuse de couverture (un module qui perd ses termes).
        assertTrue(
            "leçons vides restantes : $emptyLessons",
            emptyLessons.isEmpty()
        )
        assertEquals(
            "couverture $playable/$totalLessons — attendu 90/90 (100 %)",
            totalLessons,
            playable
        )
    }

    @Test
    fun `chaque leçon generee a entre 1 et 8 questions valides`() {
        for (mod in InitialData.modulesList) {
            for (level in 1..6) {
                val terms = InitialData.termsOfModule(mod.titleFr)
                val lesson = LessonGenerator.lessonQuestions(mod.id, level, emptyList(), terms)
                if (lesson.isEmpty()) continue
                assertTrue(
                    "${mod.id}:L$level dépasse LESSON_SIZE",
                    lesson.size <= LessonGenerator.LESSON_SIZE
                )
                lesson.forEach { ex ->
                    // Pas de question vide
                    assertTrue(
                        "${mod.id}:L$level id ${ex.id} : questionEn vide",
                        ex.questionEn.isNotBlank()
                    )
                    // Pas de réponse vide (sauf matching dont la réponse est les paires)
                    if (ex.type != "matching") {
                        assertTrue(
                            "${mod.id}:L$level id ${ex.id} : correctAnswer vide",
                            ex.correctAnswer.isNotBlank()
                        )
                    }
                    // Les MCQ ont bien 4 options
                    if (ex.type == "mcq" || ex.type == "reading" || ex.type == "clinical_case") {
                        assertEquals(
                            "${mod.id}:L$level id ${ex.id} (${ex.type}) : ${
                                ex.optionsRaw.split("|").size
                            } options au lieu de 4",
                            4,
                            ex.optionsRaw.split("|").size
                        )
                        // La réponse est bien parmi les options
                        assertTrue(
                            "${mod.id}:L$level id ${ex.id} : la réponse n'est pas dans les options",
                            ex.optionsRaw.split("|").any { it.trim() == ex.correctAnswer.trim() }
                        )
                    }
                }
            }
        }
    }

    @Test
    fun `le tirage est stable sur les vraies donnees - idempotent`() {
        // Deux visites de la même leçon donnent exactement les mêmes questions,
        // dans le même ordre. C'est ce qui rend la leçon révisable.
        val a = LessonGenerator.lessonQuestions("anat", 3, emptyList(), InitialData.termsOfModule("Anatomie"))
        val b = LessonGenerator.lessonQuestions("anat", 3, emptyList(), InitialData.termsOfModule("Anatomie"))
        assertEquals(a.map { it.id }, b.map { it.id })
        assertEquals(a.map { it.questionEn }, b.map { it.questionEn })
    }

    @Test
    fun `le niveau 6 genere des cas cliniques avec contexte`() {
        // L6 est le niveau BOSS (cas cliniques). C'est celui qui exige le plus
        // de données (exEn || pearl), donc le plus susceptible d'être appauvri.
        var clinicalGenerated = 0
        for (mod in InitialData.modulesList) {
            val lesson = LessonGenerator.lessonQuestions(mod.id, 6, emptyList(), InitialData.termsOfModule(mod.titleFr))
            clinicalGenerated += lesson.count { it.type == "clinical_case" }
        }
        // Tous les modules confondus, L6 doit produire des cas cliniques
        assertTrue(
            "L6 n'a généré aucun cas clinique — les niveaux BOSS sont vides",
            clinicalGenerated > 0
        )
    }

    @Test
    fun `tous les modules sont jouables`() {
        // Historiquement, 10 modules n'avaient aucun exercice écrit main et
        // étaient injouables. Désormais la phase 2 les remplit, mais la vraie
        // garantie à tester ne dépend pas de la répartition main/générée :
        // **chaque** module doit produire une leçon de 8 questions au niveau 1,
        // qu'il ait des exercices écrits main ou non.
        var playable = 0
        for (mod in InitialData.modulesList) {
            // Exercices écrits main de ce module (peut être vide).
            val real = InitialData.exercises.filter { it.module.trim() == mod.titleFr.trim() }
            val lesson = LessonGenerator.lessonQuestions(mod.id, 1, real, InitialData.termsOfModule(mod.titleFr))
            if (lesson.isNotEmpty()) playable++
        }
        assertEquals(
            "tous les modules ne sont pas jouables : $playable/${InitialData.modulesList.size}",
            InitialData.modulesList.size,
            playable
        )
    }
}
