# Relecture — Immunologie Microbienne (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-004` — Le cas de Farid : Immunité adaptative anormal(e) / _Farid: the adaptive immunity is not normal_

**Objectif :** Comprendre des résultats : Réponse immunitaire normal(e), Immunité adaptative anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

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
4. **EN** The doctor says, “The immune response is normal.”
   - FR : Le médecin dit : « Réponse immunitaire est normal(e). »
   - AR : يقول الطبيب: «الاستجابة المناعية طبيعي/طبيعية.»
5. **EN** “But the adaptive immunity is not normal.”
   - FR : « Mais Immunité adaptative n'est pas normal(e). »
   - AR : «لكن المناعة التكيفية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the adaptive immunity normal?”
   - FR : Lina demande : « Immunité adaptative est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل المناعة التكيفية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:immune-response:v1` — **an immune response** · FR : Réponse immunitaire · AR : الاستجابة المناعية
- `gen:innate-immunity:v1` — **an innate immunity** · FR : Immunité innée · AR : المناعة الفطرية
- `gen:adaptive-immunity:v1` — **an adaptive immunity** · FR : Immunité adaptative · AR : المناعة التكيفية

### Questions (acceptées / modèle / hint)
- **q1.** Is the immune response normal?
  - accepté : yes ; yes it is ; the immune response is normal ; it is normal
  - modèle : Yes, the immune response is normal.
- **q2.** Is the adaptive immunity normal?
  - accepté : no ; no it is not ; no it isn't ; the adaptive immunity is not normal ; it is not normal ; it isn't normal
  - modèle : No, the adaptive immunity is not normal.
  - hint (si erreur) — FR : Attention : Immunité adaptative n'est PAS normal(e).
- **q3.** Which one is not normal: the immune response or the adaptive immunity?
  - accepté : adaptive immunity ; the adaptive immunity
  - modèle : The adaptive immunity is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the innate immunity normal?
  - accepté : yes ; yes it is ; the innate immunity is normal
  - modèle : Yes, the innate immunity is normal.
- **Tt2.** Is the immune response normal?
  - accepté : no ; no it is not ; no it isn't ; the immune response is not normal ; it is not normal
  - modèle : No, the immune response is not normal.
  - hint (si erreur) — FR : Attention : Réponse immunitaire n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The immune response … normal. (be)` → is · modèle : The immune response is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The immune response is… / The adaptive immunity is not… — modèle : *The immune response is normal. The adaptive immunity is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Immunité innée normal(e), mais Réponse immunitaire anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The innate immunity is normal. The immune response is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Réponse immunitaire et Immunité innée à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check immune response.”
   - FR : « D'abord, nous vérifions Réponse immunitaire. »
   - AR : «أولًا، نتحقق من الاستجابة المناعية.»
5. **EN** “Then, we check innate immunity.”
   - FR : « Ensuite, nous vérifions Immunité innée. »
   - AR : «ثم نتحقق من المناعة الفطرية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:immune-response:v1` — **an immune response** · FR : Réponse immunitaire · AR : الاستجابة المناعية
- `gen:innate-immunity:v1` — **an innate immunity** · FR : Immunité innée · AR : المناعة الفطرية
- `gen:adaptive-immunity:v1` — **an adaptive immunity** · FR : Immunité adaptative · AR : المناعة التكيفية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : immune response ; we check immune response ; immune response first ; the doctor checks immune response first
  - modèle : First, the doctor checks immune response.
- **q2.** What does the doctor check next?
  - accepté : innate immunity ; then we check innate immunity ; innate immunity next ; the doctor checks innate immunity next
  - modèle : Then, the doctor checks innate immunity.
- **q3.** Does the doctor check innate immunity before immune response?
  - accepté : no ; no he does not ; no he doesn't ; he checks immune response first
  - modèle : No, he checks immune response first, then innate immunity.
  - hint (si erreur) — FR : Attention : Réponse immunitaire vient D'ABORD, ensuite Immunité innée.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : adaptive immunity ; adaptive immunity first ; he checks adaptive immunity first
  - modèle : First, the doctor checks adaptive immunity.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check immune response. Then, we check innate immunity.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Immunité adaptative, ensuite Réponse immunitaire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check adaptive immunity. Then, we check immune response.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Réponse immunitaire / Immunité innée, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the immune response.
   - FR : Le patient du lit 3 a Réponse immunitaire.
   - AR : مريض السرير ٣ عنده الاستجابة المناعية.
4. **EN** The patient in bed 5 has a problem with the innate immunity.
   - FR : Le patient du lit 5 a Immunité innée.
   - AR : مريض السرير ٥ عنده المناعة الفطرية.
5. **EN** The patient in bed 5 does not have a problem with the immune response.
   - FR : Le patient du lit 5 n'a pas de Réponse immunitaire.
   - AR : مريض السرير ٥ ليس عنده الاستجابة المناعية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:immune-response:v1` — **an immune response** · FR : Réponse immunitaire · AR : الاستجابة المناعية
- `gen:innate-immunity:v1` — **an innate immunity** · FR : Immunité innée · AR : المناعة الفطرية
- `gen:adaptive-immunity:v1` — **an adaptive immunity** · FR : Immunité adaptative · AR : المناعة التكيفية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : immune response ; an immune response ; the patient with immune response
  - modèle : The patient in bed 3 has an immune response.
- **q2.** What does the patient in bed 5 have?
  - accepté : innate immunity ; a problem with the innate immunity
  - modèle : He has a problem with the innate immunity.
- **q3.** Does the patient in bed 5 have immune response?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the immune response ; he doesn't
  - modèle : No, he does not have a problem with the immune response.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS réponse immunitaire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : adaptive immunity ; a problem with the adaptive immunity
  - modèle : He has a problem with the adaptive immunity.
- **Tt2.** Does the patient in bed 9 have innate immunity?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the innate immunity ; he doesn't
  - modèle : No, he does not have a problem with the innate immunity.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS immunité innée.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an immune response and an innate immunity. (have)` → have · modèle : The patients have an immune response and an innate immunity.
- `The patient in bed 5 … not have an immune response. (do)` → does · modèle : The patient in bed 5 does not have an immune response.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the immune response. The patient in bed 5 has a problem with the innate immunity.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Immunité adaptative, le lit 9 a Réponse immunitaire et n'a pas Immunité innée.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the adaptive immunity. The patient in bed 9 has a problem with the immune response.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
