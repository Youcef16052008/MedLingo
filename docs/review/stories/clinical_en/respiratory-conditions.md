# Relecture — Respiratory Conditions (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-034` — Le cas de Hana : Apnée du sommeil / _Hana: Sleep apnea_

**Objectif :** Comprendre un cas : apnée du sommeil présent, sifflement respiratoire absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient at the clinic.
   - FR : Hana est un·e patient·e à la clinique.
   - AR : Hana مريض/مريضة في العيادة.
2. **EN** Hana talks to Lina, a medical student.
   - FR : Hana parle avec Lina, étudiant·e en médecine.
   - AR : Hana يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has sleep apnea.
   - FR : Elle a Apnée du sommeil.
   - AR : عنده توقف التنفس أثناء النوم.
4. **EN** She does not have wheezing.
   - FR : Elle n'a pas de Sifflement respiratoire.
   - AR : ليس عنده الصفير التنفسي.
5. **EN** Lina asks, “Do you have wheezing?”
   - FR : Lina demande : « Avez-vous Sifflement respiratoire ? »
   - AR : يسأل Lina: «هل عندك الصفير التنفسي؟»
6. **EN** Hana says, “No, I do not.”
   - FR : Hana répond : « Non. »
   - AR : يجيب Hana: «لا.»
7. **EN** Lina writes “Sleep apnea” in the file.
   - FR : Lina écrit « Apnée du sommeil » dans le dossier.
   - AR : يكتب Lina «Apnée du sommeil» في الملف.

### Expressions ciblées
- `gen:sleep-apnea:v1` — **sleep apnea** · FR : Apnée du sommeil · AR : توقف التنفس أثناء النوم
- `gen:wheezing:v1` — **wheezing** · FR : Sifflement respiratoire · AR : الصفير التنفسي
- `gen:copd:v1` — **a COPD** · FR : BPCO (Bronchopneumopathie) · AR : مرض الانسداد الرئوي المزمن

### Questions (acceptées / modèle / hint)
- **q1.** Does Hana have sleep apnea?
  - accepté : yes ; yes she does ; yes she has sleep apnea ; she has sleep apnea
  - modèle : Yes, she has sleep apnea.
- **q2.** Does she have wheezing?
  - accepté : no ; no she does not ; no she doesn't ; she does not have wheezing ; she doesn't
  - modèle : No, she does not have wheezing.
  - hint (si erreur) — FR : Attention : Hana n'a PAS sifflement respiratoire.
- **q3.** Does she have sleep apnea or wheezing?
  - accepté : sleep apnea ; she has sleep apnea
  - modèle : She has sleep apnea.
- **q4.** What does Hana have?
  - accepté : sleep apnea ; she has sleep apnea
  - modèle : She has sleep apnea.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have COPD?
  - accepté : yes ; yes he does ; he has a COPD
  - modèle : Yes, he has a COPD.
- **Tt2.** Does he have sleep apnea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have sleep apnea ; he doesn't
  - modèle : No, he does not have sleep apnea.
  - hint (si erreur) — FR : Attention : il n'a PAS apnée du sommeil.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … sleep apnea. (have)` → has · modèle : She has sleep apnea.
- `They … not have wheezing. (do)` → do · modèle : They do not have wheezing.
- `I … sleep apnea. (have)` → have · modèle : I have sleep apnea.

### Productions
- **Reformulation :** Rapporte le cas de Hana en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has sleep apnea. She does not have wheezing.*
- **Transfert (contexte) :** Un autre patient a BPCO (Bronchopneumopathie). Il n'a pas de Apnée du sommeil.
- Glossaire : BPCO (Bronchopneumopathie) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a COPD. He does not have sleep apnea.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-034` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique BPCO (Bronchopneumopathie) et Emphysème à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check COPD.”
   - FR : « D'abord, nous vérifions BPCO (Bronchopneumopathie). »
   - AR : «أولًا، نتحقق من مرض الانسداد الرئوي المزمن.»
5. **EN** “Then, we check emphysema.”
   - FR : « Ensuite, nous vérifions Emphysème. »
   - AR : «ثم نتحقق من انتفاخ الرئة.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:copd:v1` — **a COPD** · FR : BPCO (Bronchopneumopathie) · AR : مرض الانسداد الرئوي المزمن
