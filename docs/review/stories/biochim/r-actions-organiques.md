# Relecture — Réactions Organiques (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-019` — Le cas de Hana : Réaction de substitution anormal(e) / _Hana: the substitution reaction is not normal_

**Objectif :** Comprendre des résultats : Réaction d'addition normal(e), Réaction de substitution anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The addition reaction is normal.”
   - FR : Le médecin dit : « Réaction d'addition est normal(e). »
   - AR : يقول الطبيب: «تفاعل الإضافة طبيعي/طبيعية.»
5. **EN** “But the substitution reaction is not normal.”
   - FR : « Mais Réaction de substitution n'est pas normal(e). »
   - AR : «لكن تفاعل الاستبدال غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the substitution reaction normal?”
   - FR : Amine demande : « Réaction de substitution est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل تفاعل الاستبدال طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:addition-reaction:v1` — **an addition reaction** · FR : Réaction d'addition · AR : تفاعل الإضافة
- `gen:elimination-reaction:v1` — **an elimination reaction** · FR : Réaction d'élimination · AR : تفاعل الحذف
- `gen:substitution-reaction:v1` — **a substitution reaction** · FR : Réaction de substitution · AR : تفاعل الاستبدال

### Questions (acceptées / modèle / hint)
- **q1.** Is the addition reaction normal?
  - accepté : yes ; yes it is ; the addition reaction is normal ; it is normal
  - modèle : Yes, the addition reaction is normal.
- **q2.** Is the substitution reaction normal?
  - accepté : no ; no it is not ; no it isn't ; the substitution reaction is not normal ; it is not normal ; it isn't normal
  - modèle : No, the substitution reaction is not normal.
  - hint (si erreur) — FR : Attention : Réaction de substitution n'est PAS normal(e).
- **q3.** Which one is not normal: the addition reaction or the substitution reaction?
  - accepté : substitution reaction ; the substitution reaction
  - modèle : The substitution reaction is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the elimination reaction normal?
  - accepté : yes ; yes it is ; the elimination reaction is normal
  - modèle : Yes, the elimination reaction is normal.
- **Tt2.** Is the addition reaction normal?
  - accepté : no ; no it is not ; no it isn't ; the addition reaction is not normal ; it is not normal
  - modèle : No, the addition reaction is not normal.
  - hint (si erreur) — FR : Attention : Réaction d'addition n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The addition reaction … normal. (be)` → is · modèle : The addition reaction is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Hana en deux phrases. Garde la négation. — amorces : The addition reaction is… / The substitution reaction is not… — modèle : *The addition reaction is normal. The substitution reaction is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Réaction d'élimination normal(e), mais Réaction d'addition anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The elimination reaction is normal. The addition reaction is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-019` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Réaction d'addition et Réaction d'élimination à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check addition reaction.”
   - FR : « D'abord, nous vérifions Réaction d'addition. »
   - AR : «أولًا، نتحقق من تفاعل الإضافة.»
5. **EN** “Then, we check elimination reaction.”
   - FR : « Ensuite, nous vérifions Réaction d'élimination. »
   - AR : «ثم نتحقق من تفاعل الحذف.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:addition-reaction:v1` — **an addition reaction** · FR : Réaction d'addition · AR : تفاعل الإضافة
- `gen:elimination-reaction:v1` — **an elimination reaction** · FR : Réaction d'élimination · AR : تفاعل الحذف
- `gen:substitution-reaction:v1` — **a substitution reaction** · FR : Réaction de substitution · AR : تفاعل الاستبدال

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : addition reaction ; we check addition reaction ; addition reaction first ; the doctor checks addition reaction first
  - modèle : First, the doctor checks addition reaction.
- **q2.** What does the doctor check next?
  - accepté : elimination reaction ; then we check elimination reaction ; elimination reaction next ; the doctor checks elimination reaction next
  - modèle : Then, the doctor checks elimination reaction.
- **q3.** Does the doctor check elimination reaction before addition reaction?
  - accepté : no ; no he does not ; no he doesn't ; he checks addition reaction first
  - modèle : No, he checks addition reaction first, then elimination reaction.
  - hint (si erreur) — FR : Attention : Réaction d'addition vient D'ABORD, ensuite Réaction d'élimination.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : substitution reaction ; substitution reaction first ; he checks substitution reaction first
  - modèle : First, the doctor checks substitution reaction.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check addition reaction. Then, we check elimination reaction.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Réaction de substitution, ensuite Réaction d'addition, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check substitution reaction. Then, we check addition reaction.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-019` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Réaction d'addition / Réaction d'élimination, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the addition reaction.
   - FR : Le patient du lit 3 a Réaction d'addition.
   - AR : مريض السرير ٣ عنده تفاعل الإضافة.
4. **EN** The patient in bed 5 has a problem with the elimination reaction.
   - FR : Le patient du lit 5 a Réaction d'élimination.
   - AR : مريض السرير ٥ عنده تفاعل الحذف.
5. **EN** The patient in bed 5 does not have a problem with the addition reaction.
   - FR : Le patient du lit 5 n'a pas de Réaction d'addition.
   - AR : مريض السرير ٥ ليس عنده تفاعل الإضافة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:addition-reaction:v1` — **an addition reaction** · FR : Réaction d'addition · AR : تفاعل الإضافة
- `gen:elimination-reaction:v1` — **an elimination reaction** · FR : Réaction d'élimination · AR : تفاعل الحذف
- `gen:substitution-reaction:v1` — **a substitution reaction** · FR : Réaction de substitution · AR : تفاعل الاستبدال

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : addition reaction ; an addition reaction ; the patient with addition reaction
  - modèle : The patient in bed 3 has an addition reaction.
- **q2.** What does the patient in bed 5 have?
  - accepté : elimination reaction ; a problem with the elimination reaction
  - modèle : He has a problem with the elimination reaction.
- **q3.** Does the patient in bed 5 have addition reaction?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the addition reaction ; he doesn't
  - modèle : No, he does not have a problem with the addition reaction.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS réaction d'addition — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : substitution reaction ; a problem with the substitution reaction
  - modèle : He has a problem with the substitution reaction.
- **Tt2.** Does the patient in bed 9 have elimination reaction?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the elimination reaction ; he doesn't
  - modèle : No, he does not have a problem with the elimination reaction.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS réaction d'élimination.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an addition reaction and an elimination reaction. (have)` → have · modèle : The patients have an addition reaction and an elimination reaction.
- `The patient in bed 5 … not have an addition reaction. (do)` → does · modèle : The patient in bed 5 does not have an addition reaction.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the addition reaction. The patient in bed 5 has a problem with the elimination reaction.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Réaction de substitution, le lit 9 a Réaction d'addition et n'a pas Réaction d'élimination.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the substitution reaction. The patient in bed 9 has a problem with the addition reaction.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
