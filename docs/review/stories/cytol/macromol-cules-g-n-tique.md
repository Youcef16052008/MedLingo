# Relecture — Macromolécules & Génétique (cytol)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `cytol-case-003` — Le cas de Amel : Gène anormal(e) / _Amel: the gene is not normal_

**Objectif :** Comprendre des résultats : ADN (Acide désoxyribonucléique) normal(e), Gène anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The DNA is normal.”
   - FR : Le médecin dit : « ADN (Acide désoxyribonucléique) est normal(e). »
   - AR : يقول الطبيب: «الحمض النووي الريبوزي منقوص الأكسجين طبيعي/طبيعية.»
5. **EN** “But the gene is not normal.”
   - FR : « Mais Gène n'est pas normal(e). »
   - AR : «لكن الجين غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the gene normal?”
   - FR : Amine demande : « Gène est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الجين طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:dna:v1` — **a DNA** · FR : ADN (Acide désoxyribonucléique) · AR : الحمض النووي الريبوزي منقوص الأكسجين
- `gen:rna:v1` — **a RNA** · FR : ARN (Acide ribonucléique) · AR : الحمض النووي الريبوزي
- `gen:gene:v1` — **a gene** · FR : Gène · AR : الجين

### Questions (acceptées / modèle / hint)
- **q1.** Is the DNA normal?
  - accepté : yes ; yes it is ; the DNA is normal ; it is normal
  - modèle : Yes, the DNA is normal.
- **q2.** Is the gene normal?
  - accepté : no ; no it is not ; no it isn't ; the gene is not normal ; it is not normal ; it isn't normal
  - modèle : No, the gene is not normal.
  - hint (si erreur) — FR : Attention : Gène n'est PAS normal(e).
- **q3.** Which one is not normal: the DNA or the gene?
  - accepté : gene ; the gene
  - modèle : The gene is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the RNA normal?
  - accepté : yes ; yes it is ; the RNA is normal
  - modèle : Yes, the RNA is normal.
- **Tt2.** Is the DNA normal?
  - accepté : no ; no it is not ; no it isn't ; the DNA is not normal ; it is not normal
  - modèle : No, the DNA is not normal.
  - hint (si erreur) — FR : Attention : ADN (Acide désoxyribonucléique) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The DNA … normal. (be)` → is · modèle : The DNA is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The DNA is… / The gene is not… — modèle : *The DNA is normal. The gene is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : ARN (Acide ribonucléique) normal(e), mais ADN (Acide désoxyribonucléique) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The RNA is normal. The DNA is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `cytol-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique ADN (Acide désoxyribonucléique) et ARN (Acide ribonucléique) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient.
   - FR : Meriem est un·e patient·e.
   - AR : Meriem مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check DNA.”
   - FR : « D'abord, nous vérifions ADN (Acide désoxyribonucléique). »
   - AR : «أولًا، نتحقق من الحمض النووي الريبوزي منقوص الأكسجين.»
5. **EN** “Then, we check RNA.”
   - FR : « Ensuite, nous vérifions ARN (Acide ribonucléique). »
   - AR : «ثم نتحقق من الحمض النووي الريبوزي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:dna:v1` — **a DNA** · FR : ADN (Acide désoxyribonucléique) · AR : الحمض النووي الريبوزي منقوص الأكسجين
- `gen:rna:v1` — **a RNA** · FR : ARN (Acide ribonucléique) · AR : الحمض النووي الريبوزي
- `gen:gene:v1` — **a gene** · FR : Gène · AR : الجين

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : DNA ; we check DNA ; DNA first ; the doctor checks DNA first
  - modèle : First, the doctor checks DNA.
- **q2.** What does the doctor check next?
  - accepté : RNA ; then we check RNA ; RNA next ; the doctor checks RNA next
  - modèle : Then, the doctor checks RNA.
- **q3.** Does the doctor check RNA before DNA?
  - accepté : no ; no he does not ; no he doesn't ; he checks DNA first
  - modèle : No, he checks DNA first, then RNA.
  - hint (si erreur) — FR : Attention : ADN (Acide désoxyribonucléique) vient D'ABORD, ensuite ARN (Acide ribonucléique).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
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
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check DNA. Then, we check RNA.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Gène, ensuite ADN (Acide désoxyribonucléique), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check gene. Then, we check DNA.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `cytol-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (ADN (Acide désoxyribonucléique) / ARN (Acide ribonucléique), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the DNA.
   - FR : Le patient du lit 3 a ADN (Acide désoxyribonucléique).
   - AR : مريض السرير ٣ عنده الحمض النووي الريبوزي منقوص الأكسجين.
4. **EN** The patient in bed 5 has a problem with the RNA.
   - FR : Le patient du lit 5 a ARN (Acide ribonucléique).
   - AR : مريض السرير ٥ عنده الحمض النووي الريبوزي.
5. **EN** The patient in bed 5 does not have a problem with the DNA.
   - FR : Le patient du lit 5 n'a pas de ADN (Acide désoxyribonucléique).
   - AR : مريض السرير ٥ ليس عنده الحمض النووي الريبوزي منقوص الأكسجين.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:dna:v1` — **a DNA** · FR : ADN (Acide désoxyribonucléique) · AR : الحمض النووي الريبوزي منقوص الأكسجين
- `gen:rna:v1` — **a RNA** · FR : ARN (Acide ribonucléique) · AR : الحمض النووي الريبوزي
- `gen:gene:v1` — **a gene** · FR : Gène · AR : الجين

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : DNA ; a DNA ; the patient with DNA
  - modèle : The patient in bed 3 has a DNA.
- **q2.** What does the patient in bed 5 have?
  - accepté : RNA ; a problem with the RNA
  - modèle : He has a problem with the RNA.
- **q3.** Does the patient in bed 5 have DNA?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the DNA ; he doesn't
  - modèle : No, he does not have a problem with the DNA.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS ADN (Acide désoxyribonucléique) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : gene ; a problem with the gene
  - modèle : He has a problem with the gene.
- **Tt2.** Does the patient in bed 9 have RNA?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the RNA ; he doesn't
  - modèle : No, he does not have a problem with the RNA.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS ARN (Acide ribonucléique).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a DNA and a RNA. (have)` → have · modèle : The patients have a DNA and a RNA.
- `The patient in bed 5 … not have a DNA. (do)` → does · modèle : The patient in bed 5 does not have a DNA.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the DNA. The patient in bed 5 has a problem with the RNA.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Gène, le lit 9 a ADN (Acide désoxyribonucléique) et n'a pas ARN (Acide ribonucléique).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the gene. The patient in bed 9 has a problem with the DNA.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
