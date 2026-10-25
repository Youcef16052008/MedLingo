# Relecture — Termes généraux (anat)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anat-case-010` — Le cas de Omar : Plan coronal (frontal) anormal(e) / _Omar: the coronal plane is not normal_

**Objectif :** Comprendre des résultats : Anatomie normal(e), Plan coronal (frontal) anormal(e).
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
4. **EN** The doctor says, “The anatomy is normal.”
   - FR : Le médecin dit : « Anatomie est normal(e). »
   - AR : يقول الطبيب: «علم التشريح طبيعي/طبيعية.»
5. **EN** “But the coronal plane is not normal.”
   - FR : « Mais Plan coronal (frontal) n'est pas normal(e). »
   - AR : «لكن المستوى الإكليلي غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the coronal plane normal?”
   - FR : Lina demande : « Plan coronal (frontal) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل المستوى الإكليلي طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:anatomy:v1` — **an anatomy** · FR : Anatomie · AR : علم التشريح
- `gen:sagittal-plane:v1` — **a sagittal plane** · FR : Plan sagittal · AR : المستوى السهمي
- `gen:coronal-plane:v1` — **a coronal plane** · FR : Plan coronal (frontal) · AR : المستوى الإكليلي

### Questions (acceptées / modèle / hint)
- **q1.** Is the anatomy normal?
  - accepté : yes ; yes it is ; the anatomy is normal ; it is normal
  - modèle : Yes, the anatomy is normal.
- **q2.** Is the coronal plane normal?
  - accepté : no ; no it is not ; no it isn't ; the coronal plane is not normal ; it is not normal ; it isn't normal
  - modèle : No, the coronal plane is not normal.
  - hint (si erreur) — FR : Attention : Plan coronal (frontal) n'est PAS normal(e).
- **q3.** Which one is not normal: the anatomy or the coronal plane?
  - accepté : coronal plane ; the coronal plane
  - modèle : The coronal plane is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the sagittal plane normal?
  - accepté : yes ; yes it is ; the sagittal plane is normal
  - modèle : Yes, the sagittal plane is normal.
- **Tt2.** Is the anatomy normal?
  - accepté : no ; no it is not ; no it isn't ; the anatomy is not normal ; it is not normal
  - modèle : No, the anatomy is not normal.
  - hint (si erreur) — FR : Attention : Anatomie n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The anatomy … normal. (be)` → is · modèle : The anatomy is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Omar en deux phrases. Garde la négation. — amorces : The anatomy is… / The coronal plane is not… — modèle : *The anatomy is normal. The coronal plane is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Plan sagittal normal(e), mais Anatomie anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The sagittal plane is normal. The anatomy is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anat-explain-010` — Expliquer le plan à Karim / _Explaining the plan to Karim_

**Objectif :** Comprendre un médecin qui explique Anatomie et Plan sagittal à un patient.
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
4. **EN** “First, we check anatomy.”
   - FR : « D'abord, nous vérifions Anatomie. »
   - AR : «أولًا، نتحقق من علم التشريح.»
5. **EN** “Then, we check sagittal plane.”
   - FR : « Ensuite, nous vérifions Plan sagittal. »
   - AR : «ثم نتحقق من المستوى السهمي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Karim says, “Thank you. I understand now.”
   - FR : Karim dit : « Merci. Je comprends maintenant. »
   - AR : يقول Karim: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:anatomy:v1` — **an anatomy** · FR : Anatomie · AR : علم التشريح
- `gen:sagittal-plane:v1` — **a sagittal plane** · FR : Plan sagittal · AR : المستوى السهمي
- `gen:coronal-plane:v1` — **a coronal plane** · FR : Plan coronal (frontal) · AR : المستوى الإكليلي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : anatomy ; we check anatomy ; anatomy first ; the doctor checks anatomy first
  - modèle : First, the doctor checks anatomy.
- **q2.** What does the doctor check next?
  - accepté : sagittal plane ; then we check sagittal plane ; sagittal plane next ; the doctor checks sagittal plane next
  - modèle : Then, the doctor checks sagittal plane.
- **q3.** Does the doctor check sagittal plane before anatomy?
  - accepté : no ; no he does not ; no he doesn't ; he checks anatomy first
  - modèle : No, he checks anatomy first, then sagittal plane.
  - hint (si erreur) — FR : Attention : Anatomie vient D'ABORD, ensuite Plan sagittal.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : karim ; the patient ; the patient (karim)
  - modèle : Karim understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : coronal plane ; coronal plane first ; he checks coronal plane first
  - modèle : First, the doctor checks coronal plane.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check anatomy. Then, we check sagittal plane.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Plan coronal (frontal), ensuite Anatomie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check coronal plane. Then, we check anatomy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anat-handover-010` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Anatomie / Plan sagittal, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the anatomy.
   - FR : Le patient du lit 3 a Anatomie.
   - AR : مريض السرير ٣ عنده علم التشريح.
4. **EN** The patient in bed 5 has a problem with the sagittal plane.
   - FR : Le patient du lit 5 a Plan sagittal.
   - AR : مريض السرير ٥ عنده المستوى السهمي.
5. **EN** The patient in bed 5 does not have a problem with the anatomy.
   - FR : Le patient du lit 5 n'a pas de Anatomie.
   - AR : مريض السرير ٥ ليس عنده علم التشريح.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:anatomy:v1` — **an anatomy** · FR : Anatomie · AR : علم التشريح
- `gen:sagittal-plane:v1` — **a sagittal plane** · FR : Plan sagittal · AR : المستوى السهمي
- `gen:coronal-plane:v1` — **a coronal plane** · FR : Plan coronal (frontal) · AR : المستوى الإكليلي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : anatomy ; an anatomy ; the patient with anatomy
  - modèle : The patient in bed 3 has an anatomy.
- **q2.** What does the patient in bed 5 have?
  - accepté : sagittal plane ; a problem with the sagittal plane
  - modèle : He has a problem with the sagittal plane.
- **q3.** Does the patient in bed 5 have anatomy?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the anatomy ; he doesn't
  - modèle : No, he does not have a problem with the anatomy.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS anatomie — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : coronal plane ; a problem with the coronal plane
  - modèle : He has a problem with the coronal plane.
- **Tt2.** Does the patient in bed 9 have sagittal plane?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the sagittal plane ; he doesn't
  - modèle : No, he does not have a problem with the sagittal plane.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS plan sagittal.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an anatomy and a sagittal plane. (have)` → have · modèle : The patients have an anatomy and a sagittal plane.
- `The patient in bed 5 … not have an anatomy. (do)` → does · modèle : The patient in bed 5 does not have an anatomy.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the anatomy. The patient in bed 5 has a problem with the sagittal plane.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Plan coronal (frontal), le lit 9 a Anatomie et n'a pas Plan sagittal.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the coronal plane. The patient in bed 9 has a problem with the anatomy.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
