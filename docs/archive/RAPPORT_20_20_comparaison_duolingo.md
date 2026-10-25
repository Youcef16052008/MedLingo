# PARTIE 20/20 — Conformité à Duolingo (architecture, boucle de jeu, économie) — comparaison finale

**Sources** : l'ensemble des 19 rapports précédents + l'architecture réelle de Duolingo (session generator, Birdbrain, structures de données publiées).

## 1. Tableau de conformité par domaine

### ✅ Conformes à Duolingo (réellement implémentés)
| Mécanique | MedLingo | Duolingo | Qualité |
|---|---|---|---|
| Parcours en zigzag, unités × leçons, nœud BOSS | 15 unités × 6 | unités × skills | **Bonne** (seuil 70 %, verrouillage) |
| Système de ligues à 10 paliers | Bronze→Diamond, 30 membres, top 10 / bottom 5 | identique | **Bonne** (mais bots statiques) |
| Trophées | 12 paliers sériés |Achievements | **Bonne** |
| Cœurs | **ABSENTS** (supprimés en v10) | limités, regen, paywall | **Divergence assumée** |
| Gemmes | earn/spend, freeze 200, ledger | identique | **Bonne** (mais balance mutable, anti-triche non réelle) |
| Objectif quotidien | 20/50/100 XP, anneau | identique | **Conforme** |
| Séries (streak) + congélation | streakDays + freezeCount | identique | **Conforme** |
| Caisses récompenses | table de tirage pondérée 30/30/20/15/5 | chests | **Conforme** |
| Mascotte à humeurs | Medi 🩺 4 moods | Duo | **Partielle** (2 illustrations, 4 moods) |
| Répétition espacée | **SM-2** | Birdbrain (HLR + LSTM) | **Implémentation honnête, pas Duolingo** |
| Test de placement | **CAT adaptatif pondéré** | supprimé côté Duolingo | **Au-dessus de Duolingo** |
| Audio (TTS) | FR/EN/AR + studio de prononciation | enregistreuses natives | **Bonne** |
| i18n | FR/EN/AR + RTL | 40+ langues | **Bonne pour 3 langues** |
| Offline-first | seed Room 4 512 termes | streaming + cache | **Meilleur que Duolingo pour un usage DZ** |

### ❌ Non conformes / manquants
| Mécanique Duolingo | État MedLingo | Impact |
|---|---|---|
| **Birdbrain** (difficulté adaptative ML) | backend TS « lite » **non connecté** | Pas d'adaptation en temps réel |
| **Session Generator** (750 ms→14 ms) | backend TS **non connecté** | Pool d'exercices figé |
| **Server-authoritative progress** | 100 % local, `userId = 1` | Triche possible, pas de sync multi-appareil |
| **Système social** (amis, ligues réelles) | bots statiques + `friendships` SQL vide | Pas de rétention sociale |
| **Notifications riches localisées** | 4 notifs **en français uniquement** | Hors-fr/AR cassé |
| **Monétisation réelle** | `purchaseSuper()` **gratuit** | Paywall = fausse porte |
| **Illustrations de scène** | 2 vectoriels pour 4 512 termes | App **purement textuelle** |
| **Diversité d'exercices** | 44 exercices (29 mcq + 9 wordbank + 4 match + 2 fill) | On termine le parcours en ~1 h |
| **Types reading / clinical_case** | specs + UI existantes, **0 contenu** | Niveaux 5/6 injouables |

## 2. Comparaison de l'architecture

```
DUOLINGO (réel)                          MEDLINGO (réel)
─────────────────────                    ────────────────────
Client (Swift/Kotlin)                    Client (Kotlin/Compose)
  ↓ HTTPS                                (aucun réseau — pas de permission INTERNET)
API Gateway                                ╳
  ├─ Session Generator ← Birdbrain      Backend Supabase (non branché)
  ├─ Progress (server-authoritative)      ├─ session-generator.ts
  ├─ Gems Ledger (append-only)            ├─ birdbrain.ts (lite)
  └─ Notifications (SQS→APNS/FCM)         ├─ league-cron.ts
PostgreSQL + Redis (ligues)               └─ notifications.ts (pull)
S3 (course data)                         Room SQLite (4 512 termes en dur dans le bytecode)
```

