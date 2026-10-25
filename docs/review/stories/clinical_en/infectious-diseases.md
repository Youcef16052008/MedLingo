# Relecture — Infectious Diseases (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-015` — Le cas de Karim : Fièvre typhoïde / _Karim: Typhoid fever_

**Objectif :** Comprendre un cas : fièvre typhoïde présent, dengue absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient at the clinic.
   - FR : Karim est un·e patient·e à la clinique.
   - AR : Karim مريض/مريضة في العيادة.
2. **EN** Karim talks to Amine, a medical student.
   - FR : Karim parle avec Amine, étudiant·e en médecine.
   - AR : Karim يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has typhoid fever.
   - FR : Il a Fièvre typhoïde.
   - AR : عنده حمى التيفود.
4. **EN** He does not have dengue fever.
   - FR : Il n'a pas de Dengue.
   - AR : ليس عنده حمى الضنك.
5. **EN** Amine asks, “Do you have dengue fever?”
   - FR : Amine demande : « Avez-vous Dengue ? »
   - AR : يسأل Amine: «هل عندك حمى الضنك؟»
6. **EN** Karim says, “No, I do not.”
   - FR : Karim répond : « Non. »
   - AR : يجيب Karim: «لا.»
7. **EN** Amine writes “Typhoid fever” in the file.
   - FR : Amine écrit « Fièvre typhoïde » dans le dossier.
   - AR : يكتب Amine «Fièvre typhoïde» في الملف.

### Expressions ciblées
- `gen:typhoid-fever:v1` — **typhoid fever** · FR : Fièvre typhoïde · AR : حمى التيفود
- `gen:dengue-fever:v1` — **dengue fever** · FR : Dengue · AR : حمى الضنك
- `gen:septicemia:v1` — **a septicemia** · FR : Septicémie · AR : تسمم الدم

### Questions (acceptées / modèle / hint)
- **q1.** Does Karim have typhoid fever?
  - accepté : yes ; yes he does ; yes he has typhoid fever ; he has typhoid fever
  - modèle : Yes, he has typhoid fever.
- **q2.** Does he have dengue fever?
  - accepté : no ; no he does not ; no he doesn't ; he does not have dengue fever ; he doesn't
  - modèle : No, he does not have dengue fever.
  - hint (si erreur) — FR : Attention : Karim n'a PAS dengue.
- **q3.** Does he have typhoid fever or dengue fever?
  - accepté : typhoid fever ; he has typhoid fever
  - modèle : He has typhoid fever.
- **q4.** What does Karim have?
  - accepté : typhoid fever ; he has typhoid fever
  - modèle : He has typhoid fever.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have septicemia?
  - accepté : yes ; yes he does ; he has a septicemia
  - modèle : Yes, he has a septicemia.
- **Tt2.** Does he have typhoid fever?
  - accepté : no ; no he does not ; no he doesn't ; he does not have typhoid fever ; he doesn't
  - modèle : No, he does not have typhoid fever.
  - hint (si erreur) — FR : Attention : il n'a PAS fièvre typhoïde.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … typhoid fever. (have)` → has · modèle : He has typhoid fever.
- `They … not have dengue fever. (do)` → do · modèle : They do not have dengue fever.
- `I … typhoid fever. (have)` → have · modèle : I have typhoid fever.

### Productions
- **Reformulation :** Rapporte le cas de Karim en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has typhoid fever. He does not have dengue fever.*
- **Transfert (contexte) :** Un autre patient a Septicémie. Il n'a pas de Fièvre typhoïde.
- Glossaire : Septicémie : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a septicemia. He does not have typhoid fever.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-015` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Septicémie et Méningococcie à un patient.
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
4. **EN** “First, we check septicemia.”
   - FR : « D'abord, nous vérifions Septicémie. »
   - AR : «أولًا، نتحقق من تسمم الدم.»
5. **EN** “Then, we check meningococcal disease.”
   - FR : « Ensuite, nous vérifions Méningococcie. »
   - AR : «ثم نتحقق من المرض المكوري السحائي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:septicemia:v1` — **a septicemia** · FR : Septicémie · AR : تسمم الدم
- `gen:meningococcal-disease:v1` — **a meningococcal disease** · FR : Méningococcie · AR : المرض المكوري السحائي
- `gen:typhoid-fever:v1` — **typhoid fever** · FR : Fièvre typhoïde · AR : حمى التيفود

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : septicemia ; we check septicemia ; septicemia first ; the doctor checks septicemia first
  - modèle : First, the doctor checks septicemia.
- **q2.** What does the doctor check next?
  - accepté : meningococcal disease ; then we check meningococcal disease ; meningococcal disease next ; the doctor checks meningococcal disease next
  - modèle : Then, the doctor checks meningococcal disease.
- **q3.** Does the doctor check meningococcal disease before septicemia?
  - accepté : no ; no he does not ; no he doesn't ; he checks septicemia first
  - modèle : No, he checks septicemia first, then meningococcal disease.
  - hint (si erreur) — FR : Attention : Septicémie vient D'ABORD, ensuite Méningococcie.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : typhoid fever ; typhoid fever first ; he checks typhoid fever first
  - modèle : First, the doctor checks typhoid fever.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check septicemia. Then, we check meningococcal disease.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fièvre typhoïde, ensuite Septicémie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check typhoid fever. Then, we check septicemia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-015` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Fièvre typhoïde / Dengue, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has typhoid fever.
   - FR : Le patient du lit 3 a Fièvre typhoïde.
   - AR : مريض السرير ٣ عنده حمى التيفود.
4. **EN** The patient in bed 5 has dengue fever.
   - FR : Le patient du lit 5 a Dengue.
   - AR : مريض السرير ٥ عنده حمى الضنك.
5. **EN** The patient in bed 5 does not have typhoid fever.
   - FR : Le patient du lit 5 n'a pas de Fièvre typhoïde.
   - AR : مريض السرير ٥ ليس عنده حمى التيفود.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:typhoid-fever:v1` — **typhoid fever** · FR : Fièvre typhoïde · AR : حمى التيفود
- `gen:dengue-fever:v1` — **dengue fever** · FR : Dengue · AR : حمى الضنك
- `gen:septicemia:v1` — **a septicemia** · FR : Septicémie · AR : تسمم الدم

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : typhoid fever ; the patient with typhoid fever
  - modèle : The patient in bed 3 has typhoid fever.
- **q2.** What does the patient in bed 5 have?
  - accepté : dengue fever ; he has dengue fever
  - modèle : He has dengue fever.
- **q3.** Does the patient in bed 5 have typhoid fever?
  - accepté : no ; no he does not ; no he doesn't ; he does not have typhoid fever ; he doesn't
  - modèle : No, he does not have typhoid fever.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS fièvre typhoïde — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : septicemia ; a septicemia ; he has a septicemia
  - modèle : He has a septicemia.
- **Tt2.** Does the patient in bed 9 have dengue fever?
  - accepté : no ; no he does not ; no he doesn't ; he does not have dengue fever ; he doesn't
  - modèle : No, he does not have dengue fever.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS dengue.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … typhoid fever and dengue fever. (have)` → have · modèle : The patients have typhoid fever and dengue fever.
- `The patient in bed 5 … not have typhoid fever. (do)` → does · modèle : The patient in bed 5 does not have typhoid fever.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has typhoid fever. The patient in bed 5 has dengue fever.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Septicémie, le lit 9 a Fièvre typhoïde et n'a pas Dengue.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a septicemia. The patient in bed 9 has typhoid fever.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
