# Relecture — Neurulation et Dérivés (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-005` — Le cas de Meriem : Repli neural anormal(e) / _Meriem: the neural fold is not normal_

**Objectif :** Comprendre des résultats : Neurulation normal(e), Repli neural anormal(e).
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
4. **EN** The doctor says, “The neurulation is normal.”
   - FR : Le médecin dit : « Neurulation est normal(e). »
   - AR : يقول الطبيب: «تشكل الأنبوب العصبي طبيعي/طبيعية.»
5. **EN** “But the neural fold is not normal.”
   - FR : « Mais Repli neural n'est pas normal(e). »
   - AR : «لكن الطية العصبية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the neural fold normal?”
   - FR : Amine demande : « Repli neural est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الطية العصبية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:neurulation:v1` — **a neurulation** · FR : Neurulation · AR : تشكل الأنبوب العصبي
- `gen:neural-plate:v1` — **a neural plate** · FR : Plaque neurale · AR : الصفيحة العصبية
- `gen:neural-fold:v1` — **a neural fold** · FR : Repli neural · AR : الطية العصبية

### Questions (acceptées / modèle / hint)
- **q1.** Is the neurulation normal?
  - accepté : yes ; yes it is ; the neurulation is normal ; it is normal
  - modèle : Yes, the neurulation is normal.
- **q2.** Is the neural fold normal?
  - accepté : no ; no it is not ; no it isn't ; the neural fold is not normal ; it is not normal ; it isn't normal
  - modèle : No, the neural fold is not normal.
  - hint (si erreur) — FR : Attention : Repli neural n'est PAS normal(e).
- **q3.** Which one is not normal: the neurulation or the neural fold?
  - accepté : neural fold ; the neural fold
  - modèle : The neural fold is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the neural plate normal?
  - accepté : yes ; yes it is ; the neural plate is normal
  - modèle : Yes, the neural plate is normal.
- **Tt2.** Is the neurulation normal?
  - accepté : no ; no it is not ; no it isn't ; the neurulation is not normal ; it is not normal
  - modèle : No, the neurulation is not normal.
  - hint (si erreur) — FR : Attention : Neurulation n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The neurulation … normal. (be)` → is · modèle : The neurulation is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The neurulation is… / The neural fold is not… — modèle : *The neurulation is normal. The neural fold is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Plaque neurale normal(e), mais Neurulation anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The neural plate is normal. The neurulation is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Neurulation et Plaque neurale à un patient.
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
4. **EN** “First, we check neurulation.”
   - FR : « D'abord, nous vérifions Neurulation. »
   - AR : «أولًا، نتحقق من تشكل الأنبوب العصبي.»
5. **EN** “Then, we check neural plate.”
   - FR : « Ensuite, nous vérifions Plaque neurale. »
   - AR : «ثم نتحقق من الصفيحة العصبية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:neurulation:v1` — **a neurulation** · FR : Neurulation · AR : تشكل الأنبوب العصبي
- `gen:neural-plate:v1` — **a neural plate** · FR : Plaque neurale · AR : الصفيحة العصبية
- `gen:neural-fold:v1` — **a neural fold** · FR : Repli neural · AR : الطية العصبية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : neurulation ; we check neurulation ; neurulation first ; the doctor checks neurulation first
  - modèle : First, the doctor checks neurulation.
- **q2.** What does the doctor check next?
  - accepté : neural plate ; then we check neural plate ; neural plate next ; the doctor checks neural plate next
  - modèle : Then, the doctor checks neural plate.
- **q3.** Does the doctor check neural plate before neurulation?
  - accepté : no ; no he does not ; no he doesn't ; he checks neurulation first
  - modèle : No, he checks neurulation first, then neural plate.
  - hint (si erreur) — FR : Attention : Neurulation vient D'ABORD, ensuite Plaque neurale.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : neural fold ; neural fold first ; he checks neural fold first
  - modèle : First, the doctor checks neural fold.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check neurulation. Then, we check neural plate.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Repli neural, ensuite Neurulation, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check neural fold. Then, we check neurulation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Neurulation / Plaque neurale, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the neurulation.
   - FR : Le patient du lit 3 a Neurulation.
   - AR : مريض السرير ٣ عنده تشكل الأنبوب العصبي.
4. **EN** The patient in bed 5 has a problem with the neural plate.
   - FR : Le patient du lit 5 a Plaque neurale.
   - AR : مريض السرير ٥ عنده الصفيحة العصبية.
5. **EN** The patient in bed 5 does not have a problem with the neurulation.
   - FR : Le patient du lit 5 n'a pas de Neurulation.
   - AR : مريض السرير ٥ ليس عنده تشكل الأنبوب العصبي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:neurulation:v1` — **a neurulation** · FR : Neurulation · AR : تشكل الأنبوب العصبي
- `gen:neural-plate:v1` — **a neural plate** · FR : Plaque neurale · AR : الصفيحة العصبية
- `gen:neural-fold:v1` — **a neural fold** · FR : Repli neural · AR : الطية العصبية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : neurulation ; a neurulation ; the patient with neurulation
  - modèle : The patient in bed 3 has a neurulation.
- **q2.** What does the patient in bed 5 have?
  - accepté : neural plate ; a problem with the neural plate
  - modèle : He has a problem with the neural plate.
- **q3.** Does the patient in bed 5 have neurulation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the neurulation ; he doesn't
  - modèle : No, he does not have a problem with the neurulation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS neurulation — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : neural fold ; a problem with the neural fold
  - modèle : He has a problem with the neural fold.
- **Tt2.** Does the patient in bed 9 have neural plate?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the neural plate ; he doesn't
  - modèle : No, he does not have a problem with the neural plate.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS plaque neurale.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a neurulation and a neural plate. (have)` → have · modèle : The patients have a neurulation and a neural plate.
- `The patient in bed 5 … not have a neurulation. (do)` → does · modèle : The patient in bed 5 does not have a neurulation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the neurulation. The patient in bed 5 has a problem with the neural plate.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Repli neural, le lit 9 a Neurulation et n'a pas Plaque neurale.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the neural fold. The patient in bed 9 has a problem with the neurulation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
