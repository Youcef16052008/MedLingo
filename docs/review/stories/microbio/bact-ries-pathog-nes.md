# Relecture — Bactéries Pathogènes (microbio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `microbio-case-001` — Le cas de Nadia : Streptococcus pyogenes anormal(e) / _Nadia: the streptococcus pyogenes is not normal_

**Objectif :** Comprendre des résultats : Staphylococcus aureus normal(e), Streptococcus pyogenes anormal(e).
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
4. **EN** The doctor says, “The staphylococcus aureus is normal.”
   - FR : Le médecin dit : « Staphylococcus aureus est normal(e). »
   - AR : يقول الطبيب: «المكور العنقودي الذهبي طبيعي/طبيعية.»
5. **EN** “But the streptococcus pyogenes is not normal.”
   - FR : « Mais Streptococcus pyogenes n'est pas normal(e). »
   - AR : «لكن العقدية القيحية غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the streptococcus pyogenes normal?”
   - FR : Amine demande : « Streptococcus pyogenes est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل العقدية القيحية طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:staphylococcus-aureus:v1` — **staphylococcus aureus** · FR : Staphylococcus aureus · AR : المكور العنقودي الذهبي
- `gen:mrsa:v1` — **a MRSA** · FR : SARM (Staphylocoque aureus résistant à la méticilline) · AR : المكور العنقودي المقاوم للميثيسيلين
- `gen:streptococcus-pyogenes:v1` — **streptococcus pyogenes** · FR : Streptococcus pyogenes · AR : العقدية القيحية

### Questions (acceptées / modèle / hint)
- **q1.** Is the staphylococcus aureus normal?
  - accepté : yes ; yes it is ; the staphylococcus aureus is normal ; it is normal
  - modèle : Yes, the staphylococcus aureus is normal.
- **q2.** Is the streptococcus pyogenes normal?
  - accepté : no ; no it is not ; no it isn't ; the streptococcus pyogenes is not normal ; it is not normal ; it isn't normal
  - modèle : No, the streptococcus pyogenes is not normal.
  - hint (si erreur) — FR : Attention : Streptococcus pyogenes n'est PAS normal(e).
- **q3.** Which one is not normal: the staphylococcus aureus or the streptococcus pyogenes?
  - accepté : streptococcus pyogenes ; the streptococcus pyogenes
  - modèle : The streptococcus pyogenes is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the MRSA normal?
  - accepté : yes ; yes it is ; the MRSA is normal
  - modèle : Yes, the MRSA is normal.
- **Tt2.** Is the staphylococcus aureus normal?
  - accepté : no ; no it is not ; no it isn't ; the staphylococcus aureus is not normal ; it is not normal
  - modèle : No, the staphylococcus aureus is not normal.
  - hint (si erreur) — FR : Attention : Staphylococcus aureus n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The staphylococcus aureus … normal. (be)` → is · modèle : The staphylococcus aureus is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Nadia en deux phrases. Garde la négation. — amorces : The staphylococcus aureus is… / The streptococcus pyogenes is not… — modèle : *The staphylococcus aureus is normal. The streptococcus pyogenes is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : SARM (Staphylocoque aureus résistant à la méticilline) normal(e), mais Staphylococcus aureus anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The MRSA is normal. The staphylococcus aureus is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `microbio-explain-001` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Staphylococcus aureus et SARM (Staphylocoque aureus résistant à la méticilline) à un patient.
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
4. **EN** “First, we check staphylococcus aureus.”
   - FR : « D'abord, nous vérifions Staphylococcus aureus. »
   - AR : «أولًا، نتحقق من المكور العنقودي الذهبي.»
