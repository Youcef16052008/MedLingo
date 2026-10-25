# Relecture — Glucides (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-009` — Le cas de Sara : Fructose anormal(e) / _Sara: the fructose is not normal_

**Objectif :** Comprendre des résultats : Glucide normal(e), Fructose anormal(e).
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
4. **EN** The doctor says, “The carbohydrate is normal.”
   - FR : Le médecin dit : « Glucide est normal(e). »
   - AR : يقول الطبيب: «السكريات (الكربوهيدرات) طبيعي/طبيعية.»
5. **EN** “But the fructose is not normal.”
   - FR : « Mais Fructose n'est pas normal(e). »
   - AR : «لكن الفركتوز (سكر الفاكهة) غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the fructose normal?”
   - FR : Amine demande : « Fructose est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل الفركتوز (سكر الفاكهة) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:carbohydrate:v1` — **a carbohydrate** · FR : Glucide · AR : السكريات (الكربوهيدرات)
- `gen:monosaccharide:v1` — **a monosaccharide** · FR : Monosaccharide (Ose) · AR : السكر الأحادي
- `gen:fructose:v1` — **a fructose** · FR : Fructose · AR : الفركتوز (سكر الفاكهة)

### Questions (acceptées / modèle / hint)
- **q1.** Is the carbohydrate normal?
  - accepté : yes ; yes it is ; the carbohydrate is normal ; it is normal
  - modèle : Yes, the carbohydrate is normal.
- **q2.** Is the fructose normal?
  - accepté : no ; no it is not ; no it isn't ; the fructose is not normal ; it is not normal ; it isn't normal
  - modèle : No, the fructose is not normal.
  - hint (si erreur) — FR : Attention : Fructose n'est PAS normal(e).
- **q3.** Which one is not normal: the carbohydrate or the fructose?
  - accepté : fructose ; the fructose
  - modèle : The fructose is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the monosaccharide normal?
  - accepté : yes ; yes it is ; the monosaccharide is normal
  - modèle : Yes, the monosaccharide is normal.
- **Tt2.** Is the carbohydrate normal?
  - accepté : no ; no it is not ; no it isn't ; the carbohydrate is not normal ; it is not normal
  - modèle : No, the carbohydrate is not normal.
  - hint (si erreur) — FR : Attention : Glucide n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The carbohydrate … normal. (be)` → is · modèle : The carbohydrate is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sara en deux phrases. Garde la négation. — amorces : The carbohydrate is… / The fructose is not… — modèle : *The carbohydrate is normal. The fructose is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Monosaccharide (Ose) normal(e), mais Glucide anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The monosaccharide is normal. The carbohydrate is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-009` — Expliquer le plan à Leila / _Explaining the plan to Leila_

**Objectif :** Comprendre un médecin qui explique Glucide et Monosaccharide (Ose) à un patient.
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
4. **EN** “First, we check carbohydrate.”
   - FR : « D'abord, nous vérifions Glucide. »
   - AR : «أولًا، نتحقق من السكريات (الكربوهيدرات).»
5. **EN** “Then, we check monosaccharide.”
   - FR : « Ensuite, nous vérifions Monosaccharide (Ose). »
   - AR : «ثم نتحقق من السكر الأحادي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Leila says, “Thank you. I understand now.”
   - FR : Leila dit : « Merci. Je comprends maintenant. »
   - AR : يقول Leila: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:carbohydrate:v1` — **a carbohydrate** · FR : Glucide · AR : السكريات (الكربوهيدرات)
- `gen:monosaccharide:v1` — **a monosaccharide** · FR : Monosaccharide (Ose) · AR : السكر الأحادي
- `gen:fructose:v1` — **a fructose** · FR : Fructose · AR : الفركتوز (سكر الفاكهة)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : carbohydrate ; we check carbohydrate ; carbohydrate first ; the doctor checks carbohydrate first
  - modèle : First, the doctor checks carbohydrate.
- **q2.** What does the doctor check next?
  - accepté : monosaccharide ; then we check monosaccharide ; monosaccharide next ; the doctor checks monosaccharide next
  - modèle : Then, the doctor checks monosaccharide.
- **q3.** Does the doctor check monosaccharide before carbohydrate?
  - accepté : no ; no he does not ; no he doesn't ; he checks carbohydrate first
  - modèle : No, he checks carbohydrate first, then monosaccharide.
  - hint (si erreur) — FR : Attention : Glucide vient D'ABORD, ensuite Monosaccharide (Ose).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : leila ; the patient ; the patient (leila)
  - modèle : Leila understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : fructose ; fructose first ; he checks fructose first
  - modèle : First, the doctor checks fructose.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check carbohydrate. Then, we check monosaccharide.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fructose, ensuite Glucide, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check fructose. Then, we check carbohydrate.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-009` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Glucide / Monosaccharide (Ose), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the carbohydrate.
   - FR : Le patient du lit 3 a Glucide.
   - AR : مريض السرير ٣ عنده السكريات (الكربوهيدرات).
4. **EN** The patient in bed 5 has a problem with the monosaccharide.
   - FR : Le patient du lit 5 a Monosaccharide (Ose).
   - AR : مريض السرير ٥ عنده السكر الأحادي.
5. **EN** The patient in bed 5 does not have a problem with the carbohydrate.
   - FR : Le patient du lit 5 n'a pas de Glucide.
   - AR : مريض السرير ٥ ليس عنده السكريات (الكربوهيدرات).
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:carbohydrate:v1` — **a carbohydrate** · FR : Glucide · AR : السكريات (الكربوهيدرات)
- `gen:monosaccharide:v1` — **a monosaccharide** · FR : Monosaccharide (Ose) · AR : السكر الأحادي
- `gen:fructose:v1` — **a fructose** · FR : Fructose · AR : الفركتوز (سكر الفاكهة)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : carbohydrate ; a carbohydrate ; the patient with carbohydrate
  - modèle : The patient in bed 3 has a carbohydrate.
- **q2.** What does the patient in bed 5 have?
  - accepté : monosaccharide ; a problem with the monosaccharide
  - modèle : He has a problem with the monosaccharide.
- **q3.** Does the patient in bed 5 have carbohydrate?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the carbohydrate ; he doesn't
  - modèle : No, he does not have a problem with the carbohydrate.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS glucide — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : fructose ; a problem with the fructose
  - modèle : He has a problem with the fructose.
- **Tt2.** Does the patient in bed 9 have monosaccharide?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the monosaccharide ; he doesn't
  - modèle : No, he does not have a problem with the monosaccharide.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS monosaccharide (ose).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a carbohydrate and a monosaccharide. (have)` → have · modèle : The patients have a carbohydrate and a monosaccharide.
- `The patient in bed 5 … not have a carbohydrate. (do)` → does · modèle : The patient in bed 5 does not have a carbohydrate.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the carbohydrate. The patient in bed 5 has a problem with the monosaccharide.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Fructose, le lit 9 a Glucide et n'a pas Monosaccharide (Ose).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the fructose. The patient in bed 9 has a problem with the carbohydrate.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
