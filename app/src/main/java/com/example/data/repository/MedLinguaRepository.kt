package com.example.data.repository

import com.example.data.initial.InitialData
import com.example.data.local.dao.MedicalDao
import com.example.data.local.entity.DownloadedModuleEntity
import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.FlashcardProgressEntity
import com.example.data.local.entity.MedicalTermEntity
import com.example.data.local.entity.UserStatsEntity
import com.example.domain.sm2.ReviewResult
import com.example.domain.sm2.SpacedRepetitionAlgorithm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MedLinguaRepository(private val dao: MedicalDao) {

    val allTerms: Flow<List<MedicalTermEntity>> = dao.getAllTerms()
    val allExercises: Flow<List<ExerciseEntity>> = dao.getAllExercises()
    val allFlashcardProgress: Flow<List<FlashcardProgressEntity>> = dao.getAllFlashcardProgress()
    val allDownloadedModules: Flow<List<DownloadedModuleEntity>> = dao.getAllDownloadedModules()
    val userStats: Flow<UserStatsEntity?> = dao.getUserStats()

    fun getExercisesByLevel(level: Int): Flow<List<ExerciseEntity>> = dao.getExercisesByLevel(level)

    suspend fun initializeDatabaseIfEmpty() = withContext(Dispatchers.IO) {
        // ALWAYS ensure all curriculum terms and exercises are inserted/updated in database
        dao.insertTerms(InitialData.terms)
        dao.insertExercises(InitialData.exercises)

        // Ensure all 8 modules are populated and synced in the offline repository
        InitialData.modulesList.forEach { mod ->
            dao.upsertDownloadedModule(
                DownloadedModuleEntity(
                    moduleId = mod.id,
                    moduleName = mod.titleFr,
                    downloadedTimestamp = System.currentTimeMillis(),
                    sizeMb = mod.estimatedSizeMb,
                    termsCount = InitialData.terms.count {
                        it.module.equals(mod.titleFr, ignoreCase = true) ||
                                it.module.contains(mod.titleFr, ignoreCase = true) ||
                                mod.titleFr.contains(it.module, ignoreCase = true)
                    },
                    exercisesCount = InitialData.exercises.count {
                        it.module.equals(mod.titleFr, ignoreCase = true) ||
                                it.module.contains(mod.titleFr, ignoreCase = true) ||
                                mod.titleFr.contains(it.module, ignoreCase = true)
                    },
                    isDownloaded = true
                )
            )
        }

        val stats = dao.getUserStatsOnce()
        if (stats == null) {
            // Seed initial flashcard progress for a few terms so user has review items right away
            val initialProgress = InitialData.terms.take(8).mapIndexed { index, term ->
                FlashcardProgressEntity(
                    termId = term.id,
                    repetitions = if (index % 2 == 0) 1 else 0,
                    easeFactor = 2.5,
                    intervalDays = 1,
                    nextReviewTimestamp = System.currentTimeMillis() - 1000L, // Due now!
                    lastReviewedTimestamp = System.currentTimeMillis() - 86400000L,
                    lastQuality = 3
                )
            }
            initialProgress.forEach { dao.upsertFlashcardProgress(it) }

            dao.upsertUserStats(
                UserStatsEntity(
                    id = 1,
                    streakDays = 12,
                    learnedTermsCount = InitialData.terms.size,
                    totalPoints = 2850,
                    quizzesCompleted = 18,
                    totalQuestionsAnswered = 92,
                    correctAnswersCount = 78,
                    isOfflineSimulated = false,
                    selectedLanguageCode = "fr",
                    level1Score = 85,
                    level2Score = 75,
                    level3Score = 0,
                    level4Score = 0,
                    level5Score = 0,
                    level6Score = 0
                )
            )
        } else if (stats.learnedTermsCount < InitialData.terms.size) {
            dao.upsertUserStats(stats.copy(learnedTermsCount = InitialData.terms.size))
        }
    }

    fun getTermsByModule(module: String): Flow<List<MedicalTermEntity>> = dao.getTermsByModule(module)

    fun searchTerms(query: String): Flow<List<MedicalTermEntity>> = dao.searchTerms(query)

    suspend fun getTermById(id: Int): MedicalTermEntity? = dao.getTermById(id)

    suspend fun toggleBookmark(term: MedicalTermEntity) = withContext(Dispatchers.IO) {
        dao.updateTerm(term.copy(isBookmarked = !term.isBookmarked))
    }

    suspend fun submitFlashcardRating(termId: Int, quality: Int): ReviewResult = withContext(Dispatchers.IO) {
        val existing = dao.getFlashcardProgress(termId)
        val currentReps = existing?.repetitions ?: 0
        val currentEase = existing?.easeFactor ?: 2.5
        val currentInterval = existing?.intervalDays ?: 0

        val result = SpacedRepetitionAlgorithm.calculateNext(
            quality = quality,
            previousRepetitions = currentReps,
            previousEaseFactor = currentEase,
            previousIntervalDays = currentInterval
        )

        val updatedProgress = FlashcardProgressEntity(
            termId = termId,
            repetitions = result.repetitions,
            easeFactor = result.easeFactor,
            intervalDays = result.intervalDays,
            nextReviewTimestamp = result.nextReviewTimestamp,
            lastReviewedTimestamp = System.currentTimeMillis(),
            lastQuality = quality
        )
        dao.upsertFlashcardProgress(updatedProgress)

        // Award points in UserStats
        val currentStats = dao.getUserStats()
        // Simple default fallback
        val stats = UserStatsEntity(
            id = 1,
            totalPoints = (existing?.let { 2450 } ?: 2450) + (quality * 5)
        )
        dao.upsertUserStats(stats)

        result
    }

    suspend fun recordQuizCompletion(pointsEarned: Int, totalQuestions: Int, correctAnswers: Int) = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: UserStatsEntity()
        val currentStats = current.copy(
            totalPoints = current.totalPoints + pointsEarned,
            quizzesCompleted = current.quizzesCompleted + 1,
            totalQuestionsAnswered = current.totalQuestionsAnswered + totalQuestions,
            correctAnswersCount = current.correctAnswersCount + correctAnswers
        )
        dao.upsertUserStats(currentStats)
    }

    suspend fun saveUserStats(stats: UserStatsEntity) = withContext(Dispatchers.IO) {
        dao.upsertUserStats(stats)
    }

    suspend fun recordLevelCompletion(level: Int, scorePercentage: Int, pointsEarned: Int, totalQuestions: Int, correctAnswers: Int): Boolean = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: UserStatsEntity()
        val prevScore = current.getScoreForLevel(level)
        val newScore = maxOf(prevScore, scorePercentage)

        val updated = when (level) {
            1 -> current.copy(level1Score = newScore)
            2 -> current.copy(level2Score = newScore)
            3 -> current.copy(level3Score = newScore)
            4 -> current.copy(level4Score = newScore)
            5 -> current.copy(level5Score = newScore)
            6 -> current.copy(level6Score = newScore)
            else -> current
        }.copy(
            totalPoints = current.totalPoints + pointsEarned,
            quizzesCompleted = current.quizzesCompleted + 1,
            totalQuestionsAnswered = current.totalQuestionsAnswered + totalQuestions,
            correctAnswersCount = current.correctAnswersCount + correctAnswers
        )
        dao.upsertUserStats(updated)

        // Return whether this completion newly unlocked the next level
        scorePercentage >= 70 && prevScore < 70
    }

    suspend fun toggleModuleDownload(moduleInfo: InitialData.ModuleInfo, isDownload: Boolean) = withContext(Dispatchers.IO) {
        if (isDownload) {
            dao.upsertDownloadedModule(
                DownloadedModuleEntity(
                    moduleId = moduleInfo.id,
                    moduleName = moduleInfo.titleFr,
                    downloadedTimestamp = System.currentTimeMillis(),
                    sizeMb = moduleInfo.estimatedSizeMb,
                    termsCount = 10,
                    exercisesCount = 5,
                    isDownloaded = true
                )
            )
        } else {
            dao.deleteDownloadedModule(moduleInfo.id)
        }
    }

    suspend fun updateLanguage(langCode: String) = withContext(Dispatchers.IO) {
        dao.upsertUserStats(
            UserStatsEntity(
                id = 1,
                selectedLanguageCode = langCode
            )
        )
    }

    suspend fun toggleOfflineSimulation(isOffline: Boolean) = withContext(Dispatchers.IO) {
        dao.upsertUserStats(
            UserStatsEntity(
                id = 1,
                isOfflineSimulated = isOffline
            )
        )
    }
}
