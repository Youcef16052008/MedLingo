# MedLingo — Guide d’intégration et d’apprentissage

**Des méthodes observées chez @dalilk4english à une expérience simple pour l’étudiant.**

Version 1.0 · 24 octobre 2026 · Android et web React

> **Statut : proposition à développer.** Ce fichier décrit le produit, le parcours étudiant et les travaux techniques. Il ne signifie pas que ces fonctionnalités ont déjà été ajoutées. Les durées et les règles de progression proposées sont des hypothèses à tester, pas des garanties de résultat.

## L’essentiel

**Ne transforme pas MedLingo en lecteur de vidéos. Transforme-le en accompagnateur de pratique.**

L’étudiant ouvre une séance courte, comprend une situation médicale, entend les mêmes expressions plusieurs fois, répond, reformule, puis les réutilise ailleurs. L’application organise les retours sur ses difficultés.

```text
UNE SITUATION UTILE
        ↓
ÉCOUTER → COMPRENDRE AVEC AIDE → RÉÉCOUTER
        ↓
RÉPONDRE → TRANSFORMER UNE PHRASE → REFORMULER
        ↓
RÉUTILISER DANS UNE AUTRE SITUATION
        ↓
REVOIR AU BON MOMENT
```

**Décision recommandée :** ajouter un mode « Histoires et pratique », à côté des flashcards et des quiz existants. Commencer par une leçon complète, puis un pilote de dix leçons originales et relues. L’IA et la reconnaissance vocale ne sont pas nécessaires pour cette première version.

---

## 1. Ce qu’on reprend et comment on l’intègre

Ce guide adapte plusieurs principes distingués dans l’analyse précédente. Il ne reproduit pas une méthode officielle unique et ne prétend pas que l’ensemble des vidéos a été visionné.

| Principe | Dans MedLingo | Ce que fait l’étudiant |
|---|---|---|
| Contenu compréhensible | Texte court, expressions connues, aides à la demande | Comprendre le sens avant de chercher la perfection |
| Histoires / situations | Une scène ou un mécanisme médical cohérent par séance | Suivre une information concrète plutôt qu’une liste |
| Écoute en trois passages | Sans texte, avec aide, puis sans texte ; parcours ajustable | Reconnaître progressivement ce qu’il entend |
| Questions répétées et variées, ou circling | Questions oui/non, alternatives et questions ouvertes sur les mêmes faits | Retrouver les mêmes expressions sous plusieurs formes |
| Grammaire appliquée | Un seul point de langue suivi de transformations | Utiliser la règle immédiatement |
| Activation et confiance | Production très courte, modèles et nouvelle tentative | Oser répondre même avec peu de mots |
| Répétition espacée | Révision d’expressions en complément des termes | Revoir les difficultés sans organiser lui-même son calendrier |
| Transfert | Une deuxième situation différente | Vérifier qu’il peut réutiliser la langue sans réciter |

**À conserver :** le lexique, les modules, le SM-2, le TTS, les traductions et les exercices actuels. Ce sont les briques du nouveau parcours, pas des éléments à supprimer.

**À ne pas copier :** les vidéos, les voix, les livres et les transcriptions sans autorisation. Les scènes de l’application doivent être originales ou utilisées avec les droits nécessaires.

---

## 2. Le parcours de l’étudiant, dès la première ouverture

### 2.1 Un accueil qui demande peu d’effort

Proposer quatre choix simples, modifiables plus tard :

1. **Mon besoin :** comprendre mes cours / communiquer en situation clinique / les deux.
2. **Ma langue d’aide :** français ou arabe, indépendamment de la langue cible.
3. **Mon temps :** 10, 20 ou 30 minutes.
4. **Mon point de départ :** je débute / je comprends un peu / je veux m’entraîner davantage.

L’anglais reste la langue à apprendre. Une interface arabe ne doit pas remplacer automatiquement le récit anglais par sa traduction arabe.

Un mini-diagnostic facultatif peut ensuite proposer quelques vérifications de vocabulaire, de compréhension et de construction de phrase. Une réponse orale seulement autoévaluée doit rester identifiée comme telle. Ne pas convertir ce mini-test en certification A1–C1.

