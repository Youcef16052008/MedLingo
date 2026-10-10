package com.example

import com.example.domain.exercise.MatchScoring
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Régression P0-1 (rapport final, bug B1) : un exercice d'appariement était
 * toujours compté correct — `MatchingExerciseView.onComplete()` ne signalait
 * pas les erreurs, donc `QuizScreen` transmettait `correctAnswer` comme une
 * réponse parfaite.
 *
 * Désormais les erreurs réduisent les points, sans jamais annuler la réussite
 * de l'exercice (toutes les paires sont appariées).
 */
class MatchScoringTest {

    @Test
    fun `zero error full points`() {
        assertEquals(20, MatchScoring.pointsFor(20, 0))
        assertEquals(15, MatchScoring.pointsFor(15, 0))
    }

    @Test
    fun `one error halves the points`() {
        // 20 points → 10, 15 points → 7, 10 points → 5
        assertEquals(10, MatchScoring.pointsFor(20, 1))
        assertEquals(7, MatchScoring.pointsFor(15, 1))
        assertEquals(5, MatchScoring.pointsFor(10, 1))
    }

    @Test
    fun `many errors still halve, never zero out`() {
        // La pénalité est plafonnée à la moitié : un exercice fini reste réussi,
        // on ne descend pas en dessous de la moitié même avec 20 erreurs.
        assertEquals(10, MatchScoring.pointsFor(20, 20))
        assertEquals(5, MatchScoring.pointsFor(10, 99))
    }

    @Test
    fun `zero points exercise stays zero`() {
        assertEquals(0, MatchScoring.pointsFor(0, 0))
        assertEquals(0, MatchScoring.pointsFor(0, 5))
    }

    @Test
    fun `negative inputs cannot produce negative points`() {
        // L'implémentation ramène toute entrée négative à 0, jamais en dessous.
        assertEquals(0, MatchScoring.pointsFor(-10, 3))
        // 10 points, erreurs négatives (donnée incohérente) → traitées comme 0 erreur.
        assertEquals(10, MatchScoring.pointsFor(10, -5))
    }
}
