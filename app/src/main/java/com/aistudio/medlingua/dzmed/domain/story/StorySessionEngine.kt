package com.aistudio.medlingua.dzmed.domain.story

/**
 * Moteur de séance « histoire » — port 1:1 de `web-react/src/learning/session.ts`.
 *
 * Machine à états pure : reçoit une action, renvoie le nouvel état. Elle ne
 * déclenche jamais l'audio, ne lit pas l'horloge (`now` injecté) ni ne change
 * d'écran. Les scénarios de test partagés (`story-scenarios.json`) garantissent
 * que les versions Kotlin et TypeScript prennent les mêmes décisions.
 */

import kotlinx.serialization.Serializable
import com.aistudio.medlingua.dzmed.domain.sm2.SpacedRepetitionAlgorithm
import com.aistudio.medlingua.dzmed.domain.time.FakeClock

/** Étapes de la boucle, dans l'ordre (§6.2 du guide d'intégration). */
val STORY_STEPS = listOf(
    "REVIEW",
    "LISTEN_DISCOVER",
    "LISTEN_WITH_HELP",
    "LISTEN_CHECK",
    "CIRCLING",
    "PATTERN_PRACTICE",
    "RETELL",
    "TRANSFER_AND_SUMMARY"
)

typealias Step = String

@Serializable
data class SessionStats(
    val firstTryIds: List<String> = emptyList(),
    val mistakeIds: List<String> = emptyList(),
    val helpedIds: List<String> = emptyList(),
    val difficultPhraseIds: List<String> = emptyList()
)

data class SessionState(
    val storyId: String,
    val contentVersion: Int,
    val step: Step,
    val reviewIds: List<String> = emptyList(),
    val reviewIndex: Int = 0,
    val qIndex: Int = 0,
    val drillIndex: Int = 0,
    val revealed: Boolean = false,
    val lastCorrect: Boolean? = null,
    val attemptsOnCurrent: Int = 0,
    val stats: SessionStats = SessionStats(),
    /** Bloc REVIEW : phraseId → « retenu » (RISK-001 : fusion dans la planification finale). */
    val reviewRatings: Map<String, Boolean> = emptyMap(),
    val retellAttempted: Boolean = false,
    val transferTaskAttempted: Boolean = false,
    val transferTaskPending: Boolean = false,
    val finished: Boolean = false
)

sealed class Action {
    object Advance : Action()
    data class SubmitAnswer(val value: String) : Action()
    data class SubmitDrill(val value: String) : Action()
    object Reveal : Action()
    object Help : Action()
    data class ReviewRated(val phraseId: String, val remembered: Boolean) : Action()
    data class RetellDone(val attempted: Boolean) : Action()
    data class TransferTaskDone(val attempted: Boolean) : Action()
}

/**
 * Normalisation des réponses courtes : casse, ponctuation finale, espaces,
 * apostrophes typographiques. Les négations et les nombres sont préservés —
 * ne jamais transformer une réponse pour la faire passer (§9 du guide).
 */
fun normalizeAnswer(s: String): String =
    s.lowercase()
        .replace("\u2019", "'")
        .replace("\u02BC", "'")
        .replace(Regex("[.,!?;:]+"), " ")
        .replace(Regex("\\s+"), " ")
        .trim()

/** Égalité stricte (après normalisation) avec une des réponses acceptées. */
fun checkAccepted(userAnswer: String, accepted: List<String>): Boolean {
    val user = normalizeAnswer(userAnswer)
    if (user.isEmpty()) return false
    return accepted.any { normalizeAnswer(it) == user }
}

fun initSession(story: Story, duePhraseIds: List<String> = emptyList()): SessionState {
    val due = duePhraseIds.filter { id -> story.targetPhrases.any { it.id == id } }
    return SessionState(
        storyId = story.storyId,
        contentVersion = story.contentVersion,
        step = if (due.isNotEmpty()) "REVIEW" else "LISTEN_DISCOVER",
        reviewIds = due
    )
}

