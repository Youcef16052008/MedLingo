# Relecture — Formes Pharmaceutiques (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-002` — Le cas de Youssef : Injection intramusculaire (IM) anormal(e) / _Youssef: the intramuscular (im) injection is not normal_

**Objectif :** Comprendre des résultats : Voie d'administration normal(e), Injection intramusculaire (IM) anormal(e).
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
4. **EN** The doctor says, “The route of administration is normal.”
   - FR : Le médecin dit : « Voie d'administration est normal(e). »
   - AR : يقول الطبيب: «طريق الإعطاء طبيعي/طبيعية.»
5. **EN** “But the intramuscular (im) injection is not normal.”
   - FR : « Mais Injection intramusculaire (IM) n'est pas normal(e). »
   - AR : «لكن الحقن العضلي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the intramuscular (im) injection normal?”
   - FR : Lina demande : « Injection intramusculaire (IM) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الحقن العضلي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:route-of-administration:v1` — **a route of administration** · FR : Voie d'administration · AR : طريق الإعطاء
- `gen:intravenous-iv-injection:v1` — **an intravenous (iv) injection** · FR : Injection intraveineuse (IV) · AR : الحقن الوريدي
- `gen:intramuscular-im-injection:v1` — **an intramuscular (im) injection** · FR : Injection intramusculaire (IM) · AR : الحقن العضلي

### Questions (acceptées / modèle / hint)
- **q1.** Is the route of administration normal?
  - accepté : yes ; yes it is ; the route of administration is normal ; it is normal
  - modèle : Yes, the route of administration is normal.
- **q2.** Is the intramuscular (im) injection normal?
  - accepté : no ; no it is not ; no it isn't ; the intramuscular (im) injection is not normal ; it is not normal ; it isn't normal
  - modèle : No, the intramuscular (im) injection is not normal.
  - hint (si erreur) — FR : Attention : Injection intramusculaire (IM) n'est PAS normal(e).
- **q3.** Which one is not normal: the route of administration or the intramuscular (im) injection?
  - accepté : intramuscular (im) injection ; the intramuscular (im) injection
  - modèle : The intramuscular (im) injection is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the intravenous (iv) injection normal?
  - accepté : yes ; yes it is ; the intravenous (iv) injection is normal
  - modèle : Yes, the intravenous (iv) injection is normal.
- **Tt2.** Is the route of administration normal?
  - accepté : no ; no it is not ; no it isn't ; the route of administration is not normal ; it is not normal
  - modèle : No, the route of administration is not normal.
  - hint (si erreur) — FR : Attention : Voie d'administration n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The route of administration … normal. (be)` → is · modèle : The route of administration is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The route of administration is… / The intramuscular (im) injection is not… — modèle : *The route of administration is normal. The intramuscular (im) injection is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Injection intraveineuse (IV) normal(e), mais Voie d'administration anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The intravenous (iv) injection is normal. The route of administration is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Voie d'administration et Injection intraveineuse (IV) à un patient.
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
4. **EN** “First, we check route of administration.”
   - FR : « D'abord, nous vérifions Voie d'administration. »
   - AR : «أولًا، نتحقق من طريق الإعطاء.»
5. **EN** “Then, we check intravenous (iv) injection.”
   - FR : « Ensuite, nous vérifions Injection intraveineuse (IV). »
   - AR : «ثم نتحقق من الحقن الوريدي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:route-of-administration:v1` — **a route of administration** · FR : Voie d'administration · AR : طريق الإعطاء
- `gen:intravenous-iv-injection:v1` — **an intravenous (iv) injection** · FR : Injection intraveineuse (IV) · AR : الحقن الوريدي
- `gen:intramuscular-im-injection:v1` — **an intramuscular (im) injection** · FR : Injection intramusculaire (IM) · AR : الحقن العضلي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : route of administration ; we check route of administration ; route of administration first ; the doctor checks route of administration first
  - modèle : First, the doctor checks route of administration.
- **q2.** What does the doctor check next?
  - accepté : intravenous (iv) injection ; then we check intravenous (iv) injection ; intravenous (iv) injection next ; the doctor checks intravenous (iv) injection next
  - modèle : Then, the doctor checks intravenous (iv) injection.
- **q3.** Does the doctor check intravenous (iv) injection before route of administration?
  - accepté : no ; no he does not ; no he doesn't ; he checks route of administration first
  - modèle : No, he checks route of administration first, then intravenous (iv) injection.
  - hint (si erreur) — FR : Attention : Voie d'administration vient D'ABORD, ensuite Injection intraveineuse (IV).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : intramuscular (im) injection ; intramuscular (im) injection first ; he checks intramuscular (im) injection first
  - modèle : First, the doctor checks intramuscular (im) injection.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check route of administration. Then, we check intravenous (iv) injection.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Injection intramusculaire (IM), ensuite Voie d'administration, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check intramuscular (im) injection. Then, we check route of administration.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Voie d'administration / Injection intraveineuse (IV), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the route of administration.
   - FR : Le patient du lit 3 a Voie d'administration.
   - AR : مريض السرير ٣ عنده طريق الإعطاء.
4. **EN** The patient in bed 5 has a problem with the intravenous (iv) injection.
   - FR : Le patient du lit 5 a Injection intraveineuse (IV).
   - AR : مريض السرير ٥ عنده الحقن الوريدي.
5. **EN** The patient in bed 5 does not have a problem with the route of administration.
   - FR : Le patient du lit 5 n'a pas de Voie d'administration.
   - AR : مريض السرير ٥ ليس عنده طريق الإعطاء.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:route-of-administration:v1` — **a route of administration** · FR : Voie d'administration · AR : طريق الإعطاء
- `gen:intravenous-iv-injection:v1` — **an intravenous (iv) injection** · FR : Injection intraveineuse (IV) · AR : الحقن الوريدي
- `gen:intramuscular-im-injection:v1` — **an intramuscular (im) injection** · FR : Injection intramusculaire (IM) · AR : الحقن العضلي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : route of administration ; a route of administration ; the patient with route of administration
  - modèle : The patient in bed 3 has a route of administration.
- **q2.** What does the patient in bed 5 have?
  - accepté : intravenous (iv) injection ; a problem with the intravenous (iv) injection
  - modèle : He has a problem with the intravenous (iv) injection.
- **q3.** Does the patient in bed 5 have route of administration?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the route of administration ; he doesn't
  - modèle : No, he does not have a problem with the route of administration.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS voie d'administration — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : intramuscular (im) injection ; a problem with the intramuscular (im) injection
  - modèle : He has a problem with the intramuscular (im) injection.
- **Tt2.** Does the patient in bed 9 have intravenous (iv) injection?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the intravenous (iv) injection ; he doesn't
  - modèle : No, he does not have a problem with the intravenous (iv) injection.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS injection intraveineuse (iv).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a route of administration and an intravenous (iv) injection. (have)` → have · modèle : The patients have a route of administration and an intravenous (iv) injection.
- `The patient in bed 5 … not have a route of administration. (do)` → does · modèle : The patient in bed 5 does not have a route of administration.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the route of administration. The patient in bed 5 has a problem with the intravenous (iv) injection.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Injection intramusculaire (IM), le lit 9 a Voie d'administration et n'a pas Injection intraveineuse (IV).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the intramuscular (im) injection. The patient in bed 9 has a problem with the route of administration.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
