# Relecture — Mycologie (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-010` — Le cas de Omar : Levure anormal(e) / _Omar: the yeast is not normal_

**Objectif :** Comprendre des résultats : Mycologie normal(e), Levure anormal(e).
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
4. **EN** The doctor says, “The mycology is normal.”
   - FR : Le médecin dit : « Mycologie est normal(e). »
   - AR : يقول الطبيب: «علم الفطريات طبيعي/طبيعية.»
5. **EN** “But the yeast is not normal.”
   - FR : « Mais Levure n'est pas normal(e). »
   - AR : «لكن الخميرة غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the yeast normal?”
   - FR : Lina demande : « Levure est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الخميرة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:mycology:v1` — **a mycology** · FR : Mycologie · AR : علم الفطريات
- `gen:fungus:v1` — **fungus** · FR : Champignon (Fongique) · AR : الفطر
- `gen:yeast:v1` — **a yeast** · FR : Levure · AR : الخميرة

### Questions (acceptées / modèle / hint)
- **q1.** Is the mycology normal?
  - accepté : yes ; yes it is ; the mycology is normal ; it is normal
  - modèle : Yes, the mycology is normal.
- **q2.** Is the yeast normal?
  - accepté : no ; no it is not ; no it isn't ; the yeast is not normal ; it is not normal ; it isn't normal
  - modèle : No, the yeast is not normal.
  - hint (si erreur) — FR : Attention : Levure n'est PAS normal(e).
- **q3.** Which one is not normal: the mycology or the yeast?
  - accepté : yeast ; the yeast
  - modèle : The yeast is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the fungus normal?
  - accepté : yes ; yes it is ; the fungus is normal
  - modèle : Yes, the fungus is normal.
- **Tt2.** Is the mycology normal?
  - accepté : no ; no it is not ; no it isn't ; the mycology is not normal ; it is not normal
  - modèle : No, the mycology is not normal.
  - hint (si erreur) — FR : Attention : Mycologie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The mycology … normal. (be)` → is · modèle : The mycology is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The mycology is… / The yeast is not… — modèle : *The mycology is normal. The yeast is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Champignon (Fongique) normal(e), mais Mycologie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The fungus is normal. The mycology is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-010` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Mycologie et Champignon (Fongique) à un patient.
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
4. **EN** “First, we check mycology.”
   - FR : « D'abord, nous vérifions Mycologie. »
   - AR : «أولًا، نتحقق من علم الفطريات.»
5. **EN** “Then, we check fungus.”
   - FR : « Ensuite, nous vérifions Champignon (Fongique). »
   - AR : «ثم نتحقق من الفطر.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:mycology:v1` — **a mycology** · FR : Mycologie · AR : علم الفطريات
- `gen:fungus:v1` — **fungus** · FR : Champignon (Fongique) · AR : الفطر
- `gen:yeast:v1` — **a yeast** · FR : Levure · AR : الخميرة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : mycology ; we check mycology ; mycology first ; the doctor checks mycology first
  - modèle : First, the doctor checks mycology.
- **q2.** What does the doctor check next?
  - accepté : fungus ; then we check fungus ; fungus next ; the doctor checks fungus next
  - modèle : Then, the doctor checks fungus.
- **q3.** Does the doctor check fungus before mycology?
  - accepté : no ; no he does not ; no he doesn't ; he checks mycology first
  - modèle : No, he checks mycology first, then fungus.
  - hint (si erreur) — FR : Attention : Mycologie vient D'ABORD, ensuite Champignon (Fongique).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : yeast ; yeast first ; he checks yeast first
  - modèle : First, the doctor checks yeast.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check mycology. Then, we check fungus.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Levure, ensuite Mycologie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check yeast. Then, we check mycology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-010` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Mycologie / Champignon (Fongique), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the mycology.
   - FR : Le patient du lit 3 a Mycologie.
   - AR : مريض السرير ٣ عنده علم الفطريات.
4. **EN** The patient in bed 5 has a problem with the fungus.
   - FR : Le patient du lit 5 a Champignon (Fongique).
   - AR : مريض السرير ٥ عنده الفطر.
5. **EN** The patient in bed 5 does not have a problem with the mycology.
   - FR : Le patient du lit 5 n'a pas de Mycologie.
   - AR : مريض السرير ٥ ليس عنده علم الفطريات.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:mycology:v1` — **a mycology** · FR : Mycologie · AR : علم الفطريات
- `gen:fungus:v1` — **fungus** · FR : Champignon (Fongique) · AR : الفطر
- `gen:yeast:v1` — **a yeast** · FR : Levure · AR : الخميرة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : mycology ; a mycology ; the patient with mycology
  - modèle : The patient in bed 3 has a mycology.
- **q2.** What does the patient in bed 5 have?
  - accepté : fungus ; a problem with the fungus
  - modèle : He has a problem with the fungus.
- **q3.** Does the patient in bed 5 have mycology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the mycology ; he doesn't
  - modèle : No, he does not have a problem with the mycology.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS mycologie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : yeast ; a problem with the yeast
  - modèle : He has a problem with the yeast.
- **Tt2.** Does the patient in bed 9 have fungus?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the fungus ; he doesn't
  - modèle : No, he does not have a problem with the fungus.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS champignon (fongique).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a mycology and fungus. (have)` → have · modèle : The patients have a mycology and fungus.
- `The patient in bed 5 … not have a mycology. (do)` → does · modèle : The patient in bed 5 does not have a mycology.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the mycology. The patient in bed 5 has a problem with the fungus.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Levure, le lit 9 a Mycologie et n'a pas Champignon (Fongique).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the yeast. The patient in bed 9 has a problem with the mycology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
