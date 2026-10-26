# Relecture — Médicaments Digestifs (pharmaco)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `pharmaco-case-006` — Le cas de Sofiane : Antiacide anormal(e) / _Sofiane: the antacid is not normal_

**Objectif :** Comprendre des résultats : Inhibiteur de la pompe à protons (IPP) normal(e), Antiacide anormal(e).
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
4. **EN** The doctor says, “The proton pump inhibitor is normal.”
   - FR : Le médecin dit : « Inhibiteur de la pompe à protons (IPP) est normal(e). »
   - AR : يقول الطبيب: «مثبط مضخة البروتون طبيعي/طبيعية.»
5. **EN** “But the antacid is not normal.”
   - FR : « Mais Antiacide n'est pas normal(e). »
   - AR : «لكن مضاد الحموضة غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the antacid normal?”
   - FR : Lina demande : « Antiacide est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل مضاد الحموضة طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:proton-pump-inhibitor:v1` — **a proton pump inhibitor** · FR : Inhibiteur de la pompe à protons (IPP) · AR : مثبط مضخة البروتون
- `gen:h2-receptor-antagonist:v1` — **a H2 receptor antagonist** · FR : Antagoniste des récepteurs H2 · AR : مضاد مستقبلات H2
- `gen:antacid:v1` — **an antacid** · FR : Antiacide · AR : مضاد الحموضة

### Questions (acceptées / modèle / hint)
- **q1.** Is the proton pump inhibitor normal?
  - accepté : yes ; yes it is ; the proton pump inhibitor is normal ; it is normal
  - modèle : Yes, the proton pump inhibitor is normal.
- **q2.** Is the antacid normal?
  - accepté : no ; no it is not ; no it isn't ; the antacid is not normal ; it is not normal ; it isn't normal
  - modèle : No, the antacid is not normal.
  - hint (si erreur) — FR : Attention : Antiacide n'est PAS normal(e).
- **q3.** Which one is not normal: the proton pump inhibitor or the antacid?
  - accepté : antacid ; the antacid
  - modèle : The antacid is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the H2 receptor antagonist normal?
  - accepté : yes ; yes it is ; the H2 receptor antagonist is normal
  - modèle : Yes, the H2 receptor antagonist is normal.
- **Tt2.** Is the proton pump inhibitor normal?
  - accepté : no ; no it is not ; no it isn't ; the proton pump inhibitor is not normal ; it is not normal
  - modèle : No, the proton pump inhibitor is not normal.
  - hint (si erreur) — FR : Attention : Inhibiteur de la pompe à protons (IPP) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The proton pump inhibitor … normal. (be)` → is · modèle : The proton pump inhibitor is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Sofiane en deux phrases. Garde la négation. — amorces : The proton pump inhibitor is… / The antacid is not… — modèle : *The proton pump inhibitor is normal. The antacid is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Antagoniste des récepteurs H2 normal(e), mais Inhibiteur de la pompe à protons (IPP) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The H2 receptor antagonist is normal. The proton pump inhibitor is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `pharmaco-explain-006` — Expliquer le plan à Bilal / _Explaining the plan to Bilal_

**Objectif :** Comprendre un médecin qui explique Inhibiteur de la pompe à protons (IPP) et Antagoniste des récepteurs H2 à un patient.
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
4. **EN** “First, we check proton pump inhibitor.”
   - FR : « D'abord, nous vérifions Inhibiteur de la pompe à protons (IPP). »
   - AR : «أولًا، نتحقق من مثبط مضخة البروتون.»
