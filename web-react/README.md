# MedLingo DZ — version web (React + Vite)

Port React/TypeScript de l'application MedLingo DZ : 4 512 flashcards SM‑2 et
44 exercices (6 types de questions) pour l'anglais médical, en français,
anglais et arabe (RTL), utilisable directement dans un navigateur.

## Démarrage

```bash
npm install
npm run dev        # http://localhost:5173
```

## Commandes

| Commande            | Rôle                                                            |
| ------------------- | --------------------------------------------------------------- |
| `npm run dev`       | serveur de développement (Vite)                                  |
| `npm run build`     | `tsc --noEmit` puis build de production dans `dist/`             |
| `npm run preview`   | sert le build de production (`http://localhost:4173`)            |
| `npm test`          | tests unitaires (Vitest)                                         |
| `npm run check:i18n`| vérifie les clés i18n fr/en/ar et repère les clés mortes  |
| `npm run extract`   | régénère `public/data/*.json` depuis les seeds Kotlin           |
| `npm run icons`     | régénère les 6 PNG + `icon.svg` depuis la mascotte Android      |
| `npm run e2e`       | parcours navigateur complet (nécessite un serveur, voir plus bas)|
| `npm run audit`     | audit bouton par bouton + actifs + cas limites (idem serveur)   |
| `npm run design`    | contrôle des styles calculés (palette Duolingo, parcours, RTL)  |

## Données

Les données ne sont **pas** écrites à la main : elles sortent des seeds Kotlin
du projet Android.

```bash
npm run extract
```

Le script lit `app/src/main/java/com/example/data/initial/*.kt`, parse les
constructeurs `MedicalTermEntity(...)` et `ExerciseEntity(...)`, et écrit :

- `public/data/terms.json` — 4 512 termes, 15 modules
- `public/data/exercises.json` — 44 exercices

`src/data/load.ts` les charge à l'amorçage, puis les indexe par module.
Un test d'intégrité (`src/i18n/__tests__/i18n.test.ts`) bloque si les comptes
ou les modules divergent.

## Architecture

```
src/
  types.ts               Term, Exercise, ExerciseType (6 types), Lang
  domain/
    sm2.ts               répétition espacée — sm2Next(quality, prev, now)
    gamification.ts       série 🔥, gemmes, XP, congélations — horloge injectée
    store.ts              état global + localStorage (clé medlingo_web_v1)
    progress.ts           due/progression par module, « reprendre »
  i18n/                   DICT { fr, en, ar }, useT(), direction RTL
  data/                   modules (15), chargement/indirection des JSON
  lib/                    router par hash, shuffle/normalisation, synthèse vocale
  components/             TopBar, TabBar, ModuleCard, SessionTop, dialog, toast, fiche terme
  screens/                Accueil, Cours, Recherche, Flashcards, Quiz, Profil
```

Choix structurants :

- **Aucune dépendance de routing** : `src/lib/router.ts` écoute `hashchange`
  et rend l'écran correspondant (`#/home`, `#/modules`, `#/search`,
  `#/flash/:id`, `#/quiz`, `#/profile`).
- **État** : un store externe (`subscribe` + `useSyncExternalStore`) persisté
  dans `localStorage`, clé `medlingo_web_v1` — compatible avec la version
  vanilla `web/`.
- **Horloge injectée** : `sm2Next`, `addXp`, `touchStreak` prennent
  `now` en argument. Aucune lecture de `Date.now()` dans la logique métier,
  ce qui rend les règles testables.
- **Effets React** : les mutations `update()` sont toujours appelées *hors*
  des fonctions de mise à jour d'état (React 19 StrictMode double-invoque les
  updaters en développement).

## Parité avec `web/` (vanilla) et Android

La version React est un port de `web/app.js`, à laquelle elle doit rester
identique sur les points de notation :

| Type             | Règle de validation                                    |
| ---------------- | ------------------------------------------------------ |
| `mcq` / `reading` / `clinical_case` | `norm(choix) === norm(answer)`          |
| `fill_blank`     | `norm(saisie) === norm(answer)`                        |
| `sentence_order` | concaténation **sensible à la casse** des mots cliqués |
| `matching`       | chaque paire `l::r` doit être exacte                   |

La casse de `sentence_order` est volontaire : la banque peut contenir à la fois
`The` et `the`, ce qui rend la question résoluble uniquement dans un ordre.
Voir `web/app.js:937` et `src/screens/QuizScreen.tsx:137`.

