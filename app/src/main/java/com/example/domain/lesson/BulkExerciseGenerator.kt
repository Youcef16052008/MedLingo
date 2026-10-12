package com.example.domain.lesson

import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.MedicalTermEntity

/**
 * Générateur en masse — 3 exercices par terme, pour chaque terme de chaque module.
 *
 * L'app n'avait que 112 exercices écrits main, dont 68 % d'anatomie ; 10 modules
 * sur 15 n'en avaient aucun. Ce générateur résout le problème à la racine : il
 * dérive les questions des 4 504 fiches trilingues, ce qui donne
 * 4 504 × 3 = 13 512 exercices — soit plus que les 10 000 visés, et couvre
 * les 15 modules de façon homogène.
 *
 * ## Les 13 templates
 *
 * | # | Kind            | type ExerciseEntity | Nécessite              |
 * |---|-----------------|---------------------|------------------------|
 * | 1 | EN_FR           | mcq                 | termFr (100 %)         |
 * | 2 | EN_AR           | mcq                 | termAr (100 %)         |
 * | 3 | FR_EN           | mcq                 | termEn (100 %)         |
 * | 4 | DEFINITION      | mcq                 | definitionEn (100 %)   |
 * | 5 | ETYMOLOGY       | mcq                 | etymology (22,7 %)     |
 * | 6 | PRONUNCIATION   | mcq                 | ipaPhonetic (100 %)    |
 * | 7 | CLINICAL_PEARL  | clinical_case       | clinicalPearl (10 %)   |
 * | 8 | MNEMONIC        | mcq                 | mnemonic (3,4 %)       |
 * | 9 | READING         | reading             | exampleEn (85,5 %)     |
 * | 10| CLINICAL_CASE   | clinical_case       | exampleEn (85,5 %)     |
 * | 11| MATCHING        | matching            | ≥ 4 termes/module      |
 * | 12| WORD_ORDER      | sentence_order      | exampleEn (85,5 %)     |
 * | 13| FILL_BLANK      | fill_blank          | exampleEn (85,5 %)     |
 *
 * ## Choix des 3 exercices par terme
 *
 * Le tirage est **déterministe** (mulberry32 sur un seed stable par terme) donc
 * identique à chaque exécution et entre Android et web. Pour chaque terme, on
 * calcule quels templates sont jouables (données présentes), puis on en prend
 * exactement 3 : le template prioritaire de chaque slot est choisi en fonction
 * de la rareté des données (les templates rares — étymologie, pearl, mnémonique
 * — sont privilégiés quand ils existent, pour ne jamais les gaspiller).
 *
 * ## Contrats respectés
 *
 * - `optionsRaw` est une liste **pipe-separated** (contract `getOptionsList()`).
 * - `correctAnswer` est **toujours** un élément exact de `optionsRaw`
 *   (case-insensitive) pour les QCM, sinon `McqQuestionView` marque l'exercice
 *   faux même si l'utilisateur a la bonne réponse.
 * - `matching` : `optionsRaw` = `termeGauche:termeDroit|…`, `correctAnswer` =
 *   `optionsRaw` (l'UI compare les paires une à une via `MatchScoring`).
 * - `sentence_order` : `optionsRaw` = mots mêlés, `correctAnswer` = phrase
 *   complète (l'UI découpe sur les espaces et compare la phrase reconstruite).
 * - `fill_blank` : `optionsRaw` = réponse seule. Les deux plateformes traitent ce
 *   type en saisie libre : Android via `ExerciseSpec.Fill.acceptedAnswers`
 *   (optionsRaw ignoré), le web via `fillScore(q.fillVal, ex.answer)`.
 *   Voir `FILL_BLANK` plus bas.
 * - `reading`/`clinical_case` : le passage/vignette va dans `contextTextEn/Fr/Ar`.
 * - `points` suit la table Android : L1=10, L2=15, L3=20, L4=25, L5=30, L6=35.
 *
 * ## Bornes de sécurité
 *
 * Le générateur ne plante jamais sur une donnée manquante : il saute le template.
 * Les distracteurs viennent du même module, sont normalisés et dédupliqués
 * (sinon on aurait 2 options identiques, et la réponse serait ambiguë).
 */
object BulkExerciseGenerator {

