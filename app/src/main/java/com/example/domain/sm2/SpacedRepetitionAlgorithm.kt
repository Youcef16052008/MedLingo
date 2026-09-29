package com.example.domain.sm2

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
     */
    fun calculateNext(
        quality: Int,
        previousRepetitions: Int,
        previousEaseFactor: Double,
        previousIntervalDays: Int
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

        val now = System.currentTimeMillis()
        val nextReviewMillis = now + (newInterval.toLong() * 24L * 60L * 60L * 1000L)

        return ReviewResult(
            repetitions = newRepetitions,
            easeFactor = newEaseFactor,
            intervalDays = newInterval,
            nextReviewTimestamp = nextReviewMillis
        )
    }
}
