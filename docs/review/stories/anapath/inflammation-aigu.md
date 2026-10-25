# Relecture — Inflammation Aiguë (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-001` — Le cas de Nadia : Signes cardinaux de l'inflammation anormal(e) / _Nadia: the cardinal signs of inflammation is not normal_

**Objectif :** Comprendre des résultats : Inflammation normal(e), Signes cardinaux de l'inflammation anormal(e).
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
4. **EN** The doctor says, “The inflammation is normal.”
   - FR : Le médecin dit : « Inflammation est normal(e). »
   - AR : يقول الطبيب: «الالتهاب طبيعي/طبيعية.»
5. **EN** “But the cardinal signs of inflammation is not normal.”
   - FR : « Mais Signes cardinaux de l'inflammation n'est pas normal(e). »
   - AR : «لكن العلامات الأساسية للالتهاب غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the cardinal signs of inflammation normal?”
   - FR : Amine demande : « Signes cardinaux de l'inflammation est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل العلامات الأساسية للالتهاب طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:inflammation:v1` — **an inflammation** · FR : Inflammation · AR : الالتهاب
- `gen:acute-inflammation:v1` — **an acute inflammation** · FR : Inflammation aiguë · AR : الالتهاب الحاد
- `gen:cardinal-signs-of-inflammation:v1` — **a cardinal signs of inflammation** · FR : Signes cardinaux de l'inflammation · AR : العلامات الأساسية للالتهاب

### Questions (acceptées / modèle / hint)
- **q1.** Is the inflammation normal?
  - accepté : yes ; yes it is ; the inflammation is normal ; it is normal
  - modèle : Yes, the inflammation is normal.
- **q2.** Is the cardinal signs of inflammation normal?
  - accepté : no ; no it is not ; no it isn't ; the cardinal signs of inflammation is not normal ; it is not normal ; it isn't normal
  - modèle : No, the cardinal signs of inflammation is not normal.
  - hint (si erreur) — FR : Attention : Signes cardinaux de l'inflammation n'est PAS normal(e).
- **q3.** Which one is not normal: the inflammation or the cardinal signs of inflammation?
  - accepté : cardinal signs of inflammation ; the cardinal signs of inflammation
  - modèle : The cardinal signs of inflammation is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the acute inflammation normal?
  - accepté : yes ; yes it is ; the acute inflammation is normal
  - modèle : Yes, the acute inflammation is normal.
- **Tt2.** Is the inflammation normal?
  - accepté : no ; no it is not ; no it isn't ; the inflammation is not normal ; it is not normal
  - modèle : No, the inflammation is not normal.
  - hint (si erreur) — FR : Attention : Inflammation n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The inflammation … normal. (be)` → is · modèle : The inflammation is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The inflammation is… / The cardinal signs of inflammation is not… — modèle : *The inflammation is normal. The cardinal signs of inflammation is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Inflammation aiguë normal(e), mais Inflammation anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The acute inflammation is normal. The inflammation is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Inflammation et Inflammation aiguë à un patient.
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
4. **EN** “First, we check inflammation.”
   - FR : « D'abord, nous vérifions Inflammation. »
   - AR : «أولًا، نتحقق من الالتهاب.»
5. **EN** “Then, we check acute inflammation.”
   - FR : « Ensuite, nous vérifions Inflammation aiguë. »
   - AR : «ثم نتحقق من الالتهاب الحاد.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:inflammation:v1` — **an inflammation** · FR : Inflammation · AR : الالتهاب
- `gen:acute-inflammation:v1` — **an acute inflammation** · FR : Inflammation aiguë · AR : الالتهاب الحاد
- `gen:cardinal-signs-of-inflammation:v1` — **a cardinal signs of inflammation** · FR : Signes cardinaux de l'inflammation · AR : العلامات الأساسية للالتهاب

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : inflammation ; we check inflammation ; inflammation first ; the doctor checks inflammation first
  - modèle : First, the doctor checks inflammation.
- **q2.** What does the doctor check next?
  - accepté : acute inflammation ; then we check acute inflammation ; acute inflammation next ; the doctor checks acute inflammation next
  - modèle : Then, the doctor checks acute inflammation.
- **q3.** Does the doctor check acute inflammation before inflammation?
  - accepté : no ; no he does not ; no he doesn't ; he checks inflammation first
  - modèle : No, he checks inflammation first, then acute inflammation.
  - hint (si erreur) — FR : Attention : Inflammation vient D'ABORD, ensuite Inflammation aiguë.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : cardinal signs of inflammation ; cardinal signs of inflammation first ; he checks cardinal signs of inflammation first
  - modèle : First, the doctor checks cardinal signs of inflammation.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check inflammation. Then, we check acute inflammation.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Signes cardinaux de l'inflammation, ensuite Inflammation, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check cardinal signs of inflammation. Then, we check inflammation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Inflammation / Inflammation aiguë, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the inflammation.
   - FR : Le patient du lit 3 a Inflammation.
   - AR : مريض السرير ٣ عنده الالتهاب.
4. **EN** The patient in bed 5 has a problem with the acute inflammation.
   - FR : Le patient du lit 5 a Inflammation aiguë.
   - AR : مريض السرير ٥ عنده الالتهاب الحاد.
5. **EN** The patient in bed 5 does not have a problem with the inflammation.
   - FR : Le patient du lit 5 n'a pas de Inflammation.
   - AR : مريض السرير ٥ ليس عنده الالتهاب.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:inflammation:v1` — **an inflammation** · FR : Inflammation · AR : الالتهاب
- `gen:acute-inflammation:v1` — **an acute inflammation** · FR : Inflammation aiguë · AR : الالتهاب الحاد
- `gen:cardinal-signs-of-inflammation:v1` — **a cardinal signs of inflammation** · FR : Signes cardinaux de l'inflammation · AR : العلامات الأساسية للالتهاب

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : inflammation ; an inflammation ; the patient with inflammation
  - modèle : The patient in bed 3 has an inflammation.
- **q2.** What does the patient in bed 5 have?
  - accepté : acute inflammation ; a problem with the acute inflammation
  - modèle : He has a problem with the acute inflammation.
- **q3.** Does the patient in bed 5 have inflammation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the inflammation ; he doesn't
  - modèle : No, he does not have a problem with the inflammation.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS inflammation — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : cardinal signs of inflammation ; a problem with the cardinal signs of inflammation
  - modèle : He has a problem with the cardinal signs of inflammation.
- **Tt2.** Does the patient in bed 9 have acute inflammation?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the acute inflammation ; he doesn't
  - modèle : No, he does not have a problem with the acute inflammation.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS inflammation aiguë.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an inflammation and an acute inflammation. (have)` → have · modèle : The patients have an inflammation and an acute inflammation.
- `The patient in bed 5 … not have an inflammation. (do)` → does · modèle : The patient in bed 5 does not have an inflammation.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the inflammation. The patient in bed 5 has a problem with the acute inflammation.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Signes cardinaux de l'inflammation, le lit 9 a Inflammation et n'a pas Inflammation aiguë.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the cardinal signs of inflammation. The patient in bed 9 has a problem with the inflammation.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
