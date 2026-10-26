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
 * Régression v12 → v14 (mode histoire, phase 2 Android).
 *
 * Vérifie les trois propriétés du guide d'intégration §8 :
 *  1. la table `review_queue` est CRÉÉE en v13 (l'`ALTER` d'origine plantait :
 *     la table n'existait pas en v12 committée) ;
 *  2. les tables `story_session_progress` et `phrase_progress` arrivent en v14 ;
 *  3. AUCUNE donnée existante n'est perdue (termes, flashcards, stats) —
 *     jamais de repli destructif.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class MigrationV13_14Test {

    private val dbFile = "migration-v13-14-test.db"
    private lateinit var helper: SupportSQLiteOpenHelper

    private fun context(): Context = ApplicationProvider.getApplicationContext()

    private fun tableExists(db: SupportSQLiteDatabase, table: String): Boolean =
        db.query(
            "SELECT 1 FROM sqlite_master WHERE type = 'table' AND name = ?",
            arrayOf(table)
        ).use { it.moveToFirst() }

    private fun count(db: SupportSQLiteDatabase, table: String): Int {
        db.query("SELECT COUNT(*) FROM $table").use { c ->
            c.moveToFirst()
            return c.getInt(0)
        }
    }

    /** Schéma v12 minimal avec des données qui DOIVENT survivre. */
    private fun createV12(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE medical_terms (
                id INTEGER NOT NULL PRIMARY KEY,
                termEn TEXT NOT NULL, termFr TEXT NOT NULL, termAr TEXT NOT NULL,
                definitionEn TEXT NOT NULL, definitionFr TEXT NOT NULL, definitionAr TEXT NOT NULL,
                etymology TEXT NOT NULL, clinicalPearl TEXT NOT NULL, mnemonic TEXT NOT NULL,
                module TEXT NOT NULL, chapter TEXT NOT NULL,
                exampleEn TEXT NOT NULL, exampleFr TEXT NOT NULL, exampleAr TEXT NOT NULL,
                imageAsset TEXT NOT NULL, audioAsset TEXT NOT NULL,
                ipaPhonetic TEXT NOT NULL, isBookmarked INTEGER NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL(
            "INSERT INTO medical_terms VALUES (1, 'Heart', 'Cœur', 'قلب', '', '', '', '', '', '', 'Anatomie', '', '', '', '', '', '', '', 1)"
        )
        db.execSQL(
            """
            CREATE TABLE flashcard_progress (
                termId INTEGER NOT NULL PRIMARY KEY,
                repetitions INTEGER NOT NULL,
                easeFactor REAL NOT NULL,
                intervalDays INTEGER NOT NULL,
                nextReviewTimestamp INTEGER NOT NULL,
                lastReviewedTimestamp INTEGER NOT NULL,
                lastQuality INTEGER NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL(
            "INSERT INTO flashcard_progress VALUES (1, 3, 2.5, 7, 9999999999999, 1000, 4)"
        )
        db.execSQL(
            """
            CREATE TABLE user_stats (
                id INTEGER NOT NULL PRIMARY KEY,
                streakDays INTEGER NOT NULL, learnedTermsCount INTEGER NOT NULL,
                totalPoints INTEGER NOT NULL, quizzesCompleted INTEGER NOT NULL,
                totalQuestionsAnswered INTEGER NOT NULL, correctAnswersCount INTEGER NOT NULL,
                isOfflineSimulated INTEGER NOT NULL, selectedLanguageCode TEXT NOT NULL,
                level1Score INTEGER NOT NULL, level2Score INTEGER NOT NULL, level3Score INTEGER NOT NULL,
                level4Score INTEGER NOT NULL, level5Score INTEGER NOT NULL, level6Score INTEGER NOT NULL,
                gems INTEGER NOT NULL, weeklyXp INTEGER NOT NULL, leagueCohortId TEXT,
                leagueTier TEXT NOT NULL, isSuper INTEGER NOT NULL, superExpiresAt INTEGER,
                streakFreezeCount INTEGER NOT NULL, perfectLessonsCount INTEGER NOT NULL,
                lessonsCompleted INTEGER NOT NULL, xpToday INTEGER NOT NULL, goalDate TEXT NOT NULL,
                dailyGoal INTEGER NOT NULL, lastStudy TEXT NOT NULL, goalDays INTEGER NOT NULL,
                flashReviewed INTEGER NOT NULL, pendingChests INTEGER NOT NULL,
                chestsOpened INTEGER NOT NULL, weeklyXpReset INTEGER NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL(
            """INSERT INTO user_stats VALUES (1, 5, 10, 1200, 3, 40, 35, 0, 'fr',
               80, 0, 0, 0, 0, 0, 100, 50, NULL, 'BRONZE', 0, NULL, 1, 2, 3,
               10, '2026-10-24', 50, '2026-10-24', 1, 4, 0, 1, 0)"""
        )
    }

    @Before
    fun setUp() {
        context().deleteDatabase(dbFile)
        val config = SupportSQLiteOpenHelper.Configuration.builder(context())
            .name(dbFile)
            .callback(object : SupportSQLiteOpenHelper.Callback(12) {
                override fun onCreate(db: SupportSQLiteDatabase) = createV12(db)
                override fun onUpgrade(db: SupportSQLiteDatabase, o: Int, n: Int) = Unit
            })
            .build()
        helper = FrameworkSQLiteOpenHelperFactory().create(config)
        // Crée la base v12 (un SELECT passe par query, pas execSQL, sous Robolectric).
        helper.writableDatabase.query("SELECT 1").use { it.moveToFirst() }
    }

    @After
    fun tearDown() {
        helper.close()
        context().deleteDatabase(dbFile)
    }

    private fun migrateTo(target: Int): SupportSQLiteDatabase {
        val config = SupportSQLiteOpenHelper.Configuration.builder(context())
            .name(dbFile)
            .callback(object : SupportSQLiteOpenHelper.Callback(target) {
                override fun onCreate(db: SupportSQLiteDatabase) = Unit
                override fun onUpgrade(db: SupportSQLiteDatabase, old: Int, new: Int) {
                    if (old < 13 && new >= 13) MIGRATION_12_13.migrate(db)
                    if (old < 14 && new >= 14) MIGRATION_13_14.migrate(db)
                }
            })
            .build()
        return FrameworkSQLiteOpenHelperFactory().create(config).writableDatabase
    }

    @Test
    fun `v13 cree review_queue a la forme exacte de l entite`() {
        val db = migrateTo(13)
        assertTrue(tableExists(db, "review_queue"))
        val cols = mutableSetOf<String>()
        db.query("PRAGMA table_info(review_queue)").use { c ->
            val idx = c.getColumnIndexOrThrow("name")
            while (c.moveToNext()) cols += c.getString(idx)
        }
        assertEquals(setOf("id", "lessonId", "chapterId", "title", "questionCount", "status", "missedExIds"), cols)
        db.close()
    }

    @Test
    fun `v14 ajoute les tables du mode histoire sans toucher aux donnees`() {
        val db = migrateTo(14)
        assertTrue(tableExists(db, "story_session_progress"))
        assertTrue(tableExists(db, "phrase_progress"))
        // Données v12 intégralement conservées.
        assertEquals(1, count(db, "medical_terms"))
        assertEquals(1, count(db, "flashcard_progress"))
        assertEquals(1, count(db, "user_stats"))
        db.query("SELECT totalPoints, termEn, isBookmarked FROM medical_terms JOIN user_stats ON user_stats.id = 1").use { c ->
            c.moveToFirst()
            assertEquals(1200, c.getInt(0))
            assertEquals("Heart", c.getString(1))
            assertEquals(1, c.getInt(2))
        }
        // Les tables story sont vides (création pure).
        assertEquals(0, count(db, "story_session_progress"))
        assertEquals(0, count(db, "phrase_progress"))
        db.close()
    }

    @Test
    fun `les nouvelles tables sont utilisables immediatement`() {
        val db = migrateTo(14)
        db.execSQL(
            "INSERT INTO phrase_progress VALUES ('en:have-headache:v1', 1, 2.5, 1, 1700000000000, 1700000000000)"
        )
        db.query("SELECT phraseId, intervalDays FROM phrase_progress").use { c ->
            assertTrue(c.moveToFirst())
            assertEquals("en:have-headache:v1", c.getString(0))
            assertEquals(1, c.getInt(1))
        }
        assertFalse(
            // Aucune clé d'expression ne doit pouvoir entrer dans flashcard_progress :
            // ce test ne fait que rappeler la séparation des espaces (guide §9.3).
            db.query("SELECT termId FROM flashcard_progress WHERE termId < 0").use { it.moveToFirst() }
        )
        db.close()
    }
}
