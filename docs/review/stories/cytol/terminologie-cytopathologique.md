# Relecture — Terminologie Cytopathologique (cytol)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `cytol-case-010` — Le cas de Omar : Atypie nucléaire anormal(e) / _Omar: the nuclear atypia is not normal_

**Objectif :** Comprendre des résultats : Hyperchromatisme normal(e), Atypie nucléaire anormal(e).
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
4. **EN** The doctor says, “The hyperchromatism is normal.”
   - FR : Le médecin dit : « Hyperchromatisme est normal(e). »
   - AR : يقول الطبيب: «فرط التصبغ النووي طبيعي/طبيعية.»
5. **EN** “But the nuclear atypia is not normal.”
   - FR : « Mais Atypie nucléaire n'est pas normal(e). »
   - AR : «لكن اللانمطية النووية غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the nuclear atypia normal?”
   - FR : Lina demande : « Atypie nucléaire est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل اللانمطية النووية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:hyperchromatism:v1` — **a hyperchromatism** · FR : Hyperchromatisme · AR : فرط التصبغ النووي
- `gen:pleomorphism:v1` — **a pleomorphism** · FR : Pléomorphisme · AR : تعدد الأشكال الخلوية
- `gen:nuclear-atypia:v1` — **a nuclear atypia** · FR : Atypie nucléaire · AR : اللانمطية النووية

### Questions (acceptées / modèle / hint)
- **q1.** Is the hyperchromatism normal?
  - accepté : yes ; yes it is ; the hyperchromatism is normal ; it is normal
  - modèle : Yes, the hyperchromatism is normal.
- **q2.** Is the nuclear atypia normal?
  - accepté : no ; no it is not ; no it isn't ; the nuclear atypia is not normal ; it is not normal ; it isn't normal
  - modèle : No, the nuclear atypia is not normal.
  - hint (si erreur) — FR : Attention : Atypie nucléaire n'est PAS normal(e).
- **q3.** Which one is not normal: the hyperchromatism or the nuclear atypia?
  - accepté : nuclear atypia ; the nuclear atypia
  - modèle : The nuclear atypia is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the pleomorphism normal?
  - accepté : yes ; yes it is ; the pleomorphism is normal
  - modèle : Yes, the pleomorphism is normal.
- **Tt2.** Is the hyperchromatism normal?
  - accepté : no ; no it is not ; no it isn't ; the hyperchromatism is not normal ; it is not normal
  - modèle : No, the hyperchromatism is not normal.
  - hint (si erreur) — FR : Attention : Hyperchromatisme n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The hyperchromatism … normal. (be)` → is · modèle : The hyperchromatism is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The hyperchromatism is… / The nuclear atypia is not… — modèle : *The hyperchromatism is normal. The nuclear atypia is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Pléomorphisme normal(e), mais Hyperchromatisme anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The pleomorphism is normal. The hyperchromatism is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `cytol-explain-010` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Hyperchromatisme et Pléomorphisme à un patient.
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
4. **EN** “First, we check hyperchromatism.”
   - FR : « D'abord, nous vérifions Hyperchromatisme. »
   - AR : «أولًا، نتحقق من فرط التصبغ النووي.»
5. **EN** “Then, we check pleomorphism.”
   - FR : « Ensuite, nous vérifions Pléomorphisme. »
   - AR : «ثم نتحقق من تعدد الأشكال الخلوية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:hyperchromatism:v1` — **a hyperchromatism** · FR : Hyperchromatisme · AR : فرط التصبغ النووي
- `gen:pleomorphism:v1` — **a pleomorphism** · FR : Pléomorphisme · AR : تعدد الأشكال الخلوية
- `gen:nuclear-atypia:v1` — **a nuclear atypia** · FR : Atypie nucléaire · AR : اللانمطية النووية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : hyperchromatism ; we check hyperchromatism ; hyperchromatism first ; the doctor checks hyperchromatism first
  - modèle : First, the doctor checks hyperchromatism.
- **q2.** What does the doctor check next?
  - accepté : pleomorphism ; then we check pleomorphism ; pleomorphism next ; the doctor checks pleomorphism next
  - modèle : Then, the doctor checks pleomorphism.
- **q3.** Does the doctor check pleomorphism before hyperchromatism?
  - accepté : no ; no he does not ; no he doesn't ; he checks hyperchromatism first
  - modèle : No, he checks hyperchromatism first, then pleomorphism.
  - hint (si erreur) — FR : Attention : Hyperchromatisme vient D'ABORD, ensuite Pléomorphisme.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : nuclear atypia ; nuclear atypia first ; he checks nuclear atypia first
  - modèle : First, the doctor checks nuclear atypia.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check hyperchromatism. Then, we check pleomorphism.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Atypie nucléaire, ensuite Hyperchromatisme, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check nuclear atypia. Then, we check hyperchromatism.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `cytol-handover-010` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Hyperchromatisme / Pléomorphisme, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the hyperchromatism.
   - FR : Le patient du lit 3 a Hyperchromatisme.
   - AR : مريض السرير ٣ عنده فرط التصبغ النووي.
4. **EN** The patient in bed 5 has a problem with the pleomorphism.
   - FR : Le patient du lit 5 a Pléomorphisme.
   - AR : مريض السرير ٥ عنده تعدد الأشكال الخلوية.
5. **EN** The patient in bed 5 does not have a problem with the hyperchromatism.
   - FR : Le patient du lit 5 n'a pas de Hyperchromatisme.
   - AR : مريض السرير ٥ ليس عنده فرط التصبغ النووي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:hyperchromatism:v1` — **a hyperchromatism** · FR : Hyperchromatisme · AR : فرط التصبغ النووي
- `gen:pleomorphism:v1` — **a pleomorphism** · FR : Pléomorphisme · AR : تعدد الأشكال الخلوية
- `gen:nuclear-atypia:v1` — **a nuclear atypia** · FR : Atypie nucléaire · AR : اللانمطية النووية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hyperchromatism ; a hyperchromatism ; the patient with hyperchromatism
  - modèle : The patient in bed 3 has a hyperchromatism.
- **q2.** What does the patient in bed 5 have?
  - accepté : pleomorphism ; a problem with the pleomorphism
  - modèle : He has a problem with the pleomorphism.
- **q3.** Does the patient in bed 5 have hyperchromatism?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the hyperchromatism ; he doesn't
  - modèle : No, he does not have a problem with the hyperchromatism.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS hyperchromatisme — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : nuclear atypia ; a problem with the nuclear atypia
  - modèle : He has a problem with the nuclear atypia.
- **Tt2.** Does the patient in bed 9 have pleomorphism?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the pleomorphism ; he doesn't
  - modèle : No, he does not have a problem with the pleomorphism.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS pléomorphisme.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a hyperchromatism and a pleomorphism. (have)` → have · modèle : The patients have a hyperchromatism and a pleomorphism.
- `The patient in bed 5 … not have a hyperchromatism. (do)` → does · modèle : The patient in bed 5 does not have a hyperchromatism.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the hyperchromatism. The patient in bed 5 has a problem with the pleomorphism.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Atypie nucléaire, le lit 9 a Hyperchromatisme et n'a pas Pléomorphisme.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the nuclear atypia. The patient in bed 9 has a problem with the hyperchromatism.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
