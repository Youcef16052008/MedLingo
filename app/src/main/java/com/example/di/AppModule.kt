package com.example.di

import android.content.Context
import com.example.data.local.database.MedLinguaDatabase
import com.example.data.local.dao.MedicalDao
import com.example.data.repository.MedLinguaRepository
import com.example.domain.time.Clock
import com.example.domain.time.SystemClock
import com.example.service.TtsManager

object AppModule {
    fun provideDatabase(context: Context): MedLinguaDatabase {
        return MedLinguaDatabase.getDatabase(context)
    }
    
    fun provideRepository(dao: MedicalDao, clock: Clock = SystemClock): MedLinguaRepository {
        return MedLinguaRepository(dao, clock)
    }
    
    fun provideTtsManager(context: Context): TtsManager {
        return TtsManager(context)
    }
    
    fun provideClock(): Clock = SystemClock
}