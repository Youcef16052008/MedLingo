# Relecture — Common Illnesses & Conditions (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-005` — Le cas de Youssef : Hypertension / _Youssef: Hypertension_

**Objectif :** Comprendre un cas : hypertension présent, arthrite absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Youssef talks to Amine, a medical student.
   - FR : Youssef parle avec Amine, étudiant·e en médecine.
   - AR : Youssef يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has hypertension.
   - FR : Il a Hypertension.
   - AR : عنده ارتفاع ضغط الدم.
4. **EN** He does not have arthritis.
   - FR : Il n'a pas de Arthrite.
   - AR : ليس عنده التهاب المفاصل.
5. **EN** Amine asks, “Do you have arthritis?”
   - FR : Amine demande : « Avez-vous Arthrite ? »
   - AR : يسأل Amine: «هل عندك التهاب المفاصل؟»
6. **EN** Youssef says, “No, I do not.”
   - FR : Youssef répond : « Non. »
   - AR : يجيب Youssef: «لا.»
7. **EN** Amine writes “Hypertension” in the file.
   - FR : Amine écrit « Hypertension » dans le dossier.
   - AR : يكتب Amine «Hypertension» في الملف.

### Expressions ciblées
- `gen:hypertension:v1` — **hypertension** · FR : Hypertension · AR : ارتفاع ضغط الدم
- `gen:arthritis:v1` — **arthritis** · FR : Arthrite · AR : التهاب المفاصل
- `gen:cold:v1` — **a cold** · FR : Rhume · AR : نزلة برد

### Questions (acceptées / modèle / hint)
- **q1.** Does Youssef have hypertension?
  - accepté : yes ; yes he does ; yes he has hypertension ; he has hypertension
  - modèle : Yes, he has hypertension.
- **q2.** Does he have arthritis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have arthritis ; he doesn't
  - modèle : No, he does not have arthritis.
  - hint (si erreur) — FR : Attention : Youssef n'a PAS arthrite.
- **q3.** Does he have hypertension or arthritis?
  - accepté : hypertension ; he has hypertension
  - modèle : He has hypertension.
- **q4.** What does Youssef have?
  - accepté : hypertension ; he has hypertension
  - modèle : He has hypertension.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have cold?
  - accepté : yes ; yes he does ; he has a cold
  - modèle : Yes, he has a cold.
- **Tt2.** Does he have hypertension?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hypertension ; he doesn't
  - modèle : No, he does not have hypertension.
  - hint (si erreur) — FR : Attention : il n'a PAS hypertension.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … hypertension. (have)` → has · modèle : He has hypertension.
- `They … not have arthritis. (do)` → do · modèle : They do not have arthritis.
- `I … hypertension. (have)` → have · modèle : I have hypertension.

### Productions
- **Reformulation :** Rapporte le cas de Youssef en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has hypertension. He does not have arthritis.*
- **Transfert (contexte) :** Un autre patient a Rhume. Il n'a pas de Hypertension.
- Glossaire : Rhume : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a cold. He does not have hypertension.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Rhume et Grippe à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient.
   - FR : Hana est un·e patient·e.
   - AR : Hana مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check cold.”
   - FR : « D'abord, nous vérifions Rhume. »
   - AR : «أولًا، نتحقق من نزلة برد.»
5. **EN** “Then, we check flu.”
   - FR : « Ensuite, nous vérifions Grippe. »
   - AR : «ثم نتحقق من الإنفلونزا.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cold:v1` — **a cold** · FR : Rhume · AR : نزلة برد
- `gen:flu:v1` — **a flu** · FR : Grippe · AR : الإنفلونزا
- `gen:covid-19:v1` — **a COVID-19** · FR : COVID-19 · AR : كوفيد-19

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cold ; we check cold ; cold first ; the doctor checks cold first
  - modèle : First, the doctor checks cold.
- **q2.** What does the doctor check next?
  - accepté : flu ; then we check flu ; flu next ; the doctor checks flu next
  - modèle : Then, the doctor checks flu.
- **q3.** Does the doctor check flu before cold?
  - accepté : no ; no he does not ; no he doesn't ; he checks cold first
  - modèle : No, he checks cold first, then flu.
  - hint (si erreur) — FR : Attention : Rhume vient D'ABORD, ensuite Grippe.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : COVID-19 ; COVID-19 first ; he checks COVID-19 first
  - modèle : First, the doctor checks COVID-19.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cold. Then, we check flu.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord COVID-19, ensuite Rhume, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check COVID-19. Then, we check cold.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Hypertension / Arthrite, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has hypertension.
   - FR : Le patient du lit 3 a Hypertension.
   - AR : مريض السرير ٣ عنده ارتفاع ضغط الدم.
4. **EN** The patient in bed 5 has arthritis.
   - FR : Le patient du lit 5 a Arthrite.
   - AR : مريض السرير ٥ عنده التهاب المفاصل.
5. **EN** The patient in bed 5 does not have hypertension.
   - FR : Le patient du lit 5 n'a pas de Hypertension.
   - AR : مريض السرير ٥ ليس عنده ارتفاع ضغط الدم.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:hypertension:v1` — **hypertension** · FR : Hypertension · AR : ارتفاع ضغط الدم
- `gen:arthritis:v1` — **arthritis** · FR : Arthrite · AR : التهاب المفاصل
- `gen:cold:v1` — **a cold** · FR : Rhume · AR : نزلة برد

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hypertension ; the patient with hypertension
  - modèle : The patient in bed 3 has hypertension.
- **q2.** What does the patient in bed 5 have?
  - accepté : arthritis
  - modèle : He has arthritis.
- **q3.** Does the patient in bed 5 have hypertension?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hypertension ; he doesn't
  - modèle : No, he does not have hypertension.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS hypertension — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : cold ; a cold
  - modèle : He has a cold.
- **Tt2.** Does the patient in bed 9 have arthritis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have arthritis ; he doesn't
  - modèle : No, he does not have arthritis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS arthrite.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … hypertension and arthritis. (have)` → have · modèle : The patients have hypertension and arthritis.
- `The patient in bed 5 … not have hypertension. (do)` → does · modèle : The patient in bed 5 does not have hypertension.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has hypertension. The patient in bed 5 has arthritis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Rhume, le lit 9 a Hypertension et n'a pas Arthrite.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a cold. The patient in bed 9 has hypertension.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
