package com.example.ui.viewmodel

import com.example.data.repository.MedLinguaRepository

/**
 * Façade gamification : il n'y a **plus de cœurs** — plus de recharge, plus de
 * « leçon bloquée ». Restent l'achat Super et les gains de gemmes.
 */
class GamificationViewModel(private val repository: MedLinguaRepository) {

    suspend fun purchaseSuper(months: Int = 1) {
        repository.purchaseSuper(months)
    }

    fun showGemsEarned(amount: Int): Int {
        return amount
    }
}
