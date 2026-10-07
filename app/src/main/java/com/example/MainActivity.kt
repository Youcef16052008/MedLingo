package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.localization.Strings
import com.example.ui.components.RewardPopup
import com.example.ui.screens.FlashcardsScreen
import com.example.ui.screens.ModulesScreen
import com.example.ui.screens.PathScreen
import com.example.ui.screens.PracticeScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.StatsProfileScreen
import com.example.ui.screens.PlacementDiagnosticScreen
import com.example.ui.theme.MedGreenDark
import com.example.ui.theme.MedGreenPrimary
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.MedLinguaViewModel

import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import com.example.ui.components.AudioPronunciationStudio
import com.example.ui.components.AppIntroOverlay
import com.example.ui.components.HeartsGemsTopBar
import com.example.ui.components.LeagueScreen
import com.example.ui.components.SuperPaywallDialog
import com.example.service.NotificationHelper

enum class MainDestination(
    val titleKey: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    PATH("nav_path", Icons.Filled.Map, Icons.Outlined.Map),
    PRACTICE("nav_practice", Icons.Filled.FitnessCenter, Icons.Outlined.FitnessCenter),
    LEAGUES("nav_leagues", Icons.Filled.EmojiEvents, Icons.Outlined.EmojiEvents),
    MODULES("nav_modules", Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
    PROFILE("nav_profile", Icons.Filled.Person, Icons.Outlined.Person)
}

class MainActivity : ComponentActivity() {
    private var pendingTargetScreen = mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        pendingTargetScreen.value = intent?.getStringExtra(NotificationHelper.EXTRA_TARGET_SCREEN)
        setContent {
            MyApplicationTheme {
                val darkTheme = androidx.compose.foundation.isSystemInDarkTheme()
                var introVisible by remember { mutableStateOf(true) }
                Box(modifier = Modifier.fillMaxSize()) {
                    MedLinguaAppContent(
                        initialTarget = pendingTargetScreen.value,
                        onTargetHandled = { pendingTargetScreen.value = null }
                    )
                    if (introVisible) {
                        AppIntroOverlay(
                            darkTheme = darkTheme,
                            onTimeout = { introVisible = false }
                        )
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        pendingTargetScreen.value = intent.getStringExtra(NotificationHelper.EXTRA_TARGET_SCREEN)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release the TTS engine when the app really goes away (kept alive
        // across configuration changes where isFinishing is false)
        if (isFinishing) {
            (application as? MedLinguaApp)?.ttsManager?.shutdown()
        }
    }
}

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun MedLinguaAppContent(
    viewModel: MedLinguaViewModel = viewModel(),
    initialTarget: String? = null,
    onTargetHandled: () -> Unit = {}
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var currentDestination by remember { mutableStateOf(MainDestination.PATH) }
    // Full-screen overlays (spec §5 : leçons hors onglets)
    var showQuiz by remember { mutableStateOf(false) }
    var showFlash by remember { mutableStateOf(false) }
    val lang = uiState.currentLanguage

    // Runtime Permission for Notifications (Android 13+ / Tiramisu)
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.setNotificationPermissionGranted(isGranted)
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        } else {
            viewModel.setNotificationPermissionGranted(
                NotificationHelper.hasNotificationPermission(context)
            )
        }
    }

    // Handle incoming deep link or notification target
    LaunchedEffect(initialTarget) {
        when (initialTarget) {
            NotificationHelper.TARGET_FLASHCARDS -> {
                currentDestination = MainDestination.PRACTICE
                showFlash = true
            }
            NotificationHelper.TARGET_QUIZ -> {
                currentDestination = MainDestination.PRACTICE
                showQuiz = true
            }
            NotificationHelper.TARGET_MODULES -> currentDestination = MainDestination.MODULES
            NotificationHelper.TARGET_HOME -> currentDestination = MainDestination.PATH
        }
        if (initialTarget != null) {
            onTargetHandled()
        }
    }

    // RTL support for Arabic, LTR for French & English
    val layoutDirection = if (lang.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

    // BackHandler: ferme le quiz/les flashcards, sinon revient au Parcours
    BackHandler(
        enabled = showQuiz || showFlash || currentDestination != MainDestination.PATH
    ) {
        when {
            showQuiz -> showQuiz = false
            showFlash -> showFlash = false
            else -> currentDestination = MainDestination.PATH
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                // Duolingo Phase 2 Top Bar - Streak, Gems, League (plus de cœurs)
                Column {
                    HeartsGemsTopBar(
                        streakDays = uiState.userStats.streakDays,
                        gems = uiState.userStats.gems,
                        isSuper = uiState.userStats.isSuperActive(),
                        leagueTier = uiState.userStats.leagueTier,
                        weeklyXp = uiState.userStats.weeklyXp,
                        onGemsClick = { viewModel.showSuperPaywall(true) },
                        onLeagueClick = { currentDestination = MainDestination.LEAGUES }
                    )
                }
            },
            bottomBar = {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 8.dp
                ) {
                    MainDestination.values().forEach { destination ->
                        val isSelected = currentDestination == destination
                        val label = Strings.get(destination.titleKey, lang)

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { currentDestination = destination },
                            icon = {
                                if (destination == MainDestination.PRACTICE) {
                                    // due cards come from the VM (repository clock), not from wall time
                                    val dueCount = uiState.dueFlashcardProgress.size
                                    BadgedBox(
                                        badge = {
                                            if (dueCount > 0) {
                                                Badge(
                                                    containerColor = Color(0xFFE65100),
                                                    contentColor = Color.White
                                                ) {
                                                    Text(text = dueCount.toString(), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                                }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                            contentDescription = label,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                } else {
                                    Icon(
                                        imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                        contentDescription = label,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            },
                            label = {
                                Text(
                                    text = label,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MedGreenDark,
                                selectedTextColor = MedGreenDark,
                                indicatorColor = Color(0xFFE8F5E9),
                                unselectedIconColor = Color(0xFF90A4AE),
                                unselectedTextColor = Color(0xFF90A4AE)
                            ),
                            modifier = Modifier.testTag("nav_item_${destination.name.lowercase()}")
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (currentDestination) {
                    MainDestination.PATH -> {
                        PathScreen(
                            uiState = uiState,
                            onOpenLesson = { moduleId, level ->
                                viewModel.startPathLesson(moduleId, level)
                                showQuiz = true
                            },
                            onBuyFreeze = { viewModel.buyFreeze() },
                            onOpenChests = { viewModel.openChestRewardPopup() }
                        )
                    }
                    MainDestination.PRACTICE -> {
                        PracticeScreen(
                            uiState = uiState,
                            onOpenReview = { showFlash = true },
                            onOpenQuiz = { showQuiz = true },
                            onOpenWeak = { showFlash = true },
                            onOpenExam = { showQuiz = true }
                        )
                    }
                    MainDestination.LEAGUES -> {
                        Column(modifier = Modifier.fillMaxSize()) {
                            androidx.compose.material3.TopAppBar(
                                title = { Text(Strings.get("league_title", lang).replace("{tier}", uiState.userStats.leagueTier), fontWeight = FontWeight.Bold) },
                                navigationIcon = {
                                    androidx.compose.material3.IconButton(onClick = { currentDestination = MainDestination.PATH }) {
                                        Text(Strings.get("close", lang), fontSize = 20.sp)
                                    }
                                }
                            )
                            LeagueScreen(
                                cohort = uiState.activeLeagueCohort,
                                members = uiState.leagueMembers,
                                lang = lang
                            )
                        }
                    }
                    MainDestination.MODULES -> {
                        ModulesScreen(
                            uiState = uiState,
                            onModuleFilterChange = { viewModel.setModuleFilter(it) },
                            onChapterFilterChange = { viewModel.setChapterFilter(it) },
                            onSearchQueryChange = { viewModel.setSearchQuery(it) },
                            onSpeak = { termText ->
                                val target = uiState.terms.firstOrNull { it.termEn.equals(termText, ignoreCase = true) || it.termFr.equals(termText, ignoreCase = true) } ?: uiState.terms.firstOrNull()
                                viewModel.toggleAudioStudio(true, target)
                                // onSpeak always carries termEn: force the English voice
                                viewModel.speak(termText, lang = "en")
                            },
                            onToggleBookmark = { viewModel.toggleBookmark(it) }
                        )
                    }
                    MainDestination.PROFILE -> {
                        StatsProfileScreen(
                            uiState = uiState,
                            onLanguageChange = { viewModel.setLanguage(it) },
                            onDownloadModule = { viewModel.downloadModule(it) },
                            onToggleDailyReminder = { viewModel.toggleDailyReminder(it) },
                            onToggleStreakReminder = { viewModel.toggleStreakReminder(it) },
                            onToggleClinicalPearl = { viewModel.toggleClinicalPearlReminder(it) },
                            onSetReminderTime = { h, m -> viewModel.setReminderTime(h, m) },
                            onSendTestNotification = { viewModel.sendTestNotification(it) },
                            onRequestPermission = {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                                }
                            },
                            onStartDiagnostic = { viewModel.openDiagnosticTest() },
                            onBuyFreeze = { viewModel.buyFreeze() }
                        )
                    }
                }

                // ---- Overlay : flashcards plein écran ----
                if (showFlash) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        FlashcardsScreen(
                            uiState = uiState,
                            onFlip = { viewModel.flipCard() },
                            onRate = { termId, quality ->
                                viewModel.rateFlashcard(termId, quality, uiState.terms.size)
                            },
                            onNext = { viewModel.nextFlashcard(uiState.terms.size) },
                            onPrev = { viewModel.prevFlashcard(uiState.terms.size) },
                            onSpeak = { termText ->
                                val target = uiState.terms.firstOrNull { it.termEn.equals(termText, ignoreCase = true) || it.termFr.equals(termText, ignoreCase = true) } ?: uiState.terms.firstOrNull()
                                viewModel.toggleAudioStudio(true, target)
                                // onSpeak always carries termEn: force the English voice
                                viewModel.speak(termText, lang = "en")
                            },
                            onBookmarkToggle = { viewModel.toggleBookmark(it) },
                            onChapterFilterChange = { viewModel.setChapterFilter(it) },
                            onModuleFilterChange = { viewModel.setModuleFilter(it) }
                        )
                    }
                }

                // ---- Overlay : quiz / leçon plein écran ----
                if (showQuiz) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        QuizScreen(
                            uiState = uiState,
                            onStartLevel = { level ->
                                viewModel.startLevelTrainingWithSpec(level)
                            },
                            onExitLevel = {
                                viewModel.exitLevelTraining()
                                showQuiz = false
                            },
                            onSelectLevelOption = { answer, correct, points ->
                                viewModel.selectLevelAnswer(answer, correct, points)
                            },
                            onNextLevelQuestion = { viewModel.nextLevelQuestionWithSpec() },
                            onRestartLevel = { viewModel.restartCurrentLevel() }
                        )
                    }
                }

                // ---- Pop-up de récompense (spec §11) ----
                uiState.lastReward?.let { reward ->
                    RewardPopup(
                        data = reward,
                        chestCount = uiState.userStats.pendingChests,
                        chestReward = uiState.pendingChestReward,
                        onOpenChest = { viewModel.openPendingChest() },
                        onContinue = { viewModel.dismissReward() },
                        lang = lang
                    )
                }

                // Adaptive Placement & Diagnostic Assessment Modal
                if (uiState.isDiagnosticVisible) {
                    PlacementDiagnosticScreen(
                        lang = uiState.currentLanguage,
                        onCompleteDiagnostic = { result ->
                            viewModel.applyDiagnosticResult(result)
                        },
                        onDismiss = {
                            viewModel.closeDiagnosticTest()
                        }
                    )
                }

                // Super Paywall Dialog - Tinder Plus model
                if (uiState.showSuperPaywall) {
                    SuperPaywallDialog(
                        currentGems = uiState.userStats.gems,
                        onBuyMonthly = { viewModel.purchaseSuper(1) },
                        onBuyYearly = { viewModel.purchaseSuper(12) },
                        onDismiss = { viewModel.showSuperPaywall(false) }
                    )
                }

                // Docked Interactive Audio Pronunciation Studio
                AudioPronunciationStudio(
                    visible = uiState.isAudioStudioVisible,
                    currentTerm = uiState.activeAudioTerm ?: uiState.terms.firstOrNull(),
                    isSpeaking = uiState.isTtsSpeaking,
                    isContinuousActive = uiState.isContinuousPlayActive,
                    currentLanguageCode = uiState.ttsLanguage,
                    speechRate = uiState.ttsRate,
                    onPlayPause = {
                        if (uiState.isTtsSpeaking) {
                            viewModel.stopAudio()
                        } else {
                            val term = uiState.activeAudioTerm ?: uiState.terms.firstOrNull()
                            term?.let { viewModel.speakTerm(it) }
                        }
                    },
                    onStop = { viewModel.stopAudio() },
                    onNext = { viewModel.nextAudioTerm() },
                    onPrevious = { viewModel.previousAudioTerm() },
                    onToggleContinuous = { viewModel.toggleContinuousPlay() },
                    onChangeLanguage = { lang ->
                        viewModel.setTtsLanguage(lang)
                        val term = uiState.activeAudioTerm ?: uiState.terms.firstOrNull()
                        term?.let { viewModel.speakTerm(it, lang) }
                    },
                    onChangeSpeed = { speed -> viewModel.setTtsSpeechRate(speed) },
                    onDismiss = {
                        viewModel.stopAudio()
                        viewModel.toggleAudioStudio(false)
                    },
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}
