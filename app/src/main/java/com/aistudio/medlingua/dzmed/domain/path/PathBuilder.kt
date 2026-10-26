package com.aistudio.medlingua.dzmed.domain.path

import com.aistudio.medlingua.dzmed.domain.lesson.LessonGeneratorV2
import com.aistudio.medlingua.dzmed.domain.path.NodeState
import com.aistudio.medlingua.dzmed.domain.path.PathUnit

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
     *
     * P0-5 (rapport final, bug B5) : `moduleHasContent` indique si un module a des
     * exercices jouables. Un module sans contenu ne propose **aucun** nœud
     * AVAILABLE — l'utilisateur ne clique pas sur un nœud qui mène à l'écran vide.
     * Ces nœuds restent LOCKED (et l'UI affiche « Contenu à venir »).
     */
    fun buildPath(
        lessonBest: Map<String, Int>,
        modules: List<PathModule>,
        moduleHasContent: (moduleId: String) -> Boolean = { true }
    ): List<PathUnit> =
        modules.map { module ->
            val moduleBest = lessonBest
                .filterKeys { it.substringBefore(':') == module.id }
                .values
                .maxOrNull() ?: 0
            // P0-5 : un module sans exercice ne débloque rien. Toute la logique
            // ci-dessous est conservée à l'identique pour les modules normaux.
            val hasContent = moduleHasContent(module.id)
            val nodes = mutableListOf<PathNode>()
            var previousDone = false

            for (level in 1..LEVELS_PER_UNIT) {
                val key = lessonKey(module.id, level)
                val lessonScore = lessonBest[key]
                val score = lessonScore ?: moduleBest
                val state = when {
                    (lessonScore ?: 0) >= UNLOCK_SCORE -> NodeState.DONE
                    // P0-5 : pas de contenu → nœud verrouillé, même au niveau 1
                    !hasContent -> NodeState.LOCKED
                    // la première leçon d'une unité est toujours ouverte
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
        unit.nodes
            .firstOrNull { it.state == NodeState.AVAILABLE }

    // ─── Nouveau : Module → Chapter → Lesson ────────────────────────

    /** Chapitre d'un module (apparait dans l'architecture du parcours). */
    data class PathChapter(
        val chapterId: String,
        val title: String,
        val moduleId: String,
        val nodes: List<PathNode>
    )

    /** Nœud de leçon dans un chapitre (nouveau nœud dans l'architecture).
     *  levelIndex 0-based, kind = NEW | REVIEW, questionCount = 8 | 16. */
    data class LessonNode(
        val chapterId: String,
        val levelIndex: Int,
        val kind: LessonKind,
        val questionCount: Int,
        val state: NodeState,
        val score: Int,
        val hearts: Int = LessonGeneratorV2.HEARTS_MAX
    )

    /** 1 unité = 1 module avec ses chapitres et les nœuds de leçon. */
    data class LessonPathUnit(
        val module: PathModule,
        val chapters: List<PathChapter> = emptyList()
    )

    /** Type de leçon dans le parcours. */
    enum class LessonKind { NEW, REVIEW }

    /**
     * Construit le parcours au format Module → Chapter → Lesson.
     * `lessonBest` : clé "${chapterId}:L${index}" → meilleur score.
     * `chapterMap` : clé chapterId → PathChapter (avec titre, etc.)
     */
    fun buildLessonPath(
        lessonBest: Map<String, Int>,
        modules: List<PathModule>,
        chapterMap: Map<String, PathChapter> = emptyMap(),
        nodeStates: (chapterId: String, levelIndex: Int) -> NodeState = { _, _ -> NodeState.LOCKED },
        onScore: (chapterId: String, levelIndex: Int) -> Int = { _, _ -> 0 }
    ): List<LessonPathUnit> =
        modules.map { module ->
            val chapterIds = chapterMap.keys.filter { it.startsWith(module.id) }
            val chapters = chapterIds.mapNotNull { chapterId ->
                chapterMap[chapterId]?.takeIf { it.moduleId == module.id }?.let { chap ->
                    val nodes = (0 until 6).map { idx ->
                        val key = "${chapterId}:L${idx + 1}"
                        val score = onScore(chapterId, idx)
                        nodeStates(chapterId, idx).let { state ->
                            LessonNode(
                                chapterId = chapterId,
                                levelIndex = idx,
                                kind = if (state == NodeState.DONE) LessonKind.REVIEW else LessonKind.NEW,
                                questionCount = if (state == NodeState.DONE) LessonGeneratorV2.REVIEW_SIZE else LessonGeneratorV2.LESSON_SIZE,
                                state = state,
                                score = score
                            )
                        }
                    }
                    PathChapter(
                        chapterId = chap.chapterId,
                        title = chap.title,
                        moduleId = chap.moduleId,
                        nodes = nodes.map { node ->
                            PathNode(
                                moduleId = node.chapterId,
                                level = node.levelIndex,
                                state = node.state,
                                score = node.score,
                                isBoss = node.levelIndex == 5
                            )
                        }
                    )
                }
            }
            LessonPathUnit(module = module, chapters = chapters)
        }}
