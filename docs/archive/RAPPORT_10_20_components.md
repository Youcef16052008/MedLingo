# PARTIE 10/20 — Composants UI (16 fichiers de `ui/components/`)

**Fichiers lus intégralement** : `AppIntroOverlay.kt`, `Medi.kt`, `GoalRing.kt`, `RatingButtons.kt`, `HeartsGemsTopBar.kt`, `ExerciseComponents.kt` (626 l.), `RewardPopup.kt` (222 l.), `SuperPaywallDialog.kt` (153 l.), `LeagueScreen.kt` (289 l.), plus `LanguageSelector.kt`, `TermIllustrationView.kt`, thème (`Color.kt`, `Theme.kt`, `Type.kt`).

## Observations

### Qualités
- **Discipline de performance documentée à plusieurs endroits** : `RewardPopup` (« confettis fixes : une transition infinie ici recomposait la pop-up entière et provoquait des ANR »), `PathScreen` (pulse retiré pour ANR), `MatchingExerciseView` (état **keyed by exercise** car « unkeyed state leaked previous pairs / selections into the new exercise »). C'est rare et précieux : chaque hack de perf est expliqué, donc conservé.
- `MatchingExerciseView` : **vraie logique de matching** — sélection colonne A → colonne B, `pairs.any { it.first == first && it.second == second }`, flash d'erreur 900 ms, désactivation des appariés, et règle pédagogique intéressante : « Réussite = toutes les paires appariées, même après une erreur corrigée : un faux départ ne doit plus faire échouer l'exercice ». **C'est exactement l'inverse de Duolingo** (qui compte les erreurs), mais c'est un choix défendable pour de la révision. Problème : cet état interne **n'est pas communiqué au ViewModel** → `onComplete()` est appelé sans aucune information sur les erreurs commises, et `QuizScreen` appelle alors `onSelectOption(currentExercise.correctAnswer, currentExercise.correctAnswer, points)` (Partie 8). Donc la logique existe, mais **le score est toujours correct**. Le bug est à la frontière Composant→Écran→VM.
- `GoalRing` : Canvas propre (inset du stroke, `coerceIn(0,100)`, couleurs Duolingo exactes `0xFF58CC02` / `0xFFFFC800`).
- `HeartsGemsTopBar` : la suppression des cœurs est **assumée dans un commentaire KDoc** — bonne cohérence avec la migration v10. `leagueTier.take(4)` affiche « Bron » / « Diam » — **bug cosmétique certain** : « Diamond » devient « Diam », « Bronze » devient « Bron », « Emerald » devient « Emer » (indices hors table). La liste `LeagueTier.entries` est juste à côté ; il faut `tier.take(4)` **sur le nom court** ou un champ `shortName`.
- `AppIntroOverlay` : 1,5 s + fade 350 ms à **chaque cold start** (Partie 2). La sélection d'artwork selon le thème est soignée.
- `RewardPopup` : compteur XP animé avec `step = xp/12` (toujours ≥ 1, donc l'animation termine en ≤ 12 étapes — pas de boucle infinie si xp = 0, protégé par un `return@LaunchedEffect`).
- `SuperPaywallDialog` : pricing dérivé de `GemsManager.getSuperPricing()` (single source of truth), % d'économie annuelle **calculé**. Le sous-titre « Comme Tinder Plus, mais pour devenir bilingue médical » et 100 % des textes sont **hardcodés en français** — paywall non internationalisé, alors qu'il vise un produit trilingue.
- `LeagueScreen` : header avec `memberCount.takeIf { it > 0 }` pour éviter le flash « 0 members » — belle attention. `members.sortedByDescending { it.weeklyXp }` **dans la lambda de `itemsIndexed`** → re-tri à chaque recomposition (30 éléments, négligeable mais à sortir).
- `McqQuestionView` : feedback visuel complet (bon en vert, faux en rouge, désactivé après réponse) avec `testTag("quiz_option_$index")` — testable.

### Défauts
1. **`MatchingExerciseView` ne remonte jamais les erreurs** (voir ci-dessus) — le composant sait, l'écran ment.
2. **`HeartsGemsTopBar.leagueTier.take(4)`** — troncature affreuse des noms de ligues.
3. **`SuperPaywallDialog` non i18n** + mention « Tinder Plus » (marketing non traduit).
4. **`RatingButtons` envoie 1..5** — cohérent avec SM-2 (`quality in 1..5`). OK.
5. `TermIllustrationView` : ne dispose que de **2 illustrations** (fémur, neurone) pour 4 512 termes. Le composant est prêt mais le contenu visuel est quasi absent (voir Partie 11 : stratégie images).
6. `LeagueScreen` : `Text("Semaine: ...")` utilise `Strings.get` ? Non — « Top 10 → Promotion », « Bottom 5 → Relégation », « Semaine: » sont **hardcodés en français**.
7. `Medi` : 4 moods seulement (`MediMood`), alors que l'architecture promet « 7 moods » et que `MascotteMedi.kt` dans `.kilo` en a plus. Bulle en `Color(0xFF1E293B)` **sombre même en thème clair** — incohérent.
8. `AppIntroOverlay` : `contentDescription = null` sur une image qui occupe tout l'écran — acceptable pour un splash décoratif.
9. `LanguageSelector` : exposé en haut de `HomeScreen` (écran mort, Partie 7) — le sélecteur de langue n'est donc peut-être **pas accessible** dans la navigation courante. À vérifier dans `MainActivity` : à part `LocalLayoutDirection`, je n'ai pas vu de `LanguageSelector` câblé sur les 5 onglets. **Si confirmé, l'utilisateur ne peut pas changer de langue** — gros problème pour une app trilingue.

## Actions concrètes
1. Faire remonter le compte d'erreurs de matching : `onComplete(errors: Int)` → `QuizScreen` → `selectLevelAnswer` ; appliquer une pénalité de gems/XP proportionnelle.
2. **Corriger `leagueTier.take(4)`** → `LeagueTier` short name.
3. **Vérifier l'accessibilité du `LanguageSelector`** sur les écrans vivants ; l'ajouter dans le Profile si absent.
4. Internationaliser `SuperPaywallDialog`, `LeagueScreen` (zones promo/relégation, « Semaine: »), messages d'erreur du matching.
5. Ajouter un `light`/`dark` aware à la bulle de `Medi`.
6. Sortir `sortedByDescending` de `itemsIndexed`.
7. Compléter les illustrations (2 sur 4512 termes) — voir la stratégie d'images (Partie 11).
