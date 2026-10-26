# Relecture — Physiologie Respiratoire (physio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `physio-case-006` — Le cas de Amel : Dyspnée / _Amel: Dyspnea_

**Objectif :** Comprendre un cas : dyspnée présent, apnée absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Amel talks to Lina, a medical student.
   - FR : Amel parle avec Lina, étudiant·e en médecine.
   - AR : Amel يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has dyspnea.
   - FR : Elle a Dyspnée.
   - AR : عنده ضيق التنفس.
4. **EN** She does not have apnea.
   - FR : Elle n'a pas de Apnée.
   - AR : ليس عنده انقطاع التنفس.
5. **EN** Lina asks, “Do you have apnea?”
   - FR : Lina demande : « Avez-vous Apnée ? »
   - AR : يسأل Lina: «هل عندك انقطاع التنفس؟»
6. **EN** Amel says, “No, I do not.”
   - FR : Amel répond : « Non. »
   - AR : يجيب Amel: «لا.»
7. **EN** Lina writes “Dyspnea” in the file.
   - FR : Lina écrit « Dyspnée » dans le dossier.
   - AR : يكتب Lina «Dyspnée» في الملف.

### Expressions ciblées
- `gen:dyspnea:v1` — **dyspnea** · FR : Dyspnée · AR : ضيق التنفس
- `gen:apnea:v1` — **apnea** · FR : Apnée · AR : انقطاع التنفس
- `gen:forced-expiratory-volume-in-1-second:v1` — **a forced expiratory volume in 1 second** · FR : Volume expiratoire maximal par seconde (VEMS) · AR : الحجم الزفيري الأقصى في الثانية الأولى (FEV1)

### Questions (acceptées / modèle / hint)
- **q1.** Does Amel have dyspnea?
  - accepté : yes ; yes she does ; yes she has dyspnea ; she has dyspnea
  - modèle : Yes, she has dyspnea.
- **q2.** Does she have apnea?
  - accepté : no ; no she does not ; no she doesn't ; she does not have apnea ; she doesn't
  - modèle : No, she does not have apnea.
  - hint (si erreur) — FR : Attention : Amel n'a PAS apnée.
- **q3.** Does she have dyspnea or apnea?
  - accepté : dyspnea ; she has dyspnea
  - modèle : She has dyspnea.
- **q4.** What does Amel have?
  - accepté : dyspnea ; she has dyspnea
  - modèle : She has dyspnea.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have forced expiratory volume in 1 second?
  - accepté : yes ; yes he does ; he has a forced expiratory volume in 1 second
  - modèle : Yes, he has a forced expiratory volume in 1 second.
- **Tt2.** Does he have dyspnea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have dyspnea ; he doesn't
  - modèle : No, he does not have dyspnea.
  - hint (si erreur) — FR : Attention : il n'a PAS dyspnée.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … dyspnea. (have)` → has · modèle : She has dyspnea.
- `They … not have apnea. (do)` → do · modèle : They do not have apnea.
- `I … dyspnea. (have)` → have · modèle : I have dyspnea.

### Productions
- **Reformulation :** Rapporte le cas de Amel en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has dyspnea. She does not have apnea.*
- **Transfert (contexte) :** Un autre patient a Volume expiratoire maximal par seconde (VEMS). Il n'a pas de Dyspnée.
- Glossaire : Volume expiratoire maximal par seconde (VEMS) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a forced expiratory volume in 1 second. He does not have dyspnea.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `physio-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Volume expiratoire maximal par seconde (VEMS) et Ventilation à un patient.
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
4. **EN** “First, we check forced expiratory volume in 1 second.”
   - FR : « D'abord, nous vérifions Volume expiratoire maximal par seconde (VEMS). »
   - AR : «أولًا، نتحقق من الحجم الزفيري الأقصى في الثانية الأولى (FEV1).»
5. **EN** “Then, we check ventilation.”
   - FR : « Ensuite, nous vérifions Ventilation. »
   - AR : «ثم نتحقق من التهوية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:forced-expiratory-volume-in-1-second:v1` — **a forced expiratory volume in 1 second** · FR : Volume expiratoire maximal par seconde (VEMS) · AR : الحجم الزفيري الأقصى في الثانية الأولى (FEV1)
