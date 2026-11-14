package com.aistudio.medlingua.dzmed.data.pilot

import android.content.SharedPreferences
import com.aistudio.medlingua.dzmed.domain.pilot.MeasureSessionState
import com.aistudio.medlingua.dzmed.domain.pilot.pilotIsoDate
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * P4.3 â€” Persistance et EXPORT de la MESURE â€” port de
 * `web-react/src/pilot/measureStorage.ts` (P3.2).
 *
 * - Namespace SÃ‰PARÃ‰ de l'entraÃ®nement (`medlingo_pilot_anatomy_measure_v1`) ;
 *   le reset de mesure ne touche jamais la progression d'entraÃ®nement, et
 *   rÃ©ciproquement : la mesure n'est jamais rÃ©injectÃ©e dans SM-2.
 * - Pseudonyme Ã©ditable, sans email/nom/donnÃ©e patient.
 * - Export JSON/CSV avec les donnÃ©es exigÃ©es (temps, versions, itemId,
 *   rÃ©sultat, aides, durÃ©e, Ã©tat terminÃ©/interrompu, dÃ©lai rÃ©el T1â†’T2) ;
 *   transmission MANUELLE, consentement Ã  l'Ã©cran, aucune tÃ©lÃ©mÃ©trie.
 * - MÃªme format JSON que le web.
 */

const val MEASURE_PREFS = "medlingo_pilot_anatomy_measure_v1"
const val MEASURE_PREFS_BACKUP = "medlingo_pilot_anatomy_measure_v1_backup"
private const val KEY_STATE = "state"

@Serializable
data class MeasurePersistedState(
    val schemaVersion: Int = 1,
    val updatedAt: Long = 0L,
    /** Identifiant pseudonyme du participant â€” Ã©ditable, jamais dÃ©duit de donnÃ©es personnelles. */
    val pseudonym: String = "",
    /** Toutes les sÃ©ances de mesure (finies OU interrompues). */
    val sessions: List<MeasureSessionState> = emptyList()
)

private val json = Json { ignoreUnknownKeys = true }
private const val EXPORT_KIND = "medlingo-pilot-anatomy-measure"
private const val STATE_SCHEMA = 1

/** RÃ©sultat d'import de la MESURE â€” mÃªme forme que [PilotImportResult], portant l'Ã©tat mesure. */
sealed class MeasureImportResult {
    data class Ok(val state: MeasurePersistedState, val note: String?) : MeasureImportResult()
    data class Failed(val error: String) : MeasureImportResult() // pilotImportInvalid | pilotImportSchema | pilotImportVersion
}

/** Charge l'Ã©tat de mesure. Ne lÃ¨ve JAMAIS : Ã©tat sain + warning (clÃ© i18n). */
fun loadMeasureState(prefs: SharedPreferences, now: Long): Pair<MeasurePersistedState, String?> {
    val raw = try {
        prefs.getString(KEY_STATE, null)
    } catch (_: Exception) {
        return MeasurePersistedState() to "pilotStorageUnavailable"
    } ?: return MeasurePersistedState() to null
    val parsed = try {
        json.decodeFromString<MeasurePersistedState>(raw)
    } catch (_: Exception) {
        return MeasurePersistedState(updatedAt = now) to "pilotDataCorrupted"
    }
    if (parsed.schemaVersion != STATE_SCHEMA) {
        return MeasurePersistedState(updatedAt = now) to "pilotSchemaChanged"
    }
    // SÃ©ances d'un schÃ©ma/contenu inconnu : jetÃ©es explicitement au champ,
    // pas de crash â€” l'analyse reste honnÃªte (pas de donnÃ©e partielle muette).
    val sessions = parsed.sessions.filter { s -> s.schemaVersion == STATE_SCHEMA }
    return parsed.copy(updatedAt = if (parsed.updatedAt > 0) parsed.updatedAt else now, sessions = sessions) to null
}