/** Question courante selon l'étape (circling ou transfert). */
fun currentQuestion(state: SessionState, story: Story): StoryQuestion? = when {
    state.step == "CIRCLING" -> story.questions.getOrNull(state.qIndex)
    state.step == "TRANSFER_AND_SUMMARY" && !state.transferTaskPending ->
        story.transfer.questions.getOrNull(state.qIndex)
    else -> null
}

fun currentDrill(state: SessionState, story: Story): DrillItem? =
    if (state.step == "PATTERN_PRACTICE") story.drill.items.getOrNull(state.drillIndex) else null

/**
 * Idempotent par question (REGRESS-1) : après une reprise, `attemptsOnCurrent`
 * repart à zéro alors que le verdict initial est déjà compté.
 */
private fun recordVerdict(state: SessionState, id: String, correct: Boolean): SessionStats {
    val already = id in state.stats.firstTryIds || id in state.stats.mistakeIds
    val firstAttempt = state.attemptsOnCurrent == 0 && !already
    return if (firstAttempt) {
        if (correct) state.stats.copy(firstTryIds = state.stats.firstTryIds + id)
        else state.stats.copy(mistakeIds = state.stats.mistakeIds + id)
    } else state.stats
}

private fun markPhrasesDifficult(stats: SessionStats, phraseIds: List<String>): SessionStats =
    stats.copy(difficultPhraseIds = (stats.difficultPhraseIds + phraseIds).distinct())

