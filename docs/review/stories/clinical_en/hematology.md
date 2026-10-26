# Relecture — Hematology (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-013` — Le cas de Nadia : Plaquette anormal(e) / _Nadia: the platelet is not normal_

**Objectif :** Comprendre des résultats : Drépanocytose normal(e), Plaquette anormal(e).
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
4. **EN** The doctor says, “The sickle cell disease is normal.”
   - FR : Le médecin dit : « Drépanocytose est normal(e). »
   - AR : يقول الطبيب: «مرض الخلايا المنجلية طبيعي/طبيعية.»
5. **EN** “But the platelet is not normal.”
   - FR : « Mais Plaquette n'est pas normal(e). »
   - AR : «لكن الصفيحة الدموية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the platelet normal?”
   - FR : Amine demande : « Plaquette est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الصفيحة الدموية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:sickle-cell-disease:v1` — **a sickle cell disease** · FR : Drépanocytose · AR : مرض الخلايا المنجلية
- `gen:hemophilia:v1` — **a hemophilia** · FR : Hémophilie · AR : الهيموفيليا (ناعور)
- `gen:platelet:v1` — **a platelet** · FR : Plaquette · AR : الصفيحة الدموية

### Questions (acceptées / modèle / hint)
- **q1.** Is the sickle cell disease normal?
  - accepté : yes ; yes it is ; the sickle cell disease is normal ; it is normal
  - modèle : Yes, the sickle cell disease is normal.
- **q2.** Is the platelet normal?
  - accepté : no ; no it is not ; no it isn't ; the platelet is not normal ; it is not normal ; it isn't normal
  - modèle : No, the platelet is not normal.
  - hint (si erreur) — FR : Attention : Plaquette n'est PAS normal(e).
- **q3.** Which one is not normal: the sickle cell disease or the platelet?
  - accepté : platelet ; the platelet
  - modèle : The platelet is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the hemophilia normal?
  - accepté : yes ; yes it is ; the hemophilia is normal
  - modèle : Yes, the hemophilia is normal.
- **Tt2.** Is the sickle cell disease normal?
  - accepté : no ; no it is not ; no it isn't ; the sickle cell disease is not normal ; it is not normal
  - modèle : No, the sickle cell disease is not normal.
  - hint (si erreur) — FR : Attention : Drépanocytose n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The sickle cell disease … normal. (be)` → is · modèle : The sickle cell disease is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The sickle cell disease is… / The platelet is not… — modèle : *The sickle cell disease is normal. The platelet is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Hémophilie normal(e), mais Drépanocytose anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The hemophilia is normal. The sickle cell disease is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-013` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Drépanocytose et Hémophilie à un patient.
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
4. **EN** “First, we check sickle cell disease.”
   - FR : « D'abord, nous vérifions Drépanocytose. »
   - AR : «أولًا، نتحقق من مرض الخلايا المنجلية.»
5. **EN** “Then, we check hemophilia.”
   - FR : « Ensuite, nous vérifions Hémophilie. »
   - AR : «ثم نتحقق من الهيموفيليا (ناعور).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:sickle-cell-disease:v1` — **a sickle cell disease** · FR : Drépanocytose · AR : مرض الخلايا المنجلية
- `gen:hemophilia:v1` — **a hemophilia** · FR : Hémophilie · AR : الهيموفيليا (ناعور)
- `gen:platelet:v1` — **a platelet** · FR : Plaquette · AR : الصفيحة الدموية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : sickle cell disease ; we check sickle cell disease ; sickle cell disease first ; the doctor checks sickle cell disease first
  - modèle : First, the doctor checks sickle cell disease.
- **q2.** What does the doctor check next?
  - accepté : hemophilia ; then we check hemophilia ; hemophilia next ; the doctor checks hemophilia next
  - modèle : Then, the doctor checks hemophilia.
- **q3.** Does the doctor check hemophilia before sickle cell disease?
  - accepté : no ; no he does not ; no he doesn't ; he checks sickle cell disease first
  - modèle : No, he checks sickle cell disease first, then hemophilia.
  - hint (si erreur) — FR : Attention : Drépanocytose vient D'ABORD, ensuite Hémophilie.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : platelet ; platelet first ; he checks platelet first
  - modèle : First, the doctor checks platelet.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check sickle cell disease. Then, we check hemophilia.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Plaquette, ensuite Drépanocytose, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check platelet. Then, we check sickle cell disease.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-013` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Drépanocytose / Hémophilie, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the sickle cell disease.
   - FR : Le patient du lit 3 a Drépanocytose.
   - AR : مريض السرير ٣ عنده مرض الخلايا المنجلية.
4. **EN** The patient in bed 5 has a problem with the hemophilia.
   - FR : Le patient du lit 5 a Hémophilie.
   - AR : مريض السرير ٥ عنده الهيموفيليا (ناعور).
5. **EN** The patient in bed 5 does not have a problem with the sickle cell disease.
   - FR : Le patient du lit 5 n'a pas de Drépanocytose.
   - AR : مريض السرير ٥ ليس عنده مرض الخلايا المنجلية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:sickle-cell-disease:v1` — **a sickle cell disease** · FR : Drépanocytose · AR : مرض الخلايا المنجلية
- `gen:hemophilia:v1` — **a hemophilia** · FR : Hémophilie · AR : الهيموفيليا (ناعور)
- `gen:platelet:v1` — **a platelet** · FR : Plaquette · AR : الصفيحة الدموية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : sickle cell disease ; a sickle cell disease ; the patient with sickle cell disease
  - modèle : The patient in bed 3 has a sickle cell disease.
- **q2.** What does the patient in bed 5 have?
  - accepté : hemophilia ; a problem with the hemophilia
  - modèle : He has a problem with the hemophilia.
- **q3.** Does the patient in bed 5 have sickle cell disease?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the sickle cell disease ; he doesn't
  - modèle : No, he does not have a problem with the sickle cell disease.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS drépanocytose — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : platelet ; a problem with the platelet
  - modèle : He has a problem with the platelet.
- **Tt2.** Does the patient in bed 9 have hemophilia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the hemophilia ; he doesn't
  - modèle : No, he does not have a problem with the hemophilia.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS hémophilie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a sickle cell disease and a hemophilia. (have)` → have · modèle : The patients have a sickle cell disease and a hemophilia.
- `The patient in bed 5 … not have a sickle cell disease. (do)` → does · modèle : The patient in bed 5 does not have a sickle cell disease.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the sickle cell disease. The patient in bed 5 has a problem with the hemophilia.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Plaquette, le lit 9 a Drépanocytose et n'a pas Hémophilie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the platelet. The patient in bed 9 has a problem with the sickle cell disease.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
