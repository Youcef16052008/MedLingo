# Relecture — Biologie Cellulaire (histo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `histo-case-000` — Le cas de Karim : Nucléole anormal(e) / _Karim: the nucleolus is not normal_

**Objectif :** Comprendre des résultats : Organite normal(e), Nucléole anormal(e).
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
4. **EN** The doctor says, “The organelle is normal.”
   - FR : Le médecin dit : « Organite est normal(e). »
   - AR : يقول الطبيب: «العضية طبيعي/طبيعية.»
5. **EN** “But the nucleolus is not normal.”
   - FR : « Mais Nucléole n'est pas normal(e). »
   - AR : «لكن النوية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the nucleolus normal?”
   - FR : Lina demande : « Nucléole est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل النوية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:organelle:v1` — **an organelle** · FR : Organite · AR : العضية
- `gen:nucleus:v1` — **nucleus** · FR : Noyau · AR : النواة
- `gen:nucleolus:v1` — **nucleolus** · FR : Nucléole · AR : النوية

### Questions (acceptées / modèle / hint)
- **q1.** Is the organelle normal?
  - accepté : yes ; yes it is ; the organelle is normal ; it is normal
  - modèle : Yes, the organelle is normal.
- **q2.** Is the nucleolus normal?
  - accepté : no ; no it is not ; no it isn't ; the nucleolus is not normal ; it is not normal ; it isn't normal
  - modèle : No, the nucleolus is not normal.
  - hint (si erreur) — FR : Attention : Nucléole n'est PAS normal(e).
- **q3.** Which one is not normal: the organelle or the nucleolus?
  - accepté : nucleolus ; the nucleolus
  - modèle : The nucleolus is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the nucleus normal?
  - accepté : yes ; yes it is ; the nucleus is normal
  - modèle : Yes, the nucleus is normal.
- **Tt2.** Is the organelle normal?
  - accepté : no ; no it is not ; no it isn't ; the organelle is not normal ; it is not normal
  - modèle : No, the organelle is not normal.
  - hint (si erreur) — FR : Attention : Organite n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The organelle … normal. (be)` → is · modèle : The organelle is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The organelle is… / The nucleolus is not… — modèle : *The organelle is normal. The nucleolus is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Noyau normal(e), mais Organite anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The nucleus is normal. The organelle is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `histo-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Organite et Noyau à un patient.
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
4. **EN** “First, we check organelle.”
   - FR : « D'abord, nous vérifions Organite. »
   - AR : «أولًا، نتحقق من العضية.»
5. **EN** “Then, we check nucleus.”
   - FR : « Ensuite, nous vérifions Noyau. »
   - AR : «ثم نتحقق من النواة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:organelle:v1` — **an organelle** · FR : Organite · AR : العضية
- `gen:nucleus:v1` — **nucleus** · FR : Noyau · AR : النواة
- `gen:nucleolus:v1` — **nucleolus** · FR : Nucléole · AR : النوية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : organelle ; we check organelle ; organelle first ; the doctor checks organelle first
  - modèle : First, the doctor checks organelle.
- **q2.** What does the doctor check next?
  - accepté : nucleus ; then we check nucleus ; nucleus next ; the doctor checks nucleus next
  - modèle : Then, the doctor checks nucleus.
- **q3.** Does the doctor check nucleus before organelle?
  - accepté : no ; no he does not ; no he doesn't ; he checks organelle first
  - modèle : No, he checks organelle first, then nucleus.
  - hint (si erreur) — FR : Attention : Organite vient D'ABORD, ensuite Noyau.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : nucleolus ; nucleolus first ; he checks nucleolus first
  - modèle : First, the doctor checks nucleolus.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check organelle. Then, we check nucleus.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Nucléole, ensuite Organite, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check nucleolus. Then, we check organelle.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `histo-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Organite / Noyau, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the organelle.
   - FR : Le patient du lit 3 a Organite.
   - AR : مريض السرير ٣ عنده العضية.
4. **EN** The patient in bed 5 has a problem with the nucleus.
   - FR : Le patient du lit 5 a Noyau.
   - AR : مريض السرير ٥ عنده النواة.
5. **EN** The patient in bed 5 does not have a problem with the organelle.
   - FR : Le patient du lit 5 n'a pas de Organite.
   - AR : مريض السرير ٥ ليس عنده العضية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:organelle:v1` — **an organelle** · FR : Organite · AR : العضية
- `gen:nucleus:v1` — **nucleus** · FR : Noyau · AR : النواة
- `gen:nucleolus:v1` — **nucleolus** · FR : Nucléole · AR : النوية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : organelle ; an organelle ; the patient with organelle
  - modèle : The patient in bed 3 has an organelle.
- **q2.** What does the patient in bed 5 have?
  - accepté : nucleus ; a problem with the nucleus
  - modèle : He has a problem with the nucleus.
- **q3.** Does the patient in bed 5 have organelle?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the organelle ; he doesn't
  - modèle : No, he does not have a problem with the organelle.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS organite — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : nucleolus ; a problem with the nucleolus
  - modèle : He has a problem with the nucleolus.
- **Tt2.** Does the patient in bed 9 have nucleus?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the nucleus ; he doesn't
  - modèle : No, he does not have a problem with the nucleus.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS noyau.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an organelle and nucleus. (have)` → have · modèle : The patients have an organelle and nucleus.
- `The patient in bed 5 … not have an organelle. (do)` → does · modèle : The patient in bed 5 does not have an organelle.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the organelle. The patient in bed 5 has a problem with the nucleus.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Nucléole, le lit 9 a Organite et n'a pas Noyau.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the nucleolus. The patient in bed 9 has a problem with the organelle.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
