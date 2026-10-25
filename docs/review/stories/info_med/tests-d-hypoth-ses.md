# Relecture — Tests d'Hypothèses (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-011` — Le cas de Leila : Hypothèse alternative (H₁ ou Hₐ) anormal(e) / _Leila: the alternative hypothesis is not normal_

**Objectif :** Comprendre des résultats : Hypothèse normal(e), Hypothèse alternative (H₁ ou Hₐ) anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The hypothesis is normal.”
   - FR : Le médecin dit : « Hypothèse est normal(e). »
   - AR : يقول الطبيب: «الفرضية طبيعي/طبيعية.»
5. **EN** “But the alternative hypothesis is not normal.”
   - FR : « Mais Hypothèse alternative (H₁ ou Hₐ) n'est pas normal(e). »
   - AR : «لكن الفرضية البديلة غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the alternative hypothesis normal?”
   - FR : Amine demande : « Hypothèse alternative (H₁ ou Hₐ) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الفرضية البديلة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:hypothesis:v1` — **hypothesis** · FR : Hypothèse · AR : الفرضية
- `gen:null-hypothesis:v1` — **null hypothesis** · FR : Hypothèse nulle (H₀) · AR : الفرضية الصفرية
- `gen:alternative-hypothesis:v1` — **alternative hypothesis** · FR : Hypothèse alternative (H₁ ou Hₐ) · AR : الفرضية البديلة

### Questions (acceptées / modèle / hint)
- **q1.** Is the hypothesis normal?
  - accepté : yes ; yes it is ; the hypothesis is normal ; it is normal
  - modèle : Yes, the hypothesis is normal.
- **q2.** Is the alternative hypothesis normal?
  - accepté : no ; no it is not ; no it isn't ; the alternative hypothesis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the alternative hypothesis is not normal.
  - hint (si erreur) — FR : Attention : Hypothèse alternative (H₁ ou Hₐ) n'est PAS normal(e).
- **q3.** Which one is not normal: the hypothesis or the alternative hypothesis?
  - accepté : alternative hypothesis ; the alternative hypothesis
  - modèle : The alternative hypothesis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the null hypothesis normal?
  - accepté : yes ; yes it is ; the null hypothesis is normal
  - modèle : Yes, the null hypothesis is normal.
- **Tt2.** Is the hypothesis normal?
  - accepté : no ; no it is not ; no it isn't ; the hypothesis is not normal ; it is not normal
  - modèle : No, the hypothesis is not normal.
  - hint (si erreur) — FR : Attention : Hypothèse n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The hypothesis … normal. (be)` → is · modèle : The hypothesis is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Leila en deux phrases. Garde la négation. — amorces : The hypothesis is… / The alternative hypothesis is not… — modèle : *The hypothesis is normal. The alternative hypothesis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Hypothèse nulle (H₀) normal(e), mais Hypothèse anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The null hypothesis is normal. The hypothesis is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-011` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Hypothèse et Hypothèse nulle (H₀) à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check hypothesis.”
   - FR : « D'abord, nous vérifions Hypothèse. »
   - AR : «أولًا، نتحقق من الفرضية.»
5. **EN** “Then, we check null hypothesis.”
   - FR : « Ensuite, nous vérifions Hypothèse nulle (H₀). »
   - AR : «ثم نتحقق من الفرضية الصفرية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:hypothesis:v1` — **hypothesis** · FR : Hypothèse · AR : الفرضية
- `gen:null-hypothesis:v1` — **null hypothesis** · FR : Hypothèse nulle (H₀) · AR : الفرضية الصفرية
- `gen:alternative-hypothesis:v1` — **alternative hypothesis** · FR : Hypothèse alternative (H₁ ou Hₐ) · AR : الفرضية البديلة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : hypothesis ; we check hypothesis ; hypothesis first ; the doctor checks hypothesis first
  - modèle : First, the doctor checks hypothesis.
- **q2.** What does the doctor check next?
  - accepté : null hypothesis ; then we check null hypothesis ; null hypothesis next ; the doctor checks null hypothesis next
  - modèle : Then, the doctor checks null hypothesis.
- **q3.** Does the doctor check null hypothesis before hypothesis?
  - accepté : no ; no he does not ; no he doesn't ; he checks hypothesis first
  - modèle : No, he checks hypothesis first, then null hypothesis.
  - hint (si erreur) — FR : Attention : Hypothèse vient D'ABORD, ensuite Hypothèse nulle (H₀).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : alternative hypothesis ; alternative hypothesis first ; he checks alternative hypothesis first
  - modèle : First, the doctor checks alternative hypothesis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check hypothesis. Then, we check null hypothesis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Hypothèse alternative (H₁ ou Hₐ), ensuite Hypothèse, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check alternative hypothesis. Then, we check hypothesis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-011` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Hypothèse / Hypothèse nulle (H₀), lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the hypothesis.
   - FR : Le patient du lit 3 a Hypothèse.
   - AR : مريض السرير ٣ عنده الفرضية.
4. **EN** The patient in bed 5 has a problem with the null hypothesis.
   - FR : Le patient du lit 5 a Hypothèse nulle (H₀).
   - AR : مريض السرير ٥ عنده الفرضية الصفرية.
5. **EN** The patient in bed 5 does not have a problem with the hypothesis.
   - FR : Le patient du lit 5 n'a pas de Hypothèse.
   - AR : مريض السرير ٥ ليس عنده الفرضية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:hypothesis:v1` — **hypothesis** · FR : Hypothèse · AR : الفرضية
- `gen:null-hypothesis:v1` — **null hypothesis** · FR : Hypothèse nulle (H₀) · AR : الفرضية الصفرية
- `gen:alternative-hypothesis:v1` — **alternative hypothesis** · FR : Hypothèse alternative (H₁ ou Hₐ) · AR : الفرضية البديلة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hypothesis ; the patient with hypothesis
  - modèle : The patient in bed 3 has hypothesis.
- **q2.** What does the patient in bed 5 have?
  - accepté : null hypothesis ; a problem with the null hypothesis
  - modèle : He has a problem with the null hypothesis.
- **q3.** Does the patient in bed 5 have hypothesis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the hypothesis ; he doesn't
  - modèle : No, he does not have a problem with the hypothesis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS hypothèse — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : alternative hypothesis ; a problem with the alternative hypothesis
  - modèle : He has a problem with the alternative hypothesis.
- **Tt2.** Does the patient in bed 9 have null hypothesis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the null hypothesis ; he doesn't
  - modèle : No, he does not have a problem with the null hypothesis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS hypothèse nulle (h₀).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … hypothesis and null hypothesis. (have)` → have · modèle : The patients have hypothesis and null hypothesis.
- `The patient in bed 5 … not have hypothesis. (do)` → does · modèle : The patient in bed 5 does not have hypothesis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the hypothesis. The patient in bed 5 has a problem with the null hypothesis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Hypothèse alternative (H₁ ou Hₐ), le lit 9 a Hypothèse et n'a pas Hypothèse nulle (H₀).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the alternative hypothesis. The patient in bed 9 has a problem with the hypothesis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
