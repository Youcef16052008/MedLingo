# Relecture — Sémiologie Cardiovasculaire (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-003` — Le cas de Karim : Douleur thoracique (Précordialgie) / _Karim: Chest pain_

**Objectif :** Comprendre un cas : douleur thoracique (précordialgie) présent, dyspnée absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient at the clinic.
   - FR : Karim est un·e patient·e à la clinique.
   - AR : Karim مريض/مريضة في العيادة.
2. **EN** Karim talks to Amine, a medical student.
   - FR : Karim parle avec Amine, étudiant·e en médecine.
   - AR : Karim يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has chest pain.
   - FR : Il a Douleur thoracique (Précordialgie).
   - AR : عنده ألم الصدر.
4. **EN** He does not have dyspnea.
   - FR : Il n'a pas de Dyspnée.
   - AR : ليس عنده ضيق التنفس.
5. **EN** Amine asks, “Do you have dyspnea?”
   - FR : Amine demande : « Avez-vous Dyspnée ? »
   - AR : يسأل Amine: «هل عندك ضيق التنفس؟»
6. **EN** Karim says, “No, I do not.”
   - FR : Karim répond : « Non. »
   - AR : يجيب Karim: «لا.»
7. **EN** Amine writes “Chest pain” in the file.
   - FR : Amine écrit « Douleur thoracique (Précordialgie) » dans le dossier.
   - AR : يكتب Amine «Douleur thoracique (Précordialgie)» في الملف.

### Expressions ciblées
- `gen:chest-pain:v1` — **chest pain** · FR : Douleur thoracique (Précordialgie) · AR : ألم الصدر
- `gen:dyspnea:v1` — **dyspnea** · FR : Dyspnée · AR : ضيق التنفس
- `gen:orthopnea:v1` — **orthopnea** · FR : Orthopnée · AR : التنفس الانتصابي

### Questions (acceptées / modèle / hint)
- **q1.** Does Karim have chest pain?
  - accepté : yes ; yes he does ; yes he has chest pain ; he has chest pain
  - modèle : Yes, he has chest pain.
- **q2.** Does he have dyspnea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have dyspnea ; he doesn't
  - modèle : No, he does not have dyspnea.
  - hint (si erreur) — FR : Attention : Karim n'a PAS dyspnée.
- **q3.** Does he have chest pain or dyspnea?
  - accepté : chest pain ; he has chest pain
  - modèle : He has chest pain.
- **q4.** What does Karim have?
  - accepté : chest pain ; he has chest pain
  - modèle : He has chest pain.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have orthopnea?
  - accepté : yes ; yes he does ; he has orthopnea
  - modèle : Yes, he has orthopnea.
- **Tt2.** Does he have chest pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have chest pain ; he doesn't
  - modèle : No, he does not have chest pain.
  - hint (si erreur) — FR : Attention : il n'a PAS douleur thoracique (précordialgie).

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … chest pain. (have)` → has · modèle : He has chest pain.
- `They … not have dyspnea. (do)` → do · modèle : They do not have dyspnea.
- `I … chest pain. (have)` → have · modèle : I have chest pain.

### Productions
- **Reformulation :** Rapporte le cas de Karim en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has chest pain. He does not have dyspnea.*
- **Transfert (contexte) :** Un autre patient a Orthopnée. Il n'a pas de Douleur thoracique (Précordialgie).
- Glossaire : Orthopnée : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has orthopnea. He does not have chest pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Douleur thoracique (Précordialgie) et Dyspnée à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient.
   - FR : Meriem est un·e patient·e.
   - AR : Meriem مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check chest pain.”
   - FR : « D'abord, nous vérifions Douleur thoracique (Précordialgie). »
   - AR : «أولًا، نتحقق من ألم الصدر.»
5. **EN** “Then, we check dyspnea.”
   - FR : « Ensuite, nous vérifions Dyspnée. »
   - AR : «ثم نتحقق من ضيق التنفس.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:chest-pain:v1` — **chest pain** · FR : Douleur thoracique (Précordialgie) · AR : ألم الصدر
- `gen:dyspnea:v1` — **dyspnea** · FR : Dyspnée · AR : ضيق التنفس
- `gen:orthopnea:v1` — **orthopnea** · FR : Orthopnée · AR : التنفس الانتصابي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : chest pain ; we check chest pain ; chest pain first ; the doctor checks chest pain first
  - modèle : First, the doctor checks chest pain.
- **q2.** What does the doctor check next?
  - accepté : dyspnea ; then we check dyspnea ; dyspnea next ; the doctor checks dyspnea next
  - modèle : Then, the doctor checks dyspnea.
- **q3.** Does the doctor check dyspnea before chest pain?
  - accepté : no ; no he does not ; no he doesn't ; he checks chest pain first
  - modèle : No, he checks chest pain first, then dyspnea.
  - hint (si erreur) — FR : Attention : Douleur thoracique (Précordialgie) vient D'ABORD, ensuite Dyspnée.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : orthopnea ; orthopnea first ; he checks orthopnea first
  - modèle : First, the doctor checks orthopnea.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check chest pain. Then, we check dyspnea.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Orthopnée, ensuite Douleur thoracique (Précordialgie), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check orthopnea. Then, we check chest pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Douleur thoracique (Précordialgie) / Dyspnée, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has chest pain.
   - FR : Le patient du lit 3 a Douleur thoracique (Précordialgie).
   - AR : مريض السرير ٣ عنده ألم الصدر.
4. **EN** The patient in bed 5 has dyspnea.
   - FR : Le patient du lit 5 a Dyspnée.
   - AR : مريض السرير ٥ عنده ضيق التنفس.
5. **EN** The patient in bed 5 does not have chest pain.
   - FR : Le patient du lit 5 n'a pas de Douleur thoracique (Précordialgie).
   - AR : مريض السرير ٥ ليس عنده ألم الصدر.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:chest-pain:v1` — **chest pain** · FR : Douleur thoracique (Précordialgie) · AR : ألم الصدر
- `gen:dyspnea:v1` — **dyspnea** · FR : Dyspnée · AR : ضيق التنفس
- `gen:orthopnea:v1` — **orthopnea** · FR : Orthopnée · AR : التنفس الانتصابي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : chest pain ; the patient with chest pain
  - modèle : The patient in bed 3 has chest pain.
- **q2.** What does the patient in bed 5 have?
  - accepté : dyspnea
  - modèle : He has dyspnea.
- **q3.** Does the patient in bed 5 have chest pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have chest pain ; he doesn't
  - modèle : No, he does not have chest pain.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS douleur thoracique (précordialgie) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : orthopnea
  - modèle : He has orthopnea.
- **Tt2.** Does the patient in bed 9 have dyspnea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have dyspnea ; he doesn't
  - modèle : No, he does not have dyspnea.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS dyspnée.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … chest pain and dyspnea. (have)` → have · modèle : The patients have chest pain and dyspnea.
- `The patient in bed 5 … not have chest pain. (do)` → does · modèle : The patient in bed 5 does not have chest pain.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has chest pain. The patient in bed 5 has dyspnea.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Orthopnée, le lit 9 a Douleur thoracique (Précordialgie) et n'a pas Dyspnée.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has orthopnea. The patient in bed 9 has chest pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
