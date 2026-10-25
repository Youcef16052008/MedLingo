# Relecture — Biotechnologie et Génie Génétique (genet)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `genet-case-000` — Le cas de Karim : ADN recombinant anormal(e) / _Karim: the recombinant dna is not normal_

**Objectif :** Comprendre des résultats : Génie génétique normal(e), ADN recombinant anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient at the clinic.
   - FR : Karim est un·e patient·e à la clinique.
   - AR : Karim مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The genetic engineering is normal.”
   - FR : Le médecin dit : « Génie génétique est normal(e). »
   - AR : يقول الطبيب: «الهندسة الوراثية طبيعي/طبيعية.»
5. **EN** “But the recombinant dna is not normal.”
   - FR : « Mais ADN recombinant n'est pas normal(e). »
   - AR : «لكن الـ DNA المؤشب غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the recombinant dna normal?”
   - FR : Lina demande : « ADN recombinant est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الـ DNA المؤشب طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:genetic-engineering:v1` — **a genetic engineering** · FR : Génie génétique · AR : الهندسة الوراثية
- `gen:restriction-enzyme:v1` — **a restriction enzyme** · FR : Enzyme de restriction (Endonucléase) · AR : إنزيم القطع (الإندونوكلياز)
- `gen:recombinant-dna:v1` — **a recombinant dna** · FR : ADN recombinant · AR : الـ DNA المؤشب

### Questions (acceptées / modèle / hint)
- **q1.** Is the genetic engineering normal?
  - accepté : yes ; yes it is ; the genetic engineering is normal ; it is normal
  - modèle : Yes, the genetic engineering is normal.
- **q2.** Is the recombinant dna normal?
  - accepté : no ; no it is not ; no it isn't ; the recombinant dna is not normal ; it is not normal ; it isn't normal
  - modèle : No, the recombinant dna is not normal.
  - hint (si erreur) — FR : Attention : ADN recombinant n'est PAS normal(e).
- **q3.** Which one is not normal: the genetic engineering or the recombinant dna?
  - accepté : recombinant dna ; the recombinant dna
  - modèle : The recombinant dna is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the restriction enzyme normal?
  - accepté : yes ; yes it is ; the restriction enzyme is normal
  - modèle : Yes, the restriction enzyme is normal.
- **Tt2.** Is the genetic engineering normal?
  - accepté : no ; no it is not ; no it isn't ; the genetic engineering is not normal ; it is not normal
  - modèle : No, the genetic engineering is not normal.
  - hint (si erreur) — FR : Attention : Génie génétique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The genetic engineering … normal. (be)` → is · modèle : The genetic engineering is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The genetic engineering is… / The recombinant dna is not… — modèle : *The genetic engineering is normal. The recombinant dna is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Enzyme de restriction (Endonucléase) normal(e), mais Génie génétique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The restriction enzyme is normal. The genetic engineering is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `genet-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Génie génétique et Enzyme de restriction (Endonucléase) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient.
   - FR : Youssef est un·e patient·e.
   - AR : Youssef مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check genetic engineering.”
   - FR : « D'abord, nous vérifions Génie génétique. »
   - AR : «أولًا، نتحقق من الهندسة الوراثية.»
5. **EN** “Then, we check restriction enzyme.”
   - FR : « Ensuite, nous vérifions Enzyme de restriction (Endonucléase). »
   - AR : «ثم نتحقق من إنزيم القطع (الإندونوكلياز).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:genetic-engineering:v1` — **a genetic engineering** · FR : Génie génétique · AR : الهندسة الوراثية
- `gen:restriction-enzyme:v1` — **a restriction enzyme** · FR : Enzyme de restriction (Endonucléase) · AR : إنزيم القطع (الإندونوكلياز)
- `gen:recombinant-dna:v1` — **a recombinant dna** · FR : ADN recombinant · AR : الـ DNA المؤشب

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : genetic engineering ; we check genetic engineering ; genetic engineering first ; the doctor checks genetic engineering first
  - modèle : First, the doctor checks genetic engineering.
- **q2.** What does the doctor check next?
  - accepté : restriction enzyme ; then we check restriction enzyme ; restriction enzyme next ; the doctor checks restriction enzyme next
  - modèle : Then, the doctor checks restriction enzyme.
- **q3.** Does the doctor check restriction enzyme before genetic engineering?
  - accepté : no ; no he does not ; no he doesn't ; he checks genetic engineering first
  - modèle : No, he checks genetic engineering first, then restriction enzyme.
  - hint (si erreur) — FR : Attention : Génie génétique vient D'ABORD, ensuite Enzyme de restriction (Endonucléase).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : recombinant dna ; recombinant dna first ; he checks recombinant dna first
  - modèle : First, the doctor checks recombinant dna.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check genetic engineering. Then, we check restriction enzyme.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord ADN recombinant, ensuite Génie génétique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check recombinant dna. Then, we check genetic engineering.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `genet-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Génie génétique / Enzyme de restriction (Endonucléase), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the genetic engineering.
   - FR : Le patient du lit 3 a Génie génétique.
   - AR : مريض السرير ٣ عنده الهندسة الوراثية.
4. **EN** The patient in bed 5 has a problem with the restriction enzyme.
   - FR : Le patient du lit 5 a Enzyme de restriction (Endonucléase).
   - AR : مريض السرير ٥ عنده إنزيم القطع (الإندونوكلياز).
5. **EN** The patient in bed 5 does not have a problem with the genetic engineering.
   - FR : Le patient du lit 5 n'a pas de Génie génétique.
   - AR : مريض السرير ٥ ليس عنده الهندسة الوراثية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:genetic-engineering:v1` — **a genetic engineering** · FR : Génie génétique · AR : الهندسة الوراثية
- `gen:restriction-enzyme:v1` — **a restriction enzyme** · FR : Enzyme de restriction (Endonucléase) · AR : إنزيم القطع (الإندونوكلياز)
- `gen:recombinant-dna:v1` — **a recombinant dna** · FR : ADN recombinant · AR : الـ DNA المؤشب

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : genetic engineering ; a genetic engineering ; the patient with genetic engineering
  - modèle : The patient in bed 3 has a genetic engineering.
- **q2.** What does the patient in bed 5 have?
  - accepté : restriction enzyme ; a problem with the restriction enzyme
  - modèle : He has a problem with the restriction enzyme.
- **q3.** Does the patient in bed 5 have genetic engineering?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the genetic engineering ; he doesn't
  - modèle : No, he does not have a problem with the genetic engineering.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS génie génétique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : recombinant dna ; a problem with the recombinant dna
  - modèle : He has a problem with the recombinant dna.
- **Tt2.** Does the patient in bed 9 have restriction enzyme?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the restriction enzyme ; he doesn't
  - modèle : No, he does not have a problem with the restriction enzyme.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS enzyme de restriction (endonucléase).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a genetic engineering and a restriction enzyme. (have)` → have · modèle : The patients have a genetic engineering and a restriction enzyme.
- `The patient in bed 5 … not have a genetic engineering. (do)` → does · modèle : The patient in bed 5 does not have a genetic engineering.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the genetic engineering. The patient in bed 5 has a problem with the restriction enzyme.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a ADN recombinant, le lit 9 a Génie génétique et n'a pas Enzyme de restriction (Endonucléase).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the recombinant dna. The patient in bed 9 has a problem with the genetic engineering.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
