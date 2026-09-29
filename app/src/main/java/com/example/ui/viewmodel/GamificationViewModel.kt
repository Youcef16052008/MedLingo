package com.example.ui.viewmodel

import com.example.data.local.entity.UserStatsEntity
import com.example.data.repository.MedLinguaRepository
import com.example.domain.gamification.GemsManager
import com.example.domain.gamification.HeartsManager

class GamificationViewModel(private val repository: MedLinguaRepository) {
    suspend fun onWrongAnswer(): Boolean {
        return repository.loseHeart()
    }

    suspend fun refillHeartsWithGems(): Boolean {
        return repository.refillHeartsWithGems()
    }

    suspend fun earnHeartFromPractice(): Boolean {
        return repository.earnHeartFromPractice()
    }

    suspend fun purchaseSuper(months: Int = 1) {
        repository.purchaseSuper(months)
    }

    fun checkCanDoLesson(stats: UserStatsEntity): Boolean {
        return HeartsManager.canDoLesson(stats)
    }

    fun showGemsEarned(amount: Int): Int {
        return amount
    }
}