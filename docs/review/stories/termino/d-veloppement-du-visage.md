# Relecture — Développement du Visage (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-009` — Le cas de Sara : Processus frontonasal anormal(e) / _Sara: the frontonasal prominence is not normal_

**Objectif :** Comprendre des résultats : Stomodeum normal(e), Processus frontonasal anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The stomodeum is normal.”
   - FR : Le médecin dit : « Stomodeum est normal(e). »
   - AR : يقول الطبيب: «الفم البدائي طبيعي/طبيعية.»
5. **EN** “But the frontonasal prominence is not normal.”
   - FR : « Mais Processus frontonasal n'est pas normal(e). »
   - AR : «لكن البروز الجبهي الأنفي غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the frontonasal prominence normal?”
   - FR : Amine demande : « Processus frontonasal est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل البروز الجبهي الأنفي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:stomodeum:v1` — **a stomodeum** · FR : Stomodeum · AR : الفم البدائي
- `gen:oropharyngeal-membrane:v1` — **an oropharyngeal membrane** · FR : Membrane oropharyngée (buccopharyngée) · AR : الغشاء الفمي البلعومي
- `gen:frontonasal-prominence:v1` — **a frontonasal prominence** · FR : Processus frontonasal · AR : البروز الجبهي الأنفي

### Questions (acceptées / modèle / hint)
- **q1.** Is the stomodeum normal?
  - accepté : yes ; yes it is ; the stomodeum is normal ; it is normal
  - modèle : Yes, the stomodeum is normal.
- **q2.** Is the frontonasal prominence normal?
  - accepté : no ; no it is not ; no it isn't ; the frontonasal prominence is not normal ; it is not normal ; it isn't normal
  - modèle : No, the frontonasal prominence is not normal.
  - hint (si erreur) — FR : Attention : Processus frontonasal n'est PAS normal(e).
- **q3.** Which one is not normal: the stomodeum or the frontonasal prominence?
  - accepté : frontonasal prominence ; the frontonasal prominence
  - modèle : The frontonasal prominence is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the oropharyngeal membrane normal?
  - accepté : yes ; yes it is ; the oropharyngeal membrane is normal
  - modèle : Yes, the oropharyngeal membrane is normal.
- **Tt2.** Is the stomodeum normal?
  - accepté : no ; no it is not ; no it isn't ; the stomodeum is not normal ; it is not normal
  - modèle : No, the stomodeum is not normal.
  - hint (si erreur) — FR : Attention : Stomodeum n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The stomodeum … normal. (be)` → is · modèle : The stomodeum is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The stomodeum is… / The frontonasal prominence is not… — modèle : *The stomodeum is normal. The frontonasal prominence is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Membrane oropharyngée (buccopharyngée) normal(e), mais Stomodeum anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The oropharyngeal membrane is normal. The stomodeum is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-009` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Stomodeum et Membrane oropharyngée (buccopharyngée) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient.
   - FR : Leila est un·e patient·e.
   - AR : Leila مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check stomodeum.”
   - FR : « D'abord, nous vérifions Stomodeum. »
   - AR : «أولًا، نتحقق من الفم البدائي.»
5. **EN** “Then, we check oropharyngeal membrane.”
   - FR : « Ensuite, nous vérifions Membrane oropharyngée (buccopharyngée). »
   - AR : «ثم نتحقق من الغشاء الفمي البلعومي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:stomodeum:v1` — **a stomodeum** · FR : Stomodeum · AR : الفم البدائي
- `gen:oropharyngeal-membrane:v1` — **an oropharyngeal membrane** · FR : Membrane oropharyngée (buccopharyngée) · AR : الغشاء الفمي البلعومي
- `gen:frontonasal-prominence:v1` — **a frontonasal prominence** · FR : Processus frontonasal · AR : البروز الجبهي الأنفي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : stomodeum ; we check stomodeum ; stomodeum first ; the doctor checks stomodeum first
  - modèle : First, the doctor checks stomodeum.
- **q2.** What does the doctor check next?
  - accepté : oropharyngeal membrane ; then we check oropharyngeal membrane ; oropharyngeal membrane next ; the doctor checks oropharyngeal membrane next
  - modèle : Then, the doctor checks oropharyngeal membrane.
- **q3.** Does the doctor check oropharyngeal membrane before stomodeum?
  - accepté : no ; no he does not ; no he doesn't ; he checks stomodeum first
  - modèle : No, he checks stomodeum first, then oropharyngeal membrane.
  - hint (si erreur) — FR : Attention : Stomodeum vient D'ABORD, ensuite Membrane oropharyngée (buccopharyngée).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : frontonasal prominence ; frontonasal prominence first ; he checks frontonasal prominence first
  - modèle : First, the doctor checks frontonasal prominence.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check stomodeum. Then, we check oropharyngeal membrane.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Processus frontonasal, ensuite Stomodeum, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check frontonasal prominence. Then, we check stomodeum.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-009` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Stomodeum / Membrane oropharyngée (buccopharyngée), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the stomodeum.
   - FR : Le patient du lit 3 a Stomodeum.
   - AR : مريض السرير ٣ عنده الفم البدائي.
4. **EN** The patient in bed 5 has a problem with the oropharyngeal membrane.
   - FR : Le patient du lit 5 a Membrane oropharyngée (buccopharyngée).
   - AR : مريض السرير ٥ عنده الغشاء الفمي البلعومي.
5. **EN** The patient in bed 5 does not have a problem with the stomodeum.
   - FR : Le patient du lit 5 n'a pas de Stomodeum.
   - AR : مريض السرير ٥ ليس عنده الفم البدائي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:stomodeum:v1` — **a stomodeum** · FR : Stomodeum · AR : الفم البدائي
- `gen:oropharyngeal-membrane:v1` — **an oropharyngeal membrane** · FR : Membrane oropharyngée (buccopharyngée) · AR : الغشاء الفمي البلعومي
- `gen:frontonasal-prominence:v1` — **a frontonasal prominence** · FR : Processus frontonasal · AR : البروز الجبهي الأنفي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : stomodeum ; a stomodeum ; the patient with stomodeum
  - modèle : The patient in bed 3 has a stomodeum.
- **q2.** What does the patient in bed 5 have?
  - accepté : oropharyngeal membrane ; a problem with the oropharyngeal membrane
  - modèle : He has a problem with the oropharyngeal membrane.
- **q3.** Does the patient in bed 5 have stomodeum?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the stomodeum ; he doesn't
  - modèle : No, he does not have a problem with the stomodeum.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS stomodeum — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : frontonasal prominence ; a problem with the frontonasal prominence
  - modèle : He has a problem with the frontonasal prominence.
- **Tt2.** Does the patient in bed 9 have oropharyngeal membrane?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the oropharyngeal membrane ; he doesn't
  - modèle : No, he does not have a problem with the oropharyngeal membrane.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS membrane oropharyngée (buccopharyngée).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a stomodeum and an oropharyngeal membrane. (have)` → have · modèle : The patients have a stomodeum and an oropharyngeal membrane.
- `The patient in bed 5 … not have a stomodeum. (do)` → does · modèle : The patient in bed 5 does not have a stomodeum.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the stomodeum. The patient in bed 5 has a problem with the oropharyngeal membrane.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Processus frontonasal, le lit 9 a Stomodeum et n'a pas Membrane oropharyngée (buccopharyngée).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the frontonasal prominence. The patient in bed 9 has a problem with the stomodeum.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
