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
import com.example.domain.lesson.BulkExerciseGenerator
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
        // P0-3 (rapport final, bug B3) : tout le seed est maintenant exécuté
        // dans UNE transaction Room (`dao.seedAll`). Avant, ~30 écritures
        // indépendantes laissaient une base partiellement remplie si le process
        // était tué à mi-chemin — et la garde `isDatabaseEmpty` n'était plus
        // vraie au relancement, donc l'app démarrait sur une base cassée à vie.
        //
        // Toute la préparation (lecture des bookmarks, migration ExerciseSpec,
        // génération de la cohorte) reste en dehors de la transaction : c'est
        // du calcul pur, non écriture. Seul le résultat est écrit atomiquement.

        val stats = dao.getUserStatsOnce()
        if (stats == null) {

        // Preserve user bookmarks across the REPLACE seed (seed data may update content)
        val bookmarkedKeys = dao.getBookmarkedKeys()

        // Phase 2: Migrate exercises to ExerciseSpec JSONB
        val migratedExercises = try {
            com.example.domain.exercise.ExerciseSpecMigrator.migrateAll(InitialData.exercises)
        } catch (e: Exception) {
            InitialData.exercises
        }

        val now = clock.now()

        val modules = InitialData.modulesList.map { mod ->
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
        }

        // P0-2 : état neutre pour un nouvel utilisateur. L'ancien seed injectait
        // une persona de démo (12 jours de série, 156 termes, niveau 1 à 85 %,
        // 2 850 points) qui s'affichait avant le premier exercice — et dont
        // `level2Score = 75 >= 70` pouvait empêcher le niveau 3 de se
        // débloquer. Plus aucune progression fabriquée.
        val freshStats = UserStatsEntity.createFresh(clock).copy(
            // La série doit partir du jour courant pour que la première étude la
            // compte bien à 1 (et non à 0).
            lastStudy = ProgressionManager.todayKey(now),
            weeklyXpReset = LeagueManager.getWeekStartTimestamp(clock)
        )

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

        // Bonus de bienvenue : réel (il alimente le ledger append-only
        // anti-triche), donc crédité explicitement dans la transaction.
        val welcomeBonus = 100
        val gemsTransaction = GemsTransactionEntity(
            type = "EARN",
            amount = welcomeBonus,
            reason = "welcome_bonus",
            timestamp = now,
            balanceAfter = welcomeBonus,
            metadata = "Initial gems"
        )
        val statsWithGems = freshStats.copy(gems = welcomeBonus)

        // Init league cohort — only one cohort may be active at a time
        val cohort = LeagueManager.createNewCohort("BRONZE", clock)
        val bots = LeagueManager.generateBotsForCohort(
            cohortId = cohort.cohortId,
            currentUserName = "Dr. Youcef",
            currentUserXp = 0,
            // P0-2 : la série du joueur part de 0, comme son état.
            currentUserStreak = 0,
            clock = clock
        )
        val statsWithCohort = statsWithGems.copy(leagueCohortId = cohort.cohortId)

        // Écriture atomique : Room annule tout en cas d'échec.
        dao.seedAll(
            terms = InitialData.terms,
            bookmarkedKeys = bookmarkedKeys,
            exercises = migratedExercises,
            modules = modules,
            stats = freshStats,
            progress = initialProgress,
            gemsTransaction = gemsTransaction,
            statsWithGems = statsWithGems,
            cohort = cohort,
            deactivateOtherCohortsFor = cohort.cohortId,
            leagueMembers = bots,
            statsWithCohort = statsWithCohort
        )

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

    /**
     * Génère et persiste les exercices en masse (3 par terme × 4 504 termes
     * = 13 512), en plus des exercices écrits main.
     *
     * Pourquoi c'est ici et pas dans [initializeDatabaseIfEmpty] : la génération
     * est un calcul pur (~2 s sur un téléphone milieu de gamme) qui n'a pas
     * besoin d'être atomique avec le seed. La garde `getBulkExercisesCount()`
     * évite de recommencer à chaque lancement — le coût est payé une seule fois,
     * au premier démarrage après la mise à jour.
     *
     * Les exercices générés ont des ids négatifs : ils ne collisionnent jamais
     * avec le seed main, et `deleteBulkExercises()` permet de les régénérer
     * sans toucher aux exercices écrits main.
     *
     * En cas d'échec (espace, base verrouillée), on ne plante pas le démarrage :
     * l'utilisateur garde l'app fonctionnelle avec les exercices écrits main et
     * la génération en ligne de `LessonGenerator.lessonQuestions`.
     */
    suspend fun seedBulkExercisesIfMissing() = withContext(Dispatchers.IO) {
        val expected = InitialData.terms.size * BulkExerciseGenerator.EXERCISES_PER_TERM
        if (dao.getBulkExercisesCount() >= expected) return@withContext

        val modules = InitialData.modulesList.map { mod ->
            mod.titleFr to InitialData.termsOfModule(mod.titleFr)
        }
        val bulk = try {
            BulkExerciseGenerator.generateAll(modules)
        } catch (e: Exception) {
            android.util.Log.w(
                "MedLinguaRepo",
                "Bulk exercise generation failed: ${e.message}. " +
                    "Falling back to inline lesson generation."
            )
            return@withContext
        }
        if (bulk.isEmpty()) return@withContext

        try {
            dao.deleteBulkExercises()
            dao.insertExercises(bulk)
        } catch (e: Exception) {
            android.util.Log.w(
                "MedLinguaRepo",
                "Bulk exercise persistence failed: ${e.message}"
            )
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

        val currentStats = dao.getUserStatsOnce() ?: UserStatsEntity.createFresh()
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
        val current = dao.getUserStatsOnce() ?: UserStatsEntity.createFresh()
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
        val current = dao.getUserStatsOnce() ?: UserStatsEntity.createFresh()
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
        val current = dao.getUserStatsOnce() ?: UserStatsEntity.createFresh()
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

    /**
     * Achète l'abonnement Super MedLingo avec des gemmes — la monnaie réelle
     * de l'app (offline-first, pas de paiement Google/BaridiMob).
     *
     * Avant, cette fonction mettait `isSuper = true` **gratuitement** et
     * enregistrait une transaction à `amount = 0` : une fausse porte. Le
     * paywall affichait pourtant "500 DA/mois". Maintenant le prix est
     * cohérent : 500 gemmes/mois, 5000 gemmes/an.
     *
     * @return `false` si le solde de gemmes est insuffisant (rien n'est écrit)
     */
    suspend fun purchaseSuper(months: Int = 1): Boolean = withContext(Dispatchers.IO) {
        val pricing = GemsManager.getSuperPricing()
        val cost = if (months >= 12) pricing.yearlyGems else pricing.monthlyGems * months
        val current = dao.getUserStatsOnce() ?: return@withContext false
        if (current.gems < cost) return@withContext false
        val newBalance = current.gems - cost
        val expiresAt = clock.now() + (months * 30L * 24 * 60 * 60 * 1000)
        safeUpsertUserStats(
            current.copy(gems = newBalance, isSuper = true, superExpiresAt = expiresAt)
        )
        dao.insertGemsTransaction(
            GemsTransactionEntity(
                type = "PURCHASE",
                amount = -cost,
                reason = "super_purchase",
                timestamp = clock.now(),
                balanceAfter = newBalance,
                metadata = "months:$months expires:$expiresAt"
            )
        )
        true
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

    /**
     * PHASE 3 #8 (rapport final) : persiste l'animation des bots calculée à
     * l'affichage. Une seule écriture transactionnelle (`@Transaction` ne
     * fonctionne que dans un DAO Room, d'où la méthode dédiée) ; les vrais
     * utilisateurs ne sont jamais touchés — un bug sur ce chemin effacerait la
     * progression réelle d'un joueur, donc on filtre deux fois.
     */
    suspend fun persistBotAnimation(members: List<LeagueMemberEntity>) = withContext(Dispatchers.IO) {
        dao.upsertBots(members.filter { it.isBot })
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
        val current = dao.getUserStatsOnce() ?: UserStatsEntity.createFresh()
        safeUpsertUserStats(current.copy(selectedLanguageCode = langCode))
    }

    suspend fun toggleOfflineSimulation(isOffline: Boolean) = withContext(Dispatchers.IO) {
        val current = dao.getUserStatsOnce() ?: UserStatsEntity.createFresh()
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
