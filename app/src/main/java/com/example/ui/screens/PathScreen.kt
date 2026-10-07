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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.data.initial.InitialData
import com.example.domain.gamification.FREEZE_COST_GEMS
import com.example.domain.gamification.ProgressionManager
import com.example.domain.path.NodeState
import com.example.domain.path.PathBuilder
import com.example.domain.path.PathModule
import com.example.domain.path.PathNode
import com.example.domain.path.PathUnit
import com.example.localization.Language
import com.example.localization.Strings
import com.example.ui.components.GoalRing
import com.example.ui.components.Medi
import com.example.ui.components.MediMood
import com.example.ui.viewmodel.UiState
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
    modifier: Modifier = Modifier
) {
    val lang = uiState.currentLanguage
    val stats = uiState.userStats
    val now = System.currentTimeMillis()
    val goal = ProgressionManager.goalProgress(stats, now)
    val due = uiState.dueFlashcardProgress.size

    val modules = remember(lang) {
        InitialData.modulesList.map {
            PathModule(it.id, moduleTitle(it, lang), it.colorHex)
        }
    }
    val units = remember(uiState.lessonBest, lang) {
        PathBuilder.buildPath(uiState.lessonBest, modules)
    }

    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val greet = Strings.get(
        when {
            hour < 12 -> "greet_morning"
            hour < 18 -> "greet_afternoon"
            else -> "greet_evening"
        },
        lang
    )
    val next = units.firstNotNullOfOrNull { unit ->
        PathBuilder.nextLesson(unit)?.let { unit to it }
    }
    val mood = when {
        goal.done -> MediMood.CELEBRATING
        stats.streakDays == 0 -> MediMood.MOTIVATING
        else -> MediMood.HAPPY
    }
    var tipVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8F6))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
                        message = next?.let { (unit, node) ->
                            "$greet · ${unit.module.title} L${node.level}"
                        } ?: greet,
                        lang = lang,
                        modifier = Modifier.weight(1f)
                    )
                    GoalRing(pct = goal.pct, done = goal.done)
                }

                Text(
                    text = "${goal.today} / ${goal.goal} ${Strings.get("stat_xp", lang)} · " +
                        "$due ${Strings.get("cards_due", lang)}",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StreakPill("🔥 ${stats.streakDays} ${Strings.get("stat_streak", lang)}")
                    StreakPill("❄️ ${stats.streakFreezeCount}")
                    FreezeButton(
                        enabled = stats.gems >= FREEZE_COST_GEMS,
                        lang = lang,
                        onClick = onBuyFreeze
                    )
                }

                if (stats.pendingChests > 0) {
                    Text(
                        text = "📦 ${Strings.get("reward_chest", lang)} ×${stats.pendingChests}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
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
                color = Color(0xFF1E293B)
            )
            Text(
                text = Strings.get("path_sub", lang),
                fontSize = 12.sp,
                color = Color(0xFF64748B)
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
        text = "❄️ ${Strings.get("freeze_buy", lang)} · $FREEZE_COST_GEMS 💎",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = if (enabled) Color(0xFF0284C7) else Color(0xFF90A4AE),
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (enabled) Color(0xFFE0F2FE) else Color(0xFFF1F5F9))
            .clickable(enabled = enabled) { onClick() }
            .padding(horizontal = 10.dp, vertical = 6.dp)
    )
}

@Composable
private fun UnitCard(
    unit: PathUnit,
    lang: Language,
    onOpenLesson: (String, Int) -> Unit,
    onShowTip: () -> Unit
) {
    val pct = if (unit.nodes.isEmpty()) 0 else (unit.completed * 100) / unit.nodes.size

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
                    .background(Color(unit.module.colorHex))
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = moduleIcon(unit.module.id), fontSize = 22.sp)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = unit.module.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${unit.completed}/${unit.nodes.size} · $pct%",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }
                Text(
                    text = when {
                        pct == 100 -> "👑"
                        pct >= 50 -> "🥇"
                        else -> "📘"
                    },
                    fontSize = 20.sp
                )
            }

            // Chemin en zigzag
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                unit.nodes.forEach { node ->
                    LessonNode(
                        node = node,
                        onOpenLesson = onOpenLesson,
                        onShowTip = onShowTip
                    )
                }
            }
        }
    }
}

/**
 * Nœud du chemin. Il vit dans SA propre scope de recomposition pour que les
 * 90 nœuds ne recomposent jamais l'écran entier (ANR au démarrage sur émulateur).
 * Aucune animation continue : voir plus bas.
 */
@Composable
private fun LessonNode(
    node: PathNode,
    onOpenLesson: (moduleId: String, level: Int) -> Unit,
    onShowTip: () -> Unit
) {
    val state = node.state
    val done = state == NodeState.DONE
    val locked = state == NodeState.LOCKED
    val face = when {
        node.isBoss -> "👑"
        done -> "✅"
        locked -> "🔒"
        else -> "⭐"
    }
    val meta = when {
        node.isBoss -> "BOSS"
        done -> "${node.score}%"
        locked -> ""
        else -> "L${node.level}"
    }
    val bg = when {
        done && node.score >= 90 -> Color(0xFFFFC800)
        done && node.score >= 80 -> Color(0xFFB0BEC5)
        done -> Color(0xFF58CC02)
        locked -> Color(0xFFCFD8DC)
        else -> Color(0xFF1CB0F6)
    }
    // Pas d'animation continue : sur émulateur chargé, toute frame perpétuelle
    // (pulse) mettait le renderer en défaillance → ANR. Le nœud suivant est
    // simplement mis en avant par sa couleur/bordure.
    val pulseAlpha = 1f

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = if ((node.level - 1) % 2 == 0) 0.dp else 56.dp),
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
                    if (locked) onShowTip() else onOpenLesson(node.moduleId, node.level)
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
                color = if (locked) Color(0xFF90A4AE) else Color(0xFF455A64)
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
