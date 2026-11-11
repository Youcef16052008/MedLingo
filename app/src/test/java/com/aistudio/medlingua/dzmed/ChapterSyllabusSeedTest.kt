package com.aistudio.medlingua.dzmed

import com.aistudio.medlingua.dzmed.data.initial.InitialData
import com.aistudio.medlingua.dzmed.domain.path.ChapterSyllabus
import com.aistudio.medlingua.dzmed.domain.path.ChapterSyllabus.Kind
import com.aistudio.medlingua.dzmed.domain.path.NodeState
import com.aistudio.medlingua.dzmed.domain.path.PathBuilder
import com.aistudio.medlingua.dzmed.domain.path.PathModule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Le découpage du Parcours vérifié sur **les chapitres réels du seed**
 * (4 504 termes, 15 modules) et pas seulement sur des tailles synthétiques.
 *
 * `ChapterSyllabusTest` prouve les règles sur n = 1..40 ; ce test prouve
 * qu'elles tiennent sur la matière réellement livrée : chaque terme du seed est
 * vu exactement une fois, aucune leçon n'est vide, et chaque révision rejoue
 * précisément les leçons qu'elle annonce.
 */
class ChapterSyllabusSeedTest {

    /** Un chapitre du seed, tel que `PathScreen` le construit. */
    private data class SeedChapter(
        val module: String,
        val title: String,
        val termIds: List<Int>
    ) {
        val label: String get() = "$module / $title"
    }

    private val chapters: List<SeedChapter> = InitialData.modulesList.flatMap { info ->
        val terms = InitialData.termsOfModule(info.titleFr)
        InitialData.chaptersOfModule(info.titleFr).map { chapter ->
            SeedChapter(
                module = info.titleFr,
                title = chapter,
                termIds = terms.filter { it.chapter == chapter }.map { it.id }
            )
        }
    }

    @Test
    fun `le seed fournit des chapitres non vides`() {
        assertTrue("aucun chapitre dans le seed", chapters.isNotEmpty())
        assertTrue(
            "chapitre sans terme : ${chapters.filter { it.termIds.isEmpty() }.map { it.label }}",
            chapters.all { it.termIds.isNotEmpty() }
        )
        // Les chapitres d'un module sont distincts (pas de doublon d'affichage).
        val duplicates = chapters.groupBy { it.module to it.title }.filterValues { it.size > 1 }
        assertTrue("chapitres dupliqués : ${duplicates.keys}", duplicates.isEmpty())
    }

    @Test
    fun `chaque chapitre utilise tous ses termes une seule fois`() {
        for (chapter in chapters) {
            val plan = ChapterSyllabus.plan(chapter.termIds)
            val learned = plan.filter { it.kind == Kind.NEW }.flatMap { it.termIds }
            assertEquals(
                "l'ordre ou l'exhaustivité des termes change dans ${chapter.label}",
                chapter.termIds,
                learned
            )
            assertEquals(
                "un terme est présent deux fois dans ${chapter.label}",
                chapter.termIds.size,
                learned.toSet().size
            )
        }
    }

    @Test
    fun `aucune lecon vide et jamais un terme orphelin en fin de chapitre`() {
        for (chapter in chapters) {
            val plan = ChapterSyllabus.plan(chapter.termIds)
            assertTrue("chapitre sans leçon : ${chapter.label}", plan.isNotEmpty())
            assertFalse(
                "leçon vide dans ${chapter.label} : ${plan.map { it.termCount }}",
                plan.any { it.termCount == 0 }
            )
            val learning = plan.filter { it.kind == Kind.NEW }
            for (lesson in learning) {
                // Un chapitre d'un seul terme tient en une leçon d'un terme ;
                // partout ailleurs une leçon fait 2 ou 3 termes (jamais 1, qui
                // serait une leçon orpheline, jamais 4).
                val expected = if (chapter.termIds.size == 1) {
                    lesson.termCount == 1
                } else {
                    lesson.termCount in 2..ChapterSyllabus.TERMS_PER_LESSON
                }
                assertTrue(
                    "${chapter.label} : leçon ${lesson.level} de ${lesson.termCount} termes",
                    expected
                )
            }
            // Un chapitre de plus de 3 termes ne tient jamais en une seule leçon.
            if (chapter.termIds.size > ChapterSyllabus.TERMS_PER_LESSON) {
                assertTrue(
                    "${chapter.label} : ${chapter.termIds.size} termes en ${learning.size} leçon(s)",
                    learning.size >= 2
                )
            }
        }
    }

