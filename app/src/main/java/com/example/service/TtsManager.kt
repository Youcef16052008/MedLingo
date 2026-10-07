package com.example.service

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import java.util.Locale
import java.util.concurrent.atomic.AtomicInteger

class TtsManager(context: Context) {
    private val appContext = context.applicationContext
    private var tts: TextToSpeech? = null
    @Volatile
    private var isReady = false
    // Bumped on every engine (re)creation and on shutdown: a late onInit from a
    // superseded or shut-down engine must never mark the manager ready again.
    private var engineGeneration = 0
    private var initFailed = false
    private var currentRate = 0.9f
    private var currentPitch = 1.0f
    private var currentLangCode = "en"
    private var languageApplied = false

    private data class UtteranceCallbacks(
        val onStart: (() -> Unit)?,
        val onDone: (() -> Unit)?,
        val onError: (() -> Unit)?
    )

    private data class PendingSpeak(
        val text: String,
        val languageCode: String,
        val speechRate: Float,
        val pitch: Float,
        val onStart: (() -> Unit)?,
        val onDone: (() -> Unit)?,
        val onError: (() -> Unit)?
    )

    // Speak requested while the engine is still (re)initializing: flushed on init
    // success, dropped on stop()/shutdown(), errored on init failure.
    private var pendingSpeak: PendingSpeak? = null

    // Callbacks keyed by utteranceId: the late onDone/onError of a superseded
    // utterance (QUEUE_FLUSH + stop) must never fire the callbacks of the
    // utterance that replaced it.
    private val pendingCallbacks = mutableMapOf<String, UtteranceCallbacks>()
    private val utteranceSeq = AtomicInteger(0)

    init {
        initializeEngine()
    }

    private fun initializeEngine() {
        val generation = ++engineGeneration
        isReady = false
        initFailed = false
        try {
            tts = TextToSpeech(appContext) { status -> handleInit(generation, status) }
        } catch (e: Exception) {
            Log.e("TtsManager", "Error initializing TTS", e)
            initFailed = true
            failPendingSpeak()
        }
    }

    private fun handleInit(generation: Int, status: Int) {
        if (generation != engineGeneration) {
            // Stale engine: already shut down or replaced by a newer one.
            return
        }
        if (status == TextToSpeech.SUCCESS) {
            isReady = true
            initFailed = false
            setupProgressListener()
            // Fresh engine: the language must be applied again even if unchanged.
            languageApplied = false
            applyLanguage(currentLangCode)
            tts?.setSpeechRate(currentRate)
            tts?.setPitch(currentPitch)
            Log.d("TtsManager", "TTS successfully initialized")
            pendingSpeak?.let { pending ->
                pendingSpeak = null
                speakInternal(pending)
            }
        } else {
            Log.e("TtsManager", "TTS initialization failed status=$status")
            initFailed = true
            failPendingSpeak()
        }
    }

    private fun failPendingSpeak() {
        pendingSpeak?.onError?.invoke()
        pendingSpeak = null
    }

    private fun setupProgressListener() {
        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                utteranceId?.let { pendingCallbacks[it] }?.onStart?.invoke()
            }

            override fun onDone(utteranceId: String?) {
                utteranceId?.let { pendingCallbacks.remove(it) }?.onDone?.invoke()
            }

            @Deprecated("Deprecated in Java")
            override fun onError(utteranceId: String?) {
                utteranceId?.let { pendingCallbacks.remove(it) }?.onError?.invoke()
            }

