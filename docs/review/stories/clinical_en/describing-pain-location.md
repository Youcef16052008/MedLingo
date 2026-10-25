# Relecture — Describing Pain & Location (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-007` — Le cas de Farid : Douleur aiguë / _Farid: Sharp pain_

**Objectif :** Comprendre un cas : douleur aiguë présent, douleur brûlante absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Farid talks to Amine, a medical student.
   - FR : Farid parle avec Amine, étudiant·e en médecine.
   - AR : Farid يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has sharp pain.
   - FR : Il a Douleur aiguë.
   - AR : عنده ألم حاد.
4. **EN** He does not have burning pain.
   - FR : Il n'a pas de Douleur brûlante.
   - AR : ليس عنده ألم حارق.
5. **EN** Amine asks, “Do you have burning pain?”
   - FR : Amine demande : « Avez-vous Douleur brûlante ? »
   - AR : يسأل Amine: «هل عندك ألم حارق؟»
6. **EN** Farid says, “No, I do not.”
   - FR : Farid répond : « Non. »
   - AR : يجيب Farid: «لا.»
7. **EN** Amine writes “Sharp pain” in the file.
   - FR : Amine écrit « Douleur aiguë » dans le dossier.
   - AR : يكتب Amine «Douleur aiguë» في الملف.

### Expressions ciblées
- `gen:sharp-pain:v1` — **sharp pain** · FR : Douleur aiguë · AR : ألم حاد
- `gen:burning-pain:v1` — **burning pain** · FR : Douleur brûlante · AR : ألم حارق
- `gen:throbbing-pain:v1` — **throbbing pain** · FR : Douleur pulsatile · AR : ألم نابض

### Questions (acceptées / modèle / hint)
- **q1.** Does Farid have sharp pain?
  - accepté : yes ; yes he does ; yes he has sharp pain ; he has sharp pain
  - modèle : Yes, he has sharp pain.
- **q2.** Does he have burning pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have burning pain ; he doesn't
  - modèle : No, he does not have burning pain.
  - hint (si erreur) — FR : Attention : Farid n'a PAS douleur brûlante.
- **q3.** Does he have sharp pain or burning pain?
  - accepté : sharp pain ; he has sharp pain
  - modèle : He has sharp pain.
- **q4.** What does Farid have?
  - accepté : sharp pain ; he has sharp pain
  - modèle : He has sharp pain.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have throbbing pain?
  - accepté : yes ; yes he does ; he has throbbing pain
  - modèle : Yes, he has throbbing pain.
- **Tt2.** Does he have sharp pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have sharp pain ; he doesn't
  - modèle : No, he does not have sharp pain.
  - hint (si erreur) — FR : Attention : il n'a PAS douleur aiguë.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … sharp pain. (have)` → has · modèle : He has sharp pain.
- `They … not have burning pain. (do)` → do · modèle : They do not have burning pain.
- `I … sharp pain. (have)` → have · modèle : I have sharp pain.

### Productions
- **Reformulation :** Rapporte le cas de Farid en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has sharp pain. He does not have burning pain.*
- **Transfert (contexte) :** Un autre patient a Douleur pulsatile. Il n'a pas de Douleur aiguë.
- Glossaire : Douleur pulsatile : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has throbbing pain. He does not have sharp pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-007` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Douleur aiguë et Douleur brûlante à un patient.
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
4. **EN** “First, we check sharp pain.”
   - FR : « D'abord, nous vérifions Douleur aiguë. »
   - AR : «أولًا، نتحقق من ألم حاد.»
5. **EN** “Then, we check burning pain.”
   - FR : « Ensuite, nous vérifions Douleur brûlante. »
   - AR : «ثم نتحقق من ألم حارق.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:sharp-pain:v1` — **sharp pain** · FR : Douleur aiguë · AR : ألم حاد
- `gen:burning-pain:v1` — **burning pain** · FR : Douleur brûlante · AR : ألم حارق
- `gen:throbbing-pain:v1` — **throbbing pain** · FR : Douleur pulsatile · AR : ألم نابض

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : sharp pain ; we check sharp pain ; sharp pain first ; the doctor checks sharp pain first
  - modèle : First, the doctor checks sharp pain.
- **q2.** What does the doctor check next?
  - accepté : burning pain ; then we check burning pain ; burning pain next ; the doctor checks burning pain next
  - modèle : Then, the doctor checks burning pain.
- **q3.** Does the doctor check burning pain before sharp pain?
  - accepté : no ; no he does not ; no he doesn't ; he checks sharp pain first
  - modèle : No, he checks sharp pain first, then burning pain.
  - hint (si erreur) — FR : Attention : Douleur aiguë vient D'ABORD, ensuite Douleur brûlante.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : throbbing pain ; throbbing pain first ; he checks throbbing pain first
  - modèle : First, the doctor checks throbbing pain.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check sharp pain. Then, we check burning pain.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Douleur pulsatile, ensuite Douleur aiguë, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check throbbing pain. Then, we check sharp pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-007` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Douleur aiguë / Douleur brûlante, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has sharp pain.
   - FR : Le patient du lit 3 a Douleur aiguë.
   - AR : مريض السرير ٣ عنده ألم حاد.
4. **EN** The patient in bed 5 has burning pain.
   - FR : Le patient du lit 5 a Douleur brûlante.
   - AR : مريض السرير ٥ عنده ألم حارق.
5. **EN** The patient in bed 5 does not have sharp pain.
   - FR : Le patient du lit 5 n'a pas de Douleur aiguë.
   - AR : مريض السرير ٥ ليس عنده ألم حاد.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:sharp-pain:v1` — **sharp pain** · FR : Douleur aiguë · AR : ألم حاد
- `gen:burning-pain:v1` — **burning pain** · FR : Douleur brûlante · AR : ألم حارق
- `gen:throbbing-pain:v1` — **throbbing pain** · FR : Douleur pulsatile · AR : ألم نابض

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : sharp pain ; the patient with sharp pain
  - modèle : The patient in bed 3 has sharp pain.
- **q2.** What does the patient in bed 5 have?
  - accepté : burning pain
  - modèle : He has burning pain.
- **q3.** Does the patient in bed 5 have sharp pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have sharp pain ; he doesn't
  - modèle : No, he does not have sharp pain.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS douleur aiguë — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : throbbing pain
  - modèle : He has throbbing pain.
- **Tt2.** Does the patient in bed 9 have burning pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have burning pain ; he doesn't
  - modèle : No, he does not have burning pain.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS douleur brûlante.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … sharp pain and burning pain. (have)` → have · modèle : The patients have sharp pain and burning pain.
- `The patient in bed 5 … not have sharp pain. (do)` → does · modèle : The patient in bed 5 does not have sharp pain.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has sharp pain. The patient in bed 5 has burning pain.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Douleur pulsatile, le lit 9 a Douleur aiguë et n'a pas Douleur brûlante.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has throbbing pain. The patient in bed 9 has sharp pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
