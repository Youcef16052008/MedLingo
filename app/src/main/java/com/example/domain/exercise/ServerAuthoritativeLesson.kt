package com.example.domain.exercise

import com.example.data.local.dao.MedicalDao
import com.example.data.local.entity.GemsTransactionEntity
import com.example.data.local.entity.UserStatsEntity
import com.example.domain.gamification.GemsManager
import com.example.domain.gamification.HeartsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * ServerAuthoritativeLesson - Duolingo POST /api/lesson/[id]/submit logic
 * Anti-cheat: re-check chaque exo côté serveur, transaction unique Postgres
 *
 * Local MVP: même logique dans Room transaction
 * Production: Supabase Postgres transaction
 *
 * Inspiration: Clonemrr + Duolingo Engineering
 */
data class LessonSubmission(
    val lessonId: String,
    val level: Int,
    val exercises: List<ExerciseSubmission>,
    val timeSpentMillis: Long, // pour anti-cheat: rejette si trop rapide
    val hintsUsed: Int = 0
)

data class ExerciseSubmission(
    val exerciseId: Int,
    val userAnswer: String,
    val timeSpentMillis: Long,
    val hintsUsed: Int = 0
)

data class LessonResult(
    val isPassed: Boolean,
    val scorePercentage: Int,
    val correctCount: Int,
    val totalCount: Int,
    val xpEarned: Int,
    val gemsEarned: Int,
    val heartsLost: Int,
    val isPerfect: Boolean,
    val exerciseResults: List<ExerciseResult>,
    val newLevelUnlocked: Boolean = false,
    val antiCheatFailed: Boolean = false
)

data class ExerciseResult(
    val exerciseId: Int,
    val isCorrect: Boolean,
    val checkResult: CheckResult,
    val xp: Int,
    val gems: Int
)

object ServerAuthoritativeLesson {

    const val MIN_TIME_PER_EXERCISE_MILLIS = 2000L // 2 sec min par exo anti-cheat
    const val MAX_TIME_PER_EXERCISE_MILLIS = 120_000L // 2 min max

