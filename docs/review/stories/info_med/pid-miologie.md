# Relecture — Épidémiologie (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-014` — Le cas de Youssef : Incidence anormal(e) / _Youssef: the incidence is not normal_

**Objectif :** Comprendre des résultats : Épidémiologie normal(e), Incidence anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The epidemiology is normal.”
   - FR : Le médecin dit : « Épidémiologie est normal(e). »
   - AR : يقول الطبيب: «علم الوبائيات طبيعي/طبيعية.»
5. **EN** “But the incidence is not normal.”
   - FR : « Mais Incidence n'est pas normal(e). »
   - AR : «لكن الوقوع (الحدوث) غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the incidence normal?”
   - FR : Lina demande : « Incidence est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الوقوع (الحدوث) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:epidemiology:v1` — **an epidemiology** · FR : Épidémiologie · AR : علم الوبائيات
- `gen:prevalence:v1` — **a prevalence** · FR : Prévalence · AR : الانتشار
- `gen:incidence:v1` — **an incidence** · FR : Incidence · AR : الوقوع (الحدوث)

### Questions (acceptées / modèle / hint)
- **q1.** Is the epidemiology normal?
  - accepté : yes ; yes it is ; the epidemiology is normal ; it is normal
  - modèle : Yes, the epidemiology is normal.
- **q2.** Is the incidence normal?
  - accepté : no ; no it is not ; no it isn't ; the incidence is not normal ; it is not normal ; it isn't normal
  - modèle : No, the incidence is not normal.
  - hint (si erreur) — FR : Attention : Incidence n'est PAS normal(e).
- **q3.** Which one is not normal: the epidemiology or the incidence?
  - accepté : incidence ; the incidence
  - modèle : The incidence is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the prevalence normal?
  - accepté : yes ; yes it is ; the prevalence is normal
  - modèle : Yes, the prevalence is normal.
- **Tt2.** Is the epidemiology normal?
  - accepté : no ; no it is not ; no it isn't ; the epidemiology is not normal ; it is not normal
  - modèle : No, the epidemiology is not normal.
  - hint (si erreur) — FR : Attention : Épidémiologie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The epidemiology … normal. (be)` → is · modèle : The epidemiology is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The epidemiology is… / The incidence is not… — modèle : *The epidemiology is normal. The incidence is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Prévalence normal(e), mais Épidémiologie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The prevalence is normal. The epidemiology is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-014` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Épidémiologie et Prévalence à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient.
   - FR : Farid est un·e patient·e.
   - AR : Farid مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check epidemiology.”
   - FR : « D'abord, nous vérifions Épidémiologie. »
   - AR : «أولًا، نتحقق من علم الوبائيات.»
5. **EN** “Then, we check prevalence.”
   - FR : « Ensuite, nous vérifions Prévalence. »
   - AR : «ثم نتحقق من الانتشار.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:epidemiology:v1` — **an epidemiology** · FR : Épidémiologie · AR : علم الوبائيات
- `gen:prevalence:v1` — **a prevalence** · FR : Prévalence · AR : الانتشار
- `gen:incidence:v1` — **an incidence** · FR : Incidence · AR : الوقوع (الحدوث)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : epidemiology ; we check epidemiology ; epidemiology first ; the doctor checks epidemiology first
  - modèle : First, the doctor checks epidemiology.
- **q2.** What does the doctor check next?
  - accepté : prevalence ; then we check prevalence ; prevalence next ; the doctor checks prevalence next
  - modèle : Then, the doctor checks prevalence.
- **q3.** Does the doctor check prevalence before epidemiology?
  - accepté : no ; no he does not ; no he doesn't ; he checks epidemiology first
  - modèle : No, he checks epidemiology first, then prevalence.
  - hint (si erreur) — FR : Attention : Épidémiologie vient D'ABORD, ensuite Prévalence.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : incidence ; incidence first ; he checks incidence first
  - modèle : First, the doctor checks incidence.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check epidemiology. Then, we check prevalence.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Incidence, ensuite Épidémiologie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check incidence. Then, we check epidemiology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-014` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Épidémiologie / Prévalence, lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the epidemiology.
   - FR : Le patient du lit 3 a Épidémiologie.
   - AR : مريض السرير ٣ عنده علم الوبائيات.
4. **EN** The patient in bed 5 has a problem with the prevalence.
   - FR : Le patient du lit 5 a Prévalence.
   - AR : مريض السرير ٥ عنده الانتشار.
5. **EN** The patient in bed 5 does not have a problem with the epidemiology.
   - FR : Le patient du lit 5 n'a pas de Épidémiologie.
   - AR : مريض السرير ٥ ليس عنده علم الوبائيات.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:epidemiology:v1` — **an epidemiology** · FR : Épidémiologie · AR : علم الوبائيات
- `gen:prevalence:v1` — **a prevalence** · FR : Prévalence · AR : الانتشار
- `gen:incidence:v1` — **an incidence** · FR : Incidence · AR : الوقوع (الحدوث)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : epidemiology ; an epidemiology ; the patient with epidemiology
  - modèle : The patient in bed 3 has an epidemiology.
- **q2.** What does the patient in bed 5 have?
  - accepté : prevalence ; a problem with the prevalence
  - modèle : He has a problem with the prevalence.
- **q3.** Does the patient in bed 5 have epidemiology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the epidemiology ; he doesn't
  - modèle : No, he does not have a problem with the epidemiology.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS épidémiologie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : incidence ; a problem with the incidence
  - modèle : He has a problem with the incidence.
- **Tt2.** Does the patient in bed 9 have prevalence?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the prevalence ; he doesn't
  - modèle : No, he does not have a problem with the prevalence.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS prévalence.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an epidemiology and a prevalence. (have)` → have · modèle : The patients have an epidemiology and a prevalence.
- `The patient in bed 5 … not have an epidemiology. (do)` → does · modèle : The patient in bed 5 does not have an epidemiology.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the epidemiology. The patient in bed 5 has a problem with the prevalence.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Incidence, le lit 9 a Épidémiologie et n'a pas Prévalence.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the incidence. The patient in bed 9 has a problem with the epidemiology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
