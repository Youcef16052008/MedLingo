# Relecture — Procédures Médicales (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-022` — Le cas de Omar : Suture anormal(e) / _Omar: the suture is not normal_

**Objectif :** Comprendre des résultats : Chirurgie normal(e), Suture anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The surgery is normal.”
   - FR : Le médecin dit : « Chirurgie est normal(e). »
   - AR : يقول الطبيب: «الجراحة طبيعي/طبيعية.»
5. **EN** “But the suture is not normal.”
   - FR : « Mais Suture n'est pas normal(e). »
   - AR : «لكن الخياطة الجراحية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the suture normal?”
   - FR : Lina demande : « Suture est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الخياطة الجراحية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:surgery:v1` — **a surgery** · FR : Chirurgie · AR : الجراحة
- `gen:incision:v1` — **an incision** · FR : Incision · AR : الشق الجراحي
- `gen:suture:v1` — **a suture** · FR : Suture · AR : الخياطة الجراحية

### Questions (acceptées / modèle / hint)
- **q1.** Is the surgery normal?
  - accepté : yes ; yes it is ; the surgery is normal ; it is normal
  - modèle : Yes, the surgery is normal.
- **q2.** Is the suture normal?
  - accepté : no ; no it is not ; no it isn't ; the suture is not normal ; it is not normal ; it isn't normal
  - modèle : No, the suture is not normal.
  - hint (si erreur) — FR : Attention : Suture n'est PAS normal(e).
- **q3.** Which one is not normal: the surgery or the suture?
  - accepté : suture ; the suture
  - modèle : The suture is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the incision normal?
  - accepté : yes ; yes it is ; the incision is normal
  - modèle : Yes, the incision is normal.
- **Tt2.** Is the surgery normal?
  - accepté : no ; no it is not ; no it isn't ; the surgery is not normal ; it is not normal
  - modèle : No, the surgery is not normal.
  - hint (si erreur) — FR : Attention : Chirurgie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The surgery … normal. (be)` → is · modèle : The surgery is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The surgery is… / The suture is not… — modèle : *The surgery is normal. The suture is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Incision normal(e), mais Chirurgie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The incision is normal. The surgery is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-022` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Chirurgie et Incision à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient.
   - FR : Karim est un·e patient·e.
   - AR : Karim مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check surgery.”
   - FR : « D'abord, nous vérifions Chirurgie. »
   - AR : «أولًا، نتحقق من الجراحة.»
5. **EN** “Then, we check incision.”
   - FR : « Ensuite, nous vérifions Incision. »
   - AR : «ثم نتحقق من الشق الجراحي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:surgery:v1` — **a surgery** · FR : Chirurgie · AR : الجراحة
- `gen:incision:v1` — **an incision** · FR : Incision · AR : الشق الجراحي
- `gen:suture:v1` — **a suture** · FR : Suture · AR : الخياطة الجراحية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : surgery ; we check surgery ; surgery first ; the doctor checks surgery first
  - modèle : First, the doctor checks surgery.
- **q2.** What does the doctor check next?
  - accepté : incision ; then we check incision ; incision next ; the doctor checks incision next
  - modèle : Then, the doctor checks incision.
- **q3.** Does the doctor check incision before surgery?
  - accepté : no ; no he does not ; no he doesn't ; he checks surgery first
  - modèle : No, he checks surgery first, then incision.
  - hint (si erreur) — FR : Attention : Chirurgie vient D'ABORD, ensuite Incision.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : suture ; suture first ; he checks suture first
  - modèle : First, the doctor checks suture.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check surgery. Then, we check incision.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Suture, ensuite Chirurgie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check suture. Then, we check surgery.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-022` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Chirurgie / Incision, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the surgery.
   - FR : Le patient du lit 3 a Chirurgie.
   - AR : مريض السرير ٣ عنده الجراحة.
4. **EN** The patient in bed 5 has a problem with the incision.
   - FR : Le patient du lit 5 a Incision.
   - AR : مريض السرير ٥ عنده الشق الجراحي.
5. **EN** The patient in bed 5 does not have a problem with the surgery.
   - FR : Le patient du lit 5 n'a pas de Chirurgie.
   - AR : مريض السرير ٥ ليس عنده الجراحة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:surgery:v1` — **a surgery** · FR : Chirurgie · AR : الجراحة
- `gen:incision:v1` — **an incision** · FR : Incision · AR : الشق الجراحي
- `gen:suture:v1` — **a suture** · FR : Suture · AR : الخياطة الجراحية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : surgery ; a surgery ; the patient with surgery
  - modèle : The patient in bed 3 has a surgery.
- **q2.** What does the patient in bed 5 have?
  - accepté : incision ; a problem with the incision
  - modèle : He has a problem with the incision.
- **q3.** Does the patient in bed 5 have surgery?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the surgery ; he doesn't
  - modèle : No, he does not have a problem with the surgery.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS chirurgie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : suture ; a problem with the suture
  - modèle : He has a problem with the suture.
- **Tt2.** Does the patient in bed 9 have incision?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the incision ; he doesn't
  - modèle : No, he does not have a problem with the incision.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS incision.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a surgery and an incision. (have)` → have · modèle : The patients have a surgery and an incision.
- `The patient in bed 5 … not have a surgery. (do)` → does · modèle : The patient in bed 5 does not have a surgery.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the surgery. The patient in bed 5 has a problem with the incision.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Suture, le lit 9 a Chirurgie et n'a pas Incision.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the suture. The patient in bed 9 has a problem with the surgery.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