**Exemple :** Amine, étudiant en première année, choisit une aide française et 20 minutes. Il comprend quelques termes mais ne sait pas construire une phrase. L’app commence par une présentation simple, pas par un article complexe ou un diagnostic médical.

### 2.2 Un seul bouton principal

Sur « Parcours », afficher une carte prioritaire :

```text
MA SÉANCE D’AUJOURD’HUI
Comprendre un symptôme simple

20 minutes · 3 expressions ciblées
Tu vas écouter, répondre et reformuler.

[ Commencer ]

[ Reprendre ma séance ] si elle est interrompue
```

Garder les autres accès dans « Révision » et « Cours ». Ne pas ajouter dix nouveaux onglets.

### 2.3 Des actions toujours reconnaissables

Dans la séance, garder les mêmes commandes :

- Écouter / mettre en pause / réécouter.
- Voir le texte anglais.
- Comprendre en français ou en arabe.
- Écouter plus lentement.
- Je ne sais pas encore.
- Essayer, puis voir un exemple.

Le texte anglais doit rester lisible de gauche à droite, même dans une interface arabe de droite à gauche. Le masquage du texte doit être réversible pour l’accessibilité.

### 2.4 Une entrée encore plus douce pour le débutant complet

Pour une première séance, trois phrases peuvent suffire :

> My name is Lina.<br/>
> I am a medical student.<br/>
> I speak to a patient.

L’étudiant écoute, découvre le sens, répond à une question très simple et remplace « Lina » par son prénom. Une réponse d’un mot peut être une première étape acceptable.

**Ne pas imposer une production de trente secondes à quelqu’un qui découvre ses premiers mots.** La quantité demandée augmente avec la capacité observée.

---

## 3. Une séance de 20 minutes, étape par étape

Le découpage suivant est une proposition de conception. Il doit pouvoir être ralenti, interrompu et repris.

| Temps indicatif | Écran / activité | Action de l’étudiant | Action de l’application |
|---|---|---|---|
| 3 min | Je retrouve mes expressions | Répond à quelques révisions dues | Sélectionne les difficultés anciennes sans présenter un retard écrasant |
| 4 min | J’écoute et je comprends | Écoute, consulte une aide, réécoute | Présente une petite unité dans les trois modes d’écoute |
| 5 min | Je réponds | Répond à plusieurs questions sur les mêmes faits | Attend sa tentative, puis donne un feedback court et la réponse modèle |
| 3 min | Je construis | Transforme une phrase avec un point grammatical | Montre un modèle et vérifie les réponses prévues |
| 3 min | Je reformule | Dit ou écrit quelques mots ou phrases | Fournit des amorces ; distingue essai, autoévaluation et réponse vérifiée |
| 2 min | Je réutilise et je termine | Répond sur une autre situation et lit le bilan | Enregistre les difficultés et propose la suite |

### Les trois passages d’écoute

**Passage 1 — découvrir.** L’audio anglais est prioritaire. On cherche l’idée générale. Le texte reste disponible si l’étudiant en a besoin.

**Passage 2 — comprendre.** Afficher le texte anglais, souligner les expressions utiles et proposer leur explication FR/AR. Éviter un écran rempli de traductions simultanées.

**Passage 3 — reconnaître.** Réécouter en masquant les aides, si possible, puis répondre à une question. Le troisième passage est une réécoute ; ce n’est pas automatiquement du shadowing.

Pour un débutant en difficulté, l’app peut commencer avec le texte ou proposer une version simplifiée. Le protocole ne doit pas devenir une barrière.

### Les modes court et approfondi

- **10 minutes :** conserver une petite écoute, quelques questions, un essai de phrase et une variation ; réduire le volume, pas supprimer toute production.
- **30 minutes :** ajouter une reformulation ou une lecture courte ; ne pas doubler automatiquement le nombre de mots nouveaux.
- **Interruption :** enregistrer l’étape atteinte, arrêter l’audio et permettre une reprise sans tout recommencer.

---

## 4. Exemple complet : « Comprendre un symptôme simple »

**Public :** débutant accompagné ayant déjà vu une présentation simple.<br/>
**Statut éditorial :** maquette originale, à relire avant publication.<br/>
**Objectif :** comprendre un symptôme présent et un symptôme absent. Aucun diagnostic ni traitement n’est demandé.

### 4.1 Le petit récit

