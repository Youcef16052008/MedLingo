package com.aistudio.medlingua.dzmed.ui.screens

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.medlingua.dzmed.domain.explore.ExploreMode
import com.aistudio.medlingua.dzmed.domain.explore.TermPoolBuilder
import com.aistudio.medlingua.dzmed.domain.writing.WritingCorrector
import com.aistudio.medlingua.dzmed.localization.Language
import com.aistudio.medlingua.dzmed.localization.Strings
import com.aistudio.medlingua.dzmed.ui.components.RatingButtons
import com.aistudio.medlingua.dzmed.ui.theme.MedGreenDark
import com.aistudio.medlingua.dzmed.ui.theme.MedGreenPrimary
import com.aistudio.medlingua.dzmed.ui.viewmodel.UiState

/** LibellÃ© traduit d'un mode d'exploration (partagÃ© avec l'onglet Modules). */
fun exploreModeLabel(mode: ExploreMode, lang: Language): String = when (mode) {
    ExploreMode.ALL -> Strings.get("explore_mode_all", lang)
    ExploreMode.RANDOM -> Strings.get("explore_mode_random", lang)
    ExploreMode.DONE -> Strings.get("explore_mode_done", lang)
    ExploreMode.TO_REVIEW -> Strings.get("explore_mode_review", lang)
}

private fun verdictColor(verdict: WritingCorrector.Verdict): Color = when (verdict) {
    WritingCorrector.Verdict.EXACT -> Color(0xFF15803D)
    WritingCorrector.Verdict.ACCENT -> Color(0xFF0D9488)
    WritingCorrector.Verdict.NEAR -> Color(0xFFF59E0B)
    WritingCorrector.Verdict.WRONG -> Color(0xFFDC2626)
    WritingCorrector.Verdict.EMPTY -> Color(0xFF6B7877)
}

private fun verdictLabel(verdict: WritingCorrector.Verdict, lang: Language): String = when (verdict) {
    WritingCorrector.Verdict.EXACT -> Strings.get("explore_verdict_exact", lang)
    WritingCorrector.Verdict.ACCENT -> Strings.get("explore_verdict_accent", lang)
    WritingCorrector.Verdict.NEAR -> Strings.get("explore_verdict_near", lang)
    WritingCorrector.Verdict.WRONG -> Strings.get("explore_verdict_wrong", lang)
    WritingCorrector.Verdict.EMPTY -> Strings.get("explore_verdict_empty", lang)
}

private fun issueLabel(issue: WritingCorrector.Issue, lang: Language): String {
    val base = when (issue.kind) {
        WritingCorrector.IssueKind.EMPTY -> Strings.get("explore_issue_empty", lang)
        WritingCorrector.IssueKind.ACCENT -> Strings.get("explore_issue_accent", lang)
        WritingCorrector.IssueKind.CASE -> Strings.get("explore_issue_case", lang)
        WritingCorrector.IssueKind.ARTICLE -> Strings.get("explore_issue_article", lang)
        WritingCorrector.IssueKind.PLURAL -> Strings.get("explore_issue_plural", lang)
        WritingCorrector.IssueKind.HYPHEN -> Strings.get("explore_issue_hyphen", lang)
        WritingCorrector.IssueKind.MISSING_LETTER -> Strings.get("explore_issue_missing", lang)
        WritingCorrector.IssueKind.EXTRA_LETTER -> Strings.get("explore_issue_extra", lang)
        WritingCorrector.IssueKind.SUBSTITUTED_LETTER -> Strings.get("explore_issue_substituted", lang)
        WritingCorrector.IssueKind.WORD_ORDER -> Strings.get("explore_issue_word_order", lang)
        WritingCorrector.IssueKind.WRONG_LANGUAGE -> Strings.get("explore_issue_wrong_language", lang)
        WritingCorrector.IssueKind.MEANING -> Strings.get("explore_issue_meaning", lang)
    }
    return if (issue.detail.isBlank()) base else "$base (${issue.detail})"
}

