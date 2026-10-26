# PLAN MAÎTRE MedLingo — 2026

**Le document unique de pilotage.** Tout ce qui suit supersede et consolide les ~40 rapports/plans du dépôt (10–25 octobre 2026). Il est construit sur : (1) la digestion croisée de tous les documents historiques, (2) la vérification de leurs claims contre l'état réel du dépôt au 25/10/2026, (3) la revue profonde à 36 findings ([`REVIEW_PROFONDE_20_ANALYSES.md`](REVIEW_PROFONDE_20_ANALYSES.md)), (4) la stratégie « dépasser Duolingo » ([`RAPPORT_COMPETITIF_ET_PLAN_2026.md`](RAPPORT_COMPETITIF_ET_PLAN_2026.md)).

**Points d'entrée :** ce plan (pilotage) · [`TODO.md`](TODO.md) (exécution immédiate) · [`REVIEW_PROFONDE_20_ANALYSES.md`](REVIEW_PROFONDE_20_ANALYSES.md) (qualité, bugs détaillés) · [`RELECTURE_HISTOIRES.md`](RELECTURE_HISTOIRES.md) (campagne humaine).

---

## 1. État réel consolidé (vérité unique, vérifiée le 25/10/2026)

| Dimension | État vérifié |
|---|---|
| Plateformes | Android Kotlin/Compose (Room **v14**, 11+ écrans) · Web React 19/Vite (11 écrans) · Backend Supabase (4 functions, migrations **cassées sur seed frais**) |
| Tests | Android 288/288 · Web 5 086/5 086 · MAIS CI **jamais commitée** (`.github/` untracked) |
| Contenu | 4 504 termes trilingues (exEn 100 %, pearl 10 %) · exercices : 112 committés / **13 000 en WIP non committables** (bloqués par BUG-003) · 814/936 histoires (dashboard de relecture livré) |
| Sécurité dépôt | **Exemplaire** (zéro secret sur tout l'historique git, RLS complète, aucune usurpation possible) |
| Qualité produit | **5 bugs critiques confirmés** — verdict : *not safe to release* tant que WS0 n'est pas fait |
| Sous-système révision/chapitres (Lot 2) | Construit sur les DEUX plateformes, **routé sur aucune** |
| Backend | Non branché en réalité : client Android → tables inexistantes (no-op silencieux), économie serveur décorative |

## 2. Ce que la digestion des 40 documents a révélé (méta-conclusions)

1. **Le projet avance plus vite que sa documentation.** Chaque semaine a produit sa génération de rapports qui supplantent la précédente sans l'archiver : 4 générations identifiées (10–13 oct « remédiation » · 14–19 oct « 20 sections/LOT2 » · 24–25 oct « stories/compétitif » · 25 oct « revue profonde »).
2. **Sept contradictions document/réalité trouvées** (cf. §2.1) — dont deux qui ont masqué des bugs réels. D'où la gouvernance du §3.
3. **Trois plans historiques contiennent du travail encore ouvert et non absorbé** : RAPPORT_REMEDIATION (pearls 4 054 manquants, 519 fiches fr==en, 3,9 Mo de ressources mortes, illustrations Servier), LOT2 (routage du sous-système révision — la moitié exécutée), plan_10000_exercises (commit du corpus 13k + explications). Tous sont absorbés ci-dessous dans les workstreams.
4. **Les décisions validées par l'exécution** (à ne pas re-débattre) : offline-first mono-utilisateur d'abord · DI manuelle · Supabase (pas Firebase) comme cible cloud · gamification duolingo-like MAIS sans pubs/sans cœurs punitifs · ledger de gemmes signé anti-triche (GemsManager) · horloge injectée partout · stories = cas cliniques avec gate de relecture obligatoire.

### 2.1 Contradictions document/réalité (leçons de gouvernance)

| Document dit | Réalité vérifiée | Leçon |
|---|---|---|
| `PLAN_MEDLINGO` : « CI/CD ✅ créés » (19/10) | `.github/` **jamais commité** — la CI n'a jamais tourné | Un ✅ sans commit = un vœu. Règle : un item n'est « fait » que committé + vérifié par un test ou un check CI. |
| `PLAN_MEDLINGO` : `buildPath()` sans args = « correctif majeur validé » | C'est le **BUG-002** : parcours gelé à vie | Même décision, deux lectures : la refonte data-driven était inachevée (« déblocage pas encore implanté », dit le doc lui-même §4) |
| `REVIEW_QUALITE` : « aucun défaut critique » (13/10) | 5 critiques le 25/10 | La revue louait des *patterns* ; les critiques sont dans l'*assemblage* (écrans × données × persistance) — né après le 13/10 (story mode, ligues, corpus 13k) |
| `LOT2` : « ChapterScreen.kt ❌ n'existe pas » (18/10) | Existe (ainsi que ReviewHubScreen.kt)… mais **non routé** sur les 2 plateformes | Une fonctionnalité « construite » mais non routée est invisible : leLot 2 est à moitié fini |
| `GUIDE_BACKEND` : « le code côté app est déjà écrit » | Écrit mais **non fonctionnel** (tables inexistantes, clé anon ≠ JWT) | « Le code existe » ≠ « le chemin marche ». Tester le chemin, pas la présence du fichier |
| `ARCHITECTURE_FINALE` : « backend non branché » (13/10) | Toujours vrai le 25/10 | Le seul doc resté exact du premier coup |
| `RAPPORT_REMEDIATION` : exEn 1 106/4 504 à remplir | Aujourd'hui 4 504/4 504 ✅ (mais 8 592 *exercices* sans explications ≠ fiches termes) | Deux champs homonymes (terme.exEn vs exercice.exp) — toujours distinguer |

## 3. Gouvernance documentaire (appliquer en WS0)

**Principe : un seul document canonique par question.** Un rapport de revue est un *constat daté* ; un plan est *vivant* ; les deux ne se mélangent plus.

**Canoniques (8, à jour, sources de vérité) :**
`docs/PLAN_MAITRE_2026.md` (pilotage) · `docs/TODO.md` (exécution) · `docs/REVIEW_PROFONDE_20_ANALYSES.md` (constat qualité 25/10) · `docs/RAPPORT_COMPETITIF_ET_PLAN_2026.md` (vision/stratégie) · `docs/RELECTURE_HISTOIRES.md` + `docs/PROTOCOLE_PILOTE_ETUDIANTS.md` (protocoles humains) · `docs/PLAN_STORIES_CORPUS.md` (référence corpus histoires) · `docs/GUIDE_INTEGRATION_APPRENTISSAGE_MEDLINGO.md` (spec technique story-mode §6).

**À archiver** (déplacés en WS0.8 via `git mv` vers `docs/archive/` — l'histoire reste dans git) :
- Racine : `RAPPORT_01..20_*.md` (20 fichiers), `RAPPORT_REMEDIATION`, `REVIEW_QUALITE`, `RAPPORT_FINAL`, `RAPPORT_FINAL_DEFINITIF`, `RAPPORT_DATABASE_V2`, `RAPPORT_PARITE_WEB_ANDROID`, `ARCHITECTURE_FINALE_100_DUOLINGO`, `COMPARAISON_DUOLINGO`, `ANALYSE_MEDLINGO_20_SECTIONS` + `ANALYSIE_…` (variantes), `PLAN_MEDLINGO`, `LOT2_ARCHITECTURE_AND_PLAN` ⚠️ **lien à garder dans WS2** (c'est le design de référence du sous-système à finir), `GUIDE_BACKEND` ⚠️ prémisses invalidées — à réécrire en WS2 si le backend est activé.
- `docs/` : `PLAN_AMELIORATIONS_REVIEW.md` (clos — phases 0–3 livrées, journal conservé), `ANALYSE_DALILK_MEDLINGO.md` (exécutée), `plan_10000_exercises.md` (absorbé WS1), `IMAGE_STRATEGY.md` (absorbé WS1), `tri_termFr_termEn.md` (absorbé WS1).

**Règles futures :** (R1) toute revue future s'appelle `docs/REVIEW_<date>_<perimetre>.md` et ne déplace pas les lignes du plan maître, elle propose ; (R2) un item n'est « fait » que : code committé + check vert en CI + ligne mise à jour dans TODO.md ; (R3) les racines du dépôt n'accueillent plus de `.md` — tout va dans `docs/`.

## 4. Le plan — 7 workstreams consolidés

> Chaque tâche porte : **[D]**=dev/agent · **[H]**=humain · critère d'acceptation · origine (quelle source absorbe-t-elle).

### WS0 — STABILISER (J1–J3) — *rien d'autre ne compte avant*

| # | Tâche | Type | Critère | Origine |
|---|---|---|---|---|
| 0.1 | **BUG-001** store roundtrip (`coerceArray` + tests persist→revive→update) | D | Test roundtrip vert ; mise à jour après reload fonctionne | Revue profonde |
| 0.2 | **BUG-003** renderer `gap` + `"gap"` dans `ExerciseType` + **test de contrat types↔renderers sur le corpus servi** | D | 1ʳᵉ leçon d'un nouvel utilisateur jouable sur le corpus 13k | Revue + plan_10000 |
| 0.3 | **BUG-002** `buildPath(s.lessonBest, s.moduleBest)` — refermer la refonte inachevée du 19/10 (logique de déblocage vérifiée bout-en-bout) | D | Leçon terminée → niveau 2 déverrouillé, e2e vert | Revue + PLAN_MEDLINGO |
| 0.4 | **BUG-004** TTS : retirer `stop()` du finally + override `onStop` | D | Lecture continue sans timeout 6 s (Robolectric) | Revue |
| 0.5 | **BUG-005** collecteur ligues : lancement unique + persistance sur delta + `distinctUntilChanged` | D | 0 écriture DB au repos (test comptage) | Revue |
| 0.6 | **BUG-006** builder : valider sidecar & bâtir en mémoire AVANT écriture ; écriture atomique des 2 copies | D | Sidecar orphelin → corpus inchangé | Revue |
| 0.7 | **BUG-007** `git add .github/` + `check:stories` dans web.yml + retirer `lintDebug \|\| true` | D | CI verte sur GitHub, parité vérifiée à chaque PR | Revue + contradiction §2.1 |
| 0.8 | **Gouvernance §3** : archivage des ~28 docs + `.gitattributes` + `.gitignore` étoffé (`tmp/`, `tools_*`, `probe.txt`, `node_modules/`, `.hermes/`) | D | `git mv` commité ; racine sans .md hors README | Méta-analyse |
| 0.9 | P1 adjacents rapides : BUG-013 (race story), BUG-014 (durcissement review_server), BUG-016 (sidecar malformé) | D | Tests associés verts | Revue |

**Gate WS0 → WS1..3 :** tous les critères verts + `vitest` + `:app:testDebugUnitTest` + `check:stories` + build web/Android verts **dans la CI commitée**.

### WS1 — CONTENU (S1→S6, en continu) — *le produit EST le contenu*

| # | Tâche | Type | Critère | Origine |
|---|---|---|---|---|
| 1.1 | **Campagne de relecture** des 814 drafts via `npm run review` (150/semaine solo, ×2 avec relecteurs) | H | ≥ 95 % publiées à S6 | V0.1 + RELECTURE |
| 1.2 | Recruter 2–3 relecteurs (1 langue + 1–2 médecins) | H | 2 relecteurs actifs | Stratégie |
| 1.3 | **Commiter le corpus 13k** (débloqué par 0.2) + générer les **explications trilingues manquantes** (8 592) ou masquer proprement le bloc | D | Contrat types↔renderers vert sur le corpus committé ; feedback non vide partout | plan_10000 + BUG-027 |
| 1.4 | 44 chapitres minces : enrichir les termes (1–2 → ≥3) puis régénérer | D+H | 268→312 chapitres (936 histoires) | PLAN_STORIES_CORPUS |
| 1.5 | **Perles cliniques** : 4 054 fiches sans `pearl` (génération assistée + relecture H) | D+H | ≥ 80 % des fiches pearl | REMEDIATION #10 |
| 1.6 | **519 fiches termFr == termEn** + 8 doublons intra-module (Dizziness/Vertigo…) : revue de dédoublonnage | H | Liste validée, corpus régénéré | REMEDIATION P3 |
| 1.7 | Illustrations médicales (Servier CC BY 4.0 — 30 schémas ciblés) + crédit licence | D | 30 illustrations intégrées, licence visible | IMAGE_STRATEGY |
| 1.8 | Pilote étudiant (≥ 5, protocole existant) **dès 150+ histoires publiées** | H | Rapport de mesures K1/K3 | PROTOCOLE |

### WS2 — INTÉGRATION & PARITÉ (S2–S4, après WS0)

| # | Tâche | Type | Critère | Origine |
|---|---|---|---|---|
| 2.1 | **ADR-1 : finir ou retirer le sous-système révision/chapitres.** Recommandation : **finir** (design de référence : [`docs/archive/LOT2_ARCHITECTURE_AND_PLAN.md`](archive/LOT2_ARCHITECTURE_AND_PLAN.md)) — router `ChapterScreen`/`ReviewHubScreen` web + Android, brancher `recordAnswer`/cœurs/file SM-2, retirer les 1,8 Mo morts du chemin critique (lazy-load) | D | `#/review` et chapitres utilisables sur les 2 plateformes ; e2e offline complet | LOT2 + BUG-018/012 |
| 2.2 | BUG-008 (atomicité user_stats), BUG-022 (main thread), BUG-023 (thread-safety TTS), BUG-024/025 (ProgressionManager mort, XpOutcome) | D | Tests concurrence verts | Revue |
| 2.3 | **BUG-009 SM-2 « Hard »** : seuil quality<2 + **scénarios de parité web/Android partagés** (comme les histoires) | D | rate(2) = rappel réussi sur les 2 plateformes, mêmes sorties | Revue |
| 2.4 | BUG-019 (DST + gel = 1 jour) sur les 2 plateformes | D | Tests horloge DST verts | Revue |
| 2.5 | **ADR-2 : backend — réparer ou désactiver.** Réparer = migr. 03 régénérée + client aligné + auth JWT (si ligues réelles V1) ; sinon flag off du chemin distant (retirer deps mortes → permission INTERNET disparaît, BUG-028). Guide historique : [`docs/archive/GUIDE_BACKEND.md`](archive/GUIDE_BACKEND.md) (prémisses invalidées — à réécrire si activé) | D | ADR committé ; si répare : `supabase db reset` vert en CI + synchro non-no-op | Revue + GUIDE_BACKEND |
| 2.6 | i18n : chaînes durcies → `t()` (BUG-021) + checker élargi ; BUG-020 (trophée relégation) | D | check:i18n voit les littéraux ; 0 chaîne FR en UI ar/en | Revue |
| 2.7 | P2 restants : BUG-015 (harnais pilote-001 + asserts exacts), BUG-032 (export préservant les cases), BUG-033 (minimums des checkers) | D | Harnais strict vert | Revue |

### WS3 — RELEASE V0 (S3–S5, parallèle à WS1)

| # | Tâche | Type | Critère | Origine |
|---|---|---|---|---|
| 3.1 | Keystore prod + fail-fast si absent (BUG-029) + versionCode incrémental | D | Release signée reproductible en CI | Stratégie V0.4 |
| 3.2 | Crash reporting privacy-first + fiche store FR/AR/EN + **CGU + disclaimer médical** (R2) | D+H | Bêta fermée Play Store installable | Stratégie |
| 3.3 | ADR-3 comptes/sync (local-first, opt-in) — dépend d'ADR-2 | D | ADR committé | Stratégie D5 |
| 3.4 | Allègement : 3,9 Mo de ressources mortes APK (WebP), CSS mortes | D | APK < seuil défini ; grep classes mortes vide | REMEDIATION P2 |

### WS4 — ENGAGEMENT V1 (S5–S9) — *« addictive » par le progrès, pas par la contrainte*

Les 8 leviers (détail Partie III de la revue profonde), par rendement, tous déjà semi-présents dans le code :
1. Maîtrise visible (route le hub — livré par WS2.1) + calendrier de série « ECG » (identité verte, zéro emprunt Duolingo).
2. Objectif quotidien onboarding + gel honnête 1 jour (WS2.4) + `freezeUsed/streakGained` réels (WS2.2).
3. Coffres à récompenses variables cadencés sur l'objectif (ChestManager existe) — jamais achetables.
4. **Ligues réelles** : endpoint signé d'incrément `weekly_xp` + cron existant → classement humain réel (dépend ADR-2 « réparer ») ; sinon libellé honnête « ligue d'entraînement ».
5. Histoires sérialisées « to be continued » par chapitre (corpus 814 = l'actif cliffhanger).
6. Push ≤ 1/jour à l'heure d'étude observée (functions `notifications` + POST_NOTIFICATIONS déjà en place).
7. Certificats de maîtrise par chapitre (partageables, générés offline).
8. Widget de série Android.
**Gate V1 :** mesurer sur le pilote (K3 D30 ≥ 25 %) avant d'industrialiser.

### WS5 — PÉDAGOGIE V2 (S10–S16)

Moteur de maîtrise unifié (SM-2 + diagnostic + BirdBrain → état par terme prévisible pour l'étudiant) · parcours adaptatif pondéré par la maîtrise · **mode examen EMD** (banques chronométrées par module, 1 module pilote : Sémiologie) · dictées/écoute médicale (50 audio) · prononciation on-device optionnelle. *(Absorbe les « phases 3+ » du GUIDE_INTEGRATION et le §V2 stratégique.)*

### WS6 — DOUBLURES DÉFENSIVES V3 (S17–S24)

Trilingue intégral surface + flashcards FR↔EN↔AR systématiques · corpus 936/936 · partenariats relecteurs/facultés · marque propre (mascotte médicale, motion légère) · 2ᵉ cycle (clinique). *(Absorbe §V3 stratégique.)*

## 5. Chronogramme consolidé

| Horizon | Jalons |
|---|---|
| **J+3** | WS0 complet (gate qualité) + archivage gouvernance |
| **J+30** | CI distante verte · corpus 13k committé · ≥ 150 histoires publiées · release interne signée |
| **J+60** | WS2 intégré (hub routé, SM-2 corrigé, ADR-1/2 tranchés) · ≥ 400 histoires · pilote étudiant lancé |
| **J+90** | WS4 leviers 1–3 actifs · première courbe D30 réelle · ligues réelles ou libellé honnête |
| **J+180** | WS5 : moteur maîtrise + mode examen 1 module · corpus ≥ 95 % · trilingue renforcé |
| **J+365** | V3 engagé · décision V4 (enseignant/iOS/freemium — SuperPaywallDialog existe déjà comme UI dormante) sur données |

## 6. KPIs & gates (inchangés, rappel)

K1 maîtrise ≥ 70 % à J+7 (pilote) · K2 couverture ≥ 95 % chapitres publiés · K3 D30 ≥ 25 % · K4 crash-free ≥ 99,5 % + 100 % offline. **Gate de passage inter-workstreams : la CI distante (WS0.7) devient l'arbitre — un WS n'est « fini » que si sa colonne de checks y est verte.**

## 7. Décisions ouvertes (ADRs à trancher)

| ADR | Question | Recommandation | Échéance |
|---|---|---|---|
| ADR-1 | Finir ou retirer le sous-système révision/chapitres (LOT2) | **Finir** (design prêt, SM-2 = pilier V2) | WS2 |
| ADR-2 | Backend : réparer (seed + JWT + ligues réelles) ou désactiver (flag off, deps retirées) | Réparer si ligues réelles visées à V1 ; sinon désactiver proprement maintenant | WS2 |
| ADR-3 | Comptes/sync opt-in | Oui après pilote (Supabase Auth), local-first par défaut | WS3 |
| ADR-4 | Cœurs : le design en a-t-il besoin ? (économie serveur décorative, non-goal « pas de punitif ») | **Non** — retirer du design, garder l'anti-goal | WS2 |
| ADR-5 | Freemium (SuperPaywall UI dormante) | Décision business à ≥ 500 actifs mensuels | V4 |

## 8. Risques transverses

R1 juridique (trade dress : garder la palette verte, zéro emprunt) · R2 exactitude médicale (gate relecture inviolable + disclaimer) · R3 goulot humain de relecture (mitigé par le dashboard + recrutement WS1.2) · R4 coûts Firebase AI (plafond + fallback local) · R5 bus factor (la gouvernance §3 rend l'état lisible en une page) · R6 « 5086 tests verts » utilisé comme preuve de santé (les 5 critiques prouvent le contraire : l'assemblage n'était pas testé — WS0.2/2.7 closent cet angle).

## 9. Journal de suivi

| Date | Workstream | Commit | Vérifications | Notes |
|---|---|---|---|---|
| 25/10 | Création | `1a9a4c5` | — | Fusionne 40 documents ; WS0 détaillé dans TODO.md |
| 25/10 | **WS4 complet (8/8 leviers)** | `32c8dbe`/`6d39b3d` | vitest 5107 · gradlew 0 | Coffres cadencés (3 j), cliffhangers, certificats, widget série, rappels locaux (push cloud REJETÉ — ADR-2), bots « simulateur » |
| 25/10 | **WS5 livré** | `b3b9195`/`580d3a3` | vitest 5114 ×3 runs | Moteur de maîtrise pur, Mes faiblesses, examen EMD (chrono + renvoi chapitres) ; flake reviewFlow blindé |
| 25/10 | **Push + PR → main** | _(ce commit)_ | CI GitHub | 49+ commits fusionnés via PR ; dev WS0→WS5 terminé, portes humaines restantes (relecture, keystore, CGU, pilote) |
| 25/10 | **WS0 exécuté** | _(ce commit)_ | vitest **5094/5094** · gradlew unitaires **exit 0** · check:stories OK · tsc + build OK · garde orphelins démontrée (corpus intact) · review_server 400/415/200 | BUG-001/002/003/004/005/006/007/013/014 corrigés avec tests de régression ; CI committée (check:stories + lint strict) ; 38 documents archivés dans `docs/archive/` ; `.gitattributes`/`.gitignore` |

---

*Établi le 25/10/2026. Ce plan remplace toutes les feuilles de route antérieures ; les documents historiques restent consultables dans `docs/archive/` après WS0.8. Prochaine action : **WS0.1 → WS0.7** (voir TODO.md).*
