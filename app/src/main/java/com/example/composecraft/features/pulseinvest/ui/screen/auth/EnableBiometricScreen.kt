package com.example.composecraft.features.pulseinvest.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.AuthViewModel
import com.example.composecraft.features.pulseinvest.ui.components.PulseButton
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun EnableBiometricScreen(vm: AuthViewModel, onSuccess: () -> Unit, onSkip: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(PulseDark)) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(110.dp).clip(CircleShape)
                    .border(3.dp, PulseGreen, CircleShape)
                    .background(PulseSurface),
                contentAlignment = Alignment.Center
            ) { Text("👆", fontSize = 52.sp) }
            Spacer(Modifier.height(28.dp))
            Text("Enable Biometric Login", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = PulseTextPrimary, textAlign = TextAlign.Center)
            Spacer(Modifier.height(12.dp))
            Text("Use Face ID or Touch ID for\nfaster and more secure access.",
                fontSize = 14.sp, color = PulseTextSecondary, textAlign = TextAlign.Center, lineHeight = 20.sp)
            Spacer(Modifier.height(40.dp))
            PulseButton("Enable", onClick = { vm.enableBiometric(); onSuccess() })
            Spacer(Modifier.height(12.dp))
            TextButton(onClick = onSkip) {
                Text("Not Now", color = PulseTextSecondary, fontSize = 14.sp)
            }
        }
    }
}
