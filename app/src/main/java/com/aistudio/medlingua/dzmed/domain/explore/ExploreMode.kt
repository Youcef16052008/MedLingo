package com.aistudio.medlingua.dzmed.domain.explore

/**
 * Modes d'exploration du lexique (onglet Modules).
 *
 * Le mode ne change **que la sélection des termes** ; les cartes, la correction
 * et la répétition espacée (SM-2) restent communes à tous les modes.
 */
enum class ExploreMode {
    /** Tous les termes des modules choisis, dans l'ordre du seed. */
    ALL,

    /** Tirage aléatoire (déterministe pour une graine donnée) dans les modules choisis. */
    RANDOM,

    /** Termes déjà étudiés au moins une fois (une ligne `flashcard_progress` existe). */
    DONE,

    /**
     * Termes fragiles : dernière évaluation **< 4** (« Bien » / « Parfait »).
     * C'est le « pas reçus bien ou excellent » de la spec : oublié, difficile
     * ou moyen. Les cartes notées 4–5 sortent de ce mode.
     */
    TO_REVIEW
}
