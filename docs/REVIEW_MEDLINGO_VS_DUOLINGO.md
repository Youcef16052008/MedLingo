# MedLingo compared with Duolingo — independent product/code review

## Verdict

**MedLingo is a substantial medical-English learning prototype with a plausible specialist advantage, not yet a Duolingo-quality learning product.** Its strongest competitive idea is not copying the path, streaks or gems: it is helping French/Arabic-speaking medical students use English in their own curriculum and clinical contexts.

For that audience it could become more relevant than general-purpose Duolingo. The current repository does not demonstrate better learning outcomes, retention, conversational ability or production maturity. Content quality and integration need to catch up with feature breadth.

Recommendation: continue development, but prioritize a small, reviewed, reliable course over more gamification or generated volume. Not ready for an unrestricted public medical-education release on the evidence inspected.

## Scope and limits

- Root: `C:/Users/pc/Desktop/MedLingo`.
- Inventoried **all 666 initially tracked files**, with a per-file size/category inventory at `.planning/product-comparison/file-inventory.tsv`.
- Architecture spans Android Kotlin/Compose/Room, React/TypeScript web, dormant Supabase backend, corpus generators and review tooling, tests, CI and documentation.
- Read the current entry documentation, current product/review plans, core learning/progression/storage/story engines, representative Android implementations, routing/screens and test contracts. Structurally inspected the complete served JSON corpora and sampled content.
- This is **not** a claim of reading every line of every historical document or manually validating every medical statement. Dependencies, generated build/cache outputs, signing material and local credential/configuration values were excluded from content review. Binary media were inventoried, not exhaustively reviewed.
- Exercised the web app in isolated Chromium at 390 × 844; used screenshot review plus DOM geometry. Did not visually test the native Android app on a device/emulator.
- Duolingo comparison is based on public first-party material, not access to Duolingo's private code or a controlled side-by-side learner trial. Features vary by course/platform/subscription.
- No application source changed. Review notes, screenshots, logs, inventory and this report were added; ordinary builds updated ignored build outputs.

## What is actually present

| Asset | Verified state |
|---|---|
| Terms | 4,504 across 15 modules |
| Language fields | EN/FR/AR labels, definitions and examples are nonempty for all terms; field completeness is not proof of accuracy |
| Clinical pearls | 450/4,504 (about 10%) |
| Mnemonics | 153/4,504 (about 3.4%) |
| Stored web exercises | 13,000: 4,408 MCQ, 4,298 gap, 4,294 pair/free-response |
| Exercise explanations | All 13,000 have nonempty EN/FR/AR explanation fields |
| Chapter data | 268 chapter records and 2,306 lesson-map entries |
| Main visible path | 15 modules × 6 levels = 90 nodes, not a complete 2,306-lesson adaptive curriculum |
| Stories | 814 total: **813 draft, 1 published developer pilot** |
| Story approvals | `web-react/data/review-verdicts.v1.json` has an empty verdict ledger |
| Story parity | Android/web JSON copies identical; parity command passes |
| Leagues | Local simulated opponents, explicitly labelled as training/simulators |
| Cloud | Backend artifacts exist, but live authentication/sync/human competition were not demonstrated |

519 terms have the same French and English label. That merits a terminology review, but it is not automatically a defect: many medical words legitimately overlap.

## Comparison with Duolingo

