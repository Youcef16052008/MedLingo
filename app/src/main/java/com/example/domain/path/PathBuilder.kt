package com.example.domain.path

/**
 * Parcours « chemin vert » : 1 unité = 1 module = 6 leçons (L1 → L6, L6 = BOSS).
 * Déblocage : une leçon est ouverte dès que la précédente est validée à ≥ 70 %.
 *
 * Port Kotlin de `web-react/src/domain/path.ts` : l'état d'entrée est une map
 * `lessonKey → meilleur score` (table `lesson_scores`).
 */

/** Module du parcours (id stable + intitulé affiché). */
data class PathModule(
    val id: String,
    val title: String,
    val colorHex: Long = 0xFF58CC02
)

enum class NodeState { LOCKED, AVAILABLE, DONE }

data class PathNode(
    val moduleId: String,
    val level: Int,
    val state: NodeState,
    val score: Int,
    val isBoss: Boolean
)

data class PathUnit(
    val module: PathModule,
    /** Leçons validées (≥ 70 %). */
    val completed: Int,
    val nodes: List<PathNode>
)

object PathBuilder {

    /** Seuil de validation d'une leçon (%). */
    const val UNLOCK_SCORE = 70
    const val LEVELS_PER_UNIT = 6

    /** Clé de stockage d'une leçon (table `lesson_scores.lessonKey`). */
    fun lessonKey(moduleId: String, level: Int): String = "$moduleId:$level"

    /**
     * Construit les unités du parcours.
     * `lessonBest` : clé `module:level` → meilleur score ; le meilleur score du module
     * sert de repli d'affichage (comme sur le web) mais ne débloque rien à lui seul.
     */
    fun buildPath(
        lessonBest: Map<String, Int>,
        modules: List<PathModule>
    ): List<PathUnit> =
        modules.map { module ->
            val moduleBest = lessonBest
                .filterKeys { it.substringBefore(':') == module.id }
                .values
                .maxOrNull() ?: 0
            val nodes = mutableListOf<PathNode>()
            var previousDone = false

            for (level in 1..LEVELS_PER_UNIT) {
                val key = lessonKey(module.id, level)
                val lessonScore = lessonBest[key]
                val score = lessonScore ?: moduleBest
                val state = when {
                    // Le d�blocage regarde uniquement la le�on : le meilleur score du
                    // module ne sert qu'à l'affichage (spec §7 : L(n+1) ouverte d�s
                    // que L(n) est valid�e � >= 70 %).
                    (lessonScore ?: 0) >= UNLOCK_SCORE -> NodeState.DONE
                    // la premi�re le�on d'une unit� est toujours ouverte
                    level == 1 -> NodeState.AVAILABLE
                    previousDone -> NodeState.AVAILABLE
                    else -> NodeState.LOCKED
                }
                nodes += PathNode(
                    moduleId = module.id,
                    level = level,
                    state = state,
                    score = score,
                    isBoss = level == LEVELS_PER_UNIT
                )
                previousDone = state == NodeState.DONE
            }

            PathUnit(
                module = module,
                completed = nodes.count { it.state == NodeState.DONE },
                nodes = nodes
            )
        }

    /** Première leçon jouable d'une unité (le nœud « pulse » du parcours). */
    fun nextLesson(unit: PathUnit): PathNode? =
        unit.nodes.firstOrNull { it.state == NodeState.AVAILABLE }
}
