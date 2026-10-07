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
        // Before the fix this returned only 4 anatomy chapters (~200 terms).
        val all = AnatomyDatabase.getAllTerms()
        assertTrue(
            "getAllTerms must expose every seeded term (got ${all.size})",
            all.size >= 380
        )
        // Every registered module must contribute at least one seeded term
        InitialData.modulesList.forEach { mod ->
            assertTrue(
                "module '${mod.titleFr}' has no seeded terms",
                InitialData.termsOfModule(mod.titleFr).isNotEmpty()
            )
        }
    }

    @Test
    fun moduleLookupsAreExactAndDisjoint() {
        val titles = InitialData.modulesList.map { it.titleFr }
        assertEquals(
            "modulesList ids must be unique",
            titles.size,
            InitialData.modulesList.map { it.id }.toSet().size
        )
        assertEquals("modulesList titles must be unique", titles.size, titles.toSet().size)

        // A lookup built on bidirectional String.contains would double-count
        // ("Anatomie" would pull "Anatomie Pathologique" terms and vice versa):
        // the sum of per-module lookups must equal the number of terms directly
        // attributed to a registered module — computed via a different code path.
        val titleSet = titles.toSet()
        val lookedUp = titles.sumOf { InitialData.termsOfModule(it).size }
        val directlyAttributed = InitialData.terms.count { it.module in titleSet }
        assertEquals(
            "per-module lookups must cover every registered-module term exactly once",
            directlyAttributed,
            lookedUp
        )
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
