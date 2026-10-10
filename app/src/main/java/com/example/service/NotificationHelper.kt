package com.example.service

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.MainActivity
import com.example.R
import com.example.data.initial.InitialData
import com.example.data.local.entity.MedicalTermEntity
import com.example.localization.Language
import com.example.localization.Strings
import java.util.Calendar

object NotificationHelper {

    const val CHANNEL_ID_REVIEWS = "medlingua_daily_review"
    const val CHANNEL_ID_STREAKS = "medlingua_streak"
    const val CHANNEL_ID_PEARLS = "medlingua_clinical_pearls"
    const val CHANNEL_ID_LEVELS = "medlingua_levels"

    const val NOTIFICATION_ID_REVIEW = 1001
    const val NOTIFICATION_ID_STREAK = 1002
    const val NOTIFICATION_ID_PEARL = 1003
    const val NOTIFICATION_ID_LEVEL = 1004

    const val EXTRA_TARGET_SCREEN = "extra_target_screen"
    const val TARGET_FLASHCARDS = "FLASHCARDS"
    const val TARGET_QUIZ = "QUIZ"
    const val TARGET_MODULES = "MODULES"
    const val TARGET_HOME = "HOME"

    // Persisted toggle state for the recurring reminders (survives app restarts)
    const val PREFS_REMINDERS = "medlingua_reminder_prefs"
    const val KEY_DAILY_REMINDER_ENABLED = "daily_reminder_enabled"
    const val KEY_STREAK_REMINDER_ENABLED = "streak_reminder_enabled"
    const val KEY_PEARL_REMINDER_ENABLED = "pearl_reminder_enabled"

    // P0-4 (rapport final, bug B4) : l'heure du rappel quotidien était perdue au
    // reboot — setReminderTime() ne mettait à jour que l'UiState en mémoire, et
    // BootReceiver réarmait à 20:00 en dur. Persistance ici, relue au reboot.
    const val KEY_REMINDER_HOUR = "reminder_hour"
    const val KEY_REMINDER_MINUTE = "reminder_minute"

    /**
     * Raccourci vers `Strings.get` pour les notifications (Phase 3 #11).
     * Les clés manquantes retombent sur le français, puis sur la clé elle-même.
     */
    private fun t(key: String, lang: Language): String = Strings.get(key, lang)

    fun createNotificationChannels(context: Context, lang: Language = Language.FRENCH) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // PHASE 3 #11 : les noms de canaux apparaissent dans les réglages
            // Android de l'app — ils suivent maintenant la langue de l'app.
            val reviewChannel = NotificationChannel(
                CHANNEL_ID_REVIEWS,
                t("channel_reviews_name", lang),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = t("channel_reviews_desc", lang)
                enableVibration(true)
            }

            val streakChannel = NotificationChannel(
                CHANNEL_ID_STREAKS,
                t("channel_streaks_name", lang),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = t("channel_streaks_desc", lang)
            }

            val pearlChannel = NotificationChannel(
                CHANNEL_ID_PEARLS,
                t("channel_pearls_name", lang),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = t("channel_pearls_desc", lang)
            }

            val levelChannel = NotificationChannel(
                CHANNEL_ID_LEVELS,
                t("channel_levels_name", lang),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = t("channel_levels_desc", lang)
                enableVibration(true)
            }