> Yacine is a patient. He has a headache. He does not have a fever. Lina is a medical student. She asks, “Do you have a fever?” Yacine says, “No, I do not.”

**Aide française possible :** Yacine est un patient. Il a mal à la tête et n’a pas de fièvre. Lina, étudiante en médecine, lui demande s’il a de la fièvre. Il répond non.

Préparer aussi une aide arabe relue. Les noms sont fictifs et aucune donnée de patient réel n’est nécessaire.

### 4.2 Les expressions à cibler

- `has a headache` : a mal à la tête ;
- `does not have a fever` : n’a pas de fièvre ;
- `Do you have…?` : avez-vous… ?

Le reste du texte doit rester accessible grâce aux acquis et aux aides. Au tout début, une ou deux expressions nouvelles peuvent suffire. Trois à cinq expressions est un plafond de conception à ajuster, pas une règle scientifique universelle.

### 4.3 Les questions de circling

| Question | Réponse courte acceptable | Réponse modèle possible |
|---|---|---|
| Does Yacine have a headache? | Yes. | Yes, he has a headache. |
| Does he have a fever? | No. | No, he does not have a fever. |
| Does he have a headache or back pain? | A headache. | He has a headache. |
| What symptom does he have? | A headache. | He has a headache. |
| Who is the medical student? | Lina. | Lina is the medical student. |

Ne pas poser systématiquement toutes les questions dans cet ordre. Si l’étudiant réussit facilement, réduire les répétitions et passer à la variation.

### 4.4 Ce qui se passe après une erreur

Si l’étudiant répond « Yes » à la question sur la fièvre :

1. Afficher : « Attention au sens : il n’a pas de fièvre. »
2. Mettre en évidence `does not` et rejouer cette phrase.
3. Proposer une nouvelle tentative avec les mêmes faits.
4. Enregistrer la difficulté pour une révision ultérieure.

Si l’étudiant répond simplement « No », ne pas marquer faux parce que le modèle est plus long. Proposer la phrase complète comme enrichissement.

**Important :** une correction suivie d’une bonne réponse ne doit pas effacer l’erreur initiale dans les statistiques de compréhension sans aide.

### 4.5 Un point de langue, pas un cours entier

Point ciblé : passer de soi à une autre personne.

> I have a headache.<br/>
> He has a headache.

L’app demande ensuite de compléter `She … a headache.`

La négation rencontrée dans l’histoire peut d’abord être apprise comme une expression utile. Il n’est pas nécessaire d’expliquer toute la grammaire des auxiliaires dans cette même séance.

### 4.6 La reformulation guidée

Consigne : « Présente les deux informations importantes, sans proposer de diagnostic. »

Amorces :

> He has…<br/>
> He does not have…

Production possible :

> He has a headache. He does not have a fever.

Sans correction humaine ou système d’évaluation validé, l’app peut proposer une comparaison avec le modèle, mais ne doit pas prétendre avoir certifié la justesse d’un enregistrement oral.

### 4.7 La situation de transfert

Présenter une autre personne :

> Salma has a sore throat. She does not have a fever.

Donner le sens de `sore throat` si nécessaire, puis demander :

- Does Salma have a headache or a sore throat?
- Does she have a fever?
- Présente ses symptômes en deux phrases.

L’étudiant doit réutiliser la structure et changer le vocabulaire. Si une information n’est pas fournie dans un autre cas, il ne doit pas l’inventer.

### 4.8 Le bilan réellement honnête

```text
SÉANCE TERMINÉE

Compréhension : 4 réponses sur 5 au premier essai
Point à revoir : absence de fièvre
Production : tentative effectuée, autoévaluée
Expressions à retravailler : 1

[ Terminer ]       [ Réécouter une phrase ]
```

Ces nombres illustrent un écran, pas le résultat d’un test effectué. Une réussite après aide, une tentative orale et une compréhension vérifiée doivent rester des informations différentes.

---

## 5. Comment rendre l’apprentissage plus facile et durable

### 5.1 Réduire les décisions, pas l’activité mentale

L’application choisit la prochaine petite étape. L’étudiant conserve le droit de changer de thème, de demander de l’aide ou de réduire la séance.

Le principe n’est pas « apprendre sans effort », mais **éviter les efforts inutiles** : recherche de ressources, listes démesurées, traduction de chaque mot, peur de perdre sa progression ou consignes compliquées.

