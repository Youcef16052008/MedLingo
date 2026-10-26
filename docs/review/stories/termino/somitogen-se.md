# Relecture — Somitogenèse (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-026` — Le cas de Youssef : Myotome anormal(e) / _Youssef: the myotome is not normal_

**Objectif :** Comprendre des résultats : Somite normal(e), Myotome anormal(e).
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
4. **EN** The doctor says, “The somite is normal.”
   - FR : Le médecin dit : « Somite est normal(e). »
   - AR : يقول الطبيب: «القطيعة طبيعي/طبيعية.»
5. **EN** “But the myotome is not normal.”
   - FR : « Mais Myotome n'est pas normal(e). »
   - AR : «لكن الأديم العضلي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the myotome normal?”
   - FR : Lina demande : « Myotome est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الأديم العضلي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:somite:v1` — **a somite** · FR : Somite · AR : القطيعة
- `gen:sclerotome:v1` — **a sclerotome** · FR : Sclérotome · AR : الأديم الصخري
- `gen:myotome:v1` — **a myotome** · FR : Myotome · AR : الأديم العضلي

### Questions (acceptées / modèle / hint)
- **q1.** Is the somite normal?
  - accepté : yes ; yes it is ; the somite is normal ; it is normal
  - modèle : Yes, the somite is normal.
- **q2.** Is the myotome normal?
  - accepté : no ; no it is not ; no it isn't ; the myotome is not normal ; it is not normal ; it isn't normal
  - modèle : No, the myotome is not normal.
  - hint (si erreur) — FR : Attention : Myotome n'est PAS normal(e).
- **q3.** Which one is not normal: the somite or the myotome?
  - accepté : myotome ; the myotome
  - modèle : The myotome is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the sclerotome normal?
  - accepté : yes ; yes it is ; the sclerotome is normal
  - modèle : Yes, the sclerotome is normal.
- **Tt2.** Is the somite normal?
  - accepté : no ; no it is not ; no it isn't ; the somite is not normal ; it is not normal
  - modèle : No, the somite is not normal.
  - hint (si erreur) — FR : Attention : Somite n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The somite … normal. (be)` → is · modèle : The somite is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The somite is… / The myotome is not… — modèle : *The somite is normal. The myotome is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Sclérotome normal(e), mais Somite anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The sclerotome is normal. The somite is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-026` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Somite et Sclérotome à un patient.
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
4. **EN** “First, we check somite.”
   - FR : « D'abord, nous vérifions Somite. »
   - AR : «أولًا، نتحقق من القطيعة.»
5. **EN** “Then, we check sclerotome.”
   - FR : « Ensuite, nous vérifions Sclérotome. »
   - AR : «ثم نتحقق من الأديم الصخري.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:somite:v1` — **a somite** · FR : Somite · AR : القطيعة
- `gen:sclerotome:v1` — **a sclerotome** · FR : Sclérotome · AR : الأديم الصخري
- `gen:myotome:v1` — **a myotome** · FR : Myotome · AR : الأديم العضلي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : somite ; we check somite ; somite first ; the doctor checks somite first
  - modèle : First, the doctor checks somite.
- **q2.** What does the doctor check next?
  - accepté : sclerotome ; then we check sclerotome ; sclerotome next ; the doctor checks sclerotome next
  - modèle : Then, the doctor checks sclerotome.
- **q3.** Does the doctor check sclerotome before somite?
  - accepté : no ; no he does not ; no he doesn't ; he checks somite first
  - modèle : No, he checks somite first, then sclerotome.
  - hint (si erreur) — FR : Attention : Somite vient D'ABORD, ensuite Sclérotome.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : myotome ; myotome first ; he checks myotome first
  - modèle : First, the doctor checks myotome.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check somite. Then, we check sclerotome.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Myotome, ensuite Somite, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check myotome. Then, we check somite.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-026` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Somite / Sclérotome, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the somite.
   - FR : Le patient du lit 3 a Somite.
   - AR : مريض السرير ٣ عنده القطيعة.
4. **EN** The patient in bed 5 has a problem with the sclerotome.
   - FR : Le patient du lit 5 a Sclérotome.
   - AR : مريض السرير ٥ عنده الأديم الصخري.
5. **EN** The patient in bed 5 does not have a problem with the somite.
   - FR : Le patient du lit 5 n'a pas de Somite.
   - AR : مريض السرير ٥ ليس عنده القطيعة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:somite:v1` — **a somite** · FR : Somite · AR : القطيعة
- `gen:sclerotome:v1` — **a sclerotome** · FR : Sclérotome · AR : الأديم الصخري
- `gen:myotome:v1` — **a myotome** · FR : Myotome · AR : الأديم العضلي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : somite ; a somite ; the patient with somite
  - modèle : The patient in bed 3 has a somite.
- **q2.** What does the patient in bed 5 have?
  - accepté : sclerotome ; a problem with the sclerotome
  - modèle : He has a problem with the sclerotome.
- **q3.** Does the patient in bed 5 have somite?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the somite ; he doesn't
  - modèle : No, he does not have a problem with the somite.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS somite — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : myotome ; a problem with the myotome
  - modèle : He has a problem with the myotome.
- **Tt2.** Does the patient in bed 9 have sclerotome?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the sclerotome ; he doesn't
  - modèle : No, he does not have a problem with the sclerotome.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS sclérotome.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a somite and a sclerotome. (have)` → have · modèle : The patients have a somite and a sclerotome.
- `The patient in bed 5 … not have a somite. (do)` → does · modèle : The patient in bed 5 does not have a somite.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the somite. The patient in bed 5 has a problem with the sclerotome.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Myotome, le lit 9 a Somite et n'a pas Sclérotome.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the myotome. The patient in bed 9 has a problem with the somite.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
