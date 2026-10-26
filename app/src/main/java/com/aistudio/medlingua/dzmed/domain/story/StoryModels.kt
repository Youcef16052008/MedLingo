package com.aistudio.medlingua.dzmed.domain.story

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * Modèle « histoire » — miroir exact de `web-react/src/learning/types.ts`.
 * Source partagée : `assets/learning/stories.v1.json` (identique web/Android,
 * vérifié par `npm run check:stories`).
 */

/** Texte localisé — consignes et aides, jamais la langue cible (anglais). */
@Serializable
data class LText(
    val en: String = "",
    val fr: String = "",
    val ar: String = ""
)

@Serializable
data class StorySegment(
    val en: String = "",
    val fr: String = "",
    val ar: String = ""
)

@Serializable
data class TargetPhrase(
    val id: String,
    val en: String = "",
    val fr: String = "",
    val ar: String = ""
)

@Serializable
data class StoryQuestion(
    val id: String,
    val q: String = "",
    val qFr: String? = null,
    val qAr: String? = null,
    val accepted: List<String> = emptyList(),
    val model: String = "",
    val hint: LText? = null,
    val phraseIds: List<String> = emptyList()
)

@Serializable
data class DrillItem(
    val id: String,
    val prompt: String = "",
    val accepted: List<String> = emptyList(),
    val model: String = ""
)

@Serializable
data class Drill(
    val explain: LText = LText(),
    val items: List<DrillItem> = emptyList()
)

@Serializable
data class RetellSpec(
    val prompt: LText = LText(),
    val starters: List<String> = emptyList(),
    val model: String = ""
)

@Serializable
data class TransferSpec(
    val context: LText = LText(),
    val gloss: LText? = null,
    val questions: List<StoryQuestion> = emptyList(),
    val task: RetellSpec = RetellSpec()
)

@Serializable
data class Story(
    val schemaVersion: Int = 1,
    val storyId: String,
    val contentVersion: Int = 1,
    val title: LText = LText(),
    val goal: LText = LText(),
    val moduleIds: List<String> = emptyList(),
    val relatedTermIds: List<Int> = emptyList(),
    val languageBand: String = "",
    val publicationStatus: String = "draft",
    val durationMin: Int = 10,
    val review: StoryReview = StoryReview(),
    val segments: List<StorySegment> = emptyList(),
    val targetPhrases: List<TargetPhrase> = emptyList(),
    val questions: List<StoryQuestion> = emptyList(),
    val drill: Drill = Drill(),
    val retell: RetellSpec = RetellSpec(),
    val transfer: TransferSpec = TransferSpec()
)

@Serializable
data class StoryReview(
    val language: String = "pending",
    val medical: String = "pending"
)

@Serializable
data class StoryFile(
    val schemaVersion: Int = 0,
    val stories: List<Story> = emptyList()
)

/** Versions du fichier comprises par ce client — miroir de SUPPORTED_STORIES_SCHEMA. */
const val SUPPORTED_STORIES_SCHEMA: Int = 1

class StorySchemaException(message: String) : Exception(message)

/**
 * Analyse le contenu brut. Une version de schéma inconnue est un échec franc,
 * pas un contenu partiellement interprété ; les `draft` ne sont jamais servis.
 */
fun parseStoryFile(raw: String): List<Story> {
    val json = Json { ignoreUnknownKeys = true }
    val file = json.decodeFromString<StoryFile>(raw)
    if (file.schemaVersion != SUPPORTED_STORIES_SCHEMA) {
        throw StorySchemaException(
            "stories.v1.json : schemaVersion ${file.schemaVersion} non supporté (attendu $SUPPORTED_STORIES_SCHEMA)"
        )
    }
    return file.stories.filter { it.publicationStatus == "published" }
}
