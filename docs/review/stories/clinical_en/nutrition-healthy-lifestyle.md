# Relecture — Nutrition & Healthy Lifestyle (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-024` — Le cas de Sara : Nutrition / _Sara: Nutrition_

**Objectif :** Comprendre un cas : nutrition présent, calorie absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Sara talks to Lina, a medical student.
   - FR : Sara parle avec Lina, étudiant·e en médecine.
   - AR : Sara يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a nutrition.
   - FR : Elle a Nutrition.
   - AR : عنده التغذية.
4. **EN** She does not have a calorie.
   - FR : Elle n'a pas de Calorie.
   - AR : ليس عنده السعرة الحرارية.
5. **EN** Lina asks, “Do you have a calorie?”
   - FR : Lina demande : « Avez-vous Calorie ? »
   - AR : يسأل Lina: «هل عندك السعرة الحرارية؟»
6. **EN** Sara says, “No, I do not.”
   - FR : Sara répond : « Non. »
   - AR : يجيب Sara: «لا.»
7. **EN** Lina writes “Nutrition” in the file.
   - FR : Lina écrit « Nutrition » dans le dossier.
   - AR : يكتب Lina «Nutrition» في الملف.

### Expressions ciblées
- `gen:nutrition:v1` — **a nutrition** · FR : Nutrition · AR : التغذية
- `gen:calorie:v1` — **a calorie** · FR : Calorie · AR : السعرة الحرارية
- `gen:carbohydrate:v1` — **a carbohydrate** · FR : Glucide · AR : الكربوهيدرات

### Questions (acceptées / modèle / hint)
- **q1.** Does Sara have nutrition?
  - accepté : yes ; yes she does ; yes she has a nutrition ; she has a nutrition
  - modèle : Yes, she has a nutrition.
- **q2.** Does she have calorie?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a calorie ; she doesn't
  - modèle : No, she does not have a calorie.
  - hint (si erreur) — FR : Attention : Sara n'a PAS calorie.
- **q3.** Does she have nutrition or calorie?
  - accepté : nutrition ; a nutrition ; she has a nutrition
  - modèle : She has a nutrition.
- **q4.** What does Sara have?
  - accepté : nutrition ; a nutrition ; she has a nutrition
  - modèle : She has a nutrition.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have carbohydrate?
  - accepté : yes ; yes he does ; he has a carbohydrate
  - modèle : Yes, he has a carbohydrate.
- **Tt2.** Does he have nutrition?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a nutrition ; he doesn't
  - modèle : No, he does not have a nutrition.
  - hint (si erreur) — FR : Attention : il n'a PAS nutrition.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a nutrition. (have)` → has · modèle : She has a nutrition.
- `They … not have a calorie. (do)` → do · modèle : They do not have a calorie.
- `I … a nutrition. (have)` → have · modèle : I have a nutrition.

### Productions
- **Reformulation :** Rapporte le cas de Sara en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a nutrition. She does not have a calorie.*
- **Transfert (contexte) :** Un autre patient a Glucide. Il n'a pas de Nutrition.
- Glossaire : Glucide : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a carbohydrate. He does not have a nutrition.*

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
3. **EN** The patient in bed 3 has a nutrition.
   - FR : Le patient du lit 3 a Nutrition.
   - AR : مريض السرير ٣ عنده التغذية.
4. **EN** The patient in bed 5 has a calorie.
   - FR : Le patient du lit 5 a Calorie.
   - AR : مريض السرير ٥ عنده السعرة الحرارية.
5. **EN** The patient in bed 5 does not have a nutrition.
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
  - accepté : calorie ; a calorie ; he has a calorie
  - modèle : He has a calorie.
- **q3.** Does the patient in bed 5 have nutrition?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a nutrition ; he doesn't
  - modèle : No, he does not have a nutrition.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS nutrition — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : carbohydrate ; a carbohydrate ; he has a carbohydrate
  - modèle : He has a carbohydrate.
- **Tt2.** Does the patient in bed 9 have calorie?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a calorie ; he doesn't
  - modèle : No, he does not have a calorie.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS calorie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a nutrition and a calorie. (have)` → have · modèle : The patients have a nutrition and a calorie.
- `The patient in bed 5 … not have a nutrition. (do)` → does · modèle : The patient in bed 5 does not have a nutrition.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a nutrition. The patient in bed 5 has a calorie.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Glucide, le lit 9 a Nutrition et n'a pas Calorie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a carbohydrate. The patient in bed 9 has a nutrition.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
