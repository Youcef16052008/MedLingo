# Relecture — Neurulation (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-018` — Le cas de Sofiane : Plaque neurale anormal(e) / _Sofiane: the neural plate is not normal_

**Objectif :** Comprendre des résultats : Neurulation normal(e), Plaque neurale anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient at the clinic.
   - FR : Sofiane est un·e patient·e à la clinique.
   - AR : Sofiane مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The neurulation is normal.”
   - FR : Le médecin dit : « Neurulation est normal(e). »
   - AR : يقول الطبيب: «تكوين الأنبوب العصبي طبيعي/طبيعية.»
5. **EN** “But the neural plate is not normal.”
   - FR : « Mais Plaque neurale n'est pas normal(e). »
   - AR : «لكن الصفيحة العصبية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the neural plate normal?”
   - FR : Lina demande : « Plaque neurale est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الصفيحة العصبية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:neurulation:v1` — **a neurulation** · FR : Neurulation · AR : تكوين الأنبوب العصبي
- `gen:notochord:v1` — **a notochord** · FR : Notochorde · AR : الحبل الظهري
- `gen:neural-plate:v1` — **a neural plate** · FR : Plaque neurale · AR : الصفيحة العصبية

### Questions (acceptées / modèle / hint)
- **q1.** Is the neurulation normal?
  - accepté : yes ; yes it is ; the neurulation is normal ; it is normal
  - modèle : Yes, the neurulation is normal.
- **q2.** Is the neural plate normal?
  - accepté : no ; no it is not ; no it isn't ; the neural plate is not normal ; it is not normal ; it isn't normal
  - modèle : No, the neural plate is not normal.
  - hint (si erreur) — FR : Attention : Plaque neurale n'est PAS normal(e).
- **q3.** Which one is not normal: the neurulation or the neural plate?
  - accepté : neural plate ; the neural plate
  - modèle : The neural plate is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the notochord normal?
  - accepté : yes ; yes it is ; the notochord is normal
  - modèle : Yes, the notochord is normal.
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
- **Reformulation :** Annonce les résultats à Sofiane en deux phrases. Garde la négation. — amorces : The neurulation is… / The neural plate is not… — modèle : *The neurulation is normal. The neural plate is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Notochorde normal(e), mais Neurulation anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The notochord is normal. The neurulation is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-018` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Neurulation et Notochorde à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient.
   - FR : Bilal est un·e patient·e.
   - AR : Bilal مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check neurulation.”
   - FR : « D'abord, nous vérifions Neurulation. »
   - AR : «أولًا، نتحقق من تكوين الأنبوب العصبي.»
5. **EN** “Then, we check notochord.”
   - FR : « Ensuite, nous vérifions Notochorde. »
   - AR : «ثم نتحقق من الحبل الظهري.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:neurulation:v1` — **a neurulation** · FR : Neurulation · AR : تكوين الأنبوب العصبي
- `gen:notochord:v1` — **a notochord** · FR : Notochorde · AR : الحبل الظهري
- `gen:neural-plate:v1` — **a neural plate** · FR : Plaque neurale · AR : الصفيحة العصبية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : neurulation ; we check neurulation ; neurulation first ; the doctor checks neurulation first
  - modèle : First, the doctor checks neurulation.
- **q2.** What does the doctor check next?
  - accepté : notochord ; then we check notochord ; notochord next ; the doctor checks notochord next
  - modèle : Then, the doctor checks notochord.
- **q3.** Does the doctor check notochord before neurulation?
  - accepté : no ; no he does not ; no he doesn't ; he checks neurulation first
  - modèle : No, he checks neurulation first, then notochord.
  - hint (si erreur) — FR : Attention : Neurulation vient D'ABORD, ensuite Notochorde.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : neural plate ; neural plate first ; he checks neural plate first
  - modèle : First, the doctor checks neural plate.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check neurulation. Then, we check notochord.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Plaque neurale, ensuite Neurulation, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check neural plate. Then, we check neurulation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-018` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Neurulation / Notochorde, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the neurulation.
   - FR : Le patient du lit 3 a Neurulation.
   - AR : مريض السرير ٣ عنده تكوين الأنبوب العصبي.
4. **EN** The patient in bed 5 has a problem with the notochord.
   - FR : Le patient du lit 5 a Notochorde.
   - AR : مريض السرير ٥ عنده الحبل الظهري.
5. **EN** The patient in bed 5 does not have a problem with the neurulation.
   - FR : Le patient du lit 5 n'a pas de Neurulation.
   - AR : مريض السرير ٥ ليس عنده تكوين الأنبوب العصبي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:neurulation:v1` — **a neurulation** · FR : Neurulation · AR : تكوين الأنبوب العصبي
- `gen:notochord:v1` — **a notochord** · FR : Notochorde · AR : الحبل الظهري
- `gen:neural-plate:v1` — **a neural plate** · FR : Plaque neurale · AR : الصفيحة العصبية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : neurulation ; a neurulation ; the patient with neurulation
  - modèle : The patient in bed 3 has a neurulation.
- **q2.** What does the patient in bed 5 have?
  - accepté : notochord ; a problem with the notochord
  - modèle : He has a problem with the notochord.
- **q3.** Does the patient in bed 5 have neurulation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the neurulation ; he doesn't
  - modèle : No, he does not have a problem with the neurulation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS neurulation — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : neural plate ; a problem with the neural plate
  - modèle : He has a problem with the neural plate.
- **Tt2.** Does the patient in bed 9 have notochord?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the notochord ; he doesn't
  - modèle : No, he does not have a problem with the notochord.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS notochorde.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a neurulation and a notochord. (have)` → have · modèle : The patients have a neurulation and a notochord.
- `The patient in bed 5 … not have a neurulation. (do)` → does · modèle : The patient in bed 5 does not have a neurulation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the neurulation. The patient in bed 5 has a problem with the notochord.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Plaque neurale, le lit 9 a Neurulation et n'a pas Notochorde.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the neural plate. The patient in bed 9 has a problem with the neurulation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
