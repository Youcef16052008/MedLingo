# Relecture — Gastroenterology & Digestion (clinical_en)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `clinical-en-case-011` — Le cas de Bilal : Brûlures d'estomac (Reflux) / _Bilal: Heartburn_

**Objectif :** Comprendre un cas : brûlures d'estomac (reflux) présent, ballonnement absent.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Bilal is a patient at the clinic.
   - FR : Bilal est un·e patient·e à la clinique.
   - AR : Bilal مريض/مريضة في العيادة.
2. **EN** Bilal talks to Amine, a medical student.
   - FR : Bilal parle avec Amine, étudiant·e en médecine.
   - AR : Bilal يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has heartburn.
   - FR : Il a Brûlures d'estomac (Reflux).
   - AR : عنده حرقة المعدة.
4. **EN** He does not have bloating.
   - FR : Il n'a pas de Ballonnement.
   - AR : ليس عنده الانتفاخ.
5. **EN** Amine asks, “Do you have bloating?”
   - FR : Amine demande : « Avez-vous Ballonnement ? »
   - AR : يسأل Amine: «هل عندك الانتفاخ؟»
6. **EN** Bilal says, “No, I do not.”
   - FR : Bilal répond : « Non. »
   - AR : يجيب Bilal: «لا.»
7. **EN** Amine writes “Heartburn” in the file.
   - FR : Amine écrit « Brûlures d'estomac (Reflux) » dans le dossier.
   - AR : يكتب Amine «Brûlures d'estomac (Reflux)» في الملف.

### Expressions ciblées
- `gen:heartburn:v1` — **heartburn** · FR : Brûlures d'estomac (Reflux) · AR : حرقة المعدة
- `gen:bloating:v1` — **bloating** · FR : Ballonnement · AR : الانتفاخ
- `gen:gerd:v1` — **a GERD** · FR : RGO (Reflux gastro-œsophagien) · AR : الارتجاع المعدي المريئي

### Questions (acceptées / modèle / hint)
- **q1.** Does Bilal have heartburn?
  - accepté : yes ; yes he does ; yes he has heartburn ; he has heartburn
  - modèle : Yes, he has heartburn.
- **q2.** Does he have bloating?
  - accepté : no ; no he does not ; no he doesn't ; he does not have bloating ; he doesn't
  - modèle : No, he does not have bloating.
  - hint (si erreur) — FR : Attention : Bilal n'a PAS ballonnement.
- **q3.** Does he have heartburn or bloating?
  - accepté : heartburn ; he has heartburn
  - modèle : He has heartburn.
- **q4.** What does Bilal have?
  - accepté : heartburn ; he has heartburn
  - modèle : He has heartburn.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have GERD?
  - accepté : yes ; yes he does ; he has a GERD
  - modèle : Yes, he has a GERD.
- **Tt2.** Does he have heartburn?
  - accepté : no ; no he does not ; no he doesn't ; he does not have heartburn ; he doesn't
  - modèle : No, he does not have heartburn.
  - hint (si erreur) — FR : Attention : il n'a PAS brûlures d'estomac (reflux).

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … heartburn. (have)` → has · modèle : He has heartburn.
- `They … not have bloating. (do)` → do · modèle : They do not have bloating.
- `I … heartburn. (have)` → have · modèle : I have heartburn.

### Productions
- **Reformulation :** Rapporte le cas de Bilal en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has heartburn. He does not have bloating.*
- **Transfert (contexte) :** Un autre patient a RGO (Reflux gastro-œsophagien). Il n'a pas de Brûlures d'estomac (Reflux).
- Glossaire : RGO (Reflux gastro-œsophagien) : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a GERD. He does not have heartburn.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `clinical-en-explain-011` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Brûlures d'estomac (Reflux) et RGO (Reflux gastro-œsophagien) à un patient.
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Nadia is a patient.
   - FR : Nadia est un·e patient·e.
   - AR : Nadia مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check heartburn.”
   - FR : « D'abord, nous vérifions Brûlures d'estomac (Reflux). »
   - AR : «أولًا، نتحقق من حرقة المعدة.»
5. **EN** “Then, we check GERD.”
   - FR : « Ensuite, nous vérifions RGO (Reflux gastro-œsophagien). »
   - AR : «ثم نتحقق من الارتجاع المعدي المريئي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:heartburn:v1` — **heartburn** · FR : Brûlures d'estomac (Reflux) · AR : حرقة المعدة
