package com.aistudio.medlingua.dzmed

import com.aistudio.medlingua.dzmed.localization.Language
import com.aistudio.medlingua.dzmed.localization.Strings
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Accords en nombre des libellés du Parcours.
 *
 * 34 chapitres du seed n'ont qu'**un seul** terme : sans accord, l'UI affichait
 * « 1 leçons + 0 révisions » ou « Leçon 1 · 1 termes ». Ce test prouve que
 * chaque paire singulier/pluriel existe dans les 3 langues et que
 * [Strings.count] choisit la bonne forme.
 */
class StringsCountTest {

    private val languages = listOf(Language.ARABIC, Language.FRENCH, Language.ENGLISH)

    /** (clé singulier, clé pluriel) */
    private val pairs = listOf(
        "lesson_one_lbl" to "lessons_lbl",
        "review_one_lbl" to "reviews_lbl",
        "term_one_lbl" to "terms_lbl",
        "chapter_one_lbl" to "chapters_lbl"
    )

    @Test
    fun `les paires singulier pluriel existent dans les 3 langues`() {
        for ((one, many) in pairs) {
            for (lang in languages) {
                for (key in listOf(one, many)) {
                    val value = Strings.get(key, lang)
                    assertTrue(
                        "clé '$key' manquante en ${lang.code} (retourne la clé brute)",
                        value != key
                    )
                    assertTrue("clé '$key' vide en ${lang.code}", value.isNotBlank())
                }
                assertNotEquals(
                    "'$one' et '$many' identiques en ${lang.code} : pas d'accord",
                    Strings.get(one, lang),
                    Strings.get(many, lang)
                )
            }
        }
    }

    @Test
    fun `count choisit le singulier pour 1 et le pluriel sinon`() {
        for ((one, many) in pairs) {
            for (lang in languages) {
                assertEquals(
                    "1 doit donner le singulier ($one, ${lang.code})",
                    Strings.get(one, lang),
                    Strings.count(1, one, many, lang)
                )
                for (n in listOf(0, 2, 3, 13, 212)) {
                    assertEquals(
                        "$n doit donner le pluriel ($many, ${lang.code})",
                        Strings.get(many, lang),
                        Strings.count(n, one, many, lang)
                    )
                }
            }
        }
    }

    @Test
    fun `un chapitre d'un seul terme s'affiche au singulier`() {
        // Cas réel du seed : 34 chapitres n'ont qu'un terme → 1 leçon, 0 révision.
        val fr = Language.FRENCH
        val text = "1 " + Strings.count(1, "lesson_one_lbl", "lessons_lbl", fr) +
            " + 0 " + Strings.count(0, "review_one_lbl", "reviews_lbl", fr)
        assertEquals("1 leçon + 0 révisions", text)
    }
}
