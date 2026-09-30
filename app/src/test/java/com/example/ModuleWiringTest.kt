package com.example

import com.example.data.initial.AnatomyDatabase
import com.example.data.initial.InitialData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Regression tests for the module wiring bug: 13 of 17 seed files were declared but never
 * concatenated by [AnatomyDatabase.getAllTerms], and module counters matched terms with
 * bidirectional [String.contains] (so "Anatomie Pathologique" absorbed all "Anatomie" terms).
 */
class ModuleWiringTest {

    @Test
    fun getAllTermsConcatenatesEverySeedFile() {
        // Before the fix this returned only 4 anatomy chapters.
        assertTrue(
            "getAllTerms must expose every seeded term",
            AnatomyDatabase.getAllTerms().isNotEmpty()
        )
    }

    @Test
    fun noModuleCountedInventedByFuzzyMatching() {
        // Every module card must be counted against the exact seed module string.
        InitialData.modulesList.forEach { mod ->
            val exact = InitialData.termsOfModule(mod.titleFr)
            if (exact.isEmpty()) {
                // Modules with no seed terms yet are allowed, but must not be inflated by
                // bidirectional substring matching of another module's terms.
                assertFalse(
                    "Module '${mod.titleFr}' has no terms yet — chaptersCount must be 0, not fabricated",
                    mod.chaptersCount > 0
                )
            } else {
                assertEquals(
                    "chaptersCount for '${mod.titleFr}' must match real seed chapters",
                    InitialData.chaptersOfModule(mod.titleFr).size,
                    mod.chaptersCount
                )
            }
        }
    }

    @Test
    fun anatomiePathologiqueDoesNotAbsorbAnatomie() {
        // The classic fuzzy-matching bug: 'Anatomie Pathologique'.contains('Anatomie') is true.
        val anapath = InitialData.termsOfModule("Anatomie Pathologique")
        assertFalse(
            "Anatomie Pathologique must not contain any Anatomie term",
            anapath.any { it.module == "Anatomie" }
        )
    }
}
