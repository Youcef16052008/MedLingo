# Relecture — Termes Complémentaires (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-013` — Le cas de Omar : Chorioamniotite / _Omar: Chorioamnionitis_

**Objectif :** Comprendre un cas : chorioamniotite présent, syndrome de détresse respiratoire (sdr) absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Omar is a patient at the clinic.
   - FR : Omar est un·e patient·e à la clinique.
   - AR : Omar مريض/مريضة في العيادة.
2. **EN** Omar talks to Amine, a medical student.
   - FR : Omar parle avec Amine, étudiant·e en médecine.
   - AR : Omar يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has chorioamnionitis.
   - FR : Il a Chorioamniotite.
   - AR : عنده التهاب الغشاء المشيمي والسلوي.
4. **EN** He does not have respiratory distress syndrome.
   - FR : Il n'a pas de Syndrome de détresse respiratoire (SDR).
   - AR : ليس عنده متلازمة ضائقة الجهاز التنفسي.
5. **EN** Amine asks, “Do you have respiratory distress syndrome?”
   - FR : Amine demande : « Avez-vous Syndrome de détresse respiratoire (SDR) ? »
   - AR : يسأل Amine: «هل عندك متلازمة ضائقة الجهاز التنفسي؟»
6. **EN** Omar says, “No, I do not.”
   - FR : Omar répond : « Non. »
   - AR : يجيب Omar: «لا.»
7. **EN** Amine writes “Chorioamnionitis” in the file.
   - FR : Amine écrit « Chorioamniotite » dans le dossier.
   - AR : يكتب Amine «Chorioamniotite» في الملف.

### Expressions ciblées
- `gen:chorioamnionitis:v1` — **chorioamnionitis** · FR : Chorioamniotite · AR : التهاب الغشاء المشيمي والسلوي
- `gen:respiratory-distress-syndrome:v1` — **respiratory distress syndrome** · FR : Syndrome de détresse respiratoire (SDR) · AR : متلازمة ضائقة الجهاز التنفسي
- `gen:lateral-symmetry:v1` — **a lateral symmetry** · FR : Symétrie latérale · AR : التماثل الجانبي

### Questions (acceptées / modèle / hint)
- **q1.** Does Omar have chorioamnionitis?
  - accepté : yes ; yes he does ; yes he has chorioamnionitis ; he has chorioamnionitis
  - modèle : Yes, he has chorioamnionitis.
- **q2.** Does he have respiratory distress syndrome?
  - accepté : no ; no he does not ; no he doesn't ; he does not have respiratory distress syndrome ; he doesn't
  - modèle : No, he does not have respiratory distress syndrome.
  - hint (si erreur) — FR : Attention : Omar n'a PAS syndrome de détresse respiratoire (sdr).
- **q3.** Does he have chorioamnionitis or respiratory distress syndrome?
  - accepté : chorioamnionitis ; he has chorioamnionitis
  - modèle : He has chorioamnionitis.
- **q4.** What does Omar have?
  - accepté : chorioamnionitis ; he has chorioamnionitis
  - modèle : He has chorioamnionitis.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have lateral symmetry?
  - accepté : yes ; yes he does ; he has a lateral symmetry
  - modèle : Yes, he has a lateral symmetry.
- **Tt2.** Does he have chorioamnionitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have chorioamnionitis ; he doesn't
  - modèle : No, he does not have chorioamnionitis.
  - hint (si erreur) — FR : Attention : il n'a PAS chorioamniotite.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … chorioamnionitis. (have)` → has · modèle : He has chorioamnionitis.
- `They … not have respiratory distress syndrome. (do)` → do · modèle : They do not have respiratory distress syndrome.
- `I … chorioamnionitis. (have)` → have · modèle : I have chorioamnionitis.

### Productions
- **Reformulation :** Rapporte le cas de Omar en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has chorioamnionitis. He does not have respiratory distress syndrome.*
- **Transfert (contexte) :** Un autre patient a Symétrie latérale. Il n'a pas de Chorioamniotite.
- Glossaire : Symétrie latérale : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a lateral symmetry. He does not have chorioamnionitis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-013` — Expliquer le plan à Amel / _Explaining the plan to Amel_

**Objectif :** Comprendre un médecin qui explique Symétrie latérale et Situs inversus à un patient.
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
4. **EN** “First, we check lateral symmetry.”
   - FR : « D'abord, nous vérifions Symétrie latérale. »
   - AR : «أولًا، نتحقق من التماثل الجانبي.»
5. **EN** “Then, we check situs inversus.”
   - FR : « Ensuite, nous vérifions Situs inversus. »
   - AR : «ثم نتحقق من انعكاس الأحشاء.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Amel says, “Thank you. I understand now.”
   - FR : Amel dit : « Merci. Je comprends maintenant. »
   - AR : يقول Amel: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:lateral-symmetry:v1` — **a lateral symmetry** · FR : Symétrie latérale · AR : التماثل الجانبي
