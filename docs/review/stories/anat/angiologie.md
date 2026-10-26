# Relecture — Angiologie (anat)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anat-case-000` — Le cas de Karim : Ventricule anormal(e) / _Karim: the ventricle is not normal_

**Objectif :** Comprendre des résultats : Cœur normal(e), Ventricule anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient at the clinic.
   - FR : Karim est un·e patient·e à la clinique.
   - AR : Karim مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The heart is normal.”
   - FR : Le médecin dit : « Cœur est normal(e). »
   - AR : يقول الطبيب: «القلب طبيعي/طبيعية.»
5. **EN** “But the ventricle is not normal.”
   - FR : « Mais Ventricule n'est pas normal(e). »
   - AR : «لكن البطين غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the ventricle normal?”
   - FR : Lina demande : « Ventricule est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل البطين طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب
- `gen:atrium:v1` — **an atrium** · FR : Oreillette · AR : الأذين
- `gen:ventricle:v1` — **a ventricle** · FR : Ventricule · AR : البطين

### Questions (acceptées / modèle / hint)
- **q1.** Is the heart normal?
  - accepté : yes ; yes it is ; the heart is normal ; it is normal
  - modèle : Yes, the heart is normal.
- **q2.** Is the ventricle normal?
  - accepté : no ; no it is not ; no it isn't ; the ventricle is not normal ; it is not normal ; it isn't normal
  - modèle : No, the ventricle is not normal.
  - hint (si erreur) — FR : Attention : Ventricule n'est PAS normal(e).
- **q3.** Which one is not normal: the heart or the ventricle?
  - accepté : ventricle ; the ventricle
  - modèle : The ventricle is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the atrium normal?
  - accepté : yes ; yes it is ; the atrium is normal
  - modèle : Yes, the atrium is normal.
- **Tt2.** Is the heart normal?
  - accepté : no ; no it is not ; no it isn't ; the heart is not normal ; it is not normal
  - modèle : No, the heart is not normal.
  - hint (si erreur) — FR : Attention : Cœur n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The heart … normal. (be)` → is · modèle : The heart is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The heart is… / The ventricle is not… — modèle : *The heart is normal. The ventricle is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Oreillette normal(e), mais Cœur anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The atrium is normal. The heart is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anat-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Cœur et Oreillette à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient.
   - FR : Youssef est un·e patient·e.
   - AR : Youssef مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check heart.”
   - FR : « D'abord, nous vérifions Cœur. »
   - AR : «أولًا، نتحقق من القلب.»
5. **EN** “Then, we check atrium.”
   - FR : « Ensuite, nous vérifions Oreillette. »
   - AR : «ثم نتحقق من الأذين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب
- `gen:atrium:v1` — **an atrium** · FR : Oreillette · AR : الأذين
- `gen:ventricle:v1` — **a ventricle** · FR : Ventricule · AR : البطين

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : heart ; we check heart ; heart first ; the doctor checks heart first
  - modèle : First, the doctor checks heart.
- **q2.** What does the doctor check next?
  - accepté : atrium ; then we check atrium ; atrium next ; the doctor checks atrium next
  - modèle : Then, the doctor checks atrium.
- **q3.** Does the doctor check atrium before heart?
  - accepté : no ; no he does not ; no he doesn't ; he checks heart first
  - modèle : No, he checks heart first, then atrium.
  - hint (si erreur) — FR : Attention : Cœur vient D'ABORD, ensuite Oreillette.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : ventricle ; ventricle first ; he checks ventricle first
  - modèle : First, the doctor checks ventricle.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check heart. Then, we check atrium.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Ventricule, ensuite Cœur, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check ventricle. Then, we check heart.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anat-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cœur / Oreillette, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the heart.
   - FR : Le patient du lit 3 a Cœur.
   - AR : مريض السرير ٣ عنده القلب.
4. **EN** The patient in bed 5 has a problem with the atrium.
   - FR : Le patient du lit 5 a Oreillette.
   - AR : مريض السرير ٥ عنده الأذين.
5. **EN** The patient in bed 5 does not have a problem with the heart.
   - FR : Le patient du lit 5 n'a pas de Cœur.
   - AR : مريض السرير ٥ ليس عنده القلب.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب
- `gen:atrium:v1` — **an atrium** · FR : Oreillette · AR : الأذين
- `gen:ventricle:v1` — **a ventricle** · FR : Ventricule · AR : البطين

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : heart ; a heart ; the patient with heart
  - modèle : The patient in bed 3 has a heart.
- **q2.** What does the patient in bed 5 have?
  - accepté : atrium ; a problem with the atrium
  - modèle : He has a problem with the atrium.
- **q3.** Does the patient in bed 5 have heart?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the heart ; he doesn't
  - modèle : No, he does not have a problem with the heart.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cœur — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : ventricle ; a problem with the ventricle
  - modèle : He has a problem with the ventricle.
- **Tt2.** Does the patient in bed 9 have atrium?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the atrium ; he doesn't
  - modèle : No, he does not have a problem with the atrium.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS oreillette.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a heart and an atrium. (have)` → have · modèle : The patients have a heart and an atrium.
- `The patient in bed 5 … not have a heart. (do)` → does · modèle : The patient in bed 5 does not have a heart.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the heart. The patient in bed 5 has a problem with the atrium.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Ventricule, le lit 9 a Cœur et n'a pas Oreillette.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the ventricle. The patient in bed 9 has a problem with the heart.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
