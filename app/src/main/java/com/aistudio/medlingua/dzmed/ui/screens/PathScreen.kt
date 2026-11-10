package com.aistudio.medlingua.dzmed.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.aistudio.medlingua.dzmed.data.initial.InitialData
import com.aistudio.medlingua.dzmed.domain.gamification.GamificationEconomy
import com.aistudio.medlingua.dzmed.domain.path.NodeState
import com.aistudio.medlingua.dzmed.domain.path.ChapterSyllabus
import com.aistudio.medlingua.dzmed.domain.path.PathBuilder
import com.aistudio.medlingua.dzmed.domain.path.PathModule
import com.aistudio.medlingua.dzmed.domain.path.PathNode
import com.aistudio.medlingua.dzmed.localization.Language
import com.aistudio.medlingua.dzmed.localization.Strings
import com.aistudio.medlingua.dzmed.ui.components.GoalRing
import com.aistudio.medlingua.dzmed.ui.components.Medi
import com.aistudio.medlingua.dzmed.ui.components.MediMood
import com.aistudio.medlingua.dzmed.ui.theme.MedGreenDark
import com.aistudio.medlingua.dzmed.ui.viewmodel.UiState
import java.text.Normalizer
import java.util.Calendar

/**
 * Onglet 📍 Parcours — navigation **Module → Chapitre → Leçons**.
 *
 * 15 modules × leurs chapitres du seed × leçons de 3 termes (+ révisions).
 * Tout afficher d'un coup donnait une page interminable (et des centaines de
 * lignes composées à chaque recomposition). On suit donc le modèle Duolingo :
 *
 *  1. un seul bouton d'action « Continuer » (la prochaine leçon jouable) ;
 *  2. un sélecteur de module (panneau replié par défaut) ;
 *  3. la liste des chapitres **du module choisi** (avec recherche) ;
 *  4. taper un chapitre ouvre sa page dédiée : ses leçons en chemin vertical.
 *
 * Un chapitre est acquis (et ouvre le suivant) quand toutes ses leçons
 * d'apprentissage sont jouées, ses révisions obligatoires réussies et sa
 * moyenne ≥ 50 %.
 */