### 5.2 Ajuster après les réponses

Règles proposées pour le pilote :

- **Trop difficile :** plusieurs erreurs ou aides répétées → phrase plus courte, débit plus lent, traduction et une seule difficulté à la fois.
- **Accessible avec aide :** même structure dans une petite variation, sans introduire beaucoup de vocabulaire.
- **Facile sans aide :** moins de questions répétitives et transfert plus tôt.
- **Un symptôme présent/absent est inversé :** correction explicite du sens et nouvelle vérification structurée.

Ces règles sont des choix de produit à évaluer. Une séance difficile ne doit pas entraîner automatiquement une régression globale ou l’effacement des acquis.

### 5.3 Trois états distincts

1. **Entraîné :** l’activité a été réalisée, éventuellement avec aide.
2. **Compris sans aide :** réussite sur des questions prévues, avant correction.
3. **Réemployé dans une autre situation :** résultat vérifié sur une variation ou évalué par une personne compétente.

Ajouter séparément **« production essayée / autoévaluée »**. Ne pas la transformer en preuve de maîtrise.

Ne pas déduire un niveau CECR de ces trois états. Les niveaux internes 1–6 de MedLingo restent distincts des niveaux linguistiques A1–C1.

### 5.4 Réviser des expressions, pas seulement des traductions

Exemples de cartes futures :

- entendre une expression puis identifier le sens ;
- compléter `He … a headache.` ;
- distinguer `has a fever` et `does not have a fever` ;
- reformuler une information à partir d’un modèle court.

Réutiliser le calcul SM-2 existant dans un nouvel espace de mémoire pour les expressions. Une expression peut être reconnue à l’écoute sans être encore produite facilement : conserver cette distinction.

**Détail technique important :** les premières réussites du SM-2 actuel donnent des intervalles de 1, puis 3, puis 7 jours. Si la première séance est J0 et les révisions ont lieu à l’échéance, cela donne J1, J4 et J11, pas un calendrier fixe J1/J3/J7. L’app doit calculer les dates selon les réponses réelles.

### 5.5 Une première semaine possible

| Jour | Exemple de séance | Ce qu’on cherche |
|---|---|---|
| 1 | Se présenter avec quelques phrases | Oser une première réponse |
| 2 | Révisions dues et localisation simple | Revoir et comprendre une petite variation |
| 3 | Un symptôme présent | Utiliser une expression utile |
| 4 | Un symptôme absent | Préserver la négation |
| 5 | Même structure, autre personne | Éviter la récitation |
| 6 | Petit contexte académique : fonction du cœur | Relier la pratique aux cours |
| 7 | Nouvelle situation et bilan | Observer le réemploi et les aides encore nécessaires |

C’est un exemple de routine, pas un calendrier de révisions imposé ni une promesse de niveau atteint en sept jours. L’étudiant peut rester plus longtemps sur une étape.

---

## 6. Architecture commune à Android et au web

### 6.1 Une seule source de contenu

Créer un fichier source proposé :

`app/src/main/assets/learning/stories.v1.json`

Ce chemin **n’existe pas encore comme fonctionnalité de l’app**. Le web recevrait une copie générée, par exemple dans `web-react/public/data/stories.v1.json`.

Ne pas écrire deux versions manuelles divergentes des histoires. Chaque contenu doit comporter :

- identifiant stable et version ;
- modules et termes liés ;
- objectif et difficulté linguistique estimée ;
- segments anglais, explications FR/AR et expressions ciblées ;
- questions, réponses acceptées, feedback et faits à préserver ;
- mini-point grammatical et consigne de reformulation ;
- variante de transfert ;
- provenance, droits et statuts de relecture linguistique et médicale.

Extrait illustratif de métadonnées, **pas une leçon complète à charger en production** :

```json
{
  "schemaVersion": 1,
  "storyId": "clinical-symptoms-001",
  "contentVersion": 1,
  "moduleIds": ["clinical_en"],
  "relatedTermIds": [3860, 3896, 3885],
  "languageBand": "beginner_supported",
  "publicationStatus": "draft",
  "targetPhraseIds": [
    "en:have-headache:v1",
    "en:no-fever:v1"
  ],
  "review": {
    "language": "pending",
    "medical": "pending"
  }
}
```

