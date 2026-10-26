# Relecture — Signes et Symptômes (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-025` — Le cas de Omar : Fièvre (Pyrexie) / _Omar: Fever_

**Objectif :** Comprendre un cas : fièvre (pyrexie) présent, douleur absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Omar talks to Amine, a medical student.
   - FR : Omar parle avec Amine, étudiant·e en médecine.
   - AR : Omar يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has fever.
   - FR : Il a Fièvre (Pyrexie).
   - AR : عنده الحُمى.
4. **EN** He does not have pain.
   - FR : Il n'a pas de Douleur.
   - AR : ليس عنده الألم.
5. **EN** Amine asks, “Do you have pain?”
   - FR : Amine demande : « Avez-vous Douleur ? »
   - AR : يسأل Amine: «هل عندك الألم؟»
6. **EN** Omar says, “No, I do not.”
   - FR : Omar répond : « Non. »
   - AR : يجيب Omar: «لا.»
7. **EN** Amine writes “Fever” in the file.
   - FR : Amine écrit « Fièvre (Pyrexie) » dans le dossier.
   - AR : يكتب Amine «Fièvre (Pyrexie)» في الملف.

### Expressions ciblées
- `gen:fever:v1` — **fever** · FR : Fièvre (Pyrexie) · AR : الحُمى
- `gen:pain:v1` — **pain** · FR : Douleur · AR : الألم
- `gen:symptom:v1` — **a symptom** · FR : Symptôme · AR : العَرَض

### Questions (acceptées / modèle / hint)
- **q1.** Does Omar have fever?
  - accepté : yes ; yes he does ; yes he has fever ; he has fever
  - modèle : Yes, he has fever.
- **q2.** Does he have pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have pain ; he doesn't
  - modèle : No, he does not have pain.
  - hint (si erreur) — FR : Attention : Omar n'a PAS douleur.
- **q3.** Does he have fever or pain?
  - accepté : fever ; he has fever
  - modèle : He has fever.
- **q4.** What does Omar have?
  - accepté : fever ; he has fever
  - modèle : He has fever.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have symptom?
  - accepté : yes ; yes he does ; he has a symptom
  - modèle : Yes, he has a symptom.
- **Tt2.** Does he have fever?
  - accepté : no ; no he does not ; no he doesn't ; he does not have fever ; he doesn't
  - modèle : No, he does not have fever.
  - hint (si erreur) — FR : Attention : il n'a PAS fièvre (pyrexie).

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … fever. (have)` → has · modèle : He has fever.
- `They … not have pain. (do)` → do · modèle : They do not have pain.
- `I … fever. (have)` → have · modèle : I have fever.

### Productions
- **Reformulation :** Rapporte le cas de Omar en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has fever. He does not have pain.*
- **Transfert (contexte) :** Un autre patient a Symptôme. Il n'a pas de Fièvre (Pyrexie).
- Glossaire : Symptôme : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a symptom. He does not have fever.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-025` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Symptôme et Signe à un patient.
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
4. **EN** “First, we check symptom.”
   - FR : « D'abord, nous vérifions Symptôme. »
   - AR : «أولًا، نتحقق من العَرَض.»
5. **EN** “Then, we check sign.”
   - FR : « Ensuite, nous vérifions Signe. »
   - AR : «ثم نتحقق من العلامة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:symptom:v1` — **a symptom** · FR : Symptôme · AR : العَرَض
- `gen:sign:v1` — **a sign** · FR : Signe · AR : العلامة
- `gen:fever:v1` — **fever** · FR : Fièvre (Pyrexie) · AR : الحُمى

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : symptom ; we check symptom ; symptom first ; the doctor checks symptom first
  - modèle : First, the doctor checks symptom.
- **q2.** What does the doctor check next?
  - accepté : sign ; then we check sign ; sign next ; the doctor checks sign next
  - modèle : Then, the doctor checks sign.
- **q3.** Does the doctor check sign before symptom?
  - accepté : no ; no he does not ; no he doesn't ; he checks symptom first
  - modèle : No, he checks symptom first, then sign.
  - hint (si erreur) — FR : Attention : Symptôme vient D'ABORD, ensuite Signe.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : fever ; fever first ; he checks fever first
  - modèle : First, the doctor checks fever.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check symptom. Then, we check sign.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fièvre (Pyrexie), ensuite Symptôme, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check fever. Then, we check symptom.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-025` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Fièvre (Pyrexie) / Douleur, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has fever.
   - FR : Le patient du lit 3 a Fièvre (Pyrexie).
   - AR : مريض السرير ٣ عنده الحُمى.
4. **EN** The patient in bed 5 has pain.
   - FR : Le patient du lit 5 a Douleur.
   - AR : مريض السرير ٥ عنده الألم.
5. **EN** The patient in bed 5 does not have fever.
   - FR : Le patient du lit 5 n'a pas de Fièvre (Pyrexie).
   - AR : مريض السرير ٥ ليس عنده الحُمى.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:fever:v1` — **fever** · FR : Fièvre (Pyrexie) · AR : الحُمى
- `gen:pain:v1` — **pain** · FR : Douleur · AR : الألم
- `gen:symptom:v1` — **a symptom** · FR : Symptôme · AR : العَرَض

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : fever ; the patient with fever
  - modèle : The patient in bed 3 has fever.
- **q2.** What does the patient in bed 5 have?
  - accepté : pain
  - modèle : He has pain.
- **q3.** Does the patient in bed 5 have fever?
  - accepté : no ; no he does not ; no he doesn't ; he does not have fever ; he doesn't
  - modèle : No, he does not have fever.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS fièvre (pyrexie) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : symptom ; a symptom
  - modèle : He has a symptom.
- **Tt2.** Does the patient in bed 9 have pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have pain ; he doesn't
  - modèle : No, he does not have pain.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS douleur.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … fever and pain. (have)` → have · modèle : The patients have fever and pain.
- `The patient in bed 5 … not have fever. (do)` → does · modèle : The patient in bed 5 does not have fever.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has fever. The patient in bed 5 has pain.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Symptôme, le lit 9 a Fièvre (Pyrexie) et n'a pas Douleur.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a symptom. The patient in bed 9 has fever.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
