package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val level: Int = 1, // 1: Vocabulaire, 2: Collocations, 3: Phrases Simples, 4: Phrases Complexes, 5: Paragraphes, 6: Cas Cliniques
    val type: String, // "mcq", "fill_blank", "matching", "sentence_order", "reading", "clinical_case"
    val difficulty: String, // "beginner", "intermediate", "advanced"
    val module: String,
    val chapter: String,
    val questionEn: String,
    val questionFr: String,
    val questionAr: String,
    val optionsRaw: String, // Pipe-separated options, words to order, or key-value pairs
    val correctAnswer: String,
    val explanationEn: String,
    val explanationFr: String,
    val explanationAr: String,
    val points: Int = 10,
    val contextTextEn: String = "", // Reading passage or Clinical Case vignette
    val contextTextFr: String = "",
    val contextTextAr: String = ""
) {
    fun getOptionsList(): List<String> {
        return if (optionsRaw.isBlank()) emptyList() else optionsRaw.split("|")
    }
}
