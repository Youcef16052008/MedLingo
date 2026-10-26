# MedLingo — À faire (le reste)

**Dernière mise à jour : 25/10/2026** · **Pilotage unique : [`PLAN_MAITRE_2026.md`](PLAN_MAITRE_2026.md)** (consolide les 40 rapports du dépôt, workstreams WS0–WS6, ADRs, gouvernance documentaire) · Stratégie : [`RAPPORT_COMPETITIF_ET_PLAN_2026.md`](RAPPORT_COMPETITIF_ET_PLAN_2026.md) · Constat qualité : [`REVIEW_PROFONDE_20_ANALYSES.md`](REVIEW_PROFONDE_20_ANALYSES.md).

---

## ✅ Fait (25/10/2026)

- [x] Rapport d'analyse concurrentielle + plan stratégique « mieux que Duolingo sur le créneau » — commit `852671d`
- [x] **V0.1 — Dashboard de relecture** du corpus (commit `6fbf815`) : `npm run review`, verdicts langue/médecine persistés dans `data/review-verdicts.v1.json`, publication automatique ⟺ double approbation, harnais 5086/5086 vert, cycle testé bout-en-bout.
- [x] **Revue profonde 20 analyses** (36 findings dont 5 critiques) — [`REVIEW_PROFONDE_20_ANALYSES.md`](REVIEW_PROFONDE_20_ANALYSES.md)

## 🚨 P0 — Bugs critiques de la revue — ✅ WS0 EXECUTÉ le 25/10/2026

