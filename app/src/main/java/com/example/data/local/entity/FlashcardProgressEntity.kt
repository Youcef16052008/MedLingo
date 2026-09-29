package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcard_progress")
data class FlashcardProgressEntity(
    @PrimaryKey
    val termId: Int,
    val repetitions: Int = 0,
    val easeFactor: Double = 2.5,
    val intervalDays: Int = 0,
    val nextReviewTimestamp: Long = 0L,
    val lastReviewedTimestamp: Long = 0L,
    val lastQuality: Int = 0
)
