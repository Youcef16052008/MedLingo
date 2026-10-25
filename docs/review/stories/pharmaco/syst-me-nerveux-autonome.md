# Relecture — Système Nerveux Autonome (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-013` — Le cas de Nadia : Médicament cholinergique anormal(e) / _Nadia: the cholinergic drug is not normal_

**Objectif :** Comprendre des résultats : Système nerveux autonome (SNA) normal(e), Médicament cholinergique anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient at the clinic.
   - FR : Nadia est un·e patient·e à la clinique.
   - AR : Nadia مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The autonomic nervous system is normal.”
   - FR : Le médecin dit : « Système nerveux autonome (SNA) est normal(e). »
   - AR : يقول الطبيب: «الجهاز العصبي الذاتي طبيعي/طبيعية.»
5. **EN** “But the cholinergic drug is not normal.”
   - FR : « Mais Médicament cholinergique n'est pas normal(e). »
   - AR : «لكن الدواء الكوليني غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the cholinergic drug normal?”
   - FR : Amine demande : « Médicament cholinergique est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الدواء الكوليني طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:autonomic-nervous-system:v1` — **an autonomic nervous system** · FR : Système nerveux autonome (SNA) · AR : الجهاز العصبي الذاتي
- `gen:adrenergic-drug:v1` — **an adrenergic drug** · FR : Médicament adrénergique · AR : الدواء الأدرينالي
- `gen:cholinergic-drug:v1` — **a cholinergic drug** · FR : Médicament cholinergique · AR : الدواء الكوليني

### Questions (acceptées / modèle / hint)
- **q1.** Is the autonomic nervous system normal?
  - accepté : yes ; yes it is ; the autonomic nervous system is normal ; it is normal
  - modèle : Yes, the autonomic nervous system is normal.
- **q2.** Is the cholinergic drug normal?
  - accepté : no ; no it is not ; no it isn't ; the cholinergic drug is not normal ; it is not normal ; it isn't normal
  - modèle : No, the cholinergic drug is not normal.
  - hint (si erreur) — FR : Attention : Médicament cholinergique n'est PAS normal(e).
- **q3.** Which one is not normal: the autonomic nervous system or the cholinergic drug?
  - accepté : cholinergic drug ; the cholinergic drug
  - modèle : The cholinergic drug is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the adrenergic drug normal?
  - accepté : yes ; yes it is ; the adrenergic drug is normal
  - modèle : Yes, the adrenergic drug is normal.
- **Tt2.** Is the autonomic nervous system normal?
  - accepté : no ; no it is not ; no it isn't ; the autonomic nervous system is not normal ; it is not normal
  - modèle : No, the autonomic nervous system is not normal.
  - hint (si erreur) — FR : Attention : Système nerveux autonome (SNA) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The autonomic nervous system … normal. (be)` → is · modèle : The autonomic nervous system is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The autonomic nervous system is… / The cholinergic drug is not… — modèle : *The autonomic nervous system is normal. The cholinergic drug is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Médicament adrénergique normal(e), mais Système nerveux autonome (SNA) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The adrenergic drug is normal. The autonomic nervous system is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-013` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Système nerveux autonome (SNA) et Médicament adrénergique à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient.
   - FR : Amel est un·e patient·e.
   - AR : Amel مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check autonomic nervous system.”
   - FR : « D'abord, nous vérifions Système nerveux autonome (SNA). »
   - AR : «أولًا، نتحقق من الجهاز العصبي الذاتي.»
5. **EN** “Then, we check adrenergic drug.”
   - FR : « Ensuite, nous vérifions Médicament adrénergique. »
   - AR : «ثم نتحقق من الدواء الأدرينالي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:autonomic-nervous-system:v1` — **an autonomic nervous system** · FR : Système nerveux autonome (SNA) · AR : الجهاز العصبي الذاتي
