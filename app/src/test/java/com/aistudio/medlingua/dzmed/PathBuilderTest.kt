package com.aistudio.medlingua.dzmed

import com.aistudio.medlingua.dzmed.domain.path.ChapterSyllabus
import com.aistudio.medlingua.dzmed.domain.path.NodeState
import com.aistudio.medlingua.dzmed.domain.path.PathBuilder
import com.aistudio.medlingua.dzmed.domain.path.PathModule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Parcours (PathBuilder) — port Kotlin de `web-react/src/domain/__tests__/path.test.ts`.
 *
 * Règle de la spec §7 : L(n+1) s'ouvre dès que L(n) est validée à >= 70 %.
 * `moduleBest` (meilleur score du module) n'est qu'un repli d'affichage : il ne
 * d'bloque et ne valide rien.
 */
class PathBuilderTest {

    private val modules = listOf(
        PathModule("anat", "Anatomie"),
        PathModule("physio", "Physiologie")
    )

    @Test
    fun `genere des unites de 6 lecons dont L6 BOSS`() {
        val path = PathBuilder.buildPath(emptyMap(), modules)
        assertEquals(2, path.size)
        assertTrue(path.all { it.nodes.size == PathBuilder.LEVELS_PER_UNIT })
        assertTrue(path.all { it.nodes[5].isBoss })
        assertTrue(path.all { it.nodes.take(5).none { n -> n.isBoss } })
    }

    @Test
    fun `sans progression L1 ouverte et L2 a L6 verrouillees`() {
        val unit = PathBuilder.buildPath(emptyMap(), modules)[0]
        assertEquals(NodeState.AVAILABLE, unit.nodes[0].state)
        assertTrue(unit.nodes.drop(1).all { it.state == NodeState.LOCKED })
        assertEquals(0, unit.completed)
    }

    @Test
    fun `une lecon validee a 70 pourcent ouvre la suivante`() {
        val best = mapOf(PathBuilder.lessonKey("anat", 1) to PathBuilder.UNLOCK_SCORE)
        val unit = PathBuilder.buildPath(best, modules)[0]
        assertEquals(NodeState.DONE, unit.nodes[0].state)
        assertEquals(NodeState.AVAILABLE, unit.nodes[1].state)
        assertEquals(NodeState.LOCKED, unit.nodes[2].state)
        assertEquals(1, unit.completed)
    }

    @Test
    fun `une lecon sous le seuil ne debloque rien`() {
        val best = mapOf(PathBuilder.lessonKey("anat", 1) to (PathBuilder.UNLOCK_SCORE - 1))
        val unit = PathBuilder.buildPath(best, modules)[0]
        assertEquals(NodeState.AVAILABLE, unit.nodes[0].state)
        assertEquals(69, unit.nodes[0].score)
        assertEquals(NodeState.LOCKED, unit.nodes[1].state)
    }

    @Test
    fun `le deblocage se propage sur les 6 lecons`() {
        val best = (1..5).associate { PathBuilder.lessonKey("physio", it) to 85 }
        val unit = PathBuilder.buildPath(best, modules)[1]
        assertEquals("physio", unit.module.id)
        assertTrue(unit.nodes.take(5).all { it.state == NodeState.DONE })
        assertEquals(NodeState.AVAILABLE, unit.nodes[5].state)
        assertEquals(5, unit.completed)
    }

    @Test
    fun `les unites sont independantes`() {
        val best = (1..6).associate { PathBuilder.lessonKey("anat", it) to 95 }
        val path = PathBuilder.buildPath(best, modules)
        assertEquals(6, path[0].completed)
        assertEquals(NodeState.AVAILABLE, path[1].nodes[0].state)
        assertEquals(0, path[1].completed)
    }

    @Test
    fun `nextLesson renvoie la premiere lecon ouverte`() {
        val withScore = PathBuilder.buildPath(
            mapOf(PathBuilder.lessonKey("anat", 1) to 70),
            modules
        )
        assertEquals(2, PathBuilder.nextLesson(withScore[0])?.level)
        assertEquals(1, PathBuilder.nextLesson(PathBuilder.buildPath(emptyMap(), modules)[0])?.level)
    }

    @Test
    fun `moduleBest ne debloque rien - repli d affichage seul`() {
        // R�gression : le score d'un module valait jadis score de TOUTES ses le�ons.
        val best = mapOf(PathBuilder.lessonKey("anat", 1) to 85)
        val unit = PathBuilder.buildPath(best, modules)[0]
        assertEquals(NodeState.DONE, unit.nodes[0].state)
        // L2 s'ouvre (L1 valid�e) mais les le�ons suivantes restent verrouill�es
        assertEquals(NodeState.AVAILABLE, unit.nodes[1].state)
        assertEquals(85, unit.nodes[1].score)
        assertEquals(NodeState.LOCKED, unit.nodes[2].state)
        assertEquals(1, unit.completed)
        assertFalse(unit.nodes.drop(2).any { it.state == NodeState.DONE })
    }

