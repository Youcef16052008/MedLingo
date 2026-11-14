# MedLingo — Plan d’exécution du pilote d’anglais anatomique

Date : 11 novembre 2026. Statut : **plan à exécuter, aucune tâche déclarée terminée**.
Décisions produit : premières années de médecine ; anglais médical d’abord ; anatomie comme unique module du premier pilote.

Ce document est la référence du PROCHAIN cycle de travail. Il ne transforme pas les anciens audits en faits actuels et ne remplace pas les spécifications techniques utiles. Les constats ci-dessous sont datés et doivent être revérifiés avant modification.

## 0. Instructions à l’agent exécutant

1. Lire ce document en entier, puis inspecter le code réellement présent et `git status`.
2. Travailler sur la branche de session existante. Ne pas créer/changer de branche, ne pas supprimer de travail utilisateur, ne pas committer/pousser sans demande.
3. Commencer par P0 ; respecter les dépendances. Ne pas refondre l’application entière.
4. Ne pas supprimer les autres modules, données ou fonctionnalités. Le pilote est un parcours isolé et réversible.
5. Ne jamais déclarer une relecture humaine effectuée, inventer un relecteur, une référence médicale, des participants ou des résultats.
6. Ne pas obtenir du vert en affaiblissant les tests, en augmentant les seuils arbitrairement ou en ignorant une suite.
7. Réutiliser les composants et moteurs existants lorsque leurs contrats conviennent. Un petit moteur dédié vaut mieux qu’une modification dangereuse du parcours historique.
8. Aucun nouveau service cloud, achat, outil IA externe, analytics tiers ou dépendance lourde pour ce cycle.
9. À chaque lot : tests ciblés, contrôles pertinents, diff relu, compte rendu avec résultats et limites. Si un outil manque, marquer NON EXÉCUTÉ, pas « validé ».
10. Un blocage de relecture ne bloque pas le développement : utiliser des fixtures synthétiques dans les tests et un aperçu développeur explicitement non validé. Ne jamais proposer ce contenu comme approuvé aux étudiants.

## 1. Contrat produit

### Public initial
Étudiants de première année de médecine en Algérie, francophones et/ou arabophones, débutant en anglais médical. L’ouverture à d’autres publics est reportée.

### Promesse à tester
« Comprendre et utiliser des termes anatomiques anglais fréquents dans des phrases simples, avec aides FR/AR et révision espacée. »

L’anatomie est le contexte ; l’anglais est la compétence évaluée. Ne pas transformer ce pilote en cours complet de médecine, examen universitaire ou conseil clinique.

### Livrable pilote
- 30 concepts anatomiques proposés, à sélectionner et valider humainement.
- Trois unités : parties du corps, organes principaux, localisation anatomique.
- Six séances indicatives de cinq concepts, cible de durée 5–10 minutes à mesurer, jamais promise comme résultat garanti.
- Progression : reconnaissance → compréhension en phrase → rappel sans réponse visible → transfert à phrase nouvelle → production guidée.
- Révisions locales ; évaluation initiale, finale et à J+7 distinctes de l’entraînement.
- Version web d’abord pour le premier parcours testable ; Android ensuite après stabilisation du contrat de contenu. C’est un séquencement technique réversible, pas l’abandon d’Android.

### Hors périmètre
Nouveaux modules, extension du corpus, nouvelles ligues/gemmes/trophées, certificats de compétence, authentification/sync, examen complet, reconnaissance automatique de prononciation, migration globale de l’architecture. Les fonctions existantes sont conservées hors du pilote.

## 2. État de départ vérifié lors de l’audit

| Élément | Observation au 11/11/2026 | Limite |
|---|---|---|
| Build web | `npm run build` réussit | Pas une validation en navigateur |
| Tests web | 30 suites passent, une échoue au chargement ; 5 120 tests passent | Échec de `lessons.test.ts` sur chemins Windows absolus |
| i18n | 304 clés synchronisées FR/EN/AR | Synchronisation ≠ qualité linguistique |
| Seed | 4 504 termes / 13 000 exercices cohérents avec le contrôle | Cohérence ≠ justesse pédagogique |
| Histoires | 814 identiques entre plateformes ; 813 draft, 1 published avec deux reviews pending | Exception pilote contournant le verrou annoncé |
| Exercices | Fuites explicites de réponses dans certains énoncés | Comptage littéral ≠ classification médicale exhaustive |
| Maîtrise web | Lit `moduleBest["quiz"]`, alors que le quiz écrit `moduleBest[q.moduleId]` | Contrat d’intégration incohérent |
| Android | Non exécuté, JDK/SDK absents dans l’environnement d’audit | Ne pas reprendre les anciens chiffres comme résultats frais |

