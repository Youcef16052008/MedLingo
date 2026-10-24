# Plan d'améliorations — issues de la revue approfondie du mode histoire

**Origine :** revue du 24/10/2026 (rapport en fin de session, commits `8416d3f` + `c4bc03d`).
**Convention :** chaque phase se termine par des vérifications exécutées (`vitest`, `check:i18n`, `check:seed`, `build`) et un commit. Une phase n'est « done » que si ses critères d'acceptation sont verts.

---

## Phase 0 — Blocants de mise à jour & hygiène du dépôt (immédiat)

> Objectif : tout ce qui empêche d'avoir un dépôt vert avant d'ajouter quoi que ce soit.

| # | Tâche | Fichiers | Critère d'acceptation |
|---|---|---|---|
| 0.1 | **RISK-001** — fusionner les verdicts du bloc REVIEW dans la planification de fin de séance (une expression oubliée en révision ne doit pas être promue par un circling réussi). Noter les ratings dans l'état moteur (`reviewRatings`), les persister dans `storyProgress`, les appliquer dans `scheduleFromSession` avec `quality = min(circling, review)`. | `learning/session.ts`, `domain/store.ts`, `StorySessionScreen.tsx` | Tests : oubliée en REVIEW + circling parfait → due à J+1 ; retenue + parfait → rep 1. `vitest` vert. |
| 0.2 | **INFO-001** — clés i18n mortes (`reviewHubDesc`, `buildPathTitle`) : les brancher à l'endroit sémantiquement correct (sous-titre du hub de révision / titre de section) plutôt que les supprimer (travail WIP de l'utilisateur). | `i18n/index.ts`, écran concerné | `npm run check:i18n` → 0 morte, 0 manquante, 0 désynchronisée, code sortie 0. |
| 0.3 | **INFO-002** — régénérer le SQL de seed depuis le corpus courant (13 000 exercices). | `tools_gen_seed_sql.py` (exécution), asset seed Android | `npm run check:seed` → sortie 0. |
| 0.4 | Vérification complète + commit Phase 0. | — | `vitest` 100 %, `build` OK, `check:i18n` OK, `check:seed` OK. |

**Hors périmètre automatisable (rappel) :** la relecture linguistique/AR et médicale de l'histoire pilote (`review: pending`) reste une décision humaine — condition de publication store, pas du pilote dev.

---

## Phase 1 — Robustesse du mode histoire (web)

| # | Tâche | Détail | Critère d'acceptation |
|---|---|---|---|
| 1.1 | **RISK-002** — voix réellement disponibles : écouter `voiceschanged`, bannière honnête si 0 voix EN (`getVoices().length === 0`), pas seulement `speechSynthesis` présent. | `lib/speech.ts`, écran | Mode texte affiché quand aucune voix EN ; test unitaire sur la détection. |
| 1.2 | **RISK-004** — sélection de séance : priorité expressions échues (rotation `due → jamais vue → plus ancienne`), plus « première published » en dur. | `learning/stories.ts` ou `learning/selection.ts` | Test : 2 histoires, l'une avec expression échue → choisie. |
| 1.3 | **RISK-007** — parité web/Android : script `check:stories` comparant `app/src/main/assets/learning/stories.v1.json` et `web-react/public/data/stories.v1.json` (hash + ids + contentVersion). | `scripts/check-stories.mjs`, `package.json` | Sortie 0 quand identiques ; échec si divergence (testé en corrompant une copie temporaire). |
| 1.4 | **RISK-008** — garde `schemaVersion` : le chargeur rejette un fichier dont `schemaVersion` ≠ 1 (settled + failed distingués). | `learning/stories.ts` | Test : v2 → rejet, carte masquée, pas de crash. |
| 1.5 | **RISK-006** — enrichir les variantes acceptées (contractions britanniques : « he's got a headache », « hasn't », etc.) — contenu uniquement. | `stories.v1.json` (les deux copies via 1.3) | Les nouvelles variantes passent `checkAccepted` ; parité verte. |
| 1.6 | **RISK-005 (partiel)** — accessibilité : `aria-live` sur le feedback de correction, labels sur les inputs, focus géré après avance. | `StorySessionScreen.tsx` | Passage clavier complet sans souris ; inspection manuelle axe basique. |
| 1.7 | Tests composants : introduire Vitest + Testing Library, couvrir l'écran (soumission, révélation, reprise, RTL arabe). | `src/screens/__tests__/` | Suite composant verte en CI. |

