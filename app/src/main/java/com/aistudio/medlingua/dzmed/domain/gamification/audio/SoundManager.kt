package com.aistudio.medlingua.dzmed.domain.gamification.audio

import android.content.Context
import android.media.MediaPlayer
import android.media.ToneGenerator
import java.util.concurrent.ConcurrentHashMap
import com.aistudio.medlingua.dzmed.service.TtsManager

/** Types de retours sonores pour la gamification. */
enum class SoundEffect {
    CORRECT,          // ✅ Bonne réponse — tintement léger
    WRONG,            // ❌ Mauvaise réponse — bruit discret
    PERFECT,          // 100% de bonnes réponses — mention honorifique
    LEVEL_UP,         // Déblocage d'un nouveau niveau
    STREAK,           // Séries chiffonnées — 🌋
    GOAL_REACHED,     // Objectif quotidien atteint
    CHEST,            // Caisse 🎁
    ERROR,            // Erreur générale (ex: rien à faire)
    TASK_FAIL         // Echec de chargement ou autre
}

/** Utilitaire de musique et de sons pour la gamification. */
object SoundManager {

    private val players = ConcurrentHashMap<SoundEffect, MediaPlayer>()

    private var tts: TtsManager? = null
    private var initialized = false

    /** Prépare le gestionnaire audio. Doit être appelé après l'initialisation de l'application. */
    fun init(context: Context) {
        if (initialized) return
        initialized = true
        tts = TtsManager(context)
    }

    /** Joue un son selon le type donné. */
    fun play(sound: SoundEffect) {
        if (!initialized) return

        // Appel système pour l'effet sonore
        playSystemTone(sound)

        // Tentative de stockage si disponible (non bloquant)
        try {
            players.remove(sound)?.release()
        } catch (e: Exception) {
            // Silence
        }
    }

    /** Libère toutes les ressources sonores. */
    fun release() {
        players.values.forEach { if (it.isPlaying) it.stop() }
        players.clear()
        try { toneGen.release() } catch (e: Exception) {}
        tts?.stop()
    }

    /** Met en file d'attente une phrase TTS. */
    fun speak(text: String) {
        tts?.speak(text)
    }

    /** Arrête la lecture en cours. */
    fun stop() {
        players.values.forEach { if (it.isPlaying) it.stop() }
        players.clear()
        try { toneGen.release() } catch (e: Exception) {}
        tts?.stop()
    }

    private val toneGen = ToneGenerator(4, 100)  // TYPE_RINGTONE = 4

    private fun playSystemTone(sound: SoundEffect) {
        when (sound) {
            SoundEffect.CORRECT -> toneGen.startTone(ToneGenerator.TONE_DTMF_1, 200)
            SoundEffect.WRONG -> toneGen.startTone(ToneGenerator.TONE_DTMF_0, 200)
            SoundEffect.PERFECT -> toneGen.startTone(ToneGenerator.TONE_SUP_RINGTONE, 400)
            SoundEffect.LEVEL_UP -> toneGen.startTone(ToneGenerator.TONE_SUP_RINGTONE, 500)
            SoundEffect.STREAK -> toneGen.startTone(ToneGenerator.TONE_SUP_RINGTONE, 600)
            SoundEffect.GOAL_REACHED -> toneGen.startTone(ToneGenerator.TONE_SUP_RINGTONE, 400)
            SoundEffect.CHEST -> toneGen.startTone(ToneGenerator.TONE_SUP_RINGTONE, 500)
            SoundEffect.ERROR -> toneGen.startTone(ToneGenerator.TONE_DTMF_1, 300)
            SoundEffect.TASK_FAIL -> toneGen.startTone(ToneGenerator.TONE_SUP_RINGTONE, 300)
        }
    }
}
