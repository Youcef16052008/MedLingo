# Relecture — Médicaments Respiratoires (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-008` — Le cas de Bilal : Corticostéroïde inhalé (CSI) anormal(e) / _Bilal: the inhaled corticosteroid is not normal_

**Objectif :** Comprendre des résultats : Bronchodilatateur normal(e), Corticostéroïde inhalé (CSI) anormal(e).
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
4. **EN** The doctor says, “The bronchodilator is normal.”
   - FR : Le médecin dit : « Bronchodilatateur est normal(e). »
   - AR : يقول الطبيب: «موسع القصبات الهوائية طبيعي/طبيعية.»
5. **EN** “But the inhaled corticosteroid is not normal.”
   - FR : « Mais Corticostéroïde inhalé (CSI) n'est pas normal(e). »
   - AR : «لكن الكورتيكوستيروئيد بالاستنشاق غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the inhaled corticosteroid normal?”
   - FR : Lina demande : « Corticostéroïde inhalé (CSI) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الكورتيكوستيروئيد بالاستنشاق طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:bronchodilator:v1` — **a bronchodilator** · FR : Bronchodilatateur · AR : موسع القصبات الهوائية
- `gen:beta-2-agonist:v1` — **a beta-2 agonist** · FR : Agoniste bêta-2 · AR : ناهض بيتا-2
- `gen:inhaled-corticosteroid:v1` — **an inhaled corticosteroid** · FR : Corticostéroïde inhalé (CSI) · AR : الكورتيكوستيروئيد بالاستنشاق

### Questions (acceptées / modèle / hint)
- **q1.** Is the bronchodilator normal?
  - accepté : yes ; yes it is ; the bronchodilator is normal ; it is normal
  - modèle : Yes, the bronchodilator is normal.
- **q2.** Is the inhaled corticosteroid normal?
  - accepté : no ; no it is not ; no it isn't ; the inhaled corticosteroid is not normal ; it is not normal ; it isn't normal
  - modèle : No, the inhaled corticosteroid is not normal.
  - hint (si erreur) — FR : Attention : Corticostéroïde inhalé (CSI) n'est PAS normal(e).
- **q3.** Which one is not normal: the bronchodilator or the inhaled corticosteroid?
  - accepté : inhaled corticosteroid ; the inhaled corticosteroid
  - modèle : The inhaled corticosteroid is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the beta-2 agonist normal?
  - accepté : yes ; yes it is ; the beta-2 agonist is normal
  - modèle : Yes, the beta-2 agonist is normal.
- **Tt2.** Is the bronchodilator normal?
  - accepté : no ; no it is not ; no it isn't ; the bronchodilator is not normal ; it is not normal
  - modèle : No, the bronchodilator is not normal.
  - hint (si erreur) — FR : Attention : Bronchodilatateur n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The bronchodilator … normal. (be)` → is · modèle : The bronchodilator is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The bronchodilator is… / The inhaled corticosteroid is not… — modèle : *The bronchodilator is normal. The inhaled corticosteroid is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Agoniste bêta-2 normal(e), mais Bronchodilatateur anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The beta-2 agonist is normal. The bronchodilator is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Bronchodilatateur et Agoniste bêta-2 à un patient.
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
4. **EN** “First, we check bronchodilator.”
   - FR : « D'abord, nous vérifions Bronchodilatateur. »
   - AR : «أولًا، نتحقق من موسع القصبات الهوائية.»
