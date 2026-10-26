# Relecture — Appareil urogénital (anat)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anat-case-003` — Le cas de Amel : Cortex rénal anormal(e) / _Amel: the renal cortex is not normal_

**Objectif :** Comprendre des résultats : Système urinaire normal(e), Cortex rénal anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The urinary system is normal.”
   - FR : Le médecin dit : « Système urinaire est normal(e). »
   - AR : يقول الطبيب: «الجهاز البولي طبيعي/طبيعية.»
5. **EN** “But the renal cortex is not normal.”
   - FR : « Mais Cortex rénal n'est pas normal(e). »
   - AR : «لكن قشرة الكلية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the renal cortex normal?”
   - FR : Amine demande : « Cortex rénal est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل قشرة الكلية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:urinary-system:v1` — **an urinary system** · FR : Système urinaire · AR : الجهاز البولي
- `gen:kidney:v1` — **a kidney** · FR : Rein · AR : الكلیة
- `gen:renal-cortex:v1` — **a renal cortex** · FR : Cortex rénal · AR : قشرة الكلية

### Questions (acceptées / modèle / hint)
- **q1.** Is the urinary system normal?
  - accepté : yes ; yes it is ; the urinary system is normal ; it is normal
  - modèle : Yes, the urinary system is normal.
- **q2.** Is the renal cortex normal?
  - accepté : no ; no it is not ; no it isn't ; the renal cortex is not normal ; it is not normal ; it isn't normal
  - modèle : No, the renal cortex is not normal.
  - hint (si erreur) — FR : Attention : Cortex rénal n'est PAS normal(e).
- **q3.** Which one is not normal: the urinary system or the renal cortex?
  - accepté : renal cortex ; the renal cortex
  - modèle : The renal cortex is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the kidney normal?
  - accepté : yes ; yes it is ; the kidney is normal
  - modèle : Yes, the kidney is normal.
- **Tt2.** Is the urinary system normal?
  - accepté : no ; no it is not ; no it isn't ; the urinary system is not normal ; it is not normal
  - modèle : No, the urinary system is not normal.
  - hint (si erreur) — FR : Attention : Système urinaire n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The urinary system … normal. (be)` → is · modèle : The urinary system is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The urinary system is… / The renal cortex is not… — modèle : *The urinary system is normal. The renal cortex is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Rein normal(e), mais Système urinaire anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The kidney is normal. The urinary system is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anat-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Système urinaire et Rein à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient.
   - FR : Meriem est un·e patient·e.
   - AR : Meriem مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check urinary system.”
   - FR : « D'abord, nous vérifions Système urinaire. »
   - AR : «أولًا، نتحقق من الجهاز البولي.»
5. **EN** “Then, we check kidney.”
   - FR : « Ensuite, nous vérifions Rein. »
   - AR : «ثم نتحقق من الكلیة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:urinary-system:v1` — **an urinary system** · FR : Système urinaire · AR : الجهاز البولي
- `gen:kidney:v1` — **a kidney** · FR : Rein · AR : الكلیة
- `gen:renal-cortex:v1` — **a renal cortex** · FR : Cortex rénal · AR : قشرة الكلية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : urinary system ; we check urinary system ; urinary system first ; the doctor checks urinary system first
  - modèle : First, the doctor checks urinary system.
- **q2.** What does the doctor check next?
  - accepté : kidney ; then we check kidney ; kidney next ; the doctor checks kidney next
  - modèle : Then, the doctor checks kidney.
- **q3.** Does the doctor check kidney before urinary system?
  - accepté : no ; no he does not ; no he doesn't ; he checks urinary system first
  - modèle : No, he checks urinary system first, then kidney.
  - hint (si erreur) — FR : Attention : Système urinaire vient D'ABORD, ensuite Rein.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : renal cortex ; renal cortex first ; he checks renal cortex first
  - modèle : First, the doctor checks renal cortex.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check urinary system. Then, we check kidney.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Cortex rénal, ensuite Système urinaire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check renal cortex. Then, we check urinary system.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anat-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Système urinaire / Rein, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the urinary system.
   - FR : Le patient du lit 3 a Système urinaire.
   - AR : مريض السرير ٣ عنده الجهاز البولي.
4. **EN** The patient in bed 5 has a problem with the kidney.
   - FR : Le patient du lit 5 a Rein.
   - AR : مريض السرير ٥ عنده الكلیة.
5. **EN** The patient in bed 5 does not have a problem with the urinary system.
   - FR : Le patient du lit 5 n'a pas de Système urinaire.
   - AR : مريض السرير ٥ ليس عنده الجهاز البولي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:urinary-system:v1` — **an urinary system** · FR : Système urinaire · AR : الجهاز البولي
- `gen:kidney:v1` — **a kidney** · FR : Rein · AR : الكلیة
- `gen:renal-cortex:v1` — **a renal cortex** · FR : Cortex rénal · AR : قشرة الكلية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : urinary system ; an urinary system ; the patient with urinary system
  - modèle : The patient in bed 3 has an urinary system.
- **q2.** What does the patient in bed 5 have?
  - accepté : kidney ; a problem with the kidney
  - modèle : He has a problem with the kidney.
- **q3.** Does the patient in bed 5 have urinary system?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the urinary system ; he doesn't
  - modèle : No, he does not have a problem with the urinary system.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS système urinaire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : renal cortex ; a problem with the renal cortex
  - modèle : He has a problem with the renal cortex.
- **Tt2.** Does the patient in bed 9 have kidney?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the kidney ; he doesn't
  - modèle : No, he does not have a problem with the kidney.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS rein.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an urinary system and a kidney. (have)` → have · modèle : The patients have an urinary system and a kidney.
- `The patient in bed 5 … not have an urinary system. (do)` → does · modèle : The patient in bed 5 does not have an urinary system.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the urinary system. The patient in bed 5 has a problem with the kidney.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Cortex rénal, le lit 9 a Système urinaire et n'a pas Rein.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the renal cortex. The patient in bed 9 has a problem with the urinary system.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
