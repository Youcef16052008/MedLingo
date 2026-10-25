# Relecture — Imagerie Médicale (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-004` — Le cas de Farid : Radiographie anormal(e) / _Farid: the radiography is not normal_

**Objectif :** Comprendre des résultats : Rayon X normal(e), Radiographie anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The x-ray is normal.”
   - FR : Le médecin dit : « Rayon X est normal(e). »
   - AR : يقول الطبيب: «الأشعة السينية طبيعي/طبيعية.»
5. **EN** “But the radiography is not normal.”
   - FR : « Mais Radiographie n'est pas normal(e). »
   - AR : «لكن التصوير الشعاعي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the radiography normal?”
   - FR : Lina demande : « Radiographie est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل التصوير الشعاعي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:x-ray:v1` — **a x-ray** · FR : Rayon X · AR : الأشعة السينية
- `gen:x-ray-tube:v1` — **a x-ray tube** · FR : Tube à rayons X · AR : أنبوب الأشعة السينية
- `gen:radiography:v1` — **a radiography** · FR : Radiographie · AR : التصوير الشعاعي

### Questions (acceptées / modèle / hint)
- **q1.** Is the x-ray normal?
  - accepté : yes ; yes it is ; the x-ray is normal ; it is normal
  - modèle : Yes, the x-ray is normal.
- **q2.** Is the radiography normal?
  - accepté : no ; no it is not ; no it isn't ; the radiography is not normal ; it is not normal ; it isn't normal
  - modèle : No, the radiography is not normal.
  - hint (si erreur) — FR : Attention : Radiographie n'est PAS normal(e).
- **q3.** Which one is not normal: the x-ray or the radiography?
  - accepté : radiography ; the radiography
  - modèle : The radiography is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the x-ray tube normal?
  - accepté : yes ; yes it is ; the x-ray tube is normal
  - modèle : Yes, the x-ray tube is normal.
- **Tt2.** Is the x-ray normal?
  - accepté : no ; no it is not ; no it isn't ; the x-ray is not normal ; it is not normal
  - modèle : No, the x-ray is not normal.
  - hint (si erreur) — FR : Attention : Rayon X n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The x-ray … normal. (be)` → is · modèle : The x-ray is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The x-ray is… / The radiography is not… — modèle : *The x-ray is normal. The radiography is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Tube à rayons X normal(e), mais Rayon X anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The x-ray tube is normal. The x-ray is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Rayon X et Tube à rayons X à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient.
   - FR : Sofiane est un·e patient·e.
   - AR : Sofiane مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check x-ray.”
   - FR : « D'abord, nous vérifions Rayon X. »
   - AR : «أولًا، نتحقق من الأشعة السينية.»
5. **EN** “Then, we check x-ray tube.”
   - FR : « Ensuite, nous vérifions Tube à rayons X. »
   - AR : «ثم نتحقق من أنبوب الأشعة السينية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:x-ray:v1` — **a x-ray** · FR : Rayon X · AR : الأشعة السينية
- `gen:x-ray-tube:v1` — **a x-ray tube** · FR : Tube à rayons X · AR : أنبوب الأشعة السينية
- `gen:radiography:v1` — **a radiography** · FR : Radiographie · AR : التصوير الشعاعي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : x-ray ; we check x-ray ; x-ray first ; the doctor checks x-ray first
  - modèle : First, the doctor checks x-ray.
- **q2.** What does the doctor check next?
  - accepté : x-ray tube ; then we check x-ray tube ; x-ray tube next ; the doctor checks x-ray tube next
  - modèle : Then, the doctor checks x-ray tube.
- **q3.** Does the doctor check x-ray tube before x-ray?
  - accepté : no ; no he does not ; no he doesn't ; he checks x-ray first
  - modèle : No, he checks x-ray first, then x-ray tube.
  - hint (si erreur) — FR : Attention : Rayon X vient D'ABORD, ensuite Tube à rayons X.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : radiography ; radiography first ; he checks radiography first
  - modèle : First, the doctor checks radiography.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check x-ray. Then, we check x-ray tube.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Radiographie, ensuite Rayon X, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check radiography. Then, we check x-ray.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Rayon X / Tube à rayons X, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the x-ray.
   - FR : Le patient du lit 3 a Rayon X.
   - AR : مريض السرير ٣ عنده الأشعة السينية.
4. **EN** The patient in bed 5 has a problem with the x-ray tube.
   - FR : Le patient du lit 5 a Tube à rayons X.
   - AR : مريض السرير ٥ عنده أنبوب الأشعة السينية.
5. **EN** The patient in bed 5 does not have a problem with the x-ray.
   - FR : Le patient du lit 5 n'a pas de Rayon X.
   - AR : مريض السرير ٥ ليس عنده الأشعة السينية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:x-ray:v1` — **a x-ray** · FR : Rayon X · AR : الأشعة السينية
- `gen:x-ray-tube:v1` — **a x-ray tube** · FR : Tube à rayons X · AR : أنبوب الأشعة السينية
- `gen:radiography:v1` — **a radiography** · FR : Radiographie · AR : التصوير الشعاعي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : x-ray ; a x-ray ; the patient with x-ray
  - modèle : The patient in bed 3 has a x-ray.
- **q2.** What does the patient in bed 5 have?
  - accepté : x-ray tube ; a problem with the x-ray tube
  - modèle : He has a problem with the x-ray tube.
- **q3.** Does the patient in bed 5 have x-ray?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the x-ray ; he doesn't
  - modèle : No, he does not have a problem with the x-ray.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS rayon x — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : radiography ; a problem with the radiography
  - modèle : He has a problem with the radiography.
- **Tt2.** Does the patient in bed 9 have x-ray tube?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the x-ray tube ; he doesn't
  - modèle : No, he does not have a problem with the x-ray tube.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS tube à rayons x.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a x-ray and a x-ray tube. (have)` → have · modèle : The patients have a x-ray and a x-ray tube.
- `The patient in bed 5 … not have a x-ray. (do)` → does · modèle : The patient in bed 5 does not have a x-ray.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the x-ray. The patient in bed 5 has a problem with the x-ray tube.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Radiographie, le lit 9 a Rayon X et n'a pas Tube à rayons X.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the radiography. The patient in bed 9 has a problem with the x-ray.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
