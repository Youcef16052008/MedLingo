package com.aistudio.medlingua.dzmed.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

/**
 * Type converter for List<Int> - stores as a comma-separated string in the database.
 */
class ListIntConverter {
    @TypeConverter
    fun fromList(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toList(json: String): List<Int> {
        if (json.isBlank()) return emptyList()
        return json.split(",").mapNotNull { it.trim().toIntOrNull() }
    }
}

/**
 * Article de la file d'attente des révisions ("Revisions en attente").
 *
 * Stocke une leçon review qui a été débloquée (status=unlocked) mais pas
 * encore jouée. L'utilisateur peut la lancer plus tard via le hub central.
 */
@Entity(tableName = "review_queue")
@TypeConverters(ListIntConverter::class)
data class ReviewQueueItem(
    @PrimaryKey val id: String,
    val lessonId: String, // ex: "anat-Angiologie:l14"
    val chapterId: String,
    val title: String,
    val questionCount: Int = 16,
    val status: Status = Status.UNLOCKED,
    /** Ids of exercises that were missed (incorrectly answered) in this review. */ // ✓ added
    val missedExIds: List<Int> = emptyList() // ✓ added
) {
    /** Débloqué mais pas encore joué (depuis le choix Continue/L3/L5). */
    enum class Status { UNLOCKED, PLAYED }
}