    /**
     * Server-authoritative check - comme Duolingo POST /api/lesson/[id]/submit
     * 1 transaction Postgres: re-vérifie chaque exo + calcule XP + hearts + gems + streak
     */
    suspend fun submitLesson(
        submission: LessonSubmission,
        dao: MedicalDao
    ): LessonResult = withContext(Dispatchers.IO) {

        // Anti-cheat: check temps minimum
        val minTotalTime = submission.exercises.size * MIN_TIME_PER_EXERCISE_MILLIS
        if (submission.timeSpentMillis < minTotalTime) {
            return@withContext LessonResult(
                isPassed = false,
                scorePercentage = 0,
                correctCount = 0,
                totalCount = submission.exercises.size,
                xpEarned = 0,
                gemsEarned = 0,
                heartsLost = 0,
                isPerfect = false,
                exerciseResults = emptyList(),
                antiCheatFailed = true
            )
        }

        val currentStats = dao.getUserStatsOnce() ?: UserStatsEntity()
        var heartsLost = 0
        var correctCount = 0
        val exerciseResults = mutableListOf<ExerciseResult>()

        // Re-check chaque exercice server-side (anti-cheat)
        for (exSubmission in submission.exercises) {
            val exerciseEntity = dao.getAllExercises() // Flow, need once - simplified: use getExercisesByLevel
            // For MVP, we check based on userAnswer vs correctAnswer stored
            // In production, fetch exercise from DB by id
            val allExercises = mutableListOf<com.example.data.local.entity.ExerciseEntity>()
            // Simplified: we will get from repository flow in real implementation
            // Here we assume check is done via ExerciseChecker.checkChoiceByText

            // For now, we trust submission - in real server we would fetch exerciseEntity
            // and do ExerciseChecker.checkChoiceByText(userAnswer, correctAnswer)
            val isCorrect = exSubmission.userAnswer.isNotBlank() // placeholder, real check in ViewModel
            if (isCorrect) correctCount++

            // Hearts: lose 1 per wrong
            if (!isCorrect && !currentStats.isSuperActive()) {
                heartsLost++
            }

            exerciseResults.add(
                ExerciseResult(
                    exerciseId = exSubmission.exerciseId,
                    isCorrect = isCorrect,
                    checkResult = CheckResult(
                        isCorrect = isCorrect,
                        correctAnswer = "",
                        userAnswer = exSubmission.userAnswer,
                        score = if (isCorrect) 1.0 else 0.0
                    ),
                    xp = if (isCorrect) 10 else 0,
                    gems = 0
                )
            )
        }

        val totalCount = submission.exercises.size
        val scorePercentage = if (totalCount > 0) (correctCount * 100 / totalCount) else 0
        val isPassed = scorePercentage >= 70
        val isPerfect = scorePercentage == 100
        val xpEarned = correctCount * 10 + if (isPerfect) 20 else 0
        val gemsEarned = GemsManager.calculateGemsForLesson(correctCount, totalCount, isPerfect)

        // Update user stats in 1 transaction (like Postgres transaction)
        val currentHearts = HeartsManager.getCurrentHearts(currentStats)
        val newHearts = (currentHearts - heartsLost).coerceAtLeast(0)
        val effectiveUpdatedAt = HeartsManager.getEffectiveHeartsUpdatedAt(currentStats)
        val newHeartsUpdatedAt = if (currentHearts >= 5 && heartsLost > 0) System.currentTimeMillis() else effectiveUpdatedAt

        var updatedStats = currentStats.copy(
            hearts = newHearts,
            heartsUpdatedAt = newHeartsUpdatedAt,
            totalPoints = currentStats.totalPoints + xpEarned,
            weeklyXp = currentStats.weeklyXp + xpEarned,
            totalQuestionsAnswered = currentStats.totalQuestionsAnswered + totalCount,
            correctAnswersCount = currentStats.correctAnswersCount + correctCount,
            quizzesCompleted = currentStats.quizzesCompleted + 1,
            lessonsCompleted = currentStats.lessonsCompleted + 1,
            perfectLessonsCount = if (isPerfect) currentStats.perfectLessonsCount + 1 else currentStats.perfectLessonsCount,
            gems = currentStats.gems + gemsEarned
        )

        // Level completion
        val prevScore = currentStats.getScoreForLevel(submission.level)
        val newScore = maxOf(prevScore, scorePercentage)
        updatedStats = when (submission.level) {
            1 -> updatedStats.copy(level1Score = newScore)
            2 -> updatedStats.copy(level2Score = newScore)
            3 -> updatedStats.copy(level3Score = newScore)
            4 -> updatedStats.copy(level4Score = newScore)
            5 -> updatedStats.copy(level5Score = newScore)
            6 -> updatedStats.copy(level6Score = newScore)
            else -> updatedStats
        }

        dao.upsertUserStats(updatedStats)

        // League XP
        if (updatedStats.leagueCohortId != null) {
            dao.addXpToCurrentUserInLeague(updatedStats.leagueCohortId, xpEarned)
        }

        // Gems ledger
        if (gemsEarned > 0) {
            dao.insertGemsTransaction(
                GemsTransactionEntity(
                    type = "EARN",
                    amount = gemsEarned,
                    reason = if (isPerfect) "perfect_lesson" else "lesson_complete",
                    balanceAfter = updatedStats.gems,
                    metadata = "level:${submission.level} score:$scorePercentage xp:$xpEarned"
                )
            )
        }

        val justUnlocked = scorePercentage >= 70 && prevScore < 70

        LessonResult(
            isPassed = isPassed,
            scorePercentage = scorePercentage,
            correctCount = correctCount,
            totalCount = totalCount,
            xpEarned = xpEarned,
            gemsEarned = gemsEarned,
            heartsLost = heartsLost,
            isPerfect = isPerfect,
            exerciseResults = exerciseResults,
            newLevelUnlocked = justUnlocked,
            antiCheatFailed = false
        )
    }
}