Reproduire la baseline avant de corriger ; conserver les codes de sortie réels de chaque commande. Ne pas masquer un échec avec une dernière commande réussie dans une chaîne shell.

## 3. Architecture minimale proposée

### Sources et artefacts
- Les seeds Kotlin existants restent la source historique des termes ; ne pas renuméroter leurs IDs.
- Créer un manifeste pilote versionné, source unique, par exemple `web-react/data/pilot-anatomy.v1.json`.
- Exporter un artefact web dans `web-react/public/data/pilot-anatomy.v1.json`.
- Lors du port Android seulement, exporter le même contenu dans `app/src/main/assets/learning/pilot-anatomy.v1.json` et vérifier la parité.
- Ajouter un script explicite de validation/export ; ne pas dépendre de commandes manuelles de copie.
- Un manifeste peut référencer un terme historique et porter des exemples pédagogiques spécifiques. Ne pas réécrire toutes les fiches du corpus pour les besoins du pilote.
- Les concepts absents du corpus utilisent un identifiant pilote textuel stable ; ne pas inventer un ID Room numérique. Documenter le lien éventuel avec le terme historique.

### Modules suggérés (adapter aux conventions réelles)
- `src/pilot/types.ts`, `content.ts`, `progress.ts`, `assessment.ts` : contrats, validation, état et mesure.
- `src/pilot/__tests__/` : tests métier et de données.
- `src/screens/PilotAnatomyScreen.tsx` et composants locaux si nécessaires.
- Route dédiée compatible avec le routeur hash existant : `#/pilot-anatomy`.
- Point d’entrée pilote distinct ; navigation de l’application historique inchangée par défaut.

Ne pas introduire Redux, un nouveau routeur ou un second système global de thème.

## 4. P0 — Restaurer les garanties techniques et pédagogiques

### P0.1 — Tests reproductibles
Fichier confirmé : `web-react/src/domain/__tests__/lessons.test.ts`.
- Remplacer tous les chemins `C:/Users/...` par des chemins résolus relativement au fichier ou à la racine projet.
- Rechercher les autres chemins absolus machine dans tests et scripts ; corriger ceux du chemin d’exécution.
- Examiner `transformExercises` : ne pas normaliser artificiellement les vraies données pour dissimuler un contrat cassé. Garder une séparation claire entre tests de fixtures et tests du corpus réel.
- Conserver les assertions utiles et expliquer toute modification de fixture.

Acceptation : suite ciblée puis suite complète réussies depuis `web-react` sur checkout Linux propre. Aucun test ignoré ajouté pour contourner le défaut.

### P0.2 — Maîtrise réellement indexée par module
Fichiers : `src/domain/mastery.ts`, `src/domain/__tests__/mastery.test.ts`, appels dans `PracticeScreen.tsx`, écriture dans `QuizScreen.tsx`.
- Donner explicitement le moduleId au calcul OU transmettre directement le meilleur score du module ; choisir un contrat typé unique.
- Adapter tous les appels et tests ; ne pas écrire une clé globale `quiz` pour compenser.
- Vérifier les échelles 0–100, valeurs invalides, module absent et absence de cartes.
- Renommer les formulations non justifiées : indicateur de progression, pas certification de compétence.

Tests obligatoires : anatomie=80 et autre module=20 donnent deux contributions quiz distinctes ; réussir l’un ne modifie pas l’autre ; SM-2 nul, échus, horloge injectée ; scores existants conservés.

Acceptation : test d’intégration reliant une fin de quiz au calcul affiché, pas seulement un test de fonction avec dictionnaire artificiel.

