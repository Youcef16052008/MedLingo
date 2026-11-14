package com.aistudio.medlingua.dzmed.domain.pilot

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * Tests du manifeste de MESURE sur l'asset RÉEL embarqué (copie octet-pour-
 * octet du manifeste web). Garanties testées : recoupement de
 * `contentVersion` avec l'entraînement (échec franc sinon), versions A/B/C
 * complètes, ids de mesure DISJOINTS des ids d'entraînement (aucun item de
 * mesure ne peut être interprété comme un exercice, et réciproquement), pas
 * de feedback correctif embarqué, couverture recognize+recall par banque.
 */
class PilotMeasureModelsTest {

    companion object {
        private val training: PilotManifest by lazy {
            parsePilotManifest(File("src/main/assets/pilot/pilot-anatomy.v1.json").readText())
        }
        private val measure: MeasureManifest by lazy {
            parseMeasureManifest(
                File("src/main/assets/pilot/pilot-anatomy-measure.v1.json").readText(),
                training.contentVersion
            )
        }
    }

    @Test
    fun `asset reel parse - 90 banques 180 items versions completes`() {
        assertEquals(90, measure.banks.size)
        assertEquals(180, measure.banks.sumOf { it.items.size })
        for (v in EXPECTED_MEASURE_VERSIONS.split(",")) assertTrue(measure.versions.contains(v))
        for (b in measure.banks) {
            assertEquals(2, b.items.size) // recognize + recall (P3.1)
            assertTrue(TIME_TO_VERSION.containsValue(b.version))
        }
    }

    @Test
    fun `mapping fixe T0-A T1-B T2-C partage par les deux moteurs`() {
        assertEquals("A", TIME_TO_VERSION["T0"])
        assertEquals("B", TIME_TO_VERSION["T1"])
        assertEquals("C", TIME_TO_VERSION["T2"])
        // Chaque banque a exactement 2 items reconnus (recognize + recall).
        for (b in measure.banks) {
            val dims = b.items.map { it.dimension }.toSet()
            assertEquals(setOf("recognize", "recall"), dims)
        }
    }

    @Test
    fun `ids de mesure DISJOINTS des ids d'entrainement`() {
        val trainingIds = training.concepts.flatMap { it.exercises.map { e -> e.id } }.toSet()
        val measureIds = measure.banks.flatMap { b -> b.items.map { it.id } }.toSet()
        assertTrue(trainingIds.intersect(measureIds).isEmpty())
        assertEquals(180, measureIds.size)
    }

    @Test
    fun `aucun champ de feedback correctif dans les items de mesure (modele)`() {
        // Le modèle Kotlin ne porte PAS de champ `explanation` : toute clé
        // inconnue est ignorée, la mesure ne peut jamais afficher de correction.
        val fields = MeasureItem::class.java.declaredFields.map { it.name }.toSet()
        assertTrue("explanation" !in fields && "feedback" !in fields && "explanationFr" !in fields)
    }

    @Test
    fun `chaque item a une source tracee et une reponse`() {
        for (b in measure.banks) for (it in b.items) {
            assertTrue("source absente: ${it.id}", it.source.isNotBlank())
            assertTrue("reponse absente: ${it.id}", it.answer.isNotBlank())
        }
    }

    @Test(expected = PilotSchemaException::class)
    fun `contentVersion mesure differente de l'entrainement = echec franc`() {
        parseMeasureManifest(
            File("src/main/assets/pilot/pilot-anatomy-measure.v1.json").readText(),
            "9.9.9-autre"
        )
    }

    @Test(expected = PilotSchemaException::class)
    fun `banque vide = echec`() {
        parseMeasureManifest(
            """
            {"schemaVersion":1,"id":"m","contentVersion":"0.1.0","generatedAt":"","generatedFrom":"",
             "versions":["A","B","C"],
             "banks":[{"conceptId":"a","version":"A","items":[]}]}
            """.trimIndent(),
            "0.1.0"
        )
    }

    @Test(expected = PilotSchemaException::class)
    fun `dimension inconnue = echec`() {
        parseMeasureManifest(
            """
            {"schemaVersion":1,"id":"m","contentVersion":"0.1.0","generatedAt":"","generatedFrom":"",
             "versions":["A","B","C"],
             "banks":[{"conceptId":"a","version":"A","items":[
               {"id":"i1","conceptId":"a","version":"A","dimension":"souvenir","kind":"mcq",
                "options":["x","y"],"answer":"x","source":"defEn"}]}]}
            """.trimIndent(),
            "0.1.0"
        )
    }

    @Test(expected = PilotSchemaException::class)
    fun `banque sans recognize = echec (couverture minimale P3-1)`() {
        parseMeasureManifest(
            """
            {"schemaVersion":1,"id":"m","contentVersion":"0.1.0","generatedAt":"","generatedFrom":"",
             "versions":["A","B","C"],
             "banks":[{"conceptId":"a","version":"A","items":[
               {"id":"i1","conceptId":"a","version":"A","dimension":"recall","kind":"free_input",
                "answer":"x","source":"defEn"}]}]}
            """.trimIndent(),
            "0.1.0"
        )
    }
}
