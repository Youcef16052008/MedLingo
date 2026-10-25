# Relecture — Mesures de Dispersion (info_med)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `info-med-case-006` — Le cas de Sofiane : Écart interquartile (EIQ) anormal(e) / _Sofiane: the interquartile range is not normal_

**Objectif :** Comprendre des résultats : Dispersion (Variabilité) normal(e), Écart interquartile (EIQ) anormal(e).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient at the clinic.
   - FR : Sofiane est un·e patient·e à la clinique.
   - AR : Sofiane مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The dispersion is normal.”
   - FR : Le médecin dit : « Dispersion (Variabilité) est normal(e). »
   - AR : يقول الطبيب: «التشتت (التباينية) طبيعي/طبيعية.»
5. **EN** “But the interquartile range is not normal.”
   - FR : « Mais Écart interquartile (EIQ) n'est pas normal(e). »
   - AR : «لكن المدى الربيعي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the interquartile range normal?”
   - FR : Lina demande : « Écart interquartile (EIQ) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل المدى الربيعي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:dispersion:v1` — **a dispersion** · FR : Dispersion (Variabilité) · AR : التشتت (التباينية)
- `gen:range:v1` — **a range** · FR : Étendue · AR : المدى
- `gen:interquartile-range:v1` — **an interquartile range** · FR : Écart interquartile (EIQ) · AR : المدى الربيعي

### Questions (acceptées / modèle / hint)
- **q1.** Is the dispersion normal?
  - accepté : yes ; yes it is ; the dispersion is normal ; it is normal
  - modèle : Yes, the dispersion is normal.
- **q2.** Is the interquartile range normal?
  - accepté : no ; no it is not ; no it isn't ; the interquartile range is not normal ; it is not normal ; it isn't normal
  - modèle : No, the interquartile range is not normal.
  - hint (si erreur) — FR : Attention : Écart interquartile (EIQ) n'est PAS normal(e).
- **q3.** Which one is not normal: the dispersion or the interquartile range?
  - accepté : interquartile range ; the interquartile range
  - modèle : The interquartile range is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the range normal?
  - accepté : yes ; yes it is ; the range is normal
  - modèle : Yes, the range is normal.
- **Tt2.** Is the dispersion normal?
  - accepté : no ; no it is not ; no it isn't ; the dispersion is not normal ; it is not normal
  - modèle : No, the dispersion is not normal.
  - hint (si erreur) — FR : Attention : Dispersion (Variabilité) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The dispersion … normal. (be)` → is · modèle : The dispersion is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sofiane en deux phrases. Garde la négation. — amorces : The dispersion is… / The interquartile range is not… — modèle : *The dispersion is normal. The interquartile range is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Étendue normal(e), mais Dispersion (Variabilité) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The range is normal. The dispersion is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `info-med-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Dispersion (Variabilité) et Étendue à un patient.
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check dispersion.”
   - FR : « D'abord, nous vérifions Dispersion (Variabilité). »
   - AR : «أولًا، نتحقق من التشتت (التباينية).»
5. **EN** “Then, we check range.”
   - FR : « Ensuite, nous vérifions Étendue. »
   - AR : «ثم نتحقق من المدى.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:dispersion:v1` — **a dispersion** · FR : Dispersion (Variabilité) · AR : التشتت (التباينية)
- `gen:range:v1` — **a range** · FR : Étendue · AR : المدى
- `gen:interquartile-range:v1` — **an interquartile range** · FR : Écart interquartile (EIQ) · AR : المدى الربيعي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : dispersion ; we check dispersion ; dispersion first ; the doctor checks dispersion first
  - modèle : First, the doctor checks dispersion.
- **q2.** What does the doctor check next?
  - accepté : range ; then we check range ; range next ; the doctor checks range next
  - modèle : Then, the doctor checks range.
- **q3.** Does the doctor check range before dispersion?
  - accepté : no ; no he does not ; no he doesn't ; he checks dispersion first
  - modèle : No, he checks dispersion first, then range.
  - hint (si erreur) — FR : Attention : Dispersion (Variabilité) vient D'ABORD, ensuite Étendue.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : interquartile range ; interquartile range first ; he checks interquartile range first
  - modèle : First, the doctor checks interquartile range.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check dispersion. Then, we check range.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Écart interquartile (EIQ), ensuite Dispersion (Variabilité), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check interquartile range. Then, we check dispersion.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `info-med-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Dispersion (Variabilité) / Étendue, lits 3 et 5).
**Archétype :** med · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the dispersion.
   - FR : Le patient du lit 3 a Dispersion (Variabilité).
   - AR : مريض السرير ٣ عنده التشتت (التباينية).
4. **EN** The patient in bed 5 has a problem with the range.
   - FR : Le patient du lit 5 a Étendue.
   - AR : مريض السرير ٥ عنده المدى.
5. **EN** The patient in bed 5 does not have a problem with the dispersion.
   - FR : Le patient du lit 5 n'a pas de Dispersion (Variabilité).
   - AR : مريض السرير ٥ ليس عنده التشتت (التباينية).
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:dispersion:v1` — **a dispersion** · FR : Dispersion (Variabilité) · AR : التشتت (التباينية)
- `gen:range:v1` — **a range** · FR : Étendue · AR : المدى
- `gen:interquartile-range:v1` — **an interquartile range** · FR : Écart interquartile (EIQ) · AR : المدى الربيعي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : dispersion ; a dispersion ; the patient with dispersion
  - modèle : The patient in bed 3 has a dispersion.
- **q2.** What does the patient in bed 5 have?
  - accepté : range ; a problem with the range
  - modèle : He has a problem with the range.
- **q3.** Does the patient in bed 5 have dispersion?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the dispersion ; he doesn't
  - modèle : No, he does not have a problem with the dispersion.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS dispersion (variabilité) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : interquartile range ; a problem with the interquartile range
  - modèle : He has a problem with the interquartile range.
- **Tt2.** Does the patient in bed 9 have range?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the range ; he doesn't
  - modèle : No, he does not have a problem with the range.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS étendue.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a dispersion and a range. (have)` → have · modèle : The patients have a dispersion and a range.
- `The patient in bed 5 … not have a dispersion. (do)` → does · modèle : The patient in bed 5 does not have a dispersion.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the dispersion. The patient in bed 5 has a problem with the range.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Écart interquartile (EIQ), le lit 9 a Dispersion (Variabilité) et n'a pas Étendue.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the interquartile range. The patient in bed 9 has a problem with the dispersion.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
