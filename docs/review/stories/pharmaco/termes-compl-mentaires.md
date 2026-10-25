# Relecture — Termes Complémentaires (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-014` — Le cas de Youssef : Réaction idiosyncrasique anormal(e) / _Youssef: the idiosyncratic reaction is not normal_

**Objectif :** Comprendre des résultats : Pharmacovigilance normal(e), Réaction idiosyncrasique anormal(e).
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
4. **EN** The doctor says, “The pharmacovigilance is normal.”
   - FR : Le médecin dit : « Pharmacovigilance est normal(e). »
   - AR : يقول الطبيب: «مراقبة سلامة الأدوية طبيعي/طبيعية.»
5. **EN** “But the idiosyncratic reaction is not normal.”
   - FR : « Mais Réaction idiosyncrasique n'est pas normal(e). »
   - AR : «لكن التفاعل الشاذ (الخاص بالفرد) غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the idiosyncratic reaction normal?”
   - FR : Lina demande : « Réaction idiosyncrasique est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل التفاعل الشاذ (الخاص بالفرد) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:pharmacovigilance:v1` — **a pharmacovigilance** · FR : Pharmacovigilance · AR : مراقبة سلامة الأدوية
- `gen:drug-absorption-enhancer:v1` — **a drug absorption enhancer** · FR : Promoteur d'absorption · AR : مُعزِّز امتصاص الدواء
- `gen:idiosyncratic-reaction:v1` — **an idiosyncratic reaction** · FR : Réaction idiosyncrasique · AR : التفاعل الشاذ (الخاص بالفرد)

### Questions (acceptées / modèle / hint)
- **q1.** Is the pharmacovigilance normal?
  - accepté : yes ; yes it is ; the pharmacovigilance is normal ; it is normal
  - modèle : Yes, the pharmacovigilance is normal.
- **q2.** Is the idiosyncratic reaction normal?
  - accepté : no ; no it is not ; no it isn't ; the idiosyncratic reaction is not normal ; it is not normal ; it isn't normal
  - modèle : No, the idiosyncratic reaction is not normal.
  - hint (si erreur) — FR : Attention : Réaction idiosyncrasique n'est PAS normal(e).
- **q3.** Which one is not normal: the pharmacovigilance or the idiosyncratic reaction?
  - accepté : idiosyncratic reaction ; the idiosyncratic reaction
  - modèle : The idiosyncratic reaction is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the drug absorption enhancer normal?
  - accepté : yes ; yes it is ; the drug absorption enhancer is normal
  - modèle : Yes, the drug absorption enhancer is normal.
- **Tt2.** Is the pharmacovigilance normal?
  - accepté : no ; no it is not ; no it isn't ; the pharmacovigilance is not normal ; it is not normal
  - modèle : No, the pharmacovigilance is not normal.
  - hint (si erreur) — FR : Attention : Pharmacovigilance n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The pharmacovigilance … normal. (be)` → is · modèle : The pharmacovigilance is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The pharmacovigilance is… / The idiosyncratic reaction is not… — modèle : *The pharmacovigilance is normal. The idiosyncratic reaction is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Promoteur d'absorption normal(e), mais Pharmacovigilance anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The drug absorption enhancer is normal. The pharmacovigilance is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-014` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Pharmacovigilance et Promoteur d'absorption à un patient.
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
4. **EN** “First, we check pharmacovigilance.”
   - FR : « D'abord, nous vérifions Pharmacovigilance. »
   - AR : «أولًا، نتحقق من مراقبة سلامة الأدوية.»
