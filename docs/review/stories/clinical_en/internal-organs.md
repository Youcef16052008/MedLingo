# Relecture — Internal Organs (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-017` — Le cas de Youssef : Cerveau / _Youssef: Brain_

**Objectif :** Comprendre un cas : cerveau présent, cœur absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Youssef talks to Amine, a medical student.
   - FR : Youssef parle avec Amine, étudiant·e en médecine.
   - AR : Youssef يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has a brain.
   - FR : Il a Cerveau.
   - AR : عنده الدماغ.
4. **EN** He does not have a heart.
   - FR : Il n'a pas de Cœur.
   - AR : ليس عنده القلب.
5. **EN** Amine asks, “Do you have a heart?”
   - FR : Amine demande : « Avez-vous Cœur ? »
   - AR : يسأل Amine: «هل عندك القلب؟»
6. **EN** Youssef says, “No, I do not.”
   - FR : Youssef répond : « Non. »
   - AR : يجيب Youssef: «لا.»
7. **EN** Amine writes “Brain” in the file.
   - FR : Amine écrit « Cerveau » dans le dossier.
   - AR : يكتب Amine «Cerveau» في الملف.

### Expressions ciblées
- `gen:brain:v1` — **a brain** · FR : Cerveau · AR : الدماغ
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب
- `gen:lungs:v1` — **lungs** · FR : Poumons · AR : الرئتان

### Questions (acceptées / modèle / hint)
- **q1.** Does Youssef have brain?
  - accepté : yes ; yes he does ; yes he has a brain ; he has a brain
  - modèle : Yes, he has a brain.
- **q2.** Does he have heart?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a heart ; he doesn't
  - modèle : No, he does not have a heart.
  - hint (si erreur) — FR : Attention : Youssef n'a PAS cœur.
- **q3.** Does he have brain or heart?
  - accepté : brain ; a brain ; he has a brain
  - modèle : He has a brain.
- **q4.** What does Youssef have?
  - accepté : brain ; a brain ; he has a brain
  - modèle : He has a brain.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have lungs?
  - accepté : yes ; yes he does ; he has lungs
  - modèle : Yes, he has lungs.
- **Tt2.** Does he have brain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a brain ; he doesn't
  - modèle : No, he does not have a brain.
  - hint (si erreur) — FR : Attention : il n'a PAS cerveau.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … a brain. (have)` → has · modèle : He has a brain.
- `They … not have a heart. (do)` → do · modèle : They do not have a heart.
- `I … a brain. (have)` → have · modèle : I have a brain.

### Productions
- **Reformulation :** Rapporte le cas de Youssef en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has a brain. He does not have a heart.*
- **Transfert (contexte) :** Un autre patient a Poumons. Il n'a pas de Cerveau.
- Glossaire : Poumons : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has lungs. He does not have a brain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-017` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Cerveau et Cœur à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient.
   - FR : Hana est un·e patient·e.
   - AR : Hana مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check brain.”
   - FR : « D'abord, nous vérifions Cerveau. »
   - AR : «أولًا، نتحقق من الدماغ.»
5. **EN** “Then, we check heart.”
   - FR : « Ensuite, nous vérifions Cœur. »
   - AR : «ثم نتحقق من القلب.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:brain:v1` — **a brain** · FR : Cerveau · AR : الدماغ
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب
- `gen:lungs:v1` — **lungs** · FR : Poumons · AR : الرئتان

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : brain ; we check brain ; brain first ; the doctor checks brain first
  - modèle : First, the doctor checks brain.
- **q2.** What does the doctor check next?
  - accepté : heart ; then we check heart ; heart next ; the doctor checks heart next
  - modèle : Then, the doctor checks heart.
- **q3.** Does the doctor check heart before brain?
  - accepté : no ; no he does not ; no he doesn't ; he checks brain first
  - modèle : No, he checks brain first, then heart.
  - hint (si erreur) — FR : Attention : Cerveau vient D'ABORD, ensuite Cœur.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : lungs ; lungs first ; he checks lungs first
  - modèle : First, the doctor checks lungs.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check brain. Then, we check heart.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Poumons, ensuite Cerveau, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check lungs. Then, we check brain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-017` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cerveau / Cœur, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a brain.
   - FR : Le patient du lit 3 a Cerveau.
   - AR : مريض السرير ٣ عنده الدماغ.
4. **EN** The patient in bed 5 has a heart.
   - FR : Le patient du lit 5 a Cœur.
   - AR : مريض السرير ٥ عنده القلب.
5. **EN** The patient in bed 5 does not have a brain.
   - FR : Le patient du lit 5 n'a pas de Cerveau.
   - AR : مريض السرير ٥ ليس عنده الدماغ.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:brain:v1` — **a brain** · FR : Cerveau · AR : الدماغ
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب
- `gen:lungs:v1` — **lungs** · FR : Poumons · AR : الرئتان

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : brain ; a brain ; the patient with brain
  - modèle : The patient in bed 3 has a brain.
- **q2.** What does the patient in bed 5 have?
  - accepté : heart ; a heart ; he has a heart
  - modèle : He has a heart.
- **q3.** Does the patient in bed 5 have brain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a brain ; he doesn't
  - modèle : No, he does not have a brain.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cerveau — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : lungs ; he has lungs
  - modèle : He has lungs.
- **Tt2.** Does the patient in bed 9 have heart?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a heart ; he doesn't
  - modèle : No, he does not have a heart.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS cœur.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a brain and a heart. (have)` → have · modèle : The patients have a brain and a heart.
- `The patient in bed 5 … not have a brain. (do)` → does · modèle : The patient in bed 5 does not have a brain.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a brain. The patient in bed 5 has a heart.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Poumons, le lit 9 a Cerveau et n'a pas Cœur.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has lungs. The patient in bed 9 has a brain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
