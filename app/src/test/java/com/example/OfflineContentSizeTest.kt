package com.example

import com.example.data.initial.InitialData
import com.example.data.initial.OfflineContentSize
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The offline module sizes used to be hardcoded literals (14.2 MB, 7.2 MB, …) unrelated
 * to the content actually shipped, while every module is inserted as downloaded at first
 * launch. These tests pin the replacement: the figure is derived from the real seed
 * content, so it moves when content moves and nobody can reintroduce a magic number.
 */
class OfflineContentSizeTest {

    @Test
    fun `an empty module still has a positive footprint`() {
        assertTrue(
            OfflineContentSize.estimate(termsCount = 0, exercisesCount = 0) > 0.0
        )
    }

    @Test
    fun `the estimate grows with the content`() {
        val empty = OfflineContentSize.estimate(0, 0)
        val withTerms = OfflineContentSize.estimate(100, 0)
        val withExercises = OfflineContentSize.estimate(100, 10)
        assertTrue("terms must add weight: $empty -> $withTerms", withTerms > empty)
        assertTrue("exercises must add weight: $withTerms -> $withExercises", withExercises > withTerms)
    }

    @Test
    fun `the estimate is stable (same input, same output)`() {
        assertEquals(
            OfflineContentSize.estimate(412, 9),
            OfflineContentSize.estimate(412, 9),
            0.0
        )
    }

    @Test
    fun `negative counts cannot produce a negative size`() {
        assertTrue(OfflineContentSize.estimate(-5, -5) > 0.0)
    }

    @Test
    fun `every module size is derived from its own seed content`() {
        InitialData.modulesList.forEach { module ->
            assertEquals(
                "hardcoded size for module ${module.id}",
                OfflineContentSize.forModule(module.titleFr),
                module.estimatedSizeMb,
                0.0
            )
        }
    }

    @Test
    fun `modules with different content get different sizes`() {
        // The size is a pure function of the content: modules that ship the same number
        // of terms and exercises legitimately share a size, different content must not.
        val contents = InitialData.modulesList.map { module ->
            val content = InitialData.termsOfModule(module.titleFr).size to
                InitialData.exercises.count {
                    it.module.equals(module.titleFr, ignoreCase = true)
                }
            Triple(content, module.estimatedSizeMb, module.titleFr)
        }
        val collisions = mutableListOf<String>()
        for (i in contents.indices) {
            for (j in i + 1 until contents.size) {
                val (contentA, sizeA, titleA) = contents[i]
                val (contentB, sizeB, titleB) = contents[j]
                if (contentA != contentB && sizeA == sizeB) {
                    collisions += "$titleA ($contentA) and $titleB ($contentB) both report $sizeA MB"
                }
            }
        }
        assertTrue("distinct contents collapsed to the same size: $collisions", collisions.isEmpty())
    }

    @Test
    fun `the whole local content stays a believable order of magnitude`() {
        val totalMb = InitialData.modulesList.sumOf { it.estimatedSizeMb }
        // 4512 terms + 44 exercises: a few MB, not the ~200 MB the old literals implied
        assertTrue("implausible total: $totalMb MB", totalMb in 0.5..20.0)
    }
}
