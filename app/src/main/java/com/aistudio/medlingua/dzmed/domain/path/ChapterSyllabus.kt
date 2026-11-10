package com.aistudio.medlingua.dzmed.domain.path

/**
 * Découpage d'un chapitre en leçons — « parcours 3 termes » (2026-11-10).
 *
 * Règles (valables pour **tous** les modules) :
 *  1. une leçon d'apprentissage introduit **3 nouveaux termes** ([TERMS_PER_LESSON]) ;
 *  2. après **chaque paire** de leçons d'apprentissage vient une **révision** ([REVIEW_EVERY]) ;
 *  3. la révision est **facultative** si les deux leçons revues sont réussies
 *     (≥ [PASS_SCORE] %), **obligatoire** sinon — c'est le point de décision
 *     après L1 + L2 : l'élève peut continuer ou réviser ;
 *  4. jamais de leçon vide : un reste d'un seul terme est absorbé par les deux
 *     dernières leçons (2 + 2) plutôt que de créer une leçon orpheline ;
 *  5. un chapitre d'au plus 3 termes donne **une seule** leçon (pas de révision
 *     d'une leçon unique : elle n'apporterait rien).
 *
 * Exemples :
 *  - 30 termes → 10 leçons d'apprentissage + 5 révisions = 15 nœuds ;
 *  - 22 termes → 8 leçons (3×6 + 2 + 2) + 4 révisions = 12 nœuds ;
 *  - 3 termes  → 1 leçon, 8 questions ;
 *  - 1 terme   → 1 leçon, 3 questions.
 *
 * Le découpage est **déterministe** : mêmes termes, mêmes leçons, quel que soit
 * l'écran qui l'appelle (Parcours, ViewModel, tests).
 */
object ChapterSyllabus {

    /** Nouveaux termes introduits par une leçon d'apprentissage. */
    const val TERMS_PER_LESSON = 3

    /** Une révision après chaque paire de leçons d'apprentissage. */
    const val REVIEW_EVERY = 2

    /** Seuil de réussite d'une leçon (%). */
    const val PASS_SCORE = 50

    /** Moyenne minimale d'un chapitre pour ouvrir le suivant (%). */
    const val CHAPTER_UNLOCK_AVERAGE = 50

    /** Questions d'une leçon d'apprentissage (plafond). */
    const val NEW_LESSON_QUESTIONS = 8

    /** Questions d'une révision (plafond). */
    const val REVIEW_QUESTIONS = 16

    /** Une leçon travaille chaque terme sous 3 angles (EN→FR, EN→AR, FR→EN…). */
    private const val QUESTIONS_PER_TERM = 3

    /** Nature d'un nœud de leçon. */
    enum class Kind { NEW, REVIEW }

    /**
     * Une leçon planifiée.
     *
     * @param level position 1-based dans le chapitre : c'est aussi la clé de
     *   stockage (`module|chapitre|niveau`).
     * @param termIds termes travaillés (3 nouveaux, ou l'union des 2 leçons
     *   revues pour une révision).
     * @param reviewedLevels niveaux des leçons revues (vide pour un apprentissage).
     */
    data class Lesson(
        val level: Int,
        val kind: Kind,
        val termIds: List<Int>,
        val questionCount: Int,
        val reviewedLevels: List<Int> = emptyList()
    ) {
        val termCount: Int get() = termIds.size
    }

    /**
     * Plan complet d'un chapitre : apprentissages et révisions entrelacés.
     * Liste vide si le chapitre n'a aucun terme.
     */
    fun plan(termIds: List<Int>): List<Lesson> {
        if (termIds.isEmpty()) return emptyList()

        // 1. Leçons d'apprentissage : 3 termes, jamais 1 seul en fin de chapitre.
        val learning = mutableListOf<Lesson>()
        var cursor = 0
        splitSizes(termIds.size).forEachIndexed { index, size ->
            learning += Lesson(
                level = index + 1,
                kind = Kind.NEW,
                termIds = termIds.subList(cursor, cursor + size).toList(),
                questionCount = minOf(NEW_LESSON_QUESTIONS, size * QUESTIONS_PER_TERM)
            )
            cursor += size
        }

        // 2. Révision après chaque paire (la dernière paire peut être incomplète).
        //    Exception : un chapitre qui ne tient qu'en une seule leçon n'a pas de
        //    révision — réviser exactement la même leçon n'apporterait rien.
        if (learning.size == 1) return learning
        val out = mutableListOf<Lesson>()
        var level = 1
        var index = 0
        while (index < learning.size) {
            val pair = learning.subList(index, minOf(index + REVIEW_EVERY, learning.size))
            val placed = pair.map { it.copy(level = level++) }
            out += placed
            val reviewed = placed.flatMap { it.termIds }
            out += Lesson(
                level = level++,
                kind = Kind.REVIEW,
                termIds = reviewed,
                questionCount = minOf(REVIEW_QUESTIONS, reviewed.size * QUESTIONS_PER_TERM),
                reviewedLevels = placed.map { it.level }
            )
            index += REVIEW_EVERY
        }
        return out
    }

    /** La leçon `level` (1-based) du chapitre, ou `null` si le niveau n'existe pas. */
    fun lessonAt(termIds: List<Int>, level: Int): Lesson? =
        plan(termIds).firstOrNull { it.level == level }

    /** Nombre total de nœuds (apprentissages + révisions) d'un chapitre. */
    fun lessonCount(termIds: List<Int>): Int = plan(termIds).size

    /**
     * Palier pédagogique (1..6) d'une leçon, pour la difficulté des questions :
     * le premier nœud est du vocabulaire (palier 1), le dernier du niveau des
     * cas cliniques (palier 6). Les questions d'un chapitre restent ainsi
     * progressives même avec 30 leçons.
     */
    fun tierFor(level: Int, lessonCount: Int): Int {
        if (lessonCount <= 1) return 1
        val position = (level - 1).coerceIn(0, lessonCount - 1)
        val step = position.toDouble() / (lessonCount - 1)
        return (1 + step * 5).toInt().coerceIn(1, 6)
    }

    /**
     * Tailles des leçons d'apprentissage pour `termCount` termes.
     * Aucune taille n'est nulle ; `4` ne donne jamais `3 + 1` mais `2 + 2`.
     */
    fun splitSizes(termCount: Int): List<Int> = when {
        termCount <= 0 -> emptyList()
        termCount <= TERMS_PER_LESSON -> listOf(termCount)
        termCount % TERMS_PER_LESSON == 0 -> List(termCount / TERMS_PER_LESSON) { TERMS_PER_LESSON }
        termCount % TERMS_PER_LESSON == 1 ->
            List(termCount / TERMS_PER_LESSON - 1) { TERMS_PER_LESSON } + listOf(2, 2)
        else -> List(termCount / TERMS_PER_LESSON) { TERMS_PER_LESSON } + 2
    }
}
