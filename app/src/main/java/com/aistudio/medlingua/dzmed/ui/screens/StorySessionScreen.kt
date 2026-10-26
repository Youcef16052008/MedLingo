package com.aistudio.medlingua.dzmed.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aistudio.medlingua.dzmed.domain.story.Action
import com.aistudio.medlingua.dzmed.domain.story.STORY_STEPS
import com.aistudio.medlingua.dzmed.domain.story.currentDrill
import com.aistudio.medlingua.dzmed.domain.story.currentQuestion
import com.aistudio.medlingua.dzmed.localization.Language
import com.aistudio.medlingua.dzmed.localization.Strings
import com.aistudio.medlingua.dzmed.ui.theme.MedGreenDark
import com.aistudio.medlingua.dzmed.ui.viewmodel.StoryViewModel
import com.aistudio.medlingua.dzmed.ui.viewmodel.StoryViewModelFactory
import androidx.compose.ui.platform.LocalContext

/**
 * Séance « histoire » — boucle du guide §3 : révision → écoute (3 passages) →
 * questions → transformation → reformulation → transfert → bilan.
 * Le contenu ANGLAIS reste LTR même sous interface arabe (RTL) ; aucune avance
 * automatique : chaque étape attend une action de l'étudiant. Le bouton Retour
 * (géré par MainActivity) suspend la séance — déjà persistée — et coupe le TTS.
 */
@Composable
fun StorySessionScreen(
    storyId: String,
    lang: Language,
    onExit: () -> Unit,
    viewModel: StoryViewModel = viewModel(
        factory = StoryViewModelFactory(LocalContext.current)
    )
) {
    val ui by viewModel.state.collectAsState()
    LaunchedEffect(storyId) { viewModel.load(storyId) }

    if (ui.loading) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { CircularProgressIndicator() }
        return
    }
    if (ui.notFound || ui.story == null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(Strings.get("story_not_found", lang), fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Button(onClick = onExit) { Text(Strings.get("nav_path", lang)) }
        }
        return
    }

    // Capture non nulle une fois : `ui` est mutable, pas de smart-cast possible.
    val story = ui.story ?: return
    val session = ui.session ?: return
    val code = lang.code

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F7F4))
    ) {
        // ---- Barre du haut : retour + progression d'étape ----
        val stepIdx = STORY_STEPS.indexOf(session.step).coerceAtLeast(0)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = {
                viewModel.suspendSession()
                onExit()
            }) { Text("✕", fontSize = 18.sp) }
            LinearProgressIndicator(
                progress = { (stepIdx + 1f) / STORY_STEPS.size },
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                color = MedGreenDark
            )
            Text(
                "${stepIdx + 1}/${STORY_STEPS.size}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                pickText(story.title, code),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MedGreenDark
            )
            Text(stepLabel(session.step, lang), fontSize = 13.sp, color = Color(0xFF6B7877))
            // Objectif linguistique — consigne dans la langue d'interface.
            Text("🎯 " + pickText(story.goal, code), fontSize = 12.sp, color = Color(0xFF6B7877))

            when (session.step) {
                "REVIEW" -> ReviewBody(viewModel, ui, lang)
                "LISTEN_DISCOVER", "LISTEN_CHECK" -> ListenBody(
                    viewModel, ui, lang, withHelp = false
                )
                "LISTEN_WITH_HELP" -> ListenBody(viewModel, ui, lang, withHelp = true)
                "CIRCLING" -> QuestionBody(viewModel, ui, lang)
                "PATTERN_PRACTICE" -> DrillBody(viewModel, ui, lang)
                "RETELL" -> ProductionBody(
                    lang = lang,
                    viewModel = viewModel,
                    prompt = pickText(story.retell.prompt, code),
                    starters = story.retell.starters,
                    model = story.retell.model,
                    onDone = { viewModel.dispatch(Action.RetellDone(it)) }
                )
                "TRANSFER_AND_SUMMARY" -> {
                    val q = currentQuestion(session, story)
                    if (q != null) {
                        LtrCard {
                            Text(story.transfer.context.en, fontSize = 15.sp)
                            story.transfer.gloss?.let {
                                Spacer(Modifier.height(4.dp))
                                Text("💡 " + it.en, fontSize = 12.sp, color = Color(0xFF6B7877))
                            }
                        }
                        QuestionBody(viewModel, ui, lang)
                    } else {
                        ProductionBody(
                            lang = lang,
                            viewModel = viewModel,
                            prompt = pickText(story.transfer.task.prompt, code),
                            starters = story.transfer.task.starters,
                            model = story.transfer.task.model,
                            onDone = { viewModel.dispatch(Action.TransferTaskDone(it)) }
                        )
                    }
                }
            }

            // ---- Bilan final ----
            if (session.finished && ui.summary != null) {
                SummaryCard(ui, lang, onExit)
            }
        }
    }
}

