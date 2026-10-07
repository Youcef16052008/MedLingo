package com.example

import com.example.data.initial.InitialData
import com.example.domain.exercise.ExerciseLanguage
import com.example.domain.exercise.ExerciseSpec
import com.example.domain.exercise.SameExamSwappedLanguage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Bug 17 (display-language swap) tested through the REAL production path.
 *
 * The tests in ExerciseSpecFixesTest rebuilt a spec by hand, so they never exercised
 * `ExerciseEntity.toExerciseSpec()` — the derivation the app actually runs before any
 * swap. These tests walk the whole seed through that path, which makes them
 * discriminating: a regression in toExerciseSpec() (specJson parsing, legacy rebuild,
 * wordbank order) or in the swap rule fails here.
 */
class CanonicalExerciseSpecTest {

    private val specs: List<ExerciseSpec> = InitialData.exercises.mapNotNull { it.toExerciseSpec() }

    @Test
    fun `every seed exercise yields a spec through toExerciseSpec`() {
        assertEquals(
            "some seed exercises produce no spec",
            InitialData.exercises.size,
            specs.size
        )
        assertTrue("seed exercises are missing", specs.isNotEmpty())
    }

    @Test
    fun `the seed exercises the four legacy types it declares`() {
        // Seed types: mcq (29) -> Choice, sentence_order (9) -> Wordbank,
        // matching (4) -> Match, fill_blank (2) -> Fill.
        // clinical_case and reading have no seed data yet, so they are covered by the
        // dedicated constructed-spec tests below instead of by the seed.
        val types = specs.map { it::class }.toSet()
        listOf(
            ExerciseSpec.Choice::class,
            ExerciseSpec.Fill::class,
            ExerciseSpec.Wordbank::class,
            ExerciseSpec.Match::class
        ).forEach { type ->
            assertTrue("no $type in the seed", type in types)
        }
    }

