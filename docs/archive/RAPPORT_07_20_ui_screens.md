# PARTIE 7/20 — UI screens : HomeScreen (1076 l.), ModulesScreen (940 l.), PathScreen (338 l.)

**Fichiers lus** : les 3 écrans en intégralité (HomeScreen ~30 000 caractères, ModulesScreen ~44 000, PathScreen entièrement).

## Observations

### `PathScreen.kt` — le plus propre des trois
- Rendu fidèle du parcours Duolingo : 15 unités × 6 nœuds en **zigzag** (`padding(start = if ((level-1) % 2 == 0) 0.dp else 56.dp)`), nœud BOSS 👑, couleurs dorées selon le score (≥90 or, ≥80 argent, sinon vert Duolingo `0xFF58CC02`), verrouillé `🔒` + popup « 70 % requis ».
- **Commentaire d'ingénierie très intéressant** : l'auteur a **supprimé une animation continue (pulse)** qui provoquait un **ANR sur émulateur** — 90 nœuds animés en permanence mettaient le renderer en défaillance. Le nœud suivant est mis en avant uniquement par couleur. C'est une vraie leçon de perf Compose, documentée.
- `LessonNode` est extrait dans **sa propre fonction composable** — ce qui ne crée pas de scope de recomposition indépendante par nœud (seuls les paramètres stables aident), mais la stabilité vient du `key` et de l'immuabilité de `PathNode`. Correct.
- En-tête : mascotte **Medi** (mood dérivé de l'objectif/série), anneau d'objectif (`GoalRing`), pills série 🔥 et freeze ❄️, bouton d'achat de congélation (désactivé si `gems < 200`), caisse en attente cliquable.
- **Bug d'horloge** : `val now = System.currentTimeMillis()` pour `goalProgress(stats, now)` — alors que le repository expose `currentClock` **exactement pour ça** (voir le commentaire de `SpacedRepetitionAlgorithm.isDue` et `HomeScreen` qui lui utilise `uiState.dueFlashcardProgress` précalculé sur l'horloge injectée). Ici le butoir d'objectif se recalcule avec l'horloge murale : en test, avec une `FakeClock`, le PathScreen affichera l'objectif du jour réel. Incohérence testable.

### `HomeScreen.kt` — 1076 lignes, riche mais non utilisé dans la navigation courante
- Belle UI « Figma Medical » : badge hôpital, bannière héro (`img_medical_faculty` + gradient), pill online/offline, sélecteur trilingue, cartes modules, **clinical pearl card**, carte pyramme des 6 niveaux, boutons SM-2 / test de placement.
- `HomeScreen` **n'est pas référencé par `MainActivity`** (les onglets sont PATH / PRACTICE / LEAGUES / MODULES / PROFILE). C'est donc un **écran mort, conservé dans le repo** (il est probablement utilisé par la version `.kilo` ou servait avant le redesign en `PathScreen`). À vérifier avant suppression — mais 1076 lignes d'UI non atteignables, c'est de la dette.
- Sont **polis** : `dueFlashcards` vient de `uiState.dueFlashcardProgress` (commentaire : « the card and the tab badge used to show a hardcoded 6 »), `masteredByModule` dérivé des vraies révisions (`lastQuality >= 3`) avec commentaire expliquant que `ModuleInfo.progress` est statique à `0f` et n'a jamais bougé. L'auteur a **explicitement identifié et contourné** ces mensonges de données.
- `pearlTerm` utilise `uiState.terms.firstOrNull { it.clinicalPearl.isNotBlank() && it.mnemonic.isNotBlank() } ?: uiState.terms.firstOrNull()` — même fallback discutable qu'au `MainActivity` (premier terme quelconque).
- **Textes codés en dur** : « MedLingua DZ 🩺 », « Algorithme SM-2 Actif », « TEST DE PLACEMENT ADAPTATIF », « FACULTÉ DE MÉDECINE DZ • 2026 », « Modules PCEM1 🫀 », « Figma Med UI ✨ »… alors que `Strings.get` existe et contient 540+ clés. Un écran entier non internationalisé. La logique est `if (lang == Language.ARABIC) "…" else "…"` — bilingue AR+FR mais **pas d'anglais** sur ces textes.
- 72 couleurs hex littérales — aucun usage du `Color.kt` du thème (une seule référence à `MedGreenPrimary`).

