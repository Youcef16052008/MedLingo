# Relecture — Traduction (genet)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `genet-case-013` — Le cas de Nadia : ARNt (ARN de transfert) anormal(e) / _Nadia: the trna is not normal_

**Objectif :** Comprendre des résultats : Traduction normal(e), ARNt (ARN de transfert) anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The translation is normal.”
   - FR : Le médecin dit : « Traduction est normal(e). »
   - AR : يقول الطبيب: «الترجمة طبيعي/طبيعية.»
5. **EN** “But the trna is not normal.”
   - FR : « Mais ARNt (ARN de transfert) n'est pas normal(e). »
   - AR : «لكن الـ RNA الناقل غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the trna normal?”
   - FR : Amine demande : « ARNt (ARN de transfert) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الـ RNA الناقل طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:translation:v1` — **a translation** · FR : Traduction · AR : الترجمة
- `gen:ribosome:v1` — **a ribosome** · FR : Ribosome · AR : الريبوسوم
- `gen:trna:v1` — **a trna** · FR : ARNt (ARN de transfert) · AR : الـ RNA الناقل

### Questions (acceptées / modèle / hint)
- **q1.** Is the translation normal?
  - accepté : yes ; yes it is ; the translation is normal ; it is normal
  - modèle : Yes, the translation is normal.
- **q2.** Is the trna normal?
  - accepté : no ; no it is not ; no it isn't ; the trna is not normal ; it is not normal ; it isn't normal
  - modèle : No, the trna is not normal.
  - hint (si erreur) — FR : Attention : ARNt (ARN de transfert) n'est PAS normal(e).
- **q3.** Which one is not normal: the translation or the trna?
  - accepté : trna ; the trna
  - modèle : The trna is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the ribosome normal?
  - accepté : yes ; yes it is ; the ribosome is normal
  - modèle : Yes, the ribosome is normal.
- **Tt2.** Is the translation normal?
  - accepté : no ; no it is not ; no it isn't ; the translation is not normal ; it is not normal
  - modèle : No, the translation is not normal.
  - hint (si erreur) — FR : Attention : Traduction n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The translation … normal. (be)` → is · modèle : The translation is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The translation is… / The trna is not… — modèle : *The translation is normal. The trna is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Ribosome normal(e), mais Traduction anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The ribosome is normal. The translation is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `genet-explain-013` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Traduction et Ribosome à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient.
   - FR : Amel est un·e patient·e.
   - AR : Amel مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check translation.”
   - FR : « D'abord, nous vérifions Traduction. »
   - AR : «أولًا، نتحقق من الترجمة.»
5. **EN** “Then, we check ribosome.”
   - FR : « Ensuite, nous vérifions Ribosome. »
   - AR : «ثم نتحقق من الريبوسوم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:translation:v1` — **a translation** · FR : Traduction · AR : الترجمة
- `gen:ribosome:v1` — **a ribosome** · FR : Ribosome · AR : الريبوسوم
- `gen:trna:v1` — **a trna** · FR : ARNt (ARN de transfert) · AR : الـ RNA الناقل

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : translation ; we check translation ; translation first ; the doctor checks translation first
  - modèle : First, the doctor checks translation.
- **q2.** What does the doctor check next?
  - accepté : ribosome ; then we check ribosome ; ribosome next ; the doctor checks ribosome next
  - modèle : Then, the doctor checks ribosome.
- **q3.** Does the doctor check ribosome before translation?
  - accepté : no ; no he does not ; no he doesn't ; he checks translation first
  - modèle : No, he checks translation first, then ribosome.
  - hint (si erreur) — FR : Attention : Traduction vient D'ABORD, ensuite Ribosome.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : trna ; trna first ; he checks trna first
  - modèle : First, the doctor checks trna.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check translation. Then, we check ribosome.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord ARNt (ARN de transfert), ensuite Traduction, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check trna. Then, we check translation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `genet-handover-013` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Traduction / Ribosome, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the translation.
   - FR : Le patient du lit 3 a Traduction.
   - AR : مريض السرير ٣ عنده الترجمة.
4. **EN** The patient in bed 5 has a problem with the ribosome.
   - FR : Le patient du lit 5 a Ribosome.
   - AR : مريض السرير ٥ عنده الريبوسوم.
5. **EN** The patient in bed 5 does not have a problem with the translation.
   - FR : Le patient du lit 5 n'a pas de Traduction.
   - AR : مريض السرير ٥ ليس عنده الترجمة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:translation:v1` — **a translation** · FR : Traduction · AR : الترجمة
- `gen:ribosome:v1` — **a ribosome** · FR : Ribosome · AR : الريبوسوم
- `gen:trna:v1` — **a trna** · FR : ARNt (ARN de transfert) · AR : الـ RNA الناقل

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : translation ; a translation ; the patient with translation
  - modèle : The patient in bed 3 has a translation.
- **q2.** What does the patient in bed 5 have?
  - accepté : ribosome ; a problem with the ribosome
  - modèle : He has a problem with the ribosome.
- **q3.** Does the patient in bed 5 have translation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the translation ; he doesn't
  - modèle : No, he does not have a problem with the translation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS traduction — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : trna ; a problem with the trna
  - modèle : He has a problem with the trna.
- **Tt2.** Does the patient in bed 9 have ribosome?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the ribosome ; he doesn't
  - modèle : No, he does not have a problem with the ribosome.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS ribosome.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a translation and a ribosome. (have)` → have · modèle : The patients have a translation and a ribosome.
- `The patient in bed 5 … not have a translation. (do)` → does · modèle : The patient in bed 5 does not have a translation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the translation. The patient in bed 5 has a problem with the ribosome.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a ARNt (ARN de transfert), le lit 9 a Traduction et n'a pas Ribosome.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the trna. The patient in bed 9 has a problem with the translation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
