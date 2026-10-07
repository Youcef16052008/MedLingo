package com.example.domain.exercise

import com.example.data.local.entity.ExerciseEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * ExerciseSpecMigrator - Migrates legacy ExerciseEntity (optionsRaw pipe) to new ExerciseSpec JSONB
 * Like Duolingo S3 course data offline processing
 *
 * Legacy: optionsRaw = "Femur|Patella|Fibula|Scapula" + correctAnswer = "Patella"
 * New: specJson = {"type":"choice","options":[...],"correctIndex":1,...}
 */
object ExerciseSpecMigrator {

    private val json = Json {
        prettyPrint = false
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    /**
     * Migrate single ExerciseEntity to specJson
     */
    fun migrateToSpecJson(entity: ExerciseEntity): String {
        return try {
            val spec = when (entity.type) {
                "mcq" -> {
                    val options = entity.getOptionsList()
                    val correctIndex = options.indexOfFirst { it.trim().equals(entity.correctAnswer.trim(), ignoreCase = true) }.coerceAtLeast(0)
                    ExerciseSpec.Choice(
                        promptEn = entity.questionEn,
                        promptFr = entity.questionFr,
                        promptAr = entity.questionAr,
                        options = options,
                        correctIndex = correctIndex,
                        explanationEn = entity.explanationEn,
                        explanationFr = entity.explanationFr,
                        explanationAr = entity.explanationAr
                    )
                }
                "sentence_order" -> {
                    val words = entity.getOptionsList()
                    ExerciseSpec.Wordbank(
                        promptEn = entity.questionEn,
                        promptFr = entity.questionFr,
                        promptAr = entity.questionAr,
                        bank = words,
                        correctOrder = WordbankOrder.computeCorrectOrder(words, entity.correctAnswer),
                        correctSentence = entity.correctAnswer,
                        explanationEn = entity.explanationEn,
                        explanationFr = entity.explanationFr,
                        explanationAr = entity.explanationAr
                    )
                }
                "matching" -> {
                    val pairs = entity.getOptionsList().mapNotNull { raw ->
                        val parts = raw.split(":")
                        if (parts.size == 2) MatchPair(parts[0].trim(), parts[1].trim()) else null
                    }
                    ExerciseSpec.Match(
                        promptEn = entity.questionEn,
                        promptFr = entity.questionFr,
                        promptAr = entity.questionAr,
                        pairs = pairs,
                        explanationEn = entity.explanationEn,
                        explanationFr = entity.explanationFr,
                        explanationAr = entity.explanationAr
                    )
                }
                "fill_blank" -> {
                    ExerciseSpec.Fill(
                        promptEn = entity.questionEn,
                        promptFr = entity.questionFr,
                        promptAr = entity.questionAr,
                        // optionsRaw holds distractors (MCQ-style), not alternate answers
                        acceptedAnswers = listOf(entity.correctAnswer),
                        explanationEn = entity.explanationEn,
                        explanationFr = entity.explanationFr,
                        explanationAr = entity.explanationAr
                    )
                }
                "reading" -> {
                    val options = entity.getOptionsList()
                    val correctIndex = options.indexOfFirst { it.trim().equals(entity.correctAnswer.trim(), ignoreCase = true) }.coerceAtLeast(0)
                    ExerciseSpec.Reading(
                        passageEn = entity.contextTextEn,
                        passageFr = entity.contextTextFr,
                        passageAr = entity.contextTextAr,
                        questionEn = entity.questionEn,
                        questionFr = entity.questionFr,
                        questionAr = entity.questionAr,
                        options = options,
                        correctIndex = correctIndex,
                        explanationEn = entity.explanationEn,
                        explanationFr = entity.explanationFr,
                        explanationAr = entity.explanationAr
                    )
                }
                "clinical_case" -> {
                    val options = entity.getOptionsList()
                    val correctIndex = options.indexOfFirst { it.trim().equals(entity.correctAnswer.trim(), ignoreCase = true) }.coerceAtLeast(0)
                    ExerciseSpec.ClinicalCase(
                        vignetteEn = entity.contextTextEn,
                        vignetteFr = entity.contextTextFr,
                        vignetteAr = entity.contextTextAr,
                        questionEn = entity.questionEn,
                        questionFr = entity.questionFr,
                        questionAr = entity.questionAr,
                        options = options,
                        correctIndex = correctIndex,
                        explanationEn = entity.explanationEn,
                        explanationFr = entity.explanationFr,
                        explanationAr = entity.explanationAr,
                        clinicalPearl = "",
                        mnemonic = ""
                    )
                }
                else -> {
                    val options = entity.getOptionsList()
                    val correctIndex = options.indexOfFirst { it.trim().equals(entity.correctAnswer.trim(), ignoreCase = true) }.coerceAtLeast(0)
                    ExerciseSpec.Choice(
                        promptEn = entity.questionEn,
                        promptFr = entity.questionFr,
                        promptAr = entity.questionAr,
                        options = options,
                        correctIndex = correctIndex,
                        explanationEn = entity.explanationEn,
                        explanationFr = entity.explanationFr,
                        explanationAr = entity.explanationAr
                    )
                }
            }
            json.encodeToString(spec)
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * Migrate list of entities
     */
    fun migrateAll(entities: List<ExerciseEntity>): List<ExerciseEntity> {
        return entities.map { entity ->
            if (entity.specJson.isNotBlank() && entity.isSpecMigrated) {
                entity
            } else {
                val specJson = migrateToSpecJson(entity)
                entity.copy(
                    specJson = specJson,
                    isSpecMigrated = specJson.isNotBlank(),
                    version = 2
                )
            }
        }
    }

    /**
     * Parse specJson back to ExerciseSpec
     */
    fun parseSpecJson(specJson: String): ExerciseSpec? {
        return try {
            if (specJson.isBlank()) return null
            json.decodeFromString<ExerciseSpec>(specJson)
        } catch (e: Exception) {
            null
        }
    }
}
