# Anglais Medical — relecture des definitions FR/AR

**655 entrees** issues du dump `tmp_medecal_english.sql` (module `everyday_medical_english`), plus 2 termes rediges a la main (ids 1777/1778, hors de ce tableau).

> Les definitions `definitionFr` / `definitionAr` ci-dessous ont ete produites automatiquement. Elles sont livrees pour completer le corpus, mais **aucun usage pedagogique n'est valide avant relecture** par un locuteur natif (francais et arabe), avec verification de la terminologie medicale.

Pour chaque ligne : verifier la fidelite du sens, le registre, et l'usage terminologique local. Cocher une ligne = relecture faite. Les corrections se font dans `app/src/main/java/com/example/data/terms/AnglaisMedicalTerms.kt`.

- entrees a relire : **655**
- chapitres : **42**
- ids finaux : **3858..4512**

Termes ecartes comme doublons `termEn` (7) : 233, 253, 266, 336, 454, 512, 632.

Les exemples traduits correspondants sont suivis séparément dans `docs/anglais-medical-exemples-a-relire.md`.

---

## At the Hospital (25)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 1 | 3858 | Doctor | A person qualified to practice medicine | Personne qualifiée pour exercer la médecine | شخص مؤهل لممارسة الطب |
| [ ] | 2 | 3859 | Nurse | A healthcare professional who cares for patients | Professionnel de santé qui prend soin des patients | مقدم رعاية صحية يعتني بالمرضى |
| [ ] | 3 | 3860 | Patient | A person receiving medical care | Personne recevant des soins médicaux | الشخص الذي يتلقى الرعاية الطبية |
| [ ] | 4 | 3861 | Appointment | A scheduled meeting with a healthcare professional | Rendez-vous planifié avec un professionnel de santé | موعد مجدول مع مقدم الرعاية الصحية |
| [ ] | 5 | 3862 | Emergency room (ER) | A department for urgent medical treatment | Service de traitement médical urgent | قسم للعلاج الطبي العاجل |
| [ ] | 6 | 3863 | Waiting room | The area where patients wait before being seen | Espace où les patients attendent d'être examinés | المكان الذي ينتظر فيه المرضى قبل الفحص |
| [ ] | 7 | 3864 | Clinic | A medical facility for outpatient care | Établissement de soins médicaux ambulatoires | منشأة طبية للرعاية الخارجية |
| [ ] | 8 | 3865 | Ward | A room or division in a hospital for patients | Service ou salle d'hôpital accueillant des patients | جناح أو غرفة في المستشفى لمرضى |
| [ ] | 9 | 3866 | Outpatient | A patient who does not stay overnight in a hospital | Patient qui ne passe pas la nuit à l'hôpital | مريض لا يبقى في المستشفى طوال الليل |
| [ ] | 10 | 3867 | Inpatient | A patient who is admitted and stays in a hospital | Patient admis et hospitalisé | مريض مقبول ومقيم في المستشفى |
| [ ] | 11 | 3868 | Admission | The process of being officially accepted into a hospital | Processus d'accueil officiel d'un patient dans un hôpital | عملية القبول الرسمية لمريض في المستشفى |
| [ ] | 12 | 3869 | Discharge | The release of a patient from hospital care | Sortie du patient de l'hôpital | إخراج المريض من المستشفى |
| [ ] | 13 | 3870 | Reception | The front desk area for registration at a hospital | Espace d'accueil et d'enregistrement à l'hôpital | مكتب الاستقبال والتسجيل في المستشفى |
| [ ] | 14 | 3871 | Referral | A direction to see another healthcare provider | Orientation vers un autre professionnel de santé | إحالة إلى مقدم رعاية صحية آخر |
| [ ] | 15 | 3872 | Medical record | A documented history of a patient's health | Antécédent médical documenté d'un patient | السجل الطبي الموثق لمريض |
| [ ] | 16 | 3873 | Specialist | A doctor with advanced training in a specific field | Médecin ayant une formation avancée dans un domaine précis | طبيب ذو تكوين متقدم في مجال محدد |
| [ ] | 17 | 3874 | General practitioner (GP) | A primary care doctor who treats a wide range of conditions | Médecin traitant de premier recours prenant en charge des pathologies variées | طبيب الرعاية الأولية الذي يعالج أمراضًا متنوعة |
| [ ] | 18 | 3875 | Surgeon | A doctor who performs surgical operations | Médecin qui réalise des interventions chirurgicales | طبيب يجري العمليات الجراحية |
| [ ] | 19 | 3876 | Pediatrician | A doctor specializing in children's health | Médecin spécialisé dans la santé des enfants | طبيب متخصص في صحة الأطفال |
| [ ] | 20 | 3877 | Gynecologist | A doctor specializing in women's reproductive health | Médecin spécialisé dans la santé reproductive de la femme | طبيب متخصص في الصحة الإنجابية للمرأة |
| [ ] | 21 | 3878 | Cardiologist | A doctor specializing in heart conditions | Médecin spécialisé dans les maladies cardiaques | طبيب متخصص في أمراض القلب |
| [ ] | 22 | 3879 | Dermatologist | A doctor specializing in skin conditions | Médecin spécialisé dans les maladies de la peau | طبيب متخصص في أمراض الجلد |
| [ ] | 23 | 3880 | Neurologist | A doctor specializing in nervous system disorders | Médecin spécialisé dans les troubles du système nerveux | طبيب متخصص في اضطرابات الجهاز العصبي |
| [ ] | 24 | 3881 | Orthopedist | A doctor specializing in bones and joints | Médecin spécialisé dans les os et les articulations | طبيب متخصص في العظام والمفاصل |
| [ ] | 25 | 3882 | Psychiatrist | A medical doctor specializing in mental health | Médecin spécialisé en santé mentale | طبيب متخصص في الصحة النفسية |

## Symptoms & How You Feel (35)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 26 | 3883 | Pain | An unpleasant physical sensation caused by illness or injury | Sensation physique désagréable causée par une maladie ou une blessure | إحساس جسدي مزعج سببه مرض أو إصابة |
| [ ] | 27 | 3884 | Ache | A continuous, dull pain in a part of the body | Douleur continue et sourde dans une partie du corps | ألم مستمر وهادئ في جزء من الجسم |
| [ ] | 28 | 3885 | Fever | A body temperature above normal (>38°C) | Température corporelle supérieure à la normale (supérieure à 38 °C) | درجة حرارة الجسم أعلى من المعدل الطبيعي (أكثر من 38 درجة مئوية) |
| [ ] | 29 | 3886 | Cough | A sudden expulsion of air from the lungs | Expulsion brusque d'air des poumons | طرد مفاجئ للهواء من الرئتين |
| [ ] | 30 | 3887 | Sneeze | A sudden, involuntary expulsion of air through the nose | Expulsion involontaire et soudaine d'air par le nez | طرد مفاجئ ولا إرادي للهواء عبر الأنف |
| [ ] | 31 | 3888 | Fatigue | An overwhelming sense of tiredness and lack of energy | Sensation écrasante de lassitude et de manque d'énergie | إحساس شديد بالإرهاق وقلة الطاقة |
| [ ] | 32 | 3889 | Dizziness | A sensation of spinning or being unbalanced | Sensation de rotation ou de perte d'équilibre | إحساس بالدوران أو فقدان التوازن |
| [ ] | 33 | 3890 | Nausea | An unpleasant feeling in the stomach with the urge to vomit | Sensation désagréable dans l'estomac avec envie de vomir | إحساس غير مريح في المعدة مع رغبة في التقيؤ |
| [ ] | 34 | 3891 | Vomiting | Forceful expulsion of stomach contents through the mouth | Expulsion violente du contenu de l'estomac par la bouche | طرد قسري لمحتويات المعدة عبر الفم |
| [ ] | 35 | 3892 | Diarrhea | Frequent loose or watery bowel movements | Selles fréquentes, molles ou liquides | حركات أمعاء متكررة وسائلة أو رخوة |
| [ ] | 36 | 3893 | Constipation | Difficulty passing stools; infrequent bowel movements | Difficulté à évacuer les selles et selles rares | صعوبة في التبرز وحركات أمعاء متباعدة |
| [ ] | 37 | 3894 | Shortness of breath | Difficulty breathing normally | Difficulté à respirer normalement | صعوبة في التنفس الطبيعي |
| [ ] | 38 | 3895 | Chest pain | Discomfort or pain felt in the chest area | Gêne ou douleur ressentie dans la région thoracique | انزعاج أو ألم في منطقة الصدر |
| [ ] | 39 | 3896 | Headache | Pain or discomfort in the head or neck region | Douleur ou gêne dans la tête ou la nuque | ألم أو انزعاج في الرأس أو الرقبة |
| [ ] | 40 | 3897 | Migraine | A severe recurrent headache often with nausea and sensitivity to light | Mal de tête sévère et récidivant, souvent avec nausées et sensibilité à la lumière | صداع شديد متكرر غالبًا مع غثيان وحساسية للضوء |
| [ ] | 41 | 3898 | Rash | A change in skin color or texture; red, itchy spots | Modification de la couleur ou de la texture de la peau, avec taches rouges et prurigineuses | تغير في لون الجلد أو ملمسه مع بقع حمراء مصحوبة بالحكة |
| [ ] | 42 | 3899 | Itching | An irritating sensation on the skin that provokes scratching | Sensation irritante sur la peau qui provoque des grattages | إحساس مثير للتهيج في الجلد يستدعي الحك |
| [ ] | 43 | 3900 | Swelling | Enlargement of a body part due to fluid accumulation | Augmentation de volume d'une partie du corps par accumulation de liquide | انتفاخ جزء من الجسم بسبب تراكم السوائل |
| [ ] | 44 | 3901 | Bleeding | The loss of blood from damaged blood vessels | Perte de sang due à des vaisseaux endommagés | فقدان الدم من الأوعية الدموية التالفة |
| [ ] | 45 | 3902 | Bruise | A discolored area of skin caused by a blow or injury | Zone de peau décolorée suite à un choc ou à un traumatisme | منطقة متغيرة اللون في الجلد نتيجة ضربة أو إصابة |
| [ ] | 46 | 3903 | Numbness | Loss of sensation in a part of the body | Perte de la sensibilité dans une partie du corps | فقدان الإحساس في جزء من الجسم |
| [ ] | 47 | 3904 | Tingling | A prickling or pins-and-needles feeling | Sensation de picotement ou de fourmillements | إحساس بالوخز أو الوخز الخفيف كالدبابيس |
| [ ] | 48 | 3905 | Weakness | Lack of physical or muscle strength | Manque de force physique ou musculaire | نقص في القوة البدنية أو العضلية |
| [ ] | 49 | 3906 | Loss of appetite | A reduced desire to eat | Réduction du désir de manger | انخفاض الرغبة في الطعام |
| [ ] | 50 | 3907 | Weight loss | An unintentional decrease in body weight | Diminution involontaire du poids corporel | انخفاض غير مقصود في وزن الجسم |
| [ ] | 51 | 3908 | Night sweats | Episodes of excessive sweating during sleep | Épisodes de transpiration excessive pendant le sommeil | نوبات تعرق مفرط أثناء النوم |
| [ ] | 52 | 3909 | Chills | A feeling of coldness with shivering | Sensation de froid accompagnée de frissons | إحساس بالبرد مصحوب بقشعريرة |
| [ ] | 53 | 3910 | Sore throat | Pain or irritation in the throat | Douleur ou irritation dans la gorge | ألم أو تهيّج في الحلق |
| [ ] | 54 | 3911 | Runny nose | Excessive discharge of mucus from the nose | Écoulement abondant de mucus nasal | إفرازات مخاطية غزيرة من الأنف |
| [ ] | 55 | 3912 | Nasal congestion | Blockage of nasal passages due to swollen membranes | Obstruction des fosses nasales par gonflement des muqueuses | انسداد الممرات الأنفية بسبب تورم الأغشية المخاطية |
| [ ] | 56 | 3913 | Blurred vision | Lack of sharpness or clarity of vision | Manque de netteté ou de clarté de la vision | غياب الوضوح أو الحدة في الإبصار |
| [ ] | 57 | 3914 | Palpitations | An awareness of rapid or irregular heartbeats | Perception de battements cardiaques rapides ou irréguliers | الإحساس بنبضات قلب سريعة أو غير منتظمة |
| [ ] | 58 | 3915 | Insomnia | Difficulty falling or staying asleep | Difficulté à s'endormir ou à rester endormi | صعوبة في الدخول في النوم أو البقاء نائمًا |
| [ ] | 59 | 3916 | Fainting | A temporary loss of consciousness | Perte temporaire de conscience | فقدان مؤقت للوعي |
| [ ] | 60 | 3917 | Confusion | A state of disorientation or unclear thinking | État de désorientation ou de pensée confuse | حالة من الارتباك أو تشتت التفكير |

## Common Illnesses & Conditions (40)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 61 | 3918 | Cold (Common cold) | A mild viral infection of the nose and throat | Infection virale légère du nez et de la gorge | عدوى فيروسية خفيفة في الأنف والحلق |
| [ ] | 62 | 3919 | Flu (Influenza) | A contagious respiratory illness caused by influenza viruses | Maladie respiratoire contagieuse causée par les virus grippaux | مرض تنفسي معدٍّ تسببه فيروسات الإنفلونزا |
| [ ] | 63 | 3920 | COVID-19 | A respiratory illness caused by the SARS-CoV-2 virus | Maladie respiratoire causée par le virus SARS-CoV-2 | مرض تنفسي يسببه فيروس سارس-كوف-2 |
| [ ] | 64 | 3921 | Hypertension | Persistently high blood pressure (>140/90 mmHg) | Tension artérielle constamment élevée (supérieure à 140/90 mmHg) | ارتفاع مستمر في ضغط الدم (أكثر من 140/90 ملم زئبق) |
| [ ] | 65 | 3922 | Diabetes | A disease causing high blood glucose levels | Maladie provoquant une glycémie élevée | مرض يسبب ارتفاع مستوى السكر في الدم |
| [ ] | 66 | 3923 | Asthma | A condition causing airway inflammation and breathing difficulty | Affection provoquant une inflammation des voies aériennes et une gêne respiratoire | حالة تسبب التهاب المسالك الهوائية وصعوبة في التنفس |
| [ ] | 67 | 3924 | Allergy | An immune system overreaction to a harmless substance | Réaction excessive du système immunitaire à une substance inoffensive | رد فعل مبالغ فيه من الجهاز المناعي تجاه مادة غير ضارة |
| [ ] | 68 | 3925 | Anemia | A deficiency of red blood cells or hemoglobin | Déficit en globules rouges ou en hémoglobine | نقص في كريات الدم الحمراء أو الهيموغلوبين |
| [ ] | 69 | 3926 | Arthritis | Inflammation of one or more joints | Inflammation d'une ou de plusieurs articulations | التهاب واحد أو أكثر من المفاصل |
| [ ] | 70 | 3927 | Osteoporosis | A condition where bones become weak and brittle | Affection fragilisant les os, qui les rend cassants | حالة تضعف العظام فتصبح هشة |
| [ ] | 71 | 3928 | Cancer | A disease where abnormal cells grow uncontrollably | Maladie dans laquelle des cellules anormales se multiplient de façon incontrôlée | مرض تنمو فيه خلايا غير طبيعية دون توقف |
| [ ] | 72 | 3929 | Heart disease | A range of conditions affecting the heart's function | Ensemble d'affections altérant la fonction du cœur | مجموعة أمراض تؤثر في وظائف القلب |
| [ ] | 73 | 3930 | Stroke | A sudden interruption of blood flow to the brain | Interruption brutale de l'irrigation sanguine du cerveau | توقف مفاجئ في وصول الدم إلى الدماغ |
| [ ] | 74 | 3931 | Heart attack | A sudden blockage of blood flow to the heart muscle | Obstruction brutale de l'irrigation du muscle cardiaque | انسداد مفاجئ في وصول الدم إلى عضلة القلب |
| [ ] | 75 | 3932 | Pneumonia | An infection causing lung inflammation | Infection provoquant une inflammation des poumons | عدوى تسبب التهابًا في الرئتين |
| [ ] | 76 | 3933 | Bronchitis | Inflammation of the bronchial tubes in the lungs | Inflammation des bronches pulmonaires | التهاب في القصبات الهوائية |
| [ ] | 77 | 3934 | Tuberculosis (TB) | A bacterial infection primarily affecting the lungs | Infection bactérienne touchant principalement les poumons | عدوى بكتيرية تصيب الرئتين بشكل أساسي |
| [ ] | 78 | 3935 | Kidney stones | Hard deposits of minerals and salts in the kidneys | Dépôts durs de minéraux et de sels dans les reins | رواسب صلبة من المعادن والأملاح في الكلى |
| [ ] | 79 | 3936 | Urinary tract infection (UTI) | A bacterial infection of the urinary system | Infection bactérienne du système urinaire | عدوى بكتيرية في الجهاز البولي |
| [ ] | 80 | 3937 | Gastritis | Inflammation of the stomach lining | Inflammation de la muqueuse de l'estomac | التهاب في بطانة المعدة |
| [ ] | 81 | 3938 | Ulcer | A sore or open wound on the skin or mucous membrane | Plaie ouverte ou lésion sur la peau ou sur une muqueuse | قرحة أو جرح مفتوح في الجلد أو الغشاء المخاطي |
| [ ] | 82 | 3939 | Appendicitis | Inflammation of the appendix | Inflammation de l'appendice | التهاب الزائدة الدودية |
| [ ] | 83 | 3940 | Hepatitis | Inflammation of the liver, often caused by viruses | Inflammation du foie, souvent causée par un virus | التهاب الكبد غالبًا بسبب فيروس |
| [ ] | 84 | 3941 | Chickenpox | A highly contagious viral disease causing itchy spots | Maladie virale très contagieuse provoquant des taches prurigineuses | مرض فيروس شديد العدوى يسبب بقعًا حكة |
| [ ] | 85 | 3942 | Measles | A contagious viral disease causing fever and rash | Maladie virale contagieuse provoquant fièvre et éruption cutanée | مرض فيروسي معدٍّ يسبب الحمى والطفح الجلدي |
| [ ] | 86 | 3943 | Malaria | A parasitic disease transmitted by mosquito bites | Maladie parasitaire transmise par les piqûres de moustique | مرض طفيلي ينتقل عبر لدغات البعوض |
| [ ] | 87 | 3944 | HIV/AIDS | A virus that attacks the immune system | Virus qui attaque le système immunitaire | فيروس يهاجم الجهاز المناعي |
| [ ] | 88 | 3945 | Depression | A mood disorder causing persistent sadness and loss of interest | Trouble de l'humeur causant une tristesse persistante et une perte d'intérêt | اضطراب مزاجي يسبب حزنًا مستمرًا وفقدانًا للاهتمام |
| [ ] | 89 | 3946 | Anxiety | Excessive worry or fear that affects daily life | Inquiétude ou peur excessive qui affecte la vie quotidienne | قلق أو خوف مفرط يؤثر على الحياة اليومية |
| [ ] | 90 | 3947 | Epilepsy | A neurological disorder causing recurrent seizures | Trouble neurologique provoquant des convulsions récurrentes | اضطراب عصبي يسبب نوبات تشنجية متكررة |
| [ ] | 91 | 3948 | Seizure | A sudden uncontrolled electrical disturbance in the brain | Perturbation électrique soudaine et incontrôlée dans le cerveau | اضطراب كهربائي مفاجئ وغير منضبط في الدماغ |
| [ ] | 92 | 3949 | Parkinson's disease | A nervous system disorder affecting movement | Trouble du système nerveux affectant le mouvement | اضطراب في الجهاز العصبي يؤثر على الحركة |
| [ ] | 93 | 3950 | Alzheimer's disease | A progressive brain disease causing memory and cognitive decline | Maladie cérébrale progressive entraînant une perte de mémoire et des facultés cognitives | مرض دماغي تدريجي يسبب فقدان الذاكرة والقدرات المعرفية |
| [ ] | 94 | 3951 | Obesity | Excessive body fat accumulation harmful to health | Excès de graisse corporelle nocif pour la santé | تراكم مفرط للدهون في الجسم يضر بالصحة |
| [ ] | 95 | 3952 | Eczema | A skin condition causing red, inflamed, itchy patches | Affection cutanée provoquant des plaques rouges, inflammées et prurigineuses | حالة جلدية تسبب بقعًا حمراء ملتهبة ومسببة للحكة |
| [ ] | 96 | 3953 | Psoriasis | An autoimmune condition causing rapid skin cell buildup | Maladie auto-immune entraînant une accumulation rapide des cellules cutanées | مرض مناعي ذاتي يسبب تراكمًا سريعًا لخلايا الجلد |
| [ ] | 97 | 3954 | Thyroid disease | Conditions affecting thyroid gland function | Affections altérant le fonctionnement de la glande thyroïde | أمراض تؤثر في عمل الغدة الدرقية |
| [ ] | 98 | 3955 | Anaphylaxis | A life-threatening severe allergic reaction | Réaction allergique sévère potentiellement mortelle | رد فعل تحسسي شديد قد يكون قاتلًا |
| [ ] | 99 | 3956 | Food poisoning | Illness caused by consuming contaminated food | Maladie due à l'ingestion d'aliments contaminés | مرض ناتج عن تناول طعام ملوث |
| [ ] | 100 | 3957 | Infection | Invasion of the body by harmful microorganisms | Invasion de l'organisme par des micro-organismes pathogènes | غزو الجسم بواسطة كائنات دقيقة ضارة |

