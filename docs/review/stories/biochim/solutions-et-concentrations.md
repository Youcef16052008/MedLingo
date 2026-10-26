# Relecture — Solutions et Concentrations (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-020` — Le cas de Bilal : Solution saturée anormal(e) / _Bilal: the saturated solution is not normal_

**Objectif :** Comprendre des résultats : Solution normal(e), Solution saturée anormal(e).
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
4. **EN** The doctor says, “The solution is normal.”
   - FR : Le médecin dit : « Solution est normal(e). »
   - AR : يقول الطبيب: «المحلول طبيعي/طبيعية.»
5. **EN** “But the saturated solution is not normal.”
   - FR : « Mais Solution saturée n'est pas normal(e). »
   - AR : «لكن محلول مشبع غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the saturated solution normal?”
   - FR : Lina demande : « Solution saturée est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل محلول مشبع طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:solution:v1` — **a solution** · FR : Solution · AR : المحلول
- `gen:solubility:v1` — **a solubility** · FR : Solubilité · AR : الذوبانية (قابلية الذوبان)
- `gen:saturated-solution:v1` — **a saturated solution** · FR : Solution saturée · AR : محلول مشبع

### Questions (acceptées / modèle / hint)
- **q1.** Is the solution normal?
  - accepté : yes ; yes it is ; the solution is normal ; it is normal
  - modèle : Yes, the solution is normal.
- **q2.** Is the saturated solution normal?
  - accepté : no ; no it is not ; no it isn't ; the saturated solution is not normal ; it is not normal ; it isn't normal
  - modèle : No, the saturated solution is not normal.
  - hint (si erreur) — FR : Attention : Solution saturée n'est PAS normal(e).
- **q3.** Which one is not normal: the solution or the saturated solution?
  - accepté : saturated solution ; the saturated solution
  - modèle : The saturated solution is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the solubility normal?
  - accepté : yes ; yes it is ; the solubility is normal
  - modèle : Yes, the solubility is normal.
- **Tt2.** Is the solution normal?
  - accepté : no ; no it is not ; no it isn't ; the solution is not normal ; it is not normal
  - modèle : No, the solution is not normal.
  - hint (si erreur) — FR : Attention : Solution n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The solution … normal. (be)` → is · modèle : The solution is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The solution is… / The saturated solution is not… — modèle : *The solution is normal. The saturated solution is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Solubilité normal(e), mais Solution anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The solubility is normal. The solution is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-020` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Solution et Solubilité à un patient.
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
4. **EN** “First, we check solution.”
   - FR : « D'abord, nous vérifions Solution. »
   - AR : «أولًا، نتحقق من المحلول.»
5. **EN** “Then, we check solubility.”
   - FR : « Ensuite, nous vérifions Solubilité. »
   - AR : «ثم نتحقق من الذوبانية (قابلية الذوبان).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:solution:v1` — **a solution** · FR : Solution · AR : المحلول
- `gen:solubility:v1` — **a solubility** · FR : Solubilité · AR : الذوبانية (قابلية الذوبان)
- `gen:saturated-solution:v1` — **a saturated solution** · FR : Solution saturée · AR : محلول مشبع

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : solution ; we check solution ; solution first ; the doctor checks solution first
  - modèle : First, the doctor checks solution.
- **q2.** What does the doctor check next?
  - accepté : solubility ; then we check solubility ; solubility next ; the doctor checks solubility next
  - modèle : Then, the doctor checks solubility.
- **q3.** Does the doctor check solubility before solution?
  - accepté : no ; no he does not ; no he doesn't ; he checks solution first
  - modèle : No, he checks solution first, then solubility.
  - hint (si erreur) — FR : Attention : Solution vient D'ABORD, ensuite Solubilité.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : saturated solution ; saturated solution first ; he checks saturated solution first
  - modèle : First, the doctor checks saturated solution.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check solution. Then, we check solubility.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Solution saturée, ensuite Solution, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check saturated solution. Then, we check solution.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-020` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Solution / Solubilité, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the solution.
   - FR : Le patient du lit 3 a Solution.
   - AR : مريض السرير ٣ عنده المحلول.
4. **EN** The patient in bed 5 has a problem with the solubility.
   - FR : Le patient du lit 5 a Solubilité.
   - AR : مريض السرير ٥ عنده الذوبانية (قابلية الذوبان).
5. **EN** The patient in bed 5 does not have a problem with the solution.
   - FR : Le patient du lit 5 n'a pas de Solution.
   - AR : مريض السرير ٥ ليس عنده المحلول.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:solution:v1` — **a solution** · FR : Solution · AR : المحلول
- `gen:solubility:v1` — **a solubility** · FR : Solubilité · AR : الذوبانية (قابلية الذوبان)
- `gen:saturated-solution:v1` — **a saturated solution** · FR : Solution saturée · AR : محلول مشبع

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : solution ; a solution ; the patient with solution
  - modèle : The patient in bed 3 has a solution.
- **q2.** What does the patient in bed 5 have?
  - accepté : solubility ; a problem with the solubility
  - modèle : He has a problem with the solubility.
- **q3.** Does the patient in bed 5 have solution?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the solution ; he doesn't
  - modèle : No, he does not have a problem with the solution.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS solution — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : saturated solution ; a problem with the saturated solution
  - modèle : He has a problem with the saturated solution.
- **Tt2.** Does the patient in bed 9 have solubility?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the solubility ; he doesn't
  - modèle : No, he does not have a problem with the solubility.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS solubilité.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a solution and a solubility. (have)` → have · modèle : The patients have a solution and a solubility.
- `The patient in bed 5 … not have a solution. (do)` → does · modèle : The patient in bed 5 does not have a solution.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the solution. The patient in bed 5 has a problem with the solubility.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Solution saturée, le lit 9 a Solution et n'a pas Solubilité.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the saturated solution. The patient in bed 9 has a problem with the solution.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
