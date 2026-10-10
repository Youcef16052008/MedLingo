# RAPPORT FINAL — MedLingo, 2ᵉ revue (passe profonde)

**Périmètre** : 6 342 fichiers, ~19 000 lignes de Kotlin/TS/SQL analysées sur 20 rapports. Chaque conclusion ci-dessous a été **vérifiée deux fois** : une fois à la lecture, une fois par programme (grep/script/parseur) lors de la seconde revue.

**Build exécuté** : `./gradlew testDebugUnitTest` → **BUILD SUCCESSFUL** en 1 min 21 s, **164 tests, 0 échec, 0 ignoré, 0 erreur**. Le projet compile et sa suite de tests est intégralement verte — les défauts décrits ci-dessous sont donc des défauts de comportement et de contenu, **pas des défauts de compilation**.

---

## 0. Ce qu'est réellement MedLingo

Une **app Android Kotlin/Compose offline-first** (pas de permission `INTERNET`, pas de client HTTP, `userId = 1` mono-utilisateur) qui enseigne l'anglais/français/arabe médical à des étudiants PCEM1 algériens, avec **4 512 fiches trilingues** (étymologie, clinical pearl, mnemonic, IPA), un parcours Duolingo-like de 90 nœuds, des ligues, trophées, caisses, gemmes, SM-2, un **CAT adaptatif**, un studio de prononciation TTS, 4 notifications deep-linkées et un manifest PWA. **L'app compile et ses 164 tests sont verts.**

