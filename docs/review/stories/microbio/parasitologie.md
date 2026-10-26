# Relecture — Parasitologie (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-011` — Le cas de Leila : Protozoaires anormal(e) / _Leila: the protozoa is not normal_

**Objectif :** Comprendre des résultats : Parasitologie normal(e), Protozoaires anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The parasitology is normal.”
   - FR : Le médecin dit : « Parasitologie est normal(e). »
   - AR : يقول الطبيب: «علم الطفيليات طبيعي/طبيعية.»
5. **EN** “But the protozoa is not normal.”
   - FR : « Mais Protozoaires n'est pas normal(e). »
   - AR : «لكن الأوليات غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the protozoa normal?”
   - FR : Amine demande : « Protozoaires est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الأوليات طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:parasitology:v1` — **a parasitology** · FR : Parasitologie · AR : علم الطفيليات
- `gen:parasite:v1` — **a parasite** · FR : Parasite · AR : الطفيلي
- `gen:protozoa:v1` — **a protozoa** · FR : Protozoaires · AR : الأوليات

### Questions (acceptées / modèle / hint)
- **q1.** Is the parasitology normal?
  - accepté : yes ; yes it is ; the parasitology is normal ; it is normal
  - modèle : Yes, the parasitology is normal.
- **q2.** Is the protozoa normal?
  - accepté : no ; no it is not ; no it isn't ; the protozoa is not normal ; it is not normal ; it isn't normal
  - modèle : No, the protozoa is not normal.
  - hint (si erreur) — FR : Attention : Protozoaires n'est PAS normal(e).
- **q3.** Which one is not normal: the parasitology or the protozoa?
  - accepté : protozoa ; the protozoa
  - modèle : The protozoa is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the parasite normal?
  - accepté : yes ; yes it is ; the parasite is normal
  - modèle : Yes, the parasite is normal.
- **Tt2.** Is the parasitology normal?
  - accepté : no ; no it is not ; no it isn't ; the parasitology is not normal ; it is not normal
  - modèle : No, the parasitology is not normal.
  - hint (si erreur) — FR : Attention : Parasitologie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The parasitology … normal. (be)` → is · modèle : The parasitology is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Leila en deux phrases. Garde la négation. — amorces : The parasitology is… / The protozoa is not… — modèle : *The parasitology is normal. The protozoa is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Parasite normal(e), mais Parasitologie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The parasite is normal. The parasitology is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-011` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Parasitologie et Parasite à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient.
   - FR : Nadia est un·e patient·e.
   - AR : Nadia مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check parasitology.”
   - FR : « D'abord, nous vérifions Parasitologie. »
   - AR : «أولًا، نتحقق من علم الطفيليات.»
5. **EN** “Then, we check parasite.”
   - FR : « Ensuite, nous vérifions Parasite. »
   - AR : «ثم نتحقق من الطفيلي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:parasitology:v1` — **a parasitology** · FR : Parasitologie · AR : علم الطفيليات
- `gen:parasite:v1` — **a parasite** · FR : Parasite · AR : الطفيلي
- `gen:protozoa:v1` — **a protozoa** · FR : Protozoaires · AR : الأوليات

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : parasitology ; we check parasitology ; parasitology first ; the doctor checks parasitology first
  - modèle : First, the doctor checks parasitology.
- **q2.** What does the doctor check next?
  - accepté : parasite ; then we check parasite ; parasite next ; the doctor checks parasite next
  - modèle : Then, the doctor checks parasite.
- **q3.** Does the doctor check parasite before parasitology?
  - accepté : no ; no he does not ; no he doesn't ; he checks parasitology first
  - modèle : No, he checks parasitology first, then parasite.
  - hint (si erreur) — FR : Attention : Parasitologie vient D'ABORD, ensuite Parasite.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : protozoa ; protozoa first ; he checks protozoa first
  - modèle : First, the doctor checks protozoa.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check parasitology. Then, we check parasite.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Protozoaires, ensuite Parasitologie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check protozoa. Then, we check parasitology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-011` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Parasitologie / Parasite, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the parasitology.
   - FR : Le patient du lit 3 a Parasitologie.
   - AR : مريض السرير ٣ عنده علم الطفيليات.
4. **EN** The patient in bed 5 has a problem with the parasite.
   - FR : Le patient du lit 5 a Parasite.
   - AR : مريض السرير ٥ عنده الطفيلي.
5. **EN** The patient in bed 5 does not have a problem with the parasitology.
   - FR : Le patient du lit 5 n'a pas de Parasitologie.
   - AR : مريض السرير ٥ ليس عنده علم الطفيليات.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:parasitology:v1` — **a parasitology** · FR : Parasitologie · AR : علم الطفيليات
- `gen:parasite:v1` — **a parasite** · FR : Parasite · AR : الطفيلي
- `gen:protozoa:v1` — **a protozoa** · FR : Protozoaires · AR : الأوليات

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : parasitology ; a parasitology ; the patient with parasitology
  - modèle : The patient in bed 3 has a parasitology.
- **q2.** What does the patient in bed 5 have?
  - accepté : parasite ; a problem with the parasite
  - modèle : He has a problem with the parasite.
- **q3.** Does the patient in bed 5 have parasitology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the parasitology ; he doesn't
  - modèle : No, he does not have a problem with the parasitology.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS parasitologie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : protozoa ; a problem with the protozoa
  - modèle : He has a problem with the protozoa.
- **Tt2.** Does the patient in bed 9 have parasite?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the parasite ; he doesn't
  - modèle : No, he does not have a problem with the parasite.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS parasite.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a parasitology and a parasite. (have)` → have · modèle : The patients have a parasitology and a parasite.
- `The patient in bed 5 … not have a parasitology. (do)` → does · modèle : The patient in bed 5 does not have a parasitology.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the parasitology. The patient in bed 5 has a problem with the parasite.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Protozoaires, le lit 9 a Parasitologie et n'a pas Parasite.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the protozoa. The patient in bed 9 has a problem with the parasitology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
