# Plan d'implémentation — MedLingo « 100 % Duolingo »

**Spec :** `docs/superpowers/specs/2026-10-06-duolingo-gamification-design.md`
**Date :** 06 Oct 2026 · **Ordre :** Lot 1 `web-react` → Lot 2 `app/` Android

**Commandes de vérification (web) :**
```
npm run test        # vitest
npm run check:i18n  # clés fr/en/ar
npm run build       # tsc + vite build
npm run e2e         # python e2e/verify.py
npm run audit       # python e2e/full_audit.py
python e2e/check_design.py
```
**Android :** `gradlew :app:assembleDebug :app:testDebugUnitTest` puis vérif. appareil (adb).

**Règle :** chaque tâche se termine par des commandes vertes ; on ne casse jamais un lot précédent.

---

## LOT 1 — web-react

### Phase 0 — Socle (cœurs ↓, store, routes, domain)

**T0.1 · Supprimer les cœurs (web)**
- `src/domain/gamification.ts` : retirer `HEART_MAX/HEART_REGEN_MS/REFILL_COST_GEMS/regenHearts/loseHeart`, garder `todayKey/touchStreak`.
- `src/domain/store.ts` : champs `hearts, heartsTs` retirés de `AppState` + defaults (L17-18, L62-63) ; `revive()` ignore les anciens champs sans purger.
- `src/App.tsx` : ticker 30 s (L50-65) supprimé.
- `src/components/TopBar.tsx` : puce ❤️ (L28-30) supprimée.
- `src/screens/QuizScreen.tsx` : `loseHeart` (L173), dialogue hors-cœurs + recharge (L207-226), affichages (L145, L240-242) supprimés → la session se termine normalement.
- `src/screens/ProfileScreen.tsx:90` : `{gems} · {hearts}/5` → `{gems}`.
- `src/i18n/index.ts` : clés `statHearts/outHearts*/refillLbl` retirées des 3 dict.
- Tests : `gamification.test.ts:41-82` (hearts) supprimés.
- e2e : `verify.py::top_up_hearts` (L148-155) et flux « out of hearts » de `full_audit.py` supprimés/remplacés.
- ✅ Critère : `grep -ri hearts src/` = 0, tests + e2e verts.

**T0.2 · Store étendu (sans purge)**
- `AppState` + : `xpToday, goalDate, dailyGoal, freezeCount, weeklyXp, pendingChests, chestsOpened, moduleBest, trophies, league {tier, weekStart, botSeed}`.
- `revive()` : défauts des nouveaux champs, conservation de `xp/streak/sm2/...`.
- ✅ `store.test.ts` : cas « payload v1 contenant hearts » → récupéré sans eux, progression intacte.

**T0.3 · Routes & onglets**
- `router.ts` : `activeTab` whiteliste `path/practice/leagues`, `flash|quiz → practice`, `home → path`.
- `TabBar.tsx` : 5 entrées `path 📍 / practice 🏋️ / leagues 🏆 / modules 📚 / profile 👤` (clés `navPath/navPractice/navLeagues/navCourses/navProfile`).
- `App.tsx` : cases `path`, `practice`, `leagues` ; `#/home` → alias `#/path`.
- **Transitoire** : `PathScreen` = contenu de l'ancien `HomeScreen` (déplacé), `PracticeScreen` = hub minimal (2 cartes → flash/quiz), `LeaguesScreen` = classement minimal (T0.4).
- ✅ e2e/audit/check_design adaptés aux 5 nouvelles routes (Phase 0) et verts.

**T0.4 · Domain gamifié**
- `gamification.ts` réécrit : `addXp`, `goalProgress`, `buyFreeze`, `touchStreak` + congélation.
- Nouveaux : `path.ts` (`buildPath`, seuil 70), `trophies.ts` (12 trophées), `chests.ts`
  (`CHEST_TABLE`, `openChest(rand)`), `leagues.ts` (30/cohort, top 10, bottom 5, bots PRNG
  déterministe, rollover hebdo).
- ✅ Tests unitaires neufs : gamification (jour/lendemain/écart+freeze/objectif), path (déblocage),
  trophies (évaluation), chests (distribution + seed), leagues (rollover/promotion/relégation).

**T0.5 · i18n de base** — clés des onglets + squelettes dans fr/en/ar ; `check:i18n` + `i18n.test` verts.

### Phase 1 — Parcours vivant

