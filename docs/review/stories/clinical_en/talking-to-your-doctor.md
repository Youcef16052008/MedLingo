# Relecture — Talking to Your Doctor (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-038` — Le cas de Youssef : Est-ce que quelque chose soulage la douleur? anormal(e) / _Youssef: the does anything make it better? is not normal_

**Objectif :** Comprendre des résultats : Où avez-vous mal? normal(e), Est-ce que quelque chose soulage la douleur? anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The where does it hurt? is normal.”
   - FR : Le médecin dit : « Où avez-vous mal? est normal(e). »
   - AR : يقول الطبيب: «أين تشعر بالألم؟ طبيعي/طبيعية.»
5. **EN** “But the does anything make it better? is not normal.”
   - FR : « Mais Est-ce que quelque chose soulage la douleur? n'est pas normal(e). »
   - AR : «لكن هل هناك ما يخفف الألم؟ غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the does anything make it better? normal?”
   - FR : Lina demande : « Est-ce que quelque chose soulage la douleur? est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل هل هناك ما يخفف الألم؟ طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:where-does-it-hurt:v1` — **a where does it hurt?** · FR : Où avez-vous mal? · AR : أين تشعر بالألم؟
- `gen:how-long-has-this-been-going-on:v1` — **a how long has this been going on?** · FR : Depuis combien de temps? · AR : منذ متى بدأت هذه الحالة؟
- `gen:does-anything-make-it-better:v1` — **a does anything make it better?** · FR : Est-ce que quelque chose soulage la douleur? · AR : هل هناك ما يخفف الألم؟

### Questions (acceptées / modèle / hint)
- **q1.** Is the where does it hurt? normal?
  - accepté : yes ; yes it is ; the where does it hurt? is normal ; it is normal
  - modèle : Yes, the where does it hurt? is normal.
- **q2.** Is the does anything make it better? normal?
  - accepté : no ; no it is not ; no it isn't ; the does anything make it better? is not normal ; it is not normal ; it isn't normal
  - modèle : No, the does anything make it better? is not normal.
  - hint (si erreur) — FR : Attention : Est-ce que quelque chose soulage la douleur? n'est PAS normal(e).
- **q3.** Which one is not normal: the where does it hurt? or the does anything make it better??
  - accepté : does anything make it better? ; the does anything make it better?
  - modèle : The does anything make it better? is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the how long has this been going on? normal?
  - accepté : yes ; yes it is ; the how long has this been going on? is normal
  - modèle : Yes, the how long has this been going on? is normal.
- **Tt2.** Is the where does it hurt? normal?
  - accepté : no ; no it is not ; no it isn't ; the where does it hurt? is not normal ; it is not normal
  - modèle : No, the where does it hurt? is not normal.
  - hint (si erreur) — FR : Attention : Où avez-vous mal? n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The where does it hurt? … normal. (be)` → is · modèle : The where does it hurt? is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The where does it hurt? is… / The does anything make it better? is not… — modèle : *The where does it hurt? is normal. The does anything make it better? is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Depuis combien de temps? normal(e), mais Où avez-vous mal? anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The how long has this been going on? is normal. The where does it hurt? is not normal.*

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
3. **EN** The patient in bed 3 has a problem with the where does it hurt?.
   - FR : Le patient du lit 3 a Où avez-vous mal?.
   - AR : مريض السرير ٣ عنده أين تشعر بالألم؟.
4. **EN** The patient in bed 5 has a problem with the how long has this been going on?.
   - FR : Le patient du lit 5 a Depuis combien de temps?.
   - AR : مريض السرير ٥ عنده منذ متى بدأت هذه الحالة؟.
5. **EN** The patient in bed 5 does not have a problem with the where does it hurt?.
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
  - accepté : how long has this been going on? ; a problem with the how long has this been going on?
  - modèle : He has a problem with the how long has this been going on?.
- **q3.** Does the patient in bed 5 have where does it hurt??
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the where does it hurt? ; he doesn't
  - modèle : No, he does not have a problem with the where does it hurt?.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS où avez-vous mal? — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : does anything make it better? ; a problem with the does anything make it better?
  - modèle : He has a problem with the does anything make it better?.
- **Tt2.** Does the patient in bed 9 have how long has this been going on??
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the how long has this been going on? ; he doesn't
  - modèle : No, he does not have a problem with the how long has this been going on?.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS depuis combien de temps?.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a where does it hurt? and a how long has this been going on?. (have)` → have · modèle : The patients have a where does it hurt? and a how long has this been going on?.
- `The patient in bed 5 … not have a where does it hurt?. (do)` → does · modèle : The patient in bed 5 does not have a where does it hurt?.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the where does it hurt?. The patient in bed 5 has a problem with the how long has this been going on?.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Est-ce que quelque chose soulage la douleur?, le lit 9 a Où avez-vous mal? et n'a pas Depuis combien de temps?.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the does anything make it better?. The patient in bed 9 has a problem with the where does it hurt?.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
