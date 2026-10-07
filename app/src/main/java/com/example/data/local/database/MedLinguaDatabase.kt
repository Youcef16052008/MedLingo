package com.example.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.initial.InitialData
import com.example.data.local.dao.MedicalDao
import com.example.data.local.entity.DownloadedModuleEntity
import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.FlashcardProgressEntity
import com.example.data.local.entity.GemsTransactionEntity
import com.example.data.local.entity.LeagueCohortEntity
import com.example.data.local.entity.LeagueMemberEntity
import com.example.data.local.entity.LessonScoreEntity
import com.example.data.local.entity.MedicalTermEntity
import com.example.data.local.entity.TrophyEntity
import com.example.data.local.entity.UserStatsEntity

@Database(
    entities = [
        MedicalTermEntity::class,
        FlashcardProgressEntity::class,
        ExerciseEntity::class,
        DownloadedModuleEntity::class,
        UserStatsEntity::class,
        GemsTransactionEntity::class,
        LeagueCohortEntity::class,
        LeagueMemberEntity::class,
        TrophyEntity::class,
        LessonScoreEntity::class
    ],
    // v9 : les ids des seeds ont été dédupliqués (1..N) après l'intégration des
    // dumps SQL, ce qui a RENUMÉROTÉ les termes. Aucune colonne n'a changé, mais
    // `flashcard_progress.termId` pointe sur `medical_terms.id` : sans MIGRATION_8_9
    // l'historique de révision de chaque utilisateur se retrouverait rattaché au
    // mauvais terme. La migration le ré-attache par (termEn, module) au lieu de
    // supprimer la base.
    //
    // v10 : suppression du système de cœurs (hearts/heartsUpdatedAt/maxHearts) et
    // ajout de l'objectif quotidien, des caisses et des trophées — MIGRATION_9_10.
    version = 10,
    exportSchema = true
)
abstract class MedLinguaDatabase : RoomDatabase() {
    abstract fun medicalDao(): MedicalDao

    companion object {
        @Volatile
        private var INSTANCE: MedLinguaDatabase? = null

        fun getDatabase(context: Context): MedLinguaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedLinguaDatabase::class.java,
                    "medlingua_dz.db"
                ).addMigrations(
                    MIGRATION_6_7,
                    MIGRATION_7_8,
                    MIGRATION_8_9,
                    MIGRATION_9_10
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

/**
 * Ré-attache l'historique de révision aux bons termes après la renumérotation des seeds.
 *
 * Le calcul est délégué à [TermIdMigrationPlan] : une base v8 contient des termes dont
 * l'id ne correspond plus au seed courant, donc `flashcard_progress.termId` pointerait
 * sur le mauvais terme. Aucun signet, aucune progression et aucune statistique n'est
 * sacrifié — c'est tout l'intérêt de cette migration par rapport à un drop de tables.
 */
private val MIGRATION_8_9 = object : Migration(8, 9) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // En tête : certaines colonnes et certains index ont été ajoutés aux entités sans
        // migration lors des builds de développement. Tous les chemins d'upgrade passent
        // ici (v6→7→8→9, v7→8→9, v8→9), donc c'est le seul endroit qui peut réparer le
        // schéma quelle que soit la version de départ. Sans cela Room refuse l'ouverture
        // après la migration et l'app crash au démarrage.
        ensureEntityDrift(database)

        val stored = ArrayList<TermIdMigrationPlan.TermIdentity>()
        // Les colonnes de `medical_terms` portent les noms Kotlin de l'entité (camelCase,
        // sans @ColumnInfo) : c'est aussi ce que le MedicalDao interroge. Un schéma en
        // snake_case n'existe nulle part, et `term_en` faisait échouer la migration — donc
        // le démarrage — chez tout utilisateur à partir de la v8.
        database.query("SELECT id, termEn, module FROM medical_terms").use { c ->
            val idIdx = c.getColumnIndexOrThrow("id")
            val enIdx = c.getColumnIndexOrThrow("termEn")
            val modIdx = c.getColumnIndexOrThrow("module")
            while (c.moveToNext()) {
                stored += TermIdMigrationPlan.TermIdentity(
                    id = c.getInt(idIdx),
                    termEn = c.getString(enIdx),
                    module = c.getString(modIdx)
                )
            }
        }

        // On collecte avant d'écrire : modifier une table pendant qu'un curseur la parcourt
        // laisse l'itération sans garantie.
        val progressIds = LinkedHashSet<Int>()
        database.query("SELECT termId FROM flashcard_progress").use { c ->
            while (c.moveToNext()) progressIds += c.getInt(0)
        }

        val seed = InitialData.terms.map {
            TermIdMigrationPlan.TermIdentity(id = it.id, termEn = it.termEn, module = it.module)
        }
        val plan = TermIdMigrationPlan.compute(stored, seed, progressIds)

        // Un déplacement peut viser un id qu'un autre déplacement va libérer : l'ancien
        // id 3 (Neurone) devient 4 pendant que l'ancien 7 (Cell) devient 3. Appliqué dans
        // l'ordre, un UPDATE OR REPLACE écraserait la progression de Neurone avec celle de
        // Cell, et l'historique de Neurone serait perdu. On passe donc par des ids
        // temporaires négatifs — ils ne peuvent entrer en collision avec un id de seed,
        // qui est toujours positif, et rendent l'opération ordre-indépendante.
        plan.progressRemap.entries.forEachIndexed { i, (oldId, _) ->
            database.execSQL(
                "UPDATE OR REPLACE flashcard_progress SET termId = ? WHERE termId = ?",
                arrayOf<Any>(-(i + 1), oldId)
            )
        }
        plan.progressRemap.values.forEachIndexed { i, newId ->
            database.execSQL(
                "UPDATE OR REPLACE flashcard_progress SET termId = ? WHERE termId = ?",
                arrayOf<Any>(newId, -(i + 1))
            )
        }

        // Les termes retirés du seed ne doivent plus apparaître dans le lexique.
        plan.droppedTermIds.forEach { oldId ->
            database.execSQL("DELETE FROM medical_terms WHERE id = ?", arrayOf<Any>(oldId))
        }

        // Et plus aucune progression ne doit référencer un terme inexistant.
        plan.droppedProgressIds.forEach { termId ->
            database.execSQL("DELETE FROM flashcard_progress WHERE termId = ?", arrayOf<Any>(termId))
        }
    }
}

