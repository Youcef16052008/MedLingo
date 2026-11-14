package com.aistudio.medlingua.dzmed.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.medlingua.dzmed.BuildConfig
import com.aistudio.medlingua.dzmed.data.pilot.MEASURE_PREFS
import com.aistudio.medlingua.dzmed.data.pilot.MEASURE_PREFS_BACKUP
import com.aistudio.medlingua.dzmed.data.pilot.PILOT_PREFS
import com.aistudio.medlingua.dzmed.data.pilot.PILOT_PREFS_BACKUP
import com.aistudio.medlingua.dzmed.data.pilot.PilotAssetSource
import com.aistudio.medlingua.dzmed.data.pilot.PilotExerciseStat
import com.aistudio.medlingua.dzmed.data.pilot.PilotPersistedState
import com.aistudio.medlingua.dzmed.data.pilot.MeasurePersistedState
import com.aistudio.medlingua.dzmed.data.pilot.PilotSaveResult
import com.aistudio.medlingua.dzmed.data.pilot.exportMeasureCsv
import com.aistudio.medlingua.dzmed.data.pilot.exportMeasureJson
import com.aistudio.medlingua.dzmed.data.pilot.loadMeasureState
import com.aistudio.medlingua.dzmed.data.pilot.loadPilotState
import com.aistudio.medlingua.dzmed.data.pilot.mergePilotExerciseStat
import com.aistudio.medlingua.dzmed.data.pilot.resetMeasureState
import com.aistudio.medlingua.dzmed.data.pilot.resetPilotState
import com.aistudio.medlingua.dzmed.data.pilot.saveMeasureState
import com.aistudio.medlingua.dzmed.data.pilot.savePilotState
import com.aistudio.medlingua.dzmed.data.pilot.upsertMeasureSession
import com.aistudio.medlingua.dzmed.domain.pilot.MeasureItem
import com.aistudio.medlingua.dzmed.domain.pilot.MeasureSessionState
import com.aistudio.medlingua.dzmed.domain.pilot.PilotConcept
import com.aistudio.medlingua.dzmed.domain.pilot.PilotExercise
import com.aistudio.medlingua.dzmed.domain.pilot.PilotLocalizedText
import com.aistudio.medlingua.dzmed.domain.pilot.PilotManifest
import com.aistudio.medlingua.dzmed.domain.pilot.PilotSessionState
import com.aistudio.medlingua.dzmed.domain.pilot.advanceMeasureSession
import com.aistudio.medlingua.dzmed.domain.pilot.advancePilotSession
import com.aistudio.medlingua.dzmed.domain.pilot.finishedMeasureAt
import com.aistudio.medlingua.dzmed.domain.pilot.initMeasureSession
import com.aistudio.medlingua.dzmed.domain.pilot.initPilotSession
import com.aistudio.medlingua.dzmed.domain.pilot.interruptMeasureSession
import com.aistudio.medlingua.dzmed.domain.pilot.isConceptPublishable
import com.aistudio.medlingua.dzmed.domain.pilot.measureItemsFor
import com.aistudio.medlingua.dzmed.domain.pilot.measureTimeBlocked
import com.aistudio.medlingua.dzmed.domain.pilot.pilotCurrentStepNumber
import com.aistudio.medlingua.dzmed.domain.pilot.pilotTotalSteps
import com.aistudio.medlingua.dzmed.domain.pilot.recordMeasureAnswer
import com.aistudio.medlingua.dzmed.domain.pilot.recordPilotAnswer
import com.aistudio.medlingua.dzmed.domain.pilot.resumeMeasureSession
import com.aistudio.medlingua.dzmed.domain.pilot.resumePilotSession
import com.aistudio.medlingua.dzmed.domain.pilot.usePilotHelp
import com.aistudio.medlingua.dzmed.localization.Language
import com.aistudio.medlingua.dzmed.localization.Strings
import com.aistudio.medlingua.dzmed.ui.theme.MedGreenDark

