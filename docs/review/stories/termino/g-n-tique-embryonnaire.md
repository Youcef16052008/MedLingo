# Relecture — Génétique Embryonnaire (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-015` — Le cas de Amel : Syndrome de Klinefelter (47, XXY) anormal(e) / _Amel: the klinefelter syndrome is not normal_

**Objectif :** Comprendre des résultats : Syndrome de Down (Trisomie 21) normal(e), Syndrome de Klinefelter (47, XXY) anormal(e).
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
4. **EN** The doctor says, “The down syndrome is normal.”
   - FR : Le médecin dit : « Syndrome de Down (Trisomie 21) est normal(e). »
   - AR : يقول الطبيب: «متلازمة داون (تثلث الصبغي 21) طبيعي/طبيعية.»
5. **EN** “But the klinefelter syndrome is not normal.”
   - FR : « Mais Syndrome de Klinefelter (47, XXY) n'est pas normal(e). »
   - AR : «لكن متلازمة كلاينفيلتر غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the klinefelter syndrome normal?”
   - FR : Amine demande : « Syndrome de Klinefelter (47, XXY) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل متلازمة كلاينفيلتر طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:down-syndrome:v1` — **a down syndrome** · FR : Syndrome de Down (Trisomie 21) · AR : متلازمة داون (تثلث الصبغي 21)
- `gen:turner-syndrome:v1` — **a turner syndrome** · FR : Syndrome de Turner (45, X) · AR : متلازمة تيرنر
- `gen:klinefelter-syndrome:v1` — **a klinefelter syndrome** · FR : Syndrome de Klinefelter (47, XXY) · AR : متلازمة كلاينفيلتر

### Questions (acceptées / modèle / hint)
- **q1.** Is the down syndrome normal?
  - accepté : yes ; yes it is ; the down syndrome is normal ; it is normal
  - modèle : Yes, the down syndrome is normal.
- **q2.** Is the klinefelter syndrome normal?
  - accepté : no ; no it is not ; no it isn't ; the klinefelter syndrome is not normal ; it is not normal ; it isn't normal
  - modèle : No, the klinefelter syndrome is not normal.
  - hint (si erreur) — FR : Attention : Syndrome de Klinefelter (47, XXY) n'est PAS normal(e).
- **q3.** Which one is not normal: the down syndrome or the klinefelter syndrome?
  - accepté : klinefelter syndrome ; the klinefelter syndrome
  - modèle : The klinefelter syndrome is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the turner syndrome normal?
  - accepté : yes ; yes it is ; the turner syndrome is normal
  - modèle : Yes, the turner syndrome is normal.
- **Tt2.** Is the down syndrome normal?
  - accepté : no ; no it is not ; no it isn't ; the down syndrome is not normal ; it is not normal
  - modèle : No, the down syndrome is not normal.
  - hint (si erreur) — FR : Attention : Syndrome de Down (Trisomie 21) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The down syndrome … normal. (be)` → is · modèle : The down syndrome is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The down syndrome is… / The klinefelter syndrome is not… — modèle : *The down syndrome is normal. The klinefelter syndrome is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Syndrome de Turner (45, X) normal(e), mais Syndrome de Down (Trisomie 21) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The turner syndrome is normal. The down syndrome is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-015` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Syndrome de Down (Trisomie 21) et Syndrome de Turner (45, X) à un patient.
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
4. **EN** “First, we check down syndrome.”
   - FR : « D'abord, nous vérifions Syndrome de Down (Trisomie 21). »
   - AR : «أولًا، نتحقق من متلازمة داون (تثلث الصبغي 21).»
5. **EN** “Then, we check turner syndrome.”
   - FR : « Ensuite, nous vérifions Syndrome de Turner (45, X). »
   - AR : «ثم نتحقق من متلازمة تيرنر.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:down-syndrome:v1` — **a down syndrome** · FR : Syndrome de Down (Trisomie 21) · AR : متلازمة داون (تثلث الصبغي 21)
