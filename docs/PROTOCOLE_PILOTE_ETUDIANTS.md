# Protocole du pilote étudiants (phase 3.4)

**Objectif :** mesurer si la séance « histoire » améliore réellement
l'apprentissage — pas seulement l'engagement. Dérivé du guide d'analyse
§10 (« Comment savoir si cela améliore réellement MedLingo ? »).

## Design

- **Participants :** ≥ 5 étudiants en médecine (préférence PCEM1–2), volontaires.
- **Comparaison :** chaque participant alterne, à temps de pratique comparable
  (~15 min/séance), une **séance quiz classique** et une **séance histoire**
  sur des contenus de difficulté voisine (même module, même niveau interne).
- **Contre-ordre :** moitié des participants commence par chaque format.

## Ce qu'on mesure

| Mesure | Quand | Comment |
|---|---|---|
| Compréhension sur un **audio neuf** (pas le texte travaillé) | fin de séance | 5 questions sur un passage inédu de difficulté voisine |
| Rappel d'expressions | J+7 | test de reconnaissance + production courte des expressions ciblées |
| Transfert | fin de séance | réussite sur la situation de transfert (la mécanique l'enregistre : `firstTry` vs `mistakes` vs `helped`) |
| Aides nécessaires | pendant | comptées automatiquement (`helpedIds`, `difficultPhraseIds`) |
| Rétention/abandon | sur 2 semaines | séances terminées / commencées (storyProgress) |
| Sentiment de facilité et confiance | fin | auto-évaluation courte (échelle 1–5), identifiée comme auto-déclarée |

## Règles d'honnêteté (non négociables)

- Les productions orales « j'ai essayé » ne sont **jamais** des notes de
  prononciation (l'UI l'affiche déjà ; le garder dans l'analyse).
- Pas de promesse de niveau (« 100 heures = C1 » interdit).
- Correction humaine si possible **en aveugle** du format suivi.
- Versions différentes du test de J+7 pour éviter la mémorisation du test.

## Critères de décision (à affiner après les 5 premiers participants)

- **Continuer le format histoire** si : compréhension audio neuf ≥ format quiz
  ET rappel J+7 ≥ format quiz, sans hausse du temps par séance > 30 %.
- **Ajuster** si : beaucoup d'aides (> 2/séance) ou abandons en CIRCLING →
  raccourcir les histoires, simplifier les questions.
- **Mesure secondaire utile** : réutilisation spontanée des expressions dans
  la production libre du transfert.

## Aspects pratiques

- Durée totale par participant : ~20 min/séance × 4 séances + test J+7 (~10 min).
- Rémunération/valorisation : à décider (aucune exigence).
- Données : locales à l'appareil (aucune donnée envoyée — le mode est
  entièrement hors-ligne) ; relever les compteurs avec l'accord du participant.
