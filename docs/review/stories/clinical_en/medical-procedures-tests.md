# Relecture — Medical Procedures & Tests (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-020` — Le cas de Bilal : Radiographie anormal(e) / _Bilal: the x-ray is not normal_

**Objectif :** Comprendre des résultats : Analyse de sang normal(e), Radiographie anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The blood test is normal.”
   - FR : Le médecin dit : « Analyse de sang est normal(e). »
   - AR : يقول الطبيب: «تحليل الدم طبيعي/طبيعية.»
5. **EN** “But the x-ray is not normal.”
   - FR : « Mais Radiographie n'est pas normal(e). »
   - AR : «لكن أشعة سينية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the x-ray normal?”
   - FR : Lina demande : « Radiographie est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل أشعة سينية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:blood-test:v1` — **a blood test** · FR : Analyse de sang · AR : تحليل الدم
- `gen:urine-test:v1` — **an urine test** · FR : Analyse d'urine · AR : تحليل البول
- `gen:x-ray:v1` — **a x-ray** · FR : Radiographie · AR : أشعة سينية

### Questions (acceptées / modèle / hint)
- **q1.** Is the blood test normal?
  - accepté : yes ; yes it is ; the blood test is normal ; it is normal
  - modèle : Yes, the blood test is normal.
- **q2.** Is the x-ray normal?
  - accepté : no ; no it is not ; no it isn't ; the x-ray is not normal ; it is not normal ; it isn't normal
  - modèle : No, the x-ray is not normal.
  - hint (si erreur) — FR : Attention : Radiographie n'est PAS normal(e).
- **q3.** Which one is not normal: the blood test or the x-ray?
  - accepté : x-ray ; the x-ray
  - modèle : The x-ray is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the urine test normal?
  - accepté : yes ; yes it is ; the urine test is normal
  - modèle : Yes, the urine test is normal.
- **Tt2.** Is the blood test normal?
  - accepté : no ; no it is not ; no it isn't ; the blood test is not normal ; it is not normal
  - modèle : No, the blood test is not normal.
  - hint (si erreur) — FR : Attention : Analyse de sang n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The blood test … normal. (be)` → is · modèle : The blood test is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The blood test is… / The x-ray is not… — modèle : *The blood test is normal. The x-ray is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Analyse d'urine normal(e), mais Analyse de sang anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The urine test is normal. The blood test is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-020` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Analyse de sang et Analyse d'urine à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient.
   - FR : Omar est un·e patient·e.
   - AR : Omar مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check blood test.”
   - FR : « D'abord, nous vérifions Analyse de sang. »
   - AR : «أولًا، نتحقق من تحليل الدم.»
5. **EN** “Then, we check urine test.”
   - FR : « Ensuite, nous vérifions Analyse d'urine. »
   - AR : «ثم نتحقق من تحليل البول.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:blood-test:v1` — **a blood test** · FR : Analyse de sang · AR : تحليل الدم
- `gen:urine-test:v1` — **an urine test** · FR : Analyse d'urine · AR : تحليل البول
- `gen:x-ray:v1` — **a x-ray** · FR : Radiographie · AR : أشعة سينية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : blood test ; we check blood test ; blood test first ; the doctor checks blood test first
  - modèle : First, the doctor checks blood test.
- **q2.** What does the doctor check next?
  - accepté : urine test ; then we check urine test ; urine test next ; the doctor checks urine test next
  - modèle : Then, the doctor checks urine test.
- **q3.** Does the doctor check urine test before blood test?
  - accepté : no ; no he does not ; no he doesn't ; he checks blood test first
  - modèle : No, he checks blood test first, then urine test.
  - hint (si erreur) — FR : Attention : Analyse de sang vient D'ABORD, ensuite Analyse d'urine.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : x-ray ; x-ray first ; he checks x-ray first
  - modèle : First, the doctor checks x-ray.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check blood test. Then, we check urine test.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Radiographie, ensuite Analyse de sang, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check x-ray. Then, we check blood test.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-020` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Analyse de sang / Analyse d'urine, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the blood test.
   - FR : Le patient du lit 3 a Analyse de sang.
   - AR : مريض السرير ٣ عنده تحليل الدم.
4. **EN** The patient in bed 5 has a problem with the urine test.
   - FR : Le patient du lit 5 a Analyse d'urine.
   - AR : مريض السرير ٥ عنده تحليل البول.
5. **EN** The patient in bed 5 does not have a problem with the blood test.
   - FR : Le patient du lit 5 n'a pas de Analyse de sang.
   - AR : مريض السرير ٥ ليس عنده تحليل الدم.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:blood-test:v1` — **a blood test** · FR : Analyse de sang · AR : تحليل الدم
- `gen:urine-test:v1` — **an urine test** · FR : Analyse d'urine · AR : تحليل البول
- `gen:x-ray:v1` — **a x-ray** · FR : Radiographie · AR : أشعة سينية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : blood test ; a blood test ; the patient with blood test
  - modèle : The patient in bed 3 has a blood test.
- **q2.** What does the patient in bed 5 have?
  - accepté : urine test ; a problem with the urine test
  - modèle : He has a problem with the urine test.
- **q3.** Does the patient in bed 5 have blood test?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the blood test ; he doesn't
  - modèle : No, he does not have a problem with the blood test.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS analyse de sang — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : x-ray ; a problem with the x-ray
  - modèle : He has a problem with the x-ray.
- **Tt2.** Does the patient in bed 9 have urine test?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the urine test ; he doesn't
  - modèle : No, he does not have a problem with the urine test.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS analyse d'urine.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a blood test and an urine test. (have)` → have · modèle : The patients have a blood test and an urine test.
- `The patient in bed 5 … not have a blood test. (do)` → does · modèle : The patient in bed 5 does not have a blood test.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the blood test. The patient in bed 5 has a problem with the urine test.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Radiographie, le lit 9 a Analyse de sang et n'a pas Analyse d'urine.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the x-ray. The patient in bed 9 has a problem with the blood test.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
