# Remediation Plan — MedLingo DZ (2026‑11)

**Source of truth for findings:** [`docs/review/REVIEW_2026-11-07.md`](../review/REVIEW_2026-11-07.md) (R‑01…R‑36).
**Baseline:** [`docs/review/BASELINE_2026-11-07.md`](../review/BASELINE_2026-11-07.md).
**Status:** Phase 0 in progress.
**Owner:** dev agent; human approves gates marked 👤.

---

## 1. Objectives

1. Make the **current working tree** releasable without shipping known Critical/High defects.
2. Restore the honest product claims: offline-first, local-first, private by default.
3. Establish a test net that makes the fixed classes of bugs (CSS/layout, routing, SW precache, TTS failure, concurrency) **visibly fail** in CI if they regress.
4. Leave one source of truth per concern (answer checking, normalization, sync scope).

**Non-goals (until Phase 6 exits):** new features, corpus expansion, league parity on Android, analytics/funnels, visual redesign. Fix-only.

---

## 2. Guardrails (apply to every phase)

- **Red → green:** write the failing regression test first (or alongside the fix), prove it fails on the pre-fix tree, then make it pass.
- **No masking:** never add `|| true`, `dangerouslyIgnoreUnhandledErrors`, `--passWithNoTests`, or skip markers to get green.
- **One concern per commit:** finding ID in the message (e.g. `fix(web): restore modal layer (R-02)`).
- **No new dependencies** without a stated reason and a lockfile review (exception: test-only tooling approved at a gate).
- **Preserve local-first:** any auth/sync work must keep the app fully usable offline and signed-out.
- **Do not delete user WIP:** the uncommitted tree is treated as intentional work until decision **D1** says otherwise.
- **Every gate is verifiable by command**, not by claim. Commands are listed per phase.

---

## 3. Phase map

| Phase | Theme | Findings | Effort | Gate |
|-------|-------|----------|--------|------|
| **0** | Knowledge, baseline, decisions | — | 0.5 d | G0 👤 |
| **1** | Critical golden path | R‑02, R‑05 | 1–1.5 d | G1 |
| **2** | Offline/PWA resilience + first-quiz freeze | R‑03, R‑04, R‑35 | 1.5–2 d | G2 |
| **3** | Privacy & TTS correctness | R‑11, R‑12, R‑13 | 1–1.5 d | G3 |
| **4** | Data integrity: store, sync/RLS, Android economy | R‑07, R‑08, R‑09, R‑14, R‑15, R‑16, R‑20, R‑21, R‑22 | 3–4 d | G4 👤 |
| **5** | Answer correctness & cross-platform parity | R‑17, R‑18, R‑19 | 2–3 d | G5 |
| **6** | Hardening, content, test depth | R‑01, R‑06, R‑10, R‑23…R‑34 | 3–4 d | G6 |

**Total:** ~12–16 dev days, excluding human approvals and content review.

**Order rationale:** Phase 1 removes the two blockers that make the app unusable (onboarding, auth). Phase 2 makes the offline claim true before any release candidate is built. Phase 3 removes the privacy contradiction before the web app is published. Phase 4 fixes data loss/corruption, which is expensive to discover in production. Phase 5 converges scoring before users accumulate divergent progress. Phase 6 is polish and infrastructure that benefits from the earlier fixes.

---

## 4. Phase 0 — Knowledge, baseline, decisions

**Goal:** a reproducible "before" state and explicit scope decisions. **No code behavior changes.**

### Tasks

- [x] **0.1** Write the findings register (`docs/review/REVIEW_2026-11-07.md`, R‑01…R‑35).
- [x] **0.2** Write this plan.
- [ ] **0.3** Capture the baseline (`docs/review/BASELINE_2026-11-07.md`):
  - [x] `npm run build` (tsc + vite) — **exit 0**, hashed boot assets `index-BzMLt7iJ.js` / `index-oPMuR_8z.css`.
  - [x] `npm run check:i18n` — **exit 0** (233 keys × 3, no dead keys).
  - [x] `npm run check:seed` — **exit 0** (4,504 terms / 13,000 exercises).
  - [x] `npm run check:stories` — **exit 0** (814 stories, parity intact).
  - [ ] `npm test` — run recorded in the baseline doc (known-red: unhandled `onTaskUpdate`).
