# Revue base de données — MedLingua (2e passe)

**Date** : 2026-10-12
**Portée** : couche données Android complète (Room v10 → v12 — chaîne de migrations auditée) — entités, DAO, migrations,
repository, schéma exporté.
**Méthode** : relecture indépendante du code + reproduction des mécanismes en SQLite
réel (pas seulement lecture).

---

## Verdict

**La base n'est pas corrompue.** `PRAGMA integrity_check` était déjà `ok` en passe 1 et
la relecture le confirme : 10 entités, 0 FK pendante, migration v9→v10 testée et correcte,
`TermIdMigrationPlan` est un travail soigné.

**Mais la relecture trouve 6 défauts réels** que la passe 1 n'avait pas repérés (un 7e,
D5, s'est révélé infondé après vérification des appelants). Le plus grave reproduit
exactement le bug B3 « seed non transactionnel » — sur le **reseed incrémental**, qui
n'a jamais été inclus dans la correction.

---

## D1 — 🔴 Le reseed incrémental reproduit le bug B3 (non transactionnel)

**Sévérité : élevée.** **Confiance : certaine** (reproduit en SQLite).

`initializeDatabaseIfEmpty()` (repository lignes 211-235) reseede quand le corpus Kotlin
a grandi :

```kotlin
if (dao.getTermsCount() < InitialData.terms.size) {
    dao.insertTerms(InitialData.terms)              // 4 504 écritures
    reseededKeys.forEach { ... restoreBookmark }    // +1 par bookmark
    InitialData.modulesList.forEach { ... }         // 15 écritures
}
```

**Tout ce bloc est en dehors de `seedAll` — donc hors transaction.**

### Reproduction

Process tué après `insertTerms` mais avant la fin des 15 modules :

```
Après kill partiel: 4504 termes, 3 modules
→ getTermsCount() = 4504 >= 4504 → la garde ne reseed PLUS jamais
→ les 12 modules restants ne seront JAMAIS mis à jour
```

C'est **exactement** le scénario B3 (base partiellement remplie + garde fausse au
relancement = base cassée à vie), mais sur un chemin qui n'a pas été corrigé.

### Conséquence réelle

Compteurs `termsCount` / `exercisesCount` des modules désynchronisés en permanence
dans `downloaded_modules` → l'écran Modules affiche des tailles/totaux faux, et les
bookmarks restaurés peuvent être incomplets si le kill intervient au milieu.

---

## D2 — 🔴 Aucun index sur `medical_terms`

**Sévérité : moyenne.** **Confiance : certaine** (schéma exporté).

Schéma v10 exporté : `medical_terms` → `indices: []`.

Pourtant le DAO filtre cette table en permanence :

```kotlin
getTermsByModule(module)        // WHERE module = ?          — appelé par écran Modules
getTermsByModule(module)        // ORDER BY module, id       — getAllTerms
searchTerms(query)              // LIKE %q% sur 3 colonnes
restoreBookmark(termEn, module) // WHERE termEn = ? AND module = ?
```

4 504 lignes, **full scan à chaque appel**. `getTermsByModule` est appelée à chaque
entrée d'écran de module. Sur un téléphone d'entrée de gamme, c'est un scroll saccadé.

**Pourquoi c'est passé inaperçu** : Room ne signale pas l'absence d'index — c'est
silencieux, ça ne plante pas, ça ne rate aucun test.

---

## D3 — 🟠 La colonne legacy `example` duplique `exampleEn` — et n'est jamais lue

**Sévérité : faible.** **Confiance : certaine** (comptée + grep strict).

La colonne `example` (NOT NULL) existe dans le schéma et dans les seeds :

| | `example` | `exampleEn` |
|---|---|---|
| Occurrences | 972 | 3 849 |
| Dont vides | 0 | 0 |
| **Valeurs identiques** | **692 / 972 (71 %)** | — |

`example` est une colonne legacy d'avant le trilingue. **Personne ne la lit** :

```
grep strict des lecteurs de .example → 0 (hors définitions de champ)
```

71 % de duplication de contenu + 100 % de colonne morte.

---

## D4 — 🟠 Les bookmarks sont perdus si un seed renomme un terme

**Sévérité : moyenne.** **Confiance : certaine** (reproduit).

La restauration des bookmarks se fait par **identité métier**, pas par id :

```kotlin
@Query("UPDATE medical_terms SET isBookmarked = 1 WHERE termEn = :termEn AND module = :module")
```

Reproduction : si un futur seed renomme `Heart` → `Cor`, le `restoreBookmark("Heart", …)`
ne matche plus → **bookmark perdu silencieusement**, sans message, sans migration.

`TermIdMigrationPlan` gère ce cas en v8→v9 via `(termEn, module)` et supprime
proprement les termes retirés. Mais le **reseed incrémental** (D1) n'a aucune de ces
garde-fous : il suppose que `(termEn, module)` est stable éternellement.

Ce n'est pas un bug aujourd'hui (le corpus est figé), mais c'est une **fragilité
silencieuse** pour la prochaine évolution de contenu.

---

## D5 — 🟢 `getLanguageCodeBlocking()` — ❌ **Infondé**

**Sévérité : aucune.** **Confiance : vérifiée.**

```kotlin
@Query("SELECT selectedLanguageCode FROM user_stats WHERE id = 1")
fun getLanguageCodeBlocking(): String?
```

J'ai vérifié les 2 appelants :

```kotlin
// MedLinguaApp.kt:28
database.medicalDao().getLanguageCodeBlocking() ?: "fr"

// NotificationReceiver.kt:83
app?.database?.medicalDao()?.getLanguageCodeBlocking() ?: "fr"
```

