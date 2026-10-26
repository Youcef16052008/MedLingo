# Relecture — Introduction (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-005` — Le cas de Meriem : Commensal anormal(e) / _Meriem: the commensal is not normal_

**Objectif :** Comprendre des résultats : Microorganisme normal(e), Commensal anormal(e).
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
4. **EN** The doctor says, “The microorganism is normal.”
   - FR : Le médecin dit : « Microorganisme est normal(e). »
   - AR : يقول الطبيب: «الكائن الدقيق طبيعي/طبيعية.»
5. **EN** “But the commensal is not normal.”
   - FR : « Mais Commensal n'est pas normal(e). »
   - AR : «لكن المتعايش غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the commensal normal?”
   - FR : Amine demande : « Commensal est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل المتعايش طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:microorganism:v1` — **a microorganism** · FR : Microorganisme · AR : الكائن الدقيق
- `gen:pathogen:v1` — **a pathogen** · FR : Pathogène · AR : العامل الممرض
- `gen:commensal:v1` — **a commensal** · FR : Commensal · AR : المتعايش

### Questions (acceptées / modèle / hint)
- **q1.** Is the microorganism normal?
  - accepté : yes ; yes it is ; the microorganism is normal ; it is normal
  - modèle : Yes, the microorganism is normal.
- **q2.** Is the commensal normal?
  - accepté : no ; no it is not ; no it isn't ; the commensal is not normal ; it is not normal ; it isn't normal
  - modèle : No, the commensal is not normal.
  - hint (si erreur) — FR : Attention : Commensal n'est PAS normal(e).
- **q3.** Which one is not normal: the microorganism or the commensal?
  - accepté : commensal ; the commensal
  - modèle : The commensal is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the pathogen normal?
  - accepté : yes ; yes it is ; the pathogen is normal
  - modèle : Yes, the pathogen is normal.
- **Tt2.** Is the microorganism normal?
  - accepté : no ; no it is not ; no it isn't ; the microorganism is not normal ; it is not normal
  - modèle : No, the microorganism is not normal.
  - hint (si erreur) — FR : Attention : Microorganisme n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The microorganism … normal. (be)` → is · modèle : The microorganism is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The microorganism is… / The commensal is not… — modèle : *The microorganism is normal. The commensal is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Pathogène normal(e), mais Microorganisme anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The pathogen is normal. The microorganism is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Microorganisme et Pathogène à un patient.
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
4. **EN** “First, we check microorganism.”
   - FR : « D'abord, nous vérifions Microorganisme. »
   - AR : «أولًا، نتحقق من الكائن الدقيق.»
5. **EN** “Then, we check pathogen.”
   - FR : « Ensuite, nous vérifions Pathogène. »
   - AR : «ثم نتحقق من العامل الممرض.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:microorganism:v1` — **a microorganism** · FR : Microorganisme · AR : الكائن الدقيق
- `gen:pathogen:v1` — **a pathogen** · FR : Pathogène · AR : العامل الممرض
- `gen:commensal:v1` — **a commensal** · FR : Commensal · AR : المتعايش

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : microorganism ; we check microorganism ; microorganism first ; the doctor checks microorganism first
  - modèle : First, the doctor checks microorganism.
- **q2.** What does the doctor check next?
  - accepté : pathogen ; then we check pathogen ; pathogen next ; the doctor checks pathogen next
  - modèle : Then, the doctor checks pathogen.
- **q3.** Does the doctor check pathogen before microorganism?
  - accepté : no ; no he does not ; no he doesn't ; he checks microorganism first
  - modèle : No, he checks microorganism first, then pathogen.
  - hint (si erreur) — FR : Attention : Microorganisme vient D'ABORD, ensuite Pathogène.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : commensal ; commensal first ; he checks commensal first
  - modèle : First, the doctor checks commensal.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check microorganism. Then, we check pathogen.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Commensal, ensuite Microorganisme, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check commensal. Then, we check microorganism.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Microorganisme / Pathogène, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the microorganism.
   - FR : Le patient du lit 3 a Microorganisme.
   - AR : مريض السرير ٣ عنده الكائن الدقيق.
4. **EN** The patient in bed 5 has a problem with the pathogen.
   - FR : Le patient du lit 5 a Pathogène.
   - AR : مريض السرير ٥ عنده العامل الممرض.
5. **EN** The patient in bed 5 does not have a problem with the microorganism.
   - FR : Le patient du lit 5 n'a pas de Microorganisme.
   - AR : مريض السرير ٥ ليس عنده الكائن الدقيق.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:microorganism:v1` — **a microorganism** · FR : Microorganisme · AR : الكائن الدقيق
- `gen:pathogen:v1` — **a pathogen** · FR : Pathogène · AR : العامل الممرض
- `gen:commensal:v1` — **a commensal** · FR : Commensal · AR : المتعايش

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : microorganism ; a microorganism ; the patient with microorganism
  - modèle : The patient in bed 3 has a microorganism.
- **q2.** What does the patient in bed 5 have?
  - accepté : pathogen ; a problem with the pathogen
  - modèle : He has a problem with the pathogen.
- **q3.** Does the patient in bed 5 have microorganism?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the microorganism ; he doesn't
  - modèle : No, he does not have a problem with the microorganism.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS microorganisme — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : commensal ; a problem with the commensal
  - modèle : He has a problem with the commensal.
- **Tt2.** Does the patient in bed 9 have pathogen?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the pathogen ; he doesn't
  - modèle : No, he does not have a problem with the pathogen.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS pathogène.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a microorganism and a pathogen. (have)` → have · modèle : The patients have a microorganism and a pathogen.
- `The patient in bed 5 … not have a microorganism. (do)` → does · modèle : The patient in bed 5 does not have a microorganism.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the microorganism. The patient in bed 5 has a problem with the pathogen.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Commensal, le lit 9 a Microorganisme et n'a pas Pathogène.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the commensal. The patient in bed 9 has a problem with the microorganism.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
