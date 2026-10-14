# Rapport de parité visuelle web ↔ Android — MedLingo

Date : 13 octobre 2026
Branche : `phase-0-cleanup`
Android = source de vérité (`app/src/main/java/com/aistudio/medlingua/dzmed/`)

> **Mise à jour 14 octobre 2026 — design system rafraîchi.** Ce rapport documente
> la parité *structurelle* (écrans, composants, structure des cartes) réalisée
> ce jour-là. Les **jetons visuels** ont depuis été modernisés : le web porte
> son propre design system « Clinical Calm » (palette médicale fraîche, Inter,
> canvas menthe, rayons modernes — voir [web-react/DESIGN.md](web-react/DESIGN.md)),
> inspiré des systèmes santé modernes et validé WCAG AA. Les valeurs de tokens
> citées ci-dessous (fonds, verts) sont donc historiques ; la validation
> `check_design.py` suit désormais le nouveau système.

## Méthode

1. Extraction des **computed styles** de l'app web live (Playwright, viewport
   390×844) sur les 7 routes : `/`, `#/practice`, `#/leagues`, `#/modules`,
   `#/profile`, `#/quiz`, `#/search`, `#/flash/<module>`.
2. Lecture des écrans Android (`.kt`) pour chaque écran : couleurs, rayons,
   tailles de police, poids, paddings, structure de composants.
3. Correction de chaque écart trouvé.
4. Validation : `tsc --noEmit`, `vitest run` (127 tests), `e2e/check_design.py`
   (35 vérifications de computed styles).

## Écrans alignés (6 commits)

| Écran | Fichier Android de référence | Commit |
|---|---|---|
| Top bar | `GemsLeagueTopBar.kt` | `b3f0b86` |
| Bottom bar | `MainActivity.kt` | `b3f0b86` |
| Parcours | `PathScreen.kt` | `b3f0b86` |
| Révision | `PracticeScreen.kt` | `b3f0b86` |
| Modules | `ModulesScreen.kt` (`FigmaModuleDetailedCard`) | `9ae513a` |
| Profil | `StatsProfileScreen.kt` | `cdec490` |
| Ligues | `LeagueScreen.kt` | `51af599` |
| Quiz | `QuizScreen.kt` (`LearningPyramidOverviewScreen`) | `68fef70` |
| Flashcards | `FlashcardsScreen.kt` + `FlashcardCard3D.kt` + `RatingButtons.kt` | `81d9b52` |

## Détail des corrections

### Tokens globaux
- Fond de page : `#F8FAFC` → **`#F6F8F6`** (`SurfaceSubtle` de `Theme.kt`,
  utilisé par les 5 écrans Android principaux).
- Onglets : emojis → **Material Symbols Rounded** (`map`, `fitness_center`,
  `emoji_events`, `menu_book`, `person`), FILL 0 au repos / FILL 1 sur l'actif,
  couleur active `MedGreenDark` `#0D532C`.
- Badge de cartes dues : `#C62828` → **`#E65100`** (Android).

### Top bar (`GemsLeagueTopBar.kt`)
- Puces série/gemmes/ligue : fond blanc + texte noir → couleurs Android
  (`#FFF7ED`/`#EA580C`, `#EFF6FF`/`#2563EB`, `#FEF3C7`/`#D97706`).
- **Puice ligue ajoutée** (🎯 tier + XP hebdo) — absente du web, cliquable →
  onglet Ligues.

### Parcours (`PathScreen.kt`)
- StreakPill : jaune Duolingo → `#FFF7ED`/`#EA580C`, rayon 20.
- Caisse : bouton pointillé animé → `Text` plein `#FFF3C4` rayon 12.
- Titre de section : 26.4px/600 → **22px/800 `#1E293B`**.

### Modules (`FigmaModuleDetailedCard`)
- Grille 2 colonnes → **liste pleine largeur**.
- Carte compacte → carte détaillée : icône 50px/16, titre 16px/700 +
  sous-titre `TitleEn • N chapitres`, badge `N termes` coloré, **chips de
  chapitres ajoutés** (312 chips au total, dérivés des données réelles),
  piste 6px/3px sans bordure.

### Profil (`StatsProfileScreen.kt`)
- **Carte profil ajoutée** : gradient `#1B5E20`→`#2E7D32`, avatar cercle 64px,
  nom 20px, type+année `#A5F4AC`, niveau 11px blanc/90. Defaults du
  `MedLinguaViewModel` (Dr. Youcef, MED_STUDENT, YEAR_1, INTERMEDIATE).
- **Carte objectif ajoutée** : `GoalRing` 56px + `today/goal XP` + série
  `#EA580C` + bouton congélation pilule `#E0F2FE`/`#0284C7`.
