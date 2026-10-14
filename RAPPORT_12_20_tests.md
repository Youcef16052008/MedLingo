# PARTIE 12/20 — Tests unitaires & Robolectric (20 classes, 164 tests)

**Méthode** : 20 classes de test lues + **résultats de build analysés** (`app/build/test-results/testDebugUnitTest/*.xml`, 20 fichiers) + relance de `./gradlew testDebugUnitTest` en arrière-plan (résultats ci-dessous).

## État des tests (build précédent, depuis les XML)

```
TOTAL tests=164  failures=0  errors=0  skipped=0
```
**20/20 classes au vert, 164 tests, 0 échec, 0 ignoré.** C'est un bon signal — mais il faut le relativiser (voir ci-dessous).

## Qualité réelle des tests — elle est **inhabituellement bonne**
Les noms de tests et les KDoc montrent que chaque test est une **régression documentée sur un bug réel** :
- `ModuleWiringTest` — « 13 of 17 seed files were declared but never concatenated by `AnatomyDatabase.getAllTerms`, and module counters matched terms with bidirectional `String.contains` (so "Anatomie Pathologique" absorbed all "Anatomie" terms) ».
- `OfflineContentSizeTest` — « the offline module sizes used to be hardcoded literals (14.2 MB, 7.2 MB, …) unrelated to the content actually shipped ». Test « the whole local content stays a believable order of magnitude ».
- `TermIdMigrationPlanTest` — 9 tests sur la réattachement par (termEn, module), dont `does not confuse terms that concatenate to the same key` et `drops progress pointing at a term that no longer exists`.
- `CanonicalExerciseSpecTest` / `ExerciseSpecFixesTest` (30 tests) — couvrent `WordbankOrder` (tokens multi-mots, doublons, casse, `neverCoercesUnknownWordsToZero`), `swapLanguage` (round-trip FR→EN, AR→EN), `checkFill` (partial credit, reject 1-char answers, empty).
- `LeagueHeartsRegressionTest` — horloge injectée, semaine UTC, mois localisés AR/FR, `is identical in extreme timezones`.
- `SeedIntegrityTest` — 9 assertions sur les données, **qui itèrent réellement les 4 512 termes**.
- `ProgressionManagerTest` — 17 tests : freeze, syncWeek, `addXp` reset à minuit, objectif une seule fois par jour.

**Point remarquable** : `CanonicalExerciseSpecTest` contient un test **qui documente le danger plutôt que de le nier** : `swapping from an already swapped spec destroys the prompt (documented hazard)`. C'est exactement la signature d'une équipe qui assume la dette au lieu de la cacher.

## Manques critiques (couverture)

| Zone | Tests ? | Risque |
|---|---|---|
| `MedLinguaViewModel` (977 l., 65 champs d'état) | **0 test** | C'est là que sont les bugs (persona, matching, pools vides, filterTerms) |
| `MedLinguaRepository` (597 l., seeding) | **0 test** | Seed non transactionnel, `saveLessonScore` O(n) |
| `QuizScreen` / tous les composables | **0 test** (pas de test Compose UI) | Matching toujours correct → non détecté |
| `MatchingExerciseView` | **0 test** | Bug de scoring invisible |
| `TtsManager` | **0 test** | Races conditions TTS |
| `NotificationHelper` | seules les constantes sont testées (`ExampleUnitTest.testNotificationHelperConstants`) | i18n, icône, heure perdue au reboot |
| `AdaptivePlacementEngine` (CAT) | **0 test** | Le score pondéré n'est pas validé |
| `MainActivity` / navigation | **0 test** | |
| Couverture: 20 classes pour ~8 000 lignes de prod | ~15 % des fichiers | |

**Biais le plus important** : 100 % des tests portent sur `domain/` + `data/initial/` + les migrations. **La couche qui contient les bugs que j'ai trouvés (UI + ViewModel + Repository) n'est pas testée.** Les 164 tests verts ne garantissent donc **pas** que l'app marche pour l'utilisateur.

## Actions concrètes
1. **Tests Compose UI** : ajouter `androidx.compose.ui:ui-test-junit4` (déjà dans les dépendances du catalog en général) et écrire au minimum : `MatchingExerciseView` (un appariement faux ne complète pas l'exercice / le score reflète les erreurs), `QuizScreen` (état pool vide affiché), `HeartsGemsTopBar` (affichage du tier non tronqué), `PathScreen` (nœud verrouillé → popup 70 %).
2. **Tests du ViewModel** : `MainViewModelTest` avec une `FakeMedLinguaRepository` + `FakeClock` — cas : base vide (pas de persona), pool d'exercices vide (pas de complétion), `filterTerms` asynchrone.
3. **Test du repository** : `MedLinguaRepositoryTest` avec une Room in-memory — cas : `initializeDatabaseIfEmpty` interrompu → base cohérente (transaction), `saveLessonScore` lit par clé.
4. **Test du CAT** : `AdaptivePlacementEngineTest` — 5 bonnes → niveau recommandé, arrêt anticipé, score pondré identique pour deux séquences équivalentes.
5. **Test du TtsManager** : Robolectric + shadow TextToSpeech — callback tardif d'une génération périmée, `pendingSpeak` droppé sur stop.
6. **Test de notification** : icône = `R.drawable.ic_notification` (blanche), présence des traductions, `BootReceiver` relit l'heure persistée.
7. Ajouter un **seuil de couverture** (JaCoCo/Kover) sur `domain` (déjà bon) et un **objectif sur `ui/viewmodel`** (0 → 50 %).
8. Renommer `ExampleUnitTest` → `LegacySeedSmokeTest` (il ne teste pas un « example ») et `ExampleRobolectricTest` → `AppContextTest`.
9. Vérifier le passage de `./gradlew testDebugUnitTest` relancé (résultat en cours d'exécution) — tout échec serait un signal d'environnement, pas de code.
