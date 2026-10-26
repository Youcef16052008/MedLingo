# @dalilk4english : méthodes observables et adaptation à MedLingo

**Date de consultation : 24 octobre 2026.**  
**Dépôt examiné : MedLingo, état de départ `4842cff`.**  
**Livrable : analyse pédagogique et proposition produit, sans modification du fonctionnement de l'application.**

## Conclusion en une minute

La meilleure idée à reprendre n'est pas « mettre une vidéo de 100 heures dans MedLingo ». C'est **organiser l'apprentissage autour d'une situation compréhensible, la faire réentendre et questionner, puis faire réutiliser ses expressions**.

Le début accessible de la vidéo de 100 heures montre une mécanique proche du **TPRS / circling** : une petite histoire, des mots en contexte, puis plusieurs questions sur la même information. Les autres ressources publiques ajoutent l'écoute en trois passages, la pratique des structures, une routine et un travail sur l'expression orale.

Pour MedLingo, je recommande :

> **Une histoire médicale originale → écoute accompagnée → questions variées sur les mêmes faits → petite transformation de phrase → production guidée → nouvelle situation → révision espacée.**

Il faut **conserver** le lexique, le SM-2, le TTS et les exercices existants. Le changement important est de les relier dans une séance cohérente, pas de fabriquer encore davantage de QCM indépendants.

---

## 1. Périmètre réel de l'analyse

### Ce qui a été consulté

- Les pages de **16 vidéos ciblées**, avec leurs titres, descriptions et chapitres lorsqu'ils existent ; inventaire en section 2.
- Le début de la transcription automatique de **15 de ces vidéos**. La vidéo sur la présentation personnelle n'a livré que ses métadonnées et sa description.
- Le descriptif complet et le début de la transcription de la vidéo de 100 heures, notamment sa première histoire et le commencement de la suivante.
- Un fil officiel expliquant les trois passages d'écoute, l'annonce officielle des 100 heures, les pages publiques des programmes et de la feuille de route.
- Le catalogue public du podcast, dont les notices d'épisodes sur le rendez-vous médical et le dentiste.
- Le code Android et React de MedLingo et les JSON de contenu présents dans le dépôt.
- Un document de formation TPRS de Blaine Ray et une référence Cambridge sur les heures d'apprentissage.

### Ce qui n'a PAS été fait

**Je n'ai pas visionné les 100 heures, ni analysé intégralement toutes les vidéos de la chaîne.** Ce rapport est une analyse documentaire ciblée, pas un audit audiovisuel exhaustif.

L'onglet YouTube `/videos` a renvoyé une erreur 401. Les pages individuelles étaient accessibles. L'outil a parfois fourni une transcription au premier chargement, puis du contenu de page ordinaire à la pagination suivante, avec un nombre de pages différent. Il n'a donc pas été possible de parcourir de manière fiable la transcription complète des longs formats.

Les transcriptions automatiques mélangent parfois l'arabe et l'anglais, omettent des phrases et contiennent des mentions parasites. Je ne les utilise pas pour certifier une prononciation, un temps de pause, une traduction exacte ou une efficacité mesurée.

### Comment lire les conclusions

- **Observation** : visible dans les descriptions, chapitres ou extraits de transcription effectivement accessibles.
- **Déclaration du créateur** : ce qu'il explique ou promet ; ce n'est pas une validation indépendante.
- **Recommandation MedLingo** : adaptation que je propose ; elle n'est pas attribuée à la chaîne.

Les vidéos supplémentaires repérées dans les recommandations YouTube, sans consultation de leur page, ne sont pas comptées comme analysées. Le contenu payant n'a pas été audité.

### Pour terminer un véritable scan exhaustif

Il faudrait disposer d'un inventaire complet des vidéos et de transcriptions exploitables, idéalement horodatées en VTT/SRT ou texte. Les fichiers vidéo de 100 heures ne sont pas nécessaires. Le travail restant serait de :

1. Recenser les identifiants, dates, descriptions, playlists et disponibilités, sans contourner les restrictions d'accès.
2. Marquer chaque entrée comme « métadonnées seules », « transcription partielle » ou « transcription complète », au lieu de traiter une vidéo repérée comme visionnée.
3. Distinguer les compilations des contenus originaux pour ne pas compter plusieurs fois la même méthode.
4. Indexer les passages qui décrivent une méthode, avec leurs horodatages réels et leur contexte ; ne pas inventer de repères depuis la seule position dans un texte.
5. Contrôler des exemples au début, au milieu et à la fin de chaque section de niveau des 100 heures, puis étendre le contrôle au corpus complet.
6. Construire une matrice vidéo → méthode → preuve → limite → adaptation MedLingo, en signalant les passages absents ou mal reconnus.

Ce rapport fournit une première matrice utile, mais ne remplace pas ce corpus complet. Un export de sous-titres ou une liste de vidéos fournis ultérieurement permettrait de poursuivre sans prétendre que les parties manquantes ont déjà été lues.

---

## 2. Inventaire des 16 vidéos consultées

**T = description/chapitres + début de transcription automatique.**  
**D = description/chapitres uniquement.**  
Les durées ci-dessous viennent des métadonnées retournées ; elles ne représentent pas du temps de visionnage effectué.

