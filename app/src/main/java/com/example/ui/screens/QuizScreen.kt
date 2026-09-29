package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.learning.LearningLevel
import com.example.localization.Language
import com.example.localization.Strings
import com.example.ui.components.ClinicalContextCard
import com.example.ui.components.MatchingExerciseView
import com.example.ui.components.McqQuestionView
import com.example.ui.components.SentenceOrderExerciseView
import com.example.ui.theme.MedGreenDark
import com.example.ui.viewmodel.UiState

@Composable
fun QuizScreen(
    uiState: UiState,
    onStartLevel: (Int) -> Unit,
    onExitLevel: () -> Unit,
    onSelectLevelOption: (String, String, Int) -> Unit,
    onNextLevelQuestion: () -> Unit,
    onRestartLevel: () -> Unit,
    modifier: Modifier = Modifier,
    onSelectOption: (String, String, Int) -> Unit = onSelectLevelOption,
    onNextQuestion: (Int) -> Unit = { onNextLevelQuestion() },
    onResetQuiz: () -> Unit = onRestartLevel
) {
    val lang = uiState.currentLanguage
    val selectedLevelNum = uiState.selectedLearningLevel

    if (selectedLevelNum == null) {
        // Mode 1: Display the 6 Progressive Learning Pyramid Screen
        LearningPyramidOverviewScreen(
            uiState = uiState,
            onSelectLevel = onStartLevel,
            modifier = modifier
        )
    } else {
        // Intercept back button to return to Pyramid overview
        BackHandler {
            onExitLevel()
        }

        if (uiState.levelIsCompleted) {
            // Mode 2: Level Session Results & Validation Screen
            LevelResultsView(
                uiState = uiState,
                level = LearningLevel.fromNumber(selectedLevelNum),
                onRestart = onRestartLevel,
                onBackToPyramid = onExitLevel,
                currentLanguage = lang,
                modifier = modifier
            )
        } else {
            // Mode 3: Active Training Session for Selected Level
            LevelExerciseSessionView(
                uiState = uiState,
                level = LearningLevel.fromNumber(selectedLevelNum),
                onExit = onExitLevel,
                onSelectOption = onSelectLevelOption,
                onNextQuestion = onNextLevelQuestion,
                currentLanguage = lang,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun LearningPyramidOverviewScreen(
    uiState: UiState,
    onSelectLevel: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val lang = uiState.currentLanguage
    val userStats = uiState.userStats

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8F6))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Visual Pyramid Hero Card
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF0D532C), Color(0xFF1B5E20), Color(0xFF2E7D32))
                        )
                    )
                    .padding(20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = Strings.get("learning_pyramid_title", lang),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = Strings.get("learning_pyramid_desc", lang),
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Ascending Pyramid Tier Indicator
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (lvl in 1..6) {
                            val isUnlocked = userStats.isLevelUnlocked(lvl)
                            val score = userStats.getScoreForLevel(lvl)
                            val levelInfo = LearningLevel.fromNumber(lvl)

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(
                                            when {
                                                score >= 70 -> Color(0xFF4CAF50)
                                                isUnlocked -> Color(0xFFFFD54F)
                                                else -> Color.White.copy(alpha = 0.2f)
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = levelInfo.icon,
                                        fontSize = 16.sp
                                    )
                                }

                                Text(
                                    text = "L$lvl",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                Text(
                                    text = if (isUnlocked) "$score%" else "🔒",
                                    fontSize = 10.sp,
                                    color = if (score >= 70) Color(0xFFA5F4AC) else Color.White.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Level Cards List (Level 1 to 6)
        Text(
            text = if (lang == Language.ARABIC) "مستويات التعلّم الستة :" else if (lang == Language.ENGLISH) "The 6 Progressive Levels:" else "Les 6 Niveaux Progressifs :",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E2922)
        )

        LearningLevel.entries.forEach { level ->
            val isUnlocked = userStats.isLevelUnlocked(level.levelNumber)
            val score = userStats.getScoreForLevel(level.levelNumber)
            val isMastered = score >= 70

            LevelCard(
                level = level,
                isUnlocked = isUnlocked,
                score = score,
                isMastered = isMastered,
                currentLanguage = lang,
                onStart = { onSelectLevel(level.levelNumber) }
            )
        }
    }
}

@Composable
private fun LevelCard(
    level: LearningLevel,
    isUnlocked: Boolean,
    score: Int,
    isMastered: Boolean,
    currentLanguage: Language,
    onStart: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) Color.White else Color(0xFFF1F5F9)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isUnlocked) 3.dp else 0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.5.dp,
                when {
                    isMastered -> Color(0xFF16A34A)
                    isUnlocked -> Color(level.colorHex)
                    else -> Color(0xFFCBD5E1)
                },
                RoundedCornerShape(18.dp)
            )
            .testTag("level_card_${level.levelNumber}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (isUnlocked) Color(level.colorHex).copy(alpha = 0.12f)
                                else Color(0xFFE2E8F0)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = level.icon, fontSize = 22.sp)
                    }

                    Column {
                        Text(
                            text = level.getTitle(currentLanguage),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isUnlocked) Color(0xFF0F172A) else Color(0xFF64748B)
                        )
                        Text(
                            text = level.getObjective(currentLanguage),
                            fontSize = 12.sp,
                            color = if (isUnlocked) Color(0xFF475569) else Color(0xFF94A3B8),
                            maxLines = 2
                        )
                    }
                }
            }

            // Status Badge and Progress
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (isUnlocked) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        if (isMastered) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = Color(0xFF16A34A),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Score: $score% (Validé ≥70%)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF16A34A)
                            )
                        } else {
                            Text(
                                text = "Score actuel : $score% (70% requis)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFFE65100)
                            )
                        }
                    }

                    Button(
                        onClick = onStart,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(level.colorHex)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("start_level_${level.levelNumber}_btn")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = Color.White
                            )
                            Text(
                                text = Strings.get("start_training", currentLanguage),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color(0xFF94A3B8),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = Strings.get("level_locked_info", currentLanguage),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }
            }

            // Progress bar
            if (isUnlocked) {
                LinearProgressIndicator(
                    progress = { (score / 100f).coerceIn(0f, 1f) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = if (isMastered) Color(0xFF16A34A) else Color(level.colorHex),
                    trackColor = Color(0xFFEFF2EF)
                )
            }
        }
    }
}

