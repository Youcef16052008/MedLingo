package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
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
    val level6Score: Int = 0   // Level 6 (Cas Cliniques) locked
) {
    val accuracyPercentage: Int
        get() = if (totalQuestionsAnswered == 0) 0 else ((correctAnswersCount.toDouble() / totalQuestionsAnswered) * 100).toInt()

    fun isLevelUnlocked(level: Int): Boolean {
        return when (level) {
            1 -> true // Level 1 is always unlocked
            2 -> level1Score >= 70
            3 -> level2Score >= 70
            4 -> level3Score >= 70
            5 -> level4Score >= 70
            6 -> level5Score >= 70
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
}
