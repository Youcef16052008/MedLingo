# Relecture — Système Cardiovasculaire (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-026` — Le cas de Leila : Hypertension / _Leila: Hypertension_

**Objectif :** Comprendre un cas : hypertension présent, hypotension absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Leila talks to Lina, a medical student.
   - FR : Leila parle avec Lina, étudiant·e en médecine.
   - AR : Leila يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has hypertension.
   - FR : Elle a Hypertension.
   - AR : عنده ارتفاع ضغط الدم.
4. **EN** She does not have hypotension.
   - FR : Elle n'a pas de Hypotension.
   - AR : ليس عنده انخفاض ضغط الدم.
5. **EN** Lina asks, “Do you have hypotension?”
   - FR : Lina demande : « Avez-vous Hypotension ? »
   - AR : يسأل Lina: «هل عندك انخفاض ضغط الدم؟»
6. **EN** Leila says, “No, I do not.”
   - FR : Leila répond : « Non. »
   - AR : يجيب Leila: «لا.»
7. **EN** Lina writes “Hypertension” in the file.
   - FR : Lina écrit « Hypertension » dans le dossier.
   - AR : يكتب Lina «Hypertension» في الملف.

### Expressions ciblées
- `gen:hypertension:v1` — **hypertension** · FR : Hypertension · AR : ارتفاع ضغط الدم
- `gen:hypotension:v1` — **hypotension** · FR : Hypotension · AR : انخفاض ضغط الدم
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب

### Questions (acceptées / modèle / hint)
- **q1.** Does Leila have hypertension?
  - accepté : yes ; yes she does ; yes she has hypertension ; she has hypertension
  - modèle : Yes, she has hypertension.
- **q2.** Does she have hypotension?
  - accepté : no ; no she does not ; no she doesn't ; she does not have hypotension ; she doesn't
  - modèle : No, she does not have hypotension.
  - hint (si erreur) — FR : Attention : Leila n'a PAS hypotension.
- **q3.** Does she have hypertension or hypotension?
  - accepté : hypertension ; she has hypertension
  - modèle : She has hypertension.
- **q4.** What does Leila have?
  - accepté : hypertension ; she has hypertension
  - modèle : She has hypertension.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have heart?
  - accepté : yes ; yes he does ; he has a heart
  - modèle : Yes, he has a heart.
- **Tt2.** Does he have hypertension?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hypertension ; he doesn't
  - modèle : No, he does not have hypertension.
  - hint (si erreur) — FR : Attention : il n'a PAS hypertension.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … hypertension. (have)` → has · modèle : She has hypertension.
- `They … not have hypotension. (do)` → do · modèle : They do not have hypotension.
- `I … hypertension. (have)` → have · modèle : I have hypertension.

### Productions
- **Reformulation :** Rapporte le cas de Leila en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has hypertension. She does not have hypotension.*
- **Transfert (contexte) :** Un autre patient a Cœur. Il n'a pas de Hypertension.
- Glossaire : Cœur : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a heart. He does not have hypertension.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-026` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Cœur et Artère à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient.
   - FR : Farid est un·e patient·e.
   - AR : Farid مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check heart.”
   - FR : « D'abord, nous vérifions Cœur. »
   - AR : «أولًا، نتحقق من القلب.»
5. **EN** “Then, we check artery.”
   - FR : « Ensuite, nous vérifions Artère. »
   - AR : «ثم نتحقق من شريان.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب
- `gen:artery:v1` — **an artery** · FR : Artère · AR : شريان
- `gen:vein:v1` — **a vein** · FR : Veine · AR : وريد

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : heart ; we check heart ; heart first ; the doctor checks heart first
  - modèle : First, the doctor checks heart.
- **q2.** What does the doctor check next?
  - accepté : artery ; then we check artery ; artery next ; the doctor checks artery next
  - modèle : Then, the doctor checks artery.
- **q3.** Does the doctor check artery before heart?
  - accepté : no ; no he does not ; no he doesn't ; he checks heart first
  - modèle : No, he checks heart first, then artery.
  - hint (si erreur) — FR : Attention : Cœur vient D'ABORD, ensuite Artère.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : vein ; vein first ; he checks vein first
  - modèle : First, the doctor checks vein.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check heart. Then, we check artery.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Veine, ensuite Cœur, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check vein. Then, we check heart.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-026` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Hypertension / Hypotension, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has hypertension.
   - FR : Le patient du lit 3 a Hypertension.
   - AR : مريض السرير ٣ عنده ارتفاع ضغط الدم.
4. **EN** The patient in bed 5 has hypotension.
   - FR : Le patient du lit 5 a Hypotension.
   - AR : مريض السرير ٥ عنده انخفاض ضغط الدم.
5. **EN** The patient in bed 5 does not have hypertension.
   - FR : Le patient du lit 5 n'a pas de Hypertension.
   - AR : مريض السرير ٥ ليس عنده ارتفاع ضغط الدم.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:hypertension:v1` — **hypertension** · FR : Hypertension · AR : ارتفاع ضغط الدم
- `gen:hypotension:v1` — **hypotension** · FR : Hypotension · AR : انخفاض ضغط الدم
- `gen:heart:v1` — **a heart** · FR : Cœur · AR : القلب

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hypertension ; the patient with hypertension
  - modèle : The patient in bed 3 has hypertension.
- **q2.** What does the patient in bed 5 have?
  - accepté : hypotension
  - modèle : He has hypotension.
- **q3.** Does the patient in bed 5 have hypertension?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hypertension ; he doesn't
  - modèle : No, he does not have hypertension.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS hypertension — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : heart ; a heart
  - modèle : He has a heart.
- **Tt2.** Does the patient in bed 9 have hypotension?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hypotension ; he doesn't
  - modèle : No, he does not have hypotension.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS hypotension.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … hypertension and hypotension. (have)` → have · modèle : The patients have hypertension and hypotension.
- `The patient in bed 5 … not have hypertension. (do)` → does · modèle : The patient in bed 5 does not have hypertension.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has hypertension. The patient in bed 5 has hypotension.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Cœur, le lit 9 a Hypertension et n'a pas Hypotension.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a heart. The patient in bed 9 has hypertension.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
