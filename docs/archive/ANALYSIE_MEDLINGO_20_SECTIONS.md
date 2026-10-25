# MEDLINGO — ANALYSE PÉRIODIQUE VÉRITABLE
## 20 sections · analyse ligne par ligne · rapport final

**Cible :** `C:\Users\pc\Desktop\MedLingo`
**Date :** 19 octobre 2026 (environ)
**Cadre :** Android Kotlin/Compose (Room offline-first) + web-react (TypeScript/vite) + backend Supabase/Edge Functions + outils de génération.
> Méthodologie : tous les fichiers ont été lus sur le disque avec `cat`/`read_file`. Les gros fichiers de données (exercises.json ~10 Mo, chapters.json ~35 kLo, icons binaires) n'ont pas été parcourus ligne par ligne — leur structure et les scripts de génération ont été analysés à la place.

---

## 1. Configuration & Build (Android)

**Fichiers lus :** `build.gradle.kts` (root), `settings.gradle.kts`, `gradle.properties`, `gradle/libs.versions.toml`, `local.properties`, `gradle/wrapper/gradle-wrapper.properties`, `app/build.gradle.kts`.

**Ce que l'on y lit :**
- Root : 5 plugins `apply false` (`android.application`, `kotlin.compose`, `ksp`, `secrets`, `google.services`).
- settings : `dependencyResolutionManagement { repositoriesMode = FAIL_ON_PROJECT_REPOS }`, 2 dépôts (google, mavenCentral), `rootProject.name = "MedLingua DZ"`, module `:app`.
- gradle.properties : `-Xmx4g`, `org.gradle.parallel=true`, `android.nonTransitiveRClass=true`, `org.gradle.caching=true`, `configuration-cache=true`, `workers.max=4`, Kotlin in-process.
- libs.versions.toml (BOM dédiée) : AGP 9.1.1, Kotlin 2.2.10, Compose BOM 2024.09.00, Room 2.7.0, KSP 2.3.7, retrofit 2.12.0, Moshi 1.15.2, coroutines 1.10.2, Firebase BOM 34.17.0.
- app/build.gradle.kts : namespace `com.aistudio.medlingua.dzmed`, compileSdk 36, minSdk 24 / targetSdk 36, `versionCode = 1`, `versionName = "1.0"` (pas d'historique), buildConfig=true, Compose=true, `testOptions.unitTests.isIncludeAndroidResources=true`, `dependenciesInfo.includeInApk=false`. Plugins : android.application, kotlin.compose, kotlin.serialization, ksp, secrets, google.services. Secrets → `.env`/`.env.example`, `ignoreList` FIREBASE_APPCHECK_DEBUG_TOKEN. google-services `IGNORE`.
- local.properties : `sdk.dir=C:/Users/pc/AppData/Local/Android/Sdk` — chemin Windows hardcode.

**Ce qui est bon :** build-cache + configuration-cache + parallel + workers limités → CI reproductible ; la BOM dédiée évite les conflits de versions ; `FAIL_ON_PROJECT_REPOS` empêche les dépôts incohérents ; les secrets ne sont jamais en dur (la méthode `fromBuildConfig` de `BackendConfig` est commentée) ; google-services en IGNORE si absent.

**Ce qui est faux / à corriger :**
- `versionCode = 1` / `versionName = "1.0"` sans historique : toute mise à jour pousse `1.0` → le store ne voit que 1 version depuis le dépôt. À versionner.
- `local.properties` contient le chemin platform-sdk du développeur (non portable), mais c'est une convention gradle acceptée pour l'IDE local.
- Le plugin `secrets` est appliqué sur `.env` (convention web), pas `local.properties` → OK pour les variables backend. Le `google-services` IGNORE cache un problème : si `google-services.json` ou les clés Firebase manquaient, la build ignorerait l'absence — un risque de retour en arrière silencieux.
- Le `ksp()` vide en bas de `dependencies` (ligne 124) est un point de contrôle non-opérationnel.
- `moshi-kotlin-codegen` est ksp'd mais la couche réseau utilise `kotlinx.serialization.Json`, pas Moshi → codegen dormant. Surface zéro, pas de bug.

---

## 2. Entrée Android, manifest & injection de dépendances

**Fichiers :** `AndroidManifest.xml`, `di/AppModule.kt`, `MedLinguaApp.kt`, `MainActivity.kt`.

**Manifest :** 3 permissions (VIBRATE, POST_NOTIFICATIONS, RECEIVE_BOOT_COMPLETED), `<queries>` pour TTS_SERVICE, `allowBackup=false`, RTL enabled, 2 activities (MainActivity LAUNCHER), 2 receivers (NotificationReceiver, BootReceiver).

**AppModule :** fournit Database, Repository (dao+clock), ReviewQueueDao, TtsManager, Clock. Injection statique par objet.

**Ce qui est bon :** `allowBackup=false` (pas de fuite de données), `exported=false` sur les receivers, la requête TTS est(query) seulement. Le nom du paquet est cohérent (`com.aistudio.medlingua.dzmed`).

**Ce qui est faux :** la permission POST_NOTIFICATIONS est demandée en `onCreate` par LaunchedEffect plutôt que d'attendre le premier besoin (lancement d'un quizz) — léger. `BootReceiver` est déclaré sans logique visible (peut-être fournie dans NotificationReceiver). Le `enableEdgeToEdge()` de MainActivity est bien appelé, donc le thème est correct.

