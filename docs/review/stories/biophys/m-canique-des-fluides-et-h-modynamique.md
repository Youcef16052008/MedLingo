# Relecture — Mécanique des Fluides et Hémodynamique (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-006` — Le cas de Sofiane : Théorème de Bernoulli anormal(e) / _Sofiane: the bernoulli's principle is not normal_

**Objectif :** Comprendre des résultats : Loi de Poiseuille normal(e), Théorème de Bernoulli anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sofiane is a patient at the clinic.
   - FR : Sofiane est un·e patient·e à la clinique.
   - AR : Sofiane مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The poiseuille's law is normal.”
   - FR : Le médecin dit : « Loi de Poiseuille est normal(e). »
   - AR : يقول الطبيب: «قانون بواسوي في جريان الموائع طبيعي/طبيعية.»
5. **EN** “But the bernoulli's principle is not normal.”
   - FR : « Mais Théorème de Bernoulli n'est pas normal(e). »
   - AR : «لكن مبدأ برنولي لديناميكا السوائل غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the bernoulli's principle normal?”
   - FR : Lina demande : « Théorème de Bernoulli est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل مبدأ برنولي لديناميكا السوائل طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:poiseuille-s-law:v1` — **a poiseuille's law** · FR : Loi de Poiseuille · AR : قانون بواسوي في جريان الموائع
- `gen:reynolds-number:v1` — **a reynolds number** · FR : Nombre de Reynolds · AR : رقم رينولدز لجريان السوائل
- `gen:bernoulli-s-principle:v1` — **a bernoulli's principle** · FR : Théorème de Bernoulli · AR : مبدأ برنولي لديناميكا السوائل

### Questions (acceptées / modèle / hint)
- **q1.** Is the poiseuille's law normal?
  - accepté : yes ; yes it is ; the poiseuille's law is normal ; it is normal
  - modèle : Yes, the poiseuille's law is normal.
- **q2.** Is the bernoulli's principle normal?
  - accepté : no ; no it is not ; no it isn't ; the bernoulli's principle is not normal ; it is not normal ; it isn't normal
  - modèle : No, the bernoulli's principle is not normal.
  - hint (si erreur) — FR : Attention : Théorème de Bernoulli n'est PAS normal(e).
- **q3.** Which one is not normal: the poiseuille's law or the bernoulli's principle?
  - accepté : bernoulli's principle ; the bernoulli's principle
  - modèle : The bernoulli's principle is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the reynolds number normal?
  - accepté : yes ; yes it is ; the reynolds number is normal
  - modèle : Yes, the reynolds number is normal.
- **Tt2.** Is the poiseuille's law normal?
  - accepté : no ; no it is not ; no it isn't ; the poiseuille's law is not normal ; it is not normal
  - modèle : No, the poiseuille's law is not normal.
  - hint (si erreur) — FR : Attention : Loi de Poiseuille n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The poiseuille's law … normal. (be)` → is · modèle : The poiseuille's law is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sofiane en deux phrases. Garde la négation. — amorces : The poiseuille's law is… / The bernoulli's principle is not… — modèle : *The poiseuille's law is normal. The bernoulli's principle is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Nombre de Reynolds normal(e), mais Loi de Poiseuille anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The reynolds number is normal. The poiseuille's law is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Loi de Poiseuille et Nombre de Reynolds à un patient.
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
4. **EN** “First, we check poiseuille's law.”
   - FR : « D'abord, nous vérifions Loi de Poiseuille. »
   - AR : «أولًا، نتحقق من قانون بواسوي في جريان الموائع.»
