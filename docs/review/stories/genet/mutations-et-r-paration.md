# Relecture — Mutations et Réparation (genet)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `genet-case-008` — Le cas de Bilal : Substitution anormal(e) / _Bilal: the substitution is not normal_

**Objectif :** Comprendre des résultats : Mutation normal(e), Substitution anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The mutation is normal.”
   - FR : Le médecin dit : « Mutation est normal(e). »
   - AR : يقول الطبيب: «الطفرة طبيعي/طبيعية.»
5. **EN** “But the substitution is not normal.”
   - FR : « Mais Substitution n'est pas normal(e). »
   - AR : «لكن الاستبدال غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the substitution normal?”
   - FR : Lina demande : « Substitution est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الاستبدال طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:mutation:v1` — **a mutation** · FR : Mutation · AR : الطفرة
- `gen:point-mutation:v1` — **a point mutation** · FR : Mutation ponctuelle · AR : الطفرة النقطية
- `gen:substitution:v1` — **a substitution** · FR : Substitution · AR : الاستبدال

### Questions (acceptées / modèle / hint)
- **q1.** Is the mutation normal?
  - accepté : yes ; yes it is ; the mutation is normal ; it is normal
  - modèle : Yes, the mutation is normal.
- **q2.** Is the substitution normal?
  - accepté : no ; no it is not ; no it isn't ; the substitution is not normal ; it is not normal ; it isn't normal
  - modèle : No, the substitution is not normal.
  - hint (si erreur) — FR : Attention : Substitution n'est PAS normal(e).
- **q3.** Which one is not normal: the mutation or the substitution?
  - accepté : substitution ; the substitution
  - modèle : The substitution is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the point mutation normal?
  - accepté : yes ; yes it is ; the point mutation is normal
  - modèle : Yes, the point mutation is normal.
- **Tt2.** Is the mutation normal?
  - accepté : no ; no it is not ; no it isn't ; the mutation is not normal ; it is not normal
  - modèle : No, the mutation is not normal.
  - hint (si erreur) — FR : Attention : Mutation n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The mutation … normal. (be)` → is · modèle : The mutation is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The mutation is… / The substitution is not… — modèle : *The mutation is normal. The substitution is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Mutation ponctuelle normal(e), mais Mutation anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The point mutation is normal. The mutation is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `genet-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Mutation et Mutation ponctuelle à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient.
   - FR : Omar est un·e patient·e.
   - AR : Omar مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check mutation.”
   - FR : « D'abord, nous vérifions Mutation. »
   - AR : «أولًا، نتحقق من الطفرة.»
5. **EN** “Then, we check point mutation.”
   - FR : « Ensuite, nous vérifions Mutation ponctuelle. »
   - AR : «ثم نتحقق من الطفرة النقطية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:mutation:v1` — **a mutation** · FR : Mutation · AR : الطفرة
- `gen:point-mutation:v1` — **a point mutation** · FR : Mutation ponctuelle · AR : الطفرة النقطية
- `gen:substitution:v1` — **a substitution** · FR : Substitution · AR : الاستبدال

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : mutation ; we check mutation ; mutation first ; the doctor checks mutation first
  - modèle : First, the doctor checks mutation.
- **q2.** What does the doctor check next?
  - accepté : point mutation ; then we check point mutation ; point mutation next ; the doctor checks point mutation next
  - modèle : Then, the doctor checks point mutation.
- **q3.** Does the doctor check point mutation before mutation?
  - accepté : no ; no he does not ; no he doesn't ; he checks mutation first
  - modèle : No, he checks mutation first, then point mutation.
  - hint (si erreur) — FR : Attention : Mutation vient D'ABORD, ensuite Mutation ponctuelle.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : substitution ; substitution first ; he checks substitution first
  - modèle : First, the doctor checks substitution.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check mutation. Then, we check point mutation.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Substitution, ensuite Mutation, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check substitution. Then, we check mutation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `genet-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Mutation / Mutation ponctuelle, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the mutation.
   - FR : Le patient du lit 3 a Mutation.
   - AR : مريض السرير ٣ عنده الطفرة.
4. **EN** The patient in bed 5 has a problem with the point mutation.
   - FR : Le patient du lit 5 a Mutation ponctuelle.
   - AR : مريض السرير ٥ عنده الطفرة النقطية.
5. **EN** The patient in bed 5 does not have a problem with the mutation.
   - FR : Le patient du lit 5 n'a pas de Mutation.
   - AR : مريض السرير ٥ ليس عنده الطفرة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:mutation:v1` — **a mutation** · FR : Mutation · AR : الطفرة
- `gen:point-mutation:v1` — **a point mutation** · FR : Mutation ponctuelle · AR : الطفرة النقطية
- `gen:substitution:v1` — **a substitution** · FR : Substitution · AR : الاستبدال

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : mutation ; a mutation ; the patient with mutation
  - modèle : The patient in bed 3 has a mutation.
- **q2.** What does the patient in bed 5 have?
  - accepté : point mutation ; a problem with the point mutation
  - modèle : He has a problem with the point mutation.
- **q3.** Does the patient in bed 5 have mutation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the mutation ; he doesn't
  - modèle : No, he does not have a problem with the mutation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS mutation — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : substitution ; a problem with the substitution
  - modèle : He has a problem with the substitution.
- **Tt2.** Does the patient in bed 9 have point mutation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the point mutation ; he doesn't
  - modèle : No, he does not have a problem with the point mutation.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS mutation ponctuelle.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a mutation and a point mutation. (have)` → have · modèle : The patients have a mutation and a point mutation.
- `The patient in bed 5 … not have a mutation. (do)` → does · modèle : The patient in bed 5 does not have a mutation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the mutation. The patient in bed 5 has a problem with the point mutation.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Substitution, le lit 9 a Mutation et n'a pas Mutation ponctuelle.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the substitution. The patient in bed 9 has a problem with the mutation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
