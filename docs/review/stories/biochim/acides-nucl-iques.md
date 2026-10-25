# Relecture — Acides Nucléiques (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-001` — Le cas de Nadia : ARN (Acide ribonucléique) anormal(e) / _Nadia: the RNA is not normal_

**Objectif :** Comprendre des résultats : Acide nucléique normal(e), ARN (Acide ribonucléique) anormal(e).
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
4. **EN** The doctor says, “The nucleic acid is normal.”
   - FR : Le médecin dit : « Acide nucléique est normal(e). »
   - AR : يقول الطبيب: «الحمض النووي طبيعي/طبيعية.»
5. **EN** “But the RNA is not normal.”
   - FR : « Mais ARN (Acide ribonucléique) n'est pas normal(e). »
   - AR : «لكن الحمض النووي الريبي غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the RNA normal?”
   - FR : Amine demande : « ARN (Acide ribonucléique) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الحمض النووي الريبي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:nucleic-acid:v1` — **a nucleic acid** · FR : Acide nucléique · AR : الحمض النووي
- `gen:dna:v1` — **a DNA** · FR : ADN (Acide désoxyribonucléique) · AR : الحمض النووي الريبي منقوص الأكسجين
- `gen:rna:v1` — **a RNA** · FR : ARN (Acide ribonucléique) · AR : الحمض النووي الريبي

### Questions (acceptées / modèle / hint)
- **q1.** Is the nucleic acid normal?
  - accepté : yes ; yes it is ; the nucleic acid is normal ; it is normal
  - modèle : Yes, the nucleic acid is normal.
- **q2.** Is the RNA normal?
  - accepté : no ; no it is not ; no it isn't ; the RNA is not normal ; it is not normal ; it isn't normal
  - modèle : No, the RNA is not normal.
  - hint (si erreur) — FR : Attention : ARN (Acide ribonucléique) n'est PAS normal(e).
- **q3.** Which one is not normal: the nucleic acid or the RNA?
  - accepté : RNA ; the RNA
  - modèle : The RNA is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the DNA normal?
  - accepté : yes ; yes it is ; the DNA is normal
  - modèle : Yes, the DNA is normal.
- **Tt2.** Is the nucleic acid normal?
  - accepté : no ; no it is not ; no it isn't ; the nucleic acid is not normal ; it is not normal
  - modèle : No, the nucleic acid is not normal.
  - hint (si erreur) — FR : Attention : Acide nucléique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The nucleic acid … normal. (be)` → is · modèle : The nucleic acid is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The nucleic acid is… / The RNA is not… — modèle : *The nucleic acid is normal. The RNA is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : ADN (Acide désoxyribonucléique) normal(e), mais Acide nucléique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The DNA is normal. The nucleic acid is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Acide nucléique et ADN (Acide désoxyribonucléique) à un patient.
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
4. **EN** “First, we check nucleic acid.”
   - FR : « D'abord, nous vérifions Acide nucléique. »
   - AR : «أولًا، نتحقق من الحمض النووي.»
5. **EN** “Then, we check DNA.”
   - FR : « Ensuite, nous vérifions ADN (Acide désoxyribonucléique). »
   - AR : «ثم نتحقق من الحمض النووي الريبي منقوص الأكسجين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:nucleic-acid:v1` — **a nucleic acid** · FR : Acide nucléique · AR : الحمض النووي
- `gen:dna:v1` — **a DNA** · FR : ADN (Acide désoxyribonucléique) · AR : الحمض النووي الريبي منقوص الأكسجين
- `gen:rna:v1` — **a RNA** · FR : ARN (Acide ribonucléique) · AR : الحمض النووي الريبي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : nucleic acid ; we check nucleic acid ; nucleic acid first ; the doctor checks nucleic acid first
  - modèle : First, the doctor checks nucleic acid.
- **q2.** What does the doctor check next?
  - accepté : DNA ; then we check DNA ; DNA next ; the doctor checks DNA next
  - modèle : Then, the doctor checks DNA.
- **q3.** Does the doctor check DNA before nucleic acid?
  - accepté : no ; no he does not ; no he doesn't ; he checks nucleic acid first
  - modèle : No, he checks nucleic acid first, then DNA.
  - hint (si erreur) — FR : Attention : Acide nucléique vient D'ABORD, ensuite ADN (Acide désoxyribonucléique).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : RNA ; RNA first ; he checks RNA first
  - modèle : First, the doctor checks RNA.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check nucleic acid. Then, we check DNA.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord ARN (Acide ribonucléique), ensuite Acide nucléique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check RNA. Then, we check nucleic acid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Acide nucléique / ADN (Acide désoxyribonucléique), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the nucleic acid.
   - FR : Le patient du lit 3 a Acide nucléique.
   - AR : مريض السرير ٣ عنده الحمض النووي.
4. **EN** The patient in bed 5 has a problem with the DNA.
   - FR : Le patient du lit 5 a ADN (Acide désoxyribonucléique).
   - AR : مريض السرير ٥ عنده الحمض النووي الريبي منقوص الأكسجين.
5. **EN** The patient in bed 5 does not have a problem with the nucleic acid.
   - FR : Le patient du lit 5 n'a pas de Acide nucléique.
   - AR : مريض السرير ٥ ليس عنده الحمض النووي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:nucleic-acid:v1` — **a nucleic acid** · FR : Acide nucléique · AR : الحمض النووي
- `gen:dna:v1` — **a DNA** · FR : ADN (Acide désoxyribonucléique) · AR : الحمض النووي الريبي منقوص الأكسجين
- `gen:rna:v1` — **a RNA** · FR : ARN (Acide ribonucléique) · AR : الحمض النووي الريبي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : nucleic acid ; a nucleic acid ; the patient with nucleic acid
  - modèle : The patient in bed 3 has a nucleic acid.
- **q2.** What does the patient in bed 5 have?
  - accepté : DNA ; a problem with the DNA
  - modèle : He has a problem with the DNA.
- **q3.** Does the patient in bed 5 have nucleic acid?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the nucleic acid ; he doesn't
  - modèle : No, he does not have a problem with the nucleic acid.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS acide nucléique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : RNA ; a problem with the RNA
  - modèle : He has a problem with the RNA.
- **Tt2.** Does the patient in bed 9 have DNA?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the DNA ; he doesn't
  - modèle : No, he does not have a problem with the DNA.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS ADN (Acide désoxyribonucléique).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a nucleic acid and a DNA. (have)` → have · modèle : The patients have a nucleic acid and a DNA.
- `The patient in bed 5 … not have a nucleic acid. (do)` → does · modèle : The patient in bed 5 does not have a nucleic acid.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the nucleic acid. The patient in bed 5 has a problem with the DNA.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a ARN (Acide ribonucléique), le lit 9 a Acide nucléique et n'a pas ADN (Acide désoxyribonucléique).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the RNA. The patient in bed 9 has a problem with the nucleic acid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