5. **EN** “Then, we check reynolds number.”
   - FR : « Ensuite, nous vérifions Nombre de Reynolds. »
   - AR : «ثم نتحقق من رقم رينولدز لجريان السوائل.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:poiseuille-s-law:v1` — **a poiseuille's law** · FR : Loi de Poiseuille · AR : قانون بواسوي في جريان الموائع
- `gen:reynolds-number:v1` — **a reynolds number** · FR : Nombre de Reynolds · AR : رقم رينولدز لجريان السوائل
- `gen:bernoulli-s-principle:v1` — **a bernoulli's principle** · FR : Théorème de Bernoulli · AR : مبدأ برنولي لديناميكا السوائل

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : poiseuille's law ; we check poiseuille's law ; poiseuille's law first ; the doctor checks poiseuille's law first
  - modèle : First, the doctor checks poiseuille's law.
- **q2.** What does the doctor check next?
  - accepté : reynolds number ; then we check reynolds number ; reynolds number next ; the doctor checks reynolds number next
  - modèle : Then, the doctor checks reynolds number.
- **q3.** Does the doctor check reynolds number before poiseuille's law?
  - accepté : no ; no he does not ; no he doesn't ; he checks poiseuille's law first
  - modèle : No, he checks poiseuille's law first, then reynolds number.
  - hint (si erreur) — FR : Attention : Loi de Poiseuille vient D'ABORD, ensuite Nombre de Reynolds.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : bernoulli's principle ; bernoulli's principle first ; he checks bernoulli's principle first
  - modèle : First, the doctor checks bernoulli's principle.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check poiseuille's law. Then, we check reynolds number.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Théorème de Bernoulli, ensuite Loi de Poiseuille, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check bernoulli's principle. Then, we check poiseuille's law.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Loi de Poiseuille / Nombre de Reynolds, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the poiseuille's law.
   - FR : Le patient du lit 3 a Loi de Poiseuille.
   - AR : مريض السرير ٣ عنده قانون بواسوي في جريان الموائع.
4. **EN** The patient in bed 5 has a problem with the reynolds number.
   - FR : Le patient du lit 5 a Nombre de Reynolds.
   - AR : مريض السرير ٥ عنده رقم رينولدز لجريان السوائل.
5. **EN** The patient in bed 5 does not have a problem with the poiseuille's law.
   - FR : Le patient du lit 5 n'a pas de Loi de Poiseuille.
   - AR : مريض السرير ٥ ليس عنده قانون بواسوي في جريان الموائع.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:poiseuille-s-law:v1` — **a poiseuille's law** · FR : Loi de Poiseuille · AR : قانون بواسوي في جريان الموائع
- `gen:reynolds-number:v1` — **a reynolds number** · FR : Nombre de Reynolds · AR : رقم رينولدز لجريان السوائل
- `gen:bernoulli-s-principle:v1` — **a bernoulli's principle** · FR : Théorème de Bernoulli · AR : مبدأ برنولي لديناميكا السوائل

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : poiseuille's law ; a poiseuille's law ; the patient with poiseuille's law
  - modèle : The patient in bed 3 has a poiseuille's law.
- **q2.** What does the patient in bed 5 have?
  - accepté : reynolds number ; a problem with the reynolds number
  - modèle : He has a problem with the reynolds number.
- **q3.** Does the patient in bed 5 have poiseuille's law?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the poiseuille's law ; he doesn't
  - modèle : No, he does not have a problem with the poiseuille's law.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS loi de poiseuille — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : bernoulli's principle ; a problem with the bernoulli's principle
  - modèle : He has a problem with the bernoulli's principle.
- **Tt2.** Does the patient in bed 9 have reynolds number?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the reynolds number ; he doesn't
  - modèle : No, he does not have a problem with the reynolds number.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS nombre de reynolds.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a poiseuille's law and a reynolds number. (have)` → have · modèle : The patients have a poiseuille's law and a reynolds number.
- `The patient in bed 5 … not have a poiseuille's law. (do)` → does · modèle : The patient in bed 5 does not have a poiseuille's law.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the poiseuille's law. The patient in bed 5 has a problem with the reynolds number.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Théorème de Bernoulli, le lit 9 a Loi de Poiseuille et n'a pas Nombre de Reynolds.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the bernoulli's principle. The patient in bed 9 has a problem with the poiseuille's law.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
