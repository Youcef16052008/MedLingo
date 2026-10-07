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

    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val reviewChannel = NotificationChannel(
                CHANNEL_ID_REVIEWS,
                "Rappels de Révision Quotidienne (SM-2)",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications pour les flashcards prêtes pour la répétition espacée"
                enableVibration(true)
            }

            val streakChannel = NotificationChannel(
                CHANNEL_ID_STREAKS,
                "Maintien de la Série (Streak)",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Alertes pour préserver votre série de jours d'étude consécutifs"
            }

            val pearlChannel = NotificationChannel(
                CHANNEL_ID_PEARLS,
                "Perle Clinique du Jour",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notions cliniques et mnémotechniques médicales à haut rendement"
            }

            val levelChannel = NotificationChannel(
                CHANNEL_ID_LEVELS,
                "Déblocage de Niveaux & Succès",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Célébration des paliers franchis dans la pyramide d'apprentissage"
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
    fun showReviewReminder(context: Context, dueCount: Int = 6) {
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

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_REVIEWS)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("📋 $dueCount Flashcards prêtes pour révision !")
            .setContentText("Votre session de répétition espacée (SM-2) est prête. Consolidez votre mémoire clinique.")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("📋 $dueCount termes médicaux sont arrivés à échéance de répétition espacée aujourd'hui selon l'algorithme SM-2.\n\nPrenez 3 minutes pour évaluer votre rappel en anglais et français !")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(
                android.R.drawable.ic_media_play,
                "Réviser maintenant 📚",
                pendingIntent
            )
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_REVIEW, notification)
    }

    @SuppressLint("MissingPermission")
    fun showStreakReminder(context: Context, streakDays: Int = 12) {
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

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_STREAKS)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("🔥 Ne brisez pas votre série de $streakDays jours !")
            .setContentText("Vous êtes sur une excellente lancée ! Une courte session aujourd'hui maintient votre régularité.")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("🔥 Série actuelle : $streakDays jours consécutifs d'anglais médical !\n\nChaque révision quotidienne transforme le vocabulaire passif en réflexe clinique actif. Gardez le cap confrère !")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_STREAK, notification)
    }

    @SuppressLint("MissingPermission")
    fun showClinicalPearlNotification(context: Context, term: MedicalTermEntity? = null) {
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

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_PEARLS)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("🩺 Perle Clinique : ${selectedTerm.termFr} (${selectedTerm.termEn})")
            .setContentText(selectedTerm.clinicalPearl)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .setBigContentTitle("🩺 Perle Clinique : ${selectedTerm.termFr}")
                    .bigText("${selectedTerm.clinicalPearl}\n\n💡 Mnémotechnique : ${selectedTerm.mnemonic}\n\n📖 Module : ${selectedTerm.module} — ${selectedTerm.chapter}")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(
                android.R.drawable.ic_menu_agenda,
                "Explorer le module 📖",
                pendingIntent
            )
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_PEARL, notification)
    }

    @SuppressLint("MissingPermission")
    fun showLevelUnlockedNotification(context: Context, levelNumber: Int, levelName: String) {
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

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_LEVELS)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("🏆 Niveau $levelNumber débloqué !")
            .setContentText("Félicitations ! Vous avez atteint le niveau : $levelName.")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("🏆 Félicitations futur Dr ! Vous venez de franchir avec succès le palier précédent avec ≥70% de précision.\n\nLe Niveau $levelNumber ($levelName) est maintenant accessible dans votre Pyramide d'apprentissage.")
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
