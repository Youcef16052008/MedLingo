# Relecture — Pharmacocinétique (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-010` — Le cas de Omar : Distribution anormal(e) / _Omar: the distribution is not normal_

**Objectif :** Comprendre des résultats : Absorption normal(e), Distribution anormal(e).
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
4. **EN** The doctor says, “The absorption is normal.”
   - FR : Le médecin dit : « Absorption est normal(e). »
   - AR : يقول الطبيب: «الامتصاص طبيعي/طبيعية.»
5. **EN** “But the distribution is not normal.”
   - FR : « Mais Distribution n'est pas normal(e). »
   - AR : «لكن التوزيع غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the distribution normal?”
   - FR : Lina demande : « Distribution est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل التوزيع طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:absorption:v1` — **an absorption** · FR : Absorption · AR : الامتصاص
- `gen:bioavailability:v1` — **a bioavailability** · FR : Biodisponibilité · AR : التوافر الحيوي
- `gen:distribution:v1` — **a distribution** · FR : Distribution · AR : التوزيع

### Questions (acceptées / modèle / hint)
- **q1.** Is the absorption normal?
  - accepté : yes ; yes it is ; the absorption is normal ; it is normal
  - modèle : Yes, the absorption is normal.
- **q2.** Is the distribution normal?
  - accepté : no ; no it is not ; no it isn't ; the distribution is not normal ; it is not normal ; it isn't normal
  - modèle : No, the distribution is not normal.
  - hint (si erreur) — FR : Attention : Distribution n'est PAS normal(e).
- **q3.** Which one is not normal: the absorption or the distribution?
  - accepté : distribution ; the distribution
  - modèle : The distribution is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the bioavailability normal?
  - accepté : yes ; yes it is ; the bioavailability is normal
  - modèle : Yes, the bioavailability is normal.
- **Tt2.** Is the absorption normal?
  - accepté : no ; no it is not ; no it isn't ; the absorption is not normal ; it is not normal
  - modèle : No, the absorption is not normal.
  - hint (si erreur) — FR : Attention : Absorption n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The absorption … normal. (be)` → is · modèle : The absorption is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The absorption is… / The distribution is not… — modèle : *The absorption is normal. The distribution is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Biodisponibilité normal(e), mais Absorption anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The bioavailability is normal. The absorption is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-010` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Absorption et Biodisponibilité à un patient.
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
4. **EN** “First, we check absorption.”
   - FR : « D'abord, nous vérifions Absorption. »
   - AR : «أولًا، نتحقق من الامتصاص.»
5. **EN** “Then, we check bioavailability.”
   - FR : « Ensuite, nous vérifions Biodisponibilité. »
   - AR : «ثم نتحقق من التوافر الحيوي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:absorption:v1` — **an absorption** · FR : Absorption · AR : الامتصاص
- `gen:bioavailability:v1` — **a bioavailability** · FR : Biodisponibilité · AR : التوافر الحيوي
- `gen:distribution:v1` — **a distribution** · FR : Distribution · AR : التوزيع

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : absorption ; we check absorption ; absorption first ; the doctor checks absorption first
  - modèle : First, the doctor checks absorption.
- **q2.** What does the doctor check next?
  - accepté : bioavailability ; then we check bioavailability ; bioavailability next ; the doctor checks bioavailability next
  - modèle : Then, the doctor checks bioavailability.
- **q3.** Does the doctor check bioavailability before absorption?
  - accepté : no ; no he does not ; no he doesn't ; he checks absorption first
  - modèle : No, he checks absorption first, then bioavailability.
  - hint (si erreur) — FR : Attention : Absorption vient D'ABORD, ensuite Biodisponibilité.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : distribution ; distribution first ; he checks distribution first
  - modèle : First, the doctor checks distribution.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check absorption. Then, we check bioavailability.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Distribution, ensuite Absorption, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check distribution. Then, we check absorption.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-010` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Absorption / Biodisponibilité, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the absorption.
   - FR : Le patient du lit 3 a Absorption.
   - AR : مريض السرير ٣ عنده الامتصاص.
4. **EN** The patient in bed 5 has a problem with the bioavailability.
   - FR : Le patient du lit 5 a Biodisponibilité.
   - AR : مريض السرير ٥ عنده التوافر الحيوي.
5. **EN** The patient in bed 5 does not have a problem with the absorption.
   - FR : Le patient du lit 5 n'a pas de Absorption.
   - AR : مريض السرير ٥ ليس عنده الامتصاص.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:absorption:v1` — **an absorption** · FR : Absorption · AR : الامتصاص
- `gen:bioavailability:v1` — **a bioavailability** · FR : Biodisponibilité · AR : التوافر الحيوي
- `gen:distribution:v1` — **a distribution** · FR : Distribution · AR : التوزيع

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : absorption ; an absorption ; the patient with absorption
  - modèle : The patient in bed 3 has an absorption.
- **q2.** What does the patient in bed 5 have?
  - accepté : bioavailability ; a problem with the bioavailability
  - modèle : He has a problem with the bioavailability.
- **q3.** Does the patient in bed 5 have absorption?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the absorption ; he doesn't
  - modèle : No, he does not have a problem with the absorption.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS absorption — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : distribution ; a problem with the distribution
  - modèle : He has a problem with the distribution.
- **Tt2.** Does the patient in bed 9 have bioavailability?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the bioavailability ; he doesn't
  - modèle : No, he does not have a problem with the bioavailability.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS biodisponibilité.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an absorption and a bioavailability. (have)` → have · modèle : The patients have an absorption and a bioavailability.
- `The patient in bed 5 … not have an absorption. (do)` → does · modèle : The patient in bed 5 does not have an absorption.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the absorption. The patient in bed 5 has a problem with the bioavailability.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Distribution, le lit 9 a Absorption et n'a pas Biodisponibilité.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the distribution. The patient in bed 9 has a problem with the absorption.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
