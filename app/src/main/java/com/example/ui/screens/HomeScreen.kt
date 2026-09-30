package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.initial.InitialData
import com.example.localization.Language
import com.example.localization.Strings
import com.example.ui.components.LanguageSelector
import com.example.ui.theme.MedGreenDark
import com.example.ui.theme.MedGreenPrimary
import com.example.ui.viewmodel.UiState

@Composable
fun HomeScreen(
    uiState: UiState,
    onLanguageChange: (Language) -> Unit,
    onToggleOnline: () -> Unit,
    onNavigateToFlashcards: () -> Unit,
    onNavigateToModules: (String?) -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToLevels: () -> Unit = {},
    onTestNotification: (String) -> Unit = {},
    onNavigateToFlashcardsWithModule: (String) -> Unit = { onNavigateToFlashcards() },
    onStartDiagnostic: () -> Unit = {}
) {
    val lang = uiState.currentLanguage
    val userStats = uiState.userStats
    var selectedCategoryTab by remember { mutableStateOf("all") }

    val pearlTerm = remember(uiState.terms) {
        uiState.terms.firstOrNull { it.clinicalPearl.isNotBlank() && it.mnemonic.isNotBlank() }
            ?: uiState.terms.firstOrNull()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7F5))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // ========================================================
        // 1. MEDICAL TOP BAR (HOSPITAL BADGE & RESIDENCY HEADER)
        // ========================================================
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
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(listOf(Color(0xFF0A5C36), Color(0xFF00796B)))
                        )
                        .border(2.dp, Color(0xFF80CBC4), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🩺", fontSize = 22.sp)
                }

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = if (lang == Language.ARABIC) "د. طالب طب خارجي (PCEM1)" else "Dr. Externe en Médecine",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF0F172A)
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFFDCFCE7))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "🇩🇿 DZ",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF15803D)
                            )
                        }
                    }
                    Text(
                        text = if (lang == Language.ARABIC) "كلية الطب • السداسي الأول" else "Faculté de Médecine • PCEM1",
                        fontSize = 11.sp,
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Online/Offline Clinical Status Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (uiState.isOnline) Color(0xFFE8F5E9) else Color(0xFFFFF3E0))
                        .clickable { onToggleOnline() }
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                        .testTag("online_toggle_btn")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = if (uiState.isOnline) Icons.Default.Wifi else Icons.Default.WifiOff,
                            contentDescription = null,
                            tint = if (uiState.isOnline) Color(0xFF2E7D32) else Color(0xFFE65100),
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = if (uiState.isOnline) Strings.get("online", lang) else Strings.get("offline", lang),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (uiState.isOnline) Color(0xFF2E7D32) else Color(0xFFE65100)
                        )
                    }
                }

                // Trilingual Selector
                LanguageSelector(
                    currentLanguage = lang,
                    onLanguageSelected = onLanguageChange
                )
            }
        }

        // ========================================================
        // 2. CLINICAL HERO BANNER (Figma Medical UI Style)
        // ========================================================
        Card(
            shape = RoundedCornerShape(22.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.fillMaxWidth().testTag("medical_hero_banner")
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_medical_faculty),
                    contentDescription = "Medical Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // High-contrast clinical gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xF0004D40),
                                    Color(0xD000695C),
                                    Color(0x800284C7)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0x33FFFFFF))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "FACULTÉ DE MÉDECINE DZ • 2026",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }

                        Text(
                            text = "${InitialData.modulesList.size} Modules PCEM1 🫀",
                            color = Color(0xFFBBF7D0),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Column {
                        Text(
                            text = "MedLingua DZ 🩺",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = if (lang == Language.ARABIC)
                                "المرجع الشامل في الإنجليزية الطبية الأكاديمية والسريرية"
                            else
                                "L'anglais médical académique & clinique pour carabins algériens",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Anat • Biochim • Biophys • Histo • Physio • Génét • Termino • Anglais",
                            color = Color(0xFFE2E8F0),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }

        // ========================================================
        // 3. CLINICAL VITALS MONITOR (TELEMETRY DASHBOARD)
        // ========================================================
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = "📊", fontSize = 16.sp)
                        Text(
                            text = if (lang == Language.ARABIC) "مؤشرات المداومة السريرية" else "Constantes d'apprentissage clinique",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Color(0xFF1E2922)
                        )
                    }

                    Text(
                        text = "Algorithme SM-2 Actif",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF00796B)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ClinicalVitalItem(
                        icon = "🔥",
                        value = "${userStats.streakDays}j",
                        label = if (lang == Language.ARABIC) "أيام متتالية" else "Série continue",
                        bgColor = Color(0xFFFFF7ED),
                        textColor = Color(0xFFC2410C),
                        modifier = Modifier.weight(1f)
                    )
                    ClinicalVitalItem(
                        icon = "📚",
                        value = "${uiState.allTerms.size}",
                        label = if (lang == Language.ARABIC) "مصطلحات مدرجة" else "Termes actifs",
                        bgColor = Color(0xFFECFDF5),
                        textColor = Color(0xFF047857),
                        modifier = Modifier.weight(1f)
                    )
                    ClinicalVitalItem(
                        icon = "⭐",
                        value = "${userStats.totalPoints}",
                        label = if (lang == Language.ARABIC) "نقاط التفوق" else "Points cumulés",
                        bgColor = Color(0xFFFEF3C7),
                        textColor = Color(0xFFB45309),
                        modifier = Modifier.weight(1f)
                    )
                    ClinicalVitalItem(
                        icon = "🎯",
                        value = "${userStats.accuracyPercentage}%",
                        label = if (lang == Language.ARABIC) "الدقة السريرية" else "Précision",
                        bgColor = Color(0xFFEFF6FF),
                        textColor = Color(0xFF1D4ED8),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // ========================================================
        // 3.5 ADAPTIVE DIAGNOSTIC & PLACEMENT TEST CARD (CAT)
        // ========================================================
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, Color(0xFF00897B).copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                .clickable { onStartDiagnostic() }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF004D40)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🎯", fontSize = 24.sp)
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "TEST DE PLACEMENT ADAPTATIF",
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = Color(0xFF2DD4BF),
                                letterSpacing = 1.sp
                            )
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFF59E0B)
                            ) {
                                Text(
                                    text = "CAT",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF78350F),
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                )
                            }
                        }

                        Text(
                            text = if (uiState.diagnosticResult != null)
                                "${uiState.diagnosticResult.level.emoji} ${uiState.diagnosticResult.level.labelFr} (${uiState.diagnosticResult.score} pts)"
                            else
                                "Évaluez votre niveau en 5 à 10 questions",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Color.White
                        )
                        Text(
                            text = if (uiState.diagnosticResult != null)
                                "Module recommandé : ${uiState.diagnosticResult.startModuleName} • Refaire le test"
                            else
                                "Algorithme d'orientation vers votre cursus sur-mesure",
                            fontSize = 11.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFF00897B)
                ) {
                    Text(
                        text = if (uiState.diagnosticResult != null) "Refaire" else "Tester",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // ========================================================
        // 4. SCHEDULED DAILY SPACED REPETITION (SM-2) CARD
        // ========================================================
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBEB)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFFDE68A), RoundedCornerShape(20.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFEF08A)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🃏", fontSize = 24.sp)
                    }

                    Column {
                        Text(
                            text = Strings.get("daily_review_title", lang),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF92400E)
                        )
                        Text(
                            text = "6 ${Strings.get("flashcards_due", lang)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color(0xFF451A03)
                        )
                        Text(
                            text = "🦴 Anat (3) • 🧬 Biochim (2) • ❤️ Physio (1)",
                            fontSize = 11.sp,
                            color = Color(0xFF78350F)
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Notification Test Bell Button
                    IconButton(
                        onClick = { onTestNotification("review") },
                        modifier = Modifier.size(36.dp).testTag("home_notif_bell_btn")
                    ) {
                        Text(text = "🔔", fontSize = 18.sp)
                    }

                    Button(
                        onClick = onNavigateToFlashcards,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD97706)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("home_review_btn")
                    ) {
                        Text(
                            text = Strings.get("review_now", lang),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }

        // ========================================================
        // 5. HIGH-YIELD CLINICAL PEARL OF THE DAY (Vignette Clinique)
        // ========================================================
        pearlTerm?.let { term ->
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth().testTag("clinical_pearl_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFE0F2FE))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "🩺 PERLE CLINIQUE DU JOUR",
                                    color = Color(0xFF0369A1),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFF1F5F9))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "${term.module} • ${term.chapter}",
                                fontSize = 10.sp,
                                color = Color(0xFF475569),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = term.termFr,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(0xFF0F172A)
                        )
                        Text(
                            text = "(${term.termEn})",
                            fontStyle = FontStyle.Italic,
                            fontSize = 14.sp,
                            color = Color(0xFF0284C7)
                        )
                    }

                    Text(
                        text = term.clinicalPearl,
                        fontSize = 12.sp,
                        color = Color(0xFF334155),
                        lineHeight = 18.sp
                    )

                    if (term.mnemonic.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFFEF3C7))
                                .border(1.dp, Color(0xFFFDE68A), RoundedCornerShape(10.dp))
                                .padding(10.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(text = "💡", fontSize = 14.sp)
                                Text(
                                    text = term.mnemonic,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF92400E)
                                )
                            }
                        }
                    }
                }
            }
        }

        // ========================================================
        // 6. 6-LEVEL PROGRESSIVE LEARNING PYRAMID ENTRY CARD
        // ========================================================
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToLevels() }
                .testTag("home_pyramid_card")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE8F5E9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🧠", fontSize = 20.sp)
                        }

                        Column {
                            Text(
                                text = Strings.get("learning_pyramid_title", lang),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = MedGreenDark
                            )
                            val currentLvl = userStats.highestUnlockedLevel()
                            Text(
                                text = "Niveau actif : L$currentLvl • Se débloque à ≥70% 🔒",
                                fontSize = 11.sp,
                                color = Color(0xFF5A6B60)
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = MedGreenDark
                    )
                }

                // Mini Pyramid Tiers Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val icons = listOf("📖", "🧩", "💬", "📝", "📄", "🏥")
                    for (lvl in 1..6) {
                        val isUnlocked = userStats.isLevelUnlocked(lvl)
                        val score = userStats.getScoreForLevel(lvl)
                        val isMastered = score >= 70

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when {
                                            isMastered -> Color(0xFF16A34A)
                                            isUnlocked -> Color(0xFFFFF8E1)
                                            else -> Color(0xFFF1F5F9)
                                        }
                                    )
                                    .border(
                                        1.dp,
                                        if (isMastered) Color(0xFF16A34A) else if (isUnlocked) Color(0xFFFFD54F) else Color(0xFFCBD5E1),
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (isUnlocked) icons[lvl - 1] else "🔒",
                                    fontSize = 14.sp
                                )
                            }
                            Text(
                                text = "L$lvl",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isMastered) Color(0xFF16A34A) else if (isUnlocked) Color(0xFF1E2922) else Color(0xFF94A3B8)
                            )
                        }
                    }
                }
            }
        }

        // ========================================================
        // 7. OFFICIAL MEDICAL MODULES GRID (7 MODULES PCEM1)
        // ========================================================
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = if (lang == Language.ARABIC) "🏛️ المقررات الطبية الرسمية (PCEM1)" else "🏛️ Modules Médicaux Officiels (PCEM1)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = if (lang == Language.ARABIC) "المنهاج الكامل للسنة الأولى طب بالجزائر" else "Programme officiel de 1ère Année Médecine Algérie",
                        fontSize = 11.sp,
                        color = Color(0xFF64748B)
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE2E8F0))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${InitialData.modulesList.size} modules",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF334155)
                    )
                }
            }

            // Category Filter Pills
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val categoryTabs = listOf(
                    "all" to (if (lang == Language.ARABIC) "الكل (8)" else "Tous les modules (8)"),
                    "morpho" to (if (lang == Language.ARABIC) "مورفولوجيا (تشريح، أنسجة)" else "Morphologie (Anat, Histo)"),
                    "bio" to (if (lang == Language.ARABIC) "بيولوجيا وفيزياء" else "Biochimie & Biophysique"),
                    "clinical" to (if (lang == Language.ARABIC) "سريري ولغات" else "Physio & Anglais Médical")
                )

                categoryTabs.forEach { (catKey, label) ->
                    val isSelected = selectedCategoryTab == catKey
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) MedGreenDark else Color.White)
                            .border(
                                1.dp,
                                if (isSelected) MedGreenDark else Color(0xFFCBD5E1),
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { selectedCategoryTab = catKey }
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

            // Filtered modules list
            val filteredModules = remember(selectedCategoryTab) {
                when (selectedCategoryTab) {
                    "morpho" -> InitialData.modulesList.filter { it.id in listOf("anat", "histo", "embryo", "anapath") }
                    "bio" -> InitialData.modulesList.filter { it.id in listOf("biochim", "biophys", "genet", "cytol", "microbio") }
                    "clinical" -> InitialData.modulesList.filter { it.id in listOf("physio", "termino", "clinical_en", "info_med", "pharmaco", "semio") }
                    else -> InitialData.modulesList
                }
            }

            // Cards for every module
            filteredModules.forEach { module ->
                FigmaMedicalModuleCard(
                    module = module,
                    currentLanguage = lang,
                    totalTermsInModule = InitialData.termsOfModule(module.titleFr).size,
                    onExploreClick = { onNavigateToModules(module.titleFr) },
                    onFlashcardsClick = { onNavigateToFlashcardsWithModule(module.titleFr) }
                )
            }
        }
    }
}

