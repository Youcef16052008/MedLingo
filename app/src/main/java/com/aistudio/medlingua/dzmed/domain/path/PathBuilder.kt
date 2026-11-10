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
    val isBoss: Boolean,
    /** Apprentissage (3 nouveaux termes) ou révision des leçons précédentes. */
    val kind: ChapterSyllabus.Kind = ChapterSyllabus.Kind.NEW,
    /** Révision **obligatoire** (au moins une leçon revue est sous 50 %) ou facultative. */
    val required: Boolean = true,
    /** Questions de la leçon (8 en apprentissage, jusqu'à 16 en révision). */
    val questionCount: Int = ChapterSyllabus.NEW_LESSON_QUESTIONS,
    /** Termes travaillés (3 en apprentissage, jusqu'à 6 en révision). */
    val termCount: Int = 0
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
     * Clé de stockage d'une leçon de chapitre (`moduleId|chapitre|niveau`).
     * Le séparateur `|` ne peut pas apparaître dans un id de module, ce qui
     * garantit l'unicité même si deux modules partagent un titre de chapitre.
     */
    fun chapterLessonKey(moduleId: String, chapter: String, level: Int): String =
        "$moduleId|$chapter|$level"

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

    /** Graine d'un chapitre : son titre et les ids des termes du seed. */
    data class ChapterSeed(val title: String, val termIds: List<Int> = emptyList())

    /**
     * Un chapitre réel du seed, découpé en leçons par [ChapterSyllabus] :
     * leçons de 3 nouveaux termes et révisions intercalées.
     */
    data class ChapterPath(
        val chapterId: String,
        val title: String,
        val moduleId: String,
        val nodes: List<PathNode>,
        /** AVAILABLE si le chapitre précédent est acquis, LOCKED sinon. */
        val state: NodeState = NodeState.AVAILABLE,
        /** Moyenne des leçons d'apprentissage (%). */
        val average: Int = 0,
        /** Toutes les leçons jouées et la moyenne ≥ 50 %. */
        val completed: Boolean = false
    )

    /** Unité du parcours réel : un module et ses chapitres du seed. */
    data class ModuleChapterPath(
        val module: PathModule,
        val chapters: List<ChapterPath>
    )

    /**
     * Construit le parcours **Module → Chapitre → Leçons** à partir des
     * chapitres réels du seed (`InitialData.chaptersOfModule`).
     *
     * Chaque chapitre est découpé par [ChapterSyllabus] : une leçon par
     * 3 nouveaux termes, une révision après chaque paire de leçons.
     *
     * Déblocage :
     *  - le nœud suivant s'ouvre dès que le précédent a été **joué** (score
     *    enregistré, même < 50 %) — un élève peut avancer sans être parfait ;
     *  - une révision **obligatoire** (une des 2 leçons revues est sous 50 %)
     *    doit en revanche être **réussie** (≥ 50 %) pour ouvrir la suite ;
     *  - une révision **facultative** ne bloque jamais : l'élève continue ou
     *    révise, comme il veut (point de décision après L1 + L2) ;
     *  - le chapitre suivant reste verrouillé tant que la moyenne des leçons
     *    d'apprentissage du chapitre courant est < 50 % (ou que toutes ses
     *    leçons n'ont pas été jouées).
     *
     * `lessonBest` : clé [chapterLessonKey] → meilleur score.
     * `chaptersByModule` : moduleId → chapitres du seed (ordre du seed).
     */
    fun buildChapterPath(
        lessonBest: Map<String, Int>,
        modules: List<PathModule>,
        chaptersByModule: Map<String, List<ChapterSeed>>
    ): List<ModuleChapterPath> = modules.map { module ->
        var previousChapterDone = true
        val chapters = chaptersByModule[module.id].orEmpty()
            .distinctBy { it.title }
            .map { seed ->
                val plan = ChapterSyllabus.plan(seed.termIds)
                val scoreOf: (Int) -> Int? = { level ->
                    lessonBest[chapterLessonKey(module.id, seed.title, level)]
                }
                val unlocked = previousChapterDone
                // Une révision est obligatoire dès qu'une des leçons revues
                // n'atteint pas 50 % (y compris jamais jouée).
                val mandatory = plan
                    .filter { it.kind == ChapterSyllabus.Kind.REVIEW }
                    .associate { review ->
                        review.level to review.reviewedLevels.any { lvl ->
                            (scoreOf(lvl) ?: 0) < ChapterSyllabus.PASS_SCORE
                        }
                    }

                var gateOpen = unlocked
                val nodes = plan.map { lesson ->
                    val best = scoreOf(lesson.level)
                    val attempted = best != null
                    val passed = (best ?: 0) >= ChapterSyllabus.PASS_SCORE
                    val required = lesson.kind == ChapterSyllabus.Kind.NEW ||
                        mandatory[lesson.level] == true
                    val state = when {
                        // une progression acquise n'est jamais rétrogradée
                        passed -> NodeState.DONE
                        !unlocked -> NodeState.LOCKED
                        gateOpen -> NodeState.AVAILABLE
                        else -> NodeState.LOCKED
                    }
                    gateOpen = when {
                        !unlocked -> false
                        // révision facultative : ni ouvre ni ferme la porte
                        !required -> gateOpen
                        // révision obligatoire : il faut la réussir
                        lesson.kind == ChapterSyllabus.Kind.REVIEW -> passed
                        // leçon d'apprentissage : il suffit de l'avoir jouée
                        else -> attempted
                    }
                    PathNode(
                        moduleId = module.id,
                        level = lesson.level,
                        state = state,
                        score = best ?: 0,
                        isBoss = false,
                        kind = lesson.kind,
                        required = required,
                        questionCount = lesson.questionCount,
                        termCount = lesson.termCount
                    )
                }

                val learning = plan.filter { it.kind == ChapterSyllabus.Kind.NEW }
                val learningScores = learning.map { scoreOf(it.level) ?: 0 }
                val average = if (learningScores.isEmpty()) 0
                else learningScores.sum() / learningScores.size
                val completed = plan.isEmpty() || (
                    learning.all { scoreOf(it.level) != null } &&
                        plan.filter { it.kind == ChapterSyllabus.Kind.REVIEW && mandatory[it.level] == true }
                            .all { (scoreOf(it.level) ?: 0) >= ChapterSyllabus.PASS_SCORE } &&
                        average >= ChapterSyllabus.CHAPTER_UNLOCK_AVERAGE
                    )
                previousChapterDone = completed

                ChapterPath(
                    chapterId = seed.title,
                    title = seed.title,
                    moduleId = module.id,
                    nodes = nodes,
                    state = when {
                        completed -> NodeState.DONE
                        unlocked -> NodeState.AVAILABLE
                        else -> NodeState.LOCKED
                    },
                    average = average,
                    completed = completed
                )
            }
        ModuleChapterPath(module = module, chapters = chapters)
    }

    /** Leçons réussies (≥ 50 %) dans une unité. */
    fun completedLessons(unit: ModuleChapterPath): Int =
        unit.chapters.sumOf { chapter -> chapter.nodes.count { it.state == NodeState.DONE } }

    /** Nombre total de leçons d'une unité (apprentissages + révisions). */
    fun totalLessons(unit: ModuleChapterPath): Int =
        unit.chapters.sumOf { it.nodes.size }

    /** Vrai si le chapitre peut être ouvert (le précédent est acquis). */
    fun isChapterOpen(chapter: ChapterPath): Boolean =
        chapter.state != NodeState.LOCKED

    /** Première leçon jouable d'une unité : son chapitre et son nœud. */
    fun nextChapterLesson(unit: ModuleChapterPath): Pair<ChapterPath, PathNode>? =
        unit.chapters.asSequence()
            .mapNotNull { chapter ->
                chapter.nodes.firstOrNull { it.state == NodeState.AVAILABLE }
                    ?.let { chapter to it }
            }
            .firstOrNull()


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
