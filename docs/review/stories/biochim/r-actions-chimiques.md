# Relecture — Réactions Chimiques (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-018` — Le cas de Sofiane : Produit anormal(e) / _Sofiane: the product is not normal_

**Objectif :** Comprendre des résultats : Réaction chimique normal(e), Produit anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient at the clinic.
   - FR : Sofiane est un·e patient·e à la clinique.
   - AR : Sofiane مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The chemical reaction is normal.”
   - FR : Le médecin dit : « Réaction chimique est normal(e). »
   - AR : يقول الطبيب: «التفاعل الكيميائي طبيعي/طبيعية.»
5. **EN** “But the product is not normal.”
   - FR : « Mais Produit n'est pas normal(e). »
   - AR : «لكن الناتج غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the product normal?”
   - FR : Lina demande : « Produit est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الناتج طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:chemical-reaction:v1` — **a chemical reaction** · FR : Réaction chimique · AR : التفاعل الكيميائي
- `gen:reactant:v1` — **a reactant** · FR : Réactif · AR : المتفاعل
- `gen:product:v1` — **a product** · FR : Produit · AR : الناتج

### Questions (acceptées / modèle / hint)
- **q1.** Is the chemical reaction normal?
  - accepté : yes ; yes it is ; the chemical reaction is normal ; it is normal
  - modèle : Yes, the chemical reaction is normal.
- **q2.** Is the product normal?
  - accepté : no ; no it is not ; no it isn't ; the product is not normal ; it is not normal ; it isn't normal
  - modèle : No, the product is not normal.
  - hint (si erreur) — FR : Attention : Produit n'est PAS normal(e).
- **q3.** Which one is not normal: the chemical reaction or the product?
  - accepté : product ; the product
  - modèle : The product is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the reactant normal?
  - accepté : yes ; yes it is ; the reactant is normal
  - modèle : Yes, the reactant is normal.
- **Tt2.** Is the chemical reaction normal?
  - accepté : no ; no it is not ; no it isn't ; the chemical reaction is not normal ; it is not normal
  - modèle : No, the chemical reaction is not normal.
  - hint (si erreur) — FR : Attention : Réaction chimique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The chemical reaction … normal. (be)` → is · modèle : The chemical reaction is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sofiane en deux phrases. Garde la négation. — amorces : The chemical reaction is… / The product is not… — modèle : *The chemical reaction is normal. The product is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Réactif normal(e), mais Réaction chimique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The reactant is normal. The chemical reaction is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-018` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Réaction chimique et Réactif à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient.
   - FR : Bilal est un·e patient·e.
   - AR : Bilal مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check chemical reaction.”
   - FR : « D'abord, nous vérifions Réaction chimique. »
   - AR : «أولًا، نتحقق من التفاعل الكيميائي.»
5. **EN** “Then, we check reactant.”
   - FR : « Ensuite, nous vérifions Réactif. »
   - AR : «ثم نتحقق من المتفاعل.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:chemical-reaction:v1` — **a chemical reaction** · FR : Réaction chimique · AR : التفاعل الكيميائي
- `gen:reactant:v1` — **a reactant** · FR : Réactif · AR : المتفاعل
- `gen:product:v1` — **a product** · FR : Produit · AR : الناتج

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : chemical reaction ; we check chemical reaction ; chemical reaction first ; the doctor checks chemical reaction first
  - modèle : First, the doctor checks chemical reaction.
- **q2.** What does the doctor check next?
  - accepté : reactant ; then we check reactant ; reactant next ; the doctor checks reactant next
  - modèle : Then, the doctor checks reactant.
- **q3.** Does the doctor check reactant before chemical reaction?
  - accepté : no ; no he does not ; no he doesn't ; he checks chemical reaction first
  - modèle : No, he checks chemical reaction first, then reactant.
  - hint (si erreur) — FR : Attention : Réaction chimique vient D'ABORD, ensuite Réactif.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : product ; product first ; he checks product first
  - modèle : First, the doctor checks product.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check chemical reaction. Then, we check reactant.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Produit, ensuite Réaction chimique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check product. Then, we check chemical reaction.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-018` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Réaction chimique / Réactif, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the chemical reaction.
   - FR : Le patient du lit 3 a Réaction chimique.
   - AR : مريض السرير ٣ عنده التفاعل الكيميائي.
4. **EN** The patient in bed 5 has a problem with the reactant.
   - FR : Le patient du lit 5 a Réactif.
   - AR : مريض السرير ٥ عنده المتفاعل.
5. **EN** The patient in bed 5 does not have a problem with the chemical reaction.
   - FR : Le patient du lit 5 n'a pas de Réaction chimique.
   - AR : مريض السرير ٥ ليس عنده التفاعل الكيميائي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:chemical-reaction:v1` — **a chemical reaction** · FR : Réaction chimique · AR : التفاعل الكيميائي
- `gen:reactant:v1` — **a reactant** · FR : Réactif · AR : المتفاعل
- `gen:product:v1` — **a product** · FR : Produit · AR : الناتج

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : chemical reaction ; a chemical reaction ; the patient with chemical reaction
  - modèle : The patient in bed 3 has a chemical reaction.
- **q2.** What does the patient in bed 5 have?
  - accepté : reactant ; a problem with the reactant
  - modèle : He has a problem with the reactant.
- **q3.** Does the patient in bed 5 have chemical reaction?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the chemical reaction ; he doesn't
  - modèle : No, he does not have a problem with the chemical reaction.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS réaction chimique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : product ; a problem with the product
  - modèle : He has a problem with the product.
- **Tt2.** Does the patient in bed 9 have reactant?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the reactant ; he doesn't
  - modèle : No, he does not have a problem with the reactant.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS réactif.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a chemical reaction and a reactant. (have)` → have · modèle : The patients have a chemical reaction and a reactant.
- `The patient in bed 5 … not have a chemical reaction. (do)` → does · modèle : The patient in bed 5 does not have a chemical reaction.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the chemical reaction. The patient in bed 5 has a problem with the reactant.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Produit, le lit 9 a Réaction chimique et n'a pas Réactif.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the product. The patient in bed 9 has a problem with the chemical reaction.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
