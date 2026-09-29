package com.example.domain.birdbrain

import com.example.data.local.entity.UserStatsEntity
import kotlin.math.exp
import kotlin.math.pow
import kotlin.random.Random

/**
 * BirdbrainLite - Duolingo Birdbrain V2 LSTM 40-dim vector lite implementation
 * Original: PyTorch P3 GPU, 1B exercises/day, 14ms, tens of billions inferences/day, Spark
 * Lite: Kotlin local, 40-dim vector, SM-2 + heuristics, no GPU needed
 *
 * Architecture:
 * - Dim 0: overall ability (Elo-like)
 * - Dim 1-6: ability per level (1..6)
 * - Dim 7: speed (fast vs slow)
 * - Dim 11: hint dependency
 * - Dim 16: consistency (streak)
 * - Dim 21-30: module abilities (anat, physio, etc.)
 * - Dim 31-39: reserved + noise
 *
 * HLR (Half-Life Regression): p = 2^(-t/h) predict forget prob
 */

data class BirdbrainState(
    val vector: List<Float> = List(40) { 0f }, // 40-dim
    val ability: Float = 0.5f, // scalar 0..1
    val halfLifeHours: Float = 24f,
    val lastUpdated: Long = System.currentTimeMillis()
)

data class ExerciseFeatures(
    val exerciseId: Int,
    val level: Int,
    val module: String,
    val type: String, // mcq, wordbank, match, fill, clinical_case, reading
    val difficulty: Float, // 0..1 predicted
    val discrimination: Float = 0.5f // how well it discriminates ability
)

data class UserResponse(
    val isCorrect: Boolean,
    val timeSpentMillis: Long,
    val hintsUsed: Int,
    val timeSinceLastReviewHours: Float = 24f
)

object BirdbrainLite {

    const val VECTOR_SIZE = 40
    const val MIN_ABILITY = 0.05f
    const val MAX_ABILITY = 0.95f

    // Module to vector index mapping
    private val moduleToIndex = mapOf(
        "Anatomie" to 21, "Anatomie Pathologique" to 21,
        "Physiologie" to 22,
        "Biochimie" to 23,
        "Histologie" to 24,
        "Biophysique" to 25,
        "Génétique" to 26, "Génétique" to 26,
        "Terminologie Médicale" to 27, "Terminologie" to 27,
        "Anglais Médical" to 28, "Clinical Medical English" to 28,
        "Microbiologie" to 29,
        "Pharmacologie" to 30,
        "Sémiologie Médicale" to 31, "Sémiologie" to 31,
        "Cytologie" to 32,
        "Informatique Médicale" to 33,
        "Embryologie" to 34
    )

    /**
     * Initialize vector from UserStats (XP → ability)
     */
    fun initializeVector(stats: UserStatsEntity): BirdbrainState {
        val xp = stats.totalPoints
        val ability = (xp / 5000f).coerceIn(MIN_ABILITY, MAX_ABILITY)
        
        val vector = MutableList(VECTOR_SIZE) { 0f }
        vector[0] = ability // overall ability
        
        // Level abilities from scores
        vector[1] = stats.level1Score / 100f
        vector[2] = stats.level2Score / 100f
        vector[3] = stats.level3Score / 100f
        vector[4] = stats.level4Score / 100f
        vector[5] = stats.level5Score / 100f
        vector[6] = stats.level6Score / 100f
        
        // Speed: average from accuracy
        vector[7] = stats.accuracyPercentage / 100f
        
        // Consistency from streak
        vector[16] = (stats.streakDays / 30f).coerceIn(0f, 1f)
        
        return BirdbrainState(
            vector = vector,
            ability = ability,
            halfLifeHours = 24f * (1 + ability * 2),
            lastUpdated = System.currentTimeMillis()
        )
    }

