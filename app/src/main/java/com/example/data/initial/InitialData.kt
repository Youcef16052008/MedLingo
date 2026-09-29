package com.example.data.initial

import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.MedicalTermEntity

object InitialData {

    private val otherTerms = listOf(
        // === PHYSIOLOGIE (151-154) ===
        MedicalTermEntity(
            id = 151,
            termEn = "Hypertension",
            termFr = "Hypertension artérielle",
            termAr = "ارتفاع ضغط الدم الشرياني",
            definitionEn = "A chronic clinical condition characterized by persistent elevation of systemic arterial blood pressure.",
            definitionFr = "Élévation pathologique et durable de la pression artérielle systolique (≥140 mmHg) ou diastolique (≥90 mmHg).",
            definitionAr = "حالة سريرية مزمنة تتسم بالارتفاع المستمر لضغط الدم الشرياني الجهازي عن المعدلات الطبيعية.",
            etymology = "Greek: ὑπέρ (hyper) — 'above / excessive' + Latin: tensio — 'stretching / tension'",
            clinicalPearl = "Known as the 'silent killer'; major modifiable etiology for ischemic stroke, coronary artery disease, and nephropathy.",
            mnemonic = "Diagnostic thresholds: Stage 1 = 130-139 / 80-89 mmHg; Stage 2 = ≥140 / ≥90 mmHg.",
            module = "Physiologie",
            chapter = "Cardiovasculaire",
            example = "Essential hypertension accounts for over 90% of all diagnosed hypertensive patients.",
            exampleEn = "Essential hypertension accounts for over 90% of all diagnosed hypertensive patients.",
            exampleFr = "L'hypertension essentielle représente plus de 90% des cas d'hypertension diagnostiqués.",
            exampleAr = "يمثل ارتفاع ضغط الدم الأساسي أكثر من 90% من جميع حالات ارتفاع ضغط الدم المشخصة.",
            ipaPhonetic = "/ˌhaɪ.pəˈten.ʃən/"
        ),
        MedicalTermEntity(
            id = 152,
            termEn = "Homeostasis",
            termFr = "Homéostasie",
            termAr = "الاستتباب / التوازن البدني",
            definitionEn = "The physiological tendency of an organism to regulate and maintain constant internal equilibrium.",
            definitionFr = "Maintien dynamique de la constance du milieu intérieur face aux perturbations de l'environnement.",
            definitionAr = "قدرة الكائن الحي الفسيولوجية على تنظيم واستقرار وسطه الداخلي وحفظ توازنه الديناميكي.",
            etymology = "Greek: ὅμοιος (homoios) — 'similar' + στάσις (stasis) — 'standing still'",
            clinicalPearl = "Operates primarily via negative feedback loops regulating body temperature (37°C), pH (7.35-7.45), and glycemia.",
            mnemonic = "Homeostasis Loop: Receptor → Control Center (Hypothalamus) → Effector.",
            module = "Physiologie",
            chapter = "Physiologie cellulaire",
            example = "Renal acid-base buffering and pulmonary ventilation act collaboratively in acid-base homeostasis.",
            exampleEn = "Renal acid-base buffering and pulmonary ventilation act collaboratively in acid-base homeostasis.",
            exampleFr = "Le tampon rénal acido-basique et la ventilation pulmonaire collaborent pour maintenir l'homéostasie.",
            exampleAr = "يعمل التوازن الكلوي والتهوية الرئوية معاً للحفاظ على الاستتباب الحامضي القاعدي.",
            ipaPhonetic = "/ˌhəʊ.mi.əʊˈsteɪ.sɪs/"
        ),
        MedicalTermEntity(
            id = 153,
            termEn = "Action Potential",
            termFr = "Potentiel d'action",
            termAr = "جهد الفعل العصبي",
            definitionEn = "A brief, all-or-none reversal of electric polarization across an excitable cell membrane.",
            definitionFr = "Dépolarisation transitoire et régénérative de la membrane plasmique des cellules excitables.",
            definitionAr = "تغير كهربائي سريع ومؤقت ينعكس فيه استقطاب الغشاء الخلوي في الخلايا القابلة للاستثارة.",
            etymology = "Latin: actio — 'doing / movement' + potentialis — 'having power'",
            clinicalPearl = "Triggered when membrane depolarization surpasses threshold (-55 mV), inducing rapid opening of voltage-gated Na+ channels.",
            mnemonic = "Phases: 1. Depolarization (Na+ in) → 2. Repolarization (K+ out) → 3. Hyperpolarization.",
            module = "Physiologie",
            chapter = "Physiologie nerveuse",
            example = "Myelinated axons exhibit saltatory conduction of the action potential along the nodes of Ranvier.",
            exampleEn = "Myelinated axons exhibit saltatory conduction of the action potential along the nodes of Ranvier.",
            exampleFr = "Les axones myélinisés présentent une conduction saltatoire du potentiel d'action.",
            exampleAr = "تظهر المحاور المايلينية توصيلاً قفزياً لجهد الفعل عبر عقد رانفييه.",
            ipaPhonetic = "/ˈæk.ʃən pəˈten.ʃəl/"
        ),
        MedicalTermEntity(
            id = 154,
            termEn = "Alveoli",
            termFr = "Alvéoles pulmonaires",
            termAr = "الأسناخ الرئوية",
            definitionEn = "Microscopic sac-like dilatations of terminal bronchioles where alveolar-capillary gas exchange occurs.",
            definitionFr = "Petits sacs microscopiques terminaux des poumons où s'effectuent les échanges gazeux hématosiques.",
            definitionAr = "أكياس مجهرية طرفية في الرئتين يتم عبر جدرانها التبادل الغازي بين الهواء المستنشق والدم.",
            etymology = "Latin: alveolus — 'little hollow vessel, cavity, or basin'",
            clinicalPearl = "Type II pneumocytes synthesize pulmonary surfactant, preventing alveolar atelectasis by reducing surface tension.",
            mnemonic = "Type I = Gas Exchange (95% surface area); Type II = Surfactant Synthesis & Stem Cell repair.",
            module = "Physiologie",
            chapter = "Respiratoire",
            example = "Adult human lungs contain approximately 300 to 500 million functional alveoli.",
            exampleEn = "Adult human lungs contain approximately 300 to 500 million functional alveoli.",
            exampleFr = "Les poumons humains adultes contiennent environ 300 à 500 millions d'alvéoles fonctionnelles.",
            exampleAr = "تحتوي رئتا الإنسان البالغ على ما يقارب 300 إلى 500 مليون سنخ وظيفي.",
            ipaPhonetic = "/ælˈviː.ə.laɪ/"
        ),

        // === BIOCHIMIE (155-157) ===
        MedicalTermEntity(
            id = 155,
            termEn = "Mitochondria",
            termFr = "Mitochondrie",
            termAr = "الميتوكوندريا / المتقدرات",
            definitionEn = "Double-membrane cellular organelles responsible for aerobic ATP synthesis via the Krebs cycle and oxidative phosphorylation.",
            definitionFr = "Organites intracellulaires producteurs de la majorité de l'adénosine triphosphate (ATP) aérobie.",
            definitionAr = "عُضَيّات خلوية محاطة بغشاء مزدوج مسؤولة عن توليد معظم طاقة الخلية (ATP) عبر الفسفرة التأكسدية.",
            etymology = "Greek: μίτος (mitos) — 'thread' + χονδρίον (chondrion) — 'granule / little grain'",
            clinicalPearl = "Mitochondrial DNA is exclusively maternally inherited; defect leads to mitochondrial myopathies with ragged-red fibers.",
            mnemonic = "Powerhouse: Outer membrane, Intermembrane space, Inner cristae membrane (ATP synthase), Matrix (Krebs).",
            module = "Biochimie",
            chapter = "Enzymes et Énergie",
            example = "Mitochondria synthesize more than 90% of cellular energy under aerobic conditions.",
            exampleEn = "Mitochondria synthesize more than 90% of cellular energy under aerobic conditions.",
            exampleFr = "Les mitochondries synthétisent plus de 90% de l'énergie cellulaire.",
            exampleAr = "تنتج الميتوكوندريا أكثر من 90% من طاقة الخلية في الظروف الهوائية.",
            ipaPhonetic = "/ˌmaɪ.təʊˈkɒn.dri.ə/"
        ),
        MedicalTermEntity(
            id = 156,
            termEn = "Adenosine Triphosphate (ATP)",
            termFr = "Adénosine triphosphate (ATP)",
            termAr = "أدينوسين ثلاثي الفوسفات (ATP)",
            definitionEn = "The primary chemical energy carrier in all living cells, fueling endergonic enzymatic biological reactions.",
            definitionFr = "Nucléotide triphosphate servant de vecteur universel d'énergie chimique cellulaire.",
            definitionAr = "مركب نيوكليوتيدي عالي الطاقة يُعد الناقل الأساسي والعملة البيوكيميائية للطاقة في جميع الخلايا الحية.",
            etymology = "From Adenine base + Ribose sugar + 3 high-energy phosphate ester phosphoanhydride bonds.",
            clinicalPearl = "Hydrolysis of ATP to ADP and inorganic phosphate (Pi) yields approximately -30.5 kJ/mol (-7.3 kcal/mol) of free energy.",
            mnemonic = "ATP = Universal biochemical energy currency; ADP + Pi = Discharged battery.",
            module = "Biochimie",
            chapter = "Glucides et Énergie",
            example = "Active sodium-potassium pumps consume roughly 30% of basal total cellular ATP.",
            exampleEn = "Active sodium-potassium pumps consume roughly 30% of basal total cellular ATP.",
            exampleFr = "Les pompes sodium-potassium consomment environ 30% de l'ATP cellulaire de base.",
            exampleAr = "تستهلك مضخات الصوديوم والبوتاسيوم النشطة حوالي 30% من إجمالي ATP الخلوي الأساسي.",
            ipaPhonetic = "/ˌeɪ.tiːˈpiː/"
        ),
        MedicalTermEntity(
            id = 157,
            termEn = "Hemoglobin",
            termFr = "Hémoglobine",
            termAr = "خضاب الدم / الهيموغلوبين",
            definitionEn = "A globular tetrameric iron-containing metalloprotein in erythrocytes responsible for transporting oxygen to peripheral tissues.",
            definitionFr = "Métalloprotéine tétramérique contenant du fer, présente dans les globules rouges et transportant l'oxygène.",
            definitionAr = "بروتين كروي رباعي الوحدات يحتوي على ذرات الحديد في خلايا الدم الحمراء، ينقل الأكسجين للأنسجة.",
            etymology = "Greek: αἷμα (haima) — 'blood' + Latin: globus — 'sphere / round ball'",
            clinicalPearl = "Displays sigmoidal oxygen binding affinity due to positive cooperativity; shifts right with ↑2,3-BPG, ↑H+ (acidosis), ↑CO2, and ↑temperature.",
            mnemonic = "Right-shift oxygen dissociation curve: 'CADET, face Right!' (CO2, Acidity, DPG, Exercise, Temperature).",
            module = "Biochimie",
            chapter = "Protéines",
            example = "Adult hemoglobin (HbA) consists of two alpha and two beta polypeptide globin subunits.",
            exampleEn = "Adult hemoglobin (HbA) consists of two alpha and two beta polypeptide globin subunits.",
            exampleFr = "L'hémoglobine adulte (HbA) est formée de deux sous-unités alpha et deux sous-unités bêta.",
            exampleAr = "يتكون الهيموغلوبين البالغ (HbA) من وحدتين فرعيتين ألفا ووحدتين بيتا.",
            ipaPhonetic = "/ˌhiː.məˈɡləʊ.bɪn/"
        ),

        // === HISTOLOGIE (158-159) ===
        MedicalTermEntity(
            id = 158,
            termEn = "Epithelium",
            termFr = "Épithélium",
            termAr = "النسيج الظهاري / الطلائي",
            definitionEn = "A primary avascular tissue composed of tightly cohesive cells lining internal cavities, external surfaces, and forming glands.",
            definitionFr = "Tissu fondamental avasculaire constitué de cellules jointives reposant sur une membrane basale.",
            definitionAr = "نسيج أولي لا وعائي يتألف من خلايا متلاصقة ترتكز على غشاء قاعدي، يبطن التجاويف ويغطي الأسطح الخارجية.",
            etymology = "Greek: ἐπί (epi) — 'upon / over' + θηλή (thele) — 'nipple / layer of delicate tissue'",
            clinicalPearl = "Classified by layer count (simple, stratified, pseudostratified) and cellular morphology (squamous, cuboidal, columnar).",
            mnemonic = "Functions: 'P-A-S-S' (Protection, Absorption, Secretion, Sensation).",
            module = "Histologie",
            chapter = "Tissus épithéliaux",
            example = "Stratified non-keratinized squamous epithelium lines the mucosal lumen of the esophagus.",
            exampleEn = "Stratified non-keratinized squamous epithelium lines the mucosal lumen of the esophagus.",
            exampleFr = "Un épithélium malpighien non kératinisé tapisse la lumière de l'œsophage.",
            exampleAr = "يبطن النسيج الظهاري الحرشفي الطبقي غير المتقرن تجويف المريء.",
            ipaPhonetic = "/ˌep.ɪˈθiː.li.əm/"
        ),
        MedicalTermEntity(
            id = 159,
            termEn = "Schwann Cell",
            termFr = "Cellule de Schwann",
            termAr = "خلية شوان",
            definitionEn = "Principal glial cells of the peripheral nervous system that form myelin sheaths around peripheral axons.",
            definitionFr = "Cellules gliales du système nerveux périphérique responsables de la myélinisation des axones.",
            definitionAr = "الخلايا الدبقية الرئيسية في الجهاز العصبي المحيطي المسؤولة عن تكوين غمد المايلين حول المحاور العصبية.",
            etymology = "Named after Theodor Schwann, German anatomist and physiologist (1810–1882)",
            clinicalPearl = "Each Schwann cell myelinates only a single internode of a single peripheral axon, unlike oligodendrocytes which myelinate multiple axons in the CNS.",
            mnemonic = "Schwann = Single PNS axon myelination; Oligodendrocyte = Many CNS axons.",
            module = "Histologie",
            chapter = "Tissu nerveux",
            example = "Schwann cells facilitate nerve regeneration after peripheral axon injury via bands of Büngner.",
            exampleEn = "Schwann cells facilitate nerve regeneration after peripheral axon injury.",
            exampleFr = "Les cellules de Schwann facilitent la régénération après une lésion axonale périphérique.",
            exampleAr = "تسهل خلايا شوان تجدد الأعصاب بعد إصابة المحاور العصبية المحيطية.",
            ipaPhonetic = "/ʃvɑːn sel/"
        ),

        // === TERMINOLOGIE MÉDICALE (160-162) ===
        MedicalTermEntity(
            id = 160,
            termEn = "Bradycardia",
            termFr = "Bradycardie",
            termAr = "بطء ضربات القلب",
            definitionEn = "An abnormally slow resting heart rate measuring below 60 beats per minute in adult humans.",
            definitionFr = "Ralentissement pathologique ou physiologique de la fréquence cardiaque au-dessous de 60 battements/minute.",
            definitionAr = "انخفاض معدل ضربات القلب في وضعية الراحة إلى أقل من 60 نبضة في الدقيقة لدى البالغين.",
            etymology = "Greek: βραδύς (bradys) — 'slow' + καρδία (kardia) — 'heart'",
            clinicalPearl = "May be physiological in elite endurance athletes, or pathological in sick sinus syndrome, hypothyroidism, and AV block.",
            mnemonic = "Prefix Brady- = Slow (<60 bpm); Suffix -cardia = Relating to heart rate.",
            module = "Terminologie Médicale",
            chapter = "Préfixes et Suffixes",
            example = "Severe symptomatic bradycardia may require transcutaneous pacing and intravenous atropine administration.",
            exampleEn = "Severe symptomatic bradycardia may require transcutaneous pacing and intravenous atropine administration.",
            exampleFr = "Une bradycardie symptomatique sévère peut nécessiter une stimulation transcutanée.",
            exampleAr = "قد يتطلب بطء القلب المصحوب بأعراض شديدة إعطاء الأتروبين وريدياً.",
            ipaPhonetic = "/ˌbræd.iˈkɑː.di.ə/"
        ),
        MedicalTermEntity(
            id = 161,
            termEn = "Tachycardia",
            termFr = "Tachycardie",
            termAr = "تسارع ضربات القلب",
            definitionEn = "An abnormally accelerated resting heart rate exceeding 100 beats per minute.",
            definitionFr = "Accélération du rythme cardiaque au repos dépassant 100 battements par minute chez l'adulte.",
            definitionAr = "تسارع غير طبيعي في نظم القلب أثناء الراحة يزيد عن 100 نبضة في الدقيقة الواحدة.",
            etymology = "Greek: ταχύς (tachys) — 'rapid / swift' + καρδία (kardia) — 'heart'",
            clinicalPearl = "Categorized as narrow-complex (supraventricular) or wide-complex (ventricular tachycardia), the latter being potentially fatal.",
            mnemonic = "Prefix Tachy- = Fast (>100 bpm); Contrast with Brady- (Slow).",
            module = "Terminologie Médicale",
            chapter = "Préfixes et Suffixes",
            example = "Sinus tachycardia is a physiological compensatory response to hypovolemia, anemia, fever, and acute exercise.",
            exampleEn = "Sinus tachycardia is a physiological compensatory response to hypovolemia, anemia, fever, and acute exercise.",
            exampleFr = "La tachycardie sinusale est une réponse physiologique à l'hypovolémie ou à la fièvre.",
            exampleAr = "تعد تسرع القلب الجيبي استجابة تعويضية فيزيولوجية لنقص حجم الدم أو الحمى.",
            ipaPhonetic = "/ˌtæk.ɪˈkɑː.di.ə/"
        ),
        MedicalTermEntity(
            id = 162,
            termEn = "Nephrectomy",
            termFr = "Néphrectomie",
            termAr = "استئصال الكلية الجراحي",
            definitionEn = "The surgical excision and anatomical removal of all or part of one kidney.",
            definitionFr = "Intervention chirurgicale consistant en l'ablation totale ou partielle d'un rein.",
            definitionAr = "إجراء جراحي يتضمن الاستئصال الكامل أو الجزئي لإحدى الكليتين بسبب مرض أو ورم.",
            etymology = "Greek: νεφρός (nephros) — 'kidney' + ἐκتوμή (ektome) — 'cutting out / excision'",
            clinicalPearl = "Radical nephrectomy involves en bloc resection of the kidney, Gerota's fascia, perirenal fat, and ipsilateral adrenal gland.",
            mnemonic = "Suffix -ectomy = Surgical removal; -otomy = Incision into; -ostomy = Creating an opening.",
            module = "Terminologie Médicale",
            chapter = "Suffixes chirurgicaux",
            example = "Laparoscopic donor nephrectomy is the gold standard for living kidney donation.",
            exampleEn = "Laparoscopic donor nephrectomy is the gold standard for living kidney donation.",
            exampleFr = "La néphrectomie laparoscopique est la technique de référence pour le don de rein.",
            exampleAr = "يعد استئصال الكلية بالمنظار المعيار الذهبي للتبرع الحي بالكلية.",
            ipaPhonetic = "/nəˈfrek.tə.mi/"
        ),

        // === ANGLAIS MÉDICAL CLINIQUE (163-164) ===
        MedicalTermEntity(
            id = 163,
            termEn = "Anamnesis / Patient History",
            termFr = "Anamnèse / Histoire de la maladie",
            termAr = "الاستجواب السريري / السيرة المرضية",
            definitionEn = "The comprehensive clinical interview documenting a patient's chief complaint, present illness, past medical history, and systems review.",
            definitionFr = "Recueil méthodique et chronologique des antécédents médicaux et symptômes actuels exposés par le patient.",
            definitionAr = "المقابلة السريرية المنهجية التي يجريها الطبيب لتوثيق الشكوى الرئيسية وتاريخ المرض والسوابق الطبية للمريض.",
            etymology = "Greek: ἀνάμνησις (anamnesis) — 'calling to mind / recollection / memory'",
            clinicalPearl = "Accurate medical history taking alone establishes the diagnostic hypothesis in more than 75% of clinical encounters.",
            mnemonic = "Pain history mnemonic: 'SOCRATES' (Site, Onset, Character, Radiation, Associations, Time, Exacerbating, Severity).",
            module = "Anglais Médical",
            chapter = "Communication clinique",
            example = "Taking a comprehensive anamnesis is the foundational skill of clinical diagnosis.",
            exampleEn = "Taking a comprehensive anamnesis is the foundational skill of clinical diagnosis.",
            exampleFr = "Prendre une anamnèse détaillée est la compétence fondamentale du diagnostic clinique.",
            exampleAr = "يعد أخذ سيرة مرضية شاملة المهارة التأسيسية للتشخيص السريري.",
            ipaPhonetic = "/ˌæn.æmˈniː.sɪs/"
        ),
        MedicalTermEntity(
            id = 164,
            termEn = "Auscultation",
            termFr = "Auscultation",
            termAr = "التَّسَمُّع السريري (بالسماعة الطبية)",
            definitionEn = "The clinical physical diagnostic procedure of listening to internal physiological sounds of the body, usually using a stethoscope.",
            definitionFr = "Méthode d'examen clinique consistant à écouter les bruits produits par les viscères (cœur, poumons, vaisseaux).",
            definitionAr = "إجراء سريري فيزيائي لفحص الأصوات الحيوية الداخلية الصادرة عن القلب والرئتين والأمعاء باستخدام السماعة الطبية.",
            etymology = "Latin: auscultare — 'to listen attentively with the ear'",
            clinicalPearl = "Four primary cardiac auscultation areas: Aortic (2nd right ICS), Pulmonic (2nd left ICS), Tricuspid (4th left ICS), Mitral (5th ICS midclavicular).",
            mnemonic = "Cardiac valves auscultation: 'All Patients Take Meds' (Aortic, Pulmonic, Tricuspid, Mitral).",
            module = "Anglais Médical",
            chapter = "Examen clinique",
            example = "Pulmonary auscultation revealed bilateral fine inspiratory crackles over both lung bases.",
            exampleEn = "Pulmonary auscultation revealed bilateral fine inspiratory crackles over both lung bases.",
            exampleFr = "L'auscultation pulmonaire a révélé des crépitants fins inspiratoires bilatéraux.",
            exampleAr = "أظهر التسمع الرئوي وجود خراخر ناعمة ثنائية الجانب في قاعدتي الرئتين.",
            ipaPhonetic = "/ˌɔː.skəlˈteɪ.ʃən/"
        )
    )

