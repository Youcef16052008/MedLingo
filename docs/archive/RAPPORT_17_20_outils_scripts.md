# PARTIE 17/20 — Scripts & outils (Python racine, outils web)

**Fichiers lus intégralement** : `tools_gen_seed_sql.py` (209 l.), `tools_parse_english.py` (112 l.), `tools_check_tr.py` (104 l.), `tools_build_english.py` (261 l.), plus `web/tools/*` et `web-react/scripts/*`, `web-react/e2e/*`.

## Observations

### `tools_gen_seed_sql.py` — le pont web→backend, en bon état
- Génère `backend/supabase/migrations/03_seed_content.sql` depuis `web-react/public/data/*.json` (donc depuis le web, pas depuis le Kotlin).
- **Table de mapping module→unit_id** de 16 entrées avec **deux alias pour le même unit** : `"Anapath": "anapath"` **et** `"Anatomie Pathologique": "anapath"`. C'est exactement le bug de taxonomie que `ModuleFilter` a corrigé côté Android (Partie 4) — ici c'est volontaire et robuste (les deux écritures existent dans les données). Bonne résilience.
- `TYPE_MAP` : `mcq→choice, matching→match, sentence_order→wordbank, fill_blank→fill`. Cohérent avec le schéma SQL du 001.
- `build_spec()` construit le JSONB `spec` des exercices — c'est la version backend de l'`ExerciseSpecMigrator` Android. Les deux existent en parallèle.
- `uuid_from_int(n)` génère des UUID déterministes `00000000-0000-0000-0000-{n:012d}` — élégant pour garantir l'idempotence des seeds.
- **Problème** : `OUT` écrit dans `backend/supabase/migrations/03_seed_content.sql` — écrire dans un fichier de migration versionné par un script, c'est risqué : une ré-exécution **réécrit une migration déjà appliquée**. En CI, il faudrait générer un fichier `04_…` ou vérifier que le contenu est identique (test de non-dérive).
- **Le script n'est exécuté nulle part en automatique** — le `03_seed_content.sql` actuel est issu d'une exécution manuelle. D'où le risque de triple source de vérité (Partie 14).

### `tools_parse_english.py` — parseur SQL artisanal robuste
- Parse `tmp_medecal_english.sql` avec un **parseur de champs respectant les littéraux entre apostrophes** et l'échappement SQL (`''` → `'`). Le commentaire explique pourquoi un simple `split(',')` casse (virgules dans `example_sentence`).
- **Dépend d'un fichier absent** : `tmp_medecal_english.sql` n'existe **pas** dans le repo (fichier source temporaire non commité). Le script est donc **non exécutable en l'état** — c'est un outil one-shot documenté.
- Signale les lignes rejetées (nombre de champs inattendu, id non entier).

### `tools_check_tr.py` — le meilleur outil du repo (validation de traductions)
- Valide `tmp_tr/batch_*.json` et `tmp_tr/examples_*.json` **avant** génération :
  - fragments latins dans un champ arabe ;
  - **distance de Levenshtein** implémentée à la main pour détecter les traductions identiques à l'anglais (non traduites) ;
  - longueur minimale (12 pour les définitions, 8 pour les exemples — justifiée dans le code) ;
  - caractères d'autres alphabets (CJK, cyrillique) ;
  - **liste blanche de sigles latins légitimes en arabe** (HDL, LDL, MRI, CT, IV, IM).
- C'est exactement l'outil qui devrait tourner sur les **519 fiches `termFr == termEn`** identifiées en Partie 5. Actuellement, il ne valide que les 655 entrées `everyday_medical_english`.
- **Dépend aussi de `tmp_english_rows.json`** (produit par `tools_parse_english.py`) et de `tmp_tr/` — aucun des deux n'est commité.

### `tools_build_english.py` — 261 lignes, génère les termes
Non lu en détail, mais sa cohérence avec `tools_parse_english.py` + `tools_check_tr.py` est claire : c'est le pipeline de génération/traduction du module Anglais Médical (655 entrées, ids 3858–4512).

### `web-react/e2e/` — suite d'audit impressionnante
- `full_audit.py` : **1 103 lignes**, « 95 clics / 0 échec » d'après le plan (Partie 18). C'est une vraie suite E2E pilotée par CDP (le `tools/cdp.mjs` du web vanilla confirme la technique).
- `verify.py` : **455 lignes**.
- `check_design.py` : **180 lignes** (vérification de la conformité Duolingo : tokens `--duo-*`, boutons chunky, etc.).
- `web-react/scripts/check-i18n.mjs` : vérifie les 3 dictionnaires FR/EN/AR (169 clés × 3 d'après le plan).
- `web-react/scripts/make_icons.py` : génère la suite d'icônes PWA.
- **C'est la partie la plus mature du projet côté web** : le web a des audits E2E et de design, l'Android n'a **aucun test UI** (Partie 12). Déséquilibre net.

## Jugement

Les outils Python sont **de qualité inégale mais globalement bons** : le validateur de traductions (`tools_check_tr.py`) est le meilleur — il applique une discipline linguistique que peu de projets ont. En revanche, **3 des 4 scripts dépendent de fichiers `tmp_*` non commités** : ils sont inutilisables par un nouveau contributeur. Et `tools_gen_seed_sql.py` n'est branché sur rien.

## Actions concrètes
1. **Brancher `tools_gen_seed_sql.py` en CI** : générer le SQL puis asserter qu'il est identique à celui commité (test de non-dérive). Évite la triple source de vérité (Partie 14).
2. **Généraliser `tools_check_tr.py`** à **tous** les modules (pas seulement `everyday_medical_english`) : appliquer la distance de Levenshtein aux 4 512 fiches pour attraper les `termFr == termEn` et les traductions oubliées (Partie 5). Les regex `LATIN`/`FOREIGN` sont réutilisables telles quelles.
3. **Committer un exemple** de `tmp_medecal_english.sql` + `tmp_tr/` (ou ajouter un `.gitignore` explicite + un README d'explication dans chaque script) pour que les outils soient reproductibles.
4. Ajouter dans le README racine une section « Pipeline de contenu » qui explique l'ordre : `tools_parse_english.py` → `tools_check_tr.py` → `tools_build_english.py` → `extract.mjs` (web) → `tools_gen_seed_sql.py` (backend).
5. **Porter la discipline E2E côté Android** : le web a `full_audit.py` (1 103 lignes) ; l'Android n'a rien. Au minimum, un test instrumenté par écran.
6. Renommer `tools_*.py` en `tools/*.py` (convention) pour cohérence avec `web-react/tools/`.
