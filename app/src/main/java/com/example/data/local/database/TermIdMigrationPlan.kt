package com.example.data.local.database

/**
 * Plan de renumérotation des termes, calculé hors de la migration Room.
 *
 * L'intégration des dumps SQL a dédupliqué les ids des seeds : un terme n'a plus le même
 * `id` qu'avant. Or `flashcard_progress.termId` est la clé primaire qui référence
 * `medical_terms.id`, donc une base existante se retrouverait avec un historique de
 * révision rattaché au mauvais terme — une corruption silencieuse, bien plus grave qu'une
 * perte de données.
 *
 * L'identité stable d'un terme est son couple (termEn, module), pas son id. Cette classe
 * calcule donc le réattachement à partir de ce couple, sans toucher au SQL : la migration
 * ne fait qu'appliquer le plan.
 */
internal object TermIdMigrationPlan {

    /** L'identité d'un terme, côté base comme côté seed. */
    data class TermIdentity(val id: Int, val termEn: String, val module: String)

    /**
     * @param progressRemap oldId -> newId, pour les lignes de progression à déplacer
     * @param droppedTermIds lignes `medical_terms` retirées du seed
     * @param droppedProgressIds lignes `flashcard_progress` devenues orphelines
     */
    data class Plan(
        val progressRemap: Map<Int, Int>,
        val droppedTermIds: Set<Int>,
        val droppedProgressIds: Set<Int>
    )

    /**
     * La clé est séparée par NUL : aucun terme ni module ne peut contenir ce caractère,
     * donc aucune paire ne peut collisionner avec une autre par concaténation.
     */
    fun key(termEn: String, module: String): String =
        termEn.trim().lowercase() + "\u0000" + module

    fun compute(stored: List<TermIdentity>, seed: List<TermIdentity>, progressTermIds: Set<Int>): Plan {
        val liveIds = HashSet<Int>(seed.size * 2)
        val byIdentity = HashMap<String, Int>(seed.size * 2)
        seed.forEach {
            liveIds += it.id
            byIdentity[key(it.termEn, it.module)] = it.id
        }

        val droppedTermIds = LinkedHashSet<Int>()
        val candidates = LinkedHashMap<Int, Int>()
        stored.forEach { row ->
            val newId = byIdentity[key(row.termEn, row.module)]
            when {
                newId == null -> droppedTermIds += row.id
                newId != row.id -> candidates[row.id] = newId
            }
        }

        // Deux anciennes lignes ne doivent jamais fusionner sur un même nouvel id : cela
        // ferait perdre la progression de l'une d'elles, et pire, rattacherait son
        // historique au terme de l'autre. Ces collisions sont abandonnées, puis nettoyées
        // comme orphelines.
        val ambiguous = candidates.values.groupingBy { it }.eachCount().filterValues { it > 1 }.keys
        val progressRemap = LinkedHashMap<Int, Int>()
        candidates.forEach { (oldId, newId) ->
            if (newId !in ambiguous) progressRemap[oldId] = newId
        }

        // Une progression survit si, une fois déplacée, elle pointe un id vivant du seed.
        // Les lignes déplacées ne sont donc pas « perdues » : elles atterrissent ailleurs.
        val droppedProgressIds = progressTermIds.filterTo(LinkedHashSet()) { termId ->
            val finalId = progressRemap[termId] ?: termId
            finalId !in liveIds
        }

        return Plan(
            progressRemap = progressRemap,
            droppedTermIds = droppedTermIds,
            droppedProgressIds = droppedProgressIds
        )
    }
}