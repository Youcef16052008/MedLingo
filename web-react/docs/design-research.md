# MedLingo DZ — Recherche de design : éducation médicale moderne

*Date : 2026-11-05*

---

## 1. Ce que la recherche a retourné

### 1.1 Referentiels d'architecture et d'interface

| Référence | Ce qu'elle apporte | Lien |
|---|---|---|
| **NeuroAscent** (ropstam.com) | Apprentissage anatomique engamifié : WebGL 3D, SM-2, classements, badges, PWA. Le cas d'utilisation est le plus proche du noyau de MedLingo (flashcards + parcours + gamification). | https://www.ropstam.com/neuro-ascent-case-study/ |
| **VOKA 3D Anatomy & Pathology** (innowise.com) | 350 modèles 3D, dissection virtuelle, AR, hover/pinceau, mode topographie, articles cliquables → visualisation 3D est le marqueur distinctif de l'éducation médicale moderne. | https://innowise.com/case/voka-3d-anatomy-and-pathology/ |
| **Dribbble — medical education** | Cartes interactives, illustrations cliniques, dashboards d'apprentissage. | https://dribbble.com/search/medical-education |
| **Dribbble — education product** | "Lerno, Inerno, Monkis, Rymlurn" — chatbots, tuiles épurées, micro-interactions, palettes sobres. | https://dribbble.com/search/education-product |
| **Dribbble — flip card animation** | 3D flip, cartes d'apprentissage, micro-interactions de navigation. | https://dribbble.com/search/flip-card-animation |
| **Designer systems — healthcare palettes** (GEC Designs / Pencil-skill) | "Clinical Blue + Cool Gray" (clinique), "Patient Warm Teal" (chaleur), "Language Learning Vibrant" (gamification) — recommandent un système neutre + une échelle d'accent adaptée. | https://gecdesigns.com/color-palettes/healthcare |

### 1.2 Résumé des tendances à retenir

