# Relecture — Medications & Pharmacy (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-021` — Le cas de Sofiane : Ordonnance / _Sofiane: Prescription_

**Objectif :** Comprendre un cas : ordonnance présent, pharmacie absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient at the clinic.
   - FR : Sofiane est un·e patient·e à la clinique.
   - AR : Sofiane مريض/مريضة في العيادة.
2. **EN** Sofiane talks to Amine, a medical student.
   - FR : Sofiane parle avec Amine, étudiant·e en médecine.
   - AR : Sofiane يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has a prescription.
   - FR : Il a Ordonnance.
   - AR : عنده وصفة طبية.
4. **EN** He does not have a pharmacy.
   - FR : Il n'a pas de Pharmacie.
   - AR : ليس عنده صيدلية.
5. **EN** Amine asks, “Do you have a pharmacy?”
   - FR : Amine demande : « Avez-vous Pharmacie ? »
   - AR : يسأل Amine: «هل عندك صيدلية؟»
6. **EN** Sofiane says, “No, I do not.”
   - FR : Sofiane répond : « Non. »
   - AR : يجيب Sofiane: «لا.»
7. **EN** Amine writes “Prescription” in the file.
   - FR : Amine écrit « Ordonnance » dans le dossier.
   - AR : يكتب Amine «Ordonnance» في الملف.

### Expressions ciblées
- `gen:prescription:v1` — **a prescription** · FR : Ordonnance · AR : وصفة طبية
- `gen:pharmacy:v1` — **a pharmacy** · FR : Pharmacie · AR : صيدلية
- `gen:pharmacist:v1` — **a pharmacist** · FR : Pharmacien(ne) · AR : صيدلاني

### Questions (acceptées / modèle / hint)
- **q1.** Does Sofiane have prescription?
  - accepté : yes ; yes he does ; yes he has a prescription ; he has a prescription
  - modèle : Yes, he has a prescription.
- **q2.** Does he have pharmacy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a pharmacy ; he doesn't
  - modèle : No, he does not have a pharmacy.
  - hint (si erreur) — FR : Attention : Sofiane n'a PAS pharmacie.
- **q3.** Does he have prescription or pharmacy?
  - accepté : prescription ; a prescription ; he has a prescription
  - modèle : He has a prescription.
- **q4.** What does Sofiane have?
  - accepté : prescription ; a prescription ; he has a prescription
  - modèle : He has a prescription.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have pharmacist?
  - accepté : yes ; yes he does ; he has a pharmacist
  - modèle : Yes, he has a pharmacist.
- **Tt2.** Does he have prescription?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a prescription ; he doesn't
  - modèle : No, he does not have a prescription.
  - hint (si erreur) — FR : Attention : il n'a PAS ordonnance.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … a prescription. (have)` → has · modèle : He has a prescription.
- `They … not have a pharmacy. (do)` → do · modèle : They do not have a pharmacy.
- `I … a prescription. (have)` → have · modèle : I have a prescription.

### Productions
- **Reformulation :** Rapporte le cas de Sofiane en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has a prescription. He does not have a pharmacy.*
- **Transfert (contexte) :** Un autre patient a Pharmacien(ne). Il n'a pas de Ordonnance.
- Glossaire : Pharmacien(ne) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a pharmacist. He does not have a prescription.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-021` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Ordonnance et Pharmacie à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check prescription.”
   - FR : « D'abord, nous vérifions Ordonnance. »
   - AR : «أولًا، نتحقق من وصفة طبية.»
5. **EN** “Then, we check pharmacy.”
   - FR : « Ensuite, nous vérifions Pharmacie. »
   - AR : «ثم نتحقق من صيدلية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:prescription:v1` — **a prescription** · FR : Ordonnance · AR : وصفة طبية
- `gen:pharmacy:v1` — **a pharmacy** · FR : Pharmacie · AR : صيدلية
- `gen:pharmacist:v1` — **a pharmacist** · FR : Pharmacien(ne) · AR : صيدلاني

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : prescription ; we check prescription ; prescription first ; the doctor checks prescription first
  - modèle : First, the doctor checks prescription.
- **q2.** What does the doctor check next?
  - accepté : pharmacy ; then we check pharmacy ; pharmacy next ; the doctor checks pharmacy next
  - modèle : Then, the doctor checks pharmacy.
- **q3.** Does the doctor check pharmacy before prescription?
  - accepté : no ; no he does not ; no he doesn't ; he checks prescription first
  - modèle : No, he checks prescription first, then pharmacy.
  - hint (si erreur) — FR : Attention : Ordonnance vient D'ABORD, ensuite Pharmacie.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : pharmacist ; pharmacist first ; he checks pharmacist first
  - modèle : First, the doctor checks pharmacist.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check prescription. Then, we check pharmacy.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Pharmacien(ne), ensuite Ordonnance, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check pharmacist. Then, we check prescription.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-021` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Ordonnance / Pharmacie, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a prescription.
   - FR : Le patient du lit 3 a Ordonnance.
   - AR : مريض السرير ٣ عنده وصفة طبية.
4. **EN** The patient in bed 5 has a pharmacy.
   - FR : Le patient du lit 5 a Pharmacie.
   - AR : مريض السرير ٥ عنده صيدلية.
5. **EN** The patient in bed 5 does not have a prescription.
   - FR : Le patient du lit 5 n'a pas de Ordonnance.
   - AR : مريض السرير ٥ ليس عنده وصفة طبية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:prescription:v1` — **a prescription** · FR : Ordonnance · AR : وصفة طبية
- `gen:pharmacy:v1` — **a pharmacy** · FR : Pharmacie · AR : صيدلية
- `gen:pharmacist:v1` — **a pharmacist** · FR : Pharmacien(ne) · AR : صيدلاني

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : prescription ; a prescription ; the patient with prescription
  - modèle : The patient in bed 3 has a prescription.
- **q2.** What does the patient in bed 5 have?
  - accepté : pharmacy ; a pharmacy ; he has a pharmacy
  - modèle : He has a pharmacy.
- **q3.** Does the patient in bed 5 have prescription?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a prescription ; he doesn't
  - modèle : No, he does not have a prescription.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS ordonnance — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : pharmacist ; a pharmacist ; he has a pharmacist
  - modèle : He has a pharmacist.
- **Tt2.** Does the patient in bed 9 have pharmacy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a pharmacy ; he doesn't
  - modèle : No, he does not have a pharmacy.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS pharmacie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a prescription and a pharmacy. (have)` → have · modèle : The patients have a prescription and a pharmacy.
- `The patient in bed 5 … not have a prescription. (do)` → does · modèle : The patient in bed 5 does not have a prescription.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a prescription. The patient in bed 5 has a pharmacy.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Pharmacien(ne), le lit 9 a Ordonnance et n'a pas Pharmacie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a pharmacist. The patient in bed 9 has a prescription.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
