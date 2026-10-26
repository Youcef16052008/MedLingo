package com.aistudio.medlingua.dzmed.service

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.aistudio.medlingua.dzmed.MainActivity
import com.aistudio.medlingua.dzmed.R
import com.aistudio.medlingua.dzmed.data.local.database.MedLinguaDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * WS4 levier 8 — widget de série 2×2 : rappel passif permanent, 100 % offline
 * (lecture directe de user_stats en Room ; aucune donnée quitte l'appareil).
 * Mise à jour : à l'ajout, à chaque toucher (rafraîchit + ouvre l'app) et via
 * `updateAll` appelé par le ViewModel après chaque gain d'XP.
 */
class StreakWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, manager: AppWidgetManager, appWidgetIds: IntArray) {
        for (id in appWidgetIds) manager.updateAppWidget(id, loading(context))
        refresh(context)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        // Toucher le widget = rafraîchir puis ouvrir l'app.
        if (intent.action == ACTION_TAP) {
            refresh(context)
        }
    }

    private fun loading(context: Context): RemoteViews =
        RemoteViews(context.packageName, R.layout.streak_widget)

    private fun refresh(context: Context) {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        scope.launch {
            val dao = MedLinguaDatabase.getDatabase(context).medicalDao()
            val stats = dao.getUserStatsOnce() ?: return@launch
            val views = RemoteViews(context.packageName, R.layout.streak_widget)
            views.setTextViewText(R.id.widget_streak, "🔥 ${stats.streakDays}")
            views.setTextViewText(R.id.widget_gems, "💎 ${stats.gems}")
            val lang = try {
                com.aistudio.medlingua.dzmed.localization.Language.fromCode(
                    dao.getLanguageCodeBlocking() ?: "fr"
                )
            } catch (e: Exception) {
                com.aistudio.medlingua.dzmed.localization.Language.FRENCH
            }
            views.setTextViewText(
                R.id.widget_label,
                context.getString(
                    if (lang == com.aistudio.medlingua.dzmed.localization.Language.ARABIC)
                        R.string.widget_streak_label_ar else R.string.widget_streak_label
                )
            )
            val tap = PendingIntent.getBroadcast(
                context,
                0,
                Intent(context, StreakWidgetProvider::class.java).setAction(ACTION_TAP),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_root, tap)
            val open = PendingIntent.getActivity(
                context,
                0,
                Intent(context, MainActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_streak, open)
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, StreakWidgetProvider::class.java), views
            )
        }
    }

    companion object {
        private const val ACTION_TAP = "com.aistudio.medlingua.dzmed.widget.TAP"

        /** Appelé par le ViewModel après chaque gain d'XP / changement de série. */
        fun updateAll(context: Context) {
            val manager = AppWidgetManager.getInstance(context)
            val ids = manager.getAppWidgetIds(ComponentName(context, StreakWidgetProvider::class.java))
            if (ids.isNotEmpty()) {
                context.sendBroadcast(
                    Intent(context, StreakWidgetProvider::class.java)
                        .setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
                        .putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
                )
            }
        }
    }
}
