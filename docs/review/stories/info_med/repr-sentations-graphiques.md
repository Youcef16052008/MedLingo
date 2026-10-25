# Relecture — Représentations Graphiques (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-008` — Le cas de Bilal : Diagramme circulaire (Camembert) anormal(e) / _Bilal: the pie chart is not normal_

**Objectif :** Comprendre des résultats : Histogramme normal(e), Diagramme circulaire (Camembert) anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The histogram is normal.”
   - FR : Le médecin dit : « Histogramme est normal(e). »
   - AR : يقول الطبيب: «المدرج التكراري طبيعي/طبيعية.»
5. **EN** “But the pie chart is not normal.”
   - FR : « Mais Diagramme circulaire (Camembert) n'est pas normal(e). »
   - AR : «لكن المخطط الدائري غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the pie chart normal?”
   - FR : Lina demande : « Diagramme circulaire (Camembert) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل المخطط الدائري طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:histogram:v1` — **a histogram** · FR : Histogramme · AR : المدرج التكراري
- `gen:bar-chart:v1` — **a bar chart** · FR : Diagramme en barres · AR : مخطط الأعمدة
- `gen:pie-chart:v1` — **a pie chart** · FR : Diagramme circulaire (Camembert) · AR : المخطط الدائري

### Questions (acceptées / modèle / hint)
- **q1.** Is the histogram normal?
  - accepté : yes ; yes it is ; the histogram is normal ; it is normal
  - modèle : Yes, the histogram is normal.
- **q2.** Is the pie chart normal?
  - accepté : no ; no it is not ; no it isn't ; the pie chart is not normal ; it is not normal ; it isn't normal
  - modèle : No, the pie chart is not normal.
  - hint (si erreur) — FR : Attention : Diagramme circulaire (Camembert) n'est PAS normal(e).
- **q3.** Which one is not normal: the histogram or the pie chart?
  - accepté : pie chart ; the pie chart
  - modèle : The pie chart is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the bar chart normal?
  - accepté : yes ; yes it is ; the bar chart is normal
  - modèle : Yes, the bar chart is normal.
- **Tt2.** Is the histogram normal?
  - accepté : no ; no it is not ; no it isn't ; the histogram is not normal ; it is not normal
  - modèle : No, the histogram is not normal.
  - hint (si erreur) — FR : Attention : Histogramme n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The histogram … normal. (be)` → is · modèle : The histogram is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The histogram is… / The pie chart is not… — modèle : *The histogram is normal. The pie chart is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Diagramme en barres normal(e), mais Histogramme anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The bar chart is normal. The histogram is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Histogramme et Diagramme en barres à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check histogram.”
   - FR : « D'abord, nous vérifions Histogramme. »
   - AR : «أولًا، نتحقق من المدرج التكراري.»
5. **EN** “Then, we check bar chart.”
   - FR : « Ensuite, nous vérifions Diagramme en barres. »
   - AR : «ثم نتحقق من مخطط الأعمدة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:histogram:v1` — **a histogram** · FR : Histogramme · AR : المدرج التكراري
- `gen:bar-chart:v1` — **a bar chart** · FR : Diagramme en barres · AR : مخطط الأعمدة
- `gen:pie-chart:v1` — **a pie chart** · FR : Diagramme circulaire (Camembert) · AR : المخطط الدائري

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : histogram ; we check histogram ; histogram first ; the doctor checks histogram first
  - modèle : First, the doctor checks histogram.
- **q2.** What does the doctor check next?
  - accepté : bar chart ; then we check bar chart ; bar chart next ; the doctor checks bar chart next
  - modèle : Then, the doctor checks bar chart.
- **q3.** Does the doctor check bar chart before histogram?
  - accepté : no ; no he does not ; no he doesn't ; he checks histogram first
  - modèle : No, he checks histogram first, then bar chart.
  - hint (si erreur) — FR : Attention : Histogramme vient D'ABORD, ensuite Diagramme en barres.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : pie chart ; pie chart first ; he checks pie chart first
  - modèle : First, the doctor checks pie chart.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check histogram. Then, we check bar chart.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Diagramme circulaire (Camembert), ensuite Histogramme, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check pie chart. Then, we check histogram.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Histogramme / Diagramme en barres, lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the histogram.
   - FR : Le patient du lit 3 a Histogramme.
   - AR : مريض السرير ٣ عنده المدرج التكراري.
4. **EN** The patient in bed 5 has a problem with the bar chart.
   - FR : Le patient du lit 5 a Diagramme en barres.
   - AR : مريض السرير ٥ عنده مخطط الأعمدة.
5. **EN** The patient in bed 5 does not have a problem with the histogram.
   - FR : Le patient du lit 5 n'a pas de Histogramme.
   - AR : مريض السرير ٥ ليس عنده المدرج التكراري.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:histogram:v1` — **a histogram** · FR : Histogramme · AR : المدرج التكراري
- `gen:bar-chart:v1` — **a bar chart** · FR : Diagramme en barres · AR : مخطط الأعمدة
- `gen:pie-chart:v1` — **a pie chart** · FR : Diagramme circulaire (Camembert) · AR : المخطط الدائري

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : histogram ; a histogram ; the patient with histogram
  - modèle : The patient in bed 3 has a histogram.
- **q2.** What does the patient in bed 5 have?
  - accepté : bar chart ; a problem with the bar chart
  - modèle : He has a problem with the bar chart.
- **q3.** Does the patient in bed 5 have histogram?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the histogram ; he doesn't
  - modèle : No, he does not have a problem with the histogram.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS histogramme — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : pie chart ; a problem with the pie chart
  - modèle : He has a problem with the pie chart.
- **Tt2.** Does the patient in bed 9 have bar chart?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the bar chart ; he doesn't
  - modèle : No, he does not have a problem with the bar chart.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS diagramme en barres.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a histogram and a bar chart. (have)` → have · modèle : The patients have a histogram and a bar chart.
- `The patient in bed 5 … not have a histogram. (do)` → does · modèle : The patient in bed 5 does not have a histogram.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the histogram. The patient in bed 5 has a problem with the bar chart.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Diagramme circulaire (Camembert), le lit 9 a Histogramme et n'a pas Diagramme en barres.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the pie chart. The patient in bed 9 has a problem with the histogram.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
