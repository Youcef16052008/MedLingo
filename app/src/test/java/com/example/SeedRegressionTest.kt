package com.example

import com.example.data.initial.InitialData
import com.example.data.local.entity.MedicalTermEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Non-régression du seed (Phase 4).
 *
 * Le seed est la donnée fondamentale de l'app : 4 504 fiches trilingues réparties
 * sur 15 modules. Une modification du seed (un module renommé, des termes
 * supprimés, une clé de module qui change) casse silencieusement :
 *
 *  - le parcours (`modulesList` ne correspond plus aux termes réels),
 *  - le générateur de leçons (`termsOfModule` retourne une liste vide → nœud
 *    injouable, voir `LessonGeneratorRealDataTest`),
 *  - le contenu hors-ligne (`chaptersOfModule` / `OfflineContentSize`).
 *
 * Aucun test ne mesurait l'invariant "chaque module du parcours a des termes
 * **et** chaque terme appartient à un module du parcours". Ce test le fait.
 */
class SeedRegressionTest {

    private val terms: List<MedicalTermEntity> = InitialData.terms
    private val modules = InitialData.modulesList

    @Test
    fun `le seed contient exactement 4_504 termes`() {
        // Comptage de référence, mesuré le 10/10/2026. Tout écart est
        // volontaire (ajout/retrait de contenu) et doit être mis à jour ici.
        assertEquals(4504, terms.size)
    }

    @Test
    fun `le seed contient exactement 15 modules`() {
        assertEquals(15, modules.size)
    }

    @Test
    fun `aucun terme n'est duplique`() {
        // Un id dupliqué écraserait silencieusement une fiche en Room
        // (INSERT OR REPLACE) — on perdrait du contenu sans erreur.
        val ids = terms.map { it.id }
        assertEquals(
            "termes: ${terms.size}, ids distincts: ${ids.toSet().size}",
            terms.size,
            ids.toSet().size
        )
    }

    @Test
    fun `chaque module du parcours a des termes`() {
        // termsOfModule est la source de vérité de la jouabilité : un module
        // sans terme génère une leçon vide (nœud qui pulse vers un écran vide).
        modules.forEach { mod ->
            assertTrue(
                "le module ${mod.id} (${mod.titleFr}) n'a AUCUN terme — " +
                    "son nœud est injouable",
                InitialData.termsOfModule(mod.titleFr).isNotEmpty()
            )
        }
    }

    @Test
    fun `chaque terme appartient a un module du parcours`() {
        // L'inverse du précédent : un terme dont le `module` ne correspond à
        // aucun module de `modulesList` est invisible dans le parcours ET dans
        // le générateur. C'est la cause du bug P1 d'origine (termes orphelins).
        val validTitles = modules.map { it.titleFr }.toSet()
        val orphans = terms.filter { it.module !in validTitles }
        assertTrue(
            "${orphans.size} termes orphelins (module inconnu) : " +
                orphans.map { "${it.termEn} → ${it.module}" }.take(5),
            orphans.isEmpty()
        )
    }

    @Test
    fun `chaque terme a ses trois traductions et une definition`() {
        // Une fiche sans traduction affiche un trou vide en quiz.
        val incomplete = terms.filter {
            it.termEn.isBlank() || it.termFr.isBlank() || it.termAr.isBlank() ||
                it.definitionEn.isBlank()
        }
        assertTrue(
            "${incomplete.size} fiches incomplètes (sur ${terms.size}) : " +
                incomplete.take(3).map { it.termEn },
            incomplete.isEmpty()
        )
    }

    @Test
    fun `les titres de module sont uniques`() {
        // Un doublon de titre ferait que termsOfModule fusionne deux modules
        // et le parcours afficherait deux nœuds pour le même contenu.
        val titles = modules.map { it.titleFr }
        assertEquals(modules.size, titles.toSet().size)
    }

    @Test
    fun `les ids de module sont uniques`() {
        val ids = modules.map { it.id }
        assertEquals(modules.size, ids.toSet().size)
    }

    @Test
    fun `les chapitres affiches sont reellement presents dans le seed`() {
        // chaptersOfModule dérive les chapitres des termes réels. Si un module
        // affiche des chapitres qui n'existent pas, l'écran de module est vide.
        modules.forEach { mod ->
            val chapters = InitialData.chaptersOfModule(mod.titleFr)
            assertNotNull(chapters)
            // Pas de chapitre vide (un term avec chapter = "" créerait une
            // section sans titre dans ModulesScreen).
            chapters.forEach { ch ->
                assertTrue(
                    "chapitre vide dans le module ${mod.titleFr}",
                    ch.isNotBlank()
                )
            }
        }
    }

    @Test
    fun `les 15 modules couvrent la totalite des termes`() {
        // La somme des termes par module doit égaler le total : si un terme
        // n'appartient à aucun module connu, le total sera inférieur.
        val total = modules.sumOf { InitialData.termsOfModule(it.titleFr).size }
        assertEquals(
            "total termes = ${terms.size}, mais la somme par module = $total " +
                "→ des termes sont orphelins",
            terms.size,
            total
        )
    }
}
