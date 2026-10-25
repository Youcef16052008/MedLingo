package com.aistudio.medlingua.dzmed.domain.lesson

import com.aistudio.medlingua.dzmed.data.local.entity.ExerciseEntity
import com.aistudio.medlingua.dzmed.data.local.entity.MedicalTermEntity
import com.aistudio.medlingua.dzmed.data.local.dao.ReviewQueueDao
import com.aistudio.medlingua.dzmed.data.local.entity.ReviewQueueItem
import com.aistudio.medlingua.dzmed.domain.path.PathBuilder
import com.aistudio.medlingua.dzmed.domain.path.NodeState
import com.aistudio.medlingua.dzmed.data.initial.InitialData

/**
 * Générateur de leçons — port Kotlin de `web-react/src/domain/lessons.ts`.
 *
 * **Le problème résolu** : 44 exercices écrits main pour 90 nœuds (15 modules ×
 * 6 niveaux). 10 modules sur 15 n'ont **aucun** exercice — leurs leçons
 * affichaient l'écran vide `no_exercises`. Ce générateur dérive les questions
 * des 4 504 fiches trilingues : il rend les 90 leçons jouables.
 *
 * Règle (spec §7) : une leçon = 8 questions
 *   1. les exercices réels du (module, niveau), au maximum 4 ;
 *   2. le reste est généré depuis les termes du module :
 *      L1/L2 → QCM EN→FR, EN→AR, FR→EN · L3/L4 → définition/étymologie + match
 *      L5 → lecture (contexte) sinon définition · L6 → cas clinique sinon traduction
 *   3. difficulté croissante : distracteurs d'abord éloignés, puis proches
 *      (similarité de Jaccard).
 *
 * Tirage déterministe (`mulberry32(hashSeed("module:niveau"))`) → la même
 * leçon à chaque visite, et la même qu'avec la version web.
 *
 * Bornes de sécurité reprises du web : pool vide → leçon vide, boucle bornée
 * (`gen < needed * 3`), repli de distracteurs, slice final.
 */
object LessonGenerator {

    const val LESSON_SIZE = 8
    const val REAL_EXERCISES_MAX = 4
    private const val MATCH_PAIRS = 4

    private enum class Kind {
        EN_FR, EN_AR, FR_EN, DEF, ETYM, READING, CLINICAL, MATCH
    }

    /**
     * Plan des kinds générés pour un niveau. L'ordre est volontairement
     * cyclique et croisé pour varier le type de question au fil de la leçon.
     */
    private fun lessonPlan(level: Int, count: Int): List<Kind> {
        if (count <= 0) return emptyList()
        if (level <= 2) return (0 until count).map { i ->
            listOf(Kind.EN_FR, Kind.EN_AR, Kind.FR_EN)[i % 3]
        }
        if (level <= 4) return (0 until count).map { i ->
            when (i % 3) {
                1 -> Kind.MATCH
                2 -> Kind.ETYM
                else -> Kind.DEF
            }
        }
        if (level == 5) return (0 until count).map { i ->
            if (i % 2 == 1) Kind.DEF else Kind.READING
        }
        return (0 until count).map { i ->
            if (i % 2 == 1) Kind.FR_EN else Kind.CLINICAL
        }
    }

    /**
     * 3 distracteurs du même module, proches de la réponse pour les questions
     * tardives (difficulté ∈ [0, 1]) et éloignés pour les premières.
     */
    private fun distractors(
        answer: String,
        pool: List<String>,
        rand: () -> Double,
        difficulty: Double
    ): List<String> {
        val normAnswer = LessonRng.norm(answer)
        // similarité décroissante : les plus proches en tête
        val scored = pool
            .filter { LessonRng.norm(it).isNotEmpty() && LessonRng.norm(it) != normAnswer }
            .map { it to LessonRng.similarity(answer, it) }
            .sortedByDescending { it.second }
        if (scored.isEmpty()) return emptyList()

        // difficulté < 0.5 → banque éloignée ; >= 0.5 → banque proche
        val size = minOf(8, scored.size)
        val pickFrom = if (difficulty < 0.5) {
            scored.takeLast(size).toMutableList()
        } else {
            scored.take(size).toMutableList()
        }
        val out = mutableListOf<String>()
        val used = mutableSetOf(normAnswer)
        while (out.size < 3 && pickFrom.isNotEmpty()) {
            val idx = (rand() * pickFrom.size).toInt()
            val candidate = pickFrom.removeAt(idx).first
            val normCandidate = LessonRng.norm(candidate)
            if (normCandidate in used) continue
            used.add(normCandidate)
            out.add(candidate)
        }
        // repli : tranche trop pauvre → on prend le reste des candidats
        if (out.size < 3) {
            for ((c) in scored) {
                if (out.size == 3) break
                val n = LessonRng.norm(c)
                if (n in used) continue
                used.add(n)
                out.add(c)
            }
        }
        return out
    }

