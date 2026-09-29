package com.example.domain.diagnostic

enum class UserType(val labelFr: String, val icon: String, val descFr: String) {
    MED_STUDENT("Étudiant en Médecine", "🎓", "En cursus médecine (1ère à 7ème année)"),
    MED_DOCTOR("Médecin / Résident", "👨‍⚕️", "Déjà diplômé ou en spécialisation"),
    PARAMEDICAL("Paramédical", "💊", "Infirmier, pharmacien, sage-femme, kiné..."),
    NON_MEDICAL("Autre / Passionné", "📖", "Je veux apprendre le vocabulaire médical")
}

enum class MedYear(val labelFr: String, val icon: String, val descFr: String) {
    YEAR_1("1ère Année (PCEM1)", "📚", "Anatomie, Biochimie, Histologie, Biophysique..."),
    YEAR_2("2ème Année", "🔬", "Physiologie, Cytologie, Génétique..."),
    YEAR_3("3ème Année", "🩺", "Sémiologie Médicale, Pharmacologie générale..."),
    YEAR_4("4ème Année (Externat)", "🏥", "Pathologie, Cardiologie, Pneumologie..."),
    YEAR_5("5ème Année", "💊", "Pédiatrie, Neurologie, Chirurgie, Gynécologie..."),
    YEAR_6("6ème Année", "🎓", "Préparation des concours et stages hospitaliers..."),
    YEAR_7("7ème Année", "🩻", "Stage interné de fin d'études médicales..."),
    INTERN("Interne", "🔖", "Pratique clinique hospitalière approfondie..."),
    RESIDENT("Résident", "⚕️", "Spécialisation médicale ou chirurgicale...")
}

enum class KnowledgeLevel(
    val labelFr: String,
    val emoji: String,
    val scoreRange: String,
    val colorHex: Long,
    val recommendedModuleId: String,
    val recommendedModuleName: String
) {
    ZERO("Débutant Absolu", "🌱", "0–14 pts", 0xFF78909C, "anat", "Anatomie"),
    BEGINNER("Débutant", "📗", "15–29 pts", 0xFF4CAF50, "anat", "Anatomie"),
    ELEMENTARY("Élémentaire", "📘", "30–49 pts", 0xFF1976D2, "physio", "Physiologie"),
    INTERMEDIATE("Intermédiaire", "📙", "50–64 pts", 0xFFFF9800, "microbio", "Microbiologie"),
    ADVANCED("Avancé", "🔬", "65–84 pts", 0xFF7B1FA2, "pharmaco", "Pharmacologie"),
    EXPERT("Expert Clinique", "🏆", "85–100 pts", 0xFFD97706, "anapath", "Anatomie Pathologique")
}

data class PlacementQuestion(
    val id: Int,
    val termEn: String,
    val termFr: String,
    val termAr: String,
    val definition: String,
    val options: List<String>,
    val correctIndex: Int,
    val difficulty: Int, // 1 (Facile) à 5 (Expert)
    val module: String,
    val hint: String
)

data class PlacementResult(
    val name: String,
    val userType: UserType,
    val medYear: MedYear?,
    val specialty: String?,
    val level: KnowledgeLevel,
    val score: Int,
    val accuracy: Double,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val startModuleId: String,
    val startModuleName: String,
    val message: String
)

class AdaptivePlacementEngine {
    companion object {
        const val MIN_QUESTIONS = 5
        const val MAX_QUESTIONS = 12

        fun getInitialDifficulty(type: UserType, year: MedYear?): Int {
            return when (type) {
                UserType.NON_MEDICAL -> 1
                UserType.PARAMEDICAL -> 2
                UserType.MED_DOCTOR -> 4
                UserType.MED_STUDENT -> when (year) {
                    MedYear.YEAR_1 -> 1
                    MedYear.YEAR_2 -> 2
                    MedYear.YEAR_3 -> 2
                    MedYear.YEAR_4 -> 3
                    MedYear.YEAR_5 -> 3
                    MedYear.YEAR_6 -> 4
                    MedYear.YEAR_7 -> 4
                    MedYear.INTERN -> 4
                    MedYear.RESIDENT -> 5
                    null -> 2
                }
            }
        }
    }

