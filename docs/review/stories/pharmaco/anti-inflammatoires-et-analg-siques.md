# Relecture — Anti-inflammatoires et Analgésiques (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-000` — Le cas de Karim : Aspirine (acide acétylsalicylique) anormal(e) / _Karim: the aspirin is not normal_

**Objectif :** Comprendre des résultats : Médicament anti-inflammatoire normal(e), Aspirine (acide acétylsalicylique) anormal(e).
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
4. **EN** The doctor says, “The anti-inflammatory drug is normal.”
   - FR : Le médecin dit : « Médicament anti-inflammatoire est normal(e). »
   - AR : يقول الطبيب: «الدواء المضاد للالتهاب طبيعي/طبيعية.»
5. **EN** “But the aspirin is not normal.”
   - FR : « Mais Aspirine (acide acétylsalicylique) n'est pas normal(e). »
   - AR : «لكن الأسبرين غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the aspirin normal?”
   - FR : Lina demande : « Aspirine (acide acétylsalicylique) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الأسبرين طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:anti-inflammatory-drug:v1` — **an anti-inflammatory drug** · FR : Médicament anti-inflammatoire · AR : الدواء المضاد للالتهاب
- `gen:nsaid:v1` — **a NSAID** · FR : AINS (Anti-Inflammatoire Non Stéroïdien) · AR : مضاد الالتهاب اللاستيروئيدي
- `gen:aspirin:v1` — **an aspirin** · FR : Aspirine (acide acétylsalicylique) · AR : الأسبرين

### Questions (acceptées / modèle / hint)
- **q1.** Is the anti-inflammatory drug normal?
  - accepté : yes ; yes it is ; the anti-inflammatory drug is normal ; it is normal
  - modèle : Yes, the anti-inflammatory drug is normal.
- **q2.** Is the aspirin normal?
  - accepté : no ; no it is not ; no it isn't ; the aspirin is not normal ; it is not normal ; it isn't normal
  - modèle : No, the aspirin is not normal.
  - hint (si erreur) — FR : Attention : Aspirine (acide acétylsalicylique) n'est PAS normal(e).
- **q3.** Which one is not normal: the anti-inflammatory drug or the aspirin?
  - accepté : aspirin ; the aspirin
  - modèle : The aspirin is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the NSAID normal?
  - accepté : yes ; yes it is ; the NSAID is normal
  - modèle : Yes, the NSAID is normal.
- **Tt2.** Is the anti-inflammatory drug normal?
  - accepté : no ; no it is not ; no it isn't ; the anti-inflammatory drug is not normal ; it is not normal
  - modèle : No, the anti-inflammatory drug is not normal.
  - hint (si erreur) — FR : Attention : Médicament anti-inflammatoire n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The anti-inflammatory drug … normal. (be)` → is · modèle : The anti-inflammatory drug is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The anti-inflammatory drug is… / The aspirin is not… — modèle : *The anti-inflammatory drug is normal. The aspirin is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : AINS (Anti-Inflammatoire Non Stéroïdien) normal(e), mais Médicament anti-inflammatoire anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The NSAID is normal. The anti-inflammatory drug is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Médicament anti-inflammatoire et AINS (Anti-Inflammatoire Non Stéroïdien) à un patient.
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
4. **EN** “First, we check anti-inflammatory drug.”
   - FR : « D'abord, nous vérifions Médicament anti-inflammatoire. »
   - AR : «أولًا، نتحقق من الدواء المضاد للالتهاب.»
