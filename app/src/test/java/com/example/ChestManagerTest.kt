package com.example

import com.example.data.local.entity.UserStatsEntity
import com.example.domain.gamification.ChestKind
import com.example.domain.gamification.ChestManager
import com.example.domain.gamification.ChestReward
import com.example.domain.gamification.ChestSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * ChestManager — tirage pondéré des caisses (💰 / ❄️ / ⚡).
 * `rand` injecté : le tirage est reproductible en test.
 */
class ChestManagerTest {

    @Test
    fun `la table de tirage pese 100 pourcent`() {
        assertEquals(100, ChestManager.CHEST_TABLE.sumOf { it.weight })
    }

    @Test
    fun `tirage bornes de la table`() {
        // roll = 0.0 * 100 = 0 < 30 -> première ligne (XP 20)
        assertEquals(ChestReward(ChestKind.XP, 20), ChestManager.openChest { 0.0 })
        // roll = 31 -> dans [30, 60) -> seconde ligne (GEMS 30)
        assertEquals(ChestReward(ChestKind.GEMS, 30), ChestManager.openChest { 0.31 })
        // roll = 99.9 -> dernière ligne (GEMS 100)
        assertEquals(ChestReward(ChestKind.GEMS, 100), ChestManager.openChest { 0.999 })
    }

    @Test
    fun `toutes les recompenses possibles sont tirables`() {
        val seeds = listOf(0.0, 0.31, 0.55, 0.85, 0.99)
        val kinds = seeds.map { ChestManager.openChest { it }.kind }.toSet()
        // XP (0-30), GEMS (30-80), FREEZE (80-95) : les trois kinds apparaissent
        assertEquals(setOf(ChestKind.XP, ChestKind.GEMS, ChestKind.FREEZE), kinds)
    }

    @Test
    fun `un quiz gagne toujours une caisse une revition seulement a partir de 10 cartes`() {
        assertTrue(ChestManager.earnsChest(ChestSource.QUIZ, 0))
        assertFalse(ChestManager.earnsChest(ChestSource.FLASH, 9))
        assertTrue(ChestManager.earnsChest(ChestSource.FLASH, 10))
    }

    @Test
    fun `appliquer une recompense gemmes congelation ou xp`() {
        val now = 1_770_000_000_000L
        val base = UserStatsEntity(gems = 100, streakFreezeCount = 1, totalPoints = 0, weeklyXp = 0, xpToday = 0)

        val gems = ChestManager.applyChestReward(base, ChestReward(ChestKind.GEMS, 50), now)
        assertEquals(150, gems.gems)

        val freeze = ChestManager.applyChestReward(base, ChestReward(ChestKind.FREEZE, 1), now)
        assertEquals(2, freeze.streakFreezeCount)

        val xp = ChestManager.applyChestReward(base, ChestReward(ChestKind.XP, 20), now)
        assertEquals(20, xp.totalPoints)
        assertEquals(20, xp.xpToday)
        assertEquals(20, xp.weeklyXp)
        assertEquals(100, xp.gems)
    }
}
