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

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        // PHASE 3 #11 : les noms de canaux (visibles dans les réglages Android)
        // suivent la langue de l'app. Android ne permet de changer le nom d'un
        // canal existant qu'au redémarrage, d'où la lecture synchrone ici —
        // après un changement de langue, il faut relancer l'app.
        val lang = try {
            com.aistudio.medlingua.dzmed.localization.Language.fromCode(
                database.medicalDao().getLanguageCodeBlocking() ?: "fr"
            )
        } catch (e: Exception) {
            com.aistudio.medlingua.dzmed.localization.Language.FRENCH
        }
        com.aistudio.medlingua.dzmed.service.NotificationHelper.createNotificationChannels(this, lang)
        // All reminder toggles default to ON, but the user's OFF choice is
        // persisted: only arm alarms that are still enabled.
        val reminderPrefs = getSharedPreferences(
            com.aistudio.medlingua.dzmed.service.NotificationHelper.PREFS_REMINDERS,
            MODE_PRIVATE
        )
        if (reminderPrefs.getBoolean(
                com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_DAILY_REMINDER_ENABLED, true
            )
        ) {
            com.aistudio.medlingua.dzmed.service.NotificationHelper.scheduleDailyReminder(this, 20, 0)
        }
        if (reminderPrefs.getBoolean(
                com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_STREAK_REMINDER_ENABLED, true
            )
        ) {
            com.aistudio.medlingua.dzmed.service.NotificationHelper.scheduleRepeatingReminder(
                this,
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
                this,
                com.aistudio.medlingua.dzmed.service.NotificationReceiver.ACTION_CLINICAL_PEARL,
                com.aistudio.medlingua.dzmed.ui.viewmodel.MedLinguaViewModel.REQUEST_CODE_PEARL,
                com.aistudio.medlingua.dzmed.ui.viewmodel.MedLinguaViewModel.PEARL_REMINDER_HOUR,
                0
            )
        }
        applicationScope.launch {
            // Seed only on a truly empty database: re-running the full seed on
            // every launch would reset the user's module-download state.
            if (repository.isDatabaseEmpty()) {
                repository.initializeDatabaseIfEmpty()
            }
            // Exercices en masse (3 par terme × 4 504 = 13 512) : garde sur le
            // compte, donc payé une seule fois, au premier démarrage après la
            // mise à jour. En cas d'échec, l'app reste jouable via la génération
            // en ligne de `LessonGenerator.lessonQuestions`.
            repository.seedBulkExercisesIfMissing()
        }
    }
    // TTS is released in MainActivity.onDestroy (onTerminate never runs on devices)
}
