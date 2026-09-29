package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.entity.LeagueCohortEntity
import com.example.data.local.entity.LeagueMemberEntity
import com.example.data.local.entity.LeagueTier
import com.example.domain.gamification.LeagueManager

@Composable
fun LeagueScreen(
    cohort: LeagueCohortEntity?,
    members: List<LeagueMemberEntity>,
    currentUserId: Int = 1,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        cohort?.let {
            LeagueHeader(cohort = it)
        }

        // Promotion/Demotion zones info
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "🏆 Top 10 → Promotion", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF16A34A))
                    Text(text = "📉 Bottom 5 → Relégation", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFDC2626))
                }
                Text(
                    text = "Semaine: ${cohort?.let { LeagueManager.formatWeekRange(it.weekStartTimestamp) } ?: ""}",
                    fontSize = 11.sp,
                    color = Color(0xFF64748B)
                )
            }
        }

        // Leaderboard
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(members.sortedByDescending { it.weeklyXp }) { index, member ->
                LeagueMemberRow(
                    member = member,
                    rank = index + 1,
                    isPromotionZone = index < 10,
                    isDemotionZone = index >= members.size - 5
                )
            }
        }
    }
}

@Composable
private fun LeagueHeader(cohort: LeagueCohortEntity) {
    val tier = LeagueTier.entries.find { it.value == cohort.tier } ?: LeagueTier.BRONZE

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(tier.colorHex).copy(alpha = 0.1f))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(tier.colorHex).copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = tier.icon, fontSize = 24.sp)
                }
                Column {
                    Text(
                        text = "Ligue ${tier.value}",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = "${cohort.cohortId.take(12)}... • 30 membres",
                        fontSize = 11.sp,
                        color = Color(0xFF64748B)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(tier.colorHex))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = tier.icon + " ${tier.value}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun LeagueMemberRow(
    member: LeagueMemberEntity,
    rank: Int,
    isPromotionZone: Boolean,
    isDemotionZone: Boolean
) {
    val bgColor = when {
        member.isCurrentUser -> Color(0xFFECFDF5)
        isPromotionZone -> Color(0xFFF0FDF4)
        isDemotionZone -> Color(0xFFFEF2F2)
        else -> Color.White
    }

    val borderColor = when {
        member.isCurrentUser -> Color(0xFF10B981)
        isPromotionZone -> Color(0xFFBBF7D0)
        isDemotionZone -> Color(0xFFFECACA)
        else -> Color(0xFFF1F5F9)
    }

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (member.isCurrentUser) 2.dp else 0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Rank
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                rank == 1 -> Color(0xFFFFD700)
                                rank == 2 -> Color(0xFFC0C0C0)
                                rank == 3 -> Color(0xFFCD7F32)
                                isPromotionZone -> Color(0xFFDCFCE7)
                                isDemotionZone -> Color(0xFFFEE2E2)
                                else -> Color(0xFFF1F5F9)
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$rank",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = if (rank <= 3) Color.White else Color(0xFF334155)
                    )
                }

                // Avatar
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF1F5F9)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = member.avatarEmoji, fontSize = 18.sp)
                }

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = member.displayName,
                            fontWeight = if (member.isCurrentUser) FontWeight.ExtraBold else FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Color(0xFF0F172A)
                        )
                        if (member.isCurrentUser) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFF10B981))
                                    .padding(horizontal = 4.dp, vertical = 1.dp)
                            ) {
                                Text(text = "TOI", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        }
                    }
                    Text(
                        text = "🔥 ${member.streakDays}j • ${if (member.isBot) "Bot" else "Actif"}",
                        fontSize = 10.sp,
                        color = Color(0xFF64748B)
                    )
                }
            }

            // XP
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${member.weeklyXp} XP",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp,
                    color = if (isPromotionZone) Color(0xFF16A34A) else if (isDemotionZone) Color(0xFFDC2626) else Color(0xFF0F172A)
                )
                if (isPromotionZone && rank <= 10) {
                    Text(text = "↑ Promotion", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF16A34A))
                } else if (isDemotionZone) {
                    Text(text = "↓ Relégation", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFFDC2626))
                }
            }
        }
    }
}
