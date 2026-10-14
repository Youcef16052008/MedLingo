# PARTIE 18/20 — Documentation & dette documentaire

**Fichiers lus** : `README.md` (119 l.), `ARCHITECTURE_FINALE_100_DUOLINGO.md` (153 l.), `docs/IMAGE_STRATEGY.md` (23 l.), `docs/superpowers/plans/2026-10-06-duolingo-gamification-plan.md` (144 l.), `docs/superpowers/specs/2026-10-06-duolingo-gamification-design.md` (277 l.), `docs/anglais-medical-*-review.md` (2 × ~880 l.).

## État des documents

| Document | Lignes | Fiabilité | Verdict |
|---|---|---|---|
| `README.md` | 119 | **Correcte** | Build/test/signature OK, 4 512 termes exacts, commande `adb` exacte ; section « Parité web ↔ Android » ajoutée le 13/10/2026 |
| `IMAGE_STRATEGY.md` | 23 | **Excellente** | Le seul doc qui démographie les affirmations non prouvées |
| Plan gamification | 144 | **Excellente** | Journal d'exécution réel, avec corrections et mesures |
| Spec gamification | 277 | **Bonne** | Référence réelle du code (§7, §5.1/§5.2, §11 cités dans les commentaires Kotlin) |
| `ARCHITECTURE_FINALE_100_DUOLINGO.md` | 153 | **Réécrite (10/10, maj. 13/10)** | L'audit du 9/10 la jugeait FAUSSE ; elle a depuis été réécrite en « ARCHITECTURE RÉELLE » (état offline-first honnête, `[x]` retirés). §6 mis à jour : 267/127 tests, parité visuelle |
| `web/README.md` | — | **Supprimé** | Dossier `web/` (legacy) supprimé — voir `RAPPORT_16_20_web_legacy_archive.md` |
| `RAPPORT_FINAL_DEFINITIF.md` | 346 | **À jour** | Audit DB 2 passes + plan P0→P3 100 % exécuté + §9 parité visuelle web↔Android et correctifs de maintenance (13/10/2026) |
| `RAPPORT_PARITE_WEB_ANDROID.md` | 131 | **Excellente** | Nouveau (13/10/2026) — 10 écrans alignés sur Android, 35/35 checks computed styles, écarts résiduels documentés |
| `RAPPORT_DATABASE_V2.md` | — | **Excellente** | 2e passe database — 6 défauts corrigés (commit `64f9369`), 267 tests verts |
| Docs relecture anglais médical | 1 761 | **À jour mais non traitées** | 655 entrées × 2 fichiers, cases `[ ]` jamais cochées |

### Le plan de gamification est un document exceptionnel
- Chaque tâche a un **critère de validation exécutable** (ex. `grep -ri hearts src/` = 0, 161 tests verts, `full_audit.py` 95 clics/0 échec).
- Il **documente les corrections** après coup : la réparation de `moduleBest` (qui validait toutes les leçons d'un module dès qu'une atteignait 70 % — contraire à la spec §7), la correction perf ANR des 90 nœuds animés, les étapes de vérification sur émulateur.
- Il explique l'**ordre d'exécution** : Lot 1 web-react → Lot 2 Android. C'est la trace d'une vraie méthode.
- **Inexactitude résiduelle** : la Phase 6 est marquée 🟡 avec « 161 tests verts » alors que le build actuel en a **164** (Partie 12). Compte non mis à jour.
  - *Vérification exécutée* : `./gradlew testDebugUnitTest` → **BUILD SUCCESSFUL, 164 tests, 0 skipped, 0 failures, 0 errors** (exécution fraîche, 1 min 21 s).

### `ARCHITECTURE_FINALE_100_DUOLINGO.md` — le problème documentaire central

