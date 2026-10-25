package com.aistudio.medlingua.dzmed.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.SentimentSatisfiedAlt
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.medlingua.dzmed.data.local.entity.UserStatsEntity
import com.aistudio.medlingua.dzmed.localization.Language
import com.aistudio.medlingua.dzmed.localization.Strings
import com.aistudio.medlingua.dzmed.ui.theme.AmberGold
import com.aistudio.medlingua.dzmed.ui.theme.DeepGreen
import com.aistudio.medlingua.dzmed.ui.theme.MedGreenDark
import com.aistudio.medlingua.dzmed.ui.theme.MedGreenPrimary
import com.aistudio.medlingua.dzmed.ui.theme.SemanticBlue
import com.aistudio.medlingua.dzmed.ui.theme.SemanticYellow
import com.aistudio.medlingua.dzmed.ui.theme.SemanticViolet
import com.aistudio.medlingua.dzmed.ui.theme.TextSecondary
import com.aistudio.medlingua.dzmed.ui.theme.WarmGray
import com.aistudio.medlingua.dzmed.ui.theme.MedGreenPrimary
import com.aistudio.medlingua.dzmed.ui.theme.SemanticGreen
import com.aistudio.medlingua.dzmed.ui.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerScreen(
    uiState: UiState,
    onNavigateToLessons: () -> Unit,
    onNavigateToFlashcards: () -> Unit,
    onNavigateToPath: () -> Unit,
    modifier: Modifier = Modifier
) {
    val stats = uiState.userStats
    val lang = uiState.currentLanguage

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // En-tête du profil
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = Strings.get("profile", lang),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepGreen
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = Strings.get("player_stats_summary", lang),
                            fontSize = 14.sp,
                            color = WarmGray
                        )
                    }

                    IconButton(
                        onClick = { /* Méditation quitter */ }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour",
                            tint = MedGreenDark,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            // Statistiques principales (3 colonnes)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // XP
                PlayerStatCard(
                    icon = Icons.Default.TrendingUp,
                    iconColor = MedGreenDark,
                    value = stats.totalPoints.toString(),
                    label = Strings.get("total_points", lang),
                    modifier = Modifier.weight(1f)
                )

                // Series 🔥
                PlayerStatCard(
                    icon = Icons.Default.SentimentSatisfiedAlt,
                    iconColor = SemanticYellow,
                    value = stats.streakDays.toString(),
                    label = Strings.get("streak", lang),
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Gemmes 💎
                PlayerStatCard(
                    icon = Icons.Default.SportsTennis,
                    iconColor = MedGreenPrimary,
                    value = stats.gems.toString(),
                    label = Strings.get("gems", lang),
                    modifier = Modifier.weight(1f)
                )

                // Questions répondues
                PlayerStatCard(
                    icon = Icons.Default.Check,
                    iconColor = SemanticBlue,
                    value = stats.totalQuestionsAnswered.toString(),
                    label = Strings.get("questions_answered", lang),
                    modifier = Modifier.weight(1f)
                )
            }

            // Série d'étude (détaillée)
            if (stats.streakDays > 0) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(SemanticYellow.copy(alpha = 0.15f))
                                .border(1.dp, SemanticYellow.copy(alpha = 0.3f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🔥",
                                fontSize = 20.sp,
                                color = SemanticYellow
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = Strings.get("streak_days", lang),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepGreen
                            )
                            Text(
                                text = Strings.get("streak_bonus", lang) + stats.streakDays,
                                fontSize = 12.sp,
                                color = WarmGray
                            )
                        }

                        // Bouton débloquer la série
                        if (stats.streakFreezeCount > 0) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CloudQueue,
                                    contentDescription = null,
                                    tint = AmberGold,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = Strings.get("streak_freeze", lang),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = AmberGold
                                )
                            }
                        }
                    }
                }
            }

            // Trophées
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = SemanticGreen,
                        modifier = Modifier.size(20.dp)
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = Strings.get("trophies", lang),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepGreen
                        )
                        Text(
                            text = Strings.get("trophies_description", lang),
                            fontSize = 12.sp,
                            color = WarmGray
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        // Trophées obtenus (ateliers)
                        TrophyIcon("🌱", "1")
                        TrophyIcon("🔥", "30")
                        TrophyIcon("⚡", "5000")
                    }
                }
            }

            // Objectif quotidien
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = Strings.get("daily_goal", lang),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepGreen
                        )
                        Text(
                            text = Strings.get("daily_goal_text", lang),
                            fontSize = 12.sp,
                            color = WarmGray
                        )
                    }

                    // Barre de progression
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        // Fond de la barre
                        Box(
                            modifier = Modifier
                                .weight(1f - (uiState.goalProgress.pct / 100f).coerceIn(0f, 1f))
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(WarmGray.copy(alpha = 0.2f))
                        )

                        // Remplissage - poids proportionnel au pourcentage
                        Box(
                            modifier = Modifier
                                .weight((uiState.goalProgress.pct / 100f).coerceIn(0f, 1f))
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    if (uiState.goalProgress.done) SemanticGreen
                                    else MedGreenPrimary
                                )
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${uiState.goalProgress.today} / ${uiState.goalProgress.goal} XP",
                            fontSize = 12.sp,
                            color = WarmGray
                        )
                        Text(
                            text = "${uiState.goalProgress.pct}%",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (uiState.goalProgress.done) SemanticGreen else MedGreenDark
                        )
                    }
                }
            }

            // Bouton principal : Lancer les leçons
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MedGreenPrimary
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "📚",
                            fontSize = 32.sp,
                            color = Color.White
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = Strings.get("start_learning", lang),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = Strings.get("start_learning_sub", lang),
                            fontSize = 13.sp,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }

                    IconButton(
                        onClick = { onNavigateToPath() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.TrendingUp,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PlayerStatCard(
    icon: ImageVector,
    iconColor: Color,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(iconColor.copy(alpha = 0.15f))
                        .border(1.dp, iconColor.copy(alpha = 0.3f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Column {
                    Text(
                        text = value,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DeepGreen
                    )
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        color = WarmGray
                    )
                }
            }

            // Icône de progression
            if (label.contains("XP") || label.contains("score")) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(SemanticGreen.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = SemanticGreen,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun TrophyIcon(emoji: String, count: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(WarmGray.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = emoji,
                fontSize = 14.sp,
                color = WarmGray
            )
        }
        Text(
            text = count,
            fontSize = 8.sp,
            color = WarmGray
        )
    }
}