            override fun onError(utteranceId: String?, errorCode: Int) {
                Log.w("TtsManager", "TTS utterance error code: $errorCode")
                utteranceId?.let { pendingCallbacks.remove(it) }?.onError?.invoke()
            }
        })
    }

    fun setSpeechRate(rate: Float) {
        currentRate = rate
        if (isReady) {
            tts?.setSpeechRate(rate)
        }
    }

    fun setPitch(pitch: Float) {
        currentPitch = pitch
        if (isReady) {
            tts?.setPitch(pitch)
        }
    }

    private fun localeFor(languageCode: String): Locale = when (languageCode.lowercase().trim()) {
        "fr", "french", "français" -> Locale.FRENCH
        "ar", "arabic", "arabe" -> Locale("ar")
        "en-gb" -> Locale.UK
        else -> Locale.US
    }

    private fun applyLanguage(languageCode: String): Boolean {
        val engine = tts ?: return false
        if (!isReady) return false
        // Only (re)apply when the language actually changed: setLanguage is
        // asynchronous and would truncate the utterance that follows it.
        if (languageApplied && languageCode.equals(currentLangCode, ignoreCase = true)) return true

        val requested = localeFor(languageCode)
        val generic = Locale(requested.language)
        when {
            engine.isLanguageAvailable(requested) >= TextToSpeech.LANG_AVAILABLE -> {
                engine.setLanguage(requested)
            }
            generic != requested &&
                engine.isLanguageAvailable(generic) >= TextToSpeech.LANG_AVAILABLE -> {
                engine.setLanguage(generic)
            }
            else -> {
                // No voice for this language on this device: keep the current voice
                // rather than going silent — the text is always on screen anyway.
                Log.w(
                    "TtsManager",
                    "Language '$languageCode' unavailable " +
                        "(isLanguageAvailable=${engine.isLanguageAvailable(requested)}), " +
                        "speaking with the current voice"
                )
            }
        }
        currentLangCode = languageCode
        languageApplied = true
        // Always true: speaking with an approximate voice beats silence.
        return true
    }

    fun speak(
        text: String,
        languageCode: String = "en",
        speechRate: Float = currentRate,
        pitch: Float = currentPitch,
        onStart: (() -> Unit)? = null,
        onDone: (() -> Unit)? = null,
        onError: (() -> Unit)? = null
    ) {
        if (text.isBlank()) {
            onError?.invoke()
            return
        }
        if (!isReady || tts == null || initFailed) {
            // Engine still starting, previously shut down, or its init failed:
            // (re)create it and speak as soon as it is ready. Callers that need a
            // guaranteed callback time out (the ViewModel wraps this in a timeout).
            pendingSpeak = PendingSpeak(text, languageCode, speechRate, pitch, onStart, onDone, onError)
            if (tts == null || initFailed) {
                initializeEngine()
            }
            return
        }
        speakInternal(PendingSpeak(text, languageCode, speechRate, pitch, onStart, onDone, onError))
    }

    private fun speakInternal(p: PendingSpeak) {
        try {
            applyLanguage(p.languageCode)

            // Supersede any pending utterance and drop its callbacks so only
            // this utterance's callbacks can fire.
            stop()

            val utteranceId = "MedLingua_${utteranceSeq.incrementAndGet()}"
            pendingCallbacks[utteranceId] = UtteranceCallbacks(p.onStart, p.onDone, p.onError)

            tts?.setSpeechRate(p.speechRate)
            tts?.setPitch(p.pitch)

            val params = Bundle().apply {
                putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 1.0f)
            }
            tts?.speak(p.text, TextToSpeech.QUEUE_FLUSH, params, utteranceId)
        } catch (e: Exception) {
            Log.e("TtsManager", "Error playing speech", e)
            pendingCallbacks.values.remove(UtteranceCallbacks(p.onStart, p.onDone, p.onError))
            p.onError?.invoke()
        }
    }

    fun stop() {
        // A queued-but-not-started speak is cancelled too.
        pendingSpeak = null
        try {
            tts?.stop()
        } catch (e: Exception) {
            Log.e("TtsManager", "Error stopping TTS", e)
        } finally {
            pendingCallbacks.clear()
        }
    }

    fun shutdown() {
        engineGeneration++ // invalidate any in-flight onInit
        isReady = false
        initFailed = false
        pendingSpeak = null
        try {
            tts?.stop()
            tts?.shutdown()
        } catch (e: Exception) {
            Log.e("TtsManager", "Error shutting down TTS", e)
        } finally {
            tts = null // next speak() re-creates the engine
            pendingCallbacks.clear()
        }
    }
}
