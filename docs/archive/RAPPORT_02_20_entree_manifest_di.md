# PARTIE 2/20 — Manifest, Application, DI, MainActivity (point d'entrée & navigation)

**Fichiers lus** : `AndroidManifest.xml`, `MedLinguaApp.kt`, `di/AppModule.kt`, `MainActivity.kt` (480 lignes, lu intégralement).

## Observations

### AndroidManifest.xml (51 lignes)
- Permissions : `VIBRATE`, `POST_NOTIFICATIONS`, `RECEIVE_BOOT_COMPLETED`. **Minimal et justifié** — aucune permission réseau (`INTERNET`), ce qui est cohérent avec une app 100% offline. Note : sans `INTERNET`, l'app ne peut pas contacter Supabase même si on réactivait le code — les deux visions cohabitent.
- `<queries>` pour `android.intent.action.TTS_SERVICE` : correct (Android 11+ exige la déclaration pour détecter les moteurs TTS).
- `allowBackup="false"` — bon choix (empêche l'extraction de la base Room contenant les données utilisateur), même si `backup_rules.xml`/`data_extraction_rules.xml` existent en doublon.
- `MainActivity` exported, `adjustResize`, theme `.MyApplication`. **`configChanges` absent** → chaque rotation recrée l'Activity. Vu que `ttsManager.shutdown()` est appelé dans `onDestroy()` sur `isFinishing` (et que `MedLinguaApp` précise que `onTerminate` n'est jamais appelé), une rotation détruit/recrée l'activity mais pas le TTS (scope Application). Correct.
- `BootReceiver` et `NotificationReceiver` déclarés, non exportés. `BootReceiver` écoute `BOOT_COMPLETED` pour réarmer les alarmes — standard et juste.

