# Relecture — Urology & Kidney Health (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-039` — Le cas de Karim : Miction (uriner) / _Karim: Urination_

**Objectif :** Comprendre un cas : miction (uriner) présent, incontinence urinaire absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Karim is a patient at the clinic.
   - FR : Karim est un·e patient·e à la clinique.
   - AR : Karim مريض/مريضة في العيادة.
2. **EN** Karim talks to Amine, a medical student.
   - FR : Karim parle avec Amine, étudiant·e en médecine.
   - AR : Karim يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has urination.
   - FR : Il a Miction (uriner).
   - AR : عنده التبول.
4. **EN** He does not have incontinence.
   - FR : Il n'a pas de Incontinence urinaire.
   - AR : ليس عنده سلس البول.
5. **EN** Amine asks, “Do you have incontinence?”
   - FR : Amine demande : « Avez-vous Incontinence urinaire ? »
   - AR : يسأل Amine: «هل عندك سلس البول؟»
6. **EN** Karim says, “No, I do not.”
   - FR : Karim répond : « Non. »
   - AR : يجيب Karim: «لا.»
7. **EN** Amine writes “Urination” in the file.
   - FR : Amine écrit « Miction (uriner) » dans le dossier.
   - AR : يكتب Amine «Miction (uriner)» في الملف.

### Expressions ciblées
- `gen:urination:v1` — **urination** · FR : Miction (uriner) · AR : التبول
- `gen:incontinence:v1` — **incontinence** · FR : Incontinence urinaire · AR : سلس البول
- `gen:frequency:v1` — **a frequency** · FR : Pollakiurie (urination fréquente) · AR : تكرار التبول

### Questions (acceptées / modèle / hint)
- **q1.** Does Karim have urination?
  - accepté : yes ; yes he does ; yes he has urination ; he has urination
  - modèle : Yes, he has urination.
- **q2.** Does he have incontinence?
  - accepté : no ; no he does not ; no he doesn't ; he does not have incontinence ; he doesn't
  - modèle : No, he does not have incontinence.
  - hint (si erreur) — FR : Attention : Karim n'a PAS incontinence urinaire.
- **q3.** Does he have urination or incontinence?
  - accepté : urination ; he has urination
  - modèle : He has urination.
- **q4.** What does Karim have?
  - accepté : urination ; he has urination
  - modèle : He has urination.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have frequency?
  - accepté : yes ; yes he does ; he has a frequency
  - modèle : Yes, he has a frequency.
- **Tt2.** Does he have urination?
  - accepté : no ; no he does not ; no he doesn't ; he does not have urination ; he doesn't
  - modèle : No, he does not have urination.
  - hint (si erreur) — FR : Attention : il n'a PAS miction (uriner).

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … urination. (have)` → has · modèle : He has urination.
- `They … not have incontinence. (do)` → do · modèle : They do not have incontinence.
- `I … urination. (have)` → have · modèle : I have urination.

### Productions
- **Reformulation :** Rapporte le cas de Karim en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has urination. He does not have incontinence.*
- **Transfert (contexte) :** Un autre patient a Pollakiurie (urination fréquente). Il n'a pas de Miction (uriner).
- Glossaire : Pollakiurie (urination fréquente) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a frequency. He does not have urination.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-039` — Expliquer le plan à Meriem / _Explaining the plan to Meriem_

**Objectif :** Comprendre un médecin qui explique Miction (uriner) et Pollakiurie (urination fréquente) à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient.
   - FR : Meriem est un·e patient·e.
   - AR : Meriem مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check urination.”
   - FR : « D'abord, nous vérifions Miction (uriner). »
   - AR : «أولًا، نتحقق من التبول.»
5. **EN** “Then, we check frequency.”
   - FR : « Ensuite, nous vérifions Pollakiurie (urination fréquente). »
   - AR : «ثم نتحقق من تكرار التبول.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Meriem says, “Thank you. I understand now.”
   - FR : Meriem dit : « Merci. Je comprends maintenant. »
   - AR : يقول Meriem: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:urination:v1` — **urination** · FR : Miction (uriner) · AR : التبول
- `gen:frequency:v1` — **a frequency** · FR : Pollakiurie (urination fréquente) · AR : تكرار التبول
- `gen:incontinence:v1` — **incontinence** · FR : Incontinence urinaire · AR : سلس البول

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : urination ; we check urination ; urination first ; the doctor checks urination first
  - modèle : First, the doctor checks urination.
- **q2.** What does the doctor check next?
  - accepté : frequency ; then we check frequency ; frequency next ; the doctor checks frequency next
  - modèle : Then, the doctor checks frequency.
- **q3.** Does the doctor check frequency before urination?
  - accepté : no ; no he does not ; no he doesn't ; he checks urination first
  - modèle : No, he checks urination first, then frequency.
  - hint (si erreur) — FR : Attention : Miction (uriner) vient D'ABORD, ensuite Pollakiurie (urination fréquente).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : meriem ; the patient ; the patient (meriem)
  - modèle : Meriem understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : incontinence ; incontinence first ; he checks incontinence first
  - modèle : First, the doctor checks incontinence.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check urination. Then, we check frequency.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Incontinence urinaire, ensuite Miction (uriner), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check incontinence. Then, we check urination.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-039` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Miction (uriner) / Incontinence urinaire, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has urination.
   - FR : Le patient du lit 3 a Miction (uriner).
   - AR : مريض السرير ٣ عنده التبول.
4. **EN** The patient in bed 5 has incontinence.
   - FR : Le patient du lit 5 a Incontinence urinaire.
   - AR : مريض السرير ٥ عنده سلس البول.
5. **EN** The patient in bed 5 does not have urination.
   - FR : Le patient du lit 5 n'a pas de Miction (uriner).
   - AR : مريض السرير ٥ ليس عنده التبول.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:urination:v1` — **urination** · FR : Miction (uriner) · AR : التبول
- `gen:incontinence:v1` — **incontinence** · FR : Incontinence urinaire · AR : سلس البول
- `gen:frequency:v1` — **a frequency** · FR : Pollakiurie (urination fréquente) · AR : تكرار التبول

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : urination ; the patient with urination
  - modèle : The patient in bed 3 has urination.
- **q2.** What does the patient in bed 5 have?
  - accepté : incontinence
  - modèle : He has incontinence.
- **q3.** Does the patient in bed 5 have urination?
  - accepté : no ; no he does not ; no he doesn't ; he does not have urination ; he doesn't
  - modèle : No, he does not have urination.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS miction (uriner) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : frequency ; a frequency
  - modèle : He has a frequency.
- **Tt2.** Does the patient in bed 9 have incontinence?
  - accepté : no ; no he does not ; no he doesn't ; he does not have incontinence ; he doesn't
  - modèle : No, he does not have incontinence.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS incontinence urinaire.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … urination and incontinence. (have)` → have · modèle : The patients have urination and incontinence.
- `The patient in bed 5 … not have urination. (do)` → does · modèle : The patient in bed 5 does not have urination.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has urination. The patient in bed 5 has incontinence.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Pollakiurie (urination fréquente), le lit 9 a Miction (uriner) et n'a pas Incontinence urinaire.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a frequency. The patient in bed 9 has urination.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
