# Relecture — Sémiologie Digestive (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-005` — Le cas de Youssef : Douleur abdominale / _Youssef: Abdominal pain_

**Objectif :** Comprendre un cas : douleur abdominale présent, nausée absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Youssef talks to Amine, a medical student.
   - FR : Youssef parle avec Amine, étudiant·e en médecine.
   - AR : Youssef يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has abdominal pain.
   - FR : Il a Douleur abdominale.
   - AR : عنده ألم البطن.
4. **EN** He does not have nausea.
   - FR : Il n'a pas de Nausée.
   - AR : ليس عنده الغثيان.
5. **EN** Amine asks, “Do you have nausea?”
   - FR : Amine demande : « Avez-vous Nausée ? »
   - AR : يسأل Amine: «هل عندك الغثيان؟»
6. **EN** Youssef says, “No, I do not.”
   - FR : Youssef répond : « Non. »
   - AR : يجيب Youssef: «لا.»
7. **EN** Amine writes “Abdominal pain” in the file.
   - FR : Amine écrit « Douleur abdominale » dans le dossier.
   - AR : يكتب Amine «Douleur abdominale» في الملف.

### Expressions ciblées
- `gen:abdominal-pain:v1` — **abdominal pain** · FR : Douleur abdominale · AR : ألم البطن
- `gen:nausea:v1` — **nausea** · FR : Nausée · AR : الغثيان
- `gen:vomiting:v1` — **vomiting** · FR : Vomissement (Émèse) · AR : القيء

### Questions (acceptées / modèle / hint)
- **q1.** Does Youssef have abdominal pain?
  - accepté : yes ; yes he does ; yes he has abdominal pain ; he has abdominal pain
  - modèle : Yes, he has abdominal pain.
- **q2.** Does he have nausea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have nausea ; he doesn't
  - modèle : No, he does not have nausea.
  - hint (si erreur) — FR : Attention : Youssef n'a PAS nausée.
- **q3.** Does he have abdominal pain or nausea?
  - accepté : abdominal pain ; he has abdominal pain
  - modèle : He has abdominal pain.
- **q4.** What does Youssef have?
  - accepté : abdominal pain ; he has abdominal pain
  - modèle : He has abdominal pain.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have vomiting?
  - accepté : yes ; yes he does ; he has vomiting
  - modèle : Yes, he has vomiting.
- **Tt2.** Does he have abdominal pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have abdominal pain ; he doesn't
  - modèle : No, he does not have abdominal pain.
  - hint (si erreur) — FR : Attention : il n'a PAS douleur abdominale.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … abdominal pain. (have)` → has · modèle : He has abdominal pain.
- `They … not have nausea. (do)` → do · modèle : They do not have nausea.
- `I … abdominal pain. (have)` → have · modèle : I have abdominal pain.

### Productions
- **Reformulation :** Rapporte le cas de Youssef en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has abdominal pain. He does not have nausea.*
- **Transfert (contexte) :** Un autre patient a Vomissement (Émèse). Il n'a pas de Douleur abdominale.
- Glossaire : Vomissement (Émèse) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has vomiting. He does not have abdominal pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Douleur abdominale et Nausée à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check abdominal pain.”
   - FR : « D'abord, nous vérifions Douleur abdominale. »
   - AR : «أولًا، نتحقق من ألم البطن.»
5. **EN** “Then, we check nausea.”
   - FR : « Ensuite, nous vérifions Nausée. »
   - AR : «ثم نتحقق من الغثيان.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:abdominal-pain:v1` — **abdominal pain** · FR : Douleur abdominale · AR : ألم البطن
- `gen:nausea:v1` — **nausea** · FR : Nausée · AR : الغثيان
- `gen:vomiting:v1` — **vomiting** · FR : Vomissement (Émèse) · AR : القيء

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : abdominal pain ; we check abdominal pain ; abdominal pain first ; the doctor checks abdominal pain first
  - modèle : First, the doctor checks abdominal pain.
- **q2.** What does the doctor check next?
  - accepté : nausea ; then we check nausea ; nausea next ; the doctor checks nausea next
  - modèle : Then, the doctor checks nausea.
- **q3.** Does the doctor check nausea before abdominal pain?
  - accepté : no ; no he does not ; no he doesn't ; he checks abdominal pain first
  - modèle : No, he checks abdominal pain first, then nausea.
  - hint (si erreur) — FR : Attention : Douleur abdominale vient D'ABORD, ensuite Nausée.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : vomiting ; vomiting first ; he checks vomiting first
  - modèle : First, the doctor checks vomiting.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check abdominal pain. Then, we check nausea.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Vomissement (Émèse), ensuite Douleur abdominale, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check vomiting. Then, we check abdominal pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Douleur abdominale / Nausée, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has abdominal pain.
   - FR : Le patient du lit 3 a Douleur abdominale.
   - AR : مريض السرير ٣ عنده ألم البطن.
4. **EN** The patient in bed 5 has nausea.
   - FR : Le patient du lit 5 a Nausée.
   - AR : مريض السرير ٥ عنده الغثيان.
5. **EN** The patient in bed 5 does not have abdominal pain.
   - FR : Le patient du lit 5 n'a pas de Douleur abdominale.
   - AR : مريض السرير ٥ ليس عنده ألم البطن.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:abdominal-pain:v1` — **abdominal pain** · FR : Douleur abdominale · AR : ألم البطن
- `gen:nausea:v1` — **nausea** · FR : Nausée · AR : الغثيان
- `gen:vomiting:v1` — **vomiting** · FR : Vomissement (Émèse) · AR : القيء

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : abdominal pain ; the patient with abdominal pain
  - modèle : The patient in bed 3 has abdominal pain.
- **q2.** What does the patient in bed 5 have?
  - accepté : nausea
  - modèle : He has nausea.
- **q3.** Does the patient in bed 5 have abdominal pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have abdominal pain ; he doesn't
  - modèle : No, he does not have abdominal pain.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS douleur abdominale — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : vomiting
  - modèle : He has vomiting.
- **Tt2.** Does the patient in bed 9 have nausea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have nausea ; he doesn't
  - modèle : No, he does not have nausea.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS nausée.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … abdominal pain and nausea. (have)` → have · modèle : The patients have abdominal pain and nausea.
- `The patient in bed 5 … not have abdominal pain. (do)` → does · modèle : The patient in bed 5 does not have abdominal pain.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has abdominal pain. The patient in bed 5 has nausea.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Vomissement (Émèse), le lit 9 a Douleur abdominale et n'a pas Nausée.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has vomiting. The patient in bed 9 has abdominal pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