Dans l’export actuellement examiné, `clinical_en` désigne le module Anglais Médical ; 3860 correspond à Patient, 3896 à Headache et 3885 à Fever. Vérifier ces références automatiquement au chargement ou à la génération. Ne pas renuméroter les termes pour les besoins des nouvelles histoires.

Les termes actuels portent le **nom français** du module, tandis que le parcours utilise un identifiant comme `clinical_en`. Passer par la correspondance des modules ; ne pas comparer directement ces deux valeurs.

Un contenu `draft` reste inaccessible aux étudiants dans la version publiée. Les segments et les aides de chaque langue doivent être relus avant de rendre le contenu disponible dans cette langue.

### 6.2 Un moteur de séance testable

Séparer les règles d’apprentissage de l’affichage. Une logique commune de conception, portée en Kotlin et TypeScript, détermine :

```text
REVIEW
  → LISTEN_DISCOVER
  → LISTEN_WITH_HELP
  → LISTEN_CHECK
  → CIRCLING
  → PATTERN_PRACTICE
  → RETELL
  → TRANSFER_AND_SUMMARY
```

Les trois états d’écoute font partie du bloc de quatre minutes de la séance proposée. Ce sont des étapes, pas huit questions obligatoires ni huit nouveaux niveaux.

Le moteur reçoit une action et renvoie le nouvel état. Il ne doit pas déclencher directement l’audio, lire l’horloge ou changer les écrans. L’interface effectue ces opérations et transmet les événements réels : réponse, aide demandée, lecture terminée, pause ou sortie.

Des scénarios de test JSON communs permettront de vérifier que les versions Kotlin et TypeScript prennent les mêmes décisions.

### 6.3 Trois ensembles de données séparés

| Ensemble proposé | Contenu | Pourquoi le séparer |
|---|---|---|
| Préférences d’apprentissage | Temps choisi, objectif, langue d’aide | Ne pas transformer l’objectif quotidien actuel en XP en objectif exprimé en minutes |
| Progression de séance | Session, histoire, version, étape, réponses, aides, récompense déjà attribuée | Reprendre sans perdre l’état et éviter des XP dupliqués |
| Mémoire d’expression | Identifiant d’expression, compétence travaillée, échéance, aide et type de preuve | Préserver les cartes de termes et distinguer reconnaissance et production |

Employer des identifiants d’expression partagés quand la même expression revient dans plusieurs histoires. Ne pas créer cinq cartes indépendantes par accident pour la même expression.

Une autoévaluation peut aider à planifier une révision, mais doit rester étiquetée `selfReported`, pas devenir une prononciation vérifiée.

Une session interrompue reprend avec la version de contenu enregistrée. Si cette version n’est plus disponible, expliquer pourquoi cette seule séance doit être reprise ; conserver les autres acquis et ne pas attribuer deux fois sa récompense.

---

## 7. Intégration concrète dans le web React

Les chemins de cette section sont relatifs à `web-react/`. Les nouveaux noms sont des propositions, pas des fichiers déjà créés.

| Travail | Fichiers existants à relier | Ajout proposé |
|---|---|---|
| Définir les données | `src/types.ts` comme référence des termes | Modèles dédiés dans `src/learning/types.ts` |
| Charger les histoires | `src/data/load.ts`, `scripts/extract.mjs` | Chargeur dédié et validation du contenu partagé |
| Organiser la séance | `src/domain/lessons.ts` reste inchangé au départ | Moteur `src/learning/session.ts` et sélection de séance |
| Afficher la pratique | Écrans dans `src/screens/` | `StorySessionScreen.tsx` et composants de phrases/questions |
| Ouvrir la séance | `src/App.tsx`, `src/lib/router.ts` | Route proposée `#/story/:id` et gestion du paramètre |
| Donner un accès simple | `PathScreen.tsx`, `PracticeScreen.tsx` | Carte « Ma séance d’aujourd’hui » et « Reprendre » |
| Lire l’anglais | `src/lib/speech.ts` | Orchestration par segment, débit, fin réelle et erreurs |
| Persister | `src/domain/store.ts` | Préférences, progression d’histoires et mémoire d’expressions |
| Traduire les consignes | `src/i18n/` | Nouvelles clés FR/EN/AR, séparées du texte cible |
| Fonctionner hors ligne | `public/sw.js` | Nouveau JSON et médias autorisés mis en cache |

