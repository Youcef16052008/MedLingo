# Relecture — Ophthalmology (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-027` — Le cas de Amel : Sécheresse oculaire anormal(e) / _Amel: the dry eyes is not normal_

**Objectif :** Comprendre des résultats : Infection oculaire normal(e), Sécheresse oculaire anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The eye infection is normal.”
   - FR : Le médecin dit : « Infection oculaire est normal(e). »
   - AR : يقول الطبيب: «التهاب العين طبيعي/طبيعية.»
5. **EN** “But the dry eyes is not normal.”
   - FR : « Mais Sécheresse oculaire n'est pas normal(e). »
   - AR : «لكن جفاف العيون غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the dry eyes normal?”
   - FR : Amine demande : « Sécheresse oculaire est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل جفاف العيون طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:eye-infection:v1` — **an eye infection** · FR : Infection oculaire · AR : التهاب العين
- `gen:conjunctivitis:v1` — **conjunctivitis** · FR : Conjonctivite · AR : التهاب الملتحمة
- `gen:dry-eyes:v1` — **dry eyes** · FR : Sécheresse oculaire · AR : جفاف العيون

### Questions (acceptées / modèle / hint)
- **q1.** Is the eye infection normal?
  - accepté : yes ; yes it is ; the eye infection is normal ; it is normal
  - modèle : Yes, the eye infection is normal.
- **q2.** Is the dry eyes normal?
  - accepté : no ; no it is not ; no it isn't ; the dry eyes is not normal ; it is not normal ; it isn't normal
  - modèle : No, the dry eyes is not normal.
  - hint (si erreur) — FR : Attention : Sécheresse oculaire n'est PAS normal(e).
- **q3.** Which one is not normal: the eye infection or the dry eyes?
  - accepté : dry eyes ; the dry eyes
  - modèle : The dry eyes is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the conjunctivitis normal?
  - accepté : yes ; yes it is ; the conjunctivitis is normal
  - modèle : Yes, the conjunctivitis is normal.
- **Tt2.** Is the eye infection normal?
  - accepté : no ; no it is not ; no it isn't ; the eye infection is not normal ; it is not normal
  - modèle : No, the eye infection is not normal.
  - hint (si erreur) — FR : Attention : Infection oculaire n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The eye infection … normal. (be)` → is · modèle : The eye infection is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The eye infection is… / The dry eyes is not… — modèle : *The eye infection is normal. The dry eyes is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Conjonctivite normal(e), mais Infection oculaire anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The conjunctivitis is normal. The eye infection is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-027` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Infection oculaire et Conjonctivite à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient.
   - FR : Meriem est un·e patient·e.
   - AR : Meriem مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check eye infection.”
   - FR : « D'abord, nous vérifions Infection oculaire. »
   - AR : «أولًا، نتحقق من التهاب العين.»
5. **EN** “Then, we check conjunctivitis.”
   - FR : « Ensuite, nous vérifions Conjonctivite. »
   - AR : «ثم نتحقق من التهاب الملتحمة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:eye-infection:v1` — **an eye infection** · FR : Infection oculaire · AR : التهاب العين
- `gen:conjunctivitis:v1` — **conjunctivitis** · FR : Conjonctivite · AR : التهاب الملتحمة
- `gen:dry-eyes:v1` — **dry eyes** · FR : Sécheresse oculaire · AR : جفاف العيون

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : eye infection ; we check eye infection ; eye infection first ; the doctor checks eye infection first
  - modèle : First, the doctor checks eye infection.
- **q2.** What does the doctor check next?
  - accepté : conjunctivitis ; then we check conjunctivitis ; conjunctivitis next ; the doctor checks conjunctivitis next
  - modèle : Then, the doctor checks conjunctivitis.
- **q3.** Does the doctor check conjunctivitis before eye infection?
  - accepté : no ; no he does not ; no he doesn't ; he checks eye infection first
  - modèle : No, he checks eye infection first, then conjunctivitis.
  - hint (si erreur) — FR : Attention : Infection oculaire vient D'ABORD, ensuite Conjonctivite.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : dry eyes ; dry eyes first ; he checks dry eyes first
  - modèle : First, the doctor checks dry eyes.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check eye infection. Then, we check conjunctivitis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Sécheresse oculaire, ensuite Infection oculaire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check dry eyes. Then, we check eye infection.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-027` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Infection oculaire / Conjonctivite, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the eye infection.
   - FR : Le patient du lit 3 a Infection oculaire.
   - AR : مريض السرير ٣ عنده التهاب العين.
4. **EN** The patient in bed 5 has a problem with the conjunctivitis.
   - FR : Le patient du lit 5 a Conjonctivite.
   - AR : مريض السرير ٥ عنده التهاب الملتحمة.
5. **EN** The patient in bed 5 does not have a problem with the eye infection.
   - FR : Le patient du lit 5 n'a pas de Infection oculaire.
   - AR : مريض السرير ٥ ليس عنده التهاب العين.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:eye-infection:v1` — **an eye infection** · FR : Infection oculaire · AR : التهاب العين
- `gen:conjunctivitis:v1` — **conjunctivitis** · FR : Conjonctivite · AR : التهاب الملتحمة
- `gen:dry-eyes:v1` — **dry eyes** · FR : Sécheresse oculaire · AR : جفاف العيون

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : eye infection ; an eye infection ; the patient with eye infection
  - modèle : The patient in bed 3 has an eye infection.
- **q2.** What does the patient in bed 5 have?
  - accepté : conjunctivitis ; a problem with the conjunctivitis
  - modèle : He has a problem with the conjunctivitis.
- **q3.** Does the patient in bed 5 have eye infection?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the eye infection ; he doesn't
  - modèle : No, he does not have a problem with the eye infection.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS infection oculaire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : dry eyes ; a problem with the dry eyes
  - modèle : He has a problem with the dry eyes.
- **Tt2.** Does the patient in bed 9 have conjunctivitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the conjunctivitis ; he doesn't
  - modèle : No, he does not have a problem with the conjunctivitis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS conjonctivite.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an eye infection and conjunctivitis. (have)` → have · modèle : The patients have an eye infection and conjunctivitis.
- `The patient in bed 5 … not have an eye infection. (do)` → does · modèle : The patient in bed 5 does not have an eye infection.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the eye infection. The patient in bed 5 has a problem with the conjunctivitis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Sécheresse oculaire, le lit 9 a Infection oculaire et n'a pas Conjonctivite.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the dry eyes. The patient in bed 9 has a problem with the eye infection.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
