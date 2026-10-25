# Relecture — Nutrition & Healthy Lifestyle (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-024` — Le cas de Karim : Glucide anormal(e) / _Karim: the carbohydrate is not normal_

**Objectif :** Comprendre des résultats : Nutrition normal(e), Glucide anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The nutrition is normal.”
   - FR : Le médecin dit : « Nutrition est normal(e). »
   - AR : يقول الطبيب: «التغذية طبيعي/طبيعية.»
5. **EN** “But the carbohydrate is not normal.”
   - FR : « Mais Glucide n'est pas normal(e). »
   - AR : «لكن الكربوهيدرات غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the carbohydrate normal?”
   - FR : Lina demande : « Glucide est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الكربوهيدرات طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:nutrition:v1` — **a nutrition** · FR : Nutrition · AR : التغذية
- `gen:calorie:v1` — **a calorie** · FR : Calorie · AR : السعرة الحرارية
- `gen:carbohydrate:v1` — **a carbohydrate** · FR : Glucide · AR : الكربوهيدرات

### Questions (acceptées / modèle / hint)
- **q1.** Is the nutrition normal?
  - accepté : yes ; yes it is ; the nutrition is normal ; it is normal
  - modèle : Yes, the nutrition is normal.
- **q2.** Is the carbohydrate normal?
  - accepté : no ; no it is not ; no it isn't ; the carbohydrate is not normal ; it is not normal ; it isn't normal
  - modèle : No, the carbohydrate is not normal.
  - hint (si erreur) — FR : Attention : Glucide n'est PAS normal(e).
- **q3.** Which one is not normal: the nutrition or the carbohydrate?
  - accepté : carbohydrate ; the carbohydrate
  - modèle : The carbohydrate is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the calorie normal?
  - accepté : yes ; yes it is ; the calorie is normal
  - modèle : Yes, the calorie is normal.
- **Tt2.** Is the nutrition normal?
  - accepté : no ; no it is not ; no it isn't ; the nutrition is not normal ; it is not normal
  - modèle : No, the nutrition is not normal.
  - hint (si erreur) — FR : Attention : Nutrition n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The nutrition … normal. (be)` → is · modèle : The nutrition is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The nutrition is… / The carbohydrate is not… — modèle : *The nutrition is normal. The carbohydrate is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Calorie normal(e), mais Nutrition anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The calorie is normal. The nutrition is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-024` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Nutrition et Calorie à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check nutrition.”
   - FR : « D'abord, nous vérifions Nutrition. »
   - AR : «أولًا، نتحقق من التغذية.»
5. **EN** “Then, we check calorie.”
   - FR : « Ensuite, nous vérifions Calorie. »
   - AR : «ثم نتحقق من السعرة الحرارية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:nutrition:v1` — **a nutrition** · FR : Nutrition · AR : التغذية
- `gen:calorie:v1` — **a calorie** · FR : Calorie · AR : السعرة الحرارية
- `gen:carbohydrate:v1` — **a carbohydrate** · FR : Glucide · AR : الكربوهيدرات

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : nutrition ; we check nutrition ; nutrition first ; the doctor checks nutrition first
  - modèle : First, the doctor checks nutrition.
- **q2.** What does the doctor check next?
  - accepté : calorie ; then we check calorie ; calorie next ; the doctor checks calorie next
  - modèle : Then, the doctor checks calorie.
- **q3.** Does the doctor check calorie before nutrition?
  - accepté : no ; no he does not ; no he doesn't ; he checks nutrition first
  - modèle : No, he checks nutrition first, then calorie.
  - hint (si erreur) — FR : Attention : Nutrition vient D'ABORD, ensuite Calorie.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : carbohydrate ; carbohydrate first ; he checks carbohydrate first
  - modèle : First, the doctor checks carbohydrate.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check nutrition. Then, we check calorie.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Glucide, ensuite Nutrition, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check carbohydrate. Then, we check nutrition.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-024` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Nutrition / Calorie, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the nutrition.
   - FR : Le patient du lit 3 a Nutrition.
   - AR : مريض السرير ٣ عنده التغذية.
4. **EN** The patient in bed 5 has a problem with the calorie.
   - FR : Le patient du lit 5 a Calorie.
   - AR : مريض السرير ٥ عنده السعرة الحرارية.
5. **EN** The patient in bed 5 does not have a problem with the nutrition.
   - FR : Le patient du lit 5 n'a pas de Nutrition.
   - AR : مريض السرير ٥ ليس عنده التغذية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:nutrition:v1` — **a nutrition** · FR : Nutrition · AR : التغذية
- `gen:calorie:v1` — **a calorie** · FR : Calorie · AR : السعرة الحرارية
- `gen:carbohydrate:v1` — **a carbohydrate** · FR : Glucide · AR : الكربوهيدرات

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : nutrition ; a nutrition ; the patient with nutrition
  - modèle : The patient in bed 3 has a nutrition.
- **q2.** What does the patient in bed 5 have?
  - accepté : calorie ; a problem with the calorie
  - modèle : He has a problem with the calorie.
- **q3.** Does the patient in bed 5 have nutrition?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the nutrition ; he doesn't
  - modèle : No, he does not have a problem with the nutrition.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS nutrition — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : carbohydrate ; a problem with the carbohydrate
  - modèle : He has a problem with the carbohydrate.
- **Tt2.** Does the patient in bed 9 have calorie?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the calorie ; he doesn't
  - modèle : No, he does not have a problem with the calorie.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS calorie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a nutrition and a calorie. (have)` → have · modèle : The patients have a nutrition and a calorie.
- `The patient in bed 5 … not have a nutrition. (do)` → does · modèle : The patient in bed 5 does not have a nutrition.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the nutrition. The patient in bed 5 has a problem with the calorie.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Glucide, le lit 9 a Nutrition et n'a pas Calorie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the carbohydrate. The patient in bed 9 has a problem with the nutrition.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
