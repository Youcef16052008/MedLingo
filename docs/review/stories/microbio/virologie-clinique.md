# Relecture — Virologie Clinique (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-014` — Le cas de Youssef : Charge virale anormal(e) / _Youssef: the viral load is not normal_

**Objectif :** Comprendre des résultats : Médicament antiviral normal(e), Charge virale anormal(e).
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
4. **EN** The doctor says, “The antiviral drug is normal.”
   - FR : Le médecin dit : « Médicament antiviral est normal(e). »
   - AR : يقول الطبيب: «الدواء المضاد للفيروسات طبيعي/طبيعية.»
5. **EN** “But the viral load is not normal.”
   - FR : « Mais Charge virale n'est pas normal(e). »
   - AR : «لكن الحمل الفيروسي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the viral load normal?”
   - FR : Lina demande : « Charge virale est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الحمل الفيروسي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:antiviral-drug:v1` — **an antiviral drug** · FR : Médicament antiviral · AR : الدواء المضاد للفيروسات
- `gen:viral-shedding:v1` — **a viral shedding** · FR : Excrétion virale · AR : إطراح الفيروس
- `gen:viral-load:v1` — **a viral load** · FR : Charge virale · AR : الحمل الفيروسي

### Questions (acceptées / modèle / hint)
- **q1.** Is the antiviral drug normal?
  - accepté : yes ; yes it is ; the antiviral drug is normal ; it is normal
  - modèle : Yes, the antiviral drug is normal.
- **q2.** Is the viral load normal?
  - accepté : no ; no it is not ; no it isn't ; the viral load is not normal ; it is not normal ; it isn't normal
  - modèle : No, the viral load is not normal.
  - hint (si erreur) — FR : Attention : Charge virale n'est PAS normal(e).
- **q3.** Which one is not normal: the antiviral drug or the viral load?
  - accepté : viral load ; the viral load
  - modèle : The viral load is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the viral shedding normal?
  - accepté : yes ; yes it is ; the viral shedding is normal
  - modèle : Yes, the viral shedding is normal.
- **Tt2.** Is the antiviral drug normal?
  - accepté : no ; no it is not ; no it isn't ; the antiviral drug is not normal ; it is not normal
  - modèle : No, the antiviral drug is not normal.
  - hint (si erreur) — FR : Attention : Médicament antiviral n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The antiviral drug … normal. (be)` → is · modèle : The antiviral drug is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The antiviral drug is… / The viral load is not… — modèle : *The antiviral drug is normal. The viral load is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Excrétion virale normal(e), mais Médicament antiviral anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The viral shedding is normal. The antiviral drug is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-014` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Médicament antiviral et Excrétion virale à un patient.
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
4. **EN** “First, we check antiviral drug.”
   - FR : « D'abord, nous vérifions Médicament antiviral. »
   - AR : «أولًا، نتحقق من الدواء المضاد للفيروسات.»
5. **EN** “Then, we check viral shedding.”
   - FR : « Ensuite, nous vérifions Excrétion virale. »
   - AR : «ثم نتحقق من إطراح الفيروس.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:antiviral-drug:v1` — **an antiviral drug** · FR : Médicament antiviral · AR : الدواء المضاد للفيروسات
- `gen:viral-shedding:v1` — **a viral shedding** · FR : Excrétion virale · AR : إطراح الفيروس
- `gen:viral-load:v1` — **a viral load** · FR : Charge virale · AR : الحمل الفيروسي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : antiviral drug ; we check antiviral drug ; antiviral drug first ; the doctor checks antiviral drug first
  - modèle : First, the doctor checks antiviral drug.
- **q2.** What does the doctor check next?
  - accepté : viral shedding ; then we check viral shedding ; viral shedding next ; the doctor checks viral shedding next
  - modèle : Then, the doctor checks viral shedding.
- **q3.** Does the doctor check viral shedding before antiviral drug?
  - accepté : no ; no he does not ; no he doesn't ; he checks antiviral drug first
  - modèle : No, he checks antiviral drug first, then viral shedding.
  - hint (si erreur) — FR : Attention : Médicament antiviral vient D'ABORD, ensuite Excrétion virale.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : viral load ; viral load first ; he checks viral load first
  - modèle : First, the doctor checks viral load.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check antiviral drug. Then, we check viral shedding.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Charge virale, ensuite Médicament antiviral, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check viral load. Then, we check antiviral drug.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-014` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Médicament antiviral / Excrétion virale, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the antiviral drug.
   - FR : Le patient du lit 3 a Médicament antiviral.
   - AR : مريض السرير ٣ عنده الدواء المضاد للفيروسات.
4. **EN** The patient in bed 5 has a problem with the viral shedding.
   - FR : Le patient du lit 5 a Excrétion virale.
   - AR : مريض السرير ٥ عنده إطراح الفيروس.
5. **EN** The patient in bed 5 does not have a problem with the antiviral drug.
   - FR : Le patient du lit 5 n'a pas de Médicament antiviral.
   - AR : مريض السرير ٥ ليس عنده الدواء المضاد للفيروسات.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:antiviral-drug:v1` — **an antiviral drug** · FR : Médicament antiviral · AR : الدواء المضاد للفيروسات
- `gen:viral-shedding:v1` — **a viral shedding** · FR : Excrétion virale · AR : إطراح الفيروس
- `gen:viral-load:v1` — **a viral load** · FR : Charge virale · AR : الحمل الفيروسي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : antiviral drug ; an antiviral drug ; the patient with antiviral drug
  - modèle : The patient in bed 3 has an antiviral drug.
- **q2.** What does the patient in bed 5 have?
  - accepté : viral shedding ; a problem with the viral shedding
  - modèle : He has a problem with the viral shedding.
- **q3.** Does the patient in bed 5 have antiviral drug?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the antiviral drug ; he doesn't
  - modèle : No, he does not have a problem with the antiviral drug.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS médicament antiviral — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : viral load ; a problem with the viral load
  - modèle : He has a problem with the viral load.
- **Tt2.** Does the patient in bed 9 have viral shedding?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the viral shedding ; he doesn't
  - modèle : No, he does not have a problem with the viral shedding.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS excrétion virale.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an antiviral drug and a viral shedding. (have)` → have · modèle : The patients have an antiviral drug and a viral shedding.
- `The patient in bed 5 … not have an antiviral drug. (do)` → does · modèle : The patient in bed 5 does not have an antiviral drug.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the antiviral drug. The patient in bed 5 has a problem with the viral shedding.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Charge virale, le lit 9 a Médicament antiviral et n'a pas Excrétion virale.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the viral load. The patient in bed 9 has a problem with the antiviral drug.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
