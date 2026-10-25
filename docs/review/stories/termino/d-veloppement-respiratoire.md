# Relecture — Développement Respiratoire (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-005` — Le cas de Youssef : Sillon laryngotrachéal / _Youssef: Laryngotracheal groove_

**Objectif :** Comprendre un cas : sillon laryngotrachéal présent, syndrome de détresse respiratoire (sdr) absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Youssef talks to Amine, a medical student.
   - FR : Youssef parle avec Amine, étudiant·e en médecine.
   - AR : Youssef يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has laryngotracheal groove.
   - FR : Il a Sillon laryngotrachéal.
   - AR : عنده الأخدود الحنجري الرغامي.
4. **EN** He does not have respiratory distress syndrome.
   - FR : Il n'a pas de Syndrome de détresse respiratoire (SDR).
   - AR : ليس عنده متلازمة الضائقة التنفسية.
5. **EN** Amine asks, “Do you have respiratory distress syndrome?”
   - FR : Amine demande : « Avez-vous Syndrome de détresse respiratoire (SDR) ? »
   - AR : يسأل Amine: «هل عندك متلازمة الضائقة التنفسية؟»
6. **EN** Youssef says, “No, I do not.”
   - FR : Youssef répond : « Non. »
   - AR : يجيب Youssef: «لا.»
7. **EN** Amine writes “Laryngotracheal groove” in the file.
   - FR : Amine écrit « Sillon laryngotrachéal » dans le dossier.
   - AR : يكتب Amine «Sillon laryngotrachéal» في الملف.

### Expressions ciblées
- `gen:laryngotracheal-groove:v1` — **laryngotracheal groove** · FR : Sillon laryngotrachéal · AR : الأخدود الحنجري الرغامي
- `gen:respiratory-distress-syndrome:v1` — **respiratory distress syndrome** · FR : Syndrome de détresse respiratoire (SDR) · AR : متلازمة الضائقة التنفسية
- `gen:lung-bud:v1` — **a lung bud** · FR : Bourgeon pulmonaire · AR : برعم الرئة

### Questions (acceptées / modèle / hint)
- **q1.** Does Youssef have laryngotracheal groove?
  - accepté : yes ; yes he does ; yes he has laryngotracheal groove ; he has laryngotracheal groove
  - modèle : Yes, he has laryngotracheal groove.
- **q2.** Does he have respiratory distress syndrome?
  - accepté : no ; no he does not ; no he doesn't ; he does not have respiratory distress syndrome ; he doesn't
  - modèle : No, he does not have respiratory distress syndrome.
  - hint (si erreur) — FR : Attention : Youssef n'a PAS syndrome de détresse respiratoire (sdr).
- **q3.** Does he have laryngotracheal groove or respiratory distress syndrome?
  - accepté : laryngotracheal groove ; he has laryngotracheal groove
  - modèle : He has laryngotracheal groove.
- **q4.** What does Youssef have?
  - accepté : laryngotracheal groove ; he has laryngotracheal groove
  - modèle : He has laryngotracheal groove.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have lung bud?
  - accepté : yes ; yes he does ; he has a lung bud
  - modèle : Yes, he has a lung bud.
- **Tt2.** Does he have laryngotracheal groove?
  - accepté : no ; no he does not ; no he doesn't ; he does not have laryngotracheal groove ; he doesn't
  - modèle : No, he does not have laryngotracheal groove.
  - hint (si erreur) — FR : Attention : il n'a PAS sillon laryngotrachéal.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … laryngotracheal groove. (have)` → has · modèle : He has laryngotracheal groove.
- `They … not have respiratory distress syndrome. (do)` → do · modèle : They do not have respiratory distress syndrome.
- `I … laryngotracheal groove. (have)` → have · modèle : I have laryngotracheal groove.

### Productions
- **Reformulation :** Rapporte le cas de Youssef en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has laryngotracheal groove. He does not have respiratory distress syndrome.*
- **Transfert (contexte) :** Un autre patient a Bourgeon pulmonaire. Il n'a pas de Sillon laryngotrachéal.
- Glossaire : Bourgeon pulmonaire : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a lung bud. He does not have laryngotracheal groove.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Bourgeon pulmonaire et Sillon laryngotrachéal à un patient.
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
4. **EN** “First, we check lung bud.”
   - FR : « D'abord, nous vérifions Bourgeon pulmonaire. »
   - AR : «أولًا، نتحقق من برعم الرئة.»
