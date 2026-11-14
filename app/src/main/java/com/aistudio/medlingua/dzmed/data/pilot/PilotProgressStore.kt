package com.aistudio.medlingua.dzmed.data.pilot

import android.content.SharedPreferences
import com.aistudio.medlingua.dzmed.domain.pilot.PilotConcept
import com.aistudio.medlingua.dzmed.domain.pilot.PilotManifest
import com.aistudio.medlingua.dzmed.domain.pilot.PilotSessionState
import com.aistudio.medlingua.dzmed.domain.pilot.pilotIsoDate
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * P4.3 — Persistance ISOLÉE du pilote Anatomie — port de
 * `web-react/src/pilot/storage.ts` (P2.3).
 *
 * - SharedPreferences nommé `medlingo_pilot_anatomy_v1` (+ `_backup`) : le
 *   store historique de l'app (Room `medlingua_dz.db`, SM-2, quêtes) n'est
 *   JAMAIS touché — pas de migration Room créée : l'état pilote est une petite
 *   blob JSON isolée, le besoin réel d'une table n'existe pas (décision
 *   documentée, plan P4 « vérifier le besoin réel d'une migration Room »).
 * - MÊME format JSON que le web (schemaVersion 1, mêmes champs) : un export
 *   web peut être importé sur Android et inversement.
 * - Corrompu / mauvais schéma / autre version de contenu : message explicite,
 *   jamais un « enregistré » mensonger (retours typés, pas de throw).
 * - Les ids pilote sont TEXTUELS : rien n'entre dans un contrat numérique.
 */

/** Nom du fichier SharedPreferences — identique à la clé web. */
const val PILOT_PREFS = "medlingo_pilot_anatomy_v1"
const val PILOT_PREFS_BACKUP = "medlingo_pilot_anatomy_v1_backup"
private const val KEY_STATE = "state"

/** Statistiques agrégées par exercice (champs exigés par le plan P2.3). */
@Serializable
data class PilotExerciseStat(
    val conceptId: String,
    val exerciseId: String,
    val attempts: Int,
    val firstResult: String, // first_try | helped | error
    val helped: Boolean,
    val lastAt: Long
)

@Serializable
data class PilotPersistedState(
    val schemaVersion: Int = 1,
    val updatedAt: Long = 0L,
    /** Version de contenu avec laquelle ces stats ont été produites. */
    val contentVersion: String? = null,
    /** Dernière séance (état de séance pour reprise). */
    val lastSession: PilotSessionState? = null,
    /** Statistiques par exercice — clés TEXTUELLES, jamais numériques. */
    val exerciseStats: Map<String, PilotExerciseStat> = emptyMap()
)

sealed class PilotSaveResult {
    data object Ok : PilotSaveResult()
    data class Failed(val error: String) : PilotSaveResult() // pilotStorageQuota | pilotStorageUnavailable
}

sealed class PilotImportResult {
    data class Ok(val state: PilotPersistedState, val note: String?) : PilotImportResult()
    data class Failed(val error: String) : PilotImportResult() // pilotImportInvalid | pilotImportSchema
}

private val json = Json { ignoreUnknownKeys = true }
private const val EXPORT_KIND = "medlingo-pilot-anatomy-progress"
private const val STATE_SCHEMA = 1

/** Revivification défensive : un champ illisible est jeté, jamais un crash. */
private fun coerceSession(raw: PilotSessionState?, manifest: PilotManifest, now: Long): PilotSessionState? {
    if (raw == null) return null
    if (raw.schemaVersion != 1) return null
    if (raw.contentVersion != manifest.contentVersion) return null // autre version → invalidée
    if (raw.finished) return raw
    if (raw.conceptIds.isEmpty()) return null
    val known = manifest.concepts.map { it.id }.toSet()
    if (!raw.conceptIds.all { known.contains(it) }) return null
    return raw.copy(updatedAt = now)
}

private fun coerceStats(v: Map<String, PilotExerciseStat>): Map<String, PilotExerciseStat> =
    v.filterValues { s ->
        s.conceptId.isNotBlank() && s.exerciseId.isNotBlank() &&
            s.firstResult in setOf("first_try", "helped", "error") && s.attempts >= 0
    }

/** Charge l'état persisté. Ne lève JAMAIS : état sain + warning (clé i18n). */
fun loadPilotState(prefs: SharedPreferences, manifest: PilotManifest, now: Long): Pair<PilotPersistedState, String?> {
    val raw = try {
        prefs.getString(KEY_STATE, null)
    } catch (_: Exception) {
        return PilotPersistedState() to "pilotStorageUnavailable"
    } ?: return PilotPersistedState() to null
    val parsed = try {
        json.decodeFromString<PilotPersistedState>(raw)
    } catch (_: Exception) {
        return PilotPersistedState(updatedAt = now) to "pilotDataCorrupted"
    }
    if (parsed.schemaVersion != STATE_SCHEMA) {
        // Pas de migration silencieuse pour le pilote v1 : message explicite.
        return PilotPersistedState(updatedAt = now) to "pilotSchemaChanged"
    }
    return parsed.copy(
        updatedAt = if (parsed.updatedAt > 0) parsed.updatedAt else now,
        lastSession = coerceSession(parsed.lastSession, manifest, now),
        exerciseStats = coerceStats(parsed.exerciseStats)
    ) to null
}

/** Sauvegarde. Code d'erreur explicite — jamais de promesse mensongère. */
fun savePilotState(prefs: SharedPreferences, state: PilotPersistedState): PilotSaveResult {
    return try {
        val ok = prefs.edit().putString(KEY_STATE, json.encodeToString(PilotPersistedState.serializer(), state)).commit()
        if (ok) PilotSaveResult.Ok else PilotSaveResult.Failed("pilotStorageUnavailable")
    } catch (e: Exception) {
        PilotSaveResult.Failed(if (isQuota(e)) "pilotStorageQuota" else "pilotStorageUnavailable")
    }
}

private fun isQuota(e: Exception): Boolean =
    e.message?.contains("quota", ignoreCase = true) == true

/** Fusionne le résultat d'un exercice dans les stats persistées. */
fun mergePilotExerciseStat(
    state: PilotPersistedState,
    stat: PilotExerciseStat,
    now: Long
): PilotPersistedState {
    val prev = state.exerciseStats[stat.exerciseId]
    val merged = if (prev != null) prev.copy(attempts = prev.attempts + stat.attempts, lastAt = stat.lastAt) else stat
    return state.copy(updatedAt = now, exerciseStats = state.exerciseStats + (stat.exerciseId to merged))
}

/* ------------------------------ export / import ------------------------------ */

/** Export JSON versionné (transmission MANUELLE, consentement à l'écran). */
fun exportPilotJson(state: PilotPersistedState): String = json.encodeToString(
    kotlinx.serialization.json.JsonObject.serializer(),
    kotlinx.serialization.json.buildJsonObject {
        put("kind", kotlinx.serialization.json.JsonPrimitive(EXPORT_KIND))
        put("exportedAt", kotlinx.serialization.json.JsonPrimitive(pilotIsoDate(System.currentTimeMillis())))
        put("state", json.encodeToJsonElement(PilotPersistedState.serializer(), state))
    }
)

/** Importe un export (mêmes règles que le web). Version différente : séance jetée + note. */
fun importPilotJson(text: String, manifest: PilotManifest, now: Long): PilotImportResult {
    val obj = try {
        json.parseToJsonElement(text).let { it as? kotlinx.serialization.json.JsonObject }
    } catch (_: Exception) {
        null
    } ?: return PilotImportResult.Failed("pilotImportInvalid")
    if ((obj["kind"] as? kotlinx.serialization.json.JsonPrimitive)?.content != EXPORT_KIND) {
        return PilotImportResult.Failed("pilotImportInvalid")
    }
    val inner = (obj["state"] as? kotlinx.serialization.json.JsonObject)
        ?: return PilotImportResult.Failed("pilotImportInvalid")
    val state = try {
        json.decodeFromJsonElement(PilotPersistedState.serializer(), inner)
    } catch (_: Exception) {
        return PilotImportResult.Failed("pilotImportInvalid")
    }
    if (state.schemaVersion != STATE_SCHEMA) return PilotImportResult.Failed("pilotImportSchema")
    val contentVersion = state.contentVersion
    return if (contentVersion != manifest.contentVersion) {
        // Les ids restent comparables, mais la séance EST invalidée (le contenu a pu changer).
        PilotImportResult.Ok(
            PilotPersistedState(updatedAt = now, contentVersion = contentVersion, exerciseStats = coerceStats(state.exerciseStats)),
            "pilotImportVersion:${contentVersion ?: "null"}"
        )
    } else {
        PilotImportResult.Ok(
            state.copy(
                updatedAt = now,
                lastSession = coerceSession(state.lastSession, manifest, now),
                exerciseStats = coerceStats(state.exerciseStats)
            ),
            null
        )
    }
}

/**
 * Applique un import avec confirmation (décidée à l'écran) : sauvegarde de
 * l'état courant d'abord ; échec de sauvegarde = import REFUSÉ (pas de
 * remplacement sans filet).
 */
fun applyPilotImport(
    prefs: SharedPreferences,
    backupPrefs: SharedPreferences,
    current: PilotPersistedState,
    imported: PilotPersistedState
): PilotSaveResult {
    val backup = savePilotState(backupPrefs, current)
    if (backup is PilotSaveResult.Failed) return backup
    return savePilotState(prefs, imported)
}

/** Restaure la sauvegarde précédente (après import indésirable). */
fun restorePilotBackup(
    prefs: SharedPreferences,
    backupPrefs: SharedPreferences,
    manifest: PilotManifest,
    now: Long
): Pair<PilotPersistedState, String?> {
    val raw = try {
        backupPrefs.getString(KEY_STATE, null)
    } catch (_: Exception) {
        return PilotPersistedState() to "pilotStorageUnavailable"
    }
    if (raw == null) return PilotPersistedState() to "pilotNoBackup"
    return try {
        prefs.edit().putString(KEY_STATE, raw).commit()
        loadPilotState(prefs, manifest, now)
    } catch (_: Exception) {
        PilotPersistedState() to "pilotStorageUnavailable"
    }
}

/** Reset PILOTE uniquement — ne touche jamais au store global ni au Room. */
fun resetPilotState(prefs: SharedPreferences, backupPrefs: SharedPreferences): PilotSaveResult {
    return try {
        prefs.edit().clear().commit()
        backupPrefs.edit().clear().commit()
        PilotSaveResult.Ok
    } catch (_: Exception) {
        PilotSaveResult.Failed("pilotStorageUnavailable")
    }
}

/** Exercices présentables d'un concept (le verrou s'applique en amont). */
fun presentableExercises(concept: PilotConcept): List<com.aistudio.medlingua.dzmed.domain.pilot.PilotExercise> =
    if (com.aistudio.medlingua.dzmed.domain.pilot.isConceptPublishable(concept)) concept.exercises else emptyList()
