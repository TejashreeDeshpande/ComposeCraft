package com.example.composecraft.features.pulseinvest.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.ui.components.PulseButton
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun OnboardingScreen(onNext: () -> Unit, onSkip: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(PulseDark)) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = onSkip, modifier = Modifier.align(Alignment.End)) {
                Text("Skip", color = PulseTextSecondary, fontSize = 14.sp)
            }
            Spacer(Modifier.weight(1f))
            Text("💰", fontSize = 80.sp)
            Spacer(Modifier.height(24.dp))
            Text("Invest with", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = PulseTextPrimary, textAlign = TextAlign.Center)
            Text("Confidence", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = PulseGreen, textAlign = TextAlign.Center)
            Spacer(Modifier.height(16.dp))
            Text(
                "AI insights, real-time data, and smart tools to grow your wealth.",
                fontSize = 15.sp, color = PulseTextSecondary,
                textAlign = TextAlign.Center, lineHeight = 22.sp
            )
            Spacer(Modifier.height(32.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(Modifier.size(24.dp, 8.dp).clip(RoundedCornerShape(4.dp)).background(PulseGreen))
                repeat(2) { Box(Modifier.size(8.dp).clip(RoundedCornerShape(4.dp)).background(PulseSurface2)) }
            }
            Spacer(Modifier.weight(1f))
            PulseButton("Next", onClick = onNext)
        }
    }
}