    // Complete Database: 150 Anatomy Terms + All Medical Modules
    val terms: List<MedicalTermEntity> = AnatomyDatabase.getAllTerms() +
            otherTerms +
            biochimieTerms +
            biophysiqueTerms +
            histologieTerms +
            physiologieTerms +
            genetiqueEmbryoTerms +
            terminologieCliniqueTerms +
            cytologieTerms +
            informatiqueMedicaleTerms +
            embryologieTerms +
            microbiologieTerms +
            pharmacologieTerms +
            semiologieMedicaleTerms +
            anatomiePathologiqueTerms

    // 6-Level Progressive Learning System Exercises (Vocabulaire, Collocations, Phrases Simples, Phrases Complexes, Paragraphes, Cas Cliniques)
    val exercises = LearningExercisesData.exercises

    data class ModuleInfo(
        val id: String,
        val icon: String,
        val titleFr: String,
        val titleEn: String,
        val titleAr: String,
        val colorHex: Long,
        val chaptersCount: Int,
        val estimatedSizeMb: Double,
        val progress: Float
    )

    val modulesList = listOf(
        ModuleInfo("anat", "🦴", "Anatomie", "Anatomy", "علم التشريح البشري", 0xFF1B5E20, 4, 14.2, 0.85f),
        ModuleInfo("physio", "❤️", "Physiologie", "Physiology", "علم وظائف الأعضاء", 0xFF00695C, 5, 18.5, 0.42f),
        ModuleInfo("biochim", "🧬", "Biochimie", "Biochemistry", "الكيمياء الحيوية الطبية", 0xFF1565C0, 5, 12.8, 0.28f),
        ModuleInfo("histo", "🔬", "Histologie", "Histology", "علم الأنسجة العام", 0xFF6A1B9A, 5, 16.0, 0.15f),
        ModuleInfo("biophys", "🧪", "Biophysique", "Biophysics", "الفيزياء الحيوية الطبية", 0xFFE65100, 5, 9.4, 0.08f),
        ModuleInfo("genet", "🧬", "Génétique", "Medical Genetics", "علم الوراثة الطبية", 0xFF004D40, 4, 11.0, 0.20f),
        ModuleInfo("termino", "📙", "Terminologie Médicale", "Medical Terminology", "المصطلحات الطبية اليونانية واللاتينية", 0xFFE65100, 4, 8.5, 0.35f),
        ModuleInfo("clinical_en", "🩺", "Anglais Médical", "Clinical Medical English", "الإنجليزية الطبية السريرية", 0xFF00695C, 3, 7.2, 0.50f),
        ModuleInfo("cytol", "🧫", "Cytologie", "Cell Biology & Cytology", "علم الأحياء الخلوية", 0xFF00796B, 4, 10.5, 0.30f),
        ModuleInfo("info_med", "💻", "Informatique Médicale", "Medical Informatics & Biostats", "المعلوماتية الطبية والإحصاء الحيوي", 0xFF1976D2, 4, 8.0, 0.25f),
        ModuleInfo("embryo", "👶", "Embryologie", "Medical Embryology", "علم الأجنة البشرية", 0xFFC2185B, 4, 9.2, 0.18f),
        ModuleInfo("microbio", "🦠", "Microbiologie", "Medical Microbiology", "علم الأحياء الدقيقة الطبية", 0xFF00897B, 15, 13.5, 0.45f),
        ModuleInfo("pharmaco", "💊", "Pharmacologie", "Medical Pharmacology", "علم الأدوية والعقاقير الطبية", 0xFF7B1FA2, 15, 14.8, 0.38f),
        ModuleInfo("semio", "🩺", "Sémiologie Médicale", "Clinical Semiology", "علم الأعراض والتشخيص السريري", 0xFF0288D1, 15, 15.2, 0.30f),
        ModuleInfo("anapath", "🫀", "Anatomie Pathologique", "Anatomic Pathology", "علم الأمراض التشريحي", 0xFFC2185B, 13, 16.0, 0.22f)
    )
}
