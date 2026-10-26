# Relecture — Pathologie Cardiovasculaire (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-006` — Le cas de Amel : Péricardite / _Amel: Pericarditis_

**Objectif :** Comprendre un cas : péricardite présent, endocardite absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Amel talks to Lina, a medical student.
   - FR : Amel parle avec Lina, étudiant·e en médecine.
   - AR : Amel يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has pericarditis.
   - FR : Elle a Péricardite.
   - AR : عنده التهاب التامور.
4. **EN** She does not have endocarditis.
   - FR : Elle n'a pas de Endocardite.
   - AR : ليس عنده التهاب الشغاف.
5. **EN** Lina asks, “Do you have endocarditis?”
   - FR : Lina demande : « Avez-vous Endocardite ? »
   - AR : يسأل Lina: «هل عندك التهاب الشغاف؟»
6. **EN** Amel says, “No, I do not.”
   - FR : Amel répond : « Non. »
   - AR : يجيب Amel: «لا.»
7. **EN** Lina writes “Pericarditis” in the file.
   - FR : Lina écrit « Péricardite » dans le dossier.
   - AR : يكتب Lina «Péricardite» في الملف.

### Expressions ciblées
- `gen:pericarditis:v1` — **pericarditis** · FR : Péricardite · AR : التهاب التامور
- `gen:endocarditis:v1` — **endocarditis** · FR : Endocardite · AR : التهاب الشغاف
- `gen:myocardial-infarction:v1` — **a myocardial infarction** · FR : Infarctus du myocarde (IDM) · AR : احتشاء عضلة القلب

### Questions (acceptées / modèle / hint)
- **q1.** Does Amel have pericarditis?
  - accepté : yes ; yes she does ; yes she has pericarditis ; she has pericarditis
  - modèle : Yes, she has pericarditis.
- **q2.** Does she have endocarditis?
  - accepté : no ; no she does not ; no she doesn't ; she does not have endocarditis ; she doesn't
  - modèle : No, she does not have endocarditis.
  - hint (si erreur) — FR : Attention : Amel n'a PAS endocardite.
- **q3.** Does she have pericarditis or endocarditis?
  - accepté : pericarditis ; she has pericarditis
  - modèle : She has pericarditis.
- **q4.** What does Amel have?
  - accepté : pericarditis ; she has pericarditis
  - modèle : She has pericarditis.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have myocardial infarction?
  - accepté : yes ; yes he does ; he has a myocardial infarction
  - modèle : Yes, he has a myocardial infarction.
- **Tt2.** Does he have pericarditis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have pericarditis ; he doesn't
  - modèle : No, he does not have pericarditis.
  - hint (si erreur) — FR : Attention : il n'a PAS péricardite.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … pericarditis. (have)` → has · modèle : She has pericarditis.
- `They … not have endocarditis. (do)` → do · modèle : They do not have endocarditis.
- `I … pericarditis. (have)` → have · modèle : I have pericarditis.

### Productions
- **Reformulation :** Rapporte le cas de Amel en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has pericarditis. She does not have endocarditis.*
- **Transfert (contexte) :** Un autre patient a Infarctus du myocarde (IDM). Il n'a pas de Péricardite.
- Glossaire : Infarctus du myocarde (IDM) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a myocardial infarction. He does not have pericarditis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Infarctus du myocarde (IDM) et Nécrose de coagulation (myocarde) à un patient.
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
4. **EN** “First, we check myocardial infarction.”
   - FR : « D'abord, nous vérifions Infarctus du myocarde (IDM). »
   - AR : «أولًا، نتحقق من احتشاء عضلة القلب.»
5. **EN** “Then, we check coagulative necrosis.”
   - FR : « Ensuite, nous vérifions Nécrose de coagulation (myocarde). »
   - AR : «ثم نتحقق من النخر التخثري (عضلة القلب).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:myocardial-infarction:v1` — **a myocardial infarction** · FR : Infarctus du myocarde (IDM) · AR : احتشاء عضلة القلب
- `gen:coagulative-necrosis:v1` — **coagulative necrosis** · FR : Nécrose de coagulation (myocarde) · AR : النخر التخثري (عضلة القلب)
- `gen:cardiac-hypertrophy:v1` — **a cardiac hypertrophy** · FR : Hypertrophie cardiaque · AR : تضخم عضلة القلب

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : myocardial infarction ; we check myocardial infarction ; myocardial infarction first ; the doctor checks myocardial infarction first
  - modèle : First, the doctor checks myocardial infarction.
- **q2.** What does the doctor check next?
  - accepté : coagulative necrosis ; then we check coagulative necrosis ; coagulative necrosis next ; the doctor checks coagulative necrosis next
  - modèle : Then, the doctor checks coagulative necrosis.
- **q3.** Does the doctor check coagulative necrosis before myocardial infarction?
  - accepté : no ; no he does not ; no he doesn't ; he checks myocardial infarction first
  - modèle : No, he checks myocardial infarction first, then coagulative necrosis.
  - hint (si erreur) — FR : Attention : Infarctus du myocarde (IDM) vient D'ABORD, ensuite Nécrose de coagulation (myocarde).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : cardiac hypertrophy ; cardiac hypertrophy first ; he checks cardiac hypertrophy first
  - modèle : First, the doctor checks cardiac hypertrophy.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check myocardial infarction. Then, we check coagulative necrosis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Hypertrophie cardiaque, ensuite Infarctus du myocarde (IDM), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check cardiac hypertrophy. Then, we check myocardial infarction.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Péricardite / Endocardite, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has pericarditis.
   - FR : Le patient du lit 3 a Péricardite.
   - AR : مريض السرير ٣ عنده التهاب التامور.
4. **EN** The patient in bed 5 has endocarditis.
   - FR : Le patient du lit 5 a Endocardite.
   - AR : مريض السرير ٥ عنده التهاب الشغاف.
5. **EN** The patient in bed 5 does not have pericarditis.
   - FR : Le patient du lit 5 n'a pas de Péricardite.
   - AR : مريض السرير ٥ ليس عنده التهاب التامور.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:pericarditis:v1` — **pericarditis** · FR : Péricardite · AR : التهاب التامور
- `gen:endocarditis:v1` — **endocarditis** · FR : Endocardite · AR : التهاب الشغاف
- `gen:myocardial-infarction:v1` — **a myocardial infarction** · FR : Infarctus du myocarde (IDM) · AR : احتشاء عضلة القلب

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : pericarditis ; the patient with pericarditis
  - modèle : The patient in bed 3 has pericarditis.
- **q2.** What does the patient in bed 5 have?
  - accepté : endocarditis
  - modèle : He has endocarditis.
- **q3.** Does the patient in bed 5 have pericarditis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have pericarditis ; he doesn't
  - modèle : No, he does not have pericarditis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS péricardite — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : myocardial infarction ; a myocardial infarction
  - modèle : He has a myocardial infarction.
- **Tt2.** Does the patient in bed 9 have endocarditis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have endocarditis ; he doesn't
  - modèle : No, he does not have endocarditis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS endocardite.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … pericarditis and endocarditis. (have)` → have · modèle : The patients have pericarditis and endocarditis.
- `The patient in bed 5 … not have pericarditis. (do)` → does · modèle : The patient in bed 5 does not have pericarditis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has pericarditis. The patient in bed 5 has endocarditis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Infarctus du myocarde (IDM), le lit 9 a Péricardite et n'a pas Endocardite.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a myocardial infarction. The patient in bed 9 has pericarditis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
