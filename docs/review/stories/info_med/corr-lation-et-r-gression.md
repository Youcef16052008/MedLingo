# Relecture — Corrélation et Régression (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-002` — Le cas de Youssef : Corrélation de Pearson anormal(e) / _Youssef: the pearson correlation is not normal_

**Objectif :** Comprendre des résultats : Corrélation normal(e), Corrélation de Pearson anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The correlation is normal.”
   - FR : Le médecin dit : « Corrélation est normal(e). »
   - AR : يقول الطبيب: «الارتباط طبيعي/طبيعية.»
5. **EN** “But the pearson correlation is not normal.”
   - FR : « Mais Corrélation de Pearson n'est pas normal(e). »
   - AR : «لكن ارتباط بيرسون غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the pearson correlation normal?”
   - FR : Lina demande : « Corrélation de Pearson est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل ارتباط بيرسون طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:correlation:v1` — **a correlation** · FR : Corrélation · AR : الارتباط
- `gen:correlation-coefficient:v1` — **a correlation coefficient** · FR : Coefficient de corrélation (r) · AR : معامل الارتباط
- `gen:pearson-correlation:v1` — **a pearson correlation** · FR : Corrélation de Pearson · AR : ارتباط بيرسون

### Questions (acceptées / modèle / hint)
- **q1.** Is the correlation normal?
  - accepté : yes ; yes it is ; the correlation is normal ; it is normal
  - modèle : Yes, the correlation is normal.
- **q2.** Is the pearson correlation normal?
  - accepté : no ; no it is not ; no it isn't ; the pearson correlation is not normal ; it is not normal ; it isn't normal
  - modèle : No, the pearson correlation is not normal.
  - hint (si erreur) — FR : Attention : Corrélation de Pearson n'est PAS normal(e).
- **q3.** Which one is not normal: the correlation or the pearson correlation?
  - accepté : pearson correlation ; the pearson correlation
  - modèle : The pearson correlation is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the correlation coefficient normal?
  - accepté : yes ; yes it is ; the correlation coefficient is normal
  - modèle : Yes, the correlation coefficient is normal.
- **Tt2.** Is the correlation normal?
  - accepté : no ; no it is not ; no it isn't ; the correlation is not normal ; it is not normal
  - modèle : No, the correlation is not normal.
  - hint (si erreur) — FR : Attention : Corrélation n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The correlation … normal. (be)` → is · modèle : The correlation is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The correlation is… / The pearson correlation is not… — modèle : *The correlation is normal. The pearson correlation is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Coefficient de corrélation (r) normal(e), mais Corrélation anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The correlation coefficient is normal. The correlation is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Corrélation et Coefficient de corrélation (r) à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient.
   - FR : Farid est un·e patient·e.
   - AR : Farid مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check correlation.”
   - FR : « D'abord, nous vérifions Corrélation. »
   - AR : «أولًا، نتحقق من الارتباط.»
5. **EN** “Then, we check correlation coefficient.”
   - FR : « Ensuite, nous vérifions Coefficient de corrélation (r). »
   - AR : «ثم نتحقق من معامل الارتباط.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:correlation:v1` — **a correlation** · FR : Corrélation · AR : الارتباط
- `gen:correlation-coefficient:v1` — **a correlation coefficient** · FR : Coefficient de corrélation (r) · AR : معامل الارتباط
- `gen:pearson-correlation:v1` — **a pearson correlation** · FR : Corrélation de Pearson · AR : ارتباط بيرسون

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : correlation ; we check correlation ; correlation first ; the doctor checks correlation first
  - modèle : First, the doctor checks correlation.
- **q2.** What does the doctor check next?
  - accepté : correlation coefficient ; then we check correlation coefficient ; correlation coefficient next ; the doctor checks correlation coefficient next
  - modèle : Then, the doctor checks correlation coefficient.
- **q3.** Does the doctor check correlation coefficient before correlation?
  - accepté : no ; no he does not ; no he doesn't ; he checks correlation first
  - modèle : No, he checks correlation first, then correlation coefficient.
  - hint (si erreur) — FR : Attention : Corrélation vient D'ABORD, ensuite Coefficient de corrélation (r).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : pearson correlation ; pearson correlation first ; he checks pearson correlation first
  - modèle : First, the doctor checks pearson correlation.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check correlation. Then, we check correlation coefficient.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Corrélation de Pearson, ensuite Corrélation, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check pearson correlation. Then, we check correlation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Corrélation / Coefficient de corrélation (r), lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the correlation.
   - FR : Le patient du lit 3 a Corrélation.
   - AR : مريض السرير ٣ عنده الارتباط.
4. **EN** The patient in bed 5 has a problem with the correlation coefficient.
   - FR : Le patient du lit 5 a Coefficient de corrélation (r).
   - AR : مريض السرير ٥ عنده معامل الارتباط.
5. **EN** The patient in bed 5 does not have a problem with the correlation.
   - FR : Le patient du lit 5 n'a pas de Corrélation.
   - AR : مريض السرير ٥ ليس عنده الارتباط.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:correlation:v1` — **a correlation** · FR : Corrélation · AR : الارتباط
- `gen:correlation-coefficient:v1` — **a correlation coefficient** · FR : Coefficient de corrélation (r) · AR : معامل الارتباط
- `gen:pearson-correlation:v1` — **a pearson correlation** · FR : Corrélation de Pearson · AR : ارتباط بيرسون

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : correlation ; a correlation ; the patient with correlation
  - modèle : The patient in bed 3 has a correlation.
- **q2.** What does the patient in bed 5 have?
  - accepté : correlation coefficient ; a problem with the correlation coefficient
  - modèle : He has a problem with the correlation coefficient.
- **q3.** Does the patient in bed 5 have correlation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the correlation ; he doesn't
  - modèle : No, he does not have a problem with the correlation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS corrélation — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : pearson correlation ; a problem with the pearson correlation
  - modèle : He has a problem with the pearson correlation.
- **Tt2.** Does the patient in bed 9 have correlation coefficient?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the correlation coefficient ; he doesn't
  - modèle : No, he does not have a problem with the correlation coefficient.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS coefficient de corrélation (r).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a correlation and a correlation coefficient. (have)` → have · modèle : The patients have a correlation and a correlation coefficient.
- `The patient in bed 5 … not have a correlation. (do)` → does · modèle : The patient in bed 5 does not have a correlation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the correlation. The patient in bed 5 has a problem with the correlation coefficient.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Corrélation de Pearson, le lit 9 a Corrélation et n'a pas Coefficient de corrélation (r).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the pearson correlation. The patient in bed 9 has a problem with the correlation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
