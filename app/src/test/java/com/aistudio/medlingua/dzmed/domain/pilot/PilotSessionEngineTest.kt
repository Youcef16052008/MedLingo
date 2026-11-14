package com.aistudio.medlingua.dzmed.domain.pilot

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests du moteur de séance pilote — miroir des fixtures de
 * `web-react/src/pilot/__tests__/session.test.ts` (mêmes scénarios :
 * idempotence, sémantique de l'aide, progression, reprise).
 *
 * Aucune donnée réelle n'est approuvée ici : les fixtures sont des copies
 * du concept réel avec statuts forcés EN MÉMOIRE (verrou exercé séparément
 * dans [PilotModelsTest]).
 */
class PilotSessionEngineTest {

    companion object {
        private val NOW = 1_700_000_000_000L
        private val LATER = NOW + 5_000

        private val concept = PilotConcept(
            id = "a", unit = 1, order = 1, labelEn = "Head",
            definition = PilotLocalizedText(en = "Upper part of the body"),
            contentVersion = "0.1.0",
            exercises = listOf(
                PilotExercise(id = "a1", conceptId = "a", kind = "mcq", options = listOf("head", "neck"), answer = "head"),
                PilotExercise(id = "a2", conceptId = "a", kind = "free_input", answer = "cranium", acceptedVariants = listOf("the cranium")),
                PilotExercise(id = "a3", conceptId = "a", kind = "mcq", options = listOf("brain", "heart"), answer = "brain")
            )
        )
        private val conceptB = concept.copy(id = "b", order = 2, exercises = concept.exercises.map {
            it.copy(id = it.id.replace("a", "b"), conceptId = "b")
        })
        private val manifest = PilotManifest(
            schemaVersion = 1, id = "t", contentVersion = "0.1.0",
            concepts = listOf(concept, conceptB)
        )
    }

    private fun init(): PilotSessionState =
        initPilotSession(listOf(concept, conceptB), manifest.contentVersion, NOW)

    /* ---------- init ---------- */

    @Test
    fun `init limite la seance a 3 concepts et demarre non finie`() {
        val s = init()
        assertEquals(listOf("a", "b"), s.conceptIds)
        assertFalse(s.finished)
        assertEquals(NOW, s.startedAt)
    }

    @Test
    fun `init sans concept = seance finie d'emblee`() {
        val s = initPilotSession(emptyList(), manifest.contentVersion, NOW)
        assertTrue(s.finished)
    }

    /* ---------- evaluation (egalite stricte, miroir fillScore === 1) ---------- */

    @Test
    fun `reponse exacte, variante acceptee et tolerance de forme`() {
        val e = concept.exercises[1]
        assertTrue(evaluatePilotAnswer(e, "cranium"))
        assertTrue(evaluatePilotAnswer(e, "  The  Cranium.  ")) // casse/espaces/ponctuation
        assertFalse(evaluatePilotAnswer(e, "")) // réponse vide = jamais correcte
        assertFalse(evaluatePilotAnswer(e, "craniu")) // near-miss = erreur, pas de Levenshtein
    }

    /* ---------- enregistrement : idempotent, premier verdict figé ---------- */

    @Test
    fun `double soumission = meme etat (idempotence)`() {
        val s = init()
        val after1 = recordPilotAnswer(s, concept.exercises[0], "head", LATER)
        val after2 = recordPilotAnswer(after1, concept.exercises[0], "head", LATER)
        assertEquals(after1, after2)
    }

    @Test
    fun `bonne reponse sans aide = first_try et autonome`() {
        val s = recordPilotAnswer(init(), concept.exercises[0], "head", LATER)
        assertEquals("first_try", s.firstResults["a1"])
        assertTrue(s.autonomous.contains("a1"))
        assertFalse(s.helped.contains("a1"))
        assertFalse(s.errors.contains("a1"))
        assertEquals(1, s.attempts["a1"])
    }

    @Test
    fun `aide consultee puis bonne reponse = helped JAMAIS autonome`() {
        var s = usePilotHelp(init(), "a1", LATER)
        s = recordPilotAnswer(s, concept.exercises[0], "head", LATER)
        assertEquals("helped", s.firstResults["a1"])
        assertFalse(s.autonomous.contains("a1"))
        assertTrue(s.helped.contains("a1"))
    }

    @Test
    fun `mauvaise reponse = error et erreur tracee`() {
        val s = recordPilotAnswer(init(), concept.exercises[0], "neck", LATER)
        assertEquals("error", s.firstResults["a1"])
        assertTrue(s.errors.contains("a1"))
        assertFalse(s.autonomous.contains("a1"))
    }

    @Test
    fun `l'aide APRES la reponse ne change pas le verdict (apostrophe anti-triche)`() {
        val s = recordPilotAnswer(init(), concept.exercises[0], "head", LATER)
        val afterHelp = usePilotHelp(s, "a1", LATER)
        assertEquals("first_try", afterHelp.firstResults["a1"])
        // L'aide post-réponse n'affecte pas la réussite déjà consommée.
        assertTrue(afterHelp.autonomous.contains("a1"))
    }

    /* ---------- progression ---------- */

    @Test
    fun `advance sans reponse enregistree = no-op`() {
        val s = init()
        assertEquals(s, advancePilotSession(s, manifest, LATER))
    }

    @Test
    fun `avance dans le concept puis vers le concept suivant puis finit`() {
        var s = init()
        // a1 → a2 → a3 → b1 → b2 → b3 → fini
        for (i in 0 until 6) {
            val ex = conceptOf(s, manifest, s.i).exercises[s.exIdx]
            s = recordPilotAnswer(s, ex, "head", LATER)
            s = advancePilotSession(s, manifest, LATER)
        }
        assertTrue(s.finished)
        assertTrue(s.answeredExerciseId == null)
    }

    @Test
    fun `totalSteps = 6 pour deux concepts a 3 exercices`() {
        assertEquals(6, pilotTotalSteps(init(), manifest))
    }

    /* ---------- reprise ---------- */

    @Test
    fun `resume accepte une seance compatible et la marque updated`() {
        var s = init()
        s = recordPilotAnswer(s, concept.exercises[0], "head", LATER)
        val resumed = resumePilotSession(s, manifest, LATER + 100)
        assertTrue(resumed != null)
        assertEquals(LATER + 100, resumed!!.updatedAt)
    }

    @Test
    fun `resume refuse - autre contentVersion, seance finie, concepts inconnus, mauvais schema`() {
        val s = init().let { recordPilotAnswer(it, concept.exercises[0], "head", LATER) }
        assertNull(resumePilotSession(s.copy(contentVersion = "0.2.0"), manifest, LATER))
        assertNull(resumePilotSession(s.copy(finished = true), manifest, LATER))
        assertNull(resumePilotSession(s.copy(conceptIds = listOf("inconnu")), manifest, LATER))
        assertNull(resumePilotSession(s.copy(schemaVersion = 99), manifest, LATER))
        assertNull(resumePilotSession(null, manifest, LATER))
    }

    private fun conceptOf(s: PilotSessionState, m: PilotManifest, i: Int): PilotConcept =
        m.concepts.first { it.id == s.conceptIds[i] }
}
