# Relecture — Body Parts (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-003` — Le cas de Karim : Tête / _Karim: Head_

**Objectif :** Comprendre un cas : tête présent, cou absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient at the clinic.
   - FR : Karim est un·e patient·e à la clinique.
   - AR : Karim مريض/مريضة في العيادة.
2. **EN** Karim talks to Amine, a medical student.
   - FR : Karim parle avec Amine, étudiant·e en médecine.
   - AR : Karim يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has a head.
   - FR : Il a Tête.
   - AR : عنده الرأس.
4. **EN** He does not have a neck.
   - FR : Il n'a pas de Cou.
   - AR : ليس عنده الرقبة.
5. **EN** Amine asks, “Do you have a neck?”
   - FR : Amine demande : « Avez-vous Cou ? »
   - AR : يسأل Amine: «هل عندك الرقبة؟»
6. **EN** Karim says, “No, I do not.”
   - FR : Karim répond : « Non. »
   - AR : يجيب Karim: «لا.»
7. **EN** Amine writes “Head” in the file.
   - FR : Amine écrit « Tête » dans le dossier.
   - AR : يكتب Amine «Tête» في الملف.

### Expressions ciblées
- `gen:head:v1` — **a head** · FR : Tête · AR : الرأس
- `gen:neck:v1` — **a neck** · FR : Cou · AR : الرقبة
- `gen:chest:v1` — **a chest** · FR : Poitrine · AR : الصدر

### Questions (acceptées / modèle / hint)
- **q1.** Does Karim have head?
  - accepté : yes ; yes he does ; yes he has a head ; he has a head
  - modèle : Yes, he has a head.
- **q2.** Does he have neck?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a neck ; he doesn't
  - modèle : No, he does not have a neck.
  - hint (si erreur) — FR : Attention : Karim n'a PAS cou.
- **q3.** Does he have head or neck?
  - accepté : head ; a head ; he has a head
  - modèle : He has a head.
- **q4.** What does Karim have?
  - accepté : head ; a head ; he has a head
  - modèle : He has a head.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have chest?
  - accepté : yes ; yes he does ; he has a chest
  - modèle : Yes, he has a chest.
- **Tt2.** Does he have head?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a head ; he doesn't
  - modèle : No, he does not have a head.
  - hint (si erreur) — FR : Attention : il n'a PAS tête.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … a head. (have)` → has · modèle : He has a head.
- `They … not have a neck. (do)` → do · modèle : They do not have a neck.
- `I … a head. (have)` → have · modèle : I have a head.

### Productions
- **Reformulation :** Rapporte le cas de Karim en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has a head. He does not have a neck.*
- **Transfert (contexte) :** Un autre patient a Poitrine. Il n'a pas de Tête.
- Glossaire : Poitrine : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a chest. He does not have a head.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Tête et Cou à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check head.”
   - FR : « D'abord, nous vérifions Tête. »
   - AR : «أولًا، نتحقق من الرأس.»
5. **EN** “Then, we check neck.”
   - FR : « Ensuite, nous vérifions Cou. »
   - AR : «ثم نتحقق من الرقبة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:head:v1` — **a head** · FR : Tête · AR : الرأس
- `gen:neck:v1` — **a neck** · FR : Cou · AR : الرقبة
- `gen:chest:v1` — **a chest** · FR : Poitrine · AR : الصدر

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : head ; we check head ; head first ; the doctor checks head first
  - modèle : First, the doctor checks head.
- **q2.** What does the doctor check next?
  - accepté : neck ; then we check neck ; neck next ; the doctor checks neck next
  - modèle : Then, the doctor checks neck.
- **q3.** Does the doctor check neck before head?
  - accepté : no ; no he does not ; no he doesn't ; he checks head first
  - modèle : No, he checks head first, then neck.
  - hint (si erreur) — FR : Attention : Tête vient D'ABORD, ensuite Cou.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : chest ; chest first ; he checks chest first
  - modèle : First, the doctor checks chest.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check head. Then, we check neck.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Poitrine, ensuite Tête, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check chest. Then, we check head.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Tête / Cou, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a head.
   - FR : Le patient du lit 3 a Tête.
   - AR : مريض السرير ٣ عنده الرأس.
4. **EN** The patient in bed 5 has a neck.
   - FR : Le patient du lit 5 a Cou.
   - AR : مريض السرير ٥ عنده الرقبة.
5. **EN** The patient in bed 5 does not have a head.
   - FR : Le patient du lit 5 n'a pas de Tête.
   - AR : مريض السرير ٥ ليس عنده الرأس.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:head:v1` — **a head** · FR : Tête · AR : الرأس
- `gen:neck:v1` — **a neck** · FR : Cou · AR : الرقبة
- `gen:chest:v1` — **a chest** · FR : Poitrine · AR : الصدر

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : head ; a head ; the patient with head
  - modèle : The patient in bed 3 has a head.
- **q2.** What does the patient in bed 5 have?
  - accepté : neck ; a neck ; he has a neck
  - modèle : He has a neck.
- **q3.** Does the patient in bed 5 have head?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a head ; he doesn't
  - modèle : No, he does not have a head.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS tête — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : chest ; a chest ; he has a chest
  - modèle : He has a chest.
- **Tt2.** Does the patient in bed 9 have neck?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a neck ; he doesn't
  - modèle : No, he does not have a neck.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS cou.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a head and a neck. (have)` → have · modèle : The patients have a head and a neck.
- `The patient in bed 5 … not have a head. (do)` → does · modèle : The patient in bed 5 does not have a head.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a head. The patient in bed 5 has a neck.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Poitrine, le lit 9 a Tête et n'a pas Cou.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a chest. The patient in bed 9 has a head.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
