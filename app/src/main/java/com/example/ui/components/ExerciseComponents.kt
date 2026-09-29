package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.entity.ExerciseEntity
import com.example.localization.Language
import com.example.localization.Strings
import com.example.ui.theme.MedGreenDark
import com.example.ui.theme.MedGreenPrimary

@Composable
fun McqQuestionView(
    exercise: ExerciseEntity,
    selectedOption: String?,
    isAnswered: Boolean,
    onOptionSelected: (String) -> Unit,
    currentLanguage: Language,
    modifier: Modifier = Modifier
) {
    val options = exercise.getOptionsList()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        options.forEachIndexed { index, option ->
            val isSelected = selectedOption == option
            val isCorrect = option.trim().equals(exercise.correctAnswer.trim(), ignoreCase = true)

            val backgroundColor = when {
                isAnswered && isCorrect -> Color(0xFFF0FDF4)
                isAnswered && isSelected && !isCorrect -> Color(0xFFFEF2F2)
                isSelected -> Color(0xFFF0FDF4)
                else -> Color.White
            }

            val borderColor = when {
                isAnswered && isCorrect -> Color(0xFF16A34A)
                isAnswered && isSelected && !isCorrect -> Color(0xFFDC2626)
                isSelected -> MedGreenDark
                else -> Color(0xFFE2E8F0)
            }

            val letter = ('A' + index).toString()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(backgroundColor)
                    .border(
                        if (isSelected || (isAnswered && isCorrect)) 2.dp else 1.dp,
                        borderColor,
                        RoundedCornerShape(16.dp)
                    )
                    .clickable(enabled = !isAnswered) { onOptionSelected(option) }
                    .padding(16.dp)
                    .testTag("quiz_option_$index")
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isAnswered && isCorrect -> Color(0xFF16A34A)
                                    isAnswered && isSelected && !isCorrect -> Color(0xFFDC2626)
                                    isSelected -> MedGreenDark
                                    else -> Color(0xFFF1F5F9)
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = letter,
                            color = if (isSelected || isAnswered) Color.White else Color(0xFF475569),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    Text(
                        text = option,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF0F172A),
                        modifier = Modifier.weight(1f)
                    )

                    if (isAnswered && isCorrect) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Correct",
                            tint = Color(0xFF16A34A),
                            modifier = Modifier.size(24.dp)
                        )
                    } else if (isAnswered && isSelected && !isCorrect) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Wrong",
                            tint = Color(0xFFDC2626),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

        // Clinical Rationale & Explanation
        ClinicalExplanationCard(
            exercise = exercise,
            isAnswered = isAnswered,
            currentLanguage = currentLanguage
        )
    }
}

