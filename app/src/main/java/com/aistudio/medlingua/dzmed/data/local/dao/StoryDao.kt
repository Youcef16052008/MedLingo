package com.aistudio.medlingua.dzmed.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.aistudio.medlingua.dzmed.data.local.entity.PhraseProgressEntity
import com.aistudio.medlingua.dzmed.data.local.entity.StorySessionProgressEntity

@Dao
interface StoryDao {
    @Upsert
    suspend fun upsertStoryProgress(progress: StorySessionProgressEntity)

    @Query("SELECT * FROM story_session_progress WHERE storyId = :storyId")
    suspend fun storyProgress(storyId: String): StorySessionProgressEntity?

    @Query("SELECT * FROM story_session_progress")
    suspend fun storyProgressOfAll(): List<StorySessionProgressEntity>

    @Upsert
    suspend fun upsertPhrases(phrases: List<PhraseProgressEntity>)

    @Query("SELECT * FROM phrase_progress")
    suspend fun allPhrases(): List<PhraseProgressEntity>

    @Query("SELECT * FROM phrase_progress WHERE phraseId IN (:ids)")
    suspend fun phrasesByIds(ids: List<String>): List<PhraseProgressEntity>
}