@Composable
private fun ReviewBody(viewModel: StoryViewModel, ui: StoryViewModel.UiState, lang: Language) {
    val session = ui.session ?: return
    val story = ui.story ?: return
    val code = lang.code
    val phrase = story.targetPhrases.getOrNull(session.reviewIndex)
        ?: story.targetPhrases.firstOrNull { it.id == session.reviewIds.getOrNull(session.reviewIndex) }
    if (phrase == null) {
        // État incohérent (aucune expression due) : avance, question suivante.
        LaunchedEffect(Unit) { viewModel.dispatch(Action.Advance) }
        return
    }
    LtrCard {
        Text(phrase.en, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(pickHelp(phrase.fr, phrase.ar, code), fontSize = 13.sp, color = Color(0xFF6B7877))
        Spacer(Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = { viewModel.rateReview(phrase.id, true) }) {
                Text("😀 " + Strings.get("story_remember", lang))
            }
            OutlinedButton(onClick = { viewModel.rateReview(phrase.id, false) }) {
                Text("😕 " + Strings.get("story_forgot", lang))
            }
        }
    }
}

@Composable
private fun ListenBody(
    viewModel: StoryViewModel,
    ui: StoryViewModel.UiState,
    lang: Language,
    withHelp: Boolean
) {
    val story = ui.story ?: return
    val code = lang.code
    LtrCard {
        story.segments.forEachIndexed { i, seg ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                TextButton(
                    onClick = { viewModel.speakEnglish(seg.en) },
                    modifier = Modifier.weight(1f)
                ) { Text("▶ ${Strings.get("story_play", lang)} ${i + 1}") }
                TextButton(onClick = { viewModel.speakEnglish(seg.en, slow = true) }) {
                    Text("🐢")
                }
            }
            if (ui.showText || withHelp) {
                Text(seg.en, fontSize = 15.sp)
            }
            if (withHelp) {
                Text(
                    pickHelp(seg.fr, seg.ar, code),
                    fontSize = 12.sp,
                    color = Color(0xFF6B7877)
                )
            }
        }
        if (!withHelp) {
            TextButton(onClick = { viewModel.toggleText() }) {
                Text(
                    Strings.get(
                        if (ui.showText) "story_hide_text" else "story_show_text",
                        lang
                    )
                )
            }
        }
        Button(
            onClick = { viewModel.dispatch(Action.Advance) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MedGreenDark)
        ) { Text(Strings.get("story_continue", lang)) }
    }
}

@Composable
private fun QuestionBody(viewModel: StoryViewModel, ui: StoryViewModel.UiState, lang: Language) {
    val session = ui.session ?: return
    val story = ui.story ?: return
    val q = currentQuestion(session, story) ?: run {
        LaunchedEffect(Unit) { viewModel.dispatch(Action.Advance) }
        return
    }
    val code = lang.code
    LtrCard {
        Text(q.q, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        if (!ui.showHelp && (q.qFr != null || q.qAr != null)) {
            TextButton(onClick = {
                viewModel.askHelp()
                viewModel.dispatch(Action.Help)
            }) { Text("💡 " + Strings.get("story_help", lang)) }
        }
        if (ui.showHelp) {
            Text(
                pickHelp(q.qFr ?: "", q.qAr ?: "", code),
                fontSize = 12.sp,
                color = Color(0xFF6B7877)
            )
        }
        if (!session.revealed) {
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = ui.input,
                onValueChange = { viewModel.setInput(it) },
                label = { Text(Strings.get("story_your_answer", lang)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = { viewModel.dispatch(Action.SubmitAnswer(ui.input)) },
                    enabled = ui.input.isNotBlank()
                ) { Text(Strings.get("story_check", lang)) }
                OutlinedButton(onClick = { viewModel.dispatch(Action.Reveal) }) {
                    Text(Strings.get("story_dont_know", lang))
                }
            }
        } else {
            if (session.lastCorrect == true) {
                Text("✅ " + Strings.get("story_correct", lang), color = Color(0xFF2E7D32))
            }
            if (session.lastCorrect == false && q.hint != null) {
                Text(
                    "⚠️ " + pickText(q.hint, code),
                    color = Color(0xFFC62828),
                    fontSize = 13.sp
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                Strings.get("story_model", lang) + " · " + q.model,
                fontSize = 13.sp,
                color = Color(0xFF6B7877)
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    viewModel.resetForNextQuestion()
                    viewModel.dispatch(Action.Advance)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MedGreenDark)
            ) { Text(Strings.get("story_continue", lang)) }
        }
    }
}

@Composable
private fun DrillBody(viewModel: StoryViewModel, ui: StoryViewModel.UiState, lang: Language) {
    val session = ui.session ?: return
    val story = ui.story ?: return
    val d = currentDrill(session, story) ?: run {
        LaunchedEffect(Unit) { viewModel.dispatch(Action.Advance) }
        return
    }
    val code = lang.code
    LtrCard {
        if (session.drillIndex == 0) {
            Text("📘 " + pickText(story.drill.explain, code), fontSize = 12.sp, color = Color(0xFF6B7877))
            Spacer(Modifier.height(6.dp))
        }
        Text(d.prompt, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        if (!session.revealed) {
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = ui.input,
                onValueChange = { viewModel.setInput(it) },
                label = { Text(Strings.get("story_your_answer", lang)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = { viewModel.dispatch(Action.SubmitDrill(ui.input)) },
                    enabled = ui.input.isNotBlank()
                ) { Text(Strings.get("story_check", lang)) }
                OutlinedButton(onClick = { viewModel.dispatch(Action.Reveal) }) {
                    Text(Strings.get("story_dont_know", lang))
                }
            }
        } else {
            Text(
                (if (session.lastCorrect == true) "✅ " + Strings.get("story_correct", lang) else "—"),
                color = if (session.lastCorrect == true) Color(0xFF2E7D32) else Color.Unspecified
            )
            Text(
                Strings.get("story_model", lang) + " · " + d.model,
                fontSize = 13.sp,
                color = Color(0xFF6B7877)
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    viewModel.resetForNextQuestion()
                    viewModel.dispatch(Action.Advance)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MedGreenDark)
            ) { Text(Strings.get("story_continue", lang)) }
        }
    }
}

@Composable
private fun ProductionBody(
    lang: Language,
    viewModel: StoryViewModel,
    prompt: String,
    starters: List<String>,
    model: String,
    onDone: (Boolean) -> Unit
) {
    var attempted by remember { mutableStateOf<Boolean?>(null) }
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(prompt, fontSize = 14.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    starters.forEach { Text(it, fontSize = 12.sp, color = MedGreenDark, fontWeight = FontWeight.Bold) }
                }
                if (attempted == null) {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(onClick = {
                            attempted = true
                            onDone(true)
                        }) { Text(Strings.get("story_i_did_it", lang)) }
                        OutlinedButton(onClick = {
                            attempted = false
                            onDone(false)
                        }) { Text(Strings.get("story_skip", lang)) }
                    }
                } else {
                    Text(
                        Strings.get("story_self_reported", lang),
                        fontSize = 12.sp,
                        color = Color(0xFF6B7877)
                    )
                    Text(
                        Strings.get("story_model", lang) + " · " + model,
                        fontSize = 13.sp,
                        color = Color(0xFF6B7877)
                    )
                    Button(
                        onClick = {
                            viewModel.dispatch(Action.Advance)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MedGreenDark)
                    ) { Text(Strings.get("story_continue", lang)) }
                }
            }
        }
    }
}

