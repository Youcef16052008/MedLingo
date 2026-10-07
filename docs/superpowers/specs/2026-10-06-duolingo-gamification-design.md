# Spec — MedLingo « 100 % Duolingo » : parcours, gamification, suppression des cœurs

**Date :** 06 Oct 2026 · **Statut :** approuvée par le produit
**Décideurs :** Youcef (produit) · opencode (implémentation)
**Périmètre :** `web-react/` en Lot 1, `app/` (Android) en Lot 2.

---

## 1. Contexte

L'app ne contient aujourd'hui que des flashcards et un quiz : elle est « vide ». Le document
`ARCHITECTURE_FINALE_100_DUOLINGO.md` décrit une cible (ligues, séries, récompenses, mascotte) mais
une partie n'existe que sur papier, et le système de **cœurs** est à supprimer (décision produit).

Vérifié sur le code (exploration du 06/10) :

- `web-react` : 6 écrans, 5 onglets, store `medlingo_web_v1`, `domain/gamification.ts` = cœurs +
  série ; **aucune** page parcours / ligues / trophées / caisse / objectif quotidien / mascotte.
- `app` (Android) : cœurs réels (`HeartsManager`, `OutOfHeartsDialog`, portes de leçon), ligues
  locales (30/cohort, bots DZ), **pas** de parcours, de trophées, de caisse, de série active, de Medi.
- **44 exercices seulement**, répartis sur 5 modules : `Anatomie [8,6,6,2,3,0]`,
  `Anatomie Pathologique [0,0,0,0,0,5]`, `Physiologie [0,1,2,5,3,1]`, `Sémiologie [0,0,0,1,0,0]`,
  `Terminologie [0,1,0,0,0,0]`. Un parcours 15 × 6 = 90 leçons **ne peut pas** reposer sur les
  exercices seuls → voir §7 (générateur de leçons).

## 2. Décisions produit (validées)

| # | Décision |
|---|---|
| D1 | **5 onglets** : 📍 Parcours · 🏋️ Révision · 🏆 Ligues · 📚 Modules · 👤 Moi (stats + trophées) |
| D2 | Construire : hub de révision multi-modes, animations de récompense, mascotte Medi, série + objectif quotidien, trophées, caisse récompense. **Pas de boutique** |
| D3 | Parcours : **1 unité = 1 module, 6 leçons L1→L6**, déblocage ≥ 70 % |
| D4 | Paywall **Super conservé** (Android) mais **sans aucune mention de cœurs** |
| D5 | Ordre : **`web-react` d'abord** (design identique Duolingo), puis Android |
| D6 | Approche **A** : construire dans l'existant (hash-router, store, i18n, CSS), **zéro dépendance ajoutée** |

## 3. Hors périmètre

Supabase/Birdbrain côté client, boutique/paiement réel, notifications serveur, site `web/` legacy,
refonte du système de révision SM-2, import de nouveaux contenus.

---

## 4. IA & routes (`web-react`)

| Route | Onglet | Écran |
|---|---|---|
| `#/path` (**défaut**) | `path` | `screens/PathScreen.tsx` (nouveau) |
| `#/home` | `path` | alias → `#/path` (liens historiques) |
| `#/practice` | `practice` | `screens/PracticeScreen.tsx` (nouveau) |
| `#/leagues` | `leagues` | `screens/LeaguesScreen.tsx` (nouveau) |
| `#/modules` | `modules` | existant |
| `#/search` | `modules` | existant |
| `#/profile` | `profile` | existant + section trophées/objectif |
| `#/flash/:id`, `#/quiz` | `practice` | existants, sous-étapes de Révision |

Touch points : `App.tsx:124-144` (switch), `TabBar.tsx:7-13` (5 entrées), `router.ts:27-32`
(`activeTab` : `path|practice|leagues` à whitelister, `flash`/`quiz` → `practice`).
`verify.py`/`full_audit.py`/`check_design.py` assertent « exactement 5 onglets » → mis à jour.

## 5. Pages

### 5.1 Parcours (`PathScreen`)
- **Carte d'en-tête** : salutation (`greeting`), mascotte **Medi** 🩺 + bulle contextuelle,
  **anneau d'objectif quotidien** (SVG `stroke-dasharray`) `xpToday / dailyGoal`,
  carte 🔥 série avec compteur de **congélations** et bouton **« Congeler — 200 💎 »**,
  badge **caisse** clignotant si `pendingChests > 0`.
- **Unités** : les 15 `MODULES` dans l'ordre ; bannière = `icon`, `fr|en|ar`, `color`,
  progression `n/6`.