## Tests

```bash
npm test
```

- `src/domain/__tests__/sm2.test.ts` — intervalles 1→3→7 j, ease factor
  plancher 1.3, échec, échéances.
- `src/domain/__tests__/gamification.test.ts` — série (même jour / lendemain /
  trou / congélation), XP et objectif quotidien, gemmes de leçon, congélations.
- `src/domain/__tests__/lessons.test.ts` — générateur de leçons (taille,
  exercices réels, difficulté croissante, XP par niveau).
- `src/domain/__tests__/leagues.test.ts` — promotion/relégation, roulement
  hebdomadaire, garde-fous du premier jour.
- `src/domain/__tests__/path.test.ts` — progression du parcours et « reprendre ».
- `src/domain/__tests__/scoring.test.ts` — notation par type d'exercice.
- `src/domain/__tests__/chests.test.ts` — caisses et butins.
- `src/domain/__tests__/trophies.test.ts` — déblocage des trophées.
- `src/domain/__tests__/store.test.ts` — `revive()` : migration, champs
  abandonnés (cœurs, `flashSuccess`), persistance.
- `src/lib/__tests__/utils.test.ts` — mélange et normalisation (`norm`).
- `src/i18n/__tests__/i18n.test.ts` — parité fr/en/ar, intégrité des données
  extraites (comptes, modules, types, paires de matching).

### Parcours navigateur (E2E)

```bash
npm run dev          # terminal 1
npm run e2e          # terminal 2  (ou APP_URL=... npm run e2e)
```

`e2e/verify.py` (Python + Playwright) contrôle en une exécution : fenêtre
d'accueil, les 5 onglets, recherche + fiche de terme, bascule RTL, retournement
et notation des flashcards, puis des sessions de quiz qui couvrent les 4 types
de questions en répondant correctement, la persistance dans `localStorage`, et
le fait qu'**aucune** erreur console, erreur JS ni requête HTTP ≥ 400 n'apparaisse.
Il écrit ses captures dans `shots/`.

### Audit bouton par bouton

```bash
npm run dev                     # ou npm run build && npm run preview
APP_URL=http://localhost:4173 npm run audit
```

`e2e/full_audit.py` va plus loin que le parcours : il inventorie **chaque
bouton de chaque écran** et le clique (en restaurant l'état entre deux clics),
vérifie les actifs (favicon SVG + PNG, `apple-touch-icon`, manifest, JSON, SW),
l'intégration des données (15 modules, 4 512 termes, recherche fr/en/ar), puis
des cas limites : route inconnue, lien profond, retour/avant du navigateur,
fiche de terme, session jamais interrompue par des cœurs (barrière supprimée),
validation d'un `fill_blank` au clavier, progression de module et persistance
de la langue.

Sortie non nulle en cas d'erreur console/JS, de requête ≥ 400 ou de contrôle
défaillant — c'est le filet de sécurité avant de considérer une modification
comme terminée.

## Icônes

Toutes les icônes sont générées à partir de la mascotte Android
(`app/src/main/res/drawable-nodpi/ic_brand_foreground.png`) : le script aplatit
le fond blanc — **et le liseré du carré arrondi** — en vert plein `#35BE56`,
ne conserve que la composante du hibou, puis recadre.

```bash
npm run icons    # scripts/make_icons.py — 6 PNG + icon.svg dans public/
```

| Fichier | Cadrage | Usage |
|---|---|---|
| `favicon-16.png` / `favicon-32.png` | tête du hibou | onglet du navigateur |
| `icon.svg` | tête, vignette 128 px en base64 | favicon moderne (SVG) |
| `icon-192.png` / `icon-512.png` | hibou entier | manifest, raccourci |
| `apple-touch-icon.png` (180) | hibou entier | iOS, qui ignore les SVG |
| `icon-maskable-512.png` | hibou + marge | zone sûre Android (80 %) |

Aucun fichier n'est transparent : les coins doivent être opaques et verts, sinon
l'onglet affiche un fond blanc. `npm run audit` le vérifie pixel par pixel
(5 points d'échantillon par PNG) et refuse tout coin blanc ou transparent.

Le manifest déclare en plus des PNG 192/512 (dont un `maskable`), car Android
refuse un manifest sans PNG.

## Hors-ligne

`public/sw.js` met en cache le *shell* et les deux JSON de données ; il n'est
enregistré qu'en production (`import.meta.env.PROD`).
