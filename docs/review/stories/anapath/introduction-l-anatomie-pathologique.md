# Relecture — Introduction à l'Anatomie Pathologique (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-003` — Le cas de Amel : Biopsie anormal(e) / _Amel: the biopsy is not normal_

**Objectif :** Comprendre des résultats : Anatomie Pathologique normal(e), Biopsie anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The pathology is normal.”
   - FR : Le médecin dit : « Anatomie Pathologique est normal(e). »
   - AR : يقول الطبيب: «علم الأمراض التشريحي طبيعي/طبيعية.»
5. **EN** “But the biopsy is not normal.”
   - FR : « Mais Biopsie n'est pas normal(e). »
   - AR : «لكن خزعة النسيج غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the biopsy normal?”
   - FR : Amine demande : « Biopsie est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل خزعة النسيج طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:pathology:v1` — **a pathology** · FR : Anatomie Pathologique · AR : علم الأمراض التشريحي
- `gen:histopathology:v1` — **a histopathology** · FR : Histopathologie · AR : علم الأنسجة المرضية
- `gen:biopsy:v1` — **a biopsy** · FR : Biopsie · AR : خزعة النسيج

### Questions (acceptées / modèle / hint)
- **q1.** Is the pathology normal?
  - accepté : yes ; yes it is ; the pathology is normal ; it is normal
  - modèle : Yes, the pathology is normal.
- **q2.** Is the biopsy normal?
  - accepté : no ; no it is not ; no it isn't ; the biopsy is not normal ; it is not normal ; it isn't normal
  - modèle : No, the biopsy is not normal.
  - hint (si erreur) — FR : Attention : Biopsie n'est PAS normal(e).
- **q3.** Which one is not normal: the pathology or the biopsy?
  - accepté : biopsy ; the biopsy
  - modèle : The biopsy is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the histopathology normal?
  - accepté : yes ; yes it is ; the histopathology is normal
  - modèle : Yes, the histopathology is normal.
- **Tt2.** Is the pathology normal?
  - accepté : no ; no it is not ; no it isn't ; the pathology is not normal ; it is not normal
  - modèle : No, the pathology is not normal.
  - hint (si erreur) — FR : Attention : Anatomie Pathologique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The pathology … normal. (be)` → is · modèle : The pathology is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The pathology is… / The biopsy is not… — modèle : *The pathology is normal. The biopsy is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Histopathologie normal(e), mais Anatomie Pathologique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The histopathology is normal. The pathology is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Anatomie Pathologique et Histopathologie à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check pathology.”
   - FR : « D'abord, nous vérifions Anatomie Pathologique. »
   - AR : «أولًا، نتحقق من علم الأمراض التشريحي.»
5. **EN** “Then, we check histopathology.”
   - FR : « Ensuite, nous vérifions Histopathologie. »
   - AR : «ثم نتحقق من علم الأنسجة المرضية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:pathology:v1` — **a pathology** · FR : Anatomie Pathologique · AR : علم الأمراض التشريحي
- `gen:histopathology:v1` — **a histopathology** · FR : Histopathologie · AR : علم الأنسجة المرضية
- `gen:biopsy:v1` — **a biopsy** · FR : Biopsie · AR : خزعة النسيج

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : pathology ; we check pathology ; pathology first ; the doctor checks pathology first
  - modèle : First, the doctor checks pathology.
- **q2.** What does the doctor check next?
  - accepté : histopathology ; then we check histopathology ; histopathology next ; the doctor checks histopathology next
  - modèle : Then, the doctor checks histopathology.
- **q3.** Does the doctor check histopathology before pathology?
  - accepté : no ; no he does not ; no he doesn't ; he checks pathology first
  - modèle : No, he checks pathology first, then histopathology.
  - hint (si erreur) — FR : Attention : Anatomie Pathologique vient D'ABORD, ensuite Histopathologie.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : biopsy ; biopsy first ; he checks biopsy first
  - modèle : First, the doctor checks biopsy.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check pathology. Then, we check histopathology.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Biopsie, ensuite Anatomie Pathologique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check biopsy. Then, we check pathology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Anatomie Pathologique / Histopathologie, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the pathology.
   - FR : Le patient du lit 3 a Anatomie Pathologique.
   - AR : مريض السرير ٣ عنده علم الأمراض التشريحي.
4. **EN** The patient in bed 5 has a problem with the histopathology.
   - FR : Le patient du lit 5 a Histopathologie.
   - AR : مريض السرير ٥ عنده علم الأنسجة المرضية.
5. **EN** The patient in bed 5 does not have a problem with the pathology.
   - FR : Le patient du lit 5 n'a pas de Anatomie Pathologique.
   - AR : مريض السرير ٥ ليس عنده علم الأمراض التشريحي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:pathology:v1` — **a pathology** · FR : Anatomie Pathologique · AR : علم الأمراض التشريحي
- `gen:histopathology:v1` — **a histopathology** · FR : Histopathologie · AR : علم الأنسجة المرضية
- `gen:biopsy:v1` — **a biopsy** · FR : Biopsie · AR : خزعة النسيج

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : pathology ; a pathology ; the patient with pathology
  - modèle : The patient in bed 3 has a pathology.
- **q2.** What does the patient in bed 5 have?
  - accepté : histopathology ; a problem with the histopathology
  - modèle : He has a problem with the histopathology.
- **q3.** Does the patient in bed 5 have pathology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the pathology ; he doesn't
  - modèle : No, he does not have a problem with the pathology.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS anatomie pathologique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : biopsy ; a problem with the biopsy
  - modèle : He has a problem with the biopsy.
- **Tt2.** Does the patient in bed 9 have histopathology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the histopathology ; he doesn't
  - modèle : No, he does not have a problem with the histopathology.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS histopathologie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a pathology and a histopathology. (have)` → have · modèle : The patients have a pathology and a histopathology.
- `The patient in bed 5 … not have a pathology. (do)` → does · modèle : The patient in bed 5 does not have a pathology.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the pathology. The patient in bed 5 has a problem with the histopathology.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Biopsie, le lit 9 a Anatomie Pathologique et n'a pas Histopathologie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the biopsy. The patient in bed 9 has a problem with the pathology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
