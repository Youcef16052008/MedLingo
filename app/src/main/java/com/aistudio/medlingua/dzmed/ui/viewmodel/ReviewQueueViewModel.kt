package com.aistudio.medlingua.dzmed.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aistudio.medlingua.dzmed.data.local.dao.ReviewQueueDao
import com.aistudio.medlingua.dzmed.data.local.entity.ReviewQueueItem
import com.aistudio.medlingua.dzmed.localization.Language
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReviewQueueViewModel(
    private val reviewQueueDao: ReviewQueueDao
) : ViewModel() {

    val unlockedReviews: StateFlow<List<ReviewQueueItem>> = reviewQueueDao
        .getUnlockedReviews()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allReviews: StateFlow<List<ReviewQueueItem>> = reviewQueueDao
        .getAllReviews()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun refresh() {
        viewModelScope.launch {
            // Force a reload from the database
            reviewQueueDao.getUnlockedReviews()
        }
    }

    fun playReview(item: ReviewQueueItem) {
        viewModelScope.launch {
            reviewQueueDao.setReviewStatus(item.id, ReviewQueueItem.Status.PLAYED)
        }
    }

    fun deleteReview(item: ReviewQueueItem) {
        viewModelScope.launch {
            reviewQueueDao.deleteReview(item.id)
        }
    }

    fun markAsPlayed(item: ReviewQueueItem) {
        viewModelScope.launch {
            reviewQueueDao.setReviewStatus(item.id, ReviewQueueItem.Status.PLAYED)
        }
    }
}
