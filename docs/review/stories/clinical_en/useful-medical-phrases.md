# Relecture — Useful Medical Phrases (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-040` — Le cas de Nadia : Depuis combien de temps avez-vous cette douleur? / _Nadia: How long have you had this pain?_

**Objectif :** Comprendre un cas : depuis combien de temps avez-vous cette douleur? présent, respirez profondément s'il vous plaît absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Nadia talks to Lina, a medical student.
   - FR : Nadia parle avec Lina, étudiant·e en médecine.
   - AR : Nadia يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has how long have you had this pain?.
   - FR : Elle a Depuis combien de temps avez-vous cette douleur?.
   - AR : عنده منذ متى وأنت تعاني من هذا الألم؟.
4. **EN** She does not have please take a deep breath.
   - FR : Elle n'a pas de Respirez profondément s'il vous plaît.
   - AR : ليس عنده تنفس بعمق من فضلك.
5. **EN** Lina asks, “Do you have please take a deep breath?”
   - FR : Lina demande : « Avez-vous Respirez profondément s'il vous plaît ? »
   - AR : يسأل Lina: «هل عندك تنفس بعمق من فضلك؟»
6. **EN** Nadia says, “No, I do not.”
   - FR : Nadia répond : « Non. »
   - AR : يجيب Nadia: «لا.»
7. **EN** Lina writes “How long have you had this pain?” in the file.
   - FR : Lina écrit « Depuis combien de temps avez-vous cette douleur? » dans le dossier.
   - AR : يكتب Lina «Depuis combien de temps avez-vous cette douleur?» في الملف.

### Expressions ciblées
- `gen:how-long-have-you-had-this-pain:v1` — **how long have you had this pain?** · FR : Depuis combien de temps avez-vous cette douleur? · AR : منذ متى وأنت تعاني من هذا الألم؟
- `gen:please-take-a-deep-breath:v1` — **please take a deep breath** · FR : Respirez profondément s'il vous plaît · AR : تنفس بعمق من فضلك
- `gen:take-this-medication-twice-a-day:v1` — **a take this medication twice a day** · FR : Prenez ce médicament deux fois par jour · AR : خذ هذا الدواء مرتين في اليوم

### Questions (acceptées / modèle / hint)
- **q1.** Does Nadia have how long have you had this pain??
  - accepté : yes ; yes she does ; yes she has how long have you had this pain? ; she has how long have you had this pain?
  - modèle : Yes, she has how long have you had this pain?.
- **q2.** Does she have please take a deep breath?
  - accepté : no ; no she does not ; no she doesn't ; she does not have please take a deep breath ; she doesn't
  - modèle : No, she does not have please take a deep breath.
  - hint (si erreur) — FR : Attention : Nadia n'a PAS respirez profondément s'il vous plaît.
- **q3.** Does she have how long have you had this pain? or please take a deep breath?
  - accepté : how long have you had this pain? ; she has how long have you had this pain?
  - modèle : She has how long have you had this pain?.
- **q4.** What does Nadia have?
  - accepté : how long have you had this pain? ; she has how long have you had this pain?
  - modèle : She has how long have you had this pain?.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have take this medication twice a day?
  - accepté : yes ; yes he does ; he has a take this medication twice a day
  - modèle : Yes, he has a take this medication twice a day.
- **Tt2.** Does he have how long have you had this pain??
  - accepté : no ; no he does not ; no he doesn't ; he does not have how long have you had this pain? ; he doesn't
  - modèle : No, he does not have how long have you had this pain?.
  - hint (si erreur) — FR : Attention : il n'a PAS depuis combien de temps avez-vous cette douleur?.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … how long have you had this pain?. (have)` → has · modèle : She has how long have you had this pain?.
- `They … not have please take a deep breath. (do)` → do · modèle : They do not have please take a deep breath.
- `I … how long have you had this pain?. (have)` → have · modèle : I have how long have you had this pain?.

### Productions
- **Reformulation :** Rapporte le cas de Nadia en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has how long have you had this pain?. She does not have please take a deep breath.*
- **Transfert (contexte) :** Un autre patient a Prenez ce médicament deux fois par jour. Il n'a pas de Depuis combien de temps avez-vous cette douleur?.
- Glossaire : Prenez ce médicament deux fois par jour : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a take this medication twice a day. He does not have how long have you had this pain?.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-040` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Prenez ce médicament deux fois par jour et Prenez-vous des médicaments? à un patient.
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
4. **EN** “First, we check take this medication twice a day.”
   - FR : « D'abord, nous vérifions Prenez ce médicament deux fois par jour. »
   - AR : «أولًا، نتحقق من خذ هذا الدواء مرتين في اليوم.»
5. **EN** “Then, we check are you on any medication?.”
   - FR : « Ensuite, nous vérifions Prenez-vous des médicaments?. »
   - AR : «ثم نتحقق من هل تتناول أي دواء؟.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:take-this-medication-twice-a-day:v1` — **a take this medication twice a day** · FR : Prenez ce médicament deux fois par jour · AR : خذ هذا الدواء مرتين في اليوم
