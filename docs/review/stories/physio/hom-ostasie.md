# Relecture — Homéostasie (physio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `physio-case-000` — Le cas de Karim : Alcalose anormal(e) / _Karim: the alkalosis is not normal_

**Objectif :** Comprendre des résultats : pH normal(e), Alcalose anormal(e).
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
4. **EN** The doctor says, “The ph is normal.”
   - FR : Le médecin dit : « pH est normal(e). »
   - AR : يقول الطبيب: «الأس الهيدروجيني طبيعي/طبيعية.»
5. **EN** “But the alkalosis is not normal.”
   - FR : « Mais Alcalose n'est pas normal(e). »
   - AR : «لكن القلاء غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the alkalosis normal?”
   - FR : Lina demande : « Alcalose est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل القلاء طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:ph:v1` — **a ph** · FR : pH · AR : الأس الهيدروجيني
- `gen:acidosis:v1` — **acidosis** · FR : Acidose · AR : الحماض
- `gen:alkalosis:v1` — **alkalosis** · FR : Alcalose · AR : القلاء

### Questions (acceptées / modèle / hint)
- **q1.** Is the ph normal?
  - accepté : yes ; yes it is ; the ph is normal ; it is normal
  - modèle : Yes, the ph is normal.
- **q2.** Is the alkalosis normal?
  - accepté : no ; no it is not ; no it isn't ; the alkalosis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the alkalosis is not normal.
  - hint (si erreur) — FR : Attention : Alcalose n'est PAS normal(e).
- **q3.** Which one is not normal: the ph or the alkalosis?
  - accepté : alkalosis ; the alkalosis
  - modèle : The alkalosis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the acidosis normal?
  - accepté : yes ; yes it is ; the acidosis is normal
  - modèle : Yes, the acidosis is normal.
- **Tt2.** Is the ph normal?
  - accepté : no ; no it is not ; no it isn't ; the ph is not normal ; it is not normal
  - modèle : No, the ph is not normal.
  - hint (si erreur) — FR : Attention : pH n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The ph … normal. (be)` → is · modèle : The ph is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The ph is… / The alkalosis is not… — modèle : *The ph is normal. The alkalosis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Acidose normal(e), mais pH anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The acidosis is normal. The ph is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `physio-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique pH et Acidose à un patient.
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
4. **EN** “First, we check ph.”
   - FR : « D'abord, nous vérifions pH. »
   - AR : «أولًا، نتحقق من الأس الهيدروجيني.»
5. **EN** “Then, we check acidosis.”
   - FR : « Ensuite, nous vérifions Acidose. »
   - AR : «ثم نتحقق من الحماض.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:ph:v1` — **a ph** · FR : pH · AR : الأس الهيدروجيني
- `gen:acidosis:v1` — **acidosis** · FR : Acidose · AR : الحماض
- `gen:alkalosis:v1` — **alkalosis** · FR : Alcalose · AR : القلاء

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : ph ; we check ph ; ph first ; the doctor checks ph first
  - modèle : First, the doctor checks ph.
- **q2.** What does the doctor check next?
  - accepté : acidosis ; then we check acidosis ; acidosis next ; the doctor checks acidosis next
  - modèle : Then, the doctor checks acidosis.
- **q3.** Does the doctor check acidosis before ph?
  - accepté : no ; no he does not ; no he doesn't ; he checks ph first
  - modèle : No, he checks ph first, then acidosis.
  - hint (si erreur) — FR : Attention : pH vient D'ABORD, ensuite Acidose.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : alkalosis ; alkalosis first ; he checks alkalosis first
  - modèle : First, the doctor checks alkalosis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check ph. Then, we check acidosis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Alcalose, ensuite pH, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check alkalosis. Then, we check ph.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `physio-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (pH / Acidose, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the ph.
   - FR : Le patient du lit 3 a pH.
   - AR : مريض السرير ٣ عنده الأس الهيدروجيني.
4. **EN** The patient in bed 5 has a problem with the acidosis.
   - FR : Le patient du lit 5 a Acidose.
   - AR : مريض السرير ٥ عنده الحماض.
5. **EN** The patient in bed 5 does not have a problem with the ph.
   - FR : Le patient du lit 5 n'a pas de pH.
   - AR : مريض السرير ٥ ليس عنده الأس الهيدروجيني.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:ph:v1` — **a ph** · FR : pH · AR : الأس الهيدروجيني
- `gen:acidosis:v1` — **acidosis** · FR : Acidose · AR : الحماض
- `gen:alkalosis:v1` — **alkalosis** · FR : Alcalose · AR : القلاء

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : ph ; a ph ; the patient with ph
  - modèle : The patient in bed 3 has a ph.
- **q2.** What does the patient in bed 5 have?
  - accepté : acidosis ; a problem with the acidosis
  - modèle : He has a problem with the acidosis.
- **q3.** Does the patient in bed 5 have ph?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the ph ; he doesn't
  - modèle : No, he does not have a problem with the ph.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS ph — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : alkalosis ; a problem with the alkalosis
  - modèle : He has a problem with the alkalosis.
- **Tt2.** Does the patient in bed 9 have acidosis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the acidosis ; he doesn't
  - modèle : No, he does not have a problem with the acidosis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS acidose.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a ph and acidosis. (have)` → have · modèle : The patients have a ph and acidosis.
- `The patient in bed 5 … not have a ph. (do)` → does · modèle : The patient in bed 5 does not have a ph.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the ph. The patient in bed 5 has a problem with the acidosis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Alcalose, le lit 9 a pH et n'a pas Acidose.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the alkalosis. The patient in bed 9 has a problem with the ph.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
