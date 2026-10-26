package com.aistudio.medlingua.dzmed.domain.story

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Sélection de la séance du jour — miroir de `selection test ts` (web).
 * Mêmes priorités : reprise > expressions échues > jamais vue > plus ancienne.
 */
class StorySelectionTest {

    private val NOW = 1_700_000_000_000L
    private val DAY = 86_400_000L

    private val pilot: Story = Story(storyId = "a", contentVersion = 1, targetPhrases =
        listOf(
            TargetPhrase("en:p1:v1"), TargetPhrase("en:p2:v1"), TargetPhrase("en:p3:v1")
        )
    )
    private val second: Story = pilot.copy(storyId = "b")

    private fun due(id: String) = id to PhraseSched(rep = 1, ef = 2.5, iv = 1, due = NOW - 1)

    @Test
    fun `rien d'entame premiere histoire du fichier`() {
        assertEquals("a", pickTodayStory(listOf(pilot, second), emptyMap(), emptyMap(), NOW)?.storyId)
    }

    @Test
    fun `seance interrompue prime sur tout`() {
        val exprSm2 = mapOf(due("en:p1:v1"))
        val progress = mapOf("b" to StoryPickProgress(completedAt = 0L, savedAt = NOW))
        val picked = pickTodayStory(listOf(pilot, second), exprSm2, progress, NOW)
        assertEquals("b", picked?.storyId)
    }

    @Test
    fun `expressions echues la mieux due ex aequo la premiere`() {
        val exprSm2 = mapOf(due("en:p1:v1"), due("en:p2:v1"))
        val picked = pickTodayStory(listOf(pilot, second), exprSm2, emptyMap(), NOW)
        assertEquals("a", picked?.storyId)
    }

    @Test
    fun `tout termine rotation par la plus anciennement terminee`() {
        val progress = mapOf(
            "a" to StoryPickProgress(completedAt = NOW - 10 * DAY, savedAt = NOW),
            "b" to StoryPickProgress(completedAt = NOW - DAY, savedAt = NOW)
        )
        assertEquals("a", pickTodayStory(listOf(pilot, second), emptyMap(), progress, NOW)?.storyId)
    }

    @Test
    fun `corpus vide - null pas de crash`() {
        assertNull(pickTodayStory(emptyList(), emptyMap(), emptyMap(), NOW))
    }

    @Test
    fun `une expression non echue ne compte pas comme due`() {
        val future = "en:p1:v1" to PhraseSched(rep = 1, ef = 2.5, iv = 5, due = NOW + 5 * DAY)
        // Rien de dû, rien d'entamé → première du fichier (pas la sélection « due »).
        assertEquals("a", pickTodayStory(listOf(pilot, second), mapOf(future), emptyMap(), NOW)?.storyId)
    }
}