    @Test
    fun `P0-5 un module sans exercices n offre aucun noeud jouable`() {
        // Régression bug B5 : 10 modules sur 15 n'avaient aucun exercice, mais
        // leur niveau 1 restait AVAILABLE. L'utilisateur cliquait sur un nœud
        // qui pulse, tombait sur l'écran vide `no_exercises`, revenait — et le
        // nœud pulsait encore. Maintenant un module sans contenu est entièrement
        // verrouillé.
        val path = PathBuilder.buildPath(
            emptyMap(),
            modules,
            moduleHasContent = { id -> id == "anat" }
        )
        // Module avec contenu : comportement inchangé
        val withContent = path[0]
        assertEquals(NodeState.AVAILABLE, withContent.nodes[0].state)
        assertEquals(NodeState.LOCKED, withContent.nodes[1].state)

        // Module sans contenu : TOUT est verrouillé, même le niveau 1
        val empty = path[1]
        assertEquals(NodeState.LOCKED, empty.nodes[0].state)
        assertTrue(empty.nodes.all { it.state == NodeState.LOCKED })
        // Et nextLesson() ne pointe plus dessus
        assertNull(PathBuilder.nextLesson(empty))
    }

    @Test
    fun `P0-5 un module sans contenu n ouvre pas de nouvelles lecons`() {
        // Un utilisateur pouvait avoir validé des leçons (version précédente).
        // On ne rétrograde jamais une progression acquise (les DONE restent
        // DONE), mais **aucune nouvelle leçon ne s'ouvre** : on ne renvoie pas
        // vers un écran vide.
        val best = mapOf(
            PathBuilder.lessonKey("physio", 1) to 90,
            PathBuilder.lessonKey("physio", 2) to 80
        )
        val path = PathBuilder.buildPath(
            best,
            modules,
            moduleHasContent = { id -> id == "anat" }
        )
        val empty = path[1]
        // L1 et L2 validées → DONE conservés
        assertEquals(NodeState.DONE, empty.nodes[0].state)
        assertEquals(NodeState.DONE, empty.nodes[1].state)
        // L3 ne s'ouvre pas, malgré L2 validée : module sans contenu
        assertEquals(NodeState.LOCKED, empty.nodes[2].state)
        assertTrue(empty.nodes.drop(2).all { it.state == NodeState.LOCKED })
        // Pas de prochain nœud jouable dans ce module
        assertNull(PathBuilder.nextLesson(empty))
    }

    @Test
    fun `P0-5 par defaut tout est jouable (retro-compatible)`() {
        // La signature a un défaut `{ true }` : les appelants existants et les
        // autres tests ne changent pas de comportement.
        val path = PathBuilder.buildPath(emptyMap(), modules)
        assertEquals(NodeState.AVAILABLE, path[0].nodes[0].state)
        assertEquals(NodeState.AVAILABLE, path[1].nodes[0].state)
    }

    // ─── Parcours Module → Chapitre → Leçons ──────────────────────

    private val chapters = mapOf(
        "anat" to listOf(
            PathBuilder.ChapterSeed("Angiologie", (1..6).toList()),
            PathBuilder.ChapterSeed("Ostéologie", (7..9).toList())
        ),
        "physio" to listOf(PathBuilder.ChapterSeed("Cardiologie", (10..18).toList()))
    )

    @Test
    fun `le parcours chapitre decoupe chaque chapitre en lecons de 3 termes`() {
        val path = PathBuilder.buildChapterPath(emptyMap(), modules, chapters)
        assertEquals(2, path.size)
        assertEquals(listOf("Angiologie", "Ostéologie"), path[0].chapters.map { it.chapterId })

        // 6 termes → 2 leçons de 3 + 1 révision de 6.
        val angio = path[0].chapters[0]
        assertEquals(
            listOf(ChapterSyllabus.Kind.NEW, ChapterSyllabus.Kind.NEW, ChapterSyllabus.Kind.REVIEW),
            angio.nodes.map { it.kind }
        )
        assertEquals(listOf(3, 3, 6), angio.nodes.map { it.termCount })
        assertEquals(listOf(8, 8, 16), angio.nodes.map { it.questionCount })
        assertTrue(angio.nodes.none { it.isBoss })

        // 3 termes → une seule leçon (pas de révision d'une leçon unique).
        assertEquals(1, path[0].chapters[1].nodes.size)

        // 9 termes → 3 leçons + 2 révisions.
        assertEquals(5, path[1].chapters[0].nodes.size)
        assertEquals(4, PathBuilder.totalLessons(path[0]))
    }

    @Test
    fun `sans progression seul le premier chapitre ouvre sa premiere lecon`() {
        val unit = PathBuilder.buildChapterPath(emptyMap(), modules, chapters)[0]
        val first = unit.chapters[0]
        assertEquals(NodeState.AVAILABLE, first.nodes[0].state)
        assertTrue(first.nodes.drop(1).all { it.state == NodeState.LOCKED })
        assertEquals(NodeState.AVAILABLE, first.state)

        // Les chapitres sont séquentiels : le suivant attend 50 % de moyenne.
        val second = unit.chapters[1]
        assertEquals(NodeState.LOCKED, second.state)
        assertTrue(second.nodes.all { it.state == NodeState.LOCKED })
        assertFalse(PathBuilder.isChapterOpen(second))

        assertEquals(0, PathBuilder.completedLessons(unit))
    }

