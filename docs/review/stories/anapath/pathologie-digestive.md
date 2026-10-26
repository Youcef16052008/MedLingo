# Relecture — Pathologie Digestive (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-007` — Le cas de Farid : Rectocolite hémorragique (pathologie) / _Farid: Ulcerative colitis_

**Objectif :** Comprendre un cas : rectocolite hémorragique (pathologie) présent, pancréatite aiguë (pathologique) absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Farid talks to Amine, a medical student.
   - FR : Farid parle avec Amine, étudiant·e en médecine.
   - AR : Farid يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has ulcerative colitis.
   - FR : Il a Rectocolite hémorragique (pathologie).
   - AR : عنده التهاب القولون التقرحي (مرضي).
4. **EN** He does not have acute pancreatitis.
   - FR : Il n'a pas de Pancréatite aiguë (pathologique).
   - AR : ليس عنده التهاب البنكرياس الحاد (مرضي).
5. **EN** Amine asks, “Do you have acute pancreatitis?”
   - FR : Amine demande : « Avez-vous Pancréatite aiguë (pathologique) ? »
   - AR : يسأل Amine: «هل عندك التهاب البنكرياس الحاد (مرضي)؟»
6. **EN** Farid says, “No, I do not.”
   - FR : Farid répond : « Non. »
   - AR : يجيب Farid: «لا.»
7. **EN** Amine writes “Ulcerative colitis” in the file.
   - FR : Amine écrit « Rectocolite hémorragique (pathologie) » dans le dossier.
   - AR : يكتب Amine «Rectocolite hémorragique (pathologie)» في الملف.

### Expressions ciblées
- `gen:ulcerative-colitis:v1` — **ulcerative colitis** · FR : Rectocolite hémorragique (pathologie) · AR : التهاب القولون التقرحي (مرضي)
- `gen:acute-pancreatitis:v1` — **acute pancreatitis** · FR : Pancréatite aiguë (pathologique) · AR : التهاب البنكرياس الحاد (مرضي)
- `gen:peptic-ulcer:v1` — **a peptic ulcer** · FR : Ulcère gastroduodénal (pathologique) · AR : قرحة المعدة والاثني عشر (مرضي)

### Questions (acceptées / modèle / hint)
- **q1.** Does Farid have ulcerative colitis?
  - accepté : yes ; yes he does ; yes he has ulcerative colitis ; he has ulcerative colitis
  - modèle : Yes, he has ulcerative colitis.
- **q2.** Does he have acute pancreatitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have acute pancreatitis ; he doesn't
  - modèle : No, he does not have acute pancreatitis.
  - hint (si erreur) — FR : Attention : Farid n'a PAS pancréatite aiguë (pathologique).
- **q3.** Does he have ulcerative colitis or acute pancreatitis?
  - accepté : ulcerative colitis ; he has ulcerative colitis
  - modèle : He has ulcerative colitis.
- **q4.** What does Farid have?
  - accepté : ulcerative colitis ; he has ulcerative colitis
  - modèle : He has ulcerative colitis.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have peptic ulcer?
  - accepté : yes ; yes he does ; he has a peptic ulcer
  - modèle : Yes, he has a peptic ulcer.
- **Tt2.** Does he have ulcerative colitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have ulcerative colitis ; he doesn't
  - modèle : No, he does not have ulcerative colitis.
  - hint (si erreur) — FR : Attention : il n'a PAS rectocolite hémorragique (pathologie).

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … ulcerative colitis. (have)` → has · modèle : He has ulcerative colitis.
- `They … not have acute pancreatitis. (do)` → do · modèle : They do not have acute pancreatitis.
- `I … ulcerative colitis. (have)` → have · modèle : I have ulcerative colitis.

### Productions
- **Reformulation :** Rapporte le cas de Farid en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has ulcerative colitis. He does not have acute pancreatitis.*
- **Transfert (contexte) :** Un autre patient a Ulcère gastroduodénal (pathologique). Il n'a pas de Rectocolite hémorragique (pathologie).
- Glossaire : Ulcère gastroduodénal (pathologique) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a peptic ulcer. He does not have ulcerative colitis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-007` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Ulcère gastroduodénal (pathologique) et Helicobacter pylori (pathologie gastrique) à un patient.
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
4. **EN** “First, we check peptic ulcer.”
   - FR : « D'abord, nous vérifions Ulcère gastroduodénal (pathologique). »
   - AR : «أولًا، نتحقق من قرحة المعدة والاثني عشر (مرضي).»
