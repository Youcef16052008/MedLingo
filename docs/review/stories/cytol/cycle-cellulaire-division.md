# Relecture — Cycle Cellulaire & Division (cytol)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `cytol-case-001` — Le cas de Nadia : Phase G1 anormal(e) / _Nadia: the G1 phase is not normal_

**Objectif :** Comprendre des résultats : Cycle cellulaire normal(e), Phase G1 anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The cell cycle is normal.”
   - FR : Le médecin dit : « Cycle cellulaire est normal(e). »
   - AR : يقول الطبيب: «دورة الخلية طبيعي/طبيعية.»
5. **EN** “But the G1 phase is not normal.”
   - FR : « Mais Phase G1 n'est pas normal(e). »
   - AR : «لكن طور G1 (ما قبل التضاعف) غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the G1 phase normal?”
   - FR : Amine demande : « Phase G1 est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل طور G1 (ما قبل التضاعف) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:cell-cycle:v1` — **a cell cycle** · FR : Cycle cellulaire · AR : دورة الخلية
- `gen:interphase:v1` — **an interphase** · FR : Interphase · AR : الطور البيني
- `gen:g1-phase:v1` — **a G1 phase** · FR : Phase G1 · AR : طور G1 (ما قبل التضاعف)

### Questions (acceptées / modèle / hint)
- **q1.** Is the cell cycle normal?
  - accepté : yes ; yes it is ; the cell cycle is normal ; it is normal
  - modèle : Yes, the cell cycle is normal.
- **q2.** Is the G1 phase normal?
  - accepté : no ; no it is not ; no it isn't ; the G1 phase is not normal ; it is not normal ; it isn't normal
  - modèle : No, the G1 phase is not normal.
  - hint (si erreur) — FR : Attention : Phase G1 n'est PAS normal(e).
- **q3.** Which one is not normal: the cell cycle or the G1 phase?
  - accepté : G1 phase ; the G1 phase
  - modèle : The G1 phase is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the interphase normal?
  - accepté : yes ; yes it is ; the interphase is normal
  - modèle : Yes, the interphase is normal.
- **Tt2.** Is the cell cycle normal?
  - accepté : no ; no it is not ; no it isn't ; the cell cycle is not normal ; it is not normal
  - modèle : No, the cell cycle is not normal.
  - hint (si erreur) — FR : Attention : Cycle cellulaire n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The cell cycle … normal. (be)` → is · modèle : The cell cycle is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The cell cycle is… / The G1 phase is not… — modèle : *The cell cycle is normal. The G1 phase is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Interphase normal(e), mais Cycle cellulaire anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The interphase is normal. The cell cycle is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `cytol-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Cycle cellulaire et Interphase à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check cell cycle.”
   - FR : « D'abord, nous vérifions Cycle cellulaire. »
   - AR : «أولًا، نتحقق من دورة الخلية.»
5. **EN** “Then, we check interphase.”
   - FR : « Ensuite, nous vérifions Interphase. »
   - AR : «ثم نتحقق من الطور البيني.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cell-cycle:v1` — **a cell cycle** · FR : Cycle cellulaire · AR : دورة الخلية
- `gen:interphase:v1` — **an interphase** · FR : Interphase · AR : الطور البيني
- `gen:g1-phase:v1` — **a G1 phase** · FR : Phase G1 · AR : طور G1 (ما قبل التضاعف)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cell cycle ; we check cell cycle ; cell cycle first ; the doctor checks cell cycle first
  - modèle : First, the doctor checks cell cycle.
- **q2.** What does the doctor check next?
  - accepté : interphase ; then we check interphase ; interphase next ; the doctor checks interphase next
  - modèle : Then, the doctor checks interphase.
- **q3.** Does the doctor check interphase before cell cycle?
  - accepté : no ; no he does not ; no he doesn't ; he checks cell cycle first
  - modèle : No, he checks cell cycle first, then interphase.
  - hint (si erreur) — FR : Attention : Cycle cellulaire vient D'ABORD, ensuite Interphase.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : G1 phase ; G1 phase first ; he checks G1 phase first
  - modèle : First, the doctor checks G1 phase.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cell cycle. Then, we check interphase.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Phase G1, ensuite Cycle cellulaire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check G1 phase. Then, we check cell cycle.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `cytol-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cycle cellulaire / Interphase, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the cell cycle.
   - FR : Le patient du lit 3 a Cycle cellulaire.
   - AR : مريض السرير ٣ عنده دورة الخلية.
4. **EN** The patient in bed 5 has a problem with the interphase.
   - FR : Le patient du lit 5 a Interphase.
   - AR : مريض السرير ٥ عنده الطور البيني.
5. **EN** The patient in bed 5 does not have a problem with the cell cycle.
   - FR : Le patient du lit 5 n'a pas de Cycle cellulaire.
   - AR : مريض السرير ٥ ليس عنده دورة الخلية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:cell-cycle:v1` — **a cell cycle** · FR : Cycle cellulaire · AR : دورة الخلية
- `gen:interphase:v1` — **an interphase** · FR : Interphase · AR : الطور البيني
- `gen:g1-phase:v1` — **a G1 phase** · FR : Phase G1 · AR : طور G1 (ما قبل التضاعف)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cell cycle ; a cell cycle ; the patient with cell cycle
  - modèle : The patient in bed 3 has a cell cycle.
- **q2.** What does the patient in bed 5 have?
  - accepté : interphase ; a problem with the interphase
  - modèle : He has a problem with the interphase.
- **q3.** Does the patient in bed 5 have cell cycle?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the cell cycle ; he doesn't
  - modèle : No, he does not have a problem with the cell cycle.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cycle cellulaire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : G1 phase ; a problem with the G1 phase
  - modèle : He has a problem with the G1 phase.
- **Tt2.** Does the patient in bed 9 have interphase?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the interphase ; he doesn't
  - modèle : No, he does not have a problem with the interphase.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS interphase.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a cell cycle and an interphase. (have)` → have · modèle : The patients have a cell cycle and an interphase.
- `The patient in bed 5 … not have a cell cycle. (do)` → does · modèle : The patient in bed 5 does not have a cell cycle.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the cell cycle. The patient in bed 5 has a problem with the interphase.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Phase G1, le lit 9 a Cycle cellulaire et n'a pas Interphase.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the G1 phase. The patient in bed 9 has a problem with the cell cycle.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
