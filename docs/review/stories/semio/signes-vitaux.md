# Relecture — Signes Vitaux (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-002` — Le cas de Leila : Fièvre (Pyrexie) / _Leila: Fever_

**Objectif :** Comprendre un cas : fièvre (pyrexie) présent, hyperthermie absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Leila talks to Lina, a medical student.
   - FR : Leila parle avec Lina, étudiant·e en médecine.
   - AR : Leila يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has fever.
   - FR : Elle a Fièvre (Pyrexie).
   - AR : عنده الحمى.
4. **EN** She does not have hyperthermia.
   - FR : Elle n'a pas de Hyperthermie.
   - AR : ليس عنده ارتفاع الحرارة.
5. **EN** Lina asks, “Do you have hyperthermia?”
   - FR : Lina demande : « Avez-vous Hyperthermie ? »
   - AR : يسأل Lina: «هل عندك ارتفاع الحرارة؟»
6. **EN** Leila says, “No, I do not.”
   - FR : Leila répond : « Non. »
   - AR : يجيب Leila: «لا.»
7. **EN** Lina writes “Fever” in the file.
   - FR : Lina écrit « Fièvre (Pyrexie) » dans le dossier.
   - AR : يكتب Lina «Fièvre (Pyrexie)» في الملف.

### Expressions ciblées
- `gen:fever:v1` — **fever** · FR : Fièvre (Pyrexie) · AR : الحمى
- `gen:hyperthermia:v1` — **hyperthermia** · FR : Hyperthermie · AR : ارتفاع الحرارة
- `gen:vital-signs:v1` — **vital signs** · FR : Signes vitaux · AR : العلامات الحيوية

### Questions (acceptées / modèle / hint)
- **q1.** Does Leila have fever?
  - accepté : yes ; yes she does ; yes she has fever ; she has fever
  - modèle : Yes, she has fever.
- **q2.** Does she have hyperthermia?
  - accepté : no ; no she does not ; no she doesn't ; she does not have hyperthermia ; she doesn't
  - modèle : No, she does not have hyperthermia.
  - hint (si erreur) — FR : Attention : Leila n'a PAS hyperthermie.
- **q3.** Does she have fever or hyperthermia?
  - accepté : fever ; she has fever
  - modèle : She has fever.
- **q4.** What does Leila have?
  - accepté : fever ; she has fever
  - modèle : She has fever.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have vital signs?
  - accepté : yes ; yes he does ; he has vital signs
  - modèle : Yes, he has vital signs.
- **Tt2.** Does he have fever?
  - accepté : no ; no he does not ; no he doesn't ; he does not have fever ; he doesn't
  - modèle : No, he does not have fever.
  - hint (si erreur) — FR : Attention : il n'a PAS fièvre (pyrexie).

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … fever. (have)` → has · modèle : She has fever.
- `They … not have hyperthermia. (do)` → do · modèle : They do not have hyperthermia.
- `I … fever. (have)` → have · modèle : I have fever.

### Productions
- **Reformulation :** Rapporte le cas de Leila en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has fever. She does not have hyperthermia.*
- **Transfert (contexte) :** Un autre patient a Signes vitaux. Il n'a pas de Fièvre (Pyrexie).
- Glossaire : Signes vitaux : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has vital signs. He does not have fever.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Signes vitaux et Température corporelle à un patient.
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
4. **EN** “First, we check vital signs.”
   - FR : « D'abord, nous vérifions Signes vitaux. »
   - AR : «أولًا، نتحقق من العلامات الحيوية.»
5. **EN** “Then, we check body temperature.”
   - FR : « Ensuite, nous vérifions Température corporelle. »
   - AR : «ثم نتحقق من حرارة الجسم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:vital-signs:v1` — **vital signs** · FR : Signes vitaux · AR : العلامات الحيوية
- `gen:body-temperature:v1` — **a body temperature** · FR : Température corporelle · AR : حرارة الجسم
- `gen:fever:v1` — **fever** · FR : Fièvre (Pyrexie) · AR : الحمى

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : vital signs ; we check vital signs ; vital signs first ; the doctor checks vital signs first
  - modèle : First, the doctor checks vital signs.
- **q2.** What does the doctor check next?
  - accepté : body temperature ; then we check body temperature ; body temperature next ; the doctor checks body temperature next
  - modèle : Then, the doctor checks body temperature.
- **q3.** Does the doctor check body temperature before vital signs?
  - accepté : no ; no he does not ; no he doesn't ; he checks vital signs first
  - modèle : No, he checks vital signs first, then body temperature.
  - hint (si erreur) — FR : Attention : Signes vitaux vient D'ABORD, ensuite Température corporelle.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : fever ; fever first ; he checks fever first
  - modèle : First, the doctor checks fever.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check vital signs. Then, we check body temperature.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fièvre (Pyrexie), ensuite Signes vitaux, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check fever. Then, we check vital signs.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Fièvre (Pyrexie) / Hyperthermie, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has fever.
   - FR : Le patient du lit 3 a Fièvre (Pyrexie).
   - AR : مريض السرير ٣ عنده الحمى.
4. **EN** The patient in bed 5 has hyperthermia.
   - FR : Le patient du lit 5 a Hyperthermie.
   - AR : مريض السرير ٥ عنده ارتفاع الحرارة.
5. **EN** The patient in bed 5 does not have fever.
   - FR : Le patient du lit 5 n'a pas de Fièvre (Pyrexie).
   - AR : مريض السرير ٥ ليس عنده الحمى.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:fever:v1` — **fever** · FR : Fièvre (Pyrexie) · AR : الحمى
- `gen:hyperthermia:v1` — **hyperthermia** · FR : Hyperthermie · AR : ارتفاع الحرارة
- `gen:vital-signs:v1` — **vital signs** · FR : Signes vitaux · AR : العلامات الحيوية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : fever ; the patient with fever
  - modèle : The patient in bed 3 has fever.
- **q2.** What does the patient in bed 5 have?
  - accepté : hyperthermia ; he has hyperthermia
  - modèle : He has hyperthermia.
- **q3.** Does the patient in bed 5 have fever?
  - accepté : no ; no he does not ; no he doesn't ; he does not have fever ; he doesn't
  - modèle : No, he does not have fever.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS fièvre (pyrexie) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : vital signs ; he has vital signs
  - modèle : He has vital signs.
- **Tt2.** Does the patient in bed 9 have hyperthermia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hyperthermia ; he doesn't
  - modèle : No, he does not have hyperthermia.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS hyperthermie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … fever and hyperthermia. (have)` → have · modèle : The patients have fever and hyperthermia.
- `The patient in bed 5 … not have fever. (do)` → does · modèle : The patient in bed 5 does not have fever.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has fever. The patient in bed 5 has hyperthermia.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Signes vitaux, le lit 9 a Fièvre (Pyrexie) et n'a pas Hyperthermie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has vital signs. The patient in bed 9 has fever.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
