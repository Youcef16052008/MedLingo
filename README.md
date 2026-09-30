# MedLingo DZ 🇩🇿

**L'anglais médical académique & clinique pour les carabines algériens.**

Application Android-native (offline-first) d'apprentissage de l'anglais médical, pensée pour les étudiants en
médecine de la Faculté de Médecine d'Alger (PCM1) : flashcards, quiz, test de placement adaptatif, algorithme
de répétition espacée **SM-2**, prononciation TTS et tableau de bord de progression.

- **Package** : `com.aistudio.medlingua.dzmed`
- **Stack** : Kotlin · Jetpack Compose (Material 3) · Room · Navigation Compose · Coroutines · AGP 9.1.1 / Gradle 9.3.1
- **minSdk 24** · **targetSdk / compileSdk 36.1**

## Fonctionnalités

- 15 modules PCM1 (Anatomie, Histologie, Embryologie, Pharmacologie, Physiologie, Microbiologie, …)
- Flashcards avec répétition espacée SM-2 et niveaux d'apprentissage
- Test de placement adaptatif (CAT) avec moteur d'orientation
- Prononciation audio (Android TTS) et support FR / EN / AR
- Statistiques, séries d'apprentissage et suivi de précision
- Base de données Room pré-remplie (295 termes médicaux sur 15 modules), 100 % hors-ligne
- Pilote d'illustrations locales (fémur et neurone) dans le lexique et les flashcards ; stratégie et limites : [docs/IMAGE_STRATEGY.md](docs/IMAGE_STRATEGY.md)
- Écran d'introduction à chaque ouverture, adapté au thème système (sombre ou clair)
- Icône d'application personnalisée (icône adaptative Android 8+)

## Prérequis

- [Android Studio](https://developer.android.com/studio) (Ladybug ou ultérieur) **ou** JDK 17+ et Android SDK 36
- Un appareil / émulateur (Android 7.0+)

## Lancer en local

### Android Studio
1. **Open** le répertoire du projet et laisser Android Studio corriger les incompatibilités d'import.
2. Créer un fichier `.env` à la racine avec `GEMINI_API_KEY` (voir `.env.example`).
3. Lancer sur un émulateur ou un appareil physique.

### Ligne de commande
```bash
# JDK + SDK requis (JAVA_HOME + local.properties ou ANDROID_HOME)
./gradlew :app:assembleDebug          # build
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.aistudio.medlingua.dzmed/com.example.MainActivity
```

> **Note signature debug** : `app/build.gradle.kts` référence `debug.keystore` à la racine du projet
> (alias `androiddebugkey`, mot de passe `android`). Ce fichier est gitignoré ; générez-le si absent :
> ```bash
> keytool -genkeypair -v -keystore debug.keystore -storepass android -keypass android \
>         -alias androiddebugkey -keyalg RSA -keysize 2048 -validity 10000 \
>         -dname "CN=Android Debug,O=Android,C=US"
> ```
> Sinon, supprimez la ligne `debug { signingConfig = signingConfigs.getByName("debugConfig") }`.

### Tests
```bash
./gradlew :app:testDebugUnitTest     # JUnit + Robolectric
./gradlew :app:connectedDebugAndroidTest   # tests instrumentés (appareil requis)
```

## AI Studio

Ce projet a été généré avec [Google AI Studio](https://ai.studio/apps/27222750-8219-4b37-bdfb-7a04f2543115).

View your app in AI Studio: https://ai.studio/apps/27222750-8219-4b37-bdfb-7a04f2543115

1. Open Android Studio
2. Select **Open** and choose the directory containing this project
3. Allow Android Studio to fix any incompatibilities as it imports the project.
4. Create a file named `.env` in the project directory and set `GEMINI_API_KEY` in that file to your Gemini API key (see `.env.example` for an example)
5. Remove this line from the app's `build.gradle.kts` file: `signingConfig = signingConfigs.getByName("debugConfig")`
6. Run the app on an emulator or physical device
7. If you have already published your app in AI Studio, please [request upload key reset](https://support.google.com/googleplay/android-developer/answer/9842756#zippy=%2Crequest-an-upload-key-reset) in Google Play Console.

