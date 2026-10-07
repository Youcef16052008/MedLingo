package com.example.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.ui.viewmodel.MedLinguaViewModel

/** Réarme les rappels après un redémarrage : les alarmes d'AlarmManager ne
 *  survivent pas au reboot. Respecte les interrupteurs persistés de l'utilisateur. */
class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return

        val prefs = context.getSharedPreferences(
            NotificationHelper.PREFS_REMINDERS,
            Context.MODE_PRIVATE
        )
        if (prefs.getBoolean(NotificationHelper.KEY_DAILY_REMINDER_ENABLED, true)) {
            NotificationHelper.scheduleDailyReminder(context, 20, 0)
        }
        if (prefs.getBoolean(NotificationHelper.KEY_STREAK_REMINDER_ENABLED, true)) {
            NotificationHelper.scheduleRepeatingReminder(
                context,
                NotificationReceiver.ACTION_STREAK_CHECK,
                MedLinguaViewModel.REQUEST_CODE_STREAK,
                MedLinguaViewModel.STREAK_REMINDER_HOUR,
                0
            )
        }
        if (prefs.getBoolean(NotificationHelper.KEY_PEARL_REMINDER_ENABLED, true)) {
            NotificationHelper.scheduleRepeatingReminder(
                context,
                NotificationReceiver.ACTION_CLINICAL_PEARL,
                MedLinguaViewModel.REQUEST_CODE_PEARL,
                MedLinguaViewModel.PEARL_REMINDER_HOUR,
                0
            )
        }
    }
}