**Verdict** : MedLingo n'est **pas** architecturée comme Duolingo. C'est une app **offline-first mono-utilisateur** avec une couche backend **écrite mais jamais câblée**. Prétendre « 100 % Duolingo compliant » (titre du document d'architecture) est **faux au moment de l'écriture**.

## 3. Les 3 écarts produit qui comptent vraiment

1. **Le contenu d'exercices (44 pour 90 leçons)** — c'est le **goulot d'étranglement absolu**. Tout le moteur existe, la UI existe, les 6 niveaux pédagogiques sont décrits, mais il n'y a presque rien à jouer. Duolingo a des dizaines d'exercices par leçon. **C'est l'action #1.**
2. **Les ligues sont figées** — les bots ne jouent jamais. La pression hebdomadaire (« league ending ! ») qui fait la rétention de Duolingo n'existe pas.
3. **L'app est textuelle** — 2 illustrations pour 4 512 termes médicaux (l'anatomie est visuelle !). C'est le plus grand écart de qualité perçue.

## 4. Bugs confirmés (par ordre de gravité)

| # | Bug | Gravité | Localisation |
|---|---|---|---|
| B1 | **Le matching est toujours compté correct** (`onComplete` envoie `correctAnswer`) | **Critique** | `QuizScreen` → `MatchingExerciseView` |
| B2 | **Persona injectée en prod** : `UserStatsEntity()` par défaut (série 12, niveau1 85%) affichée avant lecture de `user_stats` ; peut empêcher le déblocage des niveaux | **Critique** | `UserStatsEntity` → repository → `UiState` |
| B3 | **Seed non transactionnel** : un kill mid-seed laisse une base incomplète à vie | **Élevé** | `initializeDatabaseIfEmpty` |
| B4 | **Heure de rappel perdue au reboot** (retombe à 20:00) | **Élevé** | `BootReceiver` / prefs |
| B5 | **10 modules sur 15 n'ont aucun exercice** → leçons « finies » à 0 XP | **Élevé** | données |
| B6 | `leagueTier.take(4)` affiche « Bron »/« Diam » | Moyen | `HeartsGemsTopBar` |
| B7 | `PathBuilder` affiche `moduleBest` pour une leçon non jouée | Moyen | `PathBuilder` |
| B8 | `XpOutcome.freezeUsed` toujours `false` | Moyen | `ProgressionManager.addXp` |
| B9 | `swapLanguage` mutation destructive (2e swap corrompu) | Moyen | `ExerciseSpec.swapLanguage` |
| B10 | Icône de notification = mipmap colorée (carré blanc en status bar) | Moyen | `NotificationHelper` |
| B11 | Notifications 100 % français (app trilingue) | Moyen | `NotificationHelper` |
| B12 | `RECORD_AUDIO` manquant si le studio enregistre | Élevé (crash potentiel) | manifest |
| B13 | `filterTerms` synchrone sur 4 512 termes × 7 champs à chaque frappe | Moyen (jank) | VM |
| B14 | `matchesChapter` utilise `contains` (bug que `ModuleFilter` corrige) | Faible | VM |
| B15 | `saveLessonScore` lit toutes les lignes puis filtre | Faible | repository |
| B16 | `System.currentTimeMillis()` au lieu de l'horloge injectée (Path/Practice) | Faible (tests) | écrans |
| B17 | `SwapToFrench` non testé en prod, `SwappedExercise` jamais utilisé | Faible | domain |
| B18 | HomeScreen (1 076 l.) potentiellement mort/non navigable | Faible (poids) | UI |
| B19 | Bouton « télécharger » des modules déjà téléchargés (fausse barre) | Faible (UX) | StatsProfileScreen + VM |
| B20 | Profil du CAT non persisté | Faible | VM |

