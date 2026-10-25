# Relecture — Développement Sensoriel (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-006` — Le cas de Amel : Vésicule optique / _Amel: Optic vesicle_

**Objectif :** Comprendre un cas : vésicule optique présent, vésicule otique (otocyste) absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Amel talks to Lina, a medical student.
   - FR : Amel parle avec Lina, étudiant·e en médecine.
   - AR : Amel يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has optic vesicle.
   - FR : Elle a Vésicule optique.
   - AR : عنده الحويصلة البصرية.
4. **EN** She does not have otic vesicle.
   - FR : Elle n'a pas de Vésicule otique (Otocyste).
   - AR : ليس عنده الحويصلة السمعية.
5. **EN** Lina asks, “Do you have otic vesicle?”
   - FR : Lina demande : « Avez-vous Vésicule otique (Otocyste) ? »
   - AR : يسأل Lina: «هل عندك الحويصلة السمعية؟»
6. **EN** Amel says, “No, I do not.”
   - FR : Amel répond : « Non. »
   - AR : يجيب Amel: «لا.»
7. **EN** Lina writes “Optic vesicle” in the file.
   - FR : Lina écrit « Vésicule optique » dans le dossier.
   - AR : يكتب Lina «Vésicule optique» في الملف.

### Expressions ciblées
- `gen:optic-vesicle:v1` — **optic vesicle** · FR : Vésicule optique · AR : الحويصلة البصرية
- `gen:otic-vesicle:v1` — **otic vesicle** · FR : Vésicule otique (Otocyste) · AR : الحويصلة السمعية
- `gen:optic-cup:v1` — **an optic cup** · FR : Coupe optique · AR : الكأس البصري

### Questions (acceptées / modèle / hint)
- **q1.** Does Amel have optic vesicle?
  - accepté : yes ; yes she does ; yes she has optic vesicle ; she has optic vesicle
  - modèle : Yes, she has optic vesicle.
- **q2.** Does she have otic vesicle?
  - accepté : no ; no she does not ; no she doesn't ; she does not have otic vesicle ; she doesn't
  - modèle : No, she does not have otic vesicle.
  - hint (si erreur) — FR : Attention : Amel n'a PAS vésicule otique (otocyste).
- **q3.** Does she have optic vesicle or otic vesicle?
  - accepté : optic vesicle ; she has optic vesicle
  - modèle : She has optic vesicle.
- **q4.** What does Amel have?
  - accepté : optic vesicle ; she has optic vesicle
  - modèle : She has optic vesicle.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have optic cup?
  - accepté : yes ; yes he does ; he has an optic cup
  - modèle : Yes, he has an optic cup.
- **Tt2.** Does he have optic vesicle?
  - accepté : no ; no he does not ; no he doesn't ; he does not have optic vesicle ; he doesn't
  - modèle : No, he does not have optic vesicle.
  - hint (si erreur) — FR : Attention : il n'a PAS vésicule optique.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … optic vesicle. (have)` → has · modèle : She has optic vesicle.
- `They … not have otic vesicle. (do)` → do · modèle : They do not have otic vesicle.
- `I … optic vesicle. (have)` → have · modèle : I have optic vesicle.

### Productions
- **Reformulation :** Rapporte le cas de Amel en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has optic vesicle. She does not have otic vesicle.*
- **Transfert (contexte) :** Un autre patient a Coupe optique. Il n'a pas de Vésicule optique.
- Glossaire : Coupe optique : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has an optic cup. He does not have optic vesicle.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Vésicule optique et Coupe optique à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient.
   - FR : Bilal est un·e patient·e.
   - AR : Bilal مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check optic vesicle.”
   - FR : « D'abord, nous vérifions Vésicule optique. »
   - AR : «أولًا، نتحقق من الحويصلة البصرية.»
5. **EN** “Then, we check optic cup.”
   - FR : « Ensuite, nous vérifions Coupe optique. »
   - AR : «ثم نتحقق من الكأس البصري.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:optic-vesicle:v1` — **optic vesicle** · FR : Vésicule optique · AR : الحويصلة البصرية
- `gen:optic-cup:v1` — **an optic cup** · FR : Coupe optique · AR : الكأس البصري
- `gen:lens-placode:v1` — **a lens placode** · FR : Placode du cristallin · AR : لويحة العدسة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : optic vesicle ; we check optic vesicle ; optic vesicle first ; the doctor checks optic vesicle first
  - modèle : First, the doctor checks optic vesicle.
- **q2.** What does the doctor check next?
  - accepté : optic cup ; then we check optic cup ; optic cup next ; the doctor checks optic cup next
  - modèle : Then, the doctor checks optic cup.
- **q3.** Does the doctor check optic cup before optic vesicle?
  - accepté : no ; no he does not ; no he doesn't ; he checks optic vesicle first
  - modèle : No, he checks optic vesicle first, then optic cup.
  - hint (si erreur) — FR : Attention : Vésicule optique vient D'ABORD, ensuite Coupe optique.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : lens placode ; lens placode first ; he checks lens placode first
  - modèle : First, the doctor checks lens placode.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check optic vesicle. Then, we check optic cup.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Placode du cristallin, ensuite Vésicule optique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check lens placode. Then, we check optic vesicle.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Vésicule optique / Vésicule otique (Otocyste), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has optic vesicle.
   - FR : Le patient du lit 3 a Vésicule optique.
   - AR : مريض السرير ٣ عنده الحويصلة البصرية.
4. **EN** The patient in bed 5 has otic vesicle.
   - FR : Le patient du lit 5 a Vésicule otique (Otocyste).
   - AR : مريض السرير ٥ عنده الحويصلة السمعية.
5. **EN** The patient in bed 5 does not have optic vesicle.
   - FR : Le patient du lit 5 n'a pas de Vésicule optique.
   - AR : مريض السرير ٥ ليس عنده الحويصلة البصرية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:optic-vesicle:v1` — **optic vesicle** · FR : Vésicule optique · AR : الحويصلة البصرية
- `gen:otic-vesicle:v1` — **otic vesicle** · FR : Vésicule otique (Otocyste) · AR : الحويصلة السمعية
- `gen:optic-cup:v1` — **an optic cup** · FR : Coupe optique · AR : الكأس البصري

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : optic vesicle ; the patient with optic vesicle
  - modèle : The patient in bed 3 has optic vesicle.
- **q2.** What does the patient in bed 5 have?
  - accepté : otic vesicle
  - modèle : He has otic vesicle.
- **q3.** Does the patient in bed 5 have optic vesicle?
  - accepté : no ; no he does not ; no he doesn't ; he does not have optic vesicle ; he doesn't
  - modèle : No, he does not have optic vesicle.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS vésicule optique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : optic cup ; an optic cup
  - modèle : He has an optic cup.
- **Tt2.** Does the patient in bed 9 have otic vesicle?
  - accepté : no ; no he does not ; no he doesn't ; he does not have otic vesicle ; he doesn't
  - modèle : No, he does not have otic vesicle.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS vésicule otique (otocyste).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … optic vesicle and otic vesicle. (have)` → have · modèle : The patients have optic vesicle and otic vesicle.
- `The patient in bed 5 … not have optic vesicle. (do)` → does · modèle : The patient in bed 5 does not have optic vesicle.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has optic vesicle. The patient in bed 5 has otic vesicle.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Coupe optique, le lit 9 a Vésicule optique et n'a pas Vésicule otique (Otocyste).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has an optic cup. The patient in bed 9 has optic vesicle.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
