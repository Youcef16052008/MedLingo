# Relecture — Généralités (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-013` — Le cas de Nadia : Épithélium anormal(e) / _Nadia: the epithelium is not normal_

**Objectif :** Comprendre des résultats : Embryologie normal(e), Épithélium anormal(e).
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
4. **EN** The doctor says, “The embryology is normal.”
   - FR : Le médecin dit : « Embryologie est normal(e). »
   - AR : يقول الطبيب: «علم الأجنة طبيعي/طبيعية.»
5. **EN** “But the epithelium is not normal.”
   - FR : « Mais Épithélium n'est pas normal(e). »
   - AR : «لكن الظهارة غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the epithelium normal?”
   - FR : Amine demande : « Épithélium est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الظهارة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:embryology:v1` — **an embryology** · FR : Embryologie · AR : علم الأجنة
- `gen:mesenchyme:v1` — **a mesenchyme** · FR : Mésenchyme · AR : السدى الجنيني
- `gen:epithelium:v1` — **an epithelium** · FR : Épithélium · AR : الظهارة

### Questions (acceptées / modèle / hint)
- **q1.** Is the embryology normal?
  - accepté : yes ; yes it is ; the embryology is normal ; it is normal
  - modèle : Yes, the embryology is normal.
- **q2.** Is the epithelium normal?
  - accepté : no ; no it is not ; no it isn't ; the epithelium is not normal ; it is not normal ; it isn't normal
  - modèle : No, the epithelium is not normal.
  - hint (si erreur) — FR : Attention : Épithélium n'est PAS normal(e).
- **q3.** Which one is not normal: the embryology or the epithelium?
  - accepté : epithelium ; the epithelium
  - modèle : The epithelium is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the mesenchyme normal?
  - accepté : yes ; yes it is ; the mesenchyme is normal
  - modèle : Yes, the mesenchyme is normal.
- **Tt2.** Is the embryology normal?
  - accepté : no ; no it is not ; no it isn't ; the embryology is not normal ; it is not normal
  - modèle : No, the embryology is not normal.
  - hint (si erreur) — FR : Attention : Embryologie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The embryology … normal. (be)` → is · modèle : The embryology is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The embryology is… / The epithelium is not… — modèle : *The embryology is normal. The epithelium is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Mésenchyme normal(e), mais Embryologie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The mesenchyme is normal. The embryology is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-013` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Embryologie et Mésenchyme à un patient.
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
4. **EN** “First, we check embryology.”
   - FR : « D'abord, nous vérifions Embryologie. »
   - AR : «أولًا، نتحقق من علم الأجنة.»
5. **EN** “Then, we check mesenchyme.”
   - FR : « Ensuite, nous vérifions Mésenchyme. »
   - AR : «ثم نتحقق من السدى الجنيني.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:embryology:v1` — **an embryology** · FR : Embryologie · AR : علم الأجنة
- `gen:mesenchyme:v1` — **a mesenchyme** · FR : Mésenchyme · AR : السدى الجنيني
- `gen:epithelium:v1` — **an epithelium** · FR : Épithélium · AR : الظهارة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : embryology ; we check embryology ; embryology first ; the doctor checks embryology first
  - modèle : First, the doctor checks embryology.
- **q2.** What does the doctor check next?
  - accepté : mesenchyme ; then we check mesenchyme ; mesenchyme next ; the doctor checks mesenchyme next
  - modèle : Then, the doctor checks mesenchyme.
- **q3.** Does the doctor check mesenchyme before embryology?
  - accepté : no ; no he does not ; no he doesn't ; he checks embryology first
  - modèle : No, he checks embryology first, then mesenchyme.
  - hint (si erreur) — FR : Attention : Embryologie vient D'ABORD, ensuite Mésenchyme.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : epithelium ; epithelium first ; he checks epithelium first
  - modèle : First, the doctor checks epithelium.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check embryology. Then, we check mesenchyme.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Épithélium, ensuite Embryologie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check epithelium. Then, we check embryology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-013` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Embryologie / Mésenchyme, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the embryology.
   - FR : Le patient du lit 3 a Embryologie.
   - AR : مريض السرير ٣ عنده علم الأجنة.
4. **EN** The patient in bed 5 has a problem with the mesenchyme.
   - FR : Le patient du lit 5 a Mésenchyme.
   - AR : مريض السرير ٥ عنده السدى الجنيني.
5. **EN** The patient in bed 5 does not have a problem with the embryology.
   - FR : Le patient du lit 5 n'a pas de Embryologie.
   - AR : مريض السرير ٥ ليس عنده علم الأجنة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:embryology:v1` — **an embryology** · FR : Embryologie · AR : علم الأجنة
- `gen:mesenchyme:v1` — **a mesenchyme** · FR : Mésenchyme · AR : السدى الجنيني
- `gen:epithelium:v1` — **an epithelium** · FR : Épithélium · AR : الظهارة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : embryology ; an embryology ; the patient with embryology
  - modèle : The patient in bed 3 has an embryology.
- **q2.** What does the patient in bed 5 have?
  - accepté : mesenchyme ; a problem with the mesenchyme
  - modèle : He has a problem with the mesenchyme.
- **q3.** Does the patient in bed 5 have embryology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the embryology ; he doesn't
  - modèle : No, he does not have a problem with the embryology.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS embryologie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : epithelium ; a problem with the epithelium
  - modèle : He has a problem with the epithelium.
- **Tt2.** Does the patient in bed 9 have mesenchyme?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the mesenchyme ; he doesn't
  - modèle : No, he does not have a problem with the mesenchyme.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS mésenchyme.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an embryology and a mesenchyme. (have)` → have · modèle : The patients have an embryology and a mesenchyme.
- `The patient in bed 5 … not have an embryology. (do)` → does · modèle : The patient in bed 5 does not have an embryology.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the embryology. The patient in bed 5 has a problem with the mesenchyme.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Épithélium, le lit 9 a Embryologie et n'a pas Mésenchyme.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the epithelium. The patient in bed 9 has a problem with the embryology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
