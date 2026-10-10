package com.example

import com.example.data.initial.InitialData
import com.example.domain.lesson.LessonGenerator
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Qualité des leçons générées (Phase 5).
 *
 * `LessonGeneratorRealDataTest` prouve que les 90 leçons sont *jouables*
 * (non vides). Ce test va plus loin : une leçon qui contient 5 questions au
 * lieu de 8 est une leçon **appauvrie**. C'est le défaut silencieux actuel :
 *
 *  - L5 planifie 4 × `READING` (exige `exampleEn`, présent à 25 %)
 *  - L6 planifie 4 × `CLINICAL` (exige `exampleEn` ou `pearl`, 25 %)
 *  - L3/L4 planifient 2 × `ETYM` (exige `etymology`, présent à 8 %)
 *
 * Quand le kind est inéligible, `build()` retourne `null` et le slot est
 * perdu — le générateur avance d'un terme mais conserve le kind, qui est
 * à nouveau inéligible. La leçon rétrécit sans erreur.
 */
class LessonQualityTest {

    @Test
    fun `les leçons de niveaux 5 et 6 ont 8 questions`() {
        // Avant le correctif : ~5/8 (62 %) — 3 slots `READING`/`CLINICAL`
        // silencieusement abandonnés.
        InitialData.modulesList.forEach { mod ->
            val terms = InitialData.termsOfModule(mod.titleFr)
            if (terms.isEmpty()) return@forEach

            for (level in 5..6) {
                val lesson = LessonGenerator.lessonQuestions(mod.id, level, emptyList(), terms)
                assertTrue(
                    "L$level de ${mod.titleFr} : ${lesson.size} questions, " +
                        "attendu ${LessonGenerator.LESSON_SIZE} — la leçon est appauvrie",
                    lesson.size >= LessonGenerator.LESSON_SIZE
                )
            }
        }
    }

    @Test
    fun `les leçons de niveaux 3 et 4 ont 8 questions`() {
        // Avant le correctif : ~6/8 (77 %) — 2 slots `ETYM` perdus.
        InitialData.modulesList.forEach { mod ->
            val terms = InitialData.termsOfModule(mod.titleFr)
            if (terms.isEmpty()) return@forEach

            for (level in 3..4) {
                val lesson = LessonGenerator.lessonQuestions(mod.id, level, emptyList(), terms)
                assertTrue(
                    "L$level de ${mod.titleFr} : ${lesson.size} questions, " +
                        "attendu ${LessonGenerator.LESSON_SIZE}",
                    lesson.size >= LessonGenerator.LESSON_SIZE
                )
            }
        }
    }

    @Test
    fun `aucune leçon des 90 n'est en dessous de 8 questions`() {
        // Garde-fou global : barre minimale pour les 90 leçons.
        InitialData.modulesList.forEach { mod ->
            val terms = InitialData.termsOfModule(mod.titleFr)
            if (terms.isEmpty()) return@forEach

            for (level in 1..6) {
                val lesson = LessonGenerator.lessonQuestions(mod.id, level, emptyList(), terms)
                if (lesson.isEmpty()) continue // injouable : couvert ailleurs
                assertTrue(
                    "L$level de ${mod.titleFr} : ${lesson.size} questions (< 8)",
                    lesson.size >= LessonGenerator.LESSON_SIZE
                )
            }
        }
    }
}
