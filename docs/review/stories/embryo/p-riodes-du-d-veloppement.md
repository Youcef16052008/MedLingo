# Relecture — Périodes du Développement (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-008` — Le cas de Bilal : Embryon anormal(e) / _Bilal: the embryo is not normal_

**Objectif :** Comprendre des résultats : Période embryonnaire normal(e), Embryon anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The embryonic period is normal.”
   - FR : Le médecin dit : « Période embryonnaire est normal(e). »
   - AR : يقول الطبيب: «الفترة الجنينية طبيعي/طبيعية.»
5. **EN** “But the embryo is not normal.”
   - FR : « Mais Embryon n'est pas normal(e). »
   - AR : «لكن الجنين (المضغة) غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the embryo normal?”
   - FR : Lina demande : « Embryon est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الجنين (المضغة) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:embryonic-period:v1` — **an embryonic period** · FR : Période embryonnaire · AR : الفترة الجنينية
- `gen:fetal-period:v1` — **a fetal period** · FR : Période fœtale · AR : الفترة الجنينية الكاملة
- `gen:embryo:v1` — **an embryo** · FR : Embryon · AR : الجنين (المضغة)

### Questions (acceptées / modèle / hint)
- **q1.** Is the embryonic period normal?
  - accepté : yes ; yes it is ; the embryonic period is normal ; it is normal
  - modèle : Yes, the embryonic period is normal.
- **q2.** Is the embryo normal?
  - accepté : no ; no it is not ; no it isn't ; the embryo is not normal ; it is not normal ; it isn't normal
  - modèle : No, the embryo is not normal.
  - hint (si erreur) — FR : Attention : Embryon n'est PAS normal(e).
- **q3.** Which one is not normal: the embryonic period or the embryo?
  - accepté : embryo ; the embryo
  - modèle : The embryo is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the fetal period normal?
  - accepté : yes ; yes it is ; the fetal period is normal
  - modèle : Yes, the fetal period is normal.
- **Tt2.** Is the embryonic period normal?
  - accepté : no ; no it is not ; no it isn't ; the embryonic period is not normal ; it is not normal
  - modèle : No, the embryonic period is not normal.
  - hint (si erreur) — FR : Attention : Période embryonnaire n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The embryonic period … normal. (be)` → is · modèle : The embryonic period is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The embryonic period is… / The embryo is not… — modèle : *The embryonic period is normal. The embryo is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Période fœtale normal(e), mais Période embryonnaire anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The fetal period is normal. The embryonic period is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Période embryonnaire et Période fœtale à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check embryonic period.”
   - FR : « D'abord, nous vérifions Période embryonnaire. »
   - AR : «أولًا، نتحقق من الفترة الجنينية.»
5. **EN** “Then, we check fetal period.”
   - FR : « Ensuite, nous vérifions Période fœtale. »
   - AR : «ثم نتحقق من الفترة الجنينية الكاملة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:embryonic-period:v1` — **an embryonic period** · FR : Période embryonnaire · AR : الفترة الجنينية
- `gen:fetal-period:v1` — **a fetal period** · FR : Période fœtale · AR : الفترة الجنينية الكاملة
- `gen:embryo:v1` — **an embryo** · FR : Embryon · AR : الجنين (المضغة)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : embryonic period ; we check embryonic period ; embryonic period first ; the doctor checks embryonic period first
  - modèle : First, the doctor checks embryonic period.
- **q2.** What does the doctor check next?
  - accepté : fetal period ; then we check fetal period ; fetal period next ; the doctor checks fetal period next
  - modèle : Then, the doctor checks fetal period.
- **q3.** Does the doctor check fetal period before embryonic period?
  - accepté : no ; no he does not ; no he doesn't ; he checks embryonic period first
  - modèle : No, he checks embryonic period first, then fetal period.
  - hint (si erreur) — FR : Attention : Période embryonnaire vient D'ABORD, ensuite Période fœtale.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : embryo ; embryo first ; he checks embryo first
  - modèle : First, the doctor checks embryo.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check embryonic period. Then, we check fetal period.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Embryon, ensuite Période embryonnaire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check embryo. Then, we check embryonic period.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Période embryonnaire / Période fœtale, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the embryonic period.
   - FR : Le patient du lit 3 a Période embryonnaire.
   - AR : مريض السرير ٣ عنده الفترة الجنينية.
4. **EN** The patient in bed 5 has a problem with the fetal period.
   - FR : Le patient du lit 5 a Période fœtale.
   - AR : مريض السرير ٥ عنده الفترة الجنينية الكاملة.
5. **EN** The patient in bed 5 does not have a problem with the embryonic period.
   - FR : Le patient du lit 5 n'a pas de Période embryonnaire.
   - AR : مريض السرير ٥ ليس عنده الفترة الجنينية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:embryonic-period:v1` — **an embryonic period** · FR : Période embryonnaire · AR : الفترة الجنينية
- `gen:fetal-period:v1` — **a fetal period** · FR : Période fœtale · AR : الفترة الجنينية الكاملة
- `gen:embryo:v1` — **an embryo** · FR : Embryon · AR : الجنين (المضغة)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : embryonic period ; an embryonic period ; the patient with embryonic period
  - modèle : The patient in bed 3 has an embryonic period.
- **q2.** What does the patient in bed 5 have?
  - accepté : fetal period ; a problem with the fetal period
  - modèle : He has a problem with the fetal period.
- **q3.** Does the patient in bed 5 have embryonic period?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the embryonic period ; he doesn't
  - modèle : No, he does not have a problem with the embryonic period.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS période embryonnaire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : embryo ; a problem with the embryo
  - modèle : He has a problem with the embryo.
- **Tt2.** Does the patient in bed 9 have fetal period?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the fetal period ; he doesn't
  - modèle : No, he does not have a problem with the fetal period.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS période fœtale.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an embryonic period and a fetal period. (have)` → have · modèle : The patients have an embryonic period and a fetal period.
- `The patient in bed 5 … not have an embryonic period. (do)` → does · modèle : The patient in bed 5 does not have an embryonic period.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the embryonic period. The patient in bed 5 has a problem with the fetal period.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Embryon, le lit 9 a Période embryonnaire et n'a pas Période fœtale.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the embryo. The patient in bed 9 has a problem with the embryonic period.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
