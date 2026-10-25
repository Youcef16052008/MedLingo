# Relecture — Système Immunitaire (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-029` — Le cas de Meriem : Anticorps (Immunoglobuline) anormal(e) / _Meriem: the antibody is not normal_

**Objectif :** Comprendre des résultats : Système immunitaire normal(e), Anticorps (Immunoglobuline) anormal(e).
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
4. **EN** The doctor says, “The immune system is normal.”
   - FR : Le médecin dit : « Système immunitaire est normal(e). »
   - AR : يقول الطبيب: «الجهاز المناعي طبيعي/طبيعية.»
5. **EN** “But the antibody is not normal.”
   - FR : « Mais Anticorps (Immunoglobuline) n'est pas normal(e). »
   - AR : «لكن الجسم المضاد غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the antibody normal?”
   - FR : Amine demande : « Anticorps (Immunoglobuline) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الجسم المضاد طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:immune-system:v1` — **an immune system** · FR : Système immunitaire · AR : الجهاز المناعي
- `gen:antigen:v1` — **an antigen** · FR : Antigène · AR : المستضد
- `gen:antibody:v1` — **an antibody** · FR : Anticorps (Immunoglobuline) · AR : الجسم المضاد

### Questions (acceptées / modèle / hint)
- **q1.** Is the immune system normal?
  - accepté : yes ; yes it is ; the immune system is normal ; it is normal
  - modèle : Yes, the immune system is normal.
- **q2.** Is the antibody normal?
  - accepté : no ; no it is not ; no it isn't ; the antibody is not normal ; it is not normal ; it isn't normal
  - modèle : No, the antibody is not normal.
  - hint (si erreur) — FR : Attention : Anticorps (Immunoglobuline) n'est PAS normal(e).
- **q3.** Which one is not normal: the immune system or the antibody?
  - accepté : antibody ; the antibody
  - modèle : The antibody is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the antigen normal?
  - accepté : yes ; yes it is ; the antigen is normal
  - modèle : Yes, the antigen is normal.
- **Tt2.** Is the immune system normal?
  - accepté : no ; no it is not ; no it isn't ; the immune system is not normal ; it is not normal
  - modèle : No, the immune system is not normal.
  - hint (si erreur) — FR : Attention : Système immunitaire n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The immune system … normal. (be)` → is · modèle : The immune system is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The immune system is… / The antibody is not… — modèle : *The immune system is normal. The antibody is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Antigène normal(e), mais Système immunitaire anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The antigen is normal. The immune system is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-029` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Système immunitaire et Antigène à un patient.
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
4. **EN** “First, we check immune system.”
   - FR : « D'abord, nous vérifions Système immunitaire. »
   - AR : «أولًا، نتحقق من الجهاز المناعي.»
5. **EN** “Then, we check antigen.”
   - FR : « Ensuite, nous vérifions Antigène. »
   - AR : «ثم نتحقق من المستضد.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:immune-system:v1` — **an immune system** · FR : Système immunitaire · AR : الجهاز المناعي
- `gen:antigen:v1` — **an antigen** · FR : Antigène · AR : المستضد
- `gen:antibody:v1` — **an antibody** · FR : Anticorps (Immunoglobuline) · AR : الجسم المضاد

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : immune system ; we check immune system ; immune system first ; the doctor checks immune system first
  - modèle : First, the doctor checks immune system.
- **q2.** What does the doctor check next?
  - accepté : antigen ; then we check antigen ; antigen next ; the doctor checks antigen next
  - modèle : Then, the doctor checks antigen.
- **q3.** Does the doctor check antigen before immune system?
  - accepté : no ; no he does not ; no he doesn't ; he checks immune system first
  - modèle : No, he checks immune system first, then antigen.
  - hint (si erreur) — FR : Attention : Système immunitaire vient D'ABORD, ensuite Antigène.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : antibody ; antibody first ; he checks antibody first
  - modèle : First, the doctor checks antibody.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check immune system. Then, we check antigen.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Anticorps (Immunoglobuline), ensuite Système immunitaire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check antibody. Then, we check immune system.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-029` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Système immunitaire / Antigène, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the immune system.
   - FR : Le patient du lit 3 a Système immunitaire.
   - AR : مريض السرير ٣ عنده الجهاز المناعي.
4. **EN** The patient in bed 5 has a problem with the antigen.
   - FR : Le patient du lit 5 a Antigène.
   - AR : مريض السرير ٥ عنده المستضد.
5. **EN** The patient in bed 5 does not have a problem with the immune system.
   - FR : Le patient du lit 5 n'a pas de Système immunitaire.
   - AR : مريض السرير ٥ ليس عنده الجهاز المناعي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:immune-system:v1` — **an immune system** · FR : Système immunitaire · AR : الجهاز المناعي
- `gen:antigen:v1` — **an antigen** · FR : Antigène · AR : المستضد
- `gen:antibody:v1` — **an antibody** · FR : Anticorps (Immunoglobuline) · AR : الجسم المضاد

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : immune system ; an immune system ; the patient with immune system
  - modèle : The patient in bed 3 has an immune system.
- **q2.** What does the patient in bed 5 have?
  - accepté : antigen ; a problem with the antigen
  - modèle : He has a problem with the antigen.
- **q3.** Does the patient in bed 5 have immune system?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the immune system ; he doesn't
  - modèle : No, he does not have a problem with the immune system.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS système immunitaire — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : antibody ; a problem with the antibody
  - modèle : He has a problem with the antibody.
- **Tt2.** Does the patient in bed 9 have antigen?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the antigen ; he doesn't
  - modèle : No, he does not have a problem with the antigen.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS antigène.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an immune system and an antigen. (have)` → have · modèle : The patients have an immune system and an antigen.
- `The patient in bed 5 … not have an immune system. (do)` → does · modèle : The patient in bed 5 does not have an immune system.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the immune system. The patient in bed 5 has a problem with the antigen.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Anticorps (Immunoglobuline), le lit 9 a Système immunitaire et n'a pas Antigène.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the antibody. The patient in bed 9 has a problem with the immune system.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
