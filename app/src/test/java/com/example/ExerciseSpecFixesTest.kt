package com.example

import com.example.domain.exercise.ExerciseChecker
import com.example.domain.exercise.ExerciseLanguage
import com.example.domain.exercise.ExerciseSpec
import com.example.domain.exercise.ExerciseSpecMigrator
import com.example.domain.exercise.SameExamSwappedLanguage
import com.example.domain.exercise.WordbankOrder
import com.example.data.local.entity.ExerciseEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Regression tests for Lot 2:
 * - bug 12: fill_blank acceptedAnswers included every distractor option
 * - bug 13: wordbank correctOrder broke on duplicate / multi-word / case-mismatched tokens
 * - bug 14: checkFill fuzzy accepted 1-char and empty answers
 * - bug 17: swapLanguage destroyed the original English prompt
 */
class ExerciseSpecFixesTest {

    // --- Bug 12 ---

    private fun fillBlankEntity() = ExerciseEntity(
        id = 1,
        level = 3,
        type = "fill_blank",
        difficulty = "intermediate",
        module = "Anatomie",
        chapter = "Myologie",
        questionEn = "The biceps brachii _______ the elbow joint.",
        questionFr = "Le biceps brachii _______ l'articulation du coude.",
        questionAr = "الiceps brachii _______ مفصل المرفق.",
        optionsRaw = "flexes|extends|abducts|fractures",
        correctAnswer = "flexes",
        explanationEn = "",
        explanationFr = "",
        explanationAr = ""
    )

    @Test
    fun fillBlankAcceptsOnlyCorrectAnswer() {
        val json = ExerciseSpecMigrator.migrateToSpecJson(fillBlankEntity())
        val spec = ExerciseSpecMigrator.parseSpecJson(json)
        assertTrue("spec must parse back", spec is ExerciseSpec.Fill)
        val fill = spec as ExerciseSpec.Fill
        assertEquals(listOf("flexes"), fill.acceptedAnswers)
        assertFalse(
            "Distractors must not be accepted as correct",
            fill.acceptedAnswers.contains("extends")
        )
    }

    @Test
    fun fillBlankLegacyPathAcceptsOnlyCorrectAnswer() {
        val spec = fillBlankEntity().toExerciseSpec()
        assertTrue(spec is ExerciseSpec.Fill)
        assertEquals(listOf("flexes"), (spec as ExerciseSpec.Fill).acceptedAnswers)
    }

    // --- Bug 13 ---

    @Test
    fun wordbankOrderHandlesDuplicateTokens() {
        val bank = listOf("the", "cat", "sat", "the", "mat")
        val order = WordbankOrder.computeCorrectOrder(bank, "the cat sat on the mat")
        // "on" is not in the bank and must be skipped; the second "the" maps to index 3
        assertEquals(listOf(0, 1, 2, 3, 4), order)
    }

    @Test
    fun wordbankOrderHandlesMultiWordTokens() {
        val bank = listOf("in", "order to", "function")
        val order = WordbankOrder.computeCorrectOrder(bank, "in order to function")
        assertEquals(listOf(0, 1, 2), order)
    }

    @Test
    fun wordbankOrderIsCaseInsensitive() {
        val bank = listOf("The", "cat")
        assertEquals(listOf(0, 1), WordbankOrder.computeCorrectOrder(bank, "the cat"))
    }

    @Test
    fun wordbankOrderNeverCoercesUnknownWordsToZero() {
        val bank = listOf("alpha", "beta")
        val order = WordbankOrder.computeCorrectOrder(bank, "alpha gamma beta")
        assertEquals(listOf(0, 1), order)
    }

    @Test
    fun wordbankOrderSkipsWordsMissingFromBank() {
        // Synthetic fixture pinning the bug-13 rule: words absent from the bank
        // are skipped, never mapped to index 0.
        val entity = ExerciseEntity(
            id = 30,
            level = 5,
            type = "sentence_order",
            difficulty = "advanced",
            module = "Physiologie",
            chapter = "Digestion",
            questionEn = "q",
            questionFr = "q",
            questionAr = "q",
            optionsRaw = "so that|digestion|is efficient",
            correctAnswer = "digestion is efficient so that absorption happens",
            explanationEn = "",
            explanationFr = "",
            explanationAr = ""
        )
        val spec = ExerciseSpecMigrator.migrateToSpecJson(entity)
        val parsed = ExerciseSpecMigrator.parseSpecJson(spec) as ExerciseSpec.Wordbank
        // "absorption" and "happens" are not in the bank → skipped, never mapped to 0
        assertEquals(listOf(1, 2, 0), parsed.correctOrder)
    }

