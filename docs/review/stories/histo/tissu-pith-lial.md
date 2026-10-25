# Relecture — Tissu Épithélial (histo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `histo-case-011` — Le cas de Leila : Épithélium stratifié anormal(e) / _Leila: the stratified epithelium is not normal_

**Objectif :** Comprendre des résultats : Tissu épithélial (Épithélium) normal(e), Épithélium stratifié anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The epithelial tissue is normal.”
   - FR : Le médecin dit : « Tissu épithélial (Épithélium) est normal(e). »
   - AR : يقول الطبيب: «النسيج الظهاري طبيعي/طبيعية.»
5. **EN** “But the stratified epithelium is not normal.”
   - FR : « Mais Épithélium stratifié n'est pas normal(e). »
   - AR : «لكن ظهارة طبقية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the stratified epithelium normal?”
   - FR : Amine demande : « Épithélium stratifié est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل ظهارة طبقية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:epithelial-tissue:v1` — **an epithelial tissue** · FR : Tissu épithélial (Épithélium) · AR : النسيج الظهاري
- `gen:simple-epithelium:v1` — **a simple epithelium** · FR : Épithélium simple · AR : ظهارة بسيطة
- `gen:stratified-epithelium:v1` — **a stratified epithelium** · FR : Épithélium stratifié · AR : ظهارة طبقية

### Questions (acceptées / modèle / hint)
- **q1.** Is the epithelial tissue normal?
  - accepté : yes ; yes it is ; the epithelial tissue is normal ; it is normal
  - modèle : Yes, the epithelial tissue is normal.
- **q2.** Is the stratified epithelium normal?
  - accepté : no ; no it is not ; no it isn't ; the stratified epithelium is not normal ; it is not normal ; it isn't normal
  - modèle : No, the stratified epithelium is not normal.
  - hint (si erreur) — FR : Attention : Épithélium stratifié n'est PAS normal(e).
- **q3.** Which one is not normal: the epithelial tissue or the stratified epithelium?
  - accepté : stratified epithelium ; the stratified epithelium
  - modèle : The stratified epithelium is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the simple epithelium normal?
  - accepté : yes ; yes it is ; the simple epithelium is normal
  - modèle : Yes, the simple epithelium is normal.
- **Tt2.** Is the epithelial tissue normal?
  - accepté : no ; no it is not ; no it isn't ; the epithelial tissue is not normal ; it is not normal
  - modèle : No, the epithelial tissue is not normal.
  - hint (si erreur) — FR : Attention : Tissu épithélial (Épithélium) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The epithelial tissue … normal. (be)` → is · modèle : The epithelial tissue is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Leila en deux phrases. Garde la négation. — amorces : The epithelial tissue is… / The stratified epithelium is not… — modèle : *The epithelial tissue is normal. The stratified epithelium is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Épithélium simple normal(e), mais Tissu épithélial (Épithélium) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The simple epithelium is normal. The epithelial tissue is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `histo-explain-011` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Tissu épithélial (Épithélium) et Épithélium simple à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient.
   - FR : Nadia est un·e patient·e.
   - AR : Nadia مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check epithelial tissue.”
   - FR : « D'abord, nous vérifions Tissu épithélial (Épithélium). »
   - AR : «أولًا، نتحقق من النسيج الظهاري.»
5. **EN** “Then, we check simple epithelium.”
   - FR : « Ensuite, nous vérifions Épithélium simple. »
   - AR : «ثم نتحقق من ظهارة بسيطة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:epithelial-tissue:v1` — **an epithelial tissue** · FR : Tissu épithélial (Épithélium) · AR : النسيج الظهاري
- `gen:simple-epithelium:v1` — **a simple epithelium** · FR : Épithélium simple · AR : ظهارة بسيطة
- `gen:stratified-epithelium:v1` — **a stratified epithelium** · FR : Épithélium stratifié · AR : ظهارة طبقية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : epithelial tissue ; we check epithelial tissue ; epithelial tissue first ; the doctor checks epithelial tissue first
  - modèle : First, the doctor checks epithelial tissue.
- **q2.** What does the doctor check next?
  - accepté : simple epithelium ; then we check simple epithelium ; simple epithelium next ; the doctor checks simple epithelium next
  - modèle : Then, the doctor checks simple epithelium.
- **q3.** Does the doctor check simple epithelium before epithelial tissue?
  - accepté : no ; no he does not ; no he doesn't ; he checks epithelial tissue first
  - modèle : No, he checks epithelial tissue first, then simple epithelium.
  - hint (si erreur) — FR : Attention : Tissu épithélial (Épithélium) vient D'ABORD, ensuite Épithélium simple.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : stratified epithelium ; stratified epithelium first ; he checks stratified epithelium first
  - modèle : First, the doctor checks stratified epithelium.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check epithelial tissue. Then, we check simple epithelium.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Épithélium stratifié, ensuite Tissu épithélial (Épithélium), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check stratified epithelium. Then, we check epithelial tissue.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `histo-handover-011` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Tissu épithélial (Épithélium) / Épithélium simple, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the epithelial tissue.
   - FR : Le patient du lit 3 a Tissu épithélial (Épithélium).
   - AR : مريض السرير ٣ عنده النسيج الظهاري.
4. **EN** The patient in bed 5 has a problem with the simple epithelium.
   - FR : Le patient du lit 5 a Épithélium simple.
   - AR : مريض السرير ٥ عنده ظهارة بسيطة.
5. **EN** The patient in bed 5 does not have a problem with the epithelial tissue.
   - FR : Le patient du lit 5 n'a pas de Tissu épithélial (Épithélium).
   - AR : مريض السرير ٥ ليس عنده النسيج الظهاري.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:epithelial-tissue:v1` — **an epithelial tissue** · FR : Tissu épithélial (Épithélium) · AR : النسيج الظهاري
- `gen:simple-epithelium:v1` — **a simple epithelium** · FR : Épithélium simple · AR : ظهارة بسيطة
- `gen:stratified-epithelium:v1` — **a stratified epithelium** · FR : Épithélium stratifié · AR : ظهارة طبقية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : epithelial tissue ; an epithelial tissue ; the patient with epithelial tissue
  - modèle : The patient in bed 3 has an epithelial tissue.
- **q2.** What does the patient in bed 5 have?
  - accepté : simple epithelium ; a problem with the simple epithelium
  - modèle : He has a problem with the simple epithelium.
- **q3.** Does the patient in bed 5 have epithelial tissue?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the epithelial tissue ; he doesn't
  - modèle : No, he does not have a problem with the epithelial tissue.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS tissu épithélial (épithélium) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : stratified epithelium ; a problem with the stratified epithelium
  - modèle : He has a problem with the stratified epithelium.
- **Tt2.** Does the patient in bed 9 have simple epithelium?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the simple epithelium ; he doesn't
  - modèle : No, he does not have a problem with the simple epithelium.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS épithélium simple.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an epithelial tissue and a simple epithelium. (have)` → have · modèle : The patients have an epithelial tissue and a simple epithelium.
- `The patient in bed 5 … not have an epithelial tissue. (do)` → does · modèle : The patient in bed 5 does not have an epithelial tissue.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the epithelial tissue. The patient in bed 5 has a problem with the simple epithelium.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Épithélium stratifié, le lit 9 a Tissu épithélial (Épithélium) et n'a pas Épithélium simple.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the stratified epithelium. The patient in bed 9 has a problem with the epithelial tissue.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
