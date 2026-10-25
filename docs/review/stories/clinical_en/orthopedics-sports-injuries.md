# Relecture — Orthopedics & Sports Injuries (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-028` — Le cas de Nadia : Claquage musculaire / _Nadia: Strain_

**Objectif :** Comprendre un cas : claquage musculaire présent, luxation absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Nadia talks to Lina, a medical student.
   - FR : Nadia parle avec Lina, étudiant·e en médecine.
   - AR : Nadia يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a strain.
   - FR : Elle a Claquage musculaire.
   - AR : عنده شد عضلي.
4. **EN** She does not have a dislocation.
   - FR : Elle n'a pas de Luxation.
   - AR : ليس عنده خلع المفصل.
5. **EN** Lina asks, “Do you have a dislocation?”
   - FR : Lina demande : « Avez-vous Luxation ? »
   - AR : يسأل Lina: «هل عندك خلع المفصل؟»
6. **EN** Nadia says, “No, I do not.”
   - FR : Nadia répond : « Non. »
   - AR : يجيب Nadia: «لا.»
7. **EN** Lina writes “Strain” in the file.
   - FR : Lina écrit « Claquage musculaire » dans le dossier.
   - AR : يكتب Lina «Claquage musculaire» في الملف.

### Expressions ciblées
- `gen:strain:v1` — **a strain** · FR : Claquage musculaire · AR : شد عضلي
- `gen:dislocation:v1` — **a dislocation** · FR : Luxation · AR : خلع المفصل
- `gen:tendinitis:v1` — **tendinitis** · FR : Tendinite · AR : التهاب الوتر

### Questions (acceptées / modèle / hint)
- **q1.** Does Nadia have strain?
  - accepté : yes ; yes she does ; yes she has a strain ; she has a strain
  - modèle : Yes, she has a strain.
- **q2.** Does she have dislocation?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a dislocation ; she doesn't
  - modèle : No, she does not have a dislocation.
  - hint (si erreur) — FR : Attention : Nadia n'a PAS luxation.
- **q3.** Does she have strain or dislocation?
  - accepté : strain ; a strain ; she has a strain
  - modèle : She has a strain.
- **q4.** What does Nadia have?
  - accepté : strain ; a strain ; she has a strain
  - modèle : She has a strain.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have tendinitis?
  - accepté : yes ; yes he does ; he has tendinitis
  - modèle : Yes, he has tendinitis.
- **Tt2.** Does he have strain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a strain ; he doesn't
  - modèle : No, he does not have a strain.
  - hint (si erreur) — FR : Attention : il n'a PAS claquage musculaire.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a strain. (have)` → has · modèle : She has a strain.
- `They … not have a dislocation. (do)` → do · modèle : They do not have a dislocation.
- `I … a strain. (have)` → have · modèle : I have a strain.

### Productions
- **Reformulation :** Rapporte le cas de Nadia en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a strain. She does not have a dislocation.*
- **Transfert (contexte) :** Un autre patient a Tendinite. Il n'a pas de Claquage musculaire.
- Glossaire : Tendinite : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has tendinitis. He does not have a strain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-028` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Claquage musculaire et Luxation à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient.
   - FR : Sofiane est un·e patient·e.
   - AR : Sofiane مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check strain.”
   - FR : « D'abord, nous vérifions Claquage musculaire. »
   - AR : «أولًا، نتحقق من شد عضلي.»
5. **EN** “Then, we check dislocation.”
   - FR : « Ensuite, nous vérifions Luxation. »
   - AR : «ثم نتحقق من خلع المفصل.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:strain:v1` — **a strain** · FR : Claquage musculaire · AR : شد عضلي
- `gen:dislocation:v1` — **a dislocation** · FR : Luxation · AR : خلع المفصل
- `gen:tendinitis:v1` — **tendinitis** · FR : Tendinite · AR : التهاب الوتر

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : strain ; we check strain ; strain first ; the doctor checks strain first
  - modèle : First, the doctor checks strain.
- **q2.** What does the doctor check next?
  - accepté : dislocation ; then we check dislocation ; dislocation next ; the doctor checks dislocation next
  - modèle : Then, the doctor checks dislocation.
- **q3.** Does the doctor check dislocation before strain?
  - accepté : no ; no he does not ; no he doesn't ; he checks strain first
  - modèle : No, he checks strain first, then dislocation.
  - hint (si erreur) — FR : Attention : Claquage musculaire vient D'ABORD, ensuite Luxation.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : tendinitis ; tendinitis first ; he checks tendinitis first
  - modèle : First, the doctor checks tendinitis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check strain. Then, we check dislocation.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Tendinite, ensuite Claquage musculaire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check tendinitis. Then, we check strain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-028` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Claquage musculaire / Luxation, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a strain.
   - FR : Le patient du lit 3 a Claquage musculaire.
   - AR : مريض السرير ٣ عنده شد عضلي.
4. **EN** The patient in bed 5 has a dislocation.
   - FR : Le patient du lit 5 a Luxation.
   - AR : مريض السرير ٥ عنده خلع المفصل.
5. **EN** The patient in bed 5 does not have a strain.
   - FR : Le patient du lit 5 n'a pas de Claquage musculaire.
   - AR : مريض السرير ٥ ليس عنده شد عضلي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:strain:v1` — **a strain** · FR : Claquage musculaire · AR : شد عضلي
- `gen:dislocation:v1` — **a dislocation** · FR : Luxation · AR : خلع المفصل
- `gen:tendinitis:v1` — **tendinitis** · FR : Tendinite · AR : التهاب الوتر

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : strain ; a strain ; the patient with strain
  - modèle : The patient in bed 3 has a strain.
- **q2.** What does the patient in bed 5 have?
  - accepté : dislocation ; a dislocation ; he has a dislocation
  - modèle : He has a dislocation.
- **q3.** Does the patient in bed 5 have strain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a strain ; he doesn't
  - modèle : No, he does not have a strain.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS claquage musculaire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : tendinitis ; he has tendinitis
  - modèle : He has tendinitis.
- **Tt2.** Does the patient in bed 9 have dislocation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a dislocation ; he doesn't
  - modèle : No, he does not have a dislocation.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS luxation.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a strain and a dislocation. (have)` → have · modèle : The patients have a strain and a dislocation.
- `The patient in bed 5 … not have a strain. (do)` → does · modèle : The patient in bed 5 does not have a strain.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a strain. The patient in bed 5 has a dislocation.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Tendinite, le lit 9 a Claquage musculaire et n'a pas Luxation.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has tendinitis. The patient in bed 9 has a strain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
