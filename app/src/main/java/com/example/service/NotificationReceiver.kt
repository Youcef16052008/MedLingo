package com.example.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.MedLinguaApp
import com.example.domain.sm2.SpacedRepetitionAlgorithm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class NotificationReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_DAILY_REVIEW = "com.example.medlingua.ACTION_DAILY_REVIEW"
        const val ACTION_CLINICAL_PEARL = "com.example.medlingua.ACTION_CLINICAL_PEARL"
        const val ACTION_STREAK_CHECK = "com.example.medlingua.ACTION_STREAK_CHECK"
    }

    override fun onReceive(context: Context, intent: Intent) {
        // PHASE 3 #11 : la langue de la notification est celle persistée en base
        // (l'alarme peut se déclencher alors que l'app est fermée depuis des
        // jours — on ne peut pas lire l'UiState).
        val lang = persistedLanguage(context)

        when (intent.action) {
            ACTION_DAILY_REVIEW -> {
                val app = context.applicationContext as? MedLinguaApp
                if (app == null) {
                    NotificationHelper.showReviewReminder(context, lang = lang)
                    return
                }
                // Annonce le vrai nombre de cartes dues, pas une valeur fixe.
                val pending = goAsync()
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val due = app.repository.allFlashcardProgress.first().count { progress ->
                            SpacedRepetitionAlgorithm.isDue(progress.nextReviewTimestamp, app.repository.currentClock)
                        }
                        NotificationHelper.showReviewReminder(context, dueCount = due, lang = lang)
                    } finally {
                        pending.finish()
                    }
                }
            }
            ACTION_CLINICAL_PEARL -> {
                NotificationHelper.showClinicalPearlNotification(context, lang = lang)
            }
            ACTION_STREAK_CHECK -> {
                val app = context.applicationContext as? MedLinguaApp
                if (app == null) {
                    NotificationHelper.showStreakReminder(context, lang = lang)
                    return
                }
                // Annonce la vraie série, pas une valeur fixe.
                val pending = goAsync()
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val streak = app.repository.userStats.first()?.streakDays ?: 0
                        NotificationHelper.showStreakReminder(context, streakDays = streak, lang = lang)
                    } finally {
                        pending.finish()
                    }
                }
            }
            else -> {
                NotificationHelper.showReviewReminder(context, lang = lang)
            }
        }
    }

    /**
     * Langue persistée pour les notifications hors UI. Lecture synchrone de la
     * base (Room `SELECT` simple, ~1 ms) — le receiver est déjà en IO pour les
     * alarmes qui lisent les données ; pour le fallback (`app == null`) la base
     * est en train d'être créée, le français par défaut reste correct.
     */
    private fun persistedLanguage(context: Context): com.example.localization.Language {
        return try {
            val app = context.applicationContext as? MedLinguaApp
            com.example.localization.Language.fromCode(
                app?.database?.medicalDao()?.getLanguageCodeBlocking() ?: "fr"
            )
        } catch (e: Exception) {
            com.example.localization.Language.FRENCH
        }
    }
}
