package com.aistudio.medlingua.dzmed.domain.writing

import java.text.Normalizer

/**
 * Correcteur de rappel écrit / oral d'un terme.
 *
 * L'élève doit produire le terme **en anglais** avant de retourner la carte.
 * Le correcteur ne se contente pas de « bon / faux » : il note la proximité
 * des caractères, les accents, la casse, les articles, le pluriel, l'ordre des
 * mots, et distingue « mot français au lieu de l'anglais » (sens) d'une vraie
 * faute d'orthographe. Le score 0–100 et la qualité 1–5 alimentent SM-2.
 *
 * Pur Kotlin : aucun accès Android, testable en JVM.
 */
object WritingCorrector {

    enum class Verdict {
        /** Réponse vide : rien à corriger. */
        EMPTY,

        /** Exactement le terme attendu (casse comprise). */
        EXACT,

        /** Même mot, mais accent / casse / article / tiret différent. */
        ACCENT,

        /** Quelques caractères différents : « presque le même mot ». */
        NEAR,

        /** Mot différent (ou mot français) : le sens n'est pas retrouvé. */
        WRONG
    }

    enum class IssueKind {
        EMPTY,
        ACCENT,
        CASE,
        ARTICLE,
        PLURAL,
        HYPHEN,
        MISSING_LETTER,
        EXTRA_LETTER,
        SUBSTITUTED_LETTER,
        WORD_ORDER,
        WRONG_LANGUAGE,
        MEANING
    }

    data class Issue(val kind: IssueKind, val detail: String = "")

    data class Result(
        val verdict: Verdict,
        /** 0..100, sert au bilan de session. */
        val score: Int,
        /** Qualité SM-2 1..5 proposée par le correcteur. */
        val quality: Int,
        val expected: String,
        val issues: List<Issue>
    ) {
        val isAccepted: Boolean get() = verdict == Verdict.EXACT || verdict == Verdict.ACCENT
    }

    /** Fragment de diff pour surligner « presque les mêmes caractères ». */
    data class DiffPart(val text: String, val kind: Kind) {
        enum class Kind { SAME, MISSING, EXTRA, CHANGED }
    }

    private val ARTICLES = listOf("the", "a", "an", "le", "la", "les", "l", "un", "une", "des")

