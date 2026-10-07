package com.example.domain.gamification

import com.example.data.local.entity.UserStatsEntity
import com.example.domain.time.FakeClock
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Objectifs quotidiens proposés (XP). */
val DAILY_GOAL_OPTIONS = listOf(20, 50, 100)

/** Prix d'une congélation de série (❄️). */
const val FREEZE_COST_GEMS = 200

/** Résultat d'un ajout d'XP. */
data class XpOutcome(
    val stats: UserStatsEntity,
    val gained: Int,
    /** L'objectif quotidien vient d'être atteint (une seule fois par jour). */
    val goalHit: Boolean,
    /** Une congélation a été consommée pour éviter la rupture de série. */
    val freezeUsed: Boolean
)

/** Progression de l'objectif quotidien du jour courant. */
data class GoalProgress(
    val today: Int,
    val goal: Int,
    val pct: Int,
    val done: Boolean
)

/** Résultat d'une touche de série. */
data class StreakOutcome(
    val stats: UserStatsEntity,
    val freezeUsed: Boolean
)

/**
 * Gamification quotidienne : série 🔥, objectif ⭐, congélations ❄️, gemmes 💎.
 * Port Kotlin des règles du web (`web-react/src/domain/gamification.ts`) :
 * l'horloge est injectée (`now` en ms), l'état est un [UserStatsEntity] immuable.
 *
 * Il n'y a **plus de cœurs** : une session ne peut pas être bloquée par des erreurs.
 */
object ProgressionManager {

    /** Jour civil (`yyyy-MM-dd`) dans le fuseau de l'appareil. */
    fun todayKey(now: Long): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date(now))

    /**
     * Remet à zéro les compteurs hebdomadaires (XP ligue + jours d'objectif) quand
     * le lundi UTC a changé. La première synchronisation (`weeklyXpReset == 0`) n'écrase
     * rien : elle adopte la semaine en cours pour préserver l'XP déjà gagné.
     */
    fun syncWeek(stats: UserStatsEntity, now: Long): UserStatsEntity {
        val weekStart = LeagueManager.getWeekStartTimestamp(FakeClock(now))
        if (stats.weeklyXpReset == weekStart) return stats
        if (stats.weeklyXpReset == 0L) return stats.copy(weeklyXpReset = weekStart)
        return stats.copy(weeklyXp = 0, goalDays = 0, weeklyXpReset = weekStart)
    }

    /**
     * Incrémente la série si l'utilisateur n'avait pas encore étudié aujourd'hui.
     * Écart d'un jour ou plus : une congélation consommée conserve la série,
     * sinon la série repart à 1. Une base sans `lastStudy` (migration v10) adopte
     * le jour courant sans casser la série existante.
     */
    fun touchStreak(stats: UserStatsEntity, now: Long): StreakOutcome {
        val today = todayKey(now)
        if (stats.lastStudy == today) return StreakOutcome(stats, false)
        if (stats.lastStudy.isEmpty()) {
            return StreakOutcome(stats.copy(lastStudy = today), false)
        }

        val yesterday = todayKey(now - 86_400_000L)
        if (stats.lastStudy == yesterday) {
            return StreakOutcome(
                stats.copy(streakDays = stats.streakDays + 1, lastStudy = today),
                false
            )
        }

        return if (stats.streakFreezeCount > 0) {
            StreakOutcome(
                stats.copy(
                    streakFreezeCount = stats.streakFreezeCount - 1,
                    lastStudy = today
                ),
                freezeUsed = true
            )
        } else {
            StreakOutcome(stats.copy(streakDays = 1, lastStudy = today), false)
        }
    }

    /**
     * Ajoute de l'XP : total, du jour (reset au changement de jour civil) et
     * hebdomadaire (ligue). Touche aussi la série et signale l'atteinte de
     * l'objectif quotidien.
     */
    fun addXp(stats: UserStatsEntity, xp: Int, now: Long): XpOutcome {
        val today = todayKey(now)
        var next = stats
        if (next.goalDate != today) {
            next = next.copy(xpToday = 0, goalDate = today)
        }
        next = syncWeek(next, now)
        next = touchStreak(next, now).stats

        val before = next.xpToday
        next = next.copy(
            totalPoints = next.totalPoints + xp,
            xpToday = next.xpToday + xp,
            weeklyXp = next.weeklyXp + xp
        )

        val goalHit = before < next.dailyGoal && next.xpToday >= next.dailyGoal
        if (goalHit) next = next.copy(goalDays = next.goalDays + 1)
        return XpOutcome(next, xp, goalHit, freezeUsed = false)
    }

    /** Progression de l'objectif quotidien (0 si le jour a changé). */
    fun goalProgress(stats: UserStatsEntity, now: Long): GoalProgress {
        val today = if (stats.goalDate == todayKey(now)) stats.xpToday else 0
        val goal = stats.dailyGoal
        val pct = if (goal > 0) minOf(100, (today * 100) / goal) else 0
        return GoalProgress(today, goal, pct, today >= goal)
    }

    /** Achat d'une congélation : 200 💎. `null` si les gemmes manquent. */
    fun buyFreeze(stats: UserStatsEntity): UserStatsEntity? {
        if (stats.gems < FREEZE_COST_GEMS) return null
        return stats.copy(
            gems = stats.gems - FREEZE_COST_GEMS,
            streakFreezeCount = stats.streakFreezeCount + 1
        )
    }
}
