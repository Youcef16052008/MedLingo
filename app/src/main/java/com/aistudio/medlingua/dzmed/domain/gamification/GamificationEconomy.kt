package com.aistudio.medlingua.dzmed.domain.gamification

import com.aistudio.medlingua.dzmed.data.local.entity.UserStatsEntity
import com.aistudio.medlingua.dzmed.domain.gamification.audio.SoundEffect
import com.aistudio.medlingua.dzmed.domain.gamification.audio.SoundManager
import com.aistudio.medlingua.dzmed.domain.time.Clock
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import com.aistudio.medlingua.dzmed.domain.time.FakeClock

/** Prix d'une congélation de série (❄️). */
const val FREEZE_COST_GEMS = 200

/** Options pour l'objectif quotidien (XP). */
val DAILY_GOAL_OPTIONS = listOf(20, 50, 100)

/** Résultat d'un ajout d'XP. */
data class XpOutcome(
    val stats: UserStatsEntity,
    val gained: Int,
    /** L'objectif quotidien vient d'être atteint (une seule fois par jour). */
    val goalHit: Boolean,
    /** Une congélation a été consommée pour éviter la rupture de série. */
    val freezeUsed: Boolean,
    /** La série a été incrémentée (pas de nuit). */
    val streakGained: Boolean
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
 * Gamification complète : économie XP/gemmes, série 🔥, objectif ⭐, caisses 🎁.
 * Gestion des sons et notifications via SoundManager.
 *
 * Économie XP amplifiée :
 * - QCM L1/L2 : 8-13 XP (comme web-react, pas 10-35) — trop généreux sur les débuts
 * - QCM L3/L4 : 15-25 XP
 * - QCM L5/L6 : 22-35 XP
 * - Perfect lesson (100%) : +30 XP bonus
 * - Streak 3 jours : +10 XP, 7 jours : +25 XP, 14 jours : +50 XP
 * - Objectif quotidien atteint : +20 XP
 * - Caisse finale de leçon : +10-30 XP aléatoire
 */

object GamificationEconomy {

    /** XP par question (scaling par niveau) : L1: 8, L2: 11, L3: 14, L4: 17, L5: 20, L6: 23 */
    fun xpPerQuestion(level: Int): Int = when {
        level <= 2 -> 8 + (level - 1) * 3
        level <= 4 -> 14 + (level - 3) * 3
        level <= 6 -> 20 + (level - 5) * 3
        else -> 10
    }

    /** Bonus par série complète. */
    fun streakXpBonus(days: Int): Int = when {
        days >= 30 -> 100
        days >= 14 -> 50
        days >= 7 -> 25
        days >= 3 -> 10
        else -> 0
    }

    /** Bonus par objectif quotidien atteint. */
    const val DAILY_GOAL_BONUS_XP = 20

    /** Bonus perfect lesson (100%). */
    const val PERFECT_LESSON_BONUS_XP = 30

    /** Bonus caisse de leçon. */
    private val CHEST_XP_RANGE = 10..30

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

        // Série (BUG-025 : le résultat alimente VRAIMENT XpOutcome — les
        // champs étaient codés en dur à false alors qu'un gel était consommé).
        val initialStreak = next.streakDays
        val streak = touchStreak(next, now)
        next = streak.stats

        // Total + du jour + hebdo
        val before = next.xpToday
        next = next.copy(
            totalPoints = next.totalPoints + xp,
            xpToday = next.xpToday + xp,
            weeklyXp = next.weeklyXp + xp
        )

        // Bonus série
        val streakBonus = streakXpBonus(next.streakDays)
        if (streakBonus > 0) {
            next = next.copy(totalPoints = next.totalPoints + streakBonus)
        }

        val goalHit = before < next.dailyGoal && next.xpToday >= next.dailyGoal
        if (goalHit) next = next.copy(goalDays = next.goalDays + 1)

        return XpOutcome(
            next,
            xp,
            goalHit,
            freezeUsed = streak.freezeUsed,
            streakGained = next.streakDays > initialStreak
        )
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

    /** Jour civil (`yyyy-MM-dd`) dans le fuseau de l'appareil. */
    fun todayKey(now: Long): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date(now))

    /** Hier par jour CALENDAIRE (BUG-019 : `now - 24h` tombait sur l'avant-
     *  veille au petit matin d'un jour de 23 h — changement d'heure). */
    fun yesterdayKey(now: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = now
        cal.add(Calendar.DAY_OF_MONTH, -1)
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(cal.time)
    }

    /** Jours calendaires entre deux clés yyyy-MM-dd (UTC, robuste DST). */
    fun daysBetweenKeys(from: String, to: String): Int {
        val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        fmt.timeZone = TimeZone.getTimeZone("UTC")
        val a = fmt.parse(from)?.time ?: return 0
        val b = fmt.parse(to)?.time ?: return 0
        return Math.round((b - a).toDouble() / 86_400_000.0).toInt()
    }

    /** Remet à zéro les compteurs hebdomadaires (XP ligue + jours d'objectif) quand
     *  le lundi UTC a changé. La première synchronisation (`weeklyXpReset == 0`) n'écrase
     *  rien : elle adopte la semaine en cours pour préserver l'XP déjà gagné.
     */
    fun syncWeek(stats: UserStatsEntity, now: Long): UserStatsEntity {
        val weekStart = LeagueManager.getWeekStartTimestamp(FakeClock(now))
        if (stats.weeklyXpReset == weekStart) return stats
        if (stats.weeklyXpReset == 0L) return stats.copy(weeklyXpReset = weekStart)
        return stats.copy(weeklyXp = 0, goalDays = 0, weeklyXpReset = weekStart)
    }

    /** Incrémente la série si l'utilisateur n'avait pas encore étudié aujourd'hui.
     *  Écart d'un jour : continuation. Écart de N jours (N ≥ 2) : chaque
     *  congélation comble UN jour manquant (BUG-019 : un gel couvrait un trou
     *  illimité) ; sans assez de gels, la série repart à 1. Une base sans
     *  `lastStudy` (migration v10) adopte le jour courant sans casser la série.
     */
    fun touchStreak(stats: UserStatsEntity, now: Long): StreakOutcome {
        val today = todayKey(now)
        if (stats.lastStudy == today) return StreakOutcome(stats, false)
        if (stats.lastStudy.isEmpty()) {
            return StreakOutcome(stats.copy(lastStudy = today), false)
        }

        val gap = daysBetweenKeys(stats.lastStudy, today)
        if (gap == 1) {
            return StreakOutcome(
                stats.copy(streakDays = stats.streakDays + 1, lastStudy = today),
                false
            )
        }

        val missing = gap - 1
        return if (missing > 0 && stats.streakFreezeCount >= missing) {
            StreakOutcome(
                stats.copy(
                    streakFreezeCount = stats.streakFreezeCount - missing,
                    lastStudy = today
                ),
                freezeUsed = true
            )
        } else {
            StreakOutcome(stats.copy(streakDays = 1, lastStudy = today), false)
        }
    }
}
