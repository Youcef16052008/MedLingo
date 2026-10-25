# Relecture — Cycle Menstruel (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-002` — Le cas de Youssef : Phase lutéale anormal(e) / _Youssef: the luteal phase is not normal_

**Objectif :** Comprendre des résultats : Cycle menstruel normal(e), Phase lutéale anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The menstrual cycle is normal.”
   - FR : Le médecin dit : « Cycle menstruel est normal(e). »
   - AR : يقول الطبيب: «الدورة الشهرية طبيعي/طبيعية.»
5. **EN** “But the luteal phase is not normal.”
   - FR : « Mais Phase lutéale n'est pas normal(e). »
   - AR : «لكن المرحلة الجسمية الصفراء غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the luteal phase normal?”
   - FR : Lina demande : « Phase lutéale est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل المرحلة الجسمية الصفراء طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:menstrual-cycle:v1` — **a menstrual cycle** · FR : Cycle menstruel · AR : الدورة الشهرية
- `gen:follicular-phase:v1` — **a follicular phase** · FR : Phase folliculaire · AR : المرحلة الجريبية
- `gen:luteal-phase:v1` — **a luteal phase** · FR : Phase lutéale · AR : المرحلة الجسمية الصفراء

### Questions (acceptées / modèle / hint)
- **q1.** Is the menstrual cycle normal?
  - accepté : yes ; yes it is ; the menstrual cycle is normal ; it is normal
  - modèle : Yes, the menstrual cycle is normal.
- **q2.** Is the luteal phase normal?
  - accepté : no ; no it is not ; no it isn't ; the luteal phase is not normal ; it is not normal ; it isn't normal
  - modèle : No, the luteal phase is not normal.
  - hint (si erreur) — FR : Attention : Phase lutéale n'est PAS normal(e).
- **q3.** Which one is not normal: the menstrual cycle or the luteal phase?
  - accepté : luteal phase ; the luteal phase
  - modèle : The luteal phase is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the follicular phase normal?
  - accepté : yes ; yes it is ; the follicular phase is normal
  - modèle : Yes, the follicular phase is normal.
- **Tt2.** Is the menstrual cycle normal?
  - accepté : no ; no it is not ; no it isn't ; the menstrual cycle is not normal ; it is not normal
  - modèle : No, the menstrual cycle is not normal.
  - hint (si erreur) — FR : Attention : Cycle menstruel n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The menstrual cycle … normal. (be)` → is · modèle : The menstrual cycle is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The menstrual cycle is… / The luteal phase is not… — modèle : *The menstrual cycle is normal. The luteal phase is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Phase folliculaire normal(e), mais Cycle menstruel anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The follicular phase is normal. The menstrual cycle is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Cycle menstruel et Phase folliculaire à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient.
   - FR : Farid est un·e patient·e.
   - AR : Farid مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check menstrual cycle.”
   - FR : « D'abord, nous vérifions Cycle menstruel. »
   - AR : «أولًا، نتحقق من الدورة الشهرية.»
5. **EN** “Then, we check follicular phase.”
   - FR : « Ensuite, nous vérifions Phase folliculaire. »
   - AR : «ثم نتحقق من المرحلة الجريبية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:menstrual-cycle:v1` — **a menstrual cycle** · FR : Cycle menstruel · AR : الدورة الشهرية
- `gen:follicular-phase:v1` — **a follicular phase** · FR : Phase folliculaire · AR : المرحلة الجريبية
- `gen:luteal-phase:v1` — **a luteal phase** · FR : Phase lutéale · AR : المرحلة الجسمية الصفراء

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : menstrual cycle ; we check menstrual cycle ; menstrual cycle first ; the doctor checks menstrual cycle first
  - modèle : First, the doctor checks menstrual cycle.
- **q2.** What does the doctor check next?
  - accepté : follicular phase ; then we check follicular phase ; follicular phase next ; the doctor checks follicular phase next
  - modèle : Then, the doctor checks follicular phase.
- **q3.** Does the doctor check follicular phase before menstrual cycle?
  - accepté : no ; no he does not ; no he doesn't ; he checks menstrual cycle first
  - modèle : No, he checks menstrual cycle first, then follicular phase.
  - hint (si erreur) — FR : Attention : Cycle menstruel vient D'ABORD, ensuite Phase folliculaire.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : luteal phase ; luteal phase first ; he checks luteal phase first
  - modèle : First, the doctor checks luteal phase.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check menstrual cycle. Then, we check follicular phase.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Phase lutéale, ensuite Cycle menstruel, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check luteal phase. Then, we check menstrual cycle.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cycle menstruel / Phase folliculaire, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the menstrual cycle.
   - FR : Le patient du lit 3 a Cycle menstruel.
   - AR : مريض السرير ٣ عنده الدورة الشهرية.
4. **EN** The patient in bed 5 has a problem with the follicular phase.
   - FR : Le patient du lit 5 a Phase folliculaire.
   - AR : مريض السرير ٥ عنده المرحلة الجريبية.
5. **EN** The patient in bed 5 does not have a problem with the menstrual cycle.
   - FR : Le patient du lit 5 n'a pas de Cycle menstruel.
   - AR : مريض السرير ٥ ليس عنده الدورة الشهرية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:menstrual-cycle:v1` — **a menstrual cycle** · FR : Cycle menstruel · AR : الدورة الشهرية
- `gen:follicular-phase:v1` — **a follicular phase** · FR : Phase folliculaire · AR : المرحلة الجريبية
- `gen:luteal-phase:v1` — **a luteal phase** · FR : Phase lutéale · AR : المرحلة الجسمية الصفراء

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : menstrual cycle ; a menstrual cycle ; the patient with menstrual cycle
  - modèle : The patient in bed 3 has a menstrual cycle.
- **q2.** What does the patient in bed 5 have?
  - accepté : follicular phase ; a problem with the follicular phase
  - modèle : He has a problem with the follicular phase.
- **q3.** Does the patient in bed 5 have menstrual cycle?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the menstrual cycle ; he doesn't
  - modèle : No, he does not have a problem with the menstrual cycle.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cycle menstruel — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : luteal phase ; a problem with the luteal phase
  - modèle : He has a problem with the luteal phase.
- **Tt2.** Does the patient in bed 9 have follicular phase?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the follicular phase ; he doesn't
  - modèle : No, he does not have a problem with the follicular phase.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS phase folliculaire.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a menstrual cycle and a follicular phase. (have)` → have · modèle : The patients have a menstrual cycle and a follicular phase.
- `The patient in bed 5 … not have a menstrual cycle. (do)` → does · modèle : The patient in bed 5 does not have a menstrual cycle.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the menstrual cycle. The patient in bed 5 has a problem with the follicular phase.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Phase lutéale, le lit 9 a Cycle menstruel et n'a pas Phase folliculaire.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the luteal phase. The patient in bed 9 has a problem with the menstrual cycle.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