- `gen:emphysema:v1` — **a emphysema** · FR : Emphysème · AR : انتفاخ الرئة
- `gen:pulmonary-fibrosis:v1` — **pulmonary fibrosis** · FR : Fibrose pulmonaire · AR : التليف الرئوي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : COPD ; we check COPD ; COPD first ; the doctor checks COPD first
  - modèle : First, the doctor checks COPD.
- **q2.** What does the doctor check next?
  - accepté : emphysema ; then we check emphysema ; emphysema next ; the doctor checks emphysema next
  - modèle : Then, the doctor checks emphysema.
- **q3.** Does the doctor check emphysema before COPD?
  - accepté : no ; no he does not ; no he doesn't ; he checks COPD first
  - modèle : No, he checks COPD first, then emphysema.
  - hint (si erreur) — FR : Attention : BPCO (Bronchopneumopathie) vient D'ABORD, ensuite Emphysème.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : pulmonary fibrosis ; pulmonary fibrosis first ; he checks pulmonary fibrosis first
  - modèle : First, the doctor checks pulmonary fibrosis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check COPD. Then, we check emphysema.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fibrose pulmonaire, ensuite BPCO (Bronchopneumopathie), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check pulmonary fibrosis. Then, we check COPD.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-034` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Apnée du sommeil / Sifflement respiratoire, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has sleep apnea.
   - FR : Le patient du lit 3 a Apnée du sommeil.
   - AR : مريض السرير ٣ عنده توقف التنفس أثناء النوم.
4. **EN** The patient in bed 5 has wheezing.
   - FR : Le patient du lit 5 a Sifflement respiratoire.
   - AR : مريض السرير ٥ عنده الصفير التنفسي.
5. **EN** The patient in bed 5 does not have sleep apnea.
   - FR : Le patient du lit 5 n'a pas de Apnée du sommeil.
   - AR : مريض السرير ٥ ليس عنده توقف التنفس أثناء النوم.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:sleep-apnea:v1` — **sleep apnea** · FR : Apnée du sommeil · AR : توقف التنفس أثناء النوم
- `gen:wheezing:v1` — **wheezing** · FR : Sifflement respiratoire · AR : الصفير التنفسي
- `gen:copd:v1` — **a COPD** · FR : BPCO (Bronchopneumopathie) · AR : مرض الانسداد الرئوي المزمن

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : sleep apnea ; the patient with sleep apnea
  - modèle : The patient in bed 3 has sleep apnea.
- **q2.** What does the patient in bed 5 have?
  - accepté : wheezing ; he has wheezing
  - modèle : He has wheezing.
- **q3.** Does the patient in bed 5 have sleep apnea?
  - accepté : no ; no he does not ; no he doesn't ; he does not have sleep apnea ; he doesn't
  - modèle : No, he does not have sleep apnea.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS apnée du sommeil — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : COPD ; a COPD ; he has a COPD
  - modèle : He has a COPD.
- **Tt2.** Does the patient in bed 9 have wheezing?
  - accepté : no ; no he does not ; no he doesn't ; he does not have wheezing ; he doesn't
  - modèle : No, he does not have wheezing.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS sifflement respiratoire.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … sleep apnea and wheezing. (have)` → have · modèle : The patients have sleep apnea and wheezing.
- `The patient in bed 5 … not have sleep apnea. (do)` → does · modèle : The patient in bed 5 does not have sleep apnea.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has sleep apnea. The patient in bed 5 has wheezing.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a BPCO (Bronchopneumopathie), le lit 9 a Apnée du sommeil et n'a pas Sifflement respiratoire.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a COPD. The patient in bed 9 has sleep apnea.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