    @Test
    fun `clinical case without seed data still swaps both slots and round trips`() {
        val canonical = ExerciseSpec.ClinicalCase(
            vignetteEn = "A 62 year old man presents with chest pain",
            vignetteFr = "Un homme de 62 ans se presente avec une douleur thoracique",
            vignetteAr = "ذكر يبلغ 62 岁 omnib外在 chest pain",
            questionEn = "What is the most likely diagnosis?",
            questionFr = "Quel est le diagnostic le plus probable ?",
            questionAr = "ما هو التشخيص المرجح ؟",
            options = listOf("STEMI", "Pneumonia", "GERD", "Pneumothorax"),
            correctIndex = 0,
            explanationEn = "ST elevation with chest pain = STEMI",
            explanationFr = "Sus elevation ST avec douleur = STEMI",
            explanationAr = "ارتفاع ST مع ألم صدر = STEMI",
            clinicalPearl = "Time is muscle",
            mnemonic = "STEMI = ST Elevation MI"
        )
        val toFr = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.FRENCH)
                as ExerciseSpec.ClinicalCase
        assertEquals(canonical.vignetteFr, toFr.vignetteEn)
        assertEquals(canonical.questionFr, toFr.questionEn)
        assertEquals("canonical translations survive", canonical.vignetteAr, toFr.vignetteAr)
        assertEquals("answer payload survives", canonical.correctIndex, toFr.correctIndex)
        assertEquals(canonical.clinicalPearl, toFr.clinicalPearl)
        assertEquals(
            "round trip must restore the English slots",
            canonical,
            SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.ENGLISH)
        )
    }

    @Test
    fun `reading without seed data still swaps both slots and round trips`() {
        val canonical = ExerciseSpec.Reading(
            passageEn = "Hypertension is defined as a systolic pressure above 140 mmHg",
            passageFr = "L'hypertension se definit par une pression systolique superieure a 140 mmHg",
            passageAr = "يعرف ارتفاع الضغط定义为 ضغط انقباضي فوق 140",
            questionEn = "What is the threshold mentioned?",
            questionFr = "Quel est le seuil mentionne ?",
            questionAr = "ما هو الحد المذكور؟",
            options = listOf("120", "140", "160", "180"),
            correctIndex = 1,
            explanationEn = "140 mmHg systolic",
            explanationFr = "140 mmHg systolique",
            explanationAr = "140 ملم زرق",
        )
        val toAr = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.ARABIC)
                as ExerciseSpec.Reading
        assertEquals(canonical.passageAr, toAr.passageEn)
        assertEquals(canonical.questionAr, toAr.questionEn)
        assertEquals(canonical.passageFr, toAr.passageFr)
        assertEquals("answer payload survives", canonical.correctIndex, toAr.correctIndex)
        assertEquals(
            "round trip must restore the English slots",
            canonical,
            SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.ENGLISH)
        )
    }

    @Test
    fun `round trip FR then EN restores the English display slots for every seed exercise`() {
        val broken = mutableListOf<String>()
        specs.forEach { canonical ->
            SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.FRENCH)
            val backToEn = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.ENGLISH)
            if (backToEn != canonical) broken += canonical::class.simpleName ?: "?"
        }
        assertTrue(
            "EN/FR/EN round trip is not stable for ${broken.size} spec(s): ${broken.distinct()}",
            broken.isEmpty()
        )
    }

    @Test
    fun `round trip AR then EN restores the English display slots for every seed exercise`() {
        val broken = mutableListOf<String>()
        specs.forEach { canonical ->
            val toAr = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.ARABIC)
            // the canonical FR/AR translations must survive a swap
            if (toAr.canonicalTranslations() != canonical.canonicalTranslations()) {
                broken += "${canonical::class.simpleName}(translations)"
            }
            val backToEn = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.ENGLISH)
            if (backToEn != canonical) broken += canonical::class.simpleName ?: "?"
        }
        assertTrue("AR round trip is not stable: ${broken.distinct()}", broken.isEmpty())
    }

    @Test
    fun `swapping from an already swapped spec destroys the prompt (documented hazard)`() {
        // This is why callers must re-derive from the entity instead of chaining swaps.
        val translatable = specs.filter { it.displayText().en != it.displayText().fr }
        assertTrue("no spec with a distinct French translation in the seed", translatable.isNotEmpty())
        translatable.forEach { canonical ->
            val toFr = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.FRENCH)
            val chained = SameExamSwappedLanguage.swapLanguage(toFr, ExerciseLanguage.ENGLISH)
            assertNotEquals(
                "the hazard no longer exists for ${canonical::class.simpleName}: swapLanguage " +
                    "would now be lossless and the re-derivation rule could be relaxed",
                canonical.displayText().en,
                chained.displayText().en
            )
            val rederived = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.ENGLISH)
            assertEquals(
                "re-deriving from the canonical spec must restore the English text",
                canonical.displayText(),
                rederived.displayText()
            )
        }
    }

    @Test
    fun `generateTrilingualVersions derives the three languages from the same source`() {
        specs.forEach { canonical ->
            val versions = SameExamSwappedLanguage.generateTrilingualVersions(canonical)
            assertEquals("missing a language version", 3, versions.size)
            assertEquals(
                setOf(ExerciseLanguage.ENGLISH, ExerciseLanguage.FRENCH, ExerciseLanguage.ARABIC),
                versions.keys
            )
            val texts = canonical.displayText()
            assertEquals(
                "EN version must show the English text",
                texts.en,
                versions.getValue(ExerciseLanguage.ENGLISH).displayText().en
            )
            assertEquals(
                "FR version must show the French text",
                texts.fr,
                versions.getValue(ExerciseLanguage.FRENCH).displayText().en
            )
            assertEquals(
                "AR version must show the Arabic text",
                texts.ar,
                versions.getValue(ExerciseLanguage.ARABIC).displayText().en
            )
            // every version keeps the canonical FR/AR fields and the answer payload
            versions.values.forEach { version ->
                assertEquals(
                    "canonical translations must survive the swap",
                    canonical.canonicalTranslations(),
                    version.canonicalTranslations()
                )
                assertEquals("answer payload must survive", canonical.answerPayload(), version.answerPayload())
            }
        }
    }

    @Test
    fun `match specs swap their prompt and keep their pairs`() {
        val matches = specs.filterIsInstance<ExerciseSpec.Match>()
        matches.forEach { canonical ->
            val swapped = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.FRENCH)
            val asMatch = swapped as ExerciseSpec.Match
            assertEquals(canonical.promptFr, asMatch.promptEn)
            assertEquals(canonical.pairs, asMatch.pairs)
        }
    }

    @Test
    fun `clinical case swaps both the vignette and the question`() {
        val cases = specs.filterIsInstance<ExerciseSpec.ClinicalCase>()
        cases.forEach { canonical ->
            val toFr = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.FRENCH)
            val asCase = toFr as ExerciseSpec.ClinicalCase
            assertEquals("vignette not swapped", canonical.vignetteFr, asCase.vignetteEn)
            assertEquals("question not swapped", canonical.questionFr, asCase.questionEn)
            val toAr = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.ARABIC)
                    as ExerciseSpec.ClinicalCase
            assertEquals(canonical.vignetteAr, toAr.vignetteEn)
            assertEquals(canonical.questionAr, toAr.questionEn)
            assertEquals(canonical.correctIndex, toAr.correctIndex)
            assertEquals(canonical.options, toAr.options)
        }
    }

    @Test
    fun `reading swaps both the passage and the question`() {
        val readings = specs.filterIsInstance<ExerciseSpec.Reading>()
        readings.forEach { canonical ->
            val swapped = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.ARABIC)
            val asReading = swapped as ExerciseSpec.Reading
            assertEquals("passage not swapped", canonical.passageAr, asReading.passageEn)
            assertEquals("question not swapped", canonical.questionAr, asReading.questionEn)
            assertEquals(canonical.options, asReading.options)
            assertEquals(canonical.correctIndex, asReading.correctIndex)
        }
    }

    @Test
    fun `every seed spec carries a non blank text in all three languages`() {
        val empty = mutableListOf<String>()
        specs.forEach { spec ->
            val texts = spec.displayText()
            if (texts.en.isBlank()) empty += "en/${spec::class.simpleName}"
            if (texts.fr.isBlank()) empty += "fr/${spec::class.simpleName}"
            if (texts.ar.isBlank()) empty += "ar/${spec::class.simpleName}"
        }
        assertTrue("blank texts: ${empty.distinct()}", empty.isEmpty())
    }

    @Test
    fun `choice specs keep their options and correct answer across a swap`() {
        val choices = specs.filterIsInstance<ExerciseSpec.Choice>()
        assertTrue("no choice exercise in the seed", choices.isNotEmpty())
        choices.forEach { canonical ->
            val swapped = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.ARABIC)
            val asChoice = swapped as ExerciseSpec.Choice
            assertEquals(canonical.options, asChoice.options)
            assertEquals(canonical.correctIndex, asChoice.correctIndex)
            assertEquals(canonical.explanationEn, asChoice.explanationEn)
        }
    }

    @Test
    fun `wordbank specs keep their bank and order across a swap`() {
        val wordbanks = specs.filterIsInstance<ExerciseSpec.Wordbank>()
        wordbanks.forEach { canonical ->
            val swapped = SameExamSwappedLanguage.swapLanguage(canonical, ExerciseLanguage.FRENCH)
            val asWordbank = swapped as ExerciseSpec.Wordbank
            assertEquals(canonical.bank, asWordbank.bank)
            assertEquals(canonical.correctOrder, asWordbank.correctOrder)
            assertEquals(canonical.correctSentence, asWordbank.correctSentence)
        }
    }

    // ---------- helpers ----------

    /** The text actually shown to the user, per language. */
    private data class DisplayText(val en: String, val fr: String, val ar: String)

    private fun ExerciseSpec.displayText(): DisplayText = when (this) {
        is ExerciseSpec.Choice -> DisplayText(promptEn, promptFr, promptAr)
        is ExerciseSpec.Fill -> DisplayText(promptEn, promptFr, promptAr)
        is ExerciseSpec.Wordbank -> DisplayText(promptEn, promptFr, promptAr)
        is ExerciseSpec.Match -> DisplayText(promptEn, promptFr, promptAr)
        is ExerciseSpec.ClinicalCase ->
            DisplayText("$vignetteEn\n$questionEn", "$vignetteFr\n$questionFr", "$vignetteAr\n$questionAr")
        is ExerciseSpec.Reading ->
            DisplayText("$passageEn\n$questionEn", "$passageFr\n$questionFr", "$passageAr\n$questionAr")
    }

    /** The canonical FR/AR fields, which a swap must never touch. */
    private fun ExerciseSpec.canonicalTranslations(): List<String> = when (this) {
        is ExerciseSpec.Choice -> listOf(promptFr, promptAr, explanationFr, explanationAr)
        is ExerciseSpec.Fill -> listOf(promptFr, promptAr, explanationFr, explanationAr)
        is ExerciseSpec.Wordbank -> listOf(promptFr, promptAr, correctSentence, explanationFr, explanationAr)
        is ExerciseSpec.Match -> listOf(promptFr, promptAr, explanationFr, explanationAr)
        is ExerciseSpec.ClinicalCase -> listOf(
            vignetteFr, vignetteAr, questionFr, questionAr,
            explanationFr, explanationAr, clinicalPearl, mnemonic
        )
        is ExerciseSpec.Reading -> listOf(
            passageFr, passageAr, questionFr, questionAr, explanationFr, explanationAr
        )
    }

    /** The answer payload, which a swap must never touch. */
    private fun ExerciseSpec.answerPayload(): List<Any> = when (this) {
        is ExerciseSpec.Choice -> listOf(options, correctIndex)
        is ExerciseSpec.Fill -> listOf(acceptedAnswers)
        is ExerciseSpec.Wordbank -> listOf(bank, correctOrder, correctSentence)
        is ExerciseSpec.Match -> listOf(pairs)
        is ExerciseSpec.ClinicalCase -> listOf(options, correctIndex)
        is ExerciseSpec.Reading -> listOf(options, correctIndex)
    }
}