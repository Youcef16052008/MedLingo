package com.aistudio.medlingua.dzmed.domain.pilot

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * P4.2 — Modèles du pilote Anatomie — miroir exact de
 * `web-react/src/pilot/types.ts` et de `web-react/src/pilot/content.ts`.
 *
 * Source partagée : `assets/pilot/pilot-anatomy.v1.json` (identique web/Android,
 * vérifié par `npm run check:pilot-parity`). Règle d'honnêteté inchangée :
 * tout contenu produit automatiquement est `draft` ; `approved` n'existe que si
 * les DEUX relectures humaines couvrent la version courante — c'est vérifié
 * AU CHARGEMENT par [isConceptPublishable], pas par l'honneur des générateurs.
 *
 * Les ids pilote sont TEXTUELS : ils ne franchissent JAMAIS un contrat
 * numérique (SM-2, Room). La progression pilote vit dans
 * `data/pilot/PilotProgressStore.kt`, volontairement séparée.
 */

const val SUPPORTED_PILOT_SCHEMA: Int = 1

class PilotSchemaException(message: String) : Exception(message)

@Serializable
data class PilotLocalizedText(
    val en: String = "",
    val fr: String = "",
    val ar: String = ""
)

@Serializable
data class PilotReview(
    val status: String = "pending",
    /** Personne réelle — jamais générée. Requis si status == "done". */
    val reviewer: String? = null,
    /** Date ISO de la relecture. Requis si status == "done". */
    val date: String? = null,
    /** Version de contenu exacte relue. Requis si status == "done". */
    val reviewedContentVersion: String? = null,
    val references: List<String> = emptyList(),
    val notes: String? = null
)

/** Matrice P1.3 : reconnaissance, compréhension en contexte, rappel. */
@Serializable
data class PilotExercise(
    val id: String,
    val conceptId: String,
    val objective: String = "",
    val role: String = "",
    val kind: String, // "mcq" | "free_input"
    val prompt: PilotLocalizedText = PilotLocalizedText(),
    val options: List<String> = emptyList(),
    val answer: String = "",
    val acceptedVariants: List<String> = emptyList(),
    val explanation: PilotLocalizedText = PilotLocalizedText()
)

@Serializable
data class PilotConcept(
    val id: String,
    val unit: Int,
    val order: Int,
    val legacyRef: Int? = null,
    val labelEn: String = "",
    val hints: PilotHints = PilotHints(),
    val languageObjective: String = "",
    val definition: PilotLocalizedText = PilotLocalizedText(),
    val example: PilotLocalizedText? = null,
    val acceptedVariants: List<String> = emptyList(),
    /** Justification fréquence/utilité — hypothèse À FAIRE EXAMINER (P1.2). */
    val selectionNote: String = "",
    val status: String = "draft",
    val contentVersion: String = "",
    val reviews: PilotReviews = PilotReviews(),
    val exercises: List<PilotExercise> = emptyList()
)

@Serializable
data class PilotHints(val fr: String = "", val ar: String = "")

@Serializable
data class PilotReviews(val linguistic: PilotReview = PilotReview(), val medical: PilotReview = PilotReview())

@Serializable
data class PilotManifest(
    val schemaVersion: Int = 0,
    val id: String = "",
    val contentVersion: String = "",
    val generatedAt: String = "",
    val generatedFrom: String = "",
    val concepts: List<PilotConcept> = emptyList()
)

/** Normalisation de VALIDATION identique à `scripts/pilot-schema.mjs` (casse, espaces, accents — ponctuation conservée). */
fun pilotNorm(s: String): String =
    java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD)
        .lowercase()
        .replace(Regex("[\\u0300-\\u036f]"), "")
        .replace(Regex("\\s+"), " ")
        .trim()

/** ISO-8601 UTC (miroir de `new Date(ms).toISOString()` côté web) — compatible API 24. */
fun pilotIsoDate(epochMs: Long): String {
    val fmt = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", java.util.Locale.US)
    fmt.timeZone = java.util.TimeZone.getTimeZone("UTC")
    return fmt.format(java.util.Date(epochMs))
}

