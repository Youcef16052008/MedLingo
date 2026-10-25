# Relecture — Thermodynamique (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-011` — Le cas de Bilal : Hypothermie / _Bilal: Hypothermia_

**Objectif :** Comprendre un cas : hypothermie présent, hyperthermie absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Bilal talks to Amine, a medical student.
   - FR : Bilal parle avec Amine, étudiant·e en médecine.
   - AR : Bilal يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has hypothermia.
   - FR : Il a Hypothermie.
   - AR : عنده انخفاض الحرارة.
4. **EN** He does not have hyperthermia.
   - FR : Il n'a pas de Hyperthermie.
   - AR : ليس عنده ارتفاع الحرارة.
5. **EN** Amine asks, “Do you have hyperthermia?”
   - FR : Amine demande : « Avez-vous Hyperthermie ? »
   - AR : يسأل Amine: «هل عندك ارتفاع الحرارة؟»
6. **EN** Bilal says, “No, I do not.”
   - FR : Bilal répond : « Non. »
   - AR : يجيب Bilal: «لا.»
7. **EN** Amine writes “Hypothermia” in the file.
   - FR : Amine écrit « Hypothermie » dans le dossier.
   - AR : يكتب Amine «Hypothermie» في الملف.

### Expressions ciblées
- `gen:hypothermia:v1` — **hypothermia** · FR : Hypothermie · AR : انخفاض الحرارة
- `gen:hyperthermia:v1` — **hyperthermia** · FR : Hyperthermie · AR : ارتفاع الحرارة
- `gen:thermodynamics:v1` — **thermodynamics** · FR : Thermodynamique · AR : الديناميكا الحرارية

### Questions (acceptées / modèle / hint)
- **q1.** Does Bilal have hypothermia?
  - accepté : yes ; yes he does ; yes he has hypothermia ; he has hypothermia
  - modèle : Yes, he has hypothermia.
- **q2.** Does he have hyperthermia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hyperthermia ; he doesn't
  - modèle : No, he does not have hyperthermia.
  - hint (si erreur) — FR : Attention : Bilal n'a PAS hyperthermie.
- **q3.** Does he have hypothermia or hyperthermia?
  - accepté : hypothermia ; he has hypothermia
  - modèle : He has hypothermia.
- **q4.** What does Bilal have?
  - accepté : hypothermia ; he has hypothermia
  - modèle : He has hypothermia.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have thermodynamics?
  - accepté : yes ; yes he does ; he has thermodynamics
  - modèle : Yes, he has thermodynamics.
- **Tt2.** Does he have hypothermia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hypothermia ; he doesn't
  - modèle : No, he does not have hypothermia.
  - hint (si erreur) — FR : Attention : il n'a PAS hypothermie.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … hypothermia. (have)` → has · modèle : He has hypothermia.
- `They … not have hyperthermia. (do)` → do · modèle : They do not have hyperthermia.
- `I … hypothermia. (have)` → have · modèle : I have hypothermia.

### Productions
- **Reformulation :** Rapporte le cas de Bilal en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has hypothermia. He does not have hyperthermia.*
- **Transfert (contexte) :** Un autre patient a Thermodynamique. Il n'a pas de Hypothermie.
- Glossaire : Thermodynamique : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has thermodynamics. He does not have hypothermia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-011` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Thermodynamique et Premier principe de la thermodynamique à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient.
   - FR : Nadia est un·e patient·e.
   - AR : Nadia مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check thermodynamics.”
   - FR : « D'abord, nous vérifions Thermodynamique. »
   - AR : «أولًا، نتحقق من الديناميكا الحرارية.»
5. **EN** “Then, we check first law of thermodynamics.”
   - FR : « Ensuite, nous vérifions Premier principe de la thermodynamique. »
   - AR : «ثم نتحقق من القانون الأول للديناميكا الحرارية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:thermodynamics:v1` — **thermodynamics** · FR : Thermodynamique · AR : الديناميكا الحرارية
- `gen:first-law-of-thermodynamics:v1` — **first law of thermodynamics** · FR : Premier principe de la thermodynamique · AR : القانون الأول للديناميكا الحرارية
- `gen:second-law-of-thermodynamics:v1` — **second law of thermodynamics** · FR : Deuxième principe de la thermodynamique · AR : القانون الثاني للديناميكا الحرارية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : thermodynamics ; we check thermodynamics ; thermodynamics first ; the doctor checks thermodynamics first
  - modèle : First, the doctor checks thermodynamics.
- **q2.** What does the doctor check next?
  - accepté : first law of thermodynamics ; then we check first law of thermodynamics ; first law of thermodynamics next ; the doctor checks first law of thermodynamics next
  - modèle : Then, the doctor checks first law of thermodynamics.
- **q3.** Does the doctor check first law of thermodynamics before thermodynamics?
  - accepté : no ; no he does not ; no he doesn't ; he checks thermodynamics first
  - modèle : No, he checks thermodynamics first, then first law of thermodynamics.
  - hint (si erreur) — FR : Attention : Thermodynamique vient D'ABORD, ensuite Premier principe de la thermodynamique.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : second law of thermodynamics ; second law of thermodynamics first ; he checks second law of thermodynamics first
  - modèle : First, the doctor checks second law of thermodynamics.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check thermodynamics. Then, we check first law of thermodynamics.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Deuxième principe de la thermodynamique, ensuite Thermodynamique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check second law of thermodynamics. Then, we check thermodynamics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-011` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Hypothermie / Hyperthermie, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has hypothermia.
   - FR : Le patient du lit 3 a Hypothermie.
   - AR : مريض السرير ٣ عنده انخفاض الحرارة.
4. **EN** The patient in bed 5 has hyperthermia.
   - FR : Le patient du lit 5 a Hyperthermie.
   - AR : مريض السرير ٥ عنده ارتفاع الحرارة.
5. **EN** The patient in bed 5 does not have hypothermia.
   - FR : Le patient du lit 5 n'a pas de Hypothermie.
   - AR : مريض السرير ٥ ليس عنده انخفاض الحرارة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:hypothermia:v1` — **hypothermia** · FR : Hypothermie · AR : انخفاض الحرارة
- `gen:hyperthermia:v1` — **hyperthermia** · FR : Hyperthermie · AR : ارتفاع الحرارة
- `gen:thermodynamics:v1` — **thermodynamics** · FR : Thermodynamique · AR : الديناميكا الحرارية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hypothermia ; the patient with hypothermia
  - modèle : The patient in bed 3 has hypothermia.
- **q2.** What does the patient in bed 5 have?
  - accepté : hyperthermia
  - modèle : He has hyperthermia.
- **q3.** Does the patient in bed 5 have hypothermia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hypothermia ; he doesn't
  - modèle : No, he does not have hypothermia.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS hypothermie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : thermodynamics
  - modèle : He has thermodynamics.
- **Tt2.** Does the patient in bed 9 have hyperthermia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hyperthermia ; he doesn't
  - modèle : No, he does not have hyperthermia.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS hyperthermie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … hypothermia and hyperthermia. (have)` → have · modèle : The patients have hypothermia and hyperthermia.
- `The patient in bed 5 … not have hypothermia. (do)` → does · modèle : The patient in bed 5 does not have hypothermia.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has hypothermia. The patient in bed 5 has hyperthermia.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Thermodynamique, le lit 9 a Hypothermie et n'a pas Hyperthermie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has thermodynamics. The patient in bed 9 has hypothermia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