## Body Parts (30)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 101 | 3958 | Head | The upper part of the body containing the brain | Partie supérieure du corps contenant le cerveau | الجزء العلوي من الجسم الذي يحتوي على الدماغ |
| [ ] | 102 | 3959 | Neck | The part of the body connecting the head to the torso | Partie du corps reliant la tête au torse | الجزء الذي يربط الرأس بالجذع |
| [ ] | 103 | 3960 | Chest | The area of the body between the neck and abdomen | Région du corps située entre le cou et l'abdomen | منطقة الجسم الواقعة بين الرقبة والبطن |
| [ ] | 104 | 3961 | Abdomen (Belly) | The area of the body between the chest and pelvis | Région du corps située entre la poitrine et le bassin | منطقة الجسم الواقعة بين الصدر والحوض |
| [ ] | 105 | 3962 | Back | The rear surface of the human body from neck to pelvis | Face postérieure du corps humain, du cou au bassin | الظهر البشري من الرقبة إلى الحوض |
| [ ] | 106 | 3963 | Shoulder | The joint connecting the arm to the body | Articulation reliant le bras au corps | المفصل الذي يربط الذراع بالجسم |
| [ ] | 107 | 3964 | Arm | The upper limb from shoulder to wrist | Membre supérieur de l'épaule au poignet | الطرف العلوي من الكتف إلى الرسغ |
| [ ] | 108 | 3965 | Elbow | The joint in the middle of the arm | Articulation située au milieu du bras | المفصل الموجود في منتصف الذراع |
| [ ] | 109 | 3966 | Wrist | The joint connecting the hand and forearm | Articulation reliant la main à l'avant-bras | المفصل الذي يربط اليد بالساعد |
| [ ] | 110 | 3967 | Hand | The end part of a person's arm beyond the wrist | Extrémité du bras située au-delà du poignet | طرف الذراع الواقع بعد الرسغ |
| [ ] | 111 | 3968 | Finger | Each of the digits on a hand | Chacun des doigts de la main | كل إصبع من أصابع اليد |
| [ ] | 112 | 3969 | Thumb | The short thick first digit of the human hand | Le premier doigt de la main humaine, court et épais | الإبهام وهو أول أصابع اليد وأسمكها |
| [ ] | 113 | 3970 | Hip | The joint where the leg connects to the pelvis | Articulation où la jambe se connecte au bassin | المفصل الذي تتصل عنده الساق بالحوض |
| [ ] | 114 | 3971 | Leg | The lower limb from hip to foot | Membre inférieur allant de la hanche au pied | الطرف السفلي من الورك إلى القدم |
| [ ] | 115 | 3972 | Knee | The joint in the middle of the leg | Articulation située au milieu de la jambe | المفصل الموجود في منتصف الساق |
| [ ] | 116 | 3973 | Ankle | The joint connecting the foot and leg | Articulation reliant le pied à la jambe | المفصل الذي يربط القدم بالساق |
| [ ] | 117 | 3974 | Foot | The lower extremity of the leg below the ankle | Extrémité inférieure de la jambe, sous la cheville | الطرف السفلي للساق أسفل الكاحل |
| [ ] | 118 | 3975 | Toe | Each of the digits on a foot | Chacun des orteils du pied | كل إصبع من أصابع القدم |
| [ ] | 119 | 3976 | Eye | The organ of sight | Organe de la vision | العضو المسؤول عن الإبصار والرؤية |
| [ ] | 120 | 3977 | Ear | The organ of hearing and balance | Organe de l'ouïe et de l'équilibre | عضو السمع والتوازن |
| [ ] | 121 | 3978 | Nose | The organ used for smelling and breathing | Organe servant à sentir et à respirer | عضو الشم والتنفس |
| [ ] | 122 | 3979 | Mouth | The opening through which food enters the body | Ouverture par laquelle les aliments pénètrent dans le corps | فتحة يدخل منها الطعام إلى الجسم |
| [ ] | 123 | 3980 | Throat | The passage in the neck for food and air | Passage du cou pour les aliments et l'air | ممر في الرقبة للطعام والهواء |
| [ ] | 124 | 3981 | Tongue | The muscular organ in the mouth used for tasting and speaking | Organe musculaire de la bouche servant au goût et à la parole | عضو عضلي في الفم يُستخدم للتذوق والتحدث |
| [ ] | 125 | 3982 | Tooth / Teeth | Hard calcified structures in the mouth used for chewing | Structures dures et calcifiées de la bouche servant à mastiquer | بنية صلبة متكلسة في الفم تُستخدم للمضغ |
| [ ] | 126 | 3983 | Skin | The outer covering of the body | Enveloppe extérieure du corps | الغلاف الخارجي للجسم |
| [ ] | 127 | 3984 | Spine (Backbone) | The series of vertebrae forming the backbone | Ensemble des vertèbres formant la colonne vertébrale | مجموعة الفقرات التي تشكّل العمود الفقري |
| [ ] | 128 | 3985 | Rib | One of the curved bones forming the chest wall | L'un des os courbes formant la paroi thoracique | أحد العظام المنحنية التي تشكّل جدار القفص الصدري |
| [ ] | 129 | 3986 | Pelvis | The large bony structure at the base of the spine | Large structure osseuse à la base de la colonne vertébrale | بنية عظمية كبيرة في قاعدة العمود الفقري |
| [ ] | 130 | 3987 | Groin | The area between the abdomen and thigh | Zone située entre l'abdomen et la cuisse | المنطقة الواقعة بين البطن والفخذ |

## Internal Organs (15)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 131 | 3988 | Brain | The central organ of the nervous system | Organe central du système nerveux | العضو المركزي للجهاز العصبي |
| [ ] | 132 | 3989 | Heart | The muscular organ that pumps blood | Organe musculaire qui pompe le sang | عضو عضلي يضخ الدم |
| [ ] | 133 | 3990 | Lungs | The organs responsible for breathing and gas exchange | Organes responsables de la respiration et des échanges gazeux | أعضاء مسؤولة عن التنفس وتبادل الغازات |
| [ ] | 134 | 3991 | Liver | The largest internal organ; processes nutrients and removes toxins | Plus grand organe interne ; il transforme les nutriments et élimine les toxines | أكبر عضو داخلي، يعالج العناصر الغذائية ويزيل السموم |
| [ ] | 135 | 3992 | Stomach | The organ that digests food | Organe qui digère les aliments | العضو الذي يهضم الطعام |
| [ ] | 136 | 3993 | Kidney | The organ that filters blood and produces urine | Organe qui filtre le sang et produit l'urine | العضو الذي يرشّح الدم وينتج البول |
| [ ] | 137 | 3994 | Intestine | The long tube that digests and absorbs food | Long tube qui digère et absorbe les aliments | أنبوب طويل يهضم ويمتص الطعام |
| [ ] | 138 | 3995 | Pancreas | A gland that aids digestion and regulates blood sugar | Glande qui facilite la digestion et régule la glycémie | غدة تساعد على الهضم وتتحكم في مستوى السكر بالدم |
| [ ] | 139 | 3996 | Gallbladder | A small organ that stores bile | Petit organe qui stocke la bile | عضو صغير يخزن الصفراء |
| [ ] | 140 | 3997 | Bladder | The organ that stores urine | Organe qui stocke l'urine | العضو الذي يخزن البول |
| [ ] | 141 | 3998 | Spleen | An organ involved in blood filtration and immunity | Organe participant à la filtration du sang et à l'immunité | عضو يشارك في ترشيح الدم والمناعة |
| [ ] | 142 | 3999 | Thyroid | A gland in the neck that regulates metabolism | Glande du cou qui régule le métabolisme | غدة في الرقبة تنظم الأيض |
| [ ] | 143 | 4000 | Uterus (Womb) | The female reproductive organ where a fetus develops | Organe reproducteur féminin où se développe le fœtus | العضو التناسلي الأنثوي الذي ينمو فيه الجنين |
| [ ] | 144 | 4001 | Ovary | The female reproductive organs that produce eggs | Organes reproducteurs femelles qui produisent les ovules | العضوان التناسليان الأنثويان اللذان ينتجان البويضة |
| [ ] | 145 | 4002 | Prostate | A gland in men that produces seminal fluid | Glande chez l'homme qui produit le liquide séminal | غدة عند الرجل تنتج السائل المنوي |

## Medications & Pharmacy (30)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 146 | 4003 | Prescription | A doctor's written order for medicine | Ordonnance écrite d'un médecin pour un médicament | وصفة طبية مكتوبة من الطبيب لصرف الدواء |
| [ ] | 147 | 4004 | Pharmacy (Drugstore) | A shop where medicines are dispensed | Commerce où les médicaments sont délivrés | محل تُصرف فيه الأدوية |
| [ ] | 148 | 4005 | Pharmacist | A healthcare professional who prepares and dispenses medicines | Professionnel de santé qui prépare et délivre les médicaments | مقدم رعاية صحية يحضّر الأدوية ويصرّفها |
| [ ] | 149 | 4006 | Tablet | A solid compressed medicine in pill form | Médicament solide comprimé sous forme de pilule | دواء صلب مضغوط على شكل قرص |
| [ ] | 150 | 4007 | Capsule | A small gelatin container holding medicine | Petit conteneur gélatiné contenant un médicament | كبسولة جيلاتينية صغيرة تحتوي الدواء |
| [ ] | 151 | 4008 | Syrup | A liquid form of medicine | Forme liquide d'un médicament | الشكل السائل للدواء |
| [ ] | 152 | 4009 | Injection (Shot) | A method of giving medicine using a needle and syringe | Mode d'administration d'un médicament à l'aide d'une aiguille et d'une seringue | طريقة إعطاء الدواء بإبرة وحقن |
| [ ] | 153 | 4010 | Vaccine | A preparation that provides immunity against a disease | Préparation qui confère une immunité contre une maladie | تحضير يوفر مناعة ضد مرض معين |
| [ ] | 154 | 4011 | Antibiotic | A medicine that kills or inhibits bacterial growth | Médicament qui tue les bactéries ou empêche leur multiplication | دواء يقتل البكتيريا أو يمنع نموها |
| [ ] | 155 | 4012 | Painkiller (Analgesic) | A drug that relieves pain | Médicament qui soulage la douleur | دواء يخفف الألم |
| [ ] | 156 | 4013 | Anti-inflammatory | A drug that reduces inflammation | Médicament qui réduit l'inflammation | دواء يخفض الالتهاب |
| [ ] | 157 | 4014 | Antidepressant | A drug used to treat depression and anxiety | Médicament utilisé pour traiter la dépression et l'anxiété | دواء يُستخدم لعلاج الاكتئاب والقلق |
| [ ] | 158 | 4015 | Antihistamine | A drug that blocks histamine to reduce allergic reactions | Médicament qui bloque l'histamine afin de réduire les réactions allergiques | دواء يمنع عمل الهيستامين لتقليل ردود الفعل التحسسية |
| [ ] | 159 | 4016 | Inhaler | A device for breathing in medicine | Dispositif servant à inhaler un médicament | جهاز يُستخدم لاستنشاق الدواء |
| [ ] | 160 | 4017 | Dosage | The size or frequency of a medicine dose | Taille ou fréquence d'une dose de médicament | حجم جرعة الدواء أو تكرارها |
| [ ] | 161 | 4018 | Side effect | An unwanted effect caused by a drug | Effet indésirable provoqué par un médicament | أثر جانبي غير مرغوب يسببه الدواء |
| [ ] | 162 | 4019 | Overdose | Taking too much of a medication, which can be dangerous | Prise excessive d'un médicament, qui peut être dangereuse | جرعة زائدة من الدواء قد تكون خطيرة |
| [ ] | 163 | 4020 | Ibuprofen | A common anti-inflammatory painkiller | Anti-inflammatoire et antalgique d'usage courant | دواء مسكن ومضاد للالتهاب شائع الاستخدام |
| [ ] | 164 | 4021 | Paracetamol (Acetaminophen) | A widely used painkiller and fever reducer | Antalgique et antipyrétique largement utilisé | مسكن للألم وخافض للحرارة شائع الاستخدام |
| [ ] | 165 | 4022 | Aspirin | A drug used for pain relief and blood clot prevention | Médicament utilisé contre la douleur et pour prévenir les caillots | دواء يخفف الألم ويمنع تكوّن الجلطات |
| [ ] | 166 | 4023 | Insulin | A hormone used as medication to control blood glucose | Hormone utilisée comme médicament pour contrôler la glycémie | هرمون يُستخدم كدواء للتحكم في سكر الدم |
| [ ] | 167 | 4024 | Antacid | A medicine that neutralizes stomach acid | Médicament qui neutralise l'acidité gastrique | دواء يعادل حموضة المعدة |
| [ ] | 168 | 4025 | Laxative | A substance that loosens stools and promotes bowel movements | Substance qui ramollit les selles et favorise le transit intestinal | مادة تليّن البراز وتحثّ على التبرز |
| [ ] | 169 | 4026 | Diuretic | A drug that increases urine output | Médicament qui augmente le volume des urines | دواء يزيد حجم البول |
| [ ] | 170 | 4027 | Ointment / Cream | A semisolid preparation applied to the skin | Préparation semi-solide appliquée sur la peau | مستحضر شبه سائل يُوضع على الجلد |
| [ ] | 171 | 4028 | Drops | Liquid medicine applied drop by drop | Médicament liquide appliqué goutte à goutte | دواء سائل يوضع قطرة بقطرة |
| [ ] | 172 | 4029 | Suppository | A solid medicine inserted into the rectum | Médicament solide introduit dans le rectum | دواء صلب يُدخَل عبر المستقيم |
| [ ] | 173 | 4030 | Blood thinner | A drug preventing blood from clotting easily | Médicament empêchant le sang de coaguler facilement | دواء يمنع تجلط الدم بسهولة |
| [ ] | 174 | 4031 | Vitamin supplement | A preparation providing vitamins not from diet | Préparation fournissant des vitamines en dehors de l'alimentation | مستحضر يوفر فيتامينات خارج الغذاء |
| [ ] | 175 | 4032 | Over-the-counter (OTC) | Medicine available without a prescription | Médicament disponible sans ordonnance | دواء متاح بدون وصفة طبية |