    /** Nombre d'exercices produits pour chaque terme. */
    const val EXERCISES_PER_TERM = 3

    /** Nombre d'options d'un QCM (réponse + 3 distracteurs). */
    private const val MCQ_OPTIONS = 4

    /** Nombre de paires d'un exercice d'appariement. */
    private const val MATCH_PAIRS = 4

    /**
     * Les 13 templates de questions. L'ordre du `enum` est l'ordre de priorité
     * utilisé pour remplir les 3 slots — les kinds rares d'abord.
     */
    private enum class Kind {
        ETYMOLOGY, CLINICAL_PEARL, MNEMONIC, WORD_ORDER, FILL_BLANK,
        DEFINITION, PRONUNCIATION, READING, CLINICAL_CASE, EN_FR, EN_AR, FR_EN, MATCHING
    }

    /**
     * Associe à chaque kind la fonction qui dit s'il est jouable sur ce terme.
     * Un kind dont la donnée manque n'est jamais généré — c'est ce qui garantit
     * qu'aucun exercice n'a un énoncé ou une réponse vide.
     */
    private fun isPlayable(kind: Kind, term: MedicalTermEntity, moduleSize: Int): Boolean = when (kind) {
        Kind.EN_FR          -> term.termFr.isNotBlank()
        Kind.EN_AR          -> term.termAr.isNotBlank()
        Kind.FR_EN          -> term.termEn.isNotBlank() && term.termFr.isNotBlank()
        Kind.DEFINITION     -> term.definitionEn.isNotBlank()
        Kind.ETYMOLOGY      -> term.etymology.isNotBlank()
        Kind.PRONUNCIATION  -> term.ipaPhonetic.isNotBlank()
        Kind.CLINICAL_PEARL -> term.clinicalPearl.isNotBlank()
        Kind.MNEMONIC       -> term.mnemonic.isNotBlank()
        Kind.READING        -> term.exampleEn.isNotBlank()
        Kind.CLINICAL_CASE  -> term.exampleEn.isNotBlank()
        Kind.MATCHING       -> moduleSize >= MATCH_PAIRS && term.termFr.isNotBlank()
        Kind.WORD_ORDER     -> term.exampleEn.isNotBlank()
        Kind.FILL_BLANK     -> term.exampleEn.isNotBlank()
    }

    /** Niveau d'apprentissage associé à chaque kind (1..6). */
    private fun levelFor(kind: Kind): Int = when (kind) {
        Kind.EN_FR, Kind.EN_AR, Kind.FR_EN, Kind.PRONUNCIATION -> 1
        Kind.DEFINITION, Kind.MATCHING -> 2
        Kind.FILL_BLANK, Kind.ETYMOLOGY -> 3
        Kind.WORD_ORDER, Kind.READING -> 4
        Kind.CLINICAL_CASE, Kind.CLINICAL_PEARL -> 5
        Kind.MNEMONIC -> 6
    }

    /** Difficulté affichée (cohérente avec `LessonGenerator.difficultyLevel`). */
    private fun difficultyFor(level: Int): String = when (level) {
        1, 2 -> "beginner"
        3, 4 -> "intermediate"
        else -> "advanced"
    }

    /** Points : table de référence Android L1..L6 → 10/15/20/25/30/35. */
    private fun pointsFor(level: Int): Int = 10 + (level - 1) * 5

    // ============================================================
    // API publique
    // ============================================================

    /**
     * Génère les exercices d'un module entier.
     *
     * @param moduleTitle titre exact du module (champ `module` des termes)
     * @param terms termes du module (au moins 1)
     * @return exactement `[EXERCISES_PER_TERM]` exercices par terme
     */
    fun generateModule(
        moduleTitle: String,
        terms: List<MedicalTermEntity>
    ): List<ExerciseEntity> {
        if (terms.isEmpty()) return emptyList()
        val out = ArrayList<ExerciseEntity>(terms.size * EXERCISES_PER_TERM)
        terms.forEach { term -> out += generateForTerm(moduleTitle, term, terms) }
        return out
    }

