# Relecture — Pharmacodynamie (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-011` — Le cas de Leila : Agoniste partiel anormal(e) / _Leila: the partial agonist is not normal_

**Objectif :** Comprendre des résultats : Agoniste normal(e), Agoniste partiel anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The agonist is normal.”
   - FR : Le médecin dit : « Agoniste est normal(e). »
   - AR : يقول الطبيب: «الناهض طبيعي/طبيعية.»
5. **EN** “But the partial agonist is not normal.”
   - FR : « Mais Agoniste partiel n'est pas normal(e). »
   - AR : «لكن الناهض الجزئي غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the partial agonist normal?”
   - FR : Amine demande : « Agoniste partiel est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الناهض الجزئي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:agonist:v1` — **an agonist** · FR : Agoniste · AR : الناهض
- `gen:antagonist:v1` — **an antagonist** · FR : Antagoniste · AR : المضاد (المناهض)
- `gen:partial-agonist:v1` — **a partial agonist** · FR : Agoniste partiel · AR : الناهض الجزئي

### Questions (acceptées / modèle / hint)
- **q1.** Is the agonist normal?
  - accepté : yes ; yes it is ; the agonist is normal ; it is normal
  - modèle : Yes, the agonist is normal.
- **q2.** Is the partial agonist normal?
  - accepté : no ; no it is not ; no it isn't ; the partial agonist is not normal ; it is not normal ; it isn't normal
  - modèle : No, the partial agonist is not normal.
  - hint (si erreur) — FR : Attention : Agoniste partiel n'est PAS normal(e).
- **q3.** Which one is not normal: the agonist or the partial agonist?
  - accepté : partial agonist ; the partial agonist
  - modèle : The partial agonist is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the antagonist normal?
  - accepté : yes ; yes it is ; the antagonist is normal
  - modèle : Yes, the antagonist is normal.
- **Tt2.** Is the agonist normal?
  - accepté : no ; no it is not ; no it isn't ; the agonist is not normal ; it is not normal
  - modèle : No, the agonist is not normal.
  - hint (si erreur) — FR : Attention : Agoniste n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The agonist … normal. (be)` → is · modèle : The agonist is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Leila en deux phrases. Garde la négation. — amorces : The agonist is… / The partial agonist is not… — modèle : *The agonist is normal. The partial agonist is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Antagoniste normal(e), mais Agoniste anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The antagonist is normal. The agonist is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-011` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Agoniste et Antagoniste à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check agonist.”
   - FR : « D'abord, nous vérifions Agoniste. »
   - AR : «أولًا، نتحقق من الناهض.»
5. **EN** “Then, we check antagonist.”
   - FR : « Ensuite, nous vérifions Antagoniste. »
   - AR : «ثم نتحقق من المضاد (المناهض).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:agonist:v1` — **an agonist** · FR : Agoniste · AR : الناهض
- `gen:antagonist:v1` — **an antagonist** · FR : Antagoniste · AR : المضاد (المناهض)
- `gen:partial-agonist:v1` — **a partial agonist** · FR : Agoniste partiel · AR : الناهض الجزئي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : agonist ; we check agonist ; agonist first ; the doctor checks agonist first
  - modèle : First, the doctor checks agonist.
- **q2.** What does the doctor check next?
  - accepté : antagonist ; then we check antagonist ; antagonist next ; the doctor checks antagonist next
  - modèle : Then, the doctor checks antagonist.
- **q3.** Does the doctor check antagonist before agonist?
  - accepté : no ; no he does not ; no he doesn't ; he checks agonist first
  - modèle : No, he checks agonist first, then antagonist.
  - hint (si erreur) — FR : Attention : Agoniste vient D'ABORD, ensuite Antagoniste.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : partial agonist ; partial agonist first ; he checks partial agonist first
  - modèle : First, the doctor checks partial agonist.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check agonist. Then, we check antagonist.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Agoniste partiel, ensuite Agoniste, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check partial agonist. Then, we check agonist.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-011` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Agoniste / Antagoniste, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the agonist.
   - FR : Le patient du lit 3 a Agoniste.
   - AR : مريض السرير ٣ عنده الناهض.
4. **EN** The patient in bed 5 has a problem with the antagonist.
   - FR : Le patient du lit 5 a Antagoniste.
   - AR : مريض السرير ٥ عنده المضاد (المناهض).
5. **EN** The patient in bed 5 does not have a problem with the agonist.
   - FR : Le patient du lit 5 n'a pas de Agoniste.
   - AR : مريض السرير ٥ ليس عنده الناهض.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:agonist:v1` — **an agonist** · FR : Agoniste · AR : الناهض
- `gen:antagonist:v1` — **an antagonist** · FR : Antagoniste · AR : المضاد (المناهض)
- `gen:partial-agonist:v1` — **a partial agonist** · FR : Agoniste partiel · AR : الناهض الجزئي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : agonist ; an agonist ; the patient with agonist
  - modèle : The patient in bed 3 has an agonist.
- **q2.** What does the patient in bed 5 have?
  - accepté : antagonist ; a problem with the antagonist
  - modèle : He has a problem with the antagonist.
- **q3.** Does the patient in bed 5 have agonist?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the agonist ; he doesn't
  - modèle : No, he does not have a problem with the agonist.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS agoniste — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : partial agonist ; a problem with the partial agonist
  - modèle : He has a problem with the partial agonist.
- **Tt2.** Does the patient in bed 9 have antagonist?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the antagonist ; he doesn't
  - modèle : No, he does not have a problem with the antagonist.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS antagoniste.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an agonist and an antagonist. (have)` → have · modèle : The patients have an agonist and an antagonist.
- `The patient in bed 5 … not have an agonist. (do)` → does · modèle : The patient in bed 5 does not have an agonist.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the agonist. The patient in bed 5 has a problem with the antagonist.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Agoniste partiel, le lit 9 a Agoniste et n'a pas Antagoniste.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the partial agonist. The patient in bed 9 has a problem with the agonist.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
