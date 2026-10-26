package com.aistudio.medlingua.dzmed.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aistudio.medlingua.dzmed.MedLinguaApp
import com.aistudio.medlingua.dzmed.data.repository.StoryRepository
import com.aistudio.medlingua.dzmed.domain.story.Action
import com.aistudio.medlingua.dzmed.domain.story.SavedSession
import com.aistudio.medlingua.dzmed.domain.story.SessionState
import com.aistudio.medlingua.dzmed.domain.story.SessionSummary
import com.aistudio.medlingua.dzmed.domain.story.Story
import com.aistudio.medlingua.dzmed.domain.story.duePhrases
import com.aistudio.medlingua.dzmed.domain.story.initSession
import com.aistudio.medlingua.dzmed.domain.story.resumeSession
import com.aistudio.medlingua.dzmed.domain.story.scheduleFromSession
import com.aistudio.medlingua.dzmed.domain.story.scheduleReview
import com.aistudio.medlingua.dzmed.domain.story.sessionReducer
import com.aistudio.medlingua.dzmed.domain.story.sessionSummary
import com.aistudio.medlingua.dzmed.service.TtsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

/**
 * ViewModel du mode « histoire » — isolé de [MedLinguaViewModel] pour ne pas
 * entremêler les chantiers. Toute la logique de séance vit dans le moteur
 * (`domain/story/StorySessionEngine.kt`) ; ce VM orchestre chargement,
 * persistance, TTS et récompense. Horloge passée par appelant quand c'est
 * testable, `System.currentTimeMillis()` côté écran.
 */
class StoryViewModel(
    private val repository: StoryRepository,
    private val tts: TtsManager?
) : ViewModel() {

    data class UiState(
        val loading: Boolean = true,
        val notFound: Boolean = false,
        val story: Story? = null,
        val session: SessionState? = null,
        val input: String = "",
        val showText: Boolean = false,
        val showHelp: Boolean = false,
        val summary: SessionSummary? = null,
        val rewardedXp: Int = 0
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    private val json = Json { ignoreUnknownKeys = true }
    private val ratingsSerializer = MapSerializer(String.serializer(), Boolean.serializer())

    fun load(storyId: String) {
        if (_state.value.story != null) return
        viewModelScope.launch {
            val story = repository.storyById(storyId)
            if (story == null) {
                _state.update { it.copy(loading = false, notFound = true) }
                return@launch
            }
            val now = System.currentTimeMillis()
            val due = duePhrases(story, repository.phraseScheds(), now)
            val saved = repository.progress(story.storyId)
            val session = if (
                saved != null && saved.completedAt == 0L && saved.savedAt > 0L &&
                saved.contentVersion == story.contentVersion
            ) {
                resumeSession(
                    story,
                    SavedSession(
                        contentVersion = saved.contentVersion,
                        step = saved.step,
                        qIndex = saved.qIndex,
                        drillIndex = saved.drillIndex,
                        reviewIndex = saved.reviewIndex,
                        stats = json.decodeFromString(
                            com.aistudio.medlingua.dzmed.domain.story.SessionStats.serializer(),
                            saved.statsJson
                        ),
                        reviewRatings = json.decodeFromString(ratingsSerializer, saved.reviewRatingsJson),
                        retellAttempted = saved.retellAttempted,
                        transferTaskAttempted = saved.transferTaskAttempted,
                        transferTaskPending = saved.transferTaskPending
                    ),
                    due
                )
            } else {
                repository.startSession(story, now)
                initSession(story, due)
            }
            _state.update { it.copy(loading = false, story = story, session = session) }
        }
    }

    fun dispatch(action: Action) {
        val s = _state.value
        val story = s.story ?: return
        val session = s.session ?: return
        val next = sessionReducer(session, action, story)
        _state.update { it.copy(session = next) }
        if (!next.finished) {
            viewModelScope.launch { repository.saveProgress(next, System.currentTimeMillis()) }
        }
        // Fin de séance : planification SM-2 puis récompense idempotente.
        if (next.finished && s.summary == null) {
            viewModelScope.launch {
                val now = System.currentTimeMillis()
                val scheds = scheduleFromSession(next, story, repository.phraseScheds(), now)
                repository.savePhraseScheds(scheds, now)
                val xp = repository.claimReward(story.storyId, now)
                _state.update {
                    it.copy(summary = sessionSummary(next, story), rewardedXp = xp)
                }
            }
        }
    }

    /** Bloc REVIEW : verdict immédiat en base (survit à une interruption) + état moteur. */
    fun rateReview(phraseId: String, remembered: Boolean) {
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            val scheds = scheduleReview(phraseId, remembered, repository.phraseScheds(), now)
            repository.savePhraseScheds(scheds, now)
        }
        dispatch(Action.ReviewRated(phraseId, remembered))
        dispatch(Action.Advance)
    }

    fun setInput(value: String) = _state.update { it.copy(input = value) }

    fun toggleText() = _state.update { it.copy(showText = !it.showText) }

    fun askHelp() = _state.update { it.copy(showHelp = true) }

    fun resetForNextQuestion() = _state.update { it.copy(input = "", showHelp = false) }

    /** Narration : toujours l'anglais (langue cible), débit ralenti en option. */
    fun speakEnglish(text: String, slow: Boolean = false) {
        tts?.speak(text, languageCode = "en", speechRate = if (slow) 0.65f else 0.9f)
    }

    /** Quitter : suspendre la séance (déjà persistée) et couper la narration. */
    fun suspendSession() {
        tts?.stop()
    }
}

class StoryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val app = context.applicationContext as MedLinguaApp
        val repository = StoryRepository(app, app.database.storyDao(), app.database.medicalDao())
        return StoryViewModel(repository, app.ttsManager) as T
    }
}
