package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Trophée gagné (12 réalisations de l'écran « Moi »).
 * `earnedAt` en millisecondes epoch, pour l'affichage « gagné le … ».
 */
@Entity(tableName = "trophies")
data class TrophyEntity(
    @PrimaryKey
    val id: String,
    val earnedAt: Long = 0L
)
