package com.example.composecraft.features.flightstatus.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composecraft.features.fittrack.components.FTBody
import com.example.composecraft.features.fittrack.components.FTTitle
import com.example.composecraft.ui.theme.LocalAppSpacing

@Composable
fun AppErrorState(
    message: String,
    modifier: Modifier = Modifier,
    title: String = "Something went wrong"
) {
    val spacing = LocalAppSpacing.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FTTitle(
            text = title,
            color = MaterialTheme.colorScheme.error
        )

        Spacer(Modifier.height(spacing.sm))

        FTBody(
            text = message,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
