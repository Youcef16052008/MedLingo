# Relecture — Organes sensoriels (anat)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anat-case-008` — Le cas de Bilal : Globe oculaire anormal(e) / _Bilal: the eyeball is not normal_

**Objectif :** Comprendre des résultats : Organe sensoriel normal(e), Globe oculaire anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The sensory organ is normal.”
   - FR : Le médecin dit : « Organe sensoriel est normal(e). »
   - AR : يقول الطبيب: «عضو الحس طبيعي/طبيعية.»
5. **EN** “But the eyeball is not normal.”
   - FR : « Mais Globe oculaire n'est pas normal(e). »
   - AR : «لكن كرة العين غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the eyeball normal?”
   - FR : Lina demande : « Globe oculaire est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل كرة العين طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:sensory-organ:v1` — **a sensory organ** · FR : Organe sensoriel · AR : عضو الحس
- `gen:eye:v1` — **an eye** · FR : Œil · AR : العين
- `gen:eyeball:v1` — **an eyeball** · FR : Globe oculaire · AR : كرة العين

### Questions (acceptées / modèle / hint)
- **q1.** Is the sensory organ normal?
  - accepté : yes ; yes it is ; the sensory organ is normal ; it is normal
  - modèle : Yes, the sensory organ is normal.
- **q2.** Is the eyeball normal?
  - accepté : no ; no it is not ; no it isn't ; the eyeball is not normal ; it is not normal ; it isn't normal
  - modèle : No, the eyeball is not normal.
  - hint (si erreur) — FR : Attention : Globe oculaire n'est PAS normal(e).
- **q3.** Which one is not normal: the sensory organ or the eyeball?
  - accepté : eyeball ; the eyeball
  - modèle : The eyeball is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the eye normal?
  - accepté : yes ; yes it is ; the eye is normal
  - modèle : Yes, the eye is normal.
- **Tt2.** Is the sensory organ normal?
  - accepté : no ; no it is not ; no it isn't ; the sensory organ is not normal ; it is not normal
  - modèle : No, the sensory organ is not normal.
  - hint (si erreur) — FR : Attention : Organe sensoriel n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The sensory organ … normal. (be)` → is · modèle : The sensory organ is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The sensory organ is… / The eyeball is not… — modèle : *The sensory organ is normal. The eyeball is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Œil normal(e), mais Organe sensoriel anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The eye is normal. The sensory organ is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anat-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Organe sensoriel et Œil à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient.
   - FR : Omar est un·e patient·e.
   - AR : Omar مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check sensory organ.”
   - FR : « D'abord, nous vérifions Organe sensoriel. »
   - AR : «أولًا، نتحقق من عضو الحس.»
5. **EN** “Then, we check eye.”
   - FR : « Ensuite, nous vérifions Œil. »
   - AR : «ثم نتحقق من العين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:sensory-organ:v1` — **a sensory organ** · FR : Organe sensoriel · AR : عضو الحس
- `gen:eye:v1` — **an eye** · FR : Œil · AR : العين
- `gen:eyeball:v1` — **an eyeball** · FR : Globe oculaire · AR : كرة العين

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : sensory organ ; we check sensory organ ; sensory organ first ; the doctor checks sensory organ first
  - modèle : First, the doctor checks sensory organ.
- **q2.** What does the doctor check next?
  - accepté : eye ; then we check eye ; eye next ; the doctor checks eye next
  - modèle : Then, the doctor checks eye.
- **q3.** Does the doctor check eye before sensory organ?
  - accepté : no ; no he does not ; no he doesn't ; he checks sensory organ first
  - modèle : No, he checks sensory organ first, then eye.
  - hint (si erreur) — FR : Attention : Organe sensoriel vient D'ABORD, ensuite Œil.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : eyeball ; eyeball first ; he checks eyeball first
  - modèle : First, the doctor checks eyeball.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check sensory organ. Then, we check eye.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Globe oculaire, ensuite Organe sensoriel, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check eyeball. Then, we check sensory organ.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anat-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Organe sensoriel / Œil, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the sensory organ.
   - FR : Le patient du lit 3 a Organe sensoriel.
   - AR : مريض السرير ٣ عنده عضو الحس.
4. **EN** The patient in bed 5 has a problem with the eye.
   - FR : Le patient du lit 5 a Œil.
   - AR : مريض السرير ٥ عنده العين.
5. **EN** The patient in bed 5 does not have a problem with the sensory organ.
   - FR : Le patient du lit 5 n'a pas de Organe sensoriel.
   - AR : مريض السرير ٥ ليس عنده عضو الحس.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:sensory-organ:v1` — **a sensory organ** · FR : Organe sensoriel · AR : عضو الحس
- `gen:eye:v1` — **an eye** · FR : Œil · AR : العين
- `gen:eyeball:v1` — **an eyeball** · FR : Globe oculaire · AR : كرة العين

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : sensory organ ; a sensory organ ; the patient with sensory organ
  - modèle : The patient in bed 3 has a sensory organ.
- **q2.** What does the patient in bed 5 have?
  - accepté : eye ; a problem with the eye
  - modèle : He has a problem with the eye.
- **q3.** Does the patient in bed 5 have sensory organ?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the sensory organ ; he doesn't
  - modèle : No, he does not have a problem with the sensory organ.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS organe sensoriel — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : eyeball ; a problem with the eyeball
  - modèle : He has a problem with the eyeball.
- **Tt2.** Does the patient in bed 9 have eye?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the eye ; he doesn't
  - modèle : No, he does not have a problem with the eye.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS œil.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a sensory organ and an eye. (have)` → have · modèle : The patients have a sensory organ and an eye.
- `The patient in bed 5 … not have a sensory organ. (do)` → does · modèle : The patient in bed 5 does not have a sensory organ.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the sensory organ. The patient in bed 5 has a problem with the eye.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Globe oculaire, le lit 9 a Organe sensoriel et n'a pas Œil.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the eyeball. The patient in bed 9 has a problem with the sensory organ.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
