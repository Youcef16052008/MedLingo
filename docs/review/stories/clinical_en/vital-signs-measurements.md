# Relecture — Vital Signs & Measurements (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-041` — Le cas de Youssef : Température / _Youssef: Temperature_

**Objectif :** Comprendre un cas : température présent, pouls absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Youssef talks to Amine, a medical student.
   - FR : Youssef parle avec Amine, étudiant·e en médecine.
   - AR : Youssef يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has a temperature.
   - FR : Il a Température.
   - AR : عنده درجة الحرارة.
4. **EN** He does not have a pulse.
   - FR : Il n'a pas de Pouls.
   - AR : ليس عنده النبض.
5. **EN** Amine asks, “Do you have a pulse?”
   - FR : Amine demande : « Avez-vous Pouls ? »
   - AR : يسأل Amine: «هل عندك النبض؟»
6. **EN** Youssef says, “No, I do not.”
   - FR : Youssef répond : « Non. »
   - AR : يجيب Youssef: «لا.»
7. **EN** Amine writes “Temperature” in the file.
   - FR : Amine écrit « Température » dans le dossier.
   - AR : يكتب Amine «Température» في الملف.

### Expressions ciblées
- `gen:temperature:v1` — **a temperature** · FR : Température · AR : درجة الحرارة
- `gen:pulse:v1` — **a pulse** · FR : Pouls · AR : النبض
- `gen:blood-pressure:v1` — **a blood pressure** · FR : Tension artérielle · AR : ضغط الدم

### Questions (acceptées / modèle / hint)
- **q1.** Does Youssef have temperature?
  - accepté : yes ; yes he does ; yes he has a temperature ; he has a temperature
  - modèle : Yes, he has a temperature.
- **q2.** Does he have pulse?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a pulse ; he doesn't
  - modèle : No, he does not have a pulse.
  - hint (si erreur) — FR : Attention : Youssef n'a PAS pouls.
- **q3.** Does he have temperature or pulse?
  - accepté : temperature ; a temperature ; he has a temperature
  - modèle : He has a temperature.
- **q4.** What does Youssef have?
  - accepté : temperature ; a temperature ; he has a temperature
  - modèle : He has a temperature.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have blood pressure?
  - accepté : yes ; yes he does ; he has a blood pressure
  - modèle : Yes, he has a blood pressure.
- **Tt2.** Does he have temperature?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a temperature ; he doesn't
  - modèle : No, he does not have a temperature.
  - hint (si erreur) — FR : Attention : il n'a PAS température.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … a temperature. (have)` → has · modèle : He has a temperature.
- `They … not have a pulse. (do)` → do · modèle : They do not have a pulse.
- `I … a temperature. (have)` → have · modèle : I have a temperature.

### Productions
- **Reformulation :** Rapporte le cas de Youssef en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has a temperature. He does not have a pulse.*
- **Transfert (contexte) :** Un autre patient a Tension artérielle. Il n'a pas de Température.
- Glossaire : Tension artérielle : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a blood pressure. He does not have a temperature.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-041` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Température et Pouls à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient.
   - FR : Hana est un·e patient·e.
   - AR : Hana مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check temperature.”
   - FR : « D'abord, nous vérifions Température. »
   - AR : «أولًا، نتحقق من درجة الحرارة.»
5. **EN** “Then, we check pulse.”
   - FR : « Ensuite, nous vérifions Pouls. »
   - AR : «ثم نتحقق من النبض.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:temperature:v1` — **a temperature** · FR : Température · AR : درجة الحرارة
- `gen:pulse:v1` — **a pulse** · FR : Pouls · AR : النبض
- `gen:blood-pressure:v1` — **a blood pressure** · FR : Tension artérielle · AR : ضغط الدم

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : temperature ; we check temperature ; temperature first ; the doctor checks temperature first
  - modèle : First, the doctor checks temperature.
- **q2.** What does the doctor check next?
  - accepté : pulse ; then we check pulse ; pulse next ; the doctor checks pulse next
  - modèle : Then, the doctor checks pulse.
- **q3.** Does the doctor check pulse before temperature?
  - accepté : no ; no he does not ; no he doesn't ; he checks temperature first
  - modèle : No, he checks temperature first, then pulse.
  - hint (si erreur) — FR : Attention : Température vient D'ABORD, ensuite Pouls.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : blood pressure ; blood pressure first ; he checks blood pressure first
  - modèle : First, the doctor checks blood pressure.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check temperature. Then, we check pulse.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Tension artérielle, ensuite Température, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check blood pressure. Then, we check temperature.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-041` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Température / Pouls, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a temperature.
   - FR : Le patient du lit 3 a Température.
   - AR : مريض السرير ٣ عنده درجة الحرارة.
4. **EN** The patient in bed 5 has a pulse.
   - FR : Le patient du lit 5 a Pouls.
   - AR : مريض السرير ٥ عنده النبض.
5. **EN** The patient in bed 5 does not have a temperature.
   - FR : Le patient du lit 5 n'a pas de Température.
   - AR : مريض السرير ٥ ليس عنده درجة الحرارة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:temperature:v1` — **a temperature** · FR : Température · AR : درجة الحرارة
- `gen:pulse:v1` — **a pulse** · FR : Pouls · AR : النبض
- `gen:blood-pressure:v1` — **a blood pressure** · FR : Tension artérielle · AR : ضغط الدم

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : temperature ; a temperature ; the patient with temperature
  - modèle : The patient in bed 3 has a temperature.
- **q2.** What does the patient in bed 5 have?
  - accepté : pulse ; a pulse ; he has a pulse
  - modèle : He has a pulse.
- **q3.** Does the patient in bed 5 have temperature?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a temperature ; he doesn't
  - modèle : No, he does not have a temperature.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS température — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : blood pressure ; a blood pressure ; he has a blood pressure
  - modèle : He has a blood pressure.
- **Tt2.** Does the patient in bed 9 have pulse?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a pulse ; he doesn't
  - modèle : No, he does not have a pulse.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS pouls.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a temperature and a pulse. (have)` → have · modèle : The patients have a temperature and a pulse.
- `The patient in bed 5 … not have a temperature. (do)` → does · modèle : The patient in bed 5 does not have a temperature.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a temperature. The patient in bed 5 has a pulse.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Tension artérielle, le lit 9 a Température et n'a pas Pouls.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a blood pressure. The patient in bed 9 has a temperature.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