    /**
     * Construit une question générée, ou `null` si la donnée manque sur ce
     * terme (ex. `etym` vide à 92 %, `exEn` vide à 75 %). L'appelant saute le
     * terme et conserve le kind — comme sur le web.
     */
    private fun build(
        kind: Kind,
        moduleTitle: String,
        term: MedicalTermEntity,
        level: Int,
        id: Int,
        others: List<MedicalTermEntity>,
        rand: () -> Double,
        difficulty: Double
    ): ExerciseEntity? {
        // Table de référence Android : L1..L6 → 10 / 15 / 20 / 25 / 30 / 35 XP
        val points = 10 + (level - 1) * 5
        val normFields = when (kind) {
            Kind.EN_FR -> others.map { it.termFr }
            Kind.EN_AR -> others.map { it.termAr }
            else -> others.map { it.termEn }
        }

        return when (kind) {
            Kind.EN_FR -> mcq(
                moduleTitle, term, level, id, points, rand, difficulty, normFields,
                questionEn = "What is the French translation of \"${term.termEn}\"?",
                questionFr = "Quelle est la traduction française de « ${term.termEn} » ?",
                questionAr = "ما الترجمة الفرنسية للمصطلح « ${term.termEn} »؟",
                answer = term.termFr,
                answerField = { it.termFr }
            )
            Kind.EN_AR -> {
                if (term.termAr.isBlank()) null
                else mcq(
                    moduleTitle, term, level, id, points, rand, difficulty, normFields,
                    questionEn = "What is the Arabic translation of \"${term.termEn}\"?",
                    questionFr = "Quelle est la traduction arabe de « ${term.termEn} » ?",
                    questionAr = "ما الترجمة العربية للمصطلح « ${term.termEn} »؟",
                    answer = term.termAr,
                    answerField = { it.termAr }
                )
            }
            Kind.FR_EN -> mcq(
                moduleTitle, term, level, id, points, rand, difficulty, normFields,
                questionEn = "Which English term matches \"${term.termFr}\"?",
                questionFr = "Quel est le terme anglais pour « ${term.termFr} » ?",
                questionAr = "ما المصطلح الإنجليزي المقابل لـ« ${term.termFr} »؟",
                answer = term.termEn,
                answerField = { it.termEn }
            )
            Kind.DEF -> {
                if (term.definitionFr.isBlank() && term.definitionEn.isBlank()) null
                else mcq(
                    moduleTitle, term, level, id, points, rand, difficulty, normFields,
                    questionEn = term.definitionEn.ifBlank { term.definitionFr },
                    questionFr = term.definitionFr.ifBlank { term.definitionEn },
                    questionAr = term.definitionAr.ifBlank { term.definitionEn },
                    answer = term.termEn,
                    answerField = { it.termEn }
                )
            }
            Kind.ETYM -> {
                if (term.etymology.isBlank()) null
                else mcq(
                    moduleTitle, term, level, id, points, rand, difficulty, normFields,
                    questionEn = "Which term comes from: ${term.etymology}?",
                    questionFr = "Quel terme vient de : ${term.etymology} ?",
                    questionAr = "ما المصطلح الذي مشتق من: ${term.etymology}؟",
                    answer = term.termEn,
                    answerField = { it.termEn }
                )
            }
            Kind.READING -> {
                if (term.exampleEn.isBlank()) null
                else ExerciseEntity(
                    id = id, level = level, type = "reading", difficulty = difficultyLevel(level),
                    module = moduleTitle, chapter = term.chapter,
                    questionEn = "Which term fits this context?",
                    questionFr = "Quel terme correspond à ce contexte ?",
                    questionAr = "ما المصطلح الذي يناسب هذا السياق؟",
                    optionsRaw = joinOptions(
                        LessonRng.shuffleWith(
                            listOf(term.termEn) + distractors(term.termEn, normFields, rand, difficulty),
                            rand
                        )
                    ),
                    correctAnswer = term.termEn,
                    explanationEn = term.definitionEn,
                    explanationFr = term.definitionFr,
                    explanationAr = term.definitionAr,
                    points = points,
                    contextTextEn = term.exampleEn,
                    contextTextFr = term.exampleFr.ifBlank { term.exampleEn },
                    contextTextAr = term.exampleAr.ifBlank { term.exampleEn }
                )
            }
            Kind.CLINICAL -> {
                val vignette = term.exampleEn.ifBlank { term.clinicalPearl }
                if (vignette.isBlank()) null
                else ExerciseEntity(
                    id = id, level = level, type = "clinical_case", difficulty = difficultyLevel(level),
                    module = moduleTitle, chapter = term.chapter,
                    questionEn = "Identify the term used in this clinical context.",
                    questionFr = "Identifiez le terme employé dans ce contexte clinique.",
                    questionAr = "حدّد المصطلح المستخدم في هذا السياق السريري.",
                    optionsRaw = joinOptions(
                        LessonRng.shuffleWith(
                            listOf(term.termEn) + distractors(term.termEn, normFields, rand, difficulty),
                            rand
                        )
                    ),
                    correctAnswer = term.termEn,
                    explanationEn = term.definitionEn,
                    explanationFr = term.definitionFr,
                    explanationAr = term.definitionAr,
                    points = points,
                    contextTextEn = vignette,
                    contextTextFr = term.exampleFr.ifBlank { term.clinicalPearl.ifBlank { vignette } },
                    contextTextAr = term.exampleAr.ifBlank { vignette }
                )
            }
            Kind.MATCH -> {
                // 4 paires terme → traduction FR, tirées du même module
                if (others.size < MATCH_PAIRS - 1) null
                else {
                    val pairTerms = listOf(term) + others.take(MATCH_PAIRS - 1)
                    val options = pairTerms.map { "${it.termEn}:${it.termFr}" }
                    ExerciseEntity(
                        id = id, level = level, type = "matching", difficulty = difficultyLevel(level),
                        module = moduleTitle, chapter = term.chapter,
                        questionEn = "Match each term with its French translation.",
                        questionFr = "Associez chaque terme à sa traduction française.",
                        questionAr = "صِل كل مصطلح بترجمته الفرنسية.",
                        optionsRaw = options.joinToString("|"),
                        correctAnswer = options.joinToString("  |  "),
                        explanationEn = term.definitionEn,
                        explanationFr = term.definitionFr,
                        explanationAr = term.definitionAr,
                        points = points
                    )
                }
            }
        }
    }

