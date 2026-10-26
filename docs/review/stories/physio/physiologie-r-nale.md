# Relecture — Physiologie Rénale (physio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `physio-case-007` — Le cas de Hana : Néphron anormal(e) / _Hana: the nephron is not normal_

**Objectif :** Comprendre des résultats : Débit de filtration glomérulaire (DFG) normal(e), Néphron anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient at the clinic.
   - FR : Hana est un·e patient·e à la clinique.
   - AR : Hana مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The glomerular filtration rate is normal.”
   - FR : Le médecin dit : « Débit de filtration glomérulaire (DFG) est normal(e). »
   - AR : يقول الطبيب: «معدل الترشيح الكبيبي الكلوي (GFR) طبيعي/طبيعية.»
5. **EN** “But the nephron is not normal.”
   - FR : « Mais Néphron n'est pas normal(e). »
   - AR : «لكن النفرون (الوحدة الكلوية) غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the nephron normal?”
   - FR : Amine demande : « Néphron est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل النفرون (الوحدة الكلوية) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:glomerular-filtration-rate:v1` — **a glomerular filtration rate** · FR : Débit de filtration glomérulaire (DFG) · AR : معدل الترشيح الكبيبي الكلوي (GFR)
- `gen:renin-angiotensin-aldosterone-system:v1` — **a renin-angiotensin-aldosterone system** · FR : Système rénine-angiotensine-aldostérone (SRAA) · AR : جهاز الرينين أنجيوتنسين ألدوستيرون
- `gen:nephron:v1` — **a nephron** · FR : Néphron · AR : النفرون (الوحدة الكلوية)

### Questions (acceptées / modèle / hint)
- **q1.** Is the glomerular filtration rate normal?
  - accepté : yes ; yes it is ; the glomerular filtration rate is normal ; it is normal
  - modèle : Yes, the glomerular filtration rate is normal.
- **q2.** Is the nephron normal?
  - accepté : no ; no it is not ; no it isn't ; the nephron is not normal ; it is not normal ; it isn't normal
  - modèle : No, the nephron is not normal.
  - hint (si erreur) — FR : Attention : Néphron n'est PAS normal(e).
- **q3.** Which one is not normal: the glomerular filtration rate or the nephron?
  - accepté : nephron ; the nephron
  - modèle : The nephron is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the renin-angiotensin-aldosterone system normal?
  - accepté : yes ; yes it is ; the renin-angiotensin-aldosterone system is normal
  - modèle : Yes, the renin-angiotensin-aldosterone system is normal.
- **Tt2.** Is the glomerular filtration rate normal?
  - accepté : no ; no it is not ; no it isn't ; the glomerular filtration rate is not normal ; it is not normal
  - modèle : No, the glomerular filtration rate is not normal.
  - hint (si erreur) — FR : Attention : Débit de filtration glomérulaire (DFG) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The glomerular filtration rate … normal. (be)` → is · modèle : The glomerular filtration rate is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Hana en deux phrases. Garde la négation. — amorces : The glomerular filtration rate is… / The nephron is not… — modèle : *The glomerular filtration rate is normal. The nephron is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Système rénine-angiotensine-aldostérone (SRAA) normal(e), mais Débit de filtration glomérulaire (DFG) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The renin-angiotensin-aldosterone system is normal. The glomerular filtration rate is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `physio-explain-007` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Débit de filtration glomérulaire (DFG) et Système rénine-angiotensine-aldostérone (SRAA) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient.
   - FR : Sara est un·e patient·e.
   - AR : Sara مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check glomerular filtration rate.”
   - FR : « D'abord, nous vérifions Débit de filtration glomérulaire (DFG). »
   - AR : «أولًا، نتحقق من معدل الترشيح الكبيبي الكلوي (GFR).»
