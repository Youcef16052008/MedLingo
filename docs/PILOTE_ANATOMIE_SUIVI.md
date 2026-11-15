# Pilote Anatomie — Suivi du cycle

> Source du plan : `docs/PLAN_PILOTE_ANATOMIE.md` (référence du cycle, daté du 11/11/2026).
> Ce fichier est le journal d'exécution : backlog, preuves, blocages, décisions, risques.
> Règle : aucune validation humaine (relecteurs, étudiants, résultats de pilote) n'est
> déclarée tant qu'elle n'existe pas réellement.

## État des lots

| Lot | Contenu | État | Preuves |
|---|---|---|---|
| A | Baseline, P0.1, P0.2, P0.4, P0.5 | **Exécuté le 11/11/2026** (voir ci-dessous) | Commandes datées §2 |
| B | P0.3, P1.1, corpus candidat | **Exécuté le 11/11/2026** (P0.3 + P1.1 + P1.2) | §2 (ajouts Lot B) + §3 |
| C | Parcours web P2 | **Exécuté le 11/11/2026** (P2.1–P2.3 + tests) ; **P2.4 audit a11y/petit écran/hors-ligne exécuté le 12/11/2026** (seul le zoom navigateur reste NON EXÉCUTÉ) | §2 (ajouts Lot C et P2.4) + §3 |
| D | Mesures P3 technique | **Exécuté le 11/11/2026** (P3.1 banques+moteur, P3.2 export, P3.3/P3.4 protocole) | §2 (ajouts Lot D) + §3 + `docs/PROTOCOLE_PILOTE_ANATOMIE_ETUDIANTS.md` |
| E | Relectures humaines | À faire | — |
| F | Observation étudiants | À faire | — |
| G | Port Android P4 | **Exécuté le 12/11/2026** (P4.1–P4.4 : parité, chargeurs, moteurs, stores, UI ; appareil NON EXÉCUTÉ) | §2 (Lot G) + §3 (Lot G) |
| H | Documentation P5 | **Exécuté le 12/11/2026** (README assaini + renvoi plan maître + guide d'usage `docs/PILOTE_ANATOMIE.md` ; correction UI Lot G découverte pendant H) | §2 (Lot H) + §3 (Lot H) |

## 1. Lot A — compte rendu (11/11/2026, branche `remediation/2026-11`)

### P0.1 — Tests reproductibles
- `web-react/src/domain/__tests__/lessons.test.ts` : chemins absolus machine (`C:/Users/...`)
  remplacés par une résolution relative au fichier (`fileURLToPath(new URL(..., import.meta.url))`).
- Les chemins `adb.exe` d'`android-e2e/*.py` sont hors chemin d'exécution web — signalés,
  non modifiés (diff minimal).

### P0.2 — Maîtrise par module
- Contrat typé unique : `MasteryInput.moduleId` requis ; lecture `moduleBest[moduleId]`
  (fin de la clé globale `"quiz"` jamais écrite, donc toujours 0).
- `QuizScreen.tsx` écrit via `recordModuleQuizBest` (helper partagé avec le test d'intégration).
- Garde `NaN`/bornes 0–100 ; doc : indicateur de progression, pas une certification.

### P0.4 — Verrou de publication des histoires
- `build_stories_corpus.py` : sans verdict → `draft`, sans exception (suppression de
  l'exception du pilote dev `clinical-symptoms-001`) ; décision à partir des verdicts
  du sidecar, pas de l'état hérité du builder.
- Défense AU CHARGEMENT sur les deux plateformes : web `src/learning/stories.ts`
  (`publicationStatus === "published" && language === "approved" && medical === "approved"`)
  et Kotlin `StoryModels.kt` (`parseStoryFile`), même filtre.
- Corpus régénéré : 814 histoires, **0 publiée**, pilote repassé `draft`, parité web/Android OK.
- Pas de faux verdict : `data/review-verdicts.v1.json` reste vide. Les tests utilisent des
  fixtures « publiées » en mémoire uniquement (commenté dans chaque fichier de test).
- Tests du chargeur étendus : combos de verdicts refusés, zéro approuvée gérée, version inconnue refusée.

### P0.5 — CI et checkout propre
- `web.yml` / `android.yml` : déclencheurs `push main` + `pull_request` (toutes branches) +
  `workflow_dispatch` (branche de travail) ; pas de duplication push+PR ; `permissions: contents: read`.
- `check:precache` ajouté au workflow web.
- `app/build.gradle.kts` : sans `debug.keystore` (non commité, .gitignore), le buildType
  `debug` retombe sur la signature debug standard d'AGP. Signature release inchangée,
  aucune clé commitée.

## 2. Commandes exécutées (résultats exacts, datés 11/11/2026)

Web, depuis `web-react` :
| Commande | Résultat | Code de sortie |
|---|---|---|
| `npm ci` | FAIL (EPERM, fichier verrouillé — antivirus/OS Windows) ; contourné par `npm install` (0 vulnérabilité) | 1 puis 0 |
| `npm test` (après modifications) | 31 fichiers / 5150 tests PASS | 0 |
| `npm run build` | PASS | 0 |
| `npm run check:i18n` | PASS (304 clés ×3) | 0 |
| `npm run check:seed` | PASS (4504 termes, 13000 exercices) | 0 |
| `npm run check:stories` | PASS (814 histoires, parité) | 0 |
| `npm run check:precache` | PASS | 0 |

Android, depuis la racine (JDK 21 + SDK présents sur CETTE machine, contrairement à
l'environnement d'audit) :
| Commande | Résultat | Code de sortie |
|---|---|---|
| `./gradlew :app:compileDebugKotlin` | PASS (avertissements de dépréciation préexistants) | 0 |
| `./gradlew :app:testDebugUnitTest` (suite complète) | PASS | 0 |
| `./gradlew :app:assembleDebug` | PASS | 0 |
| `./gradlew :app:lintDebug` | PASS | 0 |
| `./gradlew :app:connectedDebugAndroidTest` | **NON EXÉCUTÉ** — appareil/émulateur non connecté | — |

Lot B (P0.3), depuis `web-react` :
| Commande | Résultat | Code de sortie |
|---|---|---|
| `python3 ../tools/gen_exercises.py` | PASS — 13 000 exercices régénérés (MCQ 4390 / PAIR 4318 / GAP 4292) | 0 |
| `node scripts/exercise-quality.mjs` | **PASS — 0 erreur certaine** (avant correctifs : 1837 certaines) | 1 puis 0 |
| `npm run check:exercise-quality` | PASS | 0 |
| `npx vitest run src/domain/__tests__/exerciseQuality.test.ts` | 10/10 PASS | 0 |
| `npx vitest run pair+exerciseContract+i18n` | 16/16 PASS | 0 |
| `python3 ../tools_gen_seed_sql.py` | PASS — seed SQL réécrit (4504 termes, 13 000 exercices) | 0 |
| `npm run check:seed` | PASS | 0 |
| `python3 ../tools_align_points.py --check` | PASS (source Android absente : rien à valider) | 0 |
| `npm test` (après P0.3) | 32 fichiers / 5160 tests PASS | 0 |
| `npm run build` (après P0.3) | PASS | 0 |
| `npm run check:precache` (après P0.3) | PASS | 0 |

Lot B (P1.1 + P1.2), depuis `web-react` :
| Commande | Résultat | Code de sortie |
|---|---|---|
| `node scripts/build_pilot_manifest.mjs` | PASS — 30 concepts draft / 90 exercices, 0 fuite résiduelle | 0 |
| `npm run check:pilot` | PASS — schéma P1.1 respecté (30 concepts, v0.1.0) | 0 |
| `npx vitest run src/pilot/__tests__/pilotContent.test.ts` | 15/15 PASS | 0 |
| `npm test` (final, Lot B complet) | 32 fichiers / 5175 tests PASS | 0 |
| `npm run build` (final) | PASS | 0 |
| `npm run check:exercise-quality` (final) | PASS — 0 erreur certaine (13 000) | 0 |
| `npm run check:seed` (final) | PASS — 4504 termes / 13 000 exercices | 0 |
| `npm run check:i18n` (final) | PASS — 304 clés ×3, aucune manquante/désynchronisée/morte | 0 |
| `npm run check:stories` (final) | PASS — 814 histoires, parité | 0 |
| `npm run check:precache` (final) | PASS — 7 assets hashed | 0 |

Lot D (P3.1–P3.4), depuis `web-react` :
| Commande | Résultat | Code de sortie |
|---|---|---|
| `node scripts/build_pilot_measure.mjs` | 90 banques (30 concepts × A/B/C), 180 items, 0 collision d'ids | 0 |
| `npx tsc --noEmit -p tsconfig.json` | PASS (après corrections : déclaration `.d.mts` étendue, refs inutilisées) | 2 → **0** |
| `npx vitest run src/pilot/__tests__/pilotMeasure.test.ts` | 13/13 PASS | 0 |
| `npx vitest run src/pilot/__tests__/pilotMeasureStorage.test.ts` | 12/12 PASS (après corrections de tests : import manquant, ligne CSV d'en-tête, 2ᵉ restauration) | 1 → **0** |
| `npx vitest run src/screens/__tests__/pilotMeasureRoute.test.tsx` | 3/3 PASS (après correction du test : concept 1 = « Head », pas « Heart ») | 1 → **0** |
| `npm test` (final, lots A+B+C+D) | 33 fichiers / **5231 tests PASS** (+28) | 0 |
| `npm run build` (final) | PASS | 0 |
| `npm run check:i18n` (final) | PASS — 369 clés ×3, aucune morte | 0 |
| `npm run check:pilot` (final) | PASS — entraînement 30 concepts + mesure 90 banques A/B/C, contentVersion cohérentes | 0 |
| `npm run check:exercise-quality` (final) | PASS | 0 |
| `npm run check:stories` (final) | PASS | 0 |
| `npm run check:seed` (final) | PASS | 0 |
| `npm run check:precache` (final) | PASS | 0 |

Lot C (P2.1–P2.3), depuis `web-react` :
| Commande | Résultat | Code de sortie |
|---|---|---|
| `npx tsc --noEmit -p tsconfig.json` | PASS | 0 |
| `npx vitest run src/pilot` | 38/38 PASS (session 12, storage 11, content 15) | 0 |
| `npx vitest run src/screens/__tests__/pilotRoute.test.tsx` | 5/5 PASS | 0 |
| `npm test` (final, lots A+B+C) | 33 fichiers / 5203 tests PASS | 0 |
| `npm run build` (final) | PASS | 0 |
| `npm run check:i18n` (final) | PASS — 343 clés ×3, aucune morte | 0 |
| `npm run check:pilot` (final) | PASS | 0 |
| `npm run check:exercise-quality` (final) | PASS | 0 |
| `npm run check:stories` (final) | PASS | 0 |
| `npm run check:seed` (final) | PASS | 0 |
| `npm run check:precache` (final) | PASS | 0 |

Lot G (P4.1–P4.4), web depuis `web-react` puis Android depuis la racine (12/11/2026) :
| Commande | Résultat | Code de sortie |
|---|---|---|
| `npm run sync:pilot` | PASS — les 2 manifestes copiés octet-pour-octet vers `app/src/main/assets/pilot/` (124 828 + 149 092 octets) | 0 |
| `npm run check:pilot-parity` | PASS — parité web/Android vérifiée | 0 |
| `./gradlew :app:testDebugUnitTest --tests "*domain.pilot*" --tests "*data.pilot*"` | 77/77 PASS (itérations avec corrections de tests, voir §3) | 0 (final) |
| `./gradlew :app:testDebugUnitTest` (suite complète) | **422 tests / 50 classes, 0 échec** | 0 |
| `./gradlew :app:assembleDebug` | PASS | 0 |
| `./gradlew :app:lintDebug` | PASS (après correction : `java.time.Instant` interdit sous minSdk 24 sans desugaring — remplacé par `pilotIsoDate` SimpleDateFormat UTC, 6 erreurs lint corrigées) | 1 → **0** |
| `./gradlew :app:connectedDebugAndroidTest` | **NON EXÉCUTÉ** — appareil/émulateur non connecté | — |

Lot H (P5.1–P5.3), web depuis `web-react` puis Android depuis la racine (12/11/2026) :
| Commande | Résultat | Code de sortie |
|---|---|---|
| `npm test` | **39 fichiers / 5 231 tests PASS** (recomptage à frais : 39 est le nombre RÉEL de fichiers `*.test.ts(x)` sous `web-react/src` ; les tables antérieures disaient « 33 fichiers / 5 203 » pour le même type de run — artefact de comptage, corrigé ici) | 0 |
| `npm run check:i18n` | PASS — 369 clés ×3, aucune morte | 0 |
| `npm run check:seed` | PASS — 4 504 termes, 13 000 exercices (chiffres écrits dans le README) | 0 |
| `npm run check:pilot` + `check:pilot-parity` + `sync:pilot` | PASS (re-vérification après docs) | 0 |
| `./gradlew :app:testDebugUnitTest :app:assembleDebug` | PASS — **422 tests / 50 classes, 0 échec** après la correction UI décrite en §3 (Lot H) | 0 |

P2.4 — audit accessibilité / petit écran / hors-ligne du parcours web, depuis `web-react` (12/11/2026) :
| Commande | Résultat | Code de sortie |
|---|---|---|
| `npm run build` | PASS — 22 entrées précachées, cache `medlingo-web-ea324e29` | 0 |
| `npx tsc --noEmit -p tsconfig.json` | PASS — aucune erreur | 0 |
| `npm test` (suite complète, après correctifs) | **39 fichiers / 5 231 tests PASS** (identique à la baseline) | 0 |
| `npx vitest run src/screens/__tests__ src/pilot` | **10 fichiers / 81 tests PASS** (1ʳᵉ tentative : exit 1 sur `[vitest-pool] Failed to start forks worker` — aléa d'environnement, relance identique = 0) | 1 → **0** |
| `node scripts/check-precache.mjs` | PASS — 7 actifs hachés précachés | 0 |
| `node e2e/check_pilot_small.mjs` (390×844, FR puis AR-RTL) | PASS — **24/24 contrôles** : aucun débordement horizontal, contrôles dans les bornes, aucune cible tactile < 24 px, aucun contrôle de taille nulle ; captures `shots/pilot-small-fr.png` et `pilot-small-ar-rtl.png` | 0 |
| `node e2e/check_pilot_offline.mjs <PID preview>` (serveur coupé par le script, sans émulation CDP) | PASS — **6/6 contrôles** : SW actif, 22 entrées en cache, reload hors-ligne rend le pilote (FR), séance démarrable hors-ligne | 0 |
| Lighthouse (Chrome, `#/pilot?dev=1` / `#/pilot/mesure?dev=1`) | accessibilité **0,95** / **0,96** ; bonnes pratiques **1,0** ; SEO 0,8 (`robots.txt` invalide — hors périmètre pilote). Seul échec a11y : `.stat-chip.league` (2,86:1) — voir §3 | — |
| Zoom navigateur (Ctrl +/−) | **NON EXÉCUTÉ** — le zoom navigateur n'est pas pilotable depuis l'outillage ; substitué par un contrôle de non-débordement à 1000×700 | — |

## 3. Limites et NON EXÉCUTÉ

### P0.3 — Provenance et qualité des 13 000 exercices (11/11/2026)

**Provenance tracée** :
- `web-react/public/data/exercises.json` (13 000, ids textuels `{module}-{chapter}-l{N}-{index}`,
  types `gap/pair/mcq`) est produit par `tools/gen_exercises.py` à partir de `terms.json`.
- L'ancien générateur : RNG **non seedé** (irreproductible), niveaux aléatoires, points 10
  quel que soit le niveau, et fuite structurelle : `gap` écrivait
  `Complete : '{définition}' → {answer}` (réponse affichée dans la question, 3 langues) —
  **12 894 instances de fuite mesurées** sur l'ancien corpus.
- Le corpus « bulk » runtime (`bulkExercises.ts` / `BulkExerciseGenerator.kt`, 13 512, ids
  négatifs) est un pipeline SÉPARÉ, non affecté. `lessons.ts:263` fusionne les deux corpus
  au runtime.

**Correctifs générateur (`tools/gen_exercises.py`, réécrit)** :
- RNG seedé (`SEED = 20261111`) → régénération déterministe (deux exécutions = même fichier).
- Niveaux déterministes `((term.id + slot) % 6) + 1` ; points alignés sur la table Android
  `10 + (level-1)*5`.
- `gap` : VRAI trou (`______`) quand l'exemple/définition EN contient le terme, sinon prompt
  par définition SANS flèche ; jamais de réponse affichée.
- `mcq` : si le prompt FR cite le terme EN verbatim (terme emprunté identique, ex.
  « What is the medical English term for 'Rectum'? » → `Rectum`), bascule sur un prompt PAR
  DÉFINITION ; sans définition exploitable → skip (jamais de fuite produite).
  Distracteurs dédupliqués, réponse toujours dans les options.
- `pair` : sauté si la définition est absente, égale au terme, ou CONTIENT le terme verbatim.
- 6 tentatives/terme pour absorber les skips honnêtes tout en gardant la cible 13 000.

**Contrôle qualité (`web-react/scripts/exercise-quality.mjs` + `check:exercise-quality`)** :
- ERREURS CERTAINES (bloquantes) : `answer_after_arrow` (motif historique `'définition' →
  réponse`, i.e. flèche APRÈS la citation — la flèche À L'INTÉRIEUR d'une définition est
  légitime : notation chimique `5'→3'`, `pepsinogen → pepsin`), `answer_copied_in_question`
  (mot entier verbatim — un cognat type « Cardiaque »/« Cardiac » n'est PAS condamné),
  `blank_missing`, `invalid_options` (contrats mcq/matching/sentence_order distincts),
  `empty_answer`, `definition_equals_answer`.
- SUSPICIONS (non bloquantes) : `stray_options` (options sur un type à saisie libre).
- Tests : `src/domain/__tests__/exerciseQuality.test.ts` — 10/10 PASS (fixtures par règle,
  y compris le cas de fuite réel historique « → Ventricle » et le cognat légitime).

**Corpus régénéré (état final)** : 13 000 exercices — MCQ 4390 / PAIR 4318 / GAP 4292 ;
niveaux L1..L6 : 2161/2165/2168/2170/2170/2166 ; skips honnêtes : pair 20, mcq 7.
**0 erreur certaine** au contrôle qualité.

**Dépendances régénérées/vérifiées** : `tools_gen_seed_sql.py` réexécuté (seed SQL 4504
termes / 13 000 exercices) ; `check:seed` PASS ; `tools_align_points.py --check` → exit 0
(source Android `com.example` introuvable sur ce checkout : « ABSENT, rien à valider » —
le verrou points est celui du générateur, table Android L1..L6).

**Dette documentée (ids d'exercices)** : le format d'id est inchangé mais les index
(`-{index}`) ont changé suite à la régénération (ordre/compte par type modifiés). Les clés
de progression utilisateur référencant d'anciens ids peuvent pointer sur des exercices
absents — le loader ignore les ids inconnus (déjà le comportement). Accepté : le corpus
précédent était de toute façon irréproductible et fuyait massivement.

### P1.1 + P1.2 — Schéma éditorial pilote et corpus candidat (11/11/2026)

**Schéma (`src/pilot/types.ts`)** — chaque concept porte : id stable (`anatomy-u{unit}-{NN}-{slug}`),
référence historique facultative (`legacyRef` = id terms.json), unité (1..3) + ordre unique,
libellé EN + aides FR/AR, objectif de langue, définition simple EN/FR/AR + exemple,
variantes acceptées explicites, statut `draft|approved`, version de contenu, relectures
linguistique ET anatomique (statut, personne réelle, date ISO, version relue, références),
exercices `practice` séparés des `assessment` (objectifs P1.3 : recognize / understand /
recall, consignes et explications localisées), média facultatif avec licence/attribution/provenance.

**Validation (`scripts/pilot-schema.mjs` + façade `src/pilot/content.ts`)** :
- IDs uniques (concepts + exercices au niveau global), `legacyRef` vérifié contre terms.json,
  langues présentes, options distinctes, réponse dans les options (mcq), pas d'options
  parasites en saisie libre, variantes non vides et non dupliquées ;
- Verrou de publication : `approved` ⟺ les DEUX relectures sont `done`, par une personne
  réelle, datées, et portant sur la version courante — un bump de contenu invalide (erreur
  bloquante, jamais un silence) ; `contentVersion` du concept ≠ manifeste = erreur ;
- Fuite interdite (leçon P0.3) : la réponse ne doit jamais apparaître verbatim (mot entier,
  insensible aux accents) dans une consigne ;
- Couverture P1.3 : les trois objectifs requis pour chaque concept.

**Corpus candidat P1.2 (`data/pilot-anatomy.v1.json`, généré par `scripts/build_pilot_manifest.mjs`)** :
- 30 concepts (10 par unité : parties du corps / organes principaux / localisation
  anatomique), 90 exercices (3 par concept : QCM reconnaissance en évaluation, QCM
  compréhension en contexte en entraînement, saisie libre de rappel en entraînement) ;
- Sélection MANUELLE de 30 `legacyRef` depuis terms.json (ids réels, ex. Heart ← #1,
  Superior ← #113, Medial ← #115), chaque concept portant une `selectionNote` = hypothèse
  de fréquence/utilité À FAIRE EXAMINER par les relecteurs ;
- Templates sans fuite avec bascule automatique sur un vrai trou `______` quand la
  réponse apparaît (mot entier, insensible aux accents) dans la consigne candidate —
  rapport de build : 30×rec:def, 26×und:blank-ex, 4×und:ctx-fr, 23×recall:fr, 7×recall:def
  (les 7 rappels par définition correspondent aux termes identiques FR/EN ou cités en FR :
  Abdomen, Pelvis, Médial/Latéral/Proximal/Distal, etc.) ;
- Distracteurs déterministes (FNV-1a sur l'id de concept, jamais `Math.random`),
  tirés en priorité des libellés de la même unité (plausibilité) ;
- **Honnêteté** : contenu 100 % `draft`, toutes les relectures `pending` — aucune relecture
  humaine n'existe. `isConceptPublishable` (miroir du verrou P0.4) refuse tout à ce stade.
- Médias : aucun (pas d'image anatomique IA présentée comme référence validée).

**Tests** : `src/pilot/__tests__/pilotContent.test.ts` — manifeste réel validé sans erreur,
combos de relectures (approved sans relecture refusé, relecture sans personne/date refusée,
version relue ≠ courante refusée, bump de contenu invalide l'ancien approved), fuite
refusée, couverture P1.3, doublons d'ids refusés.

### Lot C — P2.1/P2.2/P2.3 parcours web pilote (11/11/2026)

**P2.1 — Navigation et entrée** :
- Route dédiée `#/pilot` (`App.tsx`, case dédiée) + carte d'entrée sur l'écran
  « Révision » (`PracticeScreen.tsx`). Aucune implication du moteur de quiz historique.
- Accueil : objectif, durée indicative (≈ 5 min / 9 exercices), aides FR/AR annoncées,
  phrase explicite « aucune promesse clinique, aucune certification ».
- Manifeste validé À L'EXÉCUTION (`validatePilotManifest` + check schemaVersion) ; erreur
  explicite affichée + bouton Réessayer. Le JSON est importé statiquement
  (`data/pilot-anatomy.v1.json`) → embarqué dans le bundle → précache SW automatique.
- **Verrou de publication au chargement** : seuls les concepts `isConceptPublishable`
  (les 2 relectures sur la version courante) alimentent une séance réelle — 0 concept
  approuvé ⇒ écran explicite « Aucun concept approuvé… » et AUCUN bouton de démarrage.
  Une URL directe ne contourne pas le verrou (la décision est dans le code du screen).
- Brouillons : aperçu développeur OPT-IN uniquement (`#/pilot?dev=1`), bandeau permanent
  « APERÇU DÉVELOPPEUR — contenu draft, jamais relu ».

**P2.2 — Séance et feedback** (`src/pilot/session.ts` pur + `src/screens/PilotScreen.tsx`) :
- Séance courte : 3 concepts × 3 exercices max (matrice P1.3 : reconnaissance en
  évaluation, compréhension en contexte et rappel en entraînement) ; compteur
  « étape X / Y » affiché ; interruption = quitter, reprise proposée à l'accueil.
- Feedback : verdict EN TEXTE (jamais la couleur seule) + explication localisée du
  manifeste ; aucune pénalité empêchant d'apprendre.
- Aide de langue (FR/AR du concept) : l'aide est ENREGISTRÉE — la réponse ne compte
  jamais comme « réussite autonome » (exigé par le plan).
- Fin de séance : concepts travaillés, réussites autonomes, avec aide, révisions
  proposées + garde-fou « ce résumé ne dit rien de ta maîtrise générale ».
  AUCUNE récompense de l'app principale (XP/gems/streaks) n'est distribuée ici.

**P2.3 — Persistance isolée** (`src/pilot/storage.ts`) :
- Clé propre `medlingo_pilot_anatomy_v1` (+ `_backup`) — le store global
  `medlingo_web_v1_*` n'est pas touché ; reset pilote distinct (testé : les clés globales
  survivent au reset pilote).
- Enregistré : contentVersion, sessionId, conceptId, exerciseId, horodatage, premier
  résultat (first_try/helped/error), aides, tentatives, état de séance.
- IDs pilote TEXTUELS uniquement — aucun passage par un contrat numérique (pas de SM-2).
- Horloge injectée dans TOUT le moteur pur ; horodatages en epoch ms (aucune comparaison
  de chaînes localisées).
- Export JSON versionné / import VALIDÉ : confirmation avant remplacement, sauvegarde
  préalable dans `_backup` (échec de sauvegarde = import REFUSÉ, pas de remplacement sans
  filet), restauration possible.
- Corrompu / mauvais schéma / autre version de contenu / quota / stockage absent :
  messages distincts et explicites — jamais de « sauvegardé » mensonger. Les tests
  simulent QuotaExceededError.

**Tests du lot** : `pilotSession.test.ts` (12 : idempotence double-soumission, aide ≠
autonome, variantes acceptées, reprise/invalider au changement de version ou de concepts),
`pilotStorage.test.ts` (11 : corruption, schéma, quota simulé, export/import aller-retour,
backup, reset isolé), `pilotRoute.test.tsx` (5 : verrou actif sur le manifeste réel
100 % draft — aucun démarrage possible ; aperçu `?dev=1` + bandeau ; séance complète
y compris aide et saisie libre ; interruption + reprise à la bonne étape).

### P2.4 — limites levées le 12/11/2026

Les trois limites initialement listées ici (clavier/focus/contraste/zoom, petit écran,
2ᵉ visite hors-ligne) ont été traitées dans l'audit P2.4 : voir **§3 — P2.4** ci-dessous
(résultats, constats réels et correctifs). Seul le **zoom navigateur** reste NON EXÉCUTÉ
(outillage incapable de le piloter). L'audio/TTS reste hors périmètre (facultatif au plan).

### Lot D — P3.1/P3.2/P3.3/P3.4 mesure technique (11/11/2026)

**P3.1 — Banques de mesure et moteur** :
- Fichier de mesure SÉPARÉ de l'entraînement : `data/pilot-anatomy-measure.v1.json`
  (générateur `scripts/build_pilot_measure.mjs`, entrée = manifeste d'entraînement +
  terms.json). **180 items** = 30 concepts × 3 versions parallèles A/B/C (mapping fixe
  T0→A, T1→B, T2→C, partagé moteur/générateur) × 2 dimensions (reconnaissance MCQ,
  rappel libre sans aide). Version B tourne les consignes sur une source différente
  (description FR / définition EN) — questions différentes, vocabulaire comparable.
- **Garde structurelle « stockage ≠ exposition »** : ids de mesure disjoints des ids
  d'entraînement — collision = erreur du générateur ; test de non-contamination sur le
  manifeste RÉEL (pilotMeasure.test.ts).
- Fallback anti-fuite P0.3 identique à l'entraînement (trou `______` si le terme
  apparaît verbatim dans la consigne) ; validation intégrée à `check:pilot` (nouveau
  validateur `validatePilotMeasureManifest` : versions parallèles attendues A/B/C,
  cross-référence concepts, pas d'`explanation` dans un item de mesure = pas de
  feedback correctif embarqué, couverture minimale reconnaissance+rappel par version,
  cohérence de contentVersion entraînement↔mesure bloquante).
- Moteur pur `src/pilot/measure.ts` : AUCUN feedback correctif (l'enregistrement ne
  produit pas de verdict affichable) ; score objectif = correct / items valides
  effectivement présentés ET traités, dénominateur explicite PAR DIMENSION ; item
  ambigu = invalidé ET TRACÉ (raison + horodatage), exclu du dénominateur ; absence de
  test ≠ score nul (séance vide ⇒ `hasScore: false`, jamais « 0 » affiché) ; interruption
  consignée ; durée de réponse observée passivement (aucun chrono stressant) ;
  **délai réel T1→T2 enregistré en epoch ms** (jamais prétendu = 7 jours ; null si T1
  introuvable) ; reprise invalidée au changement de version de contenu.
- Séquencement protocolaire : T1 bloqué sans T0 fini, T2 bloqué sans T1 fini (raisons
  explicites, testées).
- **Limite assumée (aucun contenu inventé)** : la dimension « compréhension en phrase
  nouvelle » (exigée pour T1 par le plan) N'EST PAS générée — composer des phrases
  neuves non travaillées est un travail humain à relire ; les phrases existantes sont
  déjà servies à l'entraînement. Le moteur supporte la dimension ; la banque ne la
  contient pas ; le manque est documenté dans le protocole (§2) et dans `generatedFrom`.

**P3.2 — Export local minimal** (`src/pilot/measureStorage.ts`, écran de mesure) :
- Namespace SÉPARÉ `medlingo_pilot_anatomy_measure_v1` (+ `_backup`) ; reset de mesure
  sans toucher l'entraînement (testé), et réciproquement la mesure n'écrit jamais dans
  la progression (aucun import de module de progression — pas de réinjection SM-2).
- **Pseudonyme** éditable, sans email/nom/donnée patient ; avertissement affiché.
- Export JSON versionné (kind/schemaVersion/contentVersion/pseudonyme/
  `aidsUsedDuringMeasure: false`/séances : temps, versions, itemId, résultat, durée
  observée, état terminé/interrompu, délai réel T1→T2) + **CSV lisible** (une ligne par
  item traité ; séance sans item traité = ligne de séance à cellules vides, JAMAIS un
  faux « 0 » ; échappement virgules/guillemets testé).
- Transmission manuelle uniquement ; écran : « ne partagez le fichier qu'avec votre
  consentement. Un JSON local ne prouve pas l'absence de triche. » Aucune télémétrie.
- Import de mesure validé : version de contenu différente REFUSÉE (mesure non
  comparable), backup préalable, restauration possible.

**Écran de mesure** (`src/screens/PilotMeasureScreen.tsx`, route `#/pilot/mesure`,
entrée depuis l'accueil pilote) : MÊME verrou de publication que l'entraînement — 0
concept approuvé ⇒ aucun démarrage possible, message explicite ; drafts en aperçu
développeur `?dev=1` avec bandeau ; après enregistrement l'item suivant est servi sans
aucun verdict ni correction affichée (testé : ni « Correct ! » ni réponse révélée) ;
aucun score affiché pendant le cycle (T0 pourrait contaminer l'entraînement/T1/T2) ;
les données partent par l'export.

**P3.3/P3.4 — Protocole humain** (`docs/PROTOCOLE_PILOTE_ANATOMIE_ETUDIANTS.md`,
NOUVEAU — le protocole historique « histoires vs quiz » reste inchangé pour son cycle) :
5 volontaires, consentement/retrait/sans enjeu de note, pseudonyme, suppression et
conservation à fixer par écrit avant collecte (non inventées), observation du premier
démarrage sans guider, abandons consignés, score objectif à dénominateur explicite, et
**critères de décision PRÉ-ENREGISTRÉS datés du 11/11/2026** (stop technique, rework
UX/pédagogie, itération anatomie) écrits AVANT tout résultat. Tableau de résultats VIDE
portant la mention « à renseigner après observation ».

**Tests du lot** : `pilotMeasure.test.ts` (13), `pilotMeasureStorage.test.ts` (12),
`pilotMeasureRoute.test.tsx` (3) — 28 nouveaux.

### Lot G — P4 port Android (12/11/2026)

**P4.1 — Parité d'artefacts (export déterministe + garde)** :
- `web-react/scripts/sync_pilot_android.mjs` (npm `sync:pilot`) : copie OCTET-POUR-OCTET des
  2 manifestes (`pilot-anatomy.v1.json` + `pilot-anatomy-measure.v1.json`) vers
  `app/src/main/assets/pilot/` — même mécanisme que l'asset histoires.
- `web-react/scripts/check-pilot-parity.mjs` (npm `check:pilot-parity`, pattern
  `check:stories`) : désaccord = exit 1. L'oubli d'export après une édition web est
  bloquant, pas silencieux.
- Un test Kotlin recharge l'asset RÉEL embarqué (pattern `StorySessionEngineTest` :
  `File("src/main/assets/…")`) — la sémantique est prouvée sur les données servies.

**P4.2 — Chargeurs Kotlin typés** (`domain/pilot/PilotModels.kt`,
`PilotMeasureModels.kt`, kotlinx.serialization, `ignoreUnknownKeys`) :
- gate `schemaVersion` fail-fast (`PilotSchemaException`, pattern `parseStoryFile`) ;
- erreurs structurelles bloquantes : ids dupliqués (concepts, exercices, items — global),
  `conceptId`/`version` hors banque, réponse vide, QCM < 2 options ou réponse absente des
  options (comparaison `pilotNorm`), libellé/définition vides, banque vide, version hors
  mapping parallèle, item sans `source`, dimension inconnue, couverture minimale
  reconnaissance+rappel par banque ;
- **recoupement `contentVersion` entraînement↔mesure** au chargement — un désaccord lève
  franchement (banques à regénérer, jamais une survivance silencieuse) ;
- verrou de publication `isConceptPublishable` — miroir exact du web (statut `approved`
  ET les DEUX relectures `done` sur la version courante) ;
- DEUX normalisations distinctes, comme côté web : `pilotNorm` (miroir `norm` de
  `pilot-schema.mjs`, pour la VALIDATION) et `pilotNormalizeAnswer` (miroir
  `normalizeAnswer` de `domain/scoring.ts` — ponctuation supprimée — pour l'ÉGALITÉ de
  réponse ; `fillScore === 1`, pas de Levenshtein, near-miss = erreur).
- Découverte de parité notable : le web décide l'égalité de réponse pilote avec
  `scoring.ts`, PAS avec le `norm` du validateur — le port reproduit exactement cette
  distinction (testé).

**P4.3 — Moteurs purs 1:1 et stores isolés** (`domain/pilot/PilotSessionEngine.kt`,
`PilotMeasureEngine.kt`, `data/pilot/PilotProgressStore.kt`, `PilotMeasureStore.kt`) :
- `PilotSessionEngine` : état sérialisable, horloge injectée, `recordPilotAnswer`
  IDEMPOTENT (premier verdict figé : first_try/helped/error), aide consultée ⇒ jamais
  `first_try`, aide POST-réponse sans effet sur le verdict déjà consommé, reprise
  invalidée sur autre `contentVersion`/concepts inconnus/schéma inconnu.
- `PilotMeasureEngine` : AUCUN verdict retourné par l'enregistrement, score à
  dénominateur explicite PAR DIMENSION, item invalidé = tracé (raison) et exclu,
  présenté-non-traité = `unanswered` (jamais compté 0), séance vide ⇒ `hasScore: false`,
  gardes T1 (exige T0 fini) / T2 (exige T1 fini), interruption ≠ temps fini, **délai
  réel T1→T2 en epoch ms** (null sans T1 fini).
- Stores : SharedPreferences nommés comme les clés web (`medlingo_pilot_anatomy_v1` +
  `_backup`, `medlingo_pilot_anatomy_measure_v1` + `_backup`), clé `state` = même blob
  JSON que le web (schemaVersion 1) — export/import interopérables web↔Android.
  **Pas de migration Room créée** : l'état pilote est une petite blob JSON isolée, le
  besoin réel d'une table n'existe pas (décision documentée, plan P4 « vérifier le
  besoin réel ») ; le store historique (Room `medlingua_dz.db`, SM-2, quêtes) n'est
  JAMAIS touché — isolation testée dans les DEUX sens (reset entraînement ⇒ mesure
  intacte ; reset mesure ⇒ entraînement intact).
- Retours typés (jamais de throw sur les chemins persistés) : corruption /
  mauvais schéma / quota / stockage indisponible / autre version = codes distincts
  affichés à l'écran — jamais de « enregistré » mensonger ; `commit()` synchrone pour
  un résultat de sauvegarde honnête.
- Export JSON versionné (exportedAt ISO UTC, `aidsUsedDuringMeasure: false`) + CSV
  (une ligne par item traité ; séance sans item traité = cellules VIDES, jamais un
  faux 0 ; échappement virgules/guillemets ; délai réel exporté) ; import de mesure
  REFUSÉ si contentVersion différente ; backup avant import confirmé ; restauration
  possible.
- Format de date : `pilotIsoDate` (SimpleDateFormat UTC, miroir `toISOString`) —
  `java.time.Instant` est interdit sous minSdk 24 sans desugaring (6 erreurs lint
  corrigées).
- Les ids pilote restent TEXTUELS ; rien n'entre dans un contrat numérique (SM-2/Room).

**P4.4 — UI Compose** (`ui/screens/PilotScreen.kt`, câblée dans `MainActivity`) :
- Entrée : carte « 🔬 Pilote Anatomie — étude pilote, sans points, sans SM-2 » sur
  `PathScreen` (sous la carte histoire), overlays plein écran `showPilot` /
  `showPilotMeasure` (pattern `showStory`), `BackHandler` dédié (retour mesure →
  entraînement).
- Écran d'entraînement : verrou de publication appliqué AU SCREEN (miroir web) —
  0 concept approuvé ⇒ message explicite « Aucun concept approuvé… » et AUCUN bouton
  de démarrage (la partie mesure reste accessible) ; 3 concepts × 3 exercices,
  barre de progression + compteur « Exercice n / total » ; QCM (boutons) et saisie
  libre (champ + Vérifier) ; bouton d'aide (FR/AR du concept, jamais la langue
  cible) — l'aide est enregistrée et la réussite n'est jamais comptée autonome ;
  verdict TEXTUEL après réponse : « sans aide ✓ » / « avec aide » / « Incorrect ✗ »
  (premier essai, jamais réécrit) ; bilan final : réussites autonomes / après aide /
  erreurs → révisions proposées, AUCUNE récompense (XP/gems/streak) distribuée ;
  reprise proposée si la séance persistée reste compatible ; reset pilote isolé.
- Écran de mesure : MÊME verrou de publication ; pseudonyme éditable (donnée
  minimale) ; cartes T0/T1/T2 avec descriptif, état « Fait ✓ », raison de blocage
  explicite ; après enregistrement l'item suivant est servi SANS AUCUN verdict ni
  correction affichée ; AUCUN score affiché pendant le cycle ; interruption = quitter
  (consignée, jamais convertie en score) ; export JSON/CSV via le partage Android
  (transmission manuelle, consentement affiché) ; reset de mesure isolé.
- Aperçu développeur : build DEBUG uniquement (miroir web `?dev=1`) — le contenu
  draft est exécutable sous bandeau permanent « NON approuvé » ; les builds release
  ne voient que le contenu approuvé. Aucun contournement en release.
- i18n : 53 clés `pilot*`/`measure*` ajoutées dans `Strings` ×3 (ar/fr/en), fallback
  FR ; RTL géré par le `LocalLayoutDirection` existant de `MainActivity` ; le
  contenu EN (consignes, définition) reste LTR/anglais sous interface arabe.
- TTS : non intégré au pilote (facultatif au plan ; rien n'est affirmé sur un mode
  audio).

**Tests Kotlin du lot** (77 nouveaux, dont 5 classes sur l'asset RÉEL embarqué) :
- `PilotModelsTest` (13) : manifeste réel (30 concepts/90 exercices, 0 publiable,
  ids textuels), `pilotNorm`/`pilotNormalizeAnswer`, toutes les combos du verrou
  (approved sans relecture refusé, version relue ≠ courante refusée), erreurs
  structurelles (schéma inconnu, doublons, QCM invalide).
- `PilotMeasureModelsTest` (9) : asset réel (90 banques/180 items A/B/C), mapping fixe
  T0→A/T1→B/T2→C, **ids de mesure disjoints des ids d'entraînement** (garde
  stockage≠exposition sur les données réelles), absence de champ de feedback dans le
  modèle, recoupement contentVersion, banque vide/sans recognize/dimension inconnue
  refusées.
- `PilotSessionEngineTest` (11) : idempotence double-soumission, aide ≠ autonome,
  aide post-réponse sans effet, variantes acceptées, near-miss = erreur, reprise
  invalidée (version/concepts/schéma), progression jusqu'à `finished`.
- `PilotMeasureEngineTest` (13) : aucun verdict, sélection d'items par version,
  dénominateurs explicites par dimension, invalidation tracée, unanswered ≠ 0,
  `hasScore: false` à vide, gardes T1/T2, interruption ≠ fini, délai réel T1→T2,
  reprise invalidée.
- `PilotProgressStoreTest` (10) / `PilotMeasureStoreTest` (11) (Robolectric
  `@Config(sdk=[34])`, SharedPreferences) : round-trips, corruption, schéma inconnu,
  séance d'une autre contentVersion invalidée, stats (premier verdict figé, miroir
  web `mergeExerciseStat`), export/import aller-retour, version différente refusée,
  backup + restauration, **isolation bidirectionnelle** des deux namespaces.

**Corrections effectuées pendant l'itération** (transparence) : bugs de tests
(ids générés aléatoirement comparés entre deux sessions distinctes, attentes
fausses sur la fusion des stats — le web conserve le PREMIER verdict, le port
fait pareil), et un ajustement moteur aligné sur le web (égalité de réponse via
la normalisation `scoring.ts`, ponctuation supprimée) ; 6 erreurs lint
`java.time.Instant` corrigées par `pilotIsoDate`. Rien n'a été masqué, aucun test
affaibli : chaque échec intermédiaire est tracé ci-dessus (exit 1 → 0).

### Limites Lot G (NON EXÉCUTÉ / documenté)
- `./gradlew :app:connectedDebugAndroidTest` : **NON EXÉCUTÉ** — aucun appareil ni
  émulateur connecté. Aucune affirmation d'« exécuté sur appareil » : le plan P4
  exige des parcours appareil réels (FR/AR, hors-ligne, interruption, accessibilité,
  stockage) — ils restent À FAIRE sur device.
- Tests UI Compose (navigation/reprise/aides/fin/mesure en instrumentation) : non
  écrits sans device ; les moteurs et stores sont couverts par 77 tests JVM.
- Aucune signature release modifiée, aucune publication store (conforme plan P4).

### Lot H — P5 documentation et livraison (12/11/2026)

**Correction découverte pendant le lot H (suivi du Lot G, transparence)** :
l'UI Compose `PilotScreen.kt` appelait `Strings.get(...)` avec des clés
camelCase (`pilotTitle`) alors que la map Android est snake_case
(`pilot_title`, convention du projet) — `Strings.get` retombe sur la clé
brute : l'écran aurait affiché les noms de clés au lieu des libellés. La
compilation ne détecte pas ce lookup et **aucun test ne couvrait les
libellés UI** : c'est passé inaperçu au lot G, découvert en relisant le code
pour la doc. Corrections appliquées :
- 49 littéraux de clés de `PilotScreen.kt` convertis en snake_case ;
- les codes d'avertissement des stores/moteurs restent camelCase (CONTRAT
  web : `pilotDataCorrupted`, `pilotImportVersion:<version>`,
  `measureNeedT0`… — même forme côté web `tKey()`) ; conversion au point
  d'affichage via helper `warnKey()` (10 codes mappés, suffixe `:version`
  tronqué) ;
- clé manquante de parité `measure_no_personal_data` ajoutée ×3 + note
  affichée sur l'écran de mesure (miroir web `measureNoPersonalData`).
Aucun test n'a été modifié ; `./gradlew :app:testDebugUnitTest` relancé :
422 / 0 échec (§2, Lot H). Enseignement tracé pour la suite : les libellés
UI Compose ne sont couverts par aucun test automatisé — la validation
appareil (parcours visuel FR/AR/EN) reste le seul contrôle réel.

**P5.1 — README assaini** :
- consignes Gemini/AI Studio retirées (étape `.env`/`GEMINI_API_KEY` de la
  section Android Studio + liste numérotée AI Studio) — inutilité vérifiée
  dans le code le 12/11/2026 (aucun `Gemini`/`GEMINI_API_KEY` dans
  `app/src` ni `web-react/src` ; seuls vestiges : `.env.example` boilerplate
  AI Studio + docs archivées, signalés comme sans effet) ;
- chiffres périmés remplacés par des chiffres frais datés : statut du
  25/10 → 12/11 avec état du pilote (0 approuvé), seed
  « 4 504 termes, 112 exercices » → « 13 000 exercices » (check:seed),
  web « 143/143 tests » → « 39 fichiers / 5 231 tests au 11/11/2026 »,
  i18n « 164 clés » → « 369 clés ×3 », parité « 127 tests » → même
  recomptage ;
- note signature debug alignée sur le code réel (`debug.keystore`
  conditionnel, aucune ligne à supprimer — l'ancienne consigne était
  obsolète).

**P5.2 — Renvoi en tête de `docs/PLAN_MAITRE_2026.md`** : le cycle courant
(pilote Anatomie) est signalé au-dessus des constats historiques du
25/10/2026, qui sont conservés intacts et explicitement datés (exigence §9
du plan pilote : ne pas transformer les anciens audits en faits actuels).

**P5.3 — Nouveau guide d'usage `docs/PILOTE_ANATOMIE.md`** : accès et verrou
de publication (0 approuvé ⇒ pas de bouton), aperçu développeur
(web `?dev=1` / Android debug-only, bandeau permanent), isolation et
retrait du parcours, parité de contenu (`sync:pilot` /
`check:pilot-parity`), schéma de données et rétention (clés identiques
web/Android, aucune donnée perso, stockage historique jamais touché),
export/import/reset (noms de fichiers exacts, CSV à cellules vides jamais
faux 0), gestion des versions de contenu, limites datées. Le guide
documente le comportement du code et ne contient AUCUN résultat d'étude.

**Commandes du lot** : voir §2 (Lot H).

### P2.4 — audit accessibilité, petit écran et hors-ligne du parcours web (12/11/2026)

Périmètre : `#/pilot?dev=1` et `#/pilot/mesure?dev=1` (aperçus dev ; les manifestes
réels restent 100 % `draft`, donc le parcours réel ne démarre pas).

**Clavier / focus — PASS.** Ordre de tabulation cohérent, aucun piège au clavier ;
focus visible (contour plein, 2,67 px) sur chaque élément atteint ; session complète au
clavier (focus + Entrée sur « Commencer la séance », Maj+Tab vers une option QCM,
Entrée) → le verdict est rendu en **texte** (« Pas encore — regarde la correction » +
réponse + définition), jamais par la couleur seule.

**Contraste — PASS après correctif (constat réel).** `src/main.tsx` n'importe QUE
`styles/hybrid-design.css` ; `.btn` et `.stat-chip` vivaient dans `styles.css` /
`duolingo.css`, jamais importés → les boutons tombaient au style UA, à **1,03:1**.
Correctif : section « restauration P2.4 » ajoutée à `hybrid-design.css`
(`.btn` #0F766E / blanc = 5,47:1 ; `.btn.ghost` `var(--ink)` = 16,42:1 clair et
18,95:1 sombre ; `.btn.danger` #B91C1C = 6,47:1 ; `.block`/`.small`/`:disabled` ;
famille `.stat-chip` portée à parité Android). Variantes assignées explicitement
(`primary`/`ghost`/`danger`) dans `PilotScreen.tsx` et `PilotMeasureScreen.tsx`.

**RTL — PASS après correctif.** Les contenus anglais héritaient du sens RTL (termes
multi-mots inversés) : `dir="ltr"` explicite sur le `h2` de `labelEn`, les options QCM,
les champs de saisie et `ex.answer`, dans les deux écrans.

**Cibles tactiles — PASS après correctif.** Champs texte hauts de 19 px (< 24 px,
WCAG 2.2 / 2.5.8) : `padding: 10px 12px ; fontSize: 1rem` sur les 3 champs visibles.
Les `input[type=file][hidden]` (déclencheur du bouton « Importer ») sont exclus à juste
titre.

**Petit écran 390×844 — PASS (24/24), FR et AR-RTL** via
`e2e/check_pilot_small.mjs` : aucun débordement horizontal, contrôles dans les bornes,
aucune cible < 24 px, aucun contrôle de taille nulle. Captures
`shots/pilot-small-fr.png` / `shots/pilot-small-ar-rtl.png`.

**Seconde visite hors-ligne — bug réel trouvé, cause racine isolée, corrigé et vérifié.**
Le service worker stockait des réponses conservant `Content-Encoding: gzip` (alors que le
corps rendu par `fetch()` est déjà décompressé) et `Vary: Origin` (vite preview, nginx,
CDN) : au reload hors-ligne, les sous-ressources JS/CSS échouaient en `net::ERR_FAILED`
et l'application s'affichait **vide**. Preuve par mini-serveur instrumenté servant `dist/` :
sans gzip ni `Vary` → OK ; avec gzip seul → KO ; avec gzip + `Vary` → KO ; en retirant
`Vary` à la mise en cache → OK. Correctifs :
- `public/sw.js` : nouvelle `putSanitized()` qui reconstruit la réponse sans
  `Content-Encoding` / `Content-Length` / `Vary` ; `install` passe à `Promise.allSettled`
  + `fetch(url, {cache:"reload"})` (une entrée de shell absente ne bloque plus
  l'installation — le contrôle build reste assuré par `check-precache.mjs`) ; les deux
  sites `cache.put` same-origin utilisent `putSanitized` ; branche polices intacte.
- `scripts/patch-sw-precache.mjs` : le nom de cache hashe désormais la liste précachée
  **et** la logique de `sw.js` — sinon un correctif de `sw.js` seul n'invalidait aucun
  cache client existant. Nouveau cache : `medlingo-web-ea324e29`.

**Lighthouse** (Chrome, build de production) : accessibilité **0,95** (pilote) et
**0,96** (mesure) ; bonnes pratiques **1,0** ; SEO 0,8 (`robots.txt` invalide — hors
périmètre pilote). Seul échec a11y : `.stat-chip.league` = **2,86:1** (#FEF3C7 sur
#D97706), deux nœuds enfants (« BRON », « 0XP ») — apparence de parité Android assumée,
**résiduel accepté et documenté** (non corrigé : changer le chip casserait la parité
visuelle avec l'app Android).

**Constat transverse NON corrigé (hors périmètre, mérite un lot dédié).**
`styles.css` (1 597 lignes, `.btn` à la ligne 312), `components.css`, `duolingo.css`,
`mobile.css`, `maquette5.css` et `tokens.css` ne sont importés par **aucun** point
d'entrée : hors pilote, l'application entière s'appuie sur des styles UA. L'angle mort
vient de `e2e/check_design.py`, qui clique les boutons sans jamais auditer leur style.
Recommandation : lot « CSS mort » dédié (inventaire + décision importer/nettoyer) plutôt
qu'un rapiéçage dans le lot pilote.

**NON EXÉCUTÉ** : zoom navigateur (Ctrl +/− non pilotable depuis l'outillage) —
substitué par un contrôle de non-débordement à 1000×700.

**Fichiers touchés** : `web-react/public/sw.js`,
`web-react/scripts/patch-sw-precache.mjs`,
`web-react/src/styles/hybrid-design.css`,
`web-react/src/screens/PilotScreen.tsx`,
`web-react/src/screens/PilotMeasureScreen.tsx`,
`web-react/e2e/check_pilot_small.mjs` (nouveau),
`web-react/e2e/check_pilot_offline.mjs` (nouveau), captures
`web-react/shots/pilot-small-*.png`. Scripts de diagnostic temporaires supprimés après
usage (aucun reste).

### Lot « CSS mort » — feuilles réellement chargées (15/11/2026)

**Cause racine (vérifiée sur l'historique).** Le commit `9a627dc` (7/11, « wip: snapshot
ADR-3… ») a remplacé dans `web-react/src/main.tsx` `import "./styles.css"` par
`import "./styles/hybrid-design.css"` sans reporter les classes que seule la feuille
historique définissait. Tout le reste de l'application (hors pilote) retombait sur les
styles par défaut du navigateur — le constat P2.4 ci-dessus n'était que le symptôme le
plus visible, sur les boutons.

**Mesure (extraction statique des `className` du JSX, `web-react/src/**/*.tsx`).**

| | classes |
| --- | --- |
| posées dans le JSX | 361 |
| couvertes par `hybrid-design.css` seule | 110 |
| couvertes par `styles.css` seule | 241 |
| couvertes par `duolingo.css` seule (dont 26 exclusives) | 68 |
| **couvertes avant ce lot** (`styles` + `hybrid`) | **245** (116 sans règle) |
| **couvertes après ce lot** (`styles` + `duolingo` + `hybrid`) | **288** (73 sans règle) |

Aucune des huit feuilles ne définit les 73 classes restantes.

**Correctif : `web-react/src/styles/entry.css` (nouveau), importé seul par `main.tsx`.**

```css
@layer legacy, design;
@import "./duolingo.css" layer(legacy);
@import "../styles.css" layer(legacy);
@import "./hybrid-design.css" layer(design);
```

Trois décisions, chacune justifiée par une mesure :

1. **Cascade layers plutôt qu'un ordre d'import.** Le socle contient des sélecteurs plus
   spécifiques que le design system (`.path-node.available.next`) : déclaré après, il
   écrasait `hybrid-design.css`. Avec les couches, une déclaration `design` gagne à
   spécificité quelconque, et `legacy` ne comble que les propriétés que le design system
   ne déclare pas. Vérifié dans le bundle : `@layer legacy,design;@layer legacy{…}`.
2. **`duolingo.css` est indispensable** : 26 classes du JSX n'existent que là (coffres
   `chest-*`, ligue `league-current|info|xp`, SM-2 `sm2-*`, trophées `trophy-icon|item|name`,
   `story-card`, `module-progress-bar`, `speak-btn`, `primary`/`secondary`). Sans elle, ces
   composants restaient sans style.
3. **`duolingo.css` avant `styles.css` dans la même couche.** Testé dans l'autre ordre :
   son `.card { background }` a la même spécificité que `.profile-hero` et écrasait le
   dégradé du profil → nom du profil **blanc sur blanc (1:1)**, mesuré. Le socle doit donc
   passer en dernier à spécificité égale.

**Effets de bord des couches, rétablis dans la couche `design`** (fin de
`hybrid-design.css`, section « Réimport du socle »). Le reset `button { color: inherit }`
du design system battait trois règles du socle :

- `.lang-switch button.active` : « FR » actif reprenait l'encre → **2,73:1** ;
- `.path-node .node-meta` : `--ink-3` sur le vert du nœud « prochaine leçon » → **2,52:1**
  (encre foncée rétablie : 9,06:1) ;
- `.freeze-buy:disabled` : le bouton « Congeler » désactivé reprenait l'encre normale et
  paraissait actif (gris `--ink-3` rétabli, 4,83:1).

**Contrastes corrigés (mesurés, thème clair et sombre).**

| Élément | Avant | Après |
| --- | --- | --- |
| `--ink-3` (deux `:root` de `hybrid-design.css`) | `#94A3B8` — 2,56:1 sur blanc, 2,36:1 sur `--bg` | `#5F6D82` — 5,25:1 / 4,83:1 |
| puce ligue `.stat-chip.league` (+ `b` dans `styles.css`) | `#D97706` — 2,86:1 | `#92400E` — 6,37:1 |
| puce série `.stat-chip.streak`, `.streak-pill`, `.profile-streak-line` | `#EA580C` — 3,35:1 / 3,56:1 | `#9A3412` — 6,88:1 / 7,31:1 |

Parité Android appliquée aux mêmes teintes : `GemsLeagueTopBar.kt` (ligue + série) et
`PathScreen.kt` (`StreakPill`).

**Mesure avant/après** (sonde Playwright maison, 6 routes — `path`, `profile`, `modules`,
`pilot?dev=1`, `review`, `flash` —, 617 éléments textuels, couleurs normalisées par canvas
pour gérer `color(srgb …)` et `color-mix()`, fonds translucides composités, textes posés sur
dégradé/image exclus) : **34 → 30 échecs AA** hors composants désactivés (path 12 → 8,
profile 12, modules 10, review 0, flash 0, pilote 0) ; 12 éléments non mesurables
(dégradés). Le total « avant » n'est **pas** comparable terme à terme : le layout défaillant
laissait 85 éléments non mesurables, donc une partie des échecs d'alors n'était même pas
mesurable.

**Lighthouse (Chrome, build de production, 15/11/2026)** : accessibilité **0,96**
(`#/pilot?dev=1`), **0,94** (`#/path`, `#/modules`), **0,91** (`#/profile`) ; **SEO 1,0 sur
les quatre routes** (0,8 avant) grâce à `web-react/public/robots.txt` (nouveau).

**Débordement horizontal corrigé** : `#/modules` en 390×844 débordait (`scrollWidth >
clientWidth`) ; plus aucun débordement après réimport, sur les quatre routes testées.

**Résiduels documentés, NON corrigés** (mesurés, décision explicite de ne pas les traiter
ici) :

- bandeaux d'unité `#E65100` / `#00897B` / `#0288D1` avec texte blanc : 3,79 / 4,32 / 3,86:1.
  Ces teintes viennent des données de module, partagées avec `InitialData.kt` : les changer
  est une décision de parité Android, pas un correctif CSS ;
- accents de module en 12 px : 3,17–4,36:1 (mêmes teintes, texte petit) ;
- libellés de trophées verrouillés `#90A4AE` en 8,96 px : 2,59:1 (composants inactifs) ;
- `header.topbar .brand-name em` (signalé par Lighthouse sur les quatre routes) ;
- `p.medi-bubble` (texte sur dégradé : non mesurable par la sonde, signalé par Lighthouse) ;
- **73 classes du JSX sans aucune règle dans les huit feuilles**, sur des écrans
  atteignables : `#/player`, `#/analytics`, `#/stories`, `#/search`, `#/tree`, les
  sous-parties `profile-*` du profil et `#/login` (quand `SYNC_ENABLED`). Certaines sont
  partiellement stylées en ligne (`PlayerScreen` : 69 attributs `style`/`className`).
  Cela demande un lot dédié (écrire les styles manquants écran par écran), pas un
  rapiéçage dans ce lot.

**Feuilles orphelines restantes** : `components.css`, `maquette5.css`, `mobile.css`,
`premium.css`, `tokens.css`. Mesure : aucune n'a de classe exclusive — tout ce qu'elles
définissent est déjà couvert par les feuilles chargées. Elles ne sont pas supprimées (leur
contenu n'a jamais été arbitré) mais ne sont plus une source de style. Inventaire écrit
dans `web-react/src/styles/README.md`.

**Android réparé au passage (pré-existant, sans lien avec le CSS).** Le commit `c1e7cdd`
avait laissé la branche **non compilable** : `:app:compileDebugKotlin` échouait avant toute
modification de ce lot.

- `MedLinguaDatabase.kt` référençait `MIGRATION_6_7` … `MIGRATION_13_14`, supprimées lors de
  la réécriture du fichier pour la v15 (seule `MIGRATION_14_15` subsistait). Restaurées
  depuis `9163f04` (dernière version qui les portait, avec `ensureEntityDrift` et
  `TermIdMigrationPlan`) — les tests `MigrationV10Test`, `MigrationV12Test` et
  `MigrationV13_14Test` les référencent, elles n'étaient donc pas facultatives.
- `KnowledgeGraphRepository` exposait `val nodes` / `val edges` non-`suspend` au-dessus
  d'appels DAO `suspend` → `suspend fun getAllNodes()` / `getAllEdges()`, appelants mis à
  jour dans `KnowledgeGraphViewModel`.
- `GraphExplorerScreen.kt` : `BorderStroke` importé depuis `androidx.compose.foundation.border`
  (paquet inexistant ; c'est `androidx.compose.foundation.BorderStroke`) et
  `Icons.Default.ArrowBack` / `Icons.Default.Close` utilisés sur des imports `outlined`.
  Corrigé selon la convention du projet (`Icons.AutoMirrored.Filled.ArrowBack`,
  `Icons.Default.Close`).

**Vérifications exécutées sur l'état final des fichiers (15/11/2026, codes de sortie réels).**

Web (`web-react/`) :

| Commande | Résultat |
| --- | --- |
| `npm run build` | **EXIT 0** — `✓ built in 17.01s`, `precached 22 entries, cache medlingo-web-42c7bd36` |
| `npx tsc --noEmit` | **EXIT 0** |
| `npm run check:i18n` | **EXIT 0** |
| `npm run check:seed` | **EXIT 0** |
| `npm run check:exercise-quality` | **EXIT 0** |
| `npm run check:pilot` | **EXIT 0** |
| `npm run check:pilot-parity` | **EXIT 0** |
| `npm run check:precache` | **EXIT 0** |
| `npm run check:stories` | **EXIT 0** |
| `npm run check:literals` | **EXIT 0** |
| `npx vitest run` | voir note ci-dessous |
| `e2e/check_pilot_small.mjs` | **EXIT 0** — 24/24 (FR + AR) |
| `e2e/check_pilot_offline.mjs` | **EXIT 0** — 6/6 |

**Note vitest — deux vrais défauts trouvés et corrigés en vérifiant (pas du bruit).**

Premier passage lancé **en parallèle** de `./gradlew :app:lintDebug` : `EXIT 1`, `31`
fichiers sur `39` seulement, `1` échec (`bulkExercises.test.ts` > « robustesse », *Test
timed out in 120000ms*) et `8` erreurs `[vitest-pool]: Failed to start forks worker`.
Second passage, Gradle terminé, machine au repos : **`EXIT 1` à nouveau**, mais cette fois
`31` fichiers **passés**, `5187` tests passés, `0` échec — et toujours `8` erreurs
`Failed to start forks worker`. Conclusion : ce n'était pas un artefact de charge. Le pool
`forks` (défaut) ne démarrait pas 8 workers sur cette machine (12 CPU logiques, Windows) ;
**8 fichiers de test n'étaient jamais exécutés** et le process sortait malgré tout en 1.
Autrement dit, `npm test` mentait dans les deux sens : des fichiers non exécutés d'un côté,
un exit 1 sans échec de test de l'autre.

Correctif 1 — `web-react/vite.config.ts` : `pool: "threads"` (commenté avec les mesures).
Mesure : `threads` → `39` fichiers, exit 0. Aucun test ne dépend d'une isolation par
processus (aucun `process.pid` / `child_process` / `process.exit` dans `src/**/*.test.*`).

Correctif 2 — le pool `threads` a fait remonter une tâche React qui se déclenchait après la
destruction de jsdom : `ReferenceError: window is not defined` levée depuis
`src/screens/__tests__/loginRoute.test.tsx`, `1` erreur non gérée → `EXIT 1` alors que les
`39` fichiers passaient. Cause : `@testing-library/react` n'active son cleanup automatique
que si `globals: true` (ce n'est pas la config ici), donc les arbres rendus restaient montés.
Correctif dans `web-react/src/setupTests.ts` : `afterEach(cleanup)` sous garde
`typeof document !== "undefined"` (le projet `domain` tourne en `node`, le hook y est un
no-op).

Correctif 3 — une fois le cleanup en place, `loginRoute.test.tsx` > « App routing: #/login »
a échoué **à raison**. Ce test ne passait que grâce au DOM laissé par le test précédent : il
cherchait un `input[type="email"]` dans tout le `document` après avoir monté `App` sur
`#/login`, or `App.tsx` fait `screen = SYNC_ENABLED ? <LoginScreen /> : <PathScreen />` et
`SYNC_ENABLED` vaut **false** par défaut (décision D2). Le test ne testait donc pas ce qu'il
annonçait. Réécrit en deux cas explicites : sync désactivée → la route ne monte pas de
formulaire (contrat D2) ; sync activée (via `vi.doMock` + `vi.resetModules`) → le formulaire
est monté. `3` tests, dont la couverture réelle des deux branches.

**Résultat final (deux passages consécutifs, machine au repos) : `npm test` → `EXIT 0`,
`39` fichiers, `5232` tests, `0` échec, `0` erreur non gérée, ~102 s** (contre ~274 s et
exit 1 avec `forks`).

Android (`./gradlew :app:testDebugUnitTest :app:lintDebug`, 15/11/2026) :

| | Résultat |
| --- | --- |
| `./gradlew` | **EXIT 0** — `BUILD SUCCESSFUL in 20m 35s`, `41 actionable tasks: 13 executed, 28 up-to-date` |
| `:app:testDebugUnitTest` | **422 tests, 0 échec, 0 erreur, 0 ignoré** (50 fichiers XML de résultats) |
| `:app:lintDebug` | **0 erreur, 72 avertissements** (`app/build/reports/lint-results-debug.txt`) |

`./gradlew :app:compileDebugKotlin` avait été vérifié séparément **EXIT 0, 0 erreur**
avant de lancer la suite. Non exécuté : `:app:connectedDebugAndroidTest` (aucun
appareil/émulateur) — voir §3, limites du Lot G.

**Fichiers touchés** : `web-react/src/styles/entry.css` (nouveau),
`web-react/src/main.tsx`, `web-react/src/styles.css`,
`web-react/src/styles/hybrid-design.css`, `web-react/src/styles/README.md` (nouveau),
`web-react/public/robots.txt` (nouveau), `web-react/src/screens/GraphExplorerScreen.tsx`
(littéraux FR passés par `t()` pour `check:literals`),
`app/src/main/java/…/ui/components/GemsLeagueTopBar.kt`,
`app/src/main/java/…/ui/screens/PathScreen.kt`,
`app/src/main/java/…/data/local/database/MedLinguaDatabase.kt`,
`app/src/main/java/…/data/repository/KnowledgeGraphRepository.kt`,
`app/src/main/java/…/ui/viewmodel/KnowledgeGraphViewModel.kt`,
`app/src/main/java/…/ui/screens/GraphExplorerScreen.kt`,
`web-react/vite.config.ts` (pool `threads`),
`web-react/src/setupTests.ts` (`afterEach(cleanup)`),
`web-react/src/screens/__tests__/loginRoute.test.tsx` (deux branches D2 réellement
couvertes). Scripts de diagnostic temporaires supprimés après usage (aucun reste).

### Limites transverses (rappel, non closes)
- P0.1 acceptation « depuis un checkout Linux propre » : non vérifiable depuis cette machine
  Windows ; la correction rend les chemins indépendants du poste, mais la preuve CI reste à
  obtenir au premier run du workflow.
- CI GitHub Actions : YAML mis à jour, mais aucun run réel observé (pas de push demandé).
- Navigation/navigateur : le parcours pilote a été exercé dans Chrome (Playwright) et
  audité par Lighthouse le 12/11/2026 (voir §3 — P2.4) ; aucune **relecture humaine**
  dans un navigateur n'a eu lieu, et le reste de l'application n'a pas été re-audité.

## 4. Prochaine tâche
Tous les lots techniques exécutables sans appareil sont faits (A, B, C dont P2.4, D, G, H).
Restant :
1. **Validation appareil du Lot G** (dès qu'un appareil/émulateur est disponible) :
   `./gradlew :app:connectedDebugAndroidTest` + parcours manuels réels (FR/AR/EN,
   hors-ligne, interruption, reprise, aides, fin, mesure T0, export, accessibilité).
2. **Lot E (humain)** :
   - Deux relectures traçables (linguistique + anatomique) sur la version `0.1.0`
     exacte des concepts ET des banques de mesure (`pilot-anatomy.v1.json` +
     `pilot-anatomy-measure.v1.json`) — seul chemin vers `approved` (le verrou est
     technique, rien à coder ; côté web ET Android, même verrou).
   - Composition humaine des phrases nouvelles pour la dimension « compréhension en
     phrase nouvelle » de T1/T2 — MANQUANT documenté (moteur prêt des deux côtés,
     banque à compléter, puis regénérer + revalider + re-synchroniser Android via
     `npm run sync:pilot`).
   - Fixer par écrit les modalités de suppression et la durée de conservation des
     données (protocole §3) — AVANT toute collecte.
   - Définir la liste des participants (5 volontaires) et le calendrier T0/T1/T2.
3. ~~**Lot « CSS mort »**~~ — **fait le 15/11/2026** (§3, lot « CSS mort »). Les feuilles
   sont désormais chargées via `web-react/src/styles/entry.css` (couches `legacy` /
   `design`), 288 des 361 classes du JSX sont couvertes. Reste de ce lot, à traiter
   séparément :
   - **73 classes du JSX sans aucune règle dans les huit feuilles**, sur des écrans
     atteignables : `#/player`, `#/analytics`, `#/stories`, `#/search`, `#/tree`, les
     sous-parties `profile-*` du profil et `#/login` (quand `SYNC_ENABLED`). Lot dédié,
     écran par écran (écrire les styles manquants), pas un rapiéçage.
   - **Contrastes résiduels mesurés et non corrigés** (bandeaux d'unité, accents de module
     en 12 px, libellés de trophées verrouillés, `brand-name em`, `p.medi-bubble`) —
     détail chiffré en §3.
   - **Cinq feuilles orphelines** (`components.css`, `maquette5.css`, `mobile.css`,
     `premium.css`, `tokens.css`) : mesurées sans classe exclusive, ni importées ni
     supprimées (contenu jamais arbitré). Inventaire dans `web-react/src/styles/README.md`.
   - Aucun **audit visuel humain** n'a eu lieu : les mesures sont automatisées
     (Playwright + Lighthouse), pas une validation esthétique.

## 5. Blocages humains ouverts
- Relectures linguistique et anatomique : aucune disponibilité connue à ce jour → le
  corpus pilote (entraînement ET banques de mesure) restera `draft`/candidat jusqu'à
  deux relectures traçables sur la version `0.1.0`.
- **Phrases nouvelles pour la compréhension de T1/T2** : à composer humainement puis
  faire relire — la mesure actuelle ne couvre que reconnaissance + rappel (manque
  documenté, non feint).
- Modalités de suppression / durée de conservation des données de mesure : à fixer par
  écrit avant la collecte (protocole §3).
- Aucun résultat de pilote : tableau à renseigner après observation uniquement.
- P3.3 observation terrain (5 volontaires) : NON EXÉCUTÉ — aucune observation n'a eu
  lieu ; rien n'est affirmé sur des participants réels.
- Validation appareil du Lot G : NON EXÉCUTÉ (aucun appareil/émulateur connecté) —
  `connectedDebugAndroidTest` et les parcours manuels device restent à faire ; aucune
  affirmation d'exécution sur appareil n'est faite.
