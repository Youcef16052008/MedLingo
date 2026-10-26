# Relecture — Physiologie Digestive (physio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `physio-case-003` — Le cas de Amel : Péristaltisme anormal(e) / _Amel: the peristalsis is not normal_

**Objectif :** Comprendre des résultats : Digestion normal(e), Péristaltisme anormal(e).
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
4. **EN** The doctor says, “The digestion is normal.”
   - FR : Le médecin dit : « Digestion est normal(e). »
   - AR : يقول الطبيب: «الهضم طبيعي/طبيعية.»
5. **EN** “But the peristalsis is not normal.”
   - FR : « Mais Péristaltisme n'est pas normal(e). »
   - AR : «لكن الحركة الدودية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the peristalsis normal?”
   - FR : Amine demande : « Péristaltisme est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الحركة الدودية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:digestion:v1` — **a digestion** · FR : Digestion · AR : الهضم
- `gen:absorption:v1` — **an absorption** · FR : Absorption · AR : الامتصاص
- `gen:peristalsis:v1` — **peristalsis** · FR : Péristaltisme · AR : الحركة الدودية

### Questions (acceptées / modèle / hint)
- **q1.** Is the digestion normal?
  - accepté : yes ; yes it is ; the digestion is normal ; it is normal
  - modèle : Yes, the digestion is normal.
- **q2.** Is the peristalsis normal?
  - accepté : no ; no it is not ; no it isn't ; the peristalsis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the peristalsis is not normal.
  - hint (si erreur) — FR : Attention : Péristaltisme n'est PAS normal(e).
- **q3.** Which one is not normal: the digestion or the peristalsis?
  - accepté : peristalsis ; the peristalsis
  - modèle : The peristalsis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the absorption normal?
  - accepté : yes ; yes it is ; the absorption is normal
  - modèle : Yes, the absorption is normal.
- **Tt2.** Is the digestion normal?
  - accepté : no ; no it is not ; no it isn't ; the digestion is not normal ; it is not normal
  - modèle : No, the digestion is not normal.
  - hint (si erreur) — FR : Attention : Digestion n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The digestion … normal. (be)` → is · modèle : The digestion is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The digestion is… / The peristalsis is not… — modèle : *The digestion is normal. The peristalsis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Absorption normal(e), mais Digestion anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The absorption is normal. The digestion is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `physio-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Digestion et Absorption à un patient.
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
4. **EN** “First, we check digestion.”
   - FR : « D'abord, nous vérifions Digestion. »
   - AR : «أولًا، نتحقق من الهضم.»
5. **EN** “Then, we check absorption.”
   - FR : « Ensuite, nous vérifions Absorption. »
   - AR : «ثم نتحقق من الامتصاص.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:digestion:v1` — **a digestion** · FR : Digestion · AR : الهضم
- `gen:absorption:v1` — **an absorption** · FR : Absorption · AR : الامتصاص
- `gen:peristalsis:v1` — **peristalsis** · FR : Péristaltisme · AR : الحركة الدودية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : digestion ; we check digestion ; digestion first ; the doctor checks digestion first
  - modèle : First, the doctor checks digestion.
- **q2.** What does the doctor check next?
  - accepté : absorption ; then we check absorption ; absorption next ; the doctor checks absorption next
  - modèle : Then, the doctor checks absorption.
- **q3.** Does the doctor check absorption before digestion?
  - accepté : no ; no he does not ; no he doesn't ; he checks digestion first
  - modèle : No, he checks digestion first, then absorption.
  - hint (si erreur) — FR : Attention : Digestion vient D'ABORD, ensuite Absorption.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : peristalsis ; peristalsis first ; he checks peristalsis first
  - modèle : First, the doctor checks peristalsis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check digestion. Then, we check absorption.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Péristaltisme, ensuite Digestion, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check peristalsis. Then, we check digestion.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `physio-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Digestion / Absorption, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the digestion.
   - FR : Le patient du lit 3 a Digestion.
   - AR : مريض السرير ٣ عنده الهضم.
4. **EN** The patient in bed 5 has a problem with the absorption.
   - FR : Le patient du lit 5 a Absorption.
   - AR : مريض السرير ٥ عنده الامتصاص.
5. **EN** The patient in bed 5 does not have a problem with the digestion.
   - FR : Le patient du lit 5 n'a pas de Digestion.
   - AR : مريض السرير ٥ ليس عنده الهضم.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:digestion:v1` — **a digestion** · FR : Digestion · AR : الهضم
- `gen:absorption:v1` — **an absorption** · FR : Absorption · AR : الامتصاص
- `gen:peristalsis:v1` — **peristalsis** · FR : Péristaltisme · AR : الحركة الدودية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : digestion ; a digestion ; the patient with digestion
  - modèle : The patient in bed 3 has a digestion.
- **q2.** What does the patient in bed 5 have?
  - accepté : absorption ; a problem with the absorption
  - modèle : He has a problem with the absorption.
- **q3.** Does the patient in bed 5 have digestion?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the digestion ; he doesn't
  - modèle : No, he does not have a problem with the digestion.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS digestion — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : peristalsis ; a problem with the peristalsis
  - modèle : He has a problem with the peristalsis.
- **Tt2.** Does the patient in bed 9 have absorption?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the absorption ; he doesn't
  - modèle : No, he does not have a problem with the absorption.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS absorption.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a digestion and an absorption. (have)` → have · modèle : The patients have a digestion and an absorption.
- `The patient in bed 5 … not have a digestion. (do)` → does · modèle : The patient in bed 5 does not have a digestion.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the digestion. The patient in bed 5 has a problem with the absorption.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Péristaltisme, le lit 9 a Digestion et n'a pas Absorption.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the peristalsis. The patient in bed 9 has a problem with the digestion.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
