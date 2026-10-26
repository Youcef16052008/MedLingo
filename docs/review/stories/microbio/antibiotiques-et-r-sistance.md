# Relecture — Antibiotiques et Résistance (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-000` — Le cas de Karim : Bactériostatique anormal(e) / _Karim: the bacteriostatic is not normal_

**Objectif :** Comprendre des résultats : Antibiotique normal(e), Bactériostatique anormal(e).
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
4. **EN** The doctor says, “The antibiotic is normal.”
   - FR : Le médecin dit : « Antibiotique est normal(e). »
   - AR : يقول الطبيب: «المضاد الحيوي طبيعي/طبيعية.»
5. **EN** “But the bacteriostatic is not normal.”
   - FR : « Mais Bactériostatique n'est pas normal(e). »
   - AR : «لكن موقف نمو البكتيريا غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the bacteriostatic normal?”
   - FR : Lina demande : « Bactériostatique est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل موقف نمو البكتيريا طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:antibiotic:v1` — **an antibiotic** · FR : Antibiotique · AR : المضاد الحيوي
- `gen:bactericidal:v1` — **a bactericidal** · FR : Bactéricide · AR : قاتل البكتيريا
- `gen:bacteriostatic:v1` — **a bacteriostatic** · FR : Bactériostatique · AR : موقف نمو البكتيريا

### Questions (acceptées / modèle / hint)
- **q1.** Is the antibiotic normal?
  - accepté : yes ; yes it is ; the antibiotic is normal ; it is normal
  - modèle : Yes, the antibiotic is normal.
- **q2.** Is the bacteriostatic normal?
  - accepté : no ; no it is not ; no it isn't ; the bacteriostatic is not normal ; it is not normal ; it isn't normal
  - modèle : No, the bacteriostatic is not normal.
  - hint (si erreur) — FR : Attention : Bactériostatique n'est PAS normal(e).
- **q3.** Which one is not normal: the antibiotic or the bacteriostatic?
  - accepté : bacteriostatic ; the bacteriostatic
  - modèle : The bacteriostatic is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the bactericidal normal?
  - accepté : yes ; yes it is ; the bactericidal is normal
  - modèle : Yes, the bactericidal is normal.
- **Tt2.** Is the antibiotic normal?
  - accepté : no ; no it is not ; no it isn't ; the antibiotic is not normal ; it is not normal
  - modèle : No, the antibiotic is not normal.
  - hint (si erreur) — FR : Attention : Antibiotique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The antibiotic … normal. (be)` → is · modèle : The antibiotic is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The antibiotic is… / The bacteriostatic is not… — modèle : *The antibiotic is normal. The bacteriostatic is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Bactéricide normal(e), mais Antibiotique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The bactericidal is normal. The antibiotic is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Antibiotique et Bactéricide à un patient.
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
4. **EN** “First, we check antibiotic.”
   - FR : « D'abord, nous vérifions Antibiotique. »
   - AR : «أولًا، نتحقق من المضاد الحيوي.»
5. **EN** “Then, we check bactericidal.”
   - FR : « Ensuite, nous vérifions Bactéricide. »
   - AR : «ثم نتحقق من قاتل البكتيريا.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:antibiotic:v1` — **an antibiotic** · FR : Antibiotique · AR : المضاد الحيوي
- `gen:bactericidal:v1` — **a bactericidal** · FR : Bactéricide · AR : قاتل البكتيريا
- `gen:bacteriostatic:v1` — **a bacteriostatic** · FR : Bactériostatique · AR : موقف نمو البكتيريا

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : antibiotic ; we check antibiotic ; antibiotic first ; the doctor checks antibiotic first
  - modèle : First, the doctor checks antibiotic.
- **q2.** What does the doctor check next?
  - accepté : bactericidal ; then we check bactericidal ; bactericidal next ; the doctor checks bactericidal next
  - modèle : Then, the doctor checks bactericidal.
- **q3.** Does the doctor check bactericidal before antibiotic?
  - accepté : no ; no he does not ; no he doesn't ; he checks antibiotic first
  - modèle : No, he checks antibiotic first, then bactericidal.
  - hint (si erreur) — FR : Attention : Antibiotique vient D'ABORD, ensuite Bactéricide.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : bacteriostatic ; bacteriostatic first ; he checks bacteriostatic first
  - modèle : First, the doctor checks bacteriostatic.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check antibiotic. Then, we check bactericidal.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Bactériostatique, ensuite Antibiotique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check bacteriostatic. Then, we check antibiotic.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Antibiotique / Bactéricide, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the antibiotic.
   - FR : Le patient du lit 3 a Antibiotique.
   - AR : مريض السرير ٣ عنده المضاد الحيوي.
4. **EN** The patient in bed 5 has a problem with the bactericidal.
   - FR : Le patient du lit 5 a Bactéricide.
   - AR : مريض السرير ٥ عنده قاتل البكتيريا.
5. **EN** The patient in bed 5 does not have a problem with the antibiotic.
   - FR : Le patient du lit 5 n'a pas de Antibiotique.
   - AR : مريض السرير ٥ ليس عنده المضاد الحيوي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:antibiotic:v1` — **an antibiotic** · FR : Antibiotique · AR : المضاد الحيوي
- `gen:bactericidal:v1` — **a bactericidal** · FR : Bactéricide · AR : قاتل البكتيريا
- `gen:bacteriostatic:v1` — **a bacteriostatic** · FR : Bactériostatique · AR : موقف نمو البكتيريا

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : antibiotic ; an antibiotic ; the patient with antibiotic
  - modèle : The patient in bed 3 has an antibiotic.
- **q2.** What does the patient in bed 5 have?
  - accepté : bactericidal ; a problem with the bactericidal
  - modèle : He has a problem with the bactericidal.
- **q3.** Does the patient in bed 5 have antibiotic?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the antibiotic ; he doesn't
  - modèle : No, he does not have a problem with the antibiotic.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS antibiotique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : bacteriostatic ; a problem with the bacteriostatic
  - modèle : He has a problem with the bacteriostatic.
- **Tt2.** Does the patient in bed 9 have bactericidal?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the bactericidal ; he doesn't
  - modèle : No, he does not have a problem with the bactericidal.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS bactéricide.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an antibiotic and a bactericidal. (have)` → have · modèle : The patients have an antibiotic and a bactericidal.
- `The patient in bed 5 … not have an antibiotic. (do)` → does · modèle : The patient in bed 5 does not have an antibiotic.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the antibiotic. The patient in bed 5 has a problem with the bactericidal.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Bactériostatique, le lit 9 a Antibiotique et n'a pas Bactéricide.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the bacteriostatic. The patient in bed 9 has a problem with the antibiotic.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
