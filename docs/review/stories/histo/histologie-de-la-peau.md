# Relecture — Histologie de la Peau (histo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `histo-case-003` — Le cas de Amel : Derme anormal(e) / _Amel: the dermis is not normal_

**Objectif :** Comprendre des résultats : Peau (Tégument) normal(e), Derme anormal(e).
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
4. **EN** The doctor says, “The skin is normal.”
   - FR : Le médecin dit : « Peau (Tégument) est normal(e). »
   - AR : يقول الطبيب: «الجلد طبيعي/طبيعية.»
5. **EN** “But the dermis is not normal.”
   - FR : « Mais Derme n'est pas normal(e). »
   - AR : «لكن الأدمة غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the dermis normal?”
   - FR : Amine demande : « Derme est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الأدمة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:skin:v1` — **a skin** · FR : Peau (Tégument) · AR : الجلد
- `gen:epidermis:v1` — **epidermis** · FR : Épiderme · AR : البشرة
- `gen:dermis:v1` — **dermis** · FR : Derme · AR : الأدمة

### Questions (acceptées / modèle / hint)
- **q1.** Is the skin normal?
  - accepté : yes ; yes it is ; the skin is normal ; it is normal
  - modèle : Yes, the skin is normal.
- **q2.** Is the dermis normal?
  - accepté : no ; no it is not ; no it isn't ; the dermis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the dermis is not normal.
  - hint (si erreur) — FR : Attention : Derme n'est PAS normal(e).
- **q3.** Which one is not normal: the skin or the dermis?
  - accepté : dermis ; the dermis
  - modèle : The dermis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the epidermis normal?
  - accepté : yes ; yes it is ; the epidermis is normal
  - modèle : Yes, the epidermis is normal.
- **Tt2.** Is the skin normal?
  - accepté : no ; no it is not ; no it isn't ; the skin is not normal ; it is not normal
  - modèle : No, the skin is not normal.
  - hint (si erreur) — FR : Attention : Peau (Tégument) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The skin … normal. (be)` → is · modèle : The skin is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The skin is… / The dermis is not… — modèle : *The skin is normal. The dermis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Épiderme normal(e), mais Peau (Tégument) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The epidermis is normal. The skin is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `histo-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Peau (Tégument) et Épiderme à un patient.
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
4. **EN** “First, we check skin.”
   - FR : « D'abord, nous vérifions Peau (Tégument). »
   - AR : «أولًا، نتحقق من الجلد.»
5. **EN** “Then, we check epidermis.”
   - FR : « Ensuite, nous vérifions Épiderme. »
   - AR : «ثم نتحقق من البشرة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:skin:v1` — **a skin** · FR : Peau (Tégument) · AR : الجلد
- `gen:epidermis:v1` — **epidermis** · FR : Épiderme · AR : البشرة
- `gen:dermis:v1` — **dermis** · FR : Derme · AR : الأدمة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : skin ; we check skin ; skin first ; the doctor checks skin first
  - modèle : First, the doctor checks skin.
- **q2.** What does the doctor check next?
  - accepté : epidermis ; then we check epidermis ; epidermis next ; the doctor checks epidermis next
  - modèle : Then, the doctor checks epidermis.
- **q3.** Does the doctor check epidermis before skin?
  - accepté : no ; no he does not ; no he doesn't ; he checks skin first
  - modèle : No, he checks skin first, then epidermis.
  - hint (si erreur) — FR : Attention : Peau (Tégument) vient D'ABORD, ensuite Épiderme.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : dermis ; dermis first ; he checks dermis first
  - modèle : First, the doctor checks dermis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check skin. Then, we check epidermis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Derme, ensuite Peau (Tégument), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check dermis. Then, we check skin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `histo-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Peau (Tégument) / Épiderme, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the skin.
   - FR : Le patient du lit 3 a Peau (Tégument).
   - AR : مريض السرير ٣ عنده الجلد.
4. **EN** The patient in bed 5 has a problem with the epidermis.
   - FR : Le patient du lit 5 a Épiderme.
   - AR : مريض السرير ٥ عنده البشرة.
5. **EN** The patient in bed 5 does not have a problem with the skin.
   - FR : Le patient du lit 5 n'a pas de Peau (Tégument).
   - AR : مريض السرير ٥ ليس عنده الجلد.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:skin:v1` — **a skin** · FR : Peau (Tégument) · AR : الجلد
- `gen:epidermis:v1` — **epidermis** · FR : Épiderme · AR : البشرة
- `gen:dermis:v1` — **dermis** · FR : Derme · AR : الأدمة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : skin ; a skin ; the patient with skin
  - modèle : The patient in bed 3 has a skin.
- **q2.** What does the patient in bed 5 have?
  - accepté : epidermis ; a problem with the epidermis
  - modèle : He has a problem with the epidermis.
- **q3.** Does the patient in bed 5 have skin?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the skin ; he doesn't
  - modèle : No, he does not have a problem with the skin.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS peau (tégument) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : dermis ; a problem with the dermis
  - modèle : He has a problem with the dermis.
- **Tt2.** Does the patient in bed 9 have epidermis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the epidermis ; he doesn't
  - modèle : No, he does not have a problem with the epidermis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS épiderme.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a skin and epidermis. (have)` → have · modèle : The patients have a skin and epidermis.
- `The patient in bed 5 … not have a skin. (do)` → does · modèle : The patient in bed 5 does not have a skin.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the skin. The patient in bed 5 has a problem with the epidermis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Derme, le lit 9 a Peau (Tégument) et n'a pas Épiderme.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the dermis. The patient in bed 9 has a problem with the skin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
