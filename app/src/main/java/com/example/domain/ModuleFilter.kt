package com.example.domain

/**
 * Single source of truth for module filter matching.
 * Seed [com.example.data.local.entity.MedicalTermEntity.module] values and UI filter keys
 * both come from ModuleInfo.titleFr, so matching must be exact (case-insensitive).
 * The previous bidirectional String.contains let "Anatomie" absorb "Anatomie Pathologique".
 */
object ModuleFilter {
    fun matchesModule(termModule: String, moduleFilter: String): Boolean {
        if (moduleFilter == "All") return true
        return termModule.equals(moduleFilter, ignoreCase = true)
    }
}
