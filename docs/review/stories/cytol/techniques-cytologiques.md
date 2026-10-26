# Relecture — Techniques Cytologiques (cytol)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `cytol-case-009` — Le cas de Sara : Frottis anormal(e) / _Sara: the smear is not normal_

**Objectif :** Comprendre des résultats : Cytologie normal(e), Frottis anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The cytology is normal.”
   - FR : Le médecin dit : « Cytologie est normal(e). »
   - AR : يقول الطبيب: «علم الخلية (السيتولوجيا) طبيعي/طبيعية.»
5. **EN** “But the smear is not normal.”
   - FR : « Mais Frottis n'est pas normal(e). »
   - AR : «لكن اللطاخة غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the smear normal?”
   - FR : Amine demande : « Frottis est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل اللطاخة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:cytology:v1` — **a cytology** · FR : Cytologie · AR : علم الخلية (السيتولوجيا)
- `gen:fine-needle-aspiration:v1` — **a fine needle aspiration** · FR : Cytoponction à l'aiguille fine (FNAC) · AR : خزعة بالإبرة الدقيقة
- `gen:smear:v1` — **a smear** · FR : Frottis · AR : اللطاخة

### Questions (acceptées / modèle / hint)
- **q1.** Is the cytology normal?
  - accepté : yes ; yes it is ; the cytology is normal ; it is normal
  - modèle : Yes, the cytology is normal.
- **q2.** Is the smear normal?
  - accepté : no ; no it is not ; no it isn't ; the smear is not normal ; it is not normal ; it isn't normal
  - modèle : No, the smear is not normal.
  - hint (si erreur) — FR : Attention : Frottis n'est PAS normal(e).
- **q3.** Which one is not normal: the cytology or the smear?
  - accepté : smear ; the smear
  - modèle : The smear is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the fine needle aspiration normal?
  - accepté : yes ; yes it is ; the fine needle aspiration is normal
  - modèle : Yes, the fine needle aspiration is normal.
- **Tt2.** Is the cytology normal?
  - accepté : no ; no it is not ; no it isn't ; the cytology is not normal ; it is not normal
  - modèle : No, the cytology is not normal.
  - hint (si erreur) — FR : Attention : Cytologie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The cytology … normal. (be)` → is · modèle : The cytology is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The cytology is… / The smear is not… — modèle : *The cytology is normal. The smear is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Cytoponction à l'aiguille fine (FNAC) normal(e), mais Cytologie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The fine needle aspiration is normal. The cytology is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `cytol-explain-009` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Cytologie et Cytoponction à l'aiguille fine (FNAC) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient.
   - FR : Leila est un·e patient·e.
   - AR : Leila مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check cytology.”
   - FR : « D'abord, nous vérifions Cytologie. »
   - AR : «أولًا، نتحقق من علم الخلية (السيتولوجيا).»
5. **EN** “Then, we check fine needle aspiration.”
   - FR : « Ensuite, nous vérifions Cytoponction à l'aiguille fine (FNAC). »
   - AR : «ثم نتحقق من خزعة بالإبرة الدقيقة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:cytology:v1` — **a cytology** · FR : Cytologie · AR : علم الخلية (السيتولوجيا)
- `gen:fine-needle-aspiration:v1` — **a fine needle aspiration** · FR : Cytoponction à l'aiguille fine (FNAC) · AR : خزعة بالإبرة الدقيقة
- `gen:smear:v1` — **a smear** · FR : Frottis · AR : اللطاخة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : cytology ; we check cytology ; cytology first ; the doctor checks cytology first
  - modèle : First, the doctor checks cytology.
- **q2.** What does the doctor check next?
  - accepté : fine needle aspiration ; then we check fine needle aspiration ; fine needle aspiration next ; the doctor checks fine needle aspiration next
  - modèle : Then, the doctor checks fine needle aspiration.
- **q3.** Does the doctor check fine needle aspiration before cytology?
  - accepté : no ; no he does not ; no he doesn't ; he checks cytology first
  - modèle : No, he checks cytology first, then fine needle aspiration.
  - hint (si erreur) — FR : Attention : Cytologie vient D'ABORD, ensuite Cytoponction à l'aiguille fine (FNAC).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : smear ; smear first ; he checks smear first
  - modèle : First, the doctor checks smear.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check cytology. Then, we check fine needle aspiration.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Frottis, ensuite Cytologie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check smear. Then, we check cytology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `cytol-handover-009` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Cytologie / Cytoponction à l'aiguille fine (FNAC), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the cytology.
   - FR : Le patient du lit 3 a Cytologie.
   - AR : مريض السرير ٣ عنده علم الخلية (السيتولوجيا).
4. **EN** The patient in bed 5 has a problem with the fine needle aspiration.
   - FR : Le patient du lit 5 a Cytoponction à l'aiguille fine (FNAC).
   - AR : مريض السرير ٥ عنده خزعة بالإبرة الدقيقة.
5. **EN** The patient in bed 5 does not have a problem with the cytology.
   - FR : Le patient du lit 5 n'a pas de Cytologie.
   - AR : مريض السرير ٥ ليس عنده علم الخلية (السيتولوجيا).
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:cytology:v1` — **a cytology** · FR : Cytologie · AR : علم الخلية (السيتولوجيا)
- `gen:fine-needle-aspiration:v1` — **a fine needle aspiration** · FR : Cytoponction à l'aiguille fine (FNAC) · AR : خزعة بالإبرة الدقيقة
- `gen:smear:v1` — **a smear** · FR : Frottis · AR : اللطاخة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : cytology ; a cytology ; the patient with cytology
  - modèle : The patient in bed 3 has a cytology.
- **q2.** What does the patient in bed 5 have?
  - accepté : fine needle aspiration ; a problem with the fine needle aspiration
  - modèle : He has a problem with the fine needle aspiration.
- **q3.** Does the patient in bed 5 have cytology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the cytology ; he doesn't
  - modèle : No, he does not have a problem with the cytology.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS cytologie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : smear ; a problem with the smear
  - modèle : He has a problem with the smear.
- **Tt2.** Does the patient in bed 9 have fine needle aspiration?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the fine needle aspiration ; he doesn't
  - modèle : No, he does not have a problem with the fine needle aspiration.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS cytoponction à l'aiguille fine (fnac).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a cytology and a fine needle aspiration. (have)` → have · modèle : The patients have a cytology and a fine needle aspiration.
- `The patient in bed 5 … not have a cytology. (do)` → does · modèle : The patient in bed 5 does not have a cytology.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the cytology. The patient in bed 5 has a problem with the fine needle aspiration.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Frottis, le lit 9 a Cytologie et n'a pas Cytoponction à l'aiguille fine (FNAC).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the smear. The patient in bed 9 has a problem with the cytology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