    /**
     * @param expected réponse canonique (terme anglais)
     * @param expectedFr terme français, pour détecter « j'ai écrit le français »
     * @param accepted variantes acceptées (synonymes) éventuelles
     */
    fun check(
        answer: String,
        expected: String,
        expectedFr: String? = null,
        accepted: List<String> = emptyList()
    ): Result {
        val raw = answer.trim()
        if (raw.isEmpty()) {
            return Result(Verdict.EMPTY, 0, 1, expected, listOf(Issue(IssueKind.EMPTY)))
        }

        val targets = (listOf(expected) + accepted)
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .distinct()

        // 1. Exact, casse comprise.
        if (targets.any { raw == it }) {
            return Result(Verdict.EXACT, 100, 5, expected, emptyList())
        }

        // 2. Même mot après normalisation : accents / casse / article / tiret.
        val normAnswer = normalize(raw)
        val accentHit = targets.firstOrNull { normalize(it) == normAnswer }
        if (accentHit != null) {
            val accentOnly = stripAccents(raw).equals(stripAccents(accentHit), ignoreCase = true) &&
                !raw.equals(accentHit, ignoreCase = true)
            val issues = buildList {
                if (accentOnly) add(Issue(IssueKind.ACCENT))
                // Casse : mêmes lettres, accents compris (« THE HEART » vs « the heart »).
                if (raw != accentHit && raw.equals(accentHit, ignoreCase = true)) {
                    add(Issue(IssueKind.CASE))
                }
                if (articlesOf(raw) != articlesOf(accentHit)) add(Issue(IssueKind.ARTICLE))
                if (raw.contains('-') != accentHit.contains('-')) add(Issue(IssueKind.HYPHEN))
            }.ifEmpty { listOf(Issue(IssueKind.ACCENT)) }
            return Result(Verdict.ACCENT, 95, 5, expected, issues)
        }

        // 3. L'élève a écrit le mot français au lieu de l'anglais.
        if (!expectedFr.isNullOrBlank() && normalize(expectedFr) == normAnswer) {
            return Result(
                Verdict.WRONG, 0, 1, expected,
                listOf(Issue(IssueKind.WRONG_LANGUAGE, expectedFr))
            )
        }

        // 4. Meilleure cible par distance d'édition.
        val best = targets.minByOrNull { levenshtein(normAnswer, normalize(it)) } ?: expected
        val normBest = normalize(best)
        // On compare aussi sans les articles : « heart » vs « the heart » ne doit
        // pas être jugé comme un mot totalement faux.
        val distRaw = levenshtein(normAnswer, normBest)
        val distNoArticle = levenshtein(stripArticles(raw), stripArticles(best))
        val distance = minOf(distRaw, distNoArticle)
        val similarity = maxOf(
            similarity(normAnswer, normBest),
            similarity(stripArticles(raw), stripArticles(best))
        )
        val nearEnough = when {
            similarity >= 0.80 -> true
            normBest.length >= 6 && distance <= 2 -> true
            normBest.length in 3..5 && distance == 1 -> true
            else -> false
        }
        // Mêmes mots dans un autre ordre (« pressure blood ») : faute de
        // grammaire, pas un mot inventé — on le juge « presque ».
        val sameWordsReordered = normAnswer != normBest &&
            normAnswer.split(' ').filter { it.isNotEmpty() }.sorted() ==
            normBest.split(' ').filter { it.isNotEmpty() }.sorted()

        // Le diagnostic porte sur la forme la plus proche (avec ou sans article),
        // sinon « heart » vs « the heart » afficherait 3 lettres manquantes.
        val compareNoArticle = distNoArticle < distRaw
        val cmpAnswer = if (compareNoArticle) stripArticles(raw) else normAnswer
        val cmpExpected = if (compareNoArticle) stripArticles(best) else normBest

        val issues = mutableListOf<Issue>()
        if (nearEnough || sameWordsReordered) {
            val score = if (nearEnough) {
                (55 + 40 * similarity).toInt().coerceIn(55, 94)
            } else {
                70
            }
            issues += letterIssues(cmpAnswer, cmpExpected)
            issues += grammarIssues(raw, best)
            if (issues.isEmpty()) issues += Issue(IssueKind.SUBSTITUTED_LETTER)
            return Result(
                Verdict.NEAR,
                score,
                if (nearEnough && similarity >= 0.9) 4 else 3,
                expected,
                issues.distinctBy { it.kind }
            )
        }

        // 5. Faux : on explique ce qui cloche, le sens n'est pas retrouvé.
        issues += letterIssues(cmpAnswer, cmpExpected)
        issues += grammarIssues(raw, best)
        issues += Issue(IssueKind.MEANING)
        val partial = (similarity * 100).toInt().coerceIn(0, 54)
        return Result(
            Verdict.WRONG,
            partial,
            if (similarity >= 0.5) 2 else 1,
            expected,
            issues.distinctBy { it.kind }
        )
    }

    // ------------------------------------------------------------------
    // Diagnostics
    // ------------------------------------------------------------------

    private fun letterIssues(answer: String, expected: String): List<Issue> {
        val diff = diff(answer, expected)
        val missing = diff.filter { it.kind == DiffPart.Kind.MISSING }.sumOf { it.text.length }
        val extra = diff.filter { it.kind == DiffPart.Kind.EXTRA }.sumOf { it.text.length }
        val changed = diff.filter { it.kind == DiffPart.Kind.CHANGED }.sumOf { it.text.length }
        return buildList {
            if (missing > 0) add(Issue(IssueKind.MISSING_LETTER, missing.toString()))
            if (extra > 0) add(Issue(IssueKind.EXTRA_LETTER, extra.toString()))
            if (changed > 0) add(Issue(IssueKind.SUBSTITUTED_LETTER, changed.toString()))
        }
    }

    private fun grammarIssues(answer: String, expected: String): List<Issue> {
        val issues = mutableListOf<Issue>()
        val a = normalize(answer)
        val e = normalize(expected)

        if (a != e && a.split(' ').filter { it.isNotEmpty() }.sorted() ==
            e.split(' ').filter { it.isNotEmpty() }.sorted()
        ) {
            issues += Issue(IssueKind.WORD_ORDER)
        }
        if (articlesOf(answer) != articlesOf(expected)) {
            issues += Issue(IssueKind.ARTICLE)
        }
        if (a + "s" == e || e + "s" == a) {
            issues += Issue(IssueKind.PLURAL)
        }
        if (answer.contains('-') != expected.contains('-')) {
            issues += Issue(IssueKind.HYPHEN)
        }
        return issues
    }

