# Relecture — Variables et Types (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-013` — Le cas de Nadia : Variable dépendante anormal(e) / _Nadia: the dependent variable is not normal_

**Objectif :** Comprendre des résultats : Variable normal(e), Variable dépendante anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The variable is normal.”
   - FR : Le médecin dit : « Variable est normal(e). »
   - AR : يقول الطبيب: «المتغير طبيعي/طبيعية.»
5. **EN** “But the dependent variable is not normal.”
   - FR : « Mais Variable dépendante n'est pas normal(e). »
   - AR : «لكن المتغير التابع غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the dependent variable normal?”
   - FR : Amine demande : « Variable dépendante est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل المتغير التابع طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:variable:v1` — **a variable** · FR : Variable · AR : المتغير
- `gen:independent-variable:v1` — **an independent variable** · FR : Variable indépendante · AR : المتغير المستقل
- `gen:dependent-variable:v1` — **a dependent variable** · FR : Variable dépendante · AR : المتغير التابع

### Questions (acceptées / modèle / hint)
- **q1.** Is the variable normal?
  - accepté : yes ; yes it is ; the variable is normal ; it is normal
  - modèle : Yes, the variable is normal.
- **q2.** Is the dependent variable normal?
  - accepté : no ; no it is not ; no it isn't ; the dependent variable is not normal ; it is not normal ; it isn't normal
  - modèle : No, the dependent variable is not normal.
  - hint (si erreur) — FR : Attention : Variable dépendante n'est PAS normal(e).
- **q3.** Which one is not normal: the variable or the dependent variable?
  - accepté : dependent variable ; the dependent variable
  - modèle : The dependent variable is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the independent variable normal?
  - accepté : yes ; yes it is ; the independent variable is normal
  - modèle : Yes, the independent variable is normal.
- **Tt2.** Is the variable normal?
  - accepté : no ; no it is not ; no it isn't ; the variable is not normal ; it is not normal
  - modèle : No, the variable is not normal.
  - hint (si erreur) — FR : Attention : Variable n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The variable … normal. (be)` → is · modèle : The variable is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The variable is… / The dependent variable is not… — modèle : *The variable is normal. The dependent variable is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Variable indépendante normal(e), mais Variable anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The independent variable is normal. The variable is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-013` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Variable et Variable indépendante à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient.
   - FR : Amel est un·e patient·e.
   - AR : Amel مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check variable.”
   - FR : « D'abord, nous vérifions Variable. »
   - AR : «أولًا، نتحقق من المتغير.»
5. **EN** “Then, we check independent variable.”
   - FR : « Ensuite, nous vérifions Variable indépendante. »
   - AR : «ثم نتحقق من المتغير المستقل.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:variable:v1` — **a variable** · FR : Variable · AR : المتغير
- `gen:independent-variable:v1` — **an independent variable** · FR : Variable indépendante · AR : المتغير المستقل
- `gen:dependent-variable:v1` — **a dependent variable** · FR : Variable dépendante · AR : المتغير التابع

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : variable ; we check variable ; variable first ; the doctor checks variable first
  - modèle : First, the doctor checks variable.
- **q2.** What does the doctor check next?
  - accepté : independent variable ; then we check independent variable ; independent variable next ; the doctor checks independent variable next
  - modèle : Then, the doctor checks independent variable.
- **q3.** Does the doctor check independent variable before variable?
  - accepté : no ; no he does not ; no he doesn't ; he checks variable first
  - modèle : No, he checks variable first, then independent variable.
  - hint (si erreur) — FR : Attention : Variable vient D'ABORD, ensuite Variable indépendante.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : dependent variable ; dependent variable first ; he checks dependent variable first
  - modèle : First, the doctor checks dependent variable.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check variable. Then, we check independent variable.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Variable dépendante, ensuite Variable, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check dependent variable. Then, we check variable.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-013` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Variable / Variable indépendante, lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the variable.
   - FR : Le patient du lit 3 a Variable.
   - AR : مريض السرير ٣ عنده المتغير.
4. **EN** The patient in bed 5 has a problem with the independent variable.
   - FR : Le patient du lit 5 a Variable indépendante.
   - AR : مريض السرير ٥ عنده المتغير المستقل.
5. **EN** The patient in bed 5 does not have a problem with the variable.
   - FR : Le patient du lit 5 n'a pas de Variable.
   - AR : مريض السرير ٥ ليس عنده المتغير.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:variable:v1` — **a variable** · FR : Variable · AR : المتغير
- `gen:independent-variable:v1` — **an independent variable** · FR : Variable indépendante · AR : المتغير المستقل
- `gen:dependent-variable:v1` — **a dependent variable** · FR : Variable dépendante · AR : المتغير التابع

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : variable ; a variable ; the patient with variable
  - modèle : The patient in bed 3 has a variable.
- **q2.** What does the patient in bed 5 have?
  - accepté : independent variable ; a problem with the independent variable
  - modèle : He has a problem with the independent variable.
- **q3.** Does the patient in bed 5 have variable?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the variable ; he doesn't
  - modèle : No, he does not have a problem with the variable.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS variable — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : dependent variable ; a problem with the dependent variable
  - modèle : He has a problem with the dependent variable.
- **Tt2.** Does the patient in bed 9 have independent variable?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the independent variable ; he doesn't
  - modèle : No, he does not have a problem with the independent variable.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS variable indépendante.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a variable and an independent variable. (have)` → have · modèle : The patients have a variable and an independent variable.
- `The patient in bed 5 … not have a variable. (do)` → does · modèle : The patient in bed 5 does not have a variable.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the variable. The patient in bed 5 has a problem with the independent variable.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Variable dépendante, le lit 9 a Variable et n'a pas Variable indépendante.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the dependent variable. The patient in bed 9 has a problem with the variable.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
