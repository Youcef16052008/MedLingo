# Relecture — Physique Atomique (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-008` — Le cas de Bilal : Neutron anormal(e) / _Bilal: the neutron is not normal_

**Objectif :** Comprendre des résultats : Atome normal(e), Neutron anormal(e).
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
4. **EN** The doctor says, “The atom is normal.”
   - FR : Le médecin dit : « Atome est normal(e). »
   - AR : يقول الطبيب: «الذرة طبيعي/طبيعية.»
5. **EN** “But the neutron is not normal.”
   - FR : « Mais Neutron n'est pas normal(e). »
   - AR : «لكن النيوترون غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the neutron normal?”
   - FR : Lina demande : « Neutron est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل النيوترون طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:atom:v1` — **an atom** · FR : Atome · AR : الذرة
- `gen:proton:v1` — **a proton** · FR : Proton · AR : البروتون
- `gen:neutron:v1` — **a neutron** · FR : Neutron · AR : النيوترون

### Questions (acceptées / modèle / hint)
- **q1.** Is the atom normal?
  - accepté : yes ; yes it is ; the atom is normal ; it is normal
  - modèle : Yes, the atom is normal.
- **q2.** Is the neutron normal?
  - accepté : no ; no it is not ; no it isn't ; the neutron is not normal ; it is not normal ; it isn't normal
  - modèle : No, the neutron is not normal.
  - hint (si erreur) — FR : Attention : Neutron n'est PAS normal(e).
- **q3.** Which one is not normal: the atom or the neutron?
  - accepté : neutron ; the neutron
  - modèle : The neutron is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the proton normal?
  - accepté : yes ; yes it is ; the proton is normal
  - modèle : Yes, the proton is normal.
- **Tt2.** Is the atom normal?
  - accepté : no ; no it is not ; no it isn't ; the atom is not normal ; it is not normal
  - modèle : No, the atom is not normal.
  - hint (si erreur) — FR : Attention : Atome n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The atom … normal. (be)` → is · modèle : The atom is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The atom is… / The neutron is not… — modèle : *The atom is normal. The neutron is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Proton normal(e), mais Atome anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The proton is normal. The atom is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Atome et Proton à un patient.
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
4. **EN** “First, we check atom.”
   - FR : « D'abord, nous vérifions Atome. »
   - AR : «أولًا، نتحقق من الذرة.»
5. **EN** “Then, we check proton.”
   - FR : « Ensuite, nous vérifions Proton. »
   - AR : «ثم نتحقق من البروتون.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:atom:v1` — **an atom** · FR : Atome · AR : الذرة
- `gen:proton:v1` — **a proton** · FR : Proton · AR : البروتون
- `gen:neutron:v1` — **a neutron** · FR : Neutron · AR : النيوترون

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : atom ; we check atom ; atom first ; the doctor checks atom first
  - modèle : First, the doctor checks atom.
- **q2.** What does the doctor check next?
  - accepté : proton ; then we check proton ; proton next ; the doctor checks proton next
  - modèle : Then, the doctor checks proton.
- **q3.** Does the doctor check proton before atom?
  - accepté : no ; no he does not ; no he doesn't ; he checks atom first
  - modèle : No, he checks atom first, then proton.
  - hint (si erreur) — FR : Attention : Atome vient D'ABORD, ensuite Proton.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : neutron ; neutron first ; he checks neutron first
  - modèle : First, the doctor checks neutron.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check atom. Then, we check proton.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Neutron, ensuite Atome, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check neutron. Then, we check atom.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Atome / Proton, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the atom.
   - FR : Le patient du lit 3 a Atome.
   - AR : مريض السرير ٣ عنده الذرة.
4. **EN** The patient in bed 5 has a problem with the proton.
   - FR : Le patient du lit 5 a Proton.
   - AR : مريض السرير ٥ عنده البروتون.
5. **EN** The patient in bed 5 does not have a problem with the atom.
   - FR : Le patient du lit 5 n'a pas de Atome.
   - AR : مريض السرير ٥ ليس عنده الذرة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:atom:v1` — **an atom** · FR : Atome · AR : الذرة
- `gen:proton:v1` — **a proton** · FR : Proton · AR : البروتون
- `gen:neutron:v1` — **a neutron** · FR : Neutron · AR : النيوترون

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : atom ; an atom ; the patient with atom
  - modèle : The patient in bed 3 has an atom.
- **q2.** What does the patient in bed 5 have?
  - accepté : proton ; a problem with the proton
  - modèle : He has a problem with the proton.
- **q3.** Does the patient in bed 5 have atom?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the atom ; he doesn't
  - modèle : No, he does not have a problem with the atom.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS atome — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : neutron ; a problem with the neutron
  - modèle : He has a problem with the neutron.
- **Tt2.** Does the patient in bed 9 have proton?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the proton ; he doesn't
  - modèle : No, he does not have a problem with the proton.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS proton.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an atom and a proton. (have)` → have · modèle : The patients have an atom and a proton.
- `The patient in bed 5 … not have an atom. (do)` → does · modèle : The patient in bed 5 does not have an atom.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the atom. The patient in bed 5 has a problem with the proton.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Neutron, le lit 9 a Atome et n'a pas Proton.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the neutron. The patient in bed 9 has a problem with the atom.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
