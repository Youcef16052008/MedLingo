package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.MedLinguaApp
import com.example.data.initial.InitialData
import com.example.data.local.entity.DownloadedModuleEntity
import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.FlashcardProgressEntity
import com.example.data.local.entity.GemsTransactionEntity
import com.example.data.local.entity.LeagueCohortEntity
import com.example.data.local.entity.LeagueMemberEntity
import com.example.data.local.entity.MedicalTermEntity
import com.example.data.local.entity.UserStatsEntity
import com.example.domain.exercise.ExerciseLanguage
import com.example.domain.exercise.ExerciseSpec
import com.example.domain.gamification.HeartsManager
import com.example.domain.sm2.ReviewResult
import com.example.localization.Language
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

data class UiState(
    val terms: List<MedicalTermEntity> = emptyList(),
    val allTerms: List<MedicalTermEntity> = emptyList(),
    val exercises: List<ExerciseEntity> = emptyList(),
    val flashcardProgressList: List<FlashcardProgressEntity> = emptyList(),
    val downloadedModules: List<DownloadedModuleEntity> = emptyList(),
    val userStats: UserStatsEntity = UserStatsEntity(),
    // Duolingo Phase 1
    val gemsTransactions: List<GemsTransactionEntity> = emptyList(),
    val activeLeagueCohort: LeagueCohortEntity? = null,
    val leagueMembers: List<LeagueMemberEntity> = emptyList(),
    val currentHearts: Int = 5,
    val timeUntilNextHeart: Long = 0L,
    val showOutOfHeartsDialog: Boolean = false,
    val showHeartRefillDialog: Boolean = false,
    val showSuperPaywall: Boolean = false,
    val lastGemsEarned: Int = 0,
    val showGemsEarnedAnimation: Boolean = false,
    // Duolingo Phase 2 - ExerciseSpec + Same Exam Swapped Language
    val currentExerciseSpec: ExerciseSpec? = null,
    val exerciseLanguage: ExerciseLanguage = ExerciseLanguage.ENGLISH,
    val wordbankConstructed: List<String> = emptyList(),
    val matchUserPairs: Map<String, String> = emptyMap(),
    val currentLanguage: Language = Language.FRENCH,
    val isOnline: Boolean = true,
    val selectedModuleFilter: String = "All",
    val selectedChapterFilter: String = "All",
    val searchQuery: String = "",
    val activeFlashcardIndex: Int = 0,
    val isCardFlipped: Boolean = false,
    val lastReviewResult: ReviewResult? = null,
    // 6-Level Progressive Learning State
    val selectedLearningLevel: Int? = null,
    val levelExercises: List<ExerciseEntity> = emptyList(),
    val levelCurrentIndex: Int = 0,
    val levelScore: Int = 0,
    val levelCorrectCount: Int = 0,
    val levelSelectedOption: String? = null,
    val levelIsAnswered: Boolean = false,
    val levelIsCompleted: Boolean = false,
    val levelJustUnlockedNext: Boolean = false,
    // General Quiz state (backward compatibility)
    val quizCurrentIndex: Int = 0,
    val quizScore: Int = 0,
    val quizSelectedOption: String? = null,
    val quizIsAnswered: Boolean = false,
    val quizIsCompleted: Boolean = false,
    // Download simulation
    val downloadingModuleId: String? = null,
    val downloadProgress: Float = 0f,
    // Notification & Reminder Settings
    val notificationsEnabled: Boolean = true,
    val notificationPermissionGranted: Boolean = false,
    val streakReminderEnabled: Boolean = true,
    val clinicalPearlReminderEnabled: Boolean = true,
    val reminderHour: Int = 20,
    val reminderMinute: Int = 0,
    // Enhanced Multi-language TTS & Audio Studio State
    val ttsRate: Float = 0.9f,
    val ttsPitch: Float = 1.0f,
    val ttsLanguage: String = "en",
    val isTtsSpeaking: Boolean = false,
    val isContinuousPlayActive: Boolean = false,
    val continuousPlayTermIndex: Int = 0,
    val isAudioStudioVisible: Boolean = false,
    val activeAudioTerm: MedicalTermEntity? = null,
    // Adaptive Placement & Diagnostic State
    val isDiagnosticVisible: Boolean = false,
    val diagnosticResult: com.example.domain.diagnostic.PlacementResult? = null,
    val userProfileName: String = "Dr. Youcef",
    val userKnowledgeLevel: com.example.domain.diagnostic.KnowledgeLevel = com.example.domain.diagnostic.KnowledgeLevel.INTERMEDIATE,
    val userType: com.example.domain.diagnostic.UserType = com.example.domain.diagnostic.UserType.MED_STUDENT,
    val medYear: com.example.domain.diagnostic.MedYear? = com.example.domain.diagnostic.MedYear.YEAR_1
)

class MedLinguaViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as MedLinguaApp
    private val repository = app.repository
    val ttsManager = app.ttsManager
    private val gamificationViewModel = GamificationViewModel(repository)
    private val leagueViewModel = LeagueViewModel(repository)
    private val quizLevelViewModel = QuizLevelViewModel()

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    companion object {
        const val DEFAULT_REMINDER_HOUR = 20
        const val DEFAULT_REMINDER_MINUTE = 0
        const val DEFAULT_TTS_RATE = 0.9f
        const val DEFAULT_TTS_PITCH = 1.0f
        const val DEFAULT_TTS_LANGUAGE = "en"
        const val LEVEL_UNLOCK_THRESHOLD = 70
        const val MAX_LEARNING_LEVELS = 6
        const val DEFAULT_STREAK_DAYS = 12
        const val DEFAULT_TOTAL_POINTS = 2450
        const val DEFAULT_GEMS = 100
        const val DEFAULT_MAX_HEARTS = 5
    }

    private var allTermsList: List<MedicalTermEntity> = emptyList()

    init {
        filterTerms()
        viewModelScope.launch {
            repository.allTerms.collect { terms ->
                if (terms.isNotEmpty()) {
                    allTermsList = terms
                }
                filterTerms()
            }
        }
        viewModelScope.launch {
            repository.allExercises.collect { exercises ->
                if (exercises.isNotEmpty()) {
                    _uiState.update { it.copy(exercises = exercises) }
                }
            }
        }
        viewModelScope.launch {
            repository.allFlashcardProgress.collect { progress ->
                _uiState.update { it.copy(flashcardProgressList = progress) }
            }
        }
        viewModelScope.launch {
            repository.allDownloadedModules.collect { downloaded ->
                _uiState.update { it.copy(downloadedModules = downloaded) }
            }
        }
        viewModelScope.launch {
            repository.userStats.collect { stats ->
                if (stats != null) {
                    val currentHearts = HeartsManager.getCurrentHearts(stats)
                    val timeUntilNext = HeartsManager.timeUntilNextHeartMillis(stats)
                    _uiState.update {
                        it.copy(
                            userStats = stats,
                            currentHearts = currentHearts,
                            timeUntilNextHeart = timeUntilNext
                        )
                    }
                    repository.refreshLeagueCohortIfNeeded()
                }
            }
        }
        viewModelScope.launch {
            repository.gemsTransactions.collect { transactions ->
                _uiState.update { it.copy(gemsTransactions = transactions) }
            }
        }
        viewModelScope.launch {
            repository.activeLeagueCohort.collect { cohort ->
                _uiState.update { it.copy(activeLeagueCohort = cohort) }
                if (cohort != null) {
                    launch {
                        repository.getLeagueMembers(cohort.cohortId).collect { members ->
                            _uiState.update { it.copy(leagueMembers = members) }
                        }
                    }
                }
            }
        }
        viewModelScope.launch {
            while (isActive) {
                delay(60_000)
                val stats = _uiState.value.userStats
                val currentHearts = HeartsManager.getCurrentHearts(stats)
                val timeUntilNext = HeartsManager.timeUntilNextHeartMillis(stats)
                _uiState.update {
                    it.copy(
                        currentHearts = currentHearts,
                        timeUntilNextHeart = timeUntilNext
                    )
                }
            }
        }
    }

    private fun filterTerms() {
        val query = _uiState.value.searchQuery.trim()
        val moduleFilter = _uiState.value.selectedModuleFilter
        val chapterFilter = _uiState.value.selectedChapterFilter

        val filtered = allTermsList.filter { term ->
            val matchesModule = (moduleFilter == "All" ||
                    term.module.equals(moduleFilter, ignoreCase = true) ||
                    term.module.contains(moduleFilter, ignoreCase = true) ||
                    moduleFilter.contains(term.module, ignoreCase = true))
            val matchesChapter = (chapterFilter == "All" || term.chapter.equals(chapterFilter, ignoreCase = true) || term.chapter.contains(chapterFilter, ignoreCase = true))
            val matchesQuery = if (query.isBlank()) true else {
                term.termEn.contains(query, ignoreCase = true) ||
                term.termFr.contains(query, ignoreCase = true) ||
                term.termAr.contains(query, ignoreCase = true) ||
                term.definitionEn.contains(query, ignoreCase = true) ||
                term.definitionFr.contains(query, ignoreCase = true) ||
                term.chapter.contains(query, ignoreCase = true) ||
                term.etymology.contains(query, ignoreCase = true)
            }
            matchesModule && matchesChapter && matchesQuery
        }

        _uiState.update { it.copy(terms = filtered, allTerms = allTermsList) }
    }

    fun setLanguage(language: Language) {
        _uiState.update { it.copy(currentLanguage = language) }
        viewModelScope.launch {
            repository.updateLanguage(language.code)
        }
    }

    fun toggleOnlineStatus() {
        val newOnline = !_uiState.value.isOnline
        _uiState.update { it.copy(isOnline = newOnline) }
        viewModelScope.launch {
            repository.toggleOfflineSimulation(!newOnline)
        }
    }

    fun setModuleFilter(module: String) {
        _uiState.update {
            it.copy(
                selectedModuleFilter = module,
                selectedChapterFilter = "All",
                activeFlashcardIndex = 0,
                isCardFlipped = false
            )
        }
        filterTerms()
    }

    fun setChapterFilter(chapter: String) {
        _uiState.update {
            it.copy(
                selectedChapterFilter = chapter,
                activeFlashcardIndex = 0,
                isCardFlipped = false
            )
        }
        filterTerms()
    }

    fun setSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        filterTerms()
    }

    fun flipCard() {
        _uiState.update { it.copy(isCardFlipped = !it.isCardFlipped) }
    }

    fun rateFlashcard(termId: Int, quality: Int, totalFlashcards: Int) {
        viewModelScope.launch {
            val result = repository.submitFlashcardRating(termId, quality)
            _uiState.update { it.copy(lastReviewResult = result) }
            delay(300)
            _uiState.update {
                val nextIndex = if (totalFlashcards > 0) (it.activeFlashcardIndex + 1) % totalFlashcards else 0
                it.copy(
                    isCardFlipped = false,
                    activeFlashcardIndex = nextIndex
                )
            }
        }
    }

    fun nextFlashcard(total: Int) {
        if (total > 0) {
            _uiState.update {
                it.copy(
                    isCardFlipped = false,
                    activeFlashcardIndex = (it.activeFlashcardIndex + 1) % total
                )
            }
        }
    }

    fun prevFlashcard(total: Int) {
        if (total > 0) {
            _uiState.update {
                val prev = if (it.activeFlashcardIndex == 0) total - 1 else it.activeFlashcardIndex - 1
                it.copy(
                    isCardFlipped = false,
                    activeFlashcardIndex = prev
                )
            }
        }
    }

    // ========================================================
    // 6-LEVEL PROGRESSIVE LEARNING METHODS
    // ========================================================

    fun startLevelTraining(level: Int) {
        val exercisesForLevel = _uiState.value.exercises.filter { it.level == level }
        _uiState.update {
            it.copy(
                selectedLearningLevel = level,
                levelExercises = exercisesForLevel,
                levelCurrentIndex = 0,
                levelScore = 0,
                levelCorrectCount = 0,
                levelSelectedOption = null,
                levelIsAnswered = false,
                levelIsCompleted = false,
                levelJustUnlockedNext = false
            )
        }
    }

    fun exitLevelTraining() {
        _uiState.update {
            it.copy(
                selectedLearningLevel = null,
                levelExercises = emptyList(),
                levelCurrentIndex = 0,
                levelScore = 0,
                levelCorrectCount = 0,
                levelSelectedOption = null,
                levelIsAnswered = false,
                levelIsCompleted = false,
                levelJustUnlockedNext = false
            )
        }
    }

    fun selectLevelAnswer(answer: String, correctAnswer: String, points: Int) {
        if (_uiState.value.levelIsAnswered) return
        val isCorrect = answer.trim().equals(correctAnswer.trim(), ignoreCase = true)
        val newScore = if (isCorrect) _uiState.value.levelScore + points else _uiState.value.levelScore
        val newCorrectCount = if (isCorrect) _uiState.value.levelCorrectCount + 1 else _uiState.value.levelCorrectCount

        _uiState.update {
            it.copy(
                levelSelectedOption = answer,
                levelIsAnswered = true,
                levelScore = newScore,
                levelCorrectCount = newCorrectCount
            )
        }
    }

    fun nextLevelQuestion() {
        val state = _uiState.value
        val exercises = state.levelExercises
        val currentIdx = state.levelCurrentIndex

        if (currentIdx + 1 < exercises.size) {
            _uiState.update {
                it.copy(
                    levelCurrentIndex = currentIdx + 1,
                    levelSelectedOption = null,
                    levelIsAnswered = false
                )
            }
        } else {
            // Level is completed! Calculate score percentage & persist in DB
            val level = state.selectedLearningLevel ?: 1
            val total = exercises.size
            val correct = state.levelCorrectCount
            val percentage = if (total > 0) ((correct.toDouble() / total) * 100).toInt() else 0

            viewModelScope.launch {
                val justUnlocked = repository.recordLevelCompletion(
                    level = level,
                    scorePercentage = percentage,
                    pointsEarned = state.levelScore,
                    totalQuestions = total,
                    correctAnswers = correct
                )
                if (justUnlocked && level < 6) {
                    val nextLevelObj = com.example.domain.learning.LearningLevel.fromNumber(level + 1)
                    com.example.service.NotificationHelper.showLevelUnlockedNotification(
                        app,
                        level + 1,
                        nextLevelObj.titleFr
                    )
                }
                _uiState.update {
                    it.copy(
                        levelIsCompleted = true,
                        levelJustUnlockedNext = justUnlocked
                    )
                }
            }
        }
    }

    fun restartCurrentLevel() {
        val level = _uiState.value.selectedLearningLevel ?: return
        startLevelTraining(level)
    }

    // ========================================================
    // GENERAL QUIZ METHODS (FOR BACKWARD COMPATIBILITY)
    // ========================================================

    fun selectQuizAnswer(answer: String, correctAnswer: String, points: Int) {
        if (_uiState.value.quizIsAnswered) return
        val isCorrect = answer.trim().equals(correctAnswer.trim(), ignoreCase = true)
        val newScore = if (isCorrect) _uiState.value.quizScore + points else _uiState.value.quizScore

        _uiState.update {
            it.copy(
                quizSelectedOption = answer,
                quizIsAnswered = true,
                quizScore = newScore
            )
        }
    }

    fun nextQuizQuestion(totalQuestions: Int) {
        val currentIdx = _uiState.value.quizCurrentIndex
        if (currentIdx + 1 < totalQuestions) {
            _uiState.update {
                it.copy(
                    quizCurrentIndex = currentIdx + 1,
                    quizSelectedOption = null,
                    quizIsAnswered = false
                )
            }
        } else {
            _uiState.update { it.copy(quizIsCompleted = true) }
            viewModelScope.launch {
                repository.recordQuizCompletion(
                    pointsEarned = _uiState.value.quizScore,
                    totalQuestions = totalQuestions,
                    correctAnswers = (_uiState.value.quizScore / 15).coerceAtMost(totalQuestions)
                )
            }
        }
    }

    fun resetQuiz() {
        _uiState.update {
            it.copy(
                quizCurrentIndex = 0,
                quizScore = 0,
                quizSelectedOption = null,
                quizIsAnswered = false,
                quizIsCompleted = false
            )
        }
    }

    private var continuousJob: kotlinx.coroutines.Job? = null

    fun setTtsSpeechRate(rate: Float) {
        _uiState.update { it.copy(ttsRate = rate) }
        ttsManager.setSpeechRate(rate)
    }

    fun setTtsPitch(pitch: Float) {
        _uiState.update { it.copy(ttsPitch = pitch) }
        ttsManager.setPitch(pitch)
    }

    fun setTtsLanguage(lang: String) {
        _uiState.update { it.copy(ttsLanguage = lang) }
    }

    fun toggleAudioStudio(visible: Boolean, term: MedicalTermEntity? = null) {
        val targetTerm = term ?: _uiState.value.terms.firstOrNull()
        _uiState.update {
            it.copy(
                isAudioStudioVisible = visible,
                activeAudioTerm = targetTerm ?: it.activeAudioTerm
            )
        }
    }

    fun speak(text: String, lang: String? = null) {
        val targetLang = lang ?: _uiState.value.ttsLanguage
        _uiState.update { it.copy(isTtsSpeaking = true) }
        ttsManager.speak(
            text = text,
            languageCode = targetLang,
            speechRate = _uiState.value.ttsRate,
            pitch = _uiState.value.ttsPitch,
            onStart = {
                _uiState.update { it.copy(isTtsSpeaking = true) }
            },
            onDone = {
                _uiState.update { it.copy(isTtsSpeaking = false) }
            },
            onError = {
                _uiState.update { it.copy(isTtsSpeaking = false) }
            }
        )
    }

    fun speakTerm(term: MedicalTermEntity, lang: String? = null) {
        val targetLang = lang ?: _uiState.value.ttsLanguage
        _uiState.update { it.copy(activeAudioTerm = term) }
        val textToSpeak = when (targetLang.lowercase()) {
            "fr" -> term.termFr
            "ar" -> term.termAr
            else -> term.termEn
        }
        speak(textToSpeak, targetLang)
    }

    fun stopAudio() {
        continuousJob?.cancel()
        continuousJob = null
        ttsManager.stop()
        _uiState.update { it.copy(isTtsSpeaking = false, isContinuousPlayActive = false) }
    }

    fun toggleContinuousPlay() {
        if (_uiState.value.isContinuousPlayActive) {
            stopAudio()
        } else {
            val startIdx = _uiState.value.terms.indexOfFirst { it.id == _uiState.value.activeAudioTerm?.id }
            startContinuousPlay(if (startIdx >= 0) startIdx else 0)
        }
    }

    fun startContinuousPlay(startIndex: Int = 0) {
        val termsToPlay = _uiState.value.terms
        if (termsToPlay.isEmpty()) return
        continuousJob?.cancel()
        _uiState.update {
            it.copy(
                isContinuousPlayActive = true,
                continuousPlayTermIndex = startIndex.coerceIn(0, termsToPlay.size - 1),
                activeAudioTerm = termsToPlay[startIndex.coerceIn(0, termsToPlay.size - 1)],
                isAudioStudioVisible = true
            )
        }

        continuousJob = viewModelScope.launch {
            var idx = startIndex
            while (this.isActive && idx < termsToPlay.size) {
                val current = termsToPlay[idx]
                _uiState.update {
                    it.copy(
                        continuousPlayTermIndex = idx,
                        activeAudioTerm = current
                    )
                }

                // 1. Pronounce English term
                speakAndWait(current.termEn, "en")
                delay(500)
                if (!this.isActive) break

                // 2. Pronounce French translation
                speakAndWait(current.termFr, "fr")
                delay(1200)
                idx++
            }
            _uiState.update { it.copy(isContinuousPlayActive = false) }
        }
    }

    private suspend fun speakAndWait(text: String, lang: String) {
        val completer = kotlinx.coroutines.CompletableDeferred<Unit>()
        _uiState.update { it.copy(isTtsSpeaking = true) }
        ttsManager.speak(
            text = text,
            languageCode = lang,
            speechRate = _uiState.value.ttsRate,
            pitch = _uiState.value.ttsPitch,
            onStart = {
                _uiState.update { it.copy(isTtsSpeaking = true) }
            },
            onDone = {
                _uiState.update { it.copy(isTtsSpeaking = false) }
                completer.complete(Unit)
            },
            onError = {
                _uiState.update { it.copy(isTtsSpeaking = false) }
                completer.complete(Unit)
            }
        )
        kotlinx.coroutines.withTimeoutOrNull(6000) {
            completer.await()
        }
    }

    fun nextAudioTerm() {
        val list = _uiState.value.terms
        if (list.isEmpty()) return
        val currentIdx = list.indexOfFirst { it.id == _uiState.value.activeAudioTerm?.id }
        val nextIdx = if (currentIdx in 0 until list.size - 1) currentIdx + 1 else 0
        val nextTerm = list[nextIdx]
        _uiState.update { it.copy(activeAudioTerm = nextTerm, continuousPlayTermIndex = nextIdx) }
        speakTerm(nextTerm)
    }

    fun previousAudioTerm() {
        val list = _uiState.value.terms
        if (list.isEmpty()) return
        val currentIdx = list.indexOfFirst { it.id == _uiState.value.activeAudioTerm?.id }
        val prevIdx = if (currentIdx > 0) currentIdx - 1 else list.size - 1
        val prevTerm = list[prevIdx]
        _uiState.update { it.copy(activeAudioTerm = prevTerm, continuousPlayTermIndex = prevIdx) }
        speakTerm(prevTerm)
    }

    fun toggleBookmark(term: MedicalTermEntity) {
        viewModelScope.launch {
            repository.toggleBookmark(term)
        }
    }

    fun downloadModule(moduleInfo: InitialData.ModuleInfo) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    downloadingModuleId = moduleInfo.id,
                    downloadProgress = 0f
                )
            }

            for (i in 1..10) {
                delay(120)
                _uiState.update { it.copy(downloadProgress = i * 0.1f) }
            }

            repository.toggleModuleDownload(moduleInfo, true)
            _uiState.update {
                it.copy(
                    downloadingModuleId = null,
                    downloadProgress = 0f
                )
            }
        }
    }

    fun deleteDownloadedModule(moduleInfo: InitialData.ModuleInfo) {
        viewModelScope.launch {
            repository.toggleModuleDownload(moduleInfo, false)
        }
    }

    // ========================================================
    // NOTIFICATIONS & REMINDERS MANAGEMENT
    // ========================================================

    fun setNotificationPermissionGranted(granted: Boolean) {
        _uiState.update { it.copy(notificationPermissionGranted = granted) }
    }

    fun toggleDailyReminder(enabled: Boolean) {
        _uiState.update { it.copy(notificationsEnabled = enabled) }
        if (enabled) {
            com.example.service.NotificationHelper.scheduleDailyReminder(
                app,
                _uiState.value.reminderHour,
                _uiState.value.reminderMinute
            )
        } else {
            com.example.service.NotificationHelper.cancelDailyReminder(app)
        }
    }

    fun toggleStreakReminder(enabled: Boolean) {
        _uiState.update { it.copy(streakReminderEnabled = enabled) }
    }

    fun toggleClinicalPearlReminder(enabled: Boolean) {
        _uiState.update { it.copy(clinicalPearlReminderEnabled = enabled) }
    }

    fun setReminderTime(hour: Int, minute: Int) {
        _uiState.update { it.copy(reminderHour = hour, reminderMinute = minute) }
        if (_uiState.value.notificationsEnabled) {
            com.example.service.NotificationHelper.scheduleDailyReminder(app, hour, minute)
        }
    }

    fun sendTestNotification(type: String) {
        when (type) {
            "review" -> {
                com.example.service.NotificationHelper.showReviewReminder(app, dueCount = 6)
            }
            "streak" -> {
                com.example.service.NotificationHelper.showStreakReminder(
                    app,
                    streakDays = _uiState.value.userStats.streakDays
                )
            }
            "pearl" -> {
                val pearlTerm = _uiState.value.terms.firstOrNull { it.clinicalPearl.isNotBlank() }
                com.example.service.NotificationHelper.showClinicalPearlNotification(app, pearlTerm)
            }
            "level" -> {
                com.example.service.NotificationHelper.showLevelUnlockedNotification(
                    app,
                    levelNumber = 2,
                    levelName = "Collocations & Expressions Cliniques"
                )
            }
        }
    }

    // ========================================================
    // ADAPTIVE PLACEMENT & DIAGNOSTIC TEST (CAT)
    // ========================================================

    fun openDiagnosticTest() {
        _uiState.update { it.copy(isDiagnosticVisible = true) }
    }

    fun closeDiagnosticTest() {
        _uiState.update { it.copy(isDiagnosticVisible = false) }
    }

    fun applyDiagnosticResult(result: com.example.domain.diagnostic.PlacementResult) {
        _uiState.update {
            it.copy(
                isDiagnosticVisible = false,
                diagnosticResult = result,
                userProfileName = result.name,
                userKnowledgeLevel = result.level,
                userType = result.userType,
                medYear = result.medYear,
                selectedModuleFilter = result.startModuleName
            )
        }
        viewModelScope.launch {
            val current = _uiState.value.userStats
            val updated = current.copy(
                totalPoints = current.totalPoints + result.score * 5
            )
            repository.saveUserStats(updated)
            _uiState.update { it.copy(userStats = updated) }
        }
    }

    // ========================================================
    // DUOLINGO PHASE 1: HEARTS, GEMS, LEAGUES (delegated)
    // ========================================================

    fun onWrongAnswer() {
        viewModelScope.launch {
            val success = gamificationViewModel.onWrongAnswer()
            if (!success) {
                _uiState.update { it.copy(showOutOfHeartsDialog = true) }
            }
        }
    }

    fun refillHeartsWithGems() {
        viewModelScope.launch {
            val success = gamificationViewModel.refillHeartsWithGems()
            if (success) {
                _uiState.update { it.copy(showOutOfHeartsDialog = false, showHeartRefillDialog = false) }
            }
        }
    }

    fun earnHeartFromPractice() {
        viewModelScope.launch {
            val success = gamificationViewModel.earnHeartFromPractice()
            if (success) {
                _uiState.update { it.copy(showOutOfHeartsDialog = false) }
            }
        }
    }

    fun purchaseSuper(months: Int = 1) {
        viewModelScope.launch {
            gamificationViewModel.purchaseSuper(months)
            _uiState.update { it.copy(showSuperPaywall = false, showOutOfHeartsDialog = false) }
        }
    }

    fun showSuperPaywall(show: Boolean) {
        _uiState.update { it.copy(showSuperPaywall = show) }
    }

    fun dismissOutOfHeartsDialog() {
        _uiState.update { it.copy(showOutOfHeartsDialog = false) }
    }

    fun dismissHeartRefillDialog() {
        _uiState.update { it.copy(showHeartRefillDialog = false) }
    }

    fun showHeartRefillDialog(show: Boolean) {
        _uiState.update { it.copy(showHeartRefillDialog = show) }
    }

    fun checkCanDoLesson(): Boolean {
        val stats = _uiState.value.userStats
        return gamificationViewModel.checkCanDoLesson(stats)
    }

    fun onLessonStart() {
        if (!checkCanDoLesson()) {
            _uiState.update { it.copy(showOutOfHeartsDialog = true) }
        }
    }

    // Override selectLevelAnswer to include hearts logic
    fun selectLevelAnswerWithHearts(answer: String, correctAnswer: String, points: Int) {
        if (_uiState.value.levelIsAnswered) return
        val isCorrect = answer.trim().equals(correctAnswer.trim(), ignoreCase = true)
        if (!isCorrect) {
            onWrongAnswer()
        }
        selectLevelAnswer(answer, correctAnswer, points)
    }

    fun showGemsEarned(amount: Int) {
        _uiState.update { it.copy(lastGemsEarned = amount, showGemsEarnedAnimation = true) }
        viewModelScope.launch {
            kotlinx.coroutines.delay(3000)
            _uiState.update { it.copy(showGemsEarnedAnimation = false) }
        }
    }

    // ========================================================
    // DUOLINGO PHASE 2: ExerciseSpec + Same Exam Swapped Language
    // ========================================================

    fun loadExerciseSpecForCurrentLevel() {
        val state = _uiState.value
        val exercises = state.levelExercises
        val idx = state.levelCurrentIndex
        if (exercises.isEmpty() || idx >= exercises.size) return
        val entity = exercises[idx]
        val spec = entity.toExerciseSpec()
        _uiState.update {
            it.copy(
                currentExerciseSpec = spec,
                wordbankConstructed = emptyList(),
                matchUserPairs = emptyMap(),
                levelSelectedOption = null,
                levelIsAnswered = false
            )
        }
    }

    fun setExerciseLanguage(lang: ExerciseLanguage) {
        _uiState.update { it.copy(exerciseLanguage = lang) }
        // Reload spec with swapped language
        val currentSpec = _uiState.value.currentExerciseSpec ?: return
        val swapped = com.example.domain.exercise.SameExamSwappedLanguage.swapLanguage(currentSpec, lang)
        _uiState.update { it.copy(currentExerciseSpec = swapped) }
    }

    // Wordbank methods
    fun addWordToConstructed(word: String) {
        _uiState.update { it.copy(wordbankConstructed = it.wordbankConstructed + word) }
    }

    fun removeWordFromConstructed(index: Int) {
        val current = _uiState.value.wordbankConstructed.toMutableList()
        if (index in current.indices) {
            current.removeAt(index)
            _uiState.update { it.copy(wordbankConstructed = current) }
        }
    }

    fun clearWordbankConstructed() {
        _uiState.update { it.copy(wordbankConstructed = emptyList()) }
    }

    fun checkWordbankAnswer(correctSentence: String) {
        if (_uiState.value.levelIsAnswered) return
        val constructed = _uiState.value.wordbankConstructed.joinToString(" ")
        val result = com.example.domain.exercise.ExerciseChecker.checkWordbank(constructed, correctSentence)
        if (!result.isCorrect) onWrongAnswer()
        // Use selectLevelAnswer to record score
        selectLevelAnswer(constructed, correctSentence, 20)
    }

    // Match methods
    fun setMatchPair(left: String, right: String) {
        val current = _uiState.value.matchUserPairs.toMutableMap()
        current[left] = right
        _uiState.update { it.copy(matchUserPairs = current) }
    }

    fun checkMatchAnswer(correctPairs: List<com.example.domain.exercise.MatchPair>) {
        if (_uiState.value.levelIsAnswered) return
        val userPairs = _uiState.value.matchUserPairs.map { com.example.domain.exercise.MatchPair(it.key, it.value) }
        val result = com.example.domain.exercise.ExerciseChecker.checkMatch(userPairs, correctPairs)
        if (!result.isCorrect) onWrongAnswer()
        selectLevelAnswer(result.userAnswer, result.correctAnswer, 20)
    }

    // Fill blank
    fun checkFillAnswer(userAnswer: String, accepted: List<String>) {
        if (_uiState.value.levelIsAnswered) return
        val result = com.example.domain.exercise.ExerciseChecker.checkFill(userAnswer, accepted)
        if (!result.isCorrect) onWrongAnswer()
        selectLevelAnswer(userAnswer, accepted.firstOrNull() ?: "", 20)
    }

    // Choice by index
    fun selectChoiceByIndex(selectedIndex: Int, correctIndex: Int, points: Int) {
        if (_uiState.value.levelIsAnswered) return
        val isCorrect = selectedIndex == correctIndex
        if (!isCorrect) onWrongAnswer()
        val result = com.example.domain.exercise.ExerciseChecker.checkChoice(selectedIndex, correctIndex)
        // Convert to legacy selectLevelAnswer for compatibility
        selectLevelAnswer(
            answer = selectedIndex.toString(),
            correctAnswer = correctIndex.toString(),
            points = if (result.isCorrect) points else 0
        )
    }

    // Override startLevelTraining to also load spec
    fun startLevelTrainingWithSpec(level: Int) {
        startLevelTraining(level)
        // Delay to allow exercises to load
        viewModelScope.launch {
            delay(100)
            loadExerciseSpecForCurrentLevel()
        }
    }

    fun nextLevelQuestionWithSpec() {
        nextLevelQuestion()
        viewModelScope.launch {
            delay(100)
            loadExerciseSpecForCurrentLevel()
        }
    }
}
