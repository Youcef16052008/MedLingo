package com.aistudio.medlingua.dzmed.domain.pilot

import kotlinx.serialization.Serializable

/**
 * P4.3 — Moteur de MESURE du pilote — port 1:1 de `web-react/src/pilot/measure.ts`.
 *
 * AUCUN feedback correctif pendant la mesure : l'enregistrement ne produit
 * aucun verdict affichable. Score objectif = correct / items valides
 * effectivement présentés ET traités, dénominateur explicite, par dimension.
 * Item ambigu = invalidé ET TRACÉ. Absence de test ≠ score nul. Le délai réel
 * T1→T2 est enregistré (jamais prétendu = 7 jours). Rien ici n'entre dans
 * SM-2 ni dans la progression d'entraînement.
 */

const val PILOT_MEASURE_SCHEMA_VERSION = 1

@Serializable
data class MeasureItemResult(
    val itemId: String,
    val conceptId: String,
    val dimension: String,
    /** null = présenté mais non traité (interruption) — jamais compté 0. */
    val correct: Boolean? = null,
    /** Item ambigu/invalide : raison tracée, exclu du dénominateur. */
    val invalidatedReason: String? = null,
    val answeredAt: Long? = null,
    /** Durée observée de réponse (ms) — passive, aucun chrono stressant. */
    val durationMs: Long? = null
)

@Serializable
data class MeasureSessionState(
    val schemaVersion: Int = PILOT_MEASURE_SCHEMA_VERSION,
    val id: String = "",
    val time: String, // T0 | T1 | T2
    /** Version parallèle réellement servie. */
    val version: String = "",
    val contentVersion: String = "",
    val conceptIds: List<String> = emptyList(),
    val startedAt: Long = 0L,
    val updatedAt: Long = 0L,
    val i: Int = 0,
    val results: List<MeasureItemResult> = emptyList(),
    val answeredItemId: String? = null,
    val finished: Boolean = false,
    /** Début interrompu avant la fin (état consigné, jamais un score nul). */
    val interrupted: Boolean = false,
    /** Pour T2 : délai RÉEL observé depuis T1 (ms), null si T1 introuvable. */
    val actualDelayFromT1Ms: Long? = null
)

/** Items de mesure d'une séance : concepts × version du temps demandé. */
fun measureItemsFor(manifest: MeasureManifest, conceptIds: List<String>, time: String): List<MeasureItem> {
    val version = TIME_TO_VERSION[time] ?: return emptyList()
    val byConcept = manifest.banks.filter { it.version == version }.associateBy { it.conceptId }
    return conceptIds.flatMap { cid -> byConcept[cid]?.items ?: emptyList() }
}

fun newMeasureId(time: String, now: Long): String {
    val rand = (0 until 0x10000).random().toString(16).padStart(4, '0')
    return "measure-$time-${pilotIsoDate(now)}-$rand"
}

fun initMeasureSession(
    time: String,
    conceptIds: List<String>,
    contentVersion: String,
    now: Long,
    previousFinishedAt: Long? = null
): MeasureSessionState {
    val version = TIME_TO_VERSION[time] ?: ""
    return MeasureSessionState(
        id = newMeasureId(time, now),
        time = time,
        version = version,
        contentVersion = contentVersion,
        conceptIds = conceptIds,
        startedAt = now,
        updatedAt = now,
        finished = conceptIds.isEmpty(),
        actualDelayFromT1Ms = if (time == "T2" && previousFinishedAt != null && previousFinishedAt <= now) {
            now - previousFinishedAt
        } else null
    )
}

/** Reprise : version de contenu identique exigée, sinon null (jamais un silence). */
fun resumeMeasureSession(raw: MeasureSessionState?, manifest: MeasureManifest, now: Long): MeasureSessionState? {
    if (raw == null) return null
    if (raw.schemaVersion != PILOT_MEASURE_SCHEMA_VERSION) return null
    if (raw.contentVersion != manifest.contentVersion) return null
    if (raw.finished) return null
    val expected = TIME_TO_VERSION[raw.time] ?: return null
    if (raw.version != expected) return null
    if (raw.conceptIds.isEmpty()) return null
    return raw.copy(updatedAt = now)
}

fun evaluateMeasureAnswer(item: MeasureItem, rawInput: String): Boolean {
    val user = pilotNormalizeAnswer(rawInput)
    if (user.isEmpty()) return false
    val candidates = listOf(item.answer) + item.acceptedVariants
    return candidates.any { pilotNormalizeAnswer(it) == user }
}

/**
 * Enregistre la réponse. IDEMPOTENT. Aucun verdict retourné : la mesure
 * n'affiche pas de correction (P3.1).
 */
