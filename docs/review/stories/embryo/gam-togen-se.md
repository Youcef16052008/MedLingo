# Relecture — Gamétogenèse (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-002` — Le cas de Youssef : Gamète anormal(e) / _Youssef: the gamete is not normal_

**Objectif :** Comprendre des résultats : Embryologie normal(e), Gamète anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The embryology is normal.”
   - FR : Le médecin dit : « Embryologie est normal(e). »
   - AR : يقول الطبيب: «علم الأجنة طبيعي/طبيعية.»
5. **EN** “But the gamete is not normal.”
   - FR : « Mais Gamète n'est pas normal(e). »
   - AR : «لكن المشيج غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the gamete normal?”
   - FR : Lina demande : « Gamète est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل المشيج طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:embryology:v1` — **an embryology** · FR : Embryologie · AR : علم الأجنة
- `gen:gametogenesis:v1` — **gametogenesis** · FR : Gamétogenèse · AR : تكوين الأمشاج
- `gen:gamete:v1` — **a gamete** · FR : Gamète · AR : المشيج

### Questions (acceptées / modèle / hint)
- **q1.** Is the embryology normal?
  - accepté : yes ; yes it is ; the embryology is normal ; it is normal
  - modèle : Yes, the embryology is normal.
- **q2.** Is the gamete normal?
  - accepté : no ; no it is not ; no it isn't ; the gamete is not normal ; it is not normal ; it isn't normal
  - modèle : No, the gamete is not normal.
  - hint (si erreur) — FR : Attention : Gamète n'est PAS normal(e).
- **q3.** Which one is not normal: the embryology or the gamete?
  - accepté : gamete ; the gamete
  - modèle : The gamete is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the gametogenesis normal?
  - accepté : yes ; yes it is ; the gametogenesis is normal
  - modèle : Yes, the gametogenesis is normal.
- **Tt2.** Is the embryology normal?
  - accepté : no ; no it is not ; no it isn't ; the embryology is not normal ; it is not normal
  - modèle : No, the embryology is not normal.
  - hint (si erreur) — FR : Attention : Embryologie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The embryology … normal. (be)` → is · modèle : The embryology is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The embryology is… / The gamete is not… — modèle : *The embryology is normal. The gamete is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Gamétogenèse normal(e), mais Embryologie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The gametogenesis is normal. The embryology is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Embryologie et Gamétogenèse à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check embryology.”
   - FR : « D'abord, nous vérifions Embryologie. »
   - AR : «أولًا، نتحقق من علم الأجنة.»
5. **EN** “Then, we check gametogenesis.”
   - FR : « Ensuite, nous vérifions Gamétogenèse. »
   - AR : «ثم نتحقق من تكوين الأمشاج.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:embryology:v1` — **an embryology** · FR : Embryologie · AR : علم الأجنة
- `gen:gametogenesis:v1` — **gametogenesis** · FR : Gamétogenèse · AR : تكوين الأمشاج
- `gen:gamete:v1` — **a gamete** · FR : Gamète · AR : المشيج

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : embryology ; we check embryology ; embryology first ; the doctor checks embryology first
  - modèle : First, the doctor checks embryology.
- **q2.** What does the doctor check next?
  - accepté : gametogenesis ; then we check gametogenesis ; gametogenesis next ; the doctor checks gametogenesis next
  - modèle : Then, the doctor checks gametogenesis.
- **q3.** Does the doctor check gametogenesis before embryology?
  - accepté : no ; no he does not ; no he doesn't ; he checks embryology first
  - modèle : No, he checks embryology first, then gametogenesis.
  - hint (si erreur) — FR : Attention : Embryologie vient D'ABORD, ensuite Gamétogenèse.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : gamete ; gamete first ; he checks gamete first
  - modèle : First, the doctor checks gamete.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check embryology. Then, we check gametogenesis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Gamète, ensuite Embryologie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check gamete. Then, we check embryology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Embryologie / Gamétogenèse, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the embryology.
   - FR : Le patient du lit 3 a Embryologie.
   - AR : مريض السرير ٣ عنده علم الأجنة.
4. **EN** The patient in bed 5 has a problem with the gametogenesis.
   - FR : Le patient du lit 5 a Gamétogenèse.
   - AR : مريض السرير ٥ عنده تكوين الأمشاج.
5. **EN** The patient in bed 5 does not have a problem with the embryology.
   - FR : Le patient du lit 5 n'a pas de Embryologie.
   - AR : مريض السرير ٥ ليس عنده علم الأجنة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:embryology:v1` — **an embryology** · FR : Embryologie · AR : علم الأجنة
- `gen:gametogenesis:v1` — **gametogenesis** · FR : Gamétogenèse · AR : تكوين الأمشاج
- `gen:gamete:v1` — **a gamete** · FR : Gamète · AR : المشيج

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : embryology ; an embryology ; the patient with embryology
  - modèle : The patient in bed 3 has an embryology.
- **q2.** What does the patient in bed 5 have?
  - accepté : gametogenesis ; a problem with the gametogenesis
  - modèle : He has a problem with the gametogenesis.
- **q3.** Does the patient in bed 5 have embryology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the embryology ; he doesn't
  - modèle : No, he does not have a problem with the embryology.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS embryologie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : gamete ; a problem with the gamete
  - modèle : He has a problem with the gamete.
- **Tt2.** Does the patient in bed 9 have gametogenesis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the gametogenesis ; he doesn't
  - modèle : No, he does not have a problem with the gametogenesis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS gamétogenèse.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an embryology and gametogenesis. (have)` → have · modèle : The patients have an embryology and gametogenesis.
- `The patient in bed 5 … not have an embryology. (do)` → does · modèle : The patient in bed 5 does not have an embryology.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the embryology. The patient in bed 5 has a problem with the gametogenesis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Gamète, le lit 9 a Embryologie et n'a pas Gamétogenèse.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the gamete. The patient in bed 9 has a problem with the embryology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
