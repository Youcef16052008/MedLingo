package com.example.data.remote

import com.example.data.local.entity.UserStatsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL

/**
 * Couche réseau de MedLingo — branchement optionnel du backend Supabase.
 *
 * ## Principe : offline-first, le réseau est une amélioration, pas une dépendance
 *
 * L'app fonctionne **totalement hors-ligne** depuis sa base Room locale. Quand
 * un backend Supabase est configuré (voir `BackendConfig`), ce client :
 *
 *  - pousse la progression (XP, série, ligues) pour le multi-appareil ;
 *  - récupère le classement de ligue réel (humains au lieu des bots) ;
 *  - synchronise les gemmes et l'abonnement Super.
 *
 * **Aucune donnée n'est perdue si le réseau échoue** : la base Room reste la
 * source de vérité, le backend est miroir. C'est le pattern "local master,
 * remote replica" — l'inverse du pattern Duolingo classique (serveur master),
 * volontairement : le public cible (PCEM1 algériens) a un réseau instable.
 *
 * ## Sécurité
 *
 * L'URL du backend et la clé sont lues via [BackendConfig], qui les tire du
 * fichier `secrets.properties` (via secrets-gradle-plugin) — **jamais** en dur
 * dans le code. La clé utilisée est la clé `anon` publique Supabase, dont le
 * scope est limité par les politiques RLS de `002_security_rls.sql`. La clé
 * `service_role` ne quitte jamais l'appareil — elle reste côté Edge Functions.
 *
 * @see BackendConfig pour activer/désactiver le backend
 */
class MedLingoRemoteClient(
    private val config: BackendConfig,
    private val json: Json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
) {

    /** Le backend est-il configuré ? Si non, tous les appels sont des no-ops. */
    val isEnabled: Boolean get() = config.isEnabled

    /**
     * Pousse la progression locale vers le backend pour la synchronisation
     * multi-appareils. Silencieux en cas d'échec — la base locale reste
     * maîtresse, donc perdre une synchronisation n'altère jamais les données.
     *
     * @return `true` si la synchronisation a abouti, `false` sinon (hors-ligne
     *   ou backend non configuré). Ne lève **jamais** d'exception : l'appelant
     *   ne doit pas gérer un cas réseau.
     */
    suspend fun syncProgress(stats: UserStatsEntity, cohortId: String?): Boolean {
        if (!isEnabled) return false
        return withContext(Dispatchers.IO) {
            try {
                val payload = ProgressPayload(
                    xp = stats.totalPoints,
                    weeklyXp = stats.weeklyXp,
                    streakDays = stats.streakDays,
                    gems = stats.gems,
                    leagueCohortId = cohortId,
                    isSuper = stats.isSuper,
                    superExpiresAt = stats.superExpiresAt
                )
                post("rest/v1/user_progress", json.encodeToString(ProgressPayload.serializer(), payload))
            } catch (e: Exception) {
                // Réseau, timeout, backend en panne : la base locale reste la
                // source de vérité. Pas de propagation — l'utilisateur ne doit
                // jamais voir un crash à cause d'une sync.
                false
            }
        }
    }

    /**
     * Récupère le classement de ligue réel (humains) si le backend est
     * configuré. Retourne `null` quand le backend n'est pas joignable —
     * l'appelant retombe sur les bots locaux (`LeagueBotAnimator`), qui sont
     * la garantie que les ligues fonctionnent toujours hors-ligne.
     */
    suspend fun fetchLeaderboard(cohortId: String): List<LeaderboardEntry>? {
        if (!isEnabled) return null
        return withContext(Dispatchers.IO) {
            try {
                val raw = get("rest/v1/leaderboard?cohort_id=eq.$cohortId&order=xp.desc&limit=30")
                json.decodeFromString<List<LeaderboardEntry>>(raw)
            } catch (e: Exception) {
                null
            }
        }
    }

    // ------------------------------------------------------------------ //
    // Réseau bas niveau — volontairement sans bibliothèque pour limiter la
    // surface de dépendances. HttpURLConnection suffit pour ce volume.
    // ------------------------------------------------------------------ //

    private fun post(path: String, body: String): Boolean {
        val conn = (URL(config.baseUrl + path).openConnection() as HttpURLConnection).apply {
            requestMethod = "POST"
            connectTimeout = TIMEOUT_MS
            readTimeout = TIMEOUT_MS
            doOutput = true
            setRequestProperty("Content-Type", "application/json")
            setRequestProperty("apikey", config.apiKey)
            setRequestProperty("Authorization", "Bearer ${config.apiKey}")
        }
        return try {
            conn.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }
            conn.responseCode in 200..299
        } finally {
            conn.disconnect()
        }
    }

    private fun get(path: String): String {
        val conn = (URL(config.baseUrl + path).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = TIMEOUT_MS
            readTimeout = TIMEOUT_MS
            setRequestProperty("apikey", config.apiKey)
            setRequestProperty("Authorization", "Bearer ${config.apiKey}")
        }
        return try {
            check(conn.responseCode in 200..299) { "HTTP ${conn.responseCode}" }
            conn.inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
        } finally {
            conn.disconnect()
        }
    }

    @Serializable
    data class ProgressPayload(
        val xp: Int,
        val weeklyXp: Int,
        val streakDays: Int,
        val gems: Int,
        val leagueCohortId: String?,
        val isSuper: Boolean,
        val superExpiresAt: Long?
    )

    @Serializable
    data class LeaderboardEntry(
        val user_id: String,
        val xp: Int,
        val display_name: String
    )

    private companion object {
        const val TIMEOUT_MS = 10_000
    }
}