/**
 * Session d'exploration plein Ã©cran.
 *
 * L'Ã©lÃ¨ve **Ã©crit** le terme anglais (ou le **dicte** au micro) avant de
 * retourner la carte ; [WritingCorrector] juge le rappel (exact, accent,
 * presque, faux) et propose une qualitÃ© SM-2. La note finale reste manuelle
 * pour ne pas voler la dÃ©cision Ã  l'Ã©lÃ¨ve.
 */
@Composable
fun ExploreScreen(
    uiState: UiState,
    onAnswerChange: (String) -> Unit,
    onCheck: () -> Unit,
    onReveal: () -> Unit,
    onRate: (Int) -> Unit,
    onRestart: () -> Unit,
    onExit: () -> Unit,
    onSpeak: (String) -> Unit,
    onVoiceResult: (String) -> Unit,
    onVoiceStatus: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val lang = uiState.currentLanguage
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current

    val total = uiState.exploreTerms.size
    val term = uiState.exploreTerms.getOrNull(uiState.exploreIndex)
    val answered = uiState.exploreAnswered
    val average = if (answered > 0) uiState.exploreScoreSum / answered else 0

    val recognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val heard = result.data
                ?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                ?.firstOrNull()
                ?.trim()
            if (!heard.isNullOrEmpty()) {
                onVoiceResult(heard)
                onVoiceStatus(null)
            } else {
                onVoiceStatus(Strings.get("explore_voice_nothing", lang))
            }
        } else {
            onVoiceStatus(null)
        }
    }

    fun launchRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
            putExtra(RecognizerIntent.EXTRA_PROMPT, Strings.get("explore_voice_prompt", lang))
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        }
        val available = SpeechRecognizer.isRecognitionAvailable(context) &&
            intent.resolveActivity(context.packageManager) != null
        if (!available) {
            // Ã‰mulateur sans moteur de reconnaissance : on ne bloque jamais la
            // carte, l'Ã©lÃ¨ve Ã©crit â€” message localisÃ©, pas de crash.
            onVoiceStatus(Strings.get("explore_voice_unavailable", lang))
            return
        }
        onVoiceStatus(Strings.get("explore_voice_listening", lang))
        runCatching { recognizerLauncher.launch(intent) }
            .onFailure { onVoiceStatus(Strings.get("explore_voice_unavailable", lang)) }
    }

    val micPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) launchRecognizer()
        else onVoiceStatus(Strings.get("explore_voice_denied", lang))
    }

    fun startVoice() {
        val granted = context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) ==
            PackageManager.PERMISSION_GRANTED
        if (granted) launchRecognizer() else micPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF0F7F4))
            .testTag("explore_screen")
    ) {
        // ---------------- En-tÃªte ----------------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onExit,
                modifier = Modifier.testTag("explore_close_btn")
            ) {
                Icon(Icons.Default.Close, contentDescription = Strings.get("explore_close", lang))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = Strings.get("explore_title", lang),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = Color(0xFF0C1414)
                )
                Text(
                    text = exploreModeLabel(uiState.exploreMode, lang) +
                        if (uiState.exploreModules.isEmpty()) ""
                        else " â€¢ ${uiState.exploreModules.size} " + Strings.get("explore_modules_short", lang),
                    fontSize = 11.sp,
                    color = Color(0xFF6B7877)
                )
            }
            Text(
                text = if (total > 0) "${(uiState.exploreIndex + 1).coerceAtMost(total)}/$total" else "0/0",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = MedGreenDark,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .testTag("explore_progress")
            )
        }

        LinearProgressIndicator(
            progress = { if (total > 0) (uiState.exploreIndex.toFloat() / total) else 0f },
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp),
            color = MedGreenPrimary,
            trackColor = Color(0xFFE3EFE9)
        )

        if (total == 0) {
            EmptyExplore(lang = lang, onExit = onExit)
        } else if (uiState.exploreFinished) {
            ExploreSummary(
                lang = lang,
                answered = answered,
                correct = uiState.exploreCorrect,
                average = average,
                total = total,
                onRestart = onRestart,
                onExit = onExit
            )
        } else {
            // ---------------- Carte courante ----------------
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (term != null) {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("explore_prompt_card")
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color(0xFFE3F5EC))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = term.module,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MedGreenDark
                                    )
                                }
                                Text(
                                    text = term.chapter,
                                    fontSize = 10.sp,
                                    color = Color(0xFF6B7877),
                                    textAlign = TextAlign.End
                                )
                            }

                            Text(
                                text = Strings.get("explore_term_fr", lang),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6B7877)
                            )
                            Text(
                                text = term.termFr,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF0C1414),
                                modifier = Modifier.testTag("explore_prompt_term")
                            )
                            if (term.definitionFr.isNotBlank()) {
                                Text(
                                    text = term.definitionFr,
                                    fontSize = 12.sp,
                                    color = Color(0xFF475050),
                                    lineHeight = 17.sp
                                )
                            }
                            Text(
                                text = Strings.get("explore_write_hint", lang),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MedGreenPrimary
                            )
                        }
                    }

                    OutlinedTextField(
                        value = uiState.exploreAnswer,
                        onValueChange = onAnswerChange,
                        enabled = !uiState.exploreRevealed,
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = Strings.get("explore_write_placeholder", lang),
                                fontSize = 13.sp,
                                color = Color(0xFF9AA5A4)
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            autoCorrect = false,
                            capitalization = KeyboardCapitalization.None
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboard?.hide()
                                if (!uiState.exploreRevealed) onCheck()
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("explore_answer_input")
                    )

                    uiState.exploreVoiceStatus?.let { status ->
                        Text(
                            text = status,
                            fontSize = 11.sp,
                            color = Color(0xFF0284C7),
                            modifier = Modifier.testTag("explore_voice_status")
                        )
                    }

                    if (!uiState.exploreRevealed) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { startVoice() },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("explore_voice_btn")
                            ) {
                                Icon(
                                    Icons.Default.Mic,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = Strings.get("explore_voice", lang),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            OutlinedButton(
                                onClick = {
                                    keyboard?.hide()
                                    onReveal()
                                },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("explore_reveal_btn")
                            ) {
                                Text(
                                    text = Strings.get("explore_reveal", lang),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Button(
                            onClick = {
                                keyboard?.hide()
                                onCheck()
                            },
                            enabled = uiState.exploreAnswer.isNotBlank(),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MedGreenPrimary),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("explore_check_btn")
                        ) {
                            Text(
                                text = Strings.get("explore_check", lang),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        VerdictCard(
                            uiState = uiState,
                            termEn = term.termEn,
                            lang = lang,
                            onSpeak = onSpeak
                        )

                        Text(
                            text = Strings.get("explore_rate", lang),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF475050)
                        )
                        RatingButtons(
                            onRated = onRate,
                            currentLanguage = lang,
                            modifier = Modifier.testTag("explore_rating_buttons")
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun VerdictCard(
    uiState: UiState,
    termEn: String,
    lang: Language,
    onSpeak: (String) -> Unit
) {
    val verdict = uiState.exploreVerdict
    val accent = verdict?.let { verdictColor(it.verdict) } ?: MedGreenDark

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = accent.copy(alpha = 0.08f)),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, accent.copy(alpha = 0.35f), RoundedCornerShape(16.dp))
            .testTag("explore_verdict_card")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = verdict?.let { verdictLabel(it.verdict, lang) }
                        ?: Strings.get("explore_verdict_revealed", lang),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    color = accent,
                    modifier = Modifier.testTag("explore_verdict_label")
                )
                verdict?.let {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(accent)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "${it.score}/100",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.testTag("explore_verdict_score")
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = Strings.get("explore_expected", lang),
                        fontSize = 10.sp,
                        color = Color(0xFF6B7877),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = termEn,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0C1414),
                        modifier = Modifier.testTag("explore_expected_term")
                    )
                }
                IconButton(onClick = { onSpeak(termEn) }) {
                    Icon(
                        Icons.Default.VolumeUp,
                        contentDescription = Strings.get("listen_pronunciation", lang),
                        tint = MedGreenDark
                    )
                }
            }

            if (verdict != null && verdict.verdict != WritingCorrector.Verdict.EXACT) {
                Text(
                    text = buildAnnotatedString {
                        WritingCorrector.diff(uiState.exploreAnswer, termEn).forEach { part ->
                            when (part.kind) {
                                WritingCorrector.DiffPart.Kind.SAME ->
                                    withStyle(SpanStyle(color = Color(0xFF15803D))) { append(part.text) }
                                WritingCorrector.DiffPart.Kind.MISSING ->
                                    withStyle(
                                        SpanStyle(
                                            color = Color(0xFF15803D),
                                            fontWeight = FontWeight.Bold,
                                            textDecoration = TextDecoration.Underline
                                        )
                                    ) { append(part.text) }
                                WritingCorrector.DiffPart.Kind.EXTRA ->
                                    withStyle(
                                        SpanStyle(
                                            color = Color(0xFFDC2626),
                                            textDecoration = TextDecoration.LineThrough
                                        )
                                    ) { append(part.text) }
                                WritingCorrector.DiffPart.Kind.CHANGED ->
                                    withStyle(
                                        SpanStyle(color = Color(0xFFF59E0B), fontWeight = FontWeight.Bold)
                                    ) { append(part.text) }
                            }
                        }
                    },
                    fontSize = 14.sp,
                    modifier = Modifier.testTag("explore_diff")
                )
            }

            val issues = verdict?.issues.orEmpty()
            if (issues.isNotEmpty()) {
                Text(
                    text = Strings.get("explore_issues", lang),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6B7877)
                )
                issues.forEach { issue ->
                    Text(
                        text = "â€¢ " + issueLabel(issue, lang),
                        fontSize = 12.sp,
                        color = Color(0xFF475050)
                    )
                }
            }

            verdict?.let {
                Text(
                    text = Strings.get("explore_suggested_quality", lang)
                        .replace("{q}", it.quality.toString()),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = accent
                )
            }
        }
    }
}

