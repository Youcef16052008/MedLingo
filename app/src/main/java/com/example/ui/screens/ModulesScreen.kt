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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.initial.InitialData
import com.example.data.local.entity.MedicalTermEntity
import com.example.ui.components.TermIllustrationView
import com.example.localization.Language
import com.example.localization.Strings
import com.example.ui.theme.AmberGold
import com.example.ui.theme.MedGreenDark
import com.example.ui.theme.MedGreenPrimary
import com.example.ui.viewmodel.UiState

@Composable
fun ModulesScreen(
    uiState: UiState,
    onModuleFilterChange: (String) -> Unit,
    onChapterFilterChange: (String) -> Unit = {},
    onSearchQueryChange: (String) -> Unit,
    onSpeak: (String) -> Unit,
    onToggleBookmark: (MedicalTermEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val lang = uiState.currentLanguage
    // 0 = Vue Figma Catalogue des Modules; 1 = Vue Dictionnaire / Lexique des Termes
    var selectedViewTab by remember { mutableIntStateOf(if (uiState.selectedModuleFilter != "All") 1 else 0) }

    val modulesList = listOf(
        "All" to Strings.get("all_modules", lang),
        "Anatomie" to "Anatomie 🦴",
        "Biochimie" to "Biochimie 🧬",
        "Biophysique" to "Biophysique 🧪",
        "Histologie" to "Histologie 🔬",
        "Physiologie" to "Physiologie ❤️",
        "Génétique" to "Génétique 🧬",
        "Terminologie Médicale" to "Terminologie 📙",
        "Anglais Médical" to "Anglais Médical 🩺",
        "Cytologie" to "Cytologie 🧫",
        "Informatique Médicale" to "Info Médicale 💻",
        "Embryologie" to "Embryologie 👶",
        "Microbiologie" to "Microbiologie 🦠",
        "Pharmacologie" to "Pharmacologie 💊",
        "Sémiologie Médicale" to "Sémiologie 🩺",
        "Anatomie Pathologique" to "Anatomie Patho 🫀"
    )

    // Dynamic chapter options based on active module filter
    val currentChapters = when (uiState.selectedModuleFilter) {
        "Anatomie" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول التشريح" else "Tous les chapitres"),
            "Ostéologie" to "🦴 Ostéologie",
            "Arthrologie" to "🔗 Arthrologie",
            "Myologie" to "💪 Myologie",
            "Neurologie" to "🧠 Neurologie"
        )
        "Biochimie" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول الكيمياء الحيوية" else "Tous les chapitres"),
            "Glucides et Métabolisme" to "🍞 Glucides",
            "Lipides et Lipoprotéines" to "🥑 Lipides",
            "Protéines et Acides Aminés" to "🥩 Protéines",
            "Acides Nucléiques et Génome" to "🧬 Acides Nucléiques",
            "Bioénergétique et Vitamines" to "⚡ Bioénergétique"
        )
        "Biophysique" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول الفيزياء الحيوية" else "Tous les chapitres"),
            "Mécanique des Fluides et Hémodynamique" to "🌊 Hémodynamique",
            "Optique Médicale et Vision" to "👁️ Optique & Vision",
            "Rayonnements et Radiobiologie" to "☢️ Rayonnements",
            "Électrophysiologie et Phénomènes de Membrane" to "⚡ Électrophysiologie",
            "Solutions Biologiques et Échanges" to "🧪 Solutions & Starling"
        )
        "Histologie" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول علم الأنسجة" else "Tous les chapitres"),
            "Tissus Épithéliaux" to "🧱 Épithéliums",
            "Tissus Conjonctifs et Cartilage/Os" to "🦴 Conjonctif & Os",
            "Tissu Musculaire" to "💪 Musculaire",
            "Tissu Nerveux" to "🧠 Nerveux",
            "Tissu Sanguin et Hématopoïèse" to "🩸 Sanguin"
        )
        "Physiologie" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول وظائف الأعضاء" else "Tous les chapitres"),
            "Physiologie Cardiovasculaire" to "❤️ Cardiovasculaire",
            "Physiologie Respiratoire" to "🫁 Respiratoire",
            "Physiologie Rénale" to "🩺 Rénal & SRAA",
            "Endocrinologie et Homéostasie" to "⚖️ Endocrinologie"
        )
        "Génétique" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع الفصول" else "Tous les chapitres"),
            "Structure de l'ADN & Chromatine" to "🧬 Structure ADN & Chromatine",
            "Réplication & Transcription" to "🔁 Réplication & Transcription",
            "Mutations & Hérédité Mendélienne" to "👨‍👩‍👧 Hérédité Mendélienne",
            "Anomalies Chromosomiques" to "🔬 Anomalies Chromosomiques"
        )
        "Terminologie Médicale" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع الفصول" else "Tous les chapitres"),
            "Préfixes et Suffixes" to "🔤 Préfixes & Suffixes",
            "Suffixes Chirurgicaux" to "🔪 Suffixes Chirurgicaux",
            "Sémiologie Cardio-Vasculaire" to "❤️ Sémiologie Cardio",
            "Sémiologie Respiratoire & Digestive" to "🩺 Sémiologie Digestive",
            "Grands Syndromes Cliniques" to "📋 Grands Syndromes"
        )
        "Anglais Médical" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع الفصول" else "Tous les chapitres"),
            "Communication clinique" to "🗣️ Communication",
            "Examen clinique" to "📋 Examen Clinique"
        )
        "Cytologie" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول علم الأحياء الخلوية" else "Tous les chapitres"),
            "Membrane Plasmique & Transports" to "🧱 Membrane & Transports",
            "Système Endomembranaire & Organites" to "📦 Organites & Golgi",
            "Cytosquelette & Motilité cellulaire" to "🏃 Cytosquelette & Moteurs",
            "Signalisation cellulaire & Apoptose" to "☠️ Apoptose & Signaux",
            "Noyau & Cycle Cellulaire" to "🧬 Noyau & CPN"
        )
        "Informatique Médicale" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول المعلوماتية الطبية" else "Tous les chapitres"),
            "Biostatistiques Fondamentales" to "📊 Biostatistiques (p-value, IC)",
            "Épidémiologie & Risque Clinique" to "📈 Épidémiologie (RR, OR)",
            "Dossier Patient Informatisé & SIH" to "📁 DMP & SIH",
            "Intelligence Artificielle en Santé" to "🤖 IA & Imagerie",
            "Sécurité des Données de Santé" to "🔒 Sécurité & RGPD"
        )
        "Embryologie" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول علم الأجنة" else "Tous les chapitres"),
            "Gamétogenèse & Fécondation" to "🥚 Fécondation & Capacitation",
            "Segmentation & Blastocyste" to "🍇 Morula & Blastocyste",
            "Neurulation & Crêtes Neurales" to "🧠 Neurulation & Crêtes",
            "Mésoderme & Somites" to "🦴 Somites & Mésoderme",
            "Placenta & Tératologie" to "🛡️ Placenta & Tératogenèse"
        )
        "Microbiologie" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول علم الأحياء الدقيقة" else "Tous les chapitres"),
            "Introduction" to "🔬 Introduction & Flores",
            "Bactériologie Générale" to "🧫 Bactériologie Générale",
            "Bactéries Pathogènes" to "🦠 Bactéries Pathogènes (SARM, BK)",
            "Virologie" to "🧬 Virologie & Rétrovirus",
            "Virologie Clinique" to "🧪 Virologie Clinique (Drift/Shift)",
            "Mycologie" to "🍄 Mycologie (Candida)",
            "Parasitologie" to "🪱 Parasitologie (Plasmodium)",
            "Antibiotiques et Résistance" to "💊 Antibiotiques & Résistances",
            "Diagnostic Microbiologique" to "🔍 Diagnostic & Hémocultures"
        )
        "Pharmacologie" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول علم الأدوية" else "Tous les chapitres"),
            "Pharmacocinétique" to "📈 Pharmacocinétique (ADME, t½, CYP)",
            "Pharmacodynamie" to "🎯 Pharmacodynamie (Agonistes, IT)",
            "Médicaments Cardiovasculaires" to "❤️ Cardiovasculaires (Bêta-bloquants, IEC, Diurétiques)",
            "Médicaments du SNC" to "🧠 Système Nerveux Central (Opioïdes, Benzos)",
            "Anti-inflammatoires et Analgésiques" to "🩹 AINS, Antalgiques & Corticoïdes",
            "Médicaments Digestifs" to "🫃 Gastro-entérologie (IPP, Antiacides)",
            "Médicaments Endocriniens" to "🧬 Endocrinologie (Insulines, Metformine)",
            "Pharmacologie Clinique et Toxicologie" to "⚠️ Toxicologie & Antidotes",
            "Formes Pharmaceutiques" to "💉 Voies d'administration"
        )
        "Sémiologie Médicale" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول السيميولوجيا الطبية" else "Tous les chapitres"),
            "Introduction à la Sémiologie" to "📋 Introduction (Signes vs Symptômes)",
            "Sémiologie Neurologique" to "🧠 Sémiologie Neurologique (Glasgow, Babinski)",
            "Sémiologie Cardiovasculaire" to "🫀 Cardiovasculaire (Angor, Dyspnée)",
            "Sémiologie Respiratoire" to "🫁 Respiratoire (Crépitants, Sibilants)",
            "Sémiologie Digestive" to "🫃 Digestive (Murphy, McBurney, Ascite)",
            "Sémiologie Endocrinienne" to "🧬 Endocrinienne (Chvostek, Trousseau)",
            "Sémiologie Gynécologique" to "🤰 Gynéco-Obstétrique (Prééclampsie)",
            "Sémiologie Dermatologique" to "🩹 Dermatologique (Pétéchies, Purpura)",
            "Sémiologie Rhumatologique" to "🦴 Rhumatologique (Lasègue)",
            "Sémiologie Urologique" to "🫘 Urologique (Giordano)",
            "Examen Clinique Général" to "🔍 Examen Général (Adénopathies)"
        )
        "Anatomie Pathologique" -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع فصول علم الأمراض" else "Tous les chapitres"),
            "Introduction à l'Anatomie Pathologique" to "🔬 Introduction & Biopsies",
            "Lésions Cellulaires et Mort Cellulaire" to "☠️ Nécroses & Apoptose",
            "Inflammation Chronique et Réparation" to "🩹 Granulomes, Cellules géantes & Fibrose",
            "Troubles Circulatoires" to "🩸 Thrombus & Triade de Virchow",
            "Pathologie Cardiovasculaire" to "❤️ Athérosclérose & Plaques",
            "Néoplasies - Généralités" to "🎗️ Néoplasies (Carcinomes, Sarcomes, TNM)",
            "Cancérogenèse et Oncologie Moléculaire" to "🧬 Gènes TP53 & Oncogènes",
            "Pathologie Digestive" to "🫃 Pathologie Digestive (Cirrhose)",
            "Techniques en Anatomie Pathologique" to "🧪 Techniques (IHC, Congélation, Rouge Congo)"
        )
        else -> listOf(
            "All" to (if (lang == Language.ARABIC) "جميع الفصول" else "Tous les chapitres (Tous modules)")
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8F6))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // ========================================================
        // 1. FIGMA MEDICAL TOP BAR & MODE SELECTOR
        // ========================================================
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth().testTag("modules_top_header")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE8F5E9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🩺", fontSize = 18.sp)
                        }
                        Column {
                            Text(
                                text = if (lang == Language.ARABIC) "المقررات والقاموس الطبي DZ" else "Modules & Syllabus Médical",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 15.sp,
                                color = Color(0xFF0F172A)
                            )
                            Text(
                                text = "PCEM1 • 8 Modules Fondamentaux • ${uiState.allTerms.size} Termes",
                                fontSize = 11.sp,
                                color = Color(0xFF64748B),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFE0F2FE))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Figma Med UI ✨",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0284C7)
                        )
                    }
                }

                // Segmented Switcher: [Catalogue des Modules] vs [Lexique des Termes]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFFF1F5F9))
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (selectedViewTab == 0) MedGreenDark else Color.Transparent)
                            .clickable { selectedViewTab = 0 }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.GridView,
                                contentDescription = null,
                                tint = if (selectedViewTab == 0) Color.White else Color(0xFF475569),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = if (lang == Language.ARABIC) "دليل المقررات (8)" else "Modules (8)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (selectedViewTab == 0) Color.White else Color(0xFF475569)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (selectedViewTab == 1) MedGreenDark else Color.Transparent)
                            .clickable { selectedViewTab = 1 }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = if (selectedViewTab == 1) Color.White else Color(0xFF475569),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = if (lang == Language.ARABIC) "قاموس المصطلحات" else "Lexique (${uiState.terms.size})",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (selectedViewTab == 1) Color.White else Color(0xFF475569)
                            )
                        }
                    }
                }
            }
        }

        // ========================================================
        // VIEW 0: FIGMA MEDICAL CATALOGUE (GRID OF MODULES)
        // ========================================================
        if (selectedViewTab == 0) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    // Clinical Faculty Banner
                    Card(
                        shape = RoundedCornerShape(18.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Brush.linearGradient(
                                        listOf(Color(0xFF004D40), Color(0xFF0D9488), Color(0xFF0284C7))
                                    )
                                )
                                .padding(16.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    text = if (lang == Language.ARABIC) "مقررات السنة الأولى طب بالجزائر" else "Syllabus 1ère Année Médecine (PCEM1)",
                                    color = Color(0xFFBBF7D0),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                                Text(
                                    text = if (lang == Language.ARABIC) "جميع التخصصات الطبية الأساسية مدمجة بالكامل" else "8 Modules Médicaux Fondamentaux",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Text(
                                    text = if (lang == Language.ARABIC)
                                        "تشريح • كيمياء حيوية • فيزياء حيوية • أنسجة • وظائف أعضاء • وراثة • مصطلحات • إنجليزية سريرية"
                                    else
                                        "Anatomie • Biochimie • Biophysique • Histologie • Physiologie • Génétique • Terminologie • Anglais Médical",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }

                items(InitialData.modulesList, key = { it.id }) { mod ->
                    val totalTermsInMod = InitialData.termsOfModule(mod.titleFr).size

                    FigmaModuleDetailedCard(
                        module = mod,
                        termsCount = totalTermsInMod,
                        currentLanguage = lang,
                        onOpenModuleTerms = {
                            onModuleFilterChange(mod.titleFr)
                            onChapterFilterChange("All")
                            selectedViewTab = 1
                        },
                        onChapterClick = { chapterName ->
                            onModuleFilterChange(mod.titleFr)
                            onChapterFilterChange(chapterName)
                            selectedViewTab = 1
                        }
                    )
                }
            }
        } else {
            // ========================================================
            // VIEW 1: LEXIQUE CLINIQUE & DICTIONNAIRE (SEARCH & FILTERS)
            // ========================================================

            // Search TextField
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("modules_search_input"),
                placeholder = {
                    Text(
                        text = Strings.get("search_hint", lang),
                        color = Color(0xFF94A3B8),
                        fontSize = 13.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MedGreenDark
                    )
                },
                trailingIcon = {
                    if (uiState.searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = Color(0xFF64748B)
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = MedGreenDark,
                    unfocusedIndicatorColor = Color(0xFFCBD5E1)
                )
            )

            // Horizontal Module Filter Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                modulesList.forEach { (moduleKey, label) ->
                    val isSelected = uiState.selectedModuleFilter == moduleKey
                    val countForModule = if (moduleKey == "All") uiState.allTerms.size else uiState.allTerms.count {
                        it.module.equals(moduleKey, ignoreCase = true) ||
                                it.module.contains(moduleKey, ignoreCase = true) ||
                                moduleKey.contains(it.module, ignoreCase = true)
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) MedGreenDark else Color.White)
                            .border(
                                1.dp,
                                if (isSelected) MedGreenDark else Color(0xFFCBD5E1),
                                RoundedCornerShape(20.dp)
                            )
                            .clickable { onModuleFilterChange(moduleKey) }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                            .testTag("filter_chip_$moduleKey")
                    ) {
                        Text(
                            text = "$label ($countForModule)",
                            color = if (isSelected) Color.White else Color(0xFF334155),
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }

            // Sub-filter for Module Chapters
            if (currentChapters.size > 1) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    currentChapters.forEach { (chapterKey, label) ->
                        val isSelected = uiState.selectedChapterFilter == chapterKey
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) Color(0xFF1B5E20) else Color(0xFFE8F5E9))
                                .border(
                                    1.dp,
                                    if (isSelected) Color(0xFF1B5E20) else Color(0xFFC8E6C9),
                                    RoundedCornerShape(16.dp)
                                )
                                .clickable { onChapterFilterChange(chapterKey) }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = label,
                                color = if (isSelected) Color.White else Color(0xFF1B5E20),
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    }
                }
            }

            // Terms Count Label
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${uiState.terms.size} ${if (lang == Language.ARABIC) "مصطلحات مطابقة" else "termes médicaux affichés"}",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                    fontWeight = FontWeight.SemiBold
                )

                if (uiState.selectedModuleFilter != "All" || uiState.selectedChapterFilter != "All") {
                    Text(
                        text = "Réinitialiser les filtres ↺",
                        fontSize = 11.sp,
                        color = MedGreenDark,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            onModuleFilterChange("All")
                            onChapterFilterChange("All")
                            onSearchQueryChange("")
                        }
                    )
                }
            }

            // Terms List or Empty State
            if (uiState.terms.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(text = "🩺", fontSize = 44.sp)
                        Text(
                            text = if (lang == Language.ARABIC) "لا توجد مصطلحات مطابقة" else "Aucun terme médical trouvé",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF1E2922)
                        )
                        Text(
                            text = if (lang == Language.ARABIC) "جرّب تغيير التصفية أو المقرر المحدد" else "Vérifiez vos filtres de recherche ou sélectionnez un autre module.",
                            fontSize = 12.sp,
                            color = Color(0xFF64748B),
                            textAlign = TextAlign.Center
                        )
                        Button(
                            onClick = {
                                onModuleFilterChange("All")
                                onChapterFilterChange("All")
                                onSearchQueryChange("")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MedGreenDark),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(if (lang == Language.ARABIC) "عرض جميع المقررات" else "Afficher tous les modules", fontSize = 12.sp)
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(uiState.terms, key = { it.id }) { term ->
                        MedicalTermCard(
                            term = term,
                            onSpeak = onSpeak,
                            onToggleBookmark = onToggleBookmark,
                            currentLanguage = lang
                        )
                    }
                }
            }
        }
    }
}

