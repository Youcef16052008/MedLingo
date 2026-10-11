# MedLingo vs Duolingo — comparaison architecturelle honnête

**Date :** 10 octobre 2026 · **Basé sur :** le code réellement exécuté (`grep`-vérifié)

---

## La réponse courte

**Non. L'architecture n'est pas 100 % identique à Duolingo — et c'est volontaire.**

La **boucle d'engagement** (les mécaniques de jeu qui font revenir l'utilisateur)
est un clone fidèle de Duolingo. L'**infrastructure** est sciemment différente :
Duolingo est une app serveur multi-appareils, MedLingo est une app
**offline-first mono-utilisateur**, adaptée à son public (PCEM1 algériens,
connexion faible, pas de moyen de paiement en ligne).

C'est un choix de conception, pas un retard. Voici la mesure exacte.

---

## 1. Ce qui est identique à Duolingo ✅ (la boucle d'engagement)

| Mécanique Duolingo | MedLingo | Preuve |
|---|---|---|
| Parcours en nœuds séquentiels | ✅ 90 nœuds (15 modules × 6 niveaux) | `PathBuilder`, `PathScreen` |
| Déblocage conditionnel | ✅ ≥ 70 % pour passer au nœud suivant | `PathBuilder.UNLOCK_SCORE` |
| Leçons générées à partir de contenu | ✅ 90/90 leçons jouables, 8 questions | `LessonGenerator` |
| Types de questions variés | ✅ 8 kinds : QCM traduction EN/FR/AR, définition, appariement, étymologie, lecture, cas clinique | `Kind` enum |
| Distracteurs proches + difficulté croissante | ✅ similarité Jaccard, banque proche/éloignée selon la position dans la leçon | `distractors()` |
| **Ligues** (Bronze → Diamond) | ✅ 10 tiers, 30 membres, promotion/démotion hebdomadaire | `LeagueManager` |
| Bots qui progressent dans la ligue | ✅ rythme hebdomadaire propre à chaque bot, bruit déterministe | `LeagueBotAnimator` |
| **Gemmes** (lingots) | ✅ ledger append-only anti-triche, transactions signées | `gems_transactions` |
| **Trophées** (achievements) | ✅ 12 trophées | `TrophyManager` |
| **Caisses** (chests) | ✅ récompenses aléatoires | `ChestReward` |
| **Série** (streak) 🔥 | ✅ + gel gratuit (streak freeze) | `ProgressionManager` |
| **Répétition espacée** (SRS) | ✅ algorithme SM-2 complet | `SpacedRepetitionAlgorithm` |
| **Tests adaptatifs** (CAT) | ✅ difficulté adaptée au niveau | couche domain |
| **Notifications de rappel** | ✅ 4 notifications deep-linkées, i18n trilingue | `NotificationHelper` |
| **Studio de prononciation** | ✅ TTS multi-langue (EN/FR/AR) | `AudioPronunciationStudio` |
| **Abonnement premium** | ✅ `Super MedLingo` (modèle Super Duolingo) | `SuperPaywallDialog` |
| **i18n complète** | ✅ ~200 clés × 3 langues (EN/FR/AR) | `AppLanguage.kt` |

**Score boucle d'engagement : 17/17 mécaniques Duolingo présentes.**

---

## 2. Ce qui est différent — par choix, pas par oubli ⚠️

| Architecture Duolingo | MedLingo | Pourquoi |
|---|---|---|
| **Serveur central** + sync multi-appareils | **Offline-first**, base Room locale | Public cible : connexion faible, forfaits limités |
| **Compte utilisateur** (email/Google/phone OTP) | **Aucune auth**, `userId = 1` | Pas de serveur → pas besoin de compte |
| **Birdbrain** (difficulté adaptative ML côté serveur) | **CAT local** basé sur le niveau | L'algo ML existe dans `backend/functions/` mais n'est pas branché |
| **Paiement** (Stripe / Google Play / App Store) | **Paiement en gemmes** (monnaie de l'app) | Pas de compte marchant Google pour l'Algérie |
| **Leaderboards humains** | **Ligues remplies de bots** | Pas de serveur → pas d'autres joueurs réels |
| **Cœurs** (5 vies, régénération) | **Supprimés** (migration v10) | Choix produit : pas de frustration, jeu illimité |
| **Mascotte animée** (Duo) | **2 illustrations** statiques | Médical, pas ludique |
| **Social** (amis, feeds, clans) | **Absent** | Offline-first |

---

## 3. La différence de fond — le contenu

C'est ici que MedLingo diverge le plus, et c'est sa **force** :

| | Duolingo | MedLingo |
|---|---|---|
| Contenu | Langues générales (es, fr, de…) | **Terminologie médicale** (anatomie, physiologie, pharmacologie…) |
| Niveau | Grand public | **Universitaire** (PCEM1, cursus algérien) |
| Langues | ~40 langues cibles | **Trilingue** : EN ↔ FR ↔ AR |
| Profondeur par terme | Mot + phrase d'exemple | **Fiche complète** : définition ×3 langues, étymologie, perle clinique, mnemonic, phonétique IPA, exemple ×3 |
| Volume | Cours construits main | **4 504 fiches** dans 15 modules |
| Mode hors-ligne | Partiel | **Total** |

**MedLingo n'est pas un Duolingo pour le médical. C'est un outil de
vocabulaire médical trilingue qui emprunte la boucle d'engagement de Duolingo.**
C'est une distinction importante : la valeur n'est pas dans l'imitation, elle
est dans le contenu médical — chose que Duolingo ne fera jamais.

---

## 4. Écart résiduel (les 2 champs non dérivables)

| Champ | Taux de remplissage | Pourquoi |
|---|---|---|
| `exampleEn` / FR / AR | **85,5 %** (3 849/4 504) | Dérivé des définitions (Phase 5) |
| `etymology` | **7,6 %** | Exige un dictionnaire étymologique — 295 termes seulement ont un suffixé grec/latin reconnaissable |
| `clinicalPearl` | **10,0 %** | Fait médical : la générer serait de l'invention, pas de la dérivation |
| `mnemonic` | **3,4 %** | Heuristique sur racines, à relecture éditoriale |

Ces champs ne peuvent pas être remplis automatiquement de façon responsable.
C'est la frontière entre la mécanique (faite) et le contenu éditorial (humain).

---

## 5. Verdict

**Sur la boucle d'engagement : MedLingo est un clone fidèle et fonctionnel de
Duolingo (17/17 mécaniques).**

**Sur l'infrastructure : c'est sciemment plus simple** — offline-first,
mono-utilisateur, pas de serveur. C'est cohérent avec le public et c'est ce qui
rend l'app utilisable en Algérie. `backend/functions/` (Supabase, Birdbrain)
existe dans le repo comme direction future, documenté comme non branché.

**Sur le fond : MedLingo fait quelque chose que Duolingo ne fait pas** —
enseigner le vocabulaire médical universitaire en trois langues, hors-ligne,
avec 4 504 fiches profondes. L'imitation de Duolingo est le moyen (la
motivation), pas la fin (la compétence médicale trilingue).