@Composable
fun PathScreen(
    uiState: UiState,
    onOpenLesson: (moduleId: String, chapter: String, level: Int) -> Unit,
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

    // Parcours réel : 15 modules → chapitres du seed → leçons découpées par
    // `ChapterSyllabus` (3 nouveaux termes par leçon, révision après chaque
    // paire). Les états viennent de `lesson_scores` (clé module|chapitre|niveau).
    val modules = remember(lang) {
        InitialData.modulesList.map { PathModule(it.id, moduleTitle(it, lang), it.colorHex) }
    }
    val chaptersByModule = remember {
        InitialData.modulesList.associate { info ->
            val terms = InitialData.termsOfModule(info.titleFr)
            info.id to InitialData.chaptersOfModule(info.titleFr).map { chapter ->
                PathBuilder.ChapterSeed(
                    title = chapter,
                    termIds = terms.filter { it.chapter == chapter }.map { it.id }
                )
            }
        }
    }
    val units = remember(uiState.lessonBest, modules) {
        PathBuilder.buildChapterPath(uiState.lessonBest, modules, chaptersByModule)
    }
    val next = remember(units, lang) {
        units.firstNotNullOfOrNull { unit ->
            PathBuilder.nextChapterLesson(unit)?.let { (chapter, node) ->
                NextLesson(
                    moduleId = unit.module.id,
                    moduleTitle = unit.module.title,
                    chapterId = chapter.chapterId,
                    chapterTitle = chapter.title,
                    level = node.level,
                    title = lessonTitle(node, lessonNumber(chapter.nodes, node), lang),
                    termCount = node.termCount
                )
            }
        }
    }

    // Module affiché : celui de la prochaine leçon, sinon le premier.
    var selectedModuleId by remember {
        mutableStateOf(next?.moduleId ?: units.firstOrNull()?.module?.id.orEmpty())
    }
    var pickerOpen by remember { mutableStateOf(false) }
    // La recherche est repliée par défaut : elle prenait ~64 dp en permanence.
    var searchOpen by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    // Chapitre ouvert : `null` = liste des chapitres, sinon page de leçons.
    var openChapterId by remember { mutableStateOf<String?>(null) }
    var tipVisible by remember { mutableStateOf(false) }

    val unit = units.firstOrNull { it.module.id == selectedModuleId } ?: units.firstOrNull()
    val openChapter = unit?.chapters?.firstOrNull { it.chapterId == openChapterId }

    // Retour Android : la page de leçons revient à la liste des chapitres.
    BackHandler(enabled = openChapterId != null) { openChapterId = null }

    if (unit != null && openChapter != null) {
        ChapterLessonPage(
            unit = unit,
            chapter = openChapter,
            lang = lang,
            onBack = { openChapterId = null },
            onOpenChapter = { openChapterId = it },
            onOpenLesson = onOpenLesson,
            onShowTip = { tipVisible = true },
            modifier = modifier
        )
        if (tipVisible) {
            LockedTip(lang = lang, onDismiss = { tipVisible = false })
        }
        return
    }

    val progressByModule = remember(units) {
        units.associate { unit ->
            unit.module.id to ModuleProgress(
                done = PathBuilder.completedLessons(unit),
                total = PathBuilder.totalLessons(unit),
                chapters = unit.chapters.size
            )
        }
    }
    val chapters = remember(unit, query) {
        val all = unit?.chapters.orEmpty()
        val needle = normalize(query)
        if (needle.isBlank()) all else all.filter { normalize(it.title).contains(needle) }
    }

    val mood = when {
        goal.done -> MediMood.CELEBRATING
        stats.streakDays == 0 -> MediMood.MOTIVATING
        else -> MediMood.HAPPY
    }
    val greet = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
        in 0..11 -> Strings.get("greet_morning", lang)
        in 12..17 -> Strings.get("greet_afternoon", lang)
        else -> Strings.get("greet_evening", lang)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF0F7F4)),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // ---- En-tête compact : Medi + anneau + objectif du jour ----
        // La série et les gemmes sont déjà dans la barre du haut : on ne les
        // répète plus ici (c'était ~50 dp de hauteur perdue).
        item(key = "header") {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Medi(
                            mood = mood,
                            message = next?.let { "$greet · ${it.chapterTitle} ${it.title}" }
                                ?: Strings.get("path_done_all", lang),
                            lang = lang,
                            modifier = Modifier.weight(1f)
                        )
                        GoalRing(pct = goal.pct, done = goal.done)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${goal.today} / ${goal.goal} ${Strings.get("stat_xp", lang)} · " +
                                "$due ${Strings.get("cards_due", lang)}",
                            fontSize = 11.sp,
                            color = Color(0xFF6B7877)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            StreakPill("❄️ ${stats.streakFreezeCount}")
                            FreezeButton(
                                enabled = stats.gems >= 200,
                                lang = lang,
                                onClick = onBuyFreeze
                            )
                        }
                    }

                    if (stats.pendingChests > 0) {
                        Text(
                            text = "📦 ${Strings.get("reward_chest", lang)} ×${stats.pendingChests}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF12211C),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFFFF3C4))
                                .clickable { onOpenChests() }
                                .padding(vertical = 8.dp, horizontal = 10.dp)
                        )
                    }
                }
            }
        }

        // ---- Une seule action principale : reprendre le parcours ----
        item(key = "continue") {
            ContinueCard(next = next, lang = lang, onOpenLesson = onOpenLesson)
        }

        // ---- Sélecteur de module (panneau replié) ----
        item(key = "module-picker") {
            ModulePickerCard(
                unit = unit,
                modules = modules,
                progressByModule = progressByModule,
                expanded = pickerOpen,
                lang = lang,
                onToggle = { pickerOpen = !pickerOpen },
                onSelect = { moduleId ->
                    selectedModuleId = moduleId
                    pickerOpen = false
                    query = ""
                }
            )
        }

        if (unit != null) {
            // En-tête des chapitres : compteur + loupe (la recherche ne prend
            // de la place que si on l'ouvre).
            item(key = "chapters-title") {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "${chapters.size} ${Strings.get("chapters_lbl", lang)}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF12211C)
                            )
                            Text(
                                text = Strings.get("choose_chapter_hint", lang),
                                fontSize = 11.sp,
                                color = Color(0xFF6B7877)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(if (searchOpen) Color(0xFFE8F4FD) else Color.White)
                                .clickable {
                                    searchOpen = !searchOpen
                                    if (!searchOpen) query = ""
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (searchOpen) Icons.Filled.Close else Icons.Filled.Search,
                                contentDescription = Strings.get("search_chapter", lang),
                                tint = if (searchOpen) Color(0xFF1CB0F6) else Color(0xFF6B7877),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    if (searchOpen) {
                        OutlinedTextField(
                            value = query,
                            onValueChange = { query = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp),
                            leadingIcon = {
                                Icon(
                                    Icons.Filled.Search,
                                    contentDescription = null,
                                    tint = Color(0xFF6B7877)
                                )
                            },
                            trailingIcon = {
                                if (query.isNotEmpty()) {
                                    Icon(
                                        Icons.Filled.Close,
                                        contentDescription = "Effacer",
                                        tint = Color(0xFF6B7877),
                                        modifier = Modifier.clickable { query = "" }
                                    )
                                }
                            },
                            placeholder = {
                                Text(
                                    Strings.get("search_chapter", lang),
                                    fontSize = 14.sp,
                                    color = Color(0xFF90A4AE)
                                )
                            }
                        )
                    }
                }
            }

            if (chapters.isEmpty()) {
                item(key = "empty") {
                    Text(
                        text = Strings.get("no_chapter_match", lang),
                        fontSize = 14.sp,
                        color = Color(0xFF6B7877),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(chapters, key = { it.chapterId }) { chapter ->
                    ChapterRow(
                        chapter = chapter,
                        lang = lang,
                        onClick = {
                            if (PathBuilder.isChapterOpen(chapter)) {
                                openChapterId = chapter.chapterId
                                query = ""
                            } else {
                                tipVisible = true
                            }
                        }
                    )
                }
            }
        }

        // ---- Ma séance d'aujourd'hui (mode histoire) ----
        item(key = "story") {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F5EC)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        "📖 " + Strings.get("story_card_title", lang),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = MedGreenDark
                    )
                    Text(
                        Strings.get("story_card_sub", lang),
                        fontSize = 12.sp,
                        color = Color(0xFF6B7877)
                    )
                    Button(
                        onClick = onOpenStory,
                        colors = ButtonDefaults.buttonColors(containerColor = MedGreenDark),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(Strings.get("story_start", lang))
                    }
                }
            }
        }
    }

    if (tipVisible) {
        LockedTip(lang = lang, onDismiss = { tipVisible = false })
    }
}

