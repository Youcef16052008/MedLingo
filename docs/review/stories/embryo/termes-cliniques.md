# Relecture — Termes Cliniques (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-012` — Le cas de Karim : Prélèvement de villosités choriales (PVC) anormal(e) / _Karim: the chorionic villus sampling is not normal_

**Objectif :** Comprendre des résultats : Diagnostic prénatal normal(e), Prélèvement de villosités choriales (PVC) anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient at the clinic.
   - FR : Karim est un·e patient·e à la clinique.
   - AR : Karim مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The prenatal diagnosis is normal.”
   - FR : Le médecin dit : « Diagnostic prénatal est normal(e). »
   - AR : يقول الطبيب: «التشخيص قبل الولادة طبيعي/طبيعية.»
5. **EN** “But the chorionic villus sampling is not normal.”
   - FR : « Mais Prélèvement de villosités choriales (PVC) n'est pas normal(e). »
   - AR : «لكن أخذ عينة من الزغابات المشيمية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the chorionic villus sampling normal?”
   - FR : Lina demande : « Prélèvement de villosités choriales (PVC) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل أخذ عينة من الزغابات المشيمية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:prenatal-diagnosis:v1` — **prenatal diagnosis** · FR : Diagnostic prénatal · AR : التشخيص قبل الولادة
- `gen:amniocentesis:v1` — **amniocentesis** · FR : Amniocentèse · AR : بزل السلى
- `gen:chorionic-villus-sampling:v1` — **a chorionic villus sampling** · FR : Prélèvement de villosités choriales (PVC) · AR : أخذ عينة من الزغابات المشيمية

### Questions (acceptées / modèle / hint)
- **q1.** Is the prenatal diagnosis normal?
  - accepté : yes ; yes it is ; the prenatal diagnosis is normal ; it is normal
  - modèle : Yes, the prenatal diagnosis is normal.
- **q2.** Is the chorionic villus sampling normal?
  - accepté : no ; no it is not ; no it isn't ; the chorionic villus sampling is not normal ; it is not normal ; it isn't normal
  - modèle : No, the chorionic villus sampling is not normal.
  - hint (si erreur) — FR : Attention : Prélèvement de villosités choriales (PVC) n'est PAS normal(e).
- **q3.** Which one is not normal: the prenatal diagnosis or the chorionic villus sampling?
  - accepté : chorionic villus sampling ; the chorionic villus sampling
  - modèle : The chorionic villus sampling is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the amniocentesis normal?
  - accepté : yes ; yes it is ; the amniocentesis is normal
  - modèle : Yes, the amniocentesis is normal.
- **Tt2.** Is the prenatal diagnosis normal?
  - accepté : no ; no it is not ; no it isn't ; the prenatal diagnosis is not normal ; it is not normal
  - modèle : No, the prenatal diagnosis is not normal.
  - hint (si erreur) — FR : Attention : Diagnostic prénatal n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The prenatal diagnosis … normal. (be)` → is · modèle : The prenatal diagnosis is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The prenatal diagnosis is… / The chorionic villus sampling is not… — modèle : *The prenatal diagnosis is normal. The chorionic villus sampling is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Amniocentèse normal(e), mais Diagnostic prénatal anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The amniocentesis is normal. The prenatal diagnosis is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-012` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Diagnostic prénatal et Amniocentèse à un patient.
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
4. **EN** “First, we check prenatal diagnosis.”
   - FR : « D'abord, nous vérifions Diagnostic prénatal. »
   - AR : «أولًا، نتحقق من التشخيص قبل الولادة.»
5. **EN** “Then, we check amniocentesis.”
   - FR : « Ensuite, nous vérifions Amniocentèse. »
   - AR : «ثم نتحقق من بزل السلى.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:prenatal-diagnosis:v1` — **prenatal diagnosis** · FR : Diagnostic prénatal · AR : التشخيص قبل الولادة
