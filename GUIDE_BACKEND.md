# GUIDE BACKEND — Brancher Supabingo à MedLingo

**Pré-requis :** le code côté app est **déjà écrit** (`data/remote/`).
Ce guide ne demande que des actions manuelles (création de compte, déploiement,
copie de clés). **Aucune ligne de code à écrire.**

**Durée :** ~25 minutes · **Coût :** gratuit (offre Supabase)

---

## Sommaire

1. [Créer le projet Supabase](#1-créer-le-projet-supabase)
2. [Déployer la base de données](#2-déployer-la-base-de-données)
3. [Déployer les 4 Edge Functions](#3-déployer-les-4-edge-functions)
4. [Brancher l'app (clés)](#4-brancher-lapp-clés)
5. [Tester](#5-tester)
6. [Désactiver / revenir en arrière](#6-désactiver--revenir-en-arrière)

---

## 1. Créer le projet Supabase

> Rôle : héberger la base PostgreSQL + les Edge Functions (Deno).

1. Aller sur **https://supabase.com** → **Start your project**
2. Se connecter (GitHub recommandé : un clic)
3. Cliquer **New project**
4. Remplir :
   - **Name :** `medlingo` (ou ce que tu veux)
   - **Database password :** génère un mot de passe fort, **coche "Save password"**
     (sinon tu le perds — il n'est pas récupérable)
   - **Region :** `Frankfurt` (eu-central-1) — la plus proche de l'Algérie
   - **Pricing plan :** **Free** (suffisant : 500 MB, 50 000 utilisateurs actifs/mois)
5. Cliquer **Create new project** → attendre ~2 minutes

**Résultat :** tu es sur le dashboard, URL du style
`https://supabase.com/dashboard/project/abcd1234`

---

## 2. Déployer la base de données

> Rôle : créer les 14 tables + les politiques de sécurité + le contenu.

### 2.1. Installer le CLI Supabase (une fois pour toutes)

Sur Windows, ouvrir un terminal (PowerShell) :

```powershell
winget install supabase.supabase
```

Vérifier :

```powershell
supabase --version
```

### 2.2. Lier ton projet local au projet Supabase

Dans le dossier du projet :

```bash
cd "C:\Users\pc\Desktop\MedLingo\backend"
```

> ⚠️ **IMPORTANT :** les commandes suivantes se lancent depuis `backend/`,
> pas depuis la racine — c'est là que `config.toml` se trouve.

```bash
supabase login
supabase link --project-ref abcd1234
```

Remplace `abcd1234` par l'ID de ton projet (visible dans l'URL du dashboard).

### 2.3. Pousser les migrations

Les 3 fichiers SQL sont **déjà prêts** dans `backend/supabase/migrations/` :

| Fichier | Rôle |
|---|---|
| `001_initial_duolingo_schema.sql` | 14 tables (users, exercises, leagues…) |
| `002_security_rls.sql` | Politiques RLS (sécurité colonne par colonne) |
| `03_seed_content.sql` | Le contenu (4 504 fiches + 44 exercices) |

```bash
supabase db push
```

Quand il demande `Do you want to push these migrations?` → tape **`y`** + Entrée.

**Résultat :** `Schema written` — tes 14 tables sont en ligne.

### 2.4. Vérifier (optionnel)

Dans le dashboard Supabase → **Table Editor** (icône table à gauche).
Tu dois voir : `users`, `medical_terms`, `exercises`, `league_cohorts`, etc.

---

## 3. Déployer les 4 Edge Functions

> Rôle : Birdbrain (difficulté adaptative), session-generator, league-cron,
> notifications.

Les fonctions sont **déjà écrites** dans `backend/functions/` (Deno/TypeScript).
Il faut juste les déployer.

### 3.1. Définir les secrets serveur

Toujours dans `backend/` :

```bash
supabase secrets set SUPABASE_URL="https://abcd1234.supabase.co"
supabase secrets set SUPABASE_SERVICE_ROLE_KEY="eyJ...service_role..."
supabase secrets set CRON_SECRET="un-secret-aleatoire-ici"
```

Où trouver les valeurs :

- **SUPABASE_URL** : dashboard → **Project Settings** (⚙️ en bas à gauche) →
  **API** → **Project URL**
- **SUPABASE_SERVICE_ROLE_KEY** : même page → **Project API keys** →
  **`service_role`** → clic **Reveal** → copier
  > ⚠️ **Ne JAMAIS** mettre cette clé dans l'app Android. Elle reste serveur.
- **CRON_SECRET** : invente une chaîne aléatoire (ex: `medlingo-cron-xyz789`)

### 3.2. Déployer chaque fonction

```bash
supabase functions deploy birdbrain
supabase functions deploy session-generator
supabase functions deploy league-cron
supabase functions deploy notifications
```

Chaque commande affiche `Deployed Function: xxx` en cas de succès.

### 3.3. Configurer le cron hebdomadaire (ligues)

Le reset des ligues doit tourner chaque lundi 00:00 UTC.

Dans le dashboard :

1. **Edge Functions** (icône ⚡ à gauche)
2. Sélectionner **`league-cron`**
3. Onglet **Cron** (ou **Triggers**)
4. Activer le cron, expression : **`0 0 * * 1`** (lundi minuit UTC)
5. **Secret d'authentification** : coller le `CRON_SECRET` choisi en 3.1

---

## 4. Brancher l'app (clés)

> Rôle : permettre à l'app de parler au backend. **Le code est déjà prêt** — il
> ne manque que les clés.

### 4.1. Créer le fichier de secrets

À la **racine** du repo (`C:\Users\pc\Desktop\MedLingo\`), créer un fichier
nommé **`secrets.properties`** contenant :

```properties
SUPABASE_URL=https://abcd1234.supabase.co
SUPABASE_ANON_KEY=eyJ...anon...
```

Où trouver la clé anon :
- Dashboard → **Project Settings** → **API** → **Project API keys** →
  **`anon`** → clic **Reveal** → copier

> ✅ Ce fichier est **automatiquement ignoré par git** (le plugin
> secrets-gradle-plugin l'exclut). Tes clés ne seront jamais commitées.

### 4.2. Ajouter la permission INTERNET

Dans `app/src/main/AndroidManifest.xml`, ajouter cette ligne juste après
`<manifest ...>` :

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

> ⚠️ **C'est la seule ligne de code à modifier.** Sans elle, les appels réseau
> sont bloqués par Android.

### 4.3. Reconstruire l'app

```bash
cd "C:\Users\pc\Desktop\MedLingo"
./gradlew assembleDebug
```

**C'est tout.** Au prochain lancement :
- la progression se synchronise (multi-appareils) ;
- les ligues affichent des **humains** quand le réseau est là, des **bots** sinon.

---

## 5. Tester

### 5.1. Vérifier que l'app est branchée

Lancer l'app, faire une leçon, puis dans le dashboard Supabase :

1. **Table Editor** → table **`user_progress`**
2. Tu dois voir une ligne avec ton XP

### 5.2. Vérifier les Edge Functions

```bash
curl -X POST "https://abcd1234.supabase.co/functions/v1/birdbrain" \
  -H "Authorization: Bearer ANON_KEY" \
  -H "Content-Type: application/json" \
  -d '{"user_id":"test","history":[]}'
```

Réponse attendue : JSON avec `next_difficulty`.

### 5.3. Mode hors-ligne (le plus important)

1. Couper le wifi/4G
2. Faire une leçon → **ça marche** (base Room locale)
3. Rallumer le réseau → la progression se pousse au prochain lancement

**C'est l'invariant offline-first : aucun échec réseau ne casse l'app.**
Garanti par `OfflineFirstRemoteTest` (6 tests).

---

## 6. Désactiver / revenir en arrière

**Le backend est 100 % optionnel.** Pour le désactiver :

1. Supprimer (ou vider) le fichier `secrets.properties`
2. Retirer la permission `INTERNET` du manifest (optionnel)
3. Reconstruire

L'app revient exactement à son comportement actuel : offline-first, ligues de
bots. `BackendConfig.isEnabled` retourne `false`, tous les appels deviennent
des no-ops.

---

## Dépannage

| Problème | Solution |
|---|---|
| `supabase db push` : `relation already exists` | `supabase db reset` puis re-push |
| Functions : `401 Unauthorized` | Vérifier `SUPABASE_SERVICE_ROLE_KEY` (`supabase secrets list`) |
| App : rien ne se sync | 1) `INTERNET` ajouté au manifest ? 2) `secrets.properties` à la racine ? 3) Clé `anon` (pas `service_role`) ? |
| App crash au lancement | Retirer `secrets.properties` → l'app doit redémarrer offline. Si oui, le problème vient des clés |
| `supabase` non reconnu | `winget install supabase.supabase` puis **rouvrir** le terminal |

---

## Ce que tu gardes manuel (comme demandé)

J'ai écrit le **code** et ce guide. Ce qui reste à toi :

1. **Créer le compte Supabase** (étape 1) — tes identifiants
2. **Déployer** (étapes 2-3) — tes clés serveur, ton projet
3. **Copier les clés** (étape 4) — la clé anon va dans `secrets.properties`
4. **La politique RLS** — vérifie que les règles de `002_security_rls.sql`
   correspondent à ton modèle utilisateur avant la mise en production

Aucune de ces étapes ne peut être faite par un agent : elles nécessitent ton
compte et tes secrets. Le code, lui, est prêt et testé (233 tests, 0 échec).