/** Prochaine leçon jouable, tous modules confondus. */
private data class NextLesson(
    val moduleId: String,
    val moduleTitle: String,
    val chapterId: String,
    val chapterTitle: String,
    val level: Int,
    /** « Leçon 3 » ou « Révision 2 ». */
    val title: String,
    val termCount: Int
)

@Composable
private fun ContinueCard(
    next: NextLesson?,
    lang: Language,
    onOpenLesson: (String, String, Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0B5A3C)),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // Tout tient sur une ligne : libellé + destination à gauche, action à
        // droite (avant : 3 lignes + un bouton pleine largeur = ~150 dp).
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "▶ ${Strings.get("continue_lbl", lang)}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White.copy(alpha = 0.75f)
                )
                if (next == null) {
                    Text(
                        text = Strings.get("path_done_all", lang),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                } else {
                    Text(
                        text = "${next.moduleTitle} · ${next.chapterTitle}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${next.title} · ${next.termCount} ${Strings.get("terms_lbl", lang)}",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }
            }
            if (next != null) {
                Button(
                    onClick = { onOpenLesson(next.moduleId, next.chapterId, next.level) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = MedGreenDark
                    ),
                    contentPadding = PaddingValues(horizontal = 18.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = Strings.get("start_label", lang),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

/**
 * Sélecteur de module : une carte compacte, et un panneau dépliable avec les
 * 15 modules (2 colonnes). Un seul module est affiché à la fois dans la liste
 * des chapitres, donc la page garde une longueur raisonnable.
 */
/** Avancement d'un module dans le sélecteur (leçons réussies / totales). */
private data class ModuleProgress(val done: Int, val total: Int, val chapters: Int)

@Composable
private fun ModulePickerCard(
    unit: PathBuilder.ModuleChapterPath?,
    modules: List<PathModule>,
    progressByModule: Map<String, ModuleProgress>,
    expanded: Boolean,
    lang: Language,
    onToggle: () -> Unit,
    onSelect: (String) -> Unit
) {
    val done = unit?.let { PathBuilder.completedLessons(it) } ?: 0
    val total = unit?.let { PathBuilder.totalLessons(it) } ?: 0
    val pct = if (total == 0) 0 else (done * 100) / total

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggle() }
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE3F5EC)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = unit?.let { moduleIcon(it.module.id) } ?: "📘",
                        fontSize = 20.sp
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = Strings.get("module_lbl", lang),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF90A4AE)
                    )
                    Text(
                        text = unit?.module?.title ?: "",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF12211C),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        // Compteur conservé pour l'onglet : « N chapitres · x/y leçons ».
                        text = "${unit?.chapters?.size ?: 0} ${Strings.get("chapters_lbl", lang)} · " +
                            "$done/$total ${Strings.get("lessons_lbl", lang)} · $pct%",
                        fontSize = 11.sp,
                        color = Color(0xFF6B7877)
                    )
                }
                Text(
                    text = if (expanded) "▴" else Strings.get("change_lbl", lang),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1CB0F6)
                )
            }

            ProgressBar(
                pct = pct,
                color = Color(0xFF58CC02),
                modifier = Modifier.padding(horizontal = 14.dp)
            )

            if (expanded) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    modules.chunked(2).forEach { pair ->
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            pair.forEach { module ->
                                val progress = progressByModule[module.id]
                                    ?: ModuleProgress(0, 0, 0)
                                ModuleTile(
                                    module = module,
                                    done = progress.done,
                                    total = progress.total,
                                    chapters = progress.chapters,
                                    selected = module.id == unit?.module?.id,
                                    lang = lang,
                                    onClick = { onSelect(module.id) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            if (pair.size == 1) Spacer(Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ModuleTile(
    module: PathModule,
    done: Int,
    total: Int,
    chapters: Int,
    selected: Boolean,
    lang: Language,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pct = if (total == 0) 0 else (done * 100) / total
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(if (selected) Color(0xFFE3F5EC) else Color(0xFFF7FAF9))
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) MedGreenDark else Color(0xFFE0E7E4),
                shape = RoundedCornerShape(14.dp)
            )
            .clickable { onClick() }
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = moduleIcon(module.id), fontSize = 18.sp)
            Text(
                text = module.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF12211C),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }
        Text(
            text = "$chapters ${Strings.get("chapters_lbl", lang)} · $pct%",
            fontSize = 10.sp,
            color = Color(0xFF6B7877)
        )
        ProgressBar(pct = pct, color = Color(0xFF58CC02))
    }
}