@Composable
private fun SummaryCard(ui: StoryViewModel.UiState, lang: Language, onExit: () -> Unit) {
    val sum = ui.summary ?: return
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🎉", fontSize = 40.sp)
            Text(Strings.get("story_summary_title", lang), fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("${sum.firstTry}/${sum.total} · " + Strings.get("story_summary_first_try", lang), fontSize = 13.sp)
            Text(Strings.get("story_summary_helped", lang) + " : ${sum.helped}", fontSize = 13.sp)
            Text(Strings.get("story_summary_mistakes", lang) + " : ${sum.mistakes}", fontSize = 13.sp)
            Text(
                Strings.get("story_summary_production", lang) + " : " +
                    if (sum.retellAttempted || sum.transferAttempted) "✅" else "—",
                fontSize = 13.sp
            )
            if (sum.difficultPhrases.isNotEmpty()) {
                Text(
                    Strings.get("story_summary_phrases", lang) + " : ${sum.difficultPhrases.size}",
                    fontSize = 13.sp
                )
            }
            if (ui.rewardedXp > 0) Text("+${ui.rewardedXp} XP", fontWeight = FontWeight.Bold, color = MedGreenDark)
            Spacer(Modifier.height(4.dp))
            Button(
                onClick = onExit,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MedGreenDark)
            ) { Text(Strings.get("nav_path", lang)) }
        }
    }
}

/** Contenu anglais forcé LTR (interface pouvant être RTL). */
@Composable
private fun LtrCard(content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                content()
            }
        }
    }
}

private fun pickText(t: com.aistudio.medlingua.dzmed.domain.story.LText, code: String): String =
    when (code) {
        "ar" -> t.ar.ifBlank { t.fr }
        "en" -> t.en.ifBlank { t.fr }
        else -> t.fr
    }

private fun pickHelp(fr: String, ar: String, code: String): String =
    if (code == "ar") ar else fr

private fun stepLabel(step: String, lang: Language): String = when (step) {
    "REVIEW" -> "story_step_review"
    "LISTEN_DISCOVER" -> "story_step_listen1"
    "LISTEN_WITH_HELP" -> "story_step_listen2"
    "LISTEN_CHECK" -> "story_step_listen3"
    "CIRCLING" -> "story_step_questions"
    "PATTERN_PRACTICE" -> "story_step_drill"
    "RETELL" -> "story_step_retell"
    else -> "story_step_transfer"
}.let { Strings.get(it, lang) }