    @Test
    fun `une revision rejoue exactement les lecons annoncees`() {
        for (chapter in chapters) {
            val plan = ChapterSyllabus.plan(chapter.termIds)
            val byLevel = plan.associateBy { it.level }
            for (review in plan.filter { it.kind == Kind.REVIEW }) {
                val reviewed = review.reviewedLevels.mapNotNull { byLevel[it] }
                assertEquals(
                    "${chapter.label} : la révision ${review.level} annonce des leçons absentes",
                    review.reviewedLevels.size,
                    reviewed.size
                )
                assertTrue(
                    "${chapter.label} : la révision ${review.level} ne revoit aucune leçon",
                    review.reviewedLevels.isNotEmpty()
                )
                assertTrue(
                    "${chapter.label} : la révision ${review.level} revoit plus de 2 leçons",
                    review.reviewedLevels.size <= ChapterSyllabus.REVIEW_EVERY
                )
                assertTrue(
                    "${chapter.label} : la révision ${review.level} revoit une révision",
                    reviewed.all { it.kind == Kind.NEW }
                )
                assertEquals(
                    "${chapter.label} : termes de la révision ${review.level}",
                    reviewed.flatMap { it.termIds },
                    review.termIds
                )
            }
        }
    }

    @Test
    fun `chaque revision suit une paire de lecons et clot le chapitre`() {
        for (chapter in chapters) {
            val plan = ChapterSyllabus.plan(chapter.termIds)
            if (plan.size == 1) {
                assertEquals(
                    "${chapter.label} : une leçon unique ne doit pas avoir de révision",
                    Kind.NEW,
                    plan.single().kind
                )
                continue
            }
            // Les niveaux sont 1..size sans trou : c'est la clé de stockage.
            assertEquals(
                "${chapter.label} : niveaux",
                (1..plan.size).toList(),
                plan.map { it.level }
            )
            assertEquals(
                "${chapter.label} : le chapitre doit finir par une révision",
                Kind.REVIEW,
                plan.last().kind
            )
            // Une révision ne revoit que les leçons d'apprentissage qui la
            // précèdent immédiatement, et jamais deux révisions de suite.
            val pending = mutableListOf<Int>()
            for (lesson in plan) {
                when (lesson.kind) {
                    Kind.NEW -> pending += lesson.level
                    Kind.REVIEW -> {
                        assertTrue(
                            "${chapter.label} : révision ${lesson.level} sans leçon à revoir",
                            lesson.reviewedLevels.isNotEmpty()
                        )
                        assertEquals(
                            "${chapter.label} : la révision ${lesson.level} ne revoit pas " +
                                "les leçons qui la précèdent",
                            pending,
                            lesson.reviewedLevels
                        )
                        pending.clear()
                    }
                }
            }
            // Le chapitre finit par une révision : plus aucune leçon en attente.
            assertTrue(
                "${chapter.label} : leçons jamais révisées $pending",
                pending.isEmpty()
            )
        }
    }

    @Test
    fun `les exemples de la spec tiennent sur le seed`() {
        // 30 termes → 10 leçons + 5 révisions = 15 nœuds.
        val thirty = chapters.filter { it.termIds.size == 30 }
        assertTrue("aucun chapitre de 30 termes dans le seed", thirty.isNotEmpty())
        for (chapter in thirty) {
            val plan = ChapterSyllabus.plan(chapter.termIds)
            assertEquals("${chapter.label} : nœuds", 15, plan.size)
            assertEquals("${chapter.label} : leçons", 10, plan.count { it.kind == Kind.NEW })
            assertEquals("${chapter.label} : révisions", 5, plan.count { it.kind == Kind.REVIEW })
        }

        // 22 termes → 8 leçons (6×3 + 2 + 2) + 4 révisions = 12 nœuds.
        val twentyTwo = chapters.filter { it.termIds.size == 22 }
        assertTrue("aucun chapitre de 22 termes dans le seed", twentyTwo.isNotEmpty())
        for (chapter in twentyTwo) {
            val plan = ChapterSyllabus.plan(chapter.termIds)
            assertEquals("${chapter.label} : nœuds", 12, plan.size)
            val learning = plan.filter { it.kind == Kind.NEW }
            assertEquals("${chapter.label} : leçons", 8, learning.size)
            assertEquals(
                "${chapter.label} : tailles de leçon",
                listOf(3, 3, 3, 3, 3, 3, 2, 2),
                learning.map { it.termCount }
            )
            assertEquals("${chapter.label} : révisions", 4, plan.count { it.kind == Kind.REVIEW })
        }

        // 38 termes (Angiologie) → 13 leçons + 7 révisions = 20 nœuds (cf. E2E parcours).
        val angio = chapters.first { it.module == "Anatomie" && it.title == "Angiologie" }
        assertEquals(20, ChapterSyllabus.lessonCount(angio.termIds))
    }