@Composable
private fun ChapterRow(
    chapter: PathBuilder.ChapterPath,
    lang: Language,
    onClick: () -> Unit
) {
    val done = chapter.nodes.count { it.state == NodeState.DONE }
    val total = chapter.nodes.size
    val pct = if (total == 0) 0 else (done * 100) / total
    val locked = chapter.state == NodeState.LOCKED
    val learning = chapter.nodes.count { it.kind == ChapterSyllabus.Kind.NEW }
    val reviews = total - learning

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (locked) Color(0xFFF5F7F6) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (locked) 0.dp else 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            locked -> Color(0xFFECEFF1)
                            chapter.completed -> Color(0xFFE8F8D8)
                            else -> Color(0xFFE3F5EC)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when {
                        locked -> "🔒"
                        chapter.completed -> "✅"
                        else -> "📖"
                    },
                    fontSize = 17.sp
                )
            }
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = chapter.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (locked) Color(0xFF90A4AE) else Color(0xFF12211C),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    // « 30 termes · 10 leçons + 5 révisions · moyenne 62 % »
                    text = "$learning ${Strings.get("lessons_lbl", lang)} + $reviews " +
                        "${Strings.get("reviews_lbl", lang)} · $done/$total · " +
                        "${Strings.get("average_lbl", lang)} ${chapter.average}%",
                    fontSize = 11.sp,
                    color = Color(0xFF90A4AE)
                )
                if (locked) {
                    Text(
                        text = Strings.get("chapter_locked_hint", lang),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFB0BEC5)
                    )
                } else {
                    ProgressBar(pct = pct, color = Color(0xFF58CC02))
                }
            }
            Text(
                text = if (locked) "🔒" else "›",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = if (locked) Color(0xFFB0BEC5) else Color(0xFF1CB0F6)
            )
        }
    }
}

