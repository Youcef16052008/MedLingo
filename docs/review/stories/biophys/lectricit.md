# Relecture — Électricité (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-012` — Le cas de Karim : Tension (Différence de potentiel) anormal(e) / _Karim: the voltage is not normal_

**Objectif :** Comprendre des résultats : Charge électrique normal(e), Tension (Différence de potentiel) anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient at the clinic.
   - FR : Karim est un·e patient·e à la clinique.
   - AR : Karim مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The electric charge is normal.”
   - FR : Le médecin dit : « Charge électrique est normal(e). »
   - AR : يقول الطبيب: «الشحنة الكهربائية طبيعي/طبيعية.»
5. **EN** “But the voltage is not normal.”
   - FR : « Mais Tension (Différence de potentiel) n'est pas normal(e). »
   - AR : «لكن الجهد الكهربائي (فرق الكمون) غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the voltage normal?”
   - FR : Lina demande : « Tension (Différence de potentiel) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الجهد الكهربائي (فرق الكمون) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:electric-charge:v1` — **an electric charge** · FR : Charge électrique · AR : الشحنة الكهربائية
- `gen:electric-current:v1` — **an electric current** · FR : Courant électrique · AR : التيار الكهربائي
- `gen:voltage:v1` — **a voltage** · FR : Tension (Différence de potentiel) · AR : الجهد الكهربائي (فرق الكمون)

### Questions (acceptées / modèle / hint)
- **q1.** Is the electric charge normal?
  - accepté : yes ; yes it is ; the electric charge is normal ; it is normal
  - modèle : Yes, the electric charge is normal.
- **q2.** Is the voltage normal?
  - accepté : no ; no it is not ; no it isn't ; the voltage is not normal ; it is not normal ; it isn't normal
  - modèle : No, the voltage is not normal.
  - hint (si erreur) — FR : Attention : Tension (Différence de potentiel) n'est PAS normal(e).
- **q3.** Which one is not normal: the electric charge or the voltage?
  - accepté : voltage ; the voltage
  - modèle : The voltage is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the electric current normal?
  - accepté : yes ; yes it is ; the electric current is normal
  - modèle : Yes, the electric current is normal.
- **Tt2.** Is the electric charge normal?
  - accepté : no ; no it is not ; no it isn't ; the electric charge is not normal ; it is not normal
  - modèle : No, the electric charge is not normal.
  - hint (si erreur) — FR : Attention : Charge électrique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The electric charge … normal. (be)` → is · modèle : The electric charge is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The electric charge is… / The voltage is not… — modèle : *The electric charge is normal. The voltage is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Courant électrique normal(e), mais Charge électrique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The electric current is normal. The electric charge is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-012` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Charge électrique et Courant électrique à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient.
   - FR : Youssef est un·e patient·e.
   - AR : Youssef مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check electric charge.”
   - FR : « D'abord, nous vérifions Charge électrique. »
   - AR : «أولًا، نتحقق من الشحنة الكهربائية.»
5. **EN** “Then, we check electric current.”
   - FR : « Ensuite, nous vérifions Courant électrique. »
   - AR : «ثم نتحقق من التيار الكهربائي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:electric-charge:v1` — **an electric charge** · FR : Charge électrique · AR : الشحنة الكهربائية
- `gen:electric-current:v1` — **an electric current** · FR : Courant électrique · AR : التيار الكهربائي
- `gen:voltage:v1` — **a voltage** · FR : Tension (Différence de potentiel) · AR : الجهد الكهربائي (فرق الكمون)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : electric charge ; we check electric charge ; electric charge first ; the doctor checks electric charge first
  - modèle : First, the doctor checks electric charge.
- **q2.** What does the doctor check next?
  - accepté : electric current ; then we check electric current ; electric current next ; the doctor checks electric current next
  - modèle : Then, the doctor checks electric current.
- **q3.** Does the doctor check electric current before electric charge?
  - accepté : no ; no he does not ; no he doesn't ; he checks electric charge first
  - modèle : No, he checks electric charge first, then electric current.
  - hint (si erreur) — FR : Attention : Charge électrique vient D'ABORD, ensuite Courant électrique.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : voltage ; voltage first ; he checks voltage first
  - modèle : First, the doctor checks voltage.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check electric charge. Then, we check electric current.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Tension (Différence de potentiel), ensuite Charge électrique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check voltage. Then, we check electric charge.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-012` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Charge électrique / Courant électrique, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the electric charge.
   - FR : Le patient du lit 3 a Charge électrique.
   - AR : مريض السرير ٣ عنده الشحنة الكهربائية.
4. **EN** The patient in bed 5 has a problem with the electric current.
   - FR : Le patient du lit 5 a Courant électrique.
   - AR : مريض السرير ٥ عنده التيار الكهربائي.
5. **EN** The patient in bed 5 does not have a problem with the electric charge.
   - FR : Le patient du lit 5 n'a pas de Charge électrique.
   - AR : مريض السرير ٥ ليس عنده الشحنة الكهربائية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:electric-charge:v1` — **an electric charge** · FR : Charge électrique · AR : الشحنة الكهربائية
- `gen:electric-current:v1` — **an electric current** · FR : Courant électrique · AR : التيار الكهربائي
- `gen:voltage:v1` — **a voltage** · FR : Tension (Différence de potentiel) · AR : الجهد الكهربائي (فرق الكمون)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : electric charge ; an electric charge ; the patient with electric charge
  - modèle : The patient in bed 3 has an electric charge.
- **q2.** What does the patient in bed 5 have?
  - accepté : electric current ; a problem with the electric current
  - modèle : He has a problem with the electric current.
- **q3.** Does the patient in bed 5 have electric charge?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the electric charge ; he doesn't
  - modèle : No, he does not have a problem with the electric charge.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS charge électrique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : voltage ; a problem with the voltage
  - modèle : He has a problem with the voltage.
- **Tt2.** Does the patient in bed 9 have electric current?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the electric current ; he doesn't
  - modèle : No, he does not have a problem with the electric current.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS courant électrique.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an electric charge and an electric current. (have)` → have · modèle : The patients have an electric charge and an electric current.
- `The patient in bed 5 … not have an electric charge. (do)` → does · modèle : The patient in bed 5 does not have an electric charge.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the electric charge. The patient in bed 5 has a problem with the electric current.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Tension (Différence de potentiel), le lit 9 a Charge électrique et n'a pas Courant électrique.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the voltage. The patient in bed 9 has a problem with the electric charge.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
