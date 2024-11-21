package br.senai.sp.jandira.touccanuser.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FeedbackViewModel : ViewModel() {
    private val _stars = MutableStateFlow(0) // Default to 0 stars
    val stars: StateFlow<Int> = _stars

    private val _review = MutableStateFlow("")
    val review: StateFlow<String> = _review

    fun setStars(stars: Int) {
        _stars.value = stars
    }

    fun setReview(review: String) {
        _review.value = review
    }
}
