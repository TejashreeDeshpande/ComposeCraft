package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    subTitle: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    navButtonIcon: String? = null, // emoji
    actionButtonIcon: String? = null, // emoji
    actionButtonIconSize: Dp = 44.dp,
    onClickActionButton: () -> Unit = {},
    onClickBackButton: () -> Unit = {},
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor
        ),
        title = {
            Column(modifier = modifier.padding(8.dp)) {
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
        navigationIcon = { },
        actions = {
            if (actionButtonIcon != null) {
                IconButton(
                    onClick = { onClickActionButton() },
                    modifier = Modifier
                        .size(actionButtonIconSize)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = actionButtonIcon,
                        fontSize = 10.sp,
                    )
                }
            }
        },
    )
}