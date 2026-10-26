# Relecture — Pathologie Rénale (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-009` — Le cas de Sofiane : Glomérulonéphrite / _Sofiane: Glomerulonephritis_

**Objectif :** Comprendre un cas : glomérulonéphrite présent, pyélonéphrite (pathologique) absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient at the clinic.
   - FR : Sofiane est un·e patient·e à la clinique.
   - AR : Sofiane مريض/مريضة في العيادة.
2. **EN** Sofiane talks to Amine, a medical student.
   - FR : Sofiane parle avec Amine, étudiant·e en médecine.
   - AR : Sofiane يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has glomerulonephritis.
   - FR : Il a Glomérulonéphrite.
   - AR : عنده التهاب الكبيبات الكلوية.
4. **EN** He does not have pyelonephritis.
   - FR : Il n'a pas de Pyélonéphrite (pathologique).
   - AR : ليس عنده التهاب الحويضة والكلية (مرضي).
5. **EN** Amine asks, “Do you have pyelonephritis?”
   - FR : Amine demande : « Avez-vous Pyélonéphrite (pathologique) ? »
   - AR : يسأل Amine: «هل عندك التهاب الحويضة والكلية (مرضي)؟»
6. **EN** Sofiane says, “No, I do not.”
   - FR : Sofiane répond : « Non. »
   - AR : يجيب Sofiane: «لا.»
7. **EN** Amine writes “Glomerulonephritis” in the file.
   - FR : Amine écrit « Glomérulonéphrite » dans le dossier.
   - AR : يكتب Amine «Glomérulonéphrite» في الملف.

### Expressions ciblées
- `gen:glomerulonephritis:v1` — **glomerulonephritis** · FR : Glomérulonéphrite · AR : التهاب الكبيبات الكلوية
- `gen:pyelonephritis:v1` — **pyelonephritis** · FR : Pyélonéphrite (pathologique) · AR : التهاب الحويضة والكلية (مرضي)
- `gen:nephrotic-syndrome:v1` — **a nephrotic syndrome** · FR : Syndrome néphrotique (pathologique) · AR : المتلازمة الكلائية (مرضي)

### Questions (acceptées / modèle / hint)
- **q1.** Does Sofiane have glomerulonephritis?
  - accepté : yes ; yes he does ; yes he has glomerulonephritis ; he has glomerulonephritis
  - modèle : Yes, he has glomerulonephritis.
- **q2.** Does he have pyelonephritis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have pyelonephritis ; he doesn't
  - modèle : No, he does not have pyelonephritis.
  - hint (si erreur) — FR : Attention : Sofiane n'a PAS pyélonéphrite (pathologique).
- **q3.** Does he have glomerulonephritis or pyelonephritis?
  - accepté : glomerulonephritis ; he has glomerulonephritis
  - modèle : He has glomerulonephritis.
- **q4.** What does Sofiane have?
  - accepté : glomerulonephritis ; he has glomerulonephritis
  - modèle : He has glomerulonephritis.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have nephrotic syndrome?
  - accepté : yes ; yes he does ; he has a nephrotic syndrome
  - modèle : Yes, he has a nephrotic syndrome.
- **Tt2.** Does he have glomerulonephritis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have glomerulonephritis ; he doesn't
  - modèle : No, he does not have glomerulonephritis.
  - hint (si erreur) — FR : Attention : il n'a PAS glomérulonéphrite.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … glomerulonephritis. (have)` → has · modèle : He has glomerulonephritis.
- `They … not have pyelonephritis. (do)` → do · modèle : They do not have pyelonephritis.
- `I … glomerulonephritis. (have)` → have · modèle : I have glomerulonephritis.

### Productions
- **Reformulation :** Rapporte le cas de Sofiane en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has glomerulonephritis. He does not have pyelonephritis.*
- **Transfert (contexte) :** Un autre patient a Syndrome néphrotique (pathologique). Il n'a pas de Glomérulonéphrite.
- Glossaire : Syndrome néphrotique (pathologique) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a nephrotic syndrome. He does not have glomerulonephritis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-009` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Glomérulonéphrite et Syndrome néphrotique (pathologique) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient.
   - FR : Leila est un·e patient·e.
   - AR : Leila مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check glomerulonephritis.”
   - FR : « D'abord, nous vérifions Glomérulonéphrite. »
   - AR : «أولًا، نتحقق من التهاب الكبيبات الكلوية.»
5. **EN** “Then, we check nephrotic syndrome.”
   - FR : « Ensuite, nous vérifions Syndrome néphrotique (pathologique). »
   - AR : «ثم نتحقق من المتلازمة الكلائية (مرضي).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:glomerulonephritis:v1` — **glomerulonephritis** · FR : Glomérulonéphrite · AR : التهاب الكبيبات الكلوية
