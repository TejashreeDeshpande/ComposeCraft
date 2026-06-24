package com.example.composecraft

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.composecraft.presentation.features.flightstatus.data.viewmodel.FlightStatusViewModel
import com.example.composecraft.presentation.features.flightstatus.presentation.screens.FlightStatusRoute
import com.example.composecraft.ui.theme.FlightStatusTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlightStatusTheme {
                val viewModel: FlightStatusViewModel = koinViewModel()
                FlightStatusRoute(viewModel)
            }
        }
    }
}