### Points à ne pas oublier

- Dans `activeTab()`, associer la nouvelle route `story` à l’onglet « Révision » si c’est le choix retenu ; sinon le routage actuel peut sélectionner « Parcours » par défaut.
- Ajouter les nouveaux champs dans les valeurs par défaut, leur validation dans `revive()` et les copies nécessaires dans `update()` ; ne pas seulement ajouter une interface TypeScript.
- Conserver les clés actuelles du SM-2 et `lessonBest`. Une histoire terminée ne doit pas déclarer automatiquement une ancienne leçon `moduleId:level` réussie.
- Préserver les comportements actuels des boutons de prononciation lors de l’évolution de `speech.ts`.
- Le mode histoire doit explicitement lire l’anglais, même si la langue d’interface est l’arabe. Ne pas réutiliser aveuglément une fonction qui choisit la voix selon l’interface.
- Arrêter la narration quand l’écran est quitté. Ne pas passer à l’étape suivante sur un simple délai supposé de lecture.
- Versionner le cache et vérifier que le nouveau JSON est bien embarqué. Ne pas annoncer une mise à jour disponible hors ligne avant qu’elle ait réellement été chargée.

**Choix recommandé :** garder ce mode séparé des six types historiques d’exercices au début. Certaines questions peuvent réutiliser les composants QCM, mais une reformulation libre ne doit pas être forcée dans une comparaison exacte de chaînes.

---

## 8. Intégration concrète dans Android

Le préfixe Kotlin existant est :

`app/src/main/java/com/aistudio/medlingua/dzmed/`

| Travail | Existant concerné | Ajout ou évolution proposé |
|---|---|---|
| Contenu local | Assets de l’application | Lire le JSON partagé dans un dépôt de contenu dédié |
| Règles de séance | Couche `domain/` | Moteur de séance indépendant de Compose |
| Écran | `ui/screens/` | Nouvel écran Compose de pratique narrative |
| Navigation | `MainActivity.kt` | Ouvrir/fermer la séance et gérer le bouton Retour |
| État | `ui/viewmodel/` | ViewModel dédié ou état isolé, conservant les responsabilités existantes |
| Voix | `service/TtsManager.kt` | Réutiliser débit, arrêt et callbacks de lecture |
| Progression | Room, DAO, repository | Nouvelles données de séance et mémoire d’expressions |
| Interface | `localization/`, écrans Parcours/Révision | Consignes et aides FR/EN/AR, anglais en LTR |

### Navigation : partir du code réellement présent

Dans l’état examiné, `MainActivity.kt` utilise notamment une destination principale et des états d’ouverture de quiz/flashcards. Ajouter l’histoire dans ce mécanisme ou refactoriser explicitement ; ne pas supposer qu’un `NavHost` pilote déjà ces écrans.

Le bouton Retour doit d’abord quitter ou suspendre la séance, arrêter le TTS et préserver sa progression, avant de revenir au parcours principal.

### Migration de données non destructive

La base Room inspectée est en **version 12**. Si aucune autre évolution n’intervient entre-temps, l’ajout de nouvelles tables demanderait une migration **12 → 13**. Sinon, utiliser la prochaine version appropriée.

Prévoir des entités dédiées, par exemple `StorySessionProgressEntity` et `PhraseProgressEntity`, ainsi qu’un stockage des préférences. Leurs noms sont proposés, pas existants.

**Interdictions pratiques :** ne pas supprimer la base, ne pas ajouter un repli de migration destructif, ne pas remplacer les `termId` des anciennes flashcards par des identifiants d’expression. Les XP, signets, séries et progressions déjà acquises doivent rester intacts.

### Hors ligne et microphone

Le TTS peut fonctionner avec des voix locales, mais leur disponibilité dépend de l’appareil et des packs installés. Signaler une voix manquante et fournir un mode texte honnêtement identifié. Une activité effectuée sans audio ne valide pas une compétence d’écoute.

Pour la première version, l’étudiant peut parler sans enregistrement. Un futur enregistrement local demanderait une permission microphone, un usage expliqué, une suppression simple et une politique de conservation. Ne pas enregistrer ni envoyer l’audio par défaut.