| Réf. | Vidéo / identifiant YouTube | Publication ; durée affichée | Accès | Ce que la source permet d'observer |
|---|---|---|---|---|
| V01 | « اعطيني 100 ساعة وبخليك تتعلم الانجليزي من الصفر للاحتراف » — `nSrjNHK6slw` | 17/10/2026 ; environ 100 h 42 | T, début seulement | Histoires, questions oui/non, alternatives, questions ouvertes, reprise des réponses et rappel grammatical dans la première histoire. |
| V02 | « اعطيني ٣ ساعات وبخليك تتكلم انجليزي من الصفر - دليلك الشامل » — `sAdA8XQhK70` | 31/12/2025 ; 3:47:56 | T | Compilation : sons, 350 mots avec exemples, grammaire, prononciation, formation de phrases. |
| V03 | « خطة تعلم اللغة الانجليزية / روتين الـ 30 دقيقة يوميا » — `G-nNpP1kKw4` | 30/12/2022 ; 08:42 | T | Routine des quatre compétences ; priorité à la compréhension, niveau adapté, inférence contextuelle, écriture libre. |
| V04 | « شرح اهم قواعد اللغة الانجليزية في ٢٥ دقيقة » — `r4oIBU7M2GQ` | 11/01/2023 ; 25:08 | T | Bases grammaticales et exemples ; distinction entre connaître une règle et l'utiliser. |
| V05 | « تدريب انجليزي مكثف للمبتدئين - طريقتي المفضلة » — `mqtaD1vfcOI` | 17/06/2026 ; 1:07:31 | T | Lecture accompagnée d'une histoire ; prévisualisation du vocabulaire, explication du sens et de petites structures grammaticales. |
| V06 | « تعلم معايا أهم 100 جملة في الانجليزي للمبتدئين » — `XRdJV0CyPBc` | 04/07/2024 ; 50:09 | T | Expressions par fonctions quotidiennes et explication de leur usage, pas seulement de leur traduction littérale. |
| V07 | « احترف النطق في اللغة الانجليزية زي الاجانب - الطريقة الصحيحة لتعلم الاحرف » — `Idh5jP-52Vc` | 23/01/2025 ; 1:00:34 | T | Lettres et sons, variantes de prononciation, exemples et invitation à essayer à voix haute. L'exactitude phonétique complète n'a pas été évaluée. |
| V08 | « احفظ معايا أهم ٢٠٠ كلمة في الانجليزي في ٤٠ دقيقة » — `-9eiu6F1T78` | 08/10/2024 ; 48:29 | T | Petits groupes thématiques suivis de défis de rappel ; le titre ne décrit pas à lui seul la méthode. |
| V09 | « تدريب مكثف لتكوين الجمل - طريقتي المفضلة لممارسة الانجليزي » — `DUqeoSB7Na0` | 22/07/2025 ; 35:33 | T | Connecteurs, exemples, demande de construire sa propre phrase et exercices en ateliers. |
| V10 | « اعطيني ٤٦ دقيقة وراح تتكلم وتعرف نفسك بالانجليزي باحترافية » — `pbQJ-1l3Ewg` | 20/08/2025 ; 46:22 | D | Présentation personnelle découpée en nom, âge, lieu, résidence, études, métier, loisirs, ambition. Les exercices précis n'ont pas été vérifiés. |
| V11 | « كم المدة اللي تحتاجها لتعلم الانجليزي من الصفر للاحتراف؟ » — `kaN_sk3g-rg` | 23/10/2026 ; 24:35 | T | Réponse à la question « est-ce que finir les 100 heures suffit ? » ; distinction entre niveau de départ, objectif et temps quotidien. |
| V12 | « اعطيني 60 دقيقة وبخليك تعرف مستواك في الانجليزي » — `TQyJF9GRwqo` | 17/12/2025 ; 1:01:03 | T | Présentation de LPCM : Language, Pronunciation, Confidence, Message. |
| V13 | « لا تضيع وقتك في تعلم الانجليزي بهذي الطريقة - خلاصة ١٣ سنة » — `t0xofciucDo` | 07/01/2026 ; 13:11 | T | Vocabulaire connu mais non mobilisable, communication plutôt que perfection permanente, droit à l'erreur. |
| V14 | « كيف تتعلم الانجليزي في اقل من ٦ اشهر » — `5zDuHBZ1fpk` | 15/07/2025 ; 15:24 | T | Objectif personnel et période de concentration. Certaines recommandations de forte intensité ne conviennent pas forcément à un étudiant en médecine. |
| V15 | « خطة تعلم اللغة الانجليزية من الصفر / برنامج الـ 90 يوم » — `w_4u3YEQfNA` | 07/05/2024 ; 09:36 | T | Plan de 90 minutes par jour, vocabulaire en phrases, règles appliquées, lecture/écoute accompagnée puis progression. |
| V16 | « اخيرااا سوينا بودكاست تعلم الانجليزي للمبتدئين! » — `ckWCjrEuy6A` | 02/02/2023 ; 18:28 | T | Importance accordée à l'écoute et difficulté des sources trop avancées ; récit d'expérience personnelle, pas essai comparatif. |

### Liens primaires de l'inventaire

Chaque identifiant ci-dessus correspond à la page consultée :

- V01 : https://www.youtube.com/watch?v=nSrjNHK6slw
- V02 : https://www.youtube.com/watch?v=sAdA8XQhK70
- V03 : https://www.youtube.com/watch?v=G-nNpP1kKw4
- V04 : https://www.youtube.com/watch?v=r4oIBU7M2GQ
- V05 : https://www.youtube.com/watch?v=mqtaD1vfcOI
- V06 : https://www.youtube.com/watch?v=XRdJV0CyPBc
- V07 : https://www.youtube.com/watch?v=Idh5jP-52Vc
- V08 : https://www.youtube.com/watch?v=-9eiu6F1T78
- V09 : https://www.youtube.com/watch?v=DUqeoSB7Na0
- V10 : https://www.youtube.com/watch?v=pbQJ-1l3Ewg
- V11 : https://www.youtube.com/watch?v=kaN_sk3g-rg
- V12 : https://www.youtube.com/watch?v=TQyJF9GRwqo
- V13 : https://www.youtube.com/watch?v=t0xofciucDo
- V14 : https://www.youtube.com/watch?v=5zDuHBZ1fpk
- V15 : https://www.youtube.com/watch?v=w_4u3YEQfNA
- V16 : https://www.youtube.com/watch?v=ckWCjrEuy6A

---

## 3. La vidéo de 100 heures : ce qui est vérifiable

### 3.1 Structure publiée par le créateur