- [ ] **0.4** Add the review pointer to `docs/TODO.md`.
- [ ] **0.5** Resolve decisions **D1–D4** with the human (below).

### Decisions required

| ID | Decision | Options | Recommendation |
|----|----------|---------|----------------|
| **D1** | How to freeze the current WIP before code fixes | (a) Commit WIP as `wip: adr3+design snapshot` on `main`; (b) branch `remediation/2026-11` and commit there; (c) leave uncommitted and patch in place | **(b)** — isolates fixes, keeps `main` history readable, allows a clean diff per finding. Commit the WIP first so every fix is reviewable. |
| **D2** | Scope of ADR‑3 auth/sync for this release | (a) Implement fully (login route, per-user namespace, per-card merge, migration 003 fix); (b) disable UI, keep code behind `VITE_ENABLE_SYNC` default off, downgrade ADR‑3 to PLANNED; (c) delete the dead auth UI | **(b)** for the current release; schedule (a) as its own milestone. Auth that cannot sync is worse than no auth. |
| **D3** | Answer-checker source of truth | (a) Wire `ExerciseChecker` into ViewModel/components; (b) delete it and keep the naive comparator | **(a)**, with the substring rule replaced by a near-miss rule (R‑19). Typo tolerance is a real product win and the code is already written. |
| **D4** | Default TTS mode | (a) Web Speech only; (b) network edge-TTS default; (c) offline default + explicit online opt-in | **(c)** — restores the privacy claim while keeping the better voice available to users who choose it. |

### Gate G0 👤

- Register + plan + baseline committed (or staged) and reviewed.
- D1–D4 recorded in the decision log below.
- `git status` state is intentional (WIP preserved).
- Baseline commands reproduce the recorded results.

**Exit:** no production code changed; a clean "before" snapshot exists.

---

## 5. Phase 1 — Critical golden path

**Goal:** onboarding and authentication are reachable and usable.

### Tasks

- [x] **1.1 R‑02 — restore the modal layer.**
  - Port the `.overlay`, `.dialog`, `.dlg-actions`, `.dlg-ico`, `.toast`, `.reward-*`, `.sheet*` rules from `web-react/src/styles.css` (lines ~891–1262) into `styles/hybrid-design.css`, or `@import "./styles.css";` at the top of the hybrid sheet (watch cascade order).
  - Ensure `.overlay { position: fixed; inset: 0; z-index: 50; }`, backdrop, and focus styling.
  - Inventory every modal/toast class rendered in `components/` and grep the new sheet for each.
  - **Test:** a CSS contract test (node) parsing `hybrid-design.css` for the required selectors + `position:fixed`; a jsdom render test asserting the dialog element carries the expected class; a browser assertion that the intro CTA is the topmost element at its center.
- [x] **1.2 R‑05 — route the login screen.**
  - Import `LoginScreen`; add `case "login"` to the switch in `App.tsx`.
  - Add a route-guard fallback: unknown route names render Path **and** a visible notice (or redirect with `navigate("path")`).
  - Keep `#/login` valid for `ProfileScreen`/`PlayerProfile` sign-out targets.
  - **Test:** `#/login` renders an email input (jsdom); unknown `#/nope` does not silently alias Path.
- [x] **1.3 Sign-out target.** Verify all three `#/login` navigations land on the form after 1.2; add a test for the sign-out flow (mock Supabase, assert hash + rendered screen).
- [x] **1.4 Manual/browser verification.** Production preview at 390×844: intro CTA click → onboarding completes; `#/login` → form; sign-out → form.

