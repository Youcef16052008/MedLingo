# Lot 2 — Architecture et plan d'exécution

**Projet** : MedLingo — app Android (Kotlin/Compose) + web-react
**Architecte** : Hermes (upstage/solar-mini4)
**Date** : 2026-10-18
**Statut** : Plan présenté pour validation de l'utilisateur

---

## 0. État actuel (validé par les tests P5)

| Côté | Fichier | État |
|------|---------|------|
| Web | `web-react/src/domain/lessons.ts` | `newLesson()` ✅, `reviewLesson()` ✅ (patch corrigée : 3 exos/terme) |
| Web | `web-react/src/domain/path.ts` | `buildLessonPath()` ✅ |
| Web | `web-react/src/screens/ChapterScreen.tsx` | ✅ (module/chapter/tableau de bord) |
| Web | `web-react/src/screens/ReviewHubScreen.tsx` | ✅ (hub central "Revisions en attente") |
| Web | `web-react/src/domain/store.ts` | Serialization `lessonKey`, `ReviewQueueItem` ✅ |
| Web | `web-react/src/domain/load.ts` | `getLessonMap()`, `getLessonEntry()` ✅ |
| **Android** | `LessonGenerator.kt` | ⚠️ Port ancien (module → 6 niveaux) — **à remplacer** |
| **Android** | `PathBuilder.kt` | ⚠️ Port ancien (module → 6 niveaux) — **à remplacer** |
| **Android** | `ChapterScreen.kt` | ❌ N'existe pas |
| **Android** | `ReviewHubScreen.kt` | ❌ N'existe pas |

---

## 1. Architecture du Lot 2 (Android)

### 1.1 Maillage conceptuel

```
┌─────────────────────────────────────────────────────────────┐
│  Lot 2 : Android port Module → Chapter → Lesson             │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  Web-react (déjà fait)          Android (à faire)           │
│  ──────────────                   ──────────────            │
│  lessons.ts            ───►   LessonGenerator.kt           │
│  path.ts               ───►   PathBuilder.kt               │
│  ChapterScreen.tsx     ───►   ChapterScreen.kt             │
│  ReviewHubScreen.tsx   ───►   ReviewHubScreen.kt           │
│  store.ts              ───►   store (serialization)        │
│  load.ts               ───►   load (data)                  │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

### 1.2 Nouveau modèle de données (Android)

| Entité | Champs clés | Source |
|--------|-------------|--------|
| `LessonEntry` | `lessonId`, `kind` (`new`\|`review`), `termIds` (list<Int>), `reviewedLessonIds` (list<String>), `questionCount` | `lesson_map.json` |
| `ChapterInfo` | `chapterId`, `moduleId`, `title` | `chapters.json` |
| `Lesson` (dispatch) | `kind`, `termIds` (list<Int>), `questions` (List<ExerciseEntity>) | Runtime |

### 1.3 Structures de contrôle décisionnelles

```
Module (15)
  └─ Chapter (312)
       ├─ NewLesson (L1-L5) : 3 termes × 8 Q
       │    ├─ L1/L3/L6 : auto-continue
       │    └─ L2/L4/L5 : Continue / Revision
       └─ ReviewLesson (L6+) : 0 terme, 16 Q
            ┌─ Continue → ajout au hub (status=unlocked)
            └─ Revision → lecture immédiate
```

### 1.4 Serialization Android (store)

```
lessonKey   = "${chapterId}:L${levelIndex+1}"
entry.id    = lessonKey
entry.kind  = entry.kind
exercise.id = -3_000_000 - term.id (unique)
```

---

## 2. Plan d'exécution — Lot 2

### 2.1 Phase 1 — Fondations (1 à 3 jours)

**Objectif** : reconstruire `LessonGenerator` et `PathBuilder` sur la nouvelle architecture, sans écran UI.

| Tâche | Fichier | Livrable |
|-------|---------|----------|
| 1.1 | `LessonGenerator.kt` | Port de `newLesson()` : 3 termes, 8 Q, `id = -3_000_000 - term.id` |
| 1.2 | `LessonGenerator.kt` | Port de `reviewLesson()` : 0 terme, 16 Q, tirage des 2 leçons précédentes |
| 1.3 | `PathBuilder.kt` | Remplacement par `buildLessonPath()` : module → chapter → lesson |
| 1.4 | `PathBuilder.kt` | Usine de `PathUnit`, `PathChapter`, `PathNode` |
| 1.5 | `ChapterScreen.kt` | Écran découverte (module → chapter → tableau de bord des leçons) |
| 1.6 | Tests | `LessonGeneratorTest.kt`, `PathBuilderTest.kt` |

**Sortie Phase 1** : générateur + routeur opérationnels, acceptation de l'architecture.

### 2.2 Phase 2 — Interface et hub (2 à 4 jours)

| Tâche | Fichier | Livrable |
|-------|---------|----------|
| 2.1 | `ChapterScreen.kt` | Navigation module → chapter → lesson (bactrack complet) |
| 2.2 | `ReviewHubScreen.kt` | "Revisions en attente" : `unlocked` / `played`, Jouer, suppression |
| 2.3 | `store` | `addToReviewQueue()`, `markReviewPlayed()`, `endLesson()` |
| 2.4 | Intégration | `PathScreen` → utilitaire pour lancer `onLessonStart()` |
| 2.5 | `gen_lessons.py` | Dernier clé en main : `lesson_map.json` (2 350 leçons) + `chapters.json` (312) |

**Sortie Phase 2** : parcours complet, hub central, hypers — 100 % de la UX demandée.

---

## 3. Design du script de génération (gen_lessons.py)

### 3.1 Pipeline

```
terms.json (4 504 fiches trilingues)
  └─ build_league_roster()  [P1 : 12 championnats × 375 termes]
       ├─ assign_championship_league()  [P2 : 18 championnats × 250 termes]
            └─ build_module_roster()    [P3 : 12 modules × 375 termes]
                 ├─ build_chapter_roster() [377 modules × 12 termes]
                      ├─ new_chapter()   [L1-L5 : 3 termes/leçon]
                      └─ review_chapter() [L6+ : 0 terme, 16 Q]
                           └─ review_league()  [-3_000_000 base, 6 leçons/championnat]
