# Relecture — Ear Nose & Throat (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-008` — Le cas de Meriem : Mal d'oreille / _Meriem: Earache_

**Objectif :** Comprendre un cas : mal d'oreille présent, acouphènes absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient at the clinic.
   - FR : Meriem est un·e patient·e à la clinique.
   - AR : Meriem مريض/مريضة في العيادة.
2. **EN** Meriem talks to Lina, a medical student.
   - FR : Meriem parle avec Lina, étudiant·e en médecine.
   - AR : Meriem يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has earache.
   - FR : Elle a Mal d'oreille.
   - AR : عنده ألم الأذن.
4. **EN** She does not have tinnitus.
   - FR : Elle n'a pas de Acouphènes.
   - AR : ليس عنده طنين الأذن.
5. **EN** Lina asks, “Do you have tinnitus?”
   - FR : Lina demande : « Avez-vous Acouphènes ? »
   - AR : يسأل Lina: «هل عندك طنين الأذن؟»
6. **EN** Meriem says, “No, I do not.”
   - FR : Meriem répond : « Non. »
   - AR : يجيب Meriem: «لا.»
7. **EN** Lina writes “Earache” in the file.
   - FR : Lina écrit « Mal d'oreille » dans le dossier.
   - AR : يكتب Lina «Mal d'oreille» في الملف.

### Expressions ciblées
- `gen:earache:v1` — **earache** · FR : Mal d'oreille · AR : ألم الأذن
- `gen:tinnitus:v1` — **tinnitus** · FR : Acouphènes · AR : طنين الأذن
- `gen:vertigo:v1` — **vertigo** · FR : Vertige (oreille interne) · AR : دوار الأذن الداخلية

### Questions (acceptées / modèle / hint)
- **q1.** Does Meriem have earache?
  - accepté : yes ; yes she does ; yes she has earache ; she has earache
  - modèle : Yes, she has earache.
- **q2.** Does she have tinnitus?
  - accepté : no ; no she does not ; no she doesn't ; she does not have tinnitus ; she doesn't
  - modèle : No, she does not have tinnitus.
  - hint (si erreur) — FR : Attention : Meriem n'a PAS acouphènes.
- **q3.** Does she have earache or tinnitus?
  - accepté : earache ; she has earache
  - modèle : She has earache.
- **q4.** What does Meriem have?
  - accepté : earache ; she has earache
  - modèle : She has earache.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have vertigo?
  - accepté : yes ; yes he does ; he has vertigo
  - modèle : Yes, he has vertigo.
- **Tt2.** Does he have earache?
  - accepté : no ; no he does not ; no he doesn't ; he does not have earache ; he doesn't
  - modèle : No, he does not have earache.
  - hint (si erreur) — FR : Attention : il n'a PAS mal d'oreille.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … earache. (have)` → has · modèle : She has earache.
- `They … not have tinnitus. (do)` → do · modèle : They do not have tinnitus.
- `I … earache. (have)` → have · modèle : I have earache.

### Productions
- **Reformulation :** Rapporte le cas de Meriem en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has earache. She does not have tinnitus.*
- **Transfert (contexte) :** Un autre patient a Vertige (oreille interne). Il n'a pas de Mal d'oreille.
- Glossaire : Vertige (oreille interne) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has vertigo. He does not have earache.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-008` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Mal d'oreille et Acouphènes à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check earache.”
   - FR : « D'abord, nous vérifions Mal d'oreille. »
   - AR : «أولًا، نتحقق من ألم الأذن.»
5. **EN** “Then, we check tinnitus.”
   - FR : « Ensuite, nous vérifions Acouphènes. »
   - AR : «ثم نتحقق من طنين الأذن.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:earache:v1` — **earache** · FR : Mal d'oreille · AR : ألم الأذن
- `gen:tinnitus:v1` — **tinnitus** · FR : Acouphènes · AR : طنين الأذن
- `gen:vertigo:v1` — **vertigo** · FR : Vertige (oreille interne) · AR : دوار الأذن الداخلية

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : earache ; we check earache ; earache first ; the doctor checks earache first
  - modèle : First, the doctor checks earache.
- **q2.** What does the doctor check next?
  - accepté : tinnitus ; then we check tinnitus ; tinnitus next ; the doctor checks tinnitus next
  - modèle : Then, the doctor checks tinnitus.
- **q3.** Does the doctor check tinnitus before earache?
  - accepté : no ; no he does not ; no he doesn't ; he checks earache first
  - modèle : No, he checks earache first, then tinnitus.
  - hint (si erreur) — FR : Attention : Mal d'oreille vient D'ABORD, ensuite Acouphènes.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : vertigo ; vertigo first ; he checks vertigo first
  - modèle : First, the doctor checks vertigo.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check earache. Then, we check tinnitus.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Vertige (oreille interne), ensuite Mal d'oreille, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check vertigo. Then, we check earache.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-008` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Mal d'oreille / Acouphènes, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has earache.
   - FR : Le patient du lit 3 a Mal d'oreille.
   - AR : مريض السرير ٣ عنده ألم الأذن.
4. **EN** The patient in bed 5 has tinnitus.
   - FR : Le patient du lit 5 a Acouphènes.
   - AR : مريض السرير ٥ عنده طنين الأذن.
5. **EN** The patient in bed 5 does not have earache.
   - FR : Le patient du lit 5 n'a pas de Mal d'oreille.
   - AR : مريض السرير ٥ ليس عنده ألم الأذن.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:earache:v1` — **earache** · FR : Mal d'oreille · AR : ألم الأذن
- `gen:tinnitus:v1` — **tinnitus** · FR : Acouphènes · AR : طنين الأذن
- `gen:vertigo:v1` — **vertigo** · FR : Vertige (oreille interne) · AR : دوار الأذن الداخلية

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : earache ; the patient with earache
  - modèle : The patient in bed 3 has earache.
- **q2.** What does the patient in bed 5 have?
  - accepté : tinnitus ; he has tinnitus
  - modèle : He has tinnitus.
- **q3.** Does the patient in bed 5 have earache?
  - accepté : no ; no he does not ; no he doesn't ; he does not have earache ; he doesn't
  - modèle : No, he does not have earache.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS mal d'oreille — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : vertigo ; he has vertigo
  - modèle : He has vertigo.
- **Tt2.** Does the patient in bed 9 have tinnitus?
  - accepté : no ; no he does not ; no he doesn't ; he does not have tinnitus ; he doesn't
  - modèle : No, he does not have tinnitus.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS acouphènes.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … earache and tinnitus. (have)` → have · modèle : The patients have earache and tinnitus.
- `The patient in bed 5 … not have earache. (do)` → does · modèle : The patient in bed 5 does not have earache.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has earache. The patient in bed 5 has tinnitus.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Vertige (oreille interne), le lit 9 a Mal d'oreille et n'a pas Acouphènes.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has vertigo. The patient in bed 9 has earache.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
