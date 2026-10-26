# Relecture — Néoplasies - Généralités (anapath)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `anapath-case-005` — Le cas de Youssef : Sarcome / _Youssef: Sarcoma_

**Objectif :** Comprendre un cas : sarcome présent, carcinome vs sarcome absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Youssef is a patient at the clinic.
   - FR : Youssef est un·e patient·e à la clinique.
   - AR : Youssef مريض/مريضة في العيادة.
2. **EN** Youssef talks to Amine, a medical student.
   - FR : Youssef parle avec Amine, étudiant·e en médecine.
   - AR : Youssef يتحدث مع Amine، طالب/طالبة طب.
3. **EN** He has sarcoma.
   - FR : Il a Sarcome.
   - AR : عنده الساركومة.
4. **EN** He does not have carcinoma vs sarcoma.
   - FR : Il n'a pas de Carcinome vs Sarcome.
   - AR : ليس عنده السرطانة الظهارية مقابل الساركوما اللحمية.
5. **EN** Amine asks, “Do you have carcinoma vs sarcoma?”
   - FR : Amine demande : « Avez-vous Carcinome vs Sarcome ? »
   - AR : يسأل Amine: «هل عندك السرطانة الظهارية مقابل الساركوما اللحمية؟»
6. **EN** Youssef says, “No, I do not.”
   - FR : Youssef répond : « Non. »
   - AR : يجيب Youssef: «لا.»
7. **EN** Amine writes “Sarcoma” in the file.
   - FR : Amine écrit « Sarcome » dans le dossier.
   - AR : يكتب Amine «Sarcome» في الملف.

### Expressions ciblées
- `gen:sarcoma:v1` — **sarcoma** · FR : Sarcome · AR : الساركومة
- `gen:carcinoma-vs-sarcoma:v1` — **carcinoma vs sarcoma** · FR : Carcinome vs Sarcome · AR : السرطانة الظهارية مقابل الساركوما اللحمية
- `gen:neoplasia:v1` — **a neoplasia** · FR : Néoplasie · AR : التشكُّل الورمي

### Questions (acceptées / modèle / hint)
- **q1.** Does Youssef have sarcoma?
  - accepté : yes ; yes he does ; yes he has sarcoma ; he has sarcoma
  - modèle : Yes, he has sarcoma.
- **q2.** Does he have carcinoma vs sarcoma?
  - accepté : no ; no he does not ; no he doesn't ; he does not have carcinoma vs sarcoma ; he doesn't
  - modèle : No, he does not have carcinoma vs sarcoma.
  - hint (si erreur) — FR : Attention : Youssef n'a PAS carcinome vs sarcome.
- **q3.** Does he have sarcoma or carcinoma vs sarcoma?
  - accepté : sarcoma ; he has sarcoma
  - modèle : He has sarcoma.
- **q4.** What does Youssef have?
  - accepté : sarcoma ; he has sarcoma
  - modèle : He has sarcoma.
- **q5.** Who writes in the file?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine writes in the file.
- **Tt1.** Does the other patient have neoplasia?
  - accepté : yes ; yes he does ; he has a neoplasia
  - modèle : Yes, he has a neoplasia.
- **Tt2.** Does he have sarcoma?
  - accepté : no ; no he does not ; no he doesn't ; he does not have sarcoma ; he doesn't
  - modèle : No, he does not have sarcoma.
  - hint (si erreur) — FR : Attention : il n'a PAS sarcome.

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `He … sarcoma. (have)` → has · modèle : He has sarcoma.
- `They … not have carcinoma vs sarcoma. (do)` → do · modèle : They do not have carcinoma vs sarcoma.
- `I … sarcoma. (have)` → have · modèle : I have sarcoma.

### Productions
- **Reformulation :** Rapporte le cas de Youssef en deux phrases. Garde la négation. — amorces : He has… / He does not have… — modèle : *He has sarcoma. He does not have carcinoma vs sarcoma.*
- **Transfert (contexte) :** Un autre patient a Néoplasie. Il n'a pas de Sarcome.
- Glossaire : Néoplasie : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has a neoplasia. He does not have sarcoma.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `anapath-explain-005` — Expliquer le plan à Hana / _Explaining the plan to Hana_

