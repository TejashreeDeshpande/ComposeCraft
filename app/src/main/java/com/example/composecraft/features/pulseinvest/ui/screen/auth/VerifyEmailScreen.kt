package com.example.composecraft.features.pulseinvest.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.ui.components.PulseButton
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun VerifyEmailScreen(email: String, onContinue: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(PulseDark)) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("✉️", fontSize = 72.sp)
            Spacer(Modifier.height(24.dp))
            Text("Verify Your Email", fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, color = PulseTextPrimary, textAlign = TextAlign.Center)
            Spacer(Modifier.height(12.dp))
            Text("We've sent a verification link to", fontSize = 15.sp, color = PulseTextSecondary, textAlign = TextAlign.Center)
            Text(email, fontSize = 15.sp, color = PulseGreen, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
            Spacer(Modifier.height(24.dp))
            Text("Didn't receive the email?", fontSize = 14.sp, color = PulseTextSecondary)
            TextButton(onClick = {}) { Text("Resend Email", color = PulseGreen, fontSize = 14.sp) }
            Spacer(Modifier.height(24.dp))
            PulseButton("Open Email App", onClick = onContinue)
        }
    }
}
