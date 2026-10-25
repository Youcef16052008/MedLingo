# Relecture — Obstetrics & Gynecology (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-025` — Le cas de Nadia : Nausées matinales anormal(e) / _Nadia: the morning sickness is not normal_

**Objectif :** Comprendre des résultats : Ovulation normal(e), Nausées matinales anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The ovulation is normal.”
   - FR : Le médecin dit : « Ovulation est normal(e). »
   - AR : يقول الطبيب: «الإباضة طبيعي/طبيعية.»
5. **EN** “But the morning sickness is not normal.”
   - FR : « Mais Nausées matinales n'est pas normal(e). »
   - AR : «لكن غثيان الصباح غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the morning sickness normal?”
   - FR : Amine demande : « Nausées matinales est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل غثيان الصباح طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:ovulation:v1` — **an ovulation** · FR : Ovulation · AR : الإباضة
- `gen:trimester:v1` — **a trimester** · FR : Trimestre · AR : الثلث (من الحمل)
- `gen:morning-sickness:v1` — **a morning sickness** · FR : Nausées matinales · AR : غثيان الصباح

### Questions (acceptées / modèle / hint)
- **q1.** Is the ovulation normal?
  - accepté : yes ; yes it is ; the ovulation is normal ; it is normal
  - modèle : Yes, the ovulation is normal.
- **q2.** Is the morning sickness normal?
  - accepté : no ; no it is not ; no it isn't ; the morning sickness is not normal ; it is not normal ; it isn't normal
  - modèle : No, the morning sickness is not normal.
  - hint (si erreur) — FR : Attention : Nausées matinales n'est PAS normal(e).
- **q3.** Which one is not normal: the ovulation or the morning sickness?
  - accepté : morning sickness ; the morning sickness
  - modèle : The morning sickness is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the trimester normal?
  - accepté : yes ; yes it is ; the trimester is normal
  - modèle : Yes, the trimester is normal.
- **Tt2.** Is the ovulation normal?
  - accepté : no ; no it is not ; no it isn't ; the ovulation is not normal ; it is not normal
  - modèle : No, the ovulation is not normal.
  - hint (si erreur) — FR : Attention : Ovulation n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The ovulation … normal. (be)` → is · modèle : The ovulation is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The ovulation is… / The morning sickness is not… — modèle : *The ovulation is normal. The morning sickness is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Trimestre normal(e), mais Ovulation anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The trimester is normal. The ovulation is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-025` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Ovulation et Trimestre à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient.
   - FR : Amel est un·e patient·e.
   - AR : Amel مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check ovulation.”
   - FR : « D'abord, nous vérifions Ovulation. »
   - AR : «أولًا، نتحقق من الإباضة.»
5. **EN** “Then, we check trimester.”
   - FR : « Ensuite, nous vérifions Trimestre. »
   - AR : «ثم نتحقق من الثلث (من الحمل).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:ovulation:v1` — **an ovulation** · FR : Ovulation · AR : الإباضة
- `gen:trimester:v1` — **a trimester** · FR : Trimestre · AR : الثلث (من الحمل)
- `gen:morning-sickness:v1` — **a morning sickness** · FR : Nausées matinales · AR : غثيان الصباح

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : ovulation ; we check ovulation ; ovulation first ; the doctor checks ovulation first
  - modèle : First, the doctor checks ovulation.
- **q2.** What does the doctor check next?
  - accepté : trimester ; then we check trimester ; trimester next ; the doctor checks trimester next
  - modèle : Then, the doctor checks trimester.
- **q3.** Does the doctor check trimester before ovulation?
  - accepté : no ; no he does not ; no he doesn't ; he checks ovulation first
  - modèle : No, he checks ovulation first, then trimester.
  - hint (si erreur) — FR : Attention : Ovulation vient D'ABORD, ensuite Trimestre.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : morning sickness ; morning sickness first ; he checks morning sickness first
  - modèle : First, the doctor checks morning sickness.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check ovulation. Then, we check trimester.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Nausées matinales, ensuite Ovulation, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check morning sickness. Then, we check ovulation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-025` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Ovulation / Trimestre, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the ovulation.
   - FR : Le patient du lit 3 a Ovulation.
   - AR : مريض السرير ٣ عنده الإباضة.
4. **EN** The patient in bed 5 has a problem with the trimester.
   - FR : Le patient du lit 5 a Trimestre.
   - AR : مريض السرير ٥ عنده الثلث (من الحمل).
5. **EN** The patient in bed 5 does not have a problem with the ovulation.
   - FR : Le patient du lit 5 n'a pas de Ovulation.
   - AR : مريض السرير ٥ ليس عنده الإباضة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:ovulation:v1` — **an ovulation** · FR : Ovulation · AR : الإباضة
- `gen:trimester:v1` — **a trimester** · FR : Trimestre · AR : الثلث (من الحمل)
- `gen:morning-sickness:v1` — **a morning sickness** · FR : Nausées matinales · AR : غثيان الصباح

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : ovulation ; an ovulation ; the patient with ovulation
  - modèle : The patient in bed 3 has an ovulation.
- **q2.** What does the patient in bed 5 have?
  - accepté : trimester ; a problem with the trimester
  - modèle : He has a problem with the trimester.
- **q3.** Does the patient in bed 5 have ovulation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the ovulation ; he doesn't
  - modèle : No, he does not have a problem with the ovulation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS ovulation — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : morning sickness ; a problem with the morning sickness
  - modèle : He has a problem with the morning sickness.
- **Tt2.** Does the patient in bed 9 have trimester?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the trimester ; he doesn't
  - modèle : No, he does not have a problem with the trimester.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS trimestre.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an ovulation and a trimester. (have)` → have · modèle : The patients have an ovulation and a trimester.
- `The patient in bed 5 … not have an ovulation. (do)` → does · modèle : The patient in bed 5 does not have an ovulation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the ovulation. The patient in bed 5 has a problem with the trimester.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Nausées matinales, le lit 9 a Ovulation et n'a pas Trimestre.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the morning sickness. The patient in bed 9 has a problem with the ovulation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
