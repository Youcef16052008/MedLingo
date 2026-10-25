# Relecture — Appareil respiratoire (anat)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anat-case-002` — Le cas de Youssef : Larynx anormal(e) / _Youssef: the larynx is not normal_

**Objectif :** Comprendre des résultats : Système respiratoire normal(e), Larynx anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The respiratory system is normal.”
   - FR : Le médecin dit : « Système respiratoire est normal(e). »
   - AR : يقول الطبيب: «الجهاز التنفسي طبيعي/طبيعية.»
5. **EN** “But the larynx is not normal.”
   - FR : « Mais Larynx n'est pas normal(e). »
   - AR : «لكن الحنجرة غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the larynx normal?”
   - FR : Lina demande : « Larynx est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الحنجرة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:respiratory-system:v1` — **a respiratory system** · FR : Système respiratoire · AR : الجهاز التنفسي
- `gen:pharynx:v1` — **a pharynx** · FR : Pharynx · AR : البلعوم
- `gen:larynx:v1` — **a larynx** · FR : Larynx · AR : الحنجرة

### Questions (acceptées / modèle / hint)
- **q1.** Is the respiratory system normal?
  - accepté : yes ; yes it is ; the respiratory system is normal ; it is normal
  - modèle : Yes, the respiratory system is normal.
- **q2.** Is the larynx normal?
  - accepté : no ; no it is not ; no it isn't ; the larynx is not normal ; it is not normal ; it isn't normal
  - modèle : No, the larynx is not normal.
  - hint (si erreur) — FR : Attention : Larynx n'est PAS normal(e).
- **q3.** Which one is not normal: the respiratory system or the larynx?
  - accepté : larynx ; the larynx
  - modèle : The larynx is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the pharynx normal?
  - accepté : yes ; yes it is ; the pharynx is normal
  - modèle : Yes, the pharynx is normal.
- **Tt2.** Is the respiratory system normal?
  - accepté : no ; no it is not ; no it isn't ; the respiratory system is not normal ; it is not normal
  - modèle : No, the respiratory system is not normal.
  - hint (si erreur) — FR : Attention : Système respiratoire n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The respiratory system … normal. (be)` → is · modèle : The respiratory system is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The respiratory system is… / The larynx is not… — modèle : *The respiratory system is normal. The larynx is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Pharynx normal(e), mais Système respiratoire anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The pharynx is normal. The respiratory system is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anat-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Système respiratoire et Pharynx à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check respiratory system.”
   - FR : « D'abord, nous vérifions Système respiratoire. »
   - AR : «أولًا، نتحقق من الجهاز التنفسي.»
5. **EN** “Then, we check pharynx.”
   - FR : « Ensuite, nous vérifions Pharynx. »
   - AR : «ثم نتحقق من البلعوم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:respiratory-system:v1` — **a respiratory system** · FR : Système respiratoire · AR : الجهاز التنفسي
- `gen:pharynx:v1` — **a pharynx** · FR : Pharynx · AR : البلعوم
- `gen:larynx:v1` — **a larynx** · FR : Larynx · AR : الحنجرة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : respiratory system ; we check respiratory system ; respiratory system first ; the doctor checks respiratory system first
  - modèle : First, the doctor checks respiratory system.
- **q2.** What does the doctor check next?
  - accepté : pharynx ; then we check pharynx ; pharynx next ; the doctor checks pharynx next
  - modèle : Then, the doctor checks pharynx.
- **q3.** Does the doctor check pharynx before respiratory system?
  - accepté : no ; no he does not ; no he doesn't ; he checks respiratory system first
  - modèle : No, he checks respiratory system first, then pharynx.
  - hint (si erreur) — FR : Attention : Système respiratoire vient D'ABORD, ensuite Pharynx.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : larynx ; larynx first ; he checks larynx first
  - modèle : First, the doctor checks larynx.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check respiratory system. Then, we check pharynx.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Larynx, ensuite Système respiratoire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check larynx. Then, we check respiratory system.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anat-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Système respiratoire / Pharynx, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the respiratory system.
   - FR : Le patient du lit 3 a Système respiratoire.
   - AR : مريض السرير ٣ عنده الجهاز التنفسي.
4. **EN** The patient in bed 5 has a problem with the pharynx.
   - FR : Le patient du lit 5 a Pharynx.
   - AR : مريض السرير ٥ عنده البلعوم.
5. **EN** The patient in bed 5 does not have a problem with the respiratory system.
   - FR : Le patient du lit 5 n'a pas de Système respiratoire.
   - AR : مريض السرير ٥ ليس عنده الجهاز التنفسي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:respiratory-system:v1` — **a respiratory system** · FR : Système respiratoire · AR : الجهاز التنفسي
- `gen:pharynx:v1` — **a pharynx** · FR : Pharynx · AR : البلعوم
- `gen:larynx:v1` — **a larynx** · FR : Larynx · AR : الحنجرة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : respiratory system ; a respiratory system ; the patient with respiratory system
  - modèle : The patient in bed 3 has a respiratory system.
- **q2.** What does the patient in bed 5 have?
  - accepté : pharynx ; a problem with the pharynx
  - modèle : He has a problem with the pharynx.
- **q3.** Does the patient in bed 5 have respiratory system?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the respiratory system ; he doesn't
  - modèle : No, he does not have a problem with the respiratory system.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS système respiratoire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : larynx ; a problem with the larynx
  - modèle : He has a problem with the larynx.
- **Tt2.** Does the patient in bed 9 have pharynx?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the pharynx ; he doesn't
  - modèle : No, he does not have a problem with the pharynx.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS pharynx.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a respiratory system and a pharynx. (have)` → have · modèle : The patients have a respiratory system and a pharynx.
- `The patient in bed 5 … not have a respiratory system. (do)` → does · modèle : The patient in bed 5 does not have a respiratory system.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the respiratory system. The patient in bed 5 has a problem with the pharynx.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Larynx, le lit 9 a Système respiratoire et n'a pas Pharynx.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the larynx. The patient in bed 9 has a problem with the respiratory system.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
