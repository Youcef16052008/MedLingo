# Relecture — Distributions de Probabilité (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-003` — Le cas de Amel : Événements mutuellement exclusifs anormal(e) / _Amel: the mutually exclusive events is not normal_

**Objectif :** Comprendre des résultats : Probabilité normal(e), Événements mutuellement exclusifs anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The probability is normal.”
   - FR : Le médecin dit : « Probabilité est normal(e). »
   - AR : يقول الطبيب: «الاحتمال طبيعي/طبيعية.»
5. **EN** “But the mutually exclusive events is not normal.”
   - FR : « Mais Événements mutuellement exclusifs n'est pas normal(e). »
   - AR : «لكن أحداث متنافية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the mutually exclusive events normal?”
   - FR : Amine demande : « Événements mutuellement exclusifs est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل أحداث متنافية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:probability:v1` — **a probability** · FR : Probabilité · AR : الاحتمال
- `gen:event:v1` — **an event** · FR : Événement · AR : الحدث
- `gen:mutually-exclusive-events:v1` — **mutually exclusive events** · FR : Événements mutuellement exclusifs · AR : أحداث متنافية

### Questions (acceptées / modèle / hint)
- **q1.** Is the probability normal?
  - accepté : yes ; yes it is ; the probability is normal ; it is normal
  - modèle : Yes, the probability is normal.
- **q2.** Is the mutually exclusive events normal?
  - accepté : no ; no it is not ; no it isn't ; the mutually exclusive events is not normal ; it is not normal ; it isn't normal
  - modèle : No, the mutually exclusive events is not normal.
  - hint (si erreur) — FR : Attention : Événements mutuellement exclusifs n'est PAS normal(e).
- **q3.** Which one is not normal: the probability or the mutually exclusive events?
  - accepté : mutually exclusive events ; the mutually exclusive events
  - modèle : The mutually exclusive events is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the event normal?
  - accepté : yes ; yes it is ; the event is normal
  - modèle : Yes, the event is normal.
- **Tt2.** Is the probability normal?
  - accepté : no ; no it is not ; no it isn't ; the probability is not normal ; it is not normal
  - modèle : No, the probability is not normal.
  - hint (si erreur) — FR : Attention : Probabilité n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The probability … normal. (be)` → is · modèle : The probability is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The probability is… / The mutually exclusive events is not… — modèle : *The probability is normal. The mutually exclusive events is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Événement normal(e), mais Probabilité anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The event is normal. The probability is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Probabilité et Événement à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check probability.”
   - FR : « D'abord, nous vérifions Probabilité. »
   - AR : «أولًا، نتحقق من الاحتمال.»
5. **EN** “Then, we check event.”
   - FR : « Ensuite, nous vérifions Événement. »
   - AR : «ثم نتحقق من الحدث.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:probability:v1` — **a probability** · FR : Probabilité · AR : الاحتمال
- `gen:event:v1` — **an event** · FR : Événement · AR : الحدث
- `gen:mutually-exclusive-events:v1` — **mutually exclusive events** · FR : Événements mutuellement exclusifs · AR : أحداث متنافية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : probability ; we check probability ; probability first ; the doctor checks probability first
  - modèle : First, the doctor checks probability.
- **q2.** What does the doctor check next?
  - accepté : event ; then we check event ; event next ; the doctor checks event next
  - modèle : Then, the doctor checks event.
- **q3.** Does the doctor check event before probability?
  - accepté : no ; no he does not ; no he doesn't ; he checks probability first
  - modèle : No, he checks probability first, then event.
  - hint (si erreur) — FR : Attention : Probabilité vient D'ABORD, ensuite Événement.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : mutually exclusive events ; mutually exclusive events first ; he checks mutually exclusive events first
  - modèle : First, the doctor checks mutually exclusive events.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check probability. Then, we check event.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Événements mutuellement exclusifs, ensuite Probabilité, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check mutually exclusive events. Then, we check probability.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Probabilité / Événement, lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the probability.
   - FR : Le patient du lit 3 a Probabilité.
   - AR : مريض السرير ٣ عنده الاحتمال.
4. **EN** The patient in bed 5 has a problem with the event.
   - FR : Le patient du lit 5 a Événement.
   - AR : مريض السرير ٥ عنده الحدث.
5. **EN** The patient in bed 5 does not have a problem with the probability.
   - FR : Le patient du lit 5 n'a pas de Probabilité.
   - AR : مريض السرير ٥ ليس عنده الاحتمال.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:probability:v1` — **a probability** · FR : Probabilité · AR : الاحتمال
- `gen:event:v1` — **an event** · FR : Événement · AR : الحدث
- `gen:mutually-exclusive-events:v1` — **mutually exclusive events** · FR : Événements mutuellement exclusifs · AR : أحداث متنافية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : probability ; a probability ; the patient with probability
  - modèle : The patient in bed 3 has a probability.
- **q2.** What does the patient in bed 5 have?
  - accepté : event ; a problem with the event
  - modèle : He has a problem with the event.
- **q3.** Does the patient in bed 5 have probability?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the probability ; he doesn't
  - modèle : No, he does not have a problem with the probability.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS probabilité — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : mutually exclusive events ; a problem with the mutually exclusive events
  - modèle : He has a problem with the mutually exclusive events.
- **Tt2.** Does the patient in bed 9 have event?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the event ; he doesn't
  - modèle : No, he does not have a problem with the event.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS événement.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a probability and an event. (have)` → have · modèle : The patients have a probability and an event.
- `The patient in bed 5 … not have a probability. (do)` → does · modèle : The patient in bed 5 does not have a probability.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the probability. The patient in bed 5 has a problem with the event.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Événements mutuellement exclusifs, le lit 9 a Probabilité et n'a pas Événement.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the mutually exclusive events. The patient in bed 9 has a problem with the probability.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