    /**
     * Génère tous les exercices de tous les modules.
     *
     * Les ids négatifs (`stableId`) garantissent qu'aucun exercice
     * généré ne collisionne avec un exercice écrit main (Room autoGenerate
     * démarre à 1), ce qui permet aux deux familles de coexister en base.
     */
    fun generateAll(
        modules: List<Pair<String, List<MedicalTermEntity>>>
    ): List<ExerciseEntity> {
        val out = ArrayList<ExerciseEntity>()
        modules.forEach { (moduleTitle, terms) ->
            out += generateModule(moduleTitle, terms)
        }
        return out
    }

    /** Type d'UI produit par un kind (sert à la diversité des exercices). */
    private fun typeOfKind(kind: Kind): String = when (kind) {
        Kind.EN_FR, Kind.EN_AR, Kind.FR_EN, Kind.DEFINITION, Kind.ETYMOLOGY,
        Kind.PRONUNCIATION, Kind.MNEMONIC -> "mcq"
        Kind.CLINICAL_PEARL, Kind.CLINICAL_CASE -> "clinical_case"
        Kind.READING -> "reading"
        Kind.MATCHING -> "matching"
        Kind.WORD_ORDER -> "sentence_order"
        Kind.FILL_BLANK -> "fill_blank"
    }

    /**
     * Génère exactement [EXERCISES_PER_TERM] exercices pour un terme.
     *
     * Le tirage des templates est déterministe sur un seed stable par terme :
     * `mulberry32(hashSeed("bulk:<module>:<termId>"))`. La même exécution donne
     * donc toujours les mêmes 3 exercices, ce qui rend la base stable entre
     * deux lancements et permet de la régénérer sans changer les ids.
     *
     * Quand un kind rare est jouable, il est prioritaire (un pearl ne se trouve
     * que sur 10 % des termes — ne pas le générer quand il existe le gaspillerait
     * à jamais). Les kinds communs (traductions) garantissent le repli : avec
     * termEn/Fr/Ar à 100 % de remplissage, tout terme produit ses 3 exercices.
     */
    private fun generateForTerm(
        moduleTitle: String,
        term: MedicalTermEntity,
        moduleTerms: List<MedicalTermEntity>
    ): List<ExerciseEntity> {
        // Distracteurs tirés du même module (hors terme courant), déterministes.
        val rand = LessonRng.mulberry32(
            LessonRng.hashSeed("bulk:$moduleTitle:${term.id}")
        )

        val playable = Kind.values().filter {
            isPlayable(it, term, moduleTerms.size)
        }
        if (playable.isEmpty()) return emptyList()

        // Ordre de visite mélangé de façon déterministe pour que deux termes
        // d'un même module ne tombent pas sur les 3 mêmes templates — mais
        // l'ordre du `enum` (rare d'abord) reste la priorité de premier passage.
        val visited = LessonRng.shuffleWith(playable, rand).toMutableList()

        val chosen = mutableListOf<ExerciseEntity>()
        val usedKinds = mutableSetOf<Kind>()
        val usedTypes = mutableSetOf<String>()

        /**
         * Construit un exercice et l'accepte s'il est non-nul et que son type
         * d'UI est encore absent (premier passage) ou sans contrainte de type
         * (second passage). Marque le kind comme utilisé **uniquement quand
         * l'exercice est accepté** : un kind refusé pour doublon de type doit
         * rester disponible au second passage, sinon on consume un kind sans
         * produire d'exercice et certains termes n'atteignent plus les 3
         * exercices obligatoires (ex. 17 termes de l'Anatomie avec 6 kinds
         * jouables mais seulement 2 exercices générés).
         */
        fun tryBuild(
            kind: Kind,
            requireNewType: Boolean
        ): Boolean {
            if (kind in usedKinds) return false
            val ex = build(kind, moduleTitle, term, moduleTerms, rand) ?: return false
            if (requireNewType && usedTypes.contains(ex.type)) return false
            chosen += ex
            usedKinds += kind
            usedTypes += ex.type
            return true
        }

        // 1. Premier passage : un exercice par kind rare jouable (priorité),
        //    en privilégiant la diversité des types d'UI.
        for (kind in Kind.values()) {
            if (chosen.size >= EXERCISES_PER_TERM) break
            if (kind in visited) tryBuild(kind, requireNewType = true)
        }

        // 2. Complétion : on reprend les kinds restants, type déjà vu ou pas.
        //    Priorité aux types encore absents, puis n'importe quel kind.
        val remaining = visited.filter { it !in usedKinds }.sortedBy { kind ->
            if (usedTypes.contains(typeOfKind(kind))) 1 else 0
        }
        for (kind in remaining) {
            if (chosen.size >= EXERCISES_PER_TERM) break
            tryBuild(kind, requireNewType = false)
        }

        return chosen.take(EXERCISES_PER_TERM)
    }

