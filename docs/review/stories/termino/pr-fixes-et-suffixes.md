# Relecture — Préfixes et Suffixes (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-024` — Le cas de Sara : Bradycardie / _Sara: Bradycardia_

**Objectif :** Comprendre un cas : bradycardie présent, tachycardie absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Sara talks to Lina, a medical student.
   - FR : Sara parle avec Lina, étudiant·e en médecine.
   - AR : Sara يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has bradycardia.
   - FR : Elle a Bradycardie.
   - AR : عنده بطء ضربات القلب.
4. **EN** She does not have tachycardia.
   - FR : Elle n'a pas de Tachycardie.
   - AR : ليس عنده تسارع ضربات القلب.
5. **EN** Lina asks, “Do you have tachycardia?”
   - FR : Lina demande : « Avez-vous Tachycardie ? »
   - AR : يسأل Lina: «هل عندك تسارع ضربات القلب؟»
6. **EN** Sara says, “No, I do not.”
   - FR : Sara répond : « Non. »
   - AR : يجيب Sara: «لا.»
7. **EN** Lina writes “Bradycardia” in the file.
   - FR : Lina écrit « Bradycardie » dans le dossier.
   - AR : يكتب Lina «Bradycardie» في الملف.

### Expressions ciblées
- `gen:bradycardia:v1` — **bradycardia** · FR : Bradycardie · AR : بطء ضربات القلب
- `gen:tachycardia:v1` — **tachycardia** · FR : Tachycardie · AR : تسارع ضربات القلب
- `gen:dyspnea:v1` — **dyspnea** · FR : Dyspnée · AR : عسر التنفس / ضيق التنفس

### Questions (acceptées / modèle / hint)
- **q1.** Does Sara have bradycardia?
  - accepté : yes ; yes she does ; yes she has bradycardia ; she has bradycardia
  - modèle : Yes, she has bradycardia.
- **q2.** Does she have tachycardia?
  - accepté : no ; no she does not ; no she doesn't ; she does not have tachycardia ; she doesn't
  - modèle : No, she does not have tachycardia.
  - hint (si erreur) — FR : Attention : Sara n'a PAS tachycardie.
- **q3.** Does she have bradycardia or tachycardia?
  - accepté : bradycardia ; she has bradycardia
  - modèle : She has bradycardia.
- **q4.** What does Sara have?
  - accepté : bradycardia ; she has bradycardia
  - modèle : She has bradycardia.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have dyspnea?
  - accepté : yes ; yes he does ; he has dyspnea
  - modèle : Yes, he has dyspnea.
- **Tt2.** Does he have bradycardia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have bradycardia ; he doesn't
  - modèle : No, he does not have bradycardia.
  - hint (si erreur) — FR : Attention : il n'a PAS bradycardie.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … bradycardia. (have)` → has · modèle : She has bradycardia.
- `They … not have tachycardia. (do)` → do · modèle : They do not have tachycardia.
- `I … bradycardia. (have)` → have · modèle : I have bradycardia.

### Productions
- **Reformulation :** Rapporte le cas de Sara en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has bradycardia. She does not have tachycardia.*
- **Transfert (contexte) :** Un autre patient a Dyspnée. Il n'a pas de Bradycardie.
- Glossaire : Dyspnée : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has dyspnea. He does not have bradycardia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-024` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Bradycardie et Tachycardie à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient.
   - FR : Youssef est un·e patient·e.
   - AR : Youssef مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check bradycardia.”
   - FR : « D'abord, nous vérifions Bradycardie. »
   - AR : «أولًا، نتحقق من بطء ضربات القلب.»
5. **EN** “Then, we check tachycardia.”
   - FR : « Ensuite, nous vérifions Tachycardie. »
   - AR : «ثم نتحقق من تسارع ضربات القلب.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:bradycardia:v1` — **bradycardia** · FR : Bradycardie · AR : بطء ضربات القلب
- `gen:tachycardia:v1` — **tachycardia** · FR : Tachycardie · AR : تسارع ضربات القلب
- `gen:dyspnea:v1` — **dyspnea** · FR : Dyspnée · AR : عسر التنفس / ضيق التنفس

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : bradycardia ; we check bradycardia ; bradycardia first ; the doctor checks bradycardia first
  - modèle : First, the doctor checks bradycardia.
- **q2.** What does the doctor check next?
  - accepté : tachycardia ; then we check tachycardia ; tachycardia next ; the doctor checks tachycardia next
  - modèle : Then, the doctor checks tachycardia.
- **q3.** Does the doctor check tachycardia before bradycardia?
  - accepté : no ; no he does not ; no he doesn't ; he checks bradycardia first
  - modèle : No, he checks bradycardia first, then tachycardia.
  - hint (si erreur) — FR : Attention : Bradycardie vient D'ABORD, ensuite Tachycardie.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : dyspnea ; dyspnea first ; he checks dyspnea first
  - modèle : First, the doctor checks dyspnea.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check bradycardia. Then, we check tachycardia.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Dyspnée, ensuite Bradycardie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check dyspnea. Then, we check bradycardia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-024` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Bradycardie / Tachycardie, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has bradycardia.
   - FR : Le patient du lit 3 a Bradycardie.
   - AR : مريض السرير ٣ عنده بطء ضربات القلب.
4. **EN** The patient in bed 5 has tachycardia.
   - FR : Le patient du lit 5 a Tachycardie.
   - AR : مريض السرير ٥ عنده تسارع ضربات القلب.
5. **EN** The patient in bed 5 does not have bradycardia.
   - FR : Le patient du lit 5 n'a pas de Bradycardie.
   - AR : مريض السرير ٥ ليس عنده بطء ضربات القلب.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:bradycardia:v1` — **bradycardia** · FR : Bradycardie · AR : بطء ضربات القلب
- `gen:tachycardia:v1` — **tachycardia** · FR : Tachycardie · AR : تسارع ضربات القلب
- `gen:dyspnea:v1` — **dyspnea** · FR : Dyspnée · AR : عسر التنفس / ضيق التنفس

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : bradycardia ; the patient with bradycardia
  - modèle : The patient in bed 3 has bradycardia.
- **q2.** What does the patient in bed 5 have?
  - accepté : tachycardia
  - modèle : He has tachycardia.
- **q3.** Does the patient in bed 5 have bradycardia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have bradycardia ; he doesn't
  - modèle : No, he does not have bradycardia.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS bradycardie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : dyspnea
  - modèle : He has dyspnea.
- **Tt2.** Does the patient in bed 9 have tachycardia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have tachycardia ; he doesn't
  - modèle : No, he does not have tachycardia.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS tachycardie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … bradycardia and tachycardia. (have)` → have · modèle : The patients have bradycardia and tachycardia.
- `The patient in bed 5 … not have bradycardia. (do)` → does · modèle : The patient in bed 5 does not have bradycardia.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has bradycardia. The patient in bed 5 has tachycardia.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Dyspnée, le lit 9 a Bradycardie et n'a pas Tachycardie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has dyspnea. The patient in bed 9 has bradycardia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
