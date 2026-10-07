package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.gamification.ChestKind
import com.example.domain.gamification.ChestReward
import com.example.localization.Language
import com.example.localization.Strings
import kotlinx.coroutines.delay

/** Données affichées par la pop-up de récompense (spec §11). */
data class RewardData(
    /** XP gagnés pendant la session. */
    val xp: Int,
    /** Gemmes gagnées pendant la session. */
    val gems: Int,
    /** L'objectif quotidien vient d'être atteint. */
    val goalHit: Boolean,
    /** Nouveaux trophées : icône à titre (localisés par l'appelant). */
    val trophies: List<Pair<String, String>>
)

private val CONFETTI_COLORS = listOf(
    Color(0xFF58CC02), Color(0xFF1CB0F6), Color(0xFFFFC800),
    Color(0xFFFF4B4B), Color(0xFFCE82FF), Color(0xFFFF9600)
)

/**
 * Pop-up de récompense (spec §11) : compteur `+N XP` animé → lignes
 * gemmes / objectif / trophées → ouverture de caisse → Continuer.
 */
@Composable
fun RewardPopup(
    data: RewardData,
    chestCount: Int,
    chestReward: ChestReward?,
    onOpenChest: () -> Unit,
    onContinue: () -> Unit,
    lang: Language = Language.FRENCH,
    modifier: Modifier = Modifier
) {
    var shown by remember(data.xp) { mutableIntStateOf(0) }
    LaunchedEffect(data.xp) {
        if (data.xp <= 0) return@LaunchedEffect
        var current = 0
        val step = maxOf(1, data.xp / 12)
        while (current < data.xp) {
            current = minOf(data.xp, current + step)
            shown = current
            delay(55)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xCC000000))
            .clickable(enabled = false) { },
        contentAlignment = Alignment.Center
    ) {
        // Confettis (12 points colorés) — fixes : une transition infinie ici
        // recomposait la pop-up entière et provoquait des ANR.
        repeat(12) { i ->
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(
                        start = (((i * 8.3) + 3) % 96).dp,
                        top = (40 + (i % 5) * 46).dp
                    )
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(CONFETTI_COLORS[i % 6].copy(alpha = 0.9f))
            )
        }

        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Medi(
                    mood = if (data.goalHit || data.trophies.isNotEmpty()) {
                        MediMood.CELEBRATING
                    } else {
                        MediMood.HAPPY
                    },
                    lang = lang
                )

                Text(
                    text = "+$shown ⭐",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF58CC02)
                )
                Text(
                    text = Strings.get("reward_title", lang),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF64748B)
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (data.gems > 0) {
                        RewardRow("💎 +${data.gems}", Strings.get("stat_gems", lang), gold = false)
                    }
                    if (data.goalHit) {
                        RewardRow("🎯 ${Strings.get("reward_goal", lang)}", "✅", gold = true)
                    }
                    data.trophies.forEach { (icon, title) ->
                        RewardRow("$icon $title", Strings.get("reward_trophy", lang), gold = true)
                    }
                }

                if (chestReward == null && chestCount > 0) {
                    Text(
                        text = "📦 ${Strings.get("reward_open_chest", lang)} ($chestCount)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFFFFC800))
                            .clickable { onOpenChest() }
                            .padding(vertical = 12.dp)
                    )
                }
                if (chestReward != null) {
                    val label = when (chestReward.kind) {
                        ChestKind.GEMS -> "🎁 💎 +${chestReward.amount}"
                        ChestKind.FREEZE -> "🎁 ❄️ +${chestReward.amount}"
                        ChestKind.XP -> "🎁 ⭐ +${chestReward.amount}"
                    }
                    Text(
                        text = label,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF1E293B),
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFFF1F8E9))
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                    )
                }

                Text(
                    text = Strings.get("reward_continue", lang),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFF58CC02))
                        .clickable { onContinue() }
                        .padding(vertical = 13.dp)
                )
            }
        }
    }
}

@Composable
private fun RewardRow(main: String, label: String, gold: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = main,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (gold) Color(0xFFB45309) else Color(0xFF1E293B)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF64748B)
        )
    }
}
