# PARTIE 9/20 — Services : TtsManager, NotificationHelper, NotificationReceiver, BootReceiver

**Fichiers lus intégralement** : `TtsManager.kt` (255 l.), `NotificationHelper.kt` (339 l.), `NotificationReceiver.kt` (68 l.), `BootReceiver.kt` (42 l.).

## Observations

### `TtsManager.kt` — un des fichiers les mieux écrits du projet
Gestion soignée des asynchronicités d'Android TTS :
- **`engineGeneration`** : un compteur de génération invalide tout `onInit` tardif d'un moteur dépassé/arrêté. Précisément le piège classique (un `onInit` qui arrive *après* un `shutdown` et remet `isReady = true` sur un moteur mort).
- **`pendingCallbacks` indexés par `utteranceId`** : un `UtteranceProgressListener` ne peut pas être retiré ; dès lors, le `onDone`/`onError` d'une utterance **supersédée** (QUEUE_FLUSH) ne peut pas déclencher les callbacks de la nouvelle. Le commentaire l'explique explicitement.
- **`pendingSpeak`** : une demande avant l'init est bufferisée et flushée à l'init, ou droppée sur stop/shutdown, ou errée sur échec d'init. Comportement contractuel clair.
- **`applyLanguage`** : ne ré-applique la langue **que si elle a changé** (`languageApplied`), car `setLanguage` est asynchrone et **tronquerait l'utterance suivante** — subtil et correct. Fallback sur la locale générique, puis **conserve la voix courante plutôt que le silence** (« speaking with an approximate voice beats silence »).
- `stop()` nettoie aussi le `pendingSpeak` (queued-not-started).
- Limite connue documentée : aucun callback garanti → le VM wrappe avec `withTimeoutOrNull(6000)`. C'est exactement le bon partenariat.

### `NotificationHelper.kt`
- **4 channels** avec importance différenciée (review HIGH + vibration, streak/pearl DEFAULT, level HIGH). Conforme aux pratiques Android 8+.
- **4 notifications** : review (SM-2), streak, clinical pearl, level unlocked. Chacune avec `PendingIntent` deep-linkant `EXTRA_TARGET_SCREEN` → `MainActivity.onNewIntent` → `LaunchedEffect(initialTarget)` → écran ciblé. **Vrai deep-link fonctionnel**, c'est bien au-dessus du minimum.
- **Garde des données réelles** : le `NotificationReceiver` utilise `goAsync()` + coroutine pour annoncer le **vrai** nombre de cartes dues et la **vraie** série (commentaires : « Annonce le vrai nombre de cartes dues, pas une valeur fixe »). Excellente discipline — l'ancien code affichait `dueCount = 6` et `streakDays = 12` hardcodés.
- `hasNotificationPermission` gère Android 13+ (`POST_NOTIFICATIONS`) et le fallback `areNotificationsEnabled()`. `@SuppressLint("MissingPermission")` justifié car le check est fait en tête.
- **`scheduleDailyReminder` / `scheduleRepeatingReminder`** : `setInexactRepeating` — **aucune permission `SCHEDULE_EXACT_ALARM` requise** (le commentaire le note). `PendingIntent.FLAG_IMMUTABLE` (Android 12+) partout. Bon.
- **`BootReceiver`** : réarme les 3 alarmes en respectant les toggles persistés. Exactement le comportement requis (les alarmes ne survivent pas au reboot).

