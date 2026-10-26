# Relecture — Lipides et Lipoprotéines (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-016` — Le cas de Farid : Cholestérol anormal(e) / _Farid: the cholesterol is not normal_

**Objectif :** Comprendre des résultats : Acide gras normal(e), Cholestérol anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The fatty acid is normal.”
   - FR : Le médecin dit : « Acide gras est normal(e). »
   - AR : يقول الطبيب: «الحمض الدهني طبيعي/طبيعية.»
5. **EN** “But the cholesterol is not normal.”
   - FR : « Mais Cholestérol n'est pas normal(e). »
   - AR : «لكن الكولسترول / الكوليسترول غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the cholesterol normal?”
   - FR : Lina demande : « Cholestérol est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الكولسترول / الكوليسترول طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:fatty-acid:v1` — **a fatty acid** · FR : Acide gras · AR : الحمض الدهني
- `gen:triglyceride:v1` — **a triglyceride** · FR : Triglycéride (Triacylglycérol) · AR : الدهون الثلاثية / ثلاثي الغليسريد
- `gen:cholesterol:v1` — **a cholesterol** · FR : Cholestérol · AR : الكولسترول / الكوليسترول

### Questions (acceptées / modèle / hint)
- **q1.** Is the fatty acid normal?
  - accepté : yes ; yes it is ; the fatty acid is normal ; it is normal
  - modèle : Yes, the fatty acid is normal.
- **q2.** Is the cholesterol normal?
  - accepté : no ; no it is not ; no it isn't ; the cholesterol is not normal ; it is not normal ; it isn't normal
  - modèle : No, the cholesterol is not normal.
  - hint (si erreur) — FR : Attention : Cholestérol n'est PAS normal(e).
- **q3.** Which one is not normal: the fatty acid or the cholesterol?
  - accepté : cholesterol ; the cholesterol
  - modèle : The cholesterol is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the triglyceride normal?
  - accepté : yes ; yes it is ; the triglyceride is normal
  - modèle : Yes, the triglyceride is normal.
- **Tt2.** Is the fatty acid normal?
  - accepté : no ; no it is not ; no it isn't ; the fatty acid is not normal ; it is not normal
  - modèle : No, the fatty acid is not normal.
  - hint (si erreur) — FR : Attention : Acide gras n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The fatty acid … normal. (be)` → is · modèle : The fatty acid is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The fatty acid is… / The cholesterol is not… — modèle : *The fatty acid is normal. The cholesterol is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Triglycéride (Triacylglycérol) normal(e), mais Acide gras anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The triglyceride is normal. The fatty acid is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-016` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Acide gras et Triglycéride (Triacylglycérol) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check fatty acid.”
   - FR : « D'abord, nous vérifions Acide gras. »
   - AR : «أولًا، نتحقق من الحمض الدهني.»
5. **EN** “Then, we check triglyceride.”
   - FR : « Ensuite, nous vérifions Triglycéride (Triacylglycérol). »
   - AR : «ثم نتحقق من الدهون الثلاثية / ثلاثي الغليسريد.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:fatty-acid:v1` — **a fatty acid** · FR : Acide gras · AR : الحمض الدهني
- `gen:triglyceride:v1` — **a triglyceride** · FR : Triglycéride (Triacylglycérol) · AR : الدهون الثلاثية / ثلاثي الغليسريد
- `gen:cholesterol:v1` — **a cholesterol** · FR : Cholestérol · AR : الكولسترول / الكوليسترول

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : fatty acid ; we check fatty acid ; fatty acid first ; the doctor checks fatty acid first
  - modèle : First, the doctor checks fatty acid.
- **q2.** What does the doctor check next?
  - accepté : triglyceride ; then we check triglyceride ; triglyceride next ; the doctor checks triglyceride next
  - modèle : Then, the doctor checks triglyceride.
- **q3.** Does the doctor check triglyceride before fatty acid?
  - accepté : no ; no he does not ; no he doesn't ; he checks fatty acid first
  - modèle : No, he checks fatty acid first, then triglyceride.
  - hint (si erreur) — FR : Attention : Acide gras vient D'ABORD, ensuite Triglycéride (Triacylglycérol).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : cholesterol ; cholesterol first ; he checks cholesterol first
  - modèle : First, the doctor checks cholesterol.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check fatty acid. Then, we check triglyceride.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Cholestérol, ensuite Acide gras, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check cholesterol. Then, we check fatty acid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-016` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Acide gras / Triglycéride (Triacylglycérol), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the fatty acid.
   - FR : Le patient du lit 3 a Acide gras.
   - AR : مريض السرير ٣ عنده الحمض الدهني.
4. **EN** The patient in bed 5 has a problem with the triglyceride.
   - FR : Le patient du lit 5 a Triglycéride (Triacylglycérol).
   - AR : مريض السرير ٥ عنده الدهون الثلاثية / ثلاثي الغليسريد.
5. **EN** The patient in bed 5 does not have a problem with the fatty acid.
   - FR : Le patient du lit 5 n'a pas de Acide gras.
   - AR : مريض السرير ٥ ليس عنده الحمض الدهني.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:fatty-acid:v1` — **a fatty acid** · FR : Acide gras · AR : الحمض الدهني
- `gen:triglyceride:v1` — **a triglyceride** · FR : Triglycéride (Triacylglycérol) · AR : الدهون الثلاثية / ثلاثي الغليسريد
- `gen:cholesterol:v1` — **a cholesterol** · FR : Cholestérol · AR : الكولسترول / الكوليسترول

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : fatty acid ; a fatty acid ; the patient with fatty acid
  - modèle : The patient in bed 3 has a fatty acid.
- **q2.** What does the patient in bed 5 have?
  - accepté : triglyceride ; a problem with the triglyceride
  - modèle : He has a problem with the triglyceride.
- **q3.** Does the patient in bed 5 have fatty acid?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the fatty acid ; he doesn't
  - modèle : No, he does not have a problem with the fatty acid.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS acide gras — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : cholesterol ; a problem with the cholesterol
  - modèle : He has a problem with the cholesterol.
- **Tt2.** Does the patient in bed 9 have triglyceride?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the triglyceride ; he doesn't
  - modèle : No, he does not have a problem with the triglyceride.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS triglycéride (triacylglycérol).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a fatty acid and a triglyceride. (have)` → have · modèle : The patients have a fatty acid and a triglyceride.
- `The patient in bed 5 … not have a fatty acid. (do)` → does · modèle : The patient in bed 5 does not have a fatty acid.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the fatty acid. The patient in bed 5 has a problem with the triglyceride.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Cholestérol, le lit 9 a Acide gras et n'a pas Triglycéride (Triacylglycérol).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the cholesterol. The patient in bed 9 has a problem with the fatty acid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
