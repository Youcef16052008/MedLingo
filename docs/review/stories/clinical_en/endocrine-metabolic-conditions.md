# Relecture — Endocrine & Metabolic Conditions (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-009` — Le cas de Sofiane : Hypoglycémie / _Sofiane: Hypoglycemia_

**Objectif :** Comprendre un cas : hypoglycémie présent, hyperglycémie absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient at the clinic.
   - FR : Sofiane est un·e patient·e à la clinique.
   - AR : Sofiane مريض/مريضة في العيادة.
2. **EN** Sofiane talks to Amine, a medical student.
   - FR : Sofiane parle avec Amine, étudiant·e en médecine.
   - AR : Sofiane يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has a hypoglycemia.
   - FR : Il a Hypoglycémie.
   - AR : عنده انخفاض سكر الدم.
4. **EN** He does not have a hyperglycemia.
   - FR : Il n'a pas de Hyperglycémie.
   - AR : ليس عنده ارتفاع سكر الدم.
5. **EN** Amine asks, “Do you have a hyperglycemia?”
   - FR : Amine demande : « Avez-vous Hyperglycémie ? »
   - AR : يسأل Amine: «هل عندك ارتفاع سكر الدم؟»
6. **EN** Sofiane says, “No, I do not.”
   - FR : Sofiane répond : « Non. »
   - AR : يجيب Sofiane: «لا.»
7. **EN** Amine writes “Hypoglycemia” in the file.
   - FR : Amine écrit « Hypoglycémie » dans le dossier.
   - AR : يكتب Amine «Hypoglycémie» في الملف.

### Expressions ciblées
- `gen:hypoglycemia:v1` — **a hypoglycemia** · FR : Hypoglycémie · AR : انخفاض سكر الدم
- `gen:hyperglycemia:v1` — **a hyperglycemia** · FR : Hyperglycémie · AR : ارتفاع سكر الدم
- `gen:type-1-diabetes:v1` — **type 1 diabetes** · FR : Diabète de type 1 · AR : السكري من النوع الأول

### Questions (acceptées / modèle / hint)
- **q1.** Does Sofiane have hypoglycemia?
  - accepté : yes ; yes he does ; yes he has a hypoglycemia ; he has a hypoglycemia
  - modèle : Yes, he has a hypoglycemia.
- **q2.** Does he have hyperglycemia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a hyperglycemia ; he doesn't
  - modèle : No, he does not have a hyperglycemia.
  - hint (si erreur) — FR : Attention : Sofiane n'a PAS hyperglycémie.
- **q3.** Does he have hypoglycemia or hyperglycemia?
  - accepté : hypoglycemia ; a hypoglycemia ; he has a hypoglycemia
  - modèle : He has a hypoglycemia.
- **q4.** What does Sofiane have?
  - accepté : hypoglycemia ; a hypoglycemia ; he has a hypoglycemia
  - modèle : He has a hypoglycemia.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have type 1 diabetes?
  - accepté : yes ; yes he does ; he has type 1 diabetes
  - modèle : Yes, he has type 1 diabetes.
- **Tt2.** Does he have hypoglycemia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a hypoglycemia ; he doesn't
  - modèle : No, he does not have a hypoglycemia.
  - hint (si erreur) — FR : Attention : il n'a PAS hypoglycémie.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … a hypoglycemia. (have)` → has · modèle : He has a hypoglycemia.
- `They … not have a hyperglycemia. (do)` → do · modèle : They do not have a hyperglycemia.
- `I … a hypoglycemia. (have)` → have · modèle : I have a hypoglycemia.

### Productions
- **Reformulation :** Rapporte le cas de Sofiane en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has a hypoglycemia. He does not have a hyperglycemia.*
- **Transfert (contexte) :** Un autre patient a Diabète de type 1. Il n'a pas de Hypoglycémie.
- Glossaire : Diabète de type 1 : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has type 1 diabetes. He does not have a hypoglycemia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-009` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Hypoglycémie et Hyperglycémie à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient.
   - FR : Leila est un·e patient·e.
   - AR : Leila مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check hypoglycemia.”
   - FR : « D'abord, nous vérifions Hypoglycémie. »
   - AR : «أولًا، نتحقق من انخفاض سكر الدم.»
5. **EN** “Then, we check hyperglycemia.”
   - FR : « Ensuite, nous vérifions Hyperglycémie. »
   - AR : «ثم نتحقق من ارتفاع سكر الدم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:hypoglycemia:v1` — **a hypoglycemia** · FR : Hypoglycémie · AR : انخفاض سكر الدم