```

### 3.2 Règles de génération

| Règle | Valeur |
|-------|--------|
| Nouveau leçon | 3 termes, 8 Q, `termIds` = `[1, 2, 3]`, `kind = new` |
| Leçon review | 0 terme, 16 Q, `reviewedLessonIds` = `[lN, lN+1]`, `kind = review` |
| Fréquence review | Toutes les 2 nouvelles leçons |
| Exercices | 18 par review (3 exos × 3 termes × 2 leçons) |
| Durée | 4 504 termes → 312 chapitres → 2 350 leçons (1 621 new, 729 review) |
| Seed | `hashSeed("${chapterId}:lesson")` → déterminisme |
| Id leçon | `{module}-{chapter}-{levelIndex+1}` |
| Id exercise | `-3_000_000 - term.id` |

### 3.3 Sorties

| Fichier | Taille | Description |
|---------|--------|-------------|
| `chapters.json` | 312 | `chapterId`, `moduleId`, `title` |
| `lesson_map.json` | 2 350 | `lessonId`, `kind`, `termIds`, `reviewedLessonIds`, `questionCount` |

---

## 4. Design final — Architecture de la UI

### 4.1 Flux d'accès

```
┌─ Modules (15) ──────────────────────────────────────────────┐
│  🏥 Anatomie      📖 Physiologie      💊 Pharmacologie      │
│  🧠 Biochimie     🔬 Histologie       🧫 Microbiologie    │
│  … (15 modules)                                       │
└─────────────────────────────────────────────────────────────┘
        │
        ▼
┌─ Chapitres (ex: Angiologie = 19) ──────────────────────────┐
│  L1 Introduction      L2 Vasculature                      │
│  L3 Capillaires       L4 Nvaste             L5 …           │
└─────────────────────────────────────────────────────────────┘
        │
        ▼
┌─ Leçon (8 Q) — Nouvelle architecture:                    │
│  • 3 termes × 8 Q → 16 Q (review à L6+)                     │
│  • Cœur (5) : mauvaise réponse → reinitialisation           │
│  • L1/L3/L6 → auto-continue                                 │
│  • L2/L4/L5 → Continue / Revision                           │
└─────────────────────────────────────────────────────────────┘
        │
        ▼
┌─ Revise (continue) → hub "Revisions en attente"           │
│  • 0 exécutable immédiatement, playable plus tard           │
│  • Hub central toutes les leçons révisées                   │
└─────────────────────────────────────────────────────────────┘
```

### 4.2 État de la leçon

| Champ | Type | Valeur |
|-------|------|--------|
| `hearts` | Int | 5 (max) |
| `score` | Int | 0–100 |
| `progress` | Int | 0–8 (new) / 0–16 (review) |
| `reviewQueue` | List<ReviewQueueItem> | `unlocked` / `played` |

### 4.3 Cœurs cardiaques

```
5 cœurs (grains de 5 coins) :  ① ② ③ ④ ⑤
Mauvaise réponse : ① → ② → ③ → ④ → ⑤
0 cœurs → leçon redémarre depuis le début
```

---

## 5. Commandes de vérification

| Commande | Résultat attendu |
|----------|------------------|
| `cd /c/Users/pc/Desktop/MedLingo/web-react && npx vitest run src/domain/__tests__/lessons.test.ts --reporter=verbose` | **22 tests pass** |
| `cd /c/Users/pc/Desktop/MedLingo && ./gradlew assembleDebug` | Build Android OK |
| `cd /c/Users/pc/Desktop/MedLingo && node -e "..."` | 2 350 leçons, 312 chapitres |
| `cd /c/Users/pc/Desktop/MedLingo/web-react && tsc --noEmit` | TypeScript clean |
