# PLAN — 10 000+ exercices (3 par terme × 4 504 termes = 13 512)

## 1. CONTEXTE & PROBLÈME

**Données actuelles :**
- 4 504 termes médicaux trilingues (EN/FR/AR) répartis sur 15 modules
- 44 exercices manuellement écrits (68 % d'anatomie)
- `LessonGenerator` génère 90 leçons (8 questions chacune) → 720 questions totales
- **Objectif** : 10 000+ exercices, **3 exercices par terme** (13 512 exercices minimum)

**Ce qui ne va PAS :**
- ⛔ Écrire 13 512 exercices manuellement → IMPOSSIBLE en temps raisonnable
- ⛔ Dupliquer le `LessonGenerator` pour chaque exercice → trop fragmenté
- ⛔ Ajouter des exercices un par un → pas évolutif

**Ce qui FONCTIONNE :**
- ✅ Les 4 504 fiches trilingues sont la source de vérité
- ✅ `ExerciseEntity` structure les données (id, level, type, question, options, answer, etc.)
- ✅ `LessonGenerator` montre la méthode : dériver des questions à partir de termes
- ✅ Les modèles de questions (QCM, fill_blank, sentence_order, matching) sont établis

---

## 2. APPROCHE : GÉNÉRATEUR AUTOMATIQUIQUE À PARTIR DE TEMPLATES

### 2.1 Architecture

```
Termes (4 504 fiches trilingues)
    ↓
[Template Engine] — 6 templates de questions
    ↓
Exercices générés (13 512) → base de données (Room) ou fichiers JSON
    ↓
Android app + Web app (web-react) → UI normale
```

### 2.2 Templates définis (10 types de questions)

| Template | Type | Question | Réponse | Créneau niveau |
|---|---|---|---|---|
| **T1 Translat** | mcq | French translation of « X »? | termFr | L1-L2 |
| **T2 Translat AR** | mcq | Arabic translation of « X »? | termAr | L1-L2 |
| **T3 Définition** | mcq | Which term matches this definition? | termEn | L2-L3 |
| **T4 Fill blank** | fill_blank | The ______ is the French for 'X' | termEn | L1-L3 |
| **T5 Match** | matching | Match each term with its French translation | termEn + termFr | L1-L2 |
| **T6 Sentence** | sentence_order | Arrange words to form a sentence | sentence_en | L3-L4 |
| **T7 Antonym** | mcq | Match term with its antonym/opposite | termEn | L3-L4 |
| **T8 Clinical Context** | clinical_case | Identify the term used in this clinical vignette | termEn | L4-L6 |
| **T9 Sentence Context** | mcq | Which sentence correctly uses the term? | termEn | L3-L5 |
| **T10 IPA/Pronunciation** | mcq | Match term with its IPA transcription | termEn + ipa | L1-L4 |

### 2.3 Flashcards (nouveau système de données)

Pour chaque terme, générer 1 flashcard avec :
- **Front** : `termEn` + `termFr` + `termAr` + illustration (si disponible)
- **Back** : `definitionEn`, `definitionFr`, `definitionAr`, `exampleEn`, `etymology`, `clinicalPearl`
- **Méta-données** : `termId`, `module`, `chapter`, `ipa`, `audioUrl`, `level`
- **SM-2** : `easeFactor`, `intervalDays`, `repetitions`, `lastReviewed`

```kotlin
@Entity(tableName = "flashcards")
data class FlashcardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val termId: Int,
    val frontEn: String, val frontFr: String, val frontAr: String,
    val backEn: String, val backFr: String, val backAr: String,
    val backDefinition: String, val backExample: String, val backEtymology: String,
    val backAudioUrl: String = "", val ipa: String,
    val module: String, val chapter: String, val level: Int = 1,
    val easeFactor: Float = 2.5f, val intervalDays: Int = 0,
    val repetitions: Int = 0, val lastReviewed: Long = 0L
)
```

Génération automatique : `FlashcardGenerator.generateAll()` → 4 504 flashcards (1 par terme).

---

### 2.4 Répartition des 3 exercices par terme

| Terme | Exercice 1 | Exercice 2 | Exercice 3 |
|---|---|---|---|
| Tout terme avec `termFr` | T1 Translat | T4 Fill blank | T7 Antonym (si possible) |
| Tout terme avec `termAr` | T2 Translat AR | T4 Fill blank | T7 Antonym (si possible) |
| Tout terme avec `definitionEn` | T3 Définition | T4 Fill blank / T9 Sentence Context | T8 Clinical Context (si `clinicalPearl`) |
| Tout terme avec `exampleEn` | T5 Match | T6 Sentence | T9 Sentence Context |
| Tout terme avec `clinicalPearl` | T8 Clinical Context | T3 Définition | T7 Antonym (si possible) |
| Tout terme avec `etymology` | T7 Antonym | T6 Sentence | T8 Clinical Context |
| Tout terme avec `ipa` | T10 IPA/Pronunciation | T4 Fill blank | T6 Sentence |

**Règle** : chaque terme reçoit exactement 3 exercices (ou le maximum possible si données insuffisantes).

---

## 3. IMPLÉMENTATION

### 3.1 Nouveau fichier : `BulkExerciseGenerator.kt`

Location : `app/src/main/java/com/example/domain/lesson/`

```kotlin
package com.example.domain.lesson

import com.example.data.local.entity.ExerciseEntity
import com.example.data.local.entity.FlashcardEntity
import com.example.data.local.entity.MedicalTermEntity
import com.example.data.initial.InitialData

object BulkExerciseGenerator {

    const val EXERCISES_PER_TERM = 3

    private enum class Template {
        TRANSLATION_FR, TRANSLATION_AR, DEFINITION, FILL_BLANK, MATCH, SENTENCE,
        ANTONYM, CLINICAL_CONTEXT, SENTENCE_CONTEXT, IPA
    }

    // Génère tous les exercices pour un module
    fun generateModule(moduleTitle: String, terms: List<MedicalTermEntity>): List<ExerciseEntity> {
        val exercises = mutableListOf<ExerciseEntity>()
        for (term in terms) {
            val exs = generateForTerm(moduleTitle, term)
            exercises.addAll(exs)
        }
        return exercises
    }

    // Génère 3 exercices pour un terme (ou moins si données manquantes)
    private fun generateForTerm(moduleTitle: String, term: MedicalTermEntity): List<ExerciseEntity> {
        val exercises = mutableListOf<ExerciseEntity>()

        // T1 : Traduction FR
        if (term.termFr.isNotBlank()) {
            exercises += translationExercise(
                moduleTitle, term, "fr",
                questionEn = "What is the French translation of \"${term.termEn}\"?",
                questionFr = "Quelle est la traduction française de « ${term.termEn} » ?",
                questionAr = "ما الترجمة الفرنسية للمصطلح « ${term.termEn} »؟",
                answer = term.termFr
            )
        }

        // T2 : Traduction AR
        if (term.termAr.isNotBlank()) {
            exercises += translationExercise(
                moduleTitle, term, "ar",
                questionEn = "What is the Arabic translation of \"${term.termEn}\"?",
                questionFr = "Quelle est la traduction arabe de « ${term.termEn} » ?",
                questionAr = "ما الترجمة العربية للمصطلح « ${term.termEn} »؟",
                answer = term.termAr
            )
        }

        // T3 : Définition
        if (term.definitionEn.isNotBlank() || term.definitionFr.isNotBlank()) {
            exercises += definitionExercise(
                moduleTitle, term,
                questionEn = term.definitionEn.ifBlank { term.definitionFr },
                questionFr = term.definitionFr.ifBlank { term.definitionEn },
                questionAr = term.definitionAr.ifBlank { term.definitionEn },
                answer = term.termEn
            )
        }

        // T4 : Fill blank
        if (exercises.size < 3 && term.termEn.isNotBlank()) {
            exercises += fillBlankExercise(
                moduleTitle, term,
                questionEn = "The ______ is the French for '${term.termEn}'",
                questionFr = "Le ______ est le français de « ${term.termEn} »",
                questionAr = "ما هو المصطلح الذي يناسب: '« ${term.termEn} »؟'",
                answer = term.termEn
            )
        }

        // T5 : Match (only if we have enough terms in the module)
        if (exercises.size < 3 && moduleHasMultipleTerms(moduleTitle)) {
            exercises += matchingExercise(moduleTitle, term, moduleTitle)
        }

        // T6 : Sentence order
        if (exercises.size < 3 && moduleHasValidSentences(moduleTitle)) {
            exercises += sentenceOrderExercise(moduleTitle, term)
        }

        // T7 : Antonym (only if term has definition and module has multiple terms)
        if (exercises.size < 3 && term.definitionEn.isNotBlank() && moduleHasMultipleTerms(moduleTitle)) {
            exercises += antonymExercise(moduleTitle, term)
        }

        // T8 : Clinical Context (if clinicalPearl available)
        if (exercises.size < 3 && term.clinicalPearl.isNotBlank()) {
            exercises += clinicalContextExercise(moduleTitle, term)
        }

        // T9 : Sentence context (if exampleEn available)
        if (exercises.size < 3 && term.exampleEn.isNotBlank() && moduleHasMultipleTerms(moduleTitle)) {
            exercises += sentenceContextExercise(moduleTitle, term)
        }

        // T10 : IPA/Pronunciation
        if (exercises.size < 3 && term.ipa.isNotBlank()) {
            exercises += ipaExercise(moduleTitle, term)
        }

        return exercises.take(EXERCISES_PER_TERM)
    }

    // ... méthodes d'implémentation pour chaque template ...
}

object FlashcardGenerator {
    fun generateAll(): List<FlashcardEntity> {
        val cards = mutableListOf<FlashcardEntity>()
        for (term in InitialData.termsList) {
            cards += createCardForTerm(term)
        }
        return cards
    }

    private fun createCardForTerm(term: MedicalTermEntity): FlashcardEntity {
        return FlashcardEntity(
            termId = term.id,
            frontEn = term.termEn,
            frontFr = term.termFr.ifBlank { "" },
            frontAr = term.termAr.ifBlank { "" },
            backEn = term.definitionEn,
            backFr = term.definitionFr.ifBlank { "" },
            backAr = term.definitionAr.ifBlank { "" },
            backDefinition = term.definitionEn,
            backExample = term.exampleEn,
            backEtymology = term.etymology,
            backAudioUrl = term.audioUrl,
            ipa = term.ipa,
            module = term.module,
            chapter = term.chapter,
            level = term.level,
            easeFactor = 2.5f,
            intervalDays = 0,
            repetitions = 0,
            lastReviewed = 0L
        )
    }
}
```

### 3.2 Intégration dans MedLinguaRepository

```kotlin
@Singleton
class MedLinguaRepository @Inject constructor(
    @AppDatabase private val database: AppDatabase
) {
    private val exerciseDao = database.exerciseDao()
    private val flashcardDao = database.flashcardDao()

    fun initializeDatabaseIfEmpty() {
        // Générer les exercices et flashcards si la base est vide
        val generator = BulkExerciseGenerator()
        val newExercises = generator.generateAllModules()
        if (newExercises.isNotEmpty()) {
            exerciseDao.insertAll(newExercises)
        }

        val newFlashcards = FlashcardGenerator.generateAll()
        if (newFlashcards.isNotEmpty()) {
            flashcardDao.insertAll(newFlashcards)
        }
    }
}
```

### 3.3 Méthodes de génération séparée (pour exécuter en arrière-plan)

```kotlin
// build.gradle.kts
tasks.register("generateBulkExercises") {
    doLast {
        val generator = BulkExerciseGenerator()
        val exercises = generator.generateAllModules()
        val flashcardGenerator = FlashcardGenerator()
        val flashcards = flashcardGenerator.generateAll()

        // Sauvegarder dans un fichier JSON
        val outputDir = File("build/generatedExercises")
        outputDir.mkdirs()
        ObjectMapper().writeValue(
            File(outputDir, "exercises.json"), exercises
        )
        ObjectMapper().writeValue(
            File(outputDir, "flashcards.json"), flashcards
        )
    }
}
```

```bash
# Lancer la génération
./gradlew :app:generateBulkExercises
```

---

## 4. SYSTÈME DE GESTION DES DONNÉES MANQUANTES

| Champ | Condition | Action |
|---|---|---|
| `termFr` vide | T1 ne peut pas être généré | Sauter T1, essayer T3/T4 |
| `termAr` vide | T2 ne peut pas être généré | Sauter T2, essayer T3/T4 |
| `definitionEn` vide | T3 ne peut pas être généré | Essayer T4/T5 |
| `exampleEn` vide | T6 ne peut pas être généré | Sauter T6 |
| Module avec < 4 termes | T5/T6 ne peuvent pas être générés | Sauter ces templates |

**Règle de sécurité** : toujours générer 3 exercices si possible, sinon le maximum possible.

---

## 5. GESTION DE LA BASE DE DONNÉES

### 5.1 Méthode d'insertion

```kotlin
@Transaction
suspend fun insertBulkExercises(exercises: List<ExerciseEntity>) {
    // Batch insert avec Room
    exercises.forEach { dao.insertExercise(it) }
}
```

### 5.2 Gestion des IDs
- IDs positifs : `System.currentTimeMillis()` pour éviter les collisions
- SI les IDs existent déjà (suite à une régénération), utiliser `OnConflictStrategy.REPLACE`

### 5.3 Nettoyage avant génération
```kotlin
// Supprimer les anciens exercices automatiquement générés
dao.deleteExercises { it.id > 9000000000 } // Marquer les exercices générés
// Puis insérer les nouveaux
```

---

## 6. WEB REACT : MISE À JOUR

### 6.1 `web-react/src/domain/lessons.ts`
Ajouter les nouveaux templates de génération :
```typescript
// Ajouter les templates T4 (fill_blank), T6 (sentence_order)
```

### 6.2 `web-react/public/data/exercises.json`
Générer le fichier avec les 112 exercices existants + les exercices générés automatiquement.

### 6.3 Tests web
- `lessons.test.ts` : vérifier que la génération produit le bon nombre d'exercices
- `i18n.test.ts` : vérifier que les clés sont correctes

---

## 7. TESTING

### 7.1 Tests unitaires critiques
```kotlin
class BulkExerciseGeneratorTest {
    @Test
    fun `each term produces 3 exercises`() {
        val terms = InitialData.termsOfModule("Anatomie")
        val exercises = BulkExerciseGenerator.generateModule("Anatomie", terms)
        assertEquals(terms.size * 3, exercises.size)
    }

    @Test
    fun `exercises have correct structure`() {
        val exercises = BulkExerciseGenerator.generateModule("Anatomie", InitialData.termsOfModule("Anatomie"))
        for (ex in exercises) {
            assertTrue(ex.questionEn.isNotBlank())
            assertTrue(ex.correctAnswer.isNotBlank())
            assertTrue(ex.optionsRaw.isNotBlank())
        }
    }

    @Test
    fun `no duplicate exercises`() {
        val exercises = BulkExerciseGenerator.generateModule("Anatomie", InitialData.termsOfModule("Anatomie"))
        val ids = exercises.map { it.id }.toSet()
        assertEquals(exercises.size, ids.size)
    }
}
```

### 7.2 Tests d'intégration
```kotlin
class BulkExerciseGeneratorIntegrationTest {
    @Test
    fun `total exercises across all modules >= 10000`() {
        val allExercises = BulkExerciseGenerator.generateAllModules()
        assertTrue(allExercises.size >= 10000)
    }

    @Test
    fun `each term in each module produces 3 exercises`() {
        for (module in InitialData.modulesList) {
            val terms = InitialData.termsOfModule(module.titleFr)
            val exercises = BulkExerciseGenerator.generateModule(module.titleFr, terms)
            assertEquals(terms.size * 3, exercises.size)
        }
    }

    @Test
    fun `total flashcards == total terms`() {
        val flashcards = FlashcardGenerator.generateAll()
        assertEquals(InitialData.termsList.size, flashcards.size)
    }

    @Test
    fun `each term has exactly one flashcard`() {
        val flashcards = FlashcardGenerator.generateAll()
        val termIds = flashcards.map { it.termId }.toSet()
        assertEquals(InitialData.termsList.size, termIds.size)
    }
}
```

### 7.3 Tests de type de question
```kotlin
class TemplateTypeTest {
    @Test
    fun `T1-T6 are mcq and fill_blank only`() {
        val exercises = BulkExerciseGenerator.generateModule("Anatomie", InitialData.termsOfModule("Anatomie"))
        val allowedTypes = setOf("mcq", "fill_blank", "matching", "sentence_order", "clinical_case")
        for (ex in exercises) {
            assertTrue(ex.type in allowedTypes, "Unexpected type: ${ex.type}")
        }
    }
}
```

---

## 8. DISTRIBUTION ET DÉPLOIEMENT

### 8.1 Android
1. Ajouter le générateur à `MedLinguaRepository`
2. Exécuter la génération lors de l'initialisation
3. Valider avec `./gradlew :app:testDebugUnitTest`

### 8.2 Web React
1. Mettre à jour `lessons.ts` avec les nouveaux templates
2. Générer `exercises.json`
3. Vérifier avec `npm test` et `npm run build`

### 8.3 Backend (optionnel)
- Si le backend est activé plus tard, le générateur peut être transporté en TypeScript pour la cohérence

---

## 9. MATRICE DE COMPLEXITÉ

| Étape | Complexité | Temps estimé | Dépendances |
|---|---|---|---|
| 1. Créer `BulkExerciseGenerator.kt` | Moyenne | 4h | Aucune |
| 2. Intégrer dans `MedLinguaRepository` | Moyenne | 2h | Aucune |
| 3. Tests unitaires | Moyenne | 3h | Étape 1 |
| 4. Tests d'intégration | Moyenne | 2h | Étapes 1-3 |
| 5. Web React update | Moyenne | 2h | Aucune |
| 6. Build Android + Web | Moyenne | 1h | Étapes 1-5 |
| **Total estimé** |  | **~14h** |  |

---

## 10. RISQUES & MITIGATIONS

| Risque | Impact | Mitigation |
|---|---|---|
| Données manquantes (92 % `etym` vides) | Moyen | Sauter les templates qui nécessitent des champs vides |
| Performance de génération | Faible | Générer en arrière-plan ou pré-générer et stocker en DB |
| Duplication d'exercices | Moyen | Vérifier les IDs, utiliser un hash unique |
| Table de données trop grande | Faible | Indexer par module + niveau + type |
| Leçon générée trop facile | Moyen | Varier la difficulté selon le niveau |

---

## 11. PLAN D'EXÉCUTION

### Semaine 1
- Jour 1-2 : Créer `BulkExerciseGenerator.kt`
- Jour 3-4 : Intégrer et écrire les tests unitaires
- Jour 5 : Exécuter les tests, corriger les bugs

### Semaine 2
- Jour 6-7 : Intégrer dans `MedLinguaRepository`, tests d'intégration
- Jour 8-9 : Web React update, génération des fichiers
- Jour 10-11 : Build Android + Web, validation finale

---

## 12. MESURE DE SUCCÈS

### Critères de validation
1. ✅ Total d'exercices ≥ 10 000
2. ✅ Chaque terme a au moins 2 exercices (idéalement 3)
3. ✅ Aucun exercice dupliqué
4. ✅ Structure de chaque exercice valide (question, options, answer)
5. ✅ Build Android et Web réussis
6. ✅ Tests unitaires 100 % verticaux

### Indicateurs de qualité
- `exercices/totalTerms` : ratio d'exercices par terme
- `exercicesAvecIdentifiantUniquely` : exercices avec identifiant unique
- `tempsDeChargementApp` : maintenir < 10 secondes
- `tailleApk` : maintenir < 10 Mo

---

## 13. CONCLUSION

Ce plan est **réalisable** et **évolutif**. Il ne nécessite pas 13 512 lignes de code manuscrites, mais plutôt une **infrastructure génératrice** qui produit des exercices à partir des données existantes. L'approche template-based est :
- ✅ **Évolutive** : ajouter un nouveau template = nouveau type d'exercice
- ✅ **Maintenable** : un seul endroit où modifier la logique
- ✅ **Stable** : les données sources ne changent pas, les templates sont stables
- ✅ **Testable** : chaque template est testable indépendamment

La clé du succès est de ne pas réinventer la roue : réutiliser l'infrastructure existante (LessonGenerator, ExerciseEntity, InitialData) et l'étendre pour gérer le volume important.
