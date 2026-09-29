package com.example.ui.viewmodel

import com.example.data.local.entity.LeagueCohortEntity
import com.example.data.local.entity.LeagueMemberEntity
import com.example.data.local.entity.UserStatsEntity
import com.example.data.repository.MedLinguaRepository

class LeagueViewModel(private val repository: MedLinguaRepository) {
    suspend fun refreshLeagueIfNeeded() {
        repository.refreshLeagueCohortIfNeeded()
    }
}