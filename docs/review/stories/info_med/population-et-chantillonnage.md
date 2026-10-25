# Relecture — Population et Échantillonnage (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-007` — Le cas de Hana : Taille de l'échantillon (n) anormal(e) / _Hana: the sample size is not normal_

**Objectif :** Comprendre des résultats : Population normal(e), Taille de l'échantillon (n) anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient at the clinic.
   - FR : Hana est un·e patient·e à la clinique.
   - AR : Hana مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The population is normal.”
   - FR : Le médecin dit : « Population est normal(e). »
   - AR : يقول الطبيب: «المجتمع (الإحصائي) طبيعي/طبيعية.»
5. **EN** “But the sample size is not normal.”
   - FR : « Mais Taille de l'échantillon (n) n'est pas normal(e). »
   - AR : «لكن حجم العينة غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the sample size normal?”
   - FR : Amine demande : « Taille de l'échantillon (n) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل حجم العينة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:population:v1` — **a population** · FR : Population · AR : المجتمع (الإحصائي)
- `gen:sample:v1` — **a sample** · FR : Échantillon · AR : العينة
- `gen:sample-size:v1` — **a sample size** · FR : Taille de l'échantillon (n) · AR : حجم العينة

### Questions (acceptées / modèle / hint)
- **q1.** Is the population normal?
  - accepté : yes ; yes it is ; the population is normal ; it is normal
  - modèle : Yes, the population is normal.
- **q2.** Is the sample size normal?
  - accepté : no ; no it is not ; no it isn't ; the sample size is not normal ; it is not normal ; it isn't normal
  - modèle : No, the sample size is not normal.
  - hint (si erreur) — FR : Attention : Taille de l'échantillon (n) n'est PAS normal(e).
- **q3.** Which one is not normal: the population or the sample size?
  - accepté : sample size ; the sample size
  - modèle : The sample size is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the sample normal?
  - accepté : yes ; yes it is ; the sample is normal
  - modèle : Yes, the sample is normal.
- **Tt2.** Is the population normal?
  - accepté : no ; no it is not ; no it isn't ; the population is not normal ; it is not normal
  - modèle : No, the population is not normal.
  - hint (si erreur) — FR : Attention : Population n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The population … normal. (be)` → is · modèle : The population is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Hana en deux phrases. Garde la négation. — amorces : The population is… / The sample size is not… — modèle : *The population is normal. The sample size is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Échantillon normal(e), mais Population anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The sample is normal. The population is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-007` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Population et Échantillon à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient.
   - FR : Sara est un·e patient·e.
   - AR : Sara مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check population.”
   - FR : « D'abord, nous vérifions Population. »
   - AR : «أولًا، نتحقق من المجتمع (الإحصائي).»
5. **EN** “Then, we check sample.”
   - FR : « Ensuite, nous vérifions Échantillon. »
   - AR : «ثم نتحقق من العينة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:population:v1` — **a population** · FR : Population · AR : المجتمع (الإحصائي)
- `gen:sample:v1` — **a sample** · FR : Échantillon · AR : العينة
- `gen:sample-size:v1` — **a sample size** · FR : Taille de l'échantillon (n) · AR : حجم العينة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : population ; we check population ; population first ; the doctor checks population first
  - modèle : First, the doctor checks population.
- **q2.** What does the doctor check next?
  - accepté : sample ; then we check sample ; sample next ; the doctor checks sample next
  - modèle : Then, the doctor checks sample.
- **q3.** Does the doctor check sample before population?
  - accepté : no ; no he does not ; no he doesn't ; he checks population first
  - modèle : No, he checks population first, then sample.
  - hint (si erreur) — FR : Attention : Population vient D'ABORD, ensuite Échantillon.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : sample size ; sample size first ; he checks sample size first
  - modèle : First, the doctor checks sample size.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check population. Then, we check sample.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Taille de l'échantillon (n), ensuite Population, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check sample size. Then, we check population.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-007` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Population / Échantillon, lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the population.
   - FR : Le patient du lit 3 a Population.
   - AR : مريض السرير ٣ عنده المجتمع (الإحصائي).
4. **EN** The patient in bed 5 has a problem with the sample.
   - FR : Le patient du lit 5 a Échantillon.
   - AR : مريض السرير ٥ عنده العينة.
5. **EN** The patient in bed 5 does not have a problem with the population.
   - FR : Le patient du lit 5 n'a pas de Population.
   - AR : مريض السرير ٥ ليس عنده المجتمع (الإحصائي).
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:population:v1` — **a population** · FR : Population · AR : المجتمع (الإحصائي)
- `gen:sample:v1` — **a sample** · FR : Échantillon · AR : العينة
- `gen:sample-size:v1` — **a sample size** · FR : Taille de l'échantillon (n) · AR : حجم العينة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : population ; a population ; the patient with population
  - modèle : The patient in bed 3 has a population.
- **q2.** What does the patient in bed 5 have?
  - accepté : sample ; a problem with the sample
  - modèle : He has a problem with the sample.
- **q3.** Does the patient in bed 5 have population?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the population ; he doesn't
  - modèle : No, he does not have a problem with the population.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS population — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : sample size ; a problem with the sample size
  - modèle : He has a problem with the sample size.
- **Tt2.** Does the patient in bed 9 have sample?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the sample ; he doesn't
  - modèle : No, he does not have a problem with the sample.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS échantillon.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a population and a sample. (have)` → have · modèle : The patients have a population and a sample.
- `The patient in bed 5 … not have a population. (do)` → does · modèle : The patient in bed 5 does not have a population.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the population. The patient in bed 5 has a problem with the sample.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Taille de l'échantillon (n), le lit 9 a Population et n'a pas Échantillon.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the sample size. The patient in bed 9 has a problem with the population.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
