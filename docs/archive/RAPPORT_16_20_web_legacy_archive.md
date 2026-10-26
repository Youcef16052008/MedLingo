# PARTIE 16/20 — `web/` (PWA vanilla JS legacy) et `version old but have react and vite/`

> **Note (13 octobre 2026)** : le dossier `web/` décrit dans ce rapport a depuis été
> **supprimé** (Phase 0 du plan de remédiation, exécutée). Le port web vivant est
> `web-react/`, documenté dans la Partie 15 et dans `web-react/README.md`.

**Fichiers analysés** : `web/` complet (14 fichiers) + inventaire complet de `version old but have react and vite/` (83 fichiers) + `docs/*`.

## Observations

### `web/` — PWA vanilla JS **obsolète mais toujours présente**
- 1 179 lignes de JS pur dans `app.js` : 41 fonctions top-level, **1 180 lignes de HTML généré par templates de chaînes**. Architecture SPA maison avec `route()`/`viewHome()`/`viewModules()`/`viewQuiz()` — du jQuery-style sans jQuery.
- `data/terms.json` contient **4 512 termes** (vérifié au parseur JSON), `data/exercises.json` **44 exercices**. Données **identiques** à l'Android et au SQL.
- **Inexactitude documentaire** : le `README.md` annonce « lexique de **2 945 termes** médicaux » alors que `terms.json` en contient **4 512**, et `sw.js` commente « 1 640 termes servis instantanément ». **Trois chiffres différents pour le même fichier** — preuve que le README n'a pas été mis à jour après l'extraction.
- **La version web a ENCORE les cœurs** : `app.js` contient 28 occurrences de `hearts`, `regenHearts()`, `loseHeart()`, régénération 30 min. Or l'Android et `web-react` les ont **supprimés** (plan T0.1/T4.1, exécuté). **Le projet comporte donc 3 versions fonctionnelles différentes de la même économie de jeu.**
- PWA correctement faite : `sw.js` network-first pour le shell + cache-first + revalidation en fond pour les données, purge des anciens caches, `skipWaiting`/`clients.claim`, `viewport-fit=cover`, `theme-color`, icône SVG. C'est du bon travail.
- Outils utiles : `tools/extract.mjs` (régénère les JSON depuis le Kotlin), `tools/check-i18n.mjs` (vérifie les 3 dictionnaires, « 100 clés »), `tools/cdp.mjs` (debug Chrome DevTools Protocol), `tools/_seed.html` (pré-remplit le localStorage pour les captures).
- **Fonts Google en `preconnect`/`link`** : la PWA « 100 % hors-ligne » dépend de `fonts.googleapis.com` au premier lancement. Contredit la promesse offline. À self-héberger.

### `version old but have react and vite/` — 83 fichiers, ~5,2 Mo d'images
C'est **l'ancienne app Android complète**, avant le re-design Duolingo :
- Modules de termes **obsolètes** : `OsteologyTerms.kt` (53 Ko), `MyologyTerms.kt` (48 Ko), `NeurologyTerms.kt` (43 Ko), `ArthrologyTerms.kt` (35 Ko) — **ces modules n'existent plus dans l'app courante** (la taxonomie actuelle est 15 modules différents : Anatomie, Biochimie…).
- `MedLinguaDatabase.kt` ne fait que **1 393 octets** (vs 18 252 aujourd'hui) — Room sans migrations.
- `MedLinguaViewModel.kt` 25 Ko (vs 38,5 Ko), `QuizScreen.kt` 31 Ko.
- **SplashScreen.kt** (7,5 Ko) existe ici mais pas dans l'app courante (remplacé par `AppIntroOverlay`).
- **Images supplémentaires non portées** : `img_splash_dark.jpg` (556 Ko), `img_splash_light.jpg` (331 Ko), `img_medlingua_mascot_icon.jpg` (474 Ko), `hero_medical_dz.jpg` (851 Ko), et **les mêmes doublons** (`img_medical_hero.jpg` ≡ `img_clinical_stethoscope_banner…jpg`, `img_medical_faculty.jpg` ≡ `medical_faculty_banner…jpg`).
- Le dossier se nomme lui-même « version old but have react and vite » — mais **il ne contient pas de React ni de Vite** : c'est un projet Android Gradle. Le nom est trompeur.

## Jugement

`web/` est une **troisième plateforme** (vanilla JS) dont l'économie de jeu (cœurs) et la documentation (2 945 vs 4 512) ont dérivé. `version old but have react and vite/` est un **dépôt fossile** de 83 fichiers dont les données ne sont plus compatibles avec le schéma actuel. Ensemble, ces deux dossiers pèsent **~5,5 Mo** dans le repo et entretiennent la confusion (trois versions du même produit, trois économies de jeu, trois comptes de termes).

## Actions concrètes
1. **Décider le sort de `web/`** : soit la maintenir à jour (supprimer les cœurs comme ailleurs, corriger le README à 4 512), soit la **supprimer** au profit de `web-react/` (qui fait la même chose en typé, testé et avec un outil d'audit). Une PWA vanilla en double de la PWA React n'a pas de raison d'être.
2. **Corriger le `README.md` de `web/`** (2 945 → 4 512) ou supprimer le dossier.
3. **Self-héberger les polices** (Fraunces, IBM Plex Sans, IBM Plex Sans Arabic) pour que la PWA soit réellement hors-ligne. La latence sur un réseau DZ est un vrai problème.
4. **Supprimer `version old but have react and vite/`** : c'est de l'histoire — git la conserve. Si on veut la garder, la déplacer dans `archive/` et la renommer (elle n'a ni React ni Vite). Les 5,2 Mo d'images doublonnées alourdissent inutilement le clone.
5. Documenter dans le README racine que `web-react/` est le front web **actuel**.
