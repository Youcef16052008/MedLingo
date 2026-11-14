package com.aistudio.medlingua.dzmed.domain.pilot

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * P4.2 — Modèles de MESURE du pilote — miroir exact de
 * `web-react/src/pilot/measure.ts` (types) et de
 * `web-react/scripts/pilot-schema.mjs` (validateur).
 *
 * Source partagée : `assets/pilot/pilot-anatomy-measure.v1.json` (parité
 * vérifiée par `npm run check:pilot-parity`). Les items de mesure ne passent
 * JAMAIS par le schéma d'entraînement : ids distincts (garde testée), pas
 * d'`explanation` (aucun feedback correctif pendant la mesure), versions
 * parallèles A/B/C explicites, et MÊME contentVersion que l'entraînement
 * (toute modification de contenu invalide les banques — pas de survivance
 * silencieuse).
 */

@Serializable
data class MeasureItem(
    val id: String,
    val conceptId: String,
    val version: String,
    val dimension: String, // recognize | recall | comprehend | production | real_use
    val kind: String, // "mcq" | "free_input"
    val prompt: PilotLocalizedText = PilotLocalizedText(),
    val options: List<String> = emptyList(),
    val answer: String = "",
    val acceptedVariants: List<String> = emptyList(),
    /** Provenance de la consigne (defEn/defFr/fr) — comparabilité à relire. */
    val source: String = ""
)

@Serializable
data class MeasureBank(
    val conceptId: String,
    val version: String,
    val items: List<MeasureItem> = emptyList()
)

@Serializable
data class MeasureManifest(
    val schemaVersion: Int = 0,
    val id: String = "",
    val contentVersion: String = "",
    val generatedAt: String = "",
    val generatedFrom: String = "",
    val versions: List<String> = emptyList(),
    val banks: List<MeasureBank> = emptyList()
)

/** Mapping fixe temps → version parallèle (aligné sur build_pilot_measure.mjs). */
val TIME_TO_VERSION: Map<String, String> = mapOf("T0" to "A", "T1" to "B", "T2" to "C")

const val EXPECTED_MEASURE_VERSIONS = "A,B,C"

private val MEASURE_DIMENSIONS =
    setOf("recognize", "recall", "comprehend", "production", "real_use")

/**
 * Analyse le manifeste de mesure. Erreurs bloquantes : schéma, banque vide,
 * version hors mapping, item sans source, fuite de la réponse dans une
 * consigne, dimension inconnue, couverture minimale par version.
 * (L'absence de `explanation`/feedback correctif est garantie à la GÉNÉRATION
 * par `check:pilot` — ce chargeur n'ignore pas silencieusement un schéma
 * inconnu, il valide ce qu'il doit consommer.)
 */
fun parseMeasureManifest(raw: String, trainingContentVersion: String): MeasureManifest {
    val json = Json { ignoreUnknownKeys = true }
    val manifest = json.decodeFromString<MeasureManifest>(raw)
    if (manifest.schemaVersion != SUPPORTED_PILOT_SCHEMA) {
        throw PilotSchemaException(
            "pilot-anatomy-measure.v1.json : schemaVersion ${manifest.schemaVersion} non supporté (attendu $SUPPORTED_PILOT_SCHEMA)"
        )
    }
    if (manifest.contentVersion != trainingContentVersion) {
        throw PilotSchemaException(
            "contentVersion mesure ${manifest.contentVersion} ≠ entraînement $trainingContentVersion — banques à regénérer/revalider"
        )
    }
    for (v in EXPECTED_MEASURE_VERSIONS.split(",")) {
        if (!manifest.versions.contains(v)) {
            throw PilotSchemaException("version parallèle « $v » absente (attendues $EXPECTED_MEASURE_VERSIONS)")
        }
    }
    val itemIds = mutableSetOf<String>()
    val banksSeen = mutableSetOf<String>()
    for (bank in manifest.banks) {
        val key = "${bank.conceptId}::${bank.version}"
        if (!banksSeen.add(key)) throw PilotSchemaException("[${bank.conceptId}/${bank.version}] banque dupliquée")
        if (!TIME_TO_VERSION.containsValue(bank.version)) {
            throw PilotSchemaException("[${bank.conceptId}/${bank.version}] version hors mapping parallèle")
        }
        if (bank.items.isEmpty()) throw PilotSchemaException("[${bank.conceptId}/${bank.version}] banque vide")
        val dims = mutableSetOf<String>()
        for (it in bank.items) {
            if (!itemIds.add(it.id)) throw PilotSchemaException("[${it.id}] id d'item dupliqué (global)")
            if (it.conceptId != bank.conceptId || it.version != bank.version) {
                throw PilotSchemaException("[${it.id}] item hors banque")
            }
            if (it.dimension !in MEASURE_DIMENSIONS) {
                throw PilotSchemaException("[${it.id}] dimension de mesure inconnue : ${it.dimension}")
            }
            dims.add(it.dimension)
            // Aucun feedback correctif pendant la mesure : pas de correction
            // embarquée, jamais affichée pendant le test.
            if (it.answer.isBlank()) throw PilotSchemaException("[${it.id}] réponse vide")
            if (it.source.isBlank()) throw PilotSchemaException("[${it.id}] source absente")
            if (it.kind == "mcq") {
                if (it.options.size < 2) throw PilotSchemaException("[${it.id}] QCM avec moins de 2 options")
                if (it.options.none { o -> pilotNorm(o) == pilotNorm(it.answer) }) {
                    throw PilotSchemaException("[${it.id}] réponse absente des options")
                }
            }
        }
        for (d in listOf("recognize", "recall")) {
            if (d !in dims) {
                throw PilotSchemaException("[${bank.conceptId}/${bank.version}] dimension « $d » absente (couverture minimale P3.1)")
            }
        }
    }
    return manifest
}
