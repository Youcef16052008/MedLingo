# Relecture — Médicaments Cardiovasculaires (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-005` — Le cas de Meriem : Antagoniste des récepteurs de l'angiotensine (ARA) anormal(e) / _Meriem: the angiotensin receptor blocker is not normal_

**Objectif :** Comprendre des résultats : Antihypertenseur normal(e), Antagoniste des récepteurs de l'angiotensine (ARA) anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient at the clinic.
   - FR : Meriem est un·e patient·e à la clinique.
   - AR : Meriem مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The antihypertensive is normal.”
   - FR : Le médecin dit : « Antihypertenseur est normal(e). »
   - AR : يقول الطبيب: «مضاد الضغط المرتفع طبيعي/طبيعية.»
5. **EN** “But the angiotensin receptor blocker is not normal.”
   - FR : « Mais Antagoniste des récepteurs de l'angiotensine (ARA) n'est pas normal(e). »
   - AR : «لكن حاصرات مستقبلات الأنجيوتنسين غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the angiotensin receptor blocker normal?”
   - FR : Amine demande : « Antagoniste des récepteurs de l'angiotensine (ARA) est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل حاصرات مستقبلات الأنجيوتنسين طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:antihypertensive:v1` — **an antihypertensive** · FR : Antihypertenseur · AR : مضاد الضغط المرتفع
- `gen:ace-inhibitor:v1` — **a ACE inhibitor** · FR : Inhibiteur de l'ECA · AR : مثبط إنزيم تحويل الأنجيوتنسين
- `gen:angiotensin-receptor-blocker:v1` — **an angiotensin receptor blocker** · FR : Antagoniste des récepteurs de l'angiotensine (ARA) · AR : حاصرات مستقبلات الأنجيوتنسين

### Questions (acceptées / modèle / hint)
- **q1.** Is the antihypertensive normal?
  - accepté : yes ; yes it is ; the antihypertensive is normal ; it is normal
  - modèle : Yes, the antihypertensive is normal.
- **q2.** Is the angiotensin receptor blocker normal?
  - accepté : no ; no it is not ; no it isn't ; the angiotensin receptor blocker is not normal ; it is not normal ; it isn't normal
  - modèle : No, the angiotensin receptor blocker is not normal.
  - hint (si erreur) — FR : Attention : Antagoniste des récepteurs de l'angiotensine (ARA) n'est PAS normal(e).
- **q3.** Which one is not normal: the antihypertensive or the angiotensin receptor blocker?
  - accepté : angiotensin receptor blocker ; the angiotensin receptor blocker
  - modèle : The angiotensin receptor blocker is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the ACE inhibitor normal?
  - accepté : yes ; yes it is ; the ACE inhibitor is normal
  - modèle : Yes, the ACE inhibitor is normal.
- **Tt2.** Is the antihypertensive normal?
  - accepté : no ; no it is not ; no it isn't ; the antihypertensive is not normal ; it is not normal
  - modèle : No, the antihypertensive is not normal.
  - hint (si erreur) — FR : Attention : Antihypertenseur n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The antihypertensive … normal. (be)` → is · modèle : The antihypertensive is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Meriem en deux phrases. Garde la négation. — amorces : The antihypertensive is… / The angiotensin receptor blocker is not… — modèle : *The antihypertensive is normal. The angiotensin receptor blocker is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Inhibiteur de l'ECA normal(e), mais Antihypertenseur anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The ACE inhibitor is normal. The antihypertensive is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Antihypertenseur et Inhibiteur de l'ECA à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient.
   - FR : Hana est un·e patient·e.
   - AR : Hana مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check antihypertensive.”
   - FR : « D'abord, nous vérifions Antihypertenseur. »
   - AR : «أولًا، نتحقق من مضاد الضغط المرتفع.»
5. **EN** “Then, we check ACE inhibitor.”
   - FR : « Ensuite, nous vérifions Inhibiteur de l'ECA. »
   - AR : «ثم نتحقق من مثبط إنزيم تحويل الأنجيوتنسين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:antihypertensive:v1` — **an antihypertensive** · FR : Antihypertenseur · AR : مضاد الضغط المرتفع
