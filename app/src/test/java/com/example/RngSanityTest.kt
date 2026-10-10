package com.example

import com.example.domain.lesson.LessonRng
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RngSanityTest {

    @Test
    fun `hashSeed est deterministe`() {
        assertEquals(LessonRng.hashSeed("anat:3"), LessonRng.hashSeed("anat:3"))
    }

    @Test
    fun `mulberry32 produit des valeurs dans 0,1`() {
        val rand = LessonRng.mulberry32(LessonRng.hashSeed("anat:3"))
        repeat(500) {
            val v = rand()
            assertTrue("valeur hors [0,1) : $v", v >= 0.0 && v < 1.0)
        }
    }

    @Test
    fun `mulberry32 ne lance jamais IndexOutOfBounds sur des listes`() {
        // Cas qui plantait : `(rand() * size).toInt()` avec size jusqu'a 8
        val rand = LessonRng.mulberry32(LessonRng.hashSeed("physio:5"))
        repeat(2000) {
            val size = (rand() * 8).toInt() + 1
            val idx = (rand() * size).toInt()
            assertTrue(idx in 0 until size)
        }
    }
}