- `gen:adrenergic-drug:v1` — **an adrenergic drug** · FR : Médicament adrénergique · AR : الدواء الأدرينالي
- `gen:cholinergic-drug:v1` — **a cholinergic drug** · FR : Médicament cholinergique · AR : الدواء الكوليني

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : autonomic nervous system ; we check autonomic nervous system ; autonomic nervous system first ; the doctor checks autonomic nervous system first
  - modèle : First, the doctor checks autonomic nervous system.
- **q2.** What does the doctor check next?
  - accepté : adrenergic drug ; then we check adrenergic drug ; adrenergic drug next ; the doctor checks adrenergic drug next
  - modèle : Then, the doctor checks adrenergic drug.
- **q3.** Does the doctor check adrenergic drug before autonomic nervous system?
  - accepté : no ; no he does not ; no he doesn't ; he checks autonomic nervous system first
  - modèle : No, he checks autonomic nervous system first, then adrenergic drug.
  - hint (si erreur) — FR : Attention : Système nerveux autonome (SNA) vient D'ABORD, ensuite Médicament adrénergique.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : cholinergic drug ; cholinergic drug first ; he checks cholinergic drug first
  - modèle : First, the doctor checks cholinergic drug.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check autonomic nervous system. Then, we check adrenergic drug.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Médicament cholinergique, ensuite Système nerveux autonome (SNA), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check cholinergic drug. Then, we check autonomic nervous system.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-013` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Système nerveux autonome (SNA) / Médicament adrénergique, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the autonomic nervous system.
   - FR : Le patient du lit 3 a Système nerveux autonome (SNA).
   - AR : مريض السرير ٣ عنده الجهاز العصبي الذاتي.
4. **EN** The patient in bed 5 has a problem with the adrenergic drug.
   - FR : Le patient du lit 5 a Médicament adrénergique.
   - AR : مريض السرير ٥ عنده الدواء الأدرينالي.
5. **EN** The patient in bed 5 does not have a problem with the autonomic nervous system.
   - FR : Le patient du lit 5 n'a pas de Système nerveux autonome (SNA).
   - AR : مريض السرير ٥ ليس عنده الجهاز العصبي الذاتي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:autonomic-nervous-system:v1` — **an autonomic nervous system** · FR : Système nerveux autonome (SNA) · AR : الجهاز العصبي الذاتي
- `gen:adrenergic-drug:v1` — **an adrenergic drug** · FR : Médicament adrénergique · AR : الدواء الأدرينالي
- `gen:cholinergic-drug:v1` — **a cholinergic drug** · FR : Médicament cholinergique · AR : الدواء الكوليني

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : autonomic nervous system ; an autonomic nervous system ; the patient with autonomic nervous system
  - modèle : The patient in bed 3 has an autonomic nervous system.
- **q2.** What does the patient in bed 5 have?
  - accepté : adrenergic drug ; a problem with the adrenergic drug
  - modèle : He has a problem with the adrenergic drug.
- **q3.** Does the patient in bed 5 have autonomic nervous system?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the autonomic nervous system ; he doesn't
  - modèle : No, he does not have a problem with the autonomic nervous system.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS système nerveux autonome (sna) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : cholinergic drug ; a problem with the cholinergic drug
  - modèle : He has a problem with the cholinergic drug.
- **Tt2.** Does the patient in bed 9 have adrenergic drug?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the adrenergic drug ; he doesn't
  - modèle : No, he does not have a problem with the adrenergic drug.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS médicament adrénergique.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an autonomic nervous system and an adrenergic drug. (have)` → have · modèle : The patients have an autonomic nervous system and an adrenergic drug.
- `The patient in bed 5 … not have an autonomic nervous system. (do)` → does · modèle : The patient in bed 5 does not have an autonomic nervous system.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the autonomic nervous system. The patient in bed 5 has a problem with the adrenergic drug.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Médicament cholinergique, le lit 9 a Système nerveux autonome (SNA) et n'a pas Médicament adrénergique.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the cholinergic drug. The patient in bed 9 has a problem with the autonomic nervous system.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