/**
 * P4.4 — Écrans Compose du pilote Anatomie — miroirs de
 * `web-react/src/screens/PilotScreen.tsx` et `PilotMeasureScreen.tsx`.
 *
 * Règles d'honnêteté portées à l'écran :
 * - le verrou de publication s'applique ICI aussi (un concept non approuvé
 *   n'est jamais présenté ; 0 approuvé ⇒ message explicite, PAS de bouton) ;
 * - l'aide ne transforme jamais une réponse en réussite autonome ;
 * - la mesure n'affiche AUCUN verdict ni score pendant le cycle ;
 * - aucune récompense de l'app principale, aucun SM-2, ids textuels only.
 *
 * Aperçu développeur : en build DEBUG uniquement, le contenu `draft` est
 * exécutable sous bandeau permanent (miroir web `?dev=1`) — les builds
 * release ne voient que le contenu approuvé.
 */

/** Texte localisé d'un champ manifeste (fr par défaut, en/ar selon la langue). */
private fun PilotLocalizedText.forLang(lang: Language): String = when (lang) {
    Language.ARABIC -> ar.ifBlank { fr.ifBlank { en } }
    Language.FRENCH -> fr.ifBlank { en.ifBlank { ar } }
    Language.ENGLISH -> en.ifBlank { fr.ifBlank { ar } }
}

/** Les aides du manifeste existent en FR/AR seulement — jamais la langue cible. */
private fun com.aistudio.medlingua.dzmed.domain.pilot.PilotHints.forLang(lang: Language): String =
    when (lang) {
        Language.ARABIC -> ar.ifBlank { fr }
        Language.FRENCH -> fr.ifBlank { ar }
        Language.ENGLISH -> ar.ifBlank { fr }
    }

/**
 * Codes d'avertissement des stores/moteurs = codes web camelCase (contrat de
 * parité JSON). Traduction locale vers les clés Android snake_case ;
 * `pilotImportVersion:<version>` est tronqué avant les deux-points.
 */
private fun warnKey(code: String): String = when (code.substringBefore(':')) {
    "pilotStorageUnavailable" -> "pilot_storage_unavailable"
    "pilotStorageQuota" -> "pilot_storage_quota"
    "pilotDataCorrupted" -> "pilot_data_corrupted"
    "pilotSchemaChanged" -> "pilot_schema_changed"
    "pilotNoBackup" -> "pilot_no_backup"
    "pilotImportInvalid" -> "pilot_import_invalid"
    "pilotImportSchema" -> "pilot_import_schema"
    "pilotImportVersion" -> "pilot_import_version"
    "measureNeedT0" -> "measure_need_t0"
    "measureNeedT1" -> "measure_need_t1"
    else -> code.substringBefore(':')
}

