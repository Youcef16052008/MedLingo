# RAPPORT 15/20 — `web-react/` : le front React/Vite/TypeScript

**Partie 15 de l'analyse en 20 volets de MedLingo**
Méthode : lecture fichier par fichier + **exécution réelle** de chaque commande de vérification (build, tests, typecheck, scripts). Toutes les chiffres qui suivent ont été produits pendant cette session, pas estimés.

---

## 0. Exécution réelle (vérifié, pas lu)

| Commande | Résultat |
|---|---|
| `npm run build` (`tsc --noEmit` + `vite build`) | **✓ 65 modules transformés en 1,41 s** — `index.html` 2,38 kB, `index-*.css` 30,35 kB, `index-*.js` 298 kB (gzip 94 kB) |
| `npx tsc --noEmit -p tsconfig.json` | **✓ TSC OK** (0 erreur, `strict: true`) |
| `npx vitest run` | **✓ 12 fichiers de test, 111 tests, 0 échec** (4,42 s) |
| `npm run check:i18n` | **✓ fr/en/ar : 153 clés chacun, 0 manquante, 0 désynchronisée, 0 clé morte** |
| `node scripts/extract.mjs` | (non relancé — README documente la procédure) |

**Le front web compile, type-checke en strict, et sa suite de tests est intégralement verte.**

---

## 1. Carte du dossier (52 fichiers dans `src/`)

```
web-react/
├─ index.html              54 l.   head + bootstrapping RTL avant rendu
├─ package.json            22 l.   zéro dépendance runtime (React 19 est la seule)
├─ vite.config.ts          11 l.   config Vitest (env node)
├─ tsconfig.json           25 l.   strict + noUnusedLocals/Parameters
├─ public/
│  ├─ data/terms.json      4 512 termes, 2,7 Mo
│  ├─ data/exercises.json  44 exercices
│  ├─ manifest.webmanifest PWA (PNG 192/512 + maskable)
│  ├─ sw.js                101 l.  cache shell + données, stale-while-revalidate polices
│  └─ 7 icônes (favicon SVG + PNG + apple-touch + maskable)
├─ scripts/
│  ├─ extract.mjs          seeds Kotlin → JSON (source de vérité : app/)
│  ├─ check-i18n.mjs       clés utilisées ↔ définies (manquantes + mortes)
│  ├─ make_icons.py        mascotte Android → 6 PNG + SVG
│  └─ (e2e dans e2e/)
├─ e2e/
│  ├─ verify.py            parcours complet (5 onglets, 4 types de quiz, RTL, persistance)
│  ├─ full_audit.py        20 fonctions : chaque bouton de chaque écran cliqué
│  └─ check_design.py      styles calculés (palette, parcours, RTL)
└─ src/                    4 547 l. de code + 1 131 l. de tests + 1 201 l. CSS
   ├─ types.ts             Term (16 champs), Exercise (21), ExerciseType (6), Lang
   ├─ App.tsx              Shell : chargement, RTL, rollover ligue, SW, switch de routes
   ├─ main.tsx             createRoot + StrictMode + import styles.css
   ├─ domain/  (9 modules, ~1 050 l.)  sm2, gamification, leagues, lessons, path,
   │                                   progress, chests, trophies, store
   ├─ i18n/    (2 fichiers, 486 l.)    DICT fr/en/ar + useT + applyDocumentLang
   ├─ data/    (2 fichiers)             modules.ts (15) + load.ts (fetch/indexation)
   ├─ lib/     (4 fichiers)             router hash, utils (shuffle/norm/fmtIv), speech, useNow
   ├─ components/ (10, ~860 l.)        TopBar, TabBar, ModuleCard, SessionTop, GoalRing,
   │                                   Medi, RewardPopup, TermSheet, DialogProvider, ToastProvider
   └─ screens/  (8, ~1 870 l.)         Path, Practice, Leagues, Modules, Search, Flash, Quiz, Profile
```

**Total `web-react/` : 3,8 Mo hors `node_modules`/`dist`/`shots`.**

---

## 2. Architecture — un port React de l'application Android

### 2.1 Un seul et unique store, sans bibliothèque

`src/domain/store.ts` (292 l.) est un store maison : `subscribe()` + `useSyncExternalStore`, persisté dans `localStorage` sous la clé `medlingo_web_v1`. Il contient 22 champs : langue, gemmes, série, objectif, XP du jour, congélations, `weeklyXp`, `pendingChests`, `chestsOpened`, `moduleBest`, `lessonBest`, `trophies`, l'état ligue, les compteurs, `sm2` (map id → Sched), `introSeen`, `dataVersion`.

