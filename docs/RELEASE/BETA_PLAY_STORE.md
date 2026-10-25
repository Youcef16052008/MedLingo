# WS3 — Dossier de release bêta (Play Store, distribution fermée)

**Établi le 25/10/2026** · Exécute WS3 du [`PLAN_MAITRE_2026.md`](../PLAN_MAITRE_2026.md). Statut : **prêt à l'emploi** dès que le keystore de production existe et que la campagne de relecture a publié ≥ 150 histoires (gate K2 du pilote).

---

## 1. Runbook technique (WS3.1)

### 1.1 Keystore de production (à faire UNE fois, humain)

```bash
keytool -genkeypair -v -keystore my-upload-key.jks -alias upload \
  -keyalg RSA -keysize 2048 -validity 10000
```

- Conserver `my-upload-key.jks` HORS du dépôt (gitignoré) + sauvegarde hors-ligne (**perte = app définitivement non mettable à jour** — Play App Signing protège côté Google, mais l'upload key doit survivre).
- Renseigner les variables d'environnement (déjà câblées dans `app/build.gradle.kts:28-32`) :
  `KEYSTORE_PATH`, `STORE_PASSWORD`, `KEY_PASSWORD`.

### 1.2 Build + versioning

```bash
export KEYSTORE_PATH=... STORE_PASSWORD=... KEY_PASSWORD=...
./gradlew :app:bundleRelease        # AAB pour le Play Store
```

- `versionCode` : incrémenter à CHAQUE upload (`app/build.gradle.kts:20`).
- **Reste à faire (dev, bloqué par le WIP non committé sur build.gradle.kts)** : fail-fast si le keystore manque (BUG-029 — aujourd'hui le build produit silencieusement une release non signable), et ADR-2 (retrait des deps réseau mortes → la permission INTERNET disparaîtrait du manifest fusionné).

### 1.3 Parcours de distribution fermée

1. Console Play → app `com.aistudio.medlingua.dzmed` → **Tests fermés (bêta interne)**.
2. Uploader l'AAB signé ; ajouter les testeurs par e-mail (les 5+ étudiants du pilote).
3. Track internal testing = aucun review public, mise à disposition en minutes.

### 1.4 Crash reporting (privacy-first) — décision

Recommandation : **Sentry self-hosted OU Firebase Crashlytics SANS Analytics**, activé uniquement en build release (`if (BuildConfig.DEBUG.not())`). Journaliser : stack traces, breadcrumb technique. **Jamais** : identité, contenu d'apprentissage, localisation. Alternative zéro-tiers : fichier local + export manuel (le protocole pilote collecte déjà les mesures nécessaires). À implémenter après résolution du WIP build.gradle (dépendance).

## 2. Fiche Play Store trilingue (WS3.2)

### Titre (30 car.)
- **FR** : MedLingo — Anglais médical DZ
- **EN** : MedLingo — Medical English DZ
- **AR** : MedLingo — الإنجليزية الطبية

### Description courte (80 car.)
- **FR** : Maîtrisez l'anglais médical : 15 modules, 4504 termes, histoires cliniques.
- **EN** : Master medical English: 15 modules, 4504 terms, clinical stories.
- **AR** : أتقن الإنجليزية الطبية: 15 وحدة، 4504 مصطلح، قصص سريرية.

### Description complète (FR, à traduire EN/AR à la relecture)
> MedLingo est conçu pour les étudiants en médecine d'Algérie : chaque module suit le cursus préclinique (anatomie, physiologie, biochimie, sémiologie…). Termes trilingues anglais ⇄ français ⇄ arabe, répétition espacée scientifique (SM-2), cas cliniques narratifs, ligues et séries pour rester régulier — le tout **hors ligne, sans publicité**.
>
> • 15 modules · 4 504 termes · 13 000 exercices · 814 histoires cliniques
> • Révision espacée SM-2 : chaque carte revient au bon moment
> • Histoires cliniques : apprenez l'anglais DU lit du patient
> • 100 % hors ligne après installation · aucune donnée collectée

### Captures d'écran (à produire) : parcours, quiz, flashcard+audio, histoire clinique, ligues — 5 min. en FR + EN.

## 3. CGU + disclaimer médical (WS3.2 — texte à valider juridiquement avant publication)

**Disclaimer médical (affiché au 1ᵉʳ lancement + fiche store) :**
> « MedLingo est un outil pédagogique de révision linguistique destiné aux étudiants en santé. Son contenu ne constitue ni un avis médical, ni une recommandation de diagnostic ou de traitement. En cas de situation clinique réelle, référez-vous toujours à un professionnel de santé et aux sources de référence de votre faculté. »

**Points CGU essentiels (rédaction complète à faire valider) :**
1. Outil éducatif sans finalité clinique (reprend le disclaimer).
2. Exactitude du contenu : revue médicale en cours — signaler toute erreur via le canal support.
3. Données : traitement 100 % local (appareil) ; aucun compte requis ; aucune donnée transmise à des tiers ; pas de publicité.
4. Propriété intellectuelle : contenus propres ; illustrations tierces sous licence CC BY 4.0 avec attribution (Servier Medical Art — cf. WS1.7).
5. Âge : 16+ recommandé (public étudiant).

## 4. ADR-3 — Comptes & synchronisation (WS3.3)

**Décision recommandée : LOCAL-FIRST, comptes opt-in APRÈS le pilote.**
- Statu quo jusqu'aux résultats du pilote (K1/K3) : aucune synchro, la perte de l'appareil = perte de progression (à afficher honnêtement dans Paramètres + bouton « exporter mes données » en attente).
- Si le pilote confirme l'usage multi-appareils : Supabase Auth (e-mail + Google), sync unidirectionnelle locale→cloud par défaut, conflits SM-2 = last-write par carte. **Dépend d'ADR-2** (réparer ou non le backend).
- Anti-but : jamais de login obligatoire pour apprendre (le créneau = révision rapide offline).

## 5. Checklist de sortie bêta

- [ ] Keystore créé + sauvegardé hors-ligne (§1.1) — **humain**
- [ ] ≥ 150 histoires publiées via le dashboard de relecture (WS1.1) — **humain**
- [ ] Pilote étudiant calé (protocole prêt) — **humain**
- [ ] BUG-029 fail-fast keystore + ADR-2 deps (dev, après résolution WIP build.gradle)
- [ ] Crash reporting décidé (§1.4) — dev
- [ ] Captures d'écran + traductions fiche (§2) — dev/humain
- [ ] CGU validées juridiquement (§3) — **humain**
- [ ] versionCode=2, AAB uploadé, testeurs invités (§1.3)
