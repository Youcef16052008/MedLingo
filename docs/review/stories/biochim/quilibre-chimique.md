# Relecture — Équilibre Chimique (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-025` — Le cas de Nadia : Principe de Le Chatelier anormal(e) / _Nadia: the le chatelier's principle is not normal_

**Objectif :** Comprendre des résultats : Équilibre chimique normal(e), Principe de Le Chatelier anormal(e).
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
4. **EN** The doctor says, “The chemical equilibrium is normal.”
   - FR : Le médecin dit : « Équilibre chimique est normal(e). »
   - AR : يقول الطبيب: «التوازن الكيميائي طبيعي/طبيعية.»
5. **EN** “But the le chatelier's principle is not normal.”
   - FR : « Mais Principe de Le Chatelier n'est pas normal(e). »
   - AR : «لكن مبدأ لوشاتيليه غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the le chatelier's principle normal?”
   - FR : Amine demande : « Principe de Le Chatelier est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل مبدأ لوشاتيليه طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:chemical-equilibrium:v1` — **a chemical equilibrium** · FR : Équilibre chimique · AR : التوازن الكيميائي
- `gen:equilibrium-constant:v1` — **an equilibrium constant** · FR : Constante d'équilibre (K) · AR : ثابت التوازن
- `gen:le-chatelier-s-principle:v1` — **a le chatelier's principle** · FR : Principe de Le Chatelier · AR : مبدأ لوشاتيليه

### Questions (acceptées / modèle / hint)
- **q1.** Is the chemical equilibrium normal?
  - accepté : yes ; yes it is ; the chemical equilibrium is normal ; it is normal
  - modèle : Yes, the chemical equilibrium is normal.
- **q2.** Is the le chatelier's principle normal?
  - accepté : no ; no it is not ; no it isn't ; the le chatelier's principle is not normal ; it is not normal ; it isn't normal
  - modèle : No, the le chatelier's principle is not normal.
  - hint (si erreur) — FR : Attention : Principe de Le Chatelier n'est PAS normal(e).
- **q3.** Which one is not normal: the chemical equilibrium or the le chatelier's principle?
  - accepté : le chatelier's principle ; the le chatelier's principle
  - modèle : The le chatelier's principle is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the equilibrium constant normal?
  - accepté : yes ; yes it is ; the equilibrium constant is normal
  - modèle : Yes, the equilibrium constant is normal.
- **Tt2.** Is the chemical equilibrium normal?
  - accepté : no ; no it is not ; no it isn't ; the chemical equilibrium is not normal ; it is not normal
  - modèle : No, the chemical equilibrium is not normal.
  - hint (si erreur) — FR : Attention : Équilibre chimique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The chemical equilibrium … normal. (be)` → is · modèle : The chemical equilibrium is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The chemical equilibrium is… / The le chatelier's principle is not… — modèle : *The chemical equilibrium is normal. The le chatelier's principle is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Constante d'équilibre (K) normal(e), mais Équilibre chimique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The equilibrium constant is normal. The chemical equilibrium is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-025` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Équilibre chimique et Constante d'équilibre (K) à un patient.
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
4. **EN** “First, we check chemical equilibrium.”
   - FR : « D'abord, nous vérifions Équilibre chimique. »
   - AR : «أولًا، نتحقق من التوازن الكيميائي.»
5. **EN** “Then, we check equilibrium constant.”
   - FR : « Ensuite, nous vérifions Constante d'équilibre (K). »
   - AR : «ثم نتحقق من ثابت التوازن.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:chemical-equilibrium:v1` — **a chemical equilibrium** · FR : Équilibre chimique · AR : التوازن الكيميائي
- `gen:equilibrium-constant:v1` — **an equilibrium constant** · FR : Constante d'équilibre (K) · AR : ثابت التوازن
- `gen:le-chatelier-s-principle:v1` — **a le chatelier's principle** · FR : Principe de Le Chatelier · AR : مبدأ لوشاتيليه

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : chemical equilibrium ; we check chemical equilibrium ; chemical equilibrium first ; the doctor checks chemical equilibrium first
  - modèle : First, the doctor checks chemical equilibrium.
- **q2.** What does the doctor check next?
  - accepté : equilibrium constant ; then we check equilibrium constant ; equilibrium constant next ; the doctor checks equilibrium constant next
  - modèle : Then, the doctor checks equilibrium constant.
- **q3.** Does the doctor check equilibrium constant before chemical equilibrium?
  - accepté : no ; no he does not ; no he doesn't ; he checks chemical equilibrium first
  - modèle : No, he checks chemical equilibrium first, then equilibrium constant.
  - hint (si erreur) — FR : Attention : Équilibre chimique vient D'ABORD, ensuite Constante d'équilibre (K).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : le chatelier's principle ; le chatelier's principle first ; he checks le chatelier's principle first
  - modèle : First, the doctor checks le chatelier's principle.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check chemical equilibrium. Then, we check equilibrium constant.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Principe de Le Chatelier, ensuite Équilibre chimique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check le chatelier's principle. Then, we check chemical equilibrium.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-025` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Équilibre chimique / Constante d'équilibre (K), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the chemical equilibrium.
   - FR : Le patient du lit 3 a Équilibre chimique.
   - AR : مريض السرير ٣ عنده التوازن الكيميائي.
4. **EN** The patient in bed 5 has a problem with the equilibrium constant.
   - FR : Le patient du lit 5 a Constante d'équilibre (K).
   - AR : مريض السرير ٥ عنده ثابت التوازن.
5. **EN** The patient in bed 5 does not have a problem with the chemical equilibrium.
   - FR : Le patient du lit 5 n'a pas de Équilibre chimique.
   - AR : مريض السرير ٥ ليس عنده التوازن الكيميائي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:chemical-equilibrium:v1` — **a chemical equilibrium** · FR : Équilibre chimique · AR : التوازن الكيميائي
- `gen:equilibrium-constant:v1` — **an equilibrium constant** · FR : Constante d'équilibre (K) · AR : ثابت التوازن
- `gen:le-chatelier-s-principle:v1` — **a le chatelier's principle** · FR : Principe de Le Chatelier · AR : مبدأ لوشاتيليه

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : chemical equilibrium ; a chemical equilibrium ; the patient with chemical equilibrium
  - modèle : The patient in bed 3 has a chemical equilibrium.
- **q2.** What does the patient in bed 5 have?
  - accepté : equilibrium constant ; a problem with the equilibrium constant
  - modèle : He has a problem with the equilibrium constant.
- **q3.** Does the patient in bed 5 have chemical equilibrium?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the chemical equilibrium ; he doesn't
  - modèle : No, he does not have a problem with the chemical equilibrium.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS équilibre chimique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : le chatelier's principle ; a problem with the le chatelier's principle
  - modèle : He has a problem with the le chatelier's principle.
- **Tt2.** Does the patient in bed 9 have equilibrium constant?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the equilibrium constant ; he doesn't
  - modèle : No, he does not have a problem with the equilibrium constant.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS constante d'équilibre (k).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a chemical equilibrium and an equilibrium constant. (have)` → have · modèle : The patients have a chemical equilibrium and an equilibrium constant.
- `The patient in bed 5 … not have a chemical equilibrium. (do)` → does · modèle : The patient in bed 5 does not have a chemical equilibrium.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the chemical equilibrium. The patient in bed 5 has a problem with the equilibrium constant.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Principe de Le Chatelier, le lit 9 a Équilibre chimique et n'a pas Constante d'équilibre (K).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the le chatelier's principle. The patient in bed 9 has a problem with the chemical equilibrium.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
