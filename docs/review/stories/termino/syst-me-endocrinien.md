# Relecture — Système Endocrinien (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-028` — Le cas de Farid : Glande exocrine anormal(e) / _Farid: the exocrine gland is not normal_

**Objectif :** Comprendre des résultats : Hormone normal(e), Glande exocrine anormal(e).
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
4. **EN** The doctor says, “The hormone is normal.”
   - FR : Le médecin dit : « Hormone est normal(e). »
   - AR : يقول الطبيب: «الهرمون طبيعي/طبيعية.»
5. **EN** “But the exocrine gland is not normal.”
   - FR : « Mais Glande exocrine n'est pas normal(e). »
   - AR : «لكن الغدة خارجية الإفراز غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the exocrine gland normal?”
   - FR : Lina demande : « Glande exocrine est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الغدة خارجية الإفراز طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:hormone:v1` — **a hormone** · FR : Hormone · AR : الهرمون
- `gen:endocrine-gland:v1` — **an endocrine gland** · FR : Glande endocrine · AR : الغدة الصماء
- `gen:exocrine-gland:v1` — **an exocrine gland** · FR : Glande exocrine · AR : الغدة خارجية الإفراز

### Questions (acceptées / modèle / hint)
- **q1.** Is the hormone normal?
  - accepté : yes ; yes it is ; the hormone is normal ; it is normal
  - modèle : Yes, the hormone is normal.
- **q2.** Is the exocrine gland normal?
  - accepté : no ; no it is not ; no it isn't ; the exocrine gland is not normal ; it is not normal ; it isn't normal
  - modèle : No, the exocrine gland is not normal.
  - hint (si erreur) — FR : Attention : Glande exocrine n'est PAS normal(e).
- **q3.** Which one is not normal: the hormone or the exocrine gland?
  - accepté : exocrine gland ; the exocrine gland
  - modèle : The exocrine gland is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the endocrine gland normal?
  - accepté : yes ; yes it is ; the endocrine gland is normal
  - modèle : Yes, the endocrine gland is normal.
- **Tt2.** Is the hormone normal?
  - accepté : no ; no it is not ; no it isn't ; the hormone is not normal ; it is not normal
  - modèle : No, the hormone is not normal.
  - hint (si erreur) — FR : Attention : Hormone n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The hormone … normal. (be)` → is · modèle : The hormone is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The hormone is… / The exocrine gland is not… — modèle : *The hormone is normal. The exocrine gland is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Glande endocrine normal(e), mais Hormone anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The endocrine gland is normal. The hormone is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-028` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Hormone et Glande endocrine à un patient.
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
4. **EN** “First, we check hormone.”
   - FR : « D'abord, nous vérifions Hormone. »
   - AR : «أولًا، نتحقق من الهرمون.»
5. **EN** “Then, we check endocrine gland.”
   - FR : « Ensuite, nous vérifions Glande endocrine. »
   - AR : «ثم نتحقق من الغدة الصماء.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:hormone:v1` — **a hormone** · FR : Hormone · AR : الهرمون
- `gen:endocrine-gland:v1` — **an endocrine gland** · FR : Glande endocrine · AR : الغدة الصماء
- `gen:exocrine-gland:v1` — **an exocrine gland** · FR : Glande exocrine · AR : الغدة خارجية الإفراز

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : hormone ; we check hormone ; hormone first ; the doctor checks hormone first
  - modèle : First, the doctor checks hormone.
- **q2.** What does the doctor check next?
  - accepté : endocrine gland ; then we check endocrine gland ; endocrine gland next ; the doctor checks endocrine gland next
  - modèle : Then, the doctor checks endocrine gland.
- **q3.** Does the doctor check endocrine gland before hormone?
  - accepté : no ; no he does not ; no he doesn't ; he checks hormone first
  - modèle : No, he checks hormone first, then endocrine gland.
  - hint (si erreur) — FR : Attention : Hormone vient D'ABORD, ensuite Glande endocrine.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : exocrine gland ; exocrine gland first ; he checks exocrine gland first
  - modèle : First, the doctor checks exocrine gland.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check hormone. Then, we check endocrine gland.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Glande exocrine, ensuite Hormone, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check exocrine gland. Then, we check hormone.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-028` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Hormone / Glande endocrine, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the hormone.
   - FR : Le patient du lit 3 a Hormone.
   - AR : مريض السرير ٣ عنده الهرمون.
4. **EN** The patient in bed 5 has a problem with the endocrine gland.
   - FR : Le patient du lit 5 a Glande endocrine.
   - AR : مريض السرير ٥ عنده الغدة الصماء.
5. **EN** The patient in bed 5 does not have a problem with the hormone.
   - FR : Le patient du lit 5 n'a pas de Hormone.
   - AR : مريض السرير ٥ ليس عنده الهرمون.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:hormone:v1` — **a hormone** · FR : Hormone · AR : الهرمون
- `gen:endocrine-gland:v1` — **an endocrine gland** · FR : Glande endocrine · AR : الغدة الصماء
- `gen:exocrine-gland:v1` — **an exocrine gland** · FR : Glande exocrine · AR : الغدة خارجية الإفراز

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hormone ; a hormone ; the patient with hormone
  - modèle : The patient in bed 3 has a hormone.
- **q2.** What does the patient in bed 5 have?
  - accepté : endocrine gland ; a problem with the endocrine gland
  - modèle : He has a problem with the endocrine gland.
- **q3.** Does the patient in bed 5 have hormone?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the hormone ; he doesn't
  - modèle : No, he does not have a problem with the hormone.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS hormone — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : exocrine gland ; a problem with the exocrine gland
  - modèle : He has a problem with the exocrine gland.
- **Tt2.** Does the patient in bed 9 have endocrine gland?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the endocrine gland ; he doesn't
  - modèle : No, he does not have a problem with the endocrine gland.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS glande endocrine.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a hormone and an endocrine gland. (have)` → have · modèle : The patients have a hormone and an endocrine gland.
- `The patient in bed 5 … not have a hormone. (do)` → does · modèle : The patient in bed 5 does not have a hormone.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the hormone. The patient in bed 5 has a problem with the endocrine gland.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Glande exocrine, le lit 9 a Hormone et n'a pas Glande endocrine.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the exocrine gland. The patient in bed 9 has a problem with the hormone.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
