package com.example.composecraft.presentation.features.draggablelist

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import java.util.Collections

@Composable
fun DragToPositionList() {
    var items by remember {
        mutableStateOf(
            listOf("Flight Scheduled", "Check-In Open", "Boarding", "In Air", "Landed")
        )
    }

    val listState = rememberLazyListState()

    var draggedItemIndex by remember { mutableStateOf<Int?>(null) }
    var targetIndex by remember { mutableStateOf<Int?>(null) }
    var dragOffsetY by remember { mutableFloatStateOf(0f) }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(
            items = items,
            key = { _, item -> item }
        ) { index, item ->

            val isDragging = draggedItemIndex == index
            val isTarget = targetIndex == index

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        translationY = if (isDragging) dragOffsetY else 0f
                        scaleX = if (isDragging) 1.04f else 1f
                        scaleY = if (isDragging) 1.04f else 1f
                    }
                    .zIndex(if (isDragging) 1f else 0f)
                    .pointerInput(items) {
                        detectDragGesturesAfterLongPress(
                            onDragStart = {
                                draggedItemIndex = index
                                targetIndex = index
                                dragOffsetY = 0f
                            },
                            onDragEnd = {
                                val from = draggedItemIndex
                                val to = targetIndex

                                if (from != null && to != null && from != to) {
                                    items = items.toMutableList().apply {
                                        val movedItem = removeAt(from)
                                        add(to, movedItem)
                                    }
                                }

                                draggedItemIndex = null
                                targetIndex = null
                                dragOffsetY = 0f
                            },
                            onDragCancel = {
                                draggedItemIndex = null
                                targetIndex = null
                                dragOffsetY = 0f
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                dragOffsetY += dragAmount.y

                                val itemHeightPx = 72f
                                val from = draggedItemIndex ?: return@detectDragGesturesAfterLongPress

                                val calculatedTarget =
                                    (from + (dragOffsetY / itemHeightPx).toInt())
                                        .coerceIn(0, items.lastIndex)

                                targetIndex = calculatedTarget
                            }
                        )
                    },
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (isDragging) 14.dp else 4.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(
//                            when {
//                                isDragging -> MaterialTheme.colorScheme.primaryContainer
//                                isTarget -> MaterialTheme.colorScheme.secondaryContainer
//                                else -> MaterialTheme.colorScheme.surface
//                            }
//                        )
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DragHandle,
                        contentDescription = "Drag"
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = item,
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}