package com.example.ui.viewmodel

import com.example.data.local.entity.ExerciseEntity
import com.example.domain.exercise.ExerciseLanguage
import com.example.domain.exercise.ExerciseSpec

class QuizLevelViewModel {
    fun startLevelTraining(level: Int, exercises: List<ExerciseEntity>): LevelTrainingState {
        val exercisesForLevel = exercises.filter { it.level == level }
        return LevelTrainingState(
            selectedLearningLevel = level,
            levelExercises = exercisesForLevel,
            levelCurrentIndex = 0,
            levelScore = 0,
            levelCorrectCount = 0,
            levelSelectedOption = null,
            levelIsAnswered = false,
            levelIsCompleted = false,
            levelJustUnlockedNext = false
        )
    }

    fun exitLevelTraining(): LevelTrainingState {
        return LevelTrainingState()
    }

    fun selectLevelAnswer(state: LevelTrainingState, answer: String, correctAnswer: String, points: Int): LevelTrainingState {
        if (state.levelIsAnswered) return state
        val isCorrect = answer.trim().equals(correctAnswer.trim(), ignoreCase = true)
        val newScore = if (isCorrect) state.levelScore + points else state.levelScore
        val newCorrectCount = if (isCorrect) state.levelCorrectCount + 1 else state.levelCorrectCount

        return state.copy(
            levelSelectedOption = answer,
            levelIsAnswered = true,
            levelScore = newScore,
            levelCorrectCount = newCorrectCount
        )
    }

    fun nextLevelQuestion(state: LevelTrainingState): LevelTrainingState {
        val exercises = state.levelExercises
        val currentIdx = state.levelCurrentIndex

        return if (currentIdx + 1 < exercises.size) {
            state.copy(
                levelCurrentIndex = currentIdx + 1,
                levelSelectedOption = null,
                levelIsAnswered = false
            )
        } else {
            state.copy(levelIsCompleted = true)
        }
    }

    fun loadExerciseSpec(state: LevelTrainingState, getSpec: (ExerciseEntity) -> ExerciseSpec?): LevelTrainingState {
        val exercises = state.levelExercises
        val idx = state.levelCurrentIndex
        if (exercises.isEmpty() || idx >= exercises.size) return state
        val entity = exercises[idx]
        val spec = getSpec(entity)
        return state.copy(
            currentExerciseSpec = spec,
            wordbankConstructed = emptyList(),
            matchUserPairs = emptyMap(),
            levelSelectedOption = null,
            levelIsAnswered = false
        )
    }

    companion object {
        const val MAX_LEARNING_LEVELS = 6
        const val LEVEL_UNLOCK_THRESHOLD = 70
        const val DEFAULT_POINTS_PER_QUESTION = 15
        const val WORDBANK_POINTS = 20
    }
}

data class LevelTrainingState(
    val selectedLearningLevel: Int? = null,
    val levelExercises: List<ExerciseEntity> = emptyList(),
    val levelCurrentIndex: Int = 0,
    val levelScore: Int = 0,
    val levelCorrectCount: Int = 0,
    val levelSelectedOption: String? = null,
    val levelIsAnswered: Boolean = false,
    val levelIsCompleted: Boolean = false,
    val levelJustUnlockedNext: Boolean = false,
    val currentExerciseSpec: ExerciseSpec? = null,
    val wordbankConstructed: List<String> = emptyList(),
    val matchUserPairs: Map<String, String> = emptyMap()
)