> **Résolu depuis** : le document a été réécrit le 10/10 en « ARCHITECTURE RÉELLE »
> (titre interne inchangé, contenu honnête). L'analyse ci-dessous conserve le verdict
> sur la version fautive du 9/10, à titre de trace d'audit.
Toutes ses cases `[x]` cochées prétendent : Phase 1 (économie de jeu) + Phase 2 (exercise engine) + Phase 3 (backend, Birdbrain, social) **terminées**. Vérifications :
- **Backend** : le code existe dans `backend/`, mais **aucune référence Supabase/HTTP dans `app/src`** (Partie 14). L'app ne sait même pas que le backend existe.
- **Birdbrain** : le vecteur 40-dim existe côté backend ; **l'app Android n'a aucun appel**. La seule "adaptation" est le CAT (`AdaptivePlacementEngine`), qui n'est pas Birdbrain.
- **Social (friends, ligues réelles)** : `friendships` existe en SQL ; l'app a des **ligues 100 % locales contre des bots statiques** (Partie 4).
- **Paiements** : `purchases` (BaridiMob/CCP/RevenueCat) existe ; l'app a `purchaseSuper()` **qui ne fait que mettre `isSuper = true` sans paiement** (Partie 4).
- **Cœurs** : le document d'architecture **les promeut encore** (`hearts ∞` dans Super), alors que le plan de gamification T0.1/T4.1 les a **explicitement supprimés** et que la migration Room v10 les a **détruits en base**.

**Deux documents du même repo se contredisent sur la même fonctionnalité.** Et le document faux est celui qui porte le titre le plus ambitieux (« 100 % Duolingo »).

### Docs de relecture : 655 × 2 cases jamais cochées
Les deux fichiers `anglais-medical-*-review.md` sont des **listes de revue éditoriale** générées automatiquement (cases `[ ]`). Aucune case n'est cochée — donc, selon la documentation du projet lui-même, **1 310 traductions n'ont jamais été validées** par un locuteur natif. Le plan de gamification Phase 6 le dit lui-même : « relecture des 2 rapports d'anglais médical laissés en attente (revue native, hors périmètre) » — c'est **honnête** (la dette est déclarée), mais non résolue. Cela contredit partiellement `SeedIntegrityTest.medical english examples are translated` qui ne vérifie que la **non-vacuité**, pas la **justesse**.

## Actions concrètes

> **Mise à jour 13 octobre 2026.** Depuis cet audit, l'action 2 est **résolue** (`web/` legacy
> supprimé, voir Partie 16) et l'action 1 est **résolue** (`ARCHITECTURE_FINALE_100_DUOLINGO.md`
> réécrit en « ARCHITECTURE RÉELLE » le 10/10). La dette documentaire principale — la parité
> visuelle web ↔ Android — a été exécutée et documentée (`RAPPORT_PARITE_WEB_ANDROID.md`).
> Reste ouverte : l'action 4 (revue éditoriale des 655 exemples).

1. ~~**Réécrire `ARCHITECTURE_FINALE_100_DUOLINGO.md`**~~ — **fait (10/10)** : réécrit en « ARCHITECTURE RÉELLE » (état offline-first honnête, `[x]` retirés là où le code n'existe pas).
2. ~~Corriger `web/README.md` (2 945 → 4 512 termes)~~ — **fait par suppression** : le dossier `web/` legacy a été supprimé (Partie 16), le port vivant est `web-react/` documenté au README.
3. Mettre à jour le plan (161 → 164 tests) et marquer la Phase 6 en fonction de l'état réel.
4. **Planifier la revue éditoriale** des 655 entrées : 42 chapitres × ~15 entrées est faisable en revue par lots ; prioriser les chapitres les plus visibles (« At the Hospital », « Emergency »).
5. Ajouter en haut du README un **diagramme de l'état réel** : app Android (offline, 4 512 termes, 44 exos) / web-react (PWA, parité visuelle alignée) / backend (non branché) / archives.
6. Migrer la spec gamification (277 lignes) au format du README pour qu'elle soit la **source canonique** citée par le code (les commentaires citent déjà §5.1/§7/§11).
