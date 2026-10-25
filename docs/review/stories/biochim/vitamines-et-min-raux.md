# Relecture — Vitamines et Minéraux (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-023` — Le cas de Leila : Vitamine liposoluble anormal(e) / _Leila: the fat-soluble vitamin is not normal_

**Objectif :** Comprendre des résultats : Vitamine normal(e), Vitamine liposoluble anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The vitamin is normal.”
   - FR : Le médecin dit : « Vitamine est normal(e). »
   - AR : يقول الطبيب: «الفيتامين طبيعي/طبيعية.»
5. **EN** “But the fat-soluble vitamin is not normal.”
   - FR : « Mais Vitamine liposoluble n'est pas normal(e). »
   - AR : «لكن فيتامين ذواب في الدهون غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the fat-soluble vitamin normal?”
   - FR : Amine demande : « Vitamine liposoluble est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل فيتامين ذواب في الدهون طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:vitamin:v1` — **a vitamin** · FR : Vitamine · AR : الفيتامين
- `gen:water-soluble-vitamin:v1` — **a water-soluble vitamin** · FR : Vitamine hydrosoluble · AR : فيتامين ذواب في الماء
- `gen:fat-soluble-vitamin:v1` — **a fat-soluble vitamin** · FR : Vitamine liposoluble · AR : فيتامين ذواب في الدهون

### Questions (acceptées / modèle / hint)
- **q1.** Is the vitamin normal?
  - accepté : yes ; yes it is ; the vitamin is normal ; it is normal
  - modèle : Yes, the vitamin is normal.
- **q2.** Is the fat-soluble vitamin normal?
  - accepté : no ; no it is not ; no it isn't ; the fat-soluble vitamin is not normal ; it is not normal ; it isn't normal
  - modèle : No, the fat-soluble vitamin is not normal.
  - hint (si erreur) — FR : Attention : Vitamine liposoluble n'est PAS normal(e).
- **q3.** Which one is not normal: the vitamin or the fat-soluble vitamin?
  - accepté : fat-soluble vitamin ; the fat-soluble vitamin
  - modèle : The fat-soluble vitamin is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the water-soluble vitamin normal?
  - accepté : yes ; yes it is ; the water-soluble vitamin is normal
  - modèle : Yes, the water-soluble vitamin is normal.
- **Tt2.** Is the vitamin normal?
  - accepté : no ; no it is not ; no it isn't ; the vitamin is not normal ; it is not normal
  - modèle : No, the vitamin is not normal.
  - hint (si erreur) — FR : Attention : Vitamine n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The vitamin … normal. (be)` → is · modèle : The vitamin is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Leila en deux phrases. Garde la négation. — amorces : The vitamin is… / The fat-soluble vitamin is not… — modèle : *The vitamin is normal. The fat-soluble vitamin is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Vitamine hydrosoluble normal(e), mais Vitamine anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The water-soluble vitamin is normal. The vitamin is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-023` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Vitamine et Vitamine hydrosoluble à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient.
   - FR : Nadia est un·e patient·e.
   - AR : Nadia مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check vitamin.”
   - FR : « D'abord, nous vérifions Vitamine. »
   - AR : «أولًا، نتحقق من الفيتامين.»
5. **EN** “Then, we check water-soluble vitamin.”
   - FR : « Ensuite, nous vérifions Vitamine hydrosoluble. »
   - AR : «ثم نتحقق من فيتامين ذواب في الماء.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:vitamin:v1` — **a vitamin** · FR : Vitamine · AR : الفيتامين
- `gen:water-soluble-vitamin:v1` — **a water-soluble vitamin** · FR : Vitamine hydrosoluble · AR : فيتامين ذواب في الماء
- `gen:fat-soluble-vitamin:v1` — **a fat-soluble vitamin** · FR : Vitamine liposoluble · AR : فيتامين ذواب في الدهون

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : vitamin ; we check vitamin ; vitamin first ; the doctor checks vitamin first
  - modèle : First, the doctor checks vitamin.
- **q2.** What does the doctor check next?
  - accepté : water-soluble vitamin ; then we check water-soluble vitamin ; water-soluble vitamin next ; the doctor checks water-soluble vitamin next
  - modèle : Then, the doctor checks water-soluble vitamin.
- **q3.** Does the doctor check water-soluble vitamin before vitamin?
  - accepté : no ; no he does not ; no he doesn't ; he checks vitamin first
  - modèle : No, he checks vitamin first, then water-soluble vitamin.
  - hint (si erreur) — FR : Attention : Vitamine vient D'ABORD, ensuite Vitamine hydrosoluble.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : fat-soluble vitamin ; fat-soluble vitamin first ; he checks fat-soluble vitamin first
  - modèle : First, the doctor checks fat-soluble vitamin.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check vitamin. Then, we check water-soluble vitamin.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Vitamine liposoluble, ensuite Vitamine, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check fat-soluble vitamin. Then, we check vitamin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-023` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Vitamine / Vitamine hydrosoluble, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the vitamin.
   - FR : Le patient du lit 3 a Vitamine.
   - AR : مريض السرير ٣ عنده الفيتامين.
4. **EN** The patient in bed 5 has a problem with the water-soluble vitamin.
   - FR : Le patient du lit 5 a Vitamine hydrosoluble.
   - AR : مريض السرير ٥ عنده فيتامين ذواب في الماء.
5. **EN** The patient in bed 5 does not have a problem with the vitamin.
   - FR : Le patient du lit 5 n'a pas de Vitamine.
   - AR : مريض السرير ٥ ليس عنده الفيتامين.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:vitamin:v1` — **a vitamin** · FR : Vitamine · AR : الفيتامين
- `gen:water-soluble-vitamin:v1` — **a water-soluble vitamin** · FR : Vitamine hydrosoluble · AR : فيتامين ذواب في الماء
- `gen:fat-soluble-vitamin:v1` — **a fat-soluble vitamin** · FR : Vitamine liposoluble · AR : فيتامين ذواب في الدهون

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : vitamin ; a vitamin ; the patient with vitamin
  - modèle : The patient in bed 3 has a vitamin.
- **q2.** What does the patient in bed 5 have?
  - accepté : water-soluble vitamin ; a problem with the water-soluble vitamin
  - modèle : He has a problem with the water-soluble vitamin.
- **q3.** Does the patient in bed 5 have vitamin?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the vitamin ; he doesn't
  - modèle : No, he does not have a problem with the vitamin.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS vitamine — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : fat-soluble vitamin ; a problem with the fat-soluble vitamin
  - modèle : He has a problem with the fat-soluble vitamin.
- **Tt2.** Does the patient in bed 9 have water-soluble vitamin?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the water-soluble vitamin ; he doesn't
  - modèle : No, he does not have a problem with the water-soluble vitamin.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS vitamine hydrosoluble.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a vitamin and a water-soluble vitamin. (have)` → have · modèle : The patients have a vitamin and a water-soluble vitamin.
- `The patient in bed 5 … not have a vitamin. (do)` → does · modèle : The patient in bed 5 does not have a vitamin.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the vitamin. The patient in bed 5 has a problem with the water-soluble vitamin.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Vitamine liposoluble, le lit 9 a Vitamine et n'a pas Vitamine hydrosoluble.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the fat-soluble vitamin. The patient in bed 9 has a problem with the vitamin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
