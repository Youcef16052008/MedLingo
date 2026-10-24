package com.aistudio.medlingua.dzmed.domain.story

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * Tests du moteur de séance — miroir de `session.test.ts` (web).
 *
 * Les scénarios JSON sont PARTAGÉS avec le web (§6.2 du guide) : ce fichier et
 * `web-react/src/learning/__tests__/scenarios.test.ts` lisent les mêmes
 * `story-scenarios.json` + `stories.v1.json` — une divergence de comportement
 * entre les deux moteurs fait échouer au moins l'un des deux côtés.
 */
class StorySessionEngineTest {

    companion object {
        private val DAY_MS = 86_400_000L
        private val NOW = 1_700_000_000_000L

        private val story: Story = parseStoryFile(
            File("src/main/assets/learning/stories.v1.json").readText()
        ).first { it.storyId == "clinical-symptoms-001" }
    }

    /* ---------- normalisation ---------- */

    @Test
    fun `normalise casse ponctuation et apostrophe typographique`() {
        assertEquals("no i do not", normalizeAnswer("  No,   I do NOT. "))
        assertEquals("no he doesn't", normalizeAnswer("No, he doesn’t."))
    }

    @Test
    fun `ne supprime jamais une negation`() {
        assertFalse(normalizeAnswer("does not") == "does")
    }

    @Test
    fun `reponse courte acceptee et inversion de fait refusee`() {
        assertTrue(checkAccepted("No", listOf("no", "no he does not")))
        assertFalse(checkAccepted("Yes", listOf("no", "no he does not")))
        assertFalse(checkAccepted("", listOf("no")))
    }

    /* ---------- initialisation ---------- */

    @Test
    fun `demarre sur ecoute sans expressions dues, sur revision sinon`() {
        assertEquals("LISTEN_DISCOVER", initSession(story).step)
        val s = initSession(story, duePhraseIds = listOf("en:no-fever:v1", "en:inconnu:v9"))
        assertEquals("REVIEW", s.step)
        assertEquals(listOf("en:no-fever:v1"), s.reviewIds) // id hors histoire écarté
    }

    /* ---------- idempotence des stats (REGRESS-1) ---------- */

    @Test
    fun `question re-repondue apres reprise ne double pas les stats`() {
        var s = initSession(story)
        repeat(3) { s = sessionReducer(s, Action.Advance, story) }
        s = sessionReducer(s, Action.SubmitAnswer("no"), story) // q1 ratée
        assertTrue("q1" in s.stats.mistakeIds)
        val resumed = resumeSession(
            story,
            SavedSession(
                contentVersion = story.contentVersion, step = "CIRCLING", qIndex = 0,
                drillIndex = 0, reviewIndex = 0, stats = s.stats, reviewRatings = emptyMap(),
                retellAttempted = false, transferTaskAttempted = false, transferTaskPending = false
            ),
            due = emptyList()
        )
        val after = sessionReducer(resumed, Action.SubmitAnswer("yes"), story)
        assertEquals(true, after.lastCorrect)
        assertFalse("q1" in after.stats.firstTryIds)
        assertTrue("q1" in after.stats.mistakeIds)
    }

    @Test
    fun `reviewIndex recale si la liste dues a change`() {
        val s = resumeSession(
            story,
            SavedSession(
                contentVersion = story.contentVersion, step = "REVIEW", qIndex = 0,
                drillIndex = 0, reviewIndex = 5,
                stats = SessionStats(), reviewRatings = emptyMap(),
                retellAttempted = false, transferTaskAttempted = false, transferTaskPending = false
            ),
            due = listOf("en:no-fever:v1")
        )
        assertEquals("REVIEW", s.step)
        assertEquals(0, s.reviewIndex)
    }

    /* ---------- memoire d'expressions (RISK-001) ---------- */

    @Test
    fun `oubliee en revision puis reussie au circling reste a revue demain`() {
        var s = initSession(story, duePhraseIds = listOf("en:no-fever:v1"))
        s = sessionReducer(s, Action.ReviewRated("en:no-fever:v1", false), story)
        repeat(4) { s = sessionReducer(s, Action.Advance, story) } // REVIEW→CIRCLING q1→q2
        val out = scheduleFromSession(s, story, emptyMap(), NOW)
        val sched = out.getValue("en:no-fever:v1")
        assertEquals(0, sched.rep)
        assertEquals(1, sched.iv)
        assertEquals(NOW + DAY_MS, sched.due)
        assertEquals(2.18, sched.ef, 0.01) // quality 2 (EF 2.5 − 0.32), pas 5
    }

