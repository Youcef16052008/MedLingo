# Relecture — Talking to Your Doctor (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-038` — Le cas de Leila : Où avez-vous mal? / _Leila: Where does it hurt?_

**Objectif :** Comprendre un cas : où avez-vous mal? présent, depuis combien de temps? absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Leila talks to Lina, a medical student.
   - FR : Leila parle avec Lina, étudiant·e en médecine.
   - AR : Leila يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a where does it hurt?.
   - FR : Elle a Où avez-vous mal?.
   - AR : عنده أين تشعر بالألم؟.
4. **EN** She does not have a how long has this been going on?.
   - FR : Elle n'a pas de Depuis combien de temps?.
   - AR : ليس عنده منذ متى بدأت هذه الحالة؟.
5. **EN** Lina asks, “Do you have a how long has this been going on??”
   - FR : Lina demande : « Avez-vous Depuis combien de temps? ? »
   - AR : يسأل Lina: «هل عندك منذ متى بدأت هذه الحالة؟؟»
6. **EN** Leila says, “No, I do not.”
   - FR : Leila répond : « Non. »
   - AR : يجيب Leila: «لا.»
7. **EN** Lina writes “Where does it hurt?” in the file.
   - FR : Lina écrit « Où avez-vous mal? » dans le dossier.
   - AR : يكتب Lina «Où avez-vous mal?» في الملف.

### Expressions ciblées
- `gen:where-does-it-hurt:v1` — **a where does it hurt?** · FR : Où avez-vous mal? · AR : أين تشعر بالألم؟
- `gen:how-long-has-this-been-going-on:v1` — **a how long has this been going on?** · FR : Depuis combien de temps? · AR : منذ متى بدأت هذه الحالة؟
- `gen:does-anything-make-it-better:v1` — **a does anything make it better?** · FR : Est-ce que quelque chose soulage la douleur? · AR : هل هناك ما يخفف الألم؟

### Questions (acceptées / modèle / hint)
- **q1.** Does Leila have where does it hurt??
  - accepté : yes ; yes she does ; yes she has a where does it hurt? ; she has a where does it hurt?
  - modèle : Yes, she has a where does it hurt?.
- **q2.** Does she have how long has this been going on??
  - accepté : no ; no she does not ; no she doesn't ; she does not have a how long has this been going on? ; she doesn't
  - modèle : No, she does not have a how long has this been going on?.
  - hint (si erreur) — FR : Attention : Leila n'a PAS depuis combien de temps?.
- **q3.** Does she have where does it hurt? or how long has this been going on??
  - accepté : where does it hurt? ; a where does it hurt? ; she has a where does it hurt?
  - modèle : She has a where does it hurt?.
- **q4.** What does Leila have?
  - accepté : where does it hurt? ; a where does it hurt? ; she has a where does it hurt?
  - modèle : She has a where does it hurt?.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have does anything make it better??
  - accepté : yes ; yes he does ; he has a does anything make it better?
  - modèle : Yes, he has a does anything make it better?.
- **Tt2.** Does he have where does it hurt??
  - accepté : no ; no he does not ; no he doesn't ; he does not have a where does it hurt? ; he doesn't
  - modèle : No, he does not have a where does it hurt?.
  - hint (si erreur) — FR : Attention : il n'a PAS où avez-vous mal?.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a where does it hurt?. (have)` → has · modèle : She has a where does it hurt?.
- `They … not have a how long has this been going on?. (do)` → do · modèle : They do not have a how long has this been going on?.
- `I … a where does it hurt?. (have)` → have · modèle : I have a where does it hurt?.

### Productions
- **Reformulation :** Rapporte le cas de Leila en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a where does it hurt?. She does not have a how long has this been going on?.*
- **Transfert (contexte) :** Un autre patient a Est-ce que quelque chose soulage la douleur?. Il n'a pas de Où avez-vous mal?.
- Glossaire : Est-ce que quelque chose soulage la douleur? : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a does anything make it better?. He does not have a where does it hurt?.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-038` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Où avez-vous mal? et Depuis combien de temps? à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient.
   - FR : Farid est un·e patient·e.
   - AR : Farid مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check where does it hurt?.”
   - FR : « D'abord, nous vérifions Où avez-vous mal?. »
   - AR : «أولًا، نتحقق من أين تشعر بالألم؟.»
5. **EN** “Then, we check how long has this been going on?.”
   - FR : « Ensuite, nous vérifions Depuis combien de temps?. »
   - AR : «ثم نتحقق من منذ متى بدأت هذه الحالة؟.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:where-does-it-hurt:v1` — **a where does it hurt?** · FR : Où avez-vous mal? · AR : أين تشعر بالألم؟
