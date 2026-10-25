# Relecture — Maladies Infectieuses (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-006` — Le cas de Amel : Méningite / _Amel: Meningitis_

**Objectif :** Comprendre un cas : méningite présent, gastroentérite absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amel is a patient at the clinic.
   - FR : Amel est un·e patient·e à la clinique.
   - AR : Amel مريض/مريضة في العيادة.
2. **EN** Amel talks to Lina, a medical student.
   - FR : Amel parle avec Lina, étudiant·e en médecine.
   - AR : Amel يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has meningitis.
   - FR : Elle a Méningite.
   - AR : عنده التهاب السحايا.
4. **EN** She does not have gastroenteritis.
   - FR : Elle n'a pas de Gastroentérite.
   - AR : ليس عنده التهاب المعدة والأمعاء.
5. **EN** Lina asks, “Do you have gastroenteritis?”
   - FR : Lina demande : « Avez-vous Gastroentérite ? »
   - AR : يسأل Lina: «هل عندك التهاب المعدة والأمعاء؟»
6. **EN** Amel says, “No, I do not.”
   - FR : Amel répond : « Non. »
   - AR : يجيب Amel: «لا.»
7. **EN** Lina writes “Meningitis” in the file.
   - FR : Lina écrit « Méningite » dans le dossier.
   - AR : يكتب Lina «Méningite» في الملف.

### Expressions ciblées
- `gen:meningitis:v1` — **meningitis** · FR : Méningite · AR : التهاب السحايا
- `gen:gastroenteritis:v1` — **gastroenteritis** · FR : Gastroentérite · AR : التهاب المعدة والأمعاء
- `gen:sepsis:v1` — **sepsis** · FR : Sepsis · AR : الإنتان

### Questions (acceptées / modèle / hint)
- **q1.** Does Amel have meningitis?
  - accepté : yes ; yes she does ; yes she has meningitis ; she has meningitis
  - modèle : Yes, she has meningitis.
- **q2.** Does she have gastroenteritis?
  - accepté : no ; no she does not ; no she doesn't ; she does not have gastroenteritis ; she doesn't
  - modèle : No, she does not have gastroenteritis.
  - hint (si erreur) — FR : Attention : Amel n'a PAS gastroentérite.
- **q3.** Does she have meningitis or gastroenteritis?
  - accepté : meningitis ; she has meningitis
  - modèle : She has meningitis.
- **q4.** What does Amel have?
  - accepté : meningitis ; she has meningitis
  - modèle : She has meningitis.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have sepsis?
  - accepté : yes ; yes he does ; he has sepsis
  - modèle : Yes, he has sepsis.
- **Tt2.** Does he have meningitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have meningitis ; he doesn't
  - modèle : No, he does not have meningitis.
  - hint (si erreur) — FR : Attention : il n'a PAS méningite.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … meningitis. (have)` → has · modèle : She has meningitis.
- `They … not have gastroenteritis. (do)` → do · modèle : They do not have gastroenteritis.
- `I … meningitis. (have)` → have · modèle : I have meningitis.

### Productions
- **Reformulation :** Rapporte le cas de Amel en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has meningitis. She does not have gastroenteritis.*
- **Transfert (contexte) :** Un autre patient a Sepsis. Il n'a pas de Méningite.
- Glossaire : Sepsis : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has sepsis. He does not have meningitis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Sepsis et Choc septique à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check sepsis.”
   - FR : « D'abord, nous vérifions Sepsis. »
   - AR : «أولًا، نتحقق من الإنتان.»
5. **EN** “Then, we check septic shock.”
   - FR : « Ensuite, nous vérifions Choc septique. »
   - AR : «ثم نتحقق من الصدمة الإنتانية.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:sepsis:v1` — **sepsis** · FR : Sepsis · AR : الإنتان
- `gen:septic-shock:v1` — **a septic shock** · FR : Choc septique · AR : الصدمة الإنتانية
- `gen:meningitis:v1` — **meningitis** · FR : Méningite · AR : التهاب السحايا

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : sepsis ; we check sepsis ; sepsis first ; the doctor checks sepsis first
  - modèle : First, the doctor checks sepsis.
- **q2.** What does the doctor check next?
  - accepté : septic shock ; then we check septic shock ; septic shock next ; the doctor checks septic shock next
  - modèle : Then, the doctor checks septic shock.
- **q3.** Does the doctor check septic shock before sepsis?
  - accepté : no ; no he does not ; no he doesn't ; he checks sepsis first
  - modèle : No, he checks sepsis first, then septic shock.
  - hint (si erreur) — FR : Attention : Sepsis vient D'ABORD, ensuite Choc septique.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : meningitis ; meningitis first ; he checks meningitis first
  - modèle : First, the doctor checks meningitis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check sepsis. Then, we check septic shock.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Méningite, ensuite Sepsis, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check meningitis. Then, we check sepsis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Méningite / Gastroentérite, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has meningitis.
   - FR : Le patient du lit 3 a Méningite.
   - AR : مريض السرير ٣ عنده التهاب السحايا.
4. **EN** The patient in bed 5 has gastroenteritis.
   - FR : Le patient du lit 5 a Gastroentérite.
   - AR : مريض السرير ٥ عنده التهاب المعدة والأمعاء.
5. **EN** The patient in bed 5 does not have meningitis.
   - FR : Le patient du lit 5 n'a pas de Méningite.
   - AR : مريض السرير ٥ ليس عنده التهاب السحايا.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:meningitis:v1` — **meningitis** · FR : Méningite · AR : التهاب السحايا
- `gen:gastroenteritis:v1` — **gastroenteritis** · FR : Gastroentérite · AR : التهاب المعدة والأمعاء
- `gen:sepsis:v1` — **sepsis** · FR : Sepsis · AR : الإنتان

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : meningitis ; the patient with meningitis
  - modèle : The patient in bed 3 has meningitis.
- **q2.** What does the patient in bed 5 have?
  - accepté : gastroenteritis
  - modèle : He has gastroenteritis.
- **q3.** Does the patient in bed 5 have meningitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have meningitis ; he doesn't
  - modèle : No, he does not have meningitis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS méningite — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : sepsis
  - modèle : He has sepsis.
- **Tt2.** Does the patient in bed 9 have gastroenteritis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have gastroenteritis ; he doesn't
  - modèle : No, he does not have gastroenteritis.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS gastroentérite.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … meningitis and gastroenteritis. (have)` → have · modèle : The patients have meningitis and gastroenteritis.
- `The patient in bed 5 … not have meningitis. (do)` → does · modèle : The patient in bed 5 does not have meningitis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has meningitis. The patient in bed 5 has gastroenteritis.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Sepsis, le lit 9 a Méningite et n'a pas Gastroentérite.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has sepsis. The patient in bed 9 has meningitis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
