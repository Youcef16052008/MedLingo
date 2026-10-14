---
version: alpha
name: Clinical Calm
description: Design system médical de MedLingo DZ — vert émeraude frais, canvas menthe, Inter, hiérarchie scannable. Structure des écrans = parité Android.
colors:
  primary: "#146B43"
  secondary: "#0D9488"
  tertiary: "#15803D"
  neutral: "#F0F7F4"
  surface: "#FFFFFF"
  surface-2: "#F5FAF7"
  line: "#DFEAE4"
  ink: "#0C1414"
  ink-2: "#475050"
  ink-3: "#6B7877"
  success: "#1E9E62"
  warning: "#D97706"
  danger: "#DC2626"
  deep: "#0B4A2E"
  mint: "#6EE7B7"
typography:
  h1:
    fontFamily: Inter
    fontSize: 1.5rem
    fontWeight: 800
    lineHeight: 1.2
    letterSpacing: "-0.015em"
  h2:
    fontFamily: Inter
    fontSize: 1.15rem
    fontWeight: 700
    lineHeight: 1.3
    letterSpacing: "-0.015em"
  body-md:
    fontFamily: Inter
    fontSize: 1rem
    fontWeight: 400
    lineHeight: 1.55
    letterSpacing: "0em"
    fontFeature: "tnum"
  ui:
    fontFamily: Inter
    fontSize: 0.875rem
    fontWeight: 500
    lineHeight: 1.5
  label:
    fontFamily: Inter
    fontSize: 0.8125rem
    fontWeight: 700
    lineHeight: 1.5
    letterSpacing: "0.02em"
rounded:
  sm: 10px
  md: 14px
  lg: 22px
  xl: 26px
spacing:
  sm: 8px
  md: 16px
  lg: 24px
components:
  button-primary:
    backgroundColor: "{colors.tertiary}"
    textColor: "#FFFFFF"
    rounded: "{rounded.md}"
    padding: 12px
  button-primary-hover:
    backgroundColor: "{colors.primary}"
    textColor: "#FFFFFF"
    rounded: "{rounded.md}"
    padding: 12px
  card:
    backgroundColor: "{colors.surface}"
    textColor: "{colors.ink}"
    rounded: "{rounded.lg}"
    padding: 16px
  chip:
    backgroundColor: "{colors.surface-2}"
    textColor: "{colors.ink-2}"
    rounded: "{rounded.sm}"
    padding: 6px
---

## Overview

Clinical Calm — le système visuel de MedLingo DZ (web). Objectif : un outil
d'apprentissage de l'anglais médical qui inspire la confiance clinique sans la
froideur des logiciels hospitaliers. Recherche UX santé appliquée : canvas teinté
menthe (pas de blanc pur — « essential to clinical warmth »), vert émeraude pour
la santé et la progression, teal clinique pour les états informatifs, hiérarchie
scannable en 2 secondes (« scan, don't study »).

La structure des écrans reste alignée sur l'app Android (parité composants) ;
seuls les jetons visuels ont été rafraîchis.

## Colors

- **primary (#146B43)** : vert médical profond — héros, dégradés, en-tête de marque.
- **tertiary (#1E9E62)** : vert d'action — boutons, liens, états actifs.
- **secondary (#0D9488)** : teal clinique — états informatifs, focus, accents froids.
- **neutral (#F0F7F4)** : canvas menthe — fond de page, chaleur clinique.
- **surface (#FFFFFF) / surface-2 (#F5FAF7)** : cartes et sous-surfaces.
- **line (#DFEAE4)** : filets vert-tendu, discrets (jamais de bordures lourdes).
- **ink (#0C1414) / ink-2 / ink-3** : encre près du noir, déclinée en vert.
- **success / warning / danger** : états de score (vert/ambre/rouge).
- Palette d'accent Duolingo (#58CC02, #FFC800, #1CB0F6, #FF4B4B, #CE82FF)
  conservée pour les surfaces de gamification (nœuds du parcours, anneaux
  d'objectif, récompenses).

## Typography

Inter pour tout (UI + display) : grande hauteur d'x, excellente lisibilité aux
petites tailles, **chiffres tabulaires** pour les scores/XP/séries — les nombres
doivent être lisibles précisément (règle typographique santé). IBM Plex Sans
Arabic prend le relais pour `lang="ar"`. Titres en 700/800 avec interlettrage
resserré (-0.015em) pour une hiérarchie nette. Aucun poids fin pour le corps.

## Layout

Colonne unique max 560px (format téléphone), canvas menthe, cartes blanches à
filet fin + ombre douce en couches (`--shadow-card` : suggère la profondeur sans
lourdeur). Espacement généreux, rythme vertical régulier.

## Elevation & Depth

Deux niveaux seulement : `--shadow-card` (contenu) et `--shadow-pop` (modales,
pop-ups). Pas d'ombres lourdes ni de bordures épaisses.

## Shapes

Échelle 10 / 14 / 18 / 22 / 26 px — plus généreuse que l'ancienne (8/12/16/20),
plus accueillante. Les éléments circulaires (avatars, anneaux, médailles)
restent à 50 %.

## Components

`button-primary` est la seule action pleine d'un écran. `card` est le conteneur
universel (blanc, filet `--line`, rayon 22). `chip` pour les métadonnées
(fond surface-2). Les statuts de score suivent toujours succès/attention/danger.

## Do's and Don'ts

- **Do** : chiffres tabulaires partout ; canvas menthe ; hiérarchie en 3 niveaux
  d'encre ; un seul vert d'action par écran.
- **Don't** : blanc pur en fond ; bordures lourdes ; plus de deux familles de
  police ; poids fins pour le corps ; couleurs hors palette pour les statuts.
