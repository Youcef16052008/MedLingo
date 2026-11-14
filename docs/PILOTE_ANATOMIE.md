# Pilote Anatomie — Guide d'utilisation

> Dernière mise à jour : 12/11/2026. Sources de vérité : plan
> [`PLAN_PILOTE_ANATOMIE.md`](PLAN_PILOTE_ANATOMIE.md), journal d'exécution
> [`PILOTE_ANATOMIE_SUIVI.md`](PILOTE_ANATOMIE_SUIVI.md), protocole étudiant
> [`PROTOCOLE_PILOTE_ANATOMIE_ETUDIANTS.md`](PROTOCOLE_PILOTE_ANATOMIE_ETUDIANTS.md).
> Contenu actuel : 30 concepts / 90 exercices + 180 items de mesure, version
> `0.1.0`, **100 % `draft` — 0 concept approuvé**. Ce document décrit le
> comportement du code, pas des résultats d'étude : aucune donnée d'étudiant
> n'a encore été collectée.

## 1. C'est quoi le pilote ?

Un parcours d'étude **isolé** du reste de l'application, pour mesurer si le
format « anglais anatomique » aide les PCM1, sans polluer la progression
historique :

- **Entraînement** : 3 concepts par séance, 3 exercices par concept (QCM +
  saisie libre), aide optionnelle, bilan final (réponses autonomes / avec
  aide / erreurs). **Aucun point, aucune gemme, aucun SM-2** — le verdict du
  premier essai (autonome / avec aide / erreur) est figé et jamais réécrit.
- **Mesure** : tests courts à 3 moments (T0 avant, T1 à ~7 jours, T2 à
  ~14 jours), versions parallèles A/B/C (mapping fixe T0→A, T1→B, T2→C),
  pseudonyme seulement, **aucun feedback ni score affiché pendant le cycle**,
  interruption consignée (jamais convertie en score), export manuel JSON/CSV.

## 2. Accès et verrou de publication

Un concept n'est jouable dans le parcours **réel** que si sa version courante
a les **deux relectures humaines** (linguistique + anatomique) faites sur
cette version exacte (`approved`). C'est le même verrou sur web et Android.

- **0 concept approuvé** (état actuel) ⇒ écran explicite « Aucun concept
  approuvé », **pas de bouton de démarrage** ; la partie mesure reste
  accessible.
- Après une relecture, le bouton apparaît automatiquement — rien à
  activer/désactiver dans le code : le verrou est éditorial, pas un
  interrupteur technique.

### Aperçu développeur (draft jouable, jamais en release)

- **Web** : `#/pilot?dev=1` (entraînement) et `#/pilot/mesure?dev=1`
  (mesure) — opt-in explicite via le hash, bandeau « NON approuvé » permanent.
- **Android** : build `debug` uniquement (`BuildConfig.DEBUG`), même bandeau
  permanent. Les builds release ne voient que le contenu approuvé ; aucun
  contournement possible.

### Retirer le pilote du produit

Le parcours est réversible par construction : la carte d'entrée (« 🔬 Pilote
Anatomie » sur le Parcours Android, lien web `#/pilot`) et les routes
peuvent être masquées sans toucher aux données ni aux autres modules. Les
données pilote vivent dans des espaces de stockage séparés (§4) et
disparaissent avec un reset (§5) ou la désinstallation.

## 3. Même contenu web et Android

Les manifestes `web-react/data/pilot-anatomy.v1.json` (entraînement) et
`pilot-anatomy-measure.v1.json` (mesure) sont copiés **octet-pour-octet** vers
`app/src/main/assets/pilot/` via `npm run sync:pilot` ; la garde
`npm run check:pilot-parity` échoue (exit 1) si un oubli crée une dérive.
Après toute édition de contenu : régénérer, revalider, puis re-synchroniser.

## 4. Données et rétention

- **Web** : `localStorage` clés `medlingo_pilot_anatomy_v1` (+ `_backup`) pour
  l'entraînement, `medlingo_pilot_anatomy_measure_v1` (+ `_backup`) pour la
  mesure. Un blob JSON unique (`state`, `schemaVersion: 1`).
- **Android** : `SharedPreferences` portant **les mêmes noms de clés et le
  même format JSON** — export/import interopérables web ↔ Android.
- Données stockées : ids d'exercices/items, verdicts du premier essai, usage
  de l'aide, horodatages, pseudonyme (≤ 40 caractères, libre). **Aucune
  donnée personnelle** n'est demandée ni déduite.
- Le stockage historique de l'app (Room, SM-2, quêtes) n'est **jamais** lu ni
  écrit par le pilote ; les resets sont isolés dans les deux sens.
- Tout reste sur l'appareil. La transmission est **manuelle** (§5).

## 5. Export, import, reset

| Action | Web | Android |
|---|---|---|
| Export entraînement | `medlingo-pilot-anatomy-progress.json` (téléchargement) | via partage système |
| Export mesure | `medlingo-pilot-anatomy-measure.json` / `.csv` | via partage système (JSON/CSV) |
| Import | page dédiée, version de contenu refusée si différente, backup avant | même garde (`pilot_import_version`) |
| Reset entraînement | bouton, isole la mesure | bouton, isole la mesure |
| Reset mesure | bouton, isole l'entraînement | bouton, isole l'entraînement |

Le CSV de mesure écrit une ligne par item traité ; les cellules restent
**vides** (jamais un faux 0) quand un item n'a pas été traité.

## 6. Changement de version de contenu

`contentVersion` (actuellement `0.1.0`) est recoupé entraînement ↔ mesure au
chargement (désaccord = erreur bloquante) et stocké avec la progression. Une
séance en cours liée à une autre version est invalidée à la reprise — jamais
silencieusement réutilisée. Les ids de mesure sont distincts des ids
d'entraînement (garde testée sur les données réelles).

## 7. Limites connues (12/11/2026)

- Parcours réel bloqué : aucun concept approuvé (en attente des relectures
  humaines, lot E du plan).
- Banques T1/T2 « compréhension en phrase nouvelle » : à compléter
  humainement (documenté dans le suivi §4).
- Validation sur appareil Android (tests instrumentés + parcours manuels
  FR/AR, hors-ligne, interruption, accessibilité) : **NON EXÉCUTÉ**, aucun
  appareil disponible lors du lot G.
