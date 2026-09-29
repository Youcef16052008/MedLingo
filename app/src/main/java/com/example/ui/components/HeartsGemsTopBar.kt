package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.gamification.HeartsManager

/**
 * HeartsGemsTopBar - Duolingo style top bar
 * Shows: Streak 🔥, Gems 💎, Hearts ❤️, League tier
 * Like Duolingo: top bar with 4 items
 */
@Composable
fun HeartsGemsTopBar(
    streakDays: Int,
    gems: Int,
    hearts: Int,
    currentHearts: Int,
    maxHearts: Int = 5,
    isSuper: Boolean,
    timeUntilNextHeart: Long,
    leagueTier: String,
    weeklyXp: Int,
    onHeartsClick: () -> Unit = {},
    onGemsClick: () -> Unit = {},
    onLeagueClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Streak 🔥
        TopBarItem(
            icon = "🔥",
            value = "$streakDays",
            bgColor = Color(0xFFFFF7ED),
            textColor = Color(0xFFEA580C),
            onClick = {}
        )

        // Gems 💎
        TopBarItem(
            icon = "💎",
            value = "$gems",
            bgColor = Color(0xFFEFF6FF),
            textColor = Color(0xFF2563EB),
            onClick = onGemsClick
        )

        // Hearts ❤️ - Duolingo style
        HeartsItem(
            currentHearts = currentHearts,
            maxHearts = maxHearts,
            isSuper = isSuper,
            timeUntilNextHeart = timeUntilNextHeart,
            onClick = onHeartsClick
        )

        // League Tier
        TopBarItem(
            icon = getLeagueIcon(leagueTier),
            value = leagueTier.take(4),
            bgColor = Color(0xFFFEF3C7),
            textColor = Color(0xFFD97706),
            onClick = onLeagueClick,
            extra = "${weeklyXp}XP"
        )
    }
}

@Composable
private fun TopBarItem(
    icon: String,
    value: String,
    bgColor: Color,
    textColor: Color,
    onClick: () -> Unit,
    extra: String? = null
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bgColor)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = icon, fontSize = 16.sp)
            Text(
                text = value,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                color = textColor
            )
            if (extra != null) {
                Text(
                    text = extra,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
private fun HeartsItem(
    currentHearts: Int,
    maxHearts: Int,
    isSuper: Boolean,
    timeUntilNextHeart: Long,
    onClick: () -> Unit
) {
    val bgColor = if (currentHearts == 0) Color(0xFFFEE2E2) else Color(0xFFFFF1F2)
    val textColor = if (currentHearts == 0) Color(0xFFDC2626) else Color(0xFFE11D48)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bgColor)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = if (isSuper) "💖" else "❤️",
                fontSize = 16.sp
            )
            Text(
                text = if (isSuper) "∞" else "$currentHearts",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                color = textColor
            )
            if (!isSuper && currentHearts < maxHearts) {
                val minutes = (timeUntilNextHeart / (1000 * 60)).toInt()
                if (minutes > 0) {
                    Text(
                        text = if (minutes >= 60) "${minutes / 60}h" else "${minutes}m",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

private fun getLeagueIcon(tier: String): String {
    return when (tier) {
        "BRONZE" -> "🥉"
        "SILVER" -> "🥈"
        "GOLD" -> "🥇"
        "SAPPHIRE" -> "💎"
        "RUBY" -> "♦️"
        "EMERALD" -> "💚"
        "AMETHYST" -> "💜"
        "PEARL" -> "⚪"
        "OBSIDIAN" -> "⚫"
        "DIAMOND" -> "🔷"
        else -> "🏆"
    }
}
