package com.example.composecraft.presentation.components.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

sealed interface UiState {
    data object Loading : UiState
    data object Success : UiState
    data object Error : UiState
}
@Composable
fun StateAnimationDemo(
    state: UiState
) {
    AnimatedContent(
        targetState = state,
        label = "uiState"
    ) { currentState ->

        when (currentState) {

            UiState.Loading -> {
                CircularProgressIndicator()
            }

            UiState.Success -> {
                Text("Success")
            }

            UiState.Error -> {
                Text("Something went wrong")
            }
        }
    }
}