            notificationManager.createNotificationChannels(
                listOf(reviewChannel, streakChannel, pearlChannel, levelChannel)
            )
        }
    }

    fun hasNotificationPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            NotificationManagerCompat.from(context).areNotificationsEnabled()
        }
    }

    @SuppressLint("MissingPermission")
    fun showReviewReminder(context: Context, dueCount: Int = 6, lang: Language = Language.FRENCH) {
        if (!hasNotificationPermission(context)) return

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(EXTRA_TARGET_SCREEN, TARGET_FLASHCARDS)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // PHASE 3 #11 : la notification suit la langue de l'app. Avant, ces
        // textes étaient des chaînes FR en dur — un utilisateur arabe ou
        // anglais recevait sa notification en français.
        val n = dueCount.toString()
        val notification = NotificationCompat.Builder(context, CHANNEL_ID_REVIEWS)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(t("notif_review_title", lang).replace("{n}", n))
            .setContentText(t("notif_review_body", lang))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(t("notif_review_big", lang).replace("{n}", n))
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(
                android.R.drawable.ic_media_play,
                t("notif_review_action", lang),
                pendingIntent
            )
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_REVIEW, notification)
    }

    @SuppressLint("MissingPermission")
    fun showStreakReminder(context: Context, streakDays: Int = 12, lang: Language = Language.FRENCH) {
        if (!hasNotificationPermission(context)) return

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(EXTRA_TARGET_SCREEN, TARGET_HOME)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            2,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val n = streakDays.toString()
        val notification = NotificationCompat.Builder(context, CHANNEL_ID_STREAKS)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(t("notif_streak_title", lang).replace("{n}", n))
            .setContentText(t("notif_streak_body", lang))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(t("notif_streak_big", lang).replace("{n}", n))
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_STREAK, notification)
    }

    @SuppressLint("MissingPermission")
    fun showClinicalPearlNotification(context: Context, term: MedicalTermEntity? = null, lang: Language = Language.FRENCH) {
        if (!hasNotificationPermission(context)) return

        val selectedTerm = term ?: InitialData.terms.firstOrNull { it.clinicalPearl.isNotBlank() } ?: InitialData.terms.first()

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(EXTRA_TARGET_SCREEN, TARGET_MODULES)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            3,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Le titre et le corps long suivent la langue ; la perle elle-même
        // reste dans sa langue d'origine (donnée brute du terme).
        val title = t("notif_pearl_title", lang)
            .replace("{fr}", selectedTerm.termFr)
            .replace("{en}", selectedTerm.termEn)
        val big = t("notif_pearl_big", lang)
            .replace("{pearl}", selectedTerm.clinicalPearl)
            .replace("{mnemo}", selectedTerm.mnemonic.ifBlank { "—" })
            .replace("{module}", selectedTerm.module)
            .replace("{chapter}", selectedTerm.chapter)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_PEARLS)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(selectedTerm.clinicalPearl)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .setBigContentTitle(title)
                    .bigText(big)
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(
                android.R.drawable.ic_menu_agenda,
                t("notif_pearl_action", lang),
                pendingIntent
            )
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_PEARL, notification)
    }

    @SuppressLint("MissingPermission")
    fun showLevelUnlockedNotification(context: Context, levelNumber: Int, levelName: String, lang: Language = Language.FRENCH) {
        if (!hasNotificationPermission(context)) return

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(EXTRA_TARGET_SCREEN, TARGET_QUIZ)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            4,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val n = levelNumber.toString()
        val notification = NotificationCompat.Builder(context, CHANNEL_ID_LEVELS)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(t("notif_level_title", lang).replace("{n}", n))
            .setContentText(t("notif_level_body", lang).replace("{name}", levelName))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(
                        t("notif_level_big", lang)
                            .replace("{n}", n)
                            .replace("{name}", levelName)
                    )
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_LEVEL, notification)
    }

    fun scheduleDailyReminder(context: Context, hourOfDay: Int = 20, minute: Int = 0) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            action = NotificationReceiver.ACTION_DAILY_REVIEW
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            100,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        // setInexactRepeating never requires SCHEDULE_EXACT_ALARM: no permission check
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun cancelDailyReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            action = NotificationReceiver.ACTION_DAILY_REVIEW
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            100,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

    /**
     * Generic daily recurring reminder for an arbitrary receiver action.
     * Used by the streak & clinical pearl toggles (their actions were
     * handled by the receiver but never scheduled).
     */
    fun scheduleRepeatingReminder(
        context: Context,
        action: String,
        requestCode: Int,
        hourOfDay: Int,
        minute: Int
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            this.action = action
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun cancelRepeatingReminder(context: Context, action: String, requestCode: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            this.action = action
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}