- `gen:nephrotic-syndrome:v1` — **a nephrotic syndrome** · FR : Syndrome néphrotique (pathologique) · AR : المتلازمة الكلائية (مرضي)
- `gen:minimal-change-disease:v1` — **a minimal change disease** · FR : Néphrose lipoïdique (maladie à lésions minimes) · AR : مرض الآفات الشحمية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : glomerulonephritis ; we check glomerulonephritis ; glomerulonephritis first ; the doctor checks glomerulonephritis first
  - modèle : First, the doctor checks glomerulonephritis.
- **q2.** What does the doctor check next?
  - accepté : nephrotic syndrome ; then we check nephrotic syndrome ; nephrotic syndrome next ; the doctor checks nephrotic syndrome next
  - modèle : Then, the doctor checks nephrotic syndrome.
- **q3.** Does the doctor check nephrotic syndrome before glomerulonephritis?
  - accepté : no ; no he does not ; no he doesn't ; he checks glomerulonephritis first
  - modèle : No, he checks glomerulonephritis first, then nephrotic syndrome.
  - hint (si erreur) — FR : Attention : Glomérulonéphrite vient D'ABORD, ensuite Syndrome néphrotique (pathologique).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : minimal change disease ; minimal change disease first ; he checks minimal change disease first
  - modèle : First, the doctor checks minimal change disease.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check glomerulonephritis. Then, we check nephrotic syndrome.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Néphrose lipoïdique (maladie à lésions minimes), ensuite Glomérulonéphrite, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check minimal change disease. Then, we check glomerulonephritis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-009` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Glomérulonéphrite / Pyélonéphrite (pathologique), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has glomerulonephritis.
   - FR : Le patient du lit 3 a Glomérulonéphrite.
   - AR : مريض السرير ٣ عنده التهاب الكبيبات الكلوية.
4. **EN** The patient in bed 5 has pyelonephritis.
   - FR : Le patient du lit 5 a Pyélonéphrite (pathologique).
   - AR : مريض السرير ٥ عنده التهاب الحويضة والكلية (مرضي).
5. **EN** The patient in bed 5 does not have glomerulonephritis.
   - FR : Le patient du lit 5 n'a pas de Glomérulonéphrite.
   - AR : مريض السرير ٥ ليس عنده التهاب الكبيبات الكلوية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:glomerulonephritis:v1` — **glomerulonephritis** · FR : Glomérulonéphrite · AR : التهاب الكبيبات الكلوية
- `gen:pyelonephritis:v1` — **pyelonephritis** · FR : Pyélonéphrite (pathologique) · AR : التهاب الحويضة والكلية (مرضي)
- `gen:nephrotic-syndrome:v1` — **a nephrotic syndrome** · FR : Syndrome néphrotique (pathologique) · AR : المتلازمة الكلائية (مرضي)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : glomerulonephritis ; the patient with glomerulonephritis
  - modèle : The patient in bed 3 has glomerulonephritis.
- **q2.** What does the patient in bed 5 have?
  - accepté : pyelonephritis
  - modèle : He has pyelonephritis.
- **q3.** Does the patient in bed 5 have glomerulonephritis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have glomerulonephritis ; he doesn't
  - modèle : No, he does not have glomerulonephritis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS glomérulonéphrite — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : nephrotic syndrome ; a nephrotic syndrome
  - modèle : He has a nephrotic syndrome.
- **Tt2.** Does the patient in bed 9 have pyelonephritis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have pyelonephritis ; he doesn't
  - modèle : No, he does not have pyelonephritis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS pyélonéphrite (pathologique).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … glomerulonephritis and pyelonephritis. (have)` → have · modèle : The patients have glomerulonephritis and pyelonephritis.
- `The patient in bed 5 … not have glomerulonephritis. (do)` → does · modèle : The patient in bed 5 does not have glomerulonephritis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has glomerulonephritis. The patient in bed 5 has pyelonephritis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Syndrome néphrotique (pathologique), le lit 9 a Glomérulonéphrite et n'a pas Pyélonéphrite (pathologique).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a nephrotic syndrome. The patient in bed 9 has glomerulonephritis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