- `gen:ace-inhibitor:v1` — **a ACE inhibitor** · FR : Inhibiteur de l'ECA · AR : مثبط إنزيم تحويل الأنجيوتنسين
- `gen:angiotensin-receptor-blocker:v1` — **an angiotensin receptor blocker** · FR : Antagoniste des récepteurs de l'angiotensine (ARA) · AR : حاصرات مستقبلات الأنجيوتنسين

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : antihypertensive ; we check antihypertensive ; antihypertensive first ; the doctor checks antihypertensive first
  - modèle : First, the doctor checks antihypertensive.
- **q2.** What does the doctor check next?
  - accepté : ACE inhibitor ; then we check ACE inhibitor ; ACE inhibitor next ; the doctor checks ACE inhibitor next
  - modèle : Then, the doctor checks ACE inhibitor.
- **q3.** Does the doctor check ACE inhibitor before antihypertensive?
  - accepté : no ; no he does not ; no he doesn't ; he checks antihypertensive first
  - modèle : No, he checks antihypertensive first, then ACE inhibitor.
  - hint (si erreur) — FR : Attention : Antihypertenseur vient D'ABORD, ensuite Inhibiteur de l'ECA.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : angiotensin receptor blocker ; angiotensin receptor blocker first ; he checks angiotensin receptor blocker first
  - modèle : First, the doctor checks angiotensin receptor blocker.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check antihypertensive. Then, we check ACE inhibitor.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Antagoniste des récepteurs de l'angiotensine (ARA), ensuite Antihypertenseur, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check angiotensin receptor blocker. Then, we check antihypertensive.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Antihypertenseur / Inhibiteur de l'ECA, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the antihypertensive.
   - FR : Le patient du lit 3 a Antihypertenseur.
   - AR : مريض السرير ٣ عنده مضاد الضغط المرتفع.
4. **EN** The patient in bed 5 has a problem with the ACE inhibitor.
   - FR : Le patient du lit 5 a Inhibiteur de l'ECA.
   - AR : مريض السرير ٥ عنده مثبط إنزيم تحويل الأنجيوتنسين.
5. **EN** The patient in bed 5 does not have a problem with the antihypertensive.
   - FR : Le patient du lit 5 n'a pas de Antihypertenseur.
   - AR : مريض السرير ٥ ليس عنده مضاد الضغط المرتفع.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:antihypertensive:v1` — **an antihypertensive** · FR : Antihypertenseur · AR : مضاد الضغط المرتفع
- `gen:ace-inhibitor:v1` — **a ACE inhibitor** · FR : Inhibiteur de l'ECA · AR : مثبط إنزيم تحويل الأنجيوتنسين
- `gen:angiotensin-receptor-blocker:v1` — **an angiotensin receptor blocker** · FR : Antagoniste des récepteurs de l'angiotensine (ARA) · AR : حاصرات مستقبلات الأنجيوتنسين

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : antihypertensive ; an antihypertensive ; the patient with antihypertensive
  - modèle : The patient in bed 3 has an antihypertensive.
- **q2.** What does the patient in bed 5 have?
  - accepté : ACE inhibitor ; a problem with the ACE inhibitor
  - modèle : He has a problem with the ACE inhibitor.
- **q3.** Does the patient in bed 5 have antihypertensive?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the antihypertensive ; he doesn't
  - modèle : No, he does not have a problem with the antihypertensive.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS antihypertenseur — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : angiotensin receptor blocker ; a problem with the angiotensin receptor blocker
  - modèle : He has a problem with the angiotensin receptor blocker.
- **Tt2.** Does the patient in bed 9 have ACE inhibitor?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the ACE inhibitor ; he doesn't
  - modèle : No, he does not have a problem with the ACE inhibitor.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS inhibiteur de l'eca.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an antihypertensive and a ACE inhibitor. (have)` → have · modèle : The patients have an antihypertensive and a ACE inhibitor.
- `The patient in bed 5 … not have an antihypertensive. (do)` → does · modèle : The patient in bed 5 does not have an antihypertensive.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the antihypertensive. The patient in bed 5 has a problem with the ACE inhibitor.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Antagoniste des récepteurs de l'angiotensine (ARA), le lit 9 a Antihypertenseur et n'a pas Inhibiteur de l'ECA.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the angiotensin receptor blocker. The patient in bed 9 has a problem with the antihypertensive.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
