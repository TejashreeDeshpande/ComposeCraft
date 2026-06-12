package com.example.composecraft.presentation.features.animtedfilter

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.Waves
import androidx.compose.ui.graphics.vector.ImageVector

enum class MosaicFilter(
    val title: String,
    val icon: ImageVector
) {
    AI("AI", Icons.Default.AutoAwesome),
    MOTION("Motion", Icons.Default.Waves),
    DESIGN("Design", Icons.Default.Brush),
    ANDROID("Android", Icons.Default.Android),
    MUSIC("Music", Icons.Default.MusicNote),
    VIDEO("Video", Icons.Default.Videocam),
    SPACE("Space", Icons.Default.Public),
    INTERACTION("Interaction", Icons.Default.TouchApp)
}