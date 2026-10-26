package com.aistudio.medlingua.dzmed.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Progression d'une séance « histoire » — miroir de `StoryProgress` (store web).
 * Tables NEUVES (v14) : aucune donnée historique n'est touchée ; les flashcards
 * de termes restent dans `flashcard_progress`, indexées par `termId` numérique —
 * les ids d'expressions (`en:have-headache:v1`) vivent dans `phrase_progress`.
 */
@Entity(tableName = "story_session_progress")
data class StorySessionProgressEntity(
    @PrimaryKey
    val storyId: String,
    val contentVersion: Int,
    val step: String,
    val qIndex: Int = 0,
    val drillIndex: Int = 0,
    val reviewIndex: Int = 0,
    /** SessionStats sérialisée (kotlinx.serialization) — listes d'ids par verdict. */
    val statsJson: String,
    /** Map phraseId → retenu/oublié (bloc REVIEW, RISK-001). */
    val reviewRatingsJson: String,
    val retellAttempted: Boolean = false,
    val transferTaskAttempted: Boolean = false,
    val transferTaskPending: Boolean = false,
    val savedAt: Long = 0L,
    /** 0 = séance non terminée. */
    val completedAt: Long = 0L,
    /** Récompense attribuée pour cette exécution (anti double XP). */
    val rewardClaimed: Boolean = false
)

/**
 * Mémoire d'expressions (SM-2) — mêmes champs que `flashcard_progress` mais
 * dans un espace de clés SÉPARÉ (guide §9.3 : jamais d'emprunt de `termId`).
 */
@Entity(tableName = "phrase_progress")
data class PhraseProgressEntity(
    @PrimaryKey
    val phraseId: String,
    val repetitions: Int = 0,
    val easeFactor: Double = 2.5,
    val intervalDays: Int = 0,
    val nextReviewTimestamp: Long = 0L,
    val lastReviewedTimestamp: Long = 0L
)
