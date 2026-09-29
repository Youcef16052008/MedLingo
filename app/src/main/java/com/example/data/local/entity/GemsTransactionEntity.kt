package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Gems Ledger append-only - comme Duolingo gems_ledger
 * Balance dérivée, jamais mutable directement (anti-cheat)
 * Inspiration: Clonemrr + Duolingo Gamification Service
 */
@Entity(
    tableName = "gems_transactions",
    indices = [
        Index("userId"),
        Index("timestamp")
    ]
)
data class GemsTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Int = 1,
    val type: String,
    val amount: Int,
    val reason: String,
    val timestamp: Long = 0L,
    val balanceAfter: Int = 0,
    val metadata: String = ""
)

enum class GemsTransactionType(val value: String) {
    EARN("EARN"),
    SPEND("SPEND"),
    PURCHASE("PURCHASE"),
    REFILL_HEARTS("REFILL_HEARTS"),
    STREAK_FREEZE("STREAK_FREEZE"),
    LEAGUE_REWARD("LEAGUE_REWARD")
}
