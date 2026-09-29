package com.example.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_DAILY_REVIEW = "com.example.medlingua.ACTION_DAILY_REVIEW"
        const val ACTION_CLINICAL_PEARL = "com.example.medlingua.ACTION_CLINICAL_PEARL"
        const val ACTION_STREAK_CHECK = "com.example.medlingua.ACTION_STREAK_CHECK"
    }

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            ACTION_DAILY_REVIEW -> {
                NotificationHelper.showReviewReminder(context)
            }
            ACTION_CLINICAL_PEARL -> {
                NotificationHelper.showClinicalPearlNotification(context)
            }
            ACTION_STREAK_CHECK -> {
                NotificationHelper.showStreakReminder(context)
            }
            else -> {
                NotificationHelper.showReviewReminder(context)
            }
        }
    }
}
