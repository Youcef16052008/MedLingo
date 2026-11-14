package com.aistudio.medlingua.dzmed.domain.pilot

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests du moteur de MESURE — miroir des fixtures de
 * `web-react/src/pilot/__tests__/measure.test.ts` (mêmes garanties : aucun
 * verdict affichable, dénominateurs explicites, absence ≠ 0, invalidation
 * tracée, délai réel T1→T2, garde T1/T2).
 */
class PilotMeasureEngineTest {

    companion object {
        private val NOW = 1_700_000_000_000L
        private val LATER = NOW + 2_000
        private const val CV = "0.1.0"

        private fun item(id: String, dim: String = "recognize") = MeasureItem(
            id = id, conceptId = "a", version = "A", dimension = dim, kind = "mcq",
            options = listOf("head", "neck"), answer = "head", source = "defEn"
        )

        private val manifest = MeasureManifest(
            schemaVersion = 1, id = "m", contentVersion = CV,
            versions = listOf("A", "B", "C"),
            banks = listOf(
                MeasureBank("a", "A", listOf(item("aA1"), item("aA2", "recall"))),
                MeasureBank("b", "A", listOf(item("bA1"))),
                MeasureBank("a", "B", listOf(item("aB1"))),
                MeasureBank("a", "C", listOf(item("aC1")))
            )
        )
    }

    private fun initSession(): MeasureSessionState =
        initMeasureSession("T0", listOf("a", "b"), CV, NOW)

    /* ---------- sélection d'items ---------- */

    @Test
    fun `measureItemsFor sert la version du temps demande et l'ordre des concepts`() {
        val items = measureItemsFor(manifest, listOf("a", "b"), "T0")
        assertEquals(listOf("aA1", "aA2", "bA1"), items.map { it.id })
    }

    @Test
    fun `temps inconnu = aucun item (echec silencieux gere a l'ecran)`() {
        assertTrue(measureItemsFor(manifest, listOf("a"), "T9").isEmpty())
    }

    /* ---------- enregistrement : AUCUN verdict ---------- */

    @Test
    fun `recordMeasureAnswer ne retourne AUCUN verdict (pas de feedback correctif)`() {
        val s = recordMeasureAnswer(initSession(), item("aA1"), "head", LATER, NOW)
        // Le seul observable est l'état ; record n'émet rien à afficher.
        assertEquals("aA1", s.answeredItemId)
        assertEquals(1, s.results.size)
    }

    @Test
    fun `reponse correcte, fausse et vide`() {
        val items = listOf(item("aA1"))
        var s = recordMeasureAnswer(initSession(), items[0], "head", LATER, NOW)
        assertEquals(true, s.results.single().correct)
        s = recordMeasureAnswer(initSession(), items[0], "neck", LATER, NOW)
        assertEquals(false, s.results.single().correct)
        s = recordMeasureAnswer(initSession(), items[0], "", LATER, NOW)
        assertEquals(false, s.results.single().correct)
    }

    @Test
    fun `idempotent - double soumission ne double pas le résultat ni la duree`() {
        val s0 = initSession()
        val s1 = recordMeasureAnswer(s0, item("aA1"), "head", LATER, NOW)
        val s2 = recordMeasureAnswer(s1, item("aA1"), "neck", LATER + 1, NOW)
        assertEquals(s1, s2) // le premier essai EST le résultat (idempotence)
    }

    @Test
    fun `duree observee en ms, jamais negative`() {
        val s = recordMeasureAnswer(initSession(), item("aA1"), "head", LATER, NOW)
        assertEquals(2_000L, s.results.single().durationMs)
        val badClock = recordMeasureAnswer(initSession(), item("aA1"), "head", NOW - 100, NOW)
        assertNull(badClock.results.single().durationMs)
    }

    /* ---------- invalidation tracée ---------- */

    @Test
    fun `item invalide = exclu du denominateur ET trace avec raison`() {
        var s = recordMeasureAnswer(initSession(), item("aA1"), "head", LATER, NOW)
        s = invalidateMeasureItem(s, "aA1", "prompt ambigu (distracteur identique à la réponse)", LATER + 10)
        val score = measureScore(s)
        assertFalse(score.hasScore)
        assertEquals(0, score.presented)
        assertEquals(1, score.invalidated)
        assertEquals("prompt ambigu (distracteur identique à la réponse)", s.results.single().invalidatedReason)
        assertNull(s.results.single().correct)
    }

    @Test
    fun `invalidation sans raison = refusee (trace obligatoire)`() {
        val s = initSession()
        assertEquals(s, invalidateMeasureItem(s, "aA1", "  ", LATER))
    }

    /* ---------- score : dénominateurs explicites, absence ≠ 0 ---------- */

    @Test
    fun `score par dimension avec denominateur correct`() {
        var s = recordMeasureAnswer(initSession(), item("aA1"), "head", LATER, NOW) // recognize ✓
        s = recordMeasureAnswer(s, item("aA2", "recall"), "neck", LATER + 100, NOW) // recall ✗
        s = advanceMeasureSession(s, measureItemsFor(manifest, listOf("a", "b"), "T0"), LATER + 200)
        val score = measureScore(s)
        assertEquals(2, score.presented)
        assertEquals(1, score.correct)
        assertTrue(score.hasScore)
        val rec = score.dimensions.first { it.dimension == "recognize" }
        val recall = score.dimensions.first { it.dimension == "recall" }
        assertEquals(1 to 1, rec.presented to rec.correct)
        assertEquals(1 to 0, recall.presented to recall.correct)
    }

