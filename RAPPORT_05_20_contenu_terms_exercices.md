# PARTIE 5/20 — Données de contenu : seed de termes (4 512) et exercices (44)

**Fichiers analysés** : `data/terms/*.kt` (15 fichiers, ~3,9 Mo), `data/initial/InitialData.kt`, `data/initial/LearningExercisesData.kt` (69 Ko), `AnatomyDatabase.kt`, `OfflineContentSize.kt` (Partie 3). Les fichiers ont été **parsés par programme** (4512 entités + 44 exercices), pas seulement estimés.

## Observations factuelles

### Corpus de termes — 4 512 entrées, 15 modules
| Module | Termes | Chapitres | Plage d'ids |
|---|---|---|---|
| Anatomie | 412 | 11 | 1–412 |
| Physiologie | 231 | 14 | 413–643 |
| Biochimie | 434 | 32 | 644–1077 |
| Histologie | 229 | 18 | 1078–1306 |
| Biophysique | 229 | 17 | 1307–1535 |
| Génétique | 232 | 19 | 1536–1767 |
| Terminologie Médicale | 457 | 44 | 1768–3857 (non contiguë) |
| Anglais Médical | 657 | 44 | 1777–4512 (non contiguë) |
| Cytologie | 244 | 15 | 1779–3179 |
| Informatique Médicale | 232 | 20 | 1789–2020 |
| Embryologie | 236 | 20 | 2021–3409 |
| Microbiologie | 235 | 15 | 2027–2261 |
| Pharmacologie | 228 | 15 | 2262–2489 |
| Sémiologie Médicale | 238 | 15 | 2490–2727 |
| Anatomie Pathologique | 218 | 13 | 2728–2945 |

- **Ids uniques globalement** (4512/4512, aucun doublon), **(termEn, module) unique** (aucun doublon intra-module).
- **Aucun champ vide** : 0 `termAr`/`termFr`/`definitionEn`/`definitionFr`/`definitionAr`/`exampleEn`/`clinicalPearl`/`etymology`/`ipaPhonetic` vide. C'est un corpus **complet et trilingue**, avec étymologie (grec/latin), clinical pearl, exemple en 3 langues et phonétique IPA. Très bon niveau pour un projet étudiant.
- Longueur moyenne `definitionEn` : 74 caractères (min 13, max 215) — définitions concises, adaptées au mobile.
- **739 termEn apparaissent plusieurs fois** (ex. « Ventricle » en Anatomie et ailleurs). C'est **normal et souhaitable** (un même terme dans 2 modules = 2 fiches distinctes), et ça valide l'identité choisie en Partie 3 : (termEn, module), pas termEn seul.
- **519 fiches ont `termFr == termEn`** (« Ventricle », « Plasma », « Ligament », « Inflammation »…). Analyse : ce sont des termes où l'usage médical français conserve le mot anglais (ou vice versa) — pour « Ventricle » le français médical dit bien « ventricule », mais la fiche affiche « Ventricle ». C'est **soit une traduction manquante, soit un calque assumé**. Il faudrait une revue éditoriale sur ces 519 fiches (12 % du corpus).

### Plages d'ids anormales
Les ids sont globalement uniques mais **les plages se chevauchent massivement entre modules** : Terminologie Médicale va de 1768 à 3857, Anglais Médical de 1777 à 4512, Cytologie 1779–3179… Les ids sont uniques mais **non contigus par module**. Conséquence : la numérotation a été faite module par module puis **ré-affectée globalement** de façon non séquentielle (probablement via un script `tools_gen_seed_sql.py`). Cela n'a aucun impact fonctionnel (les ids ne sont jamais supposés triés par module), mais c'est étrange : `MedicalDao.getAllTerms()` trie par `module, id`, donc à l'intérieur d'un module l'ordre n'est pas pédagogique. **Pas de bug, mais une opportunité de réordonnancement** (tri par chapitre puis ordre alphabétique donnerait un lexique prévisible).

### Exercices — seulement **44**
- Répartition : 29 mcq, 9 sentence_order, 4 matching, 2 fill_blank, **0 reading, 0 clinical_case**.
- Niveaux : 8/8/8/8/6/6 = 44, soit ~7,3 exercices par niveau pour **6 niveaux × 15 modules = 90 leçons**.
- Modules couverts : Anatomie (25), Physiologie (12), Anatomie Pathologique (5), Terminologie Médicale (1), Sémiologie Médicale (1). **10 modules n'ont AUCUN exercice** (Biochimie, Histologie, Biophysique, Génétique, Anglais Médical, Cytologie, Informatique Médicale, Embryologie, Microbiologie, Pharmacologie).
- Qualité des données : pour chaque mcq/reading/clinical_case, `correctAnswer` **est bien présent dans `optionsRaw`** (0 erreur sur 29). Les `sentence_order` ont des banks et phrases cohérentes (« The femur is the longest bone in the body »). `fill_blank` utilise `optionsRaw` comme distractors — cohérent avec le migrateur et le commentaire de `ExerciseEntity`.
- Points : 10→35 croissants par niveau (10, 15, 20, 25, 30, 35), progression bien calibrée.

