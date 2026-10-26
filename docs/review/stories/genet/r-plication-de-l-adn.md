# Relecture — Réplication de l'ADN (genet)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `genet-case-009` — Le cas de Sara : Origine de réplication anormal(e) / _Sara: the origin of replication is not normal_

**Objectif :** Comprendre des résultats : Réplication de l'ADN normal(e), Origine de réplication anormal(e).
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
4. **EN** The doctor says, “The DNA replication is normal.”
   - FR : Le médecin dit : « Réplication de l'ADN est normal(e). »
   - AR : يقول الطبيب: «تضاعف DNA طبيعي/طبيعية.»
5. **EN** “But the origin of replication is not normal.”
   - FR : « Mais Origine de réplication n'est pas normal(e). »
   - AR : «لكن نقطة بداية التضاعف غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the origin of replication normal?”
   - FR : Amine demande : « Origine de réplication est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل نقطة بداية التضاعف طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:dna-replication:v1` — **a DNA replication** · FR : Réplication de l'ADN · AR : تضاعف DNA
- `gen:semi-conservative-replication:v1` — **a semi-conservative replication** · FR : Réplication semi-conservative · AR : التضاعف شبه المحافظ
- `gen:origin-of-replication:v1` — **an origin of replication** · FR : Origine de réplication · AR : نقطة بداية التضاعف

### Questions (acceptées / modèle / hint)
- **q1.** Is the DNA replication normal?
  - accepté : yes ; yes it is ; the DNA replication is normal ; it is normal
  - modèle : Yes, the DNA replication is normal.
- **q2.** Is the origin of replication normal?
  - accepté : no ; no it is not ; no it isn't ; the origin of replication is not normal ; it is not normal ; it isn't normal
  - modèle : No, the origin of replication is not normal.
  - hint (si erreur) — FR : Attention : Origine de réplication n'est PAS normal(e).
- **q3.** Which one is not normal: the DNA replication or the origin of replication?
  - accepté : origin of replication ; the origin of replication
  - modèle : The origin of replication is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the semi-conservative replication normal?
  - accepté : yes ; yes it is ; the semi-conservative replication is normal
  - modèle : Yes, the semi-conservative replication is normal.
- **Tt2.** Is the DNA replication normal?
  - accepté : no ; no it is not ; no it isn't ; the DNA replication is not normal ; it is not normal
  - modèle : No, the DNA replication is not normal.
  - hint (si erreur) — FR : Attention : Réplication de l'ADN n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The DNA replication … normal. (be)` → is · modèle : The DNA replication is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The DNA replication is… / The origin of replication is not… — modèle : *The DNA replication is normal. The origin of replication is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Réplication semi-conservative normal(e), mais Réplication de l'ADN anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The semi-conservative replication is normal. The DNA replication is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `genet-explain-009` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Réplication de l'ADN et Réplication semi-conservative à un patient.
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
4. **EN** “First, we check DNA replication.”
   - FR : « D'abord, nous vérifions Réplication de l'ADN. »
   - AR : «أولًا، نتحقق من تضاعف DNA.»
5. **EN** “Then, we check semi-conservative replication.”
   - FR : « Ensuite, nous vérifions Réplication semi-conservative. »
   - AR : «ثم نتحقق من التضاعف شبه المحافظ.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:dna-replication:v1` — **a DNA replication** · FR : Réplication de l'ADN · AR : تضاعف DNA
- `gen:semi-conservative-replication:v1` — **a semi-conservative replication** · FR : Réplication semi-conservative · AR : التضاعف شبه المحافظ
- `gen:origin-of-replication:v1` — **an origin of replication** · FR : Origine de réplication · AR : نقطة بداية التضاعف

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : DNA replication ; we check DNA replication ; DNA replication first ; the doctor checks DNA replication first
  - modèle : First, the doctor checks DNA replication.
- **q2.** What does the doctor check next?
  - accepté : semi-conservative replication ; then we check semi-conservative replication ; semi-conservative replication next ; the doctor checks semi-conservative replication next
  - modèle : Then, the doctor checks semi-conservative replication.
- **q3.** Does the doctor check semi-conservative replication before DNA replication?
  - accepté : no ; no he does not ; no he doesn't ; he checks DNA replication first
  - modèle : No, he checks DNA replication first, then semi-conservative replication.
  - hint (si erreur) — FR : Attention : Réplication de l'ADN vient D'ABORD, ensuite Réplication semi-conservative.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : origin of replication ; origin of replication first ; he checks origin of replication first
  - modèle : First, the doctor checks origin of replication.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check DNA replication. Then, we check semi-conservative replication.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Origine de réplication, ensuite Réplication de l'ADN, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check origin of replication. Then, we check DNA replication.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `genet-handover-009` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Réplication de l'ADN / Réplication semi-conservative, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the DNA replication.
   - FR : Le patient du lit 3 a Réplication de l'ADN.
   - AR : مريض السرير ٣ عنده تضاعف DNA.
4. **EN** The patient in bed 5 has a problem with the semi-conservative replication.
   - FR : Le patient du lit 5 a Réplication semi-conservative.
   - AR : مريض السرير ٥ عنده التضاعف شبه المحافظ.
5. **EN** The patient in bed 5 does not have a problem with the DNA replication.
   - FR : Le patient du lit 5 n'a pas de Réplication de l'ADN.
   - AR : مريض السرير ٥ ليس عنده تضاعف DNA.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:dna-replication:v1` — **a DNA replication** · FR : Réplication de l'ADN · AR : تضاعف DNA
- `gen:semi-conservative-replication:v1` — **a semi-conservative replication** · FR : Réplication semi-conservative · AR : التضاعف شبه المحافظ
- `gen:origin-of-replication:v1` — **an origin of replication** · FR : Origine de réplication · AR : نقطة بداية التضاعف

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : DNA replication ; a DNA replication ; the patient with DNA replication
  - modèle : The patient in bed 3 has a DNA replication.
- **q2.** What does the patient in bed 5 have?
  - accepté : semi-conservative replication ; a problem with the semi-conservative replication
  - modèle : He has a problem with the semi-conservative replication.
- **q3.** Does the patient in bed 5 have DNA replication?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the DNA replication ; he doesn't
  - modèle : No, he does not have a problem with the DNA replication.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS réplication de l'adn — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : origin of replication ; a problem with the origin of replication
  - modèle : He has a problem with the origin of replication.
- **Tt2.** Does the patient in bed 9 have semi-conservative replication?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the semi-conservative replication ; he doesn't
  - modèle : No, he does not have a problem with the semi-conservative replication.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS réplication semi-conservative.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a DNA replication and a semi-conservative replication. (have)` → have · modèle : The patients have a DNA replication and a semi-conservative replication.
- `The patient in bed 5 … not have a DNA replication. (do)` → does · modèle : The patient in bed 5 does not have a DNA replication.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the DNA replication. The patient in bed 5 has a problem with the semi-conservative replication.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Origine de réplication, le lit 9 a Réplication de l'ADN et n'a pas Réplication semi-conservative.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the origin of replication. The patient in bed 9 has a problem with the DNA replication.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
