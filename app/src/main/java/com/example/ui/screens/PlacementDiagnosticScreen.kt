package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.initial.InitialData
import com.example.domain.diagnostic.AdaptivePlacementEngine
import com.example.domain.diagnostic.KnowledgeLevel
import com.example.domain.diagnostic.MedYear
import com.example.domain.diagnostic.PlacementQuestion
import com.example.domain.diagnostic.PlacementResult
import com.example.domain.diagnostic.UserType
import kotlinx.coroutines.delay

@Composable
fun PlacementDiagnosticScreen(
    onCompleteDiagnostic: (PlacementResult) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 0 = Profiling Nom & Statut, 1 = Test Adaptatif CAT, 2 = Résultat Diagnostic
    var currentPhase by remember { mutableIntStateOf(0) }

    // Profiling State
    var userName by remember { mutableStateOf("Étudiant") }
    var selectedUserType by remember { mutableStateOf(UserType.MED_STUDENT) }
    var selectedMedYear by remember { mutableStateOf<MedYear?>(MedYear.YEAR_1) }
    var selectedSpecialty by remember { mutableStateOf("") }

    // Diagnostic Engine & Questions State
    val engine = remember { AdaptivePlacementEngine() }
    var questionPool by remember { mutableStateOf(AdaptivePlacementEngine.QuestionBank.questions) }
    val usedQuestionIds = remember { mutableStateListOf<Int>() }
    var currentQuestion by remember { mutableStateOf<PlacementQuestion?>(null) }
    var questionCount by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isQuestionAnswered by remember { mutableStateOf(false) }
    var placementResult by remember { mutableStateOf<PlacementResult?>(null) }

    // Function to pick next adaptive question
    fun loadNextQuestion() {
        if (engine.shouldStop()) {
            val result = engine.calculateResult(
                name = userName.ifBlank { "Étudiant" },
                userType = selectedUserType,
                medYear = selectedMedYear,
                specialty = selectedSpecialty.ifBlank { null }
            )
            placementResult = result
            currentPhase = 2
            return
        }

        val targetDifficulty = engine.getCurrentDifficulty()
        val available = questionPool.filter { it.id !in usedQuestionIds }
        val matching = available.filter { it.difficulty == targetDifficulty }
        val nextQ = if (matching.isNotEmpty()) {
            matching.random()
        } else if (available.isNotEmpty()) {
            available.minByOrNull { kotlin.math.abs(it.difficulty - targetDifficulty) } ?: available.first()
        } else {
            // Pool exhausted
            val result = engine.calculateResult(
                name = userName.ifBlank { "Étudiant" },
                userType = selectedUserType,
                medYear = selectedMedYear,
                specialty = selectedSpecialty.ifBlank { null }
            )
            placementResult = result
            currentPhase = 2
            return
        }

        usedQuestionIds.add(nextQ.id)
        currentQuestion = nextQ
        questionCount++
        selectedOptionIndex = null
        isQuestionAnswered = false
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {
        when (currentPhase) {
            0 -> {
                ProfilingStep(
                    userName = userName,
                    onNameChange = { userName = it },
                    selectedUserType = selectedUserType,
                    onUserTypeChange = { selectedUserType = it },
                    selectedMedYear = selectedMedYear,
                    onMedYearChange = { selectedMedYear = it },
                    selectedSpecialty = selectedSpecialty,
                    onSpecialtyChange = { selectedSpecialty = it },
                    onStartTest = {
                        val initDiff = AdaptivePlacementEngine.getInitialDifficulty(selectedUserType, selectedMedYear)
                        engine.startTest(initDiff)
                        usedQuestionIds.clear()
                        questionCount = 0
                        currentPhase = 1
                        loadNextQuestion()
                    },
                    onDismiss = onDismiss
                )
            }
            1 -> {
                AdaptiveTestStep(
                    question = currentQuestion,
                    questionNumber = questionCount,
                    totalEstimated = AdaptivePlacementEngine.MAX_QUESTIONS,
                    currentDifficulty = engine.getCurrentDifficulty(),
                    selectedOption = selectedOptionIndex,
                    isAnswered = isQuestionAnswered,
                    onSelectOption = { idx ->
                        if (!isQuestionAnswered && currentQuestion != null) {
                            selectedOptionIndex = idx
                            isQuestionAnswered = true
                            val correct = idx == currentQuestion!!.correctIndex
                            engine.recordAnswer(correct, currentQuestion!!.difficulty)
                        }
                    },
                    onNext = {
                        loadNextQuestion()
                    },
                    onCancel = {
                        currentPhase = 0
                    }
                )
            }
            2 -> {
                placementResult?.let { res ->
                    DiagnosticResultStep(
                        result = res,
                        onContinue = {
                            onCompleteDiagnostic(res)
                        }
                    )
                }
            }
        }
    }
}

// =========================================================================
// ÉTAPE 1 : PROFILING UTILISATEUR
// =========================================================================

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProfilingStep(
    userName: String,
    onNameChange: (String) -> Unit,
    selectedUserType: UserType,
    onUserTypeChange: (UserType) -> Unit,
    selectedMedYear: MedYear?,
    onMedYearChange: (MedYear?) -> Unit,
    selectedSpecialty: String,
    onSpecialtyChange: (String) -> Unit,
    onStartTest: () -> Unit,
    onDismiss: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            // Header with cancel button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE2E8F0))
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Fermer",
                        tint = Color(0xFF475569),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFF00695C).copy(alpha = 0.12f)
                ) {
                    Text(
                        text = "ÉVALUATION ADAPTATIVE (CAT)",
                        color = Color(0xFF00695C),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
            }
        }

        item {
            // Title & Welcome
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Votre Profil Médical 🎯",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0F172A)
                )
                Text(
                    text = "Pour calibrer l'algorithme et démarrer exactement au niveau correspondant à votre parcours.",
                    fontSize = 14.sp,
                    color = Color(0xFF64748B),
                    lineHeight = 20.sp
                )
            }
        }

        item {
            // Name input
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Comment vous appelez-vous ?",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1E293B)
                    )
                    OutlinedTextField(
                        value = userName,
                        onValueChange = onNameChange,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Ex: Dr. Youcef, Sarah...") },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF00897B))
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF00897B),
                            unfocusedBorderColor = Color(0xFFCBD5E1)
                        ),
                        singleLine = true
                    )
                }
            }
        }

        item {
            // User Type Selection
            Text(
                text = "Quel est votre statut ?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )

            Column(
                modifier = Modifier.padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                UserType.values().forEach { type ->
                    val isSelected = selectedUserType == type
                    val borderColor by animateColorAsState(
                        if (isSelected) Color(0xFF00897B) else Color(0xFFE2E8F0),
                        label = "borderColor"
                    )
                    val bgColor by animateColorAsState(
                        if (isSelected) Color(0xFF00897B).copy(alpha = 0.08f) else Color.White,
                        label = "bgColor"
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onUserTypeChange(type) }
                            .border(1.5.dp, borderColor, RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = bgColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(text = type.icon, fontSize = 28.sp)
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = type.labelFr,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) Color(0xFF00695C) else Color(0xFF1E293B)
                                )
                                Text(
                                    text = type.descFr,
                                    fontSize = 12.sp,
                                    color = Color(0xFF64748B)
                                )
                            }
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = Color(0xFF00897B),
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        if (selectedUserType == UserType.MED_STUDENT) {
            item {
                Text(
                    text = "En quelle année d'études êtes-vous ?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MedYear.values().forEach { year ->
                        val isSelected = selectedMedYear == year
                        Surface(
                            modifier = Modifier.clickable { onMedYearChange(year) },
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) Color(0xFF00695C) else Color.White,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) Color(0xFF004D40) else Color(0xFFCBD5E1)
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(year.icon, fontSize = 14.sp)
                                Text(
                                    text = year.labelFr,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else Color(0xFF334155)
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            // Action button
            Button(
                onClick = onStartTest,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("start_adaptive_test_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00695C))
            ) {
                Text(
                    text = "Démarrer le Test Adaptatif 🎯",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

// =========================================================================
// ÉTAPE 2 : TEST ADAPTATIF (CAT)
// =========================================================================

@Composable
private fun AdaptiveTestStep(
    question: PlacementQuestion?,
    questionNumber: Int,
    totalEstimated: Int,
    currentDifficulty: Int,
    selectedOption: Int?,
    isAnswered: Boolean,
    onSelectOption: (Int) -> Unit,
    onNext: () -> Unit,
    onCancel: () -> Unit
) {
    if (question == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFF00897B))
        }
        return
    }

    LaunchedEffect(isAnswered) {
        if (isAnswered) {
            delay(1600)
            onNext()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Progress & Top Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onCancel,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Retour", tint = Color(0xFF64748B))
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "QUESTION $questionNumber / $totalEstimated",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF64748B),
                    letterSpacing = 1.sp
                )
                // Difficulty Stars
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..5) {
                        Icon(
                            imageVector = if (i <= currentDifficulty) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = null,
                            tint = if (i <= currentDifficulty) Color(0xFFF59E0B) else Color(0xFFCBD5E1),
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Text(
                        text = " Diff. $currentDifficulty",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFF59E0B)
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF00695C).copy(alpha = 0.1f)
            ) {
                Text(
                    text = question.module,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00695C),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }

        LinearProgressIndicator(
            progress = { (questionNumber.toFloat() / totalEstimated.toFloat()).coerceIn(0f, 1f) },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = Color(0xFF00897B),
            trackColor = Color(0xFFE2E8F0)
        )

        // Trilingual Focus Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Que signifie ce terme médical ?",
                    fontSize = 12.sp,
                    color = Color(0xFF94A3B8),
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = question.termEn,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🇫🇷 ${question.termFr}",
                        fontSize = 12.sp,
                        color = Color(0xFFE2E8F0)
                    )
                    Text(
                        text = "•",
                        fontSize = 12.sp,
                        color = Color(0xFF64748B)
                    )
                    Text(
                        text = "🇸🇦 ${question.termAr}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFDE68A)
                    )
                }
            }
        }

        // Options List
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(question.options.size) { index ->
                val optionText = question.options[index]
                val isSelected = selectedOption == index
                val isCorrect = index == question.correctIndex

                val containerColor = when {
                    isAnswered && isCorrect -> Color(0xFF10B981).copy(alpha = 0.15f)
                    isAnswered && isSelected && !isCorrect -> Color(0xFFEF4444).copy(alpha = 0.15f)
                    isSelected -> Color(0xFF00695C).copy(alpha = 0.1f)
                    else -> Color.White
                }

                val borderColor = when {
                    isAnswered && isCorrect -> Color(0xFF10B981)
                    isAnswered && isSelected && !isCorrect -> Color(0xFFEF4444)
                    isSelected -> Color(0xFF00695C)
                    else -> Color(0xFFE2E8F0)
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = !isAnswered) { onSelectOption(index) }
                        .border(1.5.dp, borderColor, RoundedCornerShape(14.dp)),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = containerColor)
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
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(if (isSelected || (isAnswered && isCorrect)) borderColor else Color(0xFFF1F5F9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = listOf("A", "B", "C", "D")[index],
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected || (isAnswered && isCorrect)) Color.White else Color(0xFF64748B)
                            )
                        }

                        Text(
                            text = optionText,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = Color(0xFF1E293B),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Live clinical hint if answered incorrectly
        if (isAnswered) {
            val correct = selectedOption == question.correctIndex
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (correct) Color(0xFFECFDF5) else Color(0xFFFEF2F2),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (correct) Color(0xFF10B981) else Color(0xFFEF4444)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = if (correct) "✅" else "💡", fontSize = 20.sp)
                    Column {
                        Text(
                            text = if (correct) "Excellent !" else "Précision clinique :",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (correct) Color(0xFF065F46) else Color(0xFF991B1B)
                        )
                        Text(
                            text = question.hint,
                            fontSize = 12.sp,
                            color = Color(0xFF334155)
                        )
                    }
                }
            }
        }
    }
}

