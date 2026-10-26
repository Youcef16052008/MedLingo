# PARTIE 11/20 — Ressources Android (drawables, mipmaps, values, stratégie images)

**Analyse par programme** : 30 fichiers, 7,4 Mo au total, hash MD5 pour détecter les doublons + lecture de `docs/IMAGE_STRATEGY.md`, `values/*`, `xml/*`, `drawable/*.xml`.

## Observations

### 7,4 Mo de ressources, dont 5,3 Mo de **poids mort**
| Fichier | Taille | Utilisé ? |
|---|---|---|
| `drawable-nodpi/intro_dark.png` | 1,29 Mo | ✅ intro (dark) |
| `drawable-nodpi/ic_brand_foreground.png` | 1,05 Mo | ❓ |
| `drawable/hero_medical_dz.jpg` | 0,85 Mo | à vérifier (HomeScreen ?) |
| `drawable-nodpi/intro_light.png` | 0,84 Mo | ✅ intro (light) |
| `drawable/img_medical_hero.jpg` | 0,69 Mo | **doublon** |
| `drawable/img_clinical_stethoscope_banner_…jpg` | 0,69 Mo | **doublon (mêmes octets que le précédent)** |
| `drawable/medical_faculty_banner_…jpg` | 0,65 Mo | **doublon** |
| `drawable/img_medical_faculty.jpg` | 0,65 Mo | **doublon (mêmes octets que le précédent)** |
| `drawable/medlingua_logo.jpg` | 0,47 Mo | à vérifier |

**Deux paires de fichiers sont des copies octet-pour-octet identiques** (vérifié par hash MD5) : `img_medical_hero.jpg` ≡ `img_clinical_stethoscope_banner_1790599570280.jpg` et `img_medical_faculty.jpg` ≡ `medical_faculty_banner_1790599948077.jpg`. Les noms à timestamp (`1790599570280`) sont des **téléchargements renommés à la main** qui ont laissé l'original en place. **1,34 Mo supprimables immédiatement.**

### `ic_brand_foreground.png` — 1,05 Mo pour rien
Un PNG **nodpi** de 1 Mo utilisé seulement si l'icône adaptative pointe dessus. Or `mipmap-anydpi-v26/ic_launcher.xml` référence `@drawable/ic_launcher_foreground` (le **vectoriel** de 287 octets), pas `ic_brand_foreground.png`. Le gros PNG est donc **inutilisé**. À supprimer (−1,05 Mo).

### `values/` — désert
- `strings.xml` : **1 seule chaîne** (`app_name`). Tout l'i18n de l'app passe par `AppLanguage.kt` / `Strings.get` (540+ clés). C'est un **choix défendable** (dictionnaire trilingue unique, partagé avec le web), mais cela prive l'app des `locale resources` Android (les préférences système de langue ne peuvent pas influencer `Configuration` → il faut tout câbler à la main).
- `colors.xml` : 7 couleurs **Material par défaut** (purple/teal) — résidu du template Android Studio. **Aucune n'est référencée** (le thème est dans `ui/theme/Color.kt`). À supprimer.
- `themes.xml` : référence `Theme.Material3.DayNight.NoActionBar` — 6 lignes, minimal, OK.
- **Pas de `values-ar/`** : la langue arabe ne vient que du dictionnaire Kotlin. Support RTL géré par `LocalLayoutDirection` dans `MainActivity` (Partie 2) — mais `android:configChanges` absent + pas de `locale` déclaré → les formats de date/nombre restent ceux de la locale système.

### `IMAGE_STRATEGY.md` — le meilleur document du repo
C'est un véritable document d'ingénierie honnête :
- **Démontre les affirmations non prouvées** du plan initial (« +65 % de rétention », « mémoire visuelle ×3 », « app ~25–30 MB ») comme des estimations sans protocole, avec une citation PubMed (Jägerskog et al., 2019).
- **Déclare le retrait de 150 chemins PNG pointant vers des `assets/images/` inexistants** — c'est le nettoyage repéré en Partie 3 (champ `imageAsset` mort).
- **Refuse de simuler** : « Un bouton qui simule la progression n'est pas une fonctionnalité » — critique directe du `downloadModule` de la Partie 6.
- Détaille les **licences réelles par source** (Servier CC BY 4.0, NIH BioART par entrée, CDC PHIL mixte) et refuse le hotlink.
Ce document est la preuve qu'une discipline réelle existe dans le projet. Il devrait servir de **modèle** pour réécrire `ARCHITECTURE_FINALE_100_DUOLINGO.md` (Partie 1).

### `TermIllustrations` — 2 illustrations pour 4 512 termes
`illustration_femur.xml` et `illustration_neuron.xml` (vectoriels originaux simplifiés). Le composant `TermIllustrationView` gère légendes trilingues, crédit, zoom plein écran. La stratégie « pas de placeholder pour une image qui n'existe pas » est **la bonne décision** (un placeholder cassé est pire que rien). Mais le résultat produit : 2 termes illustrés sur 4 512 = **0,04 %**. L'app est donc **purement textuelle** alors que son public (étudiants en médecine) a un besoin visuel élevé (anatomie !).

## Comparaison à Duolingo
Duolingo investit massivement dans l'illustration (mascotte, scènes, personnages par leçon). MedLingo a une mascotte emoji 🩺 (`Medi`) et 2 schémas. **C'est le plus grand écart de qualité perçue** entre les deux produits — mais c'est aussi le plus coûteux à combler.

## Actions concrètes
1. **Supprimer les 2 doublons** (`img_medical_hero.jpg` / `medical_faculty_banner_…jpg`) et `ic_brand_foreground.png` → **−2,4 Mo d'APK**. Vérifier au passage que `hero_medical_dz.jpg`, `medlingua_logo.jpg` sont réellement référencés (sinon les supprimer aussi).
2. Compresser `intro_dark.png` / `intro_light.png` (1,29 + 0,84 Mo pour des splashes) en WebP/AVIF ou réduire la résolution → encore ~1 Mo gagnable. L'écran ne reste affiché que 1,5 s.
3. Supprimer `values/colors.xml` (7 couleurs Material non référencées).
4. **Étendre le catalogue d'illustrations** : 20–30 vectoriels sur les termes les plus visuels (os, cœur, poumon, cerveau, cellule, neurone, néphron…) via **Servier Medical Art (CC BY 4.0)** avec attribution, comme le suggère `IMAGE_STRATEGY.md`. Objectif : couvrir les 300 termes les plus appris (ceux qui reviennent dans les flashcards) plutôt que viser l'exhaustivité.
5. Ajouter `values-ar/strings.xml` (au moins `app_name`) et déclarer les locales supportées dans le manifest (`android:localeConfig`) pour que le système aligne les formats de date/nombre et que le lanceur propose « Langue de l'app ».
6. Documenter l'absence d'icône de notification blanche (Partie 9) — elle est à ajouter dans `drawable/`.
7. Vérifier que `hero_medical_dz.jpg` n'est utilisé que par `HomeScreen` (écran mort, Partie 7) → si c'est le cas, 0,85 Mo de plus supprimables.
