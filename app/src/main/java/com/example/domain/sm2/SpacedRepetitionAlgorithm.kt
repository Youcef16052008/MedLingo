package com.example.domain.sm2

import com.example.domain.time.Clock
import com.example.domain.time.SystemClock

data class ReviewResult(
    val repetitions: Int,
    val easeFactor: Double,
    val intervalDays: Int,
    val nextReviewTimestamp: Long
)

object SpacedRepetitionAlgorithm {
    /**
     * SM-2 Spaced Repetition calculation
     * @param quality: 1 (Forgot), 2 (Hard), 3 (Medium), 4 (Good), 5 (Perfect)
     * @param clock injectable time source (default: system clock) so tests can
     *   assert nextReviewTimestamp without touching wall time
     */
    fun calculateNext(
        quality: Int,
        previousRepetitions: Int,
        previousEaseFactor: Double,
        previousIntervalDays: Int,
        clock: Clock = SystemClock
    ): ReviewResult {
        var newEaseFactor = previousEaseFactor
        val newInterval: Int
        val newRepetitions: Int

        if (quality < 3) {
            // Failed recall: reset repetitions and schedule for tomorrow (1 day)
            newRepetitions = 0
            newInterval = 1
        } else {
            // Successful recall
            newRepetitions = previousRepetitions + 1
            newInterval = when (newRepetitions) {
                1 -> 1
                2 -> 3
                3 -> 7
                else -> {
                    val calc = (previousIntervalDays * previousEaseFactor).toInt()
                    if (calc <= previousIntervalDays) previousIntervalDays + 1 else calc
                }
            }
        }

        // Adjust Ease Factor using SM-2 formula:
        // EF' = EF + (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02))
        val qDiff = 5 - quality
        newEaseFactor = previousEaseFactor + (0.1 - qDiff * (0.08 + qDiff * 0.02))
        if (newEaseFactor < 1.3) newEaseFactor = 1.3

        val now = clock.now()
        val nextReviewMillis = now + (newInterval.toLong() * 24L * 60L * 60L * 1000L)

        return ReviewResult(
            repetitions = newRepetitions,
            easeFactor = newEaseFactor,
            intervalDays = newInterval,
            nextReviewTimestamp = nextReviewMillis
        )
    }

    /**
     * A card is due when its scheduled date has been reached (inclusive).
     *
     * Callers MUST pass the same [Clock] they used to schedule the cards: the UI used to
     * read System.currentTimeMillis() here while the schedule was computed with an
     * injected clock, so every card looked due in tests and the badge could not be
     * reasoned about.
     */
    fun isDue(nextReviewTimestamp: Long, clock: Clock = SystemClock): Boolean =
        nextReviewTimestamp <= clock.now()

    /** Number of cards ready for review, on the injected clock. */
    fun dueCount(
        progress: List<com.example.data.local.entity.FlashcardProgressEntity>,
        clock: Clock = SystemClock
    ): Int = progress.count { isDue(it.nextReviewTimestamp, clock) }
}
