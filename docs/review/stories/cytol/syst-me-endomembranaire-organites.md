# Relecture — Système Endomembranaire & Organites (cytol)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `cytol-case-008` — Le cas de Bilal : Lysosome et Autophagie anormal(e) / _Bilal: the lysosome and autophagy is not normal_

**Objectif :** Comprendre des résultats : Réticulum Endoplasmique (Rugueux et Lisse) normal(e), Lysosome et Autophagie anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The endoplasmic reticulum is normal.”
   - FR : Le médecin dit : « Réticulum Endoplasmique (Rugueux et Lisse) est normal(e). »
   - AR : يقول الطبيب: «الشبكة الإندوبلازمية (الخشنة والملساء) طبيعي/طبيعية.»
5. **EN** “But the lysosome and autophagy is not normal.”
   - FR : « Mais Lysosome et Autophagie n'est pas normal(e). »
   - AR : «لكن الجسيم الحال (اللايزوزوم) والالتهام الذاتي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the lysosome and autophagy normal?”
   - FR : Lina demande : « Lysosome et Autophagie est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الجسيم الحال (اللايزوزوم) والالتهام الذاتي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:endoplasmic-reticulum:v1` — **an endoplasmic reticulum** · FR : Réticulum Endoplasmique (Rugueux et Lisse) · AR : الشبكة الإندوبلازمية (الخشنة والملساء)
- `gen:golgi-apparatus:v1` — **golgi apparatus** · FR : Appareil de Golgi · AR : جهاز غولجي
- `gen:lysosome-and-autophagy:v1` — **a lysosome and autophagy** · FR : Lysosome et Autophagie · AR : الجسيم الحال (اللايزوزوم) والالتهام الذاتي

### Questions (acceptées / modèle / hint)
- **q1.** Is the endoplasmic reticulum normal?
  - accepté : yes ; yes it is ; the endoplasmic reticulum is normal ; it is normal
  - modèle : Yes, the endoplasmic reticulum is normal.
- **q2.** Is the lysosome and autophagy normal?
  - accepté : no ; no it is not ; no it isn't ; the lysosome and autophagy is not normal ; it is not normal ; it isn't normal
  - modèle : No, the lysosome and autophagy is not normal.
  - hint (si erreur) — FR : Attention : Lysosome et Autophagie n'est PAS normal(e).
- **q3.** Which one is not normal: the endoplasmic reticulum or the lysosome and autophagy?
  - accepté : lysosome and autophagy ; the lysosome and autophagy
  - modèle : The lysosome and autophagy is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the golgi apparatus normal?
  - accepté : yes ; yes it is ; the golgi apparatus is normal
  - modèle : Yes, the golgi apparatus is normal.
- **Tt2.** Is the endoplasmic reticulum normal?
  - accepté : no ; no it is not ; no it isn't ; the endoplasmic reticulum is not normal ; it is not normal
  - modèle : No, the endoplasmic reticulum is not normal.
  - hint (si erreur) — FR : Attention : Réticulum Endoplasmique (Rugueux et Lisse) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The endoplasmic reticulum … normal. (be)` → is · modèle : The endoplasmic reticulum is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The endoplasmic reticulum is… / The lysosome and autophagy is not… — modèle : *The endoplasmic reticulum is normal. The lysosome and autophagy is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Appareil de Golgi normal(e), mais Réticulum Endoplasmique (Rugueux et Lisse) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The golgi apparatus is normal. The endoplasmic reticulum is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `cytol-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Réticulum Endoplasmique (Rugueux et Lisse) et Appareil de Golgi à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient.
   - FR : Omar est un·e patient·e.
   - AR : Omar مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check endoplasmic reticulum.”
   - FR : « D'abord, nous vérifions Réticulum Endoplasmique (Rugueux et Lisse). »
   - AR : «أولًا، نتحقق من الشبكة الإندوبلازمية (الخشنة والملساء).»
5. **EN** “Then, we check golgi apparatus.”
   - FR : « Ensuite, nous vérifions Appareil de Golgi. »
   - AR : «ثم نتحقق من جهاز غولجي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:endoplasmic-reticulum:v1` — **an endoplasmic reticulum** · FR : Réticulum Endoplasmique (Rugueux et Lisse) · AR : الشبكة الإندوبلازمية (الخشنة والملساء)
