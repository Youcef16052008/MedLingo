# Relecture — Épigénétique (genet)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `genet-case-015` — Le cas de Amel : Modification des histones anormal(e) / _Amel: the histone modification is not normal_

**Objectif :** Comprendre des résultats : Épigénétique normal(e), Modification des histones anormal(e).
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
4. **EN** The doctor says, “The epigenetics is normal.”
   - FR : Le médecin dit : « Épigénétique est normal(e). »
   - AR : يقول الطبيب: «علم الوراثة فوق الجينية طبيعي/طبيعية.»
5. **EN** “But the histone modification is not normal.”
   - FR : « Mais Modification des histones n'est pas normal(e). »
   - AR : «لكن تعديل الهيستونات غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the histone modification normal?”
   - FR : Amine demande : « Modification des histones est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل تعديل الهيستونات طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:epigenetics:v1` — **epigenetics** · FR : Épigénétique · AR : علم الوراثة فوق الجينية
- `gen:dna-methylation:v1` — **a DNA methylation** · FR : Méthylation de l'ADN · AR : مثيلة DNA
- `gen:histone-modification:v1` — **a histone modification** · FR : Modification des histones · AR : تعديل الهيستونات

### Questions (acceptées / modèle / hint)
- **q1.** Is the epigenetics normal?
  - accepté : yes ; yes it is ; the epigenetics is normal ; it is normal
  - modèle : Yes, the epigenetics is normal.
- **q2.** Is the histone modification normal?
  - accepté : no ; no it is not ; no it isn't ; the histone modification is not normal ; it is not normal ; it isn't normal
  - modèle : No, the histone modification is not normal.
  - hint (si erreur) — FR : Attention : Modification des histones n'est PAS normal(e).
- **q3.** Which one is not normal: the epigenetics or the histone modification?
  - accepté : histone modification ; the histone modification
  - modèle : The histone modification is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the DNA methylation normal?
  - accepté : yes ; yes it is ; the DNA methylation is normal
  - modèle : Yes, the DNA methylation is normal.
- **Tt2.** Is the epigenetics normal?
  - accepté : no ; no it is not ; no it isn't ; the epigenetics is not normal ; it is not normal
  - modèle : No, the epigenetics is not normal.
  - hint (si erreur) — FR : Attention : Épigénétique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The epigenetics … normal. (be)` → is · modèle : The epigenetics is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The epigenetics is… / The histone modification is not… — modèle : *The epigenetics is normal. The histone modification is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Méthylation de l'ADN normal(e), mais Épigénétique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The DNA methylation is normal. The epigenetics is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `genet-explain-015` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Épigénétique et Méthylation de l'ADN à un patient.
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
4. **EN** “First, we check epigenetics.”
   - FR : « D'abord, nous vérifions Épigénétique. »
   - AR : «أولًا، نتحقق من علم الوراثة فوق الجينية.»
5. **EN** “Then, we check DNA methylation.”
   - FR : « Ensuite, nous vérifions Méthylation de l'ADN. »
   - AR : «ثم نتحقق من مثيلة DNA.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:epigenetics:v1` — **epigenetics** · FR : Épigénétique · AR : علم الوراثة فوق الجينية
- `gen:dna-methylation:v1` — **a DNA methylation** · FR : Méthylation de l'ADN · AR : مثيلة DNA
- `gen:histone-modification:v1` — **a histone modification** · FR : Modification des histones · AR : تعديل الهيستونات

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : epigenetics ; we check epigenetics ; epigenetics first ; the doctor checks epigenetics first
  - modèle : First, the doctor checks epigenetics.
- **q2.** What does the doctor check next?
  - accepté : DNA methylation ; then we check DNA methylation ; DNA methylation next ; the doctor checks DNA methylation next
  - modèle : Then, the doctor checks DNA methylation.
- **q3.** Does the doctor check DNA methylation before epigenetics?
  - accepté : no ; no he does not ; no he doesn't ; he checks epigenetics first
  - modèle : No, he checks epigenetics first, then DNA methylation.
  - hint (si erreur) — FR : Attention : Épigénétique vient D'ABORD, ensuite Méthylation de l'ADN.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : histone modification ; histone modification first ; he checks histone modification first
  - modèle : First, the doctor checks histone modification.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check epigenetics. Then, we check DNA methylation.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Modification des histones, ensuite Épigénétique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check histone modification. Then, we check epigenetics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `genet-handover-015` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Épigénétique / Méthylation de l'ADN, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the epigenetics.
   - FR : Le patient du lit 3 a Épigénétique.
   - AR : مريض السرير ٣ عنده علم الوراثة فوق الجينية.
4. **EN** The patient in bed 5 has a problem with the DNA methylation.
   - FR : Le patient du lit 5 a Méthylation de l'ADN.
   - AR : مريض السرير ٥ عنده مثيلة DNA.
5. **EN** The patient in bed 5 does not have a problem with the epigenetics.
   - FR : Le patient du lit 5 n'a pas de Épigénétique.
   - AR : مريض السرير ٥ ليس عنده علم الوراثة فوق الجينية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:epigenetics:v1` — **epigenetics** · FR : Épigénétique · AR : علم الوراثة فوق الجينية
- `gen:dna-methylation:v1` — **a DNA methylation** · FR : Méthylation de l'ADN · AR : مثيلة DNA
- `gen:histone-modification:v1` — **a histone modification** · FR : Modification des histones · AR : تعديل الهيستونات

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : epigenetics ; the patient with epigenetics
  - modèle : The patient in bed 3 has epigenetics.
- **q2.** What does the patient in bed 5 have?
  - accepté : DNA methylation ; a problem with the DNA methylation
  - modèle : He has a problem with the DNA methylation.
- **q3.** Does the patient in bed 5 have epigenetics?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the epigenetics ; he doesn't
  - modèle : No, he does not have a problem with the epigenetics.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS épigénétique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : histone modification ; a problem with the histone modification
  - modèle : He has a problem with the histone modification.
- **Tt2.** Does the patient in bed 9 have DNA methylation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the DNA methylation ; he doesn't
  - modèle : No, he does not have a problem with the DNA methylation.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS méthylation de l'adn.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … epigenetics and a DNA methylation. (have)` → have · modèle : The patients have epigenetics and a DNA methylation.
- `The patient in bed 5 … not have epigenetics. (do)` → does · modèle : The patient in bed 5 does not have epigenetics.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the epigenetics. The patient in bed 5 has a problem with the DNA methylation.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Modification des histones, le lit 9 a Épigénétique et n'a pas Méthylation de l'ADN.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the histone modification. The patient in bed 9 has a problem with the epigenetics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
