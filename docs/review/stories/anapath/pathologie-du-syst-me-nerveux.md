# Relecture — Pathologie du Système Nerveux (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-010` — Le cas de Omar : Infarctus cérébral anormal(e) / _Omar: the cerebral infarction is not normal_

**Objectif :** Comprendre des résultats : Glioblastome multiforme (GBM) normal(e), Infarctus cérébral anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The glioblastoma multiforme is normal.”
   - FR : Le médecin dit : « Glioblastome multiforme (GBM) est normal(e). »
   - AR : يقول الطبيب: «الورم الدبقي متعدد الأشكال طبيعي/طبيعية.»
5. **EN** “But the cerebral infarction is not normal.”
   - FR : « Mais Infarctus cérébral n'est pas normal(e). »
   - AR : «لكن الاحتشاء الدماغي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the cerebral infarction normal?”
   - FR : Lina demande : « Infarctus cérébral est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الاحتشاء الدماغي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:glioblastoma-multiforme:v1` — **a glioblastoma multiforme** · FR : Glioblastome multiforme (GBM) · AR : الورم الدبقي متعدد الأشكال
- `gen:meningioma:v1` — **a meningioma** · FR : Méningiome · AR : ورم السحايا
- `gen:cerebral-infarction:v1` — **a cerebral infarction** · FR : Infarctus cérébral · AR : الاحتشاء الدماغي

### Questions (acceptées / modèle / hint)
- **q1.** Is the glioblastoma multiforme normal?
  - accepté : yes ; yes it is ; the glioblastoma multiforme is normal ; it is normal
  - modèle : Yes, the glioblastoma multiforme is normal.
- **q2.** Is the cerebral infarction normal?
  - accepté : no ; no it is not ; no it isn't ; the cerebral infarction is not normal ; it is not normal ; it isn't normal
  - modèle : No, the cerebral infarction is not normal.
  - hint (si erreur) — FR : Attention : Infarctus cérébral n'est PAS normal(e).
- **q3.** Which one is not normal: the glioblastoma multiforme or the cerebral infarction?
  - accepté : cerebral infarction ; the cerebral infarction
  - modèle : The cerebral infarction is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the meningioma normal?
  - accepté : yes ; yes it is ; the meningioma is normal
  - modèle : Yes, the meningioma is normal.
- **Tt2.** Is the glioblastoma multiforme normal?
  - accepté : no ; no it is not ; no it isn't ; the glioblastoma multiforme is not normal ; it is not normal
  - modèle : No, the glioblastoma multiforme is not normal.
  - hint (si erreur) — FR : Attention : Glioblastome multiforme (GBM) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The glioblastoma multiforme … normal. (be)` → is · modèle : The glioblastoma multiforme is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The glioblastoma multiforme is… / The cerebral infarction is not… — modèle : *The glioblastoma multiforme is normal. The cerebral infarction is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Méningiome normal(e), mais Glioblastome multiforme (GBM) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The meningioma is normal. The glioblastoma multiforme is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-010` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Glioblastome multiforme (GBM) et Méningiome à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient.
   - FR : Karim est un·e patient·e.
   - AR : Karim مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check glioblastoma multiforme.”
   - FR : « D'abord, nous vérifions Glioblastome multiforme (GBM). »
   - AR : «أولًا، نتحقق من الورم الدبقي متعدد الأشكال.»
5. **EN** “Then, we check meningioma.”
   - FR : « Ensuite, nous vérifions Méningiome. »
   - AR : «ثم نتحقق من ورم السحايا.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:glioblastoma-multiforme:v1` — **a glioblastoma multiforme** · FR : Glioblastome multiforme (GBM) · AR : الورم الدبقي متعدد الأشكال
- `gen:meningioma:v1` — **a meningioma** · FR : Méningiome · AR : ورم السحايا
- `gen:cerebral-infarction:v1` — **a cerebral infarction** · FR : Infarctus cérébral · AR : الاحتشاء الدماغي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : glioblastoma multiforme ; we check glioblastoma multiforme ; glioblastoma multiforme first ; the doctor checks glioblastoma multiforme first
  - modèle : First, the doctor checks glioblastoma multiforme.
- **q2.** What does the doctor check next?
  - accepté : meningioma ; then we check meningioma ; meningioma next ; the doctor checks meningioma next
  - modèle : Then, the doctor checks meningioma.
- **q3.** Does the doctor check meningioma before glioblastoma multiforme?
  - accepté : no ; no he does not ; no he doesn't ; he checks glioblastoma multiforme first
  - modèle : No, he checks glioblastoma multiforme first, then meningioma.
  - hint (si erreur) — FR : Attention : Glioblastome multiforme (GBM) vient D'ABORD, ensuite Méningiome.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : cerebral infarction ; cerebral infarction first ; he checks cerebral infarction first
  - modèle : First, the doctor checks cerebral infarction.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check glioblastoma multiforme. Then, we check meningioma.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Infarctus cérébral, ensuite Glioblastome multiforme (GBM), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check cerebral infarction. Then, we check glioblastoma multiforme.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-010` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Glioblastome multiforme (GBM) / Méningiome, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the glioblastoma multiforme.
   - FR : Le patient du lit 3 a Glioblastome multiforme (GBM).
   - AR : مريض السرير ٣ عنده الورم الدبقي متعدد الأشكال.
4. **EN** The patient in bed 5 has a problem with the meningioma.
   - FR : Le patient du lit 5 a Méningiome.
   - AR : مريض السرير ٥ عنده ورم السحايا.
5. **EN** The patient in bed 5 does not have a problem with the glioblastoma multiforme.
   - FR : Le patient du lit 5 n'a pas de Glioblastome multiforme (GBM).
   - AR : مريض السرير ٥ ليس عنده الورم الدبقي متعدد الأشكال.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:glioblastoma-multiforme:v1` — **a glioblastoma multiforme** · FR : Glioblastome multiforme (GBM) · AR : الورم الدبقي متعدد الأشكال
- `gen:meningioma:v1` — **a meningioma** · FR : Méningiome · AR : ورم السحايا
- `gen:cerebral-infarction:v1` — **a cerebral infarction** · FR : Infarctus cérébral · AR : الاحتشاء الدماغي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : glioblastoma multiforme ; a glioblastoma multiforme ; the patient with glioblastoma multiforme
  - modèle : The patient in bed 3 has a glioblastoma multiforme.
- **q2.** What does the patient in bed 5 have?
  - accepté : meningioma ; a problem with the meningioma
  - modèle : He has a problem with the meningioma.
- **q3.** Does the patient in bed 5 have glioblastoma multiforme?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the glioblastoma multiforme ; he doesn't
  - modèle : No, he does not have a problem with the glioblastoma multiforme.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS glioblastome multiforme (gbm) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : cerebral infarction ; a problem with the cerebral infarction
  - modèle : He has a problem with the cerebral infarction.
- **Tt2.** Does the patient in bed 9 have meningioma?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the meningioma ; he doesn't
  - modèle : No, he does not have a problem with the meningioma.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS méningiome.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a glioblastoma multiforme and a meningioma. (have)` → have · modèle : The patients have a glioblastoma multiforme and a meningioma.
- `The patient in bed 5 … not have a glioblastoma multiforme. (do)` → does · modèle : The patient in bed 5 does not have a glioblastoma multiforme.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the glioblastoma multiforme. The patient in bed 5 has a problem with the meningioma.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Infarctus cérébral, le lit 9 a Glioblastome multiforme (GBM) et n'a pas Méningiome.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the cerebral infarction. The patient in bed 9 has a problem with the glioblastoma multiforme.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
