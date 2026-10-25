# Relecture — Système Respiratoire (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-033` — Le cas de Sara : Bronche (pl. Bronches) anormal(e) / _Sara: the bronchus is not normal_

**Objectif :** Comprendre des résultats : Poumon normal(e), Bronche (pl. Bronches) anormal(e).
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
4. **EN** The doctor says, “The lung is normal.”
   - FR : Le médecin dit : « Poumon est normal(e). »
   - AR : يقول الطبيب: «الرئة طبيعي/طبيعية.»
5. **EN** “But the bronchus is not normal.”
   - FR : « Mais Bronche (pl. Bronches) n'est pas normal(e). »
   - AR : «لكن القصبة الهوائية الأولية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the bronchus normal?”
   - FR : Amine demande : « Bronche (pl. Bronches) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل القصبة الهوائية الأولية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:lung:v1` — **a lung** · FR : Poumon · AR : الرئة
- `gen:trachea:v1` — **trachea** · FR : Trachée · AR : القصبة الهوائية
- `gen:bronchus:v1` — **bronchus** · FR : Bronche (pl. Bronches) · AR : القصبة الهوائية الأولية

### Questions (acceptées / modèle / hint)
- **q1.** Is the lung normal?
  - accepté : yes ; yes it is ; the lung is normal ; it is normal
  - modèle : Yes, the lung is normal.
- **q2.** Is the bronchus normal?
  - accepté : no ; no it is not ; no it isn't ; the bronchus is not normal ; it is not normal ; it isn't normal
  - modèle : No, the bronchus is not normal.
  - hint (si erreur) — FR : Attention : Bronche (pl. Bronches) n'est PAS normal(e).
- **q3.** Which one is not normal: the lung or the bronchus?
  - accepté : bronchus ; the bronchus
  - modèle : The bronchus is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the trachea normal?
  - accepté : yes ; yes it is ; the trachea is normal
  - modèle : Yes, the trachea is normal.
- **Tt2.** Is the lung normal?
  - accepté : no ; no it is not ; no it isn't ; the lung is not normal ; it is not normal
  - modèle : No, the lung is not normal.
  - hint (si erreur) — FR : Attention : Poumon n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The lung … normal. (be)` → is · modèle : The lung is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The lung is… / The bronchus is not… — modèle : *The lung is normal. The bronchus is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Trachée normal(e), mais Poumon anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The trachea is normal. The lung is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-033` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Poumon et Trachée à un patient.
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
4. **EN** “First, we check lung.”
   - FR : « D'abord, nous vérifions Poumon. »
   - AR : «أولًا، نتحقق من الرئة.»
5. **EN** “Then, we check trachea.”
   - FR : « Ensuite, nous vérifions Trachée. »
   - AR : «ثم نتحقق من القصبة الهوائية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:lung:v1` — **a lung** · FR : Poumon · AR : الرئة
- `gen:trachea:v1` — **trachea** · FR : Trachée · AR : القصبة الهوائية
- `gen:bronchus:v1` — **bronchus** · FR : Bronche (pl. Bronches) · AR : القصبة الهوائية الأولية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : lung ; we check lung ; lung first ; the doctor checks lung first
  - modèle : First, the doctor checks lung.
- **q2.** What does the doctor check next?
  - accepté : trachea ; then we check trachea ; trachea next ; the doctor checks trachea next
  - modèle : Then, the doctor checks trachea.
- **q3.** Does the doctor check trachea before lung?
  - accepté : no ; no he does not ; no he doesn't ; he checks lung first
  - modèle : No, he checks lung first, then trachea.
  - hint (si erreur) — FR : Attention : Poumon vient D'ABORD, ensuite Trachée.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : bronchus ; bronchus first ; he checks bronchus first
  - modèle : First, the doctor checks bronchus.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check lung. Then, we check trachea.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Bronche (pl. Bronches), ensuite Poumon, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check bronchus. Then, we check lung.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-033` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Poumon / Trachée, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the lung.
   - FR : Le patient du lit 3 a Poumon.
   - AR : مريض السرير ٣ عنده الرئة.
4. **EN** The patient in bed 5 has a problem with the trachea.
   - FR : Le patient du lit 5 a Trachée.
   - AR : مريض السرير ٥ عنده القصبة الهوائية.
5. **EN** The patient in bed 5 does not have a problem with the lung.
   - FR : Le patient du lit 5 n'a pas de Poumon.
   - AR : مريض السرير ٥ ليس عنده الرئة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:lung:v1` — **a lung** · FR : Poumon · AR : الرئة
- `gen:trachea:v1` — **trachea** · FR : Trachée · AR : القصبة الهوائية
- `gen:bronchus:v1` — **bronchus** · FR : Bronche (pl. Bronches) · AR : القصبة الهوائية الأولية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : lung ; a lung ; the patient with lung
  - modèle : The patient in bed 3 has a lung.
- **q2.** What does the patient in bed 5 have?
  - accepté : trachea ; a problem with the trachea
  - modèle : He has a problem with the trachea.
- **q3.** Does the patient in bed 5 have lung?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the lung ; he doesn't
  - modèle : No, he does not have a problem with the lung.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS poumon — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : bronchus ; a problem with the bronchus
  - modèle : He has a problem with the bronchus.
- **Tt2.** Does the patient in bed 9 have trachea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the trachea ; he doesn't
  - modèle : No, he does not have a problem with the trachea.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS trachée.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a lung and trachea. (have)` → have · modèle : The patients have a lung and trachea.
- `The patient in bed 5 … not have a lung. (do)` → does · modèle : The patient in bed 5 does not have a lung.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the lung. The patient in bed 5 has a problem with the trachea.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Bronche (pl. Bronches), le lit 9 a Poumon et n'a pas Trachée.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the bronchus. The patient in bed 9 has a problem with the lung.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
