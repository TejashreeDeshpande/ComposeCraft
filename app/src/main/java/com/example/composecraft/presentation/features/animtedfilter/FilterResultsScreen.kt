package com.example.composecraft.presentation.features.animtedfilter

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterResultsScreen(modifier: Modifier = Modifier) {
    var selectedFilter by remember {
        mutableStateOf(MosaicFilter.AI)
    }

    var selectedItem by remember {
        mutableStateOf<MosaicItem?>(null)
    }

    val filteredItems by remember {
        derivedStateOf {
            mockMosaicItems.filter { it.filter == selectedFilter }
        }
    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Compose Animation",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                },
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1E293B), // Slate 800
                            Color(0xFF0F172A), // Slate 900
                            Color(0xFF070A12)  // Near black
                        )
                    )
                ),
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White,
                    scrolledContainerColor = Color(0xFF0F172A).copy(alpha = 0.95f)
                ),
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = Color(0xFF070A12)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 24.dp)
                    .navigationBarsPadding()
            ) {
                Text(
                    text = "Interactive motion components",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                AnimatedFilterRow(
                    selectedFilter = selectedFilter,
                    onFilterSelected = { selectedFilter = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AnimatedContent(
                    targetState = filteredItems,
                    transitionSpec = {
                        fadeIn(tween(300)) + slideInVertically { it / 4 } togetherWith
                                fadeOut(tween(200)) + slideOutVertically { -it / 4 }
                    },
                    label = "FilteredResults"
                ) { items ->
                    if (items.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 100.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Text(
                                text = "No podcast available",
                                color = Color.White.copy(alpha = 0.5f),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(
                                items = items,
                                key = { it.id }
                            ) { item ->
                                FilterResultCard(
                                    item = item,
                                    onClick = { selectedItem = item }
                                )
                            }
                        }
                    }
                }
            }

            selectedItem?.let { item ->
                HeroExpandedCard(
                    item = item,
                    onClose = { selectedItem = null }
                )
            }
        }
    }
}
