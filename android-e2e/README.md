# Android E2E — parcours & leçons (R-36)

Deux scripts de bout en bout qui pilotent **l'APK réel** sur un émulateur via
**Artemis** (`AndroidAdbDriver` + `UIAutomatorClient`, sans clé LLM : tout est
déterministe).

| Script | Ce qu'il prouve |
|---|---|
| `medlingo_path_e2e.py` | Le Parcours est un **drill-down** : un seul module est listé à la fois (11 chapitres réels du seed pour Anatomie, 6 leçons chacun, jamais « 0 chapitres ») ; taper un chapitre ouvre sa **page dédiée** avec les 6 nœuds L1→L6 (L6 = BOSS) ; L1 s'ouvre sur une vraie leçon (« Question 1 / 8 »). |
| `medlingo_lesson_e2e.py` | Une leçon se joue **en entier** (8 questions, QCM + appariement), la même leçon est déterministe d'une passe à l'autre, un score 100 % est persisté dans `lesson_scores` et débloque la leçon suivante dans le Parcours. |

## Pré-requis

- un émulateur démarré (`emulator-5554`) avec l'APK installé
  (`gradlew assembleDebug` puis `adb install -r -d app/build/outputs/apk/debug/app-debug.apk`) ;
- le dépôt **artemis** cloné à côté, avec son `.venv` (`uv sync`) : les scripts
  importent `artemis.drivers.android.adb_driver` et
  `third_party.mobile_use.clients.ui_automator_client`.

## Lancer

Depuis la racine du dépôt artemis, avec les scripts copiés à côté :

```powershell
uv run python medlingo_path_e2e.py
uv run python medlingo_lesson_e2e.py
```

## Notes d'implémentation

- **Navigation en trois niveaux** : le Parcours suit le modèle Duolingo — un
  bouton « Continuer » (prochaine leçon jouable), un sélecteur de module
  repliable (15 modules), la liste des chapitres **du module choisi** (recherche
  repliée derrière une loupe), puis une page dédiée par chapitre avec son chemin
  de 6 leçons. Rien n'est composé pour les autres modules : la page garde une
  longueur raisonnable (312 chapitres / 1 872 leçons au total).
- **Fixture reproductible** : `reset_chapter_progress()` remet le chapitre
  `Angiologie` dans un état connu via `run-as … sqlite3` (le parcours part d'un
  chapitre vierge ; la leçon part de L1/L2 validées, donc L3 est la leçon
  disponible). Sans cela, chaque exécution jouerait la leçon suivante.
- **Oracle en deux passes** : la passe 1 répond « A » et lit le marqueur
  `Correct` de l'app pour apprendre les réponses ; la passe 2 les rejoue et
  obtient 100 %. L'oracle est indexé par **position** (une leçon peut contenir
  deux fois la même question).
- **Appariement** : les paires EN→FR sont résolues depuis
  `medical_terms(termEn, termFr)` — pas de force brute, donc zéro erreur.
- **ANR** : les taps ADB arrivent parfois avant que la fenêtre ait le focus
  (`Input dispatching timed out`). `hide_error_dialogs=1` + `wait_app_focus()`
  neutralisent le problème.
- La base Room est lue en tirant `medlingua_dz.db` **et** ses `-wal` / `-shm`
  (Room écrit dans le WAL avant checkpoint).
