# Relecture — Spectre Électromagnétique (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-010` — Le cas de Omar : Ondes radio anormal(e) / _Omar: the radio waves is not normal_

**Objectif :** Comprendre des résultats : Onde électromagnétique normal(e), Ondes radio anormal(e).
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
4. **EN** The doctor says, “The electromagnetic wave is normal.”
   - FR : Le médecin dit : « Onde électromagnétique est normal(e). »
   - AR : يقول الطبيب: «الموجة الكهرومغناطيسية طبيعي/طبيعية.»
5. **EN** “But the radio waves is not normal.”
   - FR : « Mais Ondes radio n'est pas normal(e). »
   - AR : «لكن موجات الراديو غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the radio waves normal?”
   - FR : Lina demande : « Ondes radio est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل موجات الراديو طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:electromagnetic-wave:v1` — **an electromagnetic wave** · FR : Onde électromagnétique · AR : الموجة الكهرومغناطيسية
- `gen:electromagnetic-spectrum:v1` — **an electromagnetic spectrum** · FR : Spectre électromagnétique · AR : الطيف الكهرومغناطيسي
- `gen:radio-waves:v1` — **radio waves** · FR : Ondes radio · AR : موجات الراديو

### Questions (acceptées / modèle / hint)
- **q1.** Is the electromagnetic wave normal?
  - accepté : yes ; yes it is ; the electromagnetic wave is normal ; it is normal
  - modèle : Yes, the electromagnetic wave is normal.
- **q2.** Is the radio waves normal?
  - accepté : no ; no it is not ; no it isn't ; the radio waves is not normal ; it is not normal ; it isn't normal
  - modèle : No, the radio waves is not normal.
  - hint (si erreur) — FR : Attention : Ondes radio n'est PAS normal(e).
- **q3.** Which one is not normal: the electromagnetic wave or the radio waves?
  - accepté : radio waves ; the radio waves
  - modèle : The radio waves is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the electromagnetic spectrum normal?
  - accepté : yes ; yes it is ; the electromagnetic spectrum is normal
  - modèle : Yes, the electromagnetic spectrum is normal.
- **Tt2.** Is the electromagnetic wave normal?
  - accepté : no ; no it is not ; no it isn't ; the electromagnetic wave is not normal ; it is not normal
  - modèle : No, the electromagnetic wave is not normal.
  - hint (si erreur) — FR : Attention : Onde électromagnétique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The electromagnetic wave … normal. (be)` → is · modèle : The electromagnetic wave is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The electromagnetic wave is… / The radio waves is not… — modèle : *The electromagnetic wave is normal. The radio waves is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Spectre électromagnétique normal(e), mais Onde électromagnétique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The electromagnetic spectrum is normal. The electromagnetic wave is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-010` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Onde électromagnétique et Spectre électromagnétique à un patient.
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
4. **EN** “First, we check electromagnetic wave.”
   - FR : « D'abord, nous vérifions Onde électromagnétique. »
   - AR : «أولًا، نتحقق من الموجة الكهرومغناطيسية.»
5. **EN** “Then, we check electromagnetic spectrum.”
   - FR : « Ensuite, nous vérifions Spectre électromagnétique. »
   - AR : «ثم نتحقق من الطيف الكهرومغناطيسي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:electromagnetic-wave:v1` — **an electromagnetic wave** · FR : Onde électromagnétique · AR : الموجة الكهرومغناطيسية
- `gen:electromagnetic-spectrum:v1` — **an electromagnetic spectrum** · FR : Spectre électromagnétique · AR : الطيف الكهرومغناطيسي
- `gen:radio-waves:v1` — **radio waves** · FR : Ondes radio · AR : موجات الراديو

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : electromagnetic wave ; we check electromagnetic wave ; electromagnetic wave first ; the doctor checks electromagnetic wave first
  - modèle : First, the doctor checks electromagnetic wave.
- **q2.** What does the doctor check next?
  - accepté : electromagnetic spectrum ; then we check electromagnetic spectrum ; electromagnetic spectrum next ; the doctor checks electromagnetic spectrum next
  - modèle : Then, the doctor checks electromagnetic spectrum.
- **q3.** Does the doctor check electromagnetic spectrum before electromagnetic wave?
  - accepté : no ; no he does not ; no he doesn't ; he checks electromagnetic wave first
  - modèle : No, he checks electromagnetic wave first, then electromagnetic spectrum.
  - hint (si erreur) — FR : Attention : Onde électromagnétique vient D'ABORD, ensuite Spectre électromagnétique.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : radio waves ; radio waves first ; he checks radio waves first
  - modèle : First, the doctor checks radio waves.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check electromagnetic wave. Then, we check electromagnetic spectrum.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Ondes radio, ensuite Onde électromagnétique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check radio waves. Then, we check electromagnetic wave.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-010` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Onde électromagnétique / Spectre électromagnétique, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the electromagnetic wave.
   - FR : Le patient du lit 3 a Onde électromagnétique.
   - AR : مريض السرير ٣ عنده الموجة الكهرومغناطيسية.
4. **EN** The patient in bed 5 has a problem with the electromagnetic spectrum.
   - FR : Le patient du lit 5 a Spectre électromagnétique.
   - AR : مريض السرير ٥ عنده الطيف الكهرومغناطيسي.
5. **EN** The patient in bed 5 does not have a problem with the electromagnetic wave.
   - FR : Le patient du lit 5 n'a pas de Onde électromagnétique.
   - AR : مريض السرير ٥ ليس عنده الموجة الكهرومغناطيسية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:electromagnetic-wave:v1` — **an electromagnetic wave** · FR : Onde électromagnétique · AR : الموجة الكهرومغناطيسية
- `gen:electromagnetic-spectrum:v1` — **an electromagnetic spectrum** · FR : Spectre électromagnétique · AR : الطيف الكهرومغناطيسي
- `gen:radio-waves:v1` — **radio waves** · FR : Ondes radio · AR : موجات الراديو

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : electromagnetic wave ; an electromagnetic wave ; the patient with electromagnetic wave
  - modèle : The patient in bed 3 has an electromagnetic wave.
- **q2.** What does the patient in bed 5 have?
  - accepté : electromagnetic spectrum ; a problem with the electromagnetic spectrum
  - modèle : He has a problem with the electromagnetic spectrum.
- **q3.** Does the patient in bed 5 have electromagnetic wave?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the electromagnetic wave ; he doesn't
  - modèle : No, he does not have a problem with the electromagnetic wave.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS onde électromagnétique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : radio waves ; a problem with the radio waves
  - modèle : He has a problem with the radio waves.
- **Tt2.** Does the patient in bed 9 have electromagnetic spectrum?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the electromagnetic spectrum ; he doesn't
  - modèle : No, he does not have a problem with the electromagnetic spectrum.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS spectre électromagnétique.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an electromagnetic wave and an electromagnetic spectrum. (have)` → have · modèle : The patients have an electromagnetic wave and an electromagnetic spectrum.
- `The patient in bed 5 … not have an electromagnetic wave. (do)` → does · modèle : The patient in bed 5 does not have an electromagnetic wave.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the electromagnetic wave. The patient in bed 5 has a problem with the electromagnetic spectrum.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Ondes radio, le lit 9 a Onde électromagnétique et n'a pas Spectre électromagnétique.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the radio waves. The patient in bed 9 has a problem with the electromagnetic wave.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
