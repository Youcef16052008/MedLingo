package com.aistudio.medlingua.dzmed

import com.aistudio.medlingua.dzmed.domain.writing.WritingCorrector
import com.aistudio.medlingua.dzmed.domain.writing.WritingCorrector.IssueKind
import com.aistudio.medlingua.dzmed.domain.writing.WritingCorrector.Verdict
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Correcteur de rappel écrit / oral.
 *
 * Le contrat produit : l'élève écrit (ou dicte) le terme **anglais** avant de
 * retourner la carte ; le correcteur distingue l'exact, l'accent/casse, le
 * « presque » (quelques caractères) et le faux, et propose une note SM-2.
 */
class WritingCorrectorTest {

    private fun hasIssue(result: WritingCorrector.Result, kind: IssueKind): Boolean =
        result.issues.any { it.kind == kind }

    @Test
    fun `mot exact`() {
        val result = WritingCorrector.check("heart", "heart", "cœur")
        assertEquals(Verdict.EXACT, result.verdict)
        assertEquals(100, result.score)
        assertEquals(5, result.quality)
        assertTrue(result.isAccepted)
        assertTrue(result.issues.isEmpty())
    }

    @Test
    fun `casse differente acceptee avec diagnostic`() {
        val result = WritingCorrector.check("Heart", "heart")
        assertEquals(Verdict.ACCENT, result.verdict)
        assertEquals(95, result.score)
        assertTrue(result.isAccepted)
        assertTrue(hasIssue(result, IssueKind.CASE))
    }

    @Test
    fun `accent oublie accepte avec diagnostic`() {
        val result = WritingCorrector.check("Sezary", "Sézary")
        assertEquals(Verdict.ACCENT, result.verdict)
        assertTrue(result.isAccepted)
        assertTrue(hasIssue(result, IssueKind.ACCENT))
    }

    @Test
    fun `une lettre manquante donne presque`() {
        val result = WritingCorrector.check("hepatits", "hepatitis")
        assertEquals(Verdict.NEAR, result.verdict)
        assertTrue(result.score in 55..94)
        assertTrue(result.quality in 3..4)
        assertFalse(result.isAccepted)
        assertTrue(result.issues.isNotEmpty())
    }

    @Test
    fun `pluriel en trop signale`() {
        val result = WritingCorrector.check("cells", "cell")
        assertEquals(Verdict.NEAR, result.verdict)
        assertTrue(hasIssue(result, IssueKind.PLURAL))
    }

    @Test
    fun `article oublie reste presque et non faux`() {
        val result = WritingCorrector.check("heart", "the heart")
        assertEquals(Verdict.NEAR, result.verdict)
        assertTrue(result.score >= 90)
        assertTrue(hasIssue(result, IssueKind.ARTICLE))
        // Le diagnostic ne compte pas « the » comme 3 lettres manquantes.
        assertFalse(hasIssue(result, IssueKind.MISSING_LETTER))
    }

    @Test
    fun `ordre des mots signale`() {
        val result = WritingCorrector.check("pressure blood", "blood pressure")
        assertEquals(Verdict.NEAR, result.verdict)
        assertTrue(hasIssue(result, IssueKind.WORD_ORDER))
    }

    @Test
    fun `mot francais au lieu de l anglais`() {
        val result = WritingCorrector.check("cœur", "heart", "cœur")
        assertEquals(Verdict.WRONG, result.verdict)
        assertEquals(0, result.score)
        assertEquals(1, result.quality)
        assertTrue(hasIssue(result, IssueKind.WRONG_LANGUAGE))
    }

    @Test
    fun `mot sans rapport juge faux avec le sens en cause`() {
        val result = WritingCorrector.check("pneumonia", "hepatitis")
        assertEquals(Verdict.WRONG, result.verdict)
        assertTrue(result.score < 55)
        assertTrue(hasIssue(result, IssueKind.MEANING))
    }

    @Test
    fun `reponse vide`() {
        val result = WritingCorrector.check("   ", "heart")
        assertEquals(Verdict.EMPTY, result.verdict)
        assertEquals(0, result.score)
        assertEquals(1, result.quality)
        assertTrue(hasIssue(result, IssueKind.EMPTY))
    }

    @Test
    fun `un synonyme accepte passe en exact`() {
        val result = WritingCorrector.check(
            answer = "myocardium",
            expected = "cardiac muscle",
            accepted = listOf("myocardium")
        )
        assertEquals(Verdict.EXACT, result.verdict)
        assertEquals(100, result.score)
    }

    @Test
    fun `normalisation sans accents ni ponctuation`() {
        assertEquals("hepatite aigue", WritingCorrector.normalize("  Hépatite, aiguë ! "))
        assertEquals("oedeme", WritingCorrector.normalize("Œdème"))
    }

    @Test
    fun `le diff montre les caracteres en trop ou manquants`() {
        val parts = WritingCorrector.diff("hepatits", "hepatitis")
        assertTrue(parts.isNotEmpty())
        assertTrue(parts.any { it.kind == WritingCorrector.DiffPart.Kind.SAME })
        assertTrue(
            parts.any {
                it.kind == WritingCorrector.DiffPart.Kind.EXTRA ||
                    it.kind == WritingCorrector.DiffPart.Kind.MISSING
            }
        )
    }

    @Test
    fun `distance d edition`() {
        assertEquals(0, WritingCorrector.levenshtein("heart", "heart"))
        assertEquals(1, WritingCorrector.levenshtein("heart", "hearts"))
        assertEquals(2, WritingCorrector.levenshtein("heart", "herat"))
    }
}