    @Test
    fun wordbankOrderIsStructurallyValidOnEveryRealSentenceOrderExercise() {
        val real = com.example.data.initial.InitialData.exercises
            .filter { it.type == "sentence_order" }
        assertTrue("no sentence_order exercises in seed", real.isNotEmpty())
        real.forEach { entity ->
            val spec = ExerciseSpecMigrator.migrateToSpecJson(entity)
            val parsed = ExerciseSpecMigrator.parseSpecJson(spec) as ExerciseSpec.Wordbank
            val bank = entity.getOptionsList()
            assertTrue(
                "id=${entity.id} empty correctOrder",
                parsed.correctOrder.isNotEmpty()
            )
            parsed.correctOrder.forEach { idx ->
                assertTrue(
                    "id=${entity.id} index $idx outside bank size ${bank.size}",
                    idx in bank.indices
                )
            }
            assertEquals(
                "id=${entity.id} duplicate indices",
                parsed.correctOrder.size,
                parsed.correctOrder.toSet().size
            )
        }
    }

    // --- Bug 14 ---

    @Test
    fun checkFillRejectsOneCharacterAnswers() {
        val result = ExerciseChecker.checkFill("a", listOf("protects"))
        assertFalse(result.isCorrect)
        assertEquals(0.0, result.score, 0.0001)
        assertFalse(result.isFuzzy)
    }

    @Test
    fun checkFillRejectsEmptyAnswer() {
        val result = ExerciseChecker.checkFill("", listOf("protects"))
        assertFalse(result.isCorrect)
        assertEquals(0.0, result.score, 0.0001)
        assertFalse(result.isFuzzy)
    }

    @Test
    fun checkFillGivesPartialCreditForMeaningfulPrefix() {
        val result = ExerciseChecker.checkFill("protect", listOf("protects"))
        assertFalse(result.isCorrect)
        assertTrue(result.isFuzzy)
        assertEquals(0.5, result.score, 0.0001)
    }

    @Test
    fun checkFillAcceptsExactAnswerCaseInsensitively() {
        assertTrue(ExerciseChecker.checkFill("PROTECTS", listOf("protects")).isCorrect)
    }

    @Test
    fun checkFillIgnoresBlankAcceptedEntries() {
        val result = ExerciseChecker.checkFill("", listOf(""))
        assertFalse(result.isCorrect)
    }

    // --- Bug 17 ---

    private val canonicalChoice = ExerciseSpec.Choice(
        promptEn = "What is the patella?",
        promptFr = "Qu'est-ce que la patella ?",
        promptAr = "ما هي الرضفة؟",
        options = listOf("Kneecap", "Femur", "Tibia", "Fibula"),
        correctIndex = 0,
        explanationEn = "e",
        explanationFr = "e",
        explanationAr = "e"
    )

    @Test
    fun swapToFrenchPutsFrenchPromptInDisplaySlot() {
        val swapped = SameExamSwappedLanguage.swapLanguage(canonicalChoice, ExerciseLanguage.FRENCH) as ExerciseSpec.Choice
        assertEquals(canonicalChoice.promptFr, swapped.promptEn)
    }

    @Test
    fun swapKeepsNativeTranslationsIntact() {
        val swapped = SameExamSwappedLanguage.swapLanguage(canonicalChoice, ExerciseLanguage.ARABIC) as ExerciseSpec.Choice
        assertEquals(canonicalChoice.promptAr, swapped.promptEn)
        assertEquals(canonicalChoice.promptFr, swapped.promptFr)
        assertEquals(canonicalChoice.promptAr, swapped.promptAr)
        assertEquals(canonicalChoice.options, swapped.options)
        assertEquals(canonicalChoice.correctIndex, swapped.correctIndex)
    }

    @Test
    fun swappingBackToEnglishFromCanonicalRestoresEnglish() {
        val toFr = SameExamSwappedLanguage.swapLanguage(canonicalChoice, ExerciseLanguage.FRENCH) as ExerciseSpec.Choice
        // Round-trip must derive from the canonical spec (as setExerciseLanguage does)
        val backToEn = SameExamSwappedLanguage.swapLanguage(canonicalChoice, ExerciseLanguage.ENGLISH) as ExerciseSpec.Choice
        assertEquals(canonicalChoice.promptEn, backToEn.promptEn)
        assertEquals(canonicalChoice.promptFr, toFr.promptFr)
    }
}
