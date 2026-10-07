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
import androidx.compose.ui.draw.alpha
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
    onBuyFreeze: () -> Unit = {},
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
                            text = "${uiState.userType.label(lang)} ${uiState.medYear?.let { "• ${it.label(lang)}" } ?: ""}",
                            color = Color(0xFFA5F4AC),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = Strings.get("medical_level_line", lang)
                                .replace(
                                    "{level}",
                                    "${uiState.userKnowledgeLevel.emoji} ${uiState.userKnowledgeLevel.label(lang)}"
                                ),
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // ===== Duolingo Lot 2 : objectif du jour + série + congélation =====
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val now = System.currentTimeMillis()
            val goal = com.example.domain.gamification.ProgressionManager.goalProgress(stats, now)
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
                    Column {
                        Text(
                            text = Strings.get("goal_title", lang),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF1E2922)
                        )
                        Text(
                            text = "${goal.today} / ${goal.goal} ${Strings.get("stat_xp", lang)}",
                            fontSize = 13.sp,
                            color = Color(0xFF64748B),
                            modifier = Modifier.padding(top = 2.dp)
                        )
                        Text(
                            text = "🔥 ${stats.streakDays} ${Strings.get("stat_streak", lang)} · ❄️ ${stats.streakFreezeCount}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFEA580C),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    com.example.ui.components.GoalRing(pct = goal.pct, done = goal.done)
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val canBuy = stats.gems >= com.example.domain.gamification.FREEZE_COST_GEMS
                    Text(
                        text = "❄️ ${Strings.get("freeze_buy", lang)} · " +
                            "${com.example.domain.gamification.FREEZE_COST_GEMS} 💎",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (canBuy) Color(0xFF0284C7) else Color(0xFF90A4AE),
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (canBuy) Color(0xFFE0F2FE) else Color(0xFFF1F5F9))
                            .clickable(enabled = canBuy) { onBuyFreeze() }
                            .padding(horizontal = 12.dp, vertical = 7.dp)
                    )
                }
            }
        }

        // ===== Duolingo Lot 2 : grille des 12 trophées =====
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = Strings.get("trophies_title", lang),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF1E2922)
                    )
                    Text(
                        text = "${uiState.trophies.size}/12",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFB45309)
                    )
                }

                com.example.domain.gamification.TrophyId.entries.chunked(4).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        row.forEach { trophy ->
                            val won = uiState.trophies.contains(trophy.name)
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(46.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (won) Color(0xFFFFF3C4) else Color(0xFFF1F5F9)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = trophy.icon,
                                        fontSize = 20.sp,
                                        modifier = if (won) Modifier else Modifier.alpha(0.35f)
                                    )
                                }
                                Text(
                                    text = Strings.get(trophy.titleKey, lang),
                                    fontSize = 9.sp,
                                    fontWeight = if (won) FontWeight.Bold else FontWeight.Normal,
                                    color = if (won) Color(0xFF1E2922) else Color(0xFF90A4AE),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }
                        }
                        repeat(4 - row.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
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
                            text = Strings.get("cat_title", lang),
                            color = Color(0xFF2DD4BF),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = if (uiState.diagnosticResult != null)
                                Strings.get("cat_level_result", lang)
                                    .replace(
                                        "{level}",
                                        "${uiState.diagnosticResult.level.emoji} ${uiState.diagnosticResult.level.label(lang)}"
                                    )
                                    .replace("{score}", "${uiState.diagnosticResult.score}")
                            else
                                Strings.get("cat_eval", lang),
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (uiState.diagnosticResult != null)
                                Strings.get("cat_recommended", lang)
                                    .replace("{module}", uiState.diagnosticResult.startModuleName)
                            else
                                Strings.get("cat_desc", lang),
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
                        text = if (uiState.diagnosticResult != null)
                            Strings.get("retry_label", lang)
                        else
                            Strings.get("start_label", lang),
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
            // Colonne plutôt que Row(SpaceBetween) : la libellé + le sélecteur dépassent
            // la largeur de la carte côte à côte, et Row donne alors au troisième bouton
            // (English) une largeur nulle — il disparaissait de l'écran et de l'arbre
            // d'accessibilité. En pile, le sélecteur a toute la largeur de la carte.
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = Strings.get("app_language_title", lang),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF1E2922)
                )
                Text(
                    text = Strings.get("app_language_list", lang),
                    fontSize = 12.sp,
                    color = Color(0xFF78909C)
                )

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
                                text = Strings.get("offline_mode_title", lang),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(0xFF1E2922)
                            )
                            Text(
                                // The content is bundled in the local database from the first launch: this is the
    // real local footprint, not bytes "downloaded" from a server.
                            text = Strings.get("local_content", lang)
                                .replace("{size}", "%.2f".format(uiState.downloadedModules.sumOf { it.sizeMb }))
                                .replace("{count}", "${uiState.downloadedModules.size}"),
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
                            text = Strings.get("sqlite_active", lang),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )
                    }
                }

                Text(
                    text = Strings.get("offline_content_desc", lang),
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
                                    text = "~%.3f MB".format(module.estimatedSizeMb),
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
                                    contentDescription = Strings.get("download", lang),
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
                                text = Strings.get("notifications_card_title", lang),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(0xFF1E2922)
                            )
                            Text(
                                text = Strings.get("notifications_card_desc", lang),
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
                                text = Strings.get("active_label", lang),
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
                            Text(Strings.get("enable_notifications", lang), fontSize = 11.sp, fontWeight = FontWeight.Bold)
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
                                text = Strings.get("sm2_reminder_title", lang),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF263238)
                            )
                            Text(
                                text = Strings.get("sm2_reminder_desc", lang),
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
                                text = Strings.get("streak_reminder_title", lang),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF263238)
                            )
                            Text(
                                text = Strings.get("streak_reminder_desc", lang)
                                    .replace("{n}", "${stats.streakDays}"),
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
                                text = Strings.get("pearl_reminder_title", lang),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF263238)
                            )
                            Text(
                                text = Strings.get("pearl_reminder_desc", lang),
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
                        text = Strings.get("reminder_time_label", lang),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF37474F)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val scheduleTimes = listOf(
                            Triple(8, 0, Strings.get("time_morning", lang)),
                            Triple(13, 0, Strings.get("time_noon", lang)),
                            Triple(20, 0, Strings.get("time_evening", lang))
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
                        text = Strings.get("test_notif_label", lang),
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
                            Text(Strings.get("notif_review", lang), fontSize = 11.sp)
                        }

                        Button(
                            onClick = { onSendTestNotification("streak") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE65100)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("test_notif_streak_btn")
                        ) {
                            Text(
                                Strings.get("notif_streak", lang).replace("{n}", "${stats.streakDays}"),
                                fontSize = 11.sp
                            )
                        }

                        Button(
                            onClick = { onSendTestNotification("pearl") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00695C)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("test_notif_pearl_btn")
                        ) {
                            Text(Strings.get("notif_pearl", lang), fontSize = 11.sp)
                        }

                        Button(
                            onClick = { onSendTestNotification("level") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A148C)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("test_notif_level_btn")
                        ) {
                            Text(
                                Strings.get("notif_level", lang).replace("{n}", "${stats.highestUnlockedLevel()}"),
                                fontSize = 11.sp
                            )
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
                    text = Strings.get("learning_report_title", lang),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF1E2922)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    MetricBox(
                        title = Strings.get("metric_questions", lang),
                        value = "${stats.totalQuestionsAnswered}",
                        icon = "📝",
                        modifier = Modifier.weight(1f)
                    )
                    MetricBox(
                        title = Strings.get("metric_quizzes", lang),
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
                        title = Strings.get("metric_correct", lang),
                        value = "${stats.correctAnswersCount}",
                        icon = "✅",
                        modifier = Modifier.weight(1f)
                    )
                    MetricBox(
                        title = Strings.get("metric_accuracy", lang),
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
