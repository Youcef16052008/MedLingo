package com.example

import com.example.domain.path.NodeState
import com.example.domain.path.PathBuilder
import com.example.domain.path.PathModule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
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
}
