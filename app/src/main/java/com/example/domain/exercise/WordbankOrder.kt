package com.example.domain.exercise

/**
 * Computes the Wordbank [ExerciseSpec.Wordbank.correctOrder] from a token bank and the
 * correct sentence. Legacy code used `correctAnswer.split(" ").map { bank.indexOf(it) }`
 * which broke on:
 *  - duplicate tokens (every occurrence mapped to the first index, one bank slot never used),
 *  - multi-word bank tokens like "so that" (indexOf returned -1, coerced to 0),
 *  - case differences ("the" vs "The").
 * Words absent from the bank are skipped instead of silently mapping to index 0.
 */
object WordbankOrder {

    fun computeCorrectOrder(bank: List<String>, correctSentence: String): List<Int> {
        if (bank.isEmpty() || correctSentence.isBlank()) return emptyList()

        val words = correctSentence.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }
        val indicesByToken = HashMap<String, MutableList<Int>>()
        bank.forEachIndexed { index, token ->
            indicesByToken.getOrPut(token.trim().lowercase()) { mutableListOf() }.add(index)
        }
        val maxTokenWords = bank.maxOf { it.split(Regex("\\s+")).size.coerceAtLeast(1) }
        val consumed = HashMap<String, Int>()
        val order = mutableListOf<Int>()

        var i = 0
        while (i < words.size) {
            var matched = false
            for (len in minOf(maxTokenWords, words.size - i) downTo 1) {
                val candidate = words.subList(i, i + len).joinToString(" ").lowercase()
                val indices = indicesByToken[candidate] ?: continue
                val used = consumed[candidate] ?: 0
                if (used < indices.size) {
                    order.add(indices[used])
                    consumed[candidate] = used + 1
                    i += len
                    matched = true
                    break
                }
            }
            if (!matched) i++
        }
        return order
    }
}
