package com.example.domain.gamification

import com.example.data.local.entity.UserStatsEntity

/**
 * 12 trophées affichés en grille sur l'écran « Moi ».
 * [evaluate] est pur ; [claimTrophies] ne retourne que les gains nouveaux,
 * pour être affichés dans la pop-up de récompense.
 */
enum class TrophyId(
    val icon: String,
    val titleKey: String,
    val descKey: String
) {
    FIRST_LESSON("🌱", "trophy_first_lesson", "trophy_first_lesson_d"),
    FIRST_PERFECT("💯", "trophy_first_perfect", "trophy_first_perfect_d"),
    STREAK_7("🔥", "trophy_streak_7", "trophy_streak_7_d"),
    STREAK_30("🌋", "trophy_streak_30", "trophy_streak_30_d"),
    XP_1000("⚡", "trophy_xp_1000", "trophy_xp_1000_d"),
    XP_5000("🚀", "trophy_xp_5000", "trophy_xp_5000_d"),
    CARDS_100("🗂️", "trophy_cards_100", "trophy_cards_100_d"),
    QUIZZES_10("🧠", "trophy_quizzes_10", "trophy_quizzes_10_d"),
    GOAL_HIT_7("🎯", "trophy_goal_hit_7", "trophy_goal_hit_7_d"),
    MODULE_MASTER("👑", "trophy_module_master", "trophy_module_master_d"),
    LEAGUE_PROMOTED("📈", "trophy_league_promoted", "trophy_league_promoted_d"),
    CHESTS_5("📦", "trophy_chests_5", "trophy_chests_5_d")
}

object TrophyManager {

    /** Les 12 trophées, dans l'ordre d'affichage de la grille. */
    val ALL: List<TrophyId> = TrophyId.entries.toList()

    /**
     * Tous les trophées satisfaits par l'état courant (pur).
     * Les seuils reprennent ceux du web : le score d'un niveau à ≥ 90 % tient lieu de
     * « module maîtrisé » (l'Android suit les scores par niveau, pas par module).
     */
    fun evaluate(stats: UserStatsEntity): List<TrophyId> {
        val won = mutableListOf<TrophyId>()
        if (stats.quizzesCompleted >= 1) won += TrophyId.FIRST_LESSON
        if (stats.perfectLessonsCount >= 1) won += TrophyId.FIRST_PERFECT
        if (stats.streakDays >= 7) won += TrophyId.STREAK_7
        if (stats.streakDays >= 30) won += TrophyId.STREAK_30
        if (stats.totalPoints >= 1000) won += TrophyId.XP_1000
        if (stats.totalPoints >= 5000) won += TrophyId.XP_5000
        if (stats.flashReviewed >= 100) won += TrophyId.CARDS_100
        if (stats.quizzesCompleted >= 10) won += TrophyId.QUIZZES_10
        if (stats.goalDays >= 7) won += TrophyId.GOAL_HIT_7
        if ((1..6).any { stats.getScoreForLevel(it) >= 90 }) won += TrophyId.MODULE_MASTER
        if (stats.leagueTier != "BRONZE") won += TrophyId.LEAGUE_PROMOTED
        if (stats.chestsOpened >= 5) won += TrophyId.CHESTS_5
        return won
    }

    /**
     * Les trophées satisfaits et pas encore enregistrés.
     * [earned] = ids déjà présents dans la table `trophies` ; [now] = horodatage (ms).
     */
    fun claimTrophies(
        stats: UserStatsEntity,
        earned: Set<String>,
        now: Long
    ): List<Pair<TrophyId, Long>> =
        evaluate(stats)
            .filter { !earned.contains(it.name) }
            .map { it to now }
}
