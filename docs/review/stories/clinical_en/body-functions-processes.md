# Relecture — Body Functions & Processes (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-002` — Le cas de Leila : Respiration / _Leila: Breathing_

**Objectif :** Comprendre un cas : respiration présent, digestion absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Leila talks to Lina, a medical student.
   - FR : Leila parle avec Lina, étudiant·e en médecine.
   - AR : Leila يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has breathing.
   - FR : Elle a Respiration.
   - AR : عنده التنفس.
4. **EN** She does not have a digestion.
   - FR : Elle n'a pas de Digestion.
   - AR : ليس عنده الهضم.
5. **EN** Lina asks, “Do you have a digestion?”
   - FR : Lina demande : « Avez-vous Digestion ? »
   - AR : يسأل Lina: «هل عندك الهضم؟»
6. **EN** Leila says, “No, I do not.”
   - FR : Leila répond : « Non. »
   - AR : يجيب Leila: «لا.»
7. **EN** Lina writes “Breathing” in the file.
   - FR : Lina écrit « Respiration » dans le dossier.
   - AR : يكتب Lina «Respiration» في الملف.

### Expressions ciblées
- `gen:breathing:v1` — **breathing** · FR : Respiration · AR : التنفس
- `gen:digestion:v1` — **a digestion** · FR : Digestion · AR : الهضم
- `gen:circulation:v1` — **a circulation** · FR : Circulation · AR : الدورة الدموية

### Questions (acceptées / modèle / hint)
- **q1.** Does Leila have breathing?
  - accepté : yes ; yes she does ; yes she has breathing ; she has breathing
  - modèle : Yes, she has breathing.
- **q2.** Does she have digestion?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a digestion ; she doesn't
  - modèle : No, she does not have a digestion.
  - hint (si erreur) — FR : Attention : Leila n'a PAS digestion.
- **q3.** Does she have breathing or digestion?
  - accepté : breathing ; she has breathing
  - modèle : She has breathing.
- **q4.** What does Leila have?
  - accepté : breathing ; she has breathing
  - modèle : She has breathing.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have circulation?
  - accepté : yes ; yes he does ; he has a circulation
  - modèle : Yes, he has a circulation.
- **Tt2.** Does he have breathing?
  - accepté : no ; no he does not ; no he doesn't ; he does not have breathing ; he doesn't
  - modèle : No, he does not have breathing.
  - hint (si erreur) — FR : Attention : il n'a PAS respiration.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … breathing. (have)` → has · modèle : She has breathing.
- `They … not have a digestion. (do)` → do · modèle : They do not have a digestion.
- `I … breathing. (have)` → have · modèle : I have breathing.

### Productions
- **Reformulation :** Rapporte le cas de Leila en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has breathing. She does not have a digestion.*
- **Transfert (contexte) :** Un autre patient a Circulation. Il n'a pas de Respiration.
- Glossaire : Circulation : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a circulation. He does not have breathing.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Respiration et Digestion à un patient.
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
4. **EN** “First, we check breathing.”
   - FR : « D'abord, nous vérifions Respiration. »
   - AR : «أولًا، نتحقق من التنفس.»
5. **EN** “Then, we check digestion.”
   - FR : « Ensuite, nous vérifions Digestion. »
   - AR : «ثم نتحقق من الهضم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:breathing:v1` — **breathing** · FR : Respiration · AR : التنفس
- `gen:digestion:v1` — **a digestion** · FR : Digestion · AR : الهضم
- `gen:circulation:v1` — **a circulation** · FR : Circulation · AR : الدورة الدموية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : breathing ; we check breathing ; breathing first ; the doctor checks breathing first
  - modèle : First, the doctor checks breathing.
- **q2.** What does the doctor check next?
  - accepté : digestion ; then we check digestion ; digestion next ; the doctor checks digestion next
  - modèle : Then, the doctor checks digestion.
- **q3.** Does the doctor check digestion before breathing?
  - accepté : no ; no he does not ; no he doesn't ; he checks breathing first
  - modèle : No, he checks breathing first, then digestion.
  - hint (si erreur) — FR : Attention : Respiration vient D'ABORD, ensuite Digestion.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : circulation ; circulation first ; he checks circulation first
  - modèle : First, the doctor checks circulation.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check breathing. Then, we check digestion.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Circulation, ensuite Respiration, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check circulation. Then, we check breathing.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Respiration / Digestion, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has breathing.
   - FR : Le patient du lit 3 a Respiration.
   - AR : مريض السرير ٣ عنده التنفس.
4. **EN** The patient in bed 5 has a digestion.
   - FR : Le patient du lit 5 a Digestion.
   - AR : مريض السرير ٥ عنده الهضم.
5. **EN** The patient in bed 5 does not have breathing.
   - FR : Le patient du lit 5 n'a pas de Respiration.
   - AR : مريض السرير ٥ ليس عنده التنفس.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:breathing:v1` — **breathing** · FR : Respiration · AR : التنفس
- `gen:digestion:v1` — **a digestion** · FR : Digestion · AR : الهضم
- `gen:circulation:v1` — **a circulation** · FR : Circulation · AR : الدورة الدموية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : breathing ; the patient with breathing
  - modèle : The patient in bed 3 has breathing.
- **q2.** What does the patient in bed 5 have?
  - accepté : digestion ; a digestion ; he has a digestion
  - modèle : He has a digestion.
- **q3.** Does the patient in bed 5 have breathing?
  - accepté : no ; no he does not ; no he doesn't ; he does not have breathing ; he doesn't
  - modèle : No, he does not have breathing.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS respiration — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : circulation ; a circulation ; he has a circulation
  - modèle : He has a circulation.
- **Tt2.** Does the patient in bed 9 have digestion?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a digestion ; he doesn't
  - modèle : No, he does not have a digestion.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS digestion.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … breathing and a digestion. (have)` → have · modèle : The patients have breathing and a digestion.
- `The patient in bed 5 … not have breathing. (do)` → does · modèle : The patient in bed 5 does not have breathing.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has breathing. The patient in bed 5 has a digestion.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Circulation, le lit 9 a Respiration et n'a pas Digestion.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a circulation. The patient in bed 9 has breathing.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
