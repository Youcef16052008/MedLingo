# Relecture — Surgery & Post-Operative Care (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-036` — Le cas de Sara : Anesthésie générale / _Sara: General anesthesia_

**Objectif :** Comprendre un cas : anesthésie générale présent, anesthésie locale absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient at the clinic.
   - FR : Sara est un·e patient·e à la clinique.
   - AR : Sara مريض/مريضة في العيادة.
2. **EN** Sara talks to Lina, a medical student.
   - FR : Sara parle avec Lina, étudiant·e en médecine.
   - AR : Sara يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a general anesthesia.
   - FR : Elle a Anesthésie générale.
   - AR : عنده تخدير عام.
4. **EN** She does not have a local anesthesia.
   - FR : Elle n'a pas de Anesthésie locale.
   - AR : ليس عنده تخدير موضعي.
5. **EN** Lina asks, “Do you have a local anesthesia?”
   - FR : Lina demande : « Avez-vous Anesthésie locale ? »
   - AR : يسأل Lina: «هل عندك تخدير موضعي؟»
6. **EN** Sara says, “No, I do not.”
   - FR : Sara répond : « Non. »
   - AR : يجيب Sara: «لا.»
7. **EN** Lina writes “General anesthesia” in the file.
   - FR : Lina écrit « Anesthésie générale » dans le dossier.
   - AR : يكتب Lina «Anesthésie générale» في الملف.

### Expressions ciblées
- `gen:general-anesthesia:v1` — **a general anesthesia** · FR : Anesthésie générale · AR : تخدير عام
- `gen:local-anesthesia:v1` — **a local anesthesia** · FR : Anesthésie locale · AR : تخدير موضعي
- `gen:recovery-room:v1` — **a recovery room** · FR : Salle de réveil · AR : غرفة الإفاقة

### Questions (acceptées / modèle / hint)
- **q1.** Does Sara have general anesthesia?
  - accepté : yes ; yes she does ; yes she has a general anesthesia ; she has a general anesthesia
  - modèle : Yes, she has a general anesthesia.
- **q2.** Does she have local anesthesia?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a local anesthesia ; she doesn't
  - modèle : No, she does not have a local anesthesia.
  - hint (si erreur) — FR : Attention : Sara n'a PAS anesthésie locale.
- **q3.** Does she have general anesthesia or local anesthesia?
  - accepté : general anesthesia ; a general anesthesia ; she has a general anesthesia
  - modèle : She has a general anesthesia.
- **q4.** What does Sara have?
  - accepté : general anesthesia ; a general anesthesia ; she has a general anesthesia
  - modèle : She has a general anesthesia.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have recovery room?
  - accepté : yes ; yes he does ; he has a recovery room
  - modèle : Yes, he has a recovery room.
- **Tt2.** Does he have general anesthesia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a general anesthesia ; he doesn't
  - modèle : No, he does not have a general anesthesia.
  - hint (si erreur) — FR : Attention : il n'a PAS anesthésie générale.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a general anesthesia. (have)` → has · modèle : She has a general anesthesia.
- `They … not have a local anesthesia. (do)` → do · modèle : They do not have a local anesthesia.
- `I … a general anesthesia. (have)` → have · modèle : I have a general anesthesia.

### Productions
- **Reformulation :** Rapporte le cas de Sara en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a general anesthesia. She does not have a local anesthesia.*
- **Transfert (contexte) :** Un autre patient a Salle de réveil. Il n'a pas de Anesthésie générale.
- Glossaire : Salle de réveil : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a recovery room. He does not have a general anesthesia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-036` — Expliquer le plan à Youssef / _Explaining the plan to Youssef_

**Objectif :** Comprendre un médecin qui explique Anesthésie générale et Anesthésie locale à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient.
   - FR : Youssef est un·e patient·e.
   - AR : Youssef مريض/مريضة.
2. **EN** He is at the hospital.
   - FR : Il est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to him.
   - FR : Le médecin explique le plan à lui.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check general anesthesia.”
   - FR : « D'abord, nous vérifions Anesthésie générale. »
   - AR : «أولًا، نتحقق من تخدير عام.»
5. **EN** “Then, we check local anesthesia.”
   - FR : « Ensuite, nous vérifions Anesthésie locale. »
   - AR : «ثم نتحقق من تخدير موضعي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Youssef says, “Thank you. I understand now.”
   - FR : Youssef dit : « Merci. Je comprends maintenant. »
   - AR : يقول Youssef: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:general-anesthesia:v1` — **a general anesthesia** · FR : Anesthésie générale · AR : تخدير عام
