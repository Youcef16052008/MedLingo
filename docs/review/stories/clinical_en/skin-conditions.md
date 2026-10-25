# Relecture — Skin Conditions (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-035` — Le cas de Bilal : Cellulite (infectieuse) / _Bilal: Cellulitis_

**Objectif :** Comprendre un cas : cellulite (infectieuse) présent, chute de cheveux (alopécie) absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Bilal talks to Amine, a medical student.
   - FR : Bilal parle avec Amine, étudiant·e en médecine.
   - AR : Bilal يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has cellulitis.
   - FR : Il a Cellulite (infectieuse).
   - AR : عنده التهاب النسيج الخلوي.
4. **EN** He does not have hair loss.
   - FR : Il n'a pas de Chute de cheveux (Alopécie).
   - AR : ليس عنده تساقط الشعر.
5. **EN** Amine asks, “Do you have hair loss?”
   - FR : Amine demande : « Avez-vous Chute de cheveux (Alopécie) ? »
   - AR : يسأل Amine: «هل عندك تساقط الشعر؟»
6. **EN** Bilal says, “No, I do not.”
   - FR : Bilal répond : « Non. »
   - AR : يجيب Bilal: «لا.»
7. **EN** Amine writes “Cellulitis” in the file.
   - FR : Amine écrit « Cellulite (infectieuse) » dans le dossier.
   - AR : يكتب Amine «Cellulite (infectieuse)» في الملف.

### Expressions ciblées
- `gen:cellulitis:v1` — **cellulitis** · FR : Cellulite (infectieuse) · AR : التهاب النسيج الخلوي
- `gen:hair-loss:v1` — **hair loss** · FR : Chute de cheveux (Alopécie) · AR : تساقط الشعر
- `gen:acne:v1` — **a acne** · FR : Acné · AR : حب الشباب

### Questions (acceptées / modèle / hint)
- **q1.** Does Bilal have cellulitis?
  - accepté : yes ; yes he does ; yes he has cellulitis ; he has cellulitis
  - modèle : Yes, he has cellulitis.
- **q2.** Does he have hair loss?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hair loss ; he doesn't
  - modèle : No, he does not have hair loss.
  - hint (si erreur) — FR : Attention : Bilal n'a PAS chute de cheveux (alopécie).
- **q3.** Does he have cellulitis or hair loss?
  - accepté : cellulitis ; he has cellulitis
  - modèle : He has cellulitis.
- **q4.** What does Bilal have?
  - accepté : cellulitis ; he has cellulitis
  - modèle : He has cellulitis.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have acne?
  - accepté : yes ; yes he does ; he has a acne
  - modèle : Yes, he has a acne.
- **Tt2.** Does he have cellulitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have cellulitis ; he doesn't
  - modèle : No, he does not have cellulitis.
  - hint (si erreur) — FR : Attention : il n'a PAS cellulite (infectieuse).

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … cellulitis. (have)` → has · modèle : He has cellulitis.
- `They … not have hair loss. (do)` → do · modèle : They do not have hair loss.
- `I … cellulitis. (have)` → have · modèle : I have cellulitis.

### Productions
- **Reformulation :** Rapporte le cas de Bilal en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has cellulitis. He does not have hair loss.*
- **Transfert (contexte) :** Un autre patient a Acné. Il n'a pas de Cellulite (infectieuse).
- Glossaire : Acné : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a acne. He does not have cellulitis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-035` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Acné et Ampoule à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient.
   - FR : Nadia est un·e patient·e.
   - AR : Nadia مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check acne.”
   - FR : « D'abord, nous vérifions Acné. »
   - AR : «أولًا، نتحقق من حب الشباب.»
5. **EN** “Then, we check blister.”
   - FR : « Ensuite, nous vérifions Ampoule. »
   - AR : «ثم نتحقق من فقاعة جلدية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:acne:v1` — **a acne** · FR : Acné · AR : حب الشباب
- `gen:blister:v1` — **a blister** · FR : Ampoule · AR : فقاعة جلدية
- `gen:hives:v1` — **hives** · FR : Urticaire · AR : الشرى (حساسية الجلد)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : acne ; we check acne ; acne first ; the doctor checks acne first
  - modèle : First, the doctor checks acne.
- **q2.** What does the doctor check next?
  - accepté : blister ; then we check blister ; blister next ; the doctor checks blister next
  - modèle : Then, the doctor checks blister.
- **q3.** Does the doctor check blister before acne?
  - accepté : no ; no he does not ; no he doesn't ; he checks acne first
  - modèle : No, he checks acne first, then blister.
  - hint (si erreur) — FR : Attention : Acné vient D'ABORD, ensuite Ampoule.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : hives ; hives first ; he checks hives first
  - modèle : First, the doctor checks hives.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check acne. Then, we check blister.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Urticaire, ensuite Acné, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check hives. Then, we check acne.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-035` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cellulite (infectieuse) / Chute de cheveux (Alopécie), lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has cellulitis.
   - FR : Le patient du lit 3 a Cellulite (infectieuse).
   - AR : مريض السرير ٣ عنده التهاب النسيج الخلوي.
4. **EN** The patient in bed 5 has hair loss.
   - FR : Le patient du lit 5 a Chute de cheveux (Alopécie).
   - AR : مريض السرير ٥ عنده تساقط الشعر.
5. **EN** The patient in bed 5 does not have cellulitis.
   - FR : Le patient du lit 5 n'a pas de Cellulite (infectieuse).
   - AR : مريض السرير ٥ ليس عنده التهاب النسيج الخلوي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:cellulitis:v1` — **cellulitis** · FR : Cellulite (infectieuse) · AR : التهاب النسيج الخلوي
- `gen:hair-loss:v1` — **hair loss** · FR : Chute de cheveux (Alopécie) · AR : تساقط الشعر
- `gen:acne:v1` — **a acne** · FR : Acné · AR : حب الشباب

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cellulitis ; the patient with cellulitis
  - modèle : The patient in bed 3 has cellulitis.
- **q2.** What does the patient in bed 5 have?
  - accepté : hair loss ; he has hair loss
  - modèle : He has hair loss.
- **q3.** Does the patient in bed 5 have cellulitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have cellulitis ; he doesn't
  - modèle : No, he does not have cellulitis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cellulite (infectieuse) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : acne ; a acne ; he has a acne
  - modèle : He has a acne.
- **Tt2.** Does the patient in bed 9 have hair loss?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hair loss ; he doesn't
  - modèle : No, he does not have hair loss.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS chute de cheveux (alopécie).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … cellulitis and hair loss. (have)` → have · modèle : The patients have cellulitis and hair loss.
- `The patient in bed 5 … not have cellulitis. (do)` → does · modèle : The patient in bed 5 does not have cellulitis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has cellulitis. The patient in bed 5 has hair loss.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Acné, le lit 9 a Cellulite (infectieuse) et n'a pas Chute de cheveux (Alopécie).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a acne. The patient in bed 9 has cellulitis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
