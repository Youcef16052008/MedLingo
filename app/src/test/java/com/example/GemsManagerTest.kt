package com.example

import com.example.domain.gamification.GemsManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * GemsManager — regression tests.
 *
 * The "perfect lesson" bonus used to be a caller-provided flag. The two call sites
 * disagreed:
 *   - quiz completion:   isPerfect = correctAnswers == totalQuestions && totalQuestions > 0
 *   - level completion:  isPerfect = scorePercentage == 100
 * so the bonus was an exploitable input instead of a derived property. These tests
 * pin the single derived rule.
 */
class GemsManagerTest {

    // ---------- calculateGemsForLesson ----------

    @Test
    fun `perfect lesson pays the base reward plus the bonus`() {
        val gems = GemsManager.calculateGemsForLesson(
            correctAnswers = 5,
            totalQuestions = 5
        )
        assertEquals(
            GemsManager.GEMS_LESSON_COMPLETE + GemsManager.GEMS_PERFECT_LESSON_BONUS,
            gems
        )
    }

    @Test
    fun `non-perfect lesson above the accuracy floor pays the base reward only`() {
        // 4/5 = 80% accuracy -> base only, no perfect bonus
        assertEquals(
            GemsManager.GEMS_LESSON_COMPLETE,
            GemsManager.calculateGemsForLesson(correctAnswers = 4, totalQuestions = 5)
        )
    }

    @Test
    fun `perfect bonus cannot be granted at less than 100 percent`() {
        // Regression: a caller could flag 3/5 as "perfect" (and the level path did
        // exactly that via scorePercentage). Now the bonus is derived, so 60% accuracy
        // pays the base reward only.
        assertEquals(
            GemsManager.GEMS_LESSON_COMPLETE,
            GemsManager.calculateGemsForLesson(correctAnswers = 3, totalQuestions = 5)
        )
    }

    @Test
    fun `lesson below half accuracy pays nothing`() {
        // Farming guard: 2/5 = 40% < 50%
        assertEquals(0, GemsManager.calculateGemsForLesson(correctAnswers = 2, totalQuestions = 5))
    }

    @Test
    fun `exactly half accuracy pays the base reward`() {
        // 2/4 = 50%, the floor is inclusive
        assertEquals(
            GemsManager.GEMS_LESSON_COMPLETE,
            GemsManager.calculateGemsForLesson(correctAnswers = 2, totalQuestions = 4)
        )
    }

    @Test
    fun `empty lesson pays nothing and cannot be perfect`() {
        // totalQuestions == 0 -> early return before the perfect check
        assertEquals(0, GemsManager.calculateGemsForLesson(correctAnswers = 0, totalQuestions = 0))
    }

    // ---------- transaction helpers ----------

    @Test
    fun `earn transaction carries the balance after and the supplied timestamp`() {
        val tx = GemsManager.createEarnTransaction(
            amount = 20,
            reason = "perfect_lesson",
            currentBalance = 30,
            metadata = "points:25 correct:5/5",
            timestamp = 1_700_000_000_000L
        )
        assertEquals("EARN", tx.type)
        assertEquals(20, tx.amount)
        assertEquals("perfect_lesson", tx.reason)
        assertEquals(50, tx.balanceAfter)
        assertEquals("points:25 correct:5/5", tx.metadata)
        assertEquals(1_700_000_000_000L, tx.timestamp)
    }

    @Test
    fun `spend transaction is refused when the balance is insufficient`() {
        assertNull(
            GemsManager.createSpendTransaction(
                amount = GemsManager.GEMS_STREAK_FREEZE,
                reason = "streak_freeze",
                currentBalance = GemsManager.GEMS_STREAK_FREEZE - 1,
                timestamp = 1_700_000_000_000L
            )
        )
    }

    @Test
    fun `spend transaction debits the balance and stores a negative amount`() {
        val tx = GemsManager.createSpendTransaction(
            amount = GemsManager.GEMS_STREAK_FREEZE,
            reason = "streak_freeze",
            currentBalance = 300,
            timestamp = 1_700_000_000_000L
        )
        assertEquals("SPEND", tx?.type)
        assertEquals(-GemsManager.GEMS_STREAK_FREEZE, tx?.amount)
        assertEquals(300 - GemsManager.GEMS_STREAK_FREEZE, tx?.balanceAfter)
    }
}
