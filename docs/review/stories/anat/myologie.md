# Relecture — Myologie (anat)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anat-case-005` — Le cas de Meriem : Muscle lisse anormal(e) / _Meriem: the smooth muscle is not normal_

**Objectif :** Comprendre des résultats : Muscle normal(e), Muscle lisse anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient at the clinic.
   - FR : Meriem est un·e patient·e à la clinique.
   - AR : Meriem مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The muscle is normal.”
   - FR : Le médecin dit : « Muscle est normal(e). »
   - AR : يقول الطبيب: «العضلة طبيعي/طبيعية.»
5. **EN** “But the smooth muscle is not normal.”
   - FR : « Mais Muscle lisse n'est pas normal(e). »
   - AR : «لكن العضلة الملساء غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the smooth muscle normal?”
   - FR : Amine demande : « Muscle lisse est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل العضلة الملساء طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:muscle:v1` — **a muscle** · FR : Muscle · AR : العضلة
- `gen:skeletal-muscle:v1` — **a skeletal muscle** · FR : Muscle squelettique · AR : العضلة الهيكلية
- `gen:smooth-muscle:v1` — **a smooth muscle** · FR : Muscle lisse · AR : العضلة الملساء

### Questions (acceptées / modèle / hint)
- **q1.** Is the muscle normal?
  - accepté : yes ; yes it is ; the muscle is normal ; it is normal
  - modèle : Yes, the muscle is normal.
- **q2.** Is the smooth muscle normal?
  - accepté : no ; no it is not ; no it isn't ; the smooth muscle is not normal ; it is not normal ; it isn't normal
  - modèle : No, the smooth muscle is not normal.
  - hint (si erreur) — FR : Attention : Muscle lisse n'est PAS normal(e).
- **q3.** Which one is not normal: the muscle or the smooth muscle?
  - accepté : smooth muscle ; the smooth muscle
  - modèle : The smooth muscle is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the skeletal muscle normal?
  - accepté : yes ; yes it is ; the skeletal muscle is normal
  - modèle : Yes, the skeletal muscle is normal.
- **Tt2.** Is the muscle normal?
  - accepté : no ; no it is not ; no it isn't ; the muscle is not normal ; it is not normal
  - modèle : No, the muscle is not normal.
  - hint (si erreur) — FR : Attention : Muscle n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The muscle … normal. (be)` → is · modèle : The muscle is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The muscle is… / The smooth muscle is not… — modèle : *The muscle is normal. The smooth muscle is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Muscle squelettique normal(e), mais Muscle anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The skeletal muscle is normal. The muscle is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anat-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Muscle et Muscle squelettique à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient.
   - FR : Hana est un·e patient·e.
   - AR : Hana مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check muscle.”
   - FR : « D'abord, nous vérifions Muscle. »
   - AR : «أولًا، نتحقق من العضلة.»
5. **EN** “Then, we check skeletal muscle.”
   - FR : « Ensuite, nous vérifions Muscle squelettique. »
   - AR : «ثم نتحقق من العضلة الهيكلية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:muscle:v1` — **a muscle** · FR : Muscle · AR : العضلة
- `gen:skeletal-muscle:v1` — **a skeletal muscle** · FR : Muscle squelettique · AR : العضلة الهيكلية
- `gen:smooth-muscle:v1` — **a smooth muscle** · FR : Muscle lisse · AR : العضلة الملساء

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : muscle ; we check muscle ; muscle first ; the doctor checks muscle first
  - modèle : First, the doctor checks muscle.
- **q2.** What does the doctor check next?
  - accepté : skeletal muscle ; then we check skeletal muscle ; skeletal muscle next ; the doctor checks skeletal muscle next
  - modèle : Then, the doctor checks skeletal muscle.
- **q3.** Does the doctor check skeletal muscle before muscle?
  - accepté : no ; no he does not ; no he doesn't ; he checks muscle first
  - modèle : No, he checks muscle first, then skeletal muscle.
  - hint (si erreur) — FR : Attention : Muscle vient D'ABORD, ensuite Muscle squelettique.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : smooth muscle ; smooth muscle first ; he checks smooth muscle first
  - modèle : First, the doctor checks smooth muscle.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check muscle. Then, we check skeletal muscle.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Muscle lisse, ensuite Muscle, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check smooth muscle. Then, we check muscle.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anat-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Muscle / Muscle squelettique, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the muscle.
   - FR : Le patient du lit 3 a Muscle.
   - AR : مريض السرير ٣ عنده العضلة.
4. **EN** The patient in bed 5 has a problem with the skeletal muscle.
   - FR : Le patient du lit 5 a Muscle squelettique.
   - AR : مريض السرير ٥ عنده العضلة الهيكلية.
5. **EN** The patient in bed 5 does not have a problem with the muscle.
   - FR : Le patient du lit 5 n'a pas de Muscle.
   - AR : مريض السرير ٥ ليس عنده العضلة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:muscle:v1` — **a muscle** · FR : Muscle · AR : العضلة
- `gen:skeletal-muscle:v1` — **a skeletal muscle** · FR : Muscle squelettique · AR : العضلة الهيكلية
- `gen:smooth-muscle:v1` — **a smooth muscle** · FR : Muscle lisse · AR : العضلة الملساء

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : muscle ; a muscle ; the patient with muscle
  - modèle : The patient in bed 3 has a muscle.
- **q2.** What does the patient in bed 5 have?
  - accepté : skeletal muscle ; a problem with the skeletal muscle
  - modèle : He has a problem with the skeletal muscle.
- **q3.** Does the patient in bed 5 have muscle?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the muscle ; he doesn't
  - modèle : No, he does not have a problem with the muscle.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS muscle — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : smooth muscle ; a problem with the smooth muscle
  - modèle : He has a problem with the smooth muscle.
- **Tt2.** Does the patient in bed 9 have skeletal muscle?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the skeletal muscle ; he doesn't
  - modèle : No, he does not have a problem with the skeletal muscle.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS muscle squelettique.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a muscle and a skeletal muscle. (have)` → have · modèle : The patients have a muscle and a skeletal muscle.
- `The patient in bed 5 … not have a muscle. (do)` → does · modèle : The patient in bed 5 does not have a muscle.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the muscle. The patient in bed 5 has a problem with the skeletal muscle.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Muscle lisse, le lit 9 a Muscle et n'a pas Muscle squelettique.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the smooth muscle. The patient in bed 9 has a problem with the muscle.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