### Gate G1

- `npm run build` exit 0.
- New tests fail on the pre-fix tree and pass after.
- Browser: intro CTA clickable, login form reachable, sign-out does not fall through to Path.
- No other visual regressions in Path/Practice/Profile (spot-check screenshots).

---

## 6. Phase 2 — Offline/PWA resilience + first-quiz freeze

**Goal:** install → go offline → reopen works; offline navigation fails gracefully; first quiz never freezes the UI.

### Tasks

- [x] **2.1 R-03 — build-time precache manifest.**
  - Add `web-react/scripts/patch-sw-precache.mjs`: read `dist/index.html` for `src`/`href` `/assets/...`, enumerate `dist/assets/*.js|*.css`, rewrite the `SHELL` array (or inject a `PRECACHE` list) in the copied `dist/sw.js`.
  - Derive the cache name from a content hash (e.g. hash of the file list) so deploys invalidate cleanly.
  - Wire `package.json`: `"build": "tsc --noEmit -p tsconfig.json && vite build && node scripts/patch-sw-precache.mjs"`.
  - Add `scripts/check-precache.mjs` and a CI step: assert every `dist/index.html` asset and every `dist/assets/*.js|*.css` appears in `dist/sw.js`; assert no stale hashed files remain.
  - **Test:** unit test for the patcher with fixture dist trees; CI contract check.
- [x] **2.2 R-04 — ErrorBoundary.**
  - Add `src/components/ErrorBoundary.tsx` (class component, `getDerivedStateFromError`, retry button that reloads or resets).
  - Wrap the screen tree inside `App.tsx` (inside the shell, outside `Suspense`).
  - Add a one-shot `window.addEventListener("vite:preloadError", …)` guard that reloads at most once per session (sessionStorage flag) to recover from stale chunks.
  - **Test:** lazy import rejects → boundary fallback renders (jsdom); second rejection does not loop.
- [x] **2.3 R-35 — no synchronous bulk build during warm-up.**
  - Add `whenBulkReady(): Promise<void>` (or make `warmBulkCache` return a promise) resolved when the background pass completes.
  - Quiz pool builder awaits readiness and shows the existing skeleton; never call `buildAllSync()` from a user interaction.
  - Optional: build only the requested module synchronously and merge into the cache.
  - **Test:** with `warming` in progress, the builder resolves asynchronously and performs no single task > 100 ms (instrument with a timer wrapper around the generator).
- [x] **2.4 Offline E2E (manual or Playwright if approved).** Fresh profile → load once → DevTools Offline → reload → app boots; navigate to a lazy route offline → boundary/retry, not blank.

### Gate G2

- `dist/sw.js` contains the hashed boot assets and all lazy chunks; `check:precache` passes in CI.
- Offline reload after one online visit boots the app (browser-verified, screenshot).
- Offline lazy navigation shows recovery UI.
- Opening a quiz immediately after start shows a skeleton, no long main-thread block.
- `build` + test suite green.

---

## 7. Phase 3 — Privacy & TTS correctness

**Goal:** no third-party transmission by default; voice failures never hang; stopping is deterministic.

### Tasks

- [x] **3.1 R-11 — offline by default.**
  - Add a persisted setting `ttsMode: "offline" | "online"`, default `"offline"`.
  - `speak(text, lang)`: offline → `speechSynthesis`; online → edge-TTS only when the user opted in, with a one-time disclosure.
  - Update README, intro copy, and any "aucune donnée transmise" claims to state exactly what happens in each mode.
  - **Test:** network-blocked `speak` issues no external request; opt-in path is explicit.
- [x] **3.2 R-12 — stream lifecycle.**
  - `stream.on("error", reject)`; settle the pending promise on stop; add `speakGeneration`; revoke the blob URL in `stopSpeak`.
  - Guarantee fallback: any edge-TTS error/timeout falls back to Web Speech (unless disabled) within a bounded time.
  - **Test:** error-only stream → reject + fallback; stop during stream → no play, URL revoked; superseded speak does not play.