/** Sauvegarde. Code d'erreur explicite â€” jamais de Â« ok Â» mensonger. */
fun saveMeasureState(prefs: SharedPreferences, state: MeasurePersistedState): PilotSaveResult {
    return try {
        val ok = prefs.edit().putString(KEY_STATE, json.encodeToString(MeasurePersistedState.serializer(), state)).commit()
        if (ok) PilotSaveResult.Ok else PilotSaveResult.Failed("pilotStorageUnavailable")
    } catch (e: Exception) {
        PilotSaveResult.Failed(if (e.message?.contains("quota", ignoreCase = true) == true) "pilotStorageQuota" else "pilotStorageUnavailable")
    }
}

/** Remplace/ajoute la sÃ©ance (mÃªme id â†’ remplacÃ©e). */
fun upsertMeasureSession(state: MeasurePersistedState, session: MeasureSessionState): MeasurePersistedState =
    state.copy(updatedAt = session.updatedAt, sessions = state.sessions.filter { it.id != session.id } + session)

/* ------------------------------ export P3.2 ------------------------------ */

/** Export JSON versionnÃ© : pseudonyme + sÃ©ances brutes + champ aides explicite. */
fun exportMeasureJson(state: MeasurePersistedState, contentVersion: String): String = json.encodeToString(
    kotlinx.serialization.json.JsonObject.serializer(),
    kotlinx.serialization.json.buildJsonObject {
        put("kind", kotlinx.serialization.json.JsonPrimitive(EXPORT_KIND))
        put("schemaVersion", kotlinx.serialization.json.JsonPrimitive(1))
        put("exportedAt", kotlinx.serialization.json.JsonPrimitive(pilotIsoDate(System.currentTimeMillis())))
        put("contentVersion", kotlinx.serialization.json.JsonPrimitive(contentVersion))
        put("pseudonym", kotlinx.serialization.json.JsonPrimitive(state.pseudonym))
        // Aides pendant la mesure : interdites par le protocole (champ explicite).
        put("aidsUsedDuringMeasure", kotlinx.serialization.json.JsonPrimitive(false))
        put(
            "sessions",
            kotlinx.serialization.json.JsonArray(state.sessions.map { json.encodeToJsonElement(MeasureSessionState.serializer(), it) })
        )
    }
)

/** Ligne CSV : une ligne PAR ITEM traitÃ© ; sÃ©ance sans item traitÃ© â‰  faux score 0. */
private val CSV_HEADER =
    listOf(
        "pseudonym", "contentVersion", "sessionId", "time", "testVersion", "startedAt", "state",
        "actualDelayFromT1Ms", "itemId", "conceptId", "dimension", "correct", "invalidatedReason", "durationMs"
    )

private fun csvCell(v: Any?): String {
    val s = v?.toString() ?: ""
    return if (s.contains(',') || s.contains('"') || s.contains('\n') || s.contains(';')) {
        "\"${s.replace("\"", "\"\"")}\""
    } else s
}

fun exportMeasureCsv(state: MeasurePersistedState, contentVersion: String): String {
    val lines = mutableListOf(CSV_HEADER.joinToString(","))
    for (s in state.sessions) {
        val stateLabel = when {
            s.finished && s.interrupted -> "interrupted_finished"
            s.finished -> "finished"
            else -> "in_progress"
        }
        if (s.results.isEmpty()) {
            lines.add(
                listOf(
                    state.pseudonym, contentVersion, s.id, s.time, s.version,
                    pilotIsoDate(s.startedAt), stateLabel,
                    s.actualDelayFromT1Ms?.toString() ?: "", "", "", "", "", "", ""
                ).joinToString(",") { csvCell(it) }
            )
            continue
        }
        for (r in s.results) {
            lines.add(
                listOf(
                    state.pseudonym, contentVersion, s.id, s.time, s.version,
                    pilotIsoDate(s.startedAt), stateLabel,
                    s.actualDelayFromT1Ms?.toString() ?: "",
                    r.itemId, r.conceptId, r.dimension,
                    when {
                        r.invalidatedReason != null -> "invalid"
                        r.correct == null -> "unanswered"
                        r.correct == true -> "1"
                        else -> "0"
                    },
                    r.invalidatedReason ?: "", r.durationMs?.toString() ?: ""
                ).joinToString(",") { csvCell(it) }
            )
        }
    }
    return lines.joinToString("\n") + "\n"
}

