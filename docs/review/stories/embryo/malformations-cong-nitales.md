# Relecture — Malformations Congénitales (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-004` — Le cas de Farid : Tératologie anormal(e) / _Farid: the teratology is not normal_

**Objectif :** Comprendre des résultats : Malformation congénitale normal(e), Tératologie anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The congenital malformation is normal.”
   - FR : Le médecin dit : « Malformation congénitale est normal(e). »
   - AR : يقول الطبيب: «التشوه الخلقي طبيعي/طبيعية.»
5. **EN** “But the teratology is not normal.”
   - FR : « Mais Tératologie n'est pas normal(e). »
   - AR : «لكن علم التشوهات غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the teratology normal?”
   - FR : Lina demande : « Tératologie est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل علم التشوهات طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:congenital-malformation:v1` — **a congenital malformation** · FR : Malformation congénitale · AR : التشوه الخلقي
- `gen:teratogen:v1` — **a teratogen** · FR : Tératogène · AR : المُشوِّه (العامل المشوه)
- `gen:teratology:v1` — **a teratology** · FR : Tératologie · AR : علم التشوهات

### Questions (acceptées / modèle / hint)
- **q1.** Is the congenital malformation normal?
  - accepté : yes ; yes it is ; the congenital malformation is normal ; it is normal
  - modèle : Yes, the congenital malformation is normal.
- **q2.** Is the teratology normal?
  - accepté : no ; no it is not ; no it isn't ; the teratology is not normal ; it is not normal ; it isn't normal
  - modèle : No, the teratology is not normal.
  - hint (si erreur) — FR : Attention : Tératologie n'est PAS normal(e).
- **q3.** Which one is not normal: the congenital malformation or the teratology?
  - accepté : teratology ; the teratology
  - modèle : The teratology is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the teratogen normal?
  - accepté : yes ; yes it is ; the teratogen is normal
  - modèle : Yes, the teratogen is normal.
- **Tt2.** Is the congenital malformation normal?
  - accepté : no ; no it is not ; no it isn't ; the congenital malformation is not normal ; it is not normal
  - modèle : No, the congenital malformation is not normal.
  - hint (si erreur) — FR : Attention : Malformation congénitale n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The congenital malformation … normal. (be)` → is · modèle : The congenital malformation is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The congenital malformation is… / The teratology is not… — modèle : *The congenital malformation is normal. The teratology is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Tératogène normal(e), mais Malformation congénitale anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The teratogen is normal. The congenital malformation is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Malformation congénitale et Tératogène à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient.
   - FR : Sofiane est un·e patient·e.
   - AR : Sofiane مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check congenital malformation.”
   - FR : « D'abord, nous vérifions Malformation congénitale. »
   - AR : «أولًا، نتحقق من التشوه الخلقي.»
5. **EN** “Then, we check teratogen.”
   - FR : « Ensuite, nous vérifions Tératogène. »
   - AR : «ثم نتحقق من المُشوِّه (العامل المشوه).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:congenital-malformation:v1` — **a congenital malformation** · FR : Malformation congénitale · AR : التشوه الخلقي
- `gen:teratogen:v1` — **a teratogen** · FR : Tératogène · AR : المُشوِّه (العامل المشوه)
- `gen:teratology:v1` — **a teratology** · FR : Tératologie · AR : علم التشوهات

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : congenital malformation ; we check congenital malformation ; congenital malformation first ; the doctor checks congenital malformation first
  - modèle : First, the doctor checks congenital malformation.
- **q2.** What does the doctor check next?
  - accepté : teratogen ; then we check teratogen ; teratogen next ; the doctor checks teratogen next
  - modèle : Then, the doctor checks teratogen.
- **q3.** Does the doctor check teratogen before congenital malformation?
  - accepté : no ; no he does not ; no he doesn't ; he checks congenital malformation first
  - modèle : No, he checks congenital malformation first, then teratogen.
  - hint (si erreur) — FR : Attention : Malformation congénitale vient D'ABORD, ensuite Tératogène.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : teratology ; teratology first ; he checks teratology first
  - modèle : First, the doctor checks teratology.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check congenital malformation. Then, we check teratogen.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Tératologie, ensuite Malformation congénitale, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check teratology. Then, we check congenital malformation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Malformation congénitale / Tératogène, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the congenital malformation.
   - FR : Le patient du lit 3 a Malformation congénitale.
   - AR : مريض السرير ٣ عنده التشوه الخلقي.
4. **EN** The patient in bed 5 has a problem with the teratogen.
   - FR : Le patient du lit 5 a Tératogène.
   - AR : مريض السرير ٥ عنده المُشوِّه (العامل المشوه).
5. **EN** The patient in bed 5 does not have a problem with the congenital malformation.
   - FR : Le patient du lit 5 n'a pas de Malformation congénitale.
   - AR : مريض السرير ٥ ليس عنده التشوه الخلقي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:congenital-malformation:v1` — **a congenital malformation** · FR : Malformation congénitale · AR : التشوه الخلقي
- `gen:teratogen:v1` — **a teratogen** · FR : Tératogène · AR : المُشوِّه (العامل المشوه)
- `gen:teratology:v1` — **a teratology** · FR : Tératologie · AR : علم التشوهات

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : congenital malformation ; a congenital malformation ; the patient with congenital malformation
  - modèle : The patient in bed 3 has a congenital malformation.
- **q2.** What does the patient in bed 5 have?
  - accepté : teratogen ; a problem with the teratogen
  - modèle : He has a problem with the teratogen.
- **q3.** Does the patient in bed 5 have congenital malformation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the congenital malformation ; he doesn't
  - modèle : No, he does not have a problem with the congenital malformation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS malformation congénitale — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : teratology ; a problem with the teratology
  - modèle : He has a problem with the teratology.
- **Tt2.** Does the patient in bed 9 have teratogen?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the teratogen ; he doesn't
  - modèle : No, he does not have a problem with the teratogen.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS tératogène.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a congenital malformation and a teratogen. (have)` → have · modèle : The patients have a congenital malformation and a teratogen.
- `The patient in bed 5 … not have a congenital malformation. (do)` → does · modèle : The patient in bed 5 does not have a congenital malformation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the congenital malformation. The patient in bed 5 has a problem with the teratogen.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Tératologie, le lit 9 a Malformation congénitale et n'a pas Tératogène.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the teratology. The patient in bed 9 has a problem with the congenital malformation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
