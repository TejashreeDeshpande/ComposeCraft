package com.example.composecraft.presentation.features.fittrack.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.composecraft.data.fittrack.MockWorkouts
import com.example.composecraft.data.fittrack.Workout
import com.example.composecraft.data.fittrack.WorkoutStatus
import com.example.composecraft.presentation.features.fittrack.components.FTAppButton
import com.example.composecraft.presentation.features.fittrack.components.FTAppButtonColors
import com.example.composecraft.presentation.features.fittrack.components.FTCard
import com.example.composecraft.presentation.features.fittrack.components.FTCardColors
import com.example.composecraft.presentation.features.fittrack.components.FTCircleIcon
import com.example.composecraft.presentation.features.fittrack.components.FTTitle
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBar
import com.example.composecraft.presentation.features.fittrack.components.FTTopAppBarColors
import com.example.composecraft.presentation.features.fittrack.navigation.LocalNavigationRailToggle
import com.example.composecraft.presentation.features.fittrack.navigation.SetTrackingDestination
import com.example.composecraft.presentation.features.fittrack.utils.AppUtils
import com.example.composecraft.ui.theme.FitTrackTheme

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    FitTrackTheme {
        Home(navigateTo = {})
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(
    navigateTo: (NavKey) -> Unit,
    onClickMenu: () -> Unit = LocalNavigationRailToggle.current,
) {
    val todayDate = remember { AppUtils.getFormattedTodayDate() }
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            FTTopAppBar(
                title = "FitTrack",
                subTitle = todayDate,
                colors = FTTopAppBarColors.primary(),
                onClickNavigationRail = onClickMenu
            )
        }
    ) { paddingValues ->
        HomeContent(
            navigateTo = navigateTo,
            modifier = Modifier.padding(paddingValues)
        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    navigateTo: (NavKey) -> Unit,
    modifier: Modifier = Modifier,
) {
    val workouts = MockWorkouts.list

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FTAppButton(
            text = "+ New Workout",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = FTAppButtonColors.primary()
        )

        WorkoutList(workouts, navigateTo)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ColumnScope.WorkoutList(workouts: List<Workout>, navigateTo: (NavKey) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        stickyHeader {
            FTTitle("Recent Workouts")
        }
        items(workouts) { workout ->
            WorkoutListItem(
                title = workout.name,
                subTitle = workout.details,
                status = workout.status,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateTo(SetTrackingDestination) },
                onClickIcon = { navigateTo(SetTrackingDestination) }
            )
        }
    }
}


@Preview
@Composable
fun WorkoutListItemPreview() {
    WorkoutListItem(
        title = "Push Day",
        subTitle = "3 exercises * 2 days ago",
        status = WorkoutStatus.IN_PROGRESS,
        onClickIcon = {}
    )
}

@Composable
fun WorkoutListItem(
    title: String,
    subTitle: String,
    status: WorkoutStatus,
    modifier: Modifier = Modifier,
    onClickIcon: () -> Unit
) {
    FTCard(colors = FTCardColors.disabled()) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FTCircleIcon(
                    iconStr = status.iconStr,
                    iconSize = 24.dp,
                    containerColor = status.backgroundColor,
                    contentColor = status.iconTint,
                    onClickActionButton = onClickIcon
                )

                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = subTitle,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
