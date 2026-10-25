# Relecture — Cardiology & Blood Vessels (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-004` — Le cas de Farid : Caillot sanguin anormal(e) / _Farid: the blood clot is not normal_

**Objectif :** Comprendre des résultats : Angine de poitrine normal(e), Caillot sanguin anormal(e).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The angina is normal.”
   - FR : Le médecin dit : « Angine de poitrine est normal(e). »
   - AR : يقول الطبيب: «الذبحة الصدرية طبيعي/طبيعية.»
5. **EN** “But the blood clot is not normal.”
   - FR : « Mais Caillot sanguin n'est pas normal(e). »
   - AR : «لكن جلطة دموية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the blood clot normal?”
   - FR : Lina demande : « Caillot sanguin est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل جلطة دموية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:angina:v1` — **an angina** · FR : Angine de poitrine · AR : الذبحة الصدرية
- `gen:atherosclerosis:v1` — **atherosclerosis** · FR : Athérosclérose · AR : تصلب الشرايين
- `gen:blood-clot:v1` — **a blood clot** · FR : Caillot sanguin · AR : جلطة دموية

### Questions (acceptées / modèle / hint)
- **q1.** Is the angina normal?
  - accepté : yes ; yes it is ; the angina is normal ; it is normal
  - modèle : Yes, the angina is normal.
- **q2.** Is the blood clot normal?
  - accepté : no ; no it is not ; no it isn't ; the blood clot is not normal ; it is not normal ; it isn't normal
  - modèle : No, the blood clot is not normal.
  - hint (si erreur) — FR : Attention : Caillot sanguin n'est PAS normal(e).
- **q3.** Which one is not normal: the angina or the blood clot?
  - accepté : blood clot ; the blood clot
  - modèle : The blood clot is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the atherosclerosis normal?
  - accepté : yes ; yes it is ; the atherosclerosis is normal
  - modèle : Yes, the atherosclerosis is normal.
- **Tt2.** Is the angina normal?
  - accepté : no ; no it is not ; no it isn't ; the angina is not normal ; it is not normal
  - modèle : No, the angina is not normal.
  - hint (si erreur) — FR : Attention : Angine de poitrine n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The angina … normal. (be)` → is · modèle : The angina is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The angina is… / The blood clot is not… — modèle : *The angina is normal. The blood clot is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Athérosclérose normal(e), mais Angine de poitrine anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The atherosclerosis is normal. The angina is not normal.*

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
- `gen:angina:v1` — **an angina** · FR : Angine de poitrine · AR : الذبحة الصدرية
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
3. **EN** The patient in bed 3 has a problem with the angina.
   - FR : Le patient du lit 3 a Angine de poitrine.
   - AR : مريض السرير ٣ عنده الذبحة الصدرية.
4. **EN** The patient in bed 5 has a problem with the atherosclerosis.
   - FR : Le patient du lit 5 a Athérosclérose.
   - AR : مريض السرير ٥ عنده تصلب الشرايين.
5. **EN** The patient in bed 5 does not have a problem with the angina.
   - FR : Le patient du lit 5 n'a pas de Angine de poitrine.
   - AR : مريض السرير ٥ ليس عنده الذبحة الصدرية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:angina:v1` — **an angina** · FR : Angine de poitrine · AR : الذبحة الصدرية
- `gen:atherosclerosis:v1` — **atherosclerosis** · FR : Athérosclérose · AR : تصلب الشرايين
- `gen:blood-clot:v1` — **a blood clot** · FR : Caillot sanguin · AR : جلطة دموية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : angina ; an angina ; the patient with angina
  - modèle : The patient in bed 3 has an angina.
- **q2.** What does the patient in bed 5 have?
  - accepté : atherosclerosis ; a problem with the atherosclerosis
  - modèle : He has a problem with the atherosclerosis.
- **q3.** Does the patient in bed 5 have angina?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the angina ; he doesn't
  - modèle : No, he does not have a problem with the angina.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS angine de poitrine — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : blood clot ; a problem with the blood clot
  - modèle : He has a problem with the blood clot.
- **Tt2.** Does the patient in bed 9 have atherosclerosis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the atherosclerosis ; he doesn't
  - modèle : No, he does not have a problem with the atherosclerosis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS athérosclérose.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an angina and atherosclerosis. (have)` → have · modèle : The patients have an angina and atherosclerosis.
- `The patient in bed 5 … not have an angina. (do)` → does · modèle : The patient in bed 5 does not have an angina.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the angina. The patient in bed 5 has a problem with the atherosclerosis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Caillot sanguin, le lit 9 a Angine de poitrine et n'a pas Athérosclérose.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the blood clot. The patient in bed 9 has a problem with the angina.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
