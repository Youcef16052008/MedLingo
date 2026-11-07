# MedLingo DZ — Résultats de recherche & 5 maquettes

*Date : 2026-11-05*

---

## 1. Synthèse de la recherche (mailles à retenir)

| Domaine | Ce qui a été trouvé |
|---|---|
| **Architecture** | NeuroAscent (gamified anatomy, WebGL 3D, SM-2, PWA) — cas le plus proche; VOKA 3D (350 modèles 3D, AR, dissection); Kenhub (atlas interactif, quiz, articles). |
| **Animations** | Flip 3D cards, micro-interactions hover, décollets en cascade, animations de progression (anneaux), confettis cadrés, shine effects sur progress bars. |
| **Design systems** | 3 familles : "Clinical Blue + Cool Gray" (clinique), "Patient Warm Teal" (chaleur), "Language Learning Vibrant" (gamification). Recommandation : neutre chaud + échelle d'accent verte/teal/ambre. |
| **Tendances 2025-2026** | Minimaliste, dark mode, accessibilité WCAG, champs de lecture (scroll-learning), 3D/AR, gamification discrète. |
| **XUI santé** | 3-tap maximum, touches tactile ≥44px, contraste WCAG AA, police sans-serif lisible, icônes universelles. |

**Sources** : ropstam.com/neuro-ascent-case-study, innowise.com/voka-3d, dribbble.com/search/medical-education,
dribbble.com/search/education-product, dribbble.com/search/flip-card-animation, lollypop.design/blog/2025/august/top-education-app-design-trends-2025.

---

## 2. L'état actuel de MedLingo web — diagnostic

- **3 systèmes CSS en concurrence** : `styles.css` (Clinical Calm, vert-émeraude), `premium.css` (sky-blue), `duolingo.css` (non chargé).
- **Palette conflictuelle** : deux palettes différentes s'empilent et brouillent l'identité.
- **Les composants fonctionnent** mais manquent de cohérence visuelle et de langage médical-spécifique (illustrations, animations de flashcard, etc.).
- **L'animation** est essentiellement le flip 3D existant + confettis + pulse. Riche de potentiel, sous-exploitée.

---

## 3. Les 5 maquettes conçues

### Maquette 1 · ✅ Clinique (teal) — **RECOMMANDÉE**
- **Palette** : teal 900 #0F766E / teal 700 #0D9488 / teal 500 #0891B2 / bleu doux #2563EB / canvas menthe #F0F7F4 / ambre #F59E0B
- **Visuel** : dégradé vert-émeraude puis teal, "canvas menthe" chaleureux, ombre douce, cartes blanches
- **Animations** : flip 3D fluide, micro-interactions, passes d'entrée, statuts de progression
- **Typographie** : Inter 700/800, chiffres tabulaires, hiérarchie claire
- **Rendu** : types de boutons/cartes cohérents, dark mode intégré
- **Pourquoi** : appui fort sur le design system existant (Clinical Calm), ajoute la "palette d'accents" du premium, reste professionnel pour un médical. C'est le meilleur équilibre entre confiance clinique et modernité.

### Maquette 2 · Scandinavian Minimal
- **Palette** : white / warm gray / green #1F2937 / teal / ambre
- **Visuel** : épuré, espaces généreux, ombres légères, coins arrondis, cartes blanches
- **Animations** : fade-in-up en cascade, hover doux
- **Pourquoi** : idéal pour la concentration. Moins médical, plus "edtech pur". Très propre.

### Maquette 3 · Warm Healthcare
- **Palette** : teal #0D9488, ambre #F59E0B, corail #F97316, crème #FFF7ED
- **Visuel** : chaleur, humanoïde, cartes avec effets de survol leger
- **Animations** : survol doux, bounce, micro-interactions chaleureuses
- **Pourquoi** : apprentissage amical, adapté aux étudiants de 1ère année. Un peu moins "sérieux" pour la médecine.

### Maquette 4 · Gamified Gradient (duolingo-style)
- **Palette** : green #58CC02, blue #1CB0F6, yellow #FFC800, purple #CE82FF, red #FF4B4B
- **Visuel** : tuiles colorées, gradient, micro-interactions riches, pulse
- **Animations** : micro-interactions nombreuses, pulse, bounce, confettis
- **Pourquoi** : énergie forte, gamifié, mais "entertainment" plutôt que "apprentissage". Risque de froisser la dimension médicale.

### Maquette 5 · Modern Dark Clinical
- **Palette** : dark navy #0F172A, teal #2DD4BF, cyan #22D3EE, bleu #3B82F6, ambre
- **Visuel** : dark premium, contraste élevé, effets glow, interface nocturne
- **Animations** : glow, transitions, dark-optimized
- **Pourquoi** : interface nocturne, moderne, "tech". Moins chaleureux pour les débutants. Dark mode pur.

---

## 4. Comparaison des cinq (pour le choix)

| Critère | 1. Clinique (teal) | 2. Minimale | 3. Warm | 4. Gamified | 5. Dark |
|---|---|---|---|---|---|
| **Confiance médicale** | ★★★★★ | ★★★★ | ★★★★ | ★★★ | ★★★★ |
| **Modernité visuelle** | ★★★★ | ★★★★★ | ★★★ | ★★★★ | ★★★★★ |
| **Adaptation apprentissage** | ★★★★★ | ★★★★★ | ★★★★ | ★★★ | ★★★ |
| **Aligné avec Clinical Calm** | ★★★★★ | ★★ | ★★★ | ★★ | ★★ |
| **Dark mode intégré** | Oui | Non | Non | Non | Oui |
| **Animations 3D** | Fluide | Léger | Doux | Riches | Noir |
| **Accessibilité** | Meilleure | Bonne | Moyenne | Moyenne | Bonne |

---

## 5. Recommandation finale

### Pour MedLingo DZ : **Maquette 1 — Clinical Teal**

Un design qui :
- **préserve l'identité médicale** (vert émeraude, teal clinique, canvas menthe)
- **unifie le design system** sans conflit de palettes (notre `tokens.css` + `components.css`)
- **ajoute des animations modernes** (flip 3D renforcé, décollets, micro-interactions)
- **supporte le dark mode** (prefers-color-scheme) pour l'étude nocturne
- **reste proche de l'Android** (même tokens visuels partagés)

### Prochaine étape (si vous acceptez)
1. Merger `tokens.css`, `components.css`, `animations.css` dans `src/styles/`
2. Mettre à jour `main.tsx` pour n'importer que le nouveau système
3. Supprimer les définitions redondantes (duolingo.css, premium.css, mobile.css)
4. Mettre à jour les écrans (Modules, Practice, Flash, Quiz) avec les nouveaux composants
5. Vérifier via les tests e2e existants

---

*Les 5 maquettes sont disponibles dans `web-react/docs/` sous forme de fichiers HTML autonomes.
Chaque fichier contient l'ensemble de l'interface (accueil, modules, flashcards, badge, barre de navigation) avec un style cohérent pour cette direction.*