    @Test
    fun `le parcours reel expose un noeud par lecon planifiee`() {
        val modules = InitialData.modulesList.map { PathModule(it.id, it.titleFr, it.colorHex) }
        val seeds = chapters.groupBy { it.module }
            .mapKeys { (title, _) -> InitialData.modulesList.first { it.titleFr == title }.id }
            .mapValues { (_, list) ->
                list.map { PathBuilder.ChapterSeed(it.title, it.termIds) }
            }
        val path = PathBuilder.buildChapterPath(emptyMap(), modules, seeds)

        assertEquals("unité par module", InitialData.modulesList.size, path.size)
        for (unit in path) {
            for (chapter in unit.chapters) {
                val seed = seeds[unit.module.id].orEmpty().first { it.title == chapter.chapterId }
                val plan = ChapterSyllabus.plan(seed.termIds)
                assertEquals(
                    "${unit.module.title} / ${chapter.title} : nœuds affichés",
                    plan.size,
                    chapter.nodes.size
                )
                assertEquals(
                    "${unit.module.title} / ${chapter.title} : questions des nœuds",
                    plan.map { it.questionCount },
                    chapter.nodes.map { it.questionCount }
                )
                assertEquals(
                    "${unit.module.title} / ${chapter.title} : termes des nœuds",
                    plan.map { it.termCount },
                    chapter.nodes.map { it.termCount }
                )
                // Sans progression, un chapitre se joue dans l'ordre : seul le
                // premier nœud du premier chapitre est ouvert.
                assertTrue(
                    "${unit.module.title} / ${chapter.title} : aucun nœud jouable",
                    chapter.nodes.isNotEmpty()
                )
                if (chapter.state == NodeState.AVAILABLE) {
                    assertEquals(
                        "${unit.module.title} / ${chapter.title} : première leçon",
                        NodeState.AVAILABLE,
                        chapter.nodes.first().state
                    )
                }
            }
            assertNotNull(
                "${unit.module.title} : aucune leçon jouable",
                PathBuilder.nextChapterLesson(unit)
            )
        }
    }