    @Test
    fun `presente non traite = unanswered, jamais compte faux 0`() {
        var s = recordMeasureAnswer(initSession(), item("aA1"), "head", LATER, NOW)
        s = s.copy(results = s.results + MeasureItemResult(itemId = "bA1", conceptId = "b", dimension = "recognize", correct = null))
        val score = measureScore(s)
        assertEquals(1, score.presented)
        assertEquals(1, score.correct)
        assertEquals(1, score.unanswered)
    }

    @Test
    fun `aucun item traite = hasScore false (pas de score nul affiche)`() {
        val score = measureScore(initSession())
        assertFalse(score.hasScore)
        assertEquals(0, score.presented)
    }

    /* ---------- interruption ---------- */

    @Test
    fun `interruption = etat consigne sans transformer en score`() {
        val s = interruptMeasureSession(initSession(), LATER)
        assertTrue(s.interrupted)
        assertFalse(s.finished)
        assertFalse(measureScore(s).hasScore)
        // Séance déjà finie : interruption est un no-op.
        val f = initSession().copy(finished = true)
        assertEquals(f, interruptMeasureSession(f, LATER))
    }

    /* ---------- avancement ---------- */

    @Test
    fun `advance exige une reponse puis progresse jusqu'a finished`() {
        val items = measureItemsFor(manifest, listOf("a", "b"), "T0")
        val fresh = initSession()
        assertEquals(fresh, advanceMeasureSession(fresh, items, LATER)) // no-op
        var s = recordMeasureAnswer(fresh, items[0], "head", LATER, NOW)
        s = advanceMeasureSession(s, items, LATER)
        assertEquals(1, s.i)
        // dernière réponse → finished
        for (k in 1 until items.size) {
            s = recordMeasureAnswer(s, items[k], "head", LATER, NOW)
            s = advanceMeasureSession(s, items, LATER)
        }
        assertTrue(s.finished)
    }

    /* ---------- délais et gardes de temps ---------- */

    @Test
    fun `T1 bloque sans T0 fini, T2 bloque sans T1 fini`() {
        assertEquals(null, measureTimeBlocked("T0", emptyList()))
        assertEquals("measureNeedT0", measureTimeBlocked("T1", emptyList()))
        assertEquals("measureNeedT1", measureTimeBlocked("T2", emptyList()))
        val t0 = initMeasureSession("T0", listOf("a"), CV, NOW).copy(finished = true)
        assertEquals(null, measureTimeBlocked("T1", listOf(t0)))
        assertEquals("measureNeedT1", measureTimeBlocked("T2", listOf(t0)))
        val t1 = initMeasureSession("T1", listOf("a"), CV, NOW, previousFinishedAt = t0.updatedAt).copy(finished = true)
        assertEquals(null, measureTimeBlocked("T2", listOf(t0, t1)))
    }

    @Test
    fun `interruption ne compte pas comme un temps fini (garde honnete)`() {
        val t0Interrupted = initMeasureSession("T0", listOf("a"), CV, NOW).copy(interrupted = true)
        assertEquals("measureNeedT0", measureTimeBlocked("T1", listOf(t0Interrupted)))
    }

    @Test
    fun `delaie reel T1-T2 enregistre seulement si fini et dans le passe`() {
        val t0 = initMeasureSession("T0", listOf("a"), CV, NOW).copy(finished = true, updatedAt = NOW)
        val t1Start = NOW + 86_400_000L * 7 // ~7 jours plus tard (réel)
        val t1 = initMeasureSession("T1", listOf("a"), CV, t1Start, previousFinishedAt = t0.updatedAt)
            .copy(finished = true, updatedAt = t1Start)
        assertEquals(t1Start, finishedMeasureAt(listOf(t0, t1), "T1"))
        val t2 = initMeasureSession("T2", listOf("a"), CV, t1Start + 86_400_000L, previousFinishedAt = t1.updatedAt)
        assertEquals(86_400_000L, t2.actualDelayFromT1Ms)
        // T0 et T1 n'ont pas de délai.
        assertNull(initMeasureSession("T0", listOf("a"), CV, NOW).actualDelayFromT1Ms)
        // finishedAt d'un temps absent = null.
        assertNull(finishedMeasureAt(listOf(t0), "T1"))
    }

    /* ---------- reprise ---------- */

    @Test
    fun `resume refuse - autre contentVersion, version temps incoherente, fini`() {
        val s = recordMeasureAnswer(initSession(), item("aA1"), "head", LATER, NOW)
        assertNull(resumeMeasureSession(s.copy(contentVersion = "0.2.0"), manifest, LATER))
        assertNull(resumeMeasureSession(s.copy(version = "B"), manifest, LATER))
        assertNull(resumeMeasureSession(s.copy(finished = true), manifest, LATER))
        assertNull(resumeMeasureSession(s.copy(schemaVersion = 99), manifest, LATER))
        assertNull(resumeMeasureSession(null, manifest, LATER))
        assertEquals(LATER, resumeMeasureSession(s, manifest, LATER)!!.updatedAt)
    }
}