## Medical Procedures & Tests (20)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 176 | 4033 | Blood test | A laboratory analysis of a blood sample | Analyse en laboratoire d'un échantillon de sang | تحليل مخبري لعينة دم |
| [ ] | 177 | 4034 | Urine test | A laboratory examination of a urine sample | Examen en laboratoire d'un échantillon d'urine | فحص مخبري لعينة بول |
| [ ] | 178 | 4035 | X-ray | An imaging technique using radiation to view bones | Technique d'imagerie utilisant des rayons pour visualiser les os | تقنية تصوير بالأشعة لرؤية العظام |
| [ ] | 179 | 4036 | CT scan | A detailed X-ray imaging of the body in cross-sections | Imagerie radiographique détaillée du corps en coupes | تصوير مقطعي مفصل للجسم بالأشعة |
| [ ] | 180 | 4037 | MRI | Imaging using magnetic fields to view soft tissues | Imagerie par champs magnétiques pour visualiser les tissus mous | تصوير بالمجال المغناطيسي لرؤية الأنسجة الرخوة |
| [ ] | 181 | 4038 | Ultrasound (Scan) | Imaging using sound waves to see inside the body | Imagerie par ondes sonores pour voir à l'intérieur du corps | تصوير بالموجات فوق الصوتية لرؤية داخل الجسم |
| [ ] | 182 | 4039 | ECG / EKG | A test recording the electrical activity of the heart | Examen enregistrant l'activité électrique du cœur | فحص يسجل النشاط الكهربائي للقلب |
| [ ] | 183 | 4040 | Biopsy | Removal of tissue for microscopic examination | Prélèvement de tissu pour examen microscopique | أخذ عينة من النسيج لفحصها بالمجهر |
| [ ] | 184 | 4041 | Blood pressure check | Measurement of the force of blood against vessel walls | Mesure de la force du sang contre les parois des vaisseaux | قياس قوة ضغط الدم على جدران الأوعية الدموية |
| [ ] | 185 | 4042 | Blood sugar test | A test measuring glucose levels in the blood | Examen mesurant le taux de glucose dans le sang | فحص يقيس مستوى السكر في الدم |
| [ ] | 186 | 4043 | Cholesterol test | A blood test measuring cholesterol levels | Analyse sanguine mesurant le taux de cholestérol | فحص دم يقيس مستوى الكوليسترول |
| [ ] | 187 | 4044 | Endoscopy | Visual examination of internal organs with a camera tube | Examen visuel des organes internes à l'aide d'un tube muni d'une caméra | فحص بصري للأعضاء الداخلية بأنبوب مزود بكاميرا |
| [ ] | 188 | 4045 | Mammogram | An X-ray of the breast to detect cancer | Radiographie du sein destinée à détecter un cancer | أشعة على الثدي لكشف الأورام |
| [ ] | 189 | 4046 | Pap smear | A test collecting cells from the cervix for examination | Examen prélevant des cellules du col de l'utérus | فحص تؤخذ فيه خلايا من عنق الرحم |
| [ ] | 190 | 4047 | Eye exam | A check of vision and eye health | Contrôle de la vision et de la santé des yeux | فحص الإبصار وصحة العينين |
| [ ] | 191 | 4048 | Operation (Surgery) | A medical procedure involving cutting into the body | Acte médical impliquant une incision du corps | إجراء طبي يشمل فتح الجسم |
| [ ] | 192 | 4049 | Anesthesia | Medication used to eliminate pain during surgery | Médicament supprimant la douleur pendant une intervention | دواء يزيل الألم أثناء العملية الجراحية |
| [ ] | 193 | 4050 | Stitches (Sutures) | Thread used to close a wound or surgical cut | Fil utilisé pour refermer une plaie ou une incision chirurgicale | خيط يُستخدم لإغلاق الجرح أو الشق الجراحي |
| [ ] | 194 | 4051 | Cast | A rigid casing applied to protect and support a broken bone | Coquille rigide appliquée pour protéger et soutenir un os fracturé | قالب صلب يُوضع لحماية عظم مكسور ودعمه |
| [ ] | 195 | 4052 | Checkup | A routine medical examination | Examen médical de routine | فحص طبي دوري |

## Vital Signs & Measurements (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 196 | 4053 | Temperature | A measure of body heat | Mesure de la chaleur corporelle | قياس حرارة الجسم |
| [ ] | 197 | 4054 | Pulse | The rhythmic throbbing of arteries caused by heartbeats | Battement rhythmique des artères provoqué par les battements du cœur | نبض الشرايين الإيقاعي الناتج عن ضربات القلب |
| [ ] | 198 | 4055 | Blood pressure | The pressure of blood against artery walls | Pression du sang contre les parois des artères | ضغط الدم على جدران الشرايين |
| [ ] | 199 | 4056 | Oxygen saturation (SpO2) | The percentage of hemoglobin carrying oxygen in the blood | Pourcentage d'hémoglobine transportant de l'oxygène dans le sang | نسبة الهيموغلوبين الحامل للأكسجين في الدم |
| [ ] | 200 | 4057 | Heart rate | The number of heartbeats per minute | Nombre de battements du cœur par minute | عدد ضربات القلب في الدقيقة |
| [ ] | 201 | 4058 | Breathing rate | The number of breaths taken per minute | Nombre de respirations par minute | عدد مرات التنفس في الدقيقة |
| [ ] | 202 | 4059 | Weight | The measure of how heavy a person is | Mesure de la masse corporelle | قياس وزن الجسم |
| [ ] | 203 | 4060 | Height | The measurement of a person from head to toe | Mesure de la taille d'une personne, de la tête aux pieds | قياس طول الشخص من الرأس إلى القدمين |
| [ ] | 204 | 4061 | BMI (Body Mass Index) | A measure of body fat based on height and weight | Indice de masse corporelle fondé sur la taille et le poids | مؤشر كتلة الجسم يعتمد على الطول والوزن |
| [ ] | 205 | 4062 | Blood glucose level | The concentration of sugar (glucose) in the blood | Concentration de sucre dans le sang | تركيز السكر في الدم |

## First Aid & Emergencies (15)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 206 | 4063 | First aid | Emergency care given to an injured person before professional help | Soins d'urgence administrés à une personne blessée avant l'arrivée des secours | إسعافات أولية تُقدَّم للمصاب قبل وصول المساعدة |
| [ ] | 207 | 4064 | CPR (Cardiopulmonary resuscitation) | An emergency procedure to restore heart and lung function | Procédure d'urgence visant à rétablir les fonctions cardiaque et pulmonaire | إجراء طارئ لاستعادة عمل القلب والرئتين |
| [ ] | 208 | 4065 | Ambulance | A vehicle equipped for emergency medical transport | Véhicule équipé pour le transport médical d'urgence | مركبة مجهزة للنقل الطبي الطارئ |
| [ ] | 209 | 4066 | Bandage | A strip of material used to bind a wound | Bande de matériau servant à couvrir et à protéger une plaie | شريط من القماش يُستخدم لتضميد الجرح |
| [ ] | 210 | 4067 | Wound | An injury where the skin is broken | Lésion par laquelle la peau est rompue | إصابة ينقطع فيها الجلد |
| [ ] | 211 | 4068 | Fracture (Broken bone) | A crack or complete break in a bone | Fissure ou rupture complète d'un os | شق أو كسر كامل في العظم |
| [ ] | 212 | 4069 | Sprain | An injury to a ligament from overstretching | Lésion d'un ligament due à un étirement excessif | تمزق الرباط نتيجة تمدد مفرط |
| [ ] | 213 | 4070 | Burn | Tissue damage caused by heat, chemicals, or radiation | Lésion tissulaire causée par la chaleur, des produits chimiques ou des radiations | ضرر في الأنسجة بسبب الحرارة أو المواد الكيميائية أو الإشعاع |
| [ ] | 214 | 4071 | Choking | Blockage of the airway preventing breathing | Obstruction des voies aériennes empêchant la respiration | انسداد مجرى الهواء يمنع التنفس |
| [ ] | 215 | 4072 | Drowning | Respiratory impairment from submersion in water | Atteinte respiratoire provoquée par l'immersion dans l'eau | اضطراب تنفسي بسبب الغرق في الماء |
| [ ] | 216 | 4073 | Poisoning | Harm caused by ingesting, inhaling or absorbing a toxic substance | Préjudice causé par l'ingestion, l'inhalation ou l'absorption d'une substance toxique | ضرر ناتج عن ابتلاع أو استنشاق أو امتصاص مادة سامة |
| [ ] | 217 | 4074 | Dehydration | A condition where the body loses more fluids than taken in | État dans lequel le corps perd plus de liquides qu'il n'en reçoit | حالة يفقد فيها الجسم سوائل أكثر مما يتناول |
| [ ] | 218 | 4075 | Shock | A life-threatening condition of insufficient blood flow to organs | État menaçant le pronostic vital par insuffisance d'irrigation des organes | حالة تهدد الحياة بسبب نقص تدفق الدم إلى الأعضاء |
| [ ] | 219 | 4076 | Nosebleed | Bleeding from the blood vessels in the nose | Saignement des vaisseaux sanguins du nez | نزيف من الأوعية الدموية في الأنف |
| [ ] | 220 | 4077 | Dislocated joint | A joint forced out of its normal position | Articulation déplacée de sa position normale | مفصل خرج من موضعه الطبيعي |

## Reproductive & Sexual Health (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 221 | 4078 | Pregnancy | The period of carrying a developing fetus in the uterus | Période pendant laquelle un fœtus se développe dans l'utérus | الفترة التي ينمو فيها الجنين داخل الرحم |
| [ ] | 222 | 4079 | Contraception | Methods used to prevent pregnancy | Méthodes utilisées pour éviter une grossesse | طرق تُستخدم لمنع الحمل |
| [ ] | 223 | 4080 | Miscarriage | Spontaneous loss of a pregnancy before 20 weeks | Perte spontanée d'une grossesse avant la 20e semaine | فقدان حمل تلقائي قبل الأسبوع العشرين |
| [ ] | 224 | 4081 | Childbirth (Labor) | The process of giving birth to a baby | Processus de mettre un enfant au monde | عملية الولادة |
| [ ] | 225 | 4082 | Cesarean section (C-section) | Surgical delivery of a baby through the abdomen | Accouchement par voie chirurgicale à travers l'abdomen | ولادة جراحية تتم عبر البطن |
| [ ] | 226 | 4083 | Menstruation (Period) | The monthly shedding of the uterine lining | Chute mensuelle de la paroi utérine | سقوط بطانة الرحم شهريًا |
| [ ] | 227 | 4084 | Menopause | The end of menstrual cycles in women | Arrêt des cycles menstruels chez la femme | انقطاع الدورات الشهرية عند المرأة |
| [ ] | 228 | 4085 | STI / STD | Sexually transmitted infection or disease | Infection ou maladie sexuellement transmissible | عدوى أو مرض ينتقل عبر الاتصال الجنسي |
| [ ] | 229 | 4086 | Fertility | The ability to conceive and have children | Capacité à concevoir et à avoir des enfants | القدرة على الحمل وإنجاب الأطفال |
| [ ] | 230 | 4087 | Breastfeeding | Feeding an infant with breast milk | Alimentation d'un nourrisson par le lait maternel | تغذية الرضيع بحليب الأم |

## Mental Health (11)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 231 | 4088 | Mental health | Emotional, psychological, and social well-being | Bien-être émotionnel, psychologique et social | العافية النفسية والعاطفية والاجتماعية |
| [ ] | 232 | 4089 | Stress | A state of mental or emotional tension | État de tension mentale ou émotionnelle | حالة من التوتر الذهني أو العاطفي |
| [ ] | 234 | 4090 | Anxiety disorder | A mental condition marked by excessive fear and worry | Trouble mental caractérisé par une peur et une inquiétude excessives | اضطراب نفسي يتميز بالخوف والقلق المفرطين |
| [ ] | 235 | 4091 | Panic attack | A sudden episode of intense fear with physical symptoms | Épisode soudain de peur intense accompagné de symptômes physiques | نوبة مفاجئة من الخوف الشديد مصحوبة بأعراض جسدية |
| [ ] | 236 | 4092 | Phobia | An extreme or irrational fear of something | Prainte extrême ou irrationnelle envers quelque chose | خوف شديد أو غير منطقي من شيء ما |
| [ ] | 237 | 4093 | Post-traumatic stress disorder (PTSD) | A mental disorder triggered by a traumatic event | Trouble mental déclenché par un événement traumatisant | اضطراب نفسي ناتج عن حدث صادم |
| [ ] | 238 | 4094 | Eating disorder | An illness involving abnormal eating behaviors | Maladie impliquant des comportements alimentaires anormaux | مرض يتضمن سلوكيات غذائية غير طبيعية |
| [ ] | 239 | 4095 | Addiction | A compulsive dependence on a substance or behavior | Dépendance compulsive à une substance ou à un comportement | إدمان قسري لمادة أو لسلوك |
| [ ] | 240 | 4096 | Therapy | Treatment of a disorder through talking and behavioral strategies | Traitement d'un trouble par la parole et par des stratégies comportementales | علاج اضطراب عبر الحوار واستراتيجيات سلوكية |
| [ ] | 241 | 4097 | Counseling | Professional guidance for mental and emotional issues | Orientation professionnelle sur les problèmes mentaux et émotionnels | إرشاد مهني للمشكلات النفسية والعاطفية |
| [ ] | 242 | 4098 | Burnout | Extreme exhaustion from prolonged stress, especially at work | Épuisement extrême dû à un stress prolongé, surtout au travail | إرهاق شديد بسبب التوتر الطويل خاصة في العمل |

## Nutrition & Healthy Lifestyle (14)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 243 | 4099 | Nutrition | The process of obtaining and using food for health | Processus d'obtention et d'utilisation des aliments au profit de la santé | عملية الحصول على الغذاء واستخدامه للحفاظ على الصحة |
| [ ] | 244 | 4100 | Calorie | A unit of energy provided by food | Unité d'énergie fournie par les aliments | وحدة طاقة يوفرها الطعام |
| [ ] | 245 | 4101 | Carbohydrate | A macronutrient providing the body's main energy source | Macro-nutriment constituant la principale source d'énergie du corps | عنصر غذائي كلي يمثل مصدر الطاقة الرئيسي للجسم |
| [ ] | 246 | 4102 | Protein | A macronutrient essential for building and repairing tissues | Macro-nutriment essentiel à la construction et à la réparation des tissus | عنصر غذائي كلي ضروري لبناء الأنسجة وإصلاحها |
| [ ] | 247 | 4103 | Fat | A macronutrient essential for energy and cell function | Macro-nutriment essentiel à l'énergie et au fonctionnement cellulaire | مادة دهنية أساسية للطاقة وعمل الخلية |
| [ ] | 248 | 4104 | Fiber | Plant-based carbohydrates that aid digestion | Glucides végétaux qui facilitent la digestion | الألياف النباتية التي تساعد على الهضم |
| [ ] | 249 | 4105 | Vitamin | An organic compound essential for normal body function | Composé organique essentiel au fonctionnement normal du corps | مركب عضوي أساسي لعمل الجسم الطبيعي |
| [ ] | 250 | 4106 | Mineral | An inorganic nutrient needed by the body | Nutriment inorganique nécessaire au corps | معدن غذائي يحتاجه الجسم |
| [ ] | 251 | 4107 | Hydration | Maintaining adequate fluid levels in the body | Maintien d'un niveau adéquat de liquides dans le corps | الحفاظ على مستوى كافٍ من السوائل في الجسم |
| [ ] | 252 | 4108 | Exercise | Physical activity done to improve health and fitness | Activité physique visant à améliorer la santé et la forme physique | نشاط جسدي يُجرى لتحسين الصحة ولياقة البدن |
| [ ] | 254 | 4109 | Cholesterol | A fatty substance in the blood essential but harmful in excess | Substance grasse du sang, essentielle mais nocive en excès | مادة دهنية في الدم ضرورية لكنها ضارة عند الزيادة |
| [ ] | 255 | 4110 | Diet | The kinds of food a person habitually eats | Types d'aliments qu'une personne consomme habituellement | أنواع الطعام التي يتناولها الشخص عادة |
| [ ] | 256 | 4111 | Smoking | The inhalation of tobacco smoke, which is harmful to health | Inhalation de fumée de tabac, nocive pour la santé | استنشاق دخان التبغ وهو ضار بالصحة |
| [ ] | 257 | 4112 | Alcohol | A depressant substance harmful in large quantities | Substance dépressive nocive en grande quantité | مادة مضعفة وضارة عند تناولها بكميات كبيرة |

