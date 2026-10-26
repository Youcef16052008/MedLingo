# Relecture — Sémiologie Dermatologique (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-004` — Le cas de Nadia : Éruption cutanée (Exanthème) / _Nadia: Rash_

**Objectif :** Comprendre un cas : éruption cutanée (exanthème) présent, macule absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Nadia talks to Lina, a medical student.
   - FR : Nadia parle avec Lina, étudiant·e en médecine.
   - AR : Nadia يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has rash.
   - FR : Elle a Éruption cutanée (Exanthème).
   - AR : عنده الطفح الجلدي.
4. **EN** She does not have macule.
   - FR : Elle n'a pas de Macule.
   - AR : ليس عنده البقعة.
5. **EN** Lina asks, “Do you have macule?”
   - FR : Lina demande : « Avez-vous Macule ? »
   - AR : يسأل Lina: «هل عندك البقعة؟»
6. **EN** Nadia says, “No, I do not.”
   - FR : Nadia répond : « Non. »
   - AR : يجيب Nadia: «لا.»
7. **EN** Lina writes “Rash” in the file.
   - FR : Lina écrit « Éruption cutanée (Exanthème) » dans le dossier.
   - AR : يكتب Lina «Éruption cutanée (Exanthème)» في الملف.

### Expressions ciblées
- `gen:rash:v1` — **rash** · FR : Éruption cutanée (Exanthème) · AR : الطفح الجلدي
- `gen:macule:v1` — **macule** · FR : Macule · AR : البقعة
- `gen:papule:v1` — **papule** · FR : Papule · AR : الحبيبة

### Questions (acceptées / modèle / hint)
- **q1.** Does Nadia have rash?
  - accepté : yes ; yes she does ; yes she has rash ; she has rash
  - modèle : Yes, she has rash.
- **q2.** Does she have macule?
  - accepté : no ; no she does not ; no she doesn't ; she does not have macule ; she doesn't
  - modèle : No, she does not have macule.
  - hint (si erreur) — FR : Attention : Nadia n'a PAS macule.
- **q3.** Does she have rash or macule?
  - accepté : rash ; she has rash
  - modèle : She has rash.
- **q4.** What does Nadia have?
  - accepté : rash ; she has rash
  - modèle : She has rash.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have papule?
  - accepté : yes ; yes he does ; he has papule
  - modèle : Yes, he has papule.
- **Tt2.** Does he have rash?
  - accepté : no ; no he does not ; no he doesn't ; he does not have rash ; he doesn't
  - modèle : No, he does not have rash.
  - hint (si erreur) — FR : Attention : il n'a PAS éruption cutanée (exanthème).

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … rash. (have)` → has · modèle : She has rash.
- `They … not have macule. (do)` → do · modèle : They do not have macule.
- `I … rash. (have)` → have · modèle : I have rash.

### Productions
- **Reformulation :** Rapporte le cas de Nadia en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has rash. She does not have macule.*
- **Transfert (contexte) :** Un autre patient a Papule. Il n'a pas de Éruption cutanée (Exanthème).
- Glossaire : Papule : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has papule. He does not have rash.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Éruption cutanée (Exanthème) et Macule à un patient.
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
4. **EN** “First, we check rash.”
   - FR : « D'abord, nous vérifions Éruption cutanée (Exanthème). »
   - AR : «أولًا، نتحقق من الطفح الجلدي.»
5. **EN** “Then, we check macule.”
   - FR : « Ensuite, nous vérifions Macule. »
   - AR : «ثم نتحقق من البقعة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:rash:v1` — **rash** · FR : Éruption cutanée (Exanthème) · AR : الطفح الجلدي
- `gen:macule:v1` — **macule** · FR : Macule · AR : البقعة
- `gen:papule:v1` — **papule** · FR : Papule · AR : الحبيبة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : rash ; we check rash ; rash first ; the doctor checks rash first
  - modèle : First, the doctor checks rash.
- **q2.** What does the doctor check next?
  - accepté : macule ; then we check macule ; macule next ; the doctor checks macule next
  - modèle : Then, the doctor checks macule.
- **q3.** Does the doctor check macule before rash?
  - accepté : no ; no he does not ; no he doesn't ; he checks rash first
  - modèle : No, he checks rash first, then macule.
  - hint (si erreur) — FR : Attention : Éruption cutanée (Exanthème) vient D'ABORD, ensuite Macule.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : papule ; papule first ; he checks papule first
  - modèle : First, the doctor checks papule.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check rash. Then, we check macule.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Papule, ensuite Éruption cutanée (Exanthème), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check papule. Then, we check rash.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Éruption cutanée (Exanthème) / Macule, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has rash.
   - FR : Le patient du lit 3 a Éruption cutanée (Exanthème).
   - AR : مريض السرير ٣ عنده الطفح الجلدي.
4. **EN** The patient in bed 5 has macule.
   - FR : Le patient du lit 5 a Macule.
   - AR : مريض السرير ٥ عنده البقعة.
5. **EN** The patient in bed 5 does not have rash.
   - FR : Le patient du lit 5 n'a pas de Éruption cutanée (Exanthème).
   - AR : مريض السرير ٥ ليس عنده الطفح الجلدي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:rash:v1` — **rash** · FR : Éruption cutanée (Exanthème) · AR : الطفح الجلدي
- `gen:macule:v1` — **macule** · FR : Macule · AR : البقعة
- `gen:papule:v1` — **papule** · FR : Papule · AR : الحبيبة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : rash ; the patient with rash
  - modèle : The patient in bed 3 has rash.
- **q2.** What does the patient in bed 5 have?
  - accepté : macule
  - modèle : He has macule.
- **q3.** Does the patient in bed 5 have rash?
  - accepté : no ; no he does not ; no he doesn't ; he does not have rash ; he doesn't
  - modèle : No, he does not have rash.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS éruption cutanée (exanthème) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : papule
  - modèle : He has papule.
- **Tt2.** Does the patient in bed 9 have macule?
  - accepté : no ; no he does not ; no he doesn't ; he does not have macule ; he doesn't
  - modèle : No, he does not have macule.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS macule.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … rash and macule. (have)` → have · modèle : The patients have rash and macule.
- `The patient in bed 5 … not have rash. (do)` → does · modèle : The patient in bed 5 does not have rash.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has rash. The patient in bed 5 has macule.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Papule, le lit 9 a Éruption cutanée (Exanthème) et n'a pas Macule.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has papule. The patient in bed 9 has rash.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
