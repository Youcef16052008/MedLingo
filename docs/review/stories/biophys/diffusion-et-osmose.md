# Relecture — Diffusion et Osmose (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-002` — Le cas de Youssef : Osmose anormal(e) / _Youssef: the osmosis is not normal_

**Objectif :** Comprendre des résultats : Diffusion normal(e), Osmose anormal(e).
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
4. **EN** The doctor says, “The diffusion is normal.”
   - FR : Le médecin dit : « Diffusion est normal(e). »
   - AR : يقول الطبيب: «الانتشار طبيعي/طبيعية.»
5. **EN** “But the osmosis is not normal.”
   - FR : « Mais Osmose n'est pas normal(e). »
   - AR : «لكن التناضح غير طبيعي/طبيعية.»
6. **EN** Lina asks, “Is the osmosis normal?”
   - FR : Lina demande : « Osmose est-il/elle normal(e) ? »
   - AR : يسأل Lina: «هل التناضح طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:diffusion:v1` — **a diffusion** · FR : Diffusion · AR : الانتشار
- `gen:fick-s-law-of-diffusion:v1` — **a fick's law of diffusion** · FR : Loi de Fick · AR : قانون فيك للانتشار
- `gen:osmosis:v1` — **osmosis** · FR : Osmose · AR : التناضح

### Questions (acceptées / modèle / hint)
- **q1.** Is the diffusion normal?
  - accepté : yes ; yes it is ; the diffusion is normal ; it is normal
  - modèle : Yes, the diffusion is normal.
- **q2.** Is the osmosis normal?
  - accepté : no ; no it is not ; no it isn't ; the osmosis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the osmosis is not normal.
  - hint (si erreur) — FR : Attention : Osmose n'est PAS normal(e).
- **q3.** Which one is not normal: the diffusion or the osmosis?
  - accepté : osmosis ; the osmosis
  - modèle : The osmosis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina listens to the doctor.
- **Tt1.** Is the fick's law of diffusion normal?
  - accepté : yes ; yes it is ; the fick's law of diffusion is normal
  - modèle : Yes, the fick's law of diffusion is normal.
- **Tt2.** Is the diffusion normal?
  - accepté : no ; no it is not ; no it isn't ; the diffusion is not normal ; it is not normal
  - modèle : No, the diffusion is not normal.
  - hint (si erreur) — FR : Attention : Diffusion n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The diffusion … normal. (be)` → is · modèle : The diffusion is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Youssef en deux phrases. Garde la négation. — amorces : The diffusion is… / The osmosis is not… — modèle : *The diffusion is normal. The osmosis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Loi de Fick normal(e), mais Diffusion anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The fick's law of diffusion is normal. The diffusion is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-002` — Expliquer le plan à Farid / _Explaining the plan to Farid_

**Objectif :** Comprendre un médecin qui explique Diffusion et Loi de Fick à un patient.
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
4. **EN** “First, we check diffusion.”
   - FR : « D'abord, nous vérifions Diffusion. »
   - AR : «أولًا، نتحقق من الانتشار.»
5. **EN** “Then, we check fick's law of diffusion.”
   - FR : « Ensuite, nous vérifions Loi de Fick. »
   - AR : «ثم نتحقق من قانون فيك للانتشار.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Farid says, “Thank you. I understand now.”
   - FR : Farid dit : « Merci. Je comprends maintenant. »
   - AR : يقول Farid: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:diffusion:v1` — **a diffusion** · FR : Diffusion · AR : الانتشار
- `gen:fick-s-law-of-diffusion:v1` — **a fick's law of diffusion** · FR : Loi de Fick · AR : قانون فيك للانتشار
- `gen:osmosis:v1` — **osmosis** · FR : Osmose · AR : التناضح

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : diffusion ; we check diffusion ; diffusion first ; the doctor checks diffusion first
  - modèle : First, the doctor checks diffusion.
- **q2.** What does the doctor check next?
  - accepté : fick's law of diffusion ; then we check fick's law of diffusion ; fick's law of diffusion next ; the doctor checks fick's law of diffusion next
  - modèle : Then, the doctor checks fick's law of diffusion.
- **q3.** Does the doctor check fick's law of diffusion before diffusion?
  - accepté : no ; no he does not ; no he doesn't ; he checks diffusion first
  - modèle : No, he checks diffusion first, then fick's law of diffusion.
  - hint (si erreur) — FR : Attention : Diffusion vient D'ABORD, ensuite Loi de Fick.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : farid ; the patient ; the patient (farid)
  - modèle : Farid understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : osmosis ; osmosis first ; he checks osmosis first
  - modèle : First, the doctor checks osmosis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check diffusion. Then, we check fick's law of diffusion.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Osmose, ensuite Diffusion, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check osmosis. Then, we check diffusion.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-002` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Diffusion / Loi de Fick, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Amine is a medical student.
   - FR : Amine est étudiant·e en médecine.
   - AR : Amine طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the diffusion.
   - FR : Le patient du lit 3 a Diffusion.
   - AR : مريض السرير ٣ عنده الانتشار.
4. **EN** The patient in bed 5 has a problem with the fick's law of diffusion.
   - FR : Le patient du lit 5 a Loi de Fick.
   - AR : مريض السرير ٥ عنده قانون فيك للانتشار.
5. **EN** The patient in bed 5 does not have a problem with the diffusion.
   - FR : Le patient du lit 5 n'a pas de Diffusion.
   - AR : مريض السرير ٥ ليس عنده الانتشار.
6. **EN** Amine reports the two cases to the doctor.
   - FR : Amine rapporte les deux cas au médecin.
   - AR : يذكر Amine الحالتين للطبيب.

### Expressions ciblées
- `gen:diffusion:v1` — **a diffusion** · FR : Diffusion · AR : الانتشار
- `gen:fick-s-law-of-diffusion:v1` — **a fick's law of diffusion** · FR : Loi de Fick · AR : قانون فيك للانتشار
- `gen:osmosis:v1` — **osmosis** · FR : Osmose · AR : التناضح

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : diffusion ; a diffusion ; the patient with diffusion
  - modèle : The patient in bed 3 has a diffusion.
- **q2.** What does the patient in bed 5 have?
  - accepté : fick's law of diffusion ; a problem with the fick's law of diffusion
  - modèle : He has a problem with the fick's law of diffusion.
- **q3.** Does the patient in bed 5 have diffusion?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the diffusion ; he doesn't
  - modèle : No, he does not have a problem with the diffusion.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS diffusion — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : osmosis ; a problem with the osmosis
  - modèle : He has a problem with the osmosis.
- **Tt2.** Does the patient in bed 9 have fick's law of diffusion?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the fick's law of diffusion ; he doesn't
  - modèle : No, he does not have a problem with the fick's law of diffusion.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS loi de fick.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a diffusion and a fick's law of diffusion. (have)` → have · modèle : The patients have a diffusion and a fick's law of diffusion.
- `The patient in bed 5 … not have a diffusion. (do)` → does · modèle : The patient in bed 5 does not have a diffusion.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the diffusion. The patient in bed 5 has a problem with the fick's law of diffusion.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Osmose, le lit 9 a Diffusion et n'a pas Loi de Fick.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the osmosis. The patient in bed 9 has a problem with the diffusion.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
