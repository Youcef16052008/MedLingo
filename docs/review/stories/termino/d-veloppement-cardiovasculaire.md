# Relecture — Développement Cardiovasculaire (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-003` — Le cas de Amel : Courbure cardiaque anormal(e) / _Amel: the cardiac looping is not normal_

**Objectif :** Comprendre des résultats : Cardiogenèse normal(e), Courbure cardiaque anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The cardiogenesis is normal.”
   - FR : Le médecin dit : « Cardiogenèse est normal(e). »
   - AR : يقول الطبيب: «تكوين القلب طبيعي/طبيعية.»
5. **EN** “But the cardiac looping is not normal.”
   - FR : « Mais Courbure cardiaque n'est pas normal(e). »
   - AR : «لكن تحدب القلب غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the cardiac looping normal?”
   - FR : Amine demande : « Courbure cardiaque est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل تحدب القلب طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:cardiogenesis:v1` — **cardiogenesis** · FR : Cardiogenèse · AR : تكوين القلب
- `gen:heart-tube:v1` — **a heart tube** · FR : Tube cardiaque · AR : الأنبوب القلبي
- `gen:cardiac-looping:v1` — **a cardiac looping** · FR : Courbure cardiaque · AR : تحدب القلب

### Questions (acceptées / modèle / hint)
- **q1.** Is the cardiogenesis normal?
  - accepté : yes ; yes it is ; the cardiogenesis is normal ; it is normal
  - modèle : Yes, the cardiogenesis is normal.
- **q2.** Is the cardiac looping normal?
  - accepté : no ; no it is not ; no it isn't ; the cardiac looping is not normal ; it is not normal ; it isn't normal
  - modèle : No, the cardiac looping is not normal.
  - hint (si erreur) — FR : Attention : Courbure cardiaque n'est PAS normal(e).
- **q3.** Which one is not normal: the cardiogenesis or the cardiac looping?
  - accepté : cardiac looping ; the cardiac looping
  - modèle : The cardiac looping is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the heart tube normal?
  - accepté : yes ; yes it is ; the heart tube is normal
  - modèle : Yes, the heart tube is normal.
- **Tt2.** Is the cardiogenesis normal?
  - accepté : no ; no it is not ; no it isn't ; the cardiogenesis is not normal ; it is not normal
  - modèle : No, the cardiogenesis is not normal.
  - hint (si erreur) — FR : Attention : Cardiogenèse n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The cardiogenesis … normal. (be)` → is · modèle : The cardiogenesis is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The cardiogenesis is… / The cardiac looping is not… — modèle : *The cardiogenesis is normal. The cardiac looping is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Tube cardiaque normal(e), mais Cardiogenèse anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The heart tube is normal. The cardiogenesis is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Cardiogenèse et Tube cardiaque à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient.
   - FR : Meriem est un·e patient·e.
   - AR : Meriem مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check cardiogenesis.”
   - FR : « D'abord, nous vérifions Cardiogenèse. »
   - AR : «أولًا، نتحقق من تكوين القلب.»
5. **EN** “Then, we check heart tube.”
   - FR : « Ensuite, nous vérifions Tube cardiaque. »
   - AR : «ثم نتحقق من الأنبوب القلبي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cardiogenesis:v1` — **cardiogenesis** · FR : Cardiogenèse · AR : تكوين القلب
- `gen:heart-tube:v1` — **a heart tube** · FR : Tube cardiaque · AR : الأنبوب القلبي
- `gen:cardiac-looping:v1` — **a cardiac looping** · FR : Courbure cardiaque · AR : تحدب القلب

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cardiogenesis ; we check cardiogenesis ; cardiogenesis first ; the doctor checks cardiogenesis first
  - modèle : First, the doctor checks cardiogenesis.
- **q2.** What does the doctor check next?
  - accepté : heart tube ; then we check heart tube ; heart tube next ; the doctor checks heart tube next
  - modèle : Then, the doctor checks heart tube.
- **q3.** Does the doctor check heart tube before cardiogenesis?
  - accepté : no ; no he does not ; no he doesn't ; he checks cardiogenesis first
  - modèle : No, he checks cardiogenesis first, then heart tube.
  - hint (si erreur) — FR : Attention : Cardiogenèse vient D'ABORD, ensuite Tube cardiaque.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
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
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cardiogenesis. Then, we check heart tube.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Courbure cardiaque, ensuite Cardiogenèse, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check cardiac looping. Then, we check cardiogenesis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cardiogenèse / Tube cardiaque, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the cardiogenesis.
   - FR : Le patient du lit 3 a Cardiogenèse.
   - AR : مريض السرير ٣ عنده تكوين القلب.
4. **EN** The patient in bed 5 has a problem with the heart tube.
   - FR : Le patient du lit 5 a Tube cardiaque.
   - AR : مريض السرير ٥ عنده الأنبوب القلبي.
5. **EN** The patient in bed 5 does not have a problem with the cardiogenesis.
   - FR : Le patient du lit 5 n'a pas de Cardiogenèse.
   - AR : مريض السرير ٥ ليس عنده تكوين القلب.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:cardiogenesis:v1` — **cardiogenesis** · FR : Cardiogenèse · AR : تكوين القلب
- `gen:heart-tube:v1` — **a heart tube** · FR : Tube cardiaque · AR : الأنبوب القلبي
- `gen:cardiac-looping:v1` — **a cardiac looping** · FR : Courbure cardiaque · AR : تحدب القلب

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cardiogenesis ; the patient with cardiogenesis
  - modèle : The patient in bed 3 has cardiogenesis.
- **q2.** What does the patient in bed 5 have?
  - accepté : heart tube ; a problem with the heart tube
  - modèle : He has a problem with the heart tube.
- **q3.** Does the patient in bed 5 have cardiogenesis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the cardiogenesis ; he doesn't
  - modèle : No, he does not have a problem with the cardiogenesis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cardiogenèse — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
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
- `The patients … cardiogenesis and a heart tube. (have)` → have · modèle : The patients have cardiogenesis and a heart tube.
- `The patient in bed 5 … not have cardiogenesis. (do)` → does · modèle : The patient in bed 5 does not have cardiogenesis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the cardiogenesis. The patient in bed 5 has a problem with the heart tube.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Courbure cardiaque, le lit 9 a Cardiogenèse et n'a pas Tube cardiaque.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the cardiac looping. The patient in bed 9 has a problem with the cardiogenesis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
