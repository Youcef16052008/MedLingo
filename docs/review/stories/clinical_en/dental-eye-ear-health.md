# Relecture — Dental, Eye & Ear Health (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-006` — Le cas de Amel : Mal de dent / _Amel: Toothache_

**Objectif :** Comprendre un cas : mal de dent présent, perte auditive absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Amel talks to Lina, a medical student.
   - FR : Amel parle avec Lina, étudiant·e en médecine.
   - AR : Amel يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has toothache.
   - FR : Elle a Mal de dent.
   - AR : عنده ألم الأسنان.
4. **EN** She does not have hearing loss.
   - FR : Elle n'a pas de Perte auditive.
   - AR : ليس عنده فقدان السمع.
5. **EN** Lina asks, “Do you have hearing loss?”
   - FR : Lina demande : « Avez-vous Perte auditive ? »
   - AR : يسأل Lina: «هل عندك فقدان السمع؟»
6. **EN** Amel says, “No, I do not.”
   - FR : Amel répond : « Non. »
   - AR : يجيب Amel: «لا.»
7. **EN** Lina writes “Toothache” in the file.
   - FR : Lina écrit « Mal de dent » dans le dossier.
   - AR : يكتب Lina «Mal de dent» في الملف.

### Expressions ciblées
- `gen:toothache:v1` — **toothache** · FR : Mal de dent · AR : ألم الأسنان
- `gen:hearing-loss:v1` — **hearing loss** · FR : Perte auditive · AR : فقدان السمع
- `gen:cavity:v1` — **a cavity** · FR : Carie dentaire · AR : تسوس الأسنان

### Questions (acceptées / modèle / hint)
- **q1.** Does Amel have toothache?
  - accepté : yes ; yes she does ; yes she has toothache ; she has toothache
  - modèle : Yes, she has toothache.
- **q2.** Does she have hearing loss?
  - accepté : no ; no she does not ; no she doesn't ; she does not have hearing loss ; she doesn't
  - modèle : No, she does not have hearing loss.
  - hint (si erreur) — FR : Attention : Amel n'a PAS perte auditive.
- **q3.** Does she have toothache or hearing loss?
  - accepté : toothache ; she has toothache
  - modèle : She has toothache.
- **q4.** What does Amel have?
  - accepté : toothache ; she has toothache
  - modèle : She has toothache.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have cavity?
  - accepté : yes ; yes he does ; he has a cavity
  - modèle : Yes, he has a cavity.
- **Tt2.** Does he have toothache?
  - accepté : no ; no he does not ; no he doesn't ; he does not have toothache ; he doesn't
  - modèle : No, he does not have toothache.
  - hint (si erreur) — FR : Attention : il n'a PAS mal de dent.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … toothache. (have)` → has · modèle : She has toothache.
- `They … not have hearing loss. (do)` → do · modèle : They do not have hearing loss.
- `I … toothache. (have)` → have · modèle : I have toothache.

### Productions
- **Reformulation :** Rapporte le cas de Amel en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has toothache. She does not have hearing loss.*
- **Transfert (contexte) :** Un autre patient a Carie dentaire. Il n'a pas de Mal de dent.
- Glossaire : Carie dentaire : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a cavity. He does not have toothache.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Mal de dent et Carie dentaire à un patient.
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
4. **EN** “First, we check toothache.”
   - FR : « D'abord, nous vérifions Mal de dent. »
   - AR : «أولًا، نتحقق من ألم الأسنان.»
5. **EN** “Then, we check cavity.”
   - FR : « Ensuite, nous vérifions Carie dentaire. »
   - AR : «ثم نتحقق من تسوس الأسنان.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:toothache:v1` — **toothache** · FR : Mal de dent · AR : ألم الأسنان
- `gen:cavity:v1` — **a cavity** · FR : Carie dentaire · AR : تسوس الأسنان
- `gen:gum-disease:v1` — **a gum disease** · FR : Maladie des gencives · AR : مرض اللثة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : toothache ; we check toothache ; toothache first ; the doctor checks toothache first
  - modèle : First, the doctor checks toothache.
- **q2.** What does the doctor check next?
  - accepté : cavity ; then we check cavity ; cavity next ; the doctor checks cavity next
  - modèle : Then, the doctor checks cavity.
- **q3.** Does the doctor check cavity before toothache?
  - accepté : no ; no he does not ; no he doesn't ; he checks toothache first
  - modèle : No, he checks toothache first, then cavity.
  - hint (si erreur) — FR : Attention : Mal de dent vient D'ABORD, ensuite Carie dentaire.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : gum disease ; gum disease first ; he checks gum disease first
  - modèle : First, the doctor checks gum disease.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check toothache. Then, we check cavity.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Maladie des gencives, ensuite Mal de dent, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check gum disease. Then, we check toothache.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Mal de dent / Perte auditive, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has toothache.
   - FR : Le patient du lit 3 a Mal de dent.
   - AR : مريض السرير ٣ عنده ألم الأسنان.
4. **EN** The patient in bed 5 has hearing loss.
   - FR : Le patient du lit 5 a Perte auditive.
   - AR : مريض السرير ٥ عنده فقدان السمع.
5. **EN** The patient in bed 5 does not have toothache.
   - FR : Le patient du lit 5 n'a pas de Mal de dent.
   - AR : مريض السرير ٥ ليس عنده ألم الأسنان.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:toothache:v1` — **toothache** · FR : Mal de dent · AR : ألم الأسنان
- `gen:hearing-loss:v1` — **hearing loss** · FR : Perte auditive · AR : فقدان السمع
- `gen:cavity:v1` — **a cavity** · FR : Carie dentaire · AR : تسوس الأسنان

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : toothache ; the patient with toothache
  - modèle : The patient in bed 3 has toothache.
- **q2.** What does the patient in bed 5 have?
  - accepté : hearing loss ; he has hearing loss
  - modèle : He has hearing loss.
- **q3.** Does the patient in bed 5 have toothache?
  - accepté : no ; no he does not ; no he doesn't ; he does not have toothache ; he doesn't
  - modèle : No, he does not have toothache.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS mal de dent — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : cavity ; a cavity ; he has a cavity
  - modèle : He has a cavity.
- **Tt2.** Does the patient in bed 9 have hearing loss?
  - accepté : no ; no he does not ; no he doesn't ; he does not have hearing loss ; he doesn't
  - modèle : No, he does not have hearing loss.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS perte auditive.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … toothache and hearing loss. (have)` → have · modèle : The patients have toothache and hearing loss.
- `The patient in bed 5 … not have toothache. (do)` → does · modèle : The patient in bed 5 does not have toothache.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has toothache. The patient in bed 5 has hearing loss.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Carie dentaire, le lit 9 a Mal de dent et n'a pas Perte auditive.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a cavity. The patient in bed 9 has toothache.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
