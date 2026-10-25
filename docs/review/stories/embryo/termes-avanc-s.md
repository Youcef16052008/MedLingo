# Relecture — Termes Avancés (embryo)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `embryo-case-011` — Le cas de Leila : Morphogenèse anormal(e) / _Leila: the morphogenesis is not normal_

**Objectif :** Comprendre des résultats : Apoptose (Mort cellulaire programmée) normal(e), Morphogenèse anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Leila is a patient at the clinic.
   - FR : Leila est un·e patient·e à la clinique.
   - AR : Leila مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The apoptosis is normal.”
   - FR : Le médecin dit : « Apoptose (Mort cellulaire programmée) est normal(e). »
   - AR : يقول الطبيب: «الموت الخلوي المبرمج طبيعي/طبيعية.»
5. **EN** “But the morphogenesis is not normal.”
   - FR : « Mais Morphogenèse n'est pas normal(e). »
   - AR : «لكن التشكل (تطور الشكل) غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the morphogenesis normal?”
   - FR : Amine demande : « Morphogenèse est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل التشكل (تطور الشكل) طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:apoptosis:v1` — **apoptosis** · FR : Apoptose (Mort cellulaire programmée) · AR : الموت الخلوي المبرمج
- `gen:morphogen:v1` — **a morphogen** · FR : Morphogène · AR : المورفوجين
- `gen:morphogenesis:v1` — **morphogenesis** · FR : Morphogenèse · AR : التشكل (تطور الشكل)

### Questions (acceptées / modèle / hint)
- **q1.** Is the apoptosis normal?
  - accepté : yes ; yes it is ; the apoptosis is normal ; it is normal
  - modèle : Yes, the apoptosis is normal.
- **q2.** Is the morphogenesis normal?
  - accepté : no ; no it is not ; no it isn't ; the morphogenesis is not normal ; it is not normal ; it isn't normal
  - modèle : No, the morphogenesis is not normal.
  - hint (si erreur) — FR : Attention : Morphogenèse n'est PAS normal(e).
- **q3.** Which one is not normal: the apoptosis or the morphogenesis?
  - accepté : morphogenesis ; the morphogenesis
  - modèle : The morphogenesis is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the morphogen normal?
  - accepté : yes ; yes it is ; the morphogen is normal
  - modèle : Yes, the morphogen is normal.
- **Tt2.** Is the apoptosis normal?
  - accepté : no ; no it is not ; no it isn't ; the apoptosis is not normal ; it is not normal
  - modèle : No, the apoptosis is not normal.
  - hint (si erreur) — FR : Attention : Apoptose (Mort cellulaire programmée) n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The apoptosis … normal. (be)` → is · modèle : The apoptosis is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Leila en deux phrases. Garde la négation. — amorces : The apoptosis is… / The morphogenesis is not… — modèle : *The apoptosis is normal. The morphogenesis is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Morphogène normal(e), mais Apoptose (Mort cellulaire programmée) anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The morphogen is normal. The apoptosis is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `embryo-explain-011` — Expliquer le plan à Nadia / _Explaining the plan to Nadia_

**Objectif :** Comprendre un médecin qui explique Apoptose (Mort cellulaire programmée) et Morphogène à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

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
4. **EN** “First, we check apoptosis.”
   - FR : « D'abord, nous vérifions Apoptose (Mort cellulaire programmée). »
   - AR : «أولًا، نتحقق من الموت الخلوي المبرمج.»
5. **EN** “Then, we check morphogen.”
   - FR : « Ensuite, nous vérifions Morphogène. »
   - AR : «ثم نتحقق من المورفوجين.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Nadia says, “Thank you. I understand now.”
   - FR : Nadia dit : « Merci. Je comprends maintenant. »
   - AR : يقول Nadia: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:apoptosis:v1` — **apoptosis** · FR : Apoptose (Mort cellulaire programmée) · AR : الموت الخلوي المبرمج
- `gen:morphogen:v1` — **a morphogen** · FR : Morphogène · AR : المورفوجين
- `gen:morphogenesis:v1` — **morphogenesis** · FR : Morphogenèse · AR : التشكل (تطور الشكل)

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : apoptosis ; we check apoptosis ; apoptosis first ; the doctor checks apoptosis first
  - modèle : First, the doctor checks apoptosis.
- **q2.** What does the doctor check next?
  - accepté : morphogen ; then we check morphogen ; morphogen next ; the doctor checks morphogen next
  - modèle : Then, the doctor checks morphogen.
- **q3.** Does the doctor check morphogen before apoptosis?
  - accepté : no ; no he does not ; no he doesn't ; he checks apoptosis first
  - modèle : No, he checks apoptosis first, then morphogen.
  - hint (si erreur) — FR : Attention : Apoptose (Mort cellulaire programmée) vient D'ABORD, ensuite Morphogène.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : nadia ; the patient ; the patient (nadia)
  - modèle : Nadia understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : morphogenesis ; morphogenesis first ; he checks morphogenesis first
  - modèle : First, the doctor checks morphogenesis.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check apoptosis. Then, we check morphogen.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Morphogenèse, ensuite Apoptose (Mort cellulaire programmée), puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check morphogenesis. Then, we check apoptosis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `embryo-handover-011` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Apoptose (Mort cellulaire programmée) / Morphogène, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the apoptosis.
   - FR : Le patient du lit 3 a Apoptose (Mort cellulaire programmée).
   - AR : مريض السرير ٣ عنده الموت الخلوي المبرمج.
4. **EN** The patient in bed 5 has a problem with the morphogen.
   - FR : Le patient du lit 5 a Morphogène.
   - AR : مريض السرير ٥ عنده المورفوجين.
5. **EN** The patient in bed 5 does not have a problem with the apoptosis.
   - FR : Le patient du lit 5 n'a pas de Apoptose (Mort cellulaire programmée).
   - AR : مريض السرير ٥ ليس عنده الموت الخلوي المبرمج.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:apoptosis:v1` — **apoptosis** · FR : Apoptose (Mort cellulaire programmée) · AR : الموت الخلوي المبرمج
- `gen:morphogen:v1` — **a morphogen** · FR : Morphogène · AR : المورفوجين
- `gen:morphogenesis:v1` — **morphogenesis** · FR : Morphogenèse · AR : التشكل (تطور الشكل)

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : apoptosis ; the patient with apoptosis
  - modèle : The patient in bed 3 has apoptosis.
- **q2.** What does the patient in bed 5 have?
  - accepté : morphogen ; a problem with the morphogen
  - modèle : He has a problem with the morphogen.
- **q3.** Does the patient in bed 5 have apoptosis?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the apoptosis ; he doesn't
  - modèle : No, he does not have a problem with the apoptosis.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS apoptose (mort cellulaire programmée) — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : morphogenesis ; a problem with the morphogenesis
  - modèle : He has a problem with the morphogenesis.
- **Tt2.** Does the patient in bed 9 have morphogen?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the morphogen ; he doesn't
  - modèle : No, he does not have a problem with the morphogen.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS morphogène.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … apoptosis and a morphogen. (have)` → have · modèle : The patients have apoptosis and a morphogen.
- `The patient in bed 5 … not have apoptosis. (do)` → does · modèle : The patient in bed 5 does not have apoptosis.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the apoptosis. The patient in bed 5 has a problem with the morphogen.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Morphogenèse, le lit 9 a Apoptose (Mort cellulaire programmée) et n'a pas Morphogène.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the morphogenesis. The patient in bed 9 has a problem with the apoptosis.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
