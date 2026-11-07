package com.aistudio.medlingua.dzmed.domain.exercise

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
        val isCorrect = normalize(selectedText) == normalize(correctText)
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
     * Check Fill blank — Duolingo-grade corrector.
     * Accepts exact match, accent/case/punctuation-insensitive match,
     * and near-misses (typos) within edit-distance tolerance.
     */
    fun checkFill(userAnswer: String, acceptedAnswers: List<String>): CheckResult {
        val normalizedUser = normalize(userAnswer)
        val normalizedAccepted = acceptedAnswers.map { normalize(it) }.filter { it.isNotEmpty() }

        // Exact match
        if (normalizedUser.isNotEmpty() && normalizedAccepted.any { it == normalizedUser }) {
            return CheckResult(
                isCorrect = true,
                correctAnswer = acceptedAnswers.firstOrNull() ?: "",
                userAnswer = userAnswer,
                score = 1.0,
                suggestion = acceptedAnswers.firstOrNull()
            )
        }

        // Near-miss (typo) tolerance — Duolingo "almost correct"
        val maxDist = if (normalizedUser.length <= 4) 1 else (normalizedUser.length / 10).coerceAtLeast(1).coerceAtMost(2)
        var bestDist = Int.MAX_VALUE
        var bestAccepted: String? = null
        for (accepted in normalizedAccepted) {
            val d = levenshtein(normalizedUser, accepted)
            if (d < bestDist) { bestDist = d; bestAccepted = accepted }
        }
        val isAlmost = normalizedUser.length >= 3 && bestDist in 1..maxDist
        if (isAlmost) {
            // Map normalized bestAccepted back to original accepted answer (with accents)
            val originalSuggestion = acceptedAnswers.firstOrNull { normalize(it) == bestAccepted } ?: acceptedAnswers.firstOrNull()
            return CheckResult(
                isCorrect = false,
                correctAnswer = acceptedAnswers.firstOrNull() ?: "",
                userAnswer = userAnswer,
                score = 0.9,
                isFuzzy = true,
                explanation = "typo",
                suggestion = originalSuggestion
            )
        }

        // R-19 (2026-11-07): arbitrary substring containment no longer earns
        // partial credit (it allowed farming half-XP with 3-letter fragments).
        // Only genuine near-misses (handled above) get partial credit.
        return CheckResult(
            isCorrect = false,
            correctAnswer = acceptedAnswers.firstOrNull() ?: "",
            userAnswer = userAnswer,
            score = 0.0,
            isFuzzy = false
        )
    }

    // Helpers
    fun normalize(text: String): String {
        val nfd = java.text.Normalizer.normalize(text, java.text.Normalizer.Form.NFD)
        return nfd.trim()
            .lowercase()
            // Strip Arabic diacritics (harakat), tatweel and superscript alef
            .replace(Regex("[\\u0640\\u064B-\\u0652\\u0670]"), "")
            // Strip combining marks (accents) for Latin medical terms
            .replace(Regex("\\p{Mn}"), "")
            // Keep all Unicode letters (incl. Arabic) and digits; drop punctuation
            .replace(Regex("[^\\p{L}\\p{N} ]"), "")
            .replace(Regex("\\s+"), " ")
            .trim()
    }

    private fun normalizeSentence(sentence: String): String = normalize(sentence)

    private fun levenshtein(a: String, b: String): Int {
        if (a == b) return 0
        if (a.isEmpty()) return b.length
        if (b.isEmpty()) return a.length
        val prev = IntArray(b.length + 1) { it }
        val curr = IntArray(b.length + 1)
        for (i in a.indices) {
            curr[0] = i + 1
            for (j in b.indices) {
                val cost = if (a[i] == b[j]) 0 else 1
                curr[j + 1] = minOf(curr[j] + 1, prev[j + 1] + 1, prev[j] + cost)
            }
            for (j in 0..b.length) prev[j] = curr[j]
        }
        return prev[b.length]
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
    val explanation: String? = null,
    val suggestion: String? = null  // Duolingo-style "you have a typo": closest accepted answer
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
