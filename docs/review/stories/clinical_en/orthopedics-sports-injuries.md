# Relecture — Orthopedics & Sports Injuries (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-028` — Le cas de Farid : Tendinite anormal(e) / _Farid: the tendinitis is not normal_

**Objectif :** Comprendre des résultats : Claquage musculaire normal(e), Tendinite anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The strain is normal.”
   - FR : Le médecin dit : « Claquage musculaire est normal(e). »
   - AR : يقول الطبيب: «شد عضلي طبيعي/طبيعية.»
5. **EN** “But the tendinitis is not normal.”
   - FR : « Mais Tendinite n'est pas normal(e). »
   - AR : «لكن التهاب الوتر غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the tendinitis normal?”
   - FR : Lina demande : « Tendinite est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل التهاب الوتر طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:strain:v1` — **a strain** · FR : Claquage musculaire · AR : شد عضلي
- `gen:dislocation:v1` — **a dislocation** · FR : Luxation · AR : خلع المفصل
- `gen:tendinitis:v1` — **tendinitis** · FR : Tendinite · AR : التهاب الوتر

### Questions (acceptées / modèle / hint)
- **q1.** Is the strain normal?
  - accepté : yes ; yes it is ; the strain is normal ; it is normal
  - modèle : Yes, the strain is normal.
- **q2.** Is the tendinitis normal?
  - accepté : no ; no it is not ; no it isn't ; the tendinitis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the tendinitis is not normal.
  - hint (si erreur) — FR : Attention : Tendinite n'est PAS normal(e).
- **q3.** Which one is not normal: the strain or the tendinitis?
  - accepté : tendinitis ; the tendinitis
  - modèle : The tendinitis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the dislocation normal?
  - accepté : yes ; yes it is ; the dislocation is normal
  - modèle : Yes, the dislocation is normal.
- **Tt2.** Is the strain normal?
  - accepté : no ; no it is not ; no it isn't ; the strain is not normal ; it is not normal
  - modèle : No, the strain is not normal.
  - hint (si erreur) — FR : Attention : Claquage musculaire n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The strain … normal. (be)` → is · modèle : The strain is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The strain is… / The tendinitis is not… — modèle : *The strain is normal. The tendinitis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Luxation normal(e), mais Claquage musculaire anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The dislocation is normal. The strain is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-028` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Claquage musculaire et Luxation à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient.
   - FR : Sofiane est un·e patient·e.
   - AR : Sofiane مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check strain.”
   - FR : « D'abord, nous vérifions Claquage musculaire. »
   - AR : «أولًا، نتحقق من شد عضلي.»
5. **EN** “Then, we check dislocation.”
   - FR : « Ensuite, nous vérifions Luxation. »
   - AR : «ثم نتحقق من خلع المفصل.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:strain:v1` — **a strain** · FR : Claquage musculaire · AR : شد عضلي
- `gen:dislocation:v1` — **a dislocation** · FR : Luxation · AR : خلع المفصل
- `gen:tendinitis:v1` — **tendinitis** · FR : Tendinite · AR : التهاب الوتر

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : strain ; we check strain ; strain first ; the doctor checks strain first
  - modèle : First, the doctor checks strain.
- **q2.** What does the doctor check next?
  - accepté : dislocation ; then we check dislocation ; dislocation next ; the doctor checks dislocation next
  - modèle : Then, the doctor checks dislocation.
- **q3.** Does the doctor check dislocation before strain?
  - accepté : no ; no he does not ; no he doesn't ; he checks strain first
  - modèle : No, he checks strain first, then dislocation.
  - hint (si erreur) — FR : Attention : Claquage musculaire vient D'ABORD, ensuite Luxation.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : tendinitis ; tendinitis first ; he checks tendinitis first
  - modèle : First, the doctor checks tendinitis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check strain. Then, we check dislocation.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Tendinite, ensuite Claquage musculaire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check tendinitis. Then, we check strain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-028` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Claquage musculaire / Luxation, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the strain.
   - FR : Le patient du lit 3 a Claquage musculaire.
   - AR : مريض السرير ٣ عنده شد عضلي.
4. **EN** The patient in bed 5 has a problem with the dislocation.
   - FR : Le patient du lit 5 a Luxation.
   - AR : مريض السرير ٥ عنده خلع المفصل.
5. **EN** The patient in bed 5 does not have a problem with the strain.
   - FR : Le patient du lit 5 n'a pas de Claquage musculaire.
   - AR : مريض السرير ٥ ليس عنده شد عضلي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:strain:v1` — **a strain** · FR : Claquage musculaire · AR : شد عضلي
- `gen:dislocation:v1` — **a dislocation** · FR : Luxation · AR : خلع المفصل
- `gen:tendinitis:v1` — **tendinitis** · FR : Tendinite · AR : التهاب الوتر

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : strain ; a strain ; the patient with strain
  - modèle : The patient in bed 3 has a strain.
- **q2.** What does the patient in bed 5 have?
  - accepté : dislocation ; a problem with the dislocation
  - modèle : He has a problem with the dislocation.
- **q3.** Does the patient in bed 5 have strain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the strain ; he doesn't
  - modèle : No, he does not have a problem with the strain.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS claquage musculaire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : tendinitis ; a problem with the tendinitis
  - modèle : He has a problem with the tendinitis.
- **Tt2.** Does the patient in bed 9 have dislocation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the dislocation ; he doesn't
  - modèle : No, he does not have a problem with the dislocation.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS luxation.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a strain and a dislocation. (have)` → have · modèle : The patients have a strain and a dislocation.
- `The patient in bed 5 … not have a strain. (do)` → does · modèle : The patient in bed 5 does not have a strain.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the strain. The patient in bed 5 has a problem with the dislocation.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Tendinite, le lit 9 a Claquage musculaire et n'a pas Luxation.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the tendinitis. The patient in bed 9 has a problem with the strain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
