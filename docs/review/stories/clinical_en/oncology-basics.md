# Relecture — Oncology Basics (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-026` — Le cas de Youssef : Ganglions gonflés anormal(e) / _Youssef: the swollen lymph nodes is not normal_

**Objectif :** Comprendre des résultats : Marqueur tumoral normal(e), Ganglions gonflés anormal(e).
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
4. **EN** The doctor says, “The tumor marker is normal.”
   - FR : Le médecin dit : « Marqueur tumoral est normal(e). »
   - AR : يقول الطبيب: «علامة الورم طبيعي/طبيعية.»
5. **EN** “But the swollen lymph nodes is not normal.”
   - FR : « Mais Ganglions gonflés n'est pas normal(e). »
   - AR : «لكن تضخم الغدد الليمفاوية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the swollen lymph nodes normal?”
   - FR : Lina demande : « Ganglions gonflés est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل تضخم الغدد الليمفاوية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:tumor-marker:v1` — **a tumor marker** · FR : Marqueur tumoral · AR : علامة الورم
- `gen:lymph-node:v1` — **a lymph node** · FR : Ganglion lymphatique · AR : العقدة اللمفاوية
- `gen:swollen-lymph-nodes:v1` — **swollen lymph nodes** · FR : Ganglions gonflés · AR : تضخم الغدد الليمفاوية

### Questions (acceptées / modèle / hint)
- **q1.** Is the tumor marker normal?
  - accepté : yes ; yes it is ; the tumor marker is normal ; it is normal
  - modèle : Yes, the tumor marker is normal.
- **q2.** Is the swollen lymph nodes normal?
  - accepté : no ; no it is not ; no it isn't ; the swollen lymph nodes is not normal ; it is not normal ; it isn't normal
  - modèle : No, the swollen lymph nodes is not normal.
  - hint (si erreur) — FR : Attention : Ganglions gonflés n'est PAS normal(e).
- **q3.** Which one is not normal: the tumor marker or the swollen lymph nodes?
  - accepté : swollen lymph nodes ; the swollen lymph nodes
  - modèle : The swollen lymph nodes is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the lymph node normal?
  - accepté : yes ; yes it is ; the lymph node is normal
  - modèle : Yes, the lymph node is normal.
- **Tt2.** Is the tumor marker normal?
  - accepté : no ; no it is not ; no it isn't ; the tumor marker is not normal ; it is not normal
  - modèle : No, the tumor marker is not normal.
  - hint (si erreur) — FR : Attention : Marqueur tumoral n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The tumor marker … normal. (be)` → is · modèle : The tumor marker is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The tumor marker is… / The swollen lymph nodes is not… — modèle : *The tumor marker is normal. The swollen lymph nodes is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Ganglion lymphatique normal(e), mais Marqueur tumoral anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The lymph node is normal. The tumor marker is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-026` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Marqueur tumoral et Ganglion lymphatique à un patient.
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
4. **EN** “First, we check tumor marker.”
   - FR : « D'abord, nous vérifions Marqueur tumoral. »
   - AR : «أولًا، نتحقق من علامة الورم.»
5. **EN** “Then, we check lymph node.”
   - FR : « Ensuite, nous vérifions Ganglion lymphatique. »
   - AR : «ثم نتحقق من العقدة اللمفاوية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:tumor-marker:v1` — **a tumor marker** · FR : Marqueur tumoral · AR : علامة الورم
- `gen:lymph-node:v1` — **a lymph node** · FR : Ganglion lymphatique · AR : العقدة اللمفاوية
- `gen:swollen-lymph-nodes:v1` — **swollen lymph nodes** · FR : Ganglions gonflés · AR : تضخم الغدد الليمفاوية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : tumor marker ; we check tumor marker ; tumor marker first ; the doctor checks tumor marker first
  - modèle : First, the doctor checks tumor marker.
- **q2.** What does the doctor check next?
  - accepté : lymph node ; then we check lymph node ; lymph node next ; the doctor checks lymph node next
  - modèle : Then, the doctor checks lymph node.
- **q3.** Does the doctor check lymph node before tumor marker?
  - accepté : no ; no he does not ; no he doesn't ; he checks tumor marker first
  - modèle : No, he checks tumor marker first, then lymph node.
  - hint (si erreur) — FR : Attention : Marqueur tumoral vient D'ABORD, ensuite Ganglion lymphatique.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : swollen lymph nodes ; swollen lymph nodes first ; he checks swollen lymph nodes first
  - modèle : First, the doctor checks swollen lymph nodes.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check tumor marker. Then, we check lymph node.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Ganglions gonflés, ensuite Marqueur tumoral, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check swollen lymph nodes. Then, we check tumor marker.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-026` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Marqueur tumoral / Ganglion lymphatique, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the tumor marker.
   - FR : Le patient du lit 3 a Marqueur tumoral.
   - AR : مريض السرير ٣ عنده علامة الورم.
4. **EN** The patient in bed 5 has a problem with the lymph node.
   - FR : Le patient du lit 5 a Ganglion lymphatique.
   - AR : مريض السرير ٥ عنده العقدة اللمفاوية.
5. **EN** The patient in bed 5 does not have a problem with the tumor marker.
   - FR : Le patient du lit 5 n'a pas de Marqueur tumoral.
   - AR : مريض السرير ٥ ليس عنده علامة الورم.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:tumor-marker:v1` — **a tumor marker** · FR : Marqueur tumoral · AR : علامة الورم
- `gen:lymph-node:v1` — **a lymph node** · FR : Ganglion lymphatique · AR : العقدة اللمفاوية
- `gen:swollen-lymph-nodes:v1` — **swollen lymph nodes** · FR : Ganglions gonflés · AR : تضخم الغدد الليمفاوية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : tumor marker ; a tumor marker ; the patient with tumor marker
  - modèle : The patient in bed 3 has a tumor marker.
- **q2.** What does the patient in bed 5 have?
  - accepté : lymph node ; a problem with the lymph node
  - modèle : He has a problem with the lymph node.
- **q3.** Does the patient in bed 5 have tumor marker?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the tumor marker ; he doesn't
  - modèle : No, he does not have a problem with the tumor marker.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS marqueur tumoral — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : swollen lymph nodes ; a problem with the swollen lymph nodes
  - modèle : He has a problem with the swollen lymph nodes.
- **Tt2.** Does the patient in bed 9 have lymph node?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the lymph node ; he doesn't
  - modèle : No, he does not have a problem with the lymph node.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS ganglion lymphatique.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a tumor marker and a lymph node. (have)` → have · modèle : The patients have a tumor marker and a lymph node.
- `The patient in bed 5 … not have a tumor marker. (do)` → does · modèle : The patient in bed 5 does not have a tumor marker.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the tumor marker. The patient in bed 5 has a problem with the lymph node.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Ganglions gonflés, le lit 9 a Marqueur tumoral et n'a pas Ganglion lymphatique.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the swollen lymph nodes. The patient in bed 9 has a problem with the tumor marker.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
