# Relecture — Symptoms & How You Feel (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-037` — Le cas de Omar : Douleur / _Omar: Pain_

**Objectif :** Comprendre un cas : douleur présent, douleur sourde absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Omar talks to Amine, a medical student.
   - FR : Omar parle avec Amine, étudiant·e en médecine.
   - AR : Omar يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has pain.
   - FR : Il a Douleur.
   - AR : عنده ألم.
4. **EN** He does not have ache.
   - FR : Il n'a pas de Douleur sourde.
   - AR : ليس عنده وجع مستمر.
5. **EN** Amine asks, “Do you have ache?”
   - FR : Amine demande : « Avez-vous Douleur sourde ? »
   - AR : يسأل Amine: «هل عندك وجع مستمر؟»
6. **EN** Omar says, “No, I do not.”
   - FR : Omar répond : « Non. »
   - AR : يجيب Omar: «لا.»
7. **EN** Amine writes “Pain” in the file.
   - FR : Amine écrit « Douleur » dans le dossier.
   - AR : يكتب Amine «Douleur» في الملف.

### Expressions ciblées
- `gen:pain:v1` — **pain** · FR : Douleur · AR : ألم
- `gen:ache:v1` — **ache** · FR : Douleur sourde · AR : وجع مستمر
- `gen:fever:v1` — **fever** · FR : Fièvre · AR : حُمى

### Questions (acceptées / modèle / hint)
- **q1.** Does Omar have pain?
  - accepté : yes ; yes he does ; yes he has pain ; he has pain
  - modèle : Yes, he has pain.
- **q2.** Does he have ache?
  - accepté : no ; no he does not ; no he doesn't ; he does not have ache ; he doesn't
  - modèle : No, he does not have ache.
  - hint (si erreur) — FR : Attention : Omar n'a PAS douleur sourde.
- **q3.** Does he have pain or ache?
  - accepté : pain ; he has pain
  - modèle : He has pain.
- **q4.** What does Omar have?
  - accepté : pain ; he has pain
  - modèle : He has pain.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have fever?
  - accepté : yes ; yes he does ; he has fever
  - modèle : Yes, he has fever.
- **Tt2.** Does he have pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have pain ; he doesn't
  - modèle : No, he does not have pain.
  - hint (si erreur) — FR : Attention : il n'a PAS douleur.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … pain. (have)` → has · modèle : He has pain.
- `They … not have ache. (do)` → do · modèle : They do not have ache.
- `I … pain. (have)` → have · modèle : I have pain.

### Productions
- **Reformulation :** Rapporte le cas de Omar en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has pain. He does not have ache.*
- **Transfert (contexte) :** Un autre patient a Fièvre. Il n'a pas de Douleur.
- Glossaire : Fièvre : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has fever. He does not have pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-037` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Douleur et Douleur sourde à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient.
   - FR : Amel est un·e patient·e.
   - AR : Amel مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check pain.”
   - FR : « D'abord, nous vérifions Douleur. »
   - AR : «أولًا، نتحقق من ألم.»
5. **EN** “Then, we check ache.”
   - FR : « Ensuite, nous vérifions Douleur sourde. »
   - AR : «ثم نتحقق من وجع مستمر.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:pain:v1` — **pain** · FR : Douleur · AR : ألم
- `gen:ache:v1` — **ache** · FR : Douleur sourde · AR : وجع مستمر
- `gen:fever:v1` — **fever** · FR : Fièvre · AR : حُمى

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : pain ; we check pain ; pain first ; the doctor checks pain first
  - modèle : First, the doctor checks pain.
- **q2.** What does the doctor check next?
  - accepté : ache ; then we check ache ; ache next ; the doctor checks ache next
  - modèle : Then, the doctor checks ache.
- **q3.** Does the doctor check ache before pain?
  - accepté : no ; no he does not ; no he doesn't ; he checks pain first
  - modèle : No, he checks pain first, then ache.
  - hint (si erreur) — FR : Attention : Douleur vient D'ABORD, ensuite Douleur sourde.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : fever ; fever first ; he checks fever first
  - modèle : First, the doctor checks fever.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check pain. Then, we check ache.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fièvre, ensuite Douleur, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check fever. Then, we check pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-037` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Douleur / Douleur sourde, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has pain.
   - FR : Le patient du lit 3 a Douleur.
   - AR : مريض السرير ٣ عنده ألم.
4. **EN** The patient in bed 5 has ache.
   - FR : Le patient du lit 5 a Douleur sourde.
   - AR : مريض السرير ٥ عنده وجع مستمر.
5. **EN** The patient in bed 5 does not have pain.
   - FR : Le patient du lit 5 n'a pas de Douleur.
   - AR : مريض السرير ٥ ليس عنده ألم.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:pain:v1` — **pain** · FR : Douleur · AR : ألم
- `gen:ache:v1` — **ache** · FR : Douleur sourde · AR : وجع مستمر
- `gen:fever:v1` — **fever** · FR : Fièvre · AR : حُمى

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : pain ; the patient with pain
  - modèle : The patient in bed 3 has pain.
- **q2.** What does the patient in bed 5 have?
  - accepté : ache ; he has ache
  - modèle : He has ache.
- **q3.** Does the patient in bed 5 have pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have pain ; he doesn't
  - modèle : No, he does not have pain.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS douleur — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : fever ; he has fever
  - modèle : He has fever.
- **Tt2.** Does the patient in bed 9 have ache?
  - accepté : no ; no he does not ; no he doesn't ; he does not have ache ; he doesn't
  - modèle : No, he does not have ache.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS douleur sourde.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … pain and ache. (have)` → have · modèle : The patients have pain and ache.
- `The patient in bed 5 … not have pain. (do)` → does · modèle : The patient in bed 5 does not have pain.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has pain. The patient in bed 5 has ache.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Fièvre, le lit 9 a Douleur et n'a pas Douleur sourde.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has fever. The patient in bed 9 has pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
