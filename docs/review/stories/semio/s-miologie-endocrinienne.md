# Relecture — Sémiologie Endocrinienne (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-006` — Le cas de Amel : Polydipsie / _Amel: Polydipsia_

**Objectif :** Comprendre un cas : polydipsie présent, polyphagie absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Amel talks to Lina, a medical student.
   - FR : Amel parle avec Lina, étudiant·e en médecine.
   - AR : Amel يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has polydipsia.
   - FR : Elle a Polydipsie.
   - AR : عنده العطاش.
4. **EN** She does not have polyphagia.
   - FR : Elle n'a pas de Polyphagie.
   - AR : ليس عنده الشراهة (كثرة الأكل).
5. **EN** Lina asks, “Do you have polyphagia?”
   - FR : Lina demande : « Avez-vous Polyphagie ? »
   - AR : يسأل Lina: «هل عندك الشراهة (كثرة الأكل)؟»
6. **EN** Amel says, “No, I do not.”
   - FR : Amel répond : « Non. »
   - AR : يجيب Amel: «لا.»
7. **EN** Lina writes “Polydipsia” in the file.
   - FR : Lina écrit « Polydipsie » dans le dossier.
   - AR : يكتب Lina «Polydipsie» في الملف.

### Expressions ciblées
- `gen:polydipsia:v1` — **polydipsia** · FR : Polydipsie · AR : العطاش
- `gen:polyphagia:v1` — **polyphagia** · FR : Polyphagie · AR : الشراهة (كثرة الأكل)
- `gen:weight-loss:v1` — **weight loss** · FR : Amaigrissement (involontaire) · AR : فقدان الوزن (غير الإرادي)

### Questions (acceptées / modèle / hint)
- **q1.** Does Amel have polydipsia?
  - accepté : yes ; yes she does ; yes she has polydipsia ; she has polydipsia
  - modèle : Yes, she has polydipsia.
- **q2.** Does she have polyphagia?
  - accepté : no ; no she does not ; no she doesn't ; she does not have polyphagia ; she doesn't
  - modèle : No, she does not have polyphagia.
  - hint (si erreur) — FR : Attention : Amel n'a PAS polyphagie.
- **q3.** Does she have polydipsia or polyphagia?
  - accepté : polydipsia ; she has polydipsia
  - modèle : She has polydipsia.
- **q4.** What does Amel have?
  - accepté : polydipsia ; she has polydipsia
  - modèle : She has polydipsia.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have weight loss?
  - accepté : yes ; yes he does ; he has weight loss
  - modèle : Yes, he has weight loss.
- **Tt2.** Does he have polydipsia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have polydipsia ; he doesn't
  - modèle : No, he does not have polydipsia.
  - hint (si erreur) — FR : Attention : il n'a PAS polydipsie.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … polydipsia. (have)` → has · modèle : She has polydipsia.
- `They … not have polyphagia. (do)` → do · modèle : They do not have polyphagia.
- `I … polydipsia. (have)` → have · modèle : I have polydipsia.

### Productions
- **Reformulation :** Rapporte le cas de Amel en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has polydipsia. She does not have polyphagia.*
- **Transfert (contexte) :** Un autre patient a Amaigrissement (involontaire). Il n'a pas de Polydipsie.
- Glossaire : Amaigrissement (involontaire) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has weight loss. He does not have polydipsia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Polydipsie et Polyphagie à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient.
   - FR : Bilal est un·e patient·e.
   - AR : Bilal مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check polydipsia.”
   - FR : « D'abord, nous vérifions Polydipsie. »
   - AR : «أولًا، نتحقق من العطاش.»
5. **EN** “Then, we check polyphagia.”
   - FR : « Ensuite, nous vérifions Polyphagie. »
   - AR : «ثم نتحقق من الشراهة (كثرة الأكل).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:polydipsia:v1` — **polydipsia** · FR : Polydipsie · AR : العطاش
- `gen:polyphagia:v1` — **polyphagia** · FR : Polyphagie · AR : الشراهة (كثرة الأكل)
- `gen:weight-loss:v1` — **weight loss** · FR : Amaigrissement (involontaire) · AR : فقدان الوزن (غير الإرادي)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : polydipsia ; we check polydipsia ; polydipsia first ; the doctor checks polydipsia first
  - modèle : First, the doctor checks polydipsia.
- **q2.** What does the doctor check next?
  - accepté : polyphagia ; then we check polyphagia ; polyphagia next ; the doctor checks polyphagia next
  - modèle : Then, the doctor checks polyphagia.
- **q3.** Does the doctor check polyphagia before polydipsia?
  - accepté : no ; no he does not ; no he doesn't ; he checks polydipsia first
  - modèle : No, he checks polydipsia first, then polyphagia.
  - hint (si erreur) — FR : Attention : Polydipsie vient D'ABORD, ensuite Polyphagie.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : weight loss ; weight loss first ; he checks weight loss first
  - modèle : First, the doctor checks weight loss.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check polydipsia. Then, we check polyphagia.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Amaigrissement (involontaire), ensuite Polydipsie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check weight loss. Then, we check polydipsia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Polydipsie / Polyphagie, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has polydipsia.
   - FR : Le patient du lit 3 a Polydipsie.
   - AR : مريض السرير ٣ عنده العطاش.
4. **EN** The patient in bed 5 has polyphagia.
   - FR : Le patient du lit 5 a Polyphagie.
   - AR : مريض السرير ٥ عنده الشراهة (كثرة الأكل).
5. **EN** The patient in bed 5 does not have polydipsia.
   - FR : Le patient du lit 5 n'a pas de Polydipsie.
   - AR : مريض السرير ٥ ليس عنده العطاش.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:polydipsia:v1` — **polydipsia** · FR : Polydipsie · AR : العطاش
- `gen:polyphagia:v1` — **polyphagia** · FR : Polyphagie · AR : الشراهة (كثرة الأكل)
- `gen:weight-loss:v1` — **weight loss** · FR : Amaigrissement (involontaire) · AR : فقدان الوزن (غير الإرادي)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : polydipsia ; the patient with polydipsia
  - modèle : The patient in bed 3 has polydipsia.
- **q2.** What does the patient in bed 5 have?
  - accepté : polyphagia
  - modèle : He has polyphagia.
- **q3.** Does the patient in bed 5 have polydipsia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have polydipsia ; he doesn't
  - modèle : No, he does not have polydipsia.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS polydipsie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : weight loss
  - modèle : He has weight loss.
- **Tt2.** Does the patient in bed 9 have polyphagia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have polyphagia ; he doesn't
  - modèle : No, he does not have polyphagia.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS polyphagie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … polydipsia and polyphagia. (have)` → have · modèle : The patients have polydipsia and polyphagia.
- `The patient in bed 5 … not have polydipsia. (do)` → does · modèle : The patient in bed 5 does not have polydipsia.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has polydipsia. The patient in bed 5 has polyphagia.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Amaigrissement (involontaire), le lit 9 a Polydipsie et n'a pas Polyphagie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has weight loss. The patient in bed 9 has polydipsia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
