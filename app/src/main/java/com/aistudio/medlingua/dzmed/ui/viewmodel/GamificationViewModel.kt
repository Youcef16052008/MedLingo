package com.aistudio.medlingua.dzmed.ui.viewmodel

import com.aistudio.medlingua.dzmed.data.repository.MedLinguaRepository

/**
 * Façade gamification : il n'y a **plus de cœurs** — plus de recharge, plus de
 * « leçon bloquée ». Restent l'achat Super et les gains de gemmes.
 */
class GamificationViewModel(private val repository: MedLinguaRepository) {

    /**
     * Achète Super MedLingo. Retourne `false` si le solde de gemmes est
     * insuffisant — l'appelant doit alors afficher un message à l'utilisateur
     * (acheter plus de gemmes en jouant), et non pas fermer le paywall.
     */
    suspend fun purchaseSuper(months: Int = 1): Boolean {
        return repository.purchaseSuper(months)
    }

    fun showGemsEarned(amount: Int): Int {
        return amount
    }
}
