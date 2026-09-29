package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.DownloadedModuleEntity
import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.FlashcardProgressEntity
import com.example.data.local.entity.GemsTransactionEntity
import com.example.data.local.entity.LeagueCohortEntity
import com.example.data.local.entity.LeagueMemberEntity
import com.example.data.local.entity.MedicalTermEntity
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

    @Query("UPDATE league_members SET weeklyXp = weeklyXp + :xp WHERE cohortId = :cohortId AND isCurrentUser = 1")
    suspend fun addXpToCurrentUserInLeague(cohortId: String, xp: Int)

    @Query("DELETE FROM league_members WHERE cohortId = :cohortId")
    suspend fun clearLeagueMembers(cohortId: String)
}
