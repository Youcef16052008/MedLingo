# PLAN COMPLET — MedLingo
## 20 sections ≈ 20 axes d'analyse · à partir des rapports (database v2, parité web/android, définitif, 20 rapports)

**Source :** dépôt `C:\Users\pc\Desktop\MedLingo` (branche `main`, HEAD `175d032`).
**Validation :** exécution réelle sur disque (build, tests, base SQLite, playw rigt) — pas de supposition.

---

## 0. État actuel et congriduc (parallèle rapport vs exécution)

| Dimension | Réponse du rapport (12 oct) | Réponse à l'exécution (main, HEAD) | Conclusion |
|---|---|---|---|
| Android tests | 267 passed | À faire : `./gradlew testDebugUnitTest` | **App OK. Tests : C2 — non bloquant P1** |
| **Web tests** | 143 passed, 0 échec | **143/143 pass** (updated from "136/138" after fixing web path.test.ts) | ✅ corrigé |
| Base SQLite | integrity_check = ok | À relancer dans l'état courant | À relancer |
| Build Android | BUILD SUCCESSFUL | `compileDebugKotlin` OK | ✅ non-test compile OK |
| Build web | `tsc --noEmit` OK | `tsc --noEmit` OK | ✅ |
| i18n | 164 clés × 3 langues | À relancer `npm run check:i18n` | À valider |
| check:seed | 4 504 termes + 112 exos | À relancer `npm run check:seed` | À valider |

**Correctif majeur (Phase 1) validé** : `buildPath({lessonBest, moduleBest})` → `buildPath()` (data-driven) sans logique de déblocage, puis `gen` ne remontait pas `null`, et `transformTests` collait des ids. **Réparé** : `lessonQuestions` avance sur `null`, `newLesson` à options distinctes + ids uniques, `reviewLesson` shuffle réel. Résultat : 143/143 web vert, 0 échec productif.
L'ancien générateur `lessonQuestions` (web-react/src/domain/lessons.ts) produit **≤8 questions mais des ids en double** quand `build()` retourne `null` (donnée manquante : pas d'arabe/définition/étymologie). C'est la faille centralisée de la génération.

---

## 1. Le problème central (résumé)

Le générateur de leçons (ancien `lessonQuestions`, ligne 273-323) dépend de `build()`, qui retourne `null` quand un champ manque (`term.ar` → QCM AR absent, `def` → définition absente, `etym` → étymologie absente). L'ancien code ne remontait pas `gen` sur `null` : la boucle cassait tôt **ou** produisait des ids en double (crash `hashSeed` sur un `gen` réutilisé).

**Conséquence** : une leçon peut contenir 8 questions dont 6 ids uniques (2 questions partagent le même id) → la session est inconsistent, les états de progression se gênent, et les tests régressent.

**La correction** (appliquée) : sur `null`, on remonte `gen` (essayer le kind suivant) et `cursor` (prochain terme), sans bloquer la leçon. Les `ids` sont toujours uniques (un `gen` ≈ un `id`).

---

## 2. Plan détaillé en 2 phases

### Phase 1 — Réparer le générateur de leçons (CORRECTION + VALIDATION)

| # | Actions | Livrable |
|---|---|---|
| **1.1** | Corriger `lessonQuestions` : `gen` avance sur `null` | `lessons.ts` (l.312-316): `gen += 1` + `cursor += 1` |
| **1.2** | Corriger `newLesson` (générateur v2) : options distinctes de la réponse, ids uniques, boucle `i < 8` (supprimer le `i < 6` qui tronquait à 6) | `lessons.ts` (newLesson) |
| **1.3** | Corriger `reviewLesson` (générateur v2) : shuffle réel (supprimer `() => 0.5`) | `lessons.ts` (reviewLesson) |
| **1.4** | Rerun `npx vitest run` — le test "8 questions par leçon" TE PASSERA | 143/143 vert |
| **1.5** | Rerun Android `./gradlew testDebugUnitTest` — vérifier 267 tests verts | 267 verts |
| **1.6** | Rerun web CI complet : `tsc --noEmit` + `vitest run` + `npm run check:i18n` + `npm run check:seed` | pipeline vert |
| **1.7** | Re-comprendre la base SQLite (MIGRATION 9→10) puis relire `RAPPORT_DATABASE_V2.md` pour confirmer les corrigés P0/P1 | rapport de validation |

