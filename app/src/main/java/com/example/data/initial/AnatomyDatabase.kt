package com.example.data.initial

import com.example.data.terms.AnatomieTerms

/**
 * Index des 412 termes d'anatomie.
 *
 * Délégue au fichier consolidé `data/terms/AnatomieTerms.kt` — un seul
 * endroit pour tous les termes d'anatomie au lieu des 11 fichiers fragmentés
 * d'origine (Osteology, Arthrology, Myology, Neurology, Angiology, Organ,
 * Sensory, Digestive, Respiratory, Urogenital, GeneralAnatomy).
 */
class AnatomyDatabase {
    companion object {
        fun getAllTerms() = AnatomieTerms.terms
    }
}