/**
 * Page d'un chapitre : ses leçons en chemin vertical (zig-zag Duolingo) —
 * apprentissages de 3 termes et révisions intercalées.
 * Seules les leçons de ce chapitre sont composées ici.
 */
@Composable
private fun ChapterLessonPage(
    unit: PathBuilder.ModuleChapterPath,
    chapter: PathBuilder.ChapterPath,
    lang: Language,
    onBack: () -> Unit,
    onOpenChapter: (String) -> Unit,
    onOpenLesson: (String, String, Int) -> Unit,
    onShowTip: () -> Unit,
    modifier: Modifier = Modifier
) {
    val done = chapter.nodes.count { it.state == NodeState.DONE }
    val total = chapter.nodes.size
    val pct = if (total == 0) 0 else (done * 100) / total
    val unitDone = PathBuilder.completedLessons(unit)
    val unitTotal = PathBuilder.totalLessons(unit)
    val index = unit.chapters.indexOfFirst { it.chapterId == chapter.chapterId }
    val nextChapter = unit.chapters.getOrNull(index + 1)
    val previousChapter = if (index > 0) unit.chapters[index - 1] else null

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF0F7F4))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Retour vers la liste des chapitres du module.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable { onBack() }
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = Strings.get("all_chapters", lang),
                tint = Color(0xFF12211C),
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = Strings.get("all_chapters", lang),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF12211C)
            )
        }

        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE3F5EC)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = moduleIcon(unit.module.id), fontSize = 20.sp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = unit.module.title,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF90A4AE)
                        )
                        Text(
                            text = chapter.title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF12211C)
                        )
                    }
                    Text(
                        text = when {
                            pct == 100 -> "👑"
                            pct >= 50 -> "🥇"
                            else -> "📘"
                        },
                        fontSize = 22.sp
                    )
                }

                ProgressBar(pct = pct, color = Color(0xFF58CC02))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$done/$total ${Strings.get("lessons_lbl", lang)} · $pct%",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF455A64)
                    )
                    Text(
                        text = "${Strings.get("average_lbl", lang)} ${chapter.average}% · " +
                            "$unitDone/$unitTotal ${Strings.get("lessons_lbl", lang)}",
                        fontSize = 11.sp,
                        color = Color(0xFF90A4AE)
                    )
                }
            }
        }

        Text(
            text = Strings.get("chapter_lessons_lbl", lang),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF12211C)
        )

        chapter.nodes.forEachIndexed { position, node ->
            LessonPathNode(
                node = node,
                number = lessonNumber(chapter.nodes, node),
                chapterId = chapter.chapterId,
                position = position,
                lang = lang,
                onOpenLesson = onOpenLesson,
                onShowTip = onShowTip
            )
        }

        // Navigation chapitre précédent / suivant sans repasser par la liste.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (previousChapter != null) {
                ChapterNavButton(
                    text = "← ${previousChapter.title}",
                    modifier = Modifier.weight(1f),
                    onClick = { onOpenChapter(previousChapter.chapterId) }
                )
            }
            if (nextChapter != null) {
                ChapterNavButton(
                    text = "${Strings.get("next_chapter_lbl", lang)} · ${nextChapter.title} →",
                    modifier = Modifier.weight(1f),
                    onClick = { onOpenChapter(nextChapter.chapterId) }
                )
            }
        }
    }
}

