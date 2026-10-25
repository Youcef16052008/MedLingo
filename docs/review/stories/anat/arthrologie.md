# Relecture — Arthrologie (anat)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anat-case-004` — Le cas de Farid : Articulation cartilagineuse anormal(e) / _Farid: the cartilaginous joint is not normal_

**Objectif :** Comprendre des résultats : Articulation normal(e), Articulation cartilagineuse anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The joint is normal.”
   - FR : Le médecin dit : « Articulation est normal(e). »
   - AR : يقول الطبيب: «المفصل طبيعي/طبيعية.»
5. **EN** “But the cartilaginous joint is not normal.”
   - FR : « Mais Articulation cartilagineuse n'est pas normal(e). »
   - AR : «لكن المفصل الغضروفي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the cartilaginous joint normal?”
   - FR : Lina demande : « Articulation cartilagineuse est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل المفصل الغضروفي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:joint:v1` — **a joint** · FR : Articulation · AR : المفصل
- `gen:synovial-joint:v1` — **a synovial joint** · FR : Articulation synoviale · AR : المفصل الزليلي
- `gen:cartilaginous-joint:v1` — **a cartilaginous joint** · FR : Articulation cartilagineuse · AR : المفصل الغضروفي

### Questions (acceptées / modèle / hint)
- **q1.** Is the joint normal?
  - accepté : yes ; yes it is ; the joint is normal ; it is normal
  - modèle : Yes, the joint is normal.
- **q2.** Is the cartilaginous joint normal?
  - accepté : no ; no it is not ; no it isn't ; the cartilaginous joint is not normal ; it is not normal ; it isn't normal
  - modèle : No, the cartilaginous joint is not normal.
  - hint (si erreur) — FR : Attention : Articulation cartilagineuse n'est PAS normal(e).
- **q3.** Which one is not normal: the joint or the cartilaginous joint?
  - accepté : cartilaginous joint ; the cartilaginous joint
  - modèle : The cartilaginous joint is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the synovial joint normal?
  - accepté : yes ; yes it is ; the synovial joint is normal
  - modèle : Yes, the synovial joint is normal.
- **Tt2.** Is the joint normal?
  - accepté : no ; no it is not ; no it isn't ; the joint is not normal ; it is not normal
  - modèle : No, the joint is not normal.
  - hint (si erreur) — FR : Attention : Articulation n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The joint … normal. (be)` → is · modèle : The joint is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The joint is… / The cartilaginous joint is not… — modèle : *The joint is normal. The cartilaginous joint is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Articulation synoviale normal(e), mais Articulation anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The synovial joint is normal. The joint is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anat-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Articulation et Articulation synoviale à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient.
   - FR : Sofiane est un·e patient·e.
   - AR : Sofiane مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check joint.”
   - FR : « D'abord, nous vérifions Articulation. »
   - AR : «أولًا، نتحقق من المفصل.»
5. **EN** “Then, we check synovial joint.”
   - FR : « Ensuite, nous vérifions Articulation synoviale. »
   - AR : «ثم نتحقق من المفصل الزليلي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:joint:v1` — **a joint** · FR : Articulation · AR : المفصل
- `gen:synovial-joint:v1` — **a synovial joint** · FR : Articulation synoviale · AR : المفصل الزليلي
- `gen:cartilaginous-joint:v1` — **a cartilaginous joint** · FR : Articulation cartilagineuse · AR : المفصل الغضروفي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : joint ; we check joint ; joint first ; the doctor checks joint first
  - modèle : First, the doctor checks joint.
- **q2.** What does the doctor check next?
  - accepté : synovial joint ; then we check synovial joint ; synovial joint next ; the doctor checks synovial joint next
  - modèle : Then, the doctor checks synovial joint.
- **q3.** Does the doctor check synovial joint before joint?
  - accepté : no ; no he does not ; no he doesn't ; he checks joint first
  - modèle : No, he checks joint first, then synovial joint.
  - hint (si erreur) — FR : Attention : Articulation vient D'ABORD, ensuite Articulation synoviale.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : cartilaginous joint ; cartilaginous joint first ; he checks cartilaginous joint first
  - modèle : First, the doctor checks cartilaginous joint.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check joint. Then, we check synovial joint.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Articulation cartilagineuse, ensuite Articulation, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check cartilaginous joint. Then, we check joint.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anat-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Articulation / Articulation synoviale, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the joint.
   - FR : Le patient du lit 3 a Articulation.
   - AR : مريض السرير ٣ عنده المفصل.
4. **EN** The patient in bed 5 has a problem with the synovial joint.
   - FR : Le patient du lit 5 a Articulation synoviale.
   - AR : مريض السرير ٥ عنده المفصل الزليلي.
5. **EN** The patient in bed 5 does not have a problem with the joint.
   - FR : Le patient du lit 5 n'a pas de Articulation.
   - AR : مريض السرير ٥ ليس عنده المفصل.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:joint:v1` — **a joint** · FR : Articulation · AR : المفصل
- `gen:synovial-joint:v1` — **a synovial joint** · FR : Articulation synoviale · AR : المفصل الزليلي
- `gen:cartilaginous-joint:v1` — **a cartilaginous joint** · FR : Articulation cartilagineuse · AR : المفصل الغضروفي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : joint ; a joint ; the patient with joint
  - modèle : The patient in bed 3 has a joint.
- **q2.** What does the patient in bed 5 have?
  - accepté : synovial joint ; a problem with the synovial joint
  - modèle : He has a problem with the synovial joint.
- **q3.** Does the patient in bed 5 have joint?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the joint ; he doesn't
  - modèle : No, he does not have a problem with the joint.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS articulation — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : cartilaginous joint ; a problem with the cartilaginous joint
  - modèle : He has a problem with the cartilaginous joint.
- **Tt2.** Does the patient in bed 9 have synovial joint?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the synovial joint ; he doesn't
  - modèle : No, he does not have a problem with the synovial joint.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS articulation synoviale.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a joint and a synovial joint. (have)` → have · modèle : The patients have a joint and a synovial joint.
- `The patient in bed 5 … not have a joint. (do)` → does · modèle : The patient in bed 5 does not have a joint.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the joint. The patient in bed 5 has a problem with the synovial joint.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Articulation cartilagineuse, le lit 9 a Articulation et n'a pas Articulation synoviale.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the cartilaginous joint. The patient in bed 9 has a problem with the joint.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