    /**
     * Le même découpage sur **chacun des 15 modules** : aucun module n'a de
     * règle spéciale, tous passent par `PathBuilder.buildChapterPath` et donc
     * par `ChapterSyllabus.plan`. La table imprimée sert de preuve lisible.
     */
    @Test
    fun `les quinze modules appliquent le meme decoupage`() {
        val modules = InitialData.modulesList.map { PathModule(it.id, it.titleFr, it.colorHex) }
        val seeds = chapters.groupBy { it.module }
            .mapKeys { (title, _) -> InitialData.modulesList.first { it.titleFr == title }.id }
            .mapValues { (_, list) -> list.map { PathBuilder.ChapterSeed(it.title, it.termIds) } }
        val path = PathBuilder.buildChapterPath(emptyMap(), modules, seeds)

        assertEquals("un module du seed manque au parcours", modules.size, path.size)
        var totalLessons = 0
        var totalReviews = 0
        println("module | chapitres | lecons | revisions | noeuds")
        for (unit in path) {
            val unitChapters = chapters.filter { it.module == unit.module.title }
            assertTrue("${unit.module.title} : aucun chapitre", unitChapters.isNotEmpty())
            assertEquals(
                "${unit.module.title} : chapitres affichés",
                unitChapters.size,
                unit.chapters.size
            )
            // Sans progression : premier chapitre ouvert, les suivants verrouillés.
            assertEquals(
                "${unit.module.title} : le premier chapitre doit être ouvert",
                NodeState.AVAILABLE,
                unit.chapters.first().state
            )
            assertTrue(
                "${unit.module.title} : un chapitre suivant est déjà ouvert",
                unit.chapters.drop(1).all { it.state == NodeState.LOCKED }
            )
            assertNotNull(
                "${unit.module.title} : aucune leçon jouable",
                PathBuilder.nextChapterLesson(unit)
            )

            var lessons = 0
            var reviews = 0
            for (chapter in unit.chapters) {
                val seed = seeds.getValue(unit.module.id).first { it.title == chapter.chapterId }
                val plan = ChapterSyllabus.plan(seed.termIds)
                assertEquals(
                    "${unit.module.title} / ${chapter.title} : nœuds",
                    plan.size,
                    chapter.nodes.size
                )
                // Les nœuds affichés sont exactement le plan, dans l'ordre.
                assertEquals(
                    "${unit.module.title} / ${chapter.title} : types de nœud",
                    plan.map { it.kind },
                    chapter.nodes.map { it.kind }
                )
                assertEquals(
                    "${unit.module.title} / ${chapter.title} : termes par nœud",
                    plan.map { it.termCount },
                    chapter.nodes.map { it.termCount }
                )
                lessons += plan.count { it.kind == Kind.NEW }
                reviews += plan.count { it.kind == Kind.REVIEW }
            }
            totalLessons += lessons
            totalReviews += reviews
            println(
                "${unit.module.title} | ${unit.chapters.size} | $lessons | $reviews | " +
                    "${lessons + reviews}"
            )
        }

        // La somme des modules est bien le seed entier, et chaque chapitre ne
        // compte qu'une fois : la règle n'est spéciale pour aucun module.
        assertEquals(
            "nœuds affichés au total",
            totalLessons + totalReviews,
            path.sumOf { unit -> unit.chapters.sumOf { it.nodes.size } }
        )
        // Formule fermée indépendante du plan : L = ceil(n/3) leçons, puis une
        // révision par paire — la dernière leçon seule est révisée aussi, sauf
        // dans un chapitre qui n'a qu'une leçon.
        fun expectedNodes(termCount: Int): Int {
            val perLesson = ChapterSyllabus.TERMS_PER_LESSON
            val lessons = (termCount + perLesson - 1) / perLesson
            return lessons + if (lessons == 1) 0 else (lessons + 1) / 2
        }
        assertEquals(
            "nœuds par module ≠ formule fermée (L + révisions par paire)",
            chapters.sumOf { expectedNodes(it.termIds.size) },
            totalLessons + totalReviews
        )
        println(
            "TOTAL | ${chapters.size} | $totalLessons | $totalReviews | " +
                "${totalLessons + totalReviews}"
        )
        println(
            "chapitres a 1 terme = ${chapters.count { it.termIds.size == 1 }} ; " +
                "2 termes = ${chapters.count { it.termIds.size == 2 }} ; " +
                "3 termes = ${chapters.count { it.termIds.size == 3 }} ; " +
                "lecons d'apprentissage impaires = " +
                chapters.count { c ->
                    ChapterSyllabus.plan(c.termIds).count { it.kind == Kind.NEW } % 2 == 1
                }
        )
    }

    @Test
    fun `un chapitre deja acquis ouvre le suivant`() {
        val modules = listOf(PathModule("anat", "Anatomie"))
        val seeds = mapOf(
            "anat" to listOf(
                PathBuilder.ChapterSeed("Angiologie", (1..6).toList()),
                PathBuilder.ChapterSeed("Ostéologie", (7..9).toList())
            )
        )
        // 2 leçons de 3 termes réussies à 80 % → moyenne 80 % → chapitre suivant ouvert.
        val best = mapOf(
            PathBuilder.chapterLessonKey("anat", "Angiologie", 1) to 80,
            PathBuilder.chapterLessonKey("anat", "Angiologie", 2) to 80
        )
        val unit = PathBuilder.buildChapterPath(best, modules, seeds).single()
        assertEquals(NodeState.DONE, unit.chapters[0].state)
        assertEquals(NodeState.AVAILABLE, unit.chapters[1].state)
        // Une révision facultative non jouée n'empêche pas l'acquis.
        assertFalse(unit.chapters[0].nodes[2].required)
        assertTrue(unit.chapters[0].completed)
    }
}
