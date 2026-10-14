package com.aistudio.medlingua.dzmed

import com.aistudio.medlingua.dzmed.data.local.entity.LeagueMemberEntity
import com.aistudio.medlingua.dzmed.domain.gamification.LeagueBotAnimator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * LeagueBotAnimator — Phase 3 #8 (rapport final).
 *
 * Les bots ne jouaient jamais : `generateBotsForCohort` fixait leur `weeklyXp`
 * à la création de la cohorte et rien ne le bougeait. Dès le lundi, un seul
 * exercice plaçait le joueur définitivement — l'urgence hebdomadaire du style
 * Duolingo n'existait pas.
 *
 * L'animateur progresse leur XP le long de la semaine, de façon déterministe.
 */
class LeagueBotAnimatorTest {

    private val weekStart = 1_700_000_000_000L
    private val weekEnd = weekStart + 7 * 24 * 60 * 60 * 1000L

    private fun bot(userId: Int, xp: Int = 0) = LeagueMemberEntity(
        id = userId.toLong(),
        cohortId = "test-cohort",
        userId = userId,
        displayName = "Bot_$userId",
        avatarEmoji = "🤖",
        weeklyXp = xp,
        totalXp = 1000,
        streakDays = 3,
        rank = 0,
        isCurrentUser = false,
        isBot = true,
        lastActiveTimestamp = weekStart
    )

    @Test
    fun `un vrai utilisateur n est jamais modifie`() {
        // Sécurité critique : modifier l'XP de l'utilisateur ici effacerait sa
        // progression réelle. Double garde dans le repository ET ici.
        val user = bot(1).copy(isBot = false, isCurrentUser = true, weeklyXp = 500)
        val advanced = LeagueBotAnimator.advanceBot(user, weekStart, weekEnd, weekEnd / 2)
        assertEquals(user, advanced)
        assertEquals(500, advanced.weeklyXp)
    }

    @Test
    fun `un bot gagne de l XP au fil de la semaine`() {
        val midWeek = weekStart + (weekEnd - weekStart) / 2
        val advanced = LeagueBotAnimator.advanceBot(bot(100), weekStart, weekEnd, midWeek)
        // À mi-semaine, le bot doit avoir avancé
        assertTrue(
            "XP à mi-semaine (${advanced.weeklyXp}) doit dépasser l'initial (0)",
            advanced.weeklyXp > 0
        )
    }

    @Test
    fun `un bot a son XP maximale en fin de semaine`() {
        val advanced = LeagueBotAnimator.advanceBot(bot(101), weekStart, weekEnd, weekEnd)
        // Ne dépasse jamais le rythme + marge 15 % (700 * 1.15 max par pace)
        assertTrue(advanced.weeklyXp <= 760 * 1.15)
    }

    @Test
    fun `le tirage est deterministe - deux appels donnent le meme XP`() {
        val now = weekStart + 3 * 24 * 60 * 60 * 1000L
        val a = LeagueBotAnimator.advanceBot(bot(102), weekStart, weekEnd, now)
        val b = LeagueBotAnimator.advanceBot(bot(102), weekStart, weekEnd, now)
        assertEquals(a.weeklyXp, b.weeklyXp)
    }

    @Test
    fun `deux bots ont des rythmes differents`() {
        // Sans ça, tous les bots monteraient au même rythme et le classement
        // serait figé — précisément le bug que l'on corrige.
        val now = weekStart + (weekEnd - weekStart) / 2
        val a = LeagueBotAnimator.advanceBot(bot(201), weekStart, weekEnd, now)
        val b = LeagueBotAnimator.advanceBot(bot(202), weekStart, weekEnd, now)
        // Pas obligatoirement différents (tirage), mais au moins un couple l'est
        // sur 10 bots : on vérifie que la cohorte n'est pas uniforme.
        val xps = (300..310).map {
            LeagueBotAnimator.advanceBot(bot(it), weekStart, weekEnd, now).weeklyXp
        }
        assertTrue("tous les bots ont le même XP — classement figé", xps.toSet().size > 1)
    }

    @Test
    fun `l XP ne descend jamais sous la valeur initiale`() {
        // Un bot qui a déjà gagné ne perd pas sa progression : c'est ce qui
        // rend l'animation crédible (un bot ne recule pas).
        val played = bot(300).copy(weeklyXp = 400)
        val advanced = LeagueBotAnimator.advanceBot(played, weekStart, weekEnd, weekStart + 1)
        assertTrue(
            "XP ${advanced.weeklyXp} < initial 400",
            advanced.weeklyXp >= 400
        )
    }

    @Test
    fun `advanceAll anime uniquement les bots`() {
        val user = bot(1).copy(isBot = false, isCurrentUser = true, weeklyXp = 250)
        val members = listOf(user, bot(400), bot(401), bot(402))
        val now = weekStart + (weekEnd - weekStart) / 2
        val advanced = LeagueBotAnimator.advanceAll(members, weekStart, weekEnd, now)

        assertEquals(4, advanced.size)
        // L'utilisateur est intact
        assertEquals(250, advanced.first { it.isCurrentUser }.weeklyXp)
        // Au moins un bot a bougé
        assertTrue(advanced.filter { it.isBot }.any { it.weeklyXp > 0 })
    }

    @Test
    fun `un appel hors semaine ne modifie rien`() {
        // Avant le début de la semaine : pas de progression.
        val before = LeagueBotAnimator.advanceBot(bot(500), weekStart, weekEnd, weekStart - 1000)
        assertEquals(0, before.weeklyXp)
    }

    @Test
    fun `les bots rattrapent l utilisateur - urgences hebdomadaire`() {
        // L'effet recherché : un joueur qui s'arrête de jouer est dépassé.
        // En début de semaine, l'utilisateur est 1er ; en fin de semaine, au
        // moins un bot le dépasse.
        val nowLate = weekStart + (weekEnd - weekStart) * 9 / 10
        val bots = (600..629).map { bot(it) }
        val advanced = LeagueBotAnimator.advanceAll(bots, weekStart, weekEnd, nowLate)
        val bestBot = advanced.maxOf { it.weeklyXp }
        assertTrue(
            "aucun bot ne dépasse 300 XP en fin de semaine ($bestBot) — pas d'urgence",
            bestBot > 300
        )
    }
}
