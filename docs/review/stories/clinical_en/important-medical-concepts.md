# Relecture — Important Medical Concepts (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-014` — Le cas de Leila : Chronique / _Leila: Chronic_

**Objectif :** Comprendre un cas : chronique présent, aigu absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Leila talks to Lina, a medical student.
   - FR : Leila parle avec Lina, étudiant·e en médecine.
   - AR : Leila يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a chronic.
   - FR : Elle a Chronique.
   - AR : عنده مزمن.
4. **EN** She does not have a acute.
   - FR : Elle n'a pas de Aigu.
   - AR : ليس عنده حاد.
5. **EN** Lina asks, “Do you have a acute?”
   - FR : Lina demande : « Avez-vous Aigu ? »
   - AR : يسأل Lina: «هل عندك حاد؟»
6. **EN** Leila says, “No, I do not.”
   - FR : Leila répond : « Non. »
   - AR : يجيب Leila: «لا.»
7. **EN** Lina writes “Chronic” in the file.
   - FR : Lina écrit « Chronique » dans le dossier.
   - AR : يكتب Lina «Chronique» في الملف.

### Expressions ciblées
- `gen:chronic:v1` — **a chronic** · FR : Chronique · AR : مزمن
- `gen:acute:v1` — **a acute** · FR : Aigu · AR : حاد
- `gen:contagious:v1` — **contagious** · FR : Contagieux · AR : معدٍ

### Questions (acceptées / modèle / hint)
- **q1.** Does Leila have chronic?
  - accepté : yes ; yes she does ; yes she has a chronic ; she has a chronic
  - modèle : Yes, she has a chronic.
- **q2.** Does she have acute?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a acute ; she doesn't
  - modèle : No, she does not have a acute.
  - hint (si erreur) — FR : Attention : Leila n'a PAS aigu.
- **q3.** Does she have chronic or acute?
  - accepté : chronic ; a chronic ; she has a chronic
  - modèle : She has a chronic.
- **q4.** What does Leila have?
  - accepté : chronic ; a chronic ; she has a chronic
  - modèle : She has a chronic.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have contagious?
  - accepté : yes ; yes he does ; he has contagious
  - modèle : Yes, he has contagious.
- **Tt2.** Does he have chronic?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a chronic ; he doesn't
  - modèle : No, he does not have a chronic.
  - hint (si erreur) — FR : Attention : il n'a PAS chronique.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a chronic. (have)` → has · modèle : She has a chronic.
- `They … not have a acute. (do)` → do · modèle : They do not have a acute.
- `I … a chronic. (have)` → have · modèle : I have a chronic.

### Productions
- **Reformulation :** Rapporte le cas de Leila en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a chronic. She does not have a acute.*
- **Transfert (contexte) :** Un autre patient a Contagieux. Il n'a pas de Chronique.
- Glossaire : Contagieux : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has contagious. He does not have a chronic.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-014` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Chronique et Aigu à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient.
   - FR : Farid est un·e patient·e.
   - AR : Farid مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check chronic.”
   - FR : « D'abord, nous vérifions Chronique. »
   - AR : «أولًا، نتحقق من مزمن.»
5. **EN** “Then, we check acute.”
   - FR : « Ensuite, nous vérifions Aigu. »
   - AR : «ثم نتحقق من حاد.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:chronic:v1` — **a chronic** · FR : Chronique · AR : مزمن
- `gen:acute:v1` — **a acute** · FR : Aigu · AR : حاد
- `gen:contagious:v1` — **contagious** · FR : Contagieux · AR : معدٍ

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : chronic ; we check chronic ; chronic first ; the doctor checks chronic first
  - modèle : First, the doctor checks chronic.
- **q2.** What does the doctor check next?
  - accepté : acute ; then we check acute ; acute next ; the doctor checks acute next
  - modèle : Then, the doctor checks acute.
- **q3.** Does the doctor check acute before chronic?
  - accepté : no ; no he does not ; no he doesn't ; he checks chronic first
  - modèle : No, he checks chronic first, then acute.
  - hint (si erreur) — FR : Attention : Chronique vient D'ABORD, ensuite Aigu.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : contagious ; contagious first ; he checks contagious first
  - modèle : First, the doctor checks contagious.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check chronic. Then, we check acute.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Contagieux, ensuite Chronique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check contagious. Then, we check chronic.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-014` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Chronique / Aigu, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a chronic.
   - FR : Le patient du lit 3 a Chronique.
   - AR : مريض السرير ٣ عنده مزمن.
4. **EN** The patient in bed 5 has a acute.
   - FR : Le patient du lit 5 a Aigu.
   - AR : مريض السرير ٥ عنده حاد.
5. **EN** The patient in bed 5 does not have a chronic.
   - FR : Le patient du lit 5 n'a pas de Chronique.
   - AR : مريض السرير ٥ ليس عنده مزمن.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:chronic:v1` — **a chronic** · FR : Chronique · AR : مزمن
- `gen:acute:v1` — **a acute** · FR : Aigu · AR : حاد
- `gen:contagious:v1` — **contagious** · FR : Contagieux · AR : معدٍ

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : chronic ; a chronic ; the patient with chronic
  - modèle : The patient in bed 3 has a chronic.
- **q2.** What does the patient in bed 5 have?
  - accepté : acute ; a acute ; he has a acute
  - modèle : He has a acute.
- **q3.** Does the patient in bed 5 have chronic?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a chronic ; he doesn't
  - modèle : No, he does not have a chronic.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS chronique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : contagious ; he has contagious
  - modèle : He has contagious.
- **Tt2.** Does the patient in bed 9 have acute?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a acute ; he doesn't
  - modèle : No, he does not have a acute.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS aigu.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a chronic and a acute. (have)` → have · modèle : The patients have a chronic and a acute.
- `The patient in bed 5 … not have a chronic. (do)` → does · modèle : The patient in bed 5 does not have a chronic.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a chronic. The patient in bed 5 has a acute.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Contagieux, le lit 9 a Chronique et n'a pas Aigu.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has contagious. The patient in bed 9 has a chronic.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
