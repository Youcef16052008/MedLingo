# Relecture — Biomécanique (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-001` — Le cas de Nadia : Pivot (Point d'appui) anormal(e) / _Nadia: the fulcrum is not normal_

**Objectif :** Comprendre des résultats : Biomécanique normal(e), Pivot (Point d'appui) anormal(e).
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
4. **EN** The doctor says, “The biomechanics is normal.”
   - FR : Le médecin dit : « Biomécanique est normal(e). »
   - AR : يقول الطبيب: «الميكانيكا الحيوية طبيعي/طبيعية.»
5. **EN** “But the fulcrum is not normal.”
   - FR : « Mais Pivot (Point d'appui) n'est pas normal(e). »
   - AR : «لكن نقطة الارتكاز غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the fulcrum normal?”
   - FR : Amine demande : « Pivot (Point d'appui) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل نقطة الارتكاز طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:biomechanics:v1` — **biomechanics** · FR : Biomécanique · AR : الميكانيكا الحيوية
- `gen:lever:v1` — **a lever** · FR : Levier · AR : الرافعة
- `gen:fulcrum:v1` — **a fulcrum** · FR : Pivot (Point d'appui) · AR : نقطة الارتكاز

### Questions (acceptées / modèle / hint)
- **q1.** Is the biomechanics normal?
  - accepté : yes ; yes it is ; the biomechanics is normal ; it is normal
  - modèle : Yes, the biomechanics is normal.
- **q2.** Is the fulcrum normal?
  - accepté : no ; no it is not ; no it isn't ; the fulcrum is not normal ; it is not normal ; it isn't normal
  - modèle : No, the fulcrum is not normal.
  - hint (si erreur) — FR : Attention : Pivot (Point d'appui) n'est PAS normal(e).
- **q3.** Which one is not normal: the biomechanics or the fulcrum?
  - accepté : fulcrum ; the fulcrum
  - modèle : The fulcrum is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the lever normal?
  - accepté : yes ; yes it is ; the lever is normal
  - modèle : Yes, the lever is normal.
- **Tt2.** Is the biomechanics normal?
  - accepté : no ; no it is not ; no it isn't ; the biomechanics is not normal ; it is not normal
  - modèle : No, the biomechanics is not normal.
  - hint (si erreur) — FR : Attention : Biomécanique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The biomechanics … normal. (be)` → is · modèle : The biomechanics is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The biomechanics is… / The fulcrum is not… — modèle : *The biomechanics is normal. The fulcrum is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Levier normal(e), mais Biomécanique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The lever is normal. The biomechanics is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Biomécanique et Levier à un patient.
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
4. **EN** “First, we check biomechanics.”
   - FR : « D'abord, nous vérifions Biomécanique. »
   - AR : «أولًا، نتحقق من الميكانيكا الحيوية.»
5. **EN** “Then, we check lever.”
   - FR : « Ensuite, nous vérifions Levier. »
   - AR : «ثم نتحقق من الرافعة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:biomechanics:v1` — **biomechanics** · FR : Biomécanique · AR : الميكانيكا الحيوية
- `gen:lever:v1` — **a lever** · FR : Levier · AR : الرافعة
- `gen:fulcrum:v1` — **a fulcrum** · FR : Pivot (Point d'appui) · AR : نقطة الارتكاز

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : biomechanics ; we check biomechanics ; biomechanics first ; the doctor checks biomechanics first
  - modèle : First, the doctor checks biomechanics.
- **q2.** What does the doctor check next?
  - accepté : lever ; then we check lever ; lever next ; the doctor checks lever next
  - modèle : Then, the doctor checks lever.
- **q3.** Does the doctor check lever before biomechanics?
  - accepté : no ; no he does not ; no he doesn't ; he checks biomechanics first
  - modèle : No, he checks biomechanics first, then lever.
  - hint (si erreur) — FR : Attention : Biomécanique vient D'ABORD, ensuite Levier.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : fulcrum ; fulcrum first ; he checks fulcrum first
  - modèle : First, the doctor checks fulcrum.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check biomechanics. Then, we check lever.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Pivot (Point d'appui), ensuite Biomécanique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check fulcrum. Then, we check biomechanics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Biomécanique / Levier, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the biomechanics.
   - FR : Le patient du lit 3 a Biomécanique.
   - AR : مريض السرير ٣ عنده الميكانيكا الحيوية.
4. **EN** The patient in bed 5 has a problem with the lever.
   - FR : Le patient du lit 5 a Levier.
   - AR : مريض السرير ٥ عنده الرافعة.
5. **EN** The patient in bed 5 does not have a problem with the biomechanics.
   - FR : Le patient du lit 5 n'a pas de Biomécanique.
   - AR : مريض السرير ٥ ليس عنده الميكانيكا الحيوية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:biomechanics:v1` — **biomechanics** · FR : Biomécanique · AR : الميكانيكا الحيوية
- `gen:lever:v1` — **a lever** · FR : Levier · AR : الرافعة
- `gen:fulcrum:v1` — **a fulcrum** · FR : Pivot (Point d'appui) · AR : نقطة الارتكاز

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : biomechanics ; the patient with biomechanics
  - modèle : The patient in bed 3 has biomechanics.
- **q2.** What does the patient in bed 5 have?
  - accepté : lever ; a problem with the lever
  - modèle : He has a problem with the lever.
- **q3.** Does the patient in bed 5 have biomechanics?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the biomechanics ; he doesn't
  - modèle : No, he does not have a problem with the biomechanics.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS biomécanique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : fulcrum ; a problem with the fulcrum
  - modèle : He has a problem with the fulcrum.
- **Tt2.** Does the patient in bed 9 have lever?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the lever ; he doesn't
  - modèle : No, he does not have a problem with the lever.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS levier.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … biomechanics and a lever. (have)` → have · modèle : The patients have biomechanics and a lever.
- `The patient in bed 5 … not have biomechanics. (do)` → does · modèle : The patient in bed 5 does not have biomechanics.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the biomechanics. The patient in bed 5 has a problem with the lever.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Pivot (Point d'appui), le lit 9 a Biomécanique et n'a pas Levier.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the fulcrum. The patient in bed 9 has a problem with the biomechanics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