- `gen:ventilation:v1` — **a ventilation** · FR : Ventilation · AR : التهوية
- `gen:inspiration:v1` — **an inspiration** · FR : Inspiration · AR : الشهيق

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : forced expiratory volume in 1 second ; we check forced expiratory volume in 1 second ; forced expiratory volume in 1 second first ; the doctor checks forced expiratory volume in 1 second first
  - modèle : First, the doctor checks forced expiratory volume in 1 second.
- **q2.** What does the doctor check next?
  - accepté : ventilation ; then we check ventilation ; ventilation next ; the doctor checks ventilation next
  - modèle : Then, the doctor checks ventilation.
- **q3.** Does the doctor check ventilation before forced expiratory volume in 1 second?
  - accepté : no ; no he does not ; no he doesn't ; he checks forced expiratory volume in 1 second first
  - modèle : No, he checks forced expiratory volume in 1 second first, then ventilation.
  - hint (si erreur) — FR : Attention : Volume expiratoire maximal par seconde (VEMS) vient D'ABORD, ensuite Ventilation.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : inspiration ; inspiration first ; he checks inspiration first
  - modèle : First, the doctor checks inspiration.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check forced expiratory volume in 1 second. Then, we check ventilation.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Inspiration, ensuite Volume expiratoire maximal par seconde (VEMS), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check inspiration. Then, we check forced expiratory volume in 1 second.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `physio-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Dyspnée / Apnée, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has dyspnea.
   - FR : Le patient du lit 3 a Dyspnée.
   - AR : مريض السرير ٣ عنده ضيق التنفس.
4. **EN** The patient in bed 5 has apnea.
   - FR : Le patient du lit 5 a Apnée.
   - AR : مريض السرير ٥ عنده انقطاع التنفس.
5. **EN** The patient in bed 5 does not have dyspnea.
   - FR : Le patient du lit 5 n'a pas de Dyspnée.
   - AR : مريض السرير ٥ ليس عنده ضيق التنفس.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:dyspnea:v1` — **dyspnea** · FR : Dyspnée · AR : ضيق التنفس
- `gen:apnea:v1` — **apnea** · FR : Apnée · AR : انقطاع التنفس
- `gen:forced-expiratory-volume-in-1-second:v1` — **a forced expiratory volume in 1 second** · FR : Volume expiratoire maximal par seconde (VEMS) · AR : الحجم الزفيري الأقصى في الثانية الأولى (FEV1)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : dyspnea ; the patient with dyspnea
  - modèle : The patient in bed 3 has dyspnea.
- **q2.** What does the patient in bed 5 have?
  - accepté : apnea
  - modèle : He has apnea.
- **q3.** Does the patient in bed 5 have dyspnea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have dyspnea ; he doesn't
  - modèle : No, he does not have dyspnea.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS dyspnée — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : forced expiratory volume in 1 second ; a forced expiratory volume in 1 second
  - modèle : He has a forced expiratory volume in 1 second.
- **Tt2.** Does the patient in bed 9 have apnea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have apnea ; he doesn't
  - modèle : No, he does not have apnea.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS apnée.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … dyspnea and apnea. (have)` → have · modèle : The patients have dyspnea and apnea.
- `The patient in bed 5 … not have dyspnea. (do)` → does · modèle : The patient in bed 5 does not have dyspnea.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has dyspnea. The patient in bed 5 has apnea.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Volume expiratoire maximal par seconde (VEMS), le lit 9 a Dyspnée et n'a pas Apnée.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a forced expiratory volume in 1 second. The patient in bed 9 has dyspnea.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
