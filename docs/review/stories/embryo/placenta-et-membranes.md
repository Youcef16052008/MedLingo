# Relecture — Placenta et Membranes (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-007` — Le cas de Hana : Chorion anormal(e) / _Hana: the chorion is not normal_

**Objectif :** Comprendre des résultats : Placenta normal(e), Chorion anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient at the clinic.
   - FR : Hana est un·e patient·e à la clinique.
   - AR : Hana مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The placenta is normal.”
   - FR : Le médecin dit : « Placenta est normal(e). »
   - AR : يقول الطبيب: «المشيمة طبيعي/طبيعية.»
5. **EN** “But the chorion is not normal.”
   - FR : « Mais Chorion n'est pas normal(e). »
   - AR : «لكن الغشاء المشيمي غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the chorion normal?”
   - FR : Amine demande : « Chorion est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الغشاء المشيمي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:placenta:v1` — **a placenta** · FR : Placenta · AR : المشيمة
- `gen:chorionic-villi:v1` — **a chorionic villi** · FR : Villosités choriales · AR : الزغابات المشيمية
- `gen:chorion:v1` — **a chorion** · FR : Chorion · AR : الغشاء المشيمي

### Questions (acceptées / modèle / hint)
- **q1.** Is the placenta normal?
  - accepté : yes ; yes it is ; the placenta is normal ; it is normal
  - modèle : Yes, the placenta is normal.
- **q2.** Is the chorion normal?
  - accepté : no ; no it is not ; no it isn't ; the chorion is not normal ; it is not normal ; it isn't normal
  - modèle : No, the chorion is not normal.
  - hint (si erreur) — FR : Attention : Chorion n'est PAS normal(e).
- **q3.** Which one is not normal: the placenta or the chorion?
  - accepté : chorion ; the chorion
  - modèle : The chorion is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the chorionic villi normal?
  - accepté : yes ; yes it is ; the chorionic villi is normal
  - modèle : Yes, the chorionic villi is normal.
- **Tt2.** Is the placenta normal?
  - accepté : no ; no it is not ; no it isn't ; the placenta is not normal ; it is not normal
  - modèle : No, the placenta is not normal.
  - hint (si erreur) — FR : Attention : Placenta n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The placenta … normal. (be)` → is · modèle : The placenta is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Hana en deux phrases. Garde la négation. — amorces : The placenta is… / The chorion is not… — modèle : *The placenta is normal. The chorion is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Villosités choriales normal(e), mais Placenta anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The chorionic villi is normal. The placenta is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-007` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Placenta et Villosités choriales à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient.
   - FR : Sara est un·e patient·e.
   - AR : Sara مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check placenta.”
   - FR : « D'abord, nous vérifions Placenta. »
   - AR : «أولًا، نتحقق من المشيمة.»
5. **EN** “Then, we check chorionic villi.”
   - FR : « Ensuite, nous vérifions Villosités choriales. »
   - AR : «ثم نتحقق من الزغابات المشيمية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:placenta:v1` — **a placenta** · FR : Placenta · AR : المشيمة
- `gen:chorionic-villi:v1` — **a chorionic villi** · FR : Villosités choriales · AR : الزغابات المشيمية
- `gen:chorion:v1` — **a chorion** · FR : Chorion · AR : الغشاء المشيمي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : placenta ; we check placenta ; placenta first ; the doctor checks placenta first
  - modèle : First, the doctor checks placenta.
- **q2.** What does the doctor check next?
  - accepté : chorionic villi ; then we check chorionic villi ; chorionic villi next ; the doctor checks chorionic villi next
  - modèle : Then, the doctor checks chorionic villi.
- **q3.** Does the doctor check chorionic villi before placenta?
  - accepté : no ; no he does not ; no he doesn't ; he checks placenta first
  - modèle : No, he checks placenta first, then chorionic villi.
  - hint (si erreur) — FR : Attention : Placenta vient D'ABORD, ensuite Villosités choriales.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : chorion ; chorion first ; he checks chorion first
  - modèle : First, the doctor checks chorion.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check placenta. Then, we check chorionic villi.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Chorion, ensuite Placenta, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check chorion. Then, we check placenta.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-007` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Placenta / Villosités choriales, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the placenta.
   - FR : Le patient du lit 3 a Placenta.
   - AR : مريض السرير ٣ عنده المشيمة.
4. **EN** The patient in bed 5 has a problem with the chorionic villi.
   - FR : Le patient du lit 5 a Villosités choriales.
   - AR : مريض السرير ٥ عنده الزغابات المشيمية.
5. **EN** The patient in bed 5 does not have a problem with the placenta.
   - FR : Le patient du lit 5 n'a pas de Placenta.
   - AR : مريض السرير ٥ ليس عنده المشيمة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:placenta:v1` — **a placenta** · FR : Placenta · AR : المشيمة
- `gen:chorionic-villi:v1` — **a chorionic villi** · FR : Villosités choriales · AR : الزغابات المشيمية
- `gen:chorion:v1` — **a chorion** · FR : Chorion · AR : الغشاء المشيمي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : placenta ; a placenta ; the patient with placenta
  - modèle : The patient in bed 3 has a placenta.
- **q2.** What does the patient in bed 5 have?
  - accepté : chorionic villi ; a problem with the chorionic villi
  - modèle : He has a problem with the chorionic villi.
- **q3.** Does the patient in bed 5 have placenta?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the placenta ; he doesn't
  - modèle : No, he does not have a problem with the placenta.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS placenta — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : chorion ; a problem with the chorion
  - modèle : He has a problem with the chorion.
- **Tt2.** Does the patient in bed 9 have chorionic villi?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the chorionic villi ; he doesn't
  - modèle : No, he does not have a problem with the chorionic villi.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS villosités choriales.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a placenta and a chorionic villi. (have)` → have · modèle : The patients have a placenta and a chorionic villi.
- `The patient in bed 5 … not have a placenta. (do)` → does · modèle : The patient in bed 5 does not have a placenta.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the placenta. The patient in bed 5 has a problem with the chorionic villi.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Chorion, le lit 9 a Placenta et n'a pas Villosités choriales.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the chorion. The patient in bed 9 has a problem with the placenta.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
