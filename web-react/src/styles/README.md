# Feuilles de style — quoi est chargé, quoi est orphelin

## Chargées au démarrage

Un seul point d'entrée : `src/main.tsx` importe `./styles/entry.css`, qui déclare
les cascade layers et importe les trois feuilles utiles.

```css
@layer legacy, design;
@import "./duolingo.css" layer(legacy);
@import "../styles.css" layer(legacy);
@import "./hybrid-design.css" layer(design);
```

| Feuille | Couche | Rôle |
| --- | --- | --- |
| `../styles.css` | `legacy` | Socle historique (1602 lignes). Comble les propriétés que le design system ne déclare pas. |
| `duolingo.css` | `legacy`, **avant** le socle | Composants « gamification » : coffres, ligue, SM-2, trophées, cartes d'histoire. 26 classes du JSX n'existent que là. |
| `hybrid-design.css` | `design` | Design system courant. Gagne à spécificité quelconque. |

**L'ordre est porteur de sens, dans les trois dimensions :**

1. `legacy` avant `design` dans la déclaration `@layer` : sinon le socle, qui
   contient des sélecteurs plus spécifiques (`.path-node.available.next`),
   écraserait le design system.
2. `duolingo.css` avant `styles.css` **dans la même couche** : ses règles
   génériques (`.card { background }`) ont la même spécificité que celles du
   socle et écraseraient le dégradé de `.profile-hero`. Mesuré dans l'autre
   ordre : nom du profil blanc sur blanc (1:1).
3. `hybrid-design.css` termine par une section « Réimport du socle » qui
   rétablit trois règles du socle battues par le reset `button { color: inherit }`
   du design system (`.lang-switch button.active`, `.path-node .node-meta`,
   `.freeze-buy:disabled`) — chacune justifiée par un contraste mesuré.

## Couverture mesurée (15/11/2026)

Extraction statique des `className` du JSX (`web-react/src/**/*.tsx`) :

| | classes |
| --- | --- |
| posées dans le JSX | 361 |
| couvertes par les trois feuilles chargées | **288** |
| sans aucune règle dans les huit feuilles | **73** |

Les 73 restantes sont sur des écrans atteignables (`#/player`, `#/analytics`,
`#/stories`, `#/search`, `#/tree`, sous-parties `profile-*`, `#/login` quand
`SYNC_ENABLED`). Aucune règle = style hérité : elles ne cassent rien, mais elles
ne doivent pas être prises pour un style existant. Lot dédié, écran par écran.

## Non chargées (orphelines)

Versionnées mais importées nulle part (comptage des blocs de règles) :

- `components.css` — 405 lignes, 210 blocs
- `maquette5.css` — 404 lignes, 120 blocs
- `mobile.css` — 223 lignes, 47 blocs
- `premium.css` — 631 lignes, 193 blocs
- `tokens.css` — 549 lignes, 200 blocs

**Mesure** : aucune de ces cinq feuilles ne définit de classe exclusive — tout ce
qu'elles stylent est déjà couvert par les trois feuilles chargées. Elles ne sont
pas supprimées ici : leur contenu n'a jamais été arbitré (certaines règles sont
plus complètes que leurs équivalentes de `hybrid-design.css`). Toute
réintégration doit être mesurée (couverture de classes, captures avant/après,
contraste) avant d'être adoptée.

## Historique du défaut

Le commit `9a627dc` (7/11, « wip: snapshot ADR-3… ») a remplacé dans
`src/main.tsx` `import "./styles.css"` par `import "./styles/hybrid-design.css"`
sans reporter les classes que seule la feuille historique définissait : boutons
(`.btn`, `.btn.ghost`, `.btn.danger`), puces d'état (`.stat-chip*`), profil,
analytique, modules, tutoriel. Les composants concernés retombaient sur le style
par défaut du navigateur (fond `#F0F0F0`, rayon 0), d'où un contraste mesuré à
1,03:1 en thème sombre. Le correctif P2.4 avait restauré les boutons et les puces
dans `hybrid-design.css` ; `entry.css` rétablit l'ensemble du socle.
