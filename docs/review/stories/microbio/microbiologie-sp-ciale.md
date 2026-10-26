# Relecture — Microbiologie Spéciale (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-008` — Le cas de Bilal : Rickettsie anormal(e) / _Bilal: the rickettsia is not normal_

**Objectif :** Comprendre des résultats : Prion normal(e), Rickettsie anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The prion is normal.”
   - FR : Le médecin dit : « Prion est normal(e). »
   - AR : يقول الطبيب: «البريون طبيعي/طبيعية.»
5. **EN** “But the rickettsia is not normal.”
   - FR : « Mais Rickettsie n'est pas normal(e). »
   - AR : «لكن الريكتسية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the rickettsia normal?”
   - FR : Lina demande : « Rickettsie est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الريكتسية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:prion:v1` — **a prion** · FR : Prion · AR : البريون
- `gen:chlamydia:v1` — **a chlamydia** · FR : Chlamydia · AR : الكلاميديا
- `gen:rickettsia:v1` — **a rickettsia** · FR : Rickettsie · AR : الريكتسية

### Questions (acceptées / modèle / hint)
- **q1.** Is the prion normal?
  - accepté : yes ; yes it is ; the prion is normal ; it is normal
  - modèle : Yes, the prion is normal.
- **q2.** Is the rickettsia normal?
  - accepté : no ; no it is not ; no it isn't ; the rickettsia is not normal ; it is not normal ; it isn't normal
  - modèle : No, the rickettsia is not normal.
  - hint (si erreur) — FR : Attention : Rickettsie n'est PAS normal(e).
- **q3.** Which one is not normal: the prion or the rickettsia?
  - accepté : rickettsia ; the rickettsia
  - modèle : The rickettsia is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the chlamydia normal?
  - accepté : yes ; yes it is ; the chlamydia is normal
  - modèle : Yes, the chlamydia is normal.
- **Tt2.** Is the prion normal?
  - accepté : no ; no it is not ; no it isn't ; the prion is not normal ; it is not normal
  - modèle : No, the prion is not normal.
  - hint (si erreur) — FR : Attention : Prion n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The prion … normal. (be)` → is · modèle : The prion is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The prion is… / The rickettsia is not… — modèle : *The prion is normal. The rickettsia is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Chlamydia normal(e), mais Prion anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The chlamydia is normal. The prion is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Prion et Chlamydia à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient.
   - FR : Omar est un·e patient·e.
   - AR : Omar مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check prion.”
   - FR : « D'abord, nous vérifions Prion. »
   - AR : «أولًا، نتحقق من البريون.»
5. **EN** “Then, we check chlamydia.”
   - FR : « Ensuite, nous vérifions Chlamydia. »
   - AR : «ثم نتحقق من الكلاميديا.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:prion:v1` — **a prion** · FR : Prion · AR : البريون
- `gen:chlamydia:v1` — **a chlamydia** · FR : Chlamydia · AR : الكلاميديا
- `gen:rickettsia:v1` — **a rickettsia** · FR : Rickettsie · AR : الريكتسية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : prion ; we check prion ; prion first ; the doctor checks prion first
  - modèle : First, the doctor checks prion.
- **q2.** What does the doctor check next?
  - accepté : chlamydia ; then we check chlamydia ; chlamydia next ; the doctor checks chlamydia next
  - modèle : Then, the doctor checks chlamydia.
- **q3.** Does the doctor check chlamydia before prion?
  - accepté : no ; no he does not ; no he doesn't ; he checks prion first
  - modèle : No, he checks prion first, then chlamydia.
  - hint (si erreur) — FR : Attention : Prion vient D'ABORD, ensuite Chlamydia.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : rickettsia ; rickettsia first ; he checks rickettsia first
  - modèle : First, the doctor checks rickettsia.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check prion. Then, we check chlamydia.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Rickettsie, ensuite Prion, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check rickettsia. Then, we check prion.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Prion / Chlamydia, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the prion.
   - FR : Le patient du lit 3 a Prion.
   - AR : مريض السرير ٣ عنده البريون.
4. **EN** The patient in bed 5 has a problem with the chlamydia.
   - FR : Le patient du lit 5 a Chlamydia.
   - AR : مريض السرير ٥ عنده الكلاميديا.
5. **EN** The patient in bed 5 does not have a problem with the prion.
   - FR : Le patient du lit 5 n'a pas de Prion.
   - AR : مريض السرير ٥ ليس عنده البريون.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:prion:v1` — **a prion** · FR : Prion · AR : البريون
- `gen:chlamydia:v1` — **a chlamydia** · FR : Chlamydia · AR : الكلاميديا
- `gen:rickettsia:v1` — **a rickettsia** · FR : Rickettsie · AR : الريكتسية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : prion ; a prion ; the patient with prion
  - modèle : The patient in bed 3 has a prion.
- **q2.** What does the patient in bed 5 have?
  - accepté : chlamydia ; a problem with the chlamydia
  - modèle : He has a problem with the chlamydia.
- **q3.** Does the patient in bed 5 have prion?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the prion ; he doesn't
  - modèle : No, he does not have a problem with the prion.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS prion — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : rickettsia ; a problem with the rickettsia
  - modèle : He has a problem with the rickettsia.
- **Tt2.** Does the patient in bed 9 have chlamydia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the chlamydia ; he doesn't
  - modèle : No, he does not have a problem with the chlamydia.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS chlamydia.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a prion and a chlamydia. (have)` → have · modèle : The patients have a prion and a chlamydia.
- `The patient in bed 5 … not have a prion. (do)` → does · modèle : The patient in bed 5 does not have a prion.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the prion. The patient in bed 5 has a problem with the chlamydia.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Rickettsie, le lit 9 a Prion et n'a pas Chlamydia.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the rickettsia. The patient in bed 9 has a problem with the prion.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