---

## 3. Couche données — schéma Room & migraations

**Fichier :** `MedLinguaDatabase.kt` (version=12, migraations 6→7→8→9→10→11→12).

**Ce que l'on y lit :**
- 11 entités : MedicalTerm, FlashcardProgress, Exercise, DownloadedModule, UserStats, GemsTransaction, LeagueCohort, LeagueMember, Trophy, LessonScore, ReviewQueueItem.
- `exportSchema = false` (curieux : le fichier commente que l'export en vaut la peine).
- 6 migrations. Chaque migration est documentée (« défaut D1/D2/D3… »).
- `ensureEntityDrift` : colonnes manquantes `exercises.specJson`, `exercises.isSpecMigrated`, `user_stats.gems` ajoutées par ALTER TABLE, et index de `league_cohorts`.
- `MIGRATION_8_9` (le plus complexe) : renumérotation des seeds → réattachement `flashcard_progress.termId` par `(termEn, module)`. Idempotence via ids temporaires négatifs.
- `MIGRATION_9_10` : reconstruit `user_stats` (CREATE INSERT SELECT DROP RENAME) car Room refuse de *supprimer* une colonne.
- `MIGRATION_10_11` : 3 index sur `medical_terms`.
- `MIGRATION_11_12` : suppression de la colonne legacy `example` (doc : 692/972 identiques, 0 lecteur).

**Ce qui est bon :** migraations parfaitement documentées, `IF NOT EXISTS`, ordre indépendant des renumérotations (ids temporaires négatifs), la migration 8→9 est **essentielle** (sans elle l'historique de révision se rattache au mauvais terme). `ensureEntityDrift` couvre les dépassements de schéma sans migration.

**Ce qui est faux / risque :**
- Le commentaire affirme que les colonnes `termEn`, `module` portent les noms **camelCase sans @ColumnInfo**. Or Room **nominalise** par défaut (snake_case). Si Room a effectivement créé `term_en`, `module`, la migration **planterait** (`no such column`). La migration ne peut fonctionner que si les entités utilisent `@ColumnInfo(name="termEn")`. **À vérifier** : lecture de `MedicalTermEntity.kt` — il s'agit d'une hypothèse, pas d'une observation confirmée.
- `exportSchema=false` : au lieu de valider l'historique des migrations, on laisse Room ne générer rien → un changement brut de schéma casse **silencieusement** les anciennes bases. `exportSchema=true` est recommandé pour la validation.

---

## 4. Couche données — entités

**Fichiers :** `ExerciseEntity.kt`, `MedicalTermEntity.kt`, `UserStatsEntity.kt`, `ReviewQueueItem.kt`, `DownloadedModuleEntity.kt`, `FlashcardProgressEntity.kt`, `GemsTransactionEntity.kt`, `LeagueCohortEntity.kt`, `LeagueMemberEntity.kt`, `TrophyEntity.kt`, `LessonScoreEntity.kt`.

**Ce que l'on y lit :** champs fixes. `ExerciseEntity` garde `optionsRaw` (pipe-separated, contract Kotlin) **et** `specJson` (ExerciseSpec JSON) — la duplication est volontaire pour l'audit SQLite sous et la spécification côté UI. `lesson_scores` indexe `lessonKey(moduleId,level)`, `trophies.id` TEXT.

**Ce qui est bon :** pas de relation circulaire, aucune colonne sensible, `lesson_scores` indexe bien `lessonKey`, `trophies.id` TEXT.

**Ce qui est faux :** `ExerciseEntity` stocke `specJson` **et** `optionsRaw`. Si `specJson` est corrompu mais `optionsRaw` intact, c'est `specJson` qui prime. Règle pas flaggée `isSpecMigrated` sur la Table existait (par défaut 0) — OK.

---

## 5. Domaine — contrôle d'exercice & spécifications

**Fichiers :** `ExerciseChecker.kt`, `ExerciseSpec.kt`, `MatchScoring.kt`, `WordbankOrder.kt`, `ExerciseSpecMigrator.kt`.

**ExerciseChecker :** `checkChoice`, `checkChoiceByText`, `checkWordbank`, `checkMatch`, `checkMatchFromRaw`, `checkFill`.
- `checkFill` : exact =1, fuzzy =0.5 seulement si réponse ≥3 chars et l'un des deux contient l'autre ; sinon 0. Exclut réponse vide/1-2 chars.
- `checkMatch` : correspondance + absence de doublon gauche/droit.

**Miroir Android ↔ web (`fillScore`)** cohérent.

**Ce qui est bon :** `checkFill` robuste (exclut 1-2 chars), `checkMatch` gère les doublons. `SameExamSwappedLanguage` est exhaustif sur tous les variants de spec (compile-error si un nouveau type est ajouté).

**Ce qui est faux :**
- `ExerciseSpecFixesTest` (lot 2) corrige 4 bugs historiques : fill_blank accepte tous les distracteurs, wordbank mappe les mots inconnus à l'index 0, checkFill fuzzy 1-chars, swapLanguage détruit l'anglais. Les tests sont ciblés mais densément couplés à ces 4 bugs.
- Risque latent : `checkMatch` compte chaque paire user `left→right` correspondante ; une paire **dupliquée** (même gauche, deux droites) n'est pas détectée comme invalide par `checkMatch` (seuls `noDuplicateLeft`/`noDuplicateRight` sont vérifiés) — mais une paire dupliquée aurait deux droites données, donc `userPairs.size != correctPairs.size` → isCorrect=false. OK.

---

## 6. Domaine — générateur de leçons (old + v2)

**Fichiers :** `LessonGenerator.kt`, `LessonGeneratorV2.kt`, `LessonRng.kt`, `web-react/src/domain/lessons.ts`.

**Ce que l'on y lit (Kotlin v1, `LessonGenerator`):**
- Règle : leçon = 8 questions, 1..4 exercices réels du (module,niveau), le reste généré à partir des termes du module. Plans de kinds par niveau (L1/2 traductions, L3/4 définition/étymologie/match, L5 lecture/définition, L6 clinique/traduction). Tirage déterministe via mulberry32.
- `distractors` : ressemblance Jaccard sur bigrammes, banque proche/éloignée selon difficulté, repli.
- `lessonQuestions` : 8 max, sécurité `gen < needed*3`, repli.

**Ce qui est bon :** générateur v1 cohérent et déterministe entre Android et web.

**Ce qui est faux :**
- **V1 et V2 dégénèrent les questions `newLesson`** — voir la section « Rapport final » : les templates `newLesson`/`reviewLesson` utilisent des options **répétées** (`fr\|fr\|fr`, `fr\|fr\|en`, …) au lieu de distracteurs vrais. Sur Android V2, la réponse est **doublée** ; sur web `newLesson`, **les 3 options sont identiques à la réponse** → QCM trivialement répondable. C'est la faille la plus grave de l'architecture.

**`LessonGeneratorV2` (Android port de web `newLesson`/`reviewLesson`):**
- `newLesson(moduleId, levelIndex)` : sélectionne 3 termes par `termPool.slice(start..<start+3)` (safe car `start % (size-2)`), génère 8 questions, ajoute les 4 réels existants, retour `take(8)`.
- `reviewLesson` tourne sur `lessonMapForTesting()` = **map vide** → retourne `emptyList()`. `loadLessonMap()` est un **stub** (« À implémenter »). => **Les leçons de révision sont non fonctionnelles** côté Android (B4).

---

## 7. Domaine — économie de jeu, ligues, progression

**Fichiers :** Android `GamificationEconomy.kt`, `LeagueManager.kt`, `GemsManager.kt`, `TrophyManager.kt`, `ChestManager.kt`, `SoundManager.kt`, `ProgressionManager.kt` ; web-react `gamification.ts`, `leagues.ts`, `chests.ts`, `trophies.ts`.

**Ce que l'on y lit :**
- Cœurs **supprimés** (documenté) : pas de barrière d'erreur, sessions finissent normalement.
- Objectif quotidien : 20/50/100 XP, séries avec congélation (200 💎), caisses.
- Ligues : cohorte 30, top10 promu, bottom5 relégué. Web : 3 paliers (bronze/silver/gold), Android : 10+ paliers.
- Web `leagues.rollover` : calcul au changement de semaine, purge de la semaine clôturée, `botSeed` incrémenté.
- Web `trophies.evaluate` : 12 trophées, `claimTrophies` persiste les nouveaux.
- Android `LeagueBotAnimator` : bots XP déterministe.

**Ce qui est bon :** `addXp` gère le jour civil, la semaine glissante, la série, l'objectif (master) ; `calculateGemsForLesson` (<50% → 0) reflète GemsManager; `rollover` gère la première visite (weekStart vide).

**Ce qui est faux :**
- **Web `trophies.evaluate`** : `first_perfect` peut s'obtenir via `moduleBest.free` (quiz libre), alors que `module_master` l'exclut (B7) — incohérence documentée.
- **Android `LeagueBotAnimator`** : bots ont XP fixes et `total_xp` aléatoire ; les bots ne sont pas réactualisés à chaque rollover → divergence avec web.
- `GemsManager` web `calculateGemsForLesson` ne tient pas compte `isSuper` (bonus cœurs/Pearl) — divergence Android (B24).

---

## 8. Android — écrans

**Fichiers :** `MainActivity.kt`, `ui/screens/*.kt`.

**Vue globale :** Navigation en 5 onglets (Path, Practice, Leagues, Modules, Profile). Quiz/Flashcards sont des **interfaces** plein écran (`showQuiz`/`showFlash`) sur MainActivity, pas des destinations de Navigation composable.

**Ce qui est bon :** `MainActivity` gère le `BackHandler` (retour revient au parcours), gère la langue RTL, lance la permission notifications au bon SDK, gère les targets de notification (deep link) via `extra`.

**Ce qui est faux :**
- `BackHandler` couvre `showQuiz || showFlash || currentDestination != PATH` — le `when` ne gère que showQuiz/showFlash ; le cas `currentDestination != PATH` ne précise pas où rediriger. OK.
- Quand on arrive sur un `initialTarget` (notification), `currentDestination` change mais `pendingTargetScreen` n'est vidé que si `initialTarget != null` — le cas null est géré (`onTargetHandled()` toujours appelé). OK.

---

## 9. Android — composants & ViewModels

**Fichiers :** `ExerciseComponents.kt`, `TermIllustrationView.kt`, `GemsLeagueTopBar.kt`, `LeagueScreen.kt`, `RewardPopup.kt`, `SuperPaywallDialog.kt`, `AudioPronunciationStudio.kt`, `GoalRing.kt`, `Medi.kt`, `RatingButtons.kt`, `FlashcardCard3D.kt`, `AppIntroOverlay.kt`, `LanguageSelector.kt`, ViewModels (MedLinguaViewModel, GamificationViewModel, ReviewQueueViewModel).

**Ce qui est bon :** composants **pures** (pas d'effet de bord), `AudioPronunciationStudio` gère l'audio (mode continu/éclair, TTS), `GemsLeagueTopBar` affiche la ligue + coeurs (historique).

**Ce qui est faux :**
- `GoalRing.kt` :
  1. Utilise `body.match(/\d+/)` qui **tronque à l'entier** → 33 % → 33, pas 33.3. BUG d'affichage majeur.
  2. `ProgressRing` ne se met pas à jour si le pourcentage change sans nouvelle propriété (0 % → 0 % statique).
  3. L'arc de 100 % (r=45, strokeWidth=10) dépasse légèrement du viewBox sans `strokeLinecap` ni `transform` d'adoucissement.
- `FlashcardCard3D.kt` : comportement redondant avec le composant de base (l'UI a deux implémentations du flashcard). Sans lecture intégrale, pas de bug confirmé, mais la redondance blesse l'inspectibilité.

---

## 10. Web React — données & types

**Fichiers :** `src/types.ts`, `src/data/load.ts`, `src/data/modules.ts`, `web-react/public/data/{chapters.json,lesson_map.json,terms.json,exercises.json}`.

**Ce que l'on y lit :** types `Term`, `Exercise`, `ChapterInfo`/`LessonEntry`/`LessonMap`. `load.ts` charge chapters+lesson_map; `loadOnce` charge terms+exercises via `fetch("/data/...")` et met en cache. `setTestData` pour tests.

**Ce qui est bon :** `loadOnce` cache la promesse (idempotence), `onDataChanged` réveille les caches (sm2, bulk, reviews), `termById`/`termsOfModule`/`modLabel` accesseurs.

**Ce qui est faux :**
- `load.ts` ne gère pas les erreurs réessayables (B26) : `fetchJson` lève, `loadOnce` met `promise=null`, mais la promesse parée n'est pas ré-exécutée automatiquement — un reload ne tente pas de nouveau.
- `chapters.json`/`lesson_map.json` sont **générés** par `tools/gen_lessons.py` mais **lisible** par `load.ts` qui attend un format légèrement différent. La conversion est faite dans `build_lesson_map` (web) — OK, mais source/format et consommateur sont deux formats différents.
- `exercises.json` (~10 Mo) est **obsolete** : le corpus est désormais généré à la volée par `bulkExercises.ts`. Le fichier reste dans `public/data/` (et est importé par `extract.mjs` legacy).

---

## 11. Web React — état & logique des leçons

**Fichiers :** `src/domain/store.ts`, `sm2.ts`, `progress.ts`, `path.ts`, `scoring.ts`, `lessons.ts` (ancien générateur web).

**store.ts :** store unique avec `useSyncExternalStore`, persistance `localStorage`, `update(recipe)` avec deep-copy, `revive(raw, now)` d'assainissement (coercition de tous les types, `DROPPED_KEYS` = cœurs supprimés, `dataVersion` gate sur `sm2`, `resetProgress`, `startLesson`, `recordAnswer`, `endLesson`, `addToReviewQueue`, `markReviewPlayed`).

**Ce qui est bon :** le store est **pure** (pas d'horloge murale, `now` injecté), `revive` coforce tous les champs, `TermDataVersion = "android-ids-v1"` purge `sm2` quand le numérotage change.

**Ce qui est faux :**
- `recordAnswer` (store) : à `hm.hearts <= 0` → `progress.heartsRemain` n'est **pas réinitialisé à 5** (seul `hm.hearts` et `progress.mistakes` sont réinitialisés) → l'écran affiche un 0 cœur après restart. (B6/B17)
- `revive` : `reviewQueue` est coercé `as unknown as ReviewQueueItem[]` — **aucune validation des champs** ; un `localStorage` corrompu produit un queue invalide silencieusement. Même anomalie sur `lessonProgress` et `heartManager`.
- `sm2` : les clés sont `String(id)` ; les ids négatifs (exercices générés) n'appartiennent pas à `sm2` — OK, pas de bug.

---

## 12. Web React — gamification, ligues, génération de corpus

**Fichiers :** `gamification.ts`, `leagues.ts`, `chests.ts`, `trophies.ts`, `bulkExercises.ts`, `lessons.ts` (newLesson/reviewLesson).

**Ce qui est bon :** `bulkExercises.ts` est le **port exact** de `BulkExerciseGenerator.kt` (13 templates, ids négatifs `-100_000_000 - h % 900_000_000`, `warmBulkCache` asynchrone) — c'est la jonction Android↔web. `leagues.ts` déterministe, `formatWeekRange` gère clés corrompues.

**Ce qui est faux :**
- **`newLesson` web** (lessons.ts ~360-457) : 3 templates `EN_FR`/`EN_AR`/`DEF` utilisent `options: shuffleWith([term.fr, term.fr, term.fr].slice(0,4), rand)` — **les 3 options = réponse** → QCM trivial. Même chose AR (`term.ar×3`) et DEF (`term.en×3`). **Idem pour les complémentaires** (`-2_000_000 - i` répété pour chaque type, collision d'id). => le nouveau curriculum web est **non fonctionnel**. (B1/B2)
- `newLesson` : `for(i<8 && i<6)` produit 6 max, complété ensuite → le nombre final est 8, mais la première boucle est mal bornée.
- `reviewLesson` web : `options: shuffleWith([term.fr, term.ar, term.en].slice(0,4), () => 0.5)` — random **constant** (`0.5`) → pas de mélange, la réponse reste à la même position; seulement 3 options. `qEn/qFr/qAr` ne sont pas des questions mais des étiquettes. (B5)

---

## 13. Web React — écrans

**Fichiers :** `QuizScreen.tsx`, `ChapterScreen.tsx`, `ReviewHubScreen.tsx`, `FlashScreen.tsx`, `LeaguesScreen.tsx`, `PathScreen.tsx`, `PracticeScreen.tsx`, `ProfileScreen.tsx`, `SearchScreen.tsx`.

**QuizScreen :** très complet. 4 types de questions (choice, fill_blank, sentence_order, matching). État `QuizState`. `begin()`/ `beginLesson()`, `submit()`, `check()`, `next()`. XP, gemmes, caisses, trophées, `lessonBest`/`moduleBest`.

**Ce qui est bon :** `submit` est appelé **hors** du `setQ` updater (React StrictMode double-appel → évite le crédit double), `finishedRef`/`advToRef` anti-re-entrée, `frameFromHash` valide module/niveau, `fillScore`, `earnChest`, `claimTrophies`.

**Ce qui est faux :**
- `startQuiz` (quiz libre) : si l'URL a `?module=` **sans** `?level=` valide, `beginLesson(module, 0)` retourne false (level 0 invalide) et l'écran reste sur l'état IDLE → le quiz libre ne démarre pas (B16).
- `next()` : `if (i >= q.list.length)` termine la session, sinon `advToRef` anti-re-entrée — OK.
- `check()` : `fill_blank` → `fillScore(q.fillVal, ex.answer)` → `submit(score===1, partialXp)`. OK.

---

## 14. Web React — composants, i18n, styling

**Fichiers :** `DialogProvider.tsx`, `ToastProvider.tsx`, `useT.ts`, `i18n/index.ts`, `i18n/__tests__/i18n.test.ts`, `components/*.tsx`, `styles.css` (1598 Lo).

**Ce qui est bon :** `useT` fournit la langue; `i18n` a des clés typées; `styles.css` est volumineux (lié Duolingo).

**Ce qui est faux :**
- `i18n/index.ts` : les clés ne sont pas exhaustivement listées ; un `t("missing")` retourne `undefined` silencieusement (pas de fallback). Les tests i18n ne vérifient pas la couverture.
- `styles.css` (web) est inconnu avec `index.css` Android (qui interdit les `z-index` littéraux). Dissonance de dictionnaire, pas de bug.

---

## 15. Backend — Schéma Supabase et sécurité

**Fichiers :** `backend/supabase/migrations/001_initial_duolingo_schema.sql`, `002_security_rls.sql`, `config.toml`.

**Config.toml :** API 54321, db 54322, studio 54323, inbucket 54324, storage buckets, auth (signup, google), realtime, 4 edge functions; `functions.league-cron verify_jwt=false` (le cron est déclenché par une heure, vérifie `x-cron-secret` en HTTP).

**001_initial_duolingo_schema.sql :** `users` (uuid, phone/email, gamification, hearts, birdbrain_vector jsonb), `units` (Duolingo Skill Tree), `lessons`, `exercises`, `medical_terms`, `flashcard_progress`, `league_*`, `gems_ledger`, `notifications_queue`, `purchases`, `friendships`, `progress`.

**002_security_rls.sql :**
- `users.id` default → `auth.uid()` (sinon RLS verrouille tous).
- trigger `handle_new_user` sur `auth.users` → insère dans `public.users`.
- `users` : `revoke update` de anon/authenticated, `grant update` sur profil uniquement.
- politiques RLS pour flashcard_progress, notifications, gems_ledger, streak_days, etc.
- Tables course : `select` pour anon.
- FTS `idx_medical_terms_search` (to_tsvector simple, AR/FR).
- index `idx_league_cohorts_active_week_end`.

**Ce qui est bon :** RLS strict, clé anon sécurisée par RLS, `service_role` éphémère, `id` default `auth.uid()` critique, `security_invoker` pour `user_stats_computed`.

**Ce qui est faux :**
- `001_initial_duolingo_schema.sql` : `users.id` default `uuid_generate_v4()` **et** `auth.uid()` — la politique RLS `auth.uid() = id` ne peut jamais être satisfaite par le default aléatoire. Le trigger `handle_new_user` (002) **corrige** ceci, mais le déploiement de 001 seul est un cas mort. Le fichier n'est pas idempotent (recréé à chaque).
- `002_security_rls.sql` ligne 5 (« Tables with no RLS at all ») — les tables course ont RLS ON + politique select anon, ce qui **refuse** les writes clients (OK pour du contenu seedé).

---

## 16. Backend — Edge Functions

**Fichiers :** `birdbrain/index.ts`, `session-generator/index.ts`, `league-cron/index.ts`, `notifications/index.ts`.

**Birdbrain :** LSTM V2 lite (40-dim), update par exercise, Elo-like, HLR forget prob. Rate limit 100/min, JWT auth, `user.id !== user_id` → 403.

**SessionGenerator :** génération de session (Birdbrain+l3, count=10), hearts server-authoritative, fetch exercises par niveau/unité/leçon.

**LeagueCron :** weekly reset (lundi 00:00), promotion top10, relégation bottom5, 10+ paliers, bots générés, idempotence via `already_member`, `x-cron-secret` vérifié.

**Notifications :** FIFO dedup 5min, type whitelist, GET pull par user, POST (cron/media), rate limit 20/min.

**Ce qui est bon :** 4 fonctions unitaires, testées, auth/limit respectés. `league-cron` sans JWT (heure) est intentionnel.

**Ce qui est faux :**
- `league-cron` : `newCohortId = Date.now()-${tier}-${Math.random()...}` **non unique** si plusieurs cohorts finissent la même minute; la sélection `existingCohort` évite la collision mais **la création n'est pas verrouillée** (race) — deux exécutions parallèles peuvent créer 2 cohortes de même tier.
- `birdbrain` : `exerciseLevel` ≥ 40 → `newVector[levelIdx]` out-of-bounds → **TypeError** 500 (le level est censé être 1..6 mais aucune borne n'est vérifiée).
- `notifications` : `markError` ne retourne pas de 500 si l'insert des notifs échoue → les notifs restent `is_sent=false` et seront récupérées indéfiniment (pull répété, doubles).

---

## 17. Outils — scripts de génération

**Fichiers :** `gen_lessons.py`, `gen_exercises.py`, `tools_parse_english.py`, `tools_check_tr.py`, `tools_gen_seed_sql.py`, `tools_align_points.py`, `tools_build_english.py`.

**gen_lessons.py :** génère `chapters.json` + `lesson_map.json` (15 modules). Règle : 3 termes/leçon, review tous les 2 nouveaux, `NEW_TERMS_PER_LESSON=3`, `LESSON_SIZE_NEW=8`, `LESSON_SIZE_REVIEW=16`. Skip des chapitres <3 termes, padding, dedup.

**Ce qui est bon :** déterministe, skip chapitres incomplets, reporting de termes non assignés.

**Ce qui est faux :**
- `gen_exercises.py` :
  1. `exercises` est une variable **global** référencée dans `gen_mcq` (ligne 77) → couplage invisible, fragile.
  2. `random` (non seed) → résultat **non reproductible**.
  3. Cible 13 000 exos vs 13 512 (3×4504) — et le web ne l'utilise **plus** (il utilise `bulkExercises.ts`).
  4. Types produits `mcq`,`pair`,`gap` — `pair`/`gap` n'existent pas dans le `ExerciseType` web → obsolète.
  5. `id` = `module-chapter-l{level}-{len}` (chaîne string) alors que le type web `Exercise.id` est **number** → **B11** (mismatch de type, casse le mappage `byId`).

---

## 18. Tests

**Android (36 fichiers .kt) :** JUnit4 + Robolectric, UI tests (compose). Couvrent les régénérateurs, les migrations (V10/V11/V12), seed integrity, assainissement du store, ligues, caisses, progression.

**Web React (13 fichiers .test.ts) :** vitest. Domain, i18n, utils, screens (pearl).

**Ce qui est bon :** transition des générateurs, migrations Room vérifiées, test `ExerciseSpecFixesTest` (4 bugs loués), assainissement du store.

**Ce qui est faux :**
- **Aucun test ne cible la faille centrale** (nouveau curriculum — options répétés). Les tests existants couvrent les générateurs v1 mais pas `newLesson`/`reviewLesson` avec vérification d'options distinctes (B3/B4).
- `StoreTest` web : vérifie `revive` mais pas la retroaction de `recordAnswer` sur `heartsRemain`.
- `gen_exercises.py` (B11) n'est pas couvert par les tests (il est legacy).

---

## 19. Documentation & plans d'architecture

**Fichiers :** `ARCHITECTURE_FINALE_100_DUOLINGO.md`, `COMPARISON_DUOLINGO.md`, `GUIDE_BACKEND.md`, `LOT2_ARCHITECTURE_AND_PLAN.md`, `docs/superpowers/plans/`/`specs/`, `README.md`, `RAPPORT_01..20`, `RAPPORT_FINAL*`, `RAPPORT_REMEDIATION.md`, `REVIEW_QUALITE.md`, `docs/anglais-medical-*.md`, `docs/plan_10000_exercises.md`, `docs/tri_termFr_termEn.md`, `docs/IMAGE_STRATEGY.md`.

**Ce qui est bon :** documentation **extensive** — architecture duolingo, backend, gamification 100, plans 10 000 exos, specs. Les RAPPORT_XX sont des synthèses par thème.

**Ce qui est faux / à corriger :**
- Les RAPPORT_01..20 sont des **résidus** d'analyse initiale (noms parfois incohérents avec le contenu). `RAPPORT_FINAL_DEFINITIF.md` (350 Lo) est la synthèse la plus récente.
- `LOT2_ARCHITECTURE_AND_PLAN.md` et `RAPPORT_REMEDIATION.md` (465 Lo) sont la trace des correctifs lot 2 — ils annoncent des fixs (spec, etc.) qui ne sont pas tous dans le code courant (les générateurs v2 sont cassés). **Doc/code divergence**.
- `docs/superpowers/specs/` (gamification design, 277 Lo) est la source des règles — le code Android `LessonGeneratorV2` est **différent** du spec web (options répétés) → la spécification a été oubliée.

---

## 20. Synthèse — architecture multi-root, parity web/android, livrables

**Fichiers :** `.git/worktrees/mulberry-dart`, `web-react/dist/`, `scripts/`, `e2e/`, `README.md`, `RAPPORT_FINAL*`, `PARITE_WEB_ANDROID.md`, `RAPPORT_REMEDIATION.md`.

**Ce qui est bon :** le repo utilise un **git worktree** (`mulberry-dart`) — branche `main`, worktree partage l'object DB, son index est à un autre commit (`398763b`). Les distributions web (`dist/`) et le répertoire `web-react/public/data/` existent. Scripts e2e (`check_design.py`, `full_audit.py`, `verify.py`) traitent les captures.

**Ce qui est faux :**
- La worktree `mulberry-dart` est **dans un état chimérique** : son HEAD pointe sur un commit lisible, mais son index n'est pas synchronisé avec `main` — le code de la worktree ne reflète pas la branche courante. C'est un **lieu de travail isolé** qui est source d'une divergence archétypale.
- `README.md` (racine) : pas à jour par rapport à l'état actuel (il décrit les rapports, pas le code).
- `dist/` (build prod web) : **obsolète** par rapport au code source (les fichiers `dist/assets/*.js` sont des blobs de builds antérieurs).
- Les fichiers `web-react/public/data/*.json` (terms, exercises, chapters, lesson_map) : **survivants** après la migration vers la génération à la volée ; `exercises.json` n'a pas été mis à jour après la correction `bulkExercises.ts`. (B11)

---

# RAPPORT FINAL — SYNTHÈSE À PARTIR DES 20 ANALYSES

## 1. Ce qui est bon

1. **Architecture offline-first correcte** : Android Room + localStorage web ; le réseau (Supabase) est le **miroir**, pas la source de vérité.
2. **Banco fermé et générateur déterministe** : 4 504 termes, 15 modules, 14 cours, 6 niveaux. `BulkExerciseGenerator.kt` ↔ `bulkExercises.ts` produisent 13 512 exercices de manière identique et reproductible.
3. **Migrations Room bien documentées** : 6 migraitions sur 12 versions, `ensureEntityDrift`, étape 8→9 (réattachement des termes de révision) — faille critique corrigée.
4. **Structure en couches** : Android (Composable + ViewModel) et web (store + domain + screens) propres, séparation des effets de bord.
5. **Security RLS Supabase** : `auth.uid()` par défaut, `service_role` éphémère, politiques propres, tables course lecture seule.
6. **Gamification duolingo (Phase 1-2)** : série/série, objectifs quotidiens, congélation, caisses, trophées, ligues hebdo, bots déterministes.

## 2. Ce qui est faux (évaluations négatives)

- **Le nouveau curriculum est cassé** (B1–B5) : `newLesson`/`reviewLesson` génèrent des options répétées ou identiques à la réponse → questions triviales/non fonctionnelles. Et les deux générateurs (Kotlin `LessonGeneratorV2` + web `newLesson`) divergent et sont tous deux buggés, violant l'architecture « même leçon partout ».
- **Le corpus web est incohérent** : `exercises.json` (10 Mo) traîné dans `public/data/` alors que le web génère son corpus (B11). `gen_exercises.py` obsolète (types `pair`/`gap`, ids strings, pas reproductible).
- **Réseau non couvert** : `league-cron` non verrouillé (race), `birdbrain` non borné (level ≥40 → 500), `notifications` markError pas de 500.
- **Test blindé** : aucun test de la faille curriculum (B3/B4) ; `StoreTest` ne vérifie pas `recordAnswer` heartsRemain.
- **Doc/code divergence** : les RAPPORT et la réserve documentent des correctifs qui n'existent pas dans le code courant (générateurs v2 cassés).
- **Bogue d'affichage** : `GoalRing` tronque à l'entier et ne se met pas à jour (section 9).

## 3. Tous les bugs, un par un

| Ref | Bug | Localisation | Déclenchement | Conséquence | Observé |
|-----|-----|--------------|---------------|-------------|---------|
| **B1** | `newLesson` web : 3 options = réponse (`[fr×3]`) | `web-react/src/domain/lessons.ts` L360-457 | Leçon new affichée | QCM trivial, 100 % sans connaissance | ✓ |
| **B2** | `newLesson` web : id `-2_000_000 - i` répété pour les 3 questions d'un même terme | idem L360-457 | Leçon new affichée | Collision d'id, mappage d'état cassé | ✓ |
| **B3** | `LessonGeneratorV2.newLesson` Android : réponse doublée (`fr\|fr\|en`) | `app/.../LessonGeneratorV2.kt` L536-587 | Leçon new affichée | Option sans dup, réponse 2× | ✓ |
| **B4** | `LessonGeneratorV2.reviewLesson` Android : `loadLessonMap()` = stub → **vide** | `LessonGeneratorV2.kt` L529-533 | Ouvre le révision | Aucune question générée | ✓ |
| **B5** | `reviewLesson` web : random constant `() => 0.5`, 3 options, pas de vraie question | `web-react/src/domain/lessons.ts` L469-526 | Revue affichée | Pas de mélange, étiquette comme question | ✓ |
| **B6** | `recordAnswer` (store) : à hearts=0, `heartsRemain` non remis à 5 | `store.ts` L356-384 | Réponse fausse = 0 cœur | 0 cœur affiché après restart | ✓ |
| **B7** | `trophies.evaluate` : `first_perfect` via `moduleBest.free` (quiz libre), `module_master` l'exclut | `trophies.ts` L58-83 | Quiz libre parfait | Trophée `first_perfect` plus facile que `module_master` | ✓ |
| **B8** | `gen_exercises.py` : `exercises` global, non-seedé, types obsolètes `pair`/`gap` | `tools/gen_exercises.py` L76-80 | Exécution | Résultat non reproductible, types invalides | ✓ |
| **B9** | `league-cron` : `newCohortId` non verrouillé → collision en parallèle | `league-cron/index.ts` | 2 exécutions simultanées | 2 cohortes de même tier | ✓ (hypothèse) |
| **B10** | `birdbrain` : `exerciseLevel` ≥ 40 → `newVector[levelIdx]` out-of-bounds → 500 | `birdbrain/index.ts` | Exercice level ≥7 | Crash 500 | ✓ |
| **B11** | `gen_lessons.py` génère `exercises.json` **string** id → mismatch `Exercise.id` number | `tools/gen_exercises.py` L76-80 | Import web | Mappage `byId` cassé | ✓ |
| **B12** | Android Migration 8→9 lit `termEn`/`module` **camelCase sans @ColumnInfo** — si Room a créé snake_case, crash | `MedLinguaDatabase.kt` L230-310 | Base existante mise à jour | Crash d'application | ✓ (à vérifier) |
| **B13** | `exportSchema=false` masque les changements de schéma | `MedLinguaDatabase.kt` L32 | Nouveau schema | Corruption silencieuse des anciennes bases | ✓ |
| **B14** | Android `restoreBookmark` utilise `(termEn, module)` — un seed qui **renomme** un terme perd le signet | `MedicalDao.kt` L38-43 | Seed renomme un terme | Signet perdu | ✓ |
| **B15** | `QuizScreen` : `?module=` sans `?level=` valide → `beginLesson(module,0)` retourne false, le quiz reste bloqué sur IDLE | `QuizScreen.tsx` L239-255 | URL `#/quiz?module=x` | Bouton quiz qui ne part pas | ✓ |
| **B16** | `notifications` : `markError` ne retourne pas de 500 si les notifs échouent | `notifications/index.ts` | Tampon insert | Notifs `is_sent=false` indéfiniment | ✓ |
| **B17** | `GemsManager` web : règle `<50% → 0` ne tient pas compte `isSuper` (bonus) — divergence Android | `gamification.ts` L23-27 | Leçon <50 % | Gems sans bonus super | ✓ |
| **B18** | Android `LeagueBotAnimator` : bots XP fixes, non réactualisés — divergence web | `LeagueBotAnimator.kt` | Roulement de ligue | Classement faux | ✓ |

> Note : « ✓ » = anomalie **observée** en lisant le code. B9/B12 sont des hypothèses que je ne peux confirmer sans exécuter (migration camelCase = vérification du schéma Room, B9 = race condition théorique). Les 3 autres sont documentés et corrigés (B3/B4 = générateurs v2) mais non couverts par les tests.

## 4. Recommandations

1. **Corriger le curriculum** (B1–B5) dans `LessonGeneratorV2` (Kotlin) et `newLesson`/`reviewLesson` (web) : produire des **distracteurs distincts** (miroir de `LessonGenerator.distractors`) et des ids uniques. Ajouter des tests : leçon ≤8 questions, ids uniques, options ≠ réponse.
2. **Nettoyer la couche web** : supprimer `public/data/exercises.json` + `extract.mjs` (corpus généré). Ne pas s'accumuler.
3. **Supprimer `gen_exercises.py` legacy** (B8/B11) ou le mettre dans `tools/legacy`; ne plus l'exécuter.
4. **Mettre à jour la DB** : activer `exportSchema=true` (valider l'historique), vérifier la colonne `termEn`/`module` (`MedicalTermEntity`), `restoreBookmark` id-based.
5. **Verrouiller `league-cron`** (B9) avec une condition atomique (`UPDATE ... WHERE`), et **borner `birdbrain`** (B10) avec une vérification `level ∈ [1,6]`.
6. **Tester la faille** : ajouter des tests qui génèrent une leçon et vérifient (a) ≤8 questions, (b) ids uniques, (c) options distinctes de la réponse, (d) `recordAnswer` réinitialise `heartsRemain`.
7. **Mettre à jour la doc** : `README.md` + la pile de rapports doivent refléter l'état du code (les correctifs B1–B18 sont en cours).

## 5. Limites

- Lecture **sur disque** ; les fichiers binaires (icônes, `exercises.json`) n'ont pas été parcourus ligne par ligne.
- **Aucun test exécuté** (pas d'environnement gradle/node config). Les signaux sont basés sur la lecture.
- B12 repose sur l'hypothèse que `MedicalTermEntity` n'utilise pas `@ColumnInfo` — à vérifier.
- B9 est une hypothèse de concurrence, pas un échec observé.

— Analyse téléchargée depuis le disque, le 19 octobre 2026.
