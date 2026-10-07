package com.example

import com.example.domain.exercise.ExerciseChecker
import com.example.domain.exercise.MatchPair
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Regression tests for Lot 1 fixes:
 * - normalizeSentence stripped everything but [a-z0-9 ], turning Arabic text into "" == ""
 *   so any Arabic answer was accepted as correct.
 * - checkMatch accepted duplicated left/right pairs as correct.
 */
class ExerciseCheckerTest {

    // --- Bug 9: Arabic normalization ---

    @Test
    fun arabicAnswersAreNotAlwaysCorrect() {
        val result = ExerciseChecker.checkWordbank("مرحبا بالعالم", "القلب عضلة نابضة")
        assertFalse("Arabic user text must not equal a different Arabic answer", result.isCorrect)
    }

    @Test
    fun arabicWithDiacriticsMatchesBareArabic() {
        val withHarakat = "الْقَلْب عُضْلَة نَابِضَة"
        val bare = "القلب عضلة نابضة"
        assertTrue(
            "Arabic diacritics must be stripped before comparison",
            ExerciseChecker.checkWordbank(withHarakat, bare).isCorrect
        )
    }

    @Test
    fun arabicExactMatchIsCorrect() {
        assertTrue(
            ExerciseChecker.checkWordbank("القلب عضلة نابضة", "القلب عضلة نابضة").isCorrect
        )
    }

    @Test
    fun frenchAndEnglishStillMatch() {
        assertTrue(ExerciseChecker.checkWordbank("Le coeur pompe le sang", "Le coeur pompe le sang").isCorrect)
        assertTrue(ExerciseChecker.checkWordbank("The heart pumps blood", "The heart pumps blood").isCorrect)
        assertFalse(ExerciseChecker.checkWordbank("The heart pumps blood", "The lungs exchange gas").isCorrect)
    }

    @Test
    fun punctuationDoesNotBreakMatching() {
        assertTrue(ExerciseChecker.checkWordbank("Le coeur, pompe le sang !", "Le coeur pompe le sang").isCorrect)
    }

    // --- Bug 10: checkMatch duplicates / bad pairs ---

    private val correct = listOf(
        MatchPair("Skull", "Crâne"),
        MatchPair("Femur", "Fémur")
    )

    @Test
    fun duplicatedLeftPairIsIncorrect() {
        val user = listOf(
            MatchPair("Skull", "Crâne"),
            MatchPair("Skull", "Crâne")
        )
        assertFalse(
            "Duplicated left key must not pass as a full correct match",
            ExerciseChecker.checkMatch(user, correct).isCorrect
        )
    }

    @Test
    fun duplicatedRightPairIsIncorrect() {
        val user = listOf(
            MatchPair("Skull", "Crâne"),
            MatchPair("Femur", "Crâne")
        )
        assertFalse(ExerciseChecker.checkMatch(user, correct).isCorrect)
    }

    @Test
    fun wrongPairIsIncorrect() {
        val user = listOf(
            MatchPair("Skull", "Fémur"),
            MatchPair("Femur", "Crâne")
        )
        assertFalse(ExerciseChecker.checkMatch(user, correct).isCorrect)
    }

    @Test
    fun shuffledCorrectPairsAreCorrect() {
        val user = correct.reversed()
        assertTrue("Pair order must not matter", ExerciseChecker.checkMatch(user, correct).isCorrect)
    }

    @Test
    fun completeCorrectMatch() {
        assertTrue(ExerciseChecker.checkMatch(correct, correct).isCorrect)
    }
}
