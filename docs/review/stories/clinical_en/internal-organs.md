# Relecture — Internal Organs (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-017` — Le cas de Meriem : Poumons anormal(e) / _Meriem: the lungs is not normal_

**Objectif :** Comprendre des résultats : Cerveau normal(e), Poumons anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient at the clinic.
   - FR : Meriem est un·e patient·e à la clinique.
   - AR : Meriem مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The brain is normal.”
   - FR : Le médecin dit : « Cerveau est normal(e). »
   - AR : يقول الطبيب: «الدماغ طبيعي/طبيعية.»
5. **EN** “But the lungs is not normal.”
   - FR : « Mais Poumons n'est pas normal(e). »
   - AR : «لكن الرئتان غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the lungs normal?”
   - FR : Amine demande : « Poumons est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الرئتان طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:brain:v1` — **a brain** · FR : Cerveau · AR : الدماغ
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب
- `gen:lungs:v1` — **lungs** · FR : Poumons · AR : الرئتان

### Questions (acceptées / modèle / hint)
- **q1.** Is the brain normal?
  - accepté : yes ; yes it is ; the brain is normal ; it is normal
  - modèle : Yes, the brain is normal.
- **q2.** Is the lungs normal?
  - accepté : no ; no it is not ; no it isn't ; the lungs is not normal ; it is not normal ; it isn't normal
  - modèle : No, the lungs is not normal.
  - hint (si erreur) — FR : Attention : Poumons n'est PAS normal(e).
- **q3.** Which one is not normal: the brain or the lungs?
  - accepté : lungs ; the lungs
  - modèle : The lungs is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the heart normal?
  - accepté : yes ; yes it is ; the heart is normal
  - modèle : Yes, the heart is normal.
- **Tt2.** Is the brain normal?
  - accepté : no ; no it is not ; no it isn't ; the brain is not normal ; it is not normal
  - modèle : No, the brain is not normal.
  - hint (si erreur) — FR : Attention : Cerveau n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The brain … normal. (be)` → is · modèle : The brain is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The brain is… / The lungs is not… — modèle : *The brain is normal. The lungs is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Cœur normal(e), mais Cerveau anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The heart is normal. The brain is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-017` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Cerveau et Cœur à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient.
   - FR : Hana est un·e patient·e.
   - AR : Hana مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check brain.”
   - FR : « D'abord, nous vérifions Cerveau. »
   - AR : «أولًا، نتحقق من الدماغ.»
5. **EN** “Then, we check heart.”
   - FR : « Ensuite, nous vérifions Cœur. »
   - AR : «ثم نتحقق من القلب.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:brain:v1` — **a brain** · FR : Cerveau · AR : الدماغ
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب
- `gen:lungs:v1` — **lungs** · FR : Poumons · AR : الرئتان

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : brain ; we check brain ; brain first ; the doctor checks brain first
  - modèle : First, the doctor checks brain.
- **q2.** What does the doctor check next?
  - accepté : heart ; then we check heart ; heart next ; the doctor checks heart next
  - modèle : Then, the doctor checks heart.
- **q3.** Does the doctor check heart before brain?
  - accepté : no ; no he does not ; no he doesn't ; he checks brain first
  - modèle : No, he checks brain first, then heart.
  - hint (si erreur) — FR : Attention : Cerveau vient D'ABORD, ensuite Cœur.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : lungs ; lungs first ; he checks lungs first
  - modèle : First, the doctor checks lungs.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check brain. Then, we check heart.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Poumons, ensuite Cerveau, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check lungs. Then, we check brain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-017` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cerveau / Cœur, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the brain.
   - FR : Le patient du lit 3 a Cerveau.
   - AR : مريض السرير ٣ عنده الدماغ.
4. **EN** The patient in bed 5 has a problem with the heart.
   - FR : Le patient du lit 5 a Cœur.
   - AR : مريض السرير ٥ عنده القلب.
5. **EN** The patient in bed 5 does not have a problem with the brain.
   - FR : Le patient du lit 5 n'a pas de Cerveau.
   - AR : مريض السرير ٥ ليس عنده الدماغ.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:brain:v1` — **a brain** · FR : Cerveau · AR : الدماغ
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب
- `gen:lungs:v1` — **lungs** · FR : Poumons · AR : الرئتان

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : brain ; a brain ; the patient with brain
  - modèle : The patient in bed 3 has a brain.
- **q2.** What does the patient in bed 5 have?
  - accepté : heart ; a problem with the heart
  - modèle : He has a problem with the heart.
- **q3.** Does the patient in bed 5 have brain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the brain ; he doesn't
  - modèle : No, he does not have a problem with the brain.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cerveau — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : lungs ; a problem with the lungs
  - modèle : He has a problem with the lungs.
- **Tt2.** Does the patient in bed 9 have heart?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the heart ; he doesn't
  - modèle : No, he does not have a problem with the heart.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS cœur.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a brain and a heart. (have)` → have · modèle : The patients have a brain and a heart.
- `The patient in bed 5 … not have a brain. (do)` → does · modèle : The patient in bed 5 does not have a brain.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the brain. The patient in bed 5 has a problem with the heart.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Poumons, le lit 9 a Cerveau et n'a pas Cœur.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the lungs. The patient in bed 9 has a problem with the brain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
