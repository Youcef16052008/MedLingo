# Relecture — Transcription (genet)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `genet-case-014` — Le cas de Youssef : Promoteur anormal(e) / _Youssef: the promoter is not normal_

**Objectif :** Comprendre des résultats : Transcription normal(e), Promoteur anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Lina, a medical student, listens.
   - FR : Lina, étudiant·e en médecine, écoute.
   - AR : Lina، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The transcription is normal.”
   - FR : Le médecin dit : « Transcription est normal(e). »
   - AR : يقول الطبيب: «النسخ طبيعي/طبيعية.»
5. **EN** “But the promoter is not normal.”
   - FR : « Mais Promoteur n'est pas normal(e). »
   - AR : «لكن المُحرِّض غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the promoter normal?”
   - FR : Lina demande : « Promoteur est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل المُحرِّض طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:transcription:v1` — **a transcription** · FR : Transcription · AR : النسخ
- `gen:rna-polymerase:v1` — **a RNA polymerase** · FR : ARN polymérase · AR : بوليميراز RNA
- `gen:promoter:v1` — **a promoter** · FR : Promoteur · AR : المُحرِّض

### Questions (acceptées / modèle / hint)
- **q1.** Is the transcription normal?
  - accepté : yes ; yes it is ; the transcription is normal ; it is normal
  - modèle : Yes, the transcription is normal.
- **q2.** Is the promoter normal?
  - accepté : no ; no it is not ; no it isn't ; the promoter is not normal ; it is not normal ; it isn't normal
  - modèle : No, the promoter is not normal.
  - hint (si erreur) — FR : Attention : Promoteur n'est PAS normal(e).
- **q3.** Which one is not normal: the transcription or the promoter?
  - accepté : promoter ; the promoter
  - modèle : The promoter is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the RNA polymerase normal?
  - accepté : yes ; yes it is ; the RNA polymerase is normal
  - modèle : Yes, the RNA polymerase is normal.
- **Tt2.** Is the transcription normal?
  - accepté : no ; no it is not ; no it isn't ; the transcription is not normal ; it is not normal
  - modèle : No, the transcription is not normal.
  - hint (si erreur) — FR : Attention : Transcription n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The transcription … normal. (be)` → is · modèle : The transcription is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The transcription is… / The promoter is not… — modèle : *The transcription is normal. The promoter is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : ARN polymérase normal(e), mais Transcription anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The RNA polymerase is normal. The transcription is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `genet-explain-014` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Transcription et ARN polymérase à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Farid is a patient.
   - FR : Farid est un·e patient·e.
   - AR : Farid مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check transcription.”
   - FR : « D'abord, nous vérifions Transcription. »
   - AR : «أولًا، نتحقق من النسخ.»
5. **EN** “Then, we check RNA polymerase.”
   - FR : « Ensuite, nous vérifions ARN polymérase. »
   - AR : «ثم نتحقق من بوليميراز RNA.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:transcription:v1` — **a transcription** · FR : Transcription · AR : النسخ
- `gen:rna-polymerase:v1` — **a RNA polymerase** · FR : ARN polymérase · AR : بوليميراز RNA
- `gen:promoter:v1` — **a promoter** · FR : Promoteur · AR : المُحرِّض

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : transcription ; we check transcription ; transcription first ; the doctor checks transcription first
  - modèle : First, the doctor checks transcription.
- **q2.** What does the doctor check next?
  - accepté : RNA polymerase ; then we check RNA polymerase ; RNA polymerase next ; the doctor checks RNA polymerase next
  - modèle : Then, the doctor checks RNA polymerase.
- **q3.** Does the doctor check RNA polymerase before transcription?
  - accepté : no ; no he does not ; no he doesn't ; he checks transcription first
  - modèle : No, he checks transcription first, then RNA polymerase.
  - hint (si erreur) — FR : Attention : Transcription vient D'ABORD, ensuite ARN polymérase.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : promoter ; promoter first ; he checks promoter first
  - modèle : First, the doctor checks promoter.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check transcription. Then, we check RNA polymerase.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Promoteur, ensuite Transcription, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check promoter. Then, we check transcription.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `genet-handover-014` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Transcription / ARN polymérase, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the transcription.
   - FR : Le patient du lit 3 a Transcription.
   - AR : مريض السرير ٣ عنده النسخ.
4. **EN** The patient in bed 5 has a problem with the RNA polymerase.
   - FR : Le patient du lit 5 a ARN polymérase.
   - AR : مريض السرير ٥ عنده بوليميراز RNA.
5. **EN** The patient in bed 5 does not have a problem with the transcription.
   - FR : Le patient du lit 5 n'a pas de Transcription.
   - AR : مريض السرير ٥ ليس عنده النسخ.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:transcription:v1` — **a transcription** · FR : Transcription · AR : النسخ
- `gen:rna-polymerase:v1` — **a RNA polymerase** · FR : ARN polymérase · AR : بوليميراز RNA
- `gen:promoter:v1` — **a promoter** · FR : Promoteur · AR : المُحرِّض

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : transcription ; a transcription ; the patient with transcription
  - modèle : The patient in bed 3 has a transcription.
- **q2.** What does the patient in bed 5 have?
  - accepté : RNA polymerase ; a problem with the RNA polymerase
  - modèle : He has a problem with the RNA polymerase.
- **q3.** Does the patient in bed 5 have transcription?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the transcription ; he doesn't
  - modèle : No, he does not have a problem with the transcription.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS transcription — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : promoter ; a problem with the promoter
  - modèle : He has a problem with the promoter.
- **Tt2.** Does the patient in bed 9 have RNA polymerase?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the RNA polymerase ; he doesn't
  - modèle : No, he does not have a problem with the RNA polymerase.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS ARN polymérase.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a transcription and a RNA polymerase. (have)` → have · modèle : The patients have a transcription and a RNA polymerase.
- `The patient in bed 5 … not have a transcription. (do)` → does · modèle : The patient in bed 5 does not have a transcription.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the transcription. The patient in bed 5 has a problem with the RNA polymerase.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Promoteur, le lit 9 a Transcription et n'a pas ARN polymérase.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the promoter. The patient in bed 9 has a problem with the transcription.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
