package com.aistudio.medlingua.dzmed.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aistudio.medlingua.dzmed.data.local.database.MedLinguaDatabase
import com.aistudio.medlingua.dzmed.data.local.entity.ExerciseEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Régression P2-1 (revue database v2, défaut D6).
 *
 * `seedBulkExercisesIfMissing` enchaînait `deleteBulkExercises()` puis
 * `insertExercises()` hors transaction. Si l'insert échouait, la table perdait
 * 13 512 exercices et l'erreur était seulement logguée.
 *
 * `replaceBulkExercises` est transactionnelle : si l'insert échoue, le DELETE
 * est annulé et l'état antérieur est intact.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class BulkExercisesAtomicityTest {

    private lateinit var db: MedLinguaDatabase
    private lateinit var dao: MedicalDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MedLinguaDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.medicalDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    /** Un exercice généré : id négatif (convention BulkExerciseGenerator). */
    private fun bulk(id: Int, module: String = "Anatomie") = ExerciseEntity(
        id = -id,
        level = 1,
        type = "mcq",
        difficulty = "beginner",
        module = module,
        chapter = "ch",
        questionEn = "q$id", questionFr = "q$id", questionAr = "q$id",
        optionsRaw = "a|b|c|d",
        correctAnswer = "a",
        explanationEn = "", explanationFr = "", explanationAr = "",
        points = 10
    )

    /** Un exercice écrit main : id positif, préservé par le nettoyage. */
    private fun handcrafted(id: Int) = bulk(id).copy(id = id)

    @Test
    fun `le remplacement normal supprime les anciens et insere les nouveaux`() = runBlocking {
        dao.insertExercises(listOf(bulk(1), bulk(2), handcrafted(100)))
        assertEquals("1 exercice main + 2 générés", 3, dao.getAllExercises().first().size)

        dao.replaceBulkExercises(listOf(bulk(3), bulk(4)))

        val all = dao.getAllExercises().first()
        assertEquals("l'exercice main est préservé", 3, all.size)
        assertEquals("2 nouveaux exercices générés", 2, dao.getBulkExercisesCount())
        assertTrue("l'exercice main id=100 survit", all.any { it.id == 100 })
    }

    @Test
    fun `un insert qui echoue ne supprime pas les exercices existants`() = runBlocking {
        // État antérieur : 2 exercices générés (modules distincts) + 1 main.
        dao.insertExercises(listOf(
            bulk(1, "Anatomie"), bulk(2, "Cardio"), handcrafted(100).copy(module = "Clinique")
        ))
        assertEquals(2, dao.getBulkExercisesCount())

        // Injecteur d'échec : un trigger SQLite qui fait ÉCHOUER un insert dans
        // `exercises` (RAISE ABORT). Sert uniquement à déclencher l'échec à
        // l'intérieur de la transaction — sans lui, Room (INSERT OR REPLACE)
        // n'échoue jamais et l'atomicité n'est pas testable. Une contrainte
        // UNIQUE ne conviendrait pas : REPLACE l'absorbe silencieusement.
        db.openHelper.writableDatabase.execSQL(
            """
            CREATE TRIGGER IF NOT EXISTS trg_injecteur_bulk
            BEFORE INSERT ON exercises
            FOR EACH ROW
            WHEN NEW.id = -4
            BEGIN
                SELECT RAISE(ABORT, 'echec injecte');
            END;
            """.trimIndent()
        )

        // L'insert doit échouer : le trigger aborte l'insertion de l'id -4.
        var aEchoue = false
        try {
            dao.replaceBulkExercises(listOf(bulk(3, "Doublon"), bulk(4, "Doublon")))
        } catch (e: android.database.sqlite.SQLiteException) {
            aEchoue = true
        }

        assertTrue("l'insert doit échouer sur la contrainte UNIQUE injectée", aEchoue)

        // Propriété clé (défaut D6) : le DELETE a été annulé par la transaction.
        // Sans @Transaction, la table aurait perdu ses exercices générés avec
        // seulement une erreur logguée — amputation silencieuse de
        // 13 512 exercices en production.
        assertEquals(
            "les exercices existants n'ont pas été supprimés",
            2, dao.getBulkExercisesCount()
        )
        assertEquals("l'exercice main est intact", 1,
            dao.getAllExercises().first().count { it.id > 0 })
    }
}