**T1.1 · `PathScreen` réel** : en-tête (salutation, Medi, anneau XP, carte série + bouton
Congeler 200 💎, badge caisse), 15 unités × 6 nœuds (états/BOSS/décalage), bannières de module.
**T1.2 · `lessons.ts`** : `lessonQuestions(moduleId, level, ctx)` (§7 spec) + seed déterministe +
tests (90 leçons, 8 questions, options uniques).
**T1.3 · Quiz cadré** : `QuizScreen` accepte `?module=&level=` (pool cadré + titre d'unité),
retour `#/path` en fin de session.
**T1.4 · Récompenses** : `RewardPopup` (compteur XP, gemmes, ouverture de caisse) + détection
trophées à la fin de session ; `Medi.tsx` (4 moods, i18n).
**T1.5 · e2e Parcours** : rendu des 15 unités, clic nœud → session → fin → pop-up, verrouillage.

### Phase 2 — Révision, Ligues, Moi

**T2.1 · `PracticeScreen` complet** : 4 cartes (dues, quiz, faibles `#/flash/weak`, exam blanc
`?mode=exam`), mini-anneau, conseil Medi ; sessions dérivées des mêmes domaines.
**T2.2 · `LeaguesScreen` complet** : bandeau palier + semaine + compte à rebours, zones verte/
rouge, ligne TOI, XP animé ; rollover appliqué au chargement.
**T2.3 · `ProfileScreen` étendu** : grille 12 trophées, objectif quotidien 20/50/100, carte série/
congélations ; nettoyage final des restes cœurs.
**T2.4 · e2e/audit** : couverture des 3 nouvelles pages.

### Phase 3 — Design Duolingo + audits

**T3.1 · Styles** : tokens `--duo-*`, boutons chunky (ombre 4 px), bulles de leçon, bannières
d'unité, anneau, confettis, `prefers-reduced-motion`.
**T3.2 · Audits** : `check_design.py` étendu (nouvelles surfaces) ; `full_audit.py` vert ;
relecture responsive (360 px → desktop).

---

## LOT 2 — Android

### Phase 4 — Cœurs ↓ + fondations

**T4.1 · Migration DB v9→v10** : reconstruction de `user_stats` sans `hearts/heartsUpdatedAt/maxHearts`
(+ `xpToday, goalDate, dailyGoal, freezeCount, pendingChests`), table `trophies` — test de migration.
**T4.2 · Suppression du code cœurs** : `HeartsManager`, `OutOfHeartsDialog` (garder
`SuperPaywallDialog` sans cœurs), portes `MainActivity:282/296/345`, états/VM/repo, puce
`QuizScreen`, clés i18n ; `LeagueHeartsRegressionTest` réécrit.
**T4.3 · Domain** : port de `path/trophies/chests/leagues` en Kotlin + tests unitaires.

> ✅ **Phase 4 terminée (06/10)** — `:app:testDebugUnitTest` **161 tests verts** · `:app:assembleDebug` OK.
> - `MIGRATION_9_10` + `MigrationV10Test` (Robolectric) : cœurs retirés, 9 colonnes ajoutées,
>   tables `trophies`/`lesson_scores`, index de ligues recréés, données conservées.
> - Cœurs supprimés partout : `HeartsManager` supprimé, `OutOfHeartsDialog` → `SuperPaywallDialog`,
>   gates/`selectLevelAnswerWithHearts`/`onWrongAnswer` sortis de `MainActivity` + VM, puce cœurs
>   retirée de `QuizScreen`, `GEMS_HEART_REFILL`/`REFILL_HEARTS`/clés i18n cœurs supprimés.
>   Seuls restent le nom du composant `HeartsGemsTopBar` et des commentaires de migration.
> - Tests domain ajoutés : `ProgressionManagerTest`, `TrophyManagerTest`, `ChestManagerTest`,
>   `PathBuilderTest`, `GemsManagerTest` (freeze 200 💎), `LeagueHeartsRegressionTest` sans cœurs.
> - **Correction (web + Android)** : `moduleBest` ne participe plus au déblocage — uniquement à
>   l'affichage. Avant, une leçon à ≥ 70 % marquait *toutes* les leçons du module comme validées
>   (contraire à la spec §7). Corrigé dans `web-react/src/domain/path.ts` (+ test) et
>   `PathBuilder.kt` ; web re-vérifié : 85 tests, i18n 169×3, build, `verify.py`, `full_audit.py`
>   (95 clics / 0 échec), `check_design.py` (conforme).

### Phase 5 — Pages Android ✅ (code complet, compile)

**T5.1 · Navigation** : 5 onglets (`PATH/PRACTICE/LEAGUES/MODULES/PROFILE`) dans `MainActivity`.
**T5.2 · `PathScreen`** (miroir web) + quiz cadré module+niveau.
**T5.3 · `PracticeScreen`**, **T5.4 · onglet Ligues** (réutilise `LeagueScreen`),
**T5.5 · Profil** (trophées + objectif + série, i18n complète), **T5.6 · Medi + RewardPopup Compose**.
**T5.7 · i18n** : nouvelles clés ×3 dans `AppLanguage.kt` (toutes les clés utilisées vérifiées présentes).

> **Correction perf (ANR)** : 90 nœuds du parcours animaient chacun un
> `rememberInfiniteTransition` dans le scope de l'unité → invalidation continue de l'écran
> entier, renderer saturé (main thread bloqué en `syncAndDrawFrame`) → dialog « isn't
> responding ». Fix : `LessonNode` extrait en composable isolé, pulse **supprimé** (nœud
> suivant mis en avant par couleur/bordure), confettis du `RewardPopup` rendus statiques.
> Après fix : plus aucun ANR côté app (restants = watchdog `system_server` du faux-hôte,
> load 18), caisse/récompenses/navigation réactives.

### Phase 6 — Vérification finale 🟡 (appareil fait, relecture à venir)
`assembleDebug` ✓ · 161 tests unitaires ✓ · appareil (émulateur) :
migration v9→v10 conservée ✓ · 5 onglets ✓ · leçon → RewardPopup (+55 ⭐, +10 💎,
objectif ✓, trophées ✓) ✓ · caisse ×1 → +50 💎 (110→160) ✓ · Profil (grille 4/12,
objectif 55/50, freeze) ✓ · Ligues (BRONZE, 30 membres) ✓ · Modules ✓ ·
`logcat -b crash` vide ✓ · relecture des 2 rapports d'anglais médical laissés
en attente (revue native, hors périmètre).

---

## Ordre d'exécution immédiat
`Phase 0 (T0.1 → T0.5)` → vert → `Phase 1` → vert → `Phase 2` → vert → `Phase 3` → Lot 2.
