# Relecture — Hydrocarbures (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-012` — Le cas de Karim : Éthane (C₂H₆) anormal(e) / _Karim: the ethane is not normal_

**Objectif :** Comprendre des résultats : Alcane normal(e), Éthane (C₂H₆) anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient at the clinic.
   - FR : Karim est un·e patient·e à la clinique.
   - AR : Karim مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The alkane is normal.”
   - FR : Le médecin dit : « Alcane est normal(e). »
   - AR : يقول الطبيب: «الألكان طبيعي/طبيعية.»
5. **EN** “But the ethane is not normal.”
   - FR : « Mais Éthane (C₂H₆) n'est pas normal(e). »
   - AR : «لكن الإيثان غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the ethane normal?”
   - FR : Lina demande : « Éthane (C₂H₆) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الإيثان طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:alkane:v1` — **an alkane** · FR : Alcane · AR : الألكان
- `gen:methane:v1` — **a methane** · FR : Méthane (CH₄) · AR : الميثان
- `gen:ethane:v1` — **an ethane** · FR : Éthane (C₂H₆) · AR : الإيثان

### Questions (acceptées / modèle / hint)
- **q1.** Is the alkane normal?
  - accepté : yes ; yes it is ; the alkane is normal ; it is normal
  - modèle : Yes, the alkane is normal.
- **q2.** Is the ethane normal?
  - accepté : no ; no it is not ; no it isn't ; the ethane is not normal ; it is not normal ; it isn't normal
  - modèle : No, the ethane is not normal.
  - hint (si erreur) — FR : Attention : Éthane (C₂H₆) n'est PAS normal(e).
- **q3.** Which one is not normal: the alkane or the ethane?
  - accepté : ethane ; the ethane
  - modèle : The ethane is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the methane normal?
  - accepté : yes ; yes it is ; the methane is normal
  - modèle : Yes, the methane is normal.
- **Tt2.** Is the alkane normal?
  - accepté : no ; no it is not ; no it isn't ; the alkane is not normal ; it is not normal
  - modèle : No, the alkane is not normal.
  - hint (si erreur) — FR : Attention : Alcane n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The alkane … normal. (be)` → is · modèle : The alkane is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The alkane is… / The ethane is not… — modèle : *The alkane is normal. The ethane is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Méthane (CH₄) normal(e), mais Alcane anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The methane is normal. The alkane is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-012` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Alcane et Méthane (CH₄) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient.
   - FR : Youssef est un·e patient·e.
   - AR : Youssef مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check alkane.”
   - FR : « D'abord, nous vérifions Alcane. »
   - AR : «أولًا، نتحقق من الألكان.»
5. **EN** “Then, we check methane.”
   - FR : « Ensuite, nous vérifions Méthane (CH₄). »
   - AR : «ثم نتحقق من الميثان.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:alkane:v1` — **an alkane** · FR : Alcane · AR : الألكان
- `gen:methane:v1` — **a methane** · FR : Méthane (CH₄) · AR : الميثان
- `gen:ethane:v1` — **an ethane** · FR : Éthane (C₂H₆) · AR : الإيثان

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : alkane ; we check alkane ; alkane first ; the doctor checks alkane first
  - modèle : First, the doctor checks alkane.
- **q2.** What does the doctor check next?
  - accepté : methane ; then we check methane ; methane next ; the doctor checks methane next
  - modèle : Then, the doctor checks methane.
- **q3.** Does the doctor check methane before alkane?
  - accepté : no ; no he does not ; no he doesn't ; he checks alkane first
  - modèle : No, he checks alkane first, then methane.
  - hint (si erreur) — FR : Attention : Alcane vient D'ABORD, ensuite Méthane (CH₄).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : ethane ; ethane first ; he checks ethane first
  - modèle : First, the doctor checks ethane.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check alkane. Then, we check methane.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Éthane (C₂H₆), ensuite Alcane, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check ethane. Then, we check alkane.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-012` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Alcane / Méthane (CH₄), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the alkane.
   - FR : Le patient du lit 3 a Alcane.
   - AR : مريض السرير ٣ عنده الألكان.
4. **EN** The patient in bed 5 has a problem with the methane.
   - FR : Le patient du lit 5 a Méthane (CH₄).
   - AR : مريض السرير ٥ عنده الميثان.
5. **EN** The patient in bed 5 does not have a problem with the alkane.
   - FR : Le patient du lit 5 n'a pas de Alcane.
   - AR : مريض السرير ٥ ليس عنده الألكان.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:alkane:v1` — **an alkane** · FR : Alcane · AR : الألكان
- `gen:methane:v1` — **a methane** · FR : Méthane (CH₄) · AR : الميثان
- `gen:ethane:v1` — **an ethane** · FR : Éthane (C₂H₆) · AR : الإيثان

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : alkane ; an alkane ; the patient with alkane
  - modèle : The patient in bed 3 has an alkane.
- **q2.** What does the patient in bed 5 have?
  - accepté : methane ; a problem with the methane
  - modèle : He has a problem with the methane.
- **q3.** Does the patient in bed 5 have alkane?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the alkane ; he doesn't
  - modèle : No, he does not have a problem with the alkane.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS alcane — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : ethane ; a problem with the ethane
  - modèle : He has a problem with the ethane.
- **Tt2.** Does the patient in bed 9 have methane?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the methane ; he doesn't
  - modèle : No, he does not have a problem with the methane.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS méthane (ch₄).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an alkane and a methane. (have)` → have · modèle : The patients have an alkane and a methane.
- `The patient in bed 5 … not have an alkane. (do)` → does · modèle : The patient in bed 5 does not have an alkane.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the alkane. The patient in bed 5 has a problem with the methane.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Éthane (C₂H₆), le lit 9 a Alcane et n'a pas Méthane (CH₄).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the ethane. The patient in bed 9 has a problem with the alkane.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
