package com.aistudio.medlingua.dzmed.domain.explore

import com.aistudio.medlingua.dzmed.data.local.entity.FlashcardProgressEntity
import com.aistudio.medlingua.dzmed.data.local.entity.MedicalTermEntity
import com.aistudio.medlingua.dzmed.domain.ModuleFilter
import kotlin.random.Random

/**
 * Sélection des cartes d'une session d'exploration.
 *
 * Règles (toutes testables, aucun accès Android) :
 *  - les modules sont **multi-sélectionnables** ; un ensemble vide = tous ;
 *  - [ExploreMode.RANDOM] tire au hasard mais **déterministe** pour une graine
 *    donnée (les tests et les E2E rejouent la même session) ;
 *  - la quantité demandée est bornée à [MAX_CARDS] (300) ;
 *  - si le vivier est plus petit que la quantité, on rend tout le vivier
 *    (jamais de session vide tant qu'il reste au moins un terme) ;
 *  - l'ordre du seed est conservé hors mode [ExploreMode.RANDOM].
 */
object TermPoolBuilder {

    /** Plafond dur demandé par la spec : jamais plus de 300 cartes. */
    const val MAX_CARDS = 300

    /** Quantités proposées dans l'UI (50 et 100 demandés explicitement). */
    val COUNTS: List<Int> = listOf(10, 25, 50, 100, 150, 200, 300)

    const val DEFAULT_COUNT = 50

    /** Dernière qualité considérée comme « fragile » (donc à revoir). */
    const val FRAGILE_MAX_QUALITY = 3

    fun sanitizeCount(count: Int): Int = count.coerceIn(1, MAX_CARDS)

    /** Termes du vivier, avant bornage par la quantité. */
    fun available(
        terms: List<MedicalTermEntity>,
        progressByTermId: Map<Int, FlashcardProgressEntity>,
        modules: Set<String>,
        mode: ExploreMode
    ): List<MedicalTermEntity> {
        val scoped = terms.filter { term ->
            modules.isEmpty() || modules.any { ModuleFilter.matchesModule(term.module, it) }
        }
        return when (mode) {
            ExploreMode.ALL, ExploreMode.RANDOM -> scoped
            ExploreMode.DONE -> scoped.filter { progressByTermId.containsKey(it.id) }
            ExploreMode.TO_REVIEW -> scoped.filter { term ->
                val quality = progressByTermId[term.id]?.lastQuality
                quality != null && quality <= FRAGILE_MAX_QUALITY
            }
        }
    }

    /**
     * Construit la session : vivier filtré puis borné à [count] (max 300).
     *
     * @param seed graine du tirage aléatoire ; identique = même deck.
     */
    fun build(
        terms: List<MedicalTermEntity>,
        progressByTermId: Map<Int, FlashcardProgressEntity>,
        modules: Set<String>,
        mode: ExploreMode,
        count: Int,
        seed: Long = 0L
    ): List<MedicalTermEntity> {
        val pool = available(terms, progressByTermId, modules, mode)
        val target = sanitizeCount(count)
        if (pool.size <= target) return pool
        return when (mode) {
            ExploreMode.RANDOM -> pool.shuffled(Random(seed)).take(target)
            else -> pool.take(target)
        }
    }

    /** Comptes par mode pour l'écran de configuration (« 412 · 38 · 96 »). */
    fun countsByMode(
        terms: List<MedicalTermEntity>,
        progressByTermId: Map<Int, FlashcardProgressEntity>,
        modules: Set<String>
    ): Map<ExploreMode, Int> = ExploreMode.entries.associateWith { mode ->
        available(terms, progressByTermId, modules, mode).size
    }
}
