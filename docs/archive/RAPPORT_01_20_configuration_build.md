# PARTIE 1/20 — Racine, build Gradle, configuration, README, doc d'architecture

**Périmètre analysé** : `settings.gradle.kts`, `build.gradle.kts`, `gradle.properties`, `gradle/libs.versions.toml`, `app/build.gradle.kts`, `metadata.json`, `local.properties`, `.env.example`, `.gitignore`, `README.md`, `ARCHITECTURE_FINALE_100_DUOLINGO.md`, `docs/superpowers/*`.
**Méthode** : lecture intégrale des fichiers listés.

## Observations factuelles

### Build Gradle
- `settings.gradle.kts` — un seul module `:app`, `rootProject.name = "MedLingua DZ"`, pluginmanagement canonique avec `foojay-resolver-convention 1.0.0` (résolution auto des toolchains JDK). `FAIL_ON_PROJECT_REPOS` activé — bonne pratique.
- `app/build.gradle.kts` :
  - `namespace = "com.example"` mais `applicationId = "com.aistudio.medlingua.dzmed"`. **Divergence d'identité** : le namespace (qui détermine le package des classes générées, notamment `R` et `BuildConfig`) reste `com.example`, tandis que le code source vit dans `com.example` (cohérent) et le README/package officiel est `com.aistudio.medlingua.dzmed`. La commande `adb shell am start -n com.aistudio.medlingua.dzmed/com.example.MainActivity` du README confirme cette séparation.
  - `compileSdk = 36`, `minSdk = 24`, `targetSdk = 36`, `versionCode = 1`, `versionName = "1.0"`. minSdk 24 = Android 7.0 (couvre ~98% du parc, bon choix pour de l'offline-first DZ).
  - **JDK 17** mais toolchains non déclarées : `compileOptions` fixe Java 17 sans `kotlin.jvm.toolchain`. Compte tenu d'AGP 9.1.1 (qui requiert JDK 21+ pour *lancer* Gradle), c'est fonctionnel mais non reproductible : la version du JDK utilisée dépend de la machine.
  - Signature : `release` lit `KEYSTORE_PATH`/`STORE_PASSWORD`/`KEY_PASSWORD` depuis l'env, fallback `my-upload-key.jks` (inexistant — donc par défaut la release n'est **pas** signée, `signingConfig` n'est appliqué que si le keystore existe). `debugConfig` pointe `debug.keystore` (présent, 2666 octets, **non commité** — `.gitignore` le exclu : un développeur frais doit le régénérer).
  - Release active `minify` + `shrinkResources` + `proguard-android-optimize.txt` + `proguard-rules.pro` (22 lignes).
  - `testOptions { unitTests { isIncludeAndroidResources = true } }` — nécessaire pour Robolectric.
  - **Pas de `kotlinOptions`/`compilerOptions` block** : pas de `-Xexplicit-api`, pas de `freeCompilerArgs`, pas d'opt-in CoroutineFlow Android.
  - `dependenciesInfo { includeInBundle = true }` sans signature de bundle — inutile/incomplet.
- `gradle.properties` — standard, mais **`org.gradle.configuration-cache = true` + AGP 9.1.1** : la configuration-cache est encore capricieuse avec KSP/Room ; à surveiller.
- `gradle/libs.versions.toml` — version catalog complet et propre (46 entrées libraries). **Incohérences détectées** :
  - `composeBom = "2024.09.00"` (il y a un an) alors que le reste est très récent (Kotlin 2.2.10, AGP 9.1.1, Room 2.7.0, lifecycle 2.8.7). Anachronisme de version — la BOM Compose devrait être 2025.x.
  - `lifecycle 2.8.7` mais `activityCompose 1.10.1` et `navigationCompose 2.8.9` — écart de cycle acceptable.
  - Dépendances **déclarées mais jamais utilisées dans `app/build.gradle.kts`** : `accompanist-permissions`, `play-services-location`, `camera-*` (4 artefacts), `datastore-preferences`, `firebase-firestore`, `firebase-auth`, `credentials`, `credentials-play-services`, `googleid`. Soit ~10 artefacts morts dans le catalog (uniquement déclarés dans le TOML, jamais référencés). Rien ne casse, mais cela révèle des fonctionnalités abandonnées (caméra, datastore, auth Google) ou un nettoyage inachevé.
  - `lifecycleViewmodelCompose` est utilisé (`viewmodel-compose`), OK.
- `metadata.json` — `majorCapabilities: ["MAJOR_CAPABILITY_SERVER_SIDE_GEMINI_API"]`. Confirme que le projet a été généré/exporté par Google AI Studio. `requestFramePermissions: []`.
- `local.properties` — `sdk.dir` commité **en violation du .gitignore** (`local.properties` est ignoré mais il est présent sur disque dans le working tree — en réalité non tracké, et `.gitignore` contient deux entrées `/local.properties` et `local.properties`). Contient un chemin Windows absolu → non portable.
- `.env.example` — `GEMINI_API_KEY` commenté. Si l'app utilisait Gemini, la clé ne serait pas packagée.

### Documentation
- `README.md` — bien écrit, trilingue implicite, instructions build/test/signature correctes. Mentionne **4 512 termes** sur 15 modules, Room v8 `medlingua_dz.db`, SM-2, CAT, TTS FR/EN/AR. Référence `docs/IMAGE_STRATEGY.md`.
- `ARCHITECTURE_FINALE_100_DUOLINGO.md` (271 lignes) — document ambitionnel qui annonce **« 100 % Duolingo compliant »** avec checklist `[x]` sur 3 phases (économie de jeu, exercise engine, backend + Birdbrain + social). Or, vérification faite : le backend Supabase n'est **jamais référencé par le code Android** (voir Partie 9), `SupabaseClient.kt` a été **supprimé** entre la version `.kilo/worktrees/mulberry-dart` (présent, 6887 octets) et la version actuelle (absent). Ce document **décrit une architecture qui n'existe plus dans le code** : les seuls "bot DZ", "Mascotte Medi 7 moods", "Same Exam Swapped Language" référencés en Phase 3 sont absents de l'app courante. À rapprocher du fait que `MascotteMedi.kt`, `OutOfHeartsDialog.kt`, `AudioPronunciationStudio.kt`, `FlashcardsScreen.kt` existent dans le worktree mais pas dans `app/` — le worktree `.kilo` est une **branche divergente plus avancée**.
- `docs/superpowers/` — deux documents de design gamification (plan 144 lignes + spec 277 lignes) cohérents avec l'implémentation actuelle (chests, leagues, trophies, pearls).

## Jugement

La configuration de build est **fonctionnelle et de bon niveau** (version catalog, FAIL_ON_PROJECT_REPOS, proguard, secrets plugin, stratégie de signature externalisée). Les problèmes sont de l'ordre de l'hygiène : `com.example` comme namespace est le marqueur le plus visible d'un projet sorti d'un générateur (AI Studio) sans renommage complet ; le catalog contient ~10 artefacts morts ; la BOM Compose est vieille d'un an ; le document d'architecture « 100% Duolingo » est de la dette documentaire dangereuse car il **affirme des fonctionnalités backend/ML/sociales qui ne sont pas dans le code**.

## Actions concrètes
1. Renommer le namespace `com.example` → `com.aistudio.medlingua.dzmed` (ou `com.medlingo.dz`) : `namespace` + déplacement des packages + mise à jour `MainActivity` dans le manifest. Effet : cohérence d'identité, `BuildConfig`/`R` correctement nommés, et suppression de la confusion dans le README.
2. Supprimer du catalog les artefacts non référencés (camera×4, accompanist-permissions, play-services-location, datastore-preferences, firebase-firestore, firebase-auth, credentials×2, googleid) — **sauf** si une feature est prévue immédiatement.
3. Mettre `composeBom` à une version 2025.x compatible Kotlin 2.2.10.
4. Déclarer une `kotlin { jvmToolchain(17) }` ou fixer explicitement la toolchain pour reproductibilité.
5. **Réécrire `ARCHITECTURE_FINALE_100_DUOLINGO.md`** pour qu'il reflète le code réel (offline-first pur, pas de backend) ou le marquer clairement comme document de vision future. Tel quel, un nouveau contributeur croira que l'app synchrone avec Supabase.
6. Retirer `local.properties` du disque ou l'exclure plus proprement (déjà ignoré — juste non commité, c'est en fait correct mais le laisser traîner est inutile).
7. Documenter dans le README la branche `.kilo/worktrees/mulberry-dart` (worktree Git persistant) qui contient des fonctionnalités non fusionnées (Supabase, mascotte, flashcards 3D) — sinon c'est une source de confusion permanente.