@Composable
private fun PilotWarning(warning: String?, lang: Language) {
    if (warning != null) {
        Text(
            text = Strings.get(warnKey(warning), lang),
            color = Color(0xFFB36B00),
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}

@Composable
private fun PilotDevBanner(lang: Language) {
    if (BuildConfig.DEBUG) {
        Text(
            text = Strings.get("pilot_dev_preview", lang),
            color = Color(0xFFB36B00),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFF3E0))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

/**
 * Écran d'ENTRAÎNEMENT du pilote. `onOpenMeasure` ouvre l'écran de mesure ;
 * le contenu exécutable est soit les concepts approuvés (release), soit tout
 * le manifeste sous bandeau « aperçu développeur » (debug uniquement).
 */
@Composable
fun PilotScreen(
    lang: Language,
    onExit: () -> Unit,
    onOpenMeasure: () -> Unit
) {
    val context = LocalContext.current
    var loaded by remember { mutableStateOf<Result<PilotAssetSource.PilotAssets>?>(null) }
    LaunchedEffect(Unit) {
        loaded = runCatching { PilotAssetSource.load(context.applicationContext) }
    }
    val assets = loaded?.getOrNull()

    if (assets == null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val error = loaded?.exceptionOrNull()
            if (error == null) {
                CircularProgressIndicator()
            } else {
                // Échec franc : message explicite, jamais un écran vide.
                Text(
                    Strings.get("pilot_load_error", lang),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFB00020)
                )
                Spacer(Modifier.height(8.dp))
                Text(error.message ?: "", fontSize = 12.sp, color = Color(0xFF6B7877))
            }
            Spacer(Modifier.height(16.dp))
            TextButton(onClick = onExit) { Text(Strings.get("pilot_exit", lang)) }
        }
        return
    }

    val (training, measureManifest) = assets
    val devPreview = BuildConfig.DEBUG
    val pool = if (devPreview) training.concepts else training.concepts.filter { isConceptPublishable(it) }

    var persisted by remember {
        val (state, warning) = loadPilotState(
            context.getSharedPreferences(PILOT_PREFS, Context.MODE_PRIVATE),
            training, System.currentTimeMillis()
        )
        mutableStateOf(PersistedWithWarning(state, warning))
    }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        PilotDevBanner(lang)
        PilotWarning(persisted.warning, lang)

        // ——— En-tête ———
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(Strings.get("pilot_title", lang), fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MedGreenDark)
                Text(Strings.get("pilot_subtitle", lang), fontSize = 12.sp, color = Color(0xFF6B7877))
            }
            TextButton(onClick = onExit) { Text(Strings.get("pilot_exit", lang)) }
        }

        if (pool.isEmpty()) {
            // Verrou de publication : 0 approuvé ⇒ message explicite, PAS de bouton.
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1))
            ) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(Strings.get("pilot_none_approved", lang), fontWeight = FontWeight.Bold)
                    Text(Strings.get("pilot_none_approved_hint", lang), fontSize = 13.sp, color = Color(0xFF6B7877))
                    Text(
                        Strings.get("measure_open_still", lang) + " " + training.contentVersion,
                        fontSize = 12.sp, color = Color(0xFF6B7877)
                    )
                    OutlinedButton(onClick = onOpenMeasure, modifier = Modifier.fillMaxWidth()) {
                        Text(Strings.get("measure_title", lang))
                    }
                }
            }
        } else {
            PilotTrainingSection(
                lang = lang,
                manifest = training,
                pool = pool,
                persisted = persisted,
                onStateChange = { persisted = it },
                context = context
            )
            OutlinedButton(
                onClick = onOpenMeasure,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) { Text(Strings.get("measure_title", lang)) }
        }
    }
}

/** Porteur de l'état persisté + du code d'avertissement de chargement. */
private data class PersistedWithWarning(val state: PilotPersistedState, val warning: String?)

