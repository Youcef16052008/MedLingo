package com.example

import com.example.data.local.entity.UserStatsEntity
import com.example.domain.gamification.TrophyId
import com.example.domain.gamification.TrophyManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * TrophyManager — 12 trophées de l'écran « Moi ».
 * [TrophyManager.evaluate] est pur, [TrophyManager.claimTrophies] retourne
 * uniquement les gains nouveaux (affichés dans la pop-up de récompense).
 */
class TrophyManagerTest {

    /** Profil vierge : aucun seuil atteint. */
    private fun emptyStats() = UserStatsEntity(
        streakDays = 0,
        totalPoints = 0,
        quizzesCompleted = 0,
        perfectLessonsCount = 0,
        level1Score = 0,
        level2Score = 0,
        level3Score = 0,
        level4Score = 0,
        level5Score = 0,
        level6Score = 0,
        leagueTier = "BRONZE",
        flashReviewed = 0,
        goalDays = 0,
        chestsOpened = 0
    )

    @Test
    fun `les douze trophes existent dans l ordre d affichage`() {
        assertEquals(12, TrophyManager.ALL.size)
        assertEquals(TrophyId.entries.toList(), TrophyManager.ALL)
    }

    @Test
    fun `un profil vierge ne gagne aucun trophee`() {
        assertEquals(emptyList<TrophyId>(), TrophyManager.evaluate(emptyStats()))
    }

    @Test
    fun `le profil de depart gagne les trophees de serie xp et quiz`() {
        val won = TrophyManager.evaluate(UserStatsEntity())
        assertEquals(
            setOf(
                TrophyId.FIRST_LESSON,
                TrophyId.STREAK_7,
                TrophyId.XP_1000,
                TrophyId.QUIZZES_10
            ),
            won.toSet()
        )
        assertFalse(won.contains(TrophyId.STREAK_30))
        assertFalse(won.contains(TrophyId.XP_5000))
        assertFalse(won.contains(TrophyId.MODULE_MASTER))
        assertFalse(won.contains(TrophyId.LEAGUE_PROMOTED))
    }

    @Test
    fun `seuils de serie xp cartes objectif et caisses`() {
        val stats = emptyStats().copy(
            streakDays = 30,
            totalPoints = 5000,
            flashReviewed = 100,
            goalDays = 7,
            chestsOpened = 5,
            perfectLessonsCount = 1
        )
        val won = TrophyManager.evaluate(stats).toSet()
        assertTrue(won.contains(TrophyId.STREAK_7))
        assertTrue(won.contains(TrophyId.STREAK_30))
        assertTrue(won.contains(TrophyId.XP_1000))
        assertTrue(won.contains(TrophyId.XP_5000))
        assertTrue(won.contains(TrophyId.CARDS_100))
        assertTrue(won.contains(TrophyId.GOAL_HIT_7))
        assertTrue(won.contains(TrophyId.CHESTS_5))
        assertTrue(won.contains(TrophyId.FIRST_PERFECT))
        assertFalse(won.contains(TrophyId.MODULE_MASTER))
    }

    @Test
    fun `module master exige au moins 90 sur un niveau`() {
        assertFalse(TrophyManager.evaluate(emptyStats().copy(level1Score = 89)).contains(TrophyId.MODULE_MASTER))
        assertTrue(TrophyManager.evaluate(emptyStats().copy(level1Score = 90)).contains(TrophyId.MODULE_MASTER))
        assertTrue(TrophyManager.evaluate(emptyStats().copy(level6Score = 90)).contains(TrophyId.MODULE_MASTER))
    }

    @Test
    fun `promotion de ligue = tout sauf bronze`() {
        assertTrue(TrophyManager.evaluate(emptyStats().copy(leagueTier = "SILVER")).contains(TrophyId.LEAGUE_PROMOTED))
        assertFalse(TrophyManager.evaluate(emptyStats().copy(leagueTier = "BRONZE")).contains(TrophyId.LEAGUE_PROMOTED))
    }

    @Test
    fun `claimTrophies ignore les trophees deja gagnes et horodate`() {
        val now = 1_770_000_000_000L
        val stats = emptyStats().copy(streakDays = 7, totalPoints = 1000)

        val fresh = TrophyManager.claimTrophies(stats, earned = emptySet(), now = now)
        assertEquals(
            listOf(TrophyId.STREAK_7, TrophyId.XP_1000),
            fresh.map { it.first }
        )
        assertTrue(fresh.all { it.second == now })

        val claimed = TrophyManager.claimTrophies(
            stats,
            earned = setOf(TrophyId.STREAK_7.name),
            now = now + 1
        )
        assertEquals(listOf(TrophyId.XP_1000), claimed.map { it.first })

        val none = TrophyManager.claimTrophies(
            stats,
            earned = setOf(TrophyId.STREAK_7.name, TrophyId.XP_1000.name),
            now = now + 2
        )
        assertTrue(none.isEmpty())
    }
}
