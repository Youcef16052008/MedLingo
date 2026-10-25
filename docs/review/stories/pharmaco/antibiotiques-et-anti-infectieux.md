# Relecture — Antibiotiques et Anti-infectieux (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-001` — Le cas de Nadia : Fluoroquinolone anormal(e) / _Nadia: the fluoroquinolone is not normal_

**Objectif :** Comprendre des résultats : Céphalosporine normal(e), Fluoroquinolone anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The cephalosporin is normal.”
   - FR : Le médecin dit : « Céphalosporine est normal(e). »
   - AR : يقول الطبيب: «السيفالوسبورين طبيعي/طبيعية.»
5. **EN** “But the fluoroquinolone is not normal.”
   - FR : « Mais Fluoroquinolone n'est pas normal(e). »
   - AR : «لكن الفلوروكينولون غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the fluoroquinolone normal?”
   - FR : Amine demande : « Fluoroquinolone est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الفلوروكينولون طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:cephalosporin:v1` — **a cephalosporin** · FR : Céphalosporine · AR : السيفالوسبورين
- `gen:macrolide:v1` — **a macrolide** · FR : Macrolide · AR : الماكروليد
- `gen:fluoroquinolone:v1` — **a fluoroquinolone** · FR : Fluoroquinolone · AR : الفلوروكينولون

### Questions (acceptées / modèle / hint)
- **q1.** Is the cephalosporin normal?
  - accepté : yes ; yes it is ; the cephalosporin is normal ; it is normal
  - modèle : Yes, the cephalosporin is normal.
- **q2.** Is the fluoroquinolone normal?
  - accepté : no ; no it is not ; no it isn't ; the fluoroquinolone is not normal ; it is not normal ; it isn't normal
  - modèle : No, the fluoroquinolone is not normal.
  - hint (si erreur) — FR : Attention : Fluoroquinolone n'est PAS normal(e).
- **q3.** Which one is not normal: the cephalosporin or the fluoroquinolone?
  - accepté : fluoroquinolone ; the fluoroquinolone
  - modèle : The fluoroquinolone is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the macrolide normal?
  - accepté : yes ; yes it is ; the macrolide is normal
  - modèle : Yes, the macrolide is normal.
- **Tt2.** Is the cephalosporin normal?
  - accepté : no ; no it is not ; no it isn't ; the cephalosporin is not normal ; it is not normal
  - modèle : No, the cephalosporin is not normal.
  - hint (si erreur) — FR : Attention : Céphalosporine n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The cephalosporin … normal. (be)` → is · modèle : The cephalosporin is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The cephalosporin is… / The fluoroquinolone is not… — modèle : *The cephalosporin is normal. The fluoroquinolone is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Macrolide normal(e), mais Céphalosporine anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The macrolide is normal. The cephalosporin is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Céphalosporine et Macrolide à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient.
   - FR : Amel est un·e patient·e.
   - AR : Amel مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check cephalosporin.”
   - FR : « D'abord, nous vérifions Céphalosporine. »
   - AR : «أولًا، نتحقق من السيفالوسبورين.»
5. **EN** “Then, we check macrolide.”
   - FR : « Ensuite, nous vérifions Macrolide. »
   - AR : «ثم نتحقق من الماكروليد.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cephalosporin:v1` — **a cephalosporin** · FR : Céphalosporine · AR : السيفالوسبورين
- `gen:macrolide:v1` — **a macrolide** · FR : Macrolide · AR : الماكروليد
- `gen:fluoroquinolone:v1` — **a fluoroquinolone** · FR : Fluoroquinolone · AR : الفلوروكينولون

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cephalosporin ; we check cephalosporin ; cephalosporin first ; the doctor checks cephalosporin first
  - modèle : First, the doctor checks cephalosporin.
- **q2.** What does the doctor check next?
  - accepté : macrolide ; then we check macrolide ; macrolide next ; the doctor checks macrolide next
  - modèle : Then, the doctor checks macrolide.
- **q3.** Does the doctor check macrolide before cephalosporin?
  - accepté : no ; no he does not ; no he doesn't ; he checks cephalosporin first
  - modèle : No, he checks cephalosporin first, then macrolide.
  - hint (si erreur) — FR : Attention : Céphalosporine vient D'ABORD, ensuite Macrolide.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : fluoroquinolone ; fluoroquinolone first ; he checks fluoroquinolone first
  - modèle : First, the doctor checks fluoroquinolone.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cephalosporin. Then, we check macrolide.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fluoroquinolone, ensuite Céphalosporine, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check fluoroquinolone. Then, we check cephalosporin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Céphalosporine / Macrolide, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the cephalosporin.
   - FR : Le patient du lit 3 a Céphalosporine.
   - AR : مريض السرير ٣ عنده السيفالوسبورين.
4. **EN** The patient in bed 5 has a problem with the macrolide.
   - FR : Le patient du lit 5 a Macrolide.
   - AR : مريض السرير ٥ عنده الماكروليد.
5. **EN** The patient in bed 5 does not have a problem with the cephalosporin.
   - FR : Le patient du lit 5 n'a pas de Céphalosporine.
   - AR : مريض السرير ٥ ليس عنده السيفالوسبورين.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:cephalosporin:v1` — **a cephalosporin** · FR : Céphalosporine · AR : السيفالوسبورين
- `gen:macrolide:v1` — **a macrolide** · FR : Macrolide · AR : الماكروليد
- `gen:fluoroquinolone:v1` — **a fluoroquinolone** · FR : Fluoroquinolone · AR : الفلوروكينولون

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cephalosporin ; a cephalosporin ; the patient with cephalosporin
  - modèle : The patient in bed 3 has a cephalosporin.
- **q2.** What does the patient in bed 5 have?
  - accepté : macrolide ; a problem with the macrolide
  - modèle : He has a problem with the macrolide.
- **q3.** Does the patient in bed 5 have cephalosporin?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the cephalosporin ; he doesn't
  - modèle : No, he does not have a problem with the cephalosporin.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS céphalosporine — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : fluoroquinolone ; a problem with the fluoroquinolone
  - modèle : He has a problem with the fluoroquinolone.
- **Tt2.** Does the patient in bed 9 have macrolide?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the macrolide ; he doesn't
  - modèle : No, he does not have a problem with the macrolide.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS macrolide.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a cephalosporin and a macrolide. (have)` → have · modèle : The patients have a cephalosporin and a macrolide.
- `The patient in bed 5 … not have a cephalosporin. (do)` → does · modèle : The patient in bed 5 does not have a cephalosporin.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the cephalosporin. The patient in bed 5 has a problem with the macrolide.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Fluoroquinolone, le lit 9 a Céphalosporine et n'a pas Macrolide.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the fluoroquinolone. The patient in bed 9 has a problem with the cephalosporin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
