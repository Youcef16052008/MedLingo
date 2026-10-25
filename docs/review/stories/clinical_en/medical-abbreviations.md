# Relecture — Medical Abbreviations (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-019` — Le cas de Farid : Dyspnée / _Farid: Shortness of Breath_

**Objectif :** Comprendre un cas : dyspnée présent, n/v — nausée et vomissements absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Farid talks to Amine, a medical student.
   - FR : Farid parle avec Amine, étudiant·e en médecine.
   - AR : Farid يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has shortness of breath.
   - FR : Il a Dyspnée.
   - AR : عنده ضيق التنفس.
4. **EN** He does not have nausea and vomiting.
   - FR : Il n'a pas de N/V — Nausée et Vomissements.
   - AR : ليس عنده غثيان وقيء.
5. **EN** Amine asks, “Do you have nausea and vomiting?”
   - FR : Amine demande : « Avez-vous N/V — Nausée et Vomissements ? »
   - AR : يسأل Amine: «هل عندك غثيان وقيء؟»
6. **EN** Farid says, “No, I do not.”
   - FR : Farid répond : « Non. »
   - AR : يجيب Farid: «لا.»
7. **EN** Amine writes “Shortness of Breath” in the file.
   - FR : Amine écrit « Dyspnée » dans le dossier.
   - AR : يكتب Amine «Dyspnée» في الملف.

### Expressions ciblées
- `gen:shortness-of-breath:v1` — **shortness of breath** · FR : Dyspnée · AR : ضيق التنفس
- `gen:nausea-and-vomiting:v1` — **nausea and vomiting** · FR : N/V — Nausée et Vomissements · AR : غثيان وقيء
- `gen:blood-pressure:v1` — **a blood pressure** · FR : TA — Tension Artérielle · AR : ضغط الدم

### Questions (acceptées / modèle / hint)
- **q1.** Does Farid have shortness of breath?
  - accepté : yes ; yes he does ; yes he has shortness of breath ; he has shortness of breath
  - modèle : Yes, he has shortness of breath.
- **q2.** Does he have nausea and vomiting?
  - accepté : no ; no he does not ; no he doesn't ; he does not have nausea and vomiting ; he doesn't
  - modèle : No, he does not have nausea and vomiting.
  - hint (si erreur) — FR : Attention : Farid n'a PAS n/v — nausée et vomissements.
- **q3.** Does he have shortness of breath or nausea and vomiting?
  - accepté : shortness of breath ; he has shortness of breath
  - modèle : He has shortness of breath.
- **q4.** What does Farid have?
  - accepté : shortness of breath ; he has shortness of breath
  - modèle : He has shortness of breath.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have blood pressure?
  - accepté : yes ; yes he does ; he has a blood pressure
  - modèle : Yes, he has a blood pressure.
- **Tt2.** Does he have shortness of breath?
  - accepté : no ; no he does not ; no he doesn't ; he does not have shortness of breath ; he doesn't
  - modèle : No, he does not have shortness of breath.
  - hint (si erreur) — FR : Attention : il n'a PAS dyspnée.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … shortness of breath. (have)` → has · modèle : He has shortness of breath.
- `They … not have nausea and vomiting. (do)` → do · modèle : They do not have nausea and vomiting.
- `I … shortness of breath. (have)` → have · modèle : I have shortness of breath.

### Productions
- **Reformulation :** Rapporte le cas de Farid en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has shortness of breath. He does not have nausea and vomiting.*
- **Transfert (contexte) :** Un autre patient a TA — Tension Artérielle. Il n'a pas de Dyspnée.
- Glossaire : TA — Tension Artérielle : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a blood pressure. He does not have shortness of breath.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-019` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique TA — Tension Artérielle et FC — Fréquence Cardiaque à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient.
   - FR : Sara est un·e patient·e.
   - AR : Sara مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check blood pressure.”
   - FR : « D'abord, nous vérifions TA — Tension Artérielle. »
   - AR : «أولًا، نتحقق من ضغط الدم.»
5. **EN** “Then, we check heart rate.”
   - FR : « Ensuite, nous vérifions FC — Fréquence Cardiaque. »
   - AR : «ثم نتحقق من معدل ضربات القلب.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:blood-pressure:v1` — **a blood pressure** · FR : TA — Tension Artérielle · AR : ضغط الدم
