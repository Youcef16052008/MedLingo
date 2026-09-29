package com.example

import com.example.domain.sm2.SpacedRepetitionAlgorithm
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun testSpacedRepetitionSuccessiveReviews() {
    // 1st successful recall
    val review1 = SpacedRepetitionAlgorithm.calculateNext(
      quality = 4,
      previousRepetitions = 0,
      previousEaseFactor = 2.5,
      previousIntervalDays = 0
    )
    assertEquals(1, review1.repetitions)
    assertEquals(1, review1.intervalDays)

    // 2nd successful recall
    val review2 = SpacedRepetitionAlgorithm.calculateNext(
      quality = 5,
      previousRepetitions = review1.repetitions,
      previousEaseFactor = review1.easeFactor,
      previousIntervalDays = review1.intervalDays
    )
    assertEquals(2, review2.repetitions)
    assertEquals(3, review2.intervalDays)
    assertTrue(review2.easeFactor >= 2.5)

    // Failure / Forgotten recall
    val reviewFail = SpacedRepetitionAlgorithm.calculateNext(
      quality = 1,
      previousRepetitions = 2,
      previousEaseFactor = 2.6,
      previousIntervalDays = 3
    )
    assertEquals(0, reviewFail.repetitions)
    assertEquals(1, reviewFail.intervalDays)
  }

  @Test
  fun testAnatomyDatabaseCompleteness() {
    val allTerms = com.example.data.initial.AnatomyDatabase.getAllTerms()
    assertEquals(150, allTerms.size)

    val osteo = allTerms.filter { it.chapter == "Ostéologie" }
    val arthro = allTerms.filter { it.chapter == "Arthrologie" }
    val myo = allTerms.filter { it.chapter == "Myologie" }
    val neuro = allTerms.filter { it.chapter == "Neurologie" }

    assertEquals(45, osteo.size)
    assertEquals(30, arthro.size)
    assertEquals(40, myo.size)
    assertEquals(35, neuro.size)

    // Verify IDs and non-empty multilingual terms
    allTerms.forEach { term ->
      assertTrue(term.id in 1..150)
      assertTrue(term.termEn.isNotBlank())
      assertTrue(term.termFr.isNotBlank())
      assertTrue(term.termAr.isNotBlank())
      assertTrue(term.definitionEn.isNotBlank())
      assertTrue(term.definitionFr.isNotBlank())
      assertTrue(term.definitionAr.isNotBlank())
    }
  }

  @Test
  fun testSixLevelLearningProgressiveUnlockingRules() {
    // Fresh user stats with only level 1 passed
    val freshUser = com.example.data.local.entity.UserStatsEntity(
      level1Score = 60,
      level2Score = 0,
      level3Score = 0,
      level4Score = 0,
      level5Score = 0,
      level6Score = 0
    )
    assertTrue(freshUser.isLevelUnlocked(1))
    // Level 2 locked because level 1 score < 70%
    org.junit.Assert.assertFalse(freshUser.isLevelUnlocked(2))

    // User achieves 75% on Level 1
    val passedL1 = freshUser.copy(level1Score = 75)
    assertTrue(passedL1.isLevelUnlocked(1))
    assertTrue(passedL1.isLevelUnlocked(2))
    org.junit.Assert.assertFalse(passedL1.isLevelUnlocked(3))

    // Sequential unlocking: Level 2 passed >= 70% unlocks Level 3
    val passedL2 = passedL1.copy(level2Score = 80)
    assertTrue(passedL2.isLevelUnlocked(3))
    org.junit.Assert.assertFalse(passedL2.isLevelUnlocked(4))

    // Level 3 passed >= 70% unlocks Level 4
    val passedL3 = passedL2.copy(level3Score = 70)
    assertTrue(passedL3.isLevelUnlocked(4))
    org.junit.Assert.assertFalse(passedL3.isLevelUnlocked(5))

    // Level 4 passed >= 70% unlocks Level 5
    val passedL4 = passedL3.copy(level4Score = 90)
    assertTrue(passedL4.isLevelUnlocked(5))
    org.junit.Assert.assertFalse(passedL4.isLevelUnlocked(6))

    // Level 5 passed >= 70% unlocks Level 6 (Clinical Cases)
    val passedL5 = passedL4.copy(level5Score = 85)
    assertTrue(passedL5.isLevelUnlocked(6))
    assertEquals(6, passedL5.highestUnlockedLevel())
  }

  @Test
  fun testLearningExercisesDataCompleteness() {
    val exercises = com.example.data.initial.LearningExercisesData.exercises
    assertTrue(exercises.size >= 44)

    // Check that every level from 1 to 6 has exercises
    for (lvl in 1..6) {
      val lvlExercises = exercises.filter { it.level == lvl }
      assertTrue("Level $lvl should have exercises", lvlExercises.isNotEmpty())
      lvlExercises.forEach { ex ->
        assertTrue(ex.questionEn.isNotBlank())
        assertTrue(ex.correctAnswer.isNotBlank())
        assertTrue(ex.explanationEn.isNotBlank())
      }
    }

    // Verify Level 5 has reading texts and Level 6 has clinical case vignettes
    val level5 = exercises.filter { it.level == 5 }
    level5.forEach { ex ->
      assertTrue(ex.contextTextEn.isNotBlank())
    }

    val level6 = exercises.filter { it.level == 6 }
    level6.forEach { ex ->
      assertTrue(ex.contextTextEn.isNotBlank())
    }
  }

  @Test
  fun testNewMedicalCurriculumDatabases() {
    val allTerms = com.example.data.initial.InitialData.terms
    assertTrue("Total curriculum terms should exceed 175", allTerms.size >= 175)

    val modules = allTerms.map { it.module }.distinct()
    assertTrue(modules.contains("Anatomie"))
    assertTrue(modules.contains("Biochimie"))
    assertTrue(modules.contains("Biophysique"))
    assertTrue(modules.contains("Histologie"))
    assertTrue(modules.contains("Physiologie"))
    assertTrue(modules.contains("Génétique"))
    assertTrue(modules.contains("Terminologie Médicale"))

    // Validate that every term has complete multilingual fields
    allTerms.forEach { term ->
      assertTrue(term.id > 0)
      assertTrue("Term EN should not be blank for id ${term.id}", term.termEn.isNotBlank())
      assertTrue("Term FR should not be blank for id ${term.id}", term.termFr.isNotBlank())
      assertTrue("Term AR should not be blank for id ${term.id}", term.termAr.isNotBlank())
      assertTrue("Definition EN should not be blank for id ${term.id}", term.definitionEn.isNotBlank())
      assertTrue("Definition FR should not be blank for id ${term.id}", term.definitionFr.isNotBlank())
      assertTrue("Definition AR should not be blank for id ${term.id}", term.definitionAr.isNotBlank())
    }
  }

  @Test
  fun testNotificationHelperConstants() {
    assertEquals("medlingua_daily_review", com.example.service.NotificationHelper.CHANNEL_ID_REVIEWS)
    assertEquals("medlingua_streak", com.example.service.NotificationHelper.CHANNEL_ID_STREAKS)
    assertEquals("medlingua_clinical_pearls", com.example.service.NotificationHelper.CHANNEL_ID_PEARLS)
    assertEquals("medlingua_levels", com.example.service.NotificationHelper.CHANNEL_ID_LEVELS)

    assertEquals("FLASHCARDS", com.example.service.NotificationHelper.TARGET_FLASHCARDS)
    assertEquals("QUIZ", com.example.service.NotificationHelper.TARGET_QUIZ)
    assertEquals("MODULES", com.example.service.NotificationHelper.TARGET_MODULES)
    assertEquals("HOME", com.example.service.NotificationHelper.TARGET_HOME)
  }
}