### Défauts et risques
1. **Toutes les notifications sont en français hardcodé** (titres, corps, BigTextStyle, noms de channels). Un utilisateur dont l'app est en **arabe** ou en **anglais** reçoit des notifications en français. C'est la plus grosse dette i18n du projet : 4 notifications × ~3 chaînes, non couvertes par les 540+ clés de `Strings`. À noter que les channels ne peuvent être créés qu'une fois (Android garde le nom) — il faut donc bien réfléchir à la stratégie (soit localiser à la création, soit accepter).
2. **`showClinicalPearlNotification` fallback triple** : `term ?: InitialData.terms.firstOrNull { it.clinicalPearl.isNotBlank() } ?: InitialData.terms.first()` — `InitialData.terms.first()` est un **second fallback sur la liste entière**. Comme les pearls sont toujours non vides (Partie 5), le dernier branche n'est théoriquement jamais pris, mais `InitialData.terms.first()` force en plus l'**instanciation paresseuse de tout le seed** (4512 entités) au moment de la notification → pic CPU/mémoire pour une notif. À remplacer par `return` quand il n'y a pas de pearl.
3. **`setSmallIcon(R.mipmap.ic_launcher)`** — utiliser une mipmap en petite icône de notification est une **erreur Android connue** : les icônes de notification doivent être **blanches sur transparent** (vectoriel), sinon Android 5+ les affiche comme un carré blanc/silhouette. `ic_launcher` est une icône adaptative colorée → l'utilisateur verra un carré blanc dans la status bar. Il faut une `ic_notification` vectorielle blanche (et éventuellement un `setColor` pour le tint sur Android 5+).
4. **Pas de `setShowWhen`/timestamp**, pas de groupage, pas de réutilisation de `notificationId` : `NOTIFICATION_ID_REVIEW = 1001` fixe → une nouvelle notif review **remplace** la précédente. C'est intentionnel et bon (pas de spam).
5. **`ACTION_DAILY_REVIEW` fallback `else`** : un intent avec une action inconnue déclenche `showReviewReminder(context)` (sans compte réel). Comportement paresseux ; autant ignorer.
6. **Alarmes à heure fixe sans randomisation** : `setInexactRepeating` à 20:00 pile. Duolingo randomise/décale pour éviter que l'utilisateur ne désinstalle à cause d'une notif toujours à la même heure. Mineur.
7. **`BootReceiver` hardcode `scheduleDailyReminder(context, 20, 0)`** — l'heure **choisie par l'utilisateur** (`reminderHour`/`reminderMinute` du VM) est perdue au reboot : on revient à 20:00. Bug réel (l'utilisateur règle 18h30, après reboot c'est 20h00). Il faut persister l'heure dans les prefs (elle ne l'est pas : `setReminderTime` met à jour l'état UI et l'alarme, mais **pas les prefs**).
8. **`TtsManager.localeFor`** : `"ar" -> Locale("ar")` — la plupart des moteurs TTS installés en Algérie n'ont pas de voix arabe. Le code gère ce cas (fallback silencieux documenté), mais la **panne est invisible** : l'utilisateur arabe entend la voix anglaise par défaut sans savoir pourquoi. Ajouter un état `languageUnavailable` exposé à l'UI.

## Jugement

La couche services est **sérieuse** : le TTS est écrit par quelqu'un qui a vraiment rencontré et résolu les races conditions d'Android TTS (génération, callbacks indexés, pending speak, apply-language-asynchrone). Les notifications sont fonctionnelles, deep-linkées, respectueuses des permissions et des toggles persistés. Les problèmes résiduels sont localisés : **i18n des notifications absente**, **petite icône incorrecte (mipmap)**, **heure de rappel perdue au reboot**.

## Actions concrètes
1. **Créer une icône de notification vectorielle blanche** `res/drawable/ic_notification.xml` et l'utiliser dans les 4 notifications ; ajouter `.setColor(...)` pour le tint Android 5+.
2. **Internationaliser les 4 notifications** (au minimum les titres/corps via une table traduite ; pour les channels, choisir la langue au premier lancement et l'assumer, c'est une contrainte Android).
3. **Persister `reminderHour`/`reminderMinute`** dans `PREFS_REMINDERS` et les lire dans `BootReceiver` et `MedLinguaApp.onCreate()`. Bug utilisateur certain.
4. Éliminer le double fallback `?: InitialData.terms.first()` dans `showClinicalPearlNotification` (retourner tôt) ; éviter d'instancier tout le seed dans un BroadcastReceiver.
5. Exposer l'indisponibilité de la langue TTS à l'UI (`applyLanguage` retourne déjà un booléen — le propager dans le VM/UIState pour afficher un hint discret).
6. Dans le `else` du receiver, ignorer l'action inconnue plutôt que d'afficher une review.
7. Ajouter un peu de jitter (±30 min) sur les alarmes récurrentes pour réduire la fatigue.
8. Supprimer les paramètres par défaut trompeurs `dueCount: Int = 6` / `streakDays: Int = 12` dans les signatures publiques (plus aucun appelant légitime n'utilise ces valeurs) — ils incitent à recréer le bug corrigé.
