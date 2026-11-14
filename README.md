# MedLingo DZ 🇩🇿

**L'anglais médical académique & clinique pour les carabines algériens.**

Application Android-native (offline-first) d'apprentissage de l'anglais médical, pensée pour les étudiants en
médecine de la Faculté de Médecine d'Alger (PCM1) : flashcards, quiz, test de placement adaptatif, algorithme
de répétition espacée **SM-2**, prononciation TTS et tableau de bord de progression.

- **Package** : `com.aistudio.medlingua.dzmed`
- **Stack** : Kotlin · Jetpack Compose (Material 3) · Room · Navigation Compose · Coroutines · AGP 9.1.1 / Gradle 9.3.1
- **minSdk 24** · **targetSdk / compileSdk 36.1**
- **Aussi** : web React 19 + Vite (`web-react/`, PWA offline) · backend Supabase (dormant — l'app ne demande AUCUNE permission réseau)
- **Statut (12/11/2026)** : dev complet — workstreams WS0→WS5 livrés (stabilisation 36 findings, contenu 13 000 exercices + 814 histoires, intégration, release dossier, 8 leviers d'engagement, moteur de maîtrise + examen EMD). **Cycle courant** : pilote Anatomie — parcours web + Android prêts, contenu 100 % `draft` (**0 concept relu/approuvé** : le parcours réel refuse de démarrer tant que les deux relectures humaines n'ont pas eu lieu) — plan [docs/PLAN_PILOTE_ANATOMIE.md](docs/PLAN_PILOTE_ANATOMIE.md), suivi [docs/PILOTE_ANATOMIE_SUIVI.md](docs/PILOTE_ANATOMIE_SUIVI.md). **Portes restantes (humaines)** : relecture des histoires (`web-react && npm run review`), deux relectures du contenu pilote (linguistique + anatomique), keystore + CGU. Pilotage : [docs/PLAN_MAITRE_2026.md](docs/PLAN_MAITRE_2026.md).

## Fonctionnalités

- 15 modules PCM1 (Anatomie, Histologie, Embryologie, Pharmacologie, Physiologie, Microbiologie, …)
- Flashcards avec répétition espacée SM-2 et niveaux d'apprentissage
- Test de placement adaptatif (CAT) avec moteur d'orientation
- Prononciation audio (Android TTS) et support FR / EN / AR
- Statistiques, séries d'apprentissage et suivi de précision
- Base de données Room pré-remplie (4 504 termes médicaux sur 15 modules), 100 % hors-ligne
- Pilote d'illustrations locales (fémur et neurone) dans le lexique et les flashcards ; stratégie et limites : [docs/IMAGE_STRATEGY.md](docs/IMAGE_STRATEGY.md)
- Écran d'introduction à chaque ouverture, adapté au thème système (sombre ou clair)
- Icône d'application personnalisée (icône adaptative Android 8+)

## Prérequis

- [Android Studio](https://developer.android.com/studio) (Ladybug ou ultérieur) **ou** JDK 17+ et Android SDK 36
- Un appareil / émulateur (Android 7.0+)

## Lancer en local

### Android Studio
1. **Open** le répertoire du projet et laisser Android Studio corriger les incompatibilités d'import.
2. Lancer sur un émulateur ou un appareil physique.

### Ligne de commande
```bash
# JDK + SDK requis (JAVA_HOME + local.properties ou ANDROID_HOME)
./gradlew :app:assembleDebug          # build
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.aistudio.medlingua.dzmed/.MainActivity
```

> **Note signature debug** : `app/build.gradle.kts` applique le `debug.keystore` de la racine
> (alias `androiddebugkey`, mot de passe `android`) **seulement s'il existe** — ce fichier est
> gitignoré ; générez-le si besoin :
> ```bash
> keytool -genkeypair -v -keystore debug.keystore -storepass android -keypass android \
>         -alias androiddebugkey -keyalg RSA -keysize 2048 -validity 10000 \
>         -dname "CN=Android Debug,O=Android,C=US"
> ```
> Sans lui, le build debug utilise la clé de signature debug par défaut de l'AGP (aucune ligne à supprimer).

### Tests
```bash
./gradlew :app:testDebugUnitTest     # JUnit + Robolectric
./gradlew :app:connectedDebugAndroidTest   # tests instrumentés (appareil requis)
```

## Statut du backend

L'application est **100 % hors-ligne** : aucune permission `INTERNET`, base Room pré-remplie, mono-utilisateur.

Le dossier `backend/` (schéma Supabase, RLS, seed) **n'est pas branché** : la couche réseau
(`MedLingoRemoteClient`) est écrite et fonctionne en mode no-op. Le brancher demande `INTERNET`
+ un fichier `secrets.properties` (voir `GUIDE_BACKEND.md`).

**Le seed SQL est maintenu** : `npm run check:seed` (web) et un test Android (`SeedIntegrityTest`)
vérifient à chaque build qu'il couvre bien l'intégralité du corpus (4 504 termes, 13 000 exercices).
Ne pas le régénérer après modification des seeds crée une dérive silencieuse.

## Web

Un port web React/TS existe dans `web-react/` :

```bash
cd web-react
npm i
npm run extract      # regenere terms.json/exercises.json depuis les seeds Kotlin
npm run test         # suite vitest complète — 39 fichiers / 5 231 tests PASS au 11/11/2026
npm run check:i18n   # parité i18n ar/fr/en — 369 clés ×3, 0 morte, au 11/11/2026
npm run check:seed   # coherence du seed SQL backend
npm run design       # 35 checks de computed styles (parité design web ↔ Android)
npm run build        # tsc strict + vite  → dist/ (utilisable sur Vercel)
```

## Parité web ↔ Android

Les deux plateformes partagent la **même source de vérité visuelle** : chaque écran web a été
aligné sur son homologue Android (`.kt` = référence), token par token — couleurs exactes, rayons
dp→px, tailles sp→px, typographie, structure des composants.

- **10 écrans alignés** : top bar, bottom bar, parcours, révision, modules, profil, ligues,
  quiz, flashcards (+ parcours des leçons)
- **Validé par exécution** : `npm run design` (Playwright, viewport 390×844) — 35/35 vérifications
  de computed styles OK, `tsc --noEmit` 0 erreur, `npm run test` 0 échec (5 231 tests au 11/11/2026)
- **Bug de routage corrigé** : le paramètre de route (`#/flash/anat`) était supprimé par
  `routeName()` avant lecture — la session flashcards se construisait sur un module vide

Détail complet et écarts résiduels documentés : [RAPPORT_PARITE_WEB_ANDROID.md](RAPPORT_PARITE_WEB_ANDROID.md).

**Design system web — « Clinical Calm »** (octobre 2026) : au-delà de la parité
structurelle avec Android, le web porte son propre système visuel rafraîchi —
palette médicale (vert émeraude, teal clinique, canvas menthe `#F0F7F4`),
typographie Inter à chiffres tabulaires, échelle de rayons moderne, élévation
douce — inspiré des systèmes santé modernes et validé WCAG AA. Spec complète :
[web-react/DESIGN.md](web-react/DESIGN.md), vérification : `npm run design`
(40 contrôles de computed styles).

**Fonctionnalités Android non portées sur le web** (différences de plateforme, pas des écarts
de design) : placement diagnostic initial, gestion hors-ligne par module, notifications,
sélecteur d'objectif quotidien. L'écran Recherche est web-only.

## Origine AI Studio

Ce projet a été généré à l'origine avec [Google AI Studio](https://ai.studio/apps/27222750-8219-4b37-bdfb-7a04f2543115).

Aucune clé Gemini n'est nécessaire : l'application est 100 % hors-ligne et n'appelle aucune API
distante (le `.env.example` hérité d'AI Studio est un vestige sans effet ; inutilité vérifiée
dans le code le 12/11/2026). Pour lancer l'app, suivre « Lancer en local » ci-dessus.

