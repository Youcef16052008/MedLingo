# Relecture — Types Cellulaires (cytol)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `cytol-case-011` — Le cas de Leila : Cellule cylindrique anormal(e) / _Leila: the columnar cell is not normal_

**Objectif :** Comprendre des résultats : Cellule épithéliale normal(e), Cellule cylindrique anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The epithelial cell is normal.”
   - FR : Le médecin dit : « Cellule épithéliale est normal(e). »
   - AR : يقول الطبيب: «الخلية الظهارية طبيعي/طبيعية.»
5. **EN** “But the columnar cell is not normal.”
   - FR : « Mais Cellule cylindrique n'est pas normal(e). »
   - AR : «لكن الخلية الأسطوانية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the columnar cell normal?”
   - FR : Amine demande : « Cellule cylindrique est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الخلية الأسطوانية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:epithelial-cell:v1` — **an epithelial cell** · FR : Cellule épithéliale · AR : الخلية الظهارية
- `gen:squamous-cell:v1` — **a squamous cell** · FR : Cellule squameuse · AR : الخلية الحرشفية
- `gen:columnar-cell:v1` — **a columnar cell** · FR : Cellule cylindrique · AR : الخلية الأسطوانية

### Questions (acceptées / modèle / hint)
- **q1.** Is the epithelial cell normal?
  - accepté : yes ; yes it is ; the epithelial cell is normal ; it is normal
  - modèle : Yes, the epithelial cell is normal.
- **q2.** Is the columnar cell normal?
  - accepté : no ; no it is not ; no it isn't ; the columnar cell is not normal ; it is not normal ; it isn't normal
  - modèle : No, the columnar cell is not normal.
  - hint (si erreur) — FR : Attention : Cellule cylindrique n'est PAS normal(e).
- **q3.** Which one is not normal: the epithelial cell or the columnar cell?
  - accepté : columnar cell ; the columnar cell
  - modèle : The columnar cell is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the squamous cell normal?
  - accepté : yes ; yes it is ; the squamous cell is normal
  - modèle : Yes, the squamous cell is normal.
- **Tt2.** Is the epithelial cell normal?
  - accepté : no ; no it is not ; no it isn't ; the epithelial cell is not normal ; it is not normal
  - modèle : No, the epithelial cell is not normal.
  - hint (si erreur) — FR : Attention : Cellule épithéliale n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The epithelial cell … normal. (be)` → is · modèle : The epithelial cell is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Leila en deux phrases. Garde la négation. — amorces : The epithelial cell is… / The columnar cell is not… — modèle : *The epithelial cell is normal. The columnar cell is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Cellule squameuse normal(e), mais Cellule épithéliale anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The squamous cell is normal. The epithelial cell is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `cytol-explain-011` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Cellule épithéliale et Cellule squameuse à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient.
   - FR : Nadia est un·e patient·e.
   - AR : Nadia مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check epithelial cell.”
   - FR : « D'abord, nous vérifions Cellule épithéliale. »
   - AR : «أولًا، نتحقق من الخلية الظهارية.»
5. **EN** “Then, we check squamous cell.”
   - FR : « Ensuite, nous vérifions Cellule squameuse. »
   - AR : «ثم نتحقق من الخلية الحرشفية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:epithelial-cell:v1` — **an epithelial cell** · FR : Cellule épithéliale · AR : الخلية الظهارية
- `gen:squamous-cell:v1` — **a squamous cell** · FR : Cellule squameuse · AR : الخلية الحرشفية
- `gen:columnar-cell:v1` — **a columnar cell** · FR : Cellule cylindrique · AR : الخلية الأسطوانية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : epithelial cell ; we check epithelial cell ; epithelial cell first ; the doctor checks epithelial cell first
  - modèle : First, the doctor checks epithelial cell.
- **q2.** What does the doctor check next?
  - accepté : squamous cell ; then we check squamous cell ; squamous cell next ; the doctor checks squamous cell next
  - modèle : Then, the doctor checks squamous cell.
- **q3.** Does the doctor check squamous cell before epithelial cell?
  - accepté : no ; no he does not ; no he doesn't ; he checks epithelial cell first
  - modèle : No, he checks epithelial cell first, then squamous cell.
  - hint (si erreur) — FR : Attention : Cellule épithéliale vient D'ABORD, ensuite Cellule squameuse.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : columnar cell ; columnar cell first ; he checks columnar cell first
  - modèle : First, the doctor checks columnar cell.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check epithelial cell. Then, we check squamous cell.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Cellule cylindrique, ensuite Cellule épithéliale, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check columnar cell. Then, we check epithelial cell.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `cytol-handover-011` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cellule épithéliale / Cellule squameuse, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the epithelial cell.
   - FR : Le patient du lit 3 a Cellule épithéliale.
   - AR : مريض السرير ٣ عنده الخلية الظهارية.
4. **EN** The patient in bed 5 has a problem with the squamous cell.
   - FR : Le patient du lit 5 a Cellule squameuse.
   - AR : مريض السرير ٥ عنده الخلية الحرشفية.
5. **EN** The patient in bed 5 does not have a problem with the epithelial cell.
   - FR : Le patient du lit 5 n'a pas de Cellule épithéliale.
   - AR : مريض السرير ٥ ليس عنده الخلية الظهارية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:epithelial-cell:v1` — **an epithelial cell** · FR : Cellule épithéliale · AR : الخلية الظهارية
- `gen:squamous-cell:v1` — **a squamous cell** · FR : Cellule squameuse · AR : الخلية الحرشفية
- `gen:columnar-cell:v1` — **a columnar cell** · FR : Cellule cylindrique · AR : الخلية الأسطوانية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : epithelial cell ; an epithelial cell ; the patient with epithelial cell
  - modèle : The patient in bed 3 has an epithelial cell.
- **q2.** What does the patient in bed 5 have?
  - accepté : squamous cell ; a problem with the squamous cell
  - modèle : He has a problem with the squamous cell.
- **q3.** Does the patient in bed 5 have epithelial cell?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the epithelial cell ; he doesn't
  - modèle : No, he does not have a problem with the epithelial cell.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cellule épithéliale — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : columnar cell ; a problem with the columnar cell
  - modèle : He has a problem with the columnar cell.
- **Tt2.** Does the patient in bed 9 have squamous cell?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the squamous cell ; he doesn't
  - modèle : No, he does not have a problem with the squamous cell.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS cellule squameuse.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an epithelial cell and a squamous cell. (have)` → have · modèle : The patients have an epithelial cell and a squamous cell.
- `The patient in bed 5 … not have an epithelial cell. (do)` → does · modèle : The patient in bed 5 does not have an epithelial cell.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the epithelial cell. The patient in bed 5 has a problem with the squamous cell.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Cellule cylindrique, le lit 9 a Cellule épithéliale et n'a pas Cellule squameuse.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the columnar cell. The patient in bed 9 has a problem with the epithelial cell.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