### `ModulesScreen.kt` — 940 lignes, deux vues en une
- Segmented control : **Catalogue des modules** (cards) / **Lexique des termes** (LazyColumn + recherche + chips). Pratique Duolingo-like.
- **Duplication de données** : `modulesList` est **recodé en dur** dans l'écran (16 paires `"Anatomie" to "Anatomie 🦴"`…) en plus de `InitialData.modulesList`. Si un module est ajouté au seed, l'écran ne le montre pas. Et les libellés divergent déjà (« Terminologie 📙 » vs « 📙 Terminologie Médicale », « Anatomie Patho 🫀 » vs « Anatomie Pathologique 🫀 »). À dériver de `InitialData.modulesList`.
- **Bonne réparation** : les chips de chapitre sont **dérivés du seed** via `InitialData.chaptersOfModule(...)` — le commentaire indique que les listes hardcodées avaient dérivé (« Anatomie 4/11, Génétique 4/19… »). Excellent.
- **Comptage par chip O(n) à chaque recomposition** : `uiState.allTerms.count { ModuleFilter.matchesModule(...) }` pour chaque chip → 16 × 4512 = ~72 000 comparaisons à chaque recomposition de l'écran. À précalculer (`remember(uiState.allTerms) { groupBy }`) comme c'est fait pour `masteredByModule`.
- Recherche : `OutlinedTextField` branché sur `onSearchQueryChange` → `filterTerms()` synchrone (Partie 6). Le champ `singleLine = true`, pas de `imeAction`, pas de debounce.
- `LazyColumn` avec `items(uiState.terms, key = { it.id })` — bonne pratique (clé stable).
- État vide géré (« Vérifiez vos filtres… » + bouton réinitialiser) — bien.
- i18n : 3 textes français hardcodés (« Figma Med UI ✨ », « Réinitialiser les filtres ↺ », « Consulter le module ($termsCount termes) »), le reste via `Strings.get` mais beaucoup de `if (lang == ARABIC) "…" else "…"` (bilingue FR/AR sans anglais).

### Comparaison à Duolingo
| Aspect | MedLingo | Duolingo |
|---|---|---|
| Onglet principal | Parcours (zigzag, unités) | Learn path (zigzag, unités) ✅ |
| Progression du nœud | % affiché, or/argent/vert | Couronnes, XP par leçon |
| Feedback mascotte | Medi (mood basé sur l'objectif) | Duo + bulles contextuelles ✅ |
| Recherche lexicale | Oui (chips module/chapitre + texte) | Oui (dictionnaire) ✅ |
| Difficulté visible | — | Niveau du nœud, barre de difficulté |

## Jugement

Les écrans sont **visuellement soignés et structurellement conformes à Duolingo**, avec un vrai souci de perf (suppression du pulse ANR) et de justesse des données (`dueFlashcards`, `chaptersOfModule`, `masteredByModule`). Trois défauts systémiques : (1) **HomeScreen de 1076 lignes est mort/non navigable**, (2) **duplication des modules** entre `InitialData` et `ModulesScreen`, (3) **i18n incomplète** — beaucoup de `if ARABIC else FRANÇAIS` sans anglais, et des textes hardcodés.

## Actions concrètes
1. Vérifier si `HomeScreen` est référencé quelque part (tests instrumentés compris) ; sinon le supprimer ou le fusionner dans `PathScreen` (1076 lignes de dead UI).
2. **Dériver `modulesList` de `InitialData.modulesList`** dans `ModulesScreen` (supprimer la duplication hardcodée).
3. Précalculer les comptes par module dans un `remember(uiState.allTerms) { terms.groupingBy { it.module }.eachCount() }`.
4. Brancher `PathScreen.now` sur `repository.currentClock` (exposer via `UiState` ou passer la `Clock` à l'écran) pour que l'objectif quotidien soit cohérent en test.
5. Compléter l'i18n : remplacer les `if (lang == ARABIC) "…" else "…"` par des entrées `Strings.get` trilingues (AR/FR/EN) — un script peut les détecter (regex `if \(lang == Language.ARABIC\)`).
6. Supprimer le fallback `?: uiState.terms.firstOrNull()` pour la pearl du jour (afficher un état « aucune pearl »).
7. Déplacer les couleurs hex littérales dans `ui/theme/Color.kt` (il existe déjà) — 72 littérales sur HomeScreen.