fun recordMeasureAnswer(
    state: MeasureSessionState,
    item: MeasureItem,
    rawInput: String,
    now: Long,
    shownAt: Long
): MeasureSessionState {
    if (state.answeredItemId == item.id) return state
    val result = MeasureItemResult(
        itemId = item.id,
        conceptId = item.conceptId,
        dimension = item.dimension,
        correct = evaluateMeasureAnswer(item, rawInput),
        answeredAt = now,
        durationMs = if (shownAt <= now) now - shownAt else null
    )
    return state.copy(
        updatedAt = now,
        answeredItemId = item.id,
        results = state.results.filter { it.itemId != item.id } + result
    )
}

/** Invalide un item ambigu : TRACÉ avec raison, exclu du dénominateur. */
fun invalidateMeasureItem(
    state: MeasureSessionState,
    itemId: String,
    reason: String,
    now: Long
): MeasureSessionState {
    if (reason.isBlank()) return state
    val existing = state.results.firstOrNull { it.itemId == itemId }
    val result = existing?.copy(invalidatedReason = reason, correct = null, answeredAt = now)
        ?: MeasureItemResult(itemId = itemId, conceptId = "", dimension = "recognize", invalidatedReason = reason)
    return state.copy(
        updatedAt = now,
        answeredItemId = if (state.answeredItemId == itemId) null else state.answeredItemId,
        results = state.results.filter { it.itemId != itemId } + result
    )
}

/** Avance après enregistrement. */
fun advanceMeasureSession(state: MeasureSessionState, items: List<MeasureItem>, now: Long): MeasureSessionState {
    if (state.answeredItemId == null) return state
    return if (state.i + 1 < items.size) {
        state.copy(i = state.i + 1, answeredItemId = null, updatedAt = now)
    } else {
        state.copy(finished = true, answeredItemId = null, updatedAt = now)
    }
}

/** Interrompt la séance (état consigné — jamais transformé en score). */
fun interruptMeasureSession(state: MeasureSessionState, now: Long): MeasureSessionState =
    if (state.finished) state else state.copy(interrupted = true, updatedAt = now)

data class MeasureDimensionScore(
    val dimension: String,
    /** Items traités non invalidés — dénominateur EXPLICITE. */
    val presented: Int,
    val correct: Int,
    /** true seulement si presented > 0 : l'absence de test n'est pas un score nul. */
    val hasScore: Boolean
)

data class MeasureScore(
    val dimensions: List<MeasureDimensionScore>,
    val presented: Int,
    val correct: Int,
    val hasScore: Boolean,
    val invalidated: Int,
    val unanswered: Int
)

/** Score d'une séance : par dimension, dénominateur explicite, invalidés tracés à part. */
fun measureScore(state: MeasureSessionState): MeasureScore {
    val byDim = linkedMapOf<String, Pair<Int, Int>>()
    for (r in state.results) {
        if (r.invalidatedReason != null) continue // tracé ailleurs, hors dénominateur
        val c = r.correct ?: continue // présenté non traité : compté dans unanswered
        val p = byDim[r.dimension] ?: (0 to 0)
        byDim[r.dimension] = (p.first + 1) to (p.second + if (c) 1 else 0)
    }
    val dims = byDim.entries
        .map { MeasureDimensionScore(it.key, it.value.first, it.value.second, true) }
        .sortedBy { it.dimension }
    val presented = dims.sumOf { it.presented }
    val correct = dims.sumOf { it.correct }
    val invalidated = state.results.count { it.invalidatedReason != null }
    val unanswered = state.results.count { it.invalidatedReason == null && it.correct == null }
    return MeasureScore(dims, presented, correct, presented > 0, invalidated, unanswered)
}

/**
 * Le temps de mesure est-il disponible ? T1 exige T0 fini, T2 exige T1 fini.
 * Retourne null si disponible, sinon le motif.
 */
fun measureTimeBlocked(time: String, sessions: List<MeasureSessionState>): String? {
    fun finished(t: String) = sessions.firstOrNull { it.time == t && it.finished && !it.interrupted }
    return when (time) {
        "T0" -> null
        "T1" -> if (finished("T0") != null) null else "measureNeedT0"
        "T2" -> if (finished("T1") != null) null else "measureNeedT1"
        else -> "measureNeedT0"
    }
}

/** Dernière séance FINIE d'un temps donné (pour le délai réel T1→T2). */
fun finishedMeasureAt(sessions: List<MeasureSessionState>, time: String): Long? =
    sessions.firstOrNull { it.time == time && it.finished && !it.interrupted }?.updatedAt