@Composable
private fun ChapterNavButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF1CB0F6),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFE8F4FD))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 10.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun LessonPathNode(
    node: PathNode,
    number: Int,
    chapterId: String,
    position: Int,
    lang: Language,
    onOpenLesson: (String, String, Int) -> Unit,
    onShowTip: () -> Unit
) {
    val state = node.state
    val done = state == NodeState.DONE
    val locked = state == NodeState.LOCKED
    val review = node.kind == ChapterSyllabus.Kind.REVIEW
    val face = when {
        done -> "✅"
        locked -> "🔒"
        review -> "🔁"
        else -> "⭐"
    }
    val title = lessonTitle(node, number, lang)
    // Le niveau reste lisible même verrouillé : l'élève sait quelle leçon
    // s'ouvrira ensuite.
    val meta = when {
        done -> "${node.score}% · ${node.termCount} ${Strings.get("terms_lbl", lang)}"
        review -> "${node.termCount} ${Strings.get("terms_lbl", lang)} · " +
            Strings.get(
                if (node.required) "review_required_lbl" else "review_optional_lbl",
                lang
            )
        else -> "${node.termCount} ${Strings.get("terms_lbl", lang)}"
    }
    val bg = when {
        done -> Color(0xFF58CC02)
        locked -> Color(0xFFCFD8DC)
        review -> Color(0xFFFF9600)
        else -> Color(0xFF1CB0F6)
    }
    // Zig-zag : 0, +40, 0, -40, 0, +40 (le chemin « serpente »).
    val dx = when (position % 4) {
        1 -> 40.dp
        3 -> (-40).dp
        else -> 0.dp
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.offset(x = dx),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state == NodeState.AVAILABLE) {
                Text(
                    text = "${Strings.get("start_label", lang)} ▼",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = bg,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .border(1.dp, bg, RoundedCornerShape(10.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                )
                Spacer(Modifier.height(4.dp))
            }

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(bg)
                    .border(4.dp, bg.copy(alpha = 0.25f), CircleShape)
                    // Lu par TalkBack et exposé à la hiérarchie UI (tests ADB).
                    .semantics {
                        contentDescription = "$title · ${node.termCount} " +
                            Strings.get("terms_lbl", lang) +
                            when {
                                done -> " · ${node.score}%"
                                locked -> " · ${Strings.get("lesson_locked_lbl", lang)}"
                                else -> " · ${Strings.get("lesson_available_lbl", lang)}"
                            }
                    }
                    .clickable {
                        if (locked) onShowTip() else onOpenLesson(node.moduleId, chapterId, node.level)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = face, fontSize = 26.sp)
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (locked) Color(0xFF90A4AE) else Color(0xFF455A64)
            )
            Text(
                text = meta,
                fontSize = 11.sp,
                fontWeight = if (review && node.required && !done) FontWeight.Bold
                else FontWeight.Normal,
                color = if (locked) Color(0xFF90A4AE) else Color(0xFF6B7877)
            )
        }
    }
}

/** Numéro d'affichage d'un nœud dans sa série (« Leçon 3 » / « Révision 2 »). */
private fun lessonNumber(nodes: List<PathNode>, node: PathNode): Int =
    nodes.filter { it.kind == node.kind }.indexOfFirst { it.level == node.level } + 1

/** Intitulé localisé d'un nœud : « Leçon 3 » ou « Révision 2 ». */
private fun lessonTitle(node: PathNode, number: Int, lang: Language): String {
    val key = if (node.kind == ChapterSyllabus.Kind.REVIEW) "review_lbl" else "lesson_lbl"
    return "${Strings.get(key, lang)} $number"
}

@Composable
private fun ProgressBar(pct: Int, color: Color, modifier: Modifier = Modifier) {
    val fraction = (pct.coerceIn(0, 100)) / 100f
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(6.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(Color(0xFFE0E7E4))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction)
                .fillMaxHeight()
                .clip(RoundedCornerShape(3.dp))
                .background(color)
        )
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
        text = "❄️ ${Strings.get("freeze_buy", lang)} · " +
            "${com.aistudio.medlingua.dzmed.domain.gamification.FREEZE_COST_GEMS} 💎",
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

/** Minuscules + accents retirés : « Ostéologie » se trouve en tapant « osteo ». */
private fun normalize(value: String): String {
    val decomposed = Normalizer.normalize(value, Normalizer.Form.NFD)
    return decomposed.replace(Regex("\\p{Mn}+"), "").lowercase().trim()
}

private fun moduleTitle(info: InitialData.ModuleInfo, lang: Language): String = when (lang) {
    Language.ARABIC -> info.titleAr
    Language.ENGLISH -> info.titleEn
    Language.FRENCH -> info.titleFr
}

private fun moduleIcon(moduleId: String): String =
    InitialData.modulesList.firstOrNull { it.id == moduleId }?.icon ?: "📘"