- `gen:turner-syndrome:v1` — **a turner syndrome** · FR : Syndrome de Turner (45, X) · AR : متلازمة تيرنر
- `gen:klinefelter-syndrome:v1` — **a klinefelter syndrome** · FR : Syndrome de Klinefelter (47, XXY) · AR : متلازمة كلاينفيلتر

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : down syndrome ; we check down syndrome ; down syndrome first ; the doctor checks down syndrome first
  - modèle : First, the doctor checks down syndrome.
- **q2.** What does the doctor check next?
  - accepté : turner syndrome ; then we check turner syndrome ; turner syndrome next ; the doctor checks turner syndrome next
  - modèle : Then, the doctor checks turner syndrome.
- **q3.** Does the doctor check turner syndrome before down syndrome?
  - accepté : no ; no he does not ; no he doesn't ; he checks down syndrome first
  - modèle : No, he checks down syndrome first, then turner syndrome.
  - hint (si erreur) — FR : Attention : Syndrome de Down (Trisomie 21) vient D'ABORD, ensuite Syndrome de Turner (45, X).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : klinefelter syndrome ; klinefelter syndrome first ; he checks klinefelter syndrome first
  - modèle : First, the doctor checks klinefelter syndrome.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check down syndrome. Then, we check turner syndrome.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Syndrome de Klinefelter (47, XXY), ensuite Syndrome de Down (Trisomie 21), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check klinefelter syndrome. Then, we check down syndrome.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-015` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Syndrome de Down (Trisomie 21) / Syndrome de Turner (45, X), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the down syndrome.
   - FR : Le patient du lit 3 a Syndrome de Down (Trisomie 21).
   - AR : مريض السرير ٣ عنده متلازمة داون (تثلث الصبغي 21).
4. **EN** The patient in bed 5 has a problem with the turner syndrome.
   - FR : Le patient du lit 5 a Syndrome de Turner (45, X).
   - AR : مريض السرير ٥ عنده متلازمة تيرنر.
5. **EN** The patient in bed 5 does not have a problem with the down syndrome.
   - FR : Le patient du lit 5 n'a pas de Syndrome de Down (Trisomie 21).
   - AR : مريض السرير ٥ ليس عنده متلازمة داون (تثلث الصبغي 21).
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:down-syndrome:v1` — **a down syndrome** · FR : Syndrome de Down (Trisomie 21) · AR : متلازمة داون (تثلث الصبغي 21)
- `gen:turner-syndrome:v1` — **a turner syndrome** · FR : Syndrome de Turner (45, X) · AR : متلازمة تيرنر
- `gen:klinefelter-syndrome:v1` — **a klinefelter syndrome** · FR : Syndrome de Klinefelter (47, XXY) · AR : متلازمة كلاينفيلتر

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : down syndrome ; a down syndrome ; the patient with down syndrome
  - modèle : The patient in bed 3 has a down syndrome.
- **q2.** What does the patient in bed 5 have?
  - accepté : turner syndrome ; a problem with the turner syndrome
  - modèle : He has a problem with the turner syndrome.
- **q3.** Does the patient in bed 5 have down syndrome?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the down syndrome ; he doesn't
  - modèle : No, he does not have a problem with the down syndrome.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS syndrome de down (trisomie 21) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : klinefelter syndrome ; a problem with the klinefelter syndrome
  - modèle : He has a problem with the klinefelter syndrome.
- **Tt2.** Does the patient in bed 9 have turner syndrome?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the turner syndrome ; he doesn't
  - modèle : No, he does not have a problem with the turner syndrome.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS syndrome de turner (45, x).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a down syndrome and a turner syndrome. (have)` → have · modèle : The patients have a down syndrome and a turner syndrome.
- `The patient in bed 5 … not have a down syndrome. (do)` → does · modèle : The patient in bed 5 does not have a down syndrome.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the down syndrome. The patient in bed 5 has a problem with the turner syndrome.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Syndrome de Klinefelter (47, XXY), le lit 9 a Syndrome de Down (Trisomie 21) et n'a pas Syndrome de Turner (45, X).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the klinefelter syndrome. The patient in bed 9 has a problem with the down syndrome.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
