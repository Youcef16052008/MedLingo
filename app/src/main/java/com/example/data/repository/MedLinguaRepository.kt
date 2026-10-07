package com.example.data.repository

import com.example.data.initial.InitialData
import com.example.data.local.dao.MedicalDao
import com.example.data.local.entity.DownloadedModuleEntity
import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.FlashcardProgressEntity
import com.example.data.local.entity.GemsTransactionEntity
import com.example.data.local.entity.LeagueCohortEntity
import com.example.data.local.entity.LeagueMemberEntity
import com.example.data.local.entity.LessonScoreEntity
import com.example.data.local.entity.MedicalTermEntity
import com.example.data.local.entity.TrophyEntity
import com.example.data.local.entity.UserStatsEntity
import com.example.domain.gamification.ChestKind
import com.example.domain.gamification.ChestManager
import com.example.domain.gamification.ChestReward
import com.example.domain.gamification.ChestSource
import com.example.domain.gamification.FREEZE_COST_GEMS
import com.example.domain.gamification.DAILY_GOAL_OPTIONS
import com.example.domain.gamification.GemsManager
import com.example.domain.gamification.LeagueManager
import com.example.domain.gamification.ProgressionManager
import com.example.domain.gamification.TrophyId
import com.example.domain.gamification.TrophyManager
import com.example.domain.path.PathBuilder
import com.example.domain.sm2.ReviewResult
import com.example.domain.sm2.SpacedRepetitionAlgorithm
import com.example.domain.time.Clock
import com.example.domain.time.SystemClock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

class MedLinguaRepository(private val dao: MedicalDao, private val clock: Clock = SystemClock) {

    /**
 * The time source of the data layer, exposed so the UI layer schedules and reads
 * "due" cards on the SAME clock. UI code used to read System.currentTimeMillis() while
 * every timestamp here came from this injected clock, so the two disagreed.
 */
val currentClock: Clock get() = clock

    val allTerms: Flow<List<MedicalTermEntity>> = dao.getAllTerms()
    val allExercises: Flow<List<ExerciseEntity>> = dao.getAllExercises()
    val allFlashcardProgress: Flow<List<FlashcardProgressEntity>> = dao.getAllFlashcardProgress()
    val allDownloadedModules: Flow<List<DownloadedModuleEntity>> = dao.getAllDownloadedModules()
    val userStats: Flow<UserStatsEntity?> = dao.getUserStats()
    val gemsTransactions: Flow<List<GemsTransactionEntity>> = dao.getAllGemsTransactions()
    val trophies: Flow<List<TrophyEntity>> = dao.getAllTrophies()
    val lessonScores: Flow<List<LessonScoreEntity>> = dao.getAllLessonScores()
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

    suspend fun isDatabaseEmpty(): Boolean = withContext(Dispatchers.IO) {
        dao.getTermsCount() == 0
    }

