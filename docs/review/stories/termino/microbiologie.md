# Relecture — Microbiologie (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-017` — Le cas de Meriem : Virus anormal(e) / _Meriem: the virus is not normal_

**Objectif :** Comprendre des résultats : Pathogène normal(e), Virus anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient at the clinic.
   - FR : Meriem est un·e patient·e à la clinique.
   - AR : Meriem مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The pathogen is normal.”
   - FR : Le médecin dit : « Pathogène est normal(e). »
   - AR : يقول الطبيب: «مسبب المرض طبيعي/طبيعية.»
5. **EN** “But the virus is not normal.”
   - FR : « Mais Virus n'est pas normal(e). »
   - AR : «لكن الفيروس غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the virus normal?”
   - FR : Amine demande : « Virus est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الفيروس طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:pathogen:v1` — **a pathogen** · FR : Pathogène · AR : مسبب المرض
- `gen:bacterium:v1` — **a bacterium** · FR : Bactérie (pl. Bactéries) · AR : البكتيريا
- `gen:virus:v1` — **virus** · FR : Virus · AR : الفيروس

### Questions (acceptées / modèle / hint)
- **q1.** Is the pathogen normal?
  - accepté : yes ; yes it is ; the pathogen is normal ; it is normal
  - modèle : Yes, the pathogen is normal.
- **q2.** Is the virus normal?
  - accepté : no ; no it is not ; no it isn't ; the virus is not normal ; it is not normal ; it isn't normal
  - modèle : No, the virus is not normal.
  - hint (si erreur) — FR : Attention : Virus n'est PAS normal(e).
- **q3.** Which one is not normal: the pathogen or the virus?
  - accepté : virus ; the virus
  - modèle : The virus is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the bacterium normal?
  - accepté : yes ; yes it is ; the bacterium is normal
  - modèle : Yes, the bacterium is normal.
- **Tt2.** Is the pathogen normal?
  - accepté : no ; no it is not ; no it isn't ; the pathogen is not normal ; it is not normal
  - modèle : No, the pathogen is not normal.
  - hint (si erreur) — FR : Attention : Pathogène n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The pathogen … normal. (be)` → is · modèle : The pathogen is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The pathogen is… / The virus is not… — modèle : *The pathogen is normal. The virus is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Bactérie (pl. Bactéries) normal(e), mais Pathogène anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The bacterium is normal. The pathogen is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-017` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Pathogène et Bactérie (pl. Bactéries) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient.
   - FR : Hana est un·e patient·e.
   - AR : Hana مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check pathogen.”
   - FR : « D'abord, nous vérifions Pathogène. »
   - AR : «أولًا، نتحقق من مسبب المرض.»
5. **EN** “Then, we check bacterium.”
   - FR : « Ensuite, nous vérifions Bactérie (pl. Bactéries). »
   - AR : «ثم نتحقق من البكتيريا.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:pathogen:v1` — **a pathogen** · FR : Pathogène · AR : مسبب المرض
- `gen:bacterium:v1` — **a bacterium** · FR : Bactérie (pl. Bactéries) · AR : البكتيريا
- `gen:virus:v1` — **virus** · FR : Virus · AR : الفيروس

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : pathogen ; we check pathogen ; pathogen first ; the doctor checks pathogen first
  - modèle : First, the doctor checks pathogen.
- **q2.** What does the doctor check next?
  - accepté : bacterium ; then we check bacterium ; bacterium next ; the doctor checks bacterium next
  - modèle : Then, the doctor checks bacterium.
- **q3.** Does the doctor check bacterium before pathogen?
  - accepté : no ; no he does not ; no he doesn't ; he checks pathogen first
  - modèle : No, he checks pathogen first, then bacterium.
  - hint (si erreur) — FR : Attention : Pathogène vient D'ABORD, ensuite Bactérie (pl. Bactéries).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : virus ; virus first ; he checks virus first
  - modèle : First, the doctor checks virus.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check pathogen. Then, we check bacterium.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Virus, ensuite Pathogène, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check virus. Then, we check pathogen.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-017` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Pathogène / Bactérie (pl. Bactéries), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the pathogen.
   - FR : Le patient du lit 3 a Pathogène.
   - AR : مريض السرير ٣ عنده مسبب المرض.
4. **EN** The patient in bed 5 has a problem with the bacterium.
   - FR : Le patient du lit 5 a Bactérie (pl. Bactéries).
   - AR : مريض السرير ٥ عنده البكتيريا.
5. **EN** The patient in bed 5 does not have a problem with the pathogen.
   - FR : Le patient du lit 5 n'a pas de Pathogène.
   - AR : مريض السرير ٥ ليس عنده مسبب المرض.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:pathogen:v1` — **a pathogen** · FR : Pathogène · AR : مسبب المرض
- `gen:bacterium:v1` — **a bacterium** · FR : Bactérie (pl. Bactéries) · AR : البكتيريا
- `gen:virus:v1` — **virus** · FR : Virus · AR : الفيروس

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : pathogen ; a pathogen ; the patient with pathogen
  - modèle : The patient in bed 3 has a pathogen.
- **q2.** What does the patient in bed 5 have?
  - accepté : bacterium ; a problem with the bacterium
  - modèle : He has a problem with the bacterium.
- **q3.** Does the patient in bed 5 have pathogen?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the pathogen ; he doesn't
  - modèle : No, he does not have a problem with the pathogen.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS pathogène — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : virus ; a problem with the virus
  - modèle : He has a problem with the virus.
- **Tt2.** Does the patient in bed 9 have bacterium?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the bacterium ; he doesn't
  - modèle : No, he does not have a problem with the bacterium.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS bactérie (pl. bactéries).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a pathogen and a bacterium. (have)` → have · modèle : The patients have a pathogen and a bacterium.
- `The patient in bed 5 … not have a pathogen. (do)` → does · modèle : The patient in bed 5 does not have a pathogen.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the pathogen. The patient in bed 5 has a problem with the bacterium.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Virus, le lit 9 a Pathogène et n'a pas Bactérie (pl. Bactéries).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the virus. The patient in bed 9 has a problem with the pathogen.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