Trois plateformes coexistent (Android, `web-react` typé/testé, `web/` vanilla JS obsolète) et **trois économies de jeu différentes** (l'Android et web-react ont supprimé les cœurs ; `web/` les a encore ; le backend SQL les promeut).

---

## 1. Preuves recueillies lors de la 2ᵉ revue (vérifications par programme)

| # | Conclusion | Preuve |
|---|---|---|
| B1 | **Le matching est toujours compté correct** | `QuizScreen.kt` → `MatchingExerciseView(..., onComplete = { onSelectOption(currentExercise.correctAnswer, currentExercise.correctAnswer, points) })` — la réponse envoyée au VM est **toujours la bonne**, quelles que soient les paires cliquées |
| B2 | **La persona fictive est injectée en prod** | `UserStatsEntity.createDefault()` met `streakDays=12, level1Score=85, level2Score=75, totalPoints=2450` (commenté « demo persona ») ; **7 fallbacks `?: UserStatsEntity()`** dans le repository ; `UiState.userStats = UserStatsEntity()` par défaut |
| B3 | **Le seed n'est pas transactionnel** | `grep -c "@Transaction"` dans `MedicalDao.kt` = **0** ; `initializeDatabaseIfEmpty` enchaîne 5 insertions + 15 upserts sans transaction |
| B4 | **L'heure de rappel est perdue au reboot** | `BootReceiver.kt` appelle `scheduleDailyReminder(context, 20, 0)` — hardcodé, ne lit jamais `reminderHour/reminderMinute` |
| B5 | **10 modules sur 15 n'ont aucun exercice** | `grep module = ...` : Anatomie 25, Physiologie 12, Anatomie Patho 5, Terminologie 1, Sémiologie 1 = **44 exercices au total, 5 modules couverts** |
| B12 | **Permission micro absente** | Le manifest ne déclare que `VIBRATE`, `POST_NOTIFICATIONS`, `RECEIVE_BOOT_COMPLETED` — aucun `RECORD_AUDIO` alors que l'`AudioPronunciationStudio` est câblé |
| B18 | **`HomeScreen.kt` (1 076 lignes) est mort** | `grep -rln HomeScreen app/src` ne retourne **que lui-même** — aucun appelant. `LanguageSelector` n'est utilisé que par `HomeScreen` (mort) et `StatsProfileScreen` (vivant) → la langue est bien accessible, mais via le profil uniquement |
| B19 | **La barre de téléchargement est factice** | `downloadModule` fait `delay(120)` × 10 dans une boucle avec `downloadingModuleId` — simulation pure, alors que tout le contenu est déjà dans l'APK |

**Aucune de mes hypothèses critiques n'était infondée.** La 2ᵉ revure n'a pas non plus révélé de bug critique supplémentaire ; elle a en revanche **corriger deux de mes erreurs** (voir §5).

**Une divergence inattendue entre plateformes** : le front `web-react` — qui est un port fidèle des mêmes règles (XP, gemmes, SM-2, seuil 70 %, freeze 200 💎, 12 trophées) — **n'a ni le bug B1 ni le bug B2**. Le `matching` web compare bien la paire cliquée avant de la marquer correcte (`QuizScreen.tsx:543-555`), et son store repart toujours de `makeDefaults()` ou `revive()` — pas de persona fictive. Les deux défauts sont donc **spécifiques au portage Android**, et le code web peut servir de référence pour la correction.

---

## 2. Les 5 bugs à corriger en premier (P0)

1. **B1 — Matching triché** : 4 exercices sur 44 sont toujours validés, faussent le score et le déblocage des niveaux. **Correction** : `onComplete(errors: Int)` → `ExerciseChecker`.
2. **B2 — Persona en prod** : un nouvel utilisateur voit « 12 jours de série, 85 % au niveau 1, 156 termes appris » **avant** la première lecture de `user_stats` ; `recordLevelCompletion` peut ne jamais débloquer le niveau 3 (la persona a déjà `level2Score = 75 ≥ 70`). **Correction** : `createFresh()` neutre + `UiState.userStats: UserStatsEntity?` + état `isLoading`.
3. **B3 — Seed non transactionnel** : un process killed à mi-seed = base incomplète **à vie** (la garde `isDatabaseEmpty` n'est plus vraie au relancement). **Correction** : `@Transaction suspend fun seedAll()`.
4. **B4 — Rappel perdu au reboot** + **B10 icône de notification = mipmap** (carré blanc en status bar) + **B12 `RECORD_AUDIO`** (crash potentiel). Trois correctifs de robustesse Android, petits et indépendants.
5. **B5 — Leçons vides pour 10 modules** : l'écran affiche bien l'état « aucun exercice » (`no_exercises`) mais le nœud reste jouable-infiniment. **Correction** : rendre le nœud non-AVAILABLE quand le pool du module est vide.

---

## 3. Le vrai problème n'est pas le code — c'est le contenu

**44 exercices pour 90 leçons.** Ratio exercices/terms = 0,98 %. Aucun `reading`, aucun `clinical_case` (alors que les specs, le checker, la UI et les niveaux 5/6 existent). Un utilisateur termine le parcours entier en **~1 heure**.

Or **tout est déjà là pour générer le contenu** : 4 512 fiches avec `exampleEn`, `definitionEn`, `clinicalPearl`, `chapter`, `module` ; un script `tools_gen_seed_sql.py` qui prouve que la génération automatique est maîtrisée ; un validateur de traductions (`tools_check_tr.py`) de très bonne facture.

**L'action #1 du projet** : un générateur qui dérive ~1 800 exercices des fiches existantes (20/leçon) — mcq (3 distractrices du même module), matching (5 paires EN↔FR), wordbank (`exampleEn` découpé), fill (mot-clé masqué), reading (`clinicalPearl` comme passage), clinical_case (vignette + question). C'est mécanique, pas créatif, et ça transforme le produit.

---

## 4. Comparaison avec Duolingo — verdict honnête

**MedLingo n'est pas architecturée comme Duolingo et ne le sera pas en l'état.** `ARCHITECTURE_FINALE_100_DUOLINGO.md` affiche `[x]` sur 3 phases (économie, exercise engine, backend + Birdbrain + social). Vérifications :
- `grep -rln supabase app/src` → **0 résultat** ; `grep OkHttp|Retrofit|Ktor|HttpURLConnection app/src` → **0 résultat**.
- Le worktree `.kilo/mulberry-dart` **contient** `SupabaseClient.kt` + `ServerAuthoritativeLesson.kt` + `BirdbrainLite.kt` + `HeartsManager.kt` — la couche backend **a été écrite puis supprimée**. La décision offline-first est donc **délibérée**, et le document d'architecture décrit une **direction abandonnée**, pas l'état réel.

Ce qui est **conforme** et bien fait : parcours zigzag + seuil 70 %, ligues 10 paliers/30 membres/top 10/bottom 5, 12 trophées, caisses pondérées, objectif 20/50/100, freeze 200 💎, mascotte à moods, gemmes.

Ce qui est **supérieur à Duolingo** sur ce segment : corpus trilingue avec étymologie + IPA + pearls, **CAT adaptatif** (Duolingo a supprimé le sien public), offline **total** (crucial pour un réseau DZ), 180 clés i18n complètes AR/FR/EN avec chiffres arabo-indiens.

Ce qui est **inférieur** : 44 exercices vs des dizaines par leçon, ligues **figées** (les bots ne jouent jamais → pas d'urgence hebdomadaire), 2 illustrations pour 4 512 termes (app textuelle), pas de Birdbrain, pas de social, paywall qui **ne fait rien payer** (`purchaseSuper()` met `isSuper = true` gratuitement).

---

## 5. Corrections que j'apporte à ma propre première analyse

Deux de mes conclusions de la première passe étaient **partiellement fausses** ; la revue profonde les a invalidées :

1. **J'avais écrit que l'utilisateur pouvait enchaîner 90 leçons vides jusqu'au boss.** Faux : `QuizScreen` affiche un état dédié `no_exercises` **sans bouton « suivant »**, donc `recordLevelCompletion` ne peut pas être déclenché depuis l'écran vide. Le bug se limite à un nœud jouable qui ne progresse jamais — UX dégradée, pas un exploit. Je l'ai rétrogradé de critique à moyen.
2. **J'avais écrit que le sélecteur de langue n'était pas accessible.** Faux : `LanguageSelector` est utilisé par `StatsProfileScreen` (profil → langue). `HomeScreen` est bien mort, mais ce n'est pas lui qui portait le changement de langue. La langue est persistée en base (`selectedLanguageCode`) et lue au démarrage.

J'ai aussi **sous-estimé** deux choses : la solidité des migrations Room 8→9 (réattachement par clé composite avec ids négatifs temporaires — travail d'excellent niveau) et la qualité de `002_security_rls.sql` (durcissement SQL méthodique avec grants colonne-par-colonne).

---

## 6. Dette technique par ordre de coût

| Élément | Coût | Action |
|---|---|---|
| `version old but have react and vite/` (83 fichiers, ~5,2 Mo) | Clone + confusion | Supprimer (git conserve l'historique) |
| `.kilo/worktrees/mulberry-dart` (8,7 Mo, branche morte) | Confusion majeure (contient `SupabaseClient` !) | Archiver dans une branche, supprimer le worktree |
| `web/` vanilla (1 179 lignes, cœurs, README à 2 945 vs 4 512) | 3ᵉ plateforme divergente | Supprimer au profit de `web-react/` |
| 3,9 Mo de ressources mortes dans l'APK | +12 % de taille d'APK | `ic_brand_foreground.png` + 2 doublons + splashes → WebP |
| Namespace `com.example` | Identité, `BuildConfig` | Renommer en `com.aistudio.medlingua.dzmed` |
| ~10 artefacts catalog Gradle inutilisés | Constructions inutiles | Nettoyer `libs.versions.toml` |
| `HomeScreen.kt` 1 076 lignes mortes | Maintenance | Supprimer |
| 519 fiches `termFr == termEn` | Qualité pédagogique | Revue éditoriale (Levenshtein de `tools_check_tr.py`) |
| 655 traductions jamais relues (cases `[ ]`) | Qualité pédagogique | Revue par lots 42 chapitres |
| `mnemo` 97 % vide, `etym` 92 %, `pearl` 90 %, `exEn` 75 % | Contenu squelettique | Les champs narratifs qui distinguent MedLingo d'Anki existent à l'état d'ébauche |
| 15 classes CSS mortes dans `web-react` (~8 % du CSS servi) | Esthétique | `.vitals`, `.sm2-*`, `.hero`, `.shake` → restes d'écrans supprimés |
| Playwright non installé dans `web-react` | Filet de sécurité HS | `npm i -D playwright` — les 132 captures existantes ne sont pas rejouables |
| `terms.json` dupliqué en 3 endroits (9 Mo) | Dérive silencieuse | L'extraction est manuelle (`npm run extract`) après chaque changement des seeds |

---

## 7. Plan d'action complet

**P0 —Cette semaine** (bloque l'usage réel)
1. B1 matching triché (checker + pénalité d'erreurs) — **s'inspirer de `web-react/QuizScreen.tsx:543-555` qui est correct**
2. B2 persona en prod (valeurs neutres + `isLoading`) — **s'inspirer de `revive()` / `makeDefaults()` qui sont corrects**
3. B3 transaction sur le seed
4. B4 rappel au reboot + B10 icône notif blanche + B12 `RECORD_AUDIO`
5. B5 nœuds sans exercices non-AVAILABLE

**P1 — Ce mois** (le contenu est le produit)
6. **Générer ~1 800 exercices** depuis les 4 512 fiches (mcq/match/wordbank/fill/reading/clinical_case)
7. `getExercisesByModuleAndLevel` pour cadrer les leçons au module
8. **Animer les ligues** (bots qui progressent dans la semaine)
9. Brancher `ExerciseSpec`/`ExerciseChecker` dans le quiz (score partiel, normalisation AR, fuzzy)
10. Étendre les illustrations (Servier Medical Art CC BY 4.0 — 30 schémas ciblés)
11. i18n des 4 notifications

**P2 — Décisions produit à trancher**
12. **Cœurs** : réintroduire (cohérent avec `SuperPaywallDialog`) OU réécrire le paywall sans eux et corriger la doc
13. **Paiement** : Google Play Billing / BaridiMob, ou retirer le paywall (actuellement une fausse porte)
14. **Backend** : intégrer (Supabase SDK + sync + `INTERNET`) OU le marquer explicitement non branché
15. **Plateformes** : unifier autour d'Android + `web-react/`, supprimer `web/`

**P3 — Hygiène**
16. Renommer le namespace, nettoyer le catalog, mettre `composeBom` à jour
17. Supprimer `HomeScreen` mort, le worktree, l'archive, les ressources mortes (−2,4 Mo d'APK immédiatement)
18. **Réécrire `ARCHITECTURE_FINALE_100_DUOLINGO.md`** pour refléter le code (c'est la action documentaire #1)
19. Tests : ViewModel, Repository, CAT, TtsManager, **tests Compose UI** (0 aujourd'hui) — côté web aussi : 7 écrans sur 8 sans test, dont tout `QuizScreen`
20. CI : test de non-dérive du seed SQL, test des clés `Strings.get`, validation des traductions étendue aux 4 512 fiches. **Étendre le générateur de leçons** (`web-react/domain/lessons.ts`) : il prouve que 90 leçons jouables sont dérivables des 4 512 fiches — l'Android peut s'en inspirer plutôt que d'attendre 1 800 exercices écrits main

---

## 8. Verdict

**Ce projet est meilleur que sa documentation ne le laisse penser, et pire que son architecture ne le prétend.**

La couche de fond est solide : injection d'horloge partout, `TtsManager` qui gère réellement les races conditions Android, migrations Room dignes d'un senior, 164 tests où chaque assertion documente le bug qu'elle corrige, un validateur de traductions maison, un document (`IMAGE_STRATEGY.md`) qui **démontre les chiffres marketing non prouvés** avec une citation PubMed. Cette discipline est rare.

La faiblesse est ailleurs : **44 exercices pour 90 leçons**, des **mensonges involontaires** (persona affichée, fausse barre de téléchargement, fausse compatibilité backend, paywall gratuit), et **trois versions du même produit** qui divergent.

**La priorité n'est pas d'ajouter des fonctionnalités. C'est de combler le contenu, de corriger les 5 bugs P0, et d'aligner la documentation sur le code.** Une fois cela fait, MedLingo sera un bon produit — pas un Duolingo, mais quelque chose de plus adapté à son public : un outil d'anglais médical offline, trilingue et universitaire, ce que Duolingo ne sera jamais.

**Preuves d'exécution cumulées** : `./gradlew testDebugUnitTest` → 164 tests verts (Android) ; `npm run build` + `tsc --noEmit` + `vitest run` → 111 tests verts, build OK, typecheck strict OK (web-react) ; `check:i18n` → 153 clés × 3 langues, 0 écart.

---

### Rapports produits (20 parties + ce rapport final)

`RAPPORT_01_20_configuration_build.md` · `02_entree_manifest_di` · `03_couche_donnees` · `04_domain` · `05_contenu_terms_exercices` · `06_viewmodel` · `07_ui_screens` · `08_quiz_diagnostic` · `09_services` · `10_components` · `11_ressources` · `12_tests` · `13_i18n_profile_flashcards` · `14_backend_supabase` · `15_web_react` · `16_web_legacy_archive` · `17_outils_scripts` · `18_documentation` · `19_git_worktree_build` · `20_comparaison_duolingo` · **`RAPPORT_FINAL.md`** (ce document)