### P0.3 — Audit et correction des réponses visibles
Fichiers : `public/data/exercises.json`, générateurs à identifier, `src/screens/QuizScreen.tsx`, `src/domain/bulkExercises.ts`, scripts de génération, miroir Kotlin et SQL si affectés.
- Tracer la provenance réelle des 13 000 exercices avant édition : inspecter `scripts/gen_bulk_exercises.ts`, `scripts/extract.mjs`, scripts racine et générateurs Kotlin. Ne pas présumer qu’un fichier donné est l’unique générateur.
- Ajouter un contrôle de qualité produisant un rapport par type/langue/id : réponse copiée après flèche, modèle visible, blanc absent, consigne de traduction incohérente, options invalides.
- Séparer erreurs certaines et suspicions. Une sous-chaîne commune ou un cognat ne suffit pas à condamner un exercice.
- Corriger le générateur source ; régénérer les artefacts concernés de manière déterministe.
- Pour un exercice sans trou, utiliser une consigne honnête (« Quel terme correspond à cette définition ? »), pas « Complétez ».
- Garder les phrases d’entraînement avec aide explicite distinctes des questions notées.
- Si le corpus global ne peut être fiabilisé dans ce lot : signaler les exercices affectés, les exclure du pilote par allowlist stricte et documenter la dette. Ne pas déclarer tout le corpus corrigé.

Tests : cas de fuite réel « ... → Ventricle » détecté ; cognat légitime non rejeté automatiquement ; FR/AR cohérents avec la tâche ; réponse non révélée avant soumission sur une question notée ; déterminisme d’export.

Acceptation pilote : aucun exercice non relu ou historiquement sélectionné par hasard ne peut entrer dans le parcours pilote.

### P0.4 — Verrou de publication des histoires
Fichiers : `scripts/build_stories_corpus.py`, `src/learning/stories.ts`, `src/learning/__tests__/storiesContent.test.ts`, `stories.v1.json` web/Android, `StoryAssetSource.kt`, `web-react/data/review-verdicts.v1.json`.
- Supprimer l’exception de publication du pilote non approuvé.
- Publier seulement si statut published ET relecture linguistique approved ET médicale approved.
- Appliquer une défense au chargement sur les deux plateformes, pas seulement dans le générateur.
- État vide honnête si zéro histoire approuvée ; tests des routes directes, pas d’écran bloqué.
- Ne pas créer de faux verdict pour maintenir une démonstration disponible. Utiliser un mode développeur clairement séparé si nécessaire.

Tests : toutes les combinaisons de verdicts ; published+pending refusé ; version inconnue refusée ; zéro publiée géré ; parité web/Android conservée. Mettre à jour les anciens tests qui exigeaient artificiellement au moins une publication.

### P0.5 — CI et checkout propre
Fichiers : `.github/workflows/web.yml`, `android.yml`, `app/build.gradle.kts`.
- Faire exécuter les contrôles sur la branche de travail et les PR pertinentes ; éviter une duplication inutile push+PR, permissions minimales, ne pas déployer.
- Générer un keystore DEBUG temporaire en CI ou revenir au mécanisme debug standard. Ne jamais committer une clé ni modifier la signature release pour cela.
- Conserver lint et tests bloquants ; ajouter les validations pilote lorsqu’elles existent.
- Tester les migrations/seed sur base jetable seulement si l’outillage est disponible ; ne pas confondre `check:seed` avec un démarrage Supabase réussi.

Acceptation : build debug/tests/lint réellement exécutés en environnement équipé, ou blocage explicite documenté. Aucune revendication de succès sur la seule présence du YAML.

## 5. P1 — Constituer le petit corpus contrôlé
Dépendances : P0.3 ; le schéma peut être développé en parallèle de P0.

### P1.1 — Schéma éditorial et validation
Chaque concept doit porter :
- id stable, référence historique facultative, unité et ordre ;
- libellé EN, aides FR/AR et variantes acceptées explicites ;
- objectif de langue, définition simple et exemple EN avec aides FR/AR ;
- statut draft/approved, version de contenu ;
- relectures linguistique et anatomique : statut, personne réelle, date, version relue, références vérifiables lorsque nécessaires ;
- exercices d’entraînement séparés des évaluations ; consignes localisées ; réponse, variantes et explication ;
- média facultatif avec licence, attribution et provenance. Pas d’image anatomique IA présentée comme référence validée.

Validation : IDs uniques, références existantes, langues présentes, niveaux admis, options distinctes, réponse valide, références de séance cohérentes, contenu approuvé uniquement si les deux relectures couvrent la version courante.
Une modification de contenu doit invalider la validation ou exiger une nouvelle version relue ; un ancien approved ne doit pas survivre silencieusement.

### P1.2 — Sélection des 30 concepts
- Produire une proposition de 10 concepts par unité, avec justification de fréquence/utilité à faire examiner.
- Vérifier les doublons conceptuels, ambiguïtés, variantes anglais britannique/américain et qualité des aides arabes.
- Pour localisation : enseigner le cadre de référence anatomique ; ne pas proposer de phrase directionnelle ambiguë sans contexte.
- Privilégier des phrases brèves et grammaticalement accessibles. Ne pas certifier un niveau CECRL sans évaluation.
- Étiqueter tous les contenus produits automatiquement comme drafts.

