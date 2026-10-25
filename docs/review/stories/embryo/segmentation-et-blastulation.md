# Relecture — Segmentation et Blastulation (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-009` — Le cas de Sara : Morula anormal(e) / _Sara: the morula is not normal_

**Objectif :** Comprendre des résultats : Segmentation (Clivage) normal(e), Morula anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The cleavage is normal.”
   - FR : Le médecin dit : « Segmentation (Clivage) est normal(e). »
   - AR : يقول الطبيب: «التفتت طبيعي/طبيعية.»
5. **EN** “But the morula is not normal.”
   - FR : « Mais Morula n'est pas normal(e). »
   - AR : «لكن التوتة (الأريمة المصمتة) غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the morula normal?”
   - FR : Amine demande : « Morula est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل التوتة (الأريمة المصمتة) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:cleavage:v1` — **a cleavage** · FR : Segmentation (Clivage) · AR : التفتت
- `gen:blastomere:v1` — **a blastomere** · FR : Blastomère · AR : الأريمة
- `gen:morula:v1` — **a morula** · FR : Morula · AR : التوتة (الأريمة المصمتة)

### Questions (acceptées / modèle / hint)
- **q1.** Is the cleavage normal?
  - accepté : yes ; yes it is ; the cleavage is normal ; it is normal
  - modèle : Yes, the cleavage is normal.
- **q2.** Is the morula normal?
  - accepté : no ; no it is not ; no it isn't ; the morula is not normal ; it is not normal ; it isn't normal
  - modèle : No, the morula is not normal.
  - hint (si erreur) — FR : Attention : Morula n'est PAS normal(e).
- **q3.** Which one is not normal: the cleavage or the morula?
  - accepté : morula ; the morula
  - modèle : The morula is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the blastomere normal?
  - accepté : yes ; yes it is ; the blastomere is normal
  - modèle : Yes, the blastomere is normal.
- **Tt2.** Is the cleavage normal?
  - accepté : no ; no it is not ; no it isn't ; the cleavage is not normal ; it is not normal
  - modèle : No, the cleavage is not normal.
  - hint (si erreur) — FR : Attention : Segmentation (Clivage) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The cleavage … normal. (be)` → is · modèle : The cleavage is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The cleavage is… / The morula is not… — modèle : *The cleavage is normal. The morula is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Blastomère normal(e), mais Segmentation (Clivage) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The blastomere is normal. The cleavage is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-009` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Segmentation (Clivage) et Blastomère à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient.
   - FR : Leila est un·e patient·e.
   - AR : Leila مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check cleavage.”
   - FR : « D'abord, nous vérifions Segmentation (Clivage). »
   - AR : «أولًا، نتحقق من التفتت.»
5. **EN** “Then, we check blastomere.”
   - FR : « Ensuite, nous vérifions Blastomère. »
   - AR : «ثم نتحقق من الأريمة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cleavage:v1` — **a cleavage** · FR : Segmentation (Clivage) · AR : التفتت
- `gen:blastomere:v1` — **a blastomere** · FR : Blastomère · AR : الأريمة
- `gen:morula:v1` — **a morula** · FR : Morula · AR : التوتة (الأريمة المصمتة)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cleavage ; we check cleavage ; cleavage first ; the doctor checks cleavage first
  - modèle : First, the doctor checks cleavage.
- **q2.** What does the doctor check next?
  - accepté : blastomere ; then we check blastomere ; blastomere next ; the doctor checks blastomere next
  - modèle : Then, the doctor checks blastomere.
- **q3.** Does the doctor check blastomere before cleavage?
  - accepté : no ; no he does not ; no he doesn't ; he checks cleavage first
  - modèle : No, he checks cleavage first, then blastomere.
  - hint (si erreur) — FR : Attention : Segmentation (Clivage) vient D'ABORD, ensuite Blastomère.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : morula ; morula first ; he checks morula first
  - modèle : First, the doctor checks morula.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cleavage. Then, we check blastomere.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Morula, ensuite Segmentation (Clivage), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check morula. Then, we check cleavage.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-009` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Segmentation (Clivage) / Blastomère, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the cleavage.
   - FR : Le patient du lit 3 a Segmentation (Clivage).
   - AR : مريض السرير ٣ عنده التفتت.
4. **EN** The patient in bed 5 has a problem with the blastomere.
   - FR : Le patient du lit 5 a Blastomère.
   - AR : مريض السرير ٥ عنده الأريمة.
5. **EN** The patient in bed 5 does not have a problem with the cleavage.
   - FR : Le patient du lit 5 n'a pas de Segmentation (Clivage).
   - AR : مريض السرير ٥ ليس عنده التفتت.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:cleavage:v1` — **a cleavage** · FR : Segmentation (Clivage) · AR : التفتت
- `gen:blastomere:v1` — **a blastomere** · FR : Blastomère · AR : الأريمة
- `gen:morula:v1` — **a morula** · FR : Morula · AR : التوتة (الأريمة المصمتة)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cleavage ; a cleavage ; the patient with cleavage
  - modèle : The patient in bed 3 has a cleavage.
- **q2.** What does the patient in bed 5 have?
  - accepté : blastomere ; a problem with the blastomere
  - modèle : He has a problem with the blastomere.
- **q3.** Does the patient in bed 5 have cleavage?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the cleavage ; he doesn't
  - modèle : No, he does not have a problem with the cleavage.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS segmentation (clivage) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : morula ; a problem with the morula
  - modèle : He has a problem with the morula.
- **Tt2.** Does the patient in bed 9 have blastomere?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the blastomere ; he doesn't
  - modèle : No, he does not have a problem with the blastomere.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS blastomère.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a cleavage and a blastomere. (have)` → have · modèle : The patients have a cleavage and a blastomere.
- `The patient in bed 5 … not have a cleavage. (do)` → does · modèle : The patient in bed 5 does not have a cleavage.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the cleavage. The patient in bed 5 has a problem with the blastomere.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Morula, le lit 9 a Segmentation (Clivage) et n'a pas Blastomère.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the morula. The patient in bed 9 has a problem with the cleavage.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