    /** Fabrique un QCM (options mélangées, réponse insérée). */
    private fun mcq(
        moduleTitle: String,
        term: MedicalTermEntity,
        level: Int,
        id: Int,
        points: Int,
        rand: () -> Double,
        difficulty: Double,
        normFields: List<String>,
        questionEn: String,
        questionFr: String,
        questionAr: String,
        answer: String,
        answerField: (MedicalTermEntity) -> String
    ): ExerciseEntity {
        val options = LessonRng.shuffleWith(
            listOf(answer) + distractors(answer, normFields, rand, difficulty),
            rand
        )
        return ExerciseEntity(
            id = id, level = level, type = "mcq", difficulty = difficultyLevel(level),
            module = moduleTitle, chapter = term.chapter,
            questionEn = questionEn, questionFr = questionFr, questionAr = questionAr,
            optionsRaw = joinOptions(options),
            correctAnswer = answer,
            explanationEn = term.definitionEn,
            explanationFr = term.definitionFr,
            explanationAr = term.definitionAr,
            points = points
        )
    }

    /** Les options sont séparées par `|` (format historique de `getOptionsList`). */
    private fun joinOptions(options: List<String>): String = options.joinToString("|")

    private fun difficultyLevel(level: Int): String = when (level) {
        1, 2 -> "beginner"
        3, 4 -> "intermediate"
        else -> "advanced"
    }

