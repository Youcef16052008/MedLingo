# Relecture — Reproductive & Sexual Health (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-033` — Le cas de Sara : Accouchement anormal(e) / _Sara: the childbirth is not normal_

**Objectif :** Comprendre des résultats : Grossesse normal(e), Accouchement anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The pregnancy is normal.”
   - FR : Le médecin dit : « Grossesse est normal(e). »
   - AR : يقول الطبيب: «الحمل طبيعي/طبيعية.»
5. **EN** “But the childbirth is not normal.”
   - FR : « Mais Accouchement n'est pas normal(e). »
   - AR : «لكن الولادة غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the childbirth normal?”
   - FR : Amine demande : « Accouchement est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الولادة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:pregnancy:v1` — **a pregnancy** · FR : Grossesse · AR : الحمل
- `gen:contraception:v1` — **a contraception** · FR : Contraception · AR : منع الحمل
- `gen:childbirth:v1` — **a childbirth** · FR : Accouchement · AR : الولادة

### Questions (acceptées / modèle / hint)
- **q1.** Is the pregnancy normal?
  - accepté : yes ; yes it is ; the pregnancy is normal ; it is normal
  - modèle : Yes, the pregnancy is normal.
- **q2.** Is the childbirth normal?
  - accepté : no ; no it is not ; no it isn't ; the childbirth is not normal ; it is not normal ; it isn't normal
  - modèle : No, the childbirth is not normal.
  - hint (si erreur) — FR : Attention : Accouchement n'est PAS normal(e).
- **q3.** Which one is not normal: the pregnancy or the childbirth?
  - accepté : childbirth ; the childbirth
  - modèle : The childbirth is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the contraception normal?
  - accepté : yes ; yes it is ; the contraception is normal
  - modèle : Yes, the contraception is normal.
- **Tt2.** Is the pregnancy normal?
  - accepté : no ; no it is not ; no it isn't ; the pregnancy is not normal ; it is not normal
  - modèle : No, the pregnancy is not normal.
  - hint (si erreur) — FR : Attention : Grossesse n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The pregnancy … normal. (be)` → is · modèle : The pregnancy is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The pregnancy is… / The childbirth is not… — modèle : *The pregnancy is normal. The childbirth is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Contraception normal(e), mais Grossesse anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The contraception is normal. The pregnancy is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-033` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Grossesse et Contraception à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check pregnancy.”
   - FR : « D'abord, nous vérifions Grossesse. »
   - AR : «أولًا، نتحقق من الحمل.»
5. **EN** “Then, we check contraception.”
   - FR : « Ensuite, nous vérifions Contraception. »
   - AR : «ثم نتحقق من منع الحمل.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:pregnancy:v1` — **a pregnancy** · FR : Grossesse · AR : الحمل
- `gen:contraception:v1` — **a contraception** · FR : Contraception · AR : منع الحمل
- `gen:childbirth:v1` — **a childbirth** · FR : Accouchement · AR : الولادة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : pregnancy ; we check pregnancy ; pregnancy first ; the doctor checks pregnancy first
  - modèle : First, the doctor checks pregnancy.
- **q2.** What does the doctor check next?
  - accepté : contraception ; then we check contraception ; contraception next ; the doctor checks contraception next
  - modèle : Then, the doctor checks contraception.
- **q3.** Does the doctor check contraception before pregnancy?
  - accepté : no ; no he does not ; no he doesn't ; he checks pregnancy first
  - modèle : No, he checks pregnancy first, then contraception.
  - hint (si erreur) — FR : Attention : Grossesse vient D'ABORD, ensuite Contraception.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : childbirth ; childbirth first ; he checks childbirth first
  - modèle : First, the doctor checks childbirth.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check pregnancy. Then, we check contraception.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Accouchement, ensuite Grossesse, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check childbirth. Then, we check pregnancy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-033` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Grossesse / Contraception, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the pregnancy.
   - FR : Le patient du lit 3 a Grossesse.
   - AR : مريض السرير ٣ عنده الحمل.
4. **EN** The patient in bed 5 has a problem with the contraception.
   - FR : Le patient du lit 5 a Contraception.
   - AR : مريض السرير ٥ عنده منع الحمل.
5. **EN** The patient in bed 5 does not have a problem with the pregnancy.
   - FR : Le patient du lit 5 n'a pas de Grossesse.
   - AR : مريض السرير ٥ ليس عنده الحمل.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:pregnancy:v1` — **a pregnancy** · FR : Grossesse · AR : الحمل
- `gen:contraception:v1` — **a contraception** · FR : Contraception · AR : منع الحمل
- `gen:childbirth:v1` — **a childbirth** · FR : Accouchement · AR : الولادة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : pregnancy ; a pregnancy ; the patient with pregnancy
  - modèle : The patient in bed 3 has a pregnancy.
- **q2.** What does the patient in bed 5 have?
  - accepté : contraception ; a problem with the contraception
  - modèle : He has a problem with the contraception.
- **q3.** Does the patient in bed 5 have pregnancy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the pregnancy ; he doesn't
  - modèle : No, he does not have a problem with the pregnancy.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS grossesse — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : childbirth ; a problem with the childbirth
  - modèle : He has a problem with the childbirth.
- **Tt2.** Does the patient in bed 9 have contraception?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the contraception ; he doesn't
  - modèle : No, he does not have a problem with the contraception.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS contraception.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a pregnancy and a contraception. (have)` → have · modèle : The patients have a pregnancy and a contraception.
- `The patient in bed 5 … not have a pregnancy. (do)` → does · modèle : The patient in bed 5 does not have a pregnancy.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the pregnancy. The patient in bed 5 has a problem with the contraception.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Accouchement, le lit 9 a Grossesse et n'a pas Contraception.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the childbirth. The patient in bed 9 has a problem with the pregnancy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
