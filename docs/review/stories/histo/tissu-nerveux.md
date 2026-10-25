# Relecture — Tissu Nerveux (histo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `histo-case-008` — Le cas de Bilal : Neurone (Cellule nerveuse) anormal(e) / _Bilal: the neuron is not normal_

**Objectif :** Comprendre des résultats : Astrocyte normal(e), Neurone (Cellule nerveuse) anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The astrocyte is normal.”
   - FR : Le médecin dit : « Astrocyte est normal(e). »
   - AR : يقول الطبيب: «الخلية الدبقية النجمية طبيعي/طبيعية.»
5. **EN** “But the neuron is not normal.”
   - FR : « Mais Neurone (Cellule nerveuse) n'est pas normal(e). »
   - AR : «لكن العصبون (الخلية العصبية) غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the neuron normal?”
   - FR : Lina demande : « Neurone (Cellule nerveuse) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل العصبون (الخلية العصبية) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:astrocyte:v1` — **an astrocyte** · FR : Astrocyte · AR : الخلية الدبقية النجمية
- `gen:nervous-tissue:v1` — **a nervous tissue** · FR : Tissu nerveux · AR : النسيج العصبي
- `gen:neuron:v1` — **a neuron** · FR : Neurone (Cellule nerveuse) · AR : العصبون (الخلية العصبية)

### Questions (acceptées / modèle / hint)
- **q1.** Is the astrocyte normal?
  - accepté : yes ; yes it is ; the astrocyte is normal ; it is normal
  - modèle : Yes, the astrocyte is normal.
- **q2.** Is the neuron normal?
  - accepté : no ; no it is not ; no it isn't ; the neuron is not normal ; it is not normal ; it isn't normal
  - modèle : No, the neuron is not normal.
  - hint (si erreur) — FR : Attention : Neurone (Cellule nerveuse) n'est PAS normal(e).
- **q3.** Which one is not normal: the astrocyte or the neuron?
  - accepté : neuron ; the neuron
  - modèle : The neuron is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the nervous tissue normal?
  - accepté : yes ; yes it is ; the nervous tissue is normal
  - modèle : Yes, the nervous tissue is normal.
- **Tt2.** Is the astrocyte normal?
  - accepté : no ; no it is not ; no it isn't ; the astrocyte is not normal ; it is not normal
  - modèle : No, the astrocyte is not normal.
  - hint (si erreur) — FR : Attention : Astrocyte n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The astrocyte … normal. (be)` → is · modèle : The astrocyte is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The astrocyte is… / The neuron is not… — modèle : *The astrocyte is normal. The neuron is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Tissu nerveux normal(e), mais Astrocyte anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The nervous tissue is normal. The astrocyte is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `histo-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Astrocyte et Tissu nerveux à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient.
   - FR : Omar est un·e patient·e.
   - AR : Omar مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check astrocyte.”
   - FR : « D'abord, nous vérifions Astrocyte. »
   - AR : «أولًا، نتحقق من الخلية الدبقية النجمية.»
5. **EN** “Then, we check nervous tissue.”
   - FR : « Ensuite, nous vérifions Tissu nerveux. »
   - AR : «ثم نتحقق من النسيج العصبي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:astrocyte:v1` — **an astrocyte** · FR : Astrocyte · AR : الخلية الدبقية النجمية
- `gen:nervous-tissue:v1` — **a nervous tissue** · FR : Tissu nerveux · AR : النسيج العصبي
- `gen:neuron:v1` — **a neuron** · FR : Neurone (Cellule nerveuse) · AR : العصبون (الخلية العصبية)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : astrocyte ; we check astrocyte ; astrocyte first ; the doctor checks astrocyte first
  - modèle : First, the doctor checks astrocyte.
- **q2.** What does the doctor check next?
  - accepté : nervous tissue ; then we check nervous tissue ; nervous tissue next ; the doctor checks nervous tissue next
  - modèle : Then, the doctor checks nervous tissue.
- **q3.** Does the doctor check nervous tissue before astrocyte?
  - accepté : no ; no he does not ; no he doesn't ; he checks astrocyte first
  - modèle : No, he checks astrocyte first, then nervous tissue.
  - hint (si erreur) — FR : Attention : Astrocyte vient D'ABORD, ensuite Tissu nerveux.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : neuron ; neuron first ; he checks neuron first
  - modèle : First, the doctor checks neuron.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check astrocyte. Then, we check nervous tissue.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Neurone (Cellule nerveuse), ensuite Astrocyte, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check neuron. Then, we check astrocyte.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `histo-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Astrocyte / Tissu nerveux, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the astrocyte.
   - FR : Le patient du lit 3 a Astrocyte.
   - AR : مريض السرير ٣ عنده الخلية الدبقية النجمية.
4. **EN** The patient in bed 5 has a problem with the nervous tissue.
   - FR : Le patient du lit 5 a Tissu nerveux.
   - AR : مريض السرير ٥ عنده النسيج العصبي.
5. **EN** The patient in bed 5 does not have a problem with the astrocyte.
   - FR : Le patient du lit 5 n'a pas de Astrocyte.
   - AR : مريض السرير ٥ ليس عنده الخلية الدبقية النجمية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:astrocyte:v1` — **an astrocyte** · FR : Astrocyte · AR : الخلية الدبقية النجمية
- `gen:nervous-tissue:v1` — **a nervous tissue** · FR : Tissu nerveux · AR : النسيج العصبي
- `gen:neuron:v1` — **a neuron** · FR : Neurone (Cellule nerveuse) · AR : العصبون (الخلية العصبية)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : astrocyte ; an astrocyte ; the patient with astrocyte
  - modèle : The patient in bed 3 has an astrocyte.
- **q2.** What does the patient in bed 5 have?
  - accepté : nervous tissue ; a problem with the nervous tissue
  - modèle : He has a problem with the nervous tissue.
- **q3.** Does the patient in bed 5 have astrocyte?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the astrocyte ; he doesn't
  - modèle : No, he does not have a problem with the astrocyte.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS astrocyte — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : neuron ; a problem with the neuron
  - modèle : He has a problem with the neuron.
- **Tt2.** Does the patient in bed 9 have nervous tissue?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the nervous tissue ; he doesn't
  - modèle : No, he does not have a problem with the nervous tissue.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS tissu nerveux.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an astrocyte and a nervous tissue. (have)` → have · modèle : The patients have an astrocyte and a nervous tissue.
- `The patient in bed 5 … not have an astrocyte. (do)` → does · modèle : The patient in bed 5 does not have an astrocyte.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the astrocyte. The patient in bed 5 has a problem with the nervous tissue.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Neurone (Cellule nerveuse), le lit 9 a Astrocyte et n'a pas Tissu nerveux.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the neuron. The patient in bed 9 has a problem with the astrocyte.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
