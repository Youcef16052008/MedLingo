# Relecture — Pediatrics (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-029` — Le cas de Youssef : Convulsion fébrile / _Youssef: Febrile seizure_

**Objectif :** Comprendre un cas : convulsion fébrile présent, érythème fessier (irritation de couche) absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Youssef talks to Amine, a medical student.
   - FR : Youssef parle avec Amine, étudiant·e en médecine.
   - AR : Youssef يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has febrile seizure.
   - FR : Il a Convulsion fébrile.
   - AR : عنده تشنج حموي.
4. **EN** He does not have diaper rash.
   - FR : Il n'a pas de Érythème fessier (irritation de couche).
   - AR : ليس عنده طفح الحفاض.
5. **EN** Amine asks, “Do you have diaper rash?”
   - FR : Amine demande : « Avez-vous Érythème fessier (irritation de couche) ? »
   - AR : يسأل Amine: «هل عندك طفح الحفاض؟»
6. **EN** Youssef says, “No, I do not.”
   - FR : Youssef répond : « Non. »
   - AR : يجيب Youssef: «لا.»
7. **EN** Amine writes “Febrile seizure” in the file.
   - FR : Amine écrit « Convulsion fébrile » dans le dossier.
   - AR : يكتب Amine «Convulsion fébrile» في الملف.

### Expressions ciblées
- `gen:febrile-seizure:v1` — **febrile seizure** · FR : Convulsion fébrile · AR : تشنج حموي
- `gen:diaper-rash:v1` — **diaper rash** · FR : Érythème fessier (irritation de couche) · AR : طفح الحفاض
- `gen:newborn:v1` — **a newborn** · FR : Nouveau-né · AR : المولود الجديد

### Questions (acceptées / modèle / hint)
- **q1.** Does Youssef have febrile seizure?
  - accepté : yes ; yes he does ; yes he has febrile seizure ; he has febrile seizure
  - modèle : Yes, he has febrile seizure.
- **q2.** Does he have diaper rash?
  - accepté : no ; no he does not ; no he doesn't ; he does not have diaper rash ; he doesn't
  - modèle : No, he does not have diaper rash.
  - hint (si erreur) — FR : Attention : Youssef n'a PAS érythème fessier (irritation de couche).
- **q3.** Does he have febrile seizure or diaper rash?
  - accepté : febrile seizure ; he has febrile seizure
  - modèle : He has febrile seizure.
- **q4.** What does Youssef have?
  - accepté : febrile seizure ; he has febrile seizure
  - modèle : He has febrile seizure.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have newborn?
  - accepté : yes ; yes he does ; he has a newborn
  - modèle : Yes, he has a newborn.
- **Tt2.** Does he have febrile seizure?
  - accepté : no ; no he does not ; no he doesn't ; he does not have febrile seizure ; he doesn't
  - modèle : No, he does not have febrile seizure.
  - hint (si erreur) — FR : Attention : il n'a PAS convulsion fébrile.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … febrile seizure. (have)` → has · modèle : He has febrile seizure.
- `They … not have diaper rash. (do)` → do · modèle : They do not have diaper rash.
- `I … febrile seizure. (have)` → have · modèle : I have febrile seizure.

### Productions
- **Reformulation :** Rapporte le cas de Youssef en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has febrile seizure. He does not have diaper rash.*
- **Transfert (contexte) :** Un autre patient a Nouveau-né. Il n'a pas de Convulsion fébrile.
- Glossaire : Nouveau-né : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a newborn. He does not have febrile seizure.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-029` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Nouveau-né et Nourrisson à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check newborn.”
   - FR : « D'abord, nous vérifions Nouveau-né. »
   - AR : «أولًا، نتحقق من المولود الجديد.»
5. **EN** “Then, we check infant.”
   - FR : « Ensuite, nous vérifions Nourrisson. »
   - AR : «ثم نتحقق من الرضيع.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:newborn:v1` — **a newborn** · FR : Nouveau-né · AR : المولود الجديد
- `gen:infant:v1` — **an infant** · FR : Nourrisson · AR : الرضيع
- `gen:toddler:v1` — **a toddler** · FR : Tout-petit (1-3 ans) · AR : الطفل الصغير

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : newborn ; we check newborn ; newborn first ; the doctor checks newborn first
  - modèle : First, the doctor checks newborn.
- **q2.** What does the doctor check next?
  - accepté : infant ; then we check infant ; infant next ; the doctor checks infant next
  - modèle : Then, the doctor checks infant.
- **q3.** Does the doctor check infant before newborn?
  - accepté : no ; no he does not ; no he doesn't ; he checks newborn first
  - modèle : No, he checks newborn first, then infant.
  - hint (si erreur) — FR : Attention : Nouveau-né vient D'ABORD, ensuite Nourrisson.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : toddler ; toddler first ; he checks toddler first
  - modèle : First, the doctor checks toddler.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check newborn. Then, we check infant.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Tout-petit (1-3 ans), ensuite Nouveau-né, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check toddler. Then, we check newborn.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-029` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Convulsion fébrile / Érythème fessier (irritation de couche), lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has febrile seizure.
   - FR : Le patient du lit 3 a Convulsion fébrile.
   - AR : مريض السرير ٣ عنده تشنج حموي.
4. **EN** The patient in bed 5 has diaper rash.
   - FR : Le patient du lit 5 a Érythème fessier (irritation de couche).
   - AR : مريض السرير ٥ عنده طفح الحفاض.
5. **EN** The patient in bed 5 does not have febrile seizure.
   - FR : Le patient du lit 5 n'a pas de Convulsion fébrile.
   - AR : مريض السرير ٥ ليس عنده تشنج حموي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:febrile-seizure:v1` — **febrile seizure** · FR : Convulsion fébrile · AR : تشنج حموي
- `gen:diaper-rash:v1` — **diaper rash** · FR : Érythème fessier (irritation de couche) · AR : طفح الحفاض
- `gen:newborn:v1` — **a newborn** · FR : Nouveau-né · AR : المولود الجديد

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : febrile seizure ; the patient with febrile seizure
  - modèle : The patient in bed 3 has febrile seizure.
- **q2.** What does the patient in bed 5 have?
  - accepté : diaper rash
  - modèle : He has diaper rash.
- **q3.** Does the patient in bed 5 have febrile seizure?
  - accepté : no ; no he does not ; no he doesn't ; he does not have febrile seizure ; he doesn't
  - modèle : No, he does not have febrile seizure.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS convulsion fébrile — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : newborn ; a newborn
  - modèle : He has a newborn.
- **Tt2.** Does the patient in bed 9 have diaper rash?
  - accepté : no ; no he does not ; no he doesn't ; he does not have diaper rash ; he doesn't
  - modèle : No, he does not have diaper rash.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS érythème fessier (irritation de couche).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … febrile seizure and diaper rash. (have)` → have · modèle : The patients have febrile seizure and diaper rash.
- `The patient in bed 5 … not have febrile seizure. (do)` → does · modèle : The patient in bed 5 does not have febrile seizure.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has febrile seizure. The patient in bed 5 has diaper rash.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Nouveau-né, le lit 9 a Convulsion fébrile et n'a pas Érythème fessier (irritation de couche).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a newborn. The patient in bed 9 has febrile seizure.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
