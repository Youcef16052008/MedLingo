# Relecture — Types d'Études (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-012` — Le cas de Karim : Étude expérimentale anormal(e) / _Karim: the experimental study is not normal_

**Objectif :** Comprendre des résultats : Plan d'étude normal(e), Étude expérimentale anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The study design is normal.”
   - FR : Le médecin dit : « Plan d'étude est normal(e). »
   - AR : يقول الطبيب: «تصميم الدراسة طبيعي/طبيعية.»
5. **EN** “But the experimental study is not normal.”
   - FR : « Mais Étude expérimentale n'est pas normal(e). »
   - AR : «لكن الدراسة التجريبية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the experimental study normal?”
   - FR : Lina demande : « Étude expérimentale est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الدراسة التجريبية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:study-design:v1` — **a study design** · FR : Plan d'étude · AR : تصميم الدراسة
- `gen:observational-study:v1` — **an observational study** · FR : Étude observationnelle · AR : الدراسة الملاحظية
- `gen:experimental-study:v1` — **an experimental study** · FR : Étude expérimentale · AR : الدراسة التجريبية

### Questions (acceptées / modèle / hint)
- **q1.** Is the study design normal?
  - accepté : yes ; yes it is ; the study design is normal ; it is normal
  - modèle : Yes, the study design is normal.
- **q2.** Is the experimental study normal?
  - accepté : no ; no it is not ; no it isn't ; the experimental study is not normal ; it is not normal ; it isn't normal
  - modèle : No, the experimental study is not normal.
  - hint (si erreur) — FR : Attention : Étude expérimentale n'est PAS normal(e).
- **q3.** Which one is not normal: the study design or the experimental study?
  - accepté : experimental study ; the experimental study
  - modèle : The experimental study is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the observational study normal?
  - accepté : yes ; yes it is ; the observational study is normal
  - modèle : Yes, the observational study is normal.
- **Tt2.** Is the study design normal?
  - accepté : no ; no it is not ; no it isn't ; the study design is not normal ; it is not normal
  - modèle : No, the study design is not normal.
  - hint (si erreur) — FR : Attention : Plan d'étude n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The study design … normal. (be)` → is · modèle : The study design is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The study design is… / The experimental study is not… — modèle : *The study design is normal. The experimental study is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Étude observationnelle normal(e), mais Plan d'étude anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The observational study is normal. The study design is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-012` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Plan d'étude et Étude observationnelle à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check study design.”
   - FR : « D'abord, nous vérifions Plan d'étude. »
   - AR : «أولًا، نتحقق من تصميم الدراسة.»
5. **EN** “Then, we check observational study.”
   - FR : « Ensuite, nous vérifions Étude observationnelle. »
   - AR : «ثم نتحقق من الدراسة الملاحظية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:study-design:v1` — **a study design** · FR : Plan d'étude · AR : تصميم الدراسة
- `gen:observational-study:v1` — **an observational study** · FR : Étude observationnelle · AR : الدراسة الملاحظية
- `gen:experimental-study:v1` — **an experimental study** · FR : Étude expérimentale · AR : الدراسة التجريبية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : study design ; we check study design ; study design first ; the doctor checks study design first
  - modèle : First, the doctor checks study design.
- **q2.** What does the doctor check next?
  - accepté : observational study ; then we check observational study ; observational study next ; the doctor checks observational study next
  - modèle : Then, the doctor checks observational study.
- **q3.** Does the doctor check observational study before study design?
  - accepté : no ; no he does not ; no he doesn't ; he checks study design first
  - modèle : No, he checks study design first, then observational study.
  - hint (si erreur) — FR : Attention : Plan d'étude vient D'ABORD, ensuite Étude observationnelle.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : experimental study ; experimental study first ; he checks experimental study first
  - modèle : First, the doctor checks experimental study.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check study design. Then, we check observational study.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Étude expérimentale, ensuite Plan d'étude, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check experimental study. Then, we check study design.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-012` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Plan d'étude / Étude observationnelle, lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the study design.
   - FR : Le patient du lit 3 a Plan d'étude.
   - AR : مريض السرير ٣ عنده تصميم الدراسة.
4. **EN** The patient in bed 5 has a problem with the observational study.
   - FR : Le patient du lit 5 a Étude observationnelle.
   - AR : مريض السرير ٥ عنده الدراسة الملاحظية.
5. **EN** The patient in bed 5 does not have a problem with the study design.
   - FR : Le patient du lit 5 n'a pas de Plan d'étude.
   - AR : مريض السرير ٥ ليس عنده تصميم الدراسة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:study-design:v1` — **a study design** · FR : Plan d'étude · AR : تصميم الدراسة
- `gen:observational-study:v1` — **an observational study** · FR : Étude observationnelle · AR : الدراسة الملاحظية
- `gen:experimental-study:v1` — **an experimental study** · FR : Étude expérimentale · AR : الدراسة التجريبية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : study design ; a study design ; the patient with study design
  - modèle : The patient in bed 3 has a study design.
- **q2.** What does the patient in bed 5 have?
  - accepté : observational study ; a problem with the observational study
  - modèle : He has a problem with the observational study.
- **q3.** Does the patient in bed 5 have study design?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the study design ; he doesn't
  - modèle : No, he does not have a problem with the study design.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS plan d'étude — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : experimental study ; a problem with the experimental study
  - modèle : He has a problem with the experimental study.
- **Tt2.** Does the patient in bed 9 have observational study?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the observational study ; he doesn't
  - modèle : No, he does not have a problem with the observational study.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS étude observationnelle.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a study design and an observational study. (have)` → have · modèle : The patients have a study design and an observational study.
- `The patient in bed 5 … not have a study design. (do)` → does · modèle : The patient in bed 5 does not have a study design.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the study design. The patient in bed 5 has a problem with the observational study.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Étude expérimentale, le lit 9 a Plan d'étude et n'a pas Étude observationnelle.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the experimental study. The patient in bed 9 has a problem with the study design.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
