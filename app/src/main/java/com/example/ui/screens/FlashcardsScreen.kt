package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.entity.MedicalTermEntity
import com.example.localization.Language
import com.example.localization.Strings
import com.example.ui.components.FlashcardCard3D
import com.example.ui.components.RatingButtons
import com.example.ui.theme.MedGreenDark
import com.example.ui.theme.MedGreenPrimary
import com.example.ui.viewmodel.UiState

@Composable
fun FlashcardsScreen(
    uiState: UiState,
    onFlip: () -> Unit,
    onRate: (Int, Int) -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    onSpeak: (String) -> Unit,
    onBookmarkToggle: (MedicalTermEntity) -> Unit,
    onChapterFilterChange: (String) -> Unit = {},
    onModuleFilterChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val lang = uiState.currentLanguage
    val terms = uiState.terms
    val currentIndex = uiState.activeFlashcardIndex.coerceIn(0, (terms.size - 1).coerceAtLeast(0))
    val currentTerm = terms.getOrNull(currentIndex)

    val modulesList = listOf(
        "All" to (if (lang == Language.ARABIC) "الكل 📚" else "Tous les modules 📚"),
        "Anatomie" to "Anatomie 🦴",
        "Biochimie" to "Biochimie 🧬",
        "Biophysique" to "Biophysique 🧪",
        "Histologie" to "Histologie 🔬",
        "Physiologie" to "Physiologie ❤️",
        "Génétique" to "Génétique 🧬",
        "Terminologie Médicale" to "Terminologie 📙",
        "Anglais Médical" to "Anglais Médical 🩺"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8F6))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = Strings.get("nav_flashcards", lang),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E2922)
                )
                Text(
                    text = "Spaced Repetition (SM-2) • Algérie 🇩🇿",
                    fontSize = 11.sp,
                    color = Color(0xFF607D8B),
                    fontWeight = FontWeight.Medium
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE8F5E9))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "${if (terms.isEmpty()) 0 else currentIndex + 1} / ${terms.size}",
                    color = MedGreenDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }

        // Horizontal Module Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            modulesList.forEach { (moduleKey, label) ->
                val isSelected = uiState.selectedModuleFilter == moduleKey
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(if (isSelected) MedGreenDark else Color.White)
                        .border(
                            1.dp,
                            if (isSelected) MedGreenDark else Color(0xFFCBD5E1),
                            RoundedCornerShape(18.dp)
                        )
                        .clickable { onModuleFilterChange(moduleKey) }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .testTag("flashcard_module_chip_$moduleKey")
                ) {
                    Text(
                        text = label,
                        color = if (isSelected) Color.White else Color(0xFF334155),
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        // Dynamic Chapter Filter Chips based on selected module
        // Chapter chips are derived from the seed (InitialData.chaptersOfModule) so a
        // renamed or newly added chapter is always filterable — the previous hardcoded
        // lists had drifted away from the seed data.
        val flashcardChapters: List<Pair<String, String>> = listOf(
            "All" to (if (lang == Language.ARABIC) "الكل" else "Tous")
        ) + com.example.data.initial.InitialData.chaptersOfModule(uiState.selectedModuleFilter).map { it to it }

        if (flashcardChapters.size > 1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                flashcardChapters.forEach { (chapterKey, label) ->
                    val isSelected = uiState.selectedChapterFilter == chapterKey
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) MedGreenDark else Color.White)
                            .border(
                                1.dp,
                                if (isSelected) MedGreenDark else Color(0xFFCBD5E1),
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { onChapterFilterChange(chapterKey) }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = label,
                            color = if (isSelected) Color.White else Color(0xFF334155),
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Progress Bar
        if (terms.isNotEmpty()) {
            LinearProgressIndicator(
                progress = { (currentIndex + 1).toFloat() / terms.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = MedGreenDark,
                trackColor = Color(0xFFE0E5E0)
            )
        }

        // Flashcard 3D Card
        if (currentTerm != null) {
            FlashcardCard3D(
                term = currentTerm,
                isFlipped = uiState.isCardFlipped,
                onFlip = onFlip,
                onSpeak = onSpeak,
                onBookmarkToggle = onBookmarkToggle,
                currentLanguage = lang
            )

            // SM-2 Review Result Feedback banner
            AnimatedVisibility(visible = uiState.lastReviewResult != null) {
                uiState.lastReviewResult?.let { result ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFFE8F5E9))
                            .border(1.dp, Color(0xFFA5D6A7), RoundedCornerShape(14.dp))
                            .padding(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = Color(0xFF2E7D32),
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "${Strings.get("next_review", lang)} ${result.intervalDays} ${Strings.get("days", lang)} (Facteur: ${"%.1f".format(result.easeFactor)})",
                                fontSize = 12.sp,
                                color = Color(0xFF1B5E20),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            // Bottom action: If card flipped, show SM-2 Rating Buttons. If not, show navigation controls.
            if (uiState.isCardFlipped) {
                RatingButtons(
                    onRated = { quality -> onRate(currentTerm.id, quality) },
                    currentLanguage = lang,
                    modifier = Modifier.padding(top = 8.dp)
                )
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedIconButton(
                        onClick = onPrev,
                        modifier = Modifier.size(48.dp).testTag("flashcard_prev_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous",
                            tint = MedGreenDark
                        )
                    }

                    Text(
                        text = Strings.get("tap_to_flip", lang),
                        fontSize = 12.sp,
                        color = Color(0xFF78909C),
                        fontWeight = FontWeight.Medium
                    )

                    FilledIconButton(
                        onClick = onNext,
                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = MedGreenDark),
                        modifier = Modifier.size(48.dp).testTag("flashcard_next_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Next",
                            tint = Color.White
                        )
                    }
                }
            }
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "🎉", fontSize = 36.sp)
                    Text(
                        text = "Toutes les flashcards ont été révisées !",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
