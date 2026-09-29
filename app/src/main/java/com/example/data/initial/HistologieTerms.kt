package com.example.data.initial

import com.example.data.local.entity.MedicalTermEntity

val histologieTerms = listOf(
    // === TISSUS ÉPITHÉLIAUX ===
    MedicalTermEntity(
        id = 251,
        termEn = "Tight Junction (Zonula Occludens)",
        termFr = "Jonction serrée (Zonula occludens)",
        termAr = "الموصل المحكم / النطاق الساد",
        definitionEn = "The most apical specialized intercellular junction sealing adjacent epithelial cells, preventing paracellular diffusion of solutes and maintaining apical-basolateral polarity.",
        definitionFr = "Jonction intercellulaire la plus apicale formant une barrière étanche à la diffusion paracellulaire entre les cellules épithéliales.",
        definitionAr = "موصل خلوي قمي شديد التخصص يغلق المسافات بين الخلايا الظهارية المجاورة لمنع التسرب وتحديد الاستقطاب الخلوي.",
        etymology = "Latin: zonula — 'little belt / zone' + occludere — 'to shut off / close up'.",
        clinicalPearl = "Composed of transmembrane proteins claudins and occludins; Clostridium perfringens enterotoxin binds claudins, destroying tight junctions and causing acute food poisoning diarrhea.",
        mnemonic = "Junctional complex top-to-bottom: 'T-A-D-G' (Tight junction, Adherens junction, Desmosome, Gap junction).",
        module = "Histologie",
        chapter = "Tissus Épithéliaux",
        exampleEn = "Tight junctions of cerebral capillary endothelial cells form the structural basis of the blood-brain barrier.",
        exampleFr = "Les jonctions serrées des cellules endothéliales cérébrales constituent la barrière hémato-encéphalique.",
        exampleAr = "تشكل الموصلات المحكمة في الخلايا البطانية للشعيرات الدموية الأساس الهيكلي للحاجز الدموي الدماغي.",
        ipaPhonetic = "/taɪt ˈdʒʌŋk.ʃən/"
    ),
    MedicalTermEntity(
        id = 252,
        termEn = "Basement Membrane",
        termFr = "Membrane basale",
        termAr = "الغشاء القاعدي النسيجي",
        definitionEn = "A thin specialized sheet of extracellular matrix underlying all epithelia and endothelia, composed of the basal lamina (type IV collagen, laminin, perlecan) and reticular lamina.",
        definitionFr = "Mince feuillet matriciel extracellulaire acellulaire soutenant tout épithélium, composé de collagène IV, laminine et protéoglycanes.",
        definitionAr = "طبقة رقيقة ومتخصصة من المطرس خارج الخلوي ترتكز عليها جميع النسج الظهارية والبطانية، وتتكون أساساً من كولاجين النمط الرابع واللامينين.",
        etymology = "Latin: basis — 'foundation / base' + membrana — 'skin / parchment'.",
        clinicalPearl = "In carcinomas, tumor invasion is histopathologically defined when neoplastic epithelial cells breach the basement membrane into the underlying stroma.",
        mnemonic = "Components: 'L-N-C-P' (Laminin, Nidogen, Collagen IV, Perlecan). Goodpasture syndrome involves anti-collagen IV antibodies.",
        module = "Histologie",
        chapter = "Tissus Épithéliaux",
        exampleEn = "The glomerular basement membrane serves as a selective charge- and size-dependent filtration barrier in the kidney.",
        exampleFr = "La membrane basale glomérulaire assure la filtration sélective selon la taille et la charge électrique.",
        exampleAr = "يعمل الغشاء القاعدي الكبيبي كحاجز انتقائي للترشيح الكلوي بناءً على الحجم والشحنة الكهربائية.",
        ipaPhonetic = "/ˈbeɪs.mənt ˈmem.breɪn/"
    ),

    // === TISSUS CONJONCTIFS ET CARTILAGE/OS ===
    MedicalTermEntity(
        id = 253,
        termEn = "Fibroblast",
        termFr = "Fibroblaste",
        termAr = "الخلية الليفية اليافعة",
        definitionEn = "The principal and most abundant resident cell of connective tissue, responsible for synthesizing collagen, elastin, reticular fibers, and ground substance.",
        definitionFr = "Cellule résidente principale et la plus abondante du tissu conjonctif, synthétisant les fibres matricielles et la substance fondamentale.",
        definitionAr = "الخلية الرئيسية والأكثر وفرة في النسيج الضام، المسؤولة عن إفراز ألياف الكولاجين والإيلاستين والمادة الأساسية للمطرس.",
        etymology = "Latin: fibra — 'fiber' + Greek: βλαστός (blastos) — 'germ / sprout'.",
        clinicalPearl = "During tissue repair, fibroblasts differentiate into contractile myofibroblasts expressing alpha-smooth muscle actin, orchestrating wound contraction and scar formation.",
        mnemonic = "FibroBLAST = Active builder of matrix (euchromatic nucleus); FibroCYTE = Quiescent mature cell (spindle heterochromatic nucleus).",
        module = "Histologie",
        chapter = "Tissus Conjonctifs et Cartilage/Os",
        exampleEn = "Active fibroblasts exhibit abundant rough endoplasmic reticulum and an extensive Golgi apparatus for protein secretion.",
        exampleFr = "Les fibroblastes actifs présentent un réticulum endoplasmique rugueux très développé.",
        exampleAr = "تتميز الخلايا الليفية النشطة باحتوائها على شبكة هيولية داخلية خشنة وجهاز غولجي متطور لإفراز البروتينات.",
        ipaPhonetic = "/ˈfaɪ.brəʊ.blæst/"
    ),
    MedicalTermEntity(
        id = 254,
        termEn = "Osteoblast and Osteoclast",
        termFr = "Ostéoblaste et Ostéoclaste",
        termAr = "الخلية بانية العظم وهادمة العظم",
        definitionEn = "The primary cellular effectors of bone remodeling: osteoblasts deposit osteoid collagen matrix and initiate calcification, while multinucleated osteoclasts resorb bone via acid and proteases.",
        definitionFr = "Cellules clés du remodelage osseux : les ostéoblastes synthétisent la matrice osseuse, et les ostéoclastes multinucléés assurent la résorption.",
        definitionAr = "الخلايا المسؤولة عن تجدد العظم: تبني الخلايا البانية المادة العظمية، بينما تمتص الخلايا الهادمة متعددة النوى العظم بواسطة الأحماض والإنزيمات.",
        etymology = "Greek: ὀστέον (osteon) — 'bone' + blastos (sprout) / klastos (broken in pieces).",
        clinicalPearl = "Osteoclasts originate from the monocyte-macrophage hematopoietic lineage, differentiating in response to RANKL secreted by osteoblasts; inhibited by bisphosphonates and osteoprotegerin.",
        mnemonic = "OsteoBlast = Builds bone; OsteoClast = Cleaves / Consumes bone.",
        module = "Histologie",
        chapter = "Tissus Conjonctifs et Cartilage/Os",
        exampleEn = "Osteoclasts create shallow resorption pits called Howship's lacunae on bone surfaces.",
        exampleFr = "Les ostéoclastes creusent des cavités de résorption appelées lacunes de Howship.",
        exampleAr = "تحفر الخلايا الهادمة للعظم فجوات ارتشاف سطحية تُعرف بجيوب هاوشيب على سطح العظم.",
        ipaPhonetic = "/ˈɒs.ti.əʊ.blæst/"
    ),

    // === TISSU MUSCULAIRE ===
    MedicalTermEntity(
        id = 255,
        termEn = "Sarcomere",
        termFr = "Sarcomère",
        termAr = "القسيم العضلي / الساركومير",
        definitionEn = "The fundamental structural and contractile repeating unit of striated muscle fibrils, delineated between two consecutive Z discs and containing overlapping actin and myosin filaments.",
        definitionFr = "Unité contractile élémentaire répétitive de la myofibrille musculaire striée, délimitée entre deux stries Z consécutives.",
        definitionAr = "الوحدة البنائية والانقباضية الوظيفية الأساسية المتكررة في اللييف العضلي المخطط، المحصورة بين خطين متتاليين من أقراص Z.",
        etymology = "Greek: σάρξ (sarx) — 'flesh' + μέρος (meros) — 'part'.",
        clinicalPearl = "During sarcomeric contraction according to the sliding filament theory: A band remains constant in width, while I band and H zone shorten as thin filaments slide past thick filaments.",
        mnemonic = "During contraction: 'H and I shrink, A stays the same' (H-I-A).",
        module = "Histologie",
        chapter = "Tissu Musculaire",
        exampleEn = "Resting sarcomere length in human cardiac and skeletal muscle measures approximately 2.2 micrometers.",
        exampleFr = "La longueur de repos d'un sarcomère musculaire humain est d'environ 2,2 micromètres.",
        exampleAr = "يبلغ طول القسيم العضلي في وضعية الراحة لدى الإنسان حوالي 2.2 ميكرومتر.",
        ipaPhonetic = "/ˈsɑː.kə.mɪər/"
    ),

    // === TISSU NERVEUX ===
    MedicalTermEntity(
        id = 256,
        termEn = "Astrocyte",
        termFr = "Astrocyte",
        termAr = "الخلية الدبقية النجمية",
        definitionEn = "Star-shaped neuroglial cells of the central nervous system whose perivascular end-feet envelop capillaries to maintain the blood-brain barrier and regulate extracellular potassium and glutamate.",
        definitionFr = "Cellules gliales étoilées du SNC dont les pieds vasculaires entourent les capillaires sanguins pour consolider la barrière hémato-encéphalique.",
        definitionAr = "خلايا دبقية نجمية الشكل في الجهاز العصبي المركزي، تحيط استطالاتها بالشعيرات الدموية لدعم الحاجز الدموي الدماغي وتنظيم الوسط العصبي.",
        etymology = "Greek: ἄστρον (astron) — 'star' + κύτος (kytos) — 'hollow cell'.",
        clinicalPearl = "Protoplasmic astrocytes reside in gray matter, fibrous astrocytes in white matter. Astrocytes proliferate to form a glial scar (gliosis) after CNS infarction or trauma, marked by GFAP expression.",
        mnemonic = "Astrocytes = 'A-S-T-R-O' (Anchor to vessels, Scar formation, Transport nutrients, Regulate K+ & glutamate, Outer barrier).",
        module = "Histologie",
        chapter = "Tissu Nerveux",
        exampleEn = "Astrocytic end-feet completely envelop the cerebral microvasculature.",
        exampleFr = "Les pieds astrocytaires recouvrent entièrement la microvascularisation cérébrale.",
        exampleAr = "تغلف الأقدام الوعائية للخلايا النجمية كامل الأوعية الدموية الدقيقة في الدماغ.",
        ipaPhonetic = "/ˈæs.trə.saɪt/"
    ),

    // === TISSU SANGUIN ET HÉMATOPOÏÈSE ===
    MedicalTermEntity(
        id = 257,
        termEn = "Erythrocyte and Reticulocyte",
        termFr = "Érythrocyte et Réticulocyte",
        termAr = "كرية الدم الحمراء والخلية الشبكية",
        definitionEn = "Anucleate biconcave blood disc optimized for oxygen transport; reticulocytes are immature erythrocytes containing residual ribosomal RNA, indicating active bone marrow erythropoiesis.",
        definitionFr = "Disque biconcave anucléé transportant l'oxygène ; le réticulocyte est le précurseur immédiat contenant des débris d'ARN ribosomique.",
        definitionAr = "قرص دموي مقعر الوجهين وخالٍ من النواة مخصص لنقل الأكسجين؛ وتعد الخلية الشبكية الطليعة غير الناضجة الحاوية على بقايا رنا ريبوسومي.",
        etymology = "Greek: ἐρυθρός (erythros) — 'red' + κύτος (kytos) — 'cell'.",
        clinicalPearl = "Normal reticulocyte count is 0.5% to 2.0% (50,000 - 100,000/μL); elevated reticulocyte count (>2%) indicates bone marrow response to hemolytic anemia or acute hemorrhage (regenerative anemia).",
        mnemonic = "Reticulocyte > 2% = Regenerative (Hemolysis / Bleeding); Reticulocyte < 1% = Hypoproliferative / Aregenerative (Aplasia, Deficiency).",
        module = "Histologie",
        chapter = "Tissu Sanguin et Hématopoïèse",
        exampleEn = "Erythrocytes have an average circulating lifespan of 120 days before splenic macrophage destruction.",
        exampleFr = "Les érythrocytes ont une durée de vie moyenne de 120 jours dans la circulation.",
        exampleAr = "يبلغ متوسط عمر كريات الدم الحمراء في الدورة الدموية 120 يوماً قبل أن تبتلعها بلعميات الطحال.",
        ipaPhonetic = "/ɪˈrɪθ.rə.saɪt/"
    )
)