### MedLinguaApp.kt (69 lignes)
- Initialisation paresseuse (`by lazy`) de database/repository/ttsManager via `AppModule` — bon pattern (évite le ContentProvider d'init).
- `onCreate` crée les channels de notification puis **arme conditionnellement** les alarmes en lisant les prefs utilisateur. Le commentaire l'explique : tous les rappels sont ON par défaut mais le choix OFF persiste. **Bonne logique, mais elle vit dans la classe Application** — mélange de responsabilités (infra applicative + logique métier de notifications). Vu que `BootReceiver` et `NotificationHelper` existent, ce bloc gagnerait à y être déplacé.
- **Bug potentiel (réel)** : `repository.initializeDatabaseIfEmpty()` n'est appelé que si `isDatabaseEmpty()`. Or le seed porte **~4500 termes + exercices** : la première ouverture affiche donc une UI vide jusqu'à la fin de l'insertion (suspended coroutine). Aucun état `isLoading`/`isEmpty` n'est exposé dans l'UI à cet effet — l'utilisateur voit des écrans vides pendant quelques secondes au premier lancement. (Vérification à confirmer côté ViewModel/UIState — Partie 7.)
- `CoroutineScope(SupervisorJob() + Dispatchers.Main)` : OK, mais `Dispatchers.Main` pour un seeding de base de données → le travail est délégué à Room (`Dispatchers.IO`), donc acceptable ; un `Default` serait néanmoins plus orthodoxe.
- Le TTS est libéré dans `MainActivity.onDestroy` — commentaire explicatif pertinent.

### AppModule.kt (25 lignes)
- DI manuelle par `object` — suffisant pour un projet de cette taille (pas de Hilt/Dagger). `provideClock()` retourne `SystemClock` : permet l'injection d'une fausse horloge dans les tests (réellement utilisé par `MedLinguaRepository`).
- Limite : pas de scoping (le repository est recréé à chaque appel `provideRepository`... en pratique créé une seule fois via `by lazy` dans l'Application). Acceptable, mais fragile : si un second appelant invoque `AppModule.provideRepository()`, il obtient une **instance différente**. À changer en `by lazy` interne ou en singleton.

### MainActivity.kt (480 lignes)
- Architecture : **navigation maison par `when(currentDestination)`** plutôt que Navigation Compose. `androidx.navigation.compose` est dans les dépendances mais **non utilisé ici** (à vérifier dans le reste du code — Partie 7).
- Onglets : PATH / PRACTICE / LEAGUES / MODULES / PROFILE — la structure est **fidèle à Duolingo** (Learn path / Practice / Leagues / Lexique ici, Profile).
- `BackHandler` hiérarchique (quiz → flash → tab) — bien pensé.
- `CompositionLocalProvider(LocalLayoutDirection provides …)` selon la langue — support RTL arabe réel.
- Notification permission demandée au lancement via `ActivityResultContracts` — Android 13+. Demander la permission **dès le premier lancement** est une pratique discutable : l'utilisateur n'a pas encore vu la valeur. Mieux : demander au moment où l'utilisateur active un rappel dans le profil.
- `AppIntroOverlay` affiché à **chaque lancement** (`introVisible = remember { mutableStateOf(true) }`) — README le dit, c'est délibéré, mais c'est une friction d'ONboarding répétée. Duolingo n'affiche son intro qu'une fois (flag persisted).
- `onSpeak` dans MODULES et FLASH : `uiState.terms.firstOrNull { it.termEn.equals(termText, ignoreCase = true) || it.termFr.equals(termText, ignoreCase = true) } ?: uiState.terms.firstOrNull()` — recherche linéaire O(n) sur ~4500 termes, deux fois par clic. Et `uiState.terms.firstOrNull()` en fallback est **dangereux** : si le terme n'est pas trouvé, on prononce un terme au hasard sans avertir l'utilisateur. L'idiome `firstOrNull()` sur une liste potentiellement vide lancerait en plus `NoSuchElementException` sur `?:`... non, `firstOrNull()` ne lance rien. Mais prononcer un terme aléatoire est un bug comportemental.
- SuperPaywall : paywall + reward + audio studio empilés dans le même `Box` — pas de gestion de priorité/z-index explicite. Fonctionne car un seul à la fois en pratique.
- Bug cosmétique : dans l'onglet LEAGUES, `navigationIcon = { Text("×") }` au lieu d'une icône Material (`Icons.Default.Close`) — incohérent avec le reste.

## Jugement

Le point d'entrée est **lisible et raisonnablement structuré** pour 480 lignes, mais il commence à servir d'« attrape-tout » : la top bar, la bottom bar, les 5 destinations, 2 overlays, 4 dialogues et le studio audio sont câblés **dans une seule fonction composable**. La DI manuelle tient, le manifest est propre, le support trilingue/RTL est réel. Le pattern d'erreur le plus concret est la **recherche linéaire + fallback silencieux** dans `onSpeak`.

## Actions concrètes
1. Extraire la logique d'armement des alarmes de `MedLinguaApp.onCreate()` vers `NotificationHelper`/`BootReceiver` pour que la classe Application ne contienne que de l'infra.
2. Exposer un état `isSeeding` depuis le ViewModel et afficher un squelette/écran de chargement tant que le seed initial n'est pas terminé (évite l'écran vide au premier lancement).
3. Remplacer `firstOrNull { equals(termEn)||equals(termFr) }` par une `Map<String, Term>` construite une fois (ou un index de la DB) ; **supprimer le fallback `?: uiState.terms.firstOrNull()`** (ne rien prononcer plutôt qu'un terme au hasard).
4. Demander `POST_NOTIFICATIONS` après que l'utilisateur a activé un rappel (contexte), pas au lancement.
5. N'afficher `AppIntroOverlay` qu'au premier lancement (`DataStore`/SharedPreferences `intro_seen`), avec option « revoir » dans le profil. `datastore-preferences` est déjà dans le catalog.
6. Remplacer `Text("×")` par `Icons.Default.Close` dans la TopAppBar des ligues.
7. Évaluer la migration vers Navigation Compose (déjà en dépendance) : cela permettrait le deep-link type-safe et supprimerait le `when` manuel.
