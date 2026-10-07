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
        val noDuplicateLeft = userPairs.map { it.left }.toSet().size == userPairs.size
        val noDuplicateRight = userPairs.map { it.right }.toSet().size == userPairs.size
        val isCorrect = correctCount == correctPairs.size &&
            userPairs.size == correctPairs.size &&
            noDuplicateLeft &&
            noDuplicateRight
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
        val normalizedAccepted = acceptedAnswers.map { it.trim().lowercase() }.filter { it.isNotEmpty() }
        val isCorrect = normalizedUser.isNotEmpty() && normalizedAccepted.any { it == normalizedUser }

        // Fuzzy (partial credit) only for meaningful answers: a 1-2 char answer like "a"
        // would trivially satisfy contains() against any accepted answer.
        val isFuzzyCorrect = !isCorrect &&
            normalizedUser.length >= 3 &&
            normalizedAccepted.any { accepted ->
                normalizedUser.contains(accepted) || accepted.contains(normalizedUser)
            }

        return CheckResult(
            isCorrect = isCorrect,
            correctAnswer = acceptedAnswers.firstOrNull() ?: "",
            userAnswer = userAnswer,
            score = if (isCorrect) 1.0 else if (isFuzzyCorrect) 0.5 else 0.0,
            isFuzzy = isFuzzyCorrect
        )
    }

    // Helpers
    private fun normalizeSentence(sentence: String): String {
        return sentence.trim()
            .lowercase()
            // Strip Arabic diacritics (harakat), tatweel and superscript alef
            .replace(Regex("[\\u0640\\u064B-\\u0652\\u0670]"), "")
            // Keep all Unicode letters (incl. Arabic) and digits; drop punctuation
            .replace(Regex("[^\\p{L}\\p{N} ]"), "")
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
     * Swap the display language of an exercise: EN→FR→AR.
     *
     * Every spec type is handled (the `when` is exhaustive on purpose: adding a new
     * ExerciseSpec subtype becomes a compile error here instead of silently keeping the
     * English prompt for that type).
     *
     * The FR/AR fields always keep their canonical translations — only the display slots
     * are overwritten:
     *  - Choice / Fill / Wordbank / Match → promptEn
     *  - ClinicalCase → vignetteEn + questionEn
     *  - Reading → passageEn + questionEn
     *
     * Because this mutates the display slots, callers MUST derive every swap from the
     * original spec (rebuild it with ExerciseEntity.toExerciseSpec()); swapping from an
     * already-swapped spec would destroy the English text.
     * generateTrilingualVersions() does it correctly by always deriving its three
     * versions from the spec it is given.
     *
     * Options, acceptedAnswers, bank, correctOrder and pairs stay unchanged: they are
     * language-neutral medical terms ("protects", "flexes"…) that remain valid regardless
     * of the prompt language.
     */
    fun swapLanguage(
        spec: ExerciseSpec,
        targetLang: ExerciseLanguage
    ): ExerciseSpec {
        fun displayText(en: String, fr: String, ar: String): String =
            when (targetLang) {
                ExerciseLanguage.ENGLISH -> en
                ExerciseLanguage.FRENCH -> fr
                ExerciseLanguage.ARABIC -> ar
            }

        return when (spec) {
            is ExerciseSpec.Choice -> spec.copy(
                promptEn = displayText(spec.promptEn, spec.promptFr, spec.promptAr)
            )
            is ExerciseSpec.Fill -> spec.copy(
                promptEn = displayText(spec.promptEn, spec.promptFr, spec.promptAr)
            )
            is ExerciseSpec.Wordbank -> spec.copy(
                promptEn = displayText(spec.promptEn, spec.promptFr, spec.promptAr)
            )
            is ExerciseSpec.Match -> spec.copy(
                promptEn = displayText(spec.promptEn, spec.promptFr, spec.promptAr)
            )
            is ExerciseSpec.ClinicalCase -> spec.copy(
                vignetteEn = displayText(spec.vignetteEn, spec.vignetteFr, spec.vignetteAr),
                questionEn = displayText(spec.questionEn, spec.questionFr, spec.questionAr)
            )
            is ExerciseSpec.Reading -> spec.copy(
                passageEn = displayText(spec.passageEn, spec.passageFr, spec.passageAr),
                questionEn = displayText(spec.questionEn, spec.questionFr, spec.questionAr)
            )
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
