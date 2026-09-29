package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.exercise.ExerciseLanguage
import com.example.domain.exercise.ExerciseSpec
import com.example.domain.exercise.MatchPair
import com.example.localization.Language

/**
 * ExerciseSpecComponents - Duolingo Phase 2
 * Renderers for ExerciseSpec sealed class
 * + Same Exam Swapped Language toggle
 */

@Composable
fun ChoiceSpecView(
    spec: ExerciseSpec.Choice,
    selectedIndex: Int?,
    isAnswered: Boolean,
    currentLang: Language,
    onSelect: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        spec.options.forEachIndexed { index, option ->
            val isSelected = selectedIndex == index
            val isCorrect = index == spec.correctIndex
            val showCorrect = isAnswered && isCorrect
            val showWrong = isAnswered && isSelected && !isCorrect

            val bgColor = when {
                showCorrect -> Color(0xFFDCFCE7)
                showWrong -> Color(0xFFFEE2E2)
                isSelected -> Color(0xFFEFF6FF)
                else -> Color.White
            }
            val borderColor = when {
                showCorrect -> Color(0xFF16A34A)
                showWrong -> Color(0xFFDC2626)
                isSelected -> Color(0xFF3B82F6)
                else -> Color(0xFFE2E8F0)
            }

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = bgColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, borderColor, RoundedCornerShape(14.dp))
                    .clickable(enabled = !isAnswered) { onSelect(index) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                when {
                                    showCorrect -> Color(0xFF16A34A)
                                    showWrong -> Color(0xFFDC2626)
                                    isSelected -> Color(0xFF3B82F6)
                                    else -> Color(0xFFF1F5F9)
                                }
                            )
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "${'A' + index}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = if (isSelected || showCorrect || showWrong) Color.White else Color(0xFF64748B)
                        )
                    }
                    Text(
                        text = option,
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = Color(0xFF0F172A)
                    )
                    if (showCorrect) {
                        Text(text = "✓", fontSize = 16.sp, color = Color(0xFF16A34A), modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.End)
                    }
                    if (showWrong) {
                        Text(text = "✗", fontSize = 16.sp, color = Color(0xFFDC2626), modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.End)
                    }
                }
            }
        }

        if (isAnswered) {
            val explanation = when (currentLang) {
                Language.ARABIC -> spec.explanationAr
                Language.ENGLISH -> spec.explanationEn
                Language.FRENCH -> spec.explanationFr
            }
            if (explanation.isNotBlank()) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF3C7)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = "💡 Explication", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color(0xFF92400E))
                        Text(text = explanation, fontSize = 12.sp, color = Color(0xFF78350F), lineHeight = 18.sp)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordbankSpecView(
    spec: ExerciseSpec.Wordbank,
    constructedWords: List<String>,
    isAnswered: Boolean,
    currentLang: Language,
    onWordTap: (String, Int) -> Unit, // word, index in bank
    onConstructedTap: (Int) -> Unit, // index in constructed
    onCheck: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Constructed sentence area
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (constructedWords.isEmpty()) {
                    Text(
                        text = "Tape les mots ci-dessous pour former la phrase...",
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8),
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                } else {
                    constructedWords.forEachIndexed { idx, word ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFEFF6FF))
                                .border(1.dp, Color(0xFFBFDBFE), RoundedCornerShape(10.dp))
                                .clickable(enabled = !isAnswered) { onConstructedTap(idx) }
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(text = word, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E40AF))
                        }
                    }
                }
            }
        }

        // Bank
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            spec.bank.forEachIndexed { bankIdx, word ->
                // Don't show if already used? For simplicity show all but disabled if used
                val isUsed = constructedWords.count { it == word } >= spec.bank.count { it == word }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isUsed) Color(0xFFF1F5F9) else Color.White)
                        .border(1.dp, if (isUsed) Color(0xFFE2E8F0) else Color(0xFFCBD5E1), RoundedCornerShape(10.dp))
                        .clickable(enabled = !isAnswered && !isUsed) { onWordTap(word, bankIdx) }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = word,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (isUsed) Color(0xFF94A3B8) else Color(0xFF0F172A)
                    )
                }
            }
        }

        if (isAnswered) {
            val isCorrect = constructedWords.joinToString(" ").trim().equals(spec.correctSentence.trim(), ignoreCase = true)
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = if (isCorrect) Color(0xFFDCFCE7) else Color(0xFFFEE2E2)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = if (isCorrect) "✅ Correct!" else "❌ ${spec.correctSentence}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = if (isCorrect) Color(0xFF166534) else Color(0xFF991B1B)
                    )
                    if (!isCorrect) {
                        Text(
                            text = spec.explanationEn,
                            fontSize = 11.sp,
                            color = Color(0xFF7F1D1D),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SameExamSwappedLanguageToggle(
    currentLang: ExerciseLanguage,
    onLangChange: (ExerciseLanguage) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF1F5F9))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ExerciseLanguage.entries.forEach { lang ->
            val isSelected = currentLang == lang
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isSelected) Color.White else Color.Transparent)
                    .border(if (isSelected) 1.dp else 0.dp, if (isSelected) Color(0xFFE2E8F0) else Color.Transparent, RoundedCornerShape(16.dp))
                    .clickable { onLangChange(lang) }
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = when (lang) {
                        ExerciseLanguage.ENGLISH -> "🇺🇸 EN"
                        ExerciseLanguage.FRENCH -> "🇫🇷 FR"
                        ExerciseLanguage.ARABIC -> "🇩🇿 AR"
                    },
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color(0xFF0F172A) else Color(0xFF64748B)
                )
            }
        }
    }
}

@Composable
fun MatchSpecView(
    spec: ExerciseSpec.Match,
    userPairs: Map<String, String>, // left -> right
    isAnswered: Boolean,
    onPair: (String, String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        spec.pairs.forEach { pair ->
            val userRight = userPairs[pair.left]
            val isCorrect = userRight == pair.right
            val bgColor = when {
                !isAnswered -> Color.White
                isCorrect -> Color(0xFFDCFCE7)
                userRight != null -> Color(0xFFFEE2E2)
                else -> Color.White
            }
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = bgColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = pair.left, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Text(text = "→", fontSize = 14.sp, color = Color(0xFF94A3B8))
                    Text(
                        text = userRight ?: "?",
                        fontSize = 13.sp,
                        color = if (isAnswered) {
                            if (isCorrect) Color(0xFF166534) else Color(0xFF991B1B)
                        } else Color(0xFF475569)
                    )
                    if (isAnswered && !isCorrect) {
                        Text(text = "(${pair.right})", fontSize = 11.sp, color = Color(0xFF16A34A), fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
