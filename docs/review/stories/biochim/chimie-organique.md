# Relecture — Chimie Organique (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-005` — Le cas de Meriem : Hydrocarbure anormal(e) / _Meriem: the hydrocarbon is not normal_

**Objectif :** Comprendre des résultats : Chimie organique normal(e), Hydrocarbure anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient at the clinic.
   - FR : Meriem est un·e patient·e à la clinique.
   - AR : Meriem مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The organic chemistry is normal.”
   - FR : Le médecin dit : « Chimie organique est normal(e). »
   - AR : يقول الطبيب: «الكيمياء العضوية طبيعي/طبيعية.»
5. **EN** “But the hydrocarbon is not normal.”
   - FR : « Mais Hydrocarbure n'est pas normal(e). »
   - AR : «لكن الهيدروكربون غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the hydrocarbon normal?”
   - FR : Amine demande : « Hydrocarbure est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الهيدروكربون طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:organic-chemistry:v1` — **an organic chemistry** · FR : Chimie organique · AR : الكيمياء العضوية
- `gen:carbon:v1` — **a carbon** · FR : Carbone · AR : الكربون
- `gen:hydrocarbon:v1` — **a hydrocarbon** · FR : Hydrocarbure · AR : الهيدروكربون

### Questions (acceptées / modèle / hint)
- **q1.** Is the organic chemistry normal?
  - accepté : yes ; yes it is ; the organic chemistry is normal ; it is normal
  - modèle : Yes, the organic chemistry is normal.
- **q2.** Is the hydrocarbon normal?
  - accepté : no ; no it is not ; no it isn't ; the hydrocarbon is not normal ; it is not normal ; it isn't normal
  - modèle : No, the hydrocarbon is not normal.
  - hint (si erreur) — FR : Attention : Hydrocarbure n'est PAS normal(e).
- **q3.** Which one is not normal: the organic chemistry or the hydrocarbon?
  - accepté : hydrocarbon ; the hydrocarbon
  - modèle : The hydrocarbon is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the carbon normal?
  - accepté : yes ; yes it is ; the carbon is normal
  - modèle : Yes, the carbon is normal.
- **Tt2.** Is the organic chemistry normal?
  - accepté : no ; no it is not ; no it isn't ; the organic chemistry is not normal ; it is not normal
  - modèle : No, the organic chemistry is not normal.
  - hint (si erreur) — FR : Attention : Chimie organique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The organic chemistry … normal. (be)` → is · modèle : The organic chemistry is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The organic chemistry is… / The hydrocarbon is not… — modèle : *The organic chemistry is normal. The hydrocarbon is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Carbone normal(e), mais Chimie organique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The carbon is normal. The organic chemistry is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Chimie organique et Carbone à un patient.
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
4. **EN** “First, we check organic chemistry.”
   - FR : « D'abord, nous vérifions Chimie organique. »
   - AR : «أولًا، نتحقق من الكيمياء العضوية.»
5. **EN** “Then, we check carbon.”
   - FR : « Ensuite, nous vérifions Carbone. »
   - AR : «ثم نتحقق من الكربون.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:organic-chemistry:v1` — **an organic chemistry** · FR : Chimie organique · AR : الكيمياء العضوية
- `gen:carbon:v1` — **a carbon** · FR : Carbone · AR : الكربون
- `gen:hydrocarbon:v1` — **a hydrocarbon** · FR : Hydrocarbure · AR : الهيدروكربون

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : organic chemistry ; we check organic chemistry ; organic chemistry first ; the doctor checks organic chemistry first
  - modèle : First, the doctor checks organic chemistry.
- **q2.** What does the doctor check next?
  - accepté : carbon ; then we check carbon ; carbon next ; the doctor checks carbon next
  - modèle : Then, the doctor checks carbon.
- **q3.** Does the doctor check carbon before organic chemistry?
  - accepté : no ; no he does not ; no he doesn't ; he checks organic chemistry first
  - modèle : No, he checks organic chemistry first, then carbon.
  - hint (si erreur) — FR : Attention : Chimie organique vient D'ABORD, ensuite Carbone.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : hydrocarbon ; hydrocarbon first ; he checks hydrocarbon first
  - modèle : First, the doctor checks hydrocarbon.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check organic chemistry. Then, we check carbon.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Hydrocarbure, ensuite Chimie organique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check hydrocarbon. Then, we check organic chemistry.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Chimie organique / Carbone, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the organic chemistry.
   - FR : Le patient du lit 3 a Chimie organique.
   - AR : مريض السرير ٣ عنده الكيمياء العضوية.
4. **EN** The patient in bed 5 has a problem with the carbon.
   - FR : Le patient du lit 5 a Carbone.
   - AR : مريض السرير ٥ عنده الكربون.
5. **EN** The patient in bed 5 does not have a problem with the organic chemistry.
   - FR : Le patient du lit 5 n'a pas de Chimie organique.
   - AR : مريض السرير ٥ ليس عنده الكيمياء العضوية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:organic-chemistry:v1` — **an organic chemistry** · FR : Chimie organique · AR : الكيمياء العضوية
- `gen:carbon:v1` — **a carbon** · FR : Carbone · AR : الكربون
- `gen:hydrocarbon:v1` — **a hydrocarbon** · FR : Hydrocarbure · AR : الهيدروكربون

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : organic chemistry ; an organic chemistry ; the patient with organic chemistry
  - modèle : The patient in bed 3 has an organic chemistry.
- **q2.** What does the patient in bed 5 have?
  - accepté : carbon ; a problem with the carbon
  - modèle : He has a problem with the carbon.
- **q3.** Does the patient in bed 5 have organic chemistry?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the organic chemistry ; he doesn't
  - modèle : No, he does not have a problem with the organic chemistry.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS chimie organique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : hydrocarbon ; a problem with the hydrocarbon
  - modèle : He has a problem with the hydrocarbon.
- **Tt2.** Does the patient in bed 9 have carbon?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the carbon ; he doesn't
  - modèle : No, he does not have a problem with the carbon.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS carbone.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an organic chemistry and a carbon. (have)` → have · modèle : The patients have an organic chemistry and a carbon.
- `The patient in bed 5 … not have an organic chemistry. (do)` → does · modèle : The patient in bed 5 does not have an organic chemistry.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the organic chemistry. The patient in bed 5 has a problem with the carbon.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Hydrocarbure, le lit 9 a Chimie organique et n'a pas Carbone.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the hydrocarbon. The patient in bed 9 has a problem with the organic chemistry.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
