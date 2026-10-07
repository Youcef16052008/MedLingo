package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val level: Int = 1, // 1: Vocabulaire, 2: Collocations, 3: Phrases Simples, 4: Phrases Complexes, 5: Paragraphes, 6: Cas Cliniques
    val type: String, // "mcq", "fill_blank", "matching", "sentence_order", "reading", "clinical_case"
    val difficulty: String, // "beginner", "intermediate", "advanced"
    val module: String,
    val chapter: String,
    val questionEn: String,
    val questionFr: String,
    val questionAr: String,
    val optionsRaw: String, // Pipe-separated options, words to order, or key-value pairs (LEGACY)
    val correctAnswer: String,
    val explanationEn: String,
    val explanationFr: String,
    val explanationAr: String,
    val points: Int = 10,
    val contextTextEn: String = "", // Reading passage or Clinical Case vignette
    val contextTextFr: String = "",
    val contextTextAr: String = "",

    // === DUOLINGO PHASE 2: ExerciseSpec JSONB ===
    val specJson: String = "", // JSON serialized ExerciseSpec (new way)
    val isSpecMigrated: Boolean = false, // true if specJson is source of truth
    val version: Int = 1 // spec version for future migrations
) {
    fun getOptionsList(): List<String> {
        return if (optionsRaw.isBlank()) emptyList() else optionsRaw.split("|")
    }

    /**
     * Get ExerciseSpec from specJson or fallback to legacy optionsRaw
     * Phase 2 migration: specJson is parsed via the migrator's Json instance;
     * if it is blank or fails to parse we rebuild the spec from legacy fields
     * (the old code returned null for ANY non-blank specJson = dead branch).
     */
    fun toExerciseSpec(): com.example.domain.exercise.ExerciseSpec? {
        return try {
            if (specJson.isNotBlank()) {
                com.example.domain.exercise.ExerciseSpecMigrator.parseSpecJson(specJson)
                    ?: buildLegacySpec()
            } else {
                buildLegacySpec()
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun buildLegacySpec(): com.example.domain.exercise.ExerciseSpec? {
        return when (type) {
                    "mcq" -> {
                        val options = getOptionsList()
                        val correctIndex = options.indexOfFirst { it.trim().equals(correctAnswer.trim(), ignoreCase = true) }.coerceAtLeast(0)
                        com.example.domain.exercise.ExerciseSpec.Choice(
                            promptEn = questionEn,
                            promptFr = questionFr,
                            promptAr = questionAr,
                            options = options,
                            correctIndex = correctIndex,
                            explanationEn = explanationEn,
                            explanationFr = explanationFr,
                            explanationAr = explanationAr
                        )
                    }
                    "sentence_order" -> {
                        val words = getOptionsList()
                        com.example.domain.exercise.ExerciseSpec.Wordbank(
                            promptEn = questionEn,
                            promptFr = questionFr,
                            promptAr = questionAr,
                        bank = words,
                        correctOrder = com.example.domain.exercise.WordbankOrder.computeCorrectOrder(words, correctAnswer),
                            correctSentence = correctAnswer,
                            explanationEn = explanationEn,
                            explanationFr = explanationFr,
                            explanationAr = explanationAr
                        )
                    }
                    "matching" -> {
                        val pairs = getOptionsList().mapNotNull { raw ->
                            val parts = raw.split(":")
                            if (parts.size == 2) com.example.domain.exercise.MatchPair(parts[0].trim(), parts[1].trim()) else null
                        }
                        com.example.domain.exercise.ExerciseSpec.Match(
                            promptEn = questionEn,
                            promptFr = questionFr,
                            promptAr = questionAr,
                            pairs = pairs,
                            explanationEn = explanationEn,
                            explanationFr = explanationFr,
                            explanationAr = explanationAr
                        )
                    }
                    "fill_blank" -> {
                        com.example.domain.exercise.ExerciseSpec.Fill(
                            promptEn = questionEn,
                            promptFr = questionFr,
                            promptAr = questionAr,
                            // optionsRaw holds distractors (MCQ-style), not alternate answers
                            acceptedAnswers = listOf(correctAnswer),
                            explanationEn = explanationEn,
                            explanationFr = explanationFr,
                            explanationAr = explanationAr
                        )
                    }
                    "reading" -> {
                        val options = getOptionsList()
                        val correctIndex = options.indexOfFirst { it.trim().equals(correctAnswer.trim(), ignoreCase = true) }.coerceAtLeast(0)
                        com.example.domain.exercise.ExerciseSpec.Reading(
                            passageEn = contextTextEn,
                            passageFr = contextTextFr,
                            passageAr = contextTextAr,
                            questionEn = questionEn,
                            questionFr = questionFr,
                            questionAr = questionAr,
                            options = options,
                            correctIndex = correctIndex,
                            explanationEn = explanationEn,
                            explanationFr = explanationFr,
                            explanationAr = explanationAr
                        )
                    }
                    "clinical_case" -> {
                        val options = getOptionsList()
                        val correctIndex = options.indexOfFirst { it.trim().equals(correctAnswer.trim(), ignoreCase = true) }.coerceAtLeast(0)
                        com.example.domain.exercise.ExerciseSpec.ClinicalCase(
                            vignetteEn = contextTextEn,
                            vignetteFr = contextTextFr,
                            vignetteAr = contextTextAr,
                            questionEn = questionEn,
                            questionFr = questionFr,
                            questionAr = questionAr,
                            options = options,
                            correctIndex = correctIndex,
                            explanationEn = explanationEn,
                            explanationFr = explanationFr,
                            explanationAr = explanationAr,
                            clinicalPearl = "",
                            mnemonic = ""
                        )
                    }
                    else -> null
                }
    }
}