5. **EN** “Then, we check beta-2 agonist.”
   - FR : « Ensuite, nous vérifions Agoniste bêta-2. »
   - AR : «ثم نتحقق من ناهض بيتا-2.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:bronchodilator:v1` — **a bronchodilator** · FR : Bronchodilatateur · AR : موسع القصبات الهوائية
- `gen:beta-2-agonist:v1` — **a beta-2 agonist** · FR : Agoniste bêta-2 · AR : ناهض بيتا-2
- `gen:inhaled-corticosteroid:v1` — **an inhaled corticosteroid** · FR : Corticostéroïde inhalé (CSI) · AR : الكورتيكوستيروئيد بالاستنشاق

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : bronchodilator ; we check bronchodilator ; bronchodilator first ; the doctor checks bronchodilator first
  - modèle : First, the doctor checks bronchodilator.
- **q2.** What does the doctor check next?
  - accepté : beta-2 agonist ; then we check beta-2 agonist ; beta-2 agonist next ; the doctor checks beta-2 agonist next
  - modèle : Then, the doctor checks beta-2 agonist.
- **q3.** Does the doctor check beta-2 agonist before bronchodilator?
  - accepté : no ; no he does not ; no he doesn't ; he checks bronchodilator first
  - modèle : No, he checks bronchodilator first, then beta-2 agonist.
  - hint (si erreur) — FR : Attention : Bronchodilatateur vient D'ABORD, ensuite Agoniste bêta-2.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : inhaled corticosteroid ; inhaled corticosteroid first ; he checks inhaled corticosteroid first
  - modèle : First, the doctor checks inhaled corticosteroid.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check bronchodilator. Then, we check beta-2 agonist.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Corticostéroïde inhalé (CSI), ensuite Bronchodilatateur, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check inhaled corticosteroid. Then, we check bronchodilator.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Bronchodilatateur / Agoniste bêta-2, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the bronchodilator.
   - FR : Le patient du lit 3 a Bronchodilatateur.
   - AR : مريض السرير ٣ عنده موسع القصبات الهوائية.
4. **EN** The patient in bed 5 has a problem with the beta-2 agonist.
   - FR : Le patient du lit 5 a Agoniste bêta-2.
   - AR : مريض السرير ٥ عنده ناهض بيتا-2.
5. **EN** The patient in bed 5 does not have a problem with the bronchodilator.
   - FR : Le patient du lit 5 n'a pas de Bronchodilatateur.
   - AR : مريض السرير ٥ ليس عنده موسع القصبات الهوائية.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:bronchodilator:v1` — **a bronchodilator** · FR : Bronchodilatateur · AR : موسع القصبات الهوائية
- `gen:beta-2-agonist:v1` — **a beta-2 agonist** · FR : Agoniste bêta-2 · AR : ناهض بيتا-2
- `gen:inhaled-corticosteroid:v1` — **an inhaled corticosteroid** · FR : Corticostéroïde inhalé (CSI) · AR : الكورتيكوستيروئيد بالاستنشاق

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : bronchodilator ; a bronchodilator ; the patient with bronchodilator
  - modèle : The patient in bed 3 has a bronchodilator.
- **q2.** What does the patient in bed 5 have?
  - accepté : beta-2 agonist ; a problem with the beta-2 agonist
  - modèle : He has a problem with the beta-2 agonist.
- **q3.** Does the patient in bed 5 have bronchodilator?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the bronchodilator ; he doesn't
  - modèle : No, he does not have a problem with the bronchodilator.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS bronchodilatateur — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : inhaled corticosteroid ; a problem with the inhaled corticosteroid
  - modèle : He has a problem with the inhaled corticosteroid.
- **Tt2.** Does the patient in bed 9 have beta-2 agonist?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the beta-2 agonist ; he doesn't
  - modèle : No, he does not have a problem with the beta-2 agonist.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS agoniste bêta-2.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a bronchodilator and a beta-2 agonist. (have)` → have · modèle : The patients have a bronchodilator and a beta-2 agonist.
- `The patient in bed 5 … not have a bronchodilator. (do)` → does · modèle : The patient in bed 5 does not have a bronchodilator.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the bronchodilator. The patient in bed 5 has a problem with the beta-2 agonist.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Corticostéroïde inhalé (CSI), le lit 9 a Bronchodilatateur et n'a pas Agoniste bêta-2.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the inhaled corticosteroid. The patient in bed 9 has a problem with the bronchodilator.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
