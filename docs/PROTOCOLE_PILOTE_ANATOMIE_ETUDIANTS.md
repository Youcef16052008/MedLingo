# Protocole du pilote Anatomie — participants humains (Lot D, P3.3/P3.4)

**Statut : protocole PRÊT, aucune donnée collectée.** Tout tableau de résultats ci-dessous
est vide et porte la mention « à renseigner après observation ». Aucun résultat ne peut
être écrit avant observation réelle.

Ce document adapte `docs/PROTOCOLE_PILOTE_ETUDIANTS.md` (protocole historique
« histoires vs quiz », phase 3.4) au **parcours anatomique** : celui-ci mesure le
parcours pilote d'anatomie, il ne prétend PAS étudier des histoires non publiées.
Le protocole historique reste en place pour le cycle histoires ; les deux ne doivent
pas être mélangés.

Dernière mise à jour : 2026-11-11 (rédaction ANTÉRIEURE aux premiers résultats —
les critères de la section 5 ne doivent PAS être réécrits après coup pour annoncer
un succès ; toute modification post-résultats doit être datée et justifiée).

---

## 1. Objectif et limites déclarées

Mesurer, de façon **descriptive**, ce que le parcours anatomique produit chez quelques
participants de la cible :

- T0 (avant entraînement), T1 (après parcours), T2 (une semaine plus tard, version
  parallèle) ;
- dimensions séparées : reconnaissance, rappel sans aide, (compréhension en phrase
  nouvelle — voir limite §2), production guidée, usage réel.

**Ce que ce protocole ne peut PAS démontrer** : cinq volontaires ne démontrent ni
supériorité, ni efficacité générale, ni équivalence avec une autre méthode. Toute
comparaison Anki/autre méthode est un cycle ultérieur avec temps et contenu
comparables — ce n'est PAS une promesse de ce cycle.

## 2. État technique de la mesure (honnêteté sur le contenu)

| Élément | État |
|---|---|
| Banques de mesure A/B/C (T0/T1/T2) | Générées (`web-react/data/pilot-anatomy-measure.v1.json`, 180 items) — **draft, en attente des deux relectures humaines** |
| Reconnaissance + rappel sans aide | Automatisées, sans feedback correctif, sans aides |
| Compréhension en phrase nouvelle | **MANQUANT** : exige des phrases NEUVES composées et relues humainement (les phrases existantes sont déjà servies à l'entraînement — les réutiliser feindrait une mesure neuve). Le moteur la supporte ; la banque ne la contient pas encore. |
| Production guidée / usage réel | Hors score objectif : grille humaine simple ou auto-déclaration EXPLICITEMENT distincte (section 4). |
| Stockage | Namespace séparé `medlingo_pilot_anatomy_measure_v1` ; les écrans d'entraînement ne servent JAMAIS les items de mesure (garde testée) ; aucune réinjection dans SM-2. |

**Conséquence pour T1/T2 :** tant que les phrases nouvelles ne sont pas composées et
relues, T1/T2 mesurent reconnaissance + rappel sur des consignes différentes (source
définition EN / description FR / ordre des distracteurs). Le comparatif « phrase
nouvelle » est reporté tant que ce manque n'est pas comblé — il ne sera pas feint.

## 3. Participants, consentement, données

- **Cinq volontaires** de la cible (étudiants en médecine) pour un premier cycle
  exploratoire.
- Information préalable : objectif, durée (T0 ≈ 5 min ; séances ≈ 5 min ; T1 ≈ 5 min ;
  T2 ≈ 5 min), absence d'enjeu de note universitaire, retrait possible à tout moment
  sans justification.
- **Consentement écrit ou oral tracé AVANT T0** ; modalités de suppression des données
  et durée de conservation **à définir et fixer par écrit avant la collecte** (elles ne
  le sont pas encore — ce document ne les invente pas).
- Identifiant : **pseudonyme choisi par le participant** (champ prévu à l'écran de
  mesure). Aucun email, nom, ni donnée patient. Avertissement affiché : « N'entrez
  aucune donnée patient ou personnelle ».
- Transmission des données : **manuelle uniquement** (export JSON/CSV depuis l'écran de
  mesure), avec consentement avant partage. Aucune télémétrie. Limite déclarée au
  participant : un JSON local ne prouve pas l'absence de triche.
- Productions libres éventuelles : ne pas collecter de texte identifiant ; ne rien
  demander qui puisse contenir une donnée patient.

## 4. Déroulement par participant

1. **Observation du premier démarrage sans guider** : consigner les blocages (où la
   personne hésite, ce qu'elle ne trouve pas) sans blâmer l'étudiant.
2. **T0** : mesure initiale, sans correction affichée, sans aide, sans chrono affiché.
3. **Séances d'entraînement** : séances courtes (3 concepts × 3 exercices), aides FR/AR
   autorisées et comptées (une aide n'est jamais une réussite autonome). Noter toute
   séance supplémentaire (entraînement non demandé par le protocole).
4. **T1** : après le parcours — consignes différentes, vocabulaire comparable. Le délai
   réel entre la fin de l'entraînement et T1 est enregistré (l'app le note ; on ne
   prétend pas que tout s'est passé « le même jour »).
5. **Invitation T2 à J+7** : version parallèle ; **le délai réel depuis T1 est
   enregistré** — jamais remplacé par « exactement 7 jours ». Relances possibles ; un
   T2 absent est consigné « absent », jamais imputé à un score nul.
6. **Production guidée / usage réel** (fin de cycle) : grille humaine simple ou
   auto-déclaration 1–5, étiquetée « auto-déclarée » ; **exclue du score objectif**.
7. Abandons et raisons consignés quand disponibles (sans forcer).

**Score objectif (définition fixée avant collecte)** : réponses correctes / items
valides effectivement présentés et traités, dénominateur explicite, par dimension et
par temps. Item ambigu ⇒ invalidé et tracé (raison), jamais retiré discrètement.
Absence de test ⇒ jamais comptée « 0 ».

## 5. Critères de décision PRÉ-ENREGISTRÉS (P3.4)

Critères opérationnels proposés — pas des seuils scientifiques. Écrits le 2026-11-11,
AVANT tout résultat.

**Stop technique immédiat** si :
- perte de données ;
- réponse révélée dans une question notée (fuite) ;
- contenu non approuvé servi en dehors de l'aperçu développeur ;
- correction manifestement fausse.

**Retravailler l'UX** si plusieurs participants ne peuvent pas terminer une séance sans
aide : documenter le **nombre exact** et le blocage précis.

**Retravailler la pédagogie** si :
- réussite d'entraînement élevée MAIS échec récurrent sur les items parallèles (T1),
  ou rétention faible à T2.

**Signal encourageant + chemin fiable** ⇒ itérer sur l'anatomie avant d'ouvrir un autre
module.

## 6. Résultats — tableau VIDE (à renseigner après observation)

| Participant | T0 reconnaissance (correct/présenté) | T0 rappel (correct/présenté) | Séances faites | T1 reconnaissance | T1 rappel | T2 reconnaissance | T2 rappel | Délai réel T1→T2 | Abandon/blocage |
|---|---|---|---|---|---|---|---|---|---|
| — | à renseigner après observation | — | — | — | — | — | — | — | — |

Compte rendu attendu : résultats **individuels et descriptifs** + limites (5 personnes,
corpus draft non relu jusqu'à validation du lot E, pas de groupe de comparaison). Aucun
« tu maîtrises l'anatomie », aucune promesse d'efficacité générale.
