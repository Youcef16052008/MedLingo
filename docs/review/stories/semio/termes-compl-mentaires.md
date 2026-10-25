# Relecture — Termes Complémentaires (semio)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `semio-case-014` — Le cas de Leila : Douleur irradiée (référée) / _Leila: Referred pain_

**Objectif :** Comprendre un cas : douleur irradiée (référée) présent, irradiation de la douleur absent.
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Leila talks to Lina, a medical student.
   - FR : Leila parle avec Lina, étudiant·e en médecine.
   - AR : Leila يتحدث مع Lina، طالب/طالبة طب.
3. **EN** She has referred pain.
   - FR : Elle a Douleur irradiée (référée).
   - AR : عنده الألم المُنعَكَس.
4. **EN** She does not have radiation of pain.
   - FR : Elle n'a pas de Irradiation de la douleur.
   - AR : ليس عنده إشعاع الألم.
5. **EN** Lina asks, “Do you have radiation of pain?”
   - FR : Lina demande : « Avez-vous Irradiation de la douleur ? »
   - AR : يسأل Lina: «هل عندك إشعاع الألم؟»
6. **EN** Leila says, “No, I do not.”
   - FR : Leila répond : « Non. »
   - AR : يجيب Leila: «لا.»
7. **EN** Lina writes “Referred pain” in the file.
   - FR : Lina écrit « Douleur irradiée (référée) » dans le dossier.
   - AR : يكتب Lina «Douleur irradiée (référée)» في الملف.

### Expressions ciblées
- `gen:referred-pain:v1` — **referred pain** · FR : Douleur irradiée (référée) · AR : الألم المُنعَكَس
- `gen:radiation-of-pain:v1` — **radiation of pain** · FR : Irradiation de la douleur · AR : إشعاع الألم
- `gen:paroxysmal-pain:v1` — **paroxysmal pain** · FR : Douleur paroxystique · AR : الألم الانتيابي

### Questions (acceptées / modèle / hint)
- **q1.** Does Leila have referred pain?
  - accepté : yes ; yes she does ; yes she has referred pain ; she has referred pain
  - modèle : Yes, she has referred pain.
- **q2.** Does she have radiation of pain?
  - accepté : no ; no she does not ; no she doesn't ; she does not have radiation of pain ; she doesn't
  - modèle : No, she does not have radiation of pain.
  - hint (si erreur) — FR : Attention : Leila n'a PAS irradiation de la douleur.
- **q3.** Does she have referred pain or radiation of pain?
  - accepté : referred pain ; she has referred pain
  - modèle : She has referred pain.
- **q4.** What does Leila have?
  - accepté : referred pain ; she has referred pain
  - modèle : She has referred pain.
- **q5.** Who writes in the file?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina writes in the file.
- **Tt1.** Does the other patient have paroxysmal pain?
  - accepté : yes ; yes he does ; he has paroxysmal pain
  - modèle : Yes, he has paroxysmal pain.
- **Tt2.** Does he have referred pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have referred pain ; he doesn't
  - modèle : No, he does not have referred pain.
  - hint (si erreur) — FR : Attention : il n'a PAS douleur irradiée (référée).

### Drill
- Explication : I have → He/She has. He has → He does not have (négation).
- `She … referred pain. (have)` → has · modèle : She has referred pain.
- `They … not have radiation of pain. (do)` → do · modèle : They do not have radiation of pain.
- `I … referred pain. (have)` → have · modèle : I have referred pain.

### Productions
- **Reformulation :** Rapporte le cas de Leila en deux phrases. Garde la négation. — amorces : She has… / She does not have… — modèle : *She has referred pain. She does not have radiation of pain.*
- **Transfert (contexte) :** Un autre patient a Douleur paroxystique. Il n'a pas de Douleur irradiée (référée).
- Glossaire : Douleur paroxystique : un mot médical de ce chapitre
- **Tâche :** Rapporte le nouveau cas en deux phrases. Garde la négation. — modèle : *He has paroxysmal pain. He does not have referred pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `semio-explain-014` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Douleur irradiée (référée) et Irradiation de la douleur à un patient.
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
4. **EN** “First, we check referred pain.”
   - FR : « D'abord, nous vérifions Douleur irradiée (référée). »
   - AR : «أولًا، نتحقق من الألم المُنعَكَس.»
