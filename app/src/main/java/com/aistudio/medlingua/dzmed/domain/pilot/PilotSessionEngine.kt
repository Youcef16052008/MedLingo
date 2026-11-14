package com.aistudio.medlingua.dzmed.domain.pilot

import kotlinx.serialization.Serializable

/**
 * P4.3 — Moteur de séance du pilote — port 1:1 de `web-react/src/pilot/session.ts`
 * (pattern `StorySessionEngine`) : état sérialisable, horloge INJECTÉE, verdict
 * idempotent (double clic = même état), séparation premier essai / avec aide /
 * erreur, l'aide NE transforme JAMAIS une réponse en réussite autonome.
 *
 * Aucune récompense de l'app principale n'est distribuée ici ; aucun SM-2.
 */

const val PILOT_SESSION_SCHEMA_VERSION = 1
/** Nombre d'exercices par concept (manifeste v1). */
const val PILOT_EXERCISES_PER_CONCEPT = 3
/** Séance courte : au plus 3 concepts. */
const val PILOT_CONCEPTS_PER_SESSION = 3

@Serializable
data class PilotSessionState(
    val schemaVersion: Int = PILOT_SESSION_SCHEMA_VERSION,
    val id: String = "",
    val startedAt: Long = 0L,
    val updatedAt: Long = 0L,
    val contentVersion: String = "",
    val conceptIds: List<String> = emptyList(),
    val i: Int = 0,
    val exIdx: Int = 0,
    /** exerciseId → nombre total de tentatives (y compris ratées). */
    val attempts: Map<String, Int> = emptyMap(),
    /** exerciseId → PREMIER résultat (avant toute correction). */
    val firstResults: Map<String, String> = emptyMap(), // first_try | helped | error
    /** exerciseIds corrects sans aucune aide (réussites autonomes). */
    val autonomous: List<String> = emptyList(),
    /** exerciseIds dont l'aide a été consultée avant la réponse. */
    val helped: List<String> = emptyList(),
    /** exerciseIds ratés au moins une fois (→ « révisions proposées »). */
    val errors: List<String> = emptyList(),
    /** Id de l'exercice en attente de correction (garde anti double-soumission). */
    val answeredExerciseId: String? = null,
    val finished: Boolean = false
)

/**
 * Égalité de réponse : normalisation stricte (casse, ponctuation, accents),
 * variantes acceptées explicites seulement — ne jamais transformer une
 * réponse pour la faire passer. Identique à web (`fillScore === 1`).
 */
fun evaluatePilotAnswer(exercise: PilotExercise, rawInput: String): Boolean {
    val user = pilotNormalizeAnswer(rawInput)
    if (user.isEmpty()) return false
    val candidates = listOf(exercise.answer) + exercise.acceptedVariants
    return candidates.any { pilotNormalizeAnswer(it) == user }
}

fun newPilotSessionId(now: Long): String {
    val rand = (0 until 0x10000).random().toString(16).padStart(4, '0')
    return "pilot-${pilotIsoDate(now)}-$rand"
}

fun initPilotSession(
    concepts: List<PilotConcept>,
    contentVersion: String,
    now: Long,
    sessionId: String = newPilotSessionId(now)
): PilotSessionState {
    val ids = concepts.take(PILOT_CONCEPTS_PER_SESSION).map { it.id }
    return PilotSessionState(
        id = sessionId,
        startedAt = now,
        updatedAt = now,
        contentVersion = contentVersion,
        conceptIds = ids,
        finished = ids.isEmpty()
    )
}

/**
 * Reprise : la séance n'est reprise que si sa version de contenu correspond
 * AU manifeste courant (changement de version = invalidé, jamais un silence)
 * et que ses concepts existent encore. Retourne `null` sinon.
 */
fun resumePilotSession(raw: PilotSessionState?, manifest: PilotManifest, now: Long): PilotSessionState? {
    if (raw == null) return null
    if (raw.schemaVersion != PILOT_SESSION_SCHEMA_VERSION) return null
    if (raw.contentVersion != manifest.contentVersion) return null
    if (raw.finished) return null
    if (raw.conceptIds.isEmpty()) return null
    val known = manifest.concepts.map { it.id }.toSet()
    if (!raw.conceptIds.all { known.contains(it) }) return null
    return raw.copy(updatedAt = now)
}

/**
 * Enregistre la réponse. IDEMPOTENT : si l'exercice est déjà corrigé et pas
 * encore avancé, l'état est inchangé. L'aide consultée ⇒ jamais `first_try`.
 */
fun recordPilotAnswer(state: PilotSessionState, exercise: PilotExercise, rawInput: String, now: Long): PilotSessionState {
    if (state.answeredExerciseId == exercise.id) return state
    val correct = evaluatePilotAnswer(exercise, rawInput)
    val wasHelped = state.helped.contains(exercise.id)
    val firstResult = when {
        correct && !wasHelped -> "first_try"
        correct -> "helped"
        else -> "error"
    }
    return state.copy(
        updatedAt = now,
        answeredExerciseId = exercise.id,
        attempts = state.attempts + (exercise.id to (state.attempts[exercise.id] ?: 0) + 1),
        firstResults = state.firstResults + (exercise.id to firstResult),
        autonomous = if (correct && !wasHelped) (state.autonomous + exercise.id).distinct() else state.autonomous,
        helped = if (wasHelped) (state.helped + exercise.id).distinct() else state.helped,
        errors = if (!correct) (state.errors + exercise.id).distinct()
        else state.errors - exercise.id
    )
}

/** L'aide est consultée AVANT de répondre : la réponse ne sera jamais autonome. */
fun usePilotHelp(state: PilotSessionState, exerciseId: String, now: Long): PilotSessionState {
    if (state.answeredExerciseId == exerciseId) return state
    if (state.helped.contains(exerciseId)) return state
    return state.copy(helped = (state.helped + exerciseId).distinct(), updatedAt = now)
}

/** Avance d'un exercice (clic « Continuer » après feedback). */
fun advancePilotSession(state: PilotSessionState, manifest: PilotManifest, now: Long): PilotSessionState {
    if (state.answeredExerciseId == null) return state
    val concept = manifest.concepts.firstOrNull { it.id == state.conceptIds.getOrNull(state.i) }
        ?: return state.copy(finished = true, updatedAt = now)
    return when {
        state.exIdx + 1 < concept.exercises.size ->
            state.copy(exIdx = state.exIdx + 1, answeredExerciseId = null, updatedAt = now)
        state.i + 1 < state.conceptIds.size ->
            state.copy(i = state.i + 1, exIdx = 0, answeredExerciseId = null, updatedAt = now)
        else -> state.copy(finished = true, answeredExerciseId = null, updatedAt = now)
    }
}

/** Nombre total d'étapes affichables de la séance. */
fun pilotTotalSteps(state: PilotSessionState, manifest: PilotManifest): Int =
    state.conceptIds.sumOf { id ->
        manifest.concepts.firstOrNull { it.id == id }?.exercises?.size ?: 0
    }

/** Étape courante (1-based) pour l'affichage. */
fun pilotCurrentStepNumber(state: PilotSessionState, manifest: PilotManifest): Int {
    var n = 0
    for (k in 0 until state.i) {
        val c = manifest.concepts.firstOrNull { it.id == state.conceptIds[k] }
        n += c?.exercises?.size ?: 0
    }
    return n + state.exIdx + 1
}
