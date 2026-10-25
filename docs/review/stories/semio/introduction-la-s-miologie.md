# Relecture — Introduction à la Sémiologie (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-001` — Le cas de Omar : Sémiologie / _Omar: Semiology_

**Objectif :** Comprendre un cas : sémiologie présent, symptôme absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Omar talks to Amine, a medical student.
   - FR : Omar parle avec Amine, étudiant·e en médecine.
   - AR : Omar يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has a semiology.
   - FR : Il a Sémiologie.
   - AR : عنده علم الأعراض.
4. **EN** He does not have a symptom.
   - FR : Il n'a pas de Symptôme.
   - AR : ليس عنده العَرَض.
5. **EN** Amine asks, “Do you have a symptom?”
   - FR : Amine demande : « Avez-vous Symptôme ? »
   - AR : يسأل Amine: «هل عندك العَرَض؟»
6. **EN** Omar says, “No, I do not.”
   - FR : Omar répond : « Non. »
   - AR : يجيب Omar: «لا.»
7. **EN** Amine writes “Semiology” in the file.
   - FR : Amine écrit « Sémiologie » dans le dossier.
   - AR : يكتب Amine «Sémiologie» في الملف.

### Expressions ciblées
- `gen:semiology:v1` — **a semiology** · FR : Sémiologie · AR : علم الأعراض
- `gen:symptom:v1` — **a symptom** · FR : Symptôme · AR : العَرَض
- `gen:sign:v1` — **a sign** · FR : Signe · AR : العلامة

### Questions (acceptées / modèle / hint)
- **q1.** Does Omar have semiology?
  - accepté : yes ; yes he does ; yes he has a semiology ; he has a semiology
  - modèle : Yes, he has a semiology.
- **q2.** Does he have symptom?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a symptom ; he doesn't
  - modèle : No, he does not have a symptom.
  - hint (si erreur) — FR : Attention : Omar n'a PAS symptôme.
- **q3.** Does he have semiology or symptom?
  - accepté : semiology ; a semiology ; he has a semiology
  - modèle : He has a semiology.
- **q4.** What does Omar have?
  - accepté : semiology ; a semiology ; he has a semiology
  - modèle : He has a semiology.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have sign?
  - accepté : yes ; yes he does ; he has a sign
  - modèle : Yes, he has a sign.
- **Tt2.** Does he have semiology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a semiology ; he doesn't
  - modèle : No, he does not have a semiology.
  - hint (si erreur) — FR : Attention : il n'a PAS sémiologie.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … a semiology. (have)` → has · modèle : He has a semiology.
- `They … not have a symptom. (do)` → do · modèle : They do not have a symptom.
- `I … a semiology. (have)` → have · modèle : I have a semiology.

### Productions
- **Reformulation :** Rapporte le cas de Omar en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has a semiology. He does not have a symptom.*
- **Transfert (contexte) :** Un autre patient a Signe. Il n'a pas de Sémiologie.
- Glossaire : Signe : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a sign. He does not have a semiology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Sémiologie et Symptôme à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient.
   - FR : Amel est un·e patient·e.
   - AR : Amel مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check semiology.”
   - FR : « D'abord, nous vérifions Sémiologie. »
   - AR : «أولًا، نتحقق من علم الأعراض.»
5. **EN** “Then, we check symptom.”
   - FR : « Ensuite, nous vérifions Symptôme. »
   - AR : «ثم نتحقق من العَرَض.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:semiology:v1` — **a semiology** · FR : Sémiologie · AR : علم الأعراض
- `gen:symptom:v1` — **a symptom** · FR : Symptôme · AR : العَرَض
- `gen:sign:v1` — **a sign** · FR : Signe · AR : العلامة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : semiology ; we check semiology ; semiology first ; the doctor checks semiology first
  - modèle : First, the doctor checks semiology.
- **q2.** What does the doctor check next?
  - accepté : symptom ; then we check symptom ; symptom next ; the doctor checks symptom next
  - modèle : Then, the doctor checks symptom.
- **q3.** Does the doctor check symptom before semiology?
  - accepté : no ; no he does not ; no he doesn't ; he checks semiology first
  - modèle : No, he checks semiology first, then symptom.
  - hint (si erreur) — FR : Attention : Sémiologie vient D'ABORD, ensuite Symptôme.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : sign ; sign first ; he checks sign first
  - modèle : First, the doctor checks sign.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check semiology. Then, we check symptom.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Signe, ensuite Sémiologie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check sign. Then, we check semiology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Sémiologie / Symptôme, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a semiology.
   - FR : Le patient du lit 3 a Sémiologie.
   - AR : مريض السرير ٣ عنده علم الأعراض.
4. **EN** The patient in bed 5 has a symptom.
   - FR : Le patient du lit 5 a Symptôme.
   - AR : مريض السرير ٥ عنده العَرَض.
5. **EN** The patient in bed 5 does not have a semiology.
   - FR : Le patient du lit 5 n'a pas de Sémiologie.
   - AR : مريض السرير ٥ ليس عنده علم الأعراض.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:semiology:v1` — **a semiology** · FR : Sémiologie · AR : علم الأعراض
- `gen:symptom:v1` — **a symptom** · FR : Symptôme · AR : العَرَض
- `gen:sign:v1` — **a sign** · FR : Signe · AR : العلامة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : semiology ; a semiology ; the patient with semiology
  - modèle : The patient in bed 3 has a semiology.
- **q2.** What does the patient in bed 5 have?
  - accepté : symptom ; a symptom ; he has a symptom
  - modèle : He has a symptom.
- **q3.** Does the patient in bed 5 have semiology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a semiology ; he doesn't
  - modèle : No, he does not have a semiology.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS sémiologie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : sign ; a sign ; he has a sign
  - modèle : He has a sign.
- **Tt2.** Does the patient in bed 9 have symptom?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a symptom ; he doesn't
  - modèle : No, he does not have a symptom.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS symptôme.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a semiology and a symptom. (have)` → have · modèle : The patients have a semiology and a symptom.
- `The patient in bed 5 … not have a semiology. (do)` → does · modèle : The patient in bed 5 does not have a semiology.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a semiology. The patient in bed 5 has a symptom.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Signe, le lit 9 a Sémiologie et n'a pas Symptôme.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a sign. The patient in bed 9 has a semiology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
