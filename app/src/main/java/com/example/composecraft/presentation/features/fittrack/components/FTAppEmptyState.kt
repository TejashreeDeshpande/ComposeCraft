package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import com.example.composecraft.ui.theme.LocalAppSpacing


@Composable
fun AppEmptyState(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
    action: @Composable (() -> Unit)? = null
) {
    val spacing = LocalAppSpacing.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        icon?.invoke()

        Spacer(Modifier.height(spacing.md))

        FTTitle(text = title)

        Spacer(Modifier.height(spacing.sm))

        FTBody(
            text = message,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(spacing.md))

        action?.invoke()
    }
}