    /**
     * Update vector after user response - like LSTM update
     * Input: prior state + exercise features + correct? + time gaps
     */
    fun updateVector(
        currentState: BirdbrainState,
        exercise: ExerciseFeatures,
        response: UserResponse
    ): BirdbrainState {
        val currentVector = currentState.vector.toMutableList()
        if (currentVector.size != VECTOR_SIZE) {
            currentVector.clear()
            currentVector.addAll(List(VECTOR_SIZE) { 0f })
        }

        // Time factor: fast = higher ability delta (like Duolingo)
        val timeFactor = (10000f / response.timeSpentMillis.coerceAtLeast(1000L).toFloat()).coerceIn(0.5f, 1.5f)
        val hintPenalty = response.hintsUsed * 0.1f
        val correctnessFactor = if (response.isCorrect) 1f else -1f
        
        val abilityDelta = (correctnessFactor * 0.05f * timeFactor) - hintPenalty
        var newAbility = (currentVector[0] + abilityDelta).coerceIn(MIN_ABILITY, MAX_ABILITY)
        currentVector[0] = newAbility

        // Level ability update
        val levelIdx = exercise.level.coerceIn(1, 6)
        if (levelIdx < currentVector.size) {
            currentVector[levelIdx] = (currentVector[levelIdx] + abilityDelta * 0.5f).coerceIn(0f, 1f)
        }

        // Speed update (dim 7)
        val speedIdx = 7
        val currentSpeed = currentVector[speedIdx]
        val newSpeed = if (response.isCorrect) {
            currentSpeed * 0.9f + if (timeFactor > 1) 0.1f else 0f
        } else {
            currentSpeed * 0.9f - 0.05f
        }
        currentVector[speedIdx] = newSpeed.coerceIn(0f, 1f)

        // Hint dependency (dim 11)
        val hintIdx = 11
        currentVector[hintIdx] = (currentVector[hintIdx] + if (response.hintsUsed > 0) 0.05f else -0.02f).coerceIn(0f, 1f)

        // Consistency (dim 16)
        val consistencyIdx = 16
        currentVector[consistencyIdx] = if (response.isCorrect) {
            (currentVector[consistencyIdx] + 0.03f).coerceIn(0f, 1f)
        } else {
            (currentVector[consistencyIdx] - 0.1f).coerceIn(0f, 1f)
        }

        // Module ability (dim 21-30)
        val moduleIdx = moduleToIndex[exercise.module] ?: moduleToIndex.entries.find { exercise.module.contains(it.key, ignoreCase = true) }?.value ?: 21
        if (moduleIdx < currentVector.size) {
            currentVector[moduleIdx] = (currentVector[moduleIdx] + abilityDelta * 0.3f).coerceIn(0f, 1f)
        }

        // Exercise type ability (dim 31-35)
        val typeMap = mapOf(
            "mcq" to 31, "fill_blank" to 32, "matching" to 33,
            "sentence_order" to 34, "reading" to 35, "clinical_case" to 35
        )
        val typeIdx = typeMap[exercise.type] ?: 31
        if (typeIdx < currentVector.size) {
            currentVector[typeIdx] = (currentVector[typeIdx] + abilityDelta * 0.2f).coerceIn(0f, 1f)
        }

        // Add small noise like real LSTM (dim 36-39)
        for (i in 36 until VECTOR_SIZE) {
            currentVector[i] = (currentVector[i] + (Random.nextFloat() - 0.5f) * 0.01f).coerceIn(-1f, 1f)
        }

        // HLR Half-life update
        val baseHalfLife = 24f
        val abilityMultiplier = 1 + newAbility * 2
        val correctnessMultiplier = if (response.isCorrect) 1.5f else 0.5f
        val halfLifeHours = baseHalfLife * abilityMultiplier * correctnessMultiplier

        return BirdbrainState(
            vector = currentVector,
            ability = newAbility,
            halfLifeHours = halfLifeHours,
            lastUpdated = System.currentTimeMillis()
        )
    }

    /**
     * Predict next exercise - 14ms decision like Duolingo
     * Score exercises by difficulty match to ability
     */
    fun predictNextExercise(
        state: BirdbrainState,
        candidates: List<ExerciseFeatures>
    ): List<Pair<ExerciseFeatures, Float>> {
        val ability = state.ability
        val idealDifficulty = (ability + 0.1f).coerceIn(0.1f, 0.9f)

        return candidates.map { ex ->
            val difficultyMatch = 1f - kotlin.math.abs(ex.difficulty - idealDifficulty)
            val discriminationBoost = ex.discrimination * 0.2f
            
            // Boost for weak modules
            val moduleIdx = moduleToIndex[ex.module] ?: 21
            val moduleAbility = if (moduleIdx < state.vector.size) state.vector[moduleIdx] else 0.5f
            val weakBoost = (1f - moduleAbility) * 0.3f // weak areas get boost
            
            // Random for exploration (like Bandit)
            val randomBoost = Random.nextFloat() * 0.1f
            
            val score = difficultyMatch * 0.6f + discriminationBoost + weakBoost * 0.2f + randomBoost
            
            ex to score
        }.sortedByDescending { it.second }
    }

    /**
     * HLR: Predict forget probability p = 2^(-t/h)
     */
    fun predictForgetProbability(
        halfLifeHours: Float,
        hoursSinceLastReview: Float
    ): Float {
        return 1f - 2f.pow(-hoursSinceLastReview / halfLifeHours)
    }

    /**
     * When to next review: at 80% of half-life (like Duolingo)
     */
    fun nextReviewInHours(halfLifeHours: Float): Float {
        return halfLifeHours * 0.8f
    }

    /**
     * Explain My Answer - like Duolingo Birdbrain V2
     * Instead of "Wrong", explain why
     */
    fun explainAnswer(
        isCorrect: Boolean,
        exercise: ExerciseFeatures,
        userAnswer: String,
        correctAnswer: String
    ): String {
        if (isCorrect) {
            return "✅ Parfait! ${exercise.module} maîtrisé."
        }

        // Generate explanation based on type
        return when (exercise.type) {
            "mcq" -> {
                // Etymology explanation like MedicalTermEntity
                "❌ Pas tout à fait. La bonne réponse est '$correctAnswer'. " +
                "Rappel: ${exercise.module} - révise l'étymologie et la perle clinique."
            }
            "fill_blank" -> {
                "💡 Il fallait '$correctAnswer'. Astuce: décompose le terme en préfixe + racine + suffixe."
            }
            "sentence_order" -> {
                "📝 Ordre SVO: Sujet-Verbe-Objet. Correct: '$correctAnswer'. Ton ordre: '$userAnswer'"
            }
            else -> "❌ Réponse: '$correctAnswer'. Continue, tu progresses! 💪"
        }
    }
}
