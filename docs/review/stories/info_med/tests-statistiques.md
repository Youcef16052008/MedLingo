# Relecture — Tests Statistiques (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-010` — Le cas de Omar : Test t pour échantillons indépendants anormal(e) / _Omar: the independent samples t-test is not normal_

**Objectif :** Comprendre des résultats : Test Z normal(e), Test t pour échantillons indépendants anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The z-test is normal.”
   - FR : Le médecin dit : « Test Z est normal(e). »
   - AR : يقول الطبيب: «اختبار Z طبيعي/طبيعية.»
5. **EN** “But the independent samples t-test is not normal.”
   - FR : « Mais Test t pour échantillons indépendants n'est pas normal(e). »
   - AR : «لكن اختبار t للعينات المستقلة غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the independent samples t-test normal?”
   - FR : Lina demande : « Test t pour échantillons indépendants est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل اختبار t للعينات المستقلة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:z-test:v1` — **a z-test** · FR : Test Z · AR : اختبار Z
- `gen:t-test:v1` — **a t-test** · FR : Test t (Test t de Student) · AR : اختبار t (اختبار ستيودنت)
- `gen:independent-samples-t-test:v1` — **an independent samples t-test** · FR : Test t pour échantillons indépendants · AR : اختبار t للعينات المستقلة

### Questions (acceptées / modèle / hint)
- **q1.** Is the z-test normal?
  - accepté : yes ; yes it is ; the z-test is normal ; it is normal
  - modèle : Yes, the z-test is normal.
- **q2.** Is the independent samples t-test normal?
  - accepté : no ; no it is not ; no it isn't ; the independent samples t-test is not normal ; it is not normal ; it isn't normal
  - modèle : No, the independent samples t-test is not normal.
  - hint (si erreur) — FR : Attention : Test t pour échantillons indépendants n'est PAS normal(e).
- **q3.** Which one is not normal: the z-test or the independent samples t-test?
  - accepté : independent samples t-test ; the independent samples t-test
  - modèle : The independent samples t-test is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the t-test normal?
  - accepté : yes ; yes it is ; the t-test is normal
  - modèle : Yes, the t-test is normal.
- **Tt2.** Is the z-test normal?
  - accepté : no ; no it is not ; no it isn't ; the z-test is not normal ; it is not normal
  - modèle : No, the z-test is not normal.
  - hint (si erreur) — FR : Attention : Test Z n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The z-test … normal. (be)` → is · modèle : The z-test is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The z-test is… / The independent samples t-test is not… — modèle : *The z-test is normal. The independent samples t-test is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Test t (Test t de Student) normal(e), mais Test Z anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The t-test is normal. The z-test is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-010` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Test Z et Test t (Test t de Student) à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check z-test.”
   - FR : « D'abord, nous vérifions Test Z. »
   - AR : «أولًا، نتحقق من اختبار Z.»
5. **EN** “Then, we check t-test.”
   - FR : « Ensuite, nous vérifions Test t (Test t de Student). »
   - AR : «ثم نتحقق من اختبار t (اختبار ستيودنت).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:z-test:v1` — **a z-test** · FR : Test Z · AR : اختبار Z
- `gen:t-test:v1` — **a t-test** · FR : Test t (Test t de Student) · AR : اختبار t (اختبار ستيودنت)
- `gen:independent-samples-t-test:v1` — **an independent samples t-test** · FR : Test t pour échantillons indépendants · AR : اختبار t للعينات المستقلة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : z-test ; we check z-test ; z-test first ; the doctor checks z-test first
  - modèle : First, the doctor checks z-test.
- **q2.** What does the doctor check next?
  - accepté : t-test ; then we check t-test ; t-test next ; the doctor checks t-test next
  - modèle : Then, the doctor checks t-test.
- **q3.** Does the doctor check t-test before z-test?
  - accepté : no ; no he does not ; no he doesn't ; he checks z-test first
  - modèle : No, he checks z-test first, then t-test.
  - hint (si erreur) — FR : Attention : Test Z vient D'ABORD, ensuite Test t (Test t de Student).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : independent samples t-test ; independent samples t-test first ; he checks independent samples t-test first
  - modèle : First, the doctor checks independent samples t-test.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check z-test. Then, we check t-test.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Test t pour échantillons indépendants, ensuite Test Z, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check independent samples t-test. Then, we check z-test.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-010` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Test Z / Test t (Test t de Student), lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the z-test.
   - FR : Le patient du lit 3 a Test Z.
   - AR : مريض السرير ٣ عنده اختبار Z.
4. **EN** The patient in bed 5 has a problem with the t-test.
   - FR : Le patient du lit 5 a Test t (Test t de Student).
   - AR : مريض السرير ٥ عنده اختبار t (اختبار ستيودنت).
5. **EN** The patient in bed 5 does not have a problem with the z-test.
   - FR : Le patient du lit 5 n'a pas de Test Z.
   - AR : مريض السرير ٥ ليس عنده اختبار Z.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:z-test:v1` — **a z-test** · FR : Test Z · AR : اختبار Z
- `gen:t-test:v1` — **a t-test** · FR : Test t (Test t de Student) · AR : اختبار t (اختبار ستيودنت)
- `gen:independent-samples-t-test:v1` — **an independent samples t-test** · FR : Test t pour échantillons indépendants · AR : اختبار t للعينات المستقلة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : z-test ; a z-test ; the patient with z-test
  - modèle : The patient in bed 3 has a z-test.
- **q2.** What does the patient in bed 5 have?
  - accepté : t-test ; a problem with the t-test
  - modèle : He has a problem with the t-test.
- **q3.** Does the patient in bed 5 have z-test?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the z-test ; he doesn't
  - modèle : No, he does not have a problem with the z-test.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS test z — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : independent samples t-test ; a problem with the independent samples t-test
  - modèle : He has a problem with the independent samples t-test.
- **Tt2.** Does the patient in bed 9 have t-test?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the t-test ; he doesn't
  - modèle : No, he does not have a problem with the t-test.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS test t (test t de student).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a z-test and a t-test. (have)` → have · modèle : The patients have a z-test and a t-test.
- `The patient in bed 5 … not have a z-test. (do)` → does · modèle : The patient in bed 5 does not have a z-test.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the z-test. The patient in bed 5 has a problem with the t-test.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Test t pour échantillons indépendants, le lit 9 a Test Z et n'a pas Test t (Test t de Student).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the independent samples t-test. The patient in bed 9 has a problem with the z-test.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
