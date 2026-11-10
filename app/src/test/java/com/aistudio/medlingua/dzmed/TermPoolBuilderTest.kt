package com.aistudio.medlingua.dzmed

import com.aistudio.medlingua.dzmed.data.local.entity.FlashcardProgressEntity
import com.aistudio.medlingua.dzmed.data.local.entity.MedicalTermEntity
import com.aistudio.medlingua.dzmed.domain.explore.ExploreMode
import com.aistudio.medlingua.dzmed.domain.explore.TermPoolBuilder
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Sélection des cartes de l'exploration (onglet Modules).
 *
 * Les trois exigences produit vérifiées ici :
 *  - le mode « au hasard » couvre **plusieurs modules** sélectionnés ;
 *  - la quantité demandée ne dépasse **jamais 300** ;
 *  - « déjà faits » = ligne de progression, « à revoir » = note ≤ 3.
 */
class TermPoolBuilderTest {

    private fun term(id: Int, module: String) = MedicalTermEntity(
        id = id,
        termEn = "term$id",
        termFr = "terme$id",
        termAr = "مصطلح$id",
        definitionEn = "definition $id",
        definitionFr = "définition $id",
        definitionAr = "تعريف $id",
        module = module,
        chapter = "chapitre"
    )

    private val terms = listOf(
        term(1, "Anatomie"),
        term(2, "Anatomie"),
        term(3, "Biochimie"),
        term(4, "Biophysique"),
        term(5, "Biochimie")
    )

    private fun progress(termId: Int, quality: Int) =
        FlashcardProgressEntity(termId = termId, lastQuality = quality)

    @Test
    fun `aucun module choisi signifie tous les modules`() {
        val pool = TermPoolBuilder.available(terms, emptyMap(), emptySet(), ExploreMode.ALL)
        assertEquals(5, pool.size)
    }

    @Test
    fun `le hasard couvre l union de plusieurs modules`() {
        val pool = TermPoolBuilder.available(
            terms,
            emptyMap(),
            setOf("Anatomie", "Biochimie"),
            ExploreMode.RANDOM
        )
        assertEquals(setOf(1, 2, 3, 5), pool.map { it.id }.toSet())
        // Biophysique (terme 4) est bien exclu.
        assertTrue(pool.none { it.id == 4 })
    }

    @Test
    fun `la quantite est plafonnee a 300 cartes`() {
        val big = (1..400).map { term(it, "Anatomie") }
        val deck = TermPoolBuilder.build(
            big,
            emptyMap(),
            setOf("Anatomie"),
            ExploreMode.RANDOM,
            count = 999
        )
        assertEquals(TermPoolBuilder.MAX_CARDS, deck.size)
        assertEquals(300, TermPoolBuilder.MAX_CARDS)
        assertEquals(300, TermPoolBuilder.sanitizeCount(999))
        assertEquals(1, TermPoolBuilder.sanitizeCount(0))
    }

    @Test
    fun `un vivier plus petit que la quantite rend tout le vivier`() {
        val deck = TermPoolBuilder.build(
            terms,
            emptyMap(),
            setOf("Anatomie"),
            ExploreMode.ALL,
            count = 100
        )
        assertEquals(2, deck.size)
    }

    @Test
    fun `le hasard est deterministe pour une graine donnee`() {
        val big = (1..50).map { term(it, "Anatomie") }
        val first = TermPoolBuilder.build(big, emptyMap(), setOf("Anatomie"), ExploreMode.RANDOM, 10, seed = 42L)
        val second = TermPoolBuilder.build(big, emptyMap(), setOf("Anatomie"), ExploreMode.RANDOM, 10, seed = 42L)
        assertEquals(first.map { it.id }, second.map { it.id })
        // Graines différentes → tirages différents (50 cartes, 10 tirées).
        val other = TermPoolBuilder.build(big, emptyMap(), setOf("Anatomie"), ExploreMode.RANDOM, 10, seed = 43L)
        assertTrue(first.map { it.id } != other.map { it.id })
    }

    @Test
    fun `le mode deja faits ne garde que les termes avec une progression`() {
        val progress = mapOf(1 to progress(1, 5), 4 to progress(4, 1))
        val pool = TermPoolBuilder.available(terms, progress, emptySet(), ExploreMode.DONE)
        assertEquals(setOf(1, 4), pool.map { it.id }.toSet())
    }

    @Test
    fun `le mode a revoir garde les notes inferieures ou egales a 3`() {
        val progress = mapOf(
            1 to progress(1, 5), // excellent → sorti
            2 to progress(2, 4), // bien → sorti
            3 to progress(3, 3), // moyen → à revoir
            4 to progress(4, 1)  // oublié → à revoir
        )
        val pool = TermPoolBuilder.available(terms, progress, emptySet(), ExploreMode.TO_REVIEW)
        assertEquals(setOf(3, 4), pool.map { it.id }.toSet())
    }

    @Test
    fun `le mode a revoir respecte aussi la selection de modules`() {
        val progress = mapOf(3 to progress(3, 2), 4 to progress(4, 2))
        val pool = TermPoolBuilder.available(
            terms,
            progress,
            setOf("Biochimie"),
            ExploreMode.TO_REVIEW
        )
        assertEquals(listOf(3), pool.map { it.id })
    }

    @Test
    fun `les comptes par mode refletent les filtres`() {
        val progress = mapOf(1 to progress(1, 5), 3 to progress(3, 2))
        val counts = TermPoolBuilder.countsByMode(terms, progress, setOf("Anatomie", "Biochimie"))
        assertEquals(4, counts[ExploreMode.ALL])
        assertEquals(4, counts[ExploreMode.RANDOM])
        assertEquals(2, counts[ExploreMode.DONE])
        assertEquals(1, counts[ExploreMode.TO_REVIEW])
    }
}
