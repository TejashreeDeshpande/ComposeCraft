package com.example.composecraft.features.fittrack.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.fittrack.navigation.LocalNavigationRailToggle
import com.example.composecraft.ui.theme.FitTrackHeaderGradient


@Preview(showBackground = true)
@Composable
fun PreviewFTTopAppBar() {
    FTTopAppBar(
        title = "FitTrack",
        subTitle = "Your fitness journey",
        gradient = Brush.horizontalGradient(FitTrackHeaderGradient),
        onClickNavigationRail = {  },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FTTopAppBar(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    gradient: Brush = Brush.horizontalGradient(FitTrackHeaderGradient),
    onClickNavigationRail: () -> Unit = LocalNavigationRailToggle.current,
    onBack: (() -> Unit)? = null,
    content: @Composable () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .background(gradient),
        windowInsets = WindowInsets(0.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Column(modifier = Modifier.padding(top = 40.dp)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
                Text(
                    text = subTitle,
                    fontSize = 16.sp
                )
            }
            content()
        },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            } else {
                IconButton(onClick = onClickNavigationRail) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
    )
}

