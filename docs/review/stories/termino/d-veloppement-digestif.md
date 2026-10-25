# Relecture — Développement Digestif (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-004` — Le cas de Farid : Intestin moyen anormal(e) / _Farid: the midgut is not normal_

**Objectif :** Comprendre des résultats : Tube intestinal primitif normal(e), Intestin moyen anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient at the clinic.
   - FR : Farid est un·e patient·e à la clinique.
   - AR : Farid مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The gut tube is normal.”
   - FR : Le médecin dit : « Tube intestinal primitif est normal(e). »
   - AR : يقول الطبيب: «الأنبوب الهضمي البدائي طبيعي/طبيعية.»
5. **EN** “But the midgut is not normal.”
   - FR : « Mais Intestin moyen n'est pas normal(e). »
   - AR : «لكن الأمعاء الوسطى غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the midgut normal?”
   - FR : Lina demande : « Intestin moyen est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الأمعاء الوسطى طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:gut-tube:v1` — **a gut tube** · FR : Tube intestinal primitif · AR : الأنبوب الهضمي البدائي
- `gen:foregut:v1` — **a foregut** · FR : Intestin antérieur · AR : الأمعاء الأمامية
- `gen:midgut:v1` — **a midgut** · FR : Intestin moyen · AR : الأمعاء الوسطى

### Questions (acceptées / modèle / hint)
- **q1.** Is the gut tube normal?
  - accepté : yes ; yes it is ; the gut tube is normal ; it is normal
  - modèle : Yes, the gut tube is normal.
- **q2.** Is the midgut normal?
  - accepté : no ; no it is not ; no it isn't ; the midgut is not normal ; it is not normal ; it isn't normal
  - modèle : No, the midgut is not normal.
  - hint (si erreur) — FR : Attention : Intestin moyen n'est PAS normal(e).
- **q3.** Which one is not normal: the gut tube or the midgut?
  - accepté : midgut ; the midgut
  - modèle : The midgut is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the foregut normal?
  - accepté : yes ; yes it is ; the foregut is normal
  - modèle : Yes, the foregut is normal.
- **Tt2.** Is the gut tube normal?
  - accepté : no ; no it is not ; no it isn't ; the gut tube is not normal ; it is not normal
  - modèle : No, the gut tube is not normal.
  - hint (si erreur) — FR : Attention : Tube intestinal primitif n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The gut tube … normal. (be)` → is · modèle : The gut tube is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Farid en deux phrases. Garde la négation. — amorces : The gut tube is… / The midgut is not… — modèle : *The gut tube is normal. The midgut is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Intestin antérieur normal(e), mais Tube intestinal primitif anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The foregut is normal. The gut tube is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-004` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Tube intestinal primitif et Intestin antérieur à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient.
   - FR : Sofiane est un·e patient·e.
   - AR : Sofiane مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check gut tube.”
   - FR : « D'abord, nous vérifions Tube intestinal primitif. »
   - AR : «أولًا، نتحقق من الأنبوب الهضمي البدائي.»
5. **EN** “Then, we check foregut.”
   - FR : « Ensuite, nous vérifions Intestin antérieur. »
   - AR : «ثم نتحقق من الأمعاء الأمامية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:gut-tube:v1` — **a gut tube** · FR : Tube intestinal primitif · AR : الأنبوب الهضمي البدائي
- `gen:foregut:v1` — **a foregut** · FR : Intestin antérieur · AR : الأمعاء الأمامية
- `gen:midgut:v1` — **a midgut** · FR : Intestin moyen · AR : الأمعاء الوسطى

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : gut tube ; we check gut tube ; gut tube first ; the doctor checks gut tube first
  - modèle : First, the doctor checks gut tube.
- **q2.** What does the doctor check next?
  - accepté : foregut ; then we check foregut ; foregut next ; the doctor checks foregut next
  - modèle : Then, the doctor checks foregut.
- **q3.** Does the doctor check foregut before gut tube?
  - accepté : no ; no he does not ; no he doesn't ; he checks gut tube first
  - modèle : No, he checks gut tube first, then foregut.
  - hint (si erreur) — FR : Attention : Tube intestinal primitif vient D'ABORD, ensuite Intestin antérieur.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : midgut ; midgut first ; he checks midgut first
  - modèle : First, the doctor checks midgut.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check gut tube. Then, we check foregut.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Intestin moyen, ensuite Tube intestinal primitif, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check midgut. Then, we check gut tube.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-004` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Tube intestinal primitif / Intestin antérieur, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the gut tube.
   - FR : Le patient du lit 3 a Tube intestinal primitif.
   - AR : مريض السرير ٣ عنده الأنبوب الهضمي البدائي.
4. **EN** The patient in bed 5 has a problem with the foregut.
   - FR : Le patient du lit 5 a Intestin antérieur.
   - AR : مريض السرير ٥ عنده الأمعاء الأمامية.
5. **EN** The patient in bed 5 does not have a problem with the gut tube.
   - FR : Le patient du lit 5 n'a pas de Tube intestinal primitif.
   - AR : مريض السرير ٥ ليس عنده الأنبوب الهضمي البدائي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:gut-tube:v1` — **a gut tube** · FR : Tube intestinal primitif · AR : الأنبوب الهضمي البدائي
- `gen:foregut:v1` — **a foregut** · FR : Intestin antérieur · AR : الأمعاء الأمامية
- `gen:midgut:v1` — **a midgut** · FR : Intestin moyen · AR : الأمعاء الوسطى

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : gut tube ; a gut tube ; the patient with gut tube
  - modèle : The patient in bed 3 has a gut tube.
- **q2.** What does the patient in bed 5 have?
  - accepté : foregut ; a problem with the foregut
  - modèle : He has a problem with the foregut.
- **q3.** Does the patient in bed 5 have gut tube?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the gut tube ; he doesn't
  - modèle : No, he does not have a problem with the gut tube.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS tube intestinal primitif — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : midgut ; a problem with the midgut
  - modèle : He has a problem with the midgut.
- **Tt2.** Does the patient in bed 9 have foregut?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the foregut ; he doesn't
  - modèle : No, he does not have a problem with the foregut.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS intestin antérieur.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a gut tube and a foregut. (have)` → have · modèle : The patients have a gut tube and a foregut.
- `The patient in bed 5 … not have a gut tube. (do)` → does · modèle : The patient in bed 5 does not have a gut tube.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the gut tube. The patient in bed 5 has a problem with the foregut.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Intestin moyen, le lit 9 a Tube intestinal primitif et n'a pas Intestin antérieur.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the midgut. The patient in bed 9 has a problem with the gut tube.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
