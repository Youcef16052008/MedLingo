package com.aistudio.medlingua.dzmed.domain.gamification.audio

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import com.aistudio.medlingua.dzmed.service.TtsManager

/** Types de retours sonores pour la gamification. */
enum class SoundEffect {
    CORRECT,          // ✅ Bonne réponse — tintement léger
    WRONG,            // ❌ Mauvaise réponse — bruit discret
    PERFECT,          // 100% de bonnes réponses — mention honorifique
    LEVEL_UP,         // Déblocage d'un nouveau niveau
    STREAK,           // Série maintenue — 🔥
    GOAL_REACHED,     // Objectif quotidien atteint
    CHEST,            // Caisse 🎁
    ERROR,            // Erreur générale (ex: rien à faire)
    TASK_FAIL         // Échec de chargement ou autre
}

/**
 * Utilitaire de musique et de sons pour la gamification.
 *
 * Les effets sont synthétisés par [ToneGenerator] (aucun asset à charger, donc
 * aucun coût d'APK ni d'I/O au premier son).
 *
 * Trois pièges corrigés ici :
 *  - le générateur était construit **au chargement de l'objet** : il réservait
 *    une piste audio même si l'utilisateur n'entendait jamais un son, et
 *    pouvait lever une `RuntimeException` hors du try/catch de l'appelant ;
 *  - `release()`/`stop()` libéraient le générateur sans jamais le recréer,
 *    donc un seul `stop()` rendait tous les sons suivants silencieux pour de
 *    bon (`startTone` sur un générateur libéré lève `IllegalStateException`).
 *
 * Le flux était aussi `ToneGenerator(4, …)`, c'est-à-dire `STREAM_ALARM` (le
 * commentaire d'origine disait « TYPE_RINGTONE », ce qui est faux) : les
 * effets sortaient au volume des alarmes, en contournant le mode silencieux et
 * la molette de volume média. Ils passent maintenant par `STREAM_MUSIC`.
 */
object SoundManager {

    private var tts: TtsManager? = null
    private var initialized = false

    /** Construit à la demande, et reconstruit après [stop]/[release]. */
    private var toneGen: ToneGenerator? = null

    /** Préférence utilisateur : couper les effets sonores sans couper la TTS. */
    @Volatile
    private var muted = false

    /** Prépare le gestionnaire audio. Doit être appelé après l'initialisation de l'application. */
    @Synchronized
    fun init(context: Context) {
        if (initialized) return
        initialized = true
        tts = TtsManager(context.applicationContext)
    }

    /** Coupe (ou rétablit) les effets sonores de gamification. */
    @Synchronized
    fun setMuted(value: Boolean) {
        muted = value
        if (value) releaseToneGenerator()
    }

    fun isMuted(): Boolean = muted

    /** Joue un son selon le type donné. Ne fait jamais planter l'appelant. */
    fun play(sound: SoundEffect) {
        if (!initialized || muted) return
        val generator = toneGenerator() ?: return
        try {
            generator.startTone(toneFor(sound), durationFor(sound))
        } catch (e: IllegalStateException) {
            // Piste audio perdue (appel entrant, autre app en exclusif…) :
            // on la libère, elle sera recréée au prochain son.
            releaseToneGenerator()
        } catch (e: RuntimeException) {
            releaseToneGenerator()
        }
    }

    /** Libère toutes les ressources sonores. */
    @Synchronized
    fun release() {
        releaseToneGenerator()
        tts?.stop()
    }

    /** Arrête la lecture en cours, sans désactiver les sons suivants. */
    @Synchronized
    fun stop() {
        releaseToneGenerator()
        tts?.stop()
    }

    /** Met en file d'attente une phrase TTS. */
    fun speak(text: String) {
        tts?.speak(text)
    }

    @Synchronized
    private fun toneGenerator(): ToneGenerator? {
        toneGen?.let { return it }
        return try {
            ToneGenerator(AudioManager.STREAM_MUSIC, 100).also { toneGen = it }
        } catch (e: RuntimeException) {
            // Pas de sortie audio disponible : on reste silencieux.
            null
        }
    }

    @Synchronized
    private fun releaseToneGenerator() {
        try {
            toneGen?.release()
        } catch (e: RuntimeException) {
            // Rien à faire : le générateur est déjà hors service.
        } finally {
            toneGen = null
        }
    }

    private fun toneFor(sound: SoundEffect): Int = when (sound) {
        SoundEffect.CORRECT -> ToneGenerator.TONE_DTMF_1
        SoundEffect.WRONG -> ToneGenerator.TONE_DTMF_0
        SoundEffect.PERFECT -> ToneGenerator.TONE_SUP_RINGTONE
        SoundEffect.LEVEL_UP -> ToneGenerator.TONE_SUP_RINGTONE
        SoundEffect.STREAK -> ToneGenerator.TONE_SUP_RINGTONE
        SoundEffect.GOAL_REACHED -> ToneGenerator.TONE_SUP_RINGTONE
        SoundEffect.CHEST -> ToneGenerator.TONE_SUP_RINGTONE
        SoundEffect.ERROR -> ToneGenerator.TONE_DTMF_1
        SoundEffect.TASK_FAIL -> ToneGenerator.TONE_SUP_RINGTONE
    }

    private fun durationFor(sound: SoundEffect): Int = when (sound) {
        SoundEffect.CORRECT -> 200
        SoundEffect.WRONG -> 200
        SoundEffect.PERFECT -> 400
        SoundEffect.LEVEL_UP -> 500
        SoundEffect.STREAK -> 600
        SoundEffect.GOAL_REACHED -> 400
        SoundEffect.CHEST -> 500
        SoundEffect.ERROR -> 300
        SoundEffect.TASK_FAIL -> 300
    }
}
