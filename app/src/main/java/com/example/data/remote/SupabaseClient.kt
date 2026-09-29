package com.example.data.remote

import android.content.Context
import com.example.data.local.entity.UserStatsEntity
import com.example.domain.exercise.ExerciseLanguage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * SupabaseClient - Remote data source for MedLingo
 * Like Duolingo Galaxy Apps microservices but via Supabase
 * Handles offline-first sync: Room is source of truth, Supabase is backup
 */

data class SupabaseConfig(
    val url: String,
    val anonKey: String,
    val serviceRoleKey: String? = null
)

class SupabaseClient(
    private val context: Context,
    private val config: SupabaseConfig
) {
    // In production, use Supabase Kotlin SDK
    // For MVP, we simulate with local + Retrofit

    /**
     * Sync user stats to cloud (offline-first)
     */
    suspend fun syncUserStats(stats: UserStatsEntity): Boolean = withContext(Dispatchers.IO) {
        try {
            // TODO: Real Supabase call
            // supabase.from("users").upsert(...)
            println("[Supabase] Sync user stats: ${stats.totalPoints} XP, ${stats.gems} gems, ${stats.hearts} hearts")
            true
        } catch (e: Exception) {
            println("[Supabase] Sync failed: ${e.message}")
            false
        }
    }

    /**
     * Fetch session from Session Generator Edge Function
     * Like Duolingo Session Generator Scala 14ms
     */
    suspend fun fetchSession(
        userId: String,
        level: Int,
        unitId: String? = null,
        count: Int = 10,
        language: ExerciseLanguage = ExerciseLanguage.ENGLISH
    ): SessionResponse? = withContext(Dispatchers.IO) {
        try {
            // TODO: Real Edge Function call
            // val response = retrofit.post("functions/v1/session-generator", ...)
            println("[Supabase] Fetch session: user=$userId level=$level unit=$unitId lang=$language")
            
            // Mock response for MVP
            SessionResponse(
                sessionId = "mock-${System.currentTimeMillis()}",
                userId = userId,
                level = level,
                exercises = emptyList(),
                xpReward = count * 10,
                gemsReward = 10
            )
        } catch (e: Exception) {
            println("[Supabase] Fetch session failed: ${e.message}")
            null
        }
    }

    /**
     * Submit lesson server-authoritative (anti-cheat)
     */
    suspend fun submitLesson(
        userId: String,
        level: Int,
        correctAnswers: Int,
        totalQuestions: Int,
        timeSpentMillis: Long
    ): LessonSubmitResponse? = withContext(Dispatchers.IO) {
        try {
            println("[Supabase] Submit lesson: user=$userId level=$level $correctAnswers/$totalQuestions time=${timeSpentMillis}ms")
            
            // Anti-cheat check server-side
            val minTime = totalQuestions * 2000L
            if (timeSpentMillis < minTime) {
                return@withContext LessonSubmitResponse(
                    isAntiCheatFailed = true,
                    xpEarned = 0,
                    gemsEarned = 0
                )
            }

            val score = if (totalQuestions > 0) (correctAnswers * 100 / totalQuestions) else 0
            val isPerfect = score == 100
            val xp = correctAnswers * 10 + if (isPerfect) 20 else 0
            val gems = if (score < 50) 0 else if (isPerfect) 20 else 10

            LessonSubmitResponse(
                isAntiCheatFailed = false,
                xpEarned = xp,
                gemsEarned = gems,
                isPassed = score >= 70,
                scorePercentage = score
            )
        } catch (e: Exception) {
            println("[Supabase] Submit lesson failed: ${e.message}")
            null
        }
    }

    /**
     * Update Birdbrain vector
     */
    suspend fun updateBirdbrain(
        userId: String,
        exerciseId: String,
        isCorrect: Boolean,
        timeSpentMillis: Long,
        hintsUsed: Int,
        currentVector: List<Float>
    ): BirdbrainResponse? = withContext(Dispatchers.IO) {
        try {
            println("[Supabase] Birdbrain update: user=$userId ex=$exerciseId correct=$isCorrect")
            
            // Simplified Birdbrain update
            val newVector = currentVector.toMutableList()
            if (newVector.isEmpty()) {
                newVector.addAll(List(40) { 0f })
            }
            val abilityDelta = if (isCorrect) 0.05f else -0.05f
            newVector[0] = (newVector[0] + abilityDelta).coerceIn(0.05f, 0.95f)

            BirdbrainResponse(
                newVector = newVector,
                abilityDelta = abilityDelta,
                predictedForgetProb = 0.3f,
                nextReviewInHours = 24f
            )
        } catch (e: Exception) {
            println("[Supabase] Birdbrain update failed: ${e.message}")
            null
        }
    }

    /**
     * Fetch league leaderboard via Realtime
     */
    suspend fun fetchLeagueLeaderboard(cohortId: String): List<LeagueMemberRemote>? = withContext(Dispatchers.IO) {
        try {
            println("[Supabase] Fetch league: $cohortId")
            // TODO: Real Supabase Realtime
            emptyList()
        } catch (e: Exception) {
            println("[Supabase] Fetch league failed: ${e.message}")
            null
        }
    }

    /**
     * Send notification via Edge Function
     */
    suspend fun sendNotification(
        type: String,
        userIds: List<String>? = null,
        title: String? = null,
        body: String? = null
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            println("[Supabase] Send notification: type=$type users=${userIds?.size ?: "ALL"}")
            // TODO: Call notifications Edge Function
            true
        } catch (e: Exception) {
            println("[Supabase] Send notification failed: ${e.message}")
            false
        }
    }
}

data class SessionResponse(
    val sessionId: String,
    val userId: String,
    val level: Int,
    val exercises: List<Any>,
    val xpReward: Int,
    val gemsReward: Int
)

data class LessonSubmitResponse(
    val isAntiCheatFailed: Boolean,
    val xpEarned: Int,
    val gemsEarned: Int,
    val isPassed: Boolean = false,
    val scorePercentage: Int = 0
)

data class BirdbrainResponse(
    val newVector: List<Float>,
    val abilityDelta: Float,
    val predictedForgetProb: Float,
    val nextReviewInHours: Float
)

data class LeagueMemberRemote(
    val userId: String,
    val displayName: String,
    val avatarEmoji: String,
    val weeklyXp: Int,
    val rank: Int
)
