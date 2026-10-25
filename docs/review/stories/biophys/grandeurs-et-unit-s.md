# Relecture — Grandeurs et Unités (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-003` — Le cas de Amel : Longueur anormal(e) / _Amel: the length is not normal_

**Objectif :** Comprendre des résultats : Biophysique normal(e), Longueur anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The biophysics is normal.”
   - FR : Le médecin dit : « Biophysique est normal(e). »
   - AR : يقول الطبيب: «الفيزياء الحيوية طبيعي/طبيعية.»
5. **EN** “But the length is not normal.”
   - FR : « Mais Longueur n'est pas normal(e). »
   - AR : «لكن الطول غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the length normal?”
   - FR : Amine demande : « Longueur est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الطول طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:biophysics:v1` — **biophysics** · FR : Biophysique · AR : الفيزياء الحيوية
- `gen:mass:v1` — **a mass** · FR : Masse · AR : الكتلة
- `gen:length:v1` — **a length** · FR : Longueur · AR : الطول

### Questions (acceptées / modèle / hint)
- **q1.** Is the biophysics normal?
  - accepté : yes ; yes it is ; the biophysics is normal ; it is normal
  - modèle : Yes, the biophysics is normal.
- **q2.** Is the length normal?
  - accepté : no ; no it is not ; no it isn't ; the length is not normal ; it is not normal ; it isn't normal
  - modèle : No, the length is not normal.
  - hint (si erreur) — FR : Attention : Longueur n'est PAS normal(e).
- **q3.** Which one is not normal: the biophysics or the length?
  - accepté : length ; the length
  - modèle : The length is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the mass normal?
  - accepté : yes ; yes it is ; the mass is normal
  - modèle : Yes, the mass is normal.
- **Tt2.** Is the biophysics normal?
  - accepté : no ; no it is not ; no it isn't ; the biophysics is not normal ; it is not normal
  - modèle : No, the biophysics is not normal.
  - hint (si erreur) — FR : Attention : Biophysique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The biophysics … normal. (be)` → is · modèle : The biophysics is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The biophysics is… / The length is not… — modèle : *The biophysics is normal. The length is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Masse normal(e), mais Biophysique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The mass is normal. The biophysics is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Biophysique et Masse à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check biophysics.”
   - FR : « D'abord, nous vérifions Biophysique. »
   - AR : «أولًا، نتحقق من الفيزياء الحيوية.»
5. **EN** “Then, we check mass.”
   - FR : « Ensuite, nous vérifions Masse. »
   - AR : «ثم نتحقق من الكتلة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:biophysics:v1` — **biophysics** · FR : Biophysique · AR : الفيزياء الحيوية
- `gen:mass:v1` — **a mass** · FR : Masse · AR : الكتلة
- `gen:length:v1` — **a length** · FR : Longueur · AR : الطول

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : biophysics ; we check biophysics ; biophysics first ; the doctor checks biophysics first
  - modèle : First, the doctor checks biophysics.
- **q2.** What does the doctor check next?
  - accepté : mass ; then we check mass ; mass next ; the doctor checks mass next
  - modèle : Then, the doctor checks mass.
- **q3.** Does the doctor check mass before biophysics?
  - accepté : no ; no he does not ; no he doesn't ; he checks biophysics first
  - modèle : No, he checks biophysics first, then mass.
  - hint (si erreur) — FR : Attention : Biophysique vient D'ABORD, ensuite Masse.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : length ; length first ; he checks length first
  - modèle : First, the doctor checks length.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check biophysics. Then, we check mass.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Longueur, ensuite Biophysique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check length. Then, we check biophysics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Biophysique / Masse, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the biophysics.
   - FR : Le patient du lit 3 a Biophysique.
   - AR : مريض السرير ٣ عنده الفيزياء الحيوية.
4. **EN** The patient in bed 5 has a problem with the mass.
   - FR : Le patient du lit 5 a Masse.
   - AR : مريض السرير ٥ عنده الكتلة.
5. **EN** The patient in bed 5 does not have a problem with the biophysics.
   - FR : Le patient du lit 5 n'a pas de Biophysique.
   - AR : مريض السرير ٥ ليس عنده الفيزياء الحيوية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:biophysics:v1` — **biophysics** · FR : Biophysique · AR : الفيزياء الحيوية
- `gen:mass:v1` — **a mass** · FR : Masse · AR : الكتلة
- `gen:length:v1` — **a length** · FR : Longueur · AR : الطول

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : biophysics ; the patient with biophysics
  - modèle : The patient in bed 3 has biophysics.
- **q2.** What does the patient in bed 5 have?
  - accepté : mass ; a problem with the mass
  - modèle : He has a problem with the mass.
- **q3.** Does the patient in bed 5 have biophysics?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the biophysics ; he doesn't
  - modèle : No, he does not have a problem with the biophysics.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS biophysique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : length ; a problem with the length
  - modèle : He has a problem with the length.
- **Tt2.** Does the patient in bed 9 have mass?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the mass ; he doesn't
  - modèle : No, he does not have a problem with the mass.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS masse.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … biophysics and a mass. (have)` → have · modèle : The patients have biophysics and a mass.
- `The patient in bed 5 … not have biophysics. (do)` → does · modèle : The patient in bed 5 does not have biophysics.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the biophysics. The patient in bed 5 has a problem with the mass.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Longueur, le lit 9 a Biophysique et n'a pas Masse.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the length. The patient in bed 9 has a problem with the biophysics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
