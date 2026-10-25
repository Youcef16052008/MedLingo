# Revue profonde MedLingo — 20 analyses, findings, plan de test & verdict

**Date : 25/10/2026 · Périmètre : dépôt complet** (Android `app/`, web `web-react/`, backend `backend/`, pipeline de contenu, CI, sécurité dépôt).
**Méthode :** 5 investigations fondées sur lecture réelle du code (4 sous-audits spécialisés + 1 sweep sécurité incluant l'historique git complet), croisées avec des **vérifications personnelles ligne par ligne des bugs critiques** (greps ciblés, comptages sur les JSON, comparaison working tree vs commits).

**Ce qui a réellement été exécuté pendant cette revue :** `npx vitest run` (5086/5086 verts), `npm run check:stories` (OK), `npm run build` (OK), `python scripts/build_stories_corpus.py` (idempotence prouvée : diff git vide), test bout-en-bout du serveur de relecture (verdicts → publication → restauration), `git ls-files`/`git log --all` (sweep secrets), comptages Python sur `exercises.json`/`stories.v1.json` (working tree **et** HEAD), `git check-ignore`, inspection du manifest fusionné et du code KSP généré.
**Limites assumées :** aucune exécution sur émulateur/appareil Android (les findings Android sont de l'analyse statique + inspection du code généré) ; les Edge Functions Deno n'ont pas été déployées sur un projet Supabase frais (le BUG-010 est établi par lecture croisée migration 03 ↔ schéma 001/002) ; le navigateur n'a pas été piloté manuellement (les bugs web sont établis par lecture du code + vérification des données servies + `node -e "[...{}]"` pour le crash de coerce).

> **Conventions :** « committé » = code dans HEAD ; « WIP » = working tree non committé (régénération du corpus du 24/10). Les sévérités distinguent les deux quand ça change la conclusion.

---

# Partie I — Les 20 analyses

### 1. Architecture & injection de dépendances (Android)
DI manuelle (`AppModule`) simple, lisible, sans fuite de contexte — un choix assumé et sain à cette taille. Deux ombres : `ProgressionManager` duplique `GamificationEconomy` **et a déjà divergé** (BUG-024), et le bloc dépendances réseau/IA mort (Retrofit/OkHttp/Moshi/Firebase) fait acquérir `INTERNET` à une app offline sans aucun chemin de code réseau vivant (BUG-028).

### 2. Cycle de vie TTS (Android)
Contexte propre (application context, survie aux rotations), mais **deux défauts sérieux** : `stop()` dans le `finally` de `speakInternal` annule l'utterance qui vient d'être mise en file (BUG-004, critique — l'audio est structurellement cassé et le reste du code compense par des timeouts de 6 s), et l'état partagé `pendingCallbacks`/`engineGeneration` n'est pas thread-safe (BUG-023).

### 3. Room : schéma, migrations, seeds
**Point fort vérifié du projet** : migrations 6→14 réellement écrites et préservant les données (remapping par ids négatifs dans `MIGRATION_8_9`), schémas exportés et cohérents avec `version = 14`, zéro fallback destructif, seeds transactionnels avec restauration des bookmarks. Seule réserve : aucun chemin depuis v<6 (BUG-030, sans risque tant que `versionCode = 1`).

### 4. Concurrence & flux (Android)
Le défaut le plus lourd du dépôt Android : un collecteur Flow imbriqué dans un autre collecteur, jamais annulé, qui ré-écrit ~29 lignes à chaque émission Room → **boucle auto-entretenue d'écritures DB + accumulation de collecteurs** (BUG-005, critique). S'y ajoutent des read-modify-write `user_stats` non atomiques (mutex sur l'écriture seule, contourné par `StoryRepository`) → lost updates XP/gemmes (BUG-008), et une double source de vérité pour les trophées (BUG-031).

### 5. SM-2 — justesse algorithmique & parité
Implémentation lisible et bornée côté web. Côté Android, **le bouton « Hard » (quality 2) est traité comme un échec total** (`quality < 3` → repetitions=0, interval=1) : une carte « réussie mais difficile » revient chaque jour et l'easiness s'effondre (BUG-009) — déviation pédagogique majeure par rapport à SM-2/Anki, à comparer avec la version web pour la parité.

### 6. Économie de gamification
Socle complet (XP, série, gel de série, coffres, trophées, objectif). Trois défauts : `XpOutcome.freezeUsed/streakGained` codés en dur à `false` (BUG-025) ; le gel comble un écart **illimité** (1 gel pour 6 mois d'absence, BUG-019) ; `weeklyXp`/`goalDays` ne sont jamais remis à zéro si `leagueCohortId == null` (BUG-024).

### 7. Ligues & bots
Les ligues web sont **100 % client contre 29 bots déterministes** — cosmétiques mais honnêtes en V0 offline ; deux vrais défauts : le trophée `league_promoted` s'attribue aussi en **relégation** (BUG-020), et côté serveur `league_members.weekly_xp` n'a **aucun writer** : le leaderboard humain serait structurellement vide (BUG-017, avec le backend).

### 8. Machine à états des séances d'histoire
**Point fort vérifié** (web) : machine pure, actions invalides ignorées, verdicts idempotents, reprise après interruption, scénarios JSON partagés web/Kotlin. Un seul défaut réel : la race au montage sur `#/story/:id` si les 7,6 Mo d'histoires sont encore en vol → squelette éternel jusqu'à re-navigation (BUG-013).

### 9. Persistance web (localStorage)
`revive()` est exemplaire (assainissement champ par champ, purge versionnée) — **sauf une ligne fatale** : `reviewQueue` (persisté en tableau) est rechargé via `coerceRecord` qui rejette les tableaux → objet vide → `[...state.reviewQueue]` jette dans CHAQUE `update()` pour tout utilisateur revenant : plus aucune progression ne se sauve, silencieusement (BUG-001, critique — le plus grave du dépôt web).

### 10. Écrans & routage web
`PathScreen` appelle `buildPath()` **sans ses arguments de progression** (les `useMemo` deps prouvent l'intention) : le parcours n'affiche jamais une leçon terminée et les niveaux 2+ restent verrouillés à vie (BUG-002, critique). Par ailleurs `ReviewHubScreen` et `ChapterScreen` existent mais ne sont **pas routés** — un sous-système entier (cœurs, file de révision, chapitres) est débranché, avec `recordAnswer` dont le « signal de restart » retourne depuis la lambda interne (contrat mort, BUG-018).

### 11. Quiz & couverture des types d'exercices
Le corpus **committé** (112 exercices) est intégralement jouable (tous ses types ont un renderer — vérifié branche par branche). Mais le corpus **régénéré en WIP** (13 000 exercices, non committé) introduit le type `"gap"` : absent de `ExerciseType` et de tout renderer → 4 298 exercices injouables, 67/90 leçons touchées **dont la toute première leçon d'un nouvel utilisateur** (BUG-003, critique — bloque le commit du nouveau corpus). S'y ajoutent : crédit partiel exploitable par sous-chaîne (« the » → 0,5 point, BUG-026) et 8 592/13 000 exercices sans explication trilingue (BUG-027).

### 12. Pipeline de contenu (générateurs, verdicts)
**Point fort vérifié** : déterminisme réel, encodage UTF-8 exemplaire (zéro `open()` sans encoding), copies web/Android identiques octet à octet (sha256), application des verdicts de relecture correcte (y compris verdict partiel → reste draft). Deux vrais risques : `build_stories_p3` **écrit dans les deux copies dès l'import**, et le contrôle des verdicts orphelins peut `SystemExit` **après** l'écrasement → working tree avec 10 histoires et `check:stories` vert (BUG-006) ; écriture non atomique lue par le serveur de relecture à chaque requête (fusionné dans BUG-006).

### 13. Parité web/Android & checkers
`check:stories` (parité sémantique canonique) est un bon outil — mais **absent de la CI, qui de plus n'est pas commitée** (`.github/` est untracked : les workflows n'existent pas côté distant) (BUG-007). Angles morts des checkers : un corpus **vide** passe `check:stories` ; `check:seed` ne voit pas les collisions d'ids ; `check:i18n` détecte la « vie » des clés par coïncidence d'identifiants (BUG-033).

### 14. Harnais de tests — ce qu'il ne prouve pas
5 086 tests verts, et pourtant BUG-001/002/003 existent : aucun test ne fait le **roundtrip `revive` → `update`** avec un localStorage réaliste ; aucun ne monte les écrans avec un state rechargé ; aucun n'asserte que **chaque type du corpus servi a un renderer** ; le test pilote-001 contredit la règle « verdict autoritaire » qu'il commente lui-même (BUG-015). Leçon structurelle : les tests valident le domaine, pas l'assemblage.

### 15. Sécurité du dépôt & secrets
**Le domaine le plus propre du projet** (vérifié sur tout l'historique) : zéro secret committé (jamais aucun `.env`, keystore, `google-services.json`, `local.properties` à aucun commit), `.gitignore` couvrant les fichiers critiques, `service_role` confiné aux Edge Functions via `Deno.env`, manifest minimal (`allowBackup=false`, composants non exportés, pas de `RECORD_AUDIO`). Résiduel : hygiene `.gitignore` pour les untracked racine (partie de BUG-034), pas de CSP web (BUG-035).

### 16. Surface d'attaque locale (dashboard de relecture — mon propre code, revu à froid)
Bind 127.0.0.1, écriture sidecar atomique, validation des valeurs — mais : un payload JSON non-dict crashe le handler (AttributeError sans code HTTP), `Content-Length` illimité, et aucun check `Content-Type` → un site malveillant pourrait forger un POST cross-origine via formulaire `text/plain` (CSRF locale) (BUG-014).

### 17. Backend Supabase
**Pas de faille d'usurpation ni d'injection** : RLS sur 100 % des tables, zéro écriture anon, anti-usurpation systématique (`user.id !== user_id` → 403), CRON_SECRET fail-closed, vrais codes HTTP. Mais deux incohérences bloquantes : la migration de seed `03` référence une colonne `unit_id` **inexistante** dans le schéma → seed exercises échoue sur déploiement frais (BUG-010) ; et le client Android distant cible des tables/colonnes inexistantes (`user_progress`, `leaderboard.xp`) avec la clé anon en guise d'auth → synchro no-op silencieuse (BUG-011). L'économie serveur est décorative : cœurs jamais décrémentés, `weekly_xp` sans writer (BUG-017).

### 18. Performance & volumétrie
Démarrage web : **~24 Mo de JSON** avant `ready` (terms 4,4 + exercises 10,7 + stories 7,6 + chapters/lesson_map 1,8) — lourd pour la cible 3G revendiquée ; les 1,8 Mo de chapters/lesson_map ne servent que du code mort, promesse flottante sans `catch`, et non précâchés par le service worker qui précâche le reste (incohérence offline) (BUG-012). Android : la boucle d'écritures de BUG-005 bat la DB en continu.

### 19. i18n & RTL
Points forts réels (RTL appliqué avant premier rendu par script inline, arabe isolé `dir="rtl"` dans le contenu, `check:i18n`). Défauts : chaînes FR codées en dur dans `LeaguesScreen` (« Actif », « Bot », « TOI », « 🔥 0j ») et `ProfileScreen`, invisibles du checker (BUG-021) ; et « hier » calculé par soustraction de 24 h → casse au changement d'heure hors Algérie (fusionné dans BUG-019).

### 20. Préparation release & opérations
CI saine dans sa conception (`npm ci`, pas de `pull_request_target`, zéro secret) mais **non commitée** ; `lintDebug || true` masque les échecs ; release non signée silencieuse si keystore absent ; `debug.keystore` racine correctement ignoré (vérifié) ; build release durci (minify + shrink + `dependenciesInfo` retiré). Verdict op : rien n'est prêt pour un déploiement reproductible tant que BUG-007/029 ne sont pas réglés.

---

# Partie II — Rapport au format imposé

## 1. Executive summary

**Qualité globale : inégale mais saine dans ses fondations.** Le projet montre une discipline rare côté contenu (parité octet vérifiée, harnais de contenu 4 888 tests, encodage impeccable) et côté sécurité dépôt (zéro secret sur tout l'historique). Mais la revue a établi **5 bugs critiques confirmés** dont 3 qui cassent l'expérience cœur en production web : perte silencieuse de toute progression au rechargement (BUG-001), parcours bloqué à vie (BUG-002), et corpus régénéré injouable à 33 % (BUG-003) ; plus 2 critiques Android (audio TTS structurellement cassé BUG-004, boucle infinie d'écritures DB BUG-005).

**Sûr à publier ? Non.** La preuve la plus sévère : 5 086 tests verts **et** ces cinq bugs présents — l'assemblage (écrans + persistance + données servies) n'est pas testé, et la CI n'existe même pas côté distant.

**Risques les plus importants :** (1) tout utilisateur web revenant perd sa progression sans le savoir ; (2) le commit du corpus 13 000 exercices — clé de la valeur produit — est bloqué par le type `gap` ; (3) la boucle DB Android vide la batterie et sature l'UI pendant toute la durée de vie du ViewModel ; (4) le backend, s'il est déployé frais, ne se sème pas (migration cassée) et sa synchro Android est un no-op silencieux.

**Actions prioritaires :** P0 = BUG-001 à 007 (voir plan d'action §5) — ce sont des correctifs petits (1 à 5 lignes pour la plupart) mais bloquants.

## 2. Findings

> Sévérité : Critical / High / Medium / Low / Informational. Confiance : High = vérifié personnellement ou par lecture directe + données ; Medium = établi par lecture, impact dépendant d'un usage précis. Tous les findings ci-dessous sont des **bugs confirmés par preuve** sauf mention « risque » explicite.

---

- **ID:** BUG-001
- **Title:** Web : tout `update()` crashe après rechargement — perte totale et silencieuse de progression
- **Severity:** Critical
- **Confidence:** High (vérifié personnellement : code lu + `node -e "[...{}]"` → throw)
- **Category:** Logic / Reliability
- **Location:** `web-react/src/domain/store.ts:336` (revive) + `store.ts:394` (update)
- **Evidence:** `reviewQueue: coerceRecord(parsed.reviewQueue) as unknown as ReviewQueueItem[]` — or `coerceRecord` (store.ts:210) rejette les tableaux (`!Array.isArray(v)`) alors que `reviewQueue` est **persisté comme tableau** (push à store.ts:490). Au rechargement : `reviewQueue = {}`. Puis dans `update()` : `reviewQueue: [...state.reviewQueue]` → `[...{}]` → `TypeError: {} is not iterable`.
- **Problem:** La coercion est du mauvais type (record vs array). L'exception est jetée dans `update()` **avant** `recipe()`/`persist()`/`emit()` : aucune écriture n'atteint le localStorage.
- **Impact:** Tout utilisateur avec un localStorage non vide (tout utilisateur revenant) ne sauvegarde plus RIEN — XP, série, SM-2, trophées, ligues — silencieusement (l'exception dans un handler React ne casse pas l'arbre). C'est le bug le plus destructeur du dépôt.
- **How to reproduce:** (1) Ouvrir l'app, répondre à un quiz (état persisté). (2) Recharger la page. (3) Répondre à nouveau → `TypeError` en console, progression perdue au prochain rechargement.
- **Recommended fix:** Ajouter `coerceArray<T>(v, itemGuard)` et l'utiliser pour `reviewQueue` (et auditer `lessonProgress`/`heartManager` dont les entrées ne sont pas validées).
- **Corrected example:** `reviewQueue: coerceArray(parsed.reviewQueue, isReviewQueueItem) as ReviewQueueItem[],` avec `function coerceArray<T>(v: unknown, guard: (x: unknown) => x is T): T[] { return Array.isArray(v) ? v.filter(guard) : []; }`
- **Regression test:** `store.test.ts` : `saveState(populated)` → `revive()` → `update(s => { s.xp += 10 })` ne jette pas et `state.reviewQueue` est un tableau ( échoue avant le fix, passe après).

---

- **ID:** BUG-002
- **Title:** Web : `buildPath()` appelé sans ses arguments — parcours jamais terminé, niveaux verrouillés à vie
- **Severity:** Critical
- **Confidence:** High (vérifié personnellement)
- **Category:** Logic
- **Location:** `web-react/src/screens/PathScreen.tsx:86-88`
- **Evidence:** `const units = useMemo(() => buildPath(), [s.lessonBest, s.moduleBest]);` — `buildPath(lessonBest = {}, moduleBest = {})` (path.ts:34) prend ses défauts : `score = 0`, tout niveau ≥ 2 `locked`. Les deps du `useMemo` prouvent que l'appel args était prévu ; `path.test.ts:29` teste `buildPath(best, {})` correctement.
- **Problem:** L'écran ne passe jamais les meilleures performances ; `openNode` retourne si `locked` (PathScreen:108).
- **Impact:** Le cœur du produit (progression du parcours) est figé : « 0/6 · 0 % » partout, niveaux 2–6 inaccessibles, alors que `QuizScreen:339` écrit bien `lessonBest`.
- **How to reproduce:** Terminer une leçon au quiz, revenir au parcours : la leçon reste 0 %, le niveau 2 verrouillé.
- **Recommended fix:** `useMemo(() => buildPath(s.lessonBest, s.moduleBest), [s.lessonBest, s.moduleBest])`
- **Corrected example:** (une ligne, cf. ci-dessus)
- **Regression test:** Test composant : monter `PathScreen` avec un store où `lessonBest` contient un score → l'unité n'est pas `locked`.

---

- **ID:** BUG-003
- **Title:** Type d'exercice `gap` sans renderer — 4 298 exercices injouables dans le corpus régénéré (WIP), 1ʳᵉ leçon d'un nouvel utilisateur cassée
- **Severity:** Critical (bloque le commit du corpus 13 000 exercices)
- **Confidence:** High (vérifié personnellement : comptage JSON + branches lues)
- **Category:** Logic / Data
- **Location:** `web-react/src/types.ts:22-29` (`ExerciseType` sans `"gap"`) + `src/screens/QuizScreen.tsx:161-186` (aucune branche `gap`) + `public/data/exercises.json` (WIP : 4 408 mcq / **4 298 gap** / 4 294 pair)
- **Evidence:** Pour un `gap` : `setupQuestion` ne matche aucune branche → `opts=[]`, `sel=null` ; rendu dans la branche `else` « matching » aux colonnes vides ; `canCheck = q.sel !== null` → faux pour toujours. Le corpus **committé** (112 exercices, vérifié : mcq/sentence_order/fill_blank/clinical_case/matching/reading) est jouable — le bug se déclenche avec la régénération du 24/10 non committée. `e2e/verify.py:331` liste `gap` comme attendu (`needed = {"mcq","gap","pair"}`) : le type était anticipé côté tests, jamais implémenté côté UI. 67/90 leçons tirent un `gap` dans leurs 4 premiers exercices.
- **Problem:** Contrat de données non couvert par l'UI ; aucun test ne lie les types servis aux renderers disponibles.
- **Impact:** Session bloquée (bouton Vérifier désactivé à vie) dès la première leçon d'Anatomie L1 pour un nouvel utilisateur. Le commit du corpus — cœur de la valeur produit — est bloqué.
- **How to reproduce:** Servir le `exercises.json` du working tree, ouvrir `#/quiz/anat:1` (ou toute leçon en incluant un gap) → question vide, bouton désactivé.
- **Recommended fix:** Deux options : (a) implémenter le renderer `gap` (champ texte, comparaison via `normalizeAnswer`, crédit partiel existant) et ajouter `"gap"` à `ExerciseType` ; (b) re-générer le corpus en émettant du `fill_blank` (type déjà rendu). Recommandé : (a), le gap étant prévu par les e2e.
- **Corrected example:** Dans `setupQuestion` : `} else if (ex.type === "gap" || ex.type === "fill_blank") { … champ texte … }` + union `"gap"`.
- **Regression test:** Test de contrat : charger `public/data/exercises.json`, `new Set(types)` ⊆ ensemble des types gérés par `QuizScreen` (constante exportée) — échoue avant le fix, passe après.

---

- **ID:** BUG-004
- **Title:** Android : `stop()` dans le `finally` de `speakInternal` annule chaque utterance — audio TTS cassé structurellement
- **Severity:** Critical
- **Confidence:** High
- **Category:** Reliability / Logic
- **Location:** `app/.../service/TtsManager.kt:218-222` (+ `MedLinguaViewModel.kt:712-718` qui compense)
- **Evidence:** `tts?.speak(...)` (asynchrone) puis `finally { stop() }` exécuté dans la même milliseconde ; `stop()` fait `pendingCallbacks.clear()` ; `UtteranceProgressListener` n'override pas `onStop`. La conséquence est visible ailleurs : le ViewModel doit envelopper chaque speak dans `withTimeoutOrNull(6000)` avec le commentaire « No callback ever arrived ».
- **Problem:** La lecture est coupée avant de commencer ; les callbacks de complétion sont détruits ; la lecture continue est cadencée par un timeout de 6 s au lieu de la fin réelle de la voix (≈ 12 s par carte quoi qu'il arrive).
- **Impact:** La prononciation — fonctionnalité « Must » du produit — est instable/coupée ; lenteur artificielle massive en lecture continue.
- **How to reproduce:** Lancer une flashcard avec audio : la voix est tronquée ; enchaîner des cartes : ~12 s chacune.
- **Recommended fix:** Retirer le `stop()` du `finally` (il ne sert qu'au shutdown) ; override `onStop(utteranceId, interrupted)` pour résoudre les callbacks pending.
- **Corrected example:** `finally { /* ne rien faire : l'utterance doit vivre */ }` + `override fun onStop(utteranceId: String?, interrupted: Boolean) { resolvePending(utteranceId, interrupted) }`
- **Regression test:** Robolectric : `speak()` → `shadowOf(tts)` avance → `onDone` invoqué sans `onStop` ; assert le callback de complétion reçoit `true` (échoue avant le fix).

---

- **ID:** BUG-005
- **Title:** Android : boucle infinie d'écritures DB sur `league_members` + accumulation de collecteurs
- **Severity:** Critical
- **Confidence:** High (y compris inspection du `MedicalDao_Impl` généré : Flow ré-émis à chaque invalidation, pas de distinctUntilChanged)
- **Category:** Reliability / Performance
- **Location:** `app/.../ui/viewmodel/MedLinguaViewModel.kt:238-257` + `MedLinguaRepository.kt:557-559`
- **Evidence:** Un `launch { getLeagueMembers().collect { … persistBotAnimation(animated) } }` est créé **dans le collect** d'un autre Flow, jamais annulé ; `persistBotAnimation` fait `@Insert(REPLACE)` inconditionnel sur ~29 bots à chaque émission ; l'écriture invalide la table → ré-émission → écriture… Chaque émission du parent ajoute en outre un NOUVEAU collecteur.
- **Problem:** Cycle émission→écriture→invalidation→émission auto-entretenu, pendant toute la vie du ViewModel (dès `init`, écran ligues ouvert ou non).
- **Impact:** Batterie, CPU, usure flash, UI qui recompose en continu ; dégradation cumulative (collecteurs empilés).
- **How to reproduce:** Ouvrir l'app (le collecteur démarre à l'init), observer les écritures `league_members` en continu (Database Inspector / LogBuf).
- **Recommended fix:** Lancer le collecteur une fois au `init` (pas imbriqué) ; ne persister que si l'animation a réellement avancé (delta) ; `distinctUntilChanged()` sur le Flow.
- **Regression test:** Test ViewModel avec dao fake comptant les écritures : avancer le temps virtuel de 10 tick sans changement d'état → 0 écriture après la première (échoue avant : écritures perpétuelles).

---

- **ID:** BUG-006
- **Title:** Pipeline : écriture destructive à l'import + sortie d'erreur APRÈS écrasement → corpus réduit à 10 histoires avec `check:stories` vert
- **Severity:** High
- **Confidence:** High
- **Category:** Reliability
- **Location:** `web-react/scripts/build_stories_corpus.py:19` (import qui écrit) + `:551-559` (SystemExit orphelins) + `build_stories_p3.py` (aucun `main`)
- **Evidence:** `import build_stories_p3 as p3` réécrit web+Android avec 10 pilotes dès l'import ; ensuite `apply_verdicts` peut `raise SystemExit` (verdicts orphelins) **après** l'écrasement ; les deux copies tronquées étant identiques, `check:stories` reste vert. Seul vitest (≥ 804) le détecterait.
- **Problem:** Ordre validation/écriture inversé ; écriture non atomique (aussi lue par `review_server.py` à chaque requête → JSON à moitié écrit possible).
- **Impact:** Une session de relecture avec un sidecar sali peut laisser le working tree dans un état paraissant sain.
- **Recommended fix:** Valider le sidecar et bâtir TOUT le corpus en mémoire **avant** la première écriture ; écriture tmp + `os.replace` (déjà fait pour le sidecar).
- **Regression test:** Script de test : sidecar contenant un id inconnu → exécuter le builder → assert les deux fichiers inchangés (échoue avant).

---

- **ID:** BUG-007
- **Title:** CI inexistante côté distant (`.github/` untracked) et `check:stories` absent des workflows
- **Severity:** High
- **Confidence:** High (vérifié : `git status` montre `?? .github/`, grep des workflows)
- **Category:** Testing / Deployment
- **Location:** `.github/workflows/{android,web}.yml` (locaux seulement) ; étapes web : Install/Types/Tests/i18n/Seed/Build — pas de `check:stories`
- **Evidence:** Les workflows ne sont pas suivis par git : aucun CI n'a jamais tourné sur GitHub. Et même commités, une PR ne régénérant qu'une copie des histoires passerait verte.
- **Problem:** Garde-fous revendiqués (parité) non exécutés automatiquement.
- **Impact:** Les régressions de parité/contenu ne sont détectées que localement, par discipline.
- **Recommended fix:** `git add .github/` ; ajouter `npm run check:stories` à web.yml ; retirer le `|| true` de lint (cf. BUG-029).
- **Regression test:** (méta) — la présence du step dans le YAML + un commit de divergence en PR de test.

---

- **ID:** BUG-008
- **Title:** Android : read-modify-write `user_stats` non atomique — lost updates XP/gemmes
- **Severity:** High
- **Confidence:** High
- **Category:** Reliability
- **Location:** `MedLinguaRepository.kt:63-69` (mutex sur l'écriture seule) ; lectures hors lock aux lignes 332, 350, 385, 433, 449, 480, 610, 620, 651, 668 ; contournement total dans `StoryRepository.kt:112-120`
- **Evidence:** `userStatsMutex.withLock { dao.upsertUserStats(stats) }` protège l'écriture, mais l'état lu avant (`getUserStatsOnce()`) est déjà périmé si une autre coroutine a écrit entre-temps.
- **Problem:** Deux mises à jour concurrentes (ex. `submitFlashcardRating` pendant `recordLevelCompletion`) s'écrasent mutuellement.
- **Impact:** XP/gemmes perdus de façon intermittente — précisément le genre de bug qui détruit la confiance dans une app de gamification.
- **Recommended fix:** Étendre le mutex au couple lecture+écriture (`withLock { val s = dao.getUserStatsOnce(); … dao.upsertUserStats(s) }`), route `StoryRepository` par le repository central.
- **Regression test:** Test concurrent : lancer 50 `addXp` en parallèle → XP final = 50×xp (échoue avant).

---

- **ID:** BUG-009
- **Title:** Android/SM-2 : le bouton « Hard » (quality 2) est traité comme un échec total
- **Severity:** High (pédagogique)
- **Confidence:** High
- **Category:** Logic
- **Location:** `app/.../domain/sm2/SpacedRepetitionAlgorithm.kt:31-34` vs `ui/components/RatingButtons.kt:33`
- **Evidence:** `if (quality < 3) { newRepetitions = 0; newInterval = 1 … }` alors que le rating 2 est présenté à l'utilisateur comme « 😕 Hard » (rappel réussi mais difficile).
- **Problem:** En SM-2 classique (et Anki), « Hard » est un rappel réussi. Ici la carte est remise à zéro (repetitions→0, interval→1, EF −0,32).
- **Impact:** Les cartes jugées « dures » reviennent chaque jour indéfiniment ; l'easiness s'effondre vers le plancher ; l'utilisateur qui utilise honnêtement le bouton est puni. **À comparer avec `web-react/src/domain/sm2.ts` pour la parité** (risque de divergence de planification entre plateformes).
- **How to reproduce:** Noter une carte « Hard » → prochaine révision à J+1 avec repetitions=0.
- **Recommended fix:** Seuil d'échec à `quality < 2` pour « Hard » (ou migrer à 3 boutons Anki-style Again/Hard/Good avec mapping explicite 1/3/4).
- **Regression test:** `rate(card, 2)` → repetitions ≥ 1 et interval > précédent (échoue avant).

---

- **ID:** BUG-010
- **Title:** Backend : la migration de seed `03` référence une colonne `unit_id` inexistante → seed exercises échoue sur tout déploiement frais
- **Severity:** High
- **Confidence:** High (lecture croisée 001/002/03)
- **Category:** Data / Deployment
- **Location:** `backend/supabase/migrations/03_seed_content.sql:4671` vs `001_…sql:86-116` (table `exercises` sans `unit_id`)
- **Evidence:** `insert into exercises (id, lesson_id, unit_id, level, type, …)` — la colonne n'existe ni en 001 ni en 002 ; 002 n'en ajoute jamais.
- **Problem:** Sur `supabase db push` frais, la migration 03 échoue (`column "unit_id" … does not exist`) ; le backend actif a probablement été peuplé hors-migrations (`tools_gen_seed_sql.py`), ce qui masque le bug.
- **Impact:** Nouvel environnement = backend sans exercices → `session-generator` répond 404 (index.ts:193-197). Reproductibilité de l'infra compromise.
- **Recommended fix:** Régénérer 03 depuis le schéma réel (le générateur `tools_gen_seed_sql.py` existe) + test de déploiement frais en CI.
- **Regression test:** CI backend : `supabase db reset` sur un projet éphémère → exit 0 et `select count(*) from exercises` > 0.

---

- **ID:** BUG-011
- **Title:** Android : le client distant parle à des tables inexistantes avec la clé anon — synchro no-op silencieuse
- **Severity:** High
- **Confidence:** High
- **Category:** Data / API correctness
- **Location:** `app/.../data/remote/MedLingoRemoteClient.kt:71, 91, 112`
- **Evidence:** `post("rest/v1/user_progress", …)` et `get("rest/v1/leaderboard?…order=xp.desc")` — ni `user_progress` ni `leaderboard.xp` n'existent dans les migrations (la vue réelle est `leaderboard_current_week` avec `weekly_xp`) ; `Authorization: Bearer ${config.apiKey}` = clé **anon**, jamais un JWT utilisateur.
- **Problem:** Contrat client/serveur fantôme ; toute policy `auth.uid() = user_id` rejetterait de toute façon l'écriture.
- **Impact:** La « synchro » cloud est un no-op qui retourne false/null sans erreur visible. (Côté sécurité : personne ne peut écrire le score d'un autre — parce que rien ne s'écrit.)
- **Recommended fix:** Soit aligner le client sur le schéma réel + auth réelle (Supabase Auth) en V1, soit désactiver le code distant jusqu'à V1 (flag off) pour ne pas shipper un chemin mort.
- **Regression test:** Test d'intégration contre un schéma migré frais : chaque endpoint appelé par le client répond 2xx.

---

- **ID:** BUG-012
- **Title:** Web : ~24 Mo de JSON au démarrage, dont 1,8 Mo morts — promesse sans catch, précâchage SW incohérent
- **Severity:** High
- **Confidence:** High
- **Category:** Performance
- **Location:** `src/App.tsx:54` (`loadLessonData()` sans `.catch`, contrairement à `loadStories().catch` ligne 55) ; `public/sw.js:15-17` (SHELL précâche terms/exercises/stories mais pas chapters/lesson_map) ; `ChapterScreen.tsx` et `lessons.ts:343/462/538` = code mort
- **Evidence:** Tailles vérifiées : terms 4,4 Mo + exercises 10,7 + stories 7,6 + chapters/lesson_map 1,8 ≈ 24 Mo avant `ready`. chapters/lesson_map ne servent que des écrans/fonctions non routés.
- **Problem:** Poids de démarrage massif pour la cible 3G algérienne revendiquée ; rejet non géré ; offline cassé pour ces fichiers (précâchés non, consommés oui).
- **Impact:** Premier lancement lent/fragile ; PWA « offline-first » incohérente.
- **Recommended fix:** Supprimer le chargement des données mortes (et le code mort associé, cf. BUG-018) ; lazy-loader terms/exercises par module ; `.catch` partout.
- **Regression test:** E2E : blocage réseau après premier lancement → toutes les routes accessibles fonctionnent (échoue pour chapters/lesson_map avant).

---

- **ID:** BUG-013
- **Title:** Web : race au montage de `#/story/:id` — séance d'histoire en squelette éternel
- **Severity:** Medium
- **Confidence:** High
- **Category:** Reliability
- **Location:** `src/screens/StorySessionScreen.tsx:71, 104-105, 170`
- **Evidence:** `useEffect(() => { if (!story || initialized.current) return; … }, [])` — dépendances vides : si les 7,6 Mo d'histoires sont en vol à l'arrivée sur l'URL, l'effet s'exécute une fois avec `story === undefined` et ne rejouera jamais ; le rendu tombe sur le skeleton à vie.
- **Impact:** Lien direct/partagé vers une histoire pendant le chargement initial = écran mort (contournement : naviguer ailleurs et revenir).
- **Recommended fix:** Dépendance `[story?.storyId]` (l'effet rejoue quand l'histoire arrive) ou abonnement à un store de chargement.
- **Regression test:** Test composant : monter avec stories non chargées puis les injecter → la séance démarre (échoue avant).

---

- **ID:** BUG-014
- **Title:** Dashboard de relecture : crash sur payload non-dict, Content-Length illimité, pas de vérification Content-Type (CSRF locale possible)
- **Severity:** Medium
- **Confidence:** High (code revu à froid — c'est mon propre code, signalé honnêtement)
- **Category:** Security / Reliability
- **Location:** `web-react/scripts/review_server.py:161, 186-204`
- **Evidence:** Le `try` ne couvre que le parse JSON : un body `[1,2]` (JSON valide, non-dict) → `p.get` → `AttributeError` (connexion fermée sans code HTTP). `read(n)` sans plafond. Aucun check `Content-Type: application/json` : un site malveillant peut soumettre un form `text/plain` cross-origine vers `127.0.0.1:8799` (les POST JSON purs sont bloqués par preflight, pas les forms).
- **Impact:** Local-only donc surface réduite, mais un forgeron de verdicts via navigateur victime est plausible tant que le serveur tourne.
- **Recommended fix:** `if not isinstance(payload, dict): 400` ; `MAX_BODY = 1_000_000` avec 413 au-delà ; `if self.headers.get("Content-Type","").split(";")[0] != "application/json": 415` ; vérifier `Origin`/`Host` si présent.
- **Regression test:** `curl -X POST -H "Content-Type: text/plain" --data '{"storyId":…}'` → 415 (échoue avant : 500 ou verdict écrit).

---

- **ID:** BUG-015
- **Title:** Harnais : le test pilote-001 contredit la règle « verdict autoritaire » + asserts faibles
- **Severity:** Medium
- **Confidence:** High
- **Category:** Testing
- **Location:** `web-react/src/learning/__tests__/storiesContent.test.ts:72` (et lignes 39, 46)
- **Evidence:** Le test attend `clinical-symptoms-001` published **inconditionnellement**, alors que `apply_verdicts` (corpus.py:546-572) et le commentaire du test lui-même disent qu'un verdict négatif peut le retirer. Par ailleurs : `toBeGreaterThanOrEqual(804)` au lieu d'un compte exact, pas de vérification « 3 histoires/chapitre × 268 », `q.qFr`/`qAr`/`drill.prompt`/`targetPhrases.fr/ar` jamais vérifiés non vides, hint exigé seulement pour les yes/no nus (`["he does not have a fever"]` seul échappe).
- **Impact:** Un relecteur qui rejette le pilote → faux échec du harnais ; des régressions de contenu passent inaperçues.
- **Recommended fix:** Aligner le test sur l'invariant réel (verdict couvrant aussi 001) ; renforcer les asserts (exactement 804 = 268×3, champs trilingues non vides partout).
- **Regression test:** (méta : le test devient le garde) — injecter un verdict rejected sur 001 → le harnais doit rester vert avec 001 draft.

---

- **ID:** BUG-016
- **Title:** Pipeline : sidecar de verdicts malformé → traceback brut (JSON invalide ou entrée string au lieu d'objet)
- **Severity:** Medium
- **Confidence:** High
- **Category:** Reliability
- **Location:** `build_stories_corpus.py:551, 567`
- **Evidence:** `json.loads(VERDICTS_PATH.read_text())` sans garde ; `s["review"][dim] = v[dim]["verdict"]` → `TypeError` si un relecteur a édité à la main `"language": "approved"` (string) au lieu de l'objet.
- **Recommended fix:** Valider le format des entrées (dict avec `verdict` ∈ valeurs) avec message actionnable ; rejeter AVANT toute écriture (cf. BUG-006).
- **Regression test:** Sidecar `{"verdicts": {"x": {"language": "approved"}}}` → erreur claire, corpus inchangé.

---

- **ID:** BUG-017
- **Title:** Backend : économie serveur décorative — cœurs jamais décrémentés, `league_members.weekly_xp` sans writer (leaderboard humain vide)
- **Severity:** Medium (devient High en V1 ligues réelles)
- **Confidence:** High
- **Category:** Logic / Data
- **Location:** `backend/functions/session-generator/index.ts:163-169` (seule écriture de `hearts` = régénération) ; `league-cron/index.ts:82-91, 231, 247` (lit `weekly_xp`, le remet à 0, ne l'incrémente jamais) ; policies clients select-only (002:89-91)
- **Evidence:** `OUT_OF_HEARTS` (149-158) inatteignable puisque `hearts` reste 5 ; chaque semaine, seuls les bots (352-357) ont du XP. Le commentaire 001:124 « server-authoritative » ne correspond pas à la réalité.
- **Impact:** Toute la boucle de rétention « ligues » est cosmétique côté serveur ; le levier monétisation `is_super` (cœurs) est inopérant.
- **Recommended fix:** (V1) endpoint signé d'incrément `weekly_xp` (le cron recalcule alors pour de vrai) ; sinon documenter l'état « entraînement local » et ne pas afficher de classement prétendu global.
- **Regression test:** Intégration : deux sessions jouées → `weekly_xp` > 0 sans écriture client directe.

---

- **ID:** BUG-018
- **Title:** Web : sous-système débranché — ReviewHub/Chapter non routés, `recordAnswer` au contrat mort, 1,8 Mo de données mortes
- **Severity:** Medium
- **Confidence:** High
- **Category:** Maintainability / Logic
- **Location:** `src/App.tsx` (routes vérifiées : ni `#/review`, ni chapitres) ; `store.ts:450-467` (`return true` dans la lambda, pas de `recordAnswer`) ; `ReviewHubScreen.tsx`, `ChapterScreen.tsx` non importés dans App
- **Evidence:** Le `return true` documenté « signaler restart » sort de la recette `(draft) => void`, pas de `recordAnswer` (retourne toujours `false`). `startLesson`/`endLesson`/`addToReviewQueue`/`heartManager` : aucun appelant vivant.
- **Impact:** La « file de révision » annoncée n'existe pas à l'exécution ; maintenance confuse (le code semble vivant), poids mort au démarrage (avec BUG-012).
- **Recommended fix:** Décision assumée : soit router ReviewHub/Chapter (la fonctionnalité SM-2 « révision » est un pilier du plan V2), soit supprimer le sous-système et les données associées. Ne pas laisser les deux.
- **Regression test:** Selon la décision : e2e `#/review` fonctionne, ou `grep` CI que `chapters.json` n'est plus référencé.

---

- **ID:** BUG-019
- **Title:** Séries : « hier » = `now − 24 h` (casse au changement d'heure) et un gel comble une absence illimitée
- **Severity:** Medium
- **Confidence:** High
- **Category:** Logic
- **Location:** Web `src/domain/gamification.ts:69, 90, 97-103` ; Android `GamificationEconomy.kt:165` (même motif 24 h)
- **Evidence:** Au passage DST (jour de 23 h), `now − 24 h` tombe sur l'avant-veille entre 00:00 et 02:00 → série remise à 1 ou gel brûlé à tort. Et le gel (200 💎) comble 2 jours comme 6 mois (aucun comptage des jours manquants).
- **Impact:** Algérie (public cible) sans DST = épargnée aujourd'hui, mais l'UI est fr/en/ar et la France est touchée chaque dernier dimanche de mars. Économie du gel cassée.
- **Recommended fix:** Clés calendaires (`LocalDate`/`toLocaleDateString('fr-CA')` — déjà le format de `todayKey`) : `yesterday = todayKey(date - 1 jour civil)` ; gel = 1 jour d'absence chacun (≤ gelCount).
- **Regression test:** Horloge injectée : `now = lundi 00:30` après un jour de 23 h avec `lastStudy = samedi` → série préservée.

---

- **ID:** BUG-020
- **Title:** Ligues web : trophée « promotion » attribué aussi en relégation ; classement 100 % cosmétique affiché comme réel
- **Severity:** Medium
- **Confidence:** High
- **Category:** Logic
- **Location:** `src/domain/trophies.ts:79` (`tier !== "bronze"` → `league_promoted`) ; `leagues.ts:122-133` (bots déterministes, aucune réseau)
- **Impact:** Une relégation gold→silver débloque le trophée de promotion ; l'écran présente une « compétition » qui n'existe pas (aucun serveur — cohérent en V0 offline, mais l'affichage doit être honnête).
- **Recommended fix:** Comparer `tier` ancien vs nouveau (promotion seulement) ; libellé « Ligue d'entraînement » jusqu'au backend réel (BUG-017).

---

- **ID:** BUG-021
- **Title:** i18n : chaînes FR codées en dur, invisibles du checker
- **Severity:** Medium
- **Confidence:** High
- **Category:** i18n
- **Location:** `src/screens/LeaguesScreen.tsx:44, 149, 152` (« Actif », « Bot », « TOI », « 🔥 0j » durci à 0) ; `ProfileScreen.tsx:64` ; angle mort de `check-i18n.mjs:23-24` (regex `t("…")` uniquement)
- **Impact:** UI arabe/anglaise truffée de français ; le checker ne peut pas les voir.
- **Recommended fix:** Migrer vers `t()` + élargir le checker (linter no-react-intl ou grep CI des littéraux dans JSX).

---

- **ID:** BUG-022
- **Title:** Android : requête Room bloquante sur le main thread (`Application.onCreate` + `NotificationReceiver`)
- **Severity:** Medium
- **Confidence:** High
- **Category:** Performance
- **Location:** `MedLinguaApp.kt:26-29`, `NotificationReceiver.kt:79-84`, `MedicalDao.kt:147`
- **Evidence:** `getLanguageCodeBlocking()` (SELECT synchrone) dans `Application.onCreate` — première installation = création de base sur le chemin critique de cold start (violation StrictMode disk-read).
- **Recommended fix:** Langue dans SharedPreferences/DataStore (écrite au changement), Room en retrait.

---

- **ID:** BUG-023
- **Title:** Android : état TTS partagé sans synchronisation (`pendingCallbacks`, `engineGeneration` non volatile)
- **Severity:** Medium
- **Confidence:** High
- **Category:** Reliability
- **Location:** `TtsManager.kt:18, 48` — map lue/écrite depuis les threads binder (callbacks) ET le main ; `engineGeneration` incrémenté main, lu callback, sans `@Volatile`
- **Impact:** `ConcurrentModificationException` possible ; garde anti-onInit-tardif pouvant faillir (visibilité).
- **Recommended fix:** `ConcurrentHashMap` + `@Volatile` (ou confiner au main par `Handler(Looper.getMainLooper())`).

---

- **ID:** BUG-024
- **Title:** Android : `ProgressionManager` mort et divergent ; `weeklyXp`/`goalDays` jamais reset sans cohorte de ligue
- **Severity:** Medium
- **Confidence:** High
- **Category:** Maintainability / Logic
- **Location:** `domain/gamification/ProgressionManager.kt` (zéro référence — grep) ; `MedLinguaRepository.kt:500-548` (`return@withContext` si `leagueCohortId == null`)
- **Impact:** Duplication qui diverge déjà (`addXp` ≠ syncWeek) ; le reset hebdo réel dépend d'une condition de ligue — sans cohorte, `weeklyXp` ne revient jamais à zéro.
- **Recommended fix:** Supprimer la classe morte ; découpler le reset hebdo de l'existence d'une cohorte.

---

- **ID:** BUG-025
- **Title:** Android : `XpOutcome.freezeUsed/streakGained` codés en dur à `false` alors que la doc promet l'info
- **Severity:** Low
- **Confidence:** High
- **Category:** Logic / API contract
- **Location:** `GamificationEconomy.kt:118` vs doc l.24-25 ; `touchStreak` consomme réellement un gel (l.173-181)
- **Recommended fix:** Valoriser depuis `touchStreak`.

---

- **ID:** BUG-026
- **Title:** Web : crédit partuel exploitable par sous-chaîne triviale (« the » → 0,5 point)
- **Severity:** Low
- **Confidence:** High
- **Category:** Logic (exploitation client)
- **Location:** `src/domain/scoring.ts:14` — `user.length >= 3 && (user.includes(target) || target.includes(user))`
- **Impact:** Tout trigramme courant rapproche une demi-réponse sur les cibles le contenant (miroir assumé du `checkFill` Android — les aligner lors du fix).
- **Recommended fix:** Crédit partiel sur comparaison de mots (split/jaccard) ou préfixe tronqué, pas `includes` brut.

---

- **ID:** BUG-027
- **Title:** Contenu : 8 592/13 000 exercices sans explication trilingue — feedback vide 2 fois sur 3
- **Severity:** Low (Medium pédagogique)
- **Confidence:** High (comptage vérifié)
- **Category:** Data
- **Location:** `public/data/exercises.json` (WIP) : `expEn`/`expFr`/`expAr` absents pour 8 592 items ; bloc Explication de `QuizScreen:716-720` restera vide
- **Recommended fix:** Générer les explications (le générateur connaît le terme/chapitre) ou masquer proprement le bloc vide.

---

- **ID:** BUG-028
- **Title:** Android : dépendances réseau/IA mortes → permission `INTERNET` acquise sans usage + APK gonflé ; okhttp 4.10 (2022)
- **Severity:** Low
- **Confidence:** High
- **Category:** Maintainability / Security hygiene
- **Location:** `app/build.gradle.kts:100-110` ; `MedLingoRemoteClient` (HttpURLConnection) jamais instancié ; manifest fusionné : `INTERNET`+`ACCESS_NETWORK_STATE` via ces libs ; `HttpLoggingInterceptor` inutilisé
- **Recommended fix:** Retirer les deps non câblées (ou câbler réellement le client en V1) ; bump okhttp 4.12.

---

- **ID:** BUG-029
- **Title:** Release/CI : signature silencieusement absente si keystore manquant ; `lintDebug || true` masque les échecs
- **Severity:** Low
- **Confidence:** High
- **Category:** Deployment
- **Location:** `app/build.gradle.kts:50-53` (release non signée sans erreur) ; `.github/workflows/android.yml:33`
- **Recommended fix:** Fail-fast : exiger le keystore en CI release ; retirer le `|| true`.

---

- **ID:** BUG-030
- **Title:** Android : aucun chemin de migration depuis v<6
- **Severity:** Low (informational aujourd'hui)
- **Confidence:** High
- **Category:** Data / Deployment
- **Location:** `MedLinguaDatabase.kt:88-97` (migrations 6→14 seulement ; `app/schemas/` : 10..14)
- **Recommended fix:** Sans risque tant qu'aucune build ≤ v5 n'a circulé (versionCode 1) — à confirmer avant toute publication, sinon fournir 1→6 ou assumer destructive pour ces versions.

---

- **ID:** BUG-031
- **Title:** Android : trophées en double source de vérité (race d'affichage)
- **Severity:** Low
- **Confidence:** High
- **Category:** Logic
- **Location:** `MedLinguaViewModel.kt:366-368` (update manuel) vs collector Room l.263-267
- **Recommended fix:** Une seule source (le Flow Room).

---

- **ID:** BUG-032
- **Title:** Pipeline : `export_story_review.py` écrase les cases cochées des relecteurs à chaque régénération
- **Severity:** Low
- **Confidence:** High
- **Category:** Reliability (workflow humain)
- **Location:** `web-react/scripts/export_story_review.py:27-28, 105` — `check()` rend `- [ ]`, `write_text` écrase tout
- **Impact:** Nul à ce jour (aucune case cochée — vérifié), mais la première vraie session de relecture perdra sa progression à la première régénération. Le dashboard V0.1 remplace désormais ce flux — le script devrait fusionner l'existant (lire `[x]` avant d'écrire) ou être marqué déprécié.
- **Recommended fix:** Préserver les `[x]` existants par diff ligne à ligne.

---

- **ID:** BUG-033
- **Title:** Checkers : angles morts — corpus vide passe `check:stories`, collisions d'ids invisibles à `check:seed`, liveness i18n par coïncidence
- **Severity:** Low
- **Confidence:** High
- **Category:** Testing
- **Location:** `check-stories.mjs:33-39` (aucun minimum), `check-seed.mjs:81` (nombre seulement), `check-i18n.mjs:37, 47`
- **Recommended fix:** Minimums (`>= 800`), unicité d'ids dans le seed, matcher de clés plus strict.

---

- **ID:** BUG-034
- **Title:** Dépôt : pas de `.gitattributes` (bruit CRLF), scripts racine morts ou fragiles, untracked non ignorés
- **Severity:** Low
- **Confidence:** High
- **Category:** Maintainability
- **Location:** Aucun `.gitattributes` (vérifié) ; `tools_build_english.py:29/90` (`tmp_english_rows.json` absent → FileNotFoundError ; renumérotation dépendante de l'ordre du dump) ; `tools_align_points.py:118` (subprocess sans encoding → cp1252 sous Windows) ; untracked `tmp/`, `probe.txt`, `tools_*` non ignorés
- **Recommended fix:** `.gitattributes` (`* text=auto eol=lf` pour les sources) ; `.gitignore` étendu ; archiver les scripts morts.

---

- **ID:** BUG-035
- **Title:** Web : pas de CSP ; Google Fonts dans une PWA offline (requêtes tierces)
- **Severity:** Low
- **Confidence:** High
- **Category:** Security / Privacy
- **Location:** `web-react/index.html` (aucune balise CSP) ; fonts `fonts.googleapis.com`
- **Impact:** Faible aujourd'hui (SPA statique, rendu React sans `dangerouslySetInnerHTML` — vérifié par grep) ; une CSP préviendrait toute future XSS. Les fonts cassent l'offline et informent Google.
- **Recommended fix:** CSP `default-src 'self'` + self-host des fonts.

---

- **ID:** BUG-036
- **Title:** Backend (risques dormants) : purchases sans preuve de paiement, `correctAnswer` lisible via REST anon, cohorte non atomique, rate-limit in-memory par isolate
- **Severity:** Low / Informational (dormant)
- **Confidence:** High (lecture) — l'exploitation dépend de lecteurs futurs
- **Category:** Security
- **Location:** `002:97-98` (insert purchases self-service sans vérification — sans lecteur aujourd'hui) ; `002:76-78` + `03:11596` (`spec.correctAnswer` public par design) ; `league-cron:139-169` (check-then-insert non atomique → 2 cohortes possibles) ; `notifications:48-66` (Map par isolate, jamais purgée)
- **Recommended fix:** À traiter avant d'activer Super/ligues réelles : validation de paiement côté serveur, upsert de cohorte, rate-limit sur table.

## 3. Missing test coverage

1. **Roundtrip de persistance** : aucun test ne fait `persist → revive → update` avec un état réaliste (c'est l'angle mort qui a laissé passer BUG-001 — le plus grave).
2. **Contrat données ↔ renderers** : aucun test n'asserte que chaque `type` présent dans `exercises.json`/`stories.v1.json` est géré par l'UI (BUG-003).
3. **Écrans montés avec state rechargé** : les tests composants partent toujours d'un store vierge (BUG-002 invisible).
4. **Parité algorithmique web/Android** : les scénarios partagés couvrent les séances d'histoire, pas SM-2 ni l'économie (BUG-009/019 peuvent diverger silencieusement entre plateformes).
5. **Chemin critique offline** : aucune e2e « coupe le réseau après le premier lancement » (BUG-012).
6. **Concurrence Android** : aucun test de comptage d'écritures / de lost updates (BUG-005/008).
7. **Déploiement backend frais** : aucune CI `supabase db reset` (BUG-010).
8. **CSRF locale / payloads hostiles du review_server** (BUG-014).
9. **Chaînes hors `t()`** : le checker i18n est structurellement aveugle (BUG-021).

## 4. Test plan

**Unitaires**
- `store.test.ts` : roundtrip complet (état rempli → revive → update → persist → revive) ; `reviewQueue`/`lessonProgress` corrompus (types faux, entrées tronquées) → revive ne jette pas, types garantis.
- `path.test.ts` : `buildPath(lessonBest, moduleBest)` — niveau 1 complété → niveau 2 `unlocked` (miroir exact de l'écran).
- Contrat de types : `EXPECTED_TYPES` exporté par `QuizScreen`/`types.ts` ; test : `new Set(exercises.map(e => e.type)) ⊆ EXPECTED_TYPES` (sur le corpus committé ET le WIP).
- `sm2` : `rate(q=2)` = rappel réussi (intervue croissant) ; parité : mêmes entrées → mêmes sorties que le port Kotlin (scénarios JSON partagés, comme les histoires).
- `gamification` : horloge injectée sur un jour de 23 h (DST Europe) → série préservée ; gel = exactement 1 jour d'absence ; relégation ≠ promotion (trophée).
- `scoring` : « the » dans « the heart » ne rapporte pas 0,5.

**Intégration**
- Android : ViewModel + dao fake instrumenté — compter les écritures `league_members` sur 10 ticks sans changement (attendu : 0 après la première) ; 50 `addXp` concurrents → XP exact.
- Backend : `supabase db reset` éphémère en CI → migrations vertes + `count(exercises) > 0` ; `session-generator` avec JWT réel → réponse 2xx et `weekly_xp` incrémenté (après fix BUG-017).
- Pipeline : builder avec sidecar orphelin/malformé → corpus inchangé + message clair (déjà couvert partiellement par le harnais V0.1 — étendre au cas malformé).

**End-to-end**
- Parcours complet nouveau utilisateur (WIP corpus) : installer → 1ʳᵉ leçon (contenant un gap) → réponse → feedback → parcours mis à jour (échoue aujourd'hui sur 3 points : BUG-001/002/003).
- `#/story/:id` en lien direct pendant le chargement → la séance démarre (BUG-013).
- Mode avion après premier lancement → toutes les routes servis fonctionnent (BUG-012).

**Sécurité**
- `review_server` : POST `text/plain` cross-origin → 415 ; body non-dict → 400 ; body 10 Mo → 413 (BUG-014).
- REST anon : tenter `insert progress` d'un autre user → 403 (déjà vrai — le garder en test de non-régression) ; `update users.gems` → 403 (déjà vrai).
- Lint CI des littéraux JSX hors `t()` (BUG-021).

**Performance**
- Budget de démarrage web : poids JSON total < seuil (ex. 15 Mo après fix BUG-012) mesuré en CI sur les assets servis.
- Android : macrobenchmark cold start avant/après retrait de la lecture bloquante (BUG-022) ; trace d'écritures DB nulles au repos (BUG-005).

**Échec & récupération**
- localStorage quota dépassé / JSON corrompu → app démarre, reset propre, message utilisateur.
- Réseau coupé à chaque étape de chargement (terms/exercises/stories individuellement) → états d'erreur visibles, retry.
- Crash du TTS (onError) → la carte reste utilisable, audio désactivé avec bannière honnête (pattern déjà prouvé côté web).

**Régressions (une par bug ci-dessus)** — chaque finding des sections P0/P1 du plan d'action porte son test de régression dans sa fiche.

## 5. Prioritized action plan

**À corriger immédiatement (avant toute publication/commit de contenu) — P0**
1. BUG-001 (store roundtrip) — 1 ligne + tests roundtrip. *Sans cela, chaque utilisateur web perd tout.*
2. BUG-003 (renderer `gap`) — débloque le commit du corpus 13 000 exercices.
3. BUG-002 (`buildPath(args)`) — 1 ligne.
4. BUG-004 (TTS `stop()` finally + `onStop`) — cœur de la promesse audio.
5. BUG-005 (collecteur ligues) — batterie/DB.
6. BUG-006 (builder : valider avant d'écrire + écriture atomique).
7. BUG-007 (commiter `.github/`, ajouter `check:stories`, retirer `|| true`).

**Avant la prochaine release — P1**
BUG-008 (atomicité user_stats), BUG-009 (SM-2 Hard + parité), BUG-012 (24 Mo + données mortes), BUG-013 (race story), BUG-014 (durcissement review_server), BUG-015/016 (harnais + sidecar), BUG-017 décision ligues (affichage honnête), BUG-018 (décision router/supprimer le sous-système révision), BUG-019 (DST + gel 1 jour), BUG-021 (i18n), BUG-022 (main thread), BUG-010/011 (backend : seed + contrat client — au moins désactiver le chemin mort Android).

**À améliorer ensuite — P2**
BUG-020, BUG-023, BUG-024, BUG-025, BUG-027, BUG-028, BUG-029, BUG-032, BUG-033.

**Optionnel — P3**
BUG-026 (avec alignement Android), BUG-030 (à trancher avant versionCode 2), BUG-031, BUG-034, BUG-035, BUG-036 (devient P0 le jour où Super/ligues réelles s'activent).

## 6. Final verdict

**❌ Not safe to release (en l'état).**

Décision motivée en une phrase par plateforme :
- **Web** : trois critiques confirmés dont la perte silencieuse de progression de tout utilisateur revenant (BUG-001) — disqualifiant seul.
- **Android** : audio TTS structurellement cassé (BUG-004) + boucle d'écritures DB permanente (BUG-005) — l'app « fonctionne » visuellement mais dégrade l'appareil et trahit sa fonctionnalité phare.
- **Contenu/CI** : le commit du corpus régénéré est bloqué (BUG-003) et la CI n'existe pas côté distant (BUG-007).

**La bonne nouvelle est structurelle :** aucun de ces bugs n'est profond — les corrections P0 représentent quelques dizaines de lignes au total, les fondations (migrations Room, machine d'histoire, sécurité dépôt, harnais de contenu) sont solides, et le projet dispose déjà de l'outillage de vérification pour prouver chaque fix. **La trajectoire vers « safe to release » est de l'ordre d'une à deux journées de travail**, suivies de la campagne de relecture humaine déjà outillée.

---
---

# Partie III — Propositions : dépasser Duolingo et rendre MedLingo addictive (sans dark patterns)

## Ce que la revue change à la stratégie

Le plan `RAPPORT_COMPETITIF_ET_PLAN_2026.md` disait : « le problème est le dernier kilomètre ». La revue le précise : **il y a d'abord 1 à 2 jours de fondations (P0 ci-dessus), et l'un des risques d'addiction les plus graves est déjà là — au sens négatif** : une app qui perd la progression de ses utilisateurs (BUG-001) ou qui n'affiche jamais de parcours complété (BUG-002) est *anti-addictive* par construction. **La première fonctionnalité d'engagement, c'est la fiabilité.** Une fois P0 passé, l'arsenal d'engagement est déjà à 70 % construit dans le code — il est juste débranché ou cosmétique. Voici comment l'activer, par ordre de rendement.

## Les 8 leviers, fondés sur ce qui existe déjà dans le code

1. **Boucle de maîtrise visible (SM-2)** — le vrai différenciateur vs Duolingo. Aujourd'hui : `ReviewHub` et la file de révision existent mais ne sont **pas routés** (BUG-018). Action : router le hub, afficher par terme « force » et « prochaine révision J+3 » — la prévisibilité scientifique est addictive pour des étudiants en médecine (ils planifient leur EMD), là où Duolingo est opaque. Fixer BUG-009 (Hard=punition) est un prérequis : une économie de révision juste est une économie qui retient.
2. **Objectif quotidien + série résiliente** — `goalProgress`/`touchStreak`/gel existent déjà sur les deux plateformes. Action : écran d'engagement à l'onboarding (« 5 min/jour, léger »), gel honnête à 1 jour (fix BUG-019), calendrier de série visible (la « flame » Duolingo, en version médicale : un électrocardiogramme qui s'arrête si tu ne révises pas — identité verte médicale, zéro emprunt).
3. **Récompenses variables (coffres)** — `ChestManager` + `chests.ts` existent. C'est le mécanisme comportemental le plus puissant de Duolingo (variable reward). Action : cadencer les coffres sur l'objectif quotidien (coffre surprise à la 3ᵉ journée consécutive, contenu variable : XP, gel, thème d'icône « métier médical »), jamais achetables contre de l'argent.
4. **Ligues réelles** — l'infrastructure serveur existe (cohortes, cron fail-closed, RLS) mais `weekly_xp` n'a pas de writer (BUG-017) et le client Android parle à des tables fantômes (BUG-011). Action V1 : endpoint signé qui incrémente `weekly_xp` à la validation serveur des sessions → le leaderboard humain devient réel, le cron existe déjà. Jusque-là : libeller honnêtement « ligue d'entraînement ».
5. **Histoires à suite (cliffhangers)** — le corpus de 814 cas cliniques est l'actif que Duolingo n'aura jamais. Action : sérialiser par chapitre (le cas de `clinical-en-case-000` le lundi → « to be continued » le mercredi) — le retour programmé est le levier de rétention le plus propre pédagogiquement : l'étudiant revient pour savoir ce que le patient devient.
6. **Rappels contextuels (push)** — `notifications` function + `POST_NOTIFICATIONS` déjà demandés côté Android ; le protocole de relecture tourne autour de créneaux d'étude réels. Action : rappel unique quotidien paramétrable (jamais > 1/jour — non-goal du plan), déclenché sur l'heure habituelle d'étude observée localement, pas sur un agenda marketing.
7. **Preuves de maîtrise exportables** — certificat de chapitre (fixe le K1 du plan) : l'étudiant en médecine partage ses preuves au groupe Telegram de la promo → boucle sociale organique sans réseau social à modérer. `trophies`/`public_profiles` existent déjà côté schéma.
8. **Widget de série Android** — coût faible, rappel passif permanent, 100 % offline.

## Ce qu'on ne fera PAS (garde-fous du plan, confirmés par la revue)

Pas de cœurs punitifs bloquants (l'économie serveur de cœurs est de toute façon décorative — BUG-017 — autant la retirer du design que de la réparer), pas de PUB/achat de gel « anti-frustration », pas de notifications multi-quotidiennes, pas de streak-anxiety sans gel honnête, pas de clone visuel Duolingo. L'addiction visée est celle du progrès mesurable et de l'histoire clinique qui continue — cohérente avec la promesse « preuves, pas de temps passé ».

## Séquence recommandée (maj le TODO.md existant)

1. **J1–J2 : P0 de la revue** (ci-dessus §5) + campagne de relecture en parallèle (humain).
2. **J3–J5 : activer les leviers 1–3** (router ReviewHub, onboarding objectif, calendrier série, cadence coffres) — tout est déjà dans le code.
3. **Semaine 2 : pilote étudiant** (protocole prêt) avec les mesures K1/K3 — c'est lui qui dira quel levier mord vraiment.
4. **Semaine 3+ : ligues réelles (levier 4) + push (6)**, puis histoires sérialisées (5) et certificats (7) selon les données du pilote.

---

*Revue réalisée le 25/10/2026 — 5 audits croisés + vérifications personnelles ; 36 findings, dont 5 critiques, 8 élevés ; tests exécutés listés en tête de document ; second passage effectué (routes, renderers du corpus committé, service worker, cohérence backend). Aucun bug signalé sans preuve ; les « risques dormants » sont étiquetés comme tels.*