## 5. Points forts à préserver absolument

- **`IMAGE_STRATEGY.md`** et le **plan de gamification** : discipline documentaire rare (affirmations démystifiées, citations PubMed, critères exécutables).
- **Injection d'horloge** (`Clock`/`FakeClock`) sur tout le domaine SM-2 + ligues.
- **`TtsManager`** : gestion fine des races conditions Android (génération, callbacks indexés, pending speak).
- **Migrations Room 8→9** (réattachement par clé composite avec ids négatifs temporaires) — travail de très bon niveau.
- **Tests de régression documentés** : chaque test raconte le bug qu'il corrige (164 tests, 0 échec).
- **`002_security_rls.sql`** : durcissement SQL méthodique (RLS, column-level grants, vues sans PII).
- **`SeedIntegrityTest`** : 9 assertions sur les 4 512 données.
- Corpus de **4 512 fiches trilingues complètes** (étymologie, pearl, mnemonic, IPA) — la vraie richesse.

## 6. Plan d'action priorisé

### P0 — Cette semaine (bloque l'usage)
1. Corriger B1 (matching).
2. Corriger B2 (persona → valeurs neutres).
3. Corriger B3 (`@Transaction` sur le seed).
4. Corriger B4 (persister l'heure de rappel) + B12 (permission micro) + B10 (icône notif).

### P1 — Ce mois (contenu = produit)
5. **Générer ~1 800 exercices** dérivés des 4 512 fiches (mcq + match + wordbank + fill + reading + clinical_case) via un script. Cible : 20/leçon × 90.
6. Filtrer les exercices par **module** dans le quiz (`getExercisesByModuleAndLevel`).
7. **Animer les ligues** (progression déterministe des bots dans la semaine).
8. Brancher `ExerciseSpec`/`ExerciseChecker` dans le quiz (score partiel, normalisation AR, fuzzy).
9. Étendre les illustrations (Servier Medical Art CC BY 4.0, 30 schémas ciblés).
10. Internationaliser les notifications.

### P2 — Décisions produit
11. **Cœurs** : les réintroduire (cohérent avec le paywall) OU réécrire le paywall sans eux.
12. **Paiement** : brancher un vrai moyen de paiement (Google Play Billing / BaridiMob) ou retirer le paywall.
13. **Backend** : intégrer (Supabase SDK + sync) OU le déclarer non branché dans la doc.
14. **Plateformes** : unifier `web/`, `web-react/`, Android (les 3 ont 3 économies de jeu différentes).

### P3 — Hygiène
15. Renommer le namespace `com.example`, nettoyer le catalog (~10 artefacts morts), BOM Compose.
16. Supprimer `HomeScreen` mort, les 3,9 Mo de ressources mortes/doublées, le worktree `.kilo`, `version old but have react and vite/`.
17. Réécrire `ARCHITECTURE_FINALE_100_DUOLINGO.md` pour refléter le code.
18. Tests : ViewModel, Repository, CAT, TtsManager, tests Compose UI.
19. Revue éditoriale des 655 traductions + des 519 `termFr == termEn`.
20. CI : test de non-dérive du seed SQL, test des clés i18n, validation des traductions généralisée.

## 7. Verdict final

MedLingo **n'est pas un clone de Duolingo, et c'est tant mieux** : c'est une app **offline-first, trilingue, universitaire** avec un vrai corpus médical, un CAT adaptatif et une discipline d'ingénierie documentaire remarquable. La dette n'est pas dans le code de fond (domaine, migrations, tests) mais dans **trois zones** : le **contenu** (44 exercices), les **mensonges involontaires** (persona, fausse barre de téléchargement, fausse compatibilité backend), et la **documentation aspirationale** qui décrit un produit qui n'existe pas.

Le chemin le plus court vers la qualité : **arrêter d'ajouter des fonctionnalités et combler le contenu + corriger les 4 bugs critiques**.
