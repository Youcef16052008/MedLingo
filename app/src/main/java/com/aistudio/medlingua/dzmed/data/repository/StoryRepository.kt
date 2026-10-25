package com.aistudio.medlingua.dzmed.data.repository

import android.content.Context
import com.aistudio.medlingua.dzmed.data.local.dao.MedicalDao
import com.aistudio.medlingua.dzmed.data.local.dao.StoryDao
import com.aistudio.medlingua.dzmed.data.local.entity.PhraseProgressEntity
import com.aistudio.medlingua.dzmed.data.local.entity.StorySessionProgressEntity
import com.aistudio.medlingua.dzmed.data.story.StoryAssetSource
import com.aistudio.medlingua.dzmed.domain.gamification.GamificationEconomy
import com.aistudio.medlingua.dzmed.domain.story.ExprScheds
import com.aistudio.medlingua.dzmed.domain.story.StoryPickProgress
import com.aistudio.medlingua.dzmed.domain.story.pickTodayStory
import com.aistudio.medlingua.dzmed.domain.story.PhraseSched
import com.aistudio.medlingua.dzmed.domain.story.SessionState
import com.aistudio.medlingua.dzmed.domain.story.SessionStats
import com.aistudio.medlingua.dzmed.domain.story.Story
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

/**
 * Dépôt du mode « histoire » — volontairement SÉPARÉ de [MedLinguaRepository]
 * pour ne pas entremêler les chantiers. La récompense de fin de séance est
 * idempotente : le drapeau `rewardClaimed` est vérifié et posé dans la même
 * écriture que le crédit d'XP (miroir de `claimStoryReward` côté web).
 */
class StoryRepository(
    private val context: Context,
    private val storyDao: StoryDao,
    private val medicalDao: MedicalDao
) {
    /** Récompense de participation d'une séance (miroir de STORY_SESSION_XP). */
    val sessionXp: Int = 15

    private val json = Json { ignoreUnknownKeys = true }

    @Volatile
    private var cachedStories: List<Story>? = null

    suspend fun stories(): List<Story> {
        cachedStories?.let { return it }
        val loaded = StoryAssetSource.load(context)
        cachedStories = loaded
        return loaded
    }

    suspend fun storyById(id: String): Story? = stories().firstOrNull { it.storyId == id }

    /** Séance du jour — reprise > expressions échues > jamais vue > plus ancienne. */
    suspend fun todayStory(now: Long): Story? {
        val progress = storyDao.storyProgressOfAll().associate {
            it.storyId to StoryPickProgress(completedAt = it.completedAt, savedAt = it.savedAt)
        }
        return pickTodayStory(stories(), phraseScheds(), progress, now)
    }

    /* ---------- progression de séance ---------- */

    suspend fun progress(storyId: String): StorySessionProgressEntity? =
        storyDao.storyProgress(storyId)

    suspend fun startSession(story: Story, now: Long) {
        storyDao.upsertStoryProgress(
            StorySessionProgressEntity(
                storyId = story.storyId,
                contentVersion = story.contentVersion,
                step = "REVIEW",
                statsJson = json.encodeToString(SessionStats.serializer(), SessionStats()),
                reviewRatingsJson = "{}",
                savedAt = now
            )
        )
    }

    suspend fun saveProgress(state: SessionState, now: Long) {
        val prev = storyDao.storyProgress(state.storyId)
        storyDao.upsertStoryProgress(
            StorySessionProgressEntity(
                storyId = state.storyId,
                contentVersion = state.contentVersion,
                step = state.step,
                qIndex = state.qIndex,
                drillIndex = state.drillIndex,
                reviewIndex = state.reviewIndex,
                statsJson = json.encodeToString(SessionStats.serializer(), state.stats),
                reviewRatingsJson = json.encodeToString(
                    ratingsSerializer,
                    state.reviewRatings
                ),
                retellAttempted = state.retellAttempted,
                transferTaskAttempted = state.transferTaskAttempted,
                transferTaskPending = state.transferTaskPending,
                savedAt = now,
                completedAt = prev?.completedAt ?: 0L,
                rewardClaimed = prev?.rewardClaimed ?: false
            )
        )
    }

    /**
     * Récompense idempotente : retourne l'XP effectivement créditée (0 si déjà
     * attribuée). Le crédit porte sur totalPoints / xpToday / weeklyXp ; la
     * série et les gemmes restent gérées par les parcours existants (divergence
     * assumée et documentée par rapport au web, qui touche aussi la série).
     */
    suspend fun claimReward(storyId: String, now: Long): Int {
        val progress = storyDao.storyProgress(storyId) ?: return 0
        if (progress.rewardClaimed) return 0
        storyDao.upsertStoryProgress(
            progress.copy(rewardClaimed = true, completedAt = now, savedAt = now)
        )
        val stats = medicalDao.getUserStatsOnce() ?: return sessionXp
        val today = GamificationEconomy.todayKey(now)
        val xpToday = if (stats.goalDate == today) stats.xpToday + sessionXp else sessionXp
        medicalDao.upsertUserStats(
            stats.copy(
                totalPoints = stats.totalPoints + sessionXp,
                xpToday = xpToday,
                goalDate = today,
                weeklyXp = stats.weeklyXp + sessionXp
            )
        )
        return sessionXp
    }

    /* ---------- mémoire d'expressions ---------- */

    suspend fun phraseScheds(): ExprScheds =
        storyDao.allPhrases().associate {
            it.phraseId to PhraseSched(it.repetitions, it.easeFactor, it.intervalDays, it.nextReviewTimestamp)
        }

    suspend fun savePhraseScheds(scheds: ExprScheds, now: Long) {
        if (scheds.isEmpty()) return
        storyDao.upsertPhrases(
            scheds.map { (id, s) ->
                PhraseProgressEntity(
                    phraseId = id,
                    repetitions = s.rep,
                    easeFactor = s.ef,
                    intervalDays = s.iv,
                    nextReviewTimestamp = s.due,
                    lastReviewedTimestamp = now
                )
            }
        )
    }

    private companion object {
        private val ratingsSerializer = MapSerializer(String.serializer(), Boolean.serializer())
    }
}
