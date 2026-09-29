package com.example

import android.app.Application
import com.example.data.local.database.MedLinguaDatabase
import com.example.data.repository.MedLinguaRepository
import com.example.service.TtsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MedLinguaApp : Application() {
    val database by lazy { MedLinguaDatabase.getDatabase(this) }
    val repository by lazy { MedLinguaRepository(database.medicalDao()) }
    val ttsManager by lazy { TtsManager(this) }

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        com.example.service.NotificationHelper.createNotificationChannels(this)
        com.example.service.NotificationHelper.scheduleDailyReminder(this, 20, 0)
        applicationScope.launch {
            repository.initializeDatabaseIfEmpty()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        ttsManager.shutdown()
    }
}
