# Relecture — At the Hospital (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-001` — Le cas de Nadia : Patient(e) anormal(e) / _Nadia: the patient is not normal_

**Objectif :** Comprendre des résultats : Médecin normal(e), Patient(e) anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The doctor is normal.”
   - FR : Le médecin dit : « Médecin est normal(e). »
   - AR : يقول الطبيب: «طبيب طبيعي/طبيعية.»
5. **EN** “But the patient is not normal.”
   - FR : « Mais Patient(e) n'est pas normal(e). »
   - AR : «لكن مريض غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the patient normal?”
   - FR : Amine demande : « Patient(e) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل مريض طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:doctor:v1` — **a doctor** · FR : Médecin · AR : طبيب
- `gen:nurse:v1` — **a nurse** · FR : Infirmier/ière · AR : ممرض/ة
- `gen:patient:v1` — **a patient** · FR : Patient(e) · AR : مريض

### Questions (acceptées / modèle / hint)
- **q1.** Is the doctor normal?
  - accepté : yes ; yes it is ; the doctor is normal ; it is normal
  - modèle : Yes, the doctor is normal.
- **q2.** Is the patient normal?
  - accepté : no ; no it is not ; no it isn't ; the patient is not normal ; it is not normal ; it isn't normal
  - modèle : No, the patient is not normal.
  - hint (si erreur) — FR : Attention : Patient(e) n'est PAS normal(e).
- **q3.** Which one is not normal: the doctor or the patient?
  - accepté : patient ; the patient
  - modèle : The patient is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the nurse normal?
  - accepté : yes ; yes it is ; the nurse is normal
  - modèle : Yes, the nurse is normal.
- **Tt2.** Is the doctor normal?
  - accepté : no ; no it is not ; no it isn't ; the doctor is not normal ; it is not normal
  - modèle : No, the doctor is not normal.
  - hint (si erreur) — FR : Attention : Médecin n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The doctor … normal. (be)` → is · modèle : The doctor is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The doctor is… / The patient is not… — modèle : *The doctor is normal. The patient is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Infirmier/ière normal(e), mais Médecin anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The nurse is normal. The doctor is not normal.*

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
3. **EN** The patient in bed 3 has a problem with the doctor.
   - FR : Le patient du lit 3 a Médecin.
   - AR : مريض السرير ٣ عنده طبيب.
4. **EN** The patient in bed 5 has a problem with the nurse.
   - FR : Le patient du lit 5 a Infirmier/ière.
   - AR : مريض السرير ٥ عنده ممرض/ة.
5. **EN** The patient in bed 5 does not have a problem with the doctor.
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
  - accepté : nurse ; a problem with the nurse
  - modèle : He has a problem with the nurse.
- **q3.** Does the patient in bed 5 have doctor?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the doctor ; he doesn't
  - modèle : No, he does not have a problem with the doctor.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS médecin — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : patient ; a problem with the patient
  - modèle : He has a problem with the patient.
- **Tt2.** Does the patient in bed 9 have nurse?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the nurse ; he doesn't
  - modèle : No, he does not have a problem with the nurse.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS infirmier/ière.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a doctor and a nurse. (have)` → have · modèle : The patients have a doctor and a nurse.
- `The patient in bed 5 … not have a doctor. (do)` → does · modèle : The patient in bed 5 does not have a doctor.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the doctor. The patient in bed 5 has a problem with the nurse.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Patient(e), le lit 9 a Médecin et n'a pas Infirmier/ière.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the patient. The patient in bed 9 has a problem with the doctor.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