@Composable
private fun PilotTrainingSection(
    lang: Language,
    manifest: PilotManifest,
    pool: List<PilotConcept>,
    persisted: PersistedWithWarning,
    onStateChange: (PersistedWithWarning) -> Unit,
    context: Context
) {
    val prefs = context.getSharedPreferences(PILOT_PREFS, Context.MODE_PRIVATE)
    val backupPrefs = context.getSharedPreferences(PILOT_PREFS_BACKUP, Context.MODE_PRIVATE)

    // Reprise : la séance n'est proposée que si elle reste compatible (version,
    // concepts connus) — le moteur décide, jamais l'honneur de l'écran.
    val resumable = resumePilotSession(persisted.state.lastSession, manifest, System.currentTimeMillis())

    var session by remember { mutableStateOf<PilotSessionState?>(null) }
    var input by remember { mutableStateOf("") }
    var helpShown by remember { mutableStateOf(false) }

    fun persist(next: PilotPersistedState) {
        val saved = savePilotState(prefs, next)
        if (saved is PilotSaveResult.Failed) {
            onStateChange(PersistedWithWarning(next, saved.error))
        } else {
            onStateChange(PersistedWithWarning(next, null))
        }
    }

    fun start(resume: PilotSessionState?) {
        val s = resume ?: initPilotSession(pool, manifest.contentVersion, System.currentTimeMillis())
        session = s
        helpShown = false
        input = ""
        persist(persisted.state.copy(updatedAt = s.updatedAt, contentVersion = manifest.contentVersion, lastSession = s))
    }

    /** Enregistre la réponse (idempotent) + fusionne la stat + persiste. */
    fun commitAnswer(exercise: PilotExercise, raw: String) {
        val s = session ?: return
        val now = System.currentTimeMillis()
        val next = recordPilotAnswer(s, exercise, raw, now)
        if (next === s) return // double soumission : idempotent
        val firstResult = next.firstResults[exercise.id] ?: "error"
        val toSave = mergePilotExerciseStat(
            persisted.state,
            PilotExerciseStat(
                conceptId = exercise.conceptId,
                exerciseId = exercise.id,
                attempts = 1,
                firstResult = firstResult,
                helped = next.helped.contains(exercise.id),
                lastAt = now
            ),
            now
        ).copy(updatedAt = now, contentVersion = manifest.contentVersion, lastSession = next)
        session = next
        persist(toSave)
    }

    val current = session?.let { s -> manifest.concepts.firstOrNull { it.id == s.conceptIds[s.i] } }
    val exercise = current?.exercises?.getOrNull(session!!.exIdx)
    val answered = session?.answeredExerciseId != null
    val verdict = if (answered) session?.firstResults?.get(exercise?.id) else null

    if (session == null) {
        // ——— Accueil séance ———
        Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                Strings.count(pool.size, "pilot_concept_one", "pilot_concept_many", lang) +
                    " · " + Strings.get("pilot_content_version", lang) + " " + manifest.contentVersion,
                fontSize = 13.sp, color = Color(0xFF6B7877)
            )
            Button(onClick = { start(resumable) }, modifier = Modifier.fillMaxWidth()) {
                Text(Strings.get(if (resumable != null) "pilot_resume" else "pilot_start", lang))
            }
            OutlinedButton(
                onClick = {
                    val r = resetPilotState(prefs, backupPrefs)
                    if (r is PilotSaveResult.Failed) {
                        onStateChange(PersistedWithWarning(persisted.state, r.error))
                    } else {
                        onStateChange(PersistedWithWarning(PilotPersistedState(), null))
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text(Strings.get("pilot_reset", lang)) }
        }
        return
    }

    val s = session!!
    if (s.finished) {
        // ——— Bilan : réussites autonomes / avec aide / à revoir. Aucune XP. ———
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(Strings.get("pilot_finished", lang), fontWeight = FontWeight.Bold, fontSize = 17.sp, color = MedGreenDark)
            Text(
                Strings.count(s.autonomous.size, "pilot_autonomous_one", "pilot_autonomous_many", lang),
                fontSize = 14.sp
            )
            Text(
                Strings.count(s.helped.size, "pilot_helped_one", "pilot_helped_many", lang),
                fontSize = 14.sp
            )
            Text(
                Strings.count(s.errors.size, "pilot_error_one", "pilot_error_many", lang) + " — " +
                    Strings.get("pilot_revisions_note", lang),
                fontSize = 14.sp
            )
            Button(
                onClick = {
                    session = null
                    helpShown = false
                    input = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text(Strings.get("pilot_new_session", lang)) }
        }
        return
    }

    // ——— Exercice courant ———
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        LinearProgressIndicator(
            progress = {
                pilotCurrentStepNumber(s, manifest).toFloat() / pilotTotalSteps(s, manifest).coerceAtLeast(1)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            Strings.get("pilot_exercise_of", lang)
                .replace("{n}", "${pilotCurrentStepNumber(s, manifest)}")
                .replace("{total}", "${pilotTotalSteps(s, manifest)}"),
            fontSize = 12.sp, color = Color(0xFF6B7877)
        )
        Text(current!!.labelEn, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MedGreenDark)
        Text(current.definition.forLang(lang), fontSize = 14.sp)
        if (current.example != null) {
            Text(current.example.forLang(lang), fontSize = 13.sp, color = Color(0xFF6B7877))
        }

        Text(exercise!!.prompt.forLang(lang), fontSize = 15.sp, fontWeight = FontWeight.Medium)

        // Consigne EN jamais traduite (langue cible), aide en FR/AR seulement.
        if (helpShown) {
            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF7F2))) {
                Text(
                    Strings.get("pilot_hint_label", lang) + " " + current.hints.forLang(lang),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        when (exercise.kind) {
            "mcq" -> {
                for (option in exercise.options) {
                    OutlinedButton(
                        onClick = { if (!answered) commitAnswer(exercise, option) },
                        enabled = !answered,
                        modifier = Modifier.fillMaxWidth()
                    ) { Text(option) }
                }
            }
            else -> {
                OutlinedTextField(
                    value = input,
                    onValueChange = { if (!answered) input = it },
                    placeholder = { Text(Strings.get("pilot_answer_placeholder", lang)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Button(
                    onClick = { if (!answered) commitAnswer(exercise, input) },
                    enabled = !answered,
                    modifier = Modifier.fillMaxWidth()
                ) { Text(Strings.get("pilot_check", lang)) }
            }
        }

        // Aide : consultable AVANT de répondre ⇒ la réussite ne sera jamais autonome.
        if (!answered && exercise.kind == "mcq") {
            TextButton(onClick = {
                helpShown = true
                session = usePilotHelp(s, exercise.id, System.currentTimeMillis()).also { st ->
                    persist(persisted.state.copy(updatedAt = st.updatedAt, lastSession = st))
                }
            }) { Text(Strings.get("pilot_help", lang)) }
        }

        // Verdict — premier essai seulement : autonome / avec aide / erreur.
        if (answered) {
            Text(
                Strings.get(
                    when (verdict) {
                        "first_try" -> "pilot_correct_autonomous"
                        "helped" -> "pilot_correct_helped"
                        else -> "pilot_incorrect"
                    },
                    lang
                ),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = if (verdict == "error") Color(0xFFB00020) else MedGreenDark
            )
            Button(onClick = {
                val next = advancePilotSession(s, manifest, System.currentTimeMillis())
                session = next
                helpShown = false
                input = ""
                persist(persisted.state.copy(updatedAt = next.updatedAt, lastSession = next))
            }, modifier = Modifier.fillMaxWidth()) {
                Text(Strings.get("pilot_continue", lang))
            }
        }
    }
}

/**
 * Écran de MESURE (T0/T1/T2) — miroir de `PilotMeasureScreen.tsx` :
 * pseudonyme éditable, gardes de temps, AUCUN verdict affiché, interruption
 * consignée, export JSON/CSV manuel (partage Android), contenu versionné.
 */
@Composable
fun PilotMeasureScreen(
    lang: Language,
    onExit: () -> Unit
) {
    val context = LocalContext.current
    var loaded by remember { mutableStateOf<Result<PilotAssetSource.PilotAssets>?>(null) }
    LaunchedEffect(Unit) {
        loaded = runCatching { PilotAssetSource.load(context.applicationContext) }
    }
    val assets = loaded?.getOrNull()
    if (assets == null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(Strings.get("pilot_load_error", lang), fontWeight = FontWeight.Bold, color = Color(0xFFB00020))
            Spacer(Modifier.height(16.dp))
            TextButton(onClick = onExit) { Text(Strings.get("pilot_exit", lang)) }
        }
        return
    }
    val (training, measure) = assets
    val prefs = context.getSharedPreferences(MEASURE_PREFS, Context.MODE_PRIVATE)
    val backupPrefs = context.getSharedPreferences(MEASURE_PREFS_BACKUP, Context.MODE_PRIVATE)

    var persisted by remember {
        val (state, warning) = loadMeasureState(prefs, System.currentTimeMillis())
        mutableStateOf(MeasureWithWarning(state, warning))
    }
    var pseudonym by remember(persisted.state.pseudonym) { mutableStateOf(persisted.state.pseudonym) }
    var input by remember { mutableStateOf("") }
    var shownAt by remember { mutableStateOf(System.currentTimeMillis()) }

    fun persist(next: MeasurePersistedState) {
        val saved = saveMeasureState(prefs, next)
        persisted = if (saved is PilotSaveResult.Failed) {
            MeasureWithWarning(next, saved.error)
        } else {
            MeasureWithWarning(next, null)
        }
    }

    // Pseudonyme éditable (donnée minimale, jamais déduite de données perso).
    val syncPseudonym: (String) -> Unit = { text ->
        pseudonym = text
        if (text != persisted.state.pseudonym) persist(persisted.state.copy(pseudonym = text, updatedAt = System.currentTimeMillis()))
    }

    val devPreview = BuildConfig.DEBUG
    val pool = if (devPreview) training.concepts else training.concepts.filter { isConceptPublishable(it) }

    // Séance ouverte : reprise de la dernière non finie non interrompue.
    val openSession = persisted.state.sessions.lastOrNull { !it.finished && !it.interrupted }
    val session = openSession?.let { resumeMeasureSession(it, measure, System.currentTimeMillis()) }
    val items = if (session != null) measureItemsFor(measure, session.conceptIds, session.time) else emptyList()
    val item = session?.let { items.getOrNull(it.i) }

    LaunchedEffect(session?.id, session?.i) { shownAt = System.currentTimeMillis() }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        PilotDevBanner(lang)
        PilotWarning(persisted.warning, lang)
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(Strings.get("measure_title", lang), fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MedGreenDark)
                Text(Strings.get("measure_intro", lang), fontSize = 12.sp, color = Color(0xFF6B7877))
            }
            TextButton(onClick = onExit) { Text(Strings.get("pilot_exit", lang)) }
        }
        Text(
            Strings.get("measure_no_feedback", lang),
            fontSize = 13.sp, color = Color(0xFF6B7877),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            Strings.get("measure_no_personal_data", lang),
            fontSize = 13.sp, color = Color(0xFF6B7877),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        if (session != null && item != null) {
            // ——— Item courant : AUCUN feedback, avancement immédiat ———
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                LinearProgressIndicator(
                    progress = { (session.i + 1f) / items.size.coerceAtLeast(1) },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    Strings.get("measure_item_of", lang).replace("{n}", "${session.i + 1}").replace("{total}", "${items.size}"),
                    fontSize = 12.sp, color = Color(0xFF6B7877)
                )
                Text(item.prompt.forLang(lang), fontSize = 15.sp, fontWeight = FontWeight.Medium)
                when (item.kind) {
                    "mcq" -> {
                        for (option in item.options) {
                            Button(
                                onClick = {
                                    recordMeasure(session, item, option, items, persisted, shownAt, ::persist)
                                    input = ""
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) { Text(option) }
                        }
                    }
                    else -> {
                        OutlinedTextField(
                            value = input,
                            onValueChange = { input = it },
                            placeholder = { Text(Strings.get("pilot_answer_placeholder", lang)) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Button(
                            onClick = {
                                recordMeasure(session, item, input, items, persisted, shownAt, ::persist)
                                input = ""
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) { Text(Strings.get("measure_next", lang)) }
                    }
                }
                Text(
                    Strings.get("measure_interrupt_note", lang),
                    fontSize = 12.sp, color = Color(0xFF6B7877)
                )
            }
        } else {
            // ——— Hub T0/T1/T2 ———
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = pseudonym,
                    onValueChange = syncPseudonym,
                    label = { Text(Strings.get("measure_pseudonym", lang)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                if (pool.isEmpty()) {
                    Text(Strings.get("measure_nothing_measurable", lang), color = Color(0xFFB36B00), fontWeight = FontWeight.Bold)
                }
                for (time in listOf("T0", "T1", "T2")) {
                    val b = measureTimeBlocked(time, persisted.state.sessions)
                    val done = persisted.state.sessions.any { it.time == time && it.finished && !it.interrupted }
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F5EC))
                    ) {
                        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    time + " — " + Strings.get(
                                        when (time) {
                                            "T0" -> "measure_t0_desc"
                                            "T1" -> "measure_t1_desc"
                                            else -> "measure_t2_desc"
                                        },
                                        lang
                                    ),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    modifier = Modifier.weight(1f)
                                )
                                if (done) {
                                    Text(Strings.get("measure_done_short", lang), color = MedGreenDark, fontSize = 13.sp)
                                }
                            }
                            when {
                                done -> {}
                                pool.isEmpty() -> {}
                                b != null -> Text(Strings.get(warnKey(b), lang), fontSize = 13.sp, color = Color(0xFFB36B00))
                                else -> Button(
                                    onClick = {
                                        val s = initMeasureSession(
                                            time,
                                            pool.map { it.id },
                                            training.contentVersion,
                                            System.currentTimeMillis(),
                                            finishedMeasureAt(persisted.state.sessions, "T1")
                                        )
                                        persist(upsertMeasureSession(persisted.state, s))
                                        input = ""
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) { Text(Strings.get("measure_start", lang)) }
                            }
                        }
                    }
                }
                // ——— Export manuel (consentement à l'écran, aucune télémétrie) ———
                Text(Strings.get("measure_consent_note", lang), fontSize = 12.sp, color = Color(0xFF6B7877))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = { share(context, exportMeasureJson(persisted.state, training.contentVersion), "text/plain") },
                        enabled = persisted.state.sessions.isNotEmpty()
                    ) { Text(Strings.get("measure_export_json", lang)) }
                    OutlinedButton(
                        onClick = { share(context, exportMeasureCsv(persisted.state, training.contentVersion), "text/csv") },
                        enabled = persisted.state.sessions.isNotEmpty()
                    ) { Text(Strings.get("measure_export_csv", lang)) }
                }
                OutlinedButton(
                    onClick = {
                        val r = resetMeasureState(prefs, backupPrefs)
                        if (r is PilotSaveResult.Failed) {
                            persisted = MeasureWithWarning(persisted.state, r.error)
                        } else {
                            persisted = MeasureWithWarning(MeasurePersistedState(pseudonym = persisted.state.pseudonym), null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text(Strings.get("measure_reset", lang)) }
            }
        }
    }
}

/** Enregistre la réponse de mesure (sans verdict affiché) et avance. */
private fun recordMeasure(
    session: MeasureSessionState,
    item: MeasureItem,
    raw: String,
    items: List<MeasureItem>,
    persisted: MeasureWithWarning,
    shownAt: Long,
    persist: (MeasurePersistedState) -> Unit
) {
    val now = System.currentTimeMillis()
    val next = recordMeasureAnswer(session, item, raw, now, shownAt)
    if (next === session) return
    val advanced = advanceMeasureSession(next, items, now)
    persist(upsertMeasureSession(persisted.state, advanced))
}

/** Partage du contenu exporté — transmission MANUELLE par l'étudiant. */
private fun share(context: Context, content: String, mime: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = mime
        putExtra(Intent.EXTRA_TEXT, content)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(Intent.createChooser(intent, null))
}

private data class MeasureWithWarning(val state: MeasurePersistedState, val warning: String?)