**`revive()` est le morceau le plus soigné du projet** (l. 179-221). Il :
- supprime les clés abandonnées (`hearts`, `heartsTs`, `flashSuccess`) pour ne pas les réécrire ;
- valide chaque enum (`lang` dans `LANGS`, `tier` dans `TIERS`, `dailyGoal` dans `[20,50,100]`) ;
- coerce chaque nombre via `coerceNum` (NaN/Infinity/chaîne → défaut) ;
- coerce chaque map via `coerceNumMap` / `coerceSm2` ;
- **purge `sm2` si `dataVersion` ≠ version courante** (l'historique indexé par id ne veut plus rien dire après renumérotation) ;
- retombe sur les défauts si le JSON est corrompu (`catch`).

C'est une défensive rare : un utilisateur avec un store d'une version précédente ou un JSON cassé ne perd pas l'application.

### 2.2 Horloge injectée partout

Toutes les fonctions de `domain/` reçoivent `now` en paramètre : `sm2Next(quality, prev, now)`, `addXp(s, n, now)`, `touchStreak(s, now)`, `buildPath`, `buildStandings`, `pearlOfDay(terms, now)`. **Aucune fonction métier ne lit `Date.now()`.** C'est ce qui rend les 111 tests déterministes. Les écrans, eux, lisent l'horloge (`useNow()` re-render toutes les 60 s pour les compteurs temps réel).

### 2.3 Routeur hash maison, zéro dépendance

`src/lib/router.ts` (99 l.) écoute `hashchange` + un event custom `medlingo:navigate`. Détail intéressant : `useHashRoute` conserve un compteur `tick` parce que **`navigate()` vers la route courante n'émet pas `hashchange`** (commentaire B24). `QuizScreen` est monté avec `key={route}` (hash brut, query comprise) pour qu'un changement `?module=a → ?module=b` remonte l'écran et relance le démarrage automatique.

`activeTab()` mappe les sous-routes sur les 5 onglets (`search` → Cours, `flash`/`quiz` → Révision, alias historique `home` → Parcours).

### 2.4 Générateur de leçons — le cœur du parcours

`src/domain/lessons.ts` (288 l.) est la pièce la plus complexe. Il résout un vrai problème : **44 exercices réels pour 90 nœuds**. Sa réponse est la génération procédurale :

1. jusqu'à 4 exercices réels du (module, niveau) ;
2. complétion à 8 questions depuis les 4 512 termes, avec un **plan de kinds par niveau** : L1/L2 → `en_fr`/`en_ar`/`fr_en` ; L3/L4 → `def`/`match`/`etym` ; L5 → `reading`/`def` ; L6 → `clinical`/`fr_en` ;
3. difficulté croissante : distracteurs éloignés en début de leçon, proches (similarité de Jaccard sur bigrammes) en fin ;
4. tirage déterministe `mulberry32(hashSeed("module:niveau"))` → la même leçon à chaque visite.

Sécurités : `if (!terms.length) return []`, boucle bornée `gen < needed * 3`, `cursor > pool.length * 2 → break`, `out.slice(0, LESSON_SIZE)`.

**Les 3 tests clés du générateur** (lessons.test.ts) : *8 questions par leçon pour les 90 leçons*, *ids uniques*, *les 90 leçons sont générables*. Ils tournent sur les vraies données JSON (mock de `load.ts` lisant `public/data/`). **J'ai vérifié : 111 tests verts.**

---

## 3. Les données — et leur vraie qualité

### 3.1 Triple duplication des 4 512 termes

| Emplacement | Taille | Rôle |
|---|---|---|
| `app/src/main/java/com/example/data/terms/` (15 fichiers Kotlin) | 3,6 Mo | **source de vérité** (app Android) |
| `web-react/public/data/terms.json` | 2,7 Mo | généré par `npm run extract` |
| `web/data/terms.json` | 2,7 Mo | l'ancien front vanilla (archivé) |

J'ai comparé les deux JSON : **hash MD5 identique** (`1ab261db1…`), ids identiques. `web/` est donc une archive figée. Mais le front React embarque une **copie de 2,7 Mo** qui doit être régénérée à chaque changement des seeds Kotlin — la procédure est documentée, pas automatisée.

### 3.2 Les champs narratifs sont majoritairement vides

C'est la découverte de données la plus importante de cette partie. Sur 4 512 termes :

| Champ | Vide | % |
|---|---|---|
| `mnemo` (moyen mnémotechnique) | 4 359 | **97 %** |
| `etym` (étymologie) | 4 169 | **92 %** |
| `pearl` (perle clinique) | 4 062 | **90 %** |
| `exEn` / `exFr` / `exAr` (exemple) | 3 406 | **75 %** |

Pourtant l'UI affiche ces champs : `TermSheet` filtre les vides (`.filter(([,v]) => v)`), donc l'utilisateur ne voit que la définition + l'exemple. Mais **`pearlOfDay()`** (`PathScreen.tsx:19`) sélectionne la « perle clinique du jour » parmi les termes *ayant* un pearl — soit **450 termes sur 4 512**, et la rotation se fait sur ce sous-ensemble. Pareil pour `etym` : le kind L3/L4 `etym` est généré seulement si `term.etym` est non vide (`lessons.ts:178`), sinon `build()` retourne `null` et le curseur avance.

**Conséquence concrète pour L5/L6** : `reading` (L5) et `clinical` (L6) exigent `exEn || pearl`. Comme 75 % des termes n'ont pas d'exemple, les trois quarts des termes sont écartés et le générateur avance dans le pool. Cela n'empêche pas la génération (les tests le prouvent), mais **les niveaux 5 et 6 sont appauvris en contexte clinique** — alors que c'est précisément leur raison d'être.

### 3.3 44 exercices réels pour 90 nœuds — la couverture réelle

Mesuré par programme :

```
L1: 8 exercices | mcq=6, matching=2
L2: 8 exercices | mcq=6, matching=2
L3: 8 exercices | sentence_order=6, fill_blank=2
L4: 8 exercices | mcq=5, sentence_order=3
L5: 6 exercices | mcq=6
L6: 6 exercices | mcq=6
```

**Couples (module, niveau) couverts : 13 sur 90 (14 %).** Détail des manquants :

- `anat` : L6 · `physio` : L1 · `semio` : L1, L3, L5, L6 · `termino` : L1, L3-L6 · `anapath` : L1-L4
- **10 modules n'ont AUCUN exercice réel** : biochim, histo, biophys, genet, clinical_en, cytol, info_med, embryo, microbio, pharmaco

**Lecture** : pour 77 nœuds sur 90, la leçon est **entièrement générée**. Les exercices réels n'existent que pour Anatomie et Physiologie (plus 2-3 sur Terminologie/Sémiologie/Anapath). Le générateur comble le trou — c'est une vraie réussite technique — mais l'auteur des exercices n'a écrit que pour 2 modules sur 15.

### 3.4 Anomalie de données ponctuelle

L'exercice **id 17** (Anatomie L3, `sentence_order`) propose 9 tokens dont **`the` × 3** (`['is','The','femur','the','longest','bone','in','the','body']`). La question est résolvable (les trois `the` sont interchangeables en valeur), mais l'UI affiche **trois boutons strictement identiques**. La comparaison est volontairement sensible à la casse (documenté dans le README, `web/app.js:937`), donc `The` doit aller en position 0.

### 3.5 8 traductions `fr` dupliquées intra-module

Détectées par programme (la moitié sont des variantes de casse dans le nom EN) :

| Module | `fr` | EN en double |
|---|---|---|
| Physiologie | Loi de Frank-Starling | `Frank-Starling Law of the Heart` / `Frank-Starling law` |
| Biochimie | Glycolyse | `Glycolysis (Embden-Meyerhof-Parnas pathway)` / `Glycolysis` |
| Biochimie | Cycle de Krebs | `Krebs Cycle (Citric Acid Cycle / TCA)` / `Krebs cycle (Citric acid cycle)` |
| Histologie | Basophile | `Basophilic` / `Basophil` |
| Biophysique | Nombre de Reynolds | `Reynolds Number (Re)` / `Reynolds number` |
| Terminologie Médicale | Malformation congénitale | `Congenital malformation` / `Congenital disorder` |
| Anglais Médical | Douleur sourde | `Ache` / `Dull ache` |
| Anglais Médical | Vertige | `Dizziness` / `Vertigo` |

Conséquence : un même concept apparaît deux fois dans la même leçon (le tirage pioche dans le module) et dans les flashcards. Pour `Dizziness`/`Vertigo` ce n'est même pas un doublon de casse mais **deux concepts cliniques distincts**.

---

## 4. Bugs et défauts confirmés

### B1 — CSS mort : 15 classes sur 190 (8 %) `[confirmé, faible]`

Vérifié par programme (chaque classe CSS croisée contre tous les `.ts`/`.tsx`, en excluant les classes construites dynamiquement : `c${i%6}` pour les confettis, `mood-${mood}`, `zone-${zoneOf(rank)}` — celles-là sont vivantes) :

```
.hero  .hero-tag  .due-banner  .quiz-progress  .shake
.vital .vitals  .warm  .mint
.sm2-card  .sm2-dot  .sm2-row  .sm2-sub
.chip-amber  .chip-teal
```

**Lecture** : ce sont les restes d'écrans supprimés. `.vitals`/`.vital`/`.warm`/`.mint` → l'ancien bloc « constantes » du profil, remplacé par `.stat-chip` dans `TopBar`. `.sm2-*` → un ancien écran de révision détaillée. `.hero` → l'ancienne bannière d'accueil. `.shake` → animation d'erreur jamais branchée. `.quiz-progress` → barre de progression de quiz retirée.

**Conséquence** : 30 kB de CSS embarquent environ 8 % de mort. Aucun impact fonctionnel, mais c'est de la dette visible.

### B2 — Bug B1 d'Android **absent** côté web `[confirmé, positif]`

Le rapport 4/20 avait trouvé que le `matching` Android compte toute réponse comme correcte. **Le web n'a pas ce bug** : `QuizScreen.tsx:543-555` fait `if (pair && pair.r === r)` avant de marquer la paire, incrémente `mistakesQ` sinon, et ne déclenche `submit()` que quand `Object.keys(done).length === q.pairs.length`. Le matching web est correct.

### B3 — Bug B2 d'Android **absent** côté web `[confirmé, positif]`

La persona `?: UserStatsEntity()` d'Android n'existe pas ici : le store web part toujours de `makeDefaults(now)` ou de `revive()`, qui valide chaque champ. Pas de chemin « utilisateur inexistant → compteurs corrompus ».

### B4 — `React.StrictMode` + effets de bord : c'est géré `[confirmé, positif]`

`main.tsx` monte `<StrictMode>`. En dev, React 19 double-invoque les updaters. Le code a explicitement anticipé :
- `QuizScreen.tsx:173` : `finishedRef` + `advToRef` anti-double-comptage (commentaires B21/B22 en référence aux bugs du front vanilla) ;
- les `update()` sont appelés **hors** des fonctions de mise à jour d'état (documenté dans le README) ;
- l'effet de démarrage est gardé par `started.current`.

### B5 — `sentence_order` : 3 boutons `the` identiques `[confirmé, faible]`

Voir §3.4. Résolvable, mais confusion visuelle.

### B6 — 7 écrans sur 8 n'ont **aucun** test `[confirmé, moyen]`

| Fichier testé | Couvre |
|---|---|
| `src/domain/__tests__/*` (9 fichiers, ~800 l.) | sm2, gamification, leagues, lessons, path, progress, chests, trophies, store |
| `src/i18n/__tests__/i18n.test.ts` | parité fr/en/ar + intégrité des données |
| `src/lib/__tests__/utils.test.ts` | shuffle, norm |
| `src/screens/__tests__/pearl.test.ts` | perle du jour |

**Aucun test** pour : `FlashScreen`, `QuizScreen`, `LeaguesScreen`, `ModulesScreen`, `PracticeScreen`, `ProfileScreen`, `SearchScreen` — soit **4 547 lignes de code dont la logique de scoring de quiz**, y compris `setupQuestion`, `evalAnswer`, `submit`, `next` (les anti-double-comptages B21/B22 sont *protégés par des refs*, pas *vérifiés par des tests*).

Le filet qui existe est `e2e/full_audit.py` (Playwright) : il clique chaque bouton de chaque écran. **Mais `playwright` n'est pas installé** (`import playwright` → `ImportError`) — bien que `shots/` contienne **132 captures** datées du 5-6 octobre, l'e2e ne peut être rejoué en l'état.

### B7 — `norm()` supprime la ponctuation, `sentence_order` non `[confirmé, comportement volontaire]`

`norm()` retire `.,;:!?«»"'`. `evalAnswer` pour `sentence_order` compare la chaîne jointe **brute** (sensible casse et ponctuation). C'est documenté et voulu (la banque contient `The` et `the`), mais cela crée une asymétrie : un QCM pardonne les accents et la ponctuation, un `sentence_order` ne pardonne rien.

### B8 — Aucune permission runtime / credential `[confirmé, propre]`

`grep` sur `src/` : aucun appel `fetch` vers autre chose que `/data/*.json`, aucune clé API, aucun token, aucun client Supabase. Le front est offline-first par construction. `sw.js` ne met en cache que des actifs locaux + les polices Google (stale-while-revalidate, no-cors).

---

## 5. Parité avec l'application Android

| Règle | Android | web-react | Identique ? |
|---|---|---|---|
| XP par niveau | L1-L6 → 10/15/20/25/30/35 | `10 + (level-1)*5` (testé) | ✅ |
| Gemmes de leçon | `< 50 % → 0`, sinon 10, +10 parfaite | `calculateGemsForLesson` identique | ✅ |
| SM-2 | `SpacedRepetitionAlgorithm.kt` | port commenté « port exact », EF plancher 1.3, iv 1/3/7 puis `iv*ef` | ✅ |
| Seuil de déblocage | 70 % | `UNLOCK_SCORE = 70` | ✅ |
| Cœurs | supprimés côté Android | supprimés côté web (`DROPPED_KEYS`) | ✅ |
| Congélation | 200 gemmes | `FREEZE_COST_GEMS = 200` | ✅ |
| Objectifs | 20/50/100 XP | `DAILY_GOAL_OPTIONS` | ✅ |
| Ligues | bronze/argent/or, bots | `zoneOf(rank)` green/red/neutral | ✅ |
| Trophées | 12 | 12 (mêmes ids) | ✅ |
| Types d'exercices | 6 | 6 | ✅ |
| Bug B1 (matching) | **présent** | **absent** | ❌ divergence |
| Bug B2 (persona) | **présent** | **absent** | ❌ divergence |

**Conclusion** : le web-react est un **port fidèle des règles**, et même **meilleur que l'original sur les deux bugs confirmés** (matching, persona). Le README documente explicitement la parité attendue, ce qui est un point fort : la divergence est traquée.

---

## 6. Ce qui est remarquable

1. **`revive()`** — la migration défensive du store (enums, nombres, maps, purge `sm2`, repli JSON cassé) est un travail de production.
2. **Le générateur de leçons** — transformer 44 exercices en 90 leçons jouables, déterministes, à difficulté croissante, testées sur les 90 — est la réussite technique du dossier.
3. **Horloge injectée partout** → 111 tests déterministes, 0 flaky.
4. **`check-i18n.mjs`** — un vrai gardien CI (clés utilisées ↔ définies ↔ traduites), qui a clairement été exécuté : **153 clés × 3 langues, 0 écart, 0 morte**.
5. **Accessibilité** : `TermSheet` implémente le piège à Tab, le focus initial, Échap, et restaure le focus au démontage. `FlashScreen` a des raccourcis clavier 1-5 et les ignore dans les champs de saisie et pendant qu'une modale est ouverte.
6. **`sw.js`** — stratégies différenciées par type de requête (network-first pour les navigations, cache-first pour les données, SWR pour les polices), et le commentaire explique pourquoi le repli `index.html` ne s'applique jamais aux données (sinon servir du HTML à une requête JSON cassait le hors-ligne).

## 7. Ce qui manque / à faire (spécifique web-react)

| # | Sévérité | Item |
|---|---|---|
| 1 | Moyen | **Aucun test sur 4 547 lignes d'écrans** — notamment `QuizScreen` (scoring, anti-double-comptage). Les refs B21/B22 ne sont pas couvertes. |
| 2 | Moyen | **Playwright non installé** → `npm run e2e` / `audit` / `design` ne peuvent pas tourner. 132 captures existent mais ne sont pas rejouables. |
| 3 | Moyen | **77 nœuds sur 90 sans exercice réel** (10 modules sur 15 n'en ont aucun). Le générateur comble, mais l'auteur n'a écrit que pour 2 modules. |
| 4 | Contenu | **`mnemo` 97 % vide, `etym` 92 %, `pearl` 90 %, `exEn` 75 %** — les champs narratifs qui distinguent MedLingo d'un Anki générique existent à l'état de squelette. |
| 5 | Faible | **15 classes CSS mortes** (~8 % du CSS servi) — restes de `.vitals`, `.sm2-*`, `.hero`, `.shake`. |
| 6 | Faible | **Doublons de traduction intra-module** (8, dont `Dizziness`/`Vertigo` qui sont 2 concepts distincts). |
| 7 | Faible | **`terms.json` dupliqué en 3 endroits** (9 Mo). L'extraction est manuelle : `npm run extract` après chaque changement des seeds Kotlin. |
| 8 | Faible | **id 17** : 3 tokens `the` → 3 boutons identiques. |

---

*Rapport 15/20 — exécuté : `npm run build`, `tsc --noEmit`, `vitest run` (111 tests), `check:i18n`, analyses JSON par programme (doublons, couverture (module,level), champs vides), cross-check CSS↔TSX.*