    /** Articles réellement présents (et non « après retrait »), pour diagnostiquer. */
    private fun articlesOf(text: String): List<String> =
        normalize(text).split(' ').filter { it in ARTICLES }

    // ------------------------------------------------------------------
    // Normalisation / distances
    // ------------------------------------------------------------------

    /** Minuscules, sans accents ni ponctuation, espaces compactés. */
    fun normalize(text: String): String =
        stripAccents(
            text.lowercase()
                // Ligatures françaises : « œdème » et « oedeme » sont le même mot.
                .replace("œ", "oe")
                .replace("æ", "ae")
                .replace("ß", "ss")
        )
            .replace(Regex("[^a-z0-9\\s]"), " ")
            .replace(Regex("\\s+"), " ")
            .trim()

    private fun stripAccents(text: String): String =
        Normalizer.normalize(text, Normalizer.Form.NFD)
            .replace(Regex("\\p{M}+"), "")

    private fun stripArticles(text: String): String {
        val words = normalize(text).split(' ').filter { it.isNotEmpty() }
        val stripped = words.dropWhile { it in ARTICLES }
        return (if (stripped.isEmpty()) words else stripped).joinToString(" ")
    }

    fun levenshtein(a: String, b: String): Int {
        if (a == b) return 0
        if (a.isEmpty()) return b.length
        if (b.isEmpty()) return a.length
        var previous = IntArray(b.length + 1) { it }
        var current = IntArray(b.length + 1)
        for (i in 1..a.length) {
            current[0] = i
            for (j in 1..b.length) {
                val cost = if (a[i - 1] == b[j - 1]) 0 else 1
                current[j] = minOf(
                    current[j - 1] + 1,
                    previous[j] + 1,
                    previous[j - 1] + cost
                )
            }
            val swap = previous
            previous = current
            current = swap
        }
        return previous[b.length]
    }

    private fun similarity(a: String, b: String): Double {
        if (a.isEmpty() && b.isEmpty()) return 1.0
        val max = maxOf(a.length, b.length)
        if (max == 0) return 1.0
        return 1.0 - levenshtein(a, b).toDouble() / max
    }

    /** Diff caractère par caractère (LCS) pour l'affichage. */
    fun diff(answer: String, expected: String): List<DiffPart> {
        val a = normalize(answer)
        val b = normalize(expected)
        val table = Array(a.length + 1) { IntArray(b.length + 1) }
        for (i in a.length - 1 downTo 0) {
            for (j in b.length - 1 downTo 0) {
                table[i][j] = if (a[i] == b[j]) table[i + 1][j + 1] + 1
                else maxOf(table[i + 1][j], table[i][j + 1])
            }
        }
        val parts = mutableListOf<DiffPart>()
        fun push(text: String, kind: DiffPart.Kind) {
            if (text.isEmpty()) return
            val last = parts.lastOrNull()
            if (last != null && last.kind == kind) parts[parts.lastIndex] = last.copy(text = last.text + text)
            else parts += DiffPart(text, kind)
        }
        var i = 0
        var j = 0
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                push(a[i].toString(), DiffPart.Kind.SAME)
                i++
                j++
            } else if (table[i + 1][j] > table[i][j + 1]) {
                push(a[i].toString(), DiffPart.Kind.EXTRA)
                i++
            } else if (table[i + 1][j] < table[i][j + 1]) {
                push(b[j].toString(), DiffPart.Kind.MISSING)
                j++
            } else {
                // Même longueur restante : c'est une lettre remplacée, pas un
                // ajout + un oubli (« presque les mêmes caractères »).
                push(b[j].toString(), DiffPart.Kind.CHANGED)
                i++
                j++
            }
        }
        while (i < a.length) {
            push(a[i].toString(), DiffPart.Kind.EXTRA)
            i++
        }
        while (j < b.length) {
            push(b[j].toString(), DiffPart.Kind.MISSING)
            j++
        }
        return parts
    }
}
