package com.example.data.repository

import com.example.data.initial.InitialData
import com.example.data.local.dao.MedicalDao
import com.example.data.local.entity.DownloadedModuleEntity
import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.FlashcardProgressEntity
import com.example.data.local.entity.GemsTransactionEntity
import com.example.data.local.entity.LeagueCohortEntity
import com.example.data.local.entity.LeagueMemberEntity
import com.example.data.local.entity.MedicalTermEntity
import com.example.data.local.entity.UserStatsEntity
import com.example.domain.gamification.GemsManager
import com.example.domain.gamification.HeartsManager
import com.example.domain.gamification.LeagueManager
import com.example.domain.sm2.ReviewResult
import com.example.domain.sm2.SpacedRepetitionAlgorithm
import com.example.domain.time.Clock
import com.example.domain.time.SystemClock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MedLinguaRepository(private val dao: MedicalDao, private val clock: Clock = SystemClock) {

    val allTerms: Flow<List<MedicalTermEntity>> = dao.getAllTerms()
    val allExercises: Flow<List<ExerciseEntity>> = dao.getAllExercises()
    val allFlashcardProgress: Flow<List<FlashcardProgressEntity>> = dao.getAllFlashcardProgress()
    val allDownloadedModules: Flow<List<DownloadedModuleEntity>> = dao.getAllDownloadedModules()
    val userStats: Flow<UserStatsEntity?> = dao.getUserStats()
    val gemsTransactions: Flow<List<GemsTransactionEntity>> = dao.getAllGemsTransactions()
    val activeLeagueCohort: Flow<LeagueCohortEntity?> = dao.getActiveLeagueCohort()
    val allLeagueCohorts: Flow<List<LeagueCohortEntity>> = dao.getAllLeagueCohorts()

    fun getExercisesByLevel(level: Int): Flow<List<ExerciseEntity>> = dao.getExercisesByLevel(level)
    fun getLeagueMembers(cohortId: String): Flow<List<LeagueMemberEntity>> = dao.getLeagueMembers(cohortId)

    private val userStatsMutex = Mutex()

    private suspend fun safeUpsertUserStats(stats: UserStatsEntity) = withContext(Dispatchers.IO) {
        userStatsMutex.withLock {
            dao.upsertUserStats(stats)
        }
    }

    suspend fun initializeDatabaseIfEmpty() = withContext(Dispatchers.IO) {
        dao.insertTerms(InitialData.terms)

        // Phase 2: Migrate exercises to ExerciseSpec JSONB
        val migratedExercises = try {
            com.example.domain.exercise.ExerciseSpecMigrator.migrateAll(InitialData.exercises)
        } catch (e: Exception) {
            InitialData.exercises
        }
        dao.insertExercises(migratedExercises)

        val now = clock.now()

        InitialData.modulesList.forEach { mod ->
            dao.upsertDownloadedModule(
                DownloadedModuleEntity(
                    moduleId = mod.id,
                    moduleName = mod.titleFr,
                    downloadedTimestamp = now,
                    sizeMb = mod.estimatedSizeMb,
                    termsCount = InitialData.termsOfModule(mod.titleFr).size,
                    exercisesCount = InitialData.exercises.count {
                        it.module.equals(mod.titleFr, ignoreCase = true)
                    },
                    isDownloaded = true
                )
            )
        }

        val stats = dao.getUserStatsOnce()
        if (stats == null) {
            val initialProgress = InitialData.terms.take(8).mapIndexed { index, term ->
                FlashcardProgressEntity(
                    termId = term.id,
                    repetitions = if (index % 2 == 0) 1 else 0,
                    easeFactor = 2.5,
                    intervalDays = 1,
                    nextReviewTimestamp = now - 1000L,
                    lastReviewedTimestamp = now - 86400000L,
                    lastQuality = 3
                )
            }
            initialProgress.forEach { dao.upsertFlashcardProgress(it) }

            safeUpsertUserStats(
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
                    level6Score = 0,
                    hearts = 5,
                    heartsUpdatedAt = now,
                    gems = 100,
                    weeklyXp = 0,
                    leagueTier = "BRONZE",
                    isSuper = false,
                    streakFreezeCount = 1,
                    perfectLessonsCount = 0,
                    lessonsCompleted = 18
                )
            )

            // Init gems ledger with starting bonus
            dao.insertGemsTransaction(
                GemsTransactionEntity(
                    type = "EARN",
                    amount = 100,
                    reason = "welcome_bonus",
                    timestamp = now,
                    balanceAfter = 100,
                    metadata = "Initial gems"
                )
            )

            // Init league cohort
            val cohort = LeagueManager.createNewCohort("BRONZE")
            dao.upsertLeagueCohort(cohort)
            val bots = LeagueManager.generateBotsForCohort(
                cohortId = cohort.cohortId,
                currentUserName = "Dr. Youcef",
                currentUserXp = 0,
                currentUserStreak = 12
            )
            dao.upsertLeagueMembers(bots)

            // Update user with cohort id
            val updatedStats = dao.getUserStatsOnce()?.copy(leagueCohortId = cohort.cohortId)
            if (updatedStats != null) safeUpsertUserStats(updatedStats)

        } else {
            // Migration check: ensure new fields have defaults if old DB
            var needsUpdate = false
            var newStats = stats
            if (stats.hearts == 0 && stats.getCurrentHearts(clock) == 0) {
                if (!stats.isSuper) {
                    newStats = newStats.copy(hearts = 5, heartsUpdatedAt = now)
                    needsUpdate = true
                }
            }
            if (stats.learnedTermsCount < InitialData.terms.size) {
                newStats = newStats.copy(learnedTermsCount = InitialData.terms.size)
                needsUpdate = true
            }
            if (stats.leagueCohortId == null) {
                val activeCohort = dao.getLeagueCohortById(stats.leagueCohortId ?: "")
                if (activeCohort == null) {
                    val cohort = LeagueManager.createNewCohort(stats.leagueTier)
                    dao.upsertLeagueCohort(cohort)
                    val bots = LeagueManager.generateBotsForCohort(
                        cohortId = cohort.cohortId,
                        currentUserName = "Dr. Youcef",
                        currentUserXp = stats.weeklyXp,
                        currentUserStreak = stats.streakDays
                    )
                    dao.upsertLeagueMembers(bots)
                    newStats = newStats.copy(leagueCohortId = cohort.cohortId)
                    needsUpdate = true
                }
            }
            if (needsUpdate) safeUpsertUserStats(newStats)
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
            lastReviewedTimestamp = clock.now(),
            lastQuality = quality
        )
        dao.upsertFlashcardProgress(updatedProgress)

        val currentStats = dao.getUserStatsOnce() ?: UserStatsEntity()
        val currentHearts = HeartsManager.getCurrentHearts(currentStats)
        val effectiveUpdatedAt = HeartsManager.getEffectiveHeartsUpdatedAt(currentStats)

        val newStats = if (quality < 3) {
            if (currentHearts > 0) {
                currentStats.copy(
                    hearts = (currentHearts - 1).coerceAtLeast(0),
                    heartsUpdatedAt = if (currentHearts >= 5) clock.now() else effectiveUpdatedAt,
                    totalPoints = currentStats.totalPoints + (quality * 2)
                )
            } else currentStats
        } else {
            currentStats.copy(
                totalPoints = currentStats.totalPoints + (quality * 5),
                weeklyXp = currentStats.weeklyXp + (quality * 2)
            )
        }

        if (quality >= 3 && newStats.leagueCohortId != null) {
            dao.addXpToCurrentUserInLeague(newStats.leagueCohortId, quality * 2)
        }

        safeUpsertUserStats(newStats)
        result
    }

    suspend fun recordQuizCompletion(pointsEarned: Int, totalQuestions: Int, correctAnswers: Int) = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: UserStatsEntity()
        val currentStats = current.copy(
            totalPoints = current.totalPoints + pointsEarned,
            weeklyXp = current.weeklyXp + pointsEarned,
            quizzesCompleted = current.quizzesCompleted + 1,
            totalQuestionsAnswered = current.totalQuestionsAnswered + totalQuestions,
            correctAnswersCount = current.correctAnswersCount + correctAnswers
        )
        safeUpsertUserStats(currentStats)

        if (currentStats.leagueCohortId != null) {
            dao.addXpToCurrentUserInLeague(currentStats.leagueCohortId, pointsEarned)
        }

        val isPerfect = correctAnswers == totalQuestions && totalQuestions > 0
        val gemsEarned = GemsManager.calculateGemsForLesson(correctAnswers, totalQuestions, isPerfect)
        if (gemsEarned > 0) {
            val newBalance = currentStats.gems + gemsEarned
            dao.insertGemsTransaction(
                GemsTransactionEntity(
                    type = "EARN",
                    amount = gemsEarned,
                    reason = if (isPerfect) "perfect_lesson" else "lesson_complete",
                    timestamp = clock.now(),
                    balanceAfter = newBalance,
                    metadata = "points:$pointsEarned correct:$correctAnswers/$totalQuestions"
                )
            )
            safeUpsertUserStats(currentStats.copy(gems = newBalance))
        }
    }

    suspend fun saveUserStats(stats: UserStatsEntity) = withContext(Dispatchers.IO) {
        safeUpsertUserStats(stats)
    }

    suspend fun recordLevelCompletion(level: Int, scorePercentage: Int, pointsEarned: Int, totalQuestions: Int, correctAnswers: Int): Boolean = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: UserStatsEntity()
        val prevScore = current.getScoreForLevel(level)
        val newScore = maxOf(prevScore, scorePercentage)

        val isPerfect = scorePercentage == 100
        val gemsEarned = GemsManager.calculateGemsForLesson(correctAnswers, totalQuestions, isPerfect)

        var updated = when (level) {
            1 -> current.copy(level1Score = newScore)
            2 -> current.copy(level2Score = newScore)
            3 -> current.copy(level3Score = newScore)
            4 -> current.copy(level4Score = newScore)
            5 -> current.copy(level5Score = newScore)
            6 -> current.copy(level6Score = newScore)
            else -> current
        }.copy(
            totalPoints = current.totalPoints + pointsEarned,
            weeklyXp = current.weeklyXp + pointsEarned,
            quizzesCompleted = current.quizzesCompleted + 1,
            totalQuestionsAnswered = current.totalQuestionsAnswered + totalQuestions,
            correctAnswersCount = current.correctAnswersCount + correctAnswers,
            lessonsCompleted = current.lessonsCompleted + 1,
            perfectLessonsCount = if (isPerfect) current.perfectLessonsCount + 1 else current.perfectLessonsCount,
            gems = current.gems + gemsEarned
        )
        safeUpsertUserStats(updated)

        if (updated.leagueCohortId != null) {
            dao.addXpToCurrentUserInLeague(updated.leagueCohortId, pointsEarned)
        }

        if (gemsEarned > 0) {
            dao.insertGemsTransaction(
                GemsTransactionEntity(
                    type = "EARN",
                    amount = gemsEarned,
                    reason = if (isPerfect) "perfect_lesson" else "lesson_complete",
                    timestamp = clock.now(),
                    balanceAfter = updated.gems,
                    metadata = "level:$level score:$scorePercentage"
                )
            )
        }

        scorePercentage >= 70 && prevScore < 70
    }

    suspend fun loseHeart(): Boolean = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: return@withContext false
        if (current.isSuperActive(clock)) return@withContext true
        val currentHearts = HeartsManager.getCurrentHearts(current)
        if (currentHearts <= 0) return@withContext false

        val newStats = HeartsManager.loseHeart(current)
        safeUpsertUserStats(newStats)
        true
    }

    suspend fun refillHeartsWithGems(): Boolean = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: return@withContext false
        val (newStats, success) = HeartsManager.refillHeartsWithGems(current)
        if (!success) return@withContext false

        safeUpsertUserStats(newStats)
        dao.insertGemsTransaction(
            GemsTransactionEntity(
                type = "SPEND",
                amount = -GemsManager.GEMS_HEART_REFILL,
                reason = "heart_refill",
                timestamp = clock.now(),
                balanceAfter = newStats.gems
            )
        )
        true
    }

    suspend fun earnHeartFromPractice(): Boolean = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: return@withContext false
        val newStats = HeartsManager.earnHeartFromPractice(current)
        if (newStats.hearts == current.hearts && HeartsManager.getCurrentHearts(current) == newStats.hearts) {
            if (HeartsManager.getCurrentHearts(current) >= 5) return@withContext false
        }
        safeUpsertUserStats(newStats)
        true
    }

    suspend fun addGems(amount: Int, reason: String, metadata: String = "") = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: UserStatsEntity()
        val newBalance = current.gems + amount
        safeUpsertUserStats(current.copy(gems = newBalance))
        dao.insertGemsTransaction(
            GemsTransactionEntity(
                type = "EARN",
                amount = amount,
                reason = reason,
                timestamp = clock.now(),
                balanceAfter = newBalance,
                metadata = metadata
            )
        )
    }

    suspend fun spendGems(amount: Int, reason: String, metadata: String = ""): Boolean = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: return@withContext false
        if (current.gems < amount) return@withContext false
        val newBalance = current.gems - amount
        safeUpsertUserStats(current.copy(gems = newBalance))
        dao.insertGemsTransaction(
            GemsTransactionEntity(
                type = "SPEND",
                amount = -amount,
                reason = reason,
                timestamp = clock.now(),
                balanceAfter = newBalance,
                metadata = metadata
            )
        )
        true
    }

    suspend fun purchaseSuper(months: Int = 1) = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: UserStatsEntity()
        val expiresAt = clock.now() + (months * 30L * 24 * 60 * 60 * 1000)
        val newStats = current.copy(
            isSuper = true,
            superExpiresAt = expiresAt,
            hearts = 5,
            heartsUpdatedAt = clock.now()
        )
        safeUpsertUserStats(newStats)
        dao.insertGemsTransaction(
            GemsTransactionEntity(
                type = "PURCHASE",
                amount = 0,
                reason = "super_purchase",
                timestamp = clock.now(),
                balanceAfter = newStats.gems,
                metadata = "months:$months expires:$expiresAt"
            )
        )
    }

    suspend fun refreshLeagueCohortIfNeeded() = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: return@withContext
        val cohortId = current.leagueCohortId ?: return@withContext
        val cohort = dao.getLeagueCohortById(cohortId) ?: return@withContext

        if (clock.now() > cohort.weekEndTimestamp) {
            val members = dao.getLeagueMembersOnce(cohortId)
            val result = LeagueManager.checkPromotionDemotion(members, current.id)
            val nextTier = LeagueManager.getNextTier(current.leagueTier, result)

            val newCohort = LeagueManager.createNewCohort(nextTier)
            dao.upsertLeagueCohort(newCohort)
            val bots = LeagueManager.generateBotsForCohort(
                cohortId = newCohort.cohortId,
                currentUserName = "Dr. Youcef",
                currentUserXp = 0,
                currentUserStreak = current.streakDays
            )
            dao.upsertLeagueMembers(bots)

            var newGems = current.gems
            if (result == LeagueManager.LeagueResult.PROMOTION) {
                newGems += GemsManager.GEMS_LEAGUE_PROMOTION
                dao.insertGemsTransaction(
                    GemsTransactionEntity(
                        type = "EARN",
                        amount = GemsManager.GEMS_LEAGUE_PROMOTION,
                        reason = "league_promotion",
                        timestamp = clock.now(),
                        balanceAfter = newGems,
                        metadata = "from:${current.leagueTier} to:$nextTier"
                    )
                )
            }

            safeUpsertUserStats(
                current.copy(
                    leagueCohortId = newCohort.cohortId,
                    leagueTier = nextTier,
                    weeklyXp = 0,
                    gems = newGems
                )
            )
        }
    }

    suspend fun toggleModuleDownload(moduleInfo: InitialData.ModuleInfo, isDownload: Boolean) = withContext(Dispatchers.IO) {
        if (isDownload) {
            dao.upsertDownloadedModule(
                DownloadedModuleEntity(
                    moduleId = moduleInfo.id,
                    moduleName = moduleInfo.titleFr,
                    downloadedTimestamp = clock.now(),
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
        val current = dao.getUserStatsOnce() ?: UserStatsEntity()
        safeUpsertUserStats(current.copy(selectedLanguageCode = langCode))
    }

    suspend fun toggleOfflineSimulation(isOffline: Boolean) = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: UserStatsEntity()
        safeUpsertUserStats(current.copy(isOfflineSimulated = isOffline))
    }
}
