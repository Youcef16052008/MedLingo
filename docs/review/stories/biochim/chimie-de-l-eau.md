# Relecture — Chimie de l'Eau (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-006` — Le cas de Sofiane : Atome anormal(e) / _Sofiane: the atom is not normal_

**Objectif :** Comprendre des résultats : Biochimie normal(e), Atome anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient at the clinic.
   - FR : Sofiane est un·e patient·e à la clinique.
   - AR : Sofiane مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The biochemistry is normal.”
   - FR : Le médecin dit : « Biochimie est normal(e). »
   - AR : يقول الطبيب: «الكيمياء الحيوية طبيعي/طبيعية.»
5. **EN** “But the atom is not normal.”
   - FR : « Mais Atome n'est pas normal(e). »
   - AR : «لكن الذرة غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the atom normal?”
   - FR : Lina demande : « Atome est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الذرة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:biochemistry:v1` — **a biochemistry** · FR : Biochimie · AR : الكيمياء الحيوية
- `gen:molecule:v1` — **a molecule** · FR : Molécule · AR : الجزيء
- `gen:atom:v1` — **an atom** · FR : Atome · AR : الذرة

### Questions (acceptées / modèle / hint)
- **q1.** Is the biochemistry normal?
  - accepté : yes ; yes it is ; the biochemistry is normal ; it is normal
  - modèle : Yes, the biochemistry is normal.
- **q2.** Is the atom normal?
  - accepté : no ; no it is not ; no it isn't ; the atom is not normal ; it is not normal ; it isn't normal
  - modèle : No, the atom is not normal.
  - hint (si erreur) — FR : Attention : Atome n'est PAS normal(e).
- **q3.** Which one is not normal: the biochemistry or the atom?
  - accepté : atom ; the atom
  - modèle : The atom is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the molecule normal?
  - accepté : yes ; yes it is ; the molecule is normal
  - modèle : Yes, the molecule is normal.
- **Tt2.** Is the biochemistry normal?
  - accepté : no ; no it is not ; no it isn't ; the biochemistry is not normal ; it is not normal
  - modèle : No, the biochemistry is not normal.
  - hint (si erreur) — FR : Attention : Biochimie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The biochemistry … normal. (be)` → is · modèle : The biochemistry is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sofiane en deux phrases. Garde la négation. — amorces : The biochemistry is… / The atom is not… — modèle : *The biochemistry is normal. The atom is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Molécule normal(e), mais Biochimie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The molecule is normal. The biochemistry is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Biochimie et Molécule à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient.
   - FR : Bilal est un·e patient·e.
   - AR : Bilal مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check biochemistry.”
   - FR : « D'abord, nous vérifions Biochimie. »
   - AR : «أولًا، نتحقق من الكيمياء الحيوية.»
5. **EN** “Then, we check molecule.”
   - FR : « Ensuite, nous vérifions Molécule. »
   - AR : «ثم نتحقق من الجزيء.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:biochemistry:v1` — **a biochemistry** · FR : Biochimie · AR : الكيمياء الحيوية
- `gen:molecule:v1` — **a molecule** · FR : Molécule · AR : الجزيء
- `gen:atom:v1` — **an atom** · FR : Atome · AR : الذرة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : biochemistry ; we check biochemistry ; biochemistry first ; the doctor checks biochemistry first
  - modèle : First, the doctor checks biochemistry.
- **q2.** What does the doctor check next?
  - accepté : molecule ; then we check molecule ; molecule next ; the doctor checks molecule next
  - modèle : Then, the doctor checks molecule.
- **q3.** Does the doctor check molecule before biochemistry?
  - accepté : no ; no he does not ; no he doesn't ; he checks biochemistry first
  - modèle : No, he checks biochemistry first, then molecule.
  - hint (si erreur) — FR : Attention : Biochimie vient D'ABORD, ensuite Molécule.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : atom ; atom first ; he checks atom first
  - modèle : First, the doctor checks atom.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check biochemistry. Then, we check molecule.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Atome, ensuite Biochimie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check atom. Then, we check biochemistry.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Biochimie / Molécule, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the biochemistry.
   - FR : Le patient du lit 3 a Biochimie.
   - AR : مريض السرير ٣ عنده الكيمياء الحيوية.
4. **EN** The patient in bed 5 has a problem with the molecule.
   - FR : Le patient du lit 5 a Molécule.
   - AR : مريض السرير ٥ عنده الجزيء.
5. **EN** The patient in bed 5 does not have a problem with the biochemistry.
   - FR : Le patient du lit 5 n'a pas de Biochimie.
   - AR : مريض السرير ٥ ليس عنده الكيمياء الحيوية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:biochemistry:v1` — **a biochemistry** · FR : Biochimie · AR : الكيمياء الحيوية
- `gen:molecule:v1` — **a molecule** · FR : Molécule · AR : الجزيء
- `gen:atom:v1` — **an atom** · FR : Atome · AR : الذرة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : biochemistry ; a biochemistry ; the patient with biochemistry
  - modèle : The patient in bed 3 has a biochemistry.
- **q2.** What does the patient in bed 5 have?
  - accepté : molecule ; a problem with the molecule
  - modèle : He has a problem with the molecule.
- **q3.** Does the patient in bed 5 have biochemistry?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the biochemistry ; he doesn't
  - modèle : No, he does not have a problem with the biochemistry.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS biochimie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : atom ; a problem with the atom
  - modèle : He has a problem with the atom.
- **Tt2.** Does the patient in bed 9 have molecule?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the molecule ; he doesn't
  - modèle : No, he does not have a problem with the molecule.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS molécule.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a biochemistry and a molecule. (have)` → have · modèle : The patients have a biochemistry and a molecule.
- `The patient in bed 5 … not have a biochemistry. (do)` → does · modèle : The patient in bed 5 does not have a biochemistry.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the biochemistry. The patient in bed 5 has a problem with the molecule.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Atome, le lit 9 a Biochimie et n'a pas Molécule.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the atom. The patient in bed 9 has a problem with the biochemistry.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
