package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.domain.time.Clock
import com.example.domain.time.SystemClock

@Entity(
    tableName = "user_stats",
    indices = [
        Index("leagueCohortId"),
        Index("weeklyXp")
    ]
)
data class UserStatsEntity(
    @PrimaryKey
    val id: Int = 1,
    val streakDays: Int = 12,
    val learnedTermsCount: Int = 156,
    val totalPoints: Int = 2450,
    val quizzesCompleted: Int = 18,
    val totalQuestionsAnswered: Int = 92,
    val correctAnswersCount: Int = 78,
    val isOfflineSimulated: Boolean = false,
    val selectedLanguageCode: String = "fr",
    // 6 Progressive Learning Levels Scores (Percentage 0-100)
    // Unlock condition: Each level unlocks when the previous level reaches >= 70%
    val level1Score: Int = 85, // Level 1 (Vocabulaire) passed
    val level2Score: Int = 75, // Level 2 (Collocations) passed
    val level3Score: Int = 0,  // Level 3 (Phrases Simples) unlocked & ready
    val level4Score: Int = 0,  // Level 4 (Phrases Complexes) locked
    val level5Score: Int = 0,  // Level 5 (Paragraphes) locked
    val level6Score: Int = 0,   // Level 6 (Cas Cliniques) locked

    // === DUOLINGO PHASE 2: Game Economy (sans cœurs) ===
    // Gems Ledger - append-only balance derived (anti-cheat)
    val gems: Int = 100, // Starting gems like Duolingo

    // League System
    val weeklyXp: Int = 0,
    val leagueCohortId: String? = null,
    val leagueTier: String = "BRONZE",

    // Super MedLingo (Duolingo Plus) - Tinder Plus model
    val isSuper: Boolean = false,
    val superExpiresAt: Long? = null,
    val streakFreezeCount: Int = 0, // congélations de série (❄️, 200 💎 pièce)

    // Additional Duolingo stats
    val perfectLessonsCount: Int = 0,
    val lessonsCompleted: Int = 18,

    // === DUOLINGO PHASE 2: objectif quotidien, caisses, trophées ===
    /** XP gagné aujourd'hui ; remis à zéro au changement de jour civil. */
    val xpToday: Int = 0,
    /** Jour civil (`yyyy-MM-dd`) du dernier XP crédité. */
    val goalDate: String = "",
    /** Objectif quotidien choisi (20 / 50 / 100 XP). */
    val dailyGoal: Int = 50,
    /** Jour (`yyyy-MM-dd`) de la dernière étude, pilier de la série 🔥. */
    val lastStudy: String = "",
    /** Jours de la fenêtre hebdomadaire ayant atteint l'objectif (trophée 🎯). */
    val goalDays: Int = 0,
    /** Flashcards relues depuis le début (trophée 🗂️). */
    val flashReviewed: Int = 0,
    /** Caisse gagnée mais pas encore ouverte. */
    val pendingChests: Int = 0,
    /** Caisse déjà ouverte (trophée 📦). */
    val chestsOpened: Int = 0,
    /** Début de semaine (ms, lundi UTC) du dernier reset hebdomadaire. */
    val weeklyXpReset: Long = 0L
) {
    val accuracyPercentage: Int
        get() = if (totalQuestionsAnswered == 0) 0 else ((correctAnswersCount.toDouble() / totalQuestionsAnswered) * 100).toInt()

    fun isLevelUnlocked(level: Int): Boolean {
        return when (level) {
            1 -> true // Level 1 is always unlocked
            2 -> level1Score >= UNLOCK_THRESHOLD
            3 -> level2Score >= UNLOCK_THRESHOLD
            4 -> level3Score >= UNLOCK_THRESHOLD
            5 -> level4Score >= UNLOCK_THRESHOLD
            6 -> level5Score >= UNLOCK_THRESHOLD
            else -> false
        }
    }

    fun getScoreForLevel(level: Int): Int {
        return when (level) {
            1 -> level1Score
            2 -> level2Score
            3 -> level3Score
            4 -> level4Score
            5 -> level5Score
            6 -> level6Score
            else -> 0
        }
    }

    fun highestUnlockedLevel(): Int {
        for (lvl in 6 downTo 1) {
            if (isLevelUnlocked(lvl)) return lvl
        }
        return 1
    }

    fun isSuperActive(clock: Clock = SystemClock): Boolean {
        if (!isSuper) return false
        if (superExpiresAt == null) return true // lifetime for debug
        return clock.now() < superExpiresAt
    }

    companion object {
        const val UNLOCK_THRESHOLD = 70

        fun createDefault(clock: Clock = SystemClock): UserStatsEntity {
            return UserStatsEntity(
                id = 1,
                streakDays = 12,
                learnedTermsCount = 156,
                totalPoints = 2450,
                quizzesCompleted = 18,
                totalQuestionsAnswered = 92,
                correctAnswersCount = 78,
                isOfflineSimulated = false,
                selectedLanguageCode = "fr",
                level1Score = 85,
                level2Score = 75,
                level3Score = 0,
                level4Score = 0,
                level5Score = 0,
                level6Score = 0,
                gems = 100,
                weeklyXp = 0,
                leagueCohortId = null,
                leagueTier = "BRONZE",
                isSuper = false,
                superExpiresAt = null,
                streakFreezeCount = 0,
                perfectLessonsCount = 0,
                lessonsCompleted = 18
            )
        }
    }
}