**Objectif :** Comprendre un médecin qui explique Néoplasie et Tumeur (Néoplasme) à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient.
   - FR : Hana est un·e patient·e.
   - AR : Hana مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check neoplasia.”
   - FR : « D'abord, nous vérifions Néoplasie. »
   - AR : «أولًا، نتحقق من التشكُّل الورمي.»
5. **EN** “Then, we check tumor.”
   - FR : « Ensuite, nous vérifions Tumeur (Néoplasme). »
   - AR : «ثم نتحقق من الورم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Hana says, “Thank you. I understand now.”
   - FR : Hana dit : « Merci. Je comprends maintenant. »
   - AR : يقول Hana: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:neoplasia:v1` — **a neoplasia** · FR : Néoplasie · AR : التشكُّل الورمي
- `gen:tumor:v1` — **a tumor** · FR : Tumeur (Néoplasme) · AR : الورم
- `gen:benign-tumor:v1` — **a benign tumor** · FR : Tumeur bénigne · AR : الورم الحميد

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : neoplasia ; we check neoplasia ; neoplasia first ; the doctor checks neoplasia first
  - modèle : First, the doctor checks neoplasia.
- **q2.** What does the doctor check next?
  - accepté : tumor ; then we check tumor ; tumor next ; the doctor checks tumor next
  - modèle : Then, the doctor checks tumor.
- **q3.** Does the doctor check tumor before neoplasia?
  - accepté : no ; no he does not ; no he doesn't ; he checks neoplasia first
  - modèle : No, he checks neoplasia first, then tumor.
  - hint (si erreur) — FR : Attention : Néoplasie vient D'ABORD, ensuite Tumeur (Néoplasme).
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : hana ; the patient ; the patient (hana)
  - modèle : Hana understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : benign tumor ; benign tumor first ; he checks benign tumor first
  - modèle : First, the doctor checks benign tumor.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check neoplasia. Then, we check tumor.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Tumeur bénigne, ensuite Néoplasie, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check benign tumor. Then, we check neoplasia.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `anapath-handover-005` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Sarcome / Carcinome vs Sarcome, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has sarcoma.
   - FR : Le patient du lit 3 a Sarcome.
   - AR : مريض السرير ٣ عنده الساركومة.
4. **EN** The patient in bed 5 has carcinoma vs sarcoma.
   - FR : Le patient du lit 5 a Carcinome vs Sarcome.
   - AR : مريض السرير ٥ عنده السرطانة الظهارية مقابل الساركوما اللحمية.
5. **EN** The patient in bed 5 does not have sarcoma.
   - FR : Le patient du lit 5 n'a pas de Sarcome.
   - AR : مريض السرير ٥ ليس عنده الساركومة.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:sarcoma:v1` — **sarcoma** · FR : Sarcome · AR : الساركومة
- `gen:carcinoma-vs-sarcoma:v1` — **carcinoma vs sarcoma** · FR : Carcinome vs Sarcome · AR : السرطانة الظهارية مقابل الساركوما اللحمية
- `gen:neoplasia:v1` — **a neoplasia** · FR : Néoplasie · AR : التشكُّل الورمي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : sarcoma ; the patient with sarcoma
  - modèle : The patient in bed 3 has sarcoma.
- **q2.** What does the patient in bed 5 have?
  - accepté : carcinoma vs sarcoma
  - modèle : He has carcinoma vs sarcoma.
- **q3.** Does the patient in bed 5 have sarcoma?
  - accepté : no ; no he does not ; no he doesn't ; he does not have sarcoma ; he doesn't
  - modèle : No, he does not have sarcoma.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS sarcome — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : neoplasia ; a neoplasia
  - modèle : He has a neoplasia.
- **Tt2.** Does the patient in bed 9 have carcinoma vs sarcoma?
  - accepté : no ; no he does not ; no he doesn't ; he does not have carcinoma vs sarcoma ; he doesn't
  - modèle : No, he does not have carcinoma vs sarcoma.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS carcinome vs sarcome.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … sarcoma and carcinoma vs sarcoma. (have)` → have · modèle : The patients have sarcoma and carcinoma vs sarcoma.
- `The patient in bed 5 … not have sarcoma. (do)` → does · modèle : The patient in bed 5 does not have sarcoma.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has sarcoma. The patient in bed 5 has carcinoma vs sarcoma.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Néoplasie, le lit 9 a Sarcome et n'a pas Carcinome vs Sarcome.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a neoplasia. The patient in bed 9 has sarcoma.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
