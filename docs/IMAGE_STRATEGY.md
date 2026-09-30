# Images médicales — décision technique (pilote)

## Ce qui est faux ou non démontré dans le plan initial

- Ce dépôt est **Android natif Kotlin/Jetpack Compose**, et non Flutter. `pubspec.yaml`, `SmartMedicalImage`, `firebase_storage` et les exemples Dart ne sont pas applicables.
- « +65 % de rétention », « mémoire visuelle ×3 », « app finale ~25–30 MB », tailles des packs et prix des hébergeurs sont des **estimations sans protocole, mesure ni source adéquate**. Ne pas les présenter comme des résultats ou garanties. Des illustrations pertinentes peuvent aider l'apprentissage, mais leur effet dépend du contenu et de l'évaluation ; voir [Jägerskog et al., 2019](https://pubmed.ncbi.nlm.nih.gov/30809837/).
- Avant ce changement, **150 chemins PNG** dans les données initiales pointaient vers `assets/images/...` inexistants. Ils ont été retirés des données semées. Le champ Room `imageAsset` reste pour compatibilité du schéma, mais n'est pas une source d'images fiable ; les anciennes valeurs en base ne doivent pas être affichées. Aucune migration Room requise pour le catalogue séparé.
- Une politique d'expiration de cache, même avec Coil, n'est pas une garantie hors ligne : le cache peut être purgé par le système. Un « pack téléchargé » exige un manifeste versionné, des fichiers vérifiés, du stockage persistant, un suivi d'erreurs, une suppression réelle et des tests en mode avion. Un bouton qui simule la progression n'est pas une fonctionnalité.

## Livré maintenant

Deux schémas vectoriels **originaux et simplifiés** (fémur, neurone) sont emballés en Android VectorDrawable. Seuls les termes correspondants dans le catalogue `TermIllustrations` montrent une image, dans le lexique développé et au verso de la flashcard. Légendes FR/EN/AR, crédit, bouton d'agrandissement et pincement/translation en plein écran. Les autres termes restent textuels ; pas de placeholder annonçant une image qui n'existe pas. Les dessins schématiques ne remplacent pas des planches anatomiques ni des photographies diagnostiques ; validation par un enseignant d'anatomie avant extension du catalogue.

Pas de backend CDN, de stockage Firebase, de téléchargement par module ou de dépendance ajoutée : aucun catalogue de photographies autorisées et aucun hébergement opérationnel n'ont été fournis. Coil est déjà une dépendance du projet et pourra servir ultérieurement pour des URL HTTPS **stables, contrôlées et attribuées**.

## Avant d'ajouter des photos ou des packs

1. Choisir quelques objectifs pédagogiques par module ; vérifier les diagrammes et annotations avec un expert et tester leur apport par rapport aux cartes texte. Les photos cliniques doivent respecter consentement/confidentialité, diversité des phototypes et contexte diagnostique.
2. Pour chaque image : provenance *de l'image exacte*, auteur, URL stable, licence et version, attribution obligatoire, éventuelles modifications, légende et validation médicale. Ne pas utiliser d'URL fictive ni de hotlink direct non autorisé.
3. Vérifier les droits **par fichier** : [Servier Medical Art est sous CC BY 4.0 (attribution et indication des modifications)](https://smart.servier.com/terms-of-use/) ; [NIH BioART affiche une licence par entrée, par exemple cette entrée Public Domain](https://bioart.niaid.nih.gov/bioart/244) ; [CDC PHIL mélange images du domaine public et images protégées](https://phil.cdc.gov/FAQ.aspx). « Gratuit » n'est pas une licence universelle.
4. Si cloud : commencer par héberger des variantes vignette/HD sur un domaine contrôlé et par un pilote de chargement à la demande avec état erreur. Pour l'hors ligne garanti, ajouter ensuite un manifeste par module, identifiants et checksums, téléchargements interrompables/reprenables, réservation de place, nettoyage, état persistant et test sans réseau après redémarrage. Ne promettre « Wi-Fi uniquement » qu'après implémentation de la contrainte réseau.
5. Mesurer l'APK/AAB réel (`assembleRelease` / analyseur Android Studio), puis la consommation réelle de données et de stockage sur appareil. Les chiffres ci-dessus ne sont pas des budgets validés.
