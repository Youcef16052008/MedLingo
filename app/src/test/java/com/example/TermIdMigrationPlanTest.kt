package com.example

import com.example.data.local.database.TermIdMigrationPlan
import com.example.data.local.database.TermIdMigrationPlan.TermIdentity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Couvre la logique de [TermIdMigrationPlan], qui décide où atterrit l'historique de
 * révision après la renumérotation des seeds.
 *
 * L'enjeu : `flashcard_progress.termId` référence `medical_terms.id`. Si l'id a changé et
 * que la migration ne le réattache pas, l'historique part sur le mauvais terme — une
 * corruption silencieuse, bien plus grave qu'une perte de données.
 */
class TermIdMigrationPlanTest {

    private fun stored(id: Int, termEn: String, module: String) = TermIdentity(id, termEn, module)

    @Test
    fun `reattaches progress to the new id of the same term`() {
        // L'utilisateur avait révisé l'ancien id 7, qui est aujourd'hui l'id 3.
        val plan = TermIdMigrationPlan.compute(
            stored = listOf(stored(7, "Cell", "Anatomie")),
            seed = listOf(stored(3, "Cell", "Anatomie")),
            progressTermIds = setOf(7)
        )

        assertEquals(mapOf(7 to 3), plan.progressRemap)
        assertTrue("nothing must be dropped", plan.droppedTermIds.isEmpty())
        // 7 est un ancien id que la migration déplace : ce n'est pas une progression orpheline,
        // elle est simplement en cours de déplacement.
        assertTrue(plan.droppedProgressIds.isEmpty())
    }

    @Test
    fun `keeps ids that did not change`() {
        val plan = TermIdMigrationPlan.compute(
            stored = listOf(stored(3, "Cell", "Anatomie")),
            seed = listOf(stored(3, "Cell", "Anatomie")),
            progressTermIds = setOf(3)
        )

        assertTrue(plan.progressRemap.isEmpty())
        assertTrue(plan.droppedProgressIds.isEmpty())
    }

    @Test
    fun `drops terms removed from the seed and their progress`() {
        val plan = TermIdMigrationPlan.compute(
            stored = listOf(stored(4, "Neurone", "Anatomie"), stored(5, "Cell", "Anatomie")),
            seed = listOf(stored(5, "Cell", "Anatomie")),
            progressTermIds = setOf(4, 5)
        )

        assertEquals(setOf(4), plan.droppedTermIds)
        assertEquals(setOf(4), plan.droppedProgressIds)
    }

    @Test
    fun `drops progress pointing at a term that no longer exists`() {
        val plan = TermIdMigrationPlan.compute(
            stored = listOf(stored(5, "Cell", "Anatomie")),
            seed = listOf(stored(5, "Cell", "Anatomie"), stored(6, "Neurone", "Anatomie")),
            progressTermIds = setOf(5, 999)
        )

        assertEquals(setOf(999), plan.droppedProgressIds)
    }

    @Test
    fun `never merges two old rows onto one new id`() {
        // Deux lignes distinctes (id 7 et id 12) se retrouvent rattachées au même terme
        // "Cell" : la seconde est un doublon du premier avec une espace autour.
        // Les fusionner ferait perdre la progression de l'une d'elles, et rattacherait son
        // historique au même terme que l'autre : on préfère les abandonner proprement.
        val plan = TermIdMigrationPlan.compute(
            stored = listOf(stored(7, "Cell", "Anatomie"), stored(12, "Cell ", "Anatomie")),
            seed = listOf(stored(3, "Cell", "Anatomie")),
            progressTermIds = setOf(7, 12)
        )

        assertTrue("ambiguous rows must not be remapped", plan.progressRemap.isEmpty())
        // Les deux progressions deviennent orphelines : on ne les rattache pas au hasard.
        assertEquals(setOf(7, 12), plan.droppedProgressIds)
    }

    @Test
    fun `does not attach progress to another term that reuses the id`() {
        // L'ancien id 7 devient l'id 3 de "Cell", mais l'id 3 existe déjà en base pour
        // "Neurone". Ne pas déplacer aurait rattaché l'historique de "Cell" à "Neurone".
        val plan = TermIdMigrationPlan.compute(
            stored = listOf(stored(7, "Cell", "Anatomie"), stored(3, "Neurone", "Anatomie")),
            seed = listOf(stored(3, "Cell", "Anatomie"), stored(4, "Neurone", "Anatomie")),
            progressTermIds = setOf(7, 3)
        )

        assertEquals(mapOf(7 to 3, 3 to 4), plan.progressRemap)
        assertTrue(plan.droppedTermIds.isEmpty())
        assertTrue(plan.droppedProgressIds.isEmpty())
    }

    @Test
    fun `matches identity regardless of case and surrounding spaces`() {
        val plan = TermIdMigrationPlan.compute(
            stored = listOf(stored(1, "  cell ", "Anatomie")),
            seed = listOf(stored(9, "Cell", "Anatomie")),
            progressTermIds = setOf(1)
        )

        assertEquals(mapOf(1 to 9), plan.progressRemap)
    }

    @Test
    fun `does not confuse terms that concatenate to the same key`() {
        // Sans séparateur, ("ab", "c") et ("a", "bc") donneraient la même clé.
        assertTrue(
            TermIdMigrationPlan.key("ab", "c") != TermIdMigrationPlan.key("a", "bc")
        )
    }

    @Test
    fun `scales to the whole seed without changing behaviour`() {
        val seed = (1..500).map { TermIdentity(it, "term$it", "Module") }
        // Les mêmes 500 termes, mais stockés 1000 ids plus loin.
        val storedOld = (1..500).map { i -> TermIdentity(i + 1000, "term$i", "Module") }
        val plan = TermIdMigrationPlan.compute(
            stored = storedOld,
            seed = seed,
            progressTermIds = (1001..1500).toSet()
        )

        assertEquals(500, plan.progressRemap.size)
        assertEquals(1, plan.progressRemap[1001])
        assertEquals(500, plan.progressRemap[1500])
        assertTrue(plan.droppedTermIds.isEmpty())
        assertTrue(plan.droppedProgressIds.isEmpty())
    }
}