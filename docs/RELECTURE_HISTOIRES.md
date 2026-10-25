# Relecture des histoires — feuille de contrôle (phase 3.2)

**Règle (guide §6.1) :** un contenu `draft` n'est **jamais** servi. Chaque
histoire passe à `published` uniquement quand les deux relectures ci-dessous
sont validées, puis les deux copies (web + asset Android) sont resynchronisées
(`npm run check:stories` doit rester vert).

## Procédure par histoire

1. **Relecture linguistique (anglais + aides FR/AR)** — vérifier :
   - segments anglais naturels et au niveau A1–A2 annoncé ;
   - aides FR/AR fidèles (pas de contresens, pas de négation inversée) ;
   - questions ET réponses acceptées grammaticalement correctes ;
   - contractions/variantes acceptées cohérentes (`checkAccepted` normalise
     casse/ponctuation, PAS les négations) ;
   - interface arabe : le contenu anglais doit rester LTR (déjà forcé par le code).
2. **Relecture médicale** — vérifier :
   - exactitude factuelle des descriptions anatomiques/physiologiques ;
   - **aucun** conseil de diagnostic ou de traitement (objectif de langue) ;
   - les faits critiques (négation, localisation, durée) ne sont jamais
     ambigus dans les questions (`hint` présent — testé automatiquement) ;
   - noms de personnages fictifs, aucune donnée de patient réel.
3. **Publication** : dans `web-react/scripts/build_stories_p3.py`, passer
   `publicationStatus` à `"published"` pour l'histoire + `review.language` /
   `review.medical` à `"approved"` (initiales + date dans ce fichier), puis :
   ```bash
   cd web-react && python scripts/build_stories_p3.py && npm run check:stories
   ```

## État (24/10/2026)

| Histoire | Statut | Linguistique | Médical | Relecteur·rice | Date |
|---|---|---|---|---|---|
| clinical-symptoms-001 | **published** (pilote dev) | pending | pending | — | — |
| self-intro-002 | draft | pending | pending | | |
| locate-structure-003 | draft | pending | pending | | |
| symptom-absent-004 | draft | pending | pending | | |
| when-started-005 | draft | pending | pending | | |
| reformulate-confirm-006 | draft | pending | pending | | |
| organ-function-007 | draft | pending | pending | | |
| tissue-simple-008 | draft | pending | pending | | |
| cause-consequence-009 | draft | pending | pending | | |
| summarize-situation-010 | draft | pending | pending | | |

> Note : `clinical-symptoms-001` est publiée pour le pilote développeur ;
> elle doit passer la même relecture avant toute mise en store.

## Corpus généré (vague 1 — Anglais Médical, 126 histoires)

Les histoires `provenance: "generated:corpus-v1"` (`clinical-en-case/explain/handover-NNN`)
sont des brouillons structurés produits par `scripts/build_stories_corpus.py`
à partir des vrais termes de chaque chapitre (3 archétypes : le cas,
l'explication au patient, la passation de garde — voir
[PLAN_STORIES_CORPUS.md](PLAN_STORIES_CORPUS.md)).

Relecture par lot (un chapitre = 3 histoires, mêmes 3 termes) — **les fiches
lisibles sont déjà exportées dans [docs/review/stories/](review/stories/)**
(un fichier par chapitre, régénérées par `python scripts/export_story_review.py`) :
1. corriger articles/genre des phrases générées (`a chest pain` → `chest pain`, etc.) ;
2. vérifier les aides FR/AR (issues des champs `fr`/`ar` des termes) ;
3. ajuster les faits si un terme ne se prête pas au cadre (ex. terms administratifs) ;
4. publier le chapitre : `publicationStatus: "published"` + `review: approved`
   dans `build_stories_corpus.py` (ou éditer le JSON après génération), puis
   resynchroniser les deux copies (`check:stories`).
Le relecteur note sa progression ci-dessous.

| Chapitre | Histoires | Linguistique | Médical | Relecteur·rice | Date |
|---|---|---|---|---|---|
| _(42 chapitres — remplir au fur et à mesure)_ | | | | | |

## Garde-fous automatiques (déjà en place)

- `storiesContent.test.ts` (63 tests) : structure, réponses non ambiguës,
  hints sur faits négatifs, `phraseIds` connus, `relatedTermIds` réels,
  libellés identiques des expressions partagées.
- `check:stories` : parité web/Android.
- Le chargeur (web et Android) rejette les `draft` et les schémas inconnus.
