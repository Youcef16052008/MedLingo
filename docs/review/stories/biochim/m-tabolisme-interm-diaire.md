# Relecture — Métabolisme Intermédiaire (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-017` — Le cas de Meriem : Chaîne de transport des électrons anormal(e) / _Meriem: the electron transport chain is not normal_

**Objectif :** Comprendre des résultats : Pyruvate normal(e), Chaîne de transport des électrons anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient at the clinic.
   - FR : Meriem est un·e patient·e à la clinique.
   - AR : Meriem مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The pyruvate is normal.”
   - FR : Le médecin dit : « Pyruvate est normal(e). »
   - AR : يقول الطبيب: «البيروفات طبيعي/طبيعية.»
5. **EN** “But the electron transport chain is not normal.”
   - FR : « Mais Chaîne de transport des électrons n'est pas normal(e). »
   - AR : «لكن سلسلة نقل الإلكترونات غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the electron transport chain normal?”
   - FR : Amine demande : « Chaîne de transport des électrons est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل سلسلة نقل الإلكترونات طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:pyruvate:v1` — **a pyruvate** · FR : Pyruvate · AR : البيروفات
- `gen:acetyl-coa:v1` — **an acetyl-coa** · FR : Acétyl-CoA · AR : أسيتيل مساعد الإنزيم أ
- `gen:electron-transport-chain:v1` — **an electron transport chain** · FR : Chaîne de transport des électrons · AR : سلسلة نقل الإلكترونات

### Questions (acceptées / modèle / hint)
- **q1.** Is the pyruvate normal?
  - accepté : yes ; yes it is ; the pyruvate is normal ; it is normal
  - modèle : Yes, the pyruvate is normal.
- **q2.** Is the electron transport chain normal?
  - accepté : no ; no it is not ; no it isn't ; the electron transport chain is not normal ; it is not normal ; it isn't normal
  - modèle : No, the electron transport chain is not normal.
  - hint (si erreur) — FR : Attention : Chaîne de transport des électrons n'est PAS normal(e).
- **q3.** Which one is not normal: the pyruvate or the electron transport chain?
  - accepté : electron transport chain ; the electron transport chain
  - modèle : The electron transport chain is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the acetyl-coa normal?
  - accepté : yes ; yes it is ; the acetyl-coa is normal
  - modèle : Yes, the acetyl-coa is normal.
- **Tt2.** Is the pyruvate normal?
  - accepté : no ; no it is not ; no it isn't ; the pyruvate is not normal ; it is not normal
  - modèle : No, the pyruvate is not normal.
  - hint (si erreur) — FR : Attention : Pyruvate n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The pyruvate … normal. (be)` → is · modèle : The pyruvate is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The pyruvate is… / The electron transport chain is not… — modèle : *The pyruvate is normal. The electron transport chain is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Acétyl-CoA normal(e), mais Pyruvate anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The acetyl-coa is normal. The pyruvate is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-017` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Pyruvate et Acétyl-CoA à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient.
   - FR : Hana est un·e patient·e.
   - AR : Hana مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check pyruvate.”
   - FR : « D'abord, nous vérifions Pyruvate. »
   - AR : «أولًا، نتحقق من البيروفات.»
5. **EN** “Then, we check acetyl-coa.”
   - FR : « Ensuite, nous vérifions Acétyl-CoA. »
   - AR : «ثم نتحقق من أسيتيل مساعد الإنزيم أ.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:pyruvate:v1` — **a pyruvate** · FR : Pyruvate · AR : البيروفات
- `gen:acetyl-coa:v1` — **an acetyl-coa** · FR : Acétyl-CoA · AR : أسيتيل مساعد الإنزيم أ
- `gen:electron-transport-chain:v1` — **an electron transport chain** · FR : Chaîne de transport des électrons · AR : سلسلة نقل الإلكترونات

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : pyruvate ; we check pyruvate ; pyruvate first ; the doctor checks pyruvate first
  - modèle : First, the doctor checks pyruvate.
- **q2.** What does the doctor check next?
  - accepté : acetyl-coa ; then we check acetyl-coa ; acetyl-coa next ; the doctor checks acetyl-coa next
  - modèle : Then, the doctor checks acetyl-coa.
- **q3.** Does the doctor check acetyl-coa before pyruvate?
  - accepté : no ; no he does not ; no he doesn't ; he checks pyruvate first
  - modèle : No, he checks pyruvate first, then acetyl-coa.
  - hint (si erreur) — FR : Attention : Pyruvate vient D'ABORD, ensuite Acétyl-CoA.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : electron transport chain ; electron transport chain first ; he checks electron transport chain first
  - modèle : First, the doctor checks electron transport chain.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check pyruvate. Then, we check acetyl-coa.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Chaîne de transport des électrons, ensuite Pyruvate, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check electron transport chain. Then, we check pyruvate.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-017` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Pyruvate / Acétyl-CoA, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the pyruvate.
   - FR : Le patient du lit 3 a Pyruvate.
   - AR : مريض السرير ٣ عنده البيروفات.
4. **EN** The patient in bed 5 has a problem with the acetyl-coa.
   - FR : Le patient du lit 5 a Acétyl-CoA.
   - AR : مريض السرير ٥ عنده أسيتيل مساعد الإنزيم أ.
5. **EN** The patient in bed 5 does not have a problem with the pyruvate.
   - FR : Le patient du lit 5 n'a pas de Pyruvate.
   - AR : مريض السرير ٥ ليس عنده البيروفات.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:pyruvate:v1` — **a pyruvate** · FR : Pyruvate · AR : البيروفات
- `gen:acetyl-coa:v1` — **an acetyl-coa** · FR : Acétyl-CoA · AR : أسيتيل مساعد الإنزيم أ
- `gen:electron-transport-chain:v1` — **an electron transport chain** · FR : Chaîne de transport des électrons · AR : سلسلة نقل الإلكترونات

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : pyruvate ; a pyruvate ; the patient with pyruvate
  - modèle : The patient in bed 3 has a pyruvate.
- **q2.** What does the patient in bed 5 have?
  - accepté : acetyl-coa ; a problem with the acetyl-coa
  - modèle : He has a problem with the acetyl-coa.
- **q3.** Does the patient in bed 5 have pyruvate?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the pyruvate ; he doesn't
  - modèle : No, he does not have a problem with the pyruvate.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS pyruvate — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : electron transport chain ; a problem with the electron transport chain
  - modèle : He has a problem with the electron transport chain.
- **Tt2.** Does the patient in bed 9 have acetyl-coa?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the acetyl-coa ; he doesn't
  - modèle : No, he does not have a problem with the acetyl-coa.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS acétyl-coa.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a pyruvate and an acetyl-coa. (have)` → have · modèle : The patients have a pyruvate and an acetyl-coa.
- `The patient in bed 5 … not have a pyruvate. (do)` → does · modèle : The patient in bed 5 does not have a pyruvate.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the pyruvate. The patient in bed 5 has a problem with the acetyl-coa.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Chaîne de transport des électrons, le lit 9 a Pyruvate et n'a pas Acétyl-CoA.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the electron transport chain. The patient in bed 9 has a problem with the pyruvate.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
