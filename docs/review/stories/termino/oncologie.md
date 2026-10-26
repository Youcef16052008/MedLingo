# Relecture — Oncologie (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-020` — Le cas de Bilal : Métastase anormal(e) / _Bilal: the metastasis is not normal_

**Objectif :** Comprendre des résultats : Oncologie normal(e), Métastase anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The oncology is normal.”
   - FR : Le médecin dit : « Oncologie est normal(e). »
   - AR : يقول الطبيب: «علم الأورام طبيعي/طبيعية.»
5. **EN** “But the metastasis is not normal.”
   - FR : « Mais Métastase n'est pas normal(e). »
   - AR : «لكن النقيلة (الانتشار) غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the metastasis normal?”
   - FR : Lina demande : « Métastase est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل النقيلة (الانتشار) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:oncology:v1` — **an oncology** · FR : Oncologie · AR : علم الأورام
- `gen:tumor:v1` — **a tumor** · FR : Tumeur (Néoplasme) · AR : الورم
- `gen:metastasis:v1` — **metastasis** · FR : Métastase · AR : النقيلة (الانتشار)

### Questions (acceptées / modèle / hint)
- **q1.** Is the oncology normal?
  - accepté : yes ; yes it is ; the oncology is normal ; it is normal
  - modèle : Yes, the oncology is normal.
- **q2.** Is the metastasis normal?
  - accepté : no ; no it is not ; no it isn't ; the metastasis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the metastasis is not normal.
  - hint (si erreur) — FR : Attention : Métastase n'est PAS normal(e).
- **q3.** Which one is not normal: the oncology or the metastasis?
  - accepté : metastasis ; the metastasis
  - modèle : The metastasis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the tumor normal?
  - accepté : yes ; yes it is ; the tumor is normal
  - modèle : Yes, the tumor is normal.
- **Tt2.** Is the oncology normal?
  - accepté : no ; no it is not ; no it isn't ; the oncology is not normal ; it is not normal
  - modèle : No, the oncology is not normal.
  - hint (si erreur) — FR : Attention : Oncologie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The oncology … normal. (be)` → is · modèle : The oncology is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The oncology is… / The metastasis is not… — modèle : *The oncology is normal. The metastasis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Tumeur (Néoplasme) normal(e), mais Oncologie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The tumor is normal. The oncology is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-020` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Oncologie et Tumeur (Néoplasme) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient.
   - FR : Omar est un·e patient·e.
   - AR : Omar مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check oncology.”
   - FR : « D'abord, nous vérifions Oncologie. »
   - AR : «أولًا، نتحقق من علم الأورام.»
5. **EN** “Then, we check tumor.”
   - FR : « Ensuite, nous vérifions Tumeur (Néoplasme). »
   - AR : «ثم نتحقق من الورم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:oncology:v1` — **an oncology** · FR : Oncologie · AR : علم الأورام
- `gen:tumor:v1` — **a tumor** · FR : Tumeur (Néoplasme) · AR : الورم
- `gen:metastasis:v1` — **metastasis** · FR : Métastase · AR : النقيلة (الانتشار)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : oncology ; we check oncology ; oncology first ; the doctor checks oncology first
  - modèle : First, the doctor checks oncology.
- **q2.** What does the doctor check next?
  - accepté : tumor ; then we check tumor ; tumor next ; the doctor checks tumor next
  - modèle : Then, the doctor checks tumor.
- **q3.** Does the doctor check tumor before oncology?
  - accepté : no ; no he does not ; no he doesn't ; he checks oncology first
  - modèle : No, he checks oncology first, then tumor.
  - hint (si erreur) — FR : Attention : Oncologie vient D'ABORD, ensuite Tumeur (Néoplasme).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : metastasis ; metastasis first ; he checks metastasis first
  - modèle : First, the doctor checks metastasis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check oncology. Then, we check tumor.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Métastase, ensuite Oncologie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check metastasis. Then, we check oncology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-020` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Oncologie / Tumeur (Néoplasme), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the oncology.
   - FR : Le patient du lit 3 a Oncologie.
   - AR : مريض السرير ٣ عنده علم الأورام.
4. **EN** The patient in bed 5 has a problem with the tumor.
   - FR : Le patient du lit 5 a Tumeur (Néoplasme).
   - AR : مريض السرير ٥ عنده الورم.
5. **EN** The patient in bed 5 does not have a problem with the oncology.
   - FR : Le patient du lit 5 n'a pas de Oncologie.
   - AR : مريض السرير ٥ ليس عنده علم الأورام.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:oncology:v1` — **an oncology** · FR : Oncologie · AR : علم الأورام
- `gen:tumor:v1` — **a tumor** · FR : Tumeur (Néoplasme) · AR : الورم
- `gen:metastasis:v1` — **metastasis** · FR : Métastase · AR : النقيلة (الانتشار)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : oncology ; an oncology ; the patient with oncology
  - modèle : The patient in bed 3 has an oncology.
- **q2.** What does the patient in bed 5 have?
  - accepté : tumor ; a problem with the tumor
  - modèle : He has a problem with the tumor.
- **q3.** Does the patient in bed 5 have oncology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the oncology ; he doesn't
  - modèle : No, he does not have a problem with the oncology.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS oncologie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : metastasis ; a problem with the metastasis
  - modèle : He has a problem with the metastasis.
- **Tt2.** Does the patient in bed 9 have tumor?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the tumor ; he doesn't
  - modèle : No, he does not have a problem with the tumor.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS tumeur (néoplasme).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an oncology and a tumor. (have)` → have · modèle : The patients have an oncology and a tumor.
- `The patient in bed 5 … not have an oncology. (do)` → does · modèle : The patient in bed 5 does not have an oncology.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the oncology. The patient in bed 5 has a problem with the tumor.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Métastase, le lit 9 a Oncologie et n'a pas Tumeur (Néoplasme).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the metastasis. The patient in bed 9 has a problem with the oncology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