    // ============================================================
    // Fabrique par template
    // ============================================================

    /** Construit un exercice, ou `null` si la donnée manque finalement. */
    private fun build(
        kind: Kind,
        moduleTitle: String,
        term: MedicalTermEntity,
        moduleTerms: List<MedicalTermEntity>,
        rand: () -> Double
    ): ExerciseEntity? {
        val level = levelFor(kind)
        val points = pointsFor(level)
        val difficulty = difficultyFor(level)

        return when (kind) {
            Kind.EN_FR -> mcq(
                moduleTitle, term, level, points, difficulty, rand,
                pool = moduleTerms.map { it.termFr },
                questionEn = "What is the French translation of \"${term.termEn}\"?",
                questionFr = "Quelle est la traduction française de « ${term.termEn} » ?",
                questionAr = "ما الترجمة الفرنسية للمصطلح « ${term.termEn} »؟",
                answer = term.termFr,
                kindOrdinal = kind.ordinal
            )
            Kind.EN_AR -> mcq(
                moduleTitle, term, level, points, difficulty, rand,
                pool = moduleTerms.map { it.termAr },
                questionEn = "What is the Arabic translation of \"${term.termEn}\"?",
                questionFr = "Quelle est la traduction arabe de « ${term.termEn} » ?",
                questionAr = "ما الترجمة العربية للمصطلح « ${term.termEn} »؟",
                answer = term.termAr,
                kindOrdinal = kind.ordinal
            )
            Kind.FR_EN -> mcq(
                moduleTitle, term, level, points, difficulty, rand,
                pool = moduleTerms.map { it.termEn },
                questionEn = "Which English term matches \"${term.termFr}\"?",
                questionFr = "Quel est le terme anglais pour « ${term.termFr} » ?",
                questionAr = "ما المصطلح الإنجليزي المقابل لـ« ${term.termFr} »؟",
                answer = term.termEn,
                kindOrdinal = kind.ordinal
            )
            Kind.DEFINITION -> mcq(
                moduleTitle, term, level, points, difficulty, rand,
                pool = moduleTerms.map { it.termEn },
                questionEn = "Which term is defined as: ${term.definitionEn}?",
                questionFr = "Quel terme correspond à la définition : ${term.definitionFr}?",
                questionAr = "ما المصطلح الذي يُعرَّف بـ: ${term.definitionAr}؟",
                answer = term.termEn,
                kindOrdinal = kind.ordinal
            )
            Kind.ETYMOLOGY -> {
                if (term.etymology.isBlank()) null
                else mcq(
                    moduleTitle, term, level, points, difficulty, rand,
                    pool = moduleTerms.map { it.termEn },
                    questionEn = "Which term derives from: ${term.etymology}?",
                    questionFr = "Quel terme dérive de : ${term.etymology} ?",
                    questionAr = "ما المصطلح المشتق من: ${term.etymology}؟",
                    answer = term.termEn,
                    kindOrdinal = kind.ordinal
                )
            }
            Kind.PRONUNCIATION -> {
                if (term.ipaPhonetic.isBlank()) null
                else mcq(
                    moduleTitle, term, level, points, difficulty, rand,
                    pool = moduleTerms.map { it.termEn },
                    questionEn = "Which term is transcribed /${term.ipaPhonetic.trim('/')}?",
                    questionFr = "Quel terme se transcrit /${term.ipaPhonetic.trim('/')} ?",
                    questionAr = "ما المصطلح الذي يُكتب صوتيًا /${term.ipaPhonetic.trim('/')}؟",
                    answer = term.termEn,
                    kindOrdinal = kind.ordinal
                )
            }
            Kind.CLINICAL_PEARL -> {
                if (term.clinicalPearl.isBlank()) null
                else mcq(
                    moduleTitle, term, level, points, difficulty, rand,
                    pool = moduleTerms.map { it.termEn },
                    questionEn = "Which term matches this clinical note: ${term.clinicalPearl}?",
                    questionFr = "Quel terme correspond à cette note clinique : ${term.clinicalPearl} ?",
                    questionAr = "ما المصطلح الذي يناسب هذه الملاحظة السريرية: ${term.clinicalPearl}؟",
                    answer = term.termEn,
                    kindOrdinal = kind.ordinal,
                    // L5 doit transporter un contexte clinique (tableau de niveaux Android).
                    contextEn = "Clinical pearl: ${term.clinicalPearl}",
                    contextFr = "Perle clinique : ${term.clinicalPearl}",
                    contextAr = "جوهرة سريرية: ${term.clinicalPearl}"
                )
            }
            Kind.MNEMONIC -> {
                if (term.mnemonic.isBlank()) null
                else mcq(
                    moduleTitle, term, level, points, difficulty, rand,
                    pool = moduleTerms.map { it.termEn },
                    questionEn = "Which term is helped by this mnemonic: ${term.mnemonic}?",
                    questionFr = "Quel terme est aidé par ce moyen mnémotechnique : ${term.mnemonic} ?",
                    questionAr = "ما المصطلح الذي يساعده هذا التذكر: ${term.mnemonic}؟",
                    answer = term.termEn,
                    kindOrdinal = kind.ordinal,
                    // L6 doit transporter un contexte clinique (tableau de niveaux Android).
                    contextEn = "Mnemonic for “${term.termEn}”: ${term.mnemonic}" +
                        (if (term.clinicalPearl.isNotBlank())
                            " — Clinical relevance: ${term.clinicalPearl}" else ""),
                    contextFr = "Moyen mnémotechnique pour « ${term.termEn} » : ${term.mnemonic}" +
                        (if (term.clinicalPearl.isNotBlank())
                            " — Pertinence clinique : ${term.clinicalPearl}" else ""),
                    contextAr = "أسلوب تذكر لمصطلح « ${term.termEn} »: ${term.mnemonic}" +
                        (if (term.clinicalPearl.isNotBlank())
                            " — الأهمية السريرية: ${term.clinicalPearl}" else "")
                )
            }
            Kind.READING -> {
                if (term.exampleEn.isBlank()) null
                else ExerciseEntity(
                    id = stableId(moduleTitle, term.id, kind.ordinal),
                    level = level, type = "reading", difficulty = difficulty,
                    module = moduleTitle, chapter = term.chapter,
                    questionEn = "Which term fits this context?",
                    questionFr = "Quel terme correspond à ce contexte ?",
                    questionAr = "ما المصطلح الذي يناسب هذا السياق؟",
                    optionsRaw = joinOptions(
                        LessonRng.shuffleWith(
                            listOf(term.termEn) + distractors(
                                term.termEn, moduleTerms.map { it.termEn }, rand
                            ),
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
            Kind.CLINICAL_CASE -> {
                if (term.exampleEn.isBlank()) null
                else ExerciseEntity(
                    id = stableId(moduleTitle, term.id, kind.ordinal),
                    level = level, type = "clinical_case", difficulty = difficulty,
                    module = moduleTitle, chapter = term.chapter,
                    questionEn = "Identify the term used in this clinical context.",
                    questionFr = "Identifiez le terme employé dans ce contexte clinique.",
                    questionAr = "حدّد المصطلح المستخدم في هذا السياق السريري.",
                    optionsRaw = joinOptions(
                        LessonRng.shuffleWith(
                            listOf(term.termEn) + distractors(
                                term.termEn, moduleTerms.map { it.termEn }, rand
                            ),
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
            Kind.MATCHING -> {
                if (moduleTerms.size < MATCH_PAIRS) null
                else {
                    val pairTerms = LessonRng.shuffleWith(
                        moduleTerms.filter { it.id != term.id }, rand
                    ).take(MATCH_PAIRS - 1)
                    if (pairTerms.size < MATCH_PAIRS - 1) null
                    else {
                        val group = listOf(term) + pairTerms
                        val options = group.map { "${it.termEn}:${it.termFr}" }
                        ExerciseEntity(
                            id = stableId(moduleTitle, term.id, kind.ordinal),
                            level = level, type = "matching", difficulty = difficulty,
                            module = moduleTitle, chapter = term.chapter,
                            questionEn = "Match each term with its French translation.",
                            questionFr = "Associez chaque terme à sa traduction française.",
                            questionAr = "صِل كل مصطلح بترجمته الفرنسية.",
                            optionsRaw = options.joinToString("|"),
                            correctAnswer = options.joinToString("|"),
                            explanationEn = term.definitionEn,
                            explanationFr = term.definitionFr,
                            explanationAr = term.definitionAr,
                            points = points
                        )
                    }
                }
            }
            Kind.WORD_ORDER -> {
                val sentence = term.exampleEn
                // On découpe la phrase en mots, on garde les mots significatifs
                // (sinon "The heart is located…" devient introuvable).
                val words = splitWords(sentence)
                if (words.size < 3) null
                else ExerciseEntity(
                    id = stableId(moduleTitle, term.id, kind.ordinal),
                    level = level, type = "sentence_order", difficulty = difficulty,
                    module = moduleTitle, chapter = term.chapter,
                    questionEn = "Arrange the words to build the sentence about \"${term.termEn}\".",
                    questionFr = "Remettez les mots dans l'ordre pour construire la phrase sur « ${term.termEn} ».",
                    questionAr = "رتب الكلمات لبناء الجملة حول « ${term.termEn} ».",
                    optionsRaw = joinOptions(LessonRng.shuffleWith(words, rand)),
                    correctAnswer = words.joinToString(" "),
                    explanationEn = term.definitionEn,
                    explanationFr = term.definitionFr,
                    explanationAr = term.definitionAr,
                    points = points
                )
            }
            Kind.FILL_BLANK -> {
                val sentence = term.exampleEn
                // On remplace le terme par un trou dans l'exemple : l'utilisateur
                // doit retrouver le terme à partir de son contexte d'usage.
                //
                // `fill_blank` est une saisie libre sur les deux plateformes :
                // - Android : `ExerciseSpec.Fill.acceptedAnswers` lit
                //   `correctAnswer` uniquement (optionsRaw ignoré, voir
                //   `ExerciseEntity.toExerciseSpec`).
                // - Web : `fillScore(q.fillVal, ex.answer)`.
                // On stocke donc la réponse seule, sans distracteurs.
                val blanked = blankTermInSentence(sentence, term.termEn)
                if (blanked == null) null
                else ExerciseEntity(
                    id = stableId(moduleTitle, term.id, kind.ordinal),
                    level = level, type = "fill_blank", difficulty = difficulty,
                    module = moduleTitle, chapter = term.chapter,
                    questionEn = "Fill in the blank: ${blanked.first}",
                    questionFr = "Complétez la phrase : ${blanked.second}",
                    questionAr = "أكمل الفراغ: ${blanked.third}",
                    optionsRaw = term.termEn,
                    correctAnswer = term.termEn,
                    explanationEn = term.definitionEn,
                    explanationFr = term.definitionFr,
                    explanationAr = term.definitionAr,
                    points = points
                )
            }
        }
    }

    // ============================================================
    // Helpers
    // ============================================================

    /**
     * Construit un QCM : `optionsRaw` = réponse + 3 distracteurs, le tout mélangé.
     * La réponse est garantie présente (et unique) dans les options.
     */
    private fun mcq(
        moduleTitle: String,
        term: MedicalTermEntity,
        level: Int,
        points: Int,
        difficulty: String,
        rand: () -> Double,
        pool: List<String>,
        questionEn: String,
        questionFr: String,
        questionAr: String,
        answer: String,
        kindOrdinal: Int,
        contextEn: String = "",
        contextFr: String = "",
        contextAr: String = ""
    ): ExerciseEntity {
        val options = LessonRng.shuffleWith(
            listOf(answer) + distractors(answer, pool, rand),
            rand
        )
        return ExerciseEntity(
            id = stableId(moduleTitle, term.id, kindOrdinal),
            level = level, type = "mcq", difficulty = difficulty,
            module = moduleTitle, chapter = term.chapter,
            questionEn = questionEn, questionFr = questionFr, questionAr = questionAr,
            optionsRaw = joinOptions(options),
            correctAnswer = answer,
            explanationEn = term.definitionEn,
            explanationFr = term.definitionFr,
            explanationAr = term.definitionAr,
            points = points,
            contextTextEn = contextEn,
            contextTextFr = contextFr,
            contextTextAr = contextAr
        )
    }

    /**
     * 3 distracteurs du même module, différents de la réponse (normalisés).
     * Ils proviennent de la même famille lexicale que la réponse (terme français
     * pour une question FR, terme anglais pour une question EN), sinon l'utilisateur
     * pourrait éliminer la moitié des options d'un coup d'œil.
     */
    private fun distractors(
        answer: String,
        pool: List<String>,
        rand: () -> Double
    ): List<String> {
        if (pool.isEmpty()) return emptyList()
        val normAnswer = LessonRng.norm(answer)
        if (normAnswer.isEmpty()) return emptyList()

        // Distracteurs proches en premier (Jaccard sur les bigrammes), pour
        // que la question soit difficile : des mots du même champ lexical.
        val scored = pool
            .filter { LessonRng.norm(it).isNotEmpty() && LessonRng.norm(it) != normAnswer }
            .distinctBy { LessonRng.norm(it) }
            .map { it to LessonRng.similarity(answer, it) }
            .sortedByDescending { it.second }

        if (scored.isEmpty()) return emptyList()

        val out = mutableListOf<String>()
        val used = mutableSetOf(normAnswer)
        // On tire dans les 12 plus proches pour garder du choix sans jamais
        // retomber sur la réponse.
        val candidates = scored.take(12).toMutableList()
        while (out.size < MCQ_OPTIONS - 1 && candidates.isNotEmpty()) {
            val idx = (rand() * candidates.size).toInt().coerceIn(0, candidates.lastIndex)
            val (candidate, _) = candidates.removeAt(idx)
            val normCandidate = LessonRng.norm(candidate)
            if (normCandidate in used) continue
            used += normCandidate
            out.add(candidate)
        }
        return out
    }

    /**
     * Id stable et négatif : `-1_000_000 - (hash du terme + index du kind)`.
     *
     * Négatif pour ne **jamais** collisionner avec un exercice écrit main
     * (Room autoGenerate démarre à 1). Déterministe pour que la régénération
     * conserve les ids — la base ne change pas entre deux versions de l'app.
     * `hashSeed` pouvant être négatif en Kotlin (Int signé), on normalise en
     * Long non signé avant le modulo, comme dans `LessonGenerator`.
     *
     * **L'espace d'ids doit être large** : 4 504 termes × 13 kinds = 58 552 ids
     * à caser sans collision. Avec un modulo de 8 M, le paradoxe des
     * anniversaires produit ~270 collisions — un exercice en écrase un autre en
     * base (clé primaire) et `BulkExerciseGeneratorTest` échoue. On étend à
     * 900 M (port web : `bulkExercises.ts`).
     */
    private fun stableId(moduleTitle: String, termId: Int, kindOrdinal: Int): Int {
        val h = LessonRng.hashSeed("bulk:$moduleTitle:$termId:$kindOrdinal")
        return -100_000_000 - ((h.toLong() and 0xFFFFFFFFL) % 900_000_000L).toInt()
    }

    /** Sépare les options par `|` (contract `getOptionsList()`). */
    private fun joinOptions(options: List<String>): String = options.joinToString("|")

    /**
     * Découpe une phrase en mots jouables : on garde les mots alphabétiques de
     * plus de 2 lettres, débarassés de la ponctuation.
     */
    private fun splitWords(sentence: String): List<String> {
        return sentence.split(Regex("\\s+"))
            .map { it.trim().trim('.', ',', ';', ':', '!', '?', '"') }
            .filter { it.length > 2 && it.matches(Regex("[A-Za-zÀ-ÿ-]+")) }
    }

    /**
     * Remplace les occurrences du terme dans la phrase par un trou.
     * @return triple (phrase EN avec trou, phrase FR avec trou, phrase AR avec trou)
     */
    private fun blankTermInSentence(
        sentence: String,
        term: String
    ): Triple<String, String, String>? {
        if (sentence.isBlank() || term.isBlank()) return null
        val regex = Regex(Regex.escape(term), RegexOption.IGNORE_CASE)
        if (regex.find(sentence) == null) return null
        val blanked = regex.replace(sentence, "______")
        return Triple(blanked, blanked, blanked)
    }
}