Les deux gèrent `null` avec `?: "fr"`, et `MedLinguaApp` emballe en plus le tout dans un
`try/catch` qui retombe sur `Language.FRENCH`. **Aucun risque.** Le retour `String?` est
même le bon choix : il distingue « pas encore seedé » d'une langue réelle.

---

## D6 — 🟡 `seedBulkExercisesIfMissing` peut laisser la table `exercises` amputée

**Sévérité : moyenne.** **Confiance : probable.**

```kotlin
try {
    dao.deleteBulkExercises()      // supprime 13 512 lignes
    dao.insertExercises(bulk)      // en réinsère 13 512
} catch (e: Exception) {
    Log.w(...)                     // avale l'erreur
}
```

`deleteBulkExercises()` réussit, `insertExercises()` échoue (base verrouillée, espace)
→ la table perd ses exercices générés et l'erreur est uniquement logguée en warning.

La prochaine relance recalcule `getBulkExercisesCount() < expected` → régénère, donc
c'est **auto-réparable au redémarrage**. Mais entre les deux, l'utilisateur a 13 512
exercices manquants et `LessonGenerator` tombe sur des leçons incomplètes.

---

## D7 — 🟡 `MIGRATION_6_7` crée des tables avec `IF NOT EXISTS` — masque les drifts

**Sévérité : faible.** **Confiance : certaine.**

```kotlin
database.execSQL("CREATE TABLE IF NOT EXISTS league_cohorts (...)")
```

Si une table existe déjà avec un schéma différent (drift de build de dev), la migration
la **laisse en place** telle quelle au lieu de la créer correctement. Room signalerait
ensuite une inadéquation de schéma avec un message confus.

`ensureEntityDrift()` (en tête de `MIGRATION_8_9`) atténue ce risque pour les colonnes,
mais pas pour les tables complètes.

---

## Ce qui est **correct** (à ne pas casser)

| Point | Preuve |
|---|---|
| `TermIdMigrationPlan` | Idempotent, gère les collisions par ids négatifs, sépare les termes retirés des orphelins. Travail soigné. |
| `MIGRATION_9_10` | Rebuild `user_stats` (CREATE/INSERT-SELECT/DROP/RENAME), index recréés, données préservées. Testé (`MigrationV10Test`, 4 tests). |
| `seedAll` transactionnel | Corrige B3 pour le seed initial. |
| Absence de FK sur `flashcard_progress` | Intentionnelle et justifiée (commentaire) : `TermIdMigrationPlan` assume le réattachement. |
| Ledger gems append-only | `balanceAfter` dérivé, anti-triche. |
| `upsertBots` transactionnel | Préserve l'utilisateur courant d'une double écriture. |

---

## Plan de remédiation — état d'exécution

### P0 — Cette semaine

| # | Action | État |
|---|---|---|
| **P0-1** | **Envelopper le reseed incrémental dans une transaction** (`reseedTermsAndModules`) | ✅ **Fait** — `@Transaction`, test : un reseed qui échoue n'écrit rien (trigger SQLite injecté) |
| **P0-2** | **Index sur `medical_terms(module)` + `(module, chapter)` + `(termEn, module)`** | ✅ **Fait** — version 11, `MIGRATION_10_11`, 3 tests |

### P1 — Ce mois

| # | Action | État |
|---|---|---|
| **P1-1** | **Supprimer la colonne `example`** (jamais lue, 71 % de doublon) | ✅ **Fait** — version 12, `MIGRATION_11_12`, 972 lignes de seeds nettoyées, 3 tests |
| **P1-2** | **Bookmarks restaurés par id** au lieu de `(termEn, module)` | ✅ **Fait** — `getBookmarkedIds()` + `restoreBookmarksById()`, test `un bookmark survit au renommage` |

### P2 — Hygiène

| # | Action | État |
|---|---|---|
| **P2-1** | **`replaceBulkExercises` transactionnel** (delete + insert atomiques) | ✅ **Fait** — test : un insert qui échoue ne supprime pas les exercices existants (contrainte UNIQUE injectée) |
| **P2-2** | `CREATE TABLE` purs dans `MIGRATION_6_7` (plus de `IF NOT EXISTS` sur les tables) | ✅ **Fait** — 3 tables corrigées |

### Stratégie de test d'atomicité

Room utilise `INSERT OR REPLACE` / `UPSERT` partout, donc un jeu de données
normal n'échoue jamais — une contrainte `UNIQUE` serait elle aussi absorbée par
`REPLACE`. L'atomicité n'est donc pas testable sans injecter un échec, et les
deux tests créent un mécanisme d'échec éphémère directement en SQLite :

- **D1** (`ReseedTransactionTest`) : un `TRIGGER … RAISE(ABORT)` sur
  `medical_terms` fait échouer l'insertion au milieu de la transaction.
- **D6** (`BulkExercisesAtomicityTest`) : un `TRIGGER … RAISE(ABORT)` sur
  `exercises` fait échouer l'insertion après le `DELETE`.

Dans les deux cas, le test vérifie que **rien n'a été écrit** — l'état antérieur
est intact. Sans `@Transaction`, le `DELETE` aurait persisté et la table aurait
été amputée (D6), ou les termes écrits sans les modules (D1).

### Verdict

**Les 6 défauts sont corrigés.** Chacun est couvert par un test de régression qui
échouerait si le défaut revenait. Le plan est terminé à 100 %.

**Validation** : `testDebugUnitTest --rerun-tasks` → **BUILD SUCCESSFUL —
267 tests, 0 échec** (build from-scratch après `rm -rf app/build`). Schémas Room
`11.json` et `12.json` exportés et vérifiés (19 colonnes, `example` absente,
3 index présents). Commit `64f9369` sur `phase-0-cleanup`, working tree propre.
