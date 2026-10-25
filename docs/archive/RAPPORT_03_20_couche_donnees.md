# PARTIE 3/20 — Couche données : entities, DAO, Room Database, Repository

**Fichiers lus intégralement** : 10 entities, `MedicalDao.kt` (160 req.), `MedLinguaDatabase.kt` (383 lignes), `TermIdMigrationPlan.kt`, `MedLinguaRepository.kt` (597 lignes), `OfflineContentSize.kt`, `TermIllustrations.kt`, `AnatomyDatabase.kt`.

## Observations

### Modèle de données
10 tables Room, schéma **v10** : `medical_terms`, `flashcard_progress`, `exercises`, `downloaded_modules`, `user_stats`, `gems_transactions`, `league_cohorts`, `league_members`, `trophies`, `lesson_scores`. Modèle cohérent avec le périmètre offline d'une app Duolingo-like.

- `UserStatsEntity` est une **god entity** : 36 colonnes (stats globales, progression 6 niveaux, économie gems, ligue, Super, objectifs/caisses/trophées). La classe documente elle-même les phases d'ajout successives. Des comportements utiles y vivent (`isLevelUnlocked`, `highestUnlockedLevel`, `accuracyPercentage`, `isSuperActive(clock)`).
- **Problème de données fausses par défaut** : `UserStatsEntity.createDefault()` (et `UserStatsEntity()`) injectent une progression fictive — `streakDays = 12`, `learnedTermsCount = 156`, `quizzesCompleted = 18`, `level1Score = 85`, `level2Score = 75`, `totalPoints = 2450`. Le repository précise bien `// demo persona progress, NOT "all 1641 terms learned"`. C'est un **choix délibéré de persona de démo**, mais : (a) `MedLinguaDatabase` ne crée jamais cette ligne par défaut — la table est vide au premier lancement et c'est `initializeDatabaseIfEmpty()` qui l'insère ; (b) en cas de lecture de `user_stats` avant seeding, `UserStatsEntity()` sert de fallback implicite dans **toute** la couche repository (`dao.getUserStatsOnce() ?: UserStatsEntity()`), ce qui signifie que l'utilisateur hérite de cette fausse progression (12 jours de série, 85% au niveau 1) **même si la base est vide**. C'est un bug réel : la persona n'est plus une démo, c'est la valeur de prod.
- `MedicalTermEntity` : `imageAsset` et `audioAsset` existent mais `TermIllustrations` ne se base plus que sur **2 illustrations locales** (fémur, neurone) — les ~150 chemins audio/ images fantômes ont été nettoyés (commit `502c03e`). Colonnes probablement now dead.
- `ExerciseEntity` : double représentation (colonnes plates historiques + `specJson`), avec `toExerciseSpec()` qui parse le JSON et `buildLegacySpec()` en fallback pour 6 types (mcq, sentence_order, matching, fill_blank, reading, clinical_case). `WordbankOrder.computeCorrectOrder` est utilisé pour `sentence_order`.
- `LeagueTier` : 10 tiers Bronze→Diamond, avec `nextTier`/`prevTier` correctement gérés (`order` est 1-indexé, `getOrNull(order)` = suivant). Conforme à Duolingo.
- `GemsTransactionEntity` : ledger append-only — la balance `gems` est dupliquée dans `user_stats` (champ mutable) **et** dans `balanceAfter` des transactions. Le doc d'architecture annonçait « balance dérivée, jamais mutable (anti-cheat) » ; en réalité la balance est **aussi mutable** dans `user_stats` (toutes les fonctions `addGems`/`spendGems` font `current.copy(gems = ...)`). L'anti-triche affirmée n'existe donc pas : la source de vérité est la colonne mutable. Le ledger est purement un journal d'audit.
- `FlashcardProgressEntity` : clé primaire `termId` (pas de relation étrangère déclarée — Room ne supporte pas les FK sans `ForeignKey` ; il n'y en a aucune). La cohérence est donc applicative, et c'est exactement ce qui a rendu nécessaire la migration 8→9.

### Migrations Room — c'est le point le plus soigné du projet
- `MIGRATION_8_9` : renumérotation des ids de seeds → réattachement de `flashcard_progress` **par (termEn, module)** via `TermIdMigrationPlan`, avec **ids temporaires négatifs** pour rendre les updates ordre-indépendants (commentaire très explicite sur le risque de collision 3→4 pendant que 7→3). Gestion explicite des collisions ambiguës (abandonnées puis nettoyées comme orphelines). C'est du travail de très bon niveau.
- `ensureEntityDrift()` : répare les colonnes/index ajoutés sans migration lors des builds de dev, de façon idempotente. Reconnaît explicitement que le schéma a dérivé.
- `MIGRATION_9_10` : reconstruit `user_stats` (CREATE/INSERT-SELECT/DROP/RENAME) pour **retirer** les colonnes de cœurs — méthode correcte pour Room qui refuse les DROP COLUMN. Commentaires précis.
- **Pourtant, un risque sérieux** : la migration `8_9` et `9_10` supposent que l'utilisateur de prod vienne d'une base **créée en v6/v7/v8**. Or `MIGRATION_6_7` crée les tables ligues/gems, `MIGRATION_7_8` crée un index. Les bases v1..v5 n'ont **aucune migration** — mais le `fallbackToDestructiveMigration` n'est PAS activé, donc Room **crashe** (`IllegalStateException`) si une base v5 ou moins existe. Vu que l'app n'est probablement pas publiée (versionCode 1), le risque est théorique aujourd'hui, mais il devient réel dès la première publication si un utilisateur est en v6-.
- Autre point : `MIGRATION_9_10` ne **supprime pas** les données de cœurs (colonnes abandonnées) — c'est l'objectif, correct, mais si l'utilisateur avait acheté des congélations `streakFreezeCount` est conservé (documenté).
- `exportSchema = true` mais **aucun fichier de schéma JSON n'est présent** dans le repo (pas de `app/schemas/`). Room devrait normalement générer `10.json` lors du build ; un test `MigrationTestHelper` ne peut donc pas être écrit correctement, et les tests de migration (`MigrationV10Test.kt`) doivent mocker.

### DAO
~45 requêtes, typées `Flow` pour les observables et `suspend` pour les ponctuelles. **Aucune transaction déclarée** (`@Transaction` absent) — or `initializeDatabaseIfEmpty()` enchaîne `insertTerms` + `restoreBookmark` + `insertExercises` + 15 upserts de modules + stats + gems + cohort + bots **sans transaction**. Un kill du process en plein milieu laisse une base partiellement seedée, et la condition de garde (`isDatabaseEmpty`) ne serait plus vraie au relancement → base incomplète à vie. C'est le défaut le plus important de cette couche.
- `searchTerms` : `LIKE '%' || :query || '%'` — pas de `FTS` (alors que le doc d'architecture mentionne une FTS Postgres). Sous SQLite, un LIKE entouré ne peut **pas** utiliser d'index → scan complet de ~4500 lignes à chaque frappe. Acceptable pour 4500 lignes, mais un index `FTS4` serait trivial à ajouter et supprimerait toute latence de recherche.
- `addXpToCurrentUserInLeague` : `UPDATE ... WHERE isCurrentUser = 1` — l'utilisateur est identifié par un booléen, pas par son id (`userId = 1` hardcodé partout). Modèle mono-utilisateur assumé (offline-first, single-user) — cohérent, mais cela rend tout multi-compte impossible sans refonte.

### Repository
- Constructeur `(dao, clock)` avec `Clock` injecté et **exposé** via `currentClock` pour que l'UI lise la même horloge — bon design, testable.
- `safeUpsertUserStats` est protégé par un `Mutex` (écriture concurrente) — bien.
- `submitFlashcardRating` applique SM-2, crédite de l'XP (avec règle `quality*2` si <3, `quality*5` sinon) et met à jour la ligue en une fois. Bonne séparation avec `ProgressionManager`.
- **Anomalie dans `recordQuizCompletion`** : la transaction gems utilise `currentStats.gems` pour `balanceAfter`, mais `currentStats` est l'état **avant** ajout des gems — `balanceAfter` est donc correct, en revanche la double écriture (`gems` mutable + ledger) est omniprésente.
- **Bug subtil** : `recordLevelCompletion` retourne `scorePercentage >= 70 && prevScore < 70` (signal de déblocage) — mais elle lit `current = dao.getUserStatsOnce()` puis applique `ProgressionManager.addXp(current, ...)` : si l'utilisateur n'a pas encore de ligne stats, `UserStatsEntity()` injecte `level1Score = 85`, donc `prevScore = 85 >= 70` → le niveau 1 ne se débloquera **jamais** correctement (signal toujours faux pour le niveau 1, et le niveau 3 reste bloqué par la persona). À rapprocher du bug de `UserStatsEntity()` par défaut.
- **`initializeDatabaseIfEmpty`** : contient la logique de seed ET de reseed (si `getTermsCount() < InitialData.terms.size`, on réinsère). La **seconde moitié duplique intégralement la première** (insertTerms + restoreBookmark + 15 upserts de modules). C'est un ~35 lignes de code dupliqué — un `reseedTermsAndModules()` privé éliminerait la duplication.
- `refreshLeagueCohortIfNeeded` : promotion/rélegation si la semaine est échue, recréation de cohorte + bots + reward gems. Correct mais appelé depuis le ViewModel (à confirmer) — un utilisateur qui change la date de son téléphone déclenche/reset la ligue.
- `saveLessonScore` : lit **toutes** les `lesson_scores` puis filtre en mémoire (`dao.getAllLessonScoresOnce().firstOrNull { it.lessonKey == key }`) — alors qu'une requête `SELECT ... WHERE lessonKey = :key` serait triviale. O(n) en mémoire pour 90 leçons, gaspillage évitable.

## Jugement

La couche données est **solide par endroits (migrations 8→9, injection d'horloge, mutex, ledger) et négligée par d'autres**. Le soin apporté aux migrations contraste avec deux problèmes de fond : (1) l'absence de transaction autour du seed, (2) la valeur par défaut `UserStatsEntity()` qui injecte une progression fictive dans **tous les chemins de fallback**, ce qui peut corrompre la logique de déblocage de niveaux. C'est le code le plus impactant du projet — à corriger en priorité.

## Actions concrètes
1. **`UserStatsEntity()` ne doit pas être une persona** : créer un vrai `EMPTY`/`createFresh()` avec `streakDays = 0, level1Score = 0, ...` et l'utiliser comme fallback dans le repository. Garder la persona uniquement pour la démo/développement.
2. **Wraper `initializeDatabaseIfEmpty` dans une `@Transaction`** (DAO `@Transaction suspend fun seedAll(...)`) ou une `withTransaction` Room. Sans cela, un process killed mid-seed corrompt l'app à vie.
3. Extraire le bloc de reseed dupliqué dans une fonction privée (DRY).
4. Ajouter `SELECT * FROM lesson_scores WHERE lessonKey = :key` au DAO et l'utiliser dans `saveLessonScore`.
5. Ajouter une `fallbackToDestructiveMigrationOnDowngrade()` et définir une politique claire (ou au minimum tester les chemins v1→v10, vu que les migrations < 6 n'existent pas).
6. Remplacer `LIKE '%q%'` par une table `FTS4` (ou au moins un index `LIKE 'q%'` + recherche par préfixe). Bonus : corrige la latence de recherche et la cohérence avec la FTS Postgres du backend.
7. Supprimer les colonnes mortes `imageAsset`/`audioAsset` (ou les documenter comme non utilisées) — une migration v11.
8. Si le ledger gems doit être anti-triche, **dérive** la balance du ledger (`SELECT COALESCE(SUM(amount),0)`) au lieu d'écrire `user_stats.gems` ; sinon, supprimer la promesse « anti-cheat » de la doc.
9. Ajouter les fichiers de schéma Room (`app/schemas/*.json`) au repo (générés par KSP) pour permettre des tests de migration fiables.
10. `Entity` `user_stats` : envisager de la scinder (stats / progression / économie / ligue) pour réduire la god-entity — mais c'est un refactoring lourd, à peser.
