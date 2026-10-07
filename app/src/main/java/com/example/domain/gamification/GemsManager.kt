package com.example.domain.gamification

import com.example.data.local.entity.GemsTransactionEntity
import com.example.data.local.entity.UserStatsEntity

/**
 * GemsManager - Duolingo Gems Economy
 * Ledger append-only, balance dérivée, anti-cheat
 *
 * Earn:
 * - Lesson complete: 10 gems
 * - Perfect lesson (100%): 20 gems (10 + 10 bonus)
 * - Streak 7 days: 50 gems
 * - League promotion: 100 gems
 *
 * Spend:
 * - Streak freeze: 200 gems
 * - Super MedLingo: 500 gems / mois ou 500 DA BaridiMob
 * (Le refill de cœurs n'existe plus : il n'y a plus de cœurs.)
 */
object GemsManager {
    const val GEMS_LESSON_COMPLETE = 10
    const val GEMS_PERFECT_LESSON_BONUS = 10
    const val GEMS_STREAK_7_DAYS = 50
    const val GEMS_LEAGUE_PROMOTION = 100
    const val GEMS_STREAK_FREEZE = 200
    const val GEMS_SUPER_MONTHLY = 500

    /**
     * Single construction path for the gems ledger (type, signed amount and resulting
     * balance are computed here, callers only state the facts).
     *
     * [timestamp] is mandatory: it used to default to System.currentTimeMillis(), which
     * hid the time source inside the domain and made the ledger untestable.
     */
    fun createEarnTransaction(
        amount: Int,
        reason: String,
        currentBalance: Int,
        metadata: String = "",
        timestamp: Long
    ): GemsTransactionEntity {
        return GemsTransactionEntity(
            type = "EARN",
            amount = amount,
            reason = reason,
            timestamp = timestamp,
            balanceAfter = currentBalance + amount,
            metadata = metadata
        )
    }

    /**
     * Spend transaction, or null when the balance is too low (the caller must not
     * silently spend what the user does not have).
     */
    fun createSpendTransaction(
        amount: Int,
        reason: String,
        currentBalance: Int,
        metadata: String = "",
        timestamp: Long
    ): GemsTransactionEntity? {
        if (currentBalance < amount) return null // pas assez de gems
        return GemsTransactionEntity(
            type = "SPEND",
            amount = -amount,
            reason = reason,
            timestamp = timestamp,
            balanceAfter = currentBalance - amount,
            metadata = metadata
        )
    }

    /**
     * Gems for one completed lesson.
     *
     * "Perfect" is derived from the answers themselves (correct == total), it is not a
     * caller-provided flag: the quiz and the level-completion paths used to disagree
     * (correctAnswers == total vs scorePercentage == 100), which made the perfect
     * bonus an exploitable input.
     */
    fun calculateGemsForLesson(
        correctAnswers: Int,
        totalQuestions: Int
    ): Int {
        if (totalQuestions == 0) return 0
        val accuracy = correctAnswers.toDouble() / totalQuestions
        // Si accuracy < 50%, pas de gems (évite farming)
        if (accuracy < 0.5) return 0
        val isPerfect = correctAnswers == totalQuestions
        return GEMS_LESSON_COMPLETE + if (isPerfect) GEMS_PERFECT_LESSON_BONUS else 0
    }

    /**
     * Super MedLingo pricing - Tinder Plus model
     * 500 DA / mois = 500 gems
     */
    fun getSuperPricing(): SuperPricing {
        return SuperPricing(
            monthlyDa = 500,
            monthlyGems = GEMS_SUPER_MONTHLY,
            yearlyDa = 4000, // 33% discount vs monthly: 333 DA/mois
            yearlyGems = 5000,
            benefits = listOf(
                "Leçons illimitées 🎓",
                "Sans pubs 🚫",
                "Mode hors-ligne complet 📴",
                "Streak freeze inclus 🧊",
                "Roleplay IA Doctor-Patient 🤖",
                "Leagues boost XP x2 🚀"
            )
        )
    }

    data class SuperPricing(
        val monthlyDa: Int,
        val monthlyGems: Int,
        val yearlyDa: Int,
        val yearlyGems: Int,
        val benefits: List<String>
    )
}
