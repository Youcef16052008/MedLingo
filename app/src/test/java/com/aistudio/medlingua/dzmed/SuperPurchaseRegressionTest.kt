package com.aistudio.medlingua.dzmed

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aistudio.medlingua.dzmed.data.local.database.MedLinguaDatabase
import com.aistudio.medlingua.dzmed.data.local.entity.UserStatsEntity
import com.aistudio.medlingua.dzmed.data.repository.MedLinguaRepository
import com.aistudio.medlingua.dzmed.domain.gamification.GemsManager
import com.aistudio.medlingua.dzmed.domain.time.Clock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Régression du paywall Super (Phase 6).
 *
 * Avant : `purchaseSuper()` mettait `isSuper = true` **gratuitement** et
 * enregistrait une transaction `amount = 0`, tandis que le paywall affichait
 * "500 DA/mois". Une fausse porte — l'utilisateur croyait payer, rien ne se
 * passait, et les gemmes n'étaient jamais déduites.
 *
 * Maintenant : l'abonnement se paie en gemmes (500/mois, 5000/an), la monnaie
 * réelle de l'app offline-first. Le test couvre :
 *
 *  - l'achat réussit et déduit les gemmes ;
 *  - `isSuper` est actif ;
 *  - un solde insuffisant échoue **et ne change rien** (ni gemmes, ni isSuper).
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class SuperPurchaseRegressionTest {

    private lateinit var db: MedLinguaDatabase
    private lateinit var repo: MedLinguaRepository
    private val clock = object : Clock {
        override fun now(): Long = 1_000_000L
    }

    @Before
    fun setup() {
        val ctx = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(ctx, MedLinguaDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        repo = MedLinguaRepository(db.medicalDao(), clock)
    }

    @After
    fun teardown() {
        db.close()
    }

    private suspend fun seedStats(gems: Int): UserStatsEntity {
        val stats = UserStatsEntity.createFresh(clock).copy(gems = gems)
        db.medicalDao().upsertUserStats(stats)
        return stats
    }

    @Test
    fun `l achat mensuel deduit 500 gemmes et active super`() = runTest {
        seedStats(gems = 1000)

        val ok = repo.purchaseSuper(1)

        assertTrue("l'achat doit reussir", ok)
        val after = db.medicalDao().getUserStatsOnce()!!
        assertEquals("solde apres achat mensuel", 500, after.gems)
        assertTrue(after.isSuperActive(clock))
    }

    @Test
    fun `l achat annuel deduit 5000 gemmes et active super 12 mois`() = runTest {
        seedStats(gems = 6000)

        val ok = repo.purchaseSuper(12)

        assertTrue(ok)
        val after = db.medicalDao().getUserStatsOnce()!!
        assertEquals("solde apres achat annuel", 1000, after.gems)
        assertTrue(after.isSuperActive(clock))
    }

    @Test
    fun `un solde insuffisant echoue et ne change rien`() = runTest {
        seedStats(gems = 100)

        val ok = repo.purchaseSuper(1)

        assertFalse("l'achat doit etre refuse", ok)
        val after = db.medicalDao().getUserStatsOnce()!!
        // Rien n'a bougé : ni les gemmes, ni l'abonnement
        assertEquals(100, after.gems)
        assertFalse(after.isSuperActive(clock))
    }

    @Test
    fun `le ledger enregistre l achat avec un montant negatif`() = runTest {
        seedStats(gems = 1000)

        repo.purchaseSuper(1)

        val txs = db.medicalDao().getAllGemsTransactions().first()
        val purchase = txs.single { it.type == "PURCHASE" }
        assertEquals(
            "le montant doit etre negatif (une depense), pas 0 comme avant",
            -GemsManager.GEMS_SUPER_MONTHLY,
            purchase.amount
        )
        assertEquals(500, purchase.balanceAfter)
    }

    @Test
    fun `le pricing n expose que des montants en gemmes`() {
        // L'app etant offline-first, aucun paiement fiat n'existe : le pricing
        // ne doit exposer que des montants en gemmes.
        val pricing = GemsManager.getSuperPricing()
        assertEquals(GemsManager.GEMS_SUPER_MONTHLY, pricing.monthlyGems)
        assertEquals(5000, pricing.yearlyGems)
        assertTrue(
            "l'annuel doit etre une remise par rapport au mensuel x12",
            pricing.yearlyGems < pricing.monthlyGems * 12
        )
    }
}
