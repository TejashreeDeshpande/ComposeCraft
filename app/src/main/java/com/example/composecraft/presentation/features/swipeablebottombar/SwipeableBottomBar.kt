package com.example.composecraft.presentation.features.swipeablebottombar

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SwipeCardBottomBarScreen() {
    val tabs = listOf("Home", "Search", "Profile")
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Search,
        Icons.Default.Person
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            Card(
                modifier = Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(72.dp),
                shape = RoundedCornerShape(28.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    tabs.forEachIndexed { index, title ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            icon = {
                                Icon(
                                    imageVector = icons[index],
                                    contentDescription = title
                                )
                            },
                            label = {
                                Text(title)
                            }
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Selected: ${tabs[selectedIndex]}",
                fontSize = 24.sp
            )
        }
    }
}
//
//@Composable
//fun SwipeableBottomBar() {
//    val tabs = listOf("Home", "Search", "Profile")
//    var selectedIndex by remember { mutableIntStateOf(0) }
//
//    Scaffold(
//        bottomBar = {
//            NavigationBar(
//                modifier = Modifier.pointerInput(Unit) {
//                    var totalDrag = 0f
//                    detectHorizontalDragGestures(
//                        onDragStart = {
//                            totalDrag = 0f
//                        },
//                        onHorizontalDrag = { _, dragAmount ->
//                            totalDrag += dragAmount
//                        },
//                        onDragEnd = {
//                            if (totalDrag < -80) {
//                                selectedIndex = (selectedIndex + 1).coerceAtMost(tabs.lastIndex)
//                            } else if (totalDrag > 80) {
//                                selectedIndex = (selectedIndex - 1).coerceAtLeast(0)
//                            }
//                        }
//                    )
//                }
//            ) {
//                tabs.forEachIndexed { index, title ->
//                    NavigationBarItem(
//                        selected = selectedIndex == index,
//                        onClick = { selectedIndex = index },
//                        icon = {
//                            Icon(
//                                imageVector = when (title) {
//                                    "Home" -> Icons.Default.Home
//                                    "Search" -> Icons.Default.Search
//                                    else -> Icons.Default.Person
//                                },
//                                contentDescription = title
//                            )
//                        },
//                        label = { Text(title) }
//                    )
//                }
//            }
//        }
//    ) { padding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding),
//            contentAlignment = Alignment.Center
//        ) {
//            Text("Selected: ${tabs[selectedIndex]}")
//        }
//    }
//}