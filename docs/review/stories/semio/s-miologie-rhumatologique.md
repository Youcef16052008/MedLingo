# Relecture — Sémiologie Rhumatologique (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-012` — Le cas de Sara : Arthralgie / _Sara: Arthralgia_

**Objectif :** Comprendre un cas : arthralgie présent, arthrite absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Sara talks to Lina, a medical student.
   - FR : Sara parle avec Lina, étudiant·e en médecine.
   - AR : Sara يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has arthralgia.
   - FR : Elle a Arthralgie.
   - AR : عنده ألم المفصل.
4. **EN** She does not have arthritis.
   - FR : Elle n'a pas de Arthrite.
   - AR : ليس عنده التهاب المفصل.
5. **EN** Lina asks, “Do you have arthritis?”
   - FR : Lina demande : « Avez-vous Arthrite ? »
   - AR : يسأل Lina: «هل عندك التهاب المفصل؟»
6. **EN** Sara says, “No, I do not.”
   - FR : Sara répond : « Non. »
   - AR : يجيب Sara: «لا.»
7. **EN** Lina writes “Arthralgia” in the file.
   - FR : Lina écrit « Arthralgie » dans le dossier.
   - AR : يكتب Lina «Arthralgie» في الملف.

### Expressions ciblées
- `gen:arthralgia:v1` — **arthralgia** · FR : Arthralgie · AR : ألم المفصل
- `gen:arthritis:v1` — **arthritis** · FR : Arthrite · AR : التهاب المفصل
- `gen:morning-stiffness:v1` — **morning stiffness** · FR : Raideur matinale · AR : تيبس الصباح

### Questions (acceptées / modèle / hint)
- **q1.** Does Sara have arthralgia?
  - accepté : yes ; yes she does ; yes she has arthralgia ; she has arthralgia
  - modèle : Yes, she has arthralgia.
- **q2.** Does she have arthritis?
  - accepté : no ; no she does not ; no she doesn't ; she does not have arthritis ; she doesn't
  - modèle : No, she does not have arthritis.
  - hint (si erreur) — FR : Attention : Sara n'a PAS arthrite.
- **q3.** Does she have arthralgia or arthritis?
  - accepté : arthralgia ; she has arthralgia
  - modèle : She has arthralgia.
- **q4.** What does Sara have?
  - accepté : arthralgia ; she has arthralgia
  - modèle : She has arthralgia.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have morning stiffness?
  - accepté : yes ; yes he does ; he has morning stiffness
  - modèle : Yes, he has morning stiffness.
- **Tt2.** Does he have arthralgia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have arthralgia ; he doesn't
  - modèle : No, he does not have arthralgia.
  - hint (si erreur) — FR : Attention : il n'a PAS arthralgie.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … arthralgia. (have)` → has · modèle : She has arthralgia.
- `They … not have arthritis. (do)` → do · modèle : They do not have arthritis.
- `I … arthralgia. (have)` → have · modèle : I have arthralgia.

### Productions
- **Reformulation :** Rapporte le cas de Sara en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has arthralgia. She does not have arthritis.*
- **Transfert (contexte) :** Un autre patient a Raideur matinale. Il n'a pas de Arthralgie.
- Glossaire : Raideur matinale : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has morning stiffness. He does not have arthralgia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-012` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Arthralgie et Arthrite à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient.
   - FR : Youssef est un·e patient·e.
   - AR : Youssef مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check arthralgia.”
   - FR : « D'abord, nous vérifions Arthralgie. »
   - AR : «أولًا، نتحقق من ألم المفصل.»
5. **EN** “Then, we check arthritis.”
   - FR : « Ensuite, nous vérifions Arthrite. »
   - AR : «ثم نتحقق من التهاب المفصل.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:arthralgia:v1` — **arthralgia** · FR : Arthralgie · AR : ألم المفصل
- `gen:arthritis:v1` — **arthritis** · FR : Arthrite · AR : التهاب المفصل
- `gen:morning-stiffness:v1` — **morning stiffness** · FR : Raideur matinale · AR : تيبس الصباح

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : arthralgia ; we check arthralgia ; arthralgia first ; the doctor checks arthralgia first
  - modèle : First, the doctor checks arthralgia.
- **q2.** What does the doctor check next?
  - accepté : arthritis ; then we check arthritis ; arthritis next ; the doctor checks arthritis next
  - modèle : Then, the doctor checks arthritis.
- **q3.** Does the doctor check arthritis before arthralgia?
  - accepté : no ; no he does not ; no he doesn't ; he checks arthralgia first
  - modèle : No, he checks arthralgia first, then arthritis.
  - hint (si erreur) — FR : Attention : Arthralgie vient D'ABORD, ensuite Arthrite.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : morning stiffness ; morning stiffness first ; he checks morning stiffness first
  - modèle : First, the doctor checks morning stiffness.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check arthralgia. Then, we check arthritis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Raideur matinale, ensuite Arthralgie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check morning stiffness. Then, we check arthralgia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-012` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Arthralgie / Arthrite, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has arthralgia.
   - FR : Le patient du lit 3 a Arthralgie.
   - AR : مريض السرير ٣ عنده ألم المفصل.
4. **EN** The patient in bed 5 has arthritis.
   - FR : Le patient du lit 5 a Arthrite.
   - AR : مريض السرير ٥ عنده التهاب المفصل.
5. **EN** The patient in bed 5 does not have arthralgia.
   - FR : Le patient du lit 5 n'a pas de Arthralgie.
   - AR : مريض السرير ٥ ليس عنده ألم المفصل.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:arthralgia:v1` — **arthralgia** · FR : Arthralgie · AR : ألم المفصل
- `gen:arthritis:v1` — **arthritis** · FR : Arthrite · AR : التهاب المفصل
- `gen:morning-stiffness:v1` — **morning stiffness** · FR : Raideur matinale · AR : تيبس الصباح

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : arthralgia ; the patient with arthralgia
  - modèle : The patient in bed 3 has arthralgia.
- **q2.** What does the patient in bed 5 have?
  - accepté : arthritis
  - modèle : He has arthritis.
- **q3.** Does the patient in bed 5 have arthralgia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have arthralgia ; he doesn't
  - modèle : No, he does not have arthralgia.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS arthralgie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : morning stiffness
  - modèle : He has morning stiffness.
- **Tt2.** Does the patient in bed 9 have arthritis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have arthritis ; he doesn't
  - modèle : No, he does not have arthritis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS arthrite.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … arthralgia and arthritis. (have)` → have · modèle : The patients have arthralgia and arthritis.
- `The patient in bed 5 … not have arthralgia. (do)` → does · modèle : The patient in bed 5 does not have arthralgia.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has arthralgia. The patient in bed 5 has arthritis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Raideur matinale, le lit 9 a Arthralgie et n'a pas Arthrite.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has morning stiffness. The patient in bed 9 has arthralgia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
