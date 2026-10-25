# Relecture — Système Nerveux (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-010` — Le cas de Omar : Mésencéphale (Cerveau moyen) anormal(e) / _Omar: the mesencephalon is not normal_

**Objectif :** Comprendre des résultats : Vésicules cérébrales primaires normal(e), Mésencéphale (Cerveau moyen) anormal(e).
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
4. **EN** The doctor says, “The primary brain vesicles is normal.”
   - FR : Le médecin dit : « Vésicules cérébrales primaires est normal(e). »
   - AR : يقول الطبيب: «الحويصلات الدماغية الأولية طبيعي/طبيعية.»
5. **EN** “But the mesencephalon is not normal.”
   - FR : « Mais Mésencéphale (Cerveau moyen) n'est pas normal(e). »
   - AR : «لكن الدماغ المتوسط غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the mesencephalon normal?”
   - FR : Lina demande : « Mésencéphale (Cerveau moyen) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الدماغ المتوسط طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:primary-brain-vesicles:v1` — **primary brain vesicles** · FR : Vésicules cérébrales primaires · AR : الحويصلات الدماغية الأولية
- `gen:prosencephalon:v1` — **a prosencephalon** · FR : Prosencéphale (Cerveau antérieur) · AR : المخ الأمامي
- `gen:mesencephalon:v1` — **a mesencephalon** · FR : Mésencéphale (Cerveau moyen) · AR : الدماغ المتوسط

### Questions (acceptées / modèle / hint)
- **q1.** Is the primary brain vesicles normal?
  - accepté : yes ; yes it is ; the primary brain vesicles is normal ; it is normal
  - modèle : Yes, the primary brain vesicles is normal.
- **q2.** Is the mesencephalon normal?
  - accepté : no ; no it is not ; no it isn't ; the mesencephalon is not normal ; it is not normal ; it isn't normal
  - modèle : No, the mesencephalon is not normal.
  - hint (si erreur) — FR : Attention : Mésencéphale (Cerveau moyen) n'est PAS normal(e).
- **q3.** Which one is not normal: the primary brain vesicles or the mesencephalon?
  - accepté : mesencephalon ; the mesencephalon
  - modèle : The mesencephalon is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the prosencephalon normal?
  - accepté : yes ; yes it is ; the prosencephalon is normal
  - modèle : Yes, the prosencephalon is normal.
- **Tt2.** Is the primary brain vesicles normal?
  - accepté : no ; no it is not ; no it isn't ; the primary brain vesicles is not normal ; it is not normal
  - modèle : No, the primary brain vesicles is not normal.
  - hint (si erreur) — FR : Attention : Vésicules cérébrales primaires n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The primary brain vesicles … normal. (be)` → is · modèle : The primary brain vesicles is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The primary brain vesicles is… / The mesencephalon is not… — modèle : *The primary brain vesicles is normal. The mesencephalon is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Prosencéphale (Cerveau antérieur) normal(e), mais Vésicules cérébrales primaires anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The prosencephalon is normal. The primary brain vesicles is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-010` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Vésicules cérébrales primaires et Prosencéphale (Cerveau antérieur) à un patient.
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
4. **EN** “First, we check primary brain vesicles.”
   - FR : « D'abord, nous vérifions Vésicules cérébrales primaires. »
   - AR : «أولًا، نتحقق من الحويصلات الدماغية الأولية.»
5. **EN** “Then, we check prosencephalon.”
   - FR : « Ensuite, nous vérifions Prosencéphale (Cerveau antérieur). »
   - AR : «ثم نتحقق من المخ الأمامي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:primary-brain-vesicles:v1` — **primary brain vesicles** · FR : Vésicules cérébrales primaires · AR : الحويصلات الدماغية الأولية
