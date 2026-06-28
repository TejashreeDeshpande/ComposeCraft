package com.example.composecraft.features.animtedfilter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedFilterRow(
    selectedFilter: MosaicFilter,
    onFilterSelected: (MosaicFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(MosaicFilter.entries) { filter ->
            AnimatedFilterChip(
                filter = filter,
                selected = selectedFilter == filter,
                onClick = { onFilterSelected(filter) }
            )
        }
    }
}

@Composable
fun AnimatedFilterChip(
    filter: MosaicFilter,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(
        targetState = selected,
        label = "FilterChipTransition"
    )

    val width by transition.animateDp(
        transitionSpec = { spring(dampingRatio = 0.65f, stiffness = 350f) },
        label = "ChipWidth"
    ) { isSelected ->
        if (isSelected) 150.dp else 96.dp
    }

    val backgroundAlpha by transition.animateFloat(
        transitionSpec = { tween(250) },
        label = "BackgroundAlpha"
    ) { isSelected ->
        if (isSelected) 1f else 0.18f
    }

    val borderAlpha by transition.animateFloat(
        transitionSpec = { tween(250) },
        label = "BorderAlpha"
    ) { isSelected ->
        if (isSelected) 1f else 0.35f
    }

    val iconRotation by transition.animateFloat(
        transitionSpec = { spring(dampingRatio = 0.45f, stiffness = 500f) },
        label = "IconRotation"
    ) { isSelected ->
        if (isSelected) 360f else 0f
    }
    val scale by transition.animateFloat(
        transitionSpec = { spring(dampingRatio = 0.6f, stiffness = 400f) },
        label = "Scale"
    ) { isSelected ->
        if (isSelected) 1.06f else 1f
    }

    val glowColor = Color(0xFF8B5CF6)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(width)
            .height(48.dp)
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
            .shadow(
                elevation = if (selected) 18.dp else 4.dp,
                shape = RoundedCornerShape(50),
                ambientColor = glowColor,
                spotColor = glowColor
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = borderAlpha),
                shape = RoundedCornerShape(50)
            )
            .clip(RoundedCornerShape(50))
            .clickable { onClick() }
            .background(
                brush = if (selected) {
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFF7C3AED),
                            Color(0xFF9333EA),
                            Color(0xFF4F46E5)
                        )
                    )
                } else {
                    Brush.horizontalGradient(
                        listOf(
                            Color.White.copy(alpha = backgroundAlpha),
                            Color.White.copy(alpha = 0.08f)
                        )
                    )
                }
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 14.dp)
        ) {
            Icon(
                imageVector = filter.icon,
                contentDescription = filter.title,
                tint = if (selected) Color.White else Color(0xFFA78BFA),
                modifier = Modifier
                    .size(20.dp)
                    .graphicsLayer {
                        rotationZ = iconRotation
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = filter.title,
                color = Color.White,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                maxLines = 1
            )

            AnimatedVisibility(
                visible = selected,
                enter = fadeIn() + expandHorizontally(),
                exit = fadeOut() + shrinkHorizontally()
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun LinkedInShowcase() {
    var selectedFilter by remember { mutableStateOf(MosaicFilter.AI) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A)), // Dark Slate background
        contentAlignment = Alignment.Center
    ) {
        AnimatedFilterRow(
            selectedFilter = selectedFilter,
            onFilterSelected = { selectedFilter = it }
        )
    }
}