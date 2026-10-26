# Relecture — Optique Médicale (biophys)

Fiche générée automatiquement (`scripts/export_story_review.py`). Cocher, corriger dans `stories.v1.json` (ou le builder), puis publier le chapitre selon [RELECTURE_HISTOIRES.md](../../../RELECTURE_HISTOIRES.md).

- [ ] **Chapitre entier relu (linguistique)**
- [ ] **Chapitre entier relu (médical)**

## 1. `biophys-case-007` — Le cas de Hana : Fréquence anormal(e) / _Hana: the frequency is not normal_

**Objectif :** Comprendre des résultats : Lumière normal(e), Fréquence anormal(e).
**Archétype :** case · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Hana is a patient at the clinic.
   - FR : Hana est un·e patient·e à la clinique.
   - AR : Hana مريض/مريضة في العيادة.
2. **EN** Today, the doctor has the results.
   - FR : Aujourd'hui, le médecin a les résultats.
   - AR : اليوم، الطبيب عنده النتائج.
3. **EN** Amine, a medical student, listens.
   - FR : Amine, étudiant·e en médecine, écoute.
   - AR : Amine، طالب/طالبة طب، يستمع.
4. **EN** The doctor says, “The light is normal.”
   - FR : Le médecin dit : « Lumière est normal(e). »
   - AR : يقول الطبيب: «الضوء طبيعي/طبيعية.»
5. **EN** “But the frequency is not normal.”
   - FR : « Mais Fréquence n'est pas normal(e). »
   - AR : «لكن التردد غير طبيعي/طبيعية.»
6. **EN** Amine asks, “Is the frequency normal?”
   - FR : Amine demande : « Fréquence est-il/elle normal(e) ? »
   - AR : يسأل Amine: «هل التردد طبيعي/طبيعية؟»
7. **EN** The doctor says, “No, it is not.”
   - FR : Le médecin répond : « Non. »
   - AR : يجيب الطبيب: «لا.»

### Expressions ciblées
- `gen:light:v1` — **a light** · FR : Lumière · AR : الضوء
- `gen:wavelength:v1` — **a wavelength** · FR : Longueur d'onde · AR : الطول الموجي
- `gen:frequency:v1` — **a frequency** · FR : Fréquence · AR : التردد

### Questions (acceptées / modèle / hint)
- **q1.** Is the light normal?
  - accepté : yes ; yes it is ; the light is normal ; it is normal
  - modèle : Yes, the light is normal.
- **q2.** Is the frequency normal?
  - accepté : no ; no it is not ; no it isn't ; the frequency is not normal ; it is not normal ; it isn't normal
  - modèle : No, the frequency is not normal.
  - hint (si erreur) — FR : Attention : Fréquence n'est PAS normal(e).
- **q3.** Which one is not normal: the light or the frequency?
  - accepté : frequency ; the frequency
  - modèle : The frequency is not normal.
- **q4.** Who has the results?
  - accepté : the doctor ; doctor
  - modèle : The doctor has the results.
- **q5.** Who listens to the doctor?
  - accepté : amine ; the student ; the medical student
  - modèle : Amine listens to the doctor.
- **Tt1.** Is the wavelength normal?
  - accepté : yes ; yes it is ; the wavelength is normal
  - modèle : Yes, the wavelength is normal.
- **Tt2.** Is the light normal?
  - accepté : no ; no it is not ; no it isn't ; the light is not normal ; it is not normal
  - modèle : No, the light is not normal.
  - hint (si erreur) — FR : Attention : Lumière n'est PAS normal(e) pour ce patient.

### Drill
- Explication : The heart is → The results are. Une chose : is. Plusieurs : are.
- `The light … normal. (be)` → is · modèle : The light is normal.
- `The results … not normal. (be)` → are · modèle : The results are not normal.
- `It … not normal. (be)` → is · modèle : It is not normal.

### Productions
- **Reformulation :** Annonce les résultats à Hana en deux phrases. Garde la négation. — amorces : The light is… / The frequency is not… — modèle : *The light is normal. The frequency is not normal.*
- **Transfert (contexte) :** Un autre patient a aussi des résultats : Longueur d'onde normal(e), mais Lumière anormal(e).
- **Tâche :** Annonce les nouveaux résultats, en deux phrases. — modèle : *The wavelength is normal. The light is not normal.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 2. `biophys-explain-007` — Expliquer le plan à Sara / _Explaining the plan to Sara_

