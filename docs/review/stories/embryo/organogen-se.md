# Relecture — Organogenèse (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-006` — Le cas de Sofiane : Fentes pharyngées anormal(e) / _Sofiane: the pharyngeal clefts is not normal_

**Objectif :** Comprendre des résultats : Organogenèse normal(e), Fentes pharyngées anormal(e).
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
4. **EN** The doctor says, “The organogenesis is normal.”
   - FR : Le médecin dit : « Organogenèse est normal(e). »
   - AR : يقول الطبيب: «تكوين الأعضاء طبيعي/طبيعية.»
5. **EN** “But the pharyngeal clefts is not normal.”
   - FR : « Mais Fentes pharyngées n'est pas normal(e). »
   - AR : «لكن الشقوق البلعومية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the pharyngeal clefts normal?”
   - FR : Lina demande : « Fentes pharyngées est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الشقوق البلعومية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:organogenesis:v1` — **organogenesis** · FR : Organogenèse · AR : تكوين الأعضاء
- `gen:pharyngeal-arches:v1` — **pharyngeal arches** · FR : Arcs pharyngés (branchiaux) · AR : الأقواس البلعومية
- `gen:pharyngeal-clefts:v1` — **pharyngeal clefts** · FR : Fentes pharyngées · AR : الشقوق البلعومية

### Questions (acceptées / modèle / hint)
- **q1.** Is the organogenesis normal?
  - accepté : yes ; yes it is ; the organogenesis is normal ; it is normal
  - modèle : Yes, the organogenesis is normal.
- **q2.** Is the pharyngeal clefts normal?
  - accepté : no ; no it is not ; no it isn't ; the pharyngeal clefts is not normal ; it is not normal ; it isn't normal
  - modèle : No, the pharyngeal clefts is not normal.
  - hint (si erreur) — FR : Attention : Fentes pharyngées n'est PAS normal(e).
- **q3.** Which one is not normal: the organogenesis or the pharyngeal clefts?
  - accepté : pharyngeal clefts ; the pharyngeal clefts
  - modèle : The pharyngeal clefts is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the pharyngeal arches normal?
  - accepté : yes ; yes it is ; the pharyngeal arches is normal
  - modèle : Yes, the pharyngeal arches is normal.
- **Tt2.** Is the organogenesis normal?
  - accepté : no ; no it is not ; no it isn't ; the organogenesis is not normal ; it is not normal
  - modèle : No, the organogenesis is not normal.
  - hint (si erreur) — FR : Attention : Organogenèse n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The organogenesis … normal. (be)` → is · modèle : The organogenesis is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sofiane en deux phrases. Garde la négation. — amorces : The organogenesis is… / The pharyngeal clefts is not… — modèle : *The organogenesis is normal. The pharyngeal clefts is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Arcs pharyngés (branchiaux) normal(e), mais Organogenèse anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The pharyngeal arches is normal. The organogenesis is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Organogenèse et Arcs pharyngés (branchiaux) à un patient.
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
4. **EN** “First, we check organogenesis.”
   - FR : « D'abord, nous vérifions Organogenèse. »
   - AR : «أولًا، نتحقق من تكوين الأعضاء.»
5. **EN** “Then, we check pharyngeal arches.”
   - FR : « Ensuite, nous vérifions Arcs pharyngés (branchiaux). »
   - AR : «ثم نتحقق من الأقواس البلعومية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:organogenesis:v1` — **organogenesis** · FR : Organogenèse · AR : تكوين الأعضاء
- `gen:pharyngeal-arches:v1` — **pharyngeal arches** · FR : Arcs pharyngés (branchiaux) · AR : الأقواس البلعومية
- `gen:pharyngeal-clefts:v1` — **pharyngeal clefts** · FR : Fentes pharyngées · AR : الشقوق البلعومية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : organogenesis ; we check organogenesis ; organogenesis first ; the doctor checks organogenesis first
  - modèle : First, the doctor checks organogenesis.
- **q2.** What does the doctor check next?
  - accepté : pharyngeal arches ; then we check pharyngeal arches ; pharyngeal arches next ; the doctor checks pharyngeal arches next
  - modèle : Then, the doctor checks pharyngeal arches.
- **q3.** Does the doctor check pharyngeal arches before organogenesis?
  - accepté : no ; no he does not ; no he doesn't ; he checks organogenesis first
  - modèle : No, he checks organogenesis first, then pharyngeal arches.
  - hint (si erreur) — FR : Attention : Organogenèse vient D'ABORD, ensuite Arcs pharyngés (branchiaux).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : pharyngeal clefts ; pharyngeal clefts first ; he checks pharyngeal clefts first
  - modèle : First, the doctor checks pharyngeal clefts.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check organogenesis. Then, we check pharyngeal arches.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fentes pharyngées, ensuite Organogenèse, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check pharyngeal clefts. Then, we check organogenesis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Organogenèse / Arcs pharyngés (branchiaux), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the organogenesis.
   - FR : Le patient du lit 3 a Organogenèse.
   - AR : مريض السرير ٣ عنده تكوين الأعضاء.
4. **EN** The patient in bed 5 has a problem with the pharyngeal arches.
   - FR : Le patient du lit 5 a Arcs pharyngés (branchiaux).
   - AR : مريض السرير ٥ عنده الأقواس البلعومية.
5. **EN** The patient in bed 5 does not have a problem with the organogenesis.
   - FR : Le patient du lit 5 n'a pas de Organogenèse.
   - AR : مريض السرير ٥ ليس عنده تكوين الأعضاء.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:organogenesis:v1` — **organogenesis** · FR : Organogenèse · AR : تكوين الأعضاء
- `gen:pharyngeal-arches:v1` — **pharyngeal arches** · FR : Arcs pharyngés (branchiaux) · AR : الأقواس البلعومية
- `gen:pharyngeal-clefts:v1` — **pharyngeal clefts** · FR : Fentes pharyngées · AR : الشقوق البلعومية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : organogenesis ; the patient with organogenesis
  - modèle : The patient in bed 3 has organogenesis.
- **q2.** What does the patient in bed 5 have?
  - accepté : pharyngeal arches ; a problem with the pharyngeal arches
  - modèle : He has a problem with the pharyngeal arches.
- **q3.** Does the patient in bed 5 have organogenesis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the organogenesis ; he doesn't
  - modèle : No, he does not have a problem with the organogenesis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS organogenèse — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : pharyngeal clefts ; a problem with the pharyngeal clefts
  - modèle : He has a problem with the pharyngeal clefts.
- **Tt2.** Does the patient in bed 9 have pharyngeal arches?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the pharyngeal arches ; he doesn't
  - modèle : No, he does not have a problem with the pharyngeal arches.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS arcs pharyngés (branchiaux).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … organogenesis and pharyngeal arches. (have)` → have · modèle : The patients have organogenesis and pharyngeal arches.
- `The patient in bed 5 … not have organogenesis. (do)` → does · modèle : The patient in bed 5 does not have organogenesis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the organogenesis. The patient in bed 5 has a problem with the pharyngeal arches.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Fentes pharyngées, le lit 9 a Organogenèse et n'a pas Arcs pharyngés (branchiaux).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the pharyngeal clefts. The patient in bed 9 has a problem with the organogenesis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
