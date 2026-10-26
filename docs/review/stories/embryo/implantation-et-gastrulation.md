# Relecture — Implantation et Gastrulation (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-003` — Le cas de Amel : Décidue anormal(e) / _Amel: the decidua is not normal_

**Objectif :** Comprendre des résultats : Implantation (Nidation) normal(e), Décidue anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The implantation is normal.”
   - FR : Le médecin dit : « Implantation (Nidation) est normal(e). »
   - AR : يقول الطبيب: «الانغراس طبيعي/طبيعية.»
5. **EN** “But the decidua is not normal.”
   - FR : « Mais Décidue n'est pas normal(e). »
   - AR : «لكن الساقطة (الغشاء الساقط) غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the decidua normal?”
   - FR : Amine demande : « Décidue est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الساقطة (الغشاء الساقط) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:implantation:v1` — **an implantation** · FR : Implantation (Nidation) · AR : الانغراس
- `gen:endometrium:v1` — **an endometrium** · FR : Endomètre · AR : بطانة الرحم (الغشاء المخاطي)
- `gen:decidua:v1` — **a decidua** · FR : Décidue · AR : الساقطة (الغشاء الساقط)

### Questions (acceptées / modèle / hint)
- **q1.** Is the implantation normal?
  - accepté : yes ; yes it is ; the implantation is normal ; it is normal
  - modèle : Yes, the implantation is normal.
- **q2.** Is the decidua normal?
  - accepté : no ; no it is not ; no it isn't ; the decidua is not normal ; it is not normal ; it isn't normal
  - modèle : No, the decidua is not normal.
  - hint (si erreur) — FR : Attention : Décidue n'est PAS normal(e).
- **q3.** Which one is not normal: the implantation or the decidua?
  - accepté : decidua ; the decidua
  - modèle : The decidua is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the endometrium normal?
  - accepté : yes ; yes it is ; the endometrium is normal
  - modèle : Yes, the endometrium is normal.
- **Tt2.** Is the implantation normal?
  - accepté : no ; no it is not ; no it isn't ; the implantation is not normal ; it is not normal
  - modèle : No, the implantation is not normal.
  - hint (si erreur) — FR : Attention : Implantation (Nidation) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The implantation … normal. (be)` → is · modèle : The implantation is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The implantation is… / The decidua is not… — modèle : *The implantation is normal. The decidua is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Endomètre normal(e), mais Implantation (Nidation) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The endometrium is normal. The implantation is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-003` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Implantation (Nidation) et Endomètre à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient.
   - FR : Meriem est un·e patient·e.
   - AR : Meriem مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check implantation.”
   - FR : « D'abord, nous vérifions Implantation (Nidation). »
   - AR : «أولًا، نتحقق من الانغراس.»
5. **EN** “Then, we check endometrium.”
   - FR : « Ensuite, nous vérifions Endomètre. »
   - AR : «ثم نتحقق من بطانة الرحم (الغشاء المخاطي).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:implantation:v1` — **an implantation** · FR : Implantation (Nidation) · AR : الانغراس
- `gen:endometrium:v1` — **an endometrium** · FR : Endomètre · AR : بطانة الرحم (الغشاء المخاطي)
- `gen:decidua:v1` — **a decidua** · FR : Décidue · AR : الساقطة (الغشاء الساقط)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : implantation ; we check implantation ; implantation first ; the doctor checks implantation first
  - modèle : First, the doctor checks implantation.
- **q2.** What does the doctor check next?
  - accepté : endometrium ; then we check endometrium ; endometrium next ; the doctor checks endometrium next
  - modèle : Then, the doctor checks endometrium.
- **q3.** Does the doctor check endometrium before implantation?
  - accepté : no ; no he does not ; no he doesn't ; he checks implantation first
  - modèle : No, he checks implantation first, then endometrium.
  - hint (si erreur) — FR : Attention : Implantation (Nidation) vient D'ABORD, ensuite Endomètre.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : decidua ; decidua first ; he checks decidua first
  - modèle : First, the doctor checks decidua.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check implantation. Then, we check endometrium.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Décidue, ensuite Implantation (Nidation), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check decidua. Then, we check implantation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-003` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Implantation (Nidation) / Endomètre, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the implantation.
   - FR : Le patient du lit 3 a Implantation (Nidation).
   - AR : مريض السرير ٣ عنده الانغراس.
4. **EN** The patient in bed 5 has a problem with the endometrium.
   - FR : Le patient du lit 5 a Endomètre.
   - AR : مريض السرير ٥ عنده بطانة الرحم (الغشاء المخاطي).
5. **EN** The patient in bed 5 does not have a problem with the implantation.
   - FR : Le patient du lit 5 n'a pas de Implantation (Nidation).
   - AR : مريض السرير ٥ ليس عنده الانغراس.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:implantation:v1` — **an implantation** · FR : Implantation (Nidation) · AR : الانغراس
- `gen:endometrium:v1` — **an endometrium** · FR : Endomètre · AR : بطانة الرحم (الغشاء المخاطي)
- `gen:decidua:v1` — **a decidua** · FR : Décidue · AR : الساقطة (الغشاء الساقط)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : implantation ; an implantation ; the patient with implantation
  - modèle : The patient in bed 3 has an implantation.
- **q2.** What does the patient in bed 5 have?
  - accepté : endometrium ; a problem with the endometrium
  - modèle : He has a problem with the endometrium.
- **q3.** Does the patient in bed 5 have implantation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the implantation ; he doesn't
  - modèle : No, he does not have a problem with the implantation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS implantation (nidation) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : decidua ; a problem with the decidua
  - modèle : He has a problem with the decidua.
- **Tt2.** Does the patient in bed 9 have endometrium?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the endometrium ; he doesn't
  - modèle : No, he does not have a problem with the endometrium.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS endomètre.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an implantation and an endometrium. (have)` → have · modèle : The patients have an implantation and an endometrium.
- `The patient in bed 5 … not have an implantation. (do)` → does · modèle : The patient in bed 5 does not have an implantation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the implantation. The patient in bed 5 has a problem with the endometrium.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Décidue, le lit 9 a Implantation (Nidation) et n'a pas Endomètre.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the decidua. The patient in bed 9 has a problem with the implantation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