// ========================================================
// FIGMA CLINICAL MODULE DETAILED CARD
// ========================================================
@Composable
private fun FigmaModuleDetailedCard(
    module: InitialData.ModuleInfo,
    termsCount: Int,
    currentLanguage: Language,
    onOpenModuleTerms: () -> Unit,
    onChapterClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("figma_module_card_${module.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header Row: Icon, Titles, Badges
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(module.colorHex).copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = module.icon, fontSize = 26.sp)
                    }

                    Column {
                        Text(
                            text = if (currentLanguage == Language.ARABIC) module.titleAr else module.titleFr,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(0xFF0F172A)
                        )
                        Text(
                            text = "${module.titleEn} • ${module.chaptersCount} chapitres",
                            fontSize = 11.sp,
                            color = Color(0xFF64748B),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(module.colorHex).copy(alpha = 0.14f))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "$termsCount termes",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(module.colorHex)
                    )
                }
            }

            // Chapters Preview list
            val chapters = InitialData.chaptersOfModule(module.titleFr)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                chapters.forEach { chap ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFF8FAFC))
                            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                            .clickable { onChapterClick(chap) }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = chap,
                            fontSize = 10.sp,
                            color = Color(0xFF334155),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Progress bar
            LinearProgressIndicator(
                progress = { module.progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = Color(module.colorHex),
                trackColor = Color(0xFFF1F5F9)
            )

            // Direct Actions Row
            Button(
                onClick = onOpenModuleTerms,
                colors = ButtonDefaults.buttonColors(containerColor = Color(module.colorHex)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .testTag("explore_module_${module.id}")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Consulter le module ($termsCount termes)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(text = "→", fontSize = 14.sp, color = Color.White)
                }
            }
        }
    }
}