    /**
     * Les 8 questions d'une leçon (ordre conservé : difficulté croissante).
     * Déterministe pour un couple (moduleId, level) donné.
     *
     * @param moduleId id stable du module ("anat", "physio"…)
     * @param level 1..6
     * @param realExercises exercices écrits main du (module, niveau)
     * @param terms termes du module (source des questions générées)
     * @return liste d'au plus [LESSON_SIZE] exercices ; vide si le module est inconnu
     */
    fun lessonQuestions(
        moduleId: String,
        level: Int,
        realExercises: List<ExerciseEntity>,
        terms: List<MedicalTermEntity>
    ): List<ExerciseEntity> {
        if (level < 1 || level > 6) return emptyList()

        val moduleTitle = InitialData.modulesList
            .firstOrNull { it.id == moduleId }?.titleFr
            ?: return emptyList()
        if (terms.isEmpty()) return emptyList()

        val rand = LessonRng.mulberry32(LessonRng.hashSeed("$moduleId:$level"))

        // 1. exercices réels du (module, niveau), au plus 4
        val real = realExercises.filter { ex ->
            ex.module.equals(moduleTitle, ignoreCase = true) && ex.level == level
        }.take(REAL_EXERCISES_MAX)

        // 2. complétion générée
        val needed = maxOf(0, LESSON_SIZE - real.size)
        if (needed == 0) return real.take(LESSON_SIZE)
        val plan = lessonPlan(level, needed)
        val pool = LessonRng.shuffleWith(terms, rand).toMutableList()

        val out = real.toMutableList()
        var cursor = 0
        var gen = 0

        while (out.size < LESSON_SIZE && gen < needed * 3) {
            val kind = plan.getOrNull(gen) ?: Kind.FR_EN
            val term = pool[cursor % pool.size]
            val others = pool.filter { it.id != term.id }
            // ids négatifs pour ne jamais collisionner avec les exercices
            // écrits main (Room autoGenerate démarre à 1) — miroir du web
            // (`-1_000_000 - hash % 8_000_000`). `hashSeed` étant non signé en
            // JS, on normalise en Long positif avant modulo.
            val id = -1_000_000 - ((LessonRng.hashSeed("$moduleId:$level:$gen").toLong() and 0xFFFFFFFFL) % 8_000_000L).toInt()
            val difficulty = if (needed > 1) gen.toDouble() / (needed - 1) else 1.0

            val ex = build(kind, moduleTitle, term, level, id, others, rand, difficulty)
            if (ex != null) {
                out.add(ex)
                gen += 1
                cursor += if (kind == Kind.MATCH) MATCH_PAIRS else 1
            } else {
              // donnée manquante sur ce terme → on avance d'un terme, même kind
              gen += 1
              cursor += 1
              if (cursor > pool.size * 2) break
            }
        }

        // 3. sécurité : on ne rend jamais plus de LESSON_SIZE questions
        return out.take(LESSON_SIZE)
    }

    /** Score d'une leçon (0-100) à partir du nombre de bonnes réponses. */
    fun lessonScore(correct: Int, total: Int): Int {
        if (total <= 0) return 0
        return ((correct.toDouble() / total) * 100).roundToInt()
    }

    private fun Double.roundToInt(): Int = Math.round(this).toInt()
}

/**
 * v2 — architecture Module → Chapter → Lesson (nouveau générateur).
 * Port de web-react v2 : newLesson/chapter + reviewLesson.
 */
object LessonGeneratorV2 {

    const val HEARTS_MAX = 5
    const val LESSON_SIZE = 8
    const val REVIEW_SIZE = 16

    enum class LessonKind { NEW, REVIEW }

    data class LessonNode(
        val chapterId: String,
        val levelIndex: Int,
        val kind: LessonKind,
        val title: String,
        val state: NodeState,
        val questionCount: Int,
        val answered: Int = 0,
        val hearts: Int = HEARTS_MAX
    )

    private enum class Kind { EN_FR, EN_AR, FR_EN, DEF, ETYM, READING, CLINICAL, MATCH }

    private fun lessonPlan(level: Int, count: Int): List<Kind> {
        if (count <= 0) return emptyList()
        if (level <= 2) return (0 until count).map { i -> listOf(Kind.EN_FR, Kind.EN_AR, Kind.FR_EN)[i % 3] }
        if (level <= 4) return (0 until count).map { i ->
            when (i % 3) { 1 -> Kind.MATCH; 2 -> Kind.ETYM; else -> Kind.DEF }
        }
        if (level == 5) return (0 until count).map { i -> if (i % 2 == 1) Kind.DEF else Kind.READING }
        return (0 until count).map { i -> if (i % 2 == 1) Kind.FR_EN else Kind.CLINICAL }
    }

