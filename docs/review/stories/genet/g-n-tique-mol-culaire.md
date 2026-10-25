# Relecture — Génétique Moléculaire (genet)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `genet-case-004` — Le cas de Farid : Opéron anormal(e) / _Farid: the operon is not normal_

**Objectif :** Comprendre des résultats : Expression génique normal(e), Opéron anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The gene expression is normal.”
   - FR : Le médecin dit : « Expression génique est normal(e). »
   - AR : يقول الطبيب: «التعبير الجيني طبيعي/طبيعية.»
5. **EN** “But the operon is not normal.”
   - FR : « Mais Opéron n'est pas normal(e). »
   - AR : «لكن الأوبيرون غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the operon normal?”
   - FR : Lina demande : « Opéron est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الأوبيرون طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:gene-expression:v1` — **a gene expression** · FR : Expression génique · AR : التعبير الجيني
- `gen:gene-regulation:v1` — **a gene regulation** · FR : Régulation génique · AR : التنظيم الجيني
- `gen:operon:v1` — **an operon** · FR : Opéron · AR : الأوبيرون

### Questions (acceptées / modèle / hint)
- **q1.** Is the gene expression normal?
  - accepté : yes ; yes it is ; the gene expression is normal ; it is normal
  - modèle : Yes, the gene expression is normal.
- **q2.** Is the operon normal?
  - accepté : no ; no it is not ; no it isn't ; the operon is not normal ; it is not normal ; it isn't normal
  - modèle : No, the operon is not normal.
  - hint (si erreur) — FR : Attention : Opéron n'est PAS normal(e).
- **q3.** Which one is not normal: the gene expression or the operon?
  - accepté : operon ; the operon
  - modèle : The operon is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the gene regulation normal?
  - accepté : yes ; yes it is ; the gene regulation is normal
  - modèle : Yes, the gene regulation is normal.
- **Tt2.** Is the gene expression normal?
  - accepté : no ; no it is not ; no it isn't ; the gene expression is not normal ; it is not normal
  - modèle : No, the gene expression is not normal.
  - hint (si erreur) — FR : Attention : Expression génique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The gene expression … normal. (be)` → is · modèle : The gene expression is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The gene expression is… / The operon is not… — modèle : *The gene expression is normal. The operon is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Régulation génique normal(e), mais Expression génique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The gene regulation is normal. The gene expression is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `genet-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Expression génique et Régulation génique à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient.
   - FR : Sofiane est un·e patient·e.
   - AR : Sofiane مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check gene expression.”
   - FR : « D'abord, nous vérifions Expression génique. »
   - AR : «أولًا، نتحقق من التعبير الجيني.»
5. **EN** “Then, we check gene regulation.”
   - FR : « Ensuite, nous vérifions Régulation génique. »
   - AR : «ثم نتحقق من التنظيم الجيني.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:gene-expression:v1` — **a gene expression** · FR : Expression génique · AR : التعبير الجيني
- `gen:gene-regulation:v1` — **a gene regulation** · FR : Régulation génique · AR : التنظيم الجيني
- `gen:operon:v1` — **an operon** · FR : Opéron · AR : الأوبيرون

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : gene expression ; we check gene expression ; gene expression first ; the doctor checks gene expression first
  - modèle : First, the doctor checks gene expression.
- **q2.** What does the doctor check next?
  - accepté : gene regulation ; then we check gene regulation ; gene regulation next ; the doctor checks gene regulation next
  - modèle : Then, the doctor checks gene regulation.
- **q3.** Does the doctor check gene regulation before gene expression?
  - accepté : no ; no he does not ; no he doesn't ; he checks gene expression first
  - modèle : No, he checks gene expression first, then gene regulation.
  - hint (si erreur) — FR : Attention : Expression génique vient D'ABORD, ensuite Régulation génique.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : operon ; operon first ; he checks operon first
  - modèle : First, the doctor checks operon.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check gene expression. Then, we check gene regulation.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Opéron, ensuite Expression génique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check operon. Then, we check gene expression.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `genet-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Expression génique / Régulation génique, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the gene expression.
   - FR : Le patient du lit 3 a Expression génique.
   - AR : مريض السرير ٣ عنده التعبير الجيني.
4. **EN** The patient in bed 5 has a problem with the gene regulation.
   - FR : Le patient du lit 5 a Régulation génique.
   - AR : مريض السرير ٥ عنده التنظيم الجيني.
5. **EN** The patient in bed 5 does not have a problem with the gene expression.
   - FR : Le patient du lit 5 n'a pas de Expression génique.
   - AR : مريض السرير ٥ ليس عنده التعبير الجيني.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:gene-expression:v1` — **a gene expression** · FR : Expression génique · AR : التعبير الجيني
- `gen:gene-regulation:v1` — **a gene regulation** · FR : Régulation génique · AR : التنظيم الجيني
- `gen:operon:v1` — **an operon** · FR : Opéron · AR : الأوبيرون

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : gene expression ; a gene expression ; the patient with gene expression
  - modèle : The patient in bed 3 has a gene expression.
- **q2.** What does the patient in bed 5 have?
  - accepté : gene regulation ; a problem with the gene regulation
  - modèle : He has a problem with the gene regulation.
- **q3.** Does the patient in bed 5 have gene expression?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the gene expression ; he doesn't
  - modèle : No, he does not have a problem with the gene expression.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS expression génique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : operon ; a problem with the operon
  - modèle : He has a problem with the operon.
- **Tt2.** Does the patient in bed 9 have gene regulation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the gene regulation ; he doesn't
  - modèle : No, he does not have a problem with the gene regulation.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS régulation génique.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a gene expression and a gene regulation. (have)` → have · modèle : The patients have a gene expression and a gene regulation.
- `The patient in bed 5 … not have a gene expression. (do)` → does · modèle : The patient in bed 5 does not have a gene expression.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the gene expression. The patient in bed 5 has a problem with the gene regulation.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Opéron, le lit 9 a Expression génique et n'a pas Régulation génique.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the operon. The patient in bed 9 has a problem with the gene expression.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
