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
    // Floor instead of the exact 412: seed additions must not break the test,
    // while a lost seed file (pre-fix this returned ~200 terms) still fails.
    assertTrue("anatomy seed too small (${allTerms.size})", allTerms.size >= 380)

    val expectedChapters = setOf(
      "Ostéologie", "Arthrologie", "Myologie", "Neurologie", "Angiologie",
      "Organes", "Organes sensoriels", "Appareil digestif", "Appareil respiratoire",
      "Appareil urogénital", "Termes généraux"
    )
    // All 11 anatomy chapters must exist and every term must sit in one of them
    expectedChapters.forEach { ch ->
      val count = allTerms.count { it.chapter == ch }
      assertTrue("chapter '$ch' has no terms (got $count)", count > 0)
    }
    val unknown = allTerms.filter { it.chapter !in expectedChapters }.groupBy { it.chapter }
    assertTrue("terms outside the 11 anatomy chapters: ${unknown.keys}", unknown.isEmpty())

    // IDs unique inside the anatomy seed; cross-module uniqueness is asserted
    // by testGlobalTermIdsAreUnique (seed uses OnConflictStrategy.REPLACE).
    val ids = allTerms.map { it.id }
    assertEquals(allTerms.size, ids.toSet().size)
    ids.forEach { assertTrue("id $it must be positive", it > 0) }

    // Verify IDs and non-empty multilingual terms
    allTerms.forEach { term ->
      assertTrue(term.termEn.isNotBlank())
      assertTrue(term.termFr.isNotBlank())
      assertTrue(term.termAr.isNotBlank())
      assertTrue(term.definitionEn.isNotBlank())
      assertTrue(term.definitionFr.isNotBlank())
      assertTrue(term.definitionAr.isNotBlank())
    }
  }

  @Test
  fun testGlobalTermIdsAreUnique() {
    // MedicalDao.insertTerms runs with OnConflictStrategy.REPLACE: a duplicated
    // id silently overwrites another module's term at seed time.
    val allTerms = com.example.data.initial.InitialData.terms
    val ids = allTerms.map { it.id }
    assertEquals(
      "MedicalTermEntity ids must be globally unique across every seed file",
      allTerms.size,
      ids.toSet().size
    )
    ids.forEach { assertTrue("id $it must be positive", it > 0) }
  }

  @Test
  fun testGeneticsModuleDatabase() {
    val genetics = com.example.data.initial.InitialData.termsOfModule("Génétique")
    // Floor instead of the exact 232 (8 legacy + 225 SQL dump − 1 duplicate):
    // seed additions must not break the test, a lost dump still fails.
    assertTrue("génétique seed too small (${genetics.size})", genetics.size >= 200)
    assertTrue(
      "génétique chapters too few",
      com.example.data.initial.InitialData.chaptersOfModule("Génétique").size >= 10
    )
    genetics.forEach { term ->
      assertTrue("termEn blank for id ${term.id}", term.termEn.isNotBlank())
      assertTrue("termFr blank for id ${term.id}", term.termFr.isNotBlank())
      assertTrue("termAr blank for id ${term.id}", term.termAr.isNotBlank())
      assertTrue("definitionEn blank for id ${term.id}", term.definitionEn.isNotBlank())
      assertTrue("definitionFr blank for id ${term.id}", term.definitionFr.isNotBlank())
      assertTrue("definitionAr blank for id ${term.id}", term.definitionAr.isNotBlank())
      assertTrue("ipaPhonetic blank for id ${term.id}", term.ipaPhonetic.isNotBlank())
    }
  }

  @Test
  fun testMicrobiologyModuleDatabase() {
    val micro = com.example.data.initial.InitialData.termsOfModule("Microbiologie")
    // Floor instead of the exact 235 (15 legacy + 220 SQL dump)
    assertTrue("microbiologie seed too small (${micro.size})", micro.size >= 200)
    assertTrue(
      "microbiologie chapters too few",
      com.example.data.initial.InitialData.chaptersOfModule("Microbiologie").size >= 10
    )
    micro.forEach { term ->
      assertTrue("termEn blank for id ${term.id}", term.termEn.isNotBlank())
      assertTrue("termFr blank for id ${term.id}", term.termFr.isNotBlank())
      assertTrue("termAr blank for id ${term.id}", term.termAr.isNotBlank())
      assertTrue("definitionEn blank for id ${term.id}", term.definitionEn.isNotBlank())
      assertTrue("definitionFr blank for id ${term.id}", term.definitionFr.isNotBlank())
      assertTrue("definitionAr blank for id ${term.id}", term.definitionAr.isNotBlank())
      assertTrue("ipaPhonetic blank for id ${term.id}", term.ipaPhonetic.isNotBlank())
    }
  }

  @Test
  fun testPharmacologyModuleDatabase() {
    val pharm = com.example.data.initial.InitialData.termsOfModule("Pharmacologie")
    // Floor instead of the exact 228 (15 legacy + 213 SQL dump)
    assertTrue("pharmacologie seed too small (${pharm.size})", pharm.size >= 200)
    assertTrue(
      "pharmacologie chapters too few",
      com.example.data.initial.InitialData.chaptersOfModule("Pharmacologie").size >= 10
    )
    pharm.forEach { term ->
      assertTrue("termEn blank for id ${term.id}", term.termEn.isNotBlank())
      assertTrue("termFr blank for id ${term.id}", term.termFr.isNotBlank())
      assertTrue("termAr blank for id ${term.id}", term.termAr.isNotBlank())
      assertTrue("definitionEn blank for id ${term.id}", term.definitionEn.isNotBlank())
      assertTrue("definitionFr blank for id ${term.id}", term.definitionFr.isNotBlank())
      assertTrue("definitionAr blank for id ${term.id}", term.definitionAr.isNotBlank())
      assertTrue("ipaPhonetic blank for id ${term.id}", term.ipaPhonetic.isNotBlank())
    }
  }

  @Test
  fun testSemiologyModuleDatabase() {
    val semi = com.example.data.initial.InitialData.termsOfModule("Sémiologie Médicale")
    // Floor instead of the exact 238 (15 legacy + 223 SQL dump)
    assertTrue("sémiologie seed too small (${semi.size})", semi.size >= 200)
    assertTrue(
      "sémiologie chapters too few",
      com.example.data.initial.InitialData.chaptersOfModule("Sémiologie Médicale").size >= 10
    )
    semi.forEach { term ->
      assertTrue("termEn blank for id ${term.id}", term.termEn.isNotBlank())
      assertTrue("termFr blank for id ${term.id}", term.termFr.isNotBlank())
      assertTrue("termAr blank for id ${term.id}", term.termAr.isNotBlank())
      assertTrue("definitionEn blank for id ${term.id}", term.definitionEn.isNotBlank())
      assertTrue("definitionFr blank for id ${term.id}", term.definitionFr.isNotBlank())
      assertTrue("definitionAr blank for id ${term.id}", term.definitionAr.isNotBlank())
      assertTrue("ipaPhonetic blank for id ${term.id}", term.ipaPhonetic.isNotBlank())
    }
  }

  @Test
  fun testPathologyModuleDatabase() {
    val patho = com.example.data.initial.InitialData.termsOfModule("Anatomie Pathologique")
    // Floor instead of the exact 218 (15 legacy + 203 SQL dump)
    assertTrue("anatomie pathologique seed too small (${patho.size})", patho.size >= 180)
    assertTrue(
      "anatomie pathologique chapters too few",
      com.example.data.initial.InitialData.chaptersOfModule("Anatomie Pathologique").size >= 10
    )
    patho.forEach { term ->
      assertTrue("termEn blank for id ${term.id}", term.termEn.isNotBlank())
      assertTrue("termFr blank for id ${term.id}", term.termFr.isNotBlank())
      assertTrue("termAr blank for id ${term.id}", term.termAr.isNotBlank())
      assertTrue("definitionEn blank for id ${term.id}", term.definitionEn.isNotBlank())
      assertTrue("definitionFr blank for id ${term.id}", term.definitionFr.isNotBlank())
      assertTrue("definitionAr blank for id ${term.id}", term.definitionAr.isNotBlank())
      assertTrue("ipaPhonetic blank for id ${term.id}", term.ipaPhonetic.isNotBlank())
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
    // Real count is 44: >= keeps additions harmless while still catching any loss
    assertTrue("exercise seed too small (${exercises.size})", exercises.size >= 44)

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
    // Real floor: the 5 SQL-dump modules alone contribute ~1150 terms
    assertTrue("Total curriculum terms too small (${allTerms.size})", allTerms.size >= 1400)

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