## Important Medical Concepts (22)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 258 | 4113 | Chronic | A long-lasting condition persisting for months or years | Affection de longue durée, persistant des mois ou des années | حالة مزمنة تستمر لأشهر أو سنوات |
| [ ] | 259 | 4114 | Acute | A condition with sudden onset and short duration | Affection à début brutal et de courte durée | حالة تبدأ فجأة وتستمر مدة قصيرة |
| [ ] | 260 | 4115 | Contagious | A disease that can be spread from person to person | Maladie qui peut se transmettre d'une personne à une autre | مرض يمكن انتقاله من شخص إلى آخر |
| [ ] | 261 | 4116 | Terminal illness | An illness that cannot be cured and will cause death | Maladie incurable qui entraînera la mort | مرض لا يمكن شفاؤه وسيؤدي إلى الوفاة |
| [ ] | 262 | 4117 | Relapse | The return of a disease after recovery | Retour d'une maladie après une guérison | عودة المرض بعد التعافي منه |
| [ ] | 263 | 4118 | Recovery | The process of returning to normal health after illness | Processus de retour à la santé normale après une maladie | عملية العودة إلى الصحة الطبيعية بعد المرض |
| [ ] | 264 | 4119 | Immune system | The body's defense network against diseases | Réseau de défense de l'organisme contre les maladies | شبكة دفاع الجسم ضد الأمراض |
| [ ] | 265 | 4120 | Inflammation | The body's response to injury or infection | Réponse de l'organisme à une lésion ou à une infection | استجابة الجسم للضرر أو العدوى |
| [ ] | 267 | 4121 | Hygiene | Practices that maintain health and prevent disease | Pratiques qui maintiennent la santé et préviennent les maladies | ممارسات تحافظ على الصحة وتمنع الأمراض |
| [ ] | 268 | 4122 | Prevention | Measures taken to stop disease from occurring | Mesures prises pour empêcher l'apparition d'une maladie | إجراءات تُتخذ لمنع حدوث المرض |
| [ ] | 269 | 4123 | Hereditary | A trait or disease passed down through families | Caractère ou maladie transmis au sein des familles | صفة أو مرض ينتقل عبر الأجيال |
| [ ] | 270 | 4124 | Genetic | Related to genes and inherited traits | Lié aux gènes et aux caractères hérités | يتعلق بالجينات والصفات الموروثة |
| [ ] | 271 | 4125 | Diagnosis | Identifying a disease based on its signs and symptoms | Identification d'une maladie d'après ses signes et ses symptômes | تشخيص المرض بناءً على علاماته وأعراضه |
| [ ] | 272 | 4126 | Treatment | Medical care given to a patient for a disease | Soins médicaux administrés à un patient pour une maladie | العلاج الطبي المقدم للمريض بسبب مرض ما |
| [ ] | 273 | 4127 | Complication | A secondary disease or condition arising from a primary one | Maladie ou état secondaire résultant d'une maladie primaire | مرض أو حالة ثانوية تنشأ عن مرض أساسي |
| [ ] | 274 | 4128 | Risk factor | Anything that increases the chance of developing a disease | Tout élément augmentant la probabilité de développer une maladie | كل عامل يزيد احتمال الإصابة بمرض ما |
| [ ] | 275 | 4129 | Screening | Tests done to detect diseases early before symptoms appear | Examens réalisés pour dépister une maladie tôt, avant l'apparition des symptômes | فحوصات تهدف إلى الكشف المبكر عن المرض قبل ظهور الأعراض |
| [ ] | 276 | 4130 | Prognosis | The likely course or outcome of a disease | Évolution ou issue probable d'une maladie | المسار أو النتيجة المتوقعة لمرض ما |
| [ ] | 277 | 4131 | Placebo | An inactive substance that may cause improvement due to belief | Substance inerte pouvant améliorer l'état par le seul effet de la croyance | مادة خاملة قد تحسن الحالة بمجرد الإيمان بتأثيرها |
| [ ] | 278 | 4132 | Clinical trial | A research study testing new medical treatments on humans | Étude de recherche testant de nouveaux traitements sur l'homme | دراسة بحثية تجرّب علاجات جديدة على الإنسان |
| [ ] | 279 | 4133 | Second opinion | Getting advice from another doctor about a diagnosis | Demande d'avis à un autre médecin au sujet d'un diagnostic | طلب رأي طبيب آخر في التشخيص |
| [ ] | 280 | 4134 | Consent | Permission given for a medical procedure | Autorisation donnée pour un acte médical | الموافقة الممنوحة لإجراء طبي |

## Describing Pain & Location (15)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 281 | 4135 | Sharp pain | A sudden intense stabbing pain | Douleur intense et soudaine, en coup de poignard | ألم شديد ومفاجئ كالطعن |
| [ ] | 282 | 4136 | Dull ache | A steady, moderate pain that persists | Douleur modérée et constante qui persiste | ألم متوسط الثبات يستمر |
| [ ] | 283 | 4137 | Burning pain | A sensation of heat or fire in a body part | Sensation de chaleur ou de feu dans une partie du corps | إحساس بالحرارة أو النار في جزء من الجسم |
| [ ] | 284 | 4138 | Throbbing pain | A rhythmic, pulsating pain | Douleur rythmique et pulsatile | ألم نابض متكرر |
| [ ] | 285 | 4139 | Stabbing pain | A sudden sharp pain like being pierced | Douleur aiguë soudaine, comme une piqûre | ألم حاد مفاجئ كالوخز |
| [ ] | 286 | 4140 | Constant pain | Pain that never goes away | Douleur qui ne disparaît jamais | ألم لا يزول أبدًا |
| [ ] | 287 | 4141 | Intermittent pain | Pain that starts and stops repeatedly | Douleur qui apparaît et disparaît à répétition | ألم يتكرر فيظهر ويزول |
| [ ] | 288 | 4142 | Radiating pain | Pain that spreads from one area to another | Douleur qui s'étend d'une région à une autre | ألم ينتشر من منطقة إلى أخرى |
| [ ] | 289 | 4143 | Mild pain | Low-intensity pain that is tolerable | Douleur de faible intensité, facile à supporter | ألم ضعيف الشدة يمكن تحمّله |
| [ ] | 290 | 4144 | Severe pain | Very intense and difficult to bear pain | Douleur très intense et difficile à supporter | ألم شديد جدًا يصعب تحمّله |
| [ ] | 291 | 4145 | On a scale of 1 to 10 | The numeric pain assessment scale used by doctors | Échelle numérique d'évaluation de la douleur utilisée par les médecins | مقياس رقمي لتقييم الألم يستخدمه الأطباء |
| [ ] | 292 | 4146 | It hurts here | A phrase used to indicate the location of pain | Formule indiquant la localisation de la douleur | عبارة تدل على موضع الألم |
| [ ] | 293 | 4147 | I feel sick | Expressing general illness or nausea | Expression d'une maladie générale ou de nausées | عبارة عن مرض عام أو غثيان |
| [ ] | 294 | 4148 | I can't breathe | A phrase indicating breathing difficulty | Phrase indiquant une difficulté respiratoire | عبارة تدل على صعوبة في التنفس |
| [ ] | 295 | 4149 | I am allergic to... | A phrase to inform medical staff of allergies | Phrase permettant d'informer le personnel médical d'une allergie | عبارة لإبلاغ الطاقم الطبي بوجود حساسية |

## Dental, Eye & Ear Health (11)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 296 | 4150 | Toothache | Pain in or around a tooth | Douleur dans une dent ou autour d'elle | ألم في السن أو حوله |
| [ ] | 297 | 4151 | Cavity (Tooth decay) | Damage to a tooth caused by bacterial acid | Lésion dentaire provoquée par l'acide des bactéries | تلف في السن بسبب حمض البكتيريا |
| [ ] | 298 | 4152 | Gum disease | Infection or inflammation of the gums | Infection ou inflammation des gencives | التهاب أو عدوى في اللثة |
| [ ] | 299 | 4153 | Hearing loss | Partial or complete inability to hear | Perte partielle ou totale de l'audition | فقدان جزئي أو كلي للسمع |
| [ ] | 300 | 4154 | Ear infection | An infection in the middle or outer ear | Infection de l'oreille moyenne ou externe | عدوى في الأذن الوسطى أو الخارجية |
| [ ] | 301 | 4155 | Glasses / Spectacles | Lenses worn to correct vision problems | Lenses portées pour corriger les problèmes de vision | عدسات تُلبس لتصحيح مشاكل الإبصار |
| [ ] | 302 | 4156 | Contact lenses | Small lenses placed on the eye to correct vision | Petites lenses posées sur l'œil pour corriger la vision | عدسات صغيرة توضع على العين لتصحيح الرؤية |
| [ ] | 303 | 4157 | Nearsighted (Myopia) | Difficulty seeing distant objects clearly | Difficulté à voir nettement les objets éloignés | صعوبة رؤية الأشياء البعيدة بوضوح |
| [ ] | 304 | 4158 | Farsighted (Hyperopia) | Difficulty seeing close objects clearly | Difficulté à voir nettement les objets proches | صعوبة رؤية الأشياء القريبة بوضوح |
| [ ] | 305 | 4159 | Cataract | A clouding of the eye lens causing vision loss | Opacification du cristallin entraînant une perte de vision | تعكّس عدسة العين مما يسبب فقدان البصر |
| [ ] | 306 | 4160 | Glaucoma | A condition damaging the optic nerve from high eye pressure | Affection prejudiciant le nerf optique en raison d'une pression oculaire élevée | حالة تضر العصب البصري بسبب ارتفاع ضغط العين |

## Useful Medical Phrases (14)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 307 | 4161 | Take this medication twice a day | A common doctor's instruction about medication frequency | Instruction courante du médecin sur la fréquence d'un médicament | تعليم شائع من الطبيب حول وتيرة تناول الدواء |
| [ ] | 308 | 4162 | Are you on any medication? | A question about current medication use | Question sur la consommation actuelle de médicaments | سؤال حول الأدوية التي يتناولها المريض حاليًا |
| [ ] | 309 | 4163 | Do you have any allergies? | A standard medical screening question | Question d'anamnèse médicale standard | سؤال قياسي ضمن تاريخ المريض المرضي |
| [ ] | 310 | 4164 | How long have you had this pain? | A question to assess duration of symptoms | Question évaluant la durée des symptômes | سؤال لتقييم مدة الأعراض |
| [ ] | 311 | 4165 | I need to examine you | A phrase indicating a physical examination | Phrase indiquant un examen physique | عبارة تدل على إجراء فحص سريري |
| [ ] | 312 | 4166 | Please take a deep breath | An instruction during chest examination | Instruction lors de l'examen thoracique | تعليم أثناء فحص الصدر |
| [ ] | 313 | 4167 | Roll up your sleeve | An instruction before giving an injection | Instruction avant une injection | تعليم قبل إعطاء الحقن |
| [ ] | 314 | 4168 | You need surgery | A medical recommendation for surgical intervention | Recommandation médicale d'une intervention chirurgicale | توصية طبية بإجراء عملية جراحية |
| [ ] | 315 | 4169 | Make a follow-up appointment | An instruction to schedule a return visit | Instruction de programmer une consultation de suivi | تعليم بحجز موعد للمتابعة |
| [ ] | 316 | 4170 | Your test results are normal | Communicating normal medical test findings | Communication de résultats d'examens normaux | إبلاغ بنتائج الفحوصات الطبيعية |
| [ ] | 317 | 4171 | Call an ambulance | An emergency instruction | Instruction d'urgence | عبارة طارئة تُطلب بها سيارة الإسعاف |
| [ ] | 318 | 4172 | Is this covered by insurance? | A common patient question about medical costs | Question fréquente du patient sur les coûts médicaux | سؤال شائع من المريض عن تكاليف العلاج |
| [ ] | 319 | 4173 | I have a family history of... | Informing the doctor about inherited health risks | Information du médecin sur les risques héréditaires | إبلاغ الطبيب بالتاريخ المرضي العائلي |
| [ ] | 320 | 4174 | I am pregnant | Important information to share with medical staff | Information importante à communiquer au personnel médical | معلومة مهمة تُبلَّغ للطاقم الطبي |

## Skin Conditions (15)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 321 | 4175 | Acne | A skin condition causing pimples and spots | Affection cutanée provoquant boutons et taches | حالة جلدية تسبب البثور والبقع |
| [ ] | 322 | 4176 | Blister | A small bubble of fluid under the skin caused by friction or burn | Petite poche de liquide sous la peau due à un frottement ou à une brûlure | فقاعة صغيرة مملوءة بالسوائل تحت الجلد نتيجة احتكاك أو حرق |
| [ ] | 323 | 4177 | Hives (Urticaria) | Raised itchy welts on the skin caused by an allergic reaction | Plaques prurigineuses en relief dues à une réaction allergique | نتوءات مرتفعة مصحوبة بحكة نتيجة تفاعل حساسي |
| [ ] | 324 | 4178 | Wart | A small rough growth on the skin caused by a virus | Petite excroissance rugueuse de la peau causée par un virus | نمو صغير خشن في الجلد سببه فيروس |
| [ ] | 325 | 4179 | Mole | A small dark spot on the skin | Petite tache sombre sur la peau | بقعة صغيرة داكنة على الجلد |
| [ ] | 326 | 4180 | Sunburn | Redness and pain caused by overexposure to the sun | Rougeur et douleur dues à une surexposition au soleil | احمرار وألم بسبب التعرض المفرط للشمس |
| [ ] | 327 | 4181 | Abscess | A pocket of pus caused by a bacterial infection | Poche de pus provoquée par une infection bactérienne | تجويف يتكون من القيح بسبب عدوى بكتيرية |
| [ ] | 328 | 4182 | Cellulitis | A bacterial skin infection causing redness and swelling | Infection bactérienne de la peau provoquant rougeur et gonflement | التهاب خلوي في الجلد يسبب احمرارًا وتورمًا |
| [ ] | 329 | 4183 | Scab | A dry crust forming over a healing wound | Croûte sèche formant sur une plaie en voie de guérison | قشرة جافة تتكون فوق جرح يلتئم |
| [ ] | 330 | 4184 | Scar | A mark left on the skin after a wound heals | Marque laissée sur la peau après la cicatrisation d'une plaie | أثر يبقى على الجلد بعد التئام الجرح |
| [ ] | 331 | 4185 | Jaundice | Yellowing of skin and eyes due to high bilirubin | Jaunissement de la peau et des yeux dû à une bilirubine élevée | اصفرار الجلد والعينين نتيجة ارتفاع البيليروبين |
| [ ] | 332 | 4186 | Pale skin | Abnormal whiteness of the skin | Blancheur anormale de la peau | شحوب غير طبيعي في الجلد |
| [ ] | 333 | 4187 | Skin cancer | Abnormal growth of skin cells caused by UV damage | Croissance anormale des cellules de la peau due aux UV | نمو غير طبيعي لخلايا الجلد بسبب الأشعة فوق البنفسجية |
| [ ] | 334 | 4188 | Dandruff | Flaking of skin from the scalp | Desquamation de la peau du cuir chevelu | تقشر فروة الرأس |
| [ ] | 335 | 4189 | Hair loss (Alopecia) | Partial or complete absence of hair from the scalp | Absence partielle ou totale de cheveux sur le cuir chevelu | فقدان جزئي أو كلي للشعر في فروة الرأس |

## Orthopedics & Sports Injuries (14)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 337 | 4190 | Strain | Overstretching or tearing of a muscle or tendon | Surmenage ou déchirure d'un muscle ou d'un tendon | تمزق أو إجهاد في العضلة أو الوتر |
| [ ] | 338 | 4191 | Dislocation | A bone forced out of its normal position in a joint | Os déplacé de sa position normale dans une articulation | خروج عظم من موضعه الطبيعي في المفصل |
| [ ] | 339 | 4192 | Tendinitis | Inflammation of a tendon | Inflammation d'un tendon | التهاب في الوتر |
| [ ] | 340 | 4193 | Torn ligament | A complete or partial tear of a ligament | Déchirure complète ou partielle d'un ligament | تمزق كامل أو جزئي في الرباط |
| [ ] | 341 | 4194 | Herniated disc | A damaged spinal disc pressing on a nerve | Disque vertébral endommagé comprimant un nerf | قرص فقري منتفخ يضغط على عصب |
| [ ] | 342 | 4195 | Scoliosis | Abnormal lateral curvature of the spine | Courbure latérale anormale de la colonne vertébrale | انحناء جانبي غير طبيعي في العمود الفقري |
| [ ] | 343 | 4196 | Carpal tunnel syndrome | Pressure on the wrist nerve causing numbness and pain | Compression du nerf du poignet provoquant engourdissement et douleur | ضغط على عصب الرسغ يسبب تنميلًا وألمًا |
| [ ] | 344 | 4197 | Joint replacement | A surgical procedure replacing a damaged joint with an implant | Intervention chirurgicale remplaçant une articulation endommagée par une prothèse | عملية جراحية تستبدل مفصلًا تالفًا بزراعة |
| [ ] | 345 | 4198 | Physiotherapy | Treatment using exercise and physical techniques to restore function | Traitement par l'exercice et des techniques physiques pour restaurer la fonction | علاج بالرياضة والتقنيات الفيزيائية لاستعادة القدرة الوظيفية |
| [ ] | 346 | 4199 | Crutches | Support devices used when walking with a leg injury | Dispositifs d'appui utilisés lors d'une blessure à la jambe | وسائل دعم تُستخدم عند المشي بعد إصابة الساق |
| [ ] | 347 | 4200 | Wheelchair | A chair on wheels for people unable to walk | Fauteuil à roues destiné aux personnes incapables de marcher | كرسي بعجلات للأشخاص غير القادرين على المشي |
| [ ] | 348 | 4201 | Brace | A device worn to support or protect a body part | Dispositif porté pour soutenir ou protéger une partie du corps | جهاز يُلبس لدعم أو حماية جزء من الجسم |
| [ ] | 349 | 4202 | Bone density | The amount of mineral in bone tissue | Quantité de minéral présente dans le tissu osseux | كمية المعادن في النسيج العظمي |
| [ ] | 350 | 4203 | Rheumatism | A general term for pain in joints, muscles and connective tissues | Terme générique désignant les douleurs articulaires, musculaires et des tissus conjonctifs | مصطلح عام يصف آلام المفاصل والعضلات والأنسجة الضامة |

