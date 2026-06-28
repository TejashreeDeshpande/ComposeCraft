package com.example.composecraft.features.flightstatus.presentation.components

import androidx.compose.runtime.Composable


sealed interface AppUiState<out T> {
    data object Loading: AppUiState<Nothing>
    data class Error(val message: String): AppUiState<Nothing>
    data object Empty: AppUiState<Nothing>
    data class Content<T>(val data: T): AppUiState<T>
}

@Composable
fun <T> FTAppScreenState(
    state: AppUiState<T>,
    loading: @Composable () -> Unit = { LoadingOverlay() },
    error: @Composable (String) -> Unit = {
        AppErrorState(message = it)
    },
    empty: @Composable () -> Unit = {
        AppEmptyState(
            title = "No data",
            message = "Nothing to show yet."
        )
    },
    content: @Composable (T) -> Unit
) {
    when (state) {
        AppUiState.Loading -> loading()
        is AppUiState.Error -> error(state.message)
        AppUiState.Empty -> empty()
        is AppUiState.Content -> content(state.data)
    }
}