/**
 * Répare le schéma quand une entité a gagné un champ ou un index sans migration.
 *
 * Les specs d'exercices, l'économie de cœurs/pierres et les index de ligues ont été ajoutés
 * aux entités au fil des builds de développement sans migration dédiée : seule une base
 * créée à neuf avec le schéma courant passait la validation de Room. Chaque entrée est
 * idempotente — appliquée seulement si elle manque — pour couvrir un départ en v6, v7 ou v8
 * sans avoir à reconstituer l'ordre exact des changements.
 *
 * Les noms d'index reprennent ceux que Room génère (index_<table>_<colonnes>) : la
 * validation compare les index, pas seulement les colonnes.
 */
private fun ensureEntityDrift(db: SupportSQLiteDatabase) {
    val missingColumns = mapOf(
        "exercises" to listOf(
            "specJson TEXT NOT NULL DEFAULT ''",
            "isSpecMigrated INTEGER NOT NULL DEFAULT 0",
            "version INTEGER NOT NULL DEFAULT 1"
        ),
        "user_stats" to listOf(
            "gems INTEGER NOT NULL DEFAULT 100"
        )
    )

    for ((table, definitions) in missingColumns) {
        val existing = HashSet<String>()
        db.query("PRAGMA table_info($table)").use { c ->
            val nameIdx = c.getColumnIndexOrThrow("name")
            while (c.moveToNext()) existing += c.getString(nameIdx)
        }
        // Une table absente ne peut pas être altérée ; Room la signalerait de toute façon.
        if (existing.isEmpty()) continue
        for (definition in definitions) {
            val name = definition.substringBefore(' ')
            if (name !in existing) db.execSQL("ALTER TABLE $table ADD COLUMN $definition")
        }
    }

    listOf(
        "user_stats" to "CREATE INDEX IF NOT EXISTS index_user_stats_weeklyXp ON user_stats(weeklyXp)",
        "league_cohorts" to "CREATE INDEX IF NOT EXISTS index_league_cohorts_tier ON league_cohorts(tier)",
        "league_cohorts" to "CREATE INDEX IF NOT EXISTS index_league_cohorts_weekStartTimestamp ON league_cohorts(weekStartTimestamp)",
        "league_cohorts" to "CREATE INDEX IF NOT EXISTS index_league_cohorts_isActive ON league_cohorts(isActive)"
    ).forEach { (table, statement) ->
        // Une table manquante ferait échouer toute la migration ; Room la signale ensuite
        // par sa propre validation, avec un message exploitable.
        db.query(
            "SELECT 1 FROM sqlite_master WHERE type = 'table' AND name = ?",
            arrayOf(table)
        ).use { if (it.moveToFirst()) db.execSQL(statement) }
    }
}