## Neurology & The Brain (15)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 351 | 4204 | Concussion | A mild traumatic brain injury caused by a blow to the head | Traumatisme crânien léger causé par un choc à la tête | ارتجاج مخي خفيف ناتج عن ضربة على الرأس |
| [ ] | 352 | 4205 | Amnesia | Partial or total loss of memory | Perte partielle ou totale de la mémoire | فقدان جزئي أو كلي للذاكرة |
| [ ] | 353 | 4206 | Dementia | A decline in cognitive function severe enough to interfere with daily life | Déclin des fonctions cognitives assez severe pour gêner la vie quotidienne | تراجع في الوظائف المعرفية بما يكفي للتأثير على الحياة اليومية |
| [ ] | 354 | 4207 | Multiple sclerosis (MS) | A disease where the immune system attacks the myelin sheath of nerves | Maladie dans laquelle le système immunitaire attaque la gaine de myéline des nerfs | مرض يهاجم فيه الجهاز المناعي غشاء الميالين حول الأعصاب |
| [ ] | 355 | 4208 | Meningitis | Inflammation of the membranes surrounding the brain and spinal cord | Inflammation des méninges entourant le cerveau et la moelle épinière | التهاب الأغشية المحيطة بالدماغ والنخاع الشوكي |
| [ ] | 356 | 4209 | Vertigo | A sensation of spinning or dizziness | Sensation de rotation ou d'étourdissement | إحساس بالدوران أو الدوار |
| [ ] | 357 | 4210 | Neuropathy | Damage or dysfunction of one or more nerves | Lésion ou dysfonctionnement d'un ou plusieurs nerfs | تضرر أو خلل في عصب واحد أو أكثر |
| [ ] | 358 | 4211 | Bell's palsy | Sudden weakness or paralysis of facial muscles | Faiblesse ou paralysie brutale des muscles du visage | ضعف أو شلل مفاجئ في عضلات الوجه |
| [ ] | 359 | 4212 | Cerebral palsy | A group of disorders affecting movement caused by brain damage | Ensemble de troubles moteurs causés par une lésion cérébrale | مجموعة اضطرابات حركية ناتجة عن تلف في الدماغ |
| [ ] | 360 | 4213 | Numbness and tingling | Loss of sensation and a pins-and-needles feeling | Perte de la sensibilité et sensation de picotements | فقدان الإحساس وإحساس بالوخز |
| [ ] | 361 | 4214 | Brain tumor | An abnormal growth of cells in the brain | Croissance anormale de cellules dans le cerveau | نمو غير طبيعي للخلايا داخل الدماغ |
| [ ] | 362 | 4215 | Spinal cord injury | Damage to the spinal cord affecting motor or sensory function | Lésion de la moelle épinière affectant la fonction motrice ou sensitive | تلف في النخاع الشوكي يؤثر على القدرة الحركية أو الحسية |
| [ ] | 363 | 4216 | Autism spectrum disorder | A developmental condition affecting social interaction and behavior | Trouble du développement affectant l'interaction sociale et le comportement | اضطراب في النمو يؤثر على التفاعل الاجتماعي والسلوك |
| [ ] | 364 | 4217 | ADHD | A neurodevelopmental disorder causing inattention and hyperactivity | Trouble neurodéveloppemental provoquant inattention et hyperactivité | اضطراب في النمو العصبي يسبب عدم الانتباه والنشاط الزائد |
| [ ] | 365 | 4218 | Paralysis | Loss of muscle function in part of the body | Perte de la fonction musculaire dans une partie du corps | فقدان القدرة العضلية في جزء من الجسم |

## Cardiology & Blood Vessels (15)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 366 | 4219 | Angina | Chest pain caused by reduced blood flow to the heart | Douleur thoracique causée par une réduction du débit sanguin vers le cœur | ألم في الصدر بسبب نقص وصول الدم إلى القلب |
| [ ] | 367 | 4220 | Atherosclerosis | Hardening and narrowing of arteries due to plaque deposits | Durcissement et rétrécissement des artères à cause de dépôts de plaques | تصلب وضيق في الشرايين بسبب ترسب اللويحات |
| [ ] | 368 | 4221 | Blood clot (Thrombus) | A gel-like mass of blood that forms to stop bleeding | Masse gélatineuse de sang formée pour arrêter un saignement | كتلة هلامية من الدم تتكون لوقف النزيف |
| [ ] | 369 | 4222 | DVT (Deep vein thrombosis) | A blood clot in a deep vein, usually in the leg | Caillot sanguin dans une veine profonde, généralement de la jambe | جلطة دموية في وريد عميق عادة في الساق |
| [ ] | 370 | 4223 | Pulmonary embolism | A blood clot that blocks an artery in the lung | Caillot sanguin obstruant une artère pulmonaire | جلطة دموية تسد شريانًا في الرئة |
| [ ] | 371 | 4224 | Heart failure | A condition where the heart cannot pump enough blood | Affection où le cœur ne pompe pas assez de sang | حالة لا يضخ فيها القلب ما يكفي من الدم |
| [ ] | 372 | 4225 | Cardiac arrest | Sudden stopping of the heart's pumping function | Arrêt brutal de la fonction de pompage du cœur | توقف مفاجئ في وظيفة ضخ القلب |
| [ ] | 373 | 4226 | Pacemaker | A device implanted to regulate abnormal heart rhythms | Dispositif implanté pour réguler un rythme cardiaque anormal | جهاز يُزرع لتنظيم معدل القلب غير الطبيعي |
| [ ] | 374 | 4227 | Aneurysm | A bulge or ballooning in the wall of a blood vessel | Saillie ou dilatation de la paroi d'un vaisseau sanguin | انتفاخ أو تضخم في جدار وعاء دموي |
| [ ] | 375 | 4228 | Varicose veins | Enlarged, swollen veins visible under the skin | Veines dilatées et gonflées visibles sous la peau | وريدات متوسعة ومنتفخة تظهر تحت الجلد |
| [ ] | 376 | 4229 | Cholesterol (HDL/LDL) | Fatty substances in the blood; HDL protects, LDL can clog arteries | Substances grasses du sang ; le HDL protège, le LDL peut obstruer les artères | مواد دهنية في الدم؛ الكوليسترول النافع وقائي والكوليسترول الضار قد يسد الشرايين |
| [ ] | 377 | 4230 | Stent | A small tube inserted into a blocked vessel to keep it open | Petit tube inséré dans un vaisseau bloqué pour le maintenir ouvert | أنبوب صغير يوضع في وعاء مسدود لإبقائه مفتوحًا |
| [ ] | 378 | 4231 | Bypass surgery | A surgery creating a new route for blood to flow around a blockage | Intervention chirurgicale créant un nouveau trajet pour contourner une obstruction | عملية جراحية تُنشئ مسارًا جديدًا لتجاوز الانسداد |
| [ ] | 379 | 4232 | Defibrillator (AED) | A device delivering an electric shock to restore normal heart rhythm | Dispositif délivrant un choc électrique pour rétablir le rythme cardiaque normal | جهاز يوصل صدمة كهربائية لاستعادة نظم القلب الطبيعي |
| [ ] | 380 | 4233 | Aorta | The main artery carrying blood from the heart to the body | Artère principale transportant le sang du cœur vers le corps | الشريان الرئيسي الذي ينقل الدم من القلب إلى الجسم |

## Gastroenterology & Digestion (15)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 381 | 4234 | Heartburn (Acid reflux) | A burning sensation caused by stomach acid rising into the esophagus | Sensation de brûlure causée par la remontée d'acide gastrique dans l'œsophage | حرقان بسبب ارتجاع حمض المعدة إلى المريء |
| [ ] | 382 | 4235 | GERD | Chronic acid reflux damaging the esophagus | Reflux acide chronique endommageant l'œsophage | ارتجاع حمضي مزمن يضر بالمريء |
| [ ] | 383 | 4236 | Bloating | A feeling of fullness and tightness in the abdomen from gas | Sensation de réplétion et de tension dans l'abdomen due aux gaz | شعور بالامتلاء والانتفاخ في البطن بسبب الغازات |
| [ ] | 384 | 4237 | Flatulence (Gas) | Excess gas in the digestive tract | Excès de gaz dans le tractus digestif | غازات زائدة في الجهاز الهضمي |
| [ ] | 385 | 4238 | Hemorrhoids (Piles) | Swollen veins in the rectum or anus | Veines gonflées du rectum ou de l'anus | بواسير ناتجة عن تضخم الأوردة في المستقيم أو الشرج |
| [ ] | 386 | 4239 | Irritable bowel syndrome (IBS) | A chronic gut condition causing abdominal discomfort | Affection intestinale chronique provoquant une gêne abdominale | حالة معوية مزمنة تسبب انزعاجًا في البطن |
| [ ] | 387 | 4240 | Crohn's disease | An inflammatory bowel disease affecting any part of the gut | Maladie inflammatoire intestinale touchant toute partie du tube digestif | مرض التهابي معوي يصيب أي جزء من الجهاز الهضمي |
| [ ] | 388 | 4241 | Colitis | Inflammation of the colon lining | Inflammation de la muqueuse du côlon | التهاب في بطانة القولون |
| [ ] | 389 | 4242 | Gallstone | A hard deposit forming in the gallbladder | Dépôt dur se formant dans la vésicule biliaire | ترسب صلب يتكون في المرارة |
| [ ] | 390 | 4243 | Celiac disease | An autoimmune disease triggered by gluten consumption | Maladie auto-immune déclenchée par la consommation de gluten | مرض مناعي ذاتي يظهر عند تناول الغلوتين |
| [ ] | 391 | 4244 | Indigestion | Discomfort in the stomach after eating | Gêne à l'estomac après le repas | عسر هضم في المعدة بعد الأكل |
| [ ] | 392 | 4245 | Vomiting blood | The presence of blood in vomit; a serious symptom | Présence de sang dans les vomissures ; symptôme grave | وجود دم في القيء، وهي علامة خطيرة |
| [ ] | 393 | 4246 | Rectal bleeding | Blood passing from the rectum or anus | Sang s'écoulant du rectum ou de l'anus | نزيف يخرج من المستقيم أو الشرج |
| [ ] | 394 | 4247 | Colonoscopy | An examination of the colon using a flexible camera | Examen du côlon à l'aide d'une caméra flexible | فحص للقولون باستخدام كاميرا مرنة |
| [ ] | 395 | 4248 | Liver cirrhosis | Severe scarring of the liver | Cicatrisation sévère du foie | تليف شديد في الكبد |

## Endocrine & Metabolic Conditions (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 396 | 4249 | Hypoglycemia | Abnormally low blood sugar levels | Taux de sucre sanguin anormalement bas | انخفاض غير طبيعي في مستوى السكر بالدم |
| [ ] | 397 | 4250 | Hyperglycemia | Abnormally high blood sugar levels | Taux de sucre sanguin anormalement élevé | ارتفاع غير طبيعي في مستوى السكر بالدم |
| [ ] | 398 | 4251 | Type 1 diabetes | An autoimmune form of diabetes with no insulin production | Forme auto-immune du diabète sans production d'insuline | شكل مناعي ذاتي من السكري دون إنتاج الأنسولين |
| [ ] | 399 | 4252 | Type 2 diabetes | A metabolic condition causing insulin resistance | Affection métabolique provoquant une résistance à l'insuline | حالة استقلابية تسبب مقاومة الإنسولين |
| [ ] | 400 | 4253 | Hypothyroidism | Underactive thyroid gland producing insufficient hormones | Glande thyroïde hypoactive produisant des hormones insuffisantes | قصور الغدة الدرقية ينتج هرمونات غير كافية |
| [ ] | 401 | 4254 | Hyperthyroidism | Overactive thyroid gland producing excess hormones | Glande thyroïde hyperactive produisant trop d'hormones | فرط نشاط الغدة الدرقية ينتج هرمونات زائدة |
| [ ] | 402 | 4255 | Gout | A form of arthritis caused by excess uric acid crystals in joints | Forme d'arthrite causée par un excès de cristaux d'acide urique dans les articulations | نوع من التهاب المفاصل بسبب زيادة بلورات حمض اليوريك |
| [ ] | 403 | 4256 | Metabolic syndrome | A cluster of conditions including obesity, high blood pressure and high blood sugar | Ensemble d'affections incluant obésité, hypertension et hyperglycémie | مجموعة حالات تشمل السمنة وارتفاع ضغط الدم وارتفاع السكر |
| [ ] | 404 | 4257 | Adrenal fatigue | A claimed condition where adrenal glands are overtaxed by stress | Affection alléguée où les glandes surrénales sont surmenées par le stress | حالة مزعومة تتعرض فيها الغدد الكظرية للإرهاق بسبب التوتر |
| [ ] | 405 | 4258 | Polycystic ovary syndrome (PCOS) | A hormonal disorder common among women of reproductive age | Trouble hormonal fréquent chez les femmes en âge de procréer | اضطراب هرموني شائع عند النساء في سن الإنجاب |

## Respiratory Conditions (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 406 | 4259 | COPD | A lung disease causing obstructed airflow; includes emphysema and chronic bronchitis | Maladie pulmonaire obstruant le passage de l'air, comprenant emphysème et bronchite chronique | مرض رئوي يعيق تدفق الهواء ويضم الانتفاخ والتهاب القصبات المزمن |
| [ ] | 407 | 4260 | Emphysema | A lung condition causing shortness of breath due to damaged alveoli | Affection pulmonaire causant un essoufflement du fait d'alvéoles endommagées | مرض رئوي يسبب ضيق النفس نتيجة تضرر الحويصلات الرئوية |
| [ ] | 408 | 4261 | Pulmonary fibrosis | Scarring of lung tissue causing breathing difficulties | Cicatrisation du tissu pulmonaire causant des difficultés respiratoires | تليف نسيج الرئة يسبب صعوبة في التنفس |
| [ ] | 409 | 4262 | Sleep apnea | A disorder where breathing repeatedly stops during sleep | Trouble dans lequel la respiration s'arrête à répétition pendant le sommeil | اضطراب تتوقف فيه التنفس بصورة متكررة أثناء النوم |
| [ ] | 410 | 4263 | Pleurisy | Inflammation of the pleura surrounding the lungs | Inflammation de la plèvre qui entoure les poumons | التهاب الغشاء الذي يحيط بالرئتين |
| [ ] | 411 | 4264 | Hyperventilation | Abnormally rapid or deep breathing | Respiration anormalement rapide ou profonde | تنفس سريع أو عميق بشكل غير طبيعي |
| [ ] | 412 | 4265 | Wheezing | A high-pitched whistling sound when breathing | Sifflement aigu lors de la respiration | صوت صفير حاد أثناء التنفس |
| [ ] | 413 | 4266 | Coughing up blood | Expelling blood from the lungs through coughing | Expulsion de sang provenant des poumons par la toux | طرد دم من الرئتين عبر السعال |
| [ ] | 414 | 4267 | Oxygen therapy | Treatment using supplemental oxygen to improve breathing | Traitement utilisant un apport d'oxygène pour améliorer la respiration | علاج باستخدام الأكسجين المكمّل لتحسين التنفس |
| [ ] | 415 | 4268 | Ventilator | A machine that mechanically assists or replaces breathing | Machine assistant mécaniquement la respiration ou la remplaçant | جهاز يساعد على التنفس آليًا أو يحل محل التنفس |