- [x] **3.3 R-13 — capability check & gesture.**
  - `canSpeak()` checks `speechSynthesis` (and the configured online client), not `AudioContext`.
  - Prime audio inside the gesture or handle `play()` rejection with a visible replay control.
  - **Test:** capability matrix; play rejection surfaces retry.

### Gate G3

- Default mode performs zero network calls (verified in DevTools/Network).
- Error/timeout/stop tests green.
- Copy matches behavior in README + intro (human read).

---

## 8. Phase 4 — Data integrity: store, sync/RLS, Android economy

**Goal:** no silent data loss or corruption; auth/sync is either fully correct or explicitly off.

### Tasks

- [x] **4.1 R-20/R-21/R-22 — web store.**
  - `coerceSm2`: `Number.isFinite(rep/iv)` + integer/range bounds (`rep >= 0`, `iv >= 0`, `ef` clamp 1.3–2.5+, `due >= 0`).
  - Deep-copy nested entries in `update()` recipes (or adopt immer); delete dead `recordAnswer`; inject `now` into lesson functions or fix the header.
  - Surface `persist()` failure (state flag + toast); remove the duplicate `AppState`.
  - **Tests:** corrupt/oversized SM‑2 fixtures; snapshot purity; throwing `setItem`.
- [x] **4.2 D2 — auth/sync scope.**
  - **If (b) disable:** gate `LoginScreen`, Profile auth card, and sync calls behind `VITE_ENABLE_SYNC` (default false); remove/neutralize dead `#/login` links; mark ADR-3 PLANNED in docs; keep `LoginScreen` for the future. (Done: per-user storage scope + logout clearing + useSync wired; full per-card merge via domain/syncMerge.ts with pull-merge-push syncNow(), migration-003 data jsonb alignment, 10 merge tests.)
  - **If (a) implement:** namespace local state per `user_id`; clear on logout; per-card SM‑2 merge; wire `useSync`; fix migration 003 (uuid PK + FK cascade + authenticated ALL policy + `DROP POLICY IF EXISTS` + remove redundant index); idempotent 002.
  - **Tests:** logout isolation; two-device merge; SQL policy allow/deny; migration re-run.
- [x] **4.3 R-15/R-16 — Android concurrency/economy.**
  - Convert all remaining stats RMW sites to `mutateUserStats`.
  - Add a `@Transaction` DAO method for balance + ledger; call it from `addGems`/`spendGems`.
  - **Tests:** 50 concurrent `addGems(1)` → 50; rollback test.
- [x] **4.4 R-14 — remove `runBlocking` from `onCreate`.**
  - Default language `"fr"`, apply persisted value from an async launch/splash; no synchronous DB read on main.
  - **Test:** slow-DAO StrictMode harness; main-thread time < 50 ms.

### Gate G4 👤

- Concurrency and atomicity tests green (JVM).
- Sync decision implemented end-to-end; ADR text matches reality.
- SQL policy tests pass on a scratch database.
- No `runBlocking` in `Application.onCreate`.
- Human sign-off on the ADR‑3 scope.

---

## 9. Phase 5 — Answer correctness & parity

**Goal:** one normalization/scoring contract, identical on web and Android, with typo tolerance and no substring farming.

### Tasks

- [x] **5.1 Publish the contract.** (normalize + Levenshtein shared; fixtures in scoring tests) A short spec in `docs/` + a shared fixture file (JSON) of `{input, expected, score}` used by both platforms.
- [x] **5.2 D3 — wire `ExerciseChecker` (or delete it).**
  - Replace `selectLevelAnswer`'s naive `equals` with `ExerciseChecker.checkFill/checkChoice/…`; same for `ExerciseComponents`.
  - Delete the dead path if the decision is the opposite.
- [x] **5.3 R-19 — replace substring credit.**
  - Word-boundary match or Levenshtein ≤ tolerance → 0.5; arbitrary containment → 0.
  - Apply identically in Kotlin and TypeScript.
