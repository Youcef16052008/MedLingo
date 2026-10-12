package com.aistudio.medlingua.dzmed.data.local.database

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.core.app.ApplicationProvider
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
 * Régression P1-1 (revue database v2, défaut D3).
 *
 * La colonne legacy `example` dupliquait `exampleEn` (692 valeurs identiques
 * sur 972) et n'était lue par personne. La migration v12 reconstruit la table
 * pour la retirer — même motif que `MIGRATION_9_10`.
 *
 * On vérifie : la colonne disparaît, les 19 autres colonnes et toutes les
 * données sont conservées, et les index v11 sont recréés.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class MigrationV12Test {

    private val dbFile = "migration-v12-test.db"
    private lateinit var helper: SupportSQLiteOpenHelper

    private fun context(): Context = ApplicationProvider.getApplicationContext()

    private fun columnNames(db: SupportSQLiteDatabase, table: String): Set<String> {
        val names = mutableSetOf<String>()
        db.query("PRAGMA table_info($table)").use { c ->
            val idx = c.getColumnIndexOrThrow("name")
            while (c.moveToNext()) names += c.getString(idx)
        }
        return names
    }

    private fun indexNames(db: SupportSQLiteDatabase, table: String): Set<String> {
        val names = mutableSetOf<String>()
        db.query("PRAGMA index_list($table)").use { c ->
            val idx = c.getColumnIndexOrThrow("name")
            while (c.moveToNext()) names += c.getString(idx)
        }
        return names
    }

    /**
     * Schéma v11 : 20 colonnes (avec `example`) + les 3 index de la migration
     * v10→v11, et 2 lignes de données dont une bookmarkée.
     */
    private fun createV11(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE medical_terms (
                id INTEGER NOT NULL PRIMARY KEY,
                termEn TEXT NOT NULL, termFr TEXT NOT NULL, termAr TEXT NOT NULL,
                definitionEn TEXT NOT NULL, definitionFr TEXT NOT NULL, definitionAr TEXT NOT NULL,
                etymology TEXT NOT NULL, clinicalPearl TEXT NOT NULL, mnemonic TEXT NOT NULL,
                module TEXT NOT NULL, chapter TEXT NOT NULL,
                example TEXT NOT NULL,
                exampleEn TEXT NOT NULL, exampleFr TEXT NOT NULL, exampleAr TEXT NOT NULL,
                imageAsset TEXT NOT NULL, audioAsset TEXT NOT NULL, ipaPhonetic TEXT NOT NULL,
                isBookmarked INTEGER NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL("CREATE INDEX index_medical_terms_module ON medical_terms(module)")
        db.execSQL("CREATE INDEX index_medical_terms_module_chapter ON medical_terms(module, chapter)")
        db.execSQL("CREATE INDEX index_medical_terms_termEn_module ON medical_terms(termEn, module)")
        db.execSQL(
            """
            INSERT INTO medical_terms (id, termEn, termFr, termAr, definitionEn, definitionFr,
                definitionAr, etymology, clinicalPearl, mnemonic, module, chapter, example,
                exampleEn, exampleFr, exampleAr, imageAsset, audioAsset, ipaPhonetic, isBookmarked)
            VALUES (1, 'Heart', 'Cœur', 'القلب', 'def', 'def', 'def', 'Greek: kardia', '', '',
                    'Anatomie', 'Angiologie', 'The heart is located in the middle mediastinum.',
                    'The heart is located in the middle mediastinum.', 'Le cœur est au médiastin.',
                    'القلب في المنصف', '', '', '/hɑːrt/', 1),
                   (2, 'Lung', 'Poumon', 'الرئة', 'def', 'def', 'def', '', '', '',
                    'Anatomie', 'Respiration', 'The lungs exchange gases.',
                    'The lungs exchange gases.', 'Les poumons échangent les gaz.',
                    'الرئتان تبادلا الغازات', '', '', '/lʌŋ/', 0)
            """.trimIndent()
        )
    }

    @Before
    fun setUp() {
        context().deleteDatabase(dbFile)
        helper = FrameworkSQLiteOpenHelperFactory().create(
            SupportSQLiteOpenHelper.Configuration.builder(context())
                .name(dbFile)
                .callback(object : SupportSQLiteOpenHelper.Callback(11) {
                    override fun onCreate(db: SupportSQLiteDatabase) = createV11(db)
                    override fun onUpgrade(db: SupportSQLiteDatabase, old: Int, new: Int) = Unit
                })
                .build()
        )
        assertTrue(helper.writableDatabase.isOpen)
    }

    @After
    fun tearDown() {
        runCatching { helper.close() }
        context().deleteDatabase(dbFile)
    }

    private fun migrate() {
        val db = helper.writableDatabase
        db.beginTransaction()
        try {
            MIGRATION_11_12.migrate(db)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    @Test
    fun `v11 to v12 supprime la colonne example et garde les 19 autres`() {
        assertTrue("la base v11 de départ a bien `example`",
            columnNames(helper.writableDatabase, "medical_terms").contains("example"))
        migrate()
        val cols = columnNames(helper.writableDatabase, "medical_terms")
        assertFalse("`example` doit disparaître", cols.contains("example"))
        assertEquals("19 colonnes restantes", 19, cols.size)
        // Les colonnes trilingues sont intactes.
        listOf("exampleEn", "exampleFr", "exampleAr").forEach {
            assertTrue("colonne manquante: $it", cols.contains(it))
        }
    }

    @Test
    fun `les donnees existantes sont conservees y compris les bookmarks`() {
        migrate()
        val db = helper.writableDatabase
        db.query("SELECT COUNT(*) FROM medical_terms").use { c ->
            assertTrue(c.moveToFirst()); assertEquals(2, c.getInt(0))
        }
        db.query("SELECT COUNT(*) FROM medical_terms WHERE isBookmarked = 1").use { c ->
            assertTrue(c.moveToFirst()); assertEquals(1, c.getInt(0))
        }
        // La valeur de exampleEn (la seule qui comptait) est intacte.
        db.query("SELECT exampleEn FROM medical_terms WHERE id = 1").use { c ->
            assertTrue(c.moveToFirst())
            assertEquals("The heart is located in the middle mediastinum.", c.getString(0))
        }
    }

    @Test
    fun `les index v11 sont recrees apres le rebuild de la table`() {
        migrate()
        val indexes = indexNames(helper.writableDatabase, "medical_terms")
        assertEquals(3, indexes.size)
        listOf(
            "index_medical_terms_module",
            "index_medical_terms_module_chapter",
            "index_medical_terms_termEn_module"
        ).forEach { assertTrue("index manquant: $it", indexes.contains(it)) }
    }
}
