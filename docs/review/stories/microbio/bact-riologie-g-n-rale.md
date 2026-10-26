# Relecture — Bactériologie Générale (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-002` — Le cas de Youssef : Gram-positif anormal(e) / _Youssef: the gram-positive is not normal_

**Objectif :** Comprendre des résultats : Bactérie normal(e), Gram-positif anormal(e).
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
4. **EN** The doctor says, “The bacterium is normal.”
   - FR : Le médecin dit : « Bactérie est normal(e). »
   - AR : يقول الطبيب: «البكتيريا طبيعي/طبيعية.»
5. **EN** “But the gram-positive is not normal.”
   - FR : « Mais Gram-positif n'est pas normal(e). »
   - AR : «لكن موجبة الغرام غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the gram-positive normal?”
   - FR : Lina demande : « Gram-positif est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل موجبة الغرام طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:bacterium:v1` — **a bacterium** · FR : Bactérie · AR : البكتيريا
- `gen:gram-stain:v1` — **a gram stain** · FR : Coloration de Gram · AR : صبغة غرام
- `gen:gram-positive:v1` — **a gram-positive** · FR : Gram-positif · AR : موجبة الغرام

### Questions (acceptées / modèle / hint)
- **q1.** Is the bacterium normal?
  - accepté : yes ; yes it is ; the bacterium is normal ; it is normal
  - modèle : Yes, the bacterium is normal.
- **q2.** Is the gram-positive normal?
  - accepté : no ; no it is not ; no it isn't ; the gram-positive is not normal ; it is not normal ; it isn't normal
  - modèle : No, the gram-positive is not normal.
  - hint (si erreur) — FR : Attention : Gram-positif n'est PAS normal(e).
- **q3.** Which one is not normal: the bacterium or the gram-positive?
  - accepté : gram-positive ; the gram-positive
  - modèle : The gram-positive is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the gram stain normal?
  - accepté : yes ; yes it is ; the gram stain is normal
  - modèle : Yes, the gram stain is normal.
- **Tt2.** Is the bacterium normal?
  - accepté : no ; no it is not ; no it isn't ; the bacterium is not normal ; it is not normal
  - modèle : No, the bacterium is not normal.
  - hint (si erreur) — FR : Attention : Bactérie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The bacterium … normal. (be)` → is · modèle : The bacterium is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The bacterium is… / The gram-positive is not… — modèle : *The bacterium is normal. The gram-positive is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Coloration de Gram normal(e), mais Bactérie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The gram stain is normal. The bacterium is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Bactérie et Coloration de Gram à un patient.
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
4. **EN** “First, we check bacterium.”
   - FR : « D'abord, nous vérifions Bactérie. »
   - AR : «أولًا، نتحقق من البكتيريا.»
5. **EN** “Then, we check gram stain.”
   - FR : « Ensuite, nous vérifions Coloration de Gram. »
   - AR : «ثم نتحقق من صبغة غرام.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:bacterium:v1` — **a bacterium** · FR : Bactérie · AR : البكتيريا
- `gen:gram-stain:v1` — **a gram stain** · FR : Coloration de Gram · AR : صبغة غرام
- `gen:gram-positive:v1` — **a gram-positive** · FR : Gram-positif · AR : موجبة الغرام

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : bacterium ; we check bacterium ; bacterium first ; the doctor checks bacterium first
  - modèle : First, the doctor checks bacterium.
- **q2.** What does the doctor check next?
  - accepté : gram stain ; then we check gram stain ; gram stain next ; the doctor checks gram stain next
  - modèle : Then, the doctor checks gram stain.
- **q3.** Does the doctor check gram stain before bacterium?
  - accepté : no ; no he does not ; no he doesn't ; he checks bacterium first
  - modèle : No, he checks bacterium first, then gram stain.
  - hint (si erreur) — FR : Attention : Bactérie vient D'ABORD, ensuite Coloration de Gram.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : gram-positive ; gram-positive first ; he checks gram-positive first
  - modèle : First, the doctor checks gram-positive.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check bacterium. Then, we check gram stain.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Gram-positif, ensuite Bactérie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check gram-positive. Then, we check bacterium.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Bactérie / Coloration de Gram, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the bacterium.
   - FR : Le patient du lit 3 a Bactérie.
   - AR : مريض السرير ٣ عنده البكتيريا.
4. **EN** The patient in bed 5 has a problem with the gram stain.
   - FR : Le patient du lit 5 a Coloration de Gram.
   - AR : مريض السرير ٥ عنده صبغة غرام.
5. **EN** The patient in bed 5 does not have a problem with the bacterium.
   - FR : Le patient du lit 5 n'a pas de Bactérie.
   - AR : مريض السرير ٥ ليس عنده البكتيريا.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:bacterium:v1` — **a bacterium** · FR : Bactérie · AR : البكتيريا
- `gen:gram-stain:v1` — **a gram stain** · FR : Coloration de Gram · AR : صبغة غرام
- `gen:gram-positive:v1` — **a gram-positive** · FR : Gram-positif · AR : موجبة الغرام

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : bacterium ; a bacterium ; the patient with bacterium
  - modèle : The patient in bed 3 has a bacterium.
- **q2.** What does the patient in bed 5 have?
  - accepté : gram stain ; a problem with the gram stain
  - modèle : He has a problem with the gram stain.
- **q3.** Does the patient in bed 5 have bacterium?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the bacterium ; he doesn't
  - modèle : No, he does not have a problem with the bacterium.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS bactérie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : gram-positive ; a problem with the gram-positive
  - modèle : He has a problem with the gram-positive.
- **Tt2.** Does the patient in bed 9 have gram stain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the gram stain ; he doesn't
  - modèle : No, he does not have a problem with the gram stain.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS coloration de gram.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a bacterium and a gram stain. (have)` → have · modèle : The patients have a bacterium and a gram stain.
- `The patient in bed 5 … not have a bacterium. (do)` → does · modèle : The patient in bed 5 does not have a bacterium.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the bacterium. The patient in bed 5 has a problem with the gram stain.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Gram-positif, le lit 9 a Bactérie et n'a pas Coloration de Gram.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the gram-positive. The patient in bed 9 has a problem with the bacterium.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