    private fun distractors(answer: String, pool: List<String>, rand: () -> Double, difficulty: Double): List<String> {
        val scored = pool.filter { it.isNotBlank() && LessonRng.norm(it) != LessonRng.norm(answer) }
            .map { it to LessonRng.similarity(answer, it) }
            .sortedByDescending { it.second }
        if (scored.isEmpty()) return emptyList()
        val size = minOf(8, scored.size)
        val pickFrom = if (difficulty < 0.5) scored.takeLast(size).toMutableList() else scored.take(size).toMutableList()
        val out = mutableListOf<String>(); val used = mutableSetOf(LessonRng.norm(answer))
        while (out.size < 3 && pickFrom.isNotEmpty()) {
            val idx = (rand() * pickFrom.size).toInt()
            val cand = pickFrom.removeAt(idx).first
            val n = LessonRng.norm(cand)
            if (n in used) continue
            used.add(n); out.add(cand)
        }
        if (out.size < 3) for ((c) in scored) { if (out.size == 3) break; val n = LessonRng.norm(c); if (n in used) continue; used.add(n); out.add(c) }
        return out
    }

    private fun build(kind: Kind, moduleTitle: String, term: MedicalTermEntity, level: Int, id: Int, others: List<MedicalTermEntity>, rand: () -> Double, difficulty: Double): ExerciseEntity? {
        val points = 10 + (level - 1) * 5
        val normFields = when (kind) {
            Kind.EN_FR -> others.map { it.termFr }
            Kind.EN_AR -> others.map { it.termAr }
            else -> others.map { it.termEn }
        }
        return when (kind) {
            Kind.EN_FR -> mcq(moduleTitle, term, level, id, points, rand, difficulty, normFields,
                questionEn = "What is the French translation of \"${term.termEn}\"?",
                questionFr = "Quelle est la traduction française de « ${term.termEn} » ?",
                questionAr = "ما الترجمة الفرنسية المصطلح « ${term.termEn} »؟", answer = term.termFr, answerField = { it.termFr })
            Kind.EN_AR -> if (term.termAr.isBlank()) null else mcq(moduleTitle, term, level, id, points, rand, difficulty, normFields,
                questionEn = "What is the Arabic translation of \"${term.termEn}\"?",
                questionFr = "Quelle est la traduction arabe de « ${term.termEn} » ?",
                questionAr = "ما الترجمة العربية المصطلح « ${term.termEn} »؟", answer = term.termAr, answerField = { it.termAr })
            Kind.FR_EN -> mcq(moduleTitle, term, level, id, points, rand, difficulty, normFields,
                questionEn = "Which English term matches \"${term.termFr}\"?",
                questionFr = "Quel est le terme anglais pour « ${term.termFr} » ?",
                questionAr = "ما المصطلح الإنجليزي المقابل لـ« ${term.termFr} »؟", answer = term.termEn, answerField = { it.termEn })
            Kind.DEF -> if (term.definitionFr.isBlank() && term.definitionEn.isBlank()) null else mcq(moduleTitle, term, level, id, points, rand, difficulty, normFields,
                questionEn = term.definitionEn.ifBlank { term.definitionFr },
                questionFr = term.definitionFr.ifBlank { term.definitionEn },
                questionAr = term.definitionAr.ifBlank { term.definitionEn }, answer = term.termEn, answerField = { it.termEn })
            Kind.ETYM -> if (term.etymology.isBlank()) null else mcq(moduleTitle, term, level, id, points, rand, difficulty, normFields,
                questionEn = "Which term comes from: ${term.etymology}?",
                questionFr = "Quel terme vient de : ${term.etymology} ?",
                questionAr = "ما المصطلح qui مشتق من: ${term.etymology}؟", answer = term.termEn, answerField = { it.termEn })
            Kind.READING -> if (term.exampleEn.isBlank()) null else ExerciseEntity(
                id = id, level = level, type = "reading", difficulty = difficultyLevel(level),
                module = moduleTitle, chapter = term.chapter,
                questionEn = "Which term fits this context?", questionFr = "Quel terme correspond à ce contexte ?",
                questionAr = "ما المصطلح qui يناسب هذا السياق؟",
                optionsRaw = joinOptions(LessonRng.shuffleWith(listOf(term.termEn) + distractors(term.termEn, normFields, rand, difficulty), rand)),
                correctAnswer = term.termEn, explanationEn = term.definitionEn, explanationFr = term.definitionFr,
                explanationAr = term.definitionAr, points = points,
                contextTextEn = term.exampleEn, contextTextFr = term.exampleFr.ifBlank { term.exampleEn },
                contextTextAr = term.exampleAr.ifBlank { term.exampleEn })
            Kind.CLINICAL -> {
                val vignette = term.exampleEn.ifBlank { term.clinicalPearl }
                if (vignette.isBlank()) null else ExerciseEntity(
                    id = id, level = level, type = "clinical_case", difficulty = difficultyLevel(level),
                    module = moduleTitle, chapter = term.chapter,
                    questionEn = "Identify the term used in this clinical context.",
                    questionFr = "Identifiez le terme employé dans ce contexte clinique.",
                    questionAr = "حدّد المصطلح المستخدم في ce contexte السريري.",
                    optionsRaw = joinOptions(LessonRng.shuffleWith(listOf(term.termEn) + distractors(term.termEn, normFields, rand, difficulty), rand)),
                    correctAnswer = term.termEn, explanationEn = term.definitionEn, explanationFr = term.definitionFr,
                    explanationAr = term.definitionAr, points = points,
                    contextTextEn = vignette, contextTextFr = term.exampleFr.ifBlank { term.clinicalPearl.ifBlank { vignette } },
                    contextTextAr = term.exampleAr.ifBlank { vignette })
            }
            Kind.MATCH -> {
                if (others.size < 3 - 1) null else {
                    val pairTerms = listOf(term) + others.take(3 - 1)
                    val options = pairTerms.map { "${it.termEn}:${it.termFr}" }
                    ExerciseEntity(
                        id = id, level = level, type = "matching", difficulty = difficultyLevel(level),
                        module = moduleTitle, chapter = term.chapter,
                        questionEn = "Match each term with its French translation.",
                        questionFr = "Associez chaque terme à sa traduction française.",
                        questionAr = "صِل كل مصطلح بترجمته الفرنسية.",
                        optionsRaw = options.joinToString("|"),
                        correctAnswer = options.joinToString("  |  "),
                        explanationEn = term.definitionEn, explanationFr = term.definitionFr,
                        explanationAr = term.definitionAr, points = points)
                }
            }
        }
    }