    private val answers = mutableListOf<Boolean>()
    private val difficulties = mutableListOf<Int>()
    private var consecutiveCorrect = 0
    private var consecutiveWrong = 0
    private var currentDifficulty = 2

    fun startTest(initialDifficulty: Int) {
        answers.clear()
        difficulties.clear()
        consecutiveCorrect = 0
        consecutiveWrong = 0
        currentDifficulty = initialDifficulty.coerceIn(1, 5)
    }

    fun getCurrentDifficulty(): Int = currentDifficulty

    fun recordAnswer(correct: Boolean, questionDifficulty: Int) {
        answers.add(correct)
        difficulties.add(questionDifficulty)
        if (correct) {
            consecutiveCorrect++
            consecutiveWrong = 0
            currentDifficulty = if (consecutiveCorrect >= 3) {
                (currentDifficulty + 2).coerceAtMost(5)
            } else {
                (currentDifficulty + 1).coerceAtMost(5)
            }
        } else {
            consecutiveWrong++
            consecutiveCorrect = 0
            currentDifficulty = if (consecutiveWrong >= 3) {
                (currentDifficulty - 2).coerceAtLeast(1)
            } else {
                (currentDifficulty - 1).coerceAtLeast(1)
            }
        }
    }

    fun shouldStop(): Boolean {
        if (answers.size < MIN_QUESTIONS) return false
        if (answers.size >= MAX_QUESTIONS) return true
        if (consecutiveCorrect >= 3 && currentDifficulty >= 4) return true
        if (consecutiveWrong >= 3 && currentDifficulty <= 2) return true
        return false
    }

    fun calculateResult(
        name: String,
        userType: UserType,
        medYear: MedYear?,
        specialty: String?
    ): PlacementResult {
        val correctCount = answers.count { it }
        val accuracy = if (answers.isEmpty()) 0.0 else correctCount.toDouble() / answers.size

        var weightedScore = 0.0
        var totalWeight = 0.0
        for (i in answers.indices) {
            val weight = difficulties[i].toDouble()
            if (answers[i]) weightedScore += weight
            totalWeight += weight
        }

        val calculatedScore = if (totalWeight > 0) {
            ((weightedScore / totalWeight) * 100).toInt().coerceIn(0, 100)
        } else {
            0
        }

        val level = when {
            calculatedScore < 15 -> KnowledgeLevel.ZERO
            calculatedScore < 30 -> KnowledgeLevel.BEGINNER
            calculatedScore < 50 -> KnowledgeLevel.ELEMENTARY
            calculatedScore < 65 -> KnowledgeLevel.INTERMEDIATE
            calculatedScore < 85 -> KnowledgeLevel.ADVANCED
            else -> KnowledgeLevel.EXPERT
        }

        val (startModId, startModName) = when {
            userType == UserType.MED_DOCTOR -> "pharmaco" to "Pharmacologie"
            level == KnowledgeLevel.ZERO || level == KnowledgeLevel.BEGINNER -> "anat" to "Anatomie"
            level == KnowledgeLevel.ELEMENTARY -> "physio" to "Physiologie"
            level == KnowledgeLevel.INTERMEDIATE -> "microbio" to "Microbiologie"
            level == KnowledgeLevel.ADVANCED -> "pharmaco" to "Pharmacologie"
            else -> "anapath" to "Anatomie Pathologique"
        }

        val message = when (level) {
            KnowledgeLevel.ZERO -> "Bienvenue $name ! Nous commençons par consolider les bases anatomiques et biomédicales fondamentales. 🌱"
            KnowledgeLevel.BEGINNER -> "Bien joué $name ! Vous possédez de bonnes notions initiales. Nous allons bâtir une base solide ! 📗"
            KnowledgeLevel.ELEMENTARY -> "Très bon départ $name ! Niveau élémentaire validé. Nous passons à la physiologie et la biologie intégrée ! 📘"
            KnowledgeLevel.INTERMEDIATE -> "Impressionnant $name ! Niveau intermédiaire confirmé. Vous êtes prêt pour la microbiologie et les cas pratiques ! 📙"
            KnowledgeLevel.ADVANCED -> "Bravo $name ! Niveau avancé détecté. Nous vous orientons directement vers la pharmacologie et la thérapeutique ! 🔬"
            KnowledgeLevel.EXPERT -> "Exceptionnel $name ! Maîtrise clinique de haut niveau. Bienvenue dans les modules d'anatomie pathologique et de cas complexes ! 🏆"
        }

        return PlacementResult(
            name = name,
            userType = userType,
            medYear = medYear,
            specialty = specialty,
            level = level,
            score = calculatedScore,
            accuracy = accuracy,
            totalQuestions = answers.size,
            correctAnswers = correctCount,
            startModuleId = startModId,
            startModuleName = startModName,
            message = message
        )
    }

