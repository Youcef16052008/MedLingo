# Relecture — Système Urinaire (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-034` — Le cas de Omar : Glomérule anormal(e) / _Omar: the glomerulus is not normal_

**Objectif :** Comprendre des résultats : Rein normal(e), Glomérule anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The kidney is normal.”
   - FR : Le médecin dit : « Rein est normal(e). »
   - AR : يقول الطبيب: «الكلية طبيعي/طبيعية.»
5. **EN** “But the glomerulus is not normal.”
   - FR : « Mais Glomérule n'est pas normal(e). »
   - AR : «لكن الكبيبة غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the glomerulus normal?”
   - FR : Lina demande : « Glomérule est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الكبيبة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:kidney:v1` — **a kidney** · FR : Rein · AR : الكلية
- `gen:nephron:v1` — **a nephron** · FR : Néphron · AR : النفرون
- `gen:glomerulus:v1` — **glomerulus** · FR : Glomérule · AR : الكبيبة

### Questions (acceptées / modèle / hint)
- **q1.** Is the kidney normal?
  - accepté : yes ; yes it is ; the kidney is normal ; it is normal
  - modèle : Yes, the kidney is normal.
- **q2.** Is the glomerulus normal?
  - accepté : no ; no it is not ; no it isn't ; the glomerulus is not normal ; it is not normal ; it isn't normal
  - modèle : No, the glomerulus is not normal.
  - hint (si erreur) — FR : Attention : Glomérule n'est PAS normal(e).
- **q3.** Which one is not normal: the kidney or the glomerulus?
  - accepté : glomerulus ; the glomerulus
  - modèle : The glomerulus is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the nephron normal?
  - accepté : yes ; yes it is ; the nephron is normal
  - modèle : Yes, the nephron is normal.
- **Tt2.** Is the kidney normal?
  - accepté : no ; no it is not ; no it isn't ; the kidney is not normal ; it is not normal
  - modèle : No, the kidney is not normal.
  - hint (si erreur) — FR : Attention : Rein n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The kidney … normal. (be)` → is · modèle : The kidney is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The kidney is… / The glomerulus is not… — modèle : *The kidney is normal. The glomerulus is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Néphron normal(e), mais Rein anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The nephron is normal. The kidney is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-034` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Rein et Néphron à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient.
   - FR : Karim est un·e patient·e.
   - AR : Karim مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check kidney.”
   - FR : « D'abord, nous vérifions Rein. »
   - AR : «أولًا، نتحقق من الكلية.»
5. **EN** “Then, we check nephron.”
   - FR : « Ensuite, nous vérifions Néphron. »
   - AR : «ثم نتحقق من النفرون.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:kidney:v1` — **a kidney** · FR : Rein · AR : الكلية
- `gen:nephron:v1` — **a nephron** · FR : Néphron · AR : النفرون
- `gen:glomerulus:v1` — **glomerulus** · FR : Glomérule · AR : الكبيبة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : kidney ; we check kidney ; kidney first ; the doctor checks kidney first
  - modèle : First, the doctor checks kidney.
- **q2.** What does the doctor check next?
  - accepté : nephron ; then we check nephron ; nephron next ; the doctor checks nephron next
  - modèle : Then, the doctor checks nephron.
- **q3.** Does the doctor check nephron before kidney?
  - accepté : no ; no he does not ; no he doesn't ; he checks kidney first
  - modèle : No, he checks kidney first, then nephron.
  - hint (si erreur) — FR : Attention : Rein vient D'ABORD, ensuite Néphron.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : glomerulus ; glomerulus first ; he checks glomerulus first
  - modèle : First, the doctor checks glomerulus.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check kidney. Then, we check nephron.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Glomérule, ensuite Rein, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check glomerulus. Then, we check kidney.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-034` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Rein / Néphron, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the kidney.
   - FR : Le patient du lit 3 a Rein.
   - AR : مريض السرير ٣ عنده الكلية.
4. **EN** The patient in bed 5 has a problem with the nephron.
   - FR : Le patient du lit 5 a Néphron.
   - AR : مريض السرير ٥ عنده النفرون.
5. **EN** The patient in bed 5 does not have a problem with the kidney.
   - FR : Le patient du lit 5 n'a pas de Rein.
   - AR : مريض السرير ٥ ليس عنده الكلية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:kidney:v1` — **a kidney** · FR : Rein · AR : الكلية
- `gen:nephron:v1` — **a nephron** · FR : Néphron · AR : النفرون
- `gen:glomerulus:v1` — **glomerulus** · FR : Glomérule · AR : الكبيبة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : kidney ; a kidney ; the patient with kidney
  - modèle : The patient in bed 3 has a kidney.
- **q2.** What does the patient in bed 5 have?
  - accepté : nephron ; a problem with the nephron
  - modèle : He has a problem with the nephron.
- **q3.** Does the patient in bed 5 have kidney?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the kidney ; he doesn't
  - modèle : No, he does not have a problem with the kidney.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS rein — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : glomerulus ; a problem with the glomerulus
  - modèle : He has a problem with the glomerulus.
- **Tt2.** Does the patient in bed 9 have nephron?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the nephron ; he doesn't
  - modèle : No, he does not have a problem with the nephron.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS néphron.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a kidney and a nephron. (have)` → have · modèle : The patients have a kidney and a nephron.
- `The patient in bed 5 … not have a kidney. (do)` → does · modèle : The patient in bed 5 does not have a kidney.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the kidney. The patient in bed 5 has a problem with the nephron.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Glomérule, le lit 9 a Rein et n'a pas Néphron.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the glomerulus. The patient in bed 9 has a problem with the kidney.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
