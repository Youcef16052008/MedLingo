# Relecture — Body Parts (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-003` — Le cas de Amel : Poitrine anormal(e) / _Amel: the chest is not normal_

**Objectif :** Comprendre des résultats : Tête normal(e), Poitrine anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The head is normal.”
   - FR : Le médecin dit : « Tête est normal(e). »
   - AR : يقول الطبيب: «الرأس طبيعي/طبيعية.»
5. **EN** “But the chest is not normal.”
   - FR : « Mais Poitrine n'est pas normal(e). »
   - AR : «لكن الصدر غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the chest normal?”
   - FR : Amine demande : « Poitrine est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الصدر طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:head:v1` — **a head** · FR : Tête · AR : الرأس
- `gen:neck:v1` — **a neck** · FR : Cou · AR : الرقبة
- `gen:chest:v1` — **a chest** · FR : Poitrine · AR : الصدر

### Questions (acceptées / modèle / hint)
- **q1.** Is the head normal?
  - accepté : yes ; yes it is ; the head is normal ; it is normal
  - modèle : Yes, the head is normal.
- **q2.** Is the chest normal?
  - accepté : no ; no it is not ; no it isn't ; the chest is not normal ; it is not normal ; it isn't normal
  - modèle : No, the chest is not normal.
  - hint (si erreur) — FR : Attention : Poitrine n'est PAS normal(e).
- **q3.** Which one is not normal: the head or the chest?
  - accepté : chest ; the chest
  - modèle : The chest is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the neck normal?
  - accepté : yes ; yes it is ; the neck is normal
  - modèle : Yes, the neck is normal.
- **Tt2.** Is the head normal?
  - accepté : no ; no it is not ; no it isn't ; the head is not normal ; it is not normal
  - modèle : No, the head is not normal.
  - hint (si erreur) — FR : Attention : Tête n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The head … normal. (be)` → is · modèle : The head is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The head is… / The chest is not… — modèle : *The head is normal. The chest is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Cou normal(e), mais Tête anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The neck is normal. The head is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Tête et Cou à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient.
   - FR : Meriem est un·e patient·e.
   - AR : Meriem مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check head.”
   - FR : « D'abord, nous vérifions Tête. »
   - AR : «أولًا، نتحقق من الرأس.»
5. **EN** “Then, we check neck.”
   - FR : « Ensuite, nous vérifions Cou. »
   - AR : «ثم نتحقق من الرقبة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:head:v1` — **a head** · FR : Tête · AR : الرأس
- `gen:neck:v1` — **a neck** · FR : Cou · AR : الرقبة
- `gen:chest:v1` — **a chest** · FR : Poitrine · AR : الصدر

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : head ; we check head ; head first ; the doctor checks head first
  - modèle : First, the doctor checks head.
- **q2.** What does the doctor check next?
  - accepté : neck ; then we check neck ; neck next ; the doctor checks neck next
  - modèle : Then, the doctor checks neck.
- **q3.** Does the doctor check neck before head?
  - accepté : no ; no he does not ; no he doesn't ; he checks head first
  - modèle : No, he checks head first, then neck.
  - hint (si erreur) — FR : Attention : Tête vient D'ABORD, ensuite Cou.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : chest ; chest first ; he checks chest first
  - modèle : First, the doctor checks chest.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check head. Then, we check neck.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Poitrine, ensuite Tête, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check chest. Then, we check head.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Tête / Cou, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the head.
   - FR : Le patient du lit 3 a Tête.
   - AR : مريض السرير ٣ عنده الرأس.
4. **EN** The patient in bed 5 has a problem with the neck.
   - FR : Le patient du lit 5 a Cou.
   - AR : مريض السرير ٥ عنده الرقبة.
5. **EN** The patient in bed 5 does not have a problem with the head.
   - FR : Le patient du lit 5 n'a pas de Tête.
   - AR : مريض السرير ٥ ليس عنده الرأس.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:head:v1` — **a head** · FR : Tête · AR : الرأس
- `gen:neck:v1` — **a neck** · FR : Cou · AR : الرقبة
- `gen:chest:v1` — **a chest** · FR : Poitrine · AR : الصدر

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : head ; a head ; the patient with head
  - modèle : The patient in bed 3 has a head.
- **q2.** What does the patient in bed 5 have?
  - accepté : neck ; a problem with the neck
  - modèle : He has a problem with the neck.
- **q3.** Does the patient in bed 5 have head?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the head ; he doesn't
  - modèle : No, he does not have a problem with the head.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS tête — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : chest ; a problem with the chest
  - modèle : He has a problem with the chest.
- **Tt2.** Does the patient in bed 9 have neck?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the neck ; he doesn't
  - modèle : No, he does not have a problem with the neck.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS cou.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a head and a neck. (have)` → have · modèle : The patients have a head and a neck.
- `The patient in bed 5 … not have a head. (do)` → does · modèle : The patient in bed 5 does not have a head.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the head. The patient in bed 5 has a problem with the neck.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Poitrine, le lit 9 a Tête et n'a pas Cou.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the chest. The patient in bed 9 has a problem with the head.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