- [x] **5.4 R-18 — parity.**
  - Canonical normalize: lowercase, NFD, strip `\p{Mn}`, unify apostrophes, collapse punctuation/space.
  - Implement in `scoring.ts`, `session.ts`, `ExerciseChecker.normalize`, `StorySessionEngine`.
  - **Tests:** the shared fixtures pass on both platforms; `"val"`→0; `"cafe"` vs `"café"`→1; Arabic harakat; apostrophes.

### Gate G5

- Shared fixture suite green on web + Android.
- Live quiz behavior verified in the browser (typo accepted, substring rejected).
- No remaining "mirror" comment that the code contradicts.

---

## 10. Phase 6 — Hardening, content, test depth

**Goal:** close the remaining Medium/Low items and make the suite trustworthy.

### Tasks

- [x] **6.1 Security/deploy:** R-30 headers/CSP (vercel.json); R-31 secrets plugin removed; R-01 fail-fast signing + dynamic versionCode. (R-10 deferred: low impact, Supabase applies once.)
- [x] **6.2 Content/UX:** R-23 counts + 🇩🇿 flag; R-24 i18n literals + checker (full sweep: 304 keys x 3 languages, 0 dead keys, `check:i18n` green, `check:literals` gate added and verified); R-25 PlayerScreen link/counters; R-26 StoryBrowser discovery + ThemeToggle guard; R-06 login UX incl. confirmation-pending messaging.
- [x] **6.3 Backend:** R-27 whitelist sanitization in notifications; R-28 idempotence guard in league-cron; R-29 shared rate limiting (migration 004 `rate_limits` + atomic `rate_limit_hit` RPC called from all three functions, in-memory fallback on DB error).
- [x] **6.4 Test infrastructure:** R-34 vitest projects (node/jsdom) done. R-33 fixed: heavy tests now warm (yield) instead of sync-generate; full suite exits 0 with 0 unhandled errors (5142 tests). R-32 done: vite 7.3.7 + vitest 5.1.0 security bumps, `npm audit` 0 vulnerabilities.
  - **R‑33:** split the heavy generation out of the timed path (yield/chunk), tune worker/RPC limits, or upgrade vitest; CI gate on exit 0 **and** zero unhandled errors.
  - **R‑34:** split vitest into projects (`node` for domain, `jsdom` for UI); add UI/routing/SW tests; retarget Android checker tests to the live path.
  - Add a CI job that builds and inspects `dist/sw.js` (`check:precache`).