5. **EN** “Then, we check helicobacter pylori.”
   - FR : « Ensuite, nous vérifions Helicobacter pylori (pathologie gastrique). »
   - AR : «ثم نتحقق من الملوية البوابية (أمراض المعدة).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:peptic-ulcer:v1` — **a peptic ulcer** · FR : Ulcère gastroduodénal (pathologique) · AR : قرحة المعدة والاثني عشر (مرضي)
- `gen:helicobacter-pylori:v1` — **a helicobacter pylori** · FR : Helicobacter pylori (pathologie gastrique) · AR : الملوية البوابية (أمراض المعدة)
- `gen:colon-adenocarcinoma:v1` — **a colon adenocarcinoma** · FR : Adénocarcinome du côlon · AR : السرطانة الغدية للقولون

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : peptic ulcer ; we check peptic ulcer ; peptic ulcer first ; the doctor checks peptic ulcer first
  - modèle : First, the doctor checks peptic ulcer.
- **q2.** What does the doctor check next?
  - accepté : helicobacter pylori ; then we check helicobacter pylori ; helicobacter pylori next ; the doctor checks helicobacter pylori next
  - modèle : Then, the doctor checks helicobacter pylori.
- **q3.** Does the doctor check helicobacter pylori before peptic ulcer?
  - accepté : no ; no he does not ; no he doesn't ; he checks peptic ulcer first
  - modèle : No, he checks peptic ulcer first, then helicobacter pylori.
  - hint (si erreur) — FR : Attention : Ulcère gastroduodénal (pathologique) vient D'ABORD, ensuite Helicobacter pylori (pathologie gastrique).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : colon adenocarcinoma ; colon adenocarcinoma first ; he checks colon adenocarcinoma first
  - modèle : First, the doctor checks colon adenocarcinoma.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check peptic ulcer. Then, we check helicobacter pylori.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Adénocarcinome du côlon, ensuite Ulcère gastroduodénal (pathologique), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check colon adenocarcinoma. Then, we check peptic ulcer.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-007` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Rectocolite hémorragique (pathologie) / Pancréatite aiguë (pathologique), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has ulcerative colitis.
   - FR : Le patient du lit 3 a Rectocolite hémorragique (pathologie).
   - AR : مريض السرير ٣ عنده التهاب القولون التقرحي (مرضي).
4. **EN** The patient in bed 5 has acute pancreatitis.
   - FR : Le patient du lit 5 a Pancréatite aiguë (pathologique).
   - AR : مريض السرير ٥ عنده التهاب البنكرياس الحاد (مرضي).
5. **EN** The patient in bed 5 does not have ulcerative colitis.
   - FR : Le patient du lit 5 n'a pas de Rectocolite hémorragique (pathologie).
   - AR : مريض السرير ٥ ليس عنده التهاب القولون التقرحي (مرضي).
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:ulcerative-colitis:v1` — **ulcerative colitis** · FR : Rectocolite hémorragique (pathologie) · AR : التهاب القولون التقرحي (مرضي)
- `gen:acute-pancreatitis:v1` — **acute pancreatitis** · FR : Pancréatite aiguë (pathologique) · AR : التهاب البنكرياس الحاد (مرضي)
- `gen:peptic-ulcer:v1` — **a peptic ulcer** · FR : Ulcère gastroduodénal (pathologique) · AR : قرحة المعدة والاثني عشر (مرضي)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : ulcerative colitis ; the patient with ulcerative colitis
  - modèle : The patient in bed 3 has ulcerative colitis.
- **q2.** What does the patient in bed 5 have?
  - accepté : acute pancreatitis
  - modèle : He has acute pancreatitis.
- **q3.** Does the patient in bed 5 have ulcerative colitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have ulcerative colitis ; he doesn't
  - modèle : No, he does not have ulcerative colitis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS rectocolite hémorragique (pathologie) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : peptic ulcer ; a peptic ulcer
  - modèle : He has a peptic ulcer.
- **Tt2.** Does the patient in bed 9 have acute pancreatitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have acute pancreatitis ; he doesn't
  - modèle : No, he does not have acute pancreatitis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS pancréatite aiguë (pathologique).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … ulcerative colitis and acute pancreatitis. (have)` → have · modèle : The patients have ulcerative colitis and acute pancreatitis.
- `The patient in bed 5 … not have ulcerative colitis. (do)` → does · modèle : The patient in bed 5 does not have ulcerative colitis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has ulcerative colitis. The patient in bed 5 has acute pancreatitis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Ulcère gastroduodénal (pathologique), le lit 9 a Rectocolite hémorragique (pathologie) et n'a pas Pancréatite aiguë (pathologique).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a peptic ulcer. The patient in bed 9 has ulcerative colitis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