| Dimension | MedLingo today | Comparison / judgment |
|---|---|---|
| Audience relevance | Algerian medical students, trilingual terminology and curriculum modules | Strong potential specialist advantage; Duolingo is broader rather than a medical curriculum |
| Learning structure | Flashcards, six-level paths, quizzes, chapter/review subsystem, story state machine | Good ingredients; multiple partially overlapping learning models are not yet one coherent journey |
| Personalization | SM-2, Android adaptive placement engine, local weakness/continuation heuristics | Useful foundation; not equivalent to a validated exercise-success prediction model such as Duolingo Birdbrain |
| Content | Large generated corpus, terms, examples, definitions; nearly all stories unpublished | Volume is ahead of editorial readiness; Duolingo describes research-guided course design and learner testing |
| Speaking/listening | Device/browser TTS and structured story listening; production/retelling largely guided or self-reported | TTS is not pronunciation assessment or responsive conversation; Duolingo offers speaking exercises and, on supported paid courses, Video Call/Roleplay |
| Motivation | Streaks, XP, gems, chests, trophies, mascot, simulated leagues | Familiar and functional, but no demonstrated real social network or retention evidence |
| Offline | Native local data and production web offline reload verified | A genuine strength for the target audience; avoid asserting an untested universal comparison with Duolingo offline behavior |
| Visual polish | Coherent clinical mint/green styling, recognizable navigation | Promising identity, but mobile overflow, dense header and very long path weaken focus |
| Reliability | Broad unit/content tests, migrations, parity checks and CI definitions | Better engineering foundation than a throwaway mockup; green tests still miss broken user journeys |
| Evidence | Pilot protocol exists; no completed learner-outcome dataset inspected | Duolingo publishes efficacy work; MedLingo cannot yet claim comparative learning effectiveness |

## High-priority findings

### 1. [High] Gap exercises leak the expected answer

**Evidence:** all **4,298/4,298** stored `gap` records end the English question with `→ <answer>`. Example: `Complete : 'Lower chamber of the heart that pumps blood out of the heart.' → Ventricle`, expected answer `Ventricle`.

The first Anatomy path lesson really serves this record. In French it displays the translated cue `→ Ventricule`, but still asks the learner to type an answer without clearly specifying the target language. In English the answer is directly visible. This is not a valid unassisted recall assessment.

Sources: `web-react/public/data/exercises.json`; `web-react/src/domain/lessons.ts:290-315`; `web-react/src/screens/QuizScreen.tsx:776-793`.

**Recommendation:** remove answer-bearing suffixes, specify target language, and create genuine blanks/context questions. Add a semantic corpus check for answer leakage, not merely nonempty question/answer fields. Audit the generated source pipeline before regenerating.

### 2. [High] Module EMD exam is not reachable through its advertised link

**Reproduction:** open `#/practice`, follow the advertised module exam link, or navigate to `#/quiz?mode=exam&module=semio`. The app returns to `#/quiz` and displays an invalid-lesson toast, not a 20-question timed module exam.

**Cause:** `QuizScreen.tsx:372` handles `f.module` before `f.mode === "exam"`. The nested module-exam branch at line 392 is unreachable when a module is supplied. The earlier branch expects a level, which exam links omit.

**Additional static issue:** `setupQuestion()` sets `examEndsAt: null` at line 216 and its result is spread after the deadline assignment in `beginExamEmd()`. Fixing routing alone would still overwrite the timer.

**Recommendation:** parse explicit session modes, prioritize exam routing, and preserve session-wide timer state separately from per-question state. Add a browser journey covering entry, count, countdown, expiry and results.

### 3. [High] The mastery panel ignores actual module quiz scores

`web-react/src/domain/mastery.ts:50` reads `input.moduleBest["quiz"]`. Real quiz results are saved under module ids, e.g. `anat`, at `QuizScreen.tsx:503`. `masteryOverview()` passes the full map without selecting each module's score.

**Reproduction:** `masteryOverview({anat:100,semio:20}, {}, [{id:"anat",termIds:[1]},{id:"semio",termIds:[2]}], now)` returns `quizBest: 0` and `force: 0` for both.

Existing unit tests use a synthetic `{quiz: ...}` key, so they verify the local formula but miss the integration contract.

**Recommendation:** supply an explicit per-module score or module id to the pure calculator, and test real persisted score shapes. Treat the percentage as a heuristic until calibrated against learner recall.

### 4. [High] Chapter generation and navigation do not form a dependable curriculum