- **Nœuds** : 6 bulles par unité, décalage alterné (`±56px`) en `path` vertical ; états :
  - `locked` : 🔒, gris `#E5E7EB`, non cliquable, tooltip « 70 % requis » ;
  - `available` : verte `#58CC02`, ombre portée 4 px, pulse si c'est la prochaine ;
  - `done` : score `≥70 %` + couronne ⭐ (couleur par palier : 70/80/90).
  - `L6` de chaque unité = nœud **BOSS** (plus gros, icône 🏥).
- Clic nœud → `#/quiz?module=<id>&level=<n>` ; verrouillage via `buildPath()` (§7.3).
- Fin de page : carte « Perle du jour » existante (relocalisée depuis l'ancien Home) si le slot est libre.

### 5.2 Révision (`PracticeScreen`)
4 cartes cliquables + mini-anneau d'objectif + conseil Medi :
1. **🔁 Révision du jour** → `#/flash/all` (badge = `countDue`) ;
2. **🧠 Quiz niveaux** → `#/quiz` (sélecteur de niveau existant) ;
3. **💪 Termes faibles** → `#/flash/weak` (session SM-2 limitée aux cartes dues/échouées) ;
4. **🎓 Examen blanc** → `#/quiz?mode=exam` (mélange des 6 niveaux, 10 questions).

### 5.3 Ligues (`LeaguesScreen`)
- Bandeau : 🏆 `Tier`, semaine (`formatWeekRange`), compte à rebours du reset lundi 00:00.
- Classement 30 lignes trié par `weeklyXp` : **zone verte top 10**, **zone rouge bottom 5**,
  ligne « TOI » surlignée, avatars emoji, XP animé à l'entrée.
- Règles (miroir de l'Android `LeagueManager`) : `COHORT_SIZE=30`, `PROMOTION=10`, `DEMOTION=5`.
- **Bots** : 29 noms DZ (repris de `LeagueManager.kt`) avec XP déterministe (PRNG à graine
  `botSeed` + semaine) → reproductible pour les tests.

### 5.4 Moi (`ProfileScreen` — extension)
- Stats existantes conservées.
- **Grille de trophées** 3×4 : gagnés en couleur + date, verrouillés en gris avec exigence.
- **Objectif quotidien** : segmented 20 / 50 / 100 XP.
- Carte série + congélations, langue, reset.
- Suppression de l'affichage `hearts`.

## 6. Moteur gamifié (`src/domain/`)

### 6.1 `gamification.ts` (réécrit — cœurs supprimés)
```ts
export const DAILY_GOAL_OPTIONS = [20, 50, 100] as const;
export function todayKey(now: Date): string;                    // YYYY-MM-DD (inchangé)
export function addXp(s: AppState, n: number, now: Date): XpOutcome;
  // xp += n ; si goalDate != today → xpToday = n, goalDate = today, touchStreak
  //          sinon xpToday += n ; weeklyXp += n
export function touchStreak(s: AppState, now: Date): StreakOutcome;
  // même jour → inchangé ; lendemain → streak+1 ; écart > 1 j :
  //   freezeCount > 0 → freezeCount-1, streak conservé, freezeUsed = true ; sinon streak = 1
export function goalProgress(s, now): { today, goal, pct, done };
export function buyFreeze(s): boolean;                           // gems >= 200 → gems-200, freezeCount+1
export function loseGems / earnGems  (inchangés, helpers existants)
```
Supprimés : `HEART_MAX`, `HEART_REGEN_MS`, `REFILL_COST_GEMS`, `regenHearts`, `loseHeart`.
Les gemmes restent mutées par les écrans (inchangé) ; nouveaux postes de dépense : congélation.

### 6.2 `trophies.ts` (nouveau)
```ts
export type TrophyId = "first_lesson" | "first_perfect" | "streak_7" | "streak_30"
  | "xp_1000" | "xp_5000" | "cards_100" | "quizzes_10" | "goal_hit_7"
  | "module_master" | "league_promoted" | "chests_5";
export interface Trophy { id; icon; titleKey; descKey; }         // 12, i18n
export function evaluate(s: AppState): TrophyId[];               // nouveaux gagnés
```
Chaque gain est persisté (`trophies[id] = ISO`) et déclenche le pop-up de récompense.

### 6.3 `chests.ts` (nouveau)
```ts
export interface ChestReward { kind: "xp" | "gems" | "freeze"; amount: number; }
export const CHEST_TABLE = [                                     // poids = %
  { kind: "xp",   amount: 20,  weight: 30 },
  { kind: "gems", amount: 30,  weight: 30 },
  { kind: "gems", amount: 50,  weight: 20 },
  { kind: "freeze", amount: 1, weight: 15 },
  { kind: "gems", amount: 100, weight: 5 },
];
export function openChest(rand: () => number): ChestReward;      // rand injecté → testable
export function earnChest(s: AppState, kind: "quiz" | "flash", score: number): boolean;
  // quiz terminé → toujours ; flash → si ≥ 10 cartes revues
```

### 6.4 `path.ts` (nouveau)
```ts
export type NodeState = "locked" | "available" | "done";
export interface PathNode { moduleId; level; state; score; isBoss; }
export interface PathUnit { module: Module; nodes: PathNode[]; completed: number; }
export const UNLOCK_SCORE = 70;
export function buildPath(moduleBest: Record<string, number>): PathUnit[];
  // L1 disponible ; Ln done si best ≥ 70, sinon disponible si L(n-1) done, sinon locked
export function lessonKey(moduleId: string, level: number): string;  // `${moduleId}:${level}`
```

### 6.5 `lessons.ts` (nouveau — voir §7) et `leagues.ts` (nouveau — voir §5.3)

### 6.6 Store (`domain/store.ts`)
`AppState` **ajoute** : `xpToday, goalDate, dailyGoal, freezeCount, weeklyXp, pendingChests,
chestsOpened, moduleBest, trophies, league { tier, weekStart, botSeed }`.
`AppState` **supprime** : `hearts, heartsTs`.
`revive()` : ignore les anciens champs `hearts/heartsTs`, applique les défauts des nouveaux,
**ne purge jamais** `xp/streak/sm2/answered/...`. Clé `medlingo_web_v1` et `TERM_DATA_VERSION`
inchangées (la progression survive à la mise à jour).

## 7. Générateur de leçons (`src/domain/lessons.ts`)

Problème : 44 exercices vs 90 nœuds. Règle :

```
lessonQuestions(moduleId, level, ctx): Question[]  // 8 questions, tirage déterministe (seed)
  1. exercices réels du (module, level)  → tous inclus (max 4)
  2. complétion par questions générées depuis termsOfModule(moduleId) :
     - L1/L2 : QCM « EN → FR », « EN → AR », « FR → EN » (4 distractors proches, même module)
     - L3/L4 : QCM définition/étymologie + appariement (Match) terme ↔ traduction
     - L5    : QCM lecture (terme + contexte) si dispo, sinon type L4
     - L6    : cas clinique réel si dispo, sinon QCM « traduction du terme clinique »
  3. difficulté croissante dans la leçon (distractors d'abord proches puis éloignés)
```
- Alimente **à la fois** l'écran de quiz existant (adapté pour accepter un pool cadré
  `module+level`) et le hub Révision.
- Tests : déterminisme (même seed → même leçon), 8 questions, tous les termes du module,
  aucune réponse en double dans les options, 90 leçons générables.

## 8. Suppression des cœurs

### 8.1 `web-react`
Fichiers : `domain/gamification.ts`, `domain/store.ts` (champs + défauts), `App.tsx` (ticker
`50-65`), `components/TopBar.tsx` (puce ❤️), `screens/QuizScreen.tsx` (perte de cœur `173`,
dialogue hors-cœurs `207-226`, recharge 50 💎, affichage `145/240-242`),
`screens/ProfileScreen.tsx:90`, i18n (`statHearts`, `outHeartsTitle`, `outHeartsText`,
`refillLbl`, …) **supprimées des 3 dictionnaires** (sinon `check:i18n` sort en erreur sur les
orphelines), tests `gamification.test.ts:41-82` remplacés, e2e : `verify.py::top_up_hearts` et le
flux « out of hearts » de `full_audit.py` supprimés.
**Critère** : `grep -ri hearts src/` → 0 occurrence.

### 8.2 Android (Lot 2)
- Code : `HeartsManager`, `GamificationViewModel.onWrongAnswer/canDoLesson`,
  `MedLinguaRepository.loseHeart/refillHeartsWithGems/earnHeartFromPractice`,
  `UiState.currentHearts/timeUntilNextHeart/showOutOfHeartsDialog/showHeartRefillDialog`,
  puce de `QuizScreen`, portes `MainActivity:282/296/345`, `OutOfHeartsDialog` (le dialogue
  `SuperPaywallDialog` reste), clés i18n `hearts/out_of_hearts_*/time_until_next_heart`.
- **DB v9→v10** : reconstruction de `user_stats` sans `hearts/heartsUpdatedAt/maxHearts`
  (`CREATE user_stats_new … INSERT SELECT … DROP … RENAME`), nouvelles colonnes
  `xpToday, goalDate, dailyGoal, freezeCount, pendingChests, weeklyXpReset` + table
  `trophies(id TEXT PRIMARY KEY, earnedAt INTEGER)` — **les données existantes sont conservées**.
- Tests : `LeagueHeartsRegressionTest` réécrit (sans cœurs), nouveau test de migration v10.
- Paywall Super : bénéfices réécrits **sans** cœurs (D4).

## 9. Design « identique Duolingo »

- **Nouvelles surfaces uniquement** (parcours, ligues, récompenses, caisse, trophées) :
  - palette d'accent Duolingo : `--duo-green #58CC02`, `--duo-blue #1CB0F6`,
    `--duo-yellow #FFC800`, `--duo-red #FF4B4B`, `--duo-purple #CE82FF` ;
  - boutons « chunky » : `border-radius 16px`, `border-bottom 4px` plus foncé, `translateY` au
    `:active` (effet pressé) ;
  - bulles de leçon rondes 64-72 px, ombre portée `0 4px 0` ;
  - bannière d'unité pleine largeur, `color` du module, titre blanc ;
  - anneau XP (SVG), pop-up récompense centré avec compteur qui monte + confettis CSS.
- **Écrans existants** : palette actuelle conservée → `check_design.py` (aligné sur Android
  `Color.kt`) reste valide.
- `prefers-reduced-motion` : toutes les animations neutralisées.
- Styles : sections dédiées dans `styles.css` (sections actuelles `:100 … :891`).

## 10. Mascotte Medi

`components/Medi.tsx` : cercle emoji 🩺 stylé (pas d'asset externe) + bulle de dialogue ;
moods `happy | motivating | celebrating | frustrated` ; posée sur l'en-tête du Parcours, le hub
Révision, et les écrans de fin de session. Messages i18n (×3 langues, ~10 clés), ton complice.

## 11. Pop-up de récompense

`components/RewardPopup.tsx` : surcouche `.overlay` existant, déroulé en 3 étapes —
1) compteur `+N XP` qui monte (CSS `@keyframes`), 2) ligne 💎 gemmes / ❄️ congélation gagnées,
3) ouverture de caisse (scale + rotation) si `pendingChests`. Bouton « Continuer ».
Affiché **après** une session (quiz/flash) et à l'ouverture d'un trophée.

