package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Meilleur score d'une leçon du parcours, clé `moduleId:level` (voir
 * `com.example.domain.path.PathBuilder.lessonKey`).
 * Sert à la fois au déblocage (seuil 70 %) et à la médaille de l'unité.
 */
@Entity(tableName = "lesson_scores")
data class LessonScoreEntity(
    @PrimaryKey
    val lessonKey: String,
    val moduleId: String,
    val level: Int,
    val best: Int = 0
)
