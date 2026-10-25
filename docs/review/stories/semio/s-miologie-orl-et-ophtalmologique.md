# Relecture — Sémiologie ORL et Ophtalmologique (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-009` — Le cas de Sofiane : Otalgie / _Sofiane: Otalgia_

**Objectif :** Comprendre un cas : otalgie présent, acouphène absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient at the clinic.
   - FR : Sofiane est un·e patient·e à la clinique.
   - AR : Sofiane مريض/مريضة في العيادة.
2. **EN** Sofiane talks to Amine, a medical student.
   - FR : Sofiane parle avec Amine, étudiant·e en médecine.
   - AR : Sofiane يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has otalgia.
   - FR : Il a Otalgie.
   - AR : عنده ألم الأذن.
4. **EN** He does not have tinnitus.
   - FR : Il n'a pas de Acouphène.
   - AR : ليس عنده الطنين.
5. **EN** Amine asks, “Do you have tinnitus?”
   - FR : Amine demande : « Avez-vous Acouphène ? »
   - AR : يسأل Amine: «هل عندك الطنين؟»
6. **EN** Sofiane says, “No, I do not.”
   - FR : Sofiane répond : « Non. »
   - AR : يجيب Sofiane: «لا.»
7. **EN** Amine writes “Otalgia” in the file.
   - FR : Amine écrit « Otalgie » dans le dossier.
   - AR : يكتب Amine «Otalgie» في الملف.

### Expressions ciblées
- `gen:otalgia:v1` — **otalgia** · FR : Otalgie · AR : ألم الأذن
- `gen:tinnitus:v1` — **tinnitus** · FR : Acouphène · AR : الطنين
- `gen:hearing-loss:v1` — **hearing loss** · FR : Hypoacousie (Perte auditive) · AR : ضعف السمع

### Questions (acceptées / modèle / hint)
- **q1.** Does Sofiane have otalgia?
  - accepté : yes ; yes he does ; yes he has otalgia ; he has otalgia
  - modèle : Yes, he has otalgia.
- **q2.** Does he have tinnitus?
  - accepté : no ; no he does not ; no he doesn't ; he does not have tinnitus ; he doesn't
  - modèle : No, he does not have tinnitus.
  - hint (si erreur) — FR : Attention : Sofiane n'a PAS acouphène.
- **q3.** Does he have otalgia or tinnitus?
  - accepté : otalgia ; he has otalgia
  - modèle : He has otalgia.
- **q4.** What does Sofiane have?
  - accepté : otalgia ; he has otalgia
  - modèle : He has otalgia.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have hearing loss?
  - accepté : yes ; yes he does ; he has hearing loss
  - modèle : Yes, he has hearing loss.
- **Tt2.** Does he have otalgia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have otalgia ; he doesn't
  - modèle : No, he does not have otalgia.
  - hint (si erreur) — FR : Attention : il n'a PAS otalgie.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … otalgia. (have)` → has · modèle : He has otalgia.
- `They … not have tinnitus. (do)` → do · modèle : They do not have tinnitus.
- `I … otalgia. (have)` → have · modèle : I have otalgia.

### Productions
- **Reformulation :** Rapporte le cas de Sofiane en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has otalgia. He does not have tinnitus.*
- **Transfert (contexte) :** Un autre patient a Hypoacousie (Perte auditive). Il n'a pas de Otalgie.
- Glossaire : Hypoacousie (Perte auditive) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has hearing loss. He does not have otalgia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-009` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Otalgie et Acouphène à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient.
   - FR : Leila est un·e patient·e.
   - AR : Leila مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check otalgia.”
   - FR : « D'abord, nous vérifions Otalgie. »
   - AR : «أولًا، نتحقق من ألم الأذن.»
5. **EN** “Then, we check tinnitus.”
   - FR : « Ensuite, nous vérifions Acouphène. »
   - AR : «ثم نتحقق من الطنين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:otalgia:v1` — **otalgia** · FR : Otalgie · AR : ألم الأذن
- `gen:tinnitus:v1` — **tinnitus** · FR : Acouphène · AR : الطنين
- `gen:hearing-loss:v1` — **hearing loss** · FR : Hypoacousie (Perte auditive) · AR : ضعف السمع

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : otalgia ; we check otalgia ; otalgia first ; the doctor checks otalgia first
  - modèle : First, the doctor checks otalgia.
- **q2.** What does the doctor check next?
  - accepté : tinnitus ; then we check tinnitus ; tinnitus next ; the doctor checks tinnitus next
  - modèle : Then, the doctor checks tinnitus.
- **q3.** Does the doctor check tinnitus before otalgia?
  - accepté : no ; no he does not ; no he doesn't ; he checks otalgia first
  - modèle : No, he checks otalgia first, then tinnitus.
  - hint (si erreur) — FR : Attention : Otalgie vient D'ABORD, ensuite Acouphène.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : hearing loss ; hearing loss first ; he checks hearing loss first
  - modèle : First, the doctor checks hearing loss.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check otalgia. Then, we check tinnitus.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Hypoacousie (Perte auditive), ensuite Otalgie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check hearing loss. Then, we check otalgia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-009` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Otalgie / Acouphène, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has otalgia.
   - FR : Le patient du lit 3 a Otalgie.
   - AR : مريض السرير ٣ عنده ألم الأذن.
4. **EN** The patient in bed 5 has tinnitus.
   - FR : Le patient du lit 5 a Acouphène.
   - AR : مريض السرير ٥ عنده الطنين.
5. **EN** The patient in bed 5 does not have otalgia.
   - FR : Le patient du lit 5 n'a pas de Otalgie.
   - AR : مريض السرير ٥ ليس عنده ألم الأذن.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:otalgia:v1` — **otalgia** · FR : Otalgie · AR : ألم الأذن
- `gen:tinnitus:v1` — **tinnitus** · FR : Acouphène · AR : الطنين
- `gen:hearing-loss:v1` — **hearing loss** · FR : Hypoacousie (Perte auditive) · AR : ضعف السمع

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : otalgia ; the patient with otalgia
  - modèle : The patient in bed 3 has otalgia.
- **q2.** What does the patient in bed 5 have?
  - accepté : tinnitus ; he has tinnitus
  - modèle : He has tinnitus.
- **q3.** Does the patient in bed 5 have otalgia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have otalgia ; he doesn't
  - modèle : No, he does not have otalgia.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS otalgie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : hearing loss ; he has hearing loss
  - modèle : He has hearing loss.
- **Tt2.** Does the patient in bed 9 have tinnitus?
  - accepté : no ; no he does not ; no he doesn't ; he does not have tinnitus ; he doesn't
  - modèle : No, he does not have tinnitus.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS acouphène.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … otalgia and tinnitus. (have)` → have · modèle : The patients have otalgia and tinnitus.
- `The patient in bed 5 … not have otalgia. (do)` → does · modèle : The patient in bed 5 does not have otalgia.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has otalgia. The patient in bed 5 has tinnitus.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Hypoacousie (Perte auditive), le lit 9 a Otalgie et n'a pas Acouphène.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has hearing loss. The patient in bed 9 has otalgia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