- **Grille 12 trophées refaite** : 3 colonnes → **4 colonnes**, cercles 46px
  (`#FFF3C4` gagné / `#F1F5F9` + alpha 0.35 verrouillé), icône réelle
  toujours affichée (avant 🔒), libellé 9sp, compteur `N/12` `#B45309`.
- **Rapport d'apprentissage ajouté** : `MetricBox` 2×2 (`#F6F8F6`/`#E0E5E0`,
  rayon 14, valeur 18px `#0D532C`).
- Ancienne `stat-row` + 6 `setting-row` supprimés.

### Ligues (`LeagueScreen.kt`)
- **`LeagueHeader` ajouté** : carte rayon 20 teintée à 10% de la couleur du
  palier (`LeagueTier.colorHex`), icône cercle 48px, titre 16px/800, badge.
- **Carte zones ajoutée** : `#F8FAFC`, `🏆 Top 10 → Promotion` `#16A34A` /
  `📉 Bottom 5 → Relégation` `#DC2626` + semaine + compte à rebours.
- **`LeagueMemberRow` refait** : rang cercle 28px (médailles or/argent/bronze
  pour les 3 premiers), avatar cercle 36px, pseudo 13px/700 + tag `TOI` 8px
  `#10B981`, sous-ligne `🔥 Nj • Bot/Actif`, XP 14px/800 colorée selon zone +
  `↑ Promotion` / `↓ Relégation`. `isCurrentUser` prioritaire sur la zone
  (`#ECFDF5` + bordure `#10B981`).

### Quiz (`LearningPyramidOverviewScreen.kt`)
- **Carte pyramide ajoutée** : gradient `#0D532C`→`#1B5E20`→`#2E7D32`,
  titre 18px/800, description, bande des 6 niveaux (cercle 36px :
  `#4CAF50` si ≥70%, `#FFD54F` si débloqué, blanc/20 sinon).
- **6 `LevelCard` ajoutées** : rayon 18, bordure 1.5px de la couleur du
  niveau, icône 44px/12, titre + objectif, statut (`Validé ≥70%` / `70%
  requis` / `🔒 Verrouillé`), bouton `▶ S'entraîner`.
- **Déblocage en cascade ajouté** : miroir de `UserStatsEntity`
  (`UNLOCK_THRESHOLD = 70`). Les scores des quiz libres sont persistés sous
  `lessonBest['quiz:N']`.
- 14 clés i18n ajoutées (FR/EN/AR).

### Flashcards (`FlashcardCard3D.kt`)
- **Recto/verso inversés** : le web affichait EN blanc au recto et FR vert au
  verso. Android : recto FR gradient vert, verso EN blanc bordure verte.
- Recto : pilule `module • chapter`, signet 36px, terme FR 32px/800, AR
  20px `#A5F4AC`, pilule retourner.
- Verso : drapeau 🇬🇧 + EN 22px/800 `#0D532C` + IPA + prononcer 42px,
  séparateur, croisé trilingue, boîtes étymologie / perle clinique /
  mnémo (couleurs Android exactes), définition EN.
- Hauteur fixe **410px** + défilement interne (410.dp Android).
- **`RatingButtons` refaites** : pastilles pleines → emoji 24px dans boîte
  bordée 1.5px, couleur à 12% en fond, libellé 10px.
  Couleurs : `#D32F2F`, `#F57C00`, `#FFA000`, `#689F38`, `#2E7D32`.

### Correction de bug (bonus)
`routeName(route).split('/')` supprimait toujours le paramètre de route :
`#/flash/anat` devenait `param=undefined` → « Rien à réviser ici »
systématique. Le paramètre se lit maintenant sur le hash brut.

## Fonctionnalités Android non portées (web-only ou absent)

| Fonction | Statut |
|---|---|
| Placement diagnostic (`PlacementDiagnosticScreen`) | Non portée — aucune route web, aucun équivalent de `diagnosticResult` dans le store web |
| Gestion hors-ligne (`OfflineManagerCard`) | Non applicable — le web charge `/data/terms.json` à la volée, pas de téléchargement par module |
| Notifications (`NotificationsCard` + 3 switches + 3 horaires + 4 boutons de test) | Non applicable — pas d'API de notification web |
| Boutons radio d'objectif quotidien | Android n'a pas de sélecteur d'objectif sur le profil ; le web garde son segmented control (conscient de l'écart) |
| Écran Recherche | Web-only (pas d'équivalent Android) — laissé tel quel |

## Validation finale

| Vérification | Résultat |
|---|---|
| `tsc --noEmit` | 0 erreur |
| `vitest run` | 13 fichiers, **127 tests, 0 échec** |
| `e2e/check_design.py` | **35/35 OK, 0 échec** — TOUT EST CONFORME |
| Computed styles (Playwright 390×844) | Fond `#F6F8F6` sur les 7 routes, 5 onglets `#0D532C`, pas d'overflow horizontal |
