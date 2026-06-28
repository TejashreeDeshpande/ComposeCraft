package com.example.composecraft.features.flightstatus.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecraft.features.flightstatus.presentation.components.AppCard
import com.example.composecraft.features.flightstatus.presentation.components.AppScaffold
import com.example.composecraft.features.flightstatus.presentation.components.AppTextField
import com.example.composecraft.features.flightstatus.presentation.components.AppTitle
import com.example.composecraft.ui.theme.FlightStatusTheme

@Preview(showBackground = true)
@Composable
fun PreviewSearchFlight() {
    FlightStatusTheme {
        SearchFlight()
    }

}
@Composable
fun SearchFlight() {

    AppScaffold() {
        AppCard() {
            AppTitle("Ait France")
//            AppTextField()
        }
    }
}