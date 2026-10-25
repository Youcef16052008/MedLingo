# Relecture — Sémiologie Neurologique (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-008` — Le cas de Meriem : Céphalée / _Meriem: Headache_

**Objectif :** Comprendre un cas : céphalée présent, coma absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient at the clinic.
   - FR : Meriem est un·e patient·e à la clinique.
   - AR : Meriem مريض/مريضة في العيادة.
2. **EN** Meriem talks to Lina, a medical student.
   - FR : Meriem parle avec Lina, étudiant·e en médecine.
   - AR : Meriem يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has headache.
   - FR : Elle a Céphalée.
   - AR : عنده الصداع.
4. **EN** She does not have coma.
   - FR : Elle n'a pas de Coma.
   - AR : ليس عنده الغيبوبة.
5. **EN** Lina asks, “Do you have coma?”
   - FR : Lina demande : « Avez-vous Coma ? »
   - AR : يسأل Lina: «هل عندك الغيبوبة؟»
6. **EN** Meriem says, “No, I do not.”
   - FR : Meriem répond : « Non. »
   - AR : يجيب Meriem: «لا.»
7. **EN** Lina writes “Headache” in the file.
   - FR : Lina écrit « Céphalée » dans le dossier.
   - AR : يكتب Lina «Céphalée» في الملف.

### Expressions ciblées
- `gen:headache:v1` — **headache** · FR : Céphalée · AR : الصداع
- `gen:coma:v1` — **coma** · FR : Coma · AR : الغيبوبة
- `gen:level-of-consciousness:v1` — **a level of consciousness** · FR : Niveau de conscience · AR : مستوى الوعي

### Questions (acceptées / modèle / hint)
- **q1.** Does Meriem have headache?
  - accepté : yes ; yes she does ; yes she has headache ; she has headache
  - modèle : Yes, she has headache.
- **q2.** Does she have coma?
  - accepté : no ; no she does not ; no she doesn't ; she does not have coma ; she doesn't
  - modèle : No, she does not have coma.
  - hint (si erreur) — FR : Attention : Meriem n'a PAS coma.
- **q3.** Does she have headache or coma?
  - accepté : headache ; she has headache
  - modèle : She has headache.
- **q4.** What does Meriem have?
  - accepté : headache ; she has headache
  - modèle : She has headache.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have level of consciousness?
  - accepté : yes ; yes he does ; he has a level of consciousness
  - modèle : Yes, he has a level of consciousness.
- **Tt2.** Does he have headache?
  - accepté : no ; no he does not ; no he doesn't ; he does not have headache ; he doesn't
  - modèle : No, he does not have headache.
  - hint (si erreur) — FR : Attention : il n'a PAS céphalée.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … headache. (have)` → has · modèle : She has headache.
- `They … not have coma. (do)` → do · modèle : They do not have coma.
- `I … headache. (have)` → have · modèle : I have headache.

### Productions
- **Reformulation :** Rapporte le cas de Meriem en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has headache. She does not have coma.*
- **Transfert (contexte) :** Un autre patient a Niveau de conscience. Il n'a pas de Céphalée.
- Glossaire : Niveau de conscience : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a level of consciousness. He does not have headache.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Céphalée et Niveau de conscience à un patient.
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
4. **EN** “First, we check headache.”
   - FR : « D'abord, nous vérifions Céphalée. »
   - AR : «أولًا، نتحقق من الصداع.»
5. **EN** “Then, we check level of consciousness.”
   - FR : « Ensuite, nous vérifions Niveau de conscience. »
   - AR : «ثم نتحقق من مستوى الوعي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:headache:v1` — **headache** · FR : Céphalée · AR : الصداع
- `gen:level-of-consciousness:v1` — **a level of consciousness** · FR : Niveau de conscience · AR : مستوى الوعي
- `gen:coma:v1` — **coma** · FR : Coma · AR : الغيبوبة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : headache ; we check headache ; headache first ; the doctor checks headache first
  - modèle : First, the doctor checks headache.
- **q2.** What does the doctor check next?
  - accepté : level of consciousness ; then we check level of consciousness ; level of consciousness next ; the doctor checks level of consciousness next
  - modèle : Then, the doctor checks level of consciousness.
- **q3.** Does the doctor check level of consciousness before headache?
  - accepté : no ; no he does not ; no he doesn't ; he checks headache first
  - modèle : No, he checks headache first, then level of consciousness.
  - hint (si erreur) — FR : Attention : Céphalée vient D'ABORD, ensuite Niveau de conscience.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : coma ; coma first ; he checks coma first
  - modèle : First, the doctor checks coma.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check headache. Then, we check level of consciousness.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Coma, ensuite Céphalée, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check coma. Then, we check headache.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Céphalée / Coma, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has headache.
   - FR : Le patient du lit 3 a Céphalée.
   - AR : مريض السرير ٣ عنده الصداع.
4. **EN** The patient in bed 5 has coma.
   - FR : Le patient du lit 5 a Coma.
   - AR : مريض السرير ٥ عنده الغيبوبة.
5. **EN** The patient in bed 5 does not have headache.
   - FR : Le patient du lit 5 n'a pas de Céphalée.
   - AR : مريض السرير ٥ ليس عنده الصداع.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:headache:v1` — **headache** · FR : Céphalée · AR : الصداع
- `gen:coma:v1` — **coma** · FR : Coma · AR : الغيبوبة
- `gen:level-of-consciousness:v1` — **a level of consciousness** · FR : Niveau de conscience · AR : مستوى الوعي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : headache ; the patient with headache
  - modèle : The patient in bed 3 has headache.
- **q2.** What does the patient in bed 5 have?
  - accepté : coma ; he has coma
  - modèle : He has coma.
- **q3.** Does the patient in bed 5 have headache?
  - accepté : no ; no he does not ; no he doesn't ; he does not have headache ; he doesn't
  - modèle : No, he does not have headache.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS céphalée — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : level of consciousness ; a level of consciousness ; he has a level of consciousness
  - modèle : He has a level of consciousness.
- **Tt2.** Does the patient in bed 9 have coma?
  - accepté : no ; no he does not ; no he doesn't ; he does not have coma ; he doesn't
  - modèle : No, he does not have coma.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS coma.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … headache and coma. (have)` → have · modèle : The patients have headache and coma.
- `The patient in bed 5 … not have headache. (do)` → does · modèle : The patient in bed 5 does not have headache.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has headache. The patient in bed 5 has coma.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Niveau de conscience, le lit 9 a Céphalée et n'a pas Coma.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a level of consciousness. The patient in bed 9 has headache.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