/** Réduit l'état ; toute action invalide pour l'étape courante renvoie l'état tel quel. */
fun sessionReducer(state: SessionState, action: Action, story: Story): SessionState {
    when (action) {
        is Action.Advance -> {
            var s = state
            when (state.step) {
                "REVIEW" -> {
                    if (s.reviewIndex + 1 < s.reviewIds.size) return s.copy(reviewIndex = s.reviewIndex + 1)
                    return s.copy(step = "LISTEN_DISCOVER")
                }
                "LISTEN_DISCOVER" -> return s.copy(step = "LISTEN_WITH_HELP")
                "LISTEN_WITH_HELP" -> return s.copy(step = "LISTEN_CHECK")
                "LISTEN_CHECK" -> return s.copy(step = "CIRCLING", qIndex = 0, revealed = false, lastCorrect = null, attemptsOnCurrent = 0)
                "CIRCLING" -> {
                    if (s.qIndex + 1 < story.questions.size) {
                        return s.copy(qIndex = s.qIndex + 1, revealed = false, lastCorrect = null, attemptsOnCurrent = 0)
                    }
                    return s.copy(step = "PATTERN_PRACTICE", drillIndex = 0, revealed = false, lastCorrect = null, attemptsOnCurrent = 0)
                }
                "PATTERN_PRACTICE" -> {
                    if (s.drillIndex + 1 < story.drill.items.size) {
                        return s.copy(drillIndex = s.drillIndex + 1, revealed = false, lastCorrect = null, attemptsOnCurrent = 0)
                    }
                    return s.copy(step = "RETELL")
                }
                "RETELL" -> return s.copy(step = "TRANSFER_AND_SUMMARY", qIndex = 0, revealed = false, lastCorrect = null, attemptsOnCurrent = 0)
                "TRANSFER_AND_SUMMARY" -> {
                    if (s.qIndex + 1 < story.transfer.questions.size) {
                        return s.copy(qIndex = s.qIndex + 1, revealed = false, lastCorrect = null, attemptsOnCurrent = 0)
                    }
                    if (!s.transferTaskPending) {
                        return s.copy(transferTaskPending = true, revealed = false, lastCorrect = null)
                    }
                    return s.copy(finished = true)
                }
            }
            return s
        }

        is Action.SubmitAnswer -> {
            val q = currentQuestion(state, story) ?: return state
            if (state.lastCorrect == true) return state
            val correct = checkAccepted(action.value, q.accepted)
            var stats = recordVerdict(state, q.id, correct)
            if (!correct && state.attemptsOnCurrent == 0) stats = markPhrasesDifficult(stats, q.phraseIds)
            return state.copy(
                stats = stats,
                revealed = true,
                lastCorrect = correct,
                attemptsOnCurrent = state.attemptsOnCurrent + 1
            )
        }

        is Action.SubmitDrill -> {
            val d = currentDrill(state, story) ?: return state
            if (state.lastCorrect == true) return state
            val correct = checkAccepted(action.value, d.accepted)
            val stats = recordVerdict(state, d.id, correct)
            return state.copy(
                stats = stats,
                revealed = true,
                lastCorrect = correct,
                attemptsOnCurrent = state.attemptsOnCurrent + 1
            )
        }

        is Action.Reveal -> {
            // Pas d'interface commune entre StoryQuestion et DrillItem : on
            // extrait l'id sans elvis entre types frères (LUB = Any sinon).
            val id = currentQuestion(state, story)?.id ?: currentDrill(state, story)?.id ?: return state
            if (state.revealed) return state
            // « Je ne sais pas encore » : activité réalisée avec aide — ni réussite
            // sans aide ni erreur de compréhension si aucune tentative n'a eu lieu.
            val helped = if (id in state.stats.helpedIds) state.stats.helpedIds else state.stats.helpedIds + id
            return state.copy(
                stats = state.stats.copy(helpedIds = helped),
                revealed = true,
                lastCorrect = null,
                attemptsOnCurrent = state.attemptsOnCurrent + 1
            )
        }

        is Action.Help -> {
            val id = currentQuestion(state, story)?.id ?: currentDrill(state, story)?.id ?: return state
            if (id in state.stats.helpedIds) return state
            // Une réussite après traduction affichée n'est pas une réussite sans aide.
            return state.copy(
                stats = state.stats.copy(
                    firstTryIds = state.stats.firstTryIds - id,
                    helpedIds = state.stats.helpedIds + id
                )
            )
        }

        is Action.ReviewRated -> {
            // Le verdict est aussi écrit immédiatement en base par l'UI (survit à
            // une interruption) ; l'état moteur le conserve pour la planification finale.
            return state.copy(reviewRatings = state.reviewRatings + (action.phraseId to action.remembered))
        }

        is Action.RetellDone -> return state.copy(retellAttempted = state.retellAttempted || action.attempted)

        is Action.TransferTaskDone -> return state.copy(transferTaskAttempted = state.transferTaskAttempted || action.attempted)
    }
}

/* ---- Reprise après interruption ---- */

data class SavedSession(
    val contentVersion: Int,
    val step: Step,
    val qIndex: Int,
    val drillIndex: Int,
    val reviewIndex: Int,
    val stats: SessionStats,
    val reviewRatings: Map<String, Boolean>,
    val retellAttempted: Boolean,
    val transferTaskAttempted: Boolean,
    val transferTaskPending: Boolean
)

/**
 * Reconstruit une séance interrompue — miroir de `resumeSession` (web).
 * Les expressions dues sont recalculées : `reviewIndex` est recalé, jamais
 * hors bornes.
 */
