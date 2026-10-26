# Relecture — Ostéologie (anat)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anat-case-009` — Le cas de Sara : Os pariétal anormal(e) / _Sara: the parietal bone is not normal_

**Objectif :** Comprendre des résultats : Crâne normal(e), Os pariétal anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The skull is normal.”
   - FR : Le médecin dit : « Crâne est normal(e). »
   - AR : يقول الطبيب: «الجمجمة طبيعي/طبيعية.»
5. **EN** “But the parietal bone is not normal.”
   - FR : « Mais Os pariétal n'est pas normal(e). »
   - AR : «لكن العظم الجداري غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the parietal bone normal?”
   - FR : Amine demande : « Os pariétal est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل العظم الجداري طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:skull:v1` — **a skull** · FR : Crâne · AR : الجمجمة
- `gen:frontal-bone:v1` — **a frontal bone** · FR : Os frontal · AR : العظم الجبهي
- `gen:parietal-bone:v1` — **a parietal bone** · FR : Os pariétal · AR : العظم الجداري

### Questions (acceptées / modèle / hint)
- **q1.** Is the skull normal?
  - accepté : yes ; yes it is ; the skull is normal ; it is normal
  - modèle : Yes, the skull is normal.
- **q2.** Is the parietal bone normal?
  - accepté : no ; no it is not ; no it isn't ; the parietal bone is not normal ; it is not normal ; it isn't normal
  - modèle : No, the parietal bone is not normal.
  - hint (si erreur) — FR : Attention : Os pariétal n'est PAS normal(e).
- **q3.** Which one is not normal: the skull or the parietal bone?
  - accepté : parietal bone ; the parietal bone
  - modèle : The parietal bone is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the frontal bone normal?
  - accepté : yes ; yes it is ; the frontal bone is normal
  - modèle : Yes, the frontal bone is normal.
- **Tt2.** Is the skull normal?
  - accepté : no ; no it is not ; no it isn't ; the skull is not normal ; it is not normal
  - modèle : No, the skull is not normal.
  - hint (si erreur) — FR : Attention : Crâne n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The skull … normal. (be)` → is · modèle : The skull is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The skull is… / The parietal bone is not… — modèle : *The skull is normal. The parietal bone is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Os frontal normal(e), mais Crâne anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The frontal bone is normal. The skull is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anat-explain-009` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Crâne et Os frontal à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient.
   - FR : Leila est un·e patient·e.
   - AR : Leila مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check skull.”
   - FR : « D'abord, nous vérifions Crâne. »
   - AR : «أولًا، نتحقق من الجمجمة.»
5. **EN** “Then, we check frontal bone.”
   - FR : « Ensuite, nous vérifions Os frontal. »
   - AR : «ثم نتحقق من العظم الجبهي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:skull:v1` — **a skull** · FR : Crâne · AR : الجمجمة
- `gen:frontal-bone:v1` — **a frontal bone** · FR : Os frontal · AR : العظم الجبهي
- `gen:parietal-bone:v1` — **a parietal bone** · FR : Os pariétal · AR : العظم الجداري

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : skull ; we check skull ; skull first ; the doctor checks skull first
  - modèle : First, the doctor checks skull.
- **q2.** What does the doctor check next?
  - accepté : frontal bone ; then we check frontal bone ; frontal bone next ; the doctor checks frontal bone next
  - modèle : Then, the doctor checks frontal bone.
- **q3.** Does the doctor check frontal bone before skull?
  - accepté : no ; no he does not ; no he doesn't ; he checks skull first
  - modèle : No, he checks skull first, then frontal bone.
  - hint (si erreur) — FR : Attention : Crâne vient D'ABORD, ensuite Os frontal.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : parietal bone ; parietal bone first ; he checks parietal bone first
  - modèle : First, the doctor checks parietal bone.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check skull. Then, we check frontal bone.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Os pariétal, ensuite Crâne, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check parietal bone. Then, we check skull.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anat-handover-009` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Crâne / Os frontal, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the skull.
   - FR : Le patient du lit 3 a Crâne.
   - AR : مريض السرير ٣ عنده الجمجمة.
4. **EN** The patient in bed 5 has a problem with the frontal bone.
   - FR : Le patient du lit 5 a Os frontal.
   - AR : مريض السرير ٥ عنده العظم الجبهي.
5. **EN** The patient in bed 5 does not have a problem with the skull.
   - FR : Le patient du lit 5 n'a pas de Crâne.
   - AR : مريض السرير ٥ ليس عنده الجمجمة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:skull:v1` — **a skull** · FR : Crâne · AR : الجمجمة
- `gen:frontal-bone:v1` — **a frontal bone** · FR : Os frontal · AR : العظم الجبهي
- `gen:parietal-bone:v1` — **a parietal bone** · FR : Os pariétal · AR : العظم الجداري

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : skull ; a skull ; the patient with skull
  - modèle : The patient in bed 3 has a skull.
- **q2.** What does the patient in bed 5 have?
  - accepté : frontal bone ; a problem with the frontal bone
  - modèle : He has a problem with the frontal bone.
- **q3.** Does the patient in bed 5 have skull?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the skull ; he doesn't
  - modèle : No, he does not have a problem with the skull.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS crâne — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : parietal bone ; a problem with the parietal bone
  - modèle : He has a problem with the parietal bone.
- **Tt2.** Does the patient in bed 9 have frontal bone?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the frontal bone ; he doesn't
  - modèle : No, he does not have a problem with the frontal bone.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS os frontal.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a skull and a frontal bone. (have)` → have · modèle : The patients have a skull and a frontal bone.
- `The patient in bed 5 … not have a skull. (do)` → does · modèle : The patient in bed 5 does not have a skull.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the skull. The patient in bed 5 has a problem with the frontal bone.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Os pariétal, le lit 9 a Crâne et n'a pas Os frontal.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the parietal bone. The patient in bed 9 has a problem with the skull.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
