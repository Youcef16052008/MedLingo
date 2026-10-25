# Relecture — Fécondation (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-001` — Le cas de Nadia : Réaction acrosomiale anormal(e) / _Nadia: the acrosomal reaction is not normal_

**Objectif :** Comprendre des résultats : Fécondation normal(e), Réaction acrosomiale anormal(e).
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
4. **EN** The doctor says, “The fertilization is normal.”
   - FR : Le médecin dit : « Fécondation est normal(e). »
   - AR : يقول الطبيب: «الإخصاب طبيعي/طبيعية.»
5. **EN** “But the acrosomal reaction is not normal.”
   - FR : « Mais Réaction acrosomiale n'est pas normal(e). »
   - AR : «لكن التفاعل الأكروسومي غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the acrosomal reaction normal?”
   - FR : Amine demande : « Réaction acrosomiale est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل التفاعل الأكروسومي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:fertilization:v1` — **a fertilization** · FR : Fécondation · AR : الإخصاب
- `gen:zygote:v1` — **a zygote** · FR : Zygote · AR : اللاقحة
- `gen:acrosomal-reaction:v1` — **an acrosomal reaction** · FR : Réaction acrosomiale · AR : التفاعل الأكروسومي

### Questions (acceptées / modèle / hint)
- **q1.** Is the fertilization normal?
  - accepté : yes ; yes it is ; the fertilization is normal ; it is normal
  - modèle : Yes, the fertilization is normal.
- **q2.** Is the acrosomal reaction normal?
  - accepté : no ; no it is not ; no it isn't ; the acrosomal reaction is not normal ; it is not normal ; it isn't normal
  - modèle : No, the acrosomal reaction is not normal.
  - hint (si erreur) — FR : Attention : Réaction acrosomiale n'est PAS normal(e).
- **q3.** Which one is not normal: the fertilization or the acrosomal reaction?
  - accepté : acrosomal reaction ; the acrosomal reaction
  - modèle : The acrosomal reaction is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the zygote normal?
  - accepté : yes ; yes it is ; the zygote is normal
  - modèle : Yes, the zygote is normal.
- **Tt2.** Is the fertilization normal?
  - accepté : no ; no it is not ; no it isn't ; the fertilization is not normal ; it is not normal
  - modèle : No, the fertilization is not normal.
  - hint (si erreur) — FR : Attention : Fécondation n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The fertilization … normal. (be)` → is · modèle : The fertilization is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The fertilization is… / The acrosomal reaction is not… — modèle : *The fertilization is normal. The acrosomal reaction is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Zygote normal(e), mais Fécondation anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The zygote is normal. The fertilization is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Fécondation et Zygote à un patient.
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
4. **EN** “First, we check fertilization.”
   - FR : « D'abord, nous vérifions Fécondation. »
   - AR : «أولًا، نتحقق من الإخصاب.»
5. **EN** “Then, we check zygote.”
   - FR : « Ensuite, nous vérifions Zygote. »
   - AR : «ثم نتحقق من اللاقحة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:fertilization:v1` — **a fertilization** · FR : Fécondation · AR : الإخصاب
- `gen:zygote:v1` — **a zygote** · FR : Zygote · AR : اللاقحة
- `gen:acrosomal-reaction:v1` — **an acrosomal reaction** · FR : Réaction acrosomiale · AR : التفاعل الأكروسومي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : fertilization ; we check fertilization ; fertilization first ; the doctor checks fertilization first
  - modèle : First, the doctor checks fertilization.
- **q2.** What does the doctor check next?
  - accepté : zygote ; then we check zygote ; zygote next ; the doctor checks zygote next
  - modèle : Then, the doctor checks zygote.
- **q3.** Does the doctor check zygote before fertilization?
  - accepté : no ; no he does not ; no he doesn't ; he checks fertilization first
  - modèle : No, he checks fertilization first, then zygote.
  - hint (si erreur) — FR : Attention : Fécondation vient D'ABORD, ensuite Zygote.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : acrosomal reaction ; acrosomal reaction first ; he checks acrosomal reaction first
  - modèle : First, the doctor checks acrosomal reaction.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check fertilization. Then, we check zygote.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Réaction acrosomiale, ensuite Fécondation, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check acrosomal reaction. Then, we check fertilization.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Fécondation / Zygote, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the fertilization.
   - FR : Le patient du lit 3 a Fécondation.
   - AR : مريض السرير ٣ عنده الإخصاب.
4. **EN** The patient in bed 5 has a problem with the zygote.
   - FR : Le patient du lit 5 a Zygote.
   - AR : مريض السرير ٥ عنده اللاقحة.
5. **EN** The patient in bed 5 does not have a problem with the fertilization.
   - FR : Le patient du lit 5 n'a pas de Fécondation.
   - AR : مريض السرير ٥ ليس عنده الإخصاب.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:fertilization:v1` — **a fertilization** · FR : Fécondation · AR : الإخصاب
- `gen:zygote:v1` — **a zygote** · FR : Zygote · AR : اللاقحة
- `gen:acrosomal-reaction:v1` — **an acrosomal reaction** · FR : Réaction acrosomiale · AR : التفاعل الأكروسومي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : fertilization ; a fertilization ; the patient with fertilization
  - modèle : The patient in bed 3 has a fertilization.
- **q2.** What does the patient in bed 5 have?
  - accepté : zygote ; a problem with the zygote
  - modèle : He has a problem with the zygote.
- **q3.** Does the patient in bed 5 have fertilization?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the fertilization ; he doesn't
  - modèle : No, he does not have a problem with the fertilization.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS fécondation — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : acrosomal reaction ; a problem with the acrosomal reaction
  - modèle : He has a problem with the acrosomal reaction.
- **Tt2.** Does the patient in bed 9 have zygote?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the zygote ; he doesn't
  - modèle : No, he does not have a problem with the zygote.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS zygote.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a fertilization and a zygote. (have)` → have · modèle : The patients have a fertilization and a zygote.
- `The patient in bed 5 … not have a fertilization. (do)` → does · modèle : The patient in bed 5 does not have a fertilization.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the fertilization. The patient in bed 5 has a problem with the zygote.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Réaction acrosomiale, le lit 9 a Fécondation et n'a pas Zygote.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the acrosomal reaction. The patient in bed 9 has a problem with the fertilization.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
