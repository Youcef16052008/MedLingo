# Relecture — Sémiologie Psychiatrique (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-010` — Le cas de Hana : Orientation / _Hana: Orientation_

**Objectif :** Comprendre un cas : orientation présent, hallucination absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient at the clinic.
   - FR : Hana est un·e patient·e à la clinique.
   - AR : Hana مريض/مريضة في العيادة.
2. **EN** Hana talks to Lina, a medical student.
   - FR : Hana parle avec Lina, étudiant·e en médecine.
   - AR : Hana يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has orientation.
   - FR : Elle a Orientation.
   - AR : عنده التوجه.
4. **EN** She does not have hallucination.
   - FR : Elle n'a pas de Hallucination.
   - AR : ليس عنده الهلوسة.
5. **EN** Lina asks, “Do you have hallucination?”
   - FR : Lina demande : « Avez-vous Hallucination ? »
   - AR : يسأل Lina: «هل عندك الهلوسة؟»
6. **EN** Hana says, “No, I do not.”
   - FR : Hana répond : « Non. »
   - AR : يجيب Hana: «لا.»
7. **EN** Lina writes “Orientation” in the file.
   - FR : Lina écrit « Orientation » dans le dossier.
   - AR : يكتب Lina «Orientation» في الملف.

### Expressions ciblées
- `gen:orientation:v1` — **orientation** · FR : Orientation · AR : التوجه
- `gen:hallucination:v1` — **hallucination** · FR : Hallucination · AR : الهلوسة
- `gen:delusion:v1` — **delusion** · FR : Délire · AR : الوهم الثابت

### Questions (acceptées / modèle / hint)
- **q1.** Does Hana have orientation?
  - accepté : yes ; yes she does ; yes she has orientation ; she has orientation
  - modèle : Yes, she has orientation.
- **q2.** Does she have hallucination?
  - accepté : no ; no she does not ; no she doesn't ; she does not have hallucination ; she doesn't
  - modèle : No, she does not have hallucination.
  - hint (si erreur) — FR : Attention : Hana n'a PAS hallucination.
- **q3.** Does she have orientation or hallucination?
  - accepté : orientation ; she has orientation
  - modèle : She has orientation.
- **q4.** What does Hana have?
  - accepté : orientation ; she has orientation
  - modèle : She has orientation.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have delusion?
  - accepté : yes ; yes he does ; he has delusion
  - modèle : Yes, he has delusion.
- **Tt2.** Does he have orientation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have orientation ; he doesn't
  - modèle : No, he does not have orientation.
  - hint (si erreur) — FR : Attention : il n'a PAS orientation.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … orientation. (have)` → has · modèle : She has orientation.
- `They … not have hallucination. (do)` → do · modèle : They do not have hallucination.
- `I … orientation. (have)` → have · modèle : I have orientation.

### Productions
- **Reformulation :** Rapporte le cas de Hana en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has orientation. She does not have hallucination.*
- **Transfert (contexte) :** Un autre patient a Délire. Il n'a pas de Orientation.
- Glossaire : Délire : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has delusion. He does not have orientation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-010` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Orientation et Hallucination à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient.
   - FR : Karim est un·e patient·e.
   - AR : Karim مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check orientation.”
   - FR : « D'abord, nous vérifions Orientation. »
   - AR : «أولًا، نتحقق من التوجه.»
5. **EN** “Then, we check hallucination.”
   - FR : « Ensuite, nous vérifions Hallucination. »
   - AR : «ثم نتحقق من الهلوسة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:orientation:v1` — **orientation** · FR : Orientation · AR : التوجه
- `gen:hallucination:v1` — **hallucination** · FR : Hallucination · AR : الهلوسة
- `gen:delusion:v1` — **delusion** · FR : Délire · AR : الوهم الثابت

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : orientation ; we check orientation ; orientation first ; the doctor checks orientation first
  - modèle : First, the doctor checks orientation.
- **q2.** What does the doctor check next?
  - accepté : hallucination ; then we check hallucination ; hallucination next ; the doctor checks hallucination next
  - modèle : Then, the doctor checks hallucination.
- **q3.** Does the doctor check hallucination before orientation?
  - accepté : no ; no he does not ; no he doesn't ; he checks orientation first
  - modèle : No, he checks orientation first, then hallucination.
  - hint (si erreur) — FR : Attention : Orientation vient D'ABORD, ensuite Hallucination.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : delusion ; delusion first ; he checks delusion first
  - modèle : First, the doctor checks delusion.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check orientation. Then, we check hallucination.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Délire, ensuite Orientation, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check delusion. Then, we check orientation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-010` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Orientation / Hallucination, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has orientation.
   - FR : Le patient du lit 3 a Orientation.
   - AR : مريض السرير ٣ عنده التوجه.
4. **EN** The patient in bed 5 has hallucination.
   - FR : Le patient du lit 5 a Hallucination.
   - AR : مريض السرير ٥ عنده الهلوسة.
5. **EN** The patient in bed 5 does not have orientation.
   - FR : Le patient du lit 5 n'a pas de Orientation.
   - AR : مريض السرير ٥ ليس عنده التوجه.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:orientation:v1` — **orientation** · FR : Orientation · AR : التوجه
- `gen:hallucination:v1` — **hallucination** · FR : Hallucination · AR : الهلوسة
- `gen:delusion:v1` — **delusion** · FR : Délire · AR : الوهم الثابت

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : orientation ; the patient with orientation
  - modèle : The patient in bed 3 has orientation.
- **q2.** What does the patient in bed 5 have?
  - accepté : hallucination
  - modèle : He has hallucination.
- **q3.** Does the patient in bed 5 have orientation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have orientation ; he doesn't
  - modèle : No, he does not have orientation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS orientation — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : delusion
  - modèle : He has delusion.
- **Tt2.** Does the patient in bed 9 have hallucination?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hallucination ; he doesn't
  - modèle : No, he does not have hallucination.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS hallucination.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … orientation and hallucination. (have)` → have · modèle : The patients have orientation and hallucination.
- `The patient in bed 5 … not have orientation. (do)` → does · modèle : The patient in bed 5 does not have orientation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has orientation. The patient in bed 5 has hallucination.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Délire, le lit 9 a Orientation et n'a pas Hallucination.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has delusion. The patient in bed 9 has orientation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
