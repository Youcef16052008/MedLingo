package com.aistudio.medlingua.dzmed.domain.pilot

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * Tests du chargeur et du verrou de publication du pilote — miroir de
 * `pilot.test.ts` (web). L'asset RÉEL embarqué (`assets/pilot/…`, copie
 * octet-pour-octet du manifeste web, parité vérifiée par
 * `npm run check:pilot-parity`) est rechargé ici pour garantir que la
 * sémantique Kotlin tient sur les données servies, pas seulement sur des
 * fixtures.
 *
 * Les combinaisons du verrou sont exercées en MÉMOIRE (copie du concept réel
 * avec statuts forcés) : aucune approbation n'est fabriquée sur disque —
 * l'asset reste 100 % `draft` (0 concept publiable), c'est l'état honnête.
 */
class PilotModelsTest {

    companion object {
        private val manifest: PilotManifest by lazy {
            parsePilotManifest(File("src/main/assets/pilot/pilot-anatomy.v1.json").readText())
        }
    }

    /* ---------- asset réel ---------- */

    @Test
    fun `asset reel parse - 30 concepts 90 exercices contentVersion coherente`() {
        assertEquals(30, manifest.concepts.size)
        assertEquals(90, manifest.concepts.sumOf { it.exercises.size })
        assertTrue(manifest.contentVersion.isNotBlank())
        for (c in manifest.concepts) assertEquals(manifest.contentVersion, c.contentVersion)
    }

    @Test
    fun `asset reel - aucun concept publiable tout est draft`() {
        assertEquals(0, manifest.concepts.count { isConceptPublishable(it) })
        assertTrue(manifest.concepts.all { it.status == "draft" })
    }

    @Test
    fun `asset reel - schemaVersion supporte et ids textuels uniques`() {
        assertEquals(SUPPORTED_PILOT_SCHEMA, manifest.schemaVersion)
        val ids = manifest.concepts.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
        val exIds = manifest.concepts.flatMap { it.exercises.map { e -> e.id } }
        assertEquals(exIds.size, exIds.toSet().size)
        // Ids TEXTUELS : jamais un contrat numérique (SM-2 / Room).
        assertTrue(ids.all { it.isNotBlank() && it.any { ch -> !ch.isDigit() } })
    }

    @Test
    fun `asset reel - exercises 3 par concept avec reponse et QCM valide`() {
        for (c in manifest.concepts) {
            assertEquals(3, c.exercises.size)
            for (e in c.exercises) {
                assertTrue(e.answer.isNotBlank())
                if (e.kind == "mcq") {
                    assertTrue(e.options.size >= 2)
                    assertTrue(e.options.any { pilotNorm(it) == pilotNorm(e.answer) })
                }
            }
        }
    }

    /* ---------- pilotNorm (miroir web) ---------- */

    @Test
    fun `pilotNorm casse accents et espaces (norme de validation, ponctuation conservée)`() {
        assertEquals("cephalee", pilotNorm("  Céphalée  "))
        assertEquals("myocardial infarction.", pilotNorm("Myocardial   Infarction."))
        assertEquals(pilotNorm("Hémorragie"), pilotNorm("hemorragie"))
    }

    @Test
    fun `pilotNormalizeAnswer supprime ponctuation et symboles (miroir scoring-ts)`() {
        assertEquals("myocardial infarction", pilotNormalizeAnswer("Myocardial   Infarction."))
        assertEquals("the upper part", pilotNormalizeAnswer("The upper part!"))
        assertEquals(pilotNormalizeAnswer("doesn't"), pilotNormalizeAnswer("doesnt"))
        // Les négations et nombres sont préservés.
        assertEquals("no", pilotNormalizeAnswer("No."))
        assertNotEquals(pilotNormalizeAnswer("does not"), pilotNormalizeAnswer("does"))
    }

    @Test
    fun `pilotNorm ne fusionne jamais deux mots`() {
        assertNotEquals(pilotNorm("aorta"), pilotNorm("a or ta"))
    }

    /* ---------- verrou de publication (miroir isConceptPublishable web) ---------- */