    private fun mcq(moduleTitle: String, term: MedicalTermEntity, level: Int, id: Int, points: Int, rand: () -> Double, difficulty: Double, normFields: List<String>, questionEn: String, questionFr: String, questionAr: String, answer: String, answerField: (MedicalTermEntity) -> String): ExerciseEntity {
        val options = LessonRng.shuffleWith(listOf(answer) + distractors(answer, normFields, rand, difficulty), rand)
        return ExerciseEntity(
            id = id, level = level, type = "mcq", difficulty = difficultyLevel(level),
            module = moduleTitle, chapter = term.chapter,
            questionEn = questionEn, questionFr = questionFr, questionAr = questionAr,
            optionsRaw = joinOptions(options), correctAnswer = answer,
            explanationEn = term.definitionEn, explanationFr = term.definitionFr,
            explanationAr = term.definitionAr, points = points)
    }

    private fun joinOptions(options: List<String>) = options.joinToString("|")
    private fun difficultyLevel(level: Int) = when (level) { 1, 2 -> "beginner"; 3, 4 -> "intermediate"; else -> "advanced" }

    private fun lessonMapForTesting(): Map<String, Map<String, Any>> {
        // Mock : vide pour l'instant (à remplir avec lesson_map.json)
        return emptyMap()
    }

    /** Définition d'une entrée de lesson_map.json. */
    private data class LessonMapEntry(
        val kind: String,
        val termIds: List<Int> = emptyList(),
        val reviewedLessonIds: List<String> = emptyList(),
        val questionCount: Int = 8
    )

    /** Charge le lesson_map.json depuis l'assets (déterministe, même sur tous les appareils). */
    fun loadLessonMap() {
        // À implémenter avec le chargement du JSON depuis assets/public/data
        // Pour l'instant : mock vide (la fonction de génération fonctionne sans)
    }