Livrables humains : deux relectures traçables sur la version exacte. Si indisponibles, livrer un corpus candidat et une liste des blocages, pas un pilote public prétendument validé.

### P1.3 — Matrice exercices × objectifs
Pour chaque concept : reconnaissance, compréhension en contexte et rappel ; ajouter transfert et production guidée au niveau de la séance.
- Distracteurs plausibles mais une seule réponse correcte.
- Normaliser casse, espaces et ponctuation selon la tâche ; variantes justifiées seulement.
- Ne pas accepter automatiquement une négation inversée, un autre organe ou une relation anatomique opposée.
- Ne pas exiger une phrase exacte si plusieurs formulations sont valables ; pour production libre non automatisable, proposer modèle/auto-évaluation et exclure cette réponse du score objectif.
- Séparer « avec aide », « après correction » et « réussi au premier essai ».

Tests : bonne réponse, variante légitime, réponse fausse proche, négation/sens inversé, saisie vide, correction après erreur, indice utilisé.

## 6. P2 — Construire le parcours web pilote
Dépendances : P0.1–P0.4 et schéma P1.1. Les brouillons restent réservés à l’aperçu développeur.

### P2.1 — Navigation et entrée
- Route dédiée avec accueil : objectif, durée indicative, aides de langue et absence de promesse clinique.
- Manifeste chargé avec validation d’exécution ; erreur HTTP/version/schema explicite, reprise possible.
- Autorisation d’accès au contenu approuvé : pas de simple masquage visuel ; une URL directe ne contourne pas le verrou.
- Aucun choix implicite de tous les modules via le moteur historique de quiz.

### P2.2 — Séance et feedback
- Séance courte suivant la matrice P1.3 ; afficher nombre d’étapes et possibilité d’interrompre/reprendre.
- Feedback expliquant le sens de la réponse ; aucune pénalité empêchant d’apprendre.
- Une aide ne transforme pas une réponse en réussite autonome.
- Fin de séance : concepts travaillés, réussites autonomes, aides, révisions proposées. Pas de « tu maîtrises l’anatomie ».
- Conserver les récompenses existantes hors du pilote ; au plus une progression simple, sans chantier d’économie.

### P2.3 — Persistance isolée
- Namespace et schemaVersion pilote propres ; préférences globales de langue/thème réutilisables.
- Enregistrer contentVersion, sessionId, conceptId, exerciseId, horodatage, premier résultat, aides, tentatives et état de séance.
- Réutiliser SM-2 seulement via un adaptateur explicite : les IDs pilote textuels ne doivent pas entrer par accident dans des contrats numériques.
- Horloge injectée ; calcul des dates/jours documenté, sans comparaison naïve de chaînes localisées.
- Export/import validé, confirmation avant remplacement, sauvegarde précédente si possible, reset pilote distinct du reset global.
- Données corrompues, quota ou stockage indisponible : message explicite ; ne pas annoncer une sauvegarde qui a échoué.

Tests : rechargement, reprise, double clic, double soumission, changement de langue, interruption, import invalide, reset isolé, évolution de schéma, changement de version du contenu.

### P2.4 — Accessibilité et hors-ligne
- Clavier, labels, focus, contraste vérifié, zoom et petit écran ; retour d’erreur non transmis uniquement par couleur.
- Interface arabe RTL, passages anglais LTR ; pas d’inversion des consignes anatomiques gauche/droite.
- Audio facultatif avec texte équivalent. Ne pas garantir le hors-ligne de toutes les voix Web Speech : vérifier disponibilité locale, dégradation propre et absence d’audio sans blocage.
- Aucun appel edge-TTS sans opt-in ; pas d’analytics tiers ajoutés.
- Intégrer le manifeste aux exports et au précache ; tester la seconde visite hors-ligne après cache réussi. Ne pas promettre une première visite web sans réseau.
- Éviter le préchauffage du corpus global de milliers d’exercices sur l’entrée pilote si inutile. Mesurer sur appareil modeste ; ne pas inventer un résultat de performance.

Acceptation P2 : parcours complet sur contenu approuvé ou fixtures de test, persistance correcte, absence de régression des routes historiques, évaluation accessible séparément.