    @Test
    fun `retenue en revision sans difficulte garde EF stable`() {
        var s = initSession(story, duePhraseIds = listOf("en:no-fever:v1"))
        s = sessionReducer(s, Action.ReviewRated("en:no-fever:v1", true), story)
        val out = scheduleFromSession(s, story, emptyMap(), NOW)
        assertEquals(2.5, out.getValue("en:no-fever:v1").ef, 0.0001)
        assertEquals(2.6, out.getValue("en:have-headache:v1").ef, 0.0001)
    }

    @Test
    fun `expression jamais vue n est pas due`() {
        assertTrue(duePhrases(story, emptyMap(), NOW).isEmpty())
    }

    /* ---------- schema du fichier ---------- */

    @Test
    fun `version de schema inconnue rejetee franchement`() {
        val raw = File("src/main/assets/learning/stories.v1.json").readText()
        val v2 = raw.replace("\"schemaVersion\": 1", "\"schemaVersion\": 2")
        try {
            parseStoryFile(v2)
            throw AssertionError("rejet attendu")
        } catch (e: StorySchemaException) {
            assertTrue(e.message!!.contains("schemaVersion"))
        }
    }

    @Test
    fun `les drafts ne sont jamais servis`() {
        val raw = File("src/main/assets/learning/stories.v1.json").readText()
        val draft = raw.replace("\"publicationStatus\": \"published\"", "\"publicationStatus\": \"draft\"")
        assertTrue(parseStoryFile(draft).isEmpty())
    }

    /* ---------- scénarios partagés web/Kotlin ---------- */

    @Serializable
    private data class ScenarioAction(
        val type: String,
        val value: String? = null,
        val phraseId: String? = null,
        val remembered: Boolean? = null,
        val attempted: Boolean? = null
    )

    @Serializable
    private data class ScenarioExpect(
        val finished: Boolean,
        val step: String,
        val firstTry: Int,
        val helped: Int,
        val mistakes: Int,
        val difficultPhrases: List<String>
    )

    @Serializable
    private data class Scenario(
        val id: String,
        val duePhraseIds: List<String> = emptyList(),
        val actions: List<ScenarioAction>,
        val expect: ScenarioExpect
    )

    @Serializable
    private data class ScenarioFile(
        val schemaVersion: Int = 0,
        val scenarios: List<Scenario> = emptyList()
    )

    @Test
    fun `scenarios partages - memes entrees memes decisions que le web`() {
        val json = Json { ignoreUnknownKeys = true }
        val file = json.decodeFromString<ScenarioFile>(
            File("src/test/resources/learning/story-scenarios.json").readText()
        )
        assertEquals(1, file.schemaVersion)
        assertTrue(file.scenarios.isNotEmpty())

        for (scenario in file.scenarios) {
            var s = initSession(story, duePhraseIds = scenario.duePhraseIds)
            for (a in scenario.actions) {
                val action = when (a.type) {
                    "ADVANCE" -> Action.Advance
                    "SUBMIT_ANSWER" -> Action.SubmitAnswer(a.value ?: "")
                    "SUBMIT_DRILL" -> Action.SubmitDrill(a.value ?: "")
                    "REVEAL" -> Action.Reveal
                    "HELP" -> Action.Help
                    "REVIEW_RATED" -> Action.ReviewRated(a.phraseId ?: "", a.remembered ?: true)
                    "RETELL_DONE" -> Action.RetellDone(a.attempted ?: true)
                    "TRANSFER_TASK_DONE" -> Action.TransferTaskDone(a.attempted ?: true)
                    else -> throw AssertionError("action inconnue : ${a.type}")
                }
                s = sessionReducer(s, action, story)
            }
            val sum = sessionSummary(s, story)
            assertEquals("[${scenario.id}] finished", scenario.expect.finished, s.finished)
            assertEquals("[${scenario.id}] step", scenario.expect.step, s.step)
            assertEquals("[${scenario.id}] firstTry", scenario.expect.firstTry, sum.firstTry)
            assertEquals("[${scenario.id}] helped", scenario.expect.helped, sum.helped)
            assertEquals("[${scenario.id}] mistakes", scenario.expect.mistakes, sum.mistakes)
            assertEquals("[${scenario.id}] difficultPhrases", scenario.expect.difficultPhrases, sum.difficultPhrases)
        }
    }
}
