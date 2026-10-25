# Relecture — Glucides et Métabolisme (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-010` — Le cas de Omar : Néoglucogenèse anormal(e) / _Omar: the gluconeogenesis is not normal_

**Objectif :** Comprendre des résultats : Glucose normal(e), Néoglucogenèse anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The glucose is normal.”
   - FR : Le médecin dit : « Glucose est normal(e). »
   - AR : يقول الطبيب: «الغلوكوز / سكر العنب طبيعي/طبيعية.»
5. **EN** “But the gluconeogenesis is not normal.”
   - FR : « Mais Néoglucogenèse n'est pas normal(e). »
   - AR : «لكن استحداث السكر / توليد الغلوكوز الحديث غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the gluconeogenesis normal?”
   - FR : Lina demande : « Néoglucogenèse est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل استحداث السكر / توليد الغلوكوز الحديث طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:glucose:v1` — **a glucose** · FR : Glucose · AR : الغلوكوز / سكر العنب
- `gen:glycolysis:v1` — **glycolysis** · FR : Glycolyse · AR : تحلل الغلوكوز / مسار إمبدن مايرهوف
- `gen:gluconeogenesis:v1` — **gluconeogenesis** · FR : Néoglucogenèse · AR : استحداث السكر / توليد الغلوكوز الحديث

### Questions (acceptées / modèle / hint)
- **q1.** Is the glucose normal?
  - accepté : yes ; yes it is ; the glucose is normal ; it is normal
  - modèle : Yes, the glucose is normal.
- **q2.** Is the gluconeogenesis normal?
  - accepté : no ; no it is not ; no it isn't ; the gluconeogenesis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the gluconeogenesis is not normal.
  - hint (si erreur) — FR : Attention : Néoglucogenèse n'est PAS normal(e).
- **q3.** Which one is not normal: the glucose or the gluconeogenesis?
  - accepté : gluconeogenesis ; the gluconeogenesis
  - modèle : The gluconeogenesis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the glycolysis normal?
  - accepté : yes ; yes it is ; the glycolysis is normal
  - modèle : Yes, the glycolysis is normal.
- **Tt2.** Is the glucose normal?
  - accepté : no ; no it is not ; no it isn't ; the glucose is not normal ; it is not normal
  - modèle : No, the glucose is not normal.
  - hint (si erreur) — FR : Attention : Glucose n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The glucose … normal. (be)` → is · modèle : The glucose is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The glucose is… / The gluconeogenesis is not… — modèle : *The glucose is normal. The gluconeogenesis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Glycolyse normal(e), mais Glucose anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The glycolysis is normal. The glucose is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-010` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Glucose et Glycolyse à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient.
   - FR : Karim est un·e patient·e.
   - AR : Karim مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check glucose.”
   - FR : « D'abord, nous vérifions Glucose. »
   - AR : «أولًا، نتحقق من الغلوكوز / سكر العنب.»
5. **EN** “Then, we check glycolysis.”
   - FR : « Ensuite, nous vérifions Glycolyse. »
   - AR : «ثم نتحقق من تحلل الغلوكوز / مسار إمبدن مايرهوف.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:glucose:v1` — **a glucose** · FR : Glucose · AR : الغلوكوز / سكر العنب
- `gen:glycolysis:v1` — **glycolysis** · FR : Glycolyse · AR : تحلل الغلوكوز / مسار إمبدن مايرهوف
- `gen:gluconeogenesis:v1` — **gluconeogenesis** · FR : Néoglucogenèse · AR : استحداث السكر / توليد الغلوكوز الحديث

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : glucose ; we check glucose ; glucose first ; the doctor checks glucose first
  - modèle : First, the doctor checks glucose.
- **q2.** What does the doctor check next?
  - accepté : glycolysis ; then we check glycolysis ; glycolysis next ; the doctor checks glycolysis next
  - modèle : Then, the doctor checks glycolysis.
- **q3.** Does the doctor check glycolysis before glucose?
  - accepté : no ; no he does not ; no he doesn't ; he checks glucose first
  - modèle : No, he checks glucose first, then glycolysis.
  - hint (si erreur) — FR : Attention : Glucose vient D'ABORD, ensuite Glycolyse.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : gluconeogenesis ; gluconeogenesis first ; he checks gluconeogenesis first
  - modèle : First, the doctor checks gluconeogenesis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check glucose. Then, we check glycolysis.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Néoglucogenèse, ensuite Glucose, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check gluconeogenesis. Then, we check glucose.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-010` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Glucose / Glycolyse, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the glucose.
   - FR : Le patient du lit 3 a Glucose.
   - AR : مريض السرير ٣ عنده الغلوكوز / سكر العنب.
4. **EN** The patient in bed 5 has a problem with the glycolysis.
   - FR : Le patient du lit 5 a Glycolyse.
   - AR : مريض السرير ٥ عنده تحلل الغلوكوز / مسار إمبدن مايرهوف.
5. **EN** The patient in bed 5 does not have a problem with the glucose.
   - FR : Le patient du lit 5 n'a pas de Glucose.
   - AR : مريض السرير ٥ ليس عنده الغلوكوز / سكر العنب.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:glucose:v1` — **a glucose** · FR : Glucose · AR : الغلوكوز / سكر العنب
- `gen:glycolysis:v1` — **glycolysis** · FR : Glycolyse · AR : تحلل الغلوكوز / مسار إمبدن مايرهوف
- `gen:gluconeogenesis:v1` — **gluconeogenesis** · FR : Néoglucogenèse · AR : استحداث السكر / توليد الغلوكوز الحديث

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : glucose ; a glucose ; the patient with glucose
  - modèle : The patient in bed 3 has a glucose.
- **q2.** What does the patient in bed 5 have?
  - accepté : glycolysis ; a problem with the glycolysis
  - modèle : He has a problem with the glycolysis.
- **q3.** Does the patient in bed 5 have glucose?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the glucose ; he doesn't
  - modèle : No, he does not have a problem with the glucose.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS glucose — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : gluconeogenesis ; a problem with the gluconeogenesis
  - modèle : He has a problem with the gluconeogenesis.
- **Tt2.** Does the patient in bed 9 have glycolysis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the glycolysis ; he doesn't
  - modèle : No, he does not have a problem with the glycolysis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS glycolyse.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a glucose and glycolysis. (have)` → have · modèle : The patients have a glucose and glycolysis.
- `The patient in bed 5 … not have a glucose. (do)` → does · modèle : The patient in bed 5 does not have a glucose.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the glucose. The patient in bed 5 has a problem with the glycolysis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Néoglucogenèse, le lit 9 a Glucose et n'a pas Glycolyse.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the gluconeogenesis. The patient in bed 9 has a problem with the glucose.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
