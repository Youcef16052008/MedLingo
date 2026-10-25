# Relecture — Électrochimie (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-024` — Le cas de Karim : Anode anormal(e) / _Karim: the anode is not normal_

**Objectif :** Comprendre des résultats : Électrochimie normal(e), Anode anormal(e).
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
4. **EN** The doctor says, “The electrochemistry is normal.”
   - FR : Le médecin dit : « Électrochimie est normal(e). »
   - AR : يقول الطبيب: «الكيمياء الكهربائية طبيعي/طبيعية.»
5. **EN** “But the anode is not normal.”
   - FR : « Mais Anode n'est pas normal(e). »
   - AR : «لكن المصعد (الأنود) غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the anode normal?”
   - FR : Lina demande : « Anode est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل المصعد (الأنود) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:electrochemistry:v1` — **an electrochemistry** · FR : Électrochimie · AR : الكيمياء الكهربائية
- `gen:electrode:v1` — **an electrode** · FR : Électrode · AR : القطب الكهربائي
- `gen:anode:v1` — **an anode** · FR : Anode · AR : المصعد (الأنود)

### Questions (acceptées / modèle / hint)
- **q1.** Is the electrochemistry normal?
  - accepté : yes ; yes it is ; the electrochemistry is normal ; it is normal
  - modèle : Yes, the electrochemistry is normal.
- **q2.** Is the anode normal?
  - accepté : no ; no it is not ; no it isn't ; the anode is not normal ; it is not normal ; it isn't normal
  - modèle : No, the anode is not normal.
  - hint (si erreur) — FR : Attention : Anode n'est PAS normal(e).
- **q3.** Which one is not normal: the electrochemistry or the anode?
  - accepté : anode ; the anode
  - modèle : The anode is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the electrode normal?
  - accepté : yes ; yes it is ; the electrode is normal
  - modèle : Yes, the electrode is normal.
- **Tt2.** Is the electrochemistry normal?
  - accepté : no ; no it is not ; no it isn't ; the electrochemistry is not normal ; it is not normal
  - modèle : No, the electrochemistry is not normal.
  - hint (si erreur) — FR : Attention : Électrochimie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The electrochemistry … normal. (be)` → is · modèle : The electrochemistry is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Karim en deux phrases. Garde la négation. — amorces : The electrochemistry is… / The anode is not… — modèle : *The electrochemistry is normal. The anode is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Électrode normal(e), mais Électrochimie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The electrode is normal. The electrochemistry is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-024` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Électrochimie et Électrode à un patient.
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
4. **EN** “First, we check electrochemistry.”
   - FR : « D'abord, nous vérifions Électrochimie. »
   - AR : «أولًا، نتحقق من الكيمياء الكهربائية.»
5. **EN** “Then, we check electrode.”
   - FR : « Ensuite, nous vérifions Électrode. »
   - AR : «ثم نتحقق من القطب الكهربائي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:electrochemistry:v1` — **an electrochemistry** · FR : Électrochimie · AR : الكيمياء الكهربائية
- `gen:electrode:v1` — **an electrode** · FR : Électrode · AR : القطب الكهربائي
- `gen:anode:v1` — **an anode** · FR : Anode · AR : المصعد (الأنود)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : electrochemistry ; we check electrochemistry ; electrochemistry first ; the doctor checks electrochemistry first
  - modèle : First, the doctor checks electrochemistry.
- **q2.** What does the doctor check next?
  - accepté : electrode ; then we check electrode ; electrode next ; the doctor checks electrode next
  - modèle : Then, the doctor checks electrode.
- **q3.** Does the doctor check electrode before electrochemistry?
  - accepté : no ; no he does not ; no he doesn't ; he checks electrochemistry first
  - modèle : No, he checks electrochemistry first, then electrode.
  - hint (si erreur) — FR : Attention : Électrochimie vient D'ABORD, ensuite Électrode.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : anode ; anode first ; he checks anode first
  - modèle : First, the doctor checks anode.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check electrochemistry. Then, we check electrode.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Anode, ensuite Électrochimie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check anode. Then, we check electrochemistry.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-024` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Électrochimie / Électrode, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the electrochemistry.
   - FR : Le patient du lit 3 a Électrochimie.
   - AR : مريض السرير ٣ عنده الكيمياء الكهربائية.
4. **EN** The patient in bed 5 has a problem with the electrode.
   - FR : Le patient du lit 5 a Électrode.
   - AR : مريض السرير ٥ عنده القطب الكهربائي.
5. **EN** The patient in bed 5 does not have a problem with the electrochemistry.
   - FR : Le patient du lit 5 n'a pas de Électrochimie.
   - AR : مريض السرير ٥ ليس عنده الكيمياء الكهربائية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:electrochemistry:v1` — **an electrochemistry** · FR : Électrochimie · AR : الكيمياء الكهربائية
- `gen:electrode:v1` — **an electrode** · FR : Électrode · AR : القطب الكهربائي
- `gen:anode:v1` — **an anode** · FR : Anode · AR : المصعد (الأنود)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : electrochemistry ; an electrochemistry ; the patient with electrochemistry
  - modèle : The patient in bed 3 has an electrochemistry.
- **q2.** What does the patient in bed 5 have?
  - accepté : electrode ; a problem with the electrode
  - modèle : He has a problem with the electrode.
- **q3.** Does the patient in bed 5 have electrochemistry?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the electrochemistry ; he doesn't
  - modèle : No, he does not have a problem with the electrochemistry.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS électrochimie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : anode ; a problem with the anode
  - modèle : He has a problem with the anode.
- **Tt2.** Does the patient in bed 9 have electrode?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the electrode ; he doesn't
  - modèle : No, he does not have a problem with the electrode.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS électrode.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an electrochemistry and an electrode. (have)` → have · modèle : The patients have an electrochemistry and an electrode.
- `The patient in bed 5 … not have an electrochemistry. (do)` → does · modèle : The patient in bed 5 does not have an electrochemistry.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the electrochemistry. The patient in bed 5 has a problem with the electrode.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Anode, le lit 9 a Électrochimie et n'a pas Électrode.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the anode. The patient in bed 9 has a problem with the electrochemistry.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
