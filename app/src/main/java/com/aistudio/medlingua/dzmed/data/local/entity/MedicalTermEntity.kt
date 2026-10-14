package com.aistudio.medlingua.dzmed.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * P0-2 (revue database v2, défaut D2) : `medical_terms` est filtrée en
 * permanence par `module` (écrans de module) et par `(termEn, module)`
 * (`restoreBookmark`). Sans index, chacune de ces requêtes est un full scan
 * sur 4 504 lignes.
 *
 * `index_terms_module_chapter` couvre aussi `ORDER BY module, id` de
 * `getAllTerms()` — SQLite peut servir le tri depuis l'index.
 *
 * P1-1 (revue database v2, défaut D3) : la colonne legacy `example` a été
 * supprimée. Elle dupliquait `exampleEn` (692 valeurs identiques sur 972) et
 * n'était lue par personne — grep strict des lecteurs : 0.
 */
@Entity(
    tableName = "medical_terms",
    indices = [
        Index("module"),
        Index("module", "chapter"),
        Index("termEn", "module")
    ]
)
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
    // P1-1 : `example` (legacy) supprimé — doublon de exampleEn, jamais lu.
    val exampleEn: String = "",
    val exampleFr: String = "",
    val exampleAr: String = "",
    val imageAsset: String = "",
    val audioAsset: String = "",
    val ipaPhonetic: String = "",
    val isBookmarked: Boolean = false
)

