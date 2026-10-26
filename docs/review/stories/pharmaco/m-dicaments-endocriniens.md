# Relecture — Médicaments Endocriniens (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-007` — Le cas de Hana : Sulfonylurée anormal(e) / _Hana: the sulfonylurea is not normal_

**Objectif :** Comprendre des résultats : Insuline normal(e), Sulfonylurée anormal(e).
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
4. **EN** The doctor says, “The insulin is normal.”
   - FR : Le médecin dit : « Insuline est normal(e). »
   - AR : يقول الطبيب: «الأنسولين طبيعي/طبيعية.»
5. **EN** “But the sulfonylurea is not normal.”
   - FR : « Mais Sulfonylurée n'est pas normal(e). »
   - AR : «لكن السلفونيل يوريا غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the sulfonylurea normal?”
   - FR : Amine demande : « Sulfonylurée est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل السلفونيل يوريا طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:insulin:v1` — **an insulin** · FR : Insuline · AR : الأنسولين
- `gen:metformin:v1` — **a metformin** · FR : Metformine · AR : الميتفورمين
- `gen:sulfonylurea:v1` — **a sulfonylurea** · FR : Sulfonylurée · AR : السلفونيل يوريا

### Questions (acceptées / modèle / hint)
- **q1.** Is the insulin normal?
  - accepté : yes ; yes it is ; the insulin is normal ; it is normal
  - modèle : Yes, the insulin is normal.
- **q2.** Is the sulfonylurea normal?
  - accepté : no ; no it is not ; no it isn't ; the sulfonylurea is not normal ; it is not normal ; it isn't normal
  - modèle : No, the sulfonylurea is not normal.
  - hint (si erreur) — FR : Attention : Sulfonylurée n'est PAS normal(e).
- **q3.** Which one is not normal: the insulin or the sulfonylurea?
  - accepté : sulfonylurea ; the sulfonylurea
  - modèle : The sulfonylurea is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the metformin normal?
  - accepté : yes ; yes it is ; the metformin is normal
  - modèle : Yes, the metformin is normal.
- **Tt2.** Is the insulin normal?
  - accepté : no ; no it is not ; no it isn't ; the insulin is not normal ; it is not normal
  - modèle : No, the insulin is not normal.
  - hint (si erreur) — FR : Attention : Insuline n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The insulin … normal. (be)` → is · modèle : The insulin is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Hana en deux phrases. Garde la négation. — amorces : The insulin is… / The sulfonylurea is not… — modèle : *The insulin is normal. The sulfonylurea is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Metformine normal(e), mais Insuline anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The metformin is normal. The insulin is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-007` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Insuline et Metformine à un patient.
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
4. **EN** “First, we check insulin.”
   - FR : « D'abord, nous vérifions Insuline. »
   - AR : «أولًا، نتحقق من الأنسولين.»
5. **EN** “Then, we check metformin.”
   - FR : « Ensuite, nous vérifions Metformine. »
   - AR : «ثم نتحقق من الميتفورمين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:insulin:v1` — **an insulin** · FR : Insuline · AR : الأنسولين
- `gen:metformin:v1` — **a metformin** · FR : Metformine · AR : الميتفورمين
- `gen:sulfonylurea:v1` — **a sulfonylurea** · FR : Sulfonylurée · AR : السلفونيل يوريا

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : insulin ; we check insulin ; insulin first ; the doctor checks insulin first
  - modèle : First, the doctor checks insulin.
- **q2.** What does the doctor check next?
  - accepté : metformin ; then we check metformin ; metformin next ; the doctor checks metformin next
  - modèle : Then, the doctor checks metformin.
- **q3.** Does the doctor check metformin before insulin?
  - accepté : no ; no he does not ; no he doesn't ; he checks insulin first
  - modèle : No, he checks insulin first, then metformin.
  - hint (si erreur) — FR : Attention : Insuline vient D'ABORD, ensuite Metformine.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : sulfonylurea ; sulfonylurea first ; he checks sulfonylurea first
  - modèle : First, the doctor checks sulfonylurea.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check insulin. Then, we check metformin.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Sulfonylurée, ensuite Insuline, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check sulfonylurea. Then, we check insulin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-007` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Insuline / Metformine, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the insulin.
   - FR : Le patient du lit 3 a Insuline.
   - AR : مريض السرير ٣ عنده الأنسولين.
4. **EN** The patient in bed 5 has a problem with the metformin.
   - FR : Le patient du lit 5 a Metformine.
   - AR : مريض السرير ٥ عنده الميتفورمين.
5. **EN** The patient in bed 5 does not have a problem with the insulin.
   - FR : Le patient du lit 5 n'a pas de Insuline.
   - AR : مريض السرير ٥ ليس عنده الأنسولين.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:insulin:v1` — **an insulin** · FR : Insuline · AR : الأنسولين
- `gen:metformin:v1` — **a metformin** · FR : Metformine · AR : الميتفورمين
- `gen:sulfonylurea:v1` — **a sulfonylurea** · FR : Sulfonylurée · AR : السلفونيل يوريا

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : insulin ; an insulin ; the patient with insulin
  - modèle : The patient in bed 3 has an insulin.
- **q2.** What does the patient in bed 5 have?
  - accepté : metformin ; a problem with the metformin
  - modèle : He has a problem with the metformin.
- **q3.** Does the patient in bed 5 have insulin?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the insulin ; he doesn't
  - modèle : No, he does not have a problem with the insulin.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS insuline — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : sulfonylurea ; a problem with the sulfonylurea
  - modèle : He has a problem with the sulfonylurea.
- **Tt2.** Does the patient in bed 9 have metformin?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the metformin ; he doesn't
  - modèle : No, he does not have a problem with the metformin.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS metformine.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an insulin and a metformin. (have)` → have · modèle : The patients have an insulin and a metformin.
- `The patient in bed 5 … not have an insulin. (do)` → does · modèle : The patient in bed 5 does not have an insulin.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the insulin. The patient in bed 5 has a problem with the metformin.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Sulfonylurée, le lit 9 a Insuline et n'a pas Metformine.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the sulfonylurea. The patient in bed 9 has a problem with the insulin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
