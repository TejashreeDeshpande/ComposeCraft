package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

object FTAppButtonColors {

    @Composable
    fun primary() = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    )
    @Composable
    fun secondary() = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    )

    @Composable
    fun tertiary() = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary
    )

    @Composable
    fun text() = ButtonDefaults.textButtonColors(
        contentColor = MaterialTheme.colorScheme.primary
    )
}