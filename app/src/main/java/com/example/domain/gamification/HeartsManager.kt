package com.example.domain.gamification

import com.example.data.local.entity.UserStatsEntity
import kotlin.math.floor
import kotlin.math.min

/**
 * HeartsManager - Duolingo Hearts System 100% compliant
 * Basé sur Clonemrr + Duolingo Engineering Blog
 *
 * Règles Duolingo:
 * - 5 hearts max
 * - 1 heart perdu par mauvaise réponse
 * - Regen 1 heart per 2 heures lazy (pas de cron, calcul à la volée)
 * - Super Duolingo = unlimited hearts
 * - Peut acheter refill avec gems (50 gems = refill full) ou 500 DA Super
 */
object HeartsManager {
    const val MAX_HEARTS = 5
    const val HEART_REGEN_HOURS = 2
    const val HEART_REGEN_MILLIS = 2 * 60 * 60 * 1000L
    const val GEMS_COST_REFILL = 50

    /**
     * Calcul lazy hearts comme Duolingo - pas de cron job
     * hearts = min(5, stored + floor((now - heartsUpdatedAt)/2h))
     */
    fun getCurrentHearts(stats: UserStatsEntity): Int {
        if (stats.isSuperActive()) return MAX_HEARTS
        if (stats.hearts >= MAX_HEARTS) return MAX_HEARTS

        val elapsedMillis = System.currentTimeMillis() - stats.heartsUpdatedAt
        val elapsedHours = elapsedMillis / (1000.0 * 60 * 60)
        val regenCount = floor(elapsedHours / HEART_REGEN_HOURS).toInt()
        return min(MAX_HEARTS, stats.hearts + regenCount)
    }

    /**
     * Calcule le nouveau heartsUpdatedAt après regen partiel
     * Pour éviter de donner trop de hearts si on appelle plusieurs fois
     */
    fun getEffectiveHeartsUpdatedAt(stats: UserStatsEntity): Long {
        if (stats.hearts >= MAX_HEARTS) return stats.heartsUpdatedAt
        val current = getCurrentHearts(stats)
        if (current >= MAX_HEARTS) return System.currentTimeMillis()

        val elapsedMillis = System.currentTimeMillis() - stats.heartsUpdatedAt
        val regenCount = floor(elapsedMillis / (1000.0 * 60 * 60 * HEART_REGEN_HOURS)).toInt()
        return stats.heartsUpdatedAt + (regenCount * HEART_REGEN_MILLIS)
    }

    fun canDoLesson(stats: UserStatsEntity): Boolean {
        if (stats.isSuperActive()) return true
        return getCurrentHearts(stats) > 0
    }

    fun timeUntilNextHeartMillis(stats: UserStatsEntity): Long {
        if (stats.isSuperActive()) return 0
        if (getCurrentHearts(stats) >= MAX_HEARTS) return 0
        val elapsed = System.currentTimeMillis() - stats.heartsUpdatedAt
        val timeInCurrentCycle = elapsed % HEART_REGEN_MILLIS
        return HEART_REGEN_MILLIS - timeInCurrentCycle
    }

    fun formatTimeUntilNextHeart(millis: Long): String {
        if (millis <= 0) return "Maintenant"
        val totalMinutes = millis / (1000 * 60)
        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60
        return if (hours > 0) "${hours}h ${minutes}min" else "${minutes}min"
    }

    /**
     * Lose 1 heart on wrong answer - server-authoritative
     */
    fun loseHeart(stats: UserStatsEntity): UserStatsEntity {
        if (stats.isSuperActive()) return stats // Super = no heart loss

        val currentHearts = getCurrentHearts(stats)
        val effectiveUpdatedAt = getEffectiveHeartsUpdatedAt(stats)

        // Si on était à max hearts, on reset le timer de regen
        val newUpdatedAt = if (currentHearts >= MAX_HEARTS) {
            System.currentTimeMillis()
        } else {
            effectiveUpdatedAt
        }

        return stats.copy(
            hearts = (currentHearts - 1).coerceAtLeast(0),
            heartsUpdatedAt = newUpdatedAt
        )
    }

    /**
     * Refill hearts avec gems - 50 gems = full refill
     */
    fun refillHeartsWithGems(stats: UserStatsEntity): Pair<UserStatsEntity, Boolean> {
        if (stats.gems < GEMS_COST_REFILL) return Pair(stats, false)
        if (getCurrentHearts(stats) >= MAX_HEARTS) return Pair(stats, false)

        return Pair(
            stats.copy(
                hearts = MAX_HEARTS,
                heartsUpdatedAt = System.currentTimeMillis(),
                gems = stats.gems - GEMS_COST_REFILL
            ),
            true
        )
    }

    /**
     * Refill hearts via Super purchase - comme Tinder Plus model
     */
    fun refillHeartsWithSuper(stats: UserStatsEntity): UserStatsEntity {
        return stats.copy(
            hearts = MAX_HEARTS,
            heartsUpdatedAt = System.currentTimeMillis()
        )
    }

    /**
     * Practice to earn hearts - Duolingo feature: faire une leçon practice pour gagner 1 heart
     */
    fun earnHeartFromPractice(stats: UserStatsEntity): UserStatsEntity {
        val current = getCurrentHearts(stats)
        if (current >= MAX_HEARTS) return stats
        return stats.copy(
            hearts = (current + 1).coerceAtMost(MAX_HEARTS),
            heartsUpdatedAt = getEffectiveHeartsUpdatedAt(stats)
        )
    }
}
