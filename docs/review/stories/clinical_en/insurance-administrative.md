# Relecture — Insurance & Administrative (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-016` — Le cas de Nadia : Assurance maladie / _Nadia: Health insurance_

**Objectif :** Comprendre un cas : assurance maladie présent, quote-part / participation absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Nadia talks to Lina, a medical student.
   - FR : Nadia parle avec Lina, étudiant·e en médecine.
   - AR : Nadia يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a health insurance.
   - FR : Elle a Assurance maladie.
   - AR : عنده التأمين الصحي.
4. **EN** She does not have a co-payment.
   - FR : Elle n'a pas de Quote-part / Participation.
   - AR : ليس عنده الدفعة المشتركة.
5. **EN** Lina asks, “Do you have a co-payment?”
   - FR : Lina demande : « Avez-vous Quote-part / Participation ? »
   - AR : يسأل Lina: «هل عندك الدفعة المشتركة؟»
6. **EN** Nadia says, “No, I do not.”
   - FR : Nadia répond : « Non. »
   - AR : يجيب Nadia: «لا.»
7. **EN** Lina writes “Health insurance” in the file.
   - FR : Lina écrit « Assurance maladie » dans le dossier.
   - AR : يكتب Lina «Assurance maladie» في الملف.

### Expressions ciblées
- `gen:health-insurance:v1` — **a health insurance** · FR : Assurance maladie · AR : التأمين الصحي
- `gen:co-payment:v1` — **a co-payment** · FR : Quote-part / Participation · AR : الدفعة المشتركة
- `gen:deductible:v1` — **a deductible** · FR : Franchise · AR : المبلغ القابل للخصم

### Questions (acceptées / modèle / hint)
- **q1.** Does Nadia have health insurance?
  - accepté : yes ; yes she does ; yes she has a health insurance ; she has a health insurance
  - modèle : Yes, she has a health insurance.
- **q2.** Does she have co-payment?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a co-payment ; she doesn't
  - modèle : No, she does not have a co-payment.
  - hint (si erreur) — FR : Attention : Nadia n'a PAS quote-part / participation.
- **q3.** Does she have health insurance or co-payment?
  - accepté : health insurance ; a health insurance ; she has a health insurance
  - modèle : She has a health insurance.
- **q4.** What does Nadia have?
  - accepté : health insurance ; a health insurance ; she has a health insurance
  - modèle : She has a health insurance.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have deductible?
  - accepté : yes ; yes he does ; he has a deductible
  - modèle : Yes, he has a deductible.
- **Tt2.** Does he have health insurance?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a health insurance ; he doesn't
  - modèle : No, he does not have a health insurance.
  - hint (si erreur) — FR : Attention : il n'a PAS assurance maladie.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a health insurance. (have)` → has · modèle : She has a health insurance.
- `They … not have a co-payment. (do)` → do · modèle : They do not have a co-payment.
- `I … a health insurance. (have)` → have · modèle : I have a health insurance.

### Productions
- **Reformulation :** Rapporte le cas de Nadia en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a health insurance. She does not have a co-payment.*
- **Transfert (contexte) :** Un autre patient a Franchise. Il n'a pas de Assurance maladie.
- Glossaire : Franchise : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a deductible. He does not have a health insurance.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-016` — Expliquer le plan à Sofiane / _Explaining the plan to Sofiane_

**Objectif :** Comprendre un médecin qui explique Assurance maladie et Quote-part / Participation à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient.
   - FR : Sofiane est un·e patient·e.
   - AR : Sofiane مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check health insurance.”
   - FR : « D'abord, nous vérifions Assurance maladie. »
   - AR : «أولًا، نتحقق من التأمين الصحي.»
5. **EN** “Then, we check co-payment.”
   - FR : « Ensuite, nous vérifions Quote-part / Participation. »
   - AR : «ثم نتحقق من الدفعة المشتركة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sofiane says, “Thank you. I understand now.”
   - FR : Sofiane dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sofiane: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:health-insurance:v1` — **a health insurance** · FR : Assurance maladie · AR : التأمين الصحي
- `gen:co-payment:v1` — **a co-payment** · FR : Quote-part / Participation · AR : الدفعة المشتركة
- `gen:deductible:v1` — **a deductible** · FR : Franchise · AR : المبلغ القابل للخصم

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : health insurance ; we check health insurance ; health insurance first ; the doctor checks health insurance first
  - modèle : First, the doctor checks health insurance.
- **q2.** What does the doctor check next?
  - accepté : co-payment ; then we check co-payment ; co-payment next ; the doctor checks co-payment next
  - modèle : Then, the doctor checks co-payment.
- **q3.** Does the doctor check co-payment before health insurance?
  - accepté : no ; no he does not ; no he doesn't ; he checks health insurance first
  - modèle : No, he checks health insurance first, then co-payment.
  - hint (si erreur) — FR : Attention : Assurance maladie vient D'ABORD, ensuite Quote-part / Participation.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sofiane ; the patient ; the patient (sofiane)
  - modèle : Sofiane understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : deductible ; deductible first ; he checks deductible first
  - modèle : First, the doctor checks deductible.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check health insurance. Then, we check co-payment.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Franchise, ensuite Assurance maladie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check deductible. Then, we check health insurance.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-016` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Assurance maladie / Quote-part / Participation, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a health insurance.
   - FR : Le patient du lit 3 a Assurance maladie.
   - AR : مريض السرير ٣ عنده التأمين الصحي.
4. **EN** The patient in bed 5 has a co-payment.
   - FR : Le patient du lit 5 a Quote-part / Participation.
   - AR : مريض السرير ٥ عنده الدفعة المشتركة.
5. **EN** The patient in bed 5 does not have a health insurance.
   - FR : Le patient du lit 5 n'a pas de Assurance maladie.
   - AR : مريض السرير ٥ ليس عنده التأمين الصحي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:health-insurance:v1` — **a health insurance** · FR : Assurance maladie · AR : التأمين الصحي
- `gen:co-payment:v1` — **a co-payment** · FR : Quote-part / Participation · AR : الدفعة المشتركة
- `gen:deductible:v1` — **a deductible** · FR : Franchise · AR : المبلغ القابل للخصم

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : health insurance ; a health insurance ; the patient with health insurance
  - modèle : The patient in bed 3 has a health insurance.
- **q2.** What does the patient in bed 5 have?
  - accepté : co-payment ; a co-payment ; he has a co-payment
  - modèle : He has a co-payment.
- **q3.** Does the patient in bed 5 have health insurance?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a health insurance ; he doesn't
  - modèle : No, he does not have a health insurance.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS assurance maladie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : deductible ; a deductible ; he has a deductible
  - modèle : He has a deductible.
- **Tt2.** Does the patient in bed 9 have co-payment?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a co-payment ; he doesn't
  - modèle : No, he does not have a co-payment.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS quote-part / participation.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a health insurance and a co-payment. (have)` → have · modèle : The patients have a health insurance and a co-payment.
- `The patient in bed 5 … not have a health insurance. (do)` → does · modèle : The patient in bed 5 does not have a health insurance.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a health insurance. The patient in bed 5 has a co-payment.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Franchise, le lit 9 a Assurance maladie et n'a pas Quote-part / Participation.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a deductible. The patient in bed 9 has a health insurance.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
