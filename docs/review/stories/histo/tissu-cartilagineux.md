# Relecture — Tissu Cartilagineux (histo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `histo-case-005` — Le cas de Meriem : Chondroblaste anormal(e) / _Meriem: the chondroblast is not normal_

**Objectif :** Comprendre des résultats : Cartilage normal(e), Chondroblaste anormal(e).
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
4. **EN** The doctor says, “The cartilage is normal.”
   - FR : Le médecin dit : « Cartilage est normal(e). »
   - AR : يقول الطبيب: «الغضروف طبيعي/طبيعية.»
5. **EN** “But the chondroblast is not normal.”
   - FR : « Mais Chondroblaste n'est pas normal(e). »
   - AR : «لكن أرومة غضروفية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the chondroblast normal?”
   - FR : Amine demande : « Chondroblaste est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل أرومة غضروفية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:cartilage:v1` — **a cartilage** · FR : Cartilage · AR : الغضروف
- `gen:chondrocyte:v1` — **a chondrocyte** · FR : Chondrocyte · AR : الخلية الغضروفية
- `gen:chondroblast:v1` — **a chondroblast** · FR : Chondroblaste · AR : أرومة غضروفية

### Questions (acceptées / modèle / hint)
- **q1.** Is the cartilage normal?
  - accepté : yes ; yes it is ; the cartilage is normal ; it is normal
  - modèle : Yes, the cartilage is normal.
- **q2.** Is the chondroblast normal?
  - accepté : no ; no it is not ; no it isn't ; the chondroblast is not normal ; it is not normal ; it isn't normal
  - modèle : No, the chondroblast is not normal.
  - hint (si erreur) — FR : Attention : Chondroblaste n'est PAS normal(e).
- **q3.** Which one is not normal: the cartilage or the chondroblast?
  - accepté : chondroblast ; the chondroblast
  - modèle : The chondroblast is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the chondrocyte normal?
  - accepté : yes ; yes it is ; the chondrocyte is normal
  - modèle : Yes, the chondrocyte is normal.
- **Tt2.** Is the cartilage normal?
  - accepté : no ; no it is not ; no it isn't ; the cartilage is not normal ; it is not normal
  - modèle : No, the cartilage is not normal.
  - hint (si erreur) — FR : Attention : Cartilage n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The cartilage … normal. (be)` → is · modèle : The cartilage is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The cartilage is… / The chondroblast is not… — modèle : *The cartilage is normal. The chondroblast is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Chondrocyte normal(e), mais Cartilage anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The chondrocyte is normal. The cartilage is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `histo-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Cartilage et Chondrocyte à un patient.
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
4. **EN** “First, we check cartilage.”
   - FR : « D'abord, nous vérifions Cartilage. »
   - AR : «أولًا، نتحقق من الغضروف.»
5. **EN** “Then, we check chondrocyte.”
   - FR : « Ensuite, nous vérifions Chondrocyte. »
   - AR : «ثم نتحقق من الخلية الغضروفية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cartilage:v1` — **a cartilage** · FR : Cartilage · AR : الغضروف
- `gen:chondrocyte:v1` — **a chondrocyte** · FR : Chondrocyte · AR : الخلية الغضروفية
- `gen:chondroblast:v1` — **a chondroblast** · FR : Chondroblaste · AR : أرومة غضروفية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cartilage ; we check cartilage ; cartilage first ; the doctor checks cartilage first
  - modèle : First, the doctor checks cartilage.
- **q2.** What does the doctor check next?
  - accepté : chondrocyte ; then we check chondrocyte ; chondrocyte next ; the doctor checks chondrocyte next
  - modèle : Then, the doctor checks chondrocyte.
- **q3.** Does the doctor check chondrocyte before cartilage?
  - accepté : no ; no he does not ; no he doesn't ; he checks cartilage first
  - modèle : No, he checks cartilage first, then chondrocyte.
  - hint (si erreur) — FR : Attention : Cartilage vient D'ABORD, ensuite Chondrocyte.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : chondroblast ; chondroblast first ; he checks chondroblast first
  - modèle : First, the doctor checks chondroblast.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cartilage. Then, we check chondrocyte.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Chondroblaste, ensuite Cartilage, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check chondroblast. Then, we check cartilage.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `histo-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cartilage / Chondrocyte, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the cartilage.
   - FR : Le patient du lit 3 a Cartilage.
   - AR : مريض السرير ٣ عنده الغضروف.
4. **EN** The patient in bed 5 has a problem with the chondrocyte.
   - FR : Le patient du lit 5 a Chondrocyte.
   - AR : مريض السرير ٥ عنده الخلية الغضروفية.
5. **EN** The patient in bed 5 does not have a problem with the cartilage.
   - FR : Le patient du lit 5 n'a pas de Cartilage.
   - AR : مريض السرير ٥ ليس عنده الغضروف.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:cartilage:v1` — **a cartilage** · FR : Cartilage · AR : الغضروف
- `gen:chondrocyte:v1` — **a chondrocyte** · FR : Chondrocyte · AR : الخلية الغضروفية
- `gen:chondroblast:v1` — **a chondroblast** · FR : Chondroblaste · AR : أرومة غضروفية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cartilage ; a cartilage ; the patient with cartilage
  - modèle : The patient in bed 3 has a cartilage.
- **q2.** What does the patient in bed 5 have?
  - accepté : chondrocyte ; a problem with the chondrocyte
  - modèle : He has a problem with the chondrocyte.
- **q3.** Does the patient in bed 5 have cartilage?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the cartilage ; he doesn't
  - modèle : No, he does not have a problem with the cartilage.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cartilage — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : chondroblast ; a problem with the chondroblast
  - modèle : He has a problem with the chondroblast.
- **Tt2.** Does the patient in bed 9 have chondrocyte?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the chondrocyte ; he doesn't
  - modèle : No, he does not have a problem with the chondrocyte.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS chondrocyte.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a cartilage and a chondrocyte. (have)` → have · modèle : The patients have a cartilage and a chondrocyte.
- `The patient in bed 5 … not have a cartilage. (do)` → does · modèle : The patient in bed 5 does not have a cartilage.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the cartilage. The patient in bed 5 has a problem with the chondrocyte.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Chondroblaste, le lit 9 a Cartilage et n'a pas Chondrocyte.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the chondroblast. The patient in bed 9 has a problem with the cartilage.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
