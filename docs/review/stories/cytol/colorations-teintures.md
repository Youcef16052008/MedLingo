# Relecture — Colorations & Teintures (cytol)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `cytol-case-000` — Le cas de Karim : Coloration de Giemsa anormal(e) / _Karim: the giemsa stain is not normal_

**Objectif :** Comprendre des résultats : Hématoxyline & Éosine (H&E) normal(e), Coloration de Giemsa anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient at the clinic.
   - FR : Karim est un·e patient·e à la clinique.
   - AR : Karim مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The hematoxylin & eosin is normal.”
   - FR : Le médecin dit : « Hématoxyline & Éosine (H&E) est normal(e). »
   - AR : يقول الطبيب: «هيماتوكسيلين وإيوزين طبيعي/طبيعية.»
5. **EN** “But the giemsa stain is not normal.”
   - FR : « Mais Coloration de Giemsa n'est pas normal(e). »
   - AR : «لكن تلوين غيمزا غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the giemsa stain normal?”
   - FR : Lina demande : « Coloration de Giemsa est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل تلوين غيمزا طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:hematoxylin-eosin:v1` — **a hematoxylin & eosin** · FR : Hématoxyline & Éosine (H&E) · AR : هيماتوكسيلين وإيوزين
- `gen:papanicolaou-stain:v1` — **a papanicolaou stain** · FR : Coloration de Papanicolaou (PAP) · AR : تلوين باباnikولاو
- `gen:giemsa-stain:v1` — **a giemsa stain** · FR : Coloration de Giemsa · AR : تلوين غيمزا

### Questions (acceptées / modèle / hint)
- **q1.** Is the hematoxylin & eosin normal?
  - accepté : yes ; yes it is ; the hematoxylin & eosin is normal ; it is normal
  - modèle : Yes, the hematoxylin & eosin is normal.
- **q2.** Is the giemsa stain normal?
  - accepté : no ; no it is not ; no it isn't ; the giemsa stain is not normal ; it is not normal ; it isn't normal
  - modèle : No, the giemsa stain is not normal.
  - hint (si erreur) — FR : Attention : Coloration de Giemsa n'est PAS normal(e).
- **q3.** Which one is not normal: the hematoxylin & eosin or the giemsa stain?
  - accepté : giemsa stain ; the giemsa stain
  - modèle : The giemsa stain is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the papanicolaou stain normal?
  - accepté : yes ; yes it is ; the papanicolaou stain is normal
  - modèle : Yes, the papanicolaou stain is normal.
- **Tt2.** Is the hematoxylin & eosin normal?
  - accepté : no ; no it is not ; no it isn't ; the hematoxylin & eosin is not normal ; it is not normal
  - modèle : No, the hematoxylin & eosin is not normal.
  - hint (si erreur) — FR : Attention : Hématoxyline & Éosine (H&E) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The hematoxylin & eosin … normal. (be)` → is · modèle : The hematoxylin & eosin is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The hematoxylin & eosin is… / The giemsa stain is not… — modèle : *The hematoxylin & eosin is normal. The giemsa stain is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Coloration de Papanicolaou (PAP) normal(e), mais Hématoxyline & Éosine (H&E) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The papanicolaou stain is normal. The hematoxylin & eosin is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `cytol-explain-000` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Hématoxyline & Éosine (H&E) et Coloration de Papanicolaou (PAP) à un patient.
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
4. **EN** “First, we check hematoxylin & eosin.”
   - FR : « D'abord, nous vérifions Hématoxyline & Éosine (H&E). »
   - AR : «أولًا، نتحقق من هيماتوكسيلين وإيوزين.»
