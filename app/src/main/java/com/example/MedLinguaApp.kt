package com.example

import android.app.Application
import com.example.di.AppModule
import com.example.data.local.database.MedLinguaDatabase
import com.example.data.repository.MedLinguaRepository
import com.example.service.TtsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MedLinguaApp : Application() {
    val database by lazy { AppModule.provideDatabase(this) }
    val repository by lazy { AppModule.provideRepository(database.medicalDao()) }
    val ttsManager by lazy { AppModule.provideTtsManager(this) }

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        com.example.service.NotificationHelper.createNotificationChannels(this)
        // All reminder toggles default to ON, but the user's OFF choice is
        // persisted: only arm alarms that are still enabled.
        val reminderPrefs = getSharedPreferences(
            com.example.service.NotificationHelper.PREFS_REMINDERS,
            MODE_PRIVATE
        )
        if (reminderPrefs.getBoolean(
                com.example.service.NotificationHelper.KEY_DAILY_REMINDER_ENABLED, true
            )
        ) {
            com.example.service.NotificationHelper.scheduleDailyReminder(this, 20, 0)
        }
        if (reminderPrefs.getBoolean(
                com.example.service.NotificationHelper.KEY_STREAK_REMINDER_ENABLED, true
            )
        ) {
            com.example.service.NotificationHelper.scheduleRepeatingReminder(
                this,
                com.example.service.NotificationReceiver.ACTION_STREAK_CHECK,
                com.example.ui.viewmodel.MedLinguaViewModel.REQUEST_CODE_STREAK,
                com.example.ui.viewmodel.MedLinguaViewModel.STREAK_REMINDER_HOUR,
                0
            )
        }
        if (reminderPrefs.getBoolean(
                com.example.service.NotificationHelper.KEY_PEARL_REMINDER_ENABLED, true
            )
        ) {
            com.example.service.NotificationHelper.scheduleRepeatingReminder(
                this,
                com.example.service.NotificationReceiver.ACTION_CLINICAL_PEARL,
                com.example.ui.viewmodel.MedLinguaViewModel.REQUEST_CODE_PEARL,
                com.example.ui.viewmodel.MedLinguaViewModel.PEARL_REMINDER_HOUR,
                0
            )
        }
        applicationScope.launch {
            // Seed only on a truly empty database: re-running the full seed on
            // every launch would reset the user's module-download state.
            if (repository.isDatabaseEmpty()) {
                repository.initializeDatabaseIfEmpty()
            }
        }
    }
    // TTS is released in MainActivity.onDestroy (onTerminate never runs on devices)
}
