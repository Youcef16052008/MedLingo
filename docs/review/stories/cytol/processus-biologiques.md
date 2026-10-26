# Relecture — Processus Biologiques (cytol)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `cytol-case-006` — Le cas de Sofiane : Exocytose anormal(e) / _Sofiane: the exocytosis is not normal_

**Objectif :** Comprendre des résultats : Phagocytose normal(e), Exocytose anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient at the clinic.
   - FR : Sofiane est un·e patient·e à la clinique.
   - AR : Sofiane مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The phagocytosis is normal.”
   - FR : Le médecin dit : « Phagocytose est normal(e). »
   - AR : يقول الطبيب: «البلعمة طبيعي/طبيعية.»
5. **EN** “But the exocytosis is not normal.”
   - FR : « Mais Exocytose n'est pas normal(e). »
   - AR : «لكن الإخراج الخلوي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the exocytosis normal?”
   - FR : Lina demande : « Exocytose est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الإخراج الخلوي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:phagocytosis:v1` — **phagocytosis** · FR : Phagocytose · AR : البلعمة
- `gen:endocytosis:v1` — **endocytosis** · FR : Endocytose · AR : الالتقام الخلوي
- `gen:exocytosis:v1` — **exocytosis** · FR : Exocytose · AR : الإخراج الخلوي

### Questions (acceptées / modèle / hint)
- **q1.** Is the phagocytosis normal?
  - accepté : yes ; yes it is ; the phagocytosis is normal ; it is normal
  - modèle : Yes, the phagocytosis is normal.
- **q2.** Is the exocytosis normal?
  - accepté : no ; no it is not ; no it isn't ; the exocytosis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the exocytosis is not normal.
  - hint (si erreur) — FR : Attention : Exocytose n'est PAS normal(e).
- **q3.** Which one is not normal: the phagocytosis or the exocytosis?
  - accepté : exocytosis ; the exocytosis
  - modèle : The exocytosis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the endocytosis normal?
  - accepté : yes ; yes it is ; the endocytosis is normal
  - modèle : Yes, the endocytosis is normal.
- **Tt2.** Is the phagocytosis normal?
  - accepté : no ; no it is not ; no it isn't ; the phagocytosis is not normal ; it is not normal
  - modèle : No, the phagocytosis is not normal.
  - hint (si erreur) — FR : Attention : Phagocytose n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The phagocytosis … normal. (be)` → is · modèle : The phagocytosis is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sofiane en deux phrases. Garde la négation. — amorces : The phagocytosis is… / The exocytosis is not… — modèle : *The phagocytosis is normal. The exocytosis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Endocytose normal(e), mais Phagocytose anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The endocytosis is normal. The phagocytosis is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `cytol-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Phagocytose et Endocytose à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient.
   - FR : Bilal est un·e patient·e.
   - AR : Bilal مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check phagocytosis.”
   - FR : « D'abord, nous vérifions Phagocytose. »
   - AR : «أولًا، نتحقق من البلعمة.»
5. **EN** “Then, we check endocytosis.”
   - FR : « Ensuite, nous vérifions Endocytose. »
   - AR : «ثم نتحقق من الالتقام الخلوي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:phagocytosis:v1` — **phagocytosis** · FR : Phagocytose · AR : البلعمة
- `gen:endocytosis:v1` — **endocytosis** · FR : Endocytose · AR : الالتقام الخلوي
- `gen:exocytosis:v1` — **exocytosis** · FR : Exocytose · AR : الإخراج الخلوي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : phagocytosis ; we check phagocytosis ; phagocytosis first ; the doctor checks phagocytosis first
  - modèle : First, the doctor checks phagocytosis.
- **q2.** What does the doctor check next?
  - accepté : endocytosis ; then we check endocytosis ; endocytosis next ; the doctor checks endocytosis next
  - modèle : Then, the doctor checks endocytosis.
- **q3.** Does the doctor check endocytosis before phagocytosis?
  - accepté : no ; no he does not ; no he doesn't ; he checks phagocytosis first
  - modèle : No, he checks phagocytosis first, then endocytosis.
  - hint (si erreur) — FR : Attention : Phagocytose vient D'ABORD, ensuite Endocytose.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : exocytosis ; exocytosis first ; he checks exocytosis first
  - modèle : First, the doctor checks exocytosis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check phagocytosis. Then, we check endocytosis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Exocytose, ensuite Phagocytose, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check exocytosis. Then, we check phagocytosis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `cytol-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Phagocytose / Endocytose, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the phagocytosis.
   - FR : Le patient du lit 3 a Phagocytose.
   - AR : مريض السرير ٣ عنده البلعمة.
4. **EN** The patient in bed 5 has a problem with the endocytosis.
   - FR : Le patient du lit 5 a Endocytose.
   - AR : مريض السرير ٥ عنده الالتقام الخلوي.
5. **EN** The patient in bed 5 does not have a problem with the phagocytosis.
   - FR : Le patient du lit 5 n'a pas de Phagocytose.
   - AR : مريض السرير ٥ ليس عنده البلعمة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:phagocytosis:v1` — **phagocytosis** · FR : Phagocytose · AR : البلعمة
- `gen:endocytosis:v1` — **endocytosis** · FR : Endocytose · AR : الالتقام الخلوي
- `gen:exocytosis:v1` — **exocytosis** · FR : Exocytose · AR : الإخراج الخلوي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : phagocytosis ; the patient with phagocytosis
  - modèle : The patient in bed 3 has phagocytosis.
- **q2.** What does the patient in bed 5 have?
  - accepté : endocytosis ; a problem with the endocytosis
  - modèle : He has a problem with the endocytosis.
- **q3.** Does the patient in bed 5 have phagocytosis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the phagocytosis ; he doesn't
  - modèle : No, he does not have a problem with the phagocytosis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS phagocytose — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : exocytosis ; a problem with the exocytosis
  - modèle : He has a problem with the exocytosis.
- **Tt2.** Does the patient in bed 9 have endocytosis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the endocytosis ; he doesn't
  - modèle : No, he does not have a problem with the endocytosis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS endocytose.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … phagocytosis and endocytosis. (have)` → have · modèle : The patients have phagocytosis and endocytosis.
- `The patient in bed 5 … not have phagocytosis. (do)` → does · modèle : The patient in bed 5 does not have phagocytosis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the phagocytosis. The patient in bed 5 has a problem with the endocytosis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Exocytose, le lit 9 a Phagocytose et n'a pas Endocytose.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the exocytosis. The patient in bed 9 has a problem with the phagocytosis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