- `gen:local-anesthesia:v1` — **a local anesthesia** · FR : Anesthésie locale · AR : تخدير موضعي
- `gen:recovery-room:v1` — **a recovery room** · FR : Salle de réveil · AR : غرفة الإفاقة

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : general anesthesia ; we check general anesthesia ; general anesthesia first ; the doctor checks general anesthesia first
  - modèle : First, the doctor checks general anesthesia.
- **q2.** What does the doctor check next?
  - accepté : local anesthesia ; then we check local anesthesia ; local anesthesia next ; the doctor checks local anesthesia next
  - modèle : Then, the doctor checks local anesthesia.
- **q3.** Does the doctor check local anesthesia before general anesthesia?
  - accepté : no ; no he does not ; no he doesn't ; he checks general anesthesia first
  - modèle : No, he checks general anesthesia first, then local anesthesia.
  - hint (si erreur) — FR : Attention : Anesthésie générale vient D'ABORD, ensuite Anesthésie locale.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : youssef ; the patient ; the patient (youssef)
  - modèle : Youssef understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : recovery room ; recovery room first ; he checks recovery room first
  - modèle : First, the doctor checks recovery room.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check general anesthesia. Then, we check local anesthesia.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Salle de réveil, ensuite Anesthésie générale, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check recovery room. Then, we check general anesthesia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-036` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Anesthésie générale / Anesthésie locale, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a general anesthesia.
   - FR : Le patient du lit 3 a Anesthésie générale.
   - AR : مريض السرير ٣ عنده تخدير عام.
4. **EN** The patient in bed 5 has a local anesthesia.
   - FR : Le patient du lit 5 a Anesthésie locale.
   - AR : مريض السرير ٥ عنده تخدير موضعي.
5. **EN** The patient in bed 5 does not have a general anesthesia.
   - FR : Le patient du lit 5 n'a pas de Anesthésie générale.
   - AR : مريض السرير ٥ ليس عنده تخدير عام.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:general-anesthesia:v1` — **a general anesthesia** · FR : Anesthésie générale · AR : تخدير عام
- `gen:local-anesthesia:v1` — **a local anesthesia** · FR : Anesthésie locale · AR : تخدير موضعي
- `gen:recovery-room:v1` — **a recovery room** · FR : Salle de réveil · AR : غرفة الإفاقة

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : general anesthesia ; a general anesthesia ; the patient with general anesthesia
  - modèle : The patient in bed 3 has a general anesthesia.
- **q2.** What does the patient in bed 5 have?
  - accepté : local anesthesia ; a local anesthesia ; he has a local anesthesia
  - modèle : He has a local anesthesia.
- **q3.** Does the patient in bed 5 have general anesthesia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a general anesthesia ; he doesn't
  - modèle : No, he does not have a general anesthesia.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS anesthésie générale — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : recovery room ; a recovery room ; he has a recovery room
  - modèle : He has a recovery room.
- **Tt2.** Does the patient in bed 9 have local anesthesia?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a local anesthesia ; he doesn't
  - modèle : No, he does not have a local anesthesia.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS anesthésie locale.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a general anesthesia and a local anesthesia. (have)` → have · modèle : The patients have a general anesthesia and a local anesthesia.
- `The patient in bed 5 … not have a general anesthesia. (do)` → does · modèle : The patient in bed 5 does not have a general anesthesia.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a general anesthesia. The patient in bed 5 has a local anesthesia.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Salle de réveil, le lit 9 a Anesthésie générale et n'a pas Anesthésie locale.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a recovery room. The patient in bed 9 has a general anesthesia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
