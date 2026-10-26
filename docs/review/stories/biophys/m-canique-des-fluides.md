# Relecture — Mécanique des Fluides (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-005` — Le cas de Meriem : Hydrodynamique anormal(e) / _Meriem: the hydrodynamics is not normal_

**Objectif :** Comprendre des résultats : Fluide normal(e), Hydrodynamique anormal(e).
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
4. **EN** The doctor says, “The fluid is normal.”
   - FR : Le médecin dit : « Fluide est normal(e). »
   - AR : يقول الطبيب: «المائع طبيعي/طبيعية.»
5. **EN** “But the hydrodynamics is not normal.”
   - FR : « Mais Hydrodynamique n'est pas normal(e). »
   - AR : «لكن الديناميكا المائية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the hydrodynamics normal?”
   - FR : Amine demande : « Hydrodynamique est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الديناميكا المائية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:fluid:v1` — **a fluid** · FR : Fluide · AR : المائع
- `gen:hydrostatics:v1` — **hydrostatics** · FR : Hydrostatique · AR : السكونيات المائية
- `gen:hydrodynamics:v1` — **hydrodynamics** · FR : Hydrodynamique · AR : الديناميكا المائية

### Questions (acceptées / modèle / hint)
- **q1.** Is the fluid normal?
  - accepté : yes ; yes it is ; the fluid is normal ; it is normal
  - modèle : Yes, the fluid is normal.
- **q2.** Is the hydrodynamics normal?
  - accepté : no ; no it is not ; no it isn't ; the hydrodynamics is not normal ; it is not normal ; it isn't normal
  - modèle : No, the hydrodynamics is not normal.
  - hint (si erreur) — FR : Attention : Hydrodynamique n'est PAS normal(e).
- **q3.** Which one is not normal: the fluid or the hydrodynamics?
  - accepté : hydrodynamics ; the hydrodynamics
  - modèle : The hydrodynamics is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the hydrostatics normal?
  - accepté : yes ; yes it is ; the hydrostatics is normal
  - modèle : Yes, the hydrostatics is normal.
- **Tt2.** Is the fluid normal?
  - accepté : no ; no it is not ; no it isn't ; the fluid is not normal ; it is not normal
  - modèle : No, the fluid is not normal.
  - hint (si erreur) — FR : Attention : Fluide n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The fluid … normal. (be)` → is · modèle : The fluid is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The fluid is… / The hydrodynamics is not… — modèle : *The fluid is normal. The hydrodynamics is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Hydrostatique normal(e), mais Fluide anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The hydrostatics is normal. The fluid is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Fluide et Hydrostatique à un patient.
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
4. **EN** “First, we check fluid.”
   - FR : « D'abord, nous vérifions Fluide. »
   - AR : «أولًا، نتحقق من المائع.»
5. **EN** “Then, we check hydrostatics.”
   - FR : « Ensuite, nous vérifions Hydrostatique. »
   - AR : «ثم نتحقق من السكونيات المائية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:fluid:v1` — **a fluid** · FR : Fluide · AR : المائع
- `gen:hydrostatics:v1` — **hydrostatics** · FR : Hydrostatique · AR : السكونيات المائية
- `gen:hydrodynamics:v1` — **hydrodynamics** · FR : Hydrodynamique · AR : الديناميكا المائية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : fluid ; we check fluid ; fluid first ; the doctor checks fluid first
  - modèle : First, the doctor checks fluid.
- **q2.** What does the doctor check next?
  - accepté : hydrostatics ; then we check hydrostatics ; hydrostatics next ; the doctor checks hydrostatics next
  - modèle : Then, the doctor checks hydrostatics.
- **q3.** Does the doctor check hydrostatics before fluid?
  - accepté : no ; no he does not ; no he doesn't ; he checks fluid first
  - modèle : No, he checks fluid first, then hydrostatics.
  - hint (si erreur) — FR : Attention : Fluide vient D'ABORD, ensuite Hydrostatique.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : hydrodynamics ; hydrodynamics first ; he checks hydrodynamics first
  - modèle : First, the doctor checks hydrodynamics.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check fluid. Then, we check hydrostatics.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Hydrodynamique, ensuite Fluide, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check hydrodynamics. Then, we check fluid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Fluide / Hydrostatique, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the fluid.
   - FR : Le patient du lit 3 a Fluide.
   - AR : مريض السرير ٣ عنده المائع.
4. **EN** The patient in bed 5 has a problem with the hydrostatics.
   - FR : Le patient du lit 5 a Hydrostatique.
   - AR : مريض السرير ٥ عنده السكونيات المائية.
5. **EN** The patient in bed 5 does not have a problem with the fluid.
   - FR : Le patient du lit 5 n'a pas de Fluide.
   - AR : مريض السرير ٥ ليس عنده المائع.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:fluid:v1` — **a fluid** · FR : Fluide · AR : المائع
- `gen:hydrostatics:v1` — **hydrostatics** · FR : Hydrostatique · AR : السكونيات المائية
- `gen:hydrodynamics:v1` — **hydrodynamics** · FR : Hydrodynamique · AR : الديناميكا المائية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : fluid ; a fluid ; the patient with fluid
  - modèle : The patient in bed 3 has a fluid.
- **q2.** What does the patient in bed 5 have?
  - accepté : hydrostatics ; a problem with the hydrostatics
  - modèle : He has a problem with the hydrostatics.
- **q3.** Does the patient in bed 5 have fluid?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the fluid ; he doesn't
  - modèle : No, he does not have a problem with the fluid.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS fluide — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : hydrodynamics ; a problem with the hydrodynamics
  - modèle : He has a problem with the hydrodynamics.
- **Tt2.** Does the patient in bed 9 have hydrostatics?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the hydrostatics ; he doesn't
  - modèle : No, he does not have a problem with the hydrostatics.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS hydrostatique.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a fluid and hydrostatics. (have)` → have · modèle : The patients have a fluid and hydrostatics.
- `The patient in bed 5 … not have a fluid. (do)` → does · modèle : The patient in bed 5 does not have a fluid.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the fluid. The patient in bed 5 has a problem with the hydrostatics.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Hydrodynamique, le lit 9 a Fluide et n'a pas Hydrostatique.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the hydrodynamics. The patient in bed 9 has a problem with the fluid.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
