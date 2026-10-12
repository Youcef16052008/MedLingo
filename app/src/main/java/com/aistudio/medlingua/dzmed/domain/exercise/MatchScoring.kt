package com.aistudio.medlingua.dzmed.domain.exercise

/**
 * P0-1 : règle de pénalité pour l'exercice d'appariement.
 *
 * `MatchingExerciseView` appellait `onComplete()` sans signaler les erreurs, et
 * `QuizScreen` transmettait alors `correctAnswer` au ViewModel : l'exercice était
 * **toujours** validé comme une réponse parfaite, quel que soit le nombre de
 * fausses paires cliquées (référence : rapport final, bug B1).
 *
 * La règle est extraite ici (pur, sans dépendance Android) pour être testée
 * unitairement — `QuizScreen` est un Composable et ne l'est pas :
 *   - 0 erreur → points entiers
 *   - ≥ 1 erreur → moitié des points (jamais 0 : l'exercice est réussi, le score
 *     reflète seulement la qualité de l'appariement)
 *
 * Miroir de `web-react/src/screens/QuizScreen.tsx` (`mistakesQ` + `submit(q.mistakesQ === 0)`).
 */
object MatchScoring {

    /**
     * Points accordés pour un appariement terminé.
     *
     * @param points points de l'exercice si réponse parfaite
     * @param errors nombre de fausses paires tentées avant de finir
     */
    fun pointsFor(points: Int, errors: Int): Int {
        if (points <= 0) return 0
        if (errors <= 0) return points
        // Division entière : 20 points → 10, 15 → 7. Jamais négatif.
        return (points / 2).coerceAtLeast(0)
    }
}
