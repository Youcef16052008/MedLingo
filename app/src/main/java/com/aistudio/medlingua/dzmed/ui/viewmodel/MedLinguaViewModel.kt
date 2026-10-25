package com.aistudio.medlingua.dzmed.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aistudio.medlingua.dzmed.MedLinguaApp
import com.aistudio.medlingua.dzmed.data.initial.InitialData
import com.aistudio.medlingua.dzmed.data.local.entity.DownloadedModuleEntity
import com.aistudio.medlingua.dzmed.data.local.entity.ExerciseEntity
import com.aistudio.medlingua.dzmed.data.local.entity.FlashcardProgressEntity
import com.aistudio.medlingua.dzmed.data.local.entity.GemsTransactionEntity
import com.aistudio.medlingua.dzmed.data.local.entity.LeagueCohortEntity
import com.aistudio.medlingua.dzmed.data.local.entity.LeagueMemberEntity
import com.aistudio.medlingua.dzmed.data.local.entity.MedicalTermEntity
import com.aistudio.medlingua.dzmed.data.local.entity.UserStatsEntity
import com.aistudio.medlingua.dzmed.domain.exercise.ExerciseSpec
import com.aistudio.medlingua.dzmed.domain.gamification.ChestReward
import com.aistudio.medlingua.dzmed.domain.gamification.ChestSource
import com.aistudio.medlingua.dzmed.domain.gamification.FREEZE_COST_GEMS
import com.aistudio.medlingua.dzmed.domain.gamification.GamificationEconomy
import com.aistudio.medlingua.dzmed.domain.gamification.GoalProgress
import com.aistudio.medlingua.dzmed.domain.gamification.LeagueBotAnimator
import com.aistudio.medlingua.dzmed.domain.lesson.LessonGenerator
import com.aistudio.medlingua.dzmed.domain.sm2.ReviewResult
import com.aistudio.medlingua.dzmed.localization.Language
import com.aistudio.medlingua.dzmed.ui.components.RewardData
import kotlinx.coroutines.delay
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

data class UiState(
    val terms: List<MedicalTermEntity> = emptyList(),
    val allTerms: List<MedicalTermEntity> = emptyList(),
    val exercises: List<ExerciseEntity> = emptyList(),
    val flashcardProgressList: List<FlashcardProgressEntity> = emptyList(),
    /**
     * Cards ready for review, computed on the repository clock (not wall time).
     * Single source for the home card, the tab badge and the review reminder.
     */
    val dueFlashcardProgress: List<FlashcardProgressEntity> = emptyList(),
    val downloadedModules: List<DownloadedModuleEntity> = emptyList(),
    // P0-2 (rapport final, bug B2) : la valeur par défaut de l'UI est elle aussi
    // la persona (les valeurs par défaut de la data class sont `streakDays = 12`,
    // `level1Score = 85`…). À l'ouverture, avant la première lecture de la base,
    // l'utilisateur voyait donc une progression fabriquée. `createFresh()` est
    // neutre — un état vide est affiché le temps du chargement, pas un mensonge.
    val userStats: UserStatsEntity = UserStatsEntity.createFresh(),
    // Duolingo Phase 1
    val gemsTransactions: List<GemsTransactionEntity> = emptyList(),
    val activeLeagueCohort: LeagueCohortEntity? = null,
    val leagueMembers: List<LeagueMemberEntity> = emptyList(),
    val showSuperPaywall: Boolean = false,
    val lastGemsEarned: Int = 0,
    val showGemsEarnedAnimation: Boolean = false,
    // Duolingo Phase 2 - ExerciseSpec + Same Exam Swapped Language
    val currentExerciseSpec: ExerciseSpec? = null,
    val currentLanguage: Language = Language.FRENCH,
    val isOnline: Boolean = true,
    val selectedModuleFilter: String = "All",
    val selectedChapterFilter: String = "All",
    val searchQuery: String = "",
    val activeFlashcardIndex: Int = 0,
    val isCardFlipped: Boolean = false,
    val lastReviewResult: ReviewResult? = null,
    /** Progression de l'objectif quotidien (calculée à partir du statut utilisateur). */
    val goalProgress: GoalProgress = GoalProgress(0, 0, 0, false),
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
    val diagnosticResult: com.aistudio.medlingua.dzmed.domain.diagnostic.PlacementResult? = null,
    val userProfileName: String = "Dr. Youcef",
    val userKnowledgeLevel: com.aistudio.medlingua.dzmed.domain.diagnostic.KnowledgeLevel = com.aistudio.medlingua.dzmed.domain.diagnostic.KnowledgeLevel.INTERMEDIATE,
    val userType: com.aistudio.medlingua.dzmed.domain.diagnostic.UserType = com.aistudio.medlingua.dzmed.domain.diagnostic.UserType.MED_STUDENT,
    val medYear: com.aistudio.medlingua.dzmed.domain.diagnostic.MedYear? = com.aistudio.medlingua.dzmed.domain.diagnostic.MedYear.YEAR_1,
    // === Duolingo Lot 2 : Parcours, trophées, récompenses ===
    /** Ids des trophées déjà gagnés (table `trophies`). */
    val trophies: List<String> = emptyList(),
    /** Meilleur score par leçon (`module:level`, table `lesson_scores`). */
    val lessonBest: Map<String, Int> = emptyMap(),
    /** Module de la leçon en cours depuis le Parcours (`null` = quiz libre). */
    val pathLessonModuleId: String? = null,
    /** Récompense à afficher dans la pop-up (spec §11). */
    val lastReward: RewardData? = null,
    /** Récompense tirée de la caisse ouverte dans la pop-up. */
    val pendingChestReward: ChestReward? = null
)

class MedLinguaViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as MedLinguaApp
    private val repository = app.repository
    val ttsManager = app.ttsManager
    private val gamificationViewModel = GamificationViewModel(repository)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // LOT2/WS2.1 : file de révision (hub ReviewHubScreen). Le DAO existe
    // depuis la v13 ; la population par les leçons de chapitre viendra avec
    // la résolution du WIP repository (cf. TODO.md).
    private val reviewQueueDao = AppModule.provideReviewQueueDao(app.database)
    val reviewQueue: StateFlow<List<com.aistudio.medlingua.dzmed.data.local.entity.ReviewQueueItem>> =
        reviewQueueDao.getAllReviews()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun deleteReview(id: String) {
        viewModelScope.launch { reviewQueueDao.deleteReview(id) }
    }

    companion object {
        const val DEFAULT_REMINDER_HOUR = 20
        const val DEFAULT_REMINDER_MINUTE = 0
        const val DEFAULT_TTS_RATE = 0.9f
        const val DEFAULT_TTS_PITCH = 1.0f
        const val DEFAULT_TTS_LANGUAGE = "en"
        const val LEVEL_UNLOCK_THRESHOLD = 70
        // Recurring reminder slots (distinct request codes per alarm)
        const val REQUEST_CODE_STREAK = 101
        const val REQUEST_CODE_PEARL = 102
        const val STREAK_REMINDER_HOUR = 21
        const val PEARL_REMINDER_HOUR = 8
        const val MAX_LEARNING_LEVELS = 6
        const val DEFAULT_STREAK_DAYS = 12
        const val DEFAULT_TOTAL_POINTS = 2450
        const val DEFAULT_GEMS = 100
    }

    private var allTermsList: List<MedicalTermEntity> = emptyList()
    private var leagueRefreshChecked = false

    init {
        // Restore persisted reminder toggle state before the UI observes it.
        val reminderPrefs = app.getSharedPreferences(
            com.aistudio.medlingua.dzmed.service.NotificationHelper.PREFS_REMINDERS,
            android.content.Context.MODE_PRIVATE
        )
        _uiState.update {
            it.copy(
                notificationsEnabled = reminderPrefs.getBoolean(
                    com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_DAILY_REMINDER_ENABLED, true
                ),
                streakReminderEnabled = reminderPrefs.getBoolean(
                    com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_STREAK_REMINDER_ENABLED, true
                ),
                clinicalPearlReminderEnabled = reminderPrefs.getBoolean(
                    com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_PEARL_REMINDER_ENABLED, true
                ),
                // P0-4 : restaurer aussi l'heure choisie, sinon l'écran de
                // réglage réaffiche 20:00 à chaque ouverture alors que
                // l'alarme utilise bien l'heure persistée.
                reminderHour = reminderPrefs.getInt(
                    com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_REMINDER_HOUR, 20
                ),
                reminderMinute = reminderPrefs.getInt(
                    com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_REMINDER_MINUTE, 0
                )
            )
        }
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
                val clock = repository.currentClock
                _uiState.update {
                    it.copy(
                        flashcardProgressList = progress,
                        dueFlashcardProgress = progress.filter { card ->
                            com.aistudio.medlingua.dzmed.domain.sm2.SpacedRepetitionAlgorithm
                                .isDue(card.nextReviewTimestamp, clock)
                        }
                    )
                }
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
                    _uiState.update {
                        it.copy(
                            userStats = stats,
                            currentLanguage = com.aistudio.medlingua.dzmed.localization.Language.fromCode(stats.selectedLanguageCode)
                        )
                    }
                    if (!leagueRefreshChecked) {
                        leagueRefreshChecked = true
                        repository.refreshLeagueCohortIfNeeded()
                    }
                }
            }
        }
        viewModelScope.launch {
            repository.gemsTransactions.collect { transactions ->
                _uiState.update { it.copy(gemsTransactions = transactions) }
            }
        }
        viewModelScope.launch {
            // PHASE 3 #8 (rapport final) : animer les bots. Sans cela, leur XP
            // figé dès la création de la cohorte rendait le classement statique.
            // Déterministe (même instant → même classement), donc testable.
            //
            // BUG-005 : le collecteur de membres vivait DANS le collect du
            // cohort — chaque émission y lançait un NOUVEAU collecteur jamais
            // annulé, et le persist inconditionnel invalidait la table Room à
            // chaque tour (écriture → invalidation → ré-émission → écriture…
            // en boucle, pendant toute la vie du ViewModel). Désormais :
            // 1. le Job est annulé puis remplacé au changement de cohorte ;
            // 2. on ne persiste QUE si l'animation a changé quelque chose —
            //    advanceAll est idempotent à `now` fixé (il renvoie l'entité
            //    non modifiée quand target == weeklyXp), donc l'égalité de
            //    listes prouve qu'il n'y a rien à écrire et la boucle converge.
            var membersJob: Job? = null
            repository.activeLeagueCohort.collect { cohort ->
                _uiState.update { it.copy(activeLeagueCohort = cohort) }
                membersJob?.cancel()
                if (cohort != null) {
                    membersJob = launch {
                        repository.getLeagueMembers(cohort.cohortId)
                            .distinctUntilChanged()
                            .collect { members ->
                                val animated = LeagueBotAnimator.advanceAll(
                                    members,
                                    cohort.weekStartTimestamp,
                                    cohort.weekEndTimestamp,
                                    System.currentTimeMillis()
                                )
                                _uiState.update { it.copy(leagueMembers = animated) }

                                // L'animation est calculée à l'affichage ; on persiste
                                // en base pour que le rang query (ORDER BY
                                // weeklyXp DESC) reste cohérent entre les écrans.
                                if (animated != members) {
                                    repository.persistBotAnimation(animated)
                                }
                            }
                    }
                } else {
                    _uiState.update { it.copy(leagueMembers = emptyList()) }
                }
            }
        }
        viewModelScope.launch {
            repository.trophies.collect { list ->
                _uiState.update { it.copy(trophies = list.map { t -> t.id }) }
            }
        }
        viewModelScope.launch {
            repository.lessonScores.collect { list ->
                _uiState.update {
                    it.copy(lessonBest = list.associate { row -> row.lessonKey to row.best })
                }
            }
        }
        viewModelScope.launch {
            while (isActive) {
                delay(60_000)
                // Weekly league rotation check, throttled to once per minute
                // (was previously run on EVERY stats emission)
                repository.refreshLeagueCohortIfNeeded()
            }
        }
    }

    private fun filterTerms() {
        val query = _uiState.value.searchQuery.trim()
        val moduleFilter = _uiState.value.selectedModuleFilter
        val chapterFilter = _uiState.value.selectedChapterFilter

        val filtered = allTermsList.filter { term ->
            val matchesModule = com.aistudio.medlingua.dzmed.domain.ModuleFilter.matchesModule(term.module, moduleFilter)
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
        // Reset the deck position like the other filters: with a stale index,
        // nextFlashcard/prevFlashcard wrap on a list that no longer matches.
        _uiState.update {
            it.copy(searchQuery = query, activeFlashcardIndex = 0, isCardFlipped = false)
        }
        filterTerms()
    }

    fun flipCard() {
        _uiState.update { it.copy(isCardFlipped = !it.isCardFlipped) }
    }

    fun rateFlashcard(termId: Int, quality: Int, totalFlashcards: Int) {
        viewModelScope.launch {
            val result = repository.submitFlashcardRating(termId, quality)
            _uiState.update { it.copy(lastReviewResult = result) }
            // Les trophées ne sont pas réservés aux leçons : une session de
            // flashcards seule peut aussi en débloquer (série, cartes, objectif).
            val freshTrophies = repository.claimNewTrophies()
            if (freshTrophies.isNotEmpty()) {
                _uiState.update { it.copy(trophies = it.trophies + freshTrophies.map { t -> t.name }) }
            }
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
        val state = _uiState.value
        var exercisesForLevel = state.exercises.filter { it.level == level }
        // Leçon ouverte depuis le Parcours : le pool est cadré au module tant qu'il
        // reste au moins une question ; sinon on retombe sur tout le niveau.
        val moduleTitle = state.pathLessonModuleId?.let { id ->
            InitialData.modulesList.firstOrNull { it.id == id }?.titleFr
        }
        if (moduleTitle != null) {
            // Leçon du Parcours : le pool est strictement cadré au module. Sans
            // exercices propres, la leçon reste vide (état dédié à l'écran) plutôt
            // que de faire jouer les questions d'un autre module.
            exercisesForLevel = exercisesForLevel.filter { it.module.equals(moduleTitle, ignoreCase = true) }

            // PHASE 2 (rapport final §2) : compléter la leçon avec les questions
            // générées depuis les fiches du module. 10 modules sur 15 n'avaient
            // aucun exercice écrit main → l'écran affichait `no_exercises` et le
            // nœud pulsait indéfiniment (P0-5 les a verrouillés ; maintenant on
            // les remplit). Tirage déterministe : mêmes questions à chaque visite.
            //
            // `lessonQuestions` place déjà les exercices réels en tête et ajoute
            // le complément généré — on lui passe directement la liste filtrée.
            if (exercisesForLevel.size < LessonGenerator.LESSON_SIZE) {
                exercisesForLevel = LessonGenerator.lessonQuestions(
                    moduleId = state.pathLessonModuleId!!,
                    level = level,
                    realExercises = exercisesForLevel,
                    terms = InitialData.termsOfModule(moduleTitle)
                )
            }
        }
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

    /**
     * Ouvre la leçon `level` du module `moduleId` depuis le nœud du Parcours :
     * pool cadré module + niveau, score conservé dans `lesson_scores` en fin de leçon.
     */
    fun startPathLesson(moduleId: String, level: Int) {
        _uiState.update { it.copy(pathLessonModuleId = moduleId) }
        startLevelTrainingWithSpec(level)
    }

    fun exitLevelTraining() {
        _uiState.update {
            it.copy(
                selectedLearningLevel = null,
                pathLessonModuleId = null,
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
                val preStats = _uiState.value.userStats
                val now = repository.currentClock.now()
                val today = GamificationEconomy.todayKey(now)
                val beforeToday = if (preStats.goalDate == today) preStats.xpToday else 0
                val goalHit = beforeToday < preStats.dailyGoal &&
                    beforeToday + state.levelScore >= preStats.dailyGoal

                // Leçon du Parcours : meilleur score conservé pour le déblocage ≥ 70 %.
                val moduleId = state.pathLessonModuleId
                if (moduleId != null) {
                    repository.saveLessonScore(moduleId, level, percentage)
                }
                val justUnlocked = repository.recordLevelCompletion(
                    level = level,
                    scorePercentage = percentage,
                    pointsEarned = state.levelScore,
                    totalQuestions = total,
                    correctAnswers = correct
                )
                repository.earnChest(ChestSource.QUIZ, correct)
                val freshTrophies = repository.claimNewTrophies()
                if (justUnlocked && level < 6) {
                    val nextLevelObj = com.aistudio.medlingua.dzmed.domain.learning.LearningLevel.fromNumber(level + 1)
                    com.aistudio.medlingua.dzmed.service.NotificationHelper.showLevelUnlockedNotification(
                        app,
                        level + 1,
                        nextLevelObj.titleFr,
                        lang = _uiState.value.currentLanguage
                    )
                }
                val gems = com.aistudio.medlingua.dzmed.domain.gamification.GemsManager
                    .calculateGemsForLesson(correct, total)
                val lang = _uiState.value.currentLanguage
                _uiState.update {
                    it.copy(
                        levelIsCompleted = true,
                        levelJustUnlockedNext = justUnlocked,
                        lastReward = RewardData(
                            xp = state.levelScore,
                            gems = gems,
                            goalHit = goalHit,
                            trophies = freshTrophies.map { t ->
                                t.icon to com.aistudio.medlingua.dzmed.localization.Strings.get(t.titleKey, lang)
                            }
                        )
                    )
                }
            }
        }
    }

    fun restartCurrentLevel() {
        val level = _uiState.value.selectedLearningLevel ?: return
        startLevelTraining(level)
    }

    private var continuousJob: kotlinx.coroutines.Job? = null

    fun setTtsSpeechRate(rate: Float) {
        _uiState.update { it.copy(ttsRate = rate) }
        ttsManager.setSpeechRate(rate)
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
        // Without an explicit language, follow the app language instead of a
        // hardcoded English default
        val targetLang = lang ?: _uiState.value.currentLanguage.code
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

                // 1. Pronounce the term in the language selected in the audio studio
                val primaryLang = _uiState.value.ttsLanguage
                val secondaryLang = if (primaryLang.equals("en", ignoreCase = true)) "fr" else "en"
                val primaryText = when (primaryLang.lowercase()) {
                    "fr" -> current.termFr
                    "ar" -> current.termAr
                    else -> current.termEn
                }
                val secondaryText = when (secondaryLang.lowercase()) {
                    "fr" -> current.termFr
                    "ar" -> current.termAr
                    else -> current.termEn
                }
                speakAndWait(primaryText, primaryLang)
                delay(500)
                if (!this.isActive) break

                // 2. Pronounce the translation in the counterpart language
                speakAndWait(secondaryText, secondaryLang)
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
        } ?: run {
            // No callback ever arrived (engine stall / superseded utterance):
            // never leave the UI stuck on "speaking"
            _uiState.update { it.copy(isTtsSpeaking = false) }
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
        val prevIdx = when {
            currentIdx > 0 -> currentIdx - 1
            currentIdx == 0 -> list.size - 1
            else -> 0 // no active term: start at the first one (indexOfFirst returned -1)
        }
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
        app.getSharedPreferences(
            com.aistudio.medlingua.dzmed.service.NotificationHelper.PREFS_REMINDERS,
            android.content.Context.MODE_PRIVATE
        ).edit().putBoolean(
            com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_DAILY_REMINDER_ENABLED, enabled
        ).apply()
        if (enabled) {
            com.aistudio.medlingua.dzmed.service.NotificationHelper.scheduleDailyReminder(
                app,
                _uiState.value.reminderHour,
                _uiState.value.reminderMinute
            )
        } else {
            com.aistudio.medlingua.dzmed.service.NotificationHelper.cancelDailyReminder(app)
        }
    }

    fun toggleStreakReminder(enabled: Boolean) {
        _uiState.update { it.copy(streakReminderEnabled = enabled) }
        app.getSharedPreferences(
            com.aistudio.medlingua.dzmed.service.NotificationHelper.PREFS_REMINDERS,
            android.content.Context.MODE_PRIVATE
        ).edit().putBoolean(
            com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_STREAK_REMINDER_ENABLED, enabled
        ).apply()
        if (enabled) {
            com.aistudio.medlingua.dzmed.service.NotificationHelper.scheduleRepeatingReminder(
                app,
                com.aistudio.medlingua.dzmed.service.NotificationReceiver.ACTION_STREAK_CHECK,
                REQUEST_CODE_STREAK,
                STREAK_REMINDER_HOUR,
                0
            )
        } else {
            com.aistudio.medlingua.dzmed.service.NotificationHelper.cancelRepeatingReminder(
                app,
                com.aistudio.medlingua.dzmed.service.NotificationReceiver.ACTION_STREAK_CHECK,
                REQUEST_CODE_STREAK
            )
        }
    }

    fun toggleClinicalPearlReminder(enabled: Boolean) {
        _uiState.update { it.copy(clinicalPearlReminderEnabled = enabled) }
        app.getSharedPreferences(
            com.aistudio.medlingua.dzmed.service.NotificationHelper.PREFS_REMINDERS,
            android.content.Context.MODE_PRIVATE
        ).edit().putBoolean(
            com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_PEARL_REMINDER_ENABLED, enabled
        ).apply()
        if (enabled) {
            com.aistudio.medlingua.dzmed.service.NotificationHelper.scheduleRepeatingReminder(
                app,
                com.aistudio.medlingua.dzmed.service.NotificationReceiver.ACTION_CLINICAL_PEARL,
                REQUEST_CODE_PEARL,
                PEARL_REMINDER_HOUR,
                0
            )
        } else {
            com.aistudio.medlingua.dzmed.service.NotificationHelper.cancelRepeatingReminder(
                app,
                com.aistudio.medlingua.dzmed.service.NotificationReceiver.ACTION_CLINICAL_PEARL,
                REQUEST_CODE_PEARL
            )
        }
    }

    fun setReminderTime(hour: Int, minute: Int) {
        _uiState.update { it.copy(reminderHour = hour, reminderMinute = minute) }
        // P0-4 (rapport final, bug B4) : persister l'heure. Sans cela, elle
        // n'était en mémoire que dans UiState, et le BootReceiver réarmait à
        // 20:00 au prochain redémarrage, ignorant le choix de l'utilisateur.
        app.getSharedPreferences(
            com.aistudio.medlingua.dzmed.service.NotificationHelper.PREFS_REMINDERS,
            android.content.Context.MODE_PRIVATE
        ).edit().apply {
            putInt(com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_REMINDER_HOUR, hour)
            putInt(com.aistudio.medlingua.dzmed.service.NotificationHelper.KEY_REMINDER_MINUTE, minute)
            apply()
        }
        if (_uiState.value.notificationsEnabled) {
            com.aistudio.medlingua.dzmed.service.NotificationHelper.scheduleDailyReminder(app, hour, minute)
        }
    }

    fun sendTestNotification(type: String) {
        when (type) {
            "review" -> {
                // Nothing due: a "0 flashcards ready" notification is pure noise
                val dueCount = _uiState.value.dueFlashcardProgress.size
                if (dueCount > 0) {
                    com.aistudio.medlingua.dzmed.service.NotificationHelper.showReviewReminder(
                        app,
                        dueCount = dueCount,
                        lang = _uiState.value.currentLanguage
                    )
                } else {
                    android.util.Log.i("MedLinguaVM", "Review reminder test skipped: no flashcard is due")
                }
            }
            "streak" -> {
                com.aistudio.medlingua.dzmed.service.NotificationHelper.showStreakReminder(
                    app,
                    streakDays = _uiState.value.userStats.streakDays,
                    lang = _uiState.value.currentLanguage
                )
            }
            "pearl" -> {
                val pearlTerm = _uiState.value.terms.firstOrNull { it.clinicalPearl.isNotBlank() }
                com.aistudio.medlingua.dzmed.service.NotificationHelper.showClinicalPearlNotification(
                    app,
                    pearlTerm,
                    lang = _uiState.value.currentLanguage
                )
            }
            "level" -> {
                val levelNumber = _uiState.value.userStats.highestUnlockedLevel()
                com.aistudio.medlingua.dzmed.service.NotificationHelper.showLevelUnlockedNotification(
                    app,
                    levelNumber = levelNumber,
                    levelName = com.aistudio.medlingua.dzmed.domain.learning.LearningLevel.fromNumber(levelNumber).titleFr,
                    lang = _uiState.value.currentLanguage
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

    fun applyDiagnosticResult(result: com.aistudio.medlingua.dzmed.domain.diagnostic.PlacementResult) {
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
            // L'XP du diagnostic transite par GamificationEconomy.addXp comme toute
            // autre source : série, objectif quotidien, XP hebdo de ligue.
            val outcome = GamificationEconomy.addXp(
                current,
                result.score * 5,
                repository.currentClock.now()
            )
            repository.saveUserStats(outcome.stats)
            _uiState.update { it.copy(userStats = outcome.stats) }
        }
    }

    // ========================================================
    // DUOLINGO PHASE 2: GEMS & SUPER (delegated)
    // Les cœurs ont disparu : plus de dialogue, plus de leçon bloquée.
    // ========================================================

    fun purchaseSuper(months: Int = 1) {
        viewModelScope.launch {
            gamificationViewModel.purchaseSuper(months)
            _uiState.update { it.copy(showSuperPaywall = false) }
        }
    }

    /** Ferme la pop-up de récompense (spec §11). */
    fun dismissReward() {
        _uiState.update { it.copy(lastReward = null, pendingChestReward = null) }
    }

    /** Ouvre une caisse en attente depuis la pop-up de récompense. */
    fun openPendingChest() {
        viewModelScope.launch {
            val reward = repository.openChest()
            if (reward != null) {
                _uiState.update { it.copy(pendingChestReward = reward) }
            }
        }
    }

    /**
     * Ouvre la pop-up de récompense vide depuis le badge caisse du Parcours
     * (ouvrir une caisse sans avoir terminé une leçon).
     */
    fun openChestRewardPopup() {
        _uiState.update {
            it.copy(lastReward = RewardData(xp = 0, gems = 0, goalHit = false, trophies = emptyList()))
        }
    }

    /** Congélation de série ❄️ : 200 💎 (refusé si les gemmes manquent). */
    fun buyFreeze() {
        viewModelScope.launch { repository.buyFreeze() }
    }

    /** Objectif quotidien : 20 / 50 / 100 XP. */
    fun setDailyGoal(goal: Int) {
        viewModelScope.launch { repository.setDailyGoal(goal) }
    }

    fun showSuperPaywall(show: Boolean) {
        _uiState.update { it.copy(showSuperPaywall = show) }
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
        // Always derive the displayed spec from the entity: swapLanguage() overwrites the
        // display slot (promptEn), so a swapped spec must never be reused as a source.
        val spec = entity.toExerciseSpec()
        _uiState.update {
            it.copy(
                currentExerciseSpec = spec,
                levelSelectedOption = null,
                levelIsAnswered = false
            )
        }
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
