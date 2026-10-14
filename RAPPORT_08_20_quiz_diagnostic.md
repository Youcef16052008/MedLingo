# PARTIE 8/20 — QuizScreen, PracticeScreen, PlacementDiagnosticScreen, AdaptivePlacementEngine (CAT)

**Fichiers lus intégralement** : `QuizScreen.kt` (792 lignes), `PracticeScreen.kt`, `AdaptivePlacementEngine.kt` (574 lignes), `PlacementDiagnosticScreen.kt` (36 717 octets, parcouru en grande partie).

## Observations

### `QuizScreen.kt` — 3 modes en un
1. **Pyramide des 6 niveaux** (overview) : hero gradient + 6 cercles (validé vert / débloqué jaune / verrouillé translucide) + cartes `LearningLevel` verrouillées. Conforme à l'esprit Duolingo (on doit passer le niveau pour débloquer).
2. **Session active** : header + barre de progression + `ClinicalContextCard` si vignette + carte question (EN principal + traduction AR/FR en sous-titre) + dispatch par `type` (`sentence_order` → `SentenceOrderExerciseView`, `matching` → `MatchingExerciseView`, `else` → `McqQuestionView`) + bouton « Suivant » animé.
3. **Résultats** : succès si ≥ 70 %, 🏆/⚠️, message, boutons retour/recommencer.

**Bugs et défauts identifiés :**
- **Le matching est triché** : `MatchingExerciseView(..., onComplete = { onSelectOption(currentExercise.correctAnswer, currentExercise.correctAnswer, currentExercise.points) })` — la réponse envoyée au ViewModel est **toujours la réponse correcte**, quoi que l'utilisateur ait fait. L'exercice de matching est donc **toujours compté correct**. C'est un bug fonctionnel certain, qui invalide tout le scoring des 4 exercices matching et fausse le déblocage des niveaux. Cause probable : la signature `onSelectOption(answer, correctAnswer, points)` ne permet pas de passer une liste de paires, et le contournement a été de forcer la bonne réponse.
- **`fill_blank` n'est jamais rendu** : le `when` couvre `sentence_order`, `matching`, `else` (mcq, reading, clinical_case) — mais **`fill_blank` tombe dans `else`** et est rendu en QCM par `McqQuestionView` (ce qui fonctionne car `optionsRaw` contient des distracteurs). Acceptable mais non documenté.
- **`reading` / `clinical_case`** : `contextTextEn.isNotBlank()` affiche bien `ClinicalContextCard`, et le reste passe en QCM. Fonctionne pour les 0 exercices concernés (Partie 5).
- **Double API de callback** : `QuizScreen` définit `onSelectLevelOption`/`onNextLevelQuestion` **et** des alias par défaut (`onSelectOption`, `onNextQuestion`, `onResetQuiz`) qui pointent sur les premiers. Deux noms pour chaque action = dette d'API. `MainActivity` n'en utilise qu'un jeu.
- **État « pool vide » bien géré** : écran 📭 + `Strings.get("no_exercises")` — **c'est la bonne réponse au bug soulevé en Partie 6**. MAIS : cet état n'empêche pas `nextLevelQuestion` de créditer la leçon côté ViewModel. L'écran affiche l'état vide, mais il n'y a **pas de bouton « suivant »**, donc l'utilisateur ne peut **pas** déclencher la complétion depuis l'écran vide. Le bug de leçon vide est donc en réalité **bloqué par l'UI** — bonne nouvelle, je corrige mon hypothèse de la Partie 6 : l'utilisateur ne peut pas enchaîner les leçons vides, il doit faire retour. Cependant `onExitLevel` n'enregistre rien, donc un nœud AVAILABLE sans exercices reste jouable-infiniment-sans-progresser. UX dégradée mais pas d'exploit.
- **Correction 100% côté Vue, 0% côté modèle** : l'écran passe `currentExercise.correctAnswer` au VM (chaîne brute) ; aucun `ExerciseSpec`/`ExerciseChecker` utilisé. Tout le travail de la Partie 4 est invisible ici.

### `PracticeScreen.kt` — propre et petit
4 portes d'entraînement (Révision due / Quiz / Points faibles / Examen blanc) + anneau d'objectif + Medi avec mood dynamique. `Strings.get` partout. **Même bug d'horloge que PathScreen** : `System.currentTimeMillis()` au lieu de l'horloge injectée. `onOpenWeak` et `onOpenExam` sont câblés dans `MainActivity` sur `showFlash = true` / `showQuiz = true` — « Points faibles » ouvre les **flashcards**, « Examen blanc » ouvre le **quiz** : comportement identique aux deux autres portes. Les 4 cartes sont donc 2 comportements. Incohérence UX.

