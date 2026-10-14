# PARTIE 14/20 — Backend Supabase (schéma, RLS, 4 Edge Functions, seed SQL 2,3 Mo)

**Fichiers lus intégralement** : `001_initial_duolingo_schema.sql` (423 l.), `002_security_rls.sql` (157 l.), `config.toml` (99 l.), `session-generator/index.ts` (316 l.), `birdbrain/index.ts` (309 l.), `league-cron/index.ts` (367 l.), `notifications/index.ts` (339 l.). En-tête du `03_seed_content.sql` (2,3 Mo).

## Constat fondamental : **le backend n'est connecté à rien**

Recherche effectuée dans tout `app/src` :
- `grep -rln supabase app/src` → **AUCUNE référence**
- `grep -rln 'OkHttp|Retrofit|Ktor|HttpURLConnection|java.net.URL|Firebase' app/src/main/java` → **AUCUNE référence**

L'app Android est **100 % offline** : elle ne peut pas appeler le backend (pas de client HTTP, pas de `INTERNET` permission, pas de Supabase SDK). Le backend est un **projet parallèle non intégré**. Les deux sont même en **désaccord** :

| Point | App Android (offline) | Backend Supabase |
|---|---|---|
| Cœurs | **Supprimés** (migration v10) | Présents, régénération paresseuse + `get_current_hearts()` + `OUT_OF_HEARTS` |
| Gems | balance mutable dans `user_stats` | `gems_ledger` append-only + `balance_after` |
| Ligues | bots à XP aléatoire statique | `league_members` + cron promotion/demotion |
| Exercices | 44 en dur (legacy) | `exercises` JSONB + `spec` Zod + 44 via seed |
| Difficulté | niveau 1..6 statique | **Birdbrain** (vecteur 40-dim, HLR) |
| Utilisateur | `userId = 1` mono-compte | `users` UUID + auth + RLS |
| Amis | — | `friendships` |
| Achats | `purchaseSuper()` gratuit | `purchases` (BaridiMob/CCP/RevenueCat) |

Le backend **décrit l'architecture que l'application n'est pas**. `ARCHITECTURE_FINALE_100_DUOLINGO.md` affirme donc une intégration qui n'existe pas.

## Qualité du code backend (en soi) — elle est **bonne**
- **`002_security_rls.sql` est le meilleur fichier du backend** : il corrige méthodiquement les défauts du 001 — `users.id default auth.uid()` (sinon RLS bloque tout), policy INSERT + trigger `handle_new_user`, **retrait sélectif des droits UPDATE** (`revoke update on users from anon, authenticated` + grant limité aux colonnes de profil : un client ne peut pas s'attribuer des gems ou `is_super`), vues sur `public_profiles` (pas de fuite email/phone), `security_invoker` sur `user_stats_computed`, suppression de la fonction trigger **morte** (`check_league_week_ended`), FK **délibérément supprimées** (sentinelles Birdbrain, bots de ligue), FTS `simple` pour stemmer AR/FR (le `english` du 001 ne STEMait pas l'arabe).
- **Edge functions** : JWT vérifié à chaque appel, `user.id !== user_id` rejeté (403), rate limiting par utilisateur (50/100/20 req/min), validation stricte des payloads (`current_vector` doit faire **exactement** 40 dims), CORS allowlist, `x-cron-secret` pour `league-cron`, `mulberry32` pour un vecteur **déterministe**.
- **Commentaires d'ingénierie** : « sans écriture, `hearts` et `hearts_updated_at` resteraient figés et la régén remonterait à chaque appel depuis un horodatage obsolète » — un vrai bug compris et corrigé.
- **`birdbrain`** : Half-Life Regression lite + vecteur 40-dim + ability Elo. C'est une vraie implémentation d'un "**Birdbrain** pour pauvres", honnêtement assumée comme telle (« PyTorch P3 GPU in production, here TypeScript lite »).

## Défauts backend
1. **`rateLimitMap` en mémoire** — sans état partagé entre instances, sans TTL explicite : sous scale-out, la limite est multipliée par le nombre d'isolate. Duolingo utiliserait Redis. Acceptable pour un MVP.
2. **`import_map.json` de 3 octets** (vide) référencé pour `session-generator` et `birdbrain` — inutile ou cassé.
3. **`session-generator` référence encore les cœurs** (`OUT_OF_HEARTS`) alors que l'app les a supprimés → **incohérence de produit** entre les deux.
4. **`03_seed_content.sql` fait 2,3 Mo** et contient les **mêmes 4 512 termes** que le Kotlin + le JSON web. **Triple source de vérité** (Kotlin / JSON web / SQL), aucune synchronisation automatisée documentée. Si un terme est corrigé dans un fichier, les deux autres divergent silencieusement. Les commentaires disent "mirror of the Kotlin/web seed" mais aucun test ne le garantit.
5. **`001` seed les `chapters_count` hardcodés** (11, 14, 32…) et des `estimated_size_mb` à nouveau en littéraux (14.2, 18.5…) — **exactement le mensonge que le test `OfflineContentSizeTest` Android interdit**. Le backend n'a pas de test équivalent.
6. **Seed `lessons`** : 3 lignes seulement sur les 90 promises en commentaire (`anat-l1-vocab`, `physio-l1-vocab`, `biochim-l1-vocab`). Le reste est absent.
7. **`allow all (disable RLS for local dev)`** est commenté — OK, mais le fichier s'arrête sur les politiques de la phase 1 sans couvrir `units`/`lessons`/`exercises` (corrigé en 002).
8. Aucun **test backend** (pas de tests Deno, pas de `supabase test`).

## Actions concrètes
1. **Décider la relation app ↔ backend.** Trois options : (a) intégrer (supprimer le offline pur, ajouter Supabase SDK + `INTERNET`), (b) garder le backend comme futures API et le marquer **non branché** dans la doc, (c) supprimer `backend/` jusqu'à décision. Tel quel, c'est 2,3 Mo de SQL et 1 300 lignes de TS qui donnent une **fausse impression de complétude**.
2. **Réaligner le backend sur l'app** : retirer les cœurs du schéma et de `session-generator` (ou réintroduire les cœurs dans l'app — décision produit).
3. **Générer `03_seed_content.sql` depuis le Kotlin** via `tools_gen_seed_sql.py` (existe !) en CI, avec un test qui asserte que le SQL == le seed Kotlin. Supprime la triple source de vérité.
4. Étendre le générateur pour produire aussi les **90 lessons** et les 44+ exercices en SQL.
5. Dériver `chapters_count`/`estimated_size_mb` des données réelles dans le seed SQL (comme `OfflineContentSize.kt` le fait côté Android).
6. Supprimer les `import_map.json` vides.
7. Ajouter des tests Deno pour les edge functions (validation, rate limit, sécurité JWT) — `deno test`.
8. Documenter le rate limiting en mémoire comme limitation mono-instance.