**Objectif :** Comprendre un médecin qui explique Lumière et Longueur d'onde à un patient.
**Archétype :** explain · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Sara is a patient.
   - FR : Sara est un·e patient·e.
   - AR : Sara مريض/مريضة.
2. **EN** She is at the hospital.
   - FR : Elle est à l'hôpital.
   - AR : هي/هو في المستشفى.
3. **EN** The doctor explains the plan to her.
   - FR : Le médecin explique le plan à elle.
   - AR : يشرح الطبيب الخطة له/لها.
4. **EN** “First, we check light.”
   - FR : « D'abord, nous vérifions Lumière. »
   - AR : «أولًا، نتحقق من الضوء.»
5. **EN** “Then, we check wavelength.”
   - FR : « Ensuite, nous vérifions Longueur d'onde. »
   - AR : «ثم نتحقق من الطول الموجي.»
6. **EN** “After that, I explain the results.”
   - FR : « Après, j'explique les résultats. »
   - AR : «بعد ذلك أشرح النتائج.»
7. **EN** Sara says, “Thank you. I understand now.”
   - FR : Sara dit : « Merci. Je comprends maintenant. »
   - AR : يقول Sara: «شكرًا. أفهم الآن.»

### Expressions ciblées
- `gen:light:v1` — **a light** · FR : Lumière · AR : الضوء
- `gen:wavelength:v1` — **a wavelength** · FR : Longueur d'onde · AR : الطول الموجي
- `gen:frequency:v1` — **a frequency** · FR : Fréquence · AR : التردد

### Questions (acceptées / modèle / hint)
- **q1.** What does the doctor check first?
  - accepté : light ; we check light ; light first ; the doctor checks light first
  - modèle : First, the doctor checks light.
- **q2.** What does the doctor check next?
  - accepté : wavelength ; then we check wavelength ; wavelength next ; the doctor checks wavelength next
  - modèle : Then, the doctor checks wavelength.
- **q3.** Does the doctor check wavelength before light?
  - accepté : no ; no he does not ; no he doesn't ; he checks light first
  - modèle : No, he checks light first, then wavelength.
  - hint (si erreur) — FR : Attention : Lumière vient D'ABORD, ensuite Longueur d'onde.
- **q4.** Does the doctor explain the results?
  - accepté : yes ; yes he does ; he explains the results
  - modèle : Yes, he explains the results.
- **q5.** Who understands now?
  - accepté : sara ; the patient ; the patient (sara)
  - modèle : Sara understands now.
- **Tt1.** What does the doctor check first in the new plan?
  - accepté : frequency ; frequency first ; he checks frequency first
  - modèle : First, the doctor checks frequency.
- **Tt2.** Does he explain the results at the end?
  - accepté : yes ; yes he does ; he explains the results after that
  - modèle : Yes, he explains the results after that.

### Drill
- Explication : we check → the doctor checkS. First / then / after that = ordre.
- `First, we … the results. (check — présent)` → check · modèle : First, we check the results.
- `Then, the doctor … the plan. (explain — présent)` → explains · modèle : Then, the doctor explains the plan.
- `After that, I … the results. (explain — présent)` → explain · modèle : After that, I explain the results.

### Productions
- **Reformulation :** Dis le plan au patient, en deux phrases. — amorces : First, we check… / Then, we check… — modèle : *First, we check light. Then, we check wavelength.*
- **Transfert (contexte) :** Le médecin explique un autre plan : « D'abord Fréquence, ensuite Lumière, puis j'explique les résultats. »
- **Tâche :** Dis le nouveau plan, en deux phrases. — modèle : *First, we check frequency. Then, we check light.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)

## 3. `biophys-handover-007` — Garde du matin : deux cas / _Morning shift: two cases_

