# Relecture — Geriatrics (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-012` — Le cas de Sara : Soins aux personnes âgées / _Sara: Elderly care_

**Objectif :** Comprendre un cas : soins aux personnes âgées présent, prévention des chutes absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Sara talks to Lina, a medical student.
   - FR : Sara parle avec Lina, étudiant·e en médecine.
   - AR : Sara يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a elderly care.
   - FR : Elle a Soins aux personnes âgées.
   - AR : عنده رعاية المسنين.
4. **EN** She does not have a fall prevention.
   - FR : Elle n'a pas de Prévention des chutes.
   - AR : ليس عنده الوقاية من السقوط.
5. **EN** Lina asks, “Do you have a fall prevention?”
   - FR : Lina demande : « Avez-vous Prévention des chutes ? »
   - AR : يسأل Lina: «هل عندك الوقاية من السقوط؟»
6. **EN** Sara says, “No, I do not.”
   - FR : Sara répond : « Non. »
   - AR : يجيب Sara: «لا.»
7. **EN** Lina writes “Elderly care” in the file.
   - FR : Lina écrit « Soins aux personnes âgées » dans le dossier.
   - AR : يكتب Lina «Soins aux personnes âgées» في الملف.

### Expressions ciblées
- `gen:elderly-care:v1` — **a elderly care** · FR : Soins aux personnes âgées · AR : رعاية المسنين
- `gen:fall-prevention:v1` — **a fall prevention** · FR : Prévention des chutes · AR : الوقاية من السقوط
- `gen:hip-fracture:v1` — **a hip fracture** · FR : Fracture du col du fémur · AR : كسر عظمة الفخذ

### Questions (acceptées / modèle / hint)
- **q1.** Does Sara have elderly care?
  - accepté : yes ; yes she does ; yes she has a elderly care ; she has a elderly care
  - modèle : Yes, she has a elderly care.
- **q2.** Does she have fall prevention?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a fall prevention ; she doesn't
  - modèle : No, she does not have a fall prevention.
  - hint (si erreur) — FR : Attention : Sara n'a PAS prévention des chutes.
- **q3.** Does she have elderly care or fall prevention?
  - accepté : elderly care ; a elderly care ; she has a elderly care
  - modèle : She has a elderly care.
- **q4.** What does Sara have?
  - accepté : elderly care ; a elderly care ; she has a elderly care
  - modèle : She has a elderly care.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have hip fracture?
  - accepté : yes ; yes he does ; he has a hip fracture
  - modèle : Yes, he has a hip fracture.
- **Tt2.** Does he have elderly care?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a elderly care ; he doesn't
  - modèle : No, he does not have a elderly care.
  - hint (si erreur) — FR : Attention : il n'a PAS soins aux personnes âgées.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a elderly care. (have)` → has · modèle : She has a elderly care.
- `They … not have a fall prevention. (do)` → do · modèle : They do not have a fall prevention.
- `I … a elderly care. (have)` → have · modèle : I have a elderly care.

### Productions
- **Reformulation :** Rapporte le cas de Sara en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a elderly care. She does not have a fall prevention.*
- **Transfert (contexte) :** Un autre patient a Fracture du col du fémur. Il n'a pas de Soins aux personnes âgées.
- Glossaire : Fracture du col du fémur : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a hip fracture. He does not have a elderly care.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-012` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Soins aux personnes âgées et Prévention des chutes à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient.
   - FR : Youssef est un·e patient·e.
   - AR : Youssef مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check elderly care.”
   - FR : « D'abord, nous vérifions Soins aux personnes âgées. »
   - AR : «أولًا، نتحقق من رعاية المسنين.»
5. **EN** “Then, we check fall prevention.”
   - FR : « Ensuite, nous vérifions Prévention des chutes. »
   - AR : «ثم نتحقق من الوقاية من السقوط.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:elderly-care:v1` — **a elderly care** · FR : Soins aux personnes âgées · AR : رعاية المسنين
- `gen:fall-prevention:v1` — **a fall prevention** · FR : Prévention des chutes · AR : الوقاية من السقوط
- `gen:hip-fracture:v1` — **a hip fracture** · FR : Fracture du col du fémur · AR : كسر عظمة الفخذ

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : elderly care ; we check elderly care ; elderly care first ; the doctor checks elderly care first
  - modèle : First, the doctor checks elderly care.
- **q2.** What does the doctor check next?
  - accepté : fall prevention ; then we check fall prevention ; fall prevention next ; the doctor checks fall prevention next
  - modèle : Then, the doctor checks fall prevention.
- **q3.** Does the doctor check fall prevention before elderly care?
  - accepté : no ; no he does not ; no he doesn't ; he checks elderly care first
  - modèle : No, he checks elderly care first, then fall prevention.
  - hint (si erreur) — FR : Attention : Soins aux personnes âgées vient D'ABORD, ensuite Prévention des chutes.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : hip fracture ; hip fracture first ; he checks hip fracture first
  - modèle : First, the doctor checks hip fracture.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check elderly care. Then, we check fall prevention.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fracture du col du fémur, ensuite Soins aux personnes âgées, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check hip fracture. Then, we check elderly care.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-012` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Soins aux personnes âgées / Prévention des chutes, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a elderly care.
   - FR : Le patient du lit 3 a Soins aux personnes âgées.
   - AR : مريض السرير ٣ عنده رعاية المسنين.
4. **EN** The patient in bed 5 has a fall prevention.
   - FR : Le patient du lit 5 a Prévention des chutes.
   - AR : مريض السرير ٥ عنده الوقاية من السقوط.
5. **EN** The patient in bed 5 does not have a elderly care.
   - FR : Le patient du lit 5 n'a pas de Soins aux personnes âgées.
   - AR : مريض السرير ٥ ليس عنده رعاية المسنين.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:elderly-care:v1` — **a elderly care** · FR : Soins aux personnes âgées · AR : رعاية المسنين
- `gen:fall-prevention:v1` — **a fall prevention** · FR : Prévention des chutes · AR : الوقاية من السقوط
- `gen:hip-fracture:v1` — **a hip fracture** · FR : Fracture du col du fémur · AR : كسر عظمة الفخذ

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : elderly care ; a elderly care ; the patient with elderly care
  - modèle : The patient in bed 3 has a elderly care.
- **q2.** What does the patient in bed 5 have?
  - accepté : fall prevention ; a fall prevention ; he has a fall prevention
  - modèle : He has a fall prevention.
- **q3.** Does the patient in bed 5 have elderly care?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a elderly care ; he doesn't
  - modèle : No, he does not have a elderly care.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS soins aux personnes âgées — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : hip fracture ; a hip fracture ; he has a hip fracture
  - modèle : He has a hip fracture.
- **Tt2.** Does the patient in bed 9 have fall prevention?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a fall prevention ; he doesn't
  - modèle : No, he does not have a fall prevention.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS prévention des chutes.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a elderly care and a fall prevention. (have)` → have · modèle : The patients have a elderly care and a fall prevention.
- `The patient in bed 5 … not have a elderly care. (do)` → does · modèle : The patient in bed 5 does not have a elderly care.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a elderly care. The patient in bed 5 has a fall prevention.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Fracture du col du fémur, le lit 9 a Soins aux personnes âgées et n'a pas Prévention des chutes.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a hip fracture. The patient in bed 9 has a elderly care.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
