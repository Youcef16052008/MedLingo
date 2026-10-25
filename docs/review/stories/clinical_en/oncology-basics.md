# Relecture — Oncology Basics (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-026` — Le cas de Leila : Marqueur tumoral / _Leila: Tumor marker_

**Objectif :** Comprendre un cas : marqueur tumoral présent, ganglion lymphatique absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Leila talks to Lina, a medical student.
   - FR : Leila parle avec Lina, étudiant·e en médecine.
   - AR : Leila يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a tumor marker.
   - FR : Elle a Marqueur tumoral.
   - AR : عنده علامة الورم.
4. **EN** She does not have a lymph node.
   - FR : Elle n'a pas de Ganglion lymphatique.
   - AR : ليس عنده العقدة اللمفاوية.
5. **EN** Lina asks, “Do you have a lymph node?”
   - FR : Lina demande : « Avez-vous Ganglion lymphatique ? »
   - AR : يسأل Lina: «هل عندك العقدة اللمفاوية؟»
6. **EN** Leila says, “No, I do not.”
   - FR : Leila répond : « Non. »
   - AR : يجيب Leila: «لا.»
7. **EN** Lina writes “Tumor marker” in the file.
   - FR : Lina écrit « Marqueur tumoral » dans le dossier.
   - AR : يكتب Lina «Marqueur tumoral» في الملف.

### Expressions ciblées
- `gen:tumor-marker:v1` — **a tumor marker** · FR : Marqueur tumoral · AR : علامة الورم
- `gen:lymph-node:v1` — **a lymph node** · FR : Ganglion lymphatique · AR : العقدة اللمفاوية
- `gen:swollen-lymph-nodes:v1` — **swollen lymph nodes** · FR : Ganglions gonflés · AR : تضخم الغدد الليمفاوية

### Questions (acceptées / modèle / hint)
- **q1.** Does Leila have tumor marker?
  - accepté : yes ; yes she does ; yes she has a tumor marker ; she has a tumor marker
  - modèle : Yes, she has a tumor marker.
- **q2.** Does she have lymph node?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a lymph node ; she doesn't
  - modèle : No, she does not have a lymph node.
  - hint (si erreur) — FR : Attention : Leila n'a PAS ganglion lymphatique.
- **q3.** Does she have tumor marker or lymph node?
  - accepté : tumor marker ; a tumor marker ; she has a tumor marker
  - modèle : She has a tumor marker.
- **q4.** What does Leila have?
  - accepté : tumor marker ; a tumor marker ; she has a tumor marker
  - modèle : She has a tumor marker.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have swollen lymph nodes?
  - accepté : yes ; yes he does ; he has swollen lymph nodes
  - modèle : Yes, he has swollen lymph nodes.
- **Tt2.** Does he have tumor marker?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a tumor marker ; he doesn't
  - modèle : No, he does not have a tumor marker.
  - hint (si erreur) — FR : Attention : il n'a PAS marqueur tumoral.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a tumor marker. (have)` → has · modèle : She has a tumor marker.
- `They … not have a lymph node. (do)` → do · modèle : They do not have a lymph node.
- `I … a tumor marker. (have)` → have · modèle : I have a tumor marker.

### Productions
- **Reformulation :** Rapporte le cas de Leila en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a tumor marker. She does not have a lymph node.*
- **Transfert (contexte) :** Un autre patient a Ganglions gonflés. Il n'a pas de Marqueur tumoral.
- Glossaire : Ganglions gonflés : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has swollen lymph nodes. He does not have a tumor marker.*

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
3. **EN** The patient in bed 3 has a tumor marker.
   - FR : Le patient du lit 3 a Marqueur tumoral.
   - AR : مريض السرير ٣ عنده علامة الورم.
4. **EN** The patient in bed 5 has a lymph node.
   - FR : Le patient du lit 5 a Ganglion lymphatique.
   - AR : مريض السرير ٥ عنده العقدة اللمفاوية.
5. **EN** The patient in bed 5 does not have a tumor marker.
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
  - accepté : lymph node ; a lymph node ; he has a lymph node
  - modèle : He has a lymph node.
- **q3.** Does the patient in bed 5 have tumor marker?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a tumor marker ; he doesn't
  - modèle : No, he does not have a tumor marker.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS marqueur tumoral — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : swollen lymph nodes ; he has swollen lymph nodes
  - modèle : He has swollen lymph nodes.
- **Tt2.** Does the patient in bed 9 have lymph node?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a lymph node ; he doesn't
  - modèle : No, he does not have a lymph node.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS ganglion lymphatique.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a tumor marker and a lymph node. (have)` → have · modèle : The patients have a tumor marker and a lymph node.
- `The patient in bed 5 … not have a tumor marker. (do)` → does · modèle : The patient in bed 5 does not have a tumor marker.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a tumor marker. The patient in bed 5 has a lymph node.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Ganglions gonflés, le lit 9 a Marqueur tumoral et n'a pas Ganglion lymphatique.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has swollen lymph nodes. The patient in bed 9 has a tumor marker.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