private val MIGRATION_6_7 = object : Migration(6, 7) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add new columns for league system
        database.execSQL("ALTER TABLE user_stats ADD COLUMN weeklyXp INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE user_stats ADD COLUMN leagueCohortId TEXT")
        database.execSQL("ALTER TABLE user_stats ADD COLUMN leagueTier TEXT NOT NULL DEFAULT 'BRONZE'")
        database.execSQL("ALTER TABLE user_stats ADD COLUMN isSuper INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE user_stats ADD COLUMN superExpiresAt INTEGER")
        database.execSQL("ALTER TABLE user_stats ADD COLUMN streakFreezeCount INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE user_stats ADD COLUMN perfectLessonsCount INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE user_stats ADD COLUMN lessonsCompleted INTEGER NOT NULL DEFAULT 0")
        
        // Create league_cohorts table
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS league_cohorts (
                cohortId TEXT NOT NULL PRIMARY KEY,
                weekStartTimestamp INTEGER NOT NULL,
                weekEndTimestamp INTEGER NOT NULL,
                tier TEXT NOT NULL DEFAULT 'BRONZE',
                isActive INTEGER NOT NULL DEFAULT 1,
                isPromoted INTEGER NOT NULL DEFAULT 0,
                isDemoted INTEGER NOT NULL DEFAULT 0,
                createdAt INTEGER NOT NULL DEFAULT 0
            )
        """)
        
        // Create league_members table
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS league_members (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                cohortId TEXT NOT NULL,
                userId INTEGER NOT NULL DEFAULT 1,
                displayName TEXT NOT NULL,
                avatarEmoji TEXT NOT NULL DEFAULT '👨‍⚕️',
                weeklyXp INTEGER NOT NULL DEFAULT 0,
                totalXp INTEGER NOT NULL DEFAULT 0,
                streakDays INTEGER NOT NULL DEFAULT 0,
                rank INTEGER NOT NULL DEFAULT 0,
                isCurrentUser INTEGER NOT NULL DEFAULT 0,
                isBot INTEGER NOT NULL DEFAULT 0,
                lastActiveTimestamp INTEGER NOT NULL DEFAULT 0
            )
        """)
        
        // Create gems_transactions table
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS gems_transactions (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                userId INTEGER NOT NULL DEFAULT 1,
                type TEXT NOT NULL,
                amount INTEGER NOT NULL,
                reason TEXT NOT NULL,
                timestamp INTEGER NOT NULL DEFAULT 0,
                balanceAfter INTEGER NOT NULL DEFAULT 0,
                metadata TEXT NOT NULL DEFAULT ''
            )
        """)
        
        // Create indexes
        database.execSQL("CREATE INDEX IF NOT EXISTS index_league_members_cohortId ON league_members(cohortId)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_league_members_cohortId_weeklyXp ON league_members(cohortId, weeklyXp)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_gems_transactions_userId ON gems_transactions(userId)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_gems_transactions_timestamp ON gems_transactions(timestamp)")
    }
}

private val MIGRATION_7_8 = object : Migration(7, 8) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add any additional schema changes here
        database.execSQL("CREATE INDEX IF NOT EXISTS index_user_stats_leagueCohortId ON user_stats(leagueCohortId)")
    }
}

/**
 * v9 → v10 : fin des cœurs, début de l'objectif quotidien / des caisses / des trophées.
 *
 * `user_stats` est reconstruit (CREATE … INSERT SELECT … DROP … RENAME) : Room refuse
 * d'altérer une table pour *retirer* une colonne, et les cœurs (`hearts`, `heartsUpdatedAt`,
 * `maxHearts`) disparaissent du modèle. Toutes les données existantes sont conservées —
 * seules les colonnes de cœurs sont abandonnées.
 *
 * Colonnes ajoutées : objectif quotidien (`xpToday`, `goalDate`, `dailyGoal`),
 * série (`lastStudy`, `goalDays`), caisses (`pendingChests`, `chestsOpened`),
 * contre `flashReviewed` (trophée 🗂️) et `weeklyXpReset` (reset hebdomadaire).
 * Les congélations réutilisent `streakFreezeCount`, déjà présent en v9.
 */
internal val MIGRATION_9_10 = object : Migration(9, 10) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Colonnes / index rajoutés aux entités au fil des builds de dev : la source du
        // INSERT SELECT doit être complète quelle que soit la version de départ (v6 à v9).
        ensureEntityDrift(database)

        database.execSQL(
            """
            CREATE TABLE user_stats_new (
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
                weeklyXp INTEGER NOT NULL,
                leagueCohortId TEXT,
                leagueTier TEXT NOT NULL,
                isSuper INTEGER NOT NULL,
                superExpiresAt INTEGER,
                streakFreezeCount INTEGER NOT NULL,
                perfectLessonsCount INTEGER NOT NULL,
                lessonsCompleted INTEGER NOT NULL,
                xpToday INTEGER NOT NULL,
                goalDate TEXT NOT NULL,
                dailyGoal INTEGER NOT NULL,
                lastStudy TEXT NOT NULL,
                goalDays INTEGER NOT NULL,
                flashReviewed INTEGER NOT NULL,
                pendingChests INTEGER NOT NULL,
                chestsOpened INTEGER NOT NULL,
                weeklyXpReset INTEGER NOT NULL
            )
            """.trimIndent()
        )

        database.execSQL(
            """
            INSERT INTO user_stats_new (
                id, streakDays, learnedTermsCount, totalPoints, quizzesCompleted,
                totalQuestionsAnswered, correctAnswersCount, isOfflineSimulated,
                selectedLanguageCode, level1Score, level2Score, level3Score,
                level4Score, level5Score, level6Score, gems, weeklyXp,
                leagueCohortId, leagueTier, isSuper, superExpiresAt,
                streakFreezeCount, perfectLessonsCount, lessonsCompleted,
                xpToday, goalDate, dailyGoal, lastStudy, goalDays, flashReviewed,
                pendingChests, chestsOpened, weeklyXpReset
            )
            SELECT
                id, streakDays, learnedTermsCount, totalPoints, quizzesCompleted,
                totalQuestionsAnswered, correctAnswersCount, isOfflineSimulated,
                selectedLanguageCode, level1Score, level2Score, level3Score,
                level4Score, level5Score, level6Score, gems, weeklyXp,
                leagueCohortId, leagueTier, isSuper, superExpiresAt,
                streakFreezeCount, perfectLessonsCount, lessonsCompleted,
                0, '', 50, '', 0, 0, 0, 0, 0
            FROM user_stats
            """.trimIndent()
        )

        database.execSQL("DROP TABLE user_stats")
        database.execSQL("ALTER TABLE user_stats_new RENAME TO user_stats")
        // DROP TABLE emporte ses index : Room les attend sur la table renommée.
        database.execSQL("CREATE INDEX IF NOT EXISTS index_user_stats_weeklyXp ON user_stats(weeklyXp)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_user_stats_leagueCohortId ON user_stats(leagueCohortId)")

        database.execSQL(
            "CREATE TABLE IF NOT EXISTS trophies (id TEXT NOT NULL PRIMARY KEY, earnedAt INTEGER NOT NULL)"
        )
        // Meilleurs scores du parcours (module:niveau) : requis par le déblocage 70 %,
        // table neuve — pas de donnée historique à migrer.
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS lesson_scores (
                lessonKey TEXT NOT NULL PRIMARY KEY,
                moduleId TEXT NOT NULL,
                level INTEGER NOT NULL,
                best INTEGER NOT NULL
            )
            """.trimIndent()
        )
    }
}
