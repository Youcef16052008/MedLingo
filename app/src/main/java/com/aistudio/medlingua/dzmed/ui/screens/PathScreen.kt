package com.aistudio.medlingua.dzmed.ui.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import android.content.Context
import com.aistudio.medlingua.dzmed.domain.gamification.GamificationEconomy
import com.aistudio.medlingua.dzmed.domain.gamification.LeagueManager
import com.aistudio.medlingua.dzmed.domain.path.NodeState
import com.aistudio.medlingua.dzmed.domain.path.PathBuilder
import com.aistudio.medlingua.dzmed.domain.path.PathModule
import com.aistudio.medlingua.dzmed.domain.path.PathNode
import com.aistudio.medlingua.dzmed.ui.components.GoalRing
import com.aistudio.medlingua.dzmed.ui.components.Medi
import com.aistudio.medlingua.dzmed.ui.components.MediMood
import com.aistudio.medlingua.dzmed.ui.viewmodel.UiState
import com.aistudio.medlingua.dzmed.data.initial.InitialData
import com.aistudio.medlingua.dzmed.localization.Language
import com.aistudio.medlingua.dzmed.localization.Strings
import com.aistudio.medlingua.dzmed.ui.theme.MedGreenDark
import java.util.Calendar

/**
 * Onglet 📍 Parcours (miroir de `web-react/src/screens/PathScreen.tsx`) :
 * en-tête Medi + anneau d'objectif + série/congélation + caisse, puis les
 * 15 unités de 6 leçons. Un nœud verrouillé est désactivé (« 70 % requis »).
 */
@Composable
fun PathScreen(
    uiState: UiState,
    onOpenLesson: (moduleId: String, level: Int) -> Unit,
    onBuyFreeze: () -> Unit,
    onOpenChests: () -> Unit,
    onOpenStory: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val lang = uiState.currentLanguage
    val stats = uiState.userStats
    val now = System.currentTimeMillis()
    val goal = GamificationEconomy.goalProgress(stats, now)
    val due = uiState.dueFlashcardProgress.size

    val units: List<PathBuilder.LessonPathUnit> by lazy {
        val allUnits = mutableListOf<PathBuilder.LessonPathUnit>()
        InitialData.modulesList.forEach { moduleInfo ->
            val chapterMap = (InitialData.chaptersOfModule(moduleInfo.id).distinct().map { chap ->
                chap to PathBuilder.PathChapter(
                    chapterId = chap,
                    title = chap,
                    moduleId = chap,
                    nodes = (0 until 6).map { idx ->
                        PathNode(
                            moduleId = chap,
                            level = idx + 1,
                            state = NodeState.LOCKED,
                            score = 0,
                            isBoss = false
                        )
                    }
                )
            }).toMap()
            allUnits += PathBuilder.buildLessonPath(
                lessonBest = uiState.lessonBest,
                modules = listOf(PathModule(moduleInfo.id, moduleTitle(moduleInfo, lang))),
                chapterMap = chapterMap,
                nodeStates = { _, _ -> NodeState.LOCKED },
                onScore = { _, _ -> 0 }
            )
        }
        allUnits
    }

    val next = units.asSequence()
        .flatMap { it.chapters.asSequence() }
        .flatMap { it.nodes.asSequence() }
        .firstOrNull { it.state == com.aistudio.medlingua.dzmed.domain.path.NodeState.AVAILABLE }

    val mood = when {
        goal.done -> MediMood.CELEBRATING
        stats.streakDays == 0 -> MediMood.MOTIVATING
        else -> MediMood.HAPPY
    }
    var tipVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF0F7F4))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // ---- Ma séance d'aujourd'hui (mode histoire, guide §2.2) ----
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F5EC)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("📖 " + Strings.get("story_card_title", lang), fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MedGreenDark)
                Text(Strings.get("story_card_sub", lang), fontSize = 12.sp, color = Color(0xFF6B7877))
                Button(
                    onClick = onOpenStory,
                    colors = ButtonDefaults.buttonColors(containerColor = MedGreenDark),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(Strings.get("story_start", lang))
                }
            }
        }

        // ---- En-tête : Medi + anneau + série/congélation + caisse ----
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Medi(
                        mood = mood,
                        message = next?.let { node ->
                            val greet = "Bienvenue"
                            val chapterTitle = "Chapitre ${node.moduleId.take(4)}"
                            "$greet · $chapterTitle L${node.level}"
                        } ?: "Bienvenue",
                        lang = lang,
                        modifier = Modifier.weight(1f)
                    )
                    GoalRing(pct = goal.pct, done = goal.done)
                }

                Text(
                    text = "${goal.today} / ${goal.goal} ${Strings.get("stat_xp", lang)} · " +
                        "$due ${Strings.get("cards_due", lang)}",
                    fontSize = 12.sp,
                    color = Color(0xFF6B7877)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StreakPill("🔥 ${stats.streakDays} ${Strings.get("stat_streak", lang)}")
                    StreakPill("❄️ ${stats.streakFreezeCount}")
                    FreezeButton(
                        enabled = stats.gems >= 200,
                        lang = lang,
                        onClick = onBuyFreeze
                    )
                }

                if (stats.pendingChests > 0) {
                    Text(
                        text = "📦 ${Strings.get("reward_chest", lang)} ×${stats.pendingChests}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF12211C),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFFFF3C4))
                            .clickable { onOpenChests() }
                            .padding(vertical = 10.dp, horizontal = 12.dp)
                    )
                }
            }
        }

        // ---- Titre de section ----
        Column {
            Text(
                text = Strings.get("path_title", lang),
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF12211C)
            )
            Text(
                text = Strings.get("path_sub", lang),
                fontSize = 12.sp,
                color = Color(0xFF6B7877)
            )
        }

        // ---- 15 unités ----
        units.forEach { unit ->
            UnitCard(
                unit = unit,
                lang = lang,
                onOpenLesson = onOpenLesson,
                onShowTip = { tipVisible = true }
            )
        }

        if (tipVisible) {
            LockedTip(lang = lang, onDismiss = { tipVisible = false })
        }
    }
}

