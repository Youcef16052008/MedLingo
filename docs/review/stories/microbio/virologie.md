# Relecture — Virologie (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-013` — Le cas de Nadia : Enveloppe (virale) anormal(e) / _Nadia: the envelope is not normal_

**Objectif :** Comprendre des résultats : Virus normal(e), Enveloppe (virale) anormal(e).
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
4. **EN** The doctor says, “The virus is normal.”
   - FR : Le médecin dit : « Virus est normal(e). »
   - AR : يقول الطبيب: «الفيروس طبيعي/طبيعية.»
5. **EN** “But the envelope is not normal.”
   - FR : « Mais Enveloppe (virale) n'est pas normal(e). »
   - AR : «لكن الغلاف الفيروسي غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the envelope normal?”
   - FR : Amine demande : « Enveloppe (virale) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الغلاف الفيروسي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:virus:v1` — **virus** · FR : Virus · AR : الفيروس
- `gen:capsid:v1` — **a capsid** · FR : Capside · AR : القفيصة
- `gen:envelope:v1` — **an envelope** · FR : Enveloppe (virale) · AR : الغلاف الفيروسي

### Questions (acceptées / modèle / hint)
- **q1.** Is the virus normal?
  - accepté : yes ; yes it is ; the virus is normal ; it is normal
  - modèle : Yes, the virus is normal.
- **q2.** Is the envelope normal?
  - accepté : no ; no it is not ; no it isn't ; the envelope is not normal ; it is not normal ; it isn't normal
  - modèle : No, the envelope is not normal.
  - hint (si erreur) — FR : Attention : Enveloppe (virale) n'est PAS normal(e).
- **q3.** Which one is not normal: the virus or the envelope?
  - accepté : envelope ; the envelope
  - modèle : The envelope is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the capsid normal?
  - accepté : yes ; yes it is ; the capsid is normal
  - modèle : Yes, the capsid is normal.
- **Tt2.** Is the virus normal?
  - accepté : no ; no it is not ; no it isn't ; the virus is not normal ; it is not normal
  - modèle : No, the virus is not normal.
  - hint (si erreur) — FR : Attention : Virus n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The virus … normal. (be)` → is · modèle : The virus is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The virus is… / The envelope is not… — modèle : *The virus is normal. The envelope is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Capside normal(e), mais Virus anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The capsid is normal. The virus is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-013` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Virus et Capside à un patient.
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
4. **EN** “First, we check virus.”
   - FR : « D'abord, nous vérifions Virus. »
   - AR : «أولًا، نتحقق من الفيروس.»
5. **EN** “Then, we check capsid.”
   - FR : « Ensuite, nous vérifions Capside. »
   - AR : «ثم نتحقق من القفيصة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:virus:v1` — **virus** · FR : Virus · AR : الفيروس
- `gen:capsid:v1` — **a capsid** · FR : Capside · AR : القفيصة
- `gen:envelope:v1` — **an envelope** · FR : Enveloppe (virale) · AR : الغلاف الفيروسي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : virus ; we check virus ; virus first ; the doctor checks virus first
  - modèle : First, the doctor checks virus.
- **q2.** What does the doctor check next?
  - accepté : capsid ; then we check capsid ; capsid next ; the doctor checks capsid next
  - modèle : Then, the doctor checks capsid.
- **q3.** Does the doctor check capsid before virus?
  - accepté : no ; no he does not ; no he doesn't ; he checks virus first
  - modèle : No, he checks virus first, then capsid.
  - hint (si erreur) — FR : Attention : Virus vient D'ABORD, ensuite Capside.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : envelope ; envelope first ; he checks envelope first
  - modèle : First, the doctor checks envelope.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check virus. Then, we check capsid.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Enveloppe (virale), ensuite Virus, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check envelope. Then, we check virus.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-013` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Virus / Capside, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the virus.
   - FR : Le patient du lit 3 a Virus.
   - AR : مريض السرير ٣ عنده الفيروس.
4. **EN** The patient in bed 5 has a problem with the capsid.
   - FR : Le patient du lit 5 a Capside.
   - AR : مريض السرير ٥ عنده القفيصة.
5. **EN** The patient in bed 5 does not have a problem with the virus.
   - FR : Le patient du lit 5 n'a pas de Virus.
   - AR : مريض السرير ٥ ليس عنده الفيروس.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:virus:v1` — **virus** · FR : Virus · AR : الفيروس
- `gen:capsid:v1` — **a capsid** · FR : Capside · AR : القفيصة
- `gen:envelope:v1` — **an envelope** · FR : Enveloppe (virale) · AR : الغلاف الفيروسي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : virus ; the patient with virus
  - modèle : The patient in bed 3 has virus.
- **q2.** What does the patient in bed 5 have?
  - accepté : capsid ; a problem with the capsid
  - modèle : He has a problem with the capsid.
- **q3.** Does the patient in bed 5 have virus?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the virus ; he doesn't
  - modèle : No, he does not have a problem with the virus.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS virus — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : envelope ; a problem with the envelope
  - modèle : He has a problem with the envelope.
- **Tt2.** Does the patient in bed 9 have capsid?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the capsid ; he doesn't
  - modèle : No, he does not have a problem with the capsid.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS capside.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … virus and a capsid. (have)` → have · modèle : The patients have virus and a capsid.
- `The patient in bed 5 … not have virus. (do)` → does · modèle : The patient in bed 5 does not have virus.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the virus. The patient in bed 5 has a problem with the capsid.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Enveloppe (virale), le lit 9 a Virus et n'a pas Capside.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the envelope. The patient in bed 9 has a problem with the virus.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