- `gen:gerd:v1` — **a GERD** · FR : RGO (Reflux gastro-œsophagien) · AR : الارتجاع المعدي المريئي
- `gen:bloating:v1` — **bloating** · FR : Ballonnement · AR : الانتفاخ

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : heartburn ; we check heartburn ; heartburn first ; the doctor checks heartburn first
  - modèle : First, the doctor checks heartburn.
- **q2.** What does the doctor check next?
  - accepté : GERD ; then we check GERD ; GERD next ; the doctor checks GERD next
  - modèle : Then, the doctor checks GERD.
- **q3.** Does the doctor check GERD before heartburn?
  - accepté : no ; no he does not ; no he doesn't ; he checks heartburn first
  - modèle : No, he checks heartburn first, then GERD.
  - hint (si erreur) — FR : Attention : Brûlures d'estomac (Reflux) vient D'ABORD, ensuite RGO (Reflux gastro-œsophagien).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : bloating ; bloating first ; he checks bloating first
  - modèle : First, the doctor checks bloating.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check heartburn. Then, we check GERD.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Ballonnement, ensuite Brûlures d'estomac (Reflux), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check bloating. Then, we check heartburn.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `clinical-en-handover-011` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Brûlures d'estomac (Reflux) / Ballonnement, lits 3 et 5).
**Archétype :** en · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has heartburn.
   - FR : Le patient du lit 3 a Brûlures d'estomac (Reflux).
   - AR : مريض السرير ٣ عنده حرقة المعدة.
4. **EN** The patient in bed 5 has bloating.
   - FR : Le patient du lit 5 a Ballonnement.
   - AR : مريض السرير ٥ عنده الانتفاخ.
5. **EN** The patient in bed 5 does not have heartburn.
   - FR : Le patient du lit 5 n'a pas de Brûlures d'estomac (Reflux).
   - AR : مريض السرير ٥ ليس عنده حرقة المعدة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:heartburn:v1` — **heartburn** · FR : Brûlures d'estomac (Reflux) · AR : حرقة المعدة
- `gen:bloating:v1` — **bloating** · FR : Ballonnement · AR : الانتفاخ
- `gen:gerd:v1` — **a GERD** · FR : RGO (Reflux gastro-œsophagien) · AR : الارتجاع المعدي المريئي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : heartburn ; the patient with heartburn
  - modèle : The patient in bed 3 has heartburn.
- **q2.** What does the patient in bed 5 have?
  - accepté : bloating
  - modèle : He has bloating.
- **q3.** Does the patient in bed 5 have heartburn?
  - accepté : no ; no he does not ; no he doesn't ; he does not have heartburn ; he doesn't
  - modèle : No, he does not have heartburn.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS brûlures d'estomac (reflux) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : GERD ; a GERD
  - modèle : He has a GERD.
- **Tt2.** Does the patient in bed 9 have bloating?
  - accepté : no ; no he does not ; no he doesn't ; he does not have bloating ; he doesn't
  - modèle : No, he does not have bloating.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS ballonnement.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … heartburn and bloating. (have)` → have · modèle : The patients have heartburn and bloating.
- `The patient in bed 5 … not have heartburn. (do)` → does · modèle : The patient in bed 5 does not have heartburn.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has heartburn. The patient in bed 5 has bloating.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a RGO (Reflux gastro-œsophagien), le lit 9 a Brûlures d'estomac (Reflux) et n'a pas Ballonnement.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a GERD. The patient in bed 9 has heartburn.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