The pure chapter generator `newLesson("anat-Angiologie", 0)` returns duplicate options, including duplicated correct answers. Some prompts ask for the French translation while the expected answer/options are Arabic. Padding a three-term pool to four options creates this problem.

Sources: `web-react/src/domain/lessons.ts:368-399`.

The chapter UI exists, but normal module cards link to flashcards (`ModuleCard.tsx:30`), and the main path uses six module-wide levels. In the chapter start route, `QuizScreen` records a chapter lesson and then calls the module-wide `beginLesson()`, rather than selecting the chapter generator. Its query parser accepts only levels 1–6, although the Angiology chapter exposes lessons beyond L6.

The chapter generator defects were reproduced through its actual browser module; the chapter routing mismatch is established by source tracing. It should not be advertised as a fully integrated chapter curriculum without additional end-to-end verification.

**Recommendation:** choose one lesson identity/model and wire navigation to the intended generator. Draw distinct distractors from a larger relevant pool, allow fewer choices when necessary, and generate language-specific prompts correctly.

### 5. [High] Story quantity should not be marketed as available learning content

Only one story is published, with both language/medical review still pending. It is documented as a developer pilot. There are zero approvals in the verdict ledger.

A generated draft example, `clinical-en-case-000`, discusses convalescence and quarantine as if they were clinical test results: “The convalescence is normal” / “the quarantine is not normal.” This is structurally valid JSON but weak, unnatural medical-English teaching material.

**Positive:** the loaders filter out drafts, and the review workflow separates language and medical approval. Keep that safeguard.

**Recommendation:** review and publish a small high-quality course. Require language and medical sign-off for the developer-pilot exception before a public release. Do not bulk-approve the corpus to improve coverage statistics.

### 6. [Medium] Rewards and certificates overstate mastery

In the verified fresh-user journey, completing one 8-question Anatomy L1 lesson at 100% immediately awarded “Maître de module.” `masteredModules()` uses a best module quiz score ≥90, without a coverage or delayed-retention requirement; the certificate uses that same signal.

Sources: `web-react/src/domain/trophies.ts:77,90-95`; `web-react/src/components/MasteryCertificate.tsx`.

A perfect small quiz is an achievement, not evidence of mastering all 412 Anatomy terms. Rewards should celebrate practice honestly; educational certification needs broader assessment.

### 7. [Medium] Mobile focus and layout need refinement

At 390px viewport width, the home document measured **402px wide** and approximately **9,716px tall**. Overflowing elements were locked path buttons. A screenshot review also identified a crowded repeated profile/stats header and dense weakness card.

The 90-node path asks users to scroll through all 15 subjects. The clinical daily pearl appears after the complete path, where few new learners will find it.

**Recommendation:** show the current module plus one recommended next action; collapse other modules; surface a small reviewed clinical insight near the action. Fix measured overflow and verify focus, contrast and screen-reader behavior separately. Do not infer that all below-fold content is inaccessible merely from a screenshot.

### 8. [Medium] Offline-first needs storage and delivery hardening

The three eagerly requested JSON files total **25,111,611 raw bytes**: terms, exercises and stories. This includes draft stories hidden from learners. All five corpus JSON files total 26,844,034 raw bytes. Those are file sizes, not measured compressed network transfer or field performance.

Sources: `web-react/src/data/load.ts:143-148`; `web-react/src/App.tsx:59`; `web-react/public/sw.js`.

Web state is stored in localStorage; persistence errors are silently ignored (`domain/store.ts:193-199`). Android explicitly disables platform backup in its manifest. No user-facing cross-device recovery or export flow was demonstrated. Clearing app/browser data can therefore be expensive for a returning learner.

**Recommendation:** serve only approved stories to learners, split/lazily load module content, measure on a low-end phone/slow network, and offer transparent backup/export or opt-in sync. Surface persistence failure instead of implying everything was saved.

## Engineering strengths worth retaining

