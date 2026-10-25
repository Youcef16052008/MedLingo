# Relecture — Cardiology & Blood Vessels (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-004` — Le cas de Nadia : Angine de poitrine / _Nadia: Angina_

**Objectif :** Comprendre un cas : angine de poitrine présent, athérosclérose absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Nadia talks to Lina, a medical student.
   - FR : Nadia parle avec Lina, étudiant·e en médecine.
   - AR : Nadia يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a angina.
   - FR : Elle a Angine de poitrine.
   - AR : عنده الذبحة الصدرية.
4. **EN** She does not have atherosclerosis.
   - FR : Elle n'a pas de Athérosclérose.
   - AR : ليس عنده تصلب الشرايين.
5. **EN** Lina asks, “Do you have atherosclerosis?”
   - FR : Lina demande : « Avez-vous Athérosclérose ? »
   - AR : يسأل Lina: «هل عندك تصلب الشرايين؟»
6. **EN** Nadia says, “No, I do not.”
   - FR : Nadia répond : « Non. »
   - AR : يجيب Nadia: «لا.»
7. **EN** Lina writes “Angina” in the file.
   - FR : Lina écrit « Angine de poitrine » dans le dossier.
   - AR : يكتب Lina «Angine de poitrine» في الملف.

### Expressions ciblées
- `gen:angina:v1` — **a angina** · FR : Angine de poitrine · AR : الذبحة الصدرية
- `gen:atherosclerosis:v1` — **atherosclerosis** · FR : Athérosclérose · AR : تصلب الشرايين
- `gen:blood-clot:v1` — **a blood clot** · FR : Caillot sanguin · AR : جلطة دموية

### Questions (acceptées / modèle / hint)
- **q1.** Does Nadia have angina?
  - accepté : yes ; yes she does ; yes she has a angina ; she has a angina
  - modèle : Yes, she has a angina.
- **q2.** Does she have atherosclerosis?
  - accepté : no ; no she does not ; no she doesn't ; she does not have atherosclerosis ; she doesn't
  - modèle : No, she does not have atherosclerosis.
  - hint (si erreur) — FR : Attention : Nadia n'a PAS athérosclérose.
- **q3.** Does she have angina or atherosclerosis?
  - accepté : angina ; a angina ; she has a angina
  - modèle : She has a angina.
- **q4.** What does Nadia have?
  - accepté : angina ; a angina ; she has a angina
  - modèle : She has a angina.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have blood clot?
  - accepté : yes ; yes he does ; he has a blood clot
  - modèle : Yes, he has a blood clot.
- **Tt2.** Does he have angina?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a angina ; he doesn't
  - modèle : No, he does not have a angina.
  - hint (si erreur) — FR : Attention : il n'a PAS angine de poitrine.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a angina. (have)` → has · modèle : She has a angina.
- `They … not have atherosclerosis. (do)` → do · modèle : They do not have atherosclerosis.
- `I … a angina. (have)` → have · modèle : I have a angina.

### Productions
- **Reformulation :** Rapporte le cas de Nadia en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a angina. She does not have atherosclerosis.*
- **Transfert (contexte) :** Un autre patient a Caillot sanguin. Il n'a pas de Angine de poitrine.
- Glossaire : Caillot sanguin : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a blood clot. He does not have a angina.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Angine de poitrine et Athérosclérose à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient.
   - FR : Sofiane est un·e patient·e.
   - AR : Sofiane مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check angina.”
   - FR : « D'abord, nous vérifions Angine de poitrine. »
   - AR : «أولًا، نتحقق من الذبحة الصدرية.»
5. **EN** “Then, we check atherosclerosis.”
   - FR : « Ensuite, nous vérifions Athérosclérose. »
   - AR : «ثم نتحقق من تصلب الشرايين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:angina:v1` — **a angina** · FR : Angine de poitrine · AR : الذبحة الصدرية
- `gen:atherosclerosis:v1` — **atherosclerosis** · FR : Athérosclérose · AR : تصلب الشرايين
- `gen:blood-clot:v1` — **a blood clot** · FR : Caillot sanguin · AR : جلطة دموية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : angina ; we check angina ; angina first ; the doctor checks angina first
  - modèle : First, the doctor checks angina.
- **q2.** What does the doctor check next?
  - accepté : atherosclerosis ; then we check atherosclerosis ; atherosclerosis next ; the doctor checks atherosclerosis next
  - modèle : Then, the doctor checks atherosclerosis.
- **q3.** Does the doctor check atherosclerosis before angina?
  - accepté : no ; no he does not ; no he doesn't ; he checks angina first
  - modèle : No, he checks angina first, then atherosclerosis.
  - hint (si erreur) — FR : Attention : Angine de poitrine vient D'ABORD, ensuite Athérosclérose.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : blood clot ; blood clot first ; he checks blood clot first
  - modèle : First, the doctor checks blood clot.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check angina. Then, we check atherosclerosis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Caillot sanguin, ensuite Angine de poitrine, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check blood clot. Then, we check angina.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Angine de poitrine / Athérosclérose, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a angina.
   - FR : Le patient du lit 3 a Angine de poitrine.
   - AR : مريض السرير ٣ عنده الذبحة الصدرية.
4. **EN** The patient in bed 5 has atherosclerosis.
   - FR : Le patient du lit 5 a Athérosclérose.
   - AR : مريض السرير ٥ عنده تصلب الشرايين.
5. **EN** The patient in bed 5 does not have a angina.
   - FR : Le patient du lit 5 n'a pas de Angine de poitrine.
   - AR : مريض السرير ٥ ليس عنده الذبحة الصدرية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:angina:v1` — **a angina** · FR : Angine de poitrine · AR : الذبحة الصدرية
- `gen:atherosclerosis:v1` — **atherosclerosis** · FR : Athérosclérose · AR : تصلب الشرايين
- `gen:blood-clot:v1` — **a blood clot** · FR : Caillot sanguin · AR : جلطة دموية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : angina ; a angina ; the patient with angina
  - modèle : The patient in bed 3 has a angina.
- **q2.** What does the patient in bed 5 have?
  - accepté : atherosclerosis ; he has atherosclerosis
  - modèle : He has atherosclerosis.
- **q3.** Does the patient in bed 5 have angina?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a angina ; he doesn't
  - modèle : No, he does not have a angina.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS angine de poitrine — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : blood clot ; a blood clot ; he has a blood clot
  - modèle : He has a blood clot.
- **Tt2.** Does the patient in bed 9 have atherosclerosis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have atherosclerosis ; he doesn't
  - modèle : No, he does not have atherosclerosis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS athérosclérose.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a angina and atherosclerosis. (have)` → have · modèle : The patients have a angina and atherosclerosis.
- `The patient in bed 5 … not have a angina. (do)` → does · modèle : The patient in bed 5 does not have a angina.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a angina. The patient in bed 5 has atherosclerosis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Caillot sanguin, le lit 9 a Angine de poitrine et n'a pas Athérosclérose.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a blood clot. The patient in bed 9 has a angina.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
