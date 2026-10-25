# Relecture — Appareil digestif (anat)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anat-case-001` — Le cas de Nadia : Dent anormal(e) / _Nadia: the tooth is not normal_

**Objectif :** Comprendre des résultats : Système digestif normal(e), Dent anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The digestive system is normal.”
   - FR : Le médecin dit : « Système digestif est normal(e). »
   - AR : يقول الطبيب: «الجهاز الهضمي طبيعي/طبيعية.»
5. **EN** “But the tooth is not normal.”
   - FR : « Mais Dent n'est pas normal(e). »
   - AR : «لكن السن غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the tooth normal?”
   - FR : Amine demande : « Dent est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل السن طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:digestive-system:v1` — **a digestive system** · FR : Système digestif · AR : الجهاز الهضمي
- `gen:mouth:v1` — **a mouth** · FR : Bouche · AR : الفم
- `gen:tooth:v1` — **a tooth** · FR : Dent · AR : السن

### Questions (acceptées / modèle / hint)
- **q1.** Is the digestive system normal?
  - accepté : yes ; yes it is ; the digestive system is normal ; it is normal
  - modèle : Yes, the digestive system is normal.
- **q2.** Is the tooth normal?
  - accepté : no ; no it is not ; no it isn't ; the tooth is not normal ; it is not normal ; it isn't normal
  - modèle : No, the tooth is not normal.
  - hint (si erreur) — FR : Attention : Dent n'est PAS normal(e).
- **q3.** Which one is not normal: the digestive system or the tooth?
  - accepté : tooth ; the tooth
  - modèle : The tooth is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the mouth normal?
  - accepté : yes ; yes it is ; the mouth is normal
  - modèle : Yes, the mouth is normal.
- **Tt2.** Is the digestive system normal?
  - accepté : no ; no it is not ; no it isn't ; the digestive system is not normal ; it is not normal
  - modèle : No, the digestive system is not normal.
  - hint (si erreur) — FR : Attention : Système digestif n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The digestive system … normal. (be)` → is · modèle : The digestive system is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The digestive system is… / The tooth is not… — modèle : *The digestive system is normal. The tooth is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Bouche normal(e), mais Système digestif anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The mouth is normal. The digestive system is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anat-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Système digestif et Bouche à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient.
   - FR : Amel est un·e patient·e.
   - AR : Amel مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check digestive system.”
   - FR : « D'abord, nous vérifions Système digestif. »
   - AR : «أولًا، نتحقق من الجهاز الهضمي.»
5. **EN** “Then, we check mouth.”
   - FR : « Ensuite, nous vérifions Bouche. »
   - AR : «ثم نتحقق من الفم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:digestive-system:v1` — **a digestive system** · FR : Système digestif · AR : الجهاز الهضمي
- `gen:mouth:v1` — **a mouth** · FR : Bouche · AR : الفم
- `gen:tooth:v1` — **a tooth** · FR : Dent · AR : السن

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : digestive system ; we check digestive system ; digestive system first ; the doctor checks digestive system first
  - modèle : First, the doctor checks digestive system.
- **q2.** What does the doctor check next?
  - accepté : mouth ; then we check mouth ; mouth next ; the doctor checks mouth next
  - modèle : Then, the doctor checks mouth.
- **q3.** Does the doctor check mouth before digestive system?
  - accepté : no ; no he does not ; no he doesn't ; he checks digestive system first
  - modèle : No, he checks digestive system first, then mouth.
  - hint (si erreur) — FR : Attention : Système digestif vient D'ABORD, ensuite Bouche.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : tooth ; tooth first ; he checks tooth first
  - modèle : First, the doctor checks tooth.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check digestive system. Then, we check mouth.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Dent, ensuite Système digestif, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check tooth. Then, we check digestive system.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anat-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Système digestif / Bouche, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the digestive system.
   - FR : Le patient du lit 3 a Système digestif.
   - AR : مريض السرير ٣ عنده الجهاز الهضمي.
4. **EN** The patient in bed 5 has a problem with the mouth.
   - FR : Le patient du lit 5 a Bouche.
   - AR : مريض السرير ٥ عنده الفم.
5. **EN** The patient in bed 5 does not have a problem with the digestive system.
   - FR : Le patient du lit 5 n'a pas de Système digestif.
   - AR : مريض السرير ٥ ليس عنده الجهاز الهضمي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:digestive-system:v1` — **a digestive system** · FR : Système digestif · AR : الجهاز الهضمي
- `gen:mouth:v1` — **a mouth** · FR : Bouche · AR : الفم
- `gen:tooth:v1` — **a tooth** · FR : Dent · AR : السن

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : digestive system ; a digestive system ; the patient with digestive system
  - modèle : The patient in bed 3 has a digestive system.
- **q2.** What does the patient in bed 5 have?
  - accepté : mouth ; a problem with the mouth
  - modèle : He has a problem with the mouth.
- **q3.** Does the patient in bed 5 have digestive system?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the digestive system ; he doesn't
  - modèle : No, he does not have a problem with the digestive system.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS système digestif — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : tooth ; a problem with the tooth
  - modèle : He has a problem with the tooth.
- **Tt2.** Does the patient in bed 9 have mouth?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the mouth ; he doesn't
  - modèle : No, he does not have a problem with the mouth.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS bouche.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a digestive system and a mouth. (have)` → have · modèle : The patients have a digestive system and a mouth.
- `The patient in bed 5 … not have a digestive system. (do)` → does · modèle : The patient in bed 5 does not have a digestive system.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the digestive system. The patient in bed 5 has a problem with the mouth.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Dent, le lit 9 a Système digestif et n'a pas Bouche.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the tooth. The patient in bed 9 has a problem with the digestive system.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