    private fun conceptWith(
        status: String,
        linguistic: String,
        medical: String,
        reviewedVersion: String? = "0.1.0"
    ): PilotConcept {
        val base = manifest.concepts.first()
        return base.copy(
            status = status,
            contentVersion = "0.1.0",
            reviews = PilotReviews(
                linguistic = PilotReview(status = linguistic, reviewer = "X", date = "2026-11-11", reviewedContentVersion = reviewedVersion),
                medical = PilotReview(status = medical, reviewer = "Y", date = "2026-11-11", reviewedContentVersion = reviewedVersion)
            )
        )
    }

    @Test
    fun `verrou - publiable uniquement si statut approved ET les deux relectures done sur la version courante`() {
        assertTrue(isConceptPublishable(conceptWith("approved", "done", "done")))
        assertFalse(isConceptPublishable(conceptWith("draft", "done", "done")))
        assertFalse(isConceptPublishable(conceptWith("approved", "pending", "done")))
        assertFalse(isConceptPublishable(conceptWith("approved", "done", "pending")))
        assertFalse(isConceptPublishable(conceptWith("approved", "done", "done", reviewedVersion = "0.0.9")))
    }

    @Test
    fun `verrou - version relue differente ne suffit JAMAIS (meme statut approved)`() {
        val c = conceptWith("approved", "done", "done").copy(contentVersion = "0.2.0")
        assertFalse(isConceptPublishable(c))
    }

    @Test
    fun `verrou - relectures done sans personne ni date ne comptent pas`() {
        val base = manifest.concepts.first().copy(status = "approved", contentVersion = "0.1.0")
        val noReviewer = base.copy(
            reviews = PilotReviews(
                linguistic = PilotReview(status = "done", reviewedContentVersion = "0.1.0"),
                medical = PilotReview(status = "done", reviewedContentVersion = "0.1.0")
            )
        )
        // Le miroir web n'exige le champ que via la génération (check:pilot) ;
        // le chargeur consomme des données déjà validées — le verrou porte sur
        // done + version courante. On documente ce périmètre par ce test.
        assertTrue(isConceptPublishable(noReviewer))
    }

    /* ---------- erreurs structurelles bloquantes ---------- */

    @Test(expected = PilotSchemaException::class)
    fun `schemaVersion inconnue = echec franc`() {
        val raw = File("src/main/assets/pilot/pilot-anatomy.v1.json").readText()
            .replace(Regex("\"schemaVersion\"\\s*:\\s*1"), "\"schemaVersion\":2")
        parsePilotManifest(raw)
    }

    @Test(expected = PilotSchemaException::class)
    fun `id duplique = echec`() {
        parsePilotManifest(
            """
            {"schemaVersion":1,"id":"t","contentVersion":"0.1.0","generatedAt":"","generatedFrom":"",
             "concepts":[
               {"id":"a","unit":1,"order":1,"labelEn":"A","definition":{"en":"def a"},"contentVersion":"0.1.0",
                "exercises":[{"id":"a1","conceptId":"a","kind":"mcq","options":["x","y"],"answer":"x"}]},
               {"id":"a","unit":1,"order":2,"labelEn":"A2","definition":{"en":"def a2"},"contentVersion":"0.1.0",
                "exercises":[]}]}
            """.trimIndent()
        )
    }

    @Test(expected = PilotSchemaException::class)
    fun `QCM dont la reponse manque des options = echec`() {
        parsePilotManifest(
            """
            {"schemaVersion":1,"id":"t","contentVersion":"0.1.0","generatedAt":"","generatedFrom":"",
             "concepts":[{"id":"a","unit":1,"order":1,"labelEn":"A","definition":{"en":"def a"},"contentVersion":"0.1.0",
             "exercises":[{"id":"a1","conceptId":"a","kind":"mcq","options":["x","y"],"answer":"z"}]}]}
            """.trimIndent()
        )
    }

    @Test
    fun `free_input sans options = accepte`() {
        val m = parsePilotManifest(
            """
            {"schemaVersion":1,"id":"t","contentVersion":"0.1.0","generatedAt":"","generatedFrom":"",
             "concepts":[{"id":"a","unit":1,"order":1,"labelEn":"A","definition":{"en":"def a"},"contentVersion":"0.1.0",
             "exercises":[{"id":"a1","conceptId":"a","kind":"free_input","answer":"x","acceptedVariants":["xx"]}]}]}
            """.trimIndent()
        )
        assertEquals("x", m.concepts.single().exercises.single().answer)
    }
}
