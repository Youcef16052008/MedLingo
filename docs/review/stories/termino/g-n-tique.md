# Relecture — Génétique (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-014` — Le cas de Youssef : Gène homéotique (gène Hox) anormal(e) / _Youssef: the homeotic gene is not normal_

**Objectif :** Comprendre des résultats : Induction (embryonnaire) normal(e), Gène homéotique (gène Hox) anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The induction is normal.”
   - FR : Le médecin dit : « Induction (embryonnaire) est normal(e). »
   - AR : يقول الطبيب: «التحريض الجنيني طبيعي/طبيعية.»
5. **EN** “But the homeotic gene is not normal.”
   - FR : « Mais Gène homéotique (gène Hox) n'est pas normal(e). »
   - AR : «لكن الجين الهيوكسي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the homeotic gene normal?”
   - FR : Lina demande : « Gène homéotique (gène Hox) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الجين الهيوكسي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:induction:v1` — **an induction** · FR : Induction (embryonnaire) · AR : التحريض الجنيني
- `gen:morphogen:v1` — **a morphogen** · FR : Morphogène · AR : المورفوجين
- `gen:homeotic-gene:v1` — **a homeotic gene** · FR : Gène homéotique (gène Hox) · AR : الجين الهيوكسي

### Questions (acceptées / modèle / hint)
- **q1.** Is the induction normal?
  - accepté : yes ; yes it is ; the induction is normal ; it is normal
  - modèle : Yes, the induction is normal.
- **q2.** Is the homeotic gene normal?
  - accepté : no ; no it is not ; no it isn't ; the homeotic gene is not normal ; it is not normal ; it isn't normal
  - modèle : No, the homeotic gene is not normal.
  - hint (si erreur) — FR : Attention : Gène homéotique (gène Hox) n'est PAS normal(e).
- **q3.** Which one is not normal: the induction or the homeotic gene?
  - accepté : homeotic gene ; the homeotic gene
  - modèle : The homeotic gene is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the morphogen normal?
  - accepté : yes ; yes it is ; the morphogen is normal
  - modèle : Yes, the morphogen is normal.
- **Tt2.** Is the induction normal?
  - accepté : no ; no it is not ; no it isn't ; the induction is not normal ; it is not normal
  - modèle : No, the induction is not normal.
  - hint (si erreur) — FR : Attention : Induction (embryonnaire) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The induction … normal. (be)` → is · modèle : The induction is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The induction is… / The homeotic gene is not… — modèle : *The induction is normal. The homeotic gene is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Morphogène normal(e), mais Induction (embryonnaire) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The morphogen is normal. The induction is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-014` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Induction (embryonnaire) et Morphogène à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient.
   - FR : Farid est un·e patient·e.
   - AR : Farid مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check induction.”
   - FR : « D'abord, nous vérifions Induction (embryonnaire). »
   - AR : «أولًا، نتحقق من التحريض الجنيني.»
5. **EN** “Then, we check morphogen.”
   - FR : « Ensuite, nous vérifions Morphogène. »
   - AR : «ثم نتحقق من المورفوجين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:induction:v1` — **an induction** · FR : Induction (embryonnaire) · AR : التحريض الجنيني
- `gen:morphogen:v1` — **a morphogen** · FR : Morphogène · AR : المورفوجين
- `gen:homeotic-gene:v1` — **a homeotic gene** · FR : Gène homéotique (gène Hox) · AR : الجين الهيوكسي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : induction ; we check induction ; induction first ; the doctor checks induction first
  - modèle : First, the doctor checks induction.
- **q2.** What does the doctor check next?
  - accepté : morphogen ; then we check morphogen ; morphogen next ; the doctor checks morphogen next
  - modèle : Then, the doctor checks morphogen.
- **q3.** Does the doctor check morphogen before induction?
  - accepté : no ; no he does not ; no he doesn't ; he checks induction first
  - modèle : No, he checks induction first, then morphogen.
  - hint (si erreur) — FR : Attention : Induction (embryonnaire) vient D'ABORD, ensuite Morphogène.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : homeotic gene ; homeotic gene first ; he checks homeotic gene first
  - modèle : First, the doctor checks homeotic gene.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check induction. Then, we check morphogen.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Gène homéotique (gène Hox), ensuite Induction (embryonnaire), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check homeotic gene. Then, we check induction.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-014` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Induction (embryonnaire) / Morphogène, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the induction.
   - FR : Le patient du lit 3 a Induction (embryonnaire).
   - AR : مريض السرير ٣ عنده التحريض الجنيني.
4. **EN** The patient in bed 5 has a problem with the morphogen.
   - FR : Le patient du lit 5 a Morphogène.
   - AR : مريض السرير ٥ عنده المورفوجين.
5. **EN** The patient in bed 5 does not have a problem with the induction.
   - FR : Le patient du lit 5 n'a pas de Induction (embryonnaire).
   - AR : مريض السرير ٥ ليس عنده التحريض الجنيني.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:induction:v1` — **an induction** · FR : Induction (embryonnaire) · AR : التحريض الجنيني
- `gen:morphogen:v1` — **a morphogen** · FR : Morphogène · AR : المورفوجين
- `gen:homeotic-gene:v1` — **a homeotic gene** · FR : Gène homéotique (gène Hox) · AR : الجين الهيوكسي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : induction ; an induction ; the patient with induction
  - modèle : The patient in bed 3 has an induction.
- **q2.** What does the patient in bed 5 have?
  - accepté : morphogen ; a problem with the morphogen
  - modèle : He has a problem with the morphogen.
- **q3.** Does the patient in bed 5 have induction?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the induction ; he doesn't
  - modèle : No, he does not have a problem with the induction.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS induction (embryonnaire) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : homeotic gene ; a problem with the homeotic gene
  - modèle : He has a problem with the homeotic gene.
- **Tt2.** Does the patient in bed 9 have morphogen?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the morphogen ; he doesn't
  - modèle : No, he does not have a problem with the morphogen.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS morphogène.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an induction and a morphogen. (have)` → have · modèle : The patients have an induction and a morphogen.
- `The patient in bed 5 … not have an induction. (do)` → does · modèle : The patient in bed 5 does not have an induction.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the induction. The patient in bed 5 has a problem with the morphogen.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Gène homéotique (gène Hox), le lit 9 a Induction (embryonnaire) et n'a pas Morphogène.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the homeotic gene. The patient in bed 9 has a problem with the induction.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
