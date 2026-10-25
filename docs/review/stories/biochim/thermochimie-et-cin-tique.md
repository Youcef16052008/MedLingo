# Relecture — Thermochimie et Cinétique (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-022` — Le cas de Omar : Réaction exothermique anormal(e) / _Omar: the exothermic reaction is not normal_

**Objectif :** Comprendre des résultats : Thermochimie normal(e), Réaction exothermique anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The thermochemistry is normal.”
   - FR : Le médecin dit : « Thermochimie est normal(e). »
   - AR : يقول الطبيب: «الكيمياء الحرارية طبيعي/طبيعية.»
5. **EN** “But the exothermic reaction is not normal.”
   - FR : « Mais Réaction exothermique n'est pas normal(e). »
   - AR : «لكن تفاعل طارد للحرارة غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the exothermic reaction normal?”
   - FR : Lina demande : « Réaction exothermique est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل تفاعل طارد للحرارة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:thermochemistry:v1` — **a thermochemistry** · FR : Thermochimie · AR : الكيمياء الحرارية
- `gen:enthalpy:v1` — **an enthalpy** · FR : Enthalpie (H) · AR : المحتوى الحراري
- `gen:exothermic-reaction:v1` — **an exothermic reaction** · FR : Réaction exothermique · AR : تفاعل طارد للحرارة

### Questions (acceptées / modèle / hint)
- **q1.** Is the thermochemistry normal?
  - accepté : yes ; yes it is ; the thermochemistry is normal ; it is normal
  - modèle : Yes, the thermochemistry is normal.
- **q2.** Is the exothermic reaction normal?
  - accepté : no ; no it is not ; no it isn't ; the exothermic reaction is not normal ; it is not normal ; it isn't normal
  - modèle : No, the exothermic reaction is not normal.
  - hint (si erreur) — FR : Attention : Réaction exothermique n'est PAS normal(e).
- **q3.** Which one is not normal: the thermochemistry or the exothermic reaction?
  - accepté : exothermic reaction ; the exothermic reaction
  - modèle : The exothermic reaction is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the enthalpy normal?
  - accepté : yes ; yes it is ; the enthalpy is normal
  - modèle : Yes, the enthalpy is normal.
- **Tt2.** Is the thermochemistry normal?
  - accepté : no ; no it is not ; no it isn't ; the thermochemistry is not normal ; it is not normal
  - modèle : No, the thermochemistry is not normal.
  - hint (si erreur) — FR : Attention : Thermochimie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The thermochemistry … normal. (be)` → is · modèle : The thermochemistry is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The thermochemistry is… / The exothermic reaction is not… — modèle : *The thermochemistry is normal. The exothermic reaction is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Enthalpie (H) normal(e), mais Thermochimie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The enthalpy is normal. The thermochemistry is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-022` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Thermochimie et Enthalpie (H) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient.
   - FR : Karim est un·e patient·e.
   - AR : Karim مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check thermochemistry.”
   - FR : « D'abord, nous vérifions Thermochimie. »
   - AR : «أولًا، نتحقق من الكيمياء الحرارية.»
5. **EN** “Then, we check enthalpy.”
   - FR : « Ensuite, nous vérifions Enthalpie (H). »
   - AR : «ثم نتحقق من المحتوى الحراري.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:thermochemistry:v1` — **a thermochemistry** · FR : Thermochimie · AR : الكيمياء الحرارية
- `gen:enthalpy:v1` — **an enthalpy** · FR : Enthalpie (H) · AR : المحتوى الحراري
- `gen:exothermic-reaction:v1` — **an exothermic reaction** · FR : Réaction exothermique · AR : تفاعل طارد للحرارة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : thermochemistry ; we check thermochemistry ; thermochemistry first ; the doctor checks thermochemistry first
  - modèle : First, the doctor checks thermochemistry.
- **q2.** What does the doctor check next?
  - accepté : enthalpy ; then we check enthalpy ; enthalpy next ; the doctor checks enthalpy next
  - modèle : Then, the doctor checks enthalpy.
- **q3.** Does the doctor check enthalpy before thermochemistry?
  - accepté : no ; no he does not ; no he doesn't ; he checks thermochemistry first
  - modèle : No, he checks thermochemistry first, then enthalpy.
  - hint (si erreur) — FR : Attention : Thermochimie vient D'ABORD, ensuite Enthalpie (H).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : exothermic reaction ; exothermic reaction first ; he checks exothermic reaction first
  - modèle : First, the doctor checks exothermic reaction.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check thermochemistry. Then, we check enthalpy.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Réaction exothermique, ensuite Thermochimie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check exothermic reaction. Then, we check thermochemistry.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-022` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Thermochimie / Enthalpie (H), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the thermochemistry.
   - FR : Le patient du lit 3 a Thermochimie.
   - AR : مريض السرير ٣ عنده الكيمياء الحرارية.
4. **EN** The patient in bed 5 has a problem with the enthalpy.
   - FR : Le patient du lit 5 a Enthalpie (H).
   - AR : مريض السرير ٥ عنده المحتوى الحراري.
5. **EN** The patient in bed 5 does not have a problem with the thermochemistry.
   - FR : Le patient du lit 5 n'a pas de Thermochimie.
   - AR : مريض السرير ٥ ليس عنده الكيمياء الحرارية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:thermochemistry:v1` — **a thermochemistry** · FR : Thermochimie · AR : الكيمياء الحرارية
- `gen:enthalpy:v1` — **an enthalpy** · FR : Enthalpie (H) · AR : المحتوى الحراري
- `gen:exothermic-reaction:v1` — **an exothermic reaction** · FR : Réaction exothermique · AR : تفاعل طارد للحرارة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : thermochemistry ; a thermochemistry ; the patient with thermochemistry
  - modèle : The patient in bed 3 has a thermochemistry.
- **q2.** What does the patient in bed 5 have?
  - accepté : enthalpy ; a problem with the enthalpy
  - modèle : He has a problem with the enthalpy.
- **q3.** Does the patient in bed 5 have thermochemistry?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the thermochemistry ; he doesn't
  - modèle : No, he does not have a problem with the thermochemistry.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS thermochimie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : exothermic reaction ; a problem with the exothermic reaction
  - modèle : He has a problem with the exothermic reaction.
- **Tt2.** Does the patient in bed 9 have enthalpy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the enthalpy ; he doesn't
  - modèle : No, he does not have a problem with the enthalpy.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS enthalpie (h).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a thermochemistry and an enthalpy. (have)` → have · modèle : The patients have a thermochemistry and an enthalpy.
- `The patient in bed 5 … not have a thermochemistry. (do)` → does · modèle : The patient in bed 5 does not have a thermochemistry.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the thermochemistry. The patient in bed 5 has a problem with the enthalpy.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Réaction exothermique, le lit 9 a Thermochimie et n'a pas Enthalpie (H).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the exothermic reaction. The patient in bed 9 has a problem with the thermochemistry.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
