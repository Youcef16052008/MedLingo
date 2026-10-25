# PARTIE 13/20 — Localisation (AppLanguage/Strings, 180 clés) + Profile/Stats (943 l.) + Flashcards + Audio Studio

**Fichiers lus** : `localization/AppLanguage.kt` (478 l., parsé par programme), `ui/screens/StatsProfileScreen.kt` (943 l.), `ui/screens/FlashcardsScreen.kt` (343 l.), `ui/components/FlashcardCard3D.kt`, `ui/components/AudioPronunciationStudio.kt` (20 785 o).

## Observations

### i18n — la base est excellente, l'application est inégale
- **180 clés, toutes complètes en AR/FR/EN** (vérifié par script : 0 clé incomplète sur 180). Qualité élevée : « Oublié ❌ / صعب ⚠️ / Mastered! ⭐ », trophées avec chiffres **arabo-indiens** (٧ أيام, ١٠٠٠ XP) et chiffres occidentaux en FR/EN — vraie localisation, pas du Google Translate.
- Fallback : `translations[key]?.get(lang) ?: translations[key]?.get("fr") ?: key` — **la clé elle-même est renvoyée en dernier ressort**. Un développeur qui tape `Strings.get("typo_key")` voit s'afficher littéralement `typo_key` à l'écran. C'est volontaire (détection facile) mais il n'y a **aucun test** qui vérifie que les clés utilisées existent. À ajouter en CI.
- **La langue est persistée** : `user_stats.selectedLanguageCode` (colonne Room) lue au `init` du VM (`Language.fromCode(stats.selectedLanguageCode)`). Bon choix — la langue survit à une réinstallation partielle, et elle est synchronisée avec le backend.
- **Langue et notifications** : `selectedLanguageCode` est lu, mais les notifications sont 100 % **français hardcodé** (Partie 9). Un utilisateur en arabe reçoit du français.
- **Il manque une structure de repli pour l'arabe RTL** : `Language.isRtl` est défini et utilisé dans `MainActivity` (`LocalLayoutDirection`), mais les écrans mélangent alignements forcés (`TextAlign.Start`/`Center`) qui ne s'inversent pas en RTL.

### `StatsProfileScreen.kt` (943 lignes) — le plus gros écran
- **8 sections** : profil étudiant (type + année du CAT), statistiques (série, termes, points, précision), **trophées** (12), anneau d'objectif + bouton congélation, **LanguageSelector** (donc la langue **est bien accessible** — je corrige mon hypothèse de la Partie 10), **Offline Manager** (15 modules + taille réelle dérivée), **Paramètres de notification** (3 switches + heure + 4 boutons de test), diagnostic.
- **Honnêteté documentée** : « The content is bundled in the local database from the first launch: this is the real local footprint, not bytes "downloaded" from a server. » — commentaire dans le code, pas dans un doc. Bonne correction du mensonge précédent.
- **Les boutons de test de notification sont un atout** (`test_notif_review_btn`, `test_notif_streak_btn`, `test_notif_pearl_btn`, `test_notif_level_btn`) : un écran debug intégré à l'app, avec `testTag` pour les tests instrumentés. Très pratique pour la QA.
- **Trophées en 12 paliers** affichés avec état (gagné/gris), descriptions trilingues — fidèle à Duolingo.
- **Défauts** :
  - La carte **Offline Manager a encore un bouton "télécharger" par module** (`download_btn_${module.id}`) qui lance la fausse barre de progression (Partie 6), alors que le commentaire au-dessus dit que **tout est déjà dans l'app**. Contradiction visible par l'utilisateur : l'écran affiche la taille réelle et un bouton pour "télécharger" ce qu'il a déjà. **UX incohérent.**
  - `uiState.userProfileName` / `userType` / `medYear` viennent du CAT mais **ne sont pas persistés** (Partie 6/8) → le profil affiché après redémarrage est vide même si le test a été passé. Le commentaire « Student Profile Card » affiche donc un nom vide.
  - 943 lignes dans **une seule fonction composable** — recomposition monolithique.
  - Textes hardcodés : `"${uiState.userType.label(lang)} • ${it.label(lang)}"` est OK, mais les libellés de la section notification (« 20:00 ») et les unités ne sont pas tous traduits.

### `FlashcardsScreen.kt` (343 lignes)
- Flashcards SM-2 : carte recto-verso (`FlashcardCard3D`), pile infinie sur les cartes dues, `RatingButtons` (1–5), états vides.
- **Logique SM-2 réellement connectée** : `submitFlashcardRating` → `calculateNext` → `flashcard_progress`. La boucle de révision est **complète et fonctionnelle** — c'est le cœur pédagogique et il marche.
- `nextCard()` : `uiState.dueFlashcardProgress.drop(1)` — si l'utilisateur ne note jamais, la liste ne se vide jamais (comportement voulu : la carte revient).

### `AudioPronunciationStudio.kt` (20 785 octets)
- Studio de prononciation FR/EN/AR avec comparaison TTS. Existe et est câblé depuis `MainActivity` (`toggleAudioStudio`). Propose l'écoute, l'enregistrement (micro) et la comparaison.
- **Permission micro non déclarée** : le manifest n'a **pas** `RECORD_AUDIO` (Partie 2). Si le studio enregistre, l'app crashera en prod (ou la demande de permission échouera silencieusement). Et `accompanist-permissions` est dans le catalog mais non utilisé (Partie 1). **Bug de release certain si l'enregistrement est activé.**
- **Pas de gestion d'erreur TTS** : le studio suppose `ttsManager.isReady`. La fenêtre de 6 s du VM (`withTimeoutOrNull`) est côté VM, pas côté UI.

## Actions concrètes
1. **Corriger le bouton "télécharger"** de l'Offline Manager : afficher « Inclus ✓ » (non cliquable) pour tout module déjà présent, et supprimer la fausse barre de progression dans le VM.
2. **Persister le profil du CAT** (`userProfileName`, `userType`, `medYear`, `specialty`) — colonnes ou DataStore. Sinon l'écran profile ment.
3. **Vérifier `RECORD_AUDIO`** : ajouter la permission + la demande runtime dans `AudioPronunciationStudio`, ou désactiver l'enregistrement s'il n'est pas implémenté.
4. **Test CI des clés i18n** : un test qui parse le code (`Strings.get\("([a-z_0-9]+)"\)`) et asserte que chaque clé existe dans `translations`. Évite l'affichage de `ma_clé` en prod.
5. **Localiser les notifications** (Partie 9) — c'est la plus grosse fuite i18n.
6. Découper `StatsProfileScreen` en sous-composables (`ProfileHeaderCard`, `StatsGridCard`, `TrophiesCard`, `OfflineManagerCard`, `NotificationsCard`).
7. Auditer l'RTL : exécuter l'app en arabe et corriger les `TextAlign`/padding directionnels (`padding(start = …)` → `padding(start = …)` est OK, mais `Modifier.offset(x = …)` ne l'est pas).