@Composable
private fun ClinicalVitalItem(
    icon: String,
    value: String,
    label: String,
    bgColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(bgColor)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(text = icon, fontSize = 16.sp)
            Text(
                text = value,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                color = textColor
            )
            Text(
                text = label,
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium,
                color = textColor.copy(alpha = 0.85f),
                maxLines = 1
            )
        }
    }
}

@Composable
private fun FigmaMedicalModuleCard(
    module: InitialData.ModuleInfo,
    currentLanguage: Language,
    totalTermsInModule: Int,
    onExploreClick: () -> Unit,
    onFlashcardsClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("module_card_${module.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(module.colorHex).copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = module.icon, fontSize = 24.sp)
                    }

                    Column {
                        Text(
                            text = if (currentLanguage == Language.ARABIC) module.titleAr else module.titleFr,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
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
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(module.colorHex).copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "$totalTermsInModule termes",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(module.colorHex)
                    )
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

            // Direct Actions Row (Figma Medical Quick Actions)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onExploreClick,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f).height(36.dp)
                ) {
                    Text(
                        text = "📖 Consulter",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF334155)
                    )
                }

                Button(
                    onClick = onFlashcardsClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(module.colorHex)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f).height(36.dp)
                ) {
                    Text(
                        text = "🃏 Flashcards",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
