# Relecture — Acides, Bases et pH (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-002` — Le cas de Youssef : Base de Brønsted-Lowry anormal(e) / _Youssef: the brønsted-lowry base is not normal_

**Objectif :** Comprendre des résultats : Base normal(e), Base de Brønsted-Lowry anormal(e).
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
4. **EN** The doctor says, “The base is normal.”
   - FR : Le médecin dit : « Base est normal(e). »
   - AR : يقول الطبيب: «القاعدة طبيعي/طبيعية.»
5. **EN** “But the brønsted-lowry base is not normal.”
   - FR : « Mais Base de Brønsted-Lowry n'est pas normal(e). »
   - AR : «لكن قاعدة برونستد-لوري غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the brønsted-lowry base normal?”
   - FR : Lina demande : « Base de Brønsted-Lowry est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل قاعدة برونستد-لوري طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:base:v1` — **a base** · FR : Base · AR : القاعدة
- `gen:br-nsted-lowry-acid:v1` — **a brønsted-lowry acid** · FR : Acide de Brønsted-Lowry · AR : حمض برونستد-لوري
- `gen:br-nsted-lowry-base:v1` — **a brønsted-lowry base** · FR : Base de Brønsted-Lowry · AR : قاعدة برونستد-لوري

### Questions (acceptées / modèle / hint)
- **q1.** Is the base normal?
  - accepté : yes ; yes it is ; the base is normal ; it is normal
  - modèle : Yes, the base is normal.
- **q2.** Is the brønsted-lowry base normal?
  - accepté : no ; no it is not ; no it isn't ; the brønsted-lowry base is not normal ; it is not normal ; it isn't normal
  - modèle : No, the brønsted-lowry base is not normal.
  - hint (si erreur) — FR : Attention : Base de Brønsted-Lowry n'est PAS normal(e).
- **q3.** Which one is not normal: the base or the brønsted-lowry base?
  - accepté : brønsted-lowry base ; the brønsted-lowry base
  - modèle : The brønsted-lowry base is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the brønsted-lowry acid normal?
  - accepté : yes ; yes it is ; the brønsted-lowry acid is normal
  - modèle : Yes, the brønsted-lowry acid is normal.
- **Tt2.** Is the base normal?
  - accepté : no ; no it is not ; no it isn't ; the base is not normal ; it is not normal
  - modèle : No, the base is not normal.
  - hint (si erreur) — FR : Attention : Base n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The base … normal. (be)` → is · modèle : The base is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The base is… / The brønsted-lowry base is not… — modèle : *The base is normal. The brønsted-lowry base is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Acide de Brønsted-Lowry normal(e), mais Base anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The brønsted-lowry acid is normal. The base is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Base et Acide de Brønsted-Lowry à un patient.
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
4. **EN** “First, we check base.”
   - FR : « D'abord, nous vérifions Base. »
   - AR : «أولًا، نتحقق من القاعدة.»
5. **EN** “Then, we check brønsted-lowry acid.”
   - FR : « Ensuite, nous vérifions Acide de Brønsted-Lowry. »
   - AR : «ثم نتحقق من حمض برونستد-لوري.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:base:v1` — **a base** · FR : Base · AR : القاعدة
- `gen:br-nsted-lowry-acid:v1` — **a brønsted-lowry acid** · FR : Acide de Brønsted-Lowry · AR : حمض برونستد-لوري
- `gen:br-nsted-lowry-base:v1` — **a brønsted-lowry base** · FR : Base de Brønsted-Lowry · AR : قاعدة برونستد-لوري

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : base ; we check base ; base first ; the doctor checks base first
  - modèle : First, the doctor checks base.
- **q2.** What does the doctor check next?
  - accepté : brønsted-lowry acid ; then we check brønsted-lowry acid ; brønsted-lowry acid next ; the doctor checks brønsted-lowry acid next
  - modèle : Then, the doctor checks brønsted-lowry acid.
- **q3.** Does the doctor check brønsted-lowry acid before base?
  - accepté : no ; no he does not ; no he doesn't ; he checks base first
  - modèle : No, he checks base first, then brønsted-lowry acid.
  - hint (si erreur) — FR : Attention : Base vient D'ABORD, ensuite Acide de Brønsted-Lowry.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : brønsted-lowry base ; brønsted-lowry base first ; he checks brønsted-lowry base first
  - modèle : First, the doctor checks brønsted-lowry base.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check base. Then, we check brønsted-lowry acid.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Base de Brønsted-Lowry, ensuite Base, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check brønsted-lowry base. Then, we check base.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Base / Acide de Brønsted-Lowry, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the base.
   - FR : Le patient du lit 3 a Base.
   - AR : مريض السرير ٣ عنده القاعدة.
4. **EN** The patient in bed 5 has a problem with the brønsted-lowry acid.
   - FR : Le patient du lit 5 a Acide de Brønsted-Lowry.
   - AR : مريض السرير ٥ عنده حمض برونستد-لوري.
5. **EN** The patient in bed 5 does not have a problem with the base.
   - FR : Le patient du lit 5 n'a pas de Base.
   - AR : مريض السرير ٥ ليس عنده القاعدة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:base:v1` — **a base** · FR : Base · AR : القاعدة
- `gen:br-nsted-lowry-acid:v1` — **a brønsted-lowry acid** · FR : Acide de Brønsted-Lowry · AR : حمض برونستد-لوري
- `gen:br-nsted-lowry-base:v1` — **a brønsted-lowry base** · FR : Base de Brønsted-Lowry · AR : قاعدة برونستد-لوري

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : base ; a base ; the patient with base
  - modèle : The patient in bed 3 has a base.
- **q2.** What does the patient in bed 5 have?
  - accepté : brønsted-lowry acid ; a problem with the brønsted-lowry acid
  - modèle : He has a problem with the brønsted-lowry acid.
- **q3.** Does the patient in bed 5 have base?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the base ; he doesn't
  - modèle : No, he does not have a problem with the base.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS base — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : brønsted-lowry base ; a problem with the brønsted-lowry base
  - modèle : He has a problem with the brønsted-lowry base.
- **Tt2.** Does the patient in bed 9 have brønsted-lowry acid?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the brønsted-lowry acid ; he doesn't
  - modèle : No, he does not have a problem with the brønsted-lowry acid.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS acide de brønsted-lowry.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a base and a brønsted-lowry acid. (have)` → have · modèle : The patients have a base and a brønsted-lowry acid.
- `The patient in bed 5 … not have a base. (do)` → does · modèle : The patient in bed 5 does not have a base.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the base. The patient in bed 5 has a problem with the brønsted-lowry acid.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Base de Brønsted-Lowry, le lit 9 a Base et n'a pas Acide de Brønsted-Lowry.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the brønsted-lowry base. The patient in bed 9 has a problem with the base.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
