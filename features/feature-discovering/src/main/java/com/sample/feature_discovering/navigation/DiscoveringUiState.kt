package com.sample.feature_discovering.navigation

import com.sample.model.Topic

sealed interface DiscoveringUiState {
    object Loading : DiscoveringUiState
    data class Success(
        val topic: Topic
    ) : DiscoveringUiState {

    }
}