- `gen:prosencephalon:v1` — **a prosencephalon** · FR : Prosencéphale (Cerveau antérieur) · AR : المخ الأمامي
- `gen:mesencephalon:v1` — **a mesencephalon** · FR : Mésencéphale (Cerveau moyen) · AR : الدماغ المتوسط

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : primary brain vesicles ; we check primary brain vesicles ; primary brain vesicles first ; the doctor checks primary brain vesicles first
  - modèle : First, the doctor checks primary brain vesicles.
- **q2.** What does the doctor check next?
  - accepté : prosencephalon ; then we check prosencephalon ; prosencephalon next ; the doctor checks prosencephalon next
  - modèle : Then, the doctor checks prosencephalon.
- **q3.** Does the doctor check prosencephalon before primary brain vesicles?
  - accepté : no ; no he does not ; no he doesn't ; he checks primary brain vesicles first
  - modèle : No, he checks primary brain vesicles first, then prosencephalon.
  - hint (si erreur) — FR : Attention : Vésicules cérébrales primaires vient D'ABORD, ensuite Prosencéphale (Cerveau antérieur).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : mesencephalon ; mesencephalon first ; he checks mesencephalon first
  - modèle : First, the doctor checks mesencephalon.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check primary brain vesicles. Then, we check prosencephalon.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Mésencéphale (Cerveau moyen), ensuite Vésicules cérébrales primaires, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check mesencephalon. Then, we check primary brain vesicles.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-010` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Vésicules cérébrales primaires / Prosencéphale (Cerveau antérieur), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the primary brain vesicles.
   - FR : Le patient du lit 3 a Vésicules cérébrales primaires.
   - AR : مريض السرير ٣ عنده الحويصلات الدماغية الأولية.
4. **EN** The patient in bed 5 has a problem with the prosencephalon.
   - FR : Le patient du lit 5 a Prosencéphale (Cerveau antérieur).
   - AR : مريض السرير ٥ عنده المخ الأمامي.
5. **EN** The patient in bed 5 does not have a problem with the primary brain vesicles.
   - FR : Le patient du lit 5 n'a pas de Vésicules cérébrales primaires.
   - AR : مريض السرير ٥ ليس عنده الحويصلات الدماغية الأولية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:primary-brain-vesicles:v1` — **primary brain vesicles** · FR : Vésicules cérébrales primaires · AR : الحويصلات الدماغية الأولية
- `gen:prosencephalon:v1` — **a prosencephalon** · FR : Prosencéphale (Cerveau antérieur) · AR : المخ الأمامي
- `gen:mesencephalon:v1` — **a mesencephalon** · FR : Mésencéphale (Cerveau moyen) · AR : الدماغ المتوسط

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : primary brain vesicles ; the patient with primary brain vesicles
  - modèle : The patient in bed 3 has primary brain vesicles.
- **q2.** What does the patient in bed 5 have?
  - accepté : prosencephalon ; a problem with the prosencephalon
  - modèle : He has a problem with the prosencephalon.
- **q3.** Does the patient in bed 5 have primary brain vesicles?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the primary brain vesicles ; he doesn't
  - modèle : No, he does not have a problem with the primary brain vesicles.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS vésicules cérébrales primaires — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : mesencephalon ; a problem with the mesencephalon
  - modèle : He has a problem with the mesencephalon.
- **Tt2.** Does the patient in bed 9 have prosencephalon?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the prosencephalon ; he doesn't
  - modèle : No, he does not have a problem with the prosencephalon.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS prosencéphale (cerveau antérieur).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … primary brain vesicles and a prosencephalon. (have)` → have · modèle : The patients have primary brain vesicles and a prosencephalon.
- `The patient in bed 5 … not have primary brain vesicles. (do)` → does · modèle : The patient in bed 5 does not have primary brain vesicles.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the primary brain vesicles. The patient in bed 5 has a problem with the prosencephalon.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Mésencéphale (Cerveau moyen), le lit 9 a Vésicules cérébrales primaires et n'a pas Prosencéphale (Cerveau antérieur).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the mesencephalon. The patient in bed 9 has a problem with the primary brain vesicles.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
