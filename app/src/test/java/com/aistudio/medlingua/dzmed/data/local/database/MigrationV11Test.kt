package com.aistudio.medlingua.dzmed.data.local.database

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Régression P0-2 (revue database v2, défaut D2).
 *
 * `medical_terms` n'avait aucun index, alors que `getTermsByModule` et
 * `restoreBookmark` la filtrent en permanence : full scan sur 4 504 lignes à
 * chaque écran de module. La migration v11 ajoute 3 index.
 *
 * On construit un schéma v10 minimal (table + données), on applique
 * `MIGRATION_10_11`, et on vérifie que les 3 index attendus existent et que les
 * données ont survécu.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class MigrationV11Test {

    private val dbFile = "migration-v11-test.db"
    private lateinit var helper: SupportSQLiteOpenHelper

    private fun context(): Context = ApplicationProvider.getApplicationContext()

    private fun indexNames(db: SupportSQLiteDatabase, table: String): Set<String> {
        val names = mutableSetOf<String>()
        db.query("PRAGMA index_list($table)").use { c ->
            val idx = c.getColumnIndexOrThrow("name")
            while (c.moveToNext()) names += c.getString(idx)
        }
        return names
    }

    /** Schéma v10 minimal : table `medical_terms` sans index, avec 2 lignes. */
    private fun createV10(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE medical_terms (
                id INTEGER NOT NULL PRIMARY KEY,
                termEn TEXT NOT NULL, termFr TEXT NOT NULL, termAr TEXT NOT NULL,
                definitionEn TEXT NOT NULL, definitionFr TEXT NOT NULL, definitionAr TEXT NOT NULL,
                etymology TEXT NOT NULL, clinicalPearl TEXT NOT NULL, mnemonic TEXT NOT NULL,
                module TEXT NOT NULL, chapter TEXT NOT NULL,
                example TEXT NOT NULL, exampleEn TEXT NOT NULL,
                exampleFr TEXT NOT NULL, exampleAr TEXT NOT NULL,
                imageAsset TEXT NOT NULL, audioAsset TEXT NOT NULL, ipaPhonetic TEXT NOT NULL,
                isBookmarked INTEGER NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL(
            """
            INSERT INTO medical_terms (id, termEn, termFr, termAr, definitionEn, definitionFr,
                definitionAr, etymology, clinicalPearl, mnemonic, module, chapter,
                example, exampleEn, exampleFr, exampleAr, imageAsset, audioAsset,
                ipaPhonetic, isBookmarked)
            VALUES (1, 'Heart', 'Cœur', 'القلب', 'd', 'd', 'd', '', '', '',
                    'Anatomie', 'Angiologie', '', '', '', '', '', '', '', 0),
                   (2, 'Lung', 'Poumon', 'الرئة', 'd', 'd', 'd', '', '', '',
                    'Anatomie', 'Respiration', '', '', '', '', '', '', '', 1)
            """.trimIndent()
        )
    }

    @Before
    fun setUp() {
        context().deleteDatabase(dbFile)
        helper = FrameworkSQLiteOpenHelperFactory().create(
            SupportSQLiteOpenHelper.Configuration.builder(context())
                .name(dbFile)
                .callback(object : SupportSQLiteOpenHelper.Callback(10) {
                    override fun onCreate(db: SupportSQLiteDatabase) = createV10(db)
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

    @Test
    fun `v10 to v11 cree les trois index sur medical_terms`() {
        val db = helper.writableDatabase
        // Garde : pas d'index en v10.
        assertTrue("la base v10 de départ n'a pas d'index", indexNames(db, "medical_terms").isEmpty())

        db.beginTransaction()
        try {
            MIGRATION_10_11.migrate(db)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        val indexes = indexNames(db, "medical_terms")
        assertTrue("index module manquant: $indexes",
            indexes.contains("index_medical_terms_module"))
        assertTrue("index module_chapter manquant: $indexes",
            indexes.contains("index_medical_terms_module_chapter"))
        assertTrue("index termEn_module manquant: $indexes",
            indexes.contains("index_medical_terms_termEn_module"))
    }

    @Test
    fun `les donnees existantes sont conservees`() {
        val db = helper.writableDatabase
        db.beginTransaction()
        try {
            MIGRATION_10_11.migrate(db)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        db.query("SELECT COUNT(*) FROM medical_terms").use { c ->
            assertTrue(c.moveToFirst())
            org.junit.Assert.assertEquals(2, c.getInt(0))
        }
        // Le bookmark (isBookmarked = 1) a survécu à la migration.
        db.query("SELECT COUNT(*) FROM medical_terms WHERE isBookmarked = 1").use { c ->
            assertTrue(c.moveToFirst())
            org.junit.Assert.assertEquals(1, c.getInt(0))
        }
    }

    @Test
    fun `la migration est idempotente`() {
        val db = helper.writableDatabase
        // Appliquée deux fois de suite, elle ne doit pas échouer (IF NOT EXISTS).
        repeat(2) {
            db.beginTransaction()
            try {
                MIGRATION_10_11.migrate(db)
                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        }
        val indexes = indexNames(db, "medical_terms")
        org.junit.Assert.assertEquals(3, indexes.size)
    }
}
