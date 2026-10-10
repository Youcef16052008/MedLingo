package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
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
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicalDao {
    @Query("SELECT * FROM medical_terms ORDER BY module, id")
    fun getAllTerms(): Flow<List<MedicalTermEntity>>

    @Query("SELECT * FROM medical_terms WHERE module = :module ORDER BY id")
    fun getTermsByModule(module: String): Flow<List<MedicalTermEntity>>

    @Query("SELECT * FROM medical_terms WHERE id = :id")
    suspend fun getTermById(id: Int): MedicalTermEntity?

    @Query("SELECT * FROM medical_terms WHERE termEn LIKE '%' || :query || '%' OR termFr LIKE '%' || :query || '%' OR termAr LIKE '%' || :query || '%'")
    fun searchTerms(query: String): Flow<List<MedicalTermEntity>>

    @Query("SELECT DISTINCT module FROM medical_terms")
    fun getAllModules(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTerms(terms: List<MedicalTermEntity>)

    @Query("SELECT termEn, module FROM medical_terms WHERE isBookmarked = 1")
    suspend fun getBookmarkedKeys(): List<BookmarkedKey>

    @Query("UPDATE medical_terms SET isBookmarked = 1 WHERE termEn = :termEn AND module = :module")
    suspend fun restoreBookmark(termEn: String, module: String)

    @Update
    suspend fun updateTerm(term: MedicalTermEntity)

    // Flashcard Progress
    @Query("SELECT * FROM flashcard_progress")
    fun getAllFlashcardProgress(): Flow<List<FlashcardProgressEntity>>

    @Query("SELECT * FROM flashcard_progress WHERE termId = :termId")
    suspend fun getFlashcardProgress(termId: Int): FlashcardProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertFlashcardProgress(progress: FlashcardProgressEntity)

    // Exercises
    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE level = :level ORDER BY id")
    fun getExercisesByLevel(level: Int): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE module = :module")
    fun getExercisesByModule(module: String): Flow<List<ExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<ExerciseEntity>)

    // Downloaded Modules (Offline)
    @Query("SELECT * FROM downloaded_modules")
    fun getAllDownloadedModules(): Flow<List<DownloadedModuleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDownloadedModule(module: DownloadedModuleEntity)

    @Query("DELETE FROM downloaded_modules WHERE moduleId = :moduleId")
    suspend fun deleteDownloadedModule(moduleId: String)

    // User Stats
    @Query("SELECT * FROM user_stats WHERE id = 1")
    fun getUserStats(): Flow<UserStatsEntity?>

    @Query("SELECT * FROM user_stats WHERE id = 1")
    suspend fun getUserStatsOnce(): UserStatsEntity?

    /**
     * Variante **synchrone** pour les contextes non-coroutine : démarrage de
     * l'app (`MedLinguaApp.onCreate`, avant la première lecture) et
     * `BroadcastReceiver` (Phase 3 #11, langue des notifications).
     * On ne lit que la langue — un SELECT simple, ~1 ms, pas de jointure.
     */
    @Query("SELECT selectedLanguageCode FROM user_stats WHERE id = 1")
    fun getLanguageCodeBlocking(): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUserStats(stats: UserStatsEntity)

    @Query("SELECT COUNT(*) FROM medical_terms")
    suspend fun getTermsCount(): Int

    // === DUOLINGO PHASE 1: Gems Ledger ===
    @Query("SELECT * FROM gems_transactions ORDER BY timestamp DESC")
    fun getAllGemsTransactions(): Flow<List<GemsTransactionEntity>>

    @Query("SELECT * FROM gems_transactions ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getRecentGemsTransactions(limit: Int = 20): List<GemsTransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGemsTransaction(transaction: GemsTransactionEntity)

    // === DUOLINGO PHASE 1: League Cohorts ===
    @Query("SELECT * FROM league_cohorts WHERE isActive = 1 ORDER BY weekStartTimestamp DESC LIMIT 1")
    fun getActiveLeagueCohort(): Flow<LeagueCohortEntity?>

    @Query("SELECT * FROM league_cohorts WHERE isActive = 1 ORDER BY weekStartTimestamp DESC LIMIT 1")
    suspend fun getActiveLeagueCohortOnce(): LeagueCohortEntity?

    @Query("UPDATE league_cohorts SET isActive = 0 WHERE cohortId != :keepCohortId")
    suspend fun deactivateOtherCohorts(keepCohortId: String)

    @Query("SELECT * FROM league_cohorts WHERE cohortId = :cohortId")
    suspend fun getLeagueCohortById(cohortId: String): LeagueCohortEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLeagueCohort(cohort: LeagueCohortEntity)

    @Query("SELECT * FROM league_cohorts ORDER BY weekStartTimestamp DESC")
    fun getAllLeagueCohorts(): Flow<List<LeagueCohortEntity>>

    // === DUOLINGO PHASE 1: League Members ===
    @Query("SELECT * FROM league_members WHERE cohortId = :cohortId ORDER BY weeklyXp DESC")
    fun getLeagueMembers(cohortId: String): Flow<List<LeagueMemberEntity>>

    @Query("SELECT * FROM league_members WHERE cohortId = :cohortId ORDER BY weeklyXp DESC")
    suspend fun getLeagueMembersOnce(cohortId: String): List<LeagueMemberEntity>

    @Query("SELECT * FROM league_members WHERE cohortId = :cohortId AND isCurrentUser = 1 LIMIT 1")
    suspend fun getCurrentUserLeagueMember(cohortId: String): LeagueMemberEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLeagueMember(member: LeagueMemberEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLeagueMembers(members: List<LeagueMemberEntity>)

    /**
     * PHASE 3 #8 : écrit transactionnelle des bots animés. On ne remplace que
     * les bots — un `upsertLeagueMembers` sur toute la liste écrirait aussi
     * l'utilisateur courant, et bien que son XP vienne d'être créditée par
     * ailleurs, recalculer son `weeklyXp` ici serait une double écriture.
     */
    @Transaction
    suspend fun upsertBots(bots: List<LeagueMemberEntity>) {
        bots.forEach { upsertLeagueMember(it) }
    }

    @Query("UPDATE league_members SET weeklyXp = weeklyXp + :xp WHERE cohortId = :cohortId AND isCurrentUser = 1")
    suspend fun addXpToCurrentUserInLeague(cohortId: String, xp: Int)

    @Query("DELETE FROM league_members WHERE cohortId = :cohortId")
    suspend fun clearLeagueMembers(cohortId: String)

    // === DUOLINGO PHASE 2: Trophées ===
    @Query("SELECT * FROM trophies")
    fun getAllTrophies(): Flow<List<TrophyEntity>>

    @Query("SELECT * FROM trophies")
    suspend fun getAllTrophiesOnce(): List<TrophyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTrophies(trophies: List<TrophyEntity>)

    // === DUOLINGO PHASE 2: scores du parcours ===
    @Query("SELECT * FROM lesson_scores")
    fun getAllLessonScores(): Flow<List<LessonScoreEntity>>

    @Query("SELECT * FROM lesson_scores")
    suspend fun getAllLessonScoresOnce(): List<LessonScoreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLessonScore(score: LessonScoreEntity)

    // === P0-3 (rapport final, bug B3) : seed transactionnel ===
    //
    // Avant, `initializeDatabaseIfEmpty` enchaînait ~30 écritures DAO sans
    // transaction. Un process killed à mi-chemin (low-memory, crash) laissait
    // une base partiellement remplie ; or la garde `isDatabaseEmpty` n'était
    // plus vraie au relancement → l'app démarrait sur une base incomplète
    // **pour toujours** (des modules marqués téléchargés, mais sans termes ni
    // exercices).
    //
    // Room annule toute la transaction si une seule écriture échoue.
    @Transaction
    suspend fun seedAll(
        terms: List<MedicalTermEntity>,
        bookmarkedKeys: List<BookmarkedKey>,
        exercises: List<ExerciseEntity>,
        modules: List<DownloadedModuleEntity>,
        stats: UserStatsEntity,
        progress: List<FlashcardProgressEntity>,
        gemsTransaction: GemsTransactionEntity?,
        statsWithGems: UserStatsEntity?,
        cohort: LeagueCohortEntity?,
        deactivateOtherCohortsFor: String?,
        leagueMembers: List<LeagueMemberEntity>,
        statsWithCohort: UserStatsEntity?
    ) {
        // 1. Termes (REPLACE : le seed peut mettre à jour le contenu)
        insertTerms(terms)
        // 2. Restaurer les bookmarks de l'utilisateur (le REPLACE les efface)
        bookmarkedKeys.forEach { key -> restoreBookmark(key.termEn, key.module) }
        // 3. Exercices (déjà migrés en ExerciseSpec JSONB par le repository)
        insertExercises(exercises)
        // 4. Modules marqués téléchargés
        modules.forEach { upsertDownloadedModule(it) }
        // 5. Stats initiales (état neutre — P0-2)
        upsertUserStats(stats)
        // 6. Progression flashcard initiale
        progress.forEach { upsertFlashcardProgress(it) }
        // 7. Bonus de bienvenue (ledger append-only anti-triche) + solde
        if (gemsTransaction != null && statsWithGems != null) {
            insertGemsTransaction(gemsTransaction)
            upsertUserStats(statsWithGems)
        }
        // 8. Cohorte de ligue + bots
        if (cohort != null) {
            if (deactivateOtherCohortsFor != null) {
                deactivateOtherCohorts(deactivateOtherCohortsFor)
            }
            upsertLeagueCohort(cohort)
            upsertLeagueMembers(leagueMembers)
            if (statsWithCohort != null) upsertUserStats(statsWithCohort)
        }
    }
}

data class BookmarkedKey(val termEn: String, val module: String)