1. **3D anatomique** : l'animation 3D/VR/AR est devenue le marqueur de différenciation principal en éducation médicale (VOKA, NeuroAscent).
2. **Gamification discrète** : badges, rangées, maison de points, mais **pas** de gaîté (Neos, Duolingo le font à l'excès).
3. **Pour une app de terminology** (flashcards + quiz), le style applicable est celui des **plateformes d'apprentissage** (Khan, Brilliant, Duolingo) : tuiles épurées, transitions fluides, contrôles visibles, progression narrative.
4. **Palette** : neutre chaud ("warm neutral") + une échelle d'accent verte/teal/marine + ambre pour les alertes. Éviter le blanc pur et les rouges vifs sans contexte.
5. **Animations** : flip 3D pour les flashcards, micro-interactions de survol, décollements en cascade, animations de progression (anneaux), confettis cadrés.
6. **Accessibilité** : contraste suffisant (AA/AAA), focus visible, réduire la motion au besoin, espacements générés.

---

## 2. État actuel de MedLingo web — audit

| Fichier | Rôle | Problème |
|---|---|---|
| `src/styles.css` | Design system "Clinical Calm" (vert-éméraude / teal / canvas menthe) | Bonne base, mais surchargée de composants à la fois "santé" et "Duolingo". |
| `src/styles/premium.css` | "Premium UI/UX" sky-blue (inspiré Duolingo, Khan, Brilliant) | **Conflit** : définit des variables `--blue-*`, `--white` qui écrasent les tokens de styles.css. |
| `src/styles/duolingo.css` | Système gamification Duolingo | **Non chargé** dans `main.tsx` (laisse une base incohérente). |
| `src/styles/mobile.css` | Responsive + dark mode | OK, mais utilise `--bg/--card/--text` qui ne sont pas les tokens du design system. |
| `main.tsx` | Import des CSS | 3 fichiers concurrents. |

### Conséquences observables

- **L'heur** (`styles.css` ligne 127) utilise `color-mix` et un dégradé vert-teal → contraste mais identique à "santé".
- **Le bouton `.btn`** est défini deux fois avec deux box-shadows différents (styles.css `0 3px 0 var(--green-900)` vs premium.css `0 4px 0` 3D).
- **Le `.card`** est défini 3 fois avec des rayons et ombres différents.
- **L'icône brand** (.brand-mark) utilise un dégradé + bordure bleue #80CBC4 (Android).
- **Le `.flashcard`** utilise `perspective: 1400px` et `rotateY(180deg)` — 3D flip existant mais le léger, sans animation de "rise".
- **Le `.sm2-card`** est sombre (night) avec filet teal — contraste fort, mais isolé.
- **Le `.pearl`** (carte liminaire) est jaune clair — utilise des valeurs héritées Android.
- **Le `.module-card`** utilise `transform: translateY(-1px)` au hover — léger.
- **Les `.path-node`** ont une animation `node-pulse` — amusante mais répétitive.
- **Le `.reward-card`** utilise `reward-pop` avec `cubic-bezier(.2, 1.4, .4, 1)` — excellent.

### Ce qui manque (ématiques modernes)

- Aucune **illustration ou photographie clinique** (toutes les applications médicales modernes en imprégnent).
- Aucun **système d'icônes vectoriels** (Material Symbols, Lucide, Heroicons) — l'emoji est un proxy pauvre.
- Aucune **animation de flip 3D améliorée** (preview front franc + audio + rotation).
- Aucune **transition entre écrans** (pas de page-mounted / page-exit).
- **Dark mode** : existant (prefers-color-scheme) mais non unifié (premium.css le gère, styles.css le fait lui-même, mobile.css le fait à nouveau).
- **Spacing/typography** : échelles incohérentes (12px/14px/16px/18px/20px/22px/24px/26px).

---

## 3. Proposition de design system (single source of truth)

### 3.1 Structure

```
web-react/src/
  styles/
    tokens.css          ← NOUVEAU : variables CSS uniques (tokens)
    components.css      ← NOUVEAU : composants unifiés
    animations.css      ← NOUVEAU : animations & micro-interactions
    healthcare.css      ← NOUVEAU : légers ajouts médicaux (cards cliniques, etc.)
  main.tsx              ← importer uniquement tokens.css + components.css + animations.css
  components/
    Icon.tsx            ← NOUVEAU : composant d'icônes (Material Symbols + SVG inline)
    TermCard.tsx        ← NOUVEAU : fiche de terme moderne
    PracticeCard.tsx    ← NOUVEAU : carte d'exercice moderne
    ModuleCard.tsx      ← redéfinir avec le design system unifié
    Flashcard.tsx       ← améliorer le flip 3D
    ProgressRing.tsx    ← anneau de progression moderne
```

### 3.2 Tokens proposés

| Token | Valeur | Usage |
|---|---|---|
| `--palette-bg` | `#F0F7F4` (canvas menthe) | Fond de page |
| `--palette-surface` | `#FFFFFF` | Cartes |
| `--palette-surface-2` | `#F5FAF7` | Sous-couches |
| `--palette-line` | `#E2E8F0` | Filets (plus doux que #DFEAE4) |
| `--palette-ink` | `#0F172A` | Texte (toujours lisible) |
| `--palette-ink-2` | `#475569` | Sous-titres |
| `--palette-ink-3` | `#94A3B8` | Métadonnées |
| `--palette-primary` | `#0D9488` (teal) | Action principale |
| `--palette-primary-dark` | `#0F766E` | Hover/active |
| `--palette-success` | `#15803D` | Succès, progression |
| `--palette-warning` | `#D97706` | Avertissement |
| `--palette-danger` | `#DC2626` | Erreur |
| `--palette-accent` | `#3B82F6` (bleu) | Accents (blue soft) |
| `--palette-amber` | `#F59E0B` | Métriques, fêtes |
| `--radius-sm` | `10px` | Petits |
| `--radius-md` | `14px` | Cartes |
| `--radius-lg` | `20px` | Grosses |
| `--radius-xl` | `26px` | Héro |
| `--shadow-card` | `0 1px 2px rgba(0,0,0,.04), 0 10px 28px -18px rgba(0,15,23,.18)` | Contenu |
| `--shadow-pop` | `0 2px 6px rgba(0,0,0,.06), 0 16px 40px -20px rgba(0,15,23,.28)` | Popover/modale |
| `--font-display` | Inter | Titres |
| `--font-ui` | Inter | Corps |
| `--line-height-base` | `1.55` | Lecture |

### 3.3 Animations proposées

| Animation | Usage | Easing |
|---|---|---|
| `fade-in-up` | Débarras d'écran, cards | `cubic-bezier(.22,.61,.36,1)` |
| `fade-in` | Instructions, tooltips | `ease` |
| `pop` | Boutons, confirmations | `cubic-bezier(.2,.7,.3,1)` |
| `fly-in` | Modales | `cubic-bezier(.22,.61,.36,1)` |
| `card-flip` | Flashcards 3D | `cubic-bezier(.2,.7,.3,1)` 0.45s |
| `pulse-slow` | Nœud actif | `cubic-bezier(.4,.0,.6,1)` 1.8s |
| `shimmer` | Skeletons | `linear 1.4s infinite` |
| `confetti-fall` | Récompenses | `cubic-bezier(.2,.7,.3,1)` |

---

## 4. Priorité d'amélioration

1. **Consolider le design system** (supprimer premium.css conflictuel, unifier les tokens) — impact immédiat.
2. **Ajouter les composants médicaux** (TermCard, PracticeCard, ProgressRing) — impact visible.
3. **Améliorer les animations** (flip 3D renforcé, micro-interactions) — impact percebu.
4. **Mettre à jour les écrans** (Modules, Practice, Flash, Quiz) — lourd mais nécessaire.
5. **Dark mode unifié** — fin.
6. **Iconography system** — après la consolidation.

---

## 5. Résultat attendu

- Un design système unique et cohérent (verifier via les tests e2e).
- Des composants médicaux modernes (cartes de termes avec étymologie, clinic, mnémoniques).
- Des animations fluides et modernes (flip 3D, micro-interactions, décollements).
- Dark mode unifié et accessible.
- Un aspect plus "anté 2026" d'application d'apprentissage médical.

> Note : la génération 3D d'anatomie (Osmosis, Kenhub, Complete Anatomy, VOKA) est hors périmètre de cette tâche — le web est une PWA léger (React + Supabase), pas un outil de dissection. On se concentre sur le **système de design** et **l'interactivité**, pas sur la visualisation 3D.
