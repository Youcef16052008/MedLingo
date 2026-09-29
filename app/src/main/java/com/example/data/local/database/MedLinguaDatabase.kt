package com.example.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.MedicalDao
import com.example.data.local.entity.DownloadedModuleEntity
import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.FlashcardProgressEntity
import com.example.data.local.entity.MedicalTermEntity
import com.example.data.local.entity.UserStatsEntity

@Database(
    entities = [
        MedicalTermEntity::class,
        FlashcardProgressEntity::class,
        ExerciseEntity::class,
        DownloadedModuleEntity::class,
        UserStatsEntity::class
    ],
    version = 6,
    exportSchema = false
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
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
