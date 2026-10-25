# Relecture — Acoustique (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-000` — Le cas de Sara : Surdité de transmission / _Sara: Conductive hearing loss_

**Objectif :** Comprendre un cas : surdité de transmission présent, surdité de perception (neurosensorielle) absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Sara talks to Lina, a medical student.
   - FR : Sara parle avec Lina, étudiant·e en médecine.
   - AR : Sara يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has conductive hearing loss.
   - FR : Elle a Surdité de transmission.
   - AR : عنده فقدان السمع التوصيلي.
4. **EN** She does not have sensorineural hearing loss.
   - FR : Elle n'a pas de Surdité de perception (neurosensorielle).
   - AR : ليس عنده فقدان السمع الحسي العصبي.
5. **EN** Lina asks, “Do you have sensorineural hearing loss?”
   - FR : Lina demande : « Avez-vous Surdité de perception (neurosensorielle) ? »
   - AR : يسأل Lina: «هل عندك فقدان السمع الحسي العصبي؟»
6. **EN** Sara says, “No, I do not.”
   - FR : Sara répond : « Non. »
   - AR : يجيب Sara: «لا.»
7. **EN** Lina writes “Conductive hearing loss” in the file.
   - FR : Lina écrit « Surdité de transmission » dans le dossier.
   - AR : يكتب Lina «Surdité de transmission» في الملف.

### Expressions ciblées
- `gen:conductive-hearing-loss:v1` — **conductive hearing loss** · FR : Surdité de transmission · AR : فقدان السمع التوصيلي
- `gen:sensorineural-hearing-loss:v1` — **sensorineural hearing loss** · FR : Surdité de perception (neurosensorielle) · AR : فقدان السمع الحسي العصبي
- `gen:sound:v1` — **a sound** · FR : Son · AR : الصوت

### Questions (acceptées / modèle / hint)
- **q1.** Does Sara have conductive hearing loss?
  - accepté : yes ; yes she does ; yes she has conductive hearing loss ; she has conductive hearing loss
  - modèle : Yes, she has conductive hearing loss.
- **q2.** Does she have sensorineural hearing loss?
  - accepté : no ; no she does not ; no she doesn't ; she does not have sensorineural hearing loss ; she doesn't
  - modèle : No, she does not have sensorineural hearing loss.
  - hint (si erreur) — FR : Attention : Sara n'a PAS surdité de perception (neurosensorielle).
- **q3.** Does she have conductive hearing loss or sensorineural hearing loss?
  - accepté : conductive hearing loss ; she has conductive hearing loss
  - modèle : She has conductive hearing loss.
- **q4.** What does Sara have?
  - accepté : conductive hearing loss ; she has conductive hearing loss
  - modèle : She has conductive hearing loss.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have sound?
  - accepté : yes ; yes he does ; he has a sound
  - modèle : Yes, he has a sound.
- **Tt2.** Does he have conductive hearing loss?
  - accepté : no ; no he does not ; no he doesn't ; he does not have conductive hearing loss ; he doesn't
  - modèle : No, he does not have conductive hearing loss.
  - hint (si erreur) — FR : Attention : il n'a PAS surdité de transmission.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … conductive hearing loss. (have)` → has · modèle : She has conductive hearing loss.
- `They … not have sensorineural hearing loss. (do)` → do · modèle : They do not have sensorineural hearing loss.
- `I … conductive hearing loss. (have)` → have · modèle : I have conductive hearing loss.

### Productions
- **Reformulation :** Rapporte le cas de Sara en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has conductive hearing loss. She does not have sensorineural hearing loss.*
- **Transfert (contexte) :** Un autre patient a Son. Il n'a pas de Surdité de transmission.
- Glossaire : Son : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a sound. He does not have conductive hearing loss.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Son et Onde sonore à un patient.
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
4. **EN** “First, we check sound.”
   - FR : « D'abord, nous vérifions Son. »
   - AR : «أولًا، نتحقق من الصوت.»
