package com.example.data.initial

import com.example.data.local.entity.MedicalTermEntity

val biophysiqueTerms = listOf(
    // === MÉCANIQUE DES FLUIDES ET HÉMODYNAMIQUE ===
    MedicalTermEntity(
        id = 231,
        termEn = "Poiseuille's Law",
        termFr = "Loi de Poiseuille",
        termAr = "قانون بواسوي في جريان الموائع",
        definitionEn = "Physical law stating that laminar flow rate of a viscous fluid through a cylindrical tube is directly proportional to the fourth power of the vessel radius.",
        definitionFr = "Loi physique régissant le débit laminaire d'un fluide visqueux : le débit est proportionnel à la puissance 4 du rayon du vaisseau.",
        definitionAr = "قانون فيزيائي ينص على أن معدل التدفق الصفائحي لمائع لزج يتناسب طردياً مع القوة الرابعة لنصف قطر الوعاء الدموي.",
        etymology = "Formulated by French physician and physicist Jean Léonard Marie Poiseuille in 1838.",
        clinicalPearl = "Because flow depends on radius to the 4th power (r^4), reducing arteriolar radius by 50% increases vascular resistance by 16-fold, demonstrating why arterioles are master regulators of blood pressure.",
        mnemonic = "Resistance = (8 × viscosity × length) / (π × r^4). Half radius = 16x resistance!",
        module = "Biophysique",
        chapter = "Mécanique des Fluides et Hémodynamique",
        exampleEn = "Arteriolar vasoconstriction dramatically reduces local organ perfusion according to Poiseuille's law.",
        exampleFr = "La vasoconstriction artériolaire diminue drastiquement le débit sanguin selon la loi de Poiseuille.",
        exampleAr = "يقلل انقباض الأوعية الشريانية الصغير تدفق الدم الموضعي بشكل هائل وفقاً لقانون بواسوي.",
        ipaPhonetic = "/pwɑːˈzɜːj lɔː/"
    ),
    MedicalTermEntity(
        id = 232,
        termEn = "Reynolds Number (Re)",
        termFr = "Nombre de Reynolds",
        termAr = "رقم رينولدز لجريان السوائل",
        definitionEn = "A dimensionless quantity measuring the ratio of inertial forces to viscous forces in fluid flow, predicting laminar (Re < 2000) versus turbulent (Re > 2000) blood flow.",
        definitionFr = "Nombre sans dimension quantifiant le rapport entre forces d'inertie et forces visqueuses, prédisant la transition du régime laminaire au régime turbulent.",
        definitionAr = "معامل لا بعدي يقيس النسبة بين قوى القصور الذاتي وقوى اللزوجة في جريان السائل، للتنبؤ بنمط الجريان الهادئ أو المضطرب.",
        etymology = "Named after Osborne Reynolds who introduced the concept in 1883.",
        clinicalPearl = "Turbulent blood flow occurs when Re exceeds critical threshold (e.g., across stenotic heart valves or large aneurysms), producing audible clinical cardiac murmurs or vascular bruits on auscultation.",
        mnemonic = "Re = (density × velocity × diameter) / viscosity. Anemia lowers viscosity → Re rises → audible flow murmur.",
        module = "Biophysique",
        chapter = "Mécanique des Fluides et Hémodynamique",
        exampleEn = "Turbulence caused by high Reynolds number over an atherosclerotic plaque generates carotid bruits.",
        exampleFr = "La turbulence liée à un nombre de Reynolds élevé sur une plaque d'athérome génère un souffle carotidien.",
        exampleAr = "يولد الجريان المضطرب الناجم عن ارتفاع رقم رينولدز فوق لويحة التصلب العصيدي لغطاً شريانياً مسموعاً.",
        ipaPhonetic = "/ˈren.əldz ˈnʌm.bər/"
    ),
    MedicalTermEntity(
        id = 233,
        termEn = "Bernoulli's Principle",
        termFr = "Théorème de Bernoulli",
        termAr = "مبدأ برنولي لديناميكا السوائل",
        definitionEn = "Principle stating that within a steady, inviscid fluid flow, an increase in flow speed occurs simultaneously with a decrease in static pressure or potential energy.",
        definitionFr = "Principe de conservation de l'énergie mécanique : dans un écoulement sans frottement, une accélération de la vitesse s'accompagne d'une baisse de pression statique.",
        definitionAr = "مبدأ فيزيائي ينص على أن زيادة سرعة تدفق المائع تترافق دائماً مع انخفاض في ضغطه الساكن أو طاقته الكامنة.",
        etymology = "Published by Daniel Bernoulli in his book Hydrodynamica in 1738.",
        clinicalPearl = "Used in Doppler echocardiography (simplified Bernoulli equation: ΔP = 4v^2) to non-invasively calculate pressure gradients across stenotic aortic and mitral valves.",
        mnemonic = "Modified Bernoulli: ΔP = 4 × Vmax^2 (if blood jets at 4 m/s through aortic valve, gradient is 4 × 16 = 64 mmHg).",
        module = "Biophysique",
        chapter = "Mécanique des Fluides et Hémodynamique",
        exampleEn = "Cardiologists use Bernoulli's equation to quantify transvalvular pressure gradients during echocardiography.",
        exampleFr = "Les cardiologues utilisent l'équation de Bernoulli pour mesurer les gradients de pression transvalvulaire.",
        exampleAr = "يستخدم أطباء القلب معادلة برنولي لقياس تدرج الضغط عبر صمامات القلب بواسطة تخطيط صدى القلب.",
        ipaPhonetic = "/bɜːˈnuː.li/"
    ),

    // === OPTIQUE MÉDICALE ET VISION ===
    MedicalTermEntity(
        id = 234,
        termEn = "Emmetropia and Refraction",
        termFr = "Emmétropie et Réfraction",
        termAr = "حرجية البصر السوي والانكسار الضوئي",
        definitionEn = "The ideal optical state of the eye where parallel incident light rays focus precisely onto the fovea centralis of the retina without optical accommodation.",
        definitionFr = "État optique normal de l'œil au repos où le foyer image principal coïncide exactement avec le plan de la rétine sans accommodation.",
        definitionAr = "الحالة البصرية المثالية للعين الطبيعية حيث تتجمع الأشعة الضوئية المتوازية بدقة على نقرة الشبكية دون إجهاد عضلي للتكيف.",
        etymology = "Greek: ἔμμετρος (emmetros) — 'in proper measure' + ὤψ (ops) — 'eye'.",
        clinicalPearl = "In myopia (near-sightedness), the axial eyeball length is excessively long, focusing light in front of the retina; corrected with concave (divergent, negative diopter) lenses.",
        mnemonic = "Myopia = Light falls short (front of retina) → Minus/Divergent lens; Hyperopia = Light falls behind retina → Plus/Convergent lens.",
        module = "Biophysique",
        chapter = "Optique Médicale et Vision",
        exampleEn = "An emmetropic human eye has a total refractive power of approximately +60 diopters at rest.",
        exampleFr = "L'œil emmétrope possède une puissance réfractive totale d'environ 60 dioptries au repos.",
        exampleAr = "تمتلك العين السوية قوة كسرية إجمالية تبلغ حوالي 60 ديوبتر في حالة الراحة.",
        ipaPhonetic = "/ˌem.ɪˈtroʊ.pi.ə/"
    ),

    // === RAYONNEMENTS ET RADIOBIOLOGIE ===
    MedicalTermEntity(
        id = 235,
        termEn = "Ionizing Radiation and Dosimetry",
        termFr = "Rayonnement ionisant et Dosimétrie",
        termAr = "الإشعاع المؤين وقياس الجرعات الإشعاعية",
        definitionEn = "High-energy particulate or electromagnetic radiation (X-rays, gamma rays) capable of liberating electrons from atoms, quantified by absorbed dose (Gray) and equivalent dose (Sievert).",
        definitionFr = "Rayonnements de haute énergie capables d'arracher des électrons aux atomes, quantifiés en dose absorbée (Gray) et dose efficace (Sievert).",
        definitionAr = "إشعاعات كهرومغناطيسية أو جسيمية عالية الطاقة قادرة على تأيين الذرات، تقاس بالجرعة الممتصة (غراي) والجرعة الفعالة (سيفرت).",
        etymology = "Greek: ἰόν (ion) — 'going / particle' + Latin: radius — 'spoke / ray'.",
        clinicalPearl = "1 Gray (Gy) = 1 Joule/kg absorbed; 1 Sievert (Sv) factors in biological tissue weight and radiation quality. Radioprotection principle ALARA: 'As Low As Reasonably Achievable'.",
        mnemonic = "Gray (Gy) = Physical Energy absorbed; Sievert (Sv) = Biological Health harm.",
        module = "Biophysique",
        chapter = "Rayonnements et Radiobiologie",
        exampleEn = "A standard chest X-ray exposes the patient to an effective dose of roughly 0.02 mSv.",
        exampleFr = "Une radiographie thoracique standard expose le patient à une dose efficace d'environ 0,02 mSv.",
        exampleAr = "يعرض التصوير البسيط للصدر المريض لجرعة إشعاعية فعالة تقارب 0.02 ملي سيفرت.",
        ipaPhonetic = "/ˈaɪ.ə.naɪ.zɪŋ ˌreɪ.diˈeɪ.ʃən/"
    ),

    // === ÉLECTROPHYSIOLOGIE ET MEMBRANES ===
    MedicalTermEntity(
        id = 236,
        termEn = "Nernst Equation",
        termFr = "Équation de Nernst",
        termAr = "معادلة نيرنست للجهد الكهربائي",
        definitionEn = "Thermodynamic equation relating the chemical concentration gradient of an ion across a selectively permeable membrane to its electrical equilibrium potential.",
        definitionFr = "Équation thermodynamique reliant le gradient de concentration transmembranaire d'un ion à son potentiel d'équilibre électrique.",
        definitionAr = "معادلة ديناميكية حرارية تربط التدرج الكيميائي لتركيز أيون ما عبر غشاء نصف نفوذ بجهد التوازن الكهربائي المقابل له.",
        etymology = "Formulated by German chemist Walther Hermann Nernst in 1888.",
        clinicalPearl = "At normal body temperature (37°C), E = (61.5 / z) × log10([Ion]out / [Ion]in). For K+ with [K+]in = 140 mM and [K+]out = 4 mM, E_K ≈ -94 mV, close to resting resting potential.",
        mnemonic = "Resting membrane potential is predominantly governed by K+ permeability because resting membrane is far more permeable to K+ than Na+.",
        module = "Biophysique",
        chapter = "Électrophysiologie et Phénomènes de Membrane",
        exampleEn = "The resting membrane potential of human neurons approximates the Nernst equilibrium potential for potassium.",
        exampleFr = "Le potentiel de repos membranaire est très proche du potentiel d'équilibre de Nernst du potassium.",
        exampleAr = "يقترب جهد الراحة للغشاء العصبي البشري بشدة من جهد التوازن المحسوب بنيرنست للبوتاسيوم.",
        ipaPhonetic = "/nɜːnst ɪˈkweɪ.ʒən/"
    ),

    // === SOLUTIONS BIOLOGIQUES ET ÉCHANGES ===
    MedicalTermEntity(
        id = 237,
        termEn = "Osmotic Pressure and Starling Forces",
        termFr = "Pression osmotique et Forces de Starling",
        termAr = "الضغط الإسموزي وقوى ستارلينغ الشعرية",
        definitionEn = "The hydrostatic and colloid oncotic pressure gradients governing transcapillary fluid filtration and reabsorption across the vascular endothelium.",
        definitionFr = "Pressions hydrostatique et oncotique qui régissent les échanges liquidiens et la filtration transcapillaire selon l'équilibre de Starling.",
        definitionAr = "فروق الضغط الهيدروستاتيكي والضغط التناضحي الغرواني (الأونكوتي) المتحكمة في ترشيح وامتصاص السوائل عبر البطانة الشعرية الدموية.",
        etymology = "Named after British physiologist Ernest Henry Starling who established capillary filtration laws in 1896.",
        clinicalPearl = "Plasma oncotic pressure (~25-28 mmHg) is primarily exerted by serum albumin; hypoalbuminemia in nephrotic syndrome causes peripheral edema due to decreased capillary reabsorption.",
        mnemonic = "Net Filtration = Kf × [(Pc - Pi) - σ(πc - πi)]. High capillary hydrostatic pressure (heart failure) or low albumin causes edema.",
        module = "Biophysique",
        chapter = "Solutions Biologiques et Échanges",
        exampleEn = "Imbalance in Starling forces favoring capillary extravasation leads to tissue edema and ascites.",
        exampleFr = "Le déséquilibre des forces de Starling favorisant la filtration capillaire conduit à l'œdème interstitiel.",
        exampleAr = "يؤدي اختلال قوى ستارلينغ لصالح الترشيح الشعري إلى تراكم السوائل وظهور الوذمة النسيجية.",
        ipaPhonetic = "/ɒzˈmɒt.ɪk ˈpreʃ.ər/"
    )
)
