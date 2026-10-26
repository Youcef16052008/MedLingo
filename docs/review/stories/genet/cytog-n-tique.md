# Relecture — Cytogénétique (genet)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `genet-case-001` — Le cas de Nadia : Méiose anormal(e) / _Nadia: the meiosis is not normal_

**Objectif :** Comprendre des résultats : Cytogénétique normal(e), Méiose anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The cytogenetics is normal.”
   - FR : Le médecin dit : « Cytogénétique est normal(e). »
   - AR : يقول الطبيب: «علم الوراثة الخلوية طبيعي/طبيعية.»
5. **EN** “But the meiosis is not normal.”
   - FR : « Mais Méiose n'est pas normal(e). »
   - AR : «لكن الانقسام الاختزالي غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the meiosis normal?”
   - FR : Amine demande : « Méiose est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الانقسام الاختزالي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:cytogenetics:v1` — **cytogenetics** · FR : Cytogénétique · AR : علم الوراثة الخلوية
- `gen:mitosis:v1` — **mitosis** · FR : Mitose · AR : الانقسام المتساوي
- `gen:meiosis:v1` — **meiosis** · FR : Méiose · AR : الانقسام الاختزالي

### Questions (acceptées / modèle / hint)
- **q1.** Is the cytogenetics normal?
  - accepté : yes ; yes it is ; the cytogenetics is normal ; it is normal
  - modèle : Yes, the cytogenetics is normal.
- **q2.** Is the meiosis normal?
  - accepté : no ; no it is not ; no it isn't ; the meiosis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the meiosis is not normal.
  - hint (si erreur) — FR : Attention : Méiose n'est PAS normal(e).
- **q3.** Which one is not normal: the cytogenetics or the meiosis?
  - accepté : meiosis ; the meiosis
  - modèle : The meiosis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the mitosis normal?
  - accepté : yes ; yes it is ; the mitosis is normal
  - modèle : Yes, the mitosis is normal.
- **Tt2.** Is the cytogenetics normal?
  - accepté : no ; no it is not ; no it isn't ; the cytogenetics is not normal ; it is not normal
  - modèle : No, the cytogenetics is not normal.
  - hint (si erreur) — FR : Attention : Cytogénétique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The cytogenetics … normal. (be)` → is · modèle : The cytogenetics is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The cytogenetics is… / The meiosis is not… — modèle : *The cytogenetics is normal. The meiosis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Mitose normal(e), mais Cytogénétique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The mitosis is normal. The cytogenetics is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `genet-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Cytogénétique et Mitose à un patient.
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
4. **EN** “First, we check cytogenetics.”
   - FR : « D'abord, nous vérifions Cytogénétique. »
   - AR : «أولًا، نتحقق من علم الوراثة الخلوية.»
5. **EN** “Then, we check mitosis.”
   - FR : « Ensuite, nous vérifions Mitose. »
   - AR : «ثم نتحقق من الانقسام المتساوي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cytogenetics:v1` — **cytogenetics** · FR : Cytogénétique · AR : علم الوراثة الخلوية
- `gen:mitosis:v1` — **mitosis** · FR : Mitose · AR : الانقسام المتساوي
- `gen:meiosis:v1` — **meiosis** · FR : Méiose · AR : الانقسام الاختزالي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cytogenetics ; we check cytogenetics ; cytogenetics first ; the doctor checks cytogenetics first
  - modèle : First, the doctor checks cytogenetics.
- **q2.** What does the doctor check next?
  - accepté : mitosis ; then we check mitosis ; mitosis next ; the doctor checks mitosis next
  - modèle : Then, the doctor checks mitosis.
- **q3.** Does the doctor check mitosis before cytogenetics?
  - accepté : no ; no he does not ; no he doesn't ; he checks cytogenetics first
  - modèle : No, he checks cytogenetics first, then mitosis.
  - hint (si erreur) — FR : Attention : Cytogénétique vient D'ABORD, ensuite Mitose.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : meiosis ; meiosis first ; he checks meiosis first
  - modèle : First, the doctor checks meiosis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cytogenetics. Then, we check mitosis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Méiose, ensuite Cytogénétique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check meiosis. Then, we check cytogenetics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `genet-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cytogénétique / Mitose, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the cytogenetics.
   - FR : Le patient du lit 3 a Cytogénétique.
   - AR : مريض السرير ٣ عنده علم الوراثة الخلوية.
4. **EN** The patient in bed 5 has a problem with the mitosis.
   - FR : Le patient du lit 5 a Mitose.
   - AR : مريض السرير ٥ عنده الانقسام المتساوي.
5. **EN** The patient in bed 5 does not have a problem with the cytogenetics.
   - FR : Le patient du lit 5 n'a pas de Cytogénétique.
   - AR : مريض السرير ٥ ليس عنده علم الوراثة الخلوية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:cytogenetics:v1` — **cytogenetics** · FR : Cytogénétique · AR : علم الوراثة الخلوية
- `gen:mitosis:v1` — **mitosis** · FR : Mitose · AR : الانقسام المتساوي
- `gen:meiosis:v1` — **meiosis** · FR : Méiose · AR : الانقسام الاختزالي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cytogenetics ; the patient with cytogenetics
  - modèle : The patient in bed 3 has cytogenetics.
- **q2.** What does the patient in bed 5 have?
  - accepté : mitosis ; a problem with the mitosis
  - modèle : He has a problem with the mitosis.
- **q3.** Does the patient in bed 5 have cytogenetics?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the cytogenetics ; he doesn't
  - modèle : No, he does not have a problem with the cytogenetics.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cytogénétique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : meiosis ; a problem with the meiosis
  - modèle : He has a problem with the meiosis.
- **Tt2.** Does the patient in bed 9 have mitosis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the mitosis ; he doesn't
  - modèle : No, he does not have a problem with the mitosis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS mitose.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … cytogenetics and mitosis. (have)` → have · modèle : The patients have cytogenetics and mitosis.
- `The patient in bed 5 … not have cytogenetics. (do)` → does · modèle : The patient in bed 5 does not have cytogenetics.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the cytogenetics. The patient in bed 5 has a problem with the mitosis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Méiose, le lit 9 a Cytogénétique et n'a pas Mitose.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the meiosis. The patient in bed 9 has a problem with the cytogenetics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