- `gen:amniocentesis:v1` — **amniocentesis** · FR : Amniocentèse · AR : بزل السلى
- `gen:chorionic-villus-sampling:v1` — **a chorionic villus sampling** · FR : Prélèvement de villosités choriales (PVC) · AR : أخذ عينة من الزغابات المشيمية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : prenatal diagnosis ; we check prenatal diagnosis ; prenatal diagnosis first ; the doctor checks prenatal diagnosis first
  - modèle : First, the doctor checks prenatal diagnosis.
- **q2.** What does the doctor check next?
  - accepté : amniocentesis ; then we check amniocentesis ; amniocentesis next ; the doctor checks amniocentesis next
  - modèle : Then, the doctor checks amniocentesis.
- **q3.** Does the doctor check amniocentesis before prenatal diagnosis?
  - accepté : no ; no he does not ; no he doesn't ; he checks prenatal diagnosis first
  - modèle : No, he checks prenatal diagnosis first, then amniocentesis.
  - hint (si erreur) — FR : Attention : Diagnostic prénatal vient D'ABORD, ensuite Amniocentèse.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : chorionic villus sampling ; chorionic villus sampling first ; he checks chorionic villus sampling first
  - modèle : First, the doctor checks chorionic villus sampling.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check prenatal diagnosis. Then, we check amniocentesis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Prélèvement de villosités choriales (PVC), ensuite Diagnostic prénatal, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check chorionic villus sampling. Then, we check prenatal diagnosis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-012` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Diagnostic prénatal / Amniocentèse, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the prenatal diagnosis.
   - FR : Le patient du lit 3 a Diagnostic prénatal.
   - AR : مريض السرير ٣ عنده التشخيص قبل الولادة.
4. **EN** The patient in bed 5 has a problem with the amniocentesis.
   - FR : Le patient du lit 5 a Amniocentèse.
   - AR : مريض السرير ٥ عنده بزل السلى.
5. **EN** The patient in bed 5 does not have a problem with the prenatal diagnosis.
   - FR : Le patient du lit 5 n'a pas de Diagnostic prénatal.
   - AR : مريض السرير ٥ ليس عنده التشخيص قبل الولادة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:prenatal-diagnosis:v1` — **prenatal diagnosis** · FR : Diagnostic prénatal · AR : التشخيص قبل الولادة
- `gen:amniocentesis:v1` — **amniocentesis** · FR : Amniocentèse · AR : بزل السلى
- `gen:chorionic-villus-sampling:v1` — **a chorionic villus sampling** · FR : Prélèvement de villosités choriales (PVC) · AR : أخذ عينة من الزغابات المشيمية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : prenatal diagnosis ; the patient with prenatal diagnosis
  - modèle : The patient in bed 3 has prenatal diagnosis.
- **q2.** What does the patient in bed 5 have?
  - accepté : amniocentesis ; a problem with the amniocentesis
  - modèle : He has a problem with the amniocentesis.
- **q3.** Does the patient in bed 5 have prenatal diagnosis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the prenatal diagnosis ; he doesn't
  - modèle : No, he does not have a problem with the prenatal diagnosis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS diagnostic prénatal — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : chorionic villus sampling ; a problem with the chorionic villus sampling
  - modèle : He has a problem with the chorionic villus sampling.
- **Tt2.** Does the patient in bed 9 have amniocentesis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the amniocentesis ; he doesn't
  - modèle : No, he does not have a problem with the amniocentesis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS amniocentèse.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … prenatal diagnosis and amniocentesis. (have)` → have · modèle : The patients have prenatal diagnosis and amniocentesis.
- `The patient in bed 5 … not have prenatal diagnosis. (do)` → does · modèle : The patient in bed 5 does not have prenatal diagnosis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the prenatal diagnosis. The patient in bed 5 has a problem with the amniocentesis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Prélèvement de villosités choriales (PVC), le lit 9 a Diagnostic prénatal et n'a pas Amniocentèse.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the chorionic villus sampling. The patient in bed 9 has a problem with the prenatal diagnosis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
