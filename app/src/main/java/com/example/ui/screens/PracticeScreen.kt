package com.example.ui.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.domain.gamification.ProgressionManager
import com.example.localization.Strings
import com.example.ui.components.GoalRing
import com.example.ui.components.Medi
import com.example.ui.components.MediMood
import com.example.ui.viewmodel.UiState

/**
 * Hub 🏋️ Révision (miroir de `web-react/src/screens/PracticeScreen.tsx`) :
 * quatre portes d'entrée d'entraînement + anneau d'objectif + Medi.
 */
@Composable
fun PracticeScreen(
    uiState: UiState,
    onOpenReview: () -> Unit,
    onOpenQuiz: () -> Unit,
    onOpenWeak: () -> Unit,
    onOpenExam: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lang = uiState.currentLanguage
    val stats = uiState.userStats
    val now = System.currentTimeMillis()
    val goal = ProgressionManager.goalProgress(stats, now)
    val due = uiState.dueFlashcardProgress.size

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8F6))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = Strings.get("practice_sub", lang),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF64748B)
                )
                Text(
                    text = Strings.get("practice_title", lang),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1E293B)
                )
            }
            Text(
                text = "⭐ ${goal.today} / ${goal.goal}",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB45309),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFFFF3C4))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GoalRing(pct = goal.pct, done = goal.done, size = 56.dp)
            Medi(
                mood = when {
                    goal.done -> MediMood.CELEBRATING
                    due > 0 -> MediMood.MOTIVATING
                    else -> MediMood.HAPPY
                },
                lang = lang,
                modifier = Modifier.weight(1f)
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            PracticeCard(
                icon = "🔁",
                title = Strings.get("practice_due", lang),
                desc = Strings.get("practice_due_desc", lang),
                badge = due,
                tone = Color(0xFF00897B),
                onClick = onOpenReview
            )
            PracticeCard(
                icon = "🧠",
                title = Strings.get("practice_quiz", lang),
                desc = Strings.get("practice_quiz_desc", lang),
                badge = 0,
                tone = Color(0xFF0288D1),
                onClick = onOpenQuiz
            )
            PracticeCard(
                icon = "💪",
                title = Strings.get("practice_weak", lang),
                desc = Strings.get("practice_weak_desc", lang),
                badge = 0,
                tone = Color(0xFFD97706),
                onClick = onOpenWeak
            )
            PracticeCard(
                icon = "🎓",
                title = Strings.get("practice_exam", lang),
                desc = Strings.get("practice_exam_desc", lang),
                badge = 0,
                tone = Color(0xFF7B1FA2),
                onClick = onOpenExam
            )
        }
    }
}

@Composable
private fun PracticeCard(
    icon: String,
    title: String,
    desc: String,
    badge: Int,
    tone: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(tone.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = icon, fontSize = 22.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                    if (badge > 0) {
                        Text(
                            text = badge.toString(),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFE65100))
                                .padding(horizontal = 7.dp, vertical = 2.dp)
                        )
                    }
                }
                Text(
                    text = desc,
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
