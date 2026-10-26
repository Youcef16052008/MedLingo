# Relecture — Concepts Fondamentaux (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-001` — Le cas de Nadia : Statistique descriptive anormal(e) / _Nadia: the descriptive statistics is not normal_

**Objectif :** Comprendre des résultats : Biostatistique normal(e), Statistique descriptive anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The biostatistics is normal.”
   - FR : Le médecin dit : « Biostatistique est normal(e). »
   - AR : يقول الطبيب: «الإحصاء الحيوي طبيعي/طبيعية.»
5. **EN** “But the descriptive statistics is not normal.”
   - FR : « Mais Statistique descriptive n'est pas normal(e). »
   - AR : «لكن الإحصاء الوصفي غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the descriptive statistics normal?”
   - FR : Amine demande : « Statistique descriptive est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الإحصاء الوصفي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:biostatistics:v1` — **biostatistics** · FR : Biostatistique · AR : الإحصاء الحيوي
- `gen:statistics:v1` — **statistics** · FR : Statistique · AR : الإحصاء
- `gen:descriptive-statistics:v1` — **descriptive statistics** · FR : Statistique descriptive · AR : الإحصاء الوصفي

### Questions (acceptées / modèle / hint)
- **q1.** Is the biostatistics normal?
  - accepté : yes ; yes it is ; the biostatistics is normal ; it is normal
  - modèle : Yes, the biostatistics is normal.
- **q2.** Is the descriptive statistics normal?
  - accepté : no ; no it is not ; no it isn't ; the descriptive statistics is not normal ; it is not normal ; it isn't normal
  - modèle : No, the descriptive statistics is not normal.
  - hint (si erreur) — FR : Attention : Statistique descriptive n'est PAS normal(e).
- **q3.** Which one is not normal: the biostatistics or the descriptive statistics?
  - accepté : descriptive statistics ; the descriptive statistics
  - modèle : The descriptive statistics is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the statistics normal?
  - accepté : yes ; yes it is ; the statistics is normal
  - modèle : Yes, the statistics is normal.
- **Tt2.** Is the biostatistics normal?
  - accepté : no ; no it is not ; no it isn't ; the biostatistics is not normal ; it is not normal
  - modèle : No, the biostatistics is not normal.
  - hint (si erreur) — FR : Attention : Biostatistique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The biostatistics … normal. (be)` → is · modèle : The biostatistics is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The biostatistics is… / The descriptive statistics is not… — modèle : *The biostatistics is normal. The descriptive statistics is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Statistique normal(e), mais Biostatistique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The statistics is normal. The biostatistics is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Biostatistique et Statistique à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check biostatistics.”
   - FR : « D'abord, nous vérifions Biostatistique. »
   - AR : «أولًا، نتحقق من الإحصاء الحيوي.»
5. **EN** “Then, we check statistics.”
   - FR : « Ensuite, nous vérifions Statistique. »
   - AR : «ثم نتحقق من الإحصاء.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:biostatistics:v1` — **biostatistics** · FR : Biostatistique · AR : الإحصاء الحيوي
- `gen:statistics:v1` — **statistics** · FR : Statistique · AR : الإحصاء
- `gen:descriptive-statistics:v1` — **descriptive statistics** · FR : Statistique descriptive · AR : الإحصاء الوصفي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : biostatistics ; we check biostatistics ; biostatistics first ; the doctor checks biostatistics first
  - modèle : First, the doctor checks biostatistics.
- **q2.** What does the doctor check next?
  - accepté : statistics ; then we check statistics ; statistics next ; the doctor checks statistics next
  - modèle : Then, the doctor checks statistics.
- **q3.** Does the doctor check statistics before biostatistics?
  - accepté : no ; no he does not ; no he doesn't ; he checks biostatistics first
  - modèle : No, he checks biostatistics first, then statistics.
  - hint (si erreur) — FR : Attention : Biostatistique vient D'ABORD, ensuite Statistique.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : descriptive statistics ; descriptive statistics first ; he checks descriptive statistics first
  - modèle : First, the doctor checks descriptive statistics.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check biostatistics. Then, we check statistics.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Statistique descriptive, ensuite Biostatistique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check descriptive statistics. Then, we check biostatistics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Biostatistique / Statistique, lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the biostatistics.
   - FR : Le patient du lit 3 a Biostatistique.
   - AR : مريض السرير ٣ عنده الإحصاء الحيوي.
4. **EN** The patient in bed 5 has a problem with the statistics.
   - FR : Le patient du lit 5 a Statistique.
   - AR : مريض السرير ٥ عنده الإحصاء.
5. **EN** The patient in bed 5 does not have a problem with the biostatistics.
   - FR : Le patient du lit 5 n'a pas de Biostatistique.
   - AR : مريض السرير ٥ ليس عنده الإحصاء الحيوي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:biostatistics:v1` — **biostatistics** · FR : Biostatistique · AR : الإحصاء الحيوي
- `gen:statistics:v1` — **statistics** · FR : Statistique · AR : الإحصاء
- `gen:descriptive-statistics:v1` — **descriptive statistics** · FR : Statistique descriptive · AR : الإحصاء الوصفي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : biostatistics ; the patient with biostatistics
  - modèle : The patient in bed 3 has biostatistics.
- **q2.** What does the patient in bed 5 have?
  - accepté : statistics ; a problem with the statistics
  - modèle : He has a problem with the statistics.
- **q3.** Does the patient in bed 5 have biostatistics?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the biostatistics ; he doesn't
  - modèle : No, he does not have a problem with the biostatistics.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS biostatistique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : descriptive statistics ; a problem with the descriptive statistics
  - modèle : He has a problem with the descriptive statistics.
- **Tt2.** Does the patient in bed 9 have statistics?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the statistics ; he doesn't
  - modèle : No, he does not have a problem with the statistics.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS statistique.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … biostatistics and statistics. (have)` → have · modèle : The patients have biostatistics and statistics.
- `The patient in bed 5 … not have biostatistics. (do)` → does · modèle : The patient in bed 5 does not have biostatistics.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the biostatistics. The patient in bed 5 has a problem with the statistics.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Statistique descriptive, le lit 9 a Biostatistique et n'a pas Statistique.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the descriptive statistics. The patient in bed 9 has a problem with the biostatistics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
