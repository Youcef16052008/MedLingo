# Relecture — Examen Clinique Général (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-000` — Le cas de Sara : Adénopathie / _Sara: Lymphadenopathy_

**Objectif :** Comprendre un cas : adénopathie présent, cachexie absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Sara talks to Lina, a medical student.
   - FR : Sara parle avec Lina, étudiant·e en médecine.
   - AR : Sara يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has lymphadenopathy.
   - FR : Elle a Adénopathie.
   - AR : عنده اعتلال العقد اللمفاوية.
4. **EN** She does not have cachexia.
   - FR : Elle n'a pas de Cachexie.
   - AR : ليس عنده الهُزال.
5. **EN** Lina asks, “Do you have cachexia?”
   - FR : Lina demande : « Avez-vous Cachexie ? »
   - AR : يسأل Lina: «هل عندك الهُزال؟»
6. **EN** Sara says, “No, I do not.”
   - FR : Sara répond : « Non. »
   - AR : يجيب Sara: «لا.»
7. **EN** Lina writes “Lymphadenopathy” in the file.
   - FR : Lina écrit « Adénopathie » dans le dossier.
   - AR : يكتب Lina «Adénopathie» في الملف.

### Expressions ciblées
- `gen:lymphadenopathy:v1` — **lymphadenopathy** · FR : Adénopathie · AR : اعتلال العقد اللمفاوية
- `gen:cachexia:v1` — **cachexia** · FR : Cachexie · AR : الهُزال
- `gen:general-appearance:v1` — **a general appearance** · FR : Aspect général · AR : المظهر العام

### Questions (acceptées / modèle / hint)
- **q1.** Does Sara have lymphadenopathy?
  - accepté : yes ; yes she does ; yes she has lymphadenopathy ; she has lymphadenopathy
  - modèle : Yes, she has lymphadenopathy.
- **q2.** Does she have cachexia?
  - accepté : no ; no she does not ; no she doesn't ; she does not have cachexia ; she doesn't
  - modèle : No, she does not have cachexia.
  - hint (si erreur) — FR : Attention : Sara n'a PAS cachexie.
- **q3.** Does she have lymphadenopathy or cachexia?
  - accepté : lymphadenopathy ; she has lymphadenopathy
  - modèle : She has lymphadenopathy.
- **q4.** What does Sara have?
  - accepté : lymphadenopathy ; she has lymphadenopathy
  - modèle : She has lymphadenopathy.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have general appearance?
  - accepté : yes ; yes he does ; he has a general appearance
  - modèle : Yes, he has a general appearance.
- **Tt2.** Does he have lymphadenopathy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have lymphadenopathy ; he doesn't
  - modèle : No, he does not have lymphadenopathy.
  - hint (si erreur) — FR : Attention : il n'a PAS adénopathie.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … lymphadenopathy. (have)` → has · modèle : She has lymphadenopathy.
- `They … not have cachexia. (do)` → do · modèle : They do not have cachexia.
- `I … lymphadenopathy. (have)` → have · modèle : I have lymphadenopathy.

### Productions
- **Reformulation :** Rapporte le cas de Sara en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has lymphadenopathy. She does not have cachexia.*
- **Transfert (contexte) :** Un autre patient a Aspect général. Il n'a pas de Adénopathie.
- Glossaire : Aspect général : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a general appearance. He does not have lymphadenopathy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Aspect général et Indice de masse corporelle (IMC) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check general appearance.”
   - FR : « D'abord, nous vérifions Aspect général. »
   - AR : «أولًا، نتحقق من المظهر العام.»
5. **EN** “Then, we check body mass index.”
   - FR : « Ensuite, nous vérifions Indice de masse corporelle (IMC). »
   - AR : «ثم نتحقق من مؤشر كتلة الجسم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:general-appearance:v1` — **a general appearance** · FR : Aspect général · AR : المظهر العام
- `gen:body-mass-index:v1` — **a body mass index** · FR : Indice de masse corporelle (IMC) · AR : مؤشر كتلة الجسم
- `gen:lymphadenopathy:v1` — **lymphadenopathy** · FR : Adénopathie · AR : اعتلال العقد اللمفاوية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : general appearance ; we check general appearance ; general appearance first ; the doctor checks general appearance first
  - modèle : First, the doctor checks general appearance.
- **q2.** What does the doctor check next?
  - accepté : body mass index ; then we check body mass index ; body mass index next ; the doctor checks body mass index next
  - modèle : Then, the doctor checks body mass index.
- **q3.** Does the doctor check body mass index before general appearance?
  - accepté : no ; no he does not ; no he doesn't ; he checks general appearance first
  - modèle : No, he checks general appearance first, then body mass index.
  - hint (si erreur) — FR : Attention : Aspect général vient D'ABORD, ensuite Indice de masse corporelle (IMC).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : lymphadenopathy ; lymphadenopathy first ; he checks lymphadenopathy first
  - modèle : First, the doctor checks lymphadenopathy.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check general appearance. Then, we check body mass index.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Adénopathie, ensuite Aspect général, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check lymphadenopathy. Then, we check general appearance.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Adénopathie / Cachexie, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has lymphadenopathy.
   - FR : Le patient du lit 3 a Adénopathie.
   - AR : مريض السرير ٣ عنده اعتلال العقد اللمفاوية.
4. **EN** The patient in bed 5 has cachexia.
   - FR : Le patient du lit 5 a Cachexie.
   - AR : مريض السرير ٥ عنده الهُزال.
5. **EN** The patient in bed 5 does not have lymphadenopathy.
   - FR : Le patient du lit 5 n'a pas de Adénopathie.
   - AR : مريض السرير ٥ ليس عنده اعتلال العقد اللمفاوية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:lymphadenopathy:v1` — **lymphadenopathy** · FR : Adénopathie · AR : اعتلال العقد اللمفاوية
- `gen:cachexia:v1` — **cachexia** · FR : Cachexie · AR : الهُزال
- `gen:general-appearance:v1` — **a general appearance** · FR : Aspect général · AR : المظهر العام

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : lymphadenopathy ; the patient with lymphadenopathy
  - modèle : The patient in bed 3 has lymphadenopathy.
- **q2.** What does the patient in bed 5 have?
  - accepté : cachexia
  - modèle : He has cachexia.
- **q3.** Does the patient in bed 5 have lymphadenopathy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have lymphadenopathy ; he doesn't
  - modèle : No, he does not have lymphadenopathy.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS adénopathie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : general appearance ; a general appearance
  - modèle : He has a general appearance.
- **Tt2.** Does the patient in bed 9 have cachexia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have cachexia ; he doesn't
  - modèle : No, he does not have cachexia.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS cachexie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … lymphadenopathy and cachexia. (have)` → have · modèle : The patients have lymphadenopathy and cachexia.
- `The patient in bed 5 … not have lymphadenopathy. (do)` → does · modèle : The patient in bed 5 does not have lymphadenopathy.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has lymphadenopathy. The patient in bed 5 has cachexia.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Aspect général, le lit 9 a Adénopathie et n'a pas Cachexie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a general appearance. The patient in bed 9 has lymphadenopathy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