- `gen:situs-inversus:v1` — **situs inversus** · FR : Situs inversus · AR : انعكاس الأحشاء
- `gen:embryogenesis:v1` — **embryogenesis** · FR : Embryogenèse · AR : تكوين الجنين

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : lateral symmetry ; we check lateral symmetry ; lateral symmetry first ; the doctor checks lateral symmetry first
  - modèle : First, the doctor checks lateral symmetry.
- **q2.** What does the doctor check next?
  - accepté : situs inversus ; then we check situs inversus ; situs inversus next ; the doctor checks situs inversus next
  - modèle : Then, the doctor checks situs inversus.
- **q3.** Does the doctor check situs inversus before lateral symmetry?
  - accepté : no ; no he does not ; no he doesn't ; he checks lateral symmetry first
  - modèle : No, he checks lateral symmetry first, then situs inversus.
  - hint (si erreur) — FR : Attention : Symétrie latérale vient D'ABORD, ensuite Situs inversus.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : amel ; the patient ; the patient (amel)
  - modèle : Amel understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : embryogenesis ; embryogenesis first ; he checks embryogenesis first
  - modèle : First, the doctor checks embryogenesis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check lateral symmetry. Then, we check situs inversus.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Embryogenèse, ensuite Symétrie latérale, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check embryogenesis. Then, we check lateral symmetry.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-013` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Chorioamniotite / Syndrome de détresse respiratoire (SDR), lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has chorioamnionitis.
   - FR : Le patient du lit 3 a Chorioamniotite.
   - AR : مريض السرير ٣ عنده التهاب الغشاء المشيمي والسلوي.
4. **EN** The patient in bed 5 has respiratory distress syndrome.
   - FR : Le patient du lit 5 a Syndrome de détresse respiratoire (SDR).
   - AR : مريض السرير ٥ عنده متلازمة ضائقة الجهاز التنفسي.
5. **EN** The patient in bed 5 does not have chorioamnionitis.
   - FR : Le patient du lit 5 n'a pas de Chorioamniotite.
   - AR : مريض السرير ٥ ليس عنده التهاب الغشاء المشيمي والسلوي.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:chorioamnionitis:v1` — **chorioamnionitis** · FR : Chorioamniotite · AR : التهاب الغشاء المشيمي والسلوي
- `gen:respiratory-distress-syndrome:v1` — **respiratory distress syndrome** · FR : Syndrome de détresse respiratoire (SDR) · AR : متلازمة ضائقة الجهاز التنفسي
- `gen:lateral-symmetry:v1` — **a lateral symmetry** · FR : Symétrie latérale · AR : التماثل الجانبي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : chorioamnionitis ; the patient with chorioamnionitis
  - modèle : The patient in bed 3 has chorioamnionitis.
- **q2.** What does the patient in bed 5 have?
  - accepté : respiratory distress syndrome
  - modèle : He has respiratory distress syndrome.
- **q3.** Does the patient in bed 5 have chorioamnionitis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have chorioamnionitis ; he doesn't
  - modèle : No, he does not have chorioamnionitis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS chorioamniotite — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : lateral symmetry ; a lateral symmetry
  - modèle : He has a lateral symmetry.
- **Tt2.** Does the patient in bed 9 have respiratory distress syndrome?
  - accepté : no ; no he does not ; no he doesn't ; he does not have respiratory distress syndrome ; he doesn't
  - modèle : No, he does not have respiratory distress syndrome.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS syndrome de détresse respiratoire (sdr).

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … chorioamnionitis and respiratory distress syndrome. (have)` → have · modèle : The patients have chorioamnionitis and respiratory distress syndrome.
- `The patient in bed 5 … not have chorioamnionitis. (do)` → does · modèle : The patient in bed 5 does not have chorioamnionitis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has chorioamnionitis. The patient in bed 5 has respiratory distress syndrome.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Symétrie latérale, le lit 9 a Chorioamniotite et n'a pas Syndrome de détresse respiratoire (SDR).
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a lateral symmetry. The patient in bed 9 has chorioamnionitis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
