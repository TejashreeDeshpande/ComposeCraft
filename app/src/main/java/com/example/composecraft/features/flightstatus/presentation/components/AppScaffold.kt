package com.example.composecraft.features.flightstatus.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.ui.theme.ComposeCraftTheme

@Preview(showBackground = true)
@Composable
fun AppScaffoldPreview() {
    ComposeCraftTheme {
        AppScaffold(
            topBar = {
                Text(
                    text = "Flight Status",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Main Content")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppScaffoldLoadingPreview() {
    ComposeCraftTheme {
        AppScaffold(
            loading = true,
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Main Content")
                }
            }
        )
    }
}

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    loading: Boolean = false,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    content: @Composable (PaddingValues) -> Unit
    ) {
    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = topBar,
            bottomBar = bottomBar,
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
            content = content
        )
        if (loading) {
            LoadingOverlay()
        }
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

@Preview(showBackground = true)
@Composable
fun LoadingOverlayPreview() {
    ComposeCraftTheme {
        LoadingOverlay()
    }
}