/**
 * Normalisation de RÉPONSE — miroir exact de `normalizeAnswer`
 * (`web-react/src/domain/scoring.ts`) : casse, accents ET harakat arabes,
 * ponctuation et symboles supprimés (négations et nombres préservés),
 * espaces compactés. C'est CETTE fonction qui décide l'égalité de réponse
 * (miroir web : `normalizeAnswer(rawInput) === normalizeAnswer(answer)`).
 */
fun pilotNormalizeAnswer(s: String): String =
    java.text.Normalizer.normalize(s.trim(), java.text.Normalizer.Form.NFD)
        .lowercase()
        .replace(Regex("[\\u0640\\u064B-\\u0652\\u0670]"), "")
        .replace(Regex("\\p{Mn}"), "")
        .replace(Regex("[^\\p{L}\\p{N} ]"), "")
        .replace(Regex("\\s+"), " ")
        .trim()

/**
 * Verrou de publication pilote — MIROIR EXACT de `isConceptPublishable` côté
 * web (et du verrou d'histoires P0.4) : un concept n'est présentable que si
 * les DEUX relectures humaines couvrent la version courante ET que le statut
 * éditorial est `approved`. Aucune exception, pas de contournement
 * d'URL/écran : la décision appartient au chargeur, jamais au contenu.
 */
fun isConceptPublishable(concept: PilotConcept): Boolean {
    if (concept.status != "approved") return false
    fun covers(r: PilotReview): Boolean =
        r.status == "done" && r.reviewedContentVersion == concept.contentVersion
    return covers(concept.reviews.linguistic) && covers(concept.reviews.medical)
}

/**
 * Analyse le manifeste d'ENTRAÎNEMENT. Une version de schéma inconnue est un
 * échec franc (pattern `parseStoryFile`) ; erreurs structurelles bloquantes :
 * ids dupliqués, exercices sans réponse, QCM dont la réponse manque des
 * options, consigne vide.
 */
fun parsePilotManifest(raw: String): PilotManifest {
    val json = Json { ignoreUnknownKeys = true }
    val manifest = json.decodeFromString<PilotManifest>(raw)
    if (manifest.schemaVersion != SUPPORTED_PILOT_SCHEMA) {
        throw PilotSchemaException(
            "pilot-anatomy.v1.json : schemaVersion ${manifest.schemaVersion} non supporté (attendu $SUPPORTED_PILOT_SCHEMA)"
        )
    }
    if (manifest.contentVersion.isBlank()) {
        throw PilotSchemaException("pilot-anatomy.v1.json : contentVersion absente")
    }
    val conceptIds = mutableSetOf<String>()
    val exerciseIds = mutableSetOf<String>()
    for (c in manifest.concepts) {
        if (!conceptIds.add(c.id)) throw PilotSchemaException("[${c.id}] id de concept dupliqué")
        if (c.labelEn.isBlank() || c.definition.en.isBlank()) {
            throw PilotSchemaException("[${c.id}] libellé/définition vides")
        }
        if (c.contentVersion != manifest.contentVersion) {
            throw PilotSchemaException(
                "[${c.id}] contentVersion ${c.contentVersion} ≠ manifeste ${manifest.contentVersion}"
            )
        }
        for (e in c.exercises) {
            if (!exerciseIds.add(e.id)) throw PilotSchemaException("[${e.id}] id d'exercice dupliqué (global)")
            if (e.conceptId != c.id) {
                throw PilotSchemaException("[${e.id}] conceptId ${e.conceptId} ≠ ${c.id}")
            }
            if (e.answer.isBlank()) throw PilotSchemaException("[${e.id}] réponse vide")
            if (e.kind == "mcq") {
                if (e.options.size < 2) throw PilotSchemaException("[${e.id}] QCM avec moins de 2 options")
                if (e.options.none { pilotNorm(it) == pilotNorm(e.answer) }) {
                    throw PilotSchemaException("[${e.id}] réponse absente des options")
                }
            }
        }
    }
    return manifest
}
