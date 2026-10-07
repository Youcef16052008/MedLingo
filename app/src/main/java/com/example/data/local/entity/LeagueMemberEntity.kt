package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * League Member - membre d'une cohorte league
 * Comme Duolingo: sorted set par weekly_xp DESC
 * Local MVP: user + 29 bots avec XP simulé pour compétition
 */
@Entity(
    tableName = "league_members",
    indices = [
        Index("cohortId"),
        Index("cohortId", "weeklyXp")
    ]
)
data class LeagueMemberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cohortId: String,
    val userId: Int = 1,
    val displayName: String, // ex: "Dr. Youcef" ou bot "Amine_Med"
    val avatarEmoji: String = "👨‍⚕️",
    val weeklyXp: Int = 0,
    val totalXp: Int = 0,
    val streakDays: Int = 0,
    val rank: Int = 0, // calculé à la volée via ORDER BY weeklyXp DESC
    val isCurrentUser: Boolean = false,
    val isBot: Boolean = false,
    // no System.currentTimeMillis() default: LeagueManager stamps it from the injected
    // Clock, so "active this week" checks stay testable
    val lastActiveTimestamp: Long
)
