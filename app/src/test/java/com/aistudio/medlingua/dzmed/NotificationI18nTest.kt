package com.aistudio.medlingua.dzmed

import com.aistudio.medlingua.dzmed.localization.Language
import com.aistudio.medlingua.dzmed.localization.Strings
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Phase 3 #11 — i18n des 4 notifications.
 *
 * Avant, tous les titres/corps étaient des chaînes FR en dur dans
 * `NotificationHelper` : un utilisateur en arabe ou en anglais recevait sa
 * notification en français, alors que toute l'UI suit sa langue. Ce test prouve
 * que chaque clé notification existe dans les 3 langues — sans retour sur la
 * clé brute, et sans retour silencieux sur le français pour l'anglais.
 */
class NotificationI18nTest {

    private val languages = listOf(Language.ARABIC, Language.FRENCH, Language.ENGLISH)

    // 12 clés notifications (4 notifs × titre/corps/big) + 2 actions
    private val notifKeys = listOf(
        "notif_review_title", "notif_review_body", "notif_review_big", "notif_review_action",
        "notif_streak_title", "notif_streak_body", "notif_streak_big",
        "notif_pearl_title", "notif_pearl_big", "notif_pearl_action",
        "notif_level_title", "notif_level_body", "notif_level_big"
    )

    // 8 clés canaux (4 canaux × nom/description)
    private val channelKeys = listOf(
        "channel_reviews_name", "channel_reviews_desc",
        "channel_streaks_name", "channel_streaks_desc",
        "channel_pearls_name", "channel_pearls_desc",
        "channel_levels_name", "channel_levels_desc"
    )

    @Test
    fun `toutes les cles notification existent dans les 3 langues`() {
        for (key in notifKeys) {
            for (lang in languages) {
                val value = Strings.get(key, lang)
                assertTrue(
                    "clé '$key' manquante en ${lang.code} (retourne la clé brute)",
                    value != key
                )
                assertTrue(
                    "clé '$key' vide en ${lang.code}",
                    value.isNotBlank()
                )
            }
        }
    }

    @Test
    fun `toutes les cles canal existent dans les 3 langues`() {
        for (key in channelKeys) {
            for (lang in languages) {
                val value = Strings.get(key, lang)
                assertTrue(
                    "clé canal '$key' manquante en ${lang.code}",
                    value != key && value.isNotBlank()
                )
            }
        }
    }

    @Test
    fun `l arabe et l anglais ne retombent pas sur le francais`() {
        // Le repli FR est légitime pour une clé inconnue, mais pour une clé
        // notification i18n c'est un bug : la notification serait en français
        // pour un utilisateur arabe — exactement le défaut corrigé.
        for (key in notifKeys + channelKeys) {
            val ar = Strings.get(key, Language.ARABIC)
            val en = Strings.get(key, Language.ENGLISH)
            val fr = Strings.get(key, Language.FRENCH)
            assertNotEquals(
                "'$key' : l'arabe est identique au français — repli silencieux",
                fr, ar
            )
            assertNotEquals(
                "'$key' : l'anglais est identique au français — repli silencieux",
                fr, en
            )
        }
    }

    @Test
    fun `les cles a parametres contiennent bien leur placeholder`() {
        // {n} est remplacé à l'envoi : s'il manque, le nombre (dueCount, série,
        // niveau) n'apparaîtrait pas dans la notification.
        for (lang in languages) {
            assertTrue(
                "notif_review_title doit contenir {n} en ${lang.code}",
                "{n}" in Strings.get("notif_review_title", lang)
            )
            assertTrue(
                "notif_streak_title doit contenir {n} en ${lang.code}",
                "{n}" in Strings.get("notif_streak_title", lang)
            )
            assertTrue(
                "notif_level_title doit contenir {n} en ${lang.code}",
                "{n}" in Strings.get("notif_level_title", lang)
            )
        }
        // La perle a des placeholders nommés
        for (lang in languages) {
            val big = Strings.get("notif_pearl_big", lang)
            assertTrue("'pearl' manquant en ${lang.code}", "{pearl}" in big)
            assertTrue("'mnemo' manquant en ${lang.code}", "{mnemo}" in big)
            assertTrue("'module' manquant en ${lang.code}", "{module}" in big)
        }
    }

    @Test
    fun `le remplacement des parametres produit un texte final sans accolades`() {
        // C'est ce que NotificationHelper fait à l'envoi — on le reproduit ici
        // pour vérifier qu'aucun placeholder ne survit.
        val title = Strings.get("notif_review_title", Language.FRENCH)
            .replace("{n}", "12")
        assertEquals("📋 12 flashcards prêtes pour révision !", title)
        assertFalse("{n} survit dans '$title'", "{n}" in title)

        // Niveau
        val levelTitle = Strings.get("notif_level_title", Language.ENGLISH)
            .replace("{n}", "3")
        assertEquals("🏆 Level 3 unlocked!", levelTitle)

        // Perle : 4 paramètres nommés
        val pearlBig = Strings.get("notif_pearl_big", Language.FRENCH)
            .replace("{pearl}", "P")
            .replace("{mnemo}", "M")
            .replace("{module}", "Mo")
            .replace("{chapter}", "C")
        assertFalse("placeholder survivant dans '$pearlBig'", Regex("\\{\\w+}").containsMatchIn(pearlBig))
    }
}
