package com.example.data.initial

import com.example.data.local.entity.MedicalTermEntity

val physiologieTerms = listOf(
    // === PHYSIOLOGIE CARDIOVASCULAIRE ===
    MedicalTermEntity(
        id = 271,
        termEn = "Cardiac Output (CO)",
        termFr = "Débit cardiaque (Dc)",
        termAr = "النتاج القلبي / صبيب القلب",
        definitionEn = "The volume of blood pumped by each ventricle per minute, calculated as the mathematical product of stroke volume (SV) and heart rate (HR).",
        definitionFr = "Volume de sang éjecté par chaque ventricule cardiaque par minute, produit du volume d'éjection systolique (VES) par la fréquence cardiaque (FC).",
        definitionAr = "حجم الدم الذي يضخه كل بطين في الدقيقة الواحدة، ويساوي حاصل ضرب حجم الضربة (VES) في معدل ضربات القلب (FC).",
        etymology = "Greek: καρδία (kardia) — 'heart' + output.",
        clinicalPearl = "Normal resting cardiac output in an adult human is approximately 5.0 L/min; cardiac index normalizes this to body surface area (normal: 2.8 - 4.2 L/min/m^2).",
        mnemonic = "CO = HR × SV. Frank-Starling law states: Greater ventricular end-diastolic volume (preload) → stronger contractile force.",
        module = "Physiologie",
        chapter = "Physiologie Cardiovasculaire",
        exampleEn = "During intense aerobic exertion, cardiac output can surge up to 25 to 30 liters per minute.",
        exampleFr = "Lors d'un effort physique intense, le débit cardiaque peut atteindre 25 à 30 L/min.",
        exampleAr = "أثناء التمارين الهوائية الشديدة، يمكن للنتاج القلبي أن يرتفع ليصل إلى 25 أو 30 لتراً في الدقيقة.",
        ipaPhonetic = "/ˈkɑː.di.æk ˈaʊt.pʊt/"
    ),
    MedicalTermEntity(
        id = 272,
        termEn = "Frank-Starling Law of the Heart",
        termFr = "Loi de Frank-Starling",
        termAr = "قانون فرانك ستارلينغ القلبي",
        definitionEn = "The intrinsic physiological principle stating that the stroke volume of the heart increases in response to an increase in the volume of blood filling the heart (end-diastolic volume).",
        definitionFr = "Loi physiologique fondamentale selon laquelle la force de contraction du myocarde augmente avec l'étirement initial des fibres myocardiques en télédiastole.",
        definitionAr = "قانون فسيولوجي أساسي ينص على أن قوة انقباض العضلة القلبية وحجم الضربة يزدادان طردياً مع زيادة حجم الدم الممتلئ في نهاية الانبساط.",
        etymology = "Named after Otto Frank and Ernest Starling who demonstrated myocardial length-tension dynamics in 1895 and 1914.",
        clinicalPearl = "Explains how the heart automatically balances the outputs of the right and left ventricles; in decompensated systolic heart failure, the Frank-Starling curve shifts downward and flattens.",
        mnemonic = "More In = More Out (Increased venous return → increased stretch → optimal actin-myosin overlap → greater stroke volume).",
        module = "Physiologie",
        chapter = "Physiologie Cardiovasculaire",
        exampleEn = "Intravenous fluid resuscitation improves cardiac output through the Frank-Starling mechanism.",
        exampleFr = "Le remplissage vasculaire améliore le débit cardiaque via le mécanisme de Frank-Starling.",
        exampleAr = "يؤدي الإنعاش بالسوائل الوريدية إلى تحسين نتاج القلب من خلال آلية فرانك ستارلينغ.",
        ipaPhonetic = "/fræŋk ˈstɑː.lɪŋ lɔː/"
    ),

    // === PHYSIOLOGIE RESPIRATOIRE ===
    MedicalTermEntity(
        id = 273,
        termEn = "Forced Expiratory Volume in 1 Second (FEV1)",
        termFr = "Volume expiratoire maximal par seconde (VEMS)",
        termAr = "الحجم الزفيري الأقصى في الثانية الأولى (FEV1)",
        definitionEn = "The volume of air exhaled with maximum force in the first second of a spirometry maneuver after full inspiration, assessing airway patency.",
        definitionFr = "Volume maximal d'air expiré avec force durant la toute première seconde d'une expiration maximale forcée débutant à capacité pulmonaire totale.",
        definitionAr = "أقصى حجم هواء يمكن زفيره بقوة قصوى خلال الثانية الأولى من اختبار قياس التنفس بعد شهيق كامل لتقييم سالكية المسالك الهوائية.",
        etymology = "Latin: expirare — 'to breathe out' + volumen.",
        clinicalPearl = "Tiffeneau-Pinelli index (FEV1/FVC ratio) normally exceeds 70-75%; a post-bronchodilator ratio <0.70 confirms obstructive lung disease (Asthma, COPD).",
        mnemonic = "FEV1/FVC < 0.70 = Obstructive defect; FEV1/FVC normal or high with reduced TLC = Restrictive defect (Fibrosis).",
        module = "Physiologie",
        chapter = "Physiologie Respiratoire",
        exampleEn = "Reversible reduction of FEV1 by more than 12% following bronchodilator inhalation is diagnostic for asthma.",
        exampleFr = "Une amélioration du VEMS de plus de 12% après bronchodilatateur confirme le diagnostic d'asthme.",
        exampleAr = "إن التحسن العكوس للـ FEV1 بأكثر من 12% بعد استنشاق موسع قصبي يؤكد تشخيص الربو القصبي.",
        ipaPhonetic = "/ˌef.iː.viː ˈwʌn/"
    ),

    // === PHYSIOLOGIE RÉNALE ===
    MedicalTermEntity(
        id = 274,
        termEn = "Glomerular Filtration Rate (GFR)",
        termFr = "Débit de filtration glomérulaire (DFG)",
        termAr = "معدل الترشيح الكبيبي الكلوي (GFR)",
        definitionEn = "The total volume of plasma filtered through all functional glomeruli of both kidneys per unit of time, serving as the gold standard measure of renal function.",
        definitionFr = "Volume total de plasma filtré à travers les glomérules des deux reins par unité de temps, critère de référence de la fonction rénale.",
        definitionAr = "إجمالي حجم بلازما الدم المترشحة عبر جميع كبيبات الكليتين في وحدة الزمن، ويعد المعيار الذهبي لقياس كفاءة الوظيفة الكلوية.",
        etymology = "Latin: glomerulus — 'little ball of yarn' + filtration.",
        clinicalPearl = "Normal GFR in healthy young adults is 90 to 120 mL/min/1.73 m^2; chronic kidney disease (CKD) is clinically defined by GFR <60 mL/min/1.73 m^2 persisting for ≥3 months.",
        mnemonic = "Stages of CKD: Stage 1 (>90), Stage 2 (60-89), Stage 3a (45-59), Stage 3b (30-44), Stage 4 (15-29), Stage 5 / ESRD (<15 mL/min).",
        module = "Physiologie",
        chapter = "Physiologie Rénale",
        exampleEn = "Creatinine clearance and the CKD-EPI formula provide clinical estimates of glomerular filtration rate.",
        exampleFr = "La formule CKD-EPI permet d'estimer cliniquement le débit de filtration glomérulaire.",
        exampleAr = "تتيح معادلة CKD-EPI وتصفية الكرياتينين التقدير السريري الدقيق لمعدل الترشيح الكبيبي.",
        ipaPhonetic = "/ɡlɒmˈer.jʊ.lər fɪlˈtreɪ.ʃən reɪt/"
    ),
    MedicalTermEntity(
        id = 275,
        termEn = "Renin-Angiotensin-Aldosterone System (RAAS)",
        termFr = "Système rénine-angiotensine-aldostérone (SRAA)",
        termAr = "جهاز الرينين أنجيوتنسين ألدوستيرون",
        definitionEn = "A coordinated systemic endocrine hormone cascade regulating arterial blood pressure, extracellular fluid volume, and systemic vascular resistance.",
        definitionFr = "Cascade hormonale endocrine systémique régulant la pression artérielle et l'équilibre hydro-électrolytique de l'organisme.",
        definitionAr = "شلال هرموني متناسق ينظم ضغط الدم الشرياني وحجم السائل خارج الخلوي وتوازن الكهارل في الجسم البشري.",
        etymology = "Renin from Latin ren (kidney) + Angio (Greek: vessel) + Tensin (tension).",
        clinicalPearl = "Juxtaglomerular cells secrete renin in response to ↓renal perfusion, ↓NaCl at macula densa, or sympathetic stimulation; Angiotensin II triggers vasoconstriction and stimulates aldosterone.",
        mnemonic = "RAAS cascade: Renin (kidney) cleaves Angiotensinogen (liver) → Angiotensin I → ACE (pulmonary capillary endothelia) → Angiotensin II → Aldosterone (adrenal cortex).",
        module = "Physiologie",
        chapter = "Physiologie Rénale",
        exampleEn = "ACE inhibitors and ARBs block the RAAS cascade to lower blood pressure and protect diabetic kidneys.",
        exampleFr = "Les inhibiteurs de l'enzyme de conversion bloquent le SRAA pour traiter l'hypertension artérielle.",
        exampleAr = "تثبط مثبطات الإنزيم المحول للأنجيوتنسين شلال SRAA لعلاج ارتفاع ضغط الدم وحماية الكلى السكرية.",
        ipaPhonetic = "/ˌriː.nɪn ˌæn.dʒi.oʊˈten.sɪn ælˈdɒs.tər.oʊn/"
    ),

    // === ENDOCRINOLOGIE ET HOMÉOSTASIE ===
    MedicalTermEntity(
        id = 276,
        termEn = "Insulin and Glucagon",
        termFr = "Insuline et Glucagon",
        termAr = "الإنسولين والغلوكاغون البنكرياسي",
        definitionEn = "The two master antagonistic pancreatic endocrine hormones regulating glycemic balance: beta cells secrete anabolic insulin (hypoglycemic), alpha cells secrete catabolic glucagon (hyperglycemic).",
        definitionFr = "Les deux principales hormones pancréatiques régulant la glycémie : l'insuline hypoglycémiante des cellules bêta et le glucagon hyperglycémiant des cellules alpha.",
        definitionAr = "الهرمونان البنكرياسيان الرئيسيان المتعاكسان في تنظيم سكر الدم: الإنسولين الخافض للسكر من خلايا بيتا، والغلوكاغون الرافع للسكر من خلايا ألفا.",
        etymology = "Latin: insula — 'island' (islets of Langerhans) + Greek: glykys (sweet) + agon (driving).",
        clinicalPearl = "Insulin promotes GLUT4 translocation in muscle and adipose tissue, stimulates glycogenesis and lipogenesis, while inhibiting lipolysis and gluconeogenesis.",
        mnemonic = "Insulin = Hormone of Plenty / Storage (anabolic); Glucagon = Hormone of Fasting / Breakdown ('Glucose is GONE').",
        module = "Physiologie",
        chapter = "Endocrinologie et Homéostasie",
        exampleEn = "Postprandial hyperglycemia immediately triggers pulsatile insulin secretion from pancreatic beta cells.",
        exampleFr = "L'hyperglycémie postprandiale déclenche la sécrétion immédiate d'insuline par les cellules bêta.",
        exampleAr = "يحفز ارتفاع سكر الدم بعد الوجبات إفرازاً فورياً ونبضياً للإنسولين من خلايا بيتا البنكرياسية.",
        ipaPhonetic = "/ˈɪn.sjʊ.lɪn ænd ˈɡluː.kə.ɡɒn/"
    )
)