- Native Android rather than only a web wrapper; Room persistence and migration tests.
- Mostly explicit domain logic, injected clocks and deterministic random generation.
- Story session state machine separates learning rules from presentation and records help/first attempts.
- Shared story corpus and parity checks; shared scenario fixtures for platform behavior.
- Corpus review tooling and draft-publication filtering.
- Trilingual data, RTL support and curriculum-specific modules.
- Local simulated leagues are honestly labelled in the current web UI.
- Core progression, reload persistence and offline web behavior genuinely worked in the inspected journey.

## Verification performed

| Check | Result |
|---|---|
| `npm test` | **5,114 passed, 26 files** |
| `npm run build` | TypeScript check and Vite production build passed |
| `npm run check:stories` | Passed, 814 story records with platform parity |
| `npm run check:i18n` | Passed, 233 dictionary keys per language; does not prove every literal is localized |
| `npm run check:seed` | Passed, 4,504 terms and 13,000 exercises |
| `./gradlew :app:testDebugUnitTest --console=plain` | Build succeeded; test task was **UP-TO-DATE**, so outputs were reused, not freshly executed |
| Existing Android XML test reports | 39 suites, 289 tests, zero failures/errors/skips; cached evidence |
| Web lesson | First Anatomy lesson completed, L2 unlocked, progress survived reload |
| Arabic | Document direction switched to RTL |
| Production offline | After online installation/cache, offline reload showed 90 path nodes and Anatomy flashcards worked |
| Browser runtime | No page exceptions in the sampled initial page walkthrough; usability/correctness bugs still present |
| Negative checks | Module EMD redirect, mastery score contract, chapter duplicate options and answer leakage reproduced |

**Important:** 4,888 of the 5,114 web tests belong to the story-content suite. Thousands of data-invariant cases are valuable, but are not thousands of independent end-to-end journeys. A syntactically complete corpus can still teach poor language. No full existing browser E2E suite, native-device usability test, clinical accuracy certification, fresh backend deployment or real student efficacy trial was run.

## Recommended priorities

1. **Repair the learning contracts:** gap answer leakage, module exam routing/timer, module mastery keying, and chapter selection/distractors. Add actual browser regression journeys.
2. **Ship one excellent course:** e.g. patient symptoms/history-taking with 20–30 reviewed short lessons, realistic examples and accurate feedback. A smaller reviewed product is preferable to a larger generated one.
3. **Unify the home journey:** continue lesson → focused weak-item practice → short clinical application. Keep modules/search available without presenting every path at once.
4. **Test with target students:** use the existing pilot protocol, measure first-session completion, delayed recall and transfer to unseen clinical phrasing. Five learners can expose usability problems, but cannot establish comparative efficacy.
5. **Then invest in growth:** better original art/audio, genuine optional class cohorts and reliable opt-in sync only after the core course works. Validate release signing, store/legal documents and medical educational disclaimers before public distribution.

## Strategic conclusion

Do not build “Duolingo, but with medical words.” Build **the medical-English companion that French/Arabic-speaking students trust because it teaches exactly what they need and shows honest progress**.

The defensible advantage is reviewed local relevance and clinical transfer. The next milestone should be evidence that learners can remember and use the material a week later—not another thousand generated exercises, another badge, or another retrospective plan.

## Duolingo sources consulted

- Teaching method, interaction, personalization and content design: https://blog.duolingo.com/duolingo-teaching-method/
- Birdbrain personalization and exercise-success prediction: https://blog.duolingo.com/learning-how-to-help-you-learn-introducing-birdbrain/
- Guided path, sequencing and spaced repetition: https://blog.duolingo.com/new-duolingo-home-screen-design/
- Speaking practice: https://blog.duolingo.com/covering-all-the-bases-duolingos-approach-to-speaking-skills/
- Max feature/platform/course availability: https://www.duolingo.com/help/what-is-duolingo-max
- Published efficacy overview (vendor evidence, not independent verification here): https://www.duolingo.com/efficacy
