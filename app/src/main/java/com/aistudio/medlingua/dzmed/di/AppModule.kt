package com.aistudio.medlingua.dzmed.di

import android.content.Context
import com.aistudio.medlingua.dzmed.data.local.database.MedLinguaDatabase
import com.aistudio.medlingua.dzmed.data.local.dao.MedicalDao
import com.aistudio.medlingua.dzmed.data.local.dao.ReviewQueueDao
import com.aistudio.medlingua.dzmed.data.repository.MedLinguaRepository
import com.aistudio.medlingua.dzmed.domain.time.Clock
import com.aistudio.medlingua.dzmed.domain.time.SystemClock
import com.aistudio.medlingua.dzmed.service.TtsManager

object AppModule {
    fun provideDatabase(context: Context): MedLinguaDatabase {
        return MedLinguaDatabase.getDatabase(context)
    }
    
    fun provideRepository(dao: MedicalDao, clock: Clock = SystemClock): MedLinguaRepository {
        return MedLinguaRepository(dao, clock)
    }
    
    fun provideReviewQueueDao(database: MedLinguaDatabase): ReviewQueueDao {
        return database.reviewQueueDao()
    }
    
    fun provideTtsManager(context: Context): TtsManager {
        return TtsManager(context)
    }
    
    fun provideClock(): Clock = SystemClock
}