@Composable
private fun LevelExerciseSessionView(
    uiState: UiState,
    level: LearningLevel,
    onExit: () -> Unit,
    onSelectOption: (String, String, Int) -> Unit,
    onNextQuestion: () -> Unit,
    currentLanguage: Language,
    modifier: Modifier = Modifier
) {
    val exercises = uiState.levelExercises
    val currentIndex = uiState.levelCurrentIndex.coerceIn(0, (exercises.size - 1).coerceAtLeast(0))
    val currentExercise = exercises.getOrNull(currentIndex)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8F6))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Header with Back Button and Level Info + Hearts (Duolingo style)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = onExit) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MedGreenDark
                    )
                }

                Column {
                    Text(
                        text = level.getTitle(currentLanguage),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E2922)
                    )
                    Text(
                        text = "${Strings.get("question", currentLanguage)} ${currentIndex + 1} / ${exercises.size}",
                        fontSize = 12.sp,
                        color = Color(0xFF607D8B)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Hearts - Duolingo Phase 1
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (uiState.currentHearts == 0) Color(0xFFFEE2E2) else Color(0xFFFFF1F2))
                        .border(1.dp, if (uiState.currentHearts == 0) Color(0xFFFECACA) else Color(0xFFFFD1D9), RoundedCornerShape(20.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = if (uiState.userStats.isSuperActive()) "💖" else "❤️", fontSize = 14.sp)
                        Text(
                            text = if (uiState.userStats.isSuperActive()) "∞" else "${uiState.currentHearts}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = if (uiState.currentHearts == 0) Color(0xFFDC2626) else Color(0xFFE11D48)
                        )
                    }
                }

                // Score Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFFFF8E1))
                        .border(1.dp, Color(0xFFFFD54F), RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "⭐", fontSize = 14.sp)
                        Text(
                            text = "${uiState.levelScore} pts",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Color(0xFFE65100)
                        )
                    }
                }
            }
        }

        // Progress Bar
        if (exercises.isNotEmpty()) {
            LinearProgressIndicator(
                progress = { (currentIndex + 1).toFloat() / exercises.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = Color(level.colorHex),
                trackColor = Color(0xFFE0E5E0)
            )
        }

        if (currentExercise != null) {
            // Clinical Context Card (if reading passage or clinical vignette)
            if (currentExercise.contextTextEn.isNotBlank()) {
                ClinicalContextCard(
                    contextTextEn = currentExercise.contextTextEn,
                    contextTextFr = currentExercise.contextTextFr,
                    contextTextAr = currentExercise.contextTextAr,
                    level = level.levelNumber,
                    currentLanguage = currentLanguage
                )
            }

            // Main Question Card
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Difficulty & Points Badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(level.colorHex).copy(alpha = 0.12f))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "${currentExercise.module.uppercase()} • +${currentExercise.points} PTS",
                            color = Color(level.colorHex),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = currentExercise.questionEn,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E2922),
                        textAlign = TextAlign.Center,
                        lineHeight = 23.sp
                    )

                    val translatedQuestion = if (currentLanguage == Language.ARABIC) currentExercise.questionAr else currentExercise.questionFr
                    if (translatedQuestion.isNotBlank()) {
                        Text(
                            text = translatedQuestion,
                            fontSize = 13.sp,
                            color = Color(0xFF64748B),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Exercise View based on Type
            when (currentExercise.type) {
                "sentence_order" -> {
                    SentenceOrderExerciseView(
                        exercise = currentExercise,
                        onAnswerSubmitted = { constructed ->
                            onSelectOption(constructed, currentExercise.correctAnswer, currentExercise.points)
                        },
                        isAnswered = uiState.levelIsAnswered,
                        currentLanguage = currentLanguage
                    )
                }
                "matching" -> {
                    MatchingExerciseView(
                        exercise = currentExercise,
                        onComplete = { isSuccess ->
                            if (isSuccess) {
                                onSelectOption(currentExercise.correctAnswer, currentExercise.correctAnswer, currentExercise.points)
                            }
                        },
                        currentLanguage = currentLanguage
                    )
                }
                else -> {
                    McqQuestionView(
                        exercise = currentExercise,
                        selectedOption = uiState.levelSelectedOption,
                        isAnswered = uiState.levelIsAnswered,
                        onOptionSelected = { option ->
                            onSelectOption(option, currentExercise.correctAnswer, currentExercise.points)
                        },
                        currentLanguage = currentLanguage
                    )
                }
            }

            // Next Question Button
            AnimatedVisibility(visible = uiState.levelIsAnswered) {
                Button(
                    onClick = onNextQuestion,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("level_next_btn"),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(level.colorHex)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = if (currentIndex + 1 < exercises.size) Strings.get("next_question", currentLanguage) else Strings.get("finish_quiz", currentLanguage),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LevelResultsView(
    uiState: UiState,
    level: LearningLevel,
    onRestart: () -> Unit,
    onBackToPyramid: () -> Unit,
    currentLanguage: Language,
    modifier: Modifier = Modifier
) {
    val total = uiState.levelExercises.size
    val correct = uiState.levelCorrectCount
    val percentage = if (total > 0) ((correct.toDouble() / total) * 100).toInt() else 0
    val isPassed = percentage >= 70

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8F6))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(if (isPassed) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isPassed) "🏆" else "⚠️",
                        fontSize = 42.sp
                    )
                }

                Text(
                    text = if (isPassed) Strings.get("congrats_level_passed", currentLanguage) else Strings.get("score_needed_retry", currentLanguage),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isPassed) MedGreenDark else Color(0xFFC62828),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "$percentage% ($correct / $total correct)",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (isPassed) Color(0xFF16A34A) else Color(0xFFE65100)
                )

                if (isPassed) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF0FDF4))
                            .border(1.dp, Color(0xFFBBF7D0), RoundedCornerShape(12.dp))
                            .padding(14.dp)
                    ) {
                        Text(
                            text = Strings.get("next_level_unlocked_msg", currentLanguage),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF166534),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onBackToPyramid,
                    colors = ButtonDefaults.buttonColors(containerColor = MedGreenDark),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("back_to_pyramid_btn")
                ) {
                    Text(
                        text = Strings.get("back_to_pyramid", currentLanguage),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                OutlinedButton(
                    onClick = onRestart,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("retry_level_btn")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Replay, contentDescription = null)
                        Text(
                            text = Strings.get("play_again", currentLanguage),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
