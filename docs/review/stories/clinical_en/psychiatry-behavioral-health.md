# Relecture — Psychiatry & Behavioral Health (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-031` — Le cas de Farid : Hallucination / _Farid: Hallucination_

**Objectif :** Comprendre un cas : hallucination présent, délire absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Farid talks to Amine, a medical student.
   - FR : Farid parle avec Amine, étudiant·e en médecine.
   - AR : Farid يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has hallucination.
   - FR : Il a Hallucination.
   - AR : عنده الهلوسة.
4. **EN** He does not have delusion.
   - FR : Il n'a pas de Délire.
   - AR : ليس عنده الوهم / الضلال.
5. **EN** Amine asks, “Do you have delusion?”
   - FR : Amine demande : « Avez-vous Délire ? »
   - AR : يسأل Amine: «هل عندك الوهم / الضلال؟»
6. **EN** Farid says, “No, I do not.”
   - FR : Farid répond : « Non. »
   - AR : يجيب Farid: «لا.»
7. **EN** Amine writes “Hallucination” in the file.
   - FR : Amine écrit « Hallucination » dans le dossier.
   - AR : يكتب Amine «Hallucination» في الملف.

### Expressions ciblées
- `gen:hallucination:v1` — **hallucination** · FR : Hallucination · AR : الهلوسة
- `gen:delusion:v1` — **delusion** · FR : Délire · AR : الوهم / الضلال
- `gen:schizophrenia:v1` — **a schizophrenia** · FR : Schizophrénie · AR : الفصام

### Questions (acceptées / modèle / hint)
- **q1.** Does Farid have hallucination?
  - accepté : yes ; yes he does ; yes he has hallucination ; he has hallucination
  - modèle : Yes, he has hallucination.
- **q2.** Does he have delusion?
  - accepté : no ; no he does not ; no he doesn't ; he does not have delusion ; he doesn't
  - modèle : No, he does not have delusion.
  - hint (si erreur) — FR : Attention : Farid n'a PAS délire.
- **q3.** Does he have hallucination or delusion?
  - accepté : hallucination ; he has hallucination
  - modèle : He has hallucination.
- **q4.** What does Farid have?
  - accepté : hallucination ; he has hallucination
  - modèle : He has hallucination.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have schizophrenia?
  - accepté : yes ; yes he does ; he has a schizophrenia
  - modèle : Yes, he has a schizophrenia.
- **Tt2.** Does he have hallucination?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hallucination ; he doesn't
  - modèle : No, he does not have hallucination.
  - hint (si erreur) — FR : Attention : il n'a PAS hallucination.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … hallucination. (have)` → has · modèle : He has hallucination.
- `They … not have delusion. (do)` → do · modèle : They do not have delusion.
- `I … hallucination. (have)` → have · modèle : I have hallucination.

### Productions
- **Reformulation :** Rapporte le cas de Farid en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has hallucination. He does not have delusion.*
- **Transfert (contexte) :** Un autre patient a Schizophrénie. Il n'a pas de Hallucination.
- Glossaire : Schizophrénie : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a schizophrenia. He does not have hallucination.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-031` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Schizophrénie et Trouble bipolaire à un patient.
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
4. **EN** “First, we check schizophrenia.”
   - FR : « D'abord, nous vérifions Schizophrénie. »
   - AR : «أولًا، نتحقق من الفصام.»
5. **EN** “Then, we check bipolar disorder.”
   - FR : « Ensuite, nous vérifions Trouble bipolaire. »
   - AR : «ثم نتحقق من الاضطراب ثنائي القطب.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:schizophrenia:v1` — **a schizophrenia** · FR : Schizophrénie · AR : الفصام
- `gen:bipolar-disorder:v1` — **a bipolar disorder** · FR : Trouble bipolaire · AR : الاضطراب ثنائي القطب
- `gen:hallucination:v1` — **hallucination** · FR : Hallucination · AR : الهلوسة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : schizophrenia ; we check schizophrenia ; schizophrenia first ; the doctor checks schizophrenia first
  - modèle : First, the doctor checks schizophrenia.
- **q2.** What does the doctor check next?
  - accepté : bipolar disorder ; then we check bipolar disorder ; bipolar disorder next ; the doctor checks bipolar disorder next
  - modèle : Then, the doctor checks bipolar disorder.
- **q3.** Does the doctor check bipolar disorder before schizophrenia?
  - accepté : no ; no he does not ; no he doesn't ; he checks schizophrenia first
  - modèle : No, he checks schizophrenia first, then bipolar disorder.
  - hint (si erreur) — FR : Attention : Schizophrénie vient D'ABORD, ensuite Trouble bipolaire.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : hallucination ; hallucination first ; he checks hallucination first
  - modèle : First, the doctor checks hallucination.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check schizophrenia. Then, we check bipolar disorder.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Hallucination, ensuite Schizophrénie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check hallucination. Then, we check schizophrenia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-031` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Hallucination / Délire, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has hallucination.
   - FR : Le patient du lit 3 a Hallucination.
   - AR : مريض السرير ٣ عنده الهلوسة.
4. **EN** The patient in bed 5 has delusion.
   - FR : Le patient du lit 5 a Délire.
   - AR : مريض السرير ٥ عنده الوهم / الضلال.
5. **EN** The patient in bed 5 does not have hallucination.
   - FR : Le patient du lit 5 n'a pas de Hallucination.
   - AR : مريض السرير ٥ ليس عنده الهلوسة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:hallucination:v1` — **hallucination** · FR : Hallucination · AR : الهلوسة
- `gen:delusion:v1` — **delusion** · FR : Délire · AR : الوهم / الضلال
- `gen:schizophrenia:v1` — **a schizophrenia** · FR : Schizophrénie · AR : الفصام

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hallucination ; the patient with hallucination
  - modèle : The patient in bed 3 has hallucination.
- **q2.** What does the patient in bed 5 have?
  - accepté : delusion ; he has delusion
  - modèle : He has delusion.
- **q3.** Does the patient in bed 5 have hallucination?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hallucination ; he doesn't
  - modèle : No, he does not have hallucination.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS hallucination — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : schizophrenia ; a schizophrenia ; he has a schizophrenia
  - modèle : He has a schizophrenia.
- **Tt2.** Does the patient in bed 9 have delusion?
  - accepté : no ; no he does not ; no he doesn't ; he does not have delusion ; he doesn't
  - modèle : No, he does not have delusion.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS délire.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … hallucination and delusion. (have)` → have · modèle : The patients have hallucination and delusion.
- `The patient in bed 5 … not have hallucination. (do)` → does · modèle : The patient in bed 5 does not have hallucination.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has hallucination. The patient in bed 5 has delusion.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Schizophrénie, le lit 9 a Hallucination et n'a pas Délire.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a schizophrenia. The patient in bed 9 has hallucination.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
