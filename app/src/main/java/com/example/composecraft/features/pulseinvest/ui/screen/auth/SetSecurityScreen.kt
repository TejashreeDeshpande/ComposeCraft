package com.example.composecraft.features.pulseinvest.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.AuthViewModel
import com.example.composecraft.features.pulseinvest.ui.components.NumPad
import com.example.composecraft.features.pulseinvest.ui.components.PinDots
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun SetSecurityScreen(vm: AuthViewModel, onSuccess: () -> Unit) {
    val state by vm.state.collectAsState()
    LaunchedEffect(state.pin.length) { if (state.pin.length == 6) { vm.setupPin(); onSuccess() } }

    Box(modifier = Modifier.fillMaxSize().background(PulseDark)) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Set Up Security", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = PulseTextPrimary)
            Spacer(Modifier.height(8.dp))
            Text("Create a 6-digit PIN", fontSize = 14.sp, color = PulseTextSecondary, textAlign = TextAlign.Center)
            Spacer(Modifier.height(32.dp))
            PinDots(filledCount = state.pin.length)
            Spacer(Modifier.height(40.dp))
            NumPad(onDigit = vm::onPinDigit, onBackspace = vm::onPinBackspace)
        }
    }
}