    suspend fun initializeDatabaseIfEmpty() = withContext(Dispatchers.IO) {
        // Preserve user bookmarks across the REPLACE seed (seed data may update content)
        val bookmarkedKeys = dao.getBookmarkedKeys()
        dao.insertTerms(InitialData.terms)
        bookmarkedKeys.forEach { key -> dao.restoreBookmark(key.termEn, key.module) }

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
                    learnedTermsCount = 156, // demo persona progress, NOT "all 1641 terms learned"
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
                    gems = 100,
                    weeklyXp = 0,
                    leagueTier = "BRONZE",
                    isSuper = false,
                    streakFreezeCount = 0,
                    perfectLessonsCount = 0,
                    lessonsCompleted = 18,
                    // La série démo (12 jours) part du jour courant : sans `lastStudy`,
                    // la première étude la remettrait à 1.
                    lastStudy = ProgressionManager.todayKey(now),
                    weeklyXpReset = LeagueManager.getWeekStartTimestamp(clock)
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

            // Init league cohort — only one cohort may be active at a time
            val cohort = LeagueManager.createNewCohort("BRONZE", clock)
            dao.deactivateOtherCohorts(cohort.cohortId)
            dao.upsertLeagueCohort(cohort)
            val bots = LeagueManager.generateBotsForCohort(
                cohortId = cohort.cohortId,
                currentUserName = "Dr. Youcef",
                currentUserXp = 0,
                currentUserStreak = 12,
                clock = clock
            )
            dao.upsertLeagueMembers(bots)

            // Update user with cohort id
            val updatedStats = dao.getUserStatsOnce()?.copy(leagueCohortId = cohort.cohortId)
            if (updatedStats != null) safeUpsertUserStats(updatedStats)

        } else {
            // Migration check: ensure new fields have defaults if old DB
            var needsUpdate = false
            var newStats = stats
            // learnedTermsCount is only ever modified by real learning progress -—
            // the old code force-overwrote it to terms.size on every launch
            if (stats.leagueCohortId == null) {
                // Reattach to an existing active cohort when possible; only create
                // a new one when there is none (the old code queried by empty id)
                val activeCohort = dao.getActiveLeagueCohortOnce()
                if (activeCohort == null) {
                    val cohort = LeagueManager.createNewCohort(stats.leagueTier, clock)
                    dao.deactivateOtherCohorts(cohort.cohortId)
                    dao.upsertLeagueCohort(cohort)
                    val bots = LeagueManager.generateBotsForCohort(
                        cohortId = cohort.cohortId,
                        currentUserName = "Dr. Youcef",
                        currentUserXp = stats.weeklyXp,
                        currentUserStreak = stats.streakDays,
                        clock = clock
                    )
                    dao.upsertLeagueMembers(bots)
                    newStats = newStats.copy(leagueCohortId = cohort.cohortId)
                    needsUpdate = true
                } else {
                    newStats = newStats.copy(leagueCohortId = activeCohort.cohortId)
                    needsUpdate = true
                }
            }
            if (needsUpdate) safeUpsertUserStats(newStats)
        }

        // Reseed quand le seed Kotlin a grandi (nouvelles bases intégrées) :
        // insertTerms est un REPLACE idempotent, les bookmarks sont préservés
        // et les compteurs par module sont rafraîchis. Sans cela, les installs
        // existantes ne recevraient jamais les nouveaux termes.
        if (dao.getTermsCount() < InitialData.terms.size) {
            val reseededKeys = dao.getBookmarkedKeys()
            dao.insertTerms(InitialData.terms)
            reseededKeys.forEach { key -> dao.restoreBookmark(key.termEn, key.module) }
            val reseedNow = clock.now()
            InitialData.modulesList.forEach { mod ->
                dao.upsertDownloadedModule(
                    DownloadedModuleEntity(
                        moduleId = mod.id,
                        moduleName = mod.titleFr,
                        downloadedTimestamp = reseedNow,
                        sizeMb = mod.estimatedSizeMb,
                        termsCount = InitialData.termsOfModule(mod.titleFr).size,
                        exercisesCount = InitialData.exercises.count {
                            it.module.equals(mod.titleFr, ignoreCase = true)
                        },
                        isDownloaded = true
                    )
                )
            }
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
            previousIntervalDays = currentInterval,
            clock = clock
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
        // Plus de perte de cœur : une révision crédite de l'XP (objectif quotidien,
        // ligue, série) et alimente le compteur du trophée 🗂️.
        val gained = if (quality < 3) quality * 2 else quality * 5
        val newStats = ProgressionManager
            .addXp(currentStats, gained, clock.now())
            .stats
            .copy(flashReviewed = currentStats.flashReviewed + 1)

        if (newStats.leagueCohortId != null) {
            dao.addXpToCurrentUserInLeague(newStats.leagueCohortId, gained)
        }

        safeUpsertUserStats(newStats)
        result
    }

    suspend fun recordQuizCompletion(pointsEarned: Int, totalQuestions: Int, correctAnswers: Int) = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: UserStatsEntity()
        val currentStats = ProgressionManager.addXp(current, pointsEarned, clock.now())
            .stats
            .copy(quizzesCompleted = current.quizzesCompleted + 1,
                totalQuestionsAnswered = current.totalQuestionsAnswered + totalQuestions,
                correctAnswersCount = current.correctAnswersCount + correctAnswers
            )
        safeUpsertUserStats(currentStats)

