# MedLingo — Rapport d'analyse concurrentielle & plan stratégique

**« Mieux que Duolingo » — pour de vrai, et pour notre créneau.**

Version 1.0 · 25 octobre 2026 · Établi à partir de : l'analyse APK Duolingo fournie (doc. du 24/10/2026), l'audit complet du dépôt au 25/10/2026, et les documents internes (`RAPPORT_01..20`, `PLAN_AMELIORATIONS_REVIEW.md` et son journal d'exécution, `PLAN_STORIES_CORPUS.md`, `PROTOCOLE_PILOTE_ETUDIANTS.md`, `RELECTURE_HISTOIRES.md`).

---

## 0. Résumé exécutif

**MedLingo ne doit pas « battre Duolingo » en général — il doit le battre sur le créneau des étudiants en médecine, et c'est atteignable.** Les forces de Duolingo (contenu générique grand public, localisation de masse, pubs, marque) sont précisément ses angles morts médicaux. Les nôtres (cursus verrouillé, trilinguisme FR/AR/EN, SRS réel, histoires cliniques, offline) sont des doublures qu'il ne copiera jamais.

**État au 25/10/2026 :** plateforme saine et éprouvée — 288 tests unitaires Android verts, 261 tests web verts, parité web/Android outillée (`check:stories`), i18n vérifié, migrations Room non destructives exportées. Corpus massif : **4 504 termes, ~13 000 exercices, 814/936 histoires cliniques (87 %), 268/312 chapitres (86 %), 15 modules du cursus**.

**Mais la valeur est bloquée par trois verrous humains :** la relecture des 814 brouillons d'histoires, les 44 chapitres trop minces, et le pilote étudiant jamais exécuté. Tant que ces trois jalons ne sont pas passés, ajouter des fonctionnalités ne sert à rien : personne n'utilise encore le produit en conditions réelles.

**Le plan :** cinq phases — V0 *Finir & publier* (débloquer la valeur), V1 *Parité engagement* (égaler les boucles Duolingo), V2 *Supériorité pédagogique* (dépasser Duolingo là où il est générique), V3 *Doublures défensives* (contenu et langue qu'il ne copiera pas), V4 *Échelle & écosystème*. Avec KPIs chiffrés, non-goals assumés, et calendrier réaliste pour un développeur solo.

---

# Partie I — Rapport

## 1. Sources et méthode

1. **Document fourni** « MedLingo — Duolingo APK Architecture Analysis » (v1.0 + v2.0 fusionnées, 24/10/2026) — analyse d'un APK Duolingo décompressé + deux plans de développement (Android puis web).
2. **Audit du dépôt** `C:\Users\pc\Desktop\MedLingo` au 25/10/2026 : lecture de `app/build.gradle.kts`, structure complète du code Android (`com.aistudio.medlingua.dzmed`), web (`web-react/`), backend (`backend/`), thème, et du journal d'exécution de `docs/PLAN_AMELIORATIONS_REVIEW.md`.
3. **Mémoire projet** : phases 0–3 du plan d'améliorations toutes livrées (commits `85998cd` → `745820e`), corpus clinique généré par vagues 1–15.

---

## 2. Lecture critique du document « Duolingo APK Analysis »

### 2.1 Qualité éditoriale

Le document est une **fusion brute d'itérations successives** : sections 1–3 répétées trois fois intégralement, deux sections « §6 », numérotations cassées (« 1. 1. »), deux statuts contradictoires (v1.0 et v2.0, tous deux « Ready for Development », tous deux datés du 24/10). **À consolider avant toute diffusion.**

### 2.2 Affirmations à considérer comme non vérifiées

| Affirmation du document | Statut | Commentaire |
|---|---|---|
| Duolingo demande `READ_CONTACTS` | ⚠️ Douteux | Serait un scandale privacy ; probablement une lecture erronée du manifeste |
| Duolingo utilise Firebase Auth | ⚠️ Douteux | Duolingo a son propre système d'authentification |
| « Unity : interactive lesson engine, game physics » | ⚠️ Partiel | Unity est plausible pour du contenu interactif ; « game physics » est une interpolation |
| 18 111 fichiers, 15 377 XML, 850+ layouts, 30+ locales, SDKs pub (IronSource, UnityAds, Pangle), Zendesk, R8 | ✅ Plausible | Cohérent avec un décodage réel d'APK v6.x ; à garder comme ordre de grandeur |

**Règle : on retient les ordres de grandeur, pas les détails non vérifiables.**

### 2.3 Recommandations du document obsolètes par rapport au dépôt réel

| Le document recommande | Le dépôt fait réellement (et assume) | Verdict |
|---|---|---|
| Hilt pour l'injection | DI manuelle (`di/AppModule.kt`), simple et testable | Garder la DI manuelle — migration Hilt = coût sans bénéfice à cette taille |
| Stack Firebase complète (Auth, Firestore, FCM) + Zendesk | Backend **Supabase** (`backend/supabase/` + functions) ; Firebase limité à AI + AppCheck | Garder Supabase ; ajouter push/analytics en V1 uniquement |
| Web en Next.js 14 sur Vercel | **React 19 + Vite** (`web-react/`), Vitest, zéro dépendance UI externe | Garder Vite — plus léger, déjà testé, offline-friendly |
| « Commencer avec 3 spécialités (Cardio, Respiratoire, Neuro) » | **15 modules du cursus préclinique** (Anatomie → Terminologie), alignés sur le programme | Le document décrit un autre produit ; ignorer |
| Room minimal (4 tables) | Room riche (revue SRS, histoires, progression), schémas exportés et committés | Le dépôt est en avance |
| Palette « Primary #FACA00 (jaune Duolingo) » | **Palette verte médicale réelle** (`ui/theme/Color.kt` : `0xFF059669`, `0xFF0F766E`, etc.) — le jaune Duolingo n'est **pas** utilisé | Excellent : identité déjà divergée, à préserver (§7.1) |

**Conclusion : les sections 5–7 du document (« plans de développement ») décrivent un projet générique qui n'est pas MedLingo, avec une stack que le projet n'a pas choisie. Elles sont à archiver. La section 4 (analyse APK) est le seul actif durable.**

---

## 3. Ce que l'APK Duolingo nous apprend stratégiquement

1. **La doublure de Duolingo n'est pas technologique.** Compose, Room, Retrofit : nous avons déjà la même classe d'outils. Leur avantage = *ops de contenu* (30+ locales servies), *boucles d'engagement* éprouvées (streaks, ligues, objectifs quotidiens), *culture A/B testing* massive, et la marque.
2. **Leur monétisation (médiation pub IronSource/UnityAds/Pangle) est inapplicable et indésirable pour nous.** Publicité + étudiants en médecine révisant = dissonance produit. Notre modèle doit rester sans pubs (§13).
3. **Leur moteur de leçons interactives (Unity) est leur luxe.** Nous n'en avons pas besoin : notre profondeur est pédagogique (SRS, diagnostic, histoires cliniques), pas motion-design.
4. **Leur angle mort structurel : la spécialisation.** Un contenu conçu pour 500 millions d'utilisateurs génériques ne peut pas être aligné sur un cursus de médecine, trilingue, avec terminologie contrôlée. C'est notre espace entier.

---

## 4. Audit MedLingo — état au 25/10/2026

### 4.1 Produit et plateformes

- **Android** (`app/`, Kotlin + Compose, minSdk 24 / target 36) — 11 écrans : `ModulesScreen`, `PathScreen`, `ChapterScreen`, `QuizScreen`, `FlashcardsScreen`, `PracticeScreen`, `ReviewHubScreen`, `StorySessionScreen`, `PlacementDiagnosticScreen`, `PlayerScreen`, `StatsProfileScreen`. TTS (`TtsManager`), localisation, release minifiée (R8 + shrinkResources).
- **Web** (`web-react/`, React 19 + Vite + Vitest + Testing Library) — 11 écrans dont **`LeaguesScreen` et `SearchScreen` absents d'Android**, RTL arabe testé, PWA (manifest + icônes).
- **Backend** (`backend/`) — Supabase (migrations) + 4 functions : `birdbrain`, `league-cron`, `notifications`, `session-generator`.

### 4.2 Moteurs pédagogiques (déjà construits)

| Moteur | Localisation | Rôle |
|---|---|---|
| **SM-2** (répétition espacée réelle) | `domain/sm2/` + `web-react/src/domain/sm2.ts` | Planification des révisions — *transparence scientifique que Duolingo n'a pas* |
| **Diagnostic de placement** | `domain/diagnostic/`, écran dédié | Positionner l'étudiant à l'entrée |
| **BirdBrain** (adaptatif) | `domain/birdbrain/` + function backend | Adaptation du contenu |
| **Générateur de séances & leçons** | `domain/lesson/`, `domain/learning/`, `session-generator` | Construit les sessions d'exercices |
| **Histoires cliniques (mode Dalilk)** | `domain/story/`, session 1:1 web/Android | 814 histoires, expressions partagées inter-histoires (une seule carte SM-2), relecture obligatoire avant publication (`published` ≠ `draft`) |

### 4.3 Gamification

XP, séries (streaks), coffres (`ChestManager` + `chests.ts`), trophées (`trophies.ts`), **ligues** (`leagues.ts` + cron backend — web uniquement pour l'instant), progression (`ProgressionManager`), profil/stats. Le socle Duolingo-équivalent est là.

### 4.4 Contenu

- **15 modules** du cursus préclinique (`data/terms/` : Anatomie, Anatomie Pathologique, Anglais Médical, Biochimie, Biophysique, Cytologie, Embryologie, Génétique, Histologie, Informatique Médicale, Microbiologie, Pharmacologie, Physiologie, Sémiologie, Terminologie).
- **4 504 termes / ~13 000 exercices** (vérifiés par `check:seed` ; seed SQL ≈ 12,4 Mo).
- **814/936 histoires cliniques** (87 %) sur **268/312 chapitres** (86 %) — générées par vagues 1–15 (commit `745820e`), avec 268 fiches de relecture par chapitre.

### 4.5 Qualité et outillage (différenciateur sous-estimé)

`vitest` 261/261 web · `:app:testDebugUnitTest` 288/288 Android · `tsc` + build OK · `check:i18n` (0 clé morte/manquante) · `check:seed` (parité termes/exercices) · `check:stories` (parité web/Android des histoires, démontré échec sur divergence) · migrations Room **non destructives** avec schémas exportés (`app/schemas/`). **Ce niveau d'hygiène n'existe pas dans les projets étudiants concurrents — c'est un avantage compétitif en soi.**

### 4.6 Identité visuelle

Palette verte médicale (émeraude/teal, `Color.kt`), **aucune reprise du jaune Duolingo**. L'identité a déjà divergé de façon saine — à préserver et affirmer (§7.1, V3.4).

### 4.7 Dettes et angles morts (honnêteté obligatoire)

| # | Constat | Impact |
|---|---|---|
| D1 | **814 histoires en `draft`, jamais relues** (gate humaine `docs/RELECTURE_HISTOIRES.md`) | La fonctionnalité phare n'est pas publiée |
| D2 | **44 chapitres trop minces** + 122 histoires manquantes (936 cible) | Couverture cursus incomplète |
| D3 | **Pilote étudiant jamais exécuté** (protocole prêt : `PROTOCOLE_PILOTE_ETUDIANTS.md`) | Zéro preuve d'efficacité réelle |
| D4 | **Jamais publiée** : `versionCode 1`, signature via env vars, pas de fiche store | Aucun utilisateur réel |
| D5 | Pas de comptes/sync cross-device (local-first : force offline, limite multi-appareil) | Choix à assumer + option opt-in en V1 |
| D6 | Pas de push, d'analytics produit, ni de crash reporting | Impossible d'itérer sur données réelles |
| D7 | Ligues et recherche **non portées sur Android** | Parité web/Android incomplète côté engagement |
| D8 | TTS dépendant des voix système (bannière honnête si 0 voix EN — RISK-002 résolu) | Qualité audio variable selon appareil |
| D9 | Mono-développeur (bus factor) + verrous humains non industrialisés | Le rythme des jalons humains dépend d'une personne |
| D10 | Génération/entretien du corpus adossée à Firebase AI | Coût à surveiller avant tout scale |

---

## 5. Duel direct : MedLingo vs Duolingo sur NOTRE créneau

| Dimension | Duolingo | MedLingo aujourd'hui | Qui gagne sur le créneau ? |
|---|---|---|---|
| Alignement cursus médecine | ❌ Générique | ✅ 15 modules précliniques, chapitres par module | **MedLingo** |
| Terminologie médicale contrôlée | ❌ | ✅ 4 504 termes FR/EN (+AR) vérifiés par script | **MedLingo** |
| Répétition espacée | Opaque, non scientifique exposée | ✅ SM-2 réel, prévisible, auditable | **MedLingo** |
| Histoires cliniques immersives | ❌ (histoires génériques) | ✅ 814 (après relecture) avec expressions recyclées par SM-2 | **MedLingo** |
| Trilingue FR/AR/EN médical | ❌ | ✅ i18n + RTL testés (arabe standard) | **MedLingo** |
| Diagnostic de placement | Partiel (« test de niveau » générique) | ✅ Placement par module | **MedLingo** |
| Offline | Partiel (leçons téléchargées) | ✅ Local-first intégral (Room + assets) | **MedLingo** (contexte connectivité ALG) |
| Publicité | ✅ Présente (5 SDKs de pub) | ✅ Zéro | **MedLingo** |
| Boucles d'engagement (streaks, ligues, quêtes) | ✅ Référence mondiale | 🟡 Socle construit, ligues web seulement, pas de push | **Duolingo** → V1 |
| Localisation de masse (30+ langues UI) | ✅ | 🟡 3 langues métier suffisent pour le créneau | Nul enjeu |
| Marque & confiance grand public | ✅ Écrasant | ❌ Inconnue | **Duolingo** — on ne joue pas dessus |
| Motion design / luxe interactif (Unity) | ✅ | ❌ | **Duolingo** — coût non justifié pour nous |

**Lecture : MedLingo gagne déjà sur 8 dimensions cœur ; il perd sur l'engagement opérationnalisé et la marque. Le plan attaque exactement ces deux fronts — sans jamais abandonner les 8.**

---

## 6. Définition de la victoire + KPIs

**« Mieux que Duolingo » = pour un étudiant en médecine du marché cible, choisir MedLingo est rationnel sur preuve.** Quatre critères mesurables :

| # | Critère de victoire | KPI | Cible à 12 mois |
|---|---|---|---|
| K1 | **Maîtrise démontrée** (pas du temps passé) | Score de rétention SM-2 à J+7 sur audio/expression jamais revu (mesure du pilote) | ≥ 70 % des expressions testées retenues |
| K2 | **Couverture cursus** | Chapitres publiés (relus) / total | ≥ 95 % (296/312) |
| K3 | **Rétention produit** | D30 retention pilote → beta | ≥ 25 % (bon pour une app de révision disciplinaire) |
| K4 | **Fiabilité** | Crash-free sessions ; fonctionnalité hors-ligne complète | ≥ 99,5 % ; 100 % des parcours jouables offline |

Chaque phase du plan est rattachée à ces KPIs ; une fonctionnalité qui n'en déplace aucun n'entre pas dans le plan.

---

## 7. Risques et mitigations

| # | Risque | Gravité | Mitigation |
|---|---|---|---|
| R1 | **Juridique — trade dress / marque** : ressemblance excessive à Duolingo (habillage, onboarding, verbatims) | Haute | Palette verte conservée (vérifié : aucun `#FACA00` dans le code) ; ne jamais copier mascotte/nom d'écrans/verbatims ; nom « Ligue », « Série » OK (termes génériques) ; avis juridique avant store |
| R2 | **Exactitude médicale** — erreur de contenu enseignée à des futurs médecins | Haute | Gate `review: pending → published` déjà dans le moteur (ne JAMAIS la contourner) ; disclaimer pédagogique (« outil de révision, pas source de référence clinique ») ; relecteurs identifiés par module |
| R3 | **Goulot de relecture humaine** (814 drafts × 1 personne) | Haute | Industrialiser par lots (§V0.1) : 20/semaine = 40 semaines → mutualiser avec 2–3 relecteurs externes (faculté) ; prioriser les modules à fort trafic attendu |
| R4 | **Publication précipitée sans preuve** | Moyenne | Aucune campagne avant pilote (V0.3) — publier sans mesure = gaspiller le premier jet d'adoption |
| R5 | **Coûts Firebase AI** à l'échelle | Moyenne | Budget plafond mensuel + fallback local (le produit reste 100 % fonctionnel sans IA — règle du guide, à maintenir) |
| R6 | **Bus factor** (1 dev) | Haute | Les 20 RAPPORT_*.md + ce document = mémoire ; scripts de vérification = filet ; V0.4 inclut la procédure de build/release documentée |
| R7 | **Scope creep** (« mieux que Duolingo » lu comme « toutes ses features ») | Haute | §13 Non-goals ; règle du plan : chaque tâche cite le KPI qu'elle déplace |

---

# Partie II — Plan

## 8. Stratégie : « Ne pas copier — verrouiller le créneau »

1. **Parité des mécaniques, pas de l'habillage.** Les boucles d'engagement Duolingo (objectif quotidien, séries résilientes, ligues, notifications) fonctionnent : on les égale (V1), avec notre identité verte médicale.
2. **Supériorité pédagogique là où Duolingo est générique.** SRS transparent et prévisible, diagnostic, alignement examen, preuve de maîtrise (V2). C'est notre cœur durable.
3. **Doublures défensives qu'il ne copiera pas.** Trilinguisme médical FR/AR/EN, cursus algérien chapitre par chapitre, corpus d'histoires relues par des médecins (V3). Une entreprise de 500 M d'utilisateurs ne fera jamais un corpus de 312 chapitres d'embryologie algérienne trilingue.
4. **Discipline non négociable :** offline-first, zéro pub, l'IA reste optionnelle (le produit fonctionne sans), chaque feature déplace un KPI.

---

## 9. Phases

### V0 — « Finir & publier » (semaines 1–4) · débloque K2, K3, K4

> Objectif unique : transformer l'existant (déjà construit, déjà testé) en produit utilisé par de vrais étudiants. **Rien d'autre n'entre dans cette phase.**

| # | Tâche | Détail / fichiers | Critère d'acceptation |
|---|---|---|---|
| V0.1 | **Industrialiser la relecture des 814 drafts** | Dashboard de relecture (filtre `draft`, par module, actions par lot) sur le corpus existant + `docs/RELECTURE_HISTOIRES.md` comme protocole ; recruter 2–3 relecteurs (faculté) | ≥ 150 histoires `published` à S4 ; chaque promo de lot passe `check:stories` |
| V0.2 | **Combler les 44 chapitres minces** | Génération assistée (mêmes gates : builder + relecture) ; prioriser Sémiologie/Terminologie (forte demande examen) | 0 chapitre « mince » restant sous seuil ; `check:seed` vert |
| V0.3 | **Exécuter le pilote étudiant** | `docs/PROTOCOLE_PILOTE_ETUDIANTS.md` tel quel : ≥ 5 étudiants, compréhension audio neuf à J+7, réutilisation, aides | Rapport de mesures écrit ; baseline K1/K3 établie |
| V0.4 | **Release interne Play Store** | Keystore prod (env vars déjà prévues dans `build.gradle.kts`), `versionCode` incrémental, crash reporting privacy-first (ex. Sentry self-hosted ou Firebase Crashlytics sans analytics), fiche store FR/AR/EN, CGU + disclaimer médical (R2) | APK signé installable ; crash reporting actif ; fiche relue |
| V0.5 | **Décision D5 documentée** | Court ADR : comptes/sync — quoi, quand, pourquoi (local-first par défaut, opt-in cloud) | ADR committed dans `docs/` |

**Livrable V0 : beta fermée jouable par des étudiants réels, avec mesures.**

### V1 — « Parité engagement » (semaines 5–9) · débloque K3

> Égaler les boucles Duolingo avec notre identité. Rien de pédagogiquement nouveau ici — c'est de la rétention.

| # | Tâche | Détail | Critère |
|---|---|---|---|
| V1.1 | **Porter les ligues sur Android** | `LeaguesScreen` équivalent Compose + états `domain/gamification` ; réutiliser `league-cron` | Parité d'états web/Android testée ; ligues visibles Android |
| V1.2 | **Notifications push + rappel quotidien** | FCM (Android) + Web Push ; function `notifications` existante à câbler ; réglage horaire local, opt-in explicite, jamais > 1/jour | Rappel configurable ; désactivation en 1 geste ; test local |
| V1.3 | **Objectif quotidien + série résiliente** | Objectif XP/jour paramétrable (léger/standard/sérieux) ; jour off / gel de série hebdo (mécanique honnête, sans paywall) | Tests unitaires sur la logique série ; écrans profil mis à jour |
| V1.4 | **Recherche Android** | Port de `SearchScreen` (termes + histoires) | Parité web |
| V1.5 | **Comptes opt-in + sync** (si ADR V0.5 = oui) | Supabase Auth (email + Google) ; sync unidirectionnelle locale→cloud par défaut, conflits = last-write sur clés SM-2 | Migration réversible ; appareil neuf restauré < 1 min ; local reste pleinement fonctionnel sans compte |
| V1.6 | **Instrumentation produit minimale** | Événements locaux (activation, séance, révision, churn) + export anonyme opt-in ; zéro tracker tiers | Dashboard simple (même Supabase) ; funnels D1/D7/D30 calculables |

**Livrable V1 : un étudiant a 3 raisons quotidiennes de revenir (objectif, série, ligue) et on sait mesurer s'il revient.**

### V2 — « Supériorité pédagogique » (semaines 10–16) · débloque K1, K2

> Ici on fait ce que Duolingo structurellement ne peut pas : de la maîtrise ciblée, alignée cursus et examen.

| # | Tâche | Détail | Critère |
|---|---|---|---|
| V2.1 | **Moteur de maîtrise unifié** | Fusion diagnostic + SM-2 + BirdBrain → « état de maîtrise » par terme/chapitre exposé à l'étudiant (transparent : prochaine révision, force) | Écran « mes faiblesses » ; l'étudiant peut prédire sa révision |
| V2.2 | **Parcours adaptatif** | PathBuilder pondéré par l'état de maîtrise (pas par ordre du livre) ; l'étudiant voit POURQUOI | Test A/B interne : parcours adaptatif vs linéaire (mesure K1 sur pilote) |
| V2.3 | **Mode examen (EMD)** | Banques chronométrées par module, format proche des épreuves, correction avec renvoi au chapitre | 1 module pilote (Sémiologie) ; scores exportables |
| V2.4 | **Écoute médicale** | Dictées cliniques (accents EN variés), audio compréhension liée aux histoires existantes | 50 dictées livrées ; mesure de perception dans le pilote |
| V2.5 | **Prononciation (option)** | Reconnaissance vocale on-device si dispo (permission explicite, défaut off — règle phase 4 du plan existant) | Démo validée sur 20 termes ; échec silencieux impossible |
| V2.6 | **Preuve de maîtrise exportable** | Certificat de chapitre/module (partageable) — motivation extrinsèque honnête | Génération offline du certificat |

### V3 — « Doublures défensives » (semaines 17–24, parallélisable avec du contenu continu)

| # | Tâche | Détail |
|---|---|---|
| V3.1 | **Trilingue intégral** | AR standard + FR + EN sur toute la surface (UI déjà i18n + RTL testé) ; terminologie : terme FR ↔ EN ↔ AR systématique dans les flashcards |
| V3.2 | **Fin du corpus** | 122 histoires restantes → 936/936 ; maintenance par vagues ; chaque vague passe la même gate relecture |
| V3.3 | **Partenariats contenu** | 2–3 relecteurs médicaux identifiés par module (faculté) ; à terme: modules cliniques (2e cycle) |
| V3.4 | **Affirmer la marque** | Identité verte médicale assumée : mascotte propre (symbole médical algérien ?), motion légère, signature sonore — zéro emprunt Duolingo (R1) |

### V4 — « Échelle & écosystème » (semaine 25+)

| # | Tâche | Condition d'entrée |
|---|---|---|
| V4.1 | Tableau de bord enseignant/promo (devoirs, cohortes via Supabase) | Demande réelle d'au moins 1 enseignant au pilote |
| V4.2 | iOS (KMP ou port) | K3 ≥ cible sur Android d'abord |
| V4.3 | Freemium (contenu avancé payant, jamais de pub, jamais de paywall sur la série) | ≥ 500 actifs mensuels ; décision business explicite |
| V4.4 | Extension à d'autres cursus / universités | Preuve K1/K3 consolidée sur 2 semestres |

---

## 10. Calendrier réaliste (1 développeur + relecteurs externes pour le contenu)

| Horizon | Jalons |
|---|---|
| **J+30** | V0.4 release interne · V0.3 pilote lancé · ≥ 150 histoires relues |
| **J+60** | V0 terminé · V1.1–V1.3 livrés (ligues Android, push, objectif quotidien) · ≥ 400 histoires relues |
| **J+90** | V1 complet (sync opt-in, instrumentation) · première courbe D30 réelle |
| **J+180** | V2.1–V2.3 (moteur maîtrise, adaptatif, mode examen 1 module) · corpus ≥ 95 % |
| **J+365** | V2 complet · V3 engagé · décision V4 sur données |

**Règle de séquencement : aucune tâche V(n+1) ne démarre si le critère d'acceptation V(n) n'est pas vert.** (Même discipline que le journal de `PLAN_AMELIORATIONS_REVIEW.md`.)

---

## 11. KPIs de pilotage (rappel des cibles)

- **K1 maîtrise** : ≥ 70 % de rétention à J+7 (pilote) — mesuré dès V0.3, amélioré par V2.1/V2.2.
- **K2 couverture** : ≥ 95 % des chapitres publiés — V0.1/V0.2/V3.2.
- **K3 rétention** : D30 ≥ 25 % en beta — V1 intégralement.
- **K4 fiabilité** : ≥ 99,5 % crash-free, 100 % offline — V0.4 puis continu.
- Contrôle mensuel : tout écart > 20 % sur une cible déclenche une révision de phase, pas un ajout de feature.

---

## 12. Prochaines actions immédiates (cette semaine)

1. **V0.1** : construire le dashboard de relecture par lots + lister les candidats relecteurs (faculté). *(débloque tout le reste)*
2. **V0.4** : créer le keystore de production + documenter la procédure de build/release (R6).
3. **V0.3** : confirmer les 5+ étudiants du pilote et caler les dates.
4. Archiver les sections 5–7 du document « APK Analysis » d'origine (remplacées par le présent rapport) et en dédupliquer la section 4.

---

## 13. Non-goals assumés (le garde-fou anti-Duolingo-envie)

- ❌ **Aucune publicité**, jamais (ni mediation, ni échange de données).
- ❌ **Pas de langues génériques** hors créneau (pas d'espagnol pour voyageurs…).
- ❌ **Pas de clone visuel** Duolingo : pas de jaune `#FACA00`, pas de mascotte hibou-like, pas de verbatims traduits de Duolingo (R1).
- ❌ **Pas de gamification toxique** : pas de cœurs perdus punitifs bloquants, pas de streak-anxiety sans gel honnête, pas de dark patterns de notification.
- ❌ **Pas d'IA obligatoire** : le produit reste 100 % fonctionnel hors ligne sans aucun service cloud (règle héritée du guide, maintenue).
- ❌ **Pas de fonctionnalité sans KPI** (K1–K4).

---

## 14. Conclusion

MedLingo n'a pas un problème de technologie ni de contenu — il a un problème de **dernier kilomètre** : relecture, pilote, publication. Les trois verrous humains de V0 sont les seules choses qui séparent le dépôt actuel d'un produit réel. Une fois V0 passé, le plan ne « copie pas Duolingo » : il prend ses boucles d'engagement (V1), puis dépasse son plafond pédagogique (V2) sur un créneau qu'il ne défendra jamais (V3).

**Duolingo enseigne les langues à tout le monde. MedLingo fera maîtriser l'anglais médical aux futurs médecins — avec preuves.**
