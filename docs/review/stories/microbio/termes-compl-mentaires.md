# Relecture — Termes Complémentaires (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-012` — Le cas de Karim : Toxine anormal(e) / _Karim: the toxin is not normal_

**Objectif :** Comprendre des résultats : Exotoxine normal(e), Toxine anormal(e).
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
4. **EN** The doctor says, “The exotoxin is normal.”
   - FR : Le médecin dit : « Exotoxine est normal(e). »
   - AR : يقول الطبيب: «الذيفان الخارجي طبيعي/طبيعية.»
5. **EN** “But the toxin is not normal.”
   - FR : « Mais Toxine n'est pas normal(e). »
   - AR : «لكن السُّم (الذيفان) غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the toxin normal?”
   - FR : Lina demande : « Toxine est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل السُّم (الذيفان) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:exotoxin:v1` — **an exotoxin** · FR : Exotoxine · AR : الذيفان الخارجي
- `gen:endotoxin:v1` — **an endotoxin** · FR : Endotoxine · AR : الذيفان الداخلي
- `gen:toxin:v1` — **a toxin** · FR : Toxine · AR : السُّم (الذيفان)

### Questions (acceptées / modèle / hint)
- **q1.** Is the exotoxin normal?
  - accepté : yes ; yes it is ; the exotoxin is normal ; it is normal
  - modèle : Yes, the exotoxin is normal.
- **q2.** Is the toxin normal?
  - accepté : no ; no it is not ; no it isn't ; the toxin is not normal ; it is not normal ; it isn't normal
  - modèle : No, the toxin is not normal.
  - hint (si erreur) — FR : Attention : Toxine n'est PAS normal(e).
- **q3.** Which one is not normal: the exotoxin or the toxin?
  - accepté : toxin ; the toxin
  - modèle : The toxin is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the endotoxin normal?
  - accepté : yes ; yes it is ; the endotoxin is normal
  - modèle : Yes, the endotoxin is normal.
- **Tt2.** Is the exotoxin normal?
  - accepté : no ; no it is not ; no it isn't ; the exotoxin is not normal ; it is not normal
  - modèle : No, the exotoxin is not normal.
  - hint (si erreur) — FR : Attention : Exotoxine n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The exotoxin … normal. (be)` → is · modèle : The exotoxin is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The exotoxin is… / The toxin is not… — modèle : *The exotoxin is normal. The toxin is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Endotoxine normal(e), mais Exotoxine anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The endotoxin is normal. The exotoxin is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-012` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Exotoxine et Endotoxine à un patient.
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
4. **EN** “First, we check exotoxin.”
   - FR : « D'abord, nous vérifions Exotoxine. »
   - AR : «أولًا، نتحقق من الذيفان الخارجي.»
5. **EN** “Then, we check endotoxin.”
   - FR : « Ensuite, nous vérifions Endotoxine. »
   - AR : «ثم نتحقق من الذيفان الداخلي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:exotoxin:v1` — **an exotoxin** · FR : Exotoxine · AR : الذيفان الخارجي
- `gen:endotoxin:v1` — **an endotoxin** · FR : Endotoxine · AR : الذيفان الداخلي
- `gen:toxin:v1` — **a toxin** · FR : Toxine · AR : السُّم (الذيفان)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : exotoxin ; we check exotoxin ; exotoxin first ; the doctor checks exotoxin first
  - modèle : First, the doctor checks exotoxin.
- **q2.** What does the doctor check next?
  - accepté : endotoxin ; then we check endotoxin ; endotoxin next ; the doctor checks endotoxin next
  - modèle : Then, the doctor checks endotoxin.
- **q3.** Does the doctor check endotoxin before exotoxin?
  - accepté : no ; no he does not ; no he doesn't ; he checks exotoxin first
  - modèle : No, he checks exotoxin first, then endotoxin.
  - hint (si erreur) — FR : Attention : Exotoxine vient D'ABORD, ensuite Endotoxine.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : toxin ; toxin first ; he checks toxin first
  - modèle : First, the doctor checks toxin.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check exotoxin. Then, we check endotoxin.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Toxine, ensuite Exotoxine, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check toxin. Then, we check exotoxin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-012` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Exotoxine / Endotoxine, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the exotoxin.
   - FR : Le patient du lit 3 a Exotoxine.
   - AR : مريض السرير ٣ عنده الذيفان الخارجي.
4. **EN** The patient in bed 5 has a problem with the endotoxin.
   - FR : Le patient du lit 5 a Endotoxine.
   - AR : مريض السرير ٥ عنده الذيفان الداخلي.
5. **EN** The patient in bed 5 does not have a problem with the exotoxin.
   - FR : Le patient du lit 5 n'a pas de Exotoxine.
   - AR : مريض السرير ٥ ليس عنده الذيفان الخارجي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:exotoxin:v1` — **an exotoxin** · FR : Exotoxine · AR : الذيفان الخارجي
- `gen:endotoxin:v1` — **an endotoxin** · FR : Endotoxine · AR : الذيفان الداخلي
- `gen:toxin:v1` — **a toxin** · FR : Toxine · AR : السُّم (الذيفان)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : exotoxin ; an exotoxin ; the patient with exotoxin
  - modèle : The patient in bed 3 has an exotoxin.
- **q2.** What does the patient in bed 5 have?
  - accepté : endotoxin ; a problem with the endotoxin
  - modèle : He has a problem with the endotoxin.
- **q3.** Does the patient in bed 5 have exotoxin?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the exotoxin ; he doesn't
  - modèle : No, he does not have a problem with the exotoxin.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS exotoxine — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : toxin ; a problem with the toxin
  - modèle : He has a problem with the toxin.
- **Tt2.** Does the patient in bed 9 have endotoxin?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the endotoxin ; he doesn't
  - modèle : No, he does not have a problem with the endotoxin.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS endotoxine.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an exotoxin and an endotoxin. (have)` → have · modèle : The patients have an exotoxin and an endotoxin.
- `The patient in bed 5 … not have an exotoxin. (do)` → does · modèle : The patient in bed 5 does not have an exotoxin.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the exotoxin. The patient in bed 5 has a problem with the endotoxin.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Toxine, le lit 9 a Exotoxine et n'a pas Endotoxine.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the toxin. The patient in bed 9 has a problem with the exotoxin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
