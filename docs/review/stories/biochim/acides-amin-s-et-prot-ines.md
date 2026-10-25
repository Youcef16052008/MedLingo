# Relecture — Acides Aminés et Protéines (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-000` — Le cas de Karim : Acide aminé non essentiel anormal(e) / _Karim: the non-essential amino acid is not normal_

**Objectif :** Comprendre des résultats : Acide aminé normal(e), Acide aminé non essentiel anormal(e).
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
4. **EN** The doctor says, “The amino acid is normal.”
   - FR : Le médecin dit : « Acide aminé est normal(e). »
   - AR : يقول الطبيب: «الحمض الأميني طبيعي/طبيعية.»
5. **EN** “But the non-essential amino acid is not normal.”
   - FR : « Mais Acide aminé non essentiel n'est pas normal(e). »
   - AR : «لكن حمض أميني غير أساسي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the non-essential amino acid normal?”
   - FR : Lina demande : « Acide aminé non essentiel est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل حمض أميني غير أساسي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:amino-acid:v1` — **an amino acid** · FR : Acide aminé · AR : الحمض الأميني
- `gen:essential-amino-acid:v1` — **an essential amino acid** · FR : Acide aminé essentiel · AR : حمض أميني أساسي
- `gen:non-essential-amino-acid:v1` — **a non-essential amino acid** · FR : Acide aminé non essentiel · AR : حمض أميني غير أساسي

### Questions (acceptées / modèle / hint)
- **q1.** Is the amino acid normal?
  - accepté : yes ; yes it is ; the amino acid is normal ; it is normal
  - modèle : Yes, the amino acid is normal.
- **q2.** Is the non-essential amino acid normal?
  - accepté : no ; no it is not ; no it isn't ; the non-essential amino acid is not normal ; it is not normal ; it isn't normal
  - modèle : No, the non-essential amino acid is not normal.
  - hint (si erreur) — FR : Attention : Acide aminé non essentiel n'est PAS normal(e).
- **q3.** Which one is not normal: the amino acid or the non-essential amino acid?
  - accepté : non-essential amino acid ; the non-essential amino acid
  - modèle : The non-essential amino acid is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the essential amino acid normal?
  - accepté : yes ; yes it is ; the essential amino acid is normal
  - modèle : Yes, the essential amino acid is normal.
- **Tt2.** Is the amino acid normal?
  - accepté : no ; no it is not ; no it isn't ; the amino acid is not normal ; it is not normal
  - modèle : No, the amino acid is not normal.
  - hint (si erreur) — FR : Attention : Acide aminé n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The amino acid … normal. (be)` → is · modèle : The amino acid is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The amino acid is… / The non-essential amino acid is not… — modèle : *The amino acid is normal. The non-essential amino acid is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Acide aminé essentiel normal(e), mais Acide aminé anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The essential amino acid is normal. The amino acid is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Acide aminé et Acide aminé essentiel à un patient.
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
4. **EN** “First, we check amino acid.”
   - FR : « D'abord, nous vérifions Acide aminé. »
   - AR : «أولًا، نتحقق من الحمض الأميني.»
5. **EN** “Then, we check essential amino acid.”
   - FR : « Ensuite, nous vérifions Acide aminé essentiel. »
   - AR : «ثم نتحقق من حمض أميني أساسي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:amino-acid:v1` — **an amino acid** · FR : Acide aminé · AR : الحمض الأميني
- `gen:essential-amino-acid:v1` — **an essential amino acid** · FR : Acide aminé essentiel · AR : حمض أميني أساسي
- `gen:non-essential-amino-acid:v1` — **a non-essential amino acid** · FR : Acide aminé non essentiel · AR : حمض أميني غير أساسي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : amino acid ; we check amino acid ; amino acid first ; the doctor checks amino acid first
  - modèle : First, the doctor checks amino acid.
- **q2.** What does the doctor check next?
  - accepté : essential amino acid ; then we check essential amino acid ; essential amino acid next ; the doctor checks essential amino acid next
  - modèle : Then, the doctor checks essential amino acid.
- **q3.** Does the doctor check essential amino acid before amino acid?
  - accepté : no ; no he does not ; no he doesn't ; he checks amino acid first
  - modèle : No, he checks amino acid first, then essential amino acid.
  - hint (si erreur) — FR : Attention : Acide aminé vient D'ABORD, ensuite Acide aminé essentiel.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : non-essential amino acid ; non-essential amino acid first ; he checks non-essential amino acid first
  - modèle : First, the doctor checks non-essential amino acid.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check amino acid. Then, we check essential amino acid.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Acide aminé non essentiel, ensuite Acide aminé, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check non-essential amino acid. Then, we check amino acid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Acide aminé / Acide aminé essentiel, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the amino acid.
   - FR : Le patient du lit 3 a Acide aminé.
   - AR : مريض السرير ٣ عنده الحمض الأميني.
4. **EN** The patient in bed 5 has a problem with the essential amino acid.
   - FR : Le patient du lit 5 a Acide aminé essentiel.
   - AR : مريض السرير ٥ عنده حمض أميني أساسي.
5. **EN** The patient in bed 5 does not have a problem with the amino acid.
   - FR : Le patient du lit 5 n'a pas de Acide aminé.
   - AR : مريض السرير ٥ ليس عنده الحمض الأميني.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:amino-acid:v1` — **an amino acid** · FR : Acide aminé · AR : الحمض الأميني
- `gen:essential-amino-acid:v1` — **an essential amino acid** · FR : Acide aminé essentiel · AR : حمض أميني أساسي
- `gen:non-essential-amino-acid:v1` — **a non-essential amino acid** · FR : Acide aminé non essentiel · AR : حمض أميني غير أساسي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : amino acid ; an amino acid ; the patient with amino acid
  - modèle : The patient in bed 3 has an amino acid.
- **q2.** What does the patient in bed 5 have?
  - accepté : essential amino acid ; a problem with the essential amino acid
  - modèle : He has a problem with the essential amino acid.
- **q3.** Does the patient in bed 5 have amino acid?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the amino acid ; he doesn't
  - modèle : No, he does not have a problem with the amino acid.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS acide aminé — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : non-essential amino acid ; a problem with the non-essential amino acid
  - modèle : He has a problem with the non-essential amino acid.
- **Tt2.** Does the patient in bed 9 have essential amino acid?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the essential amino acid ; he doesn't
  - modèle : No, he does not have a problem with the essential amino acid.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS acide aminé essentiel.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an amino acid and an essential amino acid. (have)` → have · modèle : The patients have an amino acid and an essential amino acid.
- `The patient in bed 5 … not have an amino acid. (do)` → does · modèle : The patient in bed 5 does not have an amino acid.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the amino acid. The patient in bed 5 has a problem with the essential amino acid.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Acide aminé non essentiel, le lit 9 a Acide aminé et n'a pas Acide aminé essentiel.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the non-essential amino acid. The patient in bed 9 has a problem with the amino acid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
