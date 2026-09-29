package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medical_terms")
data class MedicalTermEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val termEn: String,
    val termFr: String,
    val termAr: String,
    val definitionEn: String,
    val definitionFr: String,
    val definitionAr: String,
    val etymology: String = "",
    val clinicalPearl: String = "",
    val mnemonic: String = "",
    val module: String = "Anatomie",
    val chapter: String = "",
    val example: String = "",
    val exampleEn: String = "",
    val exampleFr: String = "",
    val exampleAr: String = "",
    val imageAsset: String = "",
    val audioAsset: String = "",
    val ipaPhonetic: String = "",
    val isBookmarked: Boolean = false
)

