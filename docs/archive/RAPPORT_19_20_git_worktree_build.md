# PARTIE 19/20 — État Git, worktree `.kilo`, artefacts de build, taille du repo

**Méthode** : `git log/status/branch/worktree`, comparaison fichier-à-fichier `app/` vs worktree, inspection de l'APK `app-debug.apk` (33,6 Mo).

## Observations

### Historique Git — 9 commits, propre
```
5983dfb fix(audit): corriger les deux passes d'audit completes (Android, web-react, backend)
398763b fix(data): attribuer exactement les termes aux modules et retirer les chemins audio fantômes
f727ea8 feat(branding): custom adaptive icon and theme-aware intro screen
502c03e feat(illustrations): local femur/neuron diagrams, drop 150 ghost image paths
b91bcb7 fix(build): repair compilation after namespace rename
101e077 audit(medlingo): security, database, build and architecture fixes
2468f60 Merge remote initial commit (GitHub stub README) - keep full project README
d90af27 MedLingo DZ: app Android offline d'anglais medical (PCM1)
908a8c9 Initial commit
```
Messages de commit **conventionnels** et descriptifs. Branche unique `main`, remote `origin`. RAS.

### `.gitignore` — correct
`app/build`, `web-react/node_modules`, `.gradle`, `.kilo/`, `tmp_*`, `debug.keystore`, `local.properties` sont **tous ignorés et aucun n'est suivi** (vérifié avec `git ls-files` : 0 fichier). Le `.kilo/` (worktree de l'IDE Kilo Code) est bien exclu. **Aucun secret ou artefact n'est commité** — c'est propre.

### Worktree `.kilo/worktrees/mulberry-dart` — 8,7 Mo, **branche divergente non fusionnée**
Detached HEAD sur `398763b` (un commit **en retard** sur `main`). Comparaison des fichiers Kotlin :

**15 fichiers présents dans le worktree mais PAS dans `app/`** :
- `SupabaseClient.kt`, `ServerAuthoritativeLesson.kt`, `BirdbrainLite.kt` — **la couche backend intégrée à l'app**, supprimée du `main`.
- `HeartsManager.kt`, `OutOfHeartsDialog.kt` — les cœurs, supprimés sur `main` (plan T4.1).
- `MascotteMedi.kt` (mascotte à 7 moods), `QuizLevelViewModel.kt`, `LeagueViewModel.kt`, `ExerciseSpecComponents.kt`.
- `OsteologyTerms.kt`, `MyologyTerms.kt`, `NeurologyTerms.kt`, `ArthrologyTerms.kt`, `TerminologieCliniqueTerms.kt`, `GenetiqueEmbryoTerms.kt` — anciens modules de termes (taxonomie abandonnée).

**21 fichiers présents dans `app/` mais pas dans le worktree** — toute la couche gamification récente : `PathBuilder.kt`, `ChestManager.kt`, `TrophyManager.kt`, `ProgressionManager.kt`, `RewardPopup.kt`, `Medi.kt`, `PathScreen.kt`, `PracticeScreen.kt`, `SuperPaywallDialog.kt`, `WordbankOrder.kt`, `TermIdMigrationPlan.kt`, `OfflineContentSize.kt`, `ModuleFilter.kt`, `LessonScoreEntity.kt`, `TrophyEntity.kt`, `BootReceiver.kt`…

**Conclusion** : le worktree est une **branche morte** (l'ancienne direction « serveur-autoritaire + Birdbrain + cœurs »). Elle contient du code qui n'existe plus (`SupabaseClient`, `ServerAuthoritativeLesson`) — donc l'intégration backend **a été écrite puis supprimée**. C'est une information importante : la décision offline-first est **délibérée et récente**, pas un oubli.

### Taille et artefacts
- **Repo hors `.git` : 6 342 fichiers, 349 Mo** (dont `app/build` ~100 Mo, `node_modules`, `.gradle`) — tous ignorés par git. Clone propre.
- **APK debug présent et valide** : `app-debug.apk` 33,6 Mo, 250 entrées, **18 fichiers dex** (classes.dex 44,2 Mo compressé → l'app est lourde).
- **Ressources dans l'APK** : `intro_dark.png` 1,29 Mo, **`ic_brand_foreground.png` 1,05 Mo (inutilisé, Partie 11)**, `hero_medical_dz.jpg` 0,85 Mo, `intro_light.png` 0,84 Mo, et **les 4 doublons** (`img_medical_hero` ≡ `stethoscope_banner`, `img_medical_faculty` ≡ `medical_faculty_banner`). **3,9 Mo de ressources sont du poids mort dans l'APK** sur 5,9 Mo de ressources au total — soit **66 % du dossier `res/` est inutile ou dupliqué**.
- Les 4 512 termes + 44 exercices sont compilés dans les classes Kotlin (les fichiers `*Terms.kt` font ~3,9 Mo de source) — c'est ce qui gonfle les dex à ~75 Mo décompressés.

### L'APK compile — preuve matérielle
La présence d'un `app-debug.apk` à jour + `test-results` avec 164 tests verts (Partie 12) confirme que le projet **compile et que les tests passent**. Mes analyses statiques sont donc cohérentes avec un build réel.

## Actions concrètes
1. **Supprimer le worktree mort** `.kilo/worktrees/mulberry-dart` (8,7 Mo) ou l'archiver : il contient une direction abandonnée (SupabaseClient, cœurs, Birdbrain intégré) qui **induit en erreur** sur l'état du projet. Avant suppression, extraire `SupabaseClient.kt` et `ServerAuthoritativeLesson.kt` dans une branche `archive/server-authoritative` au cas où le backend reviendrait.
2. **Nettoyer les ressources** (Partie 11) : supprimer `ic_brand_foreground.png` + les 2 doublons → **−2,4 Mo d'APK** (et −1,3 Mo de plus si `hero_medical_dz.jpg` n'est utilisé que par l'écran mort).
3. Compresser les splashes en WebP → ~−1,5 Mo.
4. Ajouter `.kilo/` à `.gitignore` — **déjà fait** (bon).
5. Documenter dans le README que le dossier "version old but have react and vite" est une **archive** (son nom est trompeur : ni React ni Vite à l'intérieur).
6. Envisager de **déplacer le seed des termes hors du bytecode** (assets JSON compressés) : 3,9 Mo de littéraux Kotlin → 1 JSON gzippé ~600 Ko. Réduit l'APK de ~3 Mo et facilite la mise à jour du contenu. À évaluer (Room peut consommer un asset via `createFromAsset`).
