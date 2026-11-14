package com.aistudio.medlingua.dzmed.data.pilot

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.aistudio.medlingua.dzmed.domain.pilot.MeasureItemResult
import com.aistudio.medlingua.dzmed.domain.pilot.MeasureSessionState
import com.aistudio.medlingua.dzmed.domain.pilot.PilotConcept
import com.aistudio.medlingua.dzmed.domain.pilot.PilotLocalizedText
import com.aistudio.medlingua.dzmed.domain.pilot.PilotManifest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Tests de la persistance et de l'EXPORT de la mesure (Robolectric).
 *
 * Garanties testées : namespace séparé, séance sans item traité ≠ faux score
 * 0 (CSV à cellules vides), pseudonyme éditable, import refusé si
 * contentVersion différente, backup avant import, reset de mesure qui ne
 * touche JAMAIS à la progression d'entraînement.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class PilotMeasureStoreTest {

    companion object {
        private const val CV = "0.1.0"
        private val NOW = 1_700_000_000_000L
        private val LATER = NOW + 60_000

        private fun session(
            id: String,
            time: String,
            version: String,
            results: List<MeasureItemResult> = emptyList(),
            finished: Boolean = false,
            interrupted: Boolean = false,
            delay: Long? = null
        ) = MeasureSessionState(
            id = id, time = time, version = version, contentVersion = CV,
            conceptIds = listOf("a"), startedAt = NOW, updatedAt = LATER,
            results = results, finished = finished, interrupted = interrupted,
            actualDelayFromT1Ms = delay
        )
    }

    private lateinit var prefs: android.content.SharedPreferences
    private lateinit var backupPrefs: android.content.SharedPreferences
    private lateinit var trainingPrefs: android.content.SharedPreferences

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        prefs = context.getSharedPreferences(MEASURE_PREFS, Context.MODE_PRIVATE)
        backupPrefs = context.getSharedPreferences(MEASURE_PREFS_BACKUP, Context.MODE_PRIVATE)
        trainingPrefs = context.getSharedPreferences(PILOT_PREFS, Context.MODE_PRIVATE)
        prefs.edit().clear().commit()
        backupPrefs.edit().clear().commit()
        trainingPrefs.edit().clear().commit()
    }

    /* ---------- chargement / sauvegarde ---------- */

    @Test
    fun `charge vide = etat sain sans pseudonyme invente`() {
        val (state, warning) = loadMeasureState(prefs, NOW)
        assertNull(warning)
        assertEquals("", state.pseudonym)
        assertTrue(state.sessions.isEmpty())
    }

    @Test
    fun `JSON corrompu = avertissement explicite`() {
        prefs.edit().putString("state", "}corrompu{").commit()
        val (_, warning) = loadMeasureState(prefs, NOW)
        assertEquals("pilotDataCorrupted", warning)
    }

    @Test
    fun `round-trip preserve pseudonyme et seances (finies ET interrompues)`() {
        val s1 = session("m1", "T0", "A", finished = true)
        val s2 = session("m2", "T1", "B", interrupted = true)
        assertEquals(
            PilotSaveResult.Ok,
            saveMeasureState(prefs, MeasurePersistedState(pseudonym = "Salma", sessions = listOf(s1, s2)))
        )
        val (loaded, warning) = loadMeasureState(prefs, NOW)
        assertNull(warning)
        assertEquals("Salma", loaded.pseudonym)
        assertEquals(2, loaded.sessions.size)
        assertTrue(loaded.sessions.any { it.interrupted })
    }

    @Test
    fun `upsert remplace la meme seance par id sans dupliquer`() {
        var st = MeasurePersistedState(pseudonym = "X")
        st = upsertMeasureSession(st, session("m1", "T0", "A"))
        st = upsertMeasureSession(st, session("m1", "T0", "A", finished = true))
        assertEquals(1, st.sessions.size)
        assertTrue(st.sessions.single().finished)
    }

    /* ---------- export CSV : séance sans item ≠ faux 0 ---------- */

    @Test
    fun `CSV - seance finie sans item traite = ligne a cellules vides jamais un 0`() {
        val state = MeasurePersistedState(
            pseudonym = "Sana", updatedAt = NOW,
            sessions = listOf(session("m1", "T0", "A", finished = true))
        )
        val csv = exportMeasureCsv(state, CV)
        val lines = csv.trim().lines()
        assertEquals(2, lines.size)
        val row = lines[1].split(",")
        assertEquals(14, row.size)
        // colonnes item/concept/dimension/correct/raison/durée toutes VIDES.
        assertEquals(14, row.size)
        assertTrue(row.slice(8..13).all { it.isEmpty() })
        assertEquals("finished", row[6])
    }

    @Test
    fun `CSV - resultat brut honnete 1 zero unanswered invalid`() {
        val results = listOf(
            MeasureItemResult("i1", "a", "recognize", correct = true, durationMs = 1_000L),
            MeasureItemResult("i2", "a", "recall", correct = false, durationMs = 2_000L),
            MeasureItemResult("i3", "b", "recognize", correct = null),
            MeasureItemResult("i4", "b", "recall", correct = null, invalidatedReason = "prompt ambigu")
        )
        val state = MeasurePersistedState(
            pseudonym = "Sana",
            sessions = listOf(session("m1", "T0", "A", results = results, finished = true, delay = 86_400_000L))
        )
        val csv = exportMeasureCsv(state, CV)
        val rows = csv.trim().lines().drop(1).map { it.split(",") }
        assertEquals(4, rows.size)
        assertEquals("1", rows[0][11])
        assertEquals("0", rows[1][11])
        assertEquals("unanswered", rows[2][11])
        assertEquals("invalid", rows[3][11])
        assertEquals("prompt ambigu", rows[3][12])
        assertEquals("86400000", rows[0][7]) // délai réel T1→T2 exporté
    }

    /* ---------- export JSON ---------- */

    @Test
    fun `JSON - champ aidesUsedDuringMeasure explicite et faux (protocol P3-2)`() {
        val state = MeasurePersistedState(pseudonym = "S", sessions = listOf(session("m1", "T0", "A", finished = true)))
        val json = exportMeasureJson(state, CV)
        assertTrue(json.contains("\"aidsUsedDuringMeasure\":false"))
        assertTrue(json.contains("\"pseudonym\":\"S\""))
        assertTrue(json.contains("\"contentVersion\":\"$CV\""))
    }

    /* ---------- import ---------- */

    @Test
    fun `import round-trip accepte si meme contentVersion`() {
        val state = MeasurePersistedState(pseudonym = "Sana", updatedAt = NOW, sessions = listOf(session("m1", "T0", "A", finished = true)))
        val imported = importMeasureJson(exportMeasureJson(state, CV), CV, NOW)
        assertTrue(imported is MeasureImportResult.Ok)
        assertEquals("Sana", (imported as MeasureImportResult.Ok).state.pseudonym)
    }

    @Test
    fun `import d'une autre contentVersion = REFUSE (mesure non comparable)`() {
        val state = MeasurePersistedState(pseudonym = "S", sessions = listOf(session("m1", "T0", "A", finished = true)))
        val result = importMeasureJson(exportMeasureJson(state, "0.2.0"), CV, NOW)
        assertTrue(result is MeasureImportResult.Failed)
        // Le code d'erreur doit nommer la version, pas un générique.
        assertEquals("pilotImportVersion", (result as MeasureImportResult.Failed).error)
    }

    @Test
    fun `import confirme = backup prealable et restauration possible`() {
        val current = MeasurePersistedState(pseudonym = "Avant")
        saveMeasureState(prefs, current)
        val imported = MeasurePersistedState(pseudonym = "Apres")
        assertEquals(PilotSaveResult.Ok, applyMeasureImport(prefs, backupPrefs, current, imported))
        assertEquals("Apres", loadMeasureState(prefs, NOW).first.pseudonym)
        val restored = restoreMeasureBackup(prefs, backupPrefs, NOW)
        assertNull(restored.second)
        assertEquals("Avant", restored.first.pseudonym)
    }

    /* ---------- isolation symétrique ---------- */

    @Test
    fun `reset mesure n'efface JAMAIS la progression d'entrainement`() {
        saveMeasureState(prefs, MeasurePersistedState(pseudonym = "S"))
        savePilotState(trainingPrefs, PilotPersistedState(updatedAt = NOW, contentVersion = CV))
        assertEquals(PilotSaveResult.Ok, resetMeasureState(prefs, backupPrefs))
        assertTrue(loadMeasureState(prefs, NOW).first.sessions.isEmpty())
        // L'entraînement est intact.
        val (t, warning) = loadPilotState(
            trainingPrefs,
            PilotManifest(
                schemaVersion = 1, id = "t", contentVersion = CV,
                concepts = listOf(
                    PilotConcept(
                        id = "a", unit = 1, order = 1, labelEn = "Head",
                        definition = PilotLocalizedText(en = "d"),
                        contentVersion = CV
                    )
                )
            ),
            NOW
        )
        assertNull(warning)
        assertEquals(CV, t.contentVersion)
    }
}