---

## 9. Mesurer l’apprentissage sans fabriquer de faux scores

### Ce que la première version peut vérifier

- Un choix correct parmi des réponses préparées.
- Une réponse courte appartenant aux variantes explicitement acceptées.
- Une transformation grammaticale dont les réponses ont été prévues.
- Une compréhension structurée sur la variante de transfert.
- Le recours à une aide et le nombre de tentatives.

### Ce qu’elle ne peut pas prétendre vérifier automatiquement

- La qualité réelle d’une voix à partir d’un clic « j’ai répété ».
- L’exactitude de toutes les informations d’un récit oral libre sans évaluateur adapté.
- Un niveau CECR global à partir de quelques QCM.
- La compétence clinique d’un étudiant à partir d’un exercice de langue.

Pour les réponses préparées, normaliser les espaces, la casse et la ponctuation prévue. **Ne pas supprimer les négations ni transformer les nombres pour faire passer une réponse incorrecte.**

### Récompenses et bilan

Donner une récompense de participation raisonnable, puis afficher séparément les résultats de compréhension. Ne pas punir la demande d’aide ni rendre une erreur bloquante indéfiniment.

L’attribution d’une récompense doit être idempotente : revenir sur le bilan ou reprendre une session ne doit pas ajouter plusieurs fois les mêmes XP.

Mesurer les minutes de pratique de façon distincte des XP ; ne pas compter une boucle audio infinie ou un écran laissé ouvert comme une preuve de maîtrise.

### Vérifier l’utilité avec des étudiants

Après un petit pilote, observer :

1. Comprennent-ils un audio nouveau de difficulté comparable ?
2. Réutilisent-ils les expressions quelques jours plus tard ?
3. Transmettent-ils les faits d’une autre situation sans les inverser ?
4. Ont-ils besoin de moins d’aides ?
5. Trouvent-ils la séance plus claire et plus facile à reprendre ?

Comparer les formats à durée de pratique similaire. Des XP plus élevés ou une série plus longue ne suffisent pas à démontrer un meilleur apprentissage.

---

## 10. Ordre de réalisation recommandé

### Étape A — Une leçon complète avant tout

**Livrer :** une histoire originale, ses aides, cinq questions, un point de langue et une variation. Faire relire l’anglais, les traductions et le sens médical.

**Validation :** un étudiant comprend ce qu’il doit faire et peut finir la boucle avec ou sans aide. Aucun contenu `draft` ne doit être servi par erreur en production.

### Étape B — Une intégration verticale

**Livrer :** ouverture depuis « Ma séance », lecture segmentée, questions, bilan et reprise après interruption sur les deux plateformes.

**Validation :** même contenu et mêmes règles Android/web ; audio arrêté à la sortie ; ancienne progression conservée.

### Étape C — La mémoire et l’adaptation

**Livrer :** révisions d’expressions, niveau d’aide mémorisé, distinction participation/compréhension/production.

**Validation :** pas de collision d’identifiants, échéances reproductibles avec horloge injectée, aides non assimilées à une réussite sans aide.

### Étape D — Un pilote de dix contenus

| Contenu | Besoin travaillé | Modules possibles |
|---|---|---|
| 1 | Se présenter comme étudiant | `clinical_en` |
| 2 | Localiser une structure | `anat` |
| 3 | Comprendre un symptôme présent | `clinical_en` |
| 4 | Comprendre l’absence d’un symptôme | `clinical_en`, `semio` |
| 5 | Demander quand cela a commencé | `clinical_en`, `semio` |
| 6 | Reformuler et confirmer | `clinical_en` |
| 7 | Décrire la fonction d’un organe | `physio` |
| 8 | Décrire un tissu simplement | `histo` |
| 9 | Relier cause et conséquence | `physio` |
| 10 | Résumer une situation nouvelle | Selon le parcours choisi |

Cette liste est un catalogue pilote, pas dix jours obligatoires ni une progression CECR validée. Les contenus plus difficiles ne sont proposés que lorsque les prérequis sont accessibles.

### Étape E — Enrichissements, seulement si utiles

Enregistrement local, feedback humain, puis éventuellement dialogue IA et reconnaissance vocale. Les coûts, la confidentialité, le réseau et la fiabilité doivent être évalués séparément. La version de base doit rester utilisable sans IA.

