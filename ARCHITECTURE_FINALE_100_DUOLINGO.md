# ARCHITECTURE RÉELLE — MedLingo Medical English

**Date de dernière vérification :** 10 octobre 2026 (revue du code, pas du code documenté)
**Status :** Android offline-first, mono-utilisateur, backend **non branché**

> ⚠️ **Ce document remplace `ARCHITECTURE_FINALE_100_DUOLINGO.md` (29 sept 2026).**
> La version précédente affichait `✅ CODE FAIT` sur 3 phases dont le backend
> Supabase, Birdbrain, les notifications SQS et l'auth OTP — **aucun de ces
> éléments n'existe dans le code de l'app** :
> ```
> grep -rln "supabase\|Supabase" app/src/   → (aucun résultat)
> grep -n "INTERNET" app/src/main/AndroidManifest.xml  → (aucun résultat)
> ```
> Une app **offline-first** ne déclare pas `INTERNET`, et c'est cohérent : il
> n'y a pas de client HTTP. Le backend existe sous `backend/functions/`
> (1 329 lignes TypeScript, 4 Edge Functions) mais **il n'est pas référencé**.
> Le présent document décrit le code **réellement exécuté**.

---

## 1. Ce que l'app est réellement

**Une app Android Kotlin/Compose offline-first, mono-utilisateur (`userId = 1`)**
qui enseigne l'anglais/français/arabe médical à des étudiants PCEM1 algériens.

- Aucune permission `INTERNET`, aucun client HTTP, aucune authentification.
- Toute la donnée est embarquée (Room) ou générée à la volée.
- `backend/functions/` est du code non déployé, non référencé : **une direction
  abandonnée, pas une intégration**.

### Chiffres vérifiés par programme

| Élément | Valeur | Méthode |
|---|---|---|
| Termes trilingues | **4 504** | `grep -rh "id = " app/src/main/java/com/example/data/terms/*.kt \| grep -c "id = "` |
| Modules | **15** | `ls data/terms/ \| wc -l` |
| Exercices écrits main | **44** | `grep -rc "ExerciseEntity(" LearningExercisesData.kt` |
| Leçons générées jouables | **90** (15 × 6) | `LessonGeneratorRealDataTest` |
| Clés i18n | ~200 × 3 langues | `check:i18n` → 0 écart |
| Tests unitaires | **209, 0 échec** | `./gradlew testDebugUnitTest` |
| Images | 2 illustrations (`illustration_femur`, `illustration_neuron`) | `data/illustrations/` |

---

## 2. Architecture réelle (couches)

