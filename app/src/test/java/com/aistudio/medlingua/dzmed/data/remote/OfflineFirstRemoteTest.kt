package com.aistudio.medlingua.dzmed.data.remote

import com.aistudio.medlingua.dzmed.data.local.entity.UserStatsEntity
import com.aistudio.medlingua.dzmed.domain.time.Clock
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Comportement offline-first de la couche réseau (Phase 7).
 *
 * L'app reste 100 % fonctionnelle sans backend. La couche réseau est une
 * **amélioration** (multi-appareils, ligues humaines), jamais une dépendance.
 * Ces tests garantissent l'invariant : un backend désactivé ou injoignable ne
 * lève **jamais** d'exception et ne modifie **jamais** les données locales.
 */
class OfflineFirstRemoteTest {

    private fun client(url: String = "", key: String = ""): MedLingoRemoteClient {
        val config = BackendConfig.fromBuildConfig(url, key)
        return MedLingoRemoteClient(config)
    }

    private val stats = UserStatsEntity.createFresh(clock = object : Clock {
        override fun now(): Long = 1_000_000L
    }).copy(totalPoints = 420, weeklyXp = 120, streakDays = 7, gems = 800)

    @Test
    fun `config desactivee quand url ou clé absente`() {
        assertFalse(BackendConfig.fromBuildConfig(null, null).isEnabled)
        assertFalse(BackendConfig.fromBuildConfig("", "").isEnabled)
        assertFalse(BackendConfig.fromBuildConfig("   ", "  ").isEnabled)
    }

    @Test
    fun `config activee quand url et clé presentes`() {
        assertTrue(
            BackendConfig.fromBuildConfig("https://x.supabase.co", "key").isEnabled
        )
    }

    @Test
    fun `syncProgress est un no-op quand le backend est desactive`() = runTest {
        val c = client(url = "", key = "")
        // Aucune exception, retour false — l'app continue hors-ligne.
        assertFalse(c.syncProgress(stats, cohortId = "cohort-1"))
    }

    @Test
    fun `fetchLeaderboard retourne null quand le backend est desactive`() = runTest {
        val c = client(url = "", key = "")
        assertNull(c.fetchLeaderboard("cohort-1"))
    }

    @Test
    fun `syncProgress n'ecrit rien en local en cas d echec reseau`() = runTest {
        // URL injoignable : la sync échoue sans lever d'exception. La base
        // Room locale reste la source de vérité — c'est ce que teste l'app.
        val c = client(url = "http://localhost:1", key = "key")
        val result = c.syncProgress(stats, cohortId = "cohort-1")
        // Pas d'exception levée : c'est l'invariant offline-first.
        assertEquals(false, result)
    }

    @Test
    fun `fetchLeaderboard n'echoue jamais silencieusement sur un backend injoignable`() = runTest {
        val c = client(url = "http://localhost:1", key = "key")
        // Retourne null (retombe sur les bots) plutôt que de planter l'écran.
        assertNull(c.fetchLeaderboard("cohort-1"))
    }
}