    /** Génère une leçon NUEAU : 3 termes × 8 Q, tirage déterministe par (moduleId, levelIndex). */
    fun newLesson(moduleId: String, levelIndex: Int): List<ExerciseEntity> {
        // 1. Récupérer les termes du module
        val moduleTitle = InitialData.modulesList
            .firstOrNull { it.id == moduleId }?.titleFr
            ?: return emptyList()
        val terms = InitialData.termsOfModule(moduleTitle)
        if (terms.size < 3) return emptyList()

        // 2. Sélectionner 3 termes de façon déterministe (par levelIndex)
        val termPool = terms.toList()
        val start = (levelIndex * 3) % (termPool.size - 2)
        val selectedTerms = termPool.slice(start..<start + 3)

        // 3. Générer 8 questions
        val rand = LessonRng.mulberry32(LessonRng.hashSeed("$moduleId:level:$levelIndex:lesson"))
        val lessons = mutableListOf<ExerciseEntity>()

        // Exercices réels (module, niveau) - au plus 4
        val realExercises = InitialData.exercises.filter { ex ->
            ex.module.equals(moduleTitle, ignoreCase = true) && ex.level == levelIndex + 1
        }.take(4)

        val needed = maxOf(0, 8 - realExercises.size)
        if (needed > 0) {
            val plan = lessonPlan(levelIndex + 1, needed)
            val pool = selectedTerms.toList()
            var cursor = 0
            var gen = 0
            while (lessons.size < 8 && gen < needed * 3) {
                val kind = plan.getOrNull(gen) ?: Kind.FR_EN
                val term = pool[cursor % pool.size]
                val others = pool.filter { it.id != term.id }
                val id = -1_000_000 - ((LessonRng.hashSeed("$moduleId:$levelIndex:$gen").toLong() and 0xFFFFFFFFL) % 8_000_000L).toInt()
                val difficulty = if (needed > 1) gen.toDouble() / (needed - 1) else 1.0

                val ex = build(kind, moduleTitle, term, levelIndex + 1, id, others, rand, difficulty)
                if (ex != null) {
                    lessons.add(ex)
                    gen += 1
                    cursor += if (kind == Kind.MATCH) 3 else 1
                } else {
                    cursor += 1
                    if (cursor > pool.size * 2) break
                }
            }
        }

        // Générer des questions supplémentaires pour atteindre 8
        // Les exercices réels précèdent les questions générées
        val out = realExercises.toMutableList()
        val generatedToUse = lessons.take(maxOf(0, 8 - out.size))
        out.addAll(generatedToUse)
        return out
    }