```
┌─────────────────────────────────────────────────────────────────────────┐
│ UI — Jetpack Compose                                                    │
│ 6 screens + components : PathScreen, QuizScreen, StatsProfileScreen,    │
│ FlashcardsScreen, ModulesScreen, LessonScreen                           │
│ AudioPronunciationStudio (TTS), AppIntroOverlay, SuperPaywallDialog     │
└─────────────────────────────────────────────────────────────────────────┘
                              ▼ StateFlow
┌─────────────────────────────────────────────────────────────────────────┐
│ MedLinguaViewModel — UiState unique, ~900 lignes                        │
│ startLevelTraining, recordQuizCompletion, setReminderTime, ligues,      │
│ gemmes, trophées, caisses, TTS, langue                                  │
└─────────────────────────────────────────────────────────────────────────┘
                              ▼
┌─────────────────────────────────────────────────────────────────────────┐
│ MedLinguaRepository — seed transactionnel, XP/SM-2/ligues               │
└─────────────────────────────────────────────────────────────────────────┘
                              ▼ Room (medlingua_dz.db, v9)
┌─────────────────────────────────────────────────────────────────────────┐
│ 13 tables : medical_terms, exercises, user_stats, flashcard_progress,   │
│ downloaded_modules, lesson_scores, gems_transactions, league_cohorts,   │
│ league_members, trophies, ...                                           │
└─────────────────────────────────────────────────────────────────────────┘
                              ▲
┌─────────────────────────────────────────────────────────────────────────┐
│ Domain (pur, testable)                                                  │
│ LessonGenerator (90 leçons depuis 4 504 fiches)                         │
│ LeagueBotAnimator (ligues animées)   MatchScoring (P0-1)                │
│ ProgressionManager (XP/série)        LeagueManager (cohortes)           │
│ SpacedRepetitionAlgorithm (SM-2)     TrophyManager (12 trophées)        │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 3. Les features Duolingo **réellement** implémentées

| Feature | Status | Où |
|---|---|---|
| Parcours 90 nœuds (15 modules × 6 niveaux) | ✅ | `PathBuilder`, `PathScreen` |
| Déblocage à ≥ 70 % | ✅ | `PathBuilder.UNLOCK_SCORE` |
| **Générateur de leçons** (90 leçons jouables) | ✅ Phase 2 | `LessonGenerator` (port de `web-react/lessons.ts`) |
| Ligues 30 membres, 10 tiers, promo/démo | ✅ | `LeagueManager`, `LeagueBotAnimator` |
| **Ligues animées** (bots qui progressent) | ✅ Phase 3 | `LeagueBotAnimator` |
| Gemmes + ledger append-only anti-triche | ✅ | `GemsManager`, `gems_transactions` |
| Trophées (12) | ✅ | `TrophyManager` |
| Caisses | ✅ | `ChestReward`, `ChestSource` |
| SM-2 (répétition espacée) | ✅ | `SpacedRepetitionAlgorithm` |
| CAT adaptatif | ✅ | couche domain |
| Série (streak) + gel gratuit | ✅ | `ProgressionManager` |
| 4 notifications (deep-linkées, i18n) | ✅ Phase 3 | `NotificationHelper`, `NotificationReceiver` |
| Studio prononciation TTS multi-langue | ✅ | `AudioPronunciationStudio`, `TtsManager` |
| i18n trilingue complet | ✅ | `AppLanguage.kt` (~200 clés × 3) |
| ExerciseSpec JSONB + checkers purs | ✅ | `ExerciseSpecMigrator`, `ExerciseChecker` |
| Paywall `Super` | ⚠️ **fausse porte** | `purchaseSuper()` met `isSuper = true` gratuitement |
| Cœurs (hearts) | ⚠️ **partiel** | `HeartsGemsTopBar` affiche, mais plus de perte de cœur en quiz |

---

## 4. Ce qui n'existe PAS (malgré l'ancien document)

| Ancienne affirmation | Réalité |
|---|---|
| « Supabase Backend ✅ CODE FAIT » | **0 référence** à Supabase dans `app/src/` |
| « Session Generator 14ms ✅ » | Le code existe (`backend/functions/`) mais **n'est pas appelé** |
| « Birdbrain V2 40-dim ✅ » | Pareil — non branché, non déployé |
| « FIFO SQS Notifications ✅ » | Les notifications sont des `AlarmManager` locaux |
| « Mascotte Medi ✅ » | 2 illustrations statiques, pas de mascotte animée |
| « 2945 termes » | **4 504** (le comptage a été refait) |
| « Auth phone OTP + Google OAuth » | **Aucune auth** — `userId = 1` hardcodé |

**`backend/functions/` (1 329 lignes TS) et `backend/supabase/migrations/`
existent dans le repo mais ne sont ni compilés, ni déployés, ni référencés par
l'app.** C'est une direction de conception, pas une intégration.

---

## 5. Dette technique connue (extraite du rapport final)

- `web/` vanilla supprimé (Phase 0) — `web-react/` est le port fidèle et testé.
- `.kilo/worktrees/mulberry-dart` (8,7 Mo) supprimé (Phase 4) : il contenait des
  clones de `SupabaseClient.kt` / `HeartsManager.kt` **différents** de l'app,
  source de confusion majeure.
- 519 fiches `termFr == termEn` (à revoir éditorialement).
- `ARCHITECTURE_FINALE_100_DUOLINGO.md` : ce document le remplace.

---

## 6. Plateformes

| Plateforme | Status |
|---|---|
| **Android (Kotlin/Compose)** | ✅ App principale, 209 tests |
| **`web-react/`** | ✅ Port fidèle, 111 tests, `tsc --noEmit` strict |
| **`web/` vanilla** | ❌ Supprimé en Phase 0 (divergeait) |
| **`backend/functions/`** | ⚠️ Code non branché (voir §4) |

Les deux plateformes vivantes partagent le **même générateur de leçons**
(`LessonGenerator` ↔ `web-react/src/domain/lessons.ts`) et la **même logique de
parcours** (`PathBuilder` ↔ `path.ts`). C'est la garantie de non-divergence.
