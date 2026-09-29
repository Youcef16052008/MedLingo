package com.example.domain.gamification

import com.example.data.local.entity.LeagueCohortEntity
import com.example.data.local.entity.LeagueMemberEntity
import com.example.data.local.entity.LeagueTier
import java.util.Calendar
import java.util.UUID

/**
 * LeagueManager - Duolingo Leagues 100% compliant
 * Basé sur Duolingo Redis Sorted Sets league:{week}:{cohort}
 *
 * Local MVP: Room + bots simulés
 * Production: Redis + cron hebdo Vercel
 *
 * Règles Duolingo:
 * - 30 users par cohorte
 * - Reset lundi 00:00 UTC
 * - Top 10 promotion, bottom 5 demotion
 * - 10 tiers: Bronze → Diamond
 */
object LeagueManager {

    const val COHORT_SIZE = 30
    const val PROMOTION_COUNT = 10
    const val DEMOTION_COUNT = 5

    fun getWeekStartTimestamp(): Long {
        val cal = Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC"))
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        // Si aujourd'hui est dimanche, le lundi est demain, donc reculer d'une semaine
        if (cal.timeInMillis > System.currentTimeMillis()) {
            cal.add(Calendar.WEEK_OF_YEAR, -1)
        }
        return cal.timeInMillis
    }

    fun getWeekEndTimestamp(weekStart: Long): Long {
        return weekStart + (7 * 24 * 60 * 60 * 1000L) - 1
    }

    fun generateCohortId(tier: String = "BRONZE"): String {
        val week = getWeekStartTimestamp()
        val random = UUID.randomUUID().toString().take(4)
        return "${week}-${tier.lowercase()}-${random}"
    }

    fun createNewCohort(tier: String = "BRONZE"): LeagueCohortEntity {
        val weekStart = getWeekStartTimestamp()
        return LeagueCohortEntity(
            cohortId = generateCohortId(tier),
            weekStartTimestamp = weekStart,
            weekEndTimestamp = getWeekEndTimestamp(weekStart),
            tier = tier,
            isActive = true
        )
    }

    /**
     * Génère 29 bots + 1 user pour remplir cohorte 30
     * Bots avec XP réaliste pour compétition
     */
    fun generateBotsForCohort(
        cohortId: String,
        currentUserName: String = "Dr. Youcef",
        currentUserXp: Int = 0,
        currentUserStreak: Int = 12
    ): List<LeagueMemberEntity> {
        val botNames = listOf(
            "Amine_Med" to "👨‍⚕️", "Sara_Anat" to "👩‍⚕️", "Yacine_Pharm" to "💊",
            "Nour_Histo" to "🔬", "Mehdi_Cardio" to "❤️", "Lina_Pedia" to "👶",
            "Karim_Chir" to "🏥", "Imane_Bio" to "🧬", "Omar_Urge" to "🚑",
            "Fatima_Radio" to "🩻", "Anis_Neuro" to "🧠", "Zineb_Gyne" to "👩‍⚕️",
            "Riad_Ortho" to "🦴", "Samia_Derm" to "🧴", "Bilal_Oph" to "👁️",
            "Hana_Psy" to "🧠", "Tarek_Anest" to "💉", "Mouna_Labo" to "🧪",
            "Fares_Inter" to "🎓", "Dounia_Exter" to "📚", "Sofiane_Res" to "⚕️",
            "Amina_Infi" to "💉", "Nadir_Kine" to "🦾", "Leila_Sage" to "🤱",
            "Hakim_Gene" to "🧬", "Salma_Phys" to "❤️", "Youcef_Bio" to "🧪",
            "Rania_Micro" to "🦠", "Walid_Semio" to "🩺", "Ines_Anapath" to "🫀"
        )

        val members = mutableListOf<LeagueMemberEntity>()

        // Current user
        members.add(
            LeagueMemberEntity(
                cohortId = cohortId,
                userId = 1,
                displayName = currentUserName,
                avatarEmoji = "🔥",
                weeklyXp = currentUserXp,
                totalXp = 2850 + currentUserXp,
                streakDays = currentUserStreak,
                isCurrentUser = true,
                isBot = false
            )
        )

        // 29 bots avec XP aléatoire mais réaliste
        botNames.forEachIndexed { index, (name, emoji) ->
            // XP entre 0 et 500, avec quelques forts pour challenge
            val xp = when {
                index < 3 -> (400..600).random() // top 3 forts
                index < 10 -> (150..400).random() // milieu compétitif
                else -> (0..200).random() // bas
            }
            members.add(
                LeagueMemberEntity(
                    cohortId = cohortId,
                    userId = 100 + index,
                    displayName = name,
                    avatarEmoji = emoji,
                    weeklyXp = xp,
                    totalXp = (1000..5000).random(),
                    streakDays = (1..20).random(),
                    isCurrentUser = false,
                    isBot = true
                )
            )
        }

        // Trier par XP DESC et assigner rank
        return members.sortedByDescending { it.weeklyXp }.mapIndexed { idx, member ->
            member.copy(rank = idx + 1)
        }
    }

    fun checkPromotionDemotion(members: List<LeagueMemberEntity>, currentUserId: Int = 1): LeagueResult {
        val sorted = members.sortedByDescending { it.weeklyXp }
        val currentUserIndex = sorted.indexOfFirst { it.userId == currentUserId }
        if (currentUserIndex == -1) return LeagueResult.STAY

        return when {
            currentUserIndex < PROMOTION_COUNT -> LeagueResult.PROMOTION
            currentUserIndex >= sorted.size - DEMOTION_COUNT -> LeagueResult.DEMOTION
            else -> LeagueResult.STAY
        }
    }

    fun getNextTier(currentTier: String, result: LeagueResult): String {
        return when (result) {
            LeagueResult.PROMOTION -> LeagueTier.nextTier(currentTier).value
            LeagueResult.DEMOTION -> LeagueTier.prevTier(currentTier).value
            LeagueResult.STAY -> currentTier
        }
    }

    enum class LeagueResult {
        PROMOTION, DEMOTION, STAY
    }

    fun formatWeekRange(weekStart: Long): String {
        val calStart = Calendar.getInstance().apply { timeInMillis = weekStart }
        val calEnd = Calendar.getInstance().apply { timeInMillis = getWeekEndTimestamp(weekStart) }
        val monthStart = calStart.getDisplayName(Calendar.MONTH, Calendar.SHORT, java.util.Locale.FRENCH) ?: ""
        val monthEnd = calEnd.getDisplayName(Calendar.MONTH, Calendar.SHORT, java.util.Locale.FRENCH) ?: ""
        return "${calStart.get(Calendar.DAY_OF_MONTH)} $monthStart - ${calEnd.get(Calendar.DAY_OF_MONTH)} $monthEnd"
    }
}