@Composable
fun MatchingExerciseView(
    exercise: ExerciseEntity,
    onComplete: (Boolean) -> Unit,
    currentLanguage: Language,
    modifier: Modifier = Modifier
) {
    // Format: "Key:Value|Key:Value"
    val pairs = remember(exercise) {
        exercise.correctAnswer.split("|").mapNotNull {
            val parts = it.split(":")
            if (parts.size == 2) parts[0].trim() to parts[1].trim() else null
        }
    }

    val col1List = remember(pairs) { pairs.map { it.first }.shuffled() }
    val col2List = remember(pairs) { pairs.map { it.second }.shuffled() }

    var selectedFirst by remember { mutableStateOf<String?>(null) }
    var selectedSecond by remember { mutableStateOf<String?>(null) }
    val matchedPairs = remember { mutableStateMapOf<String, String>() }

    fun checkMatch(first: String, second: String) {
        val isCorrect = pairs.any { it.first == first && it.second == second }
        if (isCorrect) {
            matchedPairs[first] = second
            if (matchedPairs.size == pairs.size) {
                onComplete(true)
            }
        }
        selectedFirst = null
        selectedSecond = null
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = Strings.get("match_pairs_instruction", currentLanguage),
            fontSize = 13.sp,
            color = Color(0xFF475569),
            fontWeight = FontWeight.Medium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // First Column
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                col1List.forEach { item ->
                    val isMatched = matchedPairs.containsKey(item)
                    val isSelected = selectedFirst == item

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                when {
                                    isMatched -> Color(0xFFF0FDF4)
                                    isSelected -> Color(0xFFDCFCE7)
                                    else -> Color.White
                                }
                            )
                            .border(
                                1.5.dp,
                                when {
                                    isMatched -> Color(0xFF16A34A)
                                    isSelected -> MedGreenDark
                                    else -> Color(0xFFCBD5E1)
                                },
                                RoundedCornerShape(12.dp)
                            )
                            .clickable(enabled = !isMatched) {
                                selectedFirst = item
                                selectedSecond?.let { s -> checkMatch(item, s) }
                            }
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (isMatched) Color(0xFF166534) else Color(0xFF0F172A)
                        )
                    }
                }
            }

            // Second Column
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                col2List.forEach { item ->
                    val isMatched = matchedPairs.containsValue(item)
                    val isSelected = selectedSecond == item

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                when {
                                    isMatched -> Color(0xFFF0FDF4)
                                    isSelected -> Color(0xFFDCFCE7)
                                    else -> Color.White
                                }
                            )
                            .border(
                                1.5.dp,
                                when {
                                    isMatched -> Color(0xFF16A34A)
                                    isSelected -> MedGreenDark
                                    else -> Color(0xFFCBD5E1)
                                },
                                RoundedCornerShape(12.dp)
                            )
                            .clickable(enabled = !isMatched) {
                                selectedSecond = item
                                selectedFirst?.let { f -> checkMatch(f, item) }
                            }
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (isMatched) Color(0xFF166534) else Color(0xFF0F172A)
                        )
                    }
                }
            }
        }

        ClinicalExplanationCard(
            exercise = exercise,
            isAnswered = matchedPairs.size == pairs.size,
            currentLanguage = currentLanguage
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SentenceOrderExerciseView(
    exercise: ExerciseEntity,
    onAnswerSubmitted: (String) -> Unit,
    isAnswered: Boolean,
    currentLanguage: Language,
    modifier: Modifier = Modifier
) {
    // Correct words in order
    val correctWords = remember(exercise) {
        exercise.correctAnswer.split(" ").filter { it.isNotBlank() }
    }
    // Shuffled pool of tokens
    val availablePool = remember(exercise) {
        val tokens = exercise.optionsRaw.split("|").filter { it.isNotBlank() }
        mutableStateListOf<String>().apply { addAll(tokens.ifEmpty { correctWords.shuffled() }) }
    }
    val selectedWords = remember(exercise) { mutableStateListOf<String>() }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = Strings.get("reorder_instruction", currentLanguage),
            fontSize = 13.sp,
            color = Color(0xFF475569),
            fontWeight = FontWeight.Medium
        )

        // Selected Sentence Drop Zone
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFFF8FAFC))
                .border(1.5.dp, Color(0xFF94A3B8), RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            if (selectedWords.isEmpty()) {
                Text(
                    text = "Tap words below to build the medical sentence...",
                    color = Color(0xFF94A3B8),
                    fontSize = 14.sp
                )
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    selectedWords.forEachIndexed { idx, word ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFE8F5E9))
                                .border(1.dp, MedGreenDark, RoundedCornerShape(8.dp))
                                .clickable(enabled = !isAnswered) {
                                    // Remove word and put back in pool
                                    selectedWords.removeAt(idx)
                                    availablePool.add(word)
                                }
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = word,
                                color = MedGreenDark,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        // Available Pool of Tokens
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            availablePool.forEachIndexed { idx, word ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .border(1.dp, Color(0xFFCBD5E1), RoundedCornerShape(10.dp))
                        .clickable(enabled = !isAnswered) {
                            selectedWords.add(word)
                            availablePool.removeAt(idx)
                        }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = word,
                        color = Color(0xFF1E293B),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }
        }

        // Action Buttons (Verify & Clear)
        if (!isAnswered) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        availablePool.addAll(selectedWords)
                        selectedWords.clear()
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = Strings.get("clear_words", currentLanguage))
                }

                Button(
                    onClick = {
                        val constructed = selectedWords.joinToString(" ")
                        onAnswerSubmitted(constructed)
                    },
                    enabled = selectedWords.isNotEmpty(),
                    modifier = Modifier.weight(1.5f),
                    colors = ButtonDefaults.buttonColors(containerColor = MedGreenDark),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = Strings.get("check_sentence", currentLanguage), color = Color.White)
                }
            }
        }

        ClinicalExplanationCard(
            exercise = exercise,
            isAnswered = isAnswered,
            currentLanguage = currentLanguage
        )
    }
}

@Composable
fun ClinicalContextCard(
    contextTextEn: String,
    contextTextFr: String,
    contextTextAr: String,
    level: Int,
    currentLanguage: Language,
    modifier: Modifier = Modifier
) {
    if (contextTextEn.isBlank()) return

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = if (level == 6) Color(0xFFFFF8E1) else Color(0xFFF0FDF4)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(
                1.dp,
                if (level == 6) Color(0xFFFFD54F) else Color(0xFFA7F3D0),
                RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = if (level == 6) Icons.Default.LocalHospital else Icons.Default.MenuBook,
                    contentDescription = null,
                    tint = if (level == 6) Color(0xFFE65100) else MedGreenDark,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = if (level == 6) Strings.get("clinical_case_title", currentLanguage) else Strings.get("reading_passage_title", currentLanguage),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (level == 6) Color(0xFFE65100) else MedGreenDark
                )
            }

            Text(
                text = contextTextEn,
                fontSize = 13.sp,
                lineHeight = 19.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1E293B)
            )

            val translation = if (currentLanguage == Language.ARABIC) contextTextAr else contextTextFr
            if (translation.isNotBlank()) {
                HorizontalDivider(color = Color(0xFFCBD5E1), thickness = 0.5.dp)
                Text(
                    text = translation,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    color = Color(0xFF64748B)
                )
            }
        }
    }
}

@Composable
fun ClinicalExplanationCard(
    exercise: ExerciseEntity,
    isAnswered: Boolean,
    currentLanguage: Language,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isAnswered,
        enter = fadeIn() + slideInVertically()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF8FAFC))
                .border(1.dp, Color(0xFFCBD5E1), RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocalHospital,
                    contentDescription = null,
                    tint = MedGreenDark,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = Strings.get("clinical_explanation", currentLanguage),
                    color = MedGreenDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }

            Text(
                text = "🇬🇧 ${exercise.explanationEn}",
                color = Color(0xFF1E293B),
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            HorizontalDivider(color = Color(0xFFE2E8F0), thickness = 1.dp)

            Text(
                text = "🇫🇷 ${exercise.explanationFr}",
                color = Color(0xFF475569),
                fontSize = 12.sp,
                lineHeight = 17.sp
            )

            if (exercise.explanationAr.isNotBlank()) {
                Text(
                    text = "🇸🇦 ${exercise.explanationAr}",
                    color = Color(0xFF0F766E),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 19.sp
                )
            }
        }
    }
}
