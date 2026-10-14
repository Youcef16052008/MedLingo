package com.aistudio.medlingua.dzmed.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aistudio.medlingua.dzmed.data.local.database.MedLinguaDatabase
import com.aistudio.medlingua.dzmed.data.local.entity.DownloadedModuleEntity
import com.aistudio.medlingua.dzmed.data.local.entity.MedicalTermEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Régression P0-1 (revue database v2, défaut D1).
 *
 * Le reseed incrémental était écriture par écriture HORS transaction. Un process
 * tué à mi-chemin laissait `getTermsCount()` complet mais des modules et des
 * bookmarks incomplets — or c'est cette même garde qui décidait de ne plus
 * jamais reseeder.
 *
 * On ne peut pas tuer le process en test, mais on peut prouver la propriété qui
 * compte : la transaction est **tout-ou-rien**. Si la dernière écriture échoue,
 * RIEN n'est écrit — la base reste dans son état antérieur, et la garde pourra
 * rejouer le reseed au prochain démarrage.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ReseedTransactionTest {

    private lateinit var db: MedLinguaDatabase
    private lateinit var dao: MedicalDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MedLinguaDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.medicalDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    private fun term(id: Int, module: String) = MedicalTermEntity(
        id = id, termEn = "term-$id", termFr = "terme-$id", termAr = "مصطلح-$id",
        definitionEn = "def", definitionFr = "def", definitionAr = "def",
        module = module, chapter = "ch"
    )

    @Test
    fun `le reseed normal ecrit termes, bookmarks et modules ensemble`() = runBlocking {
        // État antérieur : 2 termes, 1 bookmarké.
        dao.insertTerms(listOf(term(1, "Anatomie"), term(2, "Anatomie")))
        dao.restoreBookmark("term-1", "Anatomie")

        // Reseed vers 3 termes + 1 module.
        dao.reseedTermsAndModules(
            terms = listOf(term(1, "Anatomie"), term(2, "Anatomie"), term(3, "Anatomie")),
            bookmarkedIds = dao.getBookmarkedIds(),
            modules = listOf(
                DownloadedModuleEntity("anatomie", "Anatomie", 0L, 1.0, 3, 0)
            )
        )

        assertEquals("3 termes après reseed", 3, dao.getTermsCount())
        // Le bookmark a survécu au REPLACE puis été restauré.
        val bookmarked = dao.getBookmarkedKeys()
        assertEquals("bookmark préservé", 1, bookmarked.size)
        assertEquals("term-1", bookmarked.first().termEn)
        assertEquals("1 module après reseed", 1, dao.getAllDownloadedModules().first().size)
    }

    @Test
    fun `un reseed qui echoue n'ecrit rien`() = runBlocking {
        // État antérieur : 2 termes, 1 module.
        dao.insertTerms(listOf(term(1, "Anatomie"), term(2, "Anatomie")))
        dao.upsertDownloadedModule(DownloadedModuleEntity("anatomie", "Anatomie", 0L, 1.0, 2, 0))

        // Injecteur d'échec : un trigger SQLite qui fait ÉCHOUER un insert dans
        // `medical_terms` (RAISE ABORT). Sert uniquement à déclencher l'échec à
        // l'intérieur de la transaction — sans lui, Room (REPLACE/UPSERT)
        // n'échoue jamais et l'atomicité n'est pas testable.
        db.openHelper.writableDatabase.execSQL(
            """
            CREATE TRIGGER IF NOT EXISTS trg_injecteur_reseed
            BEFORE INSERT ON medical_terms
            FOR EACH ROW
            WHEN NEW.id = 3
            BEGIN
                SELECT RAISE(ABORT, 'echec injecte');
            END;
            """.trimIndent()
        )

        // Le reseed doit échouer : l'insert du terme id=3 déclenche le trigger.
        var aEchoue = false
        try {
            dao.reseedTermsAndModules(
                terms = listOf(term(1, "Anatomie"), term(2, "Anatomie"), term(3, "Anatomie")),
                bookmarkedIds = emptyList(),
                modules = listOf(DownloadedModuleEntity("anatomie", "Anatomie", 0L, 1.0, 3, 0))
            )
        } catch (e: android.database.sqlite.SQLiteException) {
            aEchoue = true
        }

        assertTrue("le reseed doit échouer sur le trigger injecté", aEchoue)

        // Propriété clé (défaut D1) : RIEN n'a été écrit. La transaction est
        // tout-ou-rien : les 3 insertions de termes sont annulées, le module
        // n'est pas mis à jour. Sans @Transaction, la base aurait eu 3 termes
        // + 1 module à 3 — mais si le module avait échoué, on se retrouvait
        // avec getTermsCount() complet et des modules incomplets, et la garde
        // ne reseedait plus jamais.
        assertEquals(
            "aucun terme n'a été écrit — l'état antérieur est intact",
            2, dao.getTermsCount()
        )
        val modules = dao.getAllDownloadedModules().first()
        assertEquals("le module n'a pas été mis à jour", 1, modules.size)
        assertEquals(
            "le module est resté à sa valeur antérieure",
            2, modules.first().termsCount
        )
    }

    /**
     * Régression P1-2 (défaut D4) : un seed qui renomme un terme ne doit plus
     * perdre les bookmarks. L'ancienne restauration par (termEn, module)
     * n'aurait plus matché après renommage.
     */
    @Test
    fun `un bookmark survit au renommage de son terme`() = runBlocking {
        // État antérieur : terme id=1, "Heart", bookmarké.
        dao.insertTerms(listOf(term(1, "Anatomie").copy(termEn = "Heart")))
        dao.restoreBookmark("Heart", "Anatomie")
        assertEquals(1, dao.getBookmarkedIds().size)

        // Le seed renomme "Heart" -> "Cor" (même id=1).
        val renamed = term(1, "Anatomie").copy(termEn = "Cor")
        dao.reseedTermsAndModules(
            terms = listOf(renamed),
            bookmarkedIds = dao.getBookmarkedIds(), // [1], sauvegardé par id
            modules = emptyList()
        )

        // Le bookmark est préservé : la restauration par id matche encore.
        assertEquals("le bookmark a survécu au renommage", 1, dao.getBookmarkedKeys().size)
    }
}
