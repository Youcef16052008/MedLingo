# Relecture — Mental Health (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-022` — Le cas de Hana : Stress / _Hana: Stress_

**Objectif :** Comprendre un cas : stress présent, trouble anxieux absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient at the clinic.
   - FR : Hana est un·e patient·e à la clinique.
   - AR : Hana مريض/مريضة في العيادة.
2. **EN** Hana talks to Lina, a medical student.
   - FR : Hana parle avec Lina, étudiant·e en médecine.
   - AR : Hana يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has stress.
   - FR : Elle a Stress.
   - AR : عنده التوتر.
4. **EN** She does not have anxiety disorder.
   - FR : Elle n'a pas de Trouble anxieux.
   - AR : ليس عنده اضطراب القلق.
5. **EN** Lina asks, “Do you have anxiety disorder?”
   - FR : Lina demande : « Avez-vous Trouble anxieux ? »
   - AR : يسأل Lina: «هل عندك اضطراب القلق؟»
6. **EN** Hana says, “No, I do not.”
   - FR : Hana répond : « Non. »
   - AR : يجيب Hana: «لا.»
7. **EN** Lina writes “Stress” in the file.
   - FR : Lina écrit « Stress » dans le dossier.
   - AR : يكتب Lina «Stress» في الملف.

### Expressions ciblées
- `gen:stress:v1` — **stress** · FR : Stress · AR : التوتر
- `gen:anxiety-disorder:v1` — **anxiety disorder** · FR : Trouble anxieux · AR : اضطراب القلق
- `gen:mental-health:v1` — **a mental health** · FR : Santé mentale · AR : الصحة النفسية

### Questions (acceptées / modèle / hint)
- **q1.** Does Hana have stress?
  - accepté : yes ; yes she does ; yes she has stress ; she has stress
  - modèle : Yes, she has stress.
- **q2.** Does she have anxiety disorder?
  - accepté : no ; no she does not ; no she doesn't ; she does not have anxiety disorder ; she doesn't
  - modèle : No, she does not have anxiety disorder.
  - hint (si erreur) — FR : Attention : Hana n'a PAS trouble anxieux.
- **q3.** Does she have stress or anxiety disorder?
  - accepté : stress ; she has stress
  - modèle : She has stress.
- **q4.** What does Hana have?
  - accepté : stress ; she has stress
  - modèle : She has stress.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have mental health?
  - accepté : yes ; yes he does ; he has a mental health
  - modèle : Yes, he has a mental health.
- **Tt2.** Does he have stress?
  - accepté : no ; no he does not ; no he doesn't ; he does not have stress ; he doesn't
  - modèle : No, he does not have stress.
  - hint (si erreur) — FR : Attention : il n'a PAS stress.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … stress. (have)` → has · modèle : She has stress.
- `They … not have anxiety disorder. (do)` → do · modèle : They do not have anxiety disorder.
- `I … stress. (have)` → have · modèle : I have stress.

### Productions
- **Reformulation :** Rapporte le cas de Hana en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has stress. She does not have anxiety disorder.*
- **Transfert (contexte) :** Un autre patient a Santé mentale. Il n'a pas de Stress.
- Glossaire : Santé mentale : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a mental health. He does not have stress.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-022` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Santé mentale et Stress à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check mental health.”
   - FR : « D'abord, nous vérifions Santé mentale. »
   - AR : «أولًا، نتحقق من الصحة النفسية.»
5. **EN** “Then, we check stress.”
   - FR : « Ensuite, nous vérifions Stress. »
   - AR : «ثم نتحقق من التوتر.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:mental-health:v1` — **a mental health** · FR : Santé mentale · AR : الصحة النفسية
- `gen:stress:v1` — **stress** · FR : Stress · AR : التوتر
- `gen:anxiety-disorder:v1` — **anxiety disorder** · FR : Trouble anxieux · AR : اضطراب القلق

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : mental health ; we check mental health ; mental health first ; the doctor checks mental health first
  - modèle : First, the doctor checks mental health.
- **q2.** What does the doctor check next?
  - accepté : stress ; then we check stress ; stress next ; the doctor checks stress next
  - modèle : Then, the doctor checks stress.
- **q3.** Does the doctor check stress before mental health?
  - accepté : no ; no he does not ; no he doesn't ; he checks mental health first
  - modèle : No, he checks mental health first, then stress.
  - hint (si erreur) — FR : Attention : Santé mentale vient D'ABORD, ensuite Stress.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : anxiety disorder ; anxiety disorder first ; he checks anxiety disorder first
  - modèle : First, the doctor checks anxiety disorder.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check mental health. Then, we check stress.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Trouble anxieux, ensuite Santé mentale, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check anxiety disorder. Then, we check mental health.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-022` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Stress / Trouble anxieux, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has stress.
   - FR : Le patient du lit 3 a Stress.
   - AR : مريض السرير ٣ عنده التوتر.
4. **EN** The patient in bed 5 has anxiety disorder.
   - FR : Le patient du lit 5 a Trouble anxieux.
   - AR : مريض السرير ٥ عنده اضطراب القلق.
5. **EN** The patient in bed 5 does not have stress.
   - FR : Le patient du lit 5 n'a pas de Stress.
   - AR : مريض السرير ٥ ليس عنده التوتر.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:stress:v1` — **stress** · FR : Stress · AR : التوتر
- `gen:anxiety-disorder:v1` — **anxiety disorder** · FR : Trouble anxieux · AR : اضطراب القلق
- `gen:mental-health:v1` — **a mental health** · FR : Santé mentale · AR : الصحة النفسية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : stress ; the patient with stress
  - modèle : The patient in bed 3 has stress.
- **q2.** What does the patient in bed 5 have?
  - accepté : anxiety disorder ; he has anxiety disorder
  - modèle : He has anxiety disorder.
- **q3.** Does the patient in bed 5 have stress?
  - accepté : no ; no he does not ; no he doesn't ; he does not have stress ; he doesn't
  - modèle : No, he does not have stress.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS stress — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : mental health ; a mental health ; he has a mental health
  - modèle : He has a mental health.
- **Tt2.** Does the patient in bed 9 have anxiety disorder?
  - accepté : no ; no he does not ; no he doesn't ; he does not have anxiety disorder ; he doesn't
  - modèle : No, he does not have anxiety disorder.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS trouble anxieux.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … stress and anxiety disorder. (have)` → have · modèle : The patients have stress and anxiety disorder.
- `The patient in bed 5 … not have stress. (do)` → does · modèle : The patient in bed 5 does not have stress.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has stress. The patient in bed 5 has anxiety disorder.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Santé mentale, le lit 9 a Stress et n'a pas Trouble anxieux.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a mental health. The patient in bed 9 has stress.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