### `AdaptivePlacementEngine.kt` (CAT) — la pièce la plus avancée
- Vrai **computerized adaptive test** : difficulté 1–5, `recordAnswer` ajuste (+1/+2 après 3 bonnes, −1/−2 après 3 mauvaises), `shouldStop` (min 5, max 12, ou arrêt anticipé si plafond/plancher atteint), `calculateResult` avec **score pondéré par difficultité** (somme des poids des bonnes / somme des poids) — c'est une vraie métrique CAT, pas un simple ratio.
- **Bonne réparation visible** : `startTest` réinitialise explicitement (`answers.clear()`, etc.) — l'engine est un **stateful singleton implicite** (`class` instanciée par l'écran) qui sinon accumulerait entre deux tests.
- 6 niveaux `KnowledgeLevel` avec recommandation de module de départ. Mapping intéressant et pédagogiquement cohérent.
- `QuestionBank` : banne de questions **hardcodée** (dans le fichier) avec options en **français** uniquement (la question est trilingue, les options sont FR). Cohérent avec un public cible francophone, mais « Anglais médical » est l'objectif — des options en anglais seraient plus pertinentes pour un *placement en anglais médical*.
- `UserType` / `MedYear` : 4 profils × 9 années, tous libellés AR/FR/EN. Beau travail d'onboarding.
- **Défaut** : `getInitialDifficulty` est dérivé du profil auto-déclaré — or l'objectif d'un CAT est précisément de **mesurer** plutôt que de présupposer. La graine est cohérente (un résident démarre à 4), mais le commentaire sur `message` (« Nous commençons par… ») est 100% **français hardcodé**, non internationalisé, alors que `UserType`/`MedYear` le sont. Incohérence i18n.
- `PlacementResult.specialty` est collecté (l'écran demande) mais **jamais utilisé** dans `calculateResult`.
- **Non-persistance** (Partie 6) : le résultat est appliqué au filtre + XP, mais `userProfileName`/`level`/`medYear`/`specialty` ne sont pas sauvegardés. Au redémarrage, tout est perdu.

### Comparaison à Duolingo
Duolingo a supprimé son test de placement public ; MedLingo en propose un, **adaptatif**, avec orientation de module — c'est **au-dessus de ce que fait Duolingo** sur ce point. La pyramide 6 niveaux est une transposition pédagogique (Vocabulaire → Cas cliniques) plutôt qu'une copie de la structure unit/section de Duolingo, c'est un bon choix pour un public universitaire.

## Jugement

Le quiz est **visuellement complet et structurellement sain**, avec un vrai souci UX (état pool vide, traduction sous-titre, barre de progression). Deux défauts majeurs : (1) **le matching est toujours compté correct** — bug de scoring certain, (2) **tout le pipeline `ExerciseSpec` est ignoré**, la UI parle encore le langage legacy chaîne-par-chaîne. Le CAT est le diamant du projet : adaptatif, pondéré, orientant.

## Actions concrètes
1. **Corriger `MatchingExerciseView.onComplete`** : retourner les paires construites par l'utilisateur, les comparer via `ExerciseChecker.checkMatch` (ou au minimum comparer les paires au `correctAnswer` formaté) et passer `correct/incorrect` au VM. C'est le bug de scoring le plus grave de l'app.
2. **Brancher `ExerciseSpec` + `ExerciseChecker` dans `LevelExerciseSessionView`** : `entity.toExerciseSpec()` puis `when (spec) { is Choice -> McqSpecView ; is Wordbank -> ... ; is Match -> ... ; is Fill -> ... }` et corriger via `ExerciseChecker`. Cela active : score partiel wordbank/match, normalisation AR, fuzzy fill, et supprime le `correctAnswer` brut des signatures.
3. Fusionner les deux jeux de callbacks (`onSelectLevelOption`/`onSelectOption`).
4. Brancher « Points faibles » et « Examen blanc » sur des comportements réels (points faibles = cartes avec `lastQuality < 3` ; examen blanc = quiz sur un module complet chronométré) — sinon supprimer les deux cartes.
5. **Internationaliser `KnowledgeLevel` messages** (6 messages français hardcodés dans `calculateResult`).
6. **Persister `PlacementResult`** (profil, année, spécialité) — colonne `user_stats` ou DataStore.
7. Utiliser `specialty` ou le supprimer de l'écran de diagnostic.
8. Remplacer `System.currentTimeMillis()` par `repository.currentClock` dans `PracticeScreen`/`PathScreen` (exposer via `UiState`).
9. Faire passer la `QuestionBank` du CAT en anglais (options EN) — c'est un placement en **anglais** médical.
