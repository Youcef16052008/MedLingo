# Relecture — Pathologie Respiratoire (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-008` — Le cas de Bilal : Emphysème anormal(e) / _Bilal: the emphysema is not normal_

**Objectif :** Comprendre des résultats : Pneumonie (pathologique) normal(e), Emphysème anormal(e).
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
4. **EN** The doctor says, “The pneumonia is normal.”
   - FR : Le médecin dit : « Pneumonie (pathologique) est normal(e). »
   - AR : يقول الطبيب: «الالتهاب الرئوي (مرضي) طبيعي/طبيعية.»
5. **EN** “But the emphysema is not normal.”
   - FR : « Mais Emphysème n'est pas normal(e). »
   - AR : «لكن النفاخ الرئوي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the emphysema normal?”
   - FR : Lina demande : « Emphysème est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل النفاخ الرئوي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:pneumonia:v1` — **a pneumonia** · FR : Pneumonie (pathologique) · AR : الالتهاب الرئوي (مرضي)
- `gen:lobar-pneumonia:v1` — **a lobar pneumonia** · FR : Pneumonie lobaire (stades) · AR : الالتهاب الرئوي الفصي (مراحله)
- `gen:emphysema:v1` — **an emphysema** · FR : Emphysème · AR : النفاخ الرئوي

### Questions (acceptées / modèle / hint)
- **q1.** Is the pneumonia normal?
  - accepté : yes ; yes it is ; the pneumonia is normal ; it is normal
  - modèle : Yes, the pneumonia is normal.
- **q2.** Is the emphysema normal?
  - accepté : no ; no it is not ; no it isn't ; the emphysema is not normal ; it is not normal ; it isn't normal
  - modèle : No, the emphysema is not normal.
  - hint (si erreur) — FR : Attention : Emphysème n'est PAS normal(e).
- **q3.** Which one is not normal: the pneumonia or the emphysema?
  - accepté : emphysema ; the emphysema
  - modèle : The emphysema is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the lobar pneumonia normal?
  - accepté : yes ; yes it is ; the lobar pneumonia is normal
  - modèle : Yes, the lobar pneumonia is normal.
- **Tt2.** Is the pneumonia normal?
  - accepté : no ; no it is not ; no it isn't ; the pneumonia is not normal ; it is not normal
  - modèle : No, the pneumonia is not normal.
  - hint (si erreur) — FR : Attention : Pneumonie (pathologique) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The pneumonia … normal. (be)` → is · modèle : The pneumonia is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The pneumonia is… / The emphysema is not… — modèle : *The pneumonia is normal. The emphysema is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Pneumonie lobaire (stades) normal(e), mais Pneumonie (pathologique) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The lobar pneumonia is normal. The pneumonia is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Pneumonie (pathologique) et Pneumonie lobaire (stades) à un patient.
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
4. **EN** “First, we check pneumonia.”
   - FR : « D'abord, nous vérifions Pneumonie (pathologique). »
   - AR : «أولًا، نتحقق من الالتهاب الرئوي (مرضي).»
5. **EN** “Then, we check lobar pneumonia.”
   - FR : « Ensuite, nous vérifions Pneumonie lobaire (stades). »
   - AR : «ثم نتحقق من الالتهاب الرئوي الفصي (مراحله).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:pneumonia:v1` — **a pneumonia** · FR : Pneumonie (pathologique) · AR : الالتهاب الرئوي (مرضي)
- `gen:lobar-pneumonia:v1` — **a lobar pneumonia** · FR : Pneumonie lobaire (stades) · AR : الالتهاب الرئوي الفصي (مراحله)
- `gen:emphysema:v1` — **an emphysema** · FR : Emphysème · AR : النفاخ الرئوي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : pneumonia ; we check pneumonia ; pneumonia first ; the doctor checks pneumonia first
  - modèle : First, the doctor checks pneumonia.
- **q2.** What does the doctor check next?
  - accepté : lobar pneumonia ; then we check lobar pneumonia ; lobar pneumonia next ; the doctor checks lobar pneumonia next
  - modèle : Then, the doctor checks lobar pneumonia.
- **q3.** Does the doctor check lobar pneumonia before pneumonia?
  - accepté : no ; no he does not ; no he doesn't ; he checks pneumonia first
  - modèle : No, he checks pneumonia first, then lobar pneumonia.
  - hint (si erreur) — FR : Attention : Pneumonie (pathologique) vient D'ABORD, ensuite Pneumonie lobaire (stades).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : emphysema ; emphysema first ; he checks emphysema first
  - modèle : First, the doctor checks emphysema.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check pneumonia. Then, we check lobar pneumonia.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Emphysème, ensuite Pneumonie (pathologique), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check emphysema. Then, we check pneumonia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Pneumonie (pathologique) / Pneumonie lobaire (stades), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the pneumonia.
   - FR : Le patient du lit 3 a Pneumonie (pathologique).
   - AR : مريض السرير ٣ عنده الالتهاب الرئوي (مرضي).
4. **EN** The patient in bed 5 has a problem with the lobar pneumonia.
   - FR : Le patient du lit 5 a Pneumonie lobaire (stades).
   - AR : مريض السرير ٥ عنده الالتهاب الرئوي الفصي (مراحله).
5. **EN** The patient in bed 5 does not have a problem with the pneumonia.
   - FR : Le patient du lit 5 n'a pas de Pneumonie (pathologique).
   - AR : مريض السرير ٥ ليس عنده الالتهاب الرئوي (مرضي).
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:pneumonia:v1` — **a pneumonia** · FR : Pneumonie (pathologique) · AR : الالتهاب الرئوي (مرضي)
- `gen:lobar-pneumonia:v1` — **a lobar pneumonia** · FR : Pneumonie lobaire (stades) · AR : الالتهاب الرئوي الفصي (مراحله)
- `gen:emphysema:v1` — **an emphysema** · FR : Emphysème · AR : النفاخ الرئوي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : pneumonia ; a pneumonia ; the patient with pneumonia
  - modèle : The patient in bed 3 has a pneumonia.
- **q2.** What does the patient in bed 5 have?
  - accepté : lobar pneumonia ; a problem with the lobar pneumonia
  - modèle : He has a problem with the lobar pneumonia.
- **q3.** Does the patient in bed 5 have pneumonia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the pneumonia ; he doesn't
  - modèle : No, he does not have a problem with the pneumonia.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS pneumonie (pathologique) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : emphysema ; a problem with the emphysema
  - modèle : He has a problem with the emphysema.
- **Tt2.** Does the patient in bed 9 have lobar pneumonia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the lobar pneumonia ; he doesn't
  - modèle : No, he does not have a problem with the lobar pneumonia.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS pneumonie lobaire (stades).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a pneumonia and a lobar pneumonia. (have)` → have · modèle : The patients have a pneumonia and a lobar pneumonia.
- `The patient in bed 5 … not have a pneumonia. (do)` → does · modèle : The patient in bed 5 does not have a pneumonia.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the pneumonia. The patient in bed 5 has a problem with the lobar pneumonia.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Emphysème, le lit 9 a Pneumonie (pathologique) et n'a pas Pneumonie lobaire (stades).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the emphysema. The patient in bed 9 has a problem with the pneumonia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
