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
        when (intent.action) {
            ACTION_DAILY_REVIEW -> {
                val app = context.applicationContext as? MedLinguaApp
                if (app == null) {
                    NotificationHelper.showReviewReminder(context)
                    return
                }
                // Annonce le vrai nombre de cartes dues, pas une valeur fixe.
                val pending = goAsync()
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val due = app.repository.allFlashcardProgress.first().count { progress ->
                            SpacedRepetitionAlgorithm.isDue(progress.nextReviewTimestamp, app.repository.currentClock)
                        }
                        NotificationHelper.showReviewReminder(context, dueCount = due)
                    } finally {
                        pending.finish()
                    }
                }
            }
            ACTION_CLINICAL_PEARL -> {
                NotificationHelper.showClinicalPearlNotification(context)
            }
            ACTION_STREAK_CHECK -> {
                val app = context.applicationContext as? MedLinguaApp
                if (app == null) {
                    NotificationHelper.showStreakReminder(context)
                    return
                }
                // Annonce la vraie série, pas une valeur fixe.
                val pending = goAsync()
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val streak = app.repository.userStats.first()?.streakDays ?: 0
                        NotificationHelper.showStreakReminder(context, streakDays = streak)
                    } finally {
                        pending.finish()
                    }
                }
            }
            else -> {
                NotificationHelper.showReviewReminder(context)
            }
        }
    }
}
