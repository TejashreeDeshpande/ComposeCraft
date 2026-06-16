package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

object FTTopAppBarColors {
    @Composable
    fun primary(): TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary,
        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
    )

    @Composable
    fun secondary(): TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        titleContentColor = MaterialTheme.colorScheme.onSecondary,
        navigationIconContentColor = MaterialTheme.colorScheme.onSecondary,
        actionIconContentColor = MaterialTheme.colorScheme.onSecondary
    )

    @Composable
    fun tertiary(): TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.tertiary,
        titleContentColor = MaterialTheme.colorScheme.onTertiary,
        navigationIconContentColor = MaterialTheme.colorScheme.onTertiary,
        actionIconContentColor = MaterialTheme.colorScheme.onTertiary
    )
}