    object QuestionBank {
        val questions = listOf(
            // Difficulté 1 (Facile)
            PlacementQuestion(
                id = 1,
                termEn = "Heart & Myocardium",
                termFr = "Cœur et Myocarde",
                termAr = "القلب وعضلة القلب",
                definition = "The muscular organ that pumps blood throughout the circulatory system.",
                options = listOf(
                    "L'organe musculaire pompant le sang dans l'organisme",
                    "L'organe filtrant les déchets métaboliques urinaires",
                    "La glande synthétisant les sucs digestifs biliaires",
                    "Le viscère assurant les échanges gazeux pulmonaires"
                ),
                correctIndex = 0,
                difficulty = 1,
                module = "Anatomie",
                hint = "Le myocarde est le muscle contractile assurant la pompe cardiaque."
            ),
            PlacementQuestion(
                id = 2,
                termEn = "Fever (Pyrexia)",
                termFr = "Fièvre (Pyrexie)",
                termAr = "الحمى (ارتفاع الحرارة)",
                definition = "Elevated core body temperature exceeding 38°C caused by cytokine pyrogens.",
                options = listOf(
                    "Élévation de la température corporelle > 38°C",
                    "Accélération du rythme respiratoire au repos",
                    "Baisse de la pression artérielle systolique",
                    "Douleur musculaire diffuse à l'effort"
                ),
                correctIndex = 0,
                difficulty = 1,
                module = "Sémiologie",
                hint = "La fièvre est définie par une température corporelle matinale ≥ 38°C."
            ),
            PlacementQuestion(
                id = 3,
                termEn = "Bacteria",
                termFr = "Bactérie",
                termAr = "البكتيريا",
                definition = "A single-celled prokaryotic microorganism lacking a membrane-bound nucleus.",
                options = listOf(
                    "Un microorganisme procaryote unicellulaire",
                    "Un organisme pluricellulaire eucaryote",
                    "Un parasite acellulaire dépendant de l'hôte",
                    "Une toxine protéique produite par les champignons"
                ),
                correctIndex = 0,
                difficulty = 1,
                module = "Microbiologie",
                hint = "Les bactéries sont des procaryotes sans enveloppe nucléaire."
            ),

            // Difficulté 2 (Facile-Intermédiaire)
            PlacementQuestion(
                id = 4,
                termEn = "Tachycardia & Bradycardia",
                termFr = "Tachycardie et Bradycardie",
                termAr = "تسارع ضربات القلب وبطء القلب",
                definition = "Heart rate alterations: Tachycardia (>100 bpm) and Bradycardia (<60 bpm).",
                options = listOf(
                    "Fréquence cardiaque respectivement >100 bpm et <60 bpm",
                    "Pression artérielle respectivement élevée et basse",
                    "Rythme respiratoire respectivement rapide et lent",
                    "Volume d'éjection systolique diminué et augmenté"
                ),
                correctIndex = 0,
                difficulty = 2,
                module = "Sémiologie",
                hint = "Tachy- = rapide (>100 bpm); Brady- = lent (<60 bpm)."
            ),
            PlacementQuestion(
                id = 5,
                termEn = "Gram-positive Peptidoglycan",
                termFr = "Paroi bactérienne Gram-positif",
                termAr = "جدار البكتيريا موجبة الغرام",
                definition = "Thick multi-layered peptidoglycan retaining crystal violet stain in Gram staining.",
                options = listOf(
                    "Paroi épaisse de peptidoglycane retenant le violet de cristal",
                    "Membrane externe riche en lipopolysaccharide toxique",
                    "Double membrane sans couche de peptidoglycane",
                    "Capsule uniquement polysaccharidique sans paroi rigide"
                ),
                correctIndex = 0,
                difficulty = 2,
                module = "Microbiologie",
                hint = "Le peptidoglycane épais fixe le violet de gentiane lors de la coloration de Gram."
            ),

            // Difficulté 3 (Intermédiaire)
            PlacementQuestion(
                id = 6,
                termEn = "Elimination Half-Life (t½)",
                termFr = "Demi-vie d'élimination (t½)",
                termAr = "عمر النصف للإطراح الدوائي",
                definition = "The time required for plasma drug concentration to decrease by 50%.",
                options = listOf(
                    "Temps nécessaire pour que la concentration plasmatique diminue de moitié",
                    "Délai nécessaire pour atteindre l'effet thérapeutique maximal",
                    "Fraction du médicament atteignant la circulation générale sans altération",
                    "Durée de rétention du médicament dans le compartiment hépatique"
                ),
                correctIndex = 0,
                difficulty = 3,
                module = "Pharmacologie",
                hint = "La demi-vie détermine l'intervalle entre les prises médicamenteuses."
            ),
            PlacementQuestion(
                id = 7,
                termEn = "Apoptosis vs Necrosis",
                termFr = "Apoptose vs Nécrose",
                termAr = "الموت الخلوي المبرمج مقابل النخر النسيجي",
                definition = "Programmed physiological cell death without inflammation vs uncontrolled traumatic lysis.",
                options = listOf(
                    "Mort programmée sans inflammation vs lyse cellulaire traumatique avec inflammation",
                    "Prolifération cellulaire anormale vs différenciation terminale",
                    "Régénération tissulaire vs cicatrisation par fibrose",
                    "Accumulation intracellulaire de lipides vs œdème interstitiel"
                ),
                correctIndex = 0,
                difficulty = 3,
                module = "Anatomie Pathologique",
                hint = "L'apoptose élimine les cellules sans déclencher de réaction inflammatoire."
            ),
            PlacementQuestion(
                id = 8,
                termEn = "Jaundice & Hyperbilirubinemia",
                termFr = "Ictère et Hyperbilirubinémie",
                termAr = "اليرقان وفرط بيليروبين الدم",
                definition = "Yellowish pigmentation of skin and sclerae due to serum bilirubin elevation >35-40 μmol/L.",
                options = listOf(
                    "Coloration jaune des téguments et sclérotiques due à la bilirubine",
                    "Pâleur conjonctivale intense due à une anémie ferriprive",
                    "Coloration bleutée des extrémités due à une hypoxémie",
                    "Éruption maculo-papuleuse diffuse avec prurit intense"
                ),
                correctIndex = 0,
                difficulty = 3,
                module = "Sémiologie",
                hint = "L'ictère conjonctival est le premier signe clinique visible de l'élévation de la bilirubine."
            ),

            // Difficulté 4 (Avancé)
            PlacementQuestion(
                id = 9,
                termEn = "Babinski Sign & Pyramidal Tract",
                termFr = "Signe de Babinski et Faisceau pyramidal",
                termAr = "علامة بابينسكي وإصابة السبيل الهرمي",
                definition = "Dorsiflexion of the hallux upon plantar stroking indicating an upper motor neuron lesion.",
                options = listOf(
                    "Extension lente du gros orteil indiquant une atteinte pyramidale motrice supérieure",
                    "Flexion plantaire réflexe normale des orteils chez l'adulte vigile",
                    "Spasme douloureux de la main lors du gonflage d'un tensiomètre",
                    "Arrêt brutal de l'inspiration profonde à la palpation de l'hypochondre droit"
                ),
                correctIndex = 0,
                difficulty = 4,
                module = "Sémiologie",
                hint = "Le signe de Babinski signe une lésion du motoneurone supérieur du faisceau pyramidal."
            ),
            PlacementQuestion(
                id = 10,
                termEn = "Virchow's Triad in Thrombosis",
                termFr = "Triade de Virchow dans la thrombose",
                termAr = "ثالوث فيرشو في تكون الخثرات الدموية",
                definition = "The three predisposing drivers of intravascular thrombosis: Endothelial injury, Stasis, Hypercoagulability.",
                options = listOf(
                    "Lésion endothéliale, stase sanguine, et état d'hypercoagulabilité",
                    "Hypotension artérielle, bradycardie sinusale, et hypothermie",
                    "Fièvre, tachycardie, et polypnée réactionnelle",
                    "Protéinurie, hypoalbuminémie, et œdèmes des membres inférieurs"
                ),
                correctIndex = 0,
                difficulty = 4,
                module = "Anatomie Pathologique",
                hint = "La triade de Virchow explique la genèse des thromboses veineuses et artérielles."
            ),
            PlacementQuestion(
                id = 11,
                termEn = "Beta-Lactamases & ESBL (BLSE)",
                termFr = "Bêta-lactamases à spectre étendu (BLSE)",
                termAr = "إنزيمات البيتا لاكتاماز واسعة الطيف (BLSE)",
                definition = "Bacterial enzymes hydrolyzing beta-lactams including third-generation cephalosporins.",
                options = listOf(
                    "Enzymes bactériennes inactivant céphalosporines de 3e génération et pénicillines",
                    "Protéines de transport actif expulsant les fluoroquinolones hors de la cellule",
                    "Mutations de l'ARN ribosomique conférant la résistance aux macrolides",
                    "Capsules protectrices empêchant la pénétration des aminosides"
                ),
                correctIndex = 0,
                difficulty = 4,
                module = "Microbiologie",
                hint = "Les BLSE obligent souvent à recourir aux carbapénèmes en antibiothérapie."
            ),

            // Difficulté 5 (Expert)
            PlacementQuestion(
                id = 12,
                termEn = "Minimum Inhibitory Concentration (MIC / CMI)",
                termFr = "Concentration Minimale Inhibitrice (CMI)",
                termAr = "التركيز الأدنى المثبط للنمو البكتيري (CMI)",
                definition = "The lowest concentration of an antimicrobial agent preventing visible in vitro microbial growth.",
                options = listOf(
                    "La plus faible concentration d'antibiotique inhibant toute croissance bactérienne visible",
                    "La dose toxique causant la lyse de 50% des cellules de l'organisme hôte",
                    "La concentration sérique maximale mesurée 30 minutes après perfusion",
                    "Le seuil d'apparition des premiers effets secondaires indésirables"
                ),
                correctIndex = 0,
                difficulty = 5,
                module = "Pharmacologie",
                hint = "La CMI est le paramètre pharmacodynamique clé pour définir la sensibilité bactérienne."
            ),
            PlacementQuestion(
                id = 13,
                termEn = "TP53 Guardian of the Genome",
                termFr = "TP53 et Gènes suppresseurs de tumeurs",
                termAr = "الجين الكابح للأورام TP53 وموت الخلايا",
                definition = "Tumor suppressor gene mutated in >50% of cancers regulating cell cycle arrest and apoptosis.",
                options = listOf(
                    "Gène suppresseur induisant arrêt en G1/S ou apoptose en cas de dommage ADN irréparable",
                    "Proto-oncogène stimulant directement la prolifération cellulaire incontrôlée",
                    "Facteur angiogénique stimulant la néovascularisation tumorale par le VEGF",
                    "Enzyme maintenant la longueur des télomères pour permettre l'immortalité cellulaire"
                ),
                correctIndex = 0,
                difficulty = 5,
                module = "Anatomie Pathologique",
                hint = "La protéine p53 est surnommée le gardien du génome car elle empêche la propagation des mutations."
            ),
            PlacementQuestion(
                id = 14,
                termEn = "Antigenic Shift in Influenza A",
                termFr = "Saut antigénique chez le virus grippal A",
                termAr = "القفز المستضدي في فيروس الإنفلونزا أ",
                definition = "Major genetic reassortment of RNA segments between strains generating pandemic viruses.",
                options = listOf(
                    "Réassortiment génétique majeur de segments d'ARN viraux créant un potentiel pandémique",
                    "Mutations ponctuelles mineures accumulées causant les épidémies saisonnières habituelles",
                    "Intégration d'un provirus dans le génome de l'hôte sans expression immédiate",
                    "Production d'anticorps neutralisants ciblant spécifiquement la neuraminidase"
                ),
                correctIndex = 0,
                difficulty = 5,
                module = "Microbiologie",
                hint = "Le saut antigénique (Shift) provoque les grandes pandémies mondiales de grippe A."
            )
        )
    }
}
