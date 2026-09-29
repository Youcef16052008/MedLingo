package com.example.domain.exercise

/**
 * ExerciseChecker - Pure checkers comme Duolingo
 * Pas d'effet de bord, testable avec JUnit, anti-cheat server-authoritative
 * Inspiré Clonemrr + Duolingo Blog
 */
object ExerciseChecker {

    /**
     * Check Choice (MCQ)
     * Input: selectedIndex 0..3, correctIndex
     */
    fun checkChoice(selectedIndex: Int, correctIndex: Int): CheckResult {
        val isCorrect = selectedIndex == correctIndex
        return CheckResult(
            isCorrect = isCorrect,
            correctAnswer = correctIndex.toString(),
            userAnswer = selectedIndex.toString(),
            score = if (isCorrect) 1.0 else 0.0
        )
    }

    /**
     * Check Choice by text (backward compat optionsRaw pipe)
     */
    fun checkChoiceByText(selectedText: String, correctText: String): CheckResult {
        val isCorrect = selectedText.trim().equals(correctText.trim(), ignoreCase = true)
        return CheckResult(
            isCorrect = isCorrect,
            correctAnswer = correctText,
            userAnswer = selectedText,
            score = if (isCorrect) 1.0 else 0.0
        )
    }

    /**
     * Check Wordbank (sentence order)
     * Input: user constructed sentence vs correct sentence
     */
    fun checkWordbank(userSentence: String, correctSentence: String): CheckResult {
        val normalizedUser = normalizeSentence(userSentence)
        val normalizedCorrect = normalizeSentence(correctSentence)
        val isCorrect = normalizedUser == normalizedCorrect

        // Partial score: count correct words in correct position
        val userWords = normalizedUser.split(" ")
        val correctWords = normalizedCorrect.split(" ")
        var correctPositions = 0
        for (i in userWords.indices) {
            if (i < correctWords.size && userWords[i] == correctWords[i]) {
                correctPositions++
            }
        }
        val partialScore = if (correctWords.isNotEmpty()) correctPositions.toDouble() / correctWords.size else 0.0

        return CheckResult(
            isCorrect = isCorrect,
            correctAnswer = correctSentence,
            userAnswer = userSentence,
            score = if (isCorrect) 1.0 else partialScore.coerceIn(0.0, 0.9)
        )
    }

    /**
     * Check Match (appariement)
     * Input: user pairs vs correct pairs
     */
    fun checkMatch(userPairs: List<MatchPair>, correctPairs: List<MatchPair>): CheckResult {
        val correctMap = correctPairs.associate { it.left to it.right }
        var correctCount = 0
        for (pair in userPairs) {
            if (correctMap[pair.left] == pair.right) {
                correctCount++
            }
        }
        val isCorrect = correctCount == correctPairs.size && userPairs.size == correctPairs.size
        val score = if (correctPairs.isNotEmpty()) correctCount.toDouble() / correctPairs.size else 0.0

        return CheckResult(
            isCorrect = isCorrect,
            correctAnswer = correctPairs.joinToString("|") { "${it.left}:${it.right}" },
            userAnswer = userPairs.joinToString("|") { "${it.left}:${it.right}" },
            score = score
        )
    }

    /**
     * Check Match from pipe format "Skull:Crâne|Femur:Fémur"
     */
    fun checkMatchFromRaw(userRaw: String, correctRaw: String): CheckResult {
        val correctPairs = parseMatchRaw(correctRaw)
        val userPairs = parseMatchRaw(userRaw)
        return checkMatch(userPairs, correctPairs)
    }

    /**
     * Check Fill blank
     * Input: user text vs accepted answers list
     */
    fun checkFill(userAnswer: String, acceptedAnswers: List<String>): CheckResult {
        val normalizedUser = userAnswer.trim().lowercase()
        val isCorrect = acceptedAnswers.any { it.trim().lowercase() == normalizedUser }
        // Also accept if user answer contains accepted (fuzzy)
        val isFuzzyCorrect = acceptedAnswers.any { normalizedUser.contains(it.trim().lowercase()) || it.trim().lowercase().contains(normalizedUser) }

        return CheckResult(
            isCorrect = isCorrect,
            correctAnswer = acceptedAnswers.firstOrNull() ?: "",
            userAnswer = userAnswer,
            score = if (isCorrect) 1.0 else if (isFuzzyCorrect) 0.5 else 0.0,
            isFuzzy = isFuzzyCorrect && !isCorrect
        )
    }

    // Helpers
    private fun normalizeSentence(sentence: String): String {
        return sentence.trim()
            .lowercase()
            .replace(Regex("[^a-z0-9 ]"), "")
            .replace(Regex("\\s+"), " ")
            .trim()
    }

    private fun parseMatchRaw(raw: String): List<MatchPair> {
        if (raw.isBlank()) return emptyList()
        return raw.split("|").mapNotNull { pairStr ->
            val parts = pairStr.split(":")
            if (parts.size == 2) MatchPair(parts[0].trim(), parts[1].trim()) else null
        }
    }
}

data class CheckResult(
    val isCorrect: Boolean,
    val correctAnswer: String,
    val userAnswer: String,
    val score: Double, // 0.0 to 1.0
    val isFuzzy: Boolean = false,
    val explanation: String? = null
)

/**
 * Same Exam Swapped Language - MedicoMedics innovation
 * Même exercice, langue question changée
 */
object SameExamSwappedLanguage {

    /**
     * Swap exercise language EN→FR→AR
     * Garde même correctAnswer mais change prompt
     */
    fun swapLanguage(
        spec: ExerciseSpec,
        targetLang: ExerciseLanguage
    ): ExerciseSpec {
        return when (spec) {
            is ExerciseSpec.Choice -> {
                // Prompt reste même concept mais dans langue cible
                // Options restent en EN (termes médicaux toujours EN)
                spec.copy(
                    promptEn = when (targetLang) {
                        ExerciseLanguage.ENGLISH -> spec.promptEn
                        ExerciseLanguage.FRENCH -> spec.promptFr
                        ExerciseLanguage.ARABIC -> spec.promptAr
                    }
                )
            }
            is ExerciseSpec.Fill -> spec.copy(
                promptEn = when (targetLang) {
                    ExerciseLanguage.ENGLISH -> spec.promptEn
                    ExerciseLanguage.FRENCH -> spec.promptFr
                    ExerciseLanguage.ARABIC -> spec.promptAr
                }
            )
            is ExerciseSpec.Wordbank -> spec.copy(
                promptEn = when (targetLang) {
                    ExerciseLanguage.ENGLISH -> spec.promptEn
                    ExerciseLanguage.FRENCH -> spec.promptFr
                    ExerciseLanguage.ARABIC -> spec.promptAr
                }
            )
            else -> spec
        }
    }

    /**
     * Generate 3 versions of same exercise EN/FR/AR
     * Pour révision trilingue même concept
     */
    fun generateTrilingualVersions(spec: ExerciseSpec): Map<ExerciseLanguage, ExerciseSpec> {
        return mapOf(
            ExerciseLanguage.ENGLISH to swapLanguage(spec, ExerciseLanguage.ENGLISH),
            ExerciseLanguage.FRENCH to swapLanguage(spec, ExerciseLanguage.FRENCH),
            ExerciseLanguage.ARABIC to swapLanguage(spec, ExerciseLanguage.ARABIC)
        )
    }
}
