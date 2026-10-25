# Relecture — Tissu Osseux (histo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `histo-case-009` — Le cas de Sara : Ostéocyte anormal(e) / _Sara: the osteocyte is not normal_

**Objectif :** Comprendre des résultats : Tissu osseux normal(e), Ostéocyte anormal(e).
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
4. **EN** The doctor says, “The bone tissue is normal.”
   - FR : Le médecin dit : « Tissu osseux est normal(e). »
   - AR : يقول الطبيب: «النسيج العظمي طبيعي/طبيعية.»
5. **EN** “But the osteocyte is not normal.”
   - FR : « Mais Ostéocyte n'est pas normal(e). »
   - AR : «لكن الخلية العظمية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the osteocyte normal?”
   - FR : Amine demande : « Ostéocyte est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الخلية العظمية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:bone-tissue:v1` — **a bone tissue** · FR : Tissu osseux · AR : النسيج العظمي
- `gen:osteoblast:v1` — **an osteoblast** · FR : Ostéoblaste · AR : أرومة عظمية (بانية العظم)
- `gen:osteocyte:v1` — **an osteocyte** · FR : Ostéocyte · AR : الخلية العظمية

### Questions (acceptées / modèle / hint)
- **q1.** Is the bone tissue normal?
  - accepté : yes ; yes it is ; the bone tissue is normal ; it is normal
  - modèle : Yes, the bone tissue is normal.
- **q2.** Is the osteocyte normal?
  - accepté : no ; no it is not ; no it isn't ; the osteocyte is not normal ; it is not normal ; it isn't normal
  - modèle : No, the osteocyte is not normal.
  - hint (si erreur) — FR : Attention : Ostéocyte n'est PAS normal(e).
- **q3.** Which one is not normal: the bone tissue or the osteocyte?
  - accepté : osteocyte ; the osteocyte
  - modèle : The osteocyte is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the osteoblast normal?
  - accepté : yes ; yes it is ; the osteoblast is normal
  - modèle : Yes, the osteoblast is normal.
- **Tt2.** Is the bone tissue normal?
  - accepté : no ; no it is not ; no it isn't ; the bone tissue is not normal ; it is not normal
  - modèle : No, the bone tissue is not normal.
  - hint (si erreur) — FR : Attention : Tissu osseux n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The bone tissue … normal. (be)` → is · modèle : The bone tissue is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The bone tissue is… / The osteocyte is not… — modèle : *The bone tissue is normal. The osteocyte is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Ostéoblaste normal(e), mais Tissu osseux anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The osteoblast is normal. The bone tissue is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `histo-explain-009` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Tissu osseux et Ostéoblaste à un patient.
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
4. **EN** “First, we check bone tissue.”
   - FR : « D'abord, nous vérifions Tissu osseux. »
   - AR : «أولًا، نتحقق من النسيج العظمي.»
5. **EN** “Then, we check osteoblast.”
   - FR : « Ensuite, nous vérifions Ostéoblaste. »
   - AR : «ثم نتحقق من أرومة عظمية (بانية العظم).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:bone-tissue:v1` — **a bone tissue** · FR : Tissu osseux · AR : النسيج العظمي
- `gen:osteoblast:v1` — **an osteoblast** · FR : Ostéoblaste · AR : أرومة عظمية (بانية العظم)
- `gen:osteocyte:v1` — **an osteocyte** · FR : Ostéocyte · AR : الخلية العظمية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : bone tissue ; we check bone tissue ; bone tissue first ; the doctor checks bone tissue first
  - modèle : First, the doctor checks bone tissue.
- **q2.** What does the doctor check next?
  - accepté : osteoblast ; then we check osteoblast ; osteoblast next ; the doctor checks osteoblast next
  - modèle : Then, the doctor checks osteoblast.
- **q3.** Does the doctor check osteoblast before bone tissue?
  - accepté : no ; no he does not ; no he doesn't ; he checks bone tissue first
  - modèle : No, he checks bone tissue first, then osteoblast.
  - hint (si erreur) — FR : Attention : Tissu osseux vient D'ABORD, ensuite Ostéoblaste.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : osteocyte ; osteocyte first ; he checks osteocyte first
  - modèle : First, the doctor checks osteocyte.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check bone tissue. Then, we check osteoblast.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Ostéocyte, ensuite Tissu osseux, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check osteocyte. Then, we check bone tissue.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `histo-handover-009` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Tissu osseux / Ostéoblaste, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the bone tissue.
   - FR : Le patient du lit 3 a Tissu osseux.
   - AR : مريض السرير ٣ عنده النسيج العظمي.
4. **EN** The patient in bed 5 has a problem with the osteoblast.
   - FR : Le patient du lit 5 a Ostéoblaste.
   - AR : مريض السرير ٥ عنده أرومة عظمية (بانية العظم).
5. **EN** The patient in bed 5 does not have a problem with the bone tissue.
   - FR : Le patient du lit 5 n'a pas de Tissu osseux.
   - AR : مريض السرير ٥ ليس عنده النسيج العظمي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:bone-tissue:v1` — **a bone tissue** · FR : Tissu osseux · AR : النسيج العظمي
- `gen:osteoblast:v1` — **an osteoblast** · FR : Ostéoblaste · AR : أرومة عظمية (بانية العظم)
- `gen:osteocyte:v1` — **an osteocyte** · FR : Ostéocyte · AR : الخلية العظمية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : bone tissue ; a bone tissue ; the patient with bone tissue
  - modèle : The patient in bed 3 has a bone tissue.
- **q2.** What does the patient in bed 5 have?
  - accepté : osteoblast ; a problem with the osteoblast
  - modèle : He has a problem with the osteoblast.
- **q3.** Does the patient in bed 5 have bone tissue?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the bone tissue ; he doesn't
  - modèle : No, he does not have a problem with the bone tissue.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS tissu osseux — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : osteocyte ; a problem with the osteocyte
  - modèle : He has a problem with the osteocyte.
- **Tt2.** Does the patient in bed 9 have osteoblast?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the osteoblast ; he doesn't
  - modèle : No, he does not have a problem with the osteoblast.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS ostéoblaste.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a bone tissue and an osteoblast. (have)` → have · modèle : The patients have a bone tissue and an osteoblast.
- `The patient in bed 5 … not have a bone tissue. (do)` → does · modèle : The patient in bed 5 does not have a bone tissue.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the bone tissue. The patient in bed 5 has a problem with the osteoblast.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Ostéocyte, le lit 9 a Tissu osseux et n'a pas Ostéoblaste.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the osteocyte. The patient in bed 9 has a problem with the bone tissue.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