5. **EN** “Then, we check radiation of pain.”
   - FR : « Ensuite, nous vérifions Irradiation de la douleur. »
   - AR : «ثم نتحقق من إشعاع الألم.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:referred-pain:v1` — **referred pain** · FR : Douleur irradiée (référée) · AR : الألم المُنعَكَس
- `gen:radiation-of-pain:v1` — **radiation of pain** · FR : Irradiation de la douleur · AR : إشعاع الألم
- `gen:paroxysmal-pain:v1` — **paroxysmal pain** · FR : Douleur paroxystique · AR : الألم الانتيابي

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : referred pain ; we check referred pain ; referred pain first ; the doctor checks referred pain first
  - modèle : First, the doctor checks referred pain.
- **q2.** What does the doctor check next?
  - accepté : radiation of pain ; then we check radiation of pain ; radiation of pain next ; the doctor checks radiation of pain next
  - modèle : Then, the doctor checks radiation of pain.
- **q3.** Does the doctor check radiation of pain before referred pain?
  - accepté : no ; no he does not ; no he doesn't ; he checks referred pain first
  - modèle : No, he checks referred pain first, then radiation of pain.
  - hint (si erreur) — FR : Attention : Douleur irradiée (référée) vient D'ABORD, ensuite Irradiation de la douleur.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : paroxysmal pain ; paroxysmal pain first ; he checks paroxysmal pain first
  - modèle : First, the doctor checks paroxysmal pain.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check referred pain. Then, we check radiation of pain.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Douleur paroxystique, ensuite Douleur irradiée (référée), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check paroxysmal pain. Then, we check referred pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `semio-handover-014` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Douleur irradiée (référée) / Irradiation de la douleur, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has referred pain.
   - FR : Le patient du lit 3 a Douleur irradiée (référée).
   - AR : مريض السرير ٣ عنده الألم المُنعَكَس.
4. **EN** The patient in bed 5 has radiation of pain.
   - FR : Le patient du lit 5 a Irradiation de la douleur.
   - AR : مريض السرير ٥ عنده إشعاع الألم.
5. **EN** The patient in bed 5 does not have referred pain.
   - FR : Le patient du lit 5 n'a pas de Douleur irradiée (référée).
   - AR : مريض السرير ٥ ليس عنده الألم المُنعَكَس.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:referred-pain:v1` — **referred pain** · FR : Douleur irradiée (référée) · AR : الألم المُنعَكَس
- `gen:radiation-of-pain:v1` — **radiation of pain** · FR : Irradiation de la douleur · AR : إشعاع الألم
- `gen:paroxysmal-pain:v1` — **paroxysmal pain** · FR : Douleur paroxystique · AR : الألم الانتيابي

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : referred pain ; the patient with referred pain
  - modèle : The patient in bed 3 has referred pain.
- **q2.** What does the patient in bed 5 have?
  - accepté : radiation of pain
  - modèle : He has radiation of pain.
- **q3.** Does the patient in bed 5 have referred pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have referred pain ; he doesn't
  - modèle : No, he does not have referred pain.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS douleur irradiée (référée) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : paroxysmal pain
  - modèle : He has paroxysmal pain.
- **Tt2.** Does the patient in bed 9 have radiation of pain?
  - accepté : no ; no he does not ; no he doesn't ; he does not have radiation of pain ; he doesn't
  - modèle : No, he does not have radiation of pain.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS irradiation de la douleur.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … referred pain and radiation of pain. (have)` → have · modèle : The patients have referred pain and radiation of pain.
- `The patient in bed 5 … not have referred pain. (do)` → does · modèle : The patient in bed 5 does not have referred pain.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has referred pain. The patient in bed 5 has radiation of pain.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Douleur paroxystique, le lit 9 a Douleur irradiée (référée) et n'a pas Irradiation de la douleur.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has paroxysmal pain. The patient in bed 9 has referred pain.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