## 7. P3 — Mesure honnête de l’apprentissage
Dépendances : P1 et P2. Le protocole définit les métriques AVANT les premiers résultats.

### P3.1 — Trois temps de mesure
- T0 : niveau initial avant entraînement, court et sans feedback correctif durant la mesure.
- T1 : après parcours, questions différentes, vocabulaire ciblé comparable, phrases nouvelles.
- T2 : J+7, version parallèle ; enregistrer le délai réel, ne pas prétendre que chaque test a eu lieu exactement au septième jour.
- Le stockage/cache d’une banque n’est pas l’exposition : aucun écran d’entraînement ne doit servir les items de mesure. Pas de mesure chronométrée stressante par défaut.
- Ne pas réinjecter les erreurs de mesure dans SM-2 avant la fin de la mesure ; ne pas afficher la correction de T0 avant l’entraînement.

Mesures séparées : reconnaissance, rappel sans aide, compréhension en phrase nouvelle, production guidée et usage réel.
- Score objectif = réponses correctes / items valides effectivement présentés, avec dénominateur explicite.
- Absence de test ≠ score nul. Item ambigu = invalidé et tracé, pas retiré discrètement pour améliorer les résultats.
- Production libre : grille humaine simple ou auto-déclaration explicitement distincte.
- Enregistrer entraînement supplémentaire et délai ; comparabilité des tests à relire humainement.

### P3.2 — Export local minimal
- Identifiant pseudonyme choisi pour le pilote, sans email/nom/patient réel.
- Export JSON versionné, CSV lisible si utile, avec consentement avant transmission manuelle.
- Données : temps de mesure, version contenu/test, itemId, résultat, aides autorisées, durée observée, état terminé/interrompu.
- Éviter de collecter les productions libres identifiantes ; avertissement de ne saisir aucune donnée patient/personnelle.
- Pas de télémétrie automatique. Expliquer les limites : un JSON local ne prouve pas l’absence de triche.

### P3.3 — Pilote humain
Adapter `docs/PROTOCOLE_PILOTE_ETUDIANTS.md` : le protocole historique compare histoires/quiz ; celui-ci doit mesurer le parcours anatomique, pas prétendre étudier des histoires non publiées.
- Cinq volontaires de la cible pour un premier cycle exploratoire.
- Information/consentement, retrait possible, absence d’enjeu de note universitaire, modalités de suppression et durée de conservation à définir avant collecte.
- Observer le premier démarrage sans guider, consigner les blocages sans blâmer l’étudiant.
- T0, séances, T1 et invitation T2 ; noter abandons et raisons quand disponibles.
- Décrire les résultats individuels et les limites ; cinq personnes ne démontrent ni supériorité ni efficacité générale.
- Une comparaison Anki/autre méthode est un cycle ultérieur avec temps et contenu comparables, pas une promesse de ce lot.

### P3.4 — Décisions préalables
Ces règles sont des critères opérationnels proposés, pas des seuils scientifiques :
- Stop technique si perte de données, réponse révélée dans une question notée, contenu non approuvé servi ou correction manifestement fausse.
- Retravailler l’UX si plusieurs participants ne peuvent pas terminer sans aide ; documenter le nombre exact et le blocage.
- Retravailler pédagogie si réussite entraînement élevée mais échec récurrent sur phrases nouvelles, ou rétention faible à J+7.
- Si signal encourageant et chemin fiable : itérer sur l’anatomie avant d’ouvrir un autre module.
- Avant de lancer le pilote, fixer par écrit les attentes descriptives et ne pas les réécrire après résultats pour annoncer un succès.

## 8. P4 — Android après stabilisation web
Dépendances : format stabilisé P1–P3 technique ; pas besoin d’attendre une preuve de supériorité pour préparer le port.
- Ajouter le manifeste via export déterministe et test de parité.
- Chargeur Kotlin typé, mêmes règles d’approbation/version, progression pilote isolée.
- Réutiliser Compose, TTS et SM-2 existants sans mélanger les progrès historiques ; vérifier le besoin réel d’une migration Room avant d’en créer une.
- Même liste de concepts, mêmes objectifs et règles de score ; pas besoin d’identité pixel parfaite.
- Tests métier Kotlin des mêmes fixtures ; tests UI de navigation, reprise, aides, fin et mesure.
- Tester sur appareil/émulateur : FR/AR, anglais LTR, hors-ligne, interruption, accessibilité, TTS absent et stockage.
- Ne pas modifier signature release ou publier sur un store dans ce lot.

