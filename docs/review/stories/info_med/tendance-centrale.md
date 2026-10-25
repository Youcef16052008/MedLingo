# Relecture — Tendance Centrale (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-009` — Le cas de Sara : Moyenne pondérée anormal(e) / _Sara: the weighted mean is not normal_

**Objectif :** Comprendre des résultats : Tendance centrale normal(e), Moyenne pondérée anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The central tendency is normal.”
   - FR : Le médecin dit : « Tendance centrale est normal(e). »
   - AR : يقول الطبيب: «النزعة المركزية طبيعي/طبيعية.»
5. **EN** “But the weighted mean is not normal.”
   - FR : « Mais Moyenne pondérée n'est pas normal(e). »
   - AR : «لكن المتوسط المرجح غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the weighted mean normal?”
   - FR : Amine demande : « Moyenne pondérée est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل المتوسط المرجح طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:central-tendency:v1` — **a central tendency** · FR : Tendance centrale · AR : النزعة المركزية
- `gen:mean:v1` — **a mean** · FR : Moyenne (Moyenne arithmétique) · AR : المتوسط (الوسط الحسابي)
- `gen:weighted-mean:v1` — **a weighted mean** · FR : Moyenne pondérée · AR : المتوسط المرجح

### Questions (acceptées / modèle / hint)
- **q1.** Is the central tendency normal?
  - accepté : yes ; yes it is ; the central tendency is normal ; it is normal
  - modèle : Yes, the central tendency is normal.
- **q2.** Is the weighted mean normal?
  - accepté : no ; no it is not ; no it isn't ; the weighted mean is not normal ; it is not normal ; it isn't normal
  - modèle : No, the weighted mean is not normal.
  - hint (si erreur) — FR : Attention : Moyenne pondérée n'est PAS normal(e).
- **q3.** Which one is not normal: the central tendency or the weighted mean?
  - accepté : weighted mean ; the weighted mean
  - modèle : The weighted mean is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the mean normal?
  - accepté : yes ; yes it is ; the mean is normal
  - modèle : Yes, the mean is normal.
- **Tt2.** Is the central tendency normal?
  - accepté : no ; no it is not ; no it isn't ; the central tendency is not normal ; it is not normal
  - modèle : No, the central tendency is not normal.
  - hint (si erreur) — FR : Attention : Tendance centrale n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The central tendency … normal. (be)` → is · modèle : The central tendency is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The central tendency is… / The weighted mean is not… — modèle : *The central tendency is normal. The weighted mean is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Moyenne (Moyenne arithmétique) normal(e), mais Tendance centrale anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The mean is normal. The central tendency is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-009` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Tendance centrale et Moyenne (Moyenne arithmétique) à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check central tendency.”
   - FR : « D'abord, nous vérifions Tendance centrale. »
   - AR : «أولًا، نتحقق من النزعة المركزية.»
5. **EN** “Then, we check mean.”
   - FR : « Ensuite, nous vérifions Moyenne (Moyenne arithmétique). »
   - AR : «ثم نتحقق من المتوسط (الوسط الحسابي).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:central-tendency:v1` — **a central tendency** · FR : Tendance centrale · AR : النزعة المركزية
- `gen:mean:v1` — **a mean** · FR : Moyenne (Moyenne arithmétique) · AR : المتوسط (الوسط الحسابي)
- `gen:weighted-mean:v1` — **a weighted mean** · FR : Moyenne pondérée · AR : المتوسط المرجح

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : central tendency ; we check central tendency ; central tendency first ; the doctor checks central tendency first
  - modèle : First, the doctor checks central tendency.
- **q2.** What does the doctor check next?
  - accepté : mean ; then we check mean ; mean next ; the doctor checks mean next
  - modèle : Then, the doctor checks mean.
- **q3.** Does the doctor check mean before central tendency?
  - accepté : no ; no he does not ; no he doesn't ; he checks central tendency first
  - modèle : No, he checks central tendency first, then mean.
  - hint (si erreur) — FR : Attention : Tendance centrale vient D'ABORD, ensuite Moyenne (Moyenne arithmétique).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : weighted mean ; weighted mean first ; he checks weighted mean first
  - modèle : First, the doctor checks weighted mean.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check central tendency. Then, we check mean.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Moyenne pondérée, ensuite Tendance centrale, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check weighted mean. Then, we check central tendency.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-009` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Tendance centrale / Moyenne (Moyenne arithmétique), lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the central tendency.
   - FR : Le patient du lit 3 a Tendance centrale.
   - AR : مريض السرير ٣ عنده النزعة المركزية.
4. **EN** The patient in bed 5 has a problem with the mean.
   - FR : Le patient du lit 5 a Moyenne (Moyenne arithmétique).
   - AR : مريض السرير ٥ عنده المتوسط (الوسط الحسابي).
5. **EN** The patient in bed 5 does not have a problem with the central tendency.
   - FR : Le patient du lit 5 n'a pas de Tendance centrale.
   - AR : مريض السرير ٥ ليس عنده النزعة المركزية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:central-tendency:v1` — **a central tendency** · FR : Tendance centrale · AR : النزعة المركزية
- `gen:mean:v1` — **a mean** · FR : Moyenne (Moyenne arithmétique) · AR : المتوسط (الوسط الحسابي)
- `gen:weighted-mean:v1` — **a weighted mean** · FR : Moyenne pondérée · AR : المتوسط المرجح

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : central tendency ; a central tendency ; the patient with central tendency
  - modèle : The patient in bed 3 has a central tendency.
- **q2.** What does the patient in bed 5 have?
  - accepté : mean ; a problem with the mean
  - modèle : He has a problem with the mean.
- **q3.** Does the patient in bed 5 have central tendency?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the central tendency ; he doesn't
  - modèle : No, he does not have a problem with the central tendency.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS tendance centrale — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : weighted mean ; a problem with the weighted mean
  - modèle : He has a problem with the weighted mean.
- **Tt2.** Does the patient in bed 9 have mean?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the mean ; he doesn't
  - modèle : No, he does not have a problem with the mean.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS moyenne (moyenne arithmétique).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a central tendency and a mean. (have)` → have · modèle : The patients have a central tendency and a mean.
- `The patient in bed 5 … not have a central tendency. (do)` → does · modèle : The patient in bed 5 does not have a central tendency.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the central tendency. The patient in bed 5 has a problem with the mean.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Moyenne pondérée, le lit 9 a Tendance centrale et n'a pas Moyenne (Moyenne arithmétique).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the weighted mean. The patient in bed 9 has a problem with the central tendency.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