5. **EN** “Then, we check NSAID.”
   - FR : « Ensuite, nous vérifions AINS (Anti-Inflammatoire Non Stéroïdien). »
   - AR : «ثم نتحقق من مضاد الالتهاب اللاستيروئيدي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:anti-inflammatory-drug:v1` — **an anti-inflammatory drug** · FR : Médicament anti-inflammatoire · AR : الدواء المضاد للالتهاب
- `gen:nsaid:v1` — **a NSAID** · FR : AINS (Anti-Inflammatoire Non Stéroïdien) · AR : مضاد الالتهاب اللاستيروئيدي
- `gen:aspirin:v1` — **an aspirin** · FR : Aspirine (acide acétylsalicylique) · AR : الأسبرين

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : anti-inflammatory drug ; we check anti-inflammatory drug ; anti-inflammatory drug first ; the doctor checks anti-inflammatory drug first
  - modèle : First, the doctor checks anti-inflammatory drug.
- **q2.** What does the doctor check next?
  - accepté : NSAID ; then we check NSAID ; NSAID next ; the doctor checks NSAID next
  - modèle : Then, the doctor checks NSAID.
- **q3.** Does the doctor check NSAID before anti-inflammatory drug?
  - accepté : no ; no he does not ; no he doesn't ; he checks anti-inflammatory drug first
  - modèle : No, he checks anti-inflammatory drug first, then NSAID.
  - hint (si erreur) — FR : Attention : Médicament anti-inflammatoire vient D'ABORD, ensuite AINS (Anti-Inflammatoire Non Stéroïdien).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : aspirin ; aspirin first ; he checks aspirin first
  - modèle : First, the doctor checks aspirin.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check anti-inflammatory drug. Then, we check NSAID.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Aspirine (acide acétylsalicylique), ensuite Médicament anti-inflammatoire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check aspirin. Then, we check anti-inflammatory drug.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Médicament anti-inflammatoire / AINS (Anti-Inflammatoire Non Stéroïdien), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the anti-inflammatory drug.
   - FR : Le patient du lit 3 a Médicament anti-inflammatoire.
   - AR : مريض السرير ٣ عنده الدواء المضاد للالتهاب.
4. **EN** The patient in bed 5 has a problem with the NSAID.
   - FR : Le patient du lit 5 a AINS (Anti-Inflammatoire Non Stéroïdien).
   - AR : مريض السرير ٥ عنده مضاد الالتهاب اللاستيروئيدي.
5. **EN** The patient in bed 5 does not have a problem with the anti-inflammatory drug.
   - FR : Le patient du lit 5 n'a pas de Médicament anti-inflammatoire.
   - AR : مريض السرير ٥ ليس عنده الدواء المضاد للالتهاب.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:anti-inflammatory-drug:v1` — **an anti-inflammatory drug** · FR : Médicament anti-inflammatoire · AR : الدواء المضاد للالتهاب
- `gen:nsaid:v1` — **a NSAID** · FR : AINS (Anti-Inflammatoire Non Stéroïdien) · AR : مضاد الالتهاب اللاستيروئيدي
- `gen:aspirin:v1` — **an aspirin** · FR : Aspirine (acide acétylsalicylique) · AR : الأسبرين

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : anti-inflammatory drug ; an anti-inflammatory drug ; the patient with anti-inflammatory drug
  - modèle : The patient in bed 3 has an anti-inflammatory drug.
- **q2.** What does the patient in bed 5 have?
  - accepté : NSAID ; a problem with the NSAID
  - modèle : He has a problem with the NSAID.
- **q3.** Does the patient in bed 5 have anti-inflammatory drug?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the anti-inflammatory drug ; he doesn't
  - modèle : No, he does not have a problem with the anti-inflammatory drug.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS médicament anti-inflammatoire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : aspirin ; a problem with the aspirin
  - modèle : He has a problem with the aspirin.
- **Tt2.** Does the patient in bed 9 have NSAID?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the NSAID ; he doesn't
  - modèle : No, he does not have a problem with the NSAID.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS AINS (Anti-Inflammatoire Non Stéroïdien).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an anti-inflammatory drug and a NSAID. (have)` → have · modèle : The patients have an anti-inflammatory drug and a NSAID.
- `The patient in bed 5 … not have an anti-inflammatory drug. (do)` → does · modèle : The patient in bed 5 does not have an anti-inflammatory drug.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the anti-inflammatory drug. The patient in bed 5 has a problem with the NSAID.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Aspirine (acide acétylsalicylique), le lit 9 a Médicament anti-inflammatoire et n'a pas AINS (Anti-Inflammatoire Non Stéroïdien).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the aspirin. The patient in bed 9 has a problem with the anti-inflammatory drug.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
