# Relecture — Estimation et IC (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-005` — Le cas de Meriem : Estimation par intervalle anormal(e) / _Meriem: the interval estimate is not normal_

**Objectif :** Comprendre des résultats : Estimation normal(e), Estimation par intervalle anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient at the clinic.
   - FR : Meriem est un·e patient·e à la clinique.
   - AR : Meriem مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The estimation is normal.”
   - FR : Le médecin dit : « Estimation est normal(e). »
   - AR : يقول الطبيب: «التقدير طبيعي/طبيعية.»
5. **EN** “But the interval estimate is not normal.”
   - FR : « Mais Estimation par intervalle n'est pas normal(e). »
   - AR : «لكن التقدير بالفترة غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the interval estimate normal?”
   - FR : Amine demande : « Estimation par intervalle est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل التقدير بالفترة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:estimation:v1` — **an estimation** · FR : Estimation · AR : التقدير
- `gen:point-estimate:v1` — **a point estimate** · FR : Estimation ponctuelle · AR : التقدير النقطي
- `gen:interval-estimate:v1` — **an interval estimate** · FR : Estimation par intervalle · AR : التقدير بالفترة

### Questions (acceptées / modèle / hint)
- **q1.** Is the estimation normal?
  - accepté : yes ; yes it is ; the estimation is normal ; it is normal
  - modèle : Yes, the estimation is normal.
- **q2.** Is the interval estimate normal?
  - accepté : no ; no it is not ; no it isn't ; the interval estimate is not normal ; it is not normal ; it isn't normal
  - modèle : No, the interval estimate is not normal.
  - hint (si erreur) — FR : Attention : Estimation par intervalle n'est PAS normal(e).
- **q3.** Which one is not normal: the estimation or the interval estimate?
  - accepté : interval estimate ; the interval estimate
  - modèle : The interval estimate is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the point estimate normal?
  - accepté : yes ; yes it is ; the point estimate is normal
  - modèle : Yes, the point estimate is normal.
- **Tt2.** Is the estimation normal?
  - accepté : no ; no it is not ; no it isn't ; the estimation is not normal ; it is not normal
  - modèle : No, the estimation is not normal.
  - hint (si erreur) — FR : Attention : Estimation n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The estimation … normal. (be)` → is · modèle : The estimation is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The estimation is… / The interval estimate is not… — modèle : *The estimation is normal. The interval estimate is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Estimation ponctuelle normal(e), mais Estimation anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The point estimate is normal. The estimation is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Estimation et Estimation ponctuelle à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check estimation.”
   - FR : « D'abord, nous vérifions Estimation. »
   - AR : «أولًا، نتحقق من التقدير.»
5. **EN** “Then, we check point estimate.”
   - FR : « Ensuite, nous vérifions Estimation ponctuelle. »
   - AR : «ثم نتحقق من التقدير النقطي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:estimation:v1` — **an estimation** · FR : Estimation · AR : التقدير
- `gen:point-estimate:v1` — **a point estimate** · FR : Estimation ponctuelle · AR : التقدير النقطي
- `gen:interval-estimate:v1` — **an interval estimate** · FR : Estimation par intervalle · AR : التقدير بالفترة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : estimation ; we check estimation ; estimation first ; the doctor checks estimation first
  - modèle : First, the doctor checks estimation.
- **q2.** What does the doctor check next?
  - accepté : point estimate ; then we check point estimate ; point estimate next ; the doctor checks point estimate next
  - modèle : Then, the doctor checks point estimate.
- **q3.** Does the doctor check point estimate before estimation?
  - accepté : no ; no he does not ; no he doesn't ; he checks estimation first
  - modèle : No, he checks estimation first, then point estimate.
  - hint (si erreur) — FR : Attention : Estimation vient D'ABORD, ensuite Estimation ponctuelle.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : interval estimate ; interval estimate first ; he checks interval estimate first
  - modèle : First, the doctor checks interval estimate.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check estimation. Then, we check point estimate.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Estimation par intervalle, ensuite Estimation, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check interval estimate. Then, we check estimation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Estimation / Estimation ponctuelle, lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the estimation.
   - FR : Le patient du lit 3 a Estimation.
   - AR : مريض السرير ٣ عنده التقدير.
4. **EN** The patient in bed 5 has a problem with the point estimate.
   - FR : Le patient du lit 5 a Estimation ponctuelle.
   - AR : مريض السرير ٥ عنده التقدير النقطي.
5. **EN** The patient in bed 5 does not have a problem with the estimation.
   - FR : Le patient du lit 5 n'a pas de Estimation.
   - AR : مريض السرير ٥ ليس عنده التقدير.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:estimation:v1` — **an estimation** · FR : Estimation · AR : التقدير
- `gen:point-estimate:v1` — **a point estimate** · FR : Estimation ponctuelle · AR : التقدير النقطي
- `gen:interval-estimate:v1` — **an interval estimate** · FR : Estimation par intervalle · AR : التقدير بالفترة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : estimation ; an estimation ; the patient with estimation
  - modèle : The patient in bed 3 has an estimation.
- **q2.** What does the patient in bed 5 have?
  - accepté : point estimate ; a problem with the point estimate
  - modèle : He has a problem with the point estimate.
- **q3.** Does the patient in bed 5 have estimation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the estimation ; he doesn't
  - modèle : No, he does not have a problem with the estimation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS estimation — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : interval estimate ; a problem with the interval estimate
  - modèle : He has a problem with the interval estimate.
- **Tt2.** Does the patient in bed 9 have point estimate?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the point estimate ; he doesn't
  - modèle : No, he does not have a problem with the point estimate.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS estimation ponctuelle.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an estimation and a point estimate. (have)` → have · modèle : The patients have an estimation and a point estimate.
- `The patient in bed 5 … not have an estimation. (do)` → does · modèle : The patient in bed 5 does not have an estimation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the estimation. The patient in bed 5 has a problem with the point estimate.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Estimation par intervalle, le lit 9 a Estimation et n'a pas Estimation ponctuelle.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the interval estimate. The patient in bed 9 has a problem with the estimation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
