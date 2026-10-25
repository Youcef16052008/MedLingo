# Relecture — Termes Cliniques (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-034` — Le cas de Omar : Gestation (Grossesse) anormal(e) / _Omar: the gestation is not normal_

**Objectif :** Comprendre des résultats : Embryon normal(e), Gestation (Grossesse) anormal(e).
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
4. **EN** The doctor says, “The embryo is normal.”
   - FR : Le médecin dit : « Embryon est normal(e). »
   - AR : يقول الطبيب: «الجنين (المضغة) طبيعي/طبيعية.»
5. **EN** “But the gestation is not normal.”
   - FR : « Mais Gestation (Grossesse) n'est pas normal(e). »
   - AR : «لكن الحمل غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the gestation normal?”
   - FR : Lina demande : « Gestation (Grossesse) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الحمل طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:embryo:v1` — **an embryo** · FR : Embryon · AR : الجنين (المضغة)
- `gen:fetus:v1` — **fetus** · FR : Fœtus · AR : الجنين
- `gen:gestation:v1` — **a gestation** · FR : Gestation (Grossesse) · AR : الحمل

### Questions (acceptées / modèle / hint)
- **q1.** Is the embryo normal?
  - accepté : yes ; yes it is ; the embryo is normal ; it is normal
  - modèle : Yes, the embryo is normal.
- **q2.** Is the gestation normal?
  - accepté : no ; no it is not ; no it isn't ; the gestation is not normal ; it is not normal ; it isn't normal
  - modèle : No, the gestation is not normal.
  - hint (si erreur) — FR : Attention : Gestation (Grossesse) n'est PAS normal(e).
- **q3.** Which one is not normal: the embryo or the gestation?
  - accepté : gestation ; the gestation
  - modèle : The gestation is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the fetus normal?
  - accepté : yes ; yes it is ; the fetus is normal
  - modèle : Yes, the fetus is normal.
- **Tt2.** Is the embryo normal?
  - accepté : no ; no it is not ; no it isn't ; the embryo is not normal ; it is not normal
  - modèle : No, the embryo is not normal.
  - hint (si erreur) — FR : Attention : Embryon n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The embryo … normal. (be)` → is · modèle : The embryo is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The embryo is… / The gestation is not… — modèle : *The embryo is normal. The gestation is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Fœtus normal(e), mais Embryon anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The fetus is normal. The embryo is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-034` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Embryon et Fœtus à un patient.
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
4. **EN** “First, we check embryo.”
   - FR : « D'abord, nous vérifions Embryon. »
   - AR : «أولًا، نتحقق من الجنين (المضغة).»
5. **EN** “Then, we check fetus.”
   - FR : « Ensuite, nous vérifions Fœtus. »
   - AR : «ثم نتحقق من الجنين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:embryo:v1` — **an embryo** · FR : Embryon · AR : الجنين (المضغة)
- `gen:fetus:v1` — **fetus** · FR : Fœtus · AR : الجنين
- `gen:gestation:v1` — **a gestation** · FR : Gestation (Grossesse) · AR : الحمل

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : embryo ; we check embryo ; embryo first ; the doctor checks embryo first
  - modèle : First, the doctor checks embryo.
- **q2.** What does the doctor check next?
  - accepté : fetus ; then we check fetus ; fetus next ; the doctor checks fetus next
  - modèle : Then, the doctor checks fetus.
- **q3.** Does the doctor check fetus before embryo?
  - accepté : no ; no he does not ; no he doesn't ; he checks embryo first
  - modèle : No, he checks embryo first, then fetus.
  - hint (si erreur) — FR : Attention : Embryon vient D'ABORD, ensuite Fœtus.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : gestation ; gestation first ; he checks gestation first
  - modèle : First, the doctor checks gestation.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check embryo. Then, we check fetus.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Gestation (Grossesse), ensuite Embryon, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check gestation. Then, we check embryo.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-034` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Embryon / Fœtus, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the embryo.
   - FR : Le patient du lit 3 a Embryon.
   - AR : مريض السرير ٣ عنده الجنين (المضغة).
4. **EN** The patient in bed 5 has a problem with the fetus.
   - FR : Le patient du lit 5 a Fœtus.
   - AR : مريض السرير ٥ عنده الجنين.
5. **EN** The patient in bed 5 does not have a problem with the embryo.
   - FR : Le patient du lit 5 n'a pas de Embryon.
   - AR : مريض السرير ٥ ليس عنده الجنين (المضغة).
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:embryo:v1` — **an embryo** · FR : Embryon · AR : الجنين (المضغة)
- `gen:fetus:v1` — **fetus** · FR : Fœtus · AR : الجنين
- `gen:gestation:v1` — **a gestation** · FR : Gestation (Grossesse) · AR : الحمل

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : embryo ; an embryo ; the patient with embryo
  - modèle : The patient in bed 3 has an embryo.
- **q2.** What does the patient in bed 5 have?
  - accepté : fetus ; a problem with the fetus
  - modèle : He has a problem with the fetus.
- **q3.** Does the patient in bed 5 have embryo?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the embryo ; he doesn't
  - modèle : No, he does not have a problem with the embryo.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS embryon — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : gestation ; a problem with the gestation
  - modèle : He has a problem with the gestation.
- **Tt2.** Does the patient in bed 9 have fetus?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the fetus ; he doesn't
  - modèle : No, he does not have a problem with the fetus.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS fœtus.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an embryo and fetus. (have)` → have · modèle : The patients have an embryo and fetus.
- `The patient in bed 5 … not have an embryo. (do)` → does · modèle : The patient in bed 5 does not have an embryo.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the embryo. The patient in bed 5 has a problem with the fetus.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Gestation (Grossesse), le lit 9 a Embryon et n'a pas Fœtus.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the gestation. The patient in bed 9 has a problem with the embryo.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
