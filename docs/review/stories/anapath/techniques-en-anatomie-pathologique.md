# Relecture — Techniques en Anatomie Pathologique (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-011` — Le cas de Leila : Colorations spéciales anormal(e) / _Leila: the special stains is not normal_

**Objectif :** Comprendre des résultats : Microscopie électronique normal(e), Colorations spéciales anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The electron microscopy is normal.”
   - FR : Le médecin dit : « Microscopie électronique est normal(e). »
   - AR : يقول الطبيب: «المجهر الإلكتروني طبيعي/طبيعية.»
5. **EN** “But the special stains is not normal.”
   - FR : « Mais Colorations spéciales n'est pas normal(e). »
   - AR : «لكن الصبغات الخاصة غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the special stains normal?”
   - FR : Amine demande : « Colorations spéciales est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الصبغات الخاصة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:electron-microscopy:v1` — **an electron microscopy** · FR : Microscopie électronique · AR : المجهر الإلكتروني
- `gen:immunofluorescence:v1` — **an immunofluorescence** · FR : Immunofluorescence · AR : التألق المناعي
- `gen:special-stains:v1` — **special stains** · FR : Colorations spéciales · AR : الصبغات الخاصة

### Questions (acceptées / modèle / hint)
- **q1.** Is the electron microscopy normal?
  - accepté : yes ; yes it is ; the electron microscopy is normal ; it is normal
  - modèle : Yes, the electron microscopy is normal.
- **q2.** Is the special stains normal?
  - accepté : no ; no it is not ; no it isn't ; the special stains is not normal ; it is not normal ; it isn't normal
  - modèle : No, the special stains is not normal.
  - hint (si erreur) — FR : Attention : Colorations spéciales n'est PAS normal(e).
- **q3.** Which one is not normal: the electron microscopy or the special stains?
  - accepté : special stains ; the special stains
  - modèle : The special stains is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the immunofluorescence normal?
  - accepté : yes ; yes it is ; the immunofluorescence is normal
  - modèle : Yes, the immunofluorescence is normal.
- **Tt2.** Is the electron microscopy normal?
  - accepté : no ; no it is not ; no it isn't ; the electron microscopy is not normal ; it is not normal
  - modèle : No, the electron microscopy is not normal.
  - hint (si erreur) — FR : Attention : Microscopie électronique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The electron microscopy … normal. (be)` → is · modèle : The electron microscopy is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Leila en deux phrases. Garde la négation. — amorces : The electron microscopy is… / The special stains is not… — modèle : *The electron microscopy is normal. The special stains is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Immunofluorescence normal(e), mais Microscopie électronique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The immunofluorescence is normal. The electron microscopy is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-011` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Microscopie électronique et Immunofluorescence à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient.
   - FR : Nadia est un·e patient·e.
   - AR : Nadia مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check electron microscopy.”
   - FR : « D'abord, nous vérifions Microscopie électronique. »
   - AR : «أولًا، نتحقق من المجهر الإلكتروني.»
5. **EN** “Then, we check immunofluorescence.”
   - FR : « Ensuite, nous vérifions Immunofluorescence. »
   - AR : «ثم نتحقق من التألق المناعي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:electron-microscopy:v1` — **an electron microscopy** · FR : Microscopie électronique · AR : المجهر الإلكتروني
- `gen:immunofluorescence:v1` — **an immunofluorescence** · FR : Immunofluorescence · AR : التألق المناعي
- `gen:special-stains:v1` — **special stains** · FR : Colorations spéciales · AR : الصبغات الخاصة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : electron microscopy ; we check electron microscopy ; electron microscopy first ; the doctor checks electron microscopy first
  - modèle : First, the doctor checks electron microscopy.
- **q2.** What does the doctor check next?
  - accepté : immunofluorescence ; then we check immunofluorescence ; immunofluorescence next ; the doctor checks immunofluorescence next
  - modèle : Then, the doctor checks immunofluorescence.
- **q3.** Does the doctor check immunofluorescence before electron microscopy?
  - accepté : no ; no he does not ; no he doesn't ; he checks electron microscopy first
  - modèle : No, he checks electron microscopy first, then immunofluorescence.
  - hint (si erreur) — FR : Attention : Microscopie électronique vient D'ABORD, ensuite Immunofluorescence.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : special stains ; special stains first ; he checks special stains first
  - modèle : First, the doctor checks special stains.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check electron microscopy. Then, we check immunofluorescence.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Colorations spéciales, ensuite Microscopie électronique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check special stains. Then, we check electron microscopy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-011` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Microscopie électronique / Immunofluorescence, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the electron microscopy.
   - FR : Le patient du lit 3 a Microscopie électronique.
   - AR : مريض السرير ٣ عنده المجهر الإلكتروني.
4. **EN** The patient in bed 5 has a problem with the immunofluorescence.
   - FR : Le patient du lit 5 a Immunofluorescence.
   - AR : مريض السرير ٥ عنده التألق المناعي.
5. **EN** The patient in bed 5 does not have a problem with the electron microscopy.
   - FR : Le patient du lit 5 n'a pas de Microscopie électronique.
   - AR : مريض السرير ٥ ليس عنده المجهر الإلكتروني.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:electron-microscopy:v1` — **an electron microscopy** · FR : Microscopie électronique · AR : المجهر الإلكتروني
- `gen:immunofluorescence:v1` — **an immunofluorescence** · FR : Immunofluorescence · AR : التألق المناعي
- `gen:special-stains:v1` — **special stains** · FR : Colorations spéciales · AR : الصبغات الخاصة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : electron microscopy ; an electron microscopy ; the patient with electron microscopy
  - modèle : The patient in bed 3 has an electron microscopy.
- **q2.** What does the patient in bed 5 have?
  - accepté : immunofluorescence ; a problem with the immunofluorescence
  - modèle : He has a problem with the immunofluorescence.
- **q3.** Does the patient in bed 5 have electron microscopy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the electron microscopy ; he doesn't
  - modèle : No, he does not have a problem with the electron microscopy.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS microscopie électronique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : special stains ; a problem with the special stains
  - modèle : He has a problem with the special stains.
- **Tt2.** Does the patient in bed 9 have immunofluorescence?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the immunofluorescence ; he doesn't
  - modèle : No, he does not have a problem with the immunofluorescence.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS immunofluorescence.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an electron microscopy and an immunofluorescence. (have)` → have · modèle : The patients have an electron microscopy and an immunofluorescence.
- `The patient in bed 5 … not have an electron microscopy. (do)` → does · modèle : The patient in bed 5 does not have an electron microscopy.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the electron microscopy. The patient in bed 5 has a problem with the immunofluorescence.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Colorations spéciales, le lit 9 a Microscopie électronique et n'a pas Immunofluorescence.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the special stains. The patient in bed 9 has a problem with the electron microscopy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