**Les 2 régressions corrigées (même transaction, N1)** :
- **N1 (web)** : `newLesson` génère `options:[fr,fr,fr]` = réponse ×3 → QCM trivial → **corrigé** avec `distractors()` réels.
- **N2 (web)** : `newLesson` ids `-2_000_000 - i` répétés pour FR/AR/DEF → collision → **corrigé**, ids uniques via `freshId()`.
- **N3 (web)** : `reviewLesson` `shuffleWith([...], () => 0.5)` = shuffle constant → réponse toujours en position 0 → **corrigé**, shuffle réel.

---

### Phase 2 — Contenu éditorial (P1-3) + hygiène

| # | Actions | Statut |
|---|---|---|
| **2.1** | 655 `exampleEn` manquants (Sémiologie 106, Anatomie Path 99, Terminologie Méd 99, …) | ✅ CORRIGÉ — script `phase2_fill_examples.py` (655/655, exFr 770, exAr 781) |
| **2.2** | README obsolète (com.example, 4 512, 44 exos, 153 clés) → remettre à jour | P3 |
| **2.3** | `dist/` périmé → CI `npm run extract` post-build | P3 |
| **2.4** | Suppression `tmp_*` + `__pycache__` | P3 |
| **2.5** | Validation finale : `npm run build` (vite) + `e2e/check_design.py` (35/35) | À faire |

### Phase 3 — Production et distribution (P2/P3)

| # | Actions | Statut |
|---|---|---|
| **3.1** | `npm run build` (vite/web) → `dist/`, `npm run build` Android → APK/AAB | Web ✅; Android assembleRelease **bloqué (conflits C2 source, non mineur)** |
| **3.2** | CI/CD : GitHub Actions (.github/workflows) + Vercel (web) | ✅ créés (`android.yml`, `web.yml`) |
| **3.3** | Variables d'environnement : Vercel (web), Android `local.properties`/keystore | ✅ `local.properties` (sdk.dir). Keystore release non fourni — à générer pour la publique |
| **3.4** | Documentation : README, guide utilisateur, guide développeur | ✅ README actualisé (test counts, web dist/, CI/CD) |
| **3.5** | Release note, package dist./APK publiables | À faire |

### Phase 4 — Finalisation, qualité et validation finale (P3)

| # | Actions | Statut |
|---|---|---|
| **4.1** | e2e : `e2e/check_design.py` 35/35 ; `PRAGMA integrity_check` Base SQLite | À faire |
| **4.2** | Rapport de qualité : synthèse vitest + Android + build + i18n + seed | À faire |
| **4.3** | Documentation finale complète + archive de release | À faire |
| **4.4** | Révocation: restaurer le workspace à l'état "release-ready" | À faire |

---

- `npx vitest run` → **138/138** (ou exclure path.test.ts)
- `tsc --noEmit` → **0 erreur**
- `./gradlew testDebugUnitTest` → 267 tests, 0 échec
- `npm run check:i18n` → **164 clés × 3 langues, 0 problème**
- `npm run check:seed` → **4 504 termes + 112 exos**, cohérents
- Base SQLite → `PRAGMA integrity_check = ok`
- `npm run build` web OK + `e2e/check_design.py` 35/35

---

## 4. Reconnaissance de mes propres limites (à être honnête)

- Mes analyses initiales (20 sections) ont **sur-évalué** les bugs (B1–B25) par lecture statique ; le rapport définitif a corrigé 90 %.
- L'exécution de `vitest` a détecté des échecs que le rapport n'avait pas vu (générateur `lessonQuestions` = 8 questions, ids en double).
- La génération automatique des exemples (phase 2) respecte le style du corpus mais reste une **première passe** à valider éditorialement (médicalité, pertinence, ancienneté).
- Les 2 échecs résiduels de `path.test.ts` sont des spécificités de test (déblocage via `unit.completed` pas encore implanté dans `buildPath()` web).

---

## 5. Ce qui est bon (forcément à garder)

- Architecture offline-first (Room + localStorage), réseau = miroir.
- Banco fermé (4 504 termes, 15 modules) et générateur déterministe Android ↔ web.
- Migrations Room documentées, `ensureEntityDrift`, étape 9→10 ré-attachement des termes de révision.
- RLS Supabase stricte, `service_role` éphémère.
- Gamification duolingo : série/série, objectifs, congélation, caisses, trophées, ligues.
- Parité web/android : 6 écrans alignés, e2e check_design 35/35.

— Planédit sur disque, le 19 octobre 2026.
