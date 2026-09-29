package com.example.ui.screens

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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.initial.InitialData
import com.example.localization.Language
import com.example.localization.Strings
import com.example.ui.components.LanguageSelector
import com.example.ui.theme.AmberGold
import com.example.ui.theme.MedGreenDark
import com.example.ui.theme.MedGreenPrimary
import com.example.ui.viewmodel.UiState

import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.foundation.horizontalScroll

@Composable
fun StatsProfileScreen(
    uiState: UiState,
    onLanguageChange: (Language) -> Unit,
    onDownloadModule: (InitialData.ModuleInfo) -> Unit,
    onToggleDailyReminder: (Boolean) -> Unit = {},
    onToggleStreakReminder: (Boolean) -> Unit = {},
    onToggleClinicalPearl: (Boolean) -> Unit = {},
    onSetReminderTime: (Int, Int) -> Unit = { _, _ -> },
    onSendTestNotification: (String) -> Unit = {},
    onRequestPermission: () -> Unit = {},
    onStartDiagnostic: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val lang = uiState.currentLanguage
    val stats = uiState.userStats

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8F6))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Student Profile Card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF1B5E20), Color(0xFF2E7D32))
                        )
                    )
                    .padding(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = uiState.userType.icon, fontSize = 34.sp)
                    }

                    Column {
                        Text(
                            text = uiState.userProfileName,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${uiState.userType.labelFr} ${uiState.medYear?.let { "• ${it.labelFr}" } ?: ""}",
                            color = Color(0xFFA5F4AC),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Niveau Médical : ${uiState.userKnowledgeLevel.emoji} ${uiState.userKnowledgeLevel.labelFr}",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Adaptive Placement Diagnostic Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, Color(0xFF00897B).copy(alpha = 0.6f), RoundedCornerShape(18.dp))
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
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF004D40)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🎯", fontSize = 22.sp)
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = "DIAGNOSTIC ADAPTATIF (CAT)",
                            color = Color(0xFF2DD4BF),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = if (uiState.diagnosticResult != null)
                                "Niveau : ${uiState.diagnosticResult.level.emoji} ${uiState.diagnosticResult.level.labelFr} (${uiState.diagnosticResult.score}/100)"
                            else
                                "Évaluer mon niveau médical",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (uiState.diagnosticResult != null)
                                "Recommandé : ${uiState.diagnosticResult.startModuleName} • Refaire"
                            else
                                "Test personnalisé de 5 à 10 questions",
                            color = Color(0xFF94A3B8),
                            fontSize = 11.sp
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFF00897B)
                ) {
                    Text(
                        text = if (uiState.diagnosticResult != null) "Refaire" else "Démarrer",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // Language Switcher section
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "🌐 Langue de l'application",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF1E2922)
                    )
                    Text(
                        text = "Arabe • Français • Anglais",
                        fontSize = 12.sp,
                        color = Color(0xFF78909C)
                    )
                }

                LanguageSelector(
                    currentLanguage = lang,
                    onLanguageSelected = onLanguageChange
                )
            }
        }

        // Offline Manager Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
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
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFE8F5E9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Storage,
                                contentDescription = null,
                                tint = MedGreenDark
                            )
                        }

                        Column {
                            Text(
                                text = "📴 Mode Hors-Ligne (Offline)",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(0xFF1E2922)
                            )
                            Text(
                                text = "Taille du cache : 14.2 MB",
                                fontSize = 12.sp,
                                color = Color(0xFF5A6B60)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFE8F5E9))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "SQLite Actif ✅",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )
                    }
                }

                Text(
                    text = "Modules disponibles pour téléchargement hors-ligne :",
                    fontSize = 12.sp,
                    color = Color(0xFF607D8B),
                    fontWeight = FontWeight.SemiBold
                )

                // List of all curriculum modules with download/cache status
                InitialData.modulesList.forEach { module ->
                    val isDownloaded = uiState.downloadedModules.any { it.moduleId == module.id }
                    val isDownloading = uiState.downloadingModuleId == module.id

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF9FAF9))
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(text = module.icon, fontSize = 20.sp)
                            Column {
                                Text(
                                    text = module.titleFr,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF263238)
                                )
                                Text(
                                    text = "~${module.estimatedSizeMb} MB",
                                    fontSize = 10.sp,
                                    color = Color(0xFF90A4AE)
                                )
                            }
                        }

                        if (isDownloading) {
                            Box(contentAlignment = Alignment.Center) {
                                CircularProgressIndicator(
                                    progress = { uiState.downloadProgress },
                                    modifier = Modifier.size(28.dp),
                                    color = MedGreenDark,
                                    strokeWidth = 3.dp
                                )
                            }
                        } else if (isDownloaded) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Downloaded",
                                    tint = Color(0xFF2E7D32),
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = Strings.get("downloaded", lang),
                                    fontSize = 11.sp,
                                    color = Color(0xFF2E7D32),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { onDownloadModule(module) },
                                modifier = Modifier.size(32.dp).testTag("download_btn_${module.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Download,
                                    contentDescription = "Download",
                                    tint = MedGreenDark
                                )
                            }
                        }
                    }
                }
            }
        }

        // Notifications & Study Reminders Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth().testTag("notifications_settings_card")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
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
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE8F5E9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.NotificationsActive,
                                contentDescription = "Notifications",
                                tint = MedGreenDark,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Column {
                            Text(
                                text = if (lang == Language.ARABIC) "🔔 التنبيهات ومواعيد المراجعة" else "🔔 Notifications & Rappels",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(0xFF1E2922)
                            )
                            Text(
                                text = if (lang == Language.ARABIC) "تذكير يومي للمراجعة المتباعدة (SM-2)" else "Rappels quotidiens de répétition espacée",
                                fontSize = 11.sp,
                                color = Color(0xFF78909C)
                            )
                        }
                    }

                    // Permission status pill
                    if (uiState.notificationPermissionGranted) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFE8F5E9))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Actif ✅",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32)
                            )
                        }
                    } else {
                        Button(
                            onClick = onRequestPermission,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE65100)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.height(32.dp).testTag("request_notification_btn")
                        ) {
                            Text("Activer 🔔", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                // Toggles List
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFFF9FAF9))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // SM-2 Review reminder toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (lang == Language.ARABIC) "مراجعة البطاقات المستحقة (SM-2)" else "Révision espacée quotidienne",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF263238)
                            )
                            Text(
                                text = if (lang == Language.ARABIC) "تنبيه عند حلول موعد تكرار المصطلحات" else "Alerte quand des flashcards arrivent à échéance",
                                fontSize = 11.sp,
                                color = Color(0xFF78909C)
                            )
                        }
                        Switch(
                            checked = uiState.notificationsEnabled,
                            onCheckedChange = onToggleDailyReminder,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = MedGreenDark
                            ),
                            modifier = Modifier.testTag("switch_daily_reminder")
                        )
                    }

                    // Streak protection toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (lang == Language.ARABIC) "حماية سلسلة الأيام (Streak)" else "Alerte de maintien de série",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF263238)
                            )
                            Text(
                                text = if (lang == Language.ARABIC) "تذكير مسائي لمنع انقطاع السلسلة" else "Rappel pour préserver votre série de 12 jours",
                                fontSize = 11.sp,
                                color = Color(0xFF78909C)
                            )
                        }
                        Switch(
                            checked = uiState.streakReminderEnabled,
                            onCheckedChange = onToggleStreakReminder,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = Color(0xFFE65100)
                            ),
                            modifier = Modifier.testTag("switch_streak_reminder")
                        )
                    }

                    // Clinical Pearl toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (lang == Language.ARABIC) "فائدة سريرية يومية (Clinical Pearl)" else "Perle clinique quotidienne",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF263238)
                            )
                            Text(
                                text = if (lang == Language.ARABIC) "مصطلح وملاحظة طبية ذات أهمية سريرية" else "Terme et mnémotechnique médicale à haut rendement",
                                fontSize = 11.sp,
                                color = Color(0xFF78909C)
                            )
                        }
                        Switch(
                            checked = uiState.clinicalPearlReminderEnabled,
                            onCheckedChange = onToggleClinicalPearl,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = MedGreenDark
                            ),
                            modifier = Modifier.testTag("switch_pearl_reminder")
                        )
                    }
                }

                // Reminder Schedule Time Selectors
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = if (lang == Language.ARABIC) "⏰ توقيت التذكير اليومي المفضل :" else "⏰ Heure de révision programmée :",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF37474F)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val scheduleTimes = listOf(
                            Triple(8, 0, "08:00 (Matin)"),
                            Triple(13, 0, "13:00 (Midi)"),
                            Triple(20, 0, "20:00 (Soir)")
                        )

                        scheduleTimes.forEach { (hour, min, label) ->
                            val isSelected = uiState.reminderHour == hour && uiState.reminderMinute == min
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isSelected) MedGreenDark else Color(0xFFF1F5F2))
                                    .border(
                                        1.dp,
                                        if (isSelected) MedGreenDark else Color(0xFFCFD8DC),
                                        RoundedCornerShape(12.dp)
                                    )
                                    .clickable { onSetReminderTime(hour, min) }
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else Color(0xFF37474F)
                                )
                            }
                        }
                    }
                }

                // Instant Interactive Test Trigger Buttons
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = if (lang == Language.ARABIC) "🧪 تجربة إشعار فوري على جهازك الآن :" else "🧪 Tester l'envoi d'une notification :",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF37474F)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { onSendTestNotification("review") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("test_notif_review_btn")
                        ) {
                            Text("📋 Révision due", fontSize = 11.sp)
                        }

                        Button(
                            onClick = { onSendTestNotification("streak") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE65100)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("test_notif_streak_btn")
                        ) {
                            Text("🔥 Série (12j)", fontSize = 11.sp)
                        }

                        Button(
                            onClick = { onSendTestNotification("pearl") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00695C)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("test_notif_pearl_btn")
                        ) {
                            Text("🩺 Perle clinique", fontSize = 11.sp)
                        }

                        Button(
                            onClick = { onSendTestNotification("level") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A148C)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("test_notif_level_btn")
                        ) {
                            Text("🏆 Niveau 2", fontSize = 11.sp)
                        }
                    }
                }
            }
        }

        // Learning Statistics Grid
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "📊 Bilan d'apprentissage",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF1E2922)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    MetricBox(
                        title = "Questions Répondues",
                        value = "${stats.totalQuestionsAnswered}",
                        icon = "📝",
                        modifier = Modifier.weight(1f)
                    )
                    MetricBox(
                        title = "Quiz Complétés",
                        value = "${stats.quizzesCompleted}",
                        icon = "🏆",
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    MetricBox(
                        title = "Réponses Correctes",
                        value = "${stats.correctAnswersCount}",
                        icon = "✅",
                        modifier = Modifier.weight(1f)
                    )
                    MetricBox(
                        title = "Précision Globale",
                        value = "${stats.accuracyPercentage}%",
                        icon = "🎯",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun MetricBox(
    title: String,
    value: String,
    icon: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF6F8F6))
            .border(1.dp, Color(0xFFE0E5E0), RoundedCornerShape(14.dp))
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = icon, fontSize = 20.sp)
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MedGreenDark
            )
            Text(
                text = title,
                fontSize = 11.sp,
                color = Color(0xFF5A6B60),
                fontWeight = FontWeight.Medium
            )
        }
    }
}
