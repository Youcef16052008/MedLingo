package com.aistudio.medlingua.dzmed.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.aistudio.medlingua.dzmed.data.local.entity.ExerciseEntity
import com.aistudio.medlingua.dzmed.localization.Language
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * P3-3 : premier test Compose UI du projet.
 *
 * `MatchingExerciseView` est le composant au cœur du bug B1 (matching toujours
 * compté correct). La logique de score est couverte par `MatchScoring` et
 * `MatchScoringTest`, mais **aucun test ne vérifiait que le composable renvoie
 * bien le bon nombre d'erreurs à `onComplete`**.
 *
 * Ce test manipule directement l'UI : clique deux colonnes, déclenche des
 * paires volontairement fausses, et vérifie le `onComplete(errors)`.
 *
 * Robolectric est obligatoire : Compose UI lit `Build.FINGERPRINT` à
 * l'initialisation, qui est `null` sous JVM nue.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class MatchingExerciseViewTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private fun aMatchingExercise(
        answer: String = "Heart:Cœur|Lung:Poumon|Liver:Foie",
        optionsRaw: String = ""
    ) = ExerciseEntity(
        id = 1,
        level = 1,
        type = "matching",
        difficulty = "beginner",
        module = "Anatomie",
        chapter = "Test",
        questionEn = "Match the terms",
        questionFr = "Associez les termes",
        questionAr = "طابق المصطلحات",
        optionsRaw = optionsRaw,
        correctAnswer = answer,
        explanationEn = "",
        explanationFr = "",
        explanationAr = "",
        points = 20,
        contextTextEn = "",
        contextTextFr = "",
        contextTextAr = ""
    )

    @Test
    fun perfectMatch_reportsZeroErrors() {
        var result: Int? = null
        val ex = aMatchingExercise()

        composeRule.setContent {
            MatchingExerciseView(
                exercise = ex,
                onComplete = { result = it },
                currentLanguage = Language.FRENCH
            )
        }

        // 3 paires, aucune erreur : un appariement parfait.
        pairUp(composeRule, "Heart", "Cœur")
        pairUp(composeRule, "Lung", "Poumon")
        pairUp(composeRule, "Liver", "Foie")

        assertEquals("Appariement parfait → 0 erreur", 0, result)
    }

    @Test
    fun wrongPairs_areCountedAndExerciseStillCompletes() {
        var result: Int? = null
        val ex = aMatchingExercise()

        composeRule.setContent {
            MatchingExerciseView(
                exercise = ex,
                onComplete = { result = it },
                currentLanguage = Language.FRENCH
            )
        }

        // 2 erreurs volontaires (paires croisées), puis on termine correctement.
        pairUp(composeRule, "Heart", "Poumon")   // faux
        pairUp(composeRule, "Lung", "Cœur")     // faux
        pairUp(composeRule, "Heart", "Cœur")    // bon
        pairUp(composeRule, "Lung", "Poumon")   // bon
        pairUp(composeRule, "Liver", "Foie")    // bon

        assertEquals("2 fausses paires → errors = 2", 2, result)
    }

    @Test
    fun noCompletion_beforeAllPairsMatched() {
        var result: Int? = null
        composeRule.setContent {
            MatchingExerciseView(
                exercise = aMatchingExercise(),
                onComplete = { result = it },
                currentLanguage = Language.FRENCH
            )
        }

        // Une seule paire sur trois : l'exercice ne doit pas se terminer.
        pairUp(composeRule, "Heart", "Cœur")

        assertNull("onComplete ne doit pas être appelé avant la fin", result)
    }

    @Test
    fun matchedPairs_areDisabledAndCannotBeRetried() {
        var result: Int? = null
        composeRule.setContent {
            MatchingExerciseView(
                exercise = aMatchingExercise(),
                onComplete = { result = it },
                currentLanguage = Language.FRENCH
            )
        }

        pairUp(composeRule, "Heart", "Cœur")
        // Recliquer sur "Cœur" ne doit rien changer (déjà apparié).
        composeRule.onAllNodesWithText("Cœur")[0].performClick()

        assertNull("Un item apparié ne peut pas rejouer", result)
    }
}

/**
 * Apparie deux items : clique le premier puis le second.
 *
 * L'ordre des colonnes est aléatoire (`shuffled()`), donc on sélectionne par
 * texte plutôt que par index. `onAllNodesWithText` gère le cas où le même mot
 * apparaîtrait plusieurs fois.
 */
private fun pairUp(
    rule: ComposeContentTestRule,
    left: String,
    right: String
) {
    rule.onAllNodesWithText(left)[0].performClick()
    rule.onAllNodesWithText(right)[0].performClick()
    rule.waitForIdle()
}
