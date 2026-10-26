# Relecture — Structure Atomique (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-021` — Le cas de Sara : Élément anormal(e) / _Sara: the element is not normal_

**Objectif :** Comprendre des résultats : Chimie normal(e), Élément anormal(e).
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
4. **EN** The doctor says, “The chemistry is normal.”
   - FR : Le médecin dit : « Chimie est normal(e). »
   - AR : يقول الطبيب: «الكيمياء طبيعي/طبيعية.»
5. **EN** “But the element is not normal.”
   - FR : « Mais Élément n'est pas normal(e). »
   - AR : «لكن العنصر غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the element normal?”
   - FR : Amine demande : « Élément est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل العنصر طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:chemistry:v1` — **a chemistry** · FR : Chimie · AR : الكيمياء
- `gen:matter:v1` — **a matter** · FR : Matière · AR : المادة
- `gen:element:v1` — **an element** · FR : Élément · AR : العنصر

### Questions (acceptées / modèle / hint)
- **q1.** Is the chemistry normal?
  - accepté : yes ; yes it is ; the chemistry is normal ; it is normal
  - modèle : Yes, the chemistry is normal.
- **q2.** Is the element normal?
  - accepté : no ; no it is not ; no it isn't ; the element is not normal ; it is not normal ; it isn't normal
  - modèle : No, the element is not normal.
  - hint (si erreur) — FR : Attention : Élément n'est PAS normal(e).
- **q3.** Which one is not normal: the chemistry or the element?
  - accepté : element ; the element
  - modèle : The element is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the matter normal?
  - accepté : yes ; yes it is ; the matter is normal
  - modèle : Yes, the matter is normal.
- **Tt2.** Is the chemistry normal?
  - accepté : no ; no it is not ; no it isn't ; the chemistry is not normal ; it is not normal
  - modèle : No, the chemistry is not normal.
  - hint (si erreur) — FR : Attention : Chimie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The chemistry … normal. (be)` → is · modèle : The chemistry is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The chemistry is… / The element is not… — modèle : *The chemistry is normal. The element is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Matière normal(e), mais Chimie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The matter is normal. The chemistry is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-021` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Chimie et Matière à un patient.
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
4. **EN** “First, we check chemistry.”
   - FR : « D'abord, nous vérifions Chimie. »
   - AR : «أولًا، نتحقق من الكيمياء.»
5. **EN** “Then, we check matter.”
   - FR : « Ensuite, nous vérifions Matière. »
   - AR : «ثم نتحقق من المادة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:chemistry:v1` — **a chemistry** · FR : Chimie · AR : الكيمياء
- `gen:matter:v1` — **a matter** · FR : Matière · AR : المادة
- `gen:element:v1` — **an element** · FR : Élément · AR : العنصر

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : chemistry ; we check chemistry ; chemistry first ; the doctor checks chemistry first
  - modèle : First, the doctor checks chemistry.
- **q2.** What does the doctor check next?
  - accepté : matter ; then we check matter ; matter next ; the doctor checks matter next
  - modèle : Then, the doctor checks matter.
- **q3.** Does the doctor check matter before chemistry?
  - accepté : no ; no he does not ; no he doesn't ; he checks chemistry first
  - modèle : No, he checks chemistry first, then matter.
  - hint (si erreur) — FR : Attention : Chimie vient D'ABORD, ensuite Matière.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : element ; element first ; he checks element first
  - modèle : First, the doctor checks element.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check chemistry. Then, we check matter.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Élément, ensuite Chimie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check element. Then, we check chemistry.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-021` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Chimie / Matière, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the chemistry.
   - FR : Le patient du lit 3 a Chimie.
   - AR : مريض السرير ٣ عنده الكيمياء.
4. **EN** The patient in bed 5 has a problem with the matter.
   - FR : Le patient du lit 5 a Matière.
   - AR : مريض السرير ٥ عنده المادة.
5. **EN** The patient in bed 5 does not have a problem with the chemistry.
   - FR : Le patient du lit 5 n'a pas de Chimie.
   - AR : مريض السرير ٥ ليس عنده الكيمياء.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:chemistry:v1` — **a chemistry** · FR : Chimie · AR : الكيمياء
- `gen:matter:v1` — **a matter** · FR : Matière · AR : المادة
- `gen:element:v1` — **an element** · FR : Élément · AR : العنصر

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : chemistry ; a chemistry ; the patient with chemistry
  - modèle : The patient in bed 3 has a chemistry.
- **q2.** What does the patient in bed 5 have?
  - accepté : matter ; a problem with the matter
  - modèle : He has a problem with the matter.
- **q3.** Does the patient in bed 5 have chemistry?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the chemistry ; he doesn't
  - modèle : No, he does not have a problem with the chemistry.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS chimie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : element ; a problem with the element
  - modèle : He has a problem with the element.
- **Tt2.** Does the patient in bed 9 have matter?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the matter ; he doesn't
  - modèle : No, he does not have a problem with the matter.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS matière.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a chemistry and a matter. (have)` → have · modèle : The patients have a chemistry and a matter.
- `The patient in bed 5 … not have a chemistry. (do)` → does · modèle : The patient in bed 5 does not have a chemistry.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the chemistry. The patient in bed 5 has a problem with the matter.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Élément, le lit 9 a Chimie et n'a pas Matière.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the element. The patient in bed 9 has a problem with the chemistry.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
