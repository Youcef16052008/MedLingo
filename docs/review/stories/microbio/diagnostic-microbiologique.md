# Relecture — Diagnostic Microbiologique (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-003` — Le cas de Amel : Culture de crachat anormal(e) / _Amel: the sputum culture is not normal_

**Objectif :** Comprendre des résultats : Culture et antibiogramme normal(e), Culture de crachat anormal(e).
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
4. **EN** The doctor says, “The culture and sensitivity is normal.”
   - FR : Le médecin dit : « Culture et antibiogramme est normal(e). »
   - AR : يقول الطبيب: «الزراعة وحساسية المضادات طبيعي/طبيعية.»
5. **EN** “But the sputum culture is not normal.”
   - FR : « Mais Culture de crachat n'est pas normal(e). »
   - AR : «لكن مزرعة البلغم غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the sputum culture normal?”
   - FR : Amine demande : « Culture de crachat est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل مزرعة البلغم طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:culture-and-sensitivity:v1` — **a culture and sensitivity** · FR : Culture et antibiogramme · AR : الزراعة وحساسية المضادات
- `gen:specimen:v1` — **a specimen** · FR : Prélèvement (Spécimen) · AR : العينة
- `gen:sputum-culture:v1` — **sputum culture** · FR : Culture de crachat · AR : مزرعة البلغم

### Questions (acceptées / modèle / hint)
- **q1.** Is the culture and sensitivity normal?
  - accepté : yes ; yes it is ; the culture and sensitivity is normal ; it is normal
  - modèle : Yes, the culture and sensitivity is normal.
- **q2.** Is the sputum culture normal?
  - accepté : no ; no it is not ; no it isn't ; the sputum culture is not normal ; it is not normal ; it isn't normal
  - modèle : No, the sputum culture is not normal.
  - hint (si erreur) — FR : Attention : Culture de crachat n'est PAS normal(e).
- **q3.** Which one is not normal: the culture and sensitivity or the sputum culture?
  - accepté : sputum culture ; the sputum culture
  - modèle : The sputum culture is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the specimen normal?
  - accepté : yes ; yes it is ; the specimen is normal
  - modèle : Yes, the specimen is normal.
- **Tt2.** Is the culture and sensitivity normal?
  - accepté : no ; no it is not ; no it isn't ; the culture and sensitivity is not normal ; it is not normal
  - modèle : No, the culture and sensitivity is not normal.
  - hint (si erreur) — FR : Attention : Culture et antibiogramme n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The culture and sensitivity … normal. (be)` → is · modèle : The culture and sensitivity is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The culture and sensitivity is… / The sputum culture is not… — modèle : *The culture and sensitivity is normal. The sputum culture is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Prélèvement (Spécimen) normal(e), mais Culture et antibiogramme anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The specimen is normal. The culture and sensitivity is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Culture et antibiogramme et Prélèvement (Spécimen) à un patient.
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
4. **EN** “First, we check culture and sensitivity.”
   - FR : « D'abord, nous vérifions Culture et antibiogramme. »
   - AR : «أولًا، نتحقق من الزراعة وحساسية المضادات.»
5. **EN** “Then, we check specimen.”
   - FR : « Ensuite, nous vérifions Prélèvement (Spécimen). »
   - AR : «ثم نتحقق من العينة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:culture-and-sensitivity:v1` — **a culture and sensitivity** · FR : Culture et antibiogramme · AR : الزراعة وحساسية المضادات
- `gen:specimen:v1` — **a specimen** · FR : Prélèvement (Spécimen) · AR : العينة
- `gen:sputum-culture:v1` — **sputum culture** · FR : Culture de crachat · AR : مزرعة البلغم

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : culture and sensitivity ; we check culture and sensitivity ; culture and sensitivity first ; the doctor checks culture and sensitivity first
  - modèle : First, the doctor checks culture and sensitivity.
- **q2.** What does the doctor check next?
  - accepté : specimen ; then we check specimen ; specimen next ; the doctor checks specimen next
  - modèle : Then, the doctor checks specimen.
- **q3.** Does the doctor check specimen before culture and sensitivity?
  - accepté : no ; no he does not ; no he doesn't ; he checks culture and sensitivity first
  - modèle : No, he checks culture and sensitivity first, then specimen.
  - hint (si erreur) — FR : Attention : Culture et antibiogramme vient D'ABORD, ensuite Prélèvement (Spécimen).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : sputum culture ; sputum culture first ; he checks sputum culture first
  - modèle : First, the doctor checks sputum culture.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check culture and sensitivity. Then, we check specimen.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Culture de crachat, ensuite Culture et antibiogramme, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check sputum culture. Then, we check culture and sensitivity.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Culture et antibiogramme / Prélèvement (Spécimen), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the culture and sensitivity.
   - FR : Le patient du lit 3 a Culture et antibiogramme.
   - AR : مريض السرير ٣ عنده الزراعة وحساسية المضادات.
4. **EN** The patient in bed 5 has a problem with the specimen.
   - FR : Le patient du lit 5 a Prélèvement (Spécimen).
   - AR : مريض السرير ٥ عنده العينة.
5. **EN** The patient in bed 5 does not have a problem with the culture and sensitivity.
   - FR : Le patient du lit 5 n'a pas de Culture et antibiogramme.
   - AR : مريض السرير ٥ ليس عنده الزراعة وحساسية المضادات.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:culture-and-sensitivity:v1` — **a culture and sensitivity** · FR : Culture et antibiogramme · AR : الزراعة وحساسية المضادات
- `gen:specimen:v1` — **a specimen** · FR : Prélèvement (Spécimen) · AR : العينة
- `gen:sputum-culture:v1` — **sputum culture** · FR : Culture de crachat · AR : مزرعة البلغم

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : culture and sensitivity ; a culture and sensitivity ; the patient with culture and sensitivity
  - modèle : The patient in bed 3 has a culture and sensitivity.
- **q2.** What does the patient in bed 5 have?
  - accepté : specimen ; a problem with the specimen
  - modèle : He has a problem with the specimen.
- **q3.** Does the patient in bed 5 have culture and sensitivity?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the culture and sensitivity ; he doesn't
  - modèle : No, he does not have a problem with the culture and sensitivity.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS culture et antibiogramme — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : sputum culture ; a problem with the sputum culture
  - modèle : He has a problem with the sputum culture.
- **Tt2.** Does the patient in bed 9 have specimen?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the specimen ; he doesn't
  - modèle : No, he does not have a problem with the specimen.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS prélèvement (spécimen).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a culture and sensitivity and a specimen. (have)` → have · modèle : The patients have a culture and sensitivity and a specimen.
- `The patient in bed 5 … not have a culture and sensitivity. (do)` → does · modèle : The patient in bed 5 does not have a culture and sensitivity.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the culture and sensitivity. The patient in bed 5 has a problem with the specimen.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Culture de crachat, le lit 9 a Culture et antibiogramme et n'a pas Prélèvement (Spécimen).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the sputum culture. The patient in bed 9 has a problem with the culture and sensitivity.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
