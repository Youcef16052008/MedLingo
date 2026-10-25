# Relecture — Groupes Fonctionnels (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-011` — Le cas de Leila : Méthanol (CH₃OH) anormal(e) / _Leila: the methanol is not normal_

**Objectif :** Comprendre des résultats : Groupe hydroxyle (-OH) normal(e), Méthanol (CH₃OH) anormal(e).
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
4. **EN** The doctor says, “The hydroxyl group is normal.”
   - FR : Le médecin dit : « Groupe hydroxyle (-OH) est normal(e). »
   - AR : يقول الطبيب: «مجموعة الهيدروكسيل طبيعي/طبيعية.»
5. **EN** “But the methanol is not normal.”
   - FR : « Mais Méthanol (CH₃OH) n'est pas normal(e). »
   - AR : «لكن الميثانول غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the methanol normal?”
   - FR : Amine demande : « Méthanol (CH₃OH) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الميثانول طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:hydroxyl-group:v1` — **a hydroxyl group** · FR : Groupe hydroxyle (-OH) · AR : مجموعة الهيدروكسيل
- `gen:alcohol:v1` — **an alcohol** · FR : Alcool · AR : الكحول
- `gen:methanol:v1` — **a methanol** · FR : Méthanol (CH₃OH) · AR : الميثانول

### Questions (acceptées / modèle / hint)
- **q1.** Is the hydroxyl group normal?
  - accepté : yes ; yes it is ; the hydroxyl group is normal ; it is normal
  - modèle : Yes, the hydroxyl group is normal.
- **q2.** Is the methanol normal?
  - accepté : no ; no it is not ; no it isn't ; the methanol is not normal ; it is not normal ; it isn't normal
  - modèle : No, the methanol is not normal.
  - hint (si erreur) — FR : Attention : Méthanol (CH₃OH) n'est PAS normal(e).
- **q3.** Which one is not normal: the hydroxyl group or the methanol?
  - accepté : methanol ; the methanol
  - modèle : The methanol is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the alcohol normal?
  - accepté : yes ; yes it is ; the alcohol is normal
  - modèle : Yes, the alcohol is normal.
- **Tt2.** Is the hydroxyl group normal?
  - accepté : no ; no it is not ; no it isn't ; the hydroxyl group is not normal ; it is not normal
  - modèle : No, the hydroxyl group is not normal.
  - hint (si erreur) — FR : Attention : Groupe hydroxyle (-OH) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The hydroxyl group … normal. (be)` → is · modèle : The hydroxyl group is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Leila en deux phrases. Garde la négation. — amorces : The hydroxyl group is… / The methanol is not… — modèle : *The hydroxyl group is normal. The methanol is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Alcool normal(e), mais Groupe hydroxyle (-OH) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The alcohol is normal. The hydroxyl group is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-011` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Groupe hydroxyle (-OH) et Alcool à un patient.
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
4. **EN** “First, we check hydroxyl group.”
   - FR : « D'abord, nous vérifions Groupe hydroxyle (-OH). »
   - AR : «أولًا، نتحقق من مجموعة الهيدروكسيل.»
5. **EN** “Then, we check alcohol.”
   - FR : « Ensuite, nous vérifions Alcool. »
   - AR : «ثم نتحقق من الكحول.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:hydroxyl-group:v1` — **a hydroxyl group** · FR : Groupe hydroxyle (-OH) · AR : مجموعة الهيدروكسيل
- `gen:alcohol:v1` — **an alcohol** · FR : Alcool · AR : الكحول
- `gen:methanol:v1` — **a methanol** · FR : Méthanol (CH₃OH) · AR : الميثانول

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : hydroxyl group ; we check hydroxyl group ; hydroxyl group first ; the doctor checks hydroxyl group first
  - modèle : First, the doctor checks hydroxyl group.
- **q2.** What does the doctor check next?
  - accepté : alcohol ; then we check alcohol ; alcohol next ; the doctor checks alcohol next
  - modèle : Then, the doctor checks alcohol.
- **q3.** Does the doctor check alcohol before hydroxyl group?
  - accepté : no ; no he does not ; no he doesn't ; he checks hydroxyl group first
  - modèle : No, he checks hydroxyl group first, then alcohol.
  - hint (si erreur) — FR : Attention : Groupe hydroxyle (-OH) vient D'ABORD, ensuite Alcool.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : methanol ; methanol first ; he checks methanol first
  - modèle : First, the doctor checks methanol.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check hydroxyl group. Then, we check alcohol.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Méthanol (CH₃OH), ensuite Groupe hydroxyle (-OH), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check methanol. Then, we check hydroxyl group.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-011` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Groupe hydroxyle (-OH) / Alcool, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the hydroxyl group.
   - FR : Le patient du lit 3 a Groupe hydroxyle (-OH).
   - AR : مريض السرير ٣ عنده مجموعة الهيدروكسيل.
4. **EN** The patient in bed 5 has a problem with the alcohol.
   - FR : Le patient du lit 5 a Alcool.
   - AR : مريض السرير ٥ عنده الكحول.
5. **EN** The patient in bed 5 does not have a problem with the hydroxyl group.
   - FR : Le patient du lit 5 n'a pas de Groupe hydroxyle (-OH).
   - AR : مريض السرير ٥ ليس عنده مجموعة الهيدروكسيل.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:hydroxyl-group:v1` — **a hydroxyl group** · FR : Groupe hydroxyle (-OH) · AR : مجموعة الهيدروكسيل
- `gen:alcohol:v1` — **an alcohol** · FR : Alcool · AR : الكحول
- `gen:methanol:v1` — **a methanol** · FR : Méthanol (CH₃OH) · AR : الميثانول

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hydroxyl group ; a hydroxyl group ; the patient with hydroxyl group
  - modèle : The patient in bed 3 has a hydroxyl group.
- **q2.** What does the patient in bed 5 have?
  - accepté : alcohol ; a problem with the alcohol
  - modèle : He has a problem with the alcohol.
- **q3.** Does the patient in bed 5 have hydroxyl group?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the hydroxyl group ; he doesn't
  - modèle : No, he does not have a problem with the hydroxyl group.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS groupe hydroxyle (-oh) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : methanol ; a problem with the methanol
  - modèle : He has a problem with the methanol.
- **Tt2.** Does the patient in bed 9 have alcohol?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the alcohol ; he doesn't
  - modèle : No, he does not have a problem with the alcohol.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS alcool.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a hydroxyl group and an alcohol. (have)` → have · modèle : The patients have a hydroxyl group and an alcohol.
- `The patient in bed 5 … not have a hydroxyl group. (do)` → does · modèle : The patient in bed 5 does not have a hydroxyl group.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the hydroxyl group. The patient in bed 5 has a problem with the alcohol.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Méthanol (CH₃OH), le lit 9 a Groupe hydroxyle (-OH) et n'a pas Alcool.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the methanol. The patient in bed 9 has a problem with the hydroxyl group.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
