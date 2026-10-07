package com.example.domain.gamification

import com.example.data.local.entity.UserStatsEntity

/** Type d'une récompense de caisse. */
enum class ChestKind { XP, GEMS, FREEZE }

/** Récompense tirée d'une caisse. */
data class ChestReward(val kind: ChestKind, val amount: Int)

/** Ligne de la table de tirage (poids en %, total 100). */
data class ChestRow(val kind: ChestKind, val amount: Int, val weight: Int)

/** Session de fin de leçon susceptible de gagner une caisse. */
enum class ChestSource { QUIZ, FLASH }

/**
 * Caisse de récompense : tirage pondéré, `rand` injecté → testable.
 * Gagnée à la fin d'un quiz (toujours) ou d'une révision (≥ 10 cartes).
 */
object ChestManager {

    val CHEST_TABLE: List<ChestRow> = listOf(
        ChestRow(ChestKind.XP, 20, 30),
        ChestRow(ChestKind.GEMS, 30, 30),
        ChestRow(ChestKind.GEMS, 50, 20),
        ChestRow(ChestKind.FREEZE, 1, 15),
        ChestRow(ChestKind.GEMS, 100, 5)
    )

    /** Ouvre une caisse : `rand()` ∈ [0,1) pondère la table. */
    fun openChest(rand: () -> Double): ChestReward {
        val total = CHEST_TABLE.sumOf { it.weight }
        val roll = rand() * total
        var acc = 0
        for (row in CHEST_TABLE) {
            acc += row.weight
            if (roll < acc) return ChestReward(row.kind, row.amount)
        }
        val last = CHEST_TABLE.last()
        return ChestReward(last.kind, last.amount)
    }

    /**
     * Déclenche l'attribution d'une caisse en fin de session.
     * `QUIZ` → toujours ; `FLASH` → seulement si ≥ 10 cartes revues (`score`).
     */
    fun earnsChest(source: ChestSource, score: Int): Boolean =
        when (source) {
            ChestSource.QUIZ -> score >= 0
            ChestSource.FLASH -> score >= 10
        }

    /** Applique une récompense de caisse (l'XP suit les règles de [ProgressionManager]). */
    fun applyChestReward(
        stats: UserStatsEntity,
        reward: ChestReward,
        now: Long
    ): UserStatsEntity = when (reward.kind) {
        ChestKind.GEMS -> stats.copy(gems = stats.gems + reward.amount)
        ChestKind.FREEZE -> stats.copy(
            streakFreezeCount = stats.streakFreezeCount + reward.amount
        )
        ChestKind.XP -> ProgressionManager.addXp(stats, reward.amount, now).stats
    }
}
