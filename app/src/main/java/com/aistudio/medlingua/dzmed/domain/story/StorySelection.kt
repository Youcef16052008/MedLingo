package com.aistudio.medlingua.dzmed.domain.story

/**
 * Sélection de la séance du jour — miroir exact de `learning/selection.ts` (web).
 *
 * Priorité déterministe :
 *   1. séance interrompue (reprise) ;
 *   2. histoire avec le plus d'expressions échues ;
 *   3. histoire jamais commencée (première du fichier) ;
 *   4. histoire terminée depuis le plus longtemps (rotation).
 *
 * `progress` n'expose que les champs utiles au choix (miroir de
 * StorySelectionInput côté web) — ne pas y brancher l'entité complète.
 */
data class StoryPickProgress(
    val completedAt: Long,
    val savedAt: Long
)

fun pickTodayStory(
    stories: List<Story>,
    exprSm2: ExprScheds,
    progress: Map<String, StoryPickProgress>,
    now: Long
): Story? {
    if (stories.isEmpty()) return null

    // 1. Reprise : une séance ouverte prime sur tout.
    stories.firstOrNull { s ->
        val p = progress[s.storyId]
        p != null && p.completedAt == 0L && p.savedAt > 0L
    }?.let { return it }

    // 2. Révisions dues : l'histoire qui en a le plus repasse en premier.
    var best: Story? = null
    var bestDue = 0
    for (s in stories) {
        val due = s.targetPhrases.count { isDueExpr(exprSm2[it.id], now) }
        if (due > bestDue) {
            best = s
            bestDue = due
        }
    }
    best?.let { return it }

    // 3. Jamais commencée : la première du fichier (ordre éditorial).
    stories.firstOrNull { it.storyId !in progress }?.let { return it }

    // 4. Rotation : la terminée depuis le plus longtemps.
    return stories.minByOrNull { progress[it.storyId]?.completedAt ?: 0L }
}
