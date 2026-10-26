# Relecture — Enzymologie (biochim)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biochim-case-008` — Le cas de Bilal : Site actif anormal(e) / _Bilal: the active site is not normal_

**Objectif :** Comprendre des résultats : Enzyme normal(e), Site actif anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The enzyme is normal.”
   - FR : Le médecin dit : « Enzyme est normal(e). »
   - AR : يقول الطبيب: «الإنزيم (الخميرة) طبيعي/طبيعية.»
5. **EN** “But the active site is not normal.”
   - FR : « Mais Site actif n'est pas normal(e). »
   - AR : «لكن الموقع الفعال غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the active site normal?”
   - FR : Lina demande : « Site actif est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل الموقع الفعال طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:enzyme:v1` — **an enzyme** · FR : Enzyme · AR : الإنزيم (الخميرة)
- `gen:substrate:v1` — **a substrate** · FR : Substrat · AR : الركيزة (المادة المتفاعلة)
- `gen:active-site:v1` — **an active site** · FR : Site actif · AR : الموقع الفعال

### Questions (acceptées / modèle / hint)
- **q1.** Is the enzyme normal?
  - accepté : yes ; yes it is ; the enzyme is normal ; it is normal
  - modèle : Yes, the enzyme is normal.
- **q2.** Is the active site normal?
  - accepté : no ; no it is not ; no it isn't ; the active site is not normal ; it is not normal ; it isn't normal
  - modèle : No, the active site is not normal.
  - hint (si erreur) — FR : Attention : Site actif n'est PAS normal(e).
- **q3.** Which one is not normal: the enzyme or the active site?
  - accepté : active site ; the active site
  - modèle : The active site is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the substrate normal?
  - accepté : yes ; yes it is ; the substrate is normal
  - modèle : Yes, the substrate is normal.
- **Tt2.** Is the enzyme normal?
  - accepté : no ; no it is not ; no it isn't ; the enzyme is not normal ; it is not normal
  - modèle : No, the enzyme is not normal.
  - hint (si erreur) — FR : Attention : Enzyme n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The enzyme … normal. (be)` → is · modèle : The enzyme is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Bilal en deux phrases. Garde la négation. — amorces : The enzyme is… / The active site is not… — modèle : *The enzyme is normal. The active site is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Substrat normal(e), mais Enzyme anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The substrate is normal. The enzyme is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biochim-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Enzyme et Substrat à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient.
   - FR : Omar est un·e patient·e.
   - AR : Omar مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check enzyme.”
   - FR : « D'abord, nous vérifions Enzyme. »
   - AR : «أولًا، نتحقق من الإنزيم (الخميرة).»
5. **EN** “Then, we check substrate.”
   - FR : « Ensuite, nous vérifions Substrat. »
   - AR : «ثم نتحقق من الركيزة (المادة المتفاعلة).»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:enzyme:v1` — **an enzyme** · FR : Enzyme · AR : الإنزيم (الخميرة)
- `gen:substrate:v1` — **a substrate** · FR : Substrat · AR : الركيزة (المادة المتفاعلة)
- `gen:active-site:v1` — **an active site** · FR : Site actif · AR : الموقع الفعال

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : enzyme ; we check enzyme ; enzyme first ; the doctor checks enzyme first
  - modèle : First, the doctor checks enzyme.
- **q2.** What does the doctor check next?
  - accepté : substrate ; then we check substrate ; substrate next ; the doctor checks substrate next
  - modèle : Then, the doctor checks substrate.
- **q3.** Does the doctor check substrate before enzyme?
  - accepté : no ; no he does not ; no he doesn't ; he checks enzyme first
  - modèle : No, he checks enzyme first, then substrate.
  - hint (si erreur) — FR : Attention : Enzyme vient D'ABORD, ensuite Substrat.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : active site ; active site first ; he checks active site first
  - modèle : First, the doctor checks active site.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check enzyme. Then, we check substrate.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Site actif, ensuite Enzyme, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check active site. Then, we check enzyme.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biochim-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Enzyme / Substrat, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the enzyme.
   - FR : Le patient du lit 3 a Enzyme.
   - AR : مريض السرير ٣ عنده الإنزيم (الخميرة).
4. **EN** The patient in bed 5 has a problem with the substrate.
   - FR : Le patient du lit 5 a Substrat.
   - AR : مريض السرير ٥ عنده الركيزة (المادة المتفاعلة).
5. **EN** The patient in bed 5 does not have a problem with the enzyme.
   - FR : Le patient du lit 5 n'a pas de Enzyme.
   - AR : مريض السرير ٥ ليس عنده الإنزيم (الخميرة).
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:enzyme:v1` — **an enzyme** · FR : Enzyme · AR : الإنزيم (الخميرة)
- `gen:substrate:v1` — **a substrate** · FR : Substrat · AR : الركيزة (المادة المتفاعلة)
- `gen:active-site:v1` — **an active site** · FR : Site actif · AR : الموقع الفعال

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : enzyme ; an enzyme ; the patient with enzyme
  - modèle : The patient in bed 3 has an enzyme.
- **q2.** What does the patient in bed 5 have?
  - accepté : substrate ; a problem with the substrate
  - modèle : He has a problem with the substrate.
- **q3.** Does the patient in bed 5 have enzyme?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the enzyme ; he doesn't
  - modèle : No, he does not have a problem with the enzyme.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS enzyme — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : active site ; a problem with the active site
  - modèle : He has a problem with the active site.
- **Tt2.** Does the patient in bed 9 have substrate?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the substrate ; he doesn't
  - modèle : No, he does not have a problem with the substrate.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS substrat.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … an enzyme and a substrate. (have)` → have · modèle : The patients have an enzyme and a substrate.
- `The patient in bed 5 … not have an enzyme. (do)` → does · modèle : The patient in bed 5 does not have an enzyme.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the enzyme. The patient in bed 5 has a problem with the substrate.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Site actif, le lit 9 a Enzyme et n'a pas Substrat.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the active site. The patient in bed 9 has a problem with the enzyme.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