**Objectif :** Rapporter correctement deux cas (Lumière / Longueur d'onde, lits 3 et 5).
**Archétype :** handover · **Durée :** 10 min · **Statut :** draft

### Récit (segments EN / aide FR / aide AR)
1. **EN** Lina is a medical student.
   - FR : Lina est étudiant·e en médecine.
   - AR : Lina طالب/طالبة طب.
2. **EN** It is the morning shift.
   - FR : C'est la garde du matin.
   - AR : إنها مناوبة الصباح.
3. **EN** The patient in bed 3 has a problem with the light.
   - FR : Le patient du lit 3 a Lumière.
   - AR : مريض السرير ٣ عنده الضوء.
4. **EN** The patient in bed 5 has a problem with the wavelength.
   - FR : Le patient du lit 5 a Longueur d'onde.
   - AR : مريض السرير ٥ عنده الطول الموجي.
5. **EN** The patient in bed 5 does not have a problem with the light.
   - FR : Le patient du lit 5 n'a pas de Lumière.
   - AR : مريض السرير ٥ ليس عنده الضوء.
6. **EN** Lina reports the two cases to the doctor.
   - FR : Lina rapporte les deux cas au médecin.
   - AR : يذكر Lina الحالتين للطبيب.

### Expressions ciblées
- `gen:light:v1` — **a light** · FR : Lumière · AR : الضوء
- `gen:wavelength:v1` — **a wavelength** · FR : Longueur d'onde · AR : الطول الموجي
- `gen:frequency:v1` — **a frequency** · FR : Fréquence · AR : التردد

### Questions (acceptées / modèle / hint)
- **q1.** Which patient is in bed 3?
  - accepté : light ; a light ; the patient with light
  - modèle : The patient in bed 3 has a light.
- **q2.** What does the patient in bed 5 have?
  - accepté : wavelength ; a problem with the wavelength
  - modèle : He has a problem with the wavelength.
- **q3.** Does the patient in bed 5 have light?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the light ; he doesn't
  - modèle : No, he does not have a problem with the light.
  - hint (si erreur) — FR : Attention : le lit 5 n'a PAS lumière — c'est le lit 3.
- **q4.** How many cases does the student report?
  - accepté : two ; 2 ; two cases
  - modèle : The student reports two cases.
- **q5.** Who reports the cases?
  - accepté : lina ; the student ; the medical student
  - modèle : Lina reports them.
- **Tt1.** What does the patient in bed 7 have?
  - accepté : frequency ; a problem with the frequency
  - modèle : He has a problem with the frequency.
- **Tt2.** Does the patient in bed 9 have wavelength?
  - accepté : no ; no he does not ; no he doesn't ; he does not have a problem with the wavelength ; he doesn't
  - modèle : No, he does not have a problem with the wavelength.
  - hint (si erreur) — FR : Attention : le lit 9 n'a PAS longueur d'onde.

### Drill
- Explication : Un patient : reports/has/does. Deux patients : report/have/do.
- `The patient in bed 3 … the case. (report — présent)` → reports · modèle : The patient in bed 3 reports the case.
- `The patients … a light and a wavelength. (have)` → have · modèle : The patients have a light and a wavelength.
- `The patient in bed 5 … not have a light. (do)` → does · modèle : The patient in bed 5 does not have a light.

### Productions
- **Reformulation :** Rapporte les deux cas au médecin, en deux phrases. — amorces : The patient in bed 3 has… / The patient in bed 5 has… — modèle : *The patient in bed 3 has a problem with the light. The patient in bed 5 has a problem with the wavelength.*
- **Transfert (contexte) :** Garde du soir : le lit 7 a Fréquence, le lit 9 a Lumière et n'a pas Longueur d'onde.
- **Tâche :** Rapporte les cas du soir, en deux phrases. — modèle : *The patient in bed 7 has a problem with the frequency. The patient in bed 9 has a problem with the light.*

### À vérifier
- [ ] Anglais naturel et niveau A1–A2 (articles, genre, pluriel)
- [ ] Aides FR/AR fidèles — négations jamais inversées
- [ ] Faits cliniques exacts, aucun conseil diagnostique/thérapeutique
- [ ] Hints présents et justes sur les faits négatifs
- [ ] Réponses acceptées exhaustives (contractions incluses)
