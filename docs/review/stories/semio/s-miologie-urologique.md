# Relecture — Sémiologie Urologique (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-013` — Le cas de Omar : Dysurie / _Omar: Dysuria_

**Objectif :** Comprendre un cas : dysurie présent, hématurie absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Omar talks to Amine, a medical student.
   - FR : Omar parle avec Amine, étudiant·e en médecine.
   - AR : Omar يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has dysuria.
   - FR : Il a Dysurie.
   - AR : عنده عُسر التبول.
4. **EN** He does not have hematuria.
   - FR : Il n'a pas de Hématurie.
   - AR : ليس عنده بول الدم.
5. **EN** Amine asks, “Do you have hematuria?”
   - FR : Amine demande : « Avez-vous Hématurie ? »
   - AR : يسأل Amine: «هل عندك بول الدم؟»
6. **EN** Omar says, “No, I do not.”
   - FR : Omar répond : « Non. »
   - AR : يجيب Omar: «لا.»
7. **EN** Amine writes “Dysuria” in the file.
   - FR : Amine écrit « Dysurie » dans le dossier.
   - AR : يكتب Amine «Dysurie» في الملف.

### Expressions ciblées
- `gen:dysuria:v1` — **dysuria** · FR : Dysurie · AR : عُسر التبول
- `gen:hematuria:v1` — **hematuria** · FR : Hématurie · AR : بول الدم
- `gen:proteinuria:v1` — **proteinuria** · FR : Protéinurie · AR : البيلة البروتينية

### Questions (acceptées / modèle / hint)
- **q1.** Does Omar have dysuria?
  - accepté : yes ; yes he does ; yes he has dysuria ; he has dysuria
  - modèle : Yes, he has dysuria.
- **q2.** Does he have hematuria?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hematuria ; he doesn't
  - modèle : No, he does not have hematuria.
  - hint (si erreur) — FR : Attention : Omar n'a PAS hématurie.
- **q3.** Does he have dysuria or hematuria?
  - accepté : dysuria ; he has dysuria
  - modèle : He has dysuria.
- **q4.** What does Omar have?
  - accepté : dysuria ; he has dysuria
  - modèle : He has dysuria.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have proteinuria?
  - accepté : yes ; yes he does ; he has proteinuria
  - modèle : Yes, he has proteinuria.
- **Tt2.** Does he have dysuria?
  - accepté : no ; no he does not ; no he doesn't ; he does not have dysuria ; he doesn't
  - modèle : No, he does not have dysuria.
  - hint (si erreur) — FR : Attention : il n'a PAS dysurie.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … dysuria. (have)` → has · modèle : He has dysuria.
- `They … not have hematuria. (do)` → do · modèle : They do not have hematuria.
- `I … dysuria. (have)` → have · modèle : I have dysuria.

### Productions
- **Reformulation :** Rapporte le cas de Omar en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has dysuria. He does not have hematuria.*
- **Transfert (contexte) :** Un autre patient a Protéinurie. Il n'a pas de Dysurie.
- Glossaire : Protéinurie : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has proteinuria. He does not have dysuria.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-013` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Dysurie et Hématurie à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient.
   - FR : Amel est un·e patient·e.
   - AR : Amel مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check dysuria.”
   - FR : « D'abord, nous vérifions Dysurie. »
   - AR : «أولًا، نتحقق من عُسر التبول.»
5. **EN** “Then, we check hematuria.”
   - FR : « Ensuite, nous vérifions Hématurie. »
   - AR : «ثم نتحقق من بول الدم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:dysuria:v1` — **dysuria** · FR : Dysurie · AR : عُسر التبول
- `gen:hematuria:v1` — **hematuria** · FR : Hématurie · AR : بول الدم
- `gen:proteinuria:v1` — **proteinuria** · FR : Protéinurie · AR : البيلة البروتينية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : dysuria ; we check dysuria ; dysuria first ; the doctor checks dysuria first
  - modèle : First, the doctor checks dysuria.
- **q2.** What does the doctor check next?
  - accepté : hematuria ; then we check hematuria ; hematuria next ; the doctor checks hematuria next
  - modèle : Then, the doctor checks hematuria.
- **q3.** Does the doctor check hematuria before dysuria?
  - accepté : no ; no he does not ; no he doesn't ; he checks dysuria first
  - modèle : No, he checks dysuria first, then hematuria.
  - hint (si erreur) — FR : Attention : Dysurie vient D'ABORD, ensuite Hématurie.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : proteinuria ; proteinuria first ; he checks proteinuria first
  - modèle : First, the doctor checks proteinuria.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check dysuria. Then, we check hematuria.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Protéinurie, ensuite Dysurie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check proteinuria. Then, we check dysuria.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-013` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Dysurie / Hématurie, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has dysuria.
   - FR : Le patient du lit 3 a Dysurie.
   - AR : مريض السرير ٣ عنده عُسر التبول.
4. **EN** The patient in bed 5 has hematuria.
   - FR : Le patient du lit 5 a Hématurie.
   - AR : مريض السرير ٥ عنده بول الدم.
5. **EN** The patient in bed 5 does not have dysuria.
   - FR : Le patient du lit 5 n'a pas de Dysurie.
   - AR : مريض السرير ٥ ليس عنده عُسر التبول.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:dysuria:v1` — **dysuria** · FR : Dysurie · AR : عُسر التبول
- `gen:hematuria:v1` — **hematuria** · FR : Hématurie · AR : بول الدم
- `gen:proteinuria:v1` — **proteinuria** · FR : Protéinurie · AR : البيلة البروتينية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : dysuria ; the patient with dysuria
  - modèle : The patient in bed 3 has dysuria.
- **q2.** What does the patient in bed 5 have?
  - accepté : hematuria ; he has hematuria
  - modèle : He has hematuria.
- **q3.** Does the patient in bed 5 have dysuria?
  - accepté : no ; no he does not ; no he doesn't ; he does not have dysuria ; he doesn't
  - modèle : No, he does not have dysuria.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS dysurie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : proteinuria ; he has proteinuria
  - modèle : He has proteinuria.
- **Tt2.** Does the patient in bed 9 have hematuria?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hematuria ; he doesn't
  - modèle : No, he does not have hematuria.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS hématurie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … dysuria and hematuria. (have)` → have · modèle : The patients have dysuria and hematuria.
- `The patient in bed 5 … not have dysuria. (do)` → does · modèle : The patient in bed 5 does not have dysuria.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has dysuria. The patient in bed 5 has hematuria.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Protéinurie, le lit 9 a Dysurie et n'a pas Hématurie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has proteinuria. The patient in bed 9 has dysuria.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