        if (currentStats.leagueCohortId != null) {
            dao.addXpToCurrentUserInLeague(currentStats.leagueCohortId, pointsEarned)
        }

        val isPerfect = correctAnswers == totalQuestions && totalQuestions > 0
        val gemsEarned = GemsManager.calculateGemsForLesson(correctAnswers, totalQuestions)
        if (gemsEarned > 0) {
            val newBalance = currentStats.gems + gemsEarned
            dao.insertGemsTransaction(
                GemsManager.createEarnTransaction(
                    amount = gemsEarned,
                    reason = if (isPerfect) "perfect_lesson" else "lesson_complete",
                    currentBalance = currentStats.gems,
                    metadata = "points:$pointsEarned correct:$correctAnswers/$totalQuestions",
                    timestamp = clock.now()
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

        // isPerfect is now derived inside calculateGemsForLesson; here we only need
        // the flag for the ledger reason label, so keep it aligned with that rule.
        val isPerfect = correctAnswers == totalQuestions && totalQuestions > 0
        val gemsEarned = GemsManager.calculateGemsForLesson(correctAnswers, totalQuestions)

        val xpStats = ProgressionManager.addXp(current, pointsEarned, clock.now()).stats
        var updated = when (level) {
            1 -> xpStats.copy(level1Score = newScore)
            2 -> xpStats.copy(level2Score = newScore)
            3 -> xpStats.copy(level3Score = newScore)
            4 -> xpStats.copy(level4Score = newScore)
            5 -> xpStats.copy(level5Score = newScore)
            6 -> xpStats.copy(level6Score = newScore)
            else -> xpStats
        }.copy(
            quizzesCompleted = xpStats.quizzesCompleted + 1,
            totalQuestionsAnswered = xpStats.totalQuestionsAnswered + totalQuestions,
            correctAnswersCount = xpStats.correctAnswersCount + correctAnswers,
            lessonsCompleted = xpStats.lessonsCompleted + 1,
            perfectLessonsCount = if (isPerfect) xpStats.perfectLessonsCount + 1 else xpStats.perfectLessonsCount,
            gems = xpStats.gems + gemsEarned
        )
        safeUpsertUserStats(updated)

        if (updated.leagueCohortId != null) {
            dao.addXpToCurrentUserInLeague(updated.leagueCohortId, pointsEarned)
        }

        if (gemsEarned > 0) {
            dao.insertGemsTransaction(
                GemsManager.createEarnTransaction(
                    amount = gemsEarned,
                    reason = if (isPerfect) "perfect_lesson" else "lesson_complete",
                    currentBalance = current.gems,
                    metadata = "level:$level score:$scorePercentage",
                    timestamp = clock.now()
                )
            )
        }

        scorePercentage >= 70 && prevScore < 70
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
            superExpiresAt = expiresAt
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

            val newCohort = LeagueManager.createNewCohort(nextTier, clock)
            dao.deactivateOtherCohorts(newCohort.cohortId)
            dao.upsertLeagueCohort(newCohort)
            val bots = LeagueManager.generateBotsForCohort(
                cohortId = newCohort.cohortId,
                currentUserName = "Dr. Youcef",
                currentUserXp = 0,
                currentUserStreak = current.streakDays,
                clock = clock
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
                    goalDays = 0,
                    weeklyXpReset = newCohort.weekStartTimestamp,
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
                    termsCount = InitialData.termsOfModule(moduleInfo.titleFr).size,
                    exercisesCount = InitialData.exercises.count {
                        it.module.equals(moduleInfo.titleFr, ignoreCase = true)
                    },
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

    // ==========================================================
    // DUOLINGO PHASE 2: trophées, caisses, congélation, parcours
    // ==========================================================

    /** Enregistre les trophées nouvellement gagnés et ne retourne que ceux-ci. */
    suspend fun claimNewTrophies(): List<TrophyId> = withContext(Dispatchers.IO) {
        val stats = dao.getUserStatsOnce() ?: return@withContext emptyList()
        val now = clock.now()
        val earned = dao.getAllTrophiesOnce().mapTo(HashSet()) { it.id }
        val fresh = TrophyManager.claimTrophies(stats, earned, now)
        if (fresh.isNotEmpty()) {
            dao.upsertTrophies(fresh.map { (id, at) -> TrophyEntity(id.name, at) })
        }
        fresh.map { it.first }
    }

    /** Ajoute une caisse en attente si la session en mérite une. */
    suspend fun earnChest(source: ChestSource, score: Int): Boolean = withContext(Dispatchers.IO) {
        if (!ChestManager.earnsChest(source, score)) return@withContext false
        val stats = dao.getUserStatsOnce() ?: return@withContext false
        safeUpsertUserStats(stats.copy(pendingChests = stats.pendingChests + 1))
        true
    }

    /**
     * Ouvre une caisse en attente, applique la récompense tirée et retourne ce qui a
     * été gagné (`null` s'il n'y a rien à ouvrir).
     */
    suspend fun openChest(rand: Random = Random.Default): ChestReward? = withContext(Dispatchers.IO) {
        val stats = dao.getUserStatsOnce() ?: return@withContext null
        if (stats.pendingChests <= 0) return@withContext null

        val reward = ChestManager.openChest { rand.nextDouble() }
        val updated = ChestManager.applyChestReward(stats, reward, clock.now())
            .copy(
                pendingChests = stats.pendingChests - 1,
                chestsOpened = stats.chestsOpened + 1
            )
        safeUpsertUserStats(updated)

        // L'XP de caisse compte aussi dans la ligue (comme les autres sources).
        if (reward.kind == ChestKind.XP && reward.amount > 0 && updated.leagueCohortId != null) {
            dao.addXpToCurrentUserInLeague(updated.leagueCohortId, reward.amount)
        }

        if (reward.kind == ChestKind.GEMS && reward.amount > 0) {
            dao.insertGemsTransaction(
                GemsManager.createEarnTransaction(
                    amount = reward.amount,
                    reason = "chest",
                    currentBalance = stats.gems,
                    timestamp = clock.now()
                )
            )
        }
        reward
    }

    /** Achète une congélation (200 💎). `false` si les gemmes manquent. */
    suspend fun buyFreeze(): Boolean = withContext(Dispatchers.IO) {
        val stats = dao.getUserStatsOnce() ?: return@withContext false
        val updated = ProgressionManager.buyFreeze(stats) ?: return@withContext false
        safeUpsertUserStats(updated)
        dao.insertGemsTransaction(
            GemsManager.createSpendTransaction(
                amount = FREEZE_COST_GEMS,
                reason = "streak_freeze",
                currentBalance = stats.gems,
                timestamp = clock.now()
            ) ?: return@withContext false
        )
        true
    }

    /** Change l'objectif quotidien (20 / 50 / 100 XP). */
    suspend fun setDailyGoal(goal: Int): Boolean = withContext(Dispatchers.IO) {
        if (goal !in DAILY_GOAL_OPTIONS) return@withContext false
        val stats = dao.getUserStatsOnce() ?: return@withContext false
        if (stats.dailyGoal == goal) return@withContext true
        safeUpsertUserStats(stats.copy(dailyGoal = goal))
        true
    }

    /** Conserve le meilleur score d'une leçon (`module:level`) du parcours. */
    suspend fun saveLessonScore(moduleId: String, level: Int, score: Int) = withContext(Dispatchers.IO) {
        val key = PathBuilder.lessonKey(moduleId, level)
        val existing = dao.getAllLessonScoresOnce().firstOrNull { it.lessonKey == key }
        if (existing != null && existing.best >= score) return@withContext
        dao.upsertLessonScore(
            LessonScoreEntity(lessonKey = key, moduleId = moduleId, level = level, best = score)
        )
    }
}
