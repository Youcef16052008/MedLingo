package com.example

import com.example.domain.gamification.LeagueManager
import com.example.domain.sm2.SpacedRepetitionAlgorithm
import com.example.domain.time.FakeClock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.TimeZone

/**
 * Regression tests for Lot 3 & Lot 4 fixes:
 * - bot count must never exceed COHORT_SIZE (user + 29 bots)
 * - formatWeekRange must be stable in every device timezone (UTC)
 * - SM-2 nextReviewTimestamp driven by an injectable Clock
 */
class LeagueHeartsRegressionTest {

    private fun utcMillis(year: Int, month: Int, day: Int): Long =
        java.util.Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
            clear()
            set(year, month, day, 0, 0, 0)
        }.timeInMillis

    private fun utcTime(year: Int, month: Int, day: Int, hour: Int): Long =
        java.util.Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
            clear()
            set(year, month, day, hour, 0, 0)
        }.timeInMillis

    // ---------- Bug 20: cohort composition ----------

    @Test
    fun `generateBotsForCohort fills exactly COHORT_SIZE members`() {
        val members = LeagueManager.generateBotsForCohort(
            cohortId = "test-cohort",
            currentUserXp = 0,
            currentUserStreak = 12
        )
        assertEquals(LeagueManager.COHORT_SIZE, members.size)
        assertEquals(1, members.count { it.isCurrentUser })
        assertEquals(LeagueManager.COHORT_SIZE - 1, members.count { it.isBot })
        assertEquals(members.size, members.map { it.userId }.distinct().size)
    }

    @Test
    fun `generateBotsForCohort stays at COHORT_SIZE even with default name list`() {
        // The name list previously had 30 entries -> 31 members (29 claimed)
        val members = LeagueManager.generateBotsForCohort(cohortId = "c2")
        assertEquals(
            "default name list must fill exactly COHORT_SIZE (got ${members.size})",
            LeagueManager.COHORT_SIZE,
            members.size
        )
    }

    // ---------- Bug 22: formatWeekRange timezone ----------

    @Test
    fun `formatWeekRange renders day numbers for a fixed UTC week`() {
        val fixed = java.util.Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
            set(2026, java.util.Calendar.JANUARY, 5, 0, 0, 0) // a Monday
            set(java.util.Calendar.MILLISECOND, 0)
        }.timeInMillis
        val range = LeagueManager.formatWeekRange(fixed, java.util.Locale.FRENCH)
        // "5 janv. - 11 janv." — day numbers are UTC-based, so independent of device TZ
        assertTrue(
            "unexpected format: '$range'",
            Regex("""^\d{1,2} \S+ - \d{1,2} \S+$""").matches(range)
        )
        assertTrue("week must start on day 5: '$range'", range.startsWith("5 "))
        assertTrue("week must end on day 11: '$range'", range.contains(" - 11 "))
    }

    @Test
    fun `formatWeekRange is identical in extreme timezones`() {
        val weekStart = LeagueManager.getWeekStartTimestamp()
        val original = TimeZone.getDefault()
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("Pacific/Kiritimati")) // UTC+14
            val east = LeagueManager.formatWeekRange(weekStart, java.util.Locale.FRENCH)
            TimeZone.setDefault(TimeZone.getTimeZone("America/Anchorage")) // UTC-9
            val west = LeagueManager.formatWeekRange(weekStart, java.util.Locale.FRENCH)
            assertEquals(
                "formatWeekRange must not depend on the device timezone",
                east,
                west
            )
        } finally {
            TimeZone.setDefault(original)
        }
    }

    // ---------- formatWeekRange i18n ----------
    // The month names were hardcoded to French, so an Arabic user read "5 janv. - 11
    // janv.". formatWeekRange now requires the caller's locale.

    @Test
    fun `formatWeekRange uses French month names for the French locale`() {
        val fixed = utcMillis(2026, java.util.Calendar.JANUARY, 5)
        val range = LeagueManager.formatWeekRange(fixed, java.util.Locale.FRENCH)
        val month = java.util.Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            .apply { timeInMillis = fixed }
            .getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.SHORT, java.util.Locale.FRENCH)
        assertTrue("expected the French month name '$month' in '$range'", range.contains(month))
    }

    @Test
    fun `formatWeekRange uses Arabic month names for the Arabic locale`() {
        val fixed = utcMillis(2026, java.util.Calendar.JANUARY, 5)
        val range = LeagueManager.formatWeekRange(fixed, java.util.Locale("ar"))
        val arabicMonth = java.util.Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            .apply { timeInMillis = fixed }
            .getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.SHORT, java.util.Locale("ar"))
        assertTrue(
            "expected the Arabic month name '$arabicMonth' in '$range'",
            range.contains(arabicMonth)
        )
        assertTrue("day numbers must stay readable in Arabic: '$range'", range.startsWith("5 "))
    }

    @Test
    fun `app language maps to its platform locale`() {
        assertEquals(java.util.Locale.FRENCH, com.example.localization.Language.FRENCH.toLocale())
        assertEquals(java.util.Locale.ENGLISH, com.example.localization.Language.ENGLISH.toLocale())
        assertEquals(
            "ar",
            com.example.localization.Language.ARABIC.toLocale().language
        )
        // helper used by the UI layer must agree with the enum mapping
        com.example.localization.Language.entries.forEach { language ->
            assertEquals(
                language.toLocale(),
                com.example.localization.LanguageLocale.forLanguage(language)
            )
        }
    }

    @Test
    fun `getWeekStartTimestamp is midnight UTC on a monday`() {
        val ts = LeagueManager.getWeekStartTimestamp()
        // midnight UTC
        assertEquals(0L, ts % 86_400_000L)
        // Monday in UTC (Calendar.MONDAY=2, getDayOfWeek returns 2 for Monday)
        val cal = java.util.Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        cal.timeInMillis = ts
        assertEquals(java.util.Calendar.MONDAY, cal.get(java.util.Calendar.DAY_OF_WEEK))
    }

    // ---------- week boundary driven by the injected Clock ----------
    // getWeekStartTimestamp() used to read System.currentTimeMillis() directly, so the
    // "today is Sunday -> the Monday is in the future -> go back one week" branch could
    // not be asserted without depending on the day the tests happen to run.

    @Test
    fun `week start is the current week on a wednesday`() {
        // 2026-01-05 is a Monday, so 2026-01-07 is a Wednesday of that same week
        val clock = FakeClock(utcTime(2026, java.util.Calendar.JANUARY, 7, 15))
        assertEquals(
            utcMillis(2026, java.util.Calendar.JANUARY, 5),
            LeagueManager.getWeekStartTimestamp(clock)
        )
    }

    @Test
    fun `week start falls back to the previous week on a sunday`() {
        // Sunday 2026-01-11: the Monday of the current week (01-12) is in the future,
        // so the week must roll back to 01-05 instead of returning a future timestamp
        val clock = FakeClock(utcTime(2026, java.util.Calendar.JANUARY, 11, 15))
        val weekStart = LeagueManager.getWeekStartTimestamp(clock)
        assertEquals(utcMillis(2026, java.util.Calendar.JANUARY, 5), weekStart)
        assertTrue("week start must never be in the future", weekStart <= clock.now())
    }

    @Test
    fun `week start on monday midnight is that very instant`() {
        val clock = FakeClock(utcMillis(2026, java.util.Calendar.JANUARY, 5))
        assertEquals(
            utcMillis(2026, java.util.Calendar.JANUARY, 5),
            LeagueManager.getWeekStartTimestamp(clock)
        )
    }

    @Test
    fun `createNewCohort uses the injected clock for the week and the creation date`() {
        val now = utcTime(2026, java.util.Calendar.JANUARY, 11, 15) // Sunday
        val clock = FakeClock(now)
        val cohort = LeagueManager.createNewCohort(tier = "GOLD", clock = clock)

        val expectedWeek = utcMillis(2026, java.util.Calendar.JANUARY, 5)
        assertEquals(expectedWeek, cohort.weekStartTimestamp)
        // the week end is inclusive: the very last millisecond of the 7th day
        assertEquals(
            expectedWeek + 7L * 86_400_000L - 1L,
            cohort.weekEndTimestamp
        )
        assertEquals("createdAt must come from the injected clock", now, cohort.createdAt)
        assertTrue(
            "cohortId must embed the same week: ${cohort.cohortId}",
            cohort.cohortId.startsWith("$expectedWeek-gold-")
        )
    }

    @Test
    fun `generateCohortId embeds the week of the injected clock`() {
        val clock = FakeClock(utcTime(2026, java.util.Calendar.JANUARY, 7, 9))
        val expectedWeek = utcMillis(2026, java.util.Calendar.JANUARY, 5)
        assertTrue(
            LeagueManager.generateCohortId("SILVER", clock).startsWith("$expectedWeek-silver-")
        )
    }

    @Test
    fun `generateBotsForCohort stamps lastActiveTimestamp from the injected clock`() {
        val clock = FakeClock(1_700_000_000_000L)
        val members = LeagueManager.generateBotsForCohort(
            cohortId = "cohort-clock",
            currentUserXp = 0,
            currentUserStreak = 3,
            clock = clock
        )
        assertEquals(LeagueManager.COHORT_SIZE, members.size)
        members.forEach { member ->
            assertEquals(
                "member ${member.displayName} must not read wall clock",
                clock.now(),
                member.lastActiveTimestamp
            )
        }
    }

    // ---------- Bug 23: SM-2 with injectable clock ----------

    @Test
    fun `sm2 nextReviewTimestamp is computed from injected clock`() {
        val clock = FakeClock(1_700_000_000_000L)
        val result = SpacedRepetitionAlgorithm.calculateNext(
            quality = 4,
            previousRepetitions = 0,
            previousEaseFactor = 2.5,
            previousIntervalDays = 0,
            clock = clock
        )
        assertEquals(1, result.intervalDays)
        assertEquals(
            clock.now() + 1L * 24 * 60 * 60 * 1000,
            result.nextReviewTimestamp
        )
    }

    @Test
    fun `sm2 failure resets repetitions`() {
        val clock = FakeClock(1_700_000_000_000L)
        val failed = SpacedRepetitionAlgorithm.calculateNext(
            quality = 1,
            previousRepetitions = 5,
            previousEaseFactor = 2.5,
            previousIntervalDays = 30,
            clock = clock
        )
        assertEquals(0, failed.repetitions)
        assertEquals(1, failed.intervalDays)
    }
}