    @Test
    fun `jouer L1 ouvre L2 sans toucher au chapitre suivant`() {
        val best = mapOf(PathBuilder.chapterLessonKey("anat", "Angiologie", 1) to 80)
        val unit = PathBuilder.buildChapterPath(best, modules, chapters)[0]
        assertEquals(NodeState.DONE, unit.chapters[0].nodes[0].state)
        assertEquals(NodeState.AVAILABLE, unit.chapters[0].nodes[1].state)
        // Révision obligatoire (L2 pas encore réussie) : elle reste fermée.
        assertEquals(NodeState.LOCKED, unit.chapters[0].nodes[2].state)
        assertEquals(NodeState.LOCKED, unit.chapters[1].state)
        assertEquals(1, PathBuilder.completedLessons(unit))
    }

    @Test
    fun `une revision facultative ne bloque pas la suite`() {
        // 2 leçons réussies → révision facultative, l'élève peut continuer.
        val best = mapOf(
            PathBuilder.chapterLessonKey("physio", "Cardiologie", 1) to 80,
            PathBuilder.chapterLessonKey("physio", "Cardiologie", 2) to 80
        )
        val chapter = PathBuilder.buildChapterPath(best, modules, chapters)[1].chapters[0]
        assertFalse(chapter.nodes[2].required)
        assertEquals(NodeState.AVAILABLE, chapter.nodes[2].state)
        assertEquals(NodeState.AVAILABLE, chapter.nodes[3].state)
        assertEquals(NodeState.LOCKED, chapter.nodes[4].state)
    }

    @Test
    fun `une revision obligatoire doit etre reussie pour ouvrir la suite`() {
        val failed = mapOf(
            PathBuilder.chapterLessonKey("physio", "Cardiologie", 1) to 40,
            PathBuilder.chapterLessonKey("physio", "Cardiologie", 2) to 40
        )
        val locked = PathBuilder.buildChapterPath(failed, modules, chapters)[1].chapters[0]
        assertTrue(locked.nodes[2].required)
        assertEquals(NodeState.AVAILABLE, locked.nodes[2].state)
        assertEquals(NodeState.LOCKED, locked.nodes[3].state)

        val passed = failed +
            (PathBuilder.chapterLessonKey("physio", "Cardiologie", 3) to 60)
        val open = PathBuilder.buildChapterPath(passed, modules, chapters)[1].chapters[0]
        assertEquals(NodeState.DONE, open.nodes[2].state)
        assertEquals(NodeState.AVAILABLE, open.nodes[3].state)
    }

    @Test
    fun `le chapitre suivant exige 50 pourcent de moyenne`() {
        // Leçons jouées mais moyennes sous 50 % → chapitre non acquis.
        val weak = mapOf(
            PathBuilder.chapterLessonKey("anat", "Angiologie", 1) to 40,
            PathBuilder.chapterLessonKey("anat", "Angiologie", 2) to 40,
            PathBuilder.chapterLessonKey("anat", "Angiologie", 3) to 60
        )
        val weakUnit = PathBuilder.buildChapterPath(weak, modules, chapters)[0]
        assertEquals(40, weakUnit.chapters[0].average)
        assertFalse(weakUnit.chapters[0].completed)
        assertEquals(NodeState.LOCKED, weakUnit.chapters[1].state)

        // 60 % de moyenne → le chapitre suivant s'ouvre (révision facultative).
        val strong = mapOf(
            PathBuilder.chapterLessonKey("anat", "Angiologie", 1) to 60,
            PathBuilder.chapterLessonKey("anat", "Angiologie", 2) to 60
        )
        val strongUnit = PathBuilder.buildChapterPath(strong, modules, chapters)[0]
        assertEquals(60, strongUnit.chapters[0].average)
        assertTrue(strongUnit.chapters[0].completed)
        assertEquals(NodeState.AVAILABLE, strongUnit.chapters[1].state)
        assertTrue(PathBuilder.isChapterOpen(strongUnit.chapters[1]))
    }

    @Test
    fun `les cles de lecon de chapitre sont uniques entre modules`() {
        assertFalse(
            PathBuilder.chapterLessonKey("anat", "Cardiologie", 1) ==
                PathBuilder.chapterLessonKey("physio", "Cardiologie", 1)
        )
    }

    @Test
    fun `nextChapterLesson pointe la premiere lecon jouable`() {
        val best = mapOf(PathBuilder.chapterLessonKey("anat", "Angiologie", 1) to 70)
        val unit = PathBuilder.buildChapterPath(best, modules, chapters)[0]
        val next = PathBuilder.nextChapterLesson(unit)
        assertEquals("Angiologie", next?.first?.chapterId)
        assertEquals(2, next?.second?.level)
        assertEquals(NodeState.AVAILABLE, next?.second?.state)
    }
}