// =========================================================================
// ÉTAPE 3 : RÉSULTATS & CURRICULUM PERSONNALISÉ
// =========================================================================

@Composable
private fun DiagnosticResultStep(
    result: PlacementResult,
    onContinue: () -> Unit
) {
    val animatedScore by animateFloatAsState(
        targetValue = result.score.toFloat(),
        animationSpec = tween(1200),
        label = "scoreAnim"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = result.level.emoji, fontSize = 64.sp)
            Text(
                text = "Niveau Clinique Détecté",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF64748B)
            )
            Text(
                text = result.level.labelFr,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(result.level.colorHex)
            )
        }

        item {
            // Animated Circular Score Card
            Box(
                modifier = Modifier.size(150.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { animatedScore / 100f },
                    modifier = Modifier.fillMaxSize(),
                    strokeWidth = 10.dp,
                    color = Color(result.level.colorHex),
                    trackColor = Color(0xFFE2E8F0)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${animatedScore.toInt()}",
                        fontSize = 42.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = "Score CAT / 100",
                        fontSize = 11.sp,
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        item {
            // Personalized Feedback message card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(result.level.colorHex).copy(alpha = 0.08f)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(result.level.colorHex).copy(alpha = 0.25f))
            ) {
                Text(
                    text = result.message,
                    fontSize = 14.sp,
                    color = Color(0xFF1E293B),
                    lineHeight = 21.sp,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        item {
            // Metrics row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatBadge(label = "Questions", value = "${result.totalQuestions}", modifier = Modifier.weight(1f))
                StatBadge(label = "Réponses Justes", value = "${result.correctAnswers}", modifier = Modifier.weight(1f))
                StatBadge(label = "Précision", value = "${(result.accuracy * 100).toInt()}%", modifier = Modifier.weight(1f))
            }
        }

        item {
            // Recommended Module Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
                        Text(text = "🚀", fontSize = 20.sp)
                        Text(
                            text = "Module Recommandé pour démarrer :",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A)
                        )
                    }
                    Text(
                        text = "${result.startModuleName} • Programme Officiel",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF00695C)
                    )
                    Text(
                        text = "Votre cursus est configuré pour sauter les modules déjà maîtrisés et vous focaliser sur vos objectifs médicaux réels.",
                        fontSize = 12.sp,
                        color = Color(0xFF64748B)
                    )
                }
            }
        }

        item {
            Button(
                onClick = onContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("apply_diagnostic_result_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(result.level.colorHex))
            ) {
                Text(
                    text = "Accéder à mon Curriculum 🚀",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun StatBadge(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF00695C)
            )
            Text(
                text = label,
                fontSize = 10.sp,
                color = Color(0xFF64748B),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