5. **EN** “Then, we check sound wave.”
   - FR : « Ensuite, nous vérifions Onde sonore. »
   - AR : «ثم نتحقق من الموجة الصوتية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:sound:v1` — **a sound** · FR : Son · AR : الصوت
- `gen:sound-wave:v1` — **a sound wave** · FR : Onde sonore · AR : الموجة الصوتية
- `gen:pitch:v1` — **a pitch** · FR : Hauteur (tonalité) · AR : حدة الصوت

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : sound ; we check sound ; sound first ; the doctor checks sound first
  - modèle : First, the doctor checks sound.
- **q2.** What does the doctor check next?
  - accepté : sound wave ; then we check sound wave ; sound wave next ; the doctor checks sound wave next
  - modèle : Then, the doctor checks sound wave.
- **q3.** Does the doctor check sound wave before sound?
  - accepté : no ; no he does not ; no he doesn't ; he checks sound first
  - modèle : No, he checks sound first, then sound wave.
  - hint (si erreur) — FR : Attention : Son vient D'ABORD, ensuite Onde sonore.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : pitch ; pitch first ; he checks pitch first
  - modèle : First, the doctor checks pitch.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check sound. Then, we check sound wave.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Hauteur (tonalité), ensuite Son, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check pitch. Then, we check sound.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Surdité de transmission / Surdité de perception (neurosensorielle), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has conductive hearing loss.
   - FR : Le patient du lit 3 a Surdité de transmission.
   - AR : مريض السرير ٣ عنده فقدان السمع التوصيلي.
4. **EN** The patient in bed 5 has sensorineural hearing loss.
   - FR : Le patient du lit 5 a Surdité de perception (neurosensorielle).
   - AR : مريض السرير ٥ عنده فقدان السمع الحسي العصبي.
5. **EN** The patient in bed 5 does not have conductive hearing loss.
   - FR : Le patient du lit 5 n'a pas de Surdité de transmission.
   - AR : مريض السرير ٥ ليس عنده فقدان السمع التوصيلي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:conductive-hearing-loss:v1` — **conductive hearing loss** · FR : Surdité de transmission · AR : فقدان السمع التوصيلي
- `gen:sensorineural-hearing-loss:v1` — **sensorineural hearing loss** · FR : Surdité de perception (neurosensorielle) · AR : فقدان السمع الحسي العصبي
- `gen:sound:v1` — **a sound** · FR : Son · AR : الصوت

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : conductive hearing loss ; the patient with conductive hearing loss
  - modèle : The patient in bed 3 has conductive hearing loss.
- **q2.** What does the patient in bed 5 have?
  - accepté : sensorineural hearing loss
  - modèle : He has sensorineural hearing loss.
- **q3.** Does the patient in bed 5 have conductive hearing loss?
  - accepté : no ; no he does not ; no he doesn't ; he does not have conductive hearing loss ; he doesn't
  - modèle : No, he does not have conductive hearing loss.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS surdité de transmission — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : sound ; a sound
  - modèle : He has a sound.
- **Tt2.** Does the patient in bed 9 have sensorineural hearing loss?
  - accepté : no ; no he does not ; no he doesn't ; he does not have sensorineural hearing loss ; he doesn't
  - modèle : No, he does not have sensorineural hearing loss.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS surdité de perception (neurosensorielle).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … conductive hearing loss and sensorineural hearing loss. (have)` → have · modèle : The patients have conductive hearing loss and sensorineural hearing loss.
- `The patient in bed 5 … not have conductive hearing loss. (do)` → does · modèle : The patient in bed 5 does not have conductive hearing loss.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has conductive hearing loss. The patient in bed 5 has sensorineural hearing loss.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Son, le lit 9 a Surdité de transmission et n'a pas Surdité de perception (neurosensorielle).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a sound. The patient in bed 9 has conductive hearing loss.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
