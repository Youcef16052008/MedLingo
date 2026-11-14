package com.aistudio.medlingua.dzmed.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aistudio.medlingua.dzmed.data.local.dao.KnowledgeGraphDao
import com.aistudio.medlingua.dzmed.data.local.dao.MedicalDao
import com.aistudio.medlingua.dzmed.data.local.dao.ReviewQueueDao
import com.aistudio.medlingua.dzmed.data.local.dao.StoryDao
import com.aistudio.medlingua.dzmed.data.local.entity.DownloadedModuleEntity
import com.aistudio.medlingua.dzmed.data.local.entity.ExerciseEntity
import com.aistudio.medlingua.dzmed.data.local.entity.FlashcardProgressEntity
import com.aistudio.medlingua.dzmed.data.local.entity.GemsTransactionEntity
import com.aistudio.medlingua.dzmed.data.local.entity.LeagueCohortEntity
import com.aistudio.medlingua.dzmed.data.local.entity.LeagueMemberEntity
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphEdgeEntity
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphNodeEntity
import com.aistudio.medlingua.dzmed.data.local.entity.KnowledgeGraphReportEntity
import com.aistudio.medlingua.dzmed.data.local.entity.LessonScoreEntity
import com.aistudio.medlingua.dzmed.data.local.entity.MedicalTermEntity
import com.aistudio.medlingua.dzmed.data.local.entity.PhraseProgressEntity
import com.aistudio.medlingua.dzmed.data.local.entity.ReviewQueueItem
import com.aistudio.medlingua.dzmed.data.local.entity.StorySessionProgressEntity
import com.aistudio.medlingua.dzmed.data.local.entity.TrophyEntity
import com.aistudio.medlingua.dzmed.data.local.entity.UserStatsEntity

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
        LessonScoreEntity::class,
        ReviewQueueItem::class,
        StorySessionProgressEntity::class,
        PhraseProgressEntity::class,
        KnowledgeGraphNodeEntity::class,
        KnowledgeGraphEdgeEntity::class,
        KnowledgeGraphReportEntity::class
    ],
    version = 15,
    exportSchema = true
)
abstract class MedLinguaDatabase : RoomDatabase() {
    abstract fun medicalDao(): MedicalDao
    abstract fun reviewQueueDao(): ReviewQueueDao
    abstract fun storyDao(): StoryDao
    abstract fun knowledgeGraphDao(): KnowledgeGraphDao

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
                    MIGRATION_9_10,
                    MIGRATION_10_11,
                    MIGRATION_11_12,
                    MIGRATION_12_13,
                    MIGRATION_13_14,
                    MIGRATION_14_15
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

private val MIGRATION_14_15 = object : Migration(14, 15) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Ajoute les tables du graphe de connaissances.
        // Table neuve : aucune donnée existante n'est touchée.
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS knowledge_graph_nodes (
                nodeId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                termEn TEXT NOT NULL,
                termFr TEXT NOT NULL,
                termAr TEXT NOT NULL,
                definitionEn TEXT NOT NULL,
                definitionFr TEXT NOT NULL,
                definitionAr TEXT NOT NULL,
                etymology TEXT NOT NULL DEFAULT '',
                clinicalPearl TEXT NOT NULL DEFAULT '',
                mnemonic TEXT NOT NULL DEFAULT '',
                module TEXT NOT NULL DEFAULT '',
                chapter TEXT NOT NULL DEFAULT '',
                exEn TEXT NOT NULL DEFAULT '',
                exFr TEXT NOT NULL DEFAULT '',
                exAr TEXT NOT NULL DEFAULT '',
                ipa TEXT NOT NULL DEFAULT '',
                isBookmarked INTEGER NOT NULL DEFAULT 0
            )
            """.trimIndent()
        )
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS knowledge_graph_edges (
                edgeId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                fromNodeId INTEGER NOT NULL,
                toNodeId INTEGER NOT NULL,
                relationType TEXT NOT NULL,
                confidence TEXT NOT NULL DEFAULT 'high',
                evidence TEXT NOT NULL DEFAULT '',
                FOREIGN KEY (fromNodeId) REFERENCES knowledge_graph_nodes(nodeId)
            )
            """.trimIndent()
        )
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS knowledge_graph_report (
                reportKey TEXT NOT NULL PRIMARY KEY,
                totalNodes INTEGER NOT NULL DEFAULT 0,
                totalEdges INTEGER NOT NULL DEFAULT 0,
                semanticEdges INTEGER NOT NULL DEFAULT 0,
                structuralEdges INTEGER NOT NULL DEFAULT 0,
                relationBreakdown TEXT NOT NULL DEFAULT ''
            )
            """.trimIndent()
        )
        database.execSQL("CREATE INDEX IF NOT EXISTS index_kg_nodes_module ON knowledge_graph_nodes(module)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_kg_nodes_chapter ON knowledge_graph_nodes(module, chapter)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_kg_edges_fromNodeId ON knowledge_graph_edges(fromNodeId)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_kg_edges_toNodeId ON knowledge_graph_edges(toNodeId)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_kg_edges_relationType ON knowledge_graph_edges(relationType)")
    }
}
