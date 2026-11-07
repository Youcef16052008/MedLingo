# ADR-3 — Comptes et synchronisation (Mise à jour)

**Statut : PLANNED (downgraded 2026-11-07, decision D2) — Activation immédiate demandée**

## Contexte

ADR-3 initialement recommandait d'attendre le pilote avant activation. 
L'utilisateur a confirmé l'activation immédiate d'ADR-3, modifiant la 
décision initiale du rapport original.

## Architecture

### Frontend (Web)
- **Web React** : `LoginScreen.tsx` + `useAuth()` hook + `useSync()` hook
- Authentification e-mail, inscription, Google OAuth via Supabase
- Stockage local + sync cloud avec last-write wins pour les conflits SM-2

### Backend
- **Supabase Edge Functions** : `backend/functions/session-generator/index.ts` déjà actif
- **Tables Supabase** : `user_progress` (créée via migrations)

## Fonctionnalités implémentées

### Authentification
1. Connexion e-mail (`signIn(email, password)`)
2. Inscription (`signUp(email, password)`)
3. Connexion Google (`signInWithGoogle()`)
4. Déconnexion (`signOut()`)

### Synchronisation
1. `saveProgress(progress)` - Envoi local → cloud
2. `loadProgress()` - Réception cloud → local
3. Logiciel de conflit : last-write wins (timestamp)
4. Export manuel des données utilisateur

## Fichiers modifiés

| Fichier | Action |
|---------|--------|
| `web-react/src/screens/LoginScreen.tsx` | Créé |
| `web-react/src/screens/ProfileScreen.tsx` | Ajouté affichage auth |
| `web-react/src/hooks/useAuth.ts` | Créé |
| `web-react/src/services/supabase.ts` | Créé |
| `web-react/src/types/supabase.ts` | Créé |
| `.env` | Variables Supabase ajoutées |

## Table Supabase à créer

```sql
create table user_progress (
  user_id text primary key,
  xp integer not null default 0,
  level integer not null default 1,
  streak integer not null default 0,
  heart integer not null default 5,
  birdbrain_vector jsonb,
  lessons_completed text[],
  lessons_in_progress text[],
  lessons_next_review jsonb,
  league_position integer,
  updated_at timestamptz not null default now(),
  created_at timestamptz not null default now()
);

-- Index pour les performances
create index idx_user_progress_updated on user_progress (updated_at);
```

## Activation

1. Créer la table `user_progress` dans Supabase
2. Déployer les Edge Functions mises à jour
3. Installer le package `@supabase/supabase-js`
4. Configurer les variables d'environnement
5. Déployer le web-react avec les écrans d'authentification
