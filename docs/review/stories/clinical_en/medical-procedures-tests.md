# Relecture — Medical Procedures & Tests (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-020` — Le cas de Meriem : Analyse de sang / _Meriem: Blood test_

**Objectif :** Comprendre un cas : analyse de sang présent, analyse d'urine absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient at the clinic.
   - FR : Meriem est un·e patient·e à la clinique.
   - AR : Meriem مريض/مريضة في العيادة.
2. **EN** Meriem talks to Lina, a medical student.
   - FR : Meriem parle avec Lina, étudiant·e en médecine.
   - AR : Meriem يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a blood test.
   - FR : Elle a Analyse de sang.
   - AR : عنده تحليل الدم.
4. **EN** She does not have a urine test.
   - FR : Elle n'a pas de Analyse d'urine.
   - AR : ليس عنده تحليل البول.
5. **EN** Lina asks, “Do you have a urine test?”
   - FR : Lina demande : « Avez-vous Analyse d'urine ? »
   - AR : يسأل Lina: «هل عندك تحليل البول؟»
6. **EN** Meriem says, “No, I do not.”
   - FR : Meriem répond : « Non. »
   - AR : يجيب Meriem: «لا.»
7. **EN** Lina writes “Blood test” in the file.
   - FR : Lina écrit « Analyse de sang » dans le dossier.
   - AR : يكتب Lina «Analyse de sang» في الملف.

### Expressions ciblées
- `gen:blood-test:v1` — **a blood test** · FR : Analyse de sang · AR : تحليل الدم
- `gen:urine-test:v1` — **a urine test** · FR : Analyse d'urine · AR : تحليل البول
- `gen:x-ray:v1` — **a x-ray** · FR : Radiographie · AR : أشعة سينية

### Questions (acceptées / modèle / hint)
- **q1.** Does Meriem have blood test?
  - accepté : yes ; yes she does ; yes she has a blood test ; she has a blood test
  - modèle : Yes, she has a blood test.
- **q2.** Does she have urine test?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a urine test ; she doesn't
  - modèle : No, she does not have a urine test.
  - hint (si erreur) — FR : Attention : Meriem n'a PAS analyse d'urine.
- **q3.** Does she have blood test or urine test?
  - accepté : blood test ; a blood test ; she has a blood test
  - modèle : She has a blood test.
- **q4.** What does Meriem have?
  - accepté : blood test ; a blood test ; she has a blood test
  - modèle : She has a blood test.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have x-ray?
  - accepté : yes ; yes he does ; he has a x-ray
  - modèle : Yes, he has a x-ray.
- **Tt2.** Does he have blood test?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a blood test ; he doesn't
  - modèle : No, he does not have a blood test.
  - hint (si erreur) — FR : Attention : il n'a PAS analyse de sang.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a blood test. (have)` → has · modèle : She has a blood test.
- `They … not have a urine test. (do)` → do · modèle : They do not have a urine test.
- `I … a blood test. (have)` → have · modèle : I have a blood test.

### Productions
- **Reformulation :** Rapporte le cas de Meriem en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a blood test. She does not have a urine test.*
- **Transfert (contexte) :** Un autre patient a Radiographie. Il n'a pas de Analyse de sang.
- Glossaire : Radiographie : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a x-ray. He does not have a blood test.*

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
- `gen:urine-test:v1` — **a urine test** · FR : Analyse d'urine · AR : تحليل البول
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
3. **EN** The patient in bed 3 has a blood test.
   - FR : Le patient du lit 3 a Analyse de sang.
   - AR : مريض السرير ٣ عنده تحليل الدم.
4. **EN** The patient in bed 5 has a urine test.
   - FR : Le patient du lit 5 a Analyse d'urine.
   - AR : مريض السرير ٥ عنده تحليل البول.
5. **EN** The patient in bed 5 does not have a blood test.
   - FR : Le patient du lit 5 n'a pas de Analyse de sang.
   - AR : مريض السرير ٥ ليس عنده تحليل الدم.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:blood-test:v1` — **a blood test** · FR : Analyse de sang · AR : تحليل الدم
- `gen:urine-test:v1` — **a urine test** · FR : Analyse d'urine · AR : تحليل البول
- `gen:x-ray:v1` — **a x-ray** · FR : Radiographie · AR : أشعة سينية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : blood test ; a blood test ; the patient with blood test
  - modèle : The patient in bed 3 has a blood test.
- **q2.** What does the patient in bed 5 have?
  - accepté : urine test ; a urine test ; he has a urine test
  - modèle : He has a urine test.
- **q3.** Does the patient in bed 5 have blood test?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a blood test ; he doesn't
  - modèle : No, he does not have a blood test.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS analyse de sang — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : x-ray ; a x-ray ; he has a x-ray
  - modèle : He has a x-ray.
- **Tt2.** Does the patient in bed 9 have urine test?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a urine test ; he doesn't
  - modèle : No, he does not have a urine test.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS analyse d'urine.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a blood test and a urine test. (have)` → have · modèle : The patients have a blood test and a urine test.
- `The patient in bed 5 … not have a blood test. (do)` → does · modèle : The patient in bed 5 does not have a blood test.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a blood test. The patient in bed 5 has a urine test.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Radiographie, le lit 9 a Analyse de sang et n'a pas Analyse d'urine.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a x-ray. The patient in bed 9 has a blood test.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
