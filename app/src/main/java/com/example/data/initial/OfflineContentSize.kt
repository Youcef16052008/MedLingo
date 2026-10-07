package com.example.data.initial

import kotlin.math.max
import kotlin.math.round

/**
 * Offline payload estimate of a module.
 *
 * The per-module sizes used to be hardcoded literals (14.2 MB for Anatomy, 7.2 MB for
 * Clinical English, …) that had nothing to do with the content actually shipped: the app
 * bundles every module in the database, so the "cache size" shown to the user was
 * fiction that never moved.
 *
 * This derives the estimate from the real seed content instead:
 *  - a term carries EN + FR + AR text, a clinical pearl and an illustration reference;
 *  - an exercise carries three prompts, its options and its explanations.
 *
 * It stays an ESTIMATE (nothing measures the database file), but it now moves with the
 * content: adding terms or exercises changes the number the UI displays.
 */
object OfflineContentSize {
    // A term ships 3 languages + a clinical pearl + an illustration reference;
    // an exercise ships 3 prompts, its options and 3 explanations.
    private const val BYTES_PER_TERM = 1_200L
    private const val BYTES_PER_EXERCISE = 1_500L
    private const val MODULE_OVERHEAD_BYTES = 24_000L
    private const val BYTES_PER_MB = 1024.0 * 1024.0

    /**
     * Rounded to 4 decimals: at 2 decimals every module collapsed to the same
     * 0.0x MB value, which made the per-module figures indistinguishable, and at 3
     * decimals two genuinely different contents could still collide (300 bytes is the
     * smallest possible difference, 0.000286 MB, so 1e-4 is the finest rounding that
     * keeps `estimate` injective).
     */
    fun estimate(termsCount: Int, exercisesCount: Int): Double {
        val bytes = MODULE_OVERHEAD_BYTES +
            termsCount.coerceAtLeast(0) * BYTES_PER_TERM +
            exercisesCount.coerceAtLeast(0) * BYTES_PER_EXERCISE
        return round(max(bytes / BYTES_PER_MB, 0.01) * 10_000.0) / 10_000.0
    }

    fun forModule(moduleTitle: String): Double = estimate(
        termsCount = InitialData.termsOfModule(moduleTitle).size,
        exercisesCount = InitialData.exercises.count {
            it.module.equals(moduleTitle, ignoreCase = true)
        }
    )
}