Le titre et le descriptif annoncent l'apprentissage des mots et des règles à travers des histoires. L'annonce officielle précise qu'elles s'inspirent de livres à succès, avec vocabulaire en contexte et grammaire en usage. Sources : V01 et annonce du 18/10/2026. [1](https://www.youtube.com/watch?v=nSrjNHK6slw) [1](https://x.com/dalilk4en/status/2111775778379575326)

| Début dans la vidéo | Étiquette du créateur |
|---|---|
| 00:00:00 | Introduction |
| 00:05:00 | A0 — bases / alphabet |
| 19:02:16 | A1 — fondations |
| 36:57:00 | A2 — communication |
| 53:13:17 | B1 — fluidité |
| 70:13:26 | B2 — confiance |
| 86:03:46 | C1 — niveau avancé |
| 100:40:05 | Conclusion |

**Important : ce sont les chapitres annoncés, pas des niveaux pédagogiquement validés par cet audit.** La difficulté, la progression et la qualité de toutes les sections A1–C1 n'ont pas été contrôlées. « A0 » est ici l'étiquette utilisée par le créateur ; elle ne doit pas devenir une certification dans MedLingo.

### 3.2 Mécanique du début accessible

Dans le premier récit, à propos d'un incendie dans l'usine de Thomas Edison, on observe :

1. Une présentation de mots liés à la scène.
2. Quelques phrases narratives simples.
3. Une question fermée sur un fait déjà donné.
4. Une question proposant deux possibilités.
5. Une question ouverte sur ce même contexte.
6. Une réponse modèle qui reprend la structure et le vocabulaire.
7. La poursuite du récit avec une nouvelle information.
8. À la fin de cette première histoire, un rappel de formes grammaticales pratiquées et un encouragement.

Le début de la seconde histoire revient à une préparation lexicale puis à des questions. Cette récurrence est observable **dans l'échantillon**, sans démontrer que les 100 heures suivent exactement le même gabarit.

**Interprétation pédagogique :** c'est une répétition variée du sens, proche du *circling*. L'apprenant retrouve les mêmes mots dans des questions et réponses différentes au lieu de relire une liste. Cela rejoint une logique d'**input compréhensible** : travailler d'abord un message dont on saisit le sens. Il ne faut pas en déduire que l'écoute incompréhensible suffit ou que toute explication de grammaire serait inutile.

Le document de formation de Blaine Ray décrit précisément des questions répétées, notamment oui/non, alternatives et questions ouvertes, avec une priorité à la compréhension, à la répétition et à l'intérêt. Le rapprochement avec le TPRS est donc motivé ; cela ne prouve ni une affiliation officielle ni la supériorité de cette vidéo. [1](https://mena.northwestern.edu/documents/TPRS-workshop-handout.pdf)

### 3.3 Ce qu'il faut reprendre, et ce qu'il faut changer

| À reprendre | À adapter pour une application |
|---|---|
| Même information revue sous plusieurs formulations | Attendre la tentative de l'utilisateur avant de révéler la réponse. La durée réelle des pauses de la vidéo n'a pas été vérifiée. |
| Vocabulaire rattaché à une histoire | Employer des histoires originales liées aux études médicales et à la communication clinique. |
| Réponses modèles complètes | Accepter une réponse courte au début, puis proposer une reformulation plus complète. |
| Petite observation grammaticale | Une seule structure ciblée par séance, avec transformations immédiates. |
| Continuité et encouragements | Un objectif concret, une reprise simple et des aides facultatives plutôt qu'une séance interminable. |
| Répétition dans un contexte | Ajouter une situation nouvelle pour vérifier que l'apprenant ne récite pas seulement l'histoire. |

**Ne pas attribuer à cette vidéo ce qui n'y a pas été contrôlé :** un algorithme SM-2, une notation automatique de l'oral, un calendrier universel de révisions ou un protocole identique dans toutes les sections.

---

## 4. Les autres méthodes de l'écosystème : ne pas les confondre

### 4.1 L'écoute en trois passages — الجولات الثلاثة

Le fil officiel du 31/10/2023 décrit explicitement :

1. **Écouter sans prise de notes ni traduction.**
2. **Réécouter pour comprendre, avec possibilité de consulter le texte.**
3. **Réécouter attentivement sans texte ni traduction.**

Le dernier passage est une réécoute, pas automatiquement un exercice de shadowing. Le fil original a été consulté directement ; sa reproduction indexée fournit aussi ces étapes. [5](https://rattibha.com/thread/1719329102518227251)

Source originale : https://x.com/dalilk4en/status/1719329102518227251

**Pour MedLingo :** trois modes pour le même petit audio : « J'écoute », « Je comprends avec aide », « Je vérifie sans texte ». Ajouter une ou deux questions de compréhension au dernier passage. Le masquage du texte doit rester réversible, notamment pour l'accessibilité.

**Nuance importante :** V15 présente une variante de lecture accompagnée : écouter en lisant, consulter la traduction, puis recommencer sans traduction. La page du programme de fondation décrit encore ses propres tours de lecture. Il n'existe donc pas un protocole unique à fusionner sans distinction.

### 4.2 Les mots dans des phrases et les groupes d'expressions

V02, V06 et V15 insistent sur les exemples, l'usage et les phrases. V08 utilise des groupes thématiques puis des défis de rappel.

**Pour MedLingo :** ne pas se limiter à `pain = douleur`. Faire pratiquer « have pain », « the pain started… », « where does it hurt? » dans une scène cohérente. Les mots isolés restent utiles dans le lexique et les cartes ; ils ne doivent pas être la seule unité de pratique.

### 4.3 Les règles comme outils de production

V04 travaille les bases ; V09 demande de produire des phrases avec des connecteurs et de relier ses idées. V02 comporte aussi une section de formation de phrases.

**Pour MedLingo :** après une mini-explication, transformer immédiatement une phrase : personne, négation, temps ou connecteur. Par exemple : « I have… » → « She has… ». L'exercice doit vérifier le sens, pas seulement la capacité à reconnaître le nom d'une règle.

### 4.4 La routine et le point de départ

V03 présente une routine de 30 minutes répartie sur écoute, lecture, écriture et conversation. La feuille de route publique insiste sur le niveau réel, l'objectif et la réduction de la dispersion entre sources. [11](https://www.youtube.com/watch?v=G-nNpP1kKw4) [18](https://dalilk.com/roadmaps/english/)

**Pour MedLingo :** un bouton « Ma séance d'aujourd'hui », avec une tâche et une durée choisie. Pour un étudiant, une routine réaliste de 10, 20 ou 30 minutes est préférable à l'imposition d'une forte intensité.

V14 recommande notamment une période de concentration importante. Ce conseil n'est pas à copier littéralement en invitant les étudiants à sacrifier leurs études, leur sommeil, leurs relations ou leur activité physique.

### 4.5 La « pratique quadruple » — الممارسة الرباعية

La page publique du programme de conversation décrit quatre axes :

- activation du vocabulaire disponible ;
- correction des erreurs ;
- fluidité ;
- confiance.

La feuille de route reprend cette organisation. **Ce n'est pas simplement un autre nom des quatre compétences écouter/lire/écrire/parler.** [18](https://dalilk.com/roadmaps/english/)

Source complémentaire consultée : https://dalilk.com/programs/fluency

**Adaptation proposée :** comprendre et mobiliser une expression, corriger un point précis, refaire une courte production plus aisément, puis réussir un petit jeu de rôle. Ne pas reporter toute l'expression orale à la fin du cursus.

### 4.6 LPCM : une grille pratique, pas un diplôme

V12 présente les dimensions **Language, Pronunciation, Confidence, Message**, avec une notation proposée par le créateur.

Cela suggère une grille de feedback intéressante pour une présentation orale :

- Le langage est-il adapté et suffisamment correct ?
- La parole est-elle intelligible ?
- L'apprenant se sent-il capable de poursuivre ?
- Les informations demandées ont-elles été transmises ?

**Limite :** les ressources consultées n'établissent pas une validation psychométrique indépendante de cette grille. Ne pas afficher « score LPCM certifié », convertir ce score en CECR ou copier son identité commerciale.

**Adaptation médicale :** privilégier l'intelligibilité et l'exactitude du message, non l'imitation obligatoire d'un accent natif. La confiance peut être autoévaluée ; elle ne doit pas être déduite arbitrairement du volume, du regard ou de l'intonation.

### 4.7 Podcast, scénarios et répétition à voix haute

V16 explique l'intérêt donné à l'écoute et au niveau accessible. Le catalogue du podcast comporte déjà des épisodes sur le rendez-vous médical et le dentiste : le lien avec des situations de santé est donc concret. Les notices consultées ne remplacent pas une écoute complète ou une validation de leur contenu clinique. [13](https://podcasts.apple.com/ma/podcast/%D8%AF%D9%84%D9%8A%D9%84%D9%83-%D9%84%D9%84%D8%A7%D9%86%D8%AC%D9%84%D9%8A%D8%B2%D9%8A/id1662221603)

V07 invite à essayer des sons et des mots à voix haute. Pour l'app, on peut ajouter « écouter puis répéter », puis éventuellement du shadowing. Ce dernier serait **un choix d'adaptation**, et non un protocole démontré sur la totalité des vidéos consultées.

Les pages commerciales mentionnent également **Active Focus**, un coach IA et du suivi humain. Leur mécanisme complet et leurs résultats n'ont pas été audités. Ne pas présenter ces noms ou témoignages comme des preuves scientifiques.

---

## 5. Comment utiliser personnellement la vidéo de 100 heures

Ce protocole est **ma proposition pratique**, pas un programme officiel extrait minute par minute.

1. Choisir un passage accessible, pas automatiquement le début de la vidéo. Les chapitres servent de repères ; le label seul ne garantit pas que le passage convient.
2. Travailler une petite unité narrative plutôt que viser une heure de lecture continue.
3. Lors des questions, **mettre en pause avant la réponse** et essayer de répondre, même avec un mot.
4. Vérifier la réponse modèle et répéter une ou deux phrases utiles.
5. Reprendre le passage avec le texte si nécessaire, puis sans le texte.
6. Expliquer brièvement l'histoire avec ses mots ou adapter une phrase à sa vie.
7. Revenir plus tard aux expressions difficiles et vérifier qu'on les comprend dans une autre situation.

### Exemple de séance de 30 minutes

| Temps indicatif | Activité |
|---|---|
| 10 min | Petit passage nouveau, avec pauses pour répondre |
| 10 min | Reprise des parties difficiles, texte puis sans texte |
| 5 min | Dire ou écrire quelques phrases sans copier |
| 5 min | Revoir les expressions d'une séance précédente |

Si une unité demande plus de temps, la répartir sur plusieurs jours. Un débutant complet peut avoir besoin d'un texte très court, de traductions et d'un accompagnement dès la première écoute.

**Critère utile :** « Puis-je comprendre et réutiliser cette expression ? » plutôt que « Combien d'heures ai-je regardées ? »

---

## 6. Audit de MedLingo : atouts et écarts réellement présents

### 6.1 Comptage du contenu local

Comptage effectué sur `web-react/public/data/terms.json` et `exercises.json` présents au moment de l'analyse :

- **4 504 termes** répartis sur **15 modules**.
- **3 849** termes avec un exemple anglais non vide ; **655** sans exemple anglais.
- **3 734** avec un exemple français ; **3 723** avec un exemple arabe.
- **4 504** avec un champ IPA non vide — présence du champ, pas validation de son exactitude.
- **112 exercices de seed**, dont la répartition est donnée ci-dessous.

| Type de seed | Nombre |
|---|---:|
| `mcq` | 81 |
| `sentence_order` | 14 |
| `fill_blank` | 6 |
| `clinical_case` | 6 |
| `matching` | 4 |
| `reading` | 1 |
| **Total** | **112** |

**Ne pas confondre seed et volume total jouable :** le générateur de masse prévoit en plus **3 exercices par terme, soit 13 512**, selon ses gabarits et replis. Il n'y a donc pas « seulement 112 exercices dans l'app ». Ce volume généré ne démontre toutefois ni 13 512 contextes narratifs différents ni une pratique orale réelle.

Le module « Anglais Médical » contient 655 termes dans l'export actuel, tous avec un exemple anglais renseigné. Les 655 exemples anglais manquants au total sont répartis dans les autres modules ; ce n'est pas un module entier dépourvu d'exemples.

Des documents du dépôt indiquent que des définitions et traductions ont été produites automatiquement et restent à relire. Un champ non vide ne doit pas être assimilé à un contenu pédagogiquement validé :

- [Relecture des définitions](anglais-medical-pending-review.md)
- [Relecture des exemples](anglais-medical-exemples-a-relire.md)

### 6.2 Ce que l'app possède déjà

| Élément | Fichiers inspectés | Conséquence |
|---|---|---|
| Lexique avec exemples, définitions, traductions et IPA | `web-react/src/types.ts` ; entité Android `MedicalTermEntity.kt` | Réutiliser ces ressources après relecture, ne pas repartir de zéro. |
| Répétition espacée | `web-react/src/domain/sm2.ts` ; `SpacedRepetitionAlgorithm.kt` | Garder l'algorithme. L'implémentation prévoit notamment 1 → 3 → 7 jours pour les premières réussites. |
| TTS web | `web-react/src/lib/speech.ts` | Bonne base de lecture ; pas encore un orchestrateur d'écoute segmentée. |
| TTS Android et studio de prononciation | `service/TtsManager.kt` ; `AudioPronunciationStudio.kt` | Débit, callbacks et lecture déjà disponibles côté Android. |
| Parcours et leçons | `domain/lessons.ts` ; `LessonGenerator.kt` ; `PathBuilder.kt` | On peut ajouter une pratique narrative sans supprimer le parcours existant. |
| Cas et lecture | Types `reading`, `clinical_case`, champs `ctxEn/Fr/Ar` | Il existe déjà du contexte ; il manque surtout une séquence d'activités sur un même contexte. |
| Diagnostic Android | `AdaptivePlacementEngine.kt` | À conserver comme orientation, sans le transformer abusivement en certification CECR ou en test complet de l'oral. |
| FR / EN / AR et fonctionnement local | i18n React, Room, manifeste Android | Préserver les aides multilingues et l'usage hors ligne. |

Les chemins Android abrégés de ce tableau se trouvent sous `app/src/main/java/com/aistudio/medlingua/dzmed/`.

### 6.3 Les écarts qui comptent

**A. Reconnaître une réponse reste différent de l'utiliser.**  
Les niveaux 1–2 des questions générées de leçon utilisent des traductions EN→FR, EN→AR et FR→EN. C'est utile, mais insuffisant pour faire produire une phrase spontanément.

**B. Les huit questions d'une leçon ne constituent pas nécessairement une histoire.**  
Le générateur sélectionne des exercices réels puis complète depuis les termes du module. Il n'impose pas que toutes les activités réemploient les mêmes personnages, faits et expressions.

**C. Le gabarit appelé « pronunciation » est un QCM de reconnaissance de transcription IPA.**  
Dans `bulkExercises.ts`, il demande quel terme correspond à une transcription. Ce n'est ni une compréhension audio obligatoire ni une évaluation de la parole, même si l'app possède du TTS ailleurs.

**D. Le contexte du quiz peut être affiché dans la langue d'interface.**  
`QuizScreen.tsx` choisit `ctxAr`, `ctxEn` ou `ctxFr`. Pour entraîner l'anglais, une future séance doit distinguer **langue cible du contenu** et **langue des explications**. Interface française ne doit pas signifier histoire automatiquement remplacée par sa traduction.

**E. Les niveaux 1–6 sont des niveaux internes de tâches, pas A1–C1.**  
`LearningLevel.kt` les nomme vocabulaire, collocations, phrases simples, phrases complexes, textes et cas cliniques. Ne pas les renommer mécaniquement A0/A1/A2/B1/B2/C1.

**F. Les résultats sauvegardés ne décrivent pas encore les différentes compétences narratives.**  
Le store web conserve les XP, scores, leçons et cartes SM-2, mais pas un historique distinct de compréhension audio, reformulation et transfert. Le diagnostic Android repose notamment sur des choix de vocabulaire/définition et une orientation par module ; il ne mesure pas à lui seul la maîtrise orale ou clinique générale.

**Diagnostic produit : MedLingo possède déjà beaucoup de matière. La priorité est une meilleure orchestration et une validation du contenu, pas un nouveau compteur de volume.**

---

## 7. Proposition MedLingo : une séance « histoire et réemploi »

### 7.1 Un format commun à deux besoins

Ne pas réduire MedLingo à la conversation avec un patient. Son public a aussi besoin de comprendre des cours et des textes biomédicaux.

- **Voie académique :** décrire une structure, une fonction, un mécanisme ; comparer deux tissus ; résumer une explication.
- **Voie communication clinique :** se présenter, demander où et depuis quand, reformuler ce que dit une personne, transmettre des faits sans inventer un diagnostic.

Pour un premier pilote, viser des contenus de difficulté linguistique A1–A2 **estimée par un enseignant**, sans prétendre certifier le niveau de l'utilisateur.

### 7.2 La boucle proposée

1. **Objectif clair** : « demander où se situe un symptôme et depuis quand » ou « décrire la position d'un organe ».
2. **Petit contexte original** : quelques phrases, deux à cinq expressions réellement ciblées ; pas vingt nouveaux termes imposés.
3. **Écoute avec aides modulables** : les trois passages, avec possibilité de simplifier ou de garder le texte.
4. **Circling** : plusieurs formulations sur des faits déjà présentés ; réponse avant correction.
5. **Une observation grammaticale** : courte et directement utilisée.
6. **Production accompagnée** : mot, groupe de mots ou phrase selon la capacité réelle.
7. **Transfert** : mêmes structures dans un autre cas ou une autre description.
8. **Révision espacée** : expressions et difficultés importantes dans les séances suivantes.

La répétition intra-séance et le SM-2 inter-séances sont complémentaires. « Ne pas apprendre uniquement par listes » ne signifie pas « supprimer les flashcards ou la mémoire ».

### 7.3 Séance de 20 minutes — hypothèse produit

| Durée | Contenu |
|---|---|
| 3 min | Révisions dues d'expressions déjà travaillées |
| 4 min | Petite histoire : écoute, aide à la compréhension, réécoute |
| 5 min | Questions variées sur les mêmes informations |
| 3 min | Mini-point grammatical et transformations |
| 3 min | Reformulation ou jeu de rôle guidé |
| 2 min | Nouvelle situation et bilan |

Ce découpage est une proposition à tester, pas une durée d'efficacité scientifiquement garantie. Un mode court peut ne couvrir qu'une partie de la boucle, avec reprise de l'état de séance.

### 7.4 Éviter la monotonie et l'illusion de maîtrise

- Varier les questions et les personnages, pas toujours le même ordre oui/non → alternative → question ouverte.
- Réduire les répétitions faciles et revenir sur les points difficiles.
- Ne pas afficher immédiatement le texte de la bonne réponse sous un exercice censé entraîner l'écoute sans texte.
- Distinguer « activité terminée », « compréhension vérifiée » et « production autoévaluée ».
- Faire réutiliser la structure sans revoir la même histoire.
- Prévoir des aides et un mode texte accessible ; ne pas pénaliser l'absence de microphone.

---

## 8. Exemple complet, original et non extrait de la chaîne

**Statut : maquette pédagogique à relire avant publication.**  
**But : apprentissage linguistique ; aucun diagnostic ni traitement proposé.**

### Objectif

Comprendre et reformuler un symptôme, sa localisation et sa durée, en préservant la négation.

### Micro-histoire en anglais

> Sara is a medical student. She is speaking to a patient at a clinic. The patient has a headache. The headache started two days ago. The patient does not have a fever. Sara asks, “Where does it hurt?” The patient points to his forehead. Sara checks, “Your headache started two days ago. Is that right?” The patient says, “Yes.”

**Aide française, à la demande :** Sara, étudiante en médecine, échange avec un patient en consultation. Il a mal à la tête depuis deux jours, sans fièvre. Elle demande où il a mal et vérifie qu'elle a bien compris le début du symptôme.

**Aide arabe, à la demande :** سارة طالبة طب تتحدث مع مريض في العيادة. يعاني من صداع بدأ قبل يومين، وليس لديه حمى. تسأله عن موضع الألم وتتأكد من أنها فهمت متى بدأ الصداع.

Les formulations françaises et arabes sont des brouillons à faire relire, comme l'anglais.

### Expressions ciblées

- `has a headache`
- `started two days ago`
- `does not have a fever`
- `Where does it hurt?`
- `Is that right?`

### Questions : comprendre les faits avant de produire davantage

| Question | Réponse attendue / exemple |
|---|---|
| Does the patient have a headache? | Yes. / Yes, he does. / Yes, the patient has a headache. |
| Does the patient have a fever? | No. / No, he does not. |
| Did the headache start two days ago or two weeks ago? | Two days ago. |
| Where does it hurt? | His forehead. / It hurts in his forehead. |
| What does Sara check? | When the headache started. Une reformulation équivalente doit être possible. |

**Règle de feedback :** ne pas classer « No » comme faux uniquement parce que la réponse modèle est plus longue. En revanche, confondre « no fever » et « fever » change un fait essentiel.

### Mini-point de langue

- `I have a headache.`
- `He has a headache.`
- `He does not have a fever.`

L'apprenant transforme ensuite « I have… » avec `she`, puis produit une négation. On corrige une difficulté à la fois.

### Reformulation

Consigne : « Présente les faits en deux ou trois phrases. Ne propose pas de diagnostic. »

Exemple possible :

> The patient has a headache. It started two days ago. He does not have a fever.

### Transfert

Nouvelle carte : une personne signale une douleur au bras qui a commencé hier. Demander la localisation et le début du symptôme, puis confirmer les informations.

La variation empêche de réussir seulement en récitant le texte sur le mal de tête. On ne doit pas inventer une absence de fièvre dans le nouveau cas si cette information n'est pas fournie.

### Révision

Reprendre plus tard les expressions effectivement difficiles. Utiliser le planificateur de l'app ; les intervalles dépendent des réponses. Une autoévaluation orale ne doit pas se transformer silencieusement en preuve de prononciation correcte.

---

## 9. Intégration technique, sans réécrire toute l'application

**Les éléments suivants sont des travaux proposés, pas des fonctionnalités déjà ajoutées.**

### 9.1 Une couche « histoire » séparée au départ

Créer un modèle éditorial commun aux deux plateformes, au lieu de dupliquer manuellement des histoires dans Kotlin et TypeScript :

```text
LearningStory
  id + contentVersion
  moduleIds + termIds réels
  languageBand estimé, distinct du level 1..6 existant
  objectif linguistique
  segments anglais + aides FR/AR
  questions liées aux segments et aux faits
  expressions ciblées + point grammatical
  consigne de reformulation + variante de transfert
  références audio éventuelles
  provenance, droits, statuts de relecture linguistique et médicale
```

Une source JSON versionnée peut être lue comme asset Android et copiée vers le web par l'outillage. C'est une proposition d'architecture : aucun fichier de ce type n'est ajouté dans ce livrable.

### 9.2 Points d'intégration concrets

| Chantier | Existant concerné | Travail proposé |
|---|---|---|
| Références et modèles | `web-react/src/types.ts`, `ExerciseSpec.kt`, `ExerciseEntity.kt` | Ajouter un modèle d'histoire séparé. Réutiliser les choix simples sans forcer une production libre dans un QCM. |
| Séance cohérente | `domain/lessons.ts`, `LessonGenerator.kt` | À côté du mode actuel, construire des activités depuis la même histoire plutôt que depuis des termes indépendants. |
| Lecture audio | `lib/speech.ts`, `service/TtsManager.kt` | Lire par segment, répéter, ralentir, interrompre proprement, signaler les erreurs et attendre la fin réelle de lecture. |
| Affichage | Écrans de parcours/pratique et futur écran de séance | Ajouter « Ma séance », une progression d'étapes et un contrôle explicite du texte et des traductions. |
| Langues | i18n, choix de contexte de `QuizScreen.tsx` | Séparer langue d'interface, langue cible et langue d'aide. |
| Mémoire | `domain/store.ts`, `FlashcardProgressEntity.kt`, SM-2 | Stocker la progression des expressions à part, avec des clés stables et une migration ; préserver toutes les anciennes cartes. |
| Contenu partagé | `web-react/scripts/extract.mjs`, assets Android | Contrôler les identifiants, versions et parité web/Android. |
| Hors ligne | `web-react/public/sw.js`, packaging Android | Mettre en cache les nouveaux textes et médias autorisés ; tester l'audio selon les voix réellement présentes. |

### 9.3 Deux pièges de migration

**Mémoire :** Android indexe actuellement `FlashcardProgressEntity` par `termId`. Le store web associe aussi le SM-2 aux identifiants existants. Une phrase nouvelle ne doit pas emprunter arbitrairement l'identifiant d'un terme : cela corromprait la signification de la progression. Utiliser une table ou un espace de clés séparé pour les expressions.

**Parcours :** les scores web de leçon utilisent une clé `moduleId:level`. Ne pas enregistrer le score d'une histoire dans cette clé en prétendant que la leçon existante est terminée. Prévoir `storyProgress` ou un équivalent distinct, puis définir explicitement les règles de déblocage.

### 9.4 Audio, micro et IA : ordre raisonnable

1. **D'abord TTS ou audio local autorisé + contrôles de lecture.** Le TTS ne nécessite pas d'importer les voix de la chaîne. Sa disponibilité hors ligne dépend du moteur et des voix installées.
2. **Puis répétition et jeu de rôle guidé**, utilisables sans reconnaissance vocale. Afficher honnêtement « j'ai essayé » plutôt qu'une fausse note d'oral.
3. **Ensuite enregistrement local facultatif**, avec permission microphone, écoute de sa propre voix et suppression facile. Android ne demande actuellement pas `RECORD_AUDIO` ; cette fonction demanderait donc un travail explicite de permission et de confidentialité.
4. **Enfin, éventuellement, feedback automatique ou dialogue IA.** À traiter comme un projet distinct : coût, réseau, sécurité, protection des données, langues et validation.

Une transcription ASR n'est pas une note fiable de prononciation. Un modèle de langage ne doit pas être présenté comme évaluateur clinique validé. L'app Android ne demande actuellement pas `INTERNET` : ne pas détruire son fonctionnement local en imposant une IA distante à chaque séance.

Un premier jeu de rôle à embranchements peut fonctionner hors ligne, avec des phrases préparées. Il n'est pas nécessaire de construire immédiatement un chatbot.

### 9.5 Contrôles d'acceptation avant publication

- Même histoire, mêmes identifiants et mêmes faits sur Android et web.
- Aides FR/AR présentes et relues ; interface RTL testée.
- Réponse non révélée avant la tentative dans le mode sans texte.
- Réponse courte correcte acceptée lorsque le sens est préservé.
- Variantes de formulation prévues pour les questions ouvertes ; pas de comparaison exacte naïve pour une reformulation libre.
- Négation, localisation, temporalité et autres faits critiques ne sont pas inversés par le feedback.
- TTS indisponible : message clair et solution de repli, pas une séance bloquée ou une réussite inventée.
- Reprise de séance, interruption audio et navigation testées.
- Anciennes données de cartes et de leçons préservées après migration.
- Pas de note automatique d'oral affichée à partir d'un simple clic « j'ai répété ».
- Aucun contenu médical non relu présenté comme instruction clinique.

---

## 10. Priorités de réalisation

| Priorité | Livrable proposé | Pourquoi |
|---|---|---|
| P0 — contenu | **10 micro-histoires originales pilotes**, avec quelques expressions récurrentes ; une voie académique et une voie communication | Vérifier l'utilité et la qualité avant de générer des centaines de séances. |
| P0 — pédagogie | Écoute aidée, questions de circling, une transformation de phrase et une variante de transfert | C'est le cœur de l'adaptation observée, sans coût d'IA obligatoire. |
| P1 — produit | Bouton « Ma séance d'aujourd'hui », reprise, durée choisie et aides à la demande | Réduire le choix et la dispersion. |
| P1 — mémoire | Révision d'expressions contextualisées, séparée des cartes de termes | Faire le lien avec le SM-2 existant sans casser les progressions. |
| P1 — évaluation | Mesurer séparément compréhension, rappel, tentative de production et transfert | Ne pas confondre XP, activité et compétence. |
| P2 | Enregistrement local, comparaison avec modèle, évaluations humaines ciblées | Améliorer l'oral sans promettre un score automatique non validé. |
| P3 | Dialogue IA optionnel après validation du pilote | Option d'enrichissement, pas condition de départ. |

### Exemples de thèmes du pilote

**Communication :** se présenter comme étudiant ; situer un symptôme ; demander depuis quand ; demander de répéter ; confirmer les informations entendues.

**Académique :** localiser une structure ; décrire sa fonction ; comparer deux tissus ; exprimer une cause et une conséquence ; résumer un court mécanisme.

Les scènes cliniques sont des exercices de langue, pas une autorisation à réaliser un acte ou une décision de soin.

### Comment savoir si cela améliore réellement MedLingo ?

Évaluer avec des étudiants et un enseignant :

- compréhension sur un **audio nouveau**, pas uniquement sur le texte déjà travaillé ;
- rappel d'expressions après quelques jours, par exemple à J7 ;
- capacité à transmettre les faits d'une nouvelle situation ;
- nombre d'aides nécessaires ;
- rétention et abandon, en complément des résultats pédagogiques ;
- sentiment de facilité et de confiance déclaré par l'apprenant.

Comparer, à temps de pratique comparable, une séance actuelle et une séance narrative sur des contenus de difficulté voisine. Utiliser des versions différentes pour éviter la mémorisation du test et, si possible, une correction humaine sans connaissance du groupe.

Aucun gain chiffré n'est garanti ici. Les seuils de passage et les durées optimales doivent être déterminés par le pilote, pas déduits du succès d'une chaîne.

---

## 11. Limites à ne pas importer dans MedLingo

### « 100 heures = C1 » n'est pas une promesse acceptable

Un corpus peut proposer des sections de A0 à C1 sans faire passer automatiquement un débutant à C1 en une lecture. V11, publiée le 23 octobre, répond précisément à cette confusion et parle d'un travail quotidien étalé sur plusieurs mois. Ses estimations restent des déclarations du créateur, pas des durées garanties.

À titre de référence, Cambridge donne environ **700–800 heures d'apprentissage guidé cumulées** depuis le niveau débutant pour C1, avec une variabilité explicite. Il ne s'agit ni d'un minimum universel, ni d'une équivalence directe entre heures de vidéo et heures d'apprentissage guidé. [1](https://support.cambridgeenglish.org/hc/en-gb/articles/202838506-Guided-learning-hours)

Si l'app conserve un repère « 100 heures », l'afficher comme volume d'activité, jamais comme certification : à 20 minutes par jour, 100 heures représentent 300 séances de cette durée, sans présumer du niveau atteint.

### « La mémorisation ne sert à rien » serait une mauvaise lecture

Les contenus consultés utilisent eux-mêmes des répétitions, des rappels et des mots essentiels. L'idée utile est de **ne pas confondre mémorisation de listes et capacité de communication**. MedLingo doit conserver ses flashcards, en les reliant à des expressions et des situations.

### « Les erreurs n'ont pas d'importance » doit être adapté à la santé

Accepter une hésitation ou une erreur grammaticale mineure peut encourager l'expression. En revanche, perdre une négation, inverser une localisation ou modifier une durée peut changer le message. La sécurité du sens doit primer. Les doses, allergies et consignes de soin demanderaient des validations particulièrement strictes ; elles ne sont pas un bon terrain pour un premier générateur libre.

### La confiance n'est pas l'absence d'accent

Ne pas culpabiliser un accent francophone ou arabophone intelligible. Ne pas utiliser la vitesse maximale comme indicateur principal de réussite. Une communication claire et fidèle vaut mieux qu'un débit rapide qui masque une erreur.

### Gratuit à regarder ne signifie pas libre à redistribuer

- Ne pas télécharger et republier les vidéos, audios, livres, illustrations ou transcriptions dans l'app sans droits adaptés.
- Ne pas cloner la voix ou l'image du créateur, ni suggérer un partenariat inexistant.
- Les récits inspirés de livres à succès peuvent avoir plusieurs couches de droits : ne pas les importer par défaut.
- Adapter des principes pédagogiques avec **des histoires et dialogues originaux**, relus et documentés.
- Si l'on souhaite intégrer un contenu de la chaîne, obtenir l'autorisation nécessaire ou utiliser un lien/intégrateur autorisé en respectant ses conditions.

---

## 12. Sources complémentaires et traçabilité

### Sources primaires du créateur

- Annonce officielle des 100 heures, 18/10/2026 : https://x.com/dalilk4en/status/2111775778379575326
- Fil officiel sur l'écoute en trois passages, 31/10/2023 : https://x.com/dalilk4en/status/1719329102518227251
- Message du fil qui décrit précisément les trois passages : https://x.com/dalilk4en/status/1719343042656621012
- Programme de fondation, page publique consultée : https://dalilk.com/programs/foundation
- Programme de conversation, page publique consultée : https://dalilk.com/programs/fluency
- Feuille de route publique : https://dalilk.com/roadmaps/english/
- Catalogue du podcast : https://podcasts.apple.com/ma/podcast/%D8%AF%D9%84%D9%8A%D9%84%D9%83-%D9%84%D9%84%D8%A7%D9%86%D8%AC%D9%84%D9%8A%D8%B2%D9%8A/id1662221603

Les pages de programmes sont des **sources de description commerciale**, non des preuves indépendantes des taux de succès ou des délais annoncés. Aucun formulaire n'a été soumis et aucun achat n'a été effectué.

### Références de mise en perspective

- Blaine Ray Workshops, document de formation TPRS hébergé par Northwestern : https://mena.northwestern.edu/documents/TPRS-workshop-handout.pdf — passages consultés sur compréhension, répétition, intérêt et récit ; utilisé pour décrire une technique, pas pour valider tous les résultats revendiqués dans le document.
- Cambridge English, *Guided learning hours* : https://support.cambridgeenglish.org/hc/en-gb/articles/202838506-Guided-learning-hours — estimations cumulées et limites.

### Sources secondaires utilisées avec prudence

- Reproduction du fil des trois passages : https://rattibha.com/thread/1719329102518227251 — texte recoupé avec le fil officiel.
- Article du 19/10/2026 repéré pendant la recherche : https://inglezi.com/5276/learn-english-from-scratch-a-comprehensive-guide-to-making-the-most-of-100-hours-of-instruction/ — **son plan en quatre blocs de 25 heures n'est pas attribué à la vidéo et n'est pas repris comme méthode du créateur**.

### Vérification locale reproductible des principaux comptes

À lancer depuis la racine du dépôt, sur les exports présents ; s'ils doivent être régénérés, utiliser auparavant `npm run extract` depuis `web-react/` :

```python
import json
from collections import Counter
from pathlib import Path

root = Path("web-react/public/data")
terms = json.loads((root / "terms.json").read_text())
exercises = json.loads((root / "exercises.json").read_text())
print("termes", len(terms))
print("modules", len({t["module"] for t in terms}))
print("exercices de seed", len(exercises))
print("types", dict(Counter(e["type"] for e in exercises)))
for field in ("exEn", "exFr", "exAr", "ipa"):
    print(field, sum(bool(t.get(field, "").strip()) for t in terms))
```

## Décision recommandée

**Construire d'abord un petit parcours de pratique contextualisée, original et validé, sur les bases techniques déjà présentes.**

La valeur ajoutée visée n'est pas « encore plus de mots » ni « une vidéo plus longue », mais :

> **Je comprends une situation en anglais, je réponds, je reformule et je peux réutiliser ce que j'ai appris ailleurs.**
