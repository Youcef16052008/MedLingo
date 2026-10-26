# Relecture — Génétique Médicale (genet)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `genet-case-005` — Le cas de Meriem : Hérédité liée à l'X anormal(e) / _Meriem: the x-linked inheritance is not normal_

**Objectif :** Comprendre des résultats : Autosomique dominant normal(e), Hérédité liée à l'X anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The autosomal dominant is normal.”
   - FR : Le médecin dit : « Autosomique dominant est normal(e). »
   - AR : يقول الطبيب: «جسمي سائد طبيعي/طبيعية.»
5. **EN** “But the x-linked inheritance is not normal.”
   - FR : « Mais Hérédité liée à l'X n'est pas normal(e). »
   - AR : «لكن الوراثة المرتبطة بالكروموسوم X غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the x-linked inheritance normal?”
   - FR : Amine demande : « Hérédité liée à l'X est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الوراثة المرتبطة بالكروموسوم X طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:autosomal-dominant:v1` — **an autosomal dominant** · FR : Autosomique dominant · AR : جسمي سائد
- `gen:autosomal-recessive:v1` — **an autosomal recessive** · FR : Autosomique récessif · AR : جسمي متنحٍ
- `gen:x-linked-inheritance:v1` — **a x-linked inheritance** · FR : Hérédité liée à l'X · AR : الوراثة المرتبطة بالكروموسوم X

### Questions (acceptées / modèle / hint)
- **q1.** Is the autosomal dominant normal?
  - accepté : yes ; yes it is ; the autosomal dominant is normal ; it is normal
  - modèle : Yes, the autosomal dominant is normal.
- **q2.** Is the x-linked inheritance normal?
  - accepté : no ; no it is not ; no it isn't ; the x-linked inheritance is not normal ; it is not normal ; it isn't normal
  - modèle : No, the x-linked inheritance is not normal.
  - hint (si erreur) — FR : Attention : Hérédité liée à l'X n'est PAS normal(e).
- **q3.** Which one is not normal: the autosomal dominant or the x-linked inheritance?
  - accepté : x-linked inheritance ; the x-linked inheritance
  - modèle : The x-linked inheritance is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the autosomal recessive normal?
  - accepté : yes ; yes it is ; the autosomal recessive is normal
  - modèle : Yes, the autosomal recessive is normal.
- **Tt2.** Is the autosomal dominant normal?
  - accepté : no ; no it is not ; no it isn't ; the autosomal dominant is not normal ; it is not normal
  - modèle : No, the autosomal dominant is not normal.
  - hint (si erreur) — FR : Attention : Autosomique dominant n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The autosomal dominant … normal. (be)` → is · modèle : The autosomal dominant is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The autosomal dominant is… / The x-linked inheritance is not… — modèle : *The autosomal dominant is normal. The x-linked inheritance is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Autosomique récessif normal(e), mais Autosomique dominant anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The autosomal recessive is normal. The autosomal dominant is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `genet-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Autosomique dominant et Autosomique récessif à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check autosomal dominant.”
   - FR : « D'abord, nous vérifions Autosomique dominant. »
   - AR : «أولًا، نتحقق من جسمي سائد.»
5. **EN** “Then, we check autosomal recessive.”
   - FR : « Ensuite, nous vérifions Autosomique récessif. »
   - AR : «ثم نتحقق من جسمي متنحٍ.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:autosomal-dominant:v1` — **an autosomal dominant** · FR : Autosomique dominant · AR : جسمي سائد
- `gen:autosomal-recessive:v1` — **an autosomal recessive** · FR : Autosomique récessif · AR : جسمي متنحٍ
- `gen:x-linked-inheritance:v1` — **a x-linked inheritance** · FR : Hérédité liée à l'X · AR : الوراثة المرتبطة بالكروموسوم X

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : autosomal dominant ; we check autosomal dominant ; autosomal dominant first ; the doctor checks autosomal dominant first
  - modèle : First, the doctor checks autosomal dominant.
- **q2.** What does the doctor check next?
  - accepté : autosomal recessive ; then we check autosomal recessive ; autosomal recessive next ; the doctor checks autosomal recessive next
  - modèle : Then, the doctor checks autosomal recessive.
- **q3.** Does the doctor check autosomal recessive before autosomal dominant?
  - accepté : no ; no he does not ; no he doesn't ; he checks autosomal dominant first
  - modèle : No, he checks autosomal dominant first, then autosomal recessive.
  - hint (si erreur) — FR : Attention : Autosomique dominant vient D'ABORD, ensuite Autosomique récessif.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : x-linked inheritance ; x-linked inheritance first ; he checks x-linked inheritance first
  - modèle : First, the doctor checks x-linked inheritance.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check autosomal dominant. Then, we check autosomal recessive.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Hérédité liée à l'X, ensuite Autosomique dominant, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check x-linked inheritance. Then, we check autosomal dominant.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `genet-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Autosomique dominant / Autosomique récessif, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the autosomal dominant.
   - FR : Le patient du lit 3 a Autosomique dominant.
   - AR : مريض السرير ٣ عنده جسمي سائد.
4. **EN** The patient in bed 5 has a problem with the autosomal recessive.
   - FR : Le patient du lit 5 a Autosomique récessif.
   - AR : مريض السرير ٥ عنده جسمي متنحٍ.
5. **EN** The patient in bed 5 does not have a problem with the autosomal dominant.
   - FR : Le patient du lit 5 n'a pas de Autosomique dominant.
   - AR : مريض السرير ٥ ليس عنده جسمي سائد.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:autosomal-dominant:v1` — **an autosomal dominant** · FR : Autosomique dominant · AR : جسمي سائد
- `gen:autosomal-recessive:v1` — **an autosomal recessive** · FR : Autosomique récessif · AR : جسمي متنحٍ
- `gen:x-linked-inheritance:v1` — **a x-linked inheritance** · FR : Hérédité liée à l'X · AR : الوراثة المرتبطة بالكروموسوم X

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : autosomal dominant ; an autosomal dominant ; the patient with autosomal dominant
  - modèle : The patient in bed 3 has an autosomal dominant.
- **q2.** What does the patient in bed 5 have?
  - accepté : autosomal recessive ; a problem with the autosomal recessive
  - modèle : He has a problem with the autosomal recessive.
- **q3.** Does the patient in bed 5 have autosomal dominant?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the autosomal dominant ; he doesn't
  - modèle : No, he does not have a problem with the autosomal dominant.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS autosomique dominant — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : x-linked inheritance ; a problem with the x-linked inheritance
  - modèle : He has a problem with the x-linked inheritance.
- **Tt2.** Does the patient in bed 9 have autosomal recessive?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the autosomal recessive ; he doesn't
  - modèle : No, he does not have a problem with the autosomal recessive.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS autosomique récessif.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an autosomal dominant and an autosomal recessive. (have)` → have · modèle : The patients have an autosomal dominant and an autosomal recessive.
- `The patient in bed 5 … not have an autosomal dominant. (do)` → does · modèle : The patient in bed 5 does not have an autosomal dominant.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the autosomal dominant. The patient in bed 5 has a problem with the autosomal recessive.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Hérédité liée à l'X, le lit 9 a Autosomique dominant et n'a pas Autosomique récessif.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the x-linked inheritance. The patient in bed 9 has a problem with the autosomal dominant.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
