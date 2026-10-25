# Relecture — Neurology & The Brain (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-023` — Le cas de Bilal : Méningite / _Bilal: Meningitis_

**Objectif :** Comprendre un cas : méningite présent, neuropathie absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Bilal talks to Amine, a medical student.
   - FR : Bilal parle avec Amine, étudiant·e en médecine.
   - AR : Bilal يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has meningitis.
   - FR : Il a Méningite.
   - AR : عنده التهاب السحايا.
4. **EN** He does not have neuropathy.
   - FR : Il n'a pas de Neuropathie.
   - AR : ليس عنده اعتلال الأعصاب.
5. **EN** Amine asks, “Do you have neuropathy?”
   - FR : Amine demande : « Avez-vous Neuropathie ? »
   - AR : يسأل Amine: «هل عندك اعتلال الأعصاب؟»
6. **EN** Bilal says, “No, I do not.”
   - FR : Bilal répond : « Non. »
   - AR : يجيب Bilal: «لا.»
7. **EN** Amine writes “Meningitis” in the file.
   - FR : Amine écrit « Méningite » dans le dossier.
   - AR : يكتب Amine «Méningite» في الملف.

### Expressions ciblées
- `gen:meningitis:v1` — **meningitis** · FR : Méningite · AR : التهاب السحايا
- `gen:neuropathy:v1` — **neuropathy** · FR : Neuropathie · AR : اعتلال الأعصاب
- `gen:concussion:v1` — **a concussion** · FR : Commotion cérébrale · AR : ارتجاج الدماغ

### Questions (acceptées / modèle / hint)
- **q1.** Does Bilal have meningitis?
  - accepté : yes ; yes he does ; yes he has meningitis ; he has meningitis
  - modèle : Yes, he has meningitis.
- **q2.** Does he have neuropathy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have neuropathy ; he doesn't
  - modèle : No, he does not have neuropathy.
  - hint (si erreur) — FR : Attention : Bilal n'a PAS neuropathie.
- **q3.** Does he have meningitis or neuropathy?
  - accepté : meningitis ; he has meningitis
  - modèle : He has meningitis.
- **q4.** What does Bilal have?
  - accepté : meningitis ; he has meningitis
  - modèle : He has meningitis.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have concussion?
  - accepté : yes ; yes he does ; he has a concussion
  - modèle : Yes, he has a concussion.
- **Tt2.** Does he have meningitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have meningitis ; he doesn't
  - modèle : No, he does not have meningitis.
  - hint (si erreur) — FR : Attention : il n'a PAS méningite.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … meningitis. (have)` → has · modèle : He has meningitis.
- `They … not have neuropathy. (do)` → do · modèle : They do not have neuropathy.
- `I … meningitis. (have)` → have · modèle : I have meningitis.

### Productions
- **Reformulation :** Rapporte le cas de Bilal en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has meningitis. He does not have neuropathy.*
- **Transfert (contexte) :** Un autre patient a Commotion cérébrale. Il n'a pas de Méningite.
- Glossaire : Commotion cérébrale : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a concussion. He does not have meningitis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-023` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Commotion cérébrale et Amnésie à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check concussion.”
   - FR : « D'abord, nous vérifions Commotion cérébrale. »
   - AR : «أولًا، نتحقق من ارتجاج الدماغ.»
5. **EN** “Then, we check amnesia.”
   - FR : « Ensuite, nous vérifions Amnésie. »
   - AR : «ثم نتحقق من فقدان الذاكرة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:concussion:v1` — **a concussion** · FR : Commotion cérébrale · AR : ارتجاج الدماغ
- `gen:amnesia:v1` — **an amnesia** · FR : Amnésie · AR : فقدان الذاكرة
- `gen:dementia:v1` — **a dementia** · FR : Démence · AR : الخرف

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : concussion ; we check concussion ; concussion first ; the doctor checks concussion first
  - modèle : First, the doctor checks concussion.
- **q2.** What does the doctor check next?
  - accepté : amnesia ; then we check amnesia ; amnesia next ; the doctor checks amnesia next
  - modèle : Then, the doctor checks amnesia.
- **q3.** Does the doctor check amnesia before concussion?
  - accepté : no ; no he does not ; no he doesn't ; he checks concussion first
  - modèle : No, he checks concussion first, then amnesia.
  - hint (si erreur) — FR : Attention : Commotion cérébrale vient D'ABORD, ensuite Amnésie.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : dementia ; dementia first ; he checks dementia first
  - modèle : First, the doctor checks dementia.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check concussion. Then, we check amnesia.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Démence, ensuite Commotion cérébrale, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check dementia. Then, we check concussion.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-023` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Méningite / Neuropathie, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has meningitis.
   - FR : Le patient du lit 3 a Méningite.
   - AR : مريض السرير ٣ عنده التهاب السحايا.
4. **EN** The patient in bed 5 has neuropathy.
   - FR : Le patient du lit 5 a Neuropathie.
   - AR : مريض السرير ٥ عنده اعتلال الأعصاب.
5. **EN** The patient in bed 5 does not have meningitis.
   - FR : Le patient du lit 5 n'a pas de Méningite.
   - AR : مريض السرير ٥ ليس عنده التهاب السحايا.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:meningitis:v1` — **meningitis** · FR : Méningite · AR : التهاب السحايا
- `gen:neuropathy:v1` — **neuropathy** · FR : Neuropathie · AR : اعتلال الأعصاب
- `gen:concussion:v1` — **a concussion** · FR : Commotion cérébrale · AR : ارتجاج الدماغ

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : meningitis ; the patient with meningitis
  - modèle : The patient in bed 3 has meningitis.
- **q2.** What does the patient in bed 5 have?
  - accepté : neuropathy
  - modèle : He has neuropathy.
- **q3.** Does the patient in bed 5 have meningitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have meningitis ; he doesn't
  - modèle : No, he does not have meningitis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS méningite — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : concussion ; a concussion
  - modèle : He has a concussion.
- **Tt2.** Does the patient in bed 9 have neuropathy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have neuropathy ; he doesn't
  - modèle : No, he does not have neuropathy.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS neuropathie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … meningitis and neuropathy. (have)` → have · modèle : The patients have meningitis and neuropathy.
- `The patient in bed 5 … not have meningitis. (do)` → does · modèle : The patient in bed 5 does not have meningitis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has meningitis. The patient in bed 5 has neuropathy.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Commotion cérébrale, le lit 9 a Méningite et n'a pas Neuropathie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a concussion. The patient in bed 9 has meningitis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
