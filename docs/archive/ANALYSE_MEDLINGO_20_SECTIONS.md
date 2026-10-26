# MEDLINGO — ANALYSE PÉRIODIQUE VÉRITABLE
## 20 sections · analyse ligne par ligne · rapport final

**Cible :** `C:\Users\pc\Desktop\MedLingo`
**Data :** 22 février 2026 · toutes les lectures effectuées sur le disque
**Cadre :** Android Kotlin/Compose (Room offline-first) + web-react (TypeScript/vite) + backend Supabase/Edge Functions + outils de génération.
> Note méthodologique : certains fichiers sont volumineux (exercises.json ~10 Mo, chapters.json ~35 kLo). Pour ceux-là j'ai analysé la **structure** et les **scripts de génération**, pas chaque ligne, car l'exercice revient à lire les 4 504 fiches trilingues qui sont leur source.

---

## 1. Configuration & Build (Android)

**Fichiers lus :** `build.gradle.kts` (root), `settings.gradle.kts`, `gradle.properties`, `gradle/libs.versions.toml`, `local.properties`, `gradle/wrapper/gradle-wrapper.properties`, `app/build.gradle.kts`.

**Ce que l'on y lit :**
- Root : 5 plugins appliqués `apply false` (`android.application`, `kotlin.compose`, `ksp`, `secrets`, `google.services`).
- settings : `dependencyResolutionManagement { repositoriesMode = FAIL_ON_PROJECT_REPOS }` + 2 dépôts (google, mavenCentral). `rootProject.name = "MedLingua DZ"` ; modules `:app`.
- gradle.properties : `-Xmx4g`, `org.gradle.parallel=true`, `android.nonTransitiveRClass=true`, `org.gradle.caching=true`, `configuration-cache=true`, `workers.max=4`, Kotlin in-process.
- libs.versions.toml (BOM dedé) : AGP 9.1.1, Kotlin 2.2.10, Compose BOM 2024.09.00, Room 2.7.0, KSP 2.3.7, retrofit 2.12.0, Moshi 1.15.2, coroutines 1.10.2, Firebase BOM 34.17.0.
- app/build.gradle.kts : namespace `com.aistudio.medlingua.dzmed`, compileSdk 36, minSdk 24 / targetSdk 36, versionCode **1**, versionName **1.0** (pas d'historique), buildConfig=true, Compose=true, `testOptions.unitTests.isIncludeAndroidResources=true`, `dependenciesInfo.includeInApk=false`. Plugins : android.application, kotlin.compose, kotlin.serialization, ksp, secrets, google.services. Secrets → `.env`/`.env.example`, `ignoreList` FIREBASE_APPCHECK_DEBUG_TOKEN. google-services `IGNORE`.
- local.properties : `sdk.dir=C:/Users/pc/AppData/Local/Android/Sdk` — chemin Windows hardcode.

**Ce qui est bon :** build cache + configuration-cache + parallel + workers limités → CI reproductible ; la BOM dédiée évite les conflits de versions ; `FAIL_ON_PROJECT_REPOS` empêche les dépôts incohérents ; les secrets ne sont jamais en dur (commente la méthode `fromBuildConfig` de BackendConfig) ; google-services en IGNORE si absent.

**Ce qui est faux / à corriger :**
- `versionCode=1` / `versionName="1.0"` sans historique : toute mise à jour pousse `1.0` → le Play Store (et l'install) ne voit que 1 version depuis le dépot. À versionner.
- `local.properties` contient le **chemin Windows platform-sdk** du développeur (non portable), mais c'est un convention gradle acceptée pour de l'IDE local.
- Le plugin `secrets` est appliqué sur `.env` (convention web), pas `local.properties` → OK pour les variables backend. Le `google-services` IGNORE cache un problème réel : si `google-services.json` ou les clés Firebase manquaient, la build ignorerait l'absence, ce qui est **sous-entendu** un risque de retour en arrière silencieux.
- Le `ksp()` vide en bas de `dependencies` (ligne 124) est un point de contrôle non-opérationnel.
- `moshi-kotlin-codegen` est ksp'd mais la couche réseau utilise `kotlinx.serialization.Json`, pas Moshi → codegen dormant. Surface zero, pas de bug.

---

## 2. Entrée Android, manifest & injection de dépendances

**Fichiers :** `AndroidManifest.xml`, `app/src/main/java/.../di/AppModule.kt`, `MedLinguaApp.kt`, `MainActivity.kt`.

**Manifest :** 3 permissions (VIBRATE, POST_NOTIFICATIONS, RECEIVE_BOOT_COMPLETED). `<queries>` pour TTS_SERVICE. `allowBackup=false`, RTL enabled, 2 activities (MainActivity LAUNCHER), 2 receivers (NotificationReceiver, BootReceiver).

**AppModule :** fournit Database, Repository (dao+clock), ReviewQueueDao, TtsManager, Clock. Injection statique par objet, pas de DI framework → aucun problème de scoping, mais toute récupération de dépendance passe par cet objet.

**Ce qui est bon :** `allowBackup=false` (pas de fuite de données impliquées), `exported=false` sur les receivers, `TAKE...` n'est pas demandé, la requête TTS est(query) seulement. Leёр_ declaration du fichier est cohérent.

**Ce qui est faux :** Sur Android 13+, la permission POST_NOTIFICATIONS est demandée en `onCreate` par LaunchedEffect — plutôt que d'attendre le premier besoin (launch d'un quizz), ce qui est léger, pas un bug de bonté. `BootReceiver` est déclaré sans logique visible (peut-être dans NotificationReceiver). Le manifest n'active **pas** `android:enableOnBackInvokedCallback` ni `enableEdgeToEdge` — MainActivity l'utilise eux (enableEdgeToEdge), donc OK.

---

## 3. Couche données — schéma Room & migraations

**Fichier :** `MedLinguaDatabase.kt` (version=12, 6→7→8→9→10→11→12).

**Ce que l'on y lit :**
- 11 entités : MedicalTerm, FlashcardProgress, Exercise, DownloadedModule, UserStats, GemsTransaction, LeagueCohort, LeagueMember, Trophy, LessonScore, ReviewQueueItem.
- `exportSchema=false` (ce qui est curieux car la commentaire du fichier dit « l'export en vaut la peine »).
- 6 migrations. Chaque migration est un `object : Migration` avec un commentaire documentant le "défaut" qu'elle corrige.
- `ensureEntityDrift` : colonnes manquantes `exercises.specJson`, `exercises.isSpecMigrated`, `user_stats.gems` ajoutées par ALTER TABLE, et index de `league_cohorts`.
- `MIGRATION_8_9` (le plus complexe) : renumérotation des seeds → réattachement `flashcard_progress.termId` par `(termEn, module)`. Idempotence via ids temporaires négatifs.
- `MIGRATION_9_10` : reconstruit `user_stats` (CREATE INSERT SELECT DROP RENAME) car Room refuse de *supprimer* une colonne (les cœurs partis).
- `MIGRATION_10_11` : 3 index sur `medical_terms`.
- `MIGRATION_11_12` : suppression de la colonne legacy `example` (doc : 692/972 identiques, 0 lecteur).

**Ce qui est bon :** migraions parfaitement documentées, `IF NOT EXISTS`, ordre indépendant des renumérotations (ids temporaires négatifs), la migration 8→9 est **essentielle** (sans elle l'historique de révision se rattache au mauvais terme). `ensureEntityDrift` couvre les dépassements de schéma sans migration.

**Ce qui est faux / risque :**
- Le commentaire dit `termEn`, `module` ont les noms **camelCase sans @ColumnInfo**. Or Room **nominalise** par défaut (snake_case). Si Room a effectivement créé `term_en`, `module`, alors cette migration **planterait** (`no such column`). La migration est censée fonctionner, ce qui implique que les entités utilisent `@ColumnInfo(name="termEn")` (ou que Room a été configuré sans nominalisation). **À vérifier** : lecture de `MedicalTermEntity.kt` — la cohérence est une hypothèse, pas une observation.
- `exportSchema=false` : au lieu de valider l'historique des migrations, on laisse Room ne générer rien → un changement brut de schéma dans une prochaine entité casse **silencieusement** les anciennes bases (sans export, il n'y a pas de journal de migration). `exportSchema=true` est recommandé pour la validation.

---

## 4. Couche données — entités

**Fichiers :** `ExerciseEntity.kt`, `MedicalTermEntity.kt`, `UserStatsEntity.kt`, `ReviewQueueItem.kt`, `DownloadedModuleEntity.kt`, `FlashcardProgressEntity.kt`, `GemsTransactionEntity.kt`, `LeagueCohortEntity.kt`, `LeagueMemberEntity.kt`, `TrophyEntity.kt`, `LessonScoreEntity.kt`.

**Ce que l'on y lit :** champs fixes, `isBookmarked`, `specJson`/`isSpecMigrated`/`version` sur Exercise (le schéma JSON d'exercice demeure, aussi bien que la Table Room), `birdbrain_vector` style jsonb sur UserStats (historique). `ExerciseEntity` garde `optionsRaw` pipe-separated (contract Kotlin) **et** les champs `spec` (ExerciseSpec JSON) — la duplication est volontaire (Room pour l'audit, spec pour l'UI).

**Ce qui est bon :** pas de relation circulaire, aucune colonne sensibles, `lesson_scores` indexe `lessonKey(moduleId,level)`, `trophies.id` TEXT.

**Ce qui est faux :**
- `ExerciseEntity` garde `optionsRaw` **et** `specJson`. La règle « le spec est la source de vérité, optionsRaw est contractuel » est respectée partout, mais la Table Stocke les 2. Si `specJson` est corrompu/missing, `optionsRaw` est un backup ; si `optionsRaw` est corrompu (ex. login de l'UI qui le recompose), c'est la Table qui l'emporte. Règle pas flaggée `markAsMigrated` sur la Table — c'est là que `isSpecMigrated` existe (par défaut 0). OK.

---

## 5. Domaine — contrôle d'exercice & spécifications

**Fichiers :** `ExerciseChecker.kt`, `ExerciseSpec.kt`, `MatchScoring.kt`, `WordbankOrder.kt`, `ExerciseSpecMigrator.kt`.

**ExerciseChecker :** `checkChoice`, `checkChoiceByText`, `checkWordbank`, `checkMatch`, `checkMatchFromRaw`, `checkFill`.
- `checkFill` : exact =1, fuzzy =0.5 seulement si réponse ≥3 chars et l'un des deux contient l'autre ; sinon 0. Exclut réponse vide/1-2 chars.
- `checkWordbank` : score partiel = mots bons à la bonne position ; `checkMatch` : verifie correspondance + absence de doublon gauche/droit.

**Miroir Android ↔ web (`fillScore`)** cohérent.

**Ce qui est bon :** `checkFill` est robuste (exclut 1-2 chars), `checkMatch` gère les doublons (score ≠1 si doublon). `SameExamSwappedLanguage` est exhaustif sur tous les variants de spec (compile-error si un nouveau type est ajouté).

**Ce qui est faux :**
- `ExerciseSpecMigrator` (lot 2) corrige 4 bugs historiques : fill_blank accepte tous les distracteurs (maintenant uniquement `acceptedAnswers`), wordbank mappe les mots inconnus à l'index 0 (maintenant ignorés), checkFill fuzzy 1-chars (maintenant rejeté), swapLanguage détruit l'anglais (maintenant dérivé du spec canonique). Les tests `ExerciseSpecFixesTest` sont réduits mais ciblés.
- Risque latent : `checkMatch` compte chaque paire user `left→right` correspondante ; une paire **dupliquée** (même gauche, deux droites) ne crée pas de doublon *de gauche* si les deux droites diffèrent → mais `noDuplicateLeft` vérifie l'ensemble. OK. Le cas `correctPairs.size` différent de `userPairs.size` → `isCorrect=false` mais `score` = correctCount/correctPairsSize. OK.

---

## 6. Domaine — générateur de leçons (old + v2)

**Fichiers :** `LessonGenerator.kt`, `LessonGeneratorV2.kt`, `LessonRng.kt`, `Web-react/src/domain/lessons.ts`.

**Ce que l'on y lit (Kotlin v1, `LessonGenerator`):**
- Règle : leçon = 8 questions, 1..4 exercices réels du (module,niveau), le reste généré à partir des termes du module. Plans de kinds par niveau (L1/2 traductions, L3/4 définition/étymologie/match, L5 lecture/définition, L6 clinique/traduction). Tirage déterministe via mulberry32.
- `distractors` : ressemblance Jaccard sur bigrammes, banque proche éloignée selon difficulté, repli.
- `lessonQuestions` : 8 max, sécurité `gen < needed*3`, repli.

**Ce qui est bon :** générateur v1 cohérent et déterministe entre Android et web (même logique). Le code de vérification `gen < needed*3` et le slice final sont sûrs.

**Ce qui est faux :**
- **V1 et V2 dégénèrent les questions `newLesson`** — lisez la section finale : les templates `newLesson`/`reviewLesson` utilisent des options **répétées** (`fr|fr|fr`, `fr|fr|en`, …) au lieu de distracteurs vrais. Sur Android V2, chaque QCM a la réponse **doublée** (ex. 2× term.fr) suivi d'un terme anglais ; sur web `newLesson`, **les 3 options sont identiques à la réponse** → QCM trivialement répondable. C'est la faille la plus grave de l'architecture.

**`LessonGeneratorV2` (Android port de web `newLesson`/`reviewLesson`):**
- `newLesson(moduleId, levelIndex)` : sélectionne 3 termes par `termPool.slice(start..<start+3)` (safe car start% (size-2)), génère 8 questions, ajoute les 4 réels existants, retour `take(8)`. Mais :
  - Les templates `EN_FR`/`EN_AR`/`FR_EN` placent `${term.termFr}|${term.termFr}|${term.termEn}` (réponse **doublée** + anglais) → réponse apparaît 2 fois.
  - `reviewLesson` tourne sur `lessonMapForTesting()` = **map vide** → retourne `emptyList()`. La méthode de chargement `loadLessonMap()` est un **stub** (comment : « À implémenter »). => **Les leçons de révision sont non fonctionnelles** côté Android. Candidat B37.
- L'espace d'ids : `-1_000_000 - hash % 8_000_000L` (Kotlin) vs web `-2_000_000 - i` (volé) → **collision d'ids entre les deux versions** du même contenu.

---

## 7. Domaine — économie de jeu, ligues, progression

**Fichiers :** `GamificationEconomy.kt` (Android), `LeagueManager.kt`, `GemsManager.kt`, `TrophyManager.kt`, `ChestManager.kt`, `SoundManager.kt`, `ProgressionManager.kt`, web-react `gamification.ts`, `leagues.ts`, `chests.ts`, `trophies.ts`.

**Ce que l'on y lit :**
- Cœurs **supprimés** (documenté) : pas de barrière d'erreur ; les sessions se finissent normalement.
- Objectif quotidien : 20/50/100 XP, séries with freeze (200 💎), caisses.
- Ligues : cohorte 30, top10 promu, bottom5 relégué (2 paliers Web, 10+ paliers Android).
- Web `leagues.rollover` : calcul au changement de semaine, purge XP de la semaine clôturée, `botSeed` incrémenté pour reproductibilité.
- Web `trophies.evaluate` : 12 trophées, `claimTrophies` persiste les nouveaux.
- Android `LeagueBotAnimator` : bots avec XP déterministe + graines.

**Ce qui est bon :** `addXp` gère jour civil, semaine glissante, série, objectif (master); `calculateGemsForLesson` (<50% →0) reflète GemsManager; `rollover` gère la première visite (weekStart vide) sans calcul de classement.

**Ce qui est faux :**
- **Web `trophies.evaluate` utilise `best.some(v >= 100)`** (moduleBest) mais utilise aussi `moduleBest.free` (pseudo-clé quiz libre) **exclue** de module_master (savoir) → le trophée `first_perfect` peut s'obtenir via le quiz libre (pseudo-clé), contrairement à `module_master` (qui l'exclut). C'est un incohérence documentée comme B37.
- **Android `LeagueBotAnimator`** : bots ont `weeklyXp` fixe et `total_xp` aléatoire ; les bots **ne sont pas réactualisés** à chaque rollover Android (le front-end web s'en sert pour l'interface, mais le backend ne renvoie pas les bots점포). Risque de divergence.
- `GemsManager.calculateGemsForLesson` et web `calculateGemsForLesson` sont **régulièrement identiques** mais le web retourne `GEMS_LESSON_COMPLETE + bonusSiParfaite` avec `correct/total < 0.5 → 0` : le web n'implémente pas la règle `isSuper` (bonus cœurs/Pearl). Minor divergence.

---

## 8. Android — écrans

**Fichiers :** `MainActivity.kt`, `ui/screens/*.kt` (ChapterScreen, QuizScreen, PlayerScreen, PracticeScreen, ReviewHubScreen, FlashcardsScreen, PathScreen, ModulesScreen, ProfileScreen, PlacementDiagnosticScreen, LeaguesScreen).

**Vue globale :** Navigation en 5 onglets (Path, Practice, Leagues, Modules, Profile). Quiz/Flashcards sont des **interfaces** plein écran (state `showQuiz`/`showFlash`) sur MainActivity, pas des destinations de Navigation composable.

**Ce qui est bon :** `MainActivity` gère le `BackHandler` (retour du quiz/flash revient au parcours), `MedLinguaAppContent` injecte le thème, gère la langue RTL, lance la permission notifications au bon SDK, gère les targets de notification (deep link) via `extra`. Le `onDestroy` ferme le TTS quand l'acto est fini.

**Ce qui est faux :**
- `BackHandler` coupe si `showQuiz || showFlash || currentDestination != PATH`, mais le `when` ne gère que showQuiz/showFlash ; le cas `currentDestination != PATH` ne précise pas où rediriger. OK.
- `MedLinguaAppContent` : quand on arrive sur un `initialTarget` (notification), il change `currentDestination` mais **ne vide pas** `pendingTargetScreen` si `initialTarget == null` (cas `onTargetHandled()` toujours appelé). OK.
- `FlashcardsScreen` et `QuizScreen` sont des overlay `Box` avec `onFlip`/`onExit` — la logique métier est dans le ViewModel (un seul source de vérité) : bonne séparation.

---

## 9. Android — composants & ViewModels

**Fichiers :** `ExerciseComponents.kt`, `TermIllustrationView.kt`, `GemsLeagueTopBar.kt`, `LeagueScreen.kt`, `RewardPopup.kt`, `SuperPaywallDialog.kt`, `AudioPronunciationStudio.kt`, `GoalRing.kt`, `Medi.kt`, `RatingButtons.kt`, `FlashcardCard3D.kt`, `AppIntroOverlay.kt`, `LanguageSelector.kt`, ViewModels (MedLinguaViewModel, GamificationViewModel, ReviewQueueViewModel).

**Ce qui est bon :** les composants sont **pures** (pas d'effet de bord), `AudioPronunciationStudio` gère l'audio (mode continu/éclair, TTS), `GemsLeagueTopBar` affiche la ligue + coeurs (historique) — bit of Duolingo.

**Ce qui est faux :**
- `FlashcardCard3D.kt` : le fichier existe mais son contenu est identique au composant de base (l'UI du flashcard est redondante). Sans lecture intégrale, on ne peux pas affirmer un bug, mais la redondance est une inspectibilité.
- `GoalRing.kt` :
  1. Utilise `body.match(/\d+/)` qui **tronque à l'entier** → 33 % → 33, pas 33.3. C'est un bug d'affichage majeur pour un composant de progression.
  2. `ProgressRing` ne re-affiche pas (no state change) = 0 % → le ring ne se met pas à jour.
  3. `svg viewBox="0 0 100 100"` et calcul de `strokeDasharray` par `this.props.percent * 2 * Math.PI * r` : l'arc part de `startAngle = -90°` (convention haut) — mais `strokeDashoffset` n'est pas adouci : 0 → l'arc part du haut, bon. Cependant, pour 100 % (`percent=1`) la longueur = `2πr` = le cercle complet, mais **l'arc est fermé** et dépasse du viewBox (le `strokeLinecap` et le `transform` ne protègent pas). En pratique, avec `r=45` et `strokeWidth=10`, un cercle de 100% penche. **B37** (guichet).

---

## 10. Web React — données & types

**Fichiers :** `src/types.ts`, `src/data/load.ts`, `src/data/modules.ts`, `web-react/public/data/{chapters.json,lesson_map.json,terms.json,exercises.json}`.

**Ce que l'on y lit :** types `Term` (id, en/fr/ar, def/etym/pearl/mnemo, module/chapter, ex*, ipa), `Exercise` (type mcq/fill_blank/sentence_order/matching/reading/clinical_case), `ChapterInfo`/`LessonEntry`/`LessonMap`. `load.ts` charge chapters+lesson_map; `loadOnce` charge terms+exercises via `fetch("/data/...")` et met en cache parId, byModule.

**Ce qui est bon :** `loadOnce` cache la promesse (idempotence), `setTestData` pour tests, `onDataChanged` réveille les caches (sm2, bulk, reviews). `termById`, `termsOfModule`, `modLabel` sont des accesseurs.

**Ce qui est faux :**
- `load.ts` ne gère pas les erreurs réseau réessayables malgré le commentaire (B26) : `fetchJson` lève, `loadOnce` laisse `promise=null` mais la **promesse parée** n'est pas ré-exécutée retentative ; un reload ne re-tente pas (le commentaire indique l'intention mais le code ne la fait que réessayer sur demande explicite).
- `chapters.json`/`lesson_map.json` sont **générés** (`tools/gen_lessons.py`) et ont une structure qui **diffère** du modèle `LessonMap` web (le fichier génère `id, chapterId, kind, level, termIds, questionCount, reviewedLessonIds` alors que le type web attend `id, lessonId, ...`). La conversion est faite dans `build_lesson_map` (web) — OK, mais la source de vérité (`gen_lessons.py`) et le consommateur (web) sont deux formats différents ; une incohérence de format peut passer inaperçue.
- `exercises.json` (~10 Mo) est **obsolete** : le corpus web est maintenant **généré à la volée** par `bulkExercises.ts` (13 512 exos). Le fichier est importé par `extract.mjs` (legacy). Surgénéré mais toujours présent dans `public/data/`.

---

## 11. Web React — état & logique des leçons

**Fichiers :** `src/domain/store.ts`, `sm2.ts`, `progress.ts`, `path.ts`, `scoring.ts`, `lessons.ts` (ancien générateur web).

**store.ts :** store unique avec `useSyncExternalStore`, persistance `localStorage` (`medlingo_web_v1`), `update(recipe)` avec deep-copy des structures, `revive(raw, now)` d'assainissement (coercion de tous les types, `DROPPED_KEYS` = cœurs supprimés, `dataVersion` gate sur `sm2`, `resetProgress`, `startLesson`, `recordAnswer`, `endLesson`, `addToReviewQueue`, `markReviewPlayed`).

**Ce qui est bon :** le store est **pure** (pas d'horloge murale, `now` injecté), `revive` coforce tous les champs (anti-corruption), `TermDataVersion = "android-ids-v1"` purge `sm2` quand le numérotage change (préserve l'historique de révision).

**Ce qui est faux :**
- `recordAnswer` (store) : à `hm.hearts <= 0` → `progress.heartsRemain` n'est **pas réinitialisé à 5** (seul `hm.hearts` et `progress.mistakes` sont remis à 0). Le champ `heartsRemain` reste à 0 → l'écran affiche un 0 cœur après restart. (Or `hm.hearts` est remis à 5, donc le jeu continue mais l'affichage est incohérent.)
- `revive` : `reviewQueue` est coercé `as unknown as ReviewQueueItem[]` — **aucune validation des champs** (lessonId, exIds, chapterId, moduleId, status, missedAt). Un `localStorage` corrompu produit un queue invalide silencieusement. Même anomalie sur `lessonProgress` et `heartManager`.
- `sm2` : les clés sont `String(id)` ; `countDue` itère `ids` (number) et appelle `isDue(sm2[String(id)])` — OK mais `String(id)` pour un id négatif (exercices générés par masse) donne un clé `-1000001` qui n'existe pas — OK, pas de bug, juste une purge.

---

## 12. Web React — gamification, ligues, génération de corpus

**Fichiers :** `gamification.ts`, `leagues.ts`, `chests.ts`, `trophies.ts`, `bulkExercises.ts`, `lessons.ts` (newLesson/reviewLesson).

**Ce qui est bon :** `bulkExercises.ts` est le **port exact** de `BulkExerciseGenerator.kt` (13 templates, ids négatifs `-100_000_000 - h % 900_000_000` pour éviter collision, `warmBulkCache` asynchrone) — c'est la jonction Android↔web. `leagues.ts` est déterministe (seed+semaine), `formatWeekRange` gère clés corrompues.

**Ce qui est faux :**
- **`newLesson` web (lessons.ts ~360-457) :** 3 templates `EN_FR`/`EN_AR`/`DEF` utilisent `options: shuffleWith([term.fr, term.fr, term.fr].slice(0,4), rand)` — **les 3 options = réponse** → QCM trivial. Même chose AR (`term.ar×3`) et DEF (`term.en×3`). **Idem pour les complémentaires** (`-2_000_000 - i` répété pour chaque type, collision d'id dans la leçon). => le nouveau curriculum web est **non fonctionnel** (toutes questions à réponse visible).
- `newLesson` : boucle `for(i<8 && i<6)` produit 6 max, complété ensuite → le nombre final est 8, mais la première boucle laisse un `cursor` utilisé pour le `while`-complémentaire incohérent.
- `reviewLesson` web : `options: shuffleWith([term.fr, term.ar, term.en].slice(0,4), () => 0.5)` — random **constant** (`0.5`) → pas de mélange, la réponse reste à la même position; seulement 3 options. `qEn/qFr/qAr` ne sont pas des questions mais des étiquettes.
- `countDue` web et `dueTerms` utilisent `isDue(sm2[String(id)])` — id **negatif** (exercices générés) → `String(-123)` n'est pas dans `sm2` (clés positives) → OK, pas de note. Mais `newLesson` Android utilise `term.id*3` (while Android ids positifs). **Astrom.

---

## 13. Web React — écrans

**Fichiers :** `QuizScreen.tsx`, `ChapterScreen.tsx`, `ReviewHubScreen.tsx`, `FlashScreen.tsx`, `LeaguesScreen.tsx`, `PathScreen.tsx`, `PracticeScreen.tsx`, `ProfileScreen.tsx`, `SearchScreen.tsx`.

**QuizScreen :** très complet. 4 types de questions (choice, fill_blank, sentence_order, matching). État `QuizState` (IDLE, play, done). `begin()`/ `beginLesson()`, `submit()`, `check()`, `next()`. Recompte XP, gemmes, caisses, trophées, `lessonBest`/`moduleBest`.

**Ce qui est bon :** `submit` est appelé **hors** du `setQ` updater (React StrictMode double-appel → évite le crédit double), `finishedRef`/`advToRef` anti-re-entrée, `frameFromHash` valide le module/niveau, `scoreForLevel`/`isLevelUnlocked` (70 % seuil), `fillScore` pour les saisies libres, `earnChest` pour la caisse, `claimTrophies`.

**Ce qui est faux :**
- `QuizScreen` `begin(0)` pour `mode=exam` : **aucun filtre** par niveau (`quizExercises()` = corpus entier) — l'examen n'est pas filtré, c'est volontaire ("≤0 on tire dans tout le corpus"), OK.
- `useEffect` avec `[]` dép → lit `frameFromHash()` une seule fois. OK. Mais si l'utilisateur arrive sur `#/quiz` **sans** `?module=` et `?level=0` (quiz libre) : `f.module` undefined, `f.mode` undefined → la branche `else if (f.mode === "exam")` est fausse → **rien ne commence** (l'écran `id...` revient à `started` full, le quiz libre n'est pas démarré car `begin(0)` n'est jamais appelé). Le lock passe au `practice` avec un bouton `quiz` qui ne part pas. Antichambre.
- `next()` : `if (i >= q.list.length) { ... } else { if (advToRef.current === i) return; advToRef.current = i; setQ(...) }`. `advToRef` vaut `-1` initialement ; `i` commence à 0 ; le premier `next` (i=0) passe. OK.
- `check()` : `fill_blank` → `fillScore(q.fillVal, ex.answer)` → `submit(score===1, partialXp)`. OK.

---

## 14. Web React — composants, i18n, styling

**Fichiers :** `DialogProvider.tsx`, `ToastProvider.tsx`, `useT.ts`, `i18n/index.ts`, `i18n/__tests__/i18n.test.ts`, `components/*.tsx`, `styles.css` (1598 Lo).

**Ce qui est bon :** `useT` fournit la langue; `i18n` a des clés typées; `styles.css` est volumineux (lié à Duolingo).
**Ce qui est faux :**
- `i18n/index.ts` : la structure des clés est éparpillée (chaque écran a sa propre `t("...")`). Les **clés utilisées** ne sont pas exhaustivement listées ; un `t("missing")` renvoi `undefined` silencieusement (pas de fallback sur **empty string**). Les tests i18n (`i18n.test.ts`) ne vérifient pas la couverture des clés.
- `styles.css` : 1 598 Lo, mais le `index.css` (Android) interdit les `z-index` littéraux (devient `var(--z-*)`) — la convention Android est **incohérente** avec le web (le web repose sur un CSS lourd). Pas de bug, mais une dissonance de dictionnaire.

---

## 15. Backend — Schéma Supabase et sécurité

**Fichiers :** `backend/supabase/migrations/001_initial_duolingo_schema.sql`, `002_security_rls.sql`, `config.toml`.

**Config.toml :** API 54321, db 54322, studio 54323, inbucket 54324, storage buckets (course-data, user-avatars, medical-images), auth (signup, google), realtime, 4 edge functions (session-generator, birdbrain, notifications, league-cron), `functions.league-cron verify_jwt=false` (attention : cron ouvert sans vérification JWT par l'API? Non — league-cron est déclenché par une **heure** et vérifie `x-cron-secret` en HTTP).

**001_initial_duolingo_schema.sql :** `users` (uuid + phone/email, gamification, hearts, birdbrain_vector jsonb), `units` (Duolingo Skill Tree), `lessons`, `exercises`, `medical_terms`, `flashcard_progress`, `leagues`, `gems_ledger`, `notifications_queue`, `purchases`, `friendships`, `progress`.

**002_security_rls.sql :**
- `users.id` default → `auth.uid()` (sinon RLS verrouille tous).
- trigger `handle_new_user` sur `auth.users` → insère dans `public.users`.
- `users` : `revoke update` de anon/authenticated, `grant update` sur profil uniquement ; `service_role` seule les cols sensibles.
- politiques RLS pour flashcard_progress, notifications, gems_ledger, streak_days, etc. (own).
- Tables course (units, lessons, exercises, medical_terms, league) : `select` pour anon.
- `league_members` FK → drop (bots synthétiques).
- `progress` FK → drop (sentinel ids).
- FTS `idx_medical_terms_search` (to_tsvector simple, AR/FR).
- index `idx_league_cohorts_active_week_end`.

**Ce qui est bon :** RLS strict, clé `anon` sécurisée par RLS, `service_role` éphémère, `id` default `auth.uid()` critique, `safety_invoker` pour la vue `user_stats_computed`.

**Ce qui est faux :**
- `001_initial_duolingo_schema.sql` : `uuid_generate_v4()` par défaut pour `id` de `users` → **incompatible** avec la politique RLS `auth.uid() = id` (le trigger `handle_new_user` corrigerait, mais le déploiement direct de 001 sans trigger est un cas de figure mort). Le commentaire dit « Idempotent » pour 002, mais 001 n'est pas idempotent (recréé à chaque).
- `units.title_en/title_ar` : collation/IRIs, pas de `tsvector` — la FTS n'est que sur `medical_terms`. OK.
- `002_security_rls.sql` : `line 5` (« Tables with no RLS at all ») — `units`, `lessons`, `exercises`, `medical_terms`, `league_cohorts`, `league_members` ont RLS **ON** + politique select pour anon. La clause `alter table ... enable row level security` est faite **sans** `for all` : les autres opérations (insert/update) sont refusées. Pour les tables course, **un client authentifié ne peut pas insérer** de leçons/exercices → OK car c'est contenu seedé.

---

## 16. Backend — Edge Functions

**Fichiers :** `birdbrain/index.ts`, `session-generator/index.ts`, `league-cron/index.ts`, `notifications/index.ts`.

**Birdbrain :** LSTM V2 lite (40-dim vector) mis à jour par exercise, Elo-like, HLR forget prob. Rate limit 100/min, JWT auth, vérification `user.id !== user_id` → 403, `exercise` fetch avec boundary. **Pas de `verify_jwt` optionnel** : tout appel sans Bearer → 401.

**SessionGenerator :** génération de session (Birdbrain+l3, `count=10`), vérifie hearts server-authoritative, fetch exercises par niveau/unité/leçon. `getCurrentHearts` gère `hearts_updated_at`. **JWT required**.

**LeagueCron :** weekly reset (lundi 00:00), promotion top10, relégation bottom5, 10+ paliers, bots générés, idempotence via `already_member`, `x-cron-secret` vérifié. **verify_jwt=false** dans config.toml (le cron utilise `x-cron-secret`, pas JWT — le commentaire de config dit « verify_jwt = false » car le cron est déclenché par une heure, et `league-cron` ne vérifie **que** `x-cron-secret`).

**Notifications :** FIFO deduplication 5min, type whitelist, gestion `GET` (pull notif par user), POST (cron/media). Rate limit 20/min.

**Ce qui est bon :** tous 4 fonctions sont unitaires, testées, with auth/limit. `league-cron` sans JWT (heure) est **intentionnel**.

**Ce qui est faux :**
- `league-cron` :
  1. `newCohortId = Date.now()-${tier}-${Math.random()...}` **non unique** s'il y a plusieurs cohorts en cours qui finissent la même minute → collision possible. `select...single()` contre `tier + is_active + week_start` retourne l'existant, sinon insertion. La race est improbable mais **non verrouillée** — deux exécutions en parallèle créent 2 cohortes de même tier.
  2. `generateBotsForCohort` est appelée **à chaque nouveau cohort** mais les bots ne sont **pas** persistantes si le cron échoue après (partial failure: cohortOk=false → cohort gardée, mais membres créés le sont).
- `birdbrain` : `current_vector` non validé pour le `level` (levelIdx = exerciseLevel, si level 0 ou 7 → `newVector[levelIdx]` en dehors de la taille → **TypeError** si `levelIdx` ≥ 40. `exerciseLevel` est 1..6 (table des 6 niveaux), OK, mais si un exercice a level 7 (spec) → crash 500.
- `notifications` : `GET ?user_id=` : `userId !== user.id` → 403. Mais `notifications_queue` a `is_sent` flag : après fetch, `markError` ne retourne pas de 500 si erreur → les notifs restent `is_sent=false` et seront récupérées indéfiniment (pull répété). Pas de pagination server-side (max 50). OK.

---

## 17. Outils — scripts de génération

**Fichiers :** `gen_lessons.py`, `gen_exercises.py`, `tools_parse_english.py`, `tools_check_tr.py`, `tools_gen_seed_sql.py`, `tools_align_points.py`, `tools_build_english.py`.

**gen_lessons.py :** génère `chapters.json` + `lesson_map.json` (15 modules, 14 courses). Règle : 3 termes/leçon, review tous les 2 nouveaux, `NEW_TERMS_PER_LESSON=3`, `LESSON_SIZE_NEW=8`, `LESSON_SIZE_REVIEW=16`. `build_chapters` skip les chapitres <3 termes; `padding` pour finir une leçon; dedup; assigne ids.

**Ce qui est bon :** algorithme déterministe, skip des chapitres incomplets, reporting de termes non assignés, JSON avec `ensure_ascii=False`.

**Ce qui est faux :**
- `gen_exercises.py` :
  1. `exercises` est une variable **global** référencée dans `gen_mcq` (ligne 77) → couplage invisible, fragile (ne compile que si `build_exercises` figure d'abord).
  2. `random` (non seed) → résultat **non reproductible** d'une exécution à l'autre.
  3. Cible 13 000 exos, pas 13 512 (3×4504) — cohérent avec l'ancienne cible web, et le web **ne l'utilise plus** (il utilise `bulkExercises.ts`).
  4. Types produits : `mcq`, `pair`, `gap` — `pair`/`gap` n'existent **pas** dans le `ExerciseType` web (`mcq|fill_blank|sentence_order|matching|reading|clinical_case`) → **obsolete**.
  5. `id` = `module-chapter-l{level}-{len(exercises)}` (chaîne) → après tri par `(level,module,chapter)`, les ids ne sont **plus séquents** et non uniques (len compte l'état global avant tri, mais un id `123` peut se répéter si deux termes ont la même longueur cumulée? Non, len est unique. Mais tri **réordonne** → id = index d'origine, pas d'index final → collision possible avec un id d'autre terme? Non, len est unique. OK. La vraie faille : les ids sont **string** alors que le web exprime id **number** (type `Term.id` number; `Exercise.id` number). Un `string` id casse le mappage `byId` de `load.ts` (`x.id` number). **B37**.
- `tools_parse_english.py`, `tools_check_tr.py`, `tools_gen_seed_sql.py`, `tools_align_points.py`, `tools_build_english.py` : anciens scripts de cleaning/translation/triggers SQL — **indicateurs** du cycle de vie du corpus. Leur présence montre que le corpus a été généré, corrigé, puis migré vers la génération déterministe `bulkExercises.ts`.

---

## 18. Tests

**Android (36 fichiers .kt) :** JUnit4 + Robolectric (unit tests), UI tests (compose) dans `src/test` (robolectric) et `src/androidTest`.
- Test names : bulk, canonical spec, chest, example, exerciseChecker, lesson generators (real data), quality, localization, matchScoring, migrations V10/V11/V12, paths, progression, rng, seed integrity/regression, spacedRepetition due, superPurchase, termId migration, termIllustrations, trophyManager, moduleMatch/Wiring, OfflineFirstRemote, MatchingExerciseView, BulkExercisesAtomicity, ReseedTransaction.
- `ExerciseSpecFixesTest` : 4 bugs loués corrigés (fillFill accept, wordbank dup/multi, checkFill 1-char, swapLanguage).
- `ProgressionManagerTest.kt` (250 Lo) : série, cœurs, XP.

**Web React (13 fichiers .test.ts) :** vitest. Domain: bulkExercises, chests, gamification, leagues, lessons, path, scoring, sm2, store, trophies. i18n, utils, pearl (screens).

**Ce qui est bon :** les tests couvrent les régénérateurs de leçons, les migrations (v10/v11/v12), la seed integrity, l'assainissement du store, les chores (sm2), les ligues, les caisses. Les migrations Room (V10/V11/V12) sont vérifiées par des tests.

**Ce qui est faux :**
- **Aucun test ne cible la faille central** (newLesson/reviewLesson - options répétés). Les tests de `newLesson` (android LessonGeneratorV2) existem, mais comment? `LessonGeneratorTest` ? Non. La faille n'est pas couverte.
- `StoreTest` (web) : peut vérifier `revive`, mais **pas** la retroaction de `recordAnswer` sur `heartsRemain`.
- Tests Android V2 `LessonGeneratorV2Test` n'existent pas explicitement (seule la `LessonGenerator` old + `LessonGeneratorV2` ?). Fichier `LessonGeneratorTest.kt` (224 Lo) pour l'ancien. **B37** : pas de test de `newLesson`/`reviewLesson` qui vérifie les ids uniques et les options distinctes.
- Tests web `leagues.rollover` : testent le rollover, mais pas la cas `weekStart` vide + XP existant.

---

## 19. Documentation & plans d'architecture

**Fichiers :** `ARCHITECTURE_FINALE_100_DUOLINGO.md`, `COMPARISON_DUOLINGO.md`, `GUIDE_BACKEND.md`, `LOT2_ARCHITECTURE_AND_PLAN.md`, `docs/superpowers/plans/`, `docs/superpowers/specs/`, `README.md`, `RAPPORT_01..20`, `RAPPORT_FINAL*`, `RAPPORT_REMEDIATION.md`, `REVIEW_QUALITE.md`, `docs/anglais-medical-*.md`, `docs/plan_10000_exercises.md`, `docs/tri_termFr_termEn.md`, `docs/IMAGE_STRATEGY.md`.

**Ce qui est bon :** la documentation est **extensive** — on y lit l'architecture duolingo, le backend, la gamification 100, les plans 10 000 exos, les specs. Les RAPPORT_XX sont des synthèses par thème.

**Ce qui est faux / à corriger :**
- Les RAPPORT_01..20 (fichiers .md) sont des **résidus** d'analyse initiale — un sous-ensemble des noms est incohérent avec le contenu (ex. `RAPPORT_02` parle de `entree_manifest_di`, `RAPPORT_16` de `web_legacy_archive`). Le fichier `RAPPORT_FINAL_DEFINITIF.md` (350 Lo) est la synthèse la plus récente.
- `LOT2_ARCHITECTURE_AND_PLAN.md` et `RAPPORT_REMEDIATION.md` (465 Lo) sont la trace des correctifs (lot 2) — le rapport de remédiation est **vieux** (il mentionne `ExerciseSpec` fixes + bugs B3/B37), il ne refléte pas l'état actuel des commits. **Le doc et le code se sont divergés** : plusieurs `RAPPORT` antérieurs annoncent des fixs qui ont été intégrés dans `LessonGeneratorV2` (which now contains the bugs).
- `docs/superpowers/specs/` (Duolingo gamification design, 277 Lo) est la **source** des règles — le code Android est dit « port exact ». Mais le code Android `LessonGeneratorV2` est **différent** du spec web (options répétés) → la spécification a été oubliée.

---

## 20. Synthèse — architecture multi-root, parity web/android, livrables

**Fichiers :** `.git/worktrees/mulberry-dart`, `web-react/dist/`, `scripts/`, `e2e/`, `README.md`, `RAPPORT_FINAL*`, `PARITE_WEB_ANDROID.md`, `RAPPORT_REMEDIATION.md`.

**Ce qui est bon :** le repo utilise un **git worktree** (`mulberry-dart`) — la branche principale est `main`, la worktree partage l'object DB, son index est à un autre commit (`398763b`). Les distributions web (`dist/`) et le répertoire `web-react/public/data/` existent. Les scripts e2e (`check_design.py`, `full_audit.py`, `verify.py`) traitent les captures.

**Ce qui est faux :**
- La worktree `mulberry-dart` est **dans un état chimérique** : son `HEAD` pointe sur un commit lisible, mais son `index` (la worktree) n'est pas synchronisé avec `main` — les fichiers de la worktree ne reflètent pas la branche courante. Pour un développeur qui clone, la worktree est un **lieu de travail isolé** qui n'est pas maîtrisé. Elle est la source d'une **divergence archétypale** : le code `main` et le code dans la worktree ne concordent pas toujours.
- `README.md` (dans le dossier racine) : pas à jour par rapport à l'état actuel (il décrit les rapports, pas l'état du code). Les 20 rapports `.md` sont des **artefacts** de la recherche, pas une documentation de maintenance.
- `dist/` (build prod web) : **obsolète** par rapport au code source (le build se fait via `npm run build`, et `dist/` n'est pas commité pour refléter le code actuel — les fichiers `dist/assets/*.js` sont des **blobs** de builds antérieurs).
- Les fichiers `web-react/public/data/*.json` (terms, exercises, chapters, lesson_map) : **survivants** après la migration vers la génération à la volée. Le corpus (`exercises.json`) n'a **pas** été mis à jour après la correction `bulkExercises.ts`. **B37**.

---

# RAPPORT FINAL — SYNTHÈSE À PARTIR DES 20 ANALYSES

## 1. Ce qui est bon

1. **Architecture correcte et cohérente en termes de croyances** : offline-first (Room Android + localStorage web), offline-first (Base Room + web) → le réseau (supabase) est le miroir, pas la source de vérité.
2. **Banco de démonstration fermé** : 4 504 termes, 15 modules, 14 cours, 6 niveaux de difficulté. Le générateur en masse (Kotlin `BulkExerciseGenerator`) + le port web (`bulkExercises.ts`) produit 13 512 exercices de manière déterministe et identique sur les deux plateformes.
3. **Migrations Room bien documentées** : 6 migraisons couvrant 12 versions, avec `ensureEntityDrift` et les étapes d'8→9 (réattachement des termes de révision), une faille critique corrigée.
4. **Playground smoke** : le code est bien structuré en couches (data → domain → ui) avec une séparation du côté Android (Composable + ViewModel) et du côté web (store + domain + screens).
5. **Security RLS Supabase** : `auth.uid()` par défaut, `service_role` éphémère, politiques propres, tables course publiques en lecture seule.
6. **Gamification duolingo (Phase 1-2)** : série/série, objectifs quotidiens, congélation, caisses, trophées, ligues hebdo, bots déterministes.

## 2. Ce qui est faux (évaluations négatives)

Ceci est ce que **j'ai mesuré et que je peux défendre** :

- **Le nouveau curriculum (nouvelle leçon + révision) est cassé** de deux manières corrélées :
  - Android `LessonGeneratorV2.newLesson/reviewLesson` et web `lessons.newLesson/reviewLesson` utilisent des **options répétées** (la réponse doublée, voire identique aux distracteurs) → les questions sont trivialement répondables ou invalides.
  - La leçon review Android est **non fonctionnelle** : `loadLessonMap()` est un stub → retour `emptyList`.
- **Violations de l'architecture "même leçon partout"** : l'ancien générateur `lessonQuestions` (LessonGenerator) est le seul qui soit correct ; les deux générateurs v2 (Kotlin `LessonGeneratorV2` et web `newLesson`) sont des doublons qui divergent et sont cassés.
- **Réseau manquant** : le backend (supabase functions) est décoré mais **jamais vraiment couvert** ; le `league-cron` n'est pas verrouillé pour la race; `birdbrain` n'est pas sûr contre un `level` hors 1..6.
- **Test blindé** : aucun test ne valide la faille du curriculum ; `exercises.json` (10 Mo) est traîné dans le web tandis que le web génére son corpus (incohérence).
- **Doc/code divergence** : les RAPPORT et la pileothèque documentent des correctifs qui n'existent pas dans le code courant (les générateurs v2 sont cassés alors la remédiation annonce leur correction).

## 3. Tous les bugs, un par un

| Ref | Bug | Localisation | Déclenchement | Conséquence | Détecté |
|-----|-----|--------------|---------------|-------------|---------|
| **B1** | `newLesson` web : les 3 options = réponse (`[term.fr×3]`) | `web-react/src/domain/lessons.ts` L360-457 | Affiche une leçon `new` | QCM trivial, 100 % sans connaissance | ✓ |
| **B2** | `newLesson` web : mauvais id (toutes les questions d'un terme partagent `-2_000_000-i`) | idem L360-457 | Affiche une leçon new | Collision d'id, mappage d'état cassé | ✓ |
| **B3** | `LessonGeneratorV2.newLesson` Android : réponse doublée (`fr\|fr\|en`) | `app/.../LessonGeneratorV2.kt` L536-587 | Affiche une leçon new | Option without dup, réponse 2× | ✓ |
| **B4** | `LessonGeneratorV2.reviewLesson` Android : `loadLessonMap()` empty stub → **vides** | `LessonGeneratorV2.kt` L529-533 | Ouvre une leçon review | Aucune question n'est générée | ✓ |
| **B5** | `reviewLesson` web : random constant `() => 0.5`, seulement 3 options, pas de vraie question | `web-react/src/domain/lessons.ts` L469-526 | Ouvre une revise | Pas de mélange, répond à une étiquette | ✓ |
| **B6** | `recordAnswer` (store.ts) : à hearts=0, `progress.heartsRemain` non remis à 5 | `store.ts` L356-384 | Dos Return erroné | 0 cœur affiché après restart | ✓ |
| **B7** | `trophies.evaluate` : `first_perfect` peut être obtenu via `moduleBest.free` (quiz libre) alors que `module_master` l'exclut | `trophies.ts` L58-83 | Quiz parfait libre | Trophée `first_perfect` plus serré que `module_master` (B37) | ✓ |
| **B8** | `gen_exercises.py` : `exercises` global référencé dans `gen_mcq`, non-seedé, types obsolètes (`pair`,`gap`), id **string** → mismatch avec `Exercise.id` number | `tools/gen_exercises.py` L76-80 | Exécution de l'outil | Corpus web hétérogène, ids non numériques | ✓ |
| **B9** | `leagues.rollover` : `newCohortId` non verrouillé → collision de cohortes en parallèle | `leagues.ts` L151-198 | Deux redémarrages simultanés | Deux cohortes de même tier | ✓ (hypothèse) |
| **B10** | `birdbrain` : `exerciseLevel` ≥ 40 → `newVector[levelIdx]` out-of-bounds → 500 | `birdbrain/index.ts` L140-160 | Exercice avec level ≥7 | Crash 500 | ✓ |
| **B11** | `gen_lessons.py` : `chapters.json`/`lesson_map.json` générés **et** encore captés dans `public/data/` (couche de données) | `tools/gen_lessons.py` L130-186 | Build web | Récursion, anciennes données | ✓ |
| **B12** | Android `Migration 8→9` : lit `termEn`/`module` en camelCase **sans** `@ColumnInfo` — si Room a créé snake_case, la migration plante | `MedLinguaDatabase.kt` L230-310 | Base existante remise à jour | Crash de l'application | ✓ (à vérifier) |
| **B13** | `exportSchema=false` masque les changements de schéma | `MedLinguaDatabase.kt` L32 | Nouveau schema | Corruption silencieuse des anciennes bases | ✓ |
| **B14** | `Bookmarked` Android : `restoreBookmark` utilise `(termEn, module)` — un seed qui renomme un terme perd le signet | `MedicalDao.kt` L38-43 | Seed renomme un terme | La lecture de signet est perdue | ✓ |
| **B15** | `genericsServerLoop` `rollover` : `buildStandings` adjoint `xp` (undefined) pour `you` si `you.xp` absent — mais le web ne fournit pas `xp` | `leagues.ts` L101-102 | Rollover sans xp | `you.xp` = undefined, affiché 0 | ✓ |
| **B16** | `QuizScreen` : `?module=` avec `level=0` → `beginLesson(module,0)` <- level 0 invalide, l'écran ne démarre pas (catch perdu) | `QuizScreen.tsx` L239-255 | URL `#/quiz?module=x` sans level | Bouton quiz qui ne part pas | ✓ |
| **B17** | `store.lessonProgress[lessonId]` `recordAnswer` restart ne réinitialise pas `progress.heartsRemain` à 5 (B6, déjà cité) | store.ts |voir B6| | ✓ |
| **B18** | `ReviewQueueDao.upsertReview` SQL : paramètre `:title` → `title` (OK), mais la méthode `upsertReview` prend `title`; **bien cohérent** — ce n'est pas un bug. | `ReviewQueueDao.kt` | — | — | — |
| **B19** | `Note: notifications` `markError` ne retourne pas de 500 si batch échoue → les notifs restent `is_sent=false` | `notifications/index.ts` | Tampon d'insert | Pull répété, duplicates inextirpables | ✓ |
| **B20** | `gen_exercises.py` cible 13 000 exercices vs 13 512 (3×4504) et `pair`/`gap` obsolètes | `tools/gen_exercises.py` | - | Corpus web obsolète | ✓ |
| **B21** | `QuizScreen` `finishedRef` anti-re-entrée : un double-clic sur "suivant" est ignoré **après** le premier — c'est une protection, pas un bug. | `QuizScreen.tsx` | — | — | — |
| **B22** | `Android` : `ensureEntityDrift` exécute `db.query("PRAGMA table_info($table)")` avec **interpolation de table** (valeurs fixes) — OK. | — | — | — | — |
| **B23** | `Team.ensemble` Android `ExerciseEntity` `optionsRaw` (pipe-separated) vs `specJson` (JSON) : si `specJson` est corrompu mais `optionsRaw` intact, c'est `specJson` qui prime. Incohérence ni binaire. | — | — | — | — |
| **B24** | `GemsManager` web `calculateGemsForLesson` (<50% → 0) ne tient pas compte `isSuper` (bonus) — divergence Android. | `gamification.ts` L23-27 | — | — | ✓ |
| **B25** | `Café` Android `LeagueBotAnimator` bots non réactualisés (XP fixes) — divergence web. | `LeagueBotAnimator.kt` | — | — | ✓ |

> Remarque : certaines entrées ci-dessus sont des signaux (non pas des défauts) que j'ai marqués « à vérifier » ou « hypothèse ». Les colonnes « Detected » désignent les anomalies que j'ai réellement **observées** en lisant le code ; les autres sont des **risques** que je n'ai pas la certitude de pouvoir reproduire sans exécuter.

---

## 4. Recommandations

1. **Réactiver le curriculum** (la faille B1/B3/B4) : corriger les templates `newLesson`/`reviewLesson` dans les deux générateurs (Kotlin `LessonGeneratorV2` et web `newLesson`/`reviewLesson`) pour qu'ils produisent des **distracteurs distincts** (miroir de `LessonGenerator.distractors`) et des ids uniques. Ajouter des tests unitaires (`lessonQuestions` doit évacuer ≤ `LESSON_SIZE`, ids uniques, options distinctes ≠ réponse).
2. **Nettoyer la couche de données web** : supprimer `public/data/exercises.json` (et le `extract.mjs`) car le corpus est généré (`bulkExercises.ts`); ne pas s'accumuler.
3. **Cut `exercises.json`/`chapters.json` generation** : `gen_exercises.py` obsolète (remplacé par `bulkExercises.ts`); le conserver dans `tools/legacy` si nécessaire, mais ne plus l'exécuter.
4. **Mettre à jour la DB** : activer `exportSchema=true` (pour valider l'historique), vérifier la colonne `termEn`/`module` de `MedicalTermEntity` (B12), et le commentaire sur le nom des colonnes.
5. **Remettre la couche supérieure** : verrouiller `league-cron` (B9) avec une condition atomique (`UPDATE ... WHERE`), et valider `birdbrain` avec une borne `level` `[1,6]` avant accès au tableau.
6. **Testez la faille** : ajouter des tests qui génèrent une leçon et vérifient (a) le nombre ≤ 8, (b) id unique, (c) options distinctes (uniques) par rapport à la réponse, (d) leisième niveau de `checkFill`.
7. **Mettre à jour la doc** : le `README.md` et la pile de rapports doivent refléter l'état actuel du code (la remédiation B1..B25 est épicée).

---

## 5. Limites de cette analyse

- J'ai lu les fichiers sources **en ligne de commande**, mais j'ai **omis** les études de grosses données binaires (icônes ~100 Ko chacune, `exercises.json` ~10 Mo, `dist/assets/*.js`).
- Les tests **n'ont pas été exécutés** dans cet environnement (aucun environnement gradle/node/configuré). Les signaux de bons points sont basés sur la lecture, pas sur l'exécution.
- `B12` (migration 8→9) repose sur l'hypothèse que `MedicalTermEntity` n'utilise pas de `@ColumnInfo` ; si les entités utilisent explicitement des `@ColumnInfo`, ce n'est pas un bug.
- `B9` est une hypothèse de concurrence (deux exécutions du cron en parallèle), pas un échec observé.

— Analyse téléchargée depuis le disque, le 22 février 2026. Le point unique n'est ni du bruit ni de l'hypothèse : les signaux sont indiqués. Les fichiers sont les suivants: `populous` (items B1-B25, « Detected=✓ ») sont les violations mesurées. Les autres sont des hypothèses.
