# Relecture — Développement Cardiovasculaire (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-000` — Le cas de Karim : Inflexion cardiaque anormal(e) / _Karim: the cardiac looping is not normal_

**Objectif :** Comprendre des résultats : Aire cardiogène normal(e), Inflexion cardiaque anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The cardiogenic area is normal.”
   - FR : Le médecin dit : « Aire cardiogène est normal(e). »
   - AR : يقول الطبيب: «المنطقة القلبية المولدة طبيعي/طبيعية.»
5. **EN** “But the cardiac looping is not normal.”
   - FR : « Mais Inflexion cardiaque n'est pas normal(e). »
   - AR : «لكن الانعطاف القلبي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the cardiac looping normal?”
   - FR : Lina demande : « Inflexion cardiaque est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الانعطاف القلبي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:cardiogenic-area:v1` — **a cardiogenic area** · FR : Aire cardiogène · AR : المنطقة القلبية المولدة
- `gen:heart-tube:v1` — **a heart tube** · FR : Tube cardiaque · AR : الأنبوب القلبي
- `gen:cardiac-looping:v1` — **a cardiac looping** · FR : Inflexion cardiaque · AR : الانعطاف القلبي

### Questions (acceptées / modèle / hint)
- **q1.** Is the cardiogenic area normal?
  - accepté : yes ; yes it is ; the cardiogenic area is normal ; it is normal
  - modèle : Yes, the cardiogenic area is normal.
- **q2.** Is the cardiac looping normal?
  - accepté : no ; no it is not ; no it isn't ; the cardiac looping is not normal ; it is not normal ; it isn't normal
  - modèle : No, the cardiac looping is not normal.
  - hint (si erreur) — FR : Attention : Inflexion cardiaque n'est PAS normal(e).
- **q3.** Which one is not normal: the cardiogenic area or the cardiac looping?
  - accepté : cardiac looping ; the cardiac looping
  - modèle : The cardiac looping is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the heart tube normal?
  - accepté : yes ; yes it is ; the heart tube is normal
  - modèle : Yes, the heart tube is normal.
- **Tt2.** Is the cardiogenic area normal?
  - accepté : no ; no it is not ; no it isn't ; the cardiogenic area is not normal ; it is not normal
  - modèle : No, the cardiogenic area is not normal.
  - hint (si erreur) — FR : Attention : Aire cardiogène n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The cardiogenic area … normal. (be)` → is · modèle : The cardiogenic area is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The cardiogenic area is… / The cardiac looping is not… — modèle : *The cardiogenic area is normal. The cardiac looping is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Tube cardiaque normal(e), mais Aire cardiogène anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The heart tube is normal. The cardiogenic area is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Aire cardiogène et Tube cardiaque à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check cardiogenic area.”
   - FR : « D'abord, nous vérifions Aire cardiogène. »
   - AR : «أولًا، نتحقق من المنطقة القلبية المولدة.»
5. **EN** “Then, we check heart tube.”
   - FR : « Ensuite, nous vérifions Tube cardiaque. »
   - AR : «ثم نتحقق من الأنبوب القلبي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cardiogenic-area:v1` — **a cardiogenic area** · FR : Aire cardiogène · AR : المنطقة القلبية المولدة
- `gen:heart-tube:v1` — **a heart tube** · FR : Tube cardiaque · AR : الأنبوب القلبي
- `gen:cardiac-looping:v1` — **a cardiac looping** · FR : Inflexion cardiaque · AR : الانعطاف القلبي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cardiogenic area ; we check cardiogenic area ; cardiogenic area first ; the doctor checks cardiogenic area first
  - modèle : First, the doctor checks cardiogenic area.
- **q2.** What does the doctor check next?
  - accepté : heart tube ; then we check heart tube ; heart tube next ; the doctor checks heart tube next
  - modèle : Then, the doctor checks heart tube.
- **q3.** Does the doctor check heart tube before cardiogenic area?
  - accepté : no ; no he does not ; no he doesn't ; he checks cardiogenic area first
  - modèle : No, he checks cardiogenic area first, then heart tube.
  - hint (si erreur) — FR : Attention : Aire cardiogène vient D'ABORD, ensuite Tube cardiaque.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : cardiac looping ; cardiac looping first ; he checks cardiac looping first
  - modèle : First, the doctor checks cardiac looping.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cardiogenic area. Then, we check heart tube.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Inflexion cardiaque, ensuite Aire cardiogène, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check cardiac looping. Then, we check cardiogenic area.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Aire cardiogène / Tube cardiaque, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the cardiogenic area.
   - FR : Le patient du lit 3 a Aire cardiogène.
   - AR : مريض السرير ٣ عنده المنطقة القلبية المولدة.
4. **EN** The patient in bed 5 has a problem with the heart tube.
   - FR : Le patient du lit 5 a Tube cardiaque.
   - AR : مريض السرير ٥ عنده الأنبوب القلبي.
5. **EN** The patient in bed 5 does not have a problem with the cardiogenic area.
   - FR : Le patient du lit 5 n'a pas de Aire cardiogène.
   - AR : مريض السرير ٥ ليس عنده المنطقة القلبية المولدة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:cardiogenic-area:v1` — **a cardiogenic area** · FR : Aire cardiogène · AR : المنطقة القلبية المولدة
- `gen:heart-tube:v1` — **a heart tube** · FR : Tube cardiaque · AR : الأنبوب القلبي
- `gen:cardiac-looping:v1` — **a cardiac looping** · FR : Inflexion cardiaque · AR : الانعطاف القلبي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cardiogenic area ; a cardiogenic area ; the patient with cardiogenic area
  - modèle : The patient in bed 3 has a cardiogenic area.
- **q2.** What does the patient in bed 5 have?
  - accepté : heart tube ; a problem with the heart tube
  - modèle : He has a problem with the heart tube.
- **q3.** Does the patient in bed 5 have cardiogenic area?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the cardiogenic area ; he doesn't
  - modèle : No, he does not have a problem with the cardiogenic area.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS aire cardiogène — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : cardiac looping ; a problem with the cardiac looping
  - modèle : He has a problem with the cardiac looping.
- **Tt2.** Does the patient in bed 9 have heart tube?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the heart tube ; he doesn't
  - modèle : No, he does not have a problem with the heart tube.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS tube cardiaque.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a cardiogenic area and a heart tube. (have)` → have · modèle : The patients have a cardiogenic area and a heart tube.
- `The patient in bed 5 … not have a cardiogenic area. (do)` → does · modèle : The patient in bed 5 does not have a cardiogenic area.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the cardiogenic area. The patient in bed 5 has a problem with the heart tube.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Inflexion cardiaque, le lit 9 a Aire cardiogène et n'a pas Tube cardiaque.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the cardiac looping. The patient in bed 9 has a problem with the cardiogenic area.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