- [x] **BUG-001** web : `store.ts:336` — `reviewQueue` rechargé en objet → chaque `update()` crashe après rechargement (perte silencieuse de toute progression). **Corrigé** : `coerceArray` + gardes d'entrées + test roundtrip `persist→revive→update` (4 tests).
- [x] **BUG-003** web : type `"gap"` sans renderer — **corrigé** : `"gap"` dans `ExerciseType` + renderer saisie libre (3 points d'ancrage) + `RENDERABLE_EXERCISE_TYPES` + test de contrat corpus↔renderers (4 tests). Le commit du corpus 13 000 est débloqué.
- [x] **BUG-002** web : `PathScreen.tsx` — `buildPath(s.lessonBest, s.moduleBest)` : 1 ligne, la refonte du 19/10 est refermée.
- [x] **BUG-004** Android : `TtsManager` — `stop()` retiré du `finally`, `onStop` overridé (résout l'attente en interruption), `pendingCallbacks` en `ConcurrentHashMap`, `engineGeneration` `@Volatile`.
- [x] **BUG-005** Android : collecteur ligues — Job unique remplacé au changement de cohorte + persistance **seulement si l'animation change quelque chose** (advanceAll idempotent) + `distinctUntilChanged`. Boucle d'écritures éliminée.
- [x] **BUG-006/016** pipeline : sidecar validé (forme + ids orphelins) **AVANT toute écriture** (import p3 différé), écritures atomiques tmp+`os.replace` dans les 2 builders. Vérifié : échec orphelin → corpus intact.
- [x] **BUG-007** CI : `.github/` committé, `check:stories` ajouté à web.yml, `lintDebug || true` retiré.
- [x] **BUG-013** web : race au montage `#/story/:id` — l'effet rejoue quand l'histoire arrive (`[story?.storyId]`).
- [x] **BUG-014** serveur de relecture : Content-Type vérifié (415), corps plafonné 1 Mo (413), payloads non-objets rejetés (400). Testé en direct.

---

## 🔴 HUMAIN — bloque la mise en store

### 1. Campagne de relecture des 814 drafts (cible V0.1 : ≥ 150 cette semaine)

- [ ] `cd web-react && npm run review` → http://127.0.0.1:8799
- [ ] Renseigner ses initiales (en-tête), puis relire **chapitre par chapitre** :
      verdicts **Langue** et **Médecine** (Approuver / À corriger / Rejeter, note obligatoire si pas approuvé).
      Liste de contrôle intégrée en haut de page (protocole `RELECTURE_HISTOIRES.md`).
- [ ] Après chaque session — publier ce qui est doublement approuvé :
      ```bash
      cd web-react
      python scripts/build_stories_corpus.py && npm run check:stories && npx vitest run
      ```
- [ ] Committer `web-react/data/review-verdicts.v1.json` + les deux `stories.v1.json` régénérés.
- [ ] Cible finale : 814/814. Rythme de référence : ~50 chapitres (150 histoires)/semaine en solo ; double avec 2 relecteurs.
- [ ] Priorisation suggérée : Anglais Médical → Sémiologie → Terminologie (forte demande examen), puis le reste.

### 2. Recruter 2–3 relecteurs (faculté)

- [ ] 1 profil linguistique (anglais + aides FR/AR) + 1–2 profils médicaux.
- [ ] Pour chacun : clone du repo, `npm install` dans `web-react/`, `npm run review` — rien d'autre à installer.
- [ ] Leur donner `docs/RELECTURE_HISTOIRES.md` + ce fichier (suivi des chapitres traités).

### 3. Pilote étudiant (V0.3 — protocole déjà prêt)

- [ ] Confirmer ≥ 5 étudiants (`docs/PROTOCOLE_PILOTE_ETUDIANTS.md`).
- [ ] Caler les dates ; exécuter ; collecter : compréhension sur audio neuf à J+7, réutilisation des expressions, aides nécessaires.
- [ ] Écrire le rapport de mesures → baseline K1 (maîtrise ≥ 70 % à J+7) et K3 (rétention D30 ≥ 25 %).

---

## 🟢 WS1/WS2 — état après la session du 25/10 (soir)

- [x] **WS1.3** corpus 13 000 committé + explications trilingues 100 % (commit 7d60020) — script `fill_exercise_explanations.py` réexécutable.
- [x] **WS1.4/1.6** listes d'audit générées : [`AUDIT_TERMES_WS1.md`](AUDIT_TERMES_WS1.md) (44 chapitres minces, 519 fr==en, 0 doublon restant) — **traitement humain**.
- [x] **WS2.1 web** sous-système révision/chapitres ROUTÉ (commit 083b2a7) : #/chapter/:id, #/review, cadres ?lesson/?review dans le quiz, lazy-load 1,8 Mo, SW précâché, puce PathScreen. **Reste : routage Android** (ChapterScreen.kt/ReviewHubScreen.kt existent, non branchés dans la nav).
- [x] **WS2.3 (BUG-009) RÉSOLU SANS CHANGEMENT** : SM-2 classique correct, étiquettes honnêtes — décision documentée (commit d6e5e81).
- [x] **WS2.6a (BUG-020)** trophée promotion = meilleur palier atteint (commit d6e5e81).
- [ ] **WS2.2** : BUG-008 user_stats atomique · BUG-022 main thread · BUG-024/025 Android
- [x] **WS2.4 (BUG-019)** ✅ f6ac9a1/6dabf65 : hier calendaire (DST) + gel = 1 jour manquant, 2 plateformes, tests des 2 côtés.
- [x] **WS2.2 partiel (BUG-024/025)** ✅ 68f9107 : ProgressionManager mort supprimé (test converti vers la classe vivante), XpOutcome alimenté réellement. Reste : BUG-008 user_stats atomique + BUG-022 main thread.
- [x] **WS3 dossier de release** ✅ [`RELEASE/BETA_PLAY_STORE.md`](RELEASE/BETA_PLAY_STORE.md) : runbook keystore, fiche store FR/AR/EN, CGU + disclaimer médical, ADR-3 (local-first, comptes opt-in post-pilote), checklist. Bloqué pour publication par : keystore (humain), relecture ≥150 (humain), BUG-029/ADR-2 (dev, WIP build.gradle).
- [ ] **WS2.5** : ADR-2 backend désactiver/réparer (deps mortes → INTERNET)
- [x] **WS2.6b (BUG-021)** ✅ e301d50 : chaînes via t() (fr/en/ar), VRAIE série en ligues, titre « Ligue d'entraînement » (honnête).
- [x] **WS2.7 (BUG-015/032/033)** ✅ e301d50 : harnais pilote-001 verdict autoritaire ; export préserve les cases [x] (vérifié) ; check-stories minimum 800.
- [x] **WS2.1b Android** ✅ 49d58a6 : ReviewHubScreen ROUTÉ (overlay + pilule parcours + VM StateFlow + delete), ChapterScreen/ReviewQueueDao versionnés. Reste dépendant du WIP : population de la file (repository) + entrée chapitres (ModulesScreen).
- [x] **WS4 levier 2 démarré** ✅ (dans e301d50) : calendrier hebdo objectif (7 cellules) sur le parcours web ; leviers déjà actifs : coffres, ligues honnêtes, gel 1 jour.
- [x] **WIP RÉSOLU** ✅ 918da75/c7c361d/18c9cd4 : fichiers non suivis requis versionnés (HEAD compilait sans eux !), lot utilisateur committé (étymologies+perles ×15 modules, 9 sons + SoundManager, heure de rappel persistée, backend functions). Arbre propre.
- [x] **WS5 livré** ✅ b3b9195 (web) : moteur de maîtrise unifié (`mastery.ts`, calcul pur : quiz 60 % + santé SM-2 40 %, 7 tests) · panneau « Mes faiblesses » (module prioritaire + 6 termes faibles cliquables) · **examen EMD** (`#/quiz?mode=exam&module=X` : 20 questions, chrono 15 min, renvoi aux chapitres ratés, seuil 50 %). **Reste WS5** : dictées/écoute médicale (50 audio = contenu humain à produire) · parité Android du moteur de maîtrise · prononciation on-device (phase 4, gated pilote).
- [x] **WS2.2c (BUG-008)** ✅ f5cc423 : mutateUserStats = section critique RMW ; 2 sites chauds convertis, 13 restants à convertir (mécanique posée).
- [x] **ADR-2 exécuté (DÉSACTIVER)** ✅ f5cc423 : 10 deps réseau/IA mortes retirées + plugin google-services → **permission INTERNET disparue du manifest**. Réactivation guidée le jour des ligues réelles.
- [ ] BUG-022 main-thread (langue au démarrage) : restant, maintenant débloqué — petite mécanique prefs à poser
- [x] **WS4 leviers 3/5/7** ✅ 32c8dbe : coffres cadencés (3 journées-objectif consécutives, calcul pur), cliffhanger « À suivre » en fin d'histoire (même chapitre, publié uniquement), certificat de maîtrise (≥ 90 %, offline, partageable). Leviers 1-2-4 (hub maîtrise, calendrier, ligue honnête) déjà en place. **✅ LEVIERS 6/8 + bots honnêtes (6d39b3d) — LES 8 LEVIERS SONT ACTIFS :**
- Levier 6 = RÉSOLU COMME RAPPELS LOCAUX : Android avait déjà 3 rappels locaux opt-in (révision SM-2, série, perle clinique), réglage horaire persistant, ≤ 1/jour, zéro serveur. Le push cloud est REJETÉ (function `notifications` = queue sans consommateur ; contredit ADR-2 et le non-goal anti-dark-pattern). Dormante pour ADR-2bis.
- Levier 8 = widget de série 2×2 ✅ (StreakWidgetProvider : Room direct, rafraîchi par le flux userStats + au toucher, fond vert médical, FR/AR).
- Bots web étiquetés « simulateur » (classement = entraînement, zéro ambiguïté).
- [ ] **WS2.7** : BUG-015 harnais pilote-001 · BUG-032/033 checkers

## 🟡 DEV (agent — sur demande, dans l'ordre)

### V0.4 — Release interne Play Store (prochain jalon dev)
- [ ] Keystore de production : `my-upload-key.jks` + variables `KEYSTORE_PATH` / `STORE_PASSWORD` / `KEY_PASSWORD` (déjà prévues dans `app/build.gradle.kts`).
- [ ] Procédure de build/release documentée (bus factor R6).
- [ ] Crash reporting privacy-first + `versionCode` incrémental.
- [ ] Fiche store FR/AR/EN + CGU + **disclaimer médical** (risque R2).
- [ ] ADR comptes/sync : local-first par défaut, cloud opt-in (dette D5).

### V0.2 — 44 chapitres trop minces
- [ ] Enrichir les termes des 44 chapitres reportés (1–2 termes, liste dans la sortie du générateur), puis régénérer — même gate relecture que le reste.

### V1 — Parité engagement (après V0)
- [ ] Porter les **ligues** sur Android (`LeaguesScreen` + états `domain/gamification`).
- [ ] Notifications push + rappel quotidien (FCM ; function `notifications` à câbler ; ≤ 1/jour, opt-in).
- [ ] Objectif quotidien paramétrable + série résiliente (gel hebdo honnête, sans paywall).
- [ ] Recherche Android (port de `SearchScreen`).
- [ ] Comptes opt-in + sync (Supabase Auth) si l'ADR V0.4 dit oui.
- [ ] Instrumentation produit minimale privacy-first (funnels D1/D7/D30).

### V2+ — voir le plan stratégique §9 (moteur de maîtrise, mode examen EMD, dictées, trilingue intégral…)

---

## Ordre recommandé

1. **Relecture en continu** (humain) — chaque lot approuvé est immédiatement publiée et utilisable par les étudiants du pilote.
2. **V0.4 en parallèle** (dev) — sans dépendre de la relecture.
3. **Pilote dès 150+ histoires publiées** — ne pas attendre les 814.
4. V1 ensuite, sur données du pilote.