5. **EN** “Then, we check drug absorption enhancer.”
   - FR : « Ensuite, nous vérifions Promoteur d'absorption. »
   - AR : «ثم نتحقق من مُعزِّز امتصاص الدواء.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:pharmacovigilance:v1` — **a pharmacovigilance** · FR : Pharmacovigilance · AR : مراقبة سلامة الأدوية
- `gen:drug-absorption-enhancer:v1` — **a drug absorption enhancer** · FR : Promoteur d'absorption · AR : مُعزِّز امتصاص الدواء
- `gen:idiosyncratic-reaction:v1` — **an idiosyncratic reaction** · FR : Réaction idiosyncrasique · AR : التفاعل الشاذ (الخاص بالفرد)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : pharmacovigilance ; we check pharmacovigilance ; pharmacovigilance first ; the doctor checks pharmacovigilance first
  - modèle : First, the doctor checks pharmacovigilance.
- **q2.** What does the doctor check next?
  - accepté : drug absorption enhancer ; then we check drug absorption enhancer ; drug absorption enhancer next ; the doctor checks drug absorption enhancer next
  - modèle : Then, the doctor checks drug absorption enhancer.
- **q3.** Does the doctor check drug absorption enhancer before pharmacovigilance?
  - accepté : no ; no he does not ; no he doesn't ; he checks pharmacovigilance first
  - modèle : No, he checks pharmacovigilance first, then drug absorption enhancer.
  - hint (si erreur) — FR : Attention : Pharmacovigilance vient D'ABORD, ensuite Promoteur d'absorption.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : idiosyncratic reaction ; idiosyncratic reaction first ; he checks idiosyncratic reaction first
  - modèle : First, the doctor checks idiosyncratic reaction.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check pharmacovigilance. Then, we check drug absorption enhancer.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Réaction idiosyncrasique, ensuite Pharmacovigilance, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check idiosyncratic reaction. Then, we check pharmacovigilance.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-014` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Pharmacovigilance / Promoteur d'absorption, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the pharmacovigilance.
   - FR : Le patient du lit 3 a Pharmacovigilance.
   - AR : مريض السرير ٣ عنده مراقبة سلامة الأدوية.
4. **EN** The patient in bed 5 has a problem with the drug absorption enhancer.
   - FR : Le patient du lit 5 a Promoteur d'absorption.
   - AR : مريض السرير ٥ عنده مُعزِّز امتصاص الدواء.
5. **EN** The patient in bed 5 does not have a problem with the pharmacovigilance.
   - FR : Le patient du lit 5 n'a pas de Pharmacovigilance.
   - AR : مريض السرير ٥ ليس عنده مراقبة سلامة الأدوية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:pharmacovigilance:v1` — **a pharmacovigilance** · FR : Pharmacovigilance · AR : مراقبة سلامة الأدوية
- `gen:drug-absorption-enhancer:v1` — **a drug absorption enhancer** · FR : Promoteur d'absorption · AR : مُعزِّز امتصاص الدواء
- `gen:idiosyncratic-reaction:v1` — **an idiosyncratic reaction** · FR : Réaction idiosyncrasique · AR : التفاعل الشاذ (الخاص بالفرد)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : pharmacovigilance ; a pharmacovigilance ; the patient with pharmacovigilance
  - modèle : The patient in bed 3 has a pharmacovigilance.
- **q2.** What does the patient in bed 5 have?
  - accepté : drug absorption enhancer ; a problem with the drug absorption enhancer
  - modèle : He has a problem with the drug absorption enhancer.
- **q3.** Does the patient in bed 5 have pharmacovigilance?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the pharmacovigilance ; he doesn't
  - modèle : No, he does not have a problem with the pharmacovigilance.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS pharmacovigilance — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : idiosyncratic reaction ; a problem with the idiosyncratic reaction
  - modèle : He has a problem with the idiosyncratic reaction.
- **Tt2.** Does the patient in bed 9 have drug absorption enhancer?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the drug absorption enhancer ; he doesn't
  - modèle : No, he does not have a problem with the drug absorption enhancer.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS promoteur d'absorption.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a pharmacovigilance and a drug absorption enhancer. (have)` → have · modèle : The patients have a pharmacovigilance and a drug absorption enhancer.
- `The patient in bed 5 … not have a pharmacovigilance. (do)` → does · modèle : The patient in bed 5 does not have a pharmacovigilance.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the pharmacovigilance. The patient in bed 5 has a problem with the drug absorption enhancer.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Réaction idiosyncrasique, le lit 9 a Pharmacovigilance et n'a pas Promoteur d'absorption.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the idiosyncratic reaction. The patient in bed 9 has a problem with the pharmacovigilance.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
