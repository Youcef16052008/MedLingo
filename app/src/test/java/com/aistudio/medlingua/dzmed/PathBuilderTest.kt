package com.aistudio.medlingua.dzmed

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
}
