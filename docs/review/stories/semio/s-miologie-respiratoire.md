# Relecture — Sémiologie Respiratoire (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-011` — Le cas de Bilal : Toux / _Bilal: Cough_

**Objectif :** Comprendre un cas : toux présent, hémoptysie absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Bilal talks to Amine, a medical student.
   - FR : Bilal parle avec Amine, étudiant·e en médecine.
   - AR : Bilal يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has cough.
   - FR : Il a Toux.
   - AR : عنده السعال.
4. **EN** He does not have hemoptysis.
   - FR : Il n'a pas de Hémoptysie.
   - AR : ليس عنده نفث الدم.
5. **EN** Amine asks, “Do you have hemoptysis?”
   - FR : Amine demande : « Avez-vous Hémoptysie ? »
   - AR : يسأل Amine: «هل عندك نفث الدم؟»
6. **EN** Bilal says, “No, I do not.”
   - FR : Bilal répond : « Non. »
   - AR : يجيب Bilal: «لا.»
7. **EN** Amine writes “Cough” in the file.
   - FR : Amine écrit « Toux » dans le dossier.
   - AR : يكتب Amine «Toux» في الملف.

### Expressions ciblées
- `gen:cough:v1` — **cough** · FR : Toux · AR : السعال
- `gen:hemoptysis:v1` — **hemoptysis** · FR : Hémoptysie · AR : نفث الدم
- `gen:sputum:v1` — **sputum** · FR : Expectoration (Crachat) · AR : البلغم

### Questions (acceptées / modèle / hint)
- **q1.** Does Bilal have cough?
  - accepté : yes ; yes he does ; yes he has cough ; he has cough
  - modèle : Yes, he has cough.
- **q2.** Does he have hemoptysis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hemoptysis ; he doesn't
  - modèle : No, he does not have hemoptysis.
  - hint (si erreur) — FR : Attention : Bilal n'a PAS hémoptysie.
- **q3.** Does he have cough or hemoptysis?
  - accepté : cough ; he has cough
  - modèle : He has cough.
- **q4.** What does Bilal have?
  - accepté : cough ; he has cough
  - modèle : He has cough.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have sputum?
  - accepté : yes ; yes he does ; he has sputum
  - modèle : Yes, he has sputum.
- **Tt2.** Does he have cough?
  - accepté : no ; no he does not ; no he doesn't ; he does not have cough ; he doesn't
  - modèle : No, he does not have cough.
  - hint (si erreur) — FR : Attention : il n'a PAS toux.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … cough. (have)` → has · modèle : He has cough.
- `They … not have hemoptysis. (do)` → do · modèle : They do not have hemoptysis.
- `I … cough. (have)` → have · modèle : I have cough.

### Productions
- **Reformulation :** Rapporte le cas de Bilal en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has cough. He does not have hemoptysis.*
- **Transfert (contexte) :** Un autre patient a Expectoration (Crachat). Il n'a pas de Toux.
- Glossaire : Expectoration (Crachat) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has sputum. He does not have cough.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-011` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Toux et Hémoptysie à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient.
   - FR : Nadia est un·e patient·e.
   - AR : Nadia مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check cough.”
   - FR : « D'abord, nous vérifions Toux. »
   - AR : «أولًا، نتحقق من السعال.»
5. **EN** “Then, we check hemoptysis.”
   - FR : « Ensuite, nous vérifions Hémoptysie. »
   - AR : «ثم نتحقق من نفث الدم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cough:v1` — **cough** · FR : Toux · AR : السعال
- `gen:hemoptysis:v1` — **hemoptysis** · FR : Hémoptysie · AR : نفث الدم
- `gen:sputum:v1` — **sputum** · FR : Expectoration (Crachat) · AR : البلغم

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cough ; we check cough ; cough first ; the doctor checks cough first
  - modèle : First, the doctor checks cough.
- **q2.** What does the doctor check next?
  - accepté : hemoptysis ; then we check hemoptysis ; hemoptysis next ; the doctor checks hemoptysis next
  - modèle : Then, the doctor checks hemoptysis.
- **q3.** Does the doctor check hemoptysis before cough?
  - accepté : no ; no he does not ; no he doesn't ; he checks cough first
  - modèle : No, he checks cough first, then hemoptysis.
  - hint (si erreur) — FR : Attention : Toux vient D'ABORD, ensuite Hémoptysie.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : sputum ; sputum first ; he checks sputum first
  - modèle : First, the doctor checks sputum.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cough. Then, we check hemoptysis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Expectoration (Crachat), ensuite Toux, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check sputum. Then, we check cough.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-011` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Toux / Hémoptysie, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has cough.
   - FR : Le patient du lit 3 a Toux.
   - AR : مريض السرير ٣ عنده السعال.
4. **EN** The patient in bed 5 has hemoptysis.
   - FR : Le patient du lit 5 a Hémoptysie.
   - AR : مريض السرير ٥ عنده نفث الدم.
5. **EN** The patient in bed 5 does not have cough.
   - FR : Le patient du lit 5 n'a pas de Toux.
   - AR : مريض السرير ٥ ليس عنده السعال.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:cough:v1` — **cough** · FR : Toux · AR : السعال
- `gen:hemoptysis:v1` — **hemoptysis** · FR : Hémoptysie · AR : نفث الدم
- `gen:sputum:v1` — **sputum** · FR : Expectoration (Crachat) · AR : البلغم

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cough ; the patient with cough
  - modèle : The patient in bed 3 has cough.
- **q2.** What does the patient in bed 5 have?
  - accepté : hemoptysis
  - modèle : He has hemoptysis.
- **q3.** Does the patient in bed 5 have cough?
  - accepté : no ; no he does not ; no he doesn't ; he does not have cough ; he doesn't
  - modèle : No, he does not have cough.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS toux — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : sputum
  - modèle : He has sputum.
- **Tt2.** Does the patient in bed 9 have hemoptysis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hemoptysis ; he doesn't
  - modèle : No, he does not have hemoptysis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS hémoptysie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … cough and hemoptysis. (have)` → have · modèle : The patients have cough and hemoptysis.
- `The patient in bed 5 … not have cough. (do)` → does · modèle : The patient in bed 5 does not have cough.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has cough. The patient in bed 5 has hemoptysis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Expectoration (Crachat), le lit 9 a Toux et n'a pas Hémoptysie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has sputum. The patient in bed 9 has cough.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
