# Relecture — Lab Tests & Results (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-018` — Le cas de Sofiane : Taux d'hémoglobine anormal(e) / _Sofiane: the hemoglobin level is not normal_

**Objectif :** Comprendre des résultats : Numération formule sanguine (NFS) normal(e), Taux d'hémoglobine anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient at the clinic.
   - FR : Sofiane est un·e patient·e à la clinique.
   - AR : Sofiane مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The complete blood count is normal.”
   - FR : Le médecin dit : « Numération formule sanguine (NFS) est normal(e). »
   - AR : يقول الطبيب: «صورة الدم الكاملة طبيعي/طبيعية.»
5. **EN** “But the hemoglobin level is not normal.”
   - FR : « Mais Taux d'hémoglobine n'est pas normal(e). »
   - AR : «لكن مستوى الهيموغلوبين غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the hemoglobin level normal?”
   - FR : Lina demande : « Taux d'hémoglobine est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل مستوى الهيموغلوبين طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:complete-blood-count:v1` — **a complete blood count** · FR : Numération formule sanguine (NFS) · AR : صورة الدم الكاملة
- `gen:white-blood-cell-count:v1` — **a white blood cell count** · FR : Numération des globules blancs · AR : عدد كريات الدم البيضاء
- `gen:hemoglobin-level:v1` — **a hemoglobin level** · FR : Taux d'hémoglobine · AR : مستوى الهيموغلوبين

### Questions (acceptées / modèle / hint)
- **q1.** Is the complete blood count normal?
  - accepté : yes ; yes it is ; the complete blood count is normal ; it is normal
  - modèle : Yes, the complete blood count is normal.
- **q2.** Is the hemoglobin level normal?
  - accepté : no ; no it is not ; no it isn't ; the hemoglobin level is not normal ; it is not normal ; it isn't normal
  - modèle : No, the hemoglobin level is not normal.
  - hint (si erreur) — FR : Attention : Taux d'hémoglobine n'est PAS normal(e).
- **q3.** Which one is not normal: the complete blood count or the hemoglobin level?
  - accepté : hemoglobin level ; the hemoglobin level
  - modèle : The hemoglobin level is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the white blood cell count normal?
  - accepté : yes ; yes it is ; the white blood cell count is normal
  - modèle : Yes, the white blood cell count is normal.
- **Tt2.** Is the complete blood count normal?
  - accepté : no ; no it is not ; no it isn't ; the complete blood count is not normal ; it is not normal
  - modèle : No, the complete blood count is not normal.
  - hint (si erreur) — FR : Attention : Numération formule sanguine (NFS) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The complete blood count … normal. (be)` → is · modèle : The complete blood count is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sofiane en deux phrases. Garde la négation. — amorces : The complete blood count is… / The hemoglobin level is not… — modèle : *The complete blood count is normal. The hemoglobin level is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Numération des globules blancs normal(e), mais Numération formule sanguine (NFS) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The white blood cell count is normal. The complete blood count is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-018` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Numération formule sanguine (NFS) et Numération des globules blancs à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient.
   - FR : Bilal est un·e patient·e.
   - AR : Bilal مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check complete blood count.”
   - FR : « D'abord, nous vérifions Numération formule sanguine (NFS). »
   - AR : «أولًا، نتحقق من صورة الدم الكاملة.»
5. **EN** “Then, we check white blood cell count.”
   - FR : « Ensuite, nous vérifions Numération des globules blancs. »
   - AR : «ثم نتحقق من عدد كريات الدم البيضاء.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:complete-blood-count:v1` — **a complete blood count** · FR : Numération formule sanguine (NFS) · AR : صورة الدم الكاملة
- `gen:white-blood-cell-count:v1` — **a white blood cell count** · FR : Numération des globules blancs · AR : عدد كريات الدم البيضاء
- `gen:hemoglobin-level:v1` — **a hemoglobin level** · FR : Taux d'hémoglobine · AR : مستوى الهيموغلوبين

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : complete blood count ; we check complete blood count ; complete blood count first ; the doctor checks complete blood count first
  - modèle : First, the doctor checks complete blood count.
- **q2.** What does the doctor check next?
  - accepté : white blood cell count ; then we check white blood cell count ; white blood cell count next ; the doctor checks white blood cell count next
  - modèle : Then, the doctor checks white blood cell count.
- **q3.** Does the doctor check white blood cell count before complete blood count?
  - accepté : no ; no he does not ; no he doesn't ; he checks complete blood count first
  - modèle : No, he checks complete blood count first, then white blood cell count.
  - hint (si erreur) — FR : Attention : Numération formule sanguine (NFS) vient D'ABORD, ensuite Numération des globules blancs.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : hemoglobin level ; hemoglobin level first ; he checks hemoglobin level first
  - modèle : First, the doctor checks hemoglobin level.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check complete blood count. Then, we check white blood cell count.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Taux d'hémoglobine, ensuite Numération formule sanguine (NFS), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check hemoglobin level. Then, we check complete blood count.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-018` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Numération formule sanguine (NFS) / Numération des globules blancs, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the complete blood count.
   - FR : Le patient du lit 3 a Numération formule sanguine (NFS).
   - AR : مريض السرير ٣ عنده صورة الدم الكاملة.
4. **EN** The patient in bed 5 has a problem with the white blood cell count.
   - FR : Le patient du lit 5 a Numération des globules blancs.
   - AR : مريض السرير ٥ عنده عدد كريات الدم البيضاء.
5. **EN** The patient in bed 5 does not have a problem with the complete blood count.
   - FR : Le patient du lit 5 n'a pas de Numération formule sanguine (NFS).
   - AR : مريض السرير ٥ ليس عنده صورة الدم الكاملة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:complete-blood-count:v1` — **a complete blood count** · FR : Numération formule sanguine (NFS) · AR : صورة الدم الكاملة
- `gen:white-blood-cell-count:v1` — **a white blood cell count** · FR : Numération des globules blancs · AR : عدد كريات الدم البيضاء
- `gen:hemoglobin-level:v1` — **a hemoglobin level** · FR : Taux d'hémoglobine · AR : مستوى الهيموغلوبين

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : complete blood count ; a complete blood count ; the patient with complete blood count
  - modèle : The patient in bed 3 has a complete blood count.
- **q2.** What does the patient in bed 5 have?
  - accepté : white blood cell count ; a problem with the white blood cell count
  - modèle : He has a problem with the white blood cell count.
- **q3.** Does the patient in bed 5 have complete blood count?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the complete blood count ; he doesn't
  - modèle : No, he does not have a problem with the complete blood count.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS numération formule sanguine (nfs) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : hemoglobin level ; a problem with the hemoglobin level
  - modèle : He has a problem with the hemoglobin level.
- **Tt2.** Does the patient in bed 9 have white blood cell count?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the white blood cell count ; he doesn't
  - modèle : No, he does not have a problem with the white blood cell count.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS numération des globules blancs.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a complete blood count and a white blood cell count. (have)` → have · modèle : The patients have a complete blood count and a white blood cell count.
- `The patient in bed 5 … not have a complete blood count. (do)` → does · modèle : The patient in bed 5 does not have a complete blood count.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the complete blood count. The patient in bed 5 has a problem with the white blood cell count.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Taux d'hémoglobine, le lit 9 a Numération formule sanguine (NFS) et n'a pas Numération des globules blancs.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the hemoglobin level. The patient in bed 9 has a problem with the complete blood count.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