## Urology & Kidney Health (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 416 | 4269 | Urination | The process of releasing urine from the body | Processus d'expulsion de l'urine hors du corps | عملية إخراج البول من الجسم |
| [ ] | 417 | 4270 | Frequency (urination) | Needing to urinate more often than normal | Besoin d'uriner plus souvent que la normale | الحاجة إلى التبول أكثر من المعتاد |
| [ ] | 418 | 4271 | Incontinence | Inability to control urination or defecation | Impossibilité de contrôler la miction ou la défécation | عجز عن التحكم في التبول أو الإخراج |
| [ ] | 419 | 4272 | Hematuria | The presence of blood in urine | Présence de sang dans les urines | وجود دم في البول |
| [ ] | 420 | 4273 | Prostate enlargement | Non-cancerous increase in prostate gland size | Augmentation non cancéreuse de la taille de la prostate | تضخم غير سرطاني في حجم غدة البروستاتا |
| [ ] | 421 | 4274 | Kidney failure | Loss of kidney function to filter waste from the blood | Perte de la fonction rénale de filtration des déchets du sang | فقدان وظيفة الكلى في ترشيح الفضلات من الدم |
| [ ] | 422 | 4275 | Chronic kidney disease (CKD) | Gradual loss of kidney function over time | Perte progressive de la fonction rénale avec le temps | فقدان تدريجي لوظيفة الكلى مع الوقت |
| [ ] | 423 | 4276 | Dialysis | A procedure filtering waste from blood when kidneys fail | Procédure filtrant les déchets du sang en cas de défaillance rénale | إجراء يرشّح الفضلات من الدم عند فشل الكلى |
| [ ] | 424 | 4277 | Kidney transplant | Surgical replacement of a failed kidney with a donor kidney | Remplacement chirurgical d'un rein défaillant par un rein de donneur | استبدال جراحي لكلى فاشلة بكلية من متبرع |
| [ ] | 425 | 4278 | Bedwetting (Enuresis) | Involuntary urination during sleep | Miction involontaire pendant le sommeil | تبول لا إرادي أثناء النوم |

## Ophthalmology (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 426 | 4279 | Eye infection | An infection of the eye caused by bacteria or viruses | Infection de l'œil causée par des bactéries ou des virus | عدوى تصيب العين بسبب بكتيريا أو فيروسات |
| [ ] | 427 | 4280 | Conjunctivitis (Pink eye) | Inflammation of the conjunctiva (white of the eye) | Inflammation de la conjonction, la membrane blanche de l'œil | التهاب الغشاء المبطّن لجفن العين |
| [ ] | 428 | 4281 | Dry eyes | A condition where the eyes do not produce enough tears | Affection où les yeux ne produisent pas assez de larmes | حالة لا تنتج فيها العينان دموعًا كافية |
| [ ] | 429 | 4282 | Double vision | Seeing two images of a single object | Voir deux images d'un même objet | رؤية صورتين لشيء واحد |
| [ ] | 430 | 4283 | Laser eye surgery | A surgical procedure using laser to correct vision problems | Intervention chirurgicale au laser corrigeant les problèmes de vision | عملية جراحية بالليزر تصحح مشاكل الإبصار |
| [ ] | 431 | 4284 | Astigmatism | An imperfection in the eye's curve causing blurred vision | Irrégularité de la courbure de l'œil causant une vision floue | اضطراب في انحناءة العين يسبب ضبابية الرؤية |
| [ ] | 432 | 4285 | Retinal detachment | When the retina pulls away from the back of the eye | Décollement de la rétine par rapport au fond de l'œil | انفصال الشبكية عن خلفية العين |
| [ ] | 433 | 4286 | Color blindness | Inability to see certain colors normally | Incapacité à distinguer normalement certaines couleurs | عجز عن تمييز بعض الألوان بشكل طبيعي |
| [ ] | 434 | 4287 | Floaters | Small spots or shapes drifting across the visual field | Petites taches ou formes dérivant dans le champ visuel | بقع أو أشكال صغيرة تتحرك في مجال الرؤية |
| [ ] | 435 | 4288 | Pupil | The black circular opening in the center of the iris | Ouverture circulaire noire au centre de l'iris | الفتحة الدائرية السوداء في مركز قزحية العين |

## Pediatrics (15)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 436 | 4289 | Newborn | A baby in the first 28 days of life | Bébé au cours des vingt-huit premiers jours de vie | طفل في أول ثمانية وعشرين يومًا من الحياة |
| [ ] | 437 | 4290 | Infant | A baby from birth to 12 months | Bébé de la naissance à douze mois | طفل من الولادة حتى اثني عشر شهرًا |
| [ ] | 438 | 4291 | Toddler | A child aged 1 to 3 years | Enfant âgé de un à trois ans | طفل يتراوح عمره بين سنة وثلاث سنوات |
| [ ] | 439 | 4292 | Growth chart | A chart tracking a child's height and weight over time | Courbe de suivi de la taille et du poids de l'enfant dans le temps | رسم بياني يتابع طول ووزن الطفل عبر الزمن |
| [ ] | 440 | 4293 | Vaccination schedule | A recommended timeline for childhood immunizations | Calendrier recommandé pour les vaccinations de l'enfant | جدول موصى به لتطعيمات الأطفال |
| [ ] | 441 | 4294 | Teething | The process of a baby's first teeth emerging | Processus d'apparition des premières dents du bébé | عملية ظهور أسنان الطفل الأولى |
| [ ] | 442 | 4295 | Croup | A childhood viral illness causing a barking cough | Maladie virale de l'enfant causant une toux aboyante | مرض فيروسي عند الأطفال يسبب سعالًا نباحيًا |
| [ ] | 443 | 4296 | Jaundice in newborns | Yellow skin in newborns due to high bilirubin levels | Jaunissement de la peau chez le nouveau-né dû à un taux élevé de bilirubine | اصفرار جلد المولود بسبب ارتفاع البيليروبين |
| [ ] | 444 | 4297 | Febrile seizure | A seizure triggered by fever in young children | Convulsion déclenchée par la fièvre chez le jeune enfant | نوبة تشنجية يثيرها الحمى عند الأطفال الصغار |
| [ ] | 445 | 4298 | Head lice | Small parasitic insects living on the scalp | Petits insectes parasites vivant dans le cuir chevelu | حشرات طفيلية صغيرة تعيش في فروة الرأس |
| [ ] | 446 | 4299 | Diaper rash | Skin irritation caused by prolonged contact with a wet diaper | Irritation cutanée causée par un contact prolongé avec une couche humide | تهيج في الجلد بسبب ملامسة حفاض مبلل لفترة طويلة |
| [ ] | 447 | 4300 | SIDS (Sudden infant death syndrome) | Unexplained death of a healthy infant during sleep | Mort inexpliquée d'un nourrisson en bonne santé pendant son sommeil | وفاة غير مبررة لطفل سليم أثناء نومه |
| [ ] | 448 | 4301 | Ear tube | A small tube inserted into the eardrum to drain fluid | Petit tube inséré dans le tympan pour drainer le liquide | أنبوب صغير يوضع في طبلة الأذن لتصريف السائل |
| [ ] | 449 | 4302 | Tonsillitis | Inflammation of the tonsils caused by infection | Inflammation des amygdales causée par une infection | التهاب اللوزتين بسبب عدوى |
| [ ] | 450 | 4303 | Childhood obesity | Excessive body fat in children affecting health | Excès de graisse corporelle chez l'enfant affectant sa santé | سمنة مفرطة عند الأطفال تؤثر في صحتهم |

## Geriatrics (9)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 451 | 4304 | Elderly care | Healthcare and support specifically for older adults | Soins et soutien destinés spécifiquement aux personnes âgées | رعاية ودعم موجّهان لكبار السن |
| [ ] | 452 | 4305 | Fall prevention | Measures to prevent falls in older people | Mesures visant à prévenir les chutes chez les personnes âgées | إجراءات لمنع السقوط عند كبار السن |
| [ ] | 453 | 4306 | Hip fracture | A break in the upper part of the femur common in older adults | Fracture de la partie supérieure du fémur, fréquente chez les personnes âgées | كسر في الجزء العلوي من عظم الفخذ شائع عند كبار السن |
| [ ] | 455 | 4307 | Memory loss | Difficulty remembering information | Difficulté à se souvenir d'une information | صعوبة تذكر المعلومات |
| [ ] | 456 | 4308 | Polypharmacy | The use of multiple medications simultaneously | Utilisation simultanée de plusieurs médicaments | استخدام عدة أدوية في الوقت نفسه |
| [ ] | 457 | 4309 | Bedsore (Pressure ulcer) | A skin wound caused by prolonged pressure on the skin | Plaie cutanée causée par une pression prolongée sur la peau | جرح في الجلد نتيجة ضغط مستمر عليه |
| [ ] | 458 | 4310 | Delirium | A sudden state of serious confusion and rapid changes in brain function | État soudain de confusion grave et de variations rapides des fonctions cérébrales | حالة مفاجئة من الالتباس الشديد وتقلب سريع في وظائف الدماغ |
| [ ] | 459 | 4311 | Nursing home | A facility providing residential care for elderly or disabled people | Établissement fournissant hébergement et soins aux personnes âgées ou handicapées | مؤسسة تقدم الرعاية والإقامة لكبار السن أو ذوي الإعاقة |
| [ ] | 460 | 4312 | Life expectancy | The average number of years a person is expected to live | Nombre moyen d'années qu'une personne est censée vivre | متوسط عدد السنوات التي يُتوقع أن يعيشها الإنسان |

## Oncology Basics (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 461 | 4313 | Tumor marker | A substance in blood indicating the presence of cancer | Substance présente dans le sang indiquant la présence d'un cancer | مادة في الدم تدل على وجود ورم |
| [ ] | 462 | 4314 | Lymph node | Small glands that filter lymph fluid and fight infection | Petites glandes filtrant la lymphe et combatgeant l'infection | عقائد لمفية صغيرة ترشّح اللمف وتقاوم العدوى |
| [ ] | 463 | 4315 | Swollen lymph nodes | Enlarged lymph nodes due to infection or cancer | Ganglions lymphatiques augmentés de volume en raison d'une infection ou d'un cancer | تضخم العقد الليمفية بسبب عدوى أو سرطان |
| [ ] | 464 | 4316 | Breast cancer | Malignant tumor originating in breast tissue | Tumeur maligne naissant dans le tissu mammaire | ورم خبيث ينشأ في نسيج الثدي |
| [ ] | 465 | 4317 | Lung cancer | Malignant tumor of the lung, mainly caused by smoking | Tumeur maligne du poumon, principalement due au tabagisme | ورم خبيث في الرئة سببه الأساسي التدخين |
| [ ] | 466 | 4318 | Colorectal cancer | Cancer of the colon or rectum | Cancer du côlon ou du rectum | سرطان القولون أو المستقيم |
| [ ] | 467 | 4319 | Leukemia | Cancer starting in blood-forming tissue causing abnormal blood cells | Cancer débutant dans le tissu hématopoïétique et produisant des cellules sanguines anormales | سرطان ينشأ في نسيج الدم وينتج خلايا دموية غير طبيعية |
| [ ] | 468 | 4320 | Immunotherapy | A cancer treatment that uses the body's immune system | Traitement du cancer qui utilise le système immunitaire du corps | علاج السرطان باستخدام الجهاز المناعي للجسم |
| [ ] | 469 | 4321 | Targeted therapy | A treatment targeting specific genes or proteins of cancer cells | Traitement ciblant des gènes ou des protéines spécifiques des cellules cancéreuses | علاج يستهدف جينات أو بروتينات محددة في الخلايا السرطانية |
| [ ] | 470 | 4322 | Palliative care | Care focused on relieving symptoms and improving quality of life | Soins visant à soulager les symptômes et à améliorer la qualité de vie | رعاية تهدف إلى تخفيف الأعراض وتحسين جودة الحياة |

## Surgery & Post-Operative Care (12)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 471 | 4323 | General anesthesia | Anesthesia causing complete unconsciousness | Anesthésie provoquant une perte de connaissance complète | تخدير يسبب فقدانًا كاملًا للوعي |
| [ ] | 472 | 4324 | Local anesthesia | Numbing of a small area without affecting consciousness | Insensibilisation d'une petite zone sans affecter la conscience | تخدير موضعي لمنطقة صغيرة دون التأثير على الوعي |
| [ ] | 473 | 4325 | Recovery room | A room where patients recover immediately after surgery | Salle où les patients récupèrent juste après une intervention | غرفة يتعافى فيها المرضى بعد العملية مباشرة |
| [ ] | 474 | 4326 | ICU (Intensive Care Unit) | A hospital unit for patients needing intensive monitoring | Service hospitalier pour les patients nécessitant une surveillance intensive | وحدة رعاية مركزة في المستشفى لمرضى يحتاجون مراقبة دقيقة |
| [ ] | 475 | 4327 | Drain (surgical) | A tube placed to drain fluid from a surgical site | Tube placé pour drainer le liquide d'un site chirurgical | أنبوب يوضع لتصريف السوائل من موقع جراحي |
| [ ] | 476 | 4328 | IV (Intravenous) | A method of delivering fluids or drugs directly into a vein | Méthode d'administration de liquides ou de médicaments directement dans une veine | طريقة إعطاء السوائل أو الأدوية مباشرة عبر الوريد |
| [ ] | 477 | 4329 | Blood transfusion | The transfer of blood from a donor to a patient | Transfert de sang d'un donneur à un patient | نقل الدم من متبرع إلى مريض |
| [ ] | 478 | 4330 | Wound infection | Bacterial contamination of a surgical or traumatic wound | Contamination bactérienne d'une plaie chirurgicale ou traumatique | تلوث بكتيري لجرح جراحي أو ناتج عن إصابة |
| [ ] | 479 | 4331 | Scar tissue | Fibrous tissue replacing normal tissue after healing | Tissu fibreux remplaçant le tissu normal après la guérison | نسيج ليفي يحل محل النسيج الطبيعي بعد التئام الجرح |
| [ ] | 480 | 4332 | Amputation | Surgical removal of a limb or body part | Ablation chirurgicale d'un membre ou d'une partie du corps | بتر جراحي لطرف أو جزء من الجسم |
| [ ] | 481 | 4333 | Organ transplant | Surgical transfer of an organ from a donor to a recipient | Transfert chirurgical d'un organe d'un donneur à un receveur | نقل جراحي لعضو من متبرع إلى متلقٍّ |
| [ ] | 482 | 4334 | Post-operative pain | Pain experienced after a surgical procedure | Douleur ressentie après une intervention chirurgicale | ألم بعد العملية الجراحية |

## Lab Tests & Results (15)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 483 | 4335 | Complete blood count (CBC) | A blood test measuring different components of blood | Analyse sanguine mesurant les différents constituants du sang | فحص دم يقيس مكونات الدم المختلفة |
| [ ] | 484 | 4336 | White blood cell count | A measure of immune cells in the blood | Mesure des cellules immunitaires présentes dans le sang | قياس كريات الدم البيضاء في الدم |
| [ ] | 485 | 4337 | Hemoglobin level | A blood test measuring the oxygen-carrying protein level | Analyse sanguine mesurant le niveau de la protéine transporteuse d'oxygène | فحص دم يقيس مستوى الهيموغلوبين الناقل للأكسجين |
| [ ] | 486 | 4338 | Platelet count | A measure of clotting cells in the blood | Mesure des cellules de la coagulation dans le sang | قياس الصفائح الدموية المسؤولة عن التجلط |
| [ ] | 487 | 4339 | Liver function test (LFT) | Blood tests measuring liver health and function | Analyses sanguines mesurant la santé et le fonctionnement du foie | فحوصات دم تقيس صحة الكبد ووظائفه |
| [ ] | 488 | 4340 | Kidney function test | Blood tests measuring kidney health | Analyses sanguines mesurant la santé des reins | فحوصات دم تقيس صحة الكلى |
| [ ] | 489 | 4341 | Thyroid function test | Blood tests measuring thyroid hormone levels | Analyses sanguines mesurant les taux d'hormones thyroïdiennes | فحوصات دم تقيس مستوى هرمونات الغدة الدرقية |
| [ ] | 490 | 4342 | HbA1c (Glycated hemoglobin) | A blood test reflecting average blood glucose over 3 months | Analyse sanguine reflétant la glycémie moyenne sur trois mois | فحص دم يعكس متوسط مستوى السكر خلال ثلاثة أشهر |
| [ ] | 491 | 4343 | Urine culture | A test growing bacteria from a urine sample | Examen cultivant les bactéries à partir d'un échantillon d'urine | فحص لزراعة البكتيريا من عينة بول |
| [ ] | 492 | 4344 | Blood culture | A test detecting bacterial infection in the blood | Examen détectant une infection bactérienne dans le sang | فحص يكشف وجود عدوى بكتيرية في الدم |
| [ ] | 493 | 4345 | Biopsy result | The laboratory analysis of removed tissue | Analyse en laboratoire du tissu prélevé | نتيجة فحص العينة النسيجية في المختبر |
| [ ] | 494 | 4346 | Abnormal result | A test result outside the normal reference range | Résultat d'examen en dehors des valeurs de référence normales | نتيجة فحص خارج النطاق المرجعي الطبيعي |
| [ ] | 495 | 4347 | Normal range | The expected values for a healthy individual | Valeurs attendues chez une personne en bonne santé | القيم المتوقعة عند الشخص السليم |
| [ ] | 496 | 4348 | False positive | A test result incorrectly indicating disease | Résultat d'examen indiquant à tort la présence d'une maladie | نتيجة فحص خاطئة توحي بوجود المرض |
| [ ] | 497 | 4349 | False negative | A test result incorrectly indicating no disease | Résultat d'examen indiquant à tort l'absence de maladie | نتيجة فحص خاطئة توحي بغياب المرض |

