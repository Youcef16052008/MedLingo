# Relecture — Physiologie Nerveuse (physio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `physio-case-005` — Le cas de Meriem : Dendrite anormal(e) / _Meriem: the dendrite is not normal_

**Objectif :** Comprendre des résultats : Neurone normal(e), Dendrite anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The neuron is normal.”
   - FR : Le médecin dit : « Neurone est normal(e). »
   - AR : يقول الطبيب: «العصبون (الخلية العصبية) طبيعي/طبيعية.»
5. **EN** “But the dendrite is not normal.”
   - FR : « Mais Dendrite n'est pas normal(e). »
   - AR : «لكن التغصن غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the dendrite normal?”
   - FR : Amine demande : « Dendrite est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل التغصن طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:neuron:v1` — **a neuron** · FR : Neurone · AR : العصبون (الخلية العصبية)
- `gen:axon:v1` — **an axon** · FR : Axone · AR : المحور العصبي
- `gen:dendrite:v1` — **a dendrite** · FR : Dendrite · AR : التغصن

### Questions (acceptées / modèle / hint)
- **q1.** Is the neuron normal?
  - accepté : yes ; yes it is ; the neuron is normal ; it is normal
  - modèle : Yes, the neuron is normal.
- **q2.** Is the dendrite normal?
  - accepté : no ; no it is not ; no it isn't ; the dendrite is not normal ; it is not normal ; it isn't normal
  - modèle : No, the dendrite is not normal.
  - hint (si erreur) — FR : Attention : Dendrite n'est PAS normal(e).
- **q3.** Which one is not normal: the neuron or the dendrite?
  - accepté : dendrite ; the dendrite
  - modèle : The dendrite is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the axon normal?
  - accepté : yes ; yes it is ; the axon is normal
  - modèle : Yes, the axon is normal.
- **Tt2.** Is the neuron normal?
  - accepté : no ; no it is not ; no it isn't ; the neuron is not normal ; it is not normal
  - modèle : No, the neuron is not normal.
  - hint (si erreur) — FR : Attention : Neurone n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The neuron … normal. (be)` → is · modèle : The neuron is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The neuron is… / The dendrite is not… — modèle : *The neuron is normal. The dendrite is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Axone normal(e), mais Neurone anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The axon is normal. The neuron is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `physio-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Neurone et Axone à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check neuron.”
   - FR : « D'abord, nous vérifions Neurone. »
   - AR : «أولًا، نتحقق من العصبون (الخلية العصبية).»
5. **EN** “Then, we check axon.”
   - FR : « Ensuite, nous vérifions Axone. »
   - AR : «ثم نتحقق من المحور العصبي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:neuron:v1` — **a neuron** · FR : Neurone · AR : العصبون (الخلية العصبية)
- `gen:axon:v1` — **an axon** · FR : Axone · AR : المحور العصبي
- `gen:dendrite:v1` — **a dendrite** · FR : Dendrite · AR : التغصن

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : neuron ; we check neuron ; neuron first ; the doctor checks neuron first
  - modèle : First, the doctor checks neuron.
- **q2.** What does the doctor check next?
  - accepté : axon ; then we check axon ; axon next ; the doctor checks axon next
  - modèle : Then, the doctor checks axon.
- **q3.** Does the doctor check axon before neuron?
  - accepté : no ; no he does not ; no he doesn't ; he checks neuron first
  - modèle : No, he checks neuron first, then axon.
  - hint (si erreur) — FR : Attention : Neurone vient D'ABORD, ensuite Axone.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : dendrite ; dendrite first ; he checks dendrite first
  - modèle : First, the doctor checks dendrite.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check neuron. Then, we check axon.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Dendrite, ensuite Neurone, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check dendrite. Then, we check neuron.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `physio-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Neurone / Axone, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the neuron.
   - FR : Le patient du lit 3 a Neurone.
   - AR : مريض السرير ٣ عنده العصبون (الخلية العصبية).
4. **EN** The patient in bed 5 has a problem with the axon.
   - FR : Le patient du lit 5 a Axone.
   - AR : مريض السرير ٥ عنده المحور العصبي.
5. **EN** The patient in bed 5 does not have a problem with the neuron.
   - FR : Le patient du lit 5 n'a pas de Neurone.
   - AR : مريض السرير ٥ ليس عنده العصبون (الخلية العصبية).
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:neuron:v1` — **a neuron** · FR : Neurone · AR : العصبون (الخلية العصبية)
- `gen:axon:v1` — **an axon** · FR : Axone · AR : المحور العصبي
- `gen:dendrite:v1` — **a dendrite** · FR : Dendrite · AR : التغصن

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : neuron ; a neuron ; the patient with neuron
  - modèle : The patient in bed 3 has a neuron.
- **q2.** What does the patient in bed 5 have?
  - accepté : axon ; a problem with the axon
  - modèle : He has a problem with the axon.
- **q3.** Does the patient in bed 5 have neuron?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the neuron ; he doesn't
  - modèle : No, he does not have a problem with the neuron.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS neurone — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : dendrite ; a problem with the dendrite
  - modèle : He has a problem with the dendrite.
- **Tt2.** Does the patient in bed 9 have axon?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the axon ; he doesn't
  - modèle : No, he does not have a problem with the axon.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS axone.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a neuron and an axon. (have)` → have · modèle : The patients have a neuron and an axon.
- `The patient in bed 5 … not have a neuron. (do)` → does · modèle : The patient in bed 5 does not have a neuron.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the neuron. The patient in bed 5 has a problem with the axon.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Dendrite, le lit 9 a Neurone et n'a pas Axone.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the dendrite. The patient in bed 9 has a problem with the neuron.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
