package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * League Cohort - comme Duolingo Redis Sorted Sets league:{week}:{cohort}
 * 30 users par cohorte, reset hebdo lundi 00:00 UTC
 * Tiers: Bronze, Silver, Gold, Sapphire, Ruby, Emerald, Amethyst, Pearl, Obsidian, Diamond
 */
@Entity(
    tableName = "league_cohorts",
    indices = [
        Index("tier"),
        Index("weekStartTimestamp"),
        Index("isActive")
    ]
)
data class LeagueCohortEntity(
    @PrimaryKey
    val cohortId: String, // ex: "2026-W39-bronze-a1b2"
    val weekStartTimestamp: Long, // Lundi 00:00 UTC
    val weekEndTimestamp: Long,
    val tier: String = "BRONZE", // Duolingo 10 tiers
    val isActive: Boolean = true,
    val isPromoted: Boolean = false,
    val isDemoted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

enum class LeagueTier(val value: String, val order: Int, val colorHex: Long, val icon: String) {
    BRONZE("BRONZE", 1, 0xFFCD7F32, "🥉"),
    SILVER("SILVER", 2, 0xFFC0C0C0, "🥈"),
    GOLD("GOLD", 3, 0xFFFFD700, "🥇"),
    SAPPHIRE("SAPPHIRE", 4, 0xFF0F52BA, "💎"),
    RUBY("RUBY", 5, 0xFFE0115F, "♦️"),
    EMERALD("EMERALD", 6, 0xFF50C878, "💚"),
    AMETHYST("AMETHYST", 7, 0xFF9966CC, "💜"),
    PEARL("PEARL", 8, 0xFFFDEEF4, "⚪"),
    OBSIDIAN("OBSIDIAN", 9, 0xFF3B3C36, "⚫"),
    DIAMOND("DIAMOND", 10, 0xFFB9F2FF, "🔷");

    companion object {
        fun nextTier(current: String): LeagueTier {
            val currentTier = entries.find { it.value == current } ?: BRONZE
            return entries.getOrNull(currentTier.order) ?: DIAMOND // order is 1-indexed, next is index = order
        }
        fun prevTier(current: String): LeagueTier {
            val currentTier = entries.find { it.value == current } ?: BRONZE
            return if (currentTier.order > 1) entries[currentTier.order - 2] else BRONZE
        }
    }
}
