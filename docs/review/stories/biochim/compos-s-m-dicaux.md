# Relecture — Composés Médicaux (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-007` — Le cas de Hana : Urée (CO(NH₂)₂) anormal(e) / _Hana: the urea (co(nh₂)₂) is not normal_

**Objectif :** Comprendre des résultats : Glucose (C₆H₁₂O₆) normal(e), Urée (CO(NH₂)₂) anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient at the clinic.
   - FR : Hana est un·e patient·e à la clinique.
   - AR : Hana مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The glucose is normal.”
   - FR : Le médecin dit : « Glucose (C₆H₁₂O₆) est normal(e). »
   - AR : يقول الطبيب: «الغلوكوز طبيعي/طبيعية.»
5. **EN** “But the urea (co(nh₂)₂) is not normal.”
   - FR : « Mais Urée (CO(NH₂)₂) n'est pas normal(e). »
   - AR : «لكن اليوريا غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the urea (co(nh₂)₂) normal?”
   - FR : Amine demande : « Urée (CO(NH₂)₂) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل اليوريا طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:glucose:v1` — **a glucose** · FR : Glucose (C₆H₁₂O₆) · AR : الغلوكوز
- `gen:atp:v1` — **a ATP** · FR : ATP (Adénosine triphosphate) · AR : أدينوسين ثلاثي الفوسفات
- `gen:urea-co-nh:v1` — **an urea (co(nh₂)₂)** · FR : Urée (CO(NH₂)₂) · AR : اليوريا

### Questions (acceptées / modèle / hint)
- **q1.** Is the glucose normal?
  - accepté : yes ; yes it is ; the glucose is normal ; it is normal
  - modèle : Yes, the glucose is normal.
- **q2.** Is the urea (co(nh₂)₂) normal?
  - accepté : no ; no it is not ; no it isn't ; the urea (co(nh₂)₂) is not normal ; it is not normal ; it isn't normal
  - modèle : No, the urea (co(nh₂)₂) is not normal.
  - hint (si erreur) — FR : Attention : Urée (CO(NH₂)₂) n'est PAS normal(e).
- **q3.** Which one is not normal: the glucose or the urea (co(nh₂)₂)?
  - accepté : urea (co(nh₂)₂) ; the urea (co(nh₂)₂)
  - modèle : The urea (co(nh₂)₂) is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the ATP normal?
  - accepté : yes ; yes it is ; the ATP is normal
  - modèle : Yes, the ATP is normal.
- **Tt2.** Is the glucose normal?
  - accepté : no ; no it is not ; no it isn't ; the glucose is not normal ; it is not normal
  - modèle : No, the glucose is not normal.
  - hint (si erreur) — FR : Attention : Glucose (C₆H₁₂O₆) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The glucose … normal. (be)` → is · modèle : The glucose is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Hana en deux phrases. Garde la négation. — amorces : The glucose is… / The urea (co(nh₂)₂) is not… — modèle : *The glucose is normal. The urea (co(nh₂)₂) is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : ATP (Adénosine triphosphate) normal(e), mais Glucose (C₆H₁₂O₆) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The ATP is normal. The glucose is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-007` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Glucose (C₆H₁₂O₆) et ATP (Adénosine triphosphate) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient.
   - FR : Sara est un·e patient·e.
   - AR : Sara مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check glucose.”
   - FR : « D'abord, nous vérifions Glucose (C₆H₁₂O₆). »
   - AR : «أولًا، نتحقق من الغلوكوز.»
5. **EN** “Then, we check ATP.”
   - FR : « Ensuite, nous vérifions ATP (Adénosine triphosphate). »
   - AR : «ثم نتحقق من أدينوسين ثلاثي الفوسفات.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:glucose:v1` — **a glucose** · FR : Glucose (C₆H₁₂O₆) · AR : الغلوكوز
- `gen:atp:v1` — **a ATP** · FR : ATP (Adénosine triphosphate) · AR : أدينوسين ثلاثي الفوسفات
- `gen:urea-co-nh:v1` — **an urea (co(nh₂)₂)** · FR : Urée (CO(NH₂)₂) · AR : اليوريا

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : glucose ; we check glucose ; glucose first ; the doctor checks glucose first
  - modèle : First, the doctor checks glucose.
- **q2.** What does the doctor check next?
  - accepté : ATP ; then we check ATP ; ATP next ; the doctor checks ATP next
  - modèle : Then, the doctor checks ATP.
- **q3.** Does the doctor check ATP before glucose?
  - accepté : no ; no he does not ; no he doesn't ; he checks glucose first
  - modèle : No, he checks glucose first, then ATP.
  - hint (si erreur) — FR : Attention : Glucose (C₆H₁₂O₆) vient D'ABORD, ensuite ATP (Adénosine triphosphate).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : urea (co(nh₂)₂) ; urea (co(nh₂)₂) first ; he checks urea (co(nh₂)₂) first
  - modèle : First, the doctor checks urea (co(nh₂)₂).
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check glucose. Then, we check ATP.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Urée (CO(NH₂)₂), ensuite Glucose (C₆H₁₂O₆), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check urea (co(nh₂)₂). Then, we check glucose.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-007` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Glucose (C₆H₁₂O₆) / ATP (Adénosine triphosphate), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the glucose.
   - FR : Le patient du lit 3 a Glucose (C₆H₁₂O₆).
   - AR : مريض السرير ٣ عنده الغلوكوز.
4. **EN** The patient in bed 5 has a problem with the ATP.
   - FR : Le patient du lit 5 a ATP (Adénosine triphosphate).
   - AR : مريض السرير ٥ عنده أدينوسين ثلاثي الفوسفات.
5. **EN** The patient in bed 5 does not have a problem with the glucose.
   - FR : Le patient du lit 5 n'a pas de Glucose (C₆H₁₂O₆).
   - AR : مريض السرير ٥ ليس عنده الغلوكوز.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:glucose:v1` — **a glucose** · FR : Glucose (C₆H₁₂O₆) · AR : الغلوكوز
- `gen:atp:v1` — **a ATP** · FR : ATP (Adénosine triphosphate) · AR : أدينوسين ثلاثي الفوسفات
- `gen:urea-co-nh:v1` — **an urea (co(nh₂)₂)** · FR : Urée (CO(NH₂)₂) · AR : اليوريا

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : glucose ; a glucose ; the patient with glucose
  - modèle : The patient in bed 3 has a glucose.
- **q2.** What does the patient in bed 5 have?
  - accepté : ATP ; a problem with the ATP
  - modèle : He has a problem with the ATP.
- **q3.** Does the patient in bed 5 have glucose?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the glucose ; he doesn't
  - modèle : No, he does not have a problem with the glucose.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS glucose (c₆h₁₂o₆) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : urea (co(nh₂)₂) ; a problem with the urea (co(nh₂)₂)
  - modèle : He has a problem with the urea (co(nh₂)₂).
- **Tt2.** Does the patient in bed 9 have ATP?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the ATP ; he doesn't
  - modèle : No, he does not have a problem with the ATP.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS ATP (Adénosine triphosphate).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a glucose and a ATP. (have)` → have · modèle : The patients have a glucose and a ATP.
- `The patient in bed 5 … not have a glucose. (do)` → does · modèle : The patient in bed 5 does not have a glucose.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the glucose. The patient in bed 5 has a problem with the ATP.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Urée (CO(NH₂)₂), le lit 9 a Glucose (C₆H₁₂O₆) et n'a pas ATP (Adénosine triphosphate).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the urea (co(nh₂)₂). The patient in bed 9 has a problem with the glucose.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
