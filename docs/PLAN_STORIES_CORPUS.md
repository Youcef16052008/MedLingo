# Plan du corpus d'histoires — « 3 histoires par chapitre »

**Décision produit (25/10/2026) :** toutes les histoires sont des **cas
cliniques ou des récits de patients** (situations de soin, en anglais),
couvrant le vocabulaire réel du chapitre. Plus de scénarios « salle de classe ».

## Cible

312 chapitres × 3 histoires = **936 histoires**. Rollout **module par module**,
priorité aux modules les plus cliniques :

| Vague | Module | Chapitres | Histoires |
|---|---|---:|---:|
| 1 (pilote) | Anglais Médical | 44 | 132 |
| 2 | Sémiologie Médicale | 15 | 45 |
| 3 | Terminologie Médicale | 44 | 132 |
| 4 | Cardiologie/At the Hospital & co. (regroupements chapitres cliniques des autres modules) | — | — |
| … | puis Anatomie, Physiologie, Histologie, etc. (récits de patients orientés examen/exploration) | | |
| **Total** | 15 modules | **312** | **936** |

Chapitres avec moins de 4 termes exploitables (ex. « Communication clinique » :
1 terme) sont **reportés** jusqu'à enrichissement du corpus de termes.

## Les 3 archétypes par chapitre (tous : patients / situations de soins)

| Archétype | Format | Ce qu'il entraîne |
|---|---|---|
| **A — Le cas** | Un patient se présente avec les manifestations du chapitre ; l'étudiant(e) questionne (présent/absent, depuis quand). | Reconnaissance du vocabulaire en contexte + négations |
| **B — L'explication au patient** | Un médecin explique un examen/notion du chapitre à un patient inquiet. | Compréhension orale + reformulation douce |
| **C — La passation de garde** | L'étudiant(e) résume le cas d'un patient (qui, quoi, depuis quand, absent). | Production guidée + exactitude des faits |

## Fabrication (deux niveaux, même garde-fou)

1. **Écrites main** : les 10 pilotes (`clinical-symptoms-001`…`summarize-situation-010`),
   rethématisées en récits de patients — qualité de référence.
2. **Générées** (`scripts/build_stories_corpus.py`) : pour chaque chapitre, les
   3 archétypes remplis avec les **vrais termes du chapitre** (en/fr/ar du
   corpus), déterministes, A1–A2, avec hints sur les faits négatifs.
   **Un contenu généré est un brouillon structuré** : `draft` + `provenance:
   "generated:corpus-v1"` — la relecture (docs/RELECTURE_HISTOIRES.md) reste
   obligatoire avant publication, chapitre par chapitre.

## Garde-fous (déjà automatisés)

- `storiesContent.test.ts` : structure, réponses non ambiguës, hints sur
  négations, `relatedTermIds` réels, expressions partagées cohérentes —
  s'applique à TOUT le corpus, généré compris.
- `check:stories` : parité web/Android (les deux copies sortent du builder).
- Chargeurs web/Android : drafts jamais servis ; schéma inconnu rejeté.

## Critères de progression de vague

Une vague passe à la suivante quand : la vague précédente est relue
(linguistique + médicale) pour ≥ 80 % de ses chapitres, et que le pilote
étudiants (§PROTOCOLE_PILOTE_ETUDIANTS.md) a tourné sur la vague 1.

## Journal

| Étape | Commit | Contenu |
|---|---|---|
| Vagues 4–15 — couverture complète | _(commit ci-dessous)_ (25/10/2026) | Les 12 modules restants (Anatomie→Anapath) : **268 chapitres traités / 312** (44 chapitres à 1–2 termes reportés, seuil abaissé à 3) → **804 générées, corpus total 814** (87 % de la cible 936). Asset : 7,6 Mo ×2 copies (pistes : split par module / minification si besoin). 268 fiches de relecture. Vitest 5085/5085 (contenu 4887) · gradlew 288/288 · check:stories OK. |
| Vague 3 — génération | _(commit ci-dessous)_ (25/10/2026) | Terminologie Médicale : 37 chapitres exploitables × 3 = **111** → corpus total **292 histoires** (282 générées). Nouveauté : **cadre « résultats »** pour les chapitres non symptomatiques (« The sagittal plane is not normal ») + « has a problem with the heart » en passation + article *an* + pivots substantifs (adjectifs positionnels écartés). 94 fiches de relecture. Vitest 1953/1953 · gradlew 288/288 · check:stories OK (292). |
| Vague 2 — génération | _(commit ci-dessous)_ (25/10/2026) | Sémiologie : 15 chapitres × 3 = **45** → corpus total **181 histoires**. Générateur paramétré par vague (WAVES), ids d'EXPRESSIONS globaux inter-modules (une carte SM-2 par expression), champ `chapter` pour la relecture. Qualité renforcée : pluriels sans article, priorité aux termes symptomatiques pour les cadres « has / does not have » (suffixes -algia/-uria/-pnea…), tire « Mr./Ms. » retiré. **57 fiches de relecture** Markdown générées (docs/review/stories/) — le relecteur lit une fiche par chapitre, pas le JSON. Vitest 1287/1287 · gradlew 288/288 · check:stories OK (181). |
| Vague 1 — génération | _(commit ci-dessous)_ (25/10/2026) | 10 pilotes rethématisés en récits de patients (003/007/008/009 : douleur thoracique, explication du cœur, prélèvement de peau, douleur de jambe) + **126 générées** (42 chapitres × 3 archétypes, vrais termes FR/EN/AR, hints sur négations) = **136 histoires**, toutes draft sauf 001. Chapitres à 1 terme reportés (Communication clinique, Examen clinique). Vitest 1017/1017 (contenu : 819) · gradlew 288/288 · check:stories OK. | Anglais Médical : 42 chapitres ≥ 4 termes → 126 histoires draft + 10 pilotes rethématisés |
