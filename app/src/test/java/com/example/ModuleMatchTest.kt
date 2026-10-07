package com.example

import com.example.domain.ModuleFilter
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Regression tests for the bidirectional module filter bug (Lot 1):
 * `term.module.contains(filter) || filter.contains(term.module)` let "Anatomie"
 * absorb "Anatomie Pathologique" in filterTerms() and in ModulesScreen chip counts.
 */
class ModuleMatchTest {

    @Test
    fun anatomieDoesNotMatchAnatomiePathologique() {
        assertFalse(ModuleFilter.matchesModule("Anatomie Pathologique", "Anatomie"))
    }

    @Test
    fun anatomiePathologiqueDoesNotMatchAnatomie() {
        assertFalse(ModuleFilter.matchesModule("Anatomie", "Anatomie Pathologique"))
    }

    @Test
    fun exactMatchWorks() {
        assertTrue(ModuleFilter.matchesModule("Anatomie", "Anatomie"))
        assertTrue(ModuleFilter.matchesModule("Anatomie Pathologique", "Anatomie Pathologique"))
    }

    @Test
    fun matchIsCaseInsensitive() {
        assertTrue(ModuleFilter.matchesModule("anatomie", "Anatomie"))
        assertTrue(ModuleFilter.matchesModule("ANATOMIE PATHOLOGIQUE", "Anatomie Pathologique"))
    }

    @Test
    fun allMatchesEverything() {
        assertTrue(ModuleFilter.matchesModule("Anatomie", "All"))
        assertTrue(ModuleFilter.matchesModule("Pharmacologie", "All"))
    }

    @Test
    fun semilogieDoesNotMatchPharmacologie() {
        assertFalse(ModuleFilter.matchesModule("Sémiologie Médicale", "Pharmacologie"))
        assertFalse(ModuleFilter.matchesModule("Génétique", "Biophysique"))
    }
}
