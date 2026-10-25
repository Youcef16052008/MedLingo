# Relecture — Préfixes et Suffixes (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-023` — Le cas de Bilal : Bradycardie / _Bilal: Bradycardia_

**Objectif :** Comprendre un cas : bradycardie présent, tachycardie absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Bilal talks to Amine, a medical student.
   - FR : Bilal parle avec Amine, étudiant·e en médecine.
   - AR : Bilal يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has bradycardia.
   - FR : Il a Bradycardie.
   - AR : عنده بطء ضربات القلب.
4. **EN** He does not have tachycardia.
   - FR : Il n'a pas de Tachycardie.
   - AR : ليس عنده تسارع ضربات القلب.
5. **EN** Amine asks, “Do you have tachycardia?”
   - FR : Amine demande : « Avez-vous Tachycardie ? »
   - AR : يسأل Amine: «هل عندك تسارع ضربات القلب؟»
6. **EN** Bilal says, “No, I do not.”
   - FR : Bilal répond : « Non. »
   - AR : يجيب Bilal: «لا.»
7. **EN** Amine writes “Bradycardia” in the file.
   - FR : Amine écrit « Bradycardie » dans le dossier.
   - AR : يكتب Amine «Bradycardie» في الملف.

### Expressions ciblées
- `gen:bradycardia:v1` — **bradycardia** · FR : Bradycardie · AR : بطء ضربات القلب
- `gen:tachycardia:v1` — **tachycardia** · FR : Tachycardie · AR : تسارع ضربات القلب
- `gen:dyspnea:v1` — **dyspnea** · FR : Dyspnée · AR : عسر التنفس / ضيق التنفس

### Questions (acceptées / modèle / hint)
- **q1.** Does Bilal have bradycardia?
  - accepté : yes ; yes he does ; yes he has bradycardia ; he has bradycardia
  - modèle : Yes, he has bradycardia.
- **q2.** Does he have tachycardia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have tachycardia ; he doesn't
  - modèle : No, he does not have tachycardia.
  - hint (si erreur) — FR : Attention : Bilal n'a PAS tachycardie.
- **q3.** Does he have bradycardia or tachycardia?
  - accepté : bradycardia ; he has bradycardia
  - modèle : He has bradycardia.
- **q4.** What does Bilal have?
  - accepté : bradycardia ; he has bradycardia
  - modèle : He has bradycardia.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have dyspnea?
  - accepté : yes ; yes he does ; he has dyspnea
  - modèle : Yes, he has dyspnea.
- **Tt2.** Does he have bradycardia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have bradycardia ; he doesn't
  - modèle : No, he does not have bradycardia.
  - hint (si erreur) — FR : Attention : il n'a PAS bradycardie.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … bradycardia. (have)` → has · modèle : He has bradycardia.
- `They … not have tachycardia. (do)` → do · modèle : They do not have tachycardia.
- `I … bradycardia. (have)` → have · modèle : I have bradycardia.

### Productions
- **Reformulation :** Rapporte le cas de Bilal en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has bradycardia. He does not have tachycardia.*
- **Transfert (contexte) :** Un autre patient a Dyspnée. Il n'a pas de Bradycardie.
- Glossaire : Dyspnée : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has dyspnea. He does not have bradycardia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-023` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Bradycardie et Tachycardie à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient.
   - FR : Nadia est un·e patient·e.
   - AR : Nadia مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
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
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

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
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
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

## 3. `termino-handover-023` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Bradycardie / Tachycardie, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
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
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

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
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
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