5. **EN** “Then, we check papanicolaou stain.”
   - FR : « Ensuite, nous vérifions Coloration de Papanicolaou (PAP). »
   - AR : «ثم نتحقق من تلوين باباnikولاو.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:hematoxylin-eosin:v1` — **a hematoxylin & eosin** · FR : Hématoxyline & Éosine (H&E) · AR : هيماتوكسيلين وإيوزين
- `gen:papanicolaou-stain:v1` — **a papanicolaou stain** · FR : Coloration de Papanicolaou (PAP) · AR : تلوين باباnikولاو
- `gen:giemsa-stain:v1` — **a giemsa stain** · FR : Coloration de Giemsa · AR : تلوين غيمزا

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : hematoxylin & eosin ; we check hematoxylin & eosin ; hematoxylin & eosin first ; the doctor checks hematoxylin & eosin first
  - modèle : First, the doctor checks hematoxylin & eosin.
- **q2.** What does the doctor check next?
  - accepté : papanicolaou stain ; then we check papanicolaou stain ; papanicolaou stain next ; the doctor checks papanicolaou stain next
  - modèle : Then, the doctor checks papanicolaou stain.
- **q3.** Does the doctor check papanicolaou stain before hematoxylin & eosin?
  - accepté : no ; no he does not ; no he doesn't ; he checks hematoxylin & eosin first
  - modèle : No, he checks hematoxylin & eosin first, then papanicolaou stain.
  - hint (si erreur) — FR : Attention : Hématoxyline & Éosine (H&E) vient D'ABORD, ensuite Coloration de Papanicolaou (PAP).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : giemsa stain ; giemsa stain first ; he checks giemsa stain first
  - modèle : First, the doctor checks giemsa stain.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check hematoxylin & eosin. Then, we check papanicolaou stain.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Coloration de Giemsa, ensuite Hématoxyline & Éosine (H&E), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check giemsa stain. Then, we check hematoxylin & eosin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `cytol-handover-000` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Hématoxyline & Éosine (H&E) / Coloration de Papanicolaou (PAP), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the hematoxylin & eosin.
   - FR : Le patient du lit 3 a Hématoxyline & Éosine (H&E).
   - AR : مريض السرير ٣ عنده هيماتوكسيلين وإيوزين.
4. **EN** The patient in bed 5 has a problem with the papanicolaou stain.
   - FR : Le patient du lit 5 a Coloration de Papanicolaou (PAP).
   - AR : مريض السرير ٥ عنده تلوين باباnikولاو.
5. **EN** The patient in bed 5 does not have a problem with the hematoxylin & eosin.
   - FR : Le patient du lit 5 n'a pas de Hématoxyline & Éosine (H&E).
   - AR : مريض السرير ٥ ليس عنده هيماتوكسيلين وإيوزين.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:hematoxylin-eosin:v1` — **a hematoxylin & eosin** · FR : Hématoxyline & Éosine (H&E) · AR : هيماتوكسيلين وإيوزين
- `gen:papanicolaou-stain:v1` — **a papanicolaou stain** · FR : Coloration de Papanicolaou (PAP) · AR : تلوين باباnikولاو
- `gen:giemsa-stain:v1` — **a giemsa stain** · FR : Coloration de Giemsa · AR : تلوين غيمزا

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : hematoxylin & eosin ; a hematoxylin & eosin ; the patient with hematoxylin & eosin
  - modèle : The patient in bed 3 has a hematoxylin & eosin.
- **q2.** What does the patient in bed 5 have?
  - accepté : papanicolaou stain ; a problem with the papanicolaou stain
  - modèle : He has a problem with the papanicolaou stain.
- **q3.** Does the patient in bed 5 have hematoxylin & eosin?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the hematoxylin & eosin ; he doesn't
  - modèle : No, he does not have a problem with the hematoxylin & eosin.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS hématoxyline & éosine (h&e) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : giemsa stain ; a problem with the giemsa stain
  - modèle : He has a problem with the giemsa stain.
- **Tt2.** Does the patient in bed 9 have papanicolaou stain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the papanicolaou stain ; he doesn't
  - modèle : No, he does not have a problem with the papanicolaou stain.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS coloration de papanicolaou (pap).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a hematoxylin & eosin and a papanicolaou stain. (have)` → have · modèle : The patients have a hematoxylin & eosin and a papanicolaou stain.
- `The patient in bed 5 … not have a hematoxylin & eosin. (do)` → does · modèle : The patient in bed 5 does not have a hematoxylin & eosin.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the hematoxylin & eosin. The patient in bed 5 has a problem with the papanicolaou stain.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Coloration de Giemsa, le lit 9 a Hématoxyline & Éosine (H&E) et n'a pas Coloration de Papanicolaou (PAP).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the giemsa stain. The patient in bed 9 has a problem with the hematoxylin & eosin.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
