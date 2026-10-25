# Relecture — Radiology & Imaging (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-032` — Le cas de Meriem : Radiologue / _Meriem: Radiologist_

**Objectif :** Comprendre un cas : radiologue présent, produit de contraste absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Meriem is a patient at the clinic.
   - FR : Meriem est un·e patient·e à la clinique.
   - AR : Meriem مريض/مريضة في العيادة.
2. **EN** Meriem talks to Lina, a medical student.
   - FR : Meriem parle avec Lina, étudiant·e en médecine.
   - AR : Meriem يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has a radiologist.
   - FR : Elle a Radiologue.
   - AR : عنده طبيب الأشعة.
4. **EN** She does not have a contrast dye.
   - FR : Elle n'a pas de Produit de contraste.
   - AR : ليس عنده صبغة التباين.
5. **EN** Lina asks, “Do you have a contrast dye?”
   - FR : Lina demande : « Avez-vous Produit de contraste ? »
   - AR : يسأل Lina: «هل عندك صبغة التباين؟»
6. **EN** Meriem says, “No, I do not.”
   - FR : Meriem répond : « Non. »
   - AR : يجيب Meriem: «لا.»
7. **EN** Lina writes “Radiologist” in the file.
   - FR : Lina écrit « Radiologue » dans le dossier.
   - AR : يكتب Lina «Radiologue» في الملف.

### Expressions ciblées
- `gen:radiologist:v1` — **a radiologist** · FR : Radiologue · AR : طبيب الأشعة
- `gen:contrast-dye:v1` — **a contrast dye** · FR : Produit de contraste · AR : صبغة التباين
- `gen:pet-scan:v1` — **a PET scan** · FR : TEP scan · AR : التصوير بالإصدار البوزيتروني

### Questions (acceptées / modèle / hint)
- **q1.** Does Meriem have radiologist?
  - accepté : yes ; yes she does ; yes she has a radiologist ; she has a radiologist
  - modèle : Yes, she has a radiologist.
- **q2.** Does she have contrast dye?
  - accepté : no ; no she does not ; no she doesn't ; she does not have a contrast dye ; she doesn't
  - modèle : No, she does not have a contrast dye.
  - hint (si erreur) — FR : Attention : Meriem n'a PAS produit de contraste.
- **q3.** Does she have radiologist or contrast dye?
  - accepté : radiologist ; a radiologist ; she has a radiologist
  - modèle : She has a radiologist.
- **q4.** What does Meriem have?
  - accepté : radiologist ; a radiologist ; she has a radiologist
  - modèle : She has a radiologist.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have PET scan?
  - accepté : yes ; yes he does ; he has a PET scan
  - modèle : Yes, he has a PET scan.
- **Tt2.** Does he have radiologist?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a radiologist ; he doesn't
  - modèle : No, he does not have a radiologist.
  - hint (si erreur) — FR : Attention : il n'a PAS radiologue.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … a radiologist. (have)` → has · modèle : She has a radiologist.
- `They … not have a contrast dye. (do)` → do · modèle : They do not have a contrast dye.
- `I … a radiologist. (have)` → have · modèle : I have a radiologist.

### Productions
- **Reformulation :** Rapporte le cas de Meriem en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has a radiologist. She does not have a contrast dye.*
- **Transfert (contexte) :** Un autre patient a TEP scan. Il n'a pas de Radiologue.
- Glossaire : TEP scan : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a PET scan. He does not have a radiologist.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-032` — Expliquer le plan à Omar / _Explaining the plan to Omar_

**Objectif :** Comprendre un médecin qui explique Radiologue et Produit de contraste à un patient.
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
4. **EN** “First, we check radiologist.”
   - FR : « D'abord, nous vérifions Radiologue. »
   - AR : «أولًا، نتحقق من طبيب الأشعة.»
5. **EN** “Then, we check contrast dye.”
   - FR : « Ensuite, nous vérifions Produit de contraste. »
   - AR : «ثم نتحقق من صبغة التباين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Omar says, “Thank you. I understand now.”
   - FR : Omar dit : « Merci. Je comprends maintenant. »
   - AR : يقول Omar: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:radiologist:v1` — **a radiologist** · FR : Radiologue · AR : طبيب الأشعة
- `gen:contrast-dye:v1` — **a contrast dye** · FR : Produit de contraste · AR : صبغة التباين
- `gen:pet-scan:v1` — **a PET scan** · FR : TEP scan · AR : التصوير بالإصدار البوزيتروني

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : radiologist ; we check radiologist ; radiologist first ; the doctor checks radiologist first
  - modèle : First, the doctor checks radiologist.
- **q2.** What does the doctor check next?
  - accepté : contrast dye ; then we check contrast dye ; contrast dye next ; the doctor checks contrast dye next
  - modèle : Then, the doctor checks contrast dye.
- **q3.** Does the doctor check contrast dye before radiologist?
  - accepté : no ; no he does not ; no he doesn't ; he checks radiologist first
  - modèle : No, he checks radiologist first, then contrast dye.
  - hint (si erreur) — FR : Attention : Radiologue vient D'ABORD, ensuite Produit de contraste.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : omar ; the patient ; the patient (omar)
  - modèle : Omar understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : PET scan ; PET scan first ; he checks PET scan first
  - modèle : First, the doctor checks PET scan.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check radiologist. Then, we check contrast dye.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord TEP scan, ensuite Radiologue, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check PET scan. Then, we check radiologist.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-032` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Radiologue / Produit de contraste, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a radiologist.
   - FR : Le patient du lit 3 a Radiologue.
   - AR : مريض السرير ٣ عنده طبيب الأشعة.
4. **EN** The patient in bed 5 has a contrast dye.
   - FR : Le patient du lit 5 a Produit de contraste.
   - AR : مريض السرير ٥ عنده صبغة التباين.
5. **EN** The patient in bed 5 does not have a radiologist.
   - FR : Le patient du lit 5 n'a pas de Radiologue.
   - AR : مريض السرير ٥ ليس عنده طبيب الأشعة.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:radiologist:v1` — **a radiologist** · FR : Radiologue · AR : طبيب الأشعة
- `gen:contrast-dye:v1` — **a contrast dye** · FR : Produit de contraste · AR : صبغة التباين
- `gen:pet-scan:v1` — **a PET scan** · FR : TEP scan · AR : التصوير بالإصدار البوزيتروني

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : radiologist ; a radiologist ; the patient with radiologist
  - modèle : The patient in bed 3 has a radiologist.
- **q2.** What does the patient in bed 5 have?
  - accepté : contrast dye ; a contrast dye ; he has a contrast dye
  - modèle : He has a contrast dye.
- **q3.** Does the patient in bed 5 have radiologist?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a radiologist ; he doesn't
  - modèle : No, he does not have a radiologist.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS radiologue — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : PET scan ; a PET scan ; he has a PET scan
  - modèle : He has a PET scan.
- **Tt2.** Does the patient in bed 9 have contrast dye?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a contrast dye ; he doesn't
  - modèle : No, he does not have a contrast dye.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS produit de contraste.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a radiologist and a contrast dye. (have)` → have · modèle : The patients have a radiologist and a contrast dye.
- `The patient in bed 5 … not have a radiologist. (do)` → does · modèle : The patient in bed 5 does not have a radiologist.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a radiologist. The patient in bed 5 has a contrast dye.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a TEP scan, le lit 9 a Radiologue et n'a pas Produit de contraste.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a PET scan. The patient in bed 9 has a radiologist.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