    /** Génère une leçon REVIEW : 0 terme, 16 Q tirés des 2 leçons précédentes.
     *
     * @param reviewQueueDao DAO pour accéder à la file de révision (pour les ids ratés)
     */
    suspend fun reviewLesson(
        reviewIndex: Int,
        chapterId: String,
        reviewQueueDao: ReviewQueueDao? = null
    ): List<ExerciseEntity> {
        val lessonMap = lessonMapForTesting()

        // Récupérer les entrées des 2 leçons précédentes
        val prev1Id = "$chapterId-l${reviewIndex}"
        val prev2Id = "$chapterId-l${reviewIndex + 1}"

        val prev1 = lessonMap[prev1Id]
        val prev2 = lessonMap[prev2Id]
        if (prev1 == null || prev2 == null) return emptyList()

        // Récupérer les termes des leçons précédentes
        val terms1 = prev1["termIds"] as? List<Int> ?: return emptyList()
        val terms2 = prev2["termIds"] as? List<Int> ?: return emptyList()

        // Récupérer les termes correspondants
        val allTerms = InitialData.terms
        val termById = { id: Int -> allTerms.find { it.id == id } }

        // Générer 3 exercices par terme pour la leçon 1
        val exercises1 = mutableListOf<ExerciseEntity>()
        terms1.forEach { id ->
            val term = termById(id) ?: return@forEach
            exercises1.addAll(listOf(
                ExerciseEntity(
                    id = -3_000_000 - (term.id * 3),
                    level = reviewIndex + 1,
                    type = "mcq",
                    difficulty = "intermediate",
                    module = term.module,
                    chapter = term.chapter,
                    questionEn = "What is the French translation of \"${term.termEn}\"?",
                    questionFr = "Quelle est la traduction française de « ${term.termEn} » ?",
                    questionAr = "ما الترجمة الفرنسية المصطلح « ${term.termEn} »؟",
                    optionsRaw = "${term.termFr}|${term.termFr}|${term.termEn}",
                    correctAnswer = term.termFr,
                    explanationEn = term.definitionEn,
                    explanationFr = term.definitionFr,
                    explanationAr = term.definitionAr,
                    points = 15 + reviewIndex
                ),
                ExerciseEntity(
                    id = -3_000_000 - (term.id * 3 + 1),
                    level = reviewIndex + 1,
                    type = "mcq",
                    difficulty = "intermediate",
                    module = term.module,
                    chapter = term.chapter,
                    questionEn = "What is the Arabic translation of \"${term.termEn}\"?",
                    questionFr = "Quelle est la traduction arabe de « ${term.termEn} » ?",
                    questionAr = "ما الترجمة العربية المصطلح « ${term.termEn} »؟",
                    optionsRaw = "${term.termAr}|${term.termAr}|${term.termEn}",
                    correctAnswer = term.termAr,
                    explanationEn = term.definitionEn,
                    explanationFr = term.definitionFr,
                    explanationAr = term.definitionAr,
                    points = 15 + reviewIndex
                ),
                ExerciseEntity(
                    id = -3_000_000 - (term.id * 3 + 2),
                    level = reviewIndex + 1,
                    type = "mcq",
                    difficulty = "intermediate",
                    module = term.module,
                    chapter = term.chapter,
                    questionEn = "Which English term matches « ${term.termFr} » ?",
                    questionFr = "Quel est le terme anglais pour « ${term.termFr} » ?",
                    questionAr = "ما المصطلح الإنجليزي المقابل لـ« ${term.termFr} »؟",
                    optionsRaw = "${term.termEn}|${term.termEn}|${term.termFr}",
                    correctAnswer = term.termEn,
                    explanationEn = term.definitionEn,
                    explanationFr = term.definitionFr,
                    explanationAr = term.definitionAr,
                    points = 15 + reviewIndex
                )
            ))
        }

        // Générer 3 exercices par terme pour la leçon 2
        val exercises2 = mutableListOf<ExerciseEntity>()
        terms2.forEach { id ->
            val term = termById(id) ?: return@forEach
            exercises2.add(
                ExerciseEntity(
                    id = -3_000_000 - (term.id * 3),
                    level = reviewIndex + 1,
                    type = "mcq",
                    difficulty = "intermediate",
                    module = term.module,
                    chapter = term.chapter,
                    questionEn = "What is the French translation of \"${term.termEn}\"?",
                    questionFr = "Quelle est la traduction française de « ${term.termEn} » ?",
                    questionAr = "ما الترجمة الفرنسية المصطلح « ${term.termEn} »؟",
                    optionsRaw = "${term.termFr}|${term.termFr}|${term.termEn}",
                    correctAnswer = term.termFr,
                    explanationEn = term.definitionEn,
                    explanationFr = term.definitionFr,
                    explanationAr = term.definitionAr,
                    points = 15 + reviewIndex
                )
            )
        }

        // Sélectionner 16 questions : d'abord les ratés (missés), puis le reste
        // La file de révision tracke les ids des questions ratées (missedExIds)
        val allExercises = exercises1 + exercises2
        val missed = mutableListOf<ExerciseEntity>()
        val remaining = mutableListOf<ExerciseEntity>()
        // Récupérer les ids des questions ratées depuis la queue de révision
        val missedExIds: List<Int> = if (reviewQueueDao != null) {
            val reviewQueueItem = reviewQueueDao.getReviewById("$chapterId-l$reviewIndex")
            if (reviewQueueItem != null) reviewQueueItem.missedExIds else emptyList()
        } else {
            emptyList()
        }
        if (missedExIds.isNotEmpty()) {
            missed.addAll(allExercises.filter { ex -> missedExIds.contains(ex.id) })
        }
        // Si aucun raté connu, prendre les 10 premières comme tentative
        if (missed.isEmpty()) {
            missed.addAll(allExercises.take(10))
        }
        remaining.addAll(allExercises.drop(missed.size))
        val seed = LessonRng.hashSeed("review:$reviewIndex")
        val random = java.util.Random(seed.toLong())
        val selected = (missed + remaining).shuffled(random).take(16)

        return selected
    }
}