## Insurance & Administrative (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 498 | 4350 | Health insurance | A policy covering the cost of medical care | Assurance couvrant les frais de soins médicaux | تأمين يغطي تكاليف العلاج الطبي |
| [ ] | 499 | 4351 | Co-payment (Co-pay) | A fixed amount paid by the patient for a medical service | Montant fixe payé par le patient pour un acte médical | مبلغ ثابت يدفعه المريض عن الخدمة الطبية |
| [ ] | 500 | 4352 | Deductible | The amount paid out-of-pocket before insurance covers costs | Montant payé de sa poche avant que l'assurance ne prenne en charge les frais | مبلغ يدفعه المريض من ماله قبل أن يغطي التأمين التكاليف |
| [ ] | 501 | 4353 | Pre-authorization | Approval from insurance before receiving certain services | Accord de l'assurance avant de recevoir certains soins | موافقة مسبقة من التأمين قبل الحصول على بعض الخدمات |
| [ ] | 502 | 4354 | Medical certificate | An official document from a doctor confirming health status | Document officiel du médecin confirmant l'état de santé | شهادة طبية رسمية من الطبيب تؤكد الحالة الصحية |
| [ ] | 503 | 4355 | Sick leave | Authorized time off from work due to illness | Congé autorisé en raison d'une maladie | إجازة مرضية مصرح بها |
| [ ] | 504 | 4356 | Health card | A card showing health insurance details | Carte indiquant les informations de l'assurance maladie | بطاقة تحتوي على بيانات التأمين الصحي |
| [ ] | 505 | 4357 | Patient consent form | A signed document showing agreement to a medical procedure | Document signé attestant l'accord à un acte médical | استمارة موافقة موقعة تثبت القبول بإجراء طبي |
| [ ] | 506 | 4358 | Medical bill | A statement of charges for medical services received | Relevé des frais des actes médicaux reçus | فاتورة توضح تكاليف الخدمات الطبية المقدَّمة |
| [ ] | 507 | 4359 | Out-of-pocket expense | Medical costs paid directly by the patient | Frais médicaux payés directement par le patient | مصاريف طبية يدفعها المريض مباشرة |

## Advanced Daily Vocabulary (22)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 508 | 4360 | Autopsy | A post-mortem examination of a body | Examen d'un corps après le décès | فحص الجسد بعد الوفاة |
| [ ] | 509 | 4361 | Organ donation | Giving one's organs for transplant after death or while alive | Don de ses organes à la greffe après le décès ou de son vivant | التبرع بالأعضاء للزرع بعد الوفاة أو أثناء الحياة |
| [ ] | 510 | 4362 | Living will | A legal document stating healthcare wishes if incapacitated | Document légal exprimant les soins souhaités en cas d'incapacité | وثيقة قانونية تحدد الرعاية المرغوبة في حالة العجز |
| [ ] | 511 | 4363 | Convalescence | The gradual recovery of health after illness or surgery | Rétablissement progressif de la santé après une maladie ou une opération | تعافٍ تدريجي بعد المرض أو العملية |
| [ ] | 513 | 4364 | Remission | A period when disease symptoms are absent or reduced | Période durant laquelle les symptômes d'une maladie sont absents ou réduits | فترة تختفي فيها أعراض المرض أو تقل |
| [ ] | 514 | 4365 | Quarantine | Isolation to prevent the spread of infectious disease | Isolement destiné à empêcher la propagation d'une maladie contagieuse | حجر صحي لمنع انتشار المرض المعدي |
| [ ] | 515 | 4366 | Isolation | Separation of an infected person from healthy people | Séparation d'une personne infectée des personnes saines | عزل المصاب عن الأصحاء |
| [ ] | 516 | 4367 | Contact tracing | Identifying people who may have been exposed to an infectious person | Identification des personnes ayant été exposées à une personne infectée | تحديد الأشخاص الذين تعرضوا لمصاب |
| [ ] | 517 | 4368 | Herd immunity | Indirect protection when a large portion of a population is immune | Protection indirecte lorsque la majeure partie d'une population est immunisée | مناعة جماعية غير مباشرة عندما يكون معظم السكان محصّنين |
| [ ] | 518 | 4369 | Informed consent | Voluntary agreement to treatment after full information is provided | Accord volontaire au traitement après fourniture d'une information complète | موافقة طوعية على العلاج بعد تقديم معلومات كاملة |
| [ ] | 519 | 4370 | Medical ethics | The principles governing the conduct of medical professionals | Principes régissant la conduite des professionnels de santé | المبادئ التي تحكم سلوك العاملين في الصحة |
| [ ] | 520 | 4371 | Malpractice | Improper or negligent medical treatment causing harm | Traitement médical fautif ou négligent causant un préjudice | خطأ طبي أو إهمال في العلاج يسبب ضررًا |
| [ ] | 521 | 4372 | Confidentiality | The principle of keeping patient information private | Principe consistant à garder les informations du patient privées | مبدأ الحفاظ على خصوصية معلومات المريض |
| [ ] | 522 | 4373 | Epidemic vs Pandemic | Epidemic is regional; pandemic is worldwide spread of disease | Une épidémie est régionale, tandis qu'une pandémie est une propagation mondiale | الوباء ينحصر في منطقة، بينما الجائحة تنتشر عالميًا |
| [ ] | 523 | 4374 | Mortality rate | The proportion of deaths relative to a total population | Proportion de décès par rapport à une population totale | نسبة الوفيات إلى إجمالي عدد السكان |
| [ ] | 524 | 4375 | Morbidity | The state of being ill or having a disease | État de maladie ou fait d'être malade | حالة المرض أو التعرض له |
| [ ] | 525 | 4376 | Incidence | The rate of occurrence of a new disease in a population | Taux d'apparition d'une nouvelle maladie dans une population | معدل ظهور مرض جديد في مجموعة سكانية |
| [ ] | 526 | 4377 | Prevalence | The total proportion of a population with a disease at a given time | Proportion totale d'une population atteinte d'une maladie à un moment donné | نسبة انتشار المرض بين السكان في وقت معين |
| [ ] | 527 | 4378 | Antibiotic resistance | The ability of bacteria to resist the effects of antibiotics | Capacité des bactéries à résister aux effets des antibiotiques | قدرة البكتيريا على مقاومة تأثير المضادات الحيوية |
| [ ] | 528 | 4379 | Outbreak | A sudden increase in cases of a disease in a specific area | Augmentation soudaine des cas d'une maladie dans une zone donnée | تزايد مفاجئ في حالات مرض في منطقة محددة |
| [ ] | 529 | 4380 | Pathogen | A microorganism causing disease | Micro-organisme responsable d'une maladie | كائن دقيق مسبب لمرض |
| [ ] | 530 | 4381 | Zoonosis | A disease transmitted from animals to humans | Maladie transmise de l'animal à l'homme | مرض ينتقل من الحيوان إلى الإنسان |

## Obstetrics & Gynecology (15)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 531 | 4382 | Ovulation | The release of an egg from the ovary | Libération d'un ovule depuis l'ovaire | خروج بويضة من المبيض |
| [ ] | 532 | 4383 | Trimester | One of the three 3-month periods of pregnancy | L'une des trois périodes de trois mois que compte la grossesse | إحدى الفترات الثلاث التي تمتد ثلاثة أشهر في الحمل |
| [ ] | 533 | 4384 | Morning sickness | Nausea and vomiting experienced during early pregnancy | Nausées et vomissements survenant en début de grossesse | غثيان وقيء في بداية الحمل |
| [ ] | 534 | 4385 | Prenatal care | Medical care during pregnancy to monitor mother and baby | Soins médicaux pendant la grossesse pour suivre la mère et l'enfant | رعاية طبية أثناء الحمل لمتابعة الأم والجنين |
| [ ] | 535 | 4386 | Ultrasound (pregnancy) | Imaging using sound waves to visualize the fetus | Imagerie par ondes sonores pour visualiser le fœtus | تصوير بالموجات فوق الصوتية لرؤية الجنين |
| [ ] | 536 | 4387 | Amniotic fluid | Protective fluid surrounding the fetus in the uterus | Liquide protecteur entourant le fœtus dans l'utérus | سائل واقٍ حول الجنين داخل الرحم |
| [ ] | 537 | 4388 | Placenta | An organ in the uterus nourishing the fetus via the umbilical cord | Organe dans l'utérus nourrissant le fœtus par le cordon ombilical | عضو في الرحم يغذي الجنين عبر الحبل السري |
| [ ] | 538 | 4389 | Umbilical cord | The cord linking the fetus to the placenta | Cordon reliant le fœtus au placenta | الحبل الذي يصل الجنين بالمشيمة |
| [ ] | 539 | 4390 | Labor contractions | Uterine muscle tightening during childbirth | Contractions du muscle utérin pendant l'accouchement | انقباضات عضلة الرحم أثناء الولادة |
| [ ] | 540 | 4391 | Epidural | Anesthesia injected into the spine to relieve labor pain | Anesthésie injectée dans la colonne vertébrale pour soulager la douleur de l'accouchement | تخدير يُحقن في العمود الفقري لتخفيف ألم الولادة |
| [ ] | 541 | 4392 | Stillbirth | The delivery of a baby who has died in the womb | Accouchement d'un enfant décédé dans l'utérus | ولادة طفل متوفى في الرحم |
| [ ] | 542 | 4393 | Postpartum depression | Depression occurring after childbirth | Dépression survenant après l'accouchement | اكتئاب يحدث بعد الولادة |
| [ ] | 543 | 4394 | Cervix | The lower narrow part of the uterus | Partie inférieure et étroite de l'utérus | الجزء السفلي الضيق من الرحم |
| [ ] | 544 | 4395 | Hysterectomy | Surgical removal of the uterus | Ablation chirurgicale de l'utérus | استئصال الرحم جراحيًا |
| [ ] | 545 | 4396 | Ectopic pregnancy | A pregnancy implanted outside the uterus, usually in a fallopian tube | Grossesse implantée hors de l'utérus, le plus souvent dans une trompe | حمل ينغرس خارج الرحم، غالبًا في أحد الأنابيب |

## Ear Nose & Throat (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 546 | 4397 | Earache | Pain in or around the ear | Douleur dans l'oreille ou autour d'elle | ألم في الأذن أو حولها |
| [ ] | 547 | 4398 | Tinnitus | A ringing or buzzing noise heard in one or both ears | Bourdonnement ou sifflement perçu dans une oreille ou dans les deux | طنين في أذن واحدة أو في الأذنين |
| [ ] | 548 | 4399 | Vertigo (inner ear) | Dizziness caused by inner ear dysfunction | Étourdissement causé par un dysfonctionnement de l'oreille interne | دوار ناتج عن خلل في الأذن الداخلية |
| [ ] | 549 | 4400 | Sinusitis | Inflammation of the sinuses around the nose | Inflammation des sinus autour du nez | التهاب الجيوب الأنفية حول الأنف |
| [ ] | 550 | 4401 | Nosebleed (Epistaxis) | Bleeding from the blood vessels inside the nose | Saignement des vaisseaux sanguins à l'intérieur du nez | نزيف من الأوعية الدموية داخل الأنف |
| [ ] | 551 | 4402 | Laryngitis | Inflammation of the voice box (larynx) | Inflammation du larynx, organe de la voix | التهاب الحنجرة |
| [ ] | 552 | 4403 | Hoarseness | An abnormal change in the voice making it rough or husky | Modification anormale de la voix la rendant rauque | تغير غير طبيعي في الصوت يجعله أجش |
| [ ] | 553 | 4404 | Deviated septum | A condition where the nasal septum is off-center | Affection où la cloison nasale est décentrée | حالة يكون فيها الحاجز الأنفي مائلًا عن المنتصف |
| [ ] | 554 | 4405 | Adenoids | Lymphatic tissue at the back of the nose | Tissu lymphatique situé à l'arrière du nez | نسيج لمفي في الجزء الخلفي من الأنف |
| [ ] | 555 | 4406 | Pharyngitis | Inflammation of the pharynx (back of the throat) | Inflammation du pharynx, c'est-à-dire de l'arrière-gorge | التهاب الحلق |

## Psychiatry & Behavioral Health (13)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 556 | 4407 | Schizophrenia | A severe mental disorder causing distorted thinking and perception | Trouble mental sévère provoquant une pensée et une perception déformées | اضطراب نفسي شديد يسبب تشوه التفكير والإدراك |
| [ ] | 557 | 4408 | Bipolar disorder | A mental condition causing extreme mood episodes of mania and depression | Trouble mental provoquant des épisodes d'humeur extrêmes de manie et de dépression | اضطراب في الصحة النفسية يسبب نوبات مزاجية شديدة بين الهوس والاكتئاب |
| [ ] | 558 | 4409 | Hallucination | Seeing or hearing things that are not there | Voir ou entendre des choses qui n'existent pas | رؤية أو سماع أشياء غير موجودة |
| [ ] | 559 | 4410 | Delusion | A false fixed belief not shared by others | Croyance fixe et fausse que ne partagent pas les autres | اعتقاد خاطئ ثابت لا يشاركه الآخرون |
| [ ] | 560 | 4411 | Obsessive-compulsive disorder (OCD) | A disorder with recurring unwanted thoughts and compulsive behaviors | Trouble caractérisé par des pensées inappropriées récurrentes et des comportements compulsifs | اضطراب يتسم بأفكار متكررة غير مرغوبة وسلوكات قسرية |
| [ ] | 561 | 4412 | Anorexia nervosa | An eating disorder involving extreme food restriction | Trouble alimentaire impliquant une restriction alimentaire extrême | اضطراب في الأكل يتضمن تقييدًا شديدًا في الطعام |
| [ ] | 562 | 4413 | Bulimia | An eating disorder of binge eating and purging | Trouble alimentaire associant des accès de suralimentation et des vomissements | اضطراب في الأكل يشمل نوبات أكل مفرط ثم التطهير |
| [ ] | 563 | 4414 | Psychosis | A mental state where the person loses touch with reality | État mental dans lequel la personne perd contact avec la réalité | حالة نفسية يفقد فيها الشخص تماسكه مع الواقع |
| [ ] | 564 | 4415 | Suicidal ideation | Thoughts of ending one's own life | Pensées de mettre fin à sa propre vie | أفكار انتحارية |
| [ ] | 565 | 4416 | Cognitive behavioral therapy (CBT) | A therapy changing negative thoughts and behaviors | Thérapie modifiant les pensées et les comportements négatifs | علاج يغيّر الأفكار والسلوكيات السلبية |
| [ ] | 566 | 4417 | Mood disorder | A condition causing disturbances in a person's mood | Trouble provoquant des perturbations de l'humeur | اضطراب يسبب خللًا في المزاج |
| [ ] | 567 | 4418 | Withdrawal symptoms | Physical and mental effects of stopping a substance | Effets physiques et mentaux de l'arrêt d'une substance | أعراض جسدية ونفسية عند التوقف عن مادة ما |
| [ ] | 568 | 4419 | Rehabilitation (addiction) | A program helping people recover from addiction | Programme aidant les personnes à se rétablir d'une dépendance | برنامج يساعد الأشخاص على التعافي من الإدمان |

## Hematology (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 569 | 4420 | Sickle cell disease | A hereditary blood disorder where red cells are sickle-shaped | Maladie héréditaire du sang où les globules rouges ont une forme en faucille | مرض دم وراثي تتخذ فيه كريات الدم الحمراء شكل المنجل |
| [ ] | 570 | 4421 | Hemophilia | A genetic disorder where blood does not clot properly | Trouble génétique où le sang ne coagule pas correctement | اضطراب وراثي لا يتخثر فيه الدم بصورة طبيعية |
| [ ] | 571 | 4422 | Platelet | Small blood cells that help form clots to stop bleeding | Petites cellules sanguines qui contribuent à former les caillots et à arrêter le saignement | صفائح دموية صغيرة تساعد على تكوّن الجلطة وإيقاف النزيف |
| [ ] | 572 | 4423 | Clotting factor | A protein in blood essential for clot formation | Protéine sanguine indispensable à la formation des caillots | بروتين في الدم ضروري لتكوّن الجلطة |
| [ ] | 573 | 4424 | Blood type (Blood group) | The classification of blood based on antigens (A, B, AB, O) | Classification du sang selon les antigènes | تصنيف الدم حسب المستضدات |
| [ ] | 574 | 4425 | Rh factor | A protein on red blood cells; positive or negative | Protéine présente sur les globules rouges, positive ou négative | بروتين على كريات الدم الحمراء، موجب أو سالب |
| [ ] | 575 | 4426 | Iron deficiency | Insufficient iron in the body needed for hemoglobin production | Carence en fer nécessaire à la production d'hémoglobine | نقص الحديد اللازم لتكوين الهيموغلوبين |
| [ ] | 576 | 4427 | Bone marrow | Soft tissue inside bones where blood cells are produced | Tissu mou situé à l'intérieur des os où sont produites les cellules sanguines | نسيج ناعم داخل العظام تُنتج فيه خلايا الدم |
| [ ] | 577 | 4428 | Bone marrow transplant | Replacing diseased bone marrow with healthy donor marrow | Remplacement d'une moelle osseuse malade par une moelle saine de donneur | استبدال نخاع عظم مريض بنخاع سليم من متبرع |
| [ ] | 578 | 4429 | Lymphoma | Cancer of the lymphatic system | Cancer du système lymphatique | سرطان الجهاز الليمفي |

