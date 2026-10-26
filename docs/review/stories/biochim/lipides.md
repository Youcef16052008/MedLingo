# Relecture — Lipides (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-015` — Le cas de Amel : Acide gras insaturé anormal(e) / _Amel: the unsaturated fatty acid is not normal_

**Objectif :** Comprendre des résultats : Lipide normal(e), Acide gras insaturé anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The lipid is normal.”
   - FR : Le médecin dit : « Lipide est normal(e). »
   - AR : يقول الطبيب: «الدهن (الليبيد) طبيعي/طبيعية.»
5. **EN** “But the unsaturated fatty acid is not normal.”
   - FR : « Mais Acide gras insaturé n'est pas normal(e). »
   - AR : «لكن حمض دهني غير مشبع غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the unsaturated fatty acid normal?”
   - FR : Amine demande : « Acide gras insaturé est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل حمض دهني غير مشبع طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:lipid:v1` — **a lipid** · FR : Lipide · AR : الدهن (الليبيد)
- `gen:saturated-fatty-acid:v1` — **a saturated fatty acid** · FR : Acide gras saturé · AR : حمض دهني مشبع
- `gen:unsaturated-fatty-acid:v1` — **an unsaturated fatty acid** · FR : Acide gras insaturé · AR : حمض دهني غير مشبع

### Questions (acceptées / modèle / hint)
- **q1.** Is the lipid normal?
  - accepté : yes ; yes it is ; the lipid is normal ; it is normal
  - modèle : Yes, the lipid is normal.
- **q2.** Is the unsaturated fatty acid normal?
  - accepté : no ; no it is not ; no it isn't ; the unsaturated fatty acid is not normal ; it is not normal ; it isn't normal
  - modèle : No, the unsaturated fatty acid is not normal.
  - hint (si erreur) — FR : Attention : Acide gras insaturé n'est PAS normal(e).
- **q3.** Which one is not normal: the lipid or the unsaturated fatty acid?
  - accepté : unsaturated fatty acid ; the unsaturated fatty acid
  - modèle : The unsaturated fatty acid is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the saturated fatty acid normal?
  - accepté : yes ; yes it is ; the saturated fatty acid is normal
  - modèle : Yes, the saturated fatty acid is normal.
- **Tt2.** Is the lipid normal?
  - accepté : no ; no it is not ; no it isn't ; the lipid is not normal ; it is not normal
  - modèle : No, the lipid is not normal.
  - hint (si erreur) — FR : Attention : Lipide n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The lipid … normal. (be)` → is · modèle : The lipid is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Amel en deux phrases. Garde la négation. — amorces : The lipid is… / The unsaturated fatty acid is not… — modèle : *The lipid is normal. The unsaturated fatty acid is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Acide gras saturé normal(e), mais Lipide anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The saturated fatty acid is normal. The lipid is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-015` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Lipide et Acide gras saturé à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient.
   - FR : Meriem est un·e patient·e.
   - AR : Meriem مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check lipid.”
   - FR : « D'abord, nous vérifions Lipide. »
   - AR : «أولًا، نتحقق من الدهن (الليبيد).»
5. **EN** “Then, we check saturated fatty acid.”
   - FR : « Ensuite, nous vérifions Acide gras saturé. »
   - AR : «ثم نتحقق من حمض دهني مشبع.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:lipid:v1` — **a lipid** · FR : Lipide · AR : الدهن (الليبيد)
- `gen:saturated-fatty-acid:v1` — **a saturated fatty acid** · FR : Acide gras saturé · AR : حمض دهني مشبع
- `gen:unsaturated-fatty-acid:v1` — **an unsaturated fatty acid** · FR : Acide gras insaturé · AR : حمض دهني غير مشبع

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : lipid ; we check lipid ; lipid first ; the doctor checks lipid first
  - modèle : First, the doctor checks lipid.
- **q2.** What does the doctor check next?
  - accepté : saturated fatty acid ; then we check saturated fatty acid ; saturated fatty acid next ; the doctor checks saturated fatty acid next
  - modèle : Then, the doctor checks saturated fatty acid.
- **q3.** Does the doctor check saturated fatty acid before lipid?
  - accepté : no ; no he does not ; no he doesn't ; he checks lipid first
  - modèle : No, he checks lipid first, then saturated fatty acid.
  - hint (si erreur) — FR : Attention : Lipide vient D'ABORD, ensuite Acide gras saturé.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : unsaturated fatty acid ; unsaturated fatty acid first ; he checks unsaturated fatty acid first
  - modèle : First, the doctor checks unsaturated fatty acid.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check lipid. Then, we check saturated fatty acid.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Acide gras insaturé, ensuite Lipide, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check unsaturated fatty acid. Then, we check lipid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-015` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Lipide / Acide gras saturé, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the lipid.
   - FR : Le patient du lit 3 a Lipide.
   - AR : مريض السرير ٣ عنده الدهن (الليبيد).
4. **EN** The patient in bed 5 has a problem with the saturated fatty acid.
   - FR : Le patient du lit 5 a Acide gras saturé.
   - AR : مريض السرير ٥ عنده حمض دهني مشبع.
5. **EN** The patient in bed 5 does not have a problem with the lipid.
   - FR : Le patient du lit 5 n'a pas de Lipide.
   - AR : مريض السرير ٥ ليس عنده الدهن (الليبيد).
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:lipid:v1` — **a lipid** · FR : Lipide · AR : الدهن (الليبيد)
- `gen:saturated-fatty-acid:v1` — **a saturated fatty acid** · FR : Acide gras saturé · AR : حمض دهني مشبع
- `gen:unsaturated-fatty-acid:v1` — **an unsaturated fatty acid** · FR : Acide gras insaturé · AR : حمض دهني غير مشبع

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : lipid ; a lipid ; the patient with lipid
  - modèle : The patient in bed 3 has a lipid.
- **q2.** What does the patient in bed 5 have?
  - accepté : saturated fatty acid ; a problem with the saturated fatty acid
  - modèle : He has a problem with the saturated fatty acid.
- **q3.** Does the patient in bed 5 have lipid?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the lipid ; he doesn't
  - modèle : No, he does not have a problem with the lipid.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS lipide — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : unsaturated fatty acid ; a problem with the unsaturated fatty acid
  - modèle : He has a problem with the unsaturated fatty acid.
- **Tt2.** Does the patient in bed 9 have saturated fatty acid?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the saturated fatty acid ; he doesn't
  - modèle : No, he does not have a problem with the saturated fatty acid.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS acide gras saturé.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a lipid and a saturated fatty acid. (have)` → have · modèle : The patients have a lipid and a saturated fatty acid.
- `The patient in bed 5 … not have a lipid. (do)` → does · modèle : The patient in bed 5 does not have a lipid.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the lipid. The patient in bed 5 has a problem with the saturated fatty acid.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Acide gras insaturé, le lit 9 a Lipide et n'a pas Acide gras saturé.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the unsaturated fatty acid. The patient in bed 9 has a problem with the lipid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
