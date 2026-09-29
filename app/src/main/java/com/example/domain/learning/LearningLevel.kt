package com.example.domain.learning

import com.example.localization.Language

enum class LearningLevel(
    val levelNumber: Int,
    val icon: String,
    val titleFr: String,
    val titleEn: String,
    val titleAr: String,
    val objectiveFr: String,
    val objectiveEn: String,
    val objectiveAr: String,
    val colorHex: Long
) {
    LEVEL_1(
        levelNumber = 1,
        icon = "📖",
        titleFr = "Niveau 1 : Vocabulaire",
        titleEn = "Level 1: Vocabulary",
        titleAr = "المستوى 1 : المفردات الطبية",
        objectiveFr = "Mémoriser les termes anatomiques et médicaux un par un (FR ↔ EN ↔ AR).",
        objectiveEn = "Memorize individual medical and anatomical terms (FR ↔ EN ↔ AR).",
        objectiveAr = "حفظ المصطلحات الطبية والتشريحية كلمة بكلمة باللغات الثلاث.",
        colorHex = 0xFF1B5E20
    ),
    LEVEL_2(
        levelNumber = 2,
        icon = "🧩",
        titleFr = "Niveau 2 : Collocations",
        titleEn = "Level 2: Collocations",
        titleAr = "المستوى 2 : التلازمات اللفظية",
        objectiveFr = "Maîtriser les associations fréquentes de mots médicaux (Adjectif + Nom).",
        objectiveEn = "Master high-frequency clinical collocations (Adjective + Noun pairs).",
        objectiveAr = "إتقان التركيبات والتلازمات الطبية المعتادة (صفة + اسم).",
        colorHex = 0xFF00695C
    ),
    LEVEL_3(
        levelNumber = 3,
        icon = "💬",
        titleFr = "Niveau 3 : Phrases Simples",
        titleEn = "Level 3: Simple Sentences",
        titleAr = "المستوى 3 : الجمل البسيطة",
        objectiveFr = "Construire et utiliser les termes dans des structures de base Sujet-Verbe-Objet.",
        objectiveEn = "Construct and apply clinical terms within basic Subject-Verb-Object syntax.",
        objectiveAr = "توظيف المصطلحات في جمل طبية بسيطة (فاعل + فعل + مفعول).",
        colorHex = 0xFF1565C0
    ),
    LEVEL_4(
        levelNumber = 4,
        icon = "📝",
        titleFr = "Niveau 4 : Phrases Complexes",
        titleEn = "Level 4: Complex Sentences",
        titleAr = "المستوى 4 : الجمل المركبة",
        objectiveFr = "Relier les concepts avec connecteurs logiques médicaux (because, leading to, whereas).",
        objectiveEn = "Link physiological concepts using academic connectors (because, leading to, whereas).",
        objectiveAr = "ربط المفاهيم السريرية باستخدام الروابط المنطقية الطبية.",
        colorHex = 0xFF6A1B9A
    ),
    LEVEL_5(
        levelNumber = 5,
        icon = "📄",
        titleFr = "Niveau 5 : Paragraphes Médicaux",
        titleEn = "Level 5: Medical Texts",
        titleAr = "المستوى 5 : النصوص والفقرات الطبية",
        objectiveFr = "Lire, analyser et comprendre des extraits d'articles et rapports cliniques.",
        objectiveEn = "Read, analyze, and comprehend extracts from clinical papers and reports.",
        objectiveAr = "قراءة وفهم نصوص وتقارير طبية أكاديمية متكاملة بالإنجليزية.",
        colorHex = 0xFFE65100
    ),
    LEVEL_6(
        levelNumber = 6,
        icon = "🏥",
        titleFr = "Niveau 6 : Cas Cliniques",
        titleEn = "Level 6: Clinical Cases",
        titleAr = "المستوى 6 : الحالات السريرية الكاملة",
        objectiveFr = "Résoudre des cas médicaux complets en anglais : anamnèse, examen et décision.",
        objectiveEn = "Solve complete clinical vignettes: history, physical exam, and management.",
        objectiveAr = "حل حالات سريرية كاملة بالإنجليزية: التاريخ المرضي، الفحص، والتشخيص.",
        colorHex = 0xFFC62828
    );

    fun getTitle(lang: Language): String = when (lang) {
        Language.ARABIC -> titleAr
        Language.ENGLISH -> titleEn
        Language.FRENCH -> titleFr
    }

    fun getObjective(lang: Language): String = when (lang) {
        Language.ARABIC -> objectiveAr
        Language.ENGLISH -> objectiveEn
        Language.FRENCH -> objectiveFr
    }

    companion object {
        fun fromNumber(number: Int): LearningLevel = entries.find { it.levelNumber == number } ?: LEVEL_1
    }
}
