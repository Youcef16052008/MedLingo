package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * Mascotte Medi - Duolingo Duo like but medical
 * Like Zaria 23 ans who did 2.6B impressions via edgy comments
 * Medi is a stethoscope with threatening but cute vibe
 */

enum class MediMood {
    HAPPY, SAD, ANGRY, MOTIVATING, SLEEPY, CELEBRATING, THREATENING
}

data class MediMessage(
    val text: String,
    val mood: MediMood,
    val emoji: String = "🩺"
)

object MediMessages {

    // Like Duolingo push notifications - edgy, funny, threatening cute
    val streakReminders = listOf(
        MediMessage("Tu vas vraiment briser ta série de 12 jours? 😢", MediMood.SAD, "😭"),
        MediMessage("Medi te regarde... 2h avant de perdre ta série 🔥", MediMood.THREATENING, "👀"),
        MediMessage("On avait dit 5 min par jour, pas 0 min! 😤", MediMood.ANGRY, "😤"),
        MediMessage("Ton stéthoscope va rouiller si tu ne révises pas! 🩺", MediMood.MOTIVATING, "🩺"),
        MediMessage("12 jours → 0 jour en 1 clic. Tu veux vraiment? 💔", MediMood.SAD, "💔"),
        MediMessage("Medi a préparé 6 flashcards pour toi. Tu vas les ignorer? 🥺", MediMood.SAD, "🥺")
    )

    val heartsLost = listOf(
        MediMessage("Aïe! -1 cœur. Révise l'étymologie! 📚", MediMood.SAD, "💔"),
        MediMessage("Medi est déçu... mais tu peux te rattraper! 💪", MediMood.MOTIVATING, "😬"),
        MediMessage("Encore une faute et Medi pleure! 😭", MediMood.SAD, "😭"),
        MediMessage("Bradycardie = brady (lent) + cardia (cœur). Retiens! 🧠", MediMood.MOTIVATING, "🧠")
    )

    val levelComplete = listOf(
        MediMessage("BOOM! Niveau validé! Tu es un génie! 🏆", MediMood.CELEBRATING, "🎉"),
        MediMessage("Medi est fier de toi! Prochain niveau débloqué! 🚀", MediMood.HAPPY, "🥳"),
        MediMessage("85% de précision! Même ton prof serait jaloux! 😎", MediMood.CELEBRATING, "😎"),
        MediMessage("Tu viens de débloquer le niveau Cas Cliniques! Boss final! 🏥", MediMood.HAPPY, "🏥")
    )

    val outOfHearts = listOf(
        MediMessage("Medi n'a plus de cœurs... comme après une garde de 24h! 😵", MediMood.SAD, "😵"),
        MediMessage("Plus de cœurs? Même pas en rêve! Prends Super! 🚀", MediMood.THREATENING, "💀"),
        MediMessage("Tu as tué tous les cœurs de Medi! Monstre! 👹", MediMood.ANGRY, "👹"),
        MediMessage("Medi va faire un arrêt cardiaque si tu continues! 🫀", MediMood.THREATENING, "🫀")
    )

    val leaguePromotion = listOf(
        MediMessage("PROMOTION! Bronze → Silver! Medi t'offre 100 gemmes! 💎", MediMood.CELEBRATING, "🏆"),
        MediMessage("Tu as écrasé la ligue! Les bots pleurent! 🤖😭", MediMood.HAPPY, "🤖"),
        MediMessage("Top 3! Même Amine_Med est jaloux! 😏", MediMood.CELEBRATING, "🥇")
    )

    fun getRandomMessage(type: String): MediMessage {
        return when (type) {
            "streak" -> streakReminders.random()
            "hearts_lost" -> heartsLost.random()
            "level_complete" -> levelComplete.random()
            "out_of_hearts" -> outOfHearts.random()
            "league_promotion" -> leaguePromotion.random()
            else -> MediMessage("Continue! Tu vas devenir bilingue médical! 🩺", MediMood.MOTIVATING, "🩺")
        }
    }
}

@Composable
fun MascotteMediView(
    message: MediMessage,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    showAnimation: Boolean = true
) {
    var scale by remember { mutableStateOf(1f) }
    val animatable = remember { Animatable(1f) }

    LaunchedEffect(message) {
        if (showAnimation) {
            animatable.animateTo(1.2f, tween(200))
            animatable.animateTo(1f, tween(200))
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            animatable.animateTo(1.1f, tween(300))
            animatable.animateTo(1f, tween(300))
            delay(2000)
        }
    }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .scale(animatable.value)
                    .clip(CircleShape)
                    .background(
                        when (message.mood) {
                            MediMood.HAPPY -> Color(0xFFDCFCE7)
                            MediMood.SAD -> Color(0xFFFEE2E2)
                            MediMood.ANGRY -> Color(0xFFFFE4E6)
                            MediMood.MOTIVATING -> Color(0xFFEFF6FF)
                            MediMood.SLEEPY -> Color(0xFFF3E8FF)
                            MediMood.CELEBRATING -> Color(0xFFFEF3C7)
                            MediMood.THREATENING -> Color(0xFFF3E8FF)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = message.emoji, fontSize = 28.sp)
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Medi",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp,
                        color = Color(0xFF0F172A)
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFF10B981))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = when (message.mood) {
                                MediMood.HAPPY -> "CONTENT"
                                MediMood.SAD -> "TRISTE"
                                MediMood.ANGRY -> "FÂCHÉ"
                                MediMood.MOTIVATING -> "MOTIVANT"
                                MediMood.SLEEPY -> "FATIGUÉ"
                                MediMood.CELEBRATING -> "FÊTE"
                                MediMood.THREATENING -> "MENACANT"
                            },
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
                Text(
                    text = message.text,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF334155),
                    lineHeight = 18.sp
                )
            }

            Text(text = "💬", fontSize = 16.sp)
        }
    }
}

@Composable
fun MediFloatingButton(
    onClick: () -> Unit,
    hasNotification: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(Color(0xFF10B981))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "🩺", fontSize = 28.sp)
        if (hasNotification) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEF4444)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "!", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}
