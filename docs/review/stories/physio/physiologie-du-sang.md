# Relecture — Physiologie du Sang (physio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `physio-case-008` — Le cas de Bilal : Globule rouge (Érythrocyte) anormal(e) / _Bilal: the red blood cell is not normal_

**Objectif :** Comprendre des résultats : Sang normal(e), Globule rouge (Érythrocyte) anormal(e).
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
4. **EN** The doctor says, “The blood is normal.”
   - FR : Le médecin dit : « Sang est normal(e). »
   - AR : يقول الطبيب: «الدم طبيعي/طبيعية.»
5. **EN** “But the red blood cell is not normal.”
   - FR : « Mais Globule rouge (Érythrocyte) n'est pas normal(e). »
   - AR : «لكن كرية الدم الحمراء غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the red blood cell normal?”
   - FR : Lina demande : « Globule rouge (Érythrocyte) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل كرية الدم الحمراء طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:blood:v1` — **a blood** · FR : Sang · AR : الدم
- `gen:plasma:v1` — **a plasma** · FR : Plasma · AR : البلازما
- `gen:red-blood-cell:v1` — **a red blood cell** · FR : Globule rouge (Érythrocyte) · AR : كرية الدم الحمراء

### Questions (acceptées / modèle / hint)
- **q1.** Is the blood normal?
  - accepté : yes ; yes it is ; the blood is normal ; it is normal
  - modèle : Yes, the blood is normal.
- **q2.** Is the red blood cell normal?
  - accepté : no ; no it is not ; no it isn't ; the red blood cell is not normal ; it is not normal ; it isn't normal
  - modèle : No, the red blood cell is not normal.
  - hint (si erreur) — FR : Attention : Globule rouge (Érythrocyte) n'est PAS normal(e).
- **q3.** Which one is not normal: the blood or the red blood cell?
  - accepté : red blood cell ; the red blood cell
  - modèle : The red blood cell is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the plasma normal?
  - accepté : yes ; yes it is ; the plasma is normal
  - modèle : Yes, the plasma is normal.
- **Tt2.** Is the blood normal?
  - accepté : no ; no it is not ; no it isn't ; the blood is not normal ; it is not normal
  - modèle : No, the blood is not normal.
  - hint (si erreur) — FR : Attention : Sang n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The blood … normal. (be)` → is · modèle : The blood is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The blood is… / The red blood cell is not… — modèle : *The blood is normal. The red blood cell is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Plasma normal(e), mais Sang anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The plasma is normal. The blood is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `physio-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Sang et Plasma à un patient.
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
4. **EN** “First, we check blood.”
   - FR : « D'abord, nous vérifions Sang. »
   - AR : «أولًا، نتحقق من الدم.»
5. **EN** “Then, we check plasma.”
   - FR : « Ensuite, nous vérifions Plasma. »
   - AR : «ثم نتحقق من البلازما.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:blood:v1` — **a blood** · FR : Sang · AR : الدم
- `gen:plasma:v1` — **a plasma** · FR : Plasma · AR : البلازما
- `gen:red-blood-cell:v1` — **a red blood cell** · FR : Globule rouge (Érythrocyte) · AR : كرية الدم الحمراء

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : blood ; we check blood ; blood first ; the doctor checks blood first
  - modèle : First, the doctor checks blood.
- **q2.** What does the doctor check next?
  - accepté : plasma ; then we check plasma ; plasma next ; the doctor checks plasma next
  - modèle : Then, the doctor checks plasma.
- **q3.** Does the doctor check plasma before blood?
  - accepté : no ; no he does not ; no he doesn't ; he checks blood first
  - modèle : No, he checks blood first, then plasma.
  - hint (si erreur) — FR : Attention : Sang vient D'ABORD, ensuite Plasma.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : red blood cell ; red blood cell first ; he checks red blood cell first
  - modèle : First, the doctor checks red blood cell.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check blood. Then, we check plasma.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Globule rouge (Érythrocyte), ensuite Sang, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check red blood cell. Then, we check blood.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `physio-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Sang / Plasma, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the blood.
   - FR : Le patient du lit 3 a Sang.
   - AR : مريض السرير ٣ عنده الدم.
4. **EN** The patient in bed 5 has a problem with the plasma.
   - FR : Le patient du lit 5 a Plasma.
   - AR : مريض السرير ٥ عنده البلازما.
5. **EN** The patient in bed 5 does not have a problem with the blood.
   - FR : Le patient du lit 5 n'a pas de Sang.
   - AR : مريض السرير ٥ ليس عنده الدم.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:blood:v1` — **a blood** · FR : Sang · AR : الدم
- `gen:plasma:v1` — **a plasma** · FR : Plasma · AR : البلازما
- `gen:red-blood-cell:v1` — **a red blood cell** · FR : Globule rouge (Érythrocyte) · AR : كرية الدم الحمراء

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : blood ; a blood ; the patient with blood
  - modèle : The patient in bed 3 has a blood.
- **q2.** What does the patient in bed 5 have?
  - accepté : plasma ; a problem with the plasma
  - modèle : He has a problem with the plasma.
- **q3.** Does the patient in bed 5 have blood?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the blood ; he doesn't
  - modèle : No, he does not have a problem with the blood.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS sang — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : red blood cell ; a problem with the red blood cell
  - modèle : He has a problem with the red blood cell.
- **Tt2.** Does the patient in bed 9 have plasma?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the plasma ; he doesn't
  - modèle : No, he does not have a problem with the plasma.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS plasma.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a blood and a plasma. (have)` → have · modèle : The patients have a blood and a plasma.
- `The patient in bed 5 … not have a blood. (do)` → does · modèle : The patient in bed 5 does not have a blood.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the blood. The patient in bed 5 has a problem with the plasma.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Globule rouge (Érythrocyte), le lit 9 a Sang et n'a pas Plasma.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the red blood cell. The patient in bed 9 has a problem with the blood.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
