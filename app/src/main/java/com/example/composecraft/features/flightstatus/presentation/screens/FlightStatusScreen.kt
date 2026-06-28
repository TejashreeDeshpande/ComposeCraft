package com.example.composecraft.features.flightstatus.presentation.screens

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Airlines
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CalendarViewDay
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composecraft.R
import com.example.composecraft.features.flightstatus.data.model.Aircraft
import com.example.composecraft.features.flightstatus.data.model.FlightDetails
import com.example.composecraft.features.flightstatus.data.viewmodel.FlightStatusUiState
import com.example.composecraft.features.flightstatus.data.viewmodel.FlightStatusViewModel
import com.example.composecraft.features.flightstatus.presentation.components.AppBody
import com.example.composecraft.features.flightstatus.presentation.components.AppBodyBold
import com.example.composecraft.features.flightstatus.presentation.components.AppCaption
import com.example.composecraft.features.flightstatus.presentation.components.AppCard
import com.example.composecraft.features.flightstatus.presentation.components.AppScaffold
import com.example.composecraft.features.flightstatus.presentation.components.AppTitle
import com.example.composecraft.features.flightstatus.presentation.components.AppToggle
import com.example.composecraft.features.flightstatus.presentation.components.AppTopBar
import com.example.composecraft.features.flightstatus.presentation.components.FlightProgressContent
import com.example.composecraft.ui.theme.FlightStatus
import com.example.composecraft.ui.theme.FlightStatusTheme
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Mode", showBackground = true)
@Composable
fun PreviewFlightStatusScreen(
    @PreviewParameter(FlightStatusStateProvider::class) uiState: FlightStatusUiState
) {
    FlightStatusTheme(darkTheme = false) {
        FlightStatusScreen(
            state = uiState,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewFlightStatusScreenDark(
    @PreviewParameter(FlightStatusStateProvider::class) uiState: FlightStatusUiState
) {
    FlightStatusTheme(darkTheme = true) {
        FlightStatusScreen(
            state = uiState.copy(isDarkMode = true)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightStatusRoute(
    viewModel: FlightStatusViewModel = koinViewModel(),
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FlightStatusTheme(darkTheme = uiState.isDarkMode) {
        FlightStatusScreen(
            state = uiState,
            onSaveFlightChange = { viewModel.toggleSaveFlight(it) },
            onThemeChange = { viewModel.toggleTheme(it) },
            onBack = onBack
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FlightStatusScreen(
    state: FlightStatusUiState,
    onSaveFlightChange: (Boolean) -> Unit = {},
    onThemeChange: (Boolean) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val animatedProgress by animateFloatAsState(
        targetValue = state.progress,
        animationSpec = tween(
            durationMillis = 900,
            easing = FastOutSlowInEasing
        ),
        label = "flightProgress"
    )

    val flightStatus = state.currentStep?.status

    AppScaffold(
        topBar = {
            AppTopBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AppTitle(stringResource(R.string.flight_status), color = MaterialTheme.colorScheme.primary)
                        AppBody(state.flightDetails?.route ?: "")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (flightStatus != null && state.flightDetails != null) {
                FlightInfo(
                    airlineName = state.flightDetails.airlineName,
                    flightNumber = state.flightDetails.flightNumber,
                    aircraft = state.flightDetails.aircraft,
                    formattedDate = state.date,
                    status = flightStatus
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            TravelStartDestinationName()
            Spacer(modifier = Modifier.height(16.dp))
            if (state.currentStep != null) {
                FlightProgressContent(
                    progress = animatedProgress,
                    step = state.currentStep,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            TravelStartDestinationETA()
            Spacer(modifier = Modifier.height(24.dp))
            TerminalInfoRow(
                flightDetails = state.flightDetails,
                currentStatus = flightStatus
            )
            Spacer(modifier = Modifier.height(16.dp))
            BaggageClaimInfo()
            Spacer(modifier = Modifier.height(16.dp))
            SaveFlightToggle(
                isSaveFlight = state.isSaveFlight,
                onSaveFlightChange = onSaveFlightChange
            )
            Spacer(modifier = Modifier.height(8.dp))
            SaveLiveActivitiesToggle()
            Spacer(modifier = Modifier.height(8.dp))
            ChangeThemeToggle(
                isDarkMode = state.isDarkMode,
                onThemeChange = onThemeChange
            )
            Spacer(modifier = Modifier.height(16.dp)) // Extra bottom padding
        }
    }

}

@Composable
fun ChangeThemeToggle(
    isDarkMode: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    AppToggle(
        modifier = Modifier.fillMaxWidth(),
        checked = isDarkMode,
        onCheckedChange = onThemeChange,
        leading = {
            AppBodyBold("Dark Mode")
        })
}

@Composable
fun SaveFlightToggle(
    isSaveFlight: Boolean,
    onSaveFlightChange: (Boolean) -> Unit
) {
    AppToggle(
        modifier = Modifier.fillMaxWidth(),
        checked = isSaveFlight,
        onCheckedChange = onSaveFlightChange,
        leading = {
            AppBodyBold("Save this flight")
        })
}

@Composable
fun SaveLiveActivitiesToggle() {
    AppToggle(
        modifier = Modifier.fillMaxWidth(),
        checked = false,
        onCheckedChange = {},
        leading = {
            Column {
                AppBodyBold("Live Activities")
                AppBody("Give live flight updated on your Lock Screen starting 4 hours before departure")
            }
        })
}

@Composable
fun BaggageClaimInfo(modifier: Modifier = Modifier) {
    AppCard(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingBag,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            AppBody(
                "Baggage Claim",
                modifier = Modifier.weight(1f)
            )
            Column(horizontalAlignment = Alignment.End) {
                AppBodyBold("32")
                AppCaption("Belt")
            }
        }
    }
}

@Composable
fun TerminalInfoRow(
    flightDetails: FlightDetails?,
    currentStatus: FlightStatus?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Departure
        AppCard(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppCaption("Terminal")
                    AppBodyBold("1")
                }
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppCaption("Gate")
                    AppBodyBold(flightDetails?.departureGate ?: "--")
                }
            }
        }
        // Destination
        AppCard(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppCaption("Terminal")
                    AppBodyBold("2E")
                }
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppCaption("Gate")
                    val showArrivalGate = currentStatus == FlightStatus.LANDING || currentStatus == FlightStatus.LANDED
                    AppBodyBold(if (showArrivalGate) flightDetails?.arrivalGate ?: "--" else "--")
                }
            }
        }
    }
}

@Composable
fun TravelStartDestinationName() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppCaption("San Francisco")
            AppCaption("Paris CDG")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppTitle(
                "SFO",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            AppTitle(
                "CDG",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TravelStartDestinationETA() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            AppTitle(text = "15:05", style = MaterialTheme.typography.titleLarge)
            AppCaption(text = "Departure")
        }

        Column(horizontalAlignment = Alignment.End) {
            AppTitle(text = "10:52", style = MaterialTheme.typography.titleLarge)
            AppBodyBold(text = "Scheduled 10:55", color = MaterialTheme.colorScheme.secondary)
            AppCaption(text = "Arrival")
        }
    }
}


@Composable
fun FlightInfo(
    airlineName: String,
    flightNumber: String,
    aircraft: Aircraft,
    formattedDate: String,
    status: FlightStatus,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 8.dp)) {
        // Name & status
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppTitle(
                airlineName,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Surface(
                color = status.color.copy(alpha = 0.15f),
                contentColor = status.color,
                shape = RoundedCornerShape(16.dp),
            ) {
                AppCaption(
                    status.title.uppercase(),
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    )
                )
            }
        }
        // Date, number, aircraft details
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppBody(
                text = formattedDate,
                icon = {
                    Icon(
                        imageVector = Icons.Default.CalendarViewDay,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 4.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            )
            AppBody(
                text = flightNumber,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Airlines,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 4.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            )
            AppBody(
                text = aircraft.model,
                icon = {
                    Icon(
                        imageVector = Icons.Default.AirplanemodeActive,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 4.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            )
        }
    }
}
