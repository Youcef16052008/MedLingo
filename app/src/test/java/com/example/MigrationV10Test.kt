package com.example

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.database.MIGRATION_9_10
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
 * Migration v9 → v10 : suppression des cœurs + nouvelles colonnes
 * (objectif quotidien, série, caisses, reset hebdo) et création des tables
 * `trophies` / `lesson_scores`.
 *
 * Le schéma v9 (avec `hearts`, `heartsUpdatedAt`, `maxHearts`) est reconstruit
 * ici : Room ne peut pas retirer une colonne, la table est recréée.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class MigrationV10Test {

    private val dbFile = "migration-v10-test.db"
    private lateinit var helper: SupportSQLiteOpenHelper

    /** Colonnes attendues de `user_stats` en v10 (33). */
    private val expectedV10Columns = setOf(
        "id", "streakDays", "learnedTermsCount", "totalPoints", "quizzesCompleted",
        "totalQuestionsAnswered", "correctAnswersCount", "isOfflineSimulated",
        "selectedLanguageCode", "level1Score", "level2Score", "level3Score",
        "level4Score", "level5Score", "level6Score", "gems", "weeklyXp",
        "leagueCohortId", "leagueTier", "isSuper", "superExpiresAt",
        "streakFreezeCount", "perfectLessonsCount", "lessonsCompleted",
        "xpToday", "goalDate", "dailyGoal", "lastStudy", "goalDays",
        "flashReviewed", "pendingChests", "chestsOpened", "weeklyXpReset"
    )

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

    private fun tableExists(db: SupportSQLiteDatabase, name: String): Boolean {
        db.query(
            "SELECT 1 FROM sqlite_master WHERE type = 'table' AND name = ?",
            arrayOf(name)
        ).use { return it.moveToFirst() }
    }

    /** Schéma v9 : `user_stats` avec les colonnes de cœurs, index compris. */
    private fun createV9(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE user_stats (
                id INTEGER NOT NULL PRIMARY KEY,
                streakDays INTEGER NOT NULL,
                learnedTermsCount INTEGER NOT NULL,
                totalPoints INTEGER NOT NULL,
                quizzesCompleted INTEGER NOT NULL,
                totalQuestionsAnswered INTEGER NOT NULL,
                correctAnswersCount INTEGER NOT NULL,
                isOfflineSimulated INTEGER NOT NULL,
                selectedLanguageCode TEXT NOT NULL,
                level1Score INTEGER NOT NULL,
                level2Score INTEGER NOT NULL,
                level3Score INTEGER NOT NULL,
                level4Score INTEGER NOT NULL,
                level5Score INTEGER NOT NULL,
                level6Score INTEGER NOT NULL,
                gems INTEGER NOT NULL,
                hearts INTEGER NOT NULL,
                maxHearts INTEGER NOT NULL,
                heartsUpdatedAt INTEGER NOT NULL,
                weeklyXp INTEGER NOT NULL,
                leagueCohortId TEXT,
                leagueTier TEXT NOT NULL,
                isSuper INTEGER NOT NULL,
                superExpiresAt INTEGER,
                streakFreezeCount INTEGER NOT NULL,
                perfectLessonsCount INTEGER NOT NULL,
                lessonsCompleted INTEGER NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL("CREATE INDEX index_user_stats_weeklyXp ON user_stats(weeklyXp)")
        db.execSQL("CREATE INDEX index_user_stats_leagueCohortId ON user_stats(leagueCohortId)")
        db.execSQL(
            """
            INSERT INTO user_stats (
                id, streakDays, learnedTermsCount, totalPoints, quizzesCompleted,
                totalQuestionsAnswered, correctAnswersCount, isOfflineSimulated,
                selectedLanguageCode, level1Score, level2Score, level3Score,
                level4Score, level5Score, level6Score, gems, hearts, maxHearts,
                heartsUpdatedAt, weeklyXp, leagueCohortId, leagueTier, isSuper,
                superExpiresAt, streakFreezeCount, perfectLessonsCount, lessonsCompleted
            ) VALUES (
                1, 12, 156, 2450, 18,
                92, 78, 0,
                'fr', 85, 75, 0,
                0, 0, 0, 100, 3, 5,
                1760000000000, 300, 'cohort-1', 'ARGENT', 0,
                NULL, 1, 4, 18
            )
            """.trimIndent()
        )
    }

    @Before
    fun setUp() {
        context().deleteDatabase(dbFile)
        helper = FrameworkSQLiteOpenHelperFactory().create(
            SupportSQLiteOpenHelper.Configuration.builder(context())
                .name(dbFile)
                .callback(object : SupportSQLiteOpenHelper.Callback(9) {
                    override fun onCreate(db: SupportSQLiteDatabase) = createV9(db)
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
    fun `v9 to v10 supprime les coeurs et ajoute les colonnes du lot 2`() {
        val db = helper.writableDatabase
        assertTrue(columnNames(db, "user_stats").contains("hearts"))

        db.beginTransaction()
        try {
            MIGRATION_9_10.migrate(db)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        val columns = columnNames(db, "user_stats")
        assertEquals(expectedV10Columns, columns)
        assertFalse(columns.contains("hearts"))
        assertFalse(columns.contains("heartsUpdatedAt"))
        assertFalse(columns.contains("maxHearts"))
        // Colonnes ajoutées par la migration
        listOf(
            "xpToday", "goalDate", "dailyGoal", "lastStudy", "goalDays",
            "flashReviewed", "pendingChests", "chestsOpened", "weeklyXpReset"
        ).forEach { assertTrue("colonne manquante: $it", columns.contains(it)) }
    }

    @Test
    fun `les donnees existantes sont conservees avec des valeurs par defaut neuves`() {
        val db = helper.writableDatabase
        db.beginTransaction()
        try {
            MIGRATION_9_10.migrate(db)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        db.query("SELECT * FROM user_stats WHERE id = 1").use { c ->
            assertTrue(c.moveToFirst())
            fun get(name: String) = c.getInt(c.getColumnIndexOrThrow(name))
            fun getStr(name: String) = c.getString(c.getColumnIndexOrThrow(name))

            // Données v9 préservées
            assertEquals(12, get("streakDays"))
            assertEquals(2450, get("totalPoints"))
            assertEquals(100, get("gems"))
            assertEquals(300, get("weeklyXp"))
            assertEquals(1, get("streakFreezeCount"))
            assertEquals(4, get("perfectLessonsCount"))
            assertEquals("ARGENT", getStr("leagueTier"))
            assertEquals("cohort-1", getStr("leagueCohortId"))

            // Valeurs par défaut des colonnes neuves
            assertEquals(0, get("xpToday"))
            assertEquals("", getStr("goalDate"))
            assertEquals(50, get("dailyGoal"))
            assertEquals("", getStr("lastStudy"))
            assertEquals(0, get("goalDays"))
            assertEquals(0, get("flashReviewed"))
            assertEquals(0, get("pendingChests"))
            assertEquals(0, get("chestsOpened"))
            assertEquals(0, get("weeklyXpReset"))
        }
    }

    @Test
    fun `les index de ligues sont recrees apres la reconstruction de la table`() {
        val db = helper.writableDatabase
        db.beginTransaction()
        try {
            MIGRATION_9_10.migrate(db)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        val indexes = indexNames(db, "user_stats")
        assertTrue(indexes.contains("index_user_stats_weeklyXp"))
        assertTrue(indexes.contains("index_user_stats_leagueCohortId"))
    }

    @Test
    fun `les tables trophies et lesson_scores sont creees`() {
        val db = helper.writableDatabase
        db.beginTransaction()
        try {
            MIGRATION_9_10.migrate(db)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        assertTrue(tableExists(db, "trophies"))
        assertEquals(setOf("id", "earnedAt"), columnNames(db, "trophies"))
        assertTrue(
            indexNames(db, "trophies").any { it.startsWith("sqlite_autoindex_trophies") }
        )
        assertTrue(tableExists(db, "lesson_scores"))
        assertEquals(
            setOf("lessonKey", "moduleId", "level", "best"),
            columnNames(db, "lesson_scores")
        )
    }
}
