package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FTTopAppBar(
    title: String,
    subTitle: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    actionButtonIcon: String? = null, // emoji
    actionButtonIconSize: Dp = 44.dp,
    onClickActionButton: () -> Unit = {},
    onClickBackButton: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        windowInsets = WindowInsets(0.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor
        ),
        title = {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = title,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
                Text(
                    text = subTitle,
                    color = textColor,
                    fontSize = 16.sp
                )
            }
        },
        navigationIcon = {
            onClickBackButton?.let {
                IconButton(
                    onClick = onClickBackButton,
                    shape = CircleShape,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = backgroundColor.copy(alpha = 0.6f)
                    ),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = textColor,
                    )
                }
            }
        },
        actions = {
            if (actionButtonIcon != null) {
                IconButton(
                    onClick = onClickActionButton,
                    modifier = Modifier
                        .size(actionButtonIconSize)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = actionButtonIcon,
                        fontSize = 24.sp,
                    )
                }
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
        backgroundColor = Color(0xFF9F5F91),
        textColor = Color.White,
        actionButtonIcon = "🔥"
    )
}