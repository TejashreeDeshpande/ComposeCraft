package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.presentation.features.fittrack.navigation.LocalNavigationRailToggle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FTTopAppBar(
    title: String,
    subTitle: String,
    colors: TopAppBarColors,
    modifier: Modifier = Modifier,
    onClickNavigationRail: () -> Unit = LocalNavigationRailToggle.current,
    content: @Composable () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        windowInsets = WindowInsets(0.dp),
        colors = colors,
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
            IconButton(onClick = onClickNavigationRail) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun FTTopAppBarPreview() {
    FTTopAppBar(
        title = "FitTrack",
        subTitle = "Your fitness journey",
        colors = FTTopAppBarColors.primary(),
        onClickNavigationRail = TODO(),
    )
}