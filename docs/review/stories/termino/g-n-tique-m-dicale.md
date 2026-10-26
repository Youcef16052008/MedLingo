# Relecture — Génétique Médicale (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-016` — Le cas de Farid : Gène anormal(e) / _Farid: the gene is not normal_

**Objectif :** Comprendre des résultats : Génétique normal(e), Gène anormal(e).
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
4. **EN** The doctor says, “The genetics is normal.”
   - FR : Le médecin dit : « Génétique est normal(e). »
   - AR : يقول الطبيب: «علم الوراثة طبيعي/طبيعية.»
5. **EN** “But the gene is not normal.”
   - FR : « Mais Gène n'est pas normal(e). »
   - AR : «لكن الجين غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the gene normal?”
   - FR : Lina demande : « Gène est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الجين طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:genetics:v1` — **genetics** · FR : Génétique · AR : علم الوراثة
- `gen:chromosome:v1` — **a chromosome** · FR : Chromosome · AR : الصبغي (الكروموسوم)
- `gen:gene:v1` — **a gene** · FR : Gène · AR : الجين

### Questions (acceptées / modèle / hint)
- **q1.** Is the genetics normal?
  - accepté : yes ; yes it is ; the genetics is normal ; it is normal
  - modèle : Yes, the genetics is normal.
- **q2.** Is the gene normal?
  - accepté : no ; no it is not ; no it isn't ; the gene is not normal ; it is not normal ; it isn't normal
  - modèle : No, the gene is not normal.
  - hint (si erreur) — FR : Attention : Gène n'est PAS normal(e).
- **q3.** Which one is not normal: the genetics or the gene?
  - accepté : gene ; the gene
  - modèle : The gene is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the chromosome normal?
  - accepté : yes ; yes it is ; the chromosome is normal
  - modèle : Yes, the chromosome is normal.
- **Tt2.** Is the genetics normal?
  - accepté : no ; no it is not ; no it isn't ; the genetics is not normal ; it is not normal
  - modèle : No, the genetics is not normal.
  - hint (si erreur) — FR : Attention : Génétique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The genetics … normal. (be)` → is · modèle : The genetics is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The genetics is… / The gene is not… — modèle : *The genetics is normal. The gene is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Chromosome normal(e), mais Génétique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The chromosome is normal. The genetics is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-016` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Génétique et Chromosome à un patient.
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
4. **EN** “First, we check genetics.”
   - FR : « D'abord, nous vérifions Génétique. »
   - AR : «أولًا، نتحقق من علم الوراثة.»
5. **EN** “Then, we check chromosome.”
   - FR : « Ensuite, nous vérifions Chromosome. »
   - AR : «ثم نتحقق من الصبغي (الكروموسوم).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:genetics:v1` — **genetics** · FR : Génétique · AR : علم الوراثة
- `gen:chromosome:v1` — **a chromosome** · FR : Chromosome · AR : الصبغي (الكروموسوم)
- `gen:gene:v1` — **a gene** · FR : Gène · AR : الجين

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : genetics ; we check genetics ; genetics first ; the doctor checks genetics first
  - modèle : First, the doctor checks genetics.
- **q2.** What does the doctor check next?
  - accepté : chromosome ; then we check chromosome ; chromosome next ; the doctor checks chromosome next
  - modèle : Then, the doctor checks chromosome.
- **q3.** Does the doctor check chromosome before genetics?
  - accepté : no ; no he does not ; no he doesn't ; he checks genetics first
  - modèle : No, he checks genetics first, then chromosome.
  - hint (si erreur) — FR : Attention : Génétique vient D'ABORD, ensuite Chromosome.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : gene ; gene first ; he checks gene first
  - modèle : First, the doctor checks gene.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check genetics. Then, we check chromosome.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Gène, ensuite Génétique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check gene. Then, we check genetics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-016` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Génétique / Chromosome, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the genetics.
   - FR : Le patient du lit 3 a Génétique.
   - AR : مريض السرير ٣ عنده علم الوراثة.
4. **EN** The patient in bed 5 has a problem with the chromosome.
   - FR : Le patient du lit 5 a Chromosome.
   - AR : مريض السرير ٥ عنده الصبغي (الكروموسوم).
5. **EN** The patient in bed 5 does not have a problem with the genetics.
   - FR : Le patient du lit 5 n'a pas de Génétique.
   - AR : مريض السرير ٥ ليس عنده علم الوراثة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:genetics:v1` — **genetics** · FR : Génétique · AR : علم الوراثة
- `gen:chromosome:v1` — **a chromosome** · FR : Chromosome · AR : الصبغي (الكروموسوم)
- `gen:gene:v1` — **a gene** · FR : Gène · AR : الجين

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : genetics ; the patient with genetics
  - modèle : The patient in bed 3 has genetics.
- **q2.** What does the patient in bed 5 have?
  - accepté : chromosome ; a problem with the chromosome
  - modèle : He has a problem with the chromosome.
- **q3.** Does the patient in bed 5 have genetics?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the genetics ; he doesn't
  - modèle : No, he does not have a problem with the genetics.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS génétique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : gene ; a problem with the gene
  - modèle : He has a problem with the gene.
- **Tt2.** Does the patient in bed 9 have chromosome?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the chromosome ; he doesn't
  - modèle : No, he does not have a problem with the chromosome.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS chromosome.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … genetics and a chromosome. (have)` → have · modèle : The patients have genetics and a chromosome.
- `The patient in bed 5 … not have genetics. (do)` → does · modèle : The patient in bed 5 does not have genetics.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the genetics. The patient in bed 5 has a problem with the chromosome.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Gène, le lit 9 a Génétique et n'a pas Chromosome.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the gene. The patient in bed 9 has a problem with the genetics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