- `gen:heart-rate:v1` — **a heart rate** · FR : FC — Fréquence Cardiaque · AR : معدل ضربات القلب
- `gen:respiratory-rate:v1` — **a respiratory rate** · FR : FR — Fréquence Respiratoire · AR : معدل التنفس

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : blood pressure ; we check blood pressure ; blood pressure first ; the doctor checks blood pressure first
  - modèle : First, the doctor checks blood pressure.
- **q2.** What does the doctor check next?
  - accepté : heart rate ; then we check heart rate ; heart rate next ; the doctor checks heart rate next
  - modèle : Then, the doctor checks heart rate.
- **q3.** Does the doctor check heart rate before blood pressure?
  - accepté : no ; no he does not ; no he doesn't ; he checks blood pressure first
  - modèle : No, he checks blood pressure first, then heart rate.
  - hint (si erreur) — FR : Attention : TA — Tension Artérielle vient D'ABORD, ensuite FC — Fréquence Cardiaque.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : respiratory rate ; respiratory rate first ; he checks respiratory rate first
  - modèle : First, the doctor checks respiratory rate.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check blood pressure. Then, we check heart rate.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord FR — Fréquence Respiratoire, ensuite TA — Tension Artérielle, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check respiratory rate. Then, we check blood pressure.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-019` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Dyspnée / N/V — Nausée et Vomissements, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has shortness of breath.
   - FR : Le patient du lit 3 a Dyspnée.
   - AR : مريض السرير ٣ عنده ضيق التنفس.
4. **EN** The patient in bed 5 has nausea and vomiting.
   - FR : Le patient du lit 5 a N/V — Nausée et Vomissements.
   - AR : مريض السرير ٥ عنده غثيان وقيء.
5. **EN** The patient in bed 5 does not have shortness of breath.
   - FR : Le patient du lit 5 n'a pas de Dyspnée.
   - AR : مريض السرير ٥ ليس عنده ضيق التنفس.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:shortness-of-breath:v1` — **shortness of breath** · FR : Dyspnée · AR : ضيق التنفس
- `gen:nausea-and-vomiting:v1` — **nausea and vomiting** · FR : N/V — Nausée et Vomissements · AR : غثيان وقيء
- `gen:blood-pressure:v1` — **a blood pressure** · FR : TA — Tension Artérielle · AR : ضغط الدم

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : shortness of breath ; the patient with shortness of breath
  - modèle : The patient in bed 3 has shortness of breath.
- **q2.** What does the patient in bed 5 have?
  - accepté : nausea and vomiting ; he has nausea and vomiting
  - modèle : He has nausea and vomiting.
- **q3.** Does the patient in bed 5 have shortness of breath?
  - accepté : no ; no he does not ; no he doesn't ; he does not have shortness of breath ; he doesn't
  - modèle : No, he does not have shortness of breath.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS dyspnée — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : blood pressure ; a blood pressure ; he has a blood pressure
  - modèle : He has a blood pressure.
- **Tt2.** Does the patient in bed 9 have nausea and vomiting?
  - accepté : no ; no he does not ; no he doesn't ; he does not have nausea and vomiting ; he doesn't
  - modèle : No, he does not have nausea and vomiting.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS n/v — nausée et vomissements.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … shortness of breath and nausea and vomiting. (have)` → have · modèle : The patients have shortness of breath and nausea and vomiting.
- `The patient in bed 5 … not have shortness of breath. (do)` → does · modèle : The patient in bed 5 does not have shortness of breath.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has shortness of breath. The patient in bed 5 has nausea and vomiting.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a TA — Tension Artérielle, le lit 9 a Dyspnée et n'a pas N/V — Nausée et Vomissements.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a blood pressure. The patient in bed 9 has shortness of breath.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
