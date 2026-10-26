# Relecture — Système Musculo-Squelettique (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-031` — Le cas de Hana : Tendon anormal(e) / _Hana: the tendon is not normal_

**Objectif :** Comprendre des résultats : Os normal(e), Tendon anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient at the clinic.
   - FR : Hana est un·e patient·e à la clinique.
   - AR : Hana مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The bone is normal.”
   - FR : Le médecin dit : « Os est normal(e). »
   - AR : يقول الطبيب: «العظم طبيعي/طبيعية.»
5. **EN** “But the tendon is not normal.”
   - FR : « Mais Tendon n'est pas normal(e). »
   - AR : «لكن الوتر غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the tendon normal?”
   - FR : Amine demande : « Tendon est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الوتر طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:bone:v1` — **a bone** · FR : Os · AR : العظم
- `gen:muscle:v1` — **a muscle** · FR : Muscle · AR : العضلة
- `gen:tendon:v1` — **a tendon** · FR : Tendon · AR : الوتر

### Questions (acceptées / modèle / hint)
- **q1.** Is the bone normal?
  - accepté : yes ; yes it is ; the bone is normal ; it is normal
  - modèle : Yes, the bone is normal.
- **q2.** Is the tendon normal?
  - accepté : no ; no it is not ; no it isn't ; the tendon is not normal ; it is not normal ; it isn't normal
  - modèle : No, the tendon is not normal.
  - hint (si erreur) — FR : Attention : Tendon n'est PAS normal(e).
- **q3.** Which one is not normal: the bone or the tendon?
  - accepté : tendon ; the tendon
  - modèle : The tendon is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the muscle normal?
  - accepté : yes ; yes it is ; the muscle is normal
  - modèle : Yes, the muscle is normal.
- **Tt2.** Is the bone normal?
  - accepté : no ; no it is not ; no it isn't ; the bone is not normal ; it is not normal
  - modèle : No, the bone is not normal.
  - hint (si erreur) — FR : Attention : Os n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The bone … normal. (be)` → is · modèle : The bone is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Hana en deux phrases. Garde la négation. — amorces : The bone is… / The tendon is not… — modèle : *The bone is normal. The tendon is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Muscle normal(e), mais Os anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The muscle is normal. The bone is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-031` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Os et Muscle à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient.
   - FR : Sara est un·e patient·e.
   - AR : Sara مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check bone.”
   - FR : « D'abord, nous vérifions Os. »
   - AR : «أولًا، نتحقق من العظم.»
5. **EN** “Then, we check muscle.”
   - FR : « Ensuite, nous vérifions Muscle. »
   - AR : «ثم نتحقق من العضلة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:bone:v1` — **a bone** · FR : Os · AR : العظم
- `gen:muscle:v1` — **a muscle** · FR : Muscle · AR : العضلة
- `gen:tendon:v1` — **a tendon** · FR : Tendon · AR : الوتر

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : bone ; we check bone ; bone first ; the doctor checks bone first
  - modèle : First, the doctor checks bone.
- **q2.** What does the doctor check next?
  - accepté : muscle ; then we check muscle ; muscle next ; the doctor checks muscle next
  - modèle : Then, the doctor checks muscle.
- **q3.** Does the doctor check muscle before bone?
  - accepté : no ; no he does not ; no he doesn't ; he checks bone first
  - modèle : No, he checks bone first, then muscle.
  - hint (si erreur) — FR : Attention : Os vient D'ABORD, ensuite Muscle.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : tendon ; tendon first ; he checks tendon first
  - modèle : First, the doctor checks tendon.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check bone. Then, we check muscle.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Tendon, ensuite Os, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check tendon. Then, we check bone.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-031` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Os / Muscle, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the bone.
   - FR : Le patient du lit 3 a Os.
   - AR : مريض السرير ٣ عنده العظم.
4. **EN** The patient in bed 5 has a problem with the muscle.
   - FR : Le patient du lit 5 a Muscle.
   - AR : مريض السرير ٥ عنده العضلة.
5. **EN** The patient in bed 5 does not have a problem with the bone.
   - FR : Le patient du lit 5 n'a pas de Os.
   - AR : مريض السرير ٥ ليس عنده العظم.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:bone:v1` — **a bone** · FR : Os · AR : العظم
- `gen:muscle:v1` — **a muscle** · FR : Muscle · AR : العضلة
- `gen:tendon:v1` — **a tendon** · FR : Tendon · AR : الوتر

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : bone ; a bone ; the patient with bone
  - modèle : The patient in bed 3 has a bone.
- **q2.** What does the patient in bed 5 have?
  - accepté : muscle ; a problem with the muscle
  - modèle : He has a problem with the muscle.
- **q3.** Does the patient in bed 5 have bone?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the bone ; he doesn't
  - modèle : No, he does not have a problem with the bone.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS os — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : tendon ; a problem with the tendon
  - modèle : He has a problem with the tendon.
- **Tt2.** Does the patient in bed 9 have muscle?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the muscle ; he doesn't
  - modèle : No, he does not have a problem with the muscle.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS muscle.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a bone and a muscle. (have)` → have · modèle : The patients have a bone and a muscle.
- `The patient in bed 5 … not have a bone. (do)` → does · modèle : The patient in bed 5 does not have a bone.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the bone. The patient in bed 5 has a problem with the muscle.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Tendon, le lit 9 a Os et n'a pas Muscle.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the tendon. The patient in bed 9 has a problem with the bone.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