@Composable
private fun EmptyExplore(lang: Language, onExit: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "ðŸ—‚ï¸", fontSize = 40.sp)
        Spacer(Modifier.height(10.dp))
        Text(
            text = Strings.get("explore_empty_title", lang),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color(0xFF0C1414),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = Strings.get("explore_empty_hint", lang),
            fontSize = 12.sp,
            color = Color(0xFF6B7877),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(18.dp))
        Button(
            onClick = onExit,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MedGreenPrimary),
            modifier = Modifier.testTag("explore_empty_exit_btn")
        ) {
            Text(Strings.get("explore_back", lang), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun ExploreSummary(
    lang: Language,
    answered: Int,
    correct: Int,
    average: Int,
    total: Int,
    onRestart: () -> Unit,
    onExit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(Modifier.height(12.dp))
        Text(text = if (average >= 80) "ðŸ†" else "ðŸ“˜", fontSize = 46.sp)
        Text(
            text = Strings.get("explore_summary_title", lang),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = Color(0xFF0C1414),
            modifier = Modifier.testTag("explore_summary_title")
        )
        Text(
            text = Strings.get("explore_summary_sub", lang).replace("{n}", total.toString()),
            fontSize = 12.sp,
            color = Color(0xFF6B7877),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SummaryStat(
                label = Strings.get("explore_summary_average", lang),
                value = "$average",
                tint = if (average >= 80) Color(0xFF15803D) else Color(0xFFF59E0B),
                modifier = Modifier.weight(1f)
            )
            SummaryStat(
                label = Strings.get("explore_summary_correct", lang),
                value = "$correct/$answered",
                tint = MedGreenDark,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(4.dp))
        Button(
            onClick = onRestart,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MedGreenPrimary),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("explore_restart_btn")
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text(
                text = Strings.get("explore_summary_restart", lang),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        OutlinedButton(
            onClick = onExit,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .testTag("explore_summary_exit_btn")
        ) {
            Text(
                text = Strings.get("explore_summary_exit", lang),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun SummaryStat(label: String, value: String, tint: Color, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = tint)
            Text(text = label, fontSize = 11.sp, color = Color(0xFF6B7877), textAlign = TextAlign.Center)
        }
    }
}

/** Plafond affichÃ© dans l'UI (rappel : jamais plus de [TermPoolBuilder.MAX_CARDS]). */
@Composable
fun ExploreLimitHint(lang: Language) {
    Text(
        text = Strings.get("explore_max_hint", lang).replace("{n}", TermPoolBuilder.MAX_CARDS.toString()),
        fontSize = 10.sp,
        color = Color(0xFF6B7877)
    )
}