- `gen:golgi-apparatus:v1` — **golgi apparatus** · FR : Appareil de Golgi · AR : جهاز غولجي
- `gen:lysosome-and-autophagy:v1` — **a lysosome and autophagy** · FR : Lysosome et Autophagie · AR : الجسيم الحال (اللايزوزوم) والالتهام الذاتي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : endoplasmic reticulum ; we check endoplasmic reticulum ; endoplasmic reticulum first ; the doctor checks endoplasmic reticulum first
  - modèle : First, the doctor checks endoplasmic reticulum.
- **q2.** What does the doctor check next?
  - accepté : golgi apparatus ; then we check golgi apparatus ; golgi apparatus next ; the doctor checks golgi apparatus next
  - modèle : Then, the doctor checks golgi apparatus.
- **q3.** Does the doctor check golgi apparatus before endoplasmic reticulum?
  - accepté : no ; no he does not ; no he doesn't ; he checks endoplasmic reticulum first
  - modèle : No, he checks endoplasmic reticulum first, then golgi apparatus.
  - hint (si erreur) — FR : Attention : Réticulum Endoplasmique (Rugueux et Lisse) vient D'ABORD, ensuite Appareil de Golgi.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : lysosome and autophagy ; lysosome and autophagy first ; he checks lysosome and autophagy first
  - modèle : First, the doctor checks lysosome and autophagy.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check endoplasmic reticulum. Then, we check golgi apparatus.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Lysosome et Autophagie, ensuite Réticulum Endoplasmique (Rugueux et Lisse), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check lysosome and autophagy. Then, we check endoplasmic reticulum.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `cytol-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Réticulum Endoplasmique (Rugueux et Lisse) / Appareil de Golgi, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the endoplasmic reticulum.
   - FR : Le patient du lit 3 a Réticulum Endoplasmique (Rugueux et Lisse).
   - AR : مريض السرير ٣ عنده الشبكة الإندوبلازمية (الخشنة والملساء).
4. **EN** The patient in bed 5 has a problem with the golgi apparatus.
   - FR : Le patient du lit 5 a Appareil de Golgi.
   - AR : مريض السرير ٥ عنده جهاز غولجي.
5. **EN** The patient in bed 5 does not have a problem with the endoplasmic reticulum.
   - FR : Le patient du lit 5 n'a pas de Réticulum Endoplasmique (Rugueux et Lisse).
   - AR : مريض السرير ٥ ليس عنده الشبكة الإندوبلازمية (الخشنة والملساء).
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:endoplasmic-reticulum:v1` — **an endoplasmic reticulum** · FR : Réticulum Endoplasmique (Rugueux et Lisse) · AR : الشبكة الإندوبلازمية (الخشنة والملساء)
- `gen:golgi-apparatus:v1` — **golgi apparatus** · FR : Appareil de Golgi · AR : جهاز غولجي
- `gen:lysosome-and-autophagy:v1` — **a lysosome and autophagy** · FR : Lysosome et Autophagie · AR : الجسيم الحال (اللايزوزوم) والالتهام الذاتي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : endoplasmic reticulum ; an endoplasmic reticulum ; the patient with endoplasmic reticulum
  - modèle : The patient in bed 3 has an endoplasmic reticulum.
- **q2.** What does the patient in bed 5 have?
  - accepté : golgi apparatus ; a problem with the golgi apparatus
  - modèle : He has a problem with the golgi apparatus.
- **q3.** Does the patient in bed 5 have endoplasmic reticulum?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the endoplasmic reticulum ; he doesn't
  - modèle : No, he does not have a problem with the endoplasmic reticulum.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS réticulum endoplasmique (rugueux et lisse) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : lysosome and autophagy ; a problem with the lysosome and autophagy
  - modèle : He has a problem with the lysosome and autophagy.
- **Tt2.** Does the patient in bed 9 have golgi apparatus?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the golgi apparatus ; he doesn't
  - modèle : No, he does not have a problem with the golgi apparatus.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS appareil de golgi.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an endoplasmic reticulum and golgi apparatus. (have)` → have · modèle : The patients have an endoplasmic reticulum and golgi apparatus.
- `The patient in bed 5 … not have an endoplasmic reticulum. (do)` → does · modèle : The patient in bed 5 does not have an endoplasmic reticulum.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the endoplasmic reticulum. The patient in bed 5 has a problem with the golgi apparatus.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Lysosome et Autophagie, le lit 9 a Réticulum Endoplasmique (Rugueux et Lisse) et n'a pas Appareil de Golgi.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the lysosome and autophagy. The patient in bed 9 has a problem with the endoplasmic reticulum.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