- `gen:hyperglycemia:v1` — **a hyperglycemia** · FR : Hyperglycémie · AR : ارتفاع سكر الدم
- `gen:type-1-diabetes:v1` — **type 1 diabetes** · FR : Diabète de type 1 · AR : السكري من النوع الأول

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : hypoglycemia ; we check hypoglycemia ; hypoglycemia first ; the doctor checks hypoglycemia first
  - modèle : First, the doctor checks hypoglycemia.
- **q2.** What does the doctor check next?
  - accepté : hyperglycemia ; then we check hyperglycemia ; hyperglycemia next ; the doctor checks hyperglycemia next
  - modèle : Then, the doctor checks hyperglycemia.
- **q3.** Does the doctor check hyperglycemia before hypoglycemia?
  - accepté : no ; no he does not ; no he doesn't ; he checks hypoglycemia first
  - modèle : No, he checks hypoglycemia first, then hyperglycemia.
  - hint (si erreur) — FR : Attention : Hypoglycémie vient D'ABORD, ensuite Hyperglycémie.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : type 1 diabetes ; type 1 diabetes first ; he checks type 1 diabetes first
  - modèle : First, the doctor checks type 1 diabetes.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check hypoglycemia. Then, we check hyperglycemia.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Diabète de type 1, ensuite Hypoglycémie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check type 1 diabetes. Then, we check hypoglycemia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-009` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Hypoglycémie / Hyperglycémie, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a hypoglycemia.
   - FR : Le patient du lit 3 a Hypoglycémie.
   - AR : مريض السرير ٣ عنده انخفاض سكر الدم.
4. **EN** The patient in bed 5 has a hyperglycemia.
   - FR : Le patient du lit 5 a Hyperglycémie.
   - AR : مريض السرير ٥ عنده ارتفاع سكر الدم.
5. **EN** The patient in bed 5 does not have a hypoglycemia.
   - FR : Le patient du lit 5 n'a pas de Hypoglycémie.
   - AR : مريض السرير ٥ ليس عنده انخفاض سكر الدم.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:hypoglycemia:v1` — **a hypoglycemia** · FR : Hypoglycémie · AR : انخفاض سكر الدم
- `gen:hyperglycemia:v1` — **a hyperglycemia** · FR : Hyperglycémie · AR : ارتفاع سكر الدم
- `gen:type-1-diabetes:v1` — **type 1 diabetes** · FR : Diabète de type 1 · AR : السكري من النوع الأول

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hypoglycemia ; a hypoglycemia ; the patient with hypoglycemia
  - modèle : The patient in bed 3 has a hypoglycemia.
- **q2.** What does the patient in bed 5 have?
  - accepté : hyperglycemia ; a hyperglycemia ; he has a hyperglycemia
  - modèle : He has a hyperglycemia.
- **q3.** Does the patient in bed 5 have hypoglycemia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a hypoglycemia ; he doesn't
  - modèle : No, he does not have a hypoglycemia.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS hypoglycémie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : type 1 diabetes ; he has type 1 diabetes
  - modèle : He has type 1 diabetes.
- **Tt2.** Does the patient in bed 9 have hyperglycemia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a hyperglycemia ; he doesn't
  - modèle : No, he does not have a hyperglycemia.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS hyperglycémie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a hypoglycemia and a hyperglycemia. (have)` → have · modèle : The patients have a hypoglycemia and a hyperglycemia.
- `The patient in bed 5 … not have a hypoglycemia. (do)` → does · modèle : The patient in bed 5 does not have a hypoglycemia.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a hypoglycemia. The patient in bed 5 has a hyperglycemia.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Diabète de type 1, le lit 9 a Hypoglycémie et n'a pas Hyperglycémie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has type 1 diabetes. The patient in bed 9 has a hypoglycemia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