/** Import validÃ© ; version de contenu diffÃ©rente = REFUSÃ‰E (mesure non comparable). */
fun importMeasureJson(text: String, contentVersion: String, now: Long): MeasureImportResult {
    val obj = try {
        json.parseToJsonElement(text) as? kotlinx.serialization.json.JsonObject
    } catch (_: Exception) {
        null
    } ?: return MeasureImportResult.Failed("pilotImportInvalid")
    if ((obj["kind"] as? kotlinx.serialization.json.JsonPrimitive)?.content != EXPORT_KIND) {
        return MeasureImportResult.Failed("pilotImportInvalid")
    }
    val sv = (obj["schemaVersion"] as? kotlinx.serialization.json.JsonPrimitive)?.content?.toIntOrNull()
    if (sv != STATE_SCHEMA) return MeasureImportResult.Failed("pilotImportSchema")
    if ((obj["contentVersion"] as? kotlinx.serialization.json.JsonPrimitive)?.content != contentVersion) {
        return MeasureImportResult.Failed("pilotImportVersion")
    }
    return try {
        val state = json.decodeFromJsonElement(
            MeasurePersistedState.serializer(),
            kotlinx.serialization.json.buildJsonObject {
                put("schemaVersion", kotlinx.serialization.json.JsonPrimitive(1))
                put("updatedAt", kotlinx.serialization.json.JsonPrimitive(now))
                put("pseudonym", obj["pseudonym"] ?: kotlinx.serialization.json.JsonPrimitive(""))
                put("sessions", obj["sessions"] ?: kotlinx.serialization.json.JsonArray(emptyList()))
            }
        )
        MeasureImportResult.Ok(state, null)
    } catch (_: Exception) {
        MeasureImportResult.Failed("pilotImportInvalid")
    }
}

/** Import avec sauvegarde prÃ©alable (confirmation dÃ©cidÃ©e Ã  l'Ã©cran). */
fun applyMeasureImport(
    prefs: SharedPreferences,
    backupPrefs: SharedPreferences,
    current: MeasurePersistedState,
    imported: MeasurePersistedState
): PilotSaveResult {
    val backup = saveMeasureState(backupPrefs, current)
    if (backup is PilotSaveResult.Failed) return backup
    return saveMeasureState(prefs, imported)
}

/** Restaure la sauvegarde prÃ©cÃ©dente (aprÃ¨s import indÃ©sirable). */
fun restoreMeasureBackup(
    prefs: SharedPreferences,
    backupPrefs: SharedPreferences,
    now: Long
): Pair<MeasurePersistedState, String?> {
    val raw = try {
        backupPrefs.getString(KEY_STATE, null)
    } catch (_: Exception) {
        return MeasurePersistedState() to "pilotStorageUnavailable"
    }
    if (raw == null) return MeasurePersistedState() to "pilotNoBackup"
    return try {
        prefs.edit().putString(KEY_STATE, raw).commit()
        loadMeasureState(prefs, now)
    } catch (_: Exception) {
        MeasurePersistedState() to "pilotStorageUnavailable"
    }
}

/** Reset de la MESURE uniquement â€” progression d'entraÃ®nement intacte. */
fun resetMeasureState(prefs: SharedPreferences, backupPrefs: SharedPreferences): PilotSaveResult {
    return try {
        prefs.edit().clear().commit()
        backupPrefs.edit().clear().commit()
        PilotSaveResult.Ok
    } catch (_: Exception) {
        PilotSaveResult.Failed("pilotStorageUnavailable")
    }
}
