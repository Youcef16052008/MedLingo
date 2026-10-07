# MedLingo DZ — Version Web (PWA)

Version web légère de l'application Android MedLingo : flashcards à répétition espacée **SM-2**,
quiz progressifs (6 niveaux), lexique de **2 945 termes médicaux** et statistiques — 100 % hors-ligne.

## Lancer

```bash
# depuis la racine du projet
python -m http.server 8080 --directory web
# puis ouvrir http://localhost:8080
```

Aucune dépendance : HTML/CSS/JS vanilla, données embarquées en JSON.

## Contenu

| Fichier | Rôle |
|---|---|
| `index.html` / `styles.css` / `app.js` | Application (SPA, 4 onglets : Accueil, Cours, Quiz, Profil) |
| `data/terms.json` | 2 945 termes extraits des seeds Kotlin (`app/src/main/.../data/initial`) |
| `data/exercises.json` | 44 exercices (mcq, fill_blank, sentence_order, matching) |
| `sw.js` / `manifest.webmanifest` | PWA hors-ligne (network-first pour le code, cache-first pour les données) |

## Outils (développement)

```bash
node tools/extract.mjs      # regénère data/*.json depuis les sources Kotlin
node tools/check-i18n.mjs   # vérifie les 3 dictionnaires FR/EN/AR (100 clés)
```

Le seed `tools/_seed.html` préremplit localStorage (intro vue, stats de test) pour les captures de test.

## Fonctionnalités

- Répétition espacée SM-2 identique à l'app Android (`SpacedRepetitionAlgorithm.kt`)
- Cœurs ❤️ (régénération 1/30 min), gemmes 💎, série 🔥, XP ⭐ — persistés en localStorage
- Prononciation TTS (Web Speech API), interface **FR / EN / AR** avec RTL
- Thème clair/sombre automatique (`prefers-color-scheme`)