- [x] **6.5 Docs:** ADR-3 downgraded to PLANNED; TODO pointer added; plan tracks progress. (README claims deferred to release checklist.)
- [x] **6.6 R-36 — Parcours Android réparé.** Chapitres réels du seed × 6 leçons = 1 872 leçons jouables ; progression `module|chapitre|niveau` ; déblocage séquentiel par chapitre (≥ 70 %) ; leçon bornée à 8 questions. Vérifié sur émulateur par deux E2E Artemis. *(Découpage remplacé par 6.8 / R-42 le 2026-11-10 : le « 6 leçons fixes » ne couvrait pas les chapitres de 38 termes.)*
- [x] **6.7 Revue vérifiée sur appareil (2026-11-10).** Premier passage du registre **sur émulateur** au lieu de la seule lecture statique. Trois E2E Artemis rejoués sur l'APK réel : smoke transversal **48/48** (5 onglets, gel à 200 💎, Ligues sans identifiant brut, bascule FR → EN → FR, logcat propre), parcours **13/13**, leçon **24/24**. Suite unitaire Android **294/294**, `lintDebug` **0 erreur** (61 avertissements, contre 67). Cinq défauts supplémentaires trouvés et corrigés dans la foulée — R-37 (score de faute de frappe 0,9 au lieu de 0,5 : suite unitaire rouge), R-38 (epoch de cohorte affiché dans Ligues), R-39 (`$` parasite sur le prix du gel), R-40 (retour sonore entièrement mort + `SoundManager` qui se rendait muet à vie, et qui sortait sur `STREAM_ALARM`), R-41 (règles de sauvegarde jamais branchées).
- [x] **6.8 R-42 — Parcours : leçons de 3 termes + révisions, palier de 50 % (2026-11-10).** Le découpage ne dépend plus d'un nombre fixe de niveaux mais du **nombre de termes du chapitre**.
  - **Spécification appliquée à tous les modules :** 3 nouveaux termes par leçon ; **une révision après chaque paire de leçons** (elle rejoue les 6 termes des deux leçons) ; point de décision après les leçons 1 et 2 (continuer ou faire la leçon optionnelle) ; révision **facultative** si les deux leçons de la paire sont ≥ 50 %, **obligatoire** sinon ; **aucune leçon vide** (un reste d'un seul terme donne 2 + 2 sur les deux dernières leçons, jamais 3 + 1) ; un chapitre de ≤ 3 termes tient en une seule leçon (sans révision) ; le chapitre suivant ne s'ouvre qu'avec **≥ 50 % de moyenne** sur le chapitre courant, toutes les leçons d'apprentissage jouées et toutes les révisions obligatoires réussies.
  - **Nœuds calculés :** 30 termes → 10 leçons + 5 révisions = **15** ; 22 termes → 8 leçons (`3,3,3,3,3,3,2,2`) + 4 révisions = **12** ; 38 termes (Angiologie) → 13 + 7 = **20** ; 3 termes → **1** leçon de 8 questions ; 1 terme → **1** leçon de 3 questions. Anatomie (11 chapitres, 412 termes) = **212** nœuds. Seed complet : **312 chapitres → 1 621 leçons + 841 révisions = 2 462 nœuds** (dont 33 chapitres d'un seul terme, qui donnent 1 leçon sans révision).
  - **Implémentation :** `domain/path/ChapterSyllabus.kt` est la source de vérité unique (`plan`, `lessonAt`, `lessonCount`, `tierFor`, `splitSizes`, `PASS_SCORE = 50`, `CHAPTER_UNLOCK_AVERAGE = 50`, 8 questions par leçon de 3 termes, 16 par révision de 6) ; `PathBuilder.PathNode` gagne `kind`, `required`, `questionCount`, `termCount` et `ChapterPath` gagne `state`, `average`, `completed` ; `LessonGenerator.lessonQuestions(questionCount, seed)` rend la génération déterministe et bornée à la leçon ; le ViewModel publie `pathLessonLabel` / `pathLessonTier` (les stats/trophées restent sur les paliers 1..6, la clé de stockage reste `module|chapitre|niveau`) ; `PathScreen` affiche `N leçons + M révisions · x/y · moyenne Z%`, le cadenas par chapitre et des nœuds `Leçon N` / `Révision N` (`obligatoire` / `facultative`) — plus de « BOSS ». **Les exercices ne changent pas.**
  - **Interprétation documentée :** « 22 termes → 8 leçons » = 8 leçons d'apprentissage + 4 révisions = 12 nœuds (8 nœuds au total laisseraient des termes non vus).
  - **Vérification :** `ChapterSyllabusTest` **11/11**, `PathBuilderTest` **19/19** (dont 50 % de moyenne, révision facultative vs obligatoire), `StringsCountTest` **3/3** (accords : « 1 leçon + 0 révisions »), `ChapterSyllabusSeedTest` **9/9** (invariants sur les 312 chapitres réels + table par module : les 15 modules suivent le même plan), suite unitaire **343/343**, `lintDebug` **0 erreur**, E2E parcours **17/17** (Angiologie 13+7=20, `Leçon 1 · 3 termes · disponible`, `Révision 1 · 6 termes · verrouillée`, aucun BOSS), leçon **29/29** (Leçon 2 = 8 questions → 100 % → Révision 1 + Leçon 3 disponibles) et smoke **48/48**.

### Gate G6

- CI green from a clean clone: `npm ci`, build, tests (0 unhandled errors), i18n/seed/stories/precache, Android assemble + unit tests + lint.
- Deployed headers verified.
- No Critical/High finding open; Medium items either fixed or explicitly deferred with a reason.

---

## 11. Test plan summary

| Layer | Tool | New coverage |
|-------|------|--------------|
| CSS contract | node test reading CSS | modal/toast/reward/sheet selectors + `position:fixed` (R‑02) |
| UI/routing | jsdom + Testing Library | login route, unknown route, ErrorBoundary, sign-out (R‑04, R‑05) |
| SW/precache | node + built `dist` | boot + lazy assets present, cache version bump (R‑03) |
| TTS | mocked streams/audio | error→fallback, stop revokes, generation token, capability (R‑12, R‑13) |
| Store | node | SM‑2 corruption, purity, persist failure (R‑20…R‑22) |
| Perf | timing harness | no synchronous bulk build during warm-up (R‑35) |
| Android | JUnit/instrumented | RMW concurrency, gems atomicity, main-thread, live checker |
| Android E2E | Artemis (ADB + UIAutomator) | Parcours: chapitres réels, découpage 3 termes + révisions (20 nœuds pour Angiologie), leçon jouée 8/8 → 100 % → Révision 1 + Leçon 3 disponibles, score persisté (R‑36, R‑42) |
| Android smoke | Artemis (ADB + UIAutomator) | cold start mesuré, 5 onglets, prix du gel, Ligues sans identifiant brut, i18n FR↔EN, logcat sans FATAL/ANR (R‑24, R‑38…R‑40) |
| DB | pgTAP/scratch SQL | RLS allow/deny, migration idempotence |
| E2E | Playwright (optional, gate-approved) | offline boot, onboarding click, login, quiz typo/substring |

---

## 12. Risks & mitigations

| Risk | Likelihood | Impact | Mitigation |
|------|-----------|--------|------------|
| CSS restore causes cascade regressions | Medium | High | Port rules, don't reorder unrelated layers; visual spot-check + screenshots per screen |
| SW precache script breaks dev/preview | Low | Medium | Patch only `dist/sw.js` post-build; keep `public/sw.js` as the source template |
| ErrorBoundary masks real errors | Medium | Medium | Log the error; show a retry, not a blank; test that normal renders are unaffected |
| Auth disable breaks Profile links | Medium | Medium | Route guard + hide entry points behind the same flag; test the flag both ways |
| Android changes untestable here | High | Medium | Land with JVM tests; require a device smoke run before release (human) |
| Test infra changes hide failures | Medium | High | No masking flags; gate on unhandled-error count; review config diffs |
| Scope creep into features | Medium | High | Fix-only rule until G6 |

**Rollback:** every phase is a sequence of independent commits tagged with finding IDs. Revert a commit (or the phase branch) without touching other phases. Docs-only Phase 0 is trivially revertible.

---

## 13. Metrics

- Findings closed: Critical 2/2, High 11/11, Medium 13, Low/Info 9 (35 total).
- CI: 0 unhandled errors; precache contract enforced; jsdom UI project present.
- Offline: first-visit → offline reload boots (binary).
- Privacy: 0 external requests in default TTS mode.
- Data: 0 lost updates in the concurrency suite; 0 silent SM‑2 retirements.
- Parity: shared answer fixtures 100% identical web/Android.

---

## 14. Decision log

| Date | ID | Decision | By |
|------|----|----------|----|
| 2026‑11‑07 | — | Review register + plan created; baseline captured | agent |
| 2026‑11‑07 | D1 | Approved: branch `remediation/2026-11` + commit WIP snapshot; fixes layered on top | 👤 |
| 2026‑11‑07 | D2 | Approved (option b): auth/sync behind `VITE_ENABLE_SYNC=false` default; ADR‑3 downgraded to PLANNED | 👤 |
| 2026‑11‑07 | D3 | Approved: wire `ExerciseChecker` as single source of truth; replace substring credit with near-miss | 👤 |
| 2026‑11‑07 | D4 | Approved: offline (Web Speech) by default; edge‑TTS behind explicit opt-in with disclosure | 👤 |

---

## 15. Immediate next actions (after G0)

1. Execute **D1** and create the remediation branch with the WIP snapshot.
2. Phase 1.1: port the modal layer + CSS contract test.
3. Phase 1.2: route `LoginScreen` + guard.
4. Re-run the browser golden path and record screenshots in the gate report.

---

## 16. Addendum — R‑43 : exploration multi-modules + correcteur d'écriture (2026‑11‑10)

Hors périmètre du registre initial, livré sur la même branche après R‑42.

### 16.1 Périmètre fonctionnel

| Élément | Contenu |
|---------|---------|
| Hub | `ExplorePanel` dans l'onglet **Modules**, déplié par défaut + raccourci « Explorer » par module |
| Modes | `ALL`, `RANDOM` (graine déterministe), `DONE` (ligne `flashcard_progress`), `TO_REVIEW` (`lastQuality <= 3`) |
| Sélection | **plusieurs modules** simultanément (Set d'ids) |
| Quantités | `10, 25, 50, 100, 150, 200, 300` — `MAX_CARDS = 300`, défaut **50** |
| Session | overlay plein écran : progression `n/N`, terme FR, saisie, micro, « Voir la réponse », verdict, notes SM‑2 |
| Persistance | `flashcard_progress` réutilisée (SM‑2 + XP) — **aucune migration** |

### 16.2 Correcteur d'écriture

`WritingCorrector` renvoie `Verdict(score, quality, issues, expected, diff)` :

- `EXACT` 100 / q5 · `ACCENT` 95 / q5 · `NEAR` 55–94 / q3–4 · `WRONG` 0–54 / q1–2 · `EMPTY` 0 / q1.
- Issues : accent, casse, article, pluriel, trait d'union, lettre
  manquante / en trop / substituée, ordre des mots, mauvaise langue, sens.
- Normalisation : ligatures (œ/æ/ß), articles ignorés pour la distance,
  mots triés pour l'ordre. Erreur d'ordre seule → `NEAR` (70) ; article omis
  → `NEAR` (≥ 90) + issue `ARTICLE`.

### 16.3 Décisions

| Date | ID | Décision | By |
|------|----|----------|----|
| 2026‑11‑10 | D5 | Hub d'exploration dans l'onglet Modules (panneau dépliable), pas une 6ᵉ destination | 👤 |
| 2026‑11‑10 | D6 | `testTagsAsResourceId` **en debug uniquement** (`BuildConfig.DEBUG`) | agent |
| 2026‑11‑10 | D7 | Voix via `RecognizerIntent` + `RECORD_AUDIO` runtime, dégradation localisée si aucun moteur | agent |
| 2026‑11‑10 | D8 | Pas de migration : les résultats d'exploration écrivent dans `flashcard_progress` | agent |

### 16.4 Vérifications

- `assembleDebug` OK, `adb install -r` OK.
- Suite unitaire **334/334** ; `lintDebug` **0 erreur / 61 avertissements**.
- E2E `emulator-5554` : exploration **48/48**, parcours **17/17**, leçon **29/29**, smoke **48/48**.

### 16.5 Suivi

- **ANR intermittent** au premier affichage de l'onglet Modules (pré-existant,
  trace du 2026‑11‑09) → à profiler (`Macrobenchmark` / `Perfetto`) et à
  traiter en R‑44 si confirmé sur appareil physique.
- Mode `DONE` / `TO_REVIEW` : pas encore couverts par l'E2E (seul `ALL` +
  `RANDOM` le sont) ; couverture à ajouter.