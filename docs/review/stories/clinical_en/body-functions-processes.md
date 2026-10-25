# Relecture — Body Functions & Processes (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-002` — Le cas de Youssef : Circulation anormal(e) / _Youssef: the circulation is not normal_

**Objectif :** Comprendre des résultats : Respiration normal(e), Circulation anormal(e).
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
4. **EN** The doctor says, “The breathing is normal.”
   - FR : Le médecin dit : « Respiration est normal(e). »
   - AR : يقول الطبيب: «التنفس طبيعي/طبيعية.»
5. **EN** “But the circulation is not normal.”
   - FR : « Mais Circulation n'est pas normal(e). »
   - AR : «لكن الدورة الدموية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the circulation normal?”
   - FR : Lina demande : « Circulation est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الدورة الدموية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:breathing:v1` — **breathing** · FR : Respiration · AR : التنفس
- `gen:digestion:v1` — **a digestion** · FR : Digestion · AR : الهضم
- `gen:circulation:v1` — **a circulation** · FR : Circulation · AR : الدورة الدموية

### Questions (acceptées / modèle / hint)
- **q1.** Is the breathing normal?
  - accepté : yes ; yes it is ; the breathing is normal ; it is normal
  - modèle : Yes, the breathing is normal.
- **q2.** Is the circulation normal?
  - accepté : no ; no it is not ; no it isn't ; the circulation is not normal ; it is not normal ; it isn't normal
  - modèle : No, the circulation is not normal.
  - hint (si erreur) — FR : Attention : Circulation n'est PAS normal(e).
- **q3.** Which one is not normal: the breathing or the circulation?
  - accepté : circulation ; the circulation
  - modèle : The circulation is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the digestion normal?
  - accepté : yes ; yes it is ; the digestion is normal
  - modèle : Yes, the digestion is normal.
- **Tt2.** Is the breathing normal?
  - accepté : no ; no it is not ; no it isn't ; the breathing is not normal ; it is not normal
  - modèle : No, the breathing is not normal.
  - hint (si erreur) — FR : Attention : Respiration n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The breathing … normal. (be)` → is · modèle : The breathing is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The breathing is… / The circulation is not… — modèle : *The breathing is normal. The circulation is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Digestion normal(e), mais Respiration anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The digestion is normal. The breathing is not normal.*

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
3. **EN** The patient in bed 3 has a problem with the breathing.
   - FR : Le patient du lit 3 a Respiration.
   - AR : مريض السرير ٣ عنده التنفس.
4. **EN** The patient in bed 5 has a problem with the digestion.
   - FR : Le patient du lit 5 a Digestion.
   - AR : مريض السرير ٥ عنده الهضم.
5. **EN** The patient in bed 5 does not have a problem with the breathing.
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
  - accepté : digestion ; a problem with the digestion
  - modèle : He has a problem with the digestion.
- **q3.** Does the patient in bed 5 have breathing?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the breathing ; he doesn't
  - modèle : No, he does not have a problem with the breathing.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS respiration — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : circulation ; a problem with the circulation
  - modèle : He has a problem with the circulation.
- **Tt2.** Does the patient in bed 9 have digestion?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the digestion ; he doesn't
  - modèle : No, he does not have a problem with the digestion.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS digestion.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … breathing and a digestion. (have)` → have · modèle : The patients have breathing and a digestion.
- `The patient in bed 5 … not have breathing. (do)` → does · modèle : The patient in bed 5 does not have breathing.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the breathing. The patient in bed 5 has a problem with the digestion.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Circulation, le lit 9 a Respiration et n'a pas Digestion.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the circulation. The patient in bed 9 has a problem with the breathing.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
