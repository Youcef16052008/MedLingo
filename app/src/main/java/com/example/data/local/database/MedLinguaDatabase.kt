package com.example.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.MedicalDao
import com.example.data.local.entity.DownloadedModuleEntity
import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.FlashcardProgressEntity
import com.example.data.local.entity.GemsTransactionEntity
import com.example.data.local.entity.LeagueCohortEntity
import com.example.data.local.entity.LeagueMemberEntity
import com.example.data.local.entity.MedicalTermEntity
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
        LeagueMemberEntity::class
    ],
    version = 8,
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
                ).addMigrations(MIGRATION_6_7, MIGRATION_7_8).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

private val MIGRATION_6_7 = object : androidx.room.migration.Migration(6, 7) {
    override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
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

private val MIGRATION_7_8 = object : androidx.room.migration.Migration(7, 8) {
    override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
        // Add any additional schema changes here
        database.execSQL("CREATE INDEX IF NOT EXISTS index_user_stats_leagueCohortId ON user_stats(leagueCohortId)")
    }
}
