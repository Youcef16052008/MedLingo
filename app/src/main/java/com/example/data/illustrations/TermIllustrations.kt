package com.example.data.illustrations

import androidx.annotation.DrawableRes
import com.example.R
import com.example.data.local.entity.MedicalTermEntity

/** Only reviewed, bundled illustrations belong here. Never infer an asset from an unverified path. */
data class TermIllustration(
    @DrawableRes val drawable: Int,
    val captionFr: String,
    val captionEn: String,
    val captionAr: String,
    val credit: String
)

object TermIllustrations {
    private val entries = mapOf(
        Triple("Anatomie", "Ostéologie", "Femur") to TermIllustration(
            R.drawable.illustration_femur,
            "Fémur : tête, col, diaphyse et extrémité distale (schéma simplifié).",
            "Femur: head, neck, shaft and distal end (simplified diagram).",
            "عظم الفخذ: الرأس والعنق والجسم والطرف السفلي (رسم مبسط).",
            "Illustration originale MedLingo"
        ),
        Triple("Anatomie", "Neurologie", "Neuron") to TermIllustration(
            R.drawable.illustration_neuron,
            "Neurone : dendrites, corps cellulaire, axone myélinisé et terminaisons (schéma simplifié).",
            "Neuron: dendrites, cell body, myelinated axon and terminals (simplified diagram).",
            "الخلية العصبية: التغصنات وجسم الخلية والمحور المغمد ونهاياته (رسم مبسط).",
            "Illustration originale MedLingo"
        )
    )

    fun forTerm(term: MedicalTermEntity): TermIllustration? =
        entries[Triple(term.module, term.chapter, term.termEn)]
}