---

## 11. Checklist avant publication

### Contenu et pédagogie

- [ ] Une situation claire et un objectif mesurable par séance.
- [ ] Peu de nouvelles expressions, avec aides disponibles.
- [ ] Histoires originales ou droits d’utilisation documentés.
- [ ] Anglais et aides FR/AR relus ; sens médical vérifié.
- [ ] Réponse modèle révélée après la tentative dans le mode de vérification.
- [ ] Réponse courte correcte acceptée lorsqu’elle suffit.
- [ ] Variante de transfert qui ne demande pas d’inventer des faits.
- [ ] Aucune promesse « 100 heures = C1 » ou « apprentissage sans effort ».

### Technique et accessibilité

- [ ] Source de contenu et identifiants identiques Android/web.
- [ ] Migration Room et rechargement de l’ancien store web testés sans perte.
- [ ] Révisions d’expressions séparées des anciennes cartes de termes.
- [ ] TTS : pause, réécoute, débit, erreurs et interruption testés.
- [ ] Reprise de séance et changement de version de contenu traités explicitement.
- [ ] Interface AR en RTL, contenu anglais en LTR, boutons utilisables au clavier et par lecteur d’écran.
- [ ] Pas de microphone obligatoire ; pas d’enregistrement à l’insu de l’étudiant.
- [ ] Version publiée du cache testée hors ligne après chargement réel.
- [ ] Récompense unique par attribution prévue ; pas de doublon à la reprise.

### Mesure et sécurité

- [ ] Premier essai, aides et corrections distingués.
- [ ] Tentative orale et autoévaluation non présentées comme note de prononciation.
- [ ] Négations, localisations et durées préservées dans les réponses structurées.
- [ ] Les activités de langue ne proposent pas de diagnostic ou de traitement personnalisé.
- [ ] L’accent n’est pas pénalisé lorsqu’il reste intelligible.
- [ ] Le temps de pratique ne sert pas de certification de compétence.

### Vérifications à exécuter pendant l’implémentation

Les commandes suivantes existent dans le dépôt ; ce guide ne prétend pas les avoir exécutées pour une fonctionnalité qui n’est pas encore développée :

```bash
# Depuis web-react/
npm test
npm run check:i18n
npm run check:seed
npm run build

# Depuis la racine, avec JDK et SDK Android disponibles
./gradlew :app:testDebugUnitTest
```

Ajouter des tests du moteur de séance, des variantes de réponse, des migrations, du TTS indisponible et des parcours de reprise. Les tests existants ne suffisent pas à valider un nouveau mode d’apprentissage.

---

## 12. Décision finale et références

**La première chose à construire :** une seule séance médicale courte, complète et validée, avec écoute, compréhension, questions, reformulation et nouvelle situation.

**Le résultat recherché pour l’étudiant :**

> « Je sais quoi faire aujourd’hui. Je comprends ce que j’écoute. Je peux demander de l’aide sans être puni. Je réutilise quelques expressions utiles et l’application m’aide à les revoir. »

Cette simplicité demande de bien organiser la pratique ; elle ne signifie pas supprimer tout effort ni garantir un niveau après un nombre fixe d’heures.

### Provenance de l’adaptation

L’analyse des contenus accessibles, ses limites et l’inventaire des sources figurent dans [ANALYSE_DALILK_MEDLINGO.md](ANALYSE_DALILK_MEDLINGO.md). Ce guide en constitue une proposition d’intégration produit et technique, pas une nouvelle revendication de visionnage exhaustif de la chaîne.

Repères pour retrouver les sources primaires utilisées dans cette analyse :

- Vidéo de 100 heures : https://www.youtube.com/watch?v=nSrjNHK6slw
- Fil sur les trois passages d’écoute : https://x.com/dalilk4en/status/1719329102518227251
- Routine quotidienne : https://www.youtube.com/watch?v=G-nNpP1kKw4
- Feuille de route publique : https://dalilk.com/roadmaps/english/
- Formation TPRS, document de Blaine Ray : https://mena.northwestern.edu/documents/TPRS-workshop-handout.pdf

**Périmètre de cette livraison : documentation uniquement. Aucune de ces évolutions applicatives n’est implémentée par la création de ce fichier.**
