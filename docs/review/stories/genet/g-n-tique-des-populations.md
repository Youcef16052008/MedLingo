# Relecture — Génétique des Populations (genet)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `genet-case-006` — Le cas de Sofiane : Fréquence allélique anormal(e) / _Sofiane: the allele frequency is not normal_

**Objectif :** Comprendre des résultats : Génétique des populations normal(e), Fréquence allélique anormal(e).
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
4. **EN** The doctor says, “The population genetics is normal.”
   - FR : Le médecin dit : « Génétique des populations est normal(e). »
   - AR : يقول الطبيب: «وراثة المجتمعات طبيعي/طبيعية.»
5. **EN** “But the allele frequency is not normal.”
   - FR : « Mais Fréquence allélique n'est pas normal(e). »
   - AR : «لكن تردد الأليل غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the allele frequency normal?”
   - FR : Lina demande : « Fréquence allélique est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل تردد الأليل طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:population-genetics:v1` — **population genetics** · FR : Génétique des populations · AR : وراثة المجتمعات
- `gen:hardy-weinberg-equilibrium:v1` — **a hardy-weinberg equilibrium** · FR : Équilibre de Hardy-Weinberg · AR : توازن هاردي-واينبرغ
- `gen:allele-frequency:v1` — **an allele frequency** · FR : Fréquence allélique · AR : تردد الأليل

### Questions (acceptées / modèle / hint)
- **q1.** Is the population genetics normal?
  - accepté : yes ; yes it is ; the population genetics is normal ; it is normal
  - modèle : Yes, the population genetics is normal.
- **q2.** Is the allele frequency normal?
  - accepté : no ; no it is not ; no it isn't ; the allele frequency is not normal ; it is not normal ; it isn't normal
  - modèle : No, the allele frequency is not normal.
  - hint (si erreur) — FR : Attention : Fréquence allélique n'est PAS normal(e).
- **q3.** Which one is not normal: the population genetics or the allele frequency?
  - accepté : allele frequency ; the allele frequency
  - modèle : The allele frequency is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the hardy-weinberg equilibrium normal?
  - accepté : yes ; yes it is ; the hardy-weinberg equilibrium is normal
  - modèle : Yes, the hardy-weinberg equilibrium is normal.
- **Tt2.** Is the population genetics normal?
  - accepté : no ; no it is not ; no it isn't ; the population genetics is not normal ; it is not normal
  - modèle : No, the population genetics is not normal.
  - hint (si erreur) — FR : Attention : Génétique des populations n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The population genetics … normal. (be)` → is · modèle : The population genetics is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sofiane en deux phrases. Garde la négation. — amorces : The population genetics is… / The allele frequency is not… — modèle : *The population genetics is normal. The allele frequency is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Équilibre de Hardy-Weinberg normal(e), mais Génétique des populations anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The hardy-weinberg equilibrium is normal. The population genetics is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `genet-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Génétique des populations et Équilibre de Hardy-Weinberg à un patient.
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
4. **EN** “First, we check population genetics.”
   - FR : « D'abord, nous vérifions Génétique des populations. »
   - AR : «أولًا، نتحقق من وراثة المجتمعات.»
5. **EN** “Then, we check hardy-weinberg equilibrium.”
   - FR : « Ensuite, nous vérifions Équilibre de Hardy-Weinberg. »
   - AR : «ثم نتحقق من توازن هاردي-واينبرغ.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:population-genetics:v1` — **population genetics** · FR : Génétique des populations · AR : وراثة المجتمعات
- `gen:hardy-weinberg-equilibrium:v1` — **a hardy-weinberg equilibrium** · FR : Équilibre de Hardy-Weinberg · AR : توازن هاردي-واينبرغ
- `gen:allele-frequency:v1` — **an allele frequency** · FR : Fréquence allélique · AR : تردد الأليل

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : population genetics ; we check population genetics ; population genetics first ; the doctor checks population genetics first
  - modèle : First, the doctor checks population genetics.
- **q2.** What does the doctor check next?
  - accepté : hardy-weinberg equilibrium ; then we check hardy-weinberg equilibrium ; hardy-weinberg equilibrium next ; the doctor checks hardy-weinberg equilibrium next
  - modèle : Then, the doctor checks hardy-weinberg equilibrium.
- **q3.** Does the doctor check hardy-weinberg equilibrium before population genetics?
  - accepté : no ; no he does not ; no he doesn't ; he checks population genetics first
  - modèle : No, he checks population genetics first, then hardy-weinberg equilibrium.
  - hint (si erreur) — FR : Attention : Génétique des populations vient D'ABORD, ensuite Équilibre de Hardy-Weinberg.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : allele frequency ; allele frequency first ; he checks allele frequency first
  - modèle : First, the doctor checks allele frequency.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check population genetics. Then, we check hardy-weinberg equilibrium.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fréquence allélique, ensuite Génétique des populations, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check allele frequency. Then, we check population genetics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `genet-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Génétique des populations / Équilibre de Hardy-Weinberg, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the population genetics.
   - FR : Le patient du lit 3 a Génétique des populations.
   - AR : مريض السرير ٣ عنده وراثة المجتمعات.
4. **EN** The patient in bed 5 has a problem with the hardy-weinberg equilibrium.
   - FR : Le patient du lit 5 a Équilibre de Hardy-Weinberg.
   - AR : مريض السرير ٥ عنده توازن هاردي-واينبرغ.
5. **EN** The patient in bed 5 does not have a problem with the population genetics.
   - FR : Le patient du lit 5 n'a pas de Génétique des populations.
   - AR : مريض السرير ٥ ليس عنده وراثة المجتمعات.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:population-genetics:v1` — **population genetics** · FR : Génétique des populations · AR : وراثة المجتمعات
- `gen:hardy-weinberg-equilibrium:v1` — **a hardy-weinberg equilibrium** · FR : Équilibre de Hardy-Weinberg · AR : توازن هاردي-واينبرغ
- `gen:allele-frequency:v1` — **an allele frequency** · FR : Fréquence allélique · AR : تردد الأليل

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : population genetics ; the patient with population genetics
  - modèle : The patient in bed 3 has population genetics.
- **q2.** What does the patient in bed 5 have?
  - accepté : hardy-weinberg equilibrium ; a problem with the hardy-weinberg equilibrium
  - modèle : He has a problem with the hardy-weinberg equilibrium.
- **q3.** Does the patient in bed 5 have population genetics?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the population genetics ; he doesn't
  - modèle : No, he does not have a problem with the population genetics.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS génétique des populations — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : allele frequency ; a problem with the allele frequency
  - modèle : He has a problem with the allele frequency.
- **Tt2.** Does the patient in bed 9 have hardy-weinberg equilibrium?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the hardy-weinberg equilibrium ; he doesn't
  - modèle : No, he does not have a problem with the hardy-weinberg equilibrium.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS équilibre de hardy-weinberg.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … population genetics and a hardy-weinberg equilibrium. (have)` → have · modèle : The patients have population genetics and a hardy-weinberg equilibrium.
- `The patient in bed 5 … not have population genetics. (do)` → does · modèle : The patient in bed 5 does not have population genetics.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the population genetics. The patient in bed 5 has a problem with the hardy-weinberg equilibrium.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Fréquence allélique, le lit 9 a Génétique des populations et n'a pas Équilibre de Hardy-Weinberg.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the allele frequency. The patient in bed 9 has a problem with the population genetics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
