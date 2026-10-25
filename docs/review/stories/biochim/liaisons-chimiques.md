# Relecture — Liaisons Chimiques (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-014` — Le cas de Youssef : Liaison simple anormal(e) / _Youssef: the single bond is not normal_

**Objectif :** Comprendre des résultats : Liaison covalente polaire normal(e), Liaison simple anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The polar covalent bond is normal.”
   - FR : Le médecin dit : « Liaison covalente polaire est normal(e). »
   - AR : يقول الطبيب: «الرابطة التساهمية القطبية طبيعي/طبيعية.»
5. **EN** “But the single bond is not normal.”
   - FR : « Mais Liaison simple n'est pas normal(e). »
   - AR : «لكن الرابطة الأحادية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the single bond normal?”
   - FR : Lina demande : « Liaison simple est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الرابطة الأحادية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:polar-covalent-bond:v1` — **a polar covalent bond** · FR : Liaison covalente polaire · AR : الرابطة التساهمية القطبية
- `gen:non-polar-covalent-bond:v1` — **a non-polar covalent bond** · FR : Liaison covalente non polaire (apolaire) · AR : الرابطة التساهمية غير القطبية
- `gen:single-bond:v1` — **a single bond** · FR : Liaison simple · AR : الرابطة الأحادية

### Questions (acceptées / modèle / hint)
- **q1.** Is the polar covalent bond normal?
  - accepté : yes ; yes it is ; the polar covalent bond is normal ; it is normal
  - modèle : Yes, the polar covalent bond is normal.
- **q2.** Is the single bond normal?
  - accepté : no ; no it is not ; no it isn't ; the single bond is not normal ; it is not normal ; it isn't normal
  - modèle : No, the single bond is not normal.
  - hint (si erreur) — FR : Attention : Liaison simple n'est PAS normal(e).
- **q3.** Which one is not normal: the polar covalent bond or the single bond?
  - accepté : single bond ; the single bond
  - modèle : The single bond is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the non-polar covalent bond normal?
  - accepté : yes ; yes it is ; the non-polar covalent bond is normal
  - modèle : Yes, the non-polar covalent bond is normal.
- **Tt2.** Is the polar covalent bond normal?
  - accepté : no ; no it is not ; no it isn't ; the polar covalent bond is not normal ; it is not normal
  - modèle : No, the polar covalent bond is not normal.
  - hint (si erreur) — FR : Attention : Liaison covalente polaire n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The polar covalent bond … normal. (be)` → is · modèle : The polar covalent bond is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The polar covalent bond is… / The single bond is not… — modèle : *The polar covalent bond is normal. The single bond is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Liaison covalente non polaire (apolaire) normal(e), mais Liaison covalente polaire anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The non-polar covalent bond is normal. The polar covalent bond is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-014` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Liaison covalente polaire et Liaison covalente non polaire (apolaire) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient.
   - FR : Farid est un·e patient·e.
   - AR : Farid مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check polar covalent bond.”
   - FR : « D'abord, nous vérifions Liaison covalente polaire. »
   - AR : «أولًا، نتحقق من الرابطة التساهمية القطبية.»
5. **EN** “Then, we check non-polar covalent bond.”
   - FR : « Ensuite, nous vérifions Liaison covalente non polaire (apolaire). »
   - AR : «ثم نتحقق من الرابطة التساهمية غير القطبية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:polar-covalent-bond:v1` — **a polar covalent bond** · FR : Liaison covalente polaire · AR : الرابطة التساهمية القطبية
- `gen:non-polar-covalent-bond:v1` — **a non-polar covalent bond** · FR : Liaison covalente non polaire (apolaire) · AR : الرابطة التساهمية غير القطبية
- `gen:single-bond:v1` — **a single bond** · FR : Liaison simple · AR : الرابطة الأحادية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : polar covalent bond ; we check polar covalent bond ; polar covalent bond first ; the doctor checks polar covalent bond first
  - modèle : First, the doctor checks polar covalent bond.
- **q2.** What does the doctor check next?
  - accepté : non-polar covalent bond ; then we check non-polar covalent bond ; non-polar covalent bond next ; the doctor checks non-polar covalent bond next
  - modèle : Then, the doctor checks non-polar covalent bond.
- **q3.** Does the doctor check non-polar covalent bond before polar covalent bond?
  - accepté : no ; no he does not ; no he doesn't ; he checks polar covalent bond first
  - modèle : No, he checks polar covalent bond first, then non-polar covalent bond.
  - hint (si erreur) — FR : Attention : Liaison covalente polaire vient D'ABORD, ensuite Liaison covalente non polaire (apolaire).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : single bond ; single bond first ; he checks single bond first
  - modèle : First, the doctor checks single bond.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check polar covalent bond. Then, we check non-polar covalent bond.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Liaison simple, ensuite Liaison covalente polaire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check single bond. Then, we check polar covalent bond.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-014` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Liaison covalente polaire / Liaison covalente non polaire (apolaire), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the polar covalent bond.
   - FR : Le patient du lit 3 a Liaison covalente polaire.
   - AR : مريض السرير ٣ عنده الرابطة التساهمية القطبية.
4. **EN** The patient in bed 5 has a problem with the non-polar covalent bond.
   - FR : Le patient du lit 5 a Liaison covalente non polaire (apolaire).
   - AR : مريض السرير ٥ عنده الرابطة التساهمية غير القطبية.
5. **EN** The patient in bed 5 does not have a problem with the polar covalent bond.
   - FR : Le patient du lit 5 n'a pas de Liaison covalente polaire.
   - AR : مريض السرير ٥ ليس عنده الرابطة التساهمية القطبية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:polar-covalent-bond:v1` — **a polar covalent bond** · FR : Liaison covalente polaire · AR : الرابطة التساهمية القطبية
- `gen:non-polar-covalent-bond:v1` — **a non-polar covalent bond** · FR : Liaison covalente non polaire (apolaire) · AR : الرابطة التساهمية غير القطبية
- `gen:single-bond:v1` — **a single bond** · FR : Liaison simple · AR : الرابطة الأحادية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : polar covalent bond ; a polar covalent bond ; the patient with polar covalent bond
  - modèle : The patient in bed 3 has a polar covalent bond.
- **q2.** What does the patient in bed 5 have?
  - accepté : non-polar covalent bond ; a problem with the non-polar covalent bond
  - modèle : He has a problem with the non-polar covalent bond.
- **q3.** Does the patient in bed 5 have polar covalent bond?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the polar covalent bond ; he doesn't
  - modèle : No, he does not have a problem with the polar covalent bond.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS liaison covalente polaire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : single bond ; a problem with the single bond
  - modèle : He has a problem with the single bond.
- **Tt2.** Does the patient in bed 9 have non-polar covalent bond?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the non-polar covalent bond ; he doesn't
  - modèle : No, he does not have a problem with the non-polar covalent bond.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS liaison covalente non polaire (apolaire).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a polar covalent bond and a non-polar covalent bond. (have)` → have · modèle : The patients have a polar covalent bond and a non-polar covalent bond.
- `The patient in bed 5 … not have a polar covalent bond. (do)` → does · modèle : The patient in bed 5 does not have a polar covalent bond.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the polar covalent bond. The patient in bed 5 has a problem with the non-polar covalent bond.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Liaison simple, le lit 9 a Liaison covalente polaire et n'a pas Liaison covalente non polaire (apolaire).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the single bond. The patient in bed 9 has a problem with the polar covalent bond.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
