# Relecture — Techniques Histologiques (histo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `histo-case-004` — Le cas de Farid : Inclusion (Enrobage) anormal(e) / _Farid: the embedding is not normal_

**Objectif :** Comprendre des résultats : Fixation normal(e), Inclusion (Enrobage) anormal(e).
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
4. **EN** The doctor says, “The fixation is normal.”
   - FR : Le médecin dit : « Fixation est normal(e). »
   - AR : يقول الطبيب: «التثبيت طبيعي/طبيعية.»
5. **EN** “But the embedding is not normal.”
   - FR : « Mais Inclusion (Enrobage) n'est pas normal(e). »
   - AR : «لكن التضمين (الطمر) غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the embedding normal?”
   - FR : Lina demande : « Inclusion (Enrobage) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل التضمين (الطمر) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:fixation:v1` — **a fixation** · FR : Fixation · AR : التثبيت
- `gen:formalin:v1` — **a formalin** · FR : Formol (Formaldéhyde) · AR : الفورمالين
- `gen:embedding:v1` — **an embedding** · FR : Inclusion (Enrobage) · AR : التضمين (الطمر)

### Questions (acceptées / modèle / hint)
- **q1.** Is the fixation normal?
  - accepté : yes ; yes it is ; the fixation is normal ; it is normal
  - modèle : Yes, the fixation is normal.
- **q2.** Is the embedding normal?
  - accepté : no ; no it is not ; no it isn't ; the embedding is not normal ; it is not normal ; it isn't normal
  - modèle : No, the embedding is not normal.
  - hint (si erreur) — FR : Attention : Inclusion (Enrobage) n'est PAS normal(e).
- **q3.** Which one is not normal: the fixation or the embedding?
  - accepté : embedding ; the embedding
  - modèle : The embedding is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the formalin normal?
  - accepté : yes ; yes it is ; the formalin is normal
  - modèle : Yes, the formalin is normal.
- **Tt2.** Is the fixation normal?
  - accepté : no ; no it is not ; no it isn't ; the fixation is not normal ; it is not normal
  - modèle : No, the fixation is not normal.
  - hint (si erreur) — FR : Attention : Fixation n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The fixation … normal. (be)` → is · modèle : The fixation is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The fixation is… / The embedding is not… — modèle : *The fixation is normal. The embedding is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Formol (Formaldéhyde) normal(e), mais Fixation anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The formalin is normal. The fixation is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `histo-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Fixation et Formol (Formaldéhyde) à un patient.
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
4. **EN** “First, we check fixation.”
   - FR : « D'abord, nous vérifions Fixation. »
   - AR : «أولًا، نتحقق من التثبيت.»
5. **EN** “Then, we check formalin.”
   - FR : « Ensuite, nous vérifions Formol (Formaldéhyde). »
   - AR : «ثم نتحقق من الفورمالين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:fixation:v1` — **a fixation** · FR : Fixation · AR : التثبيت
- `gen:formalin:v1` — **a formalin** · FR : Formol (Formaldéhyde) · AR : الفورمالين
- `gen:embedding:v1` — **an embedding** · FR : Inclusion (Enrobage) · AR : التضمين (الطمر)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : fixation ; we check fixation ; fixation first ; the doctor checks fixation first
  - modèle : First, the doctor checks fixation.
- **q2.** What does the doctor check next?
  - accepté : formalin ; then we check formalin ; formalin next ; the doctor checks formalin next
  - modèle : Then, the doctor checks formalin.
- **q3.** Does the doctor check formalin before fixation?
  - accepté : no ; no he does not ; no he doesn't ; he checks fixation first
  - modèle : No, he checks fixation first, then formalin.
  - hint (si erreur) — FR : Attention : Fixation vient D'ABORD, ensuite Formol (Formaldéhyde).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : embedding ; embedding first ; he checks embedding first
  - modèle : First, the doctor checks embedding.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check fixation. Then, we check formalin.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Inclusion (Enrobage), ensuite Fixation, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check embedding. Then, we check fixation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `histo-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Fixation / Formol (Formaldéhyde), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the fixation.
   - FR : Le patient du lit 3 a Fixation.
   - AR : مريض السرير ٣ عنده التثبيت.
4. **EN** The patient in bed 5 has a problem with the formalin.
   - FR : Le patient du lit 5 a Formol (Formaldéhyde).
   - AR : مريض السرير ٥ عنده الفورمالين.
5. **EN** The patient in bed 5 does not have a problem with the fixation.
   - FR : Le patient du lit 5 n'a pas de Fixation.
   - AR : مريض السرير ٥ ليس عنده التثبيت.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:fixation:v1` — **a fixation** · FR : Fixation · AR : التثبيت
- `gen:formalin:v1` — **a formalin** · FR : Formol (Formaldéhyde) · AR : الفورمالين
- `gen:embedding:v1` — **an embedding** · FR : Inclusion (Enrobage) · AR : التضمين (الطمر)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : fixation ; a fixation ; the patient with fixation
  - modèle : The patient in bed 3 has a fixation.
- **q2.** What does the patient in bed 5 have?
  - accepté : formalin ; a problem with the formalin
  - modèle : He has a problem with the formalin.
- **q3.** Does the patient in bed 5 have fixation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the fixation ; he doesn't
  - modèle : No, he does not have a problem with the fixation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS fixation — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : embedding ; a problem with the embedding
  - modèle : He has a problem with the embedding.
- **Tt2.** Does the patient in bed 9 have formalin?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the formalin ; he doesn't
  - modèle : No, he does not have a problem with the formalin.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS formol (formaldéhyde).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a fixation and a formalin. (have)` → have · modèle : The patients have a fixation and a formalin.
- `The patient in bed 5 … not have a fixation. (do)` → does · modèle : The patient in bed 5 does not have a fixation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the fixation. The patient in bed 5 has a problem with the formalin.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Inclusion (Enrobage), le lit 9 a Fixation et n'a pas Formol (Formaldéhyde).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the embedding. The patient in bed 9 has a problem with the fixation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
