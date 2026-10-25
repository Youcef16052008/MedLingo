# Relecture — At the Hospital (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-001` — Le cas de Omar : Médecin / _Omar: Doctor_

**Objectif :** Comprendre un cas : médecin présent, infirmier/ière absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Omar talks to Amine, a medical student.
   - FR : Omar parle avec Amine, étudiant·e en médecine.
   - AR : Omar يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has a doctor.
   - FR : Il a Médecin.
   - AR : عنده طبيب.
4. **EN** He does not have a nurse.
   - FR : Il n'a pas de Infirmier/ière.
   - AR : ليس عنده ممرض/ة.
5. **EN** Amine asks, “Do you have a nurse?”
   - FR : Amine demande : « Avez-vous Infirmier/ière ? »
   - AR : يسأل Amine: «هل عندك ممرض/ة؟»
6. **EN** Omar says, “No, I do not.”
   - FR : Omar répond : « Non. »
   - AR : يجيب Omar: «لا.»
7. **EN** Amine writes “Doctor” in the file.
   - FR : Amine écrit « Médecin » dans le dossier.
   - AR : يكتب Amine «Médecin» في الملف.

### Expressions ciblées
- `gen:doctor:v1` — **a doctor** · FR : Médecin · AR : طبيب
- `gen:nurse:v1` — **a nurse** · FR : Infirmier/ière · AR : ممرض/ة
- `gen:patient:v1` — **a patient** · FR : Patient(e) · AR : مريض

### Questions (acceptées / modèle / hint)
- **q1.** Does Omar have doctor?
  - accepté : yes ; yes he does ; yes he has a doctor ; he has a doctor
  - modèle : Yes, he has a doctor.
- **q2.** Does he have nurse?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a nurse ; he doesn't
  - modèle : No, he does not have a nurse.
  - hint (si erreur) — FR : Attention : Omar n'a PAS infirmier/ière.
- **q3.** Does he have doctor or nurse?
  - accepté : doctor ; a doctor ; he has a doctor
  - modèle : He has a doctor.
- **q4.** What does Omar have?
  - accepté : doctor ; a doctor ; he has a doctor
  - modèle : He has a doctor.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have patient?
  - accepté : yes ; yes he does ; he has a patient
  - modèle : Yes, he has a patient.
- **Tt2.** Does he have doctor?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a doctor ; he doesn't
  - modèle : No, he does not have a doctor.
  - hint (si erreur) — FR : Attention : il n'a PAS médecin.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … a doctor. (have)` → has · modèle : He has a doctor.
- `They … not have a nurse. (do)` → do · modèle : They do not have a nurse.
- `I … a doctor. (have)` → have · modèle : I have a doctor.

### Productions
- **Reformulation :** Rapporte le cas de Omar en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has a doctor. He does not have a nurse.*
- **Transfert (contexte) :** Un autre patient a Patient(e). Il n'a pas de Médecin.
- Glossaire : Patient(e) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a patient. He does not have a doctor.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Médecin et Infirmier/ière à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check doctor.”
   - FR : « D'abord, nous vérifions Médecin. »
   - AR : «أولًا، نتحقق من طبيب.»
5. **EN** “Then, we check nurse.”
   - FR : « Ensuite, nous vérifions Infirmier/ière. »
   - AR : «ثم نتحقق من ممرض/ة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:doctor:v1` — **a doctor** · FR : Médecin · AR : طبيب
- `gen:nurse:v1` — **a nurse** · FR : Infirmier/ière · AR : ممرض/ة
- `gen:patient:v1` — **a patient** · FR : Patient(e) · AR : مريض

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : doctor ; we check doctor ; doctor first ; the doctor checks doctor first
  - modèle : First, the doctor checks doctor.
- **q2.** What does the doctor check next?
  - accepté : nurse ; then we check nurse ; nurse next ; the doctor checks nurse next
  - modèle : Then, the doctor checks nurse.
- **q3.** Does the doctor check nurse before doctor?
  - accepté : no ; no he does not ; no he doesn't ; he checks doctor first
  - modèle : No, he checks doctor first, then nurse.
  - hint (si erreur) — FR : Attention : Médecin vient D'ABORD, ensuite Infirmier/ière.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : patient ; patient first ; he checks patient first
  - modèle : First, the doctor checks patient.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check doctor. Then, we check nurse.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Patient(e), ensuite Médecin, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check patient. Then, we check doctor.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Médecin / Infirmier/ière, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a doctor.
   - FR : Le patient du lit 3 a Médecin.
   - AR : مريض السرير ٣ عنده طبيب.
4. **EN** The patient in bed 5 has a nurse.
   - FR : Le patient du lit 5 a Infirmier/ière.
   - AR : مريض السرير ٥ عنده ممرض/ة.
5. **EN** The patient in bed 5 does not have a doctor.
   - FR : Le patient du lit 5 n'a pas de Médecin.
   - AR : مريض السرير ٥ ليس عنده طبيب.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:doctor:v1` — **a doctor** · FR : Médecin · AR : طبيب
- `gen:nurse:v1` — **a nurse** · FR : Infirmier/ière · AR : ممرض/ة
- `gen:patient:v1` — **a patient** · FR : Patient(e) · AR : مريض

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : doctor ; a doctor ; the patient with doctor
  - modèle : The patient in bed 3 has a doctor.
- **q2.** What does the patient in bed 5 have?
  - accepté : nurse ; a nurse ; he has a nurse
  - modèle : He has a nurse.
- **q3.** Does the patient in bed 5 have doctor?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a doctor ; he doesn't
  - modèle : No, he does not have a doctor.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS médecin — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : patient ; a patient ; he has a patient
  - modèle : He has a patient.
- **Tt2.** Does the patient in bed 9 have nurse?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a nurse ; he doesn't
  - modèle : No, he does not have a nurse.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS infirmier/ière.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a doctor and a nurse. (have)` → have · modèle : The patients have a doctor and a nurse.
- `The patient in bed 5 … not have a doctor. (do)` → does · modèle : The patient in bed 5 does not have a doctor.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a doctor. The patient in bed 5 has a nurse.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Patient(e), le lit 9 a Médecin et n'a pas Infirmier/ière.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a patient. The patient in bed 9 has a doctor.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