5. **EN** “Then, we check MRSA.”
   - FR : « Ensuite, nous vérifions SARM (Staphylocoque aureus résistant à la méticilline). »
   - AR : «ثم نتحقق من المكور العنقودي المقاوم للميثيسيلين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:staphylococcus-aureus:v1` — **staphylococcus aureus** · FR : Staphylococcus aureus · AR : المكور العنقودي الذهبي
- `gen:mrsa:v1` — **a MRSA** · FR : SARM (Staphylocoque aureus résistant à la méticilline) · AR : المكور العنقودي المقاوم للميثيسيلين
- `gen:streptococcus-pyogenes:v1` — **streptococcus pyogenes** · FR : Streptococcus pyogenes · AR : العقدية القيحية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : staphylococcus aureus ; we check staphylococcus aureus ; staphylococcus aureus first ; the doctor checks staphylococcus aureus first
  - modèle : First, the doctor checks staphylococcus aureus.
- **q2.** What does the doctor check next?
  - accepté : MRSA ; then we check MRSA ; MRSA next ; the doctor checks MRSA next
  - modèle : Then, the doctor checks MRSA.
- **q3.** Does the doctor check MRSA before staphylococcus aureus?
  - accepté : no ; no he does not ; no he doesn't ; he checks staphylococcus aureus first
  - modèle : No, he checks staphylococcus aureus first, then MRSA.
  - hint (si erreur) — FR : Attention : Staphylococcus aureus vient D'ABORD, ensuite SARM (Staphylocoque aureus résistant à la méticilline).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : streptococcus pyogenes ; streptococcus pyogenes first ; he checks streptococcus pyogenes first
  - modèle : First, the doctor checks streptococcus pyogenes.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check staphylococcus aureus. Then, we check MRSA.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Streptococcus pyogenes, ensuite Staphylococcus aureus, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check streptococcus pyogenes. Then, we check staphylococcus aureus.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `microbio-handover-001` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Staphylococcus aureus / SARM (Staphylocoque aureus résistant à la méticilline), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the staphylococcus aureus.
   - FR : Le patient du lit 3 a Staphylococcus aureus.
   - AR : مريض السرير ٣ عنده المكور العنقودي الذهبي.
4. **EN** The patient in bed 5 has a problem with the MRSA.
   - FR : Le patient du lit 5 a SARM (Staphylocoque aureus résistant à la méticilline).
   - AR : مريض السرير ٥ عنده المكور العنقودي المقاوم للميثيسيلين.
5. **EN** The patient in bed 5 does not have a problem with the staphylococcus aureus.
   - FR : Le patient du lit 5 n'a pas de Staphylococcus aureus.
   - AR : مريض السرير ٥ ليس عنده المكور العنقودي الذهبي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:staphylococcus-aureus:v1` — **staphylococcus aureus** · FR : Staphylococcus aureus · AR : المكور العنقودي الذهبي
- `gen:mrsa:v1` — **a MRSA** · FR : SARM (Staphylocoque aureus résistant à la méticilline) · AR : المكور العنقودي المقاوم للميثيسيلين
- `gen:streptococcus-pyogenes:v1` — **streptococcus pyogenes** · FR : Streptococcus pyogenes · AR : العقدية القيحية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : staphylococcus aureus ; the patient with staphylococcus aureus
  - modèle : The patient in bed 3 has staphylococcus aureus.
- **q2.** What does the patient in bed 5 have?
  - accepté : MRSA ; a problem with the MRSA
  - modèle : He has a problem with the MRSA.
- **q3.** Does the patient in bed 5 have staphylococcus aureus?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the staphylococcus aureus ; he doesn't
  - modèle : No, he does not have a problem with the staphylococcus aureus.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS staphylococcus aureus — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : streptococcus pyogenes ; a problem with the streptococcus pyogenes
  - modèle : He has a problem with the streptococcus pyogenes.
- **Tt2.** Does the patient in bed 9 have MRSA?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the MRSA ; he doesn't
  - modèle : No, he does not have a problem with the MRSA.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS SARM (Staphylocoque aureus résistant à la méticilline).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … staphylococcus aureus and a MRSA. (have)` → have · modèle : The patients have staphylococcus aureus and a MRSA.
- `The patient in bed 5 … not have staphylococcus aureus. (do)` → does · modèle : The patient in bed 5 does not have staphylococcus aureus.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the staphylococcus aureus. The patient in bed 5 has a problem with the MRSA.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Streptococcus pyogenes, le lit 9 a Staphylococcus aureus et n'a pas SARM (Staphylocoque aureus résistant à la méticilline).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the streptococcus pyogenes. The patient in bed 9 has a problem with the staphylococcus aureus.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
