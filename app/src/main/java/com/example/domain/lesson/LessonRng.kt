package com.example.domain.lesson

/**
 * Outils déterministes partagés par le générateur de leçons.
 *
 * Port fidèle de `web-react/src/domain/leagues.ts` (mulberry32, hashSeed) et
 * `web-react/src/lib/utils.ts` (norm, shuffleWith). Les trois doivent produire
 * **exactement** la même séquence que la version web pour une graine donnée —
 * c'est ce qui garantit qu'une leçon est identique sur les deux plateformes.
 */
object LessonRng {

    /**
     * Graine stable à partir d'une chaîne (FNV-1a 32 bits), identique au web.
     * `hashSeed("anat:3")` donne le même nombre en Kotlin et en TypeScript.
     *
     * **Piège** : la version JS retourne `h >>> 0` (UInt32 non signé). En Kotlin
     * `h *= 16777619` déborde en Int signé — *c'est voulu* et identique au JS
     * (même motifs binaires), mais le résulat peut être négatif. Tout consumer
     * qui fait un modulo doit normaliser en Long non signé d'abord.
     */
    fun hashSeed(text: String): Int {
        var h = 2166136261.toInt()
        for (ch in text) {
            h = h xor ch.code
            h *= 16777619
        }
        return h
    }

    /**
     * mulberry32 : PRNG déterministe. L'arithmétique entière 32 bits est
     * intentionnellement identique à la version JS.
     *
     * **Piège** : JS `>>> 0` convertit en *UInt32 non signé* avant la division
     * finale. En Kotlin, `(t ushr 14)` reste un Int signé (donc négatif pour
     * la moitié des graines) et la division rendait des valeurs négatives →
     * `(rand() * size).toInt()` levait `IndexOutOfBoundsException`. Il faut
     * normaliser en Long non signé avant de diviser.
     */
    fun mulberry32(seed: Int): () -> Double {
        var a = seed
        return {
            a += 0x6d2b79f5
            var t = a
            t = (t xor (t ushr 15)) * (t or 1)
            t = t xor (t + (t xor (t ushr 7)) * 0x9e3779b1.toInt())
            ((t xor (t ushr 14)).toLong() and 0xFFFFFFFFL) / 4294967296.0
        }
    }

    /**
     * Normalisation pour comparer saisie et réponse : NFD + suppression des
     * diacritiques latins + minuscules + repli des espaces.
     * « Café » = « cafe », « Vertige » = « vertige ».
     */
    fun norm(s: String): String {
        val raw = s.trim()
        val folded = if (raw.any { it.code > 0x7f }) {
            java.text.Normalizer.normalize(raw, java.text.Normalizer.Form.NFD)
                .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
        } else {
            raw
        }
        return folded.lowercase().trim().replace(Regex("\\s+"), " ")
    }

    /** Mélange de Fisher-Yates avec un PRNG injecté (déterministe). */
    fun <T> shuffleWith(list: List<T>, rand: () -> Double): List<T> {
        val out = list.toMutableList()
        for (i in out.size - 1 downTo 1) {
            val j = (rand() * (i + 1)).toInt()
            val tmp = out[i]
            out[i] = out[j]
            out[j] = tmp
        }
        return out
    }

    /**
     * Ressemblance de Jaccard sur les bigrammes (0 → 1) — port de
     * `similarity()` dans lessons.ts. Sert à rapprocher les distracteurs :
     * similarité haute = termes proches (difficiles), basse = éloignés.
     */
    fun similarity(a: String, b: String): Double {
        val gramsA = bigrams(a)
        val gramsB = bigrams(b)
        if (gramsA.isEmpty() || gramsB.isEmpty()) return 0.0
        var shared = 0
        for (g in gramsA) if (g in gramsB) shared++
        return (2.0 * shared) / (gramsA.size + gramsB.size)
    }

    private fun bigrams(s: String): Set<String> {
        val t = norm(s).replace(Regex("\\s+"), "")
        val out = mutableSetOf<String>()
        var i = 0
        while (i < t.length - 1) {
            out.add(t.substring(i, i + 2))
            i++
        }
        return out
    }
}