fun resumeSession(story: Story, saved: SavedSession, due: List<String>): SessionState {
    val base = initSession(story, duePhraseIds = due)
    var s = base.copy(
        step = saved.step,
        qIndex = minOf(saved.qIndex, maxOf(0, story.questions.size - 1)),
        drillIndex = minOf(saved.drillIndex, maxOf(0, story.drill.items.size - 1)),
        reviewIndex = saved.reviewIndex,
        stats = saved.stats,
        reviewRatings = saved.reviewRatings,
        retellAttempted = saved.retellAttempted,
        transferTaskAttempted = saved.transferTaskAttempted,
        transferTaskPending = saved.transferTaskPending,
        revealed = false,
        lastCorrect = null,
        attemptsOnCurrent = 0
    )
    if (s.step == "REVIEW") {
        if (base.reviewIds.isEmpty()) {
            s = s.copy(step = "LISTEN_DISCOVER", reviewIndex = 0)
        } else {
            s = s.copy(reviewIndex = minOf(saved.reviewIndex, base.reviewIds.size - 1))
        }
    }
    if (s.step == "TRANSFER_AND_SUMMARY") {
        s = if (s.transferTaskPending) s.copy(qIndex = 0)
        else s.copy(qIndex = minOf(saved.qIndex, maxOf(0, story.transfer.questions.size - 1)))
    }
    return s
}

/* ---- Mémoire d'expressions — SM-2 dans un espace séparé des termes ---- */

/** État SM-2 d'une expression — miroir de `Sched` (web). */
data class PhraseSched(
    val rep: Int = 0,
    val ef: Double = 2.5,
    val iv: Int = 1,
    val due: Long = 0L
)

typealias ExprScheds = Map<String, PhraseSched>

/** Adaptateur horloge injectée vers `SpacedRepetitionAlgorithm` (champs plats). */
private fun sm2NextExpr(quality: Int, prev: PhraseSched?, now: Long): PhraseSched {
    val base = prev ?: PhraseSched()
    val r = SpacedRepetitionAlgorithm.calculateNext(
        quality, base.rep, base.ef, base.iv, FakeClock(now)
    )
    return PhraseSched(r.repetitions, r.easeFactor, r.intervalDays, r.nextReviewTimestamp)
}

/** Écho de `isDue` (web) — public : partagé avec la sélection de séance. */
fun isDueExpr(s: PhraseSched?, now: Long): Boolean = s != null && s.due <= now

/** Une expression jamais vue n'est pas « due » : elle sera découverte dans la séance. */
fun duePhrases(story: Story, sm2: ExprScheds, now: Long): List<String> =
    story.targetPhrases.filter { isDueExpr(sm2[it.id], now) }.map { it.id }

/**
 * Qualité = la plus sévère entre REVIEW et circling (RISK-001) :
 * oubliée en révision OU difficile → 2 ; retenue → 4 ; jamais révisée et réussie → 5.
 */
fun scheduleFromSession(state: SessionState, story: Story, prev: ExprScheds, now: Long): ExprScheds {
    val out = prev.toMutableMap()
    for (p in story.targetPhrases) {
        val difficult = p.id in state.stats.difficultPhraseIds
        val rating = state.reviewRatings[p.id]
        val quality = when {
            difficult || rating == false -> 2
            rating == true -> 4
            else -> 5
        }
        out[p.id] = sm2NextExpr(quality, out[p.id], now)
    }
    return out
}

/** Note une révision du bloc REVIEW (souvenu → 4, oublié → 2). */
fun scheduleReview(phraseId: String, remembered: Boolean, prev: ExprScheds, now: Long): ExprScheds =
    prev + (phraseId to sm2NextExpr(if (remembered) 4 else 2, prev[phraseId], now))

/** Résumé — distingue compréhension, aide et production. */
data class SessionSummary(
    val total: Int,
    val firstTry: Int,
    val helped: Int,
    val mistakes: Int,
    val difficultPhrases: List<String>,
    val retellAttempted: Boolean,
    val transferAttempted: Boolean
)

fun sessionSummary(state: SessionState, story: Story): SessionSummary {
    val total = story.questions.size + story.drill.items.size + story.transfer.questions.size
    return SessionSummary(
        total = total,
        firstTry = state.stats.firstTryIds.size,
        helped = state.stats.helpedIds.size,
        mistakes = state.stats.mistakeIds.size,
        difficultPhrases = state.stats.difficultPhraseIds,
        retellAttempted = state.retellAttempted,
        transferAttempted = state.transferTaskAttempted
    )
}
