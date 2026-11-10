package com.aistudio.medlingua.dzmed

import android.app.Application
import com.aistudio.medlingua.dzmed.di.AppModule
import com.aistudio.medlingua.dzmed.data.local.database.MedLinguaDatabase
import com.aistudio.medlingua.dzmed.data.repository.MedLinguaRepository
import com.aistudio.medlingua.dzmed.service.TtsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MedLinguaApp : Application() {
    val database by lazy { AppModule.provideDatabase(this) }
    val repository by lazy { AppModule.provideRepository(database.medicalDao()) }
    val ttsManager by lazy { AppModule.provideTtsManager(this) }

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        // Effets sonores de gamification (bonne/mauvaise réponse, palier, série).
        // Sans cet appel, SoundManager restait `initialized = false` et `play()`
        // ne faisait rien : l'app était muette en toutes circonstances.
        com.aistudio.medlingua.dzmed.domain.gamification.audio.SoundManager.init(this)
        // R-14 (2026-11-07): no runBlocking on the main thread. Channel
        // creation + reminder scheduling need the persisted language, so they
        // run in the IO scope and read it asynchronously (channels are created
        // a few ms later, before any notification is scheduled).
        applicationScope.launch {
            val lang = try {
                com.aistudio.medlingua.dzmed.localization.Language.fromCode(
                    database.medicalDao().getLanguageCodeBlocking() ?: "fr"
                )
            } catch (e: Exception) {
                com.aistudio.medlingua.dzmed.localization.Language.FRENCH
            }
            com.aistudio.medlingua.dzmed.service.NotificationHelper.createNotificationChannels(this@MedLinguaApp, lang)

            // All reminder toggles default to ON, but the user's OFF choice is
            // persisted: only arm alarms that are still enabled.
            val reminderPrefs = getSharedPreferences(
                com.aistudio.medlingua.dzmed.service.NotificationHelper.PREFS_REMINDERS,
                MODE_PRIVATE
            )
            val reminderHour = reminderPrefs.getInt(com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_REMINDER_HOUR, 20)
            val reminderMinute = reminderPrefs.getInt(com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_REMINDER_MINUTE, 0)

            if (reminderPrefs.getBoolean(
                    com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_DAILY_REMINDER_ENABLED, true
                )
            ) {
                com.aistudio.medlingua.dzmed.service.NotificationHelper.scheduleDailyReminder(this@MedLinguaApp, reminderHour, reminderMinute)
            }
            if (reminderPrefs.getBoolean(
                    com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_STREAK_REMINDER_ENABLED, true
                )
            ) {
                com.aistudio.medlingua.dzmed.service.NotificationHelper.scheduleRepeatingReminder(
                    this@MedLinguaApp,
                    com.aistudio.medlingua.dzmed.service.NotificationReceiver.ACTION_STREAK_CHECK,
                    com.aistudio.medlingua.dzmed.ui.viewmodel.MedLinguaViewModel.REQUEST_CODE_STREAK,
                    com.aistudio.medlingua.dzmed.ui.viewmodel.MedLinguaViewModel.STREAK_REMINDER_HOUR,
                    0
                )
            }
            if (reminderPrefs.getBoolean(
                    com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_PEARL_REMINDER_ENABLED, true
                )
            ) {
                com.aistudio.medlingua.dzmed.service.NotificationHelper.scheduleRepeatingReminder(
                    this@MedLinguaApp,
                    com.aistudio.medlingua.dzmed.service.NotificationReceiver.ACTION_CLINICAL_PEARL,
                    com.aistudio.medlingua.dzmed.ui.viewmodel.MedLinguaViewModel.REQUEST_CODE_PEARL,
                    com.aistudio.medlingua.dzmed.ui.viewmodel.MedLinguaViewModel.PEARL_REMINDER_HOUR,
                    0
                )
            }

            // Seed only on a truly empty database: re-running the full seed on
            // every launch would reset the user's module-download state.
            if (repository.isDatabaseEmpty()) {
                repository.initializeDatabaseIfEmpty()
            }
            // Exercices en masse (3 par terme - 4 504 = 13 512) : garde sur le
            // compte, donc paye une seule fois, au premier demarrage apres la
            // mise a jour. En cas d'echec, l'app reste jouable via la generation
            // en ligne de `LessonGenerator.lessonQuestions`.
            repository.seedBulkExercisesIfMissing()
        }
    }
    // TTS is released in MainActivity.onDestroy (onTerminate never runs on devices)
}
