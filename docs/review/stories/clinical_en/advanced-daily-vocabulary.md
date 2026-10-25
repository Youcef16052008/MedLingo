# Relecture — Advanced Daily Vocabulary (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-000` — Le cas de Sara : Convalescence / _Sara: Convalescence_

**Objectif :** Comprendre un cas : convalescence présent, rémission absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Sara talks to Lina, a medical student.
   - FR : Sara parle avec Lina, étudiant·e en médecine.
   - AR : Sara يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a convalescence.
   - FR : Elle a Convalescence.
   - AR : عنده فترة النقاهة.
4. **EN** She does not have a remission.
   - FR : Elle n'a pas de Rémission.
   - AR : ليس عنده مرحلة الهدأة.
5. **EN** Lina asks, “Do you have a remission?”
   - FR : Lina demande : « Avez-vous Rémission ? »
   - AR : يسأل Lina: «هل عندك مرحلة الهدأة؟»
6. **EN** Sara says, “No, I do not.”
   - FR : Sara répond : « Non. »
   - AR : يجيب Sara: «لا.»
7. **EN** Lina writes “Convalescence” in the file.
   - FR : Lina écrit « Convalescence » dans le dossier.
   - AR : يكتب Lina «Convalescence» في الملف.

### Expressions ciblées
- `gen:convalescence:v1` — **a convalescence** · FR : Convalescence · AR : فترة النقاهة
- `gen:remission:v1` — **a remission** · FR : Rémission · AR : مرحلة الهدأة
- `gen:quarantine:v1` — **a quarantine** · FR : Quarantaine · AR : الحجر الصحي

### Questions (acceptées / modèle / hint)
- **q1.** Does Sara have convalescence?
  - accepté : yes ; yes she does ; yes she has a convalescence ; she has a convalescence
  - modèle : Yes, she has a convalescence.
- **q2.** Does she have remission?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a remission ; she doesn't
  - modèle : No, she does not have a remission.
  - hint (si erreur) — FR : Attention : Sara n'a PAS rémission.
- **q3.** Does she have convalescence or remission?
  - accepté : convalescence ; a convalescence ; she has a convalescence
  - modèle : She has a convalescence.
- **q4.** What does Sara have?
  - accepté : convalescence ; a convalescence ; she has a convalescence
  - modèle : She has a convalescence.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have quarantine?
  - accepté : yes ; yes he does ; he has a quarantine
  - modèle : Yes, he has a quarantine.
- **Tt2.** Does he have convalescence?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a convalescence ; he doesn't
  - modèle : No, he does not have a convalescence.
  - hint (si erreur) — FR : Attention : il n'a PAS convalescence.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a convalescence. (have)` → has · modèle : She has a convalescence.
- `They … not have a remission. (do)` → do · modèle : They do not have a remission.
- `I … a convalescence. (have)` → have · modèle : I have a convalescence.

### Productions
- **Reformulation :** Rapporte le cas de Sara en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a convalescence. She does not have a remission.*
- **Transfert (contexte) :** Un autre patient a Quarantaine. Il n'a pas de Convalescence.
- Glossaire : Quarantaine : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a quarantine. He does not have a convalescence.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Convalescence et Rémission à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check convalescence.”
   - FR : « D'abord, nous vérifions Convalescence. »
   - AR : «أولًا، نتحقق من فترة النقاهة.»
5. **EN** “Then, we check remission.”
   - FR : « Ensuite, nous vérifions Rémission. »
   - AR : «ثم نتحقق من مرحلة الهدأة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:convalescence:v1` — **a convalescence** · FR : Convalescence · AR : فترة النقاهة
- `gen:remission:v1` — **a remission** · FR : Rémission · AR : مرحلة الهدأة
- `gen:quarantine:v1` — **a quarantine** · FR : Quarantaine · AR : الحجر الصحي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : convalescence ; we check convalescence ; convalescence first ; the doctor checks convalescence first
  - modèle : First, the doctor checks convalescence.
- **q2.** What does the doctor check next?
  - accepté : remission ; then we check remission ; remission next ; the doctor checks remission next
  - modèle : Then, the doctor checks remission.
- **q3.** Does the doctor check remission before convalescence?
  - accepté : no ; no he does not ; no he doesn't ; he checks convalescence first
  - modèle : No, he checks convalescence first, then remission.
  - hint (si erreur) — FR : Attention : Convalescence vient D'ABORD, ensuite Rémission.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : quarantine ; quarantine first ; he checks quarantine first
  - modèle : First, the doctor checks quarantine.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check convalescence. Then, we check remission.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Quarantaine, ensuite Convalescence, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check quarantine. Then, we check convalescence.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Convalescence / Rémission, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a convalescence.
   - FR : Le patient du lit 3 a Convalescence.
   - AR : مريض السرير ٣ عنده فترة النقاهة.
4. **EN** The patient in bed 5 has a remission.
   - FR : Le patient du lit 5 a Rémission.
   - AR : مريض السرير ٥ عنده مرحلة الهدأة.
5. **EN** The patient in bed 5 does not have a convalescence.
   - FR : Le patient du lit 5 n'a pas de Convalescence.
   - AR : مريض السرير ٥ ليس عنده فترة النقاهة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:convalescence:v1` — **a convalescence** · FR : Convalescence · AR : فترة النقاهة
- `gen:remission:v1` — **a remission** · FR : Rémission · AR : مرحلة الهدأة
- `gen:quarantine:v1` — **a quarantine** · FR : Quarantaine · AR : الحجر الصحي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : convalescence ; a convalescence ; the patient with convalescence
  - modèle : The patient in bed 3 has a convalescence.
- **q2.** What does the patient in bed 5 have?
  - accepté : remission ; a remission ; he has a remission
  - modèle : He has a remission.
- **q3.** Does the patient in bed 5 have convalescence?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a convalescence ; he doesn't
  - modèle : No, he does not have a convalescence.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS convalescence — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : quarantine ; a quarantine ; he has a quarantine
  - modèle : He has a quarantine.
- **Tt2.** Does the patient in bed 9 have remission?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a remission ; he doesn't
  - modèle : No, he does not have a remission.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS rémission.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a convalescence and a remission. (have)` → have · modèle : The patients have a convalescence and a remission.
- `The patient in bed 5 … not have a convalescence. (do)` → does · modèle : The patient in bed 5 does not have a convalescence.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a convalescence. The patient in bed 5 has a remission.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Quarantaine, le lit 9 a Convalescence et n'a pas Rémission.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a quarantine. The patient in bed 9 has a convalescence.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