### Conséquences sur le gameplay
C'est **l'écart produit le plus important du projet**. Le `PathBuilder` construit 15 unités × 6 leçons = 90 nœuds de parcours. Mais le `QuizScreen` récupère les exercices **par niveau** (`getExercisesByLevel(level)`), pas par module. Donc :
- Une leçon du parcours « Anatomie niveau 3 » interroge les exercices de niveau 3 globalement (8 exercices) — qui peuvent venir de n'importe quel module.
- Pour 10 modules, il n'y a **rien** : jouer « Pharmacologie niveau 4 » renvoie en réalité les exercices de niveau 4 d'Anatomie/Physiologie.
- Un utilisateur qui termine le parcours ne rencontre que ~44 exercices différents, contre 4 512 termes. Le ratio exercices/terms est de **0,98 %**. Duolingo a typiquement des dizaines d'exercices par leçon.
- `recordLevelCompletion` débloque au seuil 70 % sur 6–8 questions : un utilisateur peut valider en répondant à 5 bonnes questions sur 8. Tout le parcours se termine en ~1 h.

### `InitialData.kt`
Propre : un seul point d'entrée, `terms` concaténés, index `termsByModule` (groupBy, O(1)), `chaptersOfModule`, `modulesList` avec icône emoji + titres FR/EN/AR + couleur + `chaptersCount` réel + `estimatedSizeMb` dérivé. **Les 15 modules de `modulesList` correspondent exactement aux 15 modules du seed** (vérifié par script) — la réparation de `ModuleFilter` (Partie 4) tient.
- **Problème de performance** : `terms` est construit par **concaténation de 15 listes** à chaque accès. `val terms` est initialisé **paresseusement** (`object` Kotlin) — un seul calcul au premier accès, OK. Mais `termsByModule` et `modulesList` (qui appelle `OfflineContentSize.forModule` 15 fois → `InitialData.terms` × 15 + `InitialData.exercises` × 15) sont aussi des `val` initialisés une fois. À l'instanciation de `InitialData`, on construit ~16 fois des listes intermédiaires puis on les groupe. En pratique une fois par process. Acceptable, mais `modulesList` appelle `chaptersOfModule(...).size` et `OfflineContentSize.forModule(...)` qui rescannent `terms` (4512) à chaque fois → **4512 × 30 = ~135 000 allocations** à l'init. Non perceptible, mais c'est du travail gratuit.

### `LearningExercisesData.kt`
816 lignes, format legacy (colonnes plates), les 44 exercices en dur. Étant donné que `ExerciseSpecMigrator` les convertit au runtime, ce fichier est un **format source** et non un format d'exécution. Acceptable.

## Jugement

Le corpus de termes est la **vraie richesse du projet** : 4 512 fiches trilingues complètes avec étymologie, pearls et IPA. En revanche, le **contenu d'exercices est dramatiquement insuffisant** : 44 exercices pour 90 leçons, 10 modules sans exercices, et aucun type « reading » ni « clinical_case » alors que ces types existent dans le moteur (`ExerciseSpec.Reading`, `ClinicalCase`), que la UI les prévoit (`QuizScreen`), et que `LearningLevel` décrit les niveaux 5 et 6 autour de ça. C'est le goulot d'étranglement du produit : sans contenu, la boucle d'apprentissage se vide en une heure.

## Actions concrètes
1. **Priorité produit absolue : générer des exercices à partir des termes.** Il existe déjà des scripts (`tools_gen_seed_sql.py`, `tools_parse_english.py`, `tools_build_english.py`) et un pipeline backend `session-generator`. Construire un générateur qui, pour chaque terme, produit : 1 mcq (4 options = 1 correcte + 3 distractrices du même module), 1 matching (5 paires EN↔FR), 1 wordbank (phrase `exampleEn` découpée), 1 fill (compléter `exampleEn` en masquant un mot-clé). Objectif minimal : 20 exos/leçon × 90 leçons = 1 800 exos, mécaniquement dérivables des 4 512 fiches.
2. **Ajouter les types manquants** : `reading` et `clinical_case` (les specs et la UI existent). Les `clinicalPearl` + `example` du corpus fournissent la matière.
3. **Associer les exercices au module** : le `PathBuilder` utilise `module:level` mais le quiz charge `getExercisesByLevel(level)` global. Soit filtrer par module au niveau du DAO (`getExercisesByModuleAndLevel`), soit accepter le mélange mais **le documenter** dans le parcours (« leçon d'anglais médical général »).
4. **Revue éditoriale des 519 fiches `termFr == termEn`** — soit valider le calque, soit traduire.
5. **Réordonner les ids par (module, chapter, termEn)** pour que le tri `ORDER BY module, id` du DAO soit pédagogiquement prévisible.
6. Optimiser `InitialData.modulesList` : précalculer `termsByModule` puis dériver `chaptersCount` et `estimate` depuis cette map (évite les 30 rescans).
7. Mettre en place un **test CI de non-régression** : nombre d'exercices ≥ N par module, `correctAnswer ∈ optionsRaw` pour tous les mcq, (termEn, module) unique. Un `SeedIntegrityTest.kt` existe déjà — l'étendre sur ces règles.
