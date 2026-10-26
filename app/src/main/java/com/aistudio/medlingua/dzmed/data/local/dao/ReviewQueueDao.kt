package com.aistudio.medlingua.dzmed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aistudio.medlingua.dzmed.data.local.entity.ReviewQueueItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewQueueDao {

    @Query("SELECT * FROM review_queue WHERE status = 'UNLOCKED' ORDER BY id")
    fun getUnlockedReviews(): Flow<List<ReviewQueueItem>>

    @Query("SELECT * FROM review_queue WHERE status = 'UNLOCKED' AND chapterId = :chapterId")
    fun getUnlockedReviewsForChapter(chapterId: String): Flow<List<ReviewQueueItem>>

    @Query("SELECT * FROM review_queue ORDER BY id")
    fun getAllReviews(): Flow<List<ReviewQueueItem>>

    @Query("SELECT * FROM review_queue WHERE status = 'UNLOCKED'")
    suspend fun getUnlockedReviewsSync(): List<ReviewQueueItem>

    @Query("SELECT * FROM review_queue WHERE id = :id")
    suspend fun getReviewById(id: String): ReviewQueueItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(review: ReviewQueueItem)

    @Query("INSERT OR REPLACE INTO review_queue (id, lessonId, chapterId, title, questionCount, status) VALUES (:id, :lessonId, :chapterId, :title, :questionCount, :status)")
    suspend fun upsertReview(
        id: String,
        lessonId: String,
        chapterId: String,
        title: String,
        questionCount: Int,
        status: ReviewQueueItem.Status
    )

    @Query("UPDATE review_queue SET missedExIds = :missedExIds WHERE id = :id")
    suspend fun updateReviewMissedExIds(id: String, missedExIds: List<Int> = emptyList())

    @Query("UPDATE review_queue SET status = :status WHERE id = :id")
    suspend fun setReviewStatus(id: String, status: ReviewQueueItem.Status)

    @Query("DELETE FROM review_queue WHERE id = :id")
    suspend fun deleteReview(id: String)

    @Query("DELETE FROM review_queue")
    suspend fun clearAll()
}
