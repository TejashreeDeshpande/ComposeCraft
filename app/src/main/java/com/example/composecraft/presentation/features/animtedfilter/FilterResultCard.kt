package com.example.composecraft.presentation.features.animtedfilter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterResultCard(
    item: MosaicItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var visible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(item.id) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(350)) +
                slideInVertically(
                    animationSpec = spring(
                        dampingRatio = 0.75f,
                        stiffness = 300f
                    )
                ) { it / 3 } +
                scaleIn(initialScale = 0.92f),
        exit = fadeOut() + scaleOut()
    ) {
        Box(
            modifier = modifier
                .height(170.dp)
                .clip(RoundedCornerShape(28.dp))
                .clickable { onClick() }
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF1E1B4B),
                            Color(0xFF111827)
                        )
                    )
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(18.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.emoji,
                    fontSize = 36.sp
                )

                Column {
                    Text(
                        text = item.title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        maxLines = 2, // Keep titles fully visible (up to 2 lines)
                        overflow = TextOverflow.Visible
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.subtitle,
                        color = Color.White.copy(alpha = 0.65f),
                        fontSize = 13.sp,
                        maxLines = 1, // Allow subtitle ellipsis
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}