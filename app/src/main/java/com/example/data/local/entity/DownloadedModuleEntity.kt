package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloaded_modules")
data class DownloadedModuleEntity(
    @PrimaryKey
    val moduleId: String,
    val moduleName: String,
    val downloadedTimestamp: Long,
    val sizeMb: Double,
    val termsCount: Int,
    val exercisesCount: Int,
    val isDownloaded: Boolean = true
)
