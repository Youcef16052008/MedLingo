# Relecture — Résultats & Rapports Cytologiques (cytol)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `cytol-case-007` — Le cas de Hana : Cellules atypiques anormal(e) / _Hana: the atypical cells is not normal_

**Objectif :** Comprendre des résultats : Négatif pour malignité normal(e), Cellules atypiques anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient at the clinic.
   - FR : Hana est un·e patient·e à la clinique.
   - AR : Hana مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The negative for malignancy is normal.”
   - FR : Le médecin dit : « Négatif pour malignité est normal(e). »
   - AR : يقول الطبيب: «سلبي للخباثة طبيعي/طبيعية.»
5. **EN** “But the atypical cells is not normal.”
   - FR : « Mais Cellules atypiques n'est pas normal(e). »
   - AR : «لكن خلايا لانمطية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the atypical cells normal?”
   - FR : Amine demande : « Cellules atypiques est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل خلايا لانمطية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:negative-for-malignancy:v1` — **a negative for malignancy** · FR : Négatif pour malignité · AR : سلبي للخباثة
- `gen:positive-for-malignancy:v1` — **a positive for malignancy** · FR : Positif pour malignité · AR : إيجابي للخباثة
- `gen:atypical-cells:v1` — **atypical cells** · FR : Cellules atypiques · AR : خلايا لانمطية

### Questions (acceptées / modèle / hint)
- **q1.** Is the negative for malignancy normal?
  - accepté : yes ; yes it is ; the negative for malignancy is normal ; it is normal
  - modèle : Yes, the negative for malignancy is normal.
- **q2.** Is the atypical cells normal?
  - accepté : no ; no it is not ; no it isn't ; the atypical cells is not normal ; it is not normal ; it isn't normal
  - modèle : No, the atypical cells is not normal.
  - hint (si erreur) — FR : Attention : Cellules atypiques n'est PAS normal(e).
- **q3.** Which one is not normal: the negative for malignancy or the atypical cells?
  - accepté : atypical cells ; the atypical cells
  - modèle : The atypical cells is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the positive for malignancy normal?
  - accepté : yes ; yes it is ; the positive for malignancy is normal
  - modèle : Yes, the positive for malignancy is normal.
- **Tt2.** Is the negative for malignancy normal?
  - accepté : no ; no it is not ; no it isn't ; the negative for malignancy is not normal ; it is not normal
  - modèle : No, the negative for malignancy is not normal.
  - hint (si erreur) — FR : Attention : Négatif pour malignité n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The negative for malignancy … normal. (be)` → is · modèle : The negative for malignancy is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Hana en deux phrases. Garde la négation. — amorces : The negative for malignancy is… / The atypical cells is not… — modèle : *The negative for malignancy is normal. The atypical cells is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Positif pour malignité normal(e), mais Négatif pour malignité anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The positive for malignancy is normal. The negative for malignancy is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `cytol-explain-007` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Négatif pour malignité et Positif pour malignité à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient.
   - FR : Sara est un·e patient·e.
   - AR : Sara مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check negative for malignancy.”
   - FR : « D'abord, nous vérifions Négatif pour malignité. »
   - AR : «أولًا، نتحقق من سلبي للخباثة.»
5. **EN** “Then, we check positive for malignancy.”
   - FR : « Ensuite, nous vérifions Positif pour malignité. »
   - AR : «ثم نتحقق من إيجابي للخباثة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:negative-for-malignancy:v1` — **a negative for malignancy** · FR : Négatif pour malignité · AR : سلبي للخباثة
- `gen:positive-for-malignancy:v1` — **a positive for malignancy** · FR : Positif pour malignité · AR : إيجابي للخباثة
- `gen:atypical-cells:v1` — **atypical cells** · FR : Cellules atypiques · AR : خلايا لانمطية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : negative for malignancy ; we check negative for malignancy ; negative for malignancy first ; the doctor checks negative for malignancy first
  - modèle : First, the doctor checks negative for malignancy.
- **q2.** What does the doctor check next?
  - accepté : positive for malignancy ; then we check positive for malignancy ; positive for malignancy next ; the doctor checks positive for malignancy next
  - modèle : Then, the doctor checks positive for malignancy.
- **q3.** Does the doctor check positive for malignancy before negative for malignancy?
  - accepté : no ; no he does not ; no he doesn't ; he checks negative for malignancy first
  - modèle : No, he checks negative for malignancy first, then positive for malignancy.
  - hint (si erreur) — FR : Attention : Négatif pour malignité vient D'ABORD, ensuite Positif pour malignité.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : atypical cells ; atypical cells first ; he checks atypical cells first
  - modèle : First, the doctor checks atypical cells.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check negative for malignancy. Then, we check positive for malignancy.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Cellules atypiques, ensuite Négatif pour malignité, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check atypical cells. Then, we check negative for malignancy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `cytol-handover-007` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Négatif pour malignité / Positif pour malignité, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the negative for malignancy.
   - FR : Le patient du lit 3 a Négatif pour malignité.
   - AR : مريض السرير ٣ عنده سلبي للخباثة.
4. **EN** The patient in bed 5 has a problem with the positive for malignancy.
   - FR : Le patient du lit 5 a Positif pour malignité.
   - AR : مريض السرير ٥ عنده إيجابي للخباثة.
5. **EN** The patient in bed 5 does not have a problem with the negative for malignancy.
   - FR : Le patient du lit 5 n'a pas de Négatif pour malignité.
   - AR : مريض السرير ٥ ليس عنده سلبي للخباثة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:negative-for-malignancy:v1` — **a negative for malignancy** · FR : Négatif pour malignité · AR : سلبي للخباثة
- `gen:positive-for-malignancy:v1` — **a positive for malignancy** · FR : Positif pour malignité · AR : إيجابي للخباثة
- `gen:atypical-cells:v1` — **atypical cells** · FR : Cellules atypiques · AR : خلايا لانمطية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : negative for malignancy ; a negative for malignancy ; the patient with negative for malignancy
  - modèle : The patient in bed 3 has a negative for malignancy.
- **q2.** What does the patient in bed 5 have?
  - accepté : positive for malignancy ; a problem with the positive for malignancy
  - modèle : He has a problem with the positive for malignancy.
- **q3.** Does the patient in bed 5 have negative for malignancy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the negative for malignancy ; he doesn't
  - modèle : No, he does not have a problem with the negative for malignancy.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS négatif pour malignité — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : atypical cells ; a problem with the atypical cells
  - modèle : He has a problem with the atypical cells.
- **Tt2.** Does the patient in bed 9 have positive for malignancy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the positive for malignancy ; he doesn't
  - modèle : No, he does not have a problem with the positive for malignancy.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS positif pour malignité.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a negative for malignancy and a positive for malignancy. (have)` → have · modèle : The patients have a negative for malignancy and a positive for malignancy.
- `The patient in bed 5 … not have a negative for malignancy. (do)` → does · modèle : The patient in bed 5 does not have a negative for malignancy.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the negative for malignancy. The patient in bed 5 has a problem with the positive for malignancy.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Cellules atypiques, le lit 9 a Négatif pour malignité et n'a pas Positif pour malignité.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the atypical cells. The patient in bed 9 has a problem with the negative for malignancy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
