package com.example

import com.example.data.initial.InitialData
import com.example.domain.exercise.WordbankOrder
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Seed integrity checks (Lot 5 - bugs 5, 6, 48).
 * Lists every violation so bad seed data cannot silently become
 * "option 0 is correct" (indexOfFirst coerceAtLeast(0)) or an
 * invisible exercise (module label outside the 15-module taxonomy).
 */
class SeedIntegrityTest {

    private fun normalize(s: String) = s.trim().lowercase()

    @Test
    fun `seed sources are not empty`() {
        // Guards every other test in this class against silently passing on
        // empty lists (isEmpty filters over an empty collection are vacuously true).
        assertTrue("InitialData.terms is empty", InitialData.terms.isNotEmpty())
        assertTrue("InitialData.exercises is empty", InitialData.exercises.isNotEmpty())
        assertTrue("InitialData.modulesList is empty", InitialData.modulesList.isNotEmpty())
    }

    @Test
    fun `every exercise module belongs to the module taxonomy`() {
        val titles = InitialData.modulesList.map { normalize(it.titleFr) }.toSet()
        val bad = InitialData.exercises
            .filter { normalize(it.module) !in titles }
            .groupBy({ it.module }, { it.id })
            .map { (mod, ids) -> "\"$mod\" -> ids ${ids.take(20)}" }
        assertTrue(
            "Exercises with module outside taxonomy (${bad.size} labels):\n" + bad.joinToString("\n"),
            bad.isEmpty()
        )
    }

    @Test
    fun `every term module belongs to the module taxonomy`() {
        val titles = InitialData.modulesList.map { normalize(it.titleFr) }.toSet()
        val bad = InitialData.terms
            .filter { normalize(it.module) !in titles }
            .groupBy({ it.module }, { it.id })
            .map { (mod, ids) -> "\"$mod\" -> ids ${ids.take(20)}" }
        assertTrue(
            "Terms with module outside taxonomy (${bad.size} labels):\n" + bad.joinToString("\n"),
            bad.isEmpty()
        )
    }

    @Test
    fun `choice exercises contain their correct answer in options`() {
        val choice = InitialData.exercises
            .filter { it.type in setOf("mcq", "reading", "clinical_case") }
        assertTrue("no choice-type exercises in seed", choice.isNotEmpty())
        val bad = mutableListOf<String>()
        choice.forEach { ex ->
            val options = ex.getOptionsList()
            val found = options.any { normalize(it) == normalize(ex.correctAnswer) }
            val reason = when {
                options.isEmpty() -> "no options"
                !found -> "correctAnswer not in options"
                else -> null
            }
            if (reason != null) bad.add("id=${ex.id} type=${ex.type} module=${ex.module} [$reason] correct=\"${ex.correctAnswer}\" options=${options.take(4)}")
        }
        assertTrue("Choice exercises broken (${bad.size}):\n" + bad.take(40).joinToString("\n"), bad.isEmpty())
    }

    @Test
    fun `exercise structure is valid`() {
        val validTypes = setOf("mcq", "fill_blank", "matching", "sentence_order", "reading", "clinical_case")
        val bad = mutableListOf<String>()
        InitialData.exercises.forEach { ex ->
            when {
                ex.type !in validTypes -> bad.add("id=${ex.id} unknown type \"${ex.type}\"")
                ex.correctAnswer.isBlank() -> bad.add("id=${ex.id} type=${ex.type} blank correctAnswer")
                ex.questionFr.isBlank() -> bad.add("id=${ex.id} type=${ex.type} blank questionFr")
                ex.type in setOf("reading", "clinical_case") && ex.contextTextFr.isBlank() ->
                    bad.add("id=${ex.id} type=${ex.type} blank contextTextFr")
                ex.type == "matching" && ex.getOptionsList().none { it.contains(":") } ->
                    bad.add("id=${ex.id} matching without key:value pairs")
                ex.type == "sentence_order" &&
                    WordbankOrder.computeCorrectOrder(ex.getOptionsList(), ex.correctAnswer).isEmpty() ->
                    bad.add("id=${ex.id} wordbank with empty correctOrder options=${ex.getOptionsList().take(6)} answer=\"${ex.correctAnswer}\"")
            }
        }
        assertTrue("Structure violations (${bad.size}):\n" + bad.take(40).joinToString("\n"), bad.isEmpty())
    }

    @Test
    fun `ids are unique`() {
        val dupTerms = InitialData.terms.groupBy { it.id }.filter { it.value.size > 1 }.keys
        val dupEx = InitialData.exercises.groupBy { it.id }.filter { it.value.size > 1 }.keys
        assertTrue("Duplicate term ids: ${dupTerms.take(20)}", dupTerms.isEmpty())
        assertTrue("Duplicate exercise ids: ${dupEx.take(20)}", dupEx.isEmpty())
    }

    @Test
    fun `no duplicate terms within the same module`() {
        val dups = InitialData.terms
            .groupBy { normalize(it.module) to normalize(it.termEn) }
            .filterValues { it.size > 1 }
            .map { (k, v) -> "\"${k.second}\" in \"${k.first}\" ids=${v.map { it.id }}" }
        assertTrue("Duplicate terms (${dups.size}):\n" + dups.joinToString("\n"), dups.isEmpty())
    }

    @Test
    fun `medical english examples are translated`() {
        val english = InitialData.terms.filter { normalize(it.module) == "anglais médical" }
        assertTrue("no Anglais Médical terms in seed", english.isNotEmpty())
        val blank = english.filter { it.exampleFr.isBlank() || it.exampleAr.isBlank() }
            .map { "term id=${it.id} (\"${it.termEn}\")" }
        assertTrue("Untranslated examples (${blank.size}):\n" + blank.take(30).joinToString("\n"), blank.isEmpty())
        val copied = english.filter {
            normalize(it.exampleFr) == normalize(it.exampleEn) ||
                normalize(it.exampleAr) == normalize(it.exampleEn)
        }.map { "term id=${it.id} (\"${it.termEn}\")" }
        assertTrue("Examples copied from English (${copied.size}):\n" + copied.take(30).joinToString("\n"), copied.isEmpty())
    }

    @Test
    fun `no blank core fields`() {
        val bad = mutableListOf<String>()
        InitialData.terms.forEach { t ->
            if (t.termFr.isBlank()) bad.add("term id=${t.id} blank termFr")
            if (t.definitionFr.isBlank()) bad.add("term id=${t.id} (\"${t.termEn}\") blank definitionFr")
            if (t.chapter.isBlank()) bad.add("term id=${t.id} (\"${t.termEn}\") blank chapter")
        }
        InitialData.exercises.forEach { ex ->
            if (ex.level !in 1..6) bad.add("exercise id=${ex.id} level ${ex.level} out of 1..6")
            if (ex.chapter.isBlank()) bad.add("exercise id=${ex.id} blank chapter")
        }
        assertTrue("Blank fields (${bad.size}):\n" + bad.take(30).joinToString("\n"), bad.isEmpty())
    }
}
