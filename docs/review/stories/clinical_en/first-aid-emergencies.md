# Relecture — First Aid & Emergencies (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-010` — Le cas de Omar : Ambulance anormal(e) / _Omar: the ambulance is not normal_

**Objectif :** Comprendre des résultats : Premiers secours normal(e), Ambulance anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The first aid is normal.”
   - FR : Le médecin dit : « Premiers secours est normal(e). »
   - AR : يقول الطبيب: «الإسعافات الأولية طبيعي/طبيعية.»
5. **EN** “But the ambulance is not normal.”
   - FR : « Mais Ambulance n'est pas normal(e). »
   - AR : «لكن سيارة الإسعاف غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the ambulance normal?”
   - FR : Lina demande : « Ambulance est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل سيارة الإسعاف طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:first-aid:v1` — **a first aid** · FR : Premiers secours · AR : الإسعافات الأولية
- `gen:cpr:v1` — **a CPR** · FR : RCP (Réanimation) · AR : الإنعاش القلبي الرئوي
- `gen:ambulance:v1` — **an ambulance** · FR : Ambulance · AR : سيارة الإسعاف

### Questions (acceptées / modèle / hint)
- **q1.** Is the first aid normal?
  - accepté : yes ; yes it is ; the first aid is normal ; it is normal
  - modèle : Yes, the first aid is normal.
- **q2.** Is the ambulance normal?
  - accepté : no ; no it is not ; no it isn't ; the ambulance is not normal ; it is not normal ; it isn't normal
  - modèle : No, the ambulance is not normal.
  - hint (si erreur) — FR : Attention : Ambulance n'est PAS normal(e).
- **q3.** Which one is not normal: the first aid or the ambulance?
  - accepté : ambulance ; the ambulance
  - modèle : The ambulance is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the CPR normal?
  - accepté : yes ; yes it is ; the CPR is normal
  - modèle : Yes, the CPR is normal.
- **Tt2.** Is the first aid normal?
  - accepté : no ; no it is not ; no it isn't ; the first aid is not normal ; it is not normal
  - modèle : No, the first aid is not normal.
  - hint (si erreur) — FR : Attention : Premiers secours n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The first aid … normal. (be)` → is · modèle : The first aid is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The first aid is… / The ambulance is not… — modèle : *The first aid is normal. The ambulance is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : RCP (Réanimation) normal(e), mais Premiers secours anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The CPR is normal. The first aid is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-010` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Premiers secours et RCP (Réanimation) à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check first aid.”
   - FR : « D'abord, nous vérifions Premiers secours. »
   - AR : «أولًا، نتحقق من الإسعافات الأولية.»
5. **EN** “Then, we check CPR.”
   - FR : « Ensuite, nous vérifions RCP (Réanimation). »
   - AR : «ثم نتحقق من الإنعاش القلبي الرئوي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:first-aid:v1` — **a first aid** · FR : Premiers secours · AR : الإسعافات الأولية
- `gen:cpr:v1` — **a CPR** · FR : RCP (Réanimation) · AR : الإنعاش القلبي الرئوي
- `gen:ambulance:v1` — **an ambulance** · FR : Ambulance · AR : سيارة الإسعاف

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : first aid ; we check first aid ; first aid first ; the doctor checks first aid first
  - modèle : First, the doctor checks first aid.
- **q2.** What does the doctor check next?
  - accepté : CPR ; then we check CPR ; CPR next ; the doctor checks CPR next
  - modèle : Then, the doctor checks CPR.
- **q3.** Does the doctor check CPR before first aid?
  - accepté : no ; no he does not ; no he doesn't ; he checks first aid first
  - modèle : No, he checks first aid first, then CPR.
  - hint (si erreur) — FR : Attention : Premiers secours vient D'ABORD, ensuite RCP (Réanimation).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : ambulance ; ambulance first ; he checks ambulance first
  - modèle : First, the doctor checks ambulance.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check first aid. Then, we check CPR.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Ambulance, ensuite Premiers secours, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check ambulance. Then, we check first aid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-010` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Premiers secours / RCP (Réanimation), lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the first aid.
   - FR : Le patient du lit 3 a Premiers secours.
   - AR : مريض السرير ٣ عنده الإسعافات الأولية.
4. **EN** The patient in bed 5 has a problem with the CPR.
   - FR : Le patient du lit 5 a RCP (Réanimation).
   - AR : مريض السرير ٥ عنده الإنعاش القلبي الرئوي.
5. **EN** The patient in bed 5 does not have a problem with the first aid.
   - FR : Le patient du lit 5 n'a pas de Premiers secours.
   - AR : مريض السرير ٥ ليس عنده الإسعافات الأولية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:first-aid:v1` — **a first aid** · FR : Premiers secours · AR : الإسعافات الأولية
- `gen:cpr:v1` — **a CPR** · FR : RCP (Réanimation) · AR : الإنعاش القلبي الرئوي
- `gen:ambulance:v1` — **an ambulance** · FR : Ambulance · AR : سيارة الإسعاف

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : first aid ; a first aid ; the patient with first aid
  - modèle : The patient in bed 3 has a first aid.
- **q2.** What does the patient in bed 5 have?
  - accepté : CPR ; a problem with the CPR
  - modèle : He has a problem with the CPR.
- **q3.** Does the patient in bed 5 have first aid?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the first aid ; he doesn't
  - modèle : No, he does not have a problem with the first aid.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS premiers secours — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : ambulance ; a problem with the ambulance
  - modèle : He has a problem with the ambulance.
- **Tt2.** Does the patient in bed 9 have CPR?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the CPR ; he doesn't
  - modèle : No, he does not have a problem with the CPR.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS RCP (Réanimation).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a first aid and a CPR. (have)` → have · modèle : The patients have a first aid and a CPR.
- `The patient in bed 5 … not have a first aid. (do)` → does · modèle : The patient in bed 5 does not have a first aid.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the first aid. The patient in bed 5 has a problem with the CPR.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Ambulance, le lit 9 a Premiers secours et n'a pas RCP (Réanimation).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the ambulance. The patient in bed 9 has a problem with the first aid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