5. **EN** “Then, we check renin-angiotensin-aldosterone system.”
   - FR : « Ensuite, nous vérifions Système rénine-angiotensine-aldostérone (SRAA). »
   - AR : «ثم نتحقق من جهاز الرينين أنجيوتنسين ألدوستيرون.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:glomerular-filtration-rate:v1` — **a glomerular filtration rate** · FR : Débit de filtration glomérulaire (DFG) · AR : معدل الترشيح الكبيبي الكلوي (GFR)
- `gen:renin-angiotensin-aldosterone-system:v1` — **a renin-angiotensin-aldosterone system** · FR : Système rénine-angiotensine-aldostérone (SRAA) · AR : جهاز الرينين أنجيوتنسين ألدوستيرون
- `gen:nephron:v1` — **a nephron** · FR : Néphron · AR : النفرون (الوحدة الكلوية)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : glomerular filtration rate ; we check glomerular filtration rate ; glomerular filtration rate first ; the doctor checks glomerular filtration rate first
  - modèle : First, the doctor checks glomerular filtration rate.
- **q2.** What does the doctor check next?
  - accepté : renin-angiotensin-aldosterone system ; then we check renin-angiotensin-aldosterone system ; renin-angiotensin-aldosterone system next ; the doctor checks renin-angiotensin-aldosterone system next
  - modèle : Then, the doctor checks renin-angiotensin-aldosterone system.
- **q3.** Does the doctor check renin-angiotensin-aldosterone system before glomerular filtration rate?
  - accepté : no ; no he does not ; no he doesn't ; he checks glomerular filtration rate first
  - modèle : No, he checks glomerular filtration rate first, then renin-angiotensin-aldosterone system.
  - hint (si erreur) — FR : Attention : Débit de filtration glomérulaire (DFG) vient D'ABORD, ensuite Système rénine-angiotensine-aldostérone (SRAA).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : nephron ; nephron first ; he checks nephron first
  - modèle : First, the doctor checks nephron.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check glomerular filtration rate. Then, we check renin-angiotensin-aldosterone system.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Néphron, ensuite Débit de filtration glomérulaire (DFG), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check nephron. Then, we check glomerular filtration rate.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `physio-handover-007` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Débit de filtration glomérulaire (DFG) / Système rénine-angiotensine-aldostérone (SRAA), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the glomerular filtration rate.
   - FR : Le patient du lit 3 a Débit de filtration glomérulaire (DFG).
   - AR : مريض السرير ٣ عنده معدل الترشيح الكبيبي الكلوي (GFR).
4. **EN** The patient in bed 5 has a problem with the renin-angiotensin-aldosterone system.
   - FR : Le patient du lit 5 a Système rénine-angiotensine-aldostérone (SRAA).
   - AR : مريض السرير ٥ عنده جهاز الرينين أنجيوتنسين ألدوستيرون.
5. **EN** The patient in bed 5 does not have a problem with the glomerular filtration rate.
   - FR : Le patient du lit 5 n'a pas de Débit de filtration glomérulaire (DFG).
   - AR : مريض السرير ٥ ليس عنده معدل الترشيح الكبيبي الكلوي (GFR).
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:glomerular-filtration-rate:v1` — **a glomerular filtration rate** · FR : Débit de filtration glomérulaire (DFG) · AR : معدل الترشيح الكبيبي الكلوي (GFR)
- `gen:renin-angiotensin-aldosterone-system:v1` — **a renin-angiotensin-aldosterone system** · FR : Système rénine-angiotensine-aldostérone (SRAA) · AR : جهاز الرينين أنجيوتنسين ألدوستيرون
- `gen:nephron:v1` — **a nephron** · FR : Néphron · AR : النفرون (الوحدة الكلوية)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : glomerular filtration rate ; a glomerular filtration rate ; the patient with glomerular filtration rate
  - modèle : The patient in bed 3 has a glomerular filtration rate.
- **q2.** What does the patient in bed 5 have?
  - accepté : renin-angiotensin-aldosterone system ; a problem with the renin-angiotensin-aldosterone system
  - modèle : He has a problem with the renin-angiotensin-aldosterone system.
- **q3.** Does the patient in bed 5 have glomerular filtration rate?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the glomerular filtration rate ; he doesn't
  - modèle : No, he does not have a problem with the glomerular filtration rate.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS débit de filtration glomérulaire (dfg) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : nephron ; a problem with the nephron
  - modèle : He has a problem with the nephron.
- **Tt2.** Does the patient in bed 9 have renin-angiotensin-aldosterone system?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the renin-angiotensin-aldosterone system ; he doesn't
  - modèle : No, he does not have a problem with the renin-angiotensin-aldosterone system.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS système rénine-angiotensine-aldostérone (sraa).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a glomerular filtration rate and a renin-angiotensin-aldosterone system. (have)` → have · modèle : The patients have a glomerular filtration rate and a renin-angiotensin-aldosterone system.
- `The patient in bed 5 … not have a glomerular filtration rate. (do)` → does · modèle : The patient in bed 5 does not have a glomerular filtration rate.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the glomerular filtration rate. The patient in bed 5 has a problem with the renin-angiotensin-aldosterone system.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Néphron, le lit 9 a Débit de filtration glomérulaire (DFG) et n'a pas Système rénine-angiotensine-aldostérone (SRAA).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the nephron. The patient in bed 9 has a problem with the glomerular filtration rate.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
