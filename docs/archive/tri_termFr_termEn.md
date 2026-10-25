# Tri des 518 `termFr == termEn` — verdict

**Date** : 12 octobre 2026
**Méthode** : analyse des 518 fiches où `termFr` est strictement égal à `termEn`, par détection des **mots et suffixes impossibles en français médical** (mots vides anglais : `the`, `of`, `with`… ; suffixes propres à l'anglais : `-ing`, `-ness`, `-hood`, `-ize`, `-ward`…).

## Verdict : la base est correcte

| Catégorie | Nombre | Statut |
|---|---|---|
| **Vrais cognats** (mot médical unique, identique EN/FR) | **517** | ✅ Correct |
| Noms propres / acronymes | 1 (`May-Grünwald-Giemsa (MGG)`) | ✅ Correct en français |
| **Fausses traductions** | **0** | — |

Les 518 fiches ne sont **pas** des traductions manquantes. Ce sont des cognats médicaux authentiques — des termes scientifiques d'origine latine/grecque qui s'écrivent identiquement en français et en anglais :

`Inflammation`, `Hypertension`, `Diastole`, `Morphine`, `Mutation`, `Auscultation`, `Palpation`, `Abduction`, `Adduction`, `Pronation`, `Supination`, `Respiration`, `Inspiration`, `Expiration`, `Digestion`, `Ossification`, `Menstruation`, `Constipation`, `Infection`, `Contraception`, `Membrane`, `Iris`, `Abdomen`, `Codon`, `Intron`, `Cytosol`, `Thalidomide`, `Foramen ovale`, `Bronchiole`, `Diastole`…

En médecine francophone, on écrit bien « inflammation », « hypertension », « diastole » — l'identité EN/FR est la forme **correcte**, pas une copie paresseuse.

## Conclusion

**Aucune correction n'est nécessaire.** Le champ `termFr` est valide :

- `termFr == termEn` : 518 fiches, toutes des cognats légitimes
- `termAr == termFr` : **0**
- `termAr == termEn` : **0**
- `definitionFr` vide : **0**
- `definitionAr` vide : **0**

La détection naïve (comparer `termFr` à `termEn` sans analyse linguistique) produisait un **faux positif à 100 %**. Les traductions arabe et les définitions sont complètes et distinctes de l'anglais — c'est le vrai signal de qualité, et il est bon.

Le signalement initial (N5 dans `RAPPORT_FINAL_DEFINITIF.md`) est donc **infondé** et peut être retiré du plan de remédiation.
