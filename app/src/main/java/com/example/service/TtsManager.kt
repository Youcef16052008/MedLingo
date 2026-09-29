package com.example.service

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import java.util.Locale

class TtsManager(context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var isReady = false
    private var currentRate = 0.9f
    private var currentPitch = 1.0f
    private var currentLangCode = "en"

    private var onUtteranceDoneCallback: (() -> Unit)? = null
    private var onUtteranceStartCallback: (() -> Unit)? = null
    private var onUtteranceErrorCallback: (() -> Unit)? = null

    init {
        try {
            tts = TextToSpeech(context.applicationContext, this)
        } catch (e: Exception) {
            Log.e("TtsManager", "Error initializing TTS", e)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isReady = true
            setupProgressListener()
            applyLanguage(currentLangCode)
            tts?.setSpeechRate(currentRate)
            tts?.setPitch(currentPitch)
            Log.d("TtsManager", "TTS successfully initialized")
        } else {
            Log.e("TtsManager", "TTS initialization failed status=$status")
        }
    }

    private fun setupProgressListener() {
        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                onUtteranceStartCallback?.invoke()
            }

            override fun onDone(utteranceId: String?) {
                onUtteranceDoneCallback?.invoke()
            }

            @Deprecated("Deprecated in Java")
            override fun onError(utteranceId: String?) {
                onUtteranceErrorCallback?.invoke()
            }

            override fun onError(utteranceId: String?, errorCode: Int) {
                Log.w("TtsManager", "TTS utterance error code: $errorCode")
                onUtteranceErrorCallback?.invoke()
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

    private fun applyLanguage(languageCode: String): Boolean {
        if (!isReady || tts == null) return false
        val locale = when (languageCode.lowercase().trim()) {
            "fr", "french", "français" -> Locale.FRENCH
            "ar", "arabic", "arabe" -> Locale("ar")
            "uk", "en-gb" -> Locale.UK
            else -> Locale.US
        }

        val availability = tts?.isLanguageAvailable(locale) ?: TextToSpeech.LANG_NOT_SUPPORTED
        return if (availability >= TextToSpeech.LANG_AVAILABLE) {
            tts?.setLanguage(locale)
            currentLangCode = languageCode
            true
        } else {
            // Fallback for Arabic or French if specific accent unavailable
            if (locale.language == "ar") {
                val genericAr = Locale("ar")
                tts?.setLanguage(genericAr)
            } else if (locale.language == "fr") {
                tts?.setLanguage(Locale.FRANCE)
            } else {
                tts?.setLanguage(Locale.US)
            }
            currentLangCode = languageCode
            false
        }
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
        if (!isReady || tts == null || text.isBlank()) {
            Log.w("TtsManager", "TTS not ready or empty text")
            onError?.invoke()
            return
        }

        try {
            stop()
            onUtteranceStartCallback = onStart
            onUtteranceDoneCallback = onDone
            onUtteranceErrorCallback = onError

            applyLanguage(languageCode)
            tts?.setSpeechRate(speechRate)
            tts?.setPitch(pitch)

            val utteranceId = "MedLingua_${System.currentTimeMillis()}"
            val params = Bundle().apply {
                putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 1.0f)
            }
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, params, utteranceId)
        } catch (e: Exception) {
            Log.e("TtsManager", "Error playing speech", e)
            onError?.invoke()
        }
    }

    fun stop() {
        try {
            tts?.stop()
        } catch (e: Exception) {
            Log.e("TtsManager", "Error stopping TTS", e)
        }
    }

    fun shutdown() {
        try {
            tts?.stop()
            tts?.shutdown()
        } catch (e: Exception) {
            Log.e("TtsManager", "Error shutting down TTS", e)
        }
    }
}