5. **EN** “Then, we check H2 receptor antagonist.”
   - FR : « Ensuite, nous vérifions Antagoniste des récepteurs H2. »
   - AR : «ثم نتحقق من مضاد مستقبلات H2.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Bilal says, “Thank you. I understand now.”
   - FR : Bilal dit : « Merci. Je comprends maintenant. »
   - AR : يقول Bilal: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:proton-pump-inhibitor:v1` — **a proton pump inhibitor** · FR : Inhibiteur de la pompe à protons (IPP) · AR : مثبط مضخة البروتون
- `gen:h2-receptor-antagonist:v1` — **a H2 receptor antagonist** · FR : Antagoniste des récepteurs H2 · AR : مضاد مستقبلات H2
- `gen:antacid:v1` — **an antacid** · FR : Antiacide · AR : مضاد الحموضة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : proton pump inhibitor ; we check proton pump inhibitor ; proton pump inhibitor first ; the doctor checks proton pump inhibitor first
  - modèle : First, the doctor checks proton pump inhibitor.
- **q2.** What does the doctor check next?
  - accepté : H2 receptor antagonist ; then we check H2 receptor antagonist ; H2 receptor antagonist next ; the doctor checks H2 receptor antagonist next
  - modèle : Then, the doctor checks H2 receptor antagonist.
- **q3.** Does the doctor check H2 receptor antagonist before proton pump inhibitor?
  - accepté : no ; no he does not ; no he doesn't ; he checks proton pump inhibitor first
  - modèle : No, he checks proton pump inhibitor first, then H2 receptor antagonist.
  - hint (si erreur) — FR : Attention : Inhibiteur de la pompe à protons (IPP) vient D'ABORD, ensuite Antagoniste des récepteurs H2.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : bilal ; the patient ; the patient (bilal)
  - modèle : Bilal understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : antacid ; antacid first ; he checks antacid first
  - modèle : First, the doctor checks antacid.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check proton pump inhibitor. Then, we check H2 receptor antagonist.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Antiacide, ensuite Inhibiteur de la pompe à protons (IPP), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check antacid. Then, we check proton pump inhibitor.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `pharmaco-handover-006` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Inhibiteur de la pompe à protons (IPP) / Antagoniste des récepteurs H2, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the proton pump inhibitor.
   - FR : Le patient du lit 3 a Inhibiteur de la pompe à protons (IPP).
   - AR : مريض السرير ٣ عنده مثبط مضخة البروتون.
4. **EN** The patient in bed 5 has a problem with the H2 receptor antagonist.
   - FR : Le patient du lit 5 a Antagoniste des récepteurs H2.
   - AR : مريض السرير ٥ عنده مضاد مستقبلات H2.
5. **EN** The patient in bed 5 does not have a problem with the proton pump inhibitor.
   - FR : Le patient du lit 5 n'a pas de Inhibiteur de la pompe à protons (IPP).
   - AR : مريض السرير ٥ ليس عنده مثبط مضخة البروتون.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:proton-pump-inhibitor:v1` — **a proton pump inhibitor** · FR : Inhibiteur de la pompe à protons (IPP) · AR : مثبط مضخة البروتون
- `gen:h2-receptor-antagonist:v1` — **a H2 receptor antagonist** · FR : Antagoniste des récepteurs H2 · AR : مضاد مستقبلات H2
- `gen:antacid:v1` — **an antacid** · FR : Antiacide · AR : مضاد الحموضة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : proton pump inhibitor ; a proton pump inhibitor ; the patient with proton pump inhibitor
  - modèle : The patient in bed 3 has a proton pump inhibitor.
- **q2.** What does the patient in bed 5 have?
  - accepté : H2 receptor antagonist ; a problem with the H2 receptor antagonist
  - modèle : He has a problem with the H2 receptor antagonist.
- **q3.** Does the patient in bed 5 have proton pump inhibitor?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the proton pump inhibitor ; he doesn't
  - modèle : No, he does not have a problem with the proton pump inhibitor.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS inhibiteur de la pompe à protons (ipp) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : antacid ; a problem with the antacid
  - modèle : He has a problem with the antacid.
- **Tt2.** Does the patient in bed 9 have H2 receptor antagonist?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the H2 receptor antagonist ; he doesn't
  - modèle : No, he does not have a problem with the H2 receptor antagonist.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS antagoniste des récepteurs h2.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a proton pump inhibitor and a H2 receptor antagonist. (have)` → have · modèle : The patients have a proton pump inhibitor and a H2 receptor antagonist.
- `The patient in bed 5 … not have a proton pump inhibitor. (do)` → does · modèle : The patient in bed 5 does not have a proton pump inhibitor.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the proton pump inhibitor. The patient in bed 5 has a problem with the H2 receptor antagonist.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Antiacide, le lit 9 a Inhibiteur de la pompe à protons (IPP) et n'a pas Antagoniste des récepteurs H2.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the antacid. The patient in bed 9 has a problem with the proton pump inhibitor.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