## Infectious Diseases (15)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 579 | 4430 | Septicemia (Blood poisoning) | A severe infection spreading through the bloodstream | Infection grave se propageant dans la circulation sanguine | عدوى شديدة تنتشر في مجرى الدم |
| [ ] | 580 | 4431 | Meningococcal disease | A severe bacterial infection causing meningitis or sepsis | Infection bactérienne grave provoquant une méningite ou une septicémie | عدوى بكتيرية شديدة تسبب التهاب السحايا أو تسمم الدم |
| [ ] | 581 | 4432 | Typhoid fever | A bacterial infection causing high fever and intestinal symptoms | Infection bactérique provoquant une fièvre élevée et des symptômes digestifs | عدوى بكتيرية تسبب حمى شديدة وأعراضًا هضمية |
| [ ] | 582 | 4433 | Cholera | A bacterial infection causing severe watery diarrhea | Infection bactérique provoquant une diarrhée aqueuse sévère | عدوى بكتيرية تسبب إسهالًا مائيًا شديدًا |
| [ ] | 583 | 4434 | Dengue fever | A mosquito-borne viral disease causing high fever and joint pain | Maladie virale transmise par le moustique, provoquant fièvre élevée et douleurs articulaires | مرض فيروس ينقله البعوض، يسبب حمى شديدة وآلامًا في المفاصل |
| [ ] | 584 | 4435 | Shingles (Herpes zoster) | A viral infection reactivating the chickenpox virus | Infection virale réactivant le virus de la varicelle | عدوى فيروسية تنشّط من جديد فيروس جدري الماء |
| [ ] | 585 | 4436 | Lyme disease | A bacterial infection spread by tick bites | Infection bactérienne transmise par les piqûres de tiques | عدوى بكتيرية تنتقل عبر لدغات القراد |
| [ ] | 586 | 4437 | Rabies | A fatal viral disease transmitted through animal bites | Maladie virale mortelle transmise par les morsures d'animaux | مرض فيروسي قاتل ينتقل عبر لدغات الحيوانات |
| [ ] | 587 | 4438 | Tetanus | A bacterial infection causing muscle stiffness and spasms | Infection bactérienne provoquant une raideur musculaire et des spasmes | عدوى بكتيرية تسبب تصلبًا وتشنجات عضلية |
| [ ] | 588 | 4439 | Diphtheria | A bacterial infection affecting the throat and airway | Infection bactérienne touchant la gorge et les voies aériennes | عدوى بكتيرية تصيب الحلق والمجاري الهوائية |
| [ ] | 589 | 4440 | Whooping cough (Pertussis) | A highly contagious bacterial infection causing severe coughing | Infection bactérienne très contagieuse provoquant une toux sévère | عدوى بكتيرية شديدة العدوى تسبب سعالًا شديدًا |
| [ ] | 590 | 4441 | Mumps | A viral infection causing painful swelling of glands near the jaw | Infection virale provoquant un gonflement douloureux des glandes près de la mâchoire | عدوى فيروسية تسبب تورمًا مؤلمًا في الغدد قرب الفك |
| [ ] | 591 | 4442 | Rubella (German measles) | A contagious viral disease causing a fine rash | Maladie virale contagieuse provoquant une éruption cutanée fine | مرض فيروس معدي يسبب طفحًا جلديًا ناعمًا |
| [ ] | 592 | 4443 | Scabies | A skin infestation by tiny mites causing intense itching | Infestation cutanée par de minuscules acariens provoquant des démangeaisons intenses | عدوى جلدية بقمل صغير جدًا تسبب حكة شديدة |
| [ ] | 593 | 4444 | Ringworm (Tinea) | A fungal skin infection forming a ring-shaped rash | Infection fongique de la peau formant une éruption en forme d'anneau | عدوى فطرية بالجلد تشكل طفحًا على شكل حلقة |

## Radiology & Imaging (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 594 | 4445 | Radiologist | A doctor specializing in interpreting medical images | Médecin spécialisé dans l'interprétation des images médicales | طبيب متخصص في تفسير الصور الطبية |
| [ ] | 595 | 4446 | Contrast dye | A substance injected to improve visibility in imaging | Substance injectée pour améliorer la visibilité lors de l'imagerie | مادة تُحقن لتحسين وضوح الصورة أثناء التصوير |
| [ ] | 596 | 4447 | PET scan | A nuclear imaging scan showing metabolic activity | Examen d'imagerie nucléaire montrant l'activité métabolique | فحص تصوير نووي يوضح النشاط الأيضي |
| [ ] | 597 | 4448 | Bone scan | A nuclear medicine scan imaging bone activity | Examen de médecine nucléaire imagant l'activité osseuse | فحص طب نووي يوضح نشاط العظام |
| [ ] | 598 | 4449 | Angiography | X-ray imaging of blood vessels using contrast dye | Radiographie des vaisseaux sanguins utilisant un produit de contraste | تصوير بالأشعة للأوعية الدموية باستخدام مادة تباين |
| [ ] | 599 | 4450 | Echocardiogram | An ultrasound of the heart | Échographie du cœur | موجات فوق صوتية على القلب |
| [ ] | 600 | 4451 | DEXA scan | A scan measuring bone density to diagnose osteoporosis | Examen mesurant la densité osseuse pour diagnostiquer l'ostéoporose | فحص يقيس كثافة العظم لتشخيص هشاشة العظام |
| [ ] | 601 | 4452 | Fluoroscopy | Continuous X-ray imaging to observe moving body parts | Imagerie radiographique continue pour observer les parties mobiles du corps | تصوير بالأشعة مستمر لمراقبة أجزاء الجسم المتحركة |
| [ ] | 602 | 4453 | Radiation exposure | Contact with ionizing radiation from medical or other sources | Contact avec un rayonnement ionisant d'origine médicale ou autre | تعرض لإشعاع مؤين من مصدر طبي أو غيره |
| [ ] | 603 | 4454 | Report (medical imaging) | A written interpretation of medical imaging findings | Interprétation écrite des résultats d'imagerie médicale | تقرير مكتوب بنتائج التصوير الطبي |

## Medical Abbreviations (25)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 604 | 4455 | BP — Blood Pressure | Abbreviation for Blood Pressure | Abréviation de la pression artérielle | اختصار ضغط الدم |
| [ ] | 605 | 4456 | HR — Heart Rate | Abbreviation for Heart Rate | Abréviation de la fréquence cardiaque | اختصار معدل ضربات القلب |
| [ ] | 606 | 4457 | RR — Respiratory Rate | Abbreviation for Respiratory Rate | Abréviation de la fréquence respiratoire | اختصار معدل التنفس |
| [ ] | 607 | 4458 | Temp — Temperature | Abbreviation for body temperature | Abréviation de la température corporelle | اختصار حرارة الجسم |
| [ ] | 608 | 4459 | SpO2 — Oxygen Saturation | Oxygen saturation measured by pulse oximeter | Saturation en oxygène mesurée par oxymètre de pouls | تشبع الأكسجين المقاس بجهاز قياس التأكسج |
| [ ] | 609 | 4460 | BMI — Body Mass Index | A weight-for-height measure | Indice de masse corporelle, mesure du poids par rapport à la taille | مؤشر كتلة الجسم، مقياس للوزن مقابل الطول |
| [ ] | 610 | 4461 | SOB — Shortness of Breath | Abbreviated term for breathing difficulty | Abréviation de l'essoufflement et des difficultés respiratoires | اختصار ضيق التنفس |
| [ ] | 611 | 4462 | N/V — Nausea and Vomiting | Common abbreviation for nausea and vomiting | Abréviation de nausées et vomissements | اختصار الغثيان والقيء |
| [ ] | 612 | 4463 | c/o — Complains of | Medical shorthand meaning the patient reports a symptom | Abréviation médicale signifiant que le patient rapporte un symptôme | اختصار طبي يعني أن المريض يشكو من عرض |
| [ ] | 613 | 4464 | Dx — Diagnosis | Abbreviation for diagnosis | Abréviation de diagnostic | اختصار التشخيص |
| [ ] | 614 | 4465 | Rx — Prescription / Treatment | Abbreviation for prescription or treatment plan | Abréviation de prescription ou de plan de traitement | اختصار الوصفة الطبية أو خطة العلاج |
| [ ] | 615 | 4466 | Hx — History | Abbreviation for medical history | Abréviation d'antécédents médicaux | اختصار التاريخ المرضي |
| [ ] | 616 | 4467 | sx — Symptoms | Abbreviation for symptoms | Abréviation de symptômes | اختصار الأعراض |
| [ ] | 617 | 4468 | PRN — As Needed | Latin: pro re nata — take only when needed | Du latin : pro re nata — à prendre seulement si nécessaire | من اللاتينية: عند الحاجة — يؤخذ فقط عند الضرورة |
| [ ] | 618 | 4469 | q.d. / QD — Once Daily | Abbreviation for once-daily dosing | Abréviation pour une prise par jour | اختصار الجرعة مرة يوميًا |
| [ ] | 619 | 4470 | b.i.d. / BID — Twice Daily | Abbreviation for twice-daily dosing | Abréviation pour deux prises par jour | اختصار الجرعة مرتين يوميًا |
| [ ] | 620 | 4471 | NPO — Nothing by Mouth | Latin: nil per os — no food or drink before procedure | Du latin : nil per os — ni aliment ni boisson avant un examen | من اللاتينية: لا شيء عن طريق الفم — لا طعام ولا شراب قبل الفحص |
| [ ] | 621 | 4472 | IV — Intravenous | Into or within a vein | Dans ou à l'intérieur d'une veine | داخل الوريد أو إلى الوريد |
| [ ] | 622 | 4473 | IM — Intramuscular | Into a muscle | Dans un muscle | إعطاء الدواء إلى داخل العضلة |
| [ ] | 623 | 4474 | po — By Mouth (oral) | Latin: per os — administered orally | Du latin : per os — par voie orale | من اللاتينية: عن طريق الفم |
| [ ] | 624 | 4475 | STAT — Immediately | Latin: statim — to be done or given immediately | Du latin : statim — à faire ou à donner immédiatement | من اللاتينية: فورًا — يُجرى أو يُعطى فورًا |
| [ ] | 625 | 4476 | DOA — Dead on Arrival | A patient who is found dead when emergency services arrive | Patient retrouvé mort à l'arrivée des secours | مريض يُعثر عليه ميتًا عند وصول خدمات الإسعاف |
| [ ] | 626 | 4477 | ICU — Intensive Care Unit | A specialized hospital unit for critically ill patients | Unité hospitalière spécialisée pour les patients dans un état critique | وحدة متخصصة في المستشفى للمرضى في حالة حرجة |
| [ ] | 627 | 4478 | OR — Operating Room | The room in a hospital where surgical operations are performed | Salle de l'hôpital où se déroulent les interventions chirurgicales | غرفة في المستشفى تُجرى فيها العمليات الجراحية |
| [ ] | 628 | 4479 | ER / ED — Emergency Room | The department for emergency medical treatment | Service de traitement médical d'urgence | قسم العلاج الطبي الطارئ |

## Body Functions & Processes (11)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 629 | 4480 | Breathing | The process of inhaling and exhaling air | Processus d'inhalation et d'expiration de l'air | عملية شهيق وزفير الهواء |
| [ ] | 630 | 4481 | Digestion | Breaking down food into absorbable nutrients | Décomposition des aliments en nutriments absorbables | تحليل الغذاء إلى مغذيات قابلة للامتصاص |
| [ ] | 631 | 4482 | Circulation | The movement of blood through the body | Mouvement du sang dans l'ensemble du corps | حركة الدم في جميع أنحاء الجسم |
| [ ] | 633 | 4483 | Defecation | The act of passing feces | Actus d'évacuation des selles | عملية التبرز |
| [ ] | 634 | 4484 | Sweating (Perspiration) | The release of fluid through skin pores to cool the body | Libération de liquide par les pores de la peau pour refroidir le corps | إفراز سوائل عبر مسام الجلد لتبريد الجسم |
| [ ] | 635 | 4485 | Metabolism | All chemical reactions in the body sustaining life | Ensemble des réactions chimiques du corps qui entretiennent la vie | جميع التفاعلات الكيميائية في الجسم التي تدعم الحياة |
| [ ] | 636 | 4486 | Immunity | The body's ability to resist infections | Capacité du corps à résister aux infections | قدرة الجسم على مقاومة العدوى |
| [ ] | 637 | 4487 | Hormone regulation | The control of hormone levels to maintain body balance | Contrôle des taux hormonaux pour maintenir l'équilibre du corps | ضبط مستويات الهرمونات للحفاظ على توازن الجسم |
| [ ] | 638 | 4488 | Wound healing | The biological process of repairing damaged tissue | Processus biologique de réparation des tissus endommagés | العملية البيولوجية لإصلاح الأنسجة التالفة |
| [ ] | 639 | 4489 | Inflammation response | The body's reaction to infection or injury | Réaction du corps à une infection ou à une lésion | استجابة الجسم للعدوى أو الإصابة |
| [ ] | 640 | 4490 | Nerve signal | Electrical impulse transmitted through nerve fibers | Impulsion électrique transmise le long des fibres nerveuses | نبضة كهربائية تنتقل عبر الألياف العصبية |

## Talking to Your Doctor (10)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 641 | 4491 | Where does it hurt? | A doctor's question to locate the patient's pain | Question du médecin pour localiser la douleur du patient | سؤال الطبيب لتحديد مكان ألم المريض |
| [ ] | 642 | 4492 | How long has this been going on? | A question about duration of symptoms | Question sur la durée des symptômes | سؤال عن مدة الأعراض |
| [ ] | 643 | 4493 | Does anything make it better? | A question about relieving or aggravating factors | Question sur les facteurs qui soulagent ou aggravent les symptômes | سؤال عن العوامل التي تخفف الأعراض أو تفاقمها |
| [ ] | 644 | 4494 | Do you have any other symptoms? | Checking for additional symptoms | Recherche d'autres symptômes | البحث عن أعراض أخرى |
| [ ] | 645 | 4495 | I'll refer you to a specialist | A statement directing patient to another doctor | Phrase renvoyant le patient vers un autre médecin | عبارة تحيل المريض إلى طبيب آخر |
| [ ] | 646 | 4496 | You need to fast before the test | An instruction about not eating before a medical test | Instruction de ne pas manger avant un examen | تعليمات بعدم الأكل قبل الفحص |
| [ ] | 647 | 4497 | Take this medicine with food | Medication instruction for safe use | Instruction médicamenteuse pour un usage sûr | تعليمات دوائية لاستخدام آمن |
| [ ] | 648 | 4498 | Come back if symptoms worsen | A follow-up warning instruction from the doctor | Instruction de suivi du médecin en cas d'aggravation des symptômes | تعليمات متابعة من الطبيب عند تفاقم الأعراض |
| [ ] | 649 | 4499 | I need you to breathe normally | An instruction during physical examination | Instruction donnée pendant l'examen physique | تعليمات تُعطى أثناء الفحص البدني |
| [ ] | 650 | 4500 | You are going to feel a small pinch | A warning before a needle injection | Avertissement avant une injection à l'aiguille | تحذير قبل الحقن بالإبرة |

## Preventive Medicine & Wellness (12)

| | id src | id | terme (EN) | definition EN | definition FR (a relire) | definition AR (a relire) |
|---|---|---|---|---|---|---|
| [ ] | 651 | 4501 | Preventive medicine | Medical practice focused on preventing disease before it occurs | Pratique médicale axée sur la prévention des maladies | طب وقائي يركز على منع الأمراض قبل حدوثها |
| [ ] | 652 | 4502 | Annual physical exam | A yearly routine health assessment by a doctor | Évaluation médicale annuelle de routine | فحص طبي سنوي روتيني |
| [ ] | 653 | 4503 | Immunization | The process of becoming immune through vaccination | Processus d'acquisition de l'immunité par la vaccination | عملية اكتساب المناعة عبر التطعيم |
| [ ] | 654 | 4504 | Cancer screening | Tests done to detect cancer before symptoms appear | Examens réalisés pour détecter le cancer avant l'apparition des symptômes | فحوصات للكشف عن السرطان قبل ظهور الأعراض |
| [ ] | 655 | 4505 | Hand hygiene | Washing and sanitizing hands to prevent disease spread | Lavage et désinfection des mains pour prévenir la propagation des maladies | غسل اليدين وتعقيمهما لمنع انتشار الأمراض |
| [ ] | 656 | 4506 | Sunscreen (SPF) | A product protecting skin from UV radiation | Produit protégeant la peau des rayons ultraviolets | واقي شمس يحمي الجلد من الأشعة فوق البنفسجية |
| [ ] | 657 | 4507 | Healthy diet | Eating patterns promoting good health | Alimentation favorisant une bonne santé | نظام غذائي يعزز الصحة الجيدة |
| [ ] | 658 | 4508 | Physical activity | Regular exercise promoting physical and mental health | Exercice régulier favorisant la santé physique et mentale | نشاط بدني منتظم يدعم الصحة الجسدية والنفسية |
| [ ] | 659 | 4509 | Stress management | Techniques to reduce and cope with stress | Techniques pour réduire le stress et y faire face | تقنيات للتخلص من التوتر والتعامل معه |
| [ ] | 660 | 4510 | Quit smoking | Stopping the use of tobacco products | Arrêt de l'usage des produits du tabac | الإقلاع عن استخدام منتجات التبغ |
| [ ] | 661 | 4511 | Dental hygiene | Care practices to maintain healthy teeth and gums | Pratiques d'entretien des dents et des gencives | ممارسات العناية بالأسنان واللثة |
| [ ] | 662 | 4512 | Sleep hygiene | Habits promoting regular, quality sleep | Habitudes favorisant un sommeil régulier et de qualité | عادات تعزز نومًا منتظمًا بجودة عالية |