// ========================================================
// FIGMA CLINICAL MEDICAL TERM CARD
// ========================================================
@Composable
fun MedicalTermCard(
    term: MedicalTermEntity,
    onSpeak: (String) -> Unit,
    onToggleBookmark: (MedicalTermEntity) -> Unit,
    currentLanguage: Language
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .testTag("term_card_${term.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Module Tag & Bookmark
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE8F5E9))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${term.module} • ${term.chapter}",
                        color = MedGreenDark,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { onToggleBookmark(term) },
                        modifier = Modifier.size(32.dp).testTag("bookmark_btn_${term.id}")
                    ) {
                        Icon(
                            imageVector = if (term.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (term.isBookmarked) AmberGold else Color(0xFF94A3B8)
                        )
                    }
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color(0xFF64748B),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Main English Term + Speaker
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = term.termEn,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0F172A)
                    )
                    if (term.ipaPhonetic.isNotBlank()) {
                        Text(
                            text = term.ipaPhonetic,
                            fontSize = 12.sp,
                            color = Color(0xFF00796B),
                            fontStyle = FontStyle.Italic
                        )
                    }
                }

                FilledIconButton(
                    onClick = { onSpeak(term.termEn) },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFFE8F5E9)),
                    modifier = Modifier.size(38.dp).testTag("speak_btn_${term.id}")
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "Listen",
                        tint = MedGreenDark,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // French & Arabic translations row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🇫🇷 ${term.termFr}",
                    fontSize = 13.sp,
                    color = Color(0xFF334155),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "🇸🇦 ${term.termAr}",
                    fontSize = 13.sp,
                    color = Color(0xFF0D532C),
                    fontWeight = FontWeight.Bold
                )
            }

            // Etymology pill
            if (term.etymology.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF1F5F9))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "🏛️ ${term.etymology}",
                        fontSize = 10.sp,
                        color = Color(0xFF475569)
                    )
                }
            }

            // Concise Definition
            Text(
                text = if (currentLanguage == Language.ARABIC) term.definitionAr else term.definitionFr,
                fontSize = 12.sp,
                color = Color(0xFF334155),
                lineHeight = 17.sp
            )

            // ========================================================
            // EXPANDED CLINICAL DETAILS (FIGMA DRAWER ACCORDION)
            // ========================================================
            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    HorizontalDivider(color = Color(0xFFE2E8F0))
                    TermIllustrationView(term = term, language = currentLanguage)

                    // 🩺 High-Yield Clinical Pearl
                    if (term.clinicalPearl.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFE0F2FE))
                                .border(1.dp, Color(0xFFBAE6FD), RoundedCornerShape(12.dp))
                                .padding(12.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "🩺 PERLE CLINIQUE & SÉMÉIOLOGIE",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 11.sp,
                                    color = Color(0xFF0369A1)
                                )
                                Text(
                                    text = term.clinicalPearl,
                                    fontSize = 12.sp,
                                    color = Color(0xFF0C4A6E),
                                    lineHeight = 17.sp
                                )
                            }
                        }
                    }

                    // 💡 Mnemonic Trick
                    if (term.mnemonic.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFFEF3C7))
                                .border(1.dp, Color(0xFFFDE68A), RoundedCornerShape(12.dp))
                                .padding(12.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "💡 MNÉMOTECHNIQUE D'EXAMEN",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = Color(0xFF92400E)
                                )
                                Text(
                                    text = term.mnemonic,
                                    fontSize = 12.sp,
                                    color = Color(0xFF78350F),
                                    lineHeight = 17.sp
                                )
                            }
                        }
                    }

                    // 📋 Clinical Context Example
                    if (term.exampleEn.isNotBlank() || term.exampleFr.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFF8FAFC))
                                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                                .padding(12.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "📋 EXEMPLE D'USAGE CLINIQUE",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    color = Color(0xFF64748B)
                                )
                                Text(
                                    text = "“${term.exampleEn}”",
                                    fontSize = 11.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color(0xFF0F172A)
                                )
                                if (term.exampleFr.isNotBlank()) {
                                    Text(
                                        text = "🇫🇷 ${term.exampleFr}",
                                        fontSize = 11.sp,
                                        color = Color(0xFF475569)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
