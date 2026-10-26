# Relecture — Tissu Conjonctif (histo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `histo-case-006` — Le cas de Sofiane : Fibre de collagène anormal(e) / _Sofiane: the collagen fiber is not normal_

**Objectif :** Comprendre des résultats : Tissu conjonctif normal(e), Fibre de collagène anormal(e).
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
4. **EN** The doctor says, “The connective tissue is normal.”
   - FR : Le médecin dit : « Tissu conjonctif est normal(e). »
   - AR : يقول الطبيب: «النسيج الضام طبيعي/طبيعية.»
5. **EN** “But the collagen fiber is not normal.”
   - FR : « Mais Fibre de collagène n'est pas normal(e). »
   - AR : «لكن الليف الكولاجيني غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the collagen fiber normal?”
   - FR : Lina demande : « Fibre de collagène est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الليف الكولاجيني طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:connective-tissue:v1` — **a connective tissue** · FR : Tissu conjonctif · AR : النسيج الضام
- `gen:ground-substance:v1` — **a ground substance** · FR : Substance fondamentale · AR : المادة الأساسية (الأرضية)
- `gen:collagen-fiber:v1` — **a collagen fiber** · FR : Fibre de collagène · AR : الليف الكولاجيني

### Questions (acceptées / modèle / hint)
- **q1.** Is the connective tissue normal?
  - accepté : yes ; yes it is ; the connective tissue is normal ; it is normal
  - modèle : Yes, the connective tissue is normal.
- **q2.** Is the collagen fiber normal?
  - accepté : no ; no it is not ; no it isn't ; the collagen fiber is not normal ; it is not normal ; it isn't normal
  - modèle : No, the collagen fiber is not normal.
  - hint (si erreur) — FR : Attention : Fibre de collagène n'est PAS normal(e).
- **q3.** Which one is not normal: the connective tissue or the collagen fiber?
  - accepté : collagen fiber ; the collagen fiber
  - modèle : The collagen fiber is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the ground substance normal?
  - accepté : yes ; yes it is ; the ground substance is normal
  - modèle : Yes, the ground substance is normal.
- **Tt2.** Is the connective tissue normal?
  - accepté : no ; no it is not ; no it isn't ; the connective tissue is not normal ; it is not normal
  - modèle : No, the connective tissue is not normal.
  - hint (si erreur) — FR : Attention : Tissu conjonctif n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The connective tissue … normal. (be)` → is · modèle : The connective tissue is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sofiane en deux phrases. Garde la négation. — amorces : The connective tissue is… / The collagen fiber is not… — modèle : *The connective tissue is normal. The collagen fiber is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Substance fondamentale normal(e), mais Tissu conjonctif anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The ground substance is normal. The connective tissue is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `histo-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Tissu conjonctif et Substance fondamentale à un patient.
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
4. **EN** “First, we check connective tissue.”
   - FR : « D'abord, nous vérifions Tissu conjonctif. »
   - AR : «أولًا، نتحقق من النسيج الضام.»
5. **EN** “Then, we check ground substance.”
   - FR : « Ensuite, nous vérifions Substance fondamentale. »
   - AR : «ثم نتحقق من المادة الأساسية (الأرضية).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:connective-tissue:v1` — **a connective tissue** · FR : Tissu conjonctif · AR : النسيج الضام
- `gen:ground-substance:v1` — **a ground substance** · FR : Substance fondamentale · AR : المادة الأساسية (الأرضية)
- `gen:collagen-fiber:v1` — **a collagen fiber** · FR : Fibre de collagène · AR : الليف الكولاجيني

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : connective tissue ; we check connective tissue ; connective tissue first ; the doctor checks connective tissue first
  - modèle : First, the doctor checks connective tissue.
- **q2.** What does the doctor check next?
  - accepté : ground substance ; then we check ground substance ; ground substance next ; the doctor checks ground substance next
  - modèle : Then, the doctor checks ground substance.
- **q3.** Does the doctor check ground substance before connective tissue?
  - accepté : no ; no he does not ; no he doesn't ; he checks connective tissue first
  - modèle : No, he checks connective tissue first, then ground substance.
  - hint (si erreur) — FR : Attention : Tissu conjonctif vient D'ABORD, ensuite Substance fondamentale.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : collagen fiber ; collagen fiber first ; he checks collagen fiber first
  - modèle : First, the doctor checks collagen fiber.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check connective tissue. Then, we check ground substance.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fibre de collagène, ensuite Tissu conjonctif, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check collagen fiber. Then, we check connective tissue.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `histo-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Tissu conjonctif / Substance fondamentale, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the connective tissue.
   - FR : Le patient du lit 3 a Tissu conjonctif.
   - AR : مريض السرير ٣ عنده النسيج الضام.
4. **EN** The patient in bed 5 has a problem with the ground substance.
   - FR : Le patient du lit 5 a Substance fondamentale.
   - AR : مريض السرير ٥ عنده المادة الأساسية (الأرضية).
5. **EN** The patient in bed 5 does not have a problem with the connective tissue.
   - FR : Le patient du lit 5 n'a pas de Tissu conjonctif.
   - AR : مريض السرير ٥ ليس عنده النسيج الضام.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:connective-tissue:v1` — **a connective tissue** · FR : Tissu conjonctif · AR : النسيج الضام
- `gen:ground-substance:v1` — **a ground substance** · FR : Substance fondamentale · AR : المادة الأساسية (الأرضية)
- `gen:collagen-fiber:v1` — **a collagen fiber** · FR : Fibre de collagène · AR : الليف الكولاجيني

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : connective tissue ; a connective tissue ; the patient with connective tissue
  - modèle : The patient in bed 3 has a connective tissue.
- **q2.** What does the patient in bed 5 have?
  - accepté : ground substance ; a problem with the ground substance
  - modèle : He has a problem with the ground substance.
- **q3.** Does the patient in bed 5 have connective tissue?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the connective tissue ; he doesn't
  - modèle : No, he does not have a problem with the connective tissue.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS tissu conjonctif — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : collagen fiber ; a problem with the collagen fiber
  - modèle : He has a problem with the collagen fiber.
- **Tt2.** Does the patient in bed 9 have ground substance?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the ground substance ; he doesn't
  - modèle : No, he does not have a problem with the ground substance.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS substance fondamentale.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a connective tissue and a ground substance. (have)` → have · modèle : The patients have a connective tissue and a ground substance.
- `The patient in bed 5 … not have a connective tissue. (do)` → does · modèle : The patient in bed 5 does not have a connective tissue.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the connective tissue. The patient in bed 5 has a problem with the ground substance.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Fibre de collagène, le lit 9 a Tissu conjonctif et n'a pas Substance fondamentale.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the collagen fiber. The patient in bed 9 has a problem with the connective tissue.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