- `gen:how-long-has-this-been-going-on:v1` — **a how long has this been going on?** · FR : Depuis combien de temps? · AR : منذ متى بدأت هذه الحالة؟
- `gen:does-anything-make-it-better:v1` — **a does anything make it better?** · FR : Est-ce que quelque chose soulage la douleur? · AR : هل هناك ما يخفف الألم؟

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : where does it hurt? ; we check where does it hurt? ; where does it hurt? first ; the doctor checks where does it hurt? first
  - modèle : First, the doctor checks where does it hurt?.
- **q2.** What does the doctor check next?
  - accepté : how long has this been going on? ; then we check how long has this been going on? ; how long has this been going on? next ; the doctor checks how long has this been going on? next
  - modèle : Then, the doctor checks how long has this been going on?.
- **q3.** Does the doctor check how long has this been going on? before where does it hurt??
  - accepté : no ; no he does not ; no he doesn't ; he checks where does it hurt? first
  - modèle : No, he checks where does it hurt? first, then how long has this been going on?.
  - hint (si erreur) — FR : Attention : Où avez-vous mal? vient D'ABORD, ensuite Depuis combien de temps?.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : does anything make it better? ; does anything make it better? first ; he checks does anything make it better? first
  - modèle : First, the doctor checks does anything make it better?.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check where does it hurt?. Then, we check how long has this been going on?.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Est-ce que quelque chose soulage la douleur?, ensuite Où avez-vous mal?, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check does anything make it better?. Then, we check where does it hurt?.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-038` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Où avez-vous mal? / Depuis combien de temps?, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a where does it hurt?.
   - FR : Le patient du lit 3 a Où avez-vous mal?.
   - AR : مريض السرير ٣ عنده أين تشعر بالألم؟.
4. **EN** The patient in bed 5 has a how long has this been going on?.
   - FR : Le patient du lit 5 a Depuis combien de temps?.
   - AR : مريض السرير ٥ عنده منذ متى بدأت هذه الحالة؟.
5. **EN** The patient in bed 5 does not have a where does it hurt?.
   - FR : Le patient du lit 5 n'a pas de Où avez-vous mal?.
   - AR : مريض السرير ٥ ليس عنده أين تشعر بالألم؟.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:where-does-it-hurt:v1` — **a where does it hurt?** · FR : Où avez-vous mal? · AR : أين تشعر بالألم؟
- `gen:how-long-has-this-been-going-on:v1` — **a how long has this been going on?** · FR : Depuis combien de temps? · AR : منذ متى بدأت هذه الحالة؟
- `gen:does-anything-make-it-better:v1` — **a does anything make it better?** · FR : Est-ce que quelque chose soulage la douleur? · AR : هل هناك ما يخفف الألم؟

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : where does it hurt? ; a where does it hurt? ; the patient with where does it hurt?
  - modèle : The patient in bed 3 has a where does it hurt?.
- **q2.** What does the patient in bed 5 have?
  - accepté : how long has this been going on? ; a how long has this been going on? ; he has a how long has this been going on?
  - modèle : He has a how long has this been going on?.
- **q3.** Does the patient in bed 5 have where does it hurt??
  - accepté : no ; no he does not ; no he doesn't ; he does not have a where does it hurt? ; he doesn't
  - modèle : No, he does not have a where does it hurt?.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS où avez-vous mal? — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : does anything make it better? ; a does anything make it better? ; he has a does anything make it better?
  - modèle : He has a does anything make it better?.
- **Tt2.** Does the patient in bed 9 have how long has this been going on??
  - accepté : no ; no he does not ; no he doesn't ; he does not have a how long has this been going on? ; he doesn't
  - modèle : No, he does not have a how long has this been going on?.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS depuis combien de temps?.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a where does it hurt? and a how long has this been going on?. (have)` → have · modèle : The patients have a where does it hurt? and a how long has this been going on?.
- `The patient in bed 5 … not have a where does it hurt?. (do)` → does · modèle : The patient in bed 5 does not have a where does it hurt?.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a where does it hurt?. The patient in bed 5 has a how long has this been going on?.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Est-ce que quelque chose soulage la douleur?, le lit 9 a Où avez-vous mal? et n'a pas Depuis combien de temps?.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a does anything make it better?. The patient in bed 9 has a where does it hurt?.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
