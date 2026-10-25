# Relecture — Sémiologie Gynécologique (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-007` — Le cas de Farid : Aménorrhée / _Farid: Amenorrhea_

**Objectif :** Comprendre un cas : aménorrhée présent, dysménorrhée absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Farid talks to Amine, a medical student.
   - FR : Farid parle avec Amine, étudiant·e en médecine.
   - AR : Farid يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has amenorrhea.
   - FR : Il a Aménorrhée.
   - AR : عنده انقطاع الطمث.
4. **EN** He does not have dysmenorrhea.
   - FR : Il n'a pas de Dysménorrhée.
   - AR : ليس عنده عُسر الطمث.
5. **EN** Amine asks, “Do you have dysmenorrhea?”
   - FR : Amine demande : « Avez-vous Dysménorrhée ? »
   - AR : يسأل Amine: «هل عندك عُسر الطمث؟»
6. **EN** Farid says, “No, I do not.”
   - FR : Farid répond : « Non. »
   - AR : يجيب Farid: «لا.»
7. **EN** Amine writes “Amenorrhea” in the file.
   - FR : Amine écrit « Aménorrhée » dans le dossier.
   - AR : يكتب Amine «Aménorrhée» في الملف.

### Expressions ciblées
- `gen:amenorrhea:v1` — **amenorrhea** · FR : Aménorrhée · AR : انقطاع الطمث
- `gen:dysmenorrhea:v1` — **dysmenorrhea** · FR : Dysménorrhée · AR : عُسر الطمث
- `gen:metrorrhagia:v1` — **a metrorrhagia** · FR : Métrorragie · AR : نزيف الرحم غير الدوري

### Questions (acceptées / modèle / hint)
- **q1.** Does Farid have amenorrhea?
  - accepté : yes ; yes he does ; yes he has amenorrhea ; he has amenorrhea
  - modèle : Yes, he has amenorrhea.
- **q2.** Does he have dysmenorrhea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have dysmenorrhea ; he doesn't
  - modèle : No, he does not have dysmenorrhea.
  - hint (si erreur) — FR : Attention : Farid n'a PAS dysménorrhée.
- **q3.** Does he have amenorrhea or dysmenorrhea?
  - accepté : amenorrhea ; he has amenorrhea
  - modèle : He has amenorrhea.
- **q4.** What does Farid have?
  - accepté : amenorrhea ; he has amenorrhea
  - modèle : He has amenorrhea.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have metrorrhagia?
  - accepté : yes ; yes he does ; he has a metrorrhagia
  - modèle : Yes, he has a metrorrhagia.
- **Tt2.** Does he have amenorrhea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have amenorrhea ; he doesn't
  - modèle : No, he does not have amenorrhea.
  - hint (si erreur) — FR : Attention : il n'a PAS aménorrhée.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … amenorrhea. (have)` → has · modèle : He has amenorrhea.
- `They … not have dysmenorrhea. (do)` → do · modèle : They do not have dysmenorrhea.
- `I … amenorrhea. (have)` → have · modèle : I have amenorrhea.

### Productions
- **Reformulation :** Rapporte le cas de Farid en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has amenorrhea. He does not have dysmenorrhea.*
- **Transfert (contexte) :** Un autre patient a Métrorragie. Il n'a pas de Aménorrhée.
- Glossaire : Métrorragie : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a metrorrhagia. He does not have amenorrhea.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-007` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Aménorrhée et Dysménorrhée à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check amenorrhea.”
   - FR : « D'abord, nous vérifions Aménorrhée. »
   - AR : «أولًا، نتحقق من انقطاع الطمث.»
5. **EN** “Then, we check dysmenorrhea.”
   - FR : « Ensuite, nous vérifions Dysménorrhée. »
   - AR : «ثم نتحقق من عُسر الطمث.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:amenorrhea:v1` — **amenorrhea** · FR : Aménorrhée · AR : انقطاع الطمث
- `gen:dysmenorrhea:v1` — **dysmenorrhea** · FR : Dysménorrhée · AR : عُسر الطمث
- `gen:metrorrhagia:v1` — **a metrorrhagia** · FR : Métrorragie · AR : نزيف الرحم غير الدوري

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : amenorrhea ; we check amenorrhea ; amenorrhea first ; the doctor checks amenorrhea first
  - modèle : First, the doctor checks amenorrhea.
- **q2.** What does the doctor check next?
  - accepté : dysmenorrhea ; then we check dysmenorrhea ; dysmenorrhea next ; the doctor checks dysmenorrhea next
  - modèle : Then, the doctor checks dysmenorrhea.
- **q3.** Does the doctor check dysmenorrhea before amenorrhea?
  - accepté : no ; no he does not ; no he doesn't ; he checks amenorrhea first
  - modèle : No, he checks amenorrhea first, then dysmenorrhea.
  - hint (si erreur) — FR : Attention : Aménorrhée vient D'ABORD, ensuite Dysménorrhée.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : metrorrhagia ; metrorrhagia first ; he checks metrorrhagia first
  - modèle : First, the doctor checks metrorrhagia.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check amenorrhea. Then, we check dysmenorrhea.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Métrorragie, ensuite Aménorrhée, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check metrorrhagia. Then, we check amenorrhea.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-007` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Aménorrhée / Dysménorrhée, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has amenorrhea.
   - FR : Le patient du lit 3 a Aménorrhée.
   - AR : مريض السرير ٣ عنده انقطاع الطمث.
4. **EN** The patient in bed 5 has dysmenorrhea.
   - FR : Le patient du lit 5 a Dysménorrhée.
   - AR : مريض السرير ٥ عنده عُسر الطمث.
5. **EN** The patient in bed 5 does not have amenorrhea.
   - FR : Le patient du lit 5 n'a pas de Aménorrhée.
   - AR : مريض السرير ٥ ليس عنده انقطاع الطمث.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:amenorrhea:v1` — **amenorrhea** · FR : Aménorrhée · AR : انقطاع الطمث
- `gen:dysmenorrhea:v1` — **dysmenorrhea** · FR : Dysménorrhée · AR : عُسر الطمث
- `gen:metrorrhagia:v1` — **a metrorrhagia** · FR : Métrorragie · AR : نزيف الرحم غير الدوري

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : amenorrhea ; the patient with amenorrhea
  - modèle : The patient in bed 3 has amenorrhea.
- **q2.** What does the patient in bed 5 have?
  - accepté : dysmenorrhea ; he has dysmenorrhea
  - modèle : He has dysmenorrhea.
- **q3.** Does the patient in bed 5 have amenorrhea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have amenorrhea ; he doesn't
  - modèle : No, he does not have amenorrhea.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS aménorrhée — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : metrorrhagia ; a metrorrhagia ; he has a metrorrhagia
  - modèle : He has a metrorrhagia.
- **Tt2.** Does the patient in bed 9 have dysmenorrhea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have dysmenorrhea ; he doesn't
  - modèle : No, he does not have dysmenorrhea.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS dysménorrhée.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … amenorrhea and dysmenorrhea. (have)` → have · modèle : The patients have amenorrhea and dysmenorrhea.
- `The patient in bed 5 … not have amenorrhea. (do)` → does · modèle : The patient in bed 5 does not have amenorrhea.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has amenorrhea. The patient in bed 5 has dysmenorrhea.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Métrorragie, le lit 9 a Aménorrhée et n'a pas Dysménorrhée.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a metrorrhagia. The patient in bed 9 has amenorrhea.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
