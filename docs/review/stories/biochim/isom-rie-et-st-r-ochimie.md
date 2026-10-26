# Relecture — Isomérie et Stéréochimie (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-013` — Le cas de Nadia : Isomère géométrique (Cis-Trans) anormal(e) / _Nadia: the geometric isomer is not normal_

**Objectif :** Comprendre des résultats : Isomère de constitution (structural) normal(e), Isomère géométrique (Cis-Trans) anormal(e).
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
4. **EN** The doctor says, “The structural isomer is normal.”
   - FR : Le médecin dit : « Isomère de constitution (structural) est normal(e). »
   - AR : يقول الطبيب: «المتشكل البنائي طبيعي/طبيعية.»
5. **EN** “But the geometric isomer is not normal.”
   - FR : « Mais Isomère géométrique (Cis-Trans) n'est pas normal(e). »
   - AR : «لكن المتشكل الهندسي غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the geometric isomer normal?”
   - FR : Amine demande : « Isomère géométrique (Cis-Trans) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل المتشكل الهندسي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:structural-isomer:v1` — **a structural isomer** · FR : Isomère de constitution (structural) · AR : المتشكل البنائي
- `gen:stereoisomer:v1` — **a stereoisomer** · FR : Stéréo-isomère · AR : المتشكل الفراغي
- `gen:geometric-isomer:v1` — **a geometric isomer** · FR : Isomère géométrique (Cis-Trans) · AR : المتشكل الهندسي

### Questions (acceptées / modèle / hint)
- **q1.** Is the structural isomer normal?
  - accepté : yes ; yes it is ; the structural isomer is normal ; it is normal
  - modèle : Yes, the structural isomer is normal.
- **q2.** Is the geometric isomer normal?
  - accepté : no ; no it is not ; no it isn't ; the geometric isomer is not normal ; it is not normal ; it isn't normal
  - modèle : No, the geometric isomer is not normal.
  - hint (si erreur) — FR : Attention : Isomère géométrique (Cis-Trans) n'est PAS normal(e).
- **q3.** Which one is not normal: the structural isomer or the geometric isomer?
  - accepté : geometric isomer ; the geometric isomer
  - modèle : The geometric isomer is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the stereoisomer normal?
  - accepté : yes ; yes it is ; the stereoisomer is normal
  - modèle : Yes, the stereoisomer is normal.
- **Tt2.** Is the structural isomer normal?
  - accepté : no ; no it is not ; no it isn't ; the structural isomer is not normal ; it is not normal
  - modèle : No, the structural isomer is not normal.
  - hint (si erreur) — FR : Attention : Isomère de constitution (structural) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The structural isomer … normal. (be)` → is · modèle : The structural isomer is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The structural isomer is… / The geometric isomer is not… — modèle : *The structural isomer is normal. The geometric isomer is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Stéréo-isomère normal(e), mais Isomère de constitution (structural) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The stereoisomer is normal. The structural isomer is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-013` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Isomère de constitution (structural) et Stéréo-isomère à un patient.
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
4. **EN** “First, we check structural isomer.”
   - FR : « D'abord, nous vérifions Isomère de constitution (structural). »
   - AR : «أولًا، نتحقق من المتشكل البنائي.»
5. **EN** “Then, we check stereoisomer.”
   - FR : « Ensuite, nous vérifions Stéréo-isomère. »
   - AR : «ثم نتحقق من المتشكل الفراغي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:structural-isomer:v1` — **a structural isomer** · FR : Isomère de constitution (structural) · AR : المتشكل البنائي
- `gen:stereoisomer:v1` — **a stereoisomer** · FR : Stéréo-isomère · AR : المتشكل الفراغي
- `gen:geometric-isomer:v1` — **a geometric isomer** · FR : Isomère géométrique (Cis-Trans) · AR : المتشكل الهندسي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : structural isomer ; we check structural isomer ; structural isomer first ; the doctor checks structural isomer first
  - modèle : First, the doctor checks structural isomer.
- **q2.** What does the doctor check next?
  - accepté : stereoisomer ; then we check stereoisomer ; stereoisomer next ; the doctor checks stereoisomer next
  - modèle : Then, the doctor checks stereoisomer.
- **q3.** Does the doctor check stereoisomer before structural isomer?
  - accepté : no ; no he does not ; no he doesn't ; he checks structural isomer first
  - modèle : No, he checks structural isomer first, then stereoisomer.
  - hint (si erreur) — FR : Attention : Isomère de constitution (structural) vient D'ABORD, ensuite Stéréo-isomère.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : geometric isomer ; geometric isomer first ; he checks geometric isomer first
  - modèle : First, the doctor checks geometric isomer.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check structural isomer. Then, we check stereoisomer.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Isomère géométrique (Cis-Trans), ensuite Isomère de constitution (structural), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check geometric isomer. Then, we check structural isomer.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-013` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Isomère de constitution (structural) / Stéréo-isomère, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the structural isomer.
   - FR : Le patient du lit 3 a Isomère de constitution (structural).
   - AR : مريض السرير ٣ عنده المتشكل البنائي.
4. **EN** The patient in bed 5 has a problem with the stereoisomer.
   - FR : Le patient du lit 5 a Stéréo-isomère.
   - AR : مريض السرير ٥ عنده المتشكل الفراغي.
5. **EN** The patient in bed 5 does not have a problem with the structural isomer.
   - FR : Le patient du lit 5 n'a pas de Isomère de constitution (structural).
   - AR : مريض السرير ٥ ليس عنده المتشكل البنائي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:structural-isomer:v1` — **a structural isomer** · FR : Isomère de constitution (structural) · AR : المتشكل البنائي
- `gen:stereoisomer:v1` — **a stereoisomer** · FR : Stéréo-isomère · AR : المتشكل الفراغي
- `gen:geometric-isomer:v1` — **a geometric isomer** · FR : Isomère géométrique (Cis-Trans) · AR : المتشكل الهندسي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : structural isomer ; a structural isomer ; the patient with structural isomer
  - modèle : The patient in bed 3 has a structural isomer.
- **q2.** What does the patient in bed 5 have?
  - accepté : stereoisomer ; a problem with the stereoisomer
  - modèle : He has a problem with the stereoisomer.
- **q3.** Does the patient in bed 5 have structural isomer?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the structural isomer ; he doesn't
  - modèle : No, he does not have a problem with the structural isomer.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS isomère de constitution (structural) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : geometric isomer ; a problem with the geometric isomer
  - modèle : He has a problem with the geometric isomer.
- **Tt2.** Does the patient in bed 9 have stereoisomer?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the stereoisomer ; he doesn't
  - modèle : No, he does not have a problem with the stereoisomer.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS stéréo-isomère.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a structural isomer and a stereoisomer. (have)` → have · modèle : The patients have a structural isomer and a stereoisomer.
- `The patient in bed 5 … not have a structural isomer. (do)` → does · modèle : The patient in bed 5 does not have a structural isomer.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the structural isomer. The patient in bed 5 has a problem with the stereoisomer.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Isomère géométrique (Cis-Trans), le lit 9 a Isomère de constitution (structural) et n'a pas Stéréo-isomère.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the geometric isomer. The patient in bed 9 has a problem with the structural isomer.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
