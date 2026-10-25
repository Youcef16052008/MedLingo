# Relecture — Pharmacologie (termino)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `termino-case-021` — Le cas de Sara : Dose anormal(e) / _Sara: the dose is not normal_

**Objectif :** Comprendre des résultats : Pharmacologie normal(e), Dose anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The pharmacology is normal.”
   - FR : Le médecin dit : « Pharmacologie est normal(e). »
   - AR : يقول الطبيب: «علم الأدوية طبيعي/طبيعية.»
5. **EN** “But the dose is not normal.”
   - FR : « Mais Dose n'est pas normal(e). »
   - AR : «لكن الجرعة غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the dose normal?”
   - FR : Amine demande : « Dose est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الجرعة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:pharmacology:v1` — **a pharmacology** · FR : Pharmacologie · AR : علم الأدوية
- `gen:drug:v1` — **a drug** · FR : Médicament (Drogue) · AR : الدواء
- `gen:dose:v1` — **a dose** · FR : Dose · AR : الجرعة

### Questions (acceptées / modèle / hint)
- **q1.** Is the pharmacology normal?
  - accepté : yes ; yes it is ; the pharmacology is normal ; it is normal
  - modèle : Yes, the pharmacology is normal.
- **q2.** Is the dose normal?
  - accepté : no ; no it is not ; no it isn't ; the dose is not normal ; it is not normal ; it isn't normal
  - modèle : No, the dose is not normal.
  - hint (si erreur) — FR : Attention : Dose n'est PAS normal(e).
- **q3.** Which one is not normal: the pharmacology or the dose?
  - accepté : dose ; the dose
  - modèle : The dose is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the drug normal?
  - accepté : yes ; yes it is ; the drug is normal
  - modèle : Yes, the drug is normal.
- **Tt2.** Is the pharmacology normal?
  - accepté : no ; no it is not ; no it isn't ; the pharmacology is not normal ; it is not normal
  - modèle : No, the pharmacology is not normal.
  - hint (si erreur) — FR : Attention : Pharmacologie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The pharmacology … normal. (be)` → is · modèle : The pharmacology is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The pharmacology is… / The dose is not… — modèle : *The pharmacology is normal. The dose is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Médicament (Drogue) normal(e), mais Pharmacologie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The drug is normal. The pharmacology is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `termino-explain-021` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Pharmacologie et Médicament (Drogue) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient.
   - FR : Leila est un·e patient·e.
   - AR : Leila مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check pharmacology.”
   - FR : « D'abord, nous vérifions Pharmacologie. »
   - AR : «أولًا، نتحقق من علم الأدوية.»
5. **EN** “Then, we check drug.”
   - FR : « Ensuite, nous vérifions Médicament (Drogue). »
   - AR : «ثم نتحقق من الدواء.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:pharmacology:v1` — **a pharmacology** · FR : Pharmacologie · AR : علم الأدوية
- `gen:drug:v1` — **a drug** · FR : Médicament (Drogue) · AR : الدواء
- `gen:dose:v1` — **a dose** · FR : Dose · AR : الجرعة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : pharmacology ; we check pharmacology ; pharmacology first ; the doctor checks pharmacology first
  - modèle : First, the doctor checks pharmacology.
- **q2.** What does the doctor check next?
  - accepté : drug ; then we check drug ; drug next ; the doctor checks drug next
  - modèle : Then, the doctor checks drug.
- **q3.** Does the doctor check drug before pharmacology?
  - accepté : no ; no he does not ; no he doesn't ; he checks pharmacology first
  - modèle : No, he checks pharmacology first, then drug.
  - hint (si erreur) — FR : Attention : Pharmacologie vient D'ABORD, ensuite Médicament (Drogue).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : dose ; dose first ; he checks dose first
  - modèle : First, the doctor checks dose.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check pharmacology. Then, we check drug.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Dose, ensuite Pharmacologie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check dose. Then, we check pharmacology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `termino-handover-021` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Pharmacologie / Médicament (Drogue), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the pharmacology.
   - FR : Le patient du lit 3 a Pharmacologie.
   - AR : مريض السرير ٣ عنده علم الأدوية.
4. **EN** The patient in bed 5 has a problem with the drug.
   - FR : Le patient du lit 5 a Médicament (Drogue).
   - AR : مريض السرير ٥ عنده الدواء.
5. **EN** The patient in bed 5 does not have a problem with the pharmacology.
   - FR : Le patient du lit 5 n'a pas de Pharmacologie.
   - AR : مريض السرير ٥ ليس عنده علم الأدوية.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:pharmacology:v1` — **a pharmacology** · FR : Pharmacologie · AR : علم الأدوية
- `gen:drug:v1` — **a drug** · FR : Médicament (Drogue) · AR : الدواء
- `gen:dose:v1` — **a dose** · FR : Dose · AR : الجرعة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : pharmacology ; a pharmacology ; the patient with pharmacology
  - modèle : The patient in bed 3 has a pharmacology.
- **q2.** What does the patient in bed 5 have?
  - accepté : drug ; a problem with the drug
  - modèle : He has a problem with the drug.
- **q3.** Does the patient in bed 5 have pharmacology?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the pharmacology ; he doesn't
  - modèle : No, he does not have a problem with the pharmacology.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS pharmacologie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : dose ; a problem with the dose
  - modèle : He has a problem with the dose.
- **Tt2.** Does the patient in bed 9 have drug?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the drug ; he doesn't
  - modèle : No, he does not have a problem with the drug.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS médicament (drogue).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a pharmacology and a drug. (have)` → have · modèle : The patients have a pharmacology and a drug.
- `The patient in bed 5 … not have a pharmacology. (do)` → does · modèle : The patient in bed 5 does not have a pharmacology.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the pharmacology. The patient in bed 5 has a problem with the drug.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Dose, le lit 9 a Pharmacologie et n'a pas Médicament (Drogue).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the dose. The patient in bed 9 has a problem with the pharmacology.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
