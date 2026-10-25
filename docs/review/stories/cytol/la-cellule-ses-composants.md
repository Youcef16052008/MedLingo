# Relecture — La Cellule & Ses Composants (cytol)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `cytol-case-002` — Le cas de Youssef : Cytoplasme anormal(e) / _Youssef: the cytoplasm is not normal_

**Objectif :** Comprendre des résultats : Cellule normal(e), Cytoplasme anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The cell is normal.”
   - FR : Le médecin dit : « Cellule est normal(e). »
   - AR : يقول الطبيب: «الخلية طبيعي/طبيعية.»
5. **EN** “But the cytoplasm is not normal.”
   - FR : « Mais Cytoplasme n'est pas normal(e). »
   - AR : «لكن الهيولى (السيتوبلازم) غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the cytoplasm normal?”
   - FR : Lina demande : « Cytoplasme est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الهيولى (السيتوبلازم) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:cell:v1` — **a cell** · FR : Cellule · AR : الخلية
- `gen:nucleus:v1` — **nucleus** · FR : Noyau · AR : النواة
- `gen:cytoplasm:v1` — **a cytoplasm** · FR : Cytoplasme · AR : الهيولى (السيتوبلازم)

### Questions (acceptées / modèle / hint)
- **q1.** Is the cell normal?
  - accepté : yes ; yes it is ; the cell is normal ; it is normal
  - modèle : Yes, the cell is normal.
- **q2.** Is the cytoplasm normal?
  - accepté : no ; no it is not ; no it isn't ; the cytoplasm is not normal ; it is not normal ; it isn't normal
  - modèle : No, the cytoplasm is not normal.
  - hint (si erreur) — FR : Attention : Cytoplasme n'est PAS normal(e).
- **q3.** Which one is not normal: the cell or the cytoplasm?
  - accepté : cytoplasm ; the cytoplasm
  - modèle : The cytoplasm is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the nucleus normal?
  - accepté : yes ; yes it is ; the nucleus is normal
  - modèle : Yes, the nucleus is normal.
- **Tt2.** Is the cell normal?
  - accepté : no ; no it is not ; no it isn't ; the cell is not normal ; it is not normal
  - modèle : No, the cell is not normal.
  - hint (si erreur) — FR : Attention : Cellule n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The cell … normal. (be)` → is · modèle : The cell is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The cell is… / The cytoplasm is not… — modèle : *The cell is normal. The cytoplasm is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Noyau normal(e), mais Cellule anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The nucleus is normal. The cell is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `cytol-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Cellule et Noyau à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check cell.”
   - FR : « D'abord, nous vérifions Cellule. »
   - AR : «أولًا، نتحقق من الخلية.»
5. **EN** “Then, we check nucleus.”
   - FR : « Ensuite, nous vérifions Noyau. »
   - AR : «ثم نتحقق من النواة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cell:v1` — **a cell** · FR : Cellule · AR : الخلية
- `gen:nucleus:v1` — **nucleus** · FR : Noyau · AR : النواة
- `gen:cytoplasm:v1` — **a cytoplasm** · FR : Cytoplasme · AR : الهيولى (السيتوبلازم)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cell ; we check cell ; cell first ; the doctor checks cell first
  - modèle : First, the doctor checks cell.
- **q2.** What does the doctor check next?
  - accepté : nucleus ; then we check nucleus ; nucleus next ; the doctor checks nucleus next
  - modèle : Then, the doctor checks nucleus.
- **q3.** Does the doctor check nucleus before cell?
  - accepté : no ; no he does not ; no he doesn't ; he checks cell first
  - modèle : No, he checks cell first, then nucleus.
  - hint (si erreur) — FR : Attention : Cellule vient D'ABORD, ensuite Noyau.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : cytoplasm ; cytoplasm first ; he checks cytoplasm first
  - modèle : First, the doctor checks cytoplasm.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cell. Then, we check nucleus.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Cytoplasme, ensuite Cellule, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check cytoplasm. Then, we check cell.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `cytol-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cellule / Noyau, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the cell.
   - FR : Le patient du lit 3 a Cellule.
   - AR : مريض السرير ٣ عنده الخلية.
4. **EN** The patient in bed 5 has a problem with the nucleus.
   - FR : Le patient du lit 5 a Noyau.
   - AR : مريض السرير ٥ عنده النواة.
5. **EN** The patient in bed 5 does not have a problem with the cell.
   - FR : Le patient du lit 5 n'a pas de Cellule.
   - AR : مريض السرير ٥ ليس عنده الخلية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:cell:v1` — **a cell** · FR : Cellule · AR : الخلية
- `gen:nucleus:v1` — **nucleus** · FR : Noyau · AR : النواة
- `gen:cytoplasm:v1` — **a cytoplasm** · FR : Cytoplasme · AR : الهيولى (السيتوبلازم)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cell ; a cell ; the patient with cell
  - modèle : The patient in bed 3 has a cell.
- **q2.** What does the patient in bed 5 have?
  - accepté : nucleus ; a problem with the nucleus
  - modèle : He has a problem with the nucleus.
- **q3.** Does the patient in bed 5 have cell?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the cell ; he doesn't
  - modèle : No, he does not have a problem with the cell.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cellule — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : cytoplasm ; a problem with the cytoplasm
  - modèle : He has a problem with the cytoplasm.
- **Tt2.** Does the patient in bed 9 have nucleus?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the nucleus ; he doesn't
  - modèle : No, he does not have a problem with the nucleus.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS noyau.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a cell and nucleus. (have)` → have · modèle : The patients have a cell and nucleus.
- `The patient in bed 5 … not have a cell. (do)` → does · modèle : The patient in bed 5 does not have a cell.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the cell. The patient in bed 5 has a problem with the nucleus.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Cytoplasme, le lit 9 a Cellule et n'a pas Noyau.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the cytoplasm. The patient in bed 9 has a problem with the cell.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
