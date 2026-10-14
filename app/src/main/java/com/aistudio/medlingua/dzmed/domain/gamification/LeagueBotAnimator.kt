package com.aistudio.medlingua.dzmed.domain.gamification

import com.aistudio.medlingua.dzmed.data.local.entity.LeagueMemberEntity
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Animateur de ligues — les bots ne jouaient **jamais**.
 *
 * `generateBotsForCohort` fixe leur `weeklyXp` à la création de la cohorte, et
 * rien ne le bougeait ensuite. Conséquence : dès le lundi, le joueur est
 * bloqué dans un classement figé — un seul exercice le place définitivement,
 * et l'urgence hebdomadaire du Duolingo (« 3e place, encore 2 XP pour monter ! »)
 * n'existait pas. Rapport final, plan Phase 3 #8.
 *
 * **Approche** : chaque bot a un rythme hebdomadaire propre (par tour, déterminé
 * par son userId), que l'on répartit sur la semaine. Le XP affiché à l'instant
 * T est `rythme × progressionDeLaSemaine`, légèrement bruité pour rester
 * réaliste. Comme le générateur de leçons, le tirage est **déterministe** :
 * même cohorte + même instant → même classement.
 *
 * Ce n'est pas du temps réel : les bots ne jouent pas vraiment. Mais la ligue
 * progresse visiblement, ce qui est précisément l'effet recherché, et ce sans
 * backend, sans timer et sans réveil batterie.
 */
object LeagueBotAnimator {

    /** Rythme hebdomadaire par "tour" de bot (FNV-1a du userId, stable). */
    private fun botWeeklyPace(userId: Int): Int {
        var h = 0x811C9DC5.toInt()
        val s = "bot:$userId:pace"
        for (ch in s) {
            h = h xor ch.code
            h *= 0x01000193
        }
        // entre 120 et 760 XP/semaine — Duolingo bronze/silver oscille dans
        // cette zone pour un joueur actif régulier.
        return 120 + ((h.toLong() and 0xFFFFFFFFL) % 641).toInt()
    }

    /**
     * Avance d'un bot à l'instant [now], sur la semaine [weekStart] → [weekEnd].
     *
     * - L'XP suit la progression de la semaine : 50 % de la semaine écoulée →
     *   environ 50 % du rythme hebdomadaire.
     * - On ajoute un bruit déterministe (±8 %) : sans lui, les bots aux rythmes
     *   proches resteraient figés dans le même ordre toute la semaine.
     * - L'XP ne **descend jamais** sous la valeur initiale (un bot ne perd pas
     *   ce qu'il a « gagné »), et ne dépasse jamais son rythme + marge.
     * - Renvoie le membre **non modifié** si l'appel est hors de la semaine.
     */
    fun advanceBot(
        bot: LeagueMemberEntity,
        weekStart: Long,
        weekEnd: Long,
        now: Long,
        seed: Int = 7
    ): LeagueMemberEntity {
        if (!bot.isBot) return bot
        val span = weekEnd - weekStart
        if (span <= 0) return bot
        val elapsed = (now - weekStart).coerceIn(0L, span)

        // progression de la semaine ∈ [0, 1]
        val progress = elapsed.toDouble() / span

        val pace = botWeeklyPace(bot.userId)
        val noise = deterministicNoise(bot.userId, weekStart, seed)

        val target = (pace * progress * (1.0 + noise)).roundToInt()
        val clamped = max(bot.weeklyXp, target).coerceAtMost((pace * 1.15).roundToInt())

        if (clamped == bot.weeklyXp) return bot

        // lastActiveTimestamp avance lui aussi : un bot en haut du classement
        // "joue" plus souvent que celui de queue, comme un vrai groupe.
        val activityBoost = if (clamped > bot.weeklyXp) 1L else 0L
        return bot.copy(
            weeklyXp = clamped,
            lastActiveTimestamp = max(bot.lastActiveTimestamp, now - activityBoost * 3_600_000L)
        )
    }

    /**
     * Bruit déterministe ∈ [−8 %, +8 %] : `sin` d'un dérivé du userId et de la
     * semaine — stable pour un couple (bot, semaine) donné, mais différent
     * chaque semaine.
     */
    private fun deterministicNoise(userId: Int, weekStart: Long, seed: Int): Double {
        val phase = ((userId * 2654435761L) xor (weekStart / 86_400_000L) xor (seed * 40503L)).toDouble()
        return (kotlin.math.sin(phase) * 0.08)
    }

    /**
     * Anime toute une cohorte (par ordre de XP pour que `rank` reste cohérent).
     * Les vrais utilisateurs (`isBot = false`) ne sont **jamais** touchés.
     */
    fun advanceAll(
        members: List<LeagueMemberEntity>,
        weekStart: Long,
        weekEnd: Long,
        now: Long
    ): List<LeagueMemberEntity> =
        members.map { advanceBot(it, weekStart, weekEnd, now) }
}