5. **EN** “Then, we check laryngotracheal groove.”
   - FR : « Ensuite, nous vérifions Sillon laryngotrachéal. »
   - AR : «ثم نتحقق من الأخدود الحنجري الرغامي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:lung-bud:v1` — **a lung bud** · FR : Bourgeon pulmonaire · AR : برعم الرئة
- `gen:laryngotracheal-groove:v1` — **laryngotracheal groove** · FR : Sillon laryngotrachéal · AR : الأخدود الحنجري الرغامي
- `gen:pseudoglandular-stage:v1` — **a pseudoglandular stage** · FR : Stade pseudoglandulaire · AR : المرحلة الكاذبة الغدية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : lung bud ; we check lung bud ; lung bud first ; the doctor checks lung bud first
  - modèle : First, the doctor checks lung bud.
- **q2.** What does the doctor check next?
  - accepté : laryngotracheal groove ; then we check laryngotracheal groove ; laryngotracheal groove next ; the doctor checks laryngotracheal groove next
  - modèle : Then, the doctor checks laryngotracheal groove.
- **q3.** Does the doctor check laryngotracheal groove before lung bud?
  - accepté : no ; no he does not ; no he doesn't ; he checks lung bud first
  - modèle : No, he checks lung bud first, then laryngotracheal groove.
  - hint (si erreur) — FR : Attention : Bourgeon pulmonaire vient D'ABORD, ensuite Sillon laryngotrachéal.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : pseudoglandular stage ; pseudoglandular stage first ; he checks pseudoglandular stage first
  - modèle : First, the doctor checks pseudoglandular stage.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check lung bud. Then, we check laryngotracheal groove.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Stade pseudoglandulaire, ensuite Bourgeon pulmonaire, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check pseudoglandular stage. Then, we check lung bud.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Sillon laryngotrachéal / Syndrome de détresse respiratoire (SDR), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has laryngotracheal groove.
   - FR : Le patient du lit 3 a Sillon laryngotrachéal.
   - AR : مريض السرير ٣ عنده الأخدود الحنجري الرغامي.
4. **EN** The patient in bed 5 has respiratory distress syndrome.
   - FR : Le patient du lit 5 a Syndrome de détresse respiratoire (SDR).
   - AR : مريض السرير ٥ عنده متلازمة الضائقة التنفسية.
5. **EN** The patient in bed 5 does not have laryngotracheal groove.
   - FR : Le patient du lit 5 n'a pas de Sillon laryngotrachéal.
   - AR : مريض السرير ٥ ليس عنده الأخدود الحنجري الرغامي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:laryngotracheal-groove:v1` — **laryngotracheal groove** · FR : Sillon laryngotrachéal · AR : الأخدود الحنجري الرغامي
- `gen:respiratory-distress-syndrome:v1` — **respiratory distress syndrome** · FR : Syndrome de détresse respiratoire (SDR) · AR : متلازمة الضائقة التنفسية
- `gen:lung-bud:v1` — **a lung bud** · FR : Bourgeon pulmonaire · AR : برعم الرئة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : laryngotracheal groove ; the patient with laryngotracheal groove
  - modèle : The patient in bed 3 has laryngotracheal groove.
- **q2.** What does the patient in bed 5 have?
  - accepté : respiratory distress syndrome
  - modèle : He has respiratory distress syndrome.
- **q3.** Does the patient in bed 5 have laryngotracheal groove?
  - accepté : no ; no he does not ; no he doesn't ; he does not have laryngotracheal groove ; he doesn't
  - modèle : No, he does not have laryngotracheal groove.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS sillon laryngotrachéal — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : lung bud ; a lung bud
  - modèle : He has a lung bud.
- **Tt2.** Does the patient in bed 9 have respiratory distress syndrome?
  - accepté : no ; no he does not ; no he doesn't ; he does not have respiratory distress syndrome ; he doesn't
  - modèle : No, he does not have respiratory distress syndrome.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS syndrome de détresse respiratoire (sdr).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … laryngotracheal groove and respiratory distress syndrome. (have)` → have · modèle : The patients have laryngotracheal groove and respiratory distress syndrome.
- `The patient in bed 5 … not have laryngotracheal groove. (do)` → does · modèle : The patient in bed 5 does not have laryngotracheal groove.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has laryngotracheal groove. The patient in bed 5 has respiratory distress syndrome.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Bourgeon pulmonaire, le lit 9 a Sillon laryngotrachéal et n'a pas Syndrome de détresse respiratoire (SDR).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a lung bud. The patient in bed 9 has laryngotracheal groove.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
