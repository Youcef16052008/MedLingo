# Relecture — Physiologie Cardiovasculaire (physio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `physio-case-001` — Le cas de Omar : Hypotension artérielle / _Omar: Hypotension_

**Objectif :** Comprendre un cas : hypotension artérielle présent, tachycardie absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Omar talks to Amine, a medical student.
   - FR : Omar parle avec Amine, étudiant·e en médecine.
   - AR : Omar يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has hypotension.
   - FR : Il a Hypotension artérielle.
   - AR : عنده انخفاض ضغط الدم.
4. **EN** He does not have tachycardia.
   - FR : Il n'a pas de Tachycardie.
   - AR : ليس عنده تسرع القلب.
5. **EN** Amine asks, “Do you have tachycardia?”
   - FR : Amine demande : « Avez-vous Tachycardie ? »
   - AR : يسأل Amine: «هل عندك تسرع القلب؟»
6. **EN** Omar says, “No, I do not.”
   - FR : Omar répond : « Non. »
   - AR : يجيب Omar: «لا.»
7. **EN** Amine writes “Hypotension” in the file.
   - FR : Amine écrit « Hypotension artérielle » dans le dossier.
   - AR : يكتب Amine «Hypotension artérielle» في الملف.

### Expressions ciblées
- `gen:hypotension:v1` — **hypotension** · FR : Hypotension artérielle · AR : انخفاض ضغط الدم
- `gen:tachycardia:v1` — **tachycardia** · FR : Tachycardie · AR : تسرع القلب
- `gen:cardiac-output:v1` — **a cardiac output** · FR : Débit cardiaque (Dc) · AR : النتاج القلبي / صبيب القلب

### Questions (acceptées / modèle / hint)
- **q1.** Does Omar have hypotension?
  - accepté : yes ; yes he does ; yes he has hypotension ; he has hypotension
  - modèle : Yes, he has hypotension.
- **q2.** Does he have tachycardia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have tachycardia ; he doesn't
  - modèle : No, he does not have tachycardia.
  - hint (si erreur) — FR : Attention : Omar n'a PAS tachycardie.
- **q3.** Does he have hypotension or tachycardia?
  - accepté : hypotension ; he has hypotension
  - modèle : He has hypotension.
- **q4.** What does Omar have?
  - accepté : hypotension ; he has hypotension
  - modèle : He has hypotension.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have cardiac output?
  - accepté : yes ; yes he does ; he has a cardiac output
  - modèle : Yes, he has a cardiac output.
- **Tt2.** Does he have hypotension?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hypotension ; he doesn't
  - modèle : No, he does not have hypotension.
  - hint (si erreur) — FR : Attention : il n'a PAS hypotension artérielle.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … hypotension. (have)` → has · modèle : He has hypotension.
- `They … not have tachycardia. (do)` → do · modèle : They do not have tachycardia.
- `I … hypotension. (have)` → have · modèle : I have hypotension.

### Productions
- **Reformulation :** Rapporte le cas de Omar en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has hypotension. He does not have tachycardia.*
- **Transfert (contexte) :** Un autre patient a Débit cardiaque (Dc). Il n'a pas de Hypotension artérielle.
- Glossaire : Débit cardiaque (Dc) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a cardiac output. He does not have hypotension.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `physio-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Débit cardiaque (Dc) et Loi de Frank-Starling à un patient.
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
4. **EN** “First, we check cardiac output.”
   - FR : « D'abord, nous vérifions Débit cardiaque (Dc). »
   - AR : «أولًا، نتحقق من النتاج القلبي / صبيب القلب.»
5. **EN** “Then, we check frank-starling law of the heart.”
   - FR : « Ensuite, nous vérifions Loi de Frank-Starling. »
   - AR : «ثم نتحقق من قانون فرانك ستارلينغ القلبي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cardiac-output:v1` — **a cardiac output** · FR : Débit cardiaque (Dc) · AR : النتاج القلبي / صبيب القلب
- `gen:frank-starling-law-of-the-heart:v1` — **a frank-starling law of the heart** · FR : Loi de Frank-Starling · AR : قانون فرانك ستارلينغ القلبي
- `gen:cardiac-cycle:v1` — **a cardiac cycle** · FR : Cycle cardiaque · AR : الدورة القلبية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cardiac output ; we check cardiac output ; cardiac output first ; the doctor checks cardiac output first
  - modèle : First, the doctor checks cardiac output.
- **q2.** What does the doctor check next?
  - accepté : frank-starling law of the heart ; then we check frank-starling law of the heart ; frank-starling law of the heart next ; the doctor checks frank-starling law of the heart next
  - modèle : Then, the doctor checks frank-starling law of the heart.
- **q3.** Does the doctor check frank-starling law of the heart before cardiac output?
  - accepté : no ; no he does not ; no he doesn't ; he checks cardiac output first
  - modèle : No, he checks cardiac output first, then frank-starling law of the heart.
  - hint (si erreur) — FR : Attention : Débit cardiaque (Dc) vient D'ABORD, ensuite Loi de Frank-Starling.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : cardiac cycle ; cardiac cycle first ; he checks cardiac cycle first
  - modèle : First, the doctor checks cardiac cycle.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cardiac output. Then, we check frank-starling law of the heart.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Cycle cardiaque, ensuite Débit cardiaque (Dc), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check cardiac cycle. Then, we check cardiac output.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `physio-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Hypotension artérielle / Tachycardie, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has hypotension.
   - FR : Le patient du lit 3 a Hypotension artérielle.
   - AR : مريض السرير ٣ عنده انخفاض ضغط الدم.
4. **EN** The patient in bed 5 has tachycardia.
   - FR : Le patient du lit 5 a Tachycardie.
   - AR : مريض السرير ٥ عنده تسرع القلب.
5. **EN** The patient in bed 5 does not have hypotension.
   - FR : Le patient du lit 5 n'a pas de Hypotension artérielle.
   - AR : مريض السرير ٥ ليس عنده انخفاض ضغط الدم.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:hypotension:v1` — **hypotension** · FR : Hypotension artérielle · AR : انخفاض ضغط الدم
- `gen:tachycardia:v1` — **tachycardia** · FR : Tachycardie · AR : تسرع القلب
- `gen:cardiac-output:v1` — **a cardiac output** · FR : Débit cardiaque (Dc) · AR : النتاج القلبي / صبيب القلب

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hypotension ; the patient with hypotension
  - modèle : The patient in bed 3 has hypotension.
- **q2.** What does the patient in bed 5 have?
  - accepté : tachycardia
  - modèle : He has tachycardia.
- **q3.** Does the patient in bed 5 have hypotension?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hypotension ; he doesn't
  - modèle : No, he does not have hypotension.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS hypotension artérielle — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : cardiac output ; a cardiac output
  - modèle : He has a cardiac output.
- **Tt2.** Does the patient in bed 9 have tachycardia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have tachycardia ; he doesn't
  - modèle : No, he does not have tachycardia.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS tachycardie.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … hypotension and tachycardia. (have)` → have · modèle : The patients have hypotension and tachycardia.
- `The patient in bed 5 … not have hypotension. (do)` → does · modèle : The patient in bed 5 does not have hypotension.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has hypotension. The patient in bed 5 has tachycardia.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Débit cardiaque (Dc), le lit 9 a Hypotension artérielle et n'a pas Tachycardie.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a cardiac output. The patient in bed 9 has hypotension.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