@Composable
private fun StreakPill(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFEA580C),
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFFFF7ED))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    )
}

@Composable
private fun FreezeButton(enabled: Boolean, lang: Language, onClick: () -> Unit) {
    Text(
        text = "❄️ ${Strings.get("freeze_buy", lang)} · $ 💎",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = if (enabled) Color(0xFF0284C7) else Color(0xFF6B7877),
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (enabled) Color(0xFFE0F2FE) else Color(0xFFF5FAF7))
            .clickable(enabled = enabled) { onClick() }
            .padding(horizontal = 10.dp, vertical = 6.dp)
    )
}

@Composable
private fun UnitCard(
    unit: PathBuilder.LessonPathUnit,
    lang: Language,
    onOpenLesson: (String, Int) -> Unit,
    onShowTip: () -> Unit
) {
    val pct = 0

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            // Bannière module
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF58CC02))
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = "📘", fontSize = 22.sp)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = unit.module.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${unit.chapters.size} chapitres",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }
                Text(
                    text = "👑",
                    fontSize = 20.sp
                )
            }

            // Chapitres
            unit.chapters.forEach { chapter ->
                ChapterMini(
                    chapter = chapter,
                    onOpenLesson = onOpenLesson,
                    onShowTip = onShowTip
                )
            }
        }
    }
}

@Composable
private fun ChapterMini(
    chapter: PathBuilder.PathChapter,
    onOpenLesson: (String, Int) -> Unit,
    onShowTip: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = if (expanded) Color(0xFFE8F5E9) else Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = chapter.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF12211C)
                )
                Text(
                    text = "${chapter.nodes.size} leçons",
                    fontSize = 11.sp,
                    color = Color(0xFF90A4AE)
                )
            }
            if (expanded) {
                Text(
                    text = "▶",
                    fontSize = 16.sp,
                    modifier = Modifier.clickable { expanded = false }
                )
            } else {
                Box(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun LessonNode(
    node: PathBuilder.LessonNode,
    onOpenLesson: (String, Int) -> Unit,
    onShowTip: () -> Unit
) {
    val state = node.state
    val done = state == NodeState.DONE
    val locked = state == NodeState.LOCKED
    val face = when {
        done -> "✅"
        locked -> "🔒"
        else -> "⭐"
    }
    val meta = when {
        done -> "${node.score}%"
        locked -> ""
        else -> "L${node.levelIndex + 1}"
    }
    val bg = when {
        done -> Color(0xFF58CC02)
        locked -> Color(0xFFCFD8DC)
        else -> Color(0xFF1CB0F6)
    }
    val pulseAlpha = 1f

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = if ((node.levelIndex) % 2 == 0) 0.dp else 56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .alpha(pulseAlpha)
                .clip(CircleShape)
                .background(bg)
                .clickable {
                    if (locked) onShowTip() else onOpenLesson(node.chapterId, node.levelIndex)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = face, fontSize = 22.sp)
        }
        if (meta.isNotEmpty()) {
            Text(
                text = meta,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = if (locked) Color(0xFF6B7877) else Color(0xFF455A64)
            )
        }
    }
}

@Composable
private fun LockedTip(lang: Language, onDismiss: () -> Unit) {
    Popup(
        onDismissRequest = onDismiss,
        alignment = Alignment.Center
    ) {
        Text(
            text = "🔒 ${Strings.get("path_locked_text", lang)}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(32.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFF37474F))
                .padding(horizontal = 20.dp, vertical = 14.dp)
                .clickable { onDismiss() }
        )
    }
}

private fun moduleTitle(info: InitialData.ModuleInfo, lang: Language): String = when (lang) {
    Language.ARABIC -> info.titleAr
    Language.ENGLISH -> info.titleEn
    Language.FRENCH -> info.titleFr
}

private fun moduleIcon(moduleId: String): String =
    InitialData.modulesList.firstOrNull { it.id == moduleId }?.icon ?: "📘"
