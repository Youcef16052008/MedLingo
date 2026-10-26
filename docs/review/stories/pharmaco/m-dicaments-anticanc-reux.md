# Relecture — Médicaments Anticancéreux (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-004` — Le cas de Farid : Antimétabolite (cancer) anormal(e) / _Farid: the antimetabolite is not normal_

**Objectif :** Comprendre des résultats : Chimiothérapie normal(e), Antimétabolite (cancer) anormal(e).
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
4. **EN** The doctor says, “The chemotherapy is normal.”
   - FR : Le médecin dit : « Chimiothérapie est normal(e). »
   - AR : يقول الطبيب: «العلاج الكيميائي طبيعي/طبيعية.»
5. **EN** “But the antimetabolite is not normal.”
   - FR : « Mais Antimétabolite (cancer) n'est pas normal(e). »
   - AR : «لكن مضاد الاستقلاب (السرطان) غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the antimetabolite normal?”
   - FR : Lina demande : « Antimétabolite (cancer) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل مضاد الاستقلاب (السرطان) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:chemotherapy:v1` — **a chemotherapy** · FR : Chimiothérapie · AR : العلاج الكيميائي
- `gen:alkylating-agent:v1` — **an alkylating agent** · FR : Agent alkylant · AR : العامل الألكيلي
- `gen:antimetabolite:v1` — **an antimetabolite** · FR : Antimétabolite (cancer) · AR : مضاد الاستقلاب (السرطان)

### Questions (acceptées / modèle / hint)
- **q1.** Is the chemotherapy normal?
  - accepté : yes ; yes it is ; the chemotherapy is normal ; it is normal
  - modèle : Yes, the chemotherapy is normal.
- **q2.** Is the antimetabolite normal?
  - accepté : no ; no it is not ; no it isn't ; the antimetabolite is not normal ; it is not normal ; it isn't normal
  - modèle : No, the antimetabolite is not normal.
  - hint (si erreur) — FR : Attention : Antimétabolite (cancer) n'est PAS normal(e).
- **q3.** Which one is not normal: the chemotherapy or the antimetabolite?
  - accepté : antimetabolite ; the antimetabolite
  - modèle : The antimetabolite is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the alkylating agent normal?
  - accepté : yes ; yes it is ; the alkylating agent is normal
  - modèle : Yes, the alkylating agent is normal.
- **Tt2.** Is the chemotherapy normal?
  - accepté : no ; no it is not ; no it isn't ; the chemotherapy is not normal ; it is not normal
  - modèle : No, the chemotherapy is not normal.
  - hint (si erreur) — FR : Attention : Chimiothérapie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The chemotherapy … normal. (be)` → is · modèle : The chemotherapy is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The chemotherapy is… / The antimetabolite is not… — modèle : *The chemotherapy is normal. The antimetabolite is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Agent alkylant normal(e), mais Chimiothérapie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The alkylating agent is normal. The chemotherapy is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Chimiothérapie et Agent alkylant à un patient.
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
4. **EN** “First, we check chemotherapy.”
   - FR : « D'abord, nous vérifions Chimiothérapie. »
   - AR : «أولًا، نتحقق من العلاج الكيميائي.»
5. **EN** “Then, we check alkylating agent.”
   - FR : « Ensuite, nous vérifions Agent alkylant. »
   - AR : «ثم نتحقق من العامل الألكيلي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:chemotherapy:v1` — **a chemotherapy** · FR : Chimiothérapie · AR : العلاج الكيميائي
- `gen:alkylating-agent:v1` — **an alkylating agent** · FR : Agent alkylant · AR : العامل الألكيلي
- `gen:antimetabolite:v1` — **an antimetabolite** · FR : Antimétabolite (cancer) · AR : مضاد الاستقلاب (السرطان)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : chemotherapy ; we check chemotherapy ; chemotherapy first ; the doctor checks chemotherapy first
  - modèle : First, the doctor checks chemotherapy.
- **q2.** What does the doctor check next?
  - accepté : alkylating agent ; then we check alkylating agent ; alkylating agent next ; the doctor checks alkylating agent next
  - modèle : Then, the doctor checks alkylating agent.
- **q3.** Does the doctor check alkylating agent before chemotherapy?
  - accepté : no ; no he does not ; no he doesn't ; he checks chemotherapy first
  - modèle : No, he checks chemotherapy first, then alkylating agent.
  - hint (si erreur) — FR : Attention : Chimiothérapie vient D'ABORD, ensuite Agent alkylant.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : antimetabolite ; antimetabolite first ; he checks antimetabolite first
  - modèle : First, the doctor checks antimetabolite.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check chemotherapy. Then, we check alkylating agent.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Antimétabolite (cancer), ensuite Chimiothérapie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check antimetabolite. Then, we check chemotherapy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Chimiothérapie / Agent alkylant, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the chemotherapy.
   - FR : Le patient du lit 3 a Chimiothérapie.
   - AR : مريض السرير ٣ عنده العلاج الكيميائي.
4. **EN** The patient in bed 5 has a problem with the alkylating agent.
   - FR : Le patient du lit 5 a Agent alkylant.
   - AR : مريض السرير ٥ عنده العامل الألكيلي.
5. **EN** The patient in bed 5 does not have a problem with the chemotherapy.
   - FR : Le patient du lit 5 n'a pas de Chimiothérapie.
   - AR : مريض السرير ٥ ليس عنده العلاج الكيميائي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:chemotherapy:v1` — **a chemotherapy** · FR : Chimiothérapie · AR : العلاج الكيميائي
- `gen:alkylating-agent:v1` — **an alkylating agent** · FR : Agent alkylant · AR : العامل الألكيلي
- `gen:antimetabolite:v1` — **an antimetabolite** · FR : Antimétabolite (cancer) · AR : مضاد الاستقلاب (السرطان)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : chemotherapy ; a chemotherapy ; the patient with chemotherapy
  - modèle : The patient in bed 3 has a chemotherapy.
- **q2.** What does the patient in bed 5 have?
  - accepté : alkylating agent ; a problem with the alkylating agent
  - modèle : He has a problem with the alkylating agent.
- **q3.** Does the patient in bed 5 have chemotherapy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the chemotherapy ; he doesn't
  - modèle : No, he does not have a problem with the chemotherapy.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS chimiothérapie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : antimetabolite ; a problem with the antimetabolite
  - modèle : He has a problem with the antimetabolite.
- **Tt2.** Does the patient in bed 9 have alkylating agent?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the alkylating agent ; he doesn't
  - modèle : No, he does not have a problem with the alkylating agent.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS agent alkylant.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a chemotherapy and an alkylating agent. (have)` → have · modèle : The patients have a chemotherapy and an alkylating agent.
- `The patient in bed 5 … not have a chemotherapy. (do)` → does · modèle : The patient in bed 5 does not have a chemotherapy.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the chemotherapy. The patient in bed 5 has a problem with the alkylating agent.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Antimétabolite (cancer), le lit 9 a Chimiothérapie et n'a pas Agent alkylant.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the antimetabolite. The patient in bed 9 has a problem with the chemotherapy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