Acceptation : artefacts identiques, tests automatisés et parcours appareil réellement exécutés. Si Android non exécutable, livraison signalée incomplète.

## 9. P5 — Documentation et livraison
- README : démarrage réellement nécessaire ; retirer les consignes Gemini héritées si leur inutilité est confirmée ; nombre de tests daté ou commande plutôt que chiffre figé.
- Corriger les annonces seed/exercices/histoires ; distinguer préparé, relu, publié et testé.
- Ajouter en tête du plan maître un renvoi vers ce cycle sans effacer les constats historiques.
- Créer `docs/PILOTE_ANATOMIE_SUIVI.md` : backlog, preuves, blocages humains, décisions, risques ouverts.
- Aucun résultat de pilote fictif. Un modèle de tableau vide porte explicitement la mention « à renseigner après observation ».
- Documenter activation/désactivation du parcours, aperçu développeur, schéma des données et export/reset.

## 10. Matrice de validation finale

### Automatisé web
Depuis `web-react`, exécuter séparément et conserver les résultats :
```sh
npm ci
npm test
npm run build
npm run check:i18n
npm run check:seed
npm run check:stories
npm run check:precache
```
Ajouter lors de l’implémentation des commandes documentées : `check:pilot` (schema/reviews/références), `check:exercise-quality` (erreurs certaines bloquantes + suspicions rapportées) et tests de parité pilote après port Android.
Ne pas présenter ces commandes proposées comme déjà existantes.

### Automatisé Android, environnement équipé
```sh
./gradlew :app:testDebugUnitTest
./gradlew :app:assembleDebug
./gradlew :app:lintDebug
./gradlew :app:connectedDebugAndroidTest
```
La dernière commande requiert appareil/émulateur ; test instrumenté trivial ≠ couverture réelle du pilote.

### Navigateurs et appareil
- FR et AR, écran mobile, clavier, zoom, retour/navigation directe.
- Chemin complet, premier essai erroné puis corrigé, aide puis réussite.
- Rechargement à mi-séance, reprise, export, reset pilote, application historique intacte.
- Cache prêt puis réseau coupé, manifeste absent/corrompu, voix absente.
- Approuvé refusé après changement de version ; draft inaccessible par URL.
- T0/T1/T2 distincts ; aucune réponse de mesure servie dans l’entraînement.

Ne pas annoncer WCAG AA ou « 100 % hors-ligne » à partir de quelques styles/tests seulement. Lister exactement les contrôles effectués.

## 11. Ordre de livraison et portes de passage

| Lot | Travail | Dépendance | Porte de sortie |
|---|---|---|---|
| A | Baseline, P0.1, P0.2, P0.4, P0.5 | Aucune | Régressions couvertes, limites d’environnement explicites |
| B | P0.3, P1.1, corpus candidat | Baseline | Sources tracées, contrôle qualité, drafts correctement isolés |
| C | Parcours web et stockage P2 | A/B, schema stable | Parcours testé avec fixtures, aucun brouillon exposé |
| D | Mesures/export P3 technique | B/C | Tests indépendants, protocole prêt, aucun résultat inventé |
| E | Relectures et préparation pilote | B–D | Approbations réelles + consentement/protocole validés |
| F | Observation étudiants et itération | E | Compte rendu descriptif, suivi J+7, décision documentée |
| G | Port Android P4 | Contrat stabilisé | Parité + vérification appareil |
| H | Documentation P5 | Au fil des lots | Assertions datées et preuves traçables |

Pas de délai arbitraire : estimer après le lot A. Les disponibilités des relecteurs/étudiants constituent de vraies dépendances, non automatisables.

## 12. Definition of done et compte rendu attendu

Un lot n’est terminé que si :
- modification minimale et diff relu ;
- tests nouveaux couvrant les défauts réellement corrigés ;
- commandes exécutées avec résultats exacts ;
- aucune régression de données/routes historiques identifiée ;
- documentation et risques ouverts actualisés ;
- distinction claire entre validation automatique, navigateur/appareil et validation humaine.

Format de réponse de l’agent :
1. Lot traité et décisions prises.
2. Fichiers modifiés et pourquoi.
3. Tests/commandes : PASS, FAIL ou NON EXÉCUTÉ, avec causes.
4. Contenu : candidat/approuvé/publiable, sans ambiguïté.
5. Risques, blocages et prochaine tâche précise.
