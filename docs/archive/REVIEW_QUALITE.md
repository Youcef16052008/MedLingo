# Review qualité du code — MedLingo

**Date :** 10 octobre 2026 · **Portée :** app Android + `web-react/` + `backend/`

> **Mise à jour 13 octobre 2026.** Depuis cette revue, le plan de remédiation a été exécuté à
> 100 % (`RAPPORT_FINAL_DEFINITIF.md` §8), la base de données a fait l'objet d'une 2ᵉ passe
> d'audit corrigeant 6 défauts (`RAPPORT_DATABASE_V2.md`), et la parité visuelle web ↔ Android
> a été réalisée (`RAPPORT_PARITE_WEB_ANDROID.md`). Aucun défaut majeur nouveau n'a été
> introduit : 267 tests Android + 127 tests web verts, `check_design.py` 35/35.

---

## Verdict : **code de qualité, sans défaut majeur**

Le review n'a trouvé **aucun défaut critique**. Les pratiques sont solides, les
commentaires documentent *pourquoi* (pas *quoi*), et chaque bug corrigé porte
son test de régression.

**227 tests Android (0 échec) · 111 tests web (0 échec) · build réussi.**

---

## ✅ Ce qui est bien fait

### 1. Aucun TODO, FIXME, HACK

```
grep -rn "TODO|FIXME|HACK" app/src/main/java/ web-react/src/ backend/
→ 0 résultat
```

Aucun dette non suivie, aucun code "en attendant". Rare.

### 2. Les erreurs sont gérées, pas avalées

10 `catch (e: Exception)`, chacune avec un **fallback explicite et commenté** :

```kotlin
val migratedExercises = try {
    ExerciseSpecMigrator.migrateAll(InitialData.exercises)
} catch (e: Exception) {
    InitialData.exercises   // repli : exercices non migrés, l'app marche
}
```

Pas de `catch` vide, pas d'erreur silencieuse.

### 3. L'horloge est injectable partout

`Clock` est un paramètre, `SystemClock` est l'implémentation par défaut. Tous
les tests temporels sont déterministes (pas de `System.currentTimeMillis()`
caché). Travail d'excellent niveau.

### 4. Le ledger des gemmes est anti-triche

Transactions **signées** (montant négatif pour une dépense), `balanceAfter`
calculé, horodatage obligatoire. Une fonction de construction unique
(`GemsManager`) garantit que l'appelant ne peut pas mentir sur le montant.

### 5. Permissions Android minimales

```xml
VIBRATE, POST_NOTIFICATIONS, RECEIVE_BOOT_COMPLETED
```

Pas de `INTERNET` (offline-first assumé), pas de permissions suspectes. Les
`@SuppressLint("MissingPermission")` de `NotificationHelper` sont **justifiés** :
la permission est vérifiée par `hasNotificationPermission()` juste avant.

### 6. Tests = documentation

Chaque classe de test explique le bug qu'elle corrige, avec les numéros du
rapport (P0-1, P0-2…). Les tests de génération tournent sur les **vraies**
données du seed (4 504 fiches), pas des stubs.

### 7. Cohérence des deux plateformes

Android et `web-react` partagent les **mêmes invariants** : 15 modules, 6
niveaux, `LESSON_SIZE = 8`, même générateur de leçons, même schéma de termes.
Aucune divergence de contenu détectée.

---

## ⚠️ Observations mineures (non bloquantes)

### 1. La logique métier est dupliquée entre Kotlin et TypeScript

`lessons.ts` (288 lignes) est un port manuel de `LessonGenerator.kt`. Les
invariants sont identiques aujourd'hui, mais **toute évolution doit être faite
deux fois**. À terme, envisager de générer la logique web depuis le Kotlin.

### 2. `etymology` (92 % vide) et `clinicalPearl` (90 %)

Gérés correctement (`isNotBlank()`), donc sans impact fonctionnel — mais ces
champs sont lus par 5+ écrans pour n'afficher quelque chose que sur 10 % des
fiches. Soit les remplir, soit les marquer "bientôt" dans l'UI.

### 3. 519 fiches `termFr == termEn`

Traduction française absente pour 11,5 % des termes — l'exercice FR affiche
donc le mot anglais. Revue éditoriale recommandée (voir `tools_check_tr.py`).

### 4. `web-react` : Playwright non installé

Les 132 captures d'écran existantes ne sont pas rejouables (`npm i -D playwright`
nécessaire). Les 111 tests Vitest, eux, sont verts.

---

## Ce qui n'a PAS été fait (et pourquoi)

Ce review est **statique** : lecture du code, `grep`, construction et tests.
Je n'ai pas :

- lancé l'app sur un appareil réel (pas d'émulateur configuré) ;
- testé les notifications réelles (nécessite un appareil) ;
- vérifié les performances en production (pas de profiling) ;
- exécuté les Edge Functions (nécessite un runtime Deno + Supabase).

Les tests unitaires couvrent la logique, pas l'expérience utilisateur.
