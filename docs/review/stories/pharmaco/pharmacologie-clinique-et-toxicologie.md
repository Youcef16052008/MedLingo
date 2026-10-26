# Relecture — Pharmacologie Clinique et Toxicologie (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-012` — Le cas de Karim : Toxicité médicamenteuse anormal(e) / _Karim: the drug toxicity is not normal_

**Objectif :** Comprendre des résultats : Effet indésirable médicamenteux (EIM) normal(e), Toxicité médicamenteuse anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient at the clinic.
   - FR : Karim est un·e patient·e à la clinique.
   - AR : Karim مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The adverse drug reaction is normal.”
   - FR : Le médecin dit : « Effet indésirable médicamenteux (EIM) est normal(e). »
   - AR : يقول الطبيب: «التأثير الدوائي الضار طبيعي/طبيعية.»
5. **EN** “But the drug toxicity is not normal.”
   - FR : « Mais Toxicité médicamenteuse n'est pas normal(e). »
   - AR : «لكن سمية الدواء غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the drug toxicity normal?”
   - FR : Lina demande : « Toxicité médicamenteuse est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل سمية الدواء طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:adverse-drug-reaction:v1` — **an adverse drug reaction** · FR : Effet indésirable médicamenteux (EIM) · AR : التأثير الدوائي الضار
- `gen:side-effect:v1` — **a side effect** · FR : Effet secondaire · AR : التأثير الجانبي
- `gen:drug-toxicity:v1` — **a drug toxicity** · FR : Toxicité médicamenteuse · AR : سمية الدواء

### Questions (acceptées / modèle / hint)
- **q1.** Is the adverse drug reaction normal?
  - accepté : yes ; yes it is ; the adverse drug reaction is normal ; it is normal
  - modèle : Yes, the adverse drug reaction is normal.
- **q2.** Is the drug toxicity normal?
  - accepté : no ; no it is not ; no it isn't ; the drug toxicity is not normal ; it is not normal ; it isn't normal
  - modèle : No, the drug toxicity is not normal.
  - hint (si erreur) — FR : Attention : Toxicité médicamenteuse n'est PAS normal(e).
- **q3.** Which one is not normal: the adverse drug reaction or the drug toxicity?
  - accepté : drug toxicity ; the drug toxicity
  - modèle : The drug toxicity is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the side effect normal?
  - accepté : yes ; yes it is ; the side effect is normal
  - modèle : Yes, the side effect is normal.
- **Tt2.** Is the adverse drug reaction normal?
  - accepté : no ; no it is not ; no it isn't ; the adverse drug reaction is not normal ; it is not normal
  - modèle : No, the adverse drug reaction is not normal.
  - hint (si erreur) — FR : Attention : Effet indésirable médicamenteux (EIM) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The adverse drug reaction … normal. (be)` → is · modèle : The adverse drug reaction is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The adverse drug reaction is… / The drug toxicity is not… — modèle : *The adverse drug reaction is normal. The drug toxicity is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Effet secondaire normal(e), mais Effet indésirable médicamenteux (EIM) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The side effect is normal. The adverse drug reaction is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-012` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Effet indésirable médicamenteux (EIM) et Effet secondaire à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient.
   - FR : Youssef est un·e patient·e.
   - AR : Youssef مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check adverse drug reaction.”
   - FR : « D'abord, nous vérifions Effet indésirable médicamenteux (EIM). »
   - AR : «أولًا، نتحقق من التأثير الدوائي الضار.»
5. **EN** “Then, we check side effect.”
   - FR : « Ensuite, nous vérifions Effet secondaire. »
   - AR : «ثم نتحقق من التأثير الجانبي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:adverse-drug-reaction:v1` — **an adverse drug reaction** · FR : Effet indésirable médicamenteux (EIM) · AR : التأثير الدوائي الضار
- `gen:side-effect:v1` — **a side effect** · FR : Effet secondaire · AR : التأثير الجانبي
- `gen:drug-toxicity:v1` — **a drug toxicity** · FR : Toxicité médicamenteuse · AR : سمية الدواء

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : adverse drug reaction ; we check adverse drug reaction ; adverse drug reaction first ; the doctor checks adverse drug reaction first
  - modèle : First, the doctor checks adverse drug reaction.
- **q2.** What does the doctor check next?
  - accepté : side effect ; then we check side effect ; side effect next ; the doctor checks side effect next
  - modèle : Then, the doctor checks side effect.
- **q3.** Does the doctor check side effect before adverse drug reaction?
  - accepté : no ; no he does not ; no he doesn't ; he checks adverse drug reaction first
  - modèle : No, he checks adverse drug reaction first, then side effect.
  - hint (si erreur) — FR : Attention : Effet indésirable médicamenteux (EIM) vient D'ABORD, ensuite Effet secondaire.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : drug toxicity ; drug toxicity first ; he checks drug toxicity first
  - modèle : First, the doctor checks drug toxicity.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check adverse drug reaction. Then, we check side effect.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Toxicité médicamenteuse, ensuite Effet indésirable médicamenteux (EIM), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check drug toxicity. Then, we check adverse drug reaction.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-012` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Effet indésirable médicamenteux (EIM) / Effet secondaire, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the adverse drug reaction.
   - FR : Le patient du lit 3 a Effet indésirable médicamenteux (EIM).
   - AR : مريض السرير ٣ عنده التأثير الدوائي الضار.
4. **EN** The patient in bed 5 has a problem with the side effect.
   - FR : Le patient du lit 5 a Effet secondaire.
   - AR : مريض السرير ٥ عنده التأثير الجانبي.
5. **EN** The patient in bed 5 does not have a problem with the adverse drug reaction.
   - FR : Le patient du lit 5 n'a pas de Effet indésirable médicamenteux (EIM).
   - AR : مريض السرير ٥ ليس عنده التأثير الدوائي الضار.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:adverse-drug-reaction:v1` — **an adverse drug reaction** · FR : Effet indésirable médicamenteux (EIM) · AR : التأثير الدوائي الضار
- `gen:side-effect:v1` — **a side effect** · FR : Effet secondaire · AR : التأثير الجانبي
- `gen:drug-toxicity:v1` — **a drug toxicity** · FR : Toxicité médicamenteuse · AR : سمية الدواء

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : adverse drug reaction ; an adverse drug reaction ; the patient with adverse drug reaction
  - modèle : The patient in bed 3 has an adverse drug reaction.
- **q2.** What does the patient in bed 5 have?
  - accepté : side effect ; a problem with the side effect
  - modèle : He has a problem with the side effect.
- **q3.** Does the patient in bed 5 have adverse drug reaction?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the adverse drug reaction ; he doesn't
  - modèle : No, he does not have a problem with the adverse drug reaction.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS effet indésirable médicamenteux (eim) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : drug toxicity ; a problem with the drug toxicity
  - modèle : He has a problem with the drug toxicity.
- **Tt2.** Does the patient in bed 9 have side effect?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the side effect ; he doesn't
  - modèle : No, he does not have a problem with the side effect.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS effet secondaire.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an adverse drug reaction and a side effect. (have)` → have · modèle : The patients have an adverse drug reaction and a side effect.
- `The patient in bed 5 … not have an adverse drug reaction. (do)` → does · modèle : The patient in bed 5 does not have an adverse drug reaction.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the adverse drug reaction. The patient in bed 5 has a problem with the side effect.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Toxicité médicamenteuse, le lit 9 a Effet indésirable médicamenteux (EIM) et n'a pas Effet secondaire.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the drug toxicity. The patient in bed 9 has a problem with the adverse drug reaction.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
