package com.example.composecraft.presentation.components.easy

sealed class NetworkResult<out T> {
    data object Loading: NetworkResult<Nothing>()
    data class Success<out T>(val data: T): NetworkResult<T>()
    data class Error(val message: String): NetworkResult<Nothing>()
}

fun processResponse(state: NetworkResult<String>): String {
    return when (state) {
        is NetworkResult.Loading -> {
            "Loading.."
        }
        is NetworkResult.Success -> {
            "Received ${state.data}"
        }
        is NetworkResult.Error -> {
            "Failed with ${state.message}"
        }
    }
}