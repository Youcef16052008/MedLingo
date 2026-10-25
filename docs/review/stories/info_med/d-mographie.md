# Relecture — Démographie (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-004` — Le cas de Farid : Taux brut de mortalité anormal(e) / _Farid: the death rate is not normal_

**Objectif :** Comprendre des résultats : Démographie normal(e), Taux brut de mortalité anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The demography is normal.”
   - FR : Le médecin dit : « Démographie est normal(e). »
   - AR : يقول الطبيب: «الديموغرافيا (علم السكان) طبيعي/طبيعية.»
5. **EN** “But the death rate is not normal.”
   - FR : « Mais Taux brut de mortalité n'est pas normal(e). »
   - AR : «لكن معدل الوفيات الخام غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the death rate normal?”
   - FR : Lina demande : « Taux brut de mortalité est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل معدل الوفيات الخام طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:demography:v1` — **a demography** · FR : Démographie · AR : الديموغرافيا (علم السكان)
- `gen:birth-rate:v1` — **a birth rate** · FR : Taux de natalité · AR : معدل المواليد
- `gen:death-rate:v1` — **a death rate** · FR : Taux brut de mortalité · AR : معدل الوفيات الخام

### Questions (acceptées / modèle / hint)
- **q1.** Is the demography normal?
  - accepté : yes ; yes it is ; the demography is normal ; it is normal
  - modèle : Yes, the demography is normal.
- **q2.** Is the death rate normal?
  - accepté : no ; no it is not ; no it isn't ; the death rate is not normal ; it is not normal ; it isn't normal
  - modèle : No, the death rate is not normal.
  - hint (si erreur) — FR : Attention : Taux brut de mortalité n'est PAS normal(e).
- **q3.** Which one is not normal: the demography or the death rate?
  - accepté : death rate ; the death rate
  - modèle : The death rate is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the birth rate normal?
  - accepté : yes ; yes it is ; the birth rate is normal
  - modèle : Yes, the birth rate is normal.
- **Tt2.** Is the demography normal?
  - accepté : no ; no it is not ; no it isn't ; the demography is not normal ; it is not normal
  - modèle : No, the demography is not normal.
  - hint (si erreur) — FR : Attention : Démographie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The demography … normal. (be)` → is · modèle : The demography is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The demography is… / The death rate is not… — modèle : *The demography is normal. The death rate is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Taux de natalité normal(e), mais Démographie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The birth rate is normal. The demography is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Démographie et Taux de natalité à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check demography.”
   - FR : « D'abord, nous vérifions Démographie. »
   - AR : «أولًا، نتحقق من الديموغرافيا (علم السكان).»
5. **EN** “Then, we check birth rate.”
   - FR : « Ensuite, nous vérifions Taux de natalité. »
   - AR : «ثم نتحقق من معدل المواليد.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:demography:v1` — **a demography** · FR : Démographie · AR : الديموغرافيا (علم السكان)
- `gen:birth-rate:v1` — **a birth rate** · FR : Taux de natalité · AR : معدل المواليد
- `gen:death-rate:v1` — **a death rate** · FR : Taux brut de mortalité · AR : معدل الوفيات الخام

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : demography ; we check demography ; demography first ; the doctor checks demography first
  - modèle : First, the doctor checks demography.
- **q2.** What does the doctor check next?
  - accepté : birth rate ; then we check birth rate ; birth rate next ; the doctor checks birth rate next
  - modèle : Then, the doctor checks birth rate.
- **q3.** Does the doctor check birth rate before demography?
  - accepté : no ; no he does not ; no he doesn't ; he checks demography first
  - modèle : No, he checks demography first, then birth rate.
  - hint (si erreur) — FR : Attention : Démographie vient D'ABORD, ensuite Taux de natalité.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : death rate ; death rate first ; he checks death rate first
  - modèle : First, the doctor checks death rate.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check demography. Then, we check birth rate.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Taux brut de mortalité, ensuite Démographie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check death rate. Then, we check demography.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Démographie / Taux de natalité, lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the demography.
   - FR : Le patient du lit 3 a Démographie.
   - AR : مريض السرير ٣ عنده الديموغرافيا (علم السكان).
4. **EN** The patient in bed 5 has a problem with the birth rate.
   - FR : Le patient du lit 5 a Taux de natalité.
   - AR : مريض السرير ٥ عنده معدل المواليد.
5. **EN** The patient in bed 5 does not have a problem with the demography.
   - FR : Le patient du lit 5 n'a pas de Démographie.
   - AR : مريض السرير ٥ ليس عنده الديموغرافيا (علم السكان).
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:demography:v1` — **a demography** · FR : Démographie · AR : الديموغرافيا (علم السكان)
- `gen:birth-rate:v1` — **a birth rate** · FR : Taux de natalité · AR : معدل المواليد
- `gen:death-rate:v1` — **a death rate** · FR : Taux brut de mortalité · AR : معدل الوفيات الخام

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : demography ; a demography ; the patient with demography
  - modèle : The patient in bed 3 has a demography.
- **q2.** What does the patient in bed 5 have?
  - accepté : birth rate ; a problem with the birth rate
  - modèle : He has a problem with the birth rate.
- **q3.** Does the patient in bed 5 have demography?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the demography ; he doesn't
  - modèle : No, he does not have a problem with the demography.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS démographie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : death rate ; a problem with the death rate
  - modèle : He has a problem with the death rate.
- **Tt2.** Does the patient in bed 9 have birth rate?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the birth rate ; he doesn't
  - modèle : No, he does not have a problem with the birth rate.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS taux de natalité.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a demography and a birth rate. (have)` → have · modèle : The patients have a demography and a birth rate.
- `The patient in bed 5 … not have a demography. (do)` → does · modèle : The patient in bed 5 does not have a demography.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the demography. The patient in bed 5 has a problem with the birth rate.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Taux brut de mortalité, le lit 9 a Démographie et n'a pas Taux de natalité.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the death rate. The patient in bed 9 has a problem with the demography.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
