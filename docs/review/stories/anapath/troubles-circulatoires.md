# Relecture — Troubles Circulatoires (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-012` — Le cas de Karim : Thrombose anormal(e) / _Karim: the thrombosis is not normal_

**Objectif :** Comprendre des résultats : Hyperhémie normal(e), Thrombose anormal(e).
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
4. **EN** The doctor says, “The hyperemia is normal.”
   - FR : Le médecin dit : « Hyperhémie est normal(e). »
   - AR : يقول الطبيب: «الاحتقان طبيعي/طبيعية.»
5. **EN** “But the thrombosis is not normal.”
   - FR : « Mais Thrombose n'est pas normal(e). »
   - AR : «لكن التخثر الوريدي/الشرياني غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the thrombosis normal?”
   - FR : Lina demande : « Thrombose est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل التخثر الوريدي/الشرياني طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:hyperemia:v1` — **a hyperemia** · FR : Hyperhémie · AR : الاحتقان
- `gen:congestion:v1` — **a congestion** · FR : Congestion (Hyperhémie passive) · AR : الاحتقان السلبي
- `gen:thrombosis:v1` — **thrombosis** · FR : Thrombose · AR : التخثر الوريدي/الشرياني

### Questions (acceptées / modèle / hint)
- **q1.** Is the hyperemia normal?
  - accepté : yes ; yes it is ; the hyperemia is normal ; it is normal
  - modèle : Yes, the hyperemia is normal.
- **q2.** Is the thrombosis normal?
  - accepté : no ; no it is not ; no it isn't ; the thrombosis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the thrombosis is not normal.
  - hint (si erreur) — FR : Attention : Thrombose n'est PAS normal(e).
- **q3.** Which one is not normal: the hyperemia or the thrombosis?
  - accepté : thrombosis ; the thrombosis
  - modèle : The thrombosis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the congestion normal?
  - accepté : yes ; yes it is ; the congestion is normal
  - modèle : Yes, the congestion is normal.
- **Tt2.** Is the hyperemia normal?
  - accepté : no ; no it is not ; no it isn't ; the hyperemia is not normal ; it is not normal
  - modèle : No, the hyperemia is not normal.
  - hint (si erreur) — FR : Attention : Hyperhémie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The hyperemia … normal. (be)` → is · modèle : The hyperemia is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The hyperemia is… / The thrombosis is not… — modèle : *The hyperemia is normal. The thrombosis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Congestion (Hyperhémie passive) normal(e), mais Hyperhémie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The congestion is normal. The hyperemia is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-012` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Hyperhémie et Congestion (Hyperhémie passive) à un patient.
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
4. **EN** “First, we check hyperemia.”
   - FR : « D'abord, nous vérifions Hyperhémie. »
   - AR : «أولًا، نتحقق من الاحتقان.»
5. **EN** “Then, we check congestion.”
   - FR : « Ensuite, nous vérifions Congestion (Hyperhémie passive). »
   - AR : «ثم نتحقق من الاحتقان السلبي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:hyperemia:v1` — **a hyperemia** · FR : Hyperhémie · AR : الاحتقان
- `gen:congestion:v1` — **a congestion** · FR : Congestion (Hyperhémie passive) · AR : الاحتقان السلبي
- `gen:thrombosis:v1` — **thrombosis** · FR : Thrombose · AR : التخثر الوريدي/الشرياني

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : hyperemia ; we check hyperemia ; hyperemia first ; the doctor checks hyperemia first
  - modèle : First, the doctor checks hyperemia.
- **q2.** What does the doctor check next?
  - accepté : congestion ; then we check congestion ; congestion next ; the doctor checks congestion next
  - modèle : Then, the doctor checks congestion.
- **q3.** Does the doctor check congestion before hyperemia?
  - accepté : no ; no he does not ; no he doesn't ; he checks hyperemia first
  - modèle : No, he checks hyperemia first, then congestion.
  - hint (si erreur) — FR : Attention : Hyperhémie vient D'ABORD, ensuite Congestion (Hyperhémie passive).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : thrombosis ; thrombosis first ; he checks thrombosis first
  - modèle : First, the doctor checks thrombosis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check hyperemia. Then, we check congestion.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Thrombose, ensuite Hyperhémie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check thrombosis. Then, we check hyperemia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-012` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Hyperhémie / Congestion (Hyperhémie passive), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the hyperemia.
   - FR : Le patient du lit 3 a Hyperhémie.
   - AR : مريض السرير ٣ عنده الاحتقان.
4. **EN** The patient in bed 5 has a problem with the congestion.
   - FR : Le patient du lit 5 a Congestion (Hyperhémie passive).
   - AR : مريض السرير ٥ عنده الاحتقان السلبي.
5. **EN** The patient in bed 5 does not have a problem with the hyperemia.
   - FR : Le patient du lit 5 n'a pas de Hyperhémie.
   - AR : مريض السرير ٥ ليس عنده الاحتقان.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:hyperemia:v1` — **a hyperemia** · FR : Hyperhémie · AR : الاحتقان
- `gen:congestion:v1` — **a congestion** · FR : Congestion (Hyperhémie passive) · AR : الاحتقان السلبي
- `gen:thrombosis:v1` — **thrombosis** · FR : Thrombose · AR : التخثر الوريدي/الشرياني

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hyperemia ; a hyperemia ; the patient with hyperemia
  - modèle : The patient in bed 3 has a hyperemia.
- **q2.** What does the patient in bed 5 have?
  - accepté : congestion ; a problem with the congestion
  - modèle : He has a problem with the congestion.
- **q3.** Does the patient in bed 5 have hyperemia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the hyperemia ; he doesn't
  - modèle : No, he does not have a problem with the hyperemia.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS hyperhémie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : thrombosis ; a problem with the thrombosis
  - modèle : He has a problem with the thrombosis.
- **Tt2.** Does the patient in bed 9 have congestion?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the congestion ; he doesn't
  - modèle : No, he does not have a problem with the congestion.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS congestion (hyperhémie passive).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a hyperemia and a congestion. (have)` → have · modèle : The patients have a hyperemia and a congestion.
- `The patient in bed 5 … not have a hyperemia. (do)` → does · modèle : The patient in bed 5 does not have a hyperemia.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the hyperemia. The patient in bed 5 has a problem with the congestion.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Thrombose, le lit 9 a Hyperhémie et n'a pas Congestion (Hyperhémie passive).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the thrombosis. The patient in bed 9 has a problem with the hyperemia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
