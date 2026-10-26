# Relecture — Lésions Cellulaires et Mort Cellulaire (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-004` — Le cas de Farid : Lésion irréversible anormal(e) / _Farid: the irreversible injury is not normal_

**Objectif :** Comprendre des résultats : Lésion cellulaire normal(e), Lésion irréversible anormal(e).
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
4. **EN** The doctor says, “The cell injury is normal.”
   - FR : Le médecin dit : « Lésion cellulaire est normal(e). »
   - AR : يقول الطبيب: «إصابة الخلية طبيعي/طبيعية.»
5. **EN** “But the irreversible injury is not normal.”
   - FR : « Mais Lésion irréversible n'est pas normal(e). »
   - AR : «لكن الإصابة غير العكوسة غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the irreversible injury normal?”
   - FR : Lina demande : « Lésion irréversible est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الإصابة غير العكوسة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:cell-injury:v1` — **a cell injury** · FR : Lésion cellulaire · AR : إصابة الخلية
- `gen:reversible-injury:v1` — **a reversible injury** · FR : Lésion réversible · AR : الإصابة العكوسة
- `gen:irreversible-injury:v1` — **an irreversible injury** · FR : Lésion irréversible · AR : الإصابة غير العكوسة

### Questions (acceptées / modèle / hint)
- **q1.** Is the cell injury normal?
  - accepté : yes ; yes it is ; the cell injury is normal ; it is normal
  - modèle : Yes, the cell injury is normal.
- **q2.** Is the irreversible injury normal?
  - accepté : no ; no it is not ; no it isn't ; the irreversible injury is not normal ; it is not normal ; it isn't normal
  - modèle : No, the irreversible injury is not normal.
  - hint (si erreur) — FR : Attention : Lésion irréversible n'est PAS normal(e).
- **q3.** Which one is not normal: the cell injury or the irreversible injury?
  - accepté : irreversible injury ; the irreversible injury
  - modèle : The irreversible injury is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the reversible injury normal?
  - accepté : yes ; yes it is ; the reversible injury is normal
  - modèle : Yes, the reversible injury is normal.
- **Tt2.** Is the cell injury normal?
  - accepté : no ; no it is not ; no it isn't ; the cell injury is not normal ; it is not normal
  - modèle : No, the cell injury is not normal.
  - hint (si erreur) — FR : Attention : Lésion cellulaire n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The cell injury … normal. (be)` → is · modèle : The cell injury is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The cell injury is… / The irreversible injury is not… — modèle : *The cell injury is normal. The irreversible injury is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Lésion réversible normal(e), mais Lésion cellulaire anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The reversible injury is normal. The cell injury is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Lésion cellulaire et Lésion réversible à un patient.
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
4. **EN** “First, we check cell injury.”
   - FR : « D'abord, nous vérifions Lésion cellulaire. »
   - AR : «أولًا، نتحقق من إصابة الخلية.»
5. **EN** “Then, we check reversible injury.”
   - FR : « Ensuite, nous vérifions Lésion réversible. »
   - AR : «ثم نتحقق من الإصابة العكوسة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cell-injury:v1` — **a cell injury** · FR : Lésion cellulaire · AR : إصابة الخلية
- `gen:reversible-injury:v1` — **a reversible injury** · FR : Lésion réversible · AR : الإصابة العكوسة
- `gen:irreversible-injury:v1` — **an irreversible injury** · FR : Lésion irréversible · AR : الإصابة غير العكوسة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cell injury ; we check cell injury ; cell injury first ; the doctor checks cell injury first
  - modèle : First, the doctor checks cell injury.
- **q2.** What does the doctor check next?
  - accepté : reversible injury ; then we check reversible injury ; reversible injury next ; the doctor checks reversible injury next
  - modèle : Then, the doctor checks reversible injury.
- **q3.** Does the doctor check reversible injury before cell injury?
  - accepté : no ; no he does not ; no he doesn't ; he checks cell injury first
  - modèle : No, he checks cell injury first, then reversible injury.
  - hint (si erreur) — FR : Attention : Lésion cellulaire vient D'ABORD, ensuite Lésion réversible.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : irreversible injury ; irreversible injury first ; he checks irreversible injury first
  - modèle : First, the doctor checks irreversible injury.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cell injury. Then, we check reversible injury.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Lésion irréversible, ensuite Lésion cellulaire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check irreversible injury. Then, we check cell injury.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Lésion cellulaire / Lésion réversible, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the cell injury.
   - FR : Le patient du lit 3 a Lésion cellulaire.
   - AR : مريض السرير ٣ عنده إصابة الخلية.
4. **EN** The patient in bed 5 has a problem with the reversible injury.
   - FR : Le patient du lit 5 a Lésion réversible.
   - AR : مريض السرير ٥ عنده الإصابة العكوسة.
5. **EN** The patient in bed 5 does not have a problem with the cell injury.
   - FR : Le patient du lit 5 n'a pas de Lésion cellulaire.
   - AR : مريض السرير ٥ ليس عنده إصابة الخلية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:cell-injury:v1` — **a cell injury** · FR : Lésion cellulaire · AR : إصابة الخلية
- `gen:reversible-injury:v1` — **a reversible injury** · FR : Lésion réversible · AR : الإصابة العكوسة
- `gen:irreversible-injury:v1` — **an irreversible injury** · FR : Lésion irréversible · AR : الإصابة غير العكوسة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cell injury ; a cell injury ; the patient with cell injury
  - modèle : The patient in bed 3 has a cell injury.
- **q2.** What does the patient in bed 5 have?
  - accepté : reversible injury ; a problem with the reversible injury
  - modèle : He has a problem with the reversible injury.
- **q3.** Does the patient in bed 5 have cell injury?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the cell injury ; he doesn't
  - modèle : No, he does not have a problem with the cell injury.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS lésion cellulaire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : irreversible injury ; a problem with the irreversible injury
  - modèle : He has a problem with the irreversible injury.
- **Tt2.** Does the patient in bed 9 have reversible injury?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the reversible injury ; he doesn't
  - modèle : No, he does not have a problem with the reversible injury.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS lésion réversible.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a cell injury and a reversible injury. (have)` → have · modèle : The patients have a cell injury and a reversible injury.
- `The patient in bed 5 … not have a cell injury. (do)` → does · modèle : The patient in bed 5 does not have a cell injury.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the cell injury. The patient in bed 5 has a problem with the reversible injury.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Lésion irréversible, le lit 9 a Lésion cellulaire et n'a pas Lésion réversible.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the irreversible injury. The patient in bed 9 has a problem with the cell injury.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
