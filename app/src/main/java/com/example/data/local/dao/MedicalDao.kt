package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.DownloadedModuleEntity
import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.FlashcardProgressEntity
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
}
