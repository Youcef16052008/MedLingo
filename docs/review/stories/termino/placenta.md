# Relecture — Placenta (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-021` — Le cas de Sara : Amnios anormal(e) / _Sara: the amnion is not normal_

**Objectif :** Comprendre des résultats : Placenta normal(e), Amnios anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The placenta is normal.”
   - FR : Le médecin dit : « Placenta est normal(e). »
   - AR : يقول الطبيب: «المشيمة طبيعي/طبيعية.»
5. **EN** “But the amnion is not normal.”
   - FR : « Mais Amnios n'est pas normal(e). »
   - AR : «لكن السلى (الغشاء الأمنيوسي) غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the amnion normal?”
   - FR : Amine demande : « Amnios est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل السلى (الغشاء الأمنيوسي) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:placenta:v1` — **a placenta** · FR : Placenta · AR : المشيمة
- `gen:chorion:v1` — **a chorion** · FR : Chorion · AR : المشيماء
- `gen:amnion:v1` — **an amnion** · FR : Amnios · AR : السلى (الغشاء الأمنيوسي)

### Questions (acceptées / modèle / hint)
- **q1.** Is the placenta normal?
  - accepté : yes ; yes it is ; the placenta is normal ; it is normal
  - modèle : Yes, the placenta is normal.
- **q2.** Is the amnion normal?
  - accepté : no ; no it is not ; no it isn't ; the amnion is not normal ; it is not normal ; it isn't normal
  - modèle : No, the amnion is not normal.
  - hint (si erreur) — FR : Attention : Amnios n'est PAS normal(e).
- **q3.** Which one is not normal: the placenta or the amnion?
  - accepté : amnion ; the amnion
  - modèle : The amnion is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the chorion normal?
  - accepté : yes ; yes it is ; the chorion is normal
  - modèle : Yes, the chorion is normal.
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
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The placenta is… / The amnion is not… — modèle : *The placenta is normal. The amnion is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Chorion normal(e), mais Placenta anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The chorion is normal. The placenta is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-021` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Placenta et Chorion à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient.
   - FR : Leila est un·e patient·e.
   - AR : Leila مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check placenta.”
   - FR : « D'abord, nous vérifions Placenta. »
   - AR : «أولًا، نتحقق من المشيمة.»
5. **EN** “Then, we check chorion.”
   - FR : « Ensuite, nous vérifions Chorion. »
   - AR : «ثم نتحقق من المشيماء.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:placenta:v1` — **a placenta** · FR : Placenta · AR : المشيمة
- `gen:chorion:v1` — **a chorion** · FR : Chorion · AR : المشيماء
- `gen:amnion:v1` — **an amnion** · FR : Amnios · AR : السلى (الغشاء الأمنيوسي)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : placenta ; we check placenta ; placenta first ; the doctor checks placenta first
  - modèle : First, the doctor checks placenta.
- **q2.** What does the doctor check next?
  - accepté : chorion ; then we check chorion ; chorion next ; the doctor checks chorion next
  - modèle : Then, the doctor checks chorion.
- **q3.** Does the doctor check chorion before placenta?
  - accepté : no ; no he does not ; no he doesn't ; he checks placenta first
  - modèle : No, he checks placenta first, then chorion.
  - hint (si erreur) — FR : Attention : Placenta vient D'ABORD, ensuite Chorion.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : amnion ; amnion first ; he checks amnion first
  - modèle : First, the doctor checks amnion.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check placenta. Then, we check chorion.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Amnios, ensuite Placenta, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check amnion. Then, we check placenta.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-021` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Placenta / Chorion, lits 3 et 5).
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
4. **EN** The patient in bed 5 has a problem with the chorion.
   - FR : Le patient du lit 5 a Chorion.
   - AR : مريض السرير ٥ عنده المشيماء.
5. **EN** The patient in bed 5 does not have a problem with the placenta.
   - FR : Le patient du lit 5 n'a pas de Placenta.
   - AR : مريض السرير ٥ ليس عنده المشيمة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:placenta:v1` — **a placenta** · FR : Placenta · AR : المشيمة
- `gen:chorion:v1` — **a chorion** · FR : Chorion · AR : المشيماء
- `gen:amnion:v1` — **an amnion** · FR : Amnios · AR : السلى (الغشاء الأمنيوسي)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : placenta ; a placenta ; the patient with placenta
  - modèle : The patient in bed 3 has a placenta.
- **q2.** What does the patient in bed 5 have?
  - accepté : chorion ; a problem with the chorion
  - modèle : He has a problem with the chorion.
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
  - accepté : amnion ; a problem with the amnion
  - modèle : He has a problem with the amnion.
- **Tt2.** Does the patient in bed 9 have chorion?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the chorion ; he doesn't
  - modèle : No, he does not have a problem with the chorion.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS chorion.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a placenta and a chorion. (have)` → have · modèle : The patients have a placenta and a chorion.
- `The patient in bed 5 … not have a placenta. (do)` → does · modèle : The patient in bed 5 does not have a placenta.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the placenta. The patient in bed 5 has a problem with the chorion.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Amnios, le lit 9 a Placenta et n'a pas Chorion.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the amnion. The patient in bed 9 has a problem with the placenta.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
