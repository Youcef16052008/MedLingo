package com.example.domain.exercise

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * ExerciseSpec - Duolingo Exercise Engine as Data
 * Comme Duolingo: exercises = JSON discriminated union, pas de code
 * Zod validated côté backend, checkers purs côté client
 *
 * Types:
 * - choice: QCM 4 options (ex: What is patella?)
 * - wordbank: Ordre de mots (ex: The femur is the longest bone)
 * - match: Appariement (ex: Skull:Crâne)
 * - fill: Remplir le blanc (ex: The patella ___ the knee)
 * - clinical_case: Cas clinique vignette + QCM
 * - reading: Lecture + QCM
 *
 * Inspiré de Clonemrr + Duolingo Blog Session Generator
 */
@Serializable
sealed class ExerciseSpec {

    @Serializable
    @SerialName("choice")
    data class Choice(
        val promptEn: String,
        val promptFr: String,
        val promptAr: String,
        val options: List<String>, // 4 options
        val correctIndex: Int, // 0..3
        val explanationEn: String,
        val explanationFr: String,
        val explanationAr: String,
        val imageUrl: String? = null,
        val audioUrl: String? = null
    ) : ExerciseSpec()

    @Serializable
    @SerialName("wordbank")
    data class Wordbank(
        val promptEn: String,
        val promptFr: String,
        val promptAr: String,
        val bank: List<String>, // words to order
        val correctOrder: List<Int>, // indices in correct order
        val correctSentence: String, // full sentence for checking
        val explanationEn: String,
        val explanationFr: String,
        val explanationAr: String
    ) : ExerciseSpec()

    @Serializable
    @SerialName("match")
    data class Match(
        val promptEn: String,
        val promptFr: String,
        val promptAr: String,
        val pairs: List<MatchPair>, // ex: ["Skull", "Crâne"]
        val explanationEn: String,
        val explanationFr: String,
        val explanationAr: String
    ) : ExerciseSpec()

    @Serializable
    @SerialName("fill")
    data class Fill(
        val promptEn: String, // ex: "The patella ___ the knee"
        val promptFr: String,
        val promptAr: String,
        val acceptedAnswers: List<String>, // ex: ["protects", "covers"]
        val hint: String? = null,
        val explanationEn: String,
        val explanationFr: String,
        val explanationAr: String
    ) : ExerciseSpec()

    @Serializable
    @SerialName("clinical_case")
    data class ClinicalCase(
        val vignetteEn: String,
        val vignetteFr: String,
        val vignetteAr: String,
        val questionEn: String,
        val questionFr: String,
        val questionAr: String,
        val options: List<String>,
        val correctIndex: Int,
        val explanationEn: String,
        val explanationFr: String,
        val explanationAr: String,
        val clinicalPearl: String,
        val mnemonic: String
    ) : ExerciseSpec()

    @Serializable
    @SerialName("reading")
    data class Reading(
        val passageEn: String,
        val passageFr: String,
        val passageAr: String,
        val questionEn: String,
        val questionFr: String,
        val questionAr: String,
        val options: List<String>,
        val correctIndex: Int,
        val explanationEn: String,
        val explanationFr: String,
        val explanationAr: String
    ) : ExerciseSpec()
}

@Serializable
data class MatchPair(
    val left: String,
    val right: String
)

/**
 * Same Exam Swapped Language - Innovation MedicoMedics
 * Même QCM, langue de la question changée (EN→FR→AR)
 * Permet de réviser même concept dans 3 langues
 */
enum class ExerciseLanguage {
    ENGLISH, FRENCH, ARABIC
}

data class SwappedExercise(
    val originalSpec: ExerciseSpec,
    val swappedSpec: ExerciseSpec,
    val originalLang: ExerciseLanguage,
    val targetLang: ExerciseLanguage
)