- `gen:are-you-on-any-medication:v1` — **an are you on any medication?** · FR : Prenez-vous des médicaments? · AR : هل تتناول أي دواء؟
- `gen:do-you-have-any-allergies:v1` — **a do you have any allergies?** · FR : Avez-vous des allergies? · AR : هل لديك أي حساسية؟

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : take this medication twice a day ; we check take this medication twice a day ; take this medication twice a day first ; the doctor checks take this medication twice a day first
  - modèle : First, the doctor checks take this medication twice a day.
- **q2.** What does the doctor check next?
  - accepté : are you on any medication? ; then we check are you on any medication? ; are you on any medication? next ; the doctor checks are you on any medication? next
  - modèle : Then, the doctor checks are you on any medication?.
- **q3.** Does the doctor check are you on any medication? before take this medication twice a day?
  - accepté : no ; no he does not ; no he doesn't ; he checks take this medication twice a day first
  - modèle : No, he checks take this medication twice a day first, then are you on any medication?.
  - hint (si erreur) — FR : Attention : Prenez ce médicament deux fois par jour vient D'ABORD, ensuite Prenez-vous des médicaments?.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : do you have any allergies? ; do you have any allergies? first ; he checks do you have any allergies? first
  - modèle : First, the doctor checks do you have any allergies?.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check take this medication twice a day. Then, we check are you on any medication?.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Avez-vous des allergies?, ensuite Prenez ce médicament deux fois par jour, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check do you have any allergies?. Then, we check take this medication twice a day.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-040` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Depuis combien de temps avez-vous cette douleur? / Respirez profondément s'il vous plaît, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has how long have you had this pain?.
   - FR : Le patient du lit 3 a Depuis combien de temps avez-vous cette douleur?.
   - AR : مريض السرير ٣ عنده منذ متى وأنت تعاني من هذا الألم؟.
4. **EN** The patient in bed 5 has please take a deep breath.
   - FR : Le patient du lit 5 a Respirez profondément s'il vous plaît.
   - AR : مريض السرير ٥ عنده تنفس بعمق من فضلك.
5. **EN** The patient in bed 5 does not have how long have you had this pain?.
   - FR : Le patient du lit 5 n'a pas de Depuis combien de temps avez-vous cette douleur?.
   - AR : مريض السرير ٥ ليس عنده منذ متى وأنت تعاني من هذا الألم؟.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:how-long-have-you-had-this-pain:v1` — **how long have you had this pain?** · FR : Depuis combien de temps avez-vous cette douleur? · AR : منذ متى وأنت تعاني من هذا الألم؟
- `gen:please-take-a-deep-breath:v1` — **please take a deep breath** · FR : Respirez profondément s'il vous plaît · AR : تنفس بعمق من فضلك
- `gen:take-this-medication-twice-a-day:v1` — **a take this medication twice a day** · FR : Prenez ce médicament deux fois par jour · AR : خذ هذا الدواء مرتين في اليوم

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : how long have you had this pain? ; the patient with how long have you had this pain?
  - modèle : The patient in bed 3 has how long have you had this pain?.
- **q2.** What does the patient in bed 5 have?
  - accepté : please take a deep breath
  - modèle : He has please take a deep breath.
- **q3.** Does the patient in bed 5 have how long have you had this pain??
  - accepté : no ; no he does not ; no he doesn't ; he does not have how long have you had this pain? ; he doesn't
  - modèle : No, he does not have how long have you had this pain?.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS depuis combien de temps avez-vous cette douleur? — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : take this medication twice a day ; a take this medication twice a day
  - modèle : He has a take this medication twice a day.
- **Tt2.** Does the patient in bed 9 have please take a deep breath?
  - accepté : no ; no he does not ; no he doesn't ; he does not have please take a deep breath ; he doesn't
  - modèle : No, he does not have please take a deep breath.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS respirez profondément s'il vous plaît.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … how long have you had this pain? and please take a deep breath. (have)` → have · modèle : The patients have how long have you had this pain? and please take a deep breath.
- `The patient in bed 5 … not have how long have you had this pain?. (do)` → does · modèle : The patient in bed 5 does not have how long have you had this pain?.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has how long have you had this pain?. The patient in bed 5 has please take a deep breath.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Prenez ce médicament deux fois par jour, le lit 9 a Depuis combien de temps avez-vous cette douleur? et n'a pas Respirez profondément s'il vous plaît.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a take this medication twice a day. The patient in bed 9 has how long have you had this pain?.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
