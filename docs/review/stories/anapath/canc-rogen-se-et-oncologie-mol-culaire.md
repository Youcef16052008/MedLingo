# Relecture — Cancérogenèse et Oncologie Moléculaire (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-000` — Le cas de Karim : Proto-oncogène anormal(e) / _Karim: the proto-oncogene is not normal_

**Objectif :** Comprendre des résultats : Cancérogenèse normal(e), Proto-oncogène anormal(e).
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
4. **EN** The doctor says, “The carcinogenesis is normal.”
   - FR : Le médecin dit : « Cancérogenèse est normal(e). »
   - AR : يقول الطبيب: «التسرطن طبيعي/طبيعية.»
5. **EN** “But the proto-oncogene is not normal.”
   - FR : « Mais Proto-oncogène n'est pas normal(e). »
   - AR : «لكن الجين الأولي الورمي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the proto-oncogene normal?”
   - FR : Lina demande : « Proto-oncogène est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الجين الأولي الورمي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:carcinogenesis:v1` — **carcinogenesis** · FR : Cancérogenèse · AR : التسرطن
- `gen:oncogene:v1` — **an oncogene** · FR : Oncogène · AR : الجين الورمي
- `gen:proto-oncogene:v1` — **a proto-oncogene** · FR : Proto-oncogène · AR : الجين الأولي الورمي

### Questions (acceptées / modèle / hint)
- **q1.** Is the carcinogenesis normal?
  - accepté : yes ; yes it is ; the carcinogenesis is normal ; it is normal
  - modèle : Yes, the carcinogenesis is normal.
- **q2.** Is the proto-oncogene normal?
  - accepté : no ; no it is not ; no it isn't ; the proto-oncogene is not normal ; it is not normal ; it isn't normal
  - modèle : No, the proto-oncogene is not normal.
  - hint (si erreur) — FR : Attention : Proto-oncogène n'est PAS normal(e).
- **q3.** Which one is not normal: the carcinogenesis or the proto-oncogene?
  - accepté : proto-oncogene ; the proto-oncogene
  - modèle : The proto-oncogene is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the oncogene normal?
  - accepté : yes ; yes it is ; the oncogene is normal
  - modèle : Yes, the oncogene is normal.
- **Tt2.** Is the carcinogenesis normal?
  - accepté : no ; no it is not ; no it isn't ; the carcinogenesis is not normal ; it is not normal
  - modèle : No, the carcinogenesis is not normal.
  - hint (si erreur) — FR : Attention : Cancérogenèse n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The carcinogenesis … normal. (be)` → is · modèle : The carcinogenesis is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The carcinogenesis is… / The proto-oncogene is not… — modèle : *The carcinogenesis is normal. The proto-oncogene is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Oncogène normal(e), mais Cancérogenèse anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The oncogene is normal. The carcinogenesis is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Cancérogenèse et Oncogène à un patient.
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
4. **EN** “First, we check carcinogenesis.”
   - FR : « D'abord, nous vérifions Cancérogenèse. »
   - AR : «أولًا، نتحقق من التسرطن.»
5. **EN** “Then, we check oncogene.”
   - FR : « Ensuite, nous vérifions Oncogène. »
   - AR : «ثم نتحقق من الجين الورمي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:carcinogenesis:v1` — **carcinogenesis** · FR : Cancérogenèse · AR : التسرطن
- `gen:oncogene:v1` — **an oncogene** · FR : Oncogène · AR : الجين الورمي
- `gen:proto-oncogene:v1` — **a proto-oncogene** · FR : Proto-oncogène · AR : الجين الأولي الورمي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : carcinogenesis ; we check carcinogenesis ; carcinogenesis first ; the doctor checks carcinogenesis first
  - modèle : First, the doctor checks carcinogenesis.
- **q2.** What does the doctor check next?
  - accepté : oncogene ; then we check oncogene ; oncogene next ; the doctor checks oncogene next
  - modèle : Then, the doctor checks oncogene.
- **q3.** Does the doctor check oncogene before carcinogenesis?
  - accepté : no ; no he does not ; no he doesn't ; he checks carcinogenesis first
  - modèle : No, he checks carcinogenesis first, then oncogene.
  - hint (si erreur) — FR : Attention : Cancérogenèse vient D'ABORD, ensuite Oncogène.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : proto-oncogene ; proto-oncogene first ; he checks proto-oncogene first
  - modèle : First, the doctor checks proto-oncogene.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check carcinogenesis. Then, we check oncogene.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Proto-oncogène, ensuite Cancérogenèse, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check proto-oncogene. Then, we check carcinogenesis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cancérogenèse / Oncogène, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the carcinogenesis.
   - FR : Le patient du lit 3 a Cancérogenèse.
   - AR : مريض السرير ٣ عنده التسرطن.
4. **EN** The patient in bed 5 has a problem with the oncogene.
   - FR : Le patient du lit 5 a Oncogène.
   - AR : مريض السرير ٥ عنده الجين الورمي.
5. **EN** The patient in bed 5 does not have a problem with the carcinogenesis.
   - FR : Le patient du lit 5 n'a pas de Cancérogenèse.
   - AR : مريض السرير ٥ ليس عنده التسرطن.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:carcinogenesis:v1` — **carcinogenesis** · FR : Cancérogenèse · AR : التسرطن
- `gen:oncogene:v1` — **an oncogene** · FR : Oncogène · AR : الجين الورمي
- `gen:proto-oncogene:v1` — **a proto-oncogene** · FR : Proto-oncogène · AR : الجين الأولي الورمي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : carcinogenesis ; the patient with carcinogenesis
  - modèle : The patient in bed 3 has carcinogenesis.
- **q2.** What does the patient in bed 5 have?
  - accepté : oncogene ; a problem with the oncogene
  - modèle : He has a problem with the oncogene.
- **q3.** Does the patient in bed 5 have carcinogenesis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the carcinogenesis ; he doesn't
  - modèle : No, he does not have a problem with the carcinogenesis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cancérogenèse — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : proto-oncogene ; a problem with the proto-oncogene
  - modèle : He has a problem with the proto-oncogene.
- **Tt2.** Does the patient in bed 9 have oncogene?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the oncogene ; he doesn't
  - modèle : No, he does not have a problem with the oncogene.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS oncogène.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … carcinogenesis and an oncogene. (have)` → have · modèle : The patients have carcinogenesis and an oncogene.
- `The patient in bed 5 … not have carcinogenesis. (do)` → does · modèle : The patient in bed 5 does not have carcinogenesis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the carcinogenesis. The patient in bed 5 has a problem with the oncogene.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Proto-oncogène, le lit 9 a Cancérogenèse et n'a pas Oncogène.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the proto-oncogene. The patient in bed 9 has a problem with the carcinogenesis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
