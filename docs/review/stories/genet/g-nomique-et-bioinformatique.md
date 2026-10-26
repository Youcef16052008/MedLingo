# Relecture — Génomique et Bioinformatique (genet)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `genet-case-002` — Le cas de Youssef : SNP (Polymorphisme nucléotidique simple) anormal(e) / _Youssef: the SNP is not normal_

**Objectif :** Comprendre des résultats : Génomique normal(e), SNP (Polymorphisme nucléotidique simple) anormal(e).
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
4. **EN** The doctor says, “The genomics is normal.”
   - FR : Le médecin dit : « Génomique est normal(e). »
   - AR : يقول الطبيب: «علم الجينومات طبيعي/طبيعية.»
5. **EN** “But the SNP is not normal.”
   - FR : « Mais SNP (Polymorphisme nucléotidique simple) n'est pas normal(e). »
   - AR : «لكن التعدد الشكلي للنيوكليوتيد الواحد غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the SNP normal?”
   - FR : Lina demande : « SNP (Polymorphisme nucléotidique simple) est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل التعدد الشكلي للنيوكليوتيد الواحد طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:genomics:v1` — **genomics** · FR : Génomique · AR : علم الجينومات
- `gen:human-genome-project:v1` — **a human genome project** · FR : Projet Génome Humain · AR : مشروع الجينوم البشري
- `gen:snp:v1` — **a SNP** · FR : SNP (Polymorphisme nucléotidique simple) · AR : التعدد الشكلي للنيوكليوتيد الواحد

### Questions (acceptées / modèle / hint)
- **q1.** Is the genomics normal?
  - accepté : yes ; yes it is ; the genomics is normal ; it is normal
  - modèle : Yes, the genomics is normal.
- **q2.** Is the SNP normal?
  - accepté : no ; no it is not ; no it isn't ; the SNP is not normal ; it is not normal ; it isn't normal
  - modèle : No, the SNP is not normal.
  - hint (si erreur) — FR : Attention : SNP (Polymorphisme nucléotidique simple) n'est PAS normal(e).
- **q3.** Which one is not normal: the genomics or the SNP?
  - accepté : SNP ; the SNP
  - modèle : The SNP is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the human genome project normal?
  - accepté : yes ; yes it is ; the human genome project is normal
  - modèle : Yes, the human genome project is normal.
- **Tt2.** Is the genomics normal?
  - accepté : no ; no it is not ; no it isn't ; the genomics is not normal ; it is not normal
  - modèle : No, the genomics is not normal.
  - hint (si erreur) — FR : Attention : Génomique n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The genomics … normal. (be)` → is · modèle : The genomics is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The genomics is… / The SNP is not… — modèle : *The genomics is normal. The SNP is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Projet Génome Humain normal(e), mais Génomique anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The human genome project is normal. The genomics is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `genet-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Génomique et Projet Génome Humain à un patient.
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
4. **EN** “First, we check genomics.”
   - FR : « D'abord, nous vérifions Génomique. »
   - AR : «أولًا، نتحقق من علم الجينومات.»
5. **EN** “Then, we check human genome project.”
   - FR : « Ensuite, nous vérifions Projet Génome Humain. »
   - AR : «ثم نتحقق من مشروع الجينوم البشري.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:genomics:v1` — **genomics** · FR : Génomique · AR : علم الجينومات
- `gen:human-genome-project:v1` — **a human genome project** · FR : Projet Génome Humain · AR : مشروع الجينوم البشري
- `gen:snp:v1` — **a SNP** · FR : SNP (Polymorphisme nucléotidique simple) · AR : التعدد الشكلي للنيوكليوتيد الواحد

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : genomics ; we check genomics ; genomics first ; the doctor checks genomics first
  - modèle : First, the doctor checks genomics.
- **q2.** What does the doctor check next?
  - accepté : human genome project ; then we check human genome project ; human genome project next ; the doctor checks human genome project next
  - modèle : Then, the doctor checks human genome project.
- **q3.** Does the doctor check human genome project before genomics?
  - accepté : no ; no he does not ; no he doesn't ; he checks genomics first
  - modèle : No, he checks genomics first, then human genome project.
  - hint (si erreur) — FR : Attention : Génomique vient D'ABORD, ensuite Projet Génome Humain.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : SNP ; SNP first ; he checks SNP first
  - modèle : First, the doctor checks SNP.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check genomics. Then, we check human genome project.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord SNP (Polymorphisme nucléotidique simple), ensuite Génomique, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check SNP. Then, we check genomics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `genet-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Génomique / Projet Génome Humain, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the genomics.
   - FR : Le patient du lit 3 a Génomique.
   - AR : مريض السرير ٣ عنده علم الجينومات.
4. **EN** The patient in bed 5 has a problem with the human genome project.
   - FR : Le patient du lit 5 a Projet Génome Humain.
   - AR : مريض السرير ٥ عنده مشروع الجينوم البشري.
5. **EN** The patient in bed 5 does not have a problem with the genomics.
   - FR : Le patient du lit 5 n'a pas de Génomique.
   - AR : مريض السرير ٥ ليس عنده علم الجينومات.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:genomics:v1` — **genomics** · FR : Génomique · AR : علم الجينومات
- `gen:human-genome-project:v1` — **a human genome project** · FR : Projet Génome Humain · AR : مشروع الجينوم البشري
- `gen:snp:v1` — **a SNP** · FR : SNP (Polymorphisme nucléotidique simple) · AR : التعدد الشكلي للنيوكليوتيد الواحد

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : genomics ; the patient with genomics
  - modèle : The patient in bed 3 has genomics.
- **q2.** What does the patient in bed 5 have?
  - accepté : human genome project ; a problem with the human genome project
  - modèle : He has a problem with the human genome project.
- **q3.** Does the patient in bed 5 have genomics?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the genomics ; he doesn't
  - modèle : No, he does not have a problem with the genomics.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS génomique — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : SNP ; a problem with the SNP
  - modèle : He has a problem with the SNP.
- **Tt2.** Does the patient in bed 9 have human genome project?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the human genome project ; he doesn't
  - modèle : No, he does not have a problem with the human genome project.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS projet génome humain.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … genomics and a human genome project. (have)` → have · modèle : The patients have genomics and a human genome project.
- `The patient in bed 5 … not have genomics. (do)` → does · modèle : The patient in bed 5 does not have genomics.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the genomics. The patient in bed 5 has a problem with the human genome project.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a SNP (Polymorphisme nucléotidique simple), le lit 9 a Génomique et n'a pas Projet Génome Humain.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the SNP. The patient in bed 9 has a problem with the genomics.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
