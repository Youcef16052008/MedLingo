package com.aistudio.medlingua.dzmed

import com.aistudio.medlingua.dzmed.domain.path.ChapterSyllabus
import com.aistudio.medlingua.dzmed.domain.path.ChapterSyllabus.Kind
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Découpage d'un chapitre en leçons (« parcours 3 termes », 2026-11-10).
 *
 * Le plan est la source de vérité partagée par le Parcours, le ViewModel et les
 * tests : 3 nouveaux termes par leçon, une révision après chaque paire, aucune
 * leçon vide, et tous les termes utilisés exactement une fois.
 */
class ChapterSyllabusTest {

    private fun terms(n: Int): List<Int> = (1..n).toList()

    @Test
    fun `30 termes donnent 10 lecons et 5 revisions`() {
        val plan = ChapterSyllabus.plan(terms(30))
        assertEquals(15, plan.size)
        assertEquals(10, plan.count { it.kind == Kind.NEW })
        assertEquals(5, plan.count { it.kind == Kind.REVIEW })
        assertTrue(plan.filter { it.kind == Kind.NEW }.all { it.termCount == 3 })
        assertTrue(plan.filter { it.kind == Kind.REVIEW }.all { it.termCount == 6 })
    }

    @Test
    fun `22 termes donnent 8 lecons dont une fin en 2 plus 2 et 4 revisions`() {
        // 7 leçons de 3 laisseraient un terme orphelin → 6×3 + 2 + 2.
        val plan = ChapterSyllabus.plan(terms(22))
        assertEquals(12, plan.size)
        val learning = plan.filter { it.kind == Kind.NEW }
        assertEquals(8, learning.size)
        assertEquals(listOf(3, 3, 3, 3, 3, 3, 2, 2), learning.map { it.termCount })
        assertEquals(4, plan.count { it.kind == Kind.REVIEW })
        // Aucune leçon de 1 terme : jamais de leçon vide ni orpheline.
        assertFalse(plan.any { it.termCount == 0 })
    }

    @Test
    fun `un chapitre d un seul terme tient en une lecon`() {
        val plan = ChapterSyllabus.plan(listOf(42))
        assertEquals(1, plan.size)
        assertEquals(Kind.NEW, plan[0].kind)
        assertEquals(listOf(42), plan[0].termIds)
        assertEquals(3, plan[0].questionCount)
        assertEquals(1, ChapterSyllabus.lessonCount(listOf(42)))
    }

    @Test
    fun `trois termes tiennent en une lecon sans revision`() {
        val plan = ChapterSyllabus.plan(terms(3))
        assertEquals(1, plan.size)
        assertEquals(Kind.NEW, plan[0].kind)
        assertEquals(8, plan[0].questionCount)
    }

    @Test
    fun `chaque terme est utilise exactement une fois en apprentissage`() {
        for (n in 1..40) {
            val plan = ChapterSyllabus.plan(terms(n))
            val learned = plan.filter { it.kind == Kind.NEW }.flatMap { it.termIds }
            assertEquals("n=$n", (1..n).toList(), learned)
        }
    }

    @Test
    fun `une revision couvre exactement les deux lecons precedentes`() {
        val plan = ChapterSyllabus.plan(terms(12))
        // NEW, NEW, REVIEW, NEW, NEW, REVIEW
        assertEquals(6, plan.size)
        val review1 = plan[2]
        val review2 = plan[5]
        assertEquals(Kind.REVIEW, review1.kind)
        assertEquals(listOf(1, 2, 3, 4, 5, 6), review1.termIds)
        assertEquals(listOf(1, 2), review1.reviewedLevels)
        assertEquals(listOf(7, 8, 9, 10, 11, 12), review2.termIds)
        assertEquals(listOf(4, 5), review2.reviewedLevels)
        assertEquals(16, review1.questionCount)
    }

    @Test
    fun `une revision suit chaque paire et le chapitre finit par une revision`() {
        for (n in 4..40) {
            val plan = ChapterSyllabus.plan(terms(n))
            if (plan.size == 1) continue
            assertEquals("n=$n", Kind.REVIEW, plan.last().kind)
            // Les niveaux sont 1..size, sans trou.
            assertEquals((1..plan.size).toList(), plan.map { it.level })
            // Aucune révision ne suit une révision, aucune ne revoit plus de 2 leçons.
            plan.forEachIndexed { index, lesson ->
                if (lesson.kind == Kind.REVIEW) {
                    assertTrue("n=$n idx=$index", index > 0)
                    assertTrue("n=$n idx=$index", lesson.reviewedLevels.size in 1..2)
                }
            }
        }
    }

    @Test
    fun `lessonAt retrouve une lecon par son niveau`() {
        val plan = ChapterSyllabus.plan(terms(9))
        assertEquals(plan[3], ChapterSyllabus.lessonAt(terms(9), 4))
        assertNull(ChapterSyllabus.lessonAt(terms(9), 99))
        assertNull(ChapterSyllabus.lessonAt(emptyList(), 1))
    }

    @Test
    fun `un chapitre vide n a aucune lecon`() {
        assertTrue(ChapterSyllabus.plan(emptyList()).isEmpty())
        assertEquals(0, ChapterSyllabus.lessonCount(emptyList()))
        assertTrue(ChapterSyllabus.splitSizes(0).isEmpty())
    }

    @Test
    fun `splitSizes ne produit jamais de lecon d un seul terme`() {
        for (n in 1..60) {
            val sizes = ChapterSyllabus.splitSizes(n)
            assertEquals("n=$n", n, sizes.sum())
            if (n == 1) assertEquals(listOf(1), sizes)
            if (n > 1) assertTrue("n=$n", sizes.all { it in 2..3 })
            // 4 ne doit pas devenir 3 + 1.
            if (n == 4) assertEquals(listOf(2, 2), sizes)
        }
    }

    @Test
    fun `le palier de difficulte reste borne a 1 et 6`() {
        for (count in 1..20) {
            for (level in 1..count) {
                val tier = ChapterSyllabus.tierFor(level, count)
                assertTrue("count=$count level=$level", tier in 1..6)
            }
            assertEquals(1, ChapterSyllabus.tierFor(1, count))
            if (count > 1) assertEquals(6, ChapterSyllabus.tierFor(count, count))
        }
        // Un chapitre d'une seule leçon ne dépasse pas le palier 1.
        assertEquals(1, ChapterSyllabus.tierFor(1, 1))
    }
}
