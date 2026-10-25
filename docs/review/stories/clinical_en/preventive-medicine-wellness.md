# Relecture — Preventive Medicine & Wellness (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-030` — Le cas de Amel : Médecine préventive / _Amel: Preventive medicine_

**Objectif :** Comprendre un cas : médecine préventive présent, bilan de santé annuel absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Amel talks to Lina, a medical student.
   - FR : Amel parle avec Lina, étudiant·e en médecine.
   - AR : Amel يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a preventive medicine.
   - FR : Elle a Médecine préventive.
   - AR : عنده الطب الوقائي.
4. **EN** She does not have a annual physical exam.
   - FR : Elle n'a pas de Bilan de santé annuel.
   - AR : ليس عنده الفحص الطبي السنوي.
5. **EN** Lina asks, “Do you have a annual physical exam?”
   - FR : Lina demande : « Avez-vous Bilan de santé annuel ? »
   - AR : يسأل Lina: «هل عندك الفحص الطبي السنوي؟»
6. **EN** Amel says, “No, I do not.”
   - FR : Amel répond : « Non. »
   - AR : يجيب Amel: «لا.»
7. **EN** Lina writes “Preventive medicine” in the file.
   - FR : Lina écrit « Médecine préventive » dans le dossier.
   - AR : يكتب Lina «Médecine préventive» في الملف.

### Expressions ciblées
- `gen:preventive-medicine:v1` — **a preventive medicine** · FR : Médecine préventive · AR : الطب الوقائي
- `gen:annual-physical-exam:v1` — **a annual physical exam** · FR : Bilan de santé annuel · AR : الفحص الطبي السنوي
- `gen:immunization:v1` — **a immunization** · FR : Vaccination (immunisation) · AR : التطعيم

### Questions (acceptées / modèle / hint)
- **q1.** Does Amel have preventive medicine?
  - accepté : yes ; yes she does ; yes she has a preventive medicine ; she has a preventive medicine
  - modèle : Yes, she has a preventive medicine.
- **q2.** Does she have annual physical exam?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a annual physical exam ; she doesn't
  - modèle : No, she does not have a annual physical exam.
  - hint (si erreur) — FR : Attention : Amel n'a PAS bilan de santé annuel.
- **q3.** Does she have preventive medicine or annual physical exam?
  - accepté : preventive medicine ; a preventive medicine ; she has a preventive medicine
  - modèle : She has a preventive medicine.
- **q4.** What does Amel have?
  - accepté : preventive medicine ; a preventive medicine ; she has a preventive medicine
  - modèle : She has a preventive medicine.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have immunization?
  - accepté : yes ; yes he does ; he has a immunization
  - modèle : Yes, he has a immunization.
- **Tt2.** Does he have preventive medicine?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a preventive medicine ; he doesn't
  - modèle : No, he does not have a preventive medicine.
  - hint (si erreur) — FR : Attention : il n'a PAS médecine préventive.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a preventive medicine. (have)` → has · modèle : She has a preventive medicine.
- `They … not have a annual physical exam. (do)` → do · modèle : They do not have a annual physical exam.
- `I … a preventive medicine. (have)` → have · modèle : I have a preventive medicine.

### Productions
- **Reformulation :** Rapporte le cas de Amel en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a preventive medicine. She does not have a annual physical exam.*
- **Transfert (contexte) :** Un autre patient a Vaccination (immunisation). Il n'a pas de Médecine préventive.
- Glossaire : Vaccination (immunisation) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a immunization. He does not have a preventive medicine.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-030` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Médecine préventive et Bilan de santé annuel à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient.
   - FR : Bilal est un·e patient·e.
   - AR : Bilal مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check preventive medicine.”
   - FR : « D'abord, nous vérifions Médecine préventive. »
   - AR : «أولًا، نتحقق من الطب الوقائي.»
5. **EN** “Then, we check annual physical exam.”
   - FR : « Ensuite, nous vérifions Bilan de santé annuel. »
   - AR : «ثم نتحقق من الفحص الطبي السنوي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:preventive-medicine:v1` — **a preventive medicine** · FR : Médecine préventive · AR : الطب الوقائي
- `gen:annual-physical-exam:v1` — **a annual physical exam** · FR : Bilan de santé annuel · AR : الفحص الطبي السنوي
- `gen:immunization:v1` — **a immunization** · FR : Vaccination (immunisation) · AR : التطعيم

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : preventive medicine ; we check preventive medicine ; preventive medicine first ; the doctor checks preventive medicine first
  - modèle : First, the doctor checks preventive medicine.
- **q2.** What does the doctor check next?
  - accepté : annual physical exam ; then we check annual physical exam ; annual physical exam next ; the doctor checks annual physical exam next
  - modèle : Then, the doctor checks annual physical exam.
- **q3.** Does the doctor check annual physical exam before preventive medicine?
  - accepté : no ; no he does not ; no he doesn't ; he checks preventive medicine first
  - modèle : No, he checks preventive medicine first, then annual physical exam.
  - hint (si erreur) — FR : Attention : Médecine préventive vient D'ABORD, ensuite Bilan de santé annuel.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : immunization ; immunization first ; he checks immunization first
  - modèle : First, the doctor checks immunization.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check preventive medicine. Then, we check annual physical exam.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Vaccination (immunisation), ensuite Médecine préventive, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check immunization. Then, we check preventive medicine.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-030` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Médecine préventive / Bilan de santé annuel, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a preventive medicine.
   - FR : Le patient du lit 3 a Médecine préventive.
   - AR : مريض السرير ٣ عنده الطب الوقائي.
4. **EN** The patient in bed 5 has a annual physical exam.
   - FR : Le patient du lit 5 a Bilan de santé annuel.
   - AR : مريض السرير ٥ عنده الفحص الطبي السنوي.
5. **EN** The patient in bed 5 does not have a preventive medicine.
   - FR : Le patient du lit 5 n'a pas de Médecine préventive.
   - AR : مريض السرير ٥ ليس عنده الطب الوقائي.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:preventive-medicine:v1` — **a preventive medicine** · FR : Médecine préventive · AR : الطب الوقائي
- `gen:annual-physical-exam:v1` — **a annual physical exam** · FR : Bilan de santé annuel · AR : الفحص الطبي السنوي
- `gen:immunization:v1` — **a immunization** · FR : Vaccination (immunisation) · AR : التطعيم

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : preventive medicine ; a preventive medicine ; the patient with preventive medicine
  - modèle : The patient in bed 3 has a preventive medicine.
- **q2.** What does the patient in bed 5 have?
  - accepté : annual physical exam ; a annual physical exam ; he has a annual physical exam
  - modèle : He has a annual physical exam.
- **q3.** Does the patient in bed 5 have preventive medicine?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a preventive medicine ; he doesn't
  - modèle : No, he does not have a preventive medicine.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS médecine préventive — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : immunization ; a immunization ; he has a immunization
  - modèle : He has a immunization.
- **Tt2.** Does the patient in bed 9 have annual physical exam?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a annual physical exam ; he doesn't
  - modèle : No, he does not have a annual physical exam.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS bilan de santé annuel.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a preventive medicine and a annual physical exam. (have)` → have · modèle : The patients have a preventive medicine and a annual physical exam.
- `The patient in bed 5 … not have a preventive medicine. (do)` → does · modèle : The patient in bed 5 does not have a preventive medicine.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a preventive medicine. The patient in bed 5 has a annual physical exam.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Vaccination (immunisation), le lit 9 a Médecine préventive et n'a pas Bilan de santé annuel.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a immunization. The patient in bed 9 has a preventive medicine.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
