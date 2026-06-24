package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PreviewFTScaffold() {
    FTScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {},
        bottomBar = {},
        loading = false,
        snackbarHostState = SnackbarHostState(),
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.Gray)
            )
        }
    )
}

@Composable
fun FTScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    loading: Boolean = false,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    content: @Composable (PaddingValues) -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Scaffold(
            topBar = topBar,
            bottomBar = bottomBar,
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
            content = content
        )
        if (loading)
            LoadingOverlay()
    }
}

@Composable
fun LoadingOverlay(
    modifier: Modifier = Modifier,
    message: String = "Loading"
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.25f))
            .semantics {
                this.contentDescription = message
            },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}