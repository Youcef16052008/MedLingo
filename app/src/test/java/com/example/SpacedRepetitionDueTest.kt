package com.example

import com.example.data.local.entity.FlashcardProgressEntity
import com.example.domain.sm2.SpacedRepetitionAlgorithm
import com.example.domain.time.FakeClock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * "Due" cards used to be computed with System.currentTimeMillis() in four different
 * places while the schedule itself came from an injected clock. With a fake clock every
 * card looked due, and the badge, the home card and the reminder could disagree.
 * These tests pin the clock-injected rule at its single source.
 */
class SpacedRepetitionDueTest {

    private val now = 1_700_000_000_000L
    private val clock = FakeClock(now)

    private fun progress(id: Int, nextReviewTimestamp: Long) = FlashcardProgressEntity(
        termId = id,
        repetitions = 1,
        easeFactor = 2.5,
        intervalDays = 1,
        nextReviewTimestamp = nextReviewTimestamp,
        lastReviewedTimestamp = now - 86_400_000L,
        lastQuality = 4
    )

    @Test
    fun `a card scheduled in the future is not due`() {
        assertFalse(
            SpacedRepetitionAlgorithm.isDue(now + 60_000L, clock)
        )
    }

    @Test
    fun `a card scheduled exactly now is due`() {
        assertTrue(
            "the due date is inclusive",
            SpacedRepetitionAlgorithm.isDue(now, clock)
        )
    }

    @Test
    fun `a card scheduled in the past is due`() {
        assertTrue(SpacedRepetitionAlgorithm.isDue(now - 1L, clock))
    }

    @Test
    fun `dueCount counts only the cards whose date is reached`() {
        val cards = listOf(
            progress(1, now - 1_000L),          // due
            progress(2, now),                   // due (inclusive)
            progress(3, now + 86_400_000L),     // tomorrow
            progress(4, now + 7L)               // in a week
        )
        assertEquals(2, SpacedRepetitionAlgorithm.dueCount(cards, clock))
    }

    @Test
    fun `dueCount is empty without progress`() {
        assertEquals(0, SpacedRepetitionAlgorithm.dueCount(emptyList(), clock))
    }

    @Test
    fun `a card scheduled by calculateNext is not due until the clock advances`() {
        val result = SpacedRepetitionAlgorithm.calculateNext(
            quality = 4,
            previousRepetitions = 0,
            previousEaseFactor = 2.5,
            previousIntervalDays = 0,
            clock = clock
        )
        val card = progress(42, result.nextReviewTimestamp)
        assertEquals(
            "the card scheduled by the algorithm must not be due immediately",
            0,
            SpacedRepetitionAlgorithm.dueCount(listOf(card), clock)
        )
        val oneDayLater = FakeClock(now + 24L * 60L * 60L * 1000L)
        assertEquals(
            "it becomes due once the interval elapsed",
            1,
            SpacedRepetitionAlgorithm.dueCount(listOf(card), oneDayLater)
        )
    }
}