## 12. i18n

~70 nouvelles clés (groupes : `nav*`, `path*`, `unit*`, `lesson*`, `practice*`, `league*`,
`trophy_*`, `chest*`, `goal*`, `streak*`, `freeze*`, `reward*`, `medi_*`) — **ajoutées aux 3
dictionnaires** ; `npm run check:i18n` et `i18n.test.ts` doivent passer (les clés mortes
`hearts*` retirées en même temps).

## 13. Tests & critères d'acceptation

**Web (Lot 1) — obligatoires :**
1. `npm run test` vert (nouveaux : gamification (objectif/série/congélation), trophies, chests,
   path, lessons, leagues ; mis à jour : suppression des tests cœur).
2. `npm run check:i18n` vert · `npm run build` vert.
3. `npm run e2e` (`verify.py`) vert : 5 onglets `#/path #/practice #/leagues #/modules #/profile`,
   parcours rendu, leçon lancée puis terminée, récompense affichée, trophées visibles, ligues
   classées, arabe → `dir=rtl` → retour FR.
4. `npm run audit` (`full_audit.py`) vert · `python e2e/check_design.py` vert.
5. `grep -ri hearts src/` → 0.

**Android (Lot 2) — obligatoires :**
6. `assembleDebug` + `testDebugUnitTest` verts ; nouveau test de migration v10.
7. Appareil : migration v9→v10 sans perte (points/série/SM-2 conservés), 5 onglets, parcours,
   fin de leçon → récompense, aucun `FATAL EXCEPTION`.

## 14. Risques & atténuation

| Risque | Atténuation |
|---|---|
| e2e fragiles (assertion « 5 onglets ») | mise à jour atomique de `verify.py` / `audit` / `check_design` dans le même lot |
| Purge du store par erreur | test `store.test.ts` : reprise d'un `AppState` v1 contenant `hearts` → conservé sans eux |
| Contenu vide (44 exos) | générateur de leçons §7 + test « 90 leçons jouables » |
| Surcharge visuelle | animations uniquement sur les nouvelles surfaces, `prefers-reduced-motion` |
| Dérive Android/web | mêmes règles métier (70 %, 30/cohort, top10/bottom5) et mêmes noms de clés i18n |

## 15. Lots

- **Lot 1 — web-react** : (0) socle cœurs↓ + store/domain/routes/i18n, (1) Parcours + leçons +
  récompenses + Medi, (2) Révision + Ligues + Moi/trophées, (3) polish visuel + e2e/audit verts.
- **Lot 2 — Android** : suppression cœurs + migration v10, port des pages (Path/Practice/Leagues/
  Moi+trophées), Medi + pop-up, i18n, vérification appareil.