---

## Phase 2 — Tranche verticale Android (guide, étape B)

| # | Tâche | Détail |
|---|---|---|
| 2.1 | Lecteur d'asset `learning/stories.v1.json` (kotlinx.serialization ou org.json selon stack) + filtre `published`. |
| 2.2 | Port du moteur de séance en Kotlin (états/actions 1:1 avec `session.ts`), horloge injectée. |
| 2.3 | Room **12 → 13** : `StorySessionProgressEntity` + `PhraseProgressEntity` (migration non destructive ; jamais de fallback destructif ; tests de migration `MigrationTests`). |
| 2.4 | Écran Compose : même boucle, bouton Retour = suspendre + arrêter TTS + sauver. |
| 2.5 | Scénarios de test JSON partagés web/Kotlin (mêmes entrées → mêmes états) — le guide §6.2 l'exige. |
| 2.6 | Bouton « Ma séance » sur l'accueil Android. |
| **Critère** | `./gradlew :app:testDebugUnitTest` vert ; parité `check:stories` verte ; ancienne progression conservée après migration (test). |

---

## Phase 3 — Contenu pilote ×10 + mesure (guide, étapes C+D)

- 3.1 Rédiger les 10 histoires (5 communication clinique, 5 académique) avec `review` à `pending` → bloque publication.
- 3.2 Boucle de relecture linguistique + médicale (humain) → `published`.
- 3.3 Mémoire d'expressions inter-histoires (ids partagés déjà prévus par le schéma) + tableau de bord minimal (premier essai / aide / production).
- 3.4 Pilote étudiant : compréhension sur audio neuf à J7, réutilisation, aides nécessaires (protocole §10 du guide d'analyse).
- **Critère** : 10 séances jouables web + Android ; mesures collectées sur ≥ 5 étudiants.

---

## Phase 4 — Enrichissements (uniquement si utiles)

- 4.1 Enregistrement local facultatif (permission micro explicite, suppression simple, défaut off).
- 4.2 Comparaison à un modèle audio, évaluations humaines ciblées.
- 4.3 Dialogue IA optionnel (coût/réseau/confidentialité à documenter avant) — la base doit rester fonctionnelle sans IA.

---

## Journal d'exécution

| Phase | Commit | Vérifications |
|---|---|---|
| 2 | `d75ff89` (24/10/2026) | gradlew :app:testDebugUnitTest **282/282** · vitest 198/198 · check:stories OK · check:i18n 0 · tsc OK. Livré : moteur Kotlin 1:1 (12 tests), scénarios JSON partagés web/Kotlin (déjà intercepté une asymétrie d'API), Room 12→13→14 non destructif (3 tests ; MIGRATION_12_13 du WIP réécrite en CREATE TABLE — l'ALTER d'origine plantait), schémas 13/14.json exportés, écran Compose + overlay + « Ma séance » + 37 clés i18n. Divergences web assumées : XP story ne touche pas la série/gemmes Android ; sélection du jour = première published (port de pickTodayStory en phase 3). |
| 1 | `850c970` (24/10/2026) | vitest 191/191 · check:stories OK (démontré échec sur divergence) · check:i18n 0 · check:seed 0 · build OK. Couvert : RISK-002 (voix honnête, classif pure + hook), RISK-004 (sélection déterministe), RISK-006 (variantes dont négations nues), RISK-007 (parité web/Android), RISK-008 (garde schemaVersion, rejet atomique), RISK-005 partiel (aria-live, labels, lang=en, focus), P1.7 tests composant (5, jsdom). Bugs trouvés par ces tests et corrigés : condition de bannière invalide sans synthèse, bouton final de production, état partagé entre tests. |
| 0 | `85998cd` (24/10/2026) | vitest 175/175 · check:i18n 0 problème · check:seed 0 problème (4504 termes / 13000 exercices) · build OK. Notes : seed SQL ≈ 12,4 Mo (couverture complète imposée par check:seed — à assumer côté déploiement Supabase) ; relecture linguistique/médicale de l'histoire pilote toujours *pending* (humain). |
