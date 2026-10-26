# Relecture — Organes (anat)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anat-case-007` — Le cas de Hana : Cavité crânienne anormal(e) / _Hana: the cranial cavity is not normal_

**Objectif :** Comprendre des résultats : Organe normal(e), Cavité crânienne anormal(e).
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
4. **EN** The doctor says, “The organ is normal.”
   - FR : Le médecin dit : « Organe est normal(e). »
   - AR : يقول الطبيب: «عضو طبيعي/طبيعية.»
5. **EN** “But the cranial cavity is not normal.”
   - FR : « Mais Cavité crânienne n'est pas normal(e). »
   - AR : «لكن التجويف القحفي غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the cranial cavity normal?”
   - FR : Amine demande : « Cavité crânienne est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل التجويف القحفي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:organ:v1` — **an organ** · FR : Organe · AR : عضو
- `gen:body-cavity:v1` — **a body cavity** · FR : Cavité corporelle · AR : تجويف الجسم
- `gen:cranial-cavity:v1` — **a cranial cavity** · FR : Cavité crânienne · AR : التجويف القحفي

### Questions (acceptées / modèle / hint)
- **q1.** Is the organ normal?
  - accepté : yes ; yes it is ; the organ is normal ; it is normal
  - modèle : Yes, the organ is normal.
- **q2.** Is the cranial cavity normal?
  - accepté : no ; no it is not ; no it isn't ; the cranial cavity is not normal ; it is not normal ; it isn't normal
  - modèle : No, the cranial cavity is not normal.
  - hint (si erreur) — FR : Attention : Cavité crânienne n'est PAS normal(e).
- **q3.** Which one is not normal: the organ or the cranial cavity?
  - accepté : cranial cavity ; the cranial cavity
  - modèle : The cranial cavity is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the body cavity normal?
  - accepté : yes ; yes it is ; the body cavity is normal
  - modèle : Yes, the body cavity is normal.
- **Tt2.** Is the organ normal?
  - accepté : no ; no it is not ; no it isn't ; the organ is not normal ; it is not normal
  - modèle : No, the organ is not normal.
  - hint (si erreur) — FR : Attention : Organe n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The organ … normal. (be)` → is · modèle : The organ is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Hana en deux phrases. Garde la négation. — amorces : The organ is… / The cranial cavity is not… — modèle : *The organ is normal. The cranial cavity is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Cavité corporelle normal(e), mais Organe anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The body cavity is normal. The organ is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anat-explain-007` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Organe et Cavité corporelle à un patient.
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
4. **EN** “First, we check organ.”
   - FR : « D'abord, nous vérifions Organe. »
   - AR : «أولًا، نتحقق من عضو.»
5. **EN** “Then, we check body cavity.”
   - FR : « Ensuite, nous vérifions Cavité corporelle. »
   - AR : «ثم نتحقق من تجويف الجسم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:organ:v1` — **an organ** · FR : Organe · AR : عضو
- `gen:body-cavity:v1` — **a body cavity** · FR : Cavité corporelle · AR : تجويف الجسم
- `gen:cranial-cavity:v1` — **a cranial cavity** · FR : Cavité crânienne · AR : التجويف القحفي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : organ ; we check organ ; organ first ; the doctor checks organ first
  - modèle : First, the doctor checks organ.
- **q2.** What does the doctor check next?
  - accepté : body cavity ; then we check body cavity ; body cavity next ; the doctor checks body cavity next
  - modèle : Then, the doctor checks body cavity.
- **q3.** Does the doctor check body cavity before organ?
  - accepté : no ; no he does not ; no he doesn't ; he checks organ first
  - modèle : No, he checks organ first, then body cavity.
  - hint (si erreur) — FR : Attention : Organe vient D'ABORD, ensuite Cavité corporelle.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : cranial cavity ; cranial cavity first ; he checks cranial cavity first
  - modèle : First, the doctor checks cranial cavity.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check organ. Then, we check body cavity.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Cavité crânienne, ensuite Organe, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check cranial cavity. Then, we check organ.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anat-handover-007` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Organe / Cavité corporelle, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the organ.
   - FR : Le patient du lit 3 a Organe.
   - AR : مريض السرير ٣ عنده عضو.
4. **EN** The patient in bed 5 has a problem with the body cavity.
   - FR : Le patient du lit 5 a Cavité corporelle.
   - AR : مريض السرير ٥ عنده تجويف الجسم.
5. **EN** The patient in bed 5 does not have a problem with the organ.
   - FR : Le patient du lit 5 n'a pas de Organe.
   - AR : مريض السرير ٥ ليس عنده عضو.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:organ:v1` — **an organ** · FR : Organe · AR : عضو
- `gen:body-cavity:v1` — **a body cavity** · FR : Cavité corporelle · AR : تجويف الجسم
- `gen:cranial-cavity:v1` — **a cranial cavity** · FR : Cavité crânienne · AR : التجويف القحفي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : organ ; an organ ; the patient with organ
  - modèle : The patient in bed 3 has an organ.
- **q2.** What does the patient in bed 5 have?
  - accepté : body cavity ; a problem with the body cavity
  - modèle : He has a problem with the body cavity.
- **q3.** Does the patient in bed 5 have organ?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the organ ; he doesn't
  - modèle : No, he does not have a problem with the organ.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS organe — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : cranial cavity ; a problem with the cranial cavity
  - modèle : He has a problem with the cranial cavity.
- **Tt2.** Does the patient in bed 9 have body cavity?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the body cavity ; he doesn't
  - modèle : No, he does not have a problem with the body cavity.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS cavité corporelle.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an organ and a body cavity. (have)` → have · modèle : The patients have an organ and a body cavity.
- `The patient in bed 5 … not have an organ. (do)` → does · modèle : The patient in bed 5 does not have an organ.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the organ. The patient in bed 5 has a problem with the body cavity.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Cavité crânienne, le lit 9 a Organe et n'a pas Cavité corporelle.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the cranial cavity. The patient in bed 9 has a problem with the organ.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
