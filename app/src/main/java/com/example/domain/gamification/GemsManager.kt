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
 * - Heart refill: 50 gems
 * - Streak freeze: 200 gems
 * - Super MedLingo: 500 gems / mois ou 500 DA BaridiMob
 */
object GemsManager {
    const val GEMS_LESSON_COMPLETE = 10
    const val GEMS_PERFECT_LESSON_BONUS = 10
    const val GEMS_STREAK_7_DAYS = 50
    const val GEMS_LEAGUE_PROMOTION = 100
    const val GEMS_HEART_REFILL = 50
    const val GEMS_STREAK_FREEZE = 200
    const val GEMS_SUPER_MONTHLY = 500

    fun createEarnTransaction(
        amount: Int,
        reason: String,
        currentBalance: Int,
        metadata: String = ""
    ): GemsTransactionEntity {
        return GemsTransactionEntity(
            type = "EARN",
            amount = amount,
            reason = reason,
            balanceAfter = currentBalance + amount,
            metadata = metadata
        )
    }

    fun createSpendTransaction(
        amount: Int,
        reason: String,
        currentBalance: Int,
        metadata: String = ""
    ): GemsTransactionEntity? {
        if (currentBalance < amount) return null // pas assez de gems
        return GemsTransactionEntity(
            type = "SPEND",
            amount = -amount,
            reason = reason,
            balanceAfter = currentBalance - amount,
            metadata = metadata
        )
    }

    fun calculateGemsForLesson(
        correctAnswers: Int,
        totalQuestions: Int,
        isPerfect: Boolean
    ): Int {
        if (totalQuestions == 0) return 0
        val base = GEMS_LESSON_COMPLETE
        val bonus = if (isPerfect) GEMS_PERFECT_LESSON_BONUS else 0
        // Si accuracy < 50%, pas de gems (évite farming)
        val accuracy = correctAnswers.toDouble() / totalQuestions
        return if (accuracy < 0.5) 0 else base + bonus
    }

    fun canAfford(currentGems: Int, cost: Int): Boolean = currentGems >= cost

    /**
     * Super MedLingo pricing - Tinder Plus model
     * 500 DA / mois = 500 gems
     */
    fun getSuperPricing(): SuperPricing {
        return SuperPricing(
            monthlyDa = 500,
            monthlyGems = GEMS_SUPER_MONTHLY,
            yearlyDa = 4000, // 20% discount: 333 DA/mois
            yearlyGems = 5000,
            benefits = listOf(
                "Cœurs illimités ❤️",
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
