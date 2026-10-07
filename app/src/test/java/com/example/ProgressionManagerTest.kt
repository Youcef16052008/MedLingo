package com.example

import com.example.data.local.entity.UserStatsEntity
import com.example.domain.gamification.DAILY_GOAL_OPTIONS
import com.example.domain.gamification.FREEZE_COST_GEMS
import com.example.domain.gamification.ProgressionManager
import com.example.domain.gamification.LeagueManager
import com.example.domain.time.FakeClock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar
import java.util.TimeZone

/**
 * ProgressionManager — série 🔥, objectif ⭐, congélation ❄️, reset hebdomadaire.
 * L'horloge est injectée (`now` en ms) : mêmes règles que `web-react/src/domain/gamification.ts`.
 */
class ProgressionManagerTest {

    private fun utc(y: Int, m: Int, d: Int, h: Int = 12): Long =
        Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
            clear()
            set(y, m - 1, d, h, 0, 0)
        }.timeInMillis

    private fun stats(
        streakDays: Int = 12,
        lastStudy: String = "",
        streakFreezeCount: Int = 0,
        xpToday: Int = 0,
        goalDate: String = "",
        dailyGoal: Int = 50,
        goalDays: Int = 0,
        weeklyXp: Int = 0,
        weeklyXpReset: Long = 0L,
        gems: Int = 100,
        totalPoints: Int = 0
    ) = UserStatsEntity(
        streakDays = streakDays,
        lastStudy = lastStudy,
        streakFreezeCount = streakFreezeCount,
        xpToday = xpToday,
        goalDate = goalDate,
        dailyGoal = dailyGoal,
        goalDays = goalDays,
        weeklyXp = weeklyXp,
        weeklyXpReset = weeklyXpReset,
        gems = gems,
        totalPoints = totalPoints
    )

    // ---------- todayKey ----------

    @Test
    fun `todayKey est une date au format yyyy-MM-dd et est deterministe`() {
        val now = utc(2026, 10, 6)
        assertEquals("2026-10-06", ProgressionManager.todayKey(now))
        assertEquals(ProgressionManager.todayKey(now), ProgressionManager.todayKey(now))
    }

    // ---------- syncWeek ----------

    @Test
    fun `syncWeek adopte la semaine en cours sans rien ecraser`() {
        val now = utc(2026, 10, 6)
        val adopted = ProgressionManager.syncWeek(stats(weeklyXp = 300, goalDays = 2), now)
        assertEquals(LeagueManager.getWeekStartTimestamp(FakeClock(now)), adopted.weeklyXpReset)
        assertEquals(300, adopted.weeklyXp)
        assertEquals(2, adopted.goalDays)
    }

    @Test
    fun `syncWeek ne fait rien quand la semaine est deja la bonne`() {
        val now = utc(2026, 10, 6)
        val weekStart = LeagueManager.getWeekStartTimestamp(FakeClock(now))
        val s = stats(weeklyXp = 42, goalDays = 1, weeklyXpReset = weekStart)
        assertEquals(s, ProgressionManager.syncWeek(s, now))
    }

    @Test
    fun `syncWeek remet a zero XP ligue et jours d objectif au changement de semaine`() {
        val now = utc(2026, 10, 6)
        val lastWeek = LeagueManager.getWeekStartTimestamp(FakeClock(utc(2026, 9, 21)))
        val reset = ProgressionManager.syncWeek(
            stats(weeklyXp = 900, goalDays = 4, weeklyXpReset = lastWeek),
            now
        )
        assertEquals(0, reset.weeklyXp)
        assertEquals(0, reset.goalDays)
        assertEquals(LeagueManager.getWeekStartTimestamp(FakeClock(now)), reset.weeklyXpReset)
    }

    // ---------- touchStreak ----------

    @Test
    fun `touchStreak ne bouge pas quand on a deja etudie aujourd hui`() {
        val now = utc(2026, 10, 6)
        val today = ProgressionManager.todayKey(now)
        val out = ProgressionManager.touchStreak(stats(streakDays = 12, lastStudy = today), now)
        assertEquals(12, out.stats.streakDays)
        assertEquals(today, out.stats.lastStudy)
        assertFalse(out.freezeUsed)
    }

    @Test
    fun `touchStreak adopte le jour sur une base migratee sans lastStudy`() {
        val now = utc(2026, 10, 6)
        val out = ProgressionManager.touchStreak(stats(streakDays = 12, lastStudy = ""), now)
        assertEquals(12, out.stats.streakDays)
        assertEquals(ProgressionManager.todayKey(now), out.stats.lastStudy)
        assertFalse(out.freezeUsed)
    }

    @Test
    fun `touchStreak incremente la serie quand hier etudie`() {
        val now = utc(2026, 10, 6)
        val yesterday = ProgressionManager.todayKey(now - 86_400_000L)
        val out = ProgressionManager.touchStreak(stats(streakDays = 12, lastStudy = yesterday), now)
        assertEquals(13, out.stats.streakDays)
        assertFalse(out.freezeUsed)
    }

    @Test
    fun `touchStreak consomme une congelation sur un trou de plusieurs jours`() {
        val now = utc(2026, 10, 6)
        val threeDaysAgo = ProgressionManager.todayKey(now - 3 * 86_400_000L)
        val out = ProgressionManager.touchStreak(
            stats(streakDays = 12, lastStudy = threeDaysAgo, streakFreezeCount = 1),
            now
        )
        assertTrue(out.freezeUsed)
        assertEquals(0, out.stats.streakFreezeCount)
        assertEquals(12, out.stats.streakDays)
        assertEquals(ProgressionManager.todayKey(now), out.stats.lastStudy)
    }

    @Test
    fun `touchStreak casse la serie sans congelation disponible`() {
        val now = utc(2026, 10, 6)
        val threeDaysAgo = ProgressionManager.todayKey(now - 3 * 86_400_000L)
        val out = ProgressionManager.touchStreak(
            stats(streakDays = 12, lastStudy = threeDaysAgo, streakFreezeCount = 0),
            now
        )
        assertFalse(out.freezeUsed)
        assertEquals(1, out.stats.streakDays)
    }

    // ---------- addXp ----------

    @Test
    fun `addXp credite total du jour ligue et touche la serie`() {
        val now = utc(2026, 10, 6)
        val yesterday = ProgressionManager.todayKey(now - 86_400_000L)
        val out = ProgressionManager.addXp(stats(lastStudy = yesterday), xp = 25, now = now)
        assertEquals(25, out.gained)
        assertEquals(25, out.stats.totalPoints)
        assertEquals(25, out.stats.xpToday)
        assertEquals(25, out.stats.weeklyXp)
        assertEquals(13, out.stats.streakDays)
        assertEquals(ProgressionManager.todayKey(now), out.stats.lastStudy)
    }

    @Test
    fun `addXp signale l atteinte de l objectif une seule fois par jour`() {
        val now = utc(2026, 10, 6)
        var s = stats(dailyGoal = 50)

        val first = ProgressionManager.addXp(s, xp = 40, now = now)
        assertFalse(first.goalHit)
        assertEquals(0, first.stats.goalDays)

        val cross = ProgressionManager.addXp(first.stats, xp = 20, now = now)
        assertTrue(cross.goalHit)
        assertEquals(1, cross.stats.goalDays)

        val later = ProgressionManager.addXp(cross.stats, xp = 50, now = now)
        assertFalse(later.goalHit)
        assertEquals(1, later.stats.goalDays)
        assertEquals(110, later.stats.xpToday)
    }

    @Test
    fun `addXp remet a zero xpToday au changement de jour civil`() {
        val now = utc(2026, 10, 6)
        val yesterday = ProgressionManager.todayKey(now - 86_400_000L)
        val out = ProgressionManager.addXp(
            stats(xpToday = 45, goalDate = yesterday, goalDays = 1),
            xp = 5,
            now = now
        )
        assertEquals(5, out.stats.xpToday)
        assertEquals(ProgressionManager.todayKey(now), out.stats.goalDate)
        assertFalse(out.goalHit)
    }

    @Test
    fun `les options d objectif sont 20 50 100`() {
        assertEquals(listOf(20, 50, 100), DAILY_GOAL_OPTIONS)
    }

    // ---------- goalProgress ----------

    @Test
    fun `goalProgress reflete le jour courant avec un pourcentage borne`() {
        val now = utc(2026, 10, 6)
        val today = ProgressionManager.todayKey(now)
        val p = ProgressionManager.goalProgress(
            stats(xpToday = 60, goalDate = today, dailyGoal = 50),
            now
        )
        assertEquals(60, p.today)
        assertEquals(50, p.goal)
        assertEquals(100, p.pct)
        assertTrue(p.done)
    }

    @Test
    fun `goalProgress tombe a zero quand le jour a change`() {
        val now = utc(2026, 10, 6)
        val yesterday = ProgressionManager.todayKey(now - 86_400_000L)
        val p = ProgressionManager.goalProgress(
            stats(xpToday = 45, goalDate = yesterday, dailyGoal = 50),
            now
        )
        assertEquals(0, p.today)
        assertEquals(0, p.pct)
        assertFalse(p.done)
    }

    // ---------- buyFreeze ----------

    @Test
    fun `buyFreeze refuse sans assez de gemmes`() {
        assertNull(ProgressionManager.buyFreeze(stats(gems = FREEZE_COST_GEMS - 1)))
    }

    @Test
    fun `buyFreeze debite les gemmes et ajoute une congelation`() {
        val bought = ProgressionManager.buyFreeze(stats(gems = 300, streakFreezeCount = 1))
        assertNotNull(bought)
        assertEquals(300 - FREEZE_COST_GEMS, bought!!.gems)
        assertEquals(2, bought.streakFreezeCount